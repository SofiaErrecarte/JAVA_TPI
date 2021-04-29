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
		Prestamo per = ctrlP.getByIdPrestamo(p);
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
		
		//VERIFICAMOS FECHAS
				
		if(per.getFechaPrestamo().after(p.getFechaADevoler())) {
			request.setAttribute("error", "La fecha a devolver no puede ser menor que la fecha de creaci�n.");
			request.setAttribute ("prestamoAEditar", per);
			request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response); 
				}else {
					int idPers = Integer.parseInt(request.getParameter("idPersona"));
					p.setIdPersona(idPers);	
					//edito el prestamo
					MyResult res = ctrlP.editPrestamo(p);
					if (res.getResult().equals(MyResult.results.Err)) {
						request.setAttribute("result", res);
						request.setAttribute ("prestamoAEditar",per);
						request.getRequestDispatcher("modificarPrestamo.jsp").forward(request, response); 
					}else {
						request.setAttribute("result", res);
						request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
					}
					
					
				}
				
			
	}

}
