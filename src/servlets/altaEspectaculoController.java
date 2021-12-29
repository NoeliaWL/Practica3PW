package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.EspectaculosDTO;
import business.SesionesDTO;
import data.common.Categoriaevento;
import data.common.Tipoespectaculo;
import data.dao.EspectaculosDAO;
import data.dao.SesionesDAO;
import display.javabean.CustomerBean;
import display.javabean.DatosConexionBean;

@WebServlet(name="altaEspectaculoController", urlPatterns="/altaespectaculo")
public class altaEspectaculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String titulo = "", descripcion = "", categoria = "", tipo = "";
		if(request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}
		
		if(request.getParameter("descripcion") != null) {
			descripcion = request.getParameter("descripcion");
		}
		
		if(request.getParameter("categoria") != null && request.getParameter("categoria") != "vacio") {
			categoria = request.getParameter("categoria");
		}
		
		if(request.getParameter("tipo") != null && request.getParameter("tipo") != "vacio") {
			tipo = request.getParameter("tipo");
		}
		
		if(titulo == "" && descripcion == "" && (categoria == "" || categoria == "vacio") && (tipo == "" || tipo == "vacio")) {
			response.sendRedirect("mvc/views/registroEspectaculo.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
		String titulo = "", descripcion = "", categoria = "", tipo = "";
		if(request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}
		
		if(request.getParameter("descripcion") != null) {
			descripcion = request.getParameter("descripcion");
		}
		
		if(request.getParameter("categoria") != null && request.getParameter("categoria") != "vacio") {
			categoria = request.getParameter("categoria");
		}
		
		if(request.getParameter("tipo") != null && request.getParameter("tipo") != "vacio") {
			tipo = request.getParameter("tipo");
		}
		
		if(titulo == "" && descripcion == "" && (categoria == "" || categoria == "vacio") && (tipo == "" || tipo == "vacio")) {
			response.sendRedirect("mvc/views/registroEspectaculo.jsp");
		}
		
		if(tipo.equals("PUNTUAL")) {
			String fecha = "", hora = "", totalEntradas = "";
			if(request.getParameter("sesion") != null) {
				fecha = request.getParameter("sesion");
			}
			
			if(request.getParameter("hora") != null) {
				hora = request.getParameter("hora");
			}
			
			if(request.getParameter("entradas") != null) {
				totalEntradas = request.getParameter("entradas");
			}
			
			EspectaculosDTO espectaculo = new EspectaculosDTO();
			EspectaculosDAO dao = new EspectaculosDAO();
			
			espectaculo.setTitulo(titulo);
			espectaculo.setDescripcion(descripcion);
			
			if(categoria.equals("MONOLOGO")) {
				espectaculo.setCategoria(Categoriaevento.MONOLOGO);
			}
			else if(categoria.equals("CONCIERTO")) {
				espectaculo.setCategoria(Categoriaevento.CONCIERTO);
			}
			else if(categoria.equals("OBRADETEATRO")) {
				espectaculo.setCategoria(Categoriaevento.OBRADETEATRO);
			}
			espectaculo.setTipo(Tipoespectaculo.PUNTUAL);
			
			HttpSession session = request.getSession();
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			espectaculo.setPropietario(customerBean.getCorreoUser());
			
			int status = dao.registrarEspectaculo(espectaculo, datos);
			if(status == 1) {
				System.out.println("Espectaculo insertado correctamente");
				SesionesDTO sesion = new SesionesDTO();
				SesionesDAO sesionDAO = new SesionesDAO();
				sesion.setFecha(Date.valueOf(fecha));
				sesion.setHora(Time.valueOf(hora));
				sesion.setEspectaculo(titulo);
				sesion.setTotalEntradas(Integer.parseInt(totalEntradas));
				sesion.setEntradasDisponibles(Integer.parseInt(totalEntradas));
				sesion.setEntradasVendidas(0);
				
				//Llamar al DAO para insertar sesion
				int statusSesion = sesionDAO.insertarSesion(sesion, datos);
				if(statusSesion == 1) {
					System.out.println("Sesion insertada correctamente");
					response.sendRedirect("mvc/views/menuAdministrador.jsp");
				}
				else {
					System.out.println("Error al insertar la sesion");
					status = dao.borrarEspectaculo(espectaculo, datos);
					response.sendRedirect("mvc/views/registroEspectaculo.jsp");
				}
			}
			else {
				System.out.println("Error al insertar el espectaculo");
				response.sendRedirect("mvc/views/registroEspectaculo.jsp");
			}
		}
		else if(tipo.equals("PASEMULTIPLE")) {
			//Insertar espectaculo
			EspectaculosDTO espectaculo = new EspectaculosDTO();
			
			espectaculo.setTitulo(titulo);
			espectaculo.setDescripcion(descripcion);
			
			if(categoria.equals("MONOLOGO")) {
				espectaculo.setCategoria(Categoriaevento.MONOLOGO);
			}
			else if(categoria.equals("CONCIERTO")) {
				espectaculo.setCategoria(Categoriaevento.CONCIERTO);
			}
			else if(categoria.equals("OBRADETEATRO")) {
				espectaculo.setCategoria(Categoriaevento.OBRADETEATRO);
			}
			espectaculo.setTipo(Tipoespectaculo.PASEMULTIPLE);
			
			HttpSession session = request.getSession();
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			espectaculo.setPropietario(customerBean.getCorreoUser());
			
			session.setAttribute("espectaculo", espectaculo);
			
			response.sendRedirect("/Practica3PW/altasesion");
		}
		else if(tipo.equals("TEMPORADA")) {
			String fechaInicio = "", fechaFin = "", hora = "", totalEntradas = "";
			if(request.getParameter("inicioTemporada") != null) {
				fechaInicio = request.getParameter("inicioTemporada");
			}
			
			if(request.getParameter("finTemporada") != null) {
				fechaFin = request.getParameter("finTemporada");
			}
			
			if(request.getParameter("horaInput") != null) {
				hora = request.getParameter("horaInput");
			}
			
			if(request.getParameter("entradas") != null) {
				totalEntradas = request.getParameter("entradas");
			}
			
			EspectaculosDTO espectaculo = new EspectaculosDTO();
			EspectaculosDAO dao = new EspectaculosDAO();
			
			espectaculo.setTitulo(titulo);
			espectaculo.setDescripcion(descripcion);
			
			if(categoria.equals("MONOLOGO")) {
				espectaculo.setCategoria(Categoriaevento.MONOLOGO);
			}
			else if(categoria.equals("CONCIERTO")) {
				espectaculo.setCategoria(Categoriaevento.CONCIERTO);
			}
			else if(categoria.equals("OBRADETEATRO")) {
				espectaculo.setCategoria(Categoriaevento.OBRADETEATRO);
			}
			espectaculo.setTipo(Tipoespectaculo.TEMPORADA);
			
			HttpSession session = request.getSession();
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			espectaculo.setPropietario(customerBean.getCorreoUser());
			
			int status = dao.registrarEspectaculo(espectaculo, datos);
			if(status == 1) {
				//Calcular sesiones de temporada e insertarlas en la BD
				ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
				SesionesDTO sesion = new SesionesDTO();
				LocalDate fecha = LocalDate.parse(fechaInicio.toString());
				
				while(fecha.compareTo(LocalDate.parse(fechaFin.toString())) <= 0) {
					sesion = new SesionesDTO();
					
					sesion.setFecha(Date.valueOf(fecha));
					sesion.setHora(Time.valueOf(hora));
					sesion.setEspectaculo(espectaculo.getTitulo());
					sesion.setTotalEntradas(Integer.parseInt(totalEntradas));
					sesion.setEntradasVendidas(0);
					sesion.setEntradasDisponibles(Integer.parseInt(totalEntradas));
					
					sesiones.add(sesion);
					
					fecha = fecha.plusDays(7);
				}
				
				//Llamar al DAO para insertar sesion
				SesionesDAO sesionDAO = new SesionesDAO();
				int statusSesion = 0;
				for(SesionesDTO s : sesiones) {
					statusSesion = sesionDAO.insertarSesion(s, datos);
					if(statusSesion == 1) {
						System.out.println("Sesion insertada correctamente");
					}
					else {
						System.out.println("Error al insertar una sesion");
						status = dao.borrarEspectaculo(espectaculo, datos);
						response.sendRedirect("mvc/views/registroEspectaculo.jsp");
					}
				}
				
				response.sendRedirect("mvc/views/menuAdministrador.jsp");
			}
			else {
				System.out.println("Error al insertar el espectaculo");
				response.sendRedirect("mvc/views/registroEspectaculo.jsp");
			}
		}
	}
}