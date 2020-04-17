package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class suratKepadaAPSupayaMembayarPampasanTambahan_AP  extends EkptgReportServlet {
	public suratKepadaAPSupayaMembayarPampasanTambahan_AP() {
		super.setReportName("suratKepadaAPSupayaMembayarPampasanTambahan_AP");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
}
