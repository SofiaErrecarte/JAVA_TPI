package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Proveedor;
import logic.ProveedorController;

/**
 * Servlet implementation class borrarProveedorServlet
 */
@WebServlet("/borrarProveedorServlet")
public class borrarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarProveedorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProveedorController ctrlProv = new ProveedorController();
		int ID = Integer.parseInt(request.getParameter("id"));
		Proveedor prov = new Proveedor();
		prov.setIdProveedor(ID);
		Proveedor p = ctrlProv.getById(prov);
		MyResult res = ctrlProv.deleteProveedor(p);
		request.setAttribute("result", res);
		request.setAttribute("listaProveedores",ctrlProv.getAllProveedores());
		request.getRequestDispatcher ("listaProveedores.jsp").forward(request,response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ProveedorController ctrlProv = new ProveedorController(); Proveedor prov =
		 * new Proveedor();
		 * 
		 * prov.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
		 * MyResult res = ctrlProv.deleteProveedor(prov); if
		 * (res.getResult().equals(entities.MyResult.results.Err)) {
		 * request.setAttribute("result", res);
		 * request.getRequestDispatcher("listarProveedorServlet").forward(request,
		 * response); }
		 * request.getRequestDispatcher("listarProveedorServlet").forward(request,
		 * response);
		 */
		doGet(request, response);
	}

}
