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
                                	 <%if (user.isAdmin()) {%>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                               <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
                                <%} %>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
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
            <div class="container buscar">
                <a href="agregarEjemplarServlet?id=<%=lib.getIdLibro()%>" method="post" class="btn btn-success">+ Nuevo Ejemplar</a>
               	
                </div>
               
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                    <thead>
                                        <tr>
                                        	<th class="text-center">Titulo Libro </th>
                                            <th class="text-center">ID Ejemplar </th>
		                    		    	<th class="text-center">Disponible</th>
		                    		    	<th class="text-center"> Acción </th>
                                       
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Ejemplar e : ej) { %>
                    			<tr>
                    				<td class="text-center"><%=e.getTitulo()%></td>
                    				<td class="text-center"><%=e.getIdEjemplar()%></td>
                    				<td class="text-center"><%=e.isDisponible()%></td>
									<td class="text-center"><a class="deletebutton"
									href="borrarEjemplarServlet?id=<%=e.getIdEjemplar()%>">
										Eliminar</a></td> 

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

        </section>

         <!-- Footer -->
<%@ include file = "footer.jsp" %>
</body>
</html>