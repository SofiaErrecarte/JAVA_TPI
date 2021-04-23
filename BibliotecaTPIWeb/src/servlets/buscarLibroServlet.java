package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.PoliticaPrestamo;
import logic.LibroController;
import logic.PoliticaPrestamoController;

@WebServlet("/buscarLibroServlet")
public class buscarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public buscarLibroServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLib = new LibroController();
		String nombuscar=(request.getParameter("txtbuscar"));
		if(nombuscar != null) {
			LinkedList<Libro> libros= new LinkedList<Libro>();
			libros = ctrlLib.getByDesc(nombuscar);
			if (libros.isEmpty()) {
				request.setAttribute("msjFiltro", "No se han encontrado resultados para su búsqueda.");	
				request.setAttribute("listaLibros", libros);
				request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			}else {
			request.setAttribute("listaLibros", libros);
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}}
		else{ 
			LinkedList<Libro> libros = ctrlLib.getAllLibros();	
			request.setAttribute("msjFiltro", "Debe ingresar un valor válido.");
			request.setAttribute("listaLibros", libros);
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}