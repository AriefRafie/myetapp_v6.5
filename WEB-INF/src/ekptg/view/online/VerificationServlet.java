package ekptg.view.online;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

public class VerificationServlet implements IServlet2{
	
   public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context)
    throws IOException, ServletException
  {
	   	HttpSession session = request.getSession(true);
		//PrintWriter out = response.getWriter();
		//String contextPath = request.getContextPath();
		//String submit = request.getParameter("command");
		
		response.setHeader("Cache-Control", "no-cache");
	    //response.setDateHeader("Expires", 6880953060378542080L);
	    response.setContentType("image/png");
	    ImageVerification verification = new ImageVerification(response.getOutputStream());
	    String code = verification.getVerificationValue();
	    session.setAttribute("verification.code", code);
	    response.flushBuffer();
		
		
		
		
  }

}
