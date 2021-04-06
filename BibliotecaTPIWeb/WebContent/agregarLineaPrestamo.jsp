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
<title>Agregar Linea Prestamo</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
</head>
<body>
<% LineaPrestamo lineaP = (LineaPrestamo)session.getAttribute("nuevaLineaPrestamo"); 
Prestamo p = (Prestamo)request.getAttribute("prestamo");
LibroController ctrlL = new LibroController();
LinkedList<Ejemplar> ejemplares = ctrlL.getAllEjemplaresDisponibles();
%>

<section id="tabs" class="project-tab" style = "font-family:arial; size=3">
 
<form class="form-horizontal" action="agregarLineaPrestamoServlet" method="post">
<section>
<fieldset>
<!-- Este me guarda el atributo id -->
<input type="text" name="id" value=<%=p.getIdPrestamo()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="id">Id Prestamo: <%=p.getIdPrestamo()%> </label>  
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="fechaDevolucion">Fecha Devolución: </label>  
  <div class="col-md-4">
  <input class="form-control" type="date" id="fecha" name="fecha" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block">	

 
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Id Ejemplar: </label>  
 
  <div>
				   <% if( ejemplares != null) {%>
				                            <select name="idEjemplar" class="form-control">
				                                <%  for(int i = 0; i < ejemplares.size(); i++) {
				                                   Ejemplar e = (Ejemplar)ejemplares.get(i);
				                                %>
				                                <option value="<%=e.getIdEjemplar()%>"><%=e.getIdEjemplar()%> - <%=e.getTitulo() %></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay ejemplares disponibles.  <%} %>
				                        
				  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="devuelto">Devuelto: </label>  
 <label class="radio-inline"><input type="radio" name="devuelto" value="1">Sí</label>
<label class="radio-inline"><input type="radio" name="devuelto" value="0" checked>No</label>
</div>


</fieldset>
<table>
<td>
<button class="addbutton">Agregar Linea Prestamo</button>
</td>
<td>
<input type="button" onclick="history.back()" class="addbutton" name="Volver" value="Volver">
</td>
</table>

 
  </section>
</form>
  </section>
  


<%@ include file = "footer.jsp" %>

</body>
</html>