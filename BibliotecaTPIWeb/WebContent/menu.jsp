<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Persona"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>menu</title>
<%
		Persona per = (Persona)session.getAttribute("usuario");
	%>

<body>
<div class="container">
		<div class="row">
        	<h4>Libros</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>email</th>
                    		    	<th>contraseña</th>
                        			
                      			</tr>
                      		</thead>
                    		<tbody>
                    			<tr>
                    				<td><%=per.getEmail()%></td>
                    				<td><%=per.getContraseña()%></td>
                    				<td>
                    				   
                    				</td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		</tbody>	
	</div> <!-- /container --> 
	
	<form  action="listarLibroServlet" method="post">
	<button type="submit">Listar Libros</button>
    </form>
    <form action="listarProveedorServlet" method="post">
    <button type="submit">Listar Proveedores</button>
 	</form> 
 	 
 	<form action="listarLibrosProvServlet" method="post">
    <button type="submit">Lista Libros por Proveedor</button>
 	</form> 

	<form action="listarPoliticaServlet" method="post">
    <button type="submit">Listar Politica de Prestamo</button>
 	</form> 

 	</form> 
 	

</body>
</html>