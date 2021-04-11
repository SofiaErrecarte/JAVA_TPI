<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Proveedor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar Proveedor</title>
<style>
html, body{
  font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body style = "font-family:arial; size=3">
<form action="borrarProveedorServlet" method="post" style = "font-family:arial; size=3">

  	<div class="container">
  	
    <label for="id"><b>Ingrese el ID del proveedor que desea borrar:</b></label>
    <input type="number" name="id">

	
    <button type="submit" clas="addbutton">Borrar proveedor</button>
    
  </div>

	
  <div class="container">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" class="addbutton" name="Cancelar" value="Cancelar">
  </div>
  
  
</form>
	<form  action="listarProveedorServlet" method="post" style = "font-family:arial; size=3">
	<button type="submit" class="addbutton">Ver Proveedores</button>
    </form>
	

</body>
</html>