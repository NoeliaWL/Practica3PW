<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.common.Tipousuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
		
		<link href = "/Practica3PW/css/formulariosNoe.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<h1>Modificar datos usuario</h1>
		
		<form action="/Practica3PW/updateUsuario" method="post">
			<label for="nombre">Nombre:</label>
			<%
				String nombre = (String) session.getAttribute("nombre");
			%>
			<input type="text" name="nombre" value="<%=nombre%>"/>
			
			<label for="apellidos">Apellidos:</label>
			<%
				String apellidos = (String) session.getAttribute("apellidos");
			%>
			<input type="text" name="apellidos" value="<%=apellidos%>"/>
			
			<label for="nick">Nick:</label>
			<%
				String nick = (String) session.getAttribute("nick");
			%>
			<input type="text" name="nick" value="<%=nick%>"/>
			
			<label for="email">Email:</label>
			<%
				String email = (String) session.getAttribute("email");
			%>
			<input type="email" name="email" value="<%=email%>" disabled/>
			
			<label for="password">Contraseña:</label>
			<%
				String pass = (String) session.getAttribute("password");
			%>
			<input type="text" name="password" value="<%=pass%>"/>
			
			
			<%
				String tipoUsuario = (String) session.getAttribute("tipoUsuario");
				if(tipoUsuario.equals("ADMINISTRADOR")) {
			%>
			<select name="tipoUsuario" size="drop-down menu" disabled>
				<option value="vacio">Selecciona su tipo de usuario...</option>
				<option value="ADMINISTRADOR" selected>Administrador</option>
				<option value="ESPECTADOR">Espectador</option>
			</select>
			<%
				}
				else if(tipoUsuario.equals("ESPECTADOR")){
			%>
			<select name="tipoUsuario" size="drop-down menu" disabled>
				<option value="vacio">Selecciona su tipo de usuario...</option>
				<option value="ADMINISTRADOR">Administrador</option>
				<option value="ESPECTADOR" selected>Espectador</option>
			</select>
			<%
				}
			%>
			
			<div>
				<input type="submit" value="Modificar" />
				<input type="button" value="Cancelar" onclick="window.location.href='../../index.html'" />
			</div>
		</form>
	</body>
</html>