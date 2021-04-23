package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.MyResult;
import entities.Persona;
import entities.Proveedor;
import logic.LibroController;
import logic.PersonaController;
import logic.ProveedorController;

/**
 * Servlet implementation class modificarPersonaServlet
 */
@WebServlet("/modificarPersonaServlet")
public class modificarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Persona per = new Persona();
		per.setIdPersona(ID);
		Persona p = ctrlPers.getByIdPersona(per);
		
		request.setAttribute ("personaAEditar",p);
		request.getRequestDispatcher("modificarPersona.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPers = new PersonaController();
		Persona per = new Persona();
		per.setIdPersona(Integer.parseInt(request.getParameter("id")));
		Persona p = ctrlPers.getByIdPersona(per);
		String apellido = request.getParameter("apellido");
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		String direccion = request.getParameter("direccion");
		String dni = request.getParameter("dni");
		
		per.setApellido(apellido);
		per.setDireccion(direccion);
		per.setDni(dni);
		per.setEmail(mail);
		per.setNombre(nombre);
		per.setTelefono(telefono);
		per.setAdmin(p.isAdmin());
		MyResult res = ctrlPers.editPersona(per);
		request.setAttribute("result", res);
		request.setAttribute("listaPersonas",ctrlPers.getAllPersonas());
		request.getRequestDispatcher("listarPersonasServlet").forward(request, response);
		
	}

	
}
