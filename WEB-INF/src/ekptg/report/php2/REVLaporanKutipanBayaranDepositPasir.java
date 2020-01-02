package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class REVLaporanKutipanBayaranDepositPasir extends EkptgReportServlet {

	public REVLaporanKutipanBayaranDepositPasir () {
		super.setReportName("REVLaporanKutipanBayaranDepositPasir");
		super.setFolderName("php2\\REV\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
