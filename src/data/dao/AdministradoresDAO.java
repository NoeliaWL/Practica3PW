package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import business.AdministradoresDTO;
import data.common.Conexion;
import display.javabean.DatosConexionBean;

public class AdministradoresDAO {
	public AdministradoresDTO consultarUsuario(AdministradoresDTO user, DatosConexionBean datos) {
		AdministradoresDTO administrador = new AdministradoresDTO();
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("consultarUsuarioAdmin"));
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
				
				administrador = new AdministradoresDTO();
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
	
	public int registrarUsuario(AdministradoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarAdmin"));
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
	
	public ArrayList<AdministradoresDTO> listarUsuarios(DatosConexionBean datos) {
		ArrayList<AdministradoresDTO> administradores = new ArrayList<AdministradoresDTO>();
		AdministradoresDTO admin = new AdministradoresDTO();
		
		Statement stmt = null;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("listarUsuariosAdmin"));
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String nick = rs.getString("nick");
				String correo = rs.getString("correo");
				String contrasena = rs.getString("contrasena");
				Timestamp fechaRegistro = rs.getTimestamp("fechaRegistro");
				Timestamp ultimaConexion = rs.getTimestamp("ultimaConexion");
				
				admin = new AdministradoresDTO();
				admin.setNombre(nombre);
				admin.setApellidos(apellidos);
				admin.setNick(nick);
				admin.setCorreo(correo);
				admin.setContrasena(contrasena);
				admin.setFechaRegistro(fechaRegistro.toLocalDateTime());
				if(ultimaConexion != null) {
					admin.setUltimaConexion(ultimaConexion.toLocalDateTime());
				}
				
				administradores.add(admin);
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
		
		return administradores;
	}
	
	public int actualizarUltimaConexion(AdministradoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarUltimaConexionAdmin"));
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
	
	public int borrarUsuario(AdministradoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("borrarUsuarioAdmin"));
			ps.setString(1, user.getCorreo());
			
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
	
	public int actualizarUsuario(AdministradoresDTO user, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarUsuarioAdmin"));
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getNick());
			ps.setString(4, user.getContrasena());
			ps.setString(5, user.getCorreo());
			
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