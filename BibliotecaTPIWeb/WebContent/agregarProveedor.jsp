<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Proveedor"%>
    <%@page import="entities.MyResult"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Proveedor</title>
<% Proveedor prov = (Proveedor)session.getAttribute("nuevoProveedor"); %>
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
<!------ Include the above in your HEAD tag ---------->
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
                                <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                           <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
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
<form class="form-horizontal" action="agregarProveedorServlet" method="post">
<section>
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="cuit">CUIT: </label>  
  <div class="col-md-4">
  <input id="cuit" name="cuit" type="text" placeholder="CUIT" class="form-control input-md" required>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="razonSocial">Razón Social </label>  
  <div class="col-md-4">
  <input id="razonSocial" name="razonSocial" type="text" placeholder="Razon Social" class="form-control input-md" required>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="telefono">Telefono: </label>  
  <div class="col-md-4">
  <input id="telefono" name="telefono" type="text" placeholder="Telefono" class="form-control input-md" required>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="mail">E - Mail: </label>  
  <div class="col-md-4">
  <input id="mail" name="mail" type="text" placeholder="E - Mail" class="form-control input-md" required>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="direccion">Direccion: </label>  
  <div class="col-md-4">
  <input id="direccion" name="direccion" type="text" placeholder="Direccion" class="form-control input-md" required>
  </div>
</div>

</fieldset>
<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregará un nuevo proveedor. Desea confirmar?')">Agregar Proveedor</button>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a>
</td>
</tr>
</table>



  </section>
</form>

  </section>
 <%@ include file = "footer.jsp" %>
</body>
</html>