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
                    				<td> <a class="editbutton"
									href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>">
										Editar </a>
										<a class="deletebutton"
									href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>">
										Eliminar</a></td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		<% } %>
                    		</tbody>	
 	
 	<form action="agregarPolitica.jsp" method="post">
    <button type="submit">Agregar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="editarPolitica.jsp" method="post">
    <button type="submit">Editar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="borrarPolitica.jsp" method="post">
    <button type="submit">Borrar Politica de Prestamo</button>
	 
</body>
</html>