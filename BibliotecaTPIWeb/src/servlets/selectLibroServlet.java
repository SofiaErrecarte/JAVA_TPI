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
 * Servlet implementation class selectLibroServlet
 */
@WebServlet("/selectLibroServlet")
public class selectLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectLibroServlet() {
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
		LibroController ctrlLib = new LibroController();
		LinkedList<Libro> libros = ctrlLib.getByIDMinimo();
		String op = request.getParameter("opcion");
		
		if (op.equalsIgnoreCase("idmenor")) {
		libros= ctrlLib.getByIDMinimo();
		}
		else if (op.equalsIgnoreCase("idmayor")) {
			libros=ctrlLib.getByIDMayor();
		}
		else if (op.equalsIgnoreCase("tituloA")) {
			libros=ctrlLib.getByTituloA();
		}
		else if (op.equalsIgnoreCase("tituloZ")) {
			libros=ctrlLib.getByTituloZ();
		}
		else if (op.equalsIgnoreCase("proveedores")) {
			libros=ctrlLib.getByProveedores();}
		
		request.setAttribute("listaLibros", libros);
		request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}

	

}
