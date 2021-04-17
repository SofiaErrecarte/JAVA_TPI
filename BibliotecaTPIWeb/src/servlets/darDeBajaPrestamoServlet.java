package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Prestamo;
import logic.LibroController;
import logic.PrestamoController;

/**
 * Servlet implementation class darDeBajaPrestamoServlet
 */
@WebServlet("/darDeBajaPrestamoServlet")
public class darDeBajaPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public darDeBajaPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrestamoController ctrlP= new PrestamoController();
		LibroController ctrlL = new LibroController();
		Prestamo p = new Prestamo();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		p.setIdPrestamo(ID);
		ctrlP.cerrarPrestamo(p);
		ctrlP.actualizarLP(p);
		ctrlL.setAllDisponibles(p);
		p.setEstado("De Baja");
		request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
