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

import business.CriticasDTO;
import business.EspectaculosDTO;
import data.dao.CriticasDAO;
import data.dao.EspectaculosDAO;
import display.javabean.CriticasBean;
import display.javabean.DatosConexionBean;
import display.javabean.EspectaculosBean;

@WebServlet(name = "/BuscarCriticaController", urlPatterns = "/BuscarCritica")
public class BuscarCriticaController extends HttpServlet {

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

		request.getRequestDispatcher("mvc/views/BuscarCritica.jsp").forward(request, response);

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

		String critica = "";

		if (request.getParameter("espectaculo") != null) {
			critica = request.getParameter("espectaculo");
		}

		if (critica == "") {
			response.sendRedirect("mvc/views/BuscarCritica.jsp");
		}

		else {

			System.out.println(critica);

			CriticasDAO criticas = new CriticasDAO();

			CriticasDTO espectaculos = new CriticasDTO();

			espectaculos.setEspectaculo(critica);

			ArrayList<CriticasDTO> listaCriticas = criticas.ConsultarCriticasEspectaculo(espectaculos, datos);

			ArrayList<String> titulo = new ArrayList<String>();

			ArrayList<String> resena = new ArrayList<String>();

			ArrayList<String> propietario = new ArrayList<String>();

			ArrayList<String> espectaculo = new ArrayList<String>();

			ArrayList<Integer> puntuacion = new ArrayList<Integer>();

			for (CriticasDTO e : listaCriticas) {

				titulo.add(e.getTitulo());
				resena.add(e.getResena());
				propietario.add(e.getPropietario());
				espectaculo.add(e.getEspectaculo());
				puntuacion.add(e.getPuntuacion());

			}

			CriticasBean criticasBean = new CriticasBean();

			criticasBean.setTitulo(titulo);
			criticasBean.setResena(resena);
			criticasBean.setPropietario(propietario);
			criticasBean.setEspectaculo(espectaculo);
			criticasBean.SetPuntuacion(puntuacion);
			request.getSession().setAttribute("listaCriticas", criticasBean);

			response.sendRedirect("mvc/views/MostrarCriticas.jsp");

		}

	}

}
