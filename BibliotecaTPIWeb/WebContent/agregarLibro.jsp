<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Libro"%> 
<%@page import="entities.MyResult"%>  
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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<%@ include file="navInicio.jsp"%>
<section id="tabs" class="project-tab" style = "font-family:arial; size=3">
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
                            	<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>

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
<form class="form-horizontal" action="agregarLibroServlet" method="post">
<section>
<% if( proveedores.isEmpty())  { %> 
				   	<div class="warning"> No hay proveedores cargados.  </div>
				   <%}%>
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

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="cantdiasprestamo">Cant días prestamo: </label>  
  <div class="col-md-4">
  <input id="cantdiasprestamo" name="cantdiasprestamo" type="text" placeholder="Cantidad dias préstamo" class="form-control input-md" required="">
  </div>
</div>

<!-- Text input-->

<div class="form-group">
  <label class="col-md-4 control-label" for="prov">Proveedor: </label>  
  <div class="col-md-4">
   <% if( proveedores.isEmpty()){%>
		<a class="ejemplaresbutton" href="agregarProveedor.jsp">
	Añadir proveedor </a>
		   <%} else  {%>
                            <select name="idProveedor" class="form-control">
                                <%  for(int i = 0; i < proveedores.size(); i++) {
                                    Proveedor p = (Proveedor)proveedores.get(i);
                                %>
                                <option value="<%= p.getIdProveedor() %>"><%=p.getCUIT()%> - <%= p.getRazonSocial()%></option>
                                <% } %>
                            </select>
                            
                        	<td> Su proveedor no se encuentra en la lista? <a class="agreggatebutton"
									href="agregarProveedor.jsp">
										Añadir un nuevo proveedor</a></td><% } %>
  </div>
</div>
</fieldset>
<table>
<tr>
<td>
<%if(proveedores.isEmpty()){ %>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a>
<%}else{ %>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregará un nuevo libro. Desea confirmar?')">Agregar Libro</button>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a> <%} %>
</td>
</tr>
</table>
                            
        </section>


</form>

  </section>
  
  
  
  
	
 <%@ include file = "footer.jsp" %>
</body>
</html>