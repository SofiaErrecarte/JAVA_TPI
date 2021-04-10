<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="css/login.css" rel="stylesheet">

<style>

	.masthead {
	 
	font-family: Arial, Helvetica, sans-serif;
  height: 100vh;
  min-height: 500px;
  background-image: url('images/inicio2.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
	h1, .h1 {
	  font-size: 4em;
	}
	


	
</style>


</head>
<body>


<%@ include file="navInicio.jsp"%>
<header class="masthead">

<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first"> 
       <img src="images/icono.jpg" alt="User_Icon" />
    </div>
	<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>
    <!-- Login Form -->
    <form action="init" method="post">
      <input type="text" id="login" class="fadeIn second" placeholder="Usuario" name="email" required>
      <input type="password" id="password" class="fadeIn third" placeholder="Password" name="password" required>
      <input type="submit" class="fadeIn fourth" value="Ingresar">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a href="agregarUsuario.jsp" class="underlineHover" href="#">Registrarme</a>
    </div>

  </div>
</div>
</header>
<%@ include file = "footer.jsp" %>
</body>
</html>