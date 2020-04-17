package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiPermohonanBantahanHQ extends EkptgReportServlet{

	public LaporanPrestasiPermohonanBantahanHQ() {
		super.setReportName("LAPORAN PRESTASI PERMOHONAN BANTAHAN KE MAHKAMAH, PERMINTAAN UKUR, AFIDAVIT DAN AMANAH RAYA BERHAD DALAM TAHUN HQ");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
