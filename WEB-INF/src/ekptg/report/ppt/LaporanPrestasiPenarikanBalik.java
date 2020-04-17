package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiPenarikanBalik extends EkptgReportServlet {
	
	public LaporanPrestasiPenarikanBalik() {
		super.setReportName("PRESTASI PENYELESAIAN PENARIKAN BALIK BAGI TAHUN");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}