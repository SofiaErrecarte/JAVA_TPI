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
<% Libro lib = (Libro)session.getAttribute("nuevoLibro"); 
Persona user = (Persona)session.getAttribute("usuario");%>
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
                                	 href="listarLibroServlet"  role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>"  role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            	<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
 <%if ((request.getAttribute("errorString"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("errorString")%> </div>		
	<% } %>  
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
                 <h3 class="login-heading mb-4 text-center">Libro Nuevo</h3>
                
<form class="form-horizontal" action="agregarLibroServlet" method="post" enctype="multipart/form-data">
<section>
<% if( proveedores.isEmpty())  { %> 
				   	<div class="warning"> No hay proveedores cargados.  </div>
				   <%}%>
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="titulo">T�tulo: </label>  
 
  <input id="titulo" name="titulo" type="text" pattern="[A-Za-z ]{1,20}" title="No utilices caracteres especiales. M�ximo 20 caracteres" placeholder="Titulo" class="form-control input-md" required>
  
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="autor">Autor </label>  

  <input id="autor" name="autor" type="text" pattern="[A-Za-z ]{1,20}" title="No utilices caracteres especiales. M�ximo 20 caracteres" placeholder="Autor" class="form-control input-md" required>
 
</div>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="isbn">ISBN: </label>  
 
  <input id="isbn" name="isbn" type="text" pattern="[0-9]{5}" title="Ingrese 5 n�meros. No utilice letras ni caracteres especiales." placeholder="ISBN" class="form-control input-md" required>
  
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nroedicion">Numero de edicion: </label>  

  <input id="nroedicion" name="nroedicion" type="text" pattern="[0-9]{1,3}" title="No utilice letras ni caracteres especiales. M�ximo 3 n�meros" placeholder="Numero Edicion" class="form-control input-md" required>

</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="genero">G�nero:</label>  
 
   <input type="text" name="genero" pattern="[A-Za-z ]{1,20}" title="No utilices caracteres especiales. M�ximo 20 caracteres" class="form-control input-md" placeholder="G�nero" required>
     
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="prov">Proveedor: </label>  

   <% if( proveedores.isEmpty()){%>
		<a  href="agregarProveedor.jsp" style="color: #147A9B">
	A�adir proveedor </a>
		   <%} else  {%>
                            <select name="idProveedor" class="form-control">
                                <%  for(int i = 0; i < proveedores.size(); i++) {
                                    Proveedor p = (Proveedor)proveedores.get(i);
                                %>
                                <option value="<%= p.getIdProveedor() %>"><%=p.getCUIT()%> - <%= p.getRazonSocial()%></option>
                                <% } %>
                            </select>
                            
                        	 Su proveedor no se encuentra en la lista? <a 
									href="agregarProveedor.jsp" style="color: #147A9B">
										A�adir un nuevo proveedor</a>
						<% } %>
										
  
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="foto">Importar Imagen </label>  
  <div class="col-md-4">
  <input id="foto" name="foto" type="file" class="form-control input-md" required>
  </div>
</div>
</fieldset>
<br>
<table>
<tr>
<td>
<%if(proveedores.isEmpty()){ %>
<a class="btn btn-outline-secondary" href="listarLibroServlet">Volver</a>
<%}else{ %>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregar� un nuevo libro. Desea confirmar?')">Agregar Libro</button>
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