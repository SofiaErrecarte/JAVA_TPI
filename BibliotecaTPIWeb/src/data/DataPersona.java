package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Libro;
import entities.Persona;

import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DataPersona {

	public Persona getById(Persona p) {
		Persona per = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona where idPersona=?");
			stmt.setInt(1, p.getIdPersona());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				per = new Persona();
				per.setIdPersona(rs.getInt("idPersona"));
				per.setEmail(rs.getString("email"));
				per.setContraseña(""); //VER cómo hacemos esto
				per.setAdmin(rs.getBoolean("admin")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return per;
	}
	
	public Persona getByEmail(Persona p) {
		Persona per = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona where email=? and contraseña=?");
			stmt.setString(1, p.getEmail());
			stmt.setString(2, p.getContraseña());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				per = new Persona();
				per.setIdPersona(rs.getInt("idPersona"));
				per.setEmail(rs.getString("email"));
				per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
				per.setAdmin(rs.getBoolean("admin")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return per;
	}
	
	public LinkedList<Persona> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas= new LinkedList<>();
				
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from persona");
			if(rs!=null) {
				while(rs.next()) {
					Persona per = new Persona();
					per.setIdPersona(rs.getInt("idPersona"));
					per.setEmail(rs.getString("email"));
					per.setContraseña(""); //VER cómo hacemos esto
					per.setAdmin(rs.getBoolean("admin")); 
					per.setApellido(rs.getString("apellido"));
					per.setNombre(rs.getString("nombre"));
					per.setDireccion(rs.getString("direccion"));
					per.setDni(rs.getString("dni"));
					per.setMontoAPagar(rs.getFloat("montoAPagar"));
					per.setTelefono(rs.getString("telefono"));
					
					personas.add(per);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return personas;
	}
	

}
