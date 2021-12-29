package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.AdministradoresDTO;
import data.dao.AdministradoresDAO;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;

@WebServlet(name="bajaUsuarioController", urlPatterns="/bajausuario")
public class bajaUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//2. Llamar función DAO para eliminar usuario
		//3. Cerrar sesión y volver a inicio
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		DatosConexionBean datos = DatosConexionBean.getInstance();
		AdministradoresDAO dao = new AdministradoresDAO();
		AdministradoresDTO admin = new AdministradoresDTO();
		admin.setCorreo(customerBean.getCorreoUser());
		
		int status = dao.borrarUsuario(admin, datos);
		
		if(status == 1) {
			response.sendRedirect("index.html");
		}
		else {
			System.out.println("Error al dar de baja al usuario");
			response.sendRedirect("mvc/views/menuAdministrador.jsp");
		}
	}
}