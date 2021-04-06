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
                                <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
                            	<a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                               	 
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
                <a href="agregarProveedor.jsp" method="post" class="btn btn-success">+ Nuevo</a>
               	<form action="buscarProveedorServlet" class="form">
               			<input class="form-control" placeholder="CUIT proveedor" type="text" name="txtbuscar">
               			<input class="btn btn" type="submit" value="Buscar"	>
               	</form>
                </div>
                
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center" >
                                    <thead>
                                        <tr>
                                            <th>Id Proveedor</th>
                    				<th  class="text-center">CUIT</th>
                    		    	<th  class="text-center">Razón Social</th>
                        			<th  class="text-center">Teléfono</th>
                        			<th  class="text-center">E-Mail</th>
                        			<th  class="text-center">Dirección</th>
                        			<th  class="text-center">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                    		<% for (Proveedor prov : list) { %>
                    			<tr>
                    				<td  class="text-center"><%=prov.getIdProveedor()%></td>
                    				<td  class="text-center"><%=prov.getCUIT()%></td>
                    				<td  class="text-center"><%=prov.getRazonSocial() %></td>
                    				<td  class="text-center"><%=prov.getTelefono()%></td>
                    				<td  class="text-center"><%=prov.getMail()%></td>
                    				<td class="text-center"><%=prov.getDireccion()%></td>
                    				<td class="text-center">
                    				  <a class="editbutton"	href="modificarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Editar </a>
										<a class="deletebutton"	href="borrarProveedorServlet?id=<%=prov.getIdProveedor()%>">
										Eliminar</a>
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
        
 	 <!--  <table>
                            <td>
                           
                             <form action="agregarProveedor.jsp" method="post">
                             <button class="btn btn-lg btn-primary" style = "FONT-SIZE: 10pt; width:250px;margin:0 auto">Agregar Proveedor</button>
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
