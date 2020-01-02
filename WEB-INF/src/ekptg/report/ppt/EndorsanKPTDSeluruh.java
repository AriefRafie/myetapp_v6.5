package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class EndorsanKPTDSeluruh extends EkptgReportServlet {
	
	public EndorsanKPTDSeluruh() {
		super.setReportName("ss8_endors_borangk_PTD_seluruh");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}