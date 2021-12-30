package business;


public class CriticasDTO {
	
	
	protected String titulo;
	protected int puntuacion;
	protected String resena;
	protected String propietario;
	protected String espectaculo;

	public CriticasDTO() {

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getResena() {
		return resena;
	}

	public void setResena(String resena) {
		this.resena = resena;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}


}
