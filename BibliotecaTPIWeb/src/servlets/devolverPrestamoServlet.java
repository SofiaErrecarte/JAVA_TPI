package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.Prestamo;
import logic.LibroController;
import logic.PrestamoController;


@WebServlet("/devolverPrestamoServlet")
public class devolverPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public devolverPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP= new PrestamoController();
		LibroController ctrlL = new LibroController();
		Prestamo p = new Prestamo();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		p.setIdPrestamo(ID);
		ctrlP.cerrarPrestamo(p);
		ctrlP.actualizarLP(p);
		ctrlL.setAllDisponibles(p);
		p.setEstado("Cerrado");
		request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
