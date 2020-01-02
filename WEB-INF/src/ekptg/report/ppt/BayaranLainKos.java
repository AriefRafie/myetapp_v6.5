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

public class BayaranLainKos extends EkptgReportServlet {
	

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		
		
		

		String idfail = "";
		String id_hakmilik = "";
		String id_negeri = "";
		String id_permohonan = "";
		Vector maklumat_negeri_fail_panggil = null;
		
		System.out.println("id_negeri borang E :"+parameters.get("id_borange"));
		System.out.println("id_hakmilik borang E :"+parameters.get("idHakmilik"));
		System.out.println("id_permohonan borang E :"+parameters.get("id_permohonan"));
		
		
		if (parameters.get("id_hakmilik") != null){
			id_hakmilik = (String)parameters.get("id_hakmilik");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(id_hakmilik);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}	
		
		
		System.out.println("id_negeri report :"+id_negeri);
		
		if(id_negeri.equals("14"))
		{
			super.setReportName("Bayaran Lain-Lain Kos KL");
		}
		
		else
		{
			super.setReportName("Bayaran Lain-Lain Kos");
		}
		
       	
        
		super.setFolderName("ppt");
		
		
		
	}
	
	Vector maklumat_negeri_fail = null;
	public Vector maklumat_negeri_fail(String id_hakmilik) throws Exception {
		maklumat_negeri_fail = new Vector();
		Db db = null;
		maklumat_negeri_fail.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT F.ID_NEGERI AS ID_NEGERI FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTHAKMILIK H "+
					" WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = H.ID_PERMOHONAN AND H.ID_HAKMILIK = '"+id_hakmilik+"' ";
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