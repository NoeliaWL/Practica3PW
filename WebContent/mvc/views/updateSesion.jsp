<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link href = "/Practica3PW/css/formularios.css" type = "text/css" rel = "stylesheet"/>
	</head>
	<body>
		<h1>Modificar sesión de espectáculo</h1>
		
		<div>
			<form action="/Practica3PW/modificarsesion">
				<select name="titulo" size="drop-down menu">
					<option value="vacio">Selecciona el titulo del espectáculo...</option>
					<!-- Rellenar con los titulos de la base de datos -->
				</select>
				
				<select name="sesiones" size="drop-down menu">
					<option value="vacio">Selecciona la sesión a modificar...</option>
					<!-- Rellenar con las sesiones del espectáculo de la base de datos -->
				</select>
				
				<!-- Habilitar los campos siguientes después y tenerlos rellenos con los datos de la sesión a modificar -->
				<label for="fecha">Fecha de representación:</label>
				<input type="date" name="fecha" disabled/>
				
				<label for="hora">Hora de representación:</label>
				<input type="time" name="hora" disabled/>
				
				<!-- Botones -->
				<div>
					<input type="submit" value="Modificar" />
					<input type="button" value="Cancelar" onclick="window.location.href='menuAdministrador.jsp'">
				</div>
			</form>
		</div>
	</body>
</html>