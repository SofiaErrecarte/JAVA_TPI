package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Persona;
import logic.PersonaController;

/**
 * Servlet implementation class darDeAltaPersonaServlet
 */
@WebServlet("/darDeAltaPersonaServlet")
public class darDeAltaPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public darDeAltaPersonaServlet() {
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
		p.setActivo(true);
		MyResult res = ctrlPers.setEstado(p, true);
		request.setAttribute("result", res);
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
