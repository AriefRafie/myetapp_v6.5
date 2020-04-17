package ekptg.report.ppk;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangB1LAND extends EkptgReportServlet {
	
	public BorangB1LAND() {
		super.setReportName("BorangB1LAND");
		super.setFolderName("ppk");
		Map parameters = new HashMap();
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		
	}
}