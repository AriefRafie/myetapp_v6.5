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

public class SuratIringanMMK extends EkptgReportServlet {
	
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		String id_negeri = "";
		Vector maklumat_negeri_fail_panggil = null;
		
		if (parameters.get("idFail") != null){
			idfail = (String)parameters.get("idFail");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(idfail);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}	
		
		
		if(id_negeri.equals("2")){
			super.setReportName("Surat iringan Deraf MMK(KEDAH)");
		}else if(id_negeri.equals("4")){
			super.setReportName("Surat iringan Deraf MMK_Melaka");
		}else if(id_negeri.equals("7")){
			super.setReportName("SuratIringanDerafMMK(PENANG)");
		}else if(id_negeri.equals("8")){
			super.setReportName("SuratIringanDerafMMK(PERAK)");
		}else{
			super.setReportName("Surat iringan Deraf MMK_v6");
		}
		
       	
        
		super.setFolderName("ppt");
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	Vector maklumat_negeri_fail = null;
	@SuppressWarnings("unchecked")
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



