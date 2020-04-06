<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="css/test.css" rel="stylesheet" type="text/css"/>
<script src="js/test.js" type="text/javascript"></script>
</head>
<body>
${u.id}
${u.name}
${u.age}
<hr>
${name}<br>
Request:${requestScope.name}<br>
Session:${sessionScope.name}<br>
Application:${applicationScope.name}<br>
<hr>
${user.id }<br>
${user.name}<br>
${user.age}<br>
${user.dob}<br>
<hr> 
<!-- 鼠标左键点击事件 -->
<div onclick="test()"></div>
success:${name}<br>
<img alt="none" src="images/test.jpg"><br>
</body>
</html>