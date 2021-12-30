package display.javabean;

import java.util.ArrayList;

public class CriticasBean {

	
	private ArrayList<String> titulo;
	
	private ArrayList<String> resena;
	
	private ArrayList<String> propietario;
	
	private ArrayList<String> espectaculo;
	
	private ArrayList<Integer> puntuacion;
	
	
	
	public CriticasBean() {
		
		titulo = new ArrayList<String>();
		
		resena = new ArrayList<String>();
		
		propietario = new ArrayList<String>();
		
		espectaculo = new ArrayList<String>();
		
		puntuacion = new ArrayList<Integer>();
		
		
		
	}
	
	public ArrayList<String> getTitulo(){
		return titulo;
	}
	
	public void setTitulo(ArrayList<String> titulo) {
		this.titulo = titulo;
		
		
	}
	
	public ArrayList<String> getResena(){
		return resena;
	}
	
	public void setResena(ArrayList<String> resena) {
		this.resena = resena;
	}
	
	public ArrayList<String> getPropietario(){
		return propietario;
	}
	
	public void setPropietario(ArrayList<String> propietario) {
		this.propietario = propietario;
	}
	
	public ArrayList<String> getEspectaculo(){
		return espectaculo;
	}
	
	public void setEspectaculo(ArrayList<String> espectaculo) {
		this.espectaculo = espectaculo;
	
	}
	
	public ArrayList<Integer> getPuntuacion(){
		return puntuacion;
	}
	
	public void SetPuntuacion(ArrayList<Integer> puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
	
	
}
