package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import entities.*;

public class DataLineaPrestamo {

	public LineaPrestamo add(LineaPrestamo lp) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`linea_prestamo` (`fechaDevolucion`, `devuelto`, `idPrestamo`, `idEjemplar`) VALUES(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							); //, `fechaEdicion`
			stmt.setDate(1, lp.getFechaDevolucion());
			stmt.setBoolean(2, lp.isDevuelto());
			stmt.setLong(3, lp.getIdPrestamo());
			stmt.setLong(4, lp.getIdEjemplar());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                lp.setIdLineaPrestamo(keyResultSet.getInt(1));
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
		return lp;
		
	}
	
	public LineaPrestamo editLineaPrestamo(LineaPrestamo lp) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`linea_prestamo` SET `fechadevolucion` = ?, `devuelto` = ?, `idPrestamo` = ?, `idEjemplar` = ? WHERE (`idLineaPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setTimestamp(6, new java.sql.Timestamp(lp.getFechaDevolucion().getTime()));
			stmt.setBoolean(2, lp.isDevuelto());
			stmt.setLong(3, lp.getIdPrestamo());
			stmt.setLong(4, lp.getIdEjemplar());
			//stmt.setTimestamp(6, new java.sql.Timestamp(lib.getFechaEdicion().getTime()));
			//stmt.setTimestamp(6, null);
			
			stmt.setInt(5, lp.getIdLineaPrestamo());
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
	
	
}
