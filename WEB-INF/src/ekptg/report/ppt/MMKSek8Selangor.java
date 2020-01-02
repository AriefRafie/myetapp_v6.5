package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ekptg.report.EkptgReportServlet;

public class MMKSek8Selangor extends EkptgReportServlet {
	
	public MMKSek8Selangor() {
		super.setReportName("MMK_SELANGOR(SS 8)");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("idFail") != null){
			idfail = (String)parameters.get("idFail");
		}
		
		String nofail = "";
		if (parameters.get("no_fail") != null){
			nofail = (String)parameters.get("no_fail");
		}
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		System.out.println("**************** MMKSek8Selangor check ID SESSION : "+user_id);
		request.setAttribute("user_id", user_id);
		String flagVersion = (String)parameters.get("flagVersion");
		doVersioning("MMK_SELANGOR(SS 8)",idfail,nofail,flagVersion);
	}
}