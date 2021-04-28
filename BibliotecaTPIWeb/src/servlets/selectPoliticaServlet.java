package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PoliticaPrestamo;
import entities.Prestamo;
import logic.PoliticaPrestamoController;
import logic.PrestamoController;

/**
 * Servlet implementation class selectPoliticaServlet
 */
@WebServlet("/selectPoliticaServlet")
public class selectPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectPoliticaServlet() {
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
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
		LinkedList<PoliticaPrestamo> politicas = ctrlPP.getByIDMinimo();
		String op = request.getParameter("opcion");
		if (op.equalsIgnoreCase("idmenor")) {
			politicas= ctrlPP.getByIDMinimo();
		}
		else if (op.equalsIgnoreCase("idmayor")) {
			politicas=ctrlPP.getByIDMayor();
		}
		else if (op.equalsIgnoreCase("fechamenor")) {
			politicas=ctrlPP.getByFechaMinimo();
		}
		else if (op.equalsIgnoreCase("fechamenor")) {
			politicas=ctrlPP.getByFechaMayor();
		}
		request.setAttribute("listapoliticas", politicas);
		request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
		
	}

}
