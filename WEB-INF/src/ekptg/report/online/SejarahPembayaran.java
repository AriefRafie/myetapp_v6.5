package ekptg.report.online;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SejarahPembayaran extends EkptgReportServlet{
	
	HttpServletRequest request;
	public SejarahPembayaran(){
		super.setReportName("rptSejarahPembayaran");
		super.setFolderName("online");

		Map parameters = new HashMap();
		super.setParameters(parameters);
	}
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
	}

}
