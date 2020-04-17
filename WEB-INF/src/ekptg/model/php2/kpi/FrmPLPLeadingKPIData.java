/**
 * 
 */
package ekptg.model.php2.kpi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * 
 *
 */
public class FrmPLPLeadingKPIData {
	
	private Vector beanMaklumatKPISasaran = null;
	private Vector beanMaklumatKPIPelepasan = null;
	
	public String getIdKPISasaran(String idSuburusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_KPISASARAN");
			
			r.add("ID_URUSAN", "6");
			r.add("ID_SUBURUSAN", idSuburusan);

			sql = r.getSQLSelect("TBLPHPKPISASARAN");
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_KPISASARAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKPISasaran(String idKPISasaran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKPISasaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPHPKPISASARAN WHERE ID_KPISASARAN = '" + idKPISasaran + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("idKPISasaran", rs.getString("ID_KPISASARAN") == null ? "" : rs.getString("ID_KPISASARAN"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("idSeksyen", rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				
				h.put("F1", rs.getString("F1") == null ? "0" : rs.getString("F1"));
				h.put("F2", rs.getString("F2") == null ? "0" : rs.getString("F2"));
				h.put("F3", rs.getString("F3") == null ? "0" : rs.getString("F3"));
				h.put("F4", rs.getString("F4") == null ? "0" : rs.getString("F4"));
				h.put("F5", rs.getString("F5") == null ? "0" : rs.getString("F5"));
				h.put("F6", rs.getString("F6") == null ? "0" : rs.getString("F6"));
				h.put("F7", rs.getString("F7") == null ? "0" : rs.getString("F7"));
				h.put("F8", rs.getString("F8") == null ? "0" : rs.getString("F8"));
				h.put("F9", rs.getString("F9") == null ? "0" : rs.getString("F9"));
				h.put("F10", rs.getString("F10") == null ? "0" : rs.getString("F10"));
				
				h.put("F11", rs.getString("F11") == null ? "0" : rs.getString("F11"));
				h.put("F12", rs.getString("F12") == null ? "0" : rs.getString("F12"));
				h.put("F13", rs.getString("F13") == null ? "0" : rs.getString("F13"));
				h.put("F14", rs.getString("F14") == null ? "0" : rs.getString("F14"));
				h.put("F15", rs.getString("F15") == null ? "0" : rs.getString("F15"));
				h.put("F16", rs.getString("F16") == null ? "0" : rs.getString("F16"));
				h.put("F17", rs.getString("F17") == null ? "0" : rs.getString("F17"));
				h.put("F18", rs.getString("F18") == null ? "0" : rs.getString("F18"));
				h.put("F19", rs.getString("F19") == null ? "0" : rs.getString("F19"));
				h.put("F20", rs.getString("F20") == null ? "0" : rs.getString("F20"));
				
				h.put("F21", rs.getString("F21") == null ? "0" : rs.getString("F21"));
				h.put("F22", rs.getString("F22") == null ? "0" : rs.getString("F22"));
				h.put("F23", rs.getString("F23") == null ? "0" : rs.getString("F23"));
				h.put("F24", rs.getString("F24") == null ? "0" : rs.getString("F24"));
				h.put("F25", rs.getString("F25") == null ? "0" : rs.getString("F25"));
				h.put("F26", rs.getString("F26") == null ? "0" : rs.getString("F26"));
				h.put("F27", rs.getString("F27") == null ? "0" : rs.getString("F27"));
				h.put("F28", rs.getString("F28") == null ? "0" : rs.getString("F28"));
				h.put("F29", rs.getString("F29") == null ? "0" : rs.getString("F29"));
				h.put("F30", rs.getString("F30") == null ? "0" : rs.getString("F30"));

				beanMaklumatKPISasaran.addElement(h);
			} else {
				
				h = new Hashtable();
				h.put("idKPISasaran", "");
				h.put("idNegeri", "");
				h.put("idPejabat", "");
				h.put("idUrusan", "");
				h.put("idSuburusan", "");
				h.put("idSeksyen", "");
				
				h.put("F1", "0");
				h.put("F2", "0");
				h.put("F3", "0");
				h.put("F4", "0");
				h.put("F5", "0");
				h.put("F6", "0");
				h.put("F7", "0");
				h.put("F8", "0");
				h.put("F9", "0");
				h.put("F10", "0");
				
				h.put("F11", "0");
				h.put("F12", "0");
				h.put("F13", "0");
				h.put("F14", "0");
				h.put("F15", "0");
				h.put("F16", "0");
				h.put("F17", "0");
				h.put("F18", "0");
				h.put("F19", "0");
				h.put("F20", "0");
				
				h.put("F21", "0");
				h.put("F22", "0");
				h.put("F23", "0");
				h.put("F24", "0");
				h.put("F25", "0");
				h.put("F26", "0");
				h.put("F27", "0");
				h.put("F28", "0");
				h.put("F29", "0");
				h.put("F30", "0");

				beanMaklumatKPISasaran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveKPISasaran(String idSuburusan, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLPHPKPISASARAN
			long idKPISasaran = DB.getNextID("TBLPHPKPISASARAN_SEQ");
			r.add("ID_KPISASARAN", idKPISasaran);
			r.add("ID_NEGERI", "16");
			r.add("ID_URUSAN", "6");
			
			//asal
			//r.add("ID_SUBURUSAN", "34");
			// edit
			r.add("ID_SUBURUSAN", idSuburusan);
			
			r.add("ID_SEKSYEN", "4");
			
			r.add("F1", hash.get("F1"));
			r.add("F2", hash.get("F2"));
			r.add("F3", hash.get("F3"));
			r.add("F4", hash.get("F4"));
			r.add("F5", hash.get("F5"));
			r.add("F6", hash.get("F6"));
			r.add("F7", hash.get("F7"));
			r.add("F8", hash.get("F8"));
			r.add("F9", hash.get("F9"));
			r.add("F10", hash.get("F10"));
			r.add("F11", hash.get("F11"));
			r.add("F12", hash.get("F12"));
			r.add("F13", hash.get("F13"));
			r.add("F14", hash.get("F14"));
			r.add("F15", hash.get("F15"));
			r.add("F16", hash.get("F16"));
			r.add("F17", hash.get("F17"));
			r.add("F18", hash.get("F18"));
			r.add("F19", hash.get("F19"));
			r.add("F20", hash.get("F20"));
			r.add("F21", hash.get("F21"));
			r.add("F22", hash.get("F22"));
			r.add("F23", hash.get("F23"));
			r.add("F24", hash.get("F24"));
			r.add("F25", hash.get("F25"));
			r.add("F26", hash.get("F26"));
			r.add("F27", hash.get("F27"));
			r.add("F28", hash.get("F28"));
			r.add("F29", hash.get("F29"));
			r.add("F30", hash.get("F30"));
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));	
			
			sql = r.getSQLInsert("TBLPHPKPISASARAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void updateKPISasaran(String idKPISasaran, String idSuburusan, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLPHPKPISASARAN
			r.update("ID_KPISASARAN", idKPISasaran);
			r.add("ID_NEGERI", "16");
			r.add("ID_URUSAN", "6");
			//asal
			//r.add("ID_SUBURUSAN", "34");
			// edit
			r.add("ID_SUBURUSAN", idSuburusan);
			
			r.add("ID_SEKSYEN", "4");
			
			r.add("F1", hash.get("F1"));
			r.add("F2", hash.get("F2"));
			r.add("F3", hash.get("F3"));
			r.add("F4", hash.get("F4"));
			r.add("F5", hash.get("F5"));
			r.add("F6", hash.get("F6"));
			r.add("F7", hash.get("F7"));
			r.add("F8", hash.get("F8"));
			r.add("F9", hash.get("F9"));
			r.add("F10", hash.get("F10"));
			r.add("F11", hash.get("F11"));
			r.add("F12", hash.get("F12"));
			r.add("F13", hash.get("F13"));
			r.add("F14", hash.get("F14"));
			r.add("F15", hash.get("F15"));
			r.add("F16", hash.get("F16"));
			r.add("F17", hash.get("F17"));
			r.add("F18", hash.get("F18"));
			r.add("F19", hash.get("F19"));
			r.add("F20", hash.get("F20"));
			r.add("F21", hash.get("F21"));
			r.add("F22", hash.get("F22"));
			r.add("F23", hash.get("F23"));
			r.add("F24", hash.get("F24"));
			r.add("F25", hash.get("F25"));
			r.add("F26", hash.get("F26"));
			r.add("F27", hash.get("F27"));
			r.add("F28", hash.get("F28"));
			r.add("F29", hash.get("F29"));
			r.add("F30", hash.get("F30"));
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));	
			
			sql = r.getSQLUpdate("TBLPHPKPISASARAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void setMaklumatKPIPelepasan(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
		Double E1 = 0D;
		Double E2 = 0D;
		Double E3 = 0D;
		Double E4 = 0D;
		
		Double F1 = 0D;
		Double F2 = 0D;
		Double F3 = 0D;
		Double F4 = 0D;

		try {
			beanMaklumatKPIPelepasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPFDFAIL.ID_SUBURUSAN = '34'"
				+ " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')";

			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = "SELECT ID_KPISASARAN, ID_NEGERI, ID_PEJABATJKPTG, ID_URUSAN, ID_SUBURUSAN,"
				+ " ID_SEKSYEN, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10,"
				
				+ " (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL))"
				+ " FROM TBLPFDFAIL"
				+ " WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS DITERIMA,"
				         
				+ " (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL))"
				+ " FROM TBLPFDFAIL, TBLPERMOHONAN"
				+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
				+ " AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLPERMOHONAN.ID_STATUS = '1610207') AS DILULUSKAN,"
				           
				+ " (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL))"
				+ " FROM TBLPFDFAIL, TBLPERMOHONAN"
				+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
				+ " AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLPERMOHONAN.ID_STATUS = '1610208') AS DITOLAK,"
				
				+ " (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL))"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ " WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ " AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1,"
				
				+ " (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL))"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ " WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ " AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610201') AS C2,"
				
				+ " (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL))"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ " WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ " AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610202') AS C3,"
				
				+ " (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL))"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ " WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ " AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610203') AS C4,"
				
				+ " (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL))"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ " WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ " AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ")"
				+ " AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610205') AS C5,"
				
				+ "	(SELECT"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199" //JABATAN TEKNIKAL
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0,1)) + 1"
				+ "	-"
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198" //MAKLUMAT PERMOHONAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0'))"
				+ "	+"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM DUAL) + 1"
				+ "	-" 
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198" //MAKLUMAT PERMOHONAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0'))"    
				+ "	FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))"
				+ "	AS D1,"
				
				+ "	(SELECT"
				+ " SUM(NVL(((SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610202" //CETAKAN MINIT KEWANGAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0,1)) + 1"
				+ "	-"
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201" //MESYUARAT
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0'))"
				+ "	+"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM DUAL) + 1"
				+ "	-" 
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201" //MESYUARAT
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0'))"    
				+ "	FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))"
				+ "	AS D2,"
				
				+ "	(SELECT"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610204" //CETAKAN MINIT CERAIAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0,1)) + 1"
				+ "	-"
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610203" //KEMASUKAN MINIT KEWANGAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0'))"
				+ "	+"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM DUAL) + 1"
				+ "	-" 
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610203" //KEMASUKAN MINIT KEWANGAN
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0'))"    
				+ "	FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))"
				+ "	AS D3,"
				
				+ "	(SELECT"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610247" //CETAKAN BORANG 12A/12B
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0,1)) + 1"
				+ "	-"
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205" //KELULUSAN MENTERI
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0'))"
				+ "	+"
				+ "	SUM(NVL(((SELECT TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM DUAL) + 1"
				+ "	-" 
				+ "	(SELECT TO_DATE(TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY')" 
				+ "	FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS"
				+ "	WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS"
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
				+ "	AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205" //KELULUSAN MENTERI
				+ "	AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0'))"    
				+ "	FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))"
				+ "	AS D4"
				
				+ " FROM TBLPHPKPISASARAN WHERE ID_KPISASARAN = '" + idKPISasaran + "'";

			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("idKPISasaran", rs.getString("ID_KPISASARAN") == null ? "" : rs.getString("ID_KPISASARAN"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("idSeksyen", rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				
				h.put("B1", rs.getString("F1") == null ? "0.0" : Utils.format1Decimal(rs.getDouble("F1")));
				h.put("B2", rs.getString("F2") == null ? "0.0" : Utils.format1Decimal(rs.getDouble("F2")));
				h.put("B3", rs.getString("F3") == null ? "0.0" : Utils.format1Decimal(rs.getDouble("F3")));
				h.put("B4", rs.getString("F4") == null ? "0.0" : Utils.format1Decimal(rs.getDouble("F4")));
				
				h.put("AM1", rs.getString("F5") == null ? "0" : rs.getString("F5"));
				h.put("AM2", Integer.parseInt(rs.getString("F5")) + 1);
				h.put("AM3", rs.getString("F6") == null ? "0" : rs.getString("F6"));
				h.put("AM4", Integer.parseInt(rs.getString("F6")) + 1);
				
				h.put("BM1", rs.getString("F7") == null ? "0" : rs.getString("F7"));
				h.put("BM2", Integer.parseInt(rs.getString("F7")) + 1);
				h.put("BM3", rs.getString("F8") == null ? "0" : rs.getString("F8"));
				h.put("BM4", Integer.parseInt(rs.getString("F8")) + 1);
				
				h.put("CM1", rs.getString("F9") == null ? "0" : rs.getString("F9"));
				h.put("CM2", Integer.parseInt(rs.getString("F9")) + 1);
				h.put("CM3", rs.getString("F10") == null ? "0" : rs.getString("F10"));
				h.put("CM4", Integer.parseInt(rs.getString("F10")) + 1);
				
				h.put("DITERIMA", rs.getString("DITERIMA") == null ? "0" : rs.getString("DITERIMA"));
				h.put("DILULUSKAN", rs.getString("DILULUSKAN") == null ? "0" : rs.getString("DILULUSKAN"));
				h.put("DITOLAK", rs.getString("DITOLAK") == null ? "0" : rs.getString("DITOLAK"));
				
				h.put("C1", rs.getString("C1") == null ? "0" : rs.getString("C1"));
				h.put("C2", rs.getString("C2") == null ? "0" : rs.getString("C2"));
				h.put("C3", rs.getString("C3") == null ? "0" : rs.getString("C3"));
				h.put("C4", rs.getString("C4") == null ? "0" : rs.getString("C4"));
				h.put("C5", rs.getString("C5") == null ? "0" : rs.getString("C5"));
				
				h.put("D1", rs.getString("D1") == null ? "0" : rs.getString("D1"));
				h.put("D2", rs.getString("D2") == null ? "0" : rs.getString("D2"));
				h.put("D3", rs.getString("D3") == null ? "0" : rs.getString("D3"));
				h.put("D4", rs.getString("D4") == null ? "0" : rs.getString("D4"));

				if (rs.getInt("C1") > 0)
					E1 = rs.getDouble("D1") / rs.getDouble("C1");				
				if (rs.getInt("C2") > 0)
					E2 = rs.getDouble("D2") / rs.getDouble("C2");				
				if (rs.getInt("C3") > 0)
					E3 = rs.getDouble("D3") / rs.getDouble("C3");
				if (rs.getInt("C4") > 0)
					E4 = rs.getDouble("D4") / rs.getDouble("C4");
					
				h.put("E1", Utils.format1Decimal(E1));
				h.put("E2", Utils.format1Decimal(E2));
				h.put("E3", Utils.format1Decimal(E3));
				h.put("E4", Utils.format1Decimal(E4));
				
				if (E1 > 0D)
					F1 = 100 * rs.getDouble("F1") / E1;
				if (E2 > 0D)
					F2 = 100 * rs.getDouble("F2") / E2;
				if (E3 > 0D)
					F3 = 100 * rs.getDouble("F3") / E3;
				if (E4 > 0D)
					F4 = 100 * rs.getDouble("F4") / E4;
				
				h.put("F1", Utils.format1Decimal(F1));
				h.put("F2", Utils.format1Decimal(F2));
				h.put("F3", Utils.format1Decimal(F3));
				h.put("F4", Utils.format1Decimal(F4));

				beanMaklumatKPIPelepasan.addElement(h);
			} else {
				
				h = new Hashtable();
				h.put("idKPISasaran", "");
				h.put("idNegeri", "");
				h.put("idPejabat", "");
				h.put("idUrusan", "");
				h.put("idSuburusan", "");
				h.put("idSeksyen", "");
				
				h.put("B1", "0.0");
				h.put("B2", "0.0");
				h.put("B3", "0.0");
				h.put("B4", "0.0");
				
				h.put("AM1", "0");
				h.put("AM2", "0");
				h.put("AM3", "0");
				h.put("AM4", "0");
				
				h.put("BM1", "0");
				h.put("BM2", "0");
				h.put("BM3", "0");
				h.put("BM4", "0");
				
				h.put("CM1", "0");
				h.put("CM2", "0");
				h.put("CM3", "0");
				h.put("CM4", "0");
				
				h.put("DITERIMA", "0");
				h.put("DILULUSKAN", "0");
				h.put("DITOLAK", "0");
				
				h.put("C1", "0");
				h.put("C2", "0");
				h.put("C3", "0");
				h.put("C4", "0");
				h.put("C5", "0");
				
				h.put("D1", "0");
				h.put("D2", "0");
				h.put("D3", "0");
				h.put("D4", "0");
				
				h.put("E1", "0.0");
				h.put("E2", "0.0");
				h.put("E3", "0.0");
				h.put("E4", "0.0");
				
				h.put("F1", "0.0");
				h.put("F2", "0.0");
				h.put("F3", "0.0");
				h.put("F4", "0.0");

				beanMaklumatKPIPelepasan.addElement(h);
				
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPISasaran() {
		return beanMaklumatKPISasaran;
	}

	public void setBeanMaklumatKPISasaran(Vector beanMaklumatKPISasaran) {
		this.beanMaklumatKPISasaran = beanMaklumatKPISasaran;
	}

	public Vector getBeanMaklumatKPIPelepasan() {
		return beanMaklumatKPIPelepasan;
	}

	public void setBeanMaklumatKPIPelepasan(Vector beanMaklumatKPIPelepasan) {
		this.beanMaklumatKPIPelepasan = beanMaklumatKPIPelepasan;
	}
}
