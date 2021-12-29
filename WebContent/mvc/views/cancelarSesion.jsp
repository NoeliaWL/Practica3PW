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
			List<String> titulos = (List<String>) session.getAttribute("Espectaculostitulos");
			List<Integer> ids = (List<Integer>) session.getAttribute("sesionesIds");
			List<Date> fechas = (List<Date>) session.getAttribute("sesionesFechas");
			List<Time> horas = (List<Time>) session.getAttribute("sesionesHoras");
		%>
		<h1>Modificar sesión de espectáculo</h1>
		
		<div>
			<form action="/Practica3PW/cancelarsesion" method="post">
				<select name="titulos" size="drop-down menu" onchange="window.location.href='/Practica3PW/cancelarsesion'">
					<option value="">Selecciona el titulo del espectáculo...</option>
					<!-- Rellenar con los titulos de la base de datos -->
					<%
						for(int i=0; i<titulos.size(); i++) {
					%>
					<option value="<%=i+1%>"><%=titulos.get(i)%></option>
					<%
						}
					%>
				</select>
				<% %>
				<select name="sesiones" size="drop-down menu">
					<option value="">Selecciona la sesión a cancelar...</option>
					<!-- Rellenar con las sesiones del espectáculo de la base de datos -->
					<%
						if(ids != null) {
							for(int i=0; i<ids.size(); i++) {
								String sesion = fechas.get(i).toString() + " " + horas.get(i).toString();
					%>
					<option value="<%=ids.get(i)%>"><%=sesion%></option>
					<%
							}
						}
					%>
				</select>
				
				<div>
					<input type="submit" value="Borrar" />
					<input type="button" value="Volver" onclick="window.location.href='menuAdministrador.jsp'">
				</div>
			</form>
		</div>
	</body>
</html>