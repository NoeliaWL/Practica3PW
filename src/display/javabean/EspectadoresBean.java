package display.javabean;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EspectadoresBean {
	private ArrayList<String> correos;
	private ArrayList<String> nombres;
	private ArrayList<String> apellidos;
	private ArrayList<String> nicks;
	private ArrayList<LocalDateTime> fechasRegistro;
	private ArrayList<LocalDateTime> ultimasConexiones;
	
	public EspectadoresBean() {
		correos = new ArrayList<String>();
		nombres = new ArrayList<String>();
		apellidos = new ArrayList<String>();
		nicks = new ArrayList<String>();
		fechasRegistro = new ArrayList<LocalDateTime>();
		ultimasConexiones = new ArrayList<LocalDateTime>();
	}

	public ArrayList<String> getCorreos() {
		return correos;
	}

	public void setCorreos(ArrayList<String> correos) {
		this.correos = correos;
	}

	public ArrayList<String> getNombres() {
		return nombres;
	}

	public void setNombres(ArrayList<String> nombres) {
		this.nombres = nombres;
	}

	public ArrayList<String> getApellidos() {
		return apellidos;
	}

	public void setApellidos(ArrayList<String> apellidos) {
		this.apellidos = apellidos;
	}

	public ArrayList<String> getNicks() {
		return nicks;
	}

	public void setNicks(ArrayList<String> nicks) {
		this.nicks = nicks;
	}

	public ArrayList<LocalDateTime> getFechasRegistro() {
		return fechasRegistro;
	}

	public void setFechasRegistro(ArrayList<LocalDateTime> fechasRegistro) {
		this.fechasRegistro = fechasRegistro;
	}

	public ArrayList<LocalDateTime> getUltimasConexiones() {
		return ultimasConexiones;
	}

	public void setUltimasConexiones(ArrayList<LocalDateTime> ultimasConexiones) {
		this.ultimasConexiones = ultimasConexiones;
	}
}