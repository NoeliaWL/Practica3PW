package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.EspectadoresDTO;
import data.common.Tipousuario;
import data.dao.EspectadoresDAO;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;

@WebServlet(name="loginEspectadorController", urlPatterns="/comprobarLoginEspectador")
public class loginEspectadorController extends HttpServlet {
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
			response.sendRedirect("mvc/views/loginEspectadores.jsp");
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
			response.sendRedirect("mvc/views/loginEspectador.jsp");
		}
		
		EspectadoresDTO espectador = new EspectadoresDTO();
		espectador.setCorreo(email);
		espectador.setContrasena(password);
		
		EspectadoresDAO dao = new EspectadoresDAO();
		EspectadoresDTO adminBD = new EspectadoresDTO();
		adminBD = dao.consultarUsuario(espectador, datos);
		
		if(adminBD.getCorreo().equals(espectador.getCorreo())) {
			if(adminBD.getContrasena().equals(espectador.getContrasena())) {
				HttpSession session = request.getSession();
				CustomerBean customerBean = new CustomerBean();
				
				customerBean.setCorreoUser(adminBD.getCorreo());
				customerBean.setTipo(Tipousuario.ADMINISTRADOR);
				
				session.setAttribute("customerBean", customerBean);
				
				System.out.println("Login correcto");
				response.sendRedirect("mvc/views/menuEspectador.jsp");
			}
			else {
				System.out.println("Contraseña incorrecta");
				response.sendRedirect("mvc/views/loginEspectadores.jsp");
			}
		}
		else {
			//Usuario no registrado - Enviar a index.html
			System.out.println("Usuario no registrado");
			response.sendRedirect("index.html");
		}
	}
}