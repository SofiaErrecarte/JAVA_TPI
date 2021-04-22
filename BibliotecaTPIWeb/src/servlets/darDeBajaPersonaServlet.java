package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
import entities.Prestamo;
import logic.LibroController;
import logic.PersonaController;
import logic.PrestamoController;

/**
 * Servlet implementation class darDeBajaPersonaServlet
 */
@WebServlet("/darDeBajaPersonaServlet")
public class darDeBajaPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public darDeBajaPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers= new PersonaController();
		Persona p = new Persona();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		p.setIdPersona(ID);
		p.setEstado("De Baja");
		ctrlPers.setEstado(p, "De Baja");
		request.getRequestDispatcher("listarPersonasServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
