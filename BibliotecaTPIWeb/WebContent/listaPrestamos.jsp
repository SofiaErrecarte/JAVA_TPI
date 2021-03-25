<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@page import="entities.Prestamo"%>
<%@page import="entities.Persona"%>
<%@page import="java.util.Calendar"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prestamos</title>
<% 
LinkedList<Prestamo> prestamos = (LinkedList<Prestamo>)request.getAttribute("listaprestamos");
Persona user = (Persona)session.getAttribute("usuario");
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
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
                                	
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
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
            <div class="container buscar">
              <a href="prestamo.jsp" method="post" class="btn btn-success">+ Nuevo</a>
            
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID Prestamo</th>
		                    		    	<th>Fecha Prestamo</th>
		                        			<th>Fecha Devolucion </th>
		                        			<th>ID Persona</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Prestamo p : prestamos) { %>
                    			<tr>
                    				<td><%=p.getIdPrestamo()%></td>
                    				<td><%=p.getFechaPrestamo()%></td>
                    				<td><%=p.getFechaADevoler()%></td>
                    				<td><%=p.getIdPersona()%></td>
                    				
                    				
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
                           
                             <form action="prestamo.jsp" method="post">
                             <button class="addbutton">Agregar Prestamo</button>
							 </form> 
							</td>
							 	<!--  <form action="borrarLibro.jsp" method="post">
							 	 <button class="btn btn-lg btn-primary">Borrar Libro</button>
							 	</form>
							 	<form  action="modificarLibro.jsp" method="post">
							 	 <button class="btn btn-lg btn-primary">Modificar Libro</button>
							    </form>-->
							 <td>
							    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
							  <!-- <input type="button" = onclick="history.back()" class="btn btn-lg btn-primary" name="Volver" value="Volver" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto"> -->
								<form action="index.jsp" method="post">
                             <button type="button"  onclick="history.back()" class= "addbutton" name="Volver" value="Volver">>Volver</button>
							 </form> 
							  </td>
							 
                            </table>
                            
        </section>
         
 						
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
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>
