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

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href = "css/messages.css" rel="stylesheet">
<link href = "css/listado.css" rel="stylesheet">
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<% LineaPrestamo lineaP = (LineaPrestamo)session.getAttribute("nuevaLineaPrestamo"); 
Prestamo p = (Prestamo)request.getAttribute("prestamo");
LibroController ctrlL = new LibroController();
LinkedList<Ejemplar> ejemplares = ctrlL.getAllEjemplaresDisponibles();
%>

<section id="tabs" class="project-tab">

<form class="form-horizontal" action="agregarLineaPrestamoServlet" method="post">
<section>
<fieldset>
<!-- Este me guarda el atributo id -->
<input type="text" name="id" value=<%=p.getIdPrestamo()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="id">Id Prestamo: <%=p.getIdPrestamo()%> </label>  
</div>

<!-- <div class="form-group">
  <label class="col-md-4 control-label" for="fechaDevolucion">Fecha Devolución: </label>  
  <div class="col-md-4">
  <input class="form-control" type="date" id="fecha" name="fecha" style="display=block">	


  </div>
</div> -->

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
				                            <% }else{ %> <td> No hay ejemplares cargados.  <%} %>

				  </div>
</div>

<!-- <div class="form-group">
  <label class="col-md-4 control-label" for="devuelto">Devuelto (1 o 0): </label>  
  <div class="col-md-4">
  <input id="devuelto" name="devuelto" type="text" class="form-control input-md">
  </div> -->
   <!-- <div>
   		<select name="devuelto" class="form-control">
			<option value="0">NO</option>
			<option value="1">SI</option>
				 </select>
	</div> -->
	  <!-- <label class="col-md-4 control-label" for="devuelto">Devuelto: </label>  
 <label class="radio-inline"><input type="radio" name="devuelto" value="1">Sí</label>
<label class="radio-inline"><input type="radio" name="devuelto" value="0">No</label>
</div> -->


</fieldset>
<table>
<td>
<button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Linea Prestamo</button>
</td>
<td>
<input type="button" onclick="history.back()" class="btn btn-lg btn-primary" name="Volver" value="Volver" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto">
</td>
</table>


  </section>
</form>
  </section>



 <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/calendar.js" /></script>
<script type="text/javascript" src="js/calendar-es.js" /></script>
<script type="text/javascript" src="js/calendar-setup.js" /></script>
</body>
</html>