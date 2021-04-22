<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.Persona"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Usuario</title>
<% 
Persona p =(Persona)request.getAttribute("personaAEditar"); 
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
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link " id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <br>
            <h3 class="login-heading mb-4 text-center">Editar Usuario</h3>
<form class="form-horizontal" action="modificarPersonaServlet" method="post" >
<section>
<fieldset>
<div class="form-group">
  <label class="col-md-4 control-label" for="id">ID Persona: </label>  
  <div class="col-md-4">
      <!-- Este me guarda el atributo id -->	
    <input type="text" name="id" value=<%=per.getIdPersona()%> hidden="true">	  
     <!--  Este me lo muestra deshabilitado para q se vea el numero -->  
	 <input type="text" name="idLibro" value=<%=per.getIdPersona()%>  class="form-control input-md" disabled>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido </label>  
  <input id="apellido" name="apellido" type="text" value="<%=p.getApellido()%>" placeholder="Apellido" class="form-control input-md" required>
</div>
<!-- Text input-->
<div class="form-group">
  <label  for="nombre">Nombre </label>  
  <input id="nombre" name="nombre" type="text" value="<%=p.getNombre()%>" placeholder="Nombre" class="form-control input-md" required>

</div>

<div class="form-group">
  <label>DNI </label>  
  <input  id="dni" name="dni" type="text" value="<%=p.getDni()%>" placeholder="DNI" class="form-control input-md" required>
</div>

<!-- Text input-->
<div class="form-group">
  <label  for="telefono">Telefono </label>  
  <input id="telefono" name="telefono" type="text" value="<%=p.getTelefono()%>" placeholder="Telefono" class="form-control input-md" required>
  </div>
  
<div class="form-group">
  <label  for="direccion">Direccion </label>  
  <input id="direccion" name="direccion" type="text" value="<%=p.getDireccion()%>" placeholder="Direccion" class="form-control input-md" required>

</div>
<!-- Text input-->
<div class="form-group">
  <label for="mail">Email </label>  
  <input  id="mail" name="mail" type="text" value="<%=p.getEmail()%>" placeholder="Usuario" class="form-control input-md" required>
</div>

</fieldset>
    
<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se modificará la persona. Desea confirmar?')">Modificar Persona</button>
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