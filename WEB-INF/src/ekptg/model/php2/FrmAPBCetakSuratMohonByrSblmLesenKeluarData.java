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

public class FrmAPBCetakSuratMohonByrSblmLesenKeluarData {

	private Vector beanMaklumatBayaranSblm = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatBayaranSblm(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBayaranSblm = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BYRNSYRTKLLSNLESENAPB, ULASAN_EIA, ULASAN_HIDROGRAFI, LUAS_KAWASAN, ID_LUAS, KADAR_FEELESEN,JUMLAH_FEELESEN, FEELESEN_BAG_SETIAP, "
					+ " WANG_CAGARAN_PEMATUHAN,WANG_AMANAH_NELAYAN, MAKLUMAT_TAMBAHAN,ID_UNITISIPADU,ISIPADU,KADAR_ROYALTI_PASIR,"
					+ " JUMLAH_ROYALTI_SELURUH, KADAR_DAHULU_ROYALTI,JUMLAH_DAHULU_ROYALTI, SYARAT_LULUS_LAMPIRAN "
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB "
					+ " WHERE FLAG_AKTIF = 'Y' AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idByrnsyrtkllsnlesenapb", rs
						.getString("ID_BYRNSYRTKLLSNLESENAPB") == null ? ""
						: rs.getString("ID_BYRNSYRTKLLSNLESENAPB"));
				h.put("txtUlasanEIA", rs.getString("ULASAN_EIA") == null ? ""
						: rs.getString("ULASAN_EIA"));
				h.put("txtUlasanHidro",
						rs.getString("ULASAN_HIDROGRAFI") == null ? "" : rs
								.getString("ULASAN_HIDROGRAFI"));
				h.put("txtLuasKawasan",
						rs.getString("LUAS_KAWASAN") == null ? "" : rs
								.getString("LUAS_KAWASAN"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "99999" : rs
								.getString("ID_LUAS"));
				h.put("txtFeeLesen",
						rs.getString("KADAR_FEELESEN") == null ? "0.00 " : Util
								.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_FEELESEN"))));
				h.put("txtJumlahFeeLesen",
						rs.getString("JUMLAH_FEELESEN") == null ? "0.00" : Util
								.formatDecimal(Double.parseDouble(rs
										.getString("JUMLAH_FEELESEN"))));
				h.put("txtKmPersegi",
						rs.getString("FEELESEN_BAG_SETIAP") == null ? "0.0"
								: Double.parseDouble(rs
										.getString("FEELESEN_BAG_SETIAP")));
				h.put("txtWangCagaran",
						rs.getString("WANG_CAGARAN_PEMATUHAN") == null ? "0.00 "
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("WANG_CAGARAN_PEMATUHAN"))));
				h.put("txtWangAmanah",
						rs.getString("WANG_AMANAH_NELAYAN") == null ? "0.00 "
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("WANG_AMANAH_NELAYAN"))));
				h.put("txtMaklumatTambahan",
						rs.getString("MAKLUMAT_TAMBAHAN") == null ? "" : rs
								.getString("MAKLUMAT_TAMBAHAN"));
				h.put("idUnitIsipadu",
						rs.getString("ID_UNITISIPADU") == null ? "" : rs
								.getString("ID_UNITISIPADU"));
				h.put("txtIsipadu",
						rs.getString("ISIPADU") == null ? "" : rs
								.getString("ISIPADU"));
				h.put("txtKadarRoyaltiPasir",
						rs.getString("KADAR_ROYALTI_PASIR") == null ? "0.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_ROYALTI_PASIR"))));
				h.put("txtJumlahRoyaltiSeluruh",
						rs.getString("JUMLAH_ROYALTI_SELURUH") == null ? "0.00"
								: Utils.RemoveSymbol(rs
										.getString("JUMLAH_ROYALTI_SELURUH")));
				h.put("txtKadarPendhuluan",
						rs.getString("KADAR_DAHULU_ROYALTI") == null ? "0" : rs
								.getString("KADAR_DAHULU_ROYALTI"));
				h.put("txtJumDahuluRoyalti",
						rs.getString("JUMLAH_DAHULU_ROYALTI") == null ? "0.00"
								: Utils.RemoveSymbol(rs
										.getString("JUMLAH_DAHULU_ROYALTI")));
				h.put("txtSyaratKelulusan", rs
						.getString("SYARAT_LULUS_LAMPIRAN") == null ? ""
						: rs.getString("SYARAT_LULUS_LAMPIRAN"));
				beanMaklumatBayaranSblm.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatBayaranSblm(String idPermohonan,
			String txtUlasanEIA, String txtUlasanHidro, String txtLuasKawasan,
			String idLuas, String txtFeeLesen, String txtJumlahFeeLesen,
			String txtKmPersegi, String txtWangCagaran, String txtWangAmanah,
			String txtMaklumatTambahan, String txtIsipadu,
			String txtKadarRoyaltiPasir, String txtJumlahRoyaltiSeluruh,
			String txtJumDahuluRoyalti, String txtSyaratKelulusan,
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

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_AKTIF", "Y");
			
			r.add("ULASAN_EIA", txtUlasanEIA);
			r.add("ULASAN_HIDROGRAFI", txtUlasanHidro);
			r.add("LUAS_KAWASAN", txtLuasKawasan);
			r.add("ID_LUAS", idLuas);
			r.add("JUMLAH_FEELESEN", txtJumlahFeeLesen);
			r.add("KADAR_FEELESEN", txtFeeLesen);
			r.add("FEELESEN_BAG_SETIAP", txtKmPersegi);
			r.add("WANG_CAGARAN_PEMATUHAN", txtWangCagaran);
			r.add("WANG_AMANAH_NELAYAN", txtWangAmanah);
			r.add("MAKLUMAT_TAMBAHAN", txtMaklumatTambahan);
			r.add("ISIPADU", txtIsipadu);
			r.add("KADAR_ROYALTI_PASIR", txtKadarRoyaltiPasir);
			r.add("JUMLAH_ROYALTI_SELURUH", txtJumlahRoyaltiSeluruh);
			r.add("JUMLAH_DAHULU_ROYALTI", txtJumDahuluRoyalti);
			r.add("SYARAT_LULUS_LAMPIRAN", txtSyaratKelulusan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610237", "4", null, session, "UPD",
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

	public void updateStatus(String idFail, String idPermohonan, String jumlahFiLesen,
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
			r.add("ID_STATUS", "1610238"); //PENYEDIAAN LESEN DAN MEMO KEPADA MENTERI

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610238")); //PENYEDIAAN LESEN DAN MEMO KEPADA MENTERI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			// TBLPHPAKAUN - FI LESEN
			r = new SQLRenderer();
			long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_PHPPMOHONNJDUALPERTAMA", getIdJadualPertamaFromIdPermohonan(idPermohonan));
			r.add("TARIKH",r.unquote("SYSDATE"));
			r.add("ID_JENISBAYARAN", "19");
			r.add("DEBIT", jumlahFiLesen);
			r.add("CATATAN", "FI LESEN");
			r.add("ID_JENISTRANSAKSI", "1"); // CAJ
			r.add("FLAG_DEPOSIT", "T");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);	
			
			// TBLPHPAKAUN - BAYARAN DEPOSIT LESEN APB
			r = new SQLRenderer();
			idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_PHPPMOHONNJDUALPERTAMA", getIdJadualPertamaFromIdPermohonan(idPermohonan));
			r.add("TARIKH",r.unquote("SYSDATE"));
			r.add("ID_JENISBAYARAN", "13");
			r.add("DEBIT", 100000);
			r.add("CATATAN", "BAYARAN DEPOSIT LESEN APB");
			r.add("ID_JENISTRANSAKSI", "1"); // CAJ
			r.add("FLAG_DEPOSIT", "Y");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610238", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] PROSES SETERUSNYA");

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
	
	public String getIdJadualPertamaFromIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PHPPMOHONNJDUALPERTAMA FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PHPPMOHONNJDUALPERTAMA");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatBayaranSblm() {
		return beanMaklumatBayaranSblm;
	}
}