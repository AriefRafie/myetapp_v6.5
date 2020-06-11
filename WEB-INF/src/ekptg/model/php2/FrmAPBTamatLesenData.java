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

import ekptg.helpers.AuditTrail;
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
	private Vector beanMaklumatNotis = null;
	private Vector beanMaklumatPejabat = null;
	private Vector listNotis = null;
	
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
	
	public void setMaklumatNotis(String idMohonTamat) throws Exception {
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
	
	public Vector getBeanMaklumatNotis() {
		return beanMaklumatNotis;
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
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? ""
						: rs.getString("ID_PEJABAT"));
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
	
	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
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
	public String simpanMaklumatNotis(String idPermohonan, String idPejabat,
			String idNegeri, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String idSuratKe,
			String idKementerianTanah, String idAgensiTanah, HttpSession session)
			throws Exception {

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
			r.add("ID_DOKUMEN", "8");
			r.add("FLAG_KJP", idSuratKe);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlangaNotis(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String idNegeri,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String idSuratKe,
			String idKementerianTanah, String idAgensiTanah, HttpSession session)
			throws Exception {

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
			r.add("ID_DOKUMEN", "8");
			r.add("FLAG_KJP", idSuratKe);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void simpanKemaskiniMaklumaNotis(String idUlasanTeknikal,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, String idSuratKe,
			String idKementerianTanah, String idAgensiTanah,
			String txtNamaPegawai, String txtJawatan, HttpSession session)
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_DOKUMEN", "8");
			r.add("FLAG_KJP", idSuratKe);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_MENTERI", "");
			r.add("ID_AGENSI", "");
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
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("NAMA_JAWATAN", txtJawatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + idUlasanTeknikal
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
	
	public void setSenaraiNotis(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotis = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, D.KETERANGAN AS NAMA_DOKUMEN,A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, D.ID_DOKUMEN,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLPHPRUJDOKUMEN D,TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) AND A.ID_DOKUMEN = 8 AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
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
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listNotis.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getListNotis() {
		return listNotis;
	}
}
