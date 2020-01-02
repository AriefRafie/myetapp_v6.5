package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MmkPenarikanBalikSelangor2 extends EkptgReportServlet{

	public MmkPenarikanBalikSelangor2() {
		super.setReportName("MMK_SELANGOR_PENARIKANBALIK_2");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
