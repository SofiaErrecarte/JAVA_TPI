package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import entities.*;

public class DataLineaPrestamo extends DataMethods{

	public MyResult add(LineaPrestamo lp) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`linea_prestamo` (`devuelto`, `idPrestamo`, `idEjemplar`) VALUES(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							); 
			stmt.setBoolean(1, lp.isDevuelto());
			stmt.setLong(2, lp.getIdPrestamo());
			stmt.setLong(3, lp.getIdEjemplar());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                lp.setIdLineaPrestamo(keyResultSet.getInt(1));
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
	
	public MyResult editLineaPrestamo(LineaPrestamo lp) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`linea_prestamo` SET `devuelto` = ?, `idPrestamo` = ?, `idEjemplar` = ? WHERE (`idLineaPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setBoolean(1, lp.isDevuelto());
			stmt.setLong(2, lp.getIdPrestamo());
			stmt.setLong(3, lp.getIdEjemplar());
			stmt.setInt(4, lp.getIdLineaPrestamo());
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
	
	public LineaPrestamo deleteLineaPrestamo (LineaPrestamo lp) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`linea_prestamo` WHERE (`idLineaPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, lp.getIdLineaPrestamo());
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
		return lp;
	}
	
	public LinkedList<LineaPrestamo> getLineasByIdPrest(Prestamo p) {
		LineaPrestamo lp =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<LineaPrestamo> lineasP = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select T0.idLineaPrestamo, T0.fechaDevolucion, T0.devuelto, T0.idPrestamo, T0.idEjemplar, T2.titulo from linea_prestamo T0\r\n" + 
					"inner join ejemplar T1 on T0.idEjemplar = T1.idEjemplar\r\n" + 
					"inner join libro T2 on T1.idLibro = T2.idLibro\r\n" + 
					"where T0.idLineaPrestamo =?"
					);
			stmt.setLong(1, p.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				lp = new LineaPrestamo();
				lp.setIdEjemplar(rs.getInt("T0.idEjemplar"));
				lp.setIdLineaPrestamo(rs.getInt("T0.idLineaPrestamo"));
				lp.setFechaDevolucion(rs.getDate("T0.fechaDevolucion"));
				lp.setDevuelto(rs.getBoolean("T0.devuelto"));
				lp.setIdPrestamo(rs.getInt("T0.idPrestamo"));
				lp.setTituloEjemplar(rs.getString("T2.titulo"));
				lineasP.add(lp);
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
		
		return lineasP;
	}
	
	public LineaPrestamo getById(LineaPrestamo lpr) {
		LineaPrestamo lp = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from linea_prestamo where idLineaPrestamo=?"
					);
			stmt.setLong(1, lpr.getIdLineaPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				lp = new LineaPrestamo();
				lp.setIdEjemplar(rs.getInt("idEjemplar"));
				lp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
				lp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				lp.setDevuelto(rs.getBoolean("devuelto"));
				lp.setIdPrestamo(rs.getInt("idPrestamo"));
				
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
		
		return lp;
	}
}
