package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Ejemplar;
import entities.Libro;
import logic.LibroController;

/**
 * Servlet implementation class borrarLibroServlet
 */
@WebServlet("/borrarLibroServlet")
public class borrarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		LibroController ctrlLibro =  new LibroController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Libro lib = new Libro(); 
		lib.setIdLibro(ID);
		Libro l = ctrlLibro.getByIdLibro(lib);
		MyResult res = ctrlLibro.deleteLibro(l);
		LinkedList<Ejemplar> ej = new LinkedList<>();
		ej= ctrlLibro.getEjByIdLibro(l);
		for(Ejemplar e : ej) {
			ctrlLibro.deleteEjemplar(e);
		}
		request.setAttribute("result", res);
		request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
		request.getRequestDispatcher ("listaLibros.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * // TODO Auto-generated method stub LibroController ctrlLibro = new
		 * LibroController(); //LinkedList<Libro> libros2 = ctrlLibro.getAllLibros();
		 * 
		 * Libro lib = new Libro();
		 * lib.setIdLibro(Integer.parseInt(request.getParameter("idlibro")));
		 * LinkedList<Ejemplar> ej = new LinkedList<>(); ej=
		 * ctrlLibro.getEjByIdLibro(lib); for(Ejemplar e : ej) {
		 * ctrlLibro.deleteEjemplar(e); } ctrlLibro.deleteLibro(lib);
		 * //request.setAttribute("listaLibros2", libros2);
		 * 
		 * 
		 * 
		 * request.getRequestDispatcher("listarLibroServlet").forward(request,
		 * response);
		 */
		doGet(request, response);
	}
}
