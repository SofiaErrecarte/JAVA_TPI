package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Ejemplar;
import entities.Libro;
import entities.LineaPrestamo;
import entities.MyResult;
import entities.Persona;
import entities.Prestamo;

public class DataPrestamo extends DataMethods{
	public LinkedList<Prestamo> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos= new LinkedList<>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from prestamo order by fechaPrestamo desc");
			if(rs!=null) {
				while(rs.next()) {
					Prestamo p = new Prestamo();
					p.setIdPrestamo(rs.getInt("idPrestamo"));
					p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
					p.setFechaADevoler(rs.getDate("fechaADevolver"));
					p.setFechaDevolucion(rs.getDate("fechaDevolucion"));
					p.setIdPersona(rs.getInt("idPersona"));
					p.setEstado(rs.getString("estado"));
					prestamos.add(p);
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
		if(prestamos.isEmpty()) {
			Prestamo p = new Prestamo();
			p.setIdPrestamo(0);
			p.setFechaPrestamo(null);
			p.setFechaADevoler(null);
			p.setFechaDevolucion(null);
			p.setIdPersona(0);
			p.setEstado(null);
			prestamos.add(p);
		}
		
		return prestamos;
	}
	
	public MyResult add(Prestamo p ) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`prestamo` (`fechaPrestamo`, `fechaADevolver`, `idPersona`, `estado`) VALUES(?, ?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setDate(1, (java.sql.Date) p.getFechaPrestamo());
			stmt.setDate(2, (java.sql.Date) p.getFechaADevoler());
			stmt.setLong(3,p.getIdPersona());
			stmt.setString(4, "Abierto");
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPrestamo(keyResultSet.getInt(1));
            }
			
		}  catch (SQLException e) {
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
	
	public MyResult editPrestamo(Prestamo p) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`prestamo` SET `fechaPrestamo` = ?, `fechaADevolver` = ?, `idPersona` = ? WHERE (`idPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setTimestamp(1, new java.sql.Timestamp(p.getFechaPrestamo().getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(p.getFechaADevoler().getTime()));
			//stmt.setTimestamp(3, new java.sql.Timestamp(p.getFechaDevolucion().getTime()));
			stmt.setLong(3, p.getIdPersona());
			stmt.setInt(4, p.getIdPrestamo());
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
		res.setErr_message("Prestamo actualizado correctamente");
		return Update(1);
	}
	
	public void setEstado(Prestamo p, String estado) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`prestamo` SET `estado` = ? WHERE (`idPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, estado);			
			stmt.setInt(2, p.getIdPrestamo());
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public Prestamo deletePrestamo (Prestamo p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`prestamo` WHERE (`idPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, p.getIdPrestamo());
			stmt.executeUpdate();	
            
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return p;
	}

	public LinkedList<LineaPrestamo> getLPByPrestamo(Prestamo p ) {
		LineaPrestamo lp =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<LineaPrestamo> lps = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from linea_prestamo inner join ejemplar on ejemplar.idEjemplar=linea_prestamo.idEjemplar left join libro on ejemplar.idLibro=libro.idLibro where idPrestamo=?"
					);
			stmt.setLong(1, p.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				lp = new LineaPrestamo();
				lp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
				lp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				lp.setDevuelto(rs.getBoolean("devuelto"));
				lp.setIdPrestamo(rs.getInt("idPrestamo"));
				lp.setIdEjemplar(rs.getInt("idEjemplar"));
				lp.setTituloEjemplar(rs.getString("titulo"));
				lps.add(lp);
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
		
		return lps;
	}
	
	public Prestamo getById(Prestamo pr) {
		Prestamo p = new Prestamo();
		p.setIdPrestamo(0);
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo where idPrestamo=?");
			stmt.setInt(1, pr.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				p.setFechaADevoler(rs.getDate("fechaADevolver"));
				p.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				p.setIdPersona(rs.getInt("idPersona"));
				p.setEstado(rs.getString("estado"));
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
		
		return p;
	}

	public LinkedList<Prestamo> getByPersona(Persona per) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos= new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo where idPersona=?");
			stmt.setInt(1, per.getIdPersona());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Prestamo p = new Prestamo();
					p.setIdPrestamo(rs.getInt("idPrestamo"));
					p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
					p.setFechaADevoler(rs.getDate("fechaADevolver"));
					p.setFechaDevolucion(rs.getDate("fechaDevolucion"));
					p.setIdPersona(rs.getInt("idPersona"));
					p.setEstado(rs.getString("estado"));
					prestamos.add(p);
				}
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
		
		return prestamos;
	}
	
	public void cerrarPrestamo(Prestamo p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`prestamo` SET estado=?, fechaDevolucion=curdate() WHERE (`idPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, "Cerrado");
			stmt.setInt(2, p.getIdPrestamo());
			stmt.executeUpdate();	
            
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void actualizarLP(Prestamo p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE linea_prestamo lp \r\n"
							+ "INNER JOIN prestamo p ON \r\n"
							+ "lp.idPrestamo = p.idPrestamo \r\n"
							+ "SET devuelto=1, lp.fechaDevolucion= curdate() WHERE (lp.idPrestamo = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setInt(1, p.getIdPrestamo());
			stmt.executeUpdate();	
            
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	
	public LinkedList<Prestamo> getByIDMinimo() {
		Prestamo p =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo order by idPrestamo"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				p.setFechaADevoler(rs.getDate("fechaADevolver"));
				p.setIdPersona(rs.getInt("idPersona"));
				p.setEstado(rs.getString("estado"));
				prestamos.add(p);
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
		
		return prestamos;
	}
	public LinkedList<Prestamo> getByIDMayor() {
		Prestamo p =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo order by idPrestamo desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				p.setFechaADevoler(rs.getDate("fechaADevolver"));
				p.setIdPersona(rs.getInt("idPersona"));
				p.setEstado(rs.getString("estado"));
				prestamos.add(p);
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
		
		return prestamos;
	}

	public LinkedList<Prestamo> getByFechaMinimo() {
		Prestamo p =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo order by fechaPrestamo"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				p.setFechaADevoler(rs.getDate("fechaADevolver"));
				p.setIdPersona(rs.getInt("idPersona"));
				p.setEstado(rs.getString("estado"));
				prestamos.add(p);
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
		
		return prestamos;
	}
	
	public LinkedList<Prestamo> getByFechaMayor() {
		Prestamo p =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from prestamo order by fechaPrestamo desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				p.setFechaADevoler(rs.getDate("fechaADevolver"));
				p.setIdPersona(rs.getInt("idPersona"));
				p.setEstado(rs.getString("estado"));
				prestamos.add(p);
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
		
		return prestamos;
	}

	

	

}
