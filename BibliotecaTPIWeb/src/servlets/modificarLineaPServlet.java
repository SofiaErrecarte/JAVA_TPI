package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.LineaPrestamo;
import logic.LibroController;
import logic.LineaPrestamoController;

@WebServlet("/modificarLineaPServlet")
public class modificarLineaPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public modificarLineaPServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaPrestamoController ctrlLP = new LineaPrestamoController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		LineaPrestamo lpr = new LineaPrestamo();
		lpr.setIdLineaPrestamo(ID);
		LineaPrestamo lp = ctrlLP.getById(lpr);
		
	
		request.setAttribute("lineaPrestamoAEditar", lp);
		request.getRequestDispatcher("modificarLP.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaPrestamoController ctrlLP = new LineaPrestamoController();
		LineaPrestamo lpr = new LineaPrestamo();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
		LibroController ctrlL = new LibroController();		
		lpr.setIdLineaPrestamo(Integer.parseInt(request.getParameter("id")));
		
		java.util.Date utilStartDate;
		try {
			utilStartDate = formato.parse(request.getParameter("fecha"));
			java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
			lpr.setFechaDevolucion(date);
		} catch (java.text.ParseException e1) {
			lpr.setFechaDevolucion(null);
		}
		boolean devuelto = Boolean.parseBoolean(request.getParameter("devuelto"));
		int idEj = Integer.parseInt(request.getParameter("idEjemplar"));
		
		lpr.setIdEjemplar(idEj);
		lpr.setDevuelto(devuelto);
		lpr.setIdLineaPrestamo(Integer.parseInt(request.getParameter("idPrestamo")));
		
		//seteo disponibilidad del ejemplar seleccionado
		Ejemplar eje = new Ejemplar();
		eje.setIdEjemplar(idEj);
		Ejemplar ej = ctrlL.getByIdEjemplar(eje);
		ej.setDisponible(devuelto);
		ctrlL.setDisponible(ej, devuelto);
		
		ctrlLP.editLineaprestamo(lpr);
		request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
	}

}
