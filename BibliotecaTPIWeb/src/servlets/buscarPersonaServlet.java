package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
import entities.PoliticaPrestamo;
import logic.PersonaController;
import logic.PoliticaPrestamoController;

@WebServlet("/buscarPersonaServlet")
public class buscarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public buscarPersonaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaController ctrlPers = new PersonaController();
		String nombuscar=(request.getParameter("txtbuscar"));
		//LinkedList<PoliticaPrestamo> politicas=ctrlPP.ppGetByBusqueda(nombuscar);
		int id = 0;
		try {
			id= Integer.parseInt(nombuscar);
		}catch(NumberFormatException e) {
			id = 0;
		}
		if(id != 0) {
			Persona p = new Persona();
			p.setIdPersona(id);
			Persona persona = new Persona();
			persona=ctrlPers.getByIdPersona(p);
			LinkedList<Persona> personas= new LinkedList<Persona>();
			personas.add(persona);
			request.setAttribute("listaPersonas", personas);
			request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		}
		else { 
			LinkedList<Persona> personas = ctrlPers.getAllPersonas();	
			request.setAttribute("listaPersonas", personas);
			request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
