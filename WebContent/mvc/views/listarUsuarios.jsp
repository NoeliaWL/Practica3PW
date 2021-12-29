<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.time.LocalDateTime" %>
<jsp:useBean id="admins" class="display.javabean.AdministradoresBean" scope="session"></jsp:useBean>
<jsp:useBean id="especs" class="display.javabean.EspectadoresBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
		
		<link href = "/Practica3PW/css/tablas.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<%
			ArrayList<String> correos = admins.getCorreos();
			ArrayList<String> nombres = admins.getNombres();
			ArrayList<String> apellidos = admins.getApellidos();
			ArrayList<String> nicks = admins.getNicks();
			ArrayList<LocalDateTime> fechasRegistros = admins.getFechasRegistro();
			ArrayList<LocalDateTime> ultimasConexiones = admins.getUltimasConexiones();
		%>
			<h1>Lista de usuarios</h1>
			
			<table>
				<caption>
					USUARIOS ADMINISTRADORES
				</caption>
				
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Nick</th>
						<th>Correo</th>
						<th>Fecha de registro</th>
						<th>Última Conexión</th>
					</tr>
				</thead>
				
				<tbody>
		<%
			for(int i=0; i<correos.size(); i++) {
				String aux = "";
				if(ultimasConexiones.get(i) == null) {
					aux = "No conectado nunca";
				}
				else{
					aux = ultimasConexiones.get(i).toString();
				}
		%>
				<tr>
					<td><%=nombres.get(i)%></td>
					<td><%=apellidos.get(i)%></td>
					<td><%=nicks.get(i)%></td>
					<td><%=correos.get(i)%></td>
					<td><%=fechasRegistros.get(i)%></td>
					<td><%=aux%></td>
				</tr>
		<%
			}
		%>
				</tbody>
			</table>
		<%
			correos = new ArrayList<String>();
			nombres = new ArrayList<String>();
			apellidos = new ArrayList<String>();
			nicks = new ArrayList<String>();
			fechasRegistros = new ArrayList<LocalDateTime>();
			ultimasConexiones = new ArrayList<LocalDateTime>();
			
			correos = especs.getCorreos();
			nombres = especs.getNombres();
			apellidos = especs.getApellidos();
			nicks = especs.getNicks();
			fechasRegistros = especs.getFechasRegistro();
			ultimasConexiones = especs.getUltimasConexiones();
		%>
			<table>
				<caption>
					USUARIOS ESPECTADORES
				</caption>
				
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Nick</th>
						<th>Correo</th>
						<th>Fecha de registro</th>
						<th>Última Conexión</th>
					</tr>
				</thead>
				
				<tbody>
		<%
			for(int i=0; i<correos.size(); i++) {
				String aux = "";
				if(ultimasConexiones.get(i) == null) {
					aux = "No conectado nunca";
				}
				else{
					aux = ultimasConexiones.get(i).toString();
				}
		%>
				<tr>
					<td><%=nombres.get(i)%></td>
					<td><%=apellidos.get(i)%></td>
					<td><%=nicks.get(i)%></td>
					<td><%=correos.get(i)%></td>
					<td><%=fechasRegistros.get(i)%></td>
					<td><%=aux%></td>
				</tr>
		<%
			}
		%>
				</tbody>
			</table>
			
			<button type="button" onclick="window.location.href='../views/menuAdministrador.jsp'">Volver al menú</button>
	</body>
</html>