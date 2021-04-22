package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Prestamo;
import logic.PrestamoController;



/**
 * Servlet implementation class buscarPrestamoServlet
 */
@WebServlet("/buscarPrestamoServlet")
public class buscarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public buscarPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrestamoController ctrlP = new PrestamoController();
		String nombuscar=(request.getParameter("txtbuscar"));
		//LinkedList<PoliticaPrestamo> politicas=ctrlPP.ppGetByBusqueda(nombuscar);
		int id = 0;
		try {
			id= Integer.parseInt(nombuscar);
		}catch(NumberFormatException e) {
			id = 0;
		}
		if(id != 0) {			
			Prestamo p = new Prestamo();
			p.setIdPrestamo(id);
			Prestamo prestamo = new Prestamo();
			prestamo=ctrlP.getByIdPrestamo(p);
			LinkedList<Prestamo> prestamos= new LinkedList<Prestamo>();
			prestamos.add(prestamo);
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
			
		}
		
		  else { LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		  request.setAttribute("listaPrestamos", prestamos);
		  request.getRequestDispatcher("listarPrestamosServlet").forward(request,
		  response); }
		 
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
