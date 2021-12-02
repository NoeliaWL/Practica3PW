package business;

import java.time.LocalDateTime;

public class EspectadoresDTO {
	protected String nombre;
	protected String apellidos;
	protected String nick;
	protected String correo;
	protected String contrasena;
	protected LocalDateTime fechaRegistro;
	protected LocalDateTime ultimaConexion;
	
	public EspectadoresDTO() {
		nombre = "";
		apellidos = "";
		nick = "";
		correo = "";
		contrasena = "";
		fechaRegistro = null;
		ultimaConexion = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDateTime getUltimaConexion() {
		return ultimaConexion;
	}

	public void setUltimaConexion(LocalDateTime ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}
}