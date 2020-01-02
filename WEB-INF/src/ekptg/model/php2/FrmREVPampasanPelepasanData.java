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

public class FrmREVPampasanPelepasanData {

	private Vector senaraiFail = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatBayaran = null;
	private Vector beanMaklumatPelarasan = null;
	private Vector senaraiAkaun = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String namaPemohon, String noRujukan,
			String idNegeri, String idDaerah, String idMukim,
			String idJenisHakmilik, String noHakmilik, String noWarta,
			String idLot, String noLot, String peganganHakmilik,
			String noRujukanPenyewa) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL, TBLRUJURUSAN.NAMA_URUSAN, TBLPHPPEMOHON.NAMA,"
					+ " TBLPHPHASIL.ID_HASIL, TBLPFDFAIL.ID_FAIL"

					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLRUJURUSAN, TBLPERMOHONAN,"
					+ " TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK"

					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL"
					+ " AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON"
					+ " AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN"
					+ " AND TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN"
					+ " AND TBLPFDFAIL.ID_URUSAN = 6"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN(+)";

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPFDFAIL.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPPEMOHON.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_NEGERI = '"
							+ idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_DAERAH = '"
							+ idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_MUKIM = '"
							+ idMukim.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql
							+ " AND UPPER(TBLPHPHAKMILIK.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql
							+ " AND UPPER(TBLPHPHAKMILIK.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_LOT = '" + idLot.trim()
							+ "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql
							+ " AND UPPER(TBLPHPHAKMILIK.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql
							+ " AND UPPER(TBLPHPHAKMILIK.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY TBLPFDFAIL.ID_FAIL ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs
						.getString("NAMA_URUSAN").toUpperCase());
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void setMaklumatBayaran(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";

		try {
			beanMaklumatBayaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, NO_RUJUKAN, KREDIT, ID_CARABAYAR, NO_RESIT, TARIKH_RESIT, ID_BANK, CATATAN"
					+ " FROM TBLPHPAKAUN WHERE ID_AKAUN = '" + idAkaun + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("tarikh",
						rs.getString("TARIKH") == null ? "" : sdf.format(rs
								.getDate("TARIKH")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("amaun",
						rs.getString("KREDIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				h.put("idCaraBayar",
						rs.getString("ID_CARABAYAR") == null ? "99999" : rs
								.getString("ID_CARABAYAR"));
				h.put("noResit",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("tarikhResit", rs.getString("TARIKH_RESIT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_RESIT")));
				h.put("idBank",
						rs.getString("ID_BANK") == null ? "" : rs
								.getString("ID_BANK"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				beanMaklumatBayaran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void setMaklumatPelarasan(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";
		String idJenisPelarasan = "99999";

		try {
			beanMaklumatPelarasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, ID_JENISPELARASAN, KREDIT, DEBIT, NO_RUJUKAN, CATATAN FROM TBLPHPAKAUN WHERE ID_AKAUN = '"
					+ idAkaun + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				if (!"".equals(rs.getString("ID_JENISPELARASAN"))) {
					idJenisPelarasan = rs.getString("ID_JENISPELARASAN");
				}
				h.put("idJenisPelarasan", idJenisPelarasan);
				h.put("tarikh",
						rs.getString("TARIKH") == null ? "" : sdf.format(rs
								.getDate("TARIKH")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				if ("D".equals(idJenisPelarasan)) {
					h.put("amaun",
							rs.getString("DEBIT") == null ? "" : Util
									.formatDecimal(Double.valueOf(rs
											.getString("DEBIT"))));
				} else if ("K".equals(idJenisPelarasan)) {
					h.put("amaun",
							rs.getString("KREDIT") == null ? "" : Util
									.formatDecimal(Double.valueOf(rs
											.getString("KREDIT"))));
				} else {
					h.put("amaun", "");
				}

				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				beanMaklumatPelarasan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPemohon(String idHasil) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPPEMOHON.ID_PEMOHON, TBLPHPPEMOHON.NAMA, TBLPHPPEMOHON.NO_PENGENALAN, TBLPHPPEMOHON.ALAMAT1_TETAP,"
					+ " TBLPHPPEMOHON.ALAMAT2_TETAP, TBLPHPPEMOHON.ALAMAT3_TETAP, TBLPHPPEMOHON.POSKOD_TETAP, TBLPHPPEMOHON.ID_BANDARTETAP,"
					+ " TBLPHPPEMOHON.ID_NEGERITETAP, TBLPHPPEMOHON.NO_TEL, TBLPHPPEMOHON.NO_FAX, TBLPHPPEMOHON.EMEL, TBLPHPPEMOHON.NO_RUJUKAN"
					+ " FROM TBLPHPPEMOHON, TBLPHPHASIL WHERE TBLPHPPEMOHON.ID_PEMOHON = TBLPHPHASIL.ID_PEMOHON AND TBLPHPHASIL.ID_HASIL = '"
					+ idHasil + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
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
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));

				beanMaklumatPemohon.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void setListAkaun(String idHasil) throws Exception {

		Db db = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiAkaun = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 34"
					+ " AND ID_HASIL = '" + idHasil + "'";

			sql = sql + " ORDER BY ID_AKAUN, TARIKH ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				if (!"".equals(rs.getString("ID_AKAUN"))) {
					idAkaun = rs.getString("ID_AKAUN");

					if ("".equals(temp)) {
						temp = idAkaun;
					} else {
						temp = temp + "," + idAkaun;
					}
				}
				h.put("idAkaun", idAkaun);
				h.put("idJenisTransaksi",
						rs.getString("ID_JENISTRANSAKSI") == null ? "" : rs
								.getString("ID_JENISTRANSAKSI"));
				h.put("tarikh",
						rs.getDate("TARIKH") == null ? "" : sdf.format(rs
								.getDate("TARIKH")));
				h.put("tarikhResit", rs.getDate("TARIKH_RESIT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_RESIT")));
				h.put("noResit",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("debit",
						rs.getString("DEBIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));

				Double totalDebit = getTotalDebit(temp);
				Double totalKredit = getTotalKredit(temp);

				Double baki = 0D;
				baki = totalDebit - totalKredit;
				if (baki > 0D) {
					h.put("baki", Util.formatDecimal(baki));
				} else if (baki < 0D) {
					h.put("baki", "(" + Util.formatDecimal(baki * -1) + ")");
				} else {
					h.put("baki", "0.00");
				}

				senaraiAkaun.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getTotalKredit(String temp) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp
					+ ")";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + Double.valueOf(rs.getDouble("KREDIT"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return total;
	}

	@SuppressWarnings("unused")
	private Double getTotalDebit(String temp) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp
					+ ")";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + Double.valueOf(rs.getDouble("DEBIT"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return total;
	}

	public Double calculateTotal(String idHasil) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 34 AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 34 AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
			}

			total = totalDebit - totalKredit;

		} finally {
			if (db != null)
				db.close();
		}
		return total;
	}

	public void updatePemohon(String idPemohon, String txtNama,
			String txtNoPendaftaran, String txtAlamat1, String txtAlamat2,
			String txtAlamat3, String txtPoskod, String idBandar,
			String idNegeri, String txtEmel, String txtNoTel, String txtNoFaks,
			String txtNoRujukan, HttpSession session) throws Exception {

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

			// TBLPHPPEMOHON
			r.update("ID_PEMOHON", idPemohon);
			r.add("NAMA", txtNama);
			r.add("NO_PENGENALAN", txtNoPendaftaran);
			r.add("ALAMAT1_TETAP", txtAlamat1);
			r.add("ALAMAT2_TETAP", txtAlamat2);
			r.add("ALAMAT3_TETAP", txtAlamat3);
			r.add("POSKOD_TETAP", txtPoskod);
			r.add("ID_BANDARTETAP", idBandar);
			r.add("ID_NEGERITETAP", idNegeri);
			r.add("NO_TEL", txtNoTel);
			r.add("NO_FAX", txtNoFaks);
			r.add("EMEL", txtEmel);
			r.add("NO_RUJUKAN", txtNoRujukan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "UPD",
					"FAIL [" + idPemohon
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

	public String simpanBayaran(String idHasil, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String amaun, String noResit, String tarikhResit, String butiran,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idAkaunString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPAKAUN
			long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			idAkaunString = String.valueOf(idAkaun);
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_HASIL", idHasil);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			r.add("ID_JENISBAYARAN", "34");
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_BANK", idBank);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "INS",
					"FAIL [" + idHasil
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
		return idAkaunString;
	}

	public void simpanKemaskiniBayaran(String idAkaun, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String amaun, String noResit, String tarikhResit, String butiran,
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

			// TBLPHPAKAUN
			r.update("ID_AKAUN", idAkaun);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_BANK", idBank);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "UPD",
					"FAIL [" + idAkaun
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

	public void hapusBayaran(String idAkaun, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPAKAUN
			sql = "DELETE FROM TBLPHPAKAUN WHERE ID_AKAUN = '" + idAkaun + "'";
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "DEL",
					"FAIL [" + idAkaun
							+ "] DIHAPUSKAN");

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

	public String simpanPelarasan(String idHasil, String tarikh,
			String idJenisPelarasan, String noRujukan, String amaun,
			String butiran, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idAkaunString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPAKAUN
			long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			idAkaunString = String.valueOf(idAkaun);
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_HASIL", idHasil);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			r.add("ID_JENISBAYARAN", "34");
			if ("D".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
			} else if ("K".equals(idJenisPelarasan)) {
				r.add("KREDIT", amaun);
			}
			r.add("ID_JENISPELARASAN", idJenisPelarasan);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}
			r.add("ID_JENISTRANSAKSI", "3"); // PELARASAN

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "INS",
					"FAIL [" + idHasil
							+ "] DITAMBAH");

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
		return idAkaunString;
	}

	public void simpanKemaskiniPelarasan(String idAkaun, String tarikh,
			String idJenisPelarasan, String noRujukan, String amaun,
			String butiran, HttpSession session) throws Exception {

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

			// TBLPHPAKAUN
			r.update("ID_AKAUN", idAkaun);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			if ("D".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("KREDIT", "");
			} else if ("K".equals(idJenisPelarasan)) {
				r.add("DEBIT", "");
				r.add("KREDIT", amaun);
			}
			r.add("ID_JENISPELARASAN", idJenisPelarasan);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "UPD",
					"FAIL [" + idAkaun
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

	public void hapusPelarasan(String idAkaun, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPAKAUN
			sql = "DELETE FROM TBLPHPAKAUN WHERE ID_AKAUN = '" + idAkaun + "'";
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity(null, "4", null, session, "DEL",
					"FAIL [" + idAkaun
							+ "] DIHAPUSKAN");

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

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatBayaran() {
		return beanMaklumatBayaran;
	}

	public void setBeanMaklumatBayaran(Vector beanMaklumatBayaran) {
		this.beanMaklumatBayaran = beanMaklumatBayaran;
	}

	public Vector getBeanMaklumatPelarasan() {
		return beanMaklumatPelarasan;
	}

	public void setBeanMaklumatPelarasan(Vector beanMaklumatPelarasan) {
		this.beanMaklumatPelarasan = beanMaklumatPelarasan;
	}

	public Vector getSenaraiAkaun() {
		return senaraiAkaun;
	}

	public void setSenaraiAkaun(Vector senaraiAkaun) {
		this.senaraiAkaun = senaraiAkaun;
	}
}
