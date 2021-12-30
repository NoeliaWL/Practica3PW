<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "display.javabean.CustomerBean" %>
<jsp:useBean id = "listaEspectaculos" scope="session" class="display.javabean.EspectaculosBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href = "/Practica3PW/css/formulario.css" type = "text/css" rel = "stylesheet"/>

<title>Publicar Critica</title>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>





</head>
<body>
		

	
		
	<h1 style = "text-align:center;">Publicar Crítica Espectáculo</h1>
	
		<% 
		ArrayList<String> listaTitulos = listaEspectaculos.getTitulo();
		

		%>
		
		<table id="mitabla" style=";margin: 0 auto;text-align:center;width:20%;border-collapse: separate; border-spacing: 10px 5px;" border="1">
			<thead>
				<tr>
					<th>Espectaculos</th>
				</tr>
			</thead>
				
			
			
	
			<% 
				for(int i=0;i < listaTitulos.size();i++){
			%>
					<tr>
					<td><%= listaTitulos.get(i)%></td>
				   </tr>
					
			<% 
				}
				
		   %>
		
	</table>

		
			
			
		
		
	
	<form id ="critica" action = "/Practica3PW/PublicarCritica" method = "post" style="margin-top:50px">
		
		<label for="Espectaculo">Espectaculo:</label>
		
		<input type="text" name="escape" required>
		
		<label for ="Titulo">Titulo:</label>
	
		<input type="text" name="titulo" required>
		
		<label for="Critica">Critica:</label>
		
		<textarea id = "critica" name = "Critica" maxlength="250" required></textarea>
		
		
		<label for ="Puntuacion">Puntuacion:</label>
		
		<input type="number" name = "puntuacion" min="0" max="10">
		
		<input type = "submit" value ="Enviar">
		
		<button type = "button" class = "button" onclick = " window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Cancelar</button>
		
</form>
	

</body>
</html>