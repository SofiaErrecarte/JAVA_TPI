package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.Libro;
import entities.LineaPrestamo;
import entities.Prestamo;
import logic.LibroController;
import logic.LineaPrestamoController;
import logic.PrestamoController;

/**
 * Servlet implementation class agregarLPServlet
 */
@WebServlet("/agregarPrestamoServlet")
public class agregarPrestamoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarPrestamoSevlet() {
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
	
		PrestamoController ctrlP= new PrestamoController();
		Prestamo p = new Prestamo();
		LineaPrestamoController ctrlLP= new LineaPrestamoController();
		LineaPrestamo lp = new LineaPrestamo();
		LineaPrestamo lp1 = new LineaPrestamo();
		LineaPrestamo lp2 = new LineaPrestamo();
		LineaPrestamo lp3 = new LineaPrestamo();
		LibroController ctrlLib = new LibroController(); 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
	
		//CREO EL PRESTAMO
		java.util.Date utilStartDate1;
		try {
			utilStartDate1 = formato.parse(request.getParameter("fechaPrestamo"));
			java.sql.Date date = new java.sql.Date(utilStartDate1.getTime());
			p.setFechaPrestamo(date);
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		java.util.Date utilStartDate2;
		try {
			utilStartDate2 = formato.parse(request.getParameter("fechaDevolucion"));
			java.sql.Date date = new java.sql.Date(utilStartDate2.getTime());
			p.setFechaADevoler(date);
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int idPers = Integer.parseInt(request.getParameter("txtidpersona"));
		p.setIdPersona(idPers);		
		Prestamo prest = ctrlP.addPrestamo(p);

		//CREO LAS LINEAS DE PRESTAMO 
		int idLibro = Integer.parseInt(request.getParameter("idlibro"));
		Libro l = new Libro();
		l.setIdLibro(idLibro);
		Libro lib = ctrlLib.getByIdLibro(l);
		LinkedList<Ejemplar> ejs = new LinkedList<>();
		ejs = ctrlLib.getEjByIdLibro(lib);
		int idEjemplar = Integer.parseInt(request.getParameter("txtidejemplar"));
		for (Ejemplar e : ejs) {
			if (e.getIdEjemplar() == idEjemplar) {
				//Date fechaDevolucion = null;
				java.util.Date utilStartDate;
				try {
					utilStartDate = formato.parse(request.getParameter("txtfecha"));
					java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
					lp.setFechaDevolucion(date);
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
						
				Boolean devuelto = false; 
				lp.setDevuelto(devuelto);
				lp.setIdPrestamo(prest.getIdPrestamo());
				lp.setIdEjemplar(idEjemplar);	
				LineaPrestamo lineanueva = ctrlLP.addLineaPrestamo(lp);
				request.setAttribute("lpnueva", lineanueva);
				request.setAttribute("prestamonuevo", prest);
				request.getRequestDispatcher("listarPrestamosSevlet").forward(request, response);
			
			}
		}
		String id1=request.getParameter("txtlibro1");
		if (id1 != null) {
		Libro l1 = new Libro();
		int idLibro1 = Integer.parseInt(id1);
		l1.setIdLibro(idLibro1);
		Libro lib1 = ctrlLib.getByIdLibro(l1);
		LinkedList<Ejemplar> ejs1 = new LinkedList<>();
		ejs1 = ctrlLib.getEjByIdLibro(lib1);
		int idEjemplar1 = Integer.parseInt(request.getParameter("txtidejemplar1"));
		for (Ejemplar e : ejs1) {
			if (e.getIdEjemplar() == idEjemplar1) {
				//Date fechaDevolucion = null;
				Date fecha1;
				try {
					fecha1 = formato.parse(request.getParameter("txtfecha1"));
					lp1.setFechaDevolucion((java.sql.Date) fecha1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
				Boolean devuelto1 = false; 
				int idPrestamo1 = Integer.parseInt(request.getParameter("id")); 
				lp.setDevuelto(devuelto1);
				lp.setIdPrestamo(idPrestamo1);
				lp.setIdEjemplar(idEjemplar1);	
			}
		}
		ctrlLP.addLineaPrestamo(lp1);
		}
	
	//LINEA PRESTAMO 2
	String id2=request.getParameter("txtlibro2");
	if (id2 != null) {
	int idLibro2 = Integer.parseInt(id2);
	Libro l2 = new Libro();
	l2.setIdLibro(idLibro2);
	Libro lib2 = ctrlLib.getByIdLibro(l2);
	LinkedList<Ejemplar> ejs2 = new LinkedList<>();
	ejs2 = ctrlLib.getEjByIdLibro(lib2);
	int idEjemplar2 = Integer.parseInt(request.getParameter("txtidejemplar2"));
	for (Ejemplar e : ejs2) {
		if (e.getIdEjemplar() == idEjemplar2) {
			//Date fechaDevolucion = null;
			Date fecha2;
			try {
				fecha2 = formato.parse(request.getParameter("txtfecha2"));
				lp2.setFechaDevolucion((java.sql.Date) fecha2);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			Boolean devuelto2 = false; 
			int idPrestamo2 = Integer.parseInt(request.getParameter("id")); 
			lp.setDevuelto(devuelto2);
			lp.setIdPrestamo(idPrestamo2);
			lp.setIdEjemplar(idEjemplar2);	
		}
	}
	ctrlLP.addLineaPrestamo(lp2);
	}
	//AGREGAR LINEA PRESTAMO 3
	String id3=request.getParameter("txtlibro3");
	if (id3 !=null) {
	int idLibro3 = Integer.parseInt(id3);
	Libro l3 = new Libro();
	l3.setIdLibro(idLibro3);
	Libro lib3 = ctrlLib.getByIdLibro(l3);
	LinkedList<Ejemplar> ejs3 = new LinkedList<>();
	ejs3 = ctrlLib.getEjByIdLibro(lib3);
	int idEjemplar3 = Integer.parseInt(request.getParameter("txtidejemplar3"));
	for (Ejemplar e : ejs3) {
		if (e.getIdEjemplar() == idEjemplar3) {
			//Date fechaDevolucion = null;
			Date fecha3;
			try {
				fecha3 = formato.parse(request.getParameter("txtfecha3"));
				lp3.setFechaDevolucion((java.sql.Date) fecha3);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			Boolean devuelto3 = false; 
			int idPrestamo3 = Integer.parseInt(request.getParameter("id")); 
			lp.setDevuelto(devuelto3);
			lp.setIdPrestamo(idPrestamo3);
			lp.setIdEjemplar(idEjemplar3);	
		}
	}
	ctrlLP.addLineaPrestamo(lp3);
	}
		
		//Linea Prestamo 2
		
	
	}
	
}
