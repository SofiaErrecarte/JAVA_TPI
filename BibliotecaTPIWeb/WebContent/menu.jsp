<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Persona"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
<%
		Persona per = (Persona)session.getAttribute("usuario");
	%>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href = "css/listado.css" rel="stylesheet">
<body>
<!--  <div class="container">
		<div class="row">
        	<h4>Libros</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>email</th>
                    		    	<th>contraseña</th>
                        			
                      			</tr>
                      		</thead>
                    		<tbody>
                    			<tr>
                    				
                    				<td>
                    				
                    			</tr>
                    		</tbody>	
	</div> --><!-- /container --> 
	<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab"
                                	 href="listarLibroServlet" role="tab" aria-controls="nav-home" aria-selected="true">Libros</a>
                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" 
                                	href="listarProveedorServlet" role="tab" aria-controls="nav-profile" aria-selected="false">Proveedores</a>
                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" 	
                                href="listarPoliticaServlet" role="tab" aria-controls="nav-contact" aria-selected="false">Politicas Prestamo</a>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
    </section>
                        
	<!--  <form  action="listarLibroServlet" method="post">
	<button type="submit">Listar Libros</button>
    </form>
    <form action="listarProveedorServlet" method="post">
    <button type="submit">Listar Proveedores</button>
 	</form> 
 	 
 	<form action="listarLibrosProvServlet" method="post">
    <button type="submit">Lista Libros por Proveedor</button>
 	</form> 

	<form action="listarPoliticaServlet" method="post">
    <button type="submit">Listar Politica de Prestamo</button>
 	</form> -->
 	

</body>
</html>