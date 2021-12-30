package data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;

import business.EspectaculosDTO;
import business.SesionesDTO;
import data.common.Conexion;
import display.javabean.DatosConexionBean;

public class SesionesDAO {
	public int borrarSesion(SesionesDTO sesion, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("borrarSesion"));
			ps.setInt(1, sesion.getId());
			
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return status;
	}
	
	public ArrayList<SesionesDTO> listarSesionesEspectaculo(EspectaculosDTO espectaculo, DatosConexionBean datos) {
		ArrayList<SesionesDTO> sesiones = new ArrayList<SesionesDTO>();
		SesionesDTO sesion = new SesionesDTO();
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("listarSesionesEspectaculo"));
			ps.setString(1, espectaculo.getTitulo());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				Date fecha = rs.getDate("fecha");
				Time hora = rs.getTime("hora");
				int entradas = rs.getInt("totalEntradas");
				
				sesion = new SesionesDTO();
				sesion.setId(id);
				sesion.setFecha(fecha);
				sesion.setHora(hora);
				sesion.setTotalEntradas(entradas);
								
				sesiones.add(sesion);
			}
			
			conexion.closeConnection();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return sesiones;
	}
	
	public int insertarSesion(SesionesDTO sesion, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("insertarSesion"));
			ps.setDate(1, sesion.getFecha());
			ps.setTime(2, sesion.getHora());
			ps.setString(3, sesion.getEspectaculo());
			ps.setInt(4, sesion.getTotalEntradas());
			ps.setInt(5, sesion.getEntradasVendidas());
			ps.setInt(6, sesion.getEntradasDisponibles());
			
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
	
	public SesionesDTO consultarSesion(SesionesDTO sesionId, DatosConexionBean datos) {
		SesionesDTO sesion = new SesionesDTO();
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("consultarSesionId"));
			ps.setInt(1, sesionId.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int totalEntradas = rs.getInt("totalEntradas");
				int entradasVendidas = rs.getInt("entradasVendidas");
				
				sesion = new SesionesDTO();
				sesion.setTotalEntradas(totalEntradas);
				sesion.setEntradasVendidas(entradasVendidas);
			}
			
			conexion.closeConnection();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return sesion;
	}
	
	public int modificarSesion(SesionesDTO sesion, DatosConexionBean datos) {
		int status = 0;
		
		try {
			Properties prop = new Properties();
			prop = datos.getSQL();
			
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection(datos);
			
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarSesion"));
			ps.setDate(1, sesion.getFecha());
			ps.setTime(2, sesion.getHora());
			ps.setInt(3, sesion.getTotalEntradas());
			ps.setInt(4, sesion.getId());
			
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Codigo error: " + e.getErrorCode());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Mensaje error: " + e.getMessage());
		}
		
		return status;
	}
}