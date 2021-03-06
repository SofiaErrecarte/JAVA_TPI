package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Libro;
import entities.Persona;
import logic.LibroController;
import logic.PersonaController;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Init() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest (request, response);
	}	
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PersonaController ctrlp = new PersonaController();
	    	String email = request.getParameter("email");
	    	String password = request.getParameter("password");
	    	Persona per = new Persona();
	    	per.setEmail(email);
	    	per.setContraseña(password);
	    	per = ctrlp.getByEmail(per);
	    	if (per!=null)
	    	{
	    		if(per.isActivo()) {
	    		
	    		LibroController ctrlLibro = new LibroController();
	    		LinkedList<Libro> libros = ctrlLibro.getAllLibros();
	    		int var=1;
	    		for(Libro l : libros) {
	    			if(l.getIdLibro()==0) {
	    				var=0;
	    			}
	    		}
	    		if(var!=0) {
	    		LinkedList<Integer> cantidades = new LinkedList<Integer>();
	    		for (Libro l : libros) {
	    			int cant = ctrlLibro.cantEjDisponibles(l);
	    			cantidades.addLast(cant);
	    		}
	    		request.setAttribute("cantidadesDisp", cantidades);
	    		request.setAttribute("listaLibros", libros);
	        	  HttpSession sesion = request.getSession();
	        	  sesion.setAttribute("usuario", per);
	        	  RequestDispatcher rd = request.getRequestDispatcher("listaLibros.jsp");
	  			  rd.forward(request, response);
	     	  
	        	}	else {
		    		request.setAttribute("listaLibros", libros);
		        	HttpSession sesion = request.getSession();
		        	sesion.setAttribute("usuario", per);
	        		request.setAttribute("msjFiltro", "No hay libros registrados");
	        		request.setAttribute("listaLibros", libros);
	        		 RequestDispatcher rd = request.getRequestDispatcher("listaLibros.jsp");
		  			 rd.forward(request, response);
	        	}
	    		}else
	    		{
	    			request.setAttribute("error", "Usuario inactivo. Contáctese con el administrador.");
	    			request.getRequestDispatcher("index.jsp").forward(request, response);
	    			
	    		}
	    	}
	    		else
	    		{
	    			request.setAttribute("error", "Usuario o contraseña incorrecta. Inténtelo nuevamente.");
	    			request.getRequestDispatcher("index.jsp").forward(request, response);
	    			
	    		}
	    	
	}

}
