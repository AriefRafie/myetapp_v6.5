package ekptg.report.ppt;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class MmkPenarikanBalikKedah extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(MmkPenarikanBalikSelangor.class);
	
	
	public MmkPenarikanBalikKedah() {
		
		super.setReportName("MMK_KEDAH_PENARIKANBALIK");
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
		doVersioning("MMK_KEDAH_PENARIKANBALIK",idfail,nofail,flagVersion);
	}
	
	

	
	
}
