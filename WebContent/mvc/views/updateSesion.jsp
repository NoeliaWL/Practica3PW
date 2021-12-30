<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.sql.Date, java.sql.Time" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link href = "/Practica3PW/css/formularios.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<%
			Date fecha = (Date) session.getAttribute("fecha");
			Time hora = (Time) session.getAttribute("hora");
			int entrada = (int) session.getAttribute("entradas");
		%>
		<h1>Modificar sesión de espectáculo</h1>
		
		<div>
			<form action="/Practica3PW/modificarsesion" method="post">
				<label for="fecha">Fecha de representación:</label>
				<input type="date" name="fecha" value="<%=fecha%>"/>
				
				<label for="hora">Hora de representación:</label>
				<input type="time" name="hora" step="1" value="<%=hora%>"/>
				
				<label for="entradas">Selecciona el número de entradas del espectáculo para cada sesión:</label>
				<input type="number" min=0 max=5000 id="entradas" name="entradas" value="<%=entrada%>"/>
				
				<!-- Botones -->
				<div>
					<input type="submit" value="Modificar" />
					<input type="button" value="Volver al menu" onclick="window.location.href='menuAdministrador.jsp'">
				</div>
			</form>
		</div>
	</body>
</html>