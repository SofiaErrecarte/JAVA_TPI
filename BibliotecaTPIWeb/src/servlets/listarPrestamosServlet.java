package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Persona;
import entities.Prestamo;
import logic.LibroController;
import logic.PersonaController;
import logic.PrestamoController;


@WebServlet("/listarPrestamosServlet")
public class listarPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarPrestamosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		PrestamoController ctrlP = new PrestamoController();
		int idPers = Integer.parseInt((request.getParameter("id")));
		Persona pers = new Persona();
		pers.setIdPersona(idPers);
		Persona pnew =ctrlPers.getByIdPersona(pers);
		LinkedList<Prestamo> prestamos_personas = ctrlP.getByPersona(pnew);
		
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		Date fechaHoy = new Date();
		for(Prestamo p : prestamos) {
			if(p.getEstado().equals("Abierto")) {
				int dias = (int) ((p.getFechaADevoler().getTime() - fechaHoy.getTime())/86400000);
				if(dias<6 && dias>=0) {
					request.setAttribute("advertencia", "Tiene préstamos a vencer en los próximos días. (001)");
				}
				if(fechaHoy.after(p.getFechaADevoler())) {
				request.setAttribute("advertencia", "Tiene préstamos vencidos.(001)");
				}
			}
			
		}
		
		request.setAttribute("listaPrestamos", prestamos);
		request.setAttribute("listaPrestamosPersonas", prestamos_personas);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		Date fechaHoy = new Date();
		for(Prestamo p : prestamos) {
			if(p.getEstado().equals("Abierto")) {
				int dias = (int) ((p.getFechaADevoler().getTime() - fechaHoy.getTime())/86400000);
				if(dias<5 && dias>=0) {
					request.setAttribute("advertencia", "Tiene préstamos a vencer en los próximos días.(002)");
				}
				if(fechaHoy.after(p.getFechaADevoler())) {
				request.setAttribute("advertencia", "Tiene préstamos vencidos.(002)");
				}
			}
			
		}
		request.setAttribute("listaPrestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
	}

}
