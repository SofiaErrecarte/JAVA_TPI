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
</head>
<body>
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab">
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
            
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID Linea</th>
		                    		    	<th>ID Ejemplar</th>
		                    		    	<th>Fecha Devolución</th>
		                    		    	<th>Devuelto</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (LineaPrestamo lp : lpr) { %>
                    			<tr>
                    				<td><%=lp.getIdLineaPrestamo()%></td>
                    				<td><%=lp.getIdEjemplar()%></td>
                    				<td><%=lp.getFechaDevolucion()%></td>
                    				<td><%=lp.isDevuelto()%></td>
									<td><a class="editbutton"
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
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>