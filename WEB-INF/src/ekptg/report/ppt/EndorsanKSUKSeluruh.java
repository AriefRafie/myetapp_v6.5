package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class EndorsanKSUKSeluruh extends EkptgReportServlet {
	
	public EndorsanKSUKSeluruh() {
		super.setReportName("ss8_endors_borangk_SUK_seluruh");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}