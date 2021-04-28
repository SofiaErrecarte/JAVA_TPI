package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
import entities.Prestamo;
import logic.PersonaController;
import logic.PrestamoController;

/**
 * Servlet implementation class listarPrestamoPersonaServlet
 */
@WebServlet("/listarPrestamoPersonaServlet")
public class listarPrestamoPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarPrestamoPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
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
							request.setAttribute("advertencia", "Tiene pr�stamos a vencer en los pr�ximos d�as. (001)");
						}
						if(fechaHoy.after(p.getFechaADevoler())) {
						request.setAttribute("advertencia", "Tiene pr�stamos vencidos.(001)");
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
				
					request.setAttribute("PersonaDue�a", pnew);
					request.setAttribute("listaPrestamos", prestamos);
					request.setAttribute("listaPrestamosPersonas", prestamos_personas);
					request.setAttribute("Persona", "prestamospersona");
					request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
			}
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
			}
	}


