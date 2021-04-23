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


@WebServlet("/buscarPrestamoServlet")
public class buscarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public buscarPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController();
		String nombuscar=(request.getParameter("txtbuscar"));
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
			if(prestamo.getIdPrestamo()==0) {
				request.setAttribute("msjFiltro", "No se han encontrado resultados para su búsqueda.");
				request.setAttribute("listaPrestamos", prestamos);
				request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
			} else {
			prestamos.add(prestamo);
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
			
		}}
		
		  else { 
			  LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
			  request.setAttribute("msjFiltro", "Debe ingresar un número válido.");
			  request.setAttribute("listaPrestamos", prestamos);
			  request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response); 
		  }
		 
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
