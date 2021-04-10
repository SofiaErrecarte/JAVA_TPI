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
                 
<div class="fadeInDown">
  <div class="col-md-9 col-lg-8 mx-auto ">
            <br>
            <br>
            <br>
  <h3 class="login-heading mb-4 text-center">Usuario Nuevo</h3>
<form class="form-horizontal" action="agregarUsuarioServlet" method="post">

<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido </label>  
  <input name="apellido" type="text" placeholder="Apellido" class="form-control input-md" required>
</div>
<!-- Text input-->
<div class="form-group">
  <label  for="nombre">Nombre </label>  
  <input name="nombre" type="text" placeholder="Nombre" class="form-control input-md" required>

</div>

<div class="form-group">
  <label>DNI </label>  
  <input  name="dni" type="text" placeholder="DNI" class="form-control input-md" required>
</div>

<!-- Text input-->
<div class="form-group">
  <label  for="telefono">Telefono </label>  
  <input name="telefono" type="text" placeholder="Telefono" class="form-control input-md" required>
  </div>
  
<div class="form-group">
  <label  for="direccion">Direccion </label>  
  <input  name="direccion" type="text" placeholder="Direccion" class="form-control input-md" required>

</div>
<!-- Text input-->
<div class="form-group">
  <label for="mail">Usuario </label>  
  <input  name="mail" type="text" placeholder="Usuario" class="form-control input-md" required>
</div>

 <div class="form-group">
  <label  for="pass">Contraseña </label>  
  <input name="pass" type="text" placeholder="Contraseña" class="form-control input-md" required/>
</div>
 <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"  onclick="return confirm('Se agregará un nuevo usuario. Desea confirmar?')">Crear Cuenta</button>
 
</form>
</div>
</div>

 <%@ include file = "footer.jsp" %>
</body>
</html>