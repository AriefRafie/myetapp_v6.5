package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.report.EkptgReportServlet;
import ekptg.view.ppt.FrmSek8Siasatan;

public class BorangH extends EkptgReportServlet {
	
	static Logger myLogger = Logger.getLogger(FrmSek8Siasatan.class);

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		String id_hakmilik = "";
		String id_negeri = "";
		Vector maklumat_negeri_fail_panggil = null;
		
		if (parameters.get("id_hakmilik") != null){
			id_hakmilik = (String)parameters.get("id_hakmilik");			
			maklumat_negeri_fail_panggil = maklumat_negeri_fail(id_hakmilik);			
			Hashtable h = (Hashtable) maklumat_negeri_fail_panggil.get(0);			
			id_negeri = (String)h.get("ID_NEGERI");
		}
		
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
		at.logActivity("","1",null,session,"UPD","CETAK BORANG H [NO. HAKMILIK : "+NO_HAKMILIK_temp+"] CETAKAN");
			
		} finally {
			if (db != null)
				db.close();
		}	
		
		
		
		
		
		
	//	if(id_negeri.equals("10"))
	//	{
			super.setReportName("Borang H_SELANGOR");
	//	}
	//	else if(id_negeri.equals("8"))
	//	{
	//		super.setReportName("Borang H_PERAK");
	//	}
	//	else
	//	{
	//		super.setReportName("Borang H");
	//	}
		
      
			

			Integer mula = 0;
			Integer akhir = 0;
			String send_conditon = " ";
			
			if (parameters.get("mula") != null && parameters.get("mula") != ""){
				mula = Integer.parseInt((String)parameters.get("mula"));
				//parameters.put("mula",mula);
			}
			
			if (parameters.get("akhir") != null && parameters.get("akhir")!=""){
				akhir = Integer.parseInt((String)parameters.get("akhir"));
				//parameters.put("akhir",akhir);
			}
			
			if(mula>0 && akhir>0)
			{
				send_conditon = "AND (R >= "+mula+" AND R <= "+akhir+")";
			}
			
			System.out.print("parameters.get(mula) :"+parameters.get("mula"));
			System.out.print("send_conditon :"+send_conditon);
			
			parameters.put("send_conditon",send_conditon);
        
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

			sql = "SELECT DISTINCT F.ID_NEGERI FROM TBLPFDFAIL F," +
					"TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H" +
					" WHERE P.ID_FAIL = F.ID_FAIL" +
					" AND P.ID_PERMOHONAN = H.ID_PERMOHONAN" +
					" AND H.ID_HAKMILIK = '"+id_hakmilik+"'";
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


