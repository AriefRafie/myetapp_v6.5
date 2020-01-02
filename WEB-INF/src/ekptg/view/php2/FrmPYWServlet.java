package ekptg.view.php2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;
import ekptg.model.php2.FrmPYWKutipanData;

public class FrmPYWServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		FrmPYWKutipanData logicKutipanData = new FrmPYWKutipanData();

		 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("command");
			
		if ("checkingExistNoFail".equals(submit)){
			
			String noFail = request.getParameter("noFail");
						
			try {
					if (logicKutipanData.checkingExistNoFail(noFail.trim())){
						out.println("<script>popupMsg();</script>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}