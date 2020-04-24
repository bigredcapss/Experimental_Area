###### Jsp相关概念

```
1.属于动态网页技术的一种.
  (servlet、jsp、PHP、asp等等)
     1.jsp是什么
       java server page(JSP)
       后缀名以.jsp结尾的页面文件.
       .html文件是静态页面.
       .jsp 文件是动态页面.
       
2.jsp页面中可以写哪些内容
    1.html标签
	2.css
	3.javascript
	4.java代码
	5.EL表达式
	6.jstl标签库

3.jsp是如何工作的以及jsp的特点.
	一个jsp页面被服务器执行的过程index.jsp-->index_jsp.java-->index_jsp.class.
	
	可以参照jasper.jar包，通过反编译工具翻译为java文件：例如：HttpJspBase这个java类，继承HttpServlet
	
	 1.jsp页面其实就是一个servlet。
	 2.jsp页面的运行需要服务器的支持。
	 3.服务器中的jsp引擎可以帮我们去运行jsp页面。(注意并不是所有服务器都有这样的引擎的.引擎其实就是别人写的支持jsp页面运行的jar包或者代码)
	 4.jsp页面在运行之前,要经过几个步骤:首先jsp页面要被翻译成.java文件,然后再编译成.class文件,最后再运行这个.class文件.(创建这个类的对象,调用指定方法_jspService,方法中把页面里面要显示的内容用io流一行行的写给浏览器)
	 5.jsp翻译成的.java文件中,其实就是写了一个servlet,在这个类中的方法里面,用io流,把jsp页面中的内容一行一行的输出给了浏览器。因为这是在java类中的方法里面做的事情,所有很多数据我们都可以用变量来表示,同时也可以调用其他类中的方法.(在这里,jsp动态页面的效果也就体现出来.)
	 6.运行jsp页面过程中,jsp引擎帮我们去翻译或者编译成的.java文件和.class文件都保存在了tomcat中的work目录里面。
	 7.通过上述jsp的特点可知,写完一个项目之后,第一次部署运行的时候,整个项目的运行jsp速度会慢一些,因为第一次访问运行jsp的时候,需要先翻译成.java文件然后再编译成.class文件,最后再运行,这个过程会耗费一些时间,但是第二访问运行的时候就会比较快了.
	 8.访问项目中的一个jsp页面的时候,服务器首先会检查你所访问的这个jsp页面是否存在,如果不存在,服务器直接给你返回404,如果存在,服务器会进一步检查有没有和这个jsp页面对应的.class文件,如果有的话就直接运行这个.class,如果没有的话,则先把这个jsp页面翻译成.java,然后再编译成.class,最后再运行这个.class文件.

	 9.jsp页面其实就是在html页面中直接写上java代码.但是,在一个jsp页面中,可以没有任何html的代码而只有java代码,也可以没有任何java代码只有html的代码.

	 10.servlet能做的事情jsp全能做。     
       
```

###### 2.如何写一个jsp页面以及在页面中如何写java代码

```
  jsp页面中主要写的东西分为三部分:

	1.jsp的脚本元素
	     1.1表达式(expression)
		
		形式:<%= %> 相当于out.write()输出，该输出在_jspService(res,req)方法中；
		表达式只可以直接操作八个内置对象（request,session,application,pagecontext,config,out,page,response）
		注意：表达式中没有分号
		
		
		例如:<%="hello" %>
		     <%=1+1 %>
		     <%=s.getName() %>

		将来翻译到java文件中的位置:
		_jspService方法中的
		out.print(..)里面的参数.
		例如上面那几个例子会翻译成
		out.print("hello");
		out.print(1+1);
		out.print(s.getName());
		
		所以System.out.prntln()这个方法的参数可以写什么,那么我们这个jsp页面中表达式里面就可以写什么.

		注意:在jsp中的表达式里面不需要加;号。

	     1.2脚本(scriptlet)
		形式:<% ... %> 里面写java代码（Java中的方法能写的内容这里都能写）；
		脚本中的代码将来在_jspService(req,res)中操作
		     <%
			....
		     %>
		例如:
		     <%
		     Student s = new Student();

		     String name = "tom";

		     String username = s.getName();

		     List<String> list = new ArrayList<String>();
		     list.add("hello")
		     
		     %>
		 将来翻译到java文件中的位置:
		 脚本中的代码将来会被直接翻译到_jspService这个方法中.
		 
		 在一个方法中我们可以写什么样的代码,那么在脚本中就可以写什么样的代码.

		 注意:在脚本中所写的代码,代码的最后是要加上;号的.因为我们在一个方法里面所写的每一句代码后面都是要加;号。
		 在脚本声明的变量,我们是可以在表达式里面使用的,但是要注意要先声明变量再使用变量.只要查看脚本和表达式分别翻译到java文件中的位置,就可以很清楚的认识到这一点.

	     1.3声明(declaration)
		形式:<%!
			.....
		     %>里面写全局变量或方法位置和_jspService(req,res)方法同级；
		例如:
		 <%!
			private String address;
	
			public String go(){
				System.out.println("hello world jd1307");
				return "this is go()";
			}
		 
		 %>

		 将来翻译到java文件中的位置:
		  直接将声明中的代码翻译到java文件里面所定义的类中。
		  所以我们直接可以在一个类中写什么,就可以在声明中写什么.(一般在声明中会去定义一些类中的成员变量或者方法)

	    注意:这里面的代码,定义变量的时候要加;号,定义方法的时候不用加;号,这是和我们写一个java类语法是一样的。
		
	2.jsp的指令元素
	    jsp的指令是给jsp引擎看的,让jsp引擎在翻译jsp页面成java文件的时候,知道需要注意哪些地方的设置.比如页面中的编码、页面中脚本里面所用的编程语言、翻译的java文件中需要引入哪些其他包下的java类等等.
	    
	    写法:
	    <%@指令名字  属性="值" ..  %>

	    page指令
	      //表示当前页面中的编程语言是java,目前这个属性值只能写java
	      language="java"

	      //在当前页面中要引入哪些包下的类.
	      import="java.util.HashMap"
	      import="java.util.HashMap,java.sql.Connection"
	      
	      //设置jsp页面文件保存时候所用的编码
	      pageEncoding="UTF-8"
	      //设置服务器将来使用io把jsp页面内容一行一行的输出给浏览器的时候,使用什么编码向浏览器输出.
	      contentType="text/html; charset=UTF-8"
	      
	      //设置jsp页面被翻译成java文件的时候,java文件中的类要继承那个父类.这个属性不用设置,jsp引擎会给它一个默认的父类去继承的.
	      extends=""
	      
	      //设置当前这个jsp页面是否支持session对象的使用.默认是支持的.
	      session="true"
	      
	      //设置jsp页面是否是线程安全的.
	      isThreadSafe="true"
	      
	      //如果a.jsp页面中设置了errorPage="b.jsp",那么a.jsp页面在运行的时候一旦出错,就会自动跳转到b.jsp里面.
	      errorPage=""
	      
	      //如果一个页面中设置了isErrorPage="true",那么就表示这个页面是用来专门显示错误信息的页面.然后在这个页面中就能够使用到隐藏对象exception来显示出错误的信息了.
	      isErrorPage=""


	    include指令
		<%@include file="" %>
		作用:在当前页面中使用include指令可以把另外一个页面的内容引入到当前页面。
		
		一个页面包含/引入另外一个页面有俩种方式:静态包含 动态包含。
		这个include指令就属于静态包含
		
		静态包含特点:例如a.jsp页面中静态包含了b.jsp页面,那么在翻译期间,jsp引擎在翻译a.jsp成为一个a_jsp.java文件的时候,发现jsp页面中有include指令,这时候jsp引擎就会把被包含的页面b.jsp中内容原封不动的拿到a_jsp.java中,然后用io输出出去.




	    taglib指令
		作用:在当前jsp页面中引入一些特殊的标签库.比如jstl标签库、struts2标签库等等.
	    


	3.jsp的动作元素

	   <jsp:useBean id="s" class="com.briup.bean.Student" scope="page"></jsp:useBean>
	   相当于代码:
	   <%
		Student s = null;
		s = (Student)pageContext.getAttribute("s");
		if(s==null){
			s = new Student();
			pageContext.setAttribute("s",s);
		}
	   
	   %>


	   <jsp:setProperty property="name" value="tom" name="s"/>
	   相当于代码:
	   <%
		Student s = (Student)pageContext.setAttribute("s");
		s.setName("tom");
	   %>

	   <jsp:getProperty property="name" name="s"/>
	   相当于代码:
	   <%
		Student s = (Student)pageContext.getAttribute("s");
		out.write(s.getName());
	   %>
	    
	   
	   //页面跳转
	   <jsp:forward page="target.jsp"></jsp:forward>
	   
	   //跳转的同时还可以传参数
	   <jsp:forward page="target.jsp?name=tom"></jsp:forward>
	   或者
	   <jsp:forward page="target.jsp">
  		<jsp:param value="tom" name="name"/>
  	   </jsp:forward>
	   

	   //这个动作元素专门是传参数使用的
	   <jsp:param value="tom" name="name"/>

	   //动态包含
	   <jsp:include page="foot2.jsp?name=tom"></jsp:include>
	   
	   动态包含特点:在把jsp文件翻译成java文件的期间,动态包含并不会去把被包含的页面原封不动的拿过来,而是会把动态包含这个标签翻译成一个方法的调用,将来运行页面调用到这个方法的时候才会去拿被包含页面的内容.同时还可以给动态包含的页面传参数.静态包含是不能传参数的。

	   注意:总结和对比后,分析出动态包含和静态包含各自的特点,以及哪一个执行的效率更快一些.


	   //使用jsp的动作元素向浏览器输出一个标签
	   <jsp:element name="font">
  			<jsp:attribute name="color">blue</jsp:attribute>
  			<jsp:body>hello world</jsp:body>
  	   </jsp:element>
	   
	   这个效果相当于在页面代码中直接写上<font color="blue">hello world</font>
	   或者:
	   <%
	   out.println("<font color='blue'>hello world</font>");
	   %>
```

###### 3.在jsp页面代码中可以直接使用的对象     

```
一共有9个内置对象可以直接使用.
	      类型           名字
	PageContext         pageContext
	HttpServletRequest  request
	HttpSession	    session
	ServletContext	    application
	Object		    page
	HttpServletResponse response
	JspWriter	    out
	ServletConfig	    config
	Throwable	    exception

	注意:为什么这个写对象可以直接使用,因为他们都是在_jspService这个方法中默认声明了出来.而我们在表达式和脚本中所写的java代码将来是要翻译到_jspService方法中的,所以我们在表达式和脚本中写java代码的时候可以直接使用这些对象.

	四个范围对象,在一定范围内可以存取数据:
	//页面范围(只能在同一个页面中起作用)
	pageContext    
	request
	session
	application

	//虽然名字叫page,但是这个并不是页面范围对象,它是Object类型的对象,表示当前这个页面本身.
	page
	response
	//用于向浏览器输出内容的输出流.
	out
	//用于获得和servlet相关的信息.
	config
	//这个对象其实我们并不能直接使用,需要相关设置后才能使用,这个可以算是一个隐藏对象.这个对象表示将来这个jsp页面运行出错的时候所抛出的异常对象.
	exception

```

###### 4.jsp页面中的注释

```
第一种:
	<!-- html/xml中的注释方式 -->
        特点:
	1.用户在浏览器中右键查看源代码 [能] 看到这个注释。
	2.在服务器端,这个jsp页面被翻译成的java中 [能] 看到这个注释.
	注意: jsp动作元素 放在这种注释里面是不起作用(注释不起作用,失效了)

	第二种:
	<%-- 
  	jsp中的注释方式(隐藏注释)
  	--%>
	特点:
	1.用户在浏览器中右键查看源代码 [不能] 看到这个注释。
	2.在服务器端,这个jsp页面被翻译成的java中 [不能] 看到这个注释.
	
	第三种:
	java中的注释方式,但是这种注释方式只能在jsp的脚本或者声明中使用。
	//String name = "tom";

	/* 
		int b = 40;
	*/
  		 	
	/**
		int a = 20;
	*/
	特点:
	1.用户在浏览器中右键查看源代码 [不能] 看到这个注释。
	2.在服务器端,这个jsp页面被翻译成的java中 [能] 看到这个注释.
```

###### 5.jsp页面中的路径

```
一般情况下,jsp中路径问题是和我们之前在servlet中讨论的html里面的路径问题是一样的,但是在jsp中有一种情况是要特殊对待的。
	如果在jsp页面的上面写了这样一个脚本:
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	并且再<head>标签中加入了一个子标签:
	<base href="<%=basePath%>" />

	如果在jsp页面里面有上面说提到的俩个东西,那么在这个jsp页面中,我们再去写上一个相对路径(最左边没有加/的那种路径),它就不是相对于地址栏中的当前路径了,而是要相对于这个basePath变量所代表的这个路径.

	<base href="..">这个标签的作用:
	在没有这个标签的情况下,页面中相对路径的跳转,都要参照地址栏中的当前路径来跳转,但是页面中加上了这个<base>标签后,页面中的相对路径的跳转都要参照这个标签中所写的路径来跳转。
	注意:这里说的相对路径的跳转指的是最左边没有/的那种路径的跳转.
```

###### 6.EL表达式

```
形式:${ }
	作用:从一个范围里面取值或者从一个对象中取值或是向页面输出值.
	1.接收客户端参数.
	   ${param.name1 }
	2.指定范围并取值
	   ${pageScope.name2 }
  	   ${requestScope.name3 }
  	   ${sessionScope.name4 }
  	   ${applicationScope.name5 }
        3.可以不指定范围再去取值
	   ${name}
	   这时候会按照pageContext request session application这样一个顺序依次的去找有没有一个叫name的值存在,一旦找到了就输出出来,最终没有找到那么就什么都不输出。
	
	4.取出一个对象中的属性值.
	   ${requestScope.student.id}
	   ${requestScope.student.name}
	   ${requestScope.student.age}
	   或者
	   ${student.id}
	   ${student.name}
	   ${student.age}
	   或者
	   ${student["id"]}
	   ${student["name"]}
	   ${student["age"]}

	   注意:比如 ${student.id}表示是要调用student对象中的getId方法,至于对象中有没有id属性对这个操作没有任何影响.

	   如果Student类中一个方法是getAddress,返回一个Address类的对象,Address类中有一个方法getCity,这个时候我们就可以这样写去拿到city属性的值.
	   ${student.address.city}
	

	5.输出字符串
	    ${"hello"}

	6.输出运算结果或者boolean表达式
	    ${1+1 }
	    ${(1+2)*3-4+5*3 }
	    ${1<3 }
	    //为空的话返回true
	    ${empty "" }
	    ${empty "hello" }
	    //取否 不为空的话返回true
	    ${not empty "hello" }
	    ${! empty "hello" }
	    ${param.score >50 }
	    ${param.score >60?"good":"bad" }
	
	7.输出数组、集合中的元素
		<%
		String[] str = {"hello","world"};
	
		List<String> list = new ArrayList<String>();
		list.add("zhangsan");
		list.add("lisi");
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("a",100);
		map.put("b",200);
		map.put("c",300);
		
		request.setAttribute("str",str);
		request.setAttribute("list",list);
		request.setAttribute("map",map);
		
		%>
	
		${str[0] }<br>
		${list[1] }<br>
		${map["c"] }<br>

```

###### 7.JSTL标签库

```
JSP Standard Tag Library(JSTL)
	1.让web项目支持JSTL标签库
	在myeclipse中,建一个web项目的时候,在对话框下面会有提示在当前项目是否需要加入JSTL标签库的支持.(J2EE5.0是默认会加入对JSTL的支持的)
	在eclipse中,建一个文本项目,默认都是不支持JSTL,所以需要我们自己把JSTL的jar包导入到项目中(复制粘贴到项目中的lib目录):jstl.jar  standard.jar

	2.把JSTL标签库导入到某一个jsp页面中
	 使用jsp中的taglib指令:
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	 prefix="c"相当于给这个标签库起一个别名,将来在页面中就是用以c开头的标签来使用标签库中的标签。这个别名也可以叫其他的名字。
	
	<c:forEach>标签:
	  遍历List集合:
	  students是放进request对象中的一个List集合,集合中存放的是Student类型的对象.
	  items=""属性值是要遍历的集合
	  var=""  属性值是每次遍历到的对象用什么名字的变量去接收。
	  <c:forEach items="${students}" var="stu">
		<tr>
			<td>${stu.id }</td>
			<td>${stu.name }</td>
			<td>${stu.age }</td>
		</tr>
	 </c:forEach>
	
	遍历Map集合:
	map是一个Map类型的集合,放到了request对象中,entry是我们顶一个的一个变量,用做接收每次遍历到的集合中的一组键值对,我们可以通过entry.key entry.value分别拿到这次遍历到的key值和value值
	<c:forEach items="${map}" var="entry">
  		${entry.key }-->${entry.value.id } &nbsp; ${entry.value.name } &nbsp; ${entry.value.age }<br>
  	</c:forEach>

	
	<c:out>标签:
	向页面输出内容
	<c:out value="hello"></c:out>
  	<c:out value="${5+5}"></c:out>
	//students是放在request中的List集合,集合里面是Student对象
  	<c:out value="${students[2].id}"></c:out>
	
	<c:set>标签:
	向某一个范围对象中存放一个值。
	<c:set var="name" value="zhangsan" scope="request"></c:set>

        <c:remove>标签:
	从某个范围对象中把某个值给移除掉.
	<c:remove var="name" scope="request"/>

	<c:if>标签:
	条件判断
	<%
  	request.setAttribute("score",40);
  	%>
  		
  	<c:if test="${score>85 }">
  		<font color="red">你的分数超过了85分</font>
  	</c:if>
	<c:if test="${score>95 }">
  		<font color="red">你的分数超过了95分</font>
  	</c:if>

	这样写相当于:
	if(score>85){
		...
	}
	if(score>95){
		...
	}


	<c:choose>标签
	<c:when>标签
	<c:otherwise>标签
	例如:
	<c:choose>
  		<c:when test="${score>=90 }">优</c:when>
  		<c:when test="${score>=80 }">良</c:when>
  		<c:when test="${score>=70 }">中</c:when>
  		<c:when test="${score>=60 }">及格</c:when>
  		<c:otherwise>差</c:otherwise>
  	</c:choose>
	相当于:
	if(score>=90){
	
	}else if(score>=80){
	
	}else if(score>=70){
	
	}eles if(score>=60){
	
	}else{
	
	}  
```

