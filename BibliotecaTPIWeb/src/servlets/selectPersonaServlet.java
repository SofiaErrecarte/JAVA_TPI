package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Persona;
import logic.LibroController;
import logic.PersonaController;

/**
 * Servlet implementation class selectPersonaServlet
 */
@WebServlet("/selectPersonaServlet")
public class selectPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		LinkedList<Persona> personas = ctrlPers.getByApellidoA();
		String op = request.getParameter("opcion");
		if (op.equalsIgnoreCase("apeA")) {
		personas= ctrlPers.getByApellidoA();
		}
		else if (op.equalsIgnoreCase("apeZ")) {
			personas=ctrlPers.getByApellidoZ();
		}
		else if (op.equalsIgnoreCase("nomA")) {
			personas=ctrlPers.getByNombreA();
		}
		else if (op.equalsIgnoreCase("nomB")) {
			personas=ctrlPers.getByNombreZ();
		
		request.setAttribute("listaPersonas", personas);
		request.getRequestDispatcher("listarPersonas.jsp").forward(request, response);
		
	}
	}
}
