<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Proveedor"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Proveedor</title>
<% Proveedor prov = (Proveedor)session.getAttribute("nuevoProveedor"); %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">

<!------ Include the above in your HEAD tag ---------->
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
                                <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
  
                
<%if (prov!= null){%>
	<h4> Proveedor agregado con éxito</h4>
	<String> mensaje="<script type="text/javascript">alert('Esto se debe de mostrar en el msgbox');</script>";
	out.println(mensaje);
<%} else {%>
<form class="form-horizontal" action="agregarProveedorServlet" method="post">
<section>
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="cuit">CUIT: </label>  
  <div class="col-md-4">
  <input id="cuit" name="cuit" type="text" placeholder="CUIT" class="form-control input-md" required="">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="razonSocial">Razón Social </label>  
  <div class="col-md-4">
  <input id="razonSocial" name="razonSocial" type="text" placeholder="Razon Social" class="form-control input-md" required="">
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="telefono">Telefono: </label>  
  <div class="col-md-4">
  <input id="telefono" name="telefono" type="text" placeholder="Telefono" class="form-control input-md" required="">
  </div>
</div>

<!-- Select Basic 
<div class="form-group">
  <label class="col-md-4 control-label" for="selectEstadoCivil">Estado Civil: </label>
  <div class="col-md-4">
    <select id="selectEstadoCivil" name="selectEstadoCivil" class="form-control">
      <option value="s">Soltero</option>
      <option value="c">Casado</option>
      <option value="d">Divorciado</option>
      <option value="v">Viudo</option>
    </select>
  </div>
</div>-->

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="mail">E - Mail: </label>  
  <div class="col-md-4">
  <input id="mail" name="mail" type="text" placeholder="E - Mail" class="form-control input-md" required="">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="direccion">Direccion: </label>  
  <div class="col-md-4">
  <input id="direccion" name="direccion" type="text" placeholder="Direccion" class="form-control input-md" required="">
  </div>
</div>

</fieldset>
<table>
<td>
<button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Libro</button>
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
</body>
</html>