
package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.MyResult;
import logic.LibroController;

/**
 * Servlet implementation class agregarLibroServlet
 */
@WebServlet("/agregarLibroServlet")
public class agregarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public agregarLibroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro = new LibroController();
		Libro l = new Libro();
		
		String titulo = request.getParameter("titulo");
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		int nroedicion = Integer.parseInt(request.getParameter("nroedicion"));
		int cantdias = Integer.parseInt(request.getParameter("cantdiasprestamo"));
		String genero = request.getParameter("genero");
		l.setIsbn(isbn);
		
		l=ctrlLibro.getByIsbnLibro(l);
		//verificamos que el ISBN no esté cargado
		if(l==null) {
			Libro lib = new Libro();
			lib.setTitulo(titulo);
			lib.setIsbn(isbn);
			lib.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
			lib.setNroEdicion(nroedicion);
			lib.setGenero(genero);
			lib.setCantDiasMaxPrestamo(cantdias);
			
			MyResult res = ctrlLibro.createLibro(lib);
			if (res.getResult().equals(MyResult.results.Err)) {
				request.setAttribute("result", res);
				request.getRequestDispatcher("agregarLibro.jsp").forward(request, response); 
		}else {
			request.setAttribute("result", res);
			request.setAttribute("nuevoLibro", lib);
			request.getRequestDispatcher("listarLibroServlet").forward(request, response);
		}
		}else {
			request.setAttribute("error", "El libro ingresado ya existe.");
			request.getRequestDispatcher("agregarLibro.jsp").forward(request, response); }
		
		
	}

}
