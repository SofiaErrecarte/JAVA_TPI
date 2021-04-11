<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.LineaPrestamo"%>
<%@page import="entities.Prestamo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lineas Prestamo</title>
<%
LinkedList<LineaPrestamo> lpr = (LinkedList<LineaPrestamo>)request.getAttribute("listaLineas");
Prestamo p = (Prestamo)request.getAttribute("prestamo");
int cant = (Integer)request.getAttribute("cantidad");
int limiteNS = (Integer)request.getAttribute("limiteNS");
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
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab" style = "font-family:arial; size=3">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                              <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                	
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                               	 <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Prestamo</a>
                               
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
   <div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Id Prestamo: <%=p.getIdPrestamo()%> </label>  
  </div>
  
  <%if (cant>=limiteNS) { %>
		<div class="warning">No puede agregar más libros a este préstamo. Límite de política alcanzado.</div>		
	<% } %>
  
  <div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Cantidad de líneas: <%=cant%> </label>  
  </div>
  
  <div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Límite de libros por préstamo NS: <%=limiteNS%> </label>  
  </div>
  <%if(cant<limiteNS){ %>
   <div class="container buscar">
                <a 		href="agregarLineaPrestamoServlet?id=<%=p.getIdPrestamo()%>" method="post" class="btn btn-success">+ Nueva Linea</a>
                </div>            
							<%} %>         
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                       
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Linea</th>
		                    		    	<th class="text-center">ID Ejemplar</th>
		                    		    	<th class="text-center">Fecha Devolución</th>
		                    		    	<th class="text-center">Devuelto</th>
		                    		    	<th class="text-center"> Acción </th>
                                       
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (LineaPrestamo lp : lpr) { %>
                    			<tr>
                    				<td class="text-center"><%=lp.getIdLineaPrestamo()%></td>
                    				<td class="text-center"><%=lp.getIdEjemplar()%></td>
                    				<td class="text-center"><%=lp.getFechaDevolucion()%></td>
                    				<td class="text-center"><%=lp.isDevuelto()%></td>
									<td class="text-center"><a class="editbutton"
									href="modificarLineaPServlet?id=<%=lp.getIdLineaPrestamo()%>">
										Editar</a></td> 

                    				 </tr>
                    		<% } %>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
      <table>
      <tr>
      <%if(cant<limiteNS){ %>
      							 <td>
                           			<a class="addbutton"
									href="agregarLineaPrestamoServlet?id=<%=p.getIdPrestamo()%>">
										Agregar Linea </a>
                        
							</td> <%} %>
                          
							 <td>
							 <a class="addbutton" href="listarPrestamosServlet">Volver</a>
							 
							
							  </td>
</tr>
                            </table>

        </section>

         <!-- Footer -->
<%@ include file = "footer.jsp" %>

</body>
</html>