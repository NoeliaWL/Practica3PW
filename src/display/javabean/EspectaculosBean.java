package display.javabean;

import java.util.ArrayList;

public class EspectaculosBean {

	private ArrayList<String> titulo;
	
	private ArrayList<String> descripcion;
	
	private ArrayList<String> categoria;
	
	private ArrayList<String> tipo;
	
	private ArrayList<String> propietario;
	
	
	
	
	public EspectaculosBean(){
		
		titulo = new ArrayList<String>();
		
		descripcion = new ArrayList<String>();
		
		categoria = new ArrayList<String>();
		
		tipo = new ArrayList<String>();
		
		propietario = new ArrayList<String>();
		
		
		
		
	}




	public ArrayList<String> getTitulo() {
		return titulo;
	}




	public void setTitulo(ArrayList<String> titulo) {
		this.titulo = titulo;
	}




	public ArrayList<String> getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(ArrayList<String> descripcion) {
		this.descripcion = descripcion;
	}




	public ArrayList<String> getCategoria() {
		return categoria;
	}




	public void setCategoria(ArrayList<String> categoria) {
		this.categoria = categoria;
	}




	public ArrayList<String> getTipo() {
		return tipo;
	}




	public void setTipo(ArrayList<String> tipo) {
		this.tipo = tipo;
	}




	public ArrayList<String> getPropietario() {
		return propietario;
	}




	public void setPropietario(ArrayList<String> propietario) {
		this.propietario = propietario;
	}
	
}
