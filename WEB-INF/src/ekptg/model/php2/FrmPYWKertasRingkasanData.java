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

public class FrmPYWKertasRingkasanData {

	private Vector beanKertasRingkasan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKertasRingkasan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasRingkasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KERTASKERJA, ULASAN_KJP, ULASAN_JPPH, ULASAN_JKPTGN, ULASAN_KEM_KEWANGAN, ULASAN_KEM_WP, ULASAN_PTG,"
					+ " ULASAN_DBKL, ULASAN_BPH, FLAG_PAJAKAN, FLAG_PENSWASTAAN, NAMA_PEGAWAI_HTP, TARIKH_RUJUKAN_HTP"
					+ " FROM TBLPHPKERTASKERJAPENYEWAAN WHERE FLAG_KERTAS = '1' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertasKerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("ulasanKJP",
						rs.getString("ULASAN_KJP") == null ? "" : rs
								.getString("ULASAN_KJP"));
				h.put("ulasanJPPH", rs.getString("ULASAN_JPPH") == null ? ""
						: rs.getString("ULASAN_JPPH"));
				h.put("ulasanJKPTGN",
						rs.getString("ULASAN_JKPTGN") == null ? "" : rs
								.getString("ULASAN_JKPTGN"));
				h.put("ulasanKemKewangan",
						rs.getString("ULASAN_KEM_KEWANGAN") == null ? "" : rs
								.getString("ULASAN_KEM_KEWANGAN"));
				h.put("ulasanKemWP", rs.getString("ULASAN_KEM_WP") == null ? ""
						: rs.getString("ULASAN_KEM_WP"));
				h.put("ulasanPTG",
						rs.getString("ULASAN_PTG") == null ? "" : rs
								.getString("ULASAN_PTG"));
				h.put("ulasanDBKL", rs.getString("ULASAN_DBKL") == null ? ""
						: rs.getString("ULASAN_DBKL"));
				h.put("ulasanBPH",
						rs.getString("ULASAN_BPH") == null ? "" : rs
								.getString("ULASAN_BPH"));
				h.put("flagPajakan", rs.getString("FLAG_PAJAKAN") == null ? ""
						: rs.getString("FLAG_PAJAKAN"));
				h.put("flagPenswastaan",
						rs.getString("FLAG_PENSWASTAAN") == null ? "" : rs
								.getString("FLAG_PENSWASTAAN"));
				h.put("namaPegawai",
						rs.getString("NAMA_PEGAWAI_HTP") == null ? "" : rs
								.getString("NAMA_PEGAWAI_HTP"));
				h.put("tarikhRujukan",
						rs.getDate("TARIKH_RUJUKAN_HTP") == null ? "" : sdf
								.format(rs.getDate("TARIKH_RUJUKAN_HTP")));
				beanKertasRingkasan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniRingkasan(String idKertasKerja,
			String txtUlasanKJP, String txtUlasanJPPH, String txtUlasanJKPTGN,
			String txtUlasanKemKewangan, String txtUlasanKemWP,
			String txtUlasanPTG, String txtUlasanDBKL, String txtUlasanBPH,
			String socPajakan, String socPenswastaan, String txtNamaPegawai,
			String txtTarikhRujukan, HttpSession session) throws Exception {

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
			r.update("ID_KERTASKERJA", idKertasKerja);
			r.add("ULASAN_KJP", txtUlasanKJP);
			r.add("ULASAN_JPPH", txtUlasanJPPH);
			r.add("ULASAN_JKPTGN", txtUlasanJKPTGN);
			r.add("ULASAN_KEM_KEWANGAN", txtUlasanKemKewangan);
			r.add("ULASAN_KEM_WP", txtUlasanKemWP);
			r.add("ULASAN_PTG", txtUlasanPTG);
			r.add("ULASAN_DBKL", txtUlasanDBKL);
			r.add("ULASAN_BPH", txtUlasanBPH);
			r.add("FLAG_PAJAKAN", socPajakan);
			r.add("FLAG_PENSWASTAAN", socPenswastaan);
			r.add("NAMA_PEGAWAI_HTP", txtNamaPegawai);
			if (!"".equals(txtTarikhRujukan)) {
				r.add("TARIKH_RUJUKAN_HTP",
						r.unquote("to_date('" + txtTarikhRujukan
								+ "','dd/MM/yyyy')"));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idKertasKerja
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
			String idSuburusan, HttpSession session) throws Exception {
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
			r.add("ID_STATUS", "1610201"); // MESYUARAT

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
					getIdSuburusanstatus(idSuburusan, "1610201")); // MESYUARAT
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] PROSES SETERUSNYA");

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
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void gotoSemakanPPNegeri(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPengarahNegeri");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA PP");

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

	public void gotoSemakanPNegeri(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPengarahNegeri");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA P");

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

	public void gotoHantarHQ(String idFail, String idNegeriUser,
			String idPermohonan, HttpSession session) throws Exception {
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);
			
			// TBLPERMOHONAN 26012018
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_HANTAR_HQ", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA HQ");

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

	public void gotoHantarTugasanPP(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA PP HQ");

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

	public void doSimpanAgihanTugas(String idFail, String idPegawai,
			String catatan, String idNegeriUser, HttpSession session)
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahHQ");
			r.add("CATATAN", catatan);
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] TELAH DITUGASKAN");

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

	public void gotoSemakanPPHQ(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

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
	}

	public void doMintaMaklumatTambahan(String idFail, String catatan,
			String idNegeriUser, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPegawai = "";
		String idNegeri = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idPegawai = rs.getString("ID_PEGAWAI");
				idNegeri = rs.getString("ID_NEGERI");
			}

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", idNegeri);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_MT", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("CATATAN", catatan);
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] TELAH DIHANTAR");

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
	
	public void doPindaanMaklumat(String idFail, String catatan,
			String idNegeriUser, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPegawai = "";
		String idNegeri = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idPegawai = rs.getString("ID_PEGAWAI");
				idNegeri = rs.getString("ID_NEGERI");
			}

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", idNegeri);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PINDAAN", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("CATATAN", catatan);
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] TELAH DIHANTAR");

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

	public String getFlagMT(String idFail, String idPegawai) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_MT FROM TBLPHPLOGTUGASAN WHERE ID_FAIL = '"
					+ idFail
					+ "' AND ID_PEGAWAI = '"
					+ idPegawai
					+ "' AND FLAG_AKTIF = 'Y' AND ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("FLAG_MT");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getFlagPindaan(String idFail, String idPegawai) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_PINDAAN FROM TBLPHPLOGTUGASAN WHERE ID_FAIL = '"
					+ idFail
					+ "' AND ID_PEGAWAI = '"
					+ idPegawai
					+ "' AND FLAG_AKTIF = 'Y' AND ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("FLAG_PINDAAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void gotoKembaliFail(String idFail, String idNegeriUser,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPegawai = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPenolongPegawaiTanahHQ' AND ID_NEGERI = 16 AND ID_FAIL = '"
					+ idFail + "' ORDER BY ID_TUGASAN DESC";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idPegawai = rs.getString("ID_PEGAWAI");
			}

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", "16");
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] TELAH DIKEMBALIKAN");

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

	public String getIdLaporanTanah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH FROM TBLPHPLAPORANTANAH WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_LAPORANTANAH");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanKertasRingkasan() {
		return beanKertasRingkasan;
	}

	public void setBeanKertasRingkasan(Vector beanKertasRingkasan) {
		this.beanKertasRingkasan = beanKertasRingkasan;
	}
}