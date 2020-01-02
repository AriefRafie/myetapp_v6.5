package ekptg.fpx;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class FpxReportRingkasanTransaksiClass extends EkptgReportServlet {
	
	public FpxReportRingkasanTransaksiClass() {
		super.setReportName("Laporan_Transaksi_FPX");
		super.setFolderName("online");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}