package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.EspectaculosDTO;
import business.SesionesDTO;
import data.dao.EspectaculosDAO;
import data.dao.SesionesDAO;
import display.javabean.DatosConexionBean;

@WebServlet(name="updateSesionController", urlPatterns="/modificarsesion")
public class updateSesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatosConexionBean datos = DatosConexionBean.getInstance();
		EspectaculosDAO Edao = new EspectaculosDAO();
		ArrayList<EspectaculosDTO> espectaculos = new ArrayList<EspectaculosDTO>();
		
		espectaculos = Edao.listarEspectaculos(datos);
		
		List<String> titulos = new ArrayList<String>();
		List<String> tipos = new ArrayList<String>();
		
		for(EspectaculosDTO e : espectaculos) {
			titulos.add(e.getTitulo());
			tipos.add(e.getTipo().toString());
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("Espectaculostitulos", titulos);
		session.setAttribute("Espectaculostipos", tipos);
		
		String titulo = "", sesion = "";
		if(request.getParameter("titulo") != null && request.getParameter("titulo") != "vacio") {
			titulo = request.getParameter("titulo");
		}
		
		if(request.getParameter("sesiones") != null && request.getParameter("sesiones") != "vacio") {
			sesion = request.getParameter("sesiones");
		}
		
		if(session.getAttribute("titulo") != null) {
			titulo = (String) session.getAttribute("titulo");
		}
		
		if(titulo == "" && sesion == "") {
			response.sendRedirect("mvc/views/updateSelectTitulo.jsp");
		}
		else if(titulo != "" && sesion == ""){
			SesionesDAO Sdao = new SesionesDAO();
			ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
			EspectaculosDTO espectaculo = new EspectaculosDTO();
			
			espectaculo.setTitulo(titulo);
			
			sesiones = Sdao.listarSesionesEspectaculo(espectaculo, datos);

			List<Integer> ids = new ArrayList<Integer>();
			List<Date> fechas = new ArrayList<Date>();
			List<Time> horas = new ArrayList<Time>();
			List<Integer> entradas = new ArrayList<Integer>();
			
			for(SesionesDTO s : sesiones) {
				ids.add(s.getId());
				fechas.add(s.getFecha());
				horas.add(s.getHora());
				entradas.add(s.getTotalEntradas());
			}
			
			session.setAttribute("SesionesIds", ids);
			session.setAttribute("SesionesFechas", fechas);
			session.setAttribute("SesionesHoras", horas);
			session.setAttribute("SesionesEntradas", entradas);
			session.setAttribute("titulo", titulo);
			
			response.sendRedirect("mvc/views/updateSelectSesion.jsp");
		}
		else if(titulo != "" && sesion != "") {
			List<Integer> ids = (List<Integer>) session.getAttribute("SesionesIds");
			List<Date> fechas = (List<Date>) session.getAttribute("SesionesFechas");
			List<Time> horas = (List<Time>) session.getAttribute("SesionesHoras");
			List<Integer> entradas = (List<Integer>) session.getAttribute("SesionesEntradas");
			
			session.setAttribute("sesionId", sesion);
			
			int i = ids.indexOf(Integer.parseInt(sesion));
			
			session.setAttribute("fecha", fechas.get(i));
			session.setAttribute("hora", horas.get(i));
			session.setAttribute("entradas", entradas.get(i));
			
			response.sendRedirect("mvc/views/updateSesion.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fecha = "", hora = "", entradas = "";
		if(request.getParameter("fecha") != null) {
			fecha = request.getParameter("fecha");
		}
		
		if(request.getParameter("hora") != null) {
			hora = request.getParameter("hora");
		}
		
		if(request.getParameter("entradas") != null) {
			entradas = request.getParameter("entradas");
		}
		
		if(fecha == "" || hora == "" || entradas == "") {
			response.sendRedirect("mvc/views/updateSesion.jsp");
		}
		
		SesionesDAO dao = new SesionesDAO();
		SesionesDTO sesion = new SesionesDTO();
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sesionId");
		
		sesion.setId(Integer.parseInt(id));
		sesion.setHora(Time.valueOf(hora));
		
		session.setAttribute("titulo", null);
		
		if(Date.valueOf(LocalDate.now()).after(Date.valueOf(fecha))) {
			System.out.println("Fecha no valida, ya se ha pasado.");
			response.sendRedirect("mvc/views/updateSesion.jsp");
		}
		else {
			sesion.setFecha(Date.valueOf(fecha));
		}
		
		SesionesDTO sesionConsulta = new SesionesDTO();
		sesionConsulta = dao.consultarSesion(sesion, datos);
		
		if(sesionConsulta.getTotalEntradas() > Integer.parseInt(entradas) && sesionConsulta.getEntradasVendidas() > Integer.parseInt(entradas)) {
			System.out.println("No se puede modificar el número de entradas totales de esta sesion ya que se han vendido mas entradas de las que desea modificar");
			System.out.println("Si desea modificarlo deberá cancelar esta sesión y dar de alta a una nueva con los nuevos límites de entradas");
			response.sendRedirect("mvc/views/menuAdministrador.jsp");
		}
		else {
			sesion.setTotalEntradas(Integer.parseInt(entradas));
		}
		
		//MODIFIFCAR SESION LLAMANDO AL DAO
		int status = dao.modificarSesion(sesion, datos);
		if(status == 1) {
			System.out.println("Sesion modificada correctamente");
		}
		else {
			System.out.println("Error al modificar la sesion");
		}
		response.sendRedirect("mvc/views/menuAdministrador.jsp");
	}
}