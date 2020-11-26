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
import lebah.util.Util;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmAPBMaklumatPermohonanData {

	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPengarah = null;
	private Vector listPengarah = null;
	private Vector listPembeliPasir = null;
	private Vector beanMaklumatPembeliPasir = null;
	private Vector listProjek = null;
	private Vector listKoordinat = null;
	private Vector listKoordinatHistory = null;
	private Vector listPakar = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatProjek = null;
	private Vector beanMaklumatPakar = null;
	private Vector beanMaklumatKoordinat = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PEMOHON");
			r.add("C.ID_KATEGORIPEMOHON");
			r.add("C.NAMA");
			r.add("C.ID_JENISPENGENALAN");
			r.add("C.NO_PENGENALAN");
			r.add("C.PEKERJAAN");
			r.add("C.ID_JANTINA");
			r.add("C.ID_BANGSA");
			r.add("C.ALAMAT1_TETAP");
			r.add("C.ALAMAT2_TETAP");
			r.add("C.ALAMAT3_TETAP");
			r.add("C.POSKOD_TETAP");
			r.add("C.ID_NEGERITETAP");
			r.add("C.ID_BANDARTETAP");
			r.add("C.NO_TEL_RUMAH");
			r.add("C.NO_TEL_PEJABAT");
			r.add("C.NO_TEL_BIMBIT");
			r.add("C.NO_FAX");
			r.add("C.EMEL");
			r.add("C.MODAL_DIBENARKAN");
			r.add("C.MODAL_JELAS");

			r.add("A.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("C.ID_PEMOHON"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));

				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("idJantina", rs.getString("ID_JANTINA") == null ? "99999"
						: rs.getString("ID_JANTINA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandar",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTel",
						rs.getString("NO_TEL_RUMAH") == null ? "" : rs
								.getString("NO_TEL_RUMAH"));
				h.put("noTelBim", rs.getString("NO_TEL_BIMBIT") == null ? ""
						: rs.getString("NO_TEL_BIMBIT"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				h.put("namaSykt",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPengenalanSykt",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaanSykt", rs.getString("PEKERJAAN") == null ? ""
						: rs.getString("PEKERJAAN"));
				h.put("alamat1Sykt", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2Sykt", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3Sykt", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskodSykt", rs.getString("POSKOD_TETAP") == null ? ""
						: rs.getString("POSKOD_TETAP"));
				h.put("idNegeriSykt",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandarSykt",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTelSykt", rs.getString("NO_TEL_PEJABAT") == null ? ""
						: rs.getString("NO_TEL_PEJABAT"));
				h.put("noFaxSykt",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emelSykt",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("modalBenar",
						rs.getString("MODAL_DIBENARKAN") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("MODAL_DIBENARKAN"))));
				h.put("modalJelas",
						rs.getString("MODAL_JELAS") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("MODAL_JELAS"))));

				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePemohon(String idPemohon, Hashtable hash,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMOHON
			r.update("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", hash.get("idKategoriPemohon"));

			if ("1".equals(hash.get("idKategoriPemohon"))) {

				r.add("NAMA", hash.get("nama"));
				r.add("ID_JENISPENGENALAN",
						hash.get("idJenisPengenalanIndividu"));
				r.add("NO_PENGENALAN", hash.get("noPengenalan"));
				r.add("PEKERJAAN", hash.get("pekerjaan"));
				r.add("ID_JANTINA", hash.get("idJantina"));
				r.add("ID_BANGSA", hash.get("idBangsa"));
				r.add("ALAMAT1_TETAP", hash.get("alamat1"));
				r.add("ALAMAT2_TETAP", hash.get("alamat2"));
				r.add("ALAMAT3_TETAP", hash.get("alamat3"));
				r.add("POSKOD_TETAP", hash.get("poskod"));
				r.add("ID_BANDARTETAP", hash.get("idBandar"));
				r.add("ID_NEGERITETAP", hash.get("idNegeri"));
				r.add("NO_TEL_RUMAH", hash.get("noTel"));
				r.add("NO_TEL_PEJABAT", "");
				r.add("NO_TEL_BIMBIT", hash.get("noTelBim"));
				r.add("NO_FAX", hash.get("noFax"));
				r.add("EMEL", hash.get("emel"));
				r.add("MODAL_DIBENARKAN", "");
				r.add("MODAL_JELAS", "");

			} else if ("2".equals(hash.get("idKategoriPemohon"))) {

				r.add("NAMA", hash.get("namaSykt"));
				r.add("NO_PENGENALAN", hash.get("noPengenalanSykt"));
				r.add("PEKERJAAN", hash.get("pekerjaanSykt"));
				r.add("ID_JANTINA", "");
				r.add("ID_BANGSA", "");
				r.add("ALAMAT1_TETAP", hash.get("alamat1Sykt"));
				r.add("ALAMAT2_TETAP", hash.get("alamat2Sykt"));
				r.add("ALAMAT3_TETAP", hash.get("alamat3Sykt"));
				r.add("POSKOD_TETAP", hash.get("poskodSykt"));
				r.add("ID_BANDARTETAP", hash.get("idBandarSykt"));
				r.add("ID_NEGERITETAP", hash.get("idNegeriSykt"));
				r.add("NO_TEL_RUMAH", "");
				r.add("NO_TEL_PEJABAT", hash.get("noTelSykt"));
				r.add("NO_FAX", hash.get("noFaxSykt"));
				r.add("EMEL", hash.get("emelSykt"));
				r.add("MODAL_DIBENARKAN",
						Utils.RemoveSymbol((String) hash.get("modalBenar")));
				r.add("MODAL_JELAS",
						Utils.RemoveSymbol((String) hash.get("modalJelas")));

			}
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPemohon + "] DIKEMASKINI");

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

	public void setSenaraiPengarah(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT A.ID_PENGARAH, A.NAMA, ID_JENISPENGENALAN, A.NO_PENGENALAN, "
					+ " A.ID_WARGANEGARA, A.ID_BANGSA,B.KETERANGAN, A.PEGANGAN_SAHAM AS SAHAM, "
					+ " (SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon
					+ "') AS TOTAL, "
					+ " ROUND(A.PEGANGAN_SAHAM/(SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon
					+ "')*100) AS PERCENTAGE "
					+ " FROM TBLPHPPENGARAH A, TBLRUJWARGANEGARA B "
					+ " WHERE A.ID_PEMOHON = '"
					+ idPemohon
					+ "' AND A.ID_WARGANEGARA = B.ID_WARGANEGARA ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPengarah", rs.getString("ID_PENGARAH"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("idWarganegara",
						rs.getString("ID_WARGANEGARA") == null ? "99999" : rs
								.getString("ID_WARGANEGARA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("saham",
						rs.getString("PERCENTAGE") == null ? "" : rs
								.getString("PERCENTAGE"));
				h.put("peganganSaham",
						rs.getString("SAHAM") == null ? "" : rs
								.getString("SAHAM"));
				h.put("peganganSaham",
						rs.getString("SAHAM") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("SAHAM"))));
				h.put("warganegara", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());

				listPengarah.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPengarah(String idPengarah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PENGARAH");
			r.add("NAMA");
			r.add("ID_JENISPENGENALAN");
			r.add("NO_PENGENALAN");
			r.add("ID_WARGANEGARA");
			r.add("WARGANEGARA_LAIN");
			r.add("ID_BANGSA");
			r.add("BANGSA_LAIN");
			r.add("PEGANGAN_SAHAM");

			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLSelect("TBLPHPPENGARAH");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPengarah", rs.getString("ID_PENGARAH"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("idWarganegara",
						rs.getString("ID_WARGANEGARA") == null ? "99999" : rs
								.getString("ID_WARGANEGARA"));
				h.put("warganegaraLain",
						rs.getString("WARGANEGARA_LAIN") == null ? "" : rs
								.getString("WARGANEGARA_LAIN"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("bangsaLain",
						rs.getString("BANGSA_LAIN") == null ? "" : rs
								.getString("BANGSA_LAIN"));
				h.put("saham",
						rs.getString("PEGANGAN_SAHAM") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("PEGANGAN_SAHAM"))));

				beanMaklumatPengarah.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePengarah(String idPemohon, String idWarganegara,
			String nama, String idJenisPengenalan, String idBangsa,
			String noPengenalan, String saham, String bangsaLain, String warganegaraLain, 
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPengarahString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			long idPengarah = DB.getNextID("TBLPHPPENGARAH_SEQ");
			idPengarahString = String.valueOf(idPengarah);
			r.add("ID_PENGARAH", idPengarah);
			r.add("ID_PEMOHON", idPemohon);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("ID_BANGSA", idBangsa);
			r.add("PEGANGAN_SAHAM", saham);
			r.add("WARGANEGARA_LAIN", warganegaraLain);
			r.add("BANGSA_LAIN", bangsaLain);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENGARAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPemohon + "] DIDAFTARKAN");

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
		return idPengarahString;
	}

	public void updatePengarah(String idPengarah, String idWarganegara,
			String nama, String idJenisPengenalan, String idBangsa,
			String noPengenalan, String saham, String bangsaLain, String warganegaraLain, 
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			r.update("ID_PENGARAH", idPengarah);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("ID_BANGSA", idBangsa);
			r.add("PEGANGAN_SAHAM", Utils.RemoveSymbol((String) saham));
			r.add("WARGANEGARA_LAIN", warganegaraLain);
			r.add("BANGSA_LAIN", bangsaLain);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENGARAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPengarah + "] DIKEMASKINI");

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

	public void removePengarah(String idPengarah, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLDelete("TBLPHPPENGARAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idPengarah + "] DIHAPUSKAN");

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

	public boolean checkPercentagePengarah(String idPemohon, String saham)
			throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0) {
			total = total + Double.valueOf(saham);
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}

			if (total > 100) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkPercentagePengarahForUpdate(String idPemohon,
			String idPengarah, String saham) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0) {
			total = total + Double.valueOf(saham);
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon + "' AND ID_PENGARAH != '" + idPengarah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}

			if (total > 100) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPembeliPasir(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMBELIPASIR, NAMA FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPembeliPasir", rs.getString("ID_PEMBELIPASIR"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));

				listPembeliPasir.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPembeliPasir(String idPembeliPasir) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PEMBELIPASIR");
			r.add("NAMA");
			r.add("ALAMAT1");
			r.add("ALAMAT2");
			r.add("ALAMAT3");
			r.add("POSKOD");
			r.add("ID_NEGERI");
			r.add("ID_BANDAR");
			r.add("NO_TEL_PEJABAT");
			r.add("NO_FAX");
			r.add("FLAG_JENIS_PERJANJIAN");

			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLSelect("TBLPHPPEMBELIPASIR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPembeliPasir", rs.getString("ID_PEMBELIPASIR"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("alamat1",
						rs.getString("ALAMAT1") == null ? "" : rs
								.getString("ALAMAT1"));
				h.put("alamat2",
						rs.getString("ALAMAT2") == null ? "" : rs
								.getString("ALAMAT2"));
				h.put("alamat3",
						rs.getString("ALAMAT3") == null ? "" : rs
								.getString("ALAMAT3"));
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999"
						: rs.getString("ID_NEGERI"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "99999"
						: rs.getString("ID_BANDAR"));
				h.put("noTel",
						rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
								.getString("NO_TEL_PEJABAT"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("idJenisPerjanjian", rs
						.getString("FLAG_JENIS_PERJANJIAN") == null ? "99999"
						: rs.getString("FLAG_JENIS_PERJANJIAN"));

				beanMaklumatPembeliPasir.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePembeliPasir(String idPermohonan, String nama,
			String alamat1, String alamat2, String alamat3, String poskod,
			String idNegeri, String idBandar, String noTel, String noFax,
			String idJenisPerjanjian, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPembeliPasirString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			long idPembeliPasir = DB.getNextID("TBLPHPPEMBELIPASIR_SEQ");
			idPembeliPasirString = String.valueOf(idPembeliPasir);
			r.add("ID_PEMBELIPASIR", idPembeliPasir);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMBELIPASIR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");

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
		return idPembeliPasirString;
	}

	public void updatePembeliPasir(String idPembeliPasir, String nama,
			String alamat1, String alamat2, String alamat3, String poskod,
			String idNegeri, String idBandar, String noTel, String noFax,
			String idJenisPerjanjian, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			r.update("ID_PEMBELIPASIR", idPembeliPasir);

			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMBELIPASIR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPembeliPasir + "] DIKEMASKINI");

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

	public void removePembeliPasir(String idPembeliPasir, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLDelete("TBLPHPPEMBELIPASIR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idPembeliPasir + "] DIHAPUSKAN");

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

	public void setSenaraiProjek(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PROJEKLESENAPB");
			r.add("NAMA_PROJEK");

			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB",
					"ID_PROJEKLESENAPB ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idProjek", rs.getString("ID_PROJEKLESENAPB"));
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK").toUpperCase());

				listProjek.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatProjek(String idProjek) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PROJEK");

			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK").toUpperCase());

				beanMaklumatProjek.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String saveProjek(String idPermohonan, String nama,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idProjekString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			long idProjek = DB.getNextID("TBLPHPPROJEKLESENAPB_SEQ");
			idProjekString = String.valueOf(idProjek);
			r.add("ID_PROJEKLESENAPB", idProjek);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA_PROJEK", nama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");

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
		return idProjekString;
	}

	public void updateProjek(String idProjek, String nama, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.update("ID_PROJEKLESENAPB", idProjek);
			r.add("NAMA_PROJEK", nama);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idProjek + "] DIKEMASKINI");

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

	public void removeProjek(String idProjek, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLDelete("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idProjek + "] DIHAPUSKAN");

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

	public void setSenaraiKoordinat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT TBLPHPKOORDINATPERMOHONAN.ID_KOORDINATPERMOHONAN, TBLPHPKOORDINATPERMOHONAN.LABEL_TITIK, "
					+ "TBLPHPKOORDINATPERMOHONAN.DARJAH_U, TBLPHPKOORDINATPERMOHONAN.DARJAH_T, "
					+ "TBLPHPKOORDINATPERMOHONAN.MINIT_U, " 
					+ "TBLPHPKOORDINATPERMOHONAN.MINIT_T, " 
					+ "TBLPHPKOORDINATPERMOHONAN.SAAT_U, " 
					+ "TBLPHPKOORDINATPERMOHONAN.SAAT_T " 
					+ "FROM TBLPHPKOORDINATPERMOHONAN "
					+ "WHERE "
					+ "TBLPHPKOORDINATPERMOHONAN.ID_ULASANTEKNIKAL IS NULL "
					+ "AND TBLPHPKOORDINATPERMOHONAN.ID_PERMOHONAN = '"
					//roszai - edit untuk sort by history (17072020)
					//+ idPermohonan + "'" + " ORDER BY LABEL_TITIK ASC ";
					+ idPermohonan + "' AND TBLPHPKOORDINATPERMOHONAN.FLAG_HISTORY = 'N' ORDER BY TARIKH_MASUK DESC ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat", rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				h.put("darjahU",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				h.put("minitU",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				h.put("saatU",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				h.put("darjahT",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				h.put("minitT",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				h.put("saatT",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				listKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiKoordinatHistory(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKoordinatHistory = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT TBLPHPKOORDINATPERMOHONAN.ID_KOORDINATPERMOHONAN, TBLPHPKOORDINATPERMOHONAN.LABEL_TITIK, "
					+ "TBLPHPKOORDINATPERMOHONAN.DARJAH_U, TBLPHPKOORDINATPERMOHONAN.DARJAH_T, "
					+ "TBLPHPKOORDINATPERMOHONAN.MINIT_U, TBLPHPKOORDINATPERMOHONAN.MINIT_T, " 
					+ "TBLPHPKOORDINATPERMOHONAN.SAAT_U, TBLPHPKOORDINATPERMOHONAN.SAAT_T " 
					+ "FROM TBLPHPKOORDINATPERMOHONAN "
					+ "WHERE TBLPHPKOORDINATPERMOHONAN.ID_ULASANTEKNIKAL IS NULL "
					+ "AND TBLPHPKOORDINATPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan + "' "
					//roszai - edit untuk sort by history (17072020)
					//+ idPermohonan + "'" + " ORDER BY LABEL_TITIK ASC ";
					+ "AND TBLPHPKOORDINATPERMOHONAN.FLAG_HISTORY = 'Y' ORDER BY TARIKH_MASUK ASC ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat", rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				h.put("darjahU",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				h.put("minitU",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				h.put("saatU",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				h.put("darjahT",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				h.put("minitT",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				h.put("saatT",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				listKoordinatHistory.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String saveKoordinat(String idKoordinat_original, String idPermohonan, String labelTitik,
			String darjahU, String minitU, String saatU, String darjahT,
			String minitT, String saatT, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idKoordinatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(!idKoordinat_original.equals("$beanMaklumatKoordinat.idKoordinat_original")){
				// KEMASKINI SET KOORDINAT LAMA KE HISTORY		
				sql = "UPDATE TBLPHPKOORDINATPERMOHONAN SET FLAG_HISTORY='Y' WHERE ID_KOORDINATPERMOHONAN=" + idKoordinat_original;
				//r.update("ID_KOORDINATPERMOHONAN", idKoordinat_original);
				//r.add("FLAG_HISTORY", 'Y');
				//sql = r.getSQLUpdate("TBLPHPKOORDINATPERMOHONAN");
				//System.out.println(sql);
				stmt.executeUpdate(sql);
			}

			// TBLPHPKOORDINATPERMOHONAN
			r = new SQLRenderer();
			long idKoordinat = DB.getNextID("TBLPHPKOORDINATPERMOHONAN_SEQ");
			idKoordinatString = String.valueOf(idKoordinat);
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);
			r.add("FLAG_HISTORY", "N");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
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
		return idKoordinatString;
	}

	public void updateKoordinat(String idKoordinat, String labelTitik,
			String darjahU, String minitU, String saatU, String darjahT,
			String minitT, String saatT, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKOORDINATPERMOHONAN
			r.update("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKOORDINATPERMOHONAN");

			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idKoordinat + "] DIKEMASKINI");

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

	public void removeKoordinat(String idKoordinat, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKOORDINATPERMOHONAN
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLDelete("TBLPHPKOORDINATPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idKoordinat + "] DIHAPUSKAN");

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

	public void setMaklumatKoordinat(String idKoordinat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("LABEL_TITIK");
			r.add("DARJAH_U");
			r.add("MINIT_U");
			r.add("SAAT_U");
			r.add("DARJAH_T");
			r.add("MINIT_T");
			r.add("SAAT_T");
			r.add("ID_KOORDINATPERMOHONAN");

			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLSelect("TBLPHPKOORDINATPERMOHONAN");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat_original", rs.getString("ID_KOORDINATPERMOHONAN") == null ? ""
						: rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				h.put("darjahU",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				h.put("minitU",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				h.put("saatU",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				h.put("darjahT",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				h.put("minitT",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				h.put("saatT",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				beanMaklumatKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPakar(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MAKLUMATPAKAR");
			r.add("NAMA");
			r.add("KELAYAKAN");

			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR", "ID_MAKLUMATPAKAR ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPakar", rs.getString("ID_MAKLUMATPAKAR"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("kelayakan",
						rs.getString("KELAYAKAN") == null ? "" : rs
								.getString("KELAYAKAN"));

				listPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePakar(String idPermohonan, String nama, String kelayakan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPakarString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			long idPakar = DB.getNextID("TBLPHPMAKLUMATPAKAR_SEQ");
			idPakarString = String.valueOf(idPakar);
			r.add("ID_MAKLUMATPAKAR", idPakar);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMAKLUMATPAKAR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");

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
		return idPakarString;
	}

	public void updatePakar(String idPakar, String nama, String kelayakan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			r.update("ID_MAKLUMATPAKAR", idPakar);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMAKLUMATPAKAR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPakar + "] DIKEMASKINI");

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

	public void removePakar(String idPakar, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLDelete("TBLPHPMAKLUMATPAKAR");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idPakar + "] DIHAPUSKAN");

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

	public void setMaklumatPakar(String idPakar) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA");
			r.add("KELAYAKAN");

			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA").toUpperCase());
				h.put("kelayakan", rs.getString("KELAYAKAN") == null ? "" : rs
						.getString("KELAYAKAN").toUpperCase());

				beanMaklumatPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.ID_KAITANTUJUAN");
			r.add("B.TUJUAN_PENGAMBILAN");
			r.add("B.TEMPOH_DIPOHON");
			r.add("B.ID_TEMPOH");
			r.add("B.PENGALAMAN");
			r.add("B.FLAG_LUAR_PERAIRANNEGERI");
			r.add("B.LUAS_DIPOHON");
			r.add("B.ID_UNITLUAS");
			r.add("B.LOKASI_PERMOHONAN");
			r.add("B.ID_NEGERI_PERAIRAN");
			r.add("C.TUJUAN");
			r.add("C.TARIKH_SURAT");
			r.add("C.TARIKH_TERIMA");
			r.add("C.NO_RUJ_SURAT");
			r.add("C.ID_PERMOHONAN", r.unquote("B.ID_PERMOHONAN"));
			r.add("B.ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPPMOHONNJDUALPERTAMA B, TBLPERMOHONAN C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idKaitanTujuan",
						rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs
								.getString("ID_KAITANTUJUAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT"));
				h.put("perkara",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("tujuanPengambilan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs
								.getString("TUJUAN_PENGAMBILAN"));
				h.put("tempoh", rs.getString("TEMPOH_DIPOHON") == null ? ""
						: rs.getString("TEMPOH_DIPOHON"));
				h.put("idTempoh", rs.getString("ID_TEMPOH") == null ? "99999"
						: rs.getString("ID_TEMPOH"));
				h.put("pengalaman", rs.getString("PENGALAMAN") == null ? ""
						: rs.getString("PENGALAMAN"));
				h.put("idFlagLuar",
						rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "99999"
								: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("luas",
						rs.getString("LUAS_DIPOHON") == null ? "" : rs
								.getString("LUAS_DIPOHON"));
				h.put("idLuas", rs.getString("ID_UNITLUAS") == null ? "99999"
						: rs.getString("ID_UNITLUAS"));
				h.put("idNegeri",
						rs.getString("ID_NEGERI_PERAIRAN") == null ? "99999"
								: rs.getString("ID_NEGERI_PERAIRAN"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? ""
						: rs.getString("LOKASI_PERMOHONAN"));
				beanMaklumatPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonan(String idFail, String idPermohonan,
			String tarikhTerima, String tarikhSurat, String noRujSurat, String txtPerkara,
			String idKaitanTujuan, String tujuanPengambilan, String tempoh,
			String idTempoh, String pengalaman, String idFlagLuar,
			String idNegeri, String lokasi, String luas, String idLuas,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", txtPerkara);
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("NO_RUJ_SURAT", noRujSurat);
			r.add("TUJUAN", txtPerkara);
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_JENISTUJUAN", 3);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", idTempoh);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", idFlagLuar);
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idFail + "] DIKEMASKINI");

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
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610199"); //JABATAN TEKNIKAL

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610199")); //JABATAN TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			// TBLPHPKERTASKERJAAPB
			sql = "SELECT FLAG_KERTAS FROM TBLPHPKERTASKERJAAPB WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				r = new SQLRenderer();
	    		r.update("ID_PERMOHONAN", idPermohonan);
				r.update("FLAG_KERTAS", "1");
				sql = r.getSQLDelete("TBLPHPKERTASKERJAAPB");
				stmt.executeUpdate(sql);
			}

			// TBLPHPKERTASKERJAAPB
			r = new SQLRenderer();
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAAPB_SEQ");
			r.add("ID_KERTASKERJAAPB", idKertasKerja);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KERTAS", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatPengarah() {
		return beanMaklumatPengarah;
	}

	public void setBeanMaklumatPengarah(Vector beanMaklumatPengarah) {
		this.beanMaklumatPengarah = beanMaklumatPengarah;
	}

	public Vector getListPengarah() {
		return listPengarah;
	}

	public void setListPengarah(Vector listPengarah) {
		this.listPengarah = listPengarah;
	}

	public Vector getListPembeliPasir() {
		return listPembeliPasir;
	}

	public void setListPembeliPasir(Vector listPembeliPasir) {
		this.listPembeliPasir = listPembeliPasir;
	}

	public Vector getBeanMaklumatPembeliPasir() {
		return beanMaklumatPembeliPasir;
	}

	public void setBeanMaklumatPembeliPasir(Vector beanMaklumatPembeliPasir) {
		this.beanMaklumatPembeliPasir = beanMaklumatPembeliPasir;
	}

	public Vector getListProjek() {
		return listProjek;
	}

	public void setListProjek(Vector listProjek) {
		this.listProjek = listProjek;
	}

	public Vector getListKoordinat() {
		return listKoordinat;
	}

	public void setListKoordinat(Vector listKoordinat) {
		this.listKoordinat = listKoordinat;
	}
	
	public Vector getListKoordinatHistory() {
		return listKoordinatHistory;
	}

	public void setListKoordinatHistory(Vector listKoordinatHistory) {
		this.listKoordinatHistory = listKoordinatHistory;
	}

	public Vector getListPakar() {
		return listPakar;
	}

	public void setListPakar(Vector listPakar) {
		this.listPakar = listPakar;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatProjek() {
		return beanMaklumatProjek;
	}

	public void setBeanMaklumatProjek(Vector beanMaklumatProjek) {
		this.beanMaklumatProjek = beanMaklumatProjek;
	}

	public Vector getBeanMaklumatPakar() {
		return beanMaklumatPakar;
	}

	public void setBeanMaklumatPakar(Vector beanMaklumatPakar) {
		this.beanMaklumatPakar = beanMaklumatPakar;
	}

	public Vector getBeanMaklumatKoordinat() {
		return beanMaklumatKoordinat;
	}

	public void setBeanMaklumatKoordinat(Vector beanMaklumatKoordinat) {
		this.beanMaklumatKoordinat = beanMaklumatKoordinat;
	}
}
