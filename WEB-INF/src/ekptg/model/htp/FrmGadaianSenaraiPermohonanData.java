//cikyati
package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmGadaianSenaraiPermohonanData {
	
	private static Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
 	private static Logger myLog = Logger.getLogger(FrmGadaianSenaraiPermohonanData.class);
 	private static Db db = null;
 	private static String sql = "";
	
	public Vector<Hashtable<String,String>> getSenaraiPermohonan(String idFail,String noFail, String nama) throws Exception {
	    list.clear();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	String select = " " +
	      		"SELECT  F.ID_FAIL,F.NO_FAIL,F.ID_NEGERI" +
	      		",NVL(p.nama,'TIADA') NAMA" +
	      		",S.KETERANGAN" +
	      		",NVL(PP.NO_RUJUKAN_LAIN,'TIADA') NO_RUJUKAN_LAIN " +
	      		",NVL((SELECT ID_FAILLAMA FROM TBLHTPFAILMAPPING WHERE ID_FAIL=F.ID_FAIL ),'') FAIL_LAMA" +
	      		",TP.ID_PERMOHONAN,TP.TARIKH_TERIMA  ";
	    	String from = "" +
	      		"FROM TBLPFDFAIL F,TBLPERMOHONAN TP, TBLHTPPERMOHONAN PP " +
	      		", TBLRUJSUBURUSANSTATUSFAIL SF, TBLRUJSUBURUSANSTATUS SS, TBLRUJSTATUS S " +
	      		",(SELECT DISTINCT P.NAMA,U.ID_PERMOHONAN" +
	      		" FROM TBLHTPHAKMILIKURUSAN U, TBLHTPPIHAKBERKEPENTINGAN P" +
	      		" WHERE U.ID_HAKMILIKURUSAN = P.ID_HAKMILIKURUSAN " +
	      		" ) P " +  		 
	      		"";
	    	String where = "" +
	      		"WHERE TP.ID_FAIL = F.ID_FAIL " +
	      		"AND TP.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
	      		"AND SF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS " +
	      		"AND SS.ID_STATUS = S.ID_STATUS AND SF.AKTIF = '1' "+
	      		"AND SF.ID_PERMOHONAN = TP.ID_PERMOHONAN " +
	      		"AND SF.AKTIF = '1' " +
	      		"AND SF.ID_FAIL = TP.ID_FAIL " +
	      		"AND ( TP.ID_STATUS is null OR TP.ID_STATUS <> 999) "+
	      		"AND TP.ID_PERMOHONAN = P.ID_PERMOHONAN(+) " +
	      		//"AND F.ID_SUBURUSAN IN (46,47) "+
	      		"";
	      		 if(!noFail.equalsIgnoreCase("")){
	      			 where += " AND PP.NO_RUJUKAN_LAIN LIKE '%"+noFail+"%' ";
	      		 }
	      		if(!nama.equalsIgnoreCase("")){
	      			 where += " AND P.NAMA LIKE '%"+nama.toUpperCase()+"%' ";
	      		 }
	      		 where +="AND F.ID_FAIL = "+idFail;
//	      		 where +=" AND p.nama LIKE '%"+carian+"%'";
	      	sql = select + from + where +" ORDER BY TP.TARIKH_TERIMA DESC,TP.ID_PERMOHONAN DESC ";
	      	myLog.info("setListPermohonan : " + sql);
	      	ResultSet rs = stmt.executeQuery(sql);
	      	Hashtable<String,String> h;
	      	int bil = 1;

	      	while (rs.next()) {
	      		h = new Hashtable<String,String>();
	      		h.put("bil", String.valueOf(bil));
	      		h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
	      		h.put("nama", Utils.isNull(rs.getString("NAMA")));
	      		h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
	      		h.put("idNegeri", rs.getString("ID_NEGERI"));
	      		h.put("noFailLain", Utils.isNull(rs.getString("NO_RUJUKAN_LAIN")));
	      		h.put("idFail", Utils.isNull(rs.getString("ID_FAIL")));
	      		h.put("noFail", Utils.isNull(rs.getString("NO_FAIL")));
	      		h.put("idFailLama", Utils.isNull(rs.getString("FAIL_LAMA")));
	      		list.addElement(h);
	      		bil++;
	      		
		      }
	      
	   	} catch(Exception e){
	    	e.printStackTrace();
	    } finally {
	    	if (db != null) {
	    		db.close();
	    	}
	    }
	    return list;
	  
	}
	public static void setListPermohonan(String idFail,String noFail, String carian)throws Exception {
	    list.clear();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String Select = " " +
	      		"SELECT tp.id_Permohonan, p.nama, s.keterangan,f.id_Negeri,PP.NO_RUJUKAN_LAIN,F.ID_FAIL " +
	      		" ,NVL((SELECT ID_FAILLAMA FROM TBLHTPFAILMAPPING WHERE ID_FAIL=F.ID_FAIL ),'') FAIL_LAMA,TP.TARIKH_TERIMA  ";
	      String From = "" +
	      		"FROM Tblpermohonan tp, TBLHTPPERMOHONAN PP, Tblhtphakmilikurusan u, Tblhtppihakberkepentingan p, " +
	      		"Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblpfdfail f ";
	      String Where = "" +
	      		"WHERE u.id_Permohonan = tp.id_Permohonan AND u.id_Hakmilikurusan = p.id_Hakmilikurusan "+
	      		"AND sf.id_Permohonan = tp.id_Permohonan AND sf.id_Suburusanstatus = ss.id_Suburusanstatus "+
	      		"AND ss.id_Status = s.id_Status AND SF.AKTIF = '1' " +
	      		"AND ( TP.ID_STATUS is null OR TP.ID_STATUS <> 999) "+
	      		"AND tp.id_Fail = f.id_Fail AND TP.ID_PERMOHONAN = PP.ID_PERMOHONAN ";

	      		 if(noFail.equalsIgnoreCase("")){
	      			 Where += " AND PP.NO_RUJUKAN_LAIN LIKE '%"+noFail+"%' ";
	      		 }
	      		 Where +="AND f.id_Fail = "+idFail;
	      		 Where +=" AND p.nama LIKE '%"+carian+"%'";
	      sql = Select + From + Where +" ORDER BY TP.TARIKH_TERIMA DESC,TP.ID_PERMOHONAN DESC ";
	      //myLog.info("setListPermohonan : " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String,String> h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("nama", Utils.isNull(rs.getString("nama")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("noFailLain", Utils.isNull(rs.getString("NO_RUJUKAN_LAIN")));
	       	  h.put("idFail", Utils.isNull(rs.getString("ID_FAIL")));
	       	  h.put("idFailLama", Utils.isNull(rs.getString("FAIL_LAMA")));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } catch(Exception e){
	    	e.printStackTrace();
	    } finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	  
	}
	
	public static void setListPermohonanOnline(String idFail,String noFail, String carian)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String Select = " " +
	      		"SELECT tp.id_Permohonan, p.nama, s.keterangan,f.id_Negeri,PP.NO_RUJUKAN_LAIN,F.ID_FAIL " +
	      		" ,NVL((SELECT ID_FAILLAMA FROM TBLHTPFAILMAPPING WHERE ID_FAIL=F.ID_FAIL ),'') FAIL_LAMA ";
	      String From = "" +
	      		"FROM Tblpermohonan tp, TBLHTPPERMOHONAN PP, Tblhtphakmilikurusan u, Tblhtppihakberkepentingan p, " +
	      		"Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblpfdfail f ";
	      String Where = "" +
	      		"WHERE u.id_Permohonan = tp.id_Permohonan AND u.id_Hakmilikurusan = p.id_Hakmilikurusan "+
	      		"AND sf.id_Permohonan = tp.id_Permohonan AND sf.id_Suburusanstatus = ss.id_Suburusanstatus "+
	      		"AND ss.id_Status = s.id_Status " +
	      		"AND ( TP.ID_STATUS is null OR TP.ID_STATUS <> 999) "+
	      		"AND tp.id_Fail = f.id_Fail AND TP.ID_PERMOHONAN = PP.ID_PERMOHONAN ";

	      		 if(noFail.equalsIgnoreCase("")){
	      			 Where += " AND PP.NO_RUJUKAN_LAIN LIKE '%"+noFail+"%' ";
	      		 }
	      		 Where +="AND f.id_Fail = "+idFail;
	      		 Where +=" AND p.nama LIKE '%"+carian+"%'";
	      /*
	      SQLRenderer r = new SQLRenderer();
	      r.add("tp.id_Fail");
	      r.add("tp.id_Permohonan");
	      r.add("p.nama");
	      r.add("s.keterangan");

	      r.add("u.id_Permohonan",r.unquote("tp.id_Permohonan"));
	      r.add("u.id_Hakmilikurusan",r.unquote("p.id_Hakmilikurusan"));
	      r.add("sf.id_Permohonan",r.unquote("tp.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("ss.id_Suburusanstatus"));
	      r.add("ss.id_Status",r.unquote("s.id_Status"));
	      r.set("tp.id_Fail",idFail);

	      sql = r.getSQLSelect(Table);
	      */
	      sql = Select + From + Where;
	      myLog.info("setListPermohonan : " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      //
	      Hashtable<String,String> h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("nama", Utils.isNull(rs.getString("nama")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("noFailLain", Utils.isNull(rs.getString("NO_RUJUKAN_LAIN")));
	       	  h.put("idFail", Utils.isNull(rs.getString("ID_FAIL")));
	       	  h.put("idFailLama", Utils.isNull(rs.getString("FAIL_LAMA")));
	       	  myLog.info(h);
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
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

	  public static Vector<Hashtable<String,String>> getListPermohonan(){
		  myLog.info("FrmGadaianSenaraiPermohonanData:: getListPermohonan : "+ list.size());
		  return list;
	  }
}
