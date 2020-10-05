package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
//import org.apache.velocity.VelocityContext;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblsemakan;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmSemakan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmSemakan.class);
	private ILampiran iLampiran = null;
	private ILampiran iLampiranPHP = null;
	private static Db db = null;
	private static String sql = "";
	public String mode = "";
	public static Vector<Hashtable<String,String>> senarai = null;
//	VelocityContext context_ = null;
//	FrmSemakan(VelocityContext context) {
//		context_ = context;
//	}
	
	public static Vector<Tblsemakan> getSemakan(String idSemakan,String semakan) throws Exception {
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
	 
	 public static Vector<Hashtable<String,String>> getSenaraiSemakanByIDAttach(String idSenarai
		,String idSimati,String idPermohonanSimati) throws Exception {
	    Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		    
	    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("i.aturan");
		      r.add("i.id_semakansenarai");
		      r.add("s.perihal");
		      r.add("NVL(SJD.ID_JENISDOKUMEN,0) jenis_dokumen");
		      r.add("NVL(JD.KETERANGAN,'TIADA') nama_dokumen");
		
		      r.add("i.id_semakan",r.unquote("s.id_semakan"));
		      r.add("i.id_semakan",r.unquote("sjd.id_semakan(+)"));
		      r.add("sjd.id_jenisdokumen",r.unquote("jd.id_jenisdokumen(+)"));
		      //		    	  r.add("i.id_semakansenarai",("+idSenarai+"),"in");
		      sql = " tblsemakan s,tblsemakansenarai i,TBLSEMAKANJENISDOKUMEN SJD,TBLRUJJENISDOKUMEN JD";	  
		      sql = r.getSQLSelect(sql);
		      //sql = r.getSQLSelect(sql,"i.kod_form,i.aturan");
		      if(!idSenarai.equals("0"))
		    	  sql += " and i.id_semakansenarai in ("+idSenarai+")";
		      
	          myLog.info("getSenaraiSemakanByIDAttach :sql= " + sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String,String> h;
		      ekptg.model.ppk.util.LampiranBean lb = new ekptg.model.ppk.util.LampiranBean();
		      while (rs.next()) {
		    	  String lampiran = "lampiran";
		    	  h = new Hashtable<String,String>();
		    	  String jenis =rs.getString("jenis_dokumen");
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  h.put("aturan", Utils.isNull(rs.getString("aturan")));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  h.put("jenisDokumen", jenis);
		    	  myLog.info("5-10-2020 "+idPermohonanSimati);
		    	  
		    	  if(jenis.equals("99201")) {
		    		  lampiran = lb.getLampiranSimatiPaparSimati(idSimati,jenis);
			    	  h.put("lampirans", lampiran);
		    	  
		    	  }else if(jenis.equals("99202")){
		    		  lampiran = lb.getLampiranSimatiPaparSimati(idSimati,jenis);
		    		  h.put("lampirans", lampiran);
		    	  
		    	  }else if(jenis.equals("99211")){
		    		  lampiran = lb.getLampiranSimatiPaparSimati(idSimati,jenis);
		    		  h.put("lampirans", lampiran);
		    	  
		    	  }else if(jenis.equals("99212")){
		    		  lampiran = lb.getLampiranSimatiPaparSimati(idSimati,jenis);
		    		  h.put("lampirans", lampiran);
		    		  myLog.info(lampiran);
		    	  
		    	  }else if(jenis.equals("99204")){
		    		  lampiran = lb.getLampirans(idSimati,"paparLampiran");
		    		  h.put("lampirans", lampiran);
		    		  
		    	  }else if(jenis.equals("1107")){
		    		  lampiran = lb.getLampiranHAOnline(idPermohonanSimati);
		    		  h.put("lampirans", lampiran);
		    		  
		    	  }else if(jenis.equals("1108")){
		    		  lampiran = lb.getLampiranHTAOnline(idPermohonanSimati);
		    		  h.put("lampirans", lampiran);
		    	  }
		    	  else {
		    		  h.put("lampirans", "");
		    	  }
		    	  //myLog.info("getSenaraiSemakanByIDAttach:jenis="+jenis+","+lampiran);

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
 
	public Vector<Hashtable<String,String>> getSenaraiSemakanAttach(String kodForm,String idPermohonan) 
		throws Exception {
		//public Vector<Hashtable<String,String>> getSenaraiSemakanAttach(String kodForm) throws Exception {
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
//        myLog.info("getSenaraiSemakanAttach :kodForm= " + kodForm.substring(0,3));

		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("DISTINCT i.aturan");
		      r.add("i.id_semakansenarai");
	    	  r.add("i.kod_form");
	    	  r.add("s.perihal");
		      r.add("NVL(SJD.ID_JENISDOKUMEN,0) jenis_dokumen");
		      r.add("NVL(JD.KETERANGAN,'TIADA') nama_dokumen");
		      r.add("CASE "+
		    		  "WHEN sh.ID_SEMAKANHANTAR IS NULL "+
		    		  "	THEN 'N' "+
		    		  "ELSE 'Y'	"+
		    		 "END FLAG ");
		      r.add("i.id_semakan",r.unquote("s.id_semakan"));
		      r.add("i.id_semakan",r.unquote("sjd.id_semakan(+)"));
		      r.add("sjd.id_jenisdokumen",r.unquote("jd.id_jenisdokumen(+)"));
		      r.add("i.id_semakansenarai",r.unquote("sh.id_semakansenarai(+)"));

		      if(!kodForm.equals("0"))
		    	  r.add("i.kod_form",kodForm);
		      sql = " tblsemakan s,tblsemakansenarai i,tblsemakanjenisdokumen sjd,tblrujjenisdokumen jd,tblsemakanhantar sh";
		      sql = r.getSQLSelect(sql,"i.kod_form,i.aturan");
//	          myLog.info("getSenaraiSemakanAttach :sql= " + sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String,String> h;
		      String lampiran = "-";
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  h.put("aturan", Utils.isNull(rs.getString("aturan")));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  h.put("jenisDokumen", rs.getString("jenis_dokumen"));
		    	  h.put("flag", rs.getString("flag"));
		    	  if(!rs.getString("jenis_dokumen").equals("0")) {
		    		  lampiran = setLampiran(kodForm.substring(0,3),idPermohonan,rs.getString("id_semakansenarai"),rs.getString("jenis_dokumen"));
//		    		  lampiran = "X";
		    	  }else {
		    		  lampiran = "";
		    	  }
		    	  h.put("lampirans", lampiran);
					
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
		  
	private String setLampiran(String modul,String rujukan,String idSenarai,String jenisDokumen) throws Exception {		
		StringBuffer sb = new StringBuffer("");	
		myLog.info("setLampiran:mode="+mode);
//		myLog.info("setLampiran:modul="+modul);
		if(!mode.equals("view")) {
			sb.append("<a href=\"javascript:onlineAttach('"+rujukan+"','"+idSenarai+"','"+jenisDokumen+"');\">");
			sb.append("<img border='0' src='../img/plus.gif' width='20' height='15'/>");
//			sb.append("</a>");
			sb.append("</a><br>");
			
		}
		if(modul.equals("php"))
			sb.append(getDocPHP().getLampirans(rujukan, jenisDokumen,""));
		else
			sb.append(getDoc().getLampirans(rujukan, jenisDokumen,""));

		 //lampiran = lb.getLampirans(idSimati,"paparLampiran");
		 return sb.toString();
		 
	}
//	
	 public static Vector<Hashtable<String,String>> getSenaraiSemakan(String kodForm,String aktif) throws Exception {
		 Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
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
		      
		      Hashtable<String,String> h;

		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  h.put("keterangan", rs.getString("perihal"));
		    	  list.addElement(h);
		      
		      }
		      
		 }catch(Exception e){
		    	e.printStackTrace();
		    
		 }finally {
			 if (db != null) db.close();
		 }
		    
		 return list;
		  
	 }	 
	 
	 public static Vector<Hashtable<String,String>> getSenaraiSemakanHantar(String idpermohonan)throws Exception {
		 Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
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
		      Hashtable<String,String> h = null;

		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("IdSemakansenarai",rs.getString("id_semakansenarai"));
		    	  h.put("IdSemakanhantari",rs.getString("id_semakanhantar"));
		    	  list.addElement(h);
		      }
		      
		 }catch(Exception e){
		    	e.printStackTrace();
		 }finally {
		      if (db != null) db.close();
		 }
		 return list;
		 
	 }
	 
	 /** Semakan yang dihantar*/
	 public static boolean isSemakan(String idpermohonan,String idsemakansenarai) throws Exception {
		 boolean rValue = false;
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("i.id_semakansenarai");
		      
		      //fir modify 19022010
		      if(idpermohonan.equalsIgnoreCase("") || idpermohonan.equalsIgnoreCase(null)){
		    	  r.add("i.id_permohonan",idpermohonan);
		      }else{
		    	  r.add("i.id_permohonan",r.unquote(idpermohonan));
		      }
		      
//		      r.add("i.id_permohonan",r.unquote(idpermohonan));
		      r.add("i.id_semakansenarai",r.unquote(idsemakansenarai));
		      sql = r.getSQLSelect("tblsemakanhantar i");
	    	  //log.info("FrmSemakan:isSemakan("+idpermohonan+","+idsemakansenarai+")::sql:::"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      senarai = new Vector<Hashtable<String,String>>();
		      Hashtable<String,String> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("id", rs.getString("id_semakansenarai"));
		    	  senarai.addElement(h);
		    	  rValue=true;
		      
		      }
		      
		 }catch(Exception e){
		    	e.printStackTrace();
		 }finally {
			 if (db != null) db.close();
		    
		 }
		 return rValue;
		 
	 }

	 public void semakanHapusByPermohonan(String idpermohonan) throws Exception {	
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", r.unquote(idpermohonan));
		      sql = r.getSQLDelete("tblsemakanhantar");
		      stmt.executeUpdate(sql);
		 
		 }catch(Exception e){
		    	e.printStackTrace();
		 
		 }finally	{
		      if (db != null) db.close();
		 }
	  
	 }
	 
	 public void semakanHapusByPermohonan(String idPermohonan,String idSemakanSenarai) throws Exception {
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakanSenarai);
		      sql = r.getSQLDelete("tblsemakanhantar");
		      stmt.executeUpdate(sql);
		    
		 }catch(Exception e){
		    	e.printStackTrace();
		    
		 }finally	{
		      if (db != null) db.close();
	    }
	  
	 }
	 
	 public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
		 try {
		      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
		      String idPermohonan = idpermohonan;
		      String idSemakan = idsemakan;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_semakanhantar", idSemakanhantar);
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idSemakan);
		      sql = r.getSQLInsert("tblsemakanhantar");
		      myLog.info("semakanTambah : "+sql);
		      stmt.executeUpdate(sql);
		 
		 }catch(Exception e){
		    	e.printStackTrace();
		 }finally {
			 if (db != null) db.close();
		 }
		  
	 }
		
	 private ILampiran getDocPHP(){
		if(iLampiranPHP == null){
			iLampiranPHP = new ekptg.model.php2.utiliti.LampiranBean();
		}
		return iLampiranPHP;
				
	 }
	 
	 private ILampiran getDoc(){
		myLog.info("getDoc");
		if(iLampiran == null){
			iLampiran = new ekptg.model.utils.lampiran.LampiranBean();
		}
		return iLampiran;
				
	}

	
}
