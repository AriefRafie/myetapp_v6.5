package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.report.EkptgReportServlet;
import ekptg.view.ppt.FrmSek8Siasatan;

public class BorangG extends EkptgReportServlet {
	
	static Logger myLogger = Logger.getLogger(FrmSek8Siasatan.class);

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		HttpSession session = request.getSession();	
		
		
		Db db = null;
		String NO_HAKMILIK_temp = "";
		try {
		db = new Db();
		Statement stmt = db.getStatement();
		String sql = " SELECT NO_HAKMILIK FROM TBLPPTHAKMILIK " +
				"" +
				" WHERE ID_HAKMILIK = '"+parameters.get("id_hakmilik")+"'";			
		ResultSet rs = stmt.executeQuery(sql);	
		myLogger.info("SQL  :"+sql);
		while (rs.next()){				
			NO_HAKMILIK_temp = rs.getString("NO_HAKMILIK");				
	    }
	    AuditTrail at = new AuditTrail();
		at.logActivity("","1",null,session,"UPD","CETAK BORANG G [NO. HAKMILIK : "+NO_HAKMILIK_temp+"] CETAKAN");
			
		} finally {
			if (db != null)
				db.close();
		}	
		
		

		super.setReportName("Borang G_SELANGOR");

		super.setFolderName("ppt");
	}

}


