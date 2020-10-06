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
	
		String masa_siasatan_awal = "";
	
		if (parameters.get("masa_siasatan_awal") != null || parameters.get("masa_siasatan_awal") != ""){
			masa_siasatan_awal = (String)parameters.get("masa_siasatan_awal");
		}
		else {
			masa_siasatan_awal = null;
		}
		
		if(masa_siasatan_awal.isEmpty()) {
			super.setReportName("Borang E");
		}
		
		else if(masa_siasatan_awal != null || masa_siasatan_awal != ""){
			super.setReportName("Borang E Perlis");
		}
	}

}


