package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblsemakan;

public class FrmSemakan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmSemakan.class);

	public static Vector<Tblsemakan> getSemakan(String idSemakan,String semakan) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Tblsemakan> list = new Vector<Tblsemakan>();
		    
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("s.id_semakan");
		      r.add("s.perihal");
		      r.add("NVL(to_char(s.tarikh_masuk,'dd/mm/yyyy'),'TIADA') tarikh_masuk");
		      r.add("NVL(to_char(s.tarikh_kemaskini,'dd/mm/yyyy'),'TIADA') tarikh_kemaskini");
		
		      //r.add("i.id_semakan",r.unquote("s.id_semakan"));
		      if(idSemakan !=null)
		    	  r.add("s.id_semakan",idSemakan);
		      if(semakan != null)
		    	  r.add("s.perihal","%"+semakan+"%","like ");

		    	  
		      sql = r.getSQLSelect("tblsemakan s"," s.perihal ");
//	          myLog.info("getSemakan : sql=" + sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Tblsemakan h;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Tblsemakan();
		    	  h.setBil(bil++);
		    	  h.setIdSemakan(rs.getLong("id_semakan"));
		    	  h.setPerihal(rs.getString("perihal"));
		    	  h.setTarikhMasukf(rs.getString(3));
		    	  h.setTarikhKemaskinif(rs.getString(4));
		    	  list.addElement(h);
		    	  
		      }
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    }finally {
		      if (db != null){
		    	  db.close();
		      }
		    }
		    
		return list;
		  
	}

	 public static Vector<Hashtable<String,String>> getSenaraiSemakan(String kodForm) throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("i.id_semakansenarai");
	      r.add("i.aturan");
	      r.add("s.perihal");
	
	      r.add("i.id_semakan",r.unquote("s.id_semakan"));
	      if(!kodForm.equals("0"))
	    	  r.add("i.kod_form",kodForm);
	      
	      sql = r.getSQLSelect("tblsemakan s,tblsemakansenarai i","i.kod_form,i.aturan");
          myLog.info("Semakan : " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("id", rs.getString("id_semakansenarai"));
	    	  h.put("aturan", Utils.isNull(rs.getString("aturan")));
	    	  h.put("keterangan", rs.getString("perihal"));
	    	  list.addElement(h);
	    	  
	      }
	      
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	      if (db != null){
	    	  db.close();
	      }
	    }	    
	    return list;
	  
	 }

	 public static Vector getSenaraiSemakan(String kodForm,String aktif)throws Exception {
		    Db db = null;
		    String sql = "";
		    Vector list = new Vector();
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("i.id_semakansenarai");
		      r.add("s.perihal");
		
		      r.add("i.id_semakan",r.unquote("s.id_semakan"));
		
		      r.add("i.kod_form",kodForm);
		      r.add("i.catatan","aktif");
		      sql = r.getSQLSelect("tblsemakan s,tblsemakansenarai i");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  list.addElement(h);
		      }
		      
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    finally {
		      if (db != null) db.close();
		    }
		    
		    return list;
		  }	 
	 
	 public static Vector getSenaraiSemakanHantar(String idpermohonan)throws Exception {
		    Db db = null;
		    String sql = "";
		    Vector list = new Vector();
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("i.id_semakansenarai");
		      r.add("i.id_semakanhantar");
		
		      r.add("i.id_permohonan",idpermohonan);
		      sql = r.getSQLSelect("tblsemakanhantar i");
		      ResultSet rs = stmt.executeQuery(sql);
		    
		      //Tblsemakanhantar h=null;
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("IdSemakansenarai",rs.getString("id_semakansenarai"));
		    	  h.put("IdSemakanhantari",rs.getString("id_semakanhantar"));
		    	  list.addElement(h);
		      }
		      
		    } 
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    finally {
		      if (db != null) db.close();
		    }
		    
		    return list;
		  }
	 
	 /** Semakan yang dihantar*/
	 public static boolean isSemakan(String idpermohonan,String idsemakansenarai)throws Exception {
		 	Db db = null;
		    String sql = "";
		    boolean rValue = false;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("i.id_semakansenarai");
		      
		      //fir modify 19022010
		      if(idpermohonan.equalsIgnoreCase("") || idpermohonan.equalsIgnoreCase(null)){
		    	  r.add("i.id_permohonan",idpermohonan);
		      }
		      else{
		    	  r.add("i.id_permohonan",r.unquote(idpermohonan));
		      }
		      
//		      r.add("i.id_permohonan",r.unquote(idpermohonan));
		      r.add("i.id_semakansenarai",r.unquote(idsemakansenarai));
		      sql = r.getSQLSelect("tblsemakanhantar i");
	    	  //log.info("FrmSemakan:isSemakan("+idpermohonan+","+idsemakansenarai+")::sql:::"+sql);
		      
		      //fir
		      //log.info("sql semakan : " + sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  list.addElement(h);
		    	  rValue=true;
		      }
		      
		    }
		    
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    		    
		    finally {
		      if (db != null) db.close();
		    }
		    
		    return rValue;
		 
	 }

	 public void semakanHapusByPermohonan(String idpermohonan) throws Exception {
		    Db db = null;
		    //int idPermohonan= Integer.parseInt(idpermohonan);
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", r.unquote(idpermohonan));
		      sql = r.getSQLDelete("tblsemakanhantar");
		      stmt.executeUpdate(sql);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }  
		    finally	{
		      if (db != null) db.close();
	    	}
	  }
	 
	 public void semakanHapusByPermohonan(String idpermohonan,String idsemakansenarai) throws Exception {
		    Db db = null;
		    int idPermohonan = Integer.parseInt(idpermohonan);
		    int idSemakanSenarai = Integer.parseInt(idsemakansenarai);
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakanSenarai);
		      sql = r.getSQLDelete("tblsemakanhantar");
		      stmt.executeUpdate(sql);
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    finally	{
		      if (db != null) db.close();
	    	}
	  }
	 
	 public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
		      String idPermohonan = idpermohonan;
		      String idSemakan = idsemakan;
		      int idKementerian = 1;
		      int idNegeri = 1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Semakanhantar", idSemakanhantar);
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakan);
		      sql = r.getSQLInsert("Tblsemakanhantar");
		      myLog.info("semakanTambah : "+sql);
		      stmt.executeUpdate(sql);
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }


	}
