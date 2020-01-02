package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BuktiPenyampaianMT extends EkptgReportServlet {
	
	public BuktiPenyampaianMT() {
		super.setReportName("BuktiPenyampaianMT");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	/*	if(!String.valueOf(parameters.get("template")).equals("") || parameters.get("template") != null){
			super.setReportName(String.valueOf(parameters.get("template")));			
		}*/
		
	}
	
	
}