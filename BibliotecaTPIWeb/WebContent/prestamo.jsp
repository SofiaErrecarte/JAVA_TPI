<%@page import="entities.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Prestamo</title>
<%
//Prestamo p = (Prestamo)request.getAttribute("nuevoprestamo");
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<link href = "css/botones.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">@import url("css/calendar-blue.css");</style>


<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<section id="tabs" class="project-tab" style = "font-family:arial" size=3>
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
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link active" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="true">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            </div>
             </div>
                </div> 
                <div class="tab-content" id="nav-tabContent">
                <form action="agregarPrestamoServlet" method="post">
                
                <div class="tab-content" id="nav-tabContent">
               	<label class="col-md-4 control-label" for="idPersona">Ingrese el ID de la Persona</label>  
               	<input class="form-control" type="text" name="txtidpersona">
               	</div>
               	
               	<div class="tab-content" id="nav-tabContent">
               	<label class="col-md-4 control-label" for="fechaPrestamo">Ingrese la Fecha de Préstamo</label>  
               	<input class="form-control" type="date" id="fechaPrestamo" name="fechaPrestamo" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block">	     
               	     
                </div>
                
                <div class="tab-content" id="nav-tabContent">
               	<label class="col-md-4 control-label" for="fechaDevolucion">Ingrese la Fecha de Devolución</label>  
               	<input class="form-control" type="date" id="fechaDevolucion" name="fechaDevolucion" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block">	     
                   
                </div>
                
                                <table class="table" class="text-center" id="tablaprueba">
                                    <thead>
					        <tr>
					          <th class="text-center">ID Libro</th>
					          <th class="text-center">ID Ejemplar</th>
					          <th class="text-center">Fecha Devolucion</th>
					          			        </tr>
					      </thead>
					      <tr>
					     
					 	 <td><input type="text" name="idlibro" id="idlibro" class="form-control input-md"  ></td>
					     <td><input type="text" name="txtidejemplar" id="txtidejemplar" class="form-control input-md" ></td>
						 <td><input class="form-control" type="date" id="txtfecha" name="txtfecha" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block"></td>     
					      </tr>
					      <tr>
					     <td><input class="form-control" type="text" name="txtlibro1"></td>
					     <td><input class="form-control" type="text" name="txtidejemplar1"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha1" ></td> 
					      </tr>
					      <tr>
					     <td><input class="form-control" type="text" name="txtlibro2"></td>
					     <td><input class="form-control" type="text" name="txtidejemplar2"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha2" ></td> 
					      </tr>
					      <tr>
					     <td><input class="form-control" type="text" name="txtlibro3"></td>
					     <td><input class="form-control" type="text" name="txtidejemplar3"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha3" ></td> 
					        </tr>
					      
					    </table>
					
								<button class="addbutton">Finalizar</button>
					 </form>
					 </div>
 
							  
							  
</section>
<%@ include file = "footer.jsp" %>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/calendar.js" /></script>
<script type="text/javascript" src="js/calendar-es.js" /></script>
<script type="text/javascript" src="js/calendar-setup.js" /></script>
<script type="text/javascript" src="js/calendar-setup_stripped.js" /></script>
<script type="text/javascript" src="js/calendar_stripped.js" /></script>

</body>
</html>