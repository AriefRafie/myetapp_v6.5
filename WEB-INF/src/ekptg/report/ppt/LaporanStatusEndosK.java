package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanStatusEndosK extends EkptgReportServlet {
	
	public LaporanStatusEndosK() {
		super.setReportName("SENARAI_BORANGK_DIKELUARKAN_MENGIKUT_DAERAH");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}