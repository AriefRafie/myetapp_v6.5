package ekptg.report.meps;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPermohonanWarisBaru extends EkptgReportServlet {
	public LaporanPermohonanWarisBaru() {
		System.out.println("dalam report");
		super.setReportName("LaporanPermohonanWarisNegeri");
		super.setFolderName("mep");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
