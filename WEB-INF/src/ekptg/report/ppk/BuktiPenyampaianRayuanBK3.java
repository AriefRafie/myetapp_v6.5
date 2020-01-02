package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BuktiPenyampaianRayuanBK3 extends EkptgReportServlet {
	
	public BuktiPenyampaianRayuanBK3() {
		super.setReportName("BuktiPenyampaianRayuanBK3");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}