package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import entities.PoliticaPrestamo;
import logic.PoliticaPrestamoController;

/**
 * Servlet implementation class agregarPoliticaServlet
 */
@WebServlet("/agregarPoliticaServlet")
public class agregarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarPoliticaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
				PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
				PoliticaPrestamo pp = new PoliticaPrestamo();
				int numSocio = Integer.parseInt(request.getParameter("numsocio"));
				int numNoSocio = Integer.parseInt(request.getParameter("numnosocio"));
				
				
				try {
					pp.setFechaAlta((Date)formato.parse(request.getParameter("fechaalta")));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				pp.setCantMaximaSocio(numSocio);
				pp.setCantMaximaNoSocio(numNoSocio);
				
				ctrlPP.newPolitica(pp);
				request.setAttribute("nuevaPolitica", pp);
				request.getRequestDispatcher("listarPoliticaServlet").forward(request, response);
				
	}

}
