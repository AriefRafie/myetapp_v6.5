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
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.utils.UniqueStringId;

public class FrmSenaraiFailPajakanKecilData {

	static Logger mylog = Logger.getLogger(ekptg.model.htp.FrmSenaraiFailPajakanKecilData.class);
	public static void updateHakmilik(HakmilikUrusan urusan){
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.update("ID_HAKMILIKURUSAN", urusan.getIdhakmilikurusan());
		    r.add("ID_NEGERI",urusan.getIdNegeri());
		    r.add("ID_DAERAH",urusan.getIdDaerah());
		    r.add("ID_MUKIM",urusan.getIdMukim());
		    r.add("ID_JENISHAKMILIK",urusan.getIdHakmilik());
		    r.add("ID_LOT",urusan.getIdLot());
		    r.add("NO_LOT",urusan.getNolot());
		    r.add("NO_HAKMILIK",urusan.getNohakmilik());
		    r.add("ID_KEMASKINI",urusan.getIdKemaskini());
		    r.add("NO_BANGUNAN",urusan.getNoBangunan());
		    r.add("NO_TINGKAT",urusan.getNoTingkat());
		    r.add("NO_PETAK",urusan.getNoPetak());
		    
		    sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
		    stmt.executeUpdate(sql);
		    
	    }catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
	    
	    finally {
		      if (db != null) db.close();
		      
		}
	}
	
	public static void updatePemilik(PihakBerkepentingan pihak){
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("ID_PIHAKBERKEPENTINGAN",pihak.getIdpihakberkepentingan());
		    r.add("NO_RUJUKAN",pihak.getNoRujukan());
		    r.add("NAMA",pihak.getNama());
		    r.add("ALAMAT1",pihak.getAlamat1());
		    r.add("ALAMAT2",pihak.getAlamat2());
		    r.add("ALAMAT3",pihak.getAlamat3());
		    r.add("POSKOD",pihak.getPoskod());
		    r.add("ID_DAERAH",pihak.getIdDaerah());
		    r.add("ID_NEGERI",pihak.getIdNegeri());
		    r.add("NO_TEL",pihak.getTel());
		    sql = r.getSQLUpdate("TBLHTPPIHAKBERKEPENTINGAN");
		    stmt.executeUpdate(sql);
		    
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
		      if (db != null) db.close();
		}
	}
	public static PihakBerkepentingan getPemilik(String idPihakBerkepentingan){
		Db db = null;
	    String sql = "";
	    PihakBerkepentingan pihak = null;
	    try{
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.add("ID_PIHAKBERKEPENTINGAN");
		    r.add("NO_RUJUKAN");
		    r.add("NAMA");
		    r.add("ID_PIHAKBERKEPENTINGAN");
		    r.add("ALAMAT1");
		    r.add("ALAMAT2");
		    r.add("ALAMAT3");
		    r.add("POSKOD");
		    r.add("ID_DAERAH");
		    r.add("ID_NEGERI");
		    r.add("NO_TEL");
		    r.add("ID_PIHAKBERKEPENTINGAN",idPihakBerkepentingan);
		    sql = r.getSQLSelect("TBLHTPPIHAKBERKEPENTINGAN");
		    System.out.println("PihakBerkepentingan" +sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	pihak = new PihakBerkepentingan();
		    	pihak.setNama(rs.getString("NAMA"));
		    	pihak.setNoRujukan(rs.getString("NO_RUJUKAN"));
		    	pihak.setIdpihakberkepentingan(rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	pihak.setAlamat1(rs.getString("ALAMAT1"));
		    	pihak.setAlamat2(rs.getString("ALAMAT2"));
		    	pihak.setAlamat3(rs.getString("ALAMAT3"));
		    	pihak.setPoskod(rs.getString("POSKOD"));
		    	pihak.setIdDaerah(rs.getString("ID_DAERAH"));
		    	pihak.setIdNegeri(rs.getString("ID_NEGERI"));
		    	pihak.setTel(rs.getString("NO_TEL"));
		    }
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
		      if (db != null) db.close();
		}
	    return pihak;
	}
	public static Vector<Hashtable<String, String>> getListLama(String search)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		    	//System.out.println("Fail search in Model::" + search);
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("p.id_Fail");
		      r.add("f.no_Fail");
		      r.add("f.tajuk_Fail");
		      r.add("s.keterangan");
		      r.add("p.tujuan");
		      r.add("RN.nama_negeri");

		      r.add("p.id_Fail",r.unquote("f.id_Fail"));
		      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
		      r.add("SF.id_Fail",r.unquote("f.id_Fail"));
		      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
		      r.add("us.id_Status",r.unquote("s.id_Status"));
		      r.add("RN.id_negeri",r.unquote("f.id_negeri"));
	      
		      r.add("f.id_Urusan",r.unquote("309"));
		      r.add("f.ID_STATUS","999","<>");
		      r.add("sf.id_Suburusanstatus","30","<>");		      
		      r.add("sf.aktif","1");

		      r.add("f.no_Fail","%"+search+"%","like");
		      sql = r.getSQLSelect("TBLRUJNEGERI RN,Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s"," f.id_kemaskini");
			  mylog.info(":getList::sql:::"+sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h = null;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", rs.getString("id_Fail"));
		    	  h.put("no", rs.getString("no_Fail"));
		    	  h.put("tajuk", rs.getString("tajuk_Fail"));
		    	  h.put("tujuan", rs.getString("tujuan"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("negeri", rs.getString("nama_negeri"));
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

	public static Vector<Hashtable<String, String>> getList(String search)throws Exception {
	    
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("F.ID_FAIL");
	      r.add("F.NO_FAIL");
	      r.add("F.TAJUK_FAIL");
	      r.add("S.KETERANGAN");
	      r.add("RN.NAMA_NEGERI");

	      r.add("F.ID_STATUS",r.unquote("S.ID_STATUS"));
	      r.add("F.ID_NEGERI",r.unquote("RN.ID_NEGERI"));

	      r.add("F.ID_URUSAN","309");
	      //r.add("F.ID_STATUS","999","<>");
	      if (!search.equals("")){
	    	  r.add("F.no_Fail","%"+search+"%","like");
	      }
	      sql = r.getSQLSelect("TBLPFDFAIL F,TBLRUJSTATUS S,TBLRUJNEGERI RN");
	      sql += " AND (F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) ORDER BY F.ID_KEMASKINI";
		  mylog.info(":getList("+search+")::sql:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h = null;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", Utils.isNull(rs.getString("ID_FAIL")));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  //h.put("tujuan", rs.getString("tujuan"));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
	    	  list.addElement(h);

	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }	
	
	public static Vector<Hashtable<String, String>> getList(String noFail,String tajuk,String idKementerian,
			String idNegeri, String idAgensi,String idSuburusanStatus)
		throws Exception {
	    
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("DISTINCT(F.ID_FAIL)");
	      r.add("F.NO_FAIL");
	      r.add("UPPER(F.TAJUK_FAIL) TAJUK_FAIL");
	      r.add("F.TARIKH_MASUK");
	      r.add("F.TARIKH_KEMASKINI");
	      r.add("S.KETERANGAN");
	      r.add("RN.NAMA_NEGERI");

	      r.add("F.ID_STATUS",r.unquote("S.ID_STATUS"));
	      r.add("F.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
	      r.add("F.ID_URUSAN","309");
	      //r.add("F.ID_STATUS","999","<>");

	      r.add("p.id_Fail",r.unquote("f.id_Fail"));	      
	      r.add("p.id_Permohonan",r.unquote("PP.id_Permohonan"));
	      r.add("p.id_Fail",r.unquote("RSUSF.id_Fail"));	      
	      r.add("p.id_Permohonan",r.unquote("RSUSF.id_Permohonan"));
	      r.add("RSUSF.ID_SUBURUSANSTATUS",r.unquote("RSUS.ID_SUBURUSANSTATUS"));
	      //r.add("RSUS.ID_SUBURUSANSTATUS","30","<>");
	      
	      if (idNegeri != "20"){
		      if (!idNegeri.equals("")){
		    	  //r.add("f.id_negeri","%"+idNegeri+"%","=");
		    	  r.add("f.id_negeri",idNegeri);
		      }
	      }
	      if (idKementerian != null){
		      if (!idKementerian.equals("")){
		    	  r.add("F.ID_KEMENTERIAN",idKementerian);
		      }
	      }
	      if (idAgensi != null){
	    	  if (!idAgensi.equals("")){
	    		  r.add("PP.ID_AGENSI",idAgensi);
		      }
	      }
	      if (!tajuk.equals("")){
			  r.add("f.tajuk_Fail","%"+tajuk+"%","like");
		  }
	      r.add("F.NO_FAIL","%"+noFail+"%","like");
	      //r.add("RSUS.AKTIF", 1);
	      sql = r.getSQLSelect("TBLPFDFAIL F,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLRUJSTATUS S,TBLRUJNEGERI RN" +
	      		",TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF");
	      sql +=" AND RSUS.AKTIF = '1' AND (F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) ORDER BY F.TARIKH_MASUK,F.TARIKH_KEMASKINI DESC";
		  mylog.info(":getList::sql test senarai fail:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h = null;
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", Utils.isNull(rs.getString("ID_FAIL")));
	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
	    	  //h.put("tujuan", rs.getString("tujuan"));
	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
	    	  list.addElement(h);

	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }	
	
//	public static Vector<Hashtable<String, String>> getListOnline(String search,String idKementerian)throws Exception {
//	    
//		Db db = null;
//	    String sql = "";
//	    try {
//	      db = new Db();
//	      Statement stmt = db.getStatement();
//	      SQLRenderer r = new SQLRenderer();
//
//	      r.add("F.ID_FAIL");
//	      r.add("F.NO_FAIL");
//	      r.add("F.TAJUK_FAIL");
//	      r.add("S.KETERANGAN");
//	      r.add("RN.NAMA_NEGERI");
//
//	      r.add("F.ID_STATUS",r.unquote("S.ID_STATUS"));
//	      r.add("F.ID_NEGERI",r.unquote("RN.ID_NEGERI"));
//
//	      r.add("F.ID_URUSAN","309");
//	      r.add("F.ID_STATUS","999","<>");
//
//	      r.add("F.no_Fail","%"+search+"%","like");
//	      sql = r.getSQLSelect("TBLPFDFAIL F,TBLRUJSTATUS S,TBLRUJNEGERI RN"," F.ID_KEMASKINI");
//		  mylog.info(":getList("+search+")::sql:::"+sql);
//
//	      ResultSet rs = stmt.executeQuery(sql);
//	      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
//	      Hashtable<String, String> h = null;
//
//	      while (rs.next()) {
//	    	  h = new Hashtable<String, String>();
//	    	  h.put("id", Utils.isNull(rs.getString("ID_FAIL")));
//	    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
//	    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
//	    	  //h.put("tujuan", rs.getString("tujuan"));
//	    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
//	    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
//	    	  list.addElement(h);
//
//	      }
//	      return list;
//	    } finally {
//	      if (db != null) db.close();
//	    }
//	  }		
	 public static Vector<Hashtable<String, String>> getListLama(String search,String idMasuk)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("P.id_Fail");
	      r.add("F.no_Fail");
	      r.add("F.tajuk_Fail");
	      r.add("S.keterangan");
	      r.add("RN.nama_Negeri");
	      r.add("P.id_Permohonan");
	      
	      r.add("P.id_Fail",r.unquote("F.id_Fail"));
	      r.add("P.id_Fail",r.unquote("SF.id_Fail"));
	      r.add("P.id_Permohonan",r.unquote("SF.id_Permohonan"));
	      r.add("SF.id_Suburusanstatus",r.unquote("US.id_Suburusanstatus"));
	      r.add("US.id_Status",r.unquote("S.id_Status"));
	      r.add("RN.id_Negeri",r.unquote("F.id_Negeri"));

	      r.add("F.id_Urusan","309");
	      r.add("SF.aktif","1");
	      r.add("F.ID_STATUS","999","<>");
	      //r.add("SF.ID_SUBURUSANSTATUS","30","<>");
	      //r.add("ROWNUM","1","<=");
	      r.add("F.id_Masuk",idMasuk);

	      r.add("F.no_Fail","%"+search+"%","like");
	      sql = r.getSQLSelect("Tblpermohonan P, Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblrujsuburusanstatus US,Tblrujstatus S,Tblrujnegeri RN"," f.id_kemaskini");
		  mylog.info(":getList("+search+","+ idMasuk+")::sql:::"+sql);

	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
	      Hashtable<String, String> h = null;

	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  //h.put("tujuan", rs.getString("tujuan"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("idpermohonan", rs.getString("id_Permohonan"));
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	 public static Vector<Hashtable<String, String>> getList(String search,String idMasuk)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("P.id_Fail");
		      r.add("F.no_Fail");
		      r.add("F.tajuk_Fail");
		      r.add("S.keterangan");
		      r.add("RN.nama_Negeri");
		      r.add("P.id_Permohonan");
		      
		      r.add("P.id_Fail",r.unquote("F.id_Fail"));
		      r.add("P.id_Fail",r.unquote("SF.id_Fail"));
		      r.add("P.id_Permohonan",r.unquote("SF.id_Permohonan"));
		      r.add("SF.id_Suburusanstatus",r.unquote("US.id_Suburusanstatus"));
		      r.add("US.id_Status",r.unquote("S.id_Status"));
		      r.add("RN.id_Negeri",r.unquote("F.id_Negeri"));

		      r.add("F.id_Urusan","309");
		      r.add("SF.aktif","1");
		      r.add("F.ID_STATUS","999","<>");
		      //r.add("SF.ID_SUBURUSANSTATUS","30","<>");
		      //r.add("ROWNUM","1","<=");
		      r.add("F.id_Masuk",idMasuk);

		      r.add("F.no_Fail","%"+search+"%","like");
		      sql = r.getSQLSelect("Tblpermohonan P, Tblpfdfail F,Tblrujsuburusanstatusfail SF,Tblrujsuburusanstatus US,Tblrujstatus S,Tblrujnegeri RN"," f.id_kemaskini");
			  mylog.info(":getList("+search+","+ idMasuk+")::sql:::"+sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h = null;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", rs.getString("id_Fail"));
		    	  h.put("no", rs.getString("no_Fail"));
		    	  h.put("tajuk", rs.getString("tajuk_Fail"));
		    	  //h.put("tujuan", rs.getString("tujuan"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("negeri", rs.getString("nama_Negeri"));
		    	  h.put("idpermohonan", rs.getString("id_Permohonan"));
		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector<Hashtable<String, String>> getList(String search,String idMasuk,String tajukFail)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("DISTINCT(F.ID_FAIL)");
		      r.add("F.NO_FAIL");
		      r.add("F.TAJUK_FAIL");
		      r.add("S.KETERANGAN");
		      r.add("RN.NAMA_NEGERI");
		      r.add("F.TARIKH_KEMASKINI");
		      r.add("P.ID_PERMOHONAN");
		      
		      r.add("P.id_Fail",r.unquote("F.id_Fail"));
		      r.add("P.id_Fail",r.unquote("SF.id_Fail"));
		      r.add("P.id_Permohonan",r.unquote("SF.id_Permohonan"));
		      r.add("SF.id_Suburusanstatus",r.unquote("US.id_Suburusanstatus"));
		      r.add("F.ID_STATUS",r.unquote("S.ID_STATUS"));
		      r.add("F.ID_NEGERI",r.unquote("RN.ID_NEGERI"));

		      r.add("F.ID_URUSAN","309");
		      r.add("SF.aktif","1");
		      r.add("F.ID_STATUS","999","<>");
		      //r.add("SF.ID_SUBURUSANSTATUS","30","<>");
		      //r.add("ROWNUM","1","<=");
		      if(!idMasuk.equals(""))
		    	  r.add("F.ID_MASUK",idMasuk);
		      if(!search.equals(""))
		    	  r.add("F.no_Fail","%"+search+"%","like");
		      if(!tajukFail.equals(""))
		    	  r.add("F.TAJUK_FAIL","%"+tajukFail.toUpperCase()+"%","like");
		      
		      sql = r.getSQLSelect("TBLPFDFAIL F,Tblpermohonan P,Tblrujsuburusanstatusfail SF,Tblrujsuburusanstatus US,TBLRUJSTATUS S,TBLRUJNEGERI RN"," F.TARIKH_KEMASKINI DESC");
			  mylog.info(":getList("+search+","+ idMasuk+")::sql:::"+sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h = null;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", Utils.isNull(rs.getString("ID_FAIL")));
		    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
		    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
		    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
		    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
		    	  h.put("idPermohonan", Utils.isNull(rs.getString("ID_PERMOHONAN")));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Vector<Hashtable<String, String>> getListOnline(String search,String idMasuk,String idKem)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("DISTINCT(F.ID_FAIL)");
		      r.add("F.NO_FAIL");
		      r.add("F.TAJUK_FAIL");
		      r.add("S.KETERANGAN");
		      r.add("RN.NAMA_NEGERI");
		      r.add("F.TARIKH_KEMASKINI");
		      r.add("P.no_Permohonan");
		      
		      
		      r.add("P.id_Fail",r.unquote("F.id_Fail"));
		      r.add("P.id_Fail",r.unquote("SF.id_Fail"));
		      r.add("P.id_Permohonan",r.unquote("SF.id_Permohonan"));
//		      r.add("SF.id_Suburusanstatus",r.unquote("US.id_Suburusanstatus"));
//		      r.add("US.ID_STATUS",r.unquote("S.ID_STATUS"));
		      r.add("US.id_Suburusanstatus",r.unquote("US.id_Suburusanstatus"));
		      r.add("F.ID_STATUS",r.unquote("S.ID_STATUS"));
		      r.add("F.ID_NEGERI",r.unquote("RN.ID_NEGERI"));

		      r.add("F.ID_URUSAN","309");
		      r.add("F.ID_KEMENTERIAN",idKem);
		      r.add("SF.aktif","1");
		      r.add("F.ID_STATUS","999","<>");
		      //r.add("SF.ID_SUBURUSANSTATUS","30","<>");
		      //r.add("ROWNUM","1","<=");
		      r.add("F.ID_MASUK",idMasuk);
		      r.add("P.no_Permohonan","%"+search+"%","like");
		      sql = r.getSQLSelect("TBLPFDFAIL F,Tblpermohonan P,Tblrujsuburusanstatusfail SF,Tblrujsuburusanstatus US,TBLRUJSTATUS S,TBLRUJNEGERI RN"," F.TARIKH_KEMASKINI DESC");
			  mylog.info(":getList("+search+","+ idMasuk+")::sql:::"+sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h = null;

		      while (rs.next()) {


		    	  h = new Hashtable<String, String>();

		    	  h.put("id", Utils.isNull(rs.getString("ID_FAIL")));
		    	  h.put("no", Utils.isNull(rs.getString("NO_FAIL")));
		    	  h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
		    	  h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
		    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI")));
		    	  h.put("noP", Utils.isNull(rs.getString("no_Permohonan")));
		    	  h.put("status", Utils.isNull(rs.getString("KETERANGAN")));

		    	  list.addElement(h);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }


	 
	 public static Vector<Hashtable<String, String>> getListSearch(String noFail,String namaFail,String idNegeri,String idStatus)throws Exception {
		    
		 Db db = null;
		 String sql = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("p.id_Fail");
		      r.add("f.no_Fail");
		      r.add("f.tajuk_Fail");
		      r.add("s.keterangan");
		      r.add("u.user_name");

		      r.add("p.id_Fail",r.unquote("f.id_Fail"));
		      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
		      r.add("SF.id_Fail",r.unquote("f.id_Fail"));
		      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
		      r.add("us.id_Status",r.unquote("s.id_Status"));
		      r.add("f.id_Masuk",r.unquote("u.user_Id"));
		      r.add("f.id_Urusan","309");
		      r.add("sf.aktif","1");
		      r.add("f.no_Fail","%"+noFail+"%","like");
		      r.add("f.tajuk_Fail","%"+namaFail+"%","like");
		      if (idNegeri != "20"){
		    	  r.add("f.id_negeri","%"+idNegeri+"%","like");
		      }
		      r.add("sf.id_Suburusanstatus","%"+idStatus+"%","like");
		      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s,Users u");

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h = null;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", rs.getString("id_Fail"));
		    	  h.put("no", rs.getString("no_Fail"));
		    	  h.put("tajuk", rs.getString("tajuk_Fail"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("namapembuat", rs.getString("user_name"));
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
	 
	 public static Hashtable<String, ?> getFailInfo(String idfail)throws Exception {
		 Db db = null;
		 String sql = "";
		 //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("b.id_Negeri");
		      r.add("c.id_Seksyen");
		      r.add("d.id_Urusan");
		      r.add("e.id_Suburusan");
		      r.add("f.id_Tarafkeselamatan");
		      r.add("a.tajuk_Fail");
		      r.add("a.id_Status");
		      r.add("h.id_Lokasifail");
		      r.add("i.id_Faharasat");
		      r.add("to_char(a.tarikh_Daftar_Fail,'dd/mm/yyyy') as tarikh_Daftar_Fail");
		      r.add("a.id_Kementerian");
		      
		      r.add("a.id_Negeri",r.unquote("b.id_Negeri"));
		      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen"));
		      r.add("a.id_Urusan",r.unquote("d.id_Urusan"));
		      r.add("a.id_Suburusan",r.unquote("e.id_Suburusan(+)"));
		      r.add("a.id_Tarafkeselamatan",r.unquote("f.id_Tarafkeselamatan(+)"));
		      r.add("a.id_Status",r.unquote("g.id_Status"));
		      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
		      r.add("a.id_Faharasat",r.unquote("i.id_Faharasat(+)"));
		      
		      r.add("a.id_Fail",idfail);
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujnegeri b, Tblrujseksyen c, Tblrujurusan d, Tblrujsuburusan e, Tblpfdrujtarafkeselamatan f, Tblrujstatus g, Tblpfdrujlokasifail h, Tblpfdrujfaharasat i");
			  //System.out.println("FrmSenaraiFailPajakanKecilData:getFailInfo::sql:::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idFail", rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  h.put("idNegeri",rs.getString("id_Negeri")== null?0:rs.getString("id_Negeri"));
		    	  h.put("idSeksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("idUrusan",rs.getString("id_Urusan")== null?0:rs.getString("id_Urusan"));
		    	  h.put("idSuburusan",rs.getString("id_Suburusan")== null?0:rs.getString("id_Suburusan"));
		    	  h.put("idTarafkeselamatan",rs.getString("id_Tarafkeselamatan")== null?0:rs.getString("id_Tarafkeselamatan"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("idStatus",rs.getString("id_Status")== null?0:rs.getString("id_Status"));
		    	  h.put("idLokasifail", rs.getString("id_Lokasifail")== null?0:rs.getString("id_Lokasifail"));
		    	  h.put("idFaharasat", rs.getString("id_Faharasat")== null?0:rs.getString("id_Faharasat"));
		    	  h.put("tarikhDaftar",rs.getString("tarikh_Daftar_Fail"));
		    	   //h.put("tarikhDaftar",lebah.util.Util.getDateTime(rs.getDate("tarikh_Daftar_Fail"), "dd/MM/yyyy"));
		    	  h.put("idKementerian",rs.getString("id_Kementerian"));
		    	  //  list.addElement(h);
		      }
		      return h;
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		  }

	  public static void add(String kod_cara_bayar, String keterangan, Long id_masuk,
			  Date tarikh_masuk) throws Exception {
	    Db db = null;
	    String sql = "";
	    if(tarikh_masuk==null){
	    	tarikh_masuk = new Date(); }
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("id_carabayar", UniqueStringId.get());
	      r.add("kod_cara_bayar", kod_cara_bayar);
	      r.add("keterangan", keterangan);
	      r.add("id_masuk", id_masuk);
	      r.add("tarikh_masuk", tarikh_masuk);
	      r.add("id_kemaskini", id_masuk);
	      r.add("tarikh_kemaskini", tarikh_masuk);
	      sql = r.getSQLInsert("tblrujcarabayar");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	  public static void update(Long id_carabayar, String kod_cara_bayar,
			  String keterangan, Long id_kemaskini, Date tarikhKemaskini) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.clear();

	      r.update("id_carabayar", ""+id_carabayar);
	      r.add("kod_cara_bayar", kod_cara_bayar);
	      r.add("keterangan", keterangan);
	      r.add("id_kemaskini", id_kemaskini);
	      r.add("tarikh_kemaskini", tarikhKemaskini);
	      sql = r.getSQLUpdate("tblrujcarabayar");
	      stmt.executeUpdate(sql);

	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String id_carabayar)
	    throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      //boolean found = false;
	      //sql = "select id_carabayar from faculty_subject where faculty_id = '" + id_carabayar + "'";
	      //ResultSet rs = stmt.executeQuery(sql);
	      //if (rs.next()) found = true;
	      //if (found)
	      sql = "delete from tblrujcarabayar where id_carabayar = " + id_carabayar;
	      stmt.executeUpdate(sql);
	    }
	    finally
	    {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector<Hashtable<String, String>> getFileCount(int idnegeri, int idurusan)throws Exception {
		    Db db = null;
		    String sql = "";
		    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
			    System.out.println("FrmPajakanKecil::getFileCount 1");

		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	      
		      r.add("a.id_Fail");
		      /*r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("b.nama_Negeri");
		      r.add("c.kod_Seksyen");
		      r.add("d.keterangan");
		      r.add("e.keterangan as status");
		      */
		      r.add("a.id_Negeri",idnegeri);
		      r.add("a.id_Urusan",idurusan);
		      //r.add("a.id_Status",r.unquote("d.id_Status"));
		      //r.add("a.id_Fail",r.unquote("f.id_Fail(+)"));
		      //r.add("f.id_Status",r.unquote("e.id_Status(+)"));
		     
		
		      sql = r.getSQLSelect("Tblpfdfail a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable<String, String> h = null;
		      //int bil = 1;
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  //h.put("bil", bil);
		    	  h.put("id_Fail",rs.getString("id_Fail"));
		    	  /*h.put("no_Fail", rs.getString("no_Fail"));
		    	  h.put("tajuk_Fail", rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("tarikh_Daftar_Fail",sdf.format(rs.getDate("tarikh_Daftar_Fail"))== null? "":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("nama_Negeri", rs.getString("nama_Negeri"));
		    	  h.put("kod_Seksyen",rs.getString("kod_Seksyen"));
		    	  h.put("keterangan1", rs.getString("keterangan"));
		    	  h.put("keterangan2", rs.getString("status")== null? "":rs.getString("status"));
		    	  */
		    	  list.addElement(h);
		    	  //bil++;
		    	  
		    	  
		      }
			    System.out.println("FrmPajakanKecil::getFileCount 2");

		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	  
		public static String getNegeriByMampu(int idnegeri) throws Exception {
		    Db db = null;
		    //String list = "";
		    String sql = "Select id_negeri,kod_negeri,nama_negeri,kod_mampu" +
		    		" from tblrujnegeri where id_negeri="+idnegeri;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Tblrujnegeri f = null;
			    //System.out.println("FrmPajakanKecil::getNegeriByMampu 1");
			    while (rs.next()) {
		    	  f = new Tblrujnegeri();
		    	  f.setKodMampu(rs.getString("kod_mampu"));
		      }
			   //System.out.println("FrmPajakanKecil::getNegeriByMampu 2"+f.getKodMampu());
		      return f.getKodMampu();
		    } finally {
		      if (db != null) db.close();
		    }
		}

		public static String getKementerianByMampu(int idkementerian) throws Exception {
		    Db db = null;
		    //String list = "";
		    String sql = "Select id_kementerian,kod_kementerian" +
		    		" from tblrujkementerian where id_kementerian="+idkementerian;
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      Tblrujkementerian f = null;
			    while (rs.next()) {
		    	  f = new Tblrujkementerian();
		    	  f.setKodKementerian(rs.getString("kod_kementerian"));
		      }
		      return f.getKodKementerian();
		    } finally {
		      if (db != null) db.close();
		    }
		}
		
		
		 public static void janaFail(Hashtable<?, ?> data) throws Exception{
			 	Db db = null;
			    String sql = "";
			    //Date now = new Date();
			    try{	 
			    	  //long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			    	  long idFail = (Long)data.get("id_Fail");
			    	  String noFailLama = (String)data.get("no_Fail");
				      int negeri = (Integer)data.get("id_Negeri");
				      String seksyen = (String)data.get("id_Seksyen");
				      int urusan = (Integer)data.get("id_Urusan");
				      String suburusan = (String)data.get("id_Suburusan");
				      String taraf = (String)data.get("id_Tarafkeselamatan");
				      String tajukFail = (String)data.get("tajuk_Fail");
				      String status = (String)data.get("id_Status");
				      //String lokasi = (String)data.get("id_Lokasifail");
				      //String faharasat = (String)data.get("id_Faharasat");
				      int flagFail = (Integer)data.get("flag_Fail");
				      int kementerian = (Integer)data.get("id_Kementerian");
				      int idmasuk = (Integer)data.get("id_Masuk");
      
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      /*ID_FAIL              NUMBER CONSTRAINT TBLPFDFAIL_C01 NOT NULL,
				      -KOD_JABATAN          VARCHAR2(10 BYTE),
				      ID_TARAFKESELAMATAN  NUMBER,
				      ID_SEKSYEN           NUMBER,
				      ID_URUSAN            NUMBER,
				      ID_SUBURUSAN         NUMBER,
				      TARIKH_DAFTAR_FAIL   DATE,
				      TAJUK_FAIL           VARCHAR2(300 BYTE),
				      NO_FAIL              VARCHAR2(400 BYTE),
				      -NO_FAIL_ROOT         VARCHAR2(400 BYTE),
				      -LOKASI               VARCHAR2(100 BYTE),
				      ID_NEGERI            NUMBER,
				      ID_KEMENTERIAN       NUMBER,
				      -FAHARASAT            VARCHAR2(100 BYTE),
				      FLAG_FAIL            NUMBER,
				      ID_STATUS            NUMBER,
				      -CATATAN              VARCHAR2(4000 BYTE),
				      ID_MASUK             NUMBER,
				      -TARIKH_MASUK         DATE,
				      -ID_KEMASKINI         NUMBER,
				      -TARIKH_KEMASKINI     DATE, */
				      r.add("id_Fail",r.unquote(""+idFail));
				      //r.add("kod_jabatan","JKPTG");
				      r.add("id_Tarafkeselamatan", r.unquote(taraf));
				      r.add("id_Seksyen", r.unquote(seksyen));
				      r.add("id_Urusan", r.unquote(""+urusan));
				      r.add("id_Suburusan", r.unquote(suburusan));
				      r.add("tarikh_Daftar_Fail",r.unquote("sysdate")); 
				      r.add("tajuk_Fail", tajukFail);
				      r.add("no_Fail", noFailLama);
				      r.add("no_fail_root", noFailLama);
				      r.add("id_Lokasifail", "1");
				      //r.add("lokasi", "TIADA");
				      r.add("id_Negeri", r.unquote(""+negeri));
				      r.add("id_Kementerian", r.unquote(""+kementerian));
				      r.add("id_Faharasat", "1");
				      //r.add("Faharasat", "TIADA");
				      r.add("flag_Fail", flagFail);
				      r.add("id_Status", r.unquote(status));
				      r.add("catatan", "TIADA");
				      r.add("id_Masuk",r.unquote(""+idmasuk));				      
				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
				      r.add("id_Kemaskini",r.unquote(""+idmasuk));				      
				      r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				      
				      sql = r.getSQLInsert("tblpfdfail");
				      mylog.info("FrmSenaraiFailPajakanKecilData:janaFail::sql:::"+sql);
				      stmt.executeUpdate(sql);
				    } finally {
				      if (db != null) db.close();
				    }
				     
				      
		 }
		 
		 public static int kemaskiniFail(Hashtable<?, ?> data) throws Exception {
			 Db db = null;
			 String sql = "";
			 try{
//				  long idFail = (Long)data.get("id_Fail");
				 
				 long idPermohonan = (Long)data.get("idPermohonan");
				 
		    	  /*String noFailLama = (String)data.get("no_Fail");
			      int negeri = (Integer)data.get("id_Negeri");
			      String seksyen = (String)data.get("id_Seksyen");
			      int urusan = (Integer)data.get("id_Urusan");
			      String suburusan = (String)data.get("id_Suburusan");
			      String taraf = (String)data.get("id_Tarafkeselamatan");
			      String tajukFail = (String)data.get("tajuk_Fail");
			      String status = (String)data.get("id_Status");
			      String lokasi = (String)data.get("id_Lokasifail");
			      String faharasat = (String)data.get("id_Faharasat");
			      int flagFail = (Integer)data.get("flag_Fail");
			      int kementerian = (Integer)data.get("id_Kementerian");
			      */int idkemaskini = (Integer)data.get("id_kemaskini");

			      /*int idPermohonan = (Integer)data.get("idPermohonan");
			      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
			      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			      String NoFailLain = (String)data.get("NoFailLain");
				  String TarikhPermohonan = (String)data.get("TarikhPermohonan");
				  String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
				  */
			      
			      
			      String NoFailLain = (String)data.get("NoFailLain");
			      
				  Date now = new Date();
			      SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			      String date_update = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  /*
				  r.update("id_Fail", idFail);
				  //r.add("tarikh_Surat", r.unquote(TSKJP));
				  //r.add("tarikh_Terima", r.unquote(TP));
				  
				  r.add("id_Kemaskini",idkemaskini);
				  r.add("tarikh_Kemaskini",r.unquote(date_update));
			      sql = r.getSQLUpdate("tblpfdfail");
			      System.out.println("FrmPajakanKecilSenaraiFailData:kemaskini()::sql:::"+sql);

			      stmt.executeUpdate(sql);
			      */
			      r = new SQLRenderer();
			      r.update("ID_PERMOHONAN", idPermohonan);
			     // r.update("ID_FAIL", data.get("idFailN").toString());
			      r.add("tujuan",data.get("Tajuk"));
//			      if (!"".equals(data.get("TarikhSurKJP"))){
//						r.add("TARIKH_SURAT", r.unquote("to_date('" + data.get("TarikhSurKJP") + "','dd/MM/yyyy')"));
//			      }		      
			      r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));		      
			      sql = r.getSQLUpdate("TBLPERMOHONAN");
			      mylog.info("FrmPajakanKecilSenaraiFailData:kemaskini()::sql:::"+sql);
			      stmt.executeUpdate(sql);			      
			      
			      r = new SQLRenderer();
			      r.update("ID_PERMOHONAN", idPermohonan);			      
			     // r.add("NO_RUJUKAN_LAIN",NoFailLain);
			      r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));			      		      
			      sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			      mylog.info("FrmPajakanKecilSenaraiFailData:kemaskini()::sql:::"+sql);
			      stmt.executeUpdate(sql);
			      
			      return idkemaskini;
			      
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }
		 
		 public static long kemaskiniPermohonan(Hashtable<?, ?> data) throws Exception {
			 Db db = null;
			 String sql = "";
			 try{
				 
				  long idPermohonan = (Long)data.get("id_Permohonan");
			      //int idPermohonan = (Integer)data.get("idPermohonan");
			      System.out.println("FrmGadaianSemakanData::Update::tajuk = "+idPermohonan);
			      int   id_Fail  = (Integer)data.get("id_fail");
			      System.out.println("FrmGadaianSemakanData::Update::IDfail = "+id_Fail);
			      String tajuk = (String)data.get("tajuk");
			      String TarikhSurKJP = (String)data.get("TarikhSurKJP");
			      System.out.println("FrmGadaianSemakanData::Update::TSKJP = "+TarikhSurKJP);
			      String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			      //String NoFailLain = (String)data.get("NoFailLain");
			      //String TP ="";
			      //if(data.get("TarikhPermohonan") != null) {
			     // String TarikhPermohonan = (String)data.get("TarikhPermohonan");
				 //  TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
				 // }Date now = new Date();
			      //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			      //String date_update = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Permohonan", idPermohonan);
				  //r.update("id_Fail", id_Fail);
				  r.add("tujuan", tajuk);
				  System.out.println("FrmGadaianSemakanData::Update::tajuk = "+tajuk);
				  r.add("tarikh_Surat", r.unquote(TSKJP));
				  //r.add("tarikh_Terima", r.unquote(TP));
				  //r.add("id_Kemaskini",idPermohonan);
				 // r.add("tarikh_Kemaskini",r.unquote(date_update));
			      sql = r.getSQLUpdate("Tblpermohonan");
			      System.out.println("FrmGadaianSemakanData::Update::TBLPERMOHONAN = "+sql);
			      stmt.executeUpdate(sql);
			      /*
			      Statement stmtHP = db.getStatement();
				  SQLRenderer rHP = new SQLRenderer();
				  rHP.update("id_Permohonan", idPermohonan);
				  rHP.add("no_Rujukan_Lain", NoFailLain);
//				  rHP.add("tarikh_Agihan", r.unquote(TBF));
				  rHP.add("id_Kemaskini",idPermohonan);
				  rHP.add("tarikh_Kemaskini",rHP.unquote(date_update));
			      sql = rHP.getSQLUpdate("Tblhtppermohonan");
			      System.out.println("FrmGadaianSemakanData::Update::tarikh_Agihan = "+ TSKJP );
			      System.out.println("FrmGadaianSemakanData::Update::TBLHTPPERMOHONAN = "+sql);
			      stmtHP.executeUpdate(sql);*/
			      return idPermohonan;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }


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
				  System.out.println("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
			  }finally{
				  if (db != null) db.close();
			  }		  
		  }  
		  
			public static String SelectStatusDistinct(String selectName,String selectedValue, String disability,String jsFunction,String idUrusan) throws Exception {
				StringBuffer sb = new StringBuffer("");
				try {
					sb.append("<select name='"+ selectName +"'");
					if (disability != null) sb.append(disability);
					if (jsFunction != null) sb.append(jsFunction);
					sb.append(" > ");
					sb.append("<option value=\"\">SILA PILIH</option>\n");
					Vector<Hashtable<String, String>>  v = getStatusByPermohonan(idUrusan);
					Hashtable<?, ?> f = null;
					String s = "";
					for(int i=0; i< v.size() ; i++) {
						f = (Hashtable<?, ?>)v.get(i);
						if (f.get("idsuburusanstatus").equals(selectedValue)) {
							s = "selected";
						} else {
							s = "";
						}
						sb.append("<option "+s+" value="+ f.get("idsuburusanstatus") +">" + f.get("keterangan")+ "</option>\n");
					}
					sb.append("</select>");
				} catch (Exception ex) {
				      ex.printStackTrace();
				      throw ex;
				}
				
				return sb.toString();	
			}
		  
			 public static Vector<Hashtable<String, String>> getStatusByPermohonan(String idUrusan)throws Exception {
				    Db db = null;
				    String sql = "";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();

				      r.add("distinct(sf.id_Suburusanstatus)");
				      r.add("s.keterangan");

				      r.add("p.id_Fail",r.unquote("f.id_Fail"));
				      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
				      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
				      r.add("us.id_Status",r.unquote("s.id_Status"));

				      r.add("f.id_Urusan",idUrusan);
				      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");

				      ResultSet rs = stmt.executeQuery(sql);
				      Vector list = new Vector();
				      Hashtable h;

				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("idsuburusanstatus", rs.getString("id_Suburusanstatus"));
				    	  h.put("keterangan", rs.getString("keterangan"));
				    	  list.addElement(h);

				      }
				      return list;
				    } finally {
				      if (db != null) db.close();
				    }
				  }
			 
			 public static String nullStringToEmpty( String stringObject )
			 {
			 	if (((Object) stringObject) == null)
			 	{
			 		return "";
			 	}
			 	return stringObject.toString();
			 }

	  
	}
