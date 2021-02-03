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
 * Servlet implementation class agregarProveedorServlet
 */
@WebServlet("/agregarProveedorServlet")
public class agregarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarProveedorServlet() {
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
		Proveedor p = new Proveedor();
		
		String razonSocial = request.getParameter("razonSocial");
		String CUIT = request.getParameter("cuit");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		String direccion = request.getParameter("direccion");
		p.setCUIT(CUIT);
		
		p=ctrlProv.getByCUIT(p);
		
		if(p==null) {
			Proveedor prov = new Proveedor();
			prov.setRazonSocial(razonSocial);
			prov.setMail(mail);
			prov.setTelefono(telefono);
			prov.setDireccion(direccion);
			prov.setCUIT(CUIT);
			
			ctrlProv.createProveedor(prov);
			
			request.setAttribute("nuevoProveedor", prov);
			request.getRequestDispatcher("listarProveedorServlet").forward(request, response);
		}else {
			request.setAttribute("error", "El proveedor ingresado ya existe.");
			request.getRequestDispatcher("agregarProveedor.jsp").forward(request, response); }
			
		}
	
		
		
	}


