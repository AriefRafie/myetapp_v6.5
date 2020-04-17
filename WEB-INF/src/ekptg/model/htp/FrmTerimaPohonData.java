
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
import ekptg.helpers.Utils;

/**
 * @author syaiful bahri
 * @date 14/01/2010 
 */

public class FrmTerimaPohonData {
	static Logger myLog = Logger.getLogger(FrmTerimaPohonData.class);
	private IHTPStatus iStatus = null;
//	private Vector checkEmail;	
	//private static Vector list = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");	
/**
 * @category Simpan data ke DB TBLHTPPERMOHONAN dan StatusChange_Action
 * page create file 
 */
	
	public static long simpanTerimaPohon(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";
	    try
	    {
	      long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
	      long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	      long idFail = (Long)data.get("IdFail");
	      int idJKPTG = Integer.parseInt("1");
	      String noPermohonan = "TIADA";
	      String noPerserahan = "TIADA";
	      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
	      String noFailUPT = (String)data.get("noFailUPT");
	      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
	      String tujuan = (String)data.get("Tajuk"); 
	      int idAgensi = Integer.parseInt(data.get("socAgensi").toString());
	      int idSuburusan = (Integer)data.get("idSuburusan");
	      String noFailKJP = (String)data.get("noFailKJP");
	      //String idJenistanah = "1";StatusTanah   
	      String idJenistanah = (String)data.get("StatusTanah");
	      int idPegawai = Integer.parseInt("1");
	      String NoFailLain = (String)data.get("NoFailLain");
	      String TarikhPermohonan = (String)data.get("TarikhPermohonan");
		  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
//		  String TarikhBukaFail = (String)data.get("TarikhBukaFail");
//		  String TBF = "to_date('" + TarikhBukaFail + "','dd/MM/yyyy')";
		  Date now = new Date();
	      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
	      //int userid = Integer.parseInt(data.get("userid").toString());
	      int userid = (Integer)data.get("userid");

		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.add("id_Permohonan", idPermohonan);
		  r.add("id_Fail",idFail);
		  r.add("id_Jkptg",idJKPTG);
		  r.add("no_Permohonan",noPermohonan);
		  r.add("no_Perserahan",noPerserahan);
		  r.add("tarikh_Surat", r.unquote(TSKJP));
		  r.add("tarikh_Terima", r.unquote(TP));
		  r.add("tujuan",tujuan);
		  r.add("id_Masuk",userid);
		  r.add("tarikh_Masuk",r.unquote(TBF));
		  r.add("id_Kemaskini",userid);
		  r.add("tarikh_Kemaskini",r.unquote(TBF));
	      sql = r.getSQLInsert("Tblpermohonan");
	     System.out.println("TBLPERMOHONAN = "+sql);
	      stmt.executeUpdate(sql);
	      
	      Statement stmtHP = db.getStatement();
		  SQLRenderer rHP = new SQLRenderer();
		  rHP.add("id_Htppermohonan",idHtppermohonan);
		  rHP.add("id_Permohonan", idPermohonan);
		  rHP.add("id_Agensi", idAgensi);
		  rHP.add("id_Jenistanah", idJenistanah);
		  rHP.add("id_Pegawai", idPegawai);
		  rHP.add("NO_RUJUKAN_KJP",noFailKJP);
		  rHP.add("no_Rujukan_Lain", noFailUPT);
		  rHP.add("tarikh_Agihan", rHP.unquote(TBF));
		  rHP.add("id_Masuk",userid);
		  rHP.add("tarikh_Masuk",rHP.unquote(TBF));
		  rHP.add("id_Kemaskini",userid);
		  rHP.add("tarikh_Kemaskini",rHP.unquote(TBF));
	      sql = rHP.getSQLInsert("Tblhtppermohonan");
	     // mylog.debug("Insert::tarikh_Agihan = "+ TSKJP );
	      //mylog.debug("Insert::TBLHTPPERMOHONAN = "+sql);
	      stmtHP.executeUpdate(sql);
	      
	      FrmUtilData.StatusChange_Action(userid, idPermohonan, idSuburusan,idFail);
	      //StatusChange_Action(idPermohonan, idSuburusan);
	      
	      return idPermohonan;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}
	
	// Kemaskini pada 2010/11/04
	// Fungsi ini - Tidak digunakan
	/**
	 * 	page simpan Maklumat Asas Tanah
	 */
	public static void simpanMaklumatAsasTanah(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";
	    try
	    {
	    	//long idHakmilikUrusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
//	      long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	    	
	    	String peganganHakmilik = "JKPTG";
	    	int idsubketegori = 96;
	    	int idketegori = 1;
	    	int idjenishakmilik = 0;
	    	//int LuasH = null;
			
	    	long idPermohonan = Utils.parseLong((String)data.get("idpermohonan"));
	    	String idUser = (String)data.get("idUser");
			int idNegeri = Utils.parseInt((String)data.get("socNegeri"));
			int idDaerah = Utils.parseInt((String)data.get("socDaerah"));
			int idMukim = Utils.parseInt((String)data.get("socMukim"));
			int KodLot = Utils.parseInt((String)data.get("socLot"));
			String NoLot = (String)data.get("txtNoLot");
			String noSyit = (String)data.get("noSyit");
			String noPelan = (String)data.get("noPelan");
			//
			if ("".equals(data.get("kodluas"))) {
				data.put("kodluas", "0");
			}
			int kodluas = Utils.parseInt((String)data.get("socLuas"));
			String Luas = (String)data.get("Luas");
			String LuasH = (String)(data.get("LuasH"));
			
			String Lokasi = (String)data.get("Lokasi");
			int jenistanah = Utils.parseInt((String)data.get("jenistanah"));
			
//			mylog.debug("simpanMaklumatAsasTanah :: " + data);
//			String TarikhPermohonan = (String)data.get("TarikhPermohonan");
//		  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
////		  String TarikhBukaFail = (String)data.get("TarikhBukaFail");
////		  String TBF = "to_date('" + TarikhBukaFail + "','dd/MM/yyyy')";
//		  Date now = new Date();
//	      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
//	      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";

		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  
		  //r.add("id_hakmilikurusan", idHakmilikUrusan);
		  r.add("id_masuk", idUser);
		  r.add("id_kemaskini",idUser);
		  r.add("id_Permohonan", idPermohonan);
		  r.add("id_Negeri",idNegeri);
		  r.add("id_daerah",idDaerah);
		  r.add("id_mukim",idMukim);
		  r.add("id_lot",KodLot);
		  r.add("no_lot",NoLot);
		  r.add("no_syit",noSyit);
		  r.add("no_pelan",noPelan);
		  
		  r.add("id_luas","2");
		  
		  r.add("Luas",Luas);
		  
		  r.add("id_luas_bersamaan",kodluas);
		  r.add("Luas_bersamaan",Luas);
		  //r.add("luas_bersamaan",LuasH);
		  r.add("Lokasi",Lokasi);
		  r.add("tarikh_masuk",r.unquote("sysdate"));
			
		  peganganHakmilik = FrmUtilData.getKodNegeri(String.valueOf(idNegeri));
		  peganganHakmilik += FrmUtilData.getKodDaerah(String.valueOf(idDaerah));
		  peganganHakmilik += FrmUtilData.getKodMukim(String.valueOf(idMukim));
		  //peganganHakmilik += getKodJenisHakmilik(String.valueOf(data.get("socJenisHakmilikHR")));
		  //peganganHakmilik += data.get("txtNoHakmilik");
		  //if(!data.get("noBangunan").equals("") && !data.get("noTingkat").equals("") && !data.get("noPetak").equals("")){	
		  //	ph += (String)data.get("noBangunan")+(String)data.get("noTingkat")+(String)data.get("noPetak");
		  //}
		  r.add("pegangan_hakmilik",peganganHakmilik);
		  r.add("id_subkategori",idsubketegori);
		  r.add("id_kategori",idketegori);
		  r.add("id_jenishakmilik",idjenishakmilik);

	      sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
	      //mylog.debug("Insert::TBLHTPHAKMILIKURUSAN = "+sql);
	      stmt.executeUpdate(sql);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}
	
	/**
	 * 	page simpan Lokasi Tanah
	 */
		public static void simpanLokasiTanah(Hashtable data) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {

		    	long idMaklumatTanah = DB.getNextID("TBLHTPMAKLUMATTANAH_SEQ");
		    	
		    	String idUser = data.get("idUser").toString();
		    	String idhakmilikurusan = data.get("idhakmilikurusan").toString();
				String txtbandar = data.get("txtbandar").toString();
				String txtbandarperihal = data.get("txtbandarperihal").toString();
				String txtLebuhRaya = data.get("txtLebuhRaya").toString();
				String txtLebuhRayaperihal = data.get("txtLebuhRayaperihal").toString();
				String txtJkeretapi = (String)data.get("txtJkeretapi").toString();
				String txtJkeretapiperihal = (String)data.get("txtJkeretapiperihal");
				String txtSgPntai = (String)data.get("txtSgPntai");
				String txtSgPntaiperihal = data.get("txtSgPntaiperihal").toString();
				String txtSempadanUtara = (String)data.get("txtSempadanUtara");
				String txtSempadanSelatan = data.get("txtSempadanSelatan").toString();
				String txtSempadanTimur = (String)data.get("txtSempadanTimur");
				String txtSempadanBarat = data.get("txtSempadanBarat").toString();
				String txtSempadanKeteranganLain = (String)data.get("txtSempadanKeteranganLain");
				
				String txtPerihalLokasi = (String)data.get("txtPerihalLokasi");
				String txtZone = (String)data.get("txtZone");
				
				//mylog.debug("simpanLokasiTanah :: " + data);

//				  Date now = new Date();
//			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
//			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
//	
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  r.add("id_maklumattanah", idMaklumatTanah);
				  r.add("id_hakmilikurusan", idhakmilikurusan);
				  r.add("jarak_bandar", txtbandar);
				  r.add("keterangan_bandar", txtbandarperihal);
				  r.add("jarak_jalan", txtLebuhRaya);
				  r.add("keterangan_jalan", txtLebuhRayaperihal);
				  r.add("jarak_keretapi", txtJkeretapi);
				  r.add("keterangan_keretapi", txtJkeretapiperihal);
				  r.add("jarak_sungai",txtSgPntai);
				  r.add("keterangan_sungai",txtSgPntaiperihal);
				  r.add("sempadan_utara", txtSempadanUtara);
				  r.add("sempadan_selatan", txtSempadanSelatan);
				  r.add("sempadan_timur", txtSempadanTimur);
				  r.add("sempadan_barat", txtSempadanBarat);
				  r.add("lain_lain", txtSempadanKeteranganLain);
				  r.add("keterangan", txtPerihalLokasi);
				  r.add("lokasi", txtZone);
				  
			      sql = r.getSQLInsert("TBLHTPMAKLUMATTANAH");
			      //mylog.debug("FrmGadaianSemakanData::Insert::TBLHTPMAKLUMATTANAH = "+sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}
		
		
		public static void kemaskiniLokasiTanah(Hashtable data) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {

		    	//long idMaklumatTanah = DB.getNextID("TBLHTPMAKLUMATTANAH_SEQ");
		    	
		    	String idUser = data.get("idUser").toString();
		    	String idhakmilikurusan = data.get("idhakmilikurusan").toString();
				String txtbandar = data.get("txtbandar").toString();
				String txtbandarperihal = data.get("txtbandarperihal").toString();
				String txtLebuhRaya = data.get("txtLebuhRaya").toString();
				String txtLebuhRayaperihal = data.get("txtLebuhRayaperihal").toString();
				String txtJkeretapi = (String)data.get("txtJkeretapi").toString();
				String txtJkeretapiperihal = (String)data.get("txtJkeretapiperihal");
				String txtSgPntai = (String)data.get("txtSgPntai");
				String txtSgPntaiperihal = data.get("txtSgPntaiperihal").toString();
				String txtSempadanUtara = (String)data.get("txtSempadanUtara");
				String txtSempadanSelatan = data.get("txtSempadanSelatan").toString();
				String txtSempadanTimur = (String)data.get("txtSempadanTimur");
				String txtSempadanBarat = data.get("txtSempadanBarat").toString();
				String txtSempadanKeteranganLain = (String)data.get("txtSempadanKeteranganLain");
				
				String txtPerihalLokasi = (String)data.get("txtPerihalLokasi");
				String txtZone = (String)data.get("txtZone");
				
				//mylog.debug("kemaskiniLokasiTanah :: " + data);

//				  Date now = new Date();
//			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
//			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
//	
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  //r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				 //r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  //r.add("id_maklumattanah", idMaklumatTanah);
				  //r.add("id_hakmilikurusan", idhakmilikurusan);
				  r.add("jarak_bandar", txtbandar);
				  r.add("keterangan_bandar", txtbandarperihal);
				  r.add("jarak_jalan", txtLebuhRaya);
				  r.add("keterangan_jalan", txtLebuhRayaperihal);
				  r.add("jarak_keretapi", txtJkeretapi);
				  r.add("keterangan_keretapi", txtJkeretapiperihal);
				  r.add("jarak_sungai",txtSgPntai);
				  r.add("keterangan_sungai",txtSgPntaiperihal);
				  r.add("sempadan_utara", txtSempadanUtara);
				  r.add("sempadan_selatan", txtSempadanSelatan);
				  r.add("sempadan_timur", txtSempadanTimur);
				  r.add("sempadan_barat", txtSempadanBarat);
				  r.add("lain_lain", txtSempadanKeteranganLain);
				  r.add("keterangan", txtPerihalLokasi);
				  r.add("lokasi", txtZone);
				  r.update("id_hakmilikurusan", idhakmilikurusan);
			      sql = r.getSQLUpdate("TBLHTPMAKLUMATTANAH");
			      //mylog.debug(sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}
/**
 * simpan LarakanPelan
 * @param data
 * @throws Exception
 */
public static void LakaranPelan(Hashtable data,String mode) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {

		    	//long idCharting = DB.getNextID("TBLHTPCHARTING_SEQ");
		    	
		    	String idUser = data.get("idUser").toString();
		    	String idpermohonan = data.get("idpermohonan").toString();
				String FlagCharting = data.get("Flagcharting").toString();
				String idhakmilikurusan = data.get("idhakmilikurusan").toString();
				String JumBayaranPelan = data.get("JumBayaranPelan").toString();
				String ulasan = data.get("ulasan").toString();
				String keteranganImej = (String)data.get("keteranganImej").toString();
				String nofail = (String)data.get("nofail").toString();
				
				//mylog.debug("simpanLokasiTanah :: " + data);

				  Date now = new Date();
			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
	
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  //r.add("id_charting", idCharting);
				  r.add("id_kemaskini", idUser);
				  r.add("id_permohonan", idpermohonan);
				  r.add("flag_charting", FlagCharting);
				  r.add("ulasan", ulasan);
				  r.add("jumlah_bayaran_proses", JumBayaranPelan);
				  r.add("tarikh_kemaskini", r.unquote(TBF));
				  r.add("no_rujukan_seksyen", nofail);
				  if ("update".equals(mode)) {
					  r.update("id_hakmilikurusan", idhakmilikurusan);
					  sql = r.getSQLUpdate("TBLHTPCHARTING");
				  } else {
					  r.add("id_hakmilikurusan", idhakmilikurusan);
					  r.add("id_masuk", idUser);
					  r.add("tarikh_masuk", r.unquote("sysdate"));
					  sql = r.getSQLInsert("TBLHTPCHARTING");
				  }
			      
			      //mylog.debug(sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}

public static void LakaranPelanOnline(Hashtable data,String mode) throws Exception{
	
	Db db = null;
    String sql = "";
    try
    {

    	//long idCharting = DB.getNextID("TBLHTPCHARTING_SEQ");
    	
    	String idUser = data.get("idUser").toString();
    	String idpermohonan = data.get("idpermohonan").toString();
		String FlagCharting = data.get("Flagcharting").toString();
		String idhakmilikurusan = data.get("idhakmilikurusan").toString();
		String JumBayaranPelan = data.get("JumBayaranPelan").toString();
		String ulasan = data.get("ulasan").toString();
		String keteranganImej = (String)data.get("keteranganImej").toString();
		String nofail = "-";
		
		//mylog.debug("simpanLokasiTanah :: " + data);

		  Date now = new Date();
	      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";

		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  
		  //r.add("id_charting", idCharting);
		  r.add("id_kemaskini", idUser);
		  r.add("id_permohonan", idpermohonan);
		  r.add("flag_charting", FlagCharting);
		  r.add("ulasan", ulasan);
		  r.add("jumlah_bayaran_proses", JumBayaranPelan);
		  r.add("tarikh_kemaskini", r.unquote(TBF));
		  r.add("no_rujukan_seksyen", nofail);
		  if ("update".equals(mode)) {
			  r.update("id_hakmilikurusan", idhakmilikurusan);
			  sql = r.getSQLUpdate("TBLHTPCHARTING");
		  } else {
			  r.add("id_hakmilikurusan", idhakmilikurusan);
			  r.add("id_masuk", idUser);
			  r.add("tarikh_masuk", r.unquote("sysdate"));
			  sql = r.getSQLInsert("TBLHTPCHARTING");
		  }
	      
	      //mylog.debug(sql);
	      stmt.executeUpdate(sql);
      

    } finally {
      if (db != null) db.close();
    }	
}

	/**
	 * Simpan Bukti Pembayaran 
	 * @param data
	 * @throws Exception
	 */
	public static void Pembayaran(Hashtable data,String mode) throws Exception{			
		Db db = null;
		String sql = "";
		
		try {
			long idBayaran = DB.getNextID("TBLHTPBAYARAN_SEQ");
		    String idjenisbayaran = "20";
		    String idUser = data.get("idUser").toString();
		    String idpermohonan = data.get("idpermohonan").toString();
			String TarikhSuratKePTD = data.get("TarikhSuratKePTD").toString();
			String NoResit = data.get("NoResit").toString();
			String caraBayaran = data.get("caraBayaran").toString();
			String tempatBayaran = data.get("tempatBayaran").toString();
			String JumBayaran = (String)data.get("JumBayaran").toString();
			String TarikhResit = (String)data.get("TarikhResit").toString();
			String NoBaucerCekDraft = (String)data.get("NoBaucerCekDraft").toString();
			String TarikhBaucerCek = (String)data.get("TarikhBaucerCek").toString();
			//mylog.debug("simpanLokasiTanah :: " + data);

			Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			String TPTD = "to_date('" + TarikhSuratKePTD + "','dd/MM/yyyy')";
			String TR = "to_date('" + TarikhResit + "','dd/MM/yyyy')";
			String TB = "to_date('" + TarikhBaucerCek + "','dd/MM/yyyy')";
	
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//r.add("id_bayaran", idBayaran);
			r.add("id_jenisbayaran", idjenisbayaran);
			r.add("id_masuk", idUser);
			r.add("id_kemaskini", idUser);
			r.add("tarikh_terima", r.unquote(TPTD));
			r.add("no_resit", NoResit);
			r.add("id_carabayar", caraBayaran);
			r.add("nama_bank", tempatBayaran);
			r.add("jumlah_bayaran", JumBayaran);
			r.add("tarikh_resit",r.unquote(TR));
			r.add("no_baucer", NoBaucerCekDraft);
			r.add("tarikh_baucer", r.unquote(TB));
			r.add("tarikh_masuk", r.unquote(TBF));
			r.add("tarikh_kemaskini", r.unquote(TBF));
			//r.add("no_rujukan_seksyen", nofail2);
			if ("update".equals(mode)) {
				r.update("id_permohonan", idpermohonan);
				sql = r.getSQLUpdate("TBLHTPBAYARAN");
				
			} else {
				r.add("id_permohonan", idpermohonan);
				sql = r.getSQLInsert("TBLHTPBAYARAN");
				
			}
			myLog.info(sql);
			stmt.executeUpdate(sql);
		     
	    } finally {
	    	if (db != null) db.close();
	    }	
	
	}
	
/**
 * Update Pembayaran Bukti 
 * @param data
 * @throws Exception
 */
public static void updatePembayaran(Hashtable data) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {

		    	String idjenisbayaran = "20";
		    	String idUser = data.get("idUser").toString();
		    	String idbayaran = data.get("idbayaran").toString();
		    	String idpermohonan = data.get("idpermohonan").toString();
				String TarikhSuratKePTD = data.get("TarikhSuratKePTD").toString();
				String NoResit = data.get("NoResit").toString();
				String caraBayaran = data.get("caraBayaran").toString();
				String tempatBayaran = data.get("tempatBayaran").toString();
				String JumBayaran = (String)data.get("JumBayaran").toString();
				String TarikhResit = (String)data.get("TarikhResit").toString();
				String NoBaucerCekDraft = (String)data.get("NoBaucerCekDraft").toString();
				String TarikhBaucerCek = (String)data.get("TarikhBaucerCek").toString();

				//mylog.debug("simpanLokasiTanah :: " + data);

				  Date now = new Date();
			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			      String TPTD = "to_date('" + TarikhSuratKePTD + "','dd/MM/yyyy')";
			      String TR = "to_date('" + TarikhResit + "','dd/MM/yyyy')";
			      String TB = "to_date('" + TarikhBaucerCek + "','dd/MM/yyyy')";

	
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_bayaran", idbayaran);
				  r.add("id_jenisbayaran", idjenisbayaran);
				  r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				  r.add("id_permohonan", idpermohonan);
				  r.add("tarikh_terima", r.unquote(TPTD));
				  r.add("no_resit", NoResit);
				  r.add("id_carabayar", caraBayaran);
				  r.add("nama_bank", tempatBayaran);
				  r.add("jumlah_bayaran", JumBayaran);
				  r.add("tarikh_resit",r.unquote(TR));
				  r.add("no_baucer", NoBaucerCekDraft);
				  r.add("tarikh_baucer", r.unquote(TB));
				  r.add("tarikh_masuk", r.unquote(TBF));
				  r.add("tarikh_kemaskini", r.unquote(TBF));
				  //r.add("no_rujukan_seksyen", nofail2);

			      sql = r.getSQLUpdate("TBLHTPBAYARAN");
			      //ylog.debug("simpanpBAYARAN::Insert::TBLHTPBAYARAN = "+sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}

	/**
	 * simpan Pembayaran Bukti
	 * @param data
	 * @throws Exception
	 */
	public static void KeputusanBayaran(Hashtable data,String mode) throws Exception{
		Db db = null;
		String sql = "";
		try{
			long idKeputusanHohon = DB.getNextID("TBLHTPKEPUTUSANMOHON_SEQ");		    	
		    String idUser = data.get("idUser").toString();
		    String idPermohonan = data.get("idpermohonan").toString();
			String NoRujukPTD = data.get("NoRujukPTD").toString();
			String TarikhKeputusan = data.get("TarikhKeputusan").toString();
			String PilihKeputusan = data.get("PilihKeputusan").toString();
			String Catatan = data.get("Catatan").toString();
			String flagNotifikasi = data.get("flagNotifikasi").toString();
			System.out.println(" flagNotifikasi :::::::::::: "+flagNotifikasi);
			String idSubUrusan = String.valueOf(data.get("idSubUrusan"));
			
			Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			String TK = "to_date('" + TarikhKeputusan + "','dd/MM/yyyy')";
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_keputusanmohon", idKeputusanHohon);
			r.add("id_kemaskini", idUser);
			r.add("tarikh_keputusan", r.unquote(TK));
			r.add("no_rujukan_keputusan", NoRujukPTD);
			r.add("status", PilihKeputusan);
			r.add("flagNotifikasi", flagNotifikasi);
			r.add("ulasan", Catatan);
			r.add("tarikh_kemaskini", r.unquote(TBF));
				  
			if ("update".equals(mode)) {
				r.update("id_keputusanmohon",  String.valueOf(data.get("idkeputusanmohon")));
				r.update("id_permohonan", idPermohonan);
				sql = r.getSQLUpdate("TBLHTPKEPUTUSANMOHON");
			
			} else {
				r.add("id_masuk", idUser);
				r.add("tarikh_masuk", r.unquote(TBF));
				r.add("id_permohonan", idPermohonan);
				sql = r.getSQLInsert("TBLHTPKEPUTUSANMOHON");
			
			}
			//mylog.debug(sql);
			stmt.executeUpdate(sql);
		    
		} finally {
			if (db != null) db.close();	      
		}	
		
	}



/**
 * 	page simpanNotis5A
 */
	public static void Notis5A(Hashtable<String, String> data,String mode,String idNotis5a) throws Exception{
		
		Db db = null;
	    String sql = "";
	    try
	    {
	    	
	    	String idUser = data.get("idUser").toString();
	    	String idPermohonan = data.get("idpermohonan").toString();
			String NoKPPemilikAsal = data.get("NoKPPemilikAsal").toString();
			String TarikhNotis5A = data.get("TarikhNotis5A").toString();
			String TarikhLuputPertama = data.get("TarikhLuputPertama").toString();
			String CukaiTahunPertama = data.get("CukaiTahunPertama").toString();
			String BayaranNotis = (String)data.get("BayaranNotis").toString();
			String BayaranNotisF = (String)data.get("BayaranNotisF").toString();
			String Premium = (String)data.get("Premium");
			String RayuanPremium = (String)data.get("RayuanPremium");
			String RayuanLain = data.get("RayuanLain").toString();
			String PerihalRayuan = (String)data.get("PerihalRayuan");
			String TarikhTerimaNotis5A = data.get("TarikhTerimaNotis5A").toString();
			String TarikhLuputNotisKedua = (String)data.get("TarikhLuputNotisKedua");
			String TarikhLuputNotisKetiga = (String)data.get("TarikhLuputNotisKetiga");
			String PendaftaranHakmilik = data.get("PendaftaranHakmilik").toString();
			String BayarUkur = (String)data.get("BayarUkur");
			String BayaranPerenggan = (String)data.get("BayaranPerenggan");
			String TandaSempadan = (String)data.get("TandaSempadan");
			String BayaranLain = (String)data.get("BayaranLain");
			String JumBayaran = (String)data.get("JumBayaran");
			//Azam add
			String BayaranLain2 = (String)data.get("BayaranLain2");
			String BayaranLain3 = (String)data.get("BayaranLain3");
			String BayaranFail = (String)data.get("BayaranFail");
			//Syaz Add
			String TarikhRayuan = (String)data.get("TarikhRayuan");
			String TempohRayuan = (String)data.get("TempohRayuan");

			  Date now = new Date();
		      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
		      String TN5A = "to_date('" + TarikhNotis5A + "','dd/MM/yyyy')";
		      String TL1 = "to_date('" + TarikhLuputPertama + "','dd/MM/yyyy')";
		      String TTN5A = "to_date('" + TarikhTerimaNotis5A + "','dd/MM/yyyy')";
		      String TL2 = "to_date('" + TarikhLuputNotisKedua + "','dd/MM/yyyy')";
		      String TL3 = "to_date('" + TarikhLuputNotisKetiga + "','dd/MM/yyyy')";
		      String TR = "to_date('" + TarikhRayuan + "','dd/MM/yyyy')";
		      
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  
			//syah
				//x tarik field tarikh rayuan n tempoh
			  
			  r.add("id_kemaskini", idUser);
			  
			  r.add("Tarikh_kemaskini", r.unquote(TBF));
			  r.add("id_permohonan", idPermohonan);
			  r.add("no_rujukan_mop", NoKPPemilikAsal);
			  r.add("Tarikh_Notis5A",  r.unquote(TN5A));
			  r.add("tarikh_luput1",  r.unquote(TL1));
			  r.add("kadar_cukai", CukaiTahunPertama);
			  r.add("bayar_notis", BayaranNotis);
			  r.add("bayaran_notisf", BayaranNotisF);
			  r.add("bayar_premium", Premium);
			  r.add("Rayuan_Premium", RayuanPremium);
			  r.add("Rayuan_Lain",RayuanLain);
			  r.add("Perihal_Rayuan",PerihalRayuan);
			  r.add("Tarikh_Terima",  r.unquote(TTN5A));
			  r.add("tarikh_luput2",  r.unquote(TL2));
			  r.add("tarikh_luput3",  r.unquote(TL3));
			  r.add("bayar_daftar_hakmilik", PendaftaranHakmilik);
			  r.add("Bayar_Ukur", BayarUkur);
			  r.add("Bayar_Perenggan", BayaranPerenggan);
			  r.add("bayar_Sempadan", TandaSempadan);
			  r.add("Bayaran_Lain", BayaranLain);
			  r.add("Jumlah_Bayaran", JumBayaran);
			  r.add("Bayaran_Lain2", BayaranLain2);
			  r.add("Bayaran_Lain3", BayaranLain3);
			  r.add("Bayaran_Fail", BayaranFail);
			  r.add("Tarikh_Rayuan",  r.unquote(TR));
			  r.add("Tempoh_Rayuan", TempohRayuan);
			  
			  if ("update".equals(mode)) {
				  r.update("id_notis5a", idNotis5a);
				  sql = r.getSQLUpdate("TBLHTPNOTIS5A");
			  } else {
				    long idNotis5aBaru = DB.getNextID("TBLHTPNOTIS5A_SEQ");
				  myLog.debug("ni insert terima pohon notice");
				  r.add("id_notis5a", idNotis5aBaru);
				  r.add("id_masuk", idUser);
				  r.add("Tarikh_masuk", r.unquote(TBF));
				  sql = r.getSQLInsert("TBLHTPNOTIS5A");
			  }
		    //  myLog.info("Notis5A:sql="+sql);
		      stmt.executeUpdate(sql);
	      

	    } finally {
	      if (db != null) db.close();
	    }	
	}
	/**
	 * 	page updateNotis5A
	 */
		public static void updateNotis5A(Hashtable data) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {

		    	String idUser = data.get("idUser").toString();
		    	String idNotis = data.get("idNotis").toString();
		    	String idPermohonan = data.get("idPermohonan").toString();
				String NoKPPemilikAsal = data.get("NoKPPemilikAsal").toString();
				String TarikhNotis5A = data.get("TarikhNotis5A").toString();
				String TarikhLuputPertama = data.get("TarikhLuputPertama").toString();
				String CukaiTahunPertama = data.get("CukaiTahunPertama").toString();
				String BayaranNotis = (String)data.get("BayaranNotis").toString();
				String Premium = (String)data.get("Premium");
				String RayuanPremium = (String)data.get("RayuanPremium");
				String RayuanLain = data.get("RayuanLain").toString();
				String PerihalRayuan = (String)data.get("PerihalRayuan");
				String TarikhTerimaNotis5A = data.get("TarikhTerimaNotis5A").toString();
				String TarikhLuputNotisKedua = (String)data.get("TarikhLuputNotisKedua");
				String TarikhLuputNotisKetiga = (String)data.get("TarikhLuputNotisKetiga");
				String PendaftaranHakmilik = data.get("PendaftaranHakmilik").toString();
				String BayarUkur = (String)data.get("BayarUkur");
				String BayaranPerenggan = (String)data.get("BayaranPerenggan");
				String TandaSempadan = (String)data.get("TandaSempadan");
				String BayaranLain = (String)data.get("BayaranLain");
				String JumBayaran = (String)data.get("JumBayaran");

				//mylog.debug("simpanNotis5A :: " + data);

				  Date now = new Date();
			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			      String TN5A = "to_date('" + TarikhNotis5A + "','dd/MM/yyyy')";
			      String TL1 = "to_date('" + TarikhLuputPertama + "','dd/MM/yyyy')";
			      String TTN5A = "to_date('" + TarikhTerimaNotis5A + "','dd/MM/yyyy')";
			      String TL2 = "to_date('" + TarikhLuputNotisKedua + "','dd/MM/yyyy')";
			      String TL3 = "to_date('" + TarikhLuputNotisKetiga + "','dd/MM/yyyy')";

				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_notis5a", idNotis);
				  r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				  r.add("Tarikh_masuk", r.unquote(TBF));
				  r.add("Tarikh_kemaskini", r.unquote(TBF));
				  r.add("id_permohonan", idPermohonan);
				  r.add("no_rujukan_mop", NoKPPemilikAsal);
				  r.add("Tarikh_Notis5A",  r.unquote(TN5A));
				  r.add("tarikh_luput1",  r.unquote(TL1));
				  r.add("kadar_cukai", CukaiTahunPertama);
				  r.add("bayar_notis", BayaranNotis);
				  r.add("bayar_premium", Premium);
				  r.add("Rayuan_Premium", RayuanPremium);
				  r.add("Rayuan_Lain",RayuanLain);
				  r.add("Perihal_Rayuan",PerihalRayuan);
				  r.add("Tarikh_Terima",  r.unquote(TTN5A));
				  r.add("tarikh_luput2",  r.unquote(TL2));
				  r.add("tarikh_luput3",  r.unquote(TL3));
				  r.add("bayar_daftar_hakmilik", PendaftaranHakmilik);
				  r.add("Bayar_Ukur", BayarUkur);
				  r.add("Bayar_Perenggan", BayaranPerenggan);
				  r.add("bayar_Sempadan", TandaSempadan);
				  r.add("Bayaran_Lain", BayaranLain);
				  r.add("Jumlah_Bayaran", JumBayaran);
				  
			      sql = r.getSQLUpdate("TBLHTPNOTIS5A");
			      //mylog.debug("TBLHTPNOTIS5A = "+sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}
	
	/**
	 * 	page simpanBuktiNotis5A 
	 */
		public static void simpanBuktiNotis5A(Hashtable data) throws Exception{
			
			Db db = null;
		    String sql = "";
		    try
		    {
		    	long idBayaran = DB.getNextID("TBLHTPBAYARAN_SEQ");
		    	
		    	String idjenisbayaran = "23";
		    	String caraBayaran = "1";
		    	String idUser = data.get("idUser").toString();
		    	String idPermohonan = data.get("idPermohonan").toString();
				String NoBaucer = data.get("NoBaucer").toString();
				String JumBaucer = data.get("JumBaucer").toString();
				String NoResit = (String)data.get("NoResit").toString();
				String TarikhBaucer = data.get("TarikhBaucer").toString();
				String TarikhResit = data.get("TarikhResit").toString();

				//mylog.debug("simpanNotis5A :: " + data);

				  Date now = new Date();
			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			      String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			      String TN5A = "to_date('" + TarikhBaucer + "','dd/MM/yyyy')";
			      String TL1 = "to_date('" + TarikhResit + "','dd/MM/yyyy')";

			      

				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				  r.add("id_jenisbayaran", idjenisbayaran);
				  r.add("id_carabayar", caraBayaran);
				  r.add("Tarikh_masuk", r.unquote(TBF));
				  r.add("Tarikh_kemaskini", r.unquote(TBF));
				  r.add("id_permohonan", idPermohonan);
				  r.add("no_resit", NoResit);
				  r.add("tarikh_baucer",  r.unquote(TN5A));
				  r.add("tarikh_resit",  r.unquote(TL1));
				  r.add("jumlah_bayaran", JumBaucer);
				  r.add("no_baucer", NoBaucer);
			
				  
			      sql = r.getSQLInsert("TBLHTPBAYARAN");
			      //mylog.debug("TBLHTPBAYARAN = "+sql);
			      stmt.executeUpdate(sql);
		      

		    } finally {
		      if (db != null) db.close();
		    }	
		}
		// Kemaskini pada 2010/11/04
		// Fungsi ini - Tidak digunakan
		public static void updateMAT(Hashtable data) throws Exception{			
			Db db = null;
		    String sql = "";
		    try {
		    	String idUser = (String)data.get("idUser");
		    	String idhakmilikurusan = (String)data.get("idhakmilikurusan");
		    	String idPermohonan = (String)data.get("idpermohonan");
				String id_negeri = (String)data.get("socNegeri");
				String id_daerah = (String)data.get("socDaerah");
				String id_mukim = (String)data.get("socMukim");
				String id_lot = (String)data.get("socLot");
				String infonolot = (String)data.get("txtNoLot");
				String infonosyit = (String)data.get("noSyit");
				String infoPelan = (String)data.get("noPelan");
				String infoIdLot = (String)data.get("socLot");
				String id_luaslama = (String)data.get("socLuas");
				String Lokasi = (String)data.get("Lokasi");				
				//mylog.debug("updateMAT :: " + data);
				Date now = new Date();
			    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
			    String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";

			    db = new Db();
				Statement stmt = db.getStatement();
				sql = "UPDATE TBLHTPHAKMILIKURUSAN SET " +
					"id_negeri='"+id_negeri+"',id_daerah='"+id_daerah+"',id_mukim='"+id_mukim+"'," +
				  	"id_lot='"+id_lot+"',no_lot='"+infonolot+"',no_syit='"+infonosyit+"'," +
				  	"no_pelan='"+infoPelan+"',id_luas='"+id_luaslama+"',lokasi='"+Lokasi+"'," +
				  	"id_kemaskini='"+idUser+"',tarikh_kemaskini=sysdate "+
				  	"WHERE id_hakmilikurusan='"+idhakmilikurusan+"'";
				  
				  /*
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_hakmilikurusan", idhakmilikurusan);
				  r.add("id_masuk", idUser);
				  r.add("id_kemaskini", idUser);
				  r.add("id_permohonan", idPermohonan);
				  r.add("id_negeri", infoNegeri);
				  r.add("id_daerah", infoDaerah);
				  r.add("id_mukim",  infoMukim);
				  r.add("id_lot", infoLot);
				  r.add("no_lot", infonolot);
				  r.add("no_syit", infonosyit);
				  r.add("no_pelan", infoPelan);
				  r.add("id_luas",infoIdLot);
				  r.add("luas",infoLuas);
				  r.add("lokasi",  infoLokasi);
				  r.add("tarikh_kemaskini",  r.unquote(TBF));
				 
			      sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
			      */
			      
			      //mylog.debug(sql);
			      stmt.executeUpdate(sql);
		      
		    } finally {
		      if (db != null) db.close();
		      
		    }	
		}



private static void StatusChange_Action(long idPermohonan, int idSuburusan) {

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
	      //mylog.debug("FrmGadaianSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
	      stmt.executeUpdate(sql);
	  }catch(Exception ex){
		  myLog.debug("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
	  }finally{
		  if (db != null) db.close();
	  }		  
}

	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
			
	}
	
	public static Vector listEmel = null;
	
	public static Vector getListEmel() {
		return listEmel;
	}
	/*--------------------get detail user - senarai pengarah-------------------*/
	@SuppressWarnings("unchecked")//email
	public static Vector setListEmel(String id_ppengarah,String idNegeri) throws Exception {
	
		Vector listEmel = new Vector();
		
		Db db = null;
		String sql = "";
		if(idNegeri.equals("13")){/*13-sarawak*/
		sql =  "SELECT U.USER_ID AS USER_ID,U.USER_ROLE, U.USER_NAME AS USER_NAME,UI.ID_NEGERI, UI.EMEL AS EMEL "+
				" FROM USERS U, USERS_INTERNAL UI"+
				" WHERE U.USER_ID= UI.USER_ID "+
				" AND U.USER_ROLE = '(HTP)PengarahNegeriSarawak'"+
				" AND UI.ID_NEGERI = '"+idNegeri+"'";
		}
		if(idNegeri.equals("12")){/*12-sabah*/
		sql =  "SELECT U.USER_ID AS USER_ID,U.USER_ROLE, U.USER_NAME AS USER_NAME,UI.ID_NEGERI, UI.EMEL AS EMEL "+
				" FROM USERS U, USERS_INTERNAL UI"+
				" WHERE U.USER_ID= UI.USER_ID "+
				" AND U.USER_ROLE = '(HTP)PengarahNegeriSS'"+
				" AND UI.ID_NEGERI = '"+idNegeri+"'";
		}
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h = null;
				while (rs.next()) {
					h = new Hashtable();
					
					h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
					h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
					h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
					h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
					listEmel.addElement(h);
					
			}
				
				System.out.println("SQL LIST email "+listEmel);
			return listEmel;
		}catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			} finally {
			if(db != null)db.close();
		}
	}
	

	/*--------------------EMAIL CHECKING-------------------*/
	@SuppressWarnings("unchecked")
	Vector checkEmail = null;

	@SuppressWarnings("unchecked")
		public Vector checkEmail(String userId) throws Exception {
			
			checkEmail = new Vector();
			checkEmail.clear();
			
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL"; 
		
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
					checkEmail.addElement(h);
				}
				return checkEmail;


			} finally {
				if (db != null)	db.close();
			}
		}

}
