package ekptg.report.pfd;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class PergerakanFail extends EkptgReportServlet
{
	
	public PergerakanFail() {
		super.setReportName("PFD_SenaraiPergerakanFail");
		super.setFolderName("pfd");

	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		 String id_Fail =(String)parameters.get("id_Fail");
		 parameters.put("id_Fail",id_Fail);
	}

}
