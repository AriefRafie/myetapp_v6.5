package ekptg.view.ppk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class PendaftaranSimatiCheck implements IServlet2 {
	static Logger myLogger = Logger.getLogger(PendaftaranSimatiCheck.class);

	@Override
	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String submit = request.getParameter("command");
		myLogger.info("submit:"+submit);
		
		
		if ("checkICSimati".equals(submit)) {
			//String display = "<font color='red'>AMARAN!</font> <br> <input type='button' value='hantar' >";
			try {
				//coding
				
				
			} catch (Exception e) {
				out.println("error :" + e.getMessage());
			}
			//out.println(display);
		}	
		out.close();
	}
	
	
}
