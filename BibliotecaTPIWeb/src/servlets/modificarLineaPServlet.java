package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ejemplar;
import entities.LineaPrestamo;
import entities.Prestamo;
import entities.MyResult;
import logic.LibroController;
import logic.LineaPrestamoController;
import logic.PrestamoController;

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
		LibroController ctrlL = new LibroController();		
		lpr.setIdLineaPrestamo(Integer.parseInt(request.getParameter("id")));
		
			  
		boolean devuelto = Boolean.parseBoolean(request.getParameter("devuelto"));
		int idEj = Integer.parseInt(request.getParameter("idEjemplar"));
		int idEjAnt = Integer.parseInt(request.getParameter("ejemplarAnterior"));
		lpr.setIdEjemplar(idEj);
		lpr.setDevuelto(devuelto);
		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
		lpr.setIdPrestamo(idPrestamo);
		
		
				
		MyResult res = ctrlLP.editLineaprestamo(lpr);
		if (res.getResult().equals(MyResult.results.Err)) {
			request.setAttribute("result", res);
			request.setAttribute ("lineaPrestamoAEditar",lpr);
			request.getRequestDispatcher("modificarLP.jsp").forward(request, response);
		} else {
			//seteo disponibilidad del ejemplar seleccionado y actualizo la del anterior
			Ejemplar eje = new Ejemplar();
			eje.setIdEjemplar(idEj);
			Ejemplar ej = ctrlL.getByIdEjemplar(eje);
			ej.setDisponible(devuelto);
			ctrlL.setDisponible(ej, devuelto);
			
			Ejemplar ejeAnt = new Ejemplar();
			ejeAnt.setIdEjemplar(idEjAnt);
			Ejemplar ejAnt = ctrlL.getByIdEjemplar(ejeAnt);
			
			
			if(ej != ejAnt) {
				ejAnt.setDisponible(true);
				ctrlL.setDisponible(ejAnt, true);
			}
			
			request.setAttribute("result", res);
			request.getRequestDispatcher("listarPrestamosServlet").forward(request, response);
		}
	}

}
