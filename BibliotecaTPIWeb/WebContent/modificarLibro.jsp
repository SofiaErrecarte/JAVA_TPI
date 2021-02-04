<%@page import="java.util.LinkedList"%>
<%@page import="entities.Libro"%>
<%@page import="logic.LibroController"%>
<%@page import="entities.Proveedor"%>  
<%@page import="logic.ProveedorController"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
ProveedorController ctrlProv = new ProveedorController();
LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar libro</title>


</head>
<body>
 <% //Libro lib = (Libro)request.getAttribute("libroAEditar");
 	LinkedList<Libro> ll = (LinkedList<Libro>)request.getAttribute("listaLibros");
    Libro lib=(Libro)request.getAttribute("libroAEditar");
     //ArrayList < Opcion > opciones = cc . getOpcionesByIdCaracteristica (c . getIdCaracteristica ());
     
 %>
<form action="modificarLibroServlet" method="post">
<label for="id"><b>ID Libro</b></label>
	<!-- Este me guarda el atributo id -->
    <input type="text" name="id" value=<%=lib.getIdLibro()%> hidden="true">
    <!--  Este me lo muestra deshabilitado para q se vea el numero -->
    <input type="text" name="idLibro" value=<%=lib.getIdLibro()%> disabled>
  <div class="container">
    <label for="uname"><b>Titulo</b></label>
    <input type="text" name="titulo" value=<%=lib.getTitulo()%> required>

    <label for="psw"><b>ISBN</b></label>
    <input type="text"  name="isbn" value=<%=lib.getIsbn()%> hidden="true">
    <input type="text"  name="isbnLibro" value=<%=lib.getIsbn()%> disabled>
    
    <label for="nroed"><b>Numero Edicion</b></label>
    <input type="text" name="nroedicion" value=<%=lib.getNroEdicion()%> required>

	<label for="cantd"><b>Cant Dias Prestamo</b></label>
    <input type="number" name="cantdiasprestamo" value=<%=lib.getCantDiasMaxPrestamo()%> required>
    
    <label for="gen"><b>Genero</b></label>
    <input type="text" name="genero" value=<%=lib.getGenero()%> required>
    
	<label for="prov"><b>Id Proveedor</b></label>
    <% if( proveedores != null) {%>
                            <select name="idProveedor" value=<%=lib.getIdProveedor()%> class="form-control">
                                <%  for(int i = 0; i < proveedores.size(); i++) {
                                    Proveedor p = (Proveedor)proveedores.get(i);
                                %>
                                <option value="<%= p.getIdProveedor() %>"><%= p.getRazonSocial()%></option>>
                                <% } %>
                            </select>
                            <% }else{ %> <td> No hay proveedores cargados. <a class="agreggatebutton"
									href="agregarProveedor.jsp">
										Añadir un nuevo proveedor</a></td></td> <%} %>
                        	<td> Su proveedor no se encuentra en la lista? <a class="agreggatebutton"
									href="agregarProveedor.jsp">
										Añadir un nuevo proveedor</a></td>
    
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