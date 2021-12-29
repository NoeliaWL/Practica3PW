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
import data.common.Tipousuario;
import data.dao.AdministradoresDAO;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;

@WebServlet(name="loginAdministradorController", urlPatterns="/comprobarLoginAdministrador")
public class loginAdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = "", password = "";
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}
		
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		if(email == "" && password == "") {
			response.sendRedirect("mvc/views/loginAdministradores.jsp");
		}
	}
	
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
		
		String email = "", password = "";
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		}
		
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		if(email == "" && password == "") {
			response.sendRedirect("mvc/views/loginAdministradores.jsp");
		}
		
		AdministradoresDTO administrador = new AdministradoresDTO();
		administrador.setCorreo(email);
		administrador.setContrasena(password);
		
		AdministradoresDAO dao = new AdministradoresDAO();
		AdministradoresDTO adminBD = new AdministradoresDTO();
		adminBD = dao.consultarUsuario(administrador, datos);
		
		if(adminBD.getCorreo().equals(administrador.getCorreo())) {
			if(adminBD.getContrasena().equals(administrador.getContrasena())) {
				HttpSession session = request.getSession();
				CustomerBean customerBean = new CustomerBean();
				
				customerBean.setCorreoUser(adminBD.getCorreo());
				customerBean.setTipo(Tipousuario.ADMINISTRADOR);
				
				session.setAttribute("customerBean", customerBean);
				
				administrador.setUltimaConexion(LocalDateTime.now());
				int status = dao.actualizarUltimaConexion(administrador, datos);
				if(status == 1) {
					System.out.println("Fecha de ultima conexion actualizada correctamente");
				}
				else {
					System.out.println("Error al actualizar la fecha de última conexión");
				}
				
				System.out.println("Login correcto");
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
			else {
				System.out.println("Contraseña incorrecta");
				response.sendRedirect("mvc/views/loginAdministradores.jsp");
			}
		}
		else {
			//Usuario no registrado - Enviar a index.html
			System.out.println("Usuario no registrado");
			response.sendRedirect("index.html");
		}
	}
}