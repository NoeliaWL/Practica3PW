<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.sql.Date, java.sql.Time" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link href = "/Practica3PW/css/formulariosNoe.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<h1>Selector de sesiones</h1>
		
		<form action="/Practica3PW/modificarsesion">
			<select name="sesiones" size="drop-down menu">
					<option value="vacio">Selecciona la sesión a modificar...</option>
					<!-- Rellenar con las sesiones del espectáculo de la base de datos -->
			<%
				List<Integer> ids = (List<Integer>) session.getAttribute("SesionesIds");
				List<Date> fechas = (List<Date>) session.getAttribute("SesionesFechas");
				List<Time> horas = (List<Time>) session.getAttribute("SesionesHoras");
				
				for(int i=0; i<ids.size(); i++) {
					String sesion = fechas.get(i).toString() + " - " + horas.get(i).toString();
			%>
					<option value="<%=ids.get(i) %>"><%=sesion %></option>
			<%
				}
			%>
				</select>
				
				<input type="submit" value="Siguiente"/>
		</form>
	</body>
</html>