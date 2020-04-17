package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class PYWRekodPerjanjianPenyewaanAdaNoSiriMengikutBulan extends EkptgReportServlet {

	public PYWRekodPerjanjianPenyewaanAdaNoSiriMengikutBulan() {
		super.setReportName("PYWRekodPerjanjianPenyewaanAdaNoSiriMengikutBulan");
		super.setFolderName("php2\\PYW\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
