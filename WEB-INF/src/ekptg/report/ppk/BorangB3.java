package ekptg.report.ppk;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangB3 extends EkptgReportServlet {
	
	
	
	public BorangB3() {
		super.setReportName("BorangB2LAND");
		super.setFolderName("ppk");
		Map parameters = new HashMap();
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
	}
}