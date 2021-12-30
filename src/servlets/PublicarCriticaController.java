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
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;
import display.javabean.EspectaculosBean;

@WebServlet(name = "/PublicarCriticaController", urlPatterns = "/PublicarCritica")
public class PublicarCriticaController extends HttpServlet {

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

		ArrayList<String> titulos = new ArrayList<String>();

		for (EspectaculosDTO e : espectaculosCategoria) {

			titulos.add(e.getTitulo());

		}

		EspectaculosBean espectaculosBean = new EspectaculosBean();

		espectaculosBean.setTitulo(titulos);

		request.getSession().setAttribute("listaEspectaculos", espectaculosBean);

		request.getRequestDispatcher("mvc/views/PublicarCriticaEspectaculo.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

		String critica = "", titulo = "", puntuacion = "";

		String categoria = "";

		if (request.getParameter("critica") != null) {
			critica = request.getParameter("critica");
		}

		if (request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}

		if (request.getParameter("puntuacion") != null) {
			puntuacion = request.getParameter("puntuacion");
		}

		if (request.getParameter("escape") != null) {
			categoria = request.getParameter("escape");
		}

		if (critica == "" && titulo == "" && puntuacion == "" && categoria == "") {
			response.sendRedirect("mvc/views/PublicarCriticaEspectaculo.jsp");
		}

		System.out.println(categoria);

		CriticasDAO criticas = new CriticasDAO();

		CriticasDTO criticaTitulo = new CriticasDTO();

		criticaTitulo.setResena(critica);

		criticaTitulo.setTitulo(titulo);

		criticaTitulo.setPuntuacion(Integer.parseInt(puntuacion));

		criticaTitulo.setEspectaculo(categoria);

		// System.out.println(criticaTitulo.getEspectaculo());

		HttpSession session = request.getSession();

		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");

		criticaTitulo.setPropietario(customerBean.getCorreoUser());

		int status = criticas.insertarCritica(criticaTitulo, datos);

		if (status == 1) {

			System.out.println("Critica publicada correctamente");

			response.sendRedirect("mvc/views/menuEspectador.jsp");

		} else

			System.out.println("Incorrecto");

	}
}
