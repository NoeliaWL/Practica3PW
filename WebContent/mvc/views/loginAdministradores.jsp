<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mi Aplicación Web</title>
	</head>
	<body>
		<h1>Inicio sesión Administrador</h1>

		<form action="/Practica3PW/comprobarLoginAdministrador" method="post">
			<label for="email">Email:</label>
			<input type="email" name="email" />
			
			<label for="password">Contraseña:</label>
			<input type="password" name="password" />
			
			<input type="submit" value="Iniciar Sesión" />
			<input type="button" value="Cancelar" onclick="window.location.href='../../index.html'" />
		</form>
	</body>
</html>