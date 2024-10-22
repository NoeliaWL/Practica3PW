<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mi Aplicación Web</title>
		
		<link href = "/Practica3PW/css/menus.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<h1>Menú Espectadores</h1>
		
		<div>
			<button type="button" onclick="window.location.href='/Practica3PW/mvc/views/BuscarEspectaculo.jsp'">1. Buscar espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/PublicarCritica'">2. Publicar crítica de espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/BuscarCritica'">3. Consultar críticas de espectáculos</button>
			<button type="button" onclick="window.location.href='/Practica3PW/EliminarCritica'">4. Eliminar crítica de espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/ValorarCritica'">5. Valorar crítica de otro usuario</button>
		</div>
		
		<div>
			<button type="button" onclick="window.location.href='/Practica3PW/updateUsuario'">Modificar datos usuario</button>
			<button type="button" onclick="window.location.href='/Practica3PW/bajausuario'">Darse de baja</button>
			<button type="button" onclick="window.location.href='../controller/logoutController.jsp'">Cerrar Sesión</button>
		</div>
	</body>
</html>