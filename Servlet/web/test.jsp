<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="SessionTest;jsessionid=${requestScope.id}"></a>
<!-- EL表达式可以获取4个容器中的内容 -->
request:${requestScope.num}
session:${sessionScope.num}
application:${applicationScope.num}
</body>
</html>