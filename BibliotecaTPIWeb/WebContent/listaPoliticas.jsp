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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
</head>
<body>
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab" style = "font-family:arial" size=3>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                     <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            </div>
             </div>
                </div>
                <div class="container buscar">
                <a href="agregarPolitica.jsp" method="post" class="btn btn-success">+ Nuevo</a>
               	<form action="buscarPoliticaServlet" class="form">
               			<input class="form-control" type="text" name="txtbuscar">
               			<input class="btn btn" type="submit" value="Buscar"	>
               			               
               	</form>
                </div>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                    		    	<th class="text-center">Fecha Alta</th>
                        			<th class="text-center">Libros Socio</th>
                        			<th class="text-center">Libros No Socio </th>
                        			<th class="text-center"> Acciones </th>
                                        </tr>
                                    </thead>
                                 <tbody>
                    		<% for (PoliticaPrestamo pp : politicas) { %>
                    			<tr>
                    				<td class="text-center"><%=pp.getIdPoliticaPrestamo()%></td>
                    				<td class="text-center"><%=pp.getFechaAlta()%></td>
                    				<td class="text-center"><%=pp.getCantMaximaSocio()%></td>
                    				<td class="text-center"><%=pp.getCantMaximaNoSocio()%></td>
                    				<td class="text-center">
                                <a href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>" class="editbutton">Editar</a>
                               <a href="borrarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>" class="deletebutton">Delete</a>
                            		</td>                    			                    				
                    			</tr>
                    		<% } %>
                    		</tbody>	
                                </table>
                            </div>
                            
                        </div>
                   
            </section>
       

<%@ include file = "footer.jsp" %>

 	<!--  <form action="agregarPolitica.jsp" method="post">
    <button type="submit">Agregar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="editarPolitica.jsp" method="post">
    <button type="submit">Editar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="borrarPolitica.jsp" method="post">
    <button type="submit">Borrar Politica de Prestamo</button>
	 -->

         <!-- Footer -->

</body>
</html>
