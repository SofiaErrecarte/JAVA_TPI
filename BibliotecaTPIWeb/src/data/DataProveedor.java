package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.Statement;

import entities.*;

public class DataProveedor {
	
	public LinkedList<Proveedor> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Proveedor> proveedores= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from proveedor");
			if(rs!=null) {
				while(rs.next()) {
					Proveedor prov=new Proveedor();
					prov.setCUIT(rs.getString("cuit"));
					prov.setRazonSocial(rs.getString("razonSocial"));
					prov.setDireccion(rs.getString("direccion"));
					prov.setMail(rs.getString("email"));
					prov.setTelefono(rs.getString("telefono"));
					prov.setIdProveedor(rs.getInt("idProveedor"));
					proveedores.add(prov);
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
		
		
		return proveedores;
	}

	public Proveedor add(Proveedor prov) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `biblioteca`.`proveedor` (`cuit`, `razonSocial`, `telefono`, `email`, `direccion`) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, prov.getCUIT());
			stmt.setString(2, prov.getRazonSocial());
			stmt.setString(3, prov.getTelefono());
			stmt.setString(4, prov.getMail());
			stmt.setString(5, prov.getDireccion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                prov.setIdProveedor(keyResultSet.getInt(1));
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
		
		return prov;
	}

	public Proveedor getByCUIT(Proveedor prov) {
		Proveedor pr = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from proveedor where cuit=?");
			stmt.setString(1, prov.getCUIT());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				pr = new Proveedor();
				pr.setCUIT(rs.getString("cuit"));
				pr.setRazonSocial(rs.getString("razonSocial"));
				pr.setDireccion(rs.getString("direccion"));
				pr.setMail(rs.getString("email"));
				pr.setTelefono(rs.getString("telefono"));
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
		
		return pr;
	}
	
	public Proveedor getById(Proveedor prov) {
		Proveedor pr = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from proveedor where idProveedor=?");
			stmt.setInt(1, prov.getIdProveedor());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				pr = new Proveedor();
				pr.setIdProveedor(rs.getInt("idProveedor"));
				pr.setCUIT(rs.getString("cuit"));
				pr.setRazonSocial(rs.getString("razonSocial"));
				pr.setDireccion(rs.getString("direccion"));
				pr.setMail(rs.getString("email"));
				pr.setTelefono(rs.getString("telefono"));
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
		
		return pr;
	}

	public Proveedor editProveedor(Proveedor prov) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `biblioteca`.`proveedor` SET `cuit` = ?, `razonSocial` = ?, `telefono` = ?, `email` = ?, `direccion` = ? WHERE (`idProveedor` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, prov.getCUIT());
			stmt.setString(2, prov.getRazonSocial());
			stmt.setString(3, prov.getTelefono());
			stmt.setString(4, prov.getMail());
			stmt.setString(5, prov.getDireccion());
			stmt.setLong(6, prov.getIdProveedor());
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
		return prov;
	}

	public Proveedor deleteProveedor(Proveedor prov) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`proveedor` WHERE (`idProveedor` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, prov.getIdProveedor());
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
		return prov;
	}
	
	
		
	

}
