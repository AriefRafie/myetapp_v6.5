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
import lebah.util.Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

/**
 * 
 *
 */
public class FrmREVMemantauBayaranAPBData {

	protected Db db;
	private Vector beanMaklumatBayaran = null;
	private static final Log log = LogFactory.getLog(FrmREVMemantauBayaranAPBData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector getSenaraiFail(String noFail, String namaPemohon, String noPengenalan, String noLesen, String idBank, String noCek, String noResit) throws Exception {
		
		Vector senaraiFail = null;
		Db db = null;		
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS, F.ID_PHPPMOHONNJDUALPERTAMA,"
					+ " E.TARIKH_MULA_LESEN, E.TARIKH_TAMAT_LESEN, E.NO_LESEN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLPHPPMOHONNJDUALPERTAMA F"
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND E.FLAG_AKTIF(+) = 'Y' AND A.NO_FAIL IS NOT NULL AND B.ID_PERMOHONAN = F.ID_PERMOHONAN";
			
			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// noPengenalan
			if (noPengenalan != null) {
				if (!noPengenalan.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_PENGENALAN) LIKE '%' ||'"
							+ noPengenalan.trim().toUpperCase() + "'|| '%'";
				}
			}			

			// noLesen
			if (noLesen != null) {
				if (!noLesen.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_LESEN) LIKE '%' ||'"
							+ noLesen.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if (idBank != null || noCek != null || noResit != null) {
				if (!noCek.trim().equals("") || (!idBank.trim().equals("") && !idBank.trim().equals("99999")) || !noResit.trim().equals("")) {
					String sqlIn = getIdJadualPertamaByParams(noCek, idBank, noResit);
					sql = sql + " AND F.ID_PHPPMOHONNJDUALPERTAMA IN (" + sqlIn + ")";
				}
			}

			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

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
				h.put("idJadualPertama",
						rs.getString("ID_PHPPMOHONNJDUALPERTAMA") == null ? "" : rs
								.getString("ID_PHPPMOHONNJDUALPERTAMA"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("tarikhPermohonan", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("noLesen",
						rs.getString("NO_LESEN") == null ? "" : rs
								.getString("NO_LESEN"));
				
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;		
	}
	
	public static String getIdJadualPertamaByParams(String noCek, String idBank, String noResit)
			throws Exception {

		String sql = "(SELECT TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA FROM TBLPHPAKAUN WHERE TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA IS NOT NULL";
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
	
	@SuppressWarnings("unchecked")
	public Vector getSenaraiDeposit(String idJadualPertama) throws Exception {
		Vector senaraiDeposit = null;
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

			sql = "SELECT TBLPHPAKAUN.ID_AKAUN, TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.DEBIT, TBLPHPAKAUN.CATATAN, TBLPHPAKAUN.KREDIT, TBLPHPAKAUN.NO_RUJUKAN,"
					+ " TBLPHPAKAUN.TARIKH_CEK, TBLPHPAKAUN.TARIKH_RESIT, TBLPHPAKAUN.NO_RESIT, TBLPHPAKAUN.ID_JENISTRANSAKSI, TBLRUJJENISBAYARAN.KETERANGAN AS JENIS_BAYARAN"
					+ " FROM TBLPHPAKAUN, TBLRUJJENISBAYARAN WHERE TBLPHPAKAUN.ID_JENISBAYARAN = TBLRUJJENISBAYARAN.ID_JENISBAYARAN"
					+ " AND TBLPHPAKAUN.ID_JENISBAYARAN IN (13) AND TBLPHPAKAUN.FLAG_AKTIF = 'Y' AND TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA = '" + idJadualPertama + "'";

			sql = sql + " ORDER BY TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.ID_AKAUN ASC";
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
				h.put("jenisBayaran",
						rs.getString("JENIS_BAYARAN") == null ? "" : rs
								.getString("JENIS_BAYARAN"));
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
		return senaraiDeposit;
	}
	
	public Double calculateTotalDeposit(String idJadualPertama) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (13) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (13) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
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
	
	@SuppressWarnings("unchecked")
	public Vector getSenaraiFiPermohonan(String idJadualPertama) throws Exception {
		Vector senaraiFiPermohonan = null;
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiFiPermohonan = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPAKAUN.ID_AKAUN, TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.DEBIT, TBLPHPAKAUN.CATATAN, TBLPHPAKAUN.KREDIT, TBLPHPAKAUN.NO_RUJUKAN,"
					+ " TBLPHPAKAUN.TARIKH_CEK, TBLPHPAKAUN.TARIKH_RESIT, TBLPHPAKAUN.NO_RESIT, TBLPHPAKAUN.ID_JENISTRANSAKSI, TBLRUJJENISBAYARAN.KETERANGAN AS JENIS_BAYARAN"
					+ " FROM TBLPHPAKAUN, TBLRUJJENISBAYARAN WHERE TBLPHPAKAUN.ID_JENISBAYARAN = TBLRUJJENISBAYARAN.ID_JENISBAYARAN"
					+ " AND TBLPHPAKAUN.ID_JENISBAYARAN IN (11) AND TBLPHPAKAUN.FLAG_AKTIF = 'Y' AND TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA = '" + idJadualPertama + "'";

			sql = sql + " ORDER BY TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.ID_AKAUN ASC";
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
				h.put("jenisBayaran",
						rs.getString("JENIS_BAYARAN") == null ? "" : rs
								.getString("JENIS_BAYARAN"));
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

				senaraiFiPermohonan.addElement(h);
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
		return senaraiFiPermohonan;
	}
	
	public Double calculateTotalFiPermohonan(String idJadualPertama) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (11) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (11) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
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
	
	@SuppressWarnings("unchecked")
	public Vector getSenaraiFiLesen(String idJadualPertama) throws Exception {
		Vector senaraiFiLesen = null;
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiFiLesen = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPAKAUN.ID_AKAUN, TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.DEBIT, TBLPHPAKAUN.CATATAN, TBLPHPAKAUN.KREDIT, TBLPHPAKAUN.NO_RUJUKAN,"
					+ " TBLPHPAKAUN.TARIKH_CEK, TBLPHPAKAUN.TARIKH_RESIT, TBLPHPAKAUN.NO_RESIT, TBLPHPAKAUN.ID_JENISTRANSAKSI, TBLRUJJENISBAYARAN.KETERANGAN AS JENIS_BAYARAN"
					+ " FROM TBLPHPAKAUN, TBLRUJJENISBAYARAN WHERE TBLPHPAKAUN.ID_JENISBAYARAN = TBLRUJJENISBAYARAN.ID_JENISBAYARAN"
					+ " AND TBLPHPAKAUN.ID_JENISBAYARAN IN (19) AND TBLPHPAKAUN.FLAG_AKTIF = 'Y' AND TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA = '" + idJadualPertama + "'";

			sql = sql + " ORDER BY TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.ID_AKAUN ASC";
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
				h.put("jenisBayaran",
						rs.getString("JENIS_BAYARAN") == null ? "" : rs
								.getString("JENIS_BAYARAN"));
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
					h.put("baki", Util.formatDecimal(baki));
				} else {
					h.put("baki", "0.00");
				}

				senaraiFiLesen.addElement(h);
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
		return senaraiFiLesen;
	}
	
	public Double calculateTotalFiLesen(String idJadualPertama) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (19) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (19) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
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
	
	@SuppressWarnings("unchecked")
	public Vector getSenaraiRoyalti(String idJadualPertama) throws Exception {
		Vector senaraiRoyalti = null;
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			senaraiRoyalti = new Vector();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPAKAUN.ID_AKAUN, TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.DEBIT, TBLPHPAKAUN.CATATAN, TBLPHPAKAUN.KREDIT, TBLPHPAKAUN.NO_RUJUKAN,"
					+ " TBLPHPAKAUN.TARIKH_CEK, TBLPHPAKAUN.TARIKH_RESIT, TBLPHPAKAUN.NO_RESIT, TBLPHPAKAUN.ID_JENISTRANSAKSI, TBLRUJJENISBAYARAN.KETERANGAN AS JENIS_BAYARAN"
					+ " FROM TBLPHPAKAUN, TBLRUJJENISBAYARAN WHERE TBLPHPAKAUN.ID_JENISBAYARAN = TBLRUJJENISBAYARAN.ID_JENISBAYARAN"
					+ " AND TBLPHPAKAUN.ID_JENISBAYARAN IN (14) AND TBLPHPAKAUN.FLAG_AKTIF = 'Y' AND TBLPHPAKAUN.ID_PHPPMOHONNJDUALPERTAMA = '" + idJadualPertama + "'";

			sql = sql + " ORDER BY TBLPHPAKAUN.TARIKH, TBLPHPAKAUN.ID_AKAUN ASC";
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
				h.put("jenisBayaran",
						rs.getString("JENIS_BAYARAN") == null ? "" : rs
								.getString("JENIS_BAYARAN"));
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
					h.put("baki", Util.formatDecimal(baki));
				} else {
					h.put("baki", "0.00");
				}

				senaraiRoyalti.addElement(h);
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
		return senaraiRoyalti;
	}
	
	public Double calculateTotalRoyalti(String idJadualPertama) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		Double totalDebit = 0D;
		Double totalKredit = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			// DEBIT
			sql = "SELECT DEBIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (14) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsDebit = stmt.executeQuery(sql);

			while (rsDebit.next()) {
				totalDebit = totalDebit
						+ Double.valueOf(rsDebit.getDouble("DEBIT"));
			}

			// KREDIT
			sql = "SELECT KREDIT FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN IN (14) AND FLAG_AKTIF = 'Y' AND ID_PHPPMOHONNJDUALPERTAMA = '"
					+ idJadualPertama + "'";
			ResultSet rsKredit = stmt.executeQuery(sql);

			while (rsKredit.next()) {
				totalKredit = totalKredit
						+ Double.valueOf(rsKredit.getDouble("KREDIT"));
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

	public String simpanBayaranD(String idJadualPertama, String tarikh,
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
			r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "13");
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
			r.add("CATATAN", butiran);
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN
			r.add("FLAG_DEPOSIT", idStatusDeposit);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idJadualPertama
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
			r.add("CATATAN", butiran);

			r.add("FLAG_DEPOSIT", idStatusDeposit);
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
	
	public String simpanBayaranFiPermohonan(String idJadualPertama, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String noMel,
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
			r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "11");
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
			r.add("CATATAN", butiran);
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idJadualPertama
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

	public void simpanKemaskiniBayaranFiPermohonan(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran,
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
			r.add("CATATAN", butiran);

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
	
	public String simpanBayaranFiLesen(String idJadualPertama, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String noMel,
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
			r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "19");
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
			r.add("CATATAN", butiran);
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idJadualPertama
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

	public void simpanKemaskiniBayaranFiLesen(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran,
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
			r.add("CATATAN", butiran);

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
	
	public String simpanBayaranRoyalti(String idJadualPertama, String tarikh,
			String idCaraBayaran, String idBank, String noRujukan,
			String tarikhCek, String amaun, String noResit, String tarikhResit,
			String butiran, String noMel,
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
			r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
			if (!"".equals(tarikh)) {
				r.add("TARIKH",
						r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", noRujukan);
			if (!"".equals(tarikhCek)) {
				r.add("TARIKH_CEK",
						r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			}
			r.add("ID_JENISBAYARAN", "14");
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
			r.add("CATATAN", butiran);
			r.add("ID_JENISTRANSAKSI", "2"); // BAYARAN
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			AuditTrail.logActivity("", "4", null, session, "INS",
					"FAIL [" + idJadualPertama
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

	public void simpanKemaskiniBayaranRoyalti(String idHasil, String idAkaun,
			String tarikh, String idCaraBayaran, String idBank,
			String noRujukan, String tarikhCek, String amaun, String noResit,
			String tarikhResit, String butiran,
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
			r.add("CATATAN", butiran);

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

	public void hapusBayaran(String idAkaun, HttpSession session) throws Exception {
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
					"FAIL [" + idAkaun
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

	@SuppressWarnings("unchecked")
	public void setMaklumatBayaran(String idAkaun) throws Exception {

		Db db = null;
		String sql = "";

		try {
			beanMaklumatBayaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH, NO_RUJUKAN, TARIKH_CEK, KREDIT, ID_CARABAYAR, NO_RESIT, TARIKH_RESIT, ID_BANK, CATATAN, FLAG_DEPOSIT, NO_DAFTAR_MEL, ID_MOD_BAYARAN, ID_JENISBAYARAN"
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
				h.put("idJenisBayaran", rs.getString("ID_JENISBAYARAN") == null ? ""
						: rs.getString("ID_JENISBAYARAN"));

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

	public Vector getBeanMaklumatBayaran() {
		return beanMaklumatBayaran;
	}

	public void setBeanMaklumatBayaran(Vector beanMaklumatBayaran) {
		this.beanMaklumatBayaran = beanMaklumatBayaran;
	}
}
