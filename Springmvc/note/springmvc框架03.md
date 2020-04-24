###### 1.请求方法映射限定

```Java
一般获取数据为GET请求方法,提交表单一般为POST请求方法。但之前URL路径映射方式对任意请求方法都是接受的,因此我们需要某种方式来告诉相应的功能处理方法只处理如GET方式的请求或POST方式的请求.
	@RequestMapping(value="/user/{userId:\\d+}",method=RequestMethod.GET)
	可以匹配"/user/100",并且请求方式只能是GET；
	
@RequestMapping(value="/hello", method={RequestMethod.POST,RequestMethod.GET})
	可以匹配"/hello",并且请求方式只能是POST或者GET；
	
注意:
	1、一般浏览器只支持GET、POST请求方法,如想浏览器支持PUT、DELETE 等请求方法只能模拟。(jquery中的ajax函数可以发送这些方式的请求)；
	2、除了GET、POST,还有HEAD、OPTIONS、PUT、DELETE、TRACE等请求方式(观察servlet源码也可获知)；
	3、DispatcherServlet默认开启对GET、POST、PUT、DELETE、HEAD 的支持；
	4、如果需要支持OPTIONS、TRACE方式的请求,请添加DispatcherServlet 在web.xml 的初始化参数:dispatchOptionsRequest和dispatchTraceRequest为true.(查看源码,在DispatcherServlet的父类中可以找到这个俩个属性)
```

###### 2.请求参数映射限定

```Java
1)请求数据中有指定参数名
	@RequestMapping("/test")
	@Controller
	public class HomeController {
		@RequestMapping(params="create",method=RequestMethod.GET)
		public ModelAndView test1(){

			return null;
		}
		@RequestMapping(params="create",method=RequestMethod.POST)
		public ModelAndView test2(){

			return null;
		}
	}

	可以匹配的路径为:/test?create；
	如果是get 方式的请求则访问test1方法；
	如果是post方式的请求则访问test2方法；

2)请求数据中没有指定参数名
@RequestMapping(params="!create",method=RequestMethod.GET)

3)请求数据中指定参数名=值
@RequestMapping(params="username=tom")	

4)请求数据中指定参数名!=值
@RequestMapping(params="username!=tom")

5)组合使用是"且"的关系
@RequestMapping(params={"create","username=tom"})
```

###### 3.请求头数据映射限定

```Java
1)请求头数据中有指定参数名
@RequestMapping(value="/header/test1", headers="Accept")
	表示请求的URL必须为"/header/test1"且请求头中必须有Accept参数才能匹配;

@RequestMapping(value="/header/test1", headers="my_test")
	表示请求的URL必须为"/header/test1"且请求头中必须有my_test参数才能匹配;
	
2)请求头数据中没有指定参数名
@RequestMapping(value="/header/test2", headers="!abc")
	表示请求的URL必须为"/header/test2"且请求头中必须没有abc参数才能匹配;

3)请求头数据中指定参数名=值
@RequestMapping(value="/header/test3", headers="Content-Type=application/json")
	表示请求的URL必须为"/header/test3"且请求头中必须有"Content-Type=application/json"参数即可匹配;


4)请求头数据中指定参数名!=值
@RequestMapping(value="/header/test4", headers="Accept!=text/plain")
	表示请求的URL必须为"/header/test4"且请求头中必须有Accept参数,但是值不等于text/plain即可;

5)组合使用是"且"的关系
@RequestMapping(value="/header/test5", headers={"Accept!=text/plain", "abc=123"}):
	表示请求的URL必须为"/header/test5"且请求头中必须有"Accept"参数但值不等于"text/plain"且请求中必须有参数"abc=123"即可匹配;


6)consumes属性和produces属性
consumes 指定处理请求的提交内容类型;

@RequestMapping(value="/test",consumes="application/json") 
	方法仅处理请求中,Content-Type为"application/json"类型的请求;

produces 指定返回的内容类型;

@RequestMapping(value= "/test", produces="application/json")
	表示将功能处理方法将产生json格式的数据,此时根据请求头中的Accept进行匹配,如请求头"Accept:application/json"时即可匹配;

e.g:
	@Controller
	public class HomeController {
@RequestMapping(value="/test",consumes="application/json")
		public ModelAndView test1(){
			System.out.println("test1....");
			return null;
		}
		@RequestMapping(value="/test",produces="application/json")
		public ModelAndView test2(){
			System.out.println("test2....");
			return null;
		}
	}
	
测试类中的main函数代码:
	此处我们使用Spring提供的Http客户端API创建了请求并设置了请求的Content-Type和编码并在响应体中写回了json数据
	public static void main(String[] args) {
		try {
			String url = "http://127.0.0.1:8989/XXX/XXX";
			//创建HttpRequest
			ClientHttpRequest request =
			new SimpleClientHttpRequestFactory().
			createRequest(new URI(url), HttpMethod.POST);
			//设置请求头C内容类型头和内容编码
			//request.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
			request.getHeaders().set("Accept", "application/json;charset=UTF-8");
			//写出请求内容体
			String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
			request.dy().write(jsonData.getBytes("UTF-8"));
			//发送请求并得到响应
			ClientHttpResponse response = request.execute();
			System.out.println(response.getStatusCode());
			System.out.println(response.getHeaders());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//注意:在上面代码中,如果想获得响应正文中的数据,可以编写一下代码:
	//得到响应体的编码方式
	Charset charset = response.getHeaders().getContentType().getCharSet();
	//得到响应体的内容
	InputStream is = response.getBody();
	byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
	is.read(bytes);
	String data = new String(bytes, charset);
	System.out.println("charset : " + charset + ", data : " + data);
```

###### 4.数据绑定

```Java
1)功能处理方法支持的参数类型
	注意下面这些参数都可以在功能处理方法中直接声明并且没有指定顺序,spring会自动注入的;

	第一种
		ServletRequest/HttpServletRequest 和 ServletResponse/HttpServletResponse
		SpringWebMVC框架会自动帮助我们把相应的Servlet请求/响应作为参数传递过来;
		
	第二种
		InputStream/OutputStream 和 Reader/Writer
		分别对应的是request.getInputStream();
				  response.getOutputStream();
				  request.getReader();
				  response.getWriter();
		InputStream/OutputStream 和 Reader/Writer两组不能同时使用,只能使用其中的一组;
		
	注意:
	//代码如下,访问的时候会报500错误,因为使用输出流,就不能再发回视图了,因为使用输出流那么就是使用了response,使用了response就是要自己处理返回给浏览器的内容,那么也就不能再让Controller返回视图了
		@RequestMapping("/test")
		public String test(InputStream in,OutputStream out){
			System.out.println(in);
			System.out.println(out);
			
			return "test";
		}

	第三种
		WebRequest/NativeWebRequest
		WebRequest是SpringMVC提供的统一请求访问接口,不仅仅可以访问请求相关数据(如参数区数据、请求头数据,但访问不到Cookie区数据),还可以访问会话和上下文中的数据；NativeWebRequest继承了WebRequest,并提供访问本地ServletAPI的方法;
		e.g:
		public String webRequest(WebRequest webRequest, NativeWebRequest nativeWebRequest) {  
			System.out.println(webRequest.getParameter("test"));
			webRequest.setAttribute("name", "value",WebRequest.SCOPE_REQUEST);  
			System.out.println(webRequest.getAttribute("name", WebRequest.SCOPE_REQUEST));  
			
HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

HttpServletResponse response =   		nativeWebRequest.getNativeResponse(HttpServletResponse.class);  
				return "success";  
		} 

		webRequest.getParameter():访问请求参数区的数据;				webRequest.getHeader():访问请求头数据;
		webRequest.setAttribute/getAttribute:到指定的作用范围内取/放属性数据,Servlet定义的三个作用范围分别使用如下常量代表:
            SCOPE_REQUEST :代表请求作用范围；
            SCOPE_SESSION :代表会话作用范围；
            SCOPE_GLOBAL_SESSION :代表全局会话作用范围,即ServletContext上下文作用范围；
		
		得到本地的Servlet API：
		nativeWebRequest.getNativeRequest
		nativeWebRequest.getNativeResponse


	第四种
		HttpSession：
		public String session(HttpSession session) {  
			System.out.println(session);  
			return "success";  
		}  
		注意:session访问不是线程安全的,如果需要线程安全,需要设置AnnotationMethodHandlerAdapter或RequestMappingHandlerAdapter的synchronizeOnSession属性为true,即可线程安全的访问session；
		在spring2.5中是AnnotationMethodHandlerAdapter；
		在spring3中是RequestMappingHandlerAdapter.


	第五种
		命令/表单对象(自定义对象，例如User)
		SpringMVC能够自动将请求参数绑定到功能处理方法的命令/表单对象上；
		这里说的命令/表单对象并不需要实现任何接口，仅是一个拥有若干属性的POJO类对象；

		e.g:
		@RequestMapping(value = "/create")
		public String toCreateUser(HttpServletRequest request,User user) {  
			return null;  
		} 
		如果提交的表单(包含username和password文本域),将自动将请求参数绑定到命令对象user中去。


	第六种
		Model、Map、ModelMap:
		SpringMVC提供Model、Map或ModelMap让我们能去封装/处理模型数据;
		e.g:
		@RequestMapping(value="/model")  
		public String test(Model m1, Map m2, ModelMap m3)         {  
			m1.addAttribute("a", "a");  
			m2.put("b", "b");  
			m3.put("c", "c");  
			System.out.println(m1 == m2);  
			System.out.println(m2 == m3);  
			return "success";
		}  
		虽然此处注入的是三个不同的类型(Model model, Map model2, ModelMap model3),但三者是同一个对象;


	第七种
		HttpEntity<T>和ResponseEntity<T>

		HttpEntity的使用,e.g:
		@RequestMapping(value="test")
		public String test(HttpEntity<String> httpEntity){
			//获得请求中的所有的header部分
			HttpHeaders headers = httpEntity.getHeaders();
			//获得请求中的所有的body部分
			String body = httpEntity.getBody();             
		}

		ResponseEntity的使用,可以自定义响应的各个部分,e.g:
		@RequestMapping("/test")   
		public ResponseEntity<String> test(){  
			//创建响应头对象
			HttpHeaders headers = new HttpHeaders();

			//创建MediaType对象
			MediaType mt = new MediaType("text","html",Charset.forName("UTF-8")); 
			//设置ContentType
			headers.setContentType(mt);  

			//准备好相应体
			String content = new String("hello world");

			//根据响应内容/响应头信息/响应状态码创建响应对象
			ResponseEntity<String> re = new ResponseEntity<String>(content,headers,HttpStatus.OK);
			
			//返回ResponseEntity对象
			return re;
		}


	第八种
		SessionStatus中的setComplete()方法可以用来
清除使用@SessionAttributes注解放到session中的数据,下面注解的例子中会说明这个问题;
		@RequestMapping("/test")
		public String test(SessionStatus status){}


	第九种
		RedirectAttributes
		问题描述:
		当前一个表单提交(post)给Controller之后,Controller处理完带着数据(msg="添加成功")进行服务器内部跳转到一个成功页面,页面里使用EL表达式拿出msg中的值并显示出来,这个时候用户按F5刷新,那么表单数据会再被提交一次;
		如果上述过程中不是使用服务器内部跳转而是使用客户端重定向,那么用户在成功页面按F5刷新就不能重新提交表单了,但是由于使用了重定向,那么之前我们放在request范围的数据就拿不到了;

		RedirectAttributes可以帮我们解决这个问题,既要使用客户端重定向,又要把一些有用的数据保留到下一次重定向的请求之中;
		
		e.g:
@RequestMapping(value="/user/add",method=RequestMethod.POST)
		public String addUser(User user,RedirectAttributes redirectAttributes){
			System.out.println("user = "+user);
			redirectAttributes.addFlashAttribute("msg", "添加用户成功");

			//这里不能使用servlet重定向的方式
			//要使用springMVC提供的重定向方式
			//response.sendRedirect()
			return "redirect:/user/add/success";
		}
		@RequestMapping("/user/add/success")
		public String index(){
			return "success";
		}
		
		客户端重定向之后,用户可以在添加成功页面看到提示信息,但是用户按F5刷新之后,提示信息就没有了,变量msg只是被保存到了一下次重定向的请求之中。页面中使用${requestScope.msg }即可获得数据,当然也可以${msg}

	第十种 
		BindingResult:
		BindingResult对象里面可以保存SpringMVC数据校验中的错误信息,要结合数据校验功能来使用;

	其他
		除了以上几种类型参数之外,还支持一些其他的类型,如:Errors、Locale、Principal、UriComponentsBuilder等;
```

```Java
2)@RequestParam绑定单个请求参数值
	用于将请求参数区数据映射到功能处理方法的参数上
	public String test(@RequestParam String username)
	请求中包含username参数(如/test?username=tom),则自动传入；

	如果url为/test?name=tom,即俩边参数名不一致,那么就需要手动指定
	public String test(@RequestParam("name") String username)
	
@RequestParam注解中主要有哪些参数
		value:参数名字,即入参的请求参数名字
		required:是否必须,默认是true
		defaultValue:默认值,表示如果请求中对应参数时则采用此默认值,默认值可以是spring中的SpEL 表达式,如
		"#{systemProperties['java.vm.version']}"。
	
注意:systemProperties是spring自动方法ioc容器中的一个Properties对象,里面方法中很多系统变量,要取值只需#{systemProperties['key']}即可
@RequestMapping(value="/home2")
public String test(@RequestParam(defaultValue="#{systemProperties}") Object p){
	System.out.println("p = "+p);
	System.out.println("p = "+p.getClass());
	System.out.println(((Properties)p).get("user.home"));
		return "index";
	}
	
SpEL表达式的使用,例如:
	取名字为stu的bean的name字段的值,这里指的是property
	public String test(@RequestParam(defaultValue="#{stu.name}") String username)

	对其它ean中某个方法的引用
	public String test(@RequestParam(defaultValue="#{stu.sayHello()}") String username)
	public String test(@RequestParam(defaultValue="#{stu.sayHello('tom')}") String username)

	表达式(?.)可以确保在sayHello()返回不为空的情况下调用toUpperCase()方法,如果返回空那么不继续调用后面的方法
	public String test(@RequestParam(defaultValue="#{stu.sayHello()?.toUpperCase()}") String username)

	如果要调用的某个类是外部类,而不是spring中定义的bean,使用表达式T()
	public String test(@RequestParam(defaultValue="#{T(java.lang.Math).random()}") String username)
	

3)@PathVariable绑定URI模板变量值
	用于将请求URL中的模板变量映射到功能处理方法的参数上。
	@RequestMapping(value="/users/{userId}/topics/{topicId}")
		public String test(@PathVariable int userId,
		@PathVariable int topicId){
		
			return "index";
		}

	如请求的 URL为"/users/123/topics/456",则自动将URL 中模板变量{userId}和{topicId}绑定到通过@PathVariable注解的同名参数上,即入参后userId=123、topicId=456.
	如果参数不同名,则需要自己手动指定
		public String test(@PathVariable("uid") int userId,
		@PathVariable("tid") int topicId){
			return "index";
		}

4)@CookieValue绑定Cookie数据值
	用于将请求的Cookie数据映射到功能处理方法的参数上;
		public String test(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId)
	如上配置将自动将JSESSIONID 值入参到sessionId 参数上,defaultValue 表示Cookie中没有JSESSIONID时默认为空;
		
	传入参数类型也可以是javax.servlet.http.Cookie类型
		public String test(@CookieValue(value="JSESSIONID", defaultValue="") Cookie cookie)

	
5)@RequestHeader 绑定请求头数据
	用于将请求的头信息区数据映射到功能处理方法的参数上
		@RequestMapping(value="/header")
		public String test(@RequestHeader("User-Agent") String userAgent,
		@RequestHeader(value="Accept") String[] accepts)
		
	如上配置将自动将请求头"User-Agent"的值,入参到userAgent参数上,并将"Accept"请求头值入参到accepts参数上;
```

```Java
6)@ModelAttribute 绑定请求参数到命令/表单对象
该注解具有如下三个作用:

	1.绑定请求参数到命令对象,同时将对象存放到模型中(可以指定存放的名字)
	例如在用户登录时,我们需要捕获用户登录的请求参数(用户名、密码)并封装为用户对象,此时我们可以使用@ModelAttribute绑定多个请求参数到我们的命令对象；
	public String test(@ModelAttribute("my_user") User u)
			
	和上面接收到的"第五种"情况一样,只是此处多了一个注解@ModelAttribute("my_user"),它的作用是将该绑定的命令对象以"my_user"为名称；
	添加到模型对象中供视图页面展示使用.我们此时可以在视图页面使用${my_user.username}来获取绑定的命令对象的属性；
	如果不写@ModelAttribute("my_user")这个注解,那么默认添加到模型中的名字是该类型的类名首字母小写,例如这里便是user,页面中取值就需要这样${user.username};


	2.在功能处理方法执行前,向模型中添加数据
	@ModelAttribute("cityList")
	public List<String> cityList(String username) {
		return Arrays.asList("北京", "山东");
	}
	如果当前模型中没有名字为cityList的数据时,如上代码会在执行处理器中任意功能处理方法之前执行,并将返回值自动添加到模型对象中,在功能处理方法中调用Model对象中的方法containsAttribute("cityList")将会返回true;
			
	注意:模型中数据的作用范围是request级别的;
	注意:此方法中依然是可以接收本次请求的参数的,默认和客户端所传参数名字保持一致,也可以使用@RequestParam指定参数名;
			
	注意:如何有俩个同名的命令对象,如下
	@ModelAttribute("user")
	public User getUser(String username) {
		User user = new User();
		user.setUsername("briup");
		return user;
	}
			
	@RequestMapping(value="/model")
	public String test1(@ModelAttribute("user") User user, Model model){
		//输出结果为briup
		System.out.println(user.getUsername());

		//返回值是true
		System.out.println(user == model.asMap().get("user"));
		return "index";
	}
			
	说明springMVC对于模型中重名的对象,不会重复创建,默认模型中已经有了这个名字的对象,那么就直接拿出来使用;
		
	3.把功能处理方法的返回值添加到模型数据中
	@RequestMapping(value="/index")
	public @ModelAttribute("u") User test3(){
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		return user;
	}
			
	注意:这时候SpringMVC会根据RequestToViewNameTranslator进行逻辑视图名的翻译,这个例子中也就会把"index"作为逻辑视图名进行解析;
		
	注意:对于集合类型(Collection接口的实现者们,包括数组),生成的模型对象属性名为"简单类名(首字母小写)"+"List",如List<String>生成的模型对象属性名"stringList",List<User>生成的模型对象属性名为"userList";
	e.g:
	public @ModelAttribute List<String> test(){}
	public @ModelAttribute List<User> test(){}
```

```Java
7)@SessionAttributes绑定命令对象到session
@SessionAttributes(String[] value,Class[] type)
@SessionAttributes(value={},types={})
		
@SessionAttributes(value={"user"})
    写在处理器类上面，表示将模型数据中名字为"user" 的对象存储到会话中，此处value指定将模型数据中的哪些数据（名字进行匹配）存储到会话中，此外还有一个types属性表示模型数据中的哪些类型的对象存储到会话范围内，如果同时指定value和types属性则那些名字和类型都匹配的对象才能存储到会话范围内；

	注意,模型数据的作用范围是request级别的,所以一次请求过后,之前模型中的数据就没有了,@SessionAttributes注解可以将当前模型中指定的数据存放到session中,并且还可以从session中把指定数据取出来返回模型中；
		
	1.如果模型里有名字为user的数据,并且使用了@SessionAttributes("user"),那么这个模型中的数据user会被放到session中；
	2.如果要从模型中拿名字为user的数据,模型中没有,这个时候就拿不到了,但是这个时候使用了@SessionAttributes("user"),那么它会帮我们把数据从session取出来放到模型中；
	所以处理器类的上面有没有加@SessionAttributes("user")注解,会影响到我们使用下面方式是否能拿到值.
		@RequestMapping("/session")
		public String session(User u) {
			System.out.println(u);
			return "index";
		}
		默认只是从模型中拿名字叫user的值,如果加了@SessionAttributes("user")这个注解,还可以拿到session中的user对象
		
		也可以指定用哪个名字拿值,例如:
		@RequestMapping("/session")
		public String session(@ModelAttribute("my_user") User u) {
			System.out.println(u);
			return "index";
		}

		也可以使用SessionStatus对象的方法把@SessionAttributes指定的数据从session中清除掉
		@RequestMapping("/session")
		public String session(User u,SessionStatus status) {
			if(true){
				//从session中清除注解中指定的数据
				status.setComplete();
			}
			System.out.println(u);
			return "index";
		}
		
		
8)@Value绑定SpEL表示式
	用于将一个SpEL表达式结果映射到到功能处理方法的参数上。
	public String test(@Value("#{systemProperties['java.vm.version']}") String jvmVersion){
		System.out.println(jvmVersion);
		return "index";
}
		
	SpEL表达式的使用,例如:
	取名字为stu的bean的name字段的值,这里指的是property
	public String test(@Value("#{stu.name}") String username){}

	对其他bean中某个方法的引用
	public String test(@Value("#{stu.sayHello()}") String username){}
	public String test(@Value("#{stu.sayHello('tom')}") String username){}

	表达式(?.)可以确保在sayHello()返回不为空的情况下调用toUpperCase()方法,如果返回空那么不继续调用后面的方法
	public String test(@Value("#{stu.sayHello()?.toUpperCase()}") String username){}

	如果要调用的某个类是外部类,而不是spring中定义的bean,使用表达式T()
	public String test(@Value("#{T(java.lang.Math).random()}") String username)


9)@InitBinder注解
	可以解决类型转换的问题

	例如一个表单提交数据给Controller,表单中有日期数据
	@Controller
	public class InitBinderController{			@RequestMapping(value="/register",method=RequestMethod.GET)
		public String registerPage(){
		return "register";
		}
			
			@RequestMapping(value="/register",method=RequestMethod.POST)
		public String register(User user){
				System.out.println("user = "+user);
				return "index";
        }
}
		
	表单提交的时候有一个字符串形式的日期数据"1999-10-23",SpringMVC默认不支持这个格式的转换,所以需要手动配置日期类型的转换,否则会报错;

	在这个Controller中加入写一个转换的方法,加上@InitBinder即可
	@Controller
	public class InitBinderController{	
		@InitBinder
		public void test(WebDataBinder binder){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			//true表示允许为空
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
		}
			@RequestMapping(value="/register",method=RequestMethod.GET)
		public String registerPage(){
				return "register";
		}
					@RequestMapping(value="/register",method=RequestMethod.POST)
		public String register(User user){
			System.out.println("user = "+user);
			return "index";
		}
}



	在Spring3中引入了一个Converter接口，它支持从一个任意类型转为另一个任意类型
	e.g:
	自己编写的转换器代码:
	public class StringToDateConverter implements Converter<String, Date>{
		private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		@Override
		public Date convert(String str) {
			Date date = null;
			try {
					if(str!=null&&!"".equals(str.trim())){
						date = dateFormat.parse(str);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			return date;
		}
	}

spring的配置文件:例如spring的一个工厂类,产生一个转换服务,同时把我们自己的转换器注入进去,可以有多个
<bean name="formatService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<set>
<bean class="web.converter.StringToDateConverter"></bean>
			</set>
		</property>
</bean>
//在mvc标签中指定这个转换服务器
<mvc:annotation-driven conversion-service="formatService"/>
		

还有另外一种解决日期的方式,就是利用spring提供的一个专门针对日期转换的注解:@DateTimeFormat(pattern="yyyy-MM-dd")
	e.g: 
	public class User {
		private String username;
		private String password;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date dob;
			
		省略getter/setter方法
	}
注意这个时候就不需要我们再写自定义的转换器了,但是如果是其他类型的转换,我们还得需要自己编写自定义的类型转换器
```

```Java
10)@RequestBody注解
	可以接收客户端ajax请求中的json数据并且转换为对象,但是只能接收post请求中的数据,因为post请求的数据在请求体中(request-body).
	需要引入俩个json相关jar包:
		jackson-mapper-asl-1.9.13.jar
		jackson-core-asl-1.9.13.jar

	注意:在javascript中,json对象和字符串之间的转换:
		JSON.stringify(obj)将JSON转为字符串；
		JSON.parse(string) 将字符串转为JSON格式；
		
	 e.g:处理器中代码:	@RequestMapping(value="/json/update",consumes="application/json",method=RequestMethod.POST)
		public void update(@RequestBody User user,Writer out)throws Exception{
			System.out.println("user = "+user);
			out.write("helloworld");
		}
			
	页面js中代码:
			注意:http中的Content-Type,在jquery中是contentType
			$("#btn").on("click",function(){
				var json = {username:"tom",password:"123",dob:"1999-10-27"};
				$.ajax({
					type:"post",
					url:"json/update",
					contentType:"application/json",
					data:JSON.stringify(json),
					dataType:"text",
					success:function(data){
						console.log("data = "+data);
					}
				});
			});

	客户端使用ajax发送json数据给Controller,Controller里面接收json数据并且转换为对象
		1.ajax请求发送的时候要指定为post方式;
		2.ajax请求发送的时候要指定contentType:"application/json";
		3.ajax请求发送的时候要把json对象转换为字符串再发送;
		4.Controller中要使用@RequestBody指定接收数据的参数;
		5.项目中要引入俩个json相关的jar包;
		6.如果此ajax请求还希望Controller返回的数据也是json格式的,那么需要在发送ajax请求的时候指定dataType:"json";
		7.Controller中的方法要返回json格式的数据给客户端,可以使用@ResponseBody标签 或者 在方法中自己使用response对象获得io流给客户端写回去;
			
注意:
	ajax发送请求的时候,请求头中的Content-Type默认值是: application/x-www-form-urlencoded,表示当前请求中如果有数据的话,将会是key=value&key=value的形式;


11)@ResponseBody注解
	该注解用于将处理器中功能处理方法返回的对象，经过转换为指定格式后，写入到Response对象的body数据区(响应正文).一般返回的数据不是html标签的页面，而是其它某种格式的数据时使用,例如给ajax请求返回json数据.
	e.g:在@RequestBody的例子中进行修改处理器中代码:添加了@ResponseBody,修改了方法的返回值
					   @RequestMapping(value="/update",consumes="application/json",method=RequestMethod.POST)
@ResponseBody
public User update(@RequestBody User user)throws Exception{
		System.out.println("user = "+user);
		user.setUsername("张三");
		user.setPassword("123");
		user.setDob(new Date());
		return user;
}
			
页面js中代码: 注意这里的dataType属性的值
$("#btn").on("click",function(){
	var json = {username:"tom",password:"123",dob:"1999-10-27"};
	$.ajax({
			type:"post",
			url:"json/update",
			contentType:"application/json",
			data:JSON.stringify(json),
			dataType:"json",
			success:function(data){
				console.log("data = "+data);
				console.log(data.username);
				console.log(data.password);
				console.log(data.dob);
			}
	});
});

这里还是会有日期的问题,就是把user对象放入响应正文返回给客户端后,被转换为了json对象,从firebug中可以看出,这个返回的json对象为{"username":"张三","password":"123","dob":1478621620119},它默认把dob这个日期对象转为了一个时间戳;

如果我们想按照自己的日期格式进行转换,那么需要这样处理:
自定义一个json的日期类型格式化类:
public class DateJsonSerializer extends JsonSerializer<Date> {  
	@Override  
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,  
JsonProcessingException {  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String formattedDate = formatter.format(value);  
		jgen.writeString(formattedDate);  
	}  
} 

在User类中的getDob方法上添加注解
@JsonSerialize(using=DateJsonSerializer.class)
public Date getDob() {
	return dob;
}
		

@ResponseBody注解可以处理以下常见类型的返回值,如果可以的话还会把返回值转换为json格式字符串
	1.单值(基本数据类型和字符串)
		这时候ajax中要设置dataType: "text"
	2.bean对象(例如User对象、Student对象等,对象中需要有property)
	3.数组
	4.List/Set集合
	5.Map集合

		
12).@Scope注解
Controller默认情况下和servlet一样也是单例,但是spring提供了一个@Scope注解可以让Controller对象变为非单例,只需在Controller类上面加入@Scope("prototype")即可

e.g:
@Controller
@RequestMapping("/hello")
@Scope("prototype")
public class HelloWorldController{
    ....
}

```

