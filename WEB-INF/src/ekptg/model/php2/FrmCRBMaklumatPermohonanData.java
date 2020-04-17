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

public class FrmCRBMaklumatPermohonanData {

	private Vector beanMaklumatAduan = null;
	private Vector senaraiPencerobohan = null;
	private Vector beanMaklumatHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatAduan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAduan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANKUATKUASA, C.LOKASI, C.KETERANGAN_ADUAN, C.LAIN_JNS_CEROBOH, A.TARIKH_TERIMA, A.TARIKH_SURAT,D.TAJUK_FAIL, C.AKTIVITI"
					+ " FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B, TBLPHPPERMOHONANKUATKUASA C, TBLPFDFAIL D "
					+ " WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = D.ID_FAIL "
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa", rs.getString("ID_PHPPERMOHONANKUATKUASA") == null ? "" : rs.getString("ID_PHPPERMOHONANKUATKUASA"));
				h.put("lainCeroboh", rs.getString("LAIN_JNS_CEROBOH") == null ? "" : rs.getString("LAIN_JNS_CEROBOH"));
				h.put("lokasi", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI"));
				h.put("keterangan", rs.getString("KETERANGAN_ADUAN") == null ? "" : rs.getString("KETERANGAN_ADUAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("aktiviti", rs.getString("AKTIVITI") == null ? "" : rs.getString("AKTIVITI"));
				beanMaklumatAduan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiPencerobohan(String idPermohonan) throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			senaraiPencerobohan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_JENISPENCEROBOHAN,KOD_JENISPENCEROBOHAN,KETERANGAN,"
					+ " CASE WHEN ID_JENISPENCEROBOHAN IN (SELECT ID_JENISPENCEROBOHAN"
					+ " FROM TBLPHPITEMPENCEROBOHAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan
					+ "') THEN 'Y' END AS FLAG"
					+ " FROM TBLPHPRUJJENISPENCEROBOHAN ORDER BY ID_JENISPENCEROBOHAN ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idJenisPencerobohan",
						rs.getString("ID_JENISPENCEROBOHAN"));
				h.put("kodJenisPencerobohan",
						rs.getString("KOD_JENISPENCEROBOHAN"));
				h.put("keterangan", rs.getString("KETERANGAN"));
				h.put("flag",
						rs.getString("FLAG") == null ? "" : rs
								.getString("FLAG"));
				senaraiPencerobohan.addElement(h);
				bil++;
			}

			return senaraiPencerobohan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanMaklumatAduan(String idPermohonan, String lainCeroboh,
			String lokasi, String keteranganAduan,
			String[] idJenisPencerobohan, String tarikhTerima,
			String tarikhSurat, String txtPerkara, String aktiviti, String idFail, 
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
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANKUATKUASA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("LAIN_JNS_CEROBOH", lainCeroboh);
			r.add("LOKASI", lokasi);
			r.add("KETERANGAN_ADUAN", keteranganAduan);
			r.add("AKTIVITI", aktiviti);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPERMOHONANKUATKUASA");
			stmt.executeUpdate(sql);

			if (idJenisPencerobohan != null) {

				// TBLPHPITEMPENCEROBOHAN (delete)
				r = new SQLRenderer();
				r.add("ID_PERMOHONAN", idPermohonan);
				sql = r.getSQLDelete("TBLPHPITEMPENCEROBOHAN");
				stmt.executeUpdate(sql);

				for (int i = 0; i < idJenisPencerobohan.length; i++) {
					// TBLPHPITEMPENCEROBOHAN (add)
					r = new SQLRenderer();
					long idItemPencerobohan = DB
							.getNextID("TBLPHPITEMPENCEROBOHAN_SEQ");
					r.add("ID_ITEMPENCEROBOHAN", idItemPencerobohan);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("ID_JENISPENCEROBOHAN", idJenisPencerobohan[i]);
					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					sql = r.getSQLInsert("TBLPHPITEMPENCEROBOHAN");
					stmt.executeUpdate(sql);
				}
			}

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
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
			r.add("ID_STATUS", "1610200");

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("56", "1610200")); // LAWATAN
																				// TAPAK
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
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


	public Vector getBeanMaklumatAduan() {
		return beanMaklumatAduan;
	}

	public Vector getSenaraiPencerobohan() {
		return senaraiPencerobohan;
	}

	public void setSenaraiPencerobohan(Vector senaraiPencerobohan) {
		this.senaraiPencerobohan = senaraiPencerobohan;
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

	public void setBeanMaklumatAduan(Vector beanMaklumatAduan) {
		this.beanMaklumatAduan = beanMaklumatAduan;
	}
}
