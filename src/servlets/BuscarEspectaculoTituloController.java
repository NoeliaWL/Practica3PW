package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.EspectaculosDTO;
import data.dao.EspectaculosDAO;
import display.javabean.EspectaculosBean;

import display.javabean.DatosConexionBean;

@WebServlet(name = "BuscarEspectaculoTituloController", urlPatterns = "/BuscarEspectaculoTitulo")
public class BuscarEspectaculoTituloController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

		if (request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}

		if (titulo == "") {
			response.sendRedirect("mvc/views/BuscarEspectaculo.jsp");
		}

		else {
			EspectaculosDAO espectaculo = new EspectaculosDAO();
			EspectaculosDTO espectaculoTitulo = espectaculo.consultarEspectaculosTitulo(titulo, datos);

			ArrayList<String> titulos = new ArrayList<String>();

			ArrayList<String> descripciones = new ArrayList<String>();

			ArrayList<String> categorias = new ArrayList<String>();

			ArrayList<String> tipos = new ArrayList<String>();

			titulos.add(espectaculoTitulo.getTitulo());
			descripciones.add(espectaculoTitulo.getDescripcion());
			categorias.add(espectaculoTitulo.getCategoria().name());
			tipos.add(espectaculoTitulo.getTipo().name());

			EspectaculosBean espectaculosBean = new EspectaculosBean();

			espectaculosBean.setTitulo(titulos);
			espectaculosBean.setDescripcion(descripciones);
			espectaculosBean.setCategoria(categorias);
			espectaculosBean.setTipo(tipos);

			request.getSession().setAttribute("listaEspectaculos", espectaculosBean);

		}

		response.sendRedirect("mvc/views/titulo.jsp");

	}

}
