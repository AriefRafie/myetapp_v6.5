package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmGadaianSemakanData {
	private static Vector <Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmGadaianSemakanData.class);
	private static Db db = null;
	private static String sql = "";
	private IHTPStatus iStatus = null;
	private String idSuburusanStatusFail = "0";
	//*** query data have 'pemilik' from database
	public static void setSemak(String id)throws Exception {
	    list.clear();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("p.id_Fail");
	      r.add("p.id_Permohonan");
	      r.add("f.id_Negeri");
	      r.add("f.id_Kementerian");
	      r.add("hp.id_Agensi");
	      r.add("f.id_Suburusan");
	      r.add("f.tajuk_Fail");
	      r.add("f.no_Fail");
	      r.add("p.tarikh_Surat");
	      r.add("hp.no_Rujukan_Lain");
	      r.add("p.tarikh_Terima");
	      r.add("hp.tarikh_Agihan");
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("hp.id_Permohonan"));
	      r.set("p.id_Permohonan", id);
	      
	      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblhtppermohonan hp");
	      ResultSet rs = stmt.executeQuery(sql);
	      log.info("FrmGadaianSemakanData::setSemak:SQL: " + sql);
	      Hashtable h = new Hashtable();

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("idKementerian", rs.getString("id_Kementerian"));
	    	  h.put("idAgensi", rs.getString("id_Agensi"));
	    	  h.put("idSuburusan", rs.getString("id_Suburusan"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tSurat", Format.format(rs.getDate("tarikh_Surat")));
	    	  h.put("noRujukan", rs.getString("no_Rujukan_Lain"));
	    	  h.put("tPermohonan", rs.getDate("tarikh_Terima")==null?"":Format.format(rs.getDate("tarikh_Terima")));
	    	  h.put("tAgihan", Format.format(rs.getDate("tarikh_Agihan")));

	    	  list.addElement(h);
	      }
	      
	      log.info("fir FrmGadaianSemakanData Setsemak :: " + h);

	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	        
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector<Hashtable<String,String>> getSemak(){
		  log.info("FrmGadaianSemakanData::getSemak : "+ list.size());		  
		  return list;
	  } 
	  
	//*** query data don't have 'pemilik' from database
		public static void setSemakBaru(String idFail)throws Exception {
			
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
		      r.add("f.id_Fail", r.unquote(idFail));
		      //r.add("f.id_Fail", idFail, "=");
		      
		      sql = r.getSQLSelect("Tblpfdfail f");
		      ResultSet rs = stmt.executeQuery(sql);
		      log.info("::setSemakBaru:: " +sql);
		      Date now = new Date();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idNegeri", rs.getString("id_Negeri"));
		    	  h.put("idKementerian", rs.getString("id_Kementerian"));
		    	  h.put("idSuburusan", rs.getString("id_Suburusan"));
		    	  h.put("tajuk", rs.getString("tajuk_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
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
		  
		  public static Vector<Hashtable<String,String>> getSemakBaru(){
			  log.info("FrmGadaianSemakanData::getSemakBaru:Listsize:" + list.size());
			  return list;
		  }
	  
	  //*** update data from database
	  public static String update(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  String idPermohonan = "0";
		  try {
		      idPermohonan = String.valueOf(data.get("idPermohonan"));
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String NoFailLain = (String)data.get("NoFailLain");
			  String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
		      String idKemaskini = (String)data.get("idKemaskini");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("ID_PERMOHONAN", idPermohonan);
			  r.add("TARIKH_SURAT", r.unquote(TSKJP));
			  r.add("TARIKH_TERIMA", r.unquote(TP));
			  r.add("ID_KEMASKINI",idKemaskini);
			  r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("TBLPERMOHONAN");
		      //log.info("FrmGadaianSemakanData::Update::TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
		      Statement stmtHP = db.getStatement();
			  SQLRenderer rHP = new SQLRenderer();
			  rHP.update("ID_PERMOHONAN", idPermohonan);
			  rHP.add("NO_RUJUKAN_LAIN", NoFailLain);
			  rHP.add("ID_KEMASKINI",idKemaskini);
			  rHP.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
		      sql = rHP.getSQLUpdate("TBLHTPPERMOHONAN");
		      //log.info("FrmGadaianSemakanData::Update::TBLHTPPERMOHONAN = "+sql);
		      stmtHP.executeUpdate(sql);
		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    }finally {
		      if (db != null) db.close();
		    }
		    
		    return idPermohonan;
	  }	  
	  /** simpan data from database
	   * 
	   * @param data
	   * @return
	   * @throws Exception
	   */
	  public String Simpan(Hashtable<String,String> data) throws Exception {
		  String idPermohonan = "0";
		  try {
		      idPermohonan = String.valueOf(DB.getNextID("TBLPERMOHONAN_SEQ"));
		      String idFail = String.valueOf(data.get("IdFail"));
		      String idJKPTG = "1";
		      String noPermohonan = "TIADA";
		      String noPerserahan = "TIADA";
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String tujuan = String.valueOf(data.get("Tajuk"));
		      String idHtppermohonan = String.valueOf(DB.getNextID("TBLHTPPERMOHONAN_SEQ"));
		      int idAgensi = Integer.parseInt(String.valueOf(data.get("socAgensi")));
		      String idSuburusan = String.valueOf(data.get("idSuburusan"));
		      String idJenistanah = "1";
		      String idPegawai = "1";
		      String NoFailLain = (String)data.get("NoFailLain");
		      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      String idMasuk = (String)data.get("idMasuk");
		      
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_permohonan", idPermohonan);
			  r.add("id_fail",idFail);
			  r.add("id_jkptg",idJKPTG);
			  r.add("no_permohonan",noPermohonan);
			  r.add("no_perserahan",noPerserahan);
			  r.add("tarikh_surat", r.unquote(TSKJP));
			  r.add("tarikh_terima", r.unquote(TP));
			  r.add("tujuan",tujuan);
			  r.add("id_masuk",idMasuk);
			  r.add("tarikh_masuk",r.unquote("sysdate"));
			  r.add("id_kemaskini",idMasuk);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLInsert("tblpermohonan");
		      log.info("FrmGadaianSemakanData::simpan::Insert::TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
		      Statement stmtHP = db.getStatement();
			  SQLRenderer rHP = new SQLRenderer();
			  rHP.add("id_htppermohonan",idHtppermohonan);
			  rHP.add("id_permohonan", idPermohonan);
			  rHP.add("id_agensi", idAgensi);
			  rHP.add("id_jenistanah", idJenistanah);
			  rHP.add("id_pegawai", idPegawai);
			  rHP.add("no_rujukan_Lain", NoFailLain);
			  rHP.add("tarikh_Agihan", rHP.unquote(TP));
			  rHP.add("id_masuk",idMasuk);
			  rHP.add("tarikh_masuk",rHP.unquote("sysdate"));
			  rHP.add("id_kemaskini",idMasuk);
			  rHP.add("tarikh_kemaskini",rHP.unquote("sysdate"));
		      sql = rHP.getSQLInsert("tblhtppermohonan");
		      //log.info("FrmGadaianSemakanData::Insert::tarikh_Agihan = "+ TSKJP );
		      stmtHP.executeUpdate(sql);
		      
		      getStatus().statusChangeActionL1(idFail, idPermohonan, idSuburusan, idMasuk);
		      
		    }catch(Exception e){
		    	e.printStackTrace();		    
		    }finally {
		      if (db != null) db.close();		    
		    }		    
		    //log.info("FrmGadaianSemakanData id permohonan : " + idPermohonan);
			return idPermohonan;
	  
	  }
	  /** simpan data from database
	   * 
	   * @param data
	   * @return
	   * @throws Exception
	   */	  
	  public static String simpan(Hashtable<String,String> data) throws Exception {
		  String idPermohonan = "0";
		  try {
		      idPermohonan = String.valueOf(DB.getNextID("TBLPERMOHONAN_SEQ"));
		      String idFail = String.valueOf(data.get("IdFail"));
		      String idJKPTG = "1";
		      String noPermohonan = "TIADA";
		      String noPerserahan = "TIADA";
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String tujuan = String.valueOf(data.get("Tajuk"));
		      String idHtppermohonan = String.valueOf(DB.getNextID("TBLHTPPERMOHONAN_SEQ"));
		      int idAgensi = Integer.parseInt(String.valueOf(data.get("socAgensi")));
		      String idSuburusan = String.valueOf(data.get("idSuburusan"));
		      String idJenistanah = "1";
		      String idPegawai = "1";
		      String NoFailLain = (String)data.get("NoFailLain");
		      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      String idMasuk = (String)data.get("idMasuk");
		      
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_permohonan", idPermohonan);
			  r.add("id_fail",idFail);
			  r.add("id_jkptg",idJKPTG);
			  r.add("no_permohonan",noPermohonan);
			  r.add("no_perserahan",noPerserahan);
			  r.add("tarikh_surat", r.unquote(TSKJP));
			  r.add("tarikh_terima", r.unquote(TP));
			  r.add("tujuan",tujuan);
			  r.add("id_masuk",idMasuk);
			  r.add("tarikh_masuk",r.unquote("sysdate"));
			  r.add("id_kemaskini",idMasuk);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLInsert("tblpermohonan");
		      log.info("FrmGadaianSemakanData::simpan::Insert::TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
		      Statement stmtHP = db.getStatement();
			  SQLRenderer rHP = new SQLRenderer();
			  rHP.add("id_htppermohonan",idHtppermohonan);
			  rHP.add("id_permohonan", idPermohonan);
			  rHP.add("id_agensi", idAgensi);
			  rHP.add("id_jenistanah", idJenistanah);
			  rHP.add("id_pegawai", idPegawai);
			  rHP.add("no_rujukan_Lain", NoFailLain);
			  rHP.add("tarikh_Agihan", rHP.unquote(TP));
			  rHP.add("id_masuk",idMasuk);
			  rHP.add("tarikh_masuk",rHP.unquote("sysdate"));
			  rHP.add("id_kemaskini",idMasuk);
			  rHP.add("tarikh_kemaskini",rHP.unquote("sysdate"));
		      sql = rHP.getSQLInsert("tblhtppermohonan");
		      //log.info("FrmGadaianSemakanData::Insert::tarikh_Agihan = "+ TSKJP );
		      stmtHP.executeUpdate(sql);
		      
		      StatusChange_Action(idFail, idPermohonan, idSuburusan, idMasuk);
		      
		    }catch(Exception e){
		    	e.printStackTrace();		    
		    }finally {
		      if (db != null) db.close();		    
		    }		    
		    //log.info("FrmGadaianSemakanData id permohonan : " + idPermohonan);
			return idPermohonan;
	  
	  }
	  
	  public static String simpanOnline(Hashtable data) throws Exception {
		  Db db = null;
		  String sql = "";
		  String idPermohonan = "0";
		  String kodNegeriMampu = "";
		  String kodKementerianMampu = "";
		  try {
		      idPermohonan = String.valueOf(DB.getNextID("TBLPERMOHONAN_SEQ"));
		      String idFail = String.valueOf(data.get("IdFail"));
		      String idJKPTG = "1";
		      String noPermohonan = " ";
		      String noPerserahan = " ";
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String tujuan = (String)data.get("Tajuk");
		      String idHtppermohonan = String.valueOf(DB.getNextID("TBLHTPPERMOHONAN_SEQ"));
		      int idAgensi = Integer.parseInt(data.get("socAgensi").toString());
		      String idSuburusan = String.valueOf(data.get("idSuburusan"));
		      String idJenistanah = "1";
		      String idPegawai = "1";
		      String NoFailLain = (String)data.get("NoFailLain");
		      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  //Date now = new Date();
		      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		      String idMasuk = (String)data.get("idMasuk");
		      
		      Long setIdStatus = 0L;
		      setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",String.valueOf(idSuburusan),"=");
		      
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("id_Permohonan", idPermohonan);
			  r.add("id_Fail",idFail);
			  r.add("id_Jkptg",idJKPTG);
			  r.add("ID_STATUS",setIdStatus);
			  //r.add("no_Permohonan",FrmUtilData.generateNoOnline(2,kodKementerianMampu, String.valueOf(idKem), kodNegeriMampu, idNeg));
			  r.add("no_Perserahan",noPerserahan);
			  r.add("tarikh_Surat", r.unquote(TSKJP));
			  r.add("tarikh_Terima", r.unquote(TP));
			  r.add("tujuan",tujuan);
			  r.add("id_Masuk",idMasuk);
			  r.add("tarikh_Masuk",r.unquote("sysdate"));
			  r.add("id_Kemaskini",idMasuk);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLInsert("Tblpermohonan");
		      log.info("FrmGadaianSemakanData::simpan::Insert::TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
		      Statement stmtHP = db.getStatement();
			  SQLRenderer rHP = new SQLRenderer();
			  rHP.add("id_Htppermohonan",idHtppermohonan);
			  rHP.add("id_Permohonan", idPermohonan);
			  rHP.add("id_Agensi", idAgensi);
			  rHP.add("id_Jenistanah", idJenistanah);
			  rHP.add("id_Pegawai", idPegawai);
			  rHP.add("no_Rujukan_Lain", NoFailLain);
			  rHP.add("tarikh_Agihan", rHP.unquote(TP));
			  rHP.add("id_Masuk",idMasuk);
			  rHP.add("tarikh_Masuk",rHP.unquote("sysdate"));
			  rHP.add("id_Kemaskini",idMasuk);
			  rHP.add("tarikh_Kemaskini",rHP.unquote("sysdate"));
		      sql = rHP.getSQLInsert("Tblhtppermohonan");
		      //log.info("FrmGadaianSemakanData::Insert::tarikh_Agihan = "+ TSKJP );
		      log.info("FrmGadaianSemakanData::Insert::TBLHTPPERMOHONAN = "+sql);
		      stmtHP.executeUpdate(sql);
		      
//		      StatusChange_Action(idPermohonan, idSuburusan);
		      //fir
		      StatusChange_ActionOnline(idPermohonan, idSuburusan, idFail,idMasuk);
		      //FrmPembelianSemakanData.StatusChange_Action(Long.parseLong(idPermohonan), Integer.parseInt(String.valueOf(idSuburusan)), Long.parseLong(idFail));

		      
		    }catch(Exception e){
		    	e.printStackTrace();
		    
		    }finally {
		      if (db != null) db.close();
		    
		    }
		    
		    //log.info("FrmGadaianSemakanData id permohonan : " + idPermohonan);
			return String.valueOf(idPermohonan);
	  
	  }
	  
//	  public static void StatusChange_Action(long idPermohonan, int idSuburusan){
	  public static void StatusChange_Action(String idPermohonan,String idSuburusan,String idFail,String user){
		  int PPP = Integer.parseInt("31"); //PINJAMAN PERUMAHAN PERBENDAHARAAN
		  int PKRJPN = Integer.parseInt("57"); //PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGARA
		  int TPM = Integer.parseInt("60"); //PINJAMAN TMP & JPK
		  int PHG = Integer.parseInt("63"); //PERLETAKHAKAN HAK GADAIAN		  
		  String aktif = "1";
		  //Date now = new Date();
		  //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		  //String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		  
		  try{
			  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");	
			  Long setIdSuburusanstatus = 0L;
			  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSuburusan,"=");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  
			  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  r.add("id_Permohonan", idPermohonan);
			  
			 /* if(idSuburusan == 38){
				  r.add("Id_Suburusanstatus", PPP);
			  }else if(idSuburusan == 39){
				  r.add("Id_Suburusanstatus", PKRJPN);
			  }else if(idSuburusan == 40){
				  r.add("Id_Suburusanstatus", TPM);
			  }else{
				  r.add("Id_Suburusanstatus", PHG);
			  }	*/		  
			  r.add("Id_Suburusanstatus",setIdSuburusanstatus);
			  r.add("aktif",aktif);
			  r.add("ID_MASUK", r.unquote(user));
			  r.add("TARIKH_MASUK", r.unquote("sysdate"));
			  r.add("ID_KEMASKINI", r.unquote(user));
			  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			  //fir
			  r.add("id_fail", r.unquote(idFail));
			  
			  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
		      log.info("FrmGadaianSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
		      stmt.executeUpdate(sql);
		      
		  }catch(Exception ex){
			  //log.info("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
			  ex.printStackTrace();
			  
		  }finally{
			  if (db != null) {
				  db.close();
			  }
		  }		  
		  
	  }
	  
	  public static void StatusChange_ActionOnline(String idPermohonan,String idSuburusan,String idFail,String user){
		  int PPP = Integer.parseInt("31"); //PINJAMAN PERUMAHAN PERBENDAHARAAN
		  int PKRJPN = Integer.parseInt("57"); //PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGARA
		  int TPM = Integer.parseInt("60"); //PINJAMAN TMP & JPK
		  int PHG = Integer.parseInt("63"); //PERLETAKHAKAN HAK GADAIAN		  
		  String aktif = "1";
		  //Date now = new Date();
		  //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		  //String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		  
		  Db db = null;
		  String sql = "";
		  
		  try{
			  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");	
			  Long setIdSuburusanstatus = 0L;
			  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("-1",idSuburusan,"=");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  
			  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  r.add("id_Permohonan", idPermohonan);
			  
			 /* if(idSuburusan == 38){
				  r.add("Id_Suburusanstatus", PPP);
			  }else if(idSuburusan == 39){
				  r.add("Id_Suburusanstatus", PKRJPN);
			  }else if(idSuburusan == 40){
				  r.add("Id_Suburusanstatus", TPM);
			  }else{
				  r.add("Id_Suburusanstatus", PHG);
			  }	*/		  
			  r.add("Id_Suburusanstatus",setIdSuburusanstatus);
			  r.add("aktif",aktif);
			  r.add("ID_MASUK", r.unquote(user));
			  r.add("TARIKH_MASUK", r.unquote("sysdate"));
			  r.add("ID_KEMASKINI", r.unquote(user));
			  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			  //fir
			  r.add("id_fail", r.unquote(idFail));
			  
			  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
		      log.info("FrmGadaianSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
		      stmt.executeUpdate(sql);
		      
		  }catch(Exception ex){
			  //log.info("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
			  ex.printStackTrace();
			  
		  }finally{
			  if (db != null) {
				  db.close();
			  }
		  }		  
	  }
		private IHTPStatus getStatus() {
			if (iStatus == null) {
				iStatus = new HTPStatusBean();
			}
			return iStatus;

		}

	  
	  
}
