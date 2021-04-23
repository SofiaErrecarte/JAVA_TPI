package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Proveedor;
import logic.ProveedorController;


@WebServlet("/buscarProveedorServlet")
public class buscarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public buscarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProveedorController ctrlProv = new ProveedorController();
		String nombuscar = null;
		nombuscar=(request.getParameter("txtbuscar"));
		if(nombuscar != null) {
			LinkedList<Proveedor> proveedores= new LinkedList<Proveedor>();
			proveedores = ctrlProv.getByDesc(nombuscar);
			if (proveedores.isEmpty()) {
				request.setAttribute("msjFiltro", "No se han encontrado resultados para su búsqueda.");
				request.setAttribute("listaProveedores", proveedores);
				request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
			} else {
			request.setAttribute("listaProveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}}
		else { 
			LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();	
			request.setAttribute("msjFiltro", "Debe ingresar un valor válido.");
			request.setAttribute("listaProveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
