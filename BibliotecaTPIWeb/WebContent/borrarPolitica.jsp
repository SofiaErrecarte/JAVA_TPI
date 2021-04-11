<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar Politica de prestamo </title>
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body style = "font-family:arial; size=3">
<form action="borrarPoliticaServlet" method="post"  style = "font-family:arial; size=3">

  	<div class="container">
  	
    <label for="id"><b>Ingrese el ID de la politica de prestamo que desea borrar:</b></label>
    <input type="number"  name="idPolitica">

    <button type="submit" class="deletebutton">Borrar Politica</button>
    
  </div>

	
  <div class="container">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" class="addbutton" name="Cancelar" value="Cancelar">
  </div>
  
  
</form>
	<form  action="listarPoliticaServlet" method="post" style = "font-family:arial; size=3">
	<button type="submit" class="addbutton">Ver Politcas</button>
    </form>
<%@ include file = "footer.jsp" %>
</body>
</html>