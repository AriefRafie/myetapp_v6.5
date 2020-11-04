package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.php2.modelTable.TblPhpPerjanjianModel;
import ekptg.model.php2.modelTable.TblPhpPermohonanSewaModel;

public class FrmPYWPerjanjianData {

	private Vector beanMaklumatPerjanjian = null;
	private Vector beanMaklumatMaklumbalas = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatHakmilik = null;
	private Vector listSuratPeringatan = null;
	private Vector beanMaklumatTamatSewa = null;
	private static final Log log = LogFactory.getLog(FrmPYWPerjanjianData.class);


	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPerjanjian(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_SIRI, TARIKH_MULA_PERJANJIAN, TEMPOH, TARIKH_TAMAT_PERJANJIAN, KADAR_SEWA, CAGARAN, ROYALTI FROM TBLPHPPERJANJIAN"
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
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTamatSewa(String idPermohonanSewa) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTamatSewa = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PHPPERMOHONANSEWA, A.TARIKH_SURAT_TAMAT , A.CATATAN_TAMAT , B.KETERANGAN , A.ID_SEBABTAMAT"
					+ " FROM TBLPHPPERMOHONANSEWA A , TBLPHPRUJSEBABTAMAT B "
					+ " WHERE A.ID_SEBABTAMAT = B.ID_SEBABTAMAT AND A.ID_PHPPERMOHONANSEWA = '" + idPermohonanSewa + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa",
						rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs
								.getString("ID_PHPPERMOHONANSEWA"));
				h.put("idSebabTamat",
						rs.getString("ID_SEBABTAMAT") == null ? "" : rs
								.getString("ID_SEBABTAMAT"));
				h.put("sebabTamat",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("tarikhSuratTamat",
						rs.getDate("TARIKH_SURAT_TAMAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_TAMAT")));
				h.put("catatanTamat",
						rs.getString("CATATAN_TAMAT") == null ? "" : rs
								.getString("CATATAN_TAMAT"));
				beanMaklumatTamatSewa.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiDokumen(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listSuratPeringatan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERJANJIAN , TARIKH_SURAT_NOTIFIKASI FROM TBLPHPPERJANJIAN WHERE ID_PERJANJIAN = '"
					+ idPerjanjian + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPerjanjian", rs.getString("ID_PERJANJIAN") == null ? "" : rs.getString("ID_PERJANJIAN"));
				h.put("tarikhHantar",rs.getDate("TARIKH_SURAT_NOTIFIKASI") == null ? "": sdf.format(rs.getDate("TARIKH_SURAT_NOTIFIKASI")));
				listSuratPeringatan.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniPerjanjian(String idPerjanjian,
			String txtNoSiri, String txtTarikhMula, String txtTempoh,
			String txtTarikhTamat, String txtKadarSewa, String txtRoyalti,
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

			// GET MAKLUMAT PERJANJIAN
			String sqlSelect = "SELECT NO_SIRI, ID_PERMOHONAN, TARIKH_MULA_PERJANJIAN, TEMPOH, TARIKH_TAMAT_PERJANJIAN, KADAR_SEWA, CAGARAN FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'U' AND ID_PERJANJIAN = '"
					+ idPerjanjian + "'";
			ResultSet rsPerjanjian = stmt.executeQuery(sqlSelect);

			if (rsPerjanjian.next()) {
				String idPermohonan = rsPerjanjian.getString("ID_PERMOHONAN");
				String noSiri = rsPerjanjian.getString("NO_SIRI");
				String bayaran = rsPerjanjian.getString("KADAR_SEWA");
				String deposit = rsPerjanjian.getString("CAGARAN");
				String tempoh = rsPerjanjian.getString("TEMPOH");
				Date tarikhMula = new Date();
				Date tarikhTamat = new Date();
				tarikhMula = rsPerjanjian.getDate("TARIKH_MULA_PERJANJIAN");
				tarikhTamat = rsPerjanjian
						.getDate("TARIKH_TAMAT_PERJANJIAN");

				// TBLPHPBAYARANPERLUDIBAYAR
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
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
				r.add("TEMPOH", Integer.parseInt(tempoh));
				r.add("BAYARAN", bayaran);
				r.add("DEPOSIT", deposit);
				r.add("FLAG_AKTIF", "Y");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPHPBAYARANPERLUDIBAYAR");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			AuditTrail.logActivity("1610214", "4", null, session, "UPD",
					"FAIL [" + idPerjanjian
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

			AuditTrail.logActivity("1610214", "4", null, session, "UPD",
					"FAIL [" + idPerjanjian
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
			r.add("ID_STATUS", "1610195"); // PERJANJIAN AKTIF

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
					getIdSuburusanstatus(idSuburusan, "1610195")); // PERJANJIAN
																	// AKTIF

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
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

	public void updateNoSiriPerjanjian(String idPermohonan, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String noSiri = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT NO_SIRI FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'U' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				noSiri = rs.getString("NO_SIRI");
			}

			// TBLPHPBAYARANPERLUDIBAYAR
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN", noSiri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBAYARANPERLUDIBAYAR");
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

	public boolean checkEmptyNoSiri(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_PERJANJIAN FROM TBLPHPPERJANJIAN WHERE FLAG_PERJANJIAN = 'U' AND NO_SIRI IS NOT NULL"
					+ "  AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PEMOHON, C.NAMA, C.NAMA_PEGAWAI, C.NO_PENGENALAN, C.PEKERJAAN, C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP,"
					+ " C.POSKOD_TETAP, C.ID_BANDARTETAP, C.ID_NEGERITETAP, C.NO_TEL, C.NO_FAX,"
					+ " C.EMEL, C.ID_KATEGORIPEMOHON "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri", rs.getString("ID_NEGERITETAP") == null ? ""
						: rs.getString("ID_NEGERITETAP"));
				h.put("idBandar", rs.getString("ID_BANDARTETAP") == null ? ""
						: rs.getString("ID_BANDARTETAP"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));

				beanMaklumatPemohon.addElement(h);
				bil++;

			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensi(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("JENIS_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("JENIS_LOT")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_BERSAMAAN") == null
								|| rs.getString("JENIS_LUAS_BERSAMAAN") == null ? ""
								: Utils.formatLuas(rs
										.getDouble("LUAS_BERSAMAAN"))
										+ " "
										+ rs.getString("JENIS_LUAS_BERSAMAAN"));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				if (rs.getString("NO_HAKMILIK") != null
						&& rs.getString("NO_WARTA") == null) {
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null
						&& rs.getString("NO_WARTA") != null) {
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				beanMaklumatTanah.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String daftarSambungan(String idFailLama, String idPermohonanLama,
			String idUrusan, String idSuburusan, String tarikhTerima,
			String tarikhSurat, String txtNoRujukanSurat, String txtPerkara,
			String idKategoriPemohon, String txtNama, String txtNamaPegawai,
			String txtNoPendaftaran, String txtPekerjaan, String txtAlamat1,
			String txtAlamat2, String txtAlamat3, String txtPoskod,
			String idBandar, String idNegeri, String txtEmel, String txtNoTel,
			String txtNoFaks, String noSambungan, String userRole,
			String idNegeriUser, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		Db db1 = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String idPemohon = "";
		int sambungan = 0;
		if (!"".equals(noSambungan)) {
			sambungan = Utils.parseInt(noSambungan);
			sambungan++;
		}

		try {
			db = new Db();
			db1 = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			Statement stmtSelect = db1.getStatement();
			SQLRenderer r = new SQLRenderer();

			// DEACTIVATE PERMOHONAN LAMA
			deActivatePermohonanLama(idFailLama, db, userId);

			// TBLPFDFAIL
			sql = "SELECT NO_FAIL, ID_NEGERI, ID_KEMENTERIAN FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFailLama + "'";
			ResultSet rsFail = stmtSelect.executeQuery(sql);
			rsFail.next();

			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", txtPerkara);
			r.add("NO_FAIL",
					rsFail.getString("NO_FAIL") == null ? "" : rsFail
							.getString("NO_FAIL"));
			r.add("NO_FAIL_ROOT", rsFail.getString("NO_FAIL") == null ? "": rsFail.getString("NO_FAIL"));
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "1"); // DATA BARU ETAPP
			r.add("ID_NEGERI", rsFail.getString("ID_NEGERI") == null ? "": rsFail.getString("ID_NEGERI"));
			r.add("ID_KEMENTERIAN",rsFail.getString("ID_KEMENTERIAN") == null ? "" : rsFail.getString("ID_KEMENTERIAN"));
			r.add("ID_NEGERI_BUKAFAIL", getIDDBNegeri());
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPPEMOHON
			sql = "SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			rsPermohonan.next();
			idPemohon = rsPermohonan.getString("ID_PEMOHON");

			r = new SQLRenderer();
			r.update("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);
			r.add("NAMA", txtNama);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("NO_PENGENALAN", txtNoPendaftaran);
			r.add("PEKERJAAN", txtPekerjaan);
			r.add("ALAMAT1_TETAP", txtAlamat1);
			r.add("ALAMAT2_TETAP", txtAlamat2);
			r.add("ALAMAT3_TETAP", txtAlamat3);
			r.add("POSKOD_TETAP", txtPoskod);
			r.add("ID_BANDARTETAP", idBandar);
			r.add("ID_NEGERITETAP", idNegeri);
			r.add("NO_TEL", txtNoTel);
			r.add("NO_FAX", txtNoFaks);
			r.add("EMEL", txtEmel);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "1610198"); // MAKLUMAT PERMOHONAN
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT", txtNoRujukanSurat);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PERJANJIAN", "U");
			r.add("NO_SAMBUNGAN", sambungan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			copyTBLPHPHAKMILIKPERMOHONAN(idPermohonanLama, idPermohonan, db, db1,
					userId);

			// TBLPHPPERMOHONANSEWA
			copyTBLPHPPERMOHONANSEWA(idPermohonanLama, idPermohonan, db, userId);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610198")); // MAKLUMAT
																	// PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPKERTASKERJAPENYEWAAN
			r = new SQLRenderer();
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAPENYEWAAN_SEQ");
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KERTAS", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);

			// INSERT ON TUGASAN
			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", userId);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", userRole);
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610214", "4", null, session, "INS",
					"FAIL [" + idPermohonanLama
							+ "] DIDAFTARKAN SAMBUNGAN");

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
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}

	public String simpanTamatLogTugasan(String idFail, String userRole,
			String idNegeriUser, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		Db db1 = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String idPemohon = "";

		try {
			db = new Db();
			db1 = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			Statement stmtSelect = db1.getStatement();
			SQLRenderer r = new SQLRenderer();


			// INSERT ON TUGASAN
			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", userId);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", userRole);
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
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
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}

	private void copyTBLPHPPERMOHONANSEWA(String idPermohonanLama,
			long idPermohonan, Db db, String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();

			// TBLPHPPERMOHONANSEWA
			long idPermohonanSewa = DB.getNextID("TBLPHPPERMOHONANSEWA_SEQ");

			sql = "INSERT INTO TBLPHPPERMOHONANSEWA"
					+ " (ID_PHPPERMOHONANSEWA, ID_PERMOHONAN, TUJUAN, ID_LUASASAL, LUAS_ASAL, LUAS_MHN1,"
					+ " LUAS_MHN2, LUAS_MHN3, ID_LUASMHNBERSAMAAN, LUAS_MHNBERSAMAAN, ID_LUASBAKI, LUAS_BAKI,"
					+ " ID_MASUK, TARIKH_MASUK, FLAG_PERMOHONANDARI, ID_LUASMHN,"
					+ " FLAG_GUNA, FLAG_TEMPOHSEWA, FLAG_PROSESFAIL)"

					+ " SELECT "
					+ idPermohonanSewa
					+ ", "
					+ idPermohonan
					+ ", TUJUAN, ID_LUASASAL, LUAS_ASAL, LUAS_MHN1,"
					+ " LUAS_MHN2, LUAS_MHN3, ID_LUASMHNBERSAMAAN, LUAS_MHNBERSAMAAN, ID_LUASBAKI, LUAS_BAKI,"
					+ " " + userId
					+ ", SYSDATE, FLAG_PERMOHONANDARI, ID_LUASMHN,"
					+ " FLAG_GUNA, FLAG_TEMPOHSEWA, 'J'"
					+ " FROM TBLPHPPERMOHONANSEWA WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void copyTBLPHPHAKMILIKPERMOHONAN(String idPermohonanLama,
			long idPermohonan, Db db, Db db1, String userId) throws Exception {

		String sql = "";
		String idHakmilikPermohonanLama = "";
		String idHakmilikLama = "";
		String flagHakmilik = "";

		try {
			Statement stmt = db1.getStatement();

			// TBLPHPHAKMILIKPERMOHONAN
			sql = "SELECT A.ID_HAKMILIKPERMOHONAN, A.FLAG_HAKMILIK, B.ID_HAKMILIK FROM TBLPHPHAKMILIKPERMOHONAN A, TBLPHPHAKMILIK B"
					+ " WHERE A.ID_HAKMILIKPERMOHONAN = B.ID_HAKMILIKPERMOHONAN AND A.ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);
			int count = 1;
			while (rs.next()) {
				count++;
				idHakmilikPermohonanLama = rs
						.getString("ID_HAKMILIKPERMOHONAN");
				idHakmilikLama = rs.getString("ID_HAKMILIK");
				flagHakmilik = rs.getString("FLAG_HAKMILIK");
				long idHakmilikPermohonan = DB
						.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
				insertTBLPHPHAKMILIKPERMOHONAN(idHakmilikPermohonan,
						idPermohonan, idHakmilikPermohonanLama, db, userId);
				insertTBLPHPHAKMILIK(idHakmilikPermohonan, idHakmilikLama, db,
						userId);
				if ("U".equals(flagHakmilik)) {
					insertTBLPHPLAPORANTANAH(idHakmilikPermohonan,
							idPermohonan, db, userId);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void insertTBLPHPLAPORANTANAH(long idHakmilikPermohonan,
			long idPermohonan, Db db, String userId) throws Exception {

		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "P"); // TANAH DIPOHON
			r.add("ID_HAKMILIK", idHakmilikPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void insertTBLPHPHAKMILIKPERMOHONAN(long idHakmilikPermohonan,
			long idPermohonan, String idHakmilikPermohonanLama, Db db,
			String userId) throws Exception {

		String sql = "";

		Statement stmt = db.getStatement();
		// TBLPHPHAKMILIKPERMOHONAN
		sql = "INSERT INTO TBLPHPHAKMILIKPERMOHONAN"
				+ " (ID_HAKMILIKPERMOHONAN, ID_PERMOHONAN, ID_MASUK,"
				+ " TARIKH_MASUK, ID_HAKMILIKAGENSI, NILAIAN, ID_HAKMILIK,"
				+ " FLAG_BORANGK, ID_BORANGK, ID_HAKMILIKURUSAN,"
				+ " ID_PHPBORANGK, FLAG_HAKMILIK, ID_PPTBORANGK)"

				+ " SELECT "
				+ idHakmilikPermohonan
				+ ", "
				+ idPermohonan
				+ ", "
				+ userId
				+ ","
				+ " SYSDATE, ID_HAKMILIKAGENSI, NILAIAN, ID_HAKMILIK,"
				+ " FLAG_BORANGK, ID_BORANGK, ID_HAKMILIKURUSAN,"
				+ " ID_PHPBORANGK, FLAG_HAKMILIK, ID_PPTBORANGK"
				+ " FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_HAKMILIKPERMOHONAN = '"
				+ idHakmilikPermohonanLama + "'";

		stmt.executeUpdate(sql);
	}

	private void insertTBLPHPHAKMILIK(long idHakmilikPermohonan,
			String idHakmilikLama, Db db, String userId) throws Exception {

		String sql = "";

		Statement stmt = db.getStatement();

		// TBLPHPHAKMILIKPERMOHONAN
		sql = "INSERT INTO TBLPHPHAKMILIK"
				+ " (ID_NEGERI, ID_DAERAH, ID_MUKIM, ID_JENISHAKMILIK, NO_HAKMILIK, ID_LOT, NO_LOT, ID_KATEGORI,"
				+ " SYARAT, SEKATAN, ID_LUAS, LUAS, ID_KEMENTERIAN, ID_AGENSI, PEGANGAN_HAKMILIK, KEGUNAAN_TANAH, ID_SUBKATEGORI,"
				+ " ID_HAKMILIKPERMOHONAN, TARIKH_BORANGK, CATATAN, NO_PERSERAHAN, TARIKH_CATATAN, TARIKH_TERIMA,"
				+ " NO_WARTA, TARIKH_WARTA, ID_UNITLUASAMBIL, LUAS_AMBIL, SYARAT_NYATA, SEKATAN_KEPENTINGAN)"

				+ " SELECT ID_NEGERI, ID_DAERAH, ID_MUKIM, ID_JENISHAKMILIK, NO_HAKMILIK, ID_LOT, NO_LOT, ID_KATEGORI,"
				+ " SYARAT, SEKATAN, ID_LUAS, LUAS, ID_KEMENTERIAN, ID_AGENSI, PEGANGAN_HAKMILIK, KEGUNAAN_TANAH, ID_SUBKATEGORI,"
				+ " "
				+ idHakmilikPermohonan
				+ ", TARIKH_BORANGK, CATATAN, NO_PERSERAHAN, TARIKH_CATATAN, TARIKH_TERIMA,"
				+ " NO_WARTA, TARIKH_WARTA, ID_UNITLUASAMBIL, LUAS_AMBIL, SYARAT_NYATA, SEKATAN_KEPENTINGAN"
				+ " FROM TBLPHPHAKMILIK WHERE ID_HAKMILIK = '" + idHakmilikLama
				+ "'";

		stmt.executeUpdate(sql);
	}

	private void deActivatePermohonanLama(String idFail, Db db, String userId)
			throws Exception {

		String sql = "";
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		// TBLPERMOHONAN
		r = new SQLRenderer();
		r.update("ID_FAIL", idFail);
		r.add("FLAG_AKTIF", "T");

		r.add("ID_KEMASKINI", userId);
		r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

		sql = r.getSQLUpdate("TBLPERMOHONAN");
		stmt.executeUpdate(sql);
	}

	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON,"
					+ " A.ID_URUSAN, A.ID_SUBURUSAN, C.FLAG_PROSESFAIL, C.CATATAN, C.TUJUAN AS TUJUAN_BANGUNAN,  RUJURUSAN.NAMA_URUSAN, RUJSUBURUSAN.NAMA_SUBURUSAN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLRUJURUSAN RUJURUSAN, TBLRUJSUBURUSAN RUJSUBURUSAN"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"
					+ " AND A.ID_URUSAN = RUJURUSAN.ID_URUSAN(+) AND A.ID_SUBURUSAN = RUJSUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("tujuan", rs.getString("TUJUAN_BANGUNAN") == null ? ""
						: rs.getString("TUJUAN_BANGUNAN").toUpperCase());
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("urusan",
						rs.getString("NAMA_URUSAN") == null ? "" : rs
								.getString("NAMA_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				h.put("flagProsesFail",
						rs.getString("FLAG_PROSESFAIL") == null ? "" : rs
								.getString("FLAG_PROSESFAIL"));
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodKementerianByIdKementerian(String idKementerian)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_KEMENTERIAN");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodNegeriByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_NEGERI");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikPermohonanByIdFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND FLAG_HAKMILIK = 'U' AND MOHON.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKPERMOHONAN");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatHakmilik(String idHakmilikPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK"

					+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPermohonan", rs
						.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
						.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK").toUpperCase());
				beanMaklumatHakmilik.addElement(h);

			} else {

				h = new Hashtable();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");

				h.put("peganganHakmilik", "");
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				h.put("flagBorangK", "");
				beanMaklumatHakmilik.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIDDBNegeri() throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJNEGERI.ID_NEGERI FROM TBLLOOKUPSTATE, TBLRUJNEGERI WHERE TBLLOOKUPSTATE.KOD_NEGERI = TBLRUJNEGERI.KOD_NEGERI";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_NEGERI");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanSuratPeringatan(String idPerjanjian, String tarikhHantar, HttpSession session) throws Exception {
		TblPhpPerjanjianModel model = new TblPhpPerjanjianModel();
		model.setIdPerjanjian(Long.parseLong(idPerjanjian));
		model.setTarikhSuratNotifikasi(tarikhHantar);
		model.setFlagPost("update");
		model.save(model, session);
	}

	public void simpanTamatSewa(String idPermohonanSewa, String socSebabTamat, String tarikhSuratTamat, String catatanTamat, HttpSession session) throws Exception {
		TblPhpPermohonanSewaModel model = new TblPhpPermohonanSewaModel();
		model.setIdPhppermohonansewa(Long.parseLong(idPermohonanSewa));
		model.setFlagSebabTamat("Y");
		model.setTarikhSurattamat(tarikhSuratTamat);
		model.setCatatanTamat(catatanTamat);
		model.setIdSebabtamat(socSebabTamat);
		model.setFlagPost("update");
		model.save(model, session);
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPerjanjian() {
		return beanMaklumatPerjanjian;
	}

	public void setBeanMaklumatPerjanjian(Vector beanMaklumatPerjanjian) {
		this.beanMaklumatPerjanjian = beanMaklumatPerjanjian;
	}

	public Vector getBeanMaklumatMaklumbalas() {
		return beanMaklumatMaklumbalas;
	}

	public void setBeanMaklumatMaklumbalas(Vector beanMaklumatMaklumbalas) {
		this.beanMaklumatMaklumbalas = beanMaklumatMaklumbalas;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

	public Vector getListSuratPeringatan() {
		return listSuratPeringatan;
	}

	public void setListSuratPeringatan(Vector listSuratPeringatan) {
		this.listSuratPeringatan = listSuratPeringatan;
	}

	public Vector getBeanMaklumatTamatSewa() {
		return beanMaklumatTamatSewa;
	}

	public void setBeanMaklumatTamatSewa(Vector beanMaklumatTamatSewa) {
		this.beanMaklumatTamatSewa = beanMaklumatTamatSewa;
	}



}