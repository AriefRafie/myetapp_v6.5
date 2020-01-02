package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangR extends EkptgReportServlet {		
	
	public BorangR() {
		super.setReportName("BorangR");
		super.setFolderName("ppk");

	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		//System.out.println(parameters.get("idfail"));
		parameters.put("idfail",parameters.get("idfail"));
				
	}
	
	
}