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
</head>
<body>
error.jsp
<div>${ex }</div>
<div>${ex.message }</div>


<!-- 因为spring中修改了异常的默认名字,所以这里是ex -->
<% Exception ex = (Exception)request.getAttribute("ex"); %> 
<H2>Exception: <%= ex.getMessage()%></H2> 
<P/> 
<% ex.printStackTrace(new java.io.PrintWriter(out)); %> 

</body>
</html>