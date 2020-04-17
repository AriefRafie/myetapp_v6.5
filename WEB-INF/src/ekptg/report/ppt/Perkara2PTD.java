package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class Perkara2PTD extends EkptgReportServlet {
	
	public Perkara2PTD() {
		super.setReportName("PERKARA 2 - PTD (CATATAN DAN PENANDAAN)");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}