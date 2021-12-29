package business;

import java.sql.Date;
import java.sql.Time;

public class SesionesDTO {
	protected int id;
	protected Date fecha;
	protected Time hora;
	protected String espectaculo;
	protected int totalEntradas;
	protected int entradasVendidas;
	protected int entradasDisponibles;
	
	public SesionesDTO() {
		id = -1;
		fecha = Date.valueOf("0001-01-01");
		hora = Time.valueOf("00:00:00");
		espectaculo = "";
		totalEntradas = 0;
		entradasVendidas = 0;
		entradasDisponibles = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}

	public int getTotalEntradas() {
		return totalEntradas;
	}

	public void setTotalEntradas(int totalEntradas) {
		this.totalEntradas = totalEntradas;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}

	public int getEntradasDisponibles() {
		return entradasDisponibles;
	}

	public void setEntradasDisponibles(int entradasDisponibles) {
		this.entradasDisponibles = entradasDisponibles;
	}
}