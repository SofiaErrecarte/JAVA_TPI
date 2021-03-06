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
import entities.PoliticaPrestamo;
import entities.Prestamo;
import logic.LibroController;
import logic.PersonaController;
import logic.PoliticaPrestamoController;
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
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
		int idPers = Integer.parseInt((request.getParameter("id")));
		Persona pers = new Persona();
		pers.setIdPersona(idPers);
		Persona pnew =ctrlPers.getByIdPersona(pers);
		LinkedList<Prestamo> prestamos_personas = ctrlP.getByPersona(pnew);
		LinkedList<Prestamo> prestamos = ctrlP.getAllPrestamos();
		LinkedList<PoliticaPrestamo> politicas = ctrlPP.ppGetAll();	
		if(politicas.isEmpty()) {
			request.setAttribute("errorSinPolitica", "Debe cargar primero al menos una pol�tica.");
		}
		Date fechaHoy = new Date();
		int var = 1;
		for(Prestamo p : prestamos) {
			if(p.getIdPrestamo()==0) {
				var=0;
			}
		}
		if(var!=0) {
		for(Prestamo p : prestamos) {
			if(p.getEstado().equals("Abierto")) {
				int dias = (int) ((p.getFechaADevoler().getTime() - fechaHoy.getTime())/86400000);
				if(dias<6 && dias>=0) {
					request.setAttribute("advertencia", "Tiene pr�stamos a vencer en los pr�ximos d�as.");
				}
				if(fechaHoy.after(p.getFechaADevoler())) {
				request.setAttribute("advertencia", "Tiene pr�stamos vencidos.");
				}
			}
			
		}
		for(Prestamo p : prestamos_personas) {
			if(p.getEstado().equals("Abierto")) {
				int dias = (int) ((p.getFechaADevoler().getTime() - fechaHoy.getTime())/86400000);
				if(dias<6 && dias>=0) {
					request.setAttribute("advertencia2", "Tiene pr�stamos a vencer en los pr�ximos d�as.");
				}
				if(fechaHoy.after(p.getFechaADevoler())) {
				request.setAttribute("advertencia2", "Tiene pr�stamos vencidos.");
				}
			}
			
		}
		}else {
			request.setAttribute("advertencia", "No tiene pr�stamos registrados");
			request.setAttribute("advertencia2", "No tiene pr�stamos registrados");
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
			request.setAttribute("advertencia", "Tiene pr�stamos a vencer en los pr�ximos d�as.");
			}
			if(fechaHoy.after(p.getFechaADevoler())) {
			request.setAttribute("advertencia", "Tiene pr�stamos vencidos.");
			}
			}}
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);

			}
	

}
