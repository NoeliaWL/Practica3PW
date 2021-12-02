package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import business.EspectadoresDTO;
import data.common.Conexion;
import display.javabean.DatosConexionBean;

public class EspectadoresDAO {
	public EspectadoresDTO consultarUsuario(EspectadoresDTO user, DatosConexionBean datos) {
		EspectadoresDTO administrador = new EspectadoresDTO();
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("consultarUsuarioEspectador"));
			ps.setString(1, user.getCorreo());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String nick = rs.getString("nick");
				String correo = rs.getString("correo");
				String contrasena = rs.getString("contrasena");
				Timestamp fechaRegistro = rs.getTimestamp("fechaRegistro");
				Timestamp ultimaConexion = rs.getTimestamp("ultimaConexion");
				
				administrador = new EspectadoresDTO();
				administrador.setNombre(nombre);
				administrador.setApellidos(apellidos);
				administrador.setNick(nick);
				administrador.setCorreo(correo);
				administrador.setContrasena(contrasena);
				administrador.setFechaRegistro(fechaRegistro.toLocalDateTime());
				if(ultimaConexion != null) {
					administrador.setUltimaConexion(ultimaConexion.toLocalDateTime());
				}
			}
			
			conexion.closeConnection();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return administrador;
	}
}