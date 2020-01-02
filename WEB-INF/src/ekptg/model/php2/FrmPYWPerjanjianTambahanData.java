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

/**
 * 
 *
 */
public class FrmPYWPerjanjianTambahanData {

	private Vector listPerjanjianTambahan = null;
	private Vector beanMaklumatPerjanjianUtama = null;
	private Vector beanMaklumatSewa = null;
	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatJPPH = null;
	private Vector listJPPH = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector listMesyuarat = null;
	private Vector beanMaklumatKeputusan = null;
	private Vector beanMaklumatPerjanjian = null;
	private Vector beanHeaderPerjanjianTambahan = null;
	private Vector beanMaklumatMaklumbalas = null;
	private Vector beanMaklumatPermohonan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJ_SURAT, E.FLAG_JENISPERJANJIAN, E.TUJUAN, E.CATATAN, E.TARIKH_MULAPERJANJIAN,"
					+ " E.FLAG_GUNA, C.LUAS_BERSAMAAN, F.KETERANGAN, E.ID_LUASMHN, E.LUAS_MHN1, E.LUAS_MHN2, E.LUAS_MHN3, E.LUAS_MHNBERSAMAAN, E.LUAS_BAKI"
					+ " FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B, TBLHTPHAKMILIKAGENSI C, TBLHTPHAKMILIK D, TBLPHPPERMOHONANSEWA E, TBLRUJLUAS F"
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_HAKMILIKAGENSI = C.ID_HAKMILIKAGENSI AND C.ID_LUAS_BERSAMAAN = F.ID_LUAS"
					+ " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT"));
				h.put("flagJenisPerjanjian",
						rs.getString("FLAG_JENISPERJANJIAN") == null ? "" : rs
								.getString("FLAG_JENISPERJANJIAN"));
				h.put("tujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("tarikhMulaPerjanjian",
						rs.getDate("TARIKH_MULAPERJANJIAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULAPERJANJIAN")));
				h.put("flagGuna", rs.getString("FLAG_GUNA") == null ? "99999"
						: rs.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_BERSAMAAN") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN")));
				h.put("keteranganLuasAsal",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? ""
						: rs.getString("ID_LUASMHN"));
				h.put("luas1",
						rs.getString("LUAS_MHN1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2",
						rs.getString("LUAS_MHN2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3",
						rs.getString("LUAS_MHN3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan",
						rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				beanMaklumatPermohonan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setHeaderPerjanjianTambahan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanHeaderPerjanjianTambahan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_STATUS, D.ID_HAKMILIK, E.KEPUTUSAN, E.FLAG_JENISPERJANJIAN,"
					+ " A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJ_SURAT, F.KETERANGAN AS STATUS, E.CATATAN"
					+ " FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B, TBLHTPHAKMILIKAGENSI C, TBLHTPHAKMILIK D, TBLPHPPERMOHONANSEWA E, TBLRUJSTATUS F"
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_HAKMILIKAGENSI = C.ID_HAKMILIKAGENSI AND  A.ID_STATUS = F.ID_STATUS"
					+ " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanPerjanjianTambahan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idStatusPerjanjianTambahan",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("flagJenisPerjanjian",
						rs.getString("FLAG_JENISPERJANJIAN") == null ? "" : rs
								.getString("FLAG_JENISPERJANJIAN"));
				h.put("idHakmilikPerjanjianTambahan",
						rs.getString("ID_HAKMILIK") == null ? "" : rs
								.getString("ID_HAKMILIK"));
				h.put("idKeputusan", rs.getString("KEPUTUSAN") == null ? ""
						: rs.getString("KEPUTUSAN"));
				h.put("tarikhTerima",
						rs.getString("TARIKH_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getString("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujSurat", rs.getString("NO_RUJ_SURAT") == null ? ""
						: rs.getString("NO_RUJ_SURAT"));
				h.put("status",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanHeaderPerjanjianTambahan.addElement(h);

			} else {
				h = new Hashtable();
				h.put("idPermohonanPerjanjianTambahan", "");
				h.put("idStatusPerjanjianTambahan", "");
				h.put("flagJenisPerjanjian", "");
				h.put("idHakmilikPerjanjianTambahan", "");
				h.put("idKeputusan", "");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("noRujSurat", "");
				h.put("status", "");
				h.put("catatan", "");
				beanHeaderPerjanjianTambahan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPerjanjianTambahan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			listPerjanjianTambahan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_STATUS, B.FLAG_JENISPERJANJIAN, A.TARIKH_TERIMA, A.TARIKH_SURAT, C.KETERANGAN"
					+ " FROM TBLPERMOHONAN A, TBLPHPPERMOHONANSEWA B, TBLRUJSTATUS C"
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_STATUS = C.ID_STATUS AND A.FLAG_PERJANJIAN = 'T'"
					+ " AND A.ID_FAIL = '"
					+ idFail
					+ "' ORDER BY A.TARIKH_TERIMA DESC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanPerjanjianTambahan",
						rs.getString("ID_PERMOHONAN"));
				h.put("idStatusPerjanjianTambahan",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				if (rs.getString("FLAG_JENISPERJANJIAN") != null
						&& !"".equals(rs.getString("FLAG_JENISPERJANJIAN"))) {
					if ("1".equals(rs.getString("FLAG_JENISPERJANJIAN"))) {
						h.put("jenisPerjanjianTambahan",
								"PERUBAHAN TUJUAN PENYEWAAN");
					} else if ("2".equals(rs.getString("FLAG_JENISPERJANJIAN"))) {
						h.put("jenisPerjanjianTambahan",
								"PERUBAHAN TARIKH MULA PERJANJIAN");
					} else if ("3".equals(rs.getString("FLAG_JENISPERJANJIAN"))) {
						h.put("jenisPerjanjianTambahan",
								"PERUBAHAN LUAS PENYEWAAN");
					} else if ("4".equals(rs.getString("FLAG_JENISPERJANJIAN"))) {
						h.put("jenisPerjanjianTambahan", "RAYUAN KADAR SEWA");
					} else {
						h.put("jenisPerjanjianTambahan", "");
					}
				} else {
					h.put("jenisPerjanjianTambahan", "");
				}
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				listPerjanjianTambahan.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String daftarBaru(String idFail, String tarikhTerima,
			String tarikhSurat, String txtNoRujukanSurat,
			String socFlagJenisPerjanjian, String txtTujuan,
			String tarikhMulaPerjanjian, String idLuasKegunaan,
			String txtLuasAsal, String idLuas, String txtLuasMohon1,
			String txtLuasMohon2, String txtLuasMohon3,
			String txtLuasBersamaan, String txtBakiLuas, String txtCatatan,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPermohonanString = "";
		String idPemohon = "";
		String idPermohonanLama = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_PEMOHON FROM TBLPERMOHONAN A, TBLPFDFAIL B WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()) {
				idPemohon = rsPermohonan.getString("ID_PEMOHON");
				idPermohonanLama = rsPermohonan.getString("ID_PERMOHONAN");
			}

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			idPermohonanString = String.valueOf(idPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "1610198");
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT", txtNoRujukanSurat);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PERJANJIAN", "T");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			copyTBLPHPHAKMILIKPERMOHONAN(idPermohonanLama, idPermohonan, db,
					userId);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			long idPHPPermohonanSewa = DB.getNextID("TBLPHPPERMOHONANSEWA_SEQ");
			r.add("ID_PHPPERMOHONANSEWA", idPHPPermohonanSewa);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISPERJANJIAN", socFlagJenisPerjanjian);
			if ("1".equals(socFlagJenisPerjanjian)) {
				r.add("TUJUAN", txtTujuan);
			}
			if ("2".equals(socFlagJenisPerjanjian)) {
				if (!"".equals(tarikhMulaPerjanjian)) {
					r.add("TARIKH_MULAPERJANJIAN",
							r.unquote("to_date('" + tarikhMulaPerjanjian
									+ "','dd/MM/yyyy')"));
				}
			}
			if ("3".equals(socFlagJenisPerjanjian)) {
				r.add("FLAG_GUNA", idLuasKegunaan);
				r.add("ID_LUASASAL", "2");
				r.add("LUAS_ASAL", txtLuasAsal);
				r.add("ID_LUASMHN", idLuas);
				r.add("LUAS_MHN1", txtLuasMohon1);
				r.add("LUAS_MHN2", txtLuasMohon2);
				r.add("LUAS_MHN3", txtLuasMohon3);
				r.add("ID_LUASMHNBERSAMAAN", "2");
				r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
				r.add("ID_LUASBAKI", "2");
				r.add("LUAS_BAKI", txtBakiLuas);
			}
			r.add("CATATAN", txtCatatan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERMOHONANSEWA");
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
		return idPermohonanString;
	}

	public void simpanKemaskiniMaklumatPermohonan(String idPermohonan,
			String tarikhTerima, String tarikhSurat, String txtNoRujukanSurat,
			String flagJenisPerjanjian, String txtTujuan,
			String tarikhMulaPerjanjian, String idLuasKegunaan,
			String txtLuasAsal, String idLuas, String txtLuasMohon1,
			String txtLuasMohon2, String txtLuasMohon3,
			String txtLuasBersamaan, String txtBakiLuas, String txtCatatan,
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
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT", txtNoRujukanSurat);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISPERJANJIAN", flagJenisPerjanjian);
			if ("1".equals(flagJenisPerjanjian)) {
				r.add("TUJUAN", txtTujuan);
			}
			if ("2".equals(flagJenisPerjanjian)) {
				if (!"".equals(tarikhMulaPerjanjian)) {
					r.add("TARIKH_MULAPERJANJIAN",
							r.unquote("to_date('" + tarikhMulaPerjanjian
									+ "','dd/MM/yyyy')"));
				}
			}
			if ("3".equals(flagJenisPerjanjian)) {
				r.add("FLAG_GUNA", idLuasKegunaan);
				r.add("ID_LUASASAL", "2");
				r.add("LUAS_ASAL", txtLuasAsal);
				r.add("ID_LUASMHN", idLuas);
				r.add("LUAS_MHN1", txtLuasMohon1);
				r.add("LUAS_MHN2", txtLuasMohon2);
				r.add("LUAS_MHN3", txtLuasMohon3);
				r.add("ID_LUASMHNBERSAMAAN", "2");
				r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
				r.add("ID_LUASBAKI", "2");
				r.add("LUAS_BAKI", txtBakiLuas);
			}
			r.add("CATATAN", txtCatatan);

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

	private void copyTBLPHPHAKMILIKPERMOHONAN(String idPermohonanLama,
			long idPermohonan, Db db, String userId) throws Exception {

		String sql = "";
		String idHakmilikPermohonanLama = "";
		try {
			Statement stmt = db.getStatement();

			// TBLPHPHAKMILIKPERMOHONAN
			sql = "SELECT ID_HAKMILIKPERMOHONAN FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				idHakmilikPermohonanLama = rs
						.getString("ID_HAKMILIKPERMOHONAN");
				long idHakmilikPermohonan = DB
						.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
				insertTBLPHPHAKMILIKPERMOHONAN(idHakmilikPermohonan,
						idPermohonan, idHakmilikPermohonanLama, db, userId);
			}

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void insertTBLPHPHAKMILIKPERMOHONAN(long idHakmilikPermohonan,
			long idPermohonan, String idHakmilikPermohonanLama, Db db,
			String userId) throws Exception {

		String sql = "";

		try {
			Statement stmt = db.getStatement();

			// TBLPHPHAKMILIKPERMOHONAN
			sql = "INSERT INTO TBLPHPHAKMILIKPERMOHONAN"
					+ " (ID_HAKMILIKPERMOHONAN, ID_PERMOHONAN, ID_MASUK,"
					+ " TARIKH_MASUK, ID_HAKMILIKAGENSI, NILAIAN)"

					+ " SELECT "
					+ idHakmilikPermohonan
					+ ", "
					+ idPermohonan
					+ ", "
					+ userId
					+ ","
					+ " SYSDATE, ID_HAKMILIKAGENSI, NILAIAN"
					+ " FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonanLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public void setMaklumatPerjanjianUtama(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPerjanjianUtama = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_MULA_PERJANJIAN, TEMPOH, TARIKH_TAMAT_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhMulaPerjanjianUtama",
						rs.getDate("TARIKH_MULA_PERJANJIAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
				h.put("tempohPerjanjianUtama",
						rs.getString("TEMPOH") == null ? "" : rs
								.getString("TEMPOH"));
				h.put("tarikhTamatPerjanjianUtama",
						rs.getDate("TARIKH_TAMAT_PERJANJIAN") == null ? ""
								: sdf.format(rs
										.getDate("TARIKH_TAMAT_PERJANJIAN")));
				beanMaklumatPerjanjianUtama.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatSewa(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSewa = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TUJUAN, A.ID_LUASASAL, A.LUAS_ASAL, B.KETERANGAN, A.FLAG_GUNA, A.ID_LUASMHNBERSAMAAN, A.LUAS_MHNBERSAMAAN"
					+ " FROM TBLPHPPERMOHONANSEWA A, TBLRUJLUAS B"
					+ " WHERE A.ID_LUASASAL = B.ID_LUAS(+) AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("keteranganLuasAsal",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("idLuasMohonBersamaan",
						rs.getString("ID_LUASMHNBERSAMAAN") == null ? "" : rs
								.getString("ID_LUASMHNBERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				beanMaklumatSewa.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		String idHakmilik = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK FROM TBLHTPHAKMILIK A, TBLHTPHAKMILIKAGENSI B, TBLPHPHAKMILIKPERMOHONAN C"
					+ " WHERE A.ID_HAKMILIK = B.ID_HAKMILIK AND B.ID_HAKMILIKAGENSI = C.ID_HAKMILIKAGENSI"
					+ " AND C.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idHakmilik = rs.getString("ID_HAKMILIK");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idHakmilik;
	}

	public String getIdPejabatJPPH(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		String idPejabat = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG"
					+ " FROM TBLHTPHAKMILIK, TBLRUJPEJABATURUSAN"
					+ " WHERE TBLHTPHAKMILIK.ID_NEGERI = TBLRUJPEJABATURUSAN.ID_NEGERIURUS"
					+ " AND TBLHTPHAKMILIK.ID_DAERAH = TBLRUJPEJABATURUSAN.ID_DAERAHURUS"
					+ " AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = '3' AND TBLHTPHAKMILIK.ID_HAKMILIK = '"
					+ idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idPejabat = rs.getString("ID_PEJABATJKPTG");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idPejabat;
	}

	public void setMaklumatPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABAT"
					+ " FROM TBLRUJPEJABAT A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABAT = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				beanMaklumatPejabat.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPejabat", "");
				h.put("namaPejabat", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("bandar", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				h.put("noTel", "");
				h.put("noFax", "");
				beanMaklumatPejabat.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatJPPH(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, TARIKH_HANTAR, TARIKH_JANGKA_TERIMA, JANGKAMASA, FLAG_STATUS, FLAG_AKTIF, ID_PEJABAT,"
					+ " TARIKH_TERIMA, TARIKH_SURAT, NO_RUJUKAN, ULASAN, KADAR_SEWA_BULANAN, KADAR_SEWA_TAHUNAN"
					+ " FROM TBLPHPULASANTEKNIKAL A WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("jangkamasa", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus",
						rs.getString("FLAG_STATUS") == null ? "99999" : rs
								.getString("FLAG_STATUS"));
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("kadarSewaBulan",
						rs.getString("KADAR_SEWA_BULANAN") == null ? "" : Utils
								.format2Decimal(rs
										.getDouble("KADAR_SEWA_BULANAN")));
				h.put("kadarSewaTahun",
						rs.getString("KADAR_SEWA_TAHUNAN") == null ? "" : Utils
								.format2Decimal(rs
										.getDouble("KADAR_SEWA_TAHUNAN")));
				beanMaklumatJPPH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiJPPH(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JPPH' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus", rs.getString("FLAG_STATUS") == null ? ""
						: rs.getString("FLAG_STATUS"));
				if ("1".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "TELAH DIHANTAR");
				} else if ("2".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "DITERIMA");
				} else if ("3".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "TIADA JAWAPAN");
				} else {
					h.put("status", "");
				}
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listJPPH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatJPPH(String idPermohonan, String idPejabat,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_KJP", "JPPH");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJPPH(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_KJP", "JPPH");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
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
		return idUlasanTeknikalString;
	}

	public void simpanKemaskiniMaklumatJPPH(String idUlasanTeknikal,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat, String txtNoRujukan,
			String txtUlasan, String txtKadarSewaBulan,
			String txtKadarSewaTahun, HttpSession session) throws Exception {

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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", flagStatus);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukan);
			r.add("ULASAN", txtUlasan);
			r.add("KADAR_SEWA_BULANAN", Utils.RemoveSymbol(txtKadarSewaBulan));
			r.add("KADAR_SEWA_TAHUNAN", Utils.RemoveSymbol(txtKadarSewaTahun));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
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

	public void hapusMaklumatKJPKJT(String idUlasanTeknikal) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String idParent = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PARENT FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("ID_PARENT") != null
						&& !"".equals(rs.getString("ID_PARENT"))) {
					// TBLPHPULASANTEKNIKAL
					idParent = rs.getString("ID_PARENT");
					r = new SQLRenderer();
					r.update("ID_ULASANTEKNIKAL", idParent);
					r.add("FLAG_STATUS", "1");
					r.add("FLAG_AKTIF", "Y");

					sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
					stmt.executeUpdate(sql);
				}
			}

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLPHPULASANTEKNIKAL");

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

	public String getIdPerjanjianByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "' AND FLAG_PERJANJIAN = 'T'";
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

	public void setMaklumatPerjanjian(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_SIRI, TARIKH_MULA_PERJANJIAN, KADAR_SEWA, CAGARAN FROM TBLPHPPERJANJIAN"
					+ " WHERE ID_PERJANJIAN = '" + idPerjanjian + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("noSiri",
						rs.getString("NO_SIRI") == null ? "" : rs
								.getString("NO_SIRI"));
				h.put("tarikhMula",
						rs.getDate("TARIKH_MULA_PERJANJIAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_PERJANJIAN")));
				h.put("kadarSewa", rs.getString("KADAR_SEWA") == null ? ""
						: Utils.format2Decimal(rs.getDouble("KADAR_SEWA")));
				h.put("cagaran",
						rs.getString("CAGARAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("CAGARAN")));
				beanMaklumatPerjanjian.addElement(h);
			} else {
				h = new Hashtable();
				h.put("noSiri", "");
				h.put("tarikhMula", "");
				h.put("kadarSewa", "");
				h.put("cagaran", "");
				beanMaklumatPerjanjian.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanMaklumatKeputusan(String idPermohonan,
			String idKeputusan, String txtTarikhHantar, String idPerjanjian,
			String txtTarikhMula, String txtKadarSewa, String txtCagaran,
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

			if ("L".equals(idKeputusan)) {

				if ("".equals(idPerjanjian)) {
					insertPerjanjian(idPermohonan, txtTarikhMula, txtKadarSewa,
							txtCagaran, db, userId);
				} else {
					updatePerjanjian(idPerjanjian, txtTarikhMula, txtKadarSewa,
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

	private void insertPerjanjian(String idPermohonan, String txtTarikhMula,
			String txtKadarSewa, String txtCagaran, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERJANJIAN
			r = new SQLRenderer();
			long idPerjanjian = DB.getNextID("TBLPHPPERJANJIAN_SEQ");
			r.add("ID_PERJANJIAN", idPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("KADAR_SEWA", Utils.RemoveSymbol(txtKadarSewa));
			r.add("CAGARAN", Utils.RemoveSymbol(txtCagaran));
			r.add("FLAG_PERJANJIAN", "T");

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

	private void updatePerjanjian(String idPerjanjian, String txtTarikhMula,
			String txtKadarSewa, String txtCagaran, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERJANJIAN
			r = new SQLRenderer();
			r.update("ID_PERJANJIAN", idPerjanjian);
			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("KADAR_SEWA", Utils.RemoveSymbol(txtKadarSewa));
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

	public void simpanKemaskiniPerjanjian(String idPerjanjian,
			String txtNoSiri, String txtTarikhMula, String txtKadarSewa,
			String txtCagaran, HttpSession session) throws Exception {

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

			// TBLPHPPERJANJIAN
			r.update("ID_PERJANJIAN", idPerjanjian);
			r.add("NO_SIRI", txtNoSiri);
			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA_PERJANJIAN",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("KADAR_SEWA", Utils.RemoveSymbol(txtKadarSewa));
			r.add("CAGARAN", Utils.RemoveSymbol(txtCagaran));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERJANJIAN");
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

	public void setMaklumatMaklumbalas(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatMaklumbalas = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_TERIMA_CAGARAN, NO_RUJUKAN_CAGARAN, FLAG_CAGARAN, TARIKH_TANDATANGAN, NO_RUJUKAN_TANDATANGAN,"
					+ " FLAG_TANDATANGAN, TARIKH_MATISETEM, NO_RUJUKAN_MATISETEM, FLAG_MATISETEM FROM TBLPHPMAKLUMBALAS"
					+ " WHERE ID_PERJANJIAN = '" + idPerjanjian + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerimaCagaran",
						rs.getDate("TARIKH_TERIMA_CAGARAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_CAGARAN")));
				h.put("noRujukanCagaran",
						rs.getString("NO_RUJUKAN_CAGARAN") == null ? "" : rs
								.getString("NO_RUJUKAN_CAGARAN"));
				h.put("flagCagaran", rs.getString("FLAG_CAGARAN") == null ? ""
						: rs.getString("FLAG_CAGARAN"));
				h.put("tarikhTerimaTandatangan",
						rs.getDate("TARIKH_TANDATANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TANDATANGAN")));
				h.put("noRujukanTandatangan",
						rs.getString("NO_RUJUKAN_TANDATANGAN") == null ? ""
								: rs.getString("NO_RUJUKAN_TANDATANGAN"));
				h.put("flagTandatangan",
						rs.getString("FLAG_TANDATANGAN") == null ? "" : rs
								.getString("FLAG_TANDATANGAN"));
				h.put("tarikhTerimaMatiSetem",
						rs.getDate("TARIKH_MATISETEM") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MATISETEM")));
				h.put("noRujukanMatiSetem",
						rs.getString("NO_RUJUKAN_MATISETEM") == null ? "" : rs
								.getString("NO_RUJUKAN_MATISETEM"));
				h.put("flagMatiSetem",
						rs.getString("FLAG_MATISETEM") == null ? "" : rs
								.getString("FLAG_MATISETEM"));

				beanMaklumatMaklumbalas.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniMaklumbalas(String idPerjanjian,
			String txtTarikhTerimaCagaran, String txtNoRujukanCagaran,
			String socCagaran, String txtTarikhTerimaTandatangan,
			String txtNoRujukanTandatangan, String socTandatangan,
			String txtTarikhTerimaMatiSetem, String txtNoRujukanMatiSetem,
			String socMatiSetem, HttpSession session) throws Exception {

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

			// TBLPHPMAKLUMBALAS
			r.update("ID_PERJANJIAN", idPerjanjian);

			if (!"".equals(txtTarikhTerimaCagaran)) {
				r.add("TARIKH_TERIMA_CAGARAN",
						r.unquote("to_date('" + txtTarikhTerimaCagaran
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_CAGARAN", txtNoRujukanCagaran);
			r.add("FLAG_CAGARAN", socCagaran);

			if (!"".equals(txtTarikhTerimaTandatangan)) {
				r.add("TARIKH_TANDATANGAN",
						r.unquote("to_date('" + txtTarikhTerimaTandatangan
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_TANDATANGAN", txtNoRujukanTandatangan);
			r.add("FLAG_TANDATANGAN ", socTandatangan);

			if (!"".equals(txtTarikhTerimaMatiSetem)) {
				r.add("TARIKH_MATISETEM",
						r.unquote("to_date('" + txtTarikhTerimaMatiSetem
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_MATISETEM", txtNoRujukanMatiSetem);
			r.add("FLAG_MATISETEM", socMatiSetem);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMAKLUMBALAS");
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

	public void hapusPermohonan(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLDelete("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLDelete("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLDelete("TBLPERMOHONAN");
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

	public void seterusnyaMP(String idPermohonan, String flagJenisPerjanjian)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			if ("1".equals(flagJenisPerjanjian)
					|| "2".equals(flagJenisPerjanjian)) {
				r.add("ID_STATUS", "1610201"); // MESYUARAT
			} else {
				r.add("ID_STATUS", "1610199"); // JABATAN TEKNIKAL
			}

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public void seterusnyaJT(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610201"); // MESYUARAT

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public void seterusnyaMS(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610206"); // CETAKAN SURAT KEPUTUSAN

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public void seterusnyaLulus(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610214"); // PERJANJIAN

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public void seterusnyaTolak(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610208"); // TOLAK

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public boolean checkEmptyNoSiri(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'T' AND NO_SIRI IS NOT NULL"
					+ "  AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			db.close();
		}
	}

	public void seterusnyaPA(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610195"); // PERJANJIAN AKTIF

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public Vector getListPerjanjianTambahan() {
		return listPerjanjianTambahan;
	}

	public void setListPerjanjianTambahan(Vector listPerjanjianTambahan) {
		this.listPerjanjianTambahan = listPerjanjianTambahan;
	}

	public Vector getBeanMaklumatPerjanjianUtama() {
		return beanMaklumatPerjanjianUtama;
	}

	public void setBeanMaklumatPerjanjianUtama(
			Vector beanMaklumatPerjanjianUtama) {
		this.beanMaklumatPerjanjianUtama = beanMaklumatPerjanjianUtama;
	}

	public Vector getBeanMaklumatSewa() {
		return beanMaklumatSewa;
	}

	public void setBeanMaklumatSewa(Vector beanMaklumatSewa) {
		this.beanMaklumatSewa = beanMaklumatSewa;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatJPPH() {
		return beanMaklumatJPPH;
	}

	public void setBeanMaklumatJPPH(Vector beanMaklumatJPPH) {
		this.beanMaklumatJPPH = beanMaklumatJPPH;
	}

	public Vector getListJPPH() {
		return listJPPH;
	}

	public void setListJPPH(Vector listJPPH) {
		this.listJPPH = listJPPH;
	}

	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}

	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}

	public Vector getListMesyuarat() {
		return listMesyuarat;
	}

	public void setListMesyuarat(Vector listMesyuarat) {
		this.listMesyuarat = listMesyuarat;
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

	public Vector getBeanHeaderPerjanjianTambahan() {
		return beanHeaderPerjanjianTambahan;
	}

	public void setBeanHeaderPerjanjianTambahan(
			Vector beanHeaderPerjanjianTambahan) {
		this.beanHeaderPerjanjianTambahan = beanHeaderPerjanjianTambahan;
	}

	public Vector getBeanMaklumatMaklumbalas() {
		return beanMaklumatMaklumbalas;
	}

	public void setBeanMaklumatMaklumbalas(Vector beanMaklumatMaklumbalas) {
		this.beanMaklumatMaklumbalas = beanMaklumatMaklumbalas;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}
}
