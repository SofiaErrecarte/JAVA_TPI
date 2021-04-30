package data;


import java.util.LinkedList;
import entities.*;
import oracle.jrockit.jfr.tools.ConCatRepository;

import java.sql.*;

public class DataPoliticaPrestamo extends DataMethods{

	public LinkedList<PoliticaPrestamo> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPolitica,fechaAlta,cantMaximaSocio,cantMaximaNoSocio from politica_prestamo order by fechaAlta desc");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					PoliticaPrestamo pp = new PoliticaPrestamo();
					pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
					pp.setFechaAlta(rs.getDate("fechaAlta"));
					pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
					pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
					
					politicas.add(pp);
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
		if(politicas.isEmpty()) {
			PoliticaPrestamo pp = new PoliticaPrestamo();
			pp.setIdPoliticaPrestamo(0);
			pp.setFechaAlta(null);
			pp.setCantMaximaSocio(0);
			pp.setCantMaximaNoSocio(0);
			politicas.add(pp);
		}
		
		return politicas;
	}
	
	public PoliticaPrestamo getLast(Prestamo p) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		PoliticaPrestamo pp = new PoliticaPrestamo();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
			"SELECT * FROM politica_prestamo T0 WHERE\r\n" + 
					"					T0.fechaAlta = (SELECT MAX(T1.fechaAlta) FROM\r\n" + 
					"					(SELECT * FROM politica_prestamo WHERE fechaAlta <= concat(?, ' 00:00:00')) as T1)");
			stmt.setDate(1, (Date) p.getFechaPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null  && rs.next()) {
					pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
					pp.setFechaAlta(rs.getDate("fechaAlta"));
					pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
					pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
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
		
		
		return pp;
	}
	
	
	public MyResult add(PoliticaPrestamo pp) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into politica_prestamo(cantMaximaSocio,cantMaximaNoSocio,fechaAlta) values(?,?,NOW())",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setInt(1, pp.getCantMaximaSocio());
			stmt.setInt(2, pp.getCantMaximaNoSocio());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                pp.setIdPoliticaPrestamo(keyResultSet.getInt(1));
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

	public PoliticaPrestamo getById(PoliticaPrestamo poliprest) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		PoliticaPrestamo pp = new PoliticaPrestamo();
		pp.setIdPoliticaPrestamo(0);
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo where idPolitica=?"
					);
			stmt.setInt(1, poliprest.getIdPoliticaPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
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
		
		return pp;
	}

	public MyResult editPolitica(PoliticaPrestamo pp) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`politica_prestamo` SET `cantMaximaSocio` = ?, `cantMaximaNoSocio` = ? WHERE (`idPolitica` = ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1,pp.getCantMaximaSocio());
			stmt.setInt(2,pp.getCantMaximaNoSocio());
			stmt.setInt(3,pp.getIdPoliticaPrestamo());
			stmt.executeUpdate();
			
			
		}  catch (SQLException e) {
			//e.printStackTrace();
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
		res.setErr_message("Política actualizada correctamente");
		return Update(1);
	}
	
	public MyResult deletePolitica(PoliticaPrestamo pp) {
		int r = 1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`politica_prestamo` WHERE (`idPolitica` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setLong(1, pp.getIdPoliticaPrestamo());
			r = stmt.executeUpdate();	
			keyResultSet=stmt.getGeneratedKeys();
			 if(keyResultSet!=null && keyResultSet.next()){
	               pp.setIdPoliticaPrestamo(keyResultSet.getInt(1));
	            }
			 if (r == 0) {
					return Delete(0);
				}
            
		}  catch (SQLException e) {
			return Delete(0);
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	ConnectCloseError();
            }
		}
		// si llego hasta aca esta todo OK
		MyResult res = new MyResult();
		res.setResult(MyResult.results.OK);
		res.setErr_message("Política eliminada correctamente");
		return Delete(1);
		
	}
	
	public LinkedList<PoliticaPrestamo> getByDesc (String nombuscar) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> pps = new LinkedList<>();
		PoliticaPrestamo pp;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo where id LIKE '%\" +nombuscar+ \"%'"
					);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
				pps.add(pp);
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
		
		return pps;
	}
	

	public LinkedList<PoliticaPrestamo> getbybusqueda(int nombuscar){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPolitica,fechaAlta,cantMaximaSocio,cantMaximaNoSocio from politica_prestamo where idPolitica LIKE "+"'%"+"nombuscar"+"%'"
					);
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					PoliticaPrestamo pp = new PoliticaPrestamo();
					pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
					pp.setFechaAlta(rs.getDate("fechaAlta"));
					pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
					pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
					
					politicas.add(pp);
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
		
		
		return politicas;
	}
	
	public LinkedList<PoliticaPrestamo> getByFechaMinima() {
		PoliticaPrestamo pp=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo order by fechaAlta"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				politicas.add(pp);
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
		
		return politicas;
	}

	
	public LinkedList<PoliticaPrestamo> getByFechaMaxima() {
		PoliticaPrestamo pp=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo order by fechaAlta desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				politicas.add(pp);
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
		
		return politicas;
	}
	
	public LinkedList<PoliticaPrestamo> getByIdMinimo() {
		PoliticaPrestamo pp=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo order by idPolitica"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				politicas.add(pp);
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
		
		return politicas;
	}
	
	public LinkedList<PoliticaPrestamo> getByIdMaxima() {
		PoliticaPrestamo pp=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<PoliticaPrestamo> politicas = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from politica_prestamo order by idPolitica desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				pp = new PoliticaPrestamo();
				pp.setCantMaximaSocio(rs.getInt("cantMaximaSocio"));
				pp.setCantMaximaNoSocio(rs.getInt("cantMaximaNoSocio"));
				pp.setFechaAlta(rs.getDate("fechaAlta"));
				pp.setIdPoliticaPrestamo(rs.getInt("idPolitica"));
				politicas.add(pp);
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
		
		return politicas;
	}
}
