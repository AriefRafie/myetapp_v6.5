package ekptg.report.meps;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanJumlahBayaranDutiHartaBaru extends EkptgReportServlet {
	public LaporanJumlahBayaranDutiHartaBaru() {
	
		super.setReportName("LaporanJumlahBayaranDutiHarta");
		super.setFolderName("mep");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
