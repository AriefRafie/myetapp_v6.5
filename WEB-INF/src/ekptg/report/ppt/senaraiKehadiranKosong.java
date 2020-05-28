package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class senaraiKehadiranKosong extends EkptgReportServlet {
	

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {

			super.setReportName("SenaraiKehadiranSiasatanKosong");

		super.setFolderName("ppt");
	}

}


