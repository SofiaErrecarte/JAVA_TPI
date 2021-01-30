<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.LibroProv"%>
    <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros por proveedor</title>
<% 
   LinkedList<LibroProv> list = (LinkedList<LibroProv>)request.getAttribute("listaLibrosProv");
%>
</head>
<body>
<div class="container">
		<div class="row">
        	<h4>Libros por Proveedor</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>ID Libro</th>
                    		    	<th>Titulo</th>
                        			<th>ISBN</th>
                        			<th>género</th>
                        			<th>Id Proveedor</th>
                        			<th>CUIT</th>
                        			<th>Razon Social</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (LibroProv libP : list) { %>
                    			<tr>
                    				<td><%=libP.getIdLibro()%></td>
                    				<td><%=libP.getTitulo()%></td>
                    				<td><%=libP.getIsbn()%></td>
                    				<td><%=libP.getGenero()%></td>
                    				<td><%=libP.getIdProveedor()%></td>
                    				<td><%=libP.getCUIT()%></td>
                    				<td><%=libP.getRazonSocial()%></td>
                    				<td>
                    				    
                    				</td>
                    			</tr>
                    		<% } %>
                    		</tbody>	
	 
	</div> <!-- /container --> 
	 <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Volver" value="Volver">
  </div>
  
</body>
</html>