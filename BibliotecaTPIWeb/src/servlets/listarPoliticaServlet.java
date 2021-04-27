package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.*;
import logic.PoliticaPrestamoController;

/**
 * Servlet implementation class listarPoliticaServlet
 */
@WebServlet("/listarPoliticaServlet")
public class listarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarPoliticaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController(); 
		LinkedList<PoliticaPrestamo> politicas = ctrlPP.ppGetAll();
		int var = 1;
		for(PoliticaPrestamo p : politicas) {
			if(p.getIdPoliticaPrestamo()==0) {
				var=0;
			}
		}
		if(var!=0) {
		request.setAttribute("listapoliticas", politicas);
		request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
	}
		else {
			request.setAttribute("advertencia", "No existen políticas de prestamo registradas.");
			request.setAttribute("listapoliticas", politicas);
			request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
		
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
