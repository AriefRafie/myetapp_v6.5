package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmSenaraiFailPelepasanGadaianData {
	private static Vector failPelepasan;
	private static Logger log = Logger.getLogger(FrmSenaraiFailPelepasanGadaianData.class);
	private static Vector<Hashtable> senaraiFailPelepasan;
	
	public static void SenaraiFailPelepasanGadaian(String carian, String noFail, Long idNegeri)throws Exception {
	    Db db = null;
	    String sql = "";
	    String Like = "";
	    if (idNegeri == 20){
	    	idNegeri = null;
	    }
	    
	    try {
	      db = new Db();
	      failPelepasan = new Vector();
	      Statement stmt = db.getStatement();

	      sql = "SELECT distinct PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
			sql += " PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
			sql += "FROM Tblpfdfail pfdf, tblrujnegeri NEG, tblrujsuburusan S, tblhtpfailmapping fm ";
			sql += "WHERE PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN ";
			sql += " AND pfdf.id_negeri = NEG.ID_NEGERI ";
			sql += " AND PFDF.TAJUK_FAIL LIKE '%" + Like + "%'";
			sql += " AND pfdf.id_fail = fm.id_fail ";
			
			
			log.info(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	    
	     
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  failPelepasan.addElement(h);
	    	  bil++;
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

	public static Vector<Hashtable<String, Comparable>> getSenaraiFailPelepasanGadaian(String carian, String noFail, String idNegeri)throws Exception {
		Db db = null;
	    String sql = "";
	    String Like = "";
	    //if (idNegeri == 20){
	    //	idNegeri = null;
	    //}
	    
	    try {
	    	db = new Db();
	    	failPelepasan = new Vector<Hashtable<String, Comparable<Integer>>>();
	    	Statement stmt = db.getStatement();

	    	sql = "SELECT distinct PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
			sql += " PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
			sql += " FROM Tblpfdfail PFDF, tblrujnegeri NEG, tblrujsuburusan S, tblhtpfailmapping FM ";
			sql += " WHERE PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN ";
			sql += " AND pfdf.id_negeri = NEG.ID_NEGERI AND F.ID_STATUS <> 999 ";
			sql += " AND PFDF.TAJUK_FAIL LIKE '%" + carian + "%'";
			sql += " AND pfdf.id_fail = fm.id_fail ";			
			if(noFail!=""){
				sql += " AND PFDF.NO_FAIL like '%"+noFail+ "%'";	
			}
			if(String.valueOf(idNegeri)!="20"){
				sql += " AND PFDF.ID_NEGERI = "+idNegeri;	
				
			}
			log.info("getSenaraiFailPelepasanGadaian:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	     
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id", rs.getString("id_Fail"));
				h.put("no", rs.getString("no_Fail"));
				h.put("tajuk", rs.getString("tajuk_Fail"));
				h.put("negeri", rs.getString("nama_Negeri"));
				failPelepasan.addElement(h);
				bil++;
			}

	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	    	if (db != null) {
	    		db.close();
	    	}
	    }
	    return failPelepasan;
	}	

	public static Vector<Hashtable<String, Comparable>> getSenaraiFailPelepasanGadaianLama(String carian, String noFail, String idNegeri)throws Exception {
		Db db = null;
	    String sql = "";
	    String Like = "";
	    try {
	    	db = new Db();
	    	failPelepasan = new Vector<Hashtable<String, Comparable<Integer>>>();
	    	Statement stmt = db.getStatement();

	    	sql = "SELECT distinct PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
			sql += " PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
			sql += " FROM Tblpfdfail PFDF, tblrujnegeri NEG, tblrujsuburusan S ";
			sql += " WHERE PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN AND PFDF.ID_URUSAN=108 " ;
			sql += " AND S.ID_SUBURUSAN IN (45,46,47) AND pfdf.id_negeri = NEG.ID_NEGERI ";
			sql += " AND PFDF.ID_STATUS <> 999 AND PFDF.TAJUK_FAIL LIKE '%" + carian + "%'";
			//sql += " AND pfdf.id_fail = fm.id_fail ";			
			if(noFail!=""){
				sql += " AND PFDF.NO_FAIL like '%"+noFail+ "%'";	
			}
			if(String.valueOf(idNegeri)!="20"){
				sql += " AND PFDF.ID_NEGERI = "+idNegeri;	
				
			}
			log.info("getSenaraiFailPelepasanGadaian:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	     
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id", rs.getString("ID_FAIL"));
				h.put("no", rs.getString("NO_FAIL"));
				h.put("tajuk", rs.getString("TAJUK_FAIL"));
				h.put("negeri", rs.getString("NAMA_NEGERI"));
				failPelepasan.addElement(h);
				bil++;
			}

	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally {
	    	if (db != null) {
	    		db.close();
	    	}
	    }
	    return failPelepasan;
	}		
	public static Vector getFailPelepasan() {
		return failPelepasan;
	}
	
	public static void ListPermohonanPelepasanGadaian(String idFail, String noFail){
		Db db = null;
	    String sql = "";    
	    try {
	      db = new Db();
	      senaraiFailPelepasan = new Vector();
	      Statement stmt = db.getStatement();
	      sql = "SELECT F.ID_FAIL,P.ID_PERMOHONAN,PP.NO_RUJUKAN_LAIN,TPPB.NAMA ";
	      sql += " FROM TBLPFDFAIL F,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLHTPHAKMILIKURUSAN TPHU,TBLHTPPIHAKBERKEPENTINGAN TPPB ";
	      sql += " WHERE F.ID_FAIL = FP.ID_FAIL AND F.ID_FAIL="+idFail;
	      sql += " AND FP.ID_FAILLAMA = P.ID_FAIL ";
	      sql += " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN ";
	      sql += " AND PP.ID_PERMOHONAN = TPHU.ID_PERMOHONAN ";
	      sql += " AND TPHU.ID_HAKMILIKURUSAN=TPPB.ID_HAKMILIKURUSAN ORDER BY PP.TARIKH_MASUK DESC";
	      
	      log.info("ListPermohonanPelepasanGadaian("+idFail+","+ noFail+")"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idPermohonan", rs.getString("id_permohonan"));
	    	  h.put("nama", rs.getString("nama"));
	    	  h.put("noFailLain", rs.getString("no_rujukan_lain"));
	    	  senaraiFailPelepasan.addElement(h);
	    	  bil++;
	      }

	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	    
	}
	
	public Vector getPermohonanPelepasanGadaian(String idFail, String noFail){
    	//Vector returnValue = new Vector();
	    senaraiFailPelepasan = new Vector();

		Db db = null;
	    String sql = "";    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT F.ID_FAIL,P.ID_PERMOHONAN,PP.NO_RUJUKAN_LAIN,TPPB.NAMA ";
	      sql += " FROM TBLPFDFAIL F,TBLHTPFAILMAPPING FP,TBLPERMOHONAN P,TBLHTPPERMOHONAN PP,TBLHTPHAKMILIKURUSAN TPHU,TBLHTPPIHAKBERKEPENTINGAN TPPB ";
	      sql += " WHERE F.ID_FAIL = FP.ID_FAIL AND F.ID_FAIL = "+idFail;
	      sql += " AND FP.ID_FAILLAMA = P.ID_FAIL ";
	      sql += " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN ";
	      sql += " AND PP.ID_PERMOHONAN = TPHU.ID_PERMOHONAN ";
	      sql += " AND TPHU.ID_HAKMILIKURUSAN=TPPB.ID_HAKMILIKURUSAN ORDER BY PP.TARIKH_MASUK DESC";
	      
	      log.info("getPermohonanPelepasanGadaian("+idFail+","+ noFail+")"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idPermohonan", rs.getString("id_permohonan"));
	    	  h.put("nama", rs.getString("nama"));
	    	  h.put("noFailLain", rs.getString("no_rujukan_lain"));
	    	  senaraiFailPelepasan.addElement(h);
	    	  bil++;
	      }

	    }catch(Exception e){
	    	e.printStackTrace();
	    
	    }finally {
	      if (db != null) {
	    	  db.close();
	      }
	    }
	    return senaraiFailPelepasan;
	}

	public static Vector<Hashtable> getSenaraiFailPelepasan() {
		return senaraiFailPelepasan;
	}

}//close class
