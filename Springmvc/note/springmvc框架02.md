###### 1.Controller接口及其实现类

```java
Controller是控制器接口,此处只有一个方法handleRequest,用于进行请求的功能处理,处理完请求后返回ModelAndView对象(Model模型数据部分 和 View视图部分).
	
如果想直接在处理器/控制器中使用response向客户端写回数据,可以通过返回null来告诉DispatcherServlet我们已经写出响应了,不需要它进行视图解析；

Spring默认提供了一些Controller接口的实现类以方便我们使用,在Eclipse中选择Controller接口然后右键open type Hierarchy即可查看该接口的实现类,每个实现类都有自己特殊的功能,这里以实现类AbstractController为例简单介绍下.查看AbstractController类中代码可知,我们写一个Controller的时候可以继承AbstractController然后实现handleRequestInternal方法即可.

e.g:
【可选】的会话的串行化访问功能:
	//即同一会话,线程同步
	public class HelloWorldController extends AbstractController{
		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String name = request.getParameter("name");
			
			//ModelAndView对象中包括了要返回的逻辑视图,以及数据模型
			ModelAndView mv = new ModelAndView();
			//设置视图名称,可以是字符串 也可以是视图对象
			mv.setViewName("hello");
			//设置数据模型
			mv.addObject("name", name);
			return mv;
		}
	}

<bean name="/hello" class="web.controller.HelloWorldController">
	<property name="synchronizeOnSession" value="test"></property>
</bean>

e.g:	
直接通过response写响应:
	public class HelloWorldController extends AbstractController{
		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		response.getWriter().write("Hello World!!");		
		//如果想直接在该处理器/控制器写响应 可以通过返回null告诉DispatcherServlet自己已经写出响应了,不需要它进行视图解析
			return null;
		}
	}
	
e.g:
强制请求方法类型:
//只支持post和get方法
<bean name="/hello" class="web.controller.HelloWorldController">
		<property name="supportedMethods" value="POST,GET"></property>
</bean>

e.g:
当前请求的session前置条件检查,如果当前请求无session将抛出HttpSessionRequiredException异常:
	//在进入该控制器时,一定要有session存在,否则抛出HttpSessionRequiredException异常

<bean name="/hello" class="web.controller.HelloWorldController">
		<property name="requireSession" value="true"/>
</bean>
```

###### 2.自定义适配器

```java
一般情况下,springMVC中SimpleControllerHandlerAdapter会是我们常用的适配器,也是SpringMVC中默认的适配器,该适配器中的主要代码如下:
	public class SimpleControllerHandlerAdapter implements HandlerAdapter {
		public boolean supports(Object handler) {
			return (handler instanceof Controller);
		}
		public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			return ((Controller) handler).handleRequest(request, response);
		}
	}
	从代码中可以看出,它首先会判断我们的handler是否实现了Controller接口,如果实现了,那么会调用Controller接口中的handleRequest方法

	那么根据这种方式能看出,我们也可以有自己的适配器的实现,那么就可以让任意类成为SpringMVC中的handler了,无论我们的类是否实现了Controller接口
	
e.g:
	//自己的接口
	public interface MyHandler {
		public ModelAndView handler_test(HttpServletRequest request, HttpServletResponse response) throws Exception;
	}
	//自己的适配器
	public class MyHandlerAdapter implements HandlerAdapter{
		@Override
		public boolean supports(Object handler) {
			return (handler instanceof MyHandler);
		}

		@Override
		public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			return ((MyHandler)handler).handler_test(request, response);
		}

		@Override
		public long getLastModified
 (HttpServletRequest request, Object handler) {
				return -1L;
		}
}
		
	//自己的hander:(就是我们之前写的Controller)
	public class TestController implements MyController{
		@Override
		public ModelAndView handler_test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name =request.getParameter("name");
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("name", name);
		return mv;
	}
}

最后在spring的配置中把我们的适配器进行配置即可正常使用.
```

###### 3.处理器拦截器

```Java
SpringMVC的处理器拦截器类似于Servlet开发中的过滤器Filter,
用于对处理器进行预处理和后处理；
1)常见应用场景
	1、日志记录
	2、权限检查
	3、性能监控
	4、通用行为. 例如读取用户cookie
	5、OpenSessionInView 例如在Hibernate中,在进入处理器前打开Session,在完成后关闭Session等.
	
2)拦截器接口
	public interface HandlerInterceptor {
			boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception;

			void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception;

			void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception;
	}	

	preHandle方法：预处理回调方法,实现处理器的预处理,第三个参数为自己定义的处理器(本次请求要访问的那个Controller)返回值:true表示继续流程(如调用下一个拦截器或处理器)，false表示流程中断(如登录检查失败),不会继续调用其他的拦截器或处理器,此时我们需要通过response来产生响应；
		
	postHandle方法：后处理回调方法,实现处理器的后处理(但在渲染视图之前),此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理,modelAndView也可能为null；	
		
	afterCompletion方法：整个请求处理完毕回调方法,即在视图渲染完毕时回调；
	
3）拦截器适配器
有时候我们可能只需要实现三个回调方法中的某一个,如果实现HandlerInterceptor 接口的话,三个方法必须实现,不管你需不需要.此时spring提供了一个HandlerInterceptorAdapter适配器(适配器模式),允许我们只实现需要的回调方法.

在HandlerInterceptorAdapter中,对HandlerInterceptor接口中的三个方法都进行了空实现,其中preHandle方法的返回值,默认是true.
        
4)测试使用一个拦截器的情形的代码
拦截器适配器代码:
		public class MyInterceptor1 extends HandlerInterceptorAdapter{
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
throws Exception {
				System.out.println("MyInterceptor1 preHandle");
				return true;
			}
            
			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
				System.out.println("MyInterceptor1 postHandle");
			}
            
			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
				System.out.println("MyInterceptor1 afterCompletion");
			}
		}

配置文件:
<bean name="handlerInterceptor1" class="web.interceptor.MyInterceptor1"/>

<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
	<property name="interceptors">
		<list>
			<ref bean="handlerInterceptor1"/>
		</list>
	</property>
</bean>		

访问一个测试的Controller查看结果:
	MyInterceptor1 preHandle
	TestController执行
	MyInterceptor1 postHandle
	MyInterceptor1 afterCompletion
        
5)测试使用俩个拦截器的情形的代码
俩个拦截器的代码和上面类似,只是每个输出的内容不同

配置文件:
<bean name="handlerInterceptor1" class="web.interceptor.MyInterceptor1"/>
<bean name="handlerInterceptor2" class="web.interceptor.MyInterceptor1"/>

<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
	<property name="interceptors">
		<list>
			<ref bean="handlerInterceptor1"/>
			<ref bean="handlerInterceptor2"/>
		</list>
	</property>
</bean>
注意:<list>标签中引用拦截器的顺序会影响结果输出的顺序

访问一个测试的Controller查看结果:
	MyInterceptor1 preHandle
	MyInterceptor2 preHandle
	TestController执行
	MyInterceptor2 postHandle
	MyInterceptor1 postHandle
	MyInterceptor2 afterCompletion
	MyInterceptor1 afterCompletion
	
6)若Controller采用的是注解配置,则拦截器需要mvc标签进行配置：
注意:每个<mvc:interceptor>只能配置一个拦截器

<mvc:interceptors>
	<mvc:interceptor>
		<mvc:mapping path="/**"/>
		<ref bean="handlerInterceptor1"/>
	</mvc:interceptor>
</mvc:interceptors>

// 注意：/*和/**的区别  
<mvc:interceptors>
<!-- 下面所有的mvc映射路径都会被这个拦截器拦截 -->
<bean class="web.interceptor.MyInterceptor1" />
	<mvc:interceptor>
		<mapping path="/**"/>
		<exclude-mapping path="/admin/**"/>
		<bean class="web.interceptor.MyInterceptor2" />
	</mvc:interceptor>
	<mvc:interceptor>
		<mapping path="/secure/*"/>
		<bean class="web.interceptor.MyInterceptor3" />
	</mvc:interceptor>
</mvc:interceptors>
	
7)拦截器是单例
不管多少用户请求多少次都只有一个拦截器实例,即线程不安全.
    
所以在必要时可以在拦截器中使用ThreadLocal,它是和线程绑定的,一个线程一个ThreadLocal,A线程的ThreadLocal只能看到A线程的ThreadLocal,不能看到B线程的ThreadLocal.
		
8)记录执行Controller所用时间
public class TimeInterceptor extends HandlerInterceptorAdapter{
	//拦截器是单例,不是线程安全的,所以这里使用ThreadLocal
	private ThreadLocal<Long> local = new ThreadLocal<>();
			
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long start = System.currentTimeMillis();
		local.set(start);
		return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long end = System.currentTimeMillis();
		System.out.println("共耗时:"+(end-local.get()));
	}
}

9)登录检查
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//请求到登录页面放行
     if(request.getServletPath().startsWith("/login")) {
			return true;
 }

		//如果用户已经登录放行					             if(request.getSession().getAttribute("username") != null) {
			return true;
	  }

	   //重定向到登录页面   
       response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}

	
	注意:推荐能使用servlet规范中的过滤器Filter实现的功能就用Filter实现,因为HandlerInteceptor只有在SpringWebMVC环境下才能使用,因此Filter是最通用的、最先应该使用的。
```

###### 4.基于注解的SpringMVC

```Java xml
1)用于支持注解的配置
使用基于注解的配置可以省略很多操作,更方便.我们之前所看到的所有的xml配置,如果替换成基于注解的配置只需要在spring的xml文件中做如下配置:
<mvc:annotation-driven/>
		
在Spring中,
	处理器类可以使用   @Controller注解；
	业务逻辑层可以使用 @Service注解；
	数据持久层可以使用 @Repository注解；

如果在处理器上使用 @Controller注解,那么还需要在配置文件中指定哪个包下面的类使用了该注解:
<context:component-scan base-package="web.controller"></context:component-scan>


2)基于注解的Controller
使用注解后,就不需要再实现特定的接口,任意一个javaBean对象都可以当做处理器对象,对象中任意一个方法都可以作为处理器方法,只需在类上加上 @Controller注解,方法上加上 @RequestMapping注解即可

e.g:
web.xml中:
<servlet>
	<servlet-name>SpringMVC</servlet-name>
	<servlet-class>   org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
            classpath:spring-web-mvc.xml
        </param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>SpringMVC</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
		
src下面的spring-web-mvc.xml中:
<mvc:annotation-driven/>
<context:component-scan base-package="web.controller">
</context:component-scan>		
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
	<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>  
	<property name="prefix" value="/WEB-INF/jsp/"/>  
	<property name="suffix" value=".jsp"/>  
</bean>


自定义的Controller中:
@Controller
public class HomeController {
	@RequestMapping("/home")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}

如上代码,使用 @Controller表明HomeController类是一个处理器类,通过 @RequestMapping("/home")表明当url请求名为/home时,调用home方法执行处理,当处理完成之后返回ModelAndView对象.因为在spring-web-mvc.xml中配置了视图解析器的前缀和后缀,所以最后视图home.jsp被返回.

3)基于注解的Controller的返回值
	I.返回ModelAndView,和之前一样
	II.返回String,表示跳转的逻辑视图名字,模型可以通过参数传递
	@Controller
	public class HomeController {
		@RequestMapping("/home")
		public String home(Model model){
			model.addAttribute("msg", "hello world");
			return "index";
		}
	}
		
	III.声明返回类型为void
	可以通过参数获取request和response，分别使用服务器内部跳转和重定向，自己来决定要跳转的位置。
	@Controller
	public class HomeController {
		@RequestMapping("/home")
		public void home(HttpServletRequest request,HttpServletResponse response){
			String username = request.getParameter("username");
					response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("hello world! "+username);
			//或者使用servlet的方式进行跳转/重定向			
	}
}
```

###### 5.Spring2.5中引入注解对处理器(handler)支持

```Java
@Controller
	用于标识是处理器类；
@RequestMapping
	请求到处理器功能方法的映射规则；
@RequestParam
	请求参数到处理器功能处理方法的方法参数上的绑定；
@ModelAttribute
	请求参数到命令对象的绑定；
@SessionAttributes
	用于声明session 级别存储的属性,放置在处理器类上,通常列出模型属性(如@ModelAttribute)对应的名称,则这些属性会透明的保存到session 中
@InitBinder
	自定义数据绑定注册支持,用于将请求参数转换到命令对象属性的对应类型；
```

###### 6.Spring3引入了更多的注解，其中包含了对RESTful架构风格的支持

```
@CookieValue
	cookie数据到处理器功能处理方法的方法参数上的绑定；
@RequestHeader
	请求头数据到处理器功能处理方法的方法参数上的绑定；
@RequestBody
	请求的body体的绑定
@ResponseBody
	处理器功能处理方法的返回值作为响应体
@ResponseStatus
	定义处理器功能处理方法/异常处理器返回的状态码和原因；
@ExceptionHandler
	注解式声明异常处理器；
@PathVariable
	请求URI 中的模板变量部分到处理器功能处理方法的方法参数上的绑定,从而支持RESTful架构风格的URI；
```

###### 7.Spring3中引入的mvc命名空间

```xml
mvc这个命名空间是在Spring3中引入的,其作用是用来支持mvc的配置的，需要在<bean>中声明出这个命名空间及其对应的schemaLocation中的值.
	<mvc:annotation-driven>
自动注册基于注解风格的处理器和适配器:
	在spring2.5中是DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter；

	在spring3中是RequestMappingHandlerMapping和RequestMappingHandlerAdapter；
        
	同时还支持各种数据的转换器.


<!-- <mvc:interceptors>:配置自定义的处理器拦截器 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
				<ref bean="handlerInterceptor1"/>
			</mvc:interceptor>
	</mvc:interceptors>

<!-- <mvc:view-controller>:收到相应请求后直接选择相应的视图 -->
	<mvc:view-controller path="/hello" view-name="test"></mvc:view-controller>

<!-- <mvc:resources>:逻辑静态资源路径到物理静态资源路径的对应 -->
	<mvc:resources mapping="/images/**" location="/images/"/>  
	<mvc:resources mapping="/js/**" location="/js/"/> 
	<mvc:resources mapping="/css/**" location="/css/"/> 
	
<!-- <mvc:default-servlet-handler> -->
当在web.xml中DispatcherServlet使用<url-pattern>/</url-pattern> 映射的时候,会静态资源也映射了,如果配置了这个mvc标签,那么再访问静态资源的时候就转交给默认的Servlet来响应静态文件,否则报404 找不到静态资源错误.
```

###### 8.@Controller和@RequestMapping

```Java
1)声明处理器
	@Controller
	public class HelloWorldController {
		
	}

2)映射处理器中的【功能处理方法】
	@Controller
	public class HelloWorldController {
		@RequestMapping("/home")
		public ModelAndView home(){
			ModelAndView mv = new ModelAndView("index");
			return mv;
		}
	}
	这样写表明该方法映射的url路径为/home

3)@RequestMapping也可以写在处理器类上
	@RequestMapping("/test")
	@Controller
	public class HomeController {
		@RequestMapping("/home")
		public ModelAndView home(){
			ModelAndView mv = new ModelAndView("index");
			return mv;
		}
	}
	这样写表明该方法映射的url路径为/test/home
	
注意:功能处理方法的方法返回值可以是String类型,表示逻辑视图的名字,可以不用返回ModelAndView对象.
	e.g:
	@Controller
	public class HelloWorldController {
		@RequestMapping("/home")
		public String home(){
			return "index";
		}
	}
```

###### 9.请求映射

```http
假设浏览器发送了一个请求如下:
	-------------------------------
	POST /login	HTTP1.1
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
	Accept-Encoding: gzip, deflate
	Accept-Language: zh-CN,en;q=0.8,zh;q=0.5,en-US;q=0.3
	Connection: keep-alive
	Cookie: JSESSIONID=DBC6367DEB1C024A836F3EA35FCFD5A2
	Host: 127.0.0.1:8989
	Upgrade-Insecure-Requests: 1
	User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0

	username=tom&password=123
	--------------------------------


	http协议的请求格式如下:
	---------------------------------
	请求方法 URL 协议版本号
	请求头信息
	请求头信息
	请求头信息
	..
	回车换行
	请求正文
	---------------------------------

	
	从格式中我们可以看到【请求方法、URL、请求头信息、请求正文】这四部分一般是可变的,因此我们可以把请求中的这些信息在处理器的【功能处理方法】中进行的映射,因此请求的映射分为如下几种:
URL路径映射：
    使用URL映射到处理器的功能处理方法；
请求方法映射限定：
    例如限定功能处理方法只处理GET请求；
请求参数映射限定：
    例如限定只处理包含username参数的请求；
请求头映射限定：
    例如限定只处理"Accept=application/json"的请求.
```

###### 10.URL路径映射

```java
1)普通URL路径映射
	@RequestMapping(value="/test")
	@RequestMapping("/hello")
		注解中只出现一个参数且参数名为value的话,可以将参数名去掉;
	@RequestMapping(value={"/test", "/user/hello"})
		多个URL路径可以映射到同一个处理器的功能处理方法;
	
2)URI模板模式映射
@RequestMapping(value="/users/{userId}"):
	{XXX}占位符, 请求的URL可是"/users/123456"或"/users/abcd",之后可以通过@PathVariable可以提取URI模板模式中的{XXX}中的值;

@RequestMapping(value="/users/{userId}/create")
	这样也是可以的,请求的URL可以是"/users/123/create";
			@RequestMapping(value="/users/{userId}/topics/{topicId}")
	这样也是可以的,请求的URL可以是"/users/123/topics/123"
     
3)Ant风格的URL路径映射
@RequestMapping(value="/users/**")
	可以匹配"/users/abc/abc",但"/users/123"将会被【URI模板模式映射中的"/users/{userId}"模式优先映射到】;

@RequestMapping(value="/product/?")
	可匹配"/product/1"或"/product/a",但不匹配"/product"或"/product/aa",?代表有且只有一个字符;

@RequestMapping(value="/product*")
	可匹配"/productabc"或"/product",但不匹配"/productabc/abc",*代表0~n个字符;

@RequestMapping(value="/product/*")
	可匹配"/product/abc",但不匹配"/productabc";

@RequestMapping(value="/products/**/{productId}")
	可匹配"/products/abc/abc/123"或"/products/123",也就是Ant风格和URI模板变量风格可混用,**代表所有的子路径.

4)正则表达式风格的URL路径映射
从Spring3.0 开始支持正则表达式风格的URL路径映射,格式为{变量名:正则表达式},之后通过@PathVariable可以提取{XXX:正则表达式匹配的值}中的XXX这个变量的值。

@RequestMapping(value="/products/{categoryCode:\\d+}-{pageNumber:\\d+}")
	可以匹配"/products/123-1",但不能匹配"/products/abc-1",这样可以设计更加严格的规则;

@RequestMapping(value="/user/{userId:^\\d{4}-[a-z]{2}$}")
	可以匹配"/user/1234-ab";
		
注意:\d表示数字,但是\在java的字符串中是特殊字符,所以需要再加一个\进行转义即可;
	
	括号:
		[abc] 	查找方括号之间的任何字符；
        [^abc] 	查找任何不在方括号之间的字符；
        [0-9] 	查找任何从 0 至 9 的数字；
        [a-z] 	查找任何从小写 a 到小写 z 的字符；
        [A-Z] 	查找任何从大写 A 到大写 Z 的字符；
        [A-z] 	查找任何从大写 A 到小写 z 的字符；
				
			
	元字符:
		. 查找单个任意字符,除了换行和行结束符.如果要表示.这个字符,需要转义
		\w 	查找单词字符；     字母 数字 _
		\W 	查找非单词字符；   非字母 数字 _
		\d 	查找数字；
		\D 	查找非数字字符；
				

	量词:
		n+ 		匹配任何包含至少一个 n 的字符串；
		n* 		匹配任何包含零个或多个 n 的字符串；
		n? 		匹配任何包含零个或一个 n 的字符串；
		n{X} 	匹配包含 X 个 n 的序列的字符串；
		n{X,Y} 	匹配包含 X 到 Y 个 n 的序列的字符串；
		n{X,} 	匹配包含至少 X 个 n 的序列的字符串；
		n$ 		匹配任何结尾为 n 的字符串；
		^n 		匹配任何开头为 n 的字符串；


正则表达式风格的URL路径映射是一种特殊的URI模板模式映射；
URI模板模式映射不能指定模板变量的数据类型,如是数字还是字符串；
正则表达式风格的URL路径映射,可以指定模板变量的数据类型,可以将规则写的相当复杂.

	匹配的内容包含一个数字
	@RequestMapping("/regex/{userid:\\d}")
	匹配的内容包含至少一个数字
	@RequestMapping("/regex/{userid:\\d+}”)
	匹配的内容包含3个数字
	@RequestMapping("/regex/{userid:\\d{3}}")
	匹配的内容是开始是3个数字，中间随意的字符，最后结尾1-9中的任意两个数字
	@RequestMapping("/regex/{userid:^\\d{3}.*[1-9]{2}$}")
	匹配内容是3～5个数字
	@RequestMapping("/regex/{userid:\\d{3,5}}")
```

