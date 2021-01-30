<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Proveedores</title>
<% 
   LinkedList<Proveedor> list = (LinkedList<Proveedor>)request.getAttribute("listaProveedores");
%>
</head>
<body>
<div class="container">
		<div class="row">
        	<h4>Proveedores</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>Id Proveedor</th>
                    				<th>CUIT</th>
                    		    	<th>Razón Social</th>
                        			<th>Teléfono</th>
                        			<th>E-Mail</th>
                        			<th>Dirección</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Proveedor prov : list) { %>
                    			<tr>
                    				<td><%=prov.getIdProveedor()%></td>
                    				<td><%=prov.getCUIT()%></td>
                    				<td><%=prov.getRazonSocial() %></td>
                    				<td><%=prov.getTelefono()%></td>
                    				<td><%=prov.getMail()%></td>
                    				<td><%=prov.getDireccion()%></td>
                    				<td>
                    				    
                    				</td>
                    				<td> <a class="editbutton"
									href="modificarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Editar </a>
										<a class="deletebutton"
									href="borrarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Eliminar</a></td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		<% } %>
                    		</tbody>	
	 
	</div> <!-- /container --> 
	<form action="agregarProveedor.jsp" method="post">
    <button type="submit">Agregar Proveedor</button>
 	</form> 
 	<form action="borrarProveedor.jsp" method="post">
    <button type="submit">Borrar Proveedor</button>
 	</form>
 	<form  action="modificarProveedor.jsp" method="post">
    <button type="submit">Modificar Proveedor</button>
    </form>
  
 <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Volver" value="Volver">
  </div>

									
                    				
</body>
</html>