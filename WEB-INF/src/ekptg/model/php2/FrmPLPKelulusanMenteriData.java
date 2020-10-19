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

public class FrmPLPKelulusanMenteriData {

	private Vector beanKelulusanMenteri = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKelulusanMenteri(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKelulusanMenteri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KERTASKERJA, TARIKH_TERIMA_MENTERI, KEPUTUSAN_MENTERI, ULASAN_MENTERI,FLAG_KEPUTUSAN_PEMOHON, ULASAN_PEMOHON "
				+ "FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '3' AND ID_PERMOHONAN = '"
				+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertaskerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA_MENTERI") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_MENTERI")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN_MENTERI") == null ? "" : rs
								.getString("KEPUTUSAN_MENTERI"));
				h.put("ulasan", rs.getString("ULASAN_MENTERI") == null ? ""
						: rs.getString("ULASAN_MENTERI"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				h.put("ulasanPemohon", rs.getString("ULASAN_PEMOHON") == null ? ""
						: rs.getString("ULASAN_PEMOHON"));
				beanKelulusanMenteri.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniKelulusanMenteri(String idPermohonan,
			String txtTarikhTerima, String socKeputusan, String txtUlasan,
			String idStatus, String socKeputusanPemohon, String txtUlasanPemohon, HttpSession session) throws Exception {

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

			// TBLPHPKERTASKERJAPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "3");

			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA_MENTERI",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("KEPUTUSAN_MENTERI", socKeputusan);
			r.add("ULASAN_MENTERI", txtUlasan);
			r.add("FLAG_KEPUTUSAN_PEMOHON", socKeputusanPemohon);
			r.add("ULASAN_PEMOHON", txtUlasanPemohon);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("KEPUTUSAN", socKeputusan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

//			// UPDATE ID_STATUS TBLPERMOHONAN - ASNA
//			r = new SQLRenderer();
//			r.update("ID_PERMOHONAN", idPermohonan);
//			r.add("ID_STATUS", idStatus);
//
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//
//			sql = r.getSQLUpdate("TBLPERMOHONAN");
//			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610205", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIKEMASKINI");

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
	
	public Vector getBeanMaklumatLampiran(String idKertaskerja)
			throws Exception {
		Db db = null;
		String sql = "";
		Vector beanMaklumatLampiran = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"
					+ idKertaskerja + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				beanMaklumatLampiran = new Vector();
				h = new Hashtable();
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("idKertaskerja",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				beanMaklumatLampiran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

		return beanMaklumatLampiran;
	}

	public void updateStatus(String idFail, String idPermohonan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String keputusanMenteri = "";
		String idStatus = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT KEPUTUSAN_MENTERI FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '3' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				keputusanMenteri = rs.getString("KEPUTUSAN_MENTERI");
				if ("L".equals(keputusanMenteri)) {
					idStatus = "1614197"; //PENYEDIAAN BORANG 12A/12B
				} else if ("B".equals(keputusanMenteri)) {
					idStatus = "1617200"; //KEPUTUSAN PTG/PTD
				} else {
					idStatus = "1610208"; //TOLAK
				}
			}

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", idStatus);

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", idStatus));
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			if ("L".equals(keputusanMenteri)) {
				AuditTrail.logActivity("1614197", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES SETERUSNYA"); //PENYEDIAAN BORANG 12A/12B
			} else if ("B".equals(keputusanMenteri)) {
				AuditTrail.logActivity("1617200", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES SETERUSNYA"); //KEPUTUSAN PTG/PTD
			} else {
				AuditTrail.logActivity("1610208", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES SETERUSNYA"); //TOLAK
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
	
	public void pindaKeputusan(String idFail, String idPermohonan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String keputusanMenteri = "";
		String idStatus = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT KEPUTUSAN_MENTERI FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '3' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				keputusanMenteri = rs.getString("KEPUTUSAN_MENTERI");
				if ("L".equals(keputusanMenteri)) {
					idStatus = "1614197"; //PENYEDIAAN BORANG 12A/12B
				} else if ("B".equals(keputusanMenteri)) {
					idStatus = "1617200"; //KEPUTUSAN PTG/PTD
				} else {
					idStatus = "1610208"; //TOLAK
				}
			}

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", idStatus);

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", idStatus));
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			if ("L".equals(keputusanMenteri)) {
				AuditTrail.logActivity("1614197", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] DIPINDA"); //PENYEDIAAN BORANG 12A/12B
			} else if ("B".equals(keputusanMenteri)) {
				AuditTrail.logActivity("1617200", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
						+ "] DIPINDA"); //KEPUTUSAN PTG/PTD
			} else {
				AuditTrail.logActivity("1610208", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
						+ "] DIPINDA"); //TOLAK
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

	public void doBatalPermohonan(String idFail, String idPermohonan,
			String tarikhBatal, String txtSebab, HttpSession session)
			throws Exception {
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

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610212");
			r.add("TARIKH_BATAL",
					r.unquote("to_date('" + tarikhBatal + "','dd/MM/yyyy')"));
			r.add("CATATAN_BATAL", txtSebab);

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610212", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIBATALKAN");

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

	public String getNoFailByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL FROM TBLPFDFAIL, TBLPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NO_FAIL");
			} else {
				return "";
			}

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

	public Vector getBeanKelulusanMenteri() {
		return beanKelulusanMenteri;
	}

	public void setBeanKelulusanMenteri(Vector beanKelulusanMenteri) {
		this.beanKelulusanMenteri = beanKelulusanMenteri;
	}
}
