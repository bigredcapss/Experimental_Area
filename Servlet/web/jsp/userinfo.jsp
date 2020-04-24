<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详细信息</title>
<style type="text/css">
table{
text-align:center;
margin:auto;
}
</style>
</head>
<body>
<form action="loginServlet" method="post">
<table>
<caption>用户基本信息</caption>
<tr>
<td>姓名</td>
<td><input type="text" name="name" value="${sessionScope.user.name}"></td>
</tr>
<tr>
<td>密码</td>
<td><input type="password" name="password" value="${sessionScope.user.password}"></td>
</tr>
<tr>
<td colspan="2"><input type="reset" value="修改"></td>
</tr>
</table>
</form>




</body>
</html>