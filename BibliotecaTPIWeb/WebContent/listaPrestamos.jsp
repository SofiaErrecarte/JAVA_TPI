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
LinkedList<Prestamo> prestamos = (LinkedList<Prestamo>)request.getAttribute("listaPrestamos");
Persona user = (Persona)session.getAttribute("usuario");
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link href = "css/messages.css" rel="stylesheet">
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
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                               	 <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Prestamo</a>
                               
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
              <div class="container buscar">
                <a href="agregarPrestamo.jsp" method="post" class="btn btn-success">+ Nuevo</a>
               	<form action="buscarPrestamoServlet" class="form">
               			<input class="form-control" placeholder="ID Prestamo"type="text" name="txtbuscar">
               			<input class="btn btn" type="submit" value="Buscar"	>
               	</form>
                </div>
            
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Prestamo</th>
		                    		    	<th class="text-center">Fecha Prestamo</th>
		                        			<th class="text-center">Fecha Devolucion </th>
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
                    				<td class="text-center"><%=p.getIdPersona()%></td>
                    				<td class="text-center"><%=p.getEstado()%></td>
                    				<td class="text-center"> 
                    				<a class="editbutton" href="modificarPrestamoServlet?id=<%=p.getIdPrestamo()%>">
										Editar </a>
									<a class="ejemplaresbutton" href="listarLineasPrestamoServlet?id=<%=p.getIdPrestamo()%>">
										Detalle </a>
									<a class="devueltobutton" href="devolverPrestamoServlet?id=<%=p.getIdPrestamo()%>">
										Devuelto </a>
									</td>
                    			 </tr>
                    		<% } %>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
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

         <!-- Footer -->
<%@ include file = "footer.jsp" %>
</body>
</html>
