<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Libro"%>  
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>  
<%@page import="logic.ProveedorController"%>   
<%
ProveedorController ctrlProv = new ProveedorController();
LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Libro</title>
<% Libro lib = (Libro)session.getAttribute("nuevoLibro"); %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
</head>
<body>
<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>

 <%if (lib!= null){%>
	<h4> Libro agregado con éxito</h4>
	<String> mensaje="<script type="text/javascript">alert('Esto se debe de mostrar en el msgbox');</script>";
	out.println(mensaje);
<%} else {%>
<form class="form-horizontal" action="agregarLibroServlet" method="post">
<section>
<fieldset>
<div class="form-group">
  <label class="col-md-4 control-label" for="titulo">Título: </label>  
  <div class="col-md-4">
  <input id="titulo" name="titulo" type="text" placeholder="Titulo" class="form-control input-md" required="">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="isbn">ISBN: </label>  
  <div class="col-md-4">
  <input id="isbn" name="isbn" type="text" placeholder="ISBN" class="form-control input-md" required="">
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nroedicion">Numero de edicion: </label>  
  <div class="col-md-4">
  <input id="nroedicion" name="nroedicion" type="text" placeholder="Numero Edicion" class="form-control input-md" required="">
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
  <label class="col-md-4 control-label" for="cantdiasprestamo">Cant días prestamo: </label>  
  <div class="col-md-4">
  <input id="cantdiasprestamo" name="cantdiasprestamo" type="text" placeholder="Cantidad dias préstamo" class="form-control input-md" required="">
  </div>
</div>

<!-- Text input-->

<div class="form-group">
  <label class="col-md-4 control-label" for="prov">Genero: </label>  
  <div class="col-md-4">
   <% if( proveedores != null) {%>
                            <select name="idProveedor" class="form-control">
                                <%  for(int i = 0; i < proveedores.size(); i++) {
                                    Proveedor p = (Proveedor)proveedores.get(i);
                                %>
                                <option value="<%= p.getIdProveedor() %>"><%= p.getRazonSocial()%></option>
                                <% } %>
                            </select>
                            <% }else{ %> <td> No hay proveedores cargados. <a class="agreggatebutton"
									href="agregarProveedor.jsp">
										Añadir un nuevo proveedor</a></td></td> <%} %>
                        	<td> Su proveedor no se encuentra en la lista? <a class="agreggatebutton"
									href="agregarProveedor.jsp">
										Añadir un nuevo proveedor</a></td>
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
                            
        </section>
<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>

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