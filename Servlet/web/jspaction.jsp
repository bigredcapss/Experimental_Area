<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 与JSP指令元素不同的是，JSP动作元素在请求处理阶段起作用。JSP动作元素是用XML语法写成的。利用JSP动作可以动态地插入文件、重用JavaBean组件、把用户重定向到另外的页面、为Java插件生成HTML代码。
动作元素只有一种语法，它符合XML标准：<jsp:action_name attribute="value" /> -->
<!-- 所有的动作要素都有两个属性：id属性和scope属性。
id属性：id属性是动作元素的唯一标识，可以在JSP页面中引用。动作元素创建的id值可以通过PageContext来调用。
scope属性：该属性用于识别动作元素的生命周期。 id属性和scope属性有直接关系，scope属性定义了相关联id对象的寿命。 scope属性有四个可能的值： (a) page, (b)request, (c)session, 和 (d) application -->
<!-- jsp:useBean用来加载在jsp页面中使用到的javaBean -->

<jsp:useBean id="stu" class="com.briup.bean.User" scope="page"></jsp:useBean>
<%-- 
<!-- 
   该动作元素相当于如下代码：
   Object obj = pageContext.getAttribute("stu");
   Student stu =null
   if(obj==null){
     stu=Class.forName(com.briup.bean.User).newInstance();
     pageContext.setAttribute("stu",stu);
   }
 -->
<!-- 三种方式取出其中的user对象 -->
<%=pageContext.getAttribute("stu") %>
<%
out.println(pageContext.getAttribute("stu"));
%>
${pageScope.stu}


--%>
<!-- 给对象设置属性
name指对象的变量名
value
property指对象的属性，将来自动拼接set
 -->
<jsp:setProperty property="password" name="stu" value="123456"/>
<!-- 
 Student stu = (Student) pageContext.getAttribute("stu");
stu.setPassword("123456");
 -->
 <!--  ${pageScope.stu.password}-->
 <!-- 从对象中取其属性值 -->
 <jsp:getProperty property="password" name="stu"/>
 <!-- 
 Student stu = (Student) pageContext.getAttribute("stu");
 out.println(stu.getPassword("123456"));
  -->
</body>
</html>