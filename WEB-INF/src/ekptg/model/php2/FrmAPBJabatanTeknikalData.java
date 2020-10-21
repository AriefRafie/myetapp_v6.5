/**
 * 
 */
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

/**
 * modified by hilda
 * 
 */
public class FrmAPBJabatanTeknikalData {

	private Vector listJUPEM = null;
	private Vector listJAS = null;
	private Vector listJMG = null;
	private Vector listJP = null;
	private Vector listJLM = null;
	private Vector listPHM = null;
	private Vector listJPS = null;
	private Vector listPTG = null;
	private Vector listPertindihan = null;
	private Vector beanMaklumatPertindihan = null;
	private Vector beanMaklumatKJT = null;
	private Vector beanMaklumatDokumen = null;
	private Vector beanMaklumatPejabat = null;
	private Vector listNotifikasi = null;
	private Vector beanMaklumatKJP = null;
	private Vector beanMaklumatLampiranKJP = null;
	private Vector beanDocJUPEM = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKJT(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA, A.FLAG_STATUS, A.FLAG_AKTIF, B.NAMA_KEMENTERIAN, C.NAMA_AGENSI,"
					+ " A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJUKAN, A.ULASAN, A.NAMA_PEGAWAI, A.NO_TELEFON, UPPER(D.NAMA_PEJABAT) AS NAMA_PEJABAT"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C, TBLRUJPEJABAT D WHERE A.ID_MENTERI = B.ID_KEMENTERIAN"
					+ " AND A.ID_AGENSI = C.ID_AGENSI(+) AND A.ID_PEJABAT = D.ID_PEJABAT(+) AND ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("agensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs
								.getString("NAMA_AGENSI"));
				h.put("pejabat",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("jangkamasa", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus",
						rs.getString("FLAG_STATUS") == null ? "99999" : rs
								.getString("FLAG_STATUS"));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("namaPengulas", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("noTelPengulas", rs.getString("NO_TELEFON") == null ? ""
						: rs.getString("NO_TELEFON"));
				beanMaklumatKJT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiJUPEM(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJUPEM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JUPEM' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJUPEM.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	public String simpanMaklumatJUPEM(String idPermohonan,
			String idKementerian, String idAgensi, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JUPEM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJUPEM(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JUPEM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void simpanKemaskiniMaklumatKJT(String idUlasanTeknikal,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukanSurat, String txtUlasan, String txtNamaPengulas,
			String txtNoTelPengulas, HttpSession session) throws Exception {

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
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", flagStatus);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukanSurat);
			r.add("ULASAN", txtUlasan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON", txtNoTelPengulas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + idUlasanTeknikal + "] DIKEMASKINI");

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

	public void hapusDokumen(String idUlasanTeknikal, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "DEL",
					"FAIL [" + idUlasanTeknikal + "] DIHAPUSKAN");

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

	public void setMaklumatDokumen(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatDokumen = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatDokumen.addElement(h);
			}
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
			r.add("ID_STATUS", "1610235"); // CETAKAN KERTAS KERJA JAWATANKUASA

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610235")); // CETAKAN
																				// KERTAS
																				// KERJA
																				// JAWATANKUASA
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public String getKementerianByIdKementerian(String idKementerian)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NAMA_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getAgensiByIdAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(NAMA_AGENSI) AS NAMA_AGENSI FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NAMA_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// UNTUK ULASAN PTG
	public String getIdNegeriPerairan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_NEGERI_PERAIRAN FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_NEGERI_PERAIRAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getPejabatByIdPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(NAMA_PEJABAT) AS NAMA_PEJABAT FROM TBLRUJPEJABAT WHERE ID_PEJABAT = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NAMA_PEJABAT").toUpperCase();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdPejabatPTGByNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEJABAT FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT = 1 AND ID_NEGERI = '"
					+ idNegeri + "'";

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

	public String getIdPejabatJASByNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEJABAT FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT = 26 AND ID_NEGERI = '"
					+ idNegeri + "'";

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

	public void hapusMaklumatUlasan(String idUlasanTeknikal, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String idParent = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			sql = "SELECT ID_PARENT FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("ID_PARENT") != null
						&& !"".equals(rs.getString("ID_PARENT"))) {
					// TBLPHPULASANTEKNIKAL
					idParent = rs.getString("ID_PARENT");
					r = new SQLRenderer();
					r.update("ID_ULASANTEKNIKAL", idParent);
					r.add("FLAG_STATUS", "1");
					r.add("FLAG_AKTIF", "Y");

					sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
					stmt.executeUpdate(sql);
				}
			}

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "DEL",
					"FAIL [" + idUlasanTeknikal + "] DIHAPUSKAN");

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

	public String simpanMaklumatJAS(String idPermohonan, String idKementerian,
			String idPejabat, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_NEGERI", getIdNegeriPerairan(idPermohonan));
			r.add("FLAG_KJP", "JAS");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJAS(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idPejabat,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_NEGERI", getIdNegeriPerairan(idPermohonan));
			r.add("FLAG_KJP", "JAS");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiJAS(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJAS = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL,N.NAMA_NEGERI, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_PEJABAT, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJNEGERI N, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+)"
					+ " AND B.ID_NEGERI = N.ID_NEGERI AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JAS' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("pejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs
						.getString("NAMA_PEJABAT").toUpperCase());
				h.put("negeriPejabat", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJAS.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatJMG(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JMG");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJMG(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JMG");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiJMG(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJMG = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JMG' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJMG.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatJP(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JP");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJP(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JP");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JP' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJP.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatJLM(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JLM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJLM(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JLM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiJLM(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJLM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JLM' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJLM.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatPHM(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "PHM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganPHM(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "PHM");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiPHM(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPHM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'PHM' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listPHM.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatJPS(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JPS");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganJPS(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "JPS");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiJPS(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJPS = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JPS' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listJPS.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateUlasanJUPEM(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JUPEM",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JUPEM", noRujukan);
			r.add("ULASAN_JUPEM", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public void updateUlasanJAS(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JAS",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JAS", noRujukan);
			r.add("ULASAN_JAS", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
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

	public void updateUlasanJMG(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JAB_GEOSAINS",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JAB_GEOSAINS", noRujukan);
			r.add("ULASAN_JAB_GEOSAINS", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public void updateUlasanJP(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JAB_PERIKANAN",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JAB_PERIKANAN", noRujukan);
			r.add("ULASAN_JAB_PERIKANAN", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public void updateUlasanJLM(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JAB_LAUT",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JAB_LAUT", noRujukan);
			r.add("ULASAN_JAB_LAUT", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public void updateUlasanPHM(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_PUSAT_HIDROGRAFI",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_PUSAT_HIDROGRAFI", noRujukan);
			r.add("ULASAN_PUSAT_HIDROGRAFI", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
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

	public void updateUlasanJPS(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_JPS",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_JPS", noRujukan);
			r.add("ULASAN_JPS", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] KEMASKINI");

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

	public void updateUlasanPTG(String idPermohonan, String noRujukan,
			String tarikhTerima, String ulasan, HttpSession session)
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_SURAT_PTG",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN_PTG", noRujukan);
			r.add("ULASAN_PTG", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
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

	public void setSenaraiPertindihan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPertindihan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_FAILAPBBERTINDIH");
			r.add("A.BERTINDIH_DENGAN_NOFAIL");
			r.add("A.NAMA_SYARIKAT_TINDIH");
			r.add("A.FLAG_JENIS_PERTINDIHAN");
			r.add("A.ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPFAILAPBBERTINDIH A ",
					"A.ID_FAILAPBBERTINDIH ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPertindihan",
						rs.getString("ID_FAILAPBBERTINDIH") == null ? "" : rs
								.getString("ID_FAILAPBBERTINDIH"));
				h.put("noFailTindih",
						rs.getString("BERTINDIH_DENGAN_NOFAIL") == null ? ""
								: rs.getString("BERTINDIH_DENGAN_NOFAIL"));
				h.put("namaSyarikatTindih",
						rs.getString("NAMA_SYARIKAT_TINDIH") == null ? "" : rs
								.getString("NAMA_SYARIKAT_TINDIH"));
				h.put("flagJenisTindih",
						rs.getString("FLAG_JENIS_PERTINDIHAN") == null ? ""
								: rs.getString("FLAG_JENIS_PERTINDIHAN"));

				listPertindihan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPertindihan(String idPertindihan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPertindihan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.BERTINDIH_DENGAN_NOFAIL");
			r.add("A.NAMA_SYARIKAT_TINDIH");
			r.add("A.FLAG_JENIS_PERTINDIHAN");
			r.add("A.ID_STATUS");
			r.add("A.LAIN_LAIN");
			r.add("A.ID_FAILAPBBERTINDIH", idPertindihan);

			sql = r.getSQLSelect("TBLPHPFAILAPBBERTINDIH A ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("txtNoFailTindih",
						rs.getString("BERTINDIH_DENGAN_NOFAIL") == null ? ""
								: rs.getString("BERTINDIH_DENGAN_NOFAIL"));
				h.put("txtNamaSyarikat",
						rs.getString("NAMA_SYARIKAT_TINDIH") == null ? "" : rs
								.getString("NAMA_SYARIKAT_TINDIH"));
				h.put("socJenisTindih",
						rs.getString("FLAG_JENIS_PERTINDIHAN") == null ? ""
								: rs.getString("FLAG_JENIS_PERTINDIHAN"));
				h.put("socStatusTindih", rs.getString("ID_STATUS") == null ? ""
						: rs.getString("ID_STATUS"));
				h.put("txtLain",
						rs.getString("LAIN_LAIN") == null ? "" : rs
								.getString("LAIN_LAIN"));

				beanMaklumatPertindihan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatPertindihan(String idPermohonan,
			String txtNoFailTindih, String txtNamaSyarikat,
			String socJenisTindih, String socStatusTindih, String txtLain,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPertindihanString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPFAILAPBBERTINDIH
			long idFailTindih = DB.getNextID("TBLPHPFAILAPBBERTINDIH_SEQ");
			idPertindihanString = String.valueOf(idFailTindih);
			r.add("ID_FAILAPBBERTINDIH", idFailTindih);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("BERTINDIH_DENGAN_NOFAIL", txtNoFailTindih);
			r.add("NAMA_SYARIKAT_TINDIH", txtNamaSyarikat);
			r.add("FLAG_JENIS_PERTINDIHAN", socJenisTindih);
			r.add("ID_STATUS", socStatusTindih);
			r.add("LAIN_LAIN", txtLain);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPFAILAPBBERTINDIH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idPertindihanString;
	}

	public void simpanKemaskiniMaklumatPertindihan(String idPertindihan,
			String txtNoFailTindih, String txtNamaSyarikat,
			String socJenisTindih, String socStatusTindih, String txtLain,
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

			// TBLPHPFAILAPBBERTINDIH
			r.update("ID_FAILAPBBERTINDIH", idPertindihan);
			r.add("BERTINDIH_DENGAN_NOFAIL", txtNoFailTindih);
			r.add("NAMA_SYARIKAT_TINDIH", txtNamaSyarikat);
			r.add("FLAG_JENIS_PERTINDIHAN", socJenisTindih);
			r.add("ID_STATUS", socStatusTindih);
			r.add("LAIN_LAIN", txtLain);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPFAILAPBBERTINDIH");
			System.out.println(sql);
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + idPertindihan + "] DIKEMASKINI");

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

	public void hapusMaklumatPertindihan(String idPertindihan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPFAILAPBBERTINDIH
			SQLRenderer r = new SQLRenderer();
			r.add("ID_FAILAPBBERTINDIH", idPertindihan);

			sql = r.getSQLDelete("TBLPHPFAILAPBBERTINDIH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "DEL",
					"FAIL [" + idPertindihan + "] DIHAPUSKAN");

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

	public String simpanMaklumatPTG(String idPermohonan, String idKementerian,
			String idPejabat, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_NEGERI", getIdNegeriPerairan(idPermohonan));
			r.add("FLAG_KJP", "PTG");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
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

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganPTG(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idPejabat,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_NEGERI", getIdNegeriPerairan(idPermohonan));
			r.add("FLAG_KJP", "PTG");
			r.add("ID_DOKUMEN", "1");
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiPTG(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPTG = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL,N.NAMA_NEGERI, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_PEJABAT, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJNEGERI N, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+)"
					+ " AND B.ID_NEGERI = N.ID_NEGERI AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'PTG' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

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
				h.put("pejabat", rs.getString("NAMA_PEJABAT") == null ? "" : rs
						.getString("NAMA_PEJABAT").toUpperCase());
				h.put("negeriPejabat", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI").toUpperCase());
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

				String statusHari = "";
				int bilHari = 0;

				// check notifikasi surat dah hantar dan belum terima jawapan
				if ("Y".equals(rs.getString("FLAG_AKTIF"))
						&& "1".equals(rs.getString("FLAG_STATUS"))) {

					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs
							.getDate("TARIKH_JANGKA_TERIMA")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(),
							calCurrent.getTime());

					if (calCurrent.getTime().before(calTamat.getTime())
							&& bilHari <= 7 && bilHari >= 0) {

						statusHari = bilHari + " HARI LAGI";
						h.put("statusHari", statusHari);

					} else if (bilHari < 0) {
						statusHari = "TELAH LUPUT";
						h.put("statusHari", statusHari);
					} else {
						h.put("statusHari", "");
					}
				}
				listPTG.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// 10/6/2020
	// START TAMBAH NOTIFIKASI EMAIL//

	public void sendEmail(String mailTo, String tajuk, String tarikh,
			String masa, String lokasi) throws MessagingException {
		final String username = "roslizakariasip@gmail.com";
		final String password = "m.rosligmail";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		String body = "<table width='75%' border='0' cellspacing='0' cellpadding='5'>"
				+ "<tr><td>Assalamualaikum/ Salam Sejahtera.</td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>PERKARA : <u style='font-weight: bold'>NOTIS PANGGILAN MESYUARAT</u></td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>Dengan hormatnya saya merujuk kepada perkara diatas.</td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>Dimaklumkan bahawa tuan/puan dijemput menghadiri mesyuarat seperti dibawah:-"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>Tajuk Mesyuarat: "
				+ tajuk
				+ "</td></tr>"
				+ "<tr><td>Tarikh: "
				+ tarikh
				+ "</td></tr>"
				+ "<tr><td>Masa  : "
				+ masa
				+ "</td></tr>"
				+ "<tr><td>Lokasi: "
				+ lokasi
				+ "</td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>Harap maklum dan terima kasih.</i></td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "<tr><td>&nbsp;</td></tr>"
				+ "</table>";

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom((Address) new InternetAddress("rozai@yopmail.com"));
		mimeMessage.setRecipients(Message.RecipientType.TO,
				(Address[]) InternetAddress.parse(mailTo));
		mimeMessage.setSubject("NOTIS PANGGILAN MESYUARAT");
		mimeMessage.setContent(body, "text/html");
		Transport.send((Message) mimeMessage);
	}

	// 06042020- END TAMBAH NOTIFIKASI EMAIL//

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

	public void setMaklumatPejabatJKPTG(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABATJKPTG"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
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

	//start JUPEM
	public String simpanRekodEmailJUPEM(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JUPEM");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			// r.add("ID_MESYUARAT", idMesyuarat);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJUPEM(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JUPEM'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JUPEM

	//start JAS
	public String simpanRekodEmailJAS(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JAS");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJAS(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JAS'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JAS
	
	//start JMG
	public String simpanRekodEmailJMG(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JMG");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJMG(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JMG'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JMG
	
	//start JP
	public String simpanRekodEmailJP(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JP");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJP(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JP'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JP
	
	//start JLM
	public String simpanRekodEmailJLM(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JLM");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJLM(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JLM'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JLM
	
	//start PHN
	public String simpanRekodEmailPHM(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "PHN");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiPHM(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'PHN'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end PHN
	
	//start JPS
	public String simpanRekodEmailJPS(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "JPS");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiJPS(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'JPS'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end JPS
	
	//start PTG
	public String simpanRekodEmailPTG(String idUlasanTeknikalLama,String idPermohonan,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String idSuratKe, String idKementerianTanah, String idAgensiTanah,
			String namaPegawai, String jawatan, String emel, HttpSession session)
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

			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikalString);
			r.add("ID_PERMOHONAN", idUlasanTeknikalLama);
			r.add("FLAG_KJP", "PTG");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");
			r.add("ID_MASUK", userId);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("EMEL_PEGAWAI", emel);
			r.add("MAKLUMAT_TAMBAHAN", "NOTIFIKASI EMEL JABATAN TEKNIKAL");
			r.add("TARIKH_HANTAR", r.unquote("SYSDATE"));
			r.add("TARIKH_JANGKA_TERIMA", r.unquote("SYSDATE"));
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeQuery(sql);

			conn.commit();

			AuditTrail.logActivity("1610217", "4", null, session, "INS",
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
		return idUlasanTeknikalString;
	}

	public void setSenaraiNotifikasiPTG(String idUlasanTeknikalLama) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listNotifikasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NAMA_PEGAWAI,A.EMEL_PEGAWAI,A.NAMA_JAWATAN,A.NO_TELEFON,A.TARIKH_HANTAR, A.FLAG_STATUS,"
					+ " B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, E.NAMA_PEJABAT AS PEJABATPTGPTD, A.FLAG_KJP"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG B, TBLRUJPEJABAT E WHERE "
					+ " A.ID_PEJABAT = B.ID_PEJABATJKPTG(+) AND A.ID_PEJABAT = E.ID_PEJABAT(+) "
					+ " AND A.ID_PERMOHONAN = '"
					+ idUlasanTeknikalLama
					+ "' AND A.FLAG_KJP = 'PTG'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("namaPejabatPTGPTD",
						rs.getString("PEJABATPTGPTD") == null ? "" : rs
								.getString("PEJABATPTGPTD").toUpperCase());
				h.put("flagKJP",
						rs.getString("FLAG_KJP") == null ? "" : rs
								.getString("FLAG_KJP"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));

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
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("emel",
						rs.getString("EMEL_PEGAWAI") == null ? "" : rs
								.getString("EMEL_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				listNotifikasi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//end PTG

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
	
	public void sendEmailNotifikasi(String idPermohonan,String emel) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String emelUser = "";
		String noFail = "";
		String tarikhAkhir = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT D.NO_FAIL, A.TARIKH_JANGKA_TERIMA "
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ " WHERE A.ID_MENTERI = B.ID_KEMENTERIAN AND A.ID_PERMOHONAN = C.ID_PERMOHONAN "
				+ " AND C.ID_FAIL = D.ID_FAIL"
				+ " AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				//emelUser = rsEmel.getString("EMEL");
				tarikhAkhir = sdf.format(rsEmel.getDate("TARIKH_JANGKA_TERIMA"));
			}	
			
			email.RECIEPIENT = emel;
			email.SUBJECT = "PERMOHONAN ULASAN JABATAN TEKNIKAL BAGI NO. FAIL " + noFail;
			email.MESSAGE = "Mohon pihak tuan memberikan ulasan dan keputusan bagi permohonan tersebut<br><br>"
							 + "Kerjasama daripada pihak tuan untuk mengemukakan keputusan tersebut kepada Jabatan ini "
							 + "sebelum " + tarikhAkhir + " amatlah dihargai."
							 + " <br><br>Sekian, terima kasih.<br><br><br>"			
							 + " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void sendEmailMaklumanJT(String emelUnitAPB,String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String emelUser = "";
		String noFail = "";
		String tarikhAkhir = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT D.NO_FAIL, A.TARIKH_JANGKA_TERIMA "
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ " WHERE A.ID_MENTERI = B.ID_KEMENTERIAN AND A.ID_PERMOHONAN = C.ID_PERMOHONAN "
				+ " AND C.ID_FAIL = D.ID_FAIL" // AND B.ID_KEMENTERIAN = '"+idKementerian+"' "
				+ " AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				//emelUser = rsEmel.getString("EMEL");
				//tarikhAkhir = sdf.format(rsEmel.getDate("TARIKH_JANGKA_TERIMA"));
			}	
			
			//email.RECIEPIENT = emelUser;
			email.RECIEPIENT = emelUnitAPB;
			email.SUBJECT = "PEMAKLUMAN:ULASAN JABATAN TEKNIKAL BAGI NO. FAIL " + noFail + "TELAH DIKEMASKINI";
			email.MESSAGE = "Dimaklumkan bahawa ulasan jabatan teknikal telah dikemaskini dalam sistem<br><br>"
							 + " <br><br>Sekian, terima kasih.<br><br><br>"			
							 + " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKJP(String idUlasanTeknikal, String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.ID_ULASANTEKNIKAL, A.ID_PERMOHONAN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA, "
				+ "A.FLAG_STATUS, A.FLAG_AKTIF, A.ID_MENTERI, A.ID_AGENSI, A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJUKAN, A.ULASAN, "
				+ "A.FLAG_KEPUTUSAN, A.NAMA_PEGAWAI, A.NO_TELEFON "
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C WHERE A.ID_MENTERI = B.ID_KEMENTERIAN"
				+ " AND A.ID_AGENSI = C.ID_AGENSI AND A.ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'"
				+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("agensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs
								.getString("NAMA_AGENSI"));
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idKementerianKJP",
						rs.getString("ID_MENTERI") == null ? "" : rs
								.getString("ID_MENTERI"));
				h.put("idAgensiKJP", rs.getString("ID_AGENSI") == null ? ""
						: rs.getString("ID_AGENSI"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("jangkamasa", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus",
						rs.getString("FLAG_STATUS") == null ? "99999" : rs
								.getString("FLAG_STATUS"));
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("flagKeputusan",
						rs.getString("FLAG_KEPUTUSAN") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN"));
				//untuk dapatkan nama pegawai yang memberi ulasan
				h.put("namaPengulas", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("noTelPengulas", rs.getString("NO_TELEFON") == null ? ""
						: rs.getString("NO_TELEFON"));
				beanMaklumatKJP.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setLampiranKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			beanMaklumatLampiranKJP = new Vector();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, CONTENT, ID_PERMOHONAN, FLAG_DOKUMEN, NAMA_FAIL FROM TBLPHPDOKUMEN "
					+ " WHERE FLAG_DOKUMEN = 'L' AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL "
					+ " AND ID_PENAWARANKJP IS NULL AND ID_PERMOHONAN = '"
					+ idPermohonan + "' ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("content",
						rs.getString("CONTENT") == null ? "" : rs
								.getString("CONTENT"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("flagDokumen", rs.getString("FLAG_DOKUMEN") == null ? ""
						: rs.getString("FLAG_DOKUMEN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				beanMaklumatLampiranKJP.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector findDocJUPEM(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			beanDocJUPEM = new Vector();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_DOKUMEN, ID_ULASANTEKNIKAL, FLAG_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL, CONTENT "
				+ "FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"+ idUlasanTeknikal +"'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("idUlasanTeknikal", rs.getString("ID_ULASANTEKNIKAL") == null ? ""
						: rs.getString("ID_ULASANTEKNIKAL"));
				h.put("flagDokumen", rs.getString("FLAG_DOKUMEN") == null ? ""
						: rs.getString("FLAG_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("content",
						rs.getString("CONTENT") == null ? "" : rs
								.getString("CONTENT"));
				beanDocJUPEM.addElement(h);	
			} 
		} finally {
			if (db != null)
				db.close();
		}
		
		return beanDocJUPEM;
	}
	
	public Vector getListJUPEM() {
		return listJUPEM;
	}

	public void setListJUPEM(Vector listJUPEM) {
		this.listJUPEM = listJUPEM;
	}

	public Vector getListJAS() {
		return listJAS;
	}

	public void setListJAS(Vector listJAS) {
		this.listJAS = listJAS;
	}

	public Vector getListJMG() {
		return listJMG;
	}

	public void setListJMG(Vector listJMG) {
		this.listJMG = listJMG;
	}

	public Vector getListJP() {
		return listJP;
	}

	public void setListJP(Vector listJP) {
		this.listJP = listJP;
	}

	public Vector getListJLM() {
		return listJLM;
	}

	public void setListJLM(Vector listJLM) {
		this.listJLM = listJLM;
	}

	public Vector getListPHM() {
		return listPHM;
	}

	public void setListPHM(Vector listPHM) {
		this.listPHM = listPHM;
	}

	public Vector getListJPS() {
		return listJPS;
	}

	public void setListJPS(Vector listJPS) {
		this.listJPS = listJPS;
	}

	public Vector getBeanMaklumatKJT() {
		return beanMaklumatKJT;
	}

	public void setBeanMaklumatKJT(Vector beanMaklumatKJT) {
		this.beanMaklumatKJT = beanMaklumatKJT;
	}

	public Vector getBeanMaklumatDokumen() {
		return beanMaklumatDokumen;
	}

	public void setBeanMaklumatDokumen(Vector beanMaklumatDokumen) {
		this.beanMaklumatDokumen = beanMaklumatDokumen;
	}

	public Vector getListPertindihan() {
		return listPertindihan;
	}

	public void setListPertindihan(Vector listPertindihan) {
		this.listPertindihan = listPertindihan;
	}

	public Vector getBeanMaklumatPertindihan() {
		return beanMaklumatPertindihan;
	}

	public void setBeanMaklumatPertindihan(Vector beanMaklumatPertindihan) {
		this.beanMaklumatPertindihan = beanMaklumatPertindihan;
	}

	public Vector getListPTG() {
		return listPTG;
	}

	public void setListPTG(Vector listPTG) {
		this.listPTG = listPTG;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}
	
	public Vector getListNotifikasi() {
		return listNotifikasi;
	}
	
	public Vector getBeanMaklumatKJP() {
		return beanMaklumatKJP;
	}
	
	public Vector getBeanMaklumatLampiranKJP() {
		return beanMaklumatLampiranKJP;
	}
	
}
