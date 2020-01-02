package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmDaftarLogDokumenTKPKData {

	private static Vector listLogDokumen = new Vector();

	
	public static void setCarianLogDokumenTKPK(String no_Rujukan_Lain,
			String tajuk_Dokumen, String tarikh_Dokumen, String tarikh_Diterima_Dihantar,
			String status_logdokumen, String flag_logdokumen, String user_id) throws Exception {
		  Db db = null;
		    listLogDokumen.clear();
		    String sql = "";
		    
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
	    
		      sql = "SELECT DISTINCT ID_LOGDOKUMENKPTG, NO_RUJUKAN, TAJUK_DOKUMEN, TARIKH_DOKUMEN, TARIKH_DITERIMA_DIHANTAR, FLAG_LOGDOKUMEN, STATUS_LOGDOKUMEN, ID_LOGDOKUMENKPTGSEKLAIN " +
		      		"FROM TBLPFDLOGDOKUMENKPTG WHERE NO_RUJUKAN LIKE '%' ||'" + no_Rujukan_Lain + "'|| '%'" +
		      		" and id_seksyen =(select id_seksyen from users_internal where user_id='"+user_id+"')" +
		      		" and id_negeri =(select id_negeri from users_internal where user_id='"+user_id+"')";;
		      
		      System.out.println("sql::"+sql);     

//		      if (no_Rujukan_Lain != null) {
//					if (!no_Rujukan_Lain.trim().equals("")) {
//						sql = sql + " AND NO_RUJUKAN LIKE '%' ||'" + no_Rujukan_Lain + "'|| '%'";
//					}
//				}
		      

		      if (tajuk_Dokumen != null) {
					if (!tajuk_Dokumen.trim().equals("")) {
						sql = sql + " AND UPPER(TAJUK_DOKUMEN) LIKE '%' ||'" + tajuk_Dokumen.toUpperCase() + "'|| '%'";
					}
				}

		     
		      if (tarikh_Dokumen != null) {
					if (!tarikh_Dokumen.toString().trim().equals("")) {			 
						sql = sql + " AND TO_CHAR(TARIKH_DOKUMEN,'DD/MM/YYYY') = '" + tarikh_Dokumen +"'";
						
					}
				}
		      
		      if (tarikh_Diterima_Dihantar != null) {
					if (!tarikh_Diterima_Dihantar.toString().trim().equals("")) {			 
						sql = sql + " AND TO_CHAR(TARIKH_DITERIMA_DIHANTAR,'DD/MM/YYYY') = '" + tarikh_Diterima_Dihantar +"'";
						
					}
				}
		      
		    
		      sql = sql + " ORDER BY ID_LOGDOKUMENKPTG DESC";
		      ResultSet rs = stmt.executeQuery(sql);
		    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumenKPTG",rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")== null? "":rs.getString("NO_RUJUKAN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")== null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("tarikh_Dokumen",rs.getDate("TARIKH_DOKUMEN")== null? "":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Diterima_Dihantar",rs.getDate("TARIKH_DITERIMA_DIHANTAR")== null? "":sdf.format(rs.getDate("TARIKH_DITERIMA_DIHANTAR")));
		    	  //h.put("tarikh_Dokumen_Dihantar",rs.getDate("TARIKH_DOKUMENDIHANTAR")== null? "":sdf.format(rs.getDate("TARIKH_DOKUMENDIHANTAR"))); 
		    	  h.put("status_logdokumen",rs.getString("STATUS_LOGDOKUMEN")== null? "":rs.getString("STATUS_LOGDOKUMEN"));
		    	  h.put("idLogDokumenKPTGSekLain",rs.getString("ID_LOGDOKUMENKPTGSEKLAIN")== null? "":rs.getString("ID_LOGDOKUMENKPTGSEKLAIN"));
		    	  h.put("flag_logdokumen",rs.getString("FLAG_LOGDOKUMEN")== null? "":rs.getString("FLAG_LOGDOKUMEN"));

		    	  listLogDokumen.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		     
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","Tiada rekod.");
		    	  h.put("idLogDokumenKPTG",0);
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_Diterima_Dihantar", "");
		    	  //h.put("tarikh_Dokumen_Dihantar", "");
		    	  h.put("flag_logdokumen", "");

		    	  listLogDokumen.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListLogDokumenTKPK() {
		// TODO Auto-generated method stub
		return listLogDokumen;
	}

	public static Vector getPARbyIdSeksyen(String socSeksyenPAR) throws Exception {
		Vector v = null;
		v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  

 	      sql = "select user_id,user_name from users where user_id in (select user_id from users_internal where id_seksyen ='"+socSeksyenPAR+"')";
 	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getString("user_id") == null ? "" : rs.getString("user_id"));
				h.put("NAMA_PEGAWAI", rs.getString("user_name") == null ? "" : rs.getString("user_name").toUpperCase());
				v.addElement(h);
			}
 
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
	}

	public static Object totalDokumen(HttpSession session) throws Exception {
		String userId = (String)session.getAttribute("_ekptg_user_id");
	  	String portal_role = (String)session.getAttribute("_portal_role");
	  	String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
	  	Db db = null; 
	  	int total = 0;
	  	String sql="";
	  	ResultSet rs = null;
		try { 
			//Open the database connection
			db = new Db(); 
			
			 if(user_negeri.equals("16"))
			 {
				 sql +=	"select count(*) from TBLPFDLOGDOKUMENKPTG where "+
				 		"id_seksyen =(select id_seksyen from users_internal where user_id='"+userId+"')"+
				 		" and id_negeri =(select id_negeri from users_internal where user_id='"+userId+"')";
			 }	  
			 else
			 {
				 sql +=	"select count(*) from tblpfdlogdokumen where "+
			 		" id_negeri =(select id_negeri from users_internal where user_id='"+userId+"')";
			 }
			 	
			
			//get some data 
			rs = db.getStatement().executeQuery(sql); 
		if ( rs.next() ) { 
			total = rs.getInt(1); 
		} 
		} finally { 
		//Close the database connection 
		if ( db != null ) db.close(); 
		if (rs != null) rs.close();			
		} 
		return total;
	}

}
