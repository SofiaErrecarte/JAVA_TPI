package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.LineaPrestamo;
import entities.PoliticaPrestamo;
import entities.Prestamo;
import logic.LineaPrestamoController;
import logic.PoliticaPrestamoController;
import logic.PrestamoController;

@WebServlet("/listarLineasPrestamoServlet")
public class listarLineasPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public listarLineasPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaPrestamoController ctrlLp = new LineaPrestamoController();
		PrestamoController ctrlPre = new PrestamoController();
		int ID = Integer.parseInt (request.getParameter("id"));
		Prestamo pr = new Prestamo();
		pr.setIdPrestamo(ID);
		Prestamo p = ctrlPre.getByIdPrestamo(pr);
		//LinkedList<LineaPrestamo> lineasP = p.getLineasPrestamo();
		LinkedList<LineaPrestamo> lineasP = ctrlPre.getLPByPrestamo(p);
		int cant = lineasP.size();
		//busco ult politica pr y asigno el limite a no socio. recordar q no existe socio
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
		PoliticaPrestamo pp = new PoliticaPrestamo();
		pp = ctrlPP.getLast();
		int limiteNS = pp.getCantMaximaNoSocio();
		request.setAttribute("limiteNS", limiteNS);
		request.setAttribute("cantidad", cant);
		request.setAttribute("listaLineas", lineasP);
		request.setAttribute("prestamo", p);
		request.getRequestDispatcher("listaLineasP.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
