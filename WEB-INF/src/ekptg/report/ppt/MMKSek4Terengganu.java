package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MMKSek4Terengganu extends EkptgReportServlet {
	
	public MMKSek4Terengganu() {
		super.setReportName("MMK_TERENGGANU(SS 4)");
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
		
		String flagVersion = (String)parameters.get("flagVersion");
		doVersioning("MMK_TERENGGANU(SS 4)",idfail,nofail,flagVersion);
		
	}
}