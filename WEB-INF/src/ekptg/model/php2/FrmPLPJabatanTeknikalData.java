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
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.NumberToWords;
import ekptg.helpers.Utils;
import ekptg.model.php2.online.FrmPLPOnlineKJPSenaraiFailData;
import ekptg.model.utils.emel.EmailConfig;

/**
 * modified by hilda
 * 
 */
public class FrmPLPJabatanTeknikalData {
	
	static Logger myLog = Logger.getLogger(FrmPLPJabatanTeknikalData.class);

	private Vector listKJT = null;
	private Vector beanMaklumatKJT = null;
	private Vector listKJP = null;
	private Vector beanMaklumatKJP = null;
	private Vector beanMaklumatUlasanKJP = null;
	private Vector beanMaklumatLampiranKJP = null;
	private Vector listTanahPohon = null;
	private Vector listTanahGanti = null;
	private Vector beanKertasCadangan = null;
	private Vector beanMaklumatPejabat = null;
	private Vector listJPPH = null;
	private Vector beanMaklumatJPPH = null;
	private Vector beanMaklumatKewangan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiKJT(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKJT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, D.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, D.ID_DOKUMEN, C.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLRUJPEJABATJKPTG C, TBLPHPRUJDOKUMEN D WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_PEJABAT = C.ID_PEJABATJKPTG(+) AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) AND A.FLAG_KJP = 'KJT' AND A.ID_PERMOHONAN = '"
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
				if ("1".equals(rs.getString("ID_DOKUMEN"))) {
					h.put("agensi", rs.getString("NAMA_AGENSI") == null ? ""
							: rs.getString("NAMA_AGENSI").toUpperCase());
				} else if ("4".equals(rs.getString("ID_DOKUMEN"))) {
					h.put("agensi", rs.getString("NAMA_PEJABAT") == null ? ""
							: rs.getString("NAMA_PEJABAT").toUpperCase());
				} else {
					h.put("agensi", "");
				}
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
				listKJT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'KJP' AND A.ID_PERMOHONAN = '"
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

					if(rs.getString("TARIKH_JANGKA_TERIMA")!=null) {
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
							h.put("statusHari", "");
						}
					}
				}
				listKJP.addElement(h);
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
					+ " PHPHMP.FLAG_BORANGK, PHPHMP.NILAIAN, PHPHMP.NILAIAN_BANGUNAN, (PHPHMP.NILAIAN + PHPHMP.NILAIAN_BANGUNAN) AS NILAIAN_TOTAL"

					+ " FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND MOHON.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikPermohonan",
						rs.getString("ID_HAKMILIKPERMOHONAN"));
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
				h.put("nilaian", rs.getString("NILAIAN") == null ? "0.00"
						: Utils.format2Decimal(rs.getDouble("NILAIAN")));
				h.put("nilaianB",
						rs.getString("NILAIAN_BANGUNAN") == null ? "0.00"
								: Utils.format2Decimal(rs
										.getDouble("NILAIAN_BANGUNAN")));
				h.put("nilaianTotal",
						rs.getString("NILAIAN_TOTAL") == null ? "0.00" : Utils
								.format2Decimal(rs.getDouble("NILAIAN_TOTAL")));

				listTanahPohon.addElement(h);
				bil++;
			}

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

			sql = "SELECT A.ID_TANAHGANTI, C.KETERANGAN, A.NO_LOT, B.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, A.NILAIAN, A.NILAIAN_BANGUNAN, (A.NILAIAN + A.NILAIAN_BANGUNAN) AS NILAIAN_TOTAL "
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
				h.put("nilaian", rs.getString("NILAIAN") == null ? "0.00"
						: Utils.format2Decimal(rs.getDouble("NILAIAN")));
				h.put("nilaianB",
						rs.getString("NILAIAN_BANGUNAN") == null ? "0.00"
								: Utils.format2Decimal(rs
										.getDouble("NILAIAN_BANGUNAN")));
				h.put("nilaianTotal",
						rs.getString("NILAIAN_TOTAL") == null ? "0.00" : Utils
								.format2Decimal(rs.getDouble("NILAIAN_TOTAL")));
				listTanahGanti.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusMaklumatKJPKJT(String idUlasanTeknikal) throws Exception {

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
			
			AuditTrail.logActivity("1610199", "4", null, null, "DEL",
					"MAKLUMAT KJPKJT [" + idUlasanTeknikal
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

	public String simpanMaklumatKJT(String idPermohonan, String idDokumen,
			String idKementerian, String idAgensi, String idPejabat,
			String idNegeri, String txtTarikhHantar, String txtJangkaMasa,
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
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "KJT");
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
			} else if ("4".equals(idDokumen)) {
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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

	public String simpanMaklumatUlanganKJT(String idUlasanTeknikalLama,
			String idPermohonan, String idDokumen, String idKementerian,
			String idAgensi, String idPejabat, String idNegeri,
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
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "KJT");
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
			} else if ("4".equals(idDokumen)) {
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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

	public void simpanKemaskiniMaklumatKJT(String idUlasanTeknikal,
			String idDokumen, String idKementerian, String idAgensi,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, HttpSession session)
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_DOKUMEN", idDokumen);
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
				r.add("ID_NEGERI", "");
				r.add("ID_PEJABAT", "");
			} else if ("4".equals(idDokumen)) {
				r.add("ID_MENTERI", "");
				r.add("ID_AGENSI", "");
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idUlasanTeknikal + "] DIKEMASKINI");

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

	public String simpanMaklumatKJP(String idPermohonan, String idKementerian,
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
			r.add("FLAG_KJP", "KJP");
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

	public String simpanMaklumatUlanganKJP(String idUlasanTeknikalLama,
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
			r.add("FLAG_KJP", "KJP");
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

	public void simpanKemaskiniMaklumatKJP(String idUlasanTeknikal,
			String idKementerian, String idAgensi, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, String socKeputusan,
			String socKeputusanPemohon, String txtUlasanPemohon, HttpSession session) throws Exception {

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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
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
			r.add("ULASAN_PEMOHON", txtUlasanPemohon);
			r.add("FLAG_KEPUTUSAN_PEMOHON", socKeputusanPemohon);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idUlasanTeknikal + "] DIKEMASKINI");

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
	
	public void updateUlasanKJP(String idPermohonan, String ulasan, HttpSession session) throws Exception {

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
			r.add("ULASAN_KJP", ulasan);

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

	public void setMaklumatKJT(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, ID_MENTERI, ID_AGENSI, ID_DOKUMEN, ID_NEGERI, ID_PEJABAT, TARIKH_HANTAR,"
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
				h.put("idKementerian",
						rs.getString("ID_MENTERI") == null ? "99999" : rs
								.getString("ID_MENTERI"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "99999"
						: rs.getString("ID_AGENSI"));
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "99999"
						: rs.getString("ID_DOKUMEN"));
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
				beanMaklumatKJT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatUlasanKJP(String idUlasanTeknikal, String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatUlasanKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.ID_ULASANTEKNIKAL, A.ID_PERMOHONAN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA, A.FLAG_STATUS, A.FLAG_AKTIF, A.ID_MENTERI, A.ID_AGENSI,"
					+ " A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJUKAN, A.ULASAN, A.FLAG_KEPUTUSAN, "

					//add by aishah 08062017 - untuk dapatkan nama pegawai yang memberi ulasan
					+ " A.NAMA_PEGAWAI, A.NO_TELEFON"
					
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C WHERE A.ID_MENTERI = B.ID_KEMENTERIAN(+)"
					+ " AND A.ID_AGENSI = C.ID_AGENSI(+) "
					+ "AND A.ID_ULASANTEKNIKAL = '"+idUlasanTeknikal+"' "
					+ " AND A.ID_PERMOHONAN = '"+idPermohonan+"' ";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("setMaklumatUlasanKJP==="+sql);

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
				//add by aishah 08062017 - untuk dapatkan nama pegawai yang memberi ulasan
				h.put("namaPengulas",
						rs.getString("NAMA_PEGAWAI") == null ? "" : rs
								.getString("NAMA_PEGAWAI"));
				h.put("noTelPengulas",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				beanMaklumatUlasanKJP.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public void setMaklumatKJP(String idUlasanTeknikal, String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_TERIMA_KEWANGAN, KEPUTUSAN_KEWANGAN, ULASAN_KEWANGAN, FLAG_KEPUTUSAN_PEMOHON, ULASAN_PEMOHON FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhSurat",
						rs.getDate("TARIKH_TERIMA_KEWANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_KEWANGAN")));
				h.put("flagKeputusan",
						rs.getString("KEPUTUSAN_KEWANGAN") == null ? "" : rs
								.getString("KEPUTUSAN_KEWANGAN"));
				h.put("ulasan", rs.getString("ULASAN_KEWANGAN") == null ? ""
						: rs.getString("ULASAN_KEWANGAN"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				h.put("ulasanPemohon", rs.getString("ULASAN_PEMOHON") == null ? ""
						: rs.getString("ULASAN_PEMOHON"));
				beanMaklumatKJP.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatMinitKewangan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKewangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_TERIMA_KEWANGAN, KEPUTUSAN_KEWANGAN, ULASAN_KEWANGAN, FLAG_KEPUTUSAN_PEMOHON, ULASAN_PEMOHON FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA_KEWANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_KEWANGAN")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN_KEWANGAN") == null ? "" : rs
								.getString("KEPUTUSAN_KEWANGAN"));
				h.put("ulasan", rs.getString("ULASAN_KEWANGAN") == null ? ""
						: rs.getString("ULASAN_KEWANGAN"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				h.put("ulasanPemohon", rs.getString("ULASAN_PEMOHON") == null ? ""
						: rs.getString("ULASAN_PEMOHON"));
				beanMaklumatKewangan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKJPulasanMOF(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_TERIMA_KEWANGAN, KEPUTUSAN_KEWANGAN, ULASAN_KEWANGAN, FLAG_KEPUTUSAN_PEMOHON, ULASAN_PEMOHON FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("beanMaklumatKJP==="+sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA_KEWANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_KEWANGAN")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN_KEWANGAN") == null ? "" : rs
								.getString("KEPUTUSAN_KEWANGAN"));
				h.put("ulasan", rs.getString("ULASAN_KEWANGAN") == null ? ""
						: rs.getString("ULASAN_KEWANGAN"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				h.put("ulasanPemohon", rs.getString("ULASAN_PEMOHON") == null ? ""
						: rs.getString("ULASAN_PEMOHON"));
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

			sql = " SELECT ID_DOKUMEN, NAMA_DOKUMEN, NAMA_FAIL, CATATAN, CONTENT "
					+ " FROM TBLPHPDOKUMEN "
					+ " WHERE ID_PERMOHONAN = '"+idPermohonan+"' AND FLAG_DOKUMEN = 'P' ";

			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("content",
						rs.getString("CONTENT") == null ? "" : rs
								.getString("CONTENT"));
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
	
	public Vector getBeanMaklumatLampiranKJP() {
		return beanMaklumatLampiranKJP;
	}

	public String simpanMaklumatJPPH(String idPermohonan, String idPejabatJPPH,
			String txtTarikhHantar, String txtJangkaMasa,
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
			r.add("ID_PEJABAT", idPejabatJPPH);
			r.add("FLAG_KJP", "JPPH");
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

	public String simpanMaklumatUlanganJPPH(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabatJPPH, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

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
			r.add("ID_PEJABAT", idPejabatJPPH);
			r.add("FLAG_KJP", "JPPH");
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

	public void simpanKemaskiniMaklumatJPPH(String idUlasanTeknikal,
			String idPejabatJPPH, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat, String txtNoRujukan,
			String txtUlasan, HttpSession session) throws Exception {

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
			r.add("ID_PEJABAT", idPejabatJPPH);
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

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idUlasanTeknikal + "] DIKEMASKINI");

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
	
	public void updateMaklumatJPPH(String idPermohonan, String ulasan, HttpSession session) throws Exception {

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
			r.add("LAPORAN_NILAIAN", ulasan);

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610201")); // MESYUARAT
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
	
	public void gotoHantarHQ(String idFail, String idNegeriUser,
			String idPermohonan, HttpSession session) throws Exception{
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);
			
			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_HANTAR_HQ", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA HQ");

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
	
	public void gotoHantarTugasanPP(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA PP HQ");

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
	
	public void doSimpanAgihanTugas(String idFail, String idPegawai,
			String catatan, String idNegeriUser, HttpSession session)
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahHQ");
			r.add("CATATAN", catatan);
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] TELAH DITUGASKAN");

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

	public void updateKertasCadangan(String idKertasKerja,
			String txtTajukKertas, String txtTujuan,
			String txtLatarBelakangTanah, String txtLaporanNilaian,
			String txtCadanganPembangunan, String txtPemohon,
			String txtPerakuanPTP, HttpSession session) throws Exception {

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
			r.update("ID_KERTASKERJA", idKertasKerja);
			r.update("FLAG_KERTAS", "1");
			r.add("TAJUK", txtTajukKertas);
			r.add("TUJUAN", txtTujuan);
			r.add("LATAR_BELAKANGTANAH", txtLatarBelakangTanah);
			r.add("LAPORAN_NILAIAN", txtLaporanNilaian);
			r.add("CADANGAN_PROJEK", txtCadanganPembangunan);
			r.add("PEMOHON", txtPemohon);
			r.add("PERAKUAN_PTP", txtPerakuanPTP);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idKertasKerja + "] DIKEMASKINI");

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

	public void setMaklumatKertasCadangan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasCadangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			String laporanNilaianAdd = "";

			sql = "SELECT (TBLPHPHAKMILIKPERMOHONAN.NILAIAN+TBLPHPHAKMILIKPERMOHONAN.NILAIAN_BANGUNAN) AS NILAIAN, "
					+ " TBLPHPKERTASKERJAPELEPASAN.ID_KERTASKERJA, "
					+ " TBLPHPKERTASKERJAPELEPASAN.TAJUK, "
					+ " TBLPHPKERTASKERJAPELEPASAN.TUJUAN, "
					+ " TBLPHPKERTASKERJAPELEPASAN.LATAR_BELAKANGTANAH, "
					+ " TBLPHPKERTASKERJAPELEPASAN.LAPORAN_NILAIAN, "
					+ " TBLPHPKERTASKERJAPELEPASAN.CADANGAN_PROJEK, "
					+ " TBLPHPKERTASKERJAPELEPASAN.PEMOHON, "
					+ " TBLPHPKERTASKERJAPELEPASAN.PERAKUAN_PTP "
					+ " FROM TBLPHPKERTASKERJAPELEPASAN,TBLPHPHAKMILIKPERMOHONAN "
					+ " WHERE FLAG_KERTAS = '1' AND TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
					+ " AND TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				String nilaianWords = NumberToWords.convertTwoPart(rs
						.getString("NILAIAN") == null ? "0.00" : rs
						.getString("NILAIAN"));
				String cekLaporanNilaian = (rs.getString("LAPORAN_NILAIAN") == null ? ""
						: rs.getString("LAPORAN_NILAIAN"));
				if (cekLaporanNilaian.equals("")) {
					laporanNilaianAdd = "";
				} else {
					laporanNilaianAdd = (rs.getString("LAPORAN_NILAIAN") == null ? ""
							: rs.getString("LAPORAN_NILAIAN"))
							+ " RM "
							+ (rs.getString("NILAIAN") == null ? "" : rs
									.getString("NILAIAN"))
							+ " (RINGGIT MALAYSIA : " + nilaianWords + ").";
				}
				h = new Hashtable();
				h.put("idKertasKerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("txtTajukKertas",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("txtTujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("txtLatarBelakangTanah",
						rs.getString("LATAR_BELAKANGTANAH") == null ? "" : rs
								.getString("LATAR_BELAKANGTANAH"));
				h.put("txtLaporanNilaian", laporanNilaianAdd);
				h.put("txtCadanganPembangunan",
						rs.getString("CADANGAN_PROJEK") == null ? "" : rs
								.getString("CADANGAN_PROJEK"));
				h.put("txtPemohon",
						rs.getString("PEMOHON") == null ? "" : rs
								.getString("PEMOHON"));
				h.put("txtPerakuanPTP",
						rs.getString("PERAKUAN_PTP") == null ? "" : rs
								.getString("PERAKUAN_PTP"));
				beanKertasCadangan.addElement(h);
				bil++;
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

	public void setMaklumatPejabat(String idPejabatJPPH) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABAT"
					+ " FROM TBLRUJPEJABAT A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABAT = '"
					+ idPejabatJPPH + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabatJPPH", rs.getString("ID_PEJABAT") == null ? ""
						: rs.getString("ID_PEJABAT"));
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
				h.put("idPejabatJPPH", "");
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

	public void setMaklumatJPPH(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, TARIKH_HANTAR, TARIKH_JANGKA_TERIMA, JANGKAMASA, FLAG_STATUS, FLAG_AKTIF, ID_PEJABAT,"
					+ " TARIKH_TERIMA, TARIKH_SURAT, NO_RUJUKAN, ULASAN"
					+ " FROM TBLPHPULASANTEKNIKAL A WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
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
				beanMaklumatJPPH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiJPPH(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEJABAT, A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JPPH' AND A.ID_PERMOHONAN = '"
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
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
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
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				if (rs.getString("ID_PEJABAT") != null
						&& !"".equals(rs.getString("ID_PEJABAT"))) {
					h.put("flagSuratUlasan",
							getFlagSuratJPPH(idPermohonan,
									rs.getString("ID_PEJABAT")));
				} else {
					h.put("flagSuratUlasan", "");
				}

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
						h.put("statusHari", "");
					}
				}

				listJPPH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getFlagSuratJPPH(String idPermohonan, String idPejabat)
			throws Exception {
		Db db = null;
		String sql = "";
		boolean flagTanahMohon = false;
		boolean flagTanahGanti = false;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// TANAH POHON
			sql = "SELECT TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN FROM TBLPHPHAKMILIKPERMOHONAN, TBLHTPHAKMILIKAGENSI, TBLHTPHAKMILIK, TBLRUJPEJABATURUSAN"
					+ " WHERE TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKAGENSI = TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI"
					+ " AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = TBLHTPHAKMILIK.ID_HAKMILIK"
					+ " AND TBLRUJPEJABATURUSAN.ID_DAERAHURUS = TBLHTPHAKMILIK.ID_DAERAH"
					+ " AND TBLRUJPEJABATURUSAN.ID_NEGERIURUS = TBLHTPHAKMILIK.ID_NEGERI"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN = '"
					+ idPermohonan
					+ "'"
					+ " AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";
			ResultSet rsMohon = stmt.executeQuery(sql);

			if (rsMohon.next()) {
				flagTanahMohon = true;
			}

			// TANAH GANTI
			sql = "SELECT TBLPHPTANAHGANTIPLPSN.ID_TANAHGANTI FROM TBLPHPTANAHGANTIPLPSN, TBLRUJPEJABATURUSAN"
					+ " WHERE TBLRUJPEJABATURUSAN.ID_DAERAHURUS = TBLPHPTANAHGANTIPLPSN.ID_DAERAH"
					+ " AND TBLRUJPEJABATURUSAN.ID_NEGERIURUS = TBLPHPTANAHGANTIPLPSN.ID_NEGERI"
					+ " AND TBLPHPTANAHGANTIPLPSN.ID_PERMOHONAN = '"
					+ idPermohonan
					+ "'"
					+ " AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";
			ResultSet rsGanti = stmt.executeQuery(sql);

			if (rsGanti.next()) {
				flagTanahGanti = true;
			}

			// RETURN FLAG
			if (flagTanahMohon && flagTanahGanti) {
				return "B";
			} else if (flagTanahMohon) {
				return "P";
			} else if (flagTanahGanti) {
				return "G";
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

	public void updateNilaianTanahPohon(String idHakmilikPermohonan,
			String txtNilaiTanahPohon, String txtNilaiBangunanPohon,
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
			String laporanNilaian = "";

			// TBLPHPHAKMILIKPERMOHONAN
			r.update("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			r.add("NILAIAN", Utils.RemoveSymbol(txtNilaiTanahPohon));
			r.add("NILAIAN_BANGUNAN", Utils.RemoveSymbol(txtNilaiBangunanPohon));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idHakmilikPermohonan + "] DIKEMASKINI");

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

	public void updateNilaianTanahGanti(String idTanahGanti,
			String txtNilaiTanahGanti, String txtNilaiBangunanGanti,
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

			// TBLPHPTANAHGANTIPLPSN
			r.update("ID_TANAHGANTI", idTanahGanti);
			r.add("NILAIAN", Utils.RemoveSymbol(txtNilaiTanahGanti));
			r.add("NILAIAN_BANGUNAN", Utils.RemoveSymbol(txtNilaiBangunanGanti));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPTANAHGANTIPLPSN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idTanahGanti + "] DIKEMASKINI");

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

	public String getIdPejabatJPPH(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		String idPejabat = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG"
					+ " FROM TBLHTPHAKMILIK, TBLRUJPEJABATURUSAN"
					+ " WHERE TBLHTPHAKMILIK.ID_NEGERI = TBLRUJPEJABATURUSAN.ID_NEGERIURUS"
					+ " AND TBLHTPHAKMILIK.ID_DAERAH = TBLRUJPEJABATURUSAN.ID_DAERAHURUS"
					+ " AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = '3' AND TBLHTPHAKMILIK.ID_HAKMILIK = '"
					+ idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idPejabat = rs.getString("ID_PEJABATJKPTG");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idPejabat;
	}
	
	public Vector getBeanMaklumatLampiran(String idUlasanTeknikal)
			throws Exception {
		Db db = null;
		String sql = "";
		Vector beanMaklumatLampiran = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				beanMaklumatLampiran = new Vector();
				h = new Hashtable();
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				beanMaklumatLampiran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

		return beanMaklumatLampiran;
	}
	
	public void sendEmailtoKJP(String idPermohonan, String idKementerian, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //untuk sementara
		String tempoh = "";
		String noFail = "";
		String tarikhAkhir = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT D.NO_FAIL, A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA"
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ " WHERE A.ID_MENTERI = B.ID_KEMENTERIAN AND A.ID_PERMOHONAN = C.ID_PERMOHONAN "
				+ " AND C.ID_FAIL = D.ID_FAIL AND B.ID_KEMENTERIAN = '"+idKementerian+"' "
				+ " AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				tempoh = rsEmel.getString("JANGKAMASA");
				tarikhAkhir = sdf.format(rsEmel.getDate("TARIKH_JANGKA_TERIMA"));
			}	
			
			email.RECIEPIENT = emelUser;
			email.SUBJECT = "PERMOHONAN ULASAN URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
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
	
	public void sendEmailtoJPPH(String idPermohonan, String idPejabatJPPH, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //untuk sementara
		String tempoh = "";
		String noFail = "";
		String tarikhAkhir = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA, D.NO_FAIL"
				+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPERMOHONAN C, TBLPFDFAIL D"
				+ " WHERE A.ID_PEJABAT = B.ID_PEJABAT AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL"
				+ " AND A.FLAG_KJP = 'JPPH' AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				tempoh = rsEmel.getString("JANGKAMASA");
				tarikhAkhir = sdf.format(rsEmel.getDate("TARIKH_JANGKA_TERIMA"));
			}	
						
			email.RECIEPIENT = emelUser;
			email.SUBJECT = "PERMOHONAN ULASAN NILAIAN HARTA URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			email.MESSAGE = "Mohon pihak tuan memberikan ulasan dan keputusan nilaian bagi permohonan tersebut<br><br>"
							 + "Kerjasama daripada pihak tuan untuk mengemukakan keputusan tersebut kepada Jabatan ini "
							 + "sebelum " + tarikhAkhir + " (" + tempoh + " hari) amatlah dihargai."
							 + " <br><br>Sekian, terima kasih.<br><br><br>"			
							 + " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void sendEmailtoUserJKPTGN(String idPermohonan, String idNegeriUser, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String noFail = "";
		String emelUser = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT A.NO_FAIL, A.ID_MASUK, C.USER_NAME, D.EMEL FROM TBLPFDFAIL A, TBLPERMOHONAN B, USERS C, USERS_INTERNAL D "
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_MASUK = C.USER_ID "
				+ " AND C.USER_ID = D.USER_ID AND B.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				emelUser = rsEmel.getString("EMEL");
				noFail = rsEmel.getString("NO_FAIL");
			}	
						
			email.RECIEPIENT = emelUser;
			email.SUBJECT = "KEPUTUSAN NILAIAN HARTA URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			email.MESSAGE = "Keputusan nilaian bagi permohonan tersebut telah dibuat<br><br>"
							 + "Mohon semakan daripada pihak tuan "
							 + " <br><br>Sekian, terima kasih.<br>";
			email.sendEmail();
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanKertasCadangan() {
		return beanKertasCadangan;
	}

	public void setBeanKertasCadangan(Vector beanKertasCadangan) {
		this.beanKertasCadangan = beanKertasCadangan;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatKJT() {
		return beanMaklumatKJT;
	}

	public void setBeanMaklumatKJT(Vector beanMaklumatKJT) {
		this.beanMaklumatKJT = beanMaklumatKJT;
	}

	public Vector getListKJT() {
		return listKJT;
	}

	public void setListKJT(Vector listKJT) {
		this.listKJT = listKJT;
	}

	public Vector getListKJP() {
		return listKJP;
	}

	public void setListKJP(Vector listKJP) {
		this.listKJP = listKJP;
	}

	public Vector getBeanMaklumatKJP() {
		return beanMaklumatKJP;
	}

	public void setBeanMaklumatKJP(Vector beanMaklumatKJP) {
		this.beanMaklumatKJP = beanMaklumatKJP;
	}
	
	public Vector getBeanMaklumatUlasanKJP() {
		return beanMaklumatUlasanKJP;
	}

	public void setBeanMaklumatUlasanKJP(Vector beanMaklumatUlasanKJP) {
		this.beanMaklumatUlasanKJP = beanMaklumatUlasanKJP;
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

	public Vector getListJPPH() {
		return listJPPH;
	}

	public void setListJPPH(Vector listJPPH) {
		this.listJPPH = listJPPH;
	}

	public Vector getBeanMaklumatJPPH() {
		return beanMaklumatJPPH;
	}

	public void setBeanMaklumatJPPH(Vector beanMaklumatJPPH) {
		this.beanMaklumatJPPH = beanMaklumatJPPH;
	}
	public Vector getBeanMaklumatKewangan() {
		return beanMaklumatKewangan;
	}

	public void setBeanMaklumatKewangan(Vector beanMaklumatKewangan) {
		this.beanMaklumatKewangan = beanMaklumatKewangan;
	}
}
