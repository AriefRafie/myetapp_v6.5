package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPrmhnnSek8SenaraiSemakData {
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void semakanAdd(String idsemakan, String idListSemakan, String txtbox, String txtbox2, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    String sql = "INSERT INTO Tblsemakanhantar " +
	    		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, dokumen_lain, tarikh_pelbagai, tarikh_masuk)  " +
	    		"VALUES ("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', '"+txtbox2+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	    String sqlbayaran ="";
	    String sqlM ="";
	    try {

          //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
	      long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
	      int idPermohonan = Integer.parseInt(idListSemakan);
	      int idSemakan = Integer.parseInt(idsemakan);
	      String tarikh_resit = tarikhresit;	
		  String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		  
		  //int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Semakanhantar", idSemakanhantar);
	      r.add("id_semakansenarai", idSemakan);
	      r.add("id_permohonan", idPermohonan);
	      r.add("catatan", txtbox);
	      r.add("dokumen_lain", txtbox2);
	      r.add("tarikh_pelbagai", tarikhd_resit);
	      r.add("tarikh_masuk",r.unquote("sysdate")); 
	      //sql = r.getSQLInsert("tblsemakanhantar");
	      stmt.executeUpdate(sql);
	      
	      if (idsemakan.equals("9")) {
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      SQLRenderer r1 = new SQLRenderer();
	      r1.add("id_bayaran", idBayaran);
	      r1.add("id_permohonan", idPermohonan);
	      r1.add("id_jenisbayaran",2);
	      r1.add("no_resit",txtbox);
	      r1.add("tarikh_bayaran",r.unquote(tarikhd_resit));
	      r1.add("jumlah_bayaran",10);
	      r1.add("tarikh_masuk",r.unquote("sysdate")); 
	      sqlbayaran = r1.getSQLInsert("tblppkbayaran");
	      System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtA.executeUpdate(sqlbayaran);
	      }
	      
	    /*  
	      String bukti="";
	      if(buktimati=="5")
	      {
	    	  bukti="1";  
	    	  
	      }
	      else if(buktimati=="6")
	      {
	    	  
	    	  bukti="2";  
	      }
	      else if(buktimati=="7")
	      {
	    	  
	    	  bukti="3";  
	      }
	      else if(buktimati=="8")
	      {
	    	  bukti="4";  
	    	  
	      }
	      */
	      db = new Db();
		  Statement stmtM = db.getStatement();
		  SQLRenderer rM = new SQLRenderer();
		  
		  rM.update("id_permohonan", idPermohonan);
		  
		  rM.add("no_Sijil_Mati",txtbox);
		  rM.add("id_Buktimati",buktimati);
		  
		  sqlM = rM.getSQLUpdate("tblppksimati");
	      stmtM.executeUpdate(sqlM);
	     
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public static void semakanDelete(String idListSemakan) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    try {
		  int idPermohonan = Integer.parseInt(idListSemakan);
		  //int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_permohonan", idPermohonan);
	      sql1 = r.getSQLDelete("tblsemakanhantar");
	      stmt.executeUpdate(sql1);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void semakanUpdate(String idsemakan, String idListSemakan, String txtbox, String txtbox2, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    String sqlM = "";
	    String sql = "INSERT INTO Tblsemakanhantar " +
		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, dokumen_lain, tarikh_pelbagai, tarikh_masuk)  " +
		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', '"+txtbox2+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	        //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
		  int idPermohonan = Integer.parseInt(idListSemakan);
		  int idSemakan = Integer.parseInt(idsemakan);
	      String tarikh_resit = tarikhresit;	
		  String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		        
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	      
	      
	      
	      String bukti="";
	      if(buktimati=="5")
	      {
	    	  bukti="1";  
	    	  
	      }
	      else if(buktimati=="6")
	      {
	    	  
	    	  bukti="2";  
	      }
	      else if(buktimati=="7")
	      {
	    	  
	    	  bukti="3";  
	      }
	      else if(buktimati=="8")
	      {
	    	  bukti="4";  
	    	  
	      }
	      
	      db = new Db();
		  Statement stmtM = db.getStatement();
		  SQLRenderer rM = new SQLRenderer();
		  
		  rM.update("id_permohonan", idPermohonan);
		  
		  rM.add("no_Sijil_Mati",txtbox);
		  rM.add("id_Buktimati",bukti);
		  
		  sqlM = rM.getSQLUpdate("tblppksimati");
	      stmtM.executeUpdate(sqlM);
	      
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	private static Vector listSemakan = new Vector();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void setDataSemakan(long idPermohonan) throws Exception {
		Db db = null;
		listSemakan.clear();
		int id = (int)idPermohonan;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("id_semakansenarai");
		r.add("catatan");
		r.add("tarikh_pelbagai");
	
		r.add("id_Permohonan",id);
		
		sql = r.getSQLSelect("tblsemakanhantar");
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		Integer count = 0;
		while(rs.next()) {
			h = new Hashtable();
			h.put("idsemakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
			h.put("Catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("tarikh_pelbagai", rs.getString("tarikh_pelbagai")==null?"":sdf.format(rs.getDate("tarikh_pelbagai")));
			listSemakan.addElement(h);
			count ++;
		}
		 if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("idsemakansenarai","");
			  h.put("Catatan", "");
			  h.put("tarikh_pelbagai", "");
			  listSemakan.addElement(h);
	      }
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public static Vector getListSemakan(){
		  return listSemakan;
	  }
	private static Vector IdNegUser = new Vector();
	
	public void getIdNegeriUser(String idUser) throws Exception {
		Db db = null;
		IdNegUser.clear();
		int userid = Integer.parseInt(idUser);
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("id_negeri");
		
		r.add("user_id",userid);
		
		sql = r.getSQLSelect("users_internal");
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		while(rs.next()) {
			h = new Hashtable();
			h.put("negeriId", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			IdNegUser.addElement(h);
		}
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public static Vector getIdNeg(){
		  return IdNegUser;
	  }
}
