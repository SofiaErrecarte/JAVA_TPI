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
import entities.Proveedor;
import logic.PersonaController;
import logic.ProveedorController;

/**
 * Servlet implementation class listarPersonasServlet
 */
@WebServlet("/listarPersonasServlet")
public class listarPersonasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarPersonasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		LinkedList<Persona> personas = ctrlPers.getAllPersonas();
		int var = 1;
		for(Persona p : personas) {
			if(p.getIdPersona()==0) {
				var=0;
			}
		}
		if(var!=0) {
		request.setAttribute("listaPersonas", personas);
		request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		}
		else {
			request.setAttribute("advertencia", "No existen usuarios registrados.");
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
