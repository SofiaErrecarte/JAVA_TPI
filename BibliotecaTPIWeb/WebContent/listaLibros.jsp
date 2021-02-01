<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@page import="entities.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<% 
LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
LinkedList<Proveedor> list = (LinkedList<Proveedor>)request.getAttribute("listaProveedores");
LinkedList<PoliticaPrestamo> politicas = (LinkedList<PoliticaPrestamo>)request.getAttribute("listapoliticas");
%>

<link href = "css/listado.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<!------ Include the above in your HEAD tag ---------->

<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="true">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
                            </div>
                        </nav>
                        
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
		                    		    	<th>titulo</th>
		                        			<th>ISBN</th>
		                        			<th>fecha Edicion</th>
		                        			<th>Numero de edicion</th>
		                        			<th>cant de dias de prestamo</th>
		                        			<th>género</th>
		                        			<th>Id Proveedor</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Libro lib : ll) { %>
                    			<tr>
                    				<td><%=lib.getIdLibro()%></td>
                    				<td><%=lib.getTitulo()%></td>
                    				<td><%=lib.getIsbn()%></td>
                    				<td><%=lib.getFechaEdicion()%></td>
                    				<td><%=lib.getNroEdicion()%></td>
                    				<td><%=lib.getCantDiasMaxPrestamo()%></td>
                    				<td><%=lib.getGenero()%></td>
                    				<td><%=lib.getIdProveedor()%></td>
                    				<td> <a class="editbutton"
									href="modificarLibroServlet?id=<%=lib.getIdLibro()%>">
										Editar </a></td>
										<td><a class="deletebutton"
									href="borrarLibroServlet?id=<%=lib.getIdLibro()%>">
										Eliminar</a></td>
                    				 </tr>
                    		<% } %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Id Proveedor</th>
		                    				<th>CUIT</th>
		                    		    	<th>Razón Social</th>
		                        			<th>Teléfono</th>
		                        			<th>E-Mail</th>
		                        			<th>Dirección</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Proveedor prov : list) { %>
                    			<tr>
                    				<td><%=prov.getIdProveedor()%></td>
                    				<td><%=prov.getCUIT()%></td>
                    				<td><%=prov.getRazonSocial() %></td>
                    				<td><%=prov.getTelefono()%></td>
                    				<td><%=prov.getMail()%></td>
                    				<td><%=prov.getDireccion()%></td>
                    				
                    				<td> <a class="editbutton"
									href="modificarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Editar </a>
										<a class="deletebutton"
									href="borrarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Eliminar</a></td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		<% } %> 
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <table class="table" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
		                    		    	<th>Fecha Alta</th>
		                        			<th>Libros Socio</th>
		                        			<th>libros No Socio/th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (PoliticaPrestamo pp : politicas) { %>
                    			<tr>
                    				<td><%=pp.getIdPoliticaPrestamo()%></td>
                    				<td><%=pp.getFechaAlta()%></td>
                    				<td><%=pp.getCantMaximaSocio()%></td>
                    				<td><%=pp.getCantMaximaNoSocio()%></td>
                    				<td> <a class="editbutton"
									href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>">
										Editar </a>
										<a class="deletebutton"
									href="editarPoliticaServlet?id=<%=pp.getIdPoliticaPrestamo()%>">
										Eliminar</a></td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    			<% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
