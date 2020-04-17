package ekptg.report.online.aduan;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class TempohSelesaiAduanKumulatif extends EkptgReportServlet {

	public TempohSelesaiAduanKumulatif() {
		super.setReportName("TempohSelesaiAduanKumulatif");
		super.setFolderName("online\\aduan");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
