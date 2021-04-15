<%@page import="java.util.LinkedList"%>
<%@page import="entities.Libro"%>
<%@page import="logic.LibroController"%>
<%@page import="entities.Proveedor"%>  
<%@page import="logic.ProveedorController"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
ProveedorController ctrlProv = new ProveedorController();
LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar libro</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
 <% //Libro lib = (Libro)request.getAttribute("libroAEditar");
 	LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
    Libro lib=(Libro)request.getAttribute("libroAEditar");
     //ArrayList < Opcion > opciones = cc . getOpcionesByIdCaracteristica (c . getIdCaracteristica ());
     
 %>
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
                                <a class="nav-item nav-link " id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
<form class="form-horizontal" action="modificarLibroServlet" method="post">
<section>
<fieldset>

<div class="form-group">
  <label class="col-md-4 control-label" for="id">ID Libro: </label>  
  <div class="col-md-4">
      <%-- <input type="text" name="id" value=<%=lib.getIdLibro()%> class="form-control input-md" hidden="true"> --%>
      <!-- Este me guarda el atributo id -->
       <input type="text" name="id" value=<%=lib.getIdLibro()%> hidden="true">
       <!--  Este me lo muestra deshabilitado para q se vea el numero -->
	 <input type="text" name="idLibro" value=<%=lib.getIdLibro()%> class="form-control input-md" disabled>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="razonSocial">ISBN:</label>  
  <div class="col-md-4">
    <input type="text"  name="isbn" value=<%=lib.getIsbn()%> class="form-control input-md">
     </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="cuit">Titulo:</label>  
  <div class="col-md-4">  
  <input type="text" name="titulo" value=<%=lib.getTitulo()%> class="form-control input-md"  required>
  </div>
</div>



<div class="form-group">
  <label class="col-md-4 control-label" for="razonSocial">Nro Edición:</label>  
  <div class="col-md-4">
    <input type="text" name="nroedicion" value=<%=lib.getNroEdicion()%> class="form-control input-md" required>
</div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="cantdiasprestamo">Cant Dias Prestamo:</label>  
  <div class="col-md-4">
    <input type="number" name="cantdiasprestamo" value=<%=lib.getCantDiasMaxPrestamo()%> class="form-control input-md" required>
     </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="genero">Género:</label>  
  <div class="col-md-4">
   <input type="text" name="genero" value=<%=lib.getGenero()%>  class="form-control input-md" required>
     </div>
</div>

 <div class="form-group">
  <label class="col-md-4 control-label" for="idProveedor">ID Proveedor:</label>  
  <div class="col-md-4">
   <% if( proveedores != null) {%>
                            <select name="idProveedor" value=<%=lib.getIdProveedor()%> class="form-control input-md">
                                <option selected="true" value="<%= lib.getIdProveedor() %>"><%= lib.getIdProveedor()%></option>
                                <%  for(int i = 0; i < proveedores.size(); i++) {
                                    Proveedor p = (Proveedor)proveedores.get(i);
                                %>
                                <option value="<%= p.getIdProveedor() %>"><%=p.getCUIT()%> - <%= p.getRazonSocial()%></option>
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

<table>
<td>
<button class="addbutton">Modificar</button>
</td>
<td>
							 <a class="addbutton" href="listarLibroServlet">Volver</a>
							 
							
							  </td>
</table>
<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>
	</section>
	</form>
	</section>
<%@ include file = "footer.jsp" %>
</body>
</html>