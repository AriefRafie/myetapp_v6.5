/**
 * 
 */
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.view.php2.online.FrmPNWTawaranKJPView;

/**
 * 
 *
 */
public class FrmPNWTawaranKJPData {

	private Vector senaraiFail = null;
	private Vector listAgensi = null;
	private Vector beanMaklumatAgensi = null;
	private static final Log log = LogFactory.getLog(FrmPNWTawaranData.class);
	static Logger myLog = Logger.getLogger(FrmPNWTawaranKJPView.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	protected Db db;
	
	public void carianFail(String noFail, String tajukFail, String namaPemohon,
			String tarikhTerima, String idNegeri, String idDaerah,
			String idMukim, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot, String noPegangan,
			String idStatus, String idKementerian, String idAgensi,
			String checkTanah) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, A.TAJUK_FAIL, B.TARIKH_TERIMA, A.TARIKH_DAFTAR_FAIL, C.NAMA, D.KETERANGAN, B.ID_STATUS, H.USER_NAME, RUJNEGERI.NAMA_NEGERI "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H, TBLRUJNEGERI RUJNEGERI "
					+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '32' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
					+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
					+ " AND F.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) "
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.NO_FAIL IS NOT NULL"
					+ " AND A.ID_MASUK = H.USER_ID(+)"
					+ " AND D.ID_STATUS = '1610210' "; //TAWARAN

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// tajukFail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhTerima))
									.toUpperCase() + "'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND F.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND F.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND F.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND F.ID_KEMENTERIAN = '"
							+ idKementerian.trim() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND F.ID_AGENSI = '" + idAgensi.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND F.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// jenis Tanah - Asna
			if (checkTanah != null) {
				if (!checkTanah.trim().equals("") && checkTanah.equals("1")) {
					sql = sql + " AND NO_HAKMILIK IS NOT NULL";
				} else if (!checkTanah.trim().equals("")
						&& checkTanah.equals("2")) {
					sql = sql + " AND NO_WARTA IS NOT NULL";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND F.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// noPegangan
			if (noPegangan != null) {
				if (!noPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(F.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ noPegangan.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")
						&& !idStatus.trim().equals("99999")) {
					sql = sql + " AND B.ID_STATUS = '" + idStatus.trim() + "'";
				}
			}

			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("senaraiFail tawaran====="+sql);
			

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhBukaFail",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("userLogin",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h;
		Vector listDetailKJP = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("listDetailKJP:: "+sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("idNegeri", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				listDetailKJP.addElement(h);

				return listDetailKJP;
			} else {
				return listDetailKJP;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}


	public void setSenaraiAgensi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENAWARANKJP, B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.TARIKH_TERIMA, A.NO_RUJUKAN_KJP FROM TBLPHPPENAWARANKJP A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C"
					+ " WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_AGENSI = C.ID_AGENSI AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("baca listAgensi=="+sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenawaranKJP",
						rs.getString("ID_PENAWARANKJP") == null ? "" : rs
								.getString("ID_PENAWARANKJP"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhTerima",
						rs.getString("TARIKH_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs
						.getString("NO_RUJUKAN_KJP").toUpperCase());
				listAgensi.addElement(h);
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

	public void setMaklumatAgensi(String idPenawaranKJP) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PENAWARANKJP, ID_KEMENTERIAN, ID_AGENSI, TARIKH_TERIMA, NO_RUJUKAN_KJP, TUJUAN_KEGUNAAN FROM TBLPHPPENAWARANKJP"
					+ " WHERE ID_PENAWARANKJP = '" + idPenawaranKJP + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("baca setMaklumatAgensi=="+sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenawaranKJP",
						rs.getString("ID_PENAWARANKJP") == null ? "" : rs
								.getString("ID_PENAWARANKJP"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs
						.getString("ID_AGENSI").toUpperCase());
				h.put("tarikhTerima",
						rs.getString("TARIKH_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs
						.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tujuanKegunaan",
						rs.getString("TUJUAN_KEGUNAAN") == null ? "" : rs
								.getString("TUJUAN_KEGUNAAN"));
				beanMaklumatAgensi.addElement(h);
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

	public String simpanAgensi(String idPermohonan, String txtNoRujukanKJP,
			String txtTarikhTerima, String idKementerian, String idAgensi,
			String txtTujuanKegunaan, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPenawaranKJPString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENAWARANKJP
			long idPenawaranKJP = DB.getNextID("TBLPHPPENAWARANKJP_SEQ");
			idPenawaranKJPString = String.valueOf(idPenawaranKJP);
			r.add("ID_PENAWARANKJP", idPenawaranKJP);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN_KJP", txtNoRujukanKJP);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("TUJUAN_KEGUNAAN", txtTujuanKegunaan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENAWARANKJP");
			stmt.executeUpdate(sql);
			myLog.info("simpanAgensi===="+sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
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
		
		return idPenawaranKJPString;
	}

	public void simpanKemaskiniAgensi(String idPenawaranKJP,
			String txtNoRujukanKJP, String txtTarikhTerima, String idKementerian,
			String idAgensi, String txtTujuanKegunaan, HttpSession session)
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

			// TBLPHPPENAWARANKJP
			r.update("ID_PENAWARANKJP", idPenawaranKJP);
			r.add("NO_RUJUKAN_KJP", txtNoRujukanKJP);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("TUJUAN_KEGUNAAN", txtTujuanKegunaan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENAWARANKJP");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + idPenawaranKJP
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

	public void hapusAgensi(String idPenawaranKJP,String idDokumen, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			SQLRenderer r1 = new SQLRenderer();
			
			//TBLPHPDOKUMEN
			r1.add("ID_DOKUMEN", idDokumen);
			sql = r1.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			// TBLPHPPENAWARANKJP
			r.add("ID_PENAWARANKJP", idPenawaranKJP);
			sql = r.getSQLDelete("TBLPHPPENAWARANKJP");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "DEL",
					"FAIL PENAWARAN [" + idPenawaranKJP
							+ "] DIHAPUSKAN");

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610201")); // MESYUARAT
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
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
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
			String tarikhBatal, String txtSebab, HttpSession session)
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610212")); // BATAL
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListAgensi() {
		return listAgensi;
	}

	public void setListAgensi(Vector listAgensi) {
		this.listAgensi = listAgensi;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public void setBeanMaklumatAgensi(Vector beanMaklumatAgensi) {
		this.beanMaklumatAgensi = beanMaklumatAgensi;
	}
	public Vector getSenaraiFail() {
		return senaraiFail;
	}

}
