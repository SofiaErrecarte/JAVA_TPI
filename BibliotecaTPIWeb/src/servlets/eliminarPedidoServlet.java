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
import entities.LineaPrestamo;
import entities.Prestamo;
import logic.LibroController;
import logic.LineaPrestamoController;
import logic.PrestamoController;

/**
 * Servlet implementation class eliminarPedidoServlet
 */
@WebServlet("/eliminarPedidoServlet")
public class eliminarPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrestamoController ctrlP = new PrestamoController();
		//LinkedList<Libro> libros2 = ctrlLibro.getAllLibros();
		LineaPrestamoController ctrlLP = new LineaPrestamoController();
		Prestamo p = new Prestamo(); 
		p.setIdPrestamo(Integer.parseInt(request.getParameter("id")));
		LinkedList<LineaPrestamo> lps = new LinkedList<>();
		lps= ctrlP.getLPByPrestamo(p);
		for(LineaPrestamo l: lps) {
			ctrlLP.deleteLineaPrestamo(l);
		}
		ctrlP.deletePrestamo(p);
		//request.setAttribute("listaLibros2", libros2);
		
		
		
		request.getRequestDispatcher("listarLibroServlet").forward(request, response);
	}

}
