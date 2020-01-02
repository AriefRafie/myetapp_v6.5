package ekptg.view.ppt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

public class SementaraCheck implements IServlet2 {

	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext arg2) throws IOException, ServletException {
		
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 String contextPath = request.getContextPath();
		 String submit = request.getParameter("command");
		 String message = request.getParameter("alert_message");	 
		 String readmode = request.getParameter("mode");
		 String actionAgihanTugas = request.getParameter("action");
		
		
		 if("checking_validation".equals(submit))
		 {
			 try
			 {
				 if(message!="")
				 {
							 
				     if(readmode.equals("papar"))	 
				     {
					 out.println("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > "); 
				     }
				     else
				     {
				     out.println(message + " <input type='hidden' id='validation_field' name='validation_field' value='invalid' > "); 
					 }
				     
			      
					 }
				 else
				 {
				 out.println("<input type='hidden' id='validation_field' name='validation_field' value='valid' >"); 
				 }
			 }
			 catch (Exception e) {
					
					e.printStackTrace();
				}
			 
		 }	
		 
		 
		
			
	}
		
	

}
