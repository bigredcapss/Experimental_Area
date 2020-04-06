<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- base标签的作用：从当前位置开始，所有路径前没有加斜杠的路径，不在基于浏览器地址栏的路径拼接，而是基于basePath指向的路径拼接 -->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1.进入当前页面的资源名称就是提交数据的资源名称 ；2.进入该页面必须保证modelAttribute内部维护了一个将来要封装的对象-->
<sf:form method="post" modelAttribute="user">
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

</body>
</html>