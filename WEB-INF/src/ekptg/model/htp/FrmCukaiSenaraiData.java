package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmCukaiSenaraiData {

	static Logger myLog = Logger.getLogger(FrmCukaiSenaraiData.class);

	public static Vector getList(String idUrusan, String carian, String noFail, Long idNegeri) throws Exception {
		Db db = null;
		//list.clear();
		String sql = "";
		String Like = "";
		if (idNegeri == 20)
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu ";
	      sql +="FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
	      sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri ";
	      sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
	      sql +="AND sf.aktif = '1' AND f.id_Urusan = 108 AND f.tajuk_Fail LIKE '%"+carian+"%' ";
	      sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";
	      if(idNegeri != null)
	    	  sql +="AND f.id_Negeri = "+idNegeri;
	      sql +=" ORDER BY n.kod_Mampu";
	      
	      System.out.println("FrmCukaiSenaraiData::getList::sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Vector list = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("kodMampu", rs.getString("kod_Mampu"));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getViewTempData(String idHakmilik) throws Exception {
		
		Connection conn = null;
		Db db = null;
		//list.clear();
		String sql = "";
		String Like = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();

	      sql = " select trn.NAMA_NEGERI,trd.NAMA_DAERAH,trm.NAMA_MUKIM, ct.NO_HAKMILIKUPLOAD,ct.NO_LOTUPLOAD,"+ 
	    	  	" ct.KEGUNAAN_TANAH, ct.CUKAI_KENA_BAYAR, ct.CUKAI_PERLU_BAYAR, ct.DENDA, ct.TUNGGAKAN, ct.PENGECUALIAN,  ct.TAHUN "+
	    	  	" from tblhtpcukaitemp ct, tblrujnegeri trn, tblrujdaerah trd, tblrujmukim trm "+
	    	  	" where ct.ID_NEGERI = trn.ID_NEGERI " +
	    	  	" and ct.ID_DAERAH = trd.ID_DAERAH " +
	    	  	" and ct.ID_MUKIM = trm.ID_MUKIM " +
	    	  	" and no_hakmilikupload = '" + idHakmilik+"'" ;
	      
	      System.out.println("FrmCukaiSenaraiData::getList::sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Vector list = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI"));
	    	  h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
	    	  h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
	    	  if(rs.getString("TAHUN") == null){
		    	  h.put("tahun", "");
		    	  }else{
		    		  h.put("tahun", rs.getString("TAHUN"));
		    	  }
	    	 // h.put("tahun", rs.getString("TAHUN"));
	    	  //h.put("nolot", rs.getString("no_lot"));
	    	  h.put("nolotupload", rs.getString("NO_LOTUPLOAD"));
	    	  h.put("nohakmilikupload", rs.getString("NO_HAKMILIKUPLOAD"));
	    	  h.put("KEGUNAAN_TANAH", rs.getString("KEGUNAAN_TANAH"));
	    	  h.put("CUKAI_KENA_BAYAR", rs.getString("CUKAI_KENA_BAYAR"));
	    	  h.put("CUKAI_PERLU_BAYAR", rs.getString("CUKAI_PERLU_BAYAR"));
	    	  h.put("DENDA", rs.getString("DENDA"));
	    	  h.put("TUNGGAKAN", rs.getString("TUNGGAKAN"));
	    	  if(rs.getString("PENGECUALIAN") == null){
		    	  h.put("PENGECUALIAN", "0");
		    	  }else{
		    		  h.put("PENGECUALIAN", rs.getString("PENGECUALIAN"));
		    	  }
	    	  //h.put("PENGECUALIAN", rs.getString("PENGECUALIAN"));
	    	  list.addElement(h);
	    	  bil++;
	    	  conn.commit();
	      }
	      return list;
	    } finally {
	    	if(conn != null) conn.close();
	      if (db != null) db.close();
	    }
	  }
	
	  public static Vector getSenaraiTahun()throws Exception{
		  Db db = null;
		  String sql = "";
		  
		  try{
			  db = new Db();
			  Statement stmt = db.getStatement();
			  
			  //System.out.println("getSenaraiTapis :negeri::"+tahun);
		     
		      
		     sql = " select t.tahun,count(t.tahun) as counttahun from TBLHTPCUKAITEMP t group by t.tahun ";

		 System.out.println("getSenaraiTapis ::sql::"+sql);
		
		 
		 ResultSet rs = stmt.executeQuery(sql);
		 Vector list = new Vector();
	      Hashtable h = null;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  if (rs.getString("TAHUN") != null && rs.getString("TAHUN") != ""){
	    		  h.put("tahun", rs.getString("TAHUN"));
	    	  }else{
	    		  h.put("tahun", "");
	    	  } 
	    	  
	    	  if (rs.getString("counttahun") != null && rs.getString("counttahun") != ""){
	    		  h.put("counttahun", rs.getString("counttahun"));
	    	  }else{
	    		  h.put("counttahun", "");
	    	  } 
	    	  
	    	  list.addElement(h);
	      }
	      
	      return list;
	      
		  } finally {
		      if (db != null) db.close();
		    }
		
	  }
	
	  public static Vector getSenaraiTapis(String negeri,String daerah, String mukim, String nohakilik)throws Exception{
		  Db db = null;
		  String sql = "";
		  
		  try{
			  db = new Db();
			  Statement stmt = db.getStatement();
			  
			  System.out.println("getSenaraiTapis :negeri::"+negeri);
		      System.out.println("getSenaraiTapis :daerah::"+daerah);
		      System.out.println("getSenaraiTapis :mukim::"+mukim);
		      System.out.println("getSenaraiTapis :nohakmilik::"+nohakilik);
		      
		     sql = " select distinct ct.id_negeri,trn.NAMA_NEGERI,trd.ID_DAERAH,trd.NAMA_DAERAH, ct.NAMA_MUKIM, "+
		       	   " ct.NO_HAKMILIKUPLOAD,ct.NO_LOTUPLOAD,ct.KEGUNAAN_TANAH,ct.CUKAI_KENA_BAYAR,ct.CUKAI_PERLU_BAYAR "+
		       	   " from TBLHTPCUKAITEMP ct,tblrujnegeri trn, tblrujdaerah trd, tblrujmukim trm"+
		       	   " where ct.ID_NEGERI = trn.ID_NEGERI and ct.ID_DAERAH = trd.ID_DAERAH and  ct.ID_DAERAH = trm.ID_DAERAH ";
		 if(negeri != ""){    
		     sql += " and ct.ID_NEGERI = " + negeri;
		 }
		 if(daerah != ""){
		     sql += " and ct.ID_DAERAH = " + daerah;
		 }
		 if(mukim != ""){
			 sql +=  " and trm.NAMA_MUKIM = ct.NAMA_MUKIM and trm.ID_MUKIM =" + mukim;
		 }
		 if(nohakilik != ""){
			 sql += " and ct.NO_HAKMILIKUPLOAD LIKE trim('%" + nohakilik + "%')";
		 }
		 
		 System.out.println("getSenaraiTapis ::sql::"+sql);
		
		 
		 ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable<String, String> h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable<String, String>();
	    	  //System.out.println("kegunaan tanah"+ rs.getString("KEGUNAAN_TANAH") );
	    	  h.put("lblNamaDaerah", rs.getString("NAMA_DAERAH"));
	    	  h.put("lblNamaMukim", rs.getString("NAMA_MUKIM"));
	    	  h.put("Nohakmilik", rs.getString("NO_HAKMILIKUPLOAD"));
	    	  h.put("NoLot", rs.getString("NO_LOTUPLOAD"));
	    	  
	    	  if (rs.getString("KEGUNAAN_TANAH") != null && rs.getString("KEGUNAAN_TANAH") != ""){
	    		  h.put("KTanah", rs.getString("KEGUNAAN_TANAH"));
	    	  }else{
	    		  h.put("KTanah", "");
	    	  }

	    	  h.put("CUKAI_PERLU_BAYAR", rs.getString("CUKAI_PERLU_BAYAR"));
	    		    	  
	    	  list.addElement(h);
	      }
			  return list;
		  } finally {
		      if (db != null) db.close();
		    }
		  //return null;
	  }
	  
	  public static Vector getSenaraiCukai(String carian, String noFail, Long idNegeri,Hashtable data)throws Exception {
		  Db db = null;
		  String sql = "";
		  String sqlrizab = "";
		  String sqlpajak = "";
		  String sqlpembelian = "";
		  String A ="";
		  String B ="";
		  String C ="";
		  
		   A = (String)data.get("status1");
		   B = (String)data.get("status2");
		   C = (String)data.get("status3");
		  
		  try {
		      db = new Db();
		      Statement stmt = db.getStatement();
 		      sql = "select distinct ct.CUKAI_KENA_BAYAR, ct.TUNGGAKAN, ct.DENDA, ct.CUKAI_PERLU_BAYAR, ct.NO_LOTUPLOAD,"+
		    	  	" ct.KEGUNAAN_TANAH, ct.ID_NEGERI, h.pegangan_hakmilik, lot.KETERANGAN, NVL((sysdate-h.tarikh_TERIMA),0) ddd , h.PEGANGAN_HAKMILIK,"+
		    	  	" h.NO_HAKMILIK, h.NO_LOT, h.LUAS_BERSAMAAN LUAS, h.LOKASI"+
		    	  	" from tblhtphakmilik h, Tblrujlot lot, tblhtpcukaitemp ct where";
		 
		      
		      sqlrizab = "h.id_hakmilik not in (select id_hakmilik from tblhtphakmilik where status_rizab='Y' )";
		      
		      sqlpembelian = " h.id_hakmilik not in (select tph.id_hakmilik from tblhtphakmilik tph where NVL((sysdate-tph.TARIKH_TERIMA),0) != 0)";
		     
		      sqlpajak = "  h.id_hakmilik not in (select tph.id_hakmilik from tblhtphakmilikurusan tphu,tblhtphakmilik tph,tblPermohonan tp,TBLPFDFAIL F " +
	      		" where tph.PEGANGAN_HAKMILIK=tphu.PEGANGAN_HAKMILIK and tphu.ID_PERMOHONAN=tp.ID_PERMOHONAN AND TP.ID_FAIL=F.ID_FAIL AND F.ID_URUSAN=3) ";
		     
		      sql +=" lot.ID_LOT = h.ID_LOT and ct.NO_HAKMILIK = ltrim(h.NO_HAKMILIK,0) AND h.id_negeri="+ idNegeri ;
		      
		      
		      if (A.equalsIgnoreCase("pembelian")){
		    	  
		    	  sql += " AND "+ sqlpembelian;
		      		
		      }if (B.equalsIgnoreCase("pajakan")){
		    	  
		    	  sql += " AND "+ sqlpajak;
		      		
		      }if(C.equalsIgnoreCase("rizab")){
		    	  
		    	  sql += " AND " + sqlrizab;  
		      
		      }
		      SQLRenderer r = new SQLRenderer();

		      r.add("h.id_hakmilik");
//		      r.add("m.NAMA_MUKIM");
//		      r.add("j.kod_jenis_hakmilik");
//		      r.add("h.no_HAKMILIK");
//		      r.add("l.keterangan");
//		      r.add("h.NO_LOT");
//		      r.add("h.luas");
//		      r.add("lu.KOD_LUAS");
//		      r.add("hal.kegunaan_tapak");
//		      r.add("h.cukai");
//		      r.add("h.NO_FAIL_PTG");
//		      r.add("f.NO_FAIL");
//		      //r.add("h.NO_LOT");
//		      //r.add("h.luas");
//		      //r.add("lu.KOD_LUAS");
//		      r.add("h.id_mukim",r.unquote("h.id_mukim"));
//		      r.add("h.id_jenishakmilik",r.unquote("j.id_jenishakmilik"));
//		      r.add("h.id_lot",r.unquote("l.id_lot"));
//		      r.add("h.id_luas",r.unquote("lu.id_luas"));
//		      r.add("h.id_hakmilik",r.unquote("hal.id_hakmilik"));
//		      r.add("h.id_permohonan",r.unquote("p.id_permohonan"));
//		      r.add("p.id_fail",r.unquote("f.id_fail"));
//		      r.add("h.id_negeri",r.unquote("6"));
		      //r += "h.no_hakmilik is not null";
		      
		      //sql = r.getSQLSelect("tblrujmukim m,tblrujjenishakmilik j,tblhtphakmilik h,tblrujlot l, " +
		      //	"tblrujluas lu,tblhtphakmilikperihal hal,tblpermohonan p, tblpfdfail f," +
		      //"tblhtphakmilikcukai c");
		      myLog.info("FrmCukaiSenaraiData:getSenaraiCukai::sql 2:::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      Hashtable<String, String> h;
		      int bil = 1;
		      
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	 
		    	  //h.put("idhakmilik", rs.getString("id_hakmilik"));
		    	  h.put("pHakmilik", rs.getString("pegangan_hakmilik"));
		    	  h.put("luas", rs.getString("luas"));
		    	  h.put("NO_LOTUPLOAD", rs.getString("NO_LOTUPLOAD"));
		    	  h.put("CUKAI_KENA_BAYAR", rs.getString("CUKAI_KENA_BAYAR"));
				  h.put("cukai_kena_bayar", Utils.format2Decimal(rs.getDouble("CUKAI_KENA_BAYAR")));
		    	  h.put("TUNGGAKAN", rs.getString("TUNGGAKAN"));
				  h.put("tunggakan", Utils.format2Decimal(rs.getDouble("TUNGGAKAN")));
		    	  h.put("DENDA", rs.getString("DENDA"));
				  h.put("denda", Utils.format2Decimal(rs.getDouble("DENDA")));
		    	  h.put("cukailain", Utils.format2Decimal(0.00));
		    	  h.put("LEBIHAN", Utils.format2Decimal(0.00));
				  h.put("lebihan", Utils.format2Decimal(0.00));
		    	  h.put("CUKAI_PERLU_BAYAR", rs.getString("CUKAI_PERLU_BAYAR"));
				  h.put("cukai_perlu_bayar", Utils.format2Decimal(rs.getDouble("CUKAI_PERLU_BAYAR")));
		    	  if(rs.getString("lokasi") == null){
		    	  h.put("lokasi", "");
		    	  }else{
		    		  h.put("lokasi", rs.getString("lokasi"));
		    	  }
		    	  h.put("nolot", rs.getString("no_lot"));
		    	  h.put("KEGUNAAN_TANAH", rs.getString("KEGUNAAN_TANAH"));
		    		    
		    		    	  
		    	  list.addElement(h);
		      }

		     /* while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("namamukim", rs.getString("NAMA_MUKIM"));
		    	  h.put("namamukim", rs.getString("NAMA_MUKIM"));
		    	  h.put("jenishakmilik", rs.getString("kod_jenis_hakmilik"));
		    	  h.put("nohakmilik", rs.getString("no_HAKMILIK"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("nolot", rs.getString("NO_LOT"));
		    	  h.put("luas", ekptg.helpers.Utils.formatLuas(rs.getDouble("luas")));
		    	  h.put("kodluas", rs.getString("KOD_LUAS"));
		    	  if(rs.getString("kegunaan_tapak") != null){
		    		  h.put("kegunaantapak", rs.getString("kegunaan_tapak"));
		    	  }else{
		    		  h.put("kegunaantapak", "TIADA");
		    	  }
		    	  h.put("cukai", lebah.util.Util.formatDecimal(rs.getDouble("cukai")));
		    	  if(rs.getString("NO_FAIL_PTG") != null){
		    		  h.put("nofailptg", rs.getString("NO_FAIL_PTG"));
		    		  h.put("nofailptg_", rs.getString("NO_FAIL_PTG"));
		    	  }else{
		    		  h.put("nofailptg", "TIADA");
		    		  h.put("nofailptg_", "");
		    	  }
		    	  if(rs.getString("NO_FAIL") != null){
		    		  h.put("nofail", rs.getString("NO_FAIL"));
		    	  }else{
		    		  h.put("nofail", "TIADA");
		    		  h.put("nofailk", "");
		    	  }		    	  
		    	  //h.put("kegunaantapak", rs.getString("kegunaan_tapak"));
		    	  //h.put("nofailptg", rs.getString("NO_FAIL"));
		    	  //h.put("nofail", rs.getString("NO_FAIL"));
		    	  //h.put("kodMampu", rs.getString("kod_Mampu"));		    	  
		    	  list.addElement(h);
		      }*/
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

	  
	  
}
