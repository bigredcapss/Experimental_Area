<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  4.如何写一个jsp页面以及在页面中如何写java代码.
       jsp页面中主要写的东西分为三部分:

	1.jsp的脚本元素
	     1.1表达式(expression)
		形式:<%= %>
		将来翻译到java文件中的位置:
		_jspService方法中的
		out.print(..)里面的参数.
		例如上面那几个例子会翻译成
		out.print("hello");
		out.print(1+1);
		out.print(s.getName());
		
		所以System.out.prntln()这个方法的参数可以写什么,那么我们这个jsp页面中表达式里面就可以写什么.

		注意:在jsp中的表达式里面不需要加;号。表达式中可以直接操作jsp的九大内置对象(request,response,session,application,pagecontext,page,config,out,exception)
		out 对象用于在Web浏览器内输出信息，并且管理应用服务器上的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数据，为其他的输出让出缓冲空间。待数据输出完毕后，要及时关闭输出流。
		pageContext 对象的作用是取得任何范围的参数，通过它可以获取 JSP页面的out、request、reponse、session、application 等对象。pageContext对象的创建和初始化都是由容器来完成的，
		在JSP页面中可以直接使用 pageContext对象。
		config 对象的主要作用是取得服务器的配置信息。通过 pageConext对象的 getServletConfig() 方法可以获取一个config对象。当一个Servlet 初始化时，容器把某些信息通过 config对象传递给这个 Servlet,
		 开发者可以在web.xml 文件中为应用程序环境中的Servlet程序和JSP页面提供初始化参数。
		 page 对象代表JSP本身，只有在JSP页面内才是合法的。 page隐含对象本质上包含当前 Servlet接口引用的变量，类似于Java编程中的 this 指针。
		 exception 对象的作用是显示异常信息，只有在包含 isErrorPage="true" 的页面中才可以被使用，在一般的JSP页面中使用该对象将无法编译JSP文件。excepation对象和Java的所有对象一样，都具有系统提供的继承结构。
		 exception 对象几乎定义了所有异常情况。在Java程序中，可以使用try/catch关键字来处理异常情况；如果在JSP页面中出现没有捕获到的异常，就会生成 exception 对象，
		 并把 exception 对象传送到在page指令中设定的错误页面中，然后在错误页面中处理相应的 exception 对象。
	     1.2脚本(scriptlet)
		形式:<%%>
		 将来翻译到java文件中的位置:
		 脚本中的代码将来会被直接翻译到_jspService这个方法中.
		 在一个方法中我们可以写什么样的代码,那么在脚本中就可
以写什么样的代码.

		 注意:在脚本中所写的代码,代码的最后是要加上;号的.因为我们在一个方法里面所写的没一句代码后面都是要加;号。
		 在脚本声明的变量,我们是可以在表达式里面使用的,但是要注意要先声明变量再使用变量.只要查看脚本和表达式分别翻译到java文件中的位置,就可以很清楚的认识到这一点.

	     1.3声明(declaration)
		形式:<%! %>
		 将来翻译到java文件中的位置:
		  直接将声明中的代码翻译到java文件里面所定义的类中。
		  所以我们直接可以在一个类中写什么,就可以在声明中写什么.(一般在声明中会去定义一些类中的成员变量或者方法)

	    注意:这里面的代码,定义变量的时候要加;号,定义方法的时候不用加;号,这是和我们写一个java类语法是一样的。 -->
<%=1+1%>
<%="test"%>
<%=request.getParameter("name") %>
</body>
</html>