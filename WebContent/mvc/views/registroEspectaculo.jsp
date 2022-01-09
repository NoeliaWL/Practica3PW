<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
		
		<link href = "/Practica3PW/css/formulariosNoe.css" type = "text/css" rel = "stylesheet"/>
		<script type="text/javascript" src="/Practica3PW/js/registroEspectaculo.js"></script>
	</head>
	<body>
		<h1>Formulario de espectáculo</h1>
		
		<form action="/Practica3PW/altaespectaculo" method="post">
			<label for="titulo">Titulo:</label>
			<input type="text" id="titulo" name="titulo" required/>
			
			<label for="descripcion">Descripción:</label>
			<textarea id="descripcion" name="descripcion" required></textarea>
			
			<select name="categoria" id="categoria" size="drop-down menu">
				<option value="vacio">Selecciona la categoria del espectaculo...</option>
				<option value="MONOLOGO">Monologo</option>
				<option value="CONCIERTO">Concierto</option>
				<option value="OBRADETEATRO">Obra de teatro</option>
			</select>
			
			<select name="tipo" id="tipo" size="drop-down menu" onchange="habilitarSesiones(this)">
				<option value="vacio">Selecciona el tipo del espectaculo...</option>
				<option value="PUNTUAL">Espectáculo puntual</option>
				<option value="PASEMULTIPLE">Espectáculo de pase multiple</option>
				<option value="TEMPORADA">Espectáculo de temporada</option>
			</select>
			
			<label for="entradas">Selecciona el número de entradas del espectáculo para cada sesión:</label>
			<input type="number" min=0 max=5000 id="entradas" name="entradas" disabled/>
			
			<label for="hora">Selecciona la hora de representación:</label>
			<input type="time" id="horaInput" step="1" name="hora" disabled/>
			
			<label for="sesion">Seleccionar sesión....</label>
			<input type="date" id="puntualSesion" name="sesion" disabled/>
			
			<label for="fechaInicio">Seleccionar fecha de inicio:</label>
			<input type="date" id="inicioTemporada" name="fechaInicio" disabled/>
			
			<label for="fechaFin">Seleccionar fecha de fin:</label>
			<input type="date" id="finTemporada" name="fechaFin" disabled/>
			
			<div>
				<input type="submit" value="Registrar" />
				<input type="button" value="Cancelar" onclick="window.location.href='menuAdministrador.jsp'" />
			</div>
		</form>
	</body>
</html>