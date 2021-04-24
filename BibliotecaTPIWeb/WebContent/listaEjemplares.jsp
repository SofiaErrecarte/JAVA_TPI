<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ejemplar"%>
<%@page import="entities.Libro"%>
<%@page import="entities.Persona"%>
<%@page import="entities.MyResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ejemplares</title>
<% 
LinkedList<Ejemplar> ej = (LinkedList<Ejemplar>)request.getAttribute("listaEjemplares");
Libro lib = (Libro)request.getAttribute("libro");
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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab"  style = "font-family:arial; size=3">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                	  <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>"  role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                	 <%if (user.isAdmin()) {%>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                              <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               	 
                                <%} %>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
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
        		   if(res.getResult().equals(MyResult.results.Warning)){
        			   %>
                       <div class="warning"><%=res.getErr_message()%></div>
                      <% 
        		   }else{
        	      %>
                   <div class="error"><%=res.getErr_message()%></div>
                   <%}
                   }}
                 %>
                   <br>
					<div class="panel-group">
 
<div class="card">
    <div class="card-body">
      <h3 class="card-title">Libro: <%=lib.getTitulo()%> </h3>
      <h5 class="card-text">Autor: <%=lib.getAutor()%></h5>
      <h6 class="card-text">Género: <%=lib.getGenero()%></h6>
    </div>
  </div>

               <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Ejemplar </th>
		                    		    	<th class="text-center">Disponible</th>
		                    		    	<th class="text-center"> Acción </th>
                                       
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Ejemplar e : ej) { %>
                    			<tr>
                    				<td class="text-center"><%=e.getIdEjemplar()%></td>
                    				<td class="text-center"><%=e.isDisponible()%></td>
                    				<%if(e.getIdEjemplar()!=0){ %>
									<td class="text-center"><a title="Eliminar" class="deletebutton" onclick="return confirm('Se eliminará el ejemplar. Desea confirmar?')"
									href="borrarEjemplarServlet?id=<%=e.getIdEjemplar()%> ">
										<i class="fa fa-trash"></i></a></td> 
										<% } %>
                    				 </tr>
                    		<% } %>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
      <!--  <table>
      <tr>
      							 <td>
                           			<a class="addbutton"
									href="agregarEjemplarServlet?id=<%//=lib.getIdLibro()%>">
										Agregar Ejemplar </a>
                        
							</td>
                          
							 <td>
							 <a class="addbutton" href="listarLibroServlet">Volver</a>
							 
							
							  </td>
</tr>
                            </table>--> 
							  <a href="agregarEjemplarServlet?id=<%=lib.getIdLibro()%>"  class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
        </section>

         <!-- Footer -->
<%@ include file = "footer.jsp" %>
</body>
</html>