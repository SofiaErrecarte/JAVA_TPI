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
 * Servlet implementation class borrarEjemplarServlet
 */
@WebServlet("/borrarEjemplarServlet")
public class borrarEjemplarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public borrarEjemplarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro = new LibroController();
		int ID = Integer.parseInt(request.getParameter("id"));
		Ejemplar ej = new Ejemplar();
		ej.setIdEjemplar(ID);
		Ejemplar e = ctrlLibro.getByIdEjemplar(ej);
		Libro l = new Libro();
		l.setIdLibro(e.getIdLibro());
		Libro lib= ctrlLibro.getByIdLibro(l);
		MyResult res = ctrlLibro.deleteEjemplar(e);
		request.setAttribute("result", res);
		LinkedList<Ejemplar> ejemplares = ctrlLibro.getEjByIdLibro(lib);
		request.setAttribute("listaEjemplares", ejemplares);
		request.setAttribute("libro", lib);
		request.getRequestDispatcher("listaEjemplares.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
