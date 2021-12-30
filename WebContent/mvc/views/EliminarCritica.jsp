<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<jsp:useBean id="listaCriticas" scope="session"
	class="display.javabean.CriticasBean"></jsp:useBean>
<%@ page import="display.javabean.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/Practica3PW/css/formulario.css" type="text/css"
	rel="stylesheet" />
</head>
<body>
	<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>


	<h1 style="text-align: center;">Eliminar Critica Espectáculo</h1>



	<% 
		ArrayList<String> listaTitulos = listaCriticas.getTitulo();
		ArrayList<String> listaEspectaculos = listaCriticas.getEspectaculo();
	
		%>





	<table id="mitabla"
		style="margin: 0 auto; text-align: center; width: 20%; border-collapse: separate; border-spacing: 10px 5px"
		border="1">

		<thead>
			<tr>
				<th>Criticas</th>
			</tr>

		</thead>


		<% 
				for(int i=0;i < listaTitulos.size();i++){
				%>
		<tr>
			<td><%=listaTitulos.get(i)%></td>


		</tr>
		<% 
				
				}
				%>




	</table>




	<div id="critica">
		<form id="critica" action="EliminarCritica" method="post"
			style="margin-top: 50px">

			<label for="titulo">Titulo:</label> <input type="text"
				name="eliminar" required> <input type="submit"
				value="Eliminar Critica">

			<button type="button" class="button"
				onclick=" window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Cancelar</button>

		</form>
	</div>


</body>
</html>