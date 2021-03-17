package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PoliticaPrestamo;
import logic.PoliticaPrestamoController;

/**
 * Servlet implementation class borrarPoliticaServlet
 */
@WebServlet("/borrarPoliticaServlet")
public class borrarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarPoliticaServlet() {
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
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		PoliticaPrestamo pp = new PoliticaPrestamo();
		pp.setIdPoliticaPrestamo(ID);
		PoliticaPrestamo p = ctrlPP.getByIdPolitica(pp);
		ctrlPP.deletePoliticaPrestamo(p);
		request.setAttribute("listapoliticas",ctrlPP.ppGetAll());
		request.getRequestDispatcher ("listaPoliticas.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
		PoliticaPrestamo pp = new PoliticaPrestamo();
		pp.setIdPoliticaPrestamo(Integer.parseInt(request.getParameter("id")));
		ctrlPP.deletePoliticaPrestamo(pp);
		
		request.getRequestDispatcher("listarPoliticaServlet").forward(request, response);
		
		//doGet(request, response);
	}

}
