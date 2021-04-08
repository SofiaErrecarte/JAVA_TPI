package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@WebServlet("/agregarPrestamoServlet")
public class agregarPrestamoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public agregarPrestamoSevlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrestamoController ctrlP= new PrestamoController();
		Prestamo p = new Prestamo();
		LibroController ctrlLib = new LibroController(); 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
		//CREO EL PRESTAMO
		Calendar fech = Calendar.getInstance();
		java.util.Date utilStartDate;
		try {
			utilStartDate = formato.parse(request.getParameter("fechaDevolucion"));
			fech.setTime(utilStartDate);
			java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
			p.setFechaADevoler(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar fech2 = Calendar.getInstance();
		java.util.Date utilStartDate2;
		try {
			utilStartDate2 = formato.parse(request.getParameter("fechaPrestamo"));
			fech2.setTime(utilStartDate2);
			java.sql.Date date = new java.sql.Date(utilStartDate2.getTime());
			p.setFechaPrestamo(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int idPers = Integer.parseInt(request.getParameter("idPersona"));
		p.setIdPersona(idPers);		
		
		//creo el prestamo
		ctrlP.addPrestamo(p);
		request.setAttribute("nuevoPrestamo", p);
		request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
		
		
	
	}
	
}
