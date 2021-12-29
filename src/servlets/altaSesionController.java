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

@WebServlet(name="altaSesionController", urlPatterns="/altasesion")
public class altaSesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String titulo = "", fecha = "", hora = "", totalEntradas = "";
		if(request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}
		
		if(request.getParameter("sesion") != null) {
			fecha = request.getParameter("sesion");
		}
		
		if(request.getParameter("hora") != null) {
			hora = request.getParameter("hora");
		}
		
		if(request.getParameter("entradas") != null) {
			totalEntradas = request.getParameter("entradas");
		}
		
		if(titulo == "" && fecha == "" && hora == "" && totalEntradas == "") {
			HttpSession session = request.getSession();
			EspectaculosDTO espectaculo = (EspectaculosDTO) session.getAttribute("espectaculo");
			
			if(espectaculo != null && espectaculo.getTitulo() != "") {
				session.setAttribute("tituloEspectaculo", espectaculo.getTitulo());
			}
			else {
				DatosConexionBean datos = DatosConexionBean.getInstance();
				EspectaculosDAO dao = new EspectaculosDAO();
				ArrayList<EspectaculosDTO> espectaculos = new ArrayList<EspectaculosDTO>();
				
				espectaculos = dao.listarEspectaculos(datos);
				
				List<String> titulos = new ArrayList<String>();
				List<String> tipos = new ArrayList<String>();
				
				for(EspectaculosDTO e : espectaculos) {
					titulos.add(e.getTitulo());
					tipos.add(e.getTipo().toString());
				}
						
				session.setAttribute("Espectaculostitulos", titulos);
				session.setAttribute("Espectaculostipos", tipos);
			}
			
			response.sendRedirect("mvc/views/AddSesion.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String titulo = "", fecha = "", hora = "", totalEntradas = "";
		if(request.getParameter("titulo") != null) {
			titulo = request.getParameter("titulo");
		}
		
		if(request.getParameter("sesion") != null) {
			fecha = request.getParameter("sesion");
		}
		
		if(request.getParameter("hora") != null) {
			hora = request.getParameter("hora");
		}
		
		if(request.getParameter("entradas") != null) {
			totalEntradas = request.getParameter("entradas");
		}
		
		if(titulo == "" && fecha == "" && hora == "" && totalEntradas == "") {
			response.sendRedirect("mvc/views/AddSesion.jsp");
		}
		
		HttpSession session = request.getSession();
		EspectaculosDTO espectaculo = (EspectaculosDTO) session.getAttribute("espectaculo");
		EspectaculosDAO dao = new EspectaculosDAO();
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
		if(espectaculo == null) {
			//COMPROBAR TIPO DEL ESPECTACULO PARA INSERTAR SESIONES
			EspectaculosDAO espectaculosDAO = new EspectaculosDAO();
			espectaculo = new EspectaculosDTO();
			espectaculo.setTitulo(titulo);
			Tipoespectaculo tipo = espectaculosDAO.comprobarTipo(espectaculo, datos);
			
			if(tipo == Tipoespectaculo.PUNTUAL) {
				System.out.println("Espectaculo seleccionado de tipo PUNTUAL, no se pueden añadir sesiones");
				response.sendRedirect("mvc/views/AddSesion.jsp");
			}
			else if(tipo == Tipoespectaculo.TEMPORADA) {
				//COMPROBAR FECHA DE INICIO Y FECHA DE FIN
				SesionesDAO sesionDAO = new SesionesDAO();
				ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
				sesiones = sesionDAO.listarSesionesEspectaculo(espectaculo, datos);
				if(sesiones.get(0).getFecha().before(Date.valueOf(fecha)) && sesiones.get(sesiones.size()-1).getFecha().after(Date.valueOf(fecha))) {
					SesionesDTO sesion = new SesionesDTO();
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
						response.sendRedirect("mvc/views/AddSesion.jsp");
					}
				}
				else {
					System.out.println("La fecha introducida no esta dentro del rango de fechas del espectaculo de temporada seleccionado");
					System.out.println("Rango de fechas: " + sesiones.get(0).getFecha() + " - " + sesiones.get(sesiones.size()-1).getFecha());
					response.sendRedirect("mvc/views/AddSesion.jsp");
				}
			}
		}
		else {
			int status = dao.registrarEspectaculo(espectaculo, datos);
			if(status == 1) {
				System.out.println("Espectaculos registrado correctamente");
				SesionesDTO sesion = new SesionesDTO();
				SesionesDAO sesionDAO = new SesionesDAO();
				sesion.setFecha(Date.valueOf(fecha));
				sesion.setHora(Time.valueOf(hora));
				sesion.setEspectaculo(espectaculo.getTitulo());
				sesion.setTotalEntradas(Integer.parseInt(totalEntradas));
				sesion.setEntradasDisponibles(Integer.parseInt(totalEntradas));
				sesion.setEntradasVendidas(0);
				
				//Llamar al DAO para insertar sesion
				int statusSesion = sesionDAO.insertarSesion(sesion, datos);
				if(statusSesion == 1) {
					System.out.println("Sesion insertada correctamente");
					response.sendRedirect("mvc/views/AddSesion.jsp");
				}
				else {
					System.out.println("Error al insertar la sesion");
					response.sendRedirect("mvc/views/AddSesion.jsp");
				}
			}
			else {
				System.out.println("Error al insertar el espectaculo");
				response.sendRedirect("mvc/views/registroEspectaculo.jsp");
			}
		}
	}
}