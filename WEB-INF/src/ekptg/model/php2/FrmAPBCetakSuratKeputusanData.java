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

public class FrmAPBCetakSuratKeputusanData {

	private Vector beanKeputusanMenteri = null;
	private Vector beanKelulusanDasar = null;
	private Vector beanDuitRM = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKeputusanMenteri(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKeputusanMenteri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_KEPUTUSAN , FLAG_KEPUTUSAN"
					+ " FROM TBLPHPKERTASKERJAAPB "
					+ " WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("keputusanMenteri",
						rs.getString("FLAG_KEPUTUSAN") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN"));
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_KEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				beanKeputusanMenteri.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKelulusanDasar(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKelulusanDasar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPBYRNSYRTKLLSNLESENAPB.ID_BYRNSYRTKLLSNLESENAPB, TBLPHPBYRNSYRTKLLSNLESENAPB.LUAS_KAWASAN, TBLPHPBYRNSYRTKLLSNLESENAPB.ID_LUAS,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_FEELESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_FEELESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.FEELESEN_BAG_SETIAP, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.WANG_CAGARAN_PEMATUHAN, TBLPHPBYRNSYRTKLLSNLESENAPB.WANG_AMANAH_NELAYAN, TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_ROYALTI_PASIR,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.SYARAT_LULUS_LAMPIRAN, TBLPHPBYRNSYRTKLLSNLESENAPB.TEMPOH_LESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.LUAS_DLM_NAUTIKA,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_SURAT, TBLPHPBYRNSYRTKLLSNLESENAPB.TEMPOH_KELULUSAN_DASAR, TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_TAMAT_KELULUSANDASAR,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_MULA_LESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_TAMAT_LESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.NO_LESEN,"
					+ " TBLPHPPMOHONNJDUALPERTAMA.LUAS_DIPOHON, TBLRUJLUAS.KETERANGAN AS JENIS_LUAS"
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB, TBLPHPPMOHONNJDUALPERTAMA, TBLRUJLUAS "
					+ " WHERE TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = TBLPHPPMOHONNJDUALPERTAMA.ID_PERMOHONAN AND TBLPHPPMOHONNJDUALPERTAMA.ID_UNITLUAS = TBLRUJLUAS.ID_LUAS(+)"
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.FLAG_AKTIF = 'Y' AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idByrnsyrtkllsnlesenapb", rs
						.getString("ID_BYRNSYRTKLLSNLESENAPB") == null ? ""
						: rs.getString("ID_BYRNSYRTKLLSNLESENAPB"));
				h.put("luasDipohon", (rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON")) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));
				h.put("txtLuasKawasan",
						rs.getString("LUAS_KAWASAN") == null ? "" : rs
								.getString("LUAS_KAWASAN"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "99999" : rs
								.getString("ID_LUAS"));
				h.put("txtFeeLesen",
						rs.getString("KADAR_FEELESEN") == null ? "1000.00 "
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_FEELESEN"))));
				h.put("txtJumlahFeeLesen",
						rs.getString("JUMLAH_FEELESEN") == null ? "0.00" : Util
								.formatDecimal(Double.parseDouble(rs
										.getString("JUMLAH_FEELESEN"))));
				h.put("txtKmPersegi",
						rs.getString("FEELESEN_BAG_SETIAP") == null ? "2.59"
								: Double.parseDouble(rs
										.getString("FEELESEN_BAG_SETIAP")));
				h.put("txtWangCagaran",
						rs.getString("WANG_CAGARAN_PEMATUHAN") == null ? "100000.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("WANG_CAGARAN_PEMATUHAN"))));
				h.put("txtWangAmanah",
						rs.getString("WANG_AMANAH_NELAYAN") == null ? "100000.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("WANG_AMANAH_NELAYAN"))));
				h.put("txtKadarRoyaltiPasir",
						rs.getString("KADAR_ROYALTI_PASIR") == null ? "0.70"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_ROYALTI_PASIR"))));
				h.put("txtSyaratKelulusan",
						rs.getString("SYARAT_LULUS_LAMPIRAN") == null ? "" : rs
								.getString("SYARAT_LULUS_LAMPIRAN"));
				h.put("txtTempohLesen",
						rs.getString("TEMPOH_LESEN") == null ? "" : rs
								.getString("TEMPOH_LESEN"));
				h.put("txtLuasNautika",
						rs.getString("LUAS_DLM_NAUTIKA") == null ? "0" : rs
								.getString("LUAS_DLM_NAUTIKA"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tempohKelulusanDasar",
						rs.getString("TEMPOH_KELULUSAN_DASAR") == null ? "" : rs
								.getString("TEMPOH_KELULUSAN_DASAR"));
				h.put("tarikhTamatKelulusanDasar", rs
						.getDate("TARIKH_TAMAT_KELULUSANDASAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
				h.put("tarikhMulaLesen",
						rs.getDate("TARIKH_MULA_LESEN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_LESEN")));
				h.put("tarikhTamatLesen",
						rs.getDate("TARIKH_TAMAT_LESEN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TAMAT_LESEN")));
				h.put("noLesen",
						rs.getString("NO_LESEN") == null ? "" : rs
								.getString("NO_LESEN"));
				beanKelulusanDasar.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatBayaranSblm(String idPermohonan,
			String txtLuasKawasan, String idLuas, String txtFeeLesen,
			String txtJumlahFeeLesen, String txtKmPersegi,
			String txtWangCagaran, String txtWangAmanah,
			String txtKadarRoyaltiPasir, String txtLuasNautika, String txtTarikhSurat,
			String tempohKelulusanDasar, String txtTarikhTamatKelulusanDasar, HttpSession session)
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

			String TS = "to_date('" + txtTarikhSurat + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamatKelulusanDasar
					+ "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_AKTIF", "Y");
			r.add("LUAS_KAWASAN", txtLuasKawasan);
			r.add("ID_LUAS", idLuas);
			r.add("KADAR_FEELESEN", txtFeeLesen);
			r.add("JUMLAH_FEELESEN", txtJumlahFeeLesen);
			r.add("FEELESEN_BAG_SETIAP", txtKmPersegi);
			r.add("WANG_CAGARAN_PEMATUHAN", txtWangCagaran);
			r.add("WANG_AMANAH_NELAYAN", txtWangAmanah);
			r.add("KADAR_ROYALTI_PASIR", txtKadarRoyaltiPasir);
			r.add("LUAS_DLM_NAUTIKA", txtLuasNautika);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TEMPOH_KELULUSAN_DASAR", tempohKelulusanDasar);
			r.add("TARIKH_TAMAT_KELULUSANDASAR", r.unquote(TT));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1615198", "4", null, session, "UPD",
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
	
	public void daftarPerlanjutanTempoh(String idPermohonan,
			String txtLuasKawasan, String idLuas, String txtFeeLesen,
			String txtJumlahFeeLesen, String txtKmPersegi,
			String txtWangCagaran, String txtWangAmanah,
			String txtKadarRoyaltiPasir, String txtLuasNautika, String txtTarikhSurat,
			String tempohKelulusanDasar, String txtTarikhTamatKelulusanDasar, HttpSession session)
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
			
			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "T");
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);
			
			String TS = "to_date('" + txtTarikhSurat + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamatKelulusanDasar
					+ "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPBYRNSYRTKLLSN_SEQ");
			r.add("ID_BYRNSYRTKLLSNLESENAPB", id);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "Y");
			r.add("LUAS_KAWASAN", txtLuasKawasan);
			r.add("ID_LUAS", idLuas);
			r.add("KADAR_FEELESEN", txtFeeLesen);
			r.add("JUMLAH_FEELESEN", txtJumlahFeeLesen);
			r.add("FEELESEN_BAG_SETIAP", txtKmPersegi);
			r.add("WANG_CAGARAN_PEMATUHAN", txtWangCagaran);
			r.add("WANG_AMANAH_NELAYAN", txtWangAmanah);
			r.add("KADAR_ROYALTI_PASIR", txtKadarRoyaltiPasir);
			r.add("LUAS_DLM_NAUTIKA", txtLuasNautika);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TEMPOH_KELULUSAN_DASAR", tempohKelulusanDasar);
			r.add("TARIKH_TAMAT_KELULUSANDASAR", r.unquote(TT));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1615198", "4", null, session, "INS",
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
	}
	
	public void daftarKutipanDataPerlanjutanTempoh(String idPermohonan,
			String txtLuasKawasan, String idLuas, String txtFeeLesen,
			String txtJumlahFeeLesen, String txtKmPersegi,
			String txtWangCagaran, String txtWangAmanah,
			String txtKadarRoyaltiPasir, String txtLuasNautika, String txtTarikhSurat,
			String tempohKelulusanDasar, String txtTarikhTamatKelulusanDasar, HttpSession session)
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
			
			String TS = "to_date('" + txtTarikhSurat + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamatKelulusanDasar
					+ "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPBYRNSYRTKLLSN_SEQ");
			r.add("ID_BYRNSYRTKLLSNLESENAPB", id);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "Y");
			r.add("LUAS_KAWASAN", txtLuasKawasan);
			r.add("ID_LUAS", idLuas);
			r.add("KADAR_FEELESEN", txtFeeLesen);
			r.add("JUMLAH_FEELESEN", txtJumlahFeeLesen);
			r.add("FEELESEN_BAG_SETIAP", txtKmPersegi);
			r.add("WANG_CAGARAN_PEMATUHAN", txtWangCagaran);
			r.add("WANG_AMANAH_NELAYAN", txtWangAmanah);
			r.add("KADAR_ROYALTI_PASIR", txtKadarRoyaltiPasir);
			r.add("LUAS_DLM_NAUTIKA", txtLuasNautika);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TEMPOH_KELULUSAN_DASAR", tempohKelulusanDasar);
			r.add("TARIKH_TAMAT_KELULUSANDASAR", r.unquote(TT));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);
			
			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "T");
					
			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);
						
			sql = "SELECT ID_BYRNSYRTKLLSNLESENAPB FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY TARIKH_TAMAT_KELULUSANDASAR DESC";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// TBLPHPBYRNSYRTKLLSNLESENAPB
				r = new SQLRenderer();
				r.update("ID_BYRNSYRTKLLSNLESENAPB", rs.getString("ID_BYRNSYRTKLLSNLESENAPB"));
				r.add("FLAG_AKTIF", "Y");				
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			
			AuditTrail.logActivity("1615198", "4", null, session, "INS",
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
			r.add("ID_STATUS", "1610236"); //MAKLUMAT KELULUSAN EIA DAN HIDROGRAFI

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610236")); //MAKLUMAT KELULUSAN EIA DAN HIDROGRAFI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610236", "4", null, session, "UPD",
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
	
	public void setDuitRM(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanDuitRM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KADAR_FEELESEN, WANG_CAGARAN_PEMATUHAN,WANG_AMANAH_NELAYAN,KADAR_ROYALTI_PASIR"
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB "
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("txtFeeLesen",
						rs.getString("KADAR_FEELESEN") == null ? " " : rs
								.getString("KADAR_FEELESEN"));
				h.put("txtWangCagaran",
						rs.getString("WANG_CAGARAN_PEMATUHAN") == null ? ""
								: rs.getString("WANG_CAGARAN_PEMATUHAN"));
				h.put("txtWangAmanah",
						rs.getString("WANG_AMANAH_NELAYAN") == null ? "" : rs
								.getString("WANG_AMANAH_NELAYAN"));
				h.put("txtKadarRoyaltiPasir",
						rs.getString("KADAR_ROYALTI_PASIR") == null ? "" : rs
								.getString("KADAR_ROYALTI_PASIR"));
				beanDuitRM.addElement(h);
				bil++;
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
	
	public Vector getSenaraiPerlanjutanTempoh(String idPermohonan)
			throws Exception {
		Vector senaraiPerlanjutanTempoh = null;
		Db db = null;
		String sql = "";

		try {
			senaraiPerlanjutanTempoh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BYRNSYRTKLLSNLESENAPB, TARIKH_SURAT, TEMPOH_KELULUSAN_DASAR, TARIKH_TAMAT_KELULUSANDASAR"
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB "
					+ " WHERE FLAG_AKTIF = 'T' AND ID_PERMOHONAN = '" + idPermohonan + "'"
					+ " ORDER BY TARIKH_SURAT ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("id",
						rs.getString("ID_BYRNSYRTKLLSNLESENAPB") == null ? "" : rs
								.getString("ID_BYRNSYRTKLLSNLESENAPB"));
				h.put("tarikhSurat",
						rs.getDate("TARIKH_SURAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT")));
				h.put("tempoh",
						rs.getString("TEMPOH_KELULUSAN_DASAR") == null ? "" : rs
								.getString("TEMPOH_KELULUSAN_DASAR"));
				h.put("tarikhTamatKelulusanDasar",
						rs.getDate("TARIKH_TAMAT_KELULUSANDASAR") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
				senaraiPerlanjutanTempoh.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiPerlanjutanTempoh;
	}

	public Vector getBeanKelulusanDasar() {
		return beanKelulusanDasar;
	}

	public Vector getBeanKeputusanMenteri() {
		return beanKeputusanMenteri;
	}

	public Vector getBeanDuitRM() {
		return beanDuitRM;
	}
}
