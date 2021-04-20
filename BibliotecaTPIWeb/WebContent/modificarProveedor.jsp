<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.Proveedor"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Proveedor</title>
<% //Libro lib = (Libro)request.getAttribute("libroAEditar");
Proveedor prov=(Proveedor)request.getAttribute("proveedorAEditar"); 
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
<section id="tabs" class="project-tab" style = "font-family:arial; size=3">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link " id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <br>
<form class="form-horizontal" action="modificarProveedorServlet" method="post">
<section>
<fieldset>
<div class="form-group">
  <label class="col-md-4 control-label" for="id">ID proveedor: </label>  
  <div class="col-md-4">
      <!-- Este me guarda el atributo id -->	
    <input type="text" name="id" value=<%=prov.getIdProveedor()%> hidden="true">	  
     <!--  Este me lo muestra deshabilitado para q se vea el numero -->  
	 <input type="text" name="idLibro" value=<%=prov.getIdProveedor()%>  class="form-control input-md" disabled>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="cuit">CUIT:</label>  
  <div class="col-md-4"> 
   <input type="text" name="cuit" value=<%=prov.getCUIT()%> class="form-control input-md" >
 
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="razonSocial">Razón Social:</label>  
  <div class="col-md-4">
    <input type="text" name="razonSocial" value=<%=prov.getRazonSocial()%> class="form-control input-md" required>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="telefono">Teléfono:</label>  
  <div class="col-md-4">
    <input type="text" name="telefono" value=<%=prov.getTelefono() %> class="form-control input-md" required>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="mail">E-Mail:</label>  
  <div class="col-md-4">
    <input type="text" name="mail" value=<%=prov.getMail() %> class="form-control input-md" required>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="direccion">Dirección:</label>  
  <div class="col-md-4">
    <input type="text" name="direccion" value=<%=prov.getDireccion() %> class="form-control input-md" required>
  </div>
</div>
</fieldset>
    
<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se modificará el proveedor. Desea confirmar?')">Modificar Proveedor</button>
<a class="btn btn-outline-secondary" onclick="history.back()">Volver</a>
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