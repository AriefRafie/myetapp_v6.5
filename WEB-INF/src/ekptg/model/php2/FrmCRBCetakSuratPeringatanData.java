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

public class FrmCRBCetakSuratPeringatanData {

	private Vector listPenceroboh = null;
	private Vector beanMaklumatPenceroboh = null;
	private Vector listNotis = null;
	private Vector beanMaklumatNotis = null;
	private Vector beanMaklumatPejabat = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiNotis(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotis = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_PEJABAT, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, A.ID_DOKUMEN,"
					+ " A.FLAG_AKTIF, A.BIL_ULANGAN, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+) AND A.ID_DOKUMEN = C.ID_DOKUMEN(+)"
					+ " AND A.ID_DOKUMEN IN (6,9) AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

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

	public void setSenaraiPenceroboh(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPenceroboh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PENCEROBOH, NAMA, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, ID_BANDAR FROM TBLPHPPENCEROBOH"
					+ " WHERE ID_LAPORANTANAH IS NULL AND ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY NAMA ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenceroboh",
						rs.getString("ID_PENCEROBOH") == null ? "" : rs
								.getString("ID_PENCEROBOH"));
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
				listPenceroboh.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatNotis(String idPermohonan, String idDokumen,
			String idPejabat, String txtTarikhHantar, HttpSession session)
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
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "NOTIS");
			r.add("ID_PEJABAT", idPejabat);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
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

	public void simpanKemaskiniMaklumatNotis(String idUlasanTeknikal,
			String idDokumen, String idPejabat, String txtTarikhHantar,
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "NOTIS");
			r.add("ID_PEJABAT", idPejabat);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}

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

	public void hapusMaklumatNotis(String idUlasanTeknikal) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

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
			r.add("ID_STATUS", "1610216"); // CETAKAN SURAT TINDAKAN
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("56", "1610216")); // CETAKAN
																				// SURAT
																				// TINDAKAN
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

	public String getIdPejabatPTD(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEJABAT FROM TBLRUJPEJABAT A, TBLHTPHAKMILIK B WHERE A.ID_DAERAH = B.ID_DAERAH AND A.ID_SEKSYEN = '1'"
					+ " AND A.ID_JENISPEJABAT = '2' AND B.ID_HAKMILIK = '"
					+ idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PEJABAT");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatNotis(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatNotis = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, ID_DOKUMEN, ID_PEJABAT, TARIKH_HANTAR, FLAG_STATUS, FLAG_AKTIF"
					+ " FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "99999"
						: rs.getString("ID_PEJABAT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "99999"
						: rs.getString("ID_DOKUMEN"));
				h.put("flagStatus",
						rs.getString("FLAG_STATUS") == null ? "99999" : rs
								.getString("FLAG_STATUS"));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				beanMaklumatNotis.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPenceroboh(String idPenceroboh) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenceroboh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA, ID_BANGSA, NO_TEL_RUMAH, NO_TEL_BIMBIT, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD,"
					+ " ID_NEGERI,ID_BANDAR, EMEL"
					+ " FROM TBLPHPPENCEROBOH WHERE ID_PENCEROBOH = '"
					+ idPenceroboh + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("noTelefon", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("noTelefonBimbit",
						rs.getString("NO_TEL_BIMBIT") == null ? "" : rs
								.getString("NO_TEL_BIMBIT"));
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
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPenceroboh.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePenceroboh(String idPermohonan, String namaPenceroboh,
			String noTelefon, String noTelefonBimbit, String idBangsa,
			String alamat1, String alamat2, String alamat3, String poskod,
			String idNegeri, String idBandar, String emel, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPencerobohString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENCEROBOH
			long idPenceroboh = DB.getNextID("TBLPHPPENCEROBOH_SEQ");
			r.add("ID_PENCEROBOH", idPenceroboh);
			idPencerobohString = String.valueOf(idPenceroboh);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", namaPenceroboh);
			r.add("ID_BANGSA", idBangsa);
			r.add("NO_TEL_RUMAH", noTelefon);
			r.add("NO_TEL_BIMBIT", noTelefonBimbit);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("EMEL", emel);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENCEROBOH");
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
		return idPencerobohString;
	}

	public void updatePenceroboh(String idPenceroboh, String namaPenceroboh,
			String noTelefon, String noTelefonBimbit, String idBangsa,
			String alamat1, String alamat2, String alamat3, String poskod,
			String idNegeri, String idBandar, String emel, HttpSession session)
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

			// TBLPHPPENCEROBOH
			r.update("ID_PENCEROBOH", idPenceroboh);
			r.add("NAMA", namaPenceroboh);
			r.add("ID_BANGSA", idBangsa);
			r.add("NO_TEL_RUMAH", noTelefon);
			r.add("NO_TEL_BIMBIT", noTelefonBimbit);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("EMEL", emel);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENCEROBOH");
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

	public void hapusMaklumatPenceroboh(String idPenceroboh) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENCEROBOH
			r.add("ID_PENCEROBOH", idPenceroboh);
			sql = r.getSQLDelete("TBLPHPPENCEROBOH");
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

	public Vector getListPenceroboh() {
		return listPenceroboh;
	}

	public void setListPenceroboh(Vector listPenceroboh) {
		this.listPenceroboh = listPenceroboh;
	}

	public Vector getBeanMaklumatPenceroboh() {
		return beanMaklumatPenceroboh;
	}

	public void setBeanMaklumatPenceroboh(Vector beanMaklumatPenceroboh) {
		this.beanMaklumatPenceroboh = beanMaklumatPenceroboh;
	}

	public Vector getListNotis() {
		return listNotis;
	}

	public void setListNotis(Vector listNotis) {
		this.listNotis = listNotis;
	}

	public Vector getBeanMaklumatNotis() {
		return beanMaklumatNotis;
	}

	public void setBeanMaklumatNotis(Vector beanMaklumatNotis) {
		this.beanMaklumatNotis = beanMaklumatNotis;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}
}
