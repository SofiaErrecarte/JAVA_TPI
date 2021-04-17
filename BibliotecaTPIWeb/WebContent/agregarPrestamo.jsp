<%@page import="entities.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.MyResult"%>  
<%@page import="entities.Persona"%>  
<%@page import="logic.PersonaController"%>  
<%@page import="entities.Libro"%>  
<%@page import="logic.LibroController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Prestamo</title>
<%PersonaController ctrlPer = new PersonaController();
LinkedList<Persona> personas = ctrlPer.getAllPersonas(); %>
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
<section id="tabs" class="project-tab" style = "font-family:arial" size=3>  
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
            <br>
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
<form class="form-horizontal" action="agregarPrestamoServlet" method="post">
<section>              
<fieldset>  
                            	
               	<div class="form-group">
  			<label class="col-md-4 control-label" for="idPersona">Ingrese el Cliente: </label>  
 			<div class="col-md-4">
				   <% if( personas != null) {%>
				                            <select name="idPersona" class="form-control">
				                                <%  for(int i = 0; i < personas.size(); i++) {
				                                   Persona p = (Persona)personas.get(i);
				                                %>
				                                <option value="<%= p.getIdPersona() %>"><%= p.getDni()%> - <%=p.getNombre()%> <%=p.getApellido() %></option>
				                                <% } %>
				                            </select>
				                            <% }%>
				  </div>
				</div>
               	<div class="form-group">
               	<label class="col-md-4 control-label" for="fechaPrestamo">Ingrese la Fecha de Préstamo</label>  
               	<div class="col-md-4">
               	<input class="form-control" type="date" id="fechaPrestamo" name="fechaPrestamo" style="display=block" required>	     
               	</div>
               	</div>
                 
                
                 	<div class="form-group">
               	<label class="col-md-4 control-label" for="fechaDevolucion">Ingrese la Fecha de Devolución Estimada</label>  
               	<div class="col-md-4">
               	<input class="form-control" type="date" id="fechaDevolucion" name="fechaDevolucion" style="display=block">	     
                 </div>
               	</div> 
                
                      
</fieldset>						
<table>
<tr>
<td>
<button class="btn btn-outline-primary" onclick="return confirm('Se agregará un nuevo prestamo. Desea confirmar?')">Agregar Prestamo</button>
<a class="btn btn-outline-secondary" href="listarPrestamosServlet">Volver</a>
</td>
</tr>
</table>
</section>		
					 </form>
 				

							  
							  
</section>
<%@ include file = "footer.jsp" %>
</body>
</html>