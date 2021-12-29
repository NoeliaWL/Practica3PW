<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.time.LocalDateTime" %>
<%@ page import="data.dao.AdministradoresDAO, data.dao.EspectadoresDAO" %>
<%@ page import="business.AdministradoresDTO, business.EspectadoresDTO" %>
<%@ page import="display.javabean.DatosConexionBean" %>
<jsp:useBean id="admins" class="display.javabean.AdministradoresBean" scope="session"></jsp:useBean>
<jsp:useBean id="especs" class="display.javabean.EspectadoresBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mi Aplicación Web</title>
	</head>
	<body>
		<%
			DatosConexionBean datos = DatosConexionBean.getInstance();
			AdministradoresDAO dao = new AdministradoresDAO();
			ArrayList<AdministradoresDTO> administradores = new ArrayList<AdministradoresDTO>();
			
			administradores = dao.listarUsuarios(datos);
			
			ArrayList<String> correos = new ArrayList<String>();
			ArrayList<String> nombres = new ArrayList<String>();
			ArrayList<String> apellidos = new ArrayList<String>();
			ArrayList<String> nicks = new ArrayList<String>();
			ArrayList<LocalDateTime> fechasRegistro = new ArrayList<LocalDateTime>();
			ArrayList<LocalDateTime> ultimasConexiones = new ArrayList<LocalDateTime>();
			
			for(AdministradoresDTO a : administradores) {
				correos.add(a.getCorreo());
				nombres.add(a.getNombre());
				apellidos.add(a.getApellidos());
				nicks.add(a.getNick());
				fechasRegistro.add(a.getFechaRegistro());
				ultimasConexiones.add(a.getUltimaConexion());
			}
			
			admins.setApellidos(apellidos);
			admins.setCorreos(correos);
			admins.setFechasRegistro(fechasRegistro);
			admins.setNicks(nicks);
			admins.setNombres(nombres);
			admins.setUltimasConexiones(ultimasConexiones);
			
			EspectadoresDAO daoEspec = new EspectadoresDAO();
			ArrayList<EspectadoresDTO> espectadores = new ArrayList<EspectadoresDTO>();
			
			espectadores = daoEspec.listarUsuarios(datos);
			
			correos = new ArrayList<String>();
			nombres = new ArrayList<String>();
			apellidos = new ArrayList<String>();
			nicks = new ArrayList<String>();
			fechasRegistro = new ArrayList<LocalDateTime>();
			ultimasConexiones = new ArrayList<LocalDateTime>();
			
			for(EspectadoresDTO e : espectadores) {
				correos.add(e.getCorreo());
				nombres.add(e.getNombre());
				apellidos.add(e.getApellidos());
				nicks.add(e.getNick());
				fechasRegistro.add(e.getFechaRegistro());
				ultimasConexiones.add(e.getUltimaConexion());
			}
			
			especs.setApellidos(apellidos);
			especs.setCorreos(correos);
			especs.setNicks(nicks);
			especs.setNombres(nombres);
			especs.setFechasRegistro(fechasRegistro);
			especs.setUltimasConexiones(ultimasConexiones);
		%>
		
			<jsp:forward page="../views/listarUsuarios.jsp"></jsp:forward>
	</body>
</html>