package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.EspectaculosDTO;
import business.SesionesDTO;
import data.common.Tipoespectaculo;
import data.dao.EspectaculosDAO;
import data.dao.SesionesDAO;
import display.javabean.DatosConexionBean;

@WebServlet(name="cancelarSesionController", urlPatterns="/cancelarsesion")
public class cancelarSesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("request.getParameter('titulo') = " + request.getParameter("titulos"));
		//if(request.getParameter("titulos") != null && request.getParameter("titulos") != "") {
		if(true){
			doPut(request, response);
		}

		DatosConexionBean datos = DatosConexionBean.getInstance();
		EspectaculosDAO dao = new EspectaculosDAO();
		ArrayList<EspectaculosDTO> espectaculos = new ArrayList<EspectaculosDTO>();
		
		espectaculos = dao.listarEspectaculos(datos);
		
		List<String> titulos = new ArrayList<String>();
		List<Tipoespectaculo> tipos = new ArrayList<Tipoespectaculo>();
		
		for(EspectaculosDTO e : espectaculos) {
			titulos.add(e.getTitulo());
			tipos.add(e.getTipo());
		}
				
		HttpSession session = request.getSession();
		session.setAttribute("Espectaculostitulos", titulos);
		session.setAttribute("Espectaculostipos", tipos);
		
		response.sendRedirect("mvc/views/cancelarSesion.jsp");
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		String titulo = request.getParameter("titulos");
		
		System.out.println("Titulo rescatado: " + titulo);
		
		DatosConexionBean datos = DatosConexionBean.getInstance();
		SesionesDAO dao = new SesionesDAO();
		ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
		EspectaculosDTO espectaculo = new EspectaculosDTO();
		espectaculo.setTitulo(titulo);

		sesiones = dao.listarSesionesEspectaculo(espectaculo, datos);
		
		List<Integer> sesionesIds = new ArrayList<Integer>();
		List<Date> sesionesFechas = new ArrayList<Date>();
		List<Time> sesionesHoras = new ArrayList<Time>();
				
		for(SesionesDTO s : sesiones) {
			sesionesIds.add(s.getId());
			sesionesFechas.add(s.getFecha());
			sesionesHoras.add(s.getHora());
		}
				
		HttpSession session = request.getSession();
		session.setAttribute("sesionesIds", sesionesIds);
		session.setAttribute("sesionesFechas", sesionesFechas);
		session.setAttribute("sesionesHoras", sesionesHoras);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idSesion = Integer.parseInt(request.getParameter("sesiones"));
		String titulo = request.getParameter("titulos");
		titulo = "Chenoa";
		HttpSession session = request.getSession();
		List<String> titulos = (List<String>) session.getAttribute("Espectculostitulos");
		List<Tipoespectaculo> tipos = (List<Tipoespectaculo>) session.getAttribute("Espectaculostipos");
		List<Integer> sesionesIds = (List<Integer>) session.getAttribute("sesionesIds");
		int index = 0;
		//int index = titulos.indexOf(titulo);
		System.out.println("Titulos");
		for(String t : titulos) {
			System.out.println(t);
		}
		
		if(tipos.get(index).toString().equals("PUNTUAL")) {
			System.out.println("No se puede borrar la sesión por ser un espectáculo de tipo PUNTUAL");
		}
		else if(tipos.get(index).toString().equals("PASEMULTIPLE")) {
			//Dejar al menos dos sesiones
			if(sesionesIds.size() <= 2) {
				System.out.println("No se puede borrar ninguna sesión más por ser un espectáculo de tipo PASE MULTIPLE");
			}
			else {
				SesionesDAO dao = new SesionesDAO();
				DatosConexionBean datos = DatosConexionBean.getInstance();
				SesionesDTO sesion = new SesionesDTO();
				
				sesion.setId(idSesion);
				
				int status = dao.borrarSesion(sesion, datos);
				
				if(status == 1) {
					System.out.println("Sesion borrada correctamente");
				}
				else {
					System.out.println("Error al borrar la sesion");
				}
				
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
		}
		else if(tipos.get(index).toString().equals("TEMPORADA")) {
			//Dejar al menos 1 fecha
			if(sesionesIds.size() == 1) {
				System.out.println("No se puede borrar ninguna sesión más");
			}
			else {
				SesionesDAO dao = new SesionesDAO();
				DatosConexionBean datos = DatosConexionBean.getInstance();
				SesionesDTO sesion = new SesionesDTO();
				
				sesion.setId(idSesion);
				
				int status = dao.borrarSesion(sesion, datos);
				
				if(status == 1) {
					System.out.println("Sesion borrada correctamente");
				}
				else {
					System.out.println("Error al borrar la sesion");
				}
				
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
		}
	}
}