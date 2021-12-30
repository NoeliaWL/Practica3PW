<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "display.javabean.CustomerBean" %>
<jsp:useBean id = "listaCriticas" scope="session" class="display.javabean.CriticasBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href = "/Practica3PW/css/formulario.css" type = "text/css" rel = "stylesheet"/>

</head>
<body>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>

<h1 style = "text-align:center;">Valoraciones Critica</h1>


			
	<% 	   ArrayList<String> listaTitulos = listaCriticas.getTitulo();
		
		   ArrayList<String> listaResenas = listaCriticas.getResena();
		   
		   ArrayList<String> listaPropietarios = listaCriticas.getPropietario();
		   
		   ArrayList<String> listaEspectaculos = listaCriticas.getEspectaculo();
		   
		   ArrayList<Integer> listaPuntuaciones = listaCriticas.getPuntuacion();
		   
		   
		%>

	

	


			
		<table style=";margin: 0 auto;text-align:center;width:40%;border-collapse: separate; border-spacing: 10px 5px;" border = "1">
			<thead>
				<tr>
					<th>Título</th>
					
					<th>Resena</th>
				
					<th>Propietario</th>
					
					<th>Espectáculo</th>
					
					<th>Puntuación</th>
				</tr>
			</thead>
			
			<% 
				for(int i=0;i < listaTitulos.size();i++){
			%>
					<tr>
					<td><%= listaTitulos.get(i)%></td>
					<td><%= listaResenas.get(i)%></td>
					<td><%= listaEspectaculos.get(i)%></td>
					<td><%= listaPuntuaciones.get(i)%></td>
					<td><%= listaPropietarios.get(i)%></td>
					</tr>
			<% 
			}
			
			%>
</table>



<form id ="critica" action = "/Practica3PW/ValorarCritica" method = "post" style ="margin-top:50px;">

	<label for = "critica">Título:</label>

	<input type="text" name ="titulo" required>

	<label for = " propietario">Propietario:</label>

	<input type="text" name ="propietario" required>

	<label for = "puntuación">Puntuación:</label>

	<input type = "number" name ="puntuacion" max ="10" min = "0">

	<input type = "submit" value = "Enviar">
	
	<button type = "button" class = "button" onclick = " window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Cancelar</button>


	</form>

</body>
</html>