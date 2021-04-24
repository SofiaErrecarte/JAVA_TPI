<%@page import="java.util.LinkedList"%>
<%@page import="entities.*"%>
<%@page import="logic.LibroController"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.mysql.cj.jdbc.Blob"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="org.omg.CORBA.portable.InputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros</title>
<% 
//LinkedList<Integer> cantidades = (LinkedList<Integer>)request.getAttribute("cantidadesDisp");
LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
Persona user = (Persona)session.getAttribute("usuario");
LibroController ctrlL = new LibroController();
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
 height: 100%;
 font-family: Arial, Helvetica, sans-serif;
}
</style>
<body>
<%@ include file="navInicio.jsp"%>
	<section id="tabs" class="project-tab" style = "font-family:arial;size=3">
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
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
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
                   <div class="warning"><%=res.getErr_message()%></div>
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
              <form action="selectLibroServlet" method="post">
             	<div class="input-group">
             <select class="custom-select form-control" id="inlineFormCustomSelectPref" name="opcion" >
			   				<option selected>Ordenar por..</option>
			   				<option value="tituloA"> Titulo A-Z </option>
			   				<option value="tituloZ">Titulo Z-A</option>
			   				<option value="idmenor">ID Menor a Mayor</option>
			   				<option value="idmayor">ID Mayor a Menor</option>
			   				<option value="proveedores">Proveedores A-Z</option>
			  			</select>
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" name="AplicarFiltro" value= "Aplicar Filtro"> 
			      </span></div>
			      </form>
			  </td> 		 
			  <td>		
			  <form action="buscarLibroServlet" method="post">
			  	<div class="input-group">
			      <input type="text" class="form-control" placeholder="Titulo del Libro"type="text" name="txtbuscar">
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" value="Buscar">
			      </span></div>
			      </form>
			      </td>
			      <td>
			      <%if(user.isAdmin()){ %>
					<a href="agregarLibro.jsp" title="Agregar Libro" class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
			      <%} %>
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
                                        <% int var=1;
                                        for(Libro l:ll){
                                        	var = l.getIdLibro();
                                        }
                                        if(var!=0){ %>
                                        	<th class="text-center"></th>
                                        <%} %>
                                            <th class="text-center">ID</th>
		                    		    	<th class="text-center">Título</th>
		                    		    	<th class="text-center">Autor</th>
		                        			<th class="text-center">ISBN</th>
		                        			<th class="text-center">Numero de edicion</th>
		                        			<th class="text-center">Genero</th>
		                        			<th class="text-center">Proveedor</th>
		                        			<th class="text-center">Cantidad Disponible</th>
		                        			<th class="text-center">Acciones</th>
		                        			<th class="text-center">  </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                  
                                        <%for (Libro lib : ll) { %>
                                       
                    			<tr>
                    			<%if(lib.getIdLibro()!=0){ %>
                    			<%String photo=Base64.getEncoder().encodeToString(lib.getImagen()); %>
                    				<td class="text-center">
                    				<img src="data:image/png;base64,<%=photo%>" />
                    				</td>
                    				<%} %>
                    				<td class="text-center"><%=lib.getIdLibro()%></td>
                    				<td class="text-center"><%=lib.getTitulo()%></td>
                    				<td class="text-center"><%=lib.getAutor()%></td>
                    				<td class="text-center"><%=lib.getIsbn()%></td>
                    				<%-- <td><%=lib.getFechaEdicion()%></td> --%>
                    				<td class="text-center"><%=lib.getNroEdicion()%></td>
                    				<%-- <td><%=lib.getCantDiasMaxPrestamo()%></td> --%>
                    				<td class="text-center"><%=lib.getGenero()%></td>
                    				<td class="text-center"><%=lib.getCUIT()%> - <%=lib.getRazonSocialProv()%></td>
                    				<td class="text-center"><%=ctrlL.cantEjDisponibles(lib)%></td>
                    				
                    				<%if (user.isAdmin() && lib.getIdLibro()!=0) {%>
                    				<td class="text-center">
                    				
                    				<a class="editbutton" href="modificarLibroServlet?id=<%=lib.getIdLibro()%>" title="Editar"><i class="fa fa-pencil"></i></a>
                    				<!-- ver como poner este mensaje pa q el user entienda algo -->
									<a href="borrarLibroServlet?id=<%=lib.getIdLibro()%>" class="deletebutton" title="Eliminar" onclick="return confirm('Se eliminará el libro y/o aquellos ejemplares que no hayan sido asignados a préstamos. Desea confirmar?')"><i class="fa fa-trash"></i></a> 
									<a href="listarEjemplaresServlet?id=<%=lib.getIdLibro()%>" class="ejemplaresbutton" title="Ejemplares"><i class="fa fa-list-ul"></i></a>
									</td>
									
										<%} %>
                    				 </tr>
                    			<%} %>
                    			
                    		
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        <!-- <a type="button" class="editbutton">Inicio</a>
		-->					           
		<!-- <a href="listarLibrosProvServlet" method="post" class="addbutton">Lista Libros por Proveedor</a> -->
       
        </section>
     

         <!-- Footer -->
<%@ include file = "footer.jsp" %>

</body>
</html>
