package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class KertasMinitMB extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	
	public KertasMinitMB() {
		super.setReportName("MMK_SELANGOR_KERTAS_MINIT_PENTADBIR");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
	}
}