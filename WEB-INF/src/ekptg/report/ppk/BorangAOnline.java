package ekptg.report.ppk;

import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class BorangAOnline extends EkptgReportServlet {
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLogger = Logger.getLogger(BorangAOnline.class);
	public BorangAOnline() {
		super.setReportName("BorangAOnline_Malay");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idPermohonan = "";
		if (parameters.get("idPermohonan") != null){
			idPermohonan = parameters.get("idPermohonan").toString();
		}
		//Security Check
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_type = (String)session.getAttribute("_ekptg_user_type");
		//check if this user is valid for this permohonan
		if ("online".equals(user_type) && !isValidUser(user_id,idPermohonan)) {
			throw new Exception("You are not authorized to view this form");
		}	
		parameters.put("baluDuda",logic.getBaluDuda(idPermohonan));
		
	}
	
	
	public boolean isValidUser(String userid,String id_permohonan) throws Exception  {
		boolean output=false;
		String sql = "";
		Db db = null;
		try {
			db = new Db(); 
			sql = "Select count(*) as total from tblppkpermohonan where id_masuk='"+userid+"' " +
					"and id_permohonan='"+id_permohonan+"'";
			myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				if (rs.getInt("total") > 0) {
					output = true;
				}
				
			}
		} catch (Exception e) {
			throw new Exception ("error checking isValidUser :"+e.getMessage());
		}finally {
			if (db != null) db.close();
		}
		return output;		
	}
}