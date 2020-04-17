package ekptg.report.esaduan;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanByModule extends EkptgReportServlet{

	public LaporanByModule() {
		super.setReportName("LaporanByModul");
		super.setFolderName("esaduan");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
