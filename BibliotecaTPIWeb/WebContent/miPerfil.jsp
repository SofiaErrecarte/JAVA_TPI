<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Persona"%>
    <%@page import="entities.MyResult"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link href="css/newUser.css" rel="stylesheet">
	
<title>Mi Perfil</title>
<% Persona user = (Persona)session.getAttribute("usuario");%>
<style>
html,body{
 text-align: center
}

.fadeInDown {
  -webkit-animation-name: fadeInDown;
  animation-name: fadeInDown;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}
@-webkit-keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}
	
</style>

</head>

<body>
<%@ include file="navInicio.jsp"%>
 
                 
<div class="fadeInDown">
  <div class="col-md-9 col-lg-8 mx-auto ">

  <h3 class="login-heading mb-4 text-center">Mi Usuario</h3>

<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido </label>  
  <input type="text" name="idLibro" value=<%=user.getApellido()%>  class="form-control " disabled>
</div>
<!-- Text input-->
<div class="form-group">
  <label  for="nombre">Nombre </label>  
  <input type="text" name="idLibro" value=<%=user.getNombre()%>  class="form-control " disabled>
</div>

<div class="form-group">
  <label>DNI </label>  
  <input type="text" name="idLibro" value=<%=user.getDni()%>  class="form-control " disabled>
</div>

<!-- Text input-->
<div class="form-group">
  <label  for="telefono">Telefono </label>  
  <input type="text" name="idLibro" value=<%=user.getTelefono()%>  class="form-control " disabled>
  </div>
  
<div class="form-group">
  <label  for="direccion">Direccion </label>  
  <input type="text" name="idLibro" value=<%=user.getDireccion()%>  class="form-control " disabled>

</div>
<!-- Text input-->
<div class="form-group">
  <label for="mail">Email </label>  
  <input type="text" name="idLibro" value=<%=user.getEmail()%>  class="form-control " disabled>
</div>

 <div class="form-group">
  <label  for="pass">Contraseña </label>  
  <input type="text" name="idLibro" value=<%=user.getContraseña()%>  class="form-control " disabled>
</div>
<br>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a>
<br>
<br>
</div>
</div>

 <%@ include file = "footer.jsp" %>
</body>
</html>