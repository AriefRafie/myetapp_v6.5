package ekptg.report.php;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class APBLaporanPengeluaranPasirLaut  extends EkptgReportServlet {
	public APBLaporanPengeluaranPasirLaut() {
		super.setReportName("APBLaporanPengeluaranPasirLaut");
		super.setFolderName("php");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
