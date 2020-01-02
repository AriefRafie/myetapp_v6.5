package ekptg.view.php2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;
import ekptg.model.php2.FrmCRBKutipanData;

public class FrmCRBServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		 FrmCRBKutipanData logicKutipanData = new FrmCRBKutipanData();
		 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("command");
		
		if ("papar".equals(submit)){
			String idFail = request.getParameter("idFail");
			session.setAttribute("idFail", idFail);
		}
		else if ("checkingExistNoFail".equals(submit)){
			
			String noFail = request.getParameter("noFail");
			try {
				if (logicKutipanData.checkingExistNoFail(noFail)){
					out.println("<script>popupMsg();</script>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}