package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class APBLaporanBorangA  extends EkptgReportServlet {
	public APBLaporanBorangA() {
		super.setReportName("APBLaporanBorangA");
		super.setFolderName("php2\\APB\\LAPORAN");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
