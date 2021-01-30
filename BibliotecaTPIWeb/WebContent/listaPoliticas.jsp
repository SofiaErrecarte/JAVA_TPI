<%@page import="java.util.LinkedList"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Politicas Prestamo</title>
<% 
   LinkedList<PoliticaPrestamo> politicas = (LinkedList<PoliticaPrestamo>)request.getAttribute("listapoliticas");
%>
</head>
<body>
<div class="container">
		<div class="row">
        	<h4>Libros</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>ID</th>
                    		    	<th>Fecha Alta</th>
                        			<th>Libros Socio</th>
                        			<th>libros No Socio/th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (PoliticaPrestamo pp : politicas) { %>
                    			<tr>
                    				<td><%=pp.getIdPoliticaPrestamo()%></td>
                    				<td><%=pp.getFechaAlta()%></td>
                    				<td><%=pp.getCantMaximaSocio()%></td>
                    				<td><%=pp.getCantMaximaNoSocio()%></td>
                    				<td>
                    				    
                    				</td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		<% } %>
                    		</tbody>	
<form action="agregarPoliticaServlet" method="post">
    <button type="submit">Agregar Politica de Prestamo</button>
 	</form> 
	 
</body>
</html>