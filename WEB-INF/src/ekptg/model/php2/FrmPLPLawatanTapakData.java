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
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmPLPLawatanTapakData {

	private Vector listTanahPohon = null;
	private Vector listTanahGanti = null;
	private Vector beanMaklumatLaporanTanah = null;
	private Vector listKehadiran = null;
	private Vector beanMaklumatKehadiran = null;
	private Vector listImejan = null;
	private Vector beanMaklumatImejan = null;
	private Vector listDokumenLawatanTapak = null;
	private Vector beanMaklumatKJP = null;
	private Vector beanMaklumatPejabat = null;
	private static final Log log = LogFactory.getLog(FrmPLPLawatanTapakData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void hapusMaklumatKJP(String idUlasanTeknikal) throws Exception {

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

			AuditTrail.logActivity("1610200", "4", null, null, "DEL",
					"FAIL PELEPASAN [" + idUlasanTeknikal
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

	public String simpanDokumenLawatanTapakUlangan(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String idNegeri,
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

			sql = "SELECT BIL_ULANGAN, ID_NEGERI, ID_PEJABAT FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;

				idNegeri = rs.getString("ID_NEGERI") ;
				idPejabat = rs.getString("ID_PEJABAT") ;
			}

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
//			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_DOKUMEN", "4");
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

			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void simpanKemaskiniDokumenLawatanTapak(String idUlasanTeknikal,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, String socKeputusan,
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
			r.add("NO_RUJUKAN", txtNoRujukan);
			r.add("ULASAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN", socKeputusan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idUlasanTeknikal
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

	public void simpanDokumenLawatanTapak(Long idDokumen, String idPermohonan,
			String idNegeri, String idPejabat, String tarikhHantar, String jangkaMasa,
			String tarikhJangkaTerima, HttpSession session) throws Exception {

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

			// TBLPHPPEGAWAILAPORANTANAH
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			r.add("ID_DOKUMEN", 4L);
			if (!"".equals(tarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + tarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", jangkaMasa);
			if (!"".equals(tarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + tarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void setMaklumatKJP(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, ID_DOKUMEN, ID_NEGERI, ID_PEJABAT, TARIKH_HANTAR,"
					+ " TARIKH_JANGKA_TERIMA, JANGKAMASA, FLAG_STATUS, FLAG_AKTIF,"
					+ " TARIKH_TERIMA, TARIKH_SURAT, NO_RUJUKAN, ULASAN"
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
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999"
						: rs.getString("ID_NEGERI"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "99999"
						: rs.getString("ID_PEJABAT"));
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
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "99999"
						: rs.getString("ID_DOKUMEN"));
				beanMaklumatKJP.addElement(h);
				bil++;
				System.out.println("test: "+beanMaklumatKJP);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiDokumenLawatanTapak(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listDokumenLawatanTapak  = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, D.KETERANGAN AS NAMA_DOKUMEN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, D.ID_DOKUMEN, C.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABATJKPTG C, TBLPHPRUJDOKUMEN D WHERE A.ID_PEJABAT = C.ID_PEJABATJKPTG(+)"
					+ " AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) AND A.ID_DOKUMEN=4 AND A.ID_PERMOHONAN = '"
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
						statusHari = "Telah Luput";
						h.put("statusHari", statusHari);
					} else {
						System.out.println("dalam else");
						h.put("statusHari", "");
					}
				}
				listDokumenLawatanTapak.addElement(h);
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

	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	public void setSenaraiTanahPohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahPohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK, PHPHMP.NILAIAN"

					+ " FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND MOHON.ID_PERMOHONAN = '" + idPermohonan + "'";
System.out.println("setSenaraiTanahPohon :: sql >>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikPermohonan",
						rs.getString("ID_HAKMILIKPERMOHONAN"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("mukim",
						rs.getString("NAMA_MUKIM") == null ? "" : rs
								.getString("NAMA_MUKIM"));
				h.put("daerah",
						rs.getString("NAMA_DAERAH") == null ? "" : rs
								.getString("NAMA_DAERAH"));
				h.put("negeri",
						rs.getString("NAMA_NEGERI") == null ? "" : rs
								.getString("NAMA_NEGERI"));
				listTanahPohon.addElement(h);
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

	public void setSenaraiTanahGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_TANAHGANTI, A.PEGANGAN_HAKMILIK, C.KETERANGAN, A.NO_LOT, B.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM"
					+ " FROM TBLPHPTANAHGANTIPLPSN A, TBLRUJJENISHAKMILIK B, TBLRUJLOT C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F"
					+ " WHERE A.ID_JENISHAKMILIK = B.ID_JENISHAKMILIK(+) AND A.ID_LOT = C.ID_LOT(+) AND A.ID_NEGERI = D.ID_NEGERI AND A.ID_DAERAH = E.ID_DAERAH AND A.ID_MUKIM = F.ID_MUKIM"
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idTanahGanti", rs.getString("ID_TANAHGANTI"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK"));
				h.put("lot",
						rs.getString("KETERANGAN") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("KETERANGAN")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null
								|| rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK") + " "
										+ rs.getString("NO_HAKMILIK"));
				h.put("noWarta", "");
				h.put("mukim",
						rs.getString("NAMA_MUKIM") == null ? "" : rs
								.getString("NAMA_MUKIM"));
				h.put("daerah",
						rs.getString("NAMA_DAERAH") == null ? "" : rs
								.getString("NAMA_DAERAH"));
				h.put("negeri",
						rs.getString("NAMA_NEGERI") == null ? "" : rs
								.getString("NAMA_NEGERI"));
				listTanahGanti.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdLaporanTanah(String idHakmilikPermohonan,
			String idTanahGanti, String flagJenisTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("P".equals(flagJenisTanah)) {

				sql = "SELECT ID_LAPORANTANAH FROM TBLPHPLAPORANTANAH WHERE FLAG_JENISTANAH = 'P' AND ID_HAKMILIK = '"
						+ idHakmilikPermohonan + "'";

			} else if ("G".equals(flagJenisTanah)) {

				sql = "SELECT ID_LAPORANTANAH FROM TBLPHPLAPORANTANAH WHERE FLAG_JENISTANAH = 'G' AND ID_HAKMILIK = '"
						+ idTanahGanti + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_LAPORANTANAH");
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

	public void setMaklumatLaporanTanah(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatLaporanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH, TARIKH_LAWATAN, FLAG_LAWATAN, TUJUAN_LAPORAN, LAPORAN_ATASTANAH, ULASAN,"
					+ " JALAN_HUBUNGAN, KAWASAN_BERHAMPIRAN, JARAK_DARIBANDAR, KEADAAN_RUPABUMI, KEADAAN_TANAH, FLAG_KEMUDAHANASAS_AIR,"
					+ " FLAG_KEMUDAHANASAS_ELEKTRIK, FLAG_KEMUDAHANASAS_TEL, KEMUDAHAN_ASAS, SEMP_UTARA, SEMP_SELATAN, SEMP_TIMUR, SEMP_BARAT,"
					+ " NAMA_PELAPOR, ID_JAWATANPELAPOR, ID_NEGERIPELAPOR, CATATAN"
					+ " FROM TBLPHPLAPORANTANAH"
					+ " WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idLaporanTanah",
						rs.getString("ID_LAPORANTANAH") == null ? "" : rs
								.getString("ID_LAPORANTANAH"));
				h.put("flagLawatan", rs.getString("FLAG_LAWATAN") == null ? ""
						: rs.getString("FLAG_LAWATAN"));
				h.put("tarikhLawatan",
						rs.getString("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("tujuanLaporan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
				h.put("laporanAtasTanah",
						rs.getString("LAPORAN_ATASTANAH") == null ? "" : rs
								.getString("LAPORAN_ATASTANAH"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				h.put("jalanHubungan",
						rs.getString("JALAN_HUBUNGAN") == null ? "" : rs
								.getString("JALAN_HUBUNGAN"));
				h.put("kawasanBerhampiran",
						rs.getString("KAWASAN_BERHAMPIRAN") == null ? "" : rs
								.getString("KAWASAN_BERHAMPIRAN"));
				h.put("jarakDariBandar",
						rs.getString("JARAK_DARIBANDAR") == null ? "" : rs
								.getString("JARAK_DARIBANDAR"));
				h.put("keadaanRupabumi",
						rs.getString("KEADAAN_RUPABUMI") == null ? "" : rs
								.getString("KEADAAN_RUPABUMI"));
				h.put("keadaanTanah",
						rs.getString("KEADAAN_TANAH") == null ? "" : rs
								.getString("KEADAAN_TANAH"));
				h.put("flagAir",
						rs.getString("FLAG_KEMUDAHANASAS_AIR") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_AIR"));
				h.put("flagElektrik", rs
						.getString("FLAG_KEMUDAHANASAS_ELEKTRIK") == null ? ""
						: rs.getString("FLAG_KEMUDAHANASAS_ELEKTRIK"));
				h.put("flagTel",
						rs.getString("FLAG_KEMUDAHANASAS_TEL") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_TEL"));
				h.put("kemudahanAsas",
						rs.getString("KEMUDAHAN_ASAS") == null ? "" : rs
								.getString("KEMUDAHAN_ASAS"));
				h.put("utara",
						rs.getString("SEMP_UTARA") == null ? "" : rs
								.getString("SEMP_UTARA"));
				h.put("selatan",
						rs.getString("SEMP_SELATAN") == null ? "" : rs
								.getString("SEMP_SELATAN"));
				h.put("timur",
						rs.getString("SEMP_TIMUR") == null ? "" : rs
								.getString("SEMP_TIMUR"));
				h.put("barat",
						rs.getString("SEMP_BARAT") == null ? "" : rs
								.getString("SEMP_BARAT"));

				h.put("namaPegawai", rs.getString("NAMA_PELAPOR") == null ? ""
						: rs.getString("NAMA_PELAPOR"));
				h.put("idJawatan",
						rs.getString("ID_JAWATANPELAPOR") == null ? "99999"
								: rs.getString("ID_JAWATANPELAPOR"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPELAPOR") == null ? "99999" : rs
								.getString("ID_NEGERIPELAPOR"));
				beanMaklumatLaporanTanah.addElement(h);
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

	public void simpanKemaskiniMaklumatLaporanTanah(String idLaporanTanah,
			String txtTarikhLawatan, String socFlagLawatan,
			String txtTujuanLaporan, String txtLaporanAtasTanah,
			String txtIsuUlasan, String txtCatatan, HttpSession session)
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);

			if (!"".equals(txtTarikhLawatan)) {
				r.add("TARIKH_LAWATAN",
						r.unquote("to_date('" + txtTarikhLawatan
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_LAWATAN", socFlagLawatan);
			r.add("TUJUAN_LAPORAN", txtTujuanLaporan);
			r.add("LAPORAN_ATASTANAH", txtLaporanAtasTanah);
			r.add("ULASAN", txtIsuUlasan);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idLaporanTanah
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

	public void updateMinitKewangan(String idPermohonan, String tujuanLaporan, String laporanAtasTanah, HttpSession session) throws Exception {

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

			// TBLPHPKERTASKERJAPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "2");
			r.add("TUJUAN", tujuanLaporan);
			r.add("PERIHAL_KEMAJUANTANAH", laporanAtasTanah);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610202", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void simpanKemaskiniMaklumatLain(String idLaporanTanah,
			String txtJalanHubungan, String txtKawasanBerhampiran,
			String txtJarakDariBandar, String kemudahanAsasA,
			String kemudahanAsasL, String kemudahanAsasT,
			String txtKemudahanAsas, String txtKeadaanTanah,
			String txtKeadaanRupabumi, String txtUtara, String txtSelatan,
			String txtTimur, String txtBarat, HttpSession session)
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);

			r.add("JALAN_HUBUNGAN", txtJalanHubungan);
			r.add("KAWASAN_BERHAMPIRAN", txtKawasanBerhampiran);
			r.add("JARAK_DARIBANDAR", txtJarakDariBandar);
			r.add("FLAG_KEMUDAHANASAS_AIR", kemudahanAsasA);
			r.add("FLAG_KEMUDAHANASAS_ELEKTRIK", kemudahanAsasL);
			r.add("FLAG_KEMUDAHANASAS_TEL", kemudahanAsasT);
			r.add("KEMUDAHAN_ASAS", txtKemudahanAsas);
			r.add("KEADAAN_TANAH", txtKeadaanTanah);
			r.add("KEADAAN_RUPABUMI", txtKeadaanRupabumi);
			r.add("SEMP_UTARA", txtUtara);
			r.add("SEMP_SELATAN", txtSelatan);
			r.add("SEMP_TIMUR", txtTimur);
			r.add("SEMP_BARAT", txtBarat);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idLaporanTanah
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

	public void setSenaraiKehadiran(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEGAWAILAPORANTANAH, A.NAMA_PEGAWAI, B.KETERANGAN AS JAWATAN, C.NAMA_NEGERI"
					+ " FROM TBLPHPPEGAWAILAPORANTANAH A, TBLRUJJAWATAN B, TBLRUJNEGERI C WHERE A.ID_JAWATAN = B.ID_JAWATAN AND A.ID_NEGERIPEGAWAI = C.ID_NEGERI"
					+ " AND A.ID_LAPORANTANAH = '" + idLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPegawaiLaporanTanah",
						rs.getString("ID_PEGAWAILAPORANTANAH") == null ? ""
								: rs.getString("ID_PEGAWAILAPORANTANAH"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				listKehadiran.addElement(h);
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

	public String getFlagLawatanByIdLaporanTanah(String idLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_LAWATAN FROM TBLPHPLAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("FLAG_LAWATAN");
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

	public String getIdNegeriTanahPohon(String idHakmilikPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHM.ID_NEGERI FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM"
					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN"
					+ " AND PHPHM.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI");
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

	public String getIdNegeriTanahGanti(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_NEGERI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI");
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

	public void simpanKehadiran(String idLaporanTanah, String txtNamaPegawai,
			String idNegeri, String idJawatan, HttpSession session)
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

			// TBLPHPPEGAWAILAPORANTANAH
			long idPegawaiLaporan = DB
					.getNextID("TBLPHPPEGAWAILAPORANTANAH_SEQ");
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporan);
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL PELEPASAN [" + idLaporanTanah
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

	public void simpanKemaskiniKehadiran(String idPegawaiLaporanTanah,
			String txtNamaPegawai, String idNegeri, String idJawatan,
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

			// TBLPHPPEGAWAILAPORANTANAH
			r.update("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idPegawaiLaporanTanah
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

	public void hapusKehadiran(String idPegawaiLaporanTanah) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEGAWAILAPORANTANAH
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);

			sql = r.getSQLDelete("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, null, "DEL",
					"FAIL PELEPASAN [" + idPegawaiLaporanTanah
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

	public void setMaklumatKehadiran(String idPegawaiLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_PEGAWAI, ID_JAWATAN, ID_NEGERIPEGAWAI FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_PEGAWAILAPORANTANAH = '"
					+ idPegawaiLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("idJawatan", rs.getString("ID_JAWATAN") == null ? "99999"
						: rs.getString("ID_JAWATAN"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPEGAWAI") == null ? "99999" : rs
								.getString("ID_NEGERIPEGAWAI"));
				beanMaklumatKehadiran.addElement(h);
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

	public void simpanKemaskiniPelapor(String idLaporanTanah, String txtNama,
			String idNegeri, String idJawatan, HttpSession session)
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PELAPOR", txtNama);
			r.add("ID_JAWATANPELAPOR", idJawatan);
			r.add("ID_NEGERIPELAPOR", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idLaporanTanah
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

	public void setSenaraiImejan(String idLaporanTanah, String flag_dokumen) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_LAPORANTANAH = '" + idLaporanTanah + "' AND FLAG_DOKUMEN = '" + flag_dokumen + "'";

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
				listImejan.addElement(h);
				bil++;
				count++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
 finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatImej(String idDokumen,String flagDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "' AND FLAG_DOKUMEN = '"
					+ flagDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatImejan.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
 finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaImej,
			String txtCatatan, HttpSession session) throws Exception {
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

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaImej);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idDokumen
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

	public void hapusDokumen(String idDokumen) throws Exception {
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
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, null, "DEL",
					"FAIL PELEPASAN [" + idDokumen
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
			r.add("ID_STATUS", "1610199"); // JABATAN TEKNIKAL

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610199")); // JABATAN TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
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

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public Vector getListTanahPohon() {
		return listTanahPohon;
	}

	public void setListTanahPohon(Vector listTanahPohon) {
		this.listTanahPohon = listTanahPohon;
	}

	public Vector getListTanahGanti() {
		return listTanahGanti;
	}

	public void setListTanahGanti(Vector listTanahGanti) {
		this.listTanahGanti = listTanahGanti;
	}

	public Vector getBeanMaklumatLaporanTanah() {
		return beanMaklumatLaporanTanah;
	}

	public void setBeanMaklumatLaporanTanah(Vector beanMaklumatLaporanTanah) {
		this.beanMaklumatLaporanTanah = beanMaklumatLaporanTanah;
	}

	public Vector getListKehadiran() {
		return listKehadiran;
	}

	public void setListKehadiran(Vector listKehadiran) {
		this.listKehadiran = listKehadiran;
	}

	public Vector getBeanMaklumatKehadiran() {
		return beanMaklumatKehadiran;
	}

	public void setBeanMaklumatKehadiran(Vector beanMaklumatKehadiran) {
		this.beanMaklumatKehadiran = beanMaklumatKehadiran;
	}

	public Vector getListImejan() {
		return listImejan;
	}

	public void setListImejan(Vector listImejan) {
		this.listImejan = listImejan;
	}

	public Vector getBeanMaklumatImejan() {
		return beanMaklumatImejan;
	}

	public void setBeanMaklumatImejan(Vector beanMaklumatImejan) {
		this.beanMaklumatImejan = beanMaklumatImejan;
	}

	public Vector getListDokumenLawatanTapak() {
		return listDokumenLawatanTapak;
	}

	public void setListDokumenLawatanTapak(Vector listDokumenLawatanTapak) {
		this.listDokumenLawatanTapak = listDokumenLawatanTapak;
	}

	public Vector getBeanMaklumatKJP() {
		return beanMaklumatKJP;
	}

	public void setBeanMaklumatKJP(Vector beanMaklumatKJP) {
		this.beanMaklumatKJP = beanMaklumatKJP;
	}

}
