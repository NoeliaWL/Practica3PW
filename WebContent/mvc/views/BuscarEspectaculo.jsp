<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "display.javabean.CustomerBean" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src = "js/jquery.js" ></script>

<script type="text/javascript">


function mostrarForm(valor)
	{
		document.getElementById('primero').style.visibility = "hidden";
		if (document.prim.selec[1].selected == true)
		{ 
			document.getElementById('titulo').style.visibility = "visible";
			document.getElementById('categoria').style.visibility = "hidden";
		}
		else
		{
		document.getElementById('categoria').style.visibility = "visible";
		document.getElementById('titulo').style.visibility = "hidden";
		}
}



</script>
<% CustomerBean usuario = (CustomerBean)session.getAttribute("customerBean");out.println("Login:" + usuario.getCorreoUser()); %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<h1>Buscar Espectáculo</h1>

	
<div id="primero">
<form id="prim" name="prim">
<select name="selec" onchange="javascript: mostrarForm('value');">
<option value ="vacio ">Selecciona el tipo de búsqueda</option>
<option value="0">Titulo</option>
<option value="1">Categoria</option>
</select>
<button type = "button" onclick = " window.location.href='/Practica3PW/mvc/views/menuEspectador.jsp'">Cancelar</button>
</form>
</div>

<div id="categoria" style="visibility:hidden;">
<form id="categoria" action = "/Practica3PW/BuscarEspectaculoCategoria" method = "post">
	<select  id = "categoria" name="categoria">
  		<option value = "vacio ">Selecciona el tipo de categoria</option>
    	<option value = "OBRADETEATRO">OBRADETEATRO</option>
        <option value = "CONCIERTO">CONCIERTO</option>
        <option value = "MONOLOGO">MONOLOGO</option>
   </select>
  
    <input type="submit" value = "Buscar">
	<input type="submit" value = "Cancelar">
	</form>
</div>

<div id="titulo" style="visibility:hidden;">
<form id="titulo" action="/Practica3PW/BuscarEspectaculoTitulo" method = "post">
<input type="text" name = "titulo" required>
<input type="submit" value = "Buscar">
<button type = "button" onclick = " window.location.href='/Practica3PW/mvc/views/BuscarEspectaculo.jsp'">Cancelar</button>
</form>
</div>
</body>
</html>