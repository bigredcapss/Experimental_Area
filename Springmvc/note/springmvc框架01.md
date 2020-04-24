###### 1.MVC与三层架构

这里不多解释，附上链接：参考1[MVC与三层架构](https://juejin.im/post/5929259b44d90400642194f3)参考2[MVC与三层架构](https://www.cnblogs.com/zdxster/p/5305187.html)

###### 2.SpringMVC概述

```Java
1）SpringMVC简述：
SpringMVC就是Spring框架提供的一个模块,通过实现MVC模式来很好地将数据、业务与展现进行分离,SpringMVC框架的目的是要简化我们日常的Web开发；

SpringMVC框架跟其他的WebMVC框架一样,都是请求驱动,并且设计围绕一个能够分发请求到控制器以及提供其他加快web应用开发功能的核心
Servlet(叫做DispatcherServlet,即前端控制器)；

SpringMVC的DispatcherServlet实现比其他框架中还要多的功能,它和spring的ioc容器完全整合,并且允许使用spring中其他的所有功能;

SpringMVC框架设计的一个核心的原则就是"开闭原则",对扩展开放,对修改关闭.所以SpringMVC框架中很多方法都是final的,不允许用户随意覆盖,但是却提供给用户很多可扩展的机制。SpringMVC目前已经成为非常流行的web应用的框架;

2）SpringMVC框架的获取
由于SpringMVC是Spring框架中的一个模块,所以我们下载Spring框架即可,因为里面包含了Spring框架的各个模块的相关东西,当然也包含了SpringMVC的.(jar包、API文档、源代码).

spring-aop-5.0.10.RELEASE.jar
spring-aspects-5.0.10.RELEASE.jar
spring-beans-5.0.10.RELEASE.jar
spring-context-5.0.10.RELEASE.jar
spring-context-support-5.0.10.RELEASE.jar
spring-core-5.0.10.RELEASE.jar
spring-expression-5.0.10.RELEASE.jar
spring-instrument-5.0.10.RELEASE.jar
spring-instrument-tomcat-5.0.10.RELEASE.jar
spring-jdbc-5.0.10.RELEASE.jar
spring-jms-5.0.10.RELEASE.jar
spring-orm-5.0.10.RELEASE.jar
spring-oxm-5.0.10.RELEASE.jar
spring-struts-5.0.10.RELEASE.jar
spring-test-5.0.10.RELEASE.jar
spring-tx-5.0.10.RELEASE.jar
spring-web-5.0.10.RELEASE.jar
spring-webmvc-5.0.10.RELEASE.jar
spring-webmvc-portlet-5.0.10.RELEASE.jar

3)SpringMVC框架的核心组件

I:DispatcherServlet: 前端控制器.用来过滤客户端发送过来,想要进行逻辑处理的请求.

II:Controller/Headler: 控制器/处理器.开发人员自定义,用来处理用户请求的,并且处理完成之后返回给用户指定视图的对象.

III:HandlerMapping: 处理器映射器.DispatcherServlet接收到客户端请求的URL之后,根据一定的匹配规则,再把请求转发给对应的
Handler,这个匹配规则由HandlerMapping决定.
		
VI:HandlerAdaptor:处理器适配器.处理器适配器用来适配每一个要执行的Handler对象.通过HandlerAdapter可以支持任意的类作为处理器.
		
V:ViewResolver:视图解析器.Handler返回的是逻辑视图名,需要有一个解析器能够将逻辑视图名转换成实际的物理视图.而且Spring的可扩展性决定了视图可以由很多种,所以需要不同的视图解析器,解析不同的视图，例如jsp,thymeleaf等.

IV:SpringMVC的工作流程：
SpringMVC框架提供一个核心的Servlet对象(DispatcherServlet,前端控制器)来对服务器接收到的请求进行解析,当这个请求被DispatcherServlet获取到之后,DispatherServlet需要根据HandlerMapping对象的映射关系,将这个请求转交给真正能够处理客户端请求的Controller控制器(相当于之前的servlet)来处理.
Controller处理完成后返回ModelAndView对象,也就是模型和视图的结合体.
ViewResolver视图解析器根据ModelAndView中的逻辑视图名找到真正的物理视图,同时使用ModelAndView中模型里面的数据对视图进行渲染.最后把准备好的视图展现给用户
```

###### 3.SpringMVC框架在项目中的搭建

```xml
第一步：构建Web项目
第二步：导入所需jar包
第三步：配置前端控制器DispatcherServlet

SpringMVC的核心控制器就是一个Servlet对象,继承自HttpServlet,所以需要在web.xml文件中配置.

SpringMVC是Spring提供的一个模块,Spring所有的模块都是基于Spring IOC功能的,所以SpringMVC的DispatcherServlet对象在初始化之前会去实例化Spring的容器对象(ApplicationContext),那么就需要读取Spring的配置文件.
    
默认SpringMVC会在你web应用的WEB-INF目录下查找一个名字为[servlet-name]-servlet.xml文件,并且创建在这个文件中定义的bean对象.

e.g:
 <servlet>
	<servlet-name>SpringMVC</servlet-name>
	<servlet-class>     org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>SpringMVC</servlet-name>
	<url-pattern>*.action</url-pattern>
</servlet-mapping>

如上配置,框架会自动去当前应用的WEB-INF目录下查找名字为SpringMVC-servlet.xml文件(默认前缀和<servlet-name>标签中的值一致).
        		
如果你提供的spring配置文件的名字或者位置和默认的不同,那么需要在配置servlet时同时指定配置文件的位置.

e.g
<servlet>
	<servlet-name>SpringMVC</servlet-name>
	<servlet-class>    org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
	<init-param>
		<param-name>
            contextConfigLocation
        </param-name>
		<param-value>
            classpath:spring-web-mvc.xml
        </param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>SpringMVC</servlet-name>
	<url-pattern>*.action</url-pattern>
</servlet-mapping>

注意:
	配置文件在WEB-INF下:
	<param-value>
        /WEB-INF/spring-web-mvc.xml
    </param-value>
	配置文件在classpath（src）下:
	<param-value>
        classpath:spring-web-mvc.xml
    </param-value>

注意:
	<url-pattern>*.action</url-pattern>
	也可以配置成(注意这里是/ 不是/*)
	<url-pattern>/</url-pattern>

    
第四步：编写Controller控制器(也称为Handler处理器)
    
Controller控制器,主要负责功能处理部分：
		①、收集、验证请求参数并封装到对象上；
		②、将对象交给业务层,由业务对象处理并返回模型数据；
		③、返回ModelAndView(Model部分是业务层返回的模型数据,视图部分为逻辑视图名).
		 
DispatcherServlet(前端控制器)主要负责整体的控制流程的调度部分：
		①、负责将请求委托给控制器进行处理；
		②、根据控制器返回的逻辑视图名选择具体的视图进行渲染(并把模型数据传入).
因此MVC中完整的C(包含控制逻辑+功能处理)由(DispatcherServlet + Controller)组成.
		
Controller接口中只有一个需要实现的方法就是handleRequest方法,方法中接收两个参数,分别对应Servlet对象中request,response对象.可以从request中获取客户端提交过来的请求参数.返回值ModelAndView,既包含要返回给客户端浏览器的逻辑视图又包含要对视图进行渲染的数据模型.
 
e.g:
public class HelloWorldController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String name = request.getParameter("name");
	//ModelAndView对象中包括了要返回的逻辑视图,以及数据模型
    ModelAndView mv = new ModelAndView();
    //设置逻辑视图名称
    mv.setViewName("hello");
    //设置数据模型
    mv.addObject("name", name);
	return mv;
	}
}
    
第五步：配置处理器映射器(可省去,有默认配置)
注意:使用Eclipse时，如果xml文件不能自动提示,那么可以在Eclipse中把schame配置过来即可,schame文件也在下载的spring的压缩包中，Spring容器需要根据映射器来将用户提交的请求url和后台Controller/Handler进行绑定,所以需要配置映射器.

e.g:
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>

BeanNameUrlHandlerMapping：表示将请求的URL和Bean名字映射,如URL为 "/hello",则Spring配置文件必须有一个名为"/hello"的Bean.
注意:这里/代表的含义是url中项目名后面的/
    

第六步：配置处理器适配器(可省去,有默认配置)
想要正确运行自定义处理器,需要配置处理器适配器,在spring的配置文件SpringMVC-servlet.xml中,进行如下配置：
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>

SimpleControllerHandlerAdapter：表示所有实现了org.springframework.web.servlet.mvc.Controller接口的Bean都可以作为SpringMVC中的处理器，如果需要其他类型的处理器可以通过实现HadlerAdapter接口来解决.
    
    
第七步：配置视图解析器(可省去,有默认配置,但是前缀和后缀都为"")
当处理器执行完成后,返回给spring容器一个ModelAndView对象,这个对象需要能够被解析成与之相对应的视图,并且使用返回的Model数据对视图进行渲染.

e.g:
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
	<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>  
	<property name="prefix" value="/WEB-INF/jsp/"/>  
	<property name="suffix" value=".jsp"/>  
</bean>  

如果配置设置为如上操作,那么在自定义的Handler中返回的视图的名字不能有后缀.jsp,并且页面一定放在/WEB-INF目录下;
		
InternalResourceViewResolver：用于支持Servlet、JSP视图解析；
viewClass：JstlView表示JSP模板页面需要使用JSTL标签库,classpath中必须包含jstl的相关jar包；
prefix和suffix：视图页面的前缀和后缀(前缀+逻辑视图名+后缀),比如传进来的逻辑视图名为hello,则该该jsp视图页面应该存放在"WEB-INF/jsp/hello.jsp";
		
注意1:放在WEB-INF下的页面,只能通过内部跳转的方式访问到,因为客户端访问不到WEB-INF目录,而服务器端可以访问到WEB-INF目录;
注意2:需要引入jstl相关jar包
注意3:页面中的路径问题
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<base href="<%=basePath%>" />    
   
    
第八步：配置处理器
将编码后的handler/controller在spring中进行配置,让其接受Spring IoC容器管理
<bean name="/hello.action" class="web.controller.HelloWorldController"/>  

注意: 对于Spring配置文件中的处理器适配器,处理器映射器,都可以省去不写,springMVC框架中有默认的配置,视图解析器也可以不配置因为org.springframework.web.servlet.DispatcherServlet这个类的同包下,有一个DispatcherServlet.properties文件,里面就是SpringMVC默认的配置,当用户的Spring配置文件中没有指定配置时使用的默认策略(若没有配置那么使用默认的；若配置了,那么就使用你的配置)；

从默认的配置中可以看出DispatcherServlet在启动时会自动注册这些特殊的Bean,无需我们注册,如果我们注册了,默认的将不会注册；因此之前的BeanNameUrlHandlerMapping、SimpleControllerHandlerAdapter是不需要注册的,DispatcherServlet默认会注册这两个Bean;
	

整个访问的流程:
	1、首先用户发送请求,前端控制器DispatcherServlet收到请求后自己不进行处理，而是委托给其他的解析器进行处理，前端控制器作为统一访问点，进行全局的流程控制；
	2、DispatcherServlet把请求转交给HandlerMapping， HandlerMapping将会把请求映射为HandlerExecutionChain对象(包含一个Handler处理器对象、多个HandlerInterceptor拦截器)对象;
	3、DispatcherServlet再把请求转交给HandlerAdapter，HandlerAdapter将会把处理器包装为适配器，从而支持多种类型的处理器(适配器模式).简单点说就是让我们知道接下来应该调用Handler处理器里面的什么方法;
	4、HandlerAdapter将会根据适配的结果调用真正的处理器的功能处理方法，完成功能处理；并返回一个ModelAndView对象（包含模型数据、逻辑视图名）；
	5、ModelAndView的逻辑视图名交给ViewResolver解析器， ViewResolver解析器把逻辑视图名解析为具体的View，通过这种策略模式，很容易更换其他视图技术；
	6、View会根据传进来的Model模型数据进行渲染，此处的Model实际是一个Map数据结构，因此很容易支持其他视图技术；
	7、最后返回到DispatcherServlet，由DispatcherServlet返回响应给用户，到此一个流程结束.  
```

###### 4.DispatcherServlet中的映射路径

```xml
<servlet>
	<servlet-name>SpringMVC</servlet-name>
	<servlet-class>        org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>SpringMVC</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>

1)拦截所有请求
此处需要特别强调的是 <url-pattern>/</url-pattern>使用的是/,而不是/*,如果使用/*,那么请求时可以通过DispatcherServlet转发到相应的Controller中,但是Controller返回的时候,如返回的jsp还会再次被拦截,这样导致404错误,即访问不到jsp；

2)自定义拦截请求
拦截*.do、*.html、*.action, 例如/user/add.do
这是最传统的方式,最简单也最实用。不会导致静态文（jpg,js,css）被拦截；

拦截/,例如：/user/add
可以实现REST风格的访问；
弊端：会导致静态文件（jpg,js,css）被拦截后不能正常显示；

拦截/*,这是一个错误的方式,请求可以走到Controller中,但跳转到jsp时再次被拦截,不能访问到jsp；

3)静态资源的访问,如jpg,js,css
如果DispatcherServlet拦截"*.do"这样的有后缀的URL,就不存在访问不到静态资源的问题。
如果DispatcherServlet拦截"/",为了实现REST风格,拦截了所有的请求,那么同时对*.js,*.jpg等静态文件的访问也就被拦截了.

e.g:
<link href="css/hello.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/hello.js"></script>
<img alt="none" src="images/logo.png">	

解决方式一：利用Tomcat的defaultServlet来处理静态文件
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.jpg</url-pattern>
    </servlet-mapping>

    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.js</url-pattern>
    </servlet-mapping>

    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.css</url-pattern>
    </servlet-mapping>
特点：1.要配置多个，每种文件配置一个；
     2.要写在DispatcherServlet的前面(和tomcat版本有关),让defaultServlet先拦截请求,这样请求就不会进入Spring了；
	 3. 高性能；

解决方式二: 使用<mvc:resources>标签,例如:
    <mvc:resources mapping="/images/**" location="/images/"/>  
    <mvc:resources mapping="/js/**" location="/js/"/> 
    <mvc:resources mapping="/css/**" location="/css/"/> 
		
mapping：映射.两个*,表示映射指定路径下所有的URL,包括子路径；
location：本地资源路径；
这样如果有访问/images或者/js或者/css路径下面的资源的时候,spring就不会拦截了；
  
解决方式三: 使用<mvc:default-servlet-handler/>标签
在spring配置文件中加入此标签配置即可 
```

###### 5.Spring提供的编码过滤器

```xml
查看这个过滤器类源码便可知这里所传的俩个参数的作用
	<filter>
		<filter-name>CharacterEncodingFilter</filter-name>
		<filter-class> org.springframework.web.filter.CharacterEncodingFilter
        </filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>CharacterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```

