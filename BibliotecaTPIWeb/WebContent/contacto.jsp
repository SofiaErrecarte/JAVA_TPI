<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contacto</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Including Font Awesome CSS from CDN to show icons -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<br>
<br>
<br>
<br>
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
						<li class="nav-item"><a class="nav-link" href="contacto.jsp">@Contacto</a>
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


  <div class="card text-center">
  <div class="card-header">
    Biblioteca Nacional
  </div>
  <div class="card-body">
    <h5 class="card-title" style="font-weight:bold;">Información útil </h5>
    <p class="card-text">La Biblioteca Nacional de la República Argentina, fundada como Biblioteca Pública de Buenos Aires
   en el marco de la Revolución de Mayo, atesora y resguarda, a través de las más modernas técnicas bibliotecológicas, los materiales
   con los que se han forjado las distintas ideas y épocas del país, que pone a disposición de la ciudadanía como una oportunidad
   para repensar los enigmas de nuestra cultura colectiva.</p>
    <p class="card-text" style="font-weight: lighter;">E-mail: biblioteca-nacional@nacion.com  </p>
    <p class="card-text">Dirección: Córdoba 134</p>
    <p class="card-text">Teléfono: 011-44458599 </p>
    <p class="card-text">Horarios de atención: 8:00 hs a 20:00hs</p>
    <p class="card-text"> </p>
  </div>
  <div class="card-footer text-muted">
    Cualquier duda, consúltenos. 
  </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<%@ include file = "footer.jsp" %>

</body>
</html>