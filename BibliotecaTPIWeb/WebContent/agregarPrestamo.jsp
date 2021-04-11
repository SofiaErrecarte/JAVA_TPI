<%@page import="entities.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
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
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<%@ include file="navInicio.jsp"%>
<section id="tabs" class="project-tab" style = "font-family:arial" size=3>
            
<div class="tab-content" id="nav-tabContent">
<form class="form-horizontal" action="agregarPrestamoServlet" method="post">
<section>              
<fieldset>  
                            	
               	<div class="tab-content" id="nav-tabContent"> 
				<label for="txtidpersona">Ingrese el Cliente</label>  
				  <div>
				   <% if( personas != null) {%>
				                            <select name="idPersona" class="form-control">
				                                <%  for(int i = 0; i < personas.size(); i++) {
				                                   Persona p = (Persona)personas.get(i);
				                                %>
				                                <option value="<%= p.getIdPersona() %>"><%= p.getDni()%> - <%=p.getNombre()%> <%=p.getApellido() %></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay personas cargadas. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
				                        	<td> Su cliente no se encuentra en la lista? <a class="agreggatebutton"
													href="agregarProveedor.jsp">
														Añadir un nuevo cliente</a></td>
				  </div>
				</div>
               	
               	<div class="tab-content" id="nav-tabContent">
               	<label class="col-md-4 control-label" for="fechaPrestamo">Ingrese la Fecha de Préstamo</label>  
               	<input class="form-control" type="date" id="fechaPrestamo" name="fechaPrestamo" style="display=block">	     
               	</div>
                 
                
                <div class="tab-content" id="nav-tabContent">
               	<label class="col-md-4 control-label" for="fechaDevolucion">Ingrese la Fecha de Devolución</label>  
               	<input class="form-control" type="date" id="fechaDevolucion" name="fechaDevolucion" style="display=block">	     
                   
                </div>  
                
                      
</fieldset>						
<td>
<button class="addbutton">Agregar Prestamo</button>
</td>
<td>
<input type="button" onclick="history.back()" class="addbutton" name="Volver" value="Volver" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto">
</td>
</section>		
					 </form>
					 </div>
 				

							  
							  
</section>
<%@ include file = "footer.jsp" %>
</body>
</html>