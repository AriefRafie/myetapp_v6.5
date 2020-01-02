
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPrmhnnSek8SenaraiSemakOnlineData {
	FrmPrmhnnSek8InternalData logic = new FrmPrmhnnSek8InternalData();
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void semakanAdd(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    String sql = "INSERT INTO Tblsemakanhantar " +
	    		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
	    		"VALUES ("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	    //System.out.println("SQL SEMAKKKK"+sql);
	    
	    String sqlbayaran ="";
	    String sqlM ="";
	    try {

          //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
	      long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
	      String idPermohonan = idListSemakan;
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
	      r.add("tarikh_pelbagai", tarikhd_resit);
	      r.add("tarikh_masuk",r.unquote("sysdate")); 
	      //sql = r.getSQLInsert("tblsemakanhantar");
	      stmt.executeUpdate(sql);
	     
	      
	      
	      
	      /*
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
	      //r1.add("id_masuk",6); 
	      r1.add("tarikh_masuk",r.unquote("sysdate")); 
	      sqlbayaran = r1.getSQLInsert("tblppkbayaran");
	      ////System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtA.executeUpdate(sqlbayaran);
	      }
	      
	      */
	      
	  
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void update_bukti(String id_permohonan, String usid, String sijil, String buktimati) throws Exception {
	    Db db = null;
	    String bukti = "";
	    
	 
	    
	    
	    String sql = "UPDATE TBlPPKSIMATI SET ID_KEMASKINI = '"+usid+"' , TARIKH_KEMASKINI = SYSDATE, NO_SIJIL_MATI = '"+sijil+"', ID_BUKTIMATI = '"+buktimati+"' WHERE ID_SIMATI IN (SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI PS, TBLPPKPERMOHONAN PM, TBLPPKSIMATI SM " +
	    		"WHERE PS.ID_PERMOHONAN = PM.ID_PERMOHONAN AND PS.ID_SIMATI = SM.ID_SIMATI AND PM.ID_PERMOHONAN = '"+id_permohonan+"')";
	    
	    ////System.out.println("SQL BUKTI :"+sql);
	    		  try {
	     
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	      
	     
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void semakanDelete(String idListSemakan) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    try {
		  //String idPermohonan = Integer.parseInt(idListSemakan);
		  String idPermohonan = idListSemakan;
		  //int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_permohonan", idPermohonan);
	     // sql1 = r.getSQLDelete("tblsemakanhantar");
	      
	     sql1 = "DELETE FROM tblsemakanhantar WHERE id_permohonan = '"+idPermohonan+"' "
	    + " AND ID_SEMAKANSENARAI < 25 ";
	      
	      
	      stmt.executeUpdate(sql1);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static void semakanUpdate(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String buktimati,String uid) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    String sqlM = "";
	    String sqlbayaran = "";
	    
	    
	    String sql = "INSERT INTO Tblsemakanhantar " +
		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk,id_masuk,id_kemaskini)  " +
		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate,'"+uid+"','"+uid+"') ";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      
	      System.out.println("idSemakanhantar==="+idSemakanhantar);
	        //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
		  String idPermohonan = idListSemakan;
		  int idSemakan = Integer.parseInt(idsemakan);
	      String tarikh_resit = tarikhresit;	
		  String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		        
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	      
	      
	      FrmPrmhnnSek8OnlineData f1=new FrmPrmhnnSek8OnlineData();
	      
	      Vector listSimati1=new Vector();
	      f1.setDataSimati(idPermohonan);
	  	  listSimati1 = f1.getDataSimati();
	  	  
	  /*	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	//  	int inpppp = Integer.parseInt(idPermohonan);
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  	
	      Statement stmtL = db.getStatement();
	      SQLRenderer r9 = new SQLRenderer();	      
	      r9.add("no_resit",12333131);
	      String tr = "to_date('" + tarikhresit + "','dd/MM/yyyy')";
	      r9.add("tarikh_bayaran",r9.unquote(tr));	    
		  r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	      r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	      r9.update("id_permohonan", idPermohonan);
	      sqlbayaran = r9.getSQLUpdate("tblppkbayaran");
	      
	      ////System.out.print("sqlbayaran :"+sqlbayaran);
	      stmtL.executeUpdate(sqlbayaran);
	  	}*/
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void semakanUpdateBayar(String idPermohonan, String resit, String tarikh, String uid) throws Exception {
		{
			
			 Db db = null;  
			 String sqlbayaran = "";
			    try{
			    	
					   
			    	 db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  	////System.out.println("txtbox"+resit);
	  	////System.out.println("tarikhresit"+tarikh);
	      Statement stmtL = db.getStatement();
	      SQLRenderer r9 = new SQLRenderer();
	    //  r9.add("id_bayaran", idBayaran);
	      r9.update("id_permohonan", idPermohonan);
	    //  r9.add("id_jenisbayaran",2);
	      r9.add("no_resit",resit);
	      String tr = "to_date('" + tarikh + "','dd/MM/yyyy')";
	      r9.add("tarikh_bayaran",r9.unquote(tr));
	      
	      if(resit!=""){
	      r9.add("jumlah_bayaran",10);
	      }
	      else
	      {
	    	  r9.add("jumlah_bayaran",0);  
	      }
	      //r1.add("id_masuk",6); 
	      
	    //  r9.add("ID_MASUK",userId);
	      r9.add("ID_KEMASKINI",uid);
		  r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	      r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	      sqlbayaran = r9.getSQLUpdate("tblppkbayaran");
	     ////System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtL.executeUpdate(sqlbayaran);
	      
	      
	  	}
	  	
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	
	private static Vector listSemakanO = new Vector();
	
	public void setDataSemakanO(String idPermohonan) throws Exception {
		Db db = null;
		listSemakanO.clear();
		String id = idPermohonan;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	        r.add("a.no_Permohonan_Online");	
		    r.add("a.id_Permohonan",id);
		    sql = r.getSQLSelect("Tblppkpermohonan a");
		
	
		    ResultSet rs = stmt.executeQuery(sql);
		
		    Hashtable h;
	
		    while(rs.next()) {
			h = new Hashtable();
			h.put("no_online", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
			listSemakanO.addElement(h);
		
		}		
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public static Vector getListSemakanO(){
		  return listSemakanO;
	  }
	
	
	
	private static Vector listSemakan = new Vector();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
	public void setDataSemakan(String idPermohonan) throws Exception {
		Db db = null;
		listSemakan.clear();
		String id = idPermohonan;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("s.id_semakansenarai");
		r.add("s.catatan");
		r.add("s.tarikh_pelbagai");
		 r.add("p.no_Permohonan_Online");
		 
		r.add("s.id_Permohonan",id);
		r.add("p.id_Permohonan",r.unquote("s.id_Permohonan"));
		
		sql = r.getSQLSelect("tblsemakanhantar s,tblppkpermohonan p");
		
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		Integer count = 0;
		while(rs.next()) {
			h = new Hashtable();
			h.put("idsemakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
			h.put("Catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("tarikh_pelbagai", rs.getString("tarikh_pelbagai")==null?"":sdf.format(rs.getDate("tarikh_pelbagai")));
			h.put("no_online", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
			
			listSemakan.addElement(h);
			count ++;
		}
		/* if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("idsemakansenarai","");
			  h.put("Catatan", "");
			  h.put("tarikh_pelbagai", "");
			  listSemakan.addElement(h);
	      }
	      */
		}
		finally {
			if(db != null) db.close();
		}	
	}
	public static Vector getListSemakan(){
		  return listSemakan;
	  }
	
	
	
	private static Vector listSemakanSimati=new Vector();
	
	
	public void setDataSemakanSimati(String idPermohonan) throws Exception {
		Db db = null;
		listSemakanSimati.clear();
		String id = idPermohonan;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("ts.id_semakansenarai");
		r.add("ts.catatan");
		r.add("ts.tarikh_pelbagai");
		r.add("ms.id_Simati");
		
		r.add("p.id_Permohonan",id);
		r.add("p.id_Permohonan",r.unquote("ts.id_Permohonan"));
		r.add("ts.id_Permohonan",r.unquote("ms.id_Permohonan"));
	
		
		sql = r.getSQLSelect("tblsemakanhantar ts, tblppkpermohonansimati ms, tblppkpermohonan p");
		
		
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		Integer count = 0;
		while(rs.next()) {
			h = new Hashtable();
			h.put("idsemakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
			h.put("Catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("id_Simati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
			h.put("tarikh_pelbagai", rs.getString("tarikh_pelbagai")==null?"":sdf.format(rs.getDate("tarikh_pelbagai")));
			listSemakanSimati.addElement(h);
			count ++;
		}
		 if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("idsemakansenarai","");
			  h.put("Catatan", "");
			  h.put("id_Simati", "");
			  h.put("tarikh_pelbagai", "");
			  listSemakanSimati.addElement(h);
	      }
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public static Vector getListSemakanSimati(){
		  return listSemakanSimati;
	  }
	
	public Vector check_pilihan_ha(String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			////System.out.println("PILIHAN CHECK LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanha", rs.getInt("ID_PILIHANHA"));
				h.put("id_ha", rs.getString("ID_HA"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector check_pilihan(String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			////System.out.println("PILIHAN CHECK LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanhta", rs.getInt("ID_PILIHANHTA"));
				h.put("id_hta", rs.getString("ID_HTA"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public  void pilihanAdd_ha(String id_per_mati,String id_ha, String uid) throws Exception {
	    Db db = null;
	    long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	    String sql = "INSERT INTO TBLPPKPILIHANHA " +
	    		"(id_Pilihanha, id_permohonansimati,id_ha,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
	    		"VALUES ("+id_Pilihanhta+","+id_per_mati+","+id_ha+",'"+uid+"',sysdate,'"+uid+"', sysdate)";
	    ////System.out.println("SQL pilihanAdd"+sql.toUpperCase());
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void pilihanAdd(String id_per_mati,String id_hta, String uid) throws Exception {
	    Db db = null;
	    long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	    String sql = "INSERT INTO TBLPPKPILIHANHTA " +
	    		"(id_Pilihanhta, id_permohonansimati,id_hta,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
	    		"VALUES ("+id_Pilihanhta+","+id_per_mati+","+id_hta+",'"+uid+"',sysdate,'"+uid+"', sysdate)";
	    ////System.out.println("SQL pilihanAdd"+sql.toUpperCase());
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void pilihanDelete(String id_per_mati,String id_hta, String uid) throws Exception {
	    Db db = null;
	 //   long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();
	    	sql = "delete from tblppkpilihanhta where id_permohonansimati = " + id_per_mati
			+ " and id_hta not in " + id_hta + "";
	    	stmtL.executeUpdate(sql);
	      ////System.out.println("SQL pilihanDelete"+sql.toUpperCase());
		    
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public Vector pilihanList_ha(String idha, String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' AND ID_HA = '"+idha+"'";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			////System.out.println("PILIHAN LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanha", rs.getInt("ID_PILIHANHA"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector pilihanList(String idhta, String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' AND ID_HTA = '"+idhta+"'";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			////System.out.println("PILIHAN LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanhta", rs.getInt("ID_PILIHANHTA"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	private static Vector IdNegUser = new Vector();
	
	public void getIdNegeriUser(String idUser) throws Exception {
		Db db = null;
		IdNegUser.clear();
		//int userid = Integer.parseInt(idUser);
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("id_negeri");
		r.add("user_id",idUser);
		//r.add("user_id",userid);
		
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



/*
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPrmhnnSek8SenaraiSemakInternalData {
	
//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void semakanAdd(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    String sql = "INSERT INTO Tblsemakanhantar " +
	    		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
	    		"VALUES ("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
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
	      ////System.out.println("sqlbayaran-->"+sqlbayaran);
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
	
	public static void semakanUpdate(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    String sqlM = "";
	    String sql = "INSERT INTO Tblsemakanhantar " +
		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
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
		
		r.add("s.id_semakansenarai");
		r.add("s.catatan");
		r.add("s.tarikh_pelbagai");
		r.add("a.no_Permohonan_Online");
	
		r.add("s.id_Permohonan",id);

		
		r.add("s.id_Permohonan",r.unquote("a.id_Permohonan(+)"));
		
		sql = r.getSQLSelect("tblsemakanhantar s, tblppkpermohonan a");
		
		////System.out.print("SQL LISTTT"+sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		Integer count = 0;
		while(rs.next()) {
			h = new Hashtable();
			h.put("idsemakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
			h.put("Catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("tarikh_pelbagai", rs.getString("tarikh_pelbagai")==null?"":sdf.format(rs.getDate("tarikh_pelbagai")));
			h.put("no_online", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
			
			
			listSemakan.addElement(h);
			count ++;
		}
		 if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("idsemakansenarai","");
			  h.put("Catatan", "");
			  h.put("tarikh_pelbagai", "");
			  h.put("no_online", "");
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
*/
