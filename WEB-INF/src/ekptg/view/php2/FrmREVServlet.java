package ekptg.view.php2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;
import ekptg.model.php2.FrmRevKutipanDataSewaData;

public class FrmREVServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		FrmRevKutipanDataSewaData logic = new FrmRevKutipanDataSewaData();
		 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("command");
		System.out.println(submit);
		if ("checkingExistNoFail".equals(submit)){
			
			String noFail = request.getParameter("noFail");			
			
			try {
				if (logic.checkingExistNoFail(noFail)){
					out.println("<script>popupMsg();</script>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if ("checkingExistNoFailUpdate".equals(submit)){
			
			String idFail = request.getParameter("idFail");
			String noFail = request.getParameter("noFail");			
			System.out.println(idFail);
			System.out.println(noFail);
			try {
				if (logic.checkingExistNoFailUpdate(noFail, idFail)){
					out.println("<script>popupMsg();</script>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}