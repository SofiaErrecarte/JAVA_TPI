package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Proveedor;
import logic.ProveedorController;

/**
 * Servlet implementation class modificarProveedorServlet
 */
@WebServlet("/modificarProveedorServlet")
public class modificarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarProveedorServlet() {
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
		ProveedorController ctrlProv = new ProveedorController();
		Proveedor prov = new Proveedor();
		prov.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
		
		String razonSocial = request.getParameter("razonSocial");
		String CUIT = request.getParameter("cuit");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		String direccion = request.getParameter("direccion");
		
		prov.setCUIT(CUIT);
		prov.setRazonSocial(razonSocial);
		prov.setMail(mail);
		prov.setTelefono(telefono);
		prov.setDireccion(direccion);
		
		ctrlProv.editProveedor(prov);
		
		request.getRequestDispatcher("listarProveedorServlet").forward(request, response);
		
	}

}
