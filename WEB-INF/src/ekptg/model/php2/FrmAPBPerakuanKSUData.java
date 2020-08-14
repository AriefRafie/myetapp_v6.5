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

public class FrmAPBPerakuanKSUData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector getMaklumatPerakuanKeputusan(String idPermohonan) throws Exception {
		Vector beanMaklumatPerakuanKeputusan = null;
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerakuanKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_KERTASKERJAAPB , A.TARIKH_HANTAR_KSU, A.NAMA_KSU, A.TARIKH_PERAKUAN_KSU, A.FLAG_PERAKUAN_KSU,"
					+ " A.TARIKH_HANTAR_MENTERI, A.NAMA_MENTERI, A.TARIKH_KEPUTUSAN, A.FLAG_KEPUTUSAN, A.ULASAN_KEPUTUSAN,"
					+ " A.SYOR_JABATAN"
					+ " FROM TBLPHPKERTASKERJAAPB A "
					+ " WHERE A.FLAG_KERTAS = '2' AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idKertasKerjaApb",
						rs.getString("ID_KERTASKERJAAPB") == null ? "" : rs
								.getString("ID_KERTASKERJAAPB"));
				h.put("tarikhHantarKSU",
						rs.getDate("TARIKH_HANTAR_KSU") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTAR_KSU")));
				h.put("namaKSU",
						rs.getString("NAMA_KSU") == null ? "" : rs
								.getString("NAMA_KSU"));
				h.put("tarikhPerakuanKSU",
						rs.getDate("TARIKH_PERAKUAN_KSU") == null ? "" : sdf
								.format(rs.getDate("TARIKH_PERAKUAN_KSU")));				
				h.put("flagPerakuanKSU",
						rs.getString("FLAG_PERAKUAN_KSU") == null ? "" : rs
								.getString("FLAG_PERAKUAN_KSU"));	
				h.put("tarikhHantarMenteri",
						rs.getDate("TARIKH_HANTAR_MENTERI") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTAR_MENTERI")));
				h.put("namaMenteri",
						rs.getString("NAMA_MENTERI") == null ? "" : rs
								.getString("NAMA_MENTERI"));
				h.put("tarikhKeputusanMenteri",
						rs.getDate("TARIKH_KEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("flagKeputusanMenteri",
						rs.getString("FLAG_KEPUTUSAN") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN"));	
				h.put("ulasanMenteri",
						rs.getString("ULASAN_KEPUTUSAN") == null ? "" : rs
								.getString("ULASAN_KEPUTUSAN"));
				h.put("syorJabatan",
						rs.getString("SYOR_JABATAN") == null ? "" : rs
								.getString("SYOR_JABATAN"));
				beanMaklumatPerakuanKeputusan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatPerakuanKeputusan;
	}

	public void updateMaklumatPerakuanKSU(String idPermohonan,
			String idPerakuan, String txtTarikhPerakuan, String txtNamaKSU, String txtTarikhHantarKSU,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TP = "to_date('" + txtTarikhPerakuan + "','dd/MM/yyyy')";
			String TH = "to_date('" + txtTarikhHantarKSU + "','dd/MM/yyyy')";

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_PERAKUAN_KSU", idPerakuan);
			r.add("TARIKH_PERAKUAN_KSU", r.unquote(TP));
			r.add("TARIKH_HANTAR_KSU", r.unquote(TH));
			r.add("NAMA_KSU", txtNamaKSU);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610205", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

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
	}

	public void updateMaklumatKeputusan(String idPermohonan,
			String idKeputusan, String txtTarikhKeputusan,
			String txtNamaMenteri, String txtUlasanKeputusan, String txtTarikhHantarMenteri,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TK = "to_date('" + txtTarikhKeputusan + "','dd/MM/yyyy')";
			String TH = "to_date('" + txtTarikhHantarMenteri + "','dd/MM/yyyy')";

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KEPUTUSAN", idKeputusan);
			r.add("NAMA_MENTERI", txtNamaMenteri);
			r.add("ULASAN_KEPUTUSAN", txtUlasanKeputusan);
			r.add("TARIKH_KEPUTUSAN", r.unquote(TK));
			r.add("TARIKH_HANTAR_MENTERI", r.unquote(TH));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610205", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

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
	}

	public void updateStatus(String idFail, String idPermohonan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String keputusanMenteri = "";
		String syor = "";
		String kelulusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT FLAG_KEPUTUSAN, SYOR_JABATAN FROM TBLPHPKERTASKERJAAPB WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				keputusanMenteri = rs.getString("FLAG_KEPUTUSAN");
				syor = rs.getString("SYOR_JABATAN");
			}	
			
			if ("S".equals(keputusanMenteri)){
				if ("L".equals(syor))
					kelulusan = "L";
				if ("D".equals(syor))
					kelulusan = "L";
				if ("T".equals(syor))
					kelulusan = "T";
				if ("G".equals(syor))
					kelulusan = "T";
			}
			if ("T".equals(keputusanMenteri)){
				if ("L".equals(syor))
					kelulusan = "T";
				if ("D".equals(syor))
					kelulusan = "T";
				if ("T".equals(syor))
					kelulusan = "L";
				if ("G".equals(syor))
					kelulusan = "L";
			}			

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			if ("L".equals(kelulusan)) {
				r.add("ID_STATUS", "1615198"); // KEPUTUSAN PERTIMBANGAN DASAR				
			} else {
				r.add("ID_STATUS", "1610208"); // TOLAK
			}			

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");
			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
				.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			if ("L".equals(kelulusan)) {
				r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1615198")); // KEPUTUSAN PERTIMBANGAN DASAR
			} else {
				r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610208")); // TOLAK
			}	
			
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			if ("L".equals(kelulusan)) {
				// TBLPHPBYRNSYRTKLLSNLESENAPB
				r = new SQLRenderer();
				long id = DB.getNextID("TBLPHPBYRNSYRTKLLSN_SEQ");
				r.add("ID_BYRNSYRTKLLSNLESENAPB", id);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_AKTIF", "Y");

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPBYRNSYRTKLLSNLESENAPB");
				stmt.executeUpdate(sql);
			}
			
			conn.commit();
			
			if ("L".equals(kelulusan)) {
				AuditTrail.logActivity("1615198", "4", null, session, "UPD",
						"FAIL [" + idPermohonan + "] PROSES SETERUSNYA"); // KEPUTUSAN PERTIMBANGAN DASAR				
			} else {
				AuditTrail.logActivity("1610208", "4", null, session, "UPD",
						"FAIL [" + idPermohonan + "] PROSES SETERUSNYA"); // TOLAK
			}	

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
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '"
					+ idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = true;
		String keputusan = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_KEPUTUSAN, SYOR_PTP FROM TBLPHPKERTASKERJAAPB WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				keputusan = rs.getString("FLAG_KEPUTUSAN");
			}
			
			if (keputusan.trim().length() > 0)
				bool = false;

		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
	
	public boolean checkSyor(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = true;
		String syor = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SYOR_JABATAN FROM TBLPHPKERTASKERJAAPB WHERE"
					+ " FLAG_KERTAS = '2' AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				syor = rs.getString("SYOR_JABATAN");
			}
			
			if (syor.trim().length() > 0)
				bool = false;

		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
}
