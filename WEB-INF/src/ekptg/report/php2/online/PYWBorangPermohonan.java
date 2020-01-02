package ekptg.report.php2.online;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class PYWBorangPermohonan extends EkptgReportServlet {

	public PYWBorangPermohonan() {
		super.setReportName("PYWBorangPermohonan");
		super.setFolderName("php2\\online");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
