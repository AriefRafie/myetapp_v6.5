package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class REVHapusKira extends EkptgReportServlet {

	public REVHapusKira() {
		super.setReportName("LaporanHapusKira");
		super.setFolderName("php2\\REV");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {


	}
}
