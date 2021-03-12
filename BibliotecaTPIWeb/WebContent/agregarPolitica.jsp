<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="entities.PoliticaPrestamo"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agragar Politica de Prestamo</title>
<% PoliticaPrestamo politica = (PoliticaPrestamo)session.getAttribute("nuevaPolitica"); %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<link href = "css/listado.css" rel="stylesheet">
<style type="text/css">@import url("css/calendar-blue.css");</style>

</head>
<body>
<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            

<%if (politica!= null){%>
	<h4> Politica agregada con éxito</h4>
	<String> mensaje="<script type="text/javascript">alert('Esto se debe de mostrar en el msgbox');</script>";
	out.println(mensaje);
<%} else {%>
<form class="form-horizontal" action="agregarPoliticaServlet" method="post">
<section>
<fieldset>



<div class="form-group">
  <label class="col-md-4 control-label" for="fechaalta">Fecha Alta: </label>  
  <div class="col-md-4">
  <input type="text" name="fecha" id="fecha" readonly="readonly" placeholder="Fecha Alta" class="form-control input-md" />
<img src="calendario.png" id="selector" />
<script type="text/javascript">
window.onload = function() {
  Calendar.setup({
    inputField: "fecha",
    ifFormat:   "%Y-%m-%d",
    button:     "selector"
  });
}
</script>
  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numsocio">Cantidad maxima de libros Socio: </label>  
  <div class="col-md-4">
  <input id="numsocio" name="numsocio" type="text" placeholder="Cant libros Socio" class="form-control input-md" required="">
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="numnosocio">Cantidad maxima de libros No Socio: </label>  
  <div class="col-md-4">
  <input id="numnosocio" name="numnosocio" type="text" placeholder="Cant de Libros No socio" class="form-control input-md" required="">
  </div>
</div>

</fieldset>
<table>
<td>
<button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Politica</button>
</td>
<td>
<input type="button" = onclick="history.back()" class="btn btn-lg btn-primary" name="Volver" value="Volver" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto">
</td>
</table>


<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>
	
 
  </section>
</form>
<% }%>
  </section>
  


 <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
  
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/calendar.js" /></script>
<script type="text/javascript" src="js/calendar-es.js" /></script>
<script type="text/javascript" src="js/calendar-setup.js" /></script>
</body>
</html>