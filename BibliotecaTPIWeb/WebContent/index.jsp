<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.MyResult"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="css/login.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">

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
<%request.setAttribute("respuesta", "ok"); %>

<header class="masthead">

<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first"> 
       <img src="images/icono.jpg" alt="User_Icon" />
    </div>
	<%if ((request.getAttribute("error"))!=null) { %>
		<div class="error"> <%=request.getAttribute("error")%> </div>		
	<% } %>
	<%if ((request.getAttribute("logOut"))!=null) { %>
		<div class="success"> <%=request.getAttribute("logOut")%> </div>		
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
	
 <!-- Login Form -->
    <form action="init" method="post">
     <FONT SIZE=6>  
      <input type="text" id="login" class="fadeIn second" placeholder="Usuario" name="email" required>
      <input type="password" id="password" class="fadeIn third" placeholder="Contraseņa" name="password" required>
      <input type="submit" class="fadeIn fourth" value="Ingresar">
      </font>
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