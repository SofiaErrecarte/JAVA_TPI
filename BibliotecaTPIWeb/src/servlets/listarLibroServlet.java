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
import entities.Proveedor;
import logic.LibroController;
import logic.PoliticaPrestamoController;
import logic.ProveedorController;

@WebServlet("/listarLibroServlet")
public class listarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarLibroServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro = new LibroController();
		LinkedList<Libro> libros = ctrlLibro.getAllLibros();
		LinkedList<Integer> cantidades = new LinkedList<Integer>();
		int var=1;
		for(Libro l : libros) {
			if(l.getIdLibro()==0) {
				var=0;
			}
		}
		if(var!=0) {
		for (Libro l : libros) {
			int cant = ctrlLibro.cantEjDisponibles(l);
			cantidades.addLast(cant);
		}
		
		request.setAttribute("listaLibros", libros);
		request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
	}else {
		request.setAttribute("msjFiltro", "No hay libros registrados");
		request.setAttribute("listaLibros", libros);
		request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
	}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}

