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
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

/**
 * 
 *
 */
public class FrmTKRMesyuaratEPUData {

	private Vector beanMaklumatMesyuarat = null;
	private HakmilikInterface iHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatMesyuarat(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK, BIL_MESYUARAT, TARIKH_MESYUARAT, JAM_DARI, MINIT_DARI, JAM_HINGGA, MINIT_HINGGA, CATATAN, FLAG_SYOR"
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

	public String getIdMesyuaratEPU(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_MESYUARAT FROM TBLPHPMESYUARAT WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "' AND FLAG_MESYUARAT = '2'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_MESYUARAT").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniMesyuarat(String idMesyuarat,
			String txtTajukMesyuarat, String txtBilMesyuarat,
			String txtTarikhMesyuarat, String idJamDari, String idMinitDari,
			String idJamHingga, String idMinitHingga, String socSyor,
			String txtCatatan, String idStatus, HttpSession session)
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
			r.add("CATATAN", txtCatatan);
			if ("1610191".equals(idStatus)) {
				r.add("FLAG_SYOR", socSyor);
			}

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

	public void updateStatus(String idFail, String idPermohonan,
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
			if ("L".equals(idKeputusan)) {
				r.add("ID_STATUS", "1610207"); // LULUS
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
						getIdSuburusanstatus("33", "1610207")); // LULUS
			} else if ("T".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("33", "1610208")); // TOLAK
			}

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// UPDATE TBLHTPHAKMILIKAGENSI WHEN LULUS
			if ("L".equals(idKeputusan)) {

				String catatanHTP = "";
				catatanHTP = getTajukFail(idFail);
				String idHakmilikAgensi = getIdHakmilikAgensiByIdPermohonan(idPermohonan);
				String idHakmilik = getIdHakmilik(idHakmilikAgensi);
				Double luasMohon = getLuasMohon(idPermohonan);
				Double luasBaki = getLuasBaki(idPermohonan);

				// DEACTIVATE ROW TBLHTPHAKMILIKAGENSI
				deActivateHakmilikAgensi(idHakmilikAgensi, db, userId);

				// GET ROW HTPHAKMILIKAGENSI
				if (luasBaki != 0D) {
					Hashtable hashAgensi = getMaklumatAgensiByIdHakmilikAgensi(idHakmilikAgensi);
					if (hashAgensi != null) {
						// TBLHTPHAKMILIKAGENSI
						insertHakmilikAgensi(idHakmilik,
								(String) hashAgensi.get("idKementerian"),
								(String) hashAgensi.get("idAgensi"), luasBaki,
								db, userId);
					}
				}

				// TODO FOR PEMOHON IS JKPTG ITSELF
				// GENERATE ROW FOR KJP BARU YANG DILULUSKAN (PEMOHON)
				String catatanAgensi = "";
				if ("3".equals(getIdKategoriPemohon(idPermohonan))) {

					String idKementerian = getIdKementerian(idPermohonan);
					String idAgensi = getIdAgensi(idPermohonan);
					String idHakmilikAgensiExist = getIdAgensiExist(idHakmilik,
							idAgensi);

					if ("".equals(idHakmilikAgensiExist)) {
						insertHakmilikAgensi(idHakmilik, idKementerian,
								idAgensi, luasMohon, db, userId);
					} else {
						updateHakmilikAgensi(idHakmilikAgensiExist, luasMohon,
								db, userId);
					}

					catatanAgensi = getNamaKementerian(idKementerian) + " "
							+ getNamaAgensi(idAgensi) + " "
							+ Utils.formatLuas(luasMohon) + "H";

					// GET ROW TBLPHPTANAHGANTIPLPSN
					Vector listTG = null;
					listTG = getSenaraiTanahGanti(idPermohonan);
					if (listTG.size() != 0) {
						for (int i = 0; i < listTG.size(); i++) {
							Hashtable hashTG = (Hashtable) listTG.get(i);
							String idTanahGanti = "";
							if (hashTG.get("ID_TANAHGANTI") != null)
								idTanahGanti = (String) hashTG
										.get("ID_TANAHGANTI");
							String idHakmilikAgensiTG = "";
							if (hashTG.get("ID_HAKMILIKAGENSI") != null)
								idHakmilikAgensiTG = (String) hashTG
										.get("ID_HAKMILIKAGENSI");

							String idHakmilikTG = getIdHakmilik(idHakmilikAgensiTG);
							Double luasBeriTG = getLuasBeriTG(idTanahGanti);
							Double luasBakiTG = getLuasBakiTG(idTanahGanti);

							// DEACTIVATE ROW TBLHTPHAKMILIKAGENSI
							deActivateHakmilikAgensi(idHakmilikAgensiTG, db,
									userId);

							// GET ROW HTPHAKMILIKAGENSI
							if (luasBakiTG != 0D) {
								Hashtable hashAgensi = getMaklumatAgensiByIdHakmilikAgensi(idHakmilikAgensiTG);
								if (hashAgensi != null) {
									// TBLHTPHAKMILIKAGENSI
									insertHakmilikAgensi(
											idHakmilikTG,
											(String) hashAgensi
													.get("idKementerian"),
											(String) hashAgensi.get("idAgensi"),
											luasBakiTG, db, userId);
								}
							}

							String idKementerianKJP = getIdKementerianKJP(idPermohonan);
							String idAgensiKJP = getIdAgensiKJP(idPermohonan);
							String idHakmilikAgensiExistTG = getIdAgensiExist(
									idHakmilikTG, idAgensiKJP);

							if ("".equals(idHakmilikAgensiExistTG)) {
								insertHakmilikAgensi(idHakmilikTG,
										idKementerianKJP, idAgensiKJP,
										luasBeriTG, db, userId);
							} else {
								updateHakmilikAgensi(idHakmilikAgensiExistTG,
										luasBeriTG, db, userId);
							}
						}
					}
				}

				catatanHTP = catatanHTP + " (" + catatanAgensi + ")";
				getHakmilik().kemaskiniHakmilikCatatan(idHakmilik, catatanHTP);

			}

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

	private String getIdSuburusanstatus(String idSuburusan, String idStatus)
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

	private Double getLuasMohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_MHNBERSAMAAN FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_MHNBERSAMAAN");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasBaki(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BAKI FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BAKI");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void deActivateHakmilikAgensi(String idHakmilikAgensi, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPHAKMILIKAGENSI
			r = new SQLRenderer();
			r.update("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private Hashtable getMaklumatAgensiByIdHakmilikAgensi(
			String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable hash = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK, ID_KEMENTERIAN, ID_AGENSI, ID_LUAS, LUAS, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			if (rs.next()) {
				hash = new Hashtable();
				hash.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				hash.put(
						"idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				hash.put("idAgensi", rs.getString("ID_AGENSI") == null ? ""
						: rs.getString("ID_AGENSI"));
				hash.put(
						"idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				hash.put(
						"luas",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				hash.put(
						"idLuasBersamaan",
						rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs
								.getString("ID_LUAS_BERSAMAAN"));
				hash.put(
						"luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return hash;
	}

	private void insertHakmilikAgensi(String idHakmilik, String idKementerian,
			String idAgensi, Double luas, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPHAKMILIKAGENSI
			r = new SQLRenderer();
			long idHakmilikAgensiInsert = DB
					.getNextID("TBLHTPHAKMILIKAGENSI_SEQ");
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensiInsert);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_LUAS", "2");
			r.add("LUAS", Utils.formatLuas(luas));
			r.add("ID_LUAS_BERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", Utils.formatLuas(luas));
			r.add("STATUS", "T");
			r.add("FLAG_AKTIF", "Y");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private String getIdKategoriPemohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KATEGORIPEMOHON FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KATEGORIPEMOHON");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdKementerian(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KEMENTERIAN FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AGENSI FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensiExist(String idHakmilik, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIKAGENSI WHERE FLAG_AKTIF = 'Y' AND ID_HAKMILIK = '"
					+ idHakmilik + "'" + " AND ID_AGENSI = '" + idAgensi + "'";

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

	private void updateHakmilikAgensi(String idHakmilikAgensi, Double luas,
			Db db, String userId) throws Exception {

		String sql = "";
		Double totalLuas = 0D + luas;

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				totalLuas = totalLuas + rs.getDouble("LUAS_BERSAMAAN");
			}

			// TBLHTPHAKMILIKAGENSI
			r.update("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("LUAS", Utils.formatLuas(totalLuas));
			r.add("LUAS_BERSAMAAN", Utils.formatLuas(totalLuas));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public String getNamaKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNamaAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_AGENSI FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Vector getSenaraiTanahGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Vector listTanahGanti = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_TANAHGANTI, ID_HAKMILIKAGENSI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_TANAHGANTI",
						rs.getString("ID_TANAHGANTI") == null ? "" : rs
								.getString("ID_TANAHGANTI"));
				h.put("ID_HAKMILIKAGENSI",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				listTanahGanti.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listTanahGanti;
	}

	private Double getLuasBeriTG(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BERSAMAAN FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BERSAMAAN");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasBakiTG(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BAKI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BAKI");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdKementerianKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KEMENTERIAN FROM TBLHTPHAKMILIKAGENSI A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensiKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AGENSI FROM TBLHTPHAKMILIKAGENSI A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}

	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}

	public HakmilikInterface getHakmilik() {
		if (iHakmilik == null) {
			iHakmilik = new HakmilikBean();
		}
		return iHakmilik;
	}
}
