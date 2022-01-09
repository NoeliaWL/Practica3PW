<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link href = "/Practica3PW/css/formulariosNoe.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<h1>Selector de espectaculo</h1>
		
		<form action="/Practica3PW/cancelarsesion">
			<select name="titulo" size="drop-down menu">
				<option value="vacio">Selecciona el titulo del espectáculo...</option>
				<!-- Rellenar con los titulos de la base de datos -->
			<%
				List<String> espectaculosTitulos = (List<String>) session.getAttribute("Espectaculostitulos");
				for(int i=0; i<espectaculosTitulos.size(); i++) {
					String aux = espectaculosTitulos.get(i);
			%>
				<option value="<%=aux%>"><%=aux %></option>
			<%
				}
			%>
			</select>
				
			<input type="submit" value="Siguiente"/>
		</form>
	</body>
</html>