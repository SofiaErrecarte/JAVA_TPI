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

@WebServlet("/agregarProveedorServlet")
public class agregarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public agregarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
		//verificamos que el CUIT no esté cargado
		if(p==null) {
			Proveedor prov = new Proveedor();
			prov.setRazonSocial(razonSocial);
			prov.setMail(mail);
			prov.setTelefono(telefono);
			prov.setDireccion(direccion);
			prov.setCUIT(CUIT);
			
			MyResult res = ctrlProv.createProveedor(prov);
			if (res.getResult().equals(MyResult.results.Err)) {
				request.setAttribute("result", res);
				request.getRequestDispatcher("agregarProveedor.jsp").forward(request, response); 
		}else {
			request.setAttribute("result", res);
			request.setAttribute("nuevoProveedor", prov);
			request.getRequestDispatcher("listarProveedorServlet").forward(request, response);
		}
		}else {
			request.setAttribute("error", "El proveedor ingresado ya existe.");
			request.getRequestDispatcher("agregarProveedor.jsp").forward(request, response); }
			
		}
	
		
		
	}


