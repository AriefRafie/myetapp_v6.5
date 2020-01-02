package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

public class FrmDaftarLogDokumenData {

	private static Vector listLogDokumen = new Vector();
	
	public static void setCarianLogDokumen(String no_Rujukan_Lain,
			String tajuk_Dokumen, String tarikh_Dokumen,
			String tarikh_Dokumen_Diterima, String tarikh_Dokumen_Dihantar,
			String status_logdokumen, String flag_logdokumen, String user_id, String tag_Dokumen) throws Exception {
	    Db db = null;
	    listLogDokumen.clear();
	    String sql = "";
	    
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
    
	      sql = "SELECT DISTINCT A.ID_LOGDOKUMEN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN,B.TAG_DOKUMEN, A.TARIKH_DOKUMEN, A.TARIKH_DOKUMENDITERIMA, A.TARIKH_DOKUMENDIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN, A.TARIKH_MASUK " +
	      		"FROM TBLPFDLOGDOKUMEN A, TBLPFDTAGDOKUMEN B WHERE A.ID_LOGDOKUMEN = B.ID_DOKUMEN(+) AND A.NO_RUJUKAN LIKE '%' ||'" + no_Rujukan_Lain + "'|| '%'" +
	      		" and A.id_seksyen =(select id_seksyen from users_internal where user_id='"+user_id+"')" +
	      		" and A.id_negeri =(select id_negeri from users_internal where user_id='"+user_id+"')";;
	      
	      System.out.println("sql::"+sql);     

	      boolean setLimit = true;

	      if (no_Rujukan_Lain != null) {
				if (!no_Rujukan_Lain.trim().equals("")) {
			    	setLimit = false;
					sql = sql + " AND UPPER(A.NO_RUJUKAN) LIKE '%' ||'" + no_Rujukan_Lain.toUpperCase() + "'|| '%'";
				}
			}
	      
	      if (tajuk_Dokumen != null) {
				if (!tajuk_Dokumen.trim().equals("")) {
			    	setLimit = false;
					sql = sql + " AND UPPER(A.TAJUK_DOKUMEN) LIKE '%' ||'" + tajuk_Dokumen.toUpperCase() + "'|| '%'";
				}
			}
	      
	      if (tag_Dokumen != null) {
				if (!tag_Dokumen.trim().equals("")) {
			    	setLimit = false;
					sql = sql + " AND UPPER(B.TAG_DOKUMEN) LIKE '%' ||'" + tag_Dokumen.toUpperCase() + "'|| '%'";
				}
			}
	     
	      if (tarikh_Dokumen != null) {
				if (!tarikh_Dokumen.toString().trim().equals("")) {		
			    	setLimit = false;
					sql = sql + " AND TO_CHAR(A.TARIKH_DOKUMEN,'DD/MM/YYYY') = '" + tarikh_Dokumen +"'";
					
				}
			}
	      
	      if (tarikh_Dokumen_Diterima != null) {
				if (!tarikh_Dokumen_Diterima.toString().trim().equals("")) {
			    	setLimit = false;
					sql = sql + " AND TO_CHAR(A.TARIKH_DOKUMENDITERIMA,'DD/MM/YYYY') = '" + tarikh_Dokumen_Diterima +"'";
					
				}
			}
	      
	      if (tarikh_Dokumen_Dihantar != null) {
				if (!tarikh_Dokumen_Dihantar.toString().trim().equals("")) {		
			    	setLimit = false;
					sql = sql + " AND TO_CHAR(A.TARIKH_DOKUMENDIHANTAR,'DD/MM/YYYY') = '" + tarikh_Dokumen_Dihantar +"'";
					
				}
			}
	      
//			if (setLimit) {	//RESERVED BY AZAM
//				System.out.println("set LIMIT " +setLimit);	
//					sql = sql + " AND ROWNUM <= 50 ";				
//				
//			}
	      
	      sql = sql + " ORDER BY A.TARIKH_MASUK DESC";
	   
	      ResultSet rs = stmt.executeQuery(sql);
	    
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idLogDokumen",rs.getString("ID_LOGDOKUMEN"));
	    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")== null? "":rs.getString("NO_RUJUKAN"));
	    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")== null?"":rs.getString("TAJUK_DOKUMEN"));
	    	  h.put("tarikh_Dokumen",rs.getDate("TARIKH_DOKUMEN")== null? "":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
	    	  h.put("tarikh_Dokumen_Diterima",rs.getDate("TARIKH_DOKUMENDITERIMA")== null? "":sdf.format(rs.getDate("TARIKH_DOKUMENDITERIMA")));
	    	  h.put("tarikh_Dokumen_Dihantar",rs.getDate("TARIKH_DOKUMENDIHANTAR")== null? "":sdf.format(rs.getDate("TARIKH_DOKUMENDIHANTAR"))); 
	    	  h.put("status_logdokumen",rs.getString("STATUS_LOGDOKUMEN")== null? "":rs.getString("STATUS_LOGDOKUMEN"));
	    	  h.put("flag_logdokumen",rs.getString("FLAG_LOGDOKUMEN")== null? "":rs.getString("FLAG_LOGDOKUMEN"));
	    	  h.put("tag_dokumen",rs.getString("TAG_DOKUMEN")== null? "":rs.getString("TAG_DOKUMEN"));
	    	  listLogDokumen.addElement(h);	
//			if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
//							
//			}
//	    	  
	    	  
	    	  bil++;
	    	  count++;
	    	  
	      }
	     
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("idLogDokumen",0);
	    	  h.put("no_Rujukan_Lain", "Tiada rekod.");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen", "");
	    	  h.put("tarikh_Dokumen_Diterima", "");
	    	  h.put("tarikh_Dokumen_Dihantar", "");
	    	  h.put("flag_logdokumen", "");

	    	  listLogDokumen.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListLogDokumen() {
		// TODO Auto-generated method stub
		return listLogDokumen;
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
				 sql +=	"select count(*) from tblpfdlogdokumen where "+
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
