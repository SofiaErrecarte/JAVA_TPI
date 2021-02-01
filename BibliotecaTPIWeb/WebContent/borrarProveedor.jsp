<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Proveedor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar Proveedor</title>
</head>
<body>
<form action="borrarProveedorServlet" method="post">

  	<div class="container">
  	
    <label for="id"><b>Ingrese el ID del proveedor que desea borrar:</b></label>
    <input type="number"  name="id">

	
    <button type="submit">Borrar proveedor</button>
    
  </div>

	
  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
  
  
</form>
	<form  action="listarProveedorServlet" method="post">
	<button type="submit">Ver Proveedores</button>
    </form>
	

</body>
</html>