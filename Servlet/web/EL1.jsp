<%@page import="com.briup.bean.User"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<table>
<tr>
<th>用户名</th>
<th>密码</th>
<th>省份</th>
<th>城市</th>
<th>街道</th>
</tr>
<tr>
<%
List<User> list =(List<User>) request.getAttribute("list");
for(User u:list){
%>
<td><%=u.getName() %></td>
<td><%=u.getPassword() %></td>
<td><%=u.getAddre().getProvince() %></td>
<td><%=u.getAddre().getCity() %></td>
<td><%=u.getAddre().getStreet() %></td>
</tr>
<% } %>
</table>

<table>
<tr>
<th>用户名</th>
<th>密码</th>
<th>省份</th>
<th>城市</th>
<th>街道</th>
</tr>
<c:forEach items="${requestScope.list}" var="user">
<tr>
<td>${user.name }</td>
<td>${user.password }</td>
<td>${user.addre.province }</td>
<td>${user.addre.city }</td>
<td>${user.addre.street}</td>
</tr>
</c:forEach>
</table>
</body>
</html>