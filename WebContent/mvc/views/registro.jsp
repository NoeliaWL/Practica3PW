<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
	</head>
	<body>
		<h1>Formulario de registro</h1>
		
		<form action="/Practica3PW/registrarUsuario" method="post">
			<label for="nombre">Nombre:</label>
			<input type="text" name="nombre" />
			
			<label for="apellidos">Apellidos:</label>
			<input type="text" name="apellidos" />
			
			<label for="nick">Nick:</label>
			<input type="text" name="nick" />
			
			<label for="email">Email:</label>
			<input type="email" name="email" />
			
			<label for="password">Contraseña:</label>
			<input type="password" name="password" />
			
			<select name="tipoUsuario" size="drop-down menu">
				<option value="vacio">Selecciona su tipo de usuario...</option>
				<option value="ADMINISTRADOR">Administrador</option>
				<option value="ESPECTADOR">Espectador</option>
			</select>
			
			<input type="submit" value="Registrar" />
			<input type="button" value="Cancelar" onclick="window.location.href='../../index.html'" />
		</form>
	</body>
</html>