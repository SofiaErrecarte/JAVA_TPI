<%@page import="java.util.LinkedList"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@page import="entities.MyResult"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Politicas Prestamo</title>
<% 
LinkedList<PoliticaPrestamo> politicas = (LinkedList<PoliticaPrestamo>)request.getAttribute("listapoliticas");
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
</head>
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
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
                          		<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               	 
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            </div>
             </div>
                </div>
 <%if ((request.getAttribute("advertencia"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("advertencia")%> </div>		
	<% } %> 
<%if ((request.getAttribute("msjFiltro"))!=null) { %>
		<div class="warning"> <%=request.getAttribute("msjFiltro")%> </div>		
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
              <form action="selectPoliticaServlet" method="post">
             	<div class="input-group">
             <select class="custom-select form-control" id="inlineFormCustomSelectPref" name="opcion" >
			   				<option selected>Ordenar por..</option>
			   				<option value="idmenor"> Menor a Mayor ID</option>
			   				<option value="idmayor">Mayor a Menor ID</option>
			   				<option value="fechamayor">Menor a Mayor Fecha Alta</option>
			   				<option value="fechamenor">Mayor a Menor Fecha Alta</option>
			   				
			  			</select>
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" name="AplicarFiltro" value= "Aplicar Filtro"> 
			      </span></div>
			      </form>
			  </td> 		 
			  <td>		
			  <form action="buscarPoliticaServlet" method="post">
			  	<div class="input-group">
			      <input type="text" class="form-control" placeholder="ID Politica"type="text" name="txtbuscar">
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" value="Buscar" required>
			      </span></div>
			      </form>
			      </td>
			      <td>
			       <a href="agregarPolitica.jsp" title="Agregar Politica" method="post" class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
			      </td>
			  </tr>
			  </table>
			  </div>
               	</div>

                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                    		    	<th class="text-center">Fecha Alta</th>
                        			<th class="text-center">Libros Socio</th>
                        			<th class="text-center">Libros No Socio </th>                        			
                        			<th class="text-center"> Acciones </th>
                        			
                                        </tr>
                                    </thead>
                                 <tbody>
                              
                    		<% for (PoliticaPrestamo pp : politicas) { %>
                    			<tr>
                    				<td class="text-center"><%=pp.getIdPoliticaPrestamo()%></td>
                    				<td class="text-center"><%=pp.getFechaAlta()%></td>
                    				<td class="text-center"><%=pp.getCantMaximaSocio()%></td>
                    				<td class="text-center"><%=pp.getCantMaximaNoSocio()%></td>
                    			<%if(pp.getIdPoliticaPrestamo()!=0){ %>
                    				<td class="text-center">
                                <a href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>" class="editbutton"title="Consultar"><i class="fa fa-eye"></i></a>
                               <a href="borrarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>" class="deletebutton" title="Eliminar" onclick="return confirm('Se eliminará la política. Desea confirmar?')"><i class="fa fa-trash"></i></a>
                            		</td>                    			                    				
                    			</tr>
                    			<%} %>
                    		<% } %>
                    		</tbody>	
                                </table>
                                
                                
                            </div>
                            
                        </div>
                        
                       
							 
						
                   
            </section>
 <br>      
 <br>   
<%@ include file = "footer.jsp" %>

 	<!--  <form action="agregarPolitica.jsp" method="post">
    <button type="submit">Agregar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="editarPolitica.jsp" method="post">
    <button type="submit">Editar Politica de Prestamo</button>
 	</form> 
 	
 	<form action="borrarPolitica.jsp" method="post">
    <button type="submit">Borrar Politica de Prestamo</button>
	 -->

         <!-- Footer -->

</body>
</html>
