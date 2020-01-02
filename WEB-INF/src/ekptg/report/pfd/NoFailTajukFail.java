package ekptg.report.pfd;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class NoFailTajukFail extends EkptgReportServlet
{
	
	public NoFailTajukFail() {
		super.setReportName("rptNoFailTajukFail");
		super.setFolderName("pfd");
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {

	}

}
