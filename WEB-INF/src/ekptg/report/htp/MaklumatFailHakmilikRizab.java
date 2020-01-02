package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MaklumatFailHakmilikRizab extends EkptgReportServlet {
	
	public MaklumatFailHakmilikRizab() {
		super.setFolderName("htp");
		//super.setReportName("rptNoFailTajukFail");
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
		ServletContext context,Map<String, Object> parameters) throws Exception {
		//int bildok = Integer.parseInt((String)parameters.get("idfail"));
		String template =(String)parameters.get("template");
		String idHakmilik =(String)parameters.get("idHakmilik");
		
	
		//super.setFolderName("htp");
		super.setReportName(template);	
	
		parameters.put("idHakmilik",idHakmilik);	

	}
}