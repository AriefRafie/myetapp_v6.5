package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblhtphakmilikbangunan;

public class FrmKJPJawatankuasaRuangPejabatData {
	
	static Logger log = Logger.getLogger(FrmKJPJawatankuasaRuangPejabatData.class);

	 public static Vector getList(String fail)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      /*r.add("p.id_Permohonan");
	      r.add("f.no_Fail");
	      r.add("p.tujuan");
	      r.add("s.keterangan");
	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      r.add("us.id_Status",r.unquote("s.id_Status"));

	      r.add("f.id_Urusan","309");
	      r.add("p.id_Fail",fail);
	      r.add("sf.aktif","IN (1,2)",,);
	      sql = r.getSQLSelect("p.id_Permohonanp, f.no_Fail, p.tujuan, s.keterangan" +
	      		"Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
		  */
	      sql= " SELECT p.id_Permohonan, f.no_Fail, p.tujuan, s.keterangan "+
	      	" FROM Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s "+
	      	" WHERE p.id_Fail = f.id_Fail  AND p.id_Permohonan = sf.id_Permohonan  AND "+
	      	" sf.id_Suburusanstatus = us.id_Suburusanstatus AND us.id_Status = s.id_Status  "+
	      	" AND f.id_Urusan = '309'  AND p.id_Fail = "+fail+"  AND sf.aktif IN (1,2)";
		  //System.out.println("FrmPajakanKecilSenaraiPermohonanData:sql::"+sql);
     
	      log.info("SQL getList : " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("id", rs.getString("id_Permohonan"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tujuan"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	 public static Tblhtphakmilikbangunan getHakmilikBangunan(String idpermohonan)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		     Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();
		     //sql= " select h.alamat1,h.alamat2,h.alamat3,h.poskod, "+
		    	//  " h.sewa_bulanan "+
		    	//  " FROM tblhtphakmilikbangunan h where "+
		    	//  " h.id_permohonan = "+idpermohonan;
		     sql= " SELECT ID_HAKMILIKBANGUNAN, ID_PERMOHONAN, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, "+ 
		     	" ID_LUAS, LUAS, ID_MUKIM, ID_DAERAH, ID_NEGERI, "+ 
		     	" SEWA_BULANAN, ULASAN, "+
		     	" TARIKH_MULA, TARIKH_TAMAT, "+ 
		     	" ID_PEGAWAI, ID_MASUK, "+ 
		     	" TARIKH_MASUK, ID_KEMASKINI,"+ 
		     	" TARIKH_KEMASKINI, ID_DB "+
		     	" FROM TBLHTPHAKMILIKBANGUNAN "+
		     	" WHERE ID_PERMOHONAN="+idpermohonan;
		     	//System.out.println("FrmPajakanKecilMaklumatData:getHakmilikBangunan::sql:::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Tblhtphakmilikbangunan h = null;

		      while (rs.next()) {
		    	  h = new Tblhtphakmilikbangunan();
		    	  h.setIdHakmilikbangunan(rs.getLong("ID_HAKMILIKBANGUNAN"));
		      }
		      return h;
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 
	 public static Hashtable getHakmilikBangunanInfo(String idpermohonan)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		     Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();
		     sql= " select h.ID_HAKMILIKBANGUNAN,h.alamat1,h.alamat2,h.alamat3,h.poskod, "+
		    	  " h.ID_LUAS,h.LUAS,h.ID_MUKIM,h.ID_DAERAH,h.ID_NEGERI," +
		    	  " h.sewa_bulanan,h.ULASAN,h.TARIKH_MULA,h.TARIKH_TAMAT,h.ID_PEGAWAI  " +
		    	  " ,RL.KETERANGAN,h.TAHUN,h.BULAN, h.HARI "+
		    	  " FROM tblhtphakmilikbangunan h,TBLRUJLUAS RL where " +
		    	  "  RL.ID_LUAS= H.ID_LUAS "+
		    	  " AND h.id_permohonan = "+idpermohonan;
		     /*_, ID_PERMOHONAN,_,_,_,_, 
		     _, _, _, _, _, _,_,_, ID_MASUK, 
		     TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB 
		     */
		     //System.out.println("FrmPajakanKecilMaklumatData:sql::"+sql);
		      log.info("getHakmilikBangunanInfo====="+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
	    		  h.put("idhakmilikbangunan", rs.getString("ID_HAKMILIKBANGUNAN"));
	    		  //h.put("idpermohonan", rs.getString("ID_PERMOHONAN"));
	    		  h.put("idluas", rs.getString("ID_LUAS"));
	    		  //h.put("idmukim", rs.getString("ID_MUKIM"));
	    		  h.put("iddaerah", rs.getString("ID_DAERAH"));
	    		  h.put("idnegeri", rs.getString("ID_NEGERI"));
	    		  h.put("idpegawai", rs.getString("ID_PEGAWAI"));

		    	  if(rs.getString("alamat1")==null){
		    		  h.put("alamat1", "");
		    	  }else{
		    		  h.put("alamat1", rs.getString("alamat1")); 
		      		}
		    	  if(rs.getString("alamat2")==null){
		    		  h.put("alamat2", "");
		    	  }else{
		    		  h.put("alamat2", rs.getString("alamat2"));
		    	  }
		    	  if(rs.getString("alamat3")==null){
		    		  h.put("alamat3", "");
		    	  }else {
		    		  h.put("alamat3", rs.getString("alamat3"));
		    	  }
		    	  if(rs.getString("poskod")==null){
		    		  h.put("poskod", "");
		    	  }else{
		    		  h.put("poskod", rs.getString("poskod"));
		    	  }
		    	  if(rs.getString("sewa_bulanan")==null){
		    		  h.put("sewabulanan", "");
		    	  }else{
		    		  h.put("sewabulanan", Util.formatDecimal(rs.getDouble("sewa_bulanan")));
		    	  }
		       	  if(rs.getString("luas")==null){
		    		  h.put("luas", "");
		    	  }else{
		    		  h.put("luas", rs.getString("luas"));
		    	  }
		    	  if(rs.getDate("tarikh_mula")==null){
		    		  h.put("tmula",new Date());
		    	  }else{
		    		  h.put("tmula", Util.getDateTime(rs.getDate("tarikh_mula"), "dd/MM/yyyy"));
		    	  }
		    	  if(rs.getString("tarikh_tamat")==null){
		    		  h.put("ttamat", "");
		    	  }else{
		    		  h.put("ttamat", Util.getDateTime(rs.getDate("tarikh_tamat"), "dd/MM/yyyy"));
		    	  }
		    	  if(rs.getString("KETERANGAN")==null){
		    		  h.put("labelluas","TIADA");
		    	  }else{
		    		  h.put("labelluas", rs.getString("KETERANGAN"));
		    	  }
		    	  if(rs.getString("BULAN")==null){
		    		  h.put("bulan","0");
		    	  }else{
		    		  h.put("bulan", rs.getInt("BULAN"));
		    	  }
		    	  if(rs.getString("TAHUN")==null){
		    		  h.put("tahun","0");
		    	  }else{
		    		  h.put("tahun", rs.getInt("TAHUN"));
		    	  }
		    	  if(rs.getString("HARI")==null){
		    		  h.put("hari","0");
		    	  }else{
		    		  h.put("hari", rs.getInt("HARI"));
		    	  }
		    	  /*if(rs.getDate("tarikh_terima")==null){
		    		  h.put("rtterima",new Date());
		    	  }else{
		    		  h.put("rtterima", rs.getDate("tarikh_terima"));
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
		    	  //list.addElement(h);
		    	   */
		      }
		      return h;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	 	 
	  public static void add(Tblhtphakmilikbangunan s,String tmula,String ttamat ) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  lebah.util.Util u = new lebah.util.Util();
		  strTarikhSemasa = u.getDateTime(new Date(), "dd/MM/yyyy");
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  /*SQLRenderer r = new SQLRenderer();
		      //s.setIdHakmilikbangunan(DB.getNextID("TBLHTPHAKMILIKBANGUNAN_SEQ"));
		      //long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			  //r.add("id_hakmilikbangunan",s.getIdHakmilikbangunan());   
			  r.add("id_hakmilikbangunan",DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ"));   
		      r.add("id_permohonan", s.getIdPermohonan());
		      r.add("alamat1", s.getAlamat1());
		      r.add("alamat2",s.getAlamat2());
		      r.add("alamat3",s.getAlamat3());
		      r.add("poskod", s.getPoskod());
		      r.add("id_luas",s.getIdLuas());
		      r.add("luas",s.getLuas());	      
		      r.add("id_daerah",s.getIdDaerah());
		      r.add("id_negeri",s.getIdNegeri());
		      r.add("sewa_bulanan", s.getSewaBulanan());
		      //to_date('" + TarikhWarta + "','dd/MM/yyyy')
		      r.add("id_masuk", 1);
		      String tarikhMasuk = "to_date('" + s.getTarikhMasuk() + "','dd/MM/yyyy')";
		      r.add("tarikh_masuk", tarikhMasuk);
		      r.add("id_kemaskini", 1);
		      r.add("tarikh_kemaskini", tarikhMasuk);	*/
		      //sql = r.getSQLInsert("TBLHTPHAKMILIKBANGUNAN");
		      sql =" INSERT INTO TBLHTPHAKMILIKBANGUNAN " +
			  		"(ID_HAKMILIKBANGUNAN, ID_PERMOHONAN, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, " +
			  		"ID_LUAS, LUAS, ID_MUKIM, ID_DAERAH, " +
			  		"ID_NEGERI, SEWA_BULANAN, ULASAN, " +
			  		"TARIKH_MULA, TARIKH_TAMAT, ID_PEGAWAI, ID_MASUK, " +
			  		"TARIKH_MASUK, ID_KEMASKINI, " +
			  		"TARIKH_KEMASKINI, ID_DB,TAHUN,BULAN,HARI ) " +
			  		"VALUES  " +
			  		"( "+DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ")+", "+s.getIdPermohonan()+", " +
			  		"'"+s.getAlamat1()+"','"+s.getAlamat2()+"', '"+s.getAlamat3()+"', '"+s.getPoskod()+"', " +
			  		""+s.getIdLuas()+", '"+s.getLuas()+"', "+s.getIdDaerah()+", "+s.getIdDaerah()+"," +
			  		" "+s.getIdNegeri()+", "+s.getSewaBulanan()+", 'TIADA'," +
			  		" TO_DATE('"+tmula+"','dd/MM/yyyy'), TO_DATE('"+ttamat+"','dd/MM/yyyy'), 1, 1, " +
			  		" TO_DATE('"+strTarikhSemasa+"','yyyy-mm-dd hh24:mi:ss'), "+s.getIdMasuk()+", " +
			  		"TO_DATE('"+strTarikhSemasa+"','yyyy-mm-dd hh24:mi:ss'), "+s.getIdMasuk()+","+s.getTahun()+","+s.getBulan()+","+s.getHari()+")";
		      System.out.println("FrmPajakanKecilMaklumatData:add("+s+")::sql:::"+sql);
		      stmt.executeUpdate(sql);
		  }
		  catch(Exception e){
			  e.getMessage();
		  }
		  finally {
			  if (db != null) db.close();
		  }
	}
	  
	  public static void kemaskini(Hashtable s,String tmula,String ttamat ) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  lebah.util.Util u = new lebah.util.Util();
		  strTarikhSemasa = u.getDateTime(new Date(), "dd/MM/yyyy");
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  long id = (Long)s.get("idhakmilikbangunan");
			  r.update("id_hakmilikbangunan", id);
		      //r.add("id_permohonan", s.getIdPermohonan());
		      r.add("alamat1", s.get("alamat1"));
		      r.add("alamat2",s.get("alamat1"));
		      r.add("alamat3",s.get("alamat1"));
		      r.add("poskod", s.get("poskod"));
		      r.add("id_luas",(Long)s.get("idluas"));
		      r.add("luas",s.get("luas"));	      
		      r.add("id_daerah",(Long)s.get("iddaerah"));
		      r.add("id_negeri",(Long)s.get("idnegeri"));
		      r.add("sewa_bulanan", (Double)s.get("sewa"));
		      r.add("id_pegawai",(Long)s.get("idpegawai"));
		       
		      /**/
		      r.add("id_kemaskini", (Long)s.get("idkemaskini"));
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));
	  //String TarikhLuput = (String)s.get("TarikhLuput");
			  String TM = "to_date('" + tmula + "','dd/MM/yyyy')";
			  String TL = "to_date('" + ttamat + "','dd/MM/yyyy')";
		      r.add("tarikh_mula",r.unquote(TM));
		      r.add("tarikh_tamat",r.unquote(TL));

		      sql = r.getSQLUpdate("TBLHTPHAKMILIKBANGUNAN");
		      //System.out.println("FrmPajakanKecilMaklumatData:kemaskini("+s+")::sql:::"+sql);
		      stmt.executeUpdate(sql);
		  }
		  finally {
			  if (db != null) db.close();
		  }
	}
	  
	  public static void kemaskini(Tblhtphakmilikbangunan s,String tmula,String ttamat ) throws Exception {
		  Db db = null;
		  String sql = "";
		  String strTarikhSemasa="";
		  lebah.util.Util u = new lebah.util.Util();
		  strTarikhSemasa = u.getDateTime(new Date(), "dd/MM/yyyy");
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  long id = (Long)s.getIdHakmilikbangunan();
			  r.update("id_hakmilikbangunan", id);
		      //r.add("id_permohonan", s.getIdPermohonan());
		      r.add("alamat1", s.getAlamat1());
		      r.add("alamat2",s.getAlamat2());
		      r.add("alamat3",s.getAlamat3());
		      r.add("poskod", s.getPoskod());
		      r.add("id_luas",(Long)s.getIdLuas());
		      r.add("luas",s.getLuas());	      
		      r.add("id_daerah",(Long)s.getIdDaerah());
		      r.add("id_negeri",(Long)s.getIdNegeri());
		      r.add("sewa_bulanan", (String)s.getSewaBulanan());
		      r.add("id_pegawai",(Long)s.getIdPegawai());
		      r.add("tahun",s.getTahun());
		      r.add("bulan",s.getBulan());
		      r.add("hari",s.getHari());
		      r.add("id_kemaskini", (Long)s.getIdKemaskini());
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));
	  		  String TM = "to_date('" + tmula + "','dd/MM/yyyy')";
			  String TL = "to_date('" + ttamat + "','dd/MM/yyyy')";
		      r.add("tarikh_mula",r.unquote(TM));
		      r.add("tarikh_tamat",r.unquote(TL));

		      sql = r.getSQLUpdate("TBLHTPHAKMILIKBANGUNAN");
		      //System.out.println("FrmPajakanKecilMaklumatData:kemaskini("+s+")::sql:::"+sql);
		      stmt.executeUpdate(sql);
		  }
		  finally {
			  if (db != null) db.close();
		  }
	}

	  public static void update(Tblhtphakmilikbangunan s,String tmula,String ttamat) throws Exception {
		  Db db = null;	String sql = "";
		  String strTarikhSemasa="";
		  lebah.util.Util u = new lebah.util.Util();
		  strTarikhSemasa = u.getDateTime(new Date(), "dd/MM/yyyy");
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.clear();

			  r.update("id_htphakmilikbangunan", s.getIdHakmilikbangunan());
			  //r.add("kod_cara_bayar", kod_cara_bayar);
			  //r.add("keterangan", keterangan);
			  r.add("tarikh_mula", "TO_DATE('"+tmula+"','yyyy-mm-dd hh24:mi:ss')");
			  r.add("tarikh_tamat", "TO_DATE('"+ttamat+"','yyyy-mm-dd hh24:mi:ss')");
			  r.add("id_kemaskini", s.getIdKemaskini());
			  r.add("tarikh_kemaskini", "TO_DATE('"+strTarikhSemasa+"','yyyy-mm-dd hh24:mi:ss')");
			  sql = r.getSQLUpdate("TBLHTPHAKMILIKBANGUNAN");
			  stmt.executeUpdate(sql);
	    }finally {
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
	}