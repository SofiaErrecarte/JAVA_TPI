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
	
<title>Nuevo Usuario</title>

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
            <br>
            <br>
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
  <h3 class="login-heading mb-4 text-center">Usuario Nuevo</h3>
<form class="form-horizontal" action="agregarUsuarioServlet" method="post">

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
 <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"  onclick="return confirm('Se agregará un nuevo usuario. Desea confirmar?')">Crear Cuenta</button>
 
</form>
</div>
</div>

 <%@ include file = "footer.jsp" %>
</body>
</html>