package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MyResult;
import entities.Prestamo;
import logic.PrestamoController;

@WebServlet("/modificarPrestamoServlet")
public class modificarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public modificarPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController ();
		int  ID  =  Integer.parseInt (request.getParameter("id"));
		Prestamo pr = new Prestamo();
		pr.setIdPrestamo(ID);
		Prestamo p = ctrlP.getByIdPrestamo(pr);
		
		request.setAttribute ("prestamoAEditar",p);
		request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoController ctrlP = new PrestamoController ();
		Prestamo p = new Prestamo();
		p.setIdPrestamo(Integer.parseInt(request.getParameter("id")));
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		//fechas
		Calendar fech = Calendar.getInstance();
		java.util.Date utilStartDate;
		try {
			utilStartDate = formato.parse(request.getParameter("fechaADevolver"));
			fech.setTime(utilStartDate);
			java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
			p.setFechaADevoler(date);
		} catch (java.text.ParseException e) {
			p.setFechaADevoler(null);
		}
		
		Calendar fech2 = Calendar.getInstance();
		java.util.Date utilStartDate2;
		try {
			utilStartDate2 = formato.parse(request.getParameter("fechaPrestamo"));
			fech2.setTime(utilStartDate2);
			java.sql.Date date = new java.sql.Date(utilStartDate2.getTime());
			p.setFechaPrestamo(date);
		} catch (java.text.ParseException e) {
			p.setFechaPrestamo(null);
		}
		
		/*
		 * Calendar fech3 = Calendar.getInstance(); java.util.Date utilStartDate3; try {
		 * utilStartDate3 = formato.parse(request.getParameter("fechaDevolucion"));
		 * fech3.setTime(utilStartDate3); java.sql.Date date = new
		 * java.sql.Date(utilStartDate3.getTime()); p.setFechaDevolucion(date); } catch
		 * (java.text.ParseException e) { p.setFechaDevolucion(null); }
		 */
		//VERIFICAMOS FECHAS
				
		if(p.getFechaPrestamo().after(p.getFechaADevoler())) {
			request.setAttribute("error", "La fecha a devolver no puede ser menor que la fecha de creación.");
			request.setAttribute ("prestamoAEditar",p);
			request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response); 
				}else {
					
					//if(p.getFechaDevolucion()!=null && p.getFechaPrestamo().after(p.getFechaDevolucion())) {
						//request.setAttribute("error", "La fecha de devolución no puede ser menor que la fecha de creación.");
						//request.setAttribute ("prestamoAEditar",p);
						//request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response); 
					//}else {			
					
					int idPers = Integer.parseInt(request.getParameter("idPersona"));
					p.setIdPersona(idPers);	
					//edito el prestamo
					MyResult res = ctrlP.editPrestamo(p);
					if (res.getResult().equals(MyResult.results.Err)) {
						request.setAttribute("result", res);
						request.setAttribute ("prestamoAEditar",p);
						request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response); 
					}else {
						request.setAttribute("result", res);
						request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
					}
					
					
				}
				
			
	}

}
