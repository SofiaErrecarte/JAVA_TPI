<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.LineaPrestamo"%>
<%@page import="entities.PoliticaPrestamo"%>
<%@page import="entities.Prestamo"%>
<%@page import="entities.MyResult"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lineas Prestamo</title>
<%
LinkedList<LineaPrestamo> lpr = (LinkedList<LineaPrestamo>)request.getAttribute("listaLineas");
Prestamo p = (Prestamo)request.getAttribute("prestamo");
int cant = (Integer)request.getAttribute("cantidad");
int limiteNS = (Integer)request.getAttribute("limiteNS");
Persona user = (Persona)session.getAttribute("usuario");
PoliticaPrestamo pp = (PoliticaPrestamo)request.getAttribute("politicaAplicada");
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
  font-family: Arial, Helvetica, sans-serif;
}
</style>
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
                                	 <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPrestamosServlet?id=<%=user.getIdPersona() %>" role="tab" aria-controls="nav-contact" aria-selected="true">Prestamo</a>                                	
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                               	 <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPersonasServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Usuarios</a>
                               
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
                             <br>
                 
  
  <%if (cant>=limiteNS) { %>
		<div class="warning">No puede agregar más libros a este préstamo. Límite de política alcanzado.</div>		
	<% } %>
  
  
  <div class="card">
    <div class="card-body">
      <h4 class="card-title"> Préstamo</h4>
    </div>
  </div>
   <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="table-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Prestamo</th>
		                    		    	<th class="text-center">Fecha Prestamo</th>
		                        			<th class="text-center">Fecha A Devolver </th>
		                        		    
		                        			<th class="text-center">ID Persona</th>
		                        			<th class="text-center">Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                    			<tr>
                    				<td class="text-center"><%=p.getIdPrestamo()%></td>
                    				<td class="text-center"><%=p.getFechaPrestamo()%></td>
                    				<td class="text-center"><%=p.getFechaADevoler()%></td>
                    				<!-- ACA ESTABA LA FECHA DE DEVOLUCIÓN QUE NO USAMOS -->
                    				<td class="text-center"><%=p.getIdPersona()%></td>
                    				<td class="text-center"><%=p.getEstado()%></td>
                    				
                    			 </tr>
                    		
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
   <div class="card">
    <div class="card-body">
      <h5 class="card-title"> Política aplicada: <%=pp.getIdPoliticaPrestamo() %></h5>
      <h6>Límite de libros: <%=pp.getCantMaximaNoSocio()%></h6>
    </div>
  </div>
  <br>
   <div class="container buscar">
                </div>            
						<div class="card">
    <div class="card-body">
      <h4 class="card-title">Líneas de Préstamo</h4>
    </div>
  </div>	       
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <table class="table" class="text-center">
                                       
                                    <thead>
                                        <tr>
                                            <th class="text-center">ID Linea</th>
		                    		    	<th class="text-center">ID Ejemplar</th>
		                    		    	<th class="text-center">Título</th>
		                    		    	<th class="text-center">Devuelto</th> 
		                    		    	<th class="text-center"> Acción </th>
                                       		 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (LineaPrestamo lp : lpr) { %>
                    			<tr>
                    				<td class="text-center"><%=lp.getIdLineaPrestamo()%></td>
                    				<td class="text-center"><%=lp.getIdEjemplar()%></td>
                    				<td class="text-center"><%=lp.getTituloEjemplar()%></td>
                    				<td class="text-center"><%=lp.isDevuelto()%></td> 
                    				<%if(p.getEstado().equals("Abierto")){ %>
									<td class="text-center"><a class="editbutton"
									href="modificarLineaPServlet?id=<%=lp.getIdLineaPrestamo()%>" title="Editar"><i class="fa fa-pencil"></i></a>
									</td> <% } else{ %>
									<td class="text-center">Deshabilitada</td> 
										<% } %>
									

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
      <%if(cant<limiteNS && p.getEstado().equals("Abierto")){ %>
      							 <td>
                         
				 <a href="agregarLineaPrestamoServlet?id=<%=p.getIdPrestamo()%>" method="post" class="w3-button w3-xlarge w3-circle w3-teal" style="float: right;">+</a>
        
							</td> <%} %>
  			
</tr>
                            </table>

        </section>

         <!-- Footer -->
<%@ include file = "footer.jsp" %>

</body>
</html>