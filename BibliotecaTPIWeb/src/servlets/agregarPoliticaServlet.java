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
				try {
					int numSocio = Integer.parseInt(request.getParameter("numsocio"));
					pp.setCantMaximaSocio(numSocio);
					}
				catch (NumberFormatException e){
					request.setAttribute("errorString", "Ingrese un n�mero en la Cantidad M�xima Socio por favor");
					request.getRequestDispatcher("agregarPolitica.jsp").forward(request, response); 
					
				}
				try {
					int numNoSocio = Integer.parseInt(request.getParameter("numnosocio"));
					pp.setCantMaximaNoSocio(numNoSocio);
				} catch (Exception e) {
					request.setAttribute("errorString", "Ingrese un n�mero en la Cantidad M�xima No Socio por favor.");
					request.getRequestDispatcher("agregarPolitica.jsp").forward(request, response); 
				}
				
				
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
