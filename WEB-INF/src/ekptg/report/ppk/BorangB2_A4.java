package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangB2_A4 extends EkptgReportServlet{
	
	public BorangB2_A4() {
		super.setReportName("BorangB2_A4");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
		ServletContext context,Map<String, Object> parameters) throws Exception {
	}

}
