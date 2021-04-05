<%@page import="java.util.LinkedList"%>
<%@page import="entities.*"%>
<%@page import="java.util.Calendar"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros</title>
<% 
LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
Persona user = (Persona)session.getAttribute("usuario");
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


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
            <div class="container">
            <div class="container buscar">
                <a href="agregarLibro.jsp" method="post" class="btn btn-success">+ Nuevo</a>
               	<form action="buscarLibroServlet" class="form">
               			<input class="form-control" type="text" name="txtbuscar">
               			<input class="btn btn" type="submit" value="Buscar"	>
               			               
               	</form>
                </div>    
               </div>
               
               
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
		                    		    	<th>titulo</th>
		                        			<th>ISBN</th>
		                        			<th>Numero de edicion</th>
		                        			<th>Genero</th>
		                        			<th>Proveedor</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Libro lib : ll) { %>
                    			<tr>
                    				<td><%=lib.getIdLibro()%></td>
                    				<td><%=lib.getTitulo()%></td>
                    				<td><%=lib.getIsbn()%></td>
                    				<%-- <td><%=lib.getFechaEdicion()%></td> --%>
                    				<td><%=lib.getNroEdicion()%></td>
                    				<%-- <td><%=lib.getCantDiasMaxPrestamo()%></td> --%>
                    				<td><%=lib.getGenero()%></td>
                    				<td><%=lib.getCUIT()%> - <%=lib.getRazonSocialProv()%></td>
                    				<%if (user.isAdmin()) {%>
                    				<td> <a class="editbutton"
									href="modificarLibroServlet?id=<%=lib.getIdLibro()%>">
										Editar </a></td>
										<td><a class="deletebutton"
									href="borrarLibroServlet?id=<%=lib.getIdLibro()%>">
										Eliminar</a></td> 
									<td> <a class="ejemplaresbutton"
									href="listarEjemplaresServlet?id=<%=lib.getIdLibro()%>">
										Ejemplares </a></td>
										<%} %>
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
                            <td>
                           
                             <form action="agregarLibro.jsp" method="post">
                             <button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Libro</button>
							 </form> 
							</td>
							 <%-- <td>
								<form action="index.jsp" method="post">
                             <button type="button" = onclick="history.back()" class= "addbutton" name="Volver" value="Volver">>Volver</button>
                             <%user = null; %>
							 </form> 
							  </td> --%>
							 
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
