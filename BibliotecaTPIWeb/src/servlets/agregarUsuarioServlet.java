package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Persona;
import logic.PersonaController;

/**
 * Servlet implementation class agregarUsuarioServlet
 */
@WebServlet("/agregarUsuarioServlet")
public class agregarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonaController ctrlPer  = new PersonaController();
		Persona p = new Persona();
		
		String apellido = request.getParameter("apellido");
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		String direccion = request.getParameter("direccion");
		String dni = request.getParameter("dni");
		String contrase�a = request.getParameter("pass");
		//p.setAdmin(false);
		//p.setDni(dni);
		//p=ctrlPer.getByDNI(p);
		//verificamos que la persona no este cargada
		//if(p==null) {
			Persona per = new Persona();
			per.setApellido(apellido);
			per.setContrase�a(contrase�a);
			per.setDireccion(direccion);
			per.setDni(dni);
			per.setEmail(mail);
			per.setNombre(nombre);
			per.setTelefono(telefono);
			per.setAdmin(false);
			ctrlPer.createPersona(per);
			//ACA DEBER�A IR AL INDEX Y PONER AHI QUE SE CREO CON EXITO POR EJEMPLO 
			request.getRequestDispatcher("index.jsp").forward(request, response); 
			
		//}
	}
}