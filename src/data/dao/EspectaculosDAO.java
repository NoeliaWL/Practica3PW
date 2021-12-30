package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import business.EspectaculosDTO;
import data.common.Categoriaevento;
import data.common.Conexion;
import data.common.Tipoespectaculo;
import display.javabean.DatosConexionBean;

public class EspectaculosDAO {
	public ArrayList<EspectaculosDTO> listarEspectaculos(DatosConexionBean datos) {
		ArrayList<EspectaculosDTO> espectaculos = new ArrayList<EspectaculosDTO>();
		EspectaculosDTO espectaculo = new EspectaculosDTO();

		Statement stmt = null;

		try {
			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("listarEspectaculos"));

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String categoria = rs.getString("categoria");
				String tipo = rs.getString("tipo");
				String propietario = rs.getString("propietario");

				espectaculo = new EspectaculosDTO();
				espectaculo.setTitulo(titulo);
				espectaculo.setDescripcion(descripcion);
				switch (categoria) {
				case "MONOLOGO":
					espectaculo.setCategoria(Categoriaevento.MONOLOGO);
					break;

				case "CONCIERTO":
					espectaculo.setCategoria(Categoriaevento.CONCIERTO);
					break;

				case "OBRADETEATRO":
					espectaculo.setCategoria(Categoriaevento.OBRADETEATRO);
					break;
				}
				switch (tipo) {
				case "PUNTUAL":
					espectaculo.setTipo(Tipoespectaculo.PUNTUAL);
					break;

				case "PASEMULTIPLE":
					espectaculo.setTipo(Tipoespectaculo.PASEMULTIPLE);
					break;

				case "TEMPORADA":
					espectaculo.setTipo(Tipoespectaculo.TEMPORADA);
					break;
				}
				espectaculo.setPropietario(propietario);

				espectaculos.add(espectaculo);
			}

			if (stmt != null) {
				stmt.close();
			}

			conexion.closeConnection();
		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return espectaculos;
	}

	public int registrarEspectaculo(EspectaculosDTO espectaculo, DatosConexionBean datos) {
		int status = 0;

		try {
			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarEspectaculo"));
			ps.setString(1, espectaculo.getTitulo());
			ps.setString(2, espectaculo.getDescripcion());
			ps.setString(3, espectaculo.getCategoria().toString());
			ps.setString(4, espectaculo.getTipo().toString());
			ps.setString(5, espectaculo.getPropietario());

			status = ps.executeUpdate();

			conexion.closeConnection();
		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		System.out.println(status);

		return status;
	}

	public int borrarEspectaculo(EspectaculosDTO espectaculo, DatosConexionBean datos) {
		int status = 0;

		try {
			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("borrarEspectaculo"));
			ps.setString(1, espectaculo.getTitulo());

			status = ps.executeUpdate();

			conexion.closeConnection();
		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return status;
	}

	public Tipoespectaculo comprobarTipo(EspectaculosDTO espectaculo, DatosConexionBean datos) {
		Tipoespectaculo tipo = null;

		try {
			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("comprobarTipoEspectaculo"));
			ps.setString(1, espectaculo.getTitulo());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String type = rs.getString("tipo");

				switch (type) {
				case "PUNTUAL":
					tipo = Tipoespectaculo.PUNTUAL;
					break;

				case "PASEMULTIPLE":
					tipo = Tipoespectaculo.PASEMULTIPLE;
					break;

				case "TEMPORADA":
					tipo = Tipoespectaculo.TEMPORADA;
					break;
				}
			}

			conexion.closeConnection();
		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return tipo;
	}

	public EspectaculosDTO consultarEspectaculosTitulo(String titulo, DatosConexionBean datos) {
		EspectaculosDTO espectaculoConsultado = new EspectaculosDTO();

		Properties prop = new Properties();
		prop = datos.getSQL();

		try {

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			PreparedStatement ps = con.prepareStatement(prop.getProperty("selectEspectaculoTitulo"));
			ps.setString(1, titulo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String titulo1 = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String categoria = rs.getString("categoria");
				String tipo = rs.getString("tipo");
				String propietario = rs.getString("propietario");

				espectaculoConsultado = new EspectaculosDTO();
				espectaculoConsultado.setTitulo(titulo1);
				espectaculoConsultado.setDescripcion(descripcion);
				if (categoria.equals("MONOLOGO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.MONOLOGO);
				} else if (categoria.equals("CONCIERTO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.CONCIERTO);
				} else if (categoria.equals("OBRADETEATRO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.OBRADETEATRO);
				}

				if (tipo.equals("PUNTUAL")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.PUNTUAL);
				} else if (tipo.equals("PASEMULTIPLE")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.PASEMULTIPLE);
				} else if (tipo.equals("TEMPORADA")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.TEMPORADA);
				}
				espectaculoConsultado.setPropietario(propietario);
			}

			conexion.closeConnection();

		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return espectaculoConsultado;
	}

	public ArrayList<EspectaculosDTO> consultarEspectaculosCategoria(Categoriaevento categoria,
			DatosConexionBean datos) {
		ArrayList<EspectaculosDTO> espectaculos = new ArrayList<EspectaculosDTO>();
		EspectaculosDTO espectaculoConsultado = new EspectaculosDTO();

		Properties prop = new Properties();
		prop = datos.getSQL();

		try {

			Conexion connection = new Conexion();
			Connection con = connection.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("selectEspectaculoCategoria"));
			ps.setString(1, categoria.toString());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String categoria1 = rs.getString("categoria");
				String tipo = rs.getString("tipo");
				String propietario = rs.getString("propietario");

				espectaculoConsultado = new EspectaculosDTO();
				espectaculoConsultado.setTitulo(titulo);
				espectaculoConsultado.setDescripcion(descripcion);
				if (categoria1.equals("MONOLOGO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.MONOLOGO);
				} else if (categoria1.equals("CONCIERTO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.CONCIERTO);
				} else if (categoria1.equals("OBRADETEATRO")) {
					espectaculoConsultado.setCategoria(Categoriaevento.OBRADETEATRO);
				}

				if (tipo.equals("PUNTUAL")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.PUNTUAL);
				} else if (tipo.equals("PASEMULTIPLE")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.PASEMULTIPLE);
				} else if (tipo.equals("TEMPORADA")) {
					espectaculoConsultado.setTipo(Tipoespectaculo.TEMPORADA);
				}
				espectaculoConsultado.setPropietario(propietario);

				espectaculos.add(espectaculoConsultado);
			}

			connection.closeConnection();

		} catch (SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return espectaculos;
	}
}