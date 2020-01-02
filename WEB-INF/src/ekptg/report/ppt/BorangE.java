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
		
		
		if (parameters.get("id_permohonan") != null){
			id_permohonan = (String)parameters.get("id_permohonan");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(id_permohonan);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}	
		
		
		System.out.println("id_negeri report :"+id_negeri);
		
		if(id_negeri.equals("10"))
		{
			super.setReportName("Borang E");
		}
		else if(id_negeri.equals("9"))
		{
			super.setReportName("Borang E Perlis");
		}
		else if(id_negeri.equals("14"))
		{
			super.setReportName("Borang E KL");
		}
		else if(id_negeri.equals("7"))
		{
			super.setReportName("Borang E Penang");
		}
		else
		{
			super.setReportName("Borang E");
		}
		
       	
        
		super.setFolderName("ppt");
	}
	
	
	
	
	Vector maklumat_negeri_fail = null;
	public Vector maklumat_negeri_fail(String id_permohonan) throws Exception {
		maklumat_negeri_fail = new Vector();
		Db db = null;
		maklumat_negeri_fail.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT F.ID_NEGERI AS ID_NEGERI FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F "+
					" WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = '"+id_permohonan+"'";
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


