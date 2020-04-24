<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
2.jsp的指令元素
	    jsp的指令是给jsp引擎看的,让jsp引擎在翻译jsp页
面成java文件的时候,知道需要注意哪些地方的设置.比如页面中
的编码、页面中脚本里面所用的编程语言、翻译的java文件中需
要引入哪些其他包下的java类等等.
	    
<!--  	    写法<%@指令名字  属性="值" ..  %>-->

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
	      
	      //如果a.jsp页面中设置了errorPage="b.jsp",那么a.jsp页面在运行的时候一旦出错,就会自动跳转到b.jsp里面.其中b.jsp页面中要设置isErrorPage="true"
	      errorPage=""
	      
	      //如果一个页面中设置了isErrorPage="true",那么就表示这个页面是用来专门显示错误信息的页面.
然后在这个页面中就能够使用到隐藏对象exception来显示出错误的信息了.
	      isErrorPage=""
	<!--        <%@taglib uri="" prefix=""%>-->
	      taglib:引入标签库，uri指向标签库的名字
	      prefix:给标签库起前缀（防止命名冲突）
<!--   include指令<%@include file="" %>-->
		作用:在当前页面中使用include指令可以把另外一个页面的内容引入到当前页面。
		静态引入的特点：引入页面的jsp页面不会编译成.java文件
		                当前页面编译

		一个页面包含/引入另外一个页面有俩种方式:静态包含 动态包含。
		这个include指令就属于静态包含
		
		静态包含特点:例如a.jsp页面中静态包含了b.jsp页面,
那么在翻译期间,jsp引擎在翻译a.jsp成为一个a_jsp.java文
件的时候,发现jsp页面中有include指令,这时候jsp引擎就会把
被包含的页面b.jsp中内容原封不动的拿到a_jsp.java中,然后用io输出出去.

	    taglib指令
		作用:在当前jsp页面中引入一些特殊的标签
库.比如jstl标签库、struts2标签库等等.

</body>
</html>