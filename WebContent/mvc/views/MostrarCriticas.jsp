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
<link href = "/Practica3PW/css/tabla.css" type = "text/css" rel = "stylesheet"/>
</head>
<body>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>

	


	<% 	   ArrayList<String> listaTitulos = listaCriticas.getTitulo();
		
		   ArrayList<String> listaResenas = listaCriticas.getResena();
		   
		   ArrayList<String> listaPropietarios = listaCriticas.getPropietario();
		   
		   ArrayList<String> listaEspectaculos = listaCriticas.getEspectaculo();
		   
		   ArrayList<Integer> listaPuntuaciones = listaCriticas.getPuntuacion();
		   
		   
		%>
			
		<table id = "mitabla" border = "1">
			
			
			<thead>
				<tr>
					<th>Título</th>
					
					<th>Resena</th>
				
					<th>Espectaculo</th>
					
					<th>Puntuaciones</th>
					
					<th>Propietario</th>
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

<button type = "button" class ="input" onclick = " window.location.href='/Practica3PW/mvc/views/BuscarCritica.jsp'">Nueva Búsqueda</button>
<button type = "button" class = "input" onclick = " window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Menu Principal</button>



</body>
</html>