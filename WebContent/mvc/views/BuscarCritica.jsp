<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="listaEspectaculos" scope="session"
	class="display.javabean.EspectaculosBean"></jsp:useBean>
<%@ page import="java.util.ArrayList"%>
<%@ page import="display.javabean.EspectaculosBean"%>
<%@ page import="display.javabean.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>
<link href="/Practica3PW/css/formulario.css" type="text/css"
	rel="stylesheet" />
</head>
<body>


	<h1 style="text-align: center;">Buscar Critica Espectáculo</h1>




	<% 
		ArrayList<String> listaTitulos = listaEspectaculos.getTitulo();
		

		%>

	<table id="mitabla"
		style="margin: 0 auto; text-align: center; width: 20%; border-collapse: separate; border-spacing: 10px 5px"
		border="1">
		<thead>
			<tr>
				<th>Espectaculo</th>
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







	<div id="critica">
		<form id="critica" action="/Practica3PW/BuscarCritica" method="post"
			style="margin-top: 50px">

			<label>Espectaculo:</label> <input type="text" name="espectaculo"
				class="input" required> <input type="submit" value="Buscar">

			<button type="button" class="button"
				onclick=" window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Cancelar</button>

		</form>
	</div>

</body>
</html>