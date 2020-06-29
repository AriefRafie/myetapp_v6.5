package ekptg.model.htp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujjenistanah;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujstatus;
import ekptg.model.entities.Tblrujsubkategori;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.entities.Tblrujurusan;
import ekptg.model.htp.rekod.FrmRekodUtilData;

public class FrmUtilData extends EkptgCache implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5355029536510446896L;
	static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmUtilData.class);
	private static IHtp iHTP = null;  
	Date now = new Date();
	Tblrujsuburusanstatusfail rsusf = null;
	public String strNoFail = "";
//	private static Connection conn = null;
//	private static Db db = null;
	//private static FrmUtilData instance = null;

	//public static FrmUtilData getInstance(){
	//    if (instance == null) instance = new FrmUtilData();
	//    return instance;
	//}
	public static Vector<Hashtable<String,String>> vec = null;

	public void kemaskiniStatusPermohonan(String idPermohonan,String idStatus) throws Exception {
		Db db = null;
		try{ 
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			String date_update = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", idStatus);
			r.add("tarikh_Kemaskini",r.unquote(date_update));
			stmt.executeUpdate(r.getSQLUpdate("TBLPERMOHONAN"));

		}finally {
		      if (db != null) db.close();
		    }
		  
	}
	 
	public static void simpanFailPK(Hashtable<String,String> data) throws Exception{
		 String sql = "";
		 Db db = null;
		 try{	 
			 String catatan = String.valueOf(data.get("catatan"));		 
			 String idFail = String.valueOf(data.get("id_Fail"));
			 String idMasuk = data.get("id_Masuk");
			 String faharasat = data.get("id_Faharasat");
			 String flagFail = data.get("flag_Fail");		 
			 String kementerian = data.get("id_Kementerian");
			 String lokasi = data.get("id_Lokasi");			 
			 String namaFail = data.get("tajuk_Fail");
			 String negeri = data.get("id_Negeri");
			 String noFail = data.get("no_Fail");
			 String noFailroot = data.get("no_Failroot");
			 String seksyen = data.get("id_Seksyen");
			 String status = data.get("id_Status");
			 String subUrusan = data.get("id_Suburusan");
			 String tarafKeselamatan = data.get("id_Tarafkeselamatan");
			 String tarikhBukafail = "to_date('" + data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";
			 String tarikhMasuk = "to_date('" + data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";
			 String urusan = data.get("id_Urusan");

			 db = new Db();
			 Statement stmt = db.getStatement();
			 
			 SQLRenderer r = new SQLRenderer();
			 r.add("id_Fail",idFail); 
			 r.add("id_Tarafkeselamatan", tarafKeselamatan);
			 r.add("id_Seksyen", seksyen);
			 r.add("id_Urusan", urusan);
			 r.add("id_Suburusan", subUrusan);
			 r.add("tarikh_Daftar_Fail",r.unquote(tarikhBukafail)); 
			 r.add("tajuk_Fail", namaFail);
			 r.add("no_Fail", noFail);
			 r.add("no_fail_root", noFailroot);			      
			 r.add("id_Lokasifail", lokasi);
			 r.add("id_Negeri", negeri);
			 r.add("id_Kementerian", kementerian);			      
			 r.add("id_Faharasat", faharasat);
			 r.add("flag_Fail", flagFail);
			 r.add("id_Status", status);
			 r.add("catatan",catatan);
			 r.add("id_Masuk",idMasuk);				      
			 r.add("tarikh_Masuk",r.unquote(tarikhMasuk)); 
			 r.add("id_Kemaskini",idMasuk);				      
			 r.add("tarikh_Kemaskini",r.unquote(tarikhMasuk)); 
			 r.add("tarikh_Tukar_Taraf",r.unquote(tarikhMasuk)); 
			 r.add("id_Db",r.unquote("99")); 
			 r.add("no_perserahan","TIADA"); 
			 r.add("Flag_jenis_fail","1");	//Fail Biasa =  1,Lama = 2,Kutipan Data = 3
			 sql = r.getSQLInsert("tblpfdfail");
			 myLog.info("simpanFail("+data+"):sql="+sql);
			 stmt.executeUpdate(sql);
			 
		 } catch (Exception e) {
				e.printStackTrace();
				getIHTP().getErrorHTML(e.getMessage().toString());
			
		 } finally {
			 if (db != null) db.close();
		 }
				      
	 }
	/* Created by : Mohamad Rosli 2009
	 * Tujuan	  : Simpan data on TBLPFDFAIL 
	 * return  
	 * 		Cukai,Pembelian
	*/
	 public static void simpanFail(Hashtable<?, ?> data) throws Exception{
		 String sql = "";
		 Db db = null;
		 try{	 
			 long idFail = (Long)data.get("id_Fail");
			 int tarafKeselamatan = (Integer)data.get("id_Tarafkeselamatan");
			 int seksyen = (Integer)data.get("id_Seksyen");
			 int urusan = (Integer)data.get("id_Urusan");
			 int subUrusan = (Integer)data.get("id_Suburusan");
			 String tarikhBukafail = "to_date('" + (String)data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";
			 String namaFail = (String)data.get("tajuk_Fail");
			 String noFail = (String)data.get("no_Fail");
			 String noFailroot = (String)data.get("no_Failroot");
			 int lokasi = (Integer)data.get("id_Lokasi");			 
			 int negeri = (Integer)data.get("id_Negeri");
			 int kementerian = (Integer)data.get("id_Kementerian");
			 int faharasat = (Integer)data.get("id_Faharasat");
			 String flagFail = (String)data.get("flag_Fail");		 
			 int status = (Integer)data.get("id_Status");
			 String catatan = (String)data.get("catatan");		 
//			 String idMasuk = (String)data.get("id_Masuk");
			 String idMasuk = data.get("id_Masuk").toString();
			 //String tarikhMasuk = (String)data.get("tarikh_Masuk");	
			 String tarikhMasuk = "to_date('" + (String)data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";

			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			/*		 ID_FAIL	1	1	N	NUMBER		Yes		
					 ID_TARAFKESELAMATAN	2		Y	NUMBER		Yes		
					 ID_SEKSYEN	3		Y	NUMBER		Yes		
					 ID_URUSAN	4		Y	NUMBER		Yes		
					 ID_SUBURUSAN	5		Y	NUMBER		Yes		
					 TARIKH_DAFTAR_FAIL	6		Y	DATE		Yes		
					 TAJUK_FAIL	7		Y	VARCHAR2 (300 Byte)		Yes		
					 NO_FAIL	8		Y	VARCHAR2 (400 Byte)		Yes		
					 NO_FAIL_ROOT	9		Y	VARCHAR2 (400 Byte)		Yes		
					 ID_LOKASIFAIL	10		Y	NUMBER		Yes		
					 ID_NEGERI	11		Y	NUMBER		Yes		
					 ID_KEMENTERIAN	12		Y	NUMBER		Yes		
					 ID_FAHARASAT	13		Y	NUMBER		Yes		
					 FLAG_FAIL	14		Y	NUMBER		Yes		
					 ID_STATUS	15		Y	NUMBER		Yes		
					 CATATAN	16		Y	VARCHAR2 (4000 Byte)		Yes		
					 ID_MASUK	17		Y	NUMBER		Yes		
					 TARIKH_MASUK	18		Y	DATE		Yes		
					 ID_KEMASKINI	19		Y	NUMBER		Yes		
					 TARIKH_KEMASKINI	20		Y	DATE		Yes		
					 TARIKH_TUKAR_TARAF	21		Y	DATE		Yes		
					 ID_DB	22		Y	NUMBER		No		
					 NO_PERSERAHAN	23		Y	VARCHAR2 (400 Byte)		No		
					 FLAG_JENIS_FAIL	24		Y	VARCHAR2 (1 Byte)		No		
			 		*/
			      r.add("id_Fail",idFail); 
			      r.add("id_Tarafkeselamatan", tarafKeselamatan);
			      r.add("id_Seksyen", seksyen);
			      r.add("id_Urusan", urusan);
			      r.add("id_Suburusan", subUrusan);
			      r.add("tarikh_Daftar_Fail",r.unquote(tarikhBukafail)); 
			      r.add("tajuk_Fail", namaFail);
			      r.add("no_Fail", noFail);
			      r.add("no_fail_root", noFailroot);			      
			      r.add("id_Lokasifail", lokasi);
			      r.add("id_Negeri", negeri);
			      r.add("id_Kementerian", kementerian);			      
			      r.add("id_Faharasat", faharasat);
			      r.add("flag_Fail", flagFail);
			      r.add("id_Status", status);
			      r.add("catatan",catatan);
			      r.add("id_Masuk",idMasuk);				      
			      r.add("tarikh_Masuk",r.unquote(tarikhMasuk)); 
			      r.add("id_Kemaskini",idMasuk);				      
			      r.add("tarikh_Kemaskini",r.unquote(tarikhMasuk)); 
			      r.add("tarikh_Tukar_Taraf",r.unquote(tarikhMasuk)); 
			      r.add("id_Db",r.unquote("99")); 
			      r.add("no_perserahan","TIADA"); 
			      //Fail Biasa =  1
			      //Fail Lama = 2
			      //Fail Kutipan Data = 3
			      r.add("Flag_jenis_fail","1");
			      sql = r.getSQLInsert("tblpfdfail");
			      System.out.println("SQL "+sql);
			      myLog.info("simpanFail("+data+")::sql:::"+sql);
			      stmt.executeUpdate(sql);
			    } finally {
			      if (db != null) db.close();
			    }
				      
	 }

	 public static void simpanFailOnline(Hashtable<?, ?> data) throws Exception{
		 String sql = "";
		 Db db = null;
		 try{	 
			 long idFail = (Long)data.get("id_Fail");
			 int tarafKeselamatan = (Integer)data.get("id_Tarafkeselamatan");
			 int seksyen = (Integer)data.get("id_Seksyen");
			 int urusan = (Integer)data.get("id_Urusan");
			 int subUrusan = (Integer)data.get("id_Suburusan");
			 String tarikhBukafail = "to_date('" + (String)data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";
			 String namaFail = (String)data.get("tajuk_Fail");
			 String noFail = (String)data.get("no_Fail");
			 String noFailroot = (String)data.get("no_Failroot");
			 int lokasi = (Integer)data.get("id_Lokasi");			 
			 int negeri = (Integer)data.get("id_Negeri");
			 int kementerian = (Integer)data.get("id_Kementerian");
			 int faharasat = (Integer)data.get("id_Faharasat");
			 String flagFail = (String)data.get("flag_Fail");		 
			 int status = (Integer)data.get("id_Status");
			 String catatan = (String)data.get("catatan");		 
//			 String idMasuk = (String)data.get("id_Masuk");
			 String idMasuk = data.get("id_Masuk").toString();
			 //String tarikhMasuk = (String)data.get("tarikh_Masuk");	
			 String tarikhMasuk = "to_date('" + (String)data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";

			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			/*		 ID_FAIL	1	1	N	NUMBER		Yes		
					 ID_TARAFKESELAMATAN	2		Y	NUMBER		Yes		
					 ID_SEKSYEN	3		Y	NUMBER		Yes		
					 ID_URUSAN	4		Y	NUMBER		Yes		
					 ID_SUBURUSAN	5		Y	NUMBER		Yes		
					 TARIKH_DAFTAR_FAIL	6		Y	DATE		Yes		
					 TAJUK_FAIL	7		Y	VARCHAR2 (300 Byte)		Yes		
					 NO_FAIL	8		Y	VARCHAR2 (400 Byte)		Yes		
					 NO_FAIL_ROOT	9		Y	VARCHAR2 (400 Byte)		Yes		
					 ID_LOKASIFAIL	10		Y	NUMBER		Yes		
					 ID_NEGERI	11		Y	NUMBER		Yes		
					 ID_KEMENTERIAN	12		Y	NUMBER		Yes		
					 ID_FAHARASAT	13		Y	NUMBER		Yes		
					 FLAG_FAIL	14		Y	NUMBER		Yes		
					 ID_STATUS	15		Y	NUMBER		Yes		
					 CATATAN	16		Y	VARCHAR2 (4000 Byte)		Yes		
					 ID_MASUK	17		Y	NUMBER		Yes		
					 TARIKH_MASUK	18		Y	DATE		Yes		
					 ID_KEMASKINI	19		Y	NUMBER		Yes		
					 TARIKH_KEMASKINI	20		Y	DATE		Yes		
					 TARIKH_TUKAR_TARAF	21		Y	DATE		Yes		
					 ID_DB	22		Y	NUMBER		No		
					 NO_PERSERAHAN	23		Y	VARCHAR2 (400 Byte)		No		
					 FLAG_JENIS_FAIL	24		Y	VARCHAR2 (1 Byte)		No		
			 		*/
			      r.add("id_Fail",idFail); 
			      r.add("id_Tarafkeselamatan", tarafKeselamatan);
			      r.add("id_Seksyen", seksyen);
			      r.add("id_Urusan", urusan);
			      r.add("id_Suburusan", subUrusan);
			      r.add("tarikh_Daftar_Fail",r.unquote(tarikhBukafail)); 
			      r.add("tajuk_Fail", namaFail);
			      r.add("no_Fail", "");
			      r.add("no_fail_root", "");			      
			      r.add("id_Lokasifail", lokasi);
			      r.add("id_Negeri", negeri);
			      r.add("id_Kementerian", kementerian);			      
			      r.add("id_Faharasat", faharasat);
			      r.add("flag_Fail", flagFail);
			      r.add("id_Status", status);
			      r.add("catatan",catatan);
			      r.add("id_Masuk",idMasuk);				      
			      r.add("tarikh_Masuk",r.unquote(tarikhMasuk)); 
			      r.add("id_Kemaskini",idMasuk);				      
			      r.add("tarikh_Kemaskini",r.unquote(tarikhMasuk)); 
			      r.add("tarikh_Tukar_Taraf",r.unquote(tarikhMasuk)); 
			      r.add("id_Db",r.unquote("99")); 
			      r.add("no_perserahan","TIADA"); 
			      //Fail Biasa =  1
			      //Fail Lama = 2
			      //Fail Kutipan Data = 3
			      r.add("Flag_jenis_fail","1");
			      sql = r.getSQLInsert("tblpfdfail");
			      System.out.println("SQL tblpfdfail "+sql);
			      myLog.info("simpanFail("+data+")::sql:::"+sql);
			      stmt.executeUpdate(sql);
			    } finally {
			      if (db != null) db.close();
			    }
				      
	 }

	 public static int kemaskiniFail(Hashtable<?, ?> data) throws Exception {
		 Db db = null;
		 String sql = "";			  
		 Date now = new Date();
	     SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");

		 try{
			  long idFail = (Long)data.get("id_Fail");
	    	  //String noFailLama = (String)data.get("no_Fail");
		      //int negeri = (Integer)data.get("id_Negeri");
		      //String seksyen = (String)data.get("id_Seksyen");
		      //int urusan = (Integer)data.get("id_Urusan");
		      //String suburusan = (String)data.get("id_Suburusan");
			  String tarikhBukafail = "to_date('" + (String)data.get("tarikh_Bukafail") + "','dd/MM/yyyy')";

		      @SuppressWarnings("unused")
			int taraf = (Integer)data.get("id_Tarafkeselamatan");
		      @SuppressWarnings("unused")
			String namaFail = (String)data.get("tajuk_Fail");
		      @SuppressWarnings("unused")
			int status = (Integer)data.get("id_Status");
		      //String lokasi = (String)data.get("id_Lokasifail");
		      //String faharasat = (String)data.get("id_Faharasat");
		      //int flagFail = (Integer)data.get("flag_Fail");
		      //int kementerian = (Integer)data.get("id_Kementerian");
		      //int idkemaskini = (Integer)data.get("id_kemaskini");

		      /*int idPermohonan = (Integer)data.get("idPermohonan");
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String NoFailLain = (String)data.get("NoFailLain");
			  String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  */
			  int idKemaskini= (Integer)data.get("id_Kemaskini");
		      String tarikhKemaskini = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Fail", idFail);
		      r.add("TAJUK_FAIL",namaFail); 
		      r.add("tarikh_Daftar_Fail",r.unquote(tarikhBukafail)); 
			  r.add("id_Kemaskini",idKemaskini);
			  r.add("tarikh_Kemaskini",r.unquote(tarikhKemaskini));
		      sql = r.getSQLUpdate("tblpfdfail");
		      myLog.info("FrmUtilData:kemaskiniFail()::sql:::"+sql);
		      stmt.executeUpdate(sql);
		      
		      return idKemaskini;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		 
		  }

	  //*** update data from database
	  public static int kemaskiniPermohonan(Hashtable<?, ?> data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try{ 
		      int idPermohonan = (Integer)data.get("idPermohonan");
		      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
		      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
		      String NoFailLain = (String)data.get("NoFailLain");
			  String TarikhPermohonan = (String)data.get("TarikhPermohonan");
			  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			  Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		      String date_update = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Permohonan", idPermohonan);
			  r.add("tarikh_Surat", r.unquote(TSKJP));
			  //juga utk tarikh permohonan
			  r.add("tarikh_Terima", r.unquote(TP));
			  r.add("id_Kemaskini",idPermohonan);
			  r.add("tarikh_Kemaskini",r.unquote(date_update));
		      sql = r.getSQLUpdate("Tblpermohonan");
		      System.out.println("FrmGadaianSemakanData::Update::TBLPERMOHONAN = "+sql);
		      stmt.executeUpdate(sql);
		      
		      Statement stmtHP = db.getStatement();
			  SQLRenderer rHP = new SQLRenderer();
			  rHP.update("id_Permohonan", idPermohonan);
			  rHP.add("no_Rujukan_Lain", NoFailLain);
//			  rHP.add("tarikh_Agihan", r.unquote(TBF));
			  rHP.add("id_Kemaskini",idPermohonan);
			  rHP.add("tarikh_Kemaskini",rHP.unquote(date_update));
		      sql = rHP.getSQLUpdate("Tblhtppermohonan");
		      System.out.println("FrmGadaianSemakanData::Update::tarikh_Agihan = "+ TSKJP );
		      System.out.println("FrmGadaianSemakanData::Update::TBLHTPPERMOHONAN = "+sql);
		      stmtHP.executeUpdate(sql);
		      return idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	    
	  /** 
	   * Simpan permohonan -TBLPERMOHONAN,TBLHTPPERMOHONAN 
	   */
	  public static String simpanPermohonanHTP(Hashtable<?, ?> data) throws Exception {
		  Db db = null;
		  String sql = "";
		  
		  try{
			  Tblrujpejabatjkptg pejabat = null;
			  Hashtable<String, String> userInfo = null;

			  int idAgensi = (Integer)data.get("id_Agensi");
	          int idJenistanah = (Integer)data.get("id_Jenistanah");;
			  
	          long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			  long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			  
			  
			  String idFail = (String)data.get("id_Fail");		    	
			  String idJKPTG = String.valueOf(data.get("idPejabat"));
			  String idMasuk = (String)data.get("id_Masuk");
			  //userInfo = getUserInternalInfo(idMasuk);
			  //pejabat = DB.getPejabatJKPTG(userInfo.get("IdSeksyen"),userInfo.get("IdNegeri"),userInfo.get("IdDaerah"));		      zekptg_user_unit	
			  //Long idJKPTG = pejabat.getIdPejabatjkptg();
//	          String tarikhMasuk = "to_date('" + (String)data.get("tarikh_Masuk") + "','dd/MM/yyyy')";
	          String noFailkjp = (String)data.get("no_Failkjp");
	          String noFaillain = (String)data.get("no_Faillain");
			  String noPermohonan = (String)data.get("no_Permohonan");
			  String noPerserahan = (String)data.get("no_Perserahan");    	
	          String tarikhAgihan = "to_date('" + (String)data.get("tarikh_Agihan") + "','dd/MM/yyyy')";
			  String tarikhTerima = "to_date('" + (String)data.get("tarikh_Terima") + "','dd/MM/yyyy')";
			  String tarikhSuratKJP = "to_date('" + (String)data.get("tarikh_SuratKJP") + "','dd/MM/yyyy')";
	          String tujuan = (String)data.get("tajuk");

	          db = new Db();
	          Statement stmt = db.getStatement();
	          SQLRenderer r = new SQLRenderer();
	          r.add("id_permohonan", r.unquote(""+idPermohonan));
	          r.add("id_fail",idFail);
	          r.add("id_jkptg",idJKPTG);
	          r.add("no_permohonan",noPermohonan);
	          r.add("no_perserahan",noPerserahan);
	          r.add("tarikh_surat", r.unquote(tarikhSuratKJP));
	          	//tarikh_Terima juga utk tarikh permohonan
	          r.add("tarikh_terima", r.unquote(tarikhTerima));
	          r.add("tujuan",tujuan);
	          r.add("id_masuk",idMasuk);
	          r.add("tarikh_masuk",r.unquote("SYSDATE"));
	          r.add("id_kemaskini",idMasuk);
	          r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
	          sql = r.getSQLInsert("tblpermohonan");
	          myLog.info("FrmUtilData::simpanPermohonanHTP:TBLPERMOHONAN = "+sql);
	          stmt.executeUpdate(sql);
		      
	          Statement stmtHtp = db.getStatement();
	          SQLRenderer rHtp = new SQLRenderer();
	          rHtp.add("id_Htppermohonan",idHtppermohonan);
	          rHtp.add("id_Permohonan", idPermohonan);
	          rHtp.add("id_Agensi", idAgensi);
	          rHtp.add("id_Jenistanah", idJenistanah);
	          rHtp.add("id_Pegawai", idMasuk);
	          rHtp.add("no_Rujukan_Kjp", noFailkjp);
	          rHtp.add("no_Rujukan_Lain", noFaillain);
	          rHtp.add("tarikh_Agihan", rHtp.unquote(tarikhAgihan));
	          rHtp.add("id_Masuk",idMasuk);
	          rHtp.add("tarikh_Masuk",rHtp.unquote("SYSDATE"));
	          rHtp.add("id_Kemaskini",idMasuk);
	          rHtp.add("tarikh_Kemaskini",rHtp.unquote("SYSDATE"));
	          sql = rHtp.getSQLInsert("Tblhtppermohonan");
	          myLog.info("FrmUtilData::simpanPermohonanHTP::TBLHTPPERMOHONAN = "+sql);
	          stmtHtp.executeUpdate(sql);   
		      //StatusChange_Action(idPermohonan, idSuburusan);
		   
		      return String.valueOf(idPermohonan);
		      
		  }finally {
			  if (db != null) db.close();
		  }
		  
	  }
	  
	  public static String simpanPermohonanOnline(Hashtable<?, ?> data) throws Exception {
		    Db db = null;
		    String sql = "";
		    String kodNegeriMampu = "";
			String kodKementerianMampu = "";
		    try{
		    	
		    			/*ID_PERMOHONAN	1	1	N	NUMBER		Yes		
		    			ID_FAIL	2		N	NUMBER		Yes		
		    			ID_PEJABATJKPTG	3		N	NUMBER		Yes		
		    			NO_PERMOHONAN	4		Y	VARCHAR2 (30 Byte)		Yes		
		    			NO_PERSERAHAN	5		Y	VARCHAR2 (50 Byte)		Yes		
		    			TARIKH_SURAT	6		Y	DATE		Yes		
		    			TARIKH_TERIMA	7		Y	DATE		Yes		
		    			TUJUAN	8		Y	VARCHAR2 (300 Byte)		Yes		
		    			ID_MASUK	9		Y	NUMBER		Yes		
		    			TARIKH_MASUK	10		Y	DATE		Yes		
		    			ID_KEMASKINI	11		Y	NUMBER		Yes		
		    			TARIKH_KEMASKINI	12		Y	DATE		Yes		
		    			 */
		    	Tblrujpejabatjkptg pejabat = null;
		    	Hashtable<String, String> userInfo = null;

		    	long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
		    	String idNeg = (String)data.get("socNegeri");
		    	String idKem = (String)data.get("socKementerian");
		    	String idFail = (String)data.get("id_Fail");		    	
			    String idMasuk = (String)data.get("id_Masuk");
		    	userInfo = getUserInternalInfo(idMasuk);
		    	pejabat = DB.getPejabatJKPTG(userInfo.get("IdSeksyen"),userInfo.get("IdNegeri"),userInfo.get("IdDaerah"));		      
		    	long idJKPTG = 115;
		    	//Long idJKPTG = pejabat.getIdPejabatjkptg();
		    	String noPermohonan = (String)data.get("no_Permohonan");
		    	String noPerserahan = (String)data.get("no_Perserahan");    	
		    	String tarikhSuratKJP = "to_date('" + (String)data.get("tarikh_SuratKJP") + "','dd/MM/yyyy')";
		    	String tarikhTerima = "to_date('" + (String)data.get("tarikh_Terima") + "','dd/MM/yyyy')";
	          	String tujuan = (String)data.get("tajuk");
	          	//idMasuk dah declare di atas     	
	          	String tarikhMasuk = "to_date('" + (String)data.get("tarikh_Masuk") + "','dd/MM/yyyy')";
	          	
	          	// TBLHTPPERMOHONAN
	          	/*	ID_HTPPERMOHONAN	1	1	N	NUMBER		Yes		
	          			ID_PERMOHONAN	2		Y	NUMBER		Yes		
	          			ID_AGENSI	3		Y	NUMBER		Yes		
	          			ID_JENISTANAH	4		Y	VARCHAR2 (1000 Byte)		Yes		
	          			ID_PEGAWAI	5		N	NUMBER		Yes		
	          			NO_RUJUKAN_KJP	6		Y	VARCHAR2 (50 Byte)		Yes		
	          			NO_RUJUKAN_LAIN	7		Y	VARCHAR2 (50 Byte)		Yes		
	          			TARIKH_AGIHAN	8		Y	DATE		Yes		
	          			ID_MASUK	9		Y	NUMBER		Yes		
	          			TARIKH_MASUK	10		Y	DATE		Yes		
	          			ID_KEMASKINI	11		Y	NUMBER		Yes		
	          			TARIKH_KEMASKINI	12		Y	DATE		Yes		
	          	 */
	          	long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	          	int idAgensi = (Integer)data.get("id_Agensi");
	          	int idJenistanah = (Integer)data.get("id_Jenistanah");;
	          	//int idPegawai = (Integer)data.get("id_Pegawai");
	          
	          	String noFailkjp = (String)data.get("no_Failkjp");
	          	String noFaillain = (String)data.get("no_Faillain");
	          	String tarikhAgihan = "to_date('" + (String)data.get("tarikh_Agihan") + "','dd/MM/yyyy')";
	          	Long setIdStatus = 0L;
				setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",String.valueOf(data.get("idSuburusan")),"=");
				kodKementerianMampu = getKementerianByMampu(Integer.parseInt(idKem));
				kodNegeriMampu = getNegeriByMampu(Integer.parseInt(idNeg));

	          	db = new Db();
	          	Statement stmt = db.getStatement();
	          	SQLRenderer r = new SQLRenderer();
	          	r.add("id_Permohonan", r.unquote(""+idPermohonan));
	          	r.add("id_Fail",idFail);
	          	r.add("id_Jkptg",idJKPTG);
	          	r.add("no_Permohonan",FrmUtilData.generateNoOnline(2,kodKementerianMampu, String.valueOf(idKem), kodNegeriMampu, idNeg));
	          	r.add("ID_STATUS",setIdStatus);
	          	r.add("no_Perserahan",noPerserahan);
	          	r.add("tarikh_Surat", r.unquote(tarikhSuratKJP));
	          	//tarikh_Terima juga utk tarikh permohonan
	          	r.add("tarikh_Terima", r.unquote(tarikhTerima));
	          	r.add("tujuan",tujuan);
	          	r.add("id_Masuk",idMasuk);
	          	r.add("tarikh_Masuk",r.unquote("SYSDATE"));
	          	r.add("id_Kemaskini",idMasuk);
	          	r.add("tarikh_Kemaskini",r.unquote("SYSDATE"));
	          	sql = r.getSQLInsert("Tblpermohonan");
	          	myLog.info("FrmUtilData::simpanPermohonanHTP::TBLPERMOHONAN = "+sql);
	          	stmt.executeUpdate(sql);
		      
	          	Statement stmtHtp = db.getStatement();
	          	SQLRenderer rHtp = new SQLRenderer();
	          	rHtp.add("id_Htppermohonan",idHtppermohonan);
	          	rHtp.add("id_Permohonan", idPermohonan);
	          	rHtp.add("id_Agensi", idAgensi);
	          	rHtp.add("id_Jenistanah", idJenistanah);
	          	rHtp.add("id_Pegawai", idMasuk);
	          	rHtp.add("no_Rujukan_Kjp", noFailkjp);
	          	rHtp.add("no_Rujukan_Lain", noFaillain);
	          	rHtp.add("tarikh_Agihan", rHtp.unquote(tarikhAgihan));
	          	rHtp.add("id_Masuk",idMasuk);
	          	rHtp.add("tarikh_Masuk",rHtp.unquote("SYSDATE"));
	          	rHtp.add("id_Kemaskini",idMasuk);
	          	rHtp.add("tarikh_Kemaskini",rHtp.unquote("SYSDATE"));
	          	sql = rHtp.getSQLInsert("Tblhtppermohonan");
	          	myLog.info("FrmUtilData::simpanPermohonanHTP::TBLHTPPERMOHONAN = "+sql);
	          	stmtHtp.executeUpdate(sql);
	          
		      
		      //StatusChange_ActionOnline(idPermohonan, "",idSuburusan);
		      
		      return ""+idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	  
	  //public static void simpanStatusPermohonan(long idPermohonan, int idSuburusan,int status){
	  public static void simpanStatusPermohonan(Tblrujsuburusanstatusfail s){
		  Date now = new Date();
		  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
		  Db db = null;
		  String sql = "";	  
		  try{
			  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();		  
			  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
			  r.add("Id_Suburusanstatus", r.unquote(String.valueOf(s.getIdSuburusanstatus())));
			  r.add("aktif",s.getAktif());
			  r.add("id_Masuk", String.valueOf(s.getIdMasuk()));
			  if(!("".equals(s.getTarikhMasuk())))
				  r.add("tarikh_Masuk", r.unquote("to_date('" + formatter.format(s.getTarikhMasuk()) + "','dd/MM/yyyy')"));
			  else
				  r.add("tarikh_Masuk", r.unquote(sekarang));

			  if(!("".equals(s.getUrl())))
				  r.add("URL", s.getUrl());
			  else
				  r.add("URL", "TIADA");
	  
			  r.add("id_kemaskini", String.valueOf(s.getIdMasuk()));
			  r.add("tarikh_kemaskini", r.unquote(sekarang));
			  r.add("id_fail",String.valueOf(s.getIdFail()));
			  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			   myLog.info("simpanStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
		      stmt.executeUpdate(sql);
		  }catch(Exception ex){
			  ex.printStackTrace();
			  myLog.info("simpanStatusPermohonan::ex = "+ex);
		  }finally{
			  if (db != null) db.close();
		  }		  
	  }
	  
	  //public static void kemaskiniStatusPermohonan(long idPermohonan, int idSuburusan,int status){
	  public static void kemaskiniStatusPermohonanAktif(Tblrujsuburusanstatusfail s){
		  Db db = null;
		  String sql = "";
		  Date now = new Date();
		  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		  
		  try{
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan",r.unquote(""+s.getIdPermohonan()));
			  r.update("id_fail",r.unquote(""+s.getIdFail()));
			  r.update("aktif", "1");
			  //r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			  //r.add("id_Permohonan", idPermohonan);
			  //r.add("Id_Suburusanstatus", status);
			  r.add("aktif",s.getAktif());
			  r.add("id_kemaskini", s.getIdKemaskini());
			  r.add("tarikh_kemaskini", r.unquote("sysdate"));
 
			  sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
		      myLog.info("kemaskiniStatusPermohonanAktif:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
		      int flag = stmt.executeUpdate(sql);
		      System.out.println("flag "+flag);
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }finally{
			  if (db != null) db.close();
		  }		  
	  }
	  
	  public static Hashtable<String, String> getUserInternalInfo(String userId)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();		 

		    	r.add("i.id_Seksyen");
		    	r.add("i.id_Negeri");
		    	r.add("i.id_Daerah");
		
		    	r.add("u.user_id", userId);
		    	r.add("u.user_id",r.unquote("i.user_id"));

		    	sql = r.getSQLSelect("users u,users_internal i");
		    	ResultSet rs = stmt.executeQuery(sql);
		    	
		    	Hashtable<String, String> h = null;

		    	while (rs.next()) {
		    		h = new Hashtable<String, String>();
		    		h.put("IdSeksyen", rs.getString("id_Seksyen"));
		    		h.put("IdNegeri", rs.getString("id_Negeri"));
		    		h.put("IdDaerah", rs.getString("id_Daerah")==null?"0":rs.getString("id_Daerah"));
		    	}
		    	return h;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

		@SuppressWarnings({ "finally", "null" })
		public static Vector<Hashtable<String, String>> getSenaraiFailByUrusan(String idUrusan, String carian, String noFail,
				Long idNegeri) throws Exception {
			Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
			Db db = null;
			String sql = "";
			if (idNegeri == 20)
				idNegeri = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri," +
						" n.kod_Mampu,p.id_Permohonan ";
				sql += "FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
				sql += "WHERE f.id_Fail = p.id_Fail AND F.ID_STATUS <> 999 AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri ";
				sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
				sql += "AND sf.aktif = '1' AND f.id_Urusan = " + idUrusan
						+ " AND f.tajuk_Fail LIKE '%" + carian + "%' ";
				sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
				if (idNegeri != null)
					sql += "AND f.id_Negeri = " + idNegeri;
				sql += " ORDER BY n.kod_Mampu";
			
				//myLog.info(":getSenaraiFailByUrusan::sql = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, String> h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable<String, String>();
					h.put("idpermohonan", rs.getString("id_Permohonan"));
					h.put("idfail", rs.getString("id_Fail"));
					h.put("nofail", rs.getString("no_Fail"));
					h.put("tajuk", rs.getString("tajuk_Fail"));
					h.put("negeri", rs.getString("nama_Negeri"));
					h.put("keterangan", rs.getString("keterangan"));
					h.put("kodmampu", rs.getString("kod_Mampu"));
					list.addElement(h);
					bil++;
				}
				return list;
			} finally {
				if (db != null)
					db.close();
				
				return list;
			}
		}
		
		public static Vector<Hashtable<String, String>> getSenaraiTugasan(String search,String idMasuk,String role)
			throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();

			    r.add("distinct(p.id_Fail)");
			    r.add("f.no_Fail");
			    r.add("f.tajuk_Fail");
			    r.add("s.keterangan");
			    r.add("um.tab_id");
			    r.add("um.module_id");
			    r.add("f.id_kemaskini");
			    r.add("p.id_Fail",r.unquote("f.id_Fail"));
			    r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
			    r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
			    r.add("us.id_Status",r.unquote("s.id_Status"));
			    r.add("us.module_id",r.unquote("um.module_id"));
			    r.add("um.user_login",role);
			    r.add("sf.aktif","1");
			    r.add("f.id_Masuk",idMasuk);
			    r.add("f.no_Fail","%"+search+"%","like");
				  //System.out.print(role.substring(role.length()-3, role.length()));
			    if(role.substring((role.length()-3), (role.length())).equalsIgnoreCase("PPK")){
			    	sql = r.getSQLSelect(" Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um","f.id_kemaskini");
			    }else{
			    	sql = r.getSQLSelect(" Tblpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um","f.id_kemaskini");
			    }
			    //myLog.info("FrmUtilData:getSenaraiTugasan("+search+","+ idMasuk+","+role+")::sql:::"+sql);

			    ResultSet rs = stmt.executeQuery(sql);
			    Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
			    Hashtable<String, String> h;

			    while (rs.next()) {
			    	h = new Hashtable<String, String>();
			    	h.put("id", rs.getString("id_Fail"));
			    	h.put("no", rs.getString("no_Fail"));
			    	h.put("tajuk", rs.getString("tajuk_Fail"));
			    	h.put("keterangan", rs.getString("keterangan"));
			    	h.put("tabid", rs.getString("tab_id"));
			    	h.put("modul", rs.getString("module_id"));
			    	list.addElement(h);
			    }
			    
			    return list;
			    } finally {
			      if (db != null) db.close();
			    }
			}
		
		public static Vector<Hashtable<String, String>> getSenaraiTugasanLain(String idMasuk,String role)
			throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("distinct(p.id_Fail)");
				r.add("f.no_Fail");
				r.add("f.tajuk_Fail");
				r.add("s.keterangan");
				r.add("um.tab_id");
				r.add("um.module_id");
				r.add("u.user_name as didaftar_oleh");
				r.add("f.id_kemaskini");
				r.add("p.id_Fail",r.unquote("f.id_Fail"));
				r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
				r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
				r.add("us.id_Status",r.unquote("s.id_Status"));
				r.add("us.module_id",r.unquote("um.module_id"));
				r.add("um.user_login",role);
				r.add("sf.aktif","1");
					r.add("ui.id_Masuk",idMasuk);
					r.add("f.id_Masuk",r.unquote("u.user_id"));
					r.add("u.user_id",r.unquote("ui.user_id"));
			
				if(role.substring((role.length()-3), (role.length())).equalsIgnoreCase("PPK")){
					r.add("ui.id_daerah",r.unquote("rd.id_daerah"));
					r.add("rd.id_daerah",r.unquote("rpu.id_daerahurus"));
					r.add("ui.id_pejabatjkptg",r.unquote("rpu.id_pejabatjkptg"));

					sql = r.getSQLSelect(" Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf," +
							"Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um," +
							"users u,users_internal ui,TBLRUJPEJABATURUSAN rpu,tblRujDaerah rd","f.id_kemaskini");
					/*sql = " SELECT distinct(p.id_Fail), f.no_Fail, f.tajuk_Fail, s.keterangan, um.tab_id, um.module_id, "+ 
							" (select user_name from users where user_id=f.id_masuk) as didaftar_oleh,f.id_kemaskini "+ 
							" FROM  Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s,user_module_template um "+
							" WHERE p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan "+  
							" AND sf.id_Suburusanstatus = us.id_Suburusanstatus  AND us.id_Status = s.id_Status  "+  
							" AND us.module_id = um.module_id  AND um.user_login = 'adminppk'  AND sf.aktif = '1'  "+  
							" AND f.id_Masuk IN " +
							"	(select user_id from users_internal where id_daerah IN "+  
							" 		(select id_daerah from tblRujDaerah where id_daerah IN "+  
							" 			(select distinct id_daerahurus from TBLRUJPEJABATURUSAN where "+
							" 			id_pejabatjkptg = (select id_pejabatjkptg from users_internal "+
							" 			where user_id="+idMasuk+")" +
									") and user_id <> "+idMasuk+"" +
								")"+
							" AND f.no_Fail like '%%'  ORDER BY f.id_kemaskini ";*/
				}else{
					sql = r.getSQLSelect(" Tblpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf," +
							"Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um,users u,users_internal ui","f.id_kemaskini");
				}
				
			    myLog.info("FrmUtilData:getSenaraiTugasanLain("+idMasuk+","+role+")::sql:::"+sql);

				ResultSet rs = stmt.executeQuery(sql);
				Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
				Hashtable<String, String> h;
				while (rs.next()) {
					h = new Hashtable<String, String>();
					h.put("id", rs.getString("id_Fail"));
					h.put("no", rs.getString("no_Fail"));
					h.put("tajuk", rs.getString("tajuk_Fail"));
					h.put("keterangan", rs.getString("keterangan"));
					h.put("tabid", rs.getString("tab_id"));
					h.put("modul", rs.getString("module_id"));
					h.put("idmasuk", rs.getString("didaftar_oleh"));
					list.addElement(h);
				}
		    
				return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
		
		  /* Created by : Mohamad Rosli 2010/04/13
		   * Updated	: 2010/04/13
		   * Tujuan	  	: Data dari TBLRUJSTATUS,TBLRUJSUBURUSANSTATUS
		   * Pra syarat : Tiada
		   */
		public static Vector<Hashtable<String,String>> getSenaraiStatusMengikutSubUrusan(String idSuburusan) 
			throws Exception {
//			myLog.info("getSenaraiStatusMengikutSubUrusan("+idSuburusan+")");
//			String key = "DB.getSenaraiStatusMengikutSubUrusan";
			/*Element cachedObject = myCache.get(key);
			if (cachedObject != null) {
			  return (Vector)cachedObject.getObjectValue();
			} else {*/	
			Db db = null;
			String sql = "SELECT RS.ID_STATUS, RS.KOD_STATUS, RS.KETERANGAN," +
						" RSUS.ID_SUBURUSANSTATUS"+
						" FROM TBLRUJSTATUS RS,TBLRUJSUBURUSANSTATUS RSUS "+
						" WHERE RSUS.ID_STATUS=RS.ID_STATUS "+
						" AND RSUS.ID_SUBURUSAN="+idSuburusan+" " +
						" AND RSUS.LANGKAH IS NOT NULL " +
						" ORDER BY RSUS.LANGKAH ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
//				myLog.info("getSenaraiStatusMengikutSubUrusan("+idSuburusan+")"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				vec = new Vector<Hashtable<String,String>>();
				Hashtable<String,String> h = null;
				int i=0;
				while (rs.next()) {
					h = new Hashtable<String,String>();
	    			h.put("bil",String.valueOf(i+1));
					h.put("idsuburusan",String.valueOf(String.valueOf(rs.getLong("ID_SUBURUSANSTATUS"))));
					h.put("id",String.valueOf(rs.getLong("ID_STATUS")));
					h.put("kod",rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS"));
					h.put("keterangan",rs.getString("KETERANGAN") == null ? "" :rs.getString("KETERANGAN"));
    				h.put("list_keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					vec.addElement(h);
					
				}
				//myCache.put(new Element(key, v));
				return vec;
			} finally {
				if (db != null)
					db.close();
			}
			//}
		}

		  /* Created by : Mohamad Rosli 2010/04/15
		   * Updated	: 
		   * Tujuan	  	: Data dari TBLRUJSUBURUSANSTATUSFAIL
		   * Pra syarat : Tiada
		   */	
		public static boolean getIdSuburusanStatusFail(String idfail,String idpermohonan,String id) throws Exception {
			Db db = null;
			String sql = "";
			boolean returnValue = false;		
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("rsusf.id_suburusanstatus");
		        r.add("rsusf.id_fail",r.unquote( idfail));
		        r.add("rsusf.id_suburusanstatus",r.unquote(id));
		        r.add("rsusf.id_permohonan",r.unquote(idpermohonan));
		           
		    sql = r.getSQLSelect("tblrujsuburusanstatusfail rsusf");
		    
		    myLog.info("getIdStatusFailPermohonanTerkini:sql::"+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()) { 
		    	returnValue = true;
		    }
		      
		  }catch(Exception ex){
			  myLog.info("FrmUtilData:getIdSuburusanStatusFail::ex = "+ex);
		  }finally{
			  if (db != null) db.close();
		  }
		return returnValue;		  
	}
		
		
		 @SuppressWarnings("unchecked")
		public static Hashtable<String, String> getPermohonanInfo(String idpermohonan)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      @SuppressWarnings("unused")
				SQLRenderer r = new SQLRenderer();
			      sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
			      " f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
			      " to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
			      " k.id_kementerian,a.id_agensi,n.id_negeri,F.ID_SUBURUSAN " +
			      " ,( SELECT U.KOD_URUSAN FROM TBLRUJURUSAN U WHERE U.ID_URUSAN = F.ID_URUSAN) KOD_URUSAN " +
			      " ,NVL(( SELECT J.KOD_JENIS_TANAH FROM TBLRUJJENISTANAH J WHERE H.ID_JENISTANAH = J.ID_JENISTANAH), 'TIADA') KOD_JENIS_TANAH " +
			      " FROM " +
			      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
			      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
			      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
			      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
			      " and h.ID_AGENSI=a.id_agensi and F.ID_SUBURUSAN=S.ID_SUBURUSAN  "+
			      " and p.id_permohonan = "+idpermohonan;
			      /*myLog.info(":::::::::::::::::::::::"+sql);*/
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();

			      while (rs.next()) {
			    	  if(rs.getString("nama_negeri")==null){
			    		  h.put("negeri", "");
			    	  }else{
			    		  h.put("negeri", rs.getString("nama_negeri")); 
			    	  }
			    	  if(rs.getString("nama_kementerian")==null){
			    		  h.put("kementerian", "");
			    	  }else{
			    		  h.put("kementerian", rs.getString("nama_kementerian"));}
			    	  if(rs.getString("nama_agensi")==null){
			    		  h.put("agensi", "");
			    	  }else {
			    		  h.put("agensi", rs.getString("nama_agensi"));
			    	  }
		    		  h.put("idsuburusan", rs.getString("id_suburusan"));
			    	  if(rs.getString("nama_suburusan")==null){
			    		  h.put("suburusan", "");
			    	  }else{
			    		  h.put("suburusan", rs.getString("nama_suburusan"));
			    	  }
			    	  if(rs.getString("no_fail")==null){
			    		  h.put("fail", "");
			    	  }else{
			    		  h.put("fail", rs.getString("no_fail"));
			    	  }
			    	  if(rs.getString("tarikh_daftar_fail")==null){
			    		  h.put("tdaftar",new Date());
			    	  }else{
			    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));
			    	  }
			    	  if(rs.getString("no_rujukan_kjp")==null){
			    		  h.put("rujukankjp", "");
			    	  }else{
			    		  h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
			    	  }
			    	  if(rs.getString("no_rujukan_lain")==null){
			    		  h.put("rujukanlain", "");
			    	  }else{
			    		  h.put("rujukanlain", rs.getString("no_rujukan_lain"));
			    	  }
			    	  if(rs.getString("tarikh_surat")==null){
			    		  h.put("tsurat",new Date());
			    	  }else{
			    		  h.put("tsurat", rs.getString("tarikh_surat"));
			    	  }
			    	  if(rs.getString("tarikh_terima")==null){
			    		  h.put("rtterima",new Date());
			    	  }else{
			    		  h.put("rtterima", rs.getString("tarikh_terima"));
			    	  }
			    	  if(rs.getString("tujuan")==null){
			    		  h.put("tujuan", "");
			    	  }else{
			    		  h.put("tujuan", rs.getString("tujuan"));
			    	  }
			    	  if(rs.getString("id_permohonan")==null){
			    		  h.put("idpermohonan", "");
			    	  }else{
			    		  h.put("idpermohonan", rs.getString("id_permohonan"));
			    	  }
			    	  if(rs.getString("id_fail")==null){
			    		  h.put("idfail", "");
			    	  }else{
			    		  h.put("idfail", rs.getString("id_fail"));
			    	  }	 
		    		  h.put("idagensi", rs.getString("id_agensi"));
		    		  h.put("idkementerian", rs.getString("id_kementerian"));
		    		  h.put("idnegeri", rs.getString("id_negeri"));
		    		  h.put("kodUrusan", rs.getString("KOD_URUSAN"));
		    		  h.put("kodJenisTanah", rs.getString("KOD_JENIS_TANAH"));
		    		  
			    	  //list.addElement(h);
			      }
			      return h;
			    } finally {
			      if (db != null) db.close();
			    }
			  }

		 @SuppressWarnings("unchecked")
			public Hashtable<String, Comparable> getPermohonanInfoV1X(String idpermohonan)throws Exception {
				    Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      @SuppressWarnings("unused")
					SQLRenderer r = new SQLRenderer();
				      sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
				      " f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
				      " to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
				      " k.id_kementerian,a.id_agensi,n.id_negeri,H.NO_RUJUKAN_PTD,H.NO_RUJUKAN_PTG,F.TAJUK_FAIL " +
				      " ,H.ID_DAERAH" +
				      " FROM " +
				      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
				      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
				      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
				      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
				      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
				      " and p.id_permohonan = "+idpermohonan;
				      
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable h = new Hashtable();

				      while (rs.next()) {
			    		  h.put("idnegeri", rs.getString("id_negeri"));
				    	  h.put("negeri", Utils.isNull(rs.getString("nama_negeri"))); 
				    	  h.put("idkementerian", rs.getString("id_kementerian"));
				    	  h.put("kementerian", Utils.isNull(rs.getString("nama_kementerian")));
			    		  h.put("idagensi", rs.getString("id_agensi"));
			    		  h.put("agensi", Utils.isNull(rs.getString("nama_agensi")));
			    		  h.put("suburusan", Utils.isNull(rs.getString("nama_suburusan")));
			    		  h.put("fail", Utils.isNull(rs.getString("no_fail")));
				    	  if(rs.getString("tarikh_daftar_fail")==null){
				    		  h.put("tdaftar",new Date());
				    	  }else{
				    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));	}
			    		  h.put("rujukankjp", Utils.isNull(rs.getString("no_rujukan_kjp")));
			    		  h.put("rujukanlain", Utils.isNull(rs.getString("no_rujukan_lain")));
				    	  if(rs.getString("tarikh_surat")==null){
				    		  h.put("tsurat",new Date());
				    	  }else{
				    		  h.put("tsurat", rs.getString("tarikh_surat"));	}
				    	  if(rs.getString("tarikh_terima")==null){
				    		  h.put("rtterima",new Date());
				    	  }else{
				    		  h.put("rtterima", rs.getString("tarikh_terima"));	}
			    		  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
				    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
				    	  h.put("idfail", rs.getString("ID_FAIL")==null ? "0" :rs.getString("ID_FAIL"));
			    		  h.put("noFailPTG", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
			    		  h.put("noFailPTD", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
				    	  h.put("idDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
			    		  h.put("tajukFail", Utils.isNull(rs.getString("TAJUK_FAIL")));


				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
		 }
		 
		 public static Tblrujpejabatjkptg getPejabatJKPTG(String idSeksyen,String idNegeri,String idDaerah, 
				 String jawatan) 
			throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("p.id_pejabatjkptg");
		      r.add("p.nama_pejabat");
		      r.add("p.alamat1");
		      r.add("p.alamat2");
		      r.add("p.alamat3");
		      r.add("p.poskod");
		      r.add("p.id_negeri");
		      r.add("p.id_daerah");
		      r.add("p.no_tel");
		      r.add("p.no_fax");
		      r.add("p.jawatan");
		      r.add("n.nama_negeri");
		      //r.add("p.id_negeri",r.unquote("n.id_negeri"));
		      r.add("p.id_seksyen",idSeksyen);
		      r.add("p.id_negeri",idNegeri);
		      r.add("p.id_daerah",idDaerah);
		      r.add("p.jawatan",jawatan);
		      
		      sql = r.getSQLSelect("tblrujpejabatjkptg p");
		      System.out.println("FrmUtilData:getPejabatJKPTG::sql"+sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      Tblrujpejabatjkptg s = null;
		      while (rs.next()) {    	  
		    	  s = new Tblrujpejabatjkptg();
			      s.setIdPejabatjkptg(rs.getLong("id_pejabatjkptg"));
			      s.setNamaPejabat(rs.getString("nama_pejabat"));
			  	  s.setAlamat1(rs.getString("Alamat1"));
		    	  if(rs.getString("Alamat2") != null){
		    		  s.setAlamat2(rs.getString("Alamat2"));
		    	  }else{
		    		  s.setAlamat2("");
		    	  }
		    	  if(rs.getString("Alamat3") != null){
		    		  s.setAlamat3(rs.getString("Alamat3"));
		    	  }else{
		    		  s.setAlamat3("");
		    	  }	    	  
		    	  s.setPoskod(rs.getString("Poskod"));
		    	  s.setIdNegeri(rs.getLong("Id_negeri"));
		    	  s.setIdDaerah(rs.getLong("Id_daerah"));
		    	  
		    	  if(rs.getString("no_tel") != null){
		    		  s.setNoTel(rs.getString("no_tel"));
		    	  }else{
		    		  s.setNoTel("");
		    	  }
		    	  if(rs.getString("no_fax") != null){
		    		  s.setNoFax(rs.getString("no_fax"));
		    	  }else{
		    		  s.setNoFax("");
		    	  }
			  	  s.setJawatan(rs.getString("jawatan"));
		      }
		      return s;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}
	  
		 public static Vector<Tblrujnegeri> getNegeriDistinct(String namaTable) throws Exception {
			 Db db = null;
			 String sql = "";
			 try {
				 db = new Db();
				 Statement stmt = db.getStatement();
				 SQLRenderer r = new SQLRenderer();
				 r.add("n.id_Negeri");
				 r.add("n.kod_Negeri");
				 r.add("n.nama_Negeri");
				 r.add("n.kod_mampu");
				 if(namaTable == null){
					 sql = r.getSQLSelect("tblrujnegeri n");
					 //sql += " WHERE n.id_Negeri not in (0,17) ORDER BY n.kod_mampu";
					 sql += " WHERE n.id_negeri <> 0 ORDER BY n.kod_mampu";
				 }else{
					 r.add("n.id_Negeri",r.unquote("h.id_negeri"));
					 sql = r.getSQLSelectDistinct("tblrujnegeri n"+","+namaTable+" h","id_negeri");
				 }
				 //myLog.info("FrmUtilData:getNegeriDistinct("+namaTable+")::sql:::"+sql);
				 ResultSet rs = stmt.executeQuery(sql);
				 Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				 Tblrujnegeri s = null;
				 while (rs.next()) {
					 s = new Tblrujnegeri();
					 s.setIdNegeri(rs.getLong("id_Negeri"));
					 s.setKodNegeri(rs.getString("kod_Negeri"));
					 s.setNamaNegeri(rs.getString("nama_Negeri"));
					 s.setKodMampu(rs.getString("kod_mampu"));
					 v.addElement(s);
				 }
				 return v;
			 } finally {
				 if (db != null)	db.close();
			 }
		 }
		 
		 @SuppressWarnings("unchecked")
		public  static Vector<Hashtable<String, Comparable>> getList(String userid) throws Exception {
			 Db db = null;
			 //list.clear();
			 String sql = "";
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 try {
				 db = new Db();
				 Statement stmt = db.getStatement();

				 sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
		         + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
		         + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"                 
		         +" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
					+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
					+" WHERE" 
					+" D.id_daerah in ( select distinct u.id_daerahurus from"
					+" TBLRUJPEJABATURUSAN u, users_internal ur where " +
					" u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id="+userid+")"
					+" AND ST.ID_STATUS = S.ID_STATUS(+)"
					+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 
					+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)" 
					+" AND A.ID_FAIL = F.ID_FAIL(+)" 
					+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
					+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
					+" AND P.ID_SIMATI = MS.ID_SIMATI" 
					+" AND STA.ID_SUBURUSANSTATUS = 340" 
					+" AND A.SEKSYEN = 8"  
					+" AND STA.AKTIF = 1" 
					+" AND A.FLAG_JENIS_PERMOHONAN = 1" 
				//	+" ORDER BY F.ID_FAIL DESC"
					+"";
				 sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
					
				System.out.print("FrmUtilData:getList("+userid+")::sql:::"+sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;
				int bil = 1;
				Vector<Hashtable<String, Comparable>> listVec = new Vector<Hashtable<String, Comparable>>();

				while (rs.next()){
					h = new Hashtable<String, Comparable>();
					h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
					h.put("id_Pemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
					h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
					h.put("noonline", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
					h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
					h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
					h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
					h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("id_simati", rs.getString("id_Simati"));
					h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
					h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
					h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
					h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
						
					listVec.addElement(h);
					bil++;	
				}
				return listVec;
			}finally {
				if(db != null) db.close();	
			}
		 }

		 
			public static Vector<Tblrujstatus> getStatusDistinct() throws Exception {
				Db db = null;
				String sql = "Select distinct(id_Status),keterangan"
						+ " from tblrujstatus order by keterangan ASC";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					ResultSet rs = stmt.executeQuery(sql);
					Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
					Tblrujstatus s = null;
					while (rs.next()) {
						s = new Tblrujstatus();
						s.setIdStatus(rs.getLong("id_Status"));
						s.setKeterangan(rs.getString("keterangan"));
						v.addElement(s);
					}
					return v;
				} finally {
					if (db != null)
						db.close();
				}
			}
			public static Vector<Tblrujstatus> getStatusByUrusan(String idUrusan) throws Exception {
				Db db = null;
				// langkah -1,102 = status online
				String sql = "select distinct(rs.id_status),rs.keterangan,rsus.langkah"+
						" from tblrujsuburusanstatus rsus, tblrujstatus rs, tblrujsuburusan rsu" +
						" where" +
						" rsus.id_status=rs.id_status" +
						" and rsus.id_suburusan = rsu.id_suburusan" +
						" and rsus.LANGKAH is not null" +
						" and rsus.langkah not in(-1,102)" +
						" and rsu.id_urusan =" + idUrusan +
						" order by rsus.langkah" +
						"";

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					ResultSet rs = stmt.executeQuery(sql);
					Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
					Tblrujstatus s = null;
					while (rs.next()) {
						s = new Tblrujstatus();
						s.setIdStatus(rs.getLong("id_status"));
						s.setKeterangan(rs.getString("keterangan"));
						v.addElement(s);
					}
					return v;
				} finally {
					if (db != null)
						db.close();
				}
				
			}
		//**getListBaucer by arif
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getListBaucer(int idPeringkatbayaran) throws Exception {
				Db db = null;
				    String sql = "SELECT b.id_baucer,b.id_daerah,a.nama_daerah,b.amaun_baucer,b.id_peringkatbayaran " +
				    			"FROM tblrujdaerah a, tblhtpbaucer b " +
				    			"WHERE b.id_daerah = a.id_daerah and b.id_peringkatbayaran="+idPeringkatbayaran;
				    try {
				      db = new Db();
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idBaucer",rs.getLong("id_baucer"));  
				    	  h.put("idDaerah",rs.getString("id_daerah"));
				    	  h.put("idPeringkatbayaran",rs.getString("id_peringkatbayaran"));
				    	  h.put("namaDaerah",rs.getString("nama_daerah"));  
				    	  h.put("amaunBaucer",Util.formatDecimal(rs.getDouble("amaun_baucer")));  
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
			  }	   
			//**getListBaucer by arif
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getListBaucer(String idPeringkatbayaran) throws Exception {
				Db db = null;
				    String sql = "SELECT b.id_baucer,b.id_daerah,a.nama_daerah,b.amaun_baucer,b.id_peringkatbayaran " +
				    			"FROM tblrujdaerah a, tblhtpbaucer b " +
				    			"WHERE b.id_daerah = a.id_daerah and b.id_peringkatbayaran='"+idPeringkatbayaran+"'";
				    try {
				      db = new Db();
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
//				      myLog.info("getListBaucer:sql="+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idBaucer",rs.getLong("id_baucer"));  
				    	  h.put("idDaerah",rs.getString("id_daerah"));
				    	  h.put("idPeringkatbayaran",rs.getString("id_peringkatbayaran"));
				    	  h.put("namaDaerah",rs.getString("nama_daerah"));  
				    	  h.put("amaunBaucer",Util.formatDecimal(rs.getDouble("amaun_baucer")));  
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
			  }	 

			public static Vector<Hashtable<String, Comparable>> getListBaucer(String peringkatBayar,String idPeringkatbayaran) throws Exception {
				Db db = null;
				String sql = "SELECT b.id_baucer,b.id_daerah,a.nama_daerah,b.amaun_baucer,b.id_peringkatbayaran " +
					" FROM tblrujdaerah a, tblhtpbaucer b " +
				    " WHERE b.id_daerah = a.id_daerah and b.id_peringkatbayaran='"+idPeringkatbayaran+"'";
				if(peringkatBayar.equals("1")){
					sql = "" +
				    	"SELECT b.id_baucer,b.id_daerah,b.amaun_baucer,b.id_peringkatbayaran "+
				    	" ,RN.ID_NEGERI,RN.NAMA_NEGERI NAMA_DAERAH "+
				    	" FROM tblhtpbaucer B, TBLRUJNEGERI RN "+
				    	" WHERE B.ID_NEGERI = RN.ID_NEGERI "+ 
				    	" and b.id_peringkatbayaran='"+idPeringkatbayaran+"'"+
				    	"";
				}
				try {
				      db = new Db();
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      myLog.info("getListBaucer("+peringkatBayar+","+idPeringkatbayaran+"):sql="+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idBaucer",rs.getLong("id_baucer"));  
				    	  h.put("idDaerah",rs.getString("id_daerah"));
				    	  h.put("idPeringkatbayaran",rs.getString("id_peringkatbayaran"));
				    	  h.put("namaDaerah",rs.getString("nama_daerah"));  
				    	  h.put("amaunBaucer",Util.formatDecimal(rs.getDouble("amaun_baucer")));  
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
				    
			  }	
			
		//**getListDaerah by penyata cukai by arif
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getListDaerah(int idPeringkatbayaran) throws Exception {
				Db db = null;
				    String sql = "SELECT a.id_daerah, a.kod_Daerah, a.nama_Daerah, c.jumlah_cukai " +
				    			 " ,C.ID_CUKAIUTAMA " +
				    			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c " +
				       			 " WHERE b.id_negeri = c.id_negeri " +
				       			 " and c.id_daerah = a.id_daerah " +
				       			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
				       			 " and b.id_peringkatbayaran ="+idPeringkatbayaran;
				   //myLog.info("getListDaerah:"+sql);
				    try {
				      db = new Db();
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idDaerah",rs.getLong("id_daerah")); 
				    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
				    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
				    	  h.put("namaDaerah",rs.getString("nama_Daerah"));  
				    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
			  }	   

			public static String getLangkahBy(String idUrusan,String idSuburusan)throws Exception {
				Db db = null;
			    String sql = "";
				StringBuffer sb = new StringBuffer("");

			    try {
			    	db = new Db();
			    	Statement stmt = db.getStatement();
			    	SQLRenderer r = new SQLRenderer();
			    	r.add("rsus.id_suburusanstatus");
			      
			    	r.add("rsu.ID_SUBURUSAN",r.unquote("rsus.ID_SUBURUSAN"));
			    	r.add("rsu.ID_URUSAN",r.unquote(idUrusan));
			    	r.add("rsus.ID_SUBURUSAN",r.unquote(idSuburusan));
			           
			    	sql = r.getSQLSelect("tblrujsuburusanstatus rsus, tblrujsuburusan rsu");
			    	myLog.info("FrmUtilData:getPejabatJKPTG::sql"+sql);
			    	ResultSet rs = stmt.executeQuery(sql);

			    	int i=0;
			    	while (rs.next()) { 
			    		i = i+1;
			    		sb.append("Langkah "+i+">>");
			    	}
			    	return sb.toString();
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}
			
			public static String getLangkahByPermohonan(String idUrusan,String idSuburusan,String idPermohonan,
					String role)throws Exception {
				Db db = null;
			    String sql = "";
				StringBuffer sb = new StringBuffer("");
			    try {
			    	db = new Db();
			    	Statement stmt = db.getStatement();
			    	SQLRenderer r = new SQLRenderer();
			    	r.add("rsus.id_suburusanstatus");
			    	r.add("rsus.langkah");
			      
			    	r.add("rsu.ID_SUBURUSAN",r.unquote("rsus.ID_SUBURUSAN"));
			    	r.add("rsu.ID_URUSAN",r.unquote(idUrusan));
			    	r.add("rsus.ID_SUBURUSAN",r.unquote(idSuburusan));
			           
			    	sql = r.getSQLSelect("tblrujsuburusanstatus rsus, tblrujsuburusan rsu");
			    	//myLog.info("FrmUtilData:getLangkahByPermohonan("+idUrusan+","+idSuburusan+","+idPermohonan+","+role+")::sql"+sql);
			    	ResultSet rs = stmt.executeQuery(sql);
			    	Hashtable<String, String> hs = new Hashtable<String, String>();
			    	int i=0;
			    	while (rs.next()) { 
			    		i = i+1;
			    		hs = getLangkahByPermohonan(idPermohonan,""+i,role);
			    		if(hs != null){//	//document.${formName}.action = "../c/${securityToken}?_portal_module=ekptg.view.htp.FrmCukai";{
			    			if(i==1)
			    				sb.append("<a  href=\"javascript:maklumatTerperinci123('"+hs.get("tabid")+"','"+hs.get("modul")+"')\" class=\"style1\">Langkah "+i+"</a>");
			    			else
			    				sb.append(">><a  href=\"javascript:maklumatTerperinci123('"+hs.get("tabid")+"','"+hs.get("modul")+"')\" class=\"style1\">Langkah "+i+"</a>");			    				
			    		}else{
			    			sb.append("<a  href=\"javascript:doAjaxCall${formName}(\"cukaiBayaran\")>>Langkah "+i+"</a>");
			    		}
			    	}
			    	return sb.toString();
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}

			public static Hashtable<String, String> getLangkahByPermohonan(String idPermohonan,String langkah,String role)
			throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.add("distinct(p.id_Fail)");
				r.add("f.no_Fail");
				r.add("f.tajuk_Fail");
				r.add("s.keterangan");
				r.add("um.tab_id");
				r.add("um.module_id");
				r.add("f.id_kemaskini");
				r.add("us.langkah");
				r.add("p.id_Fail",r.unquote("f.id_Fail"));
				r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
				r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
				r.add("us.id_Status",r.unquote("s.id_Status"));
				r.add("us.module_id",r.unquote("um.module_id"));
				r.add("um.user_login",role);
				//r.add("sf.aktif","1");
				r.add("p.id_Permohonan",r.unquote(idPermohonan));
				r.add("us.langkah",r.unquote(langkah));
				//System.out.print(role.substring(role.length()-3, role.length()));
				if(role.substring((role.length()-3), (role.length())).equalsIgnoreCase("PPK")){
					sql = r.getSQLSelect(" Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um","f.id_kemaskini");
				}else{
					sql = r.getSQLSelect(" Tblpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, user_module_template um","f.id_kemaskini");
				}
				//System.out.println("FrmUtilData:getLangkahByPermohonan("+idPermohonan+","+ langkah+","+role+")::sql:::"+sql);

		    ResultSet rs = stmt.executeQuery(sql);
		    Hashtable<String, String> h = null;

		    while (rs.next()) {
		    	h = new Hashtable<String, String>();
		    	h.put("id", rs.getString("id_Fail"));
		    	h.put("no", rs.getString("no_Fail"));
		    	h.put("tajuk", rs.getString("tajuk_Fail"));
		    	h.put("keterangan", rs.getString("keterangan"));
		    	h.put("tabid", rs.getString("tab_id"));
		    	h.put("modul", rs.getString("module_id"));
		    	//h.put("langkah", rs.getString("langkah"));
		    	
		    	h.put("langkah", rs.getString("langkah")==null?"":rs.getString("langkah"));

		    	//list.addElement(h);
		    }
		    
		    return h;
		    } finally {
		      if (db != null) db.close();
		    }
		}

			public static Long getIdStatusFailTerkini(String idpermohonan)
				throws Exception {
				
				Db db = null;
				String sql = "";
				Long idStatus = 0L;
				String strLangkah="0";
				
				try{
					db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    r.add("rsus.langkah");
				      
				    r.add("rsus.id_suburusanstatus",r.unquote("rsusf.id_suburusanstatus"));
				    r.add("rsusf.id_permohonan",r.unquote(idpermohonan));
				           
				    sql = r.getSQLSelect("tblrujsuburusanstatusfail rsusf,tblrujsuburusanstatus rsus");
				    
				    myLog.info("getIdStatusFailTerkini:sql::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				    while (rs.next()) { 
				    	strLangkah = rs.getString("langkah");
				    }
				    Vector<?> vstatus = getIdSuburusanstatusByLangkah(strLangkah,">");			    
				    Hashtable<?, ?> hstatus = (Hashtable<?, ?>)vstatus.get(0);
				    idStatus = (Long)hstatus.get("idsuburusanstatus");					   
				
				  }catch(Exception ex){
					  myLog.info("FrmUtilData:getIdStatusFailTerkini::ex = "+ex);
				  }finally{
					  if (db != null) db.close();
				  }
				return idStatus;		  
			}
			
			//20/04/2010
			public static int getIdStatusFailTerkiniLangkah(String idpermohonan,String idfail)
			throws Exception {
			
			Db db = null;
			String sql = "";
			int idStatus = 0;
			String strLangkah="0";
			
			try{
				db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.add("rsus.langkah");
			      
			    r.add("rsus.id_suburusanstatus",r.unquote("rsusf.id_suburusanstatus"));
			    r.add("rsusf.aktif","1");
			    r.add("rsusf.id_fail",r.unquote(idfail));
			    r.add("rsusf.id_permohonan",r.unquote(idpermohonan));
			           
			    sql = r.getSQLSelect("tblrujsuburusanstatusfail rsusf,tblrujsuburusanstatus rsus");
			    
			    myLog.info("getIdStatusFailTerkini:sql::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			    while (rs.next()) { 
			    	strLangkah = rs.getString("langkah");
			    }
			  }catch(Exception ex){
				  myLog.info("FrmUtilData:getIdStatusFailTerkini::ex = "+ex);
			  }finally{
				  if (db != null) db.close();
			  }
			return idStatus;		  
		}

			
			
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getIdSuburusanstatusByLangkah
				(String langkah, String op) throws Exception {
				Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      SQLRenderer r = new SQLRenderer();
				      r.add("rsus.id_suburusanstatus");
				      r.add("rsus.langkah");
			      
				      r.add("rsus.langkah",langkah,op);
			           
				      sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
				      
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      myLog.info("getIdSuburusanstatusByLangkah:sql::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idsuburusanstatus",rs.getLong("id_suburusanstatus")); 
				    	  h.put("langkah",rs.getString("langkah"));
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
			  }	
			
			/* Created by : Mohamad Rosli 2009/12/24
			 * Tujuan	  : Keluarkan id Sub Urusan (id_suburusanstatus)
			 * Pra syarat : Assign langkah mengikut suburusan
			 * Parameter  : langkah, id_suburusan, operation sql untuk langkah(op) 
			 * return  
			*/
			public static String getIdSuburusanstatusByLangkah
				(String langkah, String idsuburusan, String op) throws Exception {
				String returnValue = "0";
				Db db = null;
				String sql = "";
				try {
					db = new Db();
				    SQLRenderer r = new SQLRenderer();
				    r.add("rsus.id_suburusanstatus");
				    //r.add("rsus.langkah");
				    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
				    r.add("rsus.langkah",langkah,op);
				    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
				      
				    Statement stmt = db.getStatement();
				    myLog.info("getIdSuburusanstatusByLangkah("+ langkah+","+idsuburusan+","+op+"):sql::"+sql);
				    ResultSet rs = stmt.executeQuery(sql);
				    while (rs.next()) {
				    	  returnValue = rs.getString("id_suburusanstatus");
				    }
				    return returnValue;
				} finally {
					if (db != null) db.close();
				}
			}

			/* Created by : Mohamad Rosli 2010/06/16
			 * Tujuan	  : Keluarkan id Sub Urusan (id_suburusanstatus)
			 * Pra syarat : Assign langkah mengikut suburusan
			 * Parameter  : langkah, id_suburusan, operation sql untuk langkah(op) 
			 * return  
			*/
			public String getIdSuburusanstatusByLangkahStr
				(String langkah, String idsuburusan, String op) throws Exception {
				String returnValue = "";
				Db db = null;
				String sql = "";
				try {
					db = new Db();
				    SQLRenderer r = new SQLRenderer();
				    r.add("rsus.id_suburusanstatus");
				    //r.add("rsus.langkah");
				    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
				    r.add("rsus.langkah",langkah,op);
				    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
				      
				    Statement stmt = db.getStatement();
				    myLog.info("getIdSuburusanstatusByLangkahStr("+ langkah+","+idsuburusan+","+op+"):sql::"+sql);
				    ResultSet rs = stmt.executeQuery(sql);
				    while (rs.next()) {
				    	  returnValue = rs.getString("id_suburusanstatus");
				    }
				    return returnValue;
				} finally {
					if (db != null) db.close();
				}
			}
			
			public static int getIdSuburusanstatusByLangkahMax
			(String idsuburusan) throws Exception {
			int returnValue = 0;
			Db db = null;
			String sql = "";
			try {
				db = new Db();
			    SQLRenderer r = new SQLRenderer();
			    //r.add("rsus.id_suburusanstatus");
			    r.add("max(rsus.langkah) langkah");
			    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
			    //r.add("rsus.langkah",langkah,op);
			    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
			      
			    Statement stmt = db.getStatement();
			    ResultSet rs = stmt.executeQuery(sql);
			    while (rs.next()) {
			    	  returnValue = rs.getInt("langkah");
			    }
			    return returnValue;
			} finally {
				if (db != null) db.close();
			}
		}	
		

        public static Long getIdSuburusanStatusByLangkah
			(String langkah, String idsuburusan, String op) throws Exception {
			Long returnValue = 0L;
			Db db = null;
			String sql = "";
			try {
				db = new Db();
			    SQLRenderer r = new SQLRenderer();
			    r.add("rsus.id_suburusanstatus");
			    //r.add("rsus.langkah");
			    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
			    r.add("rsus.langkah",langkah,op);
			    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
			      
			    Statement stmt = db.getStatement();
			    //myLog.info("getIdSuburusanStatusByLangkah("+ langkah+","+idsuburusan+","+op+"):sql::"+sql);
			    ResultSet rs = stmt.executeQuery(sql);
			    while (rs.next()) {
			    	  returnValue = Long.parseLong(rs.getString("id_suburusanstatus"));
			    }
			    return returnValue;
			} finally {
				if (db != null) db.close();
			}
		}	
        
        public static Long getIdStatusByLangkah (String langkah, String idsuburusan, String op) throws Exception {
		Long returnValue = 0L;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
		    SQLRenderer r = new SQLRenderer();
		    r.add("rsus.id_status");
		    //r.add("rsus.langkah");
		    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
		    r.add("rsus.langkah",langkah,op);
		    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
		      
		    Statement stmt = db.getStatement();
		    myLog.info("getIdStatusByLangkah("+ langkah+","+idsuburusan+","+op+"):sql::"+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()) {
		    	  returnValue = Long.parseLong(rs.getString("id_status"));
		    }
		    return returnValue;
		} finally {
			if (db != null) db.close();
		}
		
	}	

			public static String getSeksyenMengikutPengguna(String idPengguna) throws Exception {
				Db db = null;
				String sql = " ";
				String returnValue = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("ID_SEKSYEN");
					r.add("USER_ID", r.unquote(idPengguna));

					sql = r.getSQLSelect("USERS_INTERNAL");
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						returnValue = rs.getString("ID_SEKSYEN");
					}
					return returnValue;
				} finally {
					if (db != null)
						db.close();
				}
			}

			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getLaporanMengikutSeksyen
			(String seksyen) throws Exception {
			Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      SQLRenderer r = new SQLRenderer();
			      r.add("rjd.keterangan");
			      r.add("rdu.module_id");
			      r.add("rdu.peringkat");
			      r.add("rdu.template");
			      r.add("rdu.id_suburusan");
				  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
				  r.add("rdu.DOKUMEN",r.unquote("'L'"));
			      //r.add("rsus.langkah",langkah,op);
		           
			      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd");
			      
			      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
			      Statement stmt = db.getStatement();
			      //myLog.info("getLaporanMengikutSeksyen("+seksyen+"):sql::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable<String, Comparable> h;
			      while (rs.next()) {
			    	  h = new Hashtable<String, Comparable>();
			    	  h.put("idmodule",rs.getString("module_id")); 
			    	  h.put("keterangan",rs.getString("keterangan"));
			    	  h.put("peringkat",rs.getString("peringkat"));
			    	  h.put("template",rs.getString("template"));
			    	  h.put("idsuburusan",rs.getString("id_suburusan"));
			    	  v.addElement(h);
			      }
			      return v;
			    } finally {
			      if (db != null) db.close();
			    }
		  }	
			
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getLaporanMengikutSeksyen
			(String seksyen, String level) throws Exception {
			Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      SQLRenderer r = new SQLRenderer();
			      r.add("rjd.keterangan");
			      r.add("rdu.module_id");
			      r.add("rdu.peringkat");
			      r.add("rdu.template");
			      r.add("rdu.id_suburusan");
				  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
				  r.add("rdu.DOKUMEN",r.unquote("'L'"));
				  String sqlAdd = "";
				  if(level!=null){
					  if(level.equals("unit")){
						  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
					  }else if(level.equals("daerah"))
						  r.add("SUBSTR(rdu.peringkat,0,6)",level);
					  else if(level.equals("negeri"))
						  r.add("SUBSTR(rdu.peringkat,0,6)",level);

				  }		           
			      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd");
			      sql += sqlAdd+ " ORDER BY RDU.ID_DOKUMENURL";;
			      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
			      Statement stmt = db.getStatement();
			      //myLog.info("getLaporanMengikutSeksyen("+seksyen+","+level+"):sql::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable<String, Comparable> h;
			      while (rs.next()) {
			    	  h = new Hashtable<String, Comparable>();
			    	  h.put("idmodule",rs.getString("module_id")); 
			    	  h.put("keterangan",rs.getString("keterangan"));
			    	  h.put("peringkat",rs.getString("peringkat"));
			    	  h.put("template",rs.getString("template"));
			    	  h.put("idsuburusan",rs.getString("id_suburusan"));
			    	  v.addElement(h);
			      }
			      return v;
			    } finally {
			      if (db != null) db.close();
			    }
		  }	
			@SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getLaporanMengikutSeksyen
			(String seksyen, String level, String jlaporan) throws Exception {
			Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      SQLRenderer r = new SQLRenderer();
			      r.add("rjd.keterangan");
			      r.add("rdu.module_id");
			      r.add("rdu.peringkat");
			      r.add("rdu.template");
			      r.add("rdu.id_suburusan");
				  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
				  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
				  String sqlAdd = "";
				  if(level!=null){
					  if(level.equals("unit")){
						  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
					  }else if(level.equals("daerah"))
						  r.add("SUBSTR(rdu.peringkat,0,6)",level);
					  else if(level.equals("negeri"))
						  r.add("SUBSTR(rdu.peringkat,0,6)",level);

				  }		           
			      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd");
			      sql += sqlAdd + " ORDER BY RJD.KETERANGAN desc";
			      System.out.println("sqlSenaraiRekod-->"+sql);
			      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
			      Statement stmt = db.getStatement();
			      //myLog.info("getLaporanMengikutSeksyen("+seksyen+","+level+"):sql::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable<String, Comparable> h;
			      while (rs.next()) {
			    	  h = new Hashtable<String, Comparable>();
			    	  h.put("idmodule",rs.getString("module_id")); 
			    	  h.put("keterangan",rs.getString("keterangan"));
			    	  h.put("peringkat",rs.getString("peringkat"));
			    	  h.put("template",rs.getString("template"));
			    	  h.put("idsuburusan",rs.getString("id_suburusan"));
			    	  v.addElement(h);
			      }
			      return v;
			    } finally {
			      if (db != null) db.close();
			    }
		  }	
			/** 
			 * Mengenalpasti role yang diperolehi oleh pengguna 
			 */
			public static boolean isUserRole(String userid,String roleid) throws Exception {
				boolean returnValue = false;
				Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      SQLRenderer r = new SQLRenderer();
			      r.add("USER_ID");
				  r.add("USER_ID",userid);
				  r.add("ROLE_ID",roleid);
			      sql = r.getSQLSelect("USER_ROLE");		      
			      Statement stmt = db.getStatement();
			      //myLog.info("isUserRole:sql::"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while (rs.next()) { returnValue = true;	}
			      return returnValue;
			    } finally {
			      if (db != null) db.close();
			    }		
			
			}

			public static Vector<Tblrujdaerah> getDaerahByNegeri(String idnegeri) throws Exception {
				myLog.debug("idnegeri ===== "+idnegeri);
				Db db = null;
				String sql = "";
				sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah"
						+ " where ";
				if(idnegeri!=null){
					sql +=" id_negeri=" + idnegeri + " AND ";
					sql +=" KOD_DAERAH !='0' ORDER BY lpad(id_Daerah,10)";
				}
				else{
					sql +=" KOD_DAERAH !='0' ORDER BY lpad(id_Daerah,10)";
				}

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					myLog.debug(":::::::::::::test pajakan"+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Vector<Tblrujdaerah> list = new Vector<Tblrujdaerah>();
					Tblrujdaerah f = null;
					while (rs.next()) {
						f = new Tblrujdaerah();
						f.setIdDaerah(rs.getLong(1));
						f.setKodDaerah(rs.getString(2));
						f.setNamaDaerah(rs.getString(3));
						list.addElement(f);
					}
					return list;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			/* Created by : Mohamad Rosli 2009/12/22
			 * Tujuan	  : Senarai SubUrusan mengikut role 
			 * Pra syarat : Assign user kepada Role terlebih dahulu
			 */
			
			public static Vector<Tblrujsuburusan> getSubUrusanByRole(String login) throws Exception {
				Db db = null;
				String sql = " ";
				try {
					db = new Db();
		    		Statement stmt = db.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("rsu.ID_SUBURUSAN");
		    		r.add("rsu.KOD_SUBURUSAN");
		    		r.add("rsu.NAMA_SUBURUSAN");
		    		r.add("rrsu.NAME");
		    		r.add("ur.ROLE_ID",r.unquote("rrsu.NAME"));
		    		r.add("rsu.ID_SUBURUSAN",r.unquote("rrsu.ID_SUBURUSAN"));		    		    
		    		if(login!=null)	r.add("ur.user_id",login);
		    		
		    		sql = r.getSQLSelect("tblrujrolesuburusan rrsu,user_role ur,tblrujsuburusan rsu"," RSU.KOD_SUBURUSAN" );
		    		//myLog.info("getSubUrusanByRole:sql::"+sql);
		    		ResultSet rs = stmt.executeQuery(sql);			
		    		    
		    		Vector<Tblrujsuburusan> v = new Vector<Tblrujsuburusan>();
	    			Tblrujsuburusan s = null;
	    			while (rs.next()) {
	    				s = new Tblrujsuburusan(); 
		    			s.setIdSuburusan(rs.getLong("id_Suburusan")); 
		    			if(login!=null)
		    				s.setKodSuburusan(rs.getString("kod_Suburusan")); 
		    			else
		    				s.setKodSuburusan(rs.getString("name")); 
		    				
		    			s.setNamaSuburusan(rs.getString("nama_Suburusan"));
	    				v.addElement(s);
	    			}
	    			return v;
	    		} finally {
	    			if (db != null)	db.close();
	    		}
		    	    	
			}			
			
			/* Created by : Mohamad Rosli 2009/12/23
			 * Tujuan	  : Senarai SubUrusan mengikut role 
			 * Pra syarat : Assign user kepada Role terlebih dahulu
			 * return vector 
			*/
			@SuppressWarnings("unchecked")
			public static Vector<Tblrujsuburusan> getSubUrusanByRole(String login,String role) throws Exception {
				Db db = null;
				String sql = " ";
				try {
					db = new Db();
		    		Statement stmt = db.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("rrsu.ID_ROLESUBURUSAN");
		    		r.add("rsu.ID_SUBURUSAN");
		    		r.add("rsu.KOD_SUBURUSAN");
		    		r.add("rsu.NAMA_SUBURUSAN");
		    		r.add("rrsu.NAME");
		    		if(login!=null)	r.add("ur.ROLE_ID",r.unquote("rrsu.NAME"));
		    		r.add("rsu.ID_SUBURUSAN",r.unquote("rrsu.ID_SUBURUSAN"));		    		    
		    		if(login!=null)	{
		    			r.add("ur.user_id",login);
			    		sql = r.getSQLSelect("tblrujrolesuburusan rrsu,user_role ur,tblrujsuburusan rsu"," RSU.KOD_SUBURUSAN" );
		    		}
		    		if(role!=null){
		    			r.add("rrsu.NAME",role);    		
		    			sql = r.getSQLSelect("tblrujrolesuburusan rrsu,tblrujsuburusan rsu"," RSU.KOD_SUBURUSAN" );
		    		}
		    		//myLog.info("getSubUrusanByRole("+login+","+role+"):sql::"+sql);
		    		ResultSet rs = stmt.executeQuery(sql);			
		    		    
		    		Vector v = new Vector();
	    			Hashtable s = null;
	    			while (rs.next()) {
	    				s = new Hashtable(); 
		    			s.put("idrolesuburusan",rs.getLong("ID_ROLESUBURUSAN")); 
		    			s.put("idsuburusan",rs.getLong("id_Suburusan")); 
		    			if(login!=null)
		    				s.put("kodurusan",rs.getString("kod_Suburusan")); 
		    			else
		    				s.put("kodurusan",rs.getString("name")); 
		    				
		    			s.put("namasuburusan",rs.getString("nama_Suburusan"));
		    			
	    				v.addElement(s);
	    			}
	    			return v;
	    		} finally {
	    			if (db != null)	db.close();
	    		}
		    	    	
			}
			public static void StatusChange_ActionOnline(int uid,long idPermohonan, int idSuburusan,
					long idFail){
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
					r.add("Id_Suburusanstatus", getIdSuburusanstatusByLangkah("1",""+idSuburusan,"="));
					r.add("aktif",aktif);
					r.add("id_Masuk", uid);
					r.add("tarikh_Masuk", r.unquote(sekarang));
					r.add("id_KEMASKINI", uid);
					r.add("tarikh_KEMASKINI", r.unquote(sekarang));
					r.add("ID_FAIL", idFail);
					sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
					myLog.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
					stmt.executeUpdate(sql);
				  }catch(Exception ex){
					  myLog.info(":StatusChange_Action::ex = "+ex);
				  }finally{
					  if (db != null) db.close();
				  }		  
			  }

			//X guna method yang ni....
			public static void StatusChange_Action(int uid,long idPermohonan, int idSuburusan,
					long idFail){
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
					r.add("Id_Suburusanstatus", getIdSuburusanstatusByLangkah("1",""+idSuburusan,"="));
					r.add("aktif",aktif);
					r.add("id_Masuk", uid);
					r.add("tarikh_Masuk", r.unquote(sekarang));
					r.add("id_KEMASKINI", uid);
					r.add("tarikh_KEMASKINI", r.unquote(sekarang));
					r.add("ID_FAIL", idFail);
					sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
					myLog.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
					stmt.executeUpdate(sql);
				  }catch(Exception ex){
					  myLog.info(":StatusChange_Action::ex = "+ex);
				  }finally{
					  if (db != null) db.close();
				  }		  
			  }
			
			/* Created by : Mohamad Rosli 2009/12/22
			 * Tujuan	  : Senarai SubUrusan mengikut role 
			 * Pra syarat : Assign user kepada Role terlebih dahulu
			 */
			
			public static String getUrusanByRole(String login) throws Exception {
				Db db = null;
				String sql = " ";
				String returnValue = "";
				try {
					db = new Db();
		    		Statement stmt = db.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("distinct(rsu.ID_URUSAN)");
		    		r.add("ur.ROLE_ID",r.unquote("rrsu.NAME"));
		    		r.add("rsu.ID_SUBURUSAN",r.unquote("rrsu.ID_SUBURUSAN"));		    		    
		    		if(login!=null)	r.add("ur.user_id",login);
		    		
		    		sql = r.getSQLSelect("tblrujrolesuburusan rrsu,user_role ur,tblrujsuburusan rsu" );
		    		ResultSet rs = stmt.executeQuery(sql);					    		    
	    			Tblrujurusan s = null;
	    			while (rs.next()) {
	    				s = new Tblrujurusan(); 
		    			s.setIdUrusan(rs.getLong("id_urusan")); 
		    			returnValue = rs.getString("id_urusan");
	    			}
	    			return returnValue;
	    		} finally {
	    			if (db != null)	db.close();
	    		}
		    	    	
			}			
			/* Created by : Mohamad Rosli 2009/12/28
			 * Tujuan	  : Simpan ke pangkalan data TBLHTPHAKMILIKURUSAN
			 * Pra syarat : Permohonan kena dimasukkan terlebih dahulu
			 */
			public static Long simpan2(Hashtable<?, ?> data) throws Exception {
				Db db = null;
				Long i = 0L;
				String sql = "";
				String Lokasi = "";
				String NoPelan = "";
				String Syarat = "";
				String NoPU = "";
				String NoSyit = "";
				String Sekatan = "";

				try	{
					int idPermohonan = (Integer)data.get("idPermohonan");
				    long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
				    //long idHakmilikurusan = (Long)data.get("idHakmilikurusan");;
				    int idNegeri = Integer.parseInt(data.get("idNegeri").toString());
				    int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
				    int socMukim = Integer.parseInt(data.get("socMukim").toString());
				    int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
				    String NoHakmilik = (String)data.get("NoHakmilik");
				    String NoLot = (String)data.get("NoLot");
				    int socLot = Integer.parseInt(data.get("socLot").toString());
					String TarikhTerima = (String)data.get("TarikhTerima");
					String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
					double CukaiPertama = data.get("CukaiPertama")==null ? 0.0 : Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiPertama").toString()));
					Lokasi = data.get("Lokasi") == null ? "TIADA" : (String)data.get("Lokasi");
					String peganganHakmilik = "TIADA";
					int socLuas = Integer.parseInt(data.get("socLuas").toString());
					NoPelan = data.get("NoPelan") == null ? "TIADA" : (String)data.get("NoPelan");
					int socRizab = Integer.parseInt(data.get("socRizab").toString());
					int socKategori = Integer.parseInt(data.get("socKategori").toString());
					int idSubkategori = Integer.parseInt("96");
					Syarat = data.get("Syarat") == null ? "TIADA" : (String)data.get("Syarat");
					String TarikhLuput = (String)data.get("TarikhLuput");
					String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
					double CukaiSemasa = data.get("CukaiSemasa")==null ? 0.0 : Double.parseDouble(Utils.RemoveSymbol(data.get("CukaiSemasa").toString()));
					String Luas = data.get("Luas")==null ? "0.0" : ""+Double.parseDouble(Utils.RemoveSymbol(data.get("Luas").toString()));
					String TarikhWarta = (String)data.get("TarikhWarta");
					String TW = "to_date('" + TarikhWarta + "','dd/MM/yyyy')";
					String NoWarta = (String)data.get("NoWarta");
					NoPU = data.get("NoPU") == null ? "TIADA" : (String)data.get("NoPU");
					NoSyit = data.get("NoSyit") == null ? "TIADA" : (String)data.get("NoSyit");
					Sekatan = data.get("Sekatan") == null ? "TIADA" : (String)data.get("Sekatan");
					int uid = data.get("uid") == null ? 0 : (Integer)data.get("uid");
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_Hakmilikurusan", idHakmilikurusan);
					r.add("id_Permohonan", idPermohonan);
					r.add("id_Negeri", idNegeri);
					r.add("id_Daerah", socDaerah);
					r.add("id_Mukim", socMukim);
					r.add("id_JenisHakmilik", socJenisHakmilik);
					r.add("no_Hakmilik", NoHakmilik);
					r.add("no_Lot", NoLot);
					r.add("id_Lot", socLot);
					r.add("tarikh_Mula", r.unquote(TT));
					r.add("Cukai", CukaiPertama);
					r.add("Lokasi", Lokasi);
					r.add("pegangan_Hakmilik",peganganHakmilik);
					r.add("id_Luas", socLuas);
					r.add("no_Pelan", NoPelan);
					r.add("id_Jenisrizab", socRizab);
					r.add("id_Kategori", socKategori);
					r.add("id_Subkategori", idSubkategori);
					r.add("Syarat", Syarat);
					r.add("tarikh_Luput", r.unquote(TL));
					r.add("cukai_Terkini", CukaiSemasa);
					r.add("Luas", Luas);
					r.add("tarikh_Rizab", r.unquote(TW));
					r.add("no_Rizab", NoWarta);
					r.add("no_petak", NoPU);
					r.add("no_Syit", NoSyit);
					r.add("Sekatan", Sekatan);
					r.add("id_masuk", uid);
					r.add("tarikh_masuk",r.unquote("SYSDATE"));
					r.add("id_kemaskini", uid);
					r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
					r.add("id_db",r.unquote("99"));
			  
				    sql = r.getSQLInsert("Tblhtphakmilikurusan");
				    //System.out.println("FrmMukimData::Insert::Tblhtphakmilikurusan = "+sql);
				    if(stmt.executeUpdate(sql) !=0)
				    	i = idHakmilikurusan;
				    }
				finally {
					if (db != null) db.close();
				}
				return i;
			}

			/* Created by : Mohamad Rosli 2009/12/28
			 * Tujuan	  : Simpan ke pangkalan data TBLHTPPIHAKBERKEPENTINGAN
			 * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
			 */
			  public static String simpanPB(Hashtable<?, ?> data) throws Exception {
				  Db db = null;
				  String sql = "";
				  String returnValue="0";
				  try{
					  String IdPihakberkepentingan = String.valueOf(DB.getNextID("TBLHTPPIHAKBERKEPENTINGAN_SEQ"));
				      String idHakmilikurusan = String.valueOf(data.get("idHakmilikurusan"));
				      int idJenisnopb = Integer.parseInt("1");
				      int idJenispb = Integer.parseInt("1");
				      String noRujukan = (String)data.get("noRujukan");
				      String nama = (String)data.get("nama");
				      String alamat1 = (String)data.get("alamat1");
				      String alamat2 = data.get("alamat2") == null ? "TIADA" : (String)data.get("alamat2");
				      String alamat3 = data.get("alamat3") == null ? "TIADA" : (String)data.get("alamat3");	      
				      String poskod = (String)data.get("poskod");
				      int idDaerah = (Integer)data.get("idDaerah");
				      int idNegeri = (Integer)data.get("idNegeri");
				      String noTelefon = data.get("noTelefon") == null ? "TIADA" : (String)data.get("noTelefon");	      
				      String noFax = data.get("noFax") == null ? "TIADA" : (String)data.get("noFax");	      
			      
				      Date now = new Date();
				      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
				      String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
				      int uid = data.get("uid") == null ? 0 : (Integer)data.get("uid");

					  db = new Db();
					  Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
					  r.add("id_Pihakberkepentingan", r.unquote(""+IdPihakberkepentingan));
					  r.add("id_Hakmilikurusan", idHakmilikurusan);
					  r.add("id_Jenisnopb", idJenisnopb);
					  r.add("id_Jenispb", idJenispb);
					  r.add("no_rujukan", noRujukan);
					  r.add("nama", nama);
					  r.add("alamat1", alamat1);
					  r.add("alamat2", alamat2);
					  r.add("alamat3", alamat3);
					  r.add("poskod", poskod);
					  r.add("id_Daerah", idDaerah);
					  r.add("id_Negeri", idNegeri);
					  r.add("no_Tel", noTelefon);
					  r.add("no_Fax", noFax);
					  r.add("id_masuk", uid);
					  r.add("tarikh_masuk",r.unquote("SYSDATE"));
					  r.add("id_kemaskini", uid);
					  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
					  r.add("id_db",r.unquote("99"));

					  sql = r.getSQLInsert("Tblhtppihakberkepentingan");
					  //System.out.println(":Insert::Tblhtppihakberkepentingan = "+sql);
					  myLog.info("sql simpanPB : " + sql);
					  stmt.executeUpdate(sql);
				      
				      /*Statement stmtP = db.getStatement();
					  SQLRenderer rP = new SQLRenderer();
					  rP.add("id_Bebanan", idBebanan);
					  rP.add("id_Pihakberkepentingan", IdPihakberkepentingan);
					  rP.add("nama_Pihak_Berkepentingan", nama);
					  rP.add("no_Perserahan", noPerserahan);
					  rP.add("jilid", jilid);
					  rP.add("folio", folio);
					  rP.add("tarikh_Daftar", rP.unquote(sekarang));
					  rP.add("alamat1", alamat1);
					  rP.add("alamat2", alamat2);
					  rP.add("alamat3", alamat3);
					  rP.add("poskod", poskod);
					  rP.add("id_Daerah", idDaerah);
					  rP.add("id_Negeri", idNegeri);
					  rP.add("no_Tel", noTelefon);
					  rP.add("no_Fax", noFax);
					  rP.add("id_Rujbebanan", IdRujbebanan);
					  rP.add("Id_Rujpihakberkepentingan", IdRujpihakberkepentingan);
				      sql = rP.getSQLInsert("Tblhtpbebanan");
				      System.out.println("FrmGadaianHakmilikData::Insert::Tblhtpbebanan = "+sql);
				      stmtP.executeUpdate(sql);*/
					  returnValue = IdPihakberkepentingan;
				    }
				    finally {
				      if (db != null) db.close();
				    }
						  return returnValue ;
			    
				  }
			  /* Created by : Mohamad Rosli 2009/12/28
			   * Tujuan	  : Data dari tblhtphakmilikurusan
			   * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
			   */
			  public static Hashtable<String, String> getHakmilikUrusan(String idPermohonan,String idHakmilikUrusan)throws Exception {
				  System.out.println("String idpermohonan ==="+idPermohonan);
				  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
				  Db db = null;
				  String sql = "";
			      Hashtable<String, String> h = null;
				  try {
					  db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				 
				      r.add("distinct( u.Id_Hakmilikurusan)");
				      r.add("u.Id_Permohonan");
				      //r.add("u.nama_negeri");
				      r.add("u.Id_Negeri");	      
				      r.add("RN.NAMA_NEGERI");
				      r.add("u.Id_Daerah");
				      r.add("RD.NAMA_DAERAH");
				      r.add("u.Id_Mukim");
				      r.add("RM.NAMA_MUKIM");
				      r.add("u.Id_Jenishakmilik");
				      r.add("h.Keterangan as HakKeterangan");
				      r.add("H.KOD_JENIS_HAKMILIK");
				      r.add("u.No_Hakmilik");
				      r.add("u.No_Lot");
				      r.add("u.Id_Lot");
				      r.add("lot.Keterangan as LotKeterangan");
				      r.add("u.Tarikh_Mula");
				      r.add("u.Tarikh_Luput");
				      r.add("u.Luas");
				      r.add("u.Id_Luas");
				      r.add("luas.Keterangan as LuasKeterangan");
				      r.add("u.No_Pelan");
//				      r.add("u.Status_Tanah");
//				      r.add("j.Keterangan");
				      r.add("u.Id_Jenisrizab");
				      r.add("u.Id_Kategori");
					  //2010/02/25
				      r.add("RK.nama_Kementerian");
				      r.add("RK.ID_KEMENTERIAN");
				      r.add("F.no_fail");
				      r.add("F.TAJUK_FAIL");
				      r.add("RA.NAMA_AGENSI");
				      r.add("RA.ID_AGENSI");
				      r.add("PP.NO_RUJUKAN_KJP");
				      r.add("PP.NO_RUJUKAN_LAIN");
				      r.add("U.NO_BANGUNAN");
				      r.add("U.NO_TINGKAT");
				      r.add("U.NO_PETAK");
			      
				  	  if(!idHakmilikUrusan.equals("0") || !idHakmilikUrusan.equals("")){	
				  		  r.add(" u.Id_Hakmilikurusan",r.unquote("'"+idHakmilikUrusan+"'"));	
				  	 }
				      if(!idPermohonan.equals("0") || !idPermohonan.equals("")){	
				    	  r.add("u.Id_Permohonan", r.unquote("'"+idPermohonan+"'"));	}	

				  	  r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
				      r.add("u.Id_Lot",r.unquote("lot.Id_Lot"));
				      r.add("u.Id_Luas",r.unquote("luas.Id_Luas"));
				      r.add("RN.ID_NEGERI",r.unquote("U.ID_NEGERI"));
				      r.add("RD.ID_DAERAH",r.unquote("U.ID_DAERAH"));
				      r.add("RM.ID_MUKIM",r.unquote("U.ID_MUKIM")); 
				      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL")); 
				      r.add("PP.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN")); 
				      r.add("F.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
				      r.add("PP.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
				      r.add("p.ID_PERMOHONAN",r.unquote("u.Id_Permohonan"));
				      
//				      r.add("(u.No_Bangunan","'%TIADA%')","LIKE '%TIADA%' OR u.No_Tingkat LIKE '%TIADA%' OR u.No_Petak LIKE");
//				      r.add("u.No_Bangunan","%TIADA%","LIKE");
//				      r.add("u.No_Tingkat","%TIADA%","LIKE");
//				      r.add("u.No_Petak","%TIADA%","LIKE");
//				      r.add("u.Status_Tanah",r.unquote("j.Id_Jenistanah"));
				      
				      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,Tblhtphakmilikurusan u, Tblrujjenishakmilik h, Tblrujlot lot, " +
				      		"Tblrujluas luas, Tblrujjenistanah j,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM " +
				    		"  ,Tblrujkementerian RK, TBLRUJAGENSI RA" );
				      myLog.info("getHakmilikUrusan ::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      while (rs.next()) {
				    	  h = new Hashtable<String, String>();
				    	  	    	  
				    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
				    	  if(rs.getString("Id_Negeri") == null){
				    		  h.put("IdNegeri","");
				    	  }else{
				    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
				    	  }
				    	  // 
				    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
				    	  h.put("namaNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
				    	  
				    	  if(rs.getString("Id_Daerah") == null){
				    		  h.put("IdDaerah","");
				    	  }else{
				    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
				    	  }
				    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
				    	  h.put("namaDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
				    	  if(rs.getString("Id_Mukim") == null){
				    		  h.put("IdMukim","");
				    	  }else{
				    		  h.put("IdMukim",rs.getString("Id_Mukim"));
				    	  }
				    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
				    	  h.put("namaMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
				    	  if(rs.getString("Id_Jenishakmilik") == null){
				    		  h.put("IdJenisHakmilik","");
				    	  }else{
				    		  h.put("IdJenisHakmilik",rs.getString("Id_Jenishakmilik"));
				    	  }
				    	  h.put("labelNohakmilik", rs.getString("KOD_JENIS_HAKMILIK")+" "+rs.getString("No_Hakmilik"));
				    	  if(rs.getString("HakKeterangan") == null){
				    		  h.put("HakKeterangan","");
				    	  }else{
				    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
				    	  }
				    	  h.put("jenisHakmilik", rs.getString("HAKKETERANGAN")+" "+rs.getString("HAKKETERANGAN"));
				    	  if(rs.getString("No_Hakmilik") == null){
				    		  h.put("NoHakmilik","");
				    	  }else{
				    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
				    	  }
				    	  if(rs.getString("No_Lot") == null){
				    		  h.put("NoLot","");
				    	  }else{
				    		  h.put("NoLot",rs.getString("No_Lot"));
				    	  }
				    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
				    	  if(rs.getString("Id_Lot") == null){
				    		  h.put("IdLot","");
				    	  }else{
				    		  h.put("IdLot",rs.getString("Id_Lot"));
				    	  }
				    	  if(rs.getString("no_bangunan") == null){
				    		  h.put("noBangunan","");
				    	  }else{
				    		  h.put("noBangunan",rs.getString("no_bangunan"));
				    	  }
				    	  if(rs.getString("no_tingkat") == null){
				    		  h.put("noTingkat","");
				    	  }else{
				    		  h.put("noTingkat",rs.getString("no_tingkat"));
				    	  }
				    	  if(rs.getString("no_petak") == null){
				    		  h.put("noPetak","");
				    	  }else{
				    		  h.put("noPetak",rs.getString("no_petak"));
				    	  }
				    	  if(rs.getString("LotKeterangan") == null){
				    		  h.put("LotKeterangan","");
				    	  }else{
				    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
				    	  }
				    	  if(rs.getString("Tarikh_Mula") == null){
				    		  h.put("TarikhMula","");
				    	  }else{
				    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
				    	  }
				    	  if(rs.getString("Tarikh_Luput") == null){
				    		  h.put("TarikhLuput","");
				    	  }else{
				    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
				    	  }
				    	  if(rs.getString("Luas") == null){
				    		  h.put("Luas","");
				    	  }else{
				    		  h.put("Luas",rs.getString("Luas"));
				    	  }
				    	  h.put("IdLuas",rs.getString("Id_Luas"));
				    	  if(rs.getString("LuasKeterangan") == null){
				    		  h.put("LuasKeterangan","");
				    	  }else{
				    		  h.put("LuasKeterangan",rs.getString("LuasKeterangan"));
				    	  }
//				    	  if(rs.getString("Status_Tanah") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("Status_Tanah"));
//				    	  }
				    	  if(rs.getString("No_Pelan") == null){
				    		  h.put("NoPelan","");
				    	  }else{
				    		  h.put("NoPelan",rs.getString("No_Pelan"));
				    	  }
//				    	  if(rs.getString("j.Keterangan") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("j.Keterangan"));
//				    	  }
				    	  if(rs.getString("Id_Jenisrizab") == null){
				    		  h.put("IdRizab","");
				    	  }else{
				    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
				    	  }
				    	  if(rs.getString("Id_Kategori") == null){
				    		  h.put("IdKategori","");
				    	  }else{
				    		  h.put("IdKategori",rs.getString("Id_Kategori"));
				    	  }
				    	  if(rs.getString("nama_Kementerian") == null){
				    		  h.put("namaKementerian","");
				    	  }else{
				    		  h.put("namaKementerian",rs.getString("nama_Kementerian"));
				    	  }	
				    	  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
				    	  h.put("tajukFail", rs.getString("TAJUK_FAIL")==null ? "" :rs.getString("TAJUK_FAIL"));
				    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				    	  h.put("noFailKjp", rs.getString("NO_RUJUKAN_KJP")==null ? "" :rs.getString("NO_RUJUKAN_KJP"));
				    	  h.put("noFailPtg", rs.getString("NO_RUJUKAN_LAIN")==null ? "" :rs.getString("NO_RUJUKAN_LAIN"));
				    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
				    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "" :rs.getString("ID_AGENSI"));
				    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "" :rs.getString("ID_PERMOHONAN"));
				    	  
				    	  //list.addElement(h);
				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
				  }
			  
			  /* Created by : Mohamad Rosli 2009/12/28
			   * Tujuan	  : Data dari tblhtphakmilikurusan
			   * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
			   */
			  public static Hashtable<String, String> getRizabUrusan(int idPermohonan,int idHakmilikUrusan)throws Exception {
				  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
				  Db db = null;
				  String sql = "";
			      Hashtable<String, String> h = null;
				  try {
					  db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				 
				      r.add("DISTINCT( U.ID_HAKMILIKURUSAN)");
				      r.add("U.NO_WARTA");
				      r.add("U.ID_PERMOHONAN");
				      r.add("RN.ID_NEGERI");	      
				      r.add("RN.NAMA_NEGERI");
				      r.add("RD.ID_DAERAH");
				      r.add("RD.NAMA_DAERAH");
				      r.add("RM.ID_MUKIM");
				      r.add("RM.NAMA_MUKIM");
				      r.add("U.NO_LOT");
				      r.add("U.ID_LOT");
				      r.add("LOT.KETERANGAN AS LOTKETERANGAN");
				      r.add("U.Tarikh_Mula");
				      r.add("U.Tarikh_Luput");
				      r.add("U.Luas");
				      r.add("U.Id_Luas");
				      r.add("LUAS.KETERANGAN AS LUASKETERANGAN");
				      r.add("U.NO_PELAN");
				      //r.add("u.Id_Jenisrizab");
				      //r.add("u.Id_Kategori");
					  //2010/02/25
//				      r.add("RK.nama_Kementerian");
//				      r.add("RK.ID_KEMENTERIAN");
//				      r.add("F.no_fail");
//				      r.add("F.TAJUK_FAIL");
//				      r.add("RA.NAMA_AGENSI");
//				      r.add("RA.ID_AGENSI");
//				      r.add("PP.NO_RUJUKAN_KJP");
//				      r.add("PP.NO_RUJUKAN_LAIN");
			      
				      if(idPermohonan != 0){	r.add("U.ID_PERMOHONAN", r.unquote(""+idPermohonan));	}	
				  	  if(idHakmilikUrusan != 0){	r.add(" U.ID_HAKMILIKURUSAN",r.unquote(""+idHakmilikUrusan));	}

				  	  //r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
				      r.add("U.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN")); 
				      r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN")); 
				      r.add("U.ID_MUKIM",r.unquote("RM.ID_MUKIM")); 
				      r.add("RM.ID_DAERAH",r.unquote("RD.ID_DAERAH"));
				      r.add("RD.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
				      r.add("U.ID_LOT",r.unquote("LOT.ID_LOT"));
				      r.add("U.ID_LUAS",r.unquote("LUAS.ID_LUAS"));
				      r.add("PP.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
				      r.add("RA.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
				      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL")); 
				      
				      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,Tblhtphakmilikurusan u, Tblrujlot lot, " +
				      		"Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM " +
				    		"  ,Tblrujkementerian RK, TBLRUJAGENSI RA" );
				      //sql +=" AND U.NO_WARTA IS NOT NULL ";
				      myLog.info("getRizabUrusan ::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      while (rs.next()) {
				    	  h = new Hashtable<String, String>();
				    	  	    	  
				    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
				    	  if(rs.getString("Id_Negeri") == null){
				    		  h.put("IdNegeri","");
				    	  }else{
				    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
				    	  }
				    	  // 
				    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
				    	  h.put("namaNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
				    	  
				    	  if(rs.getString("Id_Daerah") == null){
				    		  h.put("IdDaerah","");
				    	  }else{
				    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
				    	  }
				    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
				    	  h.put("namaDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
				    	  if(rs.getString("Id_Mukim") == null){
				    		  h.put("IdMukim","");
				    	  }else{
				    		  h.put("IdMukim",rs.getString("Id_Mukim"));
				    	  }
				    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
				    	  h.put("namaMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
				    	  /*if(rs.getString("Id_Jenishakmilik") == null){
				    		  h.put("IdJenisHakmilik","");
				    	  }else{
				    		  h.put("IdJenisHakmilik",rs.getString("Id_Jenishakmilik"));
				    	  }
				    	  h.put("labelNohakmilik", rs.getString("KOD_JENIS_HAKMILIK")+" "+rs.getString("No_Hakmilik"));
				    	  if(rs.getString("HakKeterangan") == null){
				    		  h.put("HakKeterangan","");
				    	  }else{
				    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
				    	  }
				    	  h.put("jenisHakmilik", rs.getString("HAKKETERANGAN")+" "+rs.getString("HAKKETERANGAN"));
				    	  if(rs.getString("No_Hakmilik") == null){
				    		  h.put("NoHakmilik","");
				    	  }else{
				    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
				    	  }*/
				    	  if(rs.getString("No_Lot") == null){
				    		  h.put("NoLot","");
				    	  }else{
				    		  h.put("NoLot",rs.getString("No_Lot"));
				    	  }
				    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
				    	  if(rs.getString("Id_Lot") == null){
				    		  h.put("IdLot","");
				    	  }else{
				    		  h.put("IdLot",rs.getString("Id_Lot"));
				    	  }
				    	  if(rs.getString("LotKeterangan") == null){
				    		  h.put("LotKeterangan","");
				    	  }else{
				    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
				    	  }
				    	  if(rs.getString("Tarikh_Mula") == null){
				    		  h.put("TarikhMula","");
				    	  }else{
				    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
				    	  }
				    	  if(rs.getString("Tarikh_Luput") == null){
				    		  h.put("TarikhLuput","");
				    	  }else{
				    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
				    	  }
				    	  if(rs.getString("Luas") == null){
				    		  h.put("Luas","");
				    	  }else{
				    		  h.put("Luas",rs.getString("Luas"));
				    	  }
				    	  h.put("IdLuas",rs.getString("Id_Luas"));
				    	  if(rs.getString("LuasKeterangan") == null){
				    		  h.put("LuasKeterangan","");
				    	  }else{
				    		  h.put("LuasKeterangan",rs.getString("LuasKeterangan"));
				    	  }
//				    	  if(rs.getString("Status_Tanah") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("Status_Tanah"));
//				    	  }
				    	  if(rs.getString("No_Pelan") == null){
				    		  h.put("NoPelan","");
				    	  }else{
				    		  h.put("NoPelan",rs.getString("No_Pelan"));
				    	  }
//				    	  if(rs.getString("j.Keterangan") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("j.Keterangan"));
//				    	  }
				    	  /*if(rs.getString("Id_Jenisrizab") == null){
				    		  h.put("IdRizab","");
				    	  }else{
				    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
				    	  }
				    	  if(rs.getString("Id_Kategori") == null){
				    		  h.put("IdKategori","");
				    	  }else{
				    		  h.put("IdKategori",rs.getString("Id_Kategori"));
				    	  }*/
				    	  if(rs.getString("nama_Kementerian") == null){
				    		  h.put("namaKementerian","");
				    	  }else{
				    		  h.put("namaKementerian",rs.getString("nama_Kementerian"));
				    	  }	
				    	  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
				    	  h.put("tajukFail", rs.getString("TAJUK_FAIL")==null ? "" :rs.getString("TAJUK_FAIL"));
				    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				    	  h.put("noFailKjp", rs.getString("NO_RUJUKAN_KJP")==null ? "" :rs.getString("NO_RUJUKAN_KJP"));
				    	  h.put("noFailPTG", rs.getString("NO_RUJUKAN_LAIN")==null ? "" :rs.getString("NO_RUJUKAN_LAIN"));
				    	  //h.put("noFailPTG", "PTG");
				    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
				    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "" :rs.getString("ID_AGENSI"));
				    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "" :rs.getString("ID_PERMOHONAN"));
				    	  
				      }
				      return h;
				    } finally {
				      if (db != null) db.close();
				    }
				  }
			  

			  /* Created by : Mohamad Rosli 2009/12/31
			   * Tujuan	  : Upadate ke pangkalan data TBLHTPPIHAKBERKEPENTINGAN
			   * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
			   */
			  public static String updatePB(Hashtable<?, ?> data) throws Exception {
				  Db db = null;
				  String sql = "";
				  String returnValue="0";
				  try {
				      String IdPihakberkepentingan = String.valueOf(data.get("idPihakberkepentingan"));
				      String nama = (String)data.get("nama");
				      String alamat1 = (String)data.get("alamat1");
				      String alamat2;
				      if(data.get("alamat2") != null)
				    	  alamat2 = (String)data.get("alamat2");
				      else
				    	  alamat2 = "";
				      String alamat3;
				      if(data.get("alamat3") != null)
				    	  alamat3 = (String)data.get("alamat3");
				      else
				    	  alamat3 = "";		      
				      String poskod = (String)data.get("poskod");
				      int idDaerah = (Integer)data.get("idDaerah");
				      int idNegeri = (Integer)data.get("idNegeri");
				      String noTelefon;
				      if(data.get("noTelefon") != null)
				    	  noTelefon = (String)data.get("noTelefon");
				      else
				    	  noTelefon = "";
				      String noFax;
				      if(data.get("noFax") != null)
				    	  noFax = (String)data.get("noFax");
				      else
				    	  noFax = "";
				      int uid = data.get("uid") == null ? 0 : (Integer)data.get("uid");

					  db = new Db();
					  Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
					  r.update("id_Pihakberkepentingan", IdPihakberkepentingan);
					  r.add("nama",nama);
					  r.add("alamat1",alamat1);
					  r.add("alamat2",alamat2);
					  r.add("alamat3",alamat3);
					  r.add("poskod",poskod);
					  r.add("id_Daerah",idDaerah);
					  r.add("id_Negeri",idNegeri);
					  r.add("no_Tel",noTelefon);
					  r.add("no_Fax",noFax);
					  r.add("id_kemaskini", uid);
					  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
					  r.add("id_db",r.unquote("99"));
					  sql = r.getSQLUpdate("Tblhtppihakberkepentingan");
					  myLog.info("updatePB::Tblhtppihakberkepentingan = "+sql);
					  stmt.executeUpdate(sql);
				      returnValue = IdPihakberkepentingan;
				    }
				    finally {
				      if (db != null) db.close();
				    }
				    return returnValue;
				  }
			  
			  @SuppressWarnings("unchecked")
			public static Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusan
				(String suburusan, String level, String jlaporan) throws Exception {
					String key = "DBgetLaporan";
					Element cachedObject = myCache.get(key);
					if (cachedObject != null) {
					  return (Vector)cachedObject.getObjectValue();
					} else {
						
				  Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      SQLRenderer r = new SQLRenderer();
				      r.add("rjd.keterangan");
				      r.add("rdu.module_id");
				      r.add("rdu.peringkat");
				      r.add("rdu.template");
				      r.add("rdu.id_suburusan");
				      r.add("RU.NAMA_URUSAN");
				      r.add("RDU.ATURAN");
					  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
					  r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
					  r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
					  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
					  String sqlAdd = "";
					  if(level!=null){
						  if(level.equals("unit")){
							  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
						  }else if(level.equals("daerah"))
							  r.add("SUBSTR(rdu.peringkat,0,6)",level);
						  else if(level.equals("negeri"))
							  r.add("SUBSTR(rdu.peringkat,0,6)",level);

					  }		           
				      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
				      if(!suburusan.equals("TIADA")){
				    		  sql += " AND RDU.ID_SUBURUSAN in ("+suburusan+")"; 
				    		  }
				      sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ATURAN";
				      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
				      Statement stmt = db.getStatement();
				      myLog.info("getLaporanMengikutUrusan("+suburusan+","+level+"):sql::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      Hashtable<String, Comparable> h;
				      while (rs.next()) {
				    	  h = new Hashtable<String, Comparable>();
				    	  h.put("idmodule",rs.getString("module_id")); 
				    	  h.put("keterangan",rs.getString("keterangan"));
				    	  h.put("peringkat",rs.getString("peringkat"));
				    	  h.put("template",rs.getString("template"));
				    	  h.put("idsuburusan",rs.getString("id_suburusan"));
				    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
				    	  v.addElement(h);
				      }
				      return v;
				    } finally {
				      if (db != null) db.close();
				    }
					}
			  }		
			  /* Created by : Mohamad Rosli 2010/01/31
			   * Updated	: 2010/02/05
			   * Tujuan	  	: Data dari tblhtppermohonan,tblpermohonan,tblpfdfail
			   * Pra syarat : Pengguna hendaklah diassign kepada sub urusan terlebih dahulu
			   */
				@SuppressWarnings({ "finally", "null" })
				public static Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusanPengguna(String idUrusan, String carian, String noFail,
						Long idNegeri) throws Exception {
				    Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
					Db db = null;
					String sql = "";
					if (idNegeri == 20)
						idNegeri = null;
					try {
						db = new Db();
						Statement stmt = db.getStatement();

						sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri," +
								" n.kod_Mampu,p.id_Permohonan ";
						sql += "FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
						sql += "WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri ";
						sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
						sql += "AND sf.aktif = '1' AND F.ID_SUBURUSAN in (" + idUrusan +") ";
						if(carian != null)
							sql +=" AND f.tajuk_Fail LIKE '%" + carian + "%' ";
						if(noFail != null)
							sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
						if (idNegeri != null)
							sql += "AND f.id_Negeri = " + idNegeri;
						sql += " ORDER BY n.kod_Mampu";
					
						myLog.info(":getSenaraiFailMengikutUrusanPengguna::sql = "+sql);

						ResultSet rs = stmt.executeQuery(sql);
						Hashtable<String, String> h=null;
						//int bil = 1;

						while (rs.next()) {
							h = new Hashtable<String, String>();
							h.put("idpermohonan", rs.getString("id_Permohonan"));
							h.put("idfail", rs.getString("id_Fail"));
							h.put("id", rs.getString("id_Fail"));
							h.put("nofail", rs.getString("no_Fail"));
							h.put("no", rs.getString("no_Fail"));
							h.put("tajuk", rs.getString("tajuk_Fail"));
							h.put("negeri", rs.getString("nama_Negeri"));
							h.put("keterangan", rs.getString("keterangan"));
							h.put("kodmampu", rs.getString("kod_Mampu"));
							list.addElement(h);
						}
						return list;
					} finally {
						if (db != null)
							db.close();
						
						return list;
					}
				}

			  /* Created by : Mohamad Rosli 2010/01/31
			   * Tujuan	  : Data dari tblhtphakmilikurusan
			   * Pra syarat : Pengguna hendaklah diassign kepada sub urusan terlebih dahulu
			   */
			  public static Vector getHakmilikUrusanMengikutSubUrusan(String idSubUrusan)throws Exception {
				  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
				  Db db = null;
				  String sql = "";
			      Hashtable<String, String> h = null;
			      Vector<Hashtable<String, String>> list = null;
				  try {
					  db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				 
				      r.add("distinct( u.Id_Hakmilikurusan)");
				      r.add("u.Id_Permohonan");
				      //r.add("u.nama_negeri");
				      r.add("u.Id_Negeri");	      
				      r.add("RN.NAMA_NEGERI");
				      r.add("u.Id_Daerah");
				      r.add("RD.NAMA_DAERAH");
				      r.add("u.Id_Mukim");
				      r.add("RM.NAMA_MUKIM");
				      r.add("u.Id_Jenishakmilik");
				      r.add("h.Keterangan as HakKeterangan");
				      r.add("H.KOD_JENIS_HAKMILIK");
				      r.add("u.No_Hakmilik");
				      r.add("u.No_Lot");
				      r.add("u.Id_Lot");
				      r.add("lot.Keterangan as LotKeterangan");
				      r.add("u.Tarikh_Mula");
				      r.add("u.Tarikh_Luput");
				      r.add("u.Luas");
				      r.add("u.Id_Luas");
				      r.add("luas.Keterangan as LuasKeterangan");
				      r.add("u.No_Pelan");
//				      r.add("u.Status_Tanah");
//				      r.add("j.Keterangan");
				      r.add("u.Id_Jenisrizab");
				      r.add("u.Id_Kategori");
				  	  //if(idSubUrusan != "0"){	r.add(" u.Id_Hakmilikurusan",r.unquote(""+idHakmilikUrusan));	}
				      r.add("PP.ID_JENISTANAH");
				      r.add("F.NO_FAIL");
				      r.add("U.NO_WARTA");
				      r.add("F.ID_URUSAN");
				      //r.add("(SELECT TO_CHAR(TARIKH_TERIMA,'dd/mm/yyyy') FROM TBLHTPHAKMILIK WHERE ID_PERMOHONAN=P.ID_PERMOHONAN) TARIKH_TERIMA");

				  	  r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
				      r.add("u.Id_Lot",r.unquote("lot.Id_Lot"));
				      r.add("u.Id_Luas",r.unquote("luas.Id_Luas"));
				      r.add("RN.ID_NEGERI",r.unquote("U.ID_NEGERI"));
				      r.add("RD.ID_DAERAH",r.unquote("U.ID_DAERAH"));
				      r.add("RM.ID_MUKIM",r.unquote("U.ID_MUKIM"));
				      r.add("U.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN"));
				      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL"));
				      r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
//				      r.add("(u.No_Bangunan","'%TIADA%')","LIKE '%TIADA%' OR u.No_Tingkat LIKE '%TIADA%' OR u.No_Petak LIKE");
//				      r.add("u.No_Bangunan","%TIADA%","LIKE");
//				      r.add("u.No_Tingkat","%TIADA%","LIKE");
//				      r.add("u.No_Petak","%TIADA%","LIKE");
//				      r.add("u.Status_Tanah",r.unquote("j.Id_Jenistanah"));
				      
				      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,Tblhtphakmilikurusan u, Tblrujjenishakmilik h, Tblrujlot lot, " +
				      		"Tblrujluas luas, TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM");
				      sql +=" AND F.ID_SUBURUSAN IN ("+idSubUrusan+")";
				      //myLog.info("getHakmilikUrusanMengikutSubUrusan ::"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      list = new Vector<Hashtable<String, String>>();
				      while (rs.next()) {
				    	  h = new Hashtable<String, String>();
				    	  	    	  
				    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
				    	  if(rs.getString("Id_Negeri") == null){
				    		  h.put("IdNegeri","");
				    	  }else{
				    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
				    	  }
				    	  // 
				    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
				    	  if(rs.getString("Id_Daerah") == null){
				    		  h.put("IdDaerah","");
				    	  }else{
				    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
				    	  }
				    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
				    	  if(rs.getString("Id_Mukim") == null){
				    		  h.put("IdMukim","");
				    	  }else{
				    		  h.put("IdMukim",rs.getString("Id_Mukim"));
				    	  }
				    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
				    	  if(rs.getString("Id_Jenishakmilik") == null){
				    		  h.put("IdJenishakmilik","");
				    	  }else{
				    		  h.put("IdJenishakmilik",rs.getString("Id_Jenishakmilik"));
				    	  }
				    	  h.put("labelNohakmilik", rs.getString("KOD_JENIS_HAKMILIK")+" "+rs.getString("No_Hakmilik"));
				    	  
				    	  if(rs.getString("HakKeterangan") == null){
				    		  h.put("HakKeterangan","");
				    	  }else{
				    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
				    	  }
				    	  if(rs.getString("No_Hakmilik") == null){
				    		  h.put("NoHakmilik","");
				    	  }else{
				    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
				    	  }
				    	  h.put("noWarta", rs.getString("NO_WARTA")==null?"":rs.getString("NO_WARTA"));

				    	  if(rs.getString("No_Lot") == null){
				    		  h.put("NoLot","");
				    	  }else{
				    		  h.put("NoLot",rs.getString("No_Lot"));
				    	  }
				    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
				    	  if(rs.getString("Id_Lot") == null){
				    		  h.put("IdLot","");
				    	  }else{
				    		  h.put("IdLot",rs.getString("Id_Lot"));
				    	  }
				    	  if(rs.getString("LotKeterangan") == null){
				    		  h.put("LotKeterangan","");
				    	  }else{
				    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
				    	  }
				    	  if(rs.getString("Tarikh_Mula") == null){
				    		  h.put("TarikhMula","");
				    	  }else{
				    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
				    	  }
				    	  if(rs.getString("Tarikh_Luput") == null){
				    		  h.put("TarikhLuput","");
				    	  }else{
				    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
				    	  }
				    	  if(rs.getString("Luas") == null){
				    		  h.put("Luas","");
				    	  }else{
				    		  h.put("Luas",rs.getString("Luas"));
				    	  }
				    	  h.put("IdLuas",rs.getString("Id_Luas"));
				    	  if(rs.getString("LuasKeterangan") == null){
				    		  h.put("LuasKeterangan","");
				    	  }else{
				    		  h.put("LuasKeterangan",rs.getString("LuasKeterangan"));
				    	  }
//				    	  if(rs.getString("Status_Tanah") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("Status_Tanah"));
//				    	  }
				    	  if(rs.getString("No_Pelan") == null){
				    		  h.put("NoPelan","");
				    	  }else{
				    		  h.put("NoPelan",rs.getString("No_Pelan"));
				    	  }
//				    	  if(rs.getString("j.Keterangan") == null){
//				    		  h.put("StatusTanah","");
//				    	  }else{
//				    		  h.put("StatusTanah",rs.getString("j.Keterangan"));
//				    	  }
				    	  if(rs.getString("Id_Jenisrizab") == null){
				    		  h.put("IdRizab","");
				    	  }else{
				    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
				    	  }
				    	  if(rs.getString("Id_Kategori") == null){
				    		  h.put("IdKategori","");
				    	  }else{
				    		  h.put("IdKategori",rs.getString("Id_Kategori"));
				    	  }	    	  
				    	  if(rs.getString("ID_JENISTANAH") == null){
				    		  h.put("IdJenisTanah","");
				    	  }else{
				    		  h.put("IdJenisTanah",rs.getString("ID_JENISTANAH"));
				    	  }	
				    	  if(rs.getString("NO_FAIL") == null){
				    		  h.put("NoFail","");
				    	  }else{
				    		  h.put("NoFail",rs.getString("NO_FAIL"));
				    	  }	//Tarikh_Mula
				    	  //h.put("tarikhTerima", rs.getString("TARIKH_TERIMA")==null?"":rs.getString("TARIKH_TERIMA"));
				    	  h.put("tarikhTerima", rs.getString("Tarikh_Mula")==null?"":rs.getString("Tarikh_Mula"));
				    	  h.put("flagTanah", rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				    	  list.addElement(h);
				      }
				      return list;
				    } finally {
				      if (db != null) db.close();
				    }
				  }			  

			  public static String tambahPeringatan(Hashtable data)throws Exception {
					
				  Db db = null;
				  String sql = "";
				  Date now = new Date();
				  try {	 
				    	  //long idMinitarahan = DB.getNextID("TBLPFDMINITARAHAN_SEQ");
				    	  String idMinitarahan = (String)data.get("id_Permohonan");
				    	  String idDokumen = (String)data.get("id_Dokumen");
				    	  String minitArahan = (String)data.get("minit_Arahan");
					      String pegawaiMengarah = (String)data.get("id_Pegawai_Ygmengarah");
					      String pegawaiMenerima = (String)data.get("id_Pegawai_Ygmenerima");
					      String statusTindakan = (String)data.get("status_Tindakan");
					      String tkhMinitArahan = (String)data.get("tarikh_Minit_Arahan");
						  String tarikhMinitArahan = "to_date('" + tkhMinitArahan + "','dd/MM/yyyy')";
						  //String tempoh = "150";
					      String userid = (String)data.get("userid");
					      String tempoh = (String)data.get("tempoh");
					      
					      db = new Db();
					      Statement stmt = db.getStatement();
					      SQLRenderer r = new SQLRenderer();
					      
					      r.add("ID_PERINGATAN",idMinitarahan);
					      r.add("id_Dokumen",idDokumen);
					      r.add("ARAHAN", minitArahan);
					      r.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
					      r.add("id_Pegawai_Ygmenerima", pegawaiMenerima);
					      r.add("status_Tindakan", statusTindakan);
					      r.add("tarikh_Arahan",r.unquote(tarikhMinitArahan)); 
					      r.add("TEMPOH",r.unquote(tempoh)); 
					      r.add("ID_MASUK",r.unquote(userid)); 
					      r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					      r.add("ID_KEMASKINI",r.unquote(userid)); 
					      r.add("TARIKH_KEMASKINI",r.unquote("sysdate")); 
					      
					      sql = r.getSQLInsert("TBLHTPPERINGATAN");
					      			     
					      stmt.executeUpdate(sql);
					      return idMinitarahan;
					    } finally {
					      if (db != null) db.close();
					    }

				}

				 public static Vector getSenaraiPeringatan(String search,String idMasuk)throws Exception {
					    Db db = null;
					    String sql = "";
					    try {
					      db = new Db();
					      Statement stmt = db.getStatement();
					      SQLRenderer r = new SQLRenderer();

					      //r.add("p.id_Fail");
					      r.add("f.no_Fail");
					      r.add("f.tajuk_Fail");
					      r.add("fdma.ARAHAN");
					      r.add("TO_CHAR(fdma.tarikh_arahan,'dd/mm/yyyy') TARIKH_TAMAT");
					      //r.add("p.id_Permohonan");
					      
					      r.add("p.id_Fail",r.unquote("f.id_Fail"));
					      r.add("p.id_Permohonan",r.unquote("FDMA.ID_PERINGATAN"));
					      //r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
					      //r.add("us.id_Status",r.unquote("s.id_Status"));
					      //r.add("rn.id_Negeri",r.unquote("f.id_Negeri"));

					      r.add("f.id_Urusan","309");
					      r.add("FDMA.STATUS_TINDAKAN","0");
					      //r.add("f.id_Masuk",idMasuk);

					      //r.add("f.no_Fail","%"+search+"%","like");
					      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,tblhtpperingatan fdma");
					      sql +=" AND (fdma.tarikh_arahan - sysdate)< 151 AND (fdma.tarikh_arahan - sysdate)>0 ";
						  //System.out.println("FrmSenaraiFailPajakanKecilData:getList("+search+","+ idMasuk+")::sql:::"+sql);

					      ResultSet rs = stmt.executeQuery(sql);
					      Vector list = new Vector();
					      Hashtable<String, String> h;

					      while (rs.next()) {
					    	  h = new Hashtable<String, String>();
					    	  //h.put("id", rs.getString("id_Fail"));
					    	  h.put("no", rs.getString("no_Fail"));
					    	  h.put("tajuk", rs.getString("tajuk_Fail"));
					    	  //h.put("tujuan", rs.getString("tujuan"));
					    	  h.put("keterangan", rs.getString("arahan"));
					    	  h.put("tarikhtamat", rs.getString("TARIKH_TAMAT"));
					    	  //h.put("idpermohonan", rs.getString("id_Permohonan"));
					    	  list.addElement(h);

						        /*Tblpermohonan per = new Tblpermohonan();
						        per.setIdPermohonan(rs.getLong("id_Fail"));

						        Tblfail fail = new Tblfail();
						        fail.setKodFail(rs.getString("no_Fail"));
						        fail.setTajukFail(rs.getString("tajuk_Fail"));

						        Tblrujstatus status = new Tblrujstatus();
						        status.setKeterangan("keterangan");

						        list.addElement(per);*/
					      }
					      return list;
					    } finally {
					      if (db != null) db.close();
					    }
					  }
			  

				 public static void kemaskiniStatusPermohonan0(Tblrujsuburusanstatusfail s){
					  Db db = null;
					  String sql = "";

					  Date now = new Date();
					  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
					  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
					  
					  try{
						  db = new Db();
						  Statement stmt = db.getStatement();
						  SQLRenderer r = new SQLRenderer();
						  r.update("id_permohonan",r.unquote(""+s.getIdPermohonan()));
						  r.update("id_fail",r.unquote(""+s.getIdFail()));
						  r.update("aktif", "0");
						  //r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
						  //r.add("id_Permohonan", idPermohonan);
						  //r.add("Id_Suburusanstatus", status);
						  r.add("aktif",s.getAktif());
						  r.add("id_kemaskini", s.getIdKemaskini());
						  r.add("tarikh_kemaskini", r.unquote(sekarang));
			 
						  sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
					      myLog.info("kemaskiniStatusPermohonan0:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
					      stmt.executeUpdate(sql);
					  }catch(Exception ex){
						  myLog.info("kemaskiniStatusPermohonan:ex = "+ex);
					  }finally{
						  if (db != null) db.close();
					  }		  
				  }

				//getsubkategori
					public static Vector getSubkategori() throws Exception {
						String key = "DB.getSubkategori";
						Element cachedObject = myCache.get(key);
						if (cachedObject != null) {
						  return (Vector)cachedObject.getObjectValue();
						} else {
						Db db = null;
						String sql = "";
						Vector v = null;
						try {
							db = new Db();
							Statement stmt = db.getStatement();
							
							sql = "Select id_subkategori,kod_subkategori,keterangan from Tblrujsubkategori where kod_subkategori <> '00' order by kod_subkategori";
							
							ResultSet rs = stmt.executeQuery(sql);
							v = new Vector();
							Tblrujsubkategori m = null;
							while (rs.next()) {
								m = new Tblrujsubkategori();
								m.setIdSubkategori(rs.getLong("id_subkategori"));
								m.setKodSubkategori(rs.getString("kod_subkategori"));
								m.setKeterangan(rs.getString("keterangan"));
								v.addElement(m);
							}
							myCache.put(new Element(key, v));
							return v;
						} finally {
							if (db != null)
								db.close();
						}
						}
					}

					//*** Simpan data in database
					  public static String simpan(Hashtable data) throws Exception {
					    String sql = "";
					    String idPermohonan = "0";
					    Long idHakmilikurusan = 0L;
					    Db db = null;
					    Connection conn = null;
					    try	{
					    	//log.info("FrmPerbelianTanahData : " + data);

//					      int idPermohonan = (Integer)data.get("idPermohonan");
					      idPermohonan = (String)data.get("idPermohonan");
//					      long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
					      idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
					      int idNegeri = Integer.parseInt(data.get("socNegeri").toString());
					      int socDaerah = Integer.parseInt(data.get("socDaerah").toString());
					      int socMukim = Integer.parseInt(data.get("socMukim").toString());
					      int socJenisHakmilik = Integer.parseInt(data.get("socJenisHakmilik").toString());
					      String NoHakmilik = (String)data.get("noHakmilik");
					      String NoLot = (String)data.get("noLot");
					      int socLot = Integer.parseInt(data.get("socLot").toString());
						  String TarikhTerima = (String)data.get("tarikhTerima");
						  String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
						  String TarikhLuput = (String)data.get("tarikhLuput");
						  String TL = "to_date('" + TarikhLuput + "','dd/MM/yyyy')";
						  String peganganHakmilik = "JKPTG";
						  String Luas;
						  if(data.get("Luas") != null)
							  Luas = (String)data.get("Luas");
						  else
							  Luas = "0.00";
						  int socLuas = Integer.parseInt(data.get("socLuas").toString());
						  String NoPelan;
						  if(data.get("noPelan") != null)
							  NoPelan = (String)data.get("noPelan");
						  else
							  NoPelan = "TIADA";
//							  String status = (String)data.get("statusTanah");
						  int socRizab = Integer.parseInt(data.get("socRizab").toString());
						  int socKategori = Integer.parseInt(data.get("socKategori").toString());
						  int idSubkategori = Integer.parseInt("96");
						  String tiada = "TIADA";
						  
						  db = new Db();
						  conn = db.getConnection();
						  conn.setAutoCommit(false);
						  
						  Statement stmt = db.getStatement();
						  SQLRenderer r = new SQLRenderer();
						  r.add("id_Hakmilikurusan", idHakmilikurusan);
						  r.add("id_Permohonan", idPermohonan);	
						  r.add("pegangan_Hakmilik",peganganHakmilik);
						  r.add("id_Negeri", idNegeri);
						  r.add("id_Daerah", socDaerah);
						  r.add("id_Mukim", socMukim);
						  r.add("id_JenisHakmilik", socJenisHakmilik);
						  r.add("no_Hakmilik", NoHakmilik);
						  r.add("no_Bangunan", tiada);
						  r.add("no_Tingkat", tiada);
						  r.add("no_Petak", tiada);
						  r.add("no_Lot", NoLot);
						  r.add("id_Lot", socLot);
						  r.add("tarikh_Mula", r.unquote(TT));
						  r.add("tarikh_Luput", r.unquote(TL));
						  r.add("Luas", Luas);
						  r.add("id_Luas", socLuas);
						  r.add("no_Pelan", NoPelan);
//							  r.add("Status_Tanah", status);
						  r.add("id_Jenisrizab", socRizab);
						  r.add("id_Kategori", socKategori);
						  r.add("id_Subkategori", idSubkategori);		  
						  
					      sql = r.getSQLInsert("Tblhtphakmilikurusan");
					      //log.info("FrmPembelianTanahData::Insert::Tblhtphakmilikurusan = "+sql);
					      stmt.executeUpdate(sql);
					      
					      conn.commit();

					    }
					    
					    catch(Exception e){
					    	conn.rollback();
					    	e.printStackTrace();
					    }
					    
					    finally {
					    	if(conn !=null) conn.close();
					    	if (db != null) db.close();
					    }
					    
					    //log.info("FrmPembelianTanahData : Simpan : " + idHakmilikurusan);
					    return ""+idHakmilikurusan;
					  }
					  
					   public static Vector<Tblrujurusan> getUrusanPermohonanPerizapan(String idUrusan) throws Exception {
							String key = "DB.getUrusan"+idUrusan;
							Element cachedObject = myCache.get(key);
							if (cachedObject != null) {
							  return (Vector)cachedObject.getObjectValue();
							} else {
					    	Db db = null;
						    String sql = " ";
						    Vector v = null;
						    try {
						      db = new Db();
						      Statement stmt = db.getStatement();
						      SQLRenderer r = new SQLRenderer();
						      r.add("id_urusan");
						      r.add("kod_urusan");
						      r.add("nama_urusan");						     
						      //if(idUrusan!=null)	r.add("id_Urusan",idUrusan);
						      sql = r.getSQLSelect("tblrujurusan");
						      sql += " where id_urusan in (1,10)";						       
						      ResultSet rs = stmt.executeQuery(sql);

						      v = new Vector();
						      Tblrujurusan s = null;
						      while (rs.next()) {
						        s = new Tblrujurusan();
						        s.setIdUrusan(rs.getLong("id_urusan"));
						        s.setKodUrusan(rs.getString("kod_urusan"));
						        s.setNamaUrusan(rs.getString("nama_urusan"));
						        v.addElement(s);
						        
						      }
						      myCache.put(new Element(key, v));
						      return v;
						      
						    }
						    finally {
						      if (db != null) db.close();
						    }
							}
						  }

		// INSERT TBLHTPHAKMILIK
		public static String insertHTPHakmilik(Db db,Hashtable data) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
			String sql = "";
			String idHakmilik = "";
			//modified by rosli on 07/06/2010, romove comment
			try{
				idHakmilik = String.valueOf(DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"));
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_HAKMILIK", r.unquote(idHakmilik));
				r.add("NO_FAIL_KJP", data.get("txtFailKJP"));						    	  
				r.add("ID_PERMOHONAN", data.get("idPermohonan"));
				r.add("ID_NEGERI", data.get("socNegeriHR"));
				r.add("ID_DAERAH", data.get("socDaerahHR"));
				r.add("ID_MUKIM", data.get("socMukimHR"));
				r.add("ID_JENISHAKMILIK", data.get("socJenisHakmilikHR"));
				    //add by rosli format 0000000
				//r.add("NO_HAKMILIK", data.get("txtNoHakmilik"));
				//String ph = ""+data.get("socNegeriHR")+data.get("socDaerahHR")+data.get("socMukimHR")+data.get("socKodJenisHakmilikHR")+data.get("txtNoHakmilik");
				//comment on 07/06/2010
				//r.add("NO_HAKMILIK",String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtNoHakmilik")))));
				r.add("NO_HAKMILIK",data.get("txtNoHakmilik"));
				String ph = getKodNegeri(String.valueOf(data.get("socNegeriHR")));
				ph += getKodDaerah(String.valueOf(data.get("socDaerahHR")));
				ph += getKodMukim(String.valueOf(data.get("socMukimHR")));
				ph += getKodJenisHakmilik(String.valueOf(data.get("socJenisHakmilikHR")));
				//comment on 07/06/2010
				//ph += String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtNoHakmilik"))));
				ph += data.get("txtNoHakmilik");
				if(!data.get("noBangunan").equals("") && !data.get("noTingkat").equals("") && !data.get("noPetak").equals("")){	
					ph += (String)data.get("noBangunan")+(String)data.get("noTingkat")+(String)data.get("noPetak");
				}			
		
				r.add("PEGANGAN_HAKMILIK", ph);
				r.add("ID_LOT", data.get("socLotHR"));
				r.add("NO_LOT", data.get("txtNoLot"));
				//convert date before add
				String tarikhTerima = (String)(data.get("txdTarikhTerima"));
				String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
				r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
				//convert date before add
				String tarikhDaftar = (String)data.get("txdTarikhDaftar");
				String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
				r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
				   	 
				r.add("LOKASI", data.get("txtLokasi"));
				r.add("ID_LUAS", data.get("socLuas"));
				r.add("ID_LUAS_BERSAMAAN","2");//Default utk hektar
				r.add("TARAF_HAKMILIK", data.get("socTaraf"));
				r.add("ID_KATEGORI", data.get("socKategori"));
				r.add("NO_PELAN", data.get("txtNoPelan"));
				r.add("LUAS_BERSAMAAN", Utils.RemoveComma((String)data.get("txtLuas")));
				//AZAM ADD
				r.add("LUAS", data.get("txtLuasGabung"));
				r.add("TEMPOH", data.get("txtTempoh"));
				r.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	    	  
				r.add("HAKMILIK_ASAL", data.get("txtHakmilikAsal"));
				r.add("CUKAI", Utils.RemoveComma((String)data.get("txtCukaiTahun")));
				r.add("CUKAI_TERKINI", Utils.RemoveComma((String)data.get("txtCukaiTerkini")));
				//convert date before add
				String tarikhLuput = (String)data.get("txdTarikhLuput");
				String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
				r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
				r.add("NO_PU", data.get("txtNoPu"));
				//convert date before add
				String tarikhWarta = (String)data.get("txdTarikhWarta");
				String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
				r.add("TARIKH_RIZAB", r.unquote(txdTarikhWarta));
				r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
				r.add("NO_SYIT", data.get("txtNoSyit"));
				r.add("NO_RIZAB", data.get("txtNoWarta"));
				r.add("SEKATAN", data.get("txtSekatan"));	  
				r.add("SYARAT", data.get("txtSyarat"));	  
				r.add("HAKMILIK_BERIKUT", data.get("txtHakmilikBerikut"));
				r.add("STATUS_SAH", data.get("socStatus"));
				//convert date before add
				//String tarikhKemaskini = currentDate;
				//String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_RIZAB", data.get("socJenisRizab"));    
				r.add("STATUS_RIZAB",data.get("socRizab"));
				//AZAM CHANGE TO GET ID SUB KATEGORI
				//r.add("ID_SUBKATEGORI",data.get("socKategori"));
				r.add("ID_SUBKATEGORI",getSubCategory((String)data.get("socKategori")));
				r.add("ID_AGENSI", data.get("idAgensi"));
				r.add("ID_KEMENTERIAN", data.get("idKementerian"));
				//r.add("STATUS_RIZAB", "Y");
				r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
				//tambah pada 20100709 - hakmilik strata
				r.add("NO_BANGUNAN", data.get("noBangunan"));				
				r.add("NO_TINGKAT", data.get("noTingkat"));				
				r.add("NO_PETAK", data.get("noPetak"));				
				r.add("catatan", data.get("catatan"));				
				sql = r.getSQLInsert("TBLHTPHAKMILIK");
				//myLog.info(sql);
				stmt.executeUpdate(sql);
				
			} catch (SQLException e) {
				throw new SQLException(e);
				
			}
//							    finally {
//							      if (db != null) db.close();
//							    }	
//						
//							    Db db2 = null;
//							    String sql2 = "";
			return idHakmilik;
		}

		// INSERT TBLHTPHAKMILIK PERIHAL
		public static void insertHakmilikPerihal(Db db,Hashtable data,String idhakmilik) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
			String sql = "";
			String idHakmilik = String.valueOf(DB.getNextID("TBLHTPHAKMILIKPERIHAL_SEQ"));
			try {
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("ID_HAKMILIK", r2.unquote(idhakmilik));
				r2.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
				r2.add("TARIKH_MASUK", r2.unquote(txdTarikhKemaskini));
				r2.add("ID_MASUK", r2.unquote(String.valueOf(data.get("idMasuk"))));			  
						    	 
				sql = r2.getSQLInsert("TBLHTPHAKMILIKPERIHAL");
				stmt2.executeUpdate(sql);
			} catch (SQLException e) {
				throw new SQLException(e);
			}
//							    finally {
//							    	if (db != null) db.close();
//							    }
		}
						
		// INSERT TBLHTPHAKMILIKAGENSI
		public static void insertHakmilikAgensi(Db db,Hashtable data,String idhakmilik) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
			String sql = "";
			String idAgensi = String.valueOf(DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"));
			
			try{
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				//r.update("ID_HAKMILIK", data.get("idHakmilik"));
				r.add("ID_HAKMILIKAGENSI", r.unquote(idAgensi));
				r.add("ID_HAKMILIK", r.unquote(idhakmilik));
				r.add("ID_LUAS_BERSAMAAN", 2);
				r.add("LUAS_BERSAMAAN", data.get("txtLuasBersamaan"));
				r.add("ID_LUAS", data.get("socLuas"));
				r.add("LUAS", data.get("txtLuas"));
				r.add("ID_AGENSI", data.get("idAgensi"));
				r.add("ID_KEMENTERIAN", data.get("idKementerian"));
				r.add("STATUS", "Y");
				String tarikhKemaskini = currentDate;
				String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
				r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
				r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));			  
				r.add("TARIKH_KEMASKINI", r.unquote(txdTarikhKemaskini));
				r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));			  
				sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
				myLog.info("insertHakmilikAgensi:sql="+sql);
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				throw new SQLException(e);
			}
						    
//							    finally {
//							      if (db != null) db.close();
//							    }	
		}
						
		//INSERT TBLHTPHAKMILIK CUKAI
		public static void insertHakmilikCukai(Db db,String cukai,String userid,
				String idhakmilik) throws SQLException {
			String sql = "";
			
			try{
				Statement stmt = db.getStatement();
				sql = "INSERT INTO tblhtphakmilikcukai (id_hakmilik,cukai_terkini,cukai," +
					"Status,id_masuk,tarikh_masuk,id_kemaskini,tarikh_kemaskini) " +
					"VALUES ('"+idhakmilik+"','"+cukai+"','"+cukai+"'," +
					"'S','"+userid+"',sysdate,'"+userid+"',sysdate)";
	
				myLog.debug(sql);
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				throw new SQLException(e);
			}
//							    finally {
//							      if (db != null) db.close();
//							    }	
			
		}
						
		//AZAM ADD TRANS
		public static String insertHTPHakmilikTransaction(Hashtable data,String userId) throws Exception {
			Db db = null;
			Connection conn = null;
			String idHakmilik = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				idHakmilik = FrmUtilData.insertHTPHakmilik(db,data);
				//Remark - put KEGUNAAN_TANAH dalam table Hakmilik instead
				//FrmUtilData.insertHakmilikPerihal(db,data,idHakmilik);
									
				FrmUtilData.insertHakmilikAgensi(db,data,idHakmilik);
				FrmUtilData.insertHakmilikCukai(db,
						Utils.RemoveComma((String)data.get("txtCukaiTahun")),
						userId,idHakmilik);
				conn.commit();

			}catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:"+se2.getMessage());
				}
				se.printStackTrace();
			throw new Exception("Ralat Kemasukan Maklumat Hakmilik:"+se.getMessage());
				}finally {
					if (conn != null) conn.close();
					if (db != null)	db.close();
				}	
			return idHakmilik;
		}
						
		public static void insertHTPRizabTransaction_Lama(Hashtable data,String userId) throws Exception {
			Db db = null;
			Connection conn = null;
			String idHakmilik = "";
			FrmRekodUtilData frmRekodUtilData = null;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				frmRekodUtilData = FrmRekodUtilData.getInstance();
				idHakmilik = frmRekodUtilData.insertHTPRizab(db,data);
				//Kegunaan tanah tidak disimpan disini
				//FrmUtilData.insertHakmilikPerihal(db,data,idHakmilik);
				FrmUtilData.insertHakmilikAgensi(db,data,idHakmilik);
				conn.commit();

		    }catch (SQLException se) {
		    	try {
		    		conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:"+se2.getMessage());
				}
				se.printStackTrace();
					throw new Exception("Ralat Kemasukan Maklumat Hakmilik:"+se.getMessage());
				}finally {
					if (conn != null) conn.close();
					if (db != null)	db.close();
				}	
				
		}
		/**
		 * 
		 * @param data
		 * @throws Exception
		 * Diguna dalam 
		 * 	ekptg.view.htp.FrmPendaftaranHakmilik, FrmPendaftaranHakmilikPembelian, FrmPendaftaranHakmilikPerletakhakan
		 *  FrmPendaftaranHakmilikRizab,FrmPendaftaranHakmilikRizabCukai, FrmPendaftaranHakmilikRizabPermohonan
		 *  FrmPendaftaranHakmilikRizabRekod
		 *  
		 *  FrmPendaftaranHakmilikRizabTanah
		 *  FrmPendaftaranTanah
		 * 	FrmRekodPendaftaranPra
		 */
		public static String XinsertHTPRizabTransaction(Hashtable data,String userId) throws Exception {
			Db db = null;
			Connection conn = null;
			String idHakmilik = "";
			FrmRekodUtilData frmRekodUtilData = null;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				frmRekodUtilData = FrmRekodUtilData.getInstance();
				idHakmilik = frmRekodUtilData.insertHTPRizab(db,data);
				//Kegunaan tanah tidak disimpan disini
				//FrmUtilData.insertHakmilikPerihal(db,data,idHakmilik);
				FrmUtilData.insertHakmilikAgensi(db,data,idHakmilik);
				conn.commit();

		    }catch (SQLException se) {
		    	try {
		    		conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:"+se2.getMessage());
				}
				se.printStackTrace();
					throw new Exception("Ralat Kemasukan Maklumat Hakmilik:"+se.getMessage());
			}finally {
					if (conn != null) conn.close();
					if (db != null)	db.close();
			}	
			return idHakmilik;
				
		}
		
		public static String insertHTPRizabTransactionPermohonan(Hashtable data,String userId) throws Exception {
			Db db = null;
			Connection conn = null;
			String idHakmilik = "";
			FrmRekodUtilData frmRekodUtilData = null;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				frmRekodUtilData = FrmRekodUtilData.getInstance();
				idHakmilik = frmRekodUtilData.insertHTPRizab(db,data);
				//Kegunaan tanah tidak disimpan disini
				//FrmUtilData.insertHakmilikPerihal(db,data,idHakmilik);
				FrmUtilData.insertHakmilikAgensi(db,data,idHakmilik);
				conn.commit();

		    }catch (SQLException se) {
		    	try {
		    		conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:"+se2.getMessage());
				}
				se.printStackTrace();
					throw new Exception("Ralat Kemasukan Maklumat Hakmilik:"+se.getMessage());
				}finally {
					if (conn != null) conn.close();
					if (db != null)	db.close();
				}	
				return idHakmilik;
			}		
						
						// KEMASKINI RIZAB BY ID
						public static void updateRizabById(Hashtable data) throws Exception {
						    Db dbHakmilik = null;
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date date = new Date(); 
							String currentDate = sdf.format(date);
						    String sql = "";
						    try
						    {
						    	  dbHakmilik = new Db();
								  Statement stmtHakmilik = dbHakmilik.getStatement();
								  SQLRenderer rHakmilik = new SQLRenderer();
								  rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
						    	  //convert date before add
								  String tarikhTerima = data.get("txdTarikhTerima").toString();
								  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
								  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  	
								  rHakmilik.add("ID_LUAS", data.get("socLuas"));
								  rHakmilik.add("LUAS", data.get("txtLuas"));
								  rHakmilik.add("NO_PELAN", data.get("txtNoPelan"));
								  rHakmilik.add("NO_PU", data.get("txtNoPu"));
								  rHakmilik.add("NO_SYIT", data.get("txtNoSyit"));
								  rHakmilik.add("LOKASI", data.get("txtLokasi"));
								  rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	  
								  rHakmilik.add("STATUS_SAH",data.get("socStatus"));
						    	  //convert date before add
								  String tarikhKemaskini = currentDate;
								  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
								  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote(txdTarikhKemaskini));;			  

								  sql = rHakmilik.getSQLInsert("TBLHTPHAKMILIK");
								  stmtHakmilik.executeUpdate(sql);
							 }
							 finally {
							    if (dbHakmilik != null) dbHakmilik.close();
							 }
							 Db dbHakmilikPerihal = new Db();
							 String sqlHakmilikPerihal = "";
							 try 
							 {
							     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
								 SQLRenderer rHakmilikPerihal = new SQLRenderer();
								 rHakmilikPerihal.update("ID_HAKMILIK", data.get("idHakmilik"));
								 rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
								 //sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
								 //stmtHakmilikPerihal.getSQLInsert(sqlHakmilikPerihal);
								 // sql = rHakmilik.getSQLInsert("TBLHTPHAKMILIK");
								  //sql = rHakmilik.getSQLInsert("TBLHTPHAKMILIK");
								  //stmtHakmilik.executeUpdate(sql);
		


							}
							finally {
								if (dbHakmilikPerihal != null)dbHakmilikPerihal.close();
							}	
							 Db dbHakmilikAgensi_ = new Db();
							 String sqlHakmilikAgensi = "";
						 
							 try 
							 {
							     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
								 SQLRenderer rHakmilikPerihal = new SQLRenderer();
								 rHakmilikPerihal.update("ID_HAKMILIK", data.get("idHakmilik"));
								 rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
								 //sqlHakmilikAgensi = dbHakmilikAgensi_.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
								  sql = rHakmilikPerihal.getSQLInsert("TBLHTPHAKMILIKPERIHAL");
								  stmtHakmilikPerihal.executeUpdate(sql);
		
							}
							finally {
								if (dbHakmilikAgensi_ != null)dbHakmilikAgensi_.close();
							}		  
						}					   

						@SuppressWarnings("unchecked")
						public static Vector<Hashtable<String, Comparable>> getLaporanSuratMengikutUrusan
							(String urusan, String level, String jlaporan) throws Exception {
							Db db = null;
							String sql = "";
							try {
								db = new Db();
								SQLRenderer r = new SQLRenderer();
								r.add("rjd.keterangan");
								r.add("rdu.module_id");
								r.add("rdu.peringkat");
								r.add("rdu.template");
								r.add("rdu.id_suburusan");
								r.add("RU.NAMA_URUSAN");
								r.add("RDU.ATURAN");
								r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
								r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
								r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
								r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
								String sqlAdd = "";
								if(level!=null){
									if(level.equals("unit")){
										sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
									}else if(level.equals("daerah"))
										r.add("SUBSTR(rdu.peringkat,0,6)",level);
									else if(level.equals("negeri"))
										r.add("SUBSTR(rdu.peringkat,0,6)",level);
								  	}		           
								    sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
								    if(!urusan.equals("TIADA")){
								    	sql += " AND RSU.ID_URUSAN in ("+urusan+")"; 
								    }
								    sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ID_DOKUMENURL,RDU.ATURAN";
								    Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
								    Statement stmt = db.getStatement();
								    myLog.info("getLaporanSuratMengikutUrusan("+urusan+","+level+"):sql::"+sql);
								    ResultSet rs = stmt.executeQuery(sql);
								    Hashtable<String, Comparable> h;
								    while (rs.next()) {
								    	  h = new Hashtable<String, Comparable>();
								    	  h.put("idmodule",rs.getString("module_id")); 
								    	  h.put("keterangan",rs.getString("keterangan"));
								    	  h.put("peringkat",rs.getString("peringkat"));
								    	  h.put("template",rs.getString("template"));
								    	  h.put("idsuburusan",rs.getString("id_suburusan"));
								    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
								    	  v.addElement(h);
								    }
								      return v;
								} finally {
									if (db != null) db.close();
								}
							}		
						/* Created by : Mohamad Rosli 2010/01/31
						   * Updated	: 2010/02/05
						   * Tujuan	  	: Data dari tblhtppermohonan,tblpermohonan,tblpfdfail
						   * Pra syarat : Pengguna hendaklah diassign kepada sub urusan terlebih dahulu
						   */
							@SuppressWarnings({ "finally", "null" })
							public static Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusanDanPengguna(String idUrusan, String carian, String noFail,
									String idNegeri,String user) throws Exception {
							    Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
								Db db = null;
								String sql = "";
								//if (idNegeri == "20") idNegeri = null;
								try {
									db = new Db();
									Statement stmt = db.getStatement();

									sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri," +
											" n.kod_Mampu,p.id_Permohonan ";
									sql += "FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
									sql += "WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND " +
											" f.id_Fail = SF.id_Fail AND n.id_Negeri = f.id_Negeri ";
									sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
									sql += "AND sf.aktif = '1' AND F.ID_URUSAN in (" + idUrusan +") ";
									if(carian != null)
										sql +=" AND f.tajuk_Fail LIKE '%" + carian + "%' ";
									if(noFail != null)
										sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
									if (idNegeri != "20")
										sql += "AND f.id_Negeri = " + idNegeri;
									if (user != null)
										sql += " AND F.ID_MASUK = " + user;
									sql += " ORDER BY n.kod_Mampu";
								
									//myLog.info(":getSenaraiFailMengikutUrusanDanPengguna::sql = "+sql);

									ResultSet rs = stmt.executeQuery(sql);
									Hashtable<String, String> h=null;
									//int bil = 1;

									while (rs.next()) {
										h = new Hashtable<String, String>();
										h.put("idpermohonan", rs.getString("id_Permohonan"));
										h.put("idfail", rs.getString("id_Fail"));
										h.put("id", rs.getString("id_Fail"));
										h.put("nofail", rs.getString("no_Fail"));
										h.put("no", rs.getString("no_Fail"));
										h.put("tajuk", rs.getString("tajuk_Fail"));
										h.put("negeri", rs.getString("nama_Negeri"));
										h.put("keterangan", rs.getString("keterangan"));
										h.put("kodmampu", rs.getString("kod_Mampu"));
										list.addElement(h);
									}
									return list;
								} finally {
									if (db != null)
										db.close();
									
									return list;
								}
							}

							/* Created by : Mohamad Rosli 2010/01/31
							   * Tujuan	  : Data dari tblhtphakmilikurusan
							   * Pra syarat : Static untuk urusan Permohonan-1,Rizab-10,Pembelian-2,Perletakhakan-5
							   */
							  public static Vector getHakmilikUrusanMengikutUrusan(String idUrusan)throws Exception {
								  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
								  Db db = null;
								  String sql = "";
							      Hashtable<String, String> h = null;
							      Vector<Hashtable<String, String>> list = null;
								  try {
									  db = new Db();
								      Statement stmt = db.getStatement();
								      SQLRenderer r = new SQLRenderer();
								 
								      r.add("P.ID_PERMOHONAN");
								      r.add("RN.ID_NEGERI");	      
								      r.add("RN.NAMA_NEGERI");
								      r.add("F.NO_FAIL");
								      r.add("F.TAJUK_FAIL");
								      r.add("PP.NO_RUJUKAN_PTG");
								      r.add("PP.NO_RUJUKAN_PTD");
								      r.add("F.ID_URUSAN");
								      r.add("F.ID_FAIL");

								      r.add("RN.ID_NEGERI",r.unquote("F.ID_NEGERI"));
								      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL"));
								      r.add("P.ID_PERMOHONAN",r.unquote("PP.ID_PERMOHONAN"));
								      
								      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP " +
								      		",TBLRUJNEGERI RN ");
								      sql +=" AND F.ID_URUSAN IN ("+idUrusan+") AND ROWNUM <= 50";
								      //myLog.info("getHakmilikUrusanMengikutSubUrusan("+idUrusan+") ::"+sql);
								      ResultSet rs = stmt.executeQuery(sql);
								      list = new Vector<Hashtable<String, String>>();
								      while (rs.next()) {
								    	  h = new Hashtable<String, String>();
								    	  h.put("IdNegeri", rs.getString("ID_NEGERI")==null ? "0" :rs.getString("ID_NEGERI"));
								    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
								    	  h.put("id", rs.getString("ID_FAIL"));
								    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
								    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
								    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
								    	  //h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
								    	  //h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
								     	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
								    	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
								    	  h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
								    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
								    	  list.addElement(h);
								      }
								      return list;
								    } finally {
								      if (db != null) db.close();
								    }
								  }			  

     public static void kemaskiniSubUrusanStatusFail(Tblrujsuburusanstatusfail s){
    	 Db db = null;
    	 String sql = "";
    	 Date now = new Date();
    	 SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    	 String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
    	 try{
    		 db = new Db();
    		 Statement stmt = db.getStatement();
    		 SQLRenderer r = new SQLRenderer();
    		 r.update("ID_SUBURUSANSTATUSFAIL",r.unquote(""+s.getIdSuburusanstatusfail()));
    		 r.add("aktif",s.getAktif());
    		 r.add("id_kemaskini", s.getIdKemaskini());
    		 r.add("tarikh_kemaskini", r.unquote(sekarang));
    		 sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
    		 myLog.info("kemaskiniSubUrusanStatusFail:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
    		 stmt.executeUpdate(sql);
    	 }catch(Exception ex){
   			  myLog.info("kemaskiniSubUrusanStatusFail:ex::"+ex);
    	 }finally{
    		 if (db != null) db.close();
    	 }
     }	
     
 	public  Vector<Hashtable<String, String>> senaraiFailMengikutCarianX(String idUser,
			String nofail,String tajukfail,String id_kementerian,
			String id_negeri,String id_daerah,String id_mukim,String id_urusan)throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = " SELECT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail, " +
	      		" (select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) negeri, "+
	      		" s.keterangan, p.tujuan,PP.NO_RUJUKAN_PTD,F.ID_URUSAN,PP.NO_RUJUKAN_PTG" +
	      		" ,P.ID_PERMOHONAN "+
	      		" FROM TBLPERMOHONAN P, TBLHTPPERMOHONAN PP "+
	      		" ,Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblhtphakmilikurusan THMU,Tblrujsuburusanstatus US,Tblrujstatus S "+
	      		" WHERE P.id_Fail = f.id_Fail  " +
	      		" AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
	      		" AND P.ID_PERMOHONAN = sf.ID_PERMOHONAN  "+
	      		" AND P.ID_PERMOHONAN = thmu.ID_PERMOHONAN(+)  "+
	      		" AND sf.id_Suburusanstatus = us.id_Suburusanstatus  "+
	      		" AND SF.ID_FAIL = F.ID_FAIL "+
	      		" AND us.id_Status = s.id_Status  AND sf.aktif = '1'  AND f.id_urusan in (1,10,25) ";
	      
//	      if (idUser != null) {
//	    	  sql = sql + "AND f.id_masuk='"+idUser+"'";
//	      }
	      
	      if (nofail != null) {
	    	  sql = sql + "AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan)) {
	    	  sql = sql + " AND f.id_urusan in ("+id_urusan+") ";
	      }
	      
	      if (tajukfail != null) {
	    	  sql = sql + "AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      
	      if (id_negeri != null && !"-1".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah)) {
	    	  sql = sql + "AND thmu.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim)) {
	    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	      }
	      
	      if (!isSearch) {
	    	  sql = sql + "AND ROWNUM <= 50 ";
	      }
	      
	      sql = sql + "ORDER BY  f.id_kemaskini ";
	      myLog.info("senaraiFailMengikutCarian() ::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", rs.getString("ID_FAIL"));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	     	  h.put("ptg", Utils.isNull(rs.getString("NO_RUJUKAN_PTG")));
	    	  h.put("ptd", Utils.isNull(rs.getString("NO_RUJUKAN_PTD")));
	    	  h.put("flagTanah", Utils.isNull(rs.getString("ID_URUSAN")));
	    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));


	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  /* Created by : Mohamad Rosli 2010/05/1
	   * Tujuan	  : Data dari tblhtphakmilikurusan
	   * Pra syarat : Data tblhtphakmilikurusan dimasukkan terlebih dahulu
	   */
	  public Hashtable<String, String> getHakmilikUrusanV1X(String idPermohonan,String idHakmilikUrusan)throws Exception {
		  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
		  Db db = null;
		  String sql = "";
	      Hashtable<String, String> h = null;
		  try {
			  db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("distinct( u.Id_Hakmilikurusan)");
		      r.add("u.Id_Permohonan");
		      r.add("u.Id_Negeri");	      
		      r.add("RN.NAMA_NEGERI");
		      r.add("u.Id_Daerah");
		      r.add("RD.NAMA_DAERAH");
		      r.add("u.Id_Mukim");
		      r.add("RM.NAMA_MUKIM");
		      r.add("u.Id_Jenishakmilik");
		      r.add("h.Keterangan as HakKeterangan");
		      r.add("H.KOD_JENIS_HAKMILIK");
		      r.add("u.No_Hakmilik");
		      r.add("u.No_Lot");
		      r.add("u.Id_Lot");
		      r.add("lot.Keterangan as LotKeterangan");
		      r.add("u.Tarikh_Mula");
		      r.add("u.Tarikh_Luput");
		      r.add("u.Luas");
		      r.add("u.Id_Luas");
		      r.add("luas.Keterangan as LuasKeterangan");
		      r.add("u.No_Pelan");
		      r.add("u.Id_Jenisrizab");
		      r.add("u.Id_Kategori");
			  //2010/02/25
		      r.add("RK.nama_Kementerian");
		      r.add("RK.ID_KEMENTERIAN");
		      r.add("F.no_fail");
		      r.add("F.TAJUK_FAIL");
		      r.add("RA.NAMA_AGENSI");
		      r.add("RA.ID_AGENSI");
		      r.add("PP.NO_RUJUKAN_KJP");
		      r.add("PP.NO_RUJUKAN_LAIN");
	      
		      if(idPermohonan != "0"){	r.add("u.Id_Permohonan", r.unquote(idPermohonan));	}	
		  	  if(idHakmilikUrusan != "0"){	r.add(" u.Id_Hakmilikurusan",r.unquote(idHakmilikUrusan));	}

		  	  r.add("u.Id_Jenishakmilik",r.unquote("h.Id_Jenishakmilik"));
		      r.add("u.Id_Lot",r.unquote("lot.Id_Lot"));
		      r.add("u.Id_Luas",r.unquote("luas.Id_Luas"));
		      r.add("RN.ID_NEGERI",r.unquote("U.ID_NEGERI"));
		      r.add("RD.ID_DAERAH",r.unquote("U.ID_DAERAH"));
		      r.add("RM.ID_MUKIM",r.unquote("U.ID_MUKIM")); 
		      r.add("P.ID_FAIL",r.unquote("F.ID_FAIL")); 
		      r.add("PP.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN")); 
		      r.add("F.ID_KEMENTERIAN",r.unquote("RK.ID_KEMENTERIAN"));
		      r.add("PP.ID_AGENSI",r.unquote("RA.ID_AGENSI"));
		      
		      sql = r.getSQLSelect("" +
		      		" TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLHTPHAKMILIKURUSAN U" +
		      		", TBLRUJJENISHAKMILIK H, TBLRUJLOT LOT,TBLRUJLUAS LUAS, TBLRUJJENISTANAH J " +
		      		" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM,TBLRUJKEMENTERIAN RK, TBLRUJAGENSI RA" );
		      //myLog.info("getHakmilikUrusan ::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  	    	  
		    	  h.put("IdHakmilikurusan",rs.getString("Id_Hakmilikurusan"));
		    	  if(rs.getString("Id_Negeri") == null){
		    		  h.put("IdNegeri","");
		    	  }else{
		    		  h.put("IdNegeri",rs.getString("Id_Negeri"));
		    	  }
		    	  h.put("labelNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
		    	  h.put("namaNegeri", rs.getString("Id_Negeri")+" - "+rs.getString("NAMA_NEGERI"));
		    	  
		    	  if(rs.getString("Id_Daerah") == null){
		    		  h.put("IdDaerah","");
		    	  }else{
		    		  h.put("IdDaerah",rs.getString("Id_Daerah"));
		    	  }
		    	  h.put("labelDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
		    	  h.put("namaDaerah", rs.getString("Id_Daerah")+" - "+rs.getString("NAMA_DAERAH"));
		    	  h.put("idMukim", rs.getString("ID_MUKIM")==null ? "0" :rs.getString("ID_MUKIM"));
		    	  h.put("labelMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
		    	  h.put("namaMukim", rs.getString("Id_Mukim")+" - "+rs.getString("NAMA_MUKIM"));
		    	  h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK")==null ? "0" :rs.getString("ID_JENISHAKMILIK"));
		    	  h.put("labelNohakmilik", rs.getString("KOD_JENIS_HAKMILIK")+" "+rs.getString("No_Hakmilik"));
		    	  if(rs.getString("HakKeterangan") == null){
		    		  h.put("HakKeterangan","");
		    	  }else{
		    		  h.put("HakKeterangan",rs.getString("HakKeterangan"));
		    	  }
		    	  h.put("jenisHakmilik", rs.getString("HAKKETERANGAN")+" "+rs.getString("HAKKETERANGAN"));
		    	  if(rs.getString("No_Hakmilik") == null){
		    		  h.put("NoHakmilik","");
		    	  }else{
		    		  h.put("NoHakmilik",rs.getString("No_Hakmilik"));
		    	  }
		    	  h.put("idLot", rs.getString("ID_LOT")==null ? "0" :rs.getString("ID_LOT"));
	    		  h.put("noLot",Utils.isNull(rs.getString("No_Lot")));
		    	  h.put("labelNolot", rs.getString("LotKeterangan")+" "+rs.getString("No_Lot"));
		    	  if(rs.getString("LotKeterangan") == null){
		    		  h.put("LotKeterangan","");
		    	  }else{
		    		  h.put("LotKeterangan",rs.getString("LotKeterangan"));
		    	  }
		    	  if(rs.getString("Tarikh_Mula") == null){
		    		  h.put("TarikhMula","");
		    	  }else{
		    		  h.put("TarikhMula",Format.format(rs.getDate("Tarikh_Mula")));
		    	  }
		    	  if(rs.getString("Tarikh_Luput") == null){
		    		  h.put("TarikhLuput","");
		    	  }else{
		    		  h.put("TarikhLuput",Format.format(rs.getDate("Tarikh_Luput")));
		    	  }
		    	  h.put("idLuas", rs.getString("ID_LUAS")==null ? "0" :rs.getString("ID_LUAS"));
	    		  h.put("luas",Utils.isNull(rs.getString("LUAS")));
	    		  h.put("luasKeterangan",Utils.isNull(rs.getString("LuasKeterangan")));
		    	  if(rs.getString("No_Pelan") == null){
		    		  h.put("NoPelan","");
		    	  }else{
		    		  h.put("NoPelan",rs.getString("No_Pelan"));
		    	  }
		    	  if(rs.getString("Id_Jenisrizab") == null){
		    		  h.put("IdRizab","");
		    	  }else{
		    		  h.put("IdRizab",rs.getString("Id_Jenisrizab"));
		    	  }
		    	  if(rs.getString("Id_Kategori") == null){
		    		  h.put("IdKategori","");
		    	  }else{
		    		  h.put("IdKategori",rs.getString("Id_Kategori"));
		    	  }
		    	  if(rs.getString("nama_Kementerian") == null){
		    		  h.put("namaKementerian","");
		    	  }else{
		    		  h.put("namaKementerian",rs.getString("nama_Kementerian"));
		    	  }	
		    	  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
		    	  h.put("tajukFail", rs.getString("TAJUK_FAIL")==null ? "" :rs.getString("TAJUK_FAIL"));
		    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
		    	  h.put("noFailKjp", rs.getString("NO_RUJUKAN_KJP")==null ? "" :rs.getString("NO_RUJUKAN_KJP"));
		    	  h.put("noFailPtg", rs.getString("NO_RUJUKAN_LAIN")==null ? "" :rs.getString("NO_RUJUKAN_LAIN"));
		    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "0" :rs.getString("ID_KEMENTERIAN"));
		    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "0" :rs.getString("ID_AGENSI"));
		    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
		    	  
		      }
		      return h;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
 	
	//AZAM
	  @Deprecated
	  public static void updateHTPHakmilik(String tablename,Hashtable parameters,String idHakmilik) {
		  updateHTPHakmilik(tablename,parameters,idHakmilik,null);
	  }

	  
	  public static void updateHTPHakmilik(String tablename,Hashtable parameters,String idHakmilik,
			  String extra) {
		  Db db = null;
		  Connection conn = null;
		  String sql = "";String name="";String value="";
		  try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
			  sql = "Update "+tablename+" SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  if (name.toLowerCase().indexOf("tarikh") != -1) {
					  myLog.debug("TARIKH:"+name);
					  if(name.equals("tarikh_luput")){
						  sql = sql + name + "="+ null + "" + (x<parameters.size()?",":"") ; 						  
					  }
					  if (!"".equals(value)) {
						  value = "to_date('"+value+"','dd/MM/yyyy')";
						  sql = sql + name + "="+ value + "" + (x<parameters.size()?",":"") ; 
					  }
					  
				  } else {
					  sql = sql + name + "='"+ value + "'" + (x<parameters.size()?",":"") ;
				  }
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE id_hakmilik = '" +idHakmilik+ "' ";
			  if (extra != null) {
				  sql = sql + extra;
			  }
			  myLog.debug("SQL => "+sql);
			  stmt.executeUpdate(sql);		  
			  conn.commit();
			  
		  }catch(Exception e){
				try{
					conn.rollback();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
				e.printStackTrace();
			}
			finally{
				if(db != null)
					db.close();
			}
		  
	  }

	  public static void updateHakmilik(String tablename,Hashtable parameters,String idHakmilik,
			  String extra) {
		  Db db = null;
		  Connection conn = null;
		  String sql = "";String name="";String value="";
		  try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
			  sql = "Update "+tablename+" SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  if (name.toLowerCase().indexOf("tarikh") != -1) {
					  myLog.debug("TARIKH:"+name);
					  if(name.equals("tarikh_luput") && "".equals(value)){
						  sql = sql + name + "="+ null + "" + (x<parameters.size()?",":"") ; 						  
					  }
					  if (!"".equals(value)) {
						  value = "to_date('"+value+"','dd/MM/yyyy')";
						  sql = sql + name + "="+ value + "" + (x<parameters.size()?",":"") ; 
					  }
					  
				  } else {
					  sql = sql + name + "='"+ value + "'" + (x<parameters.size()?",":"") ;
				  }
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE id_hakmilik = '" +idHakmilik+ "' ";
			  if (extra != null) {
				  sql = sql + extra;
			  }
			  myLog.debug("SQL => "+sql);
			  stmt.executeUpdate(sql);		  
			  conn.commit();
			  
		  }catch(Exception e){
				try{
					conn.rollback();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
				e.printStackTrace();
			}
			finally{
				if(db != null)
					db.close();
			}
		  
	  }
		public static String getSubCategory(String id_kategori) throws Exception {
				String output="2";//DEFAULT
				String sql = "";
				Db db = null;
				try {
					db = new Db(); 
					sql = "SELECT id_subkategori FROM tblrujsubkategori " +
							"WHERE id_kategori='"+id_kategori+"' AND kod_subkategori='00'";
					//myLog.info(sql);
					ResultSet rs = db.getStatement().executeQuery(sql); 
					if (rs.next()){	
						output = rs.getString("id_subkategori");
					}
				} catch (Exception e) {
					throw new Exception ("error getting sub kategory :"+e.getMessage());
				}finally {
					if (db != null) db.close();
				}
				return output;
		}
		
		public static String getKodNegeri(String idNegeri) throws Exception {
			String returnValue = "00";
			Db db = null;
			String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("KOD_NEGERI");
					r.add("ID_NEGERI",idNegeri);
					sql = r.getSQLSelect("TBLRUJNEGERI");

					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
							returnValue = rs.getString("KOD_NEGERI");
					}
				} finally {
					if (db != null)	db.close();
			}
			return returnValue;
		}

		public static String getKodDaerah(String idDaerah) throws Exception {
			String returnValue = "00";
			Db db = null;
			String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("KOD_DAERAH");
					r.add("ID_DAERAH",idDaerah);
					sql = r.getSQLSelect("TBLRUJDAERAH");

					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
							returnValue = rs.getString("KOD_DAERAH");
					}
				} finally {
					if (db != null)	db.close();
			}
			return returnValue;
		}	
		
		public static String getKodMukim(String idMukim) throws Exception {
			String returnValue = "00";
			Db db = null;
			String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("KOD_MUKIM");
					r.add("ID_MUKIM",idMukim);
					sql = r.getSQLSelect("TBLRUJMUKIM");
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
							returnValue = rs.getString("KOD_MUKIM");
					}
				} finally {
					if (db != null)	db.close();
			}
			return returnValue;
		}		
		public static String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
			String returnValue = "00";
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("kod_Jenis_Hakmilik");
				r.add("id_Jenishakmilik",idJenisHakmilik);
				sql = r.getSQLSelect("Tblrujjenishakmilik");
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					returnValue = rs.getString("kod_Jenis_Hakmilik");
				}
			} finally {
				if (db != null)	db.close();
			}
			return returnValue;
		}	
		
		public static Vector<Tblrujjenishakmilik> getJenisHakmilikMengikutNegeri(String idNegeri) throws Exception {
			String key = "DB.getJenisHakmilikMengikutNegeri";
			//Element cachedObject = myCache.get(key);
			//if (cachedObject != null) {
			//  return (Vector<Tblrujjenishakmilik>)cachedObject.getObjectValue();
			//} else {
			Db db = null;
			String sql = "";
			Vector<Tblrujjenishakmilik> v = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("RJH.ID_JENISHAKMILIK");
				r.add("RJH.KOD_JENIS_HAKMILIK");
				r.add("RJH.KETERANGAN");
				r.add("RJH.ID_JENISHAKMILIK",r.unquote("RJHN.ID_JENISHAKMILIK"));
				r.add("RJHN.ID_NEGERI",idNegeri);
	
				sql = r.getSQLSelect("TBLRUJJENISHAKMILIK RJH,TBLRUJJENISHAKMILIKNEGERI RJHN ", " RJHN.ID_JENISHAKMILIKNEGERI");
				//myLog.info("getJenisHakmilikMengikutNegeri("+idNegeri+"):sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);
				v = new Vector<Tblrujjenishakmilik>();
				Tblrujjenishakmilik j = null;
				while (rs.next()) {
					j = new Tblrujjenishakmilik();
					j.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
					j.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
					j.setKeterangan(Utils.isNull(rs.getString("KETERANGAN")));
					v.addElement(j);
				}
				//myCache.put(new Element(key, v));
				return v;
			} finally {
				if (db != null)
					db.close();
			}
			//}
		}

		//created by Rosli on 16/06/2010
		public void simpanKemaskiniStatusPermohonan(String idSuburusan, String langkah
				,String idPermohonan,String idFail,String idUser){
			
			String aktif = "1";
			Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
			Db db = null;
			String sql = "";
			String idSubUrusanStatus = "";
			try{				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				idSubUrusanStatus = getIdSuburusanstatusByLangkahStr(langkah,idSuburusan,"=");
				if(getIdSuburusanStatusFail(idFail,idPermohonan,idSubUrusanStatus)){
					rsusf = new Tblrujsuburusanstatusfail();
					rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
					rsusf.setIdFail(Long.parseLong(idFail));
					rsusf.setIdSuburusanstatus(Long.parseLong(idSubUrusanStatus));
					rsusf.setIdKemaskini(Long.parseLong(idUser));
					kemaskiniStatusPermohonan(rsusf);					
				
				}else{
					rsusf = new Tblrujsuburusanstatusfail();
					rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
					rsusf.setIdFail(Long.parseLong(idFail));
					rsusf.setIdKemaskini(Long.parseLong(idUser));
					rsusf.setAktif("0");
					kemaskiniStatusPermohonanReset(rsusf);
					
					r.add("ID_SUBURUSANSTATUSFAIL", DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r.add("ID_PERMOHONAN", r.unquote(idPermohonan));
					r.add("ID_SUBURUSANSTATUS",r.unquote(idSubUrusanStatus));
					r.add("AKTIF",aktif);
					r.add("ID_MASUK",r.unquote(idUser));
					r.add("TARIKH_Masuk", r.unquote(sekarang));
					r.add("ID_KEMASKINI", r.unquote(idUser));
					r.add("TARIKH_KEMASKINI", r.unquote(sekarang));
					r.add("ID_FAIL", r.unquote(idFail));
					sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
					stmt.executeUpdate(sql);
					
				}	
				myLog.info("simpanKemaskiniStatusPermohonan::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
			  }catch(Exception ex){
				  myLog.info(":simpanKemaskiniStatusPermohonan::ex = "+ex);
			  }finally{
				  if (db != null) db.close();
			  }		  
		  }
		
		  public void kemaskiniStatusPermohonan(Tblrujsuburusanstatusfail s){
			  
			  Db db = null;
			  String sql = "";
			  Date now = new Date();
			  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";			  
			  try{
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("ID_PERMOHONAN",r.unquote(String.valueOf(s.getIdPermohonan())));
				  r.update("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
				  r.update("ID_SUBURUSANSTATUS",r.unquote(String.valueOf(s.getIdSuburusanstatus())));
				  r.add("ID_KEMASKINI", s.getIdKemaskini());
				  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
	 
				  sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			      myLog.info("kemaskiniStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
			      int flag = stmt.executeUpdate(sql);
			      System.out.println("flag "+flag);
			  }catch(Exception ex){
				  ex.printStackTrace();
			  }finally{
				  if (db != null) db.close();
			  }		  
		  }		
		  
		  public static void kemaskiniStatusPermohonanReset(Tblrujsuburusanstatusfail s){
			  
			  Db db = null;
			  String sql = "";
			  Date now = new Date();
			  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";			  
			  try{
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("ID_PERMOHONAN",r.unquote(String.valueOf(s.getIdPermohonan())));
				  r.update("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
				  r.update("AKTIF", "1");
				  r.add("AKTIF",s.getAktif());
				  r.add("ID_KEMASKINI", s.getIdKemaskini());
				  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
	 
				  sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			      myLog.info("kemaskiniStatusPermohonanReset:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
			      int flag = stmt.executeUpdate(sql);
			  }catch(Exception ex){
				  ex.printStackTrace();
			  }finally{
				  if (db != null) db.close();
			  }		  
		  }	
		  
	public static Vector<Tblrujsuburusan> getSubUrusanByUrusan(String idUrusan,String idSuburusan) throws Exception {
		String key = "DB.getSubUrusanByUrusanPembelian"+idUrusan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector)cachedObject.getObjectValue();
		} else {
			Db db = null;
		    String sql = " ";
		    Vector v = null;
		    try {
		    	db = new Db();
		    	v = new Vector<Tblrujsuburusan>();
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();
		    	r.add("id_suburusan");
		    	r.add("kod_suburusan");
		    	r.add("nama_suburusan");
		    	r.add("id_urusan",Integer.parseInt(idUrusan));
		    	r.add("flag_aktif","1","=");
		    	sql = r.getSQLSelect("tblrujsuburusan");
		    	if(idSuburusan!=null)
		    		sql += " AND ID_SUBURUSAN IN ("+idSuburusan+")";

		    	myLog.info("getSubUrusanByUrusan:sql="+sql);
		    	ResultSet rs = stmt.executeQuery(sql);			
		    	Tblrujsuburusan s = null;
		    	while (rs.next()) {
		    		s = new Tblrujsuburusan();
		    		s.setIdSuburusan(rs.getLong("id_suburusan")); 
		    		s.setKodSuburusan(rs.getString("kod_suburusan")); 
		    		s.setNamaSuburusan(rs.getString("nama_suburusan"));
		    		v.addElement(s);
		    		
		    	}
		    	myCache.put(new Element(key, v));
		    	return v;
		    } finally {
		    	if (db != null)
		    	db.close();
		    }
		}
		    	    	
	}		  


    //**Senarai Jenis Tanah
	public static Vector<Tblrujjenistanah> getJenisTanah(String idJenisTanah) throws Exception {
		Vector<Tblrujjenistanah> v = new Vector<Tblrujjenistanah>();
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("ID_JENISTANAH");
	      r.add("KOD_JENIS_TANAH");
	      r.add("Keterangan");

	      sql = r.getSQLSelect("Tblrujjenistanah");
	      if(idJenisTanah!=null){
	    	  sql += " WHERE ID_JENISTANAH in ("+idJenisTanah+") "; 
	      }
	      sql += " ORDER BY KOD_JENIS_TANAH";
	      //myLog.info("getJenisTanah:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Tblrujjenistanah l = null;
	      while (rs.next()) {
	        l = new Tblrujjenistanah();
	        l.setIdJenistanah(rs.getLong("id_JenisTanah"));
	        l.setKodJenistanah(rs.getString("kod_Jenis_Tanah"));
	        l.setKeterangan(rs.getString("Keterangan"));
	        v.addElement(l);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }		
	
    	//**Senarai Jenis Tanah
	public static Tblrujjenistanah getRujJenisTanah(String idJenisTanah) throws Exception {
		Tblrujjenistanah jt = null;
		Db db = null;
	    String sql = "";
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	r.add("ID_JENISTANAH");
	    	r.add("KOD_JENIS_TANAH");
	    	r.add("KETERANGAN");

	    	sql = r.getSQLSelect("Tblrujjenistanah");
	    	if(idJenisTanah!=null){
	    		sql += " WHERE ID_JENISTANAH = "+idJenisTanah+" "; 
	    	}
	    	sql += " ORDER BY KOD_JENIS_TANAH";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	while (rs.next()) {
	    		jt = new Tblrujjenistanah();
	    		jt.setIdJenistanah(rs.getLong("ID_JENISTANAH"));
	    		jt.setKodJenistanah(rs.getString("KOD_JENIS_TANAH"));
	    		jt.setKeterangan(rs.getString("KETERANGAN"));
	    	}
	    	return jt;
	    
	    }finally {
	      if (db != null) db.close();
	    
	    }

	}		
	
	public static String generateNoOnline(int idUrusan, String kodKementerian, String idKementerian, String kodNegeri, String idNegeri) throws Exception{
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);
		String noFail = "";
		String X = String.format("%04d",File.getSeqNo(3, idUrusan,Integer.parseInt(idKementerian), Integer.parseInt(idNegeri),getYear));

		noFail += "JKPTG/BHTP/"+ kodKementerian + "/"+ kodNegeri + "/"+X+"/"+getYear;				
		myLog.info("generateNoOnline:"+noFail);
		return noFail;
		
		
	}
	
	// Add by rosli on 16/08/2010
	public static Vector<Tblrujurusan> getUrusan() throws Exception {
		//String key = "DB.getUrusan";
		/*Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
		  return (Vector)cachedObject.getObjectValue();
		} else {
		*/Vector<Tblrujurusan> v =  null;
		Db db = null;
		String sql = "Select id_urusan,kod_urusan,nama_urusan from "
				+ " tblrujurusan order by nama_urusan";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			v = new Vector<Tblrujurusan>();
			Tblrujurusan u = null;
			while (rs.next()) {
				u = new Tblrujurusan();
				u.setIdUrusan(rs.getLong("id_urusan"));
				u.setKodUrusan(rs.getString("kod_urusan"));
				u.setNamaUrusan(rs.getString("nama_urusan"));
				v.addElement(u);
			}
			//myCache.put(new Element(key, v));
			return v;
		} finally {
			if (db != null)
				db.close();
		}
		//}
	}

	// Add by rosli on 16/08/2010
	public static Vector<Tblrujstatus> getStatus() throws Exception {
		Db db = null;
		String sql = "SELECT ID_STATUS,KETERANGAN"
				+ " FROM TBLRUJSTATUS ORDER BY KETERANGAN";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Tblrujstatus> v = new Vector<Tblrujstatus>();
			Tblrujstatus s = null;
			while (rs.next()) {
				s = new Tblrujstatus();
				s.setIdStatus(rs.getLong("id_Status"));
				s.setKeterangan(rs.getString("keterangan"));
				v.addElement(s);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	public static String getKementerianByMampu(int idkementerian) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_kementerian,kod_kementerian" +
	    		" from tblrujkementerian where id_kementerian="+idkementerian;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      //Tblrujkementerian f = null;
		    if (rs.next()) {
	    	 // f = new Tblrujkementerian();
	    	  //f.setKodKementerian(rs.getString("kod_kementerian"));
		    	output = rs.getString("kod_kementerian");
	      }
	      //return f.getKodKementerian();
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}
	
	public static String getNegeriByMampu(int idnegeri) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_negeri,kod_negeri,nama_negeri,kod_mampu" +
	    		" from tblrujnegeri where id_negeri="+idnegeri;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      //Tblrujnegeri f = null;
		    //System.out.println("FrmPajakanKecil::getNegeriByMampu 1");
		    if (rs.next()) {
	    	  //f = new Tblrujnegeri();
	    	  //f.setKodMampu(rs.getString("kod_mampu"));
		    	output = Utils.isNull(rs.getString("kod_mampu"));
	      }
		   //System.out.println("FrmPajakanKecil::getNegeriByMampu 2"+f.getKodMampu());
	      //return f.getKodMampu();
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}

	public static Vector<Tblrujkementerian> getKementerian() throws Exception {
		/*String key = "DB.getKementerian";
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
		  return (Vector)cachedObject.getObjectValue();
		} else {
		*/
		Db db = null;
		String sql = "select id_kementerian,kod_kementerian,nama_kementerian " +
				"from tblrujkementerian order by kod_kementerian";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			Tblrujkementerian f = null;
			while (rs.next()) {
				f = new Tblrujkementerian();
				f.setIdNegeri(rs.getLong(1));
				f.setIdKementerian(rs.getLong(1));
				f.setKodKementerian(rs.getString(2));
				f.setNamaKementerian(rs.getString(3));
				list.addElement(f);
				
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		//}
	}	

	public static Vector<Hashtable<String, Comparable>> getListNegeri(String idPeringkatbayaran) throws Exception {
		Db db = null;
		String sql = "SELECT RN.ID_NEGERI,RN.NAMA_NEGERI,a.id_daerah, a.kod_Daerah, a.nama_Daerah, SUM(c.jumlah_cukai) jumlah_cukai" +
		   			 " ,C.ID_CUKAIUTAMA " +
		   			 " FROM tblrujdaerah a, tblhtpperingkatbayaran b, tblhtpcukaiutama c,TBLRUJNEGERI RN " +
		   			 " WHERE b.id_negeri = c.id_negeri " +
		   			 " and c.id_daerah = a.id_daerah " +
		   			 " AND A.ID_NEGERI = RN.ID_NEGERI " +
		  			 " and b.id_peringkatbayaran = c.id_peringkatbayaran " +
		   			 " and b.id_peringkatbayaran ='"+idPeringkatbayaran+"' GROUP BY A.ID_NEGERI";
		myLog.info("getListNegeri:"+sql);
		try {
			db = new Db();
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idNegeri",rs.getLong("ID_NEGERI")); 
		    	  h.put("idDaerah",rs.getLong("id_daerah")); 
		    	  h.put("idCukai",rs.getString("id_cukaiutama")); 
		    	  h.put("kod_Daerah",rs.getString("kod_Daerah"));
		    	  //h.put("namaDaerah",rs.getString("nama_Daerah"));  
		    	  h.put("nama",rs.getString("NAMA_NEGERI"));  
		    	  h.put("jumlahCukai",Util.formatDecimal(rs.getDouble("jumlah_cukai")));  
		    	  v.addElement(h);
		      }
		      
		      return v;
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }
		    
	  }	
	//Add by rosli on 29/06/2011 -Paparan kementerian yang aktif
	   public static Vector<Tblrujurusan> getUrusanHTP(String idUrusan) throws Exception {
			String key = "DB.getUrusan"+idUrusan;
			Element cachedObject = myCache.get(key);
			if (cachedObject != null) {
			  return (Vector)cachedObject.getObjectValue();
			} else {
	    	Db db = null;
		    String sql = " ";
		    Vector v = null;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_urusan");
		      r.add("kod_urusan");
		      r.add("nama_urusan");
		      sql = r.getSQLSelect("tblrujurusan");
		      sql += " where id_urusan in ("+idUrusan+")";
		       
		      ResultSet rs = stmt.executeQuery(sql);
		      v = new Vector();
		      Tblrujurusan s = null;
		      while (rs.next()) {
		        s = new Tblrujurusan();
		        s.setIdUrusan(rs.getLong("id_urusan"));
		        s.setKodUrusan(rs.getString("kod_urusan"));
		        s.setNamaUrusan(rs.getString("nama_urusan"));
		        
		        v.addElement(s);
		      }
		      myCache.put(new Element(key, v));
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
			}
	   }	
	
		public static Vector<Tblrujkementerian> getKementerianAktif() throws Exception {
			String key = "DB.getKementerianAktif";
			Element cachedObject = myCache.get(key);
			if (cachedObject != null) {
			  return (Vector)cachedObject.getObjectValue();
			} else {
			Db db = null;
			String sql = "" +
					" SELECT DISTINCT(KM.ID_KEMENTERIANBARU) ID_KEMENTERIAN,RK.KOD_KEMENTERIAN" +
					" ,RK.NAMA_KEMENTERIAN " +
					" FROM TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING KM " +
					" WHERE KM.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN AND KM.STATUS = 'A'" +
					" ORDER BY RK.KOD_KEMENTERIAN";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector list = new Vector();
				Tblrujkementerian f = null;
				while (rs.next()) {
					f = new Tblrujkementerian();
					f.setIdNegeri(rs.getLong(1));
					f.setIdKementerian(rs.getLong(1));
					f.setKodKementerian(rs.getString(2));
					f.setNamaKementerian(rs.getString(3));
					list.addElement(f);
				}
				return list;
			} finally {
				if (db != null)
					db.close();
			}
			}
		}
		
	private static IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}		  
		  		
	   
}
