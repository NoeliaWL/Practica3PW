<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
		
		<link href = "/Practica3PW/css/tablas.css" type = "text/css" rel = "stylesheet"/>
		<link href = "/Practica3PW/css/formulariosNoe.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<%
			List<String> espectaculosTitulos = (List<String>) session.getAttribute("Espectaculostitulos");
			List<String> espectaculosTipos = (List<String>) session.getAttribute("Espectaculostipos");
		%>
		<h1>Añadir sesión a un espectáculo</h1>
		
		<table id="espectaculos">
			<caption>
				ESPECTACULOS
			</caption>
			
			<thead>
				<tr>
					<th>Titulo</th>
					<th>Tipo</th>
				</tr>
			</thead>
			
			<tbody>
				<%
					for(int i=0; i<espectaculosTitulos.size(); i++) {
				%>
						<tr>
							<td><%=espectaculosTitulos.get(i)%></td>
							<td><%=espectaculosTipos.get(i)%></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
		
		<form action="/Practica3PW/altasesion" method="post">
			<label for="titulo">Introduce el titulo del espectáculo para añadir la sesión:</label>
			<%
				String tit = (String) session.getAttribute("tituloEspectaculo");
				if(tit != null) {
			%>
					<input type="text" name="titulo" value="<%=tit%>" disabled/>
			<%
				}
				else {
			%>
					<input type="text" name="titulo" value=""/>
			<%
				}
			%>
			
			<label for="sesion">Selecciona la sesión a añadir:</label>
			<input type="date" name="sesion"/>
			
			<label for="hora">Selecciona la hora de representación:</label>
			<input type="time" id="horaInput" step="1" name="hora"/>
			
			<label for="entradas">Selecciona el número de entradas del espectáculo para cada sesión:</label>
			<input type="number" min=0 max=5000 id="entradas" name="entradas"/>
			
			<div>
				<input type="submit" value="Añadir" />
				<input type="button" value="Volver" onclick="window.location.href='menuAdministrador.jsp'" />
			</div>
		</form>
	</body>
</html>