###### 1.Controller接收客户端传的参数

```Java
1)参数是基本数据类型/包装类型/String
	@RequestMapping("test")
	public String test(int age){...}
	public String test(long id){...}
	public String test(boolean flag){...}
	public String test(Long id){...}
	public String test(String name){...}		
注意参数名字要和客户端传的参数名一致,否则需要使用@RequestParam来指定参数名

2)参数是数组类型
	@RequestMapping("test")
	public String test(int[] age){...}
	public String test(long[] id){...}
	public String test(boolean[] flag){...}
	public String test(Long[] id){...}
	public String test(String[] name){...}
注意:客户端传值类似于这样: 
	name=tom&name=lisi

3)参数是类类型(例如实体类Uesr、Student等)
	@RequestMapping("test")
	public String test(User user){...}
注意:客户端传值类似于这样:
	username=tom&password=123&dob=2016-09-25
		
注意:
	1.username/password/dob必须是User中存在的property;
	2.日期类型需要自定义转换器并在spring中注册;
```

###### 2.SpringMVC中的跳转

```Java
1)因为在Controller中的功能处理方法上可以获得到request和response,所以可以像之前servlet中一样,进行服务器内部跳转和客户端重定向;

e.g:
@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {
	@RequestMapping("/b")
	public String testB(){
		System.out.println("testB");
		return "index";
	}	
		
	@RequestMapping("/c")
	public String testC(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("testC");
		//服务器内部跳转到一个页面
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);

		//服务器内部跳转到一个功能处理方法	 
        //request.getRequestDispatcher                      ("/dispatcher/b").forward(request, response);

		//客户端重定向到另一个功能处理方法
        response.sendRedirect
(request.getContextPath()+"/dispatcher/b");
		return null;
	}		
}
	
2)Controller中可以使用字符串表示服务器内部跳转和客户端重定向;
e.g:
@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {		
	@RequestMapping("/a")
	public String testA(){
		System.out.println("testA");
		//服务器内部跳转到另一个功能处理方法
		//return "forward:/dispatcher/b";

		//客户端重定向到另一个功能处理方法
		//return "redirect:/dispatcher/b";

		//服务器内部跳转到一个页面
		return "index";
	}
			
	@RequestMapping("/b")
	public String testB(){
		System.out.println("testB");
		return "index";
	}
}

3)Controller中使用ModelAndView进行跳转和重定向
@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {
	@RequestMapping("/b")
	public String testB(){
		System.out.println("testB");
		return "index";
	}	
			
	@RequestMapping("/d")
	public ModelAndView testD() throws ServletException, IOException{
		System.out.println("testD");
				
		//服务器内部跳转到另一个功能处理方法
		//ModelAndView mv = new ModelAndView("forward:/dispatcher/b");
				
		//客户端重定向到另一个功能处理方法
		//ModelAndView mv = new ModelAndView("redirect:/dispatcher/b");
				
		//服务器内部跳转到一个页面
		ModelAndView mv = new ModelAndView("index");
				
		return mv;
	}				
}


```

###### 3.SpringMVC中的数据验证

```Java
通常在项目中使用较多的是前端校验，比如页面中js校验.对于安全要求较高的建议在服务端同时校验;

SpringMVC使用得是hibernate的校验框架validation，所以需要导入相关依赖的jar包
	classmate-1.1.0.jar
	hibernate-validator-5.1.3.Final.jar
	jboss-logging-3.1.4.GA.jar
	validation-api-1.1.0.Final.jar
	
数据校验之后,如果有错误信息,那么需要使用spring提供的标签库中的标签在页面中显示校验信息:

e.g:valid.jsp页面主要代码:
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    
		<sf:form method="post" modelAttribute="teacher">
			<sf:label path="name">用户名:</sf:label> 
			<sf:input path="name"/>
			<sf:errors path="name" cssStyle="color:red"></sf:errors><br>

			<sf:label path="age"> 年   龄:</sf:label>
			<sf:input path="age"/>
			<sf:errors path="age" cssStyle="color:red"></sf:errors><br>

			<sf:label path="dob"> 生   日:</sf:label>  
			<sf:input path="dob"/>
			<sf:errors path="dob" cssStyle="color:red"></sf:errors><br>

			<input type="submit" value="提交"/>
		</sf:form>

		注意:
		1.需要访问一个Controller再跳转到这个页面,同时需要向模型中添加一个名字叫teacher的对象(这就是我们之前说的命令/表单对象),否则跳转到这个页面的时候会报错；
		2.表单中没有这种action属性值,那么默认把数据提交给当前页面,但是提交方式是post；
		3.input标签中的path属性的值对应的是表单对象中的属性；
		4.Controller中映射的url为:/valid/user/add , 如果是get方式访问这个url那么就把valid.jsp显示给用户,如果是post方式访问这个url,就表示要提交表单的数据；
		5.在Controller中,在需要验证的参数前面加入@Valid注解；
		6.方法参数列表中,加入BindingResult对象,用来接收验证的错误信息,并根据这个进行不同情况的跳转；
		7.在被验证的表单对象所属类中,给需要验证的属性上加入指定注解；

Controller中代码:	
	@Controller
	@RequestMapping("/valid")
	public class ValidController {
		@RequestMapping(value="/user/add", method = {RequestMethod.GET})
		public String test(Model model){
			if(!model.containsAttribute("teacher")){
				model.addAttribute("teacher", new Teacher());
			}
			return "valid";
		}
			
		@RequestMapping(value="/user/add",method = {RequestMethod.POST})
		public String addTeacher(@Valid Teacher teacher,BindingResult bindingResult){
			//如果验证数据中有错误信息,将保存在bindingResult对象中
			if(bindingResult.hasErrors()){
				List<ObjectError> errorList = bindingResult.getAllErrors();             
				for(ObjectError error : errorList){            		System.out.println(error.getDefaultMessage());             
				}
			//验证不通过在跳到valid页面,因为页面上有显示错误的标签
					return "valid";
			}	
			//没有错误则跳到hello页面
			return "hello";
		}		
}

Teacher类中代码:
public class Teacher {
	private long id;
	@Size(min=5,max=8)
	private String name;
	private Integer age;
	private Date dob;

	省略getter/setter方法
}
		
	
常用的数据校验的注解：

	@Null		值只能为null
	@NotNull	值不能为null
	@NotEmpty	值不为null且不为空
	@NotBlank	值不为null且不为空(先去除首尾空格)
	@Pattern	正则表达式验证
	@Size		限制长度在x和y之间

	@Max		最大值
	@Min		最小值

	@Future		必须是一个将来的日期(和现在比)
	@Past		必须是一个过去的日期(和现在比)

	@Email		校验email格式

注意:日期属性上要加@DateTimeFormat(pattern="yyyy-MM-dd"),否则页面传的字符串是不能自动转为为日期的,这个注解既能按照我们要求的格式把String转为Date,又能把Date转为String
```

###### 4.SpringMVC中的异常处理

```Java
在SpringMVC中可以把异常统一进行处理,只需加入以下配置:
<!-- Spring提供的默认的异常解析器,也可以自定义 -->
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver"> 
	<!-- 定义默认的异常处理页面，当该异常类型的注册时使用 -->  
	<!-- value="error" 表示跳转的逻辑视图名字 --> 
	<property name="defaultErrorView" value="error"></property>  
	<!-- 定义异常处理页面用来获取异常信息的变量名，默认名为exception -->  
	<property name="exceptionAttribute" value="ex"></property>  
	 <!-- 定义需要特殊处理的异常，用简单类名或全限定名作为key，异常页名的逻辑视图名作为value -->  
	<property name="exceptionMappings">  
	    <props>  
	        <prop key="IOException">error_io</prop>  
	        <prop key="java.sql.SQLException">
	        	error_sql
	        </prop>  
	     </props>  
	</property>
</bean>


Controller中代码:
@RequestMapping("/test")
public String test()throws Exception{	
	//int a = 1/0;
	//System.out.println(a);
		
	int a = 1;
	if(a==1){
		throw new IOException("不好了,出错了!");
	}		
	return "test";
}

页面中:
	1.如果使用jsp的脚本显示信息
	<!-- 因为spring中修改了异常的默认名字,所以这里是ex -->
	<% Exception ex = (Exception)request.getAttribute("ex"); %> 
	<H2>Exception: <%= ex.getMessage()%></H2> 
	<P/> 
	<% ex.printStackTrace(new java.io.PrintWriter(out)); %> 
		
	2.如果是EL显示错误信息
		<div>${ex }</div>
		<div>${ex.message }</div>


异常处理也可以使用注解的形式,注意是这个@ExceptionHandler是要加在需要异常处理的Controller中

@Controller  
public class XxxxController {  
		
	@RequestMapping("/test")
	public String test()throws Exception{
	//		int a = 1/0;
	//		System.out.println(a);
			
			int a = 1;
			if(a==1){
				throw new IOException("你说呢？");
			}
			return "test";
	}

	@ExceptionHandler(value={IOException.class,SQLException.class})  
	public String exp(Exception ex,HttpServletRequest request) {  
			request.setAttribute("ex", ex);  
			return "error_io";  
		}  
	}
```

##### 5.SpringMVC的文件上传

```Java xml
使用上传功能需要引入俩个jar包:
	commons-fileupload-1.2.2.jar
	commons-io-2.0.1.jar

利用spring中提供的MultipartFile接口实现上传功能
	MultipartFile类中两个方法区别：
	getName : 获取表单中文件组件的名字；
	getOriginalFilename : 获取上传文件的原名；
	transferTo(File newFile);把上传的文件转存到指定文件中；

spring配置文件中加入以下配置:

<!-- SpringMVC上传文件时，需要配置MultipartResolver处理器 --> 
<!-- 注意:bean的名字不要改,一定要叫multipartResolver --> 
<bean name="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"> 
	 <property name="defaultEncoding" value="UTF-8"/> 
	 <!-- 指定所上传文件的总大小不能超过指定字节大小 --> 
	 <property name="maxUploadSize" value="20000000"/>
</bean>

	
jsp页面代码:

<form action="upload/test" method="post" enctype="multipart/form-data">
	<input type="file" name="file"><br>
	<input type="file" name="file"><br>
	<input type="submit" value="上传">
</form>


Controller中的代码:

@Controller
@RequestMapping("/upload")
public class UploadController {	
	@RequestMapping("/show")
	public String showUploadPage(){
		return "upload";
	}

	@RequestMapping("/test")
	public String upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
					// 保存文件
					saveFile(request, file);
			}
		}
		// 重定向
		return "redirect:/upload/show";
	}

	private void saveFile(HttpServletRequest request, MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				//保存的文件路径
				//需要的话可以给文件名上加时间戳
				String filePath = request.getServletContext().getRealPath("/") + "upload/"
							+ file.getOriginalFilename();
				File newFile = new File(filePath);
				//文件所在目录不存在就创建
				if (!newFile.getParentFile().exists()){
						newFile.getParentFile().mkdirs();
				}
				// 转存文件
				file.transferTo(newFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

注意:在上传文件的同时,还可以接收其他正常的单个的值,例如username、age等,同时也可以把这些单个的值自动封装成User对象；
```

###### 6.SpringMVC的下载

```Java
SpringMVC的下只需要自己设置response信息中的各个部分就可以,可以使用之前学习过的ResponseEntity<T>来完成;

@RequestMapping("/show")
public String showDownLoadPage(){
	return "download";
}
	
@RequestMapping("/test")
public ResponseEntity<byte[]> test(String fileName,HttpServletRequest request) throws IOException {
	//获得下载文件所在路径 可以指向系统中的任意一个有权访问的路径
	String downLoadPath = request.getServletContext().getRealPath("/download");

	//创建要下载的文件对象
	File file = new File(downLoadPath,fileName);

	//处理一下要下载的文件名字,解决中文乱码
	String downFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

	//创建响应头信息的对象
	HttpHeaders headers = new HttpHeaders();
	//设置下载的响应头信息,通过浏览器响应正文的内容是用户要下载的,不用浏览器解析
	headers.setContentDispositionFormData("attachment", downFileName);
	headers.setContentType
(MediaType.APPLICATION_OCTET_STREAM);
		
	//通过响应内容、响应头信息、响应状态来构建一个响应对象并返回
	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, 			HttpStatus.CREATED);
}
	
页面代码:
<a href="download/test?fileName=测试.txt">点击下载</a>
```

