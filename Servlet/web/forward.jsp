<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    //request.getRequestDispatcher("/test.jsp").forward(request,response);
    //request.getRequestDispatcher("/SessionTest").forward(request,response);
%>
<!-- 服务器内部跳转到jsp页面或servlet类中 -->
<%-- <jsp:forward page="fo.jsp?name=tom&age=33"></jsp:forward> --%>
<!-- jsp动作元素中加入注解有可能引起org.apach.jasper.Exception -->
<jsp:forward page="fo.jsp">
<jsp:param value="tom" name="name"/>
<jsp:param value="33" name="age"/>
</jsp:forward>
</body>
</html>