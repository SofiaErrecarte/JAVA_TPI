package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Prestamo;
import logic.LibroController;
import logic.PrestamoController;

/**
 * Servlet implementation class selectPrestamoServlet
 */
@WebServlet("/selectPrestamoServlet")
public class selectPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectPrestamoServlet() {
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
		PrestamoController ctrlPres = new PrestamoController();
		LinkedList<Prestamo> prestamos = ctrlPres.getByIDMinimo();
		String op = request.getParameter("opcion");
		if (op=="idmenor") {
		prestamos= ctrlPres.getByIDMinimo();
		}
		else if (op=="idmayor") {
			prestamos=ctrlPres.getByIDMayor();
		}
		else if (op=="fechamenor") {
			prestamos=ctrlPres.getByFechaMinimo();
		}
		else if (op=="fechamayor") {
			prestamos=ctrlPres.getByFechaMayor();
		}
		request.setAttribute("listaPrestamos", prestamos);
		request.getRequestDispatcher("listaPrestamos.jsp").forward(request, response);
		}

}
