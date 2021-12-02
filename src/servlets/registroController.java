package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.AdministradoresDTO;
import business.EspectadoresDTO;
import data.common.Tipousuario;
import data.dao.AdministradoresDAO;
import data.dao.EspectadoresDAO;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;

@WebServlet(name="registroController", urlPatterns="/registrarUsuario")
public class registroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
		if(datos.getDRIVER().equals("")) {
			datos.setDRIVER(getServletContext().getInitParameter("DRIVER"));
			datos.setURL(getServletContext().getInitParameter("URL"));
			datos.setUSUARIO(getServletContext().getInitParameter("USUARIO"));
			datos.setPASSWORD(getServletContext().getInitParameter("PASSWORD"));
			ServletContext application = request.getServletContext();
			InputStream s = application.getResourceAsStream(getServletContext().getInitParameter("SQLproperties"));
			datos.setFichero(s);
		}
		
		String nombre = "", apellidos = "", nick = "", email = "", password = "", tipoUsuario = "";
		if(request.getParameter("nombre") != null) {
			nombre = request.getParameter("nombre");
		}
		
		if(request.getParameter("apellidos") != null) {
			apellidos = request.getParameter("apellidos");
		}
		
		if(request.getParameter("nick") != null) {
			nick = request.getParameter("nick");
		}
		
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}
		
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		if(request.getParameter("tipoUsuario") != null && request.getParameter("tipoUsuario") != "vacio") {
			tipoUsuario = request.getParameter("tipoUsuario");
		}
		
		if(tipoUsuario.equals("ADMINISTRADOR")) {
			AdministradoresDTO administrador = new AdministradoresDTO();
			AdministradoresDAO adminDao = new AdministradoresDAO();
			
			administrador.setNombre(nombre);
			administrador.setApellidos(apellidos);
			administrador.setNick(nick);
			administrador.setCorreo(email);
			administrador.setContrasena(password);
			administrador.setFechaRegistro(LocalDateTime.now());
			
			int resultado = 0;
			resultado = adminDao.registrarUsuario(administrador, datos);
			
			if(resultado == 1) {
				HttpSession session = request.getSession();
				CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			
				if(customerBean == null) {
					customerBean = new CustomerBean();
				}
				
				customerBean.setCorreoUser(administrador.getCorreo());
				customerBean.setTipo(Tipousuario.ADMINISTRADOR);
				
				System.out.println("Usuario registrado");
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
			else {
				System.out.println("Usuario no registrado");
				response.sendRedirect("../../index.html");
			}
		}
		else if(tipoUsuario.equals("ESPECTADOR")) {
			EspectadoresDTO espectador = new EspectadoresDTO();
			EspectadoresDAO espectadorDao = new EspectadoresDAO();
			
			espectador.setNombre(nombre);
			espectador.setApellidos(apellidos);
			espectador.setNick(nick);
			espectador.setCorreo(email);
			espectador.setContrasena(password);
			espectador.setFechaRegistro(LocalDateTime.now());
			
			int resultado = 0;
			resultado = espectadorDao.registrarUsuario(espectador, datos);
			
			if(resultado == 1) {
				HttpSession session = request.getSession();
				CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			
				if(customerBean == null) {
					customerBean = new CustomerBean();
				}
				
				customerBean.setCorreoUser(espectador.getCorreo());
				customerBean.setTipo(Tipousuario.ADMINISTRADOR);
				
				System.out.println("Usuario registrado");
				response.sendRedirect("mvc/views/menuEspectador.jsp");
			}
			else {
				System.out.println("Usuario no registrado");
				response.sendRedirect("../index.html");
			}
		}
		else {
			System.out.println("Introducir el tipo de usuario.");
		}
	}
}