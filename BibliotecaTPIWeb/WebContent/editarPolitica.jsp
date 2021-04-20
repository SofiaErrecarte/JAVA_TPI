<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.PoliticaPrestamo"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Politica de Prestamo</title>
<% PoliticaPrestamo pp=(PoliticaPrestamo)request.getAttribute("politicaAEditar"); 
Persona user = (Persona)session.getAttribute("usuario");%>
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
<section id="tabs" class="project-tab"  style = "font-family:arial; size=3">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <br>
<form class="form-horizontal" action="editarPoliticaServlet" method="post">
<section>
<fieldset>
  
<div class="form-group">
  <label class="col-md-4 control-label" for="idpolitica">Id Política: </label>  
  <div class="col-md-4">
  <!-- Este me guarda el atributo id -->	
    <input type="text" name="id" value=<%=pp.getIdPoliticaPrestamo()%> hidden="true">	  
     <!--  Este me lo muestra deshabilitado para q se vea el numero -->  
   <input type="text" name="idPP" value=<%=pp.getIdPoliticaPrestamo()%> class="form-control input-md" disabled >
  </div>
</div> 
 
<div class="form-group">
  <label class="col-md-4 control-label" for="fechaalta">Fecha Alta: </label>  
  <div class="col-md-4">
 <!--  Este me lo muestra deshabilitado para q se vea el numero -->  
   <input type="text" name="idPP" value=<%=pp.getFechaAlta()%> class="form-control input-md" disabled  >
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numsocio">Cantidad maxima de libros Socio: </label>  
  <div class="col-md-4">
  <input type="text" name="librosSocio" value=<%=pp.getCantMaximaSocio() %> placeholder="Cant libros Socio" class="form-control input-md" required>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numnosocio">Cantidad maxima de libros No Socio: </label>  
  <div class="col-md-4">
  <input name="librosNoSocio" type="text" value=<%=pp.getCantMaximaNoSocio() %> placeholder="Cant de Libros No socio" class="form-control input-md" required>
  </div>
</div>

</fieldset>
    <table>
<table>
<tr>
<td>
<button class="btn btn-outline-primary">Agregar Libro</button>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a>
</td>
</tr>
</table>
<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>
	</section>
	</form>
	</section>
<%@ include file = "footer.jsp" %>
 
</body>
</html>