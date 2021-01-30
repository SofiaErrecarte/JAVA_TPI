<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="entities.PoliticaPrestamo"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agragar Politica de Prestamo</title>
<% PoliticaPrestamo politica = (PoliticaPrestamo)session.getAttribute("nuevaPolitica"); %>
</head>
<body>
<%if (politica!= null){%>
	<h4> Politica agregada con éxito</h4>
	<String> mensaje="<script type="text/javascript">alert('Esto se debe de mostrar en el msgbox');</script>";
	out.println(mensaje);
<%} else {%>
	<form action="agregarPoliticaServlet" method="post">

  <div class="container">
    <label for="uname"><b>Fecha Alta</b></label>
    <input type="date"name="fechaalta">

    <label for="numS"><b>Cantidad maxima de libros Socio</b></label>
    <input type="number"name="numsocio"required>

	<label for="numNS"><b>Cantidad maxima de libros No Socio</b></label>
    <input type="number"name="numnosocio"required>


    <button type="submit">Agregar</button>
    
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
</form>
	
<% }%>
</body>
</html>