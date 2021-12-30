package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import business.ValoracionesDTO;
import data.common.Conexion;
import display.javabean.DatosConexionBean;

public class ValoracionesDAO {

	public int insertarValoracion(ValoracionesDTO valoracion, DatosConexionBean datos) {
		int status = 0;

		try {

			Properties prop = new Properties();
			prop = datos.getSQL();

			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);

			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarValoracion"));
			ps.setString(1, valoracion.getPropietario());
			ps.setInt(2, valoracion.getPuntuacion());
			ps.setString(3, valoracion.getCriticasTitulo());

			status = ps.executeUpdate();

			conexion.closeConnection();

		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}

		return status;
	}

}
