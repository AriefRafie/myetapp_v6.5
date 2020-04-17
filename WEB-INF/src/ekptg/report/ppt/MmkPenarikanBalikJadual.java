package ekptg.report.ppt;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class MmkPenarikanBalikJadual extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(MmkPenarikanBalikSelangor.class);
	
	
	public MmkPenarikanBalikJadual() {
		
		super.setReportName("MMK_KL_1");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
	

	
	
}
