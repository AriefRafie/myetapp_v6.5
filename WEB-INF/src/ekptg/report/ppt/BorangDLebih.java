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

public class BorangDLebih extends EkptgReportServlet {
	
	

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		
		
		
		String idfail = "";
		String id_negeri = "";
		Vector maklumat_negeri_fail_panggil = null;
		
		if (parameters.get("id_Fail") != null){
			idfail = (String)parameters.get("id_Fail");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(idfail);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}	
		
		
		if(id_negeri.equals("10"))
		{
			super.setReportName("Borang D_lebihDrpdSatuHakmilik");
		}
		/*else if(id_negeri.equals("11"))
		{
			super.setReportName("Borang D_PTG_TGANU_v6");
		}
		else if(id_negeri.equals("7"))
		{
			super.setReportName("Borang D_lebihDrpdSatuHakmilik_PTG Penang");
		}*/
		else
		{
			 super.setReportName("Borang D_lebihDrpdSatuHakmilik_PTG");
		}
		
        
		super.setFolderName("ppt");
		
		
		
		
		
	}
	
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
	
	

}