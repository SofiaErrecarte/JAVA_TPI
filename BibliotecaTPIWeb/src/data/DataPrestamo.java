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
import entities.Prestamo;

public class DataPrestamo {
	public LinkedList<Prestamo> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos= new LinkedList<>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from prestamo");
			if(rs!=null) {
				while(rs.next()) {
					Prestamo p = new Prestamo();
					p.setIdPrestamo(rs.getInt("idPrestamo"));
					p.setFechaPrestamo(rs.getDate("fechaHoraPrestamo"));
					p.setFechaADevoler(rs.getDate("FechaADevolver"));
					p.setIdPersona(rs.getInt("idPersona"));
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
		
		
		return prestamos;
	}
	
	public Prestamo add(Prestamo p ) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`prestamo` (`fechaHoraPrestamo`, `fechaADevolver`, `idPersona`) VALUES(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							); //, `fechaEdicion`
			stmt.setDate(1, (java.sql.Date) p.getFechaPrestamo());
			stmt.setDate(2, (java.sql.Date) p.getFechaADevoler());
			stmt.setLong(3,p.getIdPersona());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPrestamo(keyResultSet.getInt(1));
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
		
		return p;
	}
	
	public Prestamo editPrestamo(Prestamo p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`prestamo` SET `fechaHoraPrestamo` = ?, `fechaADevolver` = ?, `idPersona` = ? WHERE (`idLineaPrestamo` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setTimestamp(1, new java.sql.Timestamp(p.getFechaPrestamo().getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(p.getFechaADevoler().getTime()));
			stmt.setLong(3, p.getIdPersona());
			//stmt.setTimestamp(6, new java.sql.Timestamp(lib.getFechaEdicion().getTime()));
			//stmt.setTimestamp(6, null);
			
			stmt.setInt(4, p.getIdPrestamo());
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
					"select * from linea_prestamo where idPrestamo=?"
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
	


}
