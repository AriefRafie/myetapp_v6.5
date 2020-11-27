package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmAPBPopupSenaraiPermohonanData {

	private Vector senaraiFailMesyuarat = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	//FrmPYWSenaraiMesyuaratView parent=new FrmPYWSenaraiMesyuaratView();
	
	public void carianFail(String noFail, String idJenisPermohonan, String namaPemohon) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFailMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL,  A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON,D.ID_JENISPERMOHONAN "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPMOHONNJDUALPERTAMA D "
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "
					+ "AND A.NO_FAIL IS NOT NULL  AND B.ID_STATUS = '1610201' AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN)";

			if (noFail != "") {
				sql = sql + " AND A.NO_FAIL LIKE '%"+noFail+ "%'";
			}
			
			if (idJenisPermohonan != null) {
				if (!idJenisPermohonan.trim().equals("")
						&& !idJenisPermohonan.trim().equals("99999")) {
					sql = sql + " AND D.ID_JENISPERMOHONAN = '"
							+ idJenisPermohonan.trim() + "'";
				}
			}
			
			if (namaPemohon != "") {
				sql = sql + " AND C.NAMA LIKE '%"+namaPemohon+ "%'";
			}
			
			sql = sql + " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				if("1".equals(rs.getString("ID_JENISPERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN BAHARU");
				} else if ("2".equals(rs.getString("ID_JENISPERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN PERLANJUTAN");
				}
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailMesyuarat.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiFailMesyuarat(String idFail) throws Exception {
		
		Db db = null;
		String sql = "";

		try {
			senaraiFailMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL,  A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C "
				+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON "
				+ "AND A.NO_FAIL IS NOT NULL  AND B.ID_STATUS = '1610201' AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN WHERE FLAG_JENIS_PERMOHONAN = 'B')";
			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiFailMesyuarat() {
		return senaraiFailMesyuarat;
	}
	
	//public String simpanPilihanBaru(String idMesyuarat,String idPermohonan,HttpSession session) throws Exception {
	public void simpanPilihanBaru(String idMesyuarat,String idPermohonan,HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//INSERT
			long idMesyuaratPermohonan = DB.getNextID("TBLPHPMESYUARATPERMOHONAN_SEQ");
			r.add("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENIS_PERMOHONAN", "B");
			r.add("FLAG_MESYUARAT_SEMULA", "0");
			r.add("FLAG_SELESAI_MESYUARAT", "0");
			r.add("FLAG_AKTIF", "1");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"MESYUARAT [" + idMesyuarat
							+ "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
		//return idMesyuaratString;
	}
	
	public void simpanPilihanLanjutan(String idMesyuarat,String idPermohonan,HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//INSERT
			long idMesyuaratPermohonan = DB.getNextID("TBLPHPMESYUARATPERMOHONAN_SEQ");
			r.add("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENIS_PERMOHONAN", "L");
			r.add("FLAG_MESYUARAT_SEMULA", "0");
			r.add("FLAG_SELESAI_MESYUARAT", "0");
			r.add("FLAG_AKTIF", "1");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"MESYUARAT [" + idMesyuarat
							+ "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
		//return idMesyuaratString;
	}
	public String getJenisPermohonan(String idPermohonan)
			throws Exception {

		Db db = null;
		String sql = "";
		String jenisPermohonan = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT A.ID_FAIL,  A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON,D.ID_JENISPERMOHONAN "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPMOHONNJDUALPERTAMA D "
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "
					+ "AND A.NO_FAIL IS NOT NULL  AND B.ID_STATUS = '1610201' "
					//+ "AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN)";
					+ " AND B.ID_PERMOHONAN='"+idPermohonan+"'";

			sql = sql + " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				jenisPermohonan=rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs.
						getString("ID_JENISPERMOHONAN");
			}
		} finally {
			if (db != null)
				db.close();
		}
		return jenisPermohonan;
	}
}
