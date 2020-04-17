package ekptg.report.online;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SalinanResitBayaran  extends EkptgReportServlet {
	public SalinanResitBayaran(){
		super.setReportName("draft_resit_fpx_salinan");
		super.setFolderName("ppt");

		Map parameters = new HashMap();
		
		super.setParameters(parameters);
	}
	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
