package ekptg.report.ppk;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangB1POT extends EkptgReportServlet {
	
	public BorangB1POT() {
		super.setReportName("BorangB1POT");
		super.setFolderName("ppk");
		Map parameters = new HashMap();
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {		
	}
}