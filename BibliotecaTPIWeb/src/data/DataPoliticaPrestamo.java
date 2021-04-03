package data;


import java.util.LinkedList;
import entities.*;

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
		
		
		return politicas;
	}
	
	public PoliticaPrestamo getLast() {
		Statement stmt=null;
		ResultSet rs=null;
		PoliticaPrestamo pp = new PoliticaPrestamo();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT * FROM politica_prestamo where fechaAlta = (select max(fechaAlta) from politica_prestamo);");
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
							"insert into politica_prestamo(cantMaximaSocio,cantMaximaNoSocio,fechaAlta) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setInt(1, pp.getCantMaximaSocio());
			stmt.setInt(2, pp.getCantMaximaNoSocio());
			stmt.setDate(3, (Date) pp.getFechaAlta());
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
		PoliticaPrestamo pp = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idPolitica,fechaAlta,cantMaximaSocio,cantMaximaNoSocio from politica_prestamo where idPolitica=?"
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
			e.printStackTrace();
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
}
