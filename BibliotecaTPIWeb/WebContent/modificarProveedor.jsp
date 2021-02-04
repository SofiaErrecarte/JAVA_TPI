<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.Proveedor"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Proveedor</title>
<% //Libro lib = (Libro)request.getAttribute("libroAEditar");
Proveedor prov=(Proveedor)request.getAttribute("proveedorAEditar"); %>
</head>
<body>
<form action="modificarProveedorServlet" method="post">
<label for="id"><b>ID Proveedor</b></label>
   <!-- Este me guarda el atributo id -->
    <input type="text" name="id" value=<%=prov.getIdProveedor()%> hidden="true">
    <!--  Este me lo muestra deshabilitado para q se vea el numero -->
    <input type="text" name="idLibro" value=<%=prov.getIdProveedor()%> disabled>
  <div class="container">
    <label for="cuit"><b>CUIT</b></label>
    <input type="text" name="cuit" value=<%=prov.getCUIT()%> required>

    <label for="razonsoc"><b>Razón Social</b></label>
    <input type="text" name="razonSocial" value=<%=prov.getRazonSocial()%> required>

	<label for="tel"><b>Telefono</b></label>
    <input type="text" name="telefono" value=<%=prov.getTelefono() %> required>
    
    <label for="email"><b>E-Mail</b></label>
    <input type="text" name="mail" value=<%=prov.getMail() %> required>

	<label for="dir"><b>Dirección</b></label>
    <input type="text" name="direccion" value=<%=prov.getDireccion() %>required>

    <button type="submit">Modificar</button>
    
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