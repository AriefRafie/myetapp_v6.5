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
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmPLPKeputusanData {

	private Vector beanMaklumatKeputusan = null;
	private Vector beanMaklumatTarikh = null;
	private HakmilikInterface iHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void simpanMaklumatKeputusan(String idPermohonan, String idPampasan, String txtPampasan, 
			String txtTarikhTerima, String socKeputusan, String txtUlasan, HttpSession session) throws Exception {

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

			// TBLPHPPERMOHONANPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);

			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKHTERIMA_PTD",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("KEPUTUSAN_PTD", socKeputusan);
			r.add("ULASAN_PTD", txtUlasan);
			
			/*if (!"".equals(txtTarikhKeputusan)) {
				r.add("TARIKH_HANTARKEPUTUSAN",
						r.unquote("to_date('" + txtTarikhKeputusan
								+ "','dd/MM/yyyy')"));
			}
			
			  if ("L".equals(idKeputusan)){ r.add("FLAG_PAMPASAN", idPampasan);
			  r.add("PAMPASAN", Utils.RemoveSymbol(txtPampasan)); }
			 */

			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1617200", "4", null, session, "UPD",
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

	// simpan maklumat tarikh - asna
	public void simpanMaklumatTarikh(String idPermohonan, String idKeputusan,
			String txtTarikhHantar, String txtTarikhLulus, HttpSession session)
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

			// TBLPHPPERMOHONANPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);

			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR_PTG",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_HANTAR_PTG", "");
			}

			if (!"".equals(txtTarikhLulus)) {
				r.add("TARIKH_TERIMA_HAKMILIK",
						r.unquote("to_date('" + txtTarikhLulus
								+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_HAKMILIK", "");
			}
			/*
			 * if ("L".equals(idKeputusan)){ r.add("FLAG_PAMPASAN", idPampasan);
			 * r.add("PAMPASAN", Utils.RemoveSymbol(txtPampasan)); }
			 */

			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1614197", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DISIMPAN"); 

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

	public void setMaklumatKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ULASAN_PTD, TARIKHTERIMA_PTD, KEPUTUSAN_PTD, KEPUTUSAN, TARIKH_HANTARKEPUTUSAN, FLAG_PAMPASAN, PAMPASAN, JENIS_PAMPASAN, CATATAN_PAMPASAN FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_HANTARKEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				h.put("flagPampasan",
						rs.getString("FLAG_PAMPASAN") == null ? "" : rs
								.getString("FLAG_PAMPASAN"));
				h.put("pampasan",
						rs.getString("PAMPASAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("PAMPASAN")));
				h.put("jenis",
						rs.getString("JENIS_PAMPASAN") == null ? "" : rs
								.getString("JENIS_PAMPASAN"));
				h.put("catatan", rs.getString("CATATAN_PAMPASAN") == null ? ""
						: rs.getString("CATATAN_PAMPASAN"));
				h.put("keputusan", rs.getString("KEPUTUSAN_PTD") == null ? ""
						: rs.getString("KEPUTUSAN_PTD"));
				h.put("tarikhTerima",
						rs.getDate("TARIKHTERIMA_PTD") == null ? "" : sdf
								.format(rs.getDate("TARIKHTERIMA_PTD")));
				h.put("ulasan", rs.getString("ULASAN_PTD") == null ? ""
						: rs.getString("ULASAN_PTD"));
				beanMaklumatKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTarikh(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTarikh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_HANTAR_PTG, TARIKH_TERIMA_HAKMILIK FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhHantar",
						rs.getDate("TARIKH_HANTAR_PTG") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTAR_PTG")));
				h.put("tarikhLulus",
						rs.getDate("TARIKH_TERIMA_HAKMILIK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_HAKMILIK")));
				beanMaklumatTarikh.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatusBorang(String idFail, String idPermohonan,
			String idKeputusan, HttpSession session) throws Exception {
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
			if ("S".equals(idKeputusan)) {
				r.add("ID_STATUS", "1614197"); // CETAK BORANG 12A/12B
			} else if ("TS".equals(idKeputusan)) {
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
			if ("S".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("34", "1614197")); // CETAK BORANG
																// 12A/12B
			} else if ("TS".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("34", "1610208")); // TOLAK
			}
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// CHECK ID HASIL & ID AKAUN WUJUD (DELETE DATA)- ASNA
			sql = "SELECT ID_HASIL FROM TBLPHPHASIL "
					+ "WHERE ID_PEMOHON = (SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "')";

			ResultSet rsCheck = stmt.executeQuery(sql);
			if (rsCheck.next()) {

				sql = "DELETE FROM TBLPHPAKAUN WHERE ID_HASIL IN (SELECT ID_HASIL FROM TBLPHPHASIL "
						+ "WHERE ID_PEMOHON = (SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
						+ idPermohonan + "'))";
				stmt.executeUpdate(sql);

				sql = "DELETE FROM TBLPHPHASIL "
						+ "WHERE ID_PEMOHON = (SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
						+ idPermohonan + "')";
				stmt.executeUpdate(sql);
			}

			// if keputusan tolak update table htp
			if ("T".equals(idKeputusan) && getFlagBorangK(idFail)) {
				String catatanHTP = "";
				catatanHTP = getTajukFail(idFail);
				String idHakmilikAgensi = getIdHakmilikAgensiByIdPermohonan(idPermohonan);
				String idHakmilik = getIdHakmilik(idHakmilikAgensi);

				// TBLHTPHAKMILIK
				r = new SQLRenderer();
				r.update("ID_HAKMILIK", idHakmilik);

				r.add("TARIKH_MOHON_PELEPASAN", "");
				r.add("STATUS_PELEPASAN", "");
				// UPDATE CATATAN
				r.add("CATATAN", "");

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIK");
				stmt.executeUpdate(sql);
			}

			conn.commit();
			
			if ("S".equals(idKeputusan)) {
				AuditTrail.logActivity("1614197", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] STATUS DIKEMASKINI"); // CETAK BORANG 12A/12B
			} else if ("TS".equals(idKeputusan)) {
				AuditTrail.logActivity("1610208", "4", null, session, "UPD",
						"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
						+ "] STATUS DIKEMASKINI"); // TOLAK
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

	public void updateStatus(String idFail, String idPermohonan,
			String idKeputusan, String idPampasan, String pampasan,
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

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610207"); // LULUS

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610207")); // LULUS

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			if ("L".equals(idKeputusan) && getFlagBorangK(idFail)) {

				String catatanHTP = "";
				catatanHTP = getTajukFail(idFail);
				String idHakmilikAgensi = getIdHakmilikAgensiByIdPermohonan(idPermohonan);
				String idHakmilik = getIdHakmilik(idHakmilikAgensi);

				// TBLHTPHAKMILIK
				r = new SQLRenderer();
				r.update("ID_HAKMILIK", idHakmilik);

				r.add("TARIKH_MOHON_PELEPASAN", r.unquote("SYSDATE"));
				r.add("STATUS_PELEPASAN", "Y");
				// UPDATE CATATAN
				r.add("CATATAN", catatanHTP);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIK");
				System.out.println("cecking sql = " + sql);
				stmt.executeUpdate(sql);

				if ((idPampasan.equals("2") || idPampasan.equals("3"))
						&& !pampasan.equals(null)) {
					generateFailHasil(idFail, idPermohonan, pampasan, db,
							session, userId);
				}

				// //UPDATE CATATAN
				// getHakmilik().kemaskiniHakmilikCatatan(idHakmilik,
				// catatanHTP);
			}

			conn.commit();
			
			AuditTrail.logActivity("1610207", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] STATUS LULUS"); // LULUS

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

	private boolean getFlagBorangK(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_BORANGK FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if ("Y".equals(rs.getString("FLAG_BORANGK"))) {
					bool = false;
				} else {
					bool = true;
				}
			}

		} finally {
			if (db != null)
				db.close();
		}

		return bool;
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

	private String getTajukFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("TAJUK_FAIL");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdHakmilikAgensiByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdHakmilik(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIK");
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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void generateFailHasil(String idFail, String idPermohonan,
			String pampasan, Db db, HttpSession session, String userId)
			throws Exception {

		String sql = "";
		String sqlSelect = "";
		String noFail = "";
		String idPemohon = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// GET ID_PEMOHON FROM FAIL LAMA
			sqlSelect = "SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			System.out.println("get id pemohon=" + sql);
			ResultSet rsPermohonan = stmt.executeQuery(sqlSelect);
			if (rsPermohonan.next()) {
				idPemohon = rsPermohonan.getString("ID_PEMOHON");
			}

			// TBLPHPHASIL
			r = new SQLRenderer();
			long idHasil = DB.getNextID("TBLPHPHASIL_SEQ");
			r.add("ID_HASIL", idHasil);
			r.add("ID_FAIL", idFail);
			r.add("ID_FAILPERMOHONAN", idFail);
			r.add("ID_PEMOHON", idPemohon);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHASIL");
			stmt.executeUpdate(sql);

			// TBLPHPAKAUN - DEPOSIT
			r = new SQLRenderer();
			long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_HASIL", idHasil);
			r.add("TARIKH", r.unquote("SYSDATE"));
			r.add("DEBIT", Utils.RemoveSymbol(pampasan));
			r.add("ID_JENISBAYARAN", "34"); // BAYARAN HASIL PAMPASAN PELEPASAN
			r.add("CATATAN", "BAYARAN HASIL PAMPASAN PELEPASAN ");
			r.add("ID_JENISTRANSAKSI", "1"); // CAJ

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			System.out.println("cek sql insert=" + sql);
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public Vector getBeanMaklumatKeputusan() {
		return beanMaklumatKeputusan;
	}

	public void setBeanMaklumatKeputusan(Vector beanMaklumatKeputusan) {
		this.beanMaklumatKeputusan = beanMaklumatKeputusan;
	}

	public HakmilikInterface getHakmilik() {
		if (iHakmilik == null) {
			iHakmilik = new HakmilikBean();
		}
		return iHakmilik;
	}

	public Vector getBeanMaklumatTarikh() {
		return beanMaklumatTarikh;
	}

	public void setBeanMaklumatTarikh(Vector beanMaklumatTarikh) {
		this.beanMaklumatTarikh = beanMaklumatTarikh;
	}
}
