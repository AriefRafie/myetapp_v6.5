package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class NoFailTajukFail extends EkptgReportServlet{
	
	public NoFailTajukFail() {
		super.setFolderName("htp");
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
		ServletContext context,Map<String, Object> parameters) throws Exception {
		String idPermohonan =(String)parameters.get("idpermohonan");
		String template =(String)parameters.get("template");
		super.setReportName(template);	
	
		parameters.put("ID_PERMOHONAN",idPermohonan);

	}
	

}
