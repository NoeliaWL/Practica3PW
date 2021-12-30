package servlets;

import java.io.IOException;

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

@WebServlet(name="updateController", urlPatterns="/updateUsuario")
public class updateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatosConexionBean datos = DatosConexionBean.getInstance();
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		if(customerBean.getTipo().equals(Tipousuario.ADMINISTRADOR)) {
			AdministradoresDTO admin = new AdministradoresDTO();
			AdministradoresDAO dao = new AdministradoresDAO();
			
			admin.setCorreo(customerBean.getCorreoUser());
			
			admin = dao.consultarUsuario(admin, datos);
			
			session.setAttribute("nombre", admin.getNombre());
			session.setAttribute("apellidos", admin.getApellidos());
			session.setAttribute("nick", admin.getNick());
			session.setAttribute("email", admin.getCorreo());
			session.setAttribute("password", admin.getContrasena());
			session.setAttribute("tipoUsuario", customerBean.getTipo().toString());
		}
		else if(customerBean.getTipo().equals(Tipousuario.ESPECTADOR)) {
			EspectadoresDTO espec = new EspectadoresDTO();
			EspectadoresDAO dao = new EspectadoresDAO();
			
			espec.setCorreo(customerBean.getCorreoUser());
			
			espec = dao.consultarUsuario(espec, datos);
			
			session.setAttribute("nombre", espec.getNombre());
			session.setAttribute("apellidos", espec.getApellidos());
			session.setAttribute("nick", espec.getNick());
			session.setAttribute("email", espec.getCorreo());
			session.setAttribute("password", espec.getContrasena());
			session.setAttribute("tipoUsuario", customerBean.getTipo().toString());
		}
		
		response.sendRedirect("mvc/views/update.jsp");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
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
		
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		email = customerBean.getCorreoUser();
		tipoUsuario = customerBean.getTipo().toString();
				
		if(tipoUsuario.equals("ADMINISTRADOR")) {
			AdministradoresDTO administrador = new AdministradoresDTO();
			AdministradoresDAO adminDao = new AdministradoresDAO();
			
			administrador.setNombre(nombre);
			administrador.setApellidos(apellidos);
			administrador.setNick(nick);
			administrador.setCorreo(email);
			administrador.setContrasena(password);
			
			int status = adminDao.actualizarUsuario(administrador, datos);
			if(status == 1) {
				System.out.println("Usuario modificado correctamente");
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
			else {
				System.out.println("Error al modificar datos usuario");
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
		}
		else if(tipoUsuario.equals("ESPECTADOR")) {
			EspectadoresDTO espectador = new EspectadoresDTO();
			EspectadoresDAO especDao = new EspectadoresDAO();
			
			espectador.setNombre(nombre);
			espectador.setApellidos(apellidos);
			espectador.setNick(nick);
			espectador.setCorreo(email);
			espectador.setContrasena(password);
			
			int status = especDao.actualizarUsuario(espectador, datos);
			if(status == 1) {
				System.out.println("Usuario modificado correctamente");
				response.sendRedirect("mvc/views/menuEspectador.jsp");
			}
			else {
				System.out.println("Error al modificar datos usuario");
				response.sendRedirect("mvc/views/menuEspectador.jsp");
			}
		}
	}
}
