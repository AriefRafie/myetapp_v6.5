package ekptg.report.pfd;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import ekptg.report.EkptgReportServlet;

public class DaftarSuratKPTG extends EkptgReportServlet
{
	public void DaftarSurat() {
		//super.setReportName("rptNoFailTajukFail");
//		super.setReportName("PFD_DaftarSurat");
//		super.setFolderName("pfd");
		
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
	
		String Flag_dokumen = (String)parameters.get("Flag_dokumen");
		String DariTarikh =(String)parameters.get("DariTarikh");
		String HinggaTarikh =(String)parameters.get("HinggaTarikh");
		HttpSession session = request.getSession();
		String[] values_DariTarikh_bucket = DariTarikh.split("/");
		String DrDD = values_DariTarikh_bucket[0];
		String DrMM = values_DariTarikh_bucket[1];
		String DrYYYY = values_DariTarikh_bucket[2];
		String NDariTarikh = DrYYYY+DrMM+DrDD;
		
		String[] values_HinggaTarikh_bucket = HinggaTarikh.split("/");
		
		String HgDD = values_HinggaTarikh_bucket[0];
		String HgMM = values_HinggaTarikh_bucket[1];
		String HgYYYY = values_HinggaTarikh_bucket[2];		
		String NHinggaTarikh = HgYYYY+HgMM+HgDD;

		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String id_negeri = getid_negeri(user_id);
		String id_seksyen = getid_seksyen(user_id);
		 
		Integer INHinggaTarikh = Integer.parseInt(NHinggaTarikh);
		Integer INDariTarikh = Integer.parseInt(NDariTarikh);

		 parameters.put("DariTarikh",INDariTarikh);
		 parameters.put("HinggaTarikh", INHinggaTarikh);
		 parameters.put("id_negeri",id_negeri);
		 parameters.put("id_seksyen",id_seksyen);
		// parameters.put("user_id",user_id);
		if("1".equalsIgnoreCase(Flag_dokumen)) { 
		super.setReportName("PFD_DaftarSuratMasukKPTG");
		super.setFolderName("pfd");
		}else{
			super.setReportName("PFD_DaftarSuratKeluarKPTG");
			super.setFolderName("pfd");
		}

		//	      //TARIKH DAFTAR FAIL	      
//	      SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
//	      if (DariTarikh != null && HinggaTarikh != null ) {
//					 
//					 parameters.put("DariTarikh",sdf1.format(DariTarikh));
//					 parameters.put("HinggaTarikh",sdf1.format(HinggaTarikh));
//			}
//		
		
	}
	 public static String  getid_seksyen(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String id_seksyen = "";
		    
		    try {

				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "select id_seksyen from users_internal where user_id=" + id;
	      		
						      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  id_seksyen = rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen");

		      }
		      
		     
		      
		   
		      return id_seksyen;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	 
	 public static String  getid_negeri(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String id_negeri = "";
		    
		    try {

				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "select id_negeri from users_internal where user_id=" + id;
	      		
						      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  id_negeri = rs.getString("id_negeri")==null?"":rs.getString("id_negeri");

		      }
		      
		     
		      
		   
		      return id_negeri;
		    } finally {
		      if (db != null) db.close();
		    }
		}
}
