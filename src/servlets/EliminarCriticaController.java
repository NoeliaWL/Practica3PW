package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.CriticasDTO;
import business.EspectaculosDTO;
import data.dao.CriticasDAO;
import data.dao.EspectaculosDAO;
import display.javabean.CriticasBean;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;
import display.javabean.EspectaculosBean;

@WebServlet(name = "/EliminarCriticaController", urlPatterns = "/EliminarCritica")
public class EliminarCriticaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		DatosConexionBean datos = DatosConexionBean.getInstance();
		if (datos.getDRIVER().equals("")) {
			datos.setDRIVER(getServletContext().getInitParameter("DRIVER"));
			datos.setURL(getServletContext().getInitParameter("URL"));
			datos.setUSUARIO(getServletContext().getInitParameter("USUARIO"));
			datos.setPASSWORD(getServletContext().getInitParameter("PASSWORD"));
			ServletContext application = request.getServletContext();
			InputStream s = application.getResourceAsStream(getServletContext().getInitParameter("SQLproperties"));
			datos.setFichero(s);
		}

		EspectaculosDAO espectaculo = new EspectaculosDAO();

		ArrayList<EspectaculosDTO> espectaculosCategoria = espectaculo.listarEspectaculos(datos);

		ArrayList<String> titulosEspectaculo = new ArrayList<String>();

		for (EspectaculosDTO e : espectaculosCategoria) {

			titulosEspectaculo.add(e.getTitulo());

		}

		EspectaculosBean espectaculosBean = new EspectaculosBean();

		espectaculosBean.setTitulo(titulosEspectaculo);

		request.getSession().setAttribute("listaEspectaculos", espectaculosBean);

		CriticasDAO criticas = new CriticasDAO();

		ArrayList<CriticasDTO> listaCriticas = criticas.cargarDatosCriticas(datos);

		ArrayList<String> titulosCritica = new ArrayList<String>();

		for (CriticasDTO e : listaCriticas) {
			titulosCritica.add(e.getTitulo());

		}

		CriticasBean criticasBean = new CriticasBean();

		criticasBean.setTitulo(titulosCritica);
		criticasBean.setEspectaculo(titulosEspectaculo);

		request.getSession().setAttribute("listaCriticas", criticasBean);

		request.getRequestDispatcher("mvc/views/EliminarCritica.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		DatosConexionBean datos = DatosConexionBean.getInstance();
		if (datos.getDRIVER().equals("")) {
			datos.setDRIVER(getServletContext().getInitParameter("DRIVER"));
			datos.setURL(getServletContext().getInitParameter("URL"));
			datos.setUSUARIO(getServletContext().getInitParameter("USUARIO"));
			datos.setPASSWORD(getServletContext().getInitParameter("PASSWORD"));
			ServletContext application = request.getServletContext();
			InputStream s = application.getResourceAsStream(getServletContext().getInitParameter("SQLproperties"));
			datos.setFichero(s);
		}

		String titulo = "";

		if (request.getParameter("eliminar") != null) {
			titulo = request.getParameter("eliminar");

		}

		if (titulo == "") {
			response.sendRedirect("mvc/views/EliminarCritica.jsp");

		}

		CriticasDAO criticas = new CriticasDAO();

		CriticasDTO criticaTitulo = new CriticasDTO();

		criticaTitulo.setTitulo(titulo);

		HttpSession session = request.getSession();

		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

		criticaTitulo.setPropietario(customerBean.getCorreoUser());

		int status = criticas.eliminarCriticaEspectaculo(criticaTitulo, datos);

		if (status == 1) {

			System.out.println("Critica eliminada correctamente");

			response.sendRedirect("mvc/views/menuEspectador.jsp");

		} else

			System.out.println("Error al eliminar la critica");

	}

}
