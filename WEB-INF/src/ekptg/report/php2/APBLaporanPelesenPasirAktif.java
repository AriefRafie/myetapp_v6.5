package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class APBLaporanPelesenPasirAktif extends EkptgReportServlet {
	
	public APBLaporanPelesenPasirAktif() {
		
		super.setFolderName("php2\\APB\\LAPORAN");
		super.setReportName("APBLaporanPelesenPasirAktif");
		
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
	}
}
