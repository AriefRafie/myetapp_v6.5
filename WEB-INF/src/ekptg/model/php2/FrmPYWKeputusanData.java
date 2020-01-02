/**
 * 
 */
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * 
 *
 */
public class FrmPYWKeputusanData {

	private Vector beanMaklumatKeputusan = null;
	private Vector beanMaklumatPerjanjian = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void simpanMaklumatKeputusan(String idPermohonan,
			String idKeputusan, String txtTarikhHantar, String idPerjanjian,
			String txtTarikhMulaDasar, String txtTarikhTamatDasar,
			String txtTarikhMula, String txtTempoh, String txtTarikhTamat,
			String txtKadarSewa, String txtRoyalti, String txtCagaran,
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

			// TBLPHPPERMOHONANSEWA
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("KEPUTUSAN", idKeputusan);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTARKEPUTUSAN",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}

			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			if ("L".equals(idKeputusan) || "D".equals(idKeputusan)) {
				if ("".equals(idPerjanjian)) {
					insertPerjanjian(idPermohonan, txtTarikhMulaDasar,
							txtTarikhTamatDasar, txtTarikhMula, txtTempoh,
							txtTarikhTamat, txtKadarSewa, txtRoyalti,
							txtCagaran, db, userId);
				} else {
					updatePerjanjian(idPerjanjian, txtTarikhMulaDasar,
							txtTarikhTamatDasar, txtTarikhMula, txtTempoh,
							txtTarikhTamat, txtKadarSewa, txtRoyalti,
							txtCagaran, db, userId);
				}
			} else {

				// TBLPHPPERJANJIAN
				r = new SQLRenderer();
				r.add("ID_PERJANJIAN", idPerjanjian);

				sql = r.getSQLDelete("TBLPHPPERJANJIAN");
				stmt.executeUpdate(sql);

				// TBLPHPMAKLUMBALAS
				r = new SQLRenderer();
				r.add("ID_PERJANJIAN", idPerjanjian);

				sql = r.getSQLDelete("TBLPHPMAKLUMBALAS");
				stmt.executeUpdate(sql);
			}

			conn.commit();
			
			AuditTrail.logActivity("1610206", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
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
	}

	private void insertPerjanjian(String idPermohonan,
			String txtTarikhMulaDasar, String txtTarikhTamatDasar,
			String txtTarikhMula, String txtTempoh, String txtTarikhTamat,
			String txtKadarSewa, String txtRoyalti, String txtCagaran, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERJANJIAN
			r = new SQLRenderer();
			long idPerjanjian = DB.getNextID("TBLPHPPERJANJIAN_SEQ");
			r.add("ID_PERJANJIAN", idPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(txtTarikhMulaDasar)) {
				r.add("TARIKH_MULA_DASAR",
						r.unquote("to_date('" + txtTarikhMulaDasar
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhTamatDasar)) {
				r.add("TARIKH_TAMAT_DASAR",
						r.unquote("to_date('" + txtTarikhTamatDasar
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", txtTempoh);
			if (!"".equals(txtTarikhTamat)) {
				r.add("TARIKH_TAMAT_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhTamat
								+ "','dd/MM/yyyy')"));
			}
			r.add("KADAR_SEWA", Utils.RemoveSymbol(txtKadarSewa));
			r.add("ROYALTI", Utils.RemoveSymbol(txtRoyalti));
			r.add("CAGARAN", Utils.RemoveSymbol(txtCagaran));
			r.add("FLAG_PERJANJIAN", "U");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERJANJIAN");
			stmt.executeUpdate(sql);

			// TBLPHPMAKLUMBALAS
			r = new SQLRenderer();
			long idMaklumbalas = DB.getNextID("TBLPHPMAKLUMBALAS_SEQ");
			r.add("ID_MAKLUMBALAS", idMaklumbalas);
			r.add("ID_PERJANJIAN", idPerjanjian);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMAKLUMBALAS");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void updatePerjanjian(String idPerjanjian,
			String txtTarikhMulaDasar, String txtTarikhTamatDasar,
			String txtTarikhMula, String txtTempoh, String txtTarikhTamat,
			String txtKadarSewa, String txtRoyalti, String txtCagaran, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERJANJIAN
			r = new SQLRenderer();
			r.update("ID_PERJANJIAN", idPerjanjian);
			if (!"".equals(txtTarikhMulaDasar)) {
				r.add("TARIKH_MULA_DASAR",
						r.unquote("to_date('" + txtTarikhMulaDasar
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhTamatDasar)) {
				r.add("TARIKH_TAMAT_DASAR",
						r.unquote("to_date('" + txtTarikhTamatDasar
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", txtTempoh);
			if (!"".equals(txtTarikhTamat)) {
				r.add("TARIKH_TAMAT_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhTamat
								+ "','dd/MM/yyyy')"));
			}
			r.add("KADAR_SEWA", Utils.RemoveSymbol(txtKadarSewa));
			r.add("ROYALTI", Utils.RemoveSymbol(txtRoyalti));
			r.add("CAGARAN", Utils.RemoveSymbol(txtCagaran));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERJANJIAN");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public void setMaklumatKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KEPUTUSAN, TARIKH_HANTARKEPUTUSAN FROM TBLPHPPERMOHONANSEWA"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("keputusan",
						rs.getString("KEPUTUSAN") == null ? "" : rs
								.getString("KEPUTUSAN"));
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_HANTARKEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				beanMaklumatKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			String idKeputusan, String idSuburusan, HttpSession session)
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
			if (("L".equals(idKeputusan)) || ("D".equals(idKeputusan))) {
				r.add("ID_STATUS", "1610214"); // PERJANJIAN
			} else if ("T".equals(idKeputusan)) {
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
			if ("L".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus(idSuburusan, "1610214")); // PERJANJIAN
			} else if ("T".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus(idSuburusan, "1610208")); // TOLAK
			}

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// //TODO GENERATE FAIL HASIL
			 if (validateGenerateFailHasil(idPermohonan, idSuburusan, db)){
				 generateFailHasil(idFail, idPermohonan, db, session, userId);
			 }

			conn.commit();
			
			if (("L".equals(idKeputusan)) || ("D".equals(idKeputusan))) {
				AuditTrail.logActivity("1610214", "4", null, session, "INS",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES SETERUSNYA"); // PERJANJIAN
			} else if ("T".equals(idKeputusan)) {
				AuditTrail.logActivity("1610208", "4", null, session, "INS",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES SETERUSNYA"); // TOLAK
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
			String idSuburusan, String tarikhBatal, String txtSebab,
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
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610212")); // BATAL
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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdPerjanjianByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "' AND FLAG_PERJANJIAN = 'U'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_PERJANJIAN").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPerjanjian(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_MULA_PERJANJIAN, TEMPOH, TARIKH_TAMAT_PERJANJIAN, KADAR_SEWA, ROYALTI, CAGARAN, TARIKH_MULA_DASAR, TARIKH_TAMAT_DASAR FROM TBLPHPPERJANJIAN"
					+ " WHERE ID_PERJANJIAN = '" + idPerjanjian + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("tarikhMulaDasar",
						rs.getDate("TARIKH_MULA_DASAR") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_DASAR")));
				h.put("tarikhTamatDasar",
						rs.getDate("TARIKH_TAMAT_DASAR") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TAMAT_DASAR")));
				h.put("tarikhMula",
						rs.getDate("TARIKH_MULA_PERJANJIAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
				h.put("tempoh",
						rs.getString("TEMPOH") == null ? "" : rs
								.getString("TEMPOH"));
				h.put("tarikhTamat",
						rs.getDate("TARIKH_TAMAT_PERJANJIAN") == null ? ""
								: sdf.format(rs
										.getDate("TARIKH_TAMAT_PERJANJIAN")));
				h.put("kadarSewa", rs.getString("KADAR_SEWA") == null ? ""
						: Utils.format2Decimal(rs.getDouble("KADAR_SEWA")));
				h.put("royalti",
						rs.getString("ROYALTI") == null ? "" : Utils
								.format2Decimal(rs.getDouble("ROYALTI")));
				h.put("cagaran",
						rs.getString("CAGARAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("CAGARAN")));
				beanMaklumatPerjanjian.addElement(h);
			} else {
				h = new Hashtable();
				h.put("tarikhMula", "");
				h.put("tempoh", "");
				h.put("tarikhTamat", "");
				h.put("kadarSewa", "");
				h.put("cagaran", "");
				beanMaklumatPerjanjian.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private boolean validateGenerateFailHasil(String idPermohonan,
			String idSuburusan, Db db) throws SQLException, Exception {
		Statement stmt = db.getStatement();
		String sqlSelect = "";
		boolean bool = true;

		if ("35".equals(idSuburusan)) {
			bool = false;

			sqlSelect = "SELECT TARIKH_MULA_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'U' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rsPerjanjian = stmt.executeQuery(sqlSelect);

			if (rsPerjanjian.next()) {

				Calendar calMula = new GregorianCalendar();
				Date dateMula = sdf.parse(sdf.format(rsPerjanjian
						.getDate("TARIKH_MULA_PERJANJIAN")));
				calMula.setTime(dateMula);

				Calendar calLimit = new GregorianCalendar();
				calLimit.set(2012, 0, 1); // LIMIT - 01/01/2012

				if (calMula.before(calLimit)) {
					bool = true;
				}
			}
		}

		return bool;
	}

	public void generateFailHasil(String idFailPenyewaan, String idPermohonan,
			Db db, HttpSession session, String userId) throws Exception {

		String sql = "";
		String sqlSelect = "";
		String sqlCheckExistFailHasil = "";
		String noFail = "";
		String idPemohon = "";
		boolean failHasilExist = false;

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// GET DATA LAMA FROM TBLPFDFAIL
			sqlSelect = "SELECT NO_FAIL, TAJUK_FAIL, ID_KEMENTERIAN, ID_NEGERI, ID_SUBURUSAN FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFailPenyewaan + "'";
			ResultSet rsFail = stmt.executeQuery(sqlSelect);

			if (rsFail.next()) {
				String tajukFail = rsFail.getString("TAJUK_FAIL");
				String idNegeri = rsFail.getString("ID_NEGERI");
				String idKementerian = rsFail.getString("ID_KEMENTERIAN");
				String idSuburusan = rsFail.getString("ID_SUBURUSAN");
				long idFail = 0;
				long idHasil = 0;
				noFail = rsFail.getString("NO_FAIL");
				if (noFail.contains("JKPTG/SPHP/931/")) {
					noFail = noFail.replaceAll("JKPTG/SPHP/931/",
							"JKPTG/SPHP/359/");
				}
				if (noFail.contains("JKPTG/SPHP/909/")) {
					noFail = noFail.replaceAll("JKPTG/SPHP/909/",
							"JKPTG/SPHP/359/");
				}

				// CHECK JIKA FAIL HASIL PERNAH WUJUD
				sqlCheckExistFailHasil = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, HASIL.ID_HASIL FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND FAIL.NO_FAIL = '"
						+ noFail + "'";
				ResultSet rsFailHasil = stmt
						.executeQuery(sqlCheckExistFailHasil);
				if (rsFailHasil.next()) {
					failHasilExist = true;
					idFail = rsFailHasil.getLong("ID_FAIL");
					idHasil = rsFailHasil.getLong("ID_HASIL");
				}
				if (failHasilExist) {
					// TBLPFDFAIL
					r = new SQLRenderer();
					r.update("ID_FAIL", idFail);
					r.add("ID_URUSAN", "115"); // HASIL
					r.add("ID_SUBURUSAN", idSuburusan);
					r.add("ID_TARAFKESELAMATAN", "1");
					r.add("ID_SEKSYEN", "4");
					r.add("FLAG_FAIL", "1");
					r.add("TAJUK_FAIL", tajukFail);
					r.add("NO_FAIL", noFail);
					r.add("NO_FAIL_ROOT", noFail);
					r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
					r.add("FLAG_JENIS_FAIL", "1"); // DATA BARU ETAPP
					r.add("ID_NEGERI", idNegeri);
					r.add("ID_KEMENTERIAN", idKementerian);

					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPFDFAIL");
					stmt.executeUpdate(sql);

				} else {
					// TBLPFDFAIL
					idFail = DB.getNextID("TBLPFDFAIL_SEQ");
					r = new SQLRenderer();
					r.add("ID_FAIL", idFail);
					r.add("ID_URUSAN", "115"); // HASIL
					r.add("ID_SUBURUSAN", idSuburusan);
					r.add("ID_TARAFKESELAMATAN", "1");
					r.add("ID_SEKSYEN", "4");
					r.add("FLAG_FAIL", "1");
					r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
					r.add("TAJUK_FAIL", tajukFail);
					r.add("NO_FAIL", noFail);
					r.add("NO_FAIL_ROOT", noFail);
					r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
					r.add("FLAG_JENIS_FAIL", "1"); // DATA BARU ETAPP
					r.add("ID_NEGERI", idNegeri);
					r.add("ID_KEMENTERIAN", idKementerian);

					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPFDFAIL");
					stmt.executeUpdate(sql);
				}

				// GET ID_PEMOHON FROM FAIL LAMA
				sqlSelect = "SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
						+ idPermohonan + "'";
				ResultSet rsPermohonan = stmt.executeQuery(sqlSelect);
				if (rsPermohonan.next()) {
					idPemohon = rsPermohonan.getString("ID_PEMOHON");
				}

				if (failHasilExist) {
					// TBLPHPHASIL
					r = new SQLRenderer();
					r.update("ID_HASIL", idHasil);
					r.add("ID_FAIL", idFail);
					r.add("ID_FAILPERMOHONAN", idFailPenyewaan);
					r.add("ID_PEMOHON", idPemohon);

					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPHPHASIL");
					stmt.executeUpdate(sql);

				} else {
					// TBLPHPHASIL
					r = new SQLRenderer();
					idHasil = DB.getNextID("TBLPHPHASIL_SEQ");
					r.add("ID_HASIL", idHasil);
					r.add("ID_FAIL", idFail);
					r.add("ID_FAILPERMOHONAN", idFailPenyewaan);
					r.add("ID_PEMOHON", idPemohon);

					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPHPHASIL");
					stmt.executeUpdate(sql);
				}

				// GET MAKLUMAT PERJANJIAN
				sqlSelect = "SELECT NO_SIRI, TARIKH_MULA_PERJANJIAN, TARIKH_TAMAT_PERJANJIAN, KADAR_SEWA, CAGARAN FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'U' AND ID_PERMOHONAN = '"
						+ idPermohonan + "'";
				ResultSet rsPerjanjian = stmt.executeQuery(sqlSelect);

				if (rsPerjanjian.next()) {
					String noSiri = rsPerjanjian.getString("NO_SIRI");
					String bayaran = rsPerjanjian.getString("KADAR_SEWA");
					String deposit = rsPerjanjian.getString("CAGARAN");
					Date tarikhMula = new Date();
					Date tarikhTamat = new Date();
					tarikhMula = rsPerjanjian.getDate("TARIKH_MULA_PERJANJIAN");
					tarikhTamat = rsPerjanjian
							.getDate("TARIKH_TAMAT_PERJANJIAN");

					// TBLPHPBAYARANPERLUDIBAYAR
					r = new SQLRenderer();
					r.update("ID_HASIL", idHasil);
					r.add("FLAG_AKTIF", "T");

					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPHPBAYARANPERLUDIBAYAR");
					stmt.executeUpdate(sql);

					// TBLPHPBAYARANPERLUDIBAYAR
					r = new SQLRenderer();
					long idBayaranPerluDibayar = DB
							.getNextID("TBLPHPBAYARANPERLUDIBAYAR_SEQ");
					r.add("ID_BAYARANPERLUDIBAYAR", idBayaranPerluDibayar);
					r.add("ID_HASIL", idHasil);
					r.add("ID_PERMOHONAN", idPermohonan);
					if (noSiri != null && noSiri.length() > 0)
						r.add("NO_RUJUKAN", noSiri);

					if (!"".equals(tarikhMula)) {
						r.add("TARIKH_MULA",
								r.unquote("to_date('" + sdf.format(tarikhMula)
										+ "','dd/MM/yyyy')"));
					}
					if (!"".equals(tarikhTamat)) {
						r.add("TARIKH_TAMAT",
								r.unquote("to_date('" + sdf.format(tarikhTamat)
										+ "','dd/MM/yyyy')"));
					}

					r.add("BAYARAN", bayaran);
					r.add("DEPOSIT", deposit);
					r.add("FLAG_AKTIF", "Y");

					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPHPBAYARANPERLUDIBAYAR");
					stmt.executeUpdate(sql);

					// GENERATE AKAUN
					generateAkaun(idHasil, noSiri, tarikhMula, tarikhTamat,
							bayaran, deposit, db, session, userId);
				}
			}

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public void generateAkaun(long idHasil, String noSiri, Date tarikhMula,
			Date tarikhTamat, String kadarSewa, String deposit, Db db,
			HttpSession session, String userId) throws Exception {

		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Calendar calCurrent = new GregorianCalendar();
			Date dateCurrent = new Date();
			calCurrent.setTime(dateCurrent);

			Calendar calMula = new GregorianCalendar();
			Date dateMula = sdf.parse(sdf.format(tarikhMula));
			calMula.setTime(dateMula);

			Calendar calTamat = new GregorianCalendar();
			Date dateTamat = sdf.parse(sdf.format(tarikhTamat));
			calTamat.setTime(dateTamat);

			while (calMula.getTime().before(calTamat.getTime())) {

				if (calMula.getTime().before(calCurrent.getTime())) {
					// TBLPHPAKAUN
					r = new SQLRenderer();
					long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
					r.add("ID_AKAUN", idAkaun);
					r.add("ID_HASIL", idHasil);
					r.add("TARIKH",
							r.unquote("to_date('"
									+ sdf.format(calMula.getTime())
									+ "','dd/MM/yyyy')"));
					r.add("DEBIT", kadarSewa);
					r.add("ID_JENISBAYARAN", "10"); // BAYARAN SEWA

					if (calMula.get(Calendar.MONTH) == 0) {
						r.add("CATATAN",
								"SEWA BULAN JANUARI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 1) {
						r.add("CATATAN",
								"SEWA BULAN FEBRUARI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 2) {
						r.add("CATATAN",
								"SEWA BULAN MAC " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 3) {
						r.add("CATATAN",
								"SEWA BULAN APRIL "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 4) {
						r.add("CATATAN",
								"SEWA BULAN MEI " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 5) {
						r.add("CATATAN",
								"SEWA BULAN JUN " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 6) {
						r.add("CATATAN",
								"SEWA BULAN JULAI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 7) {
						r.add("CATATAN",
								"SEWA BULAN OGOS " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 8) {
						r.add("CATATAN",
								"SEWA BULAN SEPTEMBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 9) {
						r.add("CATATAN",
								"SEWA BULAN OKTOBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 10) {
						r.add("CATATAN",
								"SEWA BULAN NOVEMBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 11) {
						r.add("CATATAN",
								"SEWA BULAN DISEMBER "
										+ calMula.get(Calendar.YEAR));
					} else {
						r.add("CATATAN", "");
					}
					r.add("ID_JENISTRANSAKSI", "1"); // CAJ

					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPHPAKAUN");
					stmt.executeUpdate(sql);

					calMula.add(Calendar.MONTH, 1);
				} else {
					System.out.println("break");
					break;
				}
			}

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

	public Vector getBeanMaklumatPerjanjian() {
		return beanMaklumatPerjanjian;
	}

	public void setBeanMaklumatPerjanjian(Vector beanMaklumatPerjanjian) {
		this.beanMaklumatPerjanjian = beanMaklumatPerjanjian;
	}
}
