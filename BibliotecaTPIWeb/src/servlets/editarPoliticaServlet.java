package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.PoliticaPrestamo;
import logic.PoliticaPrestamoController;

/**
 * Servlet implementation class editarPoliticaServlet
 */
@WebServlet("/editarPoliticaServlet")
public class editarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarPoliticaServlet() {
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
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
		PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
		PoliticaPrestamo pp = new PoliticaPrestamo();
		/*try {
			pp.setFechaAlta((Date)formato.parse(request.getParameter("fechaalta")));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}*/
		pp.setIdPoliticaPrestamo(Integer.parseInt(request.getParameter("idPolitica")));
		pp.setCantMaximaNoSocio(Integer.parseInt(request.getParameter("librosSocio")));
		pp.setCantMaximaSocio(Integer.parseInt(request.getParameter("librosNoSocio")));
		
		ctrlPP.editPolitica(pp);
		request.getRequestDispatcher("listarPoliticaServlet").forward(request, response);
		
		//doGet(request, response);
	}

}
