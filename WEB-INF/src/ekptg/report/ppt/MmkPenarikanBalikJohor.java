package ekptg.report.ppt;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class MmkPenarikanBalikJohor extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(MmkPenarikanBalikSelangor.class);
	
	
	public MmkPenarikanBalikJohor() {
		
		super.setReportName("MMK_JOHOR_PENARIKANBALIK");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("id_fail") != null){
			idfail = (String)parameters.get("id_fail");
		}
		
		String nofail = "";
		if (parameters.get("no_fail") != null){
			nofail = (String)parameters.get("no_fail");
		}
		
		String flagVersion = (String)parameters.get("flagVersion");
		doVersioning("MMK_JOHOR_PENARIKANBALIK",idfail,nofail,flagVersion);
	}
	
	

	
	
}
