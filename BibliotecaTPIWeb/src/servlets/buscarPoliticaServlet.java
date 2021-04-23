package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.PoliticaPrestamo;
import logic.PoliticaPrestamoController;

/**
 * Servlet implementation class buscarPoliticaServlet
 */
@WebServlet("/buscarPoliticaServlet")
public class buscarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarPoliticaServlet() {
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
		String nombuscar=(request.getParameter("txtbuscar"));
		int id = 0;
		try {
			id= Integer.parseInt(nombuscar);
		}catch(NumberFormatException e) {
			id = 0;
		}
		if(id != 0) {
			PoliticaPrestamo p = new PoliticaPrestamo();
			p.setIdPoliticaPrestamo(id);
			PoliticaPrestamo politica = new PoliticaPrestamo();
			politica=ctrlPP.getByIdPolitica(p);
			LinkedList<PoliticaPrestamo> politicas= new LinkedList<PoliticaPrestamo>();
			if(politica.getIdPoliticaPrestamo()==0) {
				request.setAttribute("msjFiltro", "No se han encontrado resultados para su búsqueda.");
				request.setAttribute("listapoliticas", politicas);
				request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
			}else {
			politicas.add(politica);
			request.setAttribute("listapoliticas", politicas);
			request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
		}}
		else { 
			LinkedList<PoliticaPrestamo> politicas = ctrlPP.ppGetAll();	
			request.setAttribute("msjFiltro", "Debe ingresar un número válido.");
			request.setAttribute("listapoliticas", politicas);
			request.getRequestDispatcher("listaPoliticas.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);}
}