package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPerbandinganAward_PT_JPPH extends EkptgReportServlet {
	
	public LaporanPerbandinganAward_PT_JPPH() {
		super.setReportName("LAPORAN PERBANDINGAN AWARD PT JPPH");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}