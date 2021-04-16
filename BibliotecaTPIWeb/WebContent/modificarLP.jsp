<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="entities.LineaPrestamo"%> 
<%@page import="entities.Prestamo"%> 
<%@page import="entities.MyResult"%>  
<%@page import="entities.Ejemplar"%>  
<%@page import="logic.LibroController"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Linea Prestamo</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style type="text/css">@import url("css/calendar-blue.css");</style>
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<% LineaPrestamo lineaP = (LineaPrestamo)request.getAttribute("lineaPrestamoAEditar"); 
LibroController ctrlL = new LibroController();
LinkedList<Ejemplar> ejemplares = ctrlL.getAllEjemplaresDisponibles();
%>
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
                            	<a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <br>
            
<form class="form-horizontal" action="modificarLineaPServlet" method="post">
<section>
<fieldset>

<!-- Este me guarda el atributo id -->
<input type="text" name="idPrestamo" value=<%=lineaP.getIdPrestamo()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="id">Id Prestamo: <%=lineaP.getIdPrestamo()%> </label>  
</div> 

<!-- Este me guarda el atributo id -->
<input type="text" name="id" value=<%=lineaP.getIdLineaPrestamo()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="id">Id Línea Préstamo: <%=lineaP.getIdLineaPrestamo()%> </label>  
</div> 

	<div class="form-group">
    <label class="col-md-4 control-label" for="fecha">Ingrese la Fecha de Devolución:</label>  
    <div class="col-md-4">
     <input class="form-control" type="date" id="fecha" value=<%=lineaP.getFechaDevolucion() %> name="fecha" placeholder="Fecha Alta" style="display=block" required>	     
     </div>
     </div>

<div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Id Ejemplar: </label>  
 
  <div>
				   <% if( ejemplares != null) {%>
				                            <select name="idEjemplar" value=<%=lineaP.getIdEjemplar() %> class="form-control">
				                                <option selected="true" value="<%=lineaP.getIdEjemplar()%>"><%=lineaP.getIdEjemplar()%></option>
				                                <%  for(int i = 0; i < ejemplares.size(); i++) {
				                                   Ejemplar e = (Ejemplar)ejemplares.get(i);
				                                   %>
				                                <option value="<%=e.getIdEjemplar()%>"><%=e.getIdEjemplar()%> - <%=e.getTitulo() %></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay ejemplares disponibles.  <%} %>
				                        
				  </div>
</div>

<!-- Este me guarda el atributo id -->
<input type="text" name="devuelto" value=<%=lineaP.isDevuelto()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="devuelto">Devuelto: <%=lineaP.isDevuelto()%> </label>  
</div>

<%-- <div class="form-group">
  <label class="col-md-4 control-label" for="devuelto">Devuelto: </label>  
<%if(lineaP.isDevuelto()){%>
 <label class="radio-inline"><input type="radio" name="devuelto" value="1" checked>Sí</label>
<label class="radio-inline"><input type="radio" name="devuelto" value="0">No</label>
<%}else{ %>
<label class="radio-inline"><input type="radio" name="devuelto" value="1">Sí</label>
<label class="radio-inline"><input type="radio" name="devuelto" value="0" checked>No</label>
<%} %>
</div>
 --%>

</fieldset>
<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se modificará la linea de prestamo. Desea confirmar?')">Modificar Linea Prestamo</button>
<a class="btn btn-outline-secondary" onclick="history.back()">Volver</a>
</td>
</tr>
</table>

 
  </section>
</form>
  </section>
  

 <%@ include file = "footer.jsp" %>

</body>
</html>