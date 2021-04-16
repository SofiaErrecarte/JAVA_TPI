package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PoliticaPrestamo;
import entities.Proveedor;
import logic.PoliticaPrestamoController;
import logic.ProveedorController;

/**
 * Servlet implementation class selectProveedorServlet
 */
@WebServlet("/selectProveedorServlet")
public class selectProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectProveedorServlet() {
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
		ProveedorController ctrlProv = new ProveedorController();
		LinkedList<Proveedor> proveedores = ctrlProv.getByIDMinimo();
		String op = request.getParameter("opcion");
		if (op.equalsIgnoreCase("idmenor")) {
			proveedores= ctrlProv.getByIDMinimo();
		}
		else if (op.equalsIgnoreCase("idmayor")) {
			proveedores=ctrlProv.getByIDMayor();
		}
		request.setAttribute("listaProveedores", proveedores);
		request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		
	}

}
