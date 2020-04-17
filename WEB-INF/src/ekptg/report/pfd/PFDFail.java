package ekptg.report.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.DbException;
import ekptg.report.EkptgReportServlet;

public class PFDFail extends EkptgReportServlet
{
	
	public PFDFail() {
		super.setReportName("PFD_KulitFail");
		super.setFolderName("pfd");
	}
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception, DbException {
		String KULITFAIL_ID = (String) parameters.get("KULITFAIL_ID");
		
		// check whether this ID exists
		String sql = "";
		Db db = new Db();
		Statement stmt = db.getStatement();
		ResultSet rs = null;
		sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = " + KULITFAIL_ID;
		rs = stmt.executeQuery(sql);
		if (!rs.next()) {
			KULITFAIL_ID = "";
		}
		rs.close();
		parameters.put("KULITFAIL_ID", KULITFAIL_ID);
	}
}