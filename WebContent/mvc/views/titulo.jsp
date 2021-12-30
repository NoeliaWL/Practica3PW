<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "display.javabean.EspectaculosBean" %>
<jsp:useBean id = "listaEspectaculos" scope="session" class="display.javabean.EspectaculosBean"></jsp:useBean>
<%@ page import = "display.javabean.CustomerBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "/Practica3PW/css/tabla.css" type = "text/css" rel = "stylesheet"/>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("idUsuario:" + usuario.getCorreoUser()); %>
		<title>Insert titulo</title>
	</head>
	<body>
		
		
		
		
		
		<% ArrayList<String> listaTitulos = listaEspectaculos.getTitulo();
		
		   ArrayList<String> listaDescripcion = listaEspectaculos.getDescripcion();
		   
		   ArrayList<String> listaCategoria = listaEspectaculos.getCategoria();
		   
		   ArrayList<String> listaTipos = listaEspectaculos.getTipo();
		   
		   ArrayList<String> listaPropietarios = listaEspectaculos.getPropietario();
		   
		   
		%>
			
		<table id = "mitabla" border = "1">
			<thead>
				<tr>
					<th>Titulo</th>
					
					<th>Descripcion</th>
				
					<th>Categoria</th>
					
					<th>Tipo</th>
				</tr>
			</thead>
			
		
			<% 
				for(int i=0;i < listaTitulos.size();i++){
			%>
					<tr>
					<td><%= listaTitulos.get(i)%></td>
					<td><%= listaDescripcion.get(i)%></td>
					<td><%= listaCategoria.get(i)%></td>
					<td><%= listaTipos.get(i)%></td>
					
					</tr>
					
			<% 
				}
				
			%>
			
					
		
			
		
		
			
			
</table>


<button type = "button" class ="input" onclick = " window.location.href='/Practica3PW/mvc/views/BuscarEspectaculo.jsp'">Nueva Búsqueda</button>
<button type = "button" class = "input" onclick = " window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Menu Principal</button>

</body>
</html>