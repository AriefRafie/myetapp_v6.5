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

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

/**
 * @author modified by hilda
 * 
 */
public class FrmAPBTamatLesenData {
	static Logger myLogger = Logger.getLogger(FrmAPBTamatLesenData.class);
	private Vector listTamatLesen = null;
	private Vector beanMaklumatMohonTamat = null;
	private Vector beanMaklumatJKPTG = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector beanMaklumatKeputusan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiPermohonanTamat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTamatLesen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MOHONTAMAT");
			r.add("RUJUKAN");
			r.add("TARIKH_TERIMA");
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPAPBMOHONTAMAT ", "ID_MOHONTAMAT ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idMohonTamat",
						rs.getString("ID_MOHONTAMAT") == null ? "" : rs
								.getString("ID_MOHONTAMAT"));
				h.put("rujukan",
						rs.getString("RUJUKAN") == null ? "" : rs
								.getString("RUJUKAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));

				listTamatLesen.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatMohonTamat(String idFail, String idPermohonan,
			Hashtable hash, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idMohonTamatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "";
			String TS = "";

			// TBLPFDMESYUARAT
			long idMesyuarat = DB.getNextID("TBLPFDMESYUARAT_SEQ");
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPFDMESYUARAT");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			// TBLPHPKERTASKERJA
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJA_SEQ");
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENIS_MESYUARAT", "L");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJA");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			// TBLPHPAPBMOHONTAMAT
			long idMohonTamat = DB.getNextID("TBLPHPAPBMOHONTAMAT_SEQ");
			r.add("ID_MOHONTAMAT", idMohonTamat);
			idMohonTamatString = String.valueOf(idMohonTamat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("RUJUKAN", hash.get("rujukan"));
			r.add("SEBAB_TAMAT", hash.get("sebabTamat"));
			if (!"".equals(hash.get("tarikhSurat"))) {
				TS = "to_date('" + hash.get("tarikhSurat") + "','dd/MM/yyyy')";
				r.add("TARIKH_SURAT", r.unquote(TS));
			}
			if (!"".equals(hash.get("tarikhJangkaTerima"))) {
				TT = "to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')";
				r.add("TARIKH_TERIMA", r.unquote(TT));
			}
			r.add("FLAG_PERMOHONAN_DARI", hash.get("socFlagDari"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAPBMOHONTAMAT");
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
		return idMohonTamatString;
	}

	public void simpanKemaskiniMaklumatMohonTamat(String idMohonTamat,
			Hashtable hash, HttpSession session) throws Exception {

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

			String TT = "";
			String TS = "";

			// TBLPHPAPBMOHONTAMAT
			r.update("ID_MOHONTAMAT", idMohonTamat);
			r.add("FLAG_PERMOHONAN_DARI", hash.get("socFlagDari"));
			r.add("RUJUKAN", hash.get("rujukan"));
			r.add("SEBAB_TAMAT", hash.get("sebabTamat"));
			if (!"".equals(hash.get("tarikhSurat"))) {
				TS = "to_date('" + hash.get("tarikhSurat") + "','dd/MM/yyyy')";
				r.add("TARIKH_SURAT", r.unquote(TS));
			}
			if (!"".equals(hash.get("tarikhJangkaTerima"))) {
				TT = "to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')";
				r.add("TARIKH_TERIMA", r.unquote(TT));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAPBMOHONTAMAT");

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

	public void simpanKemaskiniMaklumatJKPTG(String idMohonTamat,
			Hashtable hash, HttpSession session) throws Exception {

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

			// TBLPHPAPBMOHONTAMAT
			r.update("ID_MOHONTAMAT", idMohonTamat);

			r.add("ULASAN_KPTG", hash.get("ulasanJKPTG"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAPBMOHONTAMAT");
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

	public void simpanKemaskiniMaklumatMesyuarat(String idMohonTamat,
			Hashtable hash, HttpSession session) throws Exception {

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
			String idKertasKerja = "";
			String idMesyuarat = "";

			// GET DATA LAMA FROM TBLPHPAPBMOHONTAMAT
			sql = "SELECT ID_KERTASKERJA FROM TBLPHPAPBMOHONTAMAT WHERE ID_MOHONTAMAT = '"
					+ idMohonTamat + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idKertasKerja = rs.getString("ID_KERTASKERJA");
			}

			// GET DATA LAMA FROM TBLPHPKERTASKERJA
			sql = "SELECT ID_MESYUARAT FROM TBLPHPKERTASKERJA WHERE ID_KERTASKERJA = '"
					+ idKertasKerja + "'";
			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				idMesyuarat = rs1.getString("ID_MESYUARAT");
			}

			String TM = "";

			// TBLPFDMESYUARAT
			r.update("ID_MESYUARAT", idMesyuarat);
			if (!"".equals(hash.get("tarikhMesyuarat"))) {
				TM = "to_date('" + hash.get("tarikhMesyuarat")
						+ "','dd/MM/yyyy')";
				r.add("TARIKH_MESYUARAT", r.unquote(TM));
			}
			r.add("MASA_MESYUARAT_DARI", hash.get("socMasaDari"));
			r.add("WAKTU_MESYUARAT_DARI", hash.get("txtWaktuDari"));
			r.add("MASA_MESYUARAT_HINGGA", hash.get("socMasaHingga"));
			r.add("WAKTU_MESYUARAT_HINGGA", hash.get("txtWaktuHingga"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPFDMESYUARAT");
			stmt.executeUpdate(sql);

			// TBLPHPKERTASKERJA
			r = new SQLRenderer();
			r.update("ID_KERTASKERJA", idKertasKerja);
			if (!"".equals(hash.get("tarikhMesyuarat"))) {
				TM = "to_date('" + hash.get("tarikhMesyuarat")
						+ "','dd/MM/yyyy')";
				r.add("TARIKH_MESYUARAT", r.unquote(TM));
			}
			r.add("BIL_MESYUARAT", hash.get("txtBilMesyuarat"));
			r.add("TUJUAN", hash.get("txtTujuanMesyuarat"));
			r.add("ULASAN_KEPUTUSAN", hash.get("syorMesyuarat"));
			r.add("FLAG_JENIS_MESYUARAT", "L");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJA");
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

	public void simpanKemaskiniMaklumatKeputusan(String idMohonTamat,
			Hashtable hash, HttpSession session) throws Exception {

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

			String TH = "";
			String TJT = "";
			String TT = "";
			String TS = "";

			// TBLPHPAPBMOHONTAMAT

			r.update("ID_MOHONTAMAT", idMohonTamat);
			if (!"".equals(hash.get("tarikhKeputusan"))) {
				TS = "to_date('" + hash.get("tarikhKeputusan")
						+ "','dd/MM/yyyy')";
				r.add("TARIKH_KEPUTUSAN", r.unquote(TS));
			}
			r.add("KEPUTUSAN", hash.get("socKeputusan"));
			r.add("ULASAN_MENTERI", hash.get("ulasanMenteri"));

			r.add("FLAG_LESEN", hash.get("socFlagLesen"));
			r.add("FLAG_TUNGGAKAN", hash.get("socFlagTunggakan"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAPBMOHONTAMAT");
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

	public void setMaklumatMohonTamat(String idMohonTamat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatMohonTamat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_MOHONTAMAT,TARIKH_SURAT, TARIKH_TERIMA, RUJUKAN, SEBAB_TAMAT, FLAG_PERMOHONAN_DARI "
					+ " FROM TBLPHPAPBMOHONTAMAT WHERE ID_MOHONTAMAT = '"
					+ idMohonTamat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idMohonTamat",
						rs.getString("ID_MOHONTAMAT") == null ? "" : rs
								.getString("ID_MOHONTAMAT"));
				h.put("socFlagDari",
						rs.getString("FLAG_PERMOHONAN_DARI") == null ? "" : rs
								.getString("FLAG_PERMOHONAN_DARI"));
				h.put("rujukan",
						rs.getString("RUJUKAN") == null ? "" : rs
								.getString("RUJUKAN"));
				h.put("sebabTamat", rs.getString("SEBAB_TAMAT") == null ? ""
						: rs.getString("SEBAB_TAMAT"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));

				beanMaklumatMohonTamat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			String idMohonTamat, HttpSession session) throws Exception {
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
			String keputusan = "";

			// GET DATA LAMA FROM TBLPHPAPBMOHONTAMAT
			sql = "SELECT KEPUTUSAN FROM TBLPHPAPBMOHONTAMAT WHERE ID_MOHONTAMAT = '"
					+ idMohonTamat + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			keputusan = rs.getString("KEPUTUSAN");

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			if ("1".equals(keputusan)) {
				r.add("ID_STATUS", "1610244");
			} else if ("2".equals(keputusan)) {
				r.add("ID_STATUS", "1610207");
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

			// GET DATA LAMA FROM TBLPFDFAIL
			sql = "SELECT ID_SUBURUSAN FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs1 = stmt.executeQuery(sql);
			rs1.next();

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			if ("1".equals(keputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus(rs1.getString("ID_SUBURUSAN"),
								"1610244")); // TAMAT LESEN
			} else if ("2".equals(keputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus(rs1.getString("ID_SUBURUSAN"),
								"1610207")); // LULUS
			}
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

	public void setMaklumatJKPTG(String idMohonTamat) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			beanMaklumatJKPTG = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ULASAN_KPTG " + "FROM TBLPHPAPBMOHONTAMAT "
					+ "WHERE ID_MOHONTAMAT = '" + idMohonTamat + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ulasanJKPTG", rs.getString("ULASAN_KPTG") == null ? ""
						: rs.getString("ULASAN_KPTG"));
				beanMaklumatJKPTG.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatMesyuarat(String idMohonTamat) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			beanMaklumatMesyuarat = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT B.TARIKH_MESYUARAT, B.BIL_MESYUARAT, B.TUJUAN, B.ULASAN_KEPUTUSAN, C.MASA_MESYUARAT_DARI, "
					+ "C.WAKTU_MESYUARAT_DARI, C.MASA_MESYUARAT_HINGGA ,C.WAKTU_MESYUARAT_HINGGA "
					+ "FROM TBLPHPAPBMOHONTAMAT A, TBLPHPKERTASKERJA B, TBLPFDMESYUARAT C "
					+ "WHERE B.FLAG_JENIS_MESYUARAT = 'L' "
					+ "AND A.ID_KERTASKERJA = B.ID_KERTASKERJA "
					+ "AND B.ID_MESYUARAT = C.ID_MESYUARAT "
					+ "AND A.ID_MOHONTAMAT = '" + idMohonTamat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhMesyuarat",
						rs.getDate("TARIKH_MESYUARAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MESYUARAT")));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				h.put("waktuMesyuaratDari",
						rs.getString("WAKTU_MESYUARAT_DARI") == null ? "" : rs
								.getString("WAKTU_MESYUARAT_DARI"));
				h.put("idMasaDari",
						rs.getString("MASA_MESYUARAT_DARI") == null ? "" : rs
								.getString("MASA_MESYUARAT_DARI"));
				h.put("waktuMesyuaratHingga",
						rs.getString("WAKTU_MESYUARAT_HINGGA") == null ? ""
								: rs.getString("WAKTU_MESYUARAT_HINGGA"));
				h.put("idMasaHingga",
						rs.getString("MASA_MESYUARAT_HINGGA") == null ? "" : rs
								.getString("MASA_MESYUARAT_HINGGA"));
				h.put("tujuanMesyuarat", rs.getString("TUJUAN") == null ? ""
						: rs.getString("TUJUAN"));
				h.put("syorMesyuarat",
						rs.getString("ULASAN_KEPUTUSAN") == null ? "" : rs
								.getString("ULASAN_KEPUTUSAN"));
				beanMaklumatMesyuarat.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKeputusan(String idMohonTamat) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			beanMaklumatKeputusan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_KEPUTUSAN, KEPUTUSAN, ULASAN_MENTERI, FLAG_LESEN, FLAG_TUNGGAKAN "
					+ "FROM TBLPHPAPBMOHONTAMAT "
					+ "WHERE ID_MOHONTAMAT = '"
					+ idMohonTamat + "'";

			myLogger.info("beanMaklumatKeputusan :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_KEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("socKeputusan", rs.getString("KEPUTUSAN") == null ? ""
						: rs.getString("KEPUTUSAN"));
				h.put("ulasanMenteri",
						rs.getString("ULASAN_MENTERI") == null ? "" : rs
								.getString("ULASAN_MENTERI"));
				h.put("socFlagLesen", rs.getString("FLAG_LESEN") == null ? ""
						: rs.getString("FLAG_LESEN"));
				h.put("socFlagTunggakan",
						rs.getString("FLAG_TUNGGAKAN") == null ? "" : rs
								.getString("FLAG_TUNGGAKAN"));
				beanMaklumatKeputusan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListPermohonanTamat() {
		return listTamatLesen;
	}

	public Vector getBeanMaklumatMohonTamat() {
		return beanMaklumatMohonTamat;
	}

	public Vector getBeanMaklumatKeputusan() {
		return beanMaklumatKeputusan;
	}

	public Vector getBeanMaklumatJKPTG() {
		return beanMaklumatJKPTG;
	}

	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}
}
