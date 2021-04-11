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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<div class="container"  style = "font-family:arial; size=3">
		<div class="row">
        	<h4>Libros por Proveedor</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table"  class="text-center">
                    		<thead>
                    			<tr>
                    				<th class="text-center">ID Libro</th>
                    		    	<th class="text-center">Titulo</th>
                        			<th class="text-center">ISBN</th>
                        			<th class="text-center">género</th>
                        			<th>Id Proveedor</th>
                        			<th>CUIT</th>
                        			<th>Razon Social</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (LibroProv libP : list) { %>
                    			<tr>
                    				<td class="text-center"><%=libP.getIdLibro()%></td>
                    				<td class="text-center"><%=libP.getTitulo()%></td>
                    				<td class="text-center"><%=libP.getIsbn()%></td>
                    				<td class="text-center"><%=libP.getGenero()%></td>
                    				<td class="text-center"><%=libP.getIdProveedor()%></td>
                    				<td class="text-center"><%=libP.getCUIT()%></td>
                    				<td class="text-center"><%=libP.getRazonSocial()%></td>
                    				<td>
                    				    
                    				</td>
                    			</tr>
                    		<% } %>
                    		</tbody>	

  </table>
  <input type="button" onclick="history.back()" class="addbutton" name="Volver" value="Volver">
  </div>
  </div>
  </div>
 </div>
</body>
</html>