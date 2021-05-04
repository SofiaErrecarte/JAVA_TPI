package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



import java.sql.Statement;
import entities.*;

public class DataProveedor extends DataMethods{
	
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

	public MyResult add(Proveedor prov) {
		int resultado = -1;
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
		Proveedor pr = new Proveedor();
		pr.setIdProveedor(0);
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

	public MyResult editProveedor(Proveedor prov) {
		int resultado = -1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM proveedor WHERE cuit=? and idProveedor!=?"
					);
			stmt.setString(1, prov.getCUIT());
			stmt.setInt(2, prov.getIdProveedor());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un proveedor con ese CUIT
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe un proveedor actualmente con ese CUIT(001).");
					return res;
				} else {
			stmt.close();
			
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
		res.setErr_message("Proveedor actualizado correctamente");
		return Update(1);
	}

	
	public MyResult deleteProveedor(Proveedor prov) {
		int r = 1;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT COUNT(*) FROM libro WHERE idProveedor=?"
					);
			stmt.setInt(1, prov.getIdProveedor());
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				// preguntamos si hay al menos un libro con ese proveedor
				if (rs.getInt(1) > 0) {
					MyResult res = new MyResult();
					res.setResult(MyResult.results.Err);
					res.setErr_message("Existe un libro actualmente con ese proveedor");
					return res;
				} else {
			stmt.close();
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM `biblioteca`.`proveedor` WHERE (`idProveedor` = ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, prov.getIdProveedor());
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
		res.setErr_message("Proveedor eliminado correctamente");
		return Delete(1);
	}
		
	public LinkedList<Proveedor> getByDesc(String nombuscar){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Proveedor> proveedores = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from proveedor where cuit LIKE '%" +nombuscar+ "%'"
					);
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Proveedor prov = new Proveedor();
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
	
	public LinkedList<Proveedor> getByIdMinimo() {
		Proveedor prov=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Proveedor> proveedores = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from proveedor order by idProveedor"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					prov = new Proveedor();
					prov.setCUIT(rs.getString("cuit"));
					prov.setRazonSocial(rs.getString("razonSocial"));
					prov.setTelefono(rs.getString("telefono"));
					prov.setMail(rs.getString("email"));
					prov.setDireccion(rs.getString("direccion"));
					prov.setIdProveedor(rs.getInt("idProveedor"));
					proveedores.add(prov);
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
		
		return proveedores;
	}
	
	public LinkedList<Proveedor> getByIdMaximo() {
		Proveedor prov=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Proveedor> proveedores = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from proveedor order by idProveedor desc"
					);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					prov = new Proveedor();
					prov.setCUIT(rs.getString("cuit"));
					prov.setRazonSocial(rs.getString("razonSocial"));
					prov.setTelefono(rs.getString("telefono"));
					prov.setMail(rs.getString("email"));
					prov.setDireccion(rs.getString("direccion"));
					prov.setIdProveedor(rs.getInt("idProveedor"));
					proveedores.add(prov);
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
		
		return proveedores;
	}
	

}
