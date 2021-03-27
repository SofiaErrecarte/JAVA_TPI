package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import entities.*;
import java.sql.Statement;
import entities.MyResult;


public class DataLibro extends DataMethods{
	
	public LinkedList<Libro> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros= new LinkedList<>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from libro");
			if(rs!=null) {
				while(rs.next()) {
					Libro lib=new Libro();
					lib.setIdLibro(rs.getInt("idLibro"));
					lib.setTitulo(rs.getString("titulo"));
					lib.setIsbn(rs.getInt("isbn"));
					lib.setFechaEdicion(rs.getDate("fechaEdicion"));
					lib.setNroEdicion( rs.getInt("nroEdicion"));
					lib.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					lib.setGenero(rs.getString("genero"));
					lib.setIdProveedor(rs.getInt("idProveedor"));
					
					libros.add(lib);
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
		
		
		return libros;
	}
	
	
	public LinkedList<LibroProv> getLibrosPorProv(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<LibroProv> librosProv= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from libro inner join proveedor on libro.idProveedor = proveedor.idProveedor\r\n" + 
					"");
			if(rs!=null) {
				while(rs.next()) {
					LibroProv libP = new LibroProv();
					libP.setIdLibro(rs.getInt("idLibro"));
					libP.setTitulo(rs.getString("titulo"));
					libP.setIsbn(rs.getInt("isbn"));
					libP.setGenero(rs.getString("genero"));
					libP.setIdProveedor(rs.getInt("idProveedor"));
					libP.setCUIT(rs.getString("cuit"));
					libP.setRazonSocial(rs.getString("razonSocial"));
					
					
					librosProv.add(libP);
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
		
		
		return librosProv;
	}
	
	
	public Libro add(Libro lib) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`libro` ( `titulo`, `isbn`, `nroEdicion`, `cantDiasMaxPrestamo`, `genero`, `idProveedor`) VALUES(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							); //, `fechaEdicion`
			stmt.setString(1, lib.getTitulo());
			stmt.setLong(2, lib.getIsbn());
			stmt.setLong(3, lib.getNroEdicion());
			stmt.setLong(4, lib.getCantDiasMaxPrestamo());
			stmt.setString(5, lib.getGenero());	
			stmt.setInt(6, lib.getIdProveedor());
			//stmt.setTimestamp(7, new java.sql.Timestamp(lib.getFechaEdicion().getTime()));
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                lib.setIdLibro(keyResultSet.getInt(1));
            }
			
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
		
		return lib;
	}
	
	public Libro getById(Libro lib) {
		Libro l = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro where idLibro=?");
			stmt.setInt(1, lib.getIdLibro());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setIsbn(rs.getInt("isbn"));
				l.setTitulo(rs.getString("titulo"));
				l.setFechaEdicion(rs.getDate("fechaEdicion"));
				l.setNroEdicion(rs.getInt("nroEdicion"));
				l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
				l.setGenero(rs.getString("genero"));
				l.setIdProveedor(rs.getInt("idProveedor"));
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
		
		return l;
	}

	public Libro editLibro(Libro lib) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`libro` SET `titulo` = ?, `isbn` = ?, `nroEdicion` = ?, `cantDiasMaxPrestamo` = ?, `genero` = ?, `idProveedor` = ? WHERE (`idLibro` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, lib.getTitulo());
			stmt.setLong(2, lib.getIsbn());
			stmt.setLong(3, lib.getNroEdicion());
			stmt.setLong(4, lib.getCantDiasMaxPrestamo());
			stmt.setString(5, lib.getGenero());	
			//stmt.setTimestamp(6, new java.sql.Timestamp(lib.getFechaEdicion().getTime()));
			//stmt.setTimestamp(6, null);
			stmt.setInt(6, lib.getIdProveedor());
			
			stmt.setInt(7,  lib.getIdLibro());
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
		return lib;
	}

	public MyResult deleteLibro(Libro lib) {
		int r = 1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM ejemplar WHERE idLibro=? and disponible=0"
					);
			stmt.setInt(1, lib.getIdLibro());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un libro con ese proveedor
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe al menos un préstamo de un ejemplar de este libro.");
					return res;
				} else {
			stmt.close();
		
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`libro` WHERE (`idLibro` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, lib.getIdLibro());
			r = stmt.executeUpdate();
			if (r == 0) {
				return Delete(0);
			}
				}}
            
		}  catch (SQLException e) {
			return Delete(0);
		} finally {
            try {
                if(rs!=null) {rs.close();}
                if(stmt!=null) {stmt.close();}
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		// si llego hasta aca esta todo OK
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		res.setErr_message("Libro eliminado correctamente");
		return res;
	}
	
	

	public LinkedList<Libro> getByDesc(String nombuscar){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from libro where titulo LIKE '%" +nombuscar+ "%'"
					);
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idLibro"));
					l.setIsbn(rs.getInt("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setNroEdicion(rs.getInt("nroEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setGenero(rs.getString("genero"));
					l.setIdProveedor(rs.getInt("idProveedor"));
					libros.add(l);
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
		
		
		return libros;
	}

	
	//EJEMPLAR
	
	public LinkedList<Ejemplar> getAllEjemplares(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejemplar> ejemplares= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idEjemplar, idLibro, disponible from ejemplar");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Ejemplar ej = new Ejemplar();
					ej.setIdEjemplar(rs.getInt("idEjemplar"));
					ej.setIdLibro(rs.getInt("idLibro"));
					ej.setDisponible(rs.getBoolean("disponible"));
					ejemplares.add(ej);
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
		
		
		return ejemplares;
	}

	public LinkedList<Ejemplar> getEjByIdLibro(Libro lib) {
		Ejemplar ej =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejemplar> ejemplares = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idEjemplar,idLibro,disponible from ejemplar where idLibro=?"
					);
			stmt.setLong(1, lib.getIdLibro());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				ej = new Ejemplar();
				ej.setIdEjemplar(rs.getInt("idEjemplar"));
				ej.setIdLibro(rs.getInt("idLibro"));
				ej.setDisponible(rs.getBoolean("disponible"));
				ejemplares.add(ej);
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
		
		return ejemplares;
	}
	
	
	//me devuelve la cant de ejemplares diponibles de un libro
	public int cantEjemLibroDisponibles(Libro lib) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int cantDisp=0;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select count(idEjemplar) 'cantidad' FROM ejemplar where idLibro=? and disponible=1"
					);
			stmt.setLong(1, lib.getIdLibro());
			rs=stmt.executeQuery();
			if(rs!=null) {
				cantDisp = rs.getInt("cantidad");
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
		return cantDisp;
		
	}
	
	public Ejemplar addEjemplar(Libro l) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		Ejemplar ej = new Ejemplar();
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`ejemplar` ( `idLibro`,`disponible` ) VALUES(?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setLong(1, l.getIdLibro());
			stmt.setBoolean(2,  true);

            stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
                ej.setIdEjemplar(keyResultSet.getInt(1));
			}

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
		return ej;
    }

	public Ejemplar deleteEjemplar(Ejemplar ej) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`ejemplar` WHERE (`idEjemplar` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setLong(1, ej.getIdEjemplar());
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
		return ej;
		
	}
	
	public Ejemplar deleteEjemplarPorIdLibro(Ejemplar ej, Libro lib) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`ejemplar` WHERE (`idEjemplar` = ? and `idLibro` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setLong(1, ej.getIdEjemplar());
			stmt.setLong(1, lib.getIdLibro());
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
		return ej;
		
	}


	public Libro getByISBN(Libro lib) {
		Libro l = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro where isbn=?");
			stmt.setInt(1, lib.getIsbn());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setIsbn(rs.getInt("isbn"));
				l.setTitulo(rs.getString("titulo"));
				l.setFechaEdicion(rs.getDate("fechaEdicion"));
				l.setNroEdicion(rs.getInt("nroEdicion"));
				l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
				l.setGenero(rs.getString("genero"));
				l.setIdProveedor(rs.getInt("idProveedor"));
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
		
		return l;
	}

	
	
	
	
	/*public Ejemplar getByIdEjemplar(Ejemplar ej) {
		Ejemplar ejemp = null;
		DataLibro dl = new DataLibro();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idEjemplar,idLibro,idLineaPrestamo from ejemplar where idEjemplar=?"
					);
			stmt.setLong(1, ej.getIdEjemplar());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				ejemp = new Ejemplar();
				dl.setLibro(ejemp);
				ejemp.setIdEjemplar(rs.getInt("idEjemplar"));
				ejemp.getLib().setIdLibro(rs.getInt("idLibro"));
				ejemp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
				
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
		
		return ejemp;
	}*/
	
	
		
	

}
