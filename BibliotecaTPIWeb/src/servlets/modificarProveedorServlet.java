package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Proveedor;
import entities.MyResult;
import logic.ProveedorController;

@WebServlet("/modificarProveedorServlet")
public class modificarProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public modificarProveedorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProveedorController ctrlProv = new ProveedorController();
		Proveedor prov = new Proveedor();
		prov.setIdProveedor(Integer.parseInt(request.getParameter("id")));
		
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
		MyResult res = ctrlProv.editProveedor(prov);
		request.setAttribute("result", res);
		request.setAttribute("listaProveedores",ctrlProv.getAllProveedores());
		request.getRequestDispatcher("listarProveedorServlet").forward(request, response);
		
	}

}
