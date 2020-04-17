/**
 * 
 */
package ekptg.view.online.htp.pajakan;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String submit = request.getParameter("command");
		
		if ("papar".equals(submit)){
			String idFail = request.getParameter("idFail");
			String idKementerian = request.getParameter("idKementerian");
			System.out.println("FrmPajakanServlet::papar " +idFail+", "+idKementerian);
			session.setAttribute("idFail", idFail);
			session.setAttribute("idKementerian", idKementerian);
		}				
	}
}
