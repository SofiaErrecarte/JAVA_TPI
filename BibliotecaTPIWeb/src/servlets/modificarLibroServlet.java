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
		
		try {
			int isbn = Integer.parseInt(request.getParameter("isbn"));
			lib.setIsbn(isbn);
			} catch (NumberFormatException e) {
				request.setAttribute("msjFiltro", "Modificación incorrecta. Ingrese un número en ISBN por favor"); 
				request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
				request.getRequestDispatcher("listarLibroServlet").forward(request, response);
			}
		
		try {
			int nroedicion = Integer.parseInt(request.getParameter("nroedicion"));
			lib.setNroEdicion(nroedicion);
			} catch (NumberFormatException e) {
				request.setAttribute("msjFiltro", "Modificación incorrecta. Ingrese un número en Nro Edición por favor");
				request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
				request.getRequestDispatcher("listarLibroServlet").forward(request, response);

			}
				
		String genero = request.getParameter("genero");
		int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
		String autor = request.getParameter("autor");
				
		lib.setTitulo(titulo);
		lib.setAutor(autor);
		lib.setGenero(genero);
		lib.setIdProveedor(idProveedor);
		
		MyResult res = ctrlLibro.editLibro(lib);
		request.setAttribute("result", res);
		request.setAttribute("listaLibros",ctrlLibro.getAllLibros());
		request.getRequestDispatcher("listarLibroServlet").forward(request, response);
		
	}

}