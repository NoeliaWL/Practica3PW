package data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import business.CriticasDTO;
import data.common.Conexion;
import display.javabean.DatosConexionBean;
import java.sql.Connection;

public class CriticasDAO {

	public int insertarCritica(CriticasDTO critica, DatosConexionBean datos) {
		int status = 0;

		try {

			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarCritica"));
			ps.setString(1, critica.getTitulo());
			ps.setInt(2, critica.getPuntuacion());
			ps.setString(3, critica.getResena());
			ps.setString(4, critica.getPropietario());
			ps.setString(5, critica.getEspectaculo());

			status = ps.executeUpdate();

			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<CriticasDTO> ConsultarCriticasEspectaculo(CriticasDTO criticas, DatosConexionBean datos) {
		ArrayList<CriticasDTO> listaCriticas = new ArrayList<CriticasDTO>();
		CriticasDTO consultaCriticas = new CriticasDTO();

		Properties prop = new Properties();
		prop = datos.getSQL();

		try {
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			PreparedStatement ps = con.prepareStatement(prop.getProperty("selectCriticasEspectaculo"));
			ps.setString(1, criticas.getEspectaculo());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String resena = rs.getString("resena");
				String propietario = rs.getString("propietario");
				int puntuacion = rs.getInt("puntuacion");
				String espectaculo = rs.getString("espectaculos_titulo");

				consultaCriticas.setTitulo(titulo);
				consultaCriticas.setResena(resena);
				consultaCriticas.setPropietario(propietario);
				consultaCriticas.setPuntuacion(puntuacion);
				consultaCriticas.setEspectaculo(espectaculo);
				listaCriticas.add(consultaCriticas);

				consultaCriticas = new CriticasDTO();

			}

			conexion.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCriticas;
	}

	public int eliminarCriticaEspectaculo(CriticasDTO critica, DatosConexionBean datos) {
		int status = 0;

		try {

			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("deleteCritica"));
			ps.setString(1, critica.getTitulo());

			status = ps.executeUpdate();

			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	public ArrayList<CriticasDTO> cargarDatosCriticas(DatosConexionBean datos) {
		ArrayList<CriticasDTO> listaCriticas = new ArrayList<CriticasDTO>();
		CriticasDTO criticas = new CriticasDTO();

		Properties prop = new Properties();
		prop = datos.getSQL();

		try {
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			PreparedStatement ps = con.prepareStatement(prop.getProperty("selectDatosCriticas"));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String resena = rs.getString("resena");
				String propietario = rs.getString("propietario");
				int puntuacion = rs.getInt("puntuacion");
				String espectaculo = rs.getString("espectaculos_titulo");

				criticas = new CriticasDTO();
				criticas.setTitulo(titulo);
				criticas.setResena(resena);
				criticas.setPuntuacion(puntuacion);
				criticas.setPropietario(propietario);
				criticas.setEspectaculo(espectaculo);
				listaCriticas.add(criticas);

				criticas = new CriticasDTO();

			}

			conexion.closeConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCriticas;

	}

}
