package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmGadaianSemakan1Data {
	private static Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmGadaianSemakan1Data.class);
	
	//** query data have 'pemilik' from database */
	public static void setSemak(String idFail)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	
	      r.add("f.id_Fail");
	      r.add("f.id_Negeri");
	      r.add("f.id_Kementerian");
	      r.add("f.id_Suburusan");
	      r.add("f.tajuk_Fail");
	      r.add("f.no_Fail");
	      r.add("f.tarikh_Daftar_Fail");
	      r.add("f.id_Fail",idFail);
	      
	      sql = r.getSQLSelect("Tblpfdfail f");
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info(sql);
	      
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
		      h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("idKementerian", rs.getString("id_Kementerian"));
	    	  h.put("idSuburusan", rs.getString("id_Suburusan"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tarikhDaftarFail", Format.format(rs.getDate("tarikh_Daftar_Fail")));
	    	  list.addElement(h);
	      }

	    }
	    catch(Exception e){
	    	  e.printStackTrace();
	      }
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	  }
	  
	  public static Vector<Hashtable<String,String>> getSemak(){
		  log.info("FrmGadaianSemakan1Data :: getSemak::" + list.size());
		  return list;
	  }	  
	  
	//*** update data from database
	  public static String update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String idFail = "0";
	    
	    try{
	      db = new Db();
	      
	      idFail = String.valueOf(data.get("idFail"));
	      String tujuan = (String)data.get("Tajuk");
	      
	      Statement stmtFail = db.getStatement();
		  SQLRenderer rFail = new SQLRenderer();
		  rFail.update("id_Fail", idFail);
		  rFail.add("tajuk_Fail", tujuan);
		  rFail.add("id_kemaskini", data.get("idKemaskini"));
		  rFail.add("tarikh_Kemaskini", rFail.unquote("sysdate"));
		  sql = rFail.getSQLUpdate("Tblpfdfail");
	      log.info("FrmGadaianSemakanData::Update::TBLPFDFAIL = "+sql);
	      stmtFail.executeUpdate(sql);
	     
	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null){
	    	  db.close();
	      }
	    }
	    return idFail;
	  }
	  
	//*** simpan data from database (this method is for automated id generation)
//	  public static int simpan(Hashtable data) throws Exception {
//		  Db db = null;
//		  String sql = "";
//		  long idFail = 0L;
//		  try{
//		      db = new Db();
//		      idFail = DB.getNextID("TBLPFDFAIL_SEQ");
//		      String kodJabatan = "JKPTG";
//		      int idTarafKeselamatan = Integer.parseInt("1");
//		      int idSeksyen = Integer.parseInt("11");
//		      int idUrusan = Integer.parseInt("108");
//		      int idSuburusan = (Integer)data.get("idSuburusan");
//		      int idNegeri = (Integer)data.get("idNegeri");
//		      int idKementerian = (Integer)data.get("idKementerian");
//		      Date now = new Date();
//		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
//		      String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
//		      int idStatus = Integer.parseInt("7");
//		      String tujuan = (String)data.get("Tajuk");
//		      String kodUrusan = "";
//		      String kodMampu = "";
//		      
//		      Statement stmturus = db.getStatement();
//		      SQLRenderer rUrus = new SQLRenderer();				 
//		      rUrus.add("id_Urusan");
//				      rUrus.add("kod_Urusan");				      
//				      rUrus.add("id_Urusan",idUrusan);				      
//				      sql = rUrus.getSQLSelect("Tblrujurusan");
//				      ResultSet rsUrus = stmturus.executeQuery(sql);
//				      while (rsUrus.next()) {
//				    	  kodUrusan = rsUrus.getString("kod_Urusan");
//				      }
//				      Statement stmtnegeri = db.getStatement();
//				      SQLRenderer rnegeri = new SQLRenderer();				 
//				      rnegeri.add("id_Negeri");
//				      rnegeri.add("kod_Mampu");				      
//				      rnegeri.add("id_Negeri",idNegeri);				      
//				      sql = rnegeri.getSQLSelect("Tblrujnegeri");
//				      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
//				      while (rsnegeri.next()) {
//				    	  kodMampu = rsnegeri.getString("kod_Mampu");
//				      }
//		      
//		      String noFail = kodJabatan+"/101/"+kodUrusan+"/"+idKementerian+"/"+kodMampu+"-"+File.getSeqNo(idSeksyen, idUrusan, idKementerian, idNegeri);
//		      log.info("nofail = "+noFail);
//		      String noFailRoot = "TIADA";
//		      //rosli
//		      //String lokasi = "TIADA";
//		      int lokasi = Integer.parseInt("1");
//		      int flagFail = Integer.parseInt("1");
//		      //rosli	
//		      //String faharasat = "TIADA";
//		      int faharasat = Integer.parseInt("1");
//		      
//		      Statement stmtFail = db.getStatement();
//			  SQLRenderer rFail = new SQLRenderer();
////			  rFail.add("id_Fail", idFail);
//			  rFail.add("id_Fail", rFail.unquote("" + idFail));
//			  rFail.add("id_Tarafkeselamatan", idTarafKeselamatan);
//			  rFail.add("id_Seksyen",idSeksyen);
//			  rFail.add("id_Urusan",idUrusan);
//			  rFail.add("id_Suburusan", idSuburusan);
//			  rFail.add("tarikh_Daftar_Fail", rFail.unquote(tarikhDaftarFail));
//			  rFail.add("flag_Fail", flagFail);
//			  rFail.add("tajuk_Fail", tujuan);
//			  rFail.add("no_Fail", noFail);
//			  rFail.add("no_Fail_Root", noFailRoot);
//			  //rosli
//			  //rFail.add("lokasi", lokasi);
//			  rFail.add("id_lokasifail", lokasi);
//			  rFail.add("id_Negeri", idNegeri);
//			  rFail.add("id_Kementerian", idKementerian);
//			  rFail.add("id_Status", idStatus);
//			  //rFail.add("faharasat", faharasat);
//			  //rosli
//			  rFail.add("id_faharasat", faharasat);
//			  rFail.add("id_Masuk", idFail);
//			  rFail.add("tarikh_Masuk", rFail.unquote(tarikhDaftarFail));
//			  rFail.add("id_Kemaskini", idFail);
//			  rFail.add("tarikh_Kemaskini", rFail.unquote(tarikhDaftarFail));
//		      sql = rFail.getSQLInsert("Tblpfdfail");
//		      log.info("simpan("+data+":Insert-TBLPFDFAIL::"+sql);
//		      stmtFail.executeUpdate(sql);	      
//		      
//		    }
//		  	catch(Exception e){
//		  		e.printStackTrace();
//		  	}
//		    finally {
//		      if (db != null) db.close();
//		    }
//		    
//		    return (int)idFail;
//		  }
	  
	//*** simpan data to database (idfail is manual)
	  public static String simpan(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  String idFail = "0";
	      String kodUrusan = "";
	      String kodMampu = "";
	      String namaNegeri = "";
		  try{
		      db = new Db();
		      idFail = String.valueOf(DB.getNextID("TBLPFDFAIL_SEQ"));
		      String kodJabatan = "JKPTG";
		      int idTarafKeselamatan = Integer.parseInt("1");
		      int idSeksyen = Integer.parseInt("3");
		      int idUrusan = Integer.parseInt("108");
		      int idSuburusan = Integer.parseInt(String.valueOf(data.get("idSuburusan")));
		      int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
		      int idKementerian = Integer.parseInt(String.valueOf(data.get("idKementerian")));
		      //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      int idStatus = Integer.parseInt("7");
		      String tujuan = (String)data.get("Tajuk");
		      
		      Statement stmturus = db.getStatement();
		      SQLRenderer rUrus = new SQLRenderer();				 
		      rUrus.add("id_Urusan");
		      rUrus.add("kod_Urusan");				      
			  rUrus.add("id_Urusan",idUrusan);	
			  
			  sql = rUrus.getSQLSelect("Tblrujurusan");
			  ResultSet rsUrus = stmturus.executeQuery(sql);
				      
			  while (rsUrus.next()) {
			   	  kodUrusan = rsUrus.getString("kod_Urusan");
			  }
				      
			  Statement stmtnegeri = db.getStatement();
			  SQLRenderer rnegeri = new SQLRenderer();				 
			  rnegeri.add("id_Negeri");
			  rnegeri.add("kod_Mampu");	
			  rnegeri.add("nama_negeri");
			  rnegeri.add("id_Negeri",idNegeri);				      
			  sql = rnegeri.getSQLSelect("Tblrujnegeri");
			  ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
			  while (rsnegeri.next()) {
			   	  kodMampu = rsnegeri.getString("kod_Mampu");
			   	  namaNegeri = rsnegeri.getString("nama_negeri");
			  }
		      
		      String noFail = kodJabatan+"/101/"+kodUrusan+"/"+idKementerian+"/"+kodMampu+"-"+File.getSeqNo(idSeksyen, idUrusan, idKementerian, idNegeri);
			  
			  tujuan += "BAGI NEGERI ";
			  tujuan += namaNegeri;		  
			  
//			  String noFail = (String)data.get("noFailSek");
		      log.info("nofail = "+noFail);
		      String noFailRoot = "TIADA";
		      //rosli
		      //String lokasi = "TIADA";
		      int lokasi = Integer.parseInt("1");
		      int flagFail = Integer.parseInt("1");
		      //rosli	
		      //String faharasat = "TIADA";
		      int faharasat = Integer.parseInt("1");
			  String TarikhBukaFail = (String)data.get("tarikhBukaFail");
			  String TBF = "to_date('" + TarikhBukaFail + "','dd/MM/yyyy')";
		      Statement stmtFail = db.getStatement();
			  SQLRenderer rFail = new SQLRenderer();
//			  rFail.add("id_Fail", idFail);
			  rFail.add("id_Fail", rFail.unquote(String.valueOf(idFail)));
			  rFail.add("id_Tarafkeselamatan", idTarafKeselamatan);
			  rFail.add("id_Seksyen",idSeksyen);
			  rFail.add("id_Urusan",idUrusan);
			  rFail.add("id_Suburusan", idSuburusan);
			  rFail.add("tarikh_Daftar_Fail", rFail.unquote(TBF));
			  rFail.add("flag_Fail", flagFail);
			  rFail.add("tajuk_Fail", tujuan);
			  rFail.add("no_Fail", noFail);
			  rFail.add("no_Fail_Root", noFailRoot);
			  //rosli
			  //rFail.add("lokasi", lokasi);
			  rFail.add("id_lokasifail", lokasi);
			  rFail.add("id_Negeri", idNegeri);
			  rFail.add("id_Kementerian", idKementerian);
			  rFail.add("id_Status", idStatus);
			  //rFail.add("faharasat", faharasat);
			  //rosli
			  rFail.add("id_faharasat", faharasat);
			  rFail.add("id_Masuk", data.get("idMasuk"));
			  rFail.add("tarikh_Masuk", rFail.unquote("sysdate"));
			  rFail.add("id_Kemaskini", data.get("idMasuk"));
			  rFail.add("tarikh_Kemaskini", rFail.unquote("sysdate"));
		      sql = rFail.getSQLInsert("Tblpfdfail");
		      log.info("simpan("+data+":Insert-TBLPFDFAIL::"+sql);
		      stmtFail.executeUpdate(sql);			      
		      
		      //fir test untuk kes soc sahaja
		      if(tujuan.contains("SATISFACTION OF CHARGE")){
		    	  
		    	  Statement stmtMapping = db.getStatement();	    	  
		    	  SQLRenderer rHTPMapping = new SQLRenderer();
		    	  long idFailMapping = DB.getNextID("TBLHTPFAILMAPPING_SEQ");
		    	  rHTPMapping.add("id_htpfailmapping", idFailMapping);
		    	  rHTPMapping.add("id_fail", idFail);
		    	  rHTPMapping.add("id_faillama", idFail);
		    	  rHTPMapping.add("id_kemaskini", data.get("idMasuk"));
		    	  
		    	  String sqlMap = rHTPMapping.getSQLInsert("TBLHTPFAILMAPPING");	    	  
		    	  log.info("SOC Mapping :" + sqlMap);	    	  
		    	  stmtMapping.executeUpdate(sqlMap);
		    	  
		      }
		      
		    }catch(Exception e){
		  		e.printStackTrace();
		  	
		    }finally {
		      if (db != null) db.close();
		    }
		    
		    return idFail;
		    
		  }
	  
	  public static String simpanOnline(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  String idFail = "0";
	      String kodUrusan = "";
	      String kodMampu = "";
	      String namaNegeri = "";
		  try{
		      db = new Db();

		      idFail = String.valueOf(DB.getNextID("TBLPFDFAIL_SEQ"));
		      String kodJabatan = "JKPTG";
		      int idTarafKeselamatan = Integer.parseInt("1");
		      int idSeksyen = Integer.parseInt("11");
		      int idUrusan = Integer.parseInt("108");
		      int idSuburusan = (Integer)data.get("idSuburusan");
		      int idNegeri = (Integer)data.get("idNegeri");
		      int idKementerian = (Integer)data.get("idKementerian");
		      Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      int idStatus = Integer.parseInt("7");
		      String tujuan = (String)data.get("Tajuk");

		      
		      Statement stmturus = db.getStatement();
		      SQLRenderer rUrus = new SQLRenderer();				 
		      rUrus.add("id_Urusan");
		      rUrus.add("kod_Urusan");				      
			  rUrus.add("id_Urusan",idUrusan);	
			  
			  sql = rUrus.getSQLSelect("Tblrujurusan");
			  ResultSet rsUrus = stmturus.executeQuery(sql);
				      
			  while (rsUrus.next()) {
			   	  kodUrusan = rsUrus.getString("kod_Urusan");
			  }
				      
			  Statement stmtnegeri = db.getStatement();
			  SQLRenderer rnegeri = new SQLRenderer();				 
			  rnegeri.add("id_Negeri");
			  rnegeri.add("kod_Mampu");	
			  rnegeri.add("nama_negeri");
			  rnegeri.add("id_Negeri",idNegeri);				      
			  sql = rnegeri.getSQLSelect("Tblrujnegeri");
			  ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
			  while (rsnegeri.next()) {
			   	  kodMampu = rsnegeri.getString("kod_Mampu");
			   	  namaNegeri = rsnegeri.getString("nama_negeri");
			  }
		      
//		      String noFail = kodJabatan+"/101/"+kodUrusan+"/"+idKementerian+"/"+kodMampu+"-"+File.getSeqNo(idSeksyen, idUrusan, idKementerian, idNegeri);
			  
			  tujuan += "BAGI NEGERI ";
			  tujuan += namaNegeri;
			  
			  
			  String noFail = (String)data.get("noFailSek");
		      log.info("nofail = "+noFail);
		      String noFailRoot = "TIADA";
		      //rosli
		      //String lokasi = "TIADA";
		      int lokasi = Integer.parseInt("1");
		      int flagFail = Integer.parseInt("1");
		      //rosli	
		      //String faharasat = "TIADA";
		      int faharasat = Integer.parseInt("1");
		      
		      Statement stmtFail = db.getStatement();
			  SQLRenderer rFail = new SQLRenderer();
//			  rFail.add("id_Fail", idFail);
			  rFail.add("id_Fail", rFail.unquote("" + idFail));
			  rFail.add("id_Tarafkeselamatan", idTarafKeselamatan);
			  rFail.add("id_Seksyen",idSeksyen);
			  rFail.add("id_Urusan",idUrusan);
			  rFail.add("id_Suburusan", idSuburusan);
			  rFail.add("tarikh_Daftar_Fail", rFail.unquote(tarikhDaftarFail));
			  rFail.add("flag_Fail", flagFail);
			  rFail.add("tajuk_Fail", tujuan);
			  rFail.add("no_Fail", noFail);
			  rFail.add("no_Fail_Root", noFailRoot);
			  //rosli
			  //rFail.add("lokasi", lokasi);
			  rFail.add("id_lokasifail", lokasi);
			  rFail.add("id_Negeri", idNegeri);
			  rFail.add("id_Kementerian", idKementerian);
			  rFail.add("id_Status", idStatus);
			  //rFail.add("faharasat", faharasat);
			  //rosli
			  rFail.add("id_faharasat", faharasat);
			  rFail.add("id_Masuk", idFail);
			  rFail.add("tarikh_Masuk", rFail.unquote(tarikhDaftarFail));
			  rFail.add("id_Kemaskini", idFail);
			  rFail.add("tarikh_Kemaskini", rFail.unquote(tarikhDaftarFail));
		      sql = rFail.getSQLInsert("Tblpfdfail");
		      log.info("simpan("+data+":Insert-TBLPFDFAIL::"+sql);
		      stmtFail.executeUpdate(sql);	
		      
		      
		      //fir test untuk kes soc sahaja
		      if(tujuan.contains("SATISFACTION OF CHARGE")){
		    	  
		    	  Statement stmtMapping = db.getStatement();
		    	  
		    	  SQLRenderer rHTPMapping = new SQLRenderer();
		    	  long idFailMapping = DB.getNextID("TBLHTPFAILMAPPING_SEQ");
		    	  rHTPMapping.add("id_htpfailmapping", idFailMapping);
		    	  rHTPMapping.add("id_fail", idFail);
		    	  rHTPMapping.add("id_faillama", idFail);
		    	  rHTPMapping.add("id_kemaskini", idFailMapping);
		    	  
		    	  String sqlMap = rHTPMapping.getSQLInsert("TBLHTPFAILMAPPING");
		    	  
		    	  log.info("SOC Mapping :" + sqlMap);
		    	  
		    	  stmtMapping.executeUpdate(sqlMap);

		    	  
		      }
		      
		    }
		  	catch(Exception e){
		  		e.printStackTrace();
		  	}
		    finally {
		      if (db != null) db.close();
		    }	    
		    return idFail;
		    
		  }
	    
	  
	  
	  
	  /*
	  public static void StatusChange_Action(long idPermohonan, int idSuburusan){
		  
		  int PPP = Integer.parseInt("31"); //PINJAMAN PERUMAHAN PERBENDAHARAAN
		  int PKRJPN = Integer.parseInt("57"); //PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGARA
		  int TPM = Integer.parseInt("60"); //PINJAMAN TMP & JPK
		  int PHG = Integer.parseInt("63"); //PERLETAKHAKAN HAK GADAIAN
		  String aktif = "1";
		  Date now = new Date();
		  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		  
		  Db db = null;
		  String sql = "";
		  
		  try{
			  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  
			  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  r.add("id_Permohonan", idPermohonan);
			  
			  if(idSuburusan == 38){
				  r.add("Id_Suburusanstatus", PPP);
			  }else if(idSuburusan == 39){
				  r.add("Id_Suburusanstatus", PKRJPN);
			  }else if(idSuburusan == 40){
				  r.add("Id_Suburusanstatus", TPM);
			  }else{
				  r.add("Id_Suburusanstatus", PHG);
			  }			  
			  r.add("aktif",aktif);
			  r.add("id_Masuk", idPermohonan);
			  r.add("tarikh_Masuk", r.unquote(sekarang));
			  
			  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
		      System.out.println("FrmGadaianSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
		      stmt.executeUpdate(sql);
		  }catch(Exception ex){
			 // System.out.println("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
			  ex.printStackTrace();
		  }finally{
			  if (db != null) {
				  db.close();
			  }
		  }		  
	  }
	  */
}
