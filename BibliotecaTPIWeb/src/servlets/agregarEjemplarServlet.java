package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.Libro;
import logic.LibroController;

@WebServlet("/agregarEjemplarServlet")
public class agregarEjemplarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public agregarEjemplarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro = new LibroController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Libro lib = new Libro();
		lib.setIdLibro(ID);
		Ejemplar ej = ctrlLibro.addEjemplar(lib);
		request.setAttribute("nuevoEjemplar", ej);
		request.getRequestDispatcher("listarEjemplaresServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
}}
