<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
	</head>
	<body>
		<%
			customerBean.setCorreoUser(null);
			customerBean.setTipo(null);
			
			response.sendRedirect("../../index.html");
		%>
	</body>
</html>