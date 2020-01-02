/**
 * 
 */
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
import ekptg.helpers.DB;

/**
 * 
 *
 */
public class FrmPYWPenamatanPerjanjianData {

	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatKertasMakluman = null;
	private Vector listMesyuarat = null;
	private Vector beanMaklumatMesyuarat = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			Hashtable h;

			sql = "SELECT TARIKH_TERIMA_TAMAT, TARIKH_SURAT_TAMAT, NO_RUJ_SURAT_TAMAT, FLAG_SEBAB_TAMAT, CATATAN_TAMAT"
					+ " FROM TBLPHPPERMOHONANSEWA WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA_TAMAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_TAMAT")));
				h.put("tarikhSurat",
						rs.getDate("TARIKH_SURAT_TAMAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_TAMAT")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT_TAMAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT_TAMAT"));
				h.put("flagSebabTamat",
						rs.getString("FLAG_SEBAB_TAMAT") == null ? "" : rs
								.getString("FLAG_SEBAB_TAMAT"));
				h.put("catatan", rs.getString("CATATAN_TAMAT") == null ? ""
						: rs.getString("CATATAN_TAMAT"));

				beanMaklumatPermohonan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void daftarPenamatan(String idFail, String idPermohonan,
			String idSuburusan, String tarikhTerima, String tarikhSurat,
			String txtNoRujukanSurat, String socFlagSebabTamat,
			String txtCatatan, HttpSession session) throws Exception {

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
			r.add("ID_STATUS", "1610221"); // PERMOHONAN PENAMATAN PERJANJIAN

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA_TAMAT",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT_TAMAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT_TAMAT", txtNoRujukanSurat);
			r.add("FLAG_SEBAB_TAMAT", socFlagSebabTamat);
			r.add("CATATAN_TAMAT", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			// TBLPHPKERTASKERJAPENYEWAAN
			r = new SQLRenderer();
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAPENYEWAAN_SEQ");
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KERTAS", "2");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610221")); // PERMOHONAN
																	// PENAMATAN
																	// PERJANJIAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public void simpanKemaskiniMaklumatPenamatan(String idPermohonan,
			String tarikhTerima, String tarikhSurat, String txtNoRujukanSurat,
			String socFlagSebabTamat, String txtCatatan, String idStatus,
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
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA_TAMAT",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT_TAMAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT_TAMAT", txtNoRujukanSurat);
			if ("1610221".equals(idStatus)) {
				r.add("FLAG_SEBAB_TAMAT", socFlagSebabTamat);
			}
			r.add("CATATAN_TAMAT", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public void simpanKemaskiniKertasMakluman(String idPermohonan,
			String txtUlasan, String txtSediaOleh, String idJawatan,
			String tarikhSedia, HttpSession session) throws Exception {

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

			// TBLPHPKERTASKERJAPENYEWAAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "2");
			r.add("ULASAN_PENAMATAN", txtUlasan);
			r.add("SEDIA_OLEH", txtSediaOleh);
			r.add("ID_JAWATAN", idJawatan);
			if (!"".equals(tarikhSedia)) {
				r.add("TARIKH_SEDIA", r.unquote("to_date('" + tarikhSedia
						+ "','dd/MM/yyyy')"));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public void setMaklumatKertasMakluman(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKertasMakluman = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			Hashtable h;

			sql = "SELECT ULASAN_PENAMATAN, SEDIA_OLEH, ID_JAWATAN, TARIKH_SEDIA"
					+ " FROM TBLPHPKERTASKERJAPENYEWAAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("ulasan", rs.getString("ULASAN_PENAMATAN") == null ? ""
						: rs.getString("ULASAN_PENAMATAN"));
				h.put("sediaOleh",
						rs.getString("SEDIA_OLEH") == null ? "" : rs
								.getString("SEDIA_OLEH"));
				h.put("idJawatan", rs.getString("ID_JAWATAN") == null ? "99999"
						: rs.getString("ID_JAWATAN"));
				h.put("tarikhSedia", rs.getDate("TARIKH_SEDIA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SEDIA")));

				beanMaklumatKertasMakluman.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiMesyuarat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MESYUARAT");
			r.add("TAJUK");
			r.add("BIL_MESYUARAT");
			r.add("TARIKH_MESYUARAT");
			r.add("FLAG_SYOR");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_MESYUARAT", "2");

			sql = r.getSQLSelect("TBLPHPMESYUARAT", "ID_MESYUARAT ASC");
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
			r.add("FLAG_MESYUARAT", "2");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public void hapusMesyuarat(String idMesyuarat) throws Exception {

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

	public void selesai(String idFail, String idPermohonan, String idSuburusan,
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
			r.add("ID_STATUS", "1610222"); // PERJANJIAN TAMAT

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
					getIdSuburusanstatus(idSuburusan, "1610222")); // PERJANJIAN
																	// TAMAT
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatKertasMakluman() {
		return beanMaklumatKertasMakluman;
	}

	public void setBeanMaklumatKertasMakluman(Vector beanMaklumatKertasMakluman) {
		this.beanMaklumatKertasMakluman = beanMaklumatKertasMakluman;
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
}
