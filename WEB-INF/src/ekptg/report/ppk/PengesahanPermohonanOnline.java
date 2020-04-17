package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import ekptg.helpers.Utils;
import ekptg.report.EkptgReportServlet;

public class PengesahanPermohonanOnline extends EkptgReportServlet {
	
	public PengesahanPermohonanOnline() {
		super.setReportName("PengesahanPermohonanOnline");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		//Get id fail based on no permohonan
		parameters.put("idfail",getID_Fail(request.getParameter("NoPermohonan")));
		
	}
	
	public String getID_Fail(String no_permohonan_online) throws Exception {
		
		Db db = null;
		String sql = "";
		String idfail="0";
		try {
			db = new Db();
			sql = "SELECT id_fail FROM tblppkpermohonan WHERE no_permohonan_online = '"+no_permohonan_online+"'";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idfail = Utils.isNull(rs.getString("id_fail"));
			}
		}finally {
			if (db != null)
				db.close();
		}
		return idfail;
	}
	
}