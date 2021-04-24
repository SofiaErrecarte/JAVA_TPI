<%@page import="java.util.LinkedList"%>
<%@page import="entities.*"%>
<%@page import="logic.*"%>
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
<title>Usuarios</title>
<% 
LinkedList<Persona> personas = (LinkedList<Persona>)request.getAttribute("listaPersonas");
Persona user = (Persona)session.getAttribute("usuario");
PersonaController ctrlPers = new PersonaController();
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet"/>
<link href = "css/botones.css" type="text/css" rel="stylesheet"/>
<link href = "css/messages.css" type="text/css" rel="stylesheet"/>
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
.dealtabutton{
  background-color: #00ce67; 
   border: none;
  border-radius:10px;
  color: white;
  padding: 5px 12px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
   cursor:pointer;
   margin:5px;
 }
 .dealtabutton:hover {
  color:white;
 	 box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
 }
</style>
</head>
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
                                <a class="nav-item nav-link " id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="false">Libros</a>
                                	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="false">Prestamo</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                               	 <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               
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
                   <div class="error"><%=res.getErr_message()%></div>
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
              <form action="selectPersonaServlet" method="post">
             	<div class="input-group">
             <select class="custom-select form-control" id="inlineFormCustomSelectPref" name="opcion" >
			   				<option selected>Ordenar por..</option>
			   				<option value="apeA"> Apellido A-Z </option>
			   				<option value="apeZ">Apellido Z-A</option>
			   				<option value="nomA">Nombre A-Z</option>
			   				<option value="nomZ">Nombre Z-A</option>
			  			</select>
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" name="AplicarFiltro" value= "Aplicar Filtro"> 
			      </span></div>
			      </form>
			  </td> 		 
			  <td>		
			  <form action="buscarPersonaServlet" method="post">
			  	<div class="input-group">
			      <input type="text" class="form-control" placeholder="ID Persona"type="text" name="txtbuscar">
			      <span class="input-group-btn">
			        <input class="btn btn-outline-secondary" type="submit" value="Buscar">
			      </span></div>
			      </form>
			      </td>
			      <td>
					<a href="agregarPersona.jsp" title="Agregar Persona" class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
			      
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
                                            <th class="text-center">ID</th>
		                    		    	<th class="text-center">Nombre</th>
		                    		    	<th class="text-center">Apellido</th>
		                        			<th class="text-center">DNI</th>
		                        			<th class="text-center">Telefono</th>
		                        			<th class="text-center">Dirección</th>
		                        			<th class="text-center">Email</th>
		                        			<th class="text-center">Estado</th>
		                        			<th class="text-center"> Acciones </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                  
                                        <% for (Persona p : personas) { %>
                    			<tr>
                    			
                    				<td class="text-center"><%=p.getIdPersona()%></td>
                    				<td class="text-center"><%=p.getNombre()%></td>
                    				<td class="text-center"><%=p.getApellido()%></td>
                    				<td class="text-center"><%=p.getDni()%></td>
                    				<td class="text-center"><%=p.getTelefono()%></td>
                    				<td class="text-center"><%=p.getDireccion()%></td>
                    				<td class="text-center"><%=p.getEmail()%></td>
                    				<%if(p.getIdPersona()!=0){ %>
                    				<%if(p.isActivo()){ %>
                    				<td class="text-center" bgcolor="green">Activo</td>
                    				<%}else{ %>
                    				<td class="text-center" bgcolor="red">Inactivo</td>
                    				<%} %> 
                    				<%if (user.isAdmin()) {%>
                    				<td class="text-center">
                    				<a class="editbutton" href="modificarPersonaServlet?id=<%=p.getIdPersona()%>" title="Editar"><i class="fa fa-pencil"></i></a>
									<% 
									if(p.isActivo()){
									%>
									<a class="deletebutton" href="darDeBajaPersonaServlet?id=<%=p.getIdPersona()%>" onclick="return confirm('Se dará de baja el usuario. Desea confirmar?')"title="Dar de Baja"><i class="fa fa-thumbs-down"></i></a>
									<%}else{%>
									<a class="dealtabutton" href="darDeAltaPersonaServlet?id=<%=p.getIdPersona()%>" onclick="return confirm('Se dará de alta el usuario. Desea confirmar?')"title="Dar de Alta"><i class="fa fa-thumbs-up"></i></a>
									<%} %>
									<a class="ejemplaresbutton" href="listarPrestamoPersonaServlet?id=<%=p.getIdPersona()%>" title="Prestamos"><i class="fa fa-list-ul"></i></a>
									</td>
										<%}} %>
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