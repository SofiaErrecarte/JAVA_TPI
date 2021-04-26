package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import entities.*;
public class DataPersona extends DataMethods{

	public Persona getById(Persona p) {
		Persona per = new Persona();
		per.setIdPersona(0);
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
				per.setApellido(rs.getString("apellido"));
				per.setNombre(rs.getString("nombre"));
				per.setDireccion(rs.getString("direccion"));
				per.setDni(rs.getString("dni"));
				per.setTelefono(rs.getString("telefono")); 
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
	//NO MODIFICAR ESTE MÉTODO
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
				per.setContraseña(rs.getString("contraseña"));
				per.setAdmin(rs.getBoolean("admin")); 
				per.setApellido(rs.getString("apellido"));
				per.setNombre(rs.getString("nombre"));
				per.setDireccion(rs.getString("direccion"));
				per.setDni(rs.getString("dni"));
				per.setTelefono(rs.getString("telefono")); 
				per.setActivo(rs.getBoolean("activo"));
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
	
	public boolean getEstado(Persona per) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean activo=false;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select estado FROM persona where idPersona=?"
					);
			stmt.setLong(1, per.getIdPersona());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				activo = rs.getBoolean("estado");
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
		return activo;
		
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
					per.setTelefono(rs.getString("telefono"));
					per.setActivo(rs.getBoolean("activo"));
					
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
		if(personas.isEmpty()) {
			Persona per = new Persona();
			per.setIdPersona(0);
			per.setEmail(null);
			per.setContraseña(null); //VER cómo hacemos esto
			per.setAdmin(false); 
			per.setApellido(null);
			per.setNombre(null);
			per.setDireccion(null);
			per.setDni(null);
			per.setTelefono(null);
			per.setActivo(false);
			
			personas.add(per);
		}
		
		return personas;
	}
	
	
	public LinkedList<Persona> getDisponibles(){ // VER CUAL ES BIEN EL ATRIBUTO Y PROBARLO EN AGREGAR/MODIFICAR PRESTAAO
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas= new LinkedList<>();
				
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from persona where activo=1");
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
	
	public Persona getByDNI(Persona p) {
		Persona per = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona where dni=?");
			stmt.setString(1, p.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				per = new Persona();
				per.setIdPersona(rs.getInt("idPersona"));
				per.setEmail(rs.getString("email"));
				per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
				per.setAdmin(rs.getBoolean("admin")); 
				per.setApellido(rs.getString("apellido"));
				per.setNombre(rs.getString("nombre"));
				per.setDireccion(rs.getString("direccion"));
				per.setDni(rs.getString("dni"));
				per.setTelefono(rs.getString("telefono"));
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

	
	public MyResult add(Persona p) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM persona WHERE email=?"
					);
			stmt.setString(1, p.getEmail());
			keyResultSet = stmt.executeQuery();
			if(keyResultSet!=null && keyResultSet.next()){
				// preguntamos si hay al menos un proveedor con ese CUIT
				if (keyResultSet.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe una persona actualmente con ese usuario.");
					return res;
				} else {
			stmt.close();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`persona` (`apellido`, `nombre`, `telefono`, `email`, `direccion`, `dni`, `admin`, `contraseña`, `activo` ) values(?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
						stmt.setString(1, p.getApellido());
						stmt.setString(2, p.getNombre());
						stmt.setString(3, p.getTelefono());
						stmt.setString(4, p.getEmail());
						stmt.setString(5, p.getDireccion());
						stmt.setString(6, p.getDni());
						stmt.setBoolean(7, p.isAdmin());
						stmt.setString(8, p.getContraseña());
						stmt.setBoolean(9, true);
						stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPersona(keyResultSet.getInt(1));
            }
		}}}  catch (SQLException e) {
			e.printStackTrace();
			return Add(resultado);
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		// si llegó hasta acá está bien
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		return Add(1);
	}

	public MyResult editPersona(Persona per) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM persona WHERE dni=? and idPersona!=?"
					);
			stmt.setString(1, per.getDni());
			stmt.setInt(2, per.getIdPersona());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un proveedor con ese CUIT
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe una persona actualmente con ese DNI");
					return res;
				} else {
			stmt.close();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`persona` SET `apellido` = ?, `nombre` = ?, `telefono` = ?, `email` = ?, `direccion` = ?, `dni` = ?, `admin` = ? WHERE (`idPersona` = ?);",							
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, per.getApellido());
			stmt.setString(2, per.getNombre());
			stmt.setString(3, per.getTelefono());
			stmt.setString(4, per.getEmail());
			stmt.setString(5, per.getDireccion());
			stmt.setString(6, per.getDni());
			stmt.setBoolean(7, per.isAdmin());
			stmt.setInt(8, per.getIdPersona());
			stmt.executeUpdate();
			
		} }} catch (SQLException e) {
			return Update(resultado);
		} finally {
            try {
                if(rs!=null)rs.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		// si llego aca esta todo OK
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		res.setErr_message("Persona actualizada correctamente");
		return Update(1);
	}

	public MyResult setEstado(Persona p, boolean b) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`persona` SET `activo` = ? WHERE (`idPersona` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setBoolean(1, b);			
			stmt.setInt(2, p.getIdPersona());
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
			return Update(resultado);
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		// si llego aca esta todo OK
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		res.setErr_message("Usuario actualizado correctamente");
		return Update(1);
	}
	

	public LinkedList<Persona> getByApellidoA() {
		Persona per =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona order by apellido"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					per.setIdPersona(rs.getInt("idPersona"));
					per.setEmail(rs.getString("email"));
					per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
					per.setAdmin(rs.getBoolean("admin")); 
					per.setApellido(rs.getString("apellido"));
					per.setNombre(rs.getString("nombre"));
					per.setDireccion(rs.getString("direccion"));
					per.setDni(rs.getString("dni"));
					per.setTelefono(rs.getString("telefono"));
				personas.add(per);
			}}
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
		
		return personas;
	}
	
	public LinkedList<Persona> getByApellidoZ() {
		Persona per =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona order by apellido desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					per.setIdPersona(rs.getInt("idPersona"));
					per.setEmail(rs.getString("email"));
					per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
					per.setAdmin(rs.getBoolean("admin")); 
					per.setApellido(rs.getString("apellido"));
					per.setNombre(rs.getString("nombre"));
					per.setDireccion(rs.getString("direccion"));
					per.setDni(rs.getString("dni"));
					per.setTelefono(rs.getString("telefono"));
				personas.add(per);
			}}
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
		
		return personas;
	}

	public LinkedList<Persona> getByNombreA() {
		Persona per =  new Persona();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona order by nombre"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					per.setIdPersona(rs.getInt("idPersona"));
					per.setEmail(rs.getString("email"));
					per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
					per.setAdmin(rs.getBoolean("admin")); 
					per.setApellido(rs.getString("apellido"));
					per.setNombre(rs.getString("nombre"));
					per.setDireccion(rs.getString("direccion"));
					per.setDni(rs.getString("dni"));
					per.setTelefono(rs.getString("telefono"));
				personas.add(per);
			}}
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
		
		return personas;
	}
	public LinkedList<Persona> getByNombreZ() {
		Persona per =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from persona order by nombre desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					per.setIdPersona(rs.getInt("idPersona"));
					per.setEmail(rs.getString("email"));
					per.setContraseña(rs.getString("contraseña")); //VER cómo hacemos esto
					per.setAdmin(rs.getBoolean("admin")); 
					per.setApellido(rs.getString("apellido"));
					per.setNombre(rs.getString("nombre"));
					per.setDireccion(rs.getString("direccion"));
					per.setDni(rs.getString("dni"));
					per.setTelefono(rs.getString("telefono"));
				personas.add(per);
			}}
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
		
		return personas;
	}

	
}

