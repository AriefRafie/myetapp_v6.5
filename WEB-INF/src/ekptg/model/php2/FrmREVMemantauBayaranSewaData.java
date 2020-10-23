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

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.view.php2.util.UtilHasil;

/**
 *
 *
 */
public class FrmREVMemantauBayaranSewaData {

	protected Db db;
	private Vector senaraiFail = null;
	private Vector beanMaklumatPemohon = null;
	private Vector senaraiDeposit = null;
	private Vector senaraiAkaun = null;
	private Vector senaraiAkaunLL = null;
	private Vector beanMaklumatBayaran = null;
	private Vector beanMaklumatPelarasan = null;
	private Vector senaraiPerjanjian = null;
	private Vector senaraiPerjanjianTambahan = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatBayaranLL = null;
	private static final Log log = LogFactory
			.getLog(FrmREVMemantauBayaranSewaData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String namaPemohon, String noRujukan,
			String idBank, String noCek, String noResit, String idJenisFail, String idStatusPerjanjian, String tujuan,
			String idNegeri, String idDaerah, String idMukim, String idJenisHakmilik,
			String noHakmilik, String noWarta, String idLot, String noLot,
			String peganganHakmilik, String idKementerian, String idAgensi)
			throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL, TBLPHPPEMOHON.NAMA, TBLPHPBAYARANPERLUDIBAYAR.NO_RUJUKAN, TBLPHPBAYARANPERLUDIBAYAR.BAYARAN,"
					+ " TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT, TBLPHPHASIL.MAKLUMAT_LOT,"
					+ " TBLPHPHASIL.ID_HASIL, TBLPFDFAIL.ID_FAIL, TBLPFDFAIL.ID_SUBURUSAN, TBLPHPHASIL.FLAG_TUNGGAKAN, TBLPHPHASIL.FLAG_TUNGGAKAND,"
					+ " TBLPHPBAYARANPERLUDIBAYAR.STATUS AS STATUS_PERJANJIAN, TBLPHPHASIL.NILAI_TUNGGAKAN, TBLPHPHASIL.TUJUAN"

					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLPFDFAIL TBLPFDFAILPERMOHONAN, TBLPERMOHONAN, TBLPHPHAKMILIKPERMOHONAN,"
					+ " TBLPHPHAKMILIK, TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
					+ " AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON(+)"
					+ " AND TBLPHPHASIL.ID_FAILPERMOHONAN = TBLPFDFAILPERMOHONAN.ID_FAIL(+)"
					+ " AND TBLPFDFAILPERMOHONAN.ID_FAIL = TBLPERMOHONAN.ID_PERMOHONAN(+)"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN(+)"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
					+ " AND TBLPFDFAIL.ID_URUSAN = 115"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK(+) = 'U'";

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

			// no rujukan
			if (noRujukan != null) {
				if (!noRujukan.trim().equals("")) {
					sql = sql
							+ " AND UPPER(TBLPHPBAYARANPERLUDIBAYAR.NO_RUJUKAN) LIKE '%' ||'"
							+ noRujukan.trim().toUpperCase() + "'|| '%'";
				}
			}

			if (idBank != null || noCek != null || noResit != null) {
				if (!noCek.trim().equals("") || (!idBank.trim().equals("") && !idBank.trim().equals("99999")) || !noResit.trim().equals("")) {
					String sqlIn = getIdHasilByParams(noCek, idBank, noResit);
					sql = sql + " AND TBLPHPHASIL.ID_HASIL IN (" + sqlIn + ")";
				}
			}

			if (idJenisFail != null) {
				// DEPOSIT TIDAK DITUNTUT / DEPOSIT MASIH ADA BAGI PERJANJIAN YANG TELAH TAMAT
				if (idJenisFail.trim().equals("D")) {
					sql = sql + " AND TBLPHPHASIL.FLAG_TUNGGAKAND = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.STATUS = '2'";
				}

				// SEWA TERTUNGGAK
				if (idJenisFail.trim().equals("S")) {
					sql = sql + " AND TBLPHPHASIL.FLAG_TUNGGAKAN = 'Y'";
				}
			}

			// idStatusPerjanjian
			if (idStatusPerjanjian != null) {
				if (!idStatusPerjanjian.trim().equals("")
						&& !idStatusPerjanjian.trim().equals("99999")) {
					sql = sql + " AND TBLPHPBAYARANPERLUDIBAYAR.STATUS = '"
							+ idStatusPerjanjian.trim() + "'";
				}
			}

			// CARIAN BY HAKMILIK - PEJE 19042017
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

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_KEMENTERIAN = '"
							+ idKementerian.trim() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_AGENSI = '"
							+ idAgensi.trim() + "'";
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

			//tujuanPenyewaan
			if (tujuan != null) {
				if (!tujuan.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHASIL.TUJUAN) LIKE '%' ||'"
							+ tujuan.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC NULLS LAST";
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
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				if (rs.getString("ID_SUBURUSAN") != null
						&& rs.getString("ID_SUBURUSAN").equals("35")) {
					h.put("jenisPenyewaan", "BANGUNAN");
				} else {
					h.put("jenisPenyewaan", "TANAH");
				}
				h.put("flagStatusPerjanjian", rs.getString("STATUS_PERJANJIAN"));
				if (rs.getString("STATUS_PERJANJIAN") != null && rs.getString("STATUS_PERJANJIAN").equals("1")) {
					h.put("statusPerjanjian", "AKTIF");
				} else if (rs.getString("STATUS_PERJANJIAN") != null && rs.getString("STATUS_PERJANJIAN").equals("2")) {
					h.put("statusPerjanjian", "TAMAT");
				} else if (rs.getString("STATUS_PERJANJIAN") != null && rs.getString("STATUS_PERJANJIAN").equals("3")) {
					h.put("statusPerjanjian", "DITAMATKAN");
				} else {
					h.put("statusPerjanjian", "");
				}

				String statusDeposit = "";
				String status = "";
				int bilHari = 0;

				if (rs.getDate("TARIKH_TAMAT") != null && rs.getDate("TARIKH_TAMAT").toString().length() > 0) {
					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT")));
					calTamat.setTime(dateTamat);

					bilHari = daysBetween(calTamat.getTime(), calCurrent.getTime());

					if (calCurrent.getTime().after(calTamat.getTime())) {
						if (rs.getString("FLAG_TUNGGAKAND") != null && rs.getString("FLAG_TUNGGAKAND").equals("Y")) {
							statusDeposit = "DEPOSIT TIDAK DITUNTUT";
						}
					} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 90) {
						status = bilHari + " HARI LAGI";
					}
					h.put("status", status);
					h.put("statusDeposit", statusDeposit);

				} else {
					h.put("status", "");
					h.put("statusDeposit", "");
				}

				if (rs.getString("FLAG_TUNGGAKAN") != null && rs.getString("FLAG_TUNGGAKAN").equals("Y")) {
					h.put("statusTunggakan", "TERTUNGGAK");
					if (rs.getDouble("NILAI_TUNGGAKAN") == 0D) {
						h.put("tunggakan", "0.00");
					} else {
						h.put("tunggakan", Util.formatDecimal(rs.getDouble("NILAI_TUNGGAKAN")));
					}
				} else {
					h.put("statusTunggakan", "");
					if (rs.getDouble("NILAI_TUNGGAKAN") == 0D) {
						h.put("tunggakan", "0.00");
					} else {
						h.put("tunggakan", "(" + Util.formatDecimal(rs.getDouble("NILAI_TUNGGAKAN")) + ")");
					}
				}
				h.put("kadarSewa", Util.formatDecimal(rs.getDouble("BAYARAN")));
				h.put("maklumatLot",
						rs.getString("MAKLUMAT_LOT") == null ? "" : rs
								.getString("MAKLUMAT_LOT"));
				h.put("tujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));

				senaraiFail.addElement(h);
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

	public static String getIdHasilByParams(String noCek, String idBank, String noResit)
			throws Exception {

		String sql = "(SELECT TBLPHPAKAUN.ID_HASIL FROM TBLPHPAKAUN WHERE TBLPHPAKAUN.ID_HASIL IS NOT NULL";
		if (noCek != null) {
			if (!noCek.trim().equals("")) {
				sql = sql + " AND UPPER(TBLPHPAKAUN.NO_RUJUKAN) LIKE '%"
						+ noCek.trim().toUpperCase() + "%'";
			}
		}

		if (idBank != null) {
			if (!idBank.trim().equals("") && !idBank.trim().equals("99999")) {
				sql = sql + " AND TBLPHPAKAUN.ID_BANK = " + idBank;
			}
		}

		if (noResit != null) {
			if (!noResit.trim().equals("")) {
				sql = sql + " AND UPPER(TBLPHPAKAUN.NO_RESIT) LIKE '%"
						+ noResit.trim().toUpperCase() + "%'";
			}
		}

		sql = sql + ")";
		return sql;
	}

	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	@SuppressWarnings("unchecked")
	public void setListAkaun(String idHasil) throws Exception {

		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiAkaun = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'";

			sql = sql + " ORDER BY TARIKH, ID_JENISTRANSAKSI, ID_AKAUN ASC";
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
				h.put("tarikhCek",
						rs.getDate("TARIKH_CEK") == null ? "" : sdf.format(rs
								.getDate("TARIKH_CEK")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("debit",
						rs.getString("DEBIT") == null || rs.getString("DEBIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null || rs.getString("KREDIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				Double baki = getTotalBaki(temp, db1);
				if (baki > 0D) {
					h.put("baki", Util.formatDecimal(baki));
				} else if (baki < 0D) {
					baki = baki * -1;
					h.put("baki", "(" + Util.formatDecimal(baki) + ")");
				} else {
					h.put("baki", "0.00");
				}

				senaraiAkaun.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
			if (db1 != null)
				db1.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void setListAkaunLL(String idHasil) throws Exception {

		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiAkaunLL = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 37 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'";

			sql = sql + " ORDER BY TARIKH, ID_JENISTRANSAKSI, ID_AKAUN ASC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idAkaun", rs.getString("ID_AKAUN") == null ? "" : rs
						.getString("ID_AKAUN"));
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
				h.put("tarikhCek",
						rs.getDate("TARIKH_CEK") == null ? "" : sdf.format(rs
								.getDate("TARIKH_CEK")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("debit",
						rs.getString("DEBIT") == null || rs.getString("DEBIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null || rs.getString("KREDIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				senaraiAkaunLL.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
			if (db1 != null)
				db1.close();
		}
	}

	private Double getTotalBaki(String temp, Db db) throws Exception {
		String sql = "";
		Double baki = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp
					+ ")";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_AKAUN IN (" + temp
					+ ")";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
			}

			baki = totalDebit - totalKredit;

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {

		}
		return baki;
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
			sql = "SELECT SUM(DEBIT) AS DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			if (rsDebit.next()) {
				totalDebit = Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT SUM(KREDIT) AS KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			if (rsKredit.next()) {
				totalKredit = Double.valueOf(rsKredit.getDouble("KREDIT"));
			}

			total = totalDebit - totalKredit;

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return total;
	}

	public Double calculateTotalLL(String idHasil) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// KREDIT
			sql = "SELECT SUM(KREDIT) AS KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 37 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			if (rsKredit.next()) {
				total = Double.valueOf(rsKredit.getDouble("KREDIT"));
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return total;
	}

	public String simpanBayaranD(String idHasil, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String idStatusDeposit, String noMel,
			String modBayaran, HttpSession session) throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "9");
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_BANK", idBank);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN
			r.add("FLAG_DEPOSIT", idStatusDeposit);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idHasil
							+ "] DIDAFTARKAN");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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

	public void simpanKemaskiniBayaranD(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran, String idStatusDeposit,
			String noMel, String modBayaran, HttpSession session)
			throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_BANK", idBank);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}

			r.add("FLAG_DEPOSIT", idStatusDeposit);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
							+ "] DIKEMASKINI");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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

	public void hapusBayaranD(String idHasil, String idAkaun,
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

			// TBLPHPAKAUN
			r.update("ID_AKAUN", idAkaun);
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idHasil
							+ "] DIHAPUSKAN");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String noMel, String modBayaran, HttpSession session)
			throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "10");
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
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

			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idHasil
							+ "] DIDAFTARKAN");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	public String simpanBayaranLL(String idHasil, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String noMel, String modBayaran, String idKategoriBayaran, HttpSession session)
			throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "37");
			r.add("KREDIT", amaun);
			r.add("ID_CARABAYAR", idCaraBayaran);
			r.add("NO_RESIT", noResit);
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
			r.add("ID_KATEGORI_BAYARAN", idKategoriBayaran);
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

			AuditTrail.logActivity("", "4", null, session, "INS",
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

	public void simpanKemaskiniBayaran(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran, String noMel,
			String modBayaran, HttpSession session) throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
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
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
							+ "] DIKEMASKINI");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	public void simpanKemaskiniBayaranLL(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran, String noMel,
			String modBayaran, String idKategoriBayaran, HttpSession session) throws Exception {

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
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
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
			r.add("NO_DAFTAR_MEL", noMel);
			r.add("ID_MOD_BAYARAN", modBayaran);
			r.add("ID_KATEGORI_BAYARAN", idKategoriBayaran);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
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

	public void hapusBayaran(String idHasil, String idAkaun, HttpSession session)
			throws Exception {
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
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idHasil
							+ "] DIHAPUSKAN");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	public void hapusBayaranLL(String idHasil, String idAkaun,
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
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idHasil
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

	public String simpanPelarasanD(String idHasil, String tarikh,
			String idJenisPelarasan, String noRujukan, String amaun,
			String butiran, String tarikhCek, String idBank, String noResit,
			String tarikhResit, HttpSession session) throws Exception {

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
			r.add("ID_JENISBAYARAN", "9");
			if ("D".equals(idJenisPelarasan) || "R".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
			} else if ("K".equals(idJenisPelarasan)) {
				r.add("KREDIT", amaun);
			} else if ("C".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
				r.add("ID_BANK", idBank);
			}
			r.add("NO_RESIT", noResit);

			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
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

			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idHasil
							+ "] DIDAFTARKAN");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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

	public void simpanKemaskiniPelarasanD(String idHasil, String idAkaun,
			String tarikh, String idJenisPelarasan, String noRujukan,
			String amaun, String butiran, String tarikhCek, String idBank,
			String noResit, String tarikhResit, HttpSession session)
			throws Exception {

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
			if ("D".equals(idJenisPelarasan) || "R".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("KREDIT", "");
			} else if ("K".equals(idJenisPelarasan)) {
				r.add("DEBIT", "");
				r.add("KREDIT", amaun);
			} else if ("C".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
				r.add("ID_BANK", idBank);
			}
			r.add("NO_RESIT", noResit);

			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
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

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
							+ "] DIKEMASKINI");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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

	public void hapusPelarasanD(String idHasil, String idAkaun, HttpSession session)
			throws Exception {
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

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idHasil
							+ "] DIHAPUSKAN");

			UtilHasil.updateFlagTunggakanD(idHasil, db);

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
			String butiran, String tarikhCek, String idBank, String noResit,
			String tarikhResit, HttpSession session) throws Exception {

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
			r.add("ID_JENISBAYARAN", "10");
			if ("D".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
			} else if ("C".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
				r.add("ID_BANK", idBank);
			} else if ("K".equals(idJenisPelarasan)
					|| "R".equals(idJenisPelarasan)) {
				r.add("KREDIT", amaun);
			}
			r.add("ID_JENISPELARASAN", idJenisPelarasan);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}
			r.add("NO_RESIT", noResit);

			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISTRANSAKSI", "3"); // PELARASAN

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idHasil
							+ "] DIDAFTARKAN");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	public void simpanKemaskiniPelarasan(String idHasil, String idAkaun,
			String tarikh, String idJenisPelarasan, String noRujukan,
			String amaun, String butiran, String tarikhCek, String idBank,
			String noResit, String tarikhResit, HttpSession session)
			throws Exception {

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
			} else if ("C".equals(idJenisPelarasan)) {
				r.add("DEBIT", amaun);
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
				r.add("ID_BANK", idBank);
			} else if ("K".equals(idJenisPelarasan)
					|| "R".equals(idJenisPelarasan)) {
				r.add("DEBIT", "");
				r.add("KREDIT", amaun);
			}
			r.add("ID_JENISPELARASAN", idJenisPelarasan);
			if (!"".equals(butiran)) {
				r.add("CATATAN", butiran);
			}
			r.add("NO_RESIT", noResit);

			if (!"".equals(tarikhResit)) {
				r.add("TARIKH_RESIT", r.unquote("to_date('" + tarikhResit
						+ "','dd/MM/yyyy')"));
			}
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPAKAUN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
							+ "] DIKEMASKINI");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	public void hapusPelarasan(String idHasil, String idAkaun, HttpSession session) throws Exception {
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

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idHasil
							+ "] DIHAPUSKAN");

			UtilHasil.updateFlagTunggakan(idHasil, db);

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

	@SuppressWarnings("unchecked")
	public void setMaklumatBayaran(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";

		try {
			beanMaklumatBayaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, NO_RUJUKAN, TARIKH_CEK, KREDIT, ID_CARABAYAR, NO_RESIT, TARIKH_RESIT, ID_BANK, CATATAN, FLAG_DEPOSIT, NO_DAFTAR_MEL, ID_MOD_BAYARAN"
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
				h.put("tarikhCek", rs.getString("TARIKH_CEK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_CEK")));
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
						rs.getString("ID_BANK") == null ? "99999" : rs
								.getString("ID_BANK"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("flagDeposit", rs.getString("FLAG_DEPOSIT") == null ? ""
						: rs.getString("FLAG_DEPOSIT"));
				h.put("noMel",
						rs.getString("NO_DAFTAR_MEL") == null ? "" : rs
								.getString("NO_DAFTAR_MEL"));
				h.put("modBayaran", rs.getString("ID_MOD_BAYARAN") == null ? ""
						: rs.getString("ID_MOD_BAYARAN"));

				beanMaklumatBayaran.addElement(h);
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

	@SuppressWarnings("unchecked")
	public void setMaklumatBayaranLL(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";

		try {
			beanMaklumatBayaranLL = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, NO_RUJUKAN, TARIKH_CEK, KREDIT, ID_CARABAYAR, NO_RESIT, TARIKH_RESIT, ID_BANK, CATATAN, FLAG_DEPOSIT, NO_DAFTAR_MEL, ID_MOD_BAYARAN"
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
				h.put("tarikhCek", rs.getString("TARIKH_CEK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_CEK")));
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
						rs.getString("ID_BANK") == null ? "99999" : rs
								.getString("ID_BANK"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("flagDeposit", rs.getString("FLAG_DEPOSIT") == null ? ""
						: rs.getString("FLAG_DEPOSIT"));
				h.put("noMel",
						rs.getString("NO_DAFTAR_MEL") == null ? "" : rs
								.getString("NO_DAFTAR_MEL"));
				h.put("modBayaran", rs.getString("ID_MOD_BAYARAN") == null ? ""
						: rs.getString("ID_MOD_BAYARAN"));

				beanMaklumatBayaranLL.addElement(h);
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

	@SuppressWarnings("unchecked")
	public void setMaklumatPelarasan(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";
		String idJenisPelarasan = "99999";
		String idJenisBayaran = "";

		try {
			beanMaklumatPelarasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, ID_JENISPELARASAN, KREDIT, DEBIT, NO_RUJUKAN, CATATAN, TARIKH_CEK, ID_BANK, NO_RESIT, TARIKH_RESIT, ID_JENISBAYARAN FROM TBLPHPAKAUN WHERE ID_AKAUN = '"
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
				idJenisBayaran = rs.getString("ID_JENISBAYARAN");
				h.put("idJenisPelarasan", idJenisPelarasan);
				h.put("tarikh",
						rs.getString("TARIKH") == null ? "" : sdf.format(rs
								.getDate("TARIKH")));
				h.put("tarikhCek", rs.getString("TARIKH_CEK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_CEK")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("noResit",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("tarikhResit", rs.getString("TARIKH_RESIT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_RESIT")));
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
				} else if ("C".equals(idJenisPelarasan)) {
					h.put("amaun",
							rs.getString("DEBIT") == null ? "" : Util
									.formatDecimal(Double.valueOf(rs
											.getString("DEBIT"))));
					h.put("idBank", rs.getString("ID_BANK") == null ? "99999"
							: rs.getString("ID_BANK"));
				} else if ("R".equals(idJenisPelarasan)) {
					if ("9".equals(idJenisBayaran)) {
						h.put("amaun",
								rs.getString("DEBIT") == null ? "" : Util
										.formatDecimal(Double.valueOf(rs
												.getString("DEBIT"))));
					} else if ("10".equals(idJenisBayaran)) {
						h.put("amaun",
								rs.getString("KREDIT") == null ? "" : Util
										.formatDecimal(Double.valueOf(rs
												.getString("KREDIT"))));
					}
				}

				else {
					h.put("amaun", "");
				}

				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				beanMaklumatPelarasan.addElement(h);
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

	@SuppressWarnings("unchecked")
	public void setListDeposit(String idHasil) throws Exception {

		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiDeposit = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 9 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'";

			sql = sql + " ORDER BY TARIKH, ID_JENISTRANSAKSI, ID_AKAUN ASC";
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
				h.put("tarikhCek",
						rs.getDate("TARIKH_CEK") == null ? "" : sdf.format(rs
								.getDate("TARIKH_CEK")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("butiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("debit",
						rs.getString("DEBIT") == null || rs.getString("DEBIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null || rs.getString("KREDIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				Double baki = getTotalBaki(temp, db1);
				if (baki > 0D) {
					h.put("baki", Util.formatDecimal(baki));
				} else if (baki < 0D) {
					baki = baki * -1;
					h.put("baki", "(" + Util.formatDecimal(baki) + ")");
				} else {
					h.put("baki", "0.00");
				}

				senaraiDeposit.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
			if (db1 != null)
				db1.close();
		}
	}

	public Double calculateTotalDeposit(String idHasil) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT SUM(DEBIT) AS DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 9 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			if (rsDebit.next()) {
				totalDebit = Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT SUM(KREDIT) AS KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 9 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '"
					+ idHasil + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			if (rsKredit.next()) {
				totalKredit = Double.valueOf(rsKredit.getDouble("KREDIT"));
			}

			total = totalDebit - totalKredit;

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return total;
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
					+ " TBLPHPPEMOHON.ID_NEGERITETAP, TBLPHPPEMOHON.NO_TEL, TBLPHPPEMOHON.NO_FAX, TBLPHPPEMOHON.EMEL, TBLPHPPEMOHON.NO_RUJUKAN,"
					+ " TBLPHPPEMOHON.CATATAN"
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
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				beanMaklumatPemohon.addElement(h);
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

	public void updatePemohon(String idPemohon, String txtNama,
			String txtNoPendaftaran, String txtAlamat1, String txtAlamat2,
			String txtAlamat3, String txtPoskod, String idBandar,
			String idNegeri, String txtEmel, String txtNoTel, String txtNoFaks,
			String txtNoRujukan, String txtCatatan, HttpSession session) throws Exception {

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
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
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

	public void updatePermohonan(String idFail, String idHasil, String noFail,
			String idUrusan, String idSuburusan, String perkara, String tujuan,
			String catatan, HttpSession session) throws Exception {

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

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("TAJUK_FAIL", perkara);
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFail);
			r.add("CATATAN", catatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPHASIL
			r = new SQLRenderer();
			r.update("ID_HASIL", idHasil);
			r.add("TUJUAN", tujuan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPHASIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idFail
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

	@SuppressWarnings("unchecked")
	public void setListPerjanjian(String idHasil) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPBAYARANPERLUDIBAYAR WHERE ID_HASIL = '"
					+ idHasil
					+ "' AND (FLAG_PERJANJIAN = 'U' OR FLAG_PERJANJIAN IS NULL) ORDER BY TARIKH_MULA ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBayaranPerluDibayar",
						rs.getString("ID_BAYARANPERLUDIBAYAR") == null ? ""
								: rs.getString("ID_BAYARANPERLUDIBAYAR"));
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("noSiri",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("deposit",
						rs.getString("DEPOSIT") == null || rs.getString("DEPOSIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEPOSIT"))));
				h.put("sewa",
						rs.getString("BAYARAN") == null || rs.getString("BAYARAN").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("BAYARAN"))));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				senaraiPerjanjian.addElement(h);
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

	@SuppressWarnings("unchecked")
	public void setListPerjanjianTambahan(String idHasil) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiPerjanjianTambahan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPBAYARANPERLUDIBAYAR WHERE ID_HASIL = '"
					+ idHasil
					+ "' AND FLAG_PERJANJIAN != 'U' ORDER BY TARIKH_MULA ASC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBayaranPerluDibayar",
						rs.getString("ID_BAYARANPERLUDIBAYAR") == null ? ""
								: rs.getString("ID_BAYARANPERLUDIBAYAR"));
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("flagPerjanjian",
						rs.getString("FLAG_PERJANJIAN") == null ? "" : rs
								.getString("FLAG_PERJANJIAN"));

				String keteranganFlagPerjanjian = "";
				if ("1".equals(rs.getString("FLAG_PERJANJIAN"))) {
					keteranganFlagPerjanjian = "PENGURANGAN KADAR SEWA";
				} else if ("2".equals(rs.getString("FLAG_PERJANJIAN"))) {
					keteranganFlagPerjanjian = "PENAMBAHAN KADAR SEWA";
				} else if ("3".equals(rs.getString("FLAG_PERJANJIAN"))) {
					keteranganFlagPerjanjian = "PENGECUALIAN KADAR SEWA";
				}
				h.put("keteranganFlagPerjanjian",keteranganFlagPerjanjian);

				h.put("noSiri",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("deposit",
						rs.getString("DEPOSIT") == null || rs.getString("DEPOSIT").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEPOSIT"))));
				h.put("sewa",
						rs.getString("BAYARAN") == null || rs.getString("BAYARAN").equals("0") ? "0.00" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("BAYARAN"))));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				senaraiPerjanjianTambahan.addElement(h);
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

	public void setMaklumatPermohonan(String idHasil) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, HASIL.ID_HASIL, FAIL.NO_FAIL, SUBURUSAN.ID_URUSAN, FAIL.ID_SUBURUSAN,"
					+ " FAIL.TAJUK_FAIL, HASIL.TUJUAN, FAIL.CATATAN"
					+ " FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL, TBLRUJSUBURUSAN SUBURUSAN"
					+ " WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND FAIL.ID_SUBURUSAN = SUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND HASIL.ID_HASIL = '" + idHasil + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("noFail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "99999"
						: rs.getString("ID_URUSAN"));
				h.put("idSuburusan",
						rs.getString("ID_SUBURUSAN") == null ? "99999" : rs
								.getString("ID_SUBURUSAN"));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());

				beanMaklumatPermohonan.addElement(h);
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

	public void setMaklumatTanah(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FAIL.ID_NEGERI, FAIL.ID_KEMENTERIAN, HASIL.ID_AGENSI, HASIL.MAKLUMAT_LOT, HASIL.ID_LUAS, HASIL.LUAS, HASIL.CATATAN_TANAH"
					+ " FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL, TBLPHPPEMOHON PEMOHON"
					+ " WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND HASIL.ID_PEMOHON = PEMOHON.ID_PEMOHON AND FAIL.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999"
						: rs.getString("ID_NEGERI"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "99999" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "99999"
						: rs.getString("ID_AGENSI"));
				h.put("maklumatLot", rs.getString("MAKLUMAT_LOT") == null ? ""
						: rs.getString("MAKLUMAT_LOT"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				h.put("luas",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				h.put("catatanTanah",
						rs.getString("CATATAN_TANAH") == null ? "" : rs
								.getString("CATATAN_TANAH"));

				beanMaklumatTanah.addElement(h);
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

	public void updateTanah(String idFail, String idHasil,
			String idNegeriTanah, String idKementerian, String idAgensi,
			String maklumatLot, String idLuas, String luas, String catatan, HttpSession session)
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

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("ID_NEGERI", idNegeriTanah);
			r.add("ID_KEMENTERIAN", idKementerian);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPHASIL
			r = new SQLRenderer();
			r.update("ID_HASIL", idHasil);
			r.add("ID_AGENSI", idAgensi);
			r.add("MAKLUMAT_LOT", maklumatLot);
			r.add("ID_LUAS", idLuas);
			r.add("LUAS", luas);
			r.add("CATATAN_TANAH", catatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPHASIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idFail
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

	public void hapusFail(String idFail, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPAKAUN
			sql1 = "DELETE FROM TBLPHPAKAUN WHERE ID_HASIL IN (SELECT ID_HASIL FROM TBLPHPHASIL WHERE ID_FAIL = "
					+ idFail + ")";
			sql2 = "DELETE FROM TBLPHPBAYARANPERLUDIBAYAR WHERE ID_HASIL IN (SELECT ID_HASIL FROM TBLPHPHASIL WHERE ID_FAIL = "
					+ idFail + ")";
			sql3 = "DELETE FROM TBLPHPHASIL WHERE ID_FAIL = " + idFail;
			sql4 = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL = " + idFail;
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.executeBatch();

			conn.commit();

			AuditTrail.logActivity("999", "4", null, session, "UPD",
					"FAIL [" + idFail
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

	public Hashtable getMaklumatABT(String idHasil) {
		String sql = "";
		Hashtable abt = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPHASIL.CATATAN_ABT, TO_CHAR (SUM (NVL (DEBIT, 0)), '999,999,990.99') AS JUMLAH_SEWA, TO_CHAR (SUM (NVL (KREDIT, 0)), '999,999,990.99') AS JUMLAH_BAYARAN,"
					+ " TO_CHAR ((SUM (NVL (DEBIT, 0)) - SUM (NVL (KREDIT, 0))),'999,999,990.99') AS TUNGGAKAN,"
					+ " TO_NUMBER (CEIL (  (SUM (NVL (DEBIT, 0)) - SUM (NVL (KREDIT, 0))) / TBLPHPBAYARANPERLUDIBAYAR.BAYARAN),'999,999,990.99') AS BULAN"
					+ " FROM TBLPHPAKAUN AKAUN, TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLRUJURUSAN, TBLRUJSUBURUSAN, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+) AND AKAUN.ID_HASIL = TBLPHPHASIL.ID_HASIL AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON(+)"
					+ " AND TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN(+) AND TBLRUJSUBURUSAN.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.BAYARAN > 0 AND AKAUN.ID_JENISBAYARAN = 10"
					+ " AND AKAUN.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.BAYARAN > 0"
					+ " AND TBLPHPHASIL.ID_HASIL = '" + idHasil + "'"
					+ " GROUP BY TBLPHPHASIL.CATATAN_ABT, TBLPFDFAIL.NO_FAIL, TBLPHPPEMOHON.NAMA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT,"
					+ " TBLPHPBAYARANPERLUDIBAYAR.BAYARAN";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				abt = new Hashtable();
				abt.put("catatanABT", rs.getString("CATATAN_ABT") == null ? "" : rs.getString("CATATAN_ABT"));
				abt.put("jumlahSewa", rs.getString("JUMLAH_SEWA") == null ? "" : rs.getString("JUMLAH_SEWA"));
				abt.put("jumlahBayaran", rs.getString("JUMLAH_BAYARAN") == null ? "" : rs.getString("JUMLAH_BAYARAN"));
				if (Double.valueOf(Utils.RemoveComma(rs.getString("TUNGGAKAN"))) >= 0) {
					abt.put("tunggakan", rs.getString("TUNGGAKAN") == null ? "" : rs.getString("TUNGGAKAN"));
					abt.put("bulan", rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
				} else {
					abt.put("tunggakan", rs.getString("TUNGGAKAN") == null ? "" : "(" +  Util.formatDecimal((Double.valueOf(Utils.RemoveComma(rs.getString("TUNGGAKAN"))) * -1)) + ")");
					abt.put("bulan", "0");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return abt;
	}

	public void kemaskiniABT(String idHasil, String catatan, HttpSession session) throws Exception {

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

			// TBLPHPHASIL
			r = new SQLRenderer();
			r.update("ID_HASIL", idHasil);
			r.add("CATATAN_ABT", catatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPHASIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "UPD",
					"FAIL [" + idHasil
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

	public Vector getSenaraiNotis(String idHasil) {
		String sql = "";
		Vector listNotis = null;
		Hashtable h;

		try {
			listNotis = new Vector();

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPNOTISHASIL WHERE ID_HASIL = '" + idHasil + "'  AND FLAG_NOTIS IS NULL ORDER BY TARIKH_NOTIS ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idNotis", rs.getString("ID_NOTIS") == null ? "" : rs.getString("ID_NOTIS"));
				h.put("idJenisNotis", rs.getString("ID_JENIS_NOTIS") == null ? "" : rs.getString("ID_JENIS_NOTIS"));
				if ("1".equals(rs.getString("ID_JENIS_NOTIS"))) {
					h.put("jenisNotis", "NOTIS TUNTUTAN TUNGGAKAN");
				} else if ("2".equals(rs.getString("ID_JENIS_NOTIS"))) {
					h.put("jenisNotis", "NOTIS RAMPASAN DEPOSIT");
				} else {
					h.put("jenisNotis", "");
				}
				h.put("tarikhNotis", rs.getDate("TARIKH_NOTIS") == null ? "" : sdf.format(rs.getDate("TARIKH_NOTIS")));
				h.put("tarikhAkhirNotis", rs.getDate("TARIKH_AKHIR_NOTIS") == null ? "" : sdf.format(rs.getDate("TARIKH_AKHIR_NOTIS")));
				h.put("kadarSewa", rs.getString("KADAR_SEWA") == null || rs.getString("KADAR_SEWA").equals("0") ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("KADAR_SEWA"))));
				h.put("bulanTunggakan", rs.getString("BULAN_TUNGGAKAN") == null ? "" : rs.getString("BULAN_TUNGGAKAN"));
				h.put("jumlahTunggakan",rs.getString("JUMLAH_TUNGGAKAN") == null || rs.getString("JUMLAH_TUNGGAKAN").equals("0") ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("JUMLAH_TUNGGAKAN"))));
				h.put("bilPeringatan", rs.getString("BIL_PERINGATAN") == null ? "" : rs.getString("BIL_PERINGATAN"));
				if ("1".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "PERTAMA");
				} else if ("2".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "KEDUA");
				} else if ("3".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "KETIGA");
				} else {
					h.put("peringatan", "");
				}

				listNotis.addElement(h);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return listNotis;
	}

	public Vector getSenaraiMemo(String idHasil) {
		String sql = "";
		Vector listNotis = null;
		Hashtable h;
		String memo = "MEMO";

		try {
			listNotis = new Vector();

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPNOTISHASIL WHERE ID_HASIL = '" + idHasil + "' AND FLAG_NOTIS = '" + memo + "' ORDER BY TARIKH_NOTIS ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idNotis", rs.getString("ID_NOTIS") == null ? "" : rs.getString("ID_NOTIS"));
				h.put("idJenisNotis", rs.getString("ID_JENIS_NOTIS") == null ? "" : rs.getString("ID_JENIS_NOTIS"));
				if ("1".equals(rs.getString("ID_JENIS_NOTIS"))) {
					h.put("jenisNotis", "NOTIS TUNTUTAN TUNGGAKAN");
				} else if ("2".equals(rs.getString("ID_JENIS_NOTIS"))) {
					h.put("jenisNotis", "NOTIS RAMPASAN DEPOSIT");
				} else {
					h.put("jenisNotis", "");
				}
				h.put("tarikhNotis", rs.getDate("TARIKH_NOTIS") == null ? "" : sdf.format(rs.getDate("TARIKH_NOTIS")));
				h.put("tarikhAkhirNotis", rs.getDate("TARIKH_AKHIR_NOTIS") == null ? "" : sdf.format(rs.getDate("TARIKH_AKHIR_NOTIS")));
				h.put("kadarSewa", rs.getString("KADAR_SEWA") == null || rs.getString("KADAR_SEWA").equals("0") ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("KADAR_SEWA"))));
				h.put("bulanTunggakan", rs.getString("BULAN_TUNGGAKAN") == null ? "" : rs.getString("BULAN_TUNGGAKAN"));
				h.put("jumlahTunggakan",rs.getString("JUMLAH_TUNGGAKAN") == null || rs.getString("JUMLAH_TUNGGAKAN").equals("0") ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("JUMLAH_TUNGGAKAN"))));
				h.put("bilPeringatan", rs.getString("BIL_PERINGATAN") == null ? "" : rs.getString("BIL_PERINGATAN"));
				if ("1".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "PERTAMA");
				} else if ("2".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "KEDUA");
				} else if ("3".equals(rs.getString("BIL_PERINGATAN"))) {
					h.put("peringatan", "KETIGA");
				} else {
					h.put("peringatan", "");
				}

				listNotis.addElement(h);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return listNotis;
	}

	public void hapusNotis(String idNotis, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPNOTISHASIL
			r = new SQLRenderer();
			r.add("ID_NOTIS", idNotis);

			sql = r.getSQLDelete("TBLPHPNOTISHASIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("", "4", null, session, "DEL",
					"FAIL [" + idNotis
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

	public Vector getSenaraiDeposit() {
		return senaraiDeposit;
	}

	public void setSenaraiDeposit(Vector senaraiDeposit) {
		this.senaraiDeposit = senaraiDeposit;
	}

	public Vector getSenaraiAkaun() {
		return senaraiAkaun;
	}

	public void setSenaraiAkaun(Vector senaraiAkaun) {
		this.senaraiAkaun = senaraiAkaun;
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

	public Vector getSenaraiPerjanjian() {
		return senaraiPerjanjian;
	}

	public void setSenaraiPerjanjian(Vector senaraiPerjanjian) {
		this.senaraiPerjanjian = senaraiPerjanjian;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getSenaraiPerjanjianTambahan() {
		return senaraiPerjanjianTambahan;
	}

	public void setSenaraiPerjanjianTambahan(Vector senaraiPerjanjianTambahan) {
		this.senaraiPerjanjianTambahan = senaraiPerjanjianTambahan;
	}

	public Vector getSenaraiAkaunLL() {
		return senaraiAkaunLL;
	}

	public void setSenaraiAkaunLL(Vector senaraiAkaunLL) {
		this.senaraiAkaunLL = senaraiAkaunLL;
	}

	public Vector getBeanMaklumatBayaranLL() {
		return beanMaklumatBayaranLL;
	}

	public void setBeanMaklumatBayaranLL(Vector beanMaklumatBayaranLL) {
		this.beanMaklumatBayaranLL = beanMaklumatBayaranLL;
	}
}
