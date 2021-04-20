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

/**
 * Servlet implementation class buscarLibroServlet
 */
@WebServlet("/buscarLibroServlet")
public class buscarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		LibroController ctrlLib = new LibroController();
		String nombuscar=(request.getParameter("txtbuscar"));
		//LinkedList<PoliticaPrestamo> politicas=ctrlPP.ppGetByBusqueda(nombuscar);
		if(nombuscar != null) {
			LinkedList<Libro> libros= new LinkedList<Libro>();
			libros = ctrlLib.getByDesc(nombuscar);
			if (libros == null) {
				LinkedList<Libro> librs = ctrlLib.getAllLibros();	
				request.setAttribute("listaLibros", librs);
				request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			}
			request.setAttribute("listaLibros", libros);
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}
		else{ 
			LinkedList<Libro> libros = ctrlLib.getAllLibros();	
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