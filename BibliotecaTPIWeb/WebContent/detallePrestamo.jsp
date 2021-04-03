<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
<%@page import="entities.Persona"%>  
<%@page import="logic.PersonaController"%>  
<%@page import="entities.Libro"%>  
<%@page import="logic.LibroController"%>
<%PersonaController ctrlPer = new PersonaController();
LinkedList<Persona> personas = ctrlPer.getAllPersonas(); 
LibroController ctrlL = new LibroController();
LinkedList<Libro> libros = ctrlL.getAllLibros();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table" class="text-center" id="tablaprueba">
                                    <thead>
					        <tr>
					          <th class="text-center">ID Libro</th>
					          <th class="text-center">ID Ejemplar</th>
					          <th class="text-center">Fecha Devolucion</th>
					          			        </tr>
					      </thead>
					      <tr>
					     <td>
					     <% if( libros != null) {%>
				                            <select name="idlibro" id="idlibro" class="form-control input-md">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input type="text" name="txtidejemplar" id="txtidejemplar" class="form-control input-md" ></td>
						 <td><input class="form-control" type="date" id="txtfecha" name="txtfecha" value="2020-07-22" min="2000-01-01" max="2025-12-31" style="display=block"></td>    
					      </tr>
					      
					      <tr>					    
					     <td>
					     <% if( libros != null) {%>
				                            <select class="form-control" name="txtlibro1">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
						
					     <td><input class="form-control" type="text" name="txtidejemplar1"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha1" ></td> 
					      </tr>
					      
					      <tr>
					         <td>
					     <% if( libros != null) {%>
				                            <select class="form-control"  name="txtlibro2">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input class="form-control" type="text" name="txtidejemplar2"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha2" ></td> 
					      </tr>
					      
					      <tr>
					         <td>
					     <% if( libros != null) {%>
				                            <select class="form-control"  name="txtlibro3">
				                                <%  for(int i = 0; i < libros.size(); i++) {
				                                   Libro l = (Libro)libros.get(i);
				                                %>
				                                <option value="<%= l.getIdLibro() %>"><%= l.getTitulo()%></option>
				                                <% } %>
				                            </select>
				                            <% }else{ %> <td> No hay libros cargados. <a class="agreggatebutton"
													href="agregarProveedor.jsp"> <!-- ver esto -->
														Añadir un nuevo cliente</a></td></td> <%} %>
						</td>
					     <td><input class="form-control" type="text" name="txtidejemplar3"></td>
					     <td><input class="form-control" placeholder = "yyyy-mm-dd" type="text" name="txtfecha3" ></td> 
					        </tr>
					      
					    </table>
					
</body>
</html>