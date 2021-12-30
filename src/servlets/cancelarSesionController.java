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
import data.dao.EspectaculosDAO;
import data.dao.SesionesDAO;
import display.javabean.DatosConexionBean;

@WebServlet(name="cancelarSesionController", urlPatterns="/cancelarsesion")
public class cancelarSesionController extends HttpServlet {
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
		
		if(titulo == "" && sesion == "") {
			response.sendRedirect("mvc/views/cancelarSelectTitulo.jsp");
		}
		else if(titulo != "" && sesion == "") {
			SesionesDAO Sdao = new SesionesDAO();
			ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
			EspectaculosDTO espectaculo = new EspectaculosDTO();
			
			espectaculo.setTitulo(titulo);
			
			sesiones = Sdao.listarSesionesEspectaculo(espectaculo, datos);

			List<Integer> ids = new ArrayList<Integer>();
			List<Date> fechas = new ArrayList<Date>();
			List<Time> horas = new ArrayList<Time>();
			
			for(SesionesDTO s : sesiones) {
				ids.add(s.getId());
				fechas.add(s.getFecha());
				horas.add(s.getHora());
			}
			
			session.setAttribute("SesionesIds", ids);
			session.setAttribute("SesionesFechas", fechas);
			session.setAttribute("SesionesHoras", horas);
			
			response.sendRedirect("mvc/views/cancelarSesion.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sesion = "";
		if(request.getParameter("sesiones") != null && request.getParameter("sesiones") != "vacio") {
			sesion = request.getParameter("sesiones");
		}
		
		if(sesion == "") {
			response.sendRedirect("mvc/views/cancelarSesion.jsp");
		}
		
		SesionesDAO dao = new SesionesDAO();
		SesionesDTO dto = new SesionesDTO();
		DatosConexionBean datos = DatosConexionBean.getInstance();
		
		dto.setId(Integer.parseInt(sesion));
		
		int status = dao.borrarSesion(dto, datos);
		if(status == 1) {
			System.out.println("Sesion borrada correctamente");
			response.sendRedirect("mvc/views/menuAdministrador.jsp");
		}
		else {
			System.out.println("Error al borrar la sesion");
			response.sendRedirect("mvc/views/menuAdministrador.jsp");
		}
	}
}