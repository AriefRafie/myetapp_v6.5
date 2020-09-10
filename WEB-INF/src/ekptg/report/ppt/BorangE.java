package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;

public class BorangE extends EkptgReportServlet {
	
	public BorangE() {
		
		super.setFolderName("ppt");
	}
	
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	
		String masa_siasatan = "";
	
		if (parameters.get("masa_siasatan") != null || parameters.get("masa_siasatan") != ""){
			masa_siasatan = (String)parameters.get("masa_siasatan");
		}
		else {
			masa_siasatan = null;
		}
		
		if(masa_siasatan.isEmpty()) {
			super.setReportName("Borang E");
		}
		
		else if(masa_siasatan != null || masa_siasatan != ""){
			super.setReportName("Borang E Perlis");
		}
	}

}


