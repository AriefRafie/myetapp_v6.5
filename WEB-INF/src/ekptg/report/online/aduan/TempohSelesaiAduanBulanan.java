package ekptg.report.online.aduan;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class TempohSelesaiAduanBulanan extends EkptgReportServlet {

	public TempohSelesaiAduanBulanan() {
		super.setReportName("TempohSelesaiAduanBulanan");
		super.setFolderName("online\\aduan");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
