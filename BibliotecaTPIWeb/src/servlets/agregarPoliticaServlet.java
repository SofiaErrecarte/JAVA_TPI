package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import entities.MyResult;
import entities.PoliticaPrestamo;
import logic.PoliticaPrestamoController;

@WebServlet("/agregarPoliticaServlet")
public class agregarPoliticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public agregarPoliticaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
				PoliticaPrestamo pp = new PoliticaPrestamo();
				int numSocio = Integer.parseInt(request.getParameter("numsocio"));
				pp.setCantMaximaSocio(numSocio);
				int numNoSocio = Integer.parseInt(request.getParameter("numnosocio"));
				pp.setCantMaximaNoSocio(numNoSocio);
				
				
				
				MyResult res = ctrlPP.newPolitica(pp);
				if (res.getResult().equals(MyResult.results.Err)) {
					request.setAttribute("result", res);
					request.getRequestDispatcher("agregarPolitica.jsp").forward(request, response); 
				}else {
					request.setAttribute("result", res);
					request.setAttribute("nuevaPolitica", pp);
					request.getRequestDispatcher("listarPoliticaServlet").forward(request, response);
				}
				
				
	}

}
