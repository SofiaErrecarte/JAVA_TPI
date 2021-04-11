<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Libro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar libro</title>
<!--   LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros2");
-->
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>

	<form action="borrarLibroServlet" method="post" style = "font-family:arial; size=3">

  	<div class="container">
  	
    <label for="id"><b>Ingrese el ID del libro que desea borrar:</b></label>
    <input type="number"  name="idlibro">

	
    <button type="submit" class="deletebutton">Borrar libro</button>
    
  </div>

	
  <div class="container">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" class="addbutton" name="Cancelar" value="Cancelar">
  </div>
  
  
</form>
	<form  action="listarLibroServlet" method="post" style = "font-family:arial; size=3">
	<button type="submit" class="addbutton">Ver Libros</button>
    </form>
	
<%@ include file = "footer.jsp" %>
</body>
</html>