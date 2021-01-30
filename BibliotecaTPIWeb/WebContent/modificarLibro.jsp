<%@page import="java.util.LinkedList"%>
<%@page import="entities.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar libro</title>
  <% //Libro lib = (Libro)request.getAttribute("libroAEditar");
   LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
%>

</head>
<body>

<form action="modificarLibroServlet" method="post">
<label for="id"><b>ID Libro</b></label>
    <input type="text" name="id" required>
  <div class="container">
    <label for="uname"><b>Titulo</b></label>
    <input type="text" name="titulo" required>

    <label for="psw"><b>ISBN</b></label>
    <input type="text"  name="isbn"required>
    
    <label for="nroed"><b>Numero Edicion</b></label>
    <input type="text" name="nroedicion"required>

	<label for="cantd"><b>Cant Dias Prestamo</b></label>
    <input type="number" name="cantdiasprestamo"required>
    
    <label for="gen"><b>Genero</b></label>
    <input type="text" name="genero"required>
    
	<label for="idProveedor"><b>Id Proveedor</b></label>
    <input type="number" name="idProveedor"required>
    
    <button type="submit">Modificar</button>
    
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
 
</form> 
<form  action="listarLibroServlet" method="post">
	<button type="submit">Ver Libros</button>
    </form>      
                   
</body>
</html>