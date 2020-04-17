package ekptg.model.pfd;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmSenaraiTugasanTKPK {
	
	private static Vector listLogDokumenByUserId = new Vector();
	private static Vector listAgihanTugasanById = new Vector();

	public static void setListLogDokumenByUserId(String user_id) throws Exception {
		 Db db = null;
		    listLogDokumenByUserId.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_LOGDOKUMENKPTG, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DITERIMA_DIHANTAR, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_TKP, a.id_LogDokumenKPTGSekLain " +
		      		"FROM TBLPFDLOGDOKUMENKPTG a, USERS b " +
		      		"where a.ID_PTFAIL = b.USER_ID " +
		      		"and a.STATUS_LOGDOKUMEN = '0' " +
		      		"and a.id_ptfail = '"+user_id+"'";
		      sql = sql + "ORDER BY ID_LOGDOKUMENKPTG DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumenKPTG",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_Diterima_Dihantar",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_TKP", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("idLogDokumenKPTGSekLain", rs.getString(9)==null?"":rs.getString(9));

		    	  listLogDokumenByUserId.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumenKPTG","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_Diterima_Dihantar","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_TKP", "");
		    	  h.put("idLogDokumenKPTGSekLain", "");

		    	  listLogDokumenByUserId.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getListLogDokumen() {
		// TODO Auto-generated method stub
		return listLogDokumenByUserId;
	}

	public static void setListLogDokumenByUserIdSelesai(String user_id) throws Exception {
		 Db db = null;
		    listLogDokumenByUserId.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_LOGDOKUMENKPTG, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DITERIMA_DIHANTAR, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_TKP, a.id_LogDokumenKPTGSekLain " +
		      		"FROM TBLPFDLOGDOKUMENKPTG a, USERS b " +
		      		"where a.ID_PTFAIL = b.USER_ID " +
		      		"and a.STATUS_LOGDOKUMEN = '0' " +
		      		"and a.id_ptfail = '"+user_id+"'";
		      sql = sql + "ORDER BY ID_LOGDOKUMENKPTG DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumenKPTG",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_Diterima_Dihantar",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_TKP", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("idLogDokumenKPTGSekLain", rs.getString(9)==null?"":rs.getString(9));

		    	  listLogDokumenByUserId.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumenKPTG","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_Diterima_Dihantar","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_TKP", "");
		    	  h.put("idLogDokumenKPTGSekLain", "");

		    	  listLogDokumenByUserId.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getListLogDokumenSelesai() {
		// TODO Auto-generated method stub
		return listLogDokumenByUserId;
	}

	public static void setListAgihanTugasan(String user_id) throws Exception {
		 Db db = null;
		    listAgihanTugasanById.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_LOGDOKUMENKPTG, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DITERIMA_DIHANTAR, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_TKP, a.PENGIRIM_MASUK, a.ID_LOGDOKUMENKPTGSEKLAIN " +
	      		"FROM TBLPFDLOGDOKUMENKPTG a, USERS b " +
	      		"where a.ID_TKP = b.USER_ID " +
	      		"and a.STATUS_LOGDOKUMEN = '1' " +
	      		"and a.id_TKP = "+user_id+" ";
		      sql = sql + "ORDER BY ID_LOGDOKUMENKPTG DESC";
		      
//		      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
//	    		"FROM TBLPFDDOKUMEN a, USERS b " +
//	    		"where a.ID_NAMAPENERIMA = b.USER_ID " +
//	    		//"and a.ID_NAMAPENERIMA = "+user_id+"" +
//	    		"and a.ID_SETIAUSAHA = "+user_id+"";
//		      sql = sql + "ORDER BY ID_DOKUMEN DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumenKPTG",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_Diterima_Dihantar",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_TKP", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("nama_Pengirim", rs.getString(9)==null?"":rs.getString(9));
		    	  h.put("idLogDokumenKPTGSekLain",rs.getString(10)==null?"":rs.getString(10));

		    	  listAgihanTugasanById.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumenKPTG","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_Diterima_Dihantar","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_TKP", "");
		    	  h.put("nama_Pengirim","");
		    	  h.put("idLogDokumenKPTGSekLain","");

		    	  listAgihanTugasanById.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListAgihanTugasan() {
		// TODO Auto-generated method stub
		return listAgihanTugasanById;
	}
	
	public static void setListAgihanTugasanSelesai(String user_id) throws Exception {
		 Db db = null;
		    listAgihanTugasanById.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_LOGDOKUMENKPTG, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DITERIMA_DIHANTAR, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_TKP, a.PENGIRIM_MASUK, a.ID_LOGDOKUMENKPTGSEKLAIN " +
	      		"FROM TBLPFDLOGDOKUMENKPTG a, USERS b " +
	      		"where a.ID_TKP = b.USER_ID " +
	      		"and a.STATUS_LOGDOKUMEN = '0' " +
	      		"and a.id_TKP = "+user_id+" ";
		      sql = sql + "ORDER BY ID_LOGDOKUMENKPTG DESC";
		      
//		      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
//	    		"FROM TBLPFDDOKUMEN a, USERS b " +
//	    		"where a.ID_NAMAPENERIMA = b.USER_ID " +
//	    		//"and a.ID_NAMAPENERIMA = "+user_id+"" +
//	    		"and a.ID_SETIAUSAHA = "+user_id+"";
//		      sql = sql + "ORDER BY ID_DOKUMEN DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumenKPTG",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_Diterima_Dihantar",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_TKP", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("nama_Pengirim", rs.getString(9)==null?"":rs.getString(9));
		    	  h.put("idLogDokumenKPTGSekLain",rs.getString(10)==null?"":rs.getString(10));

		    	  listAgihanTugasanById.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumenKPTG","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_Diterima_Dihantar","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_TKP", "");
		    	  h.put("nama_Pengirim","");
		    	  h.put("idLogDokumenKPTGSekLain","");

		    	  listAgihanTugasanById.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListAgihanTugasanSelesai() {
		// TODO Auto-generated method stub
		return listAgihanTugasanById;
	}


}

