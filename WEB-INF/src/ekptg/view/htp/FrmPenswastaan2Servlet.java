/**
 * 
 */
package ekptg.view.htp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

/**
 * 
 *
 */
public class FrmPenswastaan2Servlet implements IServlet2 {

	//@Override
	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String submit = request.getParameter("command");
		
		if ("papar".equals(submit)){
			String idFail = request.getParameter("idFail");
			session.setAttribute("idFail", idFail);
		}				
	}
}
