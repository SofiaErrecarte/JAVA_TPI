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
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				PoliticaPrestamoController ctrlPP = new PoliticaPrestamoController();
				PoliticaPrestamo pp = new PoliticaPrestamo();
				int numSocio = Integer.parseInt(request.getParameter("numsocio"));
				int numNoSocio = Integer.parseInt(request.getParameter("numnosocio"));
				
				/*Calendar fech = Calendar.getInstance();
				java.util.Date utilStartDate;
				try {
					utilStartDate = formato.parse(request.getParameter("fecha"));
					fech.setTime(utilStartDate);
					java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
					//pp.setFechaAlta(date);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}*/
				
				pp.setCantMaximaSocio(numSocio);
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
