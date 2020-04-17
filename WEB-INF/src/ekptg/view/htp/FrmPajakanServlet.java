package ekptg.view.htp;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class FrmPajakanServlet implements IServlet2 {

	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPajakanServlet.class);

	public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) 
	 	throws IOException, ServletException {
		HttpSession session = request.getSession();
		String submit = request.getParameter("command");
		myLog.info("submit="+submit);	
		if (submit.equals("papar")){
			String idFail = request.getParameter("idFail");
			session.setAttribute("idFail", idFail);
		
		}else if (submit.equals("getGIS")) {
			ResourceBundle rb = ResourceBundle.getBundle("file");
			myLog.info("getGIS:rb="+rb);
			context.setAttribute("gisURL", rb.getString("gisurl"));
			session.setAttribute("gisURL", rb.getString("gisurl"));

		}else if (submit.equals("getGISV")) {
			ResourceBundle rb = ResourceBundle.getBundle("file");
			myLog.info("getGISV:rb="+rb);
			context.setAttribute("gisURL", rb.getString("gisview"));
			session.setAttribute("gisURL", rb.getString("gisview"));

		}		
		
	}
	
	 
}
