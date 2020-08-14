package ekptg.report.php2.online;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class PNWPengesahanPermohonanOnline extends EkptgReportServlet {

	public PNWPengesahanPermohonanOnline() {
		super.setReportName("PNWPengesahanPermohonanOnline");
		super.setFolderName("php2\\ONLINE");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
