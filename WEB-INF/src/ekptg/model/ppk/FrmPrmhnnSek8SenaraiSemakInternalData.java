
package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPrmhnnSek8SenaraiSemakInternalData {
	FrmPrmhnnSek8InternalData logic = new FrmPrmhnnSek8InternalData();
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8SenaraiSemakInternalData.class);
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void semakanAdd17(String idsemakan, String idListSemakan, String txtbox, String tarikhresit) throws Exception {
	    Db db = null;
	    long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    String sql = "INSERT INTO Tblsemakanhantar " +
	    		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
	    		"VALUES ("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	    System.out.println("SQL SEMAKANADD17 dalam FrmPrmhnnSek8SenaraiSemakInternalData "+sql);
	    
	    String sqlbayaran ="";
	    String sqlM ="";
	    try {

      
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
	      //r.add("dokumen_lain", txtbox2);
	      r.add("tarikh_pelbagai", tarikhd_resit);
	      r.add("tarikh_masuk",r.unquote("sysdate")); 
	      //sql = r.getSQLInsert("tblsemakanhantar");
	      stmt.executeUpdate(sql);
	    /*  
	      long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
	      
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
	      //System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtA.executeUpdate(sqlbayaran);
	      }
	      
	      
	      */
	  
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void semakanAdd(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String tarikhperintah, String buktimati, String uid) throws Exception {
	    Db db = null;
	    long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    /*
	    String sql = "INSERT INTO Tblsemakanhantar " +
	    		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk, id_masuk)  " +
	    		"VALUES ("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate, '"+uid+"') ";
	    */
	    //System.out.println("SQL SEMAKKKK"+sql);
	    
	    String sqlbayaran ="";
	    String sqlM ="";
	    String sql = "";
	    try {

          //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
	      //long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
	      //int idPermohonan = Integer.parseInt(idListSemakan);
	     // int idSemakan = Integer.parseInt(idsemakan);
	      //String tarikh_resit = tarikhresit;	
		  //String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		  //id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk, id_masuk
		  //("+idSemakanhantar+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate, '"+uid+"')
		  //int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Semakanhantar", idSemakanhantar);
	      r.add("id_semakansenarai", idsemakan);
	      r.add("id_permohonan", idListSemakan);
	      r.add("catatan", txtbox);
	      if(!tarikhresit.equals("") && tarikhresit!=null)
	      {
	      r.add("tarikh_pelbagai", r.unquote("to_date('" + tarikhresit + "','dd/MM/yyyy')"));
	      }
	      if(!tarikhperintah.equals("") && tarikhperintah!=null)
	      {
	      r.add("tarikh_pelbagai", r.unquote("to_date('" + tarikhperintah + "','dd/MM/yyyy')"));
	      }
	      r.add("tarikh_masuk",r.unquote("sysdate"));
	      r.add("id_masuk", uid);
	      /*
	      r.add("id_Semakanhantar", idSemakanhantar);
	      r.add("id_semakansenarai", idSemakan);
	      r.add("id_permohonan", idPermohonan);
	      r.add("catatan", txtbox); 
	      r.add("tarikh_pelbagai", tarikhd_resit);
	      r.add("tarikh_masuk",r.unquote("sysdate"));
	      */
	      
	      sql = r.getSQLInsert("tblsemakanhantar");
	      System.out.println("************ SQL tblsemakanhantar : "+sql);
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
	      //System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtA.executeUpdate(sqlbayaran);
	      }
	      
	      
	      */
	  
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	
	
	
	
	public  void pilihanAdd(String id_per_mati,String id_hta, String uid, long id_Pilihanhta,String flag_daftar ) throws Exception {
	    Db db = null;
	    
	    String sql = "INSERT INTO TBLPPKPILIHANHTA " +
	    		"(id_Pilihanhta, id_permohonansimati,id_hta,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,flag_daftar_perintah)  " +
	    		"VALUES ("+id_Pilihanhta+","+id_per_mati+","+id_hta+",'"+uid+"',sysdate,'"+uid+"', sysdate,'"+flag_daftar+"')";
	    System.out.println("SQL pilihanAdd"+sql.toUpperCase());
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void pilihanAdd_HA(String id_per_mati,String id_ha, String uid, long id_Pilihanha,String flag_daftar ) throws Exception {
	    Db db = null;
	    
	    String sql = "INSERT INTO TBLPPKPILIHANHA " +
	    		"(id_Pilihanha, id_permohonansimati,id_ha,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,flag_daftar_perintah)  " +
	    		"VALUES ("+id_Pilihanha+","+id_per_mati+","+id_ha+",'"+uid+"',sysdate,'"+uid+"', sysdate,'"+flag_daftar+"')";
	    System.out.println("SQL pilihanAdd_HA"+sql.toUpperCase());
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void pilihanBatalDanLantik(String jenis_harta,String id_perintahobmst,String id_jenisperintah,String id_perintah,String id_permohonansimati,String id_harta,String id_waris,String flag_harta,String flag_batal,String bahagian_atas,String bahagian_bawah, String uid,String status_tadbir,String id_obdtl) throws Exception {
	    Db db = null;
	    Connection conn = null;
	    try {
	     
	      db = new Db();
		  conn = db.getConnection();
		  conn.setAutoCommit(false);
	      
	     
	      String sql1 = "";
	      String sql4 = "";
	      String sql2 = "";
	      String sql3 = "";
	      String sql5 = "";
	      String sql6 = "";
	      String sql7 = "";
	      String sql8 = "";
	      String sql9 = "";
	      String sql10 = "";
	      	
	      Statement stmt1 = db.getStatement();
		  Statement stmt2 = db.getStatement();
		  Statement stmt3 = db.getStatement();
		  Statement stmt4 = db.getStatement();
		  Statement stmt5 = db.getStatement();
		  Statement stmt6 = db.getStatement();
		  Statement stmt7 = db.getStatement();
		  Statement stmt8 = db.getStatement();
		  Statement stmt9 = db.getStatement();
		  Statement stmt10 = db.getStatement();
		  
	      SQLRenderer r = new SQLRenderer();
	      
	      if(jenis_harta.equals("HTA"))
	      {
	    	String id_ob = "";
	    	String sql_get_ob = " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL " +
	    			" WHERE ID_PERINTAHHTAOBDTL = '"+id_waris+"' ";
			ResultSet rs_ob = stmt1.executeQuery(sql_get_ob);			
			while (rs_ob.next()) {
			id_ob = rs_ob.getString("ID_OB") == null ? "" : rs_ob.getString("ID_OB");
			}
	    	  
	    	String sql_total = " SELECT * FROM TBLPPKPERINTAHHTAOBMST " +
				" WHERE ID_HTA = '"+id_harta+"' AND FLAG_HARTA = 'L' AND ID_WARIS = '"+id_ob+"' AND ID_PERINTAH = '"+id_perintah+"' ";
			ResultSet rs = stmt1.executeQuery(sql_total);
			int total = 0;
			while (rs.next()) {
				total++;
			}
			
			if(total>0)
			{
			    r.clear();	
				r.update("ID_PERINTAH", id_perintah);
				r.update("ID_HTA", id_harta);
				r.update("ID_WARIS", id_ob);
				r.update("FLAG_HARTA", "L");
				r.add("FLAG_BATAL", flag_batal);
				sql1 = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
				System.out.println(" update default batal lantik id_waris htaobmst:"+sql1.toUpperCase());
				stmt2.executeUpdate(sql1);
			}
			else
			{
				r.clear();	
				r.update("ID_PERINTAH", id_perintah);
				r.update("ID_HTA", id_harta);				
				r.update("FLAG_HARTA", "L");
				r.add("FLAG_BATAL", flag_batal);
				sql2 = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
				System.out.println(" update default batal lantik htaobmst:"+sql2.toUpperCase());
				stmt3.executeUpdate(sql2);
			}
			
	      
	      sql3 = "INSERT INTO TBLPPKPERINTAHHTAOBMST " +
  		  "(ID_PERINTAHHTAOBMST, ID_WARIS,BA_WARIS,BB_WARIS,ID_HTA,ID_PERINTAH,ID_JENISPERINTAH,FLAG_HARTA,FLAG_BATAL,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
  		  "VALUES ('"+id_perintahobmst+"','"+id_ob+"','"+bahagian_atas+"','"+bahagian_bawah+"','"+id_harta+"','"+id_perintah+"','"+id_jenisperintah+"','"+flag_harta+"','"+flag_batal+"','"+uid+"',sysdate,'"+uid+"', sysdate)";
		  System.out.println("INSERT BATAL & LANTIK TBLPPKPERINTAHHTAOBMST"+sql3.toUpperCase());
		  stmt4.executeUpdate(sql3);
		  
		  if(flag_batal.equals("BL"))
		  {
		  sql4 = "INSERT INTO TBLPPKPERINTAHHTAOBDTL " +
	  	  "(ID_PERINTAHHTAOBMST, ID_OB,BA,BB,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,status_tadbir)  " +
	  	  "VALUES ('"+id_perintahobmst+"','"+id_ob+"','1','1','"+uid+"',sysdate,'"+uid+"', sysdate, '"+status_tadbir+"')";
		  System.out.println("INSERT BATAL & LANTIK TBLPPKPERINTAHHTAOBDTL"+sql4.toUpperCase());
		  stmt5.executeUpdate(sql4);
		  }	  
		  
	      }
	      
	      if(jenis_harta.equals("HA"))
	      {
	    	String id_ob = "";
		    String sql_get_ob = " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL " +
		    			" WHERE ID_PERINTAHHAOBDTL = '"+id_waris+"' ";
			ResultSet rs_ob = stmt1.executeQuery(sql_get_ob);			
			while (rs_ob.next()) {
			id_ob = rs_ob.getString("ID_OB") == null ? "" : rs_ob.getString("ID_OB");
			}  
	    	  
	    	String sql_total = " SELECT * FROM TBLPPKPERINTAHHAOBMST " +
			" WHERE ID_HA = '"+id_harta+"' AND FLAG_HARTA = 'L' AND ID_WARIS = '"+id_ob+"' AND ID_PERINTAH = '"+id_perintah+"' ";
			ResultSet rs = stmt6.executeQuery(sql_total);
			int total = 0;
			while (rs.next()) {
				total++;
			}
			
			if(total>0)
			{
				r.clear();	
				r.update("ID_PERINTAH", id_perintah);
				r.update("ID_HA", id_harta);
				r.update("ID_WARIS", id_ob);
				r.update("FLAG_HARTA", "L");
				r.add("FLAG_BATAL",flag_batal);
				sql5 = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
				System.out.println(" update default batal lantik id_waris haobmst:"+sql5.toUpperCase());
				stmt7.executeUpdate(sql5);
			}
			else
			{
				r.clear();	
				r.update("ID_PERINTAH", id_perintah);
				r.update("ID_HA", id_harta);				
				r.update("FLAG_HARTA", "L");
				r.add("FLAG_BATAL",flag_batal);
				sql6 = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
				System.out.println(" update default batal lantik haobmst:"+sql6.toUpperCase());
				stmt8.executeUpdate(sql6);
			}
	     
	      sql7 = "INSERT INTO TBLPPKPERINTAHHAOBMST " +
  		  "(ID_PERINTAHHAOBMST, ID_WARIS,BA_WARIS,BB_WARIS,ID_HA,ID_PERINTAH,ID_JENISPERINTAH,FLAG_HARTA,FLAG_BATAL,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
  		  "VALUES ('"+id_perintahobmst+"','"+id_ob+"','"+bahagian_atas+"','"+bahagian_bawah+"','"+id_harta+"','"+id_perintah+"','"+id_jenisperintah+"','"+flag_harta+"','"+flag_batal+"','"+uid+"',sysdate,'"+uid+"', sysdate)";
		  System.out.println("INSERT BATAL & LANTIK TBLPPKPERINTAHHAOBMST"+sql7.toUpperCase());
		  stmt9.executeUpdate(sql7);
		  
		  if(flag_batal.equals("BL"))
		  {
		  sql8 = "INSERT INTO TBLPPKPERINTAHHAOBDTL " +
	  	  "(ID_PERINTAHHAOBMST, ID_OB,BA,BB,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,status_tadbir)  " +
	  	  "VALUES ('"+id_perintahobmst+"','"+id_ob+"','1','1','"+uid+"',sysdate,'"+uid+"', sysdate,'"+status_tadbir+"')";
		  System.out.println("INSERT BATAL & LANTIK TBLPPKPERINTAHHAOBDTL"+sql8.toUpperCase());
		  stmt10.executeUpdate(sql8);
		  }
	      }
	      
		  
	      
		conn.commit();
			
		} catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }	
	}
	
	public  void pilihanAddOBHA(String jenis_perintah,String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanha,String flag_daftar,String id_obdtl ) throws Exception {
	    Db db = null;
	    
	  
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      if(!pa1.equals("") || !pa2.equals("") || !pa3.equals("") || !pa4.equals(""))
			{
			}
	      String sql = "INSERT INTO TBLPPKOBPILIHANHA " +
  		"(id_Pilihanha, id_permohonansimati,id_OB,PA1,PA2,PA3,PA4,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,flag_daftar_perintah,ID_PERINTAHHAOBDTL)  " +
  		"VALUES ('"+id_Pilihanha+"','"+id_per_mati+"','"+id_ob+"','"+pa1+"','"+pa2+"','"+pa3+"','"+pa4+"','"+uid+"',sysdate,'"+uid+"', sysdate,'"+flag_daftar+"','"+id_obdtl+"')";
  System.out.println("INSERT PILIHAOBHA"+sql.toUpperCase());
  stmt.executeUpdate(sql);
			
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	public  void pilihanAddOBHTA_Others_ob(String id_ha_main,String id_pilihanha,String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta ) throws Exception {
	    Db db = null;	    
	     try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      String sql_ob = " SELECT OBT.STATUS_TADBIR,OB.ID_OB,HP.ID_HA,OB.NAMA_OB,OBT.BA,OBT.BB, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA1,OBT.ID_PA1, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA2,OBT.ID_PA2, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA3,OBT.ID_PA3, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA4,OBT.ID_PA4 "+
			" FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, " +
			" TBLPPKPERMOHONANSIMATI MS, " +
			" TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
			" TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM," +
			" TBLPPKPERINTAHHAOBDTL OBT, "+
			" TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
			" WHERE OBM.ID_JENISPERINTAH IN (2) AND H.ID_SIMATI = S.ID_SIMATI AND H.ID_HA = HP.ID_HA  " +
			" AND H.ID_PERMOHONANSIMATI = '"+id_per_mati+"'  "+  
			" AND H.ID_SIMATI = MS.ID_SIMATI  " +
			" AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
			" AND MS.ID_PERMOHONANSIMATI = '"+id_per_mati+"'   "+
			" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
			" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
			" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
			" AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHAOBMST "+
			" AND OBT.ID_PERINTAHHAOBMST = OBM.ID_PERINTAHHAOBMST  "+
			" AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+id_per_mati+"' "+
			" AND OBT.ID_OB = OB1.ID_OB ";
			//" AND OBT.STATUS_TADBIR IN ('Y','T') ";	      	
	      	System.out.println("INSERT PILIHAOBHTA HA OB"+sql_ob.toUpperCase());
			ResultSet rs_ob = stmt.executeQuery(sql_ob);
			while (rs_ob.next()) {
				//System.out.println("XXXXXXXX MASUK DALAM OB :");	
				String id_ob_pilihan = rs_ob.getString("ID_OB")==null?"":rs_ob.getString("ID_OB");
				String id_ob_tadbir_pilihan = rs_ob.getString("STATUS_TADBIR")==null?"":rs_ob.getString("STATUS_TADBIR");
				String id_ha_main_ob = rs_ob.getString("ID_HA")==null?"":rs_ob.getString("ID_HA");
				System.out.println("---------- XXXXXXXX id_ha_main_ob :"+id_ha_main_ob);					
				if(id_ha_main.equals(id_ha_main_ob))
				{
					System.out.println("---------- XXXXXXXX id_ha_main_ob yang sama:"+id_ha_main_ob);	
					//System.out.println("XXXXXXXX MASUK DALAM OB 1:");	
					if(id_ob_pilihan.equals(id_ob))
					{
						//System.out.println("XXXXXXXX MASUK DALAM OB 2:");
						
					    String sql_delete = "delete from tblppkobpilihanha where " +
						" id_pilihanha = '"+id_pilihanha+"' AND id_ob = '"+id_ob+"' ";
					    stmt.executeUpdate(sql_delete);							
				    	
						System.out.println("id_per_mati :"+id_per_mati);
						System.out.println("id_pilihanha :"+id_pilihanha);
						System.out.println("id_ob :"+id_ob);
						System.out.println("pa1 :"+pa1);
						System.out.println("pa2 :"+pa2);
						System.out.println("pa3 :"+pa3);
						System.out.println("pa4 :"+pa4);
						
						if(!pa1.equals("") || !pa2.equals("") || !pa3.equals("") || !pa4.equals(""))
						{
						
						}
						String sql = "INSERT INTO TBLPPKOBPILIHANHA " +
				  		"(id_Pilihanha, id_permohonansimati,id_OB,PA1,PA2,PA3,PA4,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
				  		"VALUES ('"+id_pilihanha+"','"+id_per_mati+"','"+id_ob+"','"+pa1+"','"+pa2+"','"+pa3+"','"+pa4+"','"+uid+"',sysdate,'"+uid+"', sysdate)";
					    stmt.executeUpdate(sql);
					}
				}
			
			}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
	     } finally {
		      if (db != null) db.close();
		    }
		  }
	
	public  void pilihanAddOBHA_Others_ob(String id_hta_main,String id_pilihanhta,String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta ) throws Exception {
	    Db db = null;	    
	     try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      

	      String sql_ob = " SELECT OBT.STATUS_TADBIR,OB.ID_OB,HP.ID_HTA,OB.NAMA_OB,OBT.BA,OBT.BB, "+							
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA1,OBT.ID_PA1, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA2,OBT.ID_PA2, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA3,OBT.ID_PA3, "+
			" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+id_per_mati+"') "+
			" AS NAMA_PA4,OBT.ID_PA4 "+
			" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
			" TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
			" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ," +
			" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKPERINTAHHTAOBDTL OBT, "+
			" TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
			" WHERE OBM.ID_JENISPERINTAH IN (1,7) AND H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA  AND H.ID_PERMOHONANSIMATI = '"+id_per_mati+"'  "+  
			" AND H.ID_SIMATI = MS.ID_SIMATI  AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
			" AND MS.ID_PERMOHONANSIMATI = '"+id_per_mati+"'   "+
			" AND H.JENIS_HTA = 'Y' AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
			" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
			" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
			" AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHTAOBMST "+
			" AND OBT.ID_PERINTAHHTAOBMST = OBM.ID_PERINTAHHTAOBMST  "+
			" AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+id_per_mati+"' "+
			" AND OBT.ID_OB = OB1.ID_OB "+
			" AND OBT.STATUS_TADBIR IN ('Y','T') ";		
	      
	      System.out.println("INSERT PILIHAOBHTA HTA OB"+sql_ob.toUpperCase());
			ResultSet rs_ob = stmt.executeQuery(sql_ob);
			while (rs_ob.next()) {
				//System.out.println("XXXXXXXX MASUK DALAM OB :");	
				String id_ob_pilihan = rs_ob.getString("ID_OB")==null?"":rs_ob.getString("ID_OB");
				String id_ob_tadbir_pilihan = rs_ob.getString("STATUS_TADBIR")==null?"":rs_ob.getString("STATUS_TADBIR");
				String id_hta_main_ob = rs_ob.getString("ID_HTA")==null?"":rs_ob.getString("ID_HTA");
				System.out.println("---------- XXXXXXXX id_hta_main_ob :"+id_hta_main_ob);					
				if(id_hta_main.equals(id_hta_main_ob))
				{
					System.out.println("---------- XXXXXXXX id_hta_main_ob yang sama:"+id_hta_main_ob);	
					System.out.println("XXXXXXXX MASUK DALAM OB 1:");	
					if(id_ob_pilihan.equals(id_ob))
					{
						System.out.println("XXXXXXXX MASUK DALAM OB 2:");
						
					    String sql_delete = "delete from tblppkobpilihanhta where " +
						" id_pilihanhta = '"+id_pilihanhta+"' AND id_ob = '"+id_ob+"' ";
					    stmt.executeUpdate(sql_delete);							
				    	
						System.out.println("id_per_mati :"+id_per_mati);
						System.out.println("id_pilihanhta :"+id_pilihanhta);
						System.out.println("id_ob :"+id_ob);
						System.out.println("pa1 :"+pa1);
						System.out.println("pa2 :"+pa2);
						System.out.println("pa3 :"+pa3);
						System.out.println("pa4 :"+pa4);
						
						if(!pa1.equals("") || !pa2.equals("") || !pa3.equals("") || !pa4.equals(""))
						{
						String sql = "INSERT INTO TBLPPKOBPILIHANHTA " +
				  		"(id_Pilihanhta, id_permohonansimati,id_OB,PA1,PA2,PA3,PA4,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
				  		"VALUES ('"+id_pilihanhta+"','"+id_per_mati+"','"+id_ob+"','"+pa1+"','"+pa2+"','"+pa3+"','"+pa4+"','"+uid+"',sysdate,'"+uid+"', sysdate)";
					    System.out.println("INSERT PILIHAOBHTA OTHERS"+sql.toUpperCase());
					    stmt.executeUpdate(sql);
						}
					}
				}
			
			}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
	     } finally {
		      if (db != null) db.close();
		    }
		  }

	
	public String getIdpilihanHA(String id_per_mati,String id_ha_main,String uid) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		String sql_pilihan_ha = "SELECT ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_HA " +
		"FROM TBLPPKPILIHANHA WHERE ID_HA = '"+id_ha_main+"' AND ID_PERMOHONANSIMATI = '"+id_per_mati+"' ";
		String id_pilihanha = "";
		ResultSet rs_pilihan_ha = stmt.executeQuery(sql_pilihan_ha);	
		while (rs_pilihan_ha.next()) {
			id_pilihanha = rs_pilihan_ha.getString("ID_PILIHANHA") == null ? "" : rs_pilihan_ha.getString("ID_PILIHANHA");
		}
		
		if(id_pilihanha.equals(""))
		{
			long id_pilihanha_long = DB.getNextID("TBLPPKPILIHANHA_SEQ");
			id_pilihanha = id_pilihanha_long + "";
			pilihanAdd_HA(id_per_mati,id_ha_main,uid,id_pilihanha_long,"");										
		}
		
		
		return id_pilihanha;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdpilihanHTA(String id_per_mati,String id_ha_main,String uid) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		String sql_pilihan_ha = "SELECT ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_HTA " +
		"FROM TBLPPKPILIHANHTA WHERE ID_HTA = '"+id_ha_main+"' AND ID_PERMOHONANSIMATI = '"+id_per_mati+"' ";
		String id_pilihanhta = "";
		ResultSet rs_pilihan_ha = stmt.executeQuery(sql_pilihan_ha);	
		while (rs_pilihan_ha.next()) {
			id_pilihanhta = rs_pilihan_ha.getString("ID_PILIHANHTA") == null ? "" : 
				rs_pilihan_ha.getString("ID_PILIHANHTA");
		}
	
		if(id_pilihanhta.equals(""))
		{
			long id_pilihanhta_long = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
			id_pilihanhta = id_pilihanhta_long + "";
			pilihanAdd(id_per_mati,id_ha_main,uid,id_pilihanhta_long,"");
			System.out.println("SImpan HTA");
		}
		
		
		return id_pilihanhta;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public  void pilihanAddOBHA_Others(String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta ) throws Exception {
	    Db db = null;	    
	      try {
	      db = new Db();
	      Statement stmt = db.getStatement();	      
	      String sql_ha = "SELECT " +
			" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
			" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
			" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "+
			" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
			" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
			" H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, " +
			"H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
			"H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
			"H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
			"H.CATATAN, H.STATUS_PEMILIKAN, " +
			" H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "+
			"FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
			"TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, " +
			"TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ   " +
			"WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA " +
			" AND H.ID_PERMOHONANSIMATI = '"+id_per_mati+"'   " +
			"AND H.ID_SIMATI = MS.ID_SIMATI  " +
			"AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
			"AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI " +
			"AND MS.ID_PERMOHONANSIMATI = '"+id_per_mati+"'   " +
			"AND H.JENIS_HTA = 'Y' " +
			"AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
			"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
			"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
			"AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
			"AND S.ID_SIMATI = MS.ID_SIMATI ";
	      
	      	System.out.println("INSERT PILIHAOBHA HA"+sql_ha.toUpperCase());
		  	ResultSet rs_ha = stmt.executeQuery(sql_ha);		  	
			while (rs_ha.next()) {					
				String id_hta_main = rs_ha.getString("ID_HTA") == null ? "" : rs_ha.getString("ID_HTA");
				String id_pilihanhta = "";
				System.out.println("---------- XXXXXXXX id_hta_main :"+id_hta_main);				
				id_pilihanhta = getIdpilihanHTA(id_per_mati, id_hta_main, uid);				
				pilihanAddOBHA_Others_ob( id_hta_main, id_pilihanhta, id_per_mati, id_ob, pa1, pa2, pa3, pa4,  uid,  id_Pilihanhta );
				}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
	    } finally {
		      if (db != null) db.close();
		}
	    
	}

	
	public  void pilihanAddOBHTA_Others(String jenis_perintah,String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta ) throws Exception {
	    Db db = null;	    
	      try {
	      db = new Db();
	      Statement stmt = db.getStatement();	      
	      String sql_hta = "SELECT OBM.ID_JENISPERINTAH," +
			" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
			" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
			" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
			" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
			" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
			" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
			+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
			+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
			+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
			+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
			+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM" 
			+ "  "
			+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"+id_per_mati+"' " +
					" AND H.ID_SIMATI = S.ID_SIMATI "
			+ "AND H.ID_JENISHA = J.ID_JENISHA  "
			+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
			+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
			+ "AND MS.ID_PERMOHONANSIMATI = '"
			+ id_per_mati
			+ "' "
			+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
			+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
			+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
			+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
			+ "AND S.ID_SIMATI = MS.ID_SIMATI "
			+" AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHAOBMST AND OBM.ID_JENISPERINTAH IN (2) ";      
	      
	       	System.out.println("INSERT PILIHAOBHTA HA CHECK"+sql_hta.toUpperCase());
	       	
		  	ResultSet rs_hta = stmt.executeQuery(sql_hta);		  	
			while (rs_hta.next()) {					
				String id_ha_main = rs_hta.getString("id_Ha") == null ? "" : rs_hta.getString("id_Ha");
				String id_pilihanha = "";								
				id_pilihanha = getIdpilihanHA(id_per_mati,id_ha_main,uid);				
				pilihanAddOBHTA_Others_ob( id_ha_main, id_pilihanha, id_per_mati, id_ob, pa1, pa2, pa3, pa4,  uid,  id_Pilihanhta );
				}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
	    } finally {
		      if (db != null) db.close();
		}
	    
	}
	
	public  void pilihanAddOBHTAX(String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta ) throws Exception {
	    Db db = null;
	    
	    String sql_hta = "";
	    String sql_ob = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      if(!pa1.equals("") || !pa2.equals("") || !pa3.equals("") || !pa4.equals(""))
			{
	    	  
	    	  
			}
	      String sql = "INSERT INTO TBLPPKOBPILIHANHTA " +
  		"(id_Pilihanhta, id_permohonansimati,id_OB,PA1,PA2,PA3,PA4,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
  		"VALUES ('"+id_Pilihanhta+"','"+id_per_mati+"','"+id_ob+"','"+pa1+"','"+pa2+"','"+pa3+"','"+pa4+"','"+uid+"',sysdate,'"+uid+"', sysdate)";
	      System.out.println("INSERT PILIHAOBHTA"+sql.toUpperCase());
	      stmt.executeUpdate(sql);
	      
	     // pilihanAddOBHTA_Others(id_per_mati, id_ob, pa1, pa2, pa3, pa4,  uid,  id_Pilihanhta );
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void pilihanAddOBHTA(String jenis_perintah,String id_per_mati,String id_ob,String pa1,String pa2,String pa3,String pa4, String uid, String id_Pilihanhta,String flag_daftar,String id_obdtl ) throws Exception {
	    Db db = null;
	    
	    String sql_hta = "";
	    String sql_ob = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      if(!pa1.equals("") || !pa2.equals("") || !pa3.equals("") || !pa4.equals(""))
		  {
		  }
	      
	      String sql = "INSERT INTO TBLPPKOBPILIHANHTA " +
  		"(id_Pilihanhta, id_permohonansimati,id_OB,PA1,PA2,PA3,PA4,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini,flag_daftar_perintah,ID_PERINTAHHTAOBDTL)  " +
  		"VALUES ('"+id_Pilihanhta+"','"+id_per_mati+"','"+id_ob+"','"+pa1+"','"+pa2+"','"+pa3+"','"+pa4+"','"+uid+"',sysdate,'"+uid+"', sysdate,'"+flag_daftar+"','"+id_obdtl+"')";
	      System.out.println("INSERT PILIHAOBHTA"+sql.toUpperCase());
	      stmt.executeUpdate(sql);
	      
	      System.out.println("jenis_perintah :"+jenis_perintah);
	      
	      if(jenis_perintah.equals("2"))
	      {
	      //pilihanAddOBHTA_Others(jenis_perintah,id_per_mati, id_ob, pa1, pa2, pa3, pa4, uid, id_Pilihanhta);
	      }
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void pilihanAdd_ha(String id_per_mati,String id_ha, String uid) throws Exception {
	    Db db = null;
	    long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	    String sql = "INSERT INTO TBLPPKPILIHANHA " +
	    		"(id_Pilihanha, id_permohonansimati,id_ha,id_masuk,tarikh_masuk,id_kemaskini, tarikh_kemaskini)  " +
	    		"VALUES ("+id_Pilihanhta+","+id_per_mati+","+id_ha+",'"+uid+"',sysdate,'"+uid+"', sysdate)";
	      try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      stmt.executeUpdate(sql);
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
	      //System.out.println("SQL pilihanDelete"+sql.toUpperCase());
		    
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void clear_pilihanHTADeletex(String id_per_mati,String id_hta, String uid) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();
	    	
	    	sql = "delete from tblppkobpilihanhta where " +
	    			" id_pilihanhta = (select id_pilihanhta from tblppkpilihanhta " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_hta = " + id_hta + ") and id_permohonansimati = " + id_per_mati+" ";
	    	stmtL.executeUpdate(sql);
	    	
	    	/*
	    	sql = "delete from tblppkpilihanhta where id_permohonansimati = " + id_per_mati
			+ " and id_hta = " + id_hta + "";
	    	stmtL.executeUpdate(sql);
	    	*/
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void clear_pilihanHTAMainDelete_byId(String id_hta,String id_per_mati,String flag_jenis_harta) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();  

	    	sql = "delete from tblppkobpilihanhta where " +
			" id_pilihanhta IN ( "+
	    	" select  id_pilihanhta from tblppkpilihanhta " +
			" where id_permohonansimati = '" + id_per_mati
			+ "' and id_hta  = '"+id_hta+"'	) ";
	    	System.out.println("delete from tblppkobpilihanhta BYID :"+sql);
	    	stmtL.executeUpdate(sql);
	    	
	    	sql = "delete from tblppkpilihanhta " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_hta  = '"+id_hta+"'";
	    	System.out.println("delete from tblppkpilihanhta BYID :"+sql);
	    	stmtL.executeUpdate(sql);   	
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void clear_pilihanHAMainDelete_byId(String id_ha,String id_per_mati,String flag_jenis_harta) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();  

	    	sql = "delete from tblppkobpilihanha where " +
			" id_pilihanha IN ( "+
	    	" select  id_pilihanha from tblppkpilihanha " +
			" where id_permohonansimati = '" + id_per_mati
			+ "' and id_ha  = '"+id_ha+"'	) ";
	    	System.out.println("delete from tblppkobpilihanha BYID :"+sql);
	    	stmtL.executeUpdate(sql);
	    	
	    	sql = "delete from tblppkpilihanha " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_ha  = '"+id_ha+"'";
	    	System.out.println("delete from tblppkpilihanha BYID :"+sql);
	    	stmtL.executeUpdate(sql);   	
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void clear_pilihanHTAMainDelete(String id_per_mati,String flag_jenis_harta) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();  

	    	sql = "delete from tblppkobpilihanhta where " +
			" id_pilihanhta IN ( "+
	    	" select  id_pilihanhta from tblppkpilihanhta " +
			" where id_permohonansimati = " + id_per_mati
	+ " and id_hta IN (SELECT ID_HTA FROM TBLPPKHTAPERMOHONAN WHERE   " +
			"  ID_PERMOHONANSIMATI = '"+id_per_mati+"'";
	    	if(!flag_jenis_harta.equals(""))
	    	{
	    	sql +=	"AND JENIS_HTA = '"+flag_jenis_harta+"'";
	    	}
	    	sql += ")) ";
	    	System.out.println("delete from tblppkobpilihanhta :"+sql);
	    	stmtL.executeUpdate(sql);
	    	
	    	
	    	
	    	sql = "delete from tblppkpilihanhta " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_hta IN (SELECT ID_HTA FROM TBLPPKHTAPERMOHONAN " +
					" WHERE " +
					"  ID_PERMOHONANSIMATI = '"+id_per_mati+"'";
					if(!flag_jenis_harta.equals(""))
			    	{
					sql+=" AND JENIS_HTA = '"+flag_jenis_harta+"' ";
					}		
					sql+=")";
	    	System.out.println("delete from tblppkpilihanhta :"+sql);
	    	stmtL.executeUpdate(sql);   	
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	
	
	
	
	
	
	public  void clear_pilihanHAMainDelete(String id_per_mati,String flag_jenis_harta) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();    	
	    	
	    	sql = "delete from tblppkobpilihanha where " +
			" id_pilihanha IN ( "+
	    	" select  id_pilihanha from tblppkpilihanha " +
			" where id_permohonansimati = " + id_per_mati
	+ " and id_ha IN (SELECT ID_ha FROM TBLPPKhaPERMOHONAN WHERE   " +
			"  ID_PERMOHONANSIMATI = '"+id_per_mati+"')) ";
	    	stmtL.executeUpdate(sql);
	    	
	    	sql = "delete from tblppkpilihanha " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_ha IN (SELECT ID_ha FROM TBLPPKhaPERMOHONAN WHERE " +
					" ID_PERMOHONANSIMATI = '"+id_per_mati+"')";
	    	stmtL.executeUpdate(sql);  
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void clear_pilihanHADeletex(String id_per_mati,String id_ha, String uid) throws Exception {
	    Db db = null;
	
	    String sql = "";
	   
	    try {
	    	db = new Db();
	    	Statement stmtL = db.getStatement();
	    	
	    	sql = "delete from tblppkobpilihanha where " +
	    			" id_pilihanha = (select id_pilihanha from tblppkpilihanha " +
	    			" where id_permohonansimati = " + id_per_mati
			+ " and id_ha = " + id_ha + ") and id_permohonansimati = " + id_per_mati+" ";
	    	stmtL.executeUpdate(sql);
	    	
	    	sql = "delete from tblppkpilihanha where id_permohonansimati = " + id_per_mati
			+ " and id_ha = " + id_ha + "";
	    	stmtL.executeUpdate(sql);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	
	
	public Vector pilihanList(String idhta, String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_HTA " +
				" FROM TBLPPKPILIHANHTA " +
				" WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' AND ID_HTA = '"+idhta+"'";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("PILIHAN LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanhta", rs.getString("ID_PILIHANHTA"));
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector pilihanList_ha(String idha, String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' AND ID_HA = '"+idha+"'";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("PILIHAN LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanha", rs.getString("ID_PILIHANHA"));
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector<Hashtable<String,String>> check_pilihan(String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHTA,FLAG_DAFTAR_PERINTAH, ID_PERMOHONANSIMATI, ID_HTA " +
				" FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
		
		
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("PILIHAN CHECK LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanhta", rs.getString("ID_PILIHANHTA"));
				h.put("id_hta", rs.getString("ID_HTA"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flag_daftar_perintah", rs.getString("FLAG_DAFTAR_PERINTAH")==null?"":rs.getString("FLAG_DAFTAR_PERINTAH"));
				
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector<Hashtable<String,String>> check_pilihan_hta_ob(String permohonmati) throws Exception {
		Db db = null;
		
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();	
			String sql = "SELECT ID_PERINTAHHTAOBDTL,ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_OB,PA1,PA2,PA3,PA4,FLAG_DAFTAR_PERINTAH " +
			" FROM TBLPPKOBPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanhta", rs.getString("ID_PILIHANHTA"));
				h.put("id_ob", rs.getString("ID_OB"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				
				h.put("PA1", rs.getString("PA1")==null?"":rs.getString("PA1"));
				h.put("PA2", rs.getString("PA2")==null?"":rs.getString("PA2"));
				h.put("PA3", rs.getString("PA3")==null?"":rs.getString("PA3"));
				h.put("PA4", rs.getString("PA4")==null?"":rs.getString("PA4"));
				
				h.put("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL")==null?"":rs.getString("ID_PERINTAHHTAOBDTL"));
				
				h.put("flag_daftar_perintah", rs.getString("FLAG_DAFTAR_PERINTAH")==null?"":rs.getString("FLAG_DAFTAR_PERINTAH"));
				
				
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Vector<Hashtable<String,String>> check_pilihan_ha_ob(String permohonmati) throws Exception {
		Db db = null;
		
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();	
			String sql = "SELECT ID_PERINTAHHAOBDTL,ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_OB,PA1,PA2,PA3,PA4,FLAG_DAFTAR_PERINTAH " +
			" FROM TBLPPKOBPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanha", rs.getString("ID_PILIHANHA"));
				h.put("id_ob", rs.getString("ID_OB"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				h.put("PA1", rs.getString("PA1")==null?"":rs.getString("PA1"));
				h.put("PA2", rs.getString("PA2")==null?"":rs.getString("PA2"));
				h.put("PA3", rs.getString("PA3")==null?"":rs.getString("PA3"));
				h.put("PA4", rs.getString("PA4")==null?"":rs.getString("PA4"));
				h.put("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL")==null?"":rs.getString("ID_PERINTAHHAOBDTL"));
				h.put("flag_daftar_perintah", rs.getString("FLAG_DAFTAR_PERINTAH")==null?"":rs.getString("FLAG_DAFTAR_PERINTAH"));
				
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector<Hashtable<String,String>> check_pilihan_ha(String permohonmati) throws Exception {
		Db db = null;
		String sql = "SELECT ID_PILIHANHA,FLAG_DAFTAR_PERINTAH,ID_PERMOHONANSIMATI, ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+permohonmati+"' ";
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//System.out.println("PILIHAN CHECK LIST ::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_pilihanha", rs.getString("ID_PILIHANHA"));
				h.put("id_ha", rs.getString("ID_HA"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flag_daftar_perintah", rs.getString("FLAG_DAFTAR_PERINTAH")==null?"":rs.getString("FLAG_DAFTAR_PERINTAH"));
				v.addElement(h);
			}
			return v;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	

	
	
	
	
	public  void semakanDelete(String idPermohonan) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    try {
		  //int idPermohonan = Integer.parseInt(idListSemakan);
		  db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	    //  r.add("id_permohonan", idPermohonan);
	    //  sql1 = r.getSQLDelete("tblsemakanhantar");
	      
	      sql1 =  "DELETE FROM tblsemakanhantar WHERE id_permohonan =" +idPermohonan ;
	      //+ " AND id_semakansenarai > 0 AND id_semakansenarai < 16171000000  ";  
	     // + " AND id_semakansenarai > 0 AND id_semakansenarai < 25  ";  old
	      //System.out.println("SQL! :" +sql1);
	      
	     stmt.executeUpdate(sql1);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void semakanUpdate(String idsemakan, String idListSemakan, String txtbox, String tarikhresit, String buktimati) throws Exception {
	    Db db = null;
	    String sql1 = "";
	    String sqlM = "";
	    String sqlbayaran = "";
	    try {
	    String sql = "INSERT INTO Tblsemakanhantar " +
		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	   
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      System.out.println("idSemakanhantar==="+idSemakanhantar);
	      
	      System.out.println("sql==="+sql);
	        //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
		  //int idPermohonan = Integer.parseInt(idListSemakan);//aishahlatip comment
		  //int idSemakan = Integer.parseInt(idsemakan);//aishahlatip comment
	      String tarikh_resit = tarikhresit;	
		  String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		        
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	      
	      
	      FrmPrmhnnSek8InternalData f1=new FrmPrmhnnSek8InternalData();
	      
	      Vector listSimati1=new Vector();
	      f1.setDataSimati(idListSemakan);
	  	  listSimati1 = f1.getDataSimati();
	  	  
	
	  	  
	  	  
	  	  
	  
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public  void semakanUpdateTujuanHT(String idPermohonan,String uid,String yt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("HARTA_TINGGAL",yt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	public  void semakanUpdateTujuanLPA(String idPermohonan,String uid,String yt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("LANTIK_P_AMANAH",yt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      
	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	
	public  void semakanUpdateTujuanBPA(String idPermohonan,String uid,String yt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("BATAL_P_AMANAH",yt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      
	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	
	public  void semakanUpdateTujuanLP(String idPermohonan,String uid,String yt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("LANTIK_PENTADBIR",yt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      
	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	
	public  void semakanUpdateTujuanBKP(String idPermohonan,String uid,String yt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("BATAL_KUASA_PENTADBIR",yt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      
	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	
	
	
	public  void semakanUpdateLainTujuan(String idPermohonan,String uid,String lt) throws Exception {
		{
			
	   Db db = null;  
	   String sqltujuan = "";
	   try{	 
	   db = new Db();
	  	FrmPrmhnnSek8DaftarSek8InternalData nb = new FrmPrmhnnSek8DaftarSek8InternalData();
	  	Vector kk = new Vector();
	
	  	kk = nb.setDataPemohon_C(idPermohonan);
	  	
	  	if(kk.size()>0){
	  		
	  
	    Statement stmtL = db.getStatement();
	    SQLRenderer r9 = new SQLRenderer();	   
	    r9.update("id_permohonan", idPermohonan);
	    r9.add("LAIN_TUJUAN",lt);

		r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	    r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	    sqltujuan = r9.getSQLUpdate("tblppkpermohonan");	  
	    stmtL.executeUpdate(sqltujuan);
	      
	      
	  	}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} 
			    
			    finally {
		      if (db != null) db.close();
		    }
		
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
	  		
	  	//System.out.println("txtbox"+resit);
	  	//System.out.println("tarikhresit"+tarikh);
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
	     }else{
	    	 r9.add("jumlah_bayaran",0); 
	     }
	      //r1.add("id_masuk",6); 
	      
	    //  r9.add("ID_MASUK",userId);
	      r9.add("ID_KEMASKINI",uid);
		  r9.add("TARIKH_KEMASKINI",r9.unquote("sysdate"));
	      r9.add("tarikh_masuk",r9.unquote("sysdate")); 
	      sqlbayaran = r9.getSQLUpdate("tblppkbayaran");
	     //System.out.println("sqlbayaran-->"+sqlbayaran);
	      stmtL.executeUpdate(sqlbayaran);
	      
	      
	  	}
				} catch (Exception er) {
					myLogger.error(er);
					throw er;
		} 
			    finally {
		      if (db != null) db.close();
		    }
		
		}
	 
		}
	public  void semakanUpdate17(String idsemakan, String idListSemakan, String txtbox, String tarikhresit) throws Exception {
		System.out.println("******************* Baca di sini FrmPrmhnnSek8SenaraiSemakInternalData.java");
		Db db = null;
	    String sql1 = "";
	    String sqlM = "";
	    String sql = "INSERT INTO Tblsemakanhantar " +
		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai, tarikh_masuk)  " +
		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', to_date('" + tarikhresit + "','dd/MM/yyyy'), sysdate) ";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	        //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
		  String idPermohonan = idListSemakan;
		  int idSemakan = Integer.parseInt(idsemakan);
	      String tarikh_resit = tarikhresit;	
		  String tarikhd_resit = "to_date('" + tarikh_resit + "','dd/MM/yyyy')";
		        
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      System.out.println("semakanUpdate17 = "+sql);
	      stmtA.executeUpdate(sql);
	      
	      
	      FrmPrmhnnSek8InternalData f1=new FrmPrmhnnSek8InternalData();
	      
	    	  	  
	  	  
	  	  
	  	  
	  	  
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
		  rM.add("id_Buktimati",bukti);
		  
		  sqlM = rM.getSQLUpdate("tblppksimati");
	      stmtM.executeUpdate(sqlM);
	      */
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	
	public  void update_bukti(String id_permohonan, String usid, String sijil, String buktimati) throws Exception {
	    Db db = null;
	    String bukti = "";
	    
	    String sql = "UPDATE TBlPPKSIMATI SET ID_KEMASKINI = '"+usid+"' , TARIKH_KEMASKINI = SYSDATE, NO_SIJIL_MATI = '"+sijil+"', ID_BUKTIMATI = '"+buktimati+"' WHERE ID_SIMATI IN (SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI PS, TBLPPKPERMOHONAN PM, TBLPPKSIMATI SM " +
	    		"WHERE PS.ID_PERMOHONAN = PM.ID_PERMOHONAN AND PS.ID_SIMATI = SM.ID_SIMATI AND PM.ID_PERMOHONAN = '"+id_permohonan+"')";
	    
	    //System.out.println("SQL BUKTI :"+sql);
	    		  try {
	     
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	    			} catch (Exception er) {
	    				myLogger.error(er);
	    				throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//IL
	public  void update_akuan(String id_permohonan, String usid, String catatan, String buktimati) throws Exception {
	    Db db = null;
	    String bukti = "";
	    
	    String sql = "UPDATE TBlPPKSIMATI SET ID_KEMASKINI = '"+usid+"' , TARIKH_KEMASKINI = SYSDATE, CATATAN = '"+catatan+"', ID_BUKTIMATI = '"+buktimati+"' WHERE ID_SIMATI IN (SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI PS, TBLPPKPERMOHONAN PM, TBLPPKSIMATI SM " +
	    		"WHERE PS.ID_PERMOHONAN = PM.ID_PERMOHONAN AND PS.ID_SIMATI = SM.ID_SIMATI AND PM.ID_PERMOHONAN = '"+id_permohonan+"')";
	    
	    		  try {
	     
	      db = new Db();
	      Statement stmtA = db.getStatement();
	      stmtA.executeUpdate(sql);
	    			} catch (Exception er) {
	    				myLogger.error(er);
	    				throw er;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	private  Vector listSemakan = new Vector();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void setDataSemakan(String idPermohonan) throws Exception {
		Db db = null;
		listSemakan.clear();
		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
		r.add("id_semakansenarai"); 
		r.add("catatan");
		r.add("tarikh_pelbagai");	
		r.add("id_Permohonan");	
		//r.add("dokumen_lain");
		
		r.add("id_Permohonan",idPermohonan); 
		
		sql = r.getSQLSelect("tblsemakanhantar");
		ResultSet rs = stmt.executeQuery(sql);
		
		Hashtable h;
		Integer count = 0;
		while(rs.next()) {
			h = new Hashtable();
			h.put("idsemakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
			h.put("Catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("tarikh_pelbagai", rs.getString("tarikh_pelbagai")==null?"":sdf.format(rs.getDate("tarikh_pelbagai")));
			h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			//h.put("dokumen_lain", rs.getString("dokumen_lain")==null?"":rs.getString("dokumen_lain"));
			System.out.println("idsemakansenarai " + rs.getString("id_semakansenarai"));
			System.out.println("id_Permohonan " + rs.getString("id_Permohonan"));
			System.out.println("FrmPrmhnnSek8SenaraiSemakInternalData.java");
			//System.out.println("dokumen_lain " + rs.getString("dokumen_lain"));
			//hantar taikhmohon
			
			System.out.println("catatan " + rs.getString("catatan"));
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public  Vector getListSemakan(){
		  return listSemakan;
	  }
	
	
	
	private  Vector listSemakanSimati=new Vector();
	
	
	public void setDataSemakanSimati(String idPermohonan) throws Exception {
		Db db = null;
		listSemakanSimati.clear();
		
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
		
		r.add("ts.id_Permohonan",idPermohonan);
		
		r.add("ts.id_Permohonan",r.unquote("ms.id_Permohonan"));
		
		sql = r.getSQLSelect("tblsemakanhantar ts, tblppkpermohonansimati ms");
		//System.out.println("SQL SENARAI SEMAK :"+sql);
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public  Vector getListSemakanSimati(){
		  return listSemakanSimati;
	  }
	
	
	
	
	
	
	
	
	private  Vector IdNegUser = new Vector();
	
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}
		finally {
			if(db != null) db.close();
		}	
	}
	 
	public  Vector getIdNeg(){
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
	      //System.out.println("sqlbayaran-->"+sqlbayaran);
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
		
		System.out.print("SQL LISTTT"+sql);
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
