package ekptg.report.meps;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanJumlahNilaianHartaBaru extends EkptgReportServlet {
	public LaporanJumlahNilaianHartaBaru() {
	
		super.setReportName("LaporanJumlahHartai");
		super.setFolderName("mep");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
