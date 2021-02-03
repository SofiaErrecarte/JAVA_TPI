<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Libro"%>  
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Proveedor"%>  
<%@page import="logic.ProveedorController"%>   
<%
ProveedorController ctrlProv = new ProveedorController();
LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Libro</title>
<% Libro lib = (Libro)session.getAttribute("nuevoLibro"); %>
</head>
<body>


	<form action="agregarLibroServlet" method="post">

  <div class="container">
    <label for="uname"><b>Titulo</b></label>
    <input type="text" name="titulo" required>

    <label for="psw"><b>ISBN</b></label>
    <input type="number"  name="isbn"required>
    <!-- <label for="start">Start date:</label>

	<input type="date" id="start" name="trip-start"
       value="2018-07-22"
       min="2018-01-01" max="2018-12-31"> -->

	<label for="psw"><b>Fecha Edicion</b></label>
    <input type="date" name="fechaedicion"required>
    
    <label for="nroed"><b>Numero Edicion</b></label>
    <input type="number" name="nroedicion"required>

	<label for="cantd"><b>Cant Dias Prestamo</b></label>
    <input type="number" name="cantdiasprestamo"required>
    
    <label for="gen"><b>Genero</b></label>
    <input type="text" name="genero"required>
    
    <label for="prov"><b>Id Proveedor</b></label>
    <% if( proveedores != null) {%>
                            <select name="idProveedor" class="form-control">
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
	
    <button type="submit">Agregar</button>
    
  </div>
	<!-- CARTEL -->
	<%if ((request.getAttribute("error"))!=null) { %>
		<p style="color:red"> <%=request.getAttribute("error")%> </p>		
	<% } %>
  
  <div class="container" style="background-color:#f1f1f1">
    <!--   <button type="button" class="cancelbtn">Cancel</button> -->
    <input type="button" onclick="history.back()" name="Cancelar" value="Cancelar">
  </div>
</form>
	<%if (lib!= null){%>
	<h4 style="color:green"> Libro agregado con éxito</h4>
	
<%} %>

</body>
</html>