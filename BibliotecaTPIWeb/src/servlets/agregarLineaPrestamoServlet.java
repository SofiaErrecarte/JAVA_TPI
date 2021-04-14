package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.LineaPrestamo;
import entities.PoliticaPrestamo;
import entities.Prestamo;
import logic.LibroController;
import logic.LineaPrestamoController;
import logic.PoliticaPrestamoController;
import logic.PrestamoController;

@WebServlet("/agregarLineaPrestamoServlet")
public class agregarLineaPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public agregarLineaPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Prestamo p = new Prestamo();
		p.setIdPrestamo(ID);
		Prestamo pr = ctrlP.getByIdPrestamo(p);
		
		request.setAttribute("prestamo", pr);
		request.getRequestDispatcher("agregarLineaPrestamo.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaPrestamoController ctrlLP = new LineaPrestamoController();
		PrestamoController ctrlP = new PrestamoController();
		LibroController ctrlL = new LibroController();
		LineaPrestamo lpr = new LineaPrestamo();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Prestamo pr = new Prestamo();
		pr.setIdPrestamo(ID);
		Prestamo p = ctrlP.getByIdPrestamo(pr); //obtengo el prestamo al que le agrego las lp
		
		
		/*
		 * Calendar fech = Calendar.getInstance(); java.util.Date utilStartDate; try {
		 * utilStartDate = formato.parse(request.getParameter("fecha"));
		 * fech.setTime(utilStartDate); java.sql.Date date = new
		 * java.sql.Date(utilStartDate.getTime()); lpr.setFechaDevolucion(date); } catch
		 * (java.text.ParseException e) { lpr.setFechaDevolucion(null); }
		 */
		
		boolean devuelto = false;
		int idEj = Integer.parseInt(request.getParameter("idEjemplar"));
		lpr.setIdEjemplar(idEj);
		lpr.setIdPrestamo(ID);
		lpr.setDevuelto(devuelto);
		
		//seteo en no disponible el ejemplar seleccionado
		Ejemplar eje = new Ejemplar();
		eje.setIdEjemplar(idEj);
		Ejemplar ej = ctrlL.getByIdEjemplar(eje);
		ej.setDisponible(devuelto);
		ctrlL.setDisponible(ej, devuelto);
		
		
		p.addLp(lpr); //la añado a la coleccion de lp del prestamo
		ctrlLP.addLineaPrestamo(lpr);
		/*
		 * //verifico estado prestamo LinkedList<LineaPrestamo> lineasP =
		 * ctrlP.getLPByPrestamo(p); int cantTot = lineasP.size(); int cantDev = 0;
		 * for(LineaPrestamo lp : lineasP) { if(lp.isDevuelto()) { cantDev++; } }
		 * 
		 * if(cantDev==cantTot) { String e = "Devuelto"; ctrlP.setEstado(p, e); }else
		 * {ctrlP.setEstado(p, "Abierto");}
		 */
				
		request.setAttribute("nuevaLineaPrestamo", lpr);
		request.getRequestDispatcher("listarLineasPrestamoServlet").forward(request, response);
		
	}

}
