package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Prestamo;
import logic.LibroController;
import logic.PrestamoController;

/**
 * Servlet implementation class listarPrestamosSevlet
 */
@WebServlet("/listarPrestamosSevlet")
public class listarPrestamosSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarPrestamosSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrestamoController ctrlP = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		request.setAttribute("listaprestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrestamoController ctrlP = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		request.setAttribute("listaprestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}

}
