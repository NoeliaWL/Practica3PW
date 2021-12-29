package business;

import data.common.Categoriaevento;
import data.common.Tipoespectaculo;

public class EspectaculosDTO {
	protected String titulo;
	protected String descripcion;
	protected Categoriaevento categoria;
	protected Tipoespectaculo tipo;
	protected String propietario;
	
	public EspectaculosDTO() {
		titulo = "";
		descripcion = "";
		categoria = null;
		tipo = null;
		propietario = "";
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Categoriaevento getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoriaevento categoria) {
		this.categoria = categoria;
	}
	
	public Tipoespectaculo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipoespectaculo tipo) {
		this.tipo = tipo;
	}
	
	public String getPropietario() {
		return propietario;
	}
	
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
}