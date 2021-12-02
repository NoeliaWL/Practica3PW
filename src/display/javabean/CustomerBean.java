package display.javabean;

import data.common.Tipousuario;

public class CustomerBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String correoUser = "";
	private Tipousuario tipo = null;
	
	public String getCorreoUser() {
		return correoUser;
	}
	
	public void setCorreoUser(String correoUser) {
		this.correoUser = correoUser;
	}
	
	public Tipousuario getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipousuario tipo) {
		this.tipo = tipo;
	}
}