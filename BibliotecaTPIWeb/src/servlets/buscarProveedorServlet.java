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

/**
 * Servlet implementation class buscarProveedorServlet
 */
@WebServlet("/buscarProveedorServlet")
public class buscarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscarProveedorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ProveedorController ctrlProv = new ProveedorController();
		String nombuscar=(request.getParameter("txtbuscar"));
		if(nombuscar != null) {
			LinkedList<Proveedor> proveedores= new LinkedList<Proveedor>();
			proveedores = ctrlProv.getByDesc(nombuscar);
			if (proveedores == null) {
				LinkedList<Proveedor> librs = ctrlProv.getAllProveedores();	
				request.setAttribute("listaProveedores", librs);
				request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
			}
			request.setAttribute("listaProveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
		else { 
			LinkedList<Proveedor> proveedores = ctrlProv.getAllProveedores();	
			request.setAttribute("listaProveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
