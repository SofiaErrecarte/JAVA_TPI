<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.PoliticaPrestamo"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Politica de Prestamo</title>
<% PoliticaPrestamo pp=(PoliticaPrestamo)request.getAttribute("politicaAEditar"); %>
</head>
<body>

<form action="editarPoliticaServlet" method="post">
<label for="id"><b>ID Politica</b></label>
    <input type="number" name="id"required>
  <div class="container">
    <label for="librosSoc"><b>Cantidad libros Socio</b></label>
    <input type="number" name="librosSocio"required>

	<label for="librosNoSoc"><b>Cantidad Libros No Socio</b></label>
    <input type="number" name="librosNoSocio"required>
    <button type="submit">Modificar</button>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
</form>
<form  action="listarPoliticaServlet" method="post">
	<button type="submit">Ver Politicas</button>
    </form> 

</body>
</html>