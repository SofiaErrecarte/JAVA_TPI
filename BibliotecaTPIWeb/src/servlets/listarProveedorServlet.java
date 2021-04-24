package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.Proveedor;
import logic.ProveedorController;

/**
 * Servlet implementation class listarProveedorServlet
 */
@WebServlet("/listarProveedorServlet")
public class listarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public listarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProveedorController ctrlProv = new ProveedorController();
		LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();
		int var = 1;
		for(Proveedor p : proveedores) {
			if(p.getIdProveedor()==0) {
				var=0;
			}
		}
		if(var!=0) {
		request.setAttribute("listaProveedores", proveedores);
		request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
	}
		else {
			request.setAttribute("advertencia", "No existen proveedores registrados.");
			request.setAttribute("listaProveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
