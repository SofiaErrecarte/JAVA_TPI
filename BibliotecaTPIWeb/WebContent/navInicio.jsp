<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Persona"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
html, body{
 font-family: Arial, Helvetica, sans-serif;
}
.style {
background-color: #85929E ;
 color: white;
}
</style>
</head>
<body>

	<% Persona per = (Persona)session.getAttribute("usuario");
  				if (per != null) { %>
	<header role="banner">
		<nav class="navbar navbar-expand-lg bg-secondary bg-gradient">
			<div class="container-fluid">
				<a class="navbar-brand " href="index.jsp">Biblioteca Nacional </a>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarsExample05" aria-controls="navbarsExample05"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>


				<div class="collapse navbar-collapse" id="navbarsExample05">
					<ul class="navbar-nav pl-md-5 ml-auto">
						<li class="nav-item"><a class="nav-link" href="index.jsp" style = "font-family:arial; size=4">Inicio</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="perfilUsuario.jsp" style = "font-family:arial; size=4">Mi Perfil </a></li>
						<li class="nav-item"><a class="nav-link"style = "font-family:arial; size=4" href="index.jsp" onclick="return confirm('Se cerrará sesión. Desea confirmar?'">Cerrar
								Sesión</a></li>
					</ul>

				</div>
			</div>
		</nav>
	</header>

	<% } else { %>



	<header role="banner">
		<nav class="navbar navbar-expand-lg bg-secondary bg-gradient fixed-top">
			<div class="container-fluid">
				<a class="navbar-brand " href="index.jsp">
				Biblioteca Nacional</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarsExample05" aria-controls="navbarsExample05"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>


				<div class="collapse navbar-collapse" id="navbarsExample05">
					<ul class="navbar-nav pl-md-5 ml-auto">
						<li class="nav-item"><a class="nav-link" href="contactar.jsp">@Contacto</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="index.jsp">Iniciar
								Sesión</a></li>
						<li class="nav-item"><a class="nav-link" href="agregarUsuario.jsp">Registrarse</a>
						</li>
					</ul>

				</div>
			</div>
		</nav>
	</header>

	<%} %>
</body>
</html>
