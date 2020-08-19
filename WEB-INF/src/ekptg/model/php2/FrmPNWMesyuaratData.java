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

public class FrmPNWMesyuaratData {

	private Vector listMesyuarat = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector beanMaklumatDokumen = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiMesyuarat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

//			r.add("ID_MESYUARAT");
//			r.add("TAJUK");
//			r.add("BIL_MESYUARAT");
//			r.add("TARIKH_MESYUARAT");
//			r.add("FLAG_SYOR");
//			r.add("ID_PERMOHONAN", idPermohonan);
//
//			sql = r.getSQLSelect("TBLPHPMESYUARAT", "ID_MESYUARAT ASC");
			
			sql = "SELECT A.ID_MESYUARAT, A.TAJUK, A.BIL_MESYUARAT, A.TARIKH_MESYUARAT, A.STATUS_MESYUARAT "
					+ " FROM TBLPHPMESYUARAT A, TBLPHPMESYUARATPERMOHONAN B WHERE A.ID_MESYUARAT = B.ID_MESYUARAT"
					+ " AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idMesyuarat", rs.getString("ID_MESYUARAT") == null ? ""
						: rs.getString("ID_MESYUARAT"));
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				h.put("tarikhMesyuarat",
						rs.getDate("TARIKH_MESYUARAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MESYUARAT")));
				if (rs.getString("BIL_MESYUARAT") != null) {
					if ("L".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "LULUS");
					} else if ("T".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "TOLAK");
					} else if ("G".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "TANGGUH");
					} else {
						h.put("syor", "");
					}
				} else {
					h.put("syor", "");
				}

				listMesyuarat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMesyuarat(String idPermohonan,
			String txtTajukMesyuarat, String txtBilMesyuarat,
			String txtTarikhMesyuarat, String idJamDari, String idMinitDari,
			String idJamHingga, String idMinitHingga, String idLokasi,
			String socSyor, String txtCatatan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			long idMesyuarat = DB.getNextID("TBLPHPMESYUARAT_SEQ");
			idMesyuaratString = String.valueOf(idMesyuarat);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("TAJUK", txtTajukMesyuarat);
			r.add("BIL_MESYUARAT", txtBilMesyuarat);
			if (!"".equals(txtTarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + txtTarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", txtCatatan);
			r.add("FLAG_SYOR", socSyor);
			r.add("FLAG_MESYUARAT", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
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
		return idMesyuaratString;
	}

	public void simpanKemaskiniMesyuarat(String idMesyuarat,
			String txtTajukMesyuarat, String txtBilMesyuarat,
			String txtTarikhMesyuarat, String idJamDari, String idMinitDari,
			String idJamHingga, String idMinitHingga, String idLokasi,
			String socSyor, String txtCatatan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			r.update("ID_MESYUARAT", idMesyuarat);
			r.add("TAJUK", txtTajukMesyuarat);
			r.add("BIL_MESYUARAT", txtBilMesyuarat);
			if (!"".equals(txtTarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + txtTarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", txtCatatan);
			r.add("FLAG_SYOR", socSyor);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + idMesyuarat
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

	public void hapusMesyuarat(String idMesyuarat, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			r = new SQLRenderer();
			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLDelete("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "DEL",
					"FAIL PENAWARAN [" + idMesyuarat
							+ "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
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

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610206"); // CETAKAN SURAT KEPUTUSAN

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610206")); // CETAKAN
																				// SURAT
																				// KEPUTUSAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610212")); // BATAL
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

	public void setMaklumatMesyuarat(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK, BIL_MESYUARAT, TARIKH_MESYUARAT, JAM_DARI, MINIT_DARI, JAM_HINGGA, MINIT_HINGGA, ID_LOKASI, CATATAN, FLAG_SYOR"
					+ " FROM TBLPHPMESYUARAT WHERE ID_MESYUARAT = '"
					+ idMesyuarat + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				h.put("tarikhMesyuarat",
						rs.getDate("TARIKH_MESYUARAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MESYUARAT")));
				h.put("idLokasi", rs.getString("ID_LOKASI") == null ? "99999"
						: rs.getString("ID_LOKASI"));
				h.put("idJamDari", rs.getString("JAM_DARI") == null ? "99999"
						: rs.getString("JAM_DARI"));
				h.put("idMinitDari",
						rs.getString("MINIT_DARI") == null ? "99999" : rs
								.getString("MINIT_DARI"));
				h.put("idJamHingga",
						rs.getString("JAM_HINGGA") == null ? "99999" : rs
								.getString("JAM_HINGGA"));
				h.put("idMinitHingga",
						rs.getString("MINIT_HINGGA") == null ? "99999" : rs
								.getString("MINIT_HINGGA"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("flagSyor",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				beanMaklumatMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanDokumen(String idMesyuarat, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "INS",
					"FAIL PENAWARAN [" + idMesyuarat
							+ "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatDokumen(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatDokumen = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_MESYUARAT = '"
					+ idMesyuarat + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? ""
						: rs.getString("NAMA_FAIL"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatDokumen.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListMesyuarat() {
		return listMesyuarat;
	}

	public void setListMesyuarat(Vector listMesyuarat) {
		this.listMesyuarat = listMesyuarat;
	}

	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}

	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}
	
	public Vector getBeanMaklumatDokumen() {
		return beanMaklumatDokumen;
	}

	public void setBeanMaklumatDokumen(Vector beanMaklumatDokumen) {
		this.beanMaklumatDokumen = beanMaklumatDokumen;
	}
}
