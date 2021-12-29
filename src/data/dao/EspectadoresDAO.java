package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	
	public int registrarUsuario(EspectadoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarEspectador"));
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getNick());
			ps.setString(4, user.getCorreo());
			ps.setString(5, user.getContrasena());
			ps.setTimestamp(6, Timestamp.valueOf(user.getFechaRegistro()));
			
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
	
	public ArrayList<EspectadoresDTO> listarUsuarios(DatosConexionBean datos) {
		ArrayList<EspectadoresDTO> espectadores = new ArrayList<EspectadoresDTO>();
		EspectadoresDTO espec = new EspectadoresDTO();
		
		Statement stmt = null;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("listarUsuariosEspec"));
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String nick = rs.getString("nick");
				String correo = rs.getString("correo");
				String contrasena = rs.getString("contrasena");
				Timestamp fechaRegistro = rs.getTimestamp("fechaRegistro");
				Timestamp ultimaConexion = rs.getTimestamp("ultimaConexion");
				
				espec = new EspectadoresDTO();
				espec.setNombre(nombre);
				espec.setApellidos(apellidos);
				espec.setNick(nick);
				espec.setCorreo(correo);
				espec.setContrasena(contrasena);
				espec.setFechaRegistro(fechaRegistro.toLocalDateTime());
				if(ultimaConexion != null) {
					espec.setUltimaConexion(ultimaConexion.toLocalDateTime());
				}
				
				espectadores.add(espec);
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			conexion.closeConnection();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return espectadores;
	}
	
	public int actualizarUltimaConexion(EspectadoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarUltimaConexionEspec"));
			ps.setTimestamp(1, Timestamp.valueOf(user.getUltimaConexion()));
			ps.setString(2, user.getCorreo());
			
			
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