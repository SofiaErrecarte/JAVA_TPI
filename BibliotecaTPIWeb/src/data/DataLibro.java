package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.cj.jdbc.Blob;

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
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT * FROM libro inner join proveedor on proveedor.idProveedor=libro.idProveedor;");
			if(rs!=null) {
				while(rs.next()) {
					Libro lib=new Libro();
					lib.setIdLibro(rs.getInt("idLibro"));
					lib.setTitulo(rs.getString("titulo"));
					lib.setIsbn(rs.getInt("isbn"));
					lib.setNroEdicion( rs.getInt("nroEdicion"));
					lib.setGenero(rs.getString("genero"));
					lib.setRazonSocialProv(rs.getString("razonSocial"));
					lib.setCUIT(rs.getString("cuit"));
					lib.setImagen(rs.getBytes("imagen"));
					lib.setAutor(rs.getString("autor"));
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
		if(libros.isEmpty()) {
			Libro lib=new Libro();
			lib.setIdLibro(0);
			lib.setTitulo(null);
			lib.setIsbn(0);
			lib.setNroEdicion(0);
			lib.setGenero(null);
			lib.setRazonSocialProv(null);
			lib.setCUIT(null);
			lib.setImagen(null);
			lib.setAutor(null);
			libros.add(lib);
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
		
	public MyResult add(Libro lib) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`libro` ( `titulo`,`autor`,`isbn`, `nroEdicion`, `genero`, `idProveedor`, `imagen` ) VALUES(?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							); 
			stmt.setString(1, lib.getTitulo());
			stmt.setString(2, lib.getAutor());
			stmt.setLong(3, lib.getIsbn());
			stmt.setLong(4, lib.getNroEdicion());
			stmt.setString(5, lib.getGenero());	
			stmt.setInt(6, lib.getIdProveedor());
			stmt.setBlob(7, lib.getImagen_carga());
				
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                lib.setIdLibro(keyResultSet.getInt(1));
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
	
	public Libro getById(Libro lib) {
		Libro l = new Libro();
		l.setIdLibro(0);
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
				l.setNroEdicion(rs.getInt("nroEdicion"));
				l.setGenero(rs.getString("genero"));
				l.setIdProveedor(rs.getInt("idProveedor"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
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

	public MyResult editLibro(Libro lib) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM libro WHERE isbn=? and idLibro!=?"
					);
			stmt.setInt(1, lib.getIsbn());
			stmt.setInt(2, lib.getIdLibro());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un librocon ese isbn
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe un libro actualmente con ese ISBN");
					return res;
				} else {
			stmt.close();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`libro` SET `titulo` = ?, `isbn` = ?, `nroEdicion` = ?, `genero` = ?, `idProveedor` = ?, `autor` = ? WHERE (`idLibro` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, lib.getTitulo());
			stmt.setLong(2, lib.getIsbn());
			stmt.setLong(3, lib.getNroEdicion());
			stmt.setString(4, lib.getGenero());	
			stmt.setInt(5, lib.getIdProveedor());
			stmt.setString(6, lib.getAutor());
			stmt.setInt(7,  lib.getIdLibro());
			stmt.executeUpdate();
			
		}}}  catch (SQLException e) {
			//e.printStackTrace();
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
		res.setErr_message("Libro actualizado correctamente");
		return Update(1);
	}

	public MyResult deleteLibro(Libro lib) {
		int r = 1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM ejemplar T0 inner join linea_prestamo T1 on T0.idEjemplar=T1.idEjemplar WHERE T0.idLibro=?"
					);
			stmt.setInt(1, lib.getIdLibro());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un libro con ese proveedor
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe al menos un ejemplar de este libro asignado a un préstamo. Por favor, revise los ejemplares que no hayan sido borrados.");					return res;
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
			rs= stmt.executeQuery("select * from libro where LOWER(titulo) LIKE '%" +nombuscar+ "%'"
					);
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idLibro"));
					l.setIsbn(rs.getInt("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setNroEdicion(rs.getInt("nroEdicion"));
					l.setGenero(rs.getString("genero"));
					l.setIdProveedor(rs.getInt("idProveedor"));
					l.setImagen(rs.getBytes("imagen"));
					l.setAutor(rs.getString("autor"));
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
			rs= stmt.executeQuery("SELECT ejemplar.idEjemplar, ejemplar.disponible, ejemplar.idLibro, libro.titulo, libro.titulo\r\n" + 
					"FROM ejemplar inner join libro  on libro.idLibro=ejemplar.idLibro;");
			
			if(rs!=null) {
				while(rs.next()) {
					Ejemplar ej = new Ejemplar();
					ej.setIdEjemplar(rs.getInt("idEjemplar"));
					ej.setIdLibro(rs.getInt("idLibro"));
					ej.setDisponible(rs.getBoolean("disponible"));
					ej.setTitulo(rs.getString("titulo"));
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
		if (ejemplares.isEmpty()) {
			Ejemplar ej = new Ejemplar();
			ej.setIdEjemplar(0);
			ej.setIdLibro(0);
			ej.setDisponible(false);
			ej.setTitulo(null);
			ejemplares.add(ej);
		}
		
		return ejemplares;
	}
	public LinkedList<Ejemplar> getEjemplaresPrestamo(Prestamo p) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejemplar> ejemplares= new LinkedList<>();
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement("select T0.idEjemplar, T0.idLibro, T0.disponible from ejemplar T0 inner join linea_prestamo T1 on T0.idEjemplar=T1.idEjemplar"
					+ " inner join prestamo T2 on T1.idPrestamo=T2.idPrestamo where T2.idPrestamo=?");
			
			stmt.setLong(1, p.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Ejemplar ej = new Ejemplar();
					ej.setIdEjemplar(rs.getInt("T0.idEjemplar"));
					ej.setIdLibro(rs.getInt("T0.idLibro"));
					ej.setDisponible(rs.getBoolean("T0.disponible"));
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
	public LinkedList<Ejemplar> getAllEjemplaresDisponibles(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejemplar> ejemplares= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT idEjemplar, disponible, libro.idLibro, titulo \r\n"
					+ "FROM ejemplar inner join libro on libro.idLibro=ejemplar.idLibro \r\n"
					+ "where ejemplar.disponible=true;");
			
			if(rs!=null) {
				while(rs.next()) {
					Ejemplar ej = new Ejemplar();
					ej.setIdEjemplar(rs.getInt("idEjemplar"));
					ej.setIdLibro(rs.getInt("libro.idLibro"));
					ej.setDisponible(rs.getBoolean("disponible"));
					ej.setTitulo(rs.getString("titulo"));
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
					"select count(idEjemplar) FROM ejemplar where idLibro=? and disponible=1"
					);
			stmt.setLong(1, lib.getIdLibro());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				cantDisp = rs.getInt(1);
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
	
	//modifico el estado a no disponible del ejemeplar
	public void setDisponible(Ejemplar ej, boolean disponible) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`ejemplar` SET `disponible` = ? WHERE (`idEjemplar` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setBoolean(1,  disponible);
			stmt.setLong(2, ej.getIdEjemplar());
			

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
    }
	
	public void setAllDisponibles(Prestamo p) {
		LinkedList<Ejemplar> ejemplares = getEjemplaresPrestamo(p);
		for (Ejemplar ej : ejemplares) {
			setDisponible(ej, true);
		}
		
	}

	public MyResult deleteEjemplar(Ejemplar ej) {
		int r = 1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			//CONDICIÓN 1
			//verifico que disponible=false, lo que significaría que está prestado en un préstamo ABIERTO
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM ejemplar T0 \r\n" + 
					"inner join linea_prestamo T1 on T0.idEjemplar=T1.idEjemplar \r\n" + 
					"inner join prestamo T2 on T1.idPrestamo=T2.idPrestamo \r\n" + 
					"where T0.idEjemplar=? \r\n" + 
					"and T2.estado='Abierto'"
					);
			stmt.setInt(1, ej.getIdEjemplar());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("El ejemplar está asignado a un préstamo abierto.");
					return res;
				} else {
			stmt.close();
			//CONDICIÓN 2
			//verifico que disponible=true, lo que significaría que está prestado en un préstamo CERRADO o DE BAJA
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM ejemplar T0 \r\n" + 
					"inner join linea_prestamo T1 on T0.idEjemplar=T1.idEjemplar \r\n" + 
					"inner join prestamo T2 on T1.idPrestamo=T2.idPrestamo \r\n" + 
					"where T0.idEjemplar=? \r\n" + 
					"and (T2.estado='Cerrado' or T2.estado='De Baja')"
					);
			stmt.setInt(1, ej.getIdEjemplar());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Warning);
					res.setErr_message("El ejemplar está asignado a un préstamo cerrado o dado de baja. Su estado ha cambiado a NO disponible.");
					return res;
				} else {
			stmt.close();
			
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`ejemplar` WHERE (`idEjemplar` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, ej.getIdEjemplar());
			r = stmt.executeUpdate();
			if (r == 0) {
				return Delete(0);
			} }}
			
				}}
		}  catch (SQLException e) {
			return Delete(0);
		} finally {
            try {
                if(rs!=null)rs.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		res.setErr_message("Ejemplar eliminado correctamente");
		return Delete(1);
		
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
				l.setNroEdicion(rs.getInt("nroEdicion"));
				//l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
				l.setGenero(rs.getString("genero"));
				l.setIdProveedor(rs.getInt("idProveedor"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
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

	public Ejemplar getByIdEjemplar(Ejemplar ej) {
		Ejemplar ejemp = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from ejemplar where idEjemplar=?"
					);
			stmt.setLong(1, ej.getIdEjemplar());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				ejemp = new Ejemplar();
				ejemp.setIdEjemplar(rs.getInt("idEjemplar"));
				ejemp.setIdLibro(rs.getInt("idLibro"));
				ejemp.setDisponible(rs.getBoolean("disponible"));
				
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
		
		return ejemp;
	}
	
	public LinkedList<Libro> getByTituloA() {
		Libro l =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro inner join proveedor on proveedor.idProveedor=libro.idProveedor order by titulo asc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setIsbn(rs.getInt("isbn"));
				l.setNroEdicion( rs.getInt("nroEdicion"));
				l.setIsbn(rs.getInt("isbn"));
				l.setGenero(rs.getString("genero"));
				l.setRazonSocialProv(rs.getString("razonSocial"));
				l.setCUIT(rs.getString("cuit"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
				libros.add(l);
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
		
		return libros;
	}
	
	public LinkedList<Libro> getByTituloZ() {
		Libro l =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro inner join proveedor on proveedor.idProveedor=libro.idProveedor order by titulo desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setIsbn(rs.getInt("isbn"));
				l.setNroEdicion( rs.getInt("nroEdicion"));
				l.setIsbn(rs.getInt("isbn"));
				l.setGenero(rs.getString("genero"));
				l.setRazonSocialProv(rs.getString("razonSocial"));
				l.setCUIT(rs.getString("cuit"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
				libros.add(l);
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
		
		return libros;
	}
	
	public LinkedList<Libro> getByIdMinimo() {
		Libro l =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro inner join proveedor on proveedor.idProveedor=libro.idProveedor order by idLibro"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setNroEdicion( rs.getInt("nroEdicion"));
				l.setGenero(rs.getString("genero"));
				l.setIsbn(rs.getInt("isbn"));
				l.setRazonSocialProv(rs.getString("razonSocial"));
				l.setCUIT(rs.getString("cuit"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
				libros.add(l);
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
		
		return libros;
	}
	
	public LinkedList<Libro> getByIDMaximo() {
		Libro l =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro inner join proveedor on proveedor.idProveedor=libro.idProveedor order by idLibro desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setNroEdicion( rs.getInt("nroEdicion"));
				l.setIsbn(rs.getInt("isbn"));
				l.setGenero(rs.getString("genero"));
				l.setRazonSocialProv(rs.getString("razonSocial"));
				l.setCUIT(rs.getString("cuit"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
				libros.add(l);
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
		
		return libros;
	}
	
	public LinkedList<Libro> getByProveedores() {
		Libro l =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from libro inner join proveedor on proveedor.idProveedor=libro.idProveedor order by razonSocial"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				l.setNroEdicion( rs.getInt("nroEdicion"));
				l.setIsbn(rs.getInt("isbn"));
				l.setGenero(rs.getString("genero"));
				l.setRazonSocialProv(rs.getString("razonSocial"));
				l.setCUIT(rs.getString("cuit"));
				l.setImagen(rs.getBytes("imagen"));
				l.setAutor(rs.getString("autor"));
				libros.add(l);
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
		
		return libros;
	}


	
	


	

	
		
	

}
