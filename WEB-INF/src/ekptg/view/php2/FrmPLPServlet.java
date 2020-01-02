package ekptg.view.php2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;
import ekptg.model.php2.FrmPLPKutipanData;
import ekptg.model.php2.FrmPNWKutipanData;
import ekptg.model.php2.FrmTKRKutipanData;

public class FrmPLPServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		FrmPLPKutipanData logicKutipanData = new FrmPLPKutipanData();
		FrmPNWKutipanData logicPNWKutipanData = new FrmPNWKutipanData();
		FrmTKRKutipanData logicTKRKutipanData = new FrmTKRKutipanData();
		 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("command");
			
		if ("checkingExistNoFail".equals(submit)){
			
			String noFail = request.getParameter("noFail");
			String idSuburusan = request.getParameter("idSuburusan");
			
			//PENAWARAN
			if("32".equals(idSuburusan)){
				
				try {
					if (logicPNWKutipanData.checkingExistNoFail(noFail)){
						out.println("<script>popupMsg();</script>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			//TUKARGUNA
			else if("33".equals(idSuburusan)){
				try {
					if (logicTKRKutipanData.checkingExistNoFail(noFail)){
						out.println("<script>popupMsg();</script>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			//PELEPASAN / PENYERAHANBALIK
			else if("34".equals(idSuburusan)){
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
}