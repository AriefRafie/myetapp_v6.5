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
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmAPBMaklumatKelulusanHidrografiData {

	private Vector beanMaklumatJAS = null;
	private Vector beanMaklumatHIDRO = null;
	private Vector listDokumenHidro = null;
	private Vector listDokumenJAS = null;
	private Vector beanMaklumatDokumen = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date currentDate = new Date();

	public void setMaklumatJAS(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJAS = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BYRNSYRTKLLSNLESENAPB, NO_FAIL_JAS , TARIKH_LULUS_JAS, TARIKH_TERIMA_JAS, TARIKH_SURAT_JAS, KEPUTUSAN_JAS, TEMPOH_KELULUSAN_JAS, "
					+ " TEMPOH_AKHIR_LULUSJAS,ID_CAWANGANJAS, TARIKH_TAMAT_KELULUSANDASAR,ID_TEMPOHJAS "
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
				h.put("txtNoRujJAS", rs.getString("NO_FAIL_JAS") == null ? ""
						: rs.getString("NO_FAIL_JAS"));
				h.put("txtTarikhSuratJAS",
						rs.getDate("TARIKH_SURAT_JAS") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_JAS")));
				h.put("txtTarikhTerimaJAS",
						rs.getDate("TARIKH_TERIMA_JAS") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_JAS")));
				h.put("socKeputusanJAS",
						rs.getString("KEPUTUSAN_JAS") == null ? "" : rs
								.getString("KEPUTUSAN_JAS"));
				h.put("txtTempohLulusJAS",
						rs.getString("TEMPOH_KELULUSAN_JAS") == null ? "" : rs
								.getString("TEMPOH_KELULUSAN_JAS"));
				h.put("txtTarikhLulusJAS",
						rs.getDate("TARIKH_LULUS_JAS") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LULUS_JAS")));
				h.put("txtTempohAkhirLulusJAS",
						rs.getString("TEMPOH_AKHIR_LULUSJAS") == null ? "" : rs
								.getString("TEMPOH_AKHIR_LULUSJAS"));
				h.put("socJAS",
						rs.getString("ID_CAWANGANJAS") == null ? "99999" : rs
								.getString("ID_CAWANGANJAS"));
				h.put("socTempoh",
						rs.getString("ID_TEMPOHJAS") == null ? "99999" : rs
								.getString("ID_TEMPOHJAS"));
				h.put("tarikhTamatKelulusanDasar", rs
						.getDate("TARIKH_TAMAT_KELULUSANDASAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
				beanMaklumatJAS.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatHIDRO(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHIDRO = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BYRNSYRTKLLSNLESENAPB, NO_FAIL_PHN, TARIKH_LULUSPHN, TARIKH_SURAT_PHN, TARIKH_TERIMA_PHN,KEPUTUSAN_PHN, "
					+ " TEMPOH_AKHIR_LULUSPHN, TARIKH_TAMAT_KELULUSANDASAR"
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
				h.put("txtNoRujHidro", rs.getString("NO_FAIL_PHN") == null ? ""
						: rs.getString("NO_FAIL_PHN"));
				h.put("txtTarikhSuratHIDRO",
						rs.getDate("TARIKH_SURAT_PHN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_PHN")));
				h.put("txtTarikhTerimaHIDRO",
						rs.getDate("TARIKH_TERIMA_PHN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_PHN")));
				h.put("socKeputusanHIDRO",
						rs.getString("KEPUTUSAN_PHN") == null ? "" : rs
								.getString("KEPUTUSAN_PHN"));
				h.put("txtTarikhLulusHIDRO",
						rs.getDate("TARIKH_LULUSPHN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LULUSPHN")));
				h.put("txtTempohAkhirLulusHIDRO",
						rs.getString("TEMPOH_AKHIR_LULUSPHN") == null ? "" : rs
								.getString("TEMPOH_AKHIR_LULUSPHN"));
				h.put("tarikhTamatKelulusanDasar", rs
						.getDate("TARIKH_TAMAT_KELULUSANDASAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
				beanMaklumatHIDRO.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatHidro(String idPermohonan, String txtNoRujHidro,
			String txtTarikhSuratLulusHIDRO, String txtTarikhTerimaHIDRO,
			String socKeputusanHIDRO, String txtTarikhLulusHIDRO,
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

			String TSL = "to_date('" + txtTarikhSuratLulusHIDRO
					+ "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTerimaHIDRO + "','dd/MM/yyyy')";
			String TL = "to_date('" + txtTarikhLulusHIDRO + "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_AKTIF", "Y");

			r.add("NO_FAIL_PHN", txtNoRujHidro);
			r.add("TARIKH_SURAT_PHN", r.unquote(TSL));
			r.add("TARIKH_TERIMA_PHN", r.unquote(TT));
			r.add("KEPUTUSAN_PHN", socKeputusanHIDRO);
			r.add("TARIKH_LULUSPHN", r.unquote(TL));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610236", "4", null, session, "UPD",
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

	public void updateMaklumatJAS(String idPermohonan, String txtNoRujJAS,
			String txtTarikhSuratLulusJAS, String txtTarikhTerimaJAS,
			String socKeputusanJAS, String txtTempohLulusJAS,
			String txtTarikhLulusJAS, String socJAS, String socTempoh,
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

			String TSL = "to_date('" + txtTarikhSuratLulusJAS
					+ "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTerimaJAS + "','dd/MM/yyyy')";
			String TL = "to_date('" + txtTarikhLulusJAS + "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_AKTIF", "Y");

			r.add("NO_FAIL_JAS", txtNoRujJAS);
			r.add("TARIKH_SURAT_JAS", r.unquote(TSL));
			r.add("TARIKH_TERIMA_JAS", r.unquote(TT));
			r.add("KEPUTUSAN_JAS", socKeputusanJAS);
			r.add("TEMPOH_KELULUSAN_JAS", txtTempohLulusJAS);
			r.add("TARIKH_LULUS_JAS", r.unquote(TL));

			r.add("ID_CAWANGANJAS", socJAS);
			r.add("ID_TEMPOHJAS", socTempoh);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610236", "4", null, session, "UPD",
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

	public void updateStatus(String idFail, String idPermohonan,
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
			r.add("ID_STATUS", "1610237");

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610237")); //CETAKAN SURAT MOHON BAYARAN SEBELUM PENGELUARAN LESEN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610237", "4", null, session, "UPD",
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

	public Vector getBeanMaklumatHIDRO() {
		return beanMaklumatHIDRO;
	}

	public Vector getBeanMaklumatJAS() {
		return beanMaklumatJAS;
	}
}