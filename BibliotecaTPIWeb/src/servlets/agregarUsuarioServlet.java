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

@WebServlet("/agregarUsuarioServlet")
public class agregarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public agregarUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaController ctrlPer  = new PersonaController();
		Persona p = new Persona();
		
		String apellido = request.getParameter("apellido");
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("mail");
		String direccion = request.getParameter("direccion");
		String dni = request.getParameter("dni");
		String contraseña = request.getParameter("pass");
		//p.setAdmin(false);
		//p.setDni(dni);
		//p=ctrlPer.getByDNI(p);
		//verificamos que la persona no este cargada
		//if(p==null) {
			Persona per = new Persona();
			per.setApellido(apellido);
			per.setContraseña(contraseña);
			per.setDireccion(direccion);
			per.setDni(dni);
			per.setEmail(mail);
			per.setNombre(nombre);
			per.setTelefono(telefono);
			per.setAdmin(false);
			MyResult res = ctrlPer.createPersona(per);
			if (res.getResult().equals(MyResult.results.Err)) {
				request.setAttribute("result", res);
				request.getRequestDispatcher("agregarUsuario.jsp").forward(request, response);
			} else {
				request.setAttribute("result", res);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		//}
	}
}
