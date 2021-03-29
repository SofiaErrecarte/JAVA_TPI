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
	<section id="tabs" class="project-tab">
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
            
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID </th>
		                    		    	<th>Disponible</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Ejemplar e : ej) { %>
                    			<tr>
                    				<td><%=e.getIdEjemplar()%></td>
                    				<td><%=e.isDisponible()%></td>
									<td><a class="deletebutton"
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
       <table>
      <tr>
      							 <td>
                           			<a class="addbutton"
									href="agregarEjemplarServlet?id=<%=lib.getIdLibro()%>">
										Agregar Ejemplar </a>
                        
							</td>
                          
							 <td>
							 <a class="addbutton" href="listarLibroServlet">Volver</a>
							 
							
							  </td>
</tr>
                            </table>

        </section>

         <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>