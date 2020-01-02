package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class JPPH_HadirSiasatan_PB extends EkptgReportServlet{

	public JPPH_HadirSiasatan_PB() {
		super.setReportName("Surat JPPH Siasatan Penarikan Balik");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
