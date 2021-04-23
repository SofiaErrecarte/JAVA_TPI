package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Persona;
import entities.Prestamo;
import logic.LibroController;
import logic.PersonaController;
import logic.PrestamoController;

@WebServlet("/darDeBajaPersonaServlet")
public class darDeBajaPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public darDeBajaPersonaServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers= new PersonaController();
		Persona p = new Persona();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		p.setIdPersona(ID);
		p.setActivo(false);
		MyResult res = ctrlPers.setEstado(p, false);
		request.setAttribute("result", res);
		request.getRequestDispatcher("listarPersonasServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
