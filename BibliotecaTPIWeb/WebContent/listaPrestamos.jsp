<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@page import="entities.Prestamo"%>
<%@page import="entities.Persona"%>
<%@page import="java.util.Calendar"%>
<%@page import="entities.MyResult"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prestamos</title>
<% 
String var = (String)request.getAttribute("Persona");
LinkedList<Prestamo> prestamos = (LinkedList<Prestamo>)request.getAttribute("listaPrestamos");
LinkedList<Prestamo> prestamos_personas = (LinkedList<Prestamo>)request.getAttribute("listaPrestamosPersonas");
Persona user = (Persona)session.getAttribute("usuario");
%>
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


<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
<body>
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab" style = "font-family:arial; size=3">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                	 <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>"  role="tab" aria-controls="nav-contact" aria-selected="true">Prestamo</a>
                                <%if (user.isAdmin()) {%>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               	 <%} %>
                               <br>
                            </div>
                        </nav>
                    </div>
                </div>
                 </div>
            </div>
            </div>
  <%if (user.isAdmin() && var!="prestamospersona"){ %>
  <%if ((request.getAttribute("errorSinPolitica"))!=null) { %>
		<div class="error"> <%=request.getAttribute("errorSinPolitica")%> </div>		
	<% } %> 
	<%if ((request.getAttribute("msjFiltro"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("msjFiltro")%> </div>		
	<% } %>
  <%if ((request.getAttribute("advertencia"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("advertencia")%> </div>		
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
 
             <div class="container w3-container">
              <div class="row">
              <table class="table" class="text-center">
              <tr>
              
               <td class="text-center">
              <!--  div class="form-group"-->
              <form action="selectPrestamoServlet" method="post">
             	<div class="input-group">
             <select class="custom-select form-control" id="inlineFormCustomSelectPref" name="opcion" >
			   				<option selected>Ordenar por..</option>
			   				<option value="idmenor"> Menor a Mayor ID</option>
			   				<option value="idmayor">Mayor a Menor ID</option>
			   				<option value="fechamenor">Menor a Mayor Fecha Prestamo</option>
			   				<option value="fechamayor">Mayor a Menor Fecha Prestamo</option>
			   				
			  			</select>
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" name="AplicarFiltro" value= "Aplicar Filtro"> 
			      </span></div>
			      </form>
			  </td> 		 
			  <td>		
			  <form action="buscarPrestamoServlet" method="post">
			  	<div class="input-group">
			      <input type="text" class="form-control" placeholder="ID Prestamo"type="text" name="txtbuscar">
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" value="Buscar">
			      </span></div>
			      </form>
			      </td>
			      <%if ((request.getAttribute("errorSinPolitica"))==null) { %>
			      <td>
              <a href="agregarPrestamo.jsp" title="Agregar Prestamo" class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
               </td><%} %>
			  </tr>
			  </table>
			  </div>
               	</div>

                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="table-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Prestamo</th>
		                    		    	<th class="text-center">Fecha Prestamo</th>
		                        			<th class="text-center">Fecha a Devolver</th>
		                        			<th class="text-center">Fecha Devolución</th>
		                        			<th class="text-center">ID Persona</th>
		                        			<th class="text-center">Estado</th>
                                       		<th class="text-center"> Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                        <% for (Prestamo p : prestamos) { %>
                    			<tr>
                    				<td class="text-center"><%=p.getIdPrestamo()%></td>
                    				<td class="text-center"><%=p.getFechaPrestamo()%></td>
                    				<td class="text-center"><%=p.getFechaADevoler()%></td>
                    				<td class="text-center"><%=p.getFechaDevolucion()%></td>
                    				<td class="text-center"><%=p.getIdPersona()%></td>
                    				<td class="text-center"><%=p.getEstado()%></td>
                    				<td class="text-center"> 
                    				<%if(p.getEstado().equals("Abierto")) {%>
                    				<a class="editbutton" href="modificarPrestamoServlet?id=<%=p.getIdPrestamo()%>" title="Editar"><i class="fa fa-pencil"></i></a>
                    				<%} %>
									<a class="ejemplaresbutton" href="listarLineasPrestamoServlet?id=<%=p.getIdPrestamo()%>" title="Detalle"><i class="fa fa-list-ul"></i></a>
									<%if(p.getEstado().equals("Abierto")) {%>
									<a class="devueltobutton" href="devolverPrestamoServlet?id=<%=p.getIdPrestamo()%>" onclick="return confirm('Prestamo devuelto. Desea confirmar?')" title="Devuelto"><i class="fa fa-check"></i></a>
									<a class="deletebutton" href="darDeBajaPrestamoServlet?id=<%=p.getIdPrestamo()%>" onclick="return confirm('Se dará de baja el prestamo. Desea confirmar?')" title="Dar de Baja"><i class="fa fa-thumbs-down"></i></a>
										<%} %>
									</td>
                    			 </tr>
                    		<% } %>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                   
                </div>
          
      <!--   <table>
                            <td>
                           
                             <form action="agregarPrestamo.jsp" method="post">
                             <button class="addbutton">Agregar Prestamo</button>
							 </form> 
							</td>
							 <td>
							    <a type="button" class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto; color: white" href="listarLibroServlet" >Inicio</a>
							  </td>
							 
                            </table>-->
        </section>
   <%@ include file = "footer.jsp" %>
<%}%>
 <%if (user.isAdmin()==false || var=="prestamospersona") {%>
 
  <%if ((request.getAttribute("advertencia"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("advertencia")%> </div>		
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
                  <h3 class="login-heading mb-4 text-center">Prestamos de <%=user.getNombre() %>   <%=user.getApellido() %>  </h3>
 
             <div class="container w3-container">
              <div class="row">
              <table class="table" class="text-center">
              <tr>
              
               <td class="text-center">
              <!--  div class="form-group"-->
              <form action="selectPrestamoServlet" method="post">
             	<div class="input-group">
             <select class="custom-select form-control" id="inlineFormCustomSelectPref" name="opcion" >
			   				<option selected>Ordenar por..</option>
			   				<option value="idmenor"> Menor a Mayor ID</option>
			   				<option value="idmayor">Mayor a Menor ID</option>
			   				<option value="fechamenor">Menor a Mayor Fecha Prestamo</option>
			   				<option value="fechamayor">Mayor a Menor Fecha Prestamo</option>
			   				
			  			</select>
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" name="AplicarFiltro" value= "Aplicar Filtro"> 
			      </span></div>
			      </form>
			  </td> 		 
			  
			      <td>
              <a class="btn btn-outline-secondary" title="Volver" href="listarLibroServlet"><i class="fa fa-mail-reply">Volver</i></a>
               </td>
			  </tr>
			  </table>
			  </div>
               	</div>

                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="table-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Persona</th>
		                    		    	<th class="text-center">Fecha Prestamo</th>
		                        			<th class="text-center">Fecha a Devolver</th>
		                        			<th class="text-center">Fecha Devolución</th>
		                        			<th class="text-center">Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Prestamo p : prestamos_personas) { %>
                    			<tr>
                    			
                    				<td class="text-center"><%=p.getIdPersona()%></td>
                    				<td class="text-center"><%=p.getFechaPrestamo()%></td>
                    				<td class="text-center"><%=p.getFechaADevoler()%></td>
                    				<td class="text-center"><%=p.getFechaDevolucion()%></td>
                    				<td class="text-center"><%=p.getEstado()%></td>
                    				<td class="text-center"> 
                    				<%} %>
									</td>
                    			 </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                   
              
          
      <!--   <table>
                            <td>
                           
                             <form action="agregarPrestamo.jsp" method="post">
                             <button class="addbutton">Agregar Prestamo</button>
							 </form> 
							</td>
							 <td>
							    <a type="button" class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto; color: white" href="listarLibroServlet" >Inicio</a>
							  </td>
							 
                            </table>-->
     

         <!-- Footer -->

<%} %>
</body>
</html>
