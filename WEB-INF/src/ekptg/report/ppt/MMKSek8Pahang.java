package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MMKSek8Pahang extends EkptgReportServlet {
	
	public MMKSek8Pahang() {
		super.setReportName("MMK_PAHANG(SS 8)");
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
		doVersioning("MMK_PAHANG(SS 8)",idfail,nofail,flagVersion);
		
	}
}