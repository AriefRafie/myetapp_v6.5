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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmAPBPenyediaanLesenDanMemoKepadaMenteriData {

	private Vector beanMaklumatPenyediaanLesenDanMemo = null;
	private static final Log log = LogFactory.getLog(FrmAPBPenyediaanLesenDanMemoKepadaMenteriData.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPenyediaanLesenDanMemo(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenyediaanLesenDanMemo = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_BYRNSYRTKLLSNLESENAPB, TBLPHPBYRNSYRTKLLSNLESENAPB.ID_LUAS, TBLPHPBYRNSYRTKLLSNLESENAPB.LUAS_KAWASAN, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_FEELESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.FEELESEN_BAG_SETIAP, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_ROYALTI_PASIR, TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_ROYALTI_SELURUH, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_DAHULU_ROYALTI, TBLPHPBYRNSYRTKLLSNLESENAPB.ISIPADU,"
					+ " TBLPHPPMOHONNJDUALPERTAMA.TEMPOH_DIPOHON, TBLPHPPMOHONNJDUALPERTAMA.FLAG_LUAR_PERAIRANNEGERI, "
					+ " TBLPHPPMOHONNJDUALPERTAMA.ID_NEGERI_PERAIRAN,TBLPHPPMOHONNJDUALPERTAMA.LOKASI_PERMOHONAN, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_FEELESEN,INITCAP(TBLRUJLUAS.KETERANGAN) AS KETERANGAN, "
					+ " INITCAP(TBLRUJNEGERI.NAMA_NEGERI) AS NAMA_NEGERI,TBLPHPPMOHONNJDUALPERTAMA.ID_TEMPOH, TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_MULA_LESEN,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_TAMAT_LESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.NO_LESEN, TBLPHPPMOHONNJDUALPERTAMA.TEMPOH_DILULUSKAN "
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB,TBLPHPPMOHONNJDUALPERTAMA,TBLRUJLUAS, TBLRUJNEGERI "
					+ " WHERE TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = TBLPHPPMOHONNJDUALPERTAMA.ID_PERMOHONAN "
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_LUAS = TBLRUJLUAS.ID_LUAS(+) "
					+ " AND TBLPHPPMOHONNJDUALPERTAMA.ID_NEGERI_PERAIRAN = TBLRUJNEGERI.ID_NEGERI(+) "
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.FLAG_AKTIF = 'Y'"
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idByrnsyrtkllsnlesenapb", rs
						.getString("ID_BYRNSYRTKLLSNLESENAPB") == null ? ""
						: rs.getString("ID_BYRNSYRTKLLSNLESENAPB"));
				h.put("txtNoLesen",
						rs.getString("NO_LESEN") == null ? "" : rs
								.getString("NO_LESEN"));
				h.put("txtTarikhMula",
						rs.getDate("TARIKH_MULA_LESEN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_LESEN")));
				h.put("tempohDipohon",
						rs.getString("TEMPOH_DIPOHON") == null ? "" : rs
								.getString("TEMPOH_DIPOHON"));
				h.put("txtTarikhTamat",
						rs.getDate("TARIKH_TAMAT_LESEN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TAMAT_LESEN")));
				h.put("idTempoh",
						rs.getString("ID_TEMPOH") == null ? "" : rs
								.getString("ID_TEMPOH"));
				h.put("kadarFeeLesen",
						rs.getString("KADAR_FEELESEN") == null ? "0.00" : Util
								.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_FEELESEN"))));
				h.put("jumlahFeeLesen",
						rs.getString("JUMLAH_FEELESEN") == null ? "0.00" : Util
								.formatDecimal(Double.parseDouble(rs
										.getString("JUMLAH_FEELESEN"))));
				h.put("kmPersegi",
						rs.getString("FEELESEN_BAG_SETIAP") == null ? "0.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("FEELESEN_BAG_SETIAP"))));
				h.put("isipadu",
						rs.getString("ISIPADU") == null ? "0.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("ISIPADU"))));
				h.put("kadarRoyaltiPasir",
						rs.getString("KADAR_ROYALTI_PASIR") == null ? "0.00"
								: Util.formatDecimal(Double.parseDouble(rs
										.getString("KADAR_ROYALTI_PASIR"))));
				h.put("jumlahRoyaltiSeluruh",
						rs.getString("JUMLAH_ROYALTI_SELURUH") == null ? "0.00"
								: Utils.RemoveSymbol(rs
										.getString("JUMLAH_ROYALTI_SELURUH")));
				h.put("jumDahuluRoyalti",
						rs.getString("JUMLAH_DAHULU_ROYALTI") == null ? "0.00"
								: Utils.RemoveSymbol(rs
										.getString("JUMLAH_DAHULU_ROYALTI")));
				h.put("flagLuar",
						rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
								: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? ""
						: rs.getString("LOKASI_PERMOHONAN"));
				h.put("luas",
						rs.getString("LUAS_KAWASAN") == null ? "" : rs
								.getString("LUAS_KAWASAN"));
				h.put("jenisLuas",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("tempohDiluluskan",
						rs.getString("TEMPOH_DILULUSKAN") == null ? "" : rs
								.getString("TEMPOH_DILULUSKAN"));	//ADD NEW COLUMN -AIN 10/1/2017-
				beanMaklumatPenyediaanLesenDanMemo.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPenyediaanLesenDanMemo() {
		return beanMaklumatPenyediaanLesenDanMemo;
	}

	public void updateMaklumatPenyediaanLesenDanMemo(String idPermohonan, String txtTempohDiluluskan, String idTempoh, HttpSession session) throws Exception {

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
			r.add("ID_KEMASKINI", userId);
			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);
			
			// TBLPHPPMOHONNJDUALPERTAMA
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TEMPOH_DILULUSKAN", txtTempohDiluluskan); //AIN
			r.add("ID_TEMPOH", idTempoh); //AIN
			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610238", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data 2"
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
			r.add("ID_STATUS", "1610239"); //CETAKAN SURAT KELULUSAN LESEN KEPADA PEMOHON

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610239")); //CETAKAN SURAT KELULUSAN LESEN KEPADA PEMOHON
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610239", "4", null, session, "UPD",
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
	
	public boolean checkMaklumatPenyediaanLesenDanMemoKepadaMenteri(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;
		String tempohDiluluskan = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPPMOHONNJDUALPERTAMA.TEMPOH_DILULUSKAN "
					+ "FROM TBLPHPBYRNSYRTKLLSNLESENAPB, TBLPHPPMOHONNJDUALPERTAMA " 
					+ "WHERE TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = TBLPHPPMOHONNJDUALPERTAMA.ID_PERMOHONAN " 
					+ "AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tempohDiluluskan = rs.getString("TEMPOH_DILULUSKAN");
			}

			if (tempohDiluluskan == null) {
				bool = true;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
}