<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="entities.Persona"%>
    <%@page import="entities.MyResult"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Usuario</title>
<% Persona p = (Persona)session.getAttribute("nuevaPersona");
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
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>"  role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                          <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
             <%if ((request.getAttribute("errorString"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("errorString")%> </div>		
	<% } %>  
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
                

<h3 class="login-heading mb-4 text-center">Usuario Nuevo</h3>
<fieldset>
<form class="form-horizontal" action="agregarPersonaServlet" method="post">

<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido </label>  
  <input id="apellido" name="apellido" type="text" pattern="[A-Za-z ]{1,20}" title="No utilices caracteres especiales. Máximo 20 caracteres" placeholder="Apellido" class="form-control input-md" required>
</div>
<!-- Text input-->
<div class="form-group">
  <label  for="nombre">Nombre </label>  
  <input id="nombre" name="nombre" type="text" pattern="[A-Za-z ]{1,20}" title="No utilices caracteres especiales. Máximo 20 caracteres" placeholder="Nombre" class="form-control input-md" required>

</div>

<div class="form-group">
  <label>DNI </label>  
  <input  id="dni" name="dni" type="text" pattern="[0-9]{8}" title="Ingrese DNI sin puntos" placeholder="DNI" class="form-control input-md" required>
</div>

<!-- Text input-->
<div class="form-group">
  <label  for="telefono">Telefono </label>  
  <input id="telefono" name="telefono" type="text" pattern="[0-9]{6,20}" title="Ingresa un número de telefono válido" placeholder="Telefono" class="form-control input-md" required>
  </div>
  
<div class="form-group">
  <label  for="direccion">Direccion </label>  
  <input id="direccion" name="direccion" type="text" placeholder="Direccion" class="form-control input-md" required>

</div>
<!-- Text input-->
<div class="form-group">
  <label for="mail">Usuario </label>  
  <input  id="mail" name="mail" type="text" pattern="[A-Za-z0-9]{5,15}" title="No utilices caracteres especiales. Mínimo 5 caracteres, máximo 15" placeholder="Usuario" class="form-control input-md" required>
</div>

 <div class="form-group">
  <label  for="pass">Contraseña </label>  
  <input  id="pass" name="pass" type="text" pattern="[A-Za-z0-9]{6,45}" title="No utilices caracteres especiales. Mínimo 6 caracteres." placeholder="Contraseña" class="form-control input-md" required/>
</div>
 </fieldset>
<br>

<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregará un nuevo usuario. Desea confirmar?')">Agregar Persona</button>
<a class="btn btn-outline-secondary" href="listarPersonasServlet">Volver</a>
</td>
</tr>
</table>

</form>

  </section>

 <%@ include file = "footer.jsp" %>
</body>
</html>