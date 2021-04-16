<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
<%@page import="entities.Persona"%>  
<%@page import="logic.PersonaController"%>  
<%@page import="entities.Libro"%>  
<%@page import="logic.LibroController"%>
<%PersonaController ctrlPer = new PersonaController();
LinkedList<Persona> personas = ctrlPer.getAllPersonas(); 
LibroController ctrlL = new LibroController();
LinkedList<Libro> libros = ctrlL.getAllLibros();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle de Prestamo</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

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
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            	<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet active" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <br>
            
 					<div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="table-center"> <thead>
					        <tr>
					          <th class="text-center">ID Libro</th>
					          <th class="text-center">ID Ejemplar</th>
					          <th class="text-center">Fecha Devolucion</th>
					          			        </tr>
					      </thead>
					      <tr>
					     <td>
					     <% if( libros != null) {%>
				                            <select name="idlibro" id="idlibro" class="form-control input-md">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input type="text" name="txtidejemplar" id="txtidejemplar" class="form-control input-md" ></td>
						 <td><input class="form-control" type="date" id="txtfecha" name="txtfecha" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block"></td>    
					      </tr>
					      
					      <tr>					    
					     <td>
					     <% if( libros != null) {%>
				                            <select class="form-control" name="txtlibro1">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
						
					     <td><input class="form-control" type="text" name="txtidejemplar1"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha1" ></td> 
					      </tr>
					      
					      <tr>
					         <td>
					     <% if( libros != null) {%>
				                            <select class="form-control"  name="txtlibro2">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input class="form-control" type="text" name="txtidejemplar2"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha2" ></td> 
					      </tr>
					      
					      <tr>
					         <td>
					     <% if( libros != null) {%>
				                            <select class="form-control"  name="txtlibro3">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input class="form-control" type="text" name="txtidejemplar3"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha3" ></td> 
					        </tr>
					      
					    </table>
					    </div>
                            
                        </div>
                  </section>
	<%@ include file = "footer.jsp" %>				
</body>
</html>