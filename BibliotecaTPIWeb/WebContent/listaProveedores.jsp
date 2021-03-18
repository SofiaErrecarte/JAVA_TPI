<%@page import="java.util.LinkedList"%>
<%@page import="entities.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Proveedores</title>
<% 
   LinkedList<Proveedor> list = (LinkedList<Proveedor>)request.getAttribute("listaProveedores");
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
                                <a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
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
                    				<td>
                    				  <td> <a class="editbutton"
									href="modificarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Editar </a></td>
										<td><a class="deletebutton"
									href="borrarProveedorServlet?id=<%=prov.getIdProveedor()%>">
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
                            <td>
                           
                             <form action="agregarProveedor.jsp" method="post">
                             <button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Proveedor</button>
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
							  <input type="button" = onclick="history.back()" class="btn btn-lg btn-primary" name="Volver" value="Volver" style = "FONT-SIZE: 10pt;width:250px; margin:0 auto">
							
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
