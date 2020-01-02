package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangN_AP extends EkptgReportServlet {

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		/*
		String idFail = "";
		String id_negeri = "";
		Vector maklumat_negeri_fail_panggil = null;
		
		if (parameters.get("idFail") != null){
			idFail = (String)parameters.get("idFail");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(idFail);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}	*/
		

		super.setReportName("BorangN_AP");
		super.setFolderName("ppt");

	}
	/*
	Vector maklumat_negeri_fail = null;
	public Vector maklumat_negeri_fail(String id_fail) throws Exception {
		maklumat_negeri_fail = new Vector();
		Db db = null;
		maklumat_negeri_fail.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_NEGERI FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? ""
						: rs.getString("ID_NEGERI"));
				
				maklumat_negeri_fail.addElement(h);
			}
			return maklumat_negeri_fail;
		} finally {
			if (db != null)
				db.close();
		}
	}
	*/
	

}