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

/**
 * Servlet implementation class buscarPersonaServlet
 */
@WebServlet("/buscarPersonaServlet")
public class buscarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		String nombuscar=(request.getParameter("txtbuscar"));
		//LinkedList<PoliticaPrestamo> politicas=ctrlPP.ppGetByBusqueda(nombuscar);
		if(nombuscar != null) {
			int id= Integer.parseInt(nombuscar);
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
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		String nombuscar=(request.getParameter("txtbuscar"));
		//LinkedList<PoliticaPrestamo> politicas=ctrlPP.ppGetByBusqueda(nombuscar);
		if(nombuscar != null) {
			int id= Integer.parseInt(nombuscar);
			Persona p = new Persona();
			p.setIdPersona(id);
			Persona persona = new Persona();
			persona=ctrlPers.getByIdPersona(p);
			LinkedList<Persona> personas= new LinkedList<Persona>();
			personas.add(persona);
			if (persona == null) {
				LinkedList<Persona> person = ctrlPers.getAllPersonas();
				request.setAttribute("listaPersonas", person);
				request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
			
			}
			request.setAttribute("listaPersonas", personas);
			request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		}
		else { 
			LinkedList<Persona> personas = ctrlPers.getAllPersonas();	
			request.setAttribute("listaPersonas", personas);
			request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		}
	}

}
