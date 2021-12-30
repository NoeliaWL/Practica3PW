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
import business.ValoracionesDTO;
import data.dao.CriticasDAO;
import data.dao.ValoracionesDAO;
import display.javabean.CriticasBean;
import display.javabean.DatosConexionBean;

@WebServlet(name = "/ValoracionesCriticasController", urlPatterns = "/ValorarCritica")
public class valoracionesCriticaController extends HttpServlet {

	/**
	 * 
	 */
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

		CriticasDAO criticas = new CriticasDAO();

		ArrayList<CriticasDTO> listaCriticas = criticas.cargarDatosCriticas(datos);

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

		request.getRequestDispatcher("mvc/views/valoracionesCriticas.jsp").forward(request, response);

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

		String titulo = "", propietario = "", puntuacion = "";

		if (request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}

		if (request.getParameter("propietario") != null) {
			propietario = request.getParameter("propietario");
		}

		if (request.getParameter("puntuacion") != null) {
			puntuacion = request.getParameter("puntuacion");
		}

		if (titulo == "" && propietario == "" && puntuacion == "") {
			response.sendRedirect("mvc/views/valoracionsCriticaController.jsp");
		}

		ValoracionesDAO valoracion = new ValoracionesDAO();

		ValoracionesDTO valoracionDTO = new ValoracionesDTO();

		valoracionDTO.setCriticasTitulo(titulo);

		valoracionDTO.setPropietario(propietario);

		valoracionDTO.setPuntuacion(Integer.parseInt(puntuacion));

		// HttpSession session = request.getSession();

		// CustomerBean customerBean = (CustomerBean)
		// session.getAttribute("customerBean");

		int status = valoracion.insertarValoracion(valoracionDTO, datos);

		if (status == 1) {

			System.out.println("valoracion publicada correctamente");

			response.sendRedirect("mvc/views/menuEspectador.jsp");

		} else

			System.out.println("Incorrecto");

	}

}
