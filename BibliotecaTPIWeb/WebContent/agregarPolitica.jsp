<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="entities.PoliticaPrestamo"%> 
<%@page import="entities.MyResult"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Politica de Prestamo</title>
<% PoliticaPrestamo politica = (PoliticaPrestamo)session.getAttribute("nuevaPolitica"); %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">

</head>
<body>
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
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
 
<form class="form-horizontal" action="agregarPoliticaServlet" method="post">
<section>
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="fechaalta">Fecha Alta: </label>  
  <div class="col-md-4">
 <input class="form-control" type="date" id="fecha" name="fecha"  style="display=block">	     
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numsocio">Cantidad maxima de libros Socio: </label>  
  <div class="col-md-4">
  <input id="numsocio" name="numsocio" type="text" placeholder="Cant libros Socio" class="form-control input-md" required="">
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numnosocio">Cantidad maxima de libros No Socio: </label>  
  <div class="col-md-4">
  <input id="numnosocio" name="numnosocio" type="text" placeholder="Cant de Libros No socio" class="form-control input-md" required="">
  </div>
</div>

</fieldset>
<table>
<td>
<button class="addbutton">Agregar Politica</button>
</td>
<td>
<input type="button" = onclick="history.back()" class="addbutton" name="Volver" value="Volver">
</td>
</table>

 
  </section>
</form>
  </section>
  

<%@ include file = "footer.jsp" %>
</body>
</html>