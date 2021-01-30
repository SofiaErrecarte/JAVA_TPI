<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrar Politica de prestamo </title>
</head>
<body>
<form action="borrarPoliticaServlet" method="post">

  	<div class="container">
  	
    <label for="id"><b>Ingrese el ID de la politica de prestamo que desea borrar:</b></label>
    <input type="number"  name="idPolitica">

    <button type="submit">Borrar Politica</button>
    
  </div>

	
  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
  
  
</form>
	<form  action="listarPoliticaServlet" method="post">
	<button type="submit">Ver Politcas</button>
    </form>
</body>
</html>