package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MBSelangorSS8 extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public MBSelangorSS8() {
		super.setReportName("MMK_SELANGOR_SUBREPORT(SS 8)");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
	}
}