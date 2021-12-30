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
		<h1>Menú Administradores</h1>

		<div>
			<button type="button" onclick="window.location.href='../controller/listarUsuariosController.jsp'">1. Listado de usuarios</button>
			<button type="button" onclick="window.location.href='/Practica3PW/altaespectaculo'">2. Alta de espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/altasesion'">3. Añadir sesión de espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/modificarsesion'">4. Modificar sesión de espectáculo</button>
			<button type="button" onclick="window.location.href='/Practica3PW/cancelarsesion'">5. Cancelar sesión de espectáculo</button>
		</div>
		
		<div>
			<button type="button" onclick="window.location.href='/Practica3PW/updateUsuario'">Modificar datos usuario</button>
			<button type="button" onclick="window.location.href='/Practica3PW/bajausuario'">Darse de baja</button>
			<button type="button" onclick="window.location.href='../controller/logoutController.jsp'">Cerrar Sesión</button>
		</div>
	</body>
</html>