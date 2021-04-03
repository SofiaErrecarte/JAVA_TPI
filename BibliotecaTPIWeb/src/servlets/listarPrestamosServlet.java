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


@WebServlet("/listarPrestamosServlet")
public class listarPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarPrestamosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrestamoController ctrlP = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		request.setAttribute("listaPrestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		request.setAttribute("listaPrestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}

}
