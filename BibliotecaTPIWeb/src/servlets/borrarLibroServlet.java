package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Ejemplar;
import entities.Libro;
import logic.LibroController;

/**
 * Servlet implementation class borrarLibroServlet
 */
@WebServlet("/borrarLibroServlet")
public class borrarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		LibroController ctrlLibro =  new LibroController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Libro lib = new Libro(); 
		lib.setIdLibro(ID);
		Libro l = ctrlLibro.getByIdLibro(lib);
		LinkedList<Ejemplar> ej = new LinkedList<>();
		ej= ctrlLibro.getEjByIdLibro(l);
		
		//intento eliminar cada ejemplar
		MyResult res1 = null;
		for(Ejemplar e : ej) {
			res1 = ctrlLibro.deleteEjemplar(e);
		}
		
	
		//si puedo eliminar ejemplares, intento eliminar libro
		if (res1.getResult().equals(MyResult.results.OK)) {
			MyResult res = ctrlLibro.deleteLibro(l);
			if (res.getResult().equals(MyResult.results.Err)) {
				request.setAttribute("result", res);
				request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
				request.getRequestDispatcher ("listaLibros.jsp").forward(request, response);
			} else {
			
			
			request.setAttribute("result", res);
			request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
			request.getRequestDispatcher ("listaLibros.jsp").forward(request, response);
			}
		} else {
			res1.setErr_message("Existe al menos un ejemplar de este libro asignado a un préstamo.");
			request.setAttribute("result", res1);
			request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
			request.getRequestDispatcher ("listaLibros.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
