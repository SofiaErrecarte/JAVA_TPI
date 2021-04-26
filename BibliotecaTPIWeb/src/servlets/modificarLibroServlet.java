package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

import entities.Libro;
import entities.MyResult;
import logic.LibroController;


@WebServlet("/modificarLibroServlet")
public class modificarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public modificarLibroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro =  new  LibroController  ();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Libro lib = new Libro();
		lib.setIdLibro(ID);
		Libro l = ctrlLibro.getByIdLibro(lib);
		
		request.setAttribute ("libroAEditar",l);
		request.getRequestDispatcher("modificarLibro.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroController ctrlLibro = new LibroController();
		Libro lib=new Libro();
		lib.setIdLibro(Integer.parseInt(request.getParameter("id")));
		String titulo = request.getParameter("titulo");
		int isbn = 0;
		int nroedicion = 0;
		try {
			isbn = Integer.parseInt(request.getParameter("isbn"));
		}catch (NumberFormatException e) {
			isbn = 0;
		}
		try {
			nroedicion = Integer.parseInt(request.getParameter("nroedicion"));
		}catch (NumberFormatException e) {
			nroedicion = 0;
		}
		
		String genero = request.getParameter("genero");
		int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
		String autor = request.getParameter("autor");
		
		
		
		lib.setTitulo(titulo);
		lib.setIsbn(isbn);
		lib.setAutor(autor);
		lib.setNroEdicion(nroedicion);
		lib.setGenero(genero);
		lib.setIdProveedor(idProveedor);
		
		MyResult res = ctrlLibro.editLibro(lib);
		request.setAttribute("result", res);
		request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
		request.getRequestDispatcher("listarLibroServlet").forward(request, response);
		
	}

}