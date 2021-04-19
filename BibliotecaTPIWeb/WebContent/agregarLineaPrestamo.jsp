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
            
<% LineaPrestamo lineaP = (LineaPrestamo)session.getAttribute("nuevaLineaPrestamo"); 
Prestamo p = (Prestamo)request.getAttribute("prestamo");
LibroController ctrlL = new LibroController();
LinkedList<Ejemplar> ejemplares = ctrlL.getAllEjemplaresDisponibles();
%>


<div class="tab-content" id="nav-tabContent">
<%if ((request.getAttribute("error"))!=null) { %>
		<div class="error"> <%=request.getAttribute("error")%> </div>		
	<% } %>
	<% if (request.getAttribute("result")!=null) {
        	   MyResult res = (MyResult)request.getAttribute("result");
        	   if(res.getResult().equals(MyResult.results.OK)){
        		   %>
                   <div class="success"><%=res.getErr_message()%></div>
                  <%
        	   } else {
        	      %>
                   <div class="error"><%=res.getErr_message()%></div>
                   <%}
                   }
                 %> 
                 
                 <br>
<form class="form-horizontal" action="agregarLineaPrestamoServlet" method="post">
<section>
<% if( ejemplares.isEmpty())  { %> 
				   	<div class="warning"> No hay ejemplares disponibles.  </div>
				   <%}%>
<fieldset>

<!-- Este me guarda el atributo id -->
<input type="text" name="id" value=<%=p.getIdPrestamo()%> hidden="true">
<div class="form-group">
<label class="col-md-4 control-label" for="id">Id Prestamo: <%=p.getIdPrestamo()%> </label>  
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="idEjemplar">Id Ejemplar: </label>  
 <div class="col-md-4">
  <% if( ejemplares.isEmpty()) {%>
  			<a class="ejemplaresbutton" href="listarLibroServlet">
			Añadir ejemplares </a>
				   <%} else {%>
				           <select name="idEjemplar" class="form-control">
				                <%  for(int i = 0; i < ejemplares.size(); i++) {
				                    Ejemplar e = (Ejemplar)ejemplares.get(i);
				                    %>
				           <option value="<%=e.getIdEjemplar()%>"><%=e.getIdEjemplar()%> - <%=e.getTitulo() %></option>
				               <% } %>
				           </select>
		  <% }%>

				  </div>
</div>


</fieldset>
<table>
<tr>
<td>
<%if( ejemplares.isEmpty()) {%>
	<a class="btn btn-outline-secondary" onclick="history.back()">Volver</a>
	<%}else{ %>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregará una nueva linea de prestamo. Desea confirmar?')">Agregar Linea Prestamo</button>
<a class="btn btn-outline-secondary" href="listarLineasPrestamoServlet?id=<%=p.getIdPrestamo()%>" >Volver</a> <%} %>
</td>
</tr>
</table>
  </section>
</form>
</div>
  </section>


	
 <%@ include file = "footer.jsp" %>
</body>
</html>