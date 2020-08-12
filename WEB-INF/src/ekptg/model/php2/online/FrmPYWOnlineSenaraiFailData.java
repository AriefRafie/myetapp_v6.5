/**
 * 
 */
package ekptg.model.php2.online;

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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.ppt.FrmSek8PampasanData;
import ekptg.model.utils.emel.EmailConfig;

/**
 * 
 *
 */
public class FrmPYWOnlineSenaraiFailData {
	
	private Vector senaraiFail = null;	
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatHeader = null;
	private Vector beanMaklumatSewa = null;
	private Vector beanMaklumatHakmilik = null;
	private Vector beanMaklumatBorangK = null;
	private Vector beanMaklumatLampiran = null;
	private Vector listLampiran = null;
	private Vector beanMaklumatPejabat = null;
	private static Logger log = Logger.getLogger(FrmPYWOnlineSenaraiFailData.class);
	
	private  Vector listPohon = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector setListPerjanjian(String idHasil) throws Exception {

		Vector senaraiPerjanjian = new Vector();
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_HASIL, NO_RUJUKAN, TARIKH_MULA, TARIKH_TAMAT, DEPOSIT, BAYARAN, FLAG_AKTIF "
				  +" FROM TBLPHPBAYARANPERLUDIBAYAR  "
				  +" WHERE ID_HASIL = '" + idHasil + "' "
				  +" AND (FLAG_PERJANJIAN = 'U' OR FLAG_PERJANJIAN IS NULL) ORDER BY TARIKH_MULA ASC ";
			
			ResultSet rs = stmt.executeQuery(sql);
						
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("NO_RUJUKAN",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("TARIKH_MULA", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("TARIKH_TAMAT", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("DEPOSIT",
						rs.getString("DEPOSIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEPOSIT"))));
				h.put("BAYARAN",
						rs.getString("BAYARAN") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("BAYARAN"))));
				h.put("FLAG_AKTIF",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				senaraiPerjanjian.addElement(h);
				bil++;
			}
			return senaraiPerjanjian;

		} catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector setListBayaranLL(String idHasil) throws Exception {

		Vector senaraiBayaranLL = new Vector();
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HASIL, ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 37 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'";

			sql = sql + " ORDER BY TARIKH, ID_AKAUN ASC";
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
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
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
						rs.getString("DEBIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				h.put("baki", Util.formatDecimal(getTotalBaki(temp, db1)));

				senaraiBayaranLL.addElement(h);
				bil++;
			}
			return senaraiBayaranLL;

		} catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
			if (db1 != null)
				db1.close();
		}
	}
	
	public Vector setListMaklumatSewa(String idHasil) throws Exception {

		Vector senaraiMaklumatSewa = new Vector();
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HASIL, ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'"
					+ " ORDER BY TARIKH, ID_AKAUN ASC";
			
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
				h.put("ID_HASIL",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
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
						rs.getString("DEBIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				h.put("baki", Util.formatDecimal(getTotalBaki(temp, db1)));

				senaraiMaklumatSewa.addElement(h);
				bil++;
			}
			return senaraiMaklumatSewa;

		} catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
			if (db1 != null)
				db1.close();
		}
	}
	
	public Vector setListDeposit(String idHasil) throws Exception {

		Vector senaraiDeposit = new Vector();
		Db db = null;
		Db db1 = null;
		String sql = "";
		String idAkaun = "";
		String temp = "";

		try {
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HASIL, ID_AKAUN, TARIKH, DEBIT, CATATAN, KREDIT, NO_RUJUKAN, TARIKH_CEK, TARIKH_RESIT, NO_RESIT, ID_JENISTRANSAKSI FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 9 AND FLAG_AKTIF = 'Y'"
					+ " AND ID_HASIL = '" + idHasil + "'"
					+ " ORDER BY TARIKH, ID_AKAUN ASC";
			
			log.info("SQL setListDeposit : "+sql.toUpperCase());
			
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
				h.put("ID_HASIL",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
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
						rs.getString("DEBIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("DEBIT"))));
				h.put("kredit",
						rs.getString("KREDIT") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs
										.getString("KREDIT"))));
				h.put("baki", Util.formatDecimal(getTotalBaki(temp, db1)));

				senaraiDeposit.addElement(h);
				bil++;
			}
			return senaraiDeposit;

		} catch (Exception re) {
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
			if (baki < 0D) {
				baki = baki * -1;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {

		}
		return baki;
	}
	
	public Vector statusPembayaranSewa(String findNamaPenyewa, String findTarikhResit, String idFail, String idHasil, String user_id) throws Exception {
        
        Vector status_pembayaranSewa = new Vector();
        Db db = null;
        String sql = "";
        try {
            db = new Db();
            
            
            
            
            Statement stmt = db.getStatement();
            
            sql = " SELECT DISTINCT TBLPFDFAIL.NO_FAIL, TBLPHPPEMOHON.NAMA, "
            		+" TBLPHPBAYARANPERLUDIBAYAR.NO_RUJUKAN, "
			        +" TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, "
			        +" TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT, TBLPHPHASIL.ID_HASIL, "
			        +" TBLPFDFAIL.ID_FAIL, TBLPFDFAIL.ID_SUBURUSAN, "
			        +" TBLPHPHASIL.FLAG_TUNGGAKAN, TBLPHPHASIL.FLAG_TUNGGAKAND "
				  +" FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLPFDFAIL TBLPFDFAILPERMOHONAN, "
				  +" TBLPERMOHONAN, TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK, TBLPHPBAYARANPERLUDIBAYAR, USERS_ONLINE, TBLPHPAKAUN "
				  +" WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+) "
				    +" AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON(+) "
				    +" AND TBLPHPHASIL.ID_FAILPERMOHONAN = TBLPFDFAILPERMOHONAN.ID_FAIL(+) "
				    +" AND TBLPFDFAILPERMOHONAN.ID_FAIL = TBLPERMOHONAN.ID_PERMOHONAN(+) "
				    +" AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN(+) "
				    +" AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN(+) "
				    +" AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) "
				    +" AND TBLPHPHASIL.ID_HASIL = TBLPHPAKAUN.ID_HASIL "
				    +" AND TBLPFDFAIL.ID_URUSAN = 115 "
				    +" AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' "
				    +" AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U' "
				    +" AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK(+) = 'U' "
				    +" AND TBLPHPPEMOHON.NO_PENGENALAN = USERS_ONLINE.NO_KP_BARU "
				    +" AND USERS_ONLINE.USER_ID = '"+user_id+"' ";
            
            if (findNamaPenyewa != null) {
				if (!findNamaPenyewa.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPPEMOHON.NAMA) LIKE '%' ||'"
							+ findNamaPenyewa.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findTarikhResit != null) {
				if (!findTarikhResit.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPAKAUN.TARIKH_RESIT) = to_date('"
							+ findTarikhResit.trim().toUpperCase() + "','dd/mm/yyyy') ";
				}
			}
            
                        ResultSet rs = stmt.executeQuery(sql);

                        Hashtable h;
                        int BIL = 1;
                        while (rs.next()) {

                            h = new Hashtable();
                            h.put("BIL", BIL);
                            h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
                            h.put("NAMA", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
                            h.put("NO_RUJUKAN", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
                            h.put("TARIKH_MULA", rs.getDate("TARIKH_MULA") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA")));
                            h.put("TARIKH_TAMAT", rs.getDate("TARIKH_TAMAT") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT")));
                            h.put("idHasil", rs.getString("ID_HASIL") == null ? "" : rs.getString("ID_HASIL"));
                            h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
                            h.put("FLAG_TUNGGAKAN", rs.getString("FLAG_TUNGGAKAN") == null ? "" : rs.getString("FLAG_TUNGGAKAN"));
                            h.put("FLAG_TUNGGAKAND", rs.getString("FLAG_TUNGGAKAND") == null ? "" : rs.getString("FLAG_TUNGGAKAND"));
                            if (rs.getString("ID_SUBURUSAN") != null && rs.getString("ID_SUBURUSAN").equals("35")) {
            					h.put("ID_SUBURUSAN", "BANGUNAN");
            				} else {
            					h.put("ID_SUBURUSAN", "TANAH");
            				}
                            
                            String status = "";
            				String statusSewa = "";
            				String statusDeposit = "";
            				int bilHari = 0;
                            
                            if (rs.getDate("TARIKH_TAMAT") != null
            						&& rs.getDate("TARIKH_TAMAT").toString().length() > 0) {
            					Calendar calCurrent = new GregorianCalendar();
            					Date dateCurrent = new Date();
            					calCurrent.setTime(dateCurrent);

            					Calendar calTamat = new GregorianCalendar();
            					Date dateTamat = sdf.parse(sdf.format(rs
            							.getDate("TARIKH_TAMAT")));
            					calTamat.setTime(dateTamat);

            					bilHari = daysBetween(calTamat.getTime(),
            							calCurrent.getTime());

            					if (calCurrent.getTime().after(calTamat.getTime())) {
            						status = "PERJANJIAN TAMAT";

            						if (rs.getString("FLAG_TUNGGAKAN") != null
            								&& rs.getString("FLAG_TUNGGAKAN").equals("T")) {

            							if (rs.getString("FLAG_TUNGGAKAND") != null
            									&& rs.getString("FLAG_TUNGGAKAND").equals(
            											"T")) {
            								statusDeposit = "DEPOSIT TIDAK DITUNTUT";

            							}
            						}

            					} else if (calCurrent.getTime().before(calTamat.getTime())
            							&& bilHari <= 90) {
            						status = bilHari + " HARI LAGI";
            					}
            					h.put("status", status);
            					h.put("statusDeposit", statusDeposit);

            				} else {
            					h.put("status", "");
            					h.put("statusDeposit", "");

            				}

            				if (rs.getString("FLAG_TUNGGAKAN") != null
            						&& rs.getString("FLAG_TUNGGAKAN").equals("Y")) {
            					statusSewa = "TERTUNGGAK";
            					h.put("statusSewa", statusSewa);

            				} else {
            					h.put("statusSewa", "");
            				}

            				status_pembayaranSewa.addElement(h);

                            BIL++;
                        }
                        
                        return status_pembayaranSewa;

                    } catch (Exception re) {
                        throw re;
                    } finally {
                        if (db != null)
                            db.close();
                    }
    }
	
	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public Vector statusPermohonanSewa(String findNoPermohonan, String findNoHakmilik, String findNoWarta, String findNoLot, 
			String id_user) throws Exception {
		
		Vector status_PermohonanSewa = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.NO_PERMOHONAN, D.KETERANGAN, B.ID_STATUS, H.USER_LOGIN, B.NO_SAMBUNGAN,"
			    + " A.ID_MASUK, A.TAJUK_FAIL, F.NO_WARTA, F.NO_HAKMILIK, F.NO_LOT, TO_CHAR (B.TARIKH_TERIMA, 'DD/MM/YYYY') AS TARIKH_TERIMA, INITCAP(I.NAMA_PEJABAT) AS NAMA_PEJABAT"
			    + " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H, TBLRUJPEJABATJKPTG I"
			    + " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_STATUS = D.ID_STATUS(+) AND B.ID_PERMOHONAN = E.ID_PERMOHONAN"
			    + " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+) AND A.ID_MASUK = H.USER_ID(+) AND A.ID_URUSAN IN (7, 12, 13) AND A.FLAG_JENIS_FAIL = '4'"
			    + " AND B.FLAG_PERJANJIAN = 'U' AND B.FLAG_AKTIF = 'Y' AND E.FLAG_HAKMILIK = 'U' AND B.ID_JKPTG = I.ID_PEJABATJKPTG(+) AND A.ID_MASUK = '"+id_user+"'";

			if (findNoPermohonan != null) {
				if (!findNoPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ findNoPermohonan.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoHakmilik != null) {
				if (!findNoHakmilik.trim().equals("")) {
					sql = sql + "AND UPPER(G.NO_HAKMILIK) LIKE '%' ||'" 
							+ findNoHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoWarta != null) {
				if (!findNoWarta.trim().equals("")) {
					sql = sql + "AND UPPER(G.NO_WARTA) LIKE '%' ||'" 
							+ findNoWarta.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoLot != null) {
				if (!findNoLot.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_LOT) LIKE '%' ||'"
							+ findNoLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			  
			sql = sql + "ORDER BY A.ID_FAIL DESC";
			
			log.info(" SQL PEMOHON LIST FROM MODEL PERMOHONAN SEWA 1 :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("bil", bil);
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_PERMOHONAN", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("TARIKH_TERIMA", rs.getString("TARIKH_TERIMA") == null ? "" : rs.getString("TARIKH_TERIMA"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("PERKARA", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
					h.put("STATUS", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
					h.put("STATUS", " PENDAFTARAN");
				} else {
					h.put("STATUS", " SEDANG DIPROSES");
				}
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				
				status_PermohonanSewa.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return status_PermohonanSewa;
}
	
	public Vector setListPohon(String idpermohonan) throws Exception {
		
		Vector listPohon = new Vector();
		Db db = null;
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT DISTINCT p.tujuan_bi, p.id_permohonan, p.id_status, p.flag_jenispermohonan, p.no_permohonan, f.no_fail, flag_jenis_kod_daerah, ";
				sql += " f.id_fail, n.nama_negeri, p.tarikh_permohonan, p.tujuan, k.nama_kementerian, k.id_kementerian, catatan_status_online, "; 
				sql += " s.keterangan, d.nama_daerah, d.id_daerah, p.tarikh_kehendaki,  p.tarikh_surat, p.id_agensi, p.flag_status_online, "; 
				sql += " p.no_rujukan_surat, p.flag_peruntukan, p.flag_segera, p.id_mukim, p.flag_jenis_projek, p.no_permohonan_online,p.tarikh_sahkan, "; 
				sql += " us.nama_suburusan, us.id_suburusan, smk.id_senaraisemak, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, ";
				sql += " k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri, p.flag_semak, n2.id_negeri as idProjekNegeri, n2.nama_negeri as nama_negeriprojek ";
				sql += " FROM Tblrujsuburusan us, Tblpptsenaraisemak smk, Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, ";
				sql += " Tblrujkementerian k, Tblrujstatus s,  Tblpptpermohonan p, Tblrujnegeri n2 ";  
				sql += " WHERE f.id_fail(+) = p.id_fail "; 
				sql += " AND k.id_kementerian(+) = f.id_kementerian "; 
				sql += " AND n.id_negeri(+) = k.id_negeri "; 
				sql += " AND n2.id_negeri(+) = f.id_negeri "; 
				sql += " AND s.id_status(+) = p.id_status "; 
				sql += " AND d.id_daerah(+) = p.id_daerah "; 
				sql += " AND us.id_suburusan(+) = f.id_suburusan "; 
				sql += " AND p.id_permohonan = smk.id_permohonan(+) ";  
				sql += " AND p.id_permohonan = '"+idpermohonan+"'";
						
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				int bil = 1;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("BIL", bil);
					h.put("tujuan_bi", rs.getString("tujuan_bi")==null?"":rs.getString("tujuan_bi"));
					h.put("flag_jenis_kod_daerah", rs.getString("flag_jenis_kod_daerah")==null?"":rs.getString("flag_jenis_kod_daerah"));
					h.put("catatan_status_online", rs.getString("catatan_status_online")==null?"":rs.getString("catatan_status_online"));
					h.put("flag_status_online", rs.getString("flag_status_online")==null?"":rs.getString("flag_status_online"));
					h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
					h.put("no_permohonan_online", rs.getString("no_permohonan_online")==null?"":rs.getString("no_permohonan_online"));
					h.put("flag_jenis_projek", rs.getString("flag_jenis_projek")==null?"":rs.getString("flag_jenis_projek"));
					h.put("nama_negeriprojek", rs.getString("nama_negeriprojek")==null?"":rs.getString("nama_negeriprojek"));
					h.put("flag_semak", rs.getString("flag_semak")==null?"":rs.getString("flag_semak"));
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("idProjekNegeri", rs.getString("idProjekNegeri")==null?"":rs.getString("idProjekNegeri"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
					h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
					h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
					h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
					h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
					h.put("noPermohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
					h.put("no_fail", rs.getString("no_fail")==null?"BELUM DISAHKAN":rs.getString("no_fail"));
					h.put("no_jkptg", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
					h.put("tarikh_sahkan", rs.getDate("tarikh_sahkan")==null?"":Format.format(rs.getDate("tarikh_sahkan")));
					h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
					h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
					h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
					h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
					h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
					h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
					h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
					h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
					
					/*if(rs.getString("flag_jenispermohonan") != null && rs.getString("flag_jenispermohonan") != ""){
						
						if(rs.getString("flag_jenispermohonan").equals("1")){
							h.put("flag_jenispermohonan","PERMOHONAN ONLINE");
						}else if(rs.getString("flag_jenispermohonan").equals("2")){
							h.put("flag_jenispermohonan","PERMOHONAN KAUNTER");
						}else{
							h.put("flag_jenispermohonan","");
						}
						
					}else{
						h.put("flag_jenispermohonan","");
					}
					
					h.put("no_flag_jenispermohonan", rs.getString("flag_jenispermohonan")==null?"":rs.getString("flag_jenispermohonan"));*/
					
					listPohon.addElement(h);
					bil++;
				}
				return listPohon;
				
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
	}
	
	public void carianFail(String noPermohonan, String tarikhTerima,String idNegeriC,
			String idDaerahC, String idMukimC,String jenisHakmilik,String txtNoHakmilik,String txtNoWarta,String jenisLot,String txtNoLot,String txtNoPegangan, HttpSession session ) throws Exception {

		Db db = null;
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.NO_PERMOHONAN, B.TARIKH_TERIMA, C.NAMA,"
				+ " C.NO_PENGENALAN, D.KETERANGAN, B.ID_STATUS, H.USER_LOGIN, B.NO_SAMBUNGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E,"
				+ " TBLHTPHAKMILIKAGENSI F, TBLHTPHAKMILIK G, USERS H"
				+ " WHERE A.ID_URUSAN IN (7,12,13) AND A.FLAG_JENIS_FAIL = '4' AND B.FLAG_PERJANJIAN = 'U' "
				+ " AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
				+ " AND E.ID_HAKMILIKAGENSI = F.ID_HAKMILIKAGENSI AND F.ID_HAKMILIK = G.ID_HAKMILIK"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.FLAG_AKTIF = 'Y'"
				+ " AND A.ID_MASUK = H.USER_ID(+) AND A.ID_MASUK = '" + userId + "'";
			
			//noPermohonan
			if (noPermohonan != null) {
				if (!noPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ noPermohonan.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}	
			
			//idNegeri
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND G.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//idDaerah
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND G.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//idMukim
			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}			

			//jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("") && !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND G.ID_JENISHAKMILIK = '" + jenisHakmilik.trim() + "'";
				}
			}
			
			//noHakmilik
			if (txtNoHakmilik != null) {
				if (!txtNoHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_HAKMILIK) LIKE '%' ||'"
							+ txtNoHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//txtNoWarta
			if (txtNoWarta != null) {
				if (!txtNoWarta.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_WARTA) LIKE '%' ||'"
							+ txtNoWarta.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("") && !jenisLot.trim().equals("99999")) {
					sql = sql + " AND G.ID_LOT = '" + jenisLot.trim() + "'";
				}
			}
			
			//lot
			if (txtNoLot != null) {
				if (!txtNoLot.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_LOT) LIKE '%' ||'"
							+ txtNoLot.trim().toUpperCase() + "'|| '%'";
				}
			}	
			//peganganHakmilik
			if (txtNoPegangan != null) {
				if (!txtNoPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(G.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ txtNoPegangan.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon",rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("noPengenalan",rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
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
	
	public void setMaklumatTanah(String idHakmilikAgensi, String idHakmilikSementara) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
					+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
					+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"

					+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			
			sql = sql + " UNION";

			sql = sql
					+ " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK,"
					+ " HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, "
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH,"
					+ " HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
					+ " FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					+ " WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? 
						"" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK"));
				h.put("noLot",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot",(rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase())+ " "
						+ (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));
				h.put("luasLot",(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs
						.getDouble("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : 
						rs.getString("JENIS_LUAS")));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				//--mula------
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "": rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble
						("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));
				h.put("idDaerah",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("idNegeri",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("idSubKategori",rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				//--tamat------
				
				if (rs.getString("NO_HAKMILIK") != null && rs.getString("NO_WARTA") == null){
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null && rs.getString("NO_WARTA") != null){
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				beanMaklumatTanah.addElement(h);
				bil++;
			}
			
			if (bil == 1){
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");				
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	//--mula-----
	public void setMaklumatBorangK(String idPHPBorangK) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBorangK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK, BK.TARIKH_BORANGK, BK.CATATAN, EBK.NO_PERSERAHAN, EBK.TARIKH_CATATAN, EBK.TARIKH_TERIMA,"
					+ " HM.PEGANGAN_HAKMILIK, UPPER(RLOT.KETERANGAN) AS KETERANGAN_LOT, HM.NO_LOT, HM.LUAS_AMBIL, UPPER(RLUAS.KETERANGAN) AS KETERANGAN_LUAS,"
					+ " RJHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, RMUKIM.NAMA_MUKIM, RDAERAH.NAMA_DAERAH, RNEGERI.NAMA_NEGERI, RAGENSI.NAMA_AGENSI, RKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " RKATEGORI.KETERANGAN AS KATEGORI, RSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.SYARAT_NYATA AS SYARAT, HM.SEKATAN_KEPENTINGAN AS SEKATAN, HM.KEGUNAAN_TANAH"
					+ " FROM TBLPHPBORANGK BK, TBLPHPENDOSANBORANGK EBK, TBLPHPHAKMILIKBORANGK HMBK, TBLPHPHAKMILIK HM, TBLRUJLOT RLOT, TBLRUJLUAS RLUAS, TBLRUJJENISHAKMILIK RJHM,"
					+ " TBLRUJMUKIM RMUKIM,  TBLRUJDAERAH RDAERAH, TBLRUJNEGERI RNEGERI, TBLRUJKEMENTERIAN RKEMENTERIAN, TBLRUJAGENSI RAGENSI, TBLRUJKATEGORI RKATEGORI, TBLRUJSUBKATEGORI RSUBKATEGORI"
					+ " WHERE BK.ID_BORANGK = EBK.ID_BORANGK(+) AND BK.ID_BORANGK = HMBK.ID_BORANGK(+) AND HMBK.ID_HAKMILIK = HM.ID_HAKMILIK(+) AND HM.ID_LOT = RLOT.ID_LOT(+)"
					+ " AND HM.ID_UNITLUASAMBIL = RLUAS.ID_LUAS(+) AND HM.ID_JENISHAKMILIK = RJHM.ID_JENISHAKMILIK(+) AND HM.ID_MUKIM = RMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RNEGERI.ID_NEGERI(+) AND HM.ID_AGENSI = RAGENSI.ID_AGENSI(+) AND HM.ID_KEMENTERIAN = RKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HM.ID_KATEGORI = RKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND BK.ID_BORANGK = '" + idPHPBorangK + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPHPBorangK",
						rs.getString("ID_PHPBORANGK") == null ? "" : rs
								.getString("ID_PHPBORANGK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("KETERANGAN_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("KETERANGAN_LOT")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_AMBIL") == null
								|| rs.getString("KETERANGAN_LUAS") == null ? ""
								: Utils.formatLuas(rs.getDouble("LUAS_AMBIL"))
										+ " " + rs.getString("KETERANGAN_LUAS"));
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				beanMaklumatBorangK.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPHPBorangK", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				beanMaklumatBorangK.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPHPBorangKByPeganganHakmilik(String peganganHakmilik)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK"
					+ " FROM TBLPHPBORANGK BK, TBLPHPHAKMILIKBORANGK HMBK, TBLPHPHAKMILIK HM"
					+ " WHERE BK.ID_BORANGK = HMBK.ID_BORANGK AND HMBK.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND UPPER(HM.PEGANGAN_HAKMILIK) = '"
					+ peganganHakmilik.toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PHPBORANGK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikUrusanByPeganganHakmilik(String peganganHakmilik)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMU.ID_HAKMILIKURUSAN"
					+ " FROM TBLHTPHAKMILIKURUSAN HMU, TBLHTPPERMOHONAN MOHON"
					+ " WHERE HMU.ID_PERMOHONAN = MOHON.ID_PERMOHONAN AND MOHON.ID_JENISTANAH = 3"
					+ " AND UPPER(HMU.PEGANGAN_HAKMILIK) = '"
					+ peganganHakmilik.toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKURUSAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatBorangK(String idPPTBorangK,
			String idHakmilikUrusan, String idPHPBorangK) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBorangK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			// SENARAI BORANG K PPT
			sql = "SELECT TO_CHAR(PPTBK.ID_BORANGK) AS ID_PPTBORANGK, '' AS ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, '' AS PEGANGAN_HAKMILIK, PPTHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PPTHM.NO_HAKMILIK,"
					+ " CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1 WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN PPTHM.ID_LOT ELSE 1 END AS ID_LOT,"
					+ " (SELECT RUJLOT.KETERANGAN FROM TBLRUJLOT RUJLOT WHERE RUJLOT.ID_LOT = (CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1"
					+ " WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN  PPTHM.ID_LOT ELSE 1 END)) AS JENIS_LOT,"
					+ " CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN PPTHM.NO_LOT WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN  PPTHM.NO_PT ELSE PPTHM.NO_LOT END AS NO_LOT,"
					+ " PPTHM.ID_UNITLUASAMBIL AS ID_LUAS_BERSAMAAN, PPTHM.LUAS_AMBIL AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PPTHM.NO_WARTA_RIZAB AS NO_WARTA, PPTHM.TARIKH_WARTA_RIZAB AS TARIKH_WARTA,"
					+ " PPTHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PPTHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PPTHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PPTHM.ID_KATEGORITANAH AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, 109 AS ID_SUBKATEGORI, 'TIADA MAKLUMAT' AS SUBKATEGORI, '' AS KEGUNAAN_TANAH,"
					+ " PPTHM.SYARAT_NYATA AS SYARAT, PPTHM.SEKATAN_KEPENTINGAN AS SEKATAN, PPTMOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, PPTENBK.NO_PERSERAHAN, PPTENBK.TARIKH_CATATAN, PPTENBK.TARIKH_TERIMA"

					+ " FROM TBLPPTBORANGK PPTBK, TBLPPTHAKMILIKBORANGK PPTHMBK, TBLPPTHAKMILIK PPTHM, TBLPPTENDOSANBORANGK PPTENBK, TBLPPTPERMOHONAN PPTMOHON,"
					+ " TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI,"
					+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PPTBK.ID_BORANGK = PPTHMBK.ID_BORANGK AND PPTHMBK.ID_HAKMILIK = PPTHM.ID_HAKMILIK AND PPTBK.ID_BORANGK = PPTENBK.ID_BORANGK"
					+ " AND PPTHM.ID_PERMOHONAN = PPTMOHON.ID_PERMOHONAN AND PPTHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PPTHM.ID_UNITLUASAMBIL = RUJLUAS.ID_LUAS(+)"
					+ " AND PPTHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PPTHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PPTHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PPTHM.ID_KATEGORITANAH = RUJKATEGORI.ID_KATEGORI(+) AND PPTMOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PPTHM.FLAG_ENDOSAN_BORANGK IS NOT NULL AND PPTBK.ID_BORANGK NOT IN (SELECT ID_BORANGK FROM TBLHTPINFOBORANGK)"
					+ " AND PPTBK.ID_BORANGK = '" + idPPTBorangK + "'";

			sql = sql + " UNION";

			// SENARAI BORANG K HTP
			sql = sql
					+ " SELECT '' AS ID_PPTBORANGK, TO_CHAR(URUSAN.ID_HAKMILIKURUSAN) AS ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, URUSAN.PEGANGAN_HAKMILIK, URUSAN.ID_JENISHAKMILIK, JENISHAKMILIK.KOD_JENIS_HAKMILIK, URUSAN.NO_HAKMILIK, URUSAN.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, URUSAN.NO_LOT,"
					+ " URUSAN.ID_LUAS_BERSAMAAN, URUSAN.LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS, URUSAN.NO_WARTA, URUSAN.TARIKH_WARTA, TO_NUMBER(URUSAN.ID_MUKIM) AS ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " URUSAN.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, URUSAN.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, URUSAN.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, URUSAN.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, '' AS KEGUNAAN_TANAH,"
					+ " URUSAN.SYARAT, URUSAN.SEKATAN, MOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, ENDOSAN.NO_PERSERAHAN, ENDOSAN.TARIKH_CATATAN, ENDOSAN.TARIKH_TERIMA"

					+ " FROM TBLHTPHAKMILIKURUSAN URUSAN, TBLHTPPERMOHONAN MOHON, TBLHTPINFOBORANGK INFO, TBLPPTBORANGK PPTBK, TBLPPTENDOSANBORANGK ENDOSAN,"
					+ " TBLRUJJENISHAKMILIK JENISHAKMILIK, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI,"
					+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE URUSAN.ID_PERMOHONAN = MOHON.ID_PERMOHONAN(+) AND URUSAN.ID_PERMOHONAN = INFO.ID_PERMOHONAN(+) AND INFO.ID_INFOBORANGK = PPTBK.ID_BORANGK(+)"
					+ " AND PPTBK.ID_BORANGK = ENDOSAN.ID_BORANGK(+) AND URUSAN.ID_JENISHAKMILIK = JENISHAKMILIK.ID_JENISHAKMILIK(+) AND URUSAN.ID_LOT = RUJLOT.ID_LOT"
					+ " AND URUSAN.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND URUSAN.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND URUSAN.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND URUSAN.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND URUSAN.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND URUSAN.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND MOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					// +
					// " AND MOHON.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK)"
					+ " AND MOHON.ID_JENISTANAH = 3"
					+ " AND URUSAN.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan
					+ "'";

			sql = sql + " UNION";

			// SENARAI BORANG K PHP
			sql = sql
					+ " SELECT '' AS ID_PPTBORANGK, '' AS ID_HAKMILIKURUSAN, TO_CHAR(PHPBK.ID_BORANGK) AS ID_PHPBORANGK, PHPHMBK.PEGANGAN_HAKMILIK, PHPHMBK.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHMBK.NO_HAKMILIK,"
					+ " PHPHMBK.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHMBK.NO_LOT, PHPHMBK.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHMBK.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHMBK.NO_WARTA, PHPHMBK.TARIKH_WARTA, PHPHMBK.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHMBK.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHMBK.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHMBK.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHMBK.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHMBK.KEGUNAAN_TANAH, PHPHMBK.SYARAT, PHPHMBK.SEKATAN,"
					+ " PHPHMBK.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPBK.TARIKH_BORANGK, PHPBK.CATATAN, PHPBK.NO_PERSERAHAN, PHPBK.TARIKH_CATATAN, PHPBK.TARIKH_TERIMA"

					+ " FROM TBLPHPBORANGK PHPBK, TBLPHPHAKMILIKBORANGK PHPHMBK, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPBK.ID_BORANGK = PHPHMBK.ID_BORANGK AND PHPHMBK.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHMBK.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHMBK.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHMBK.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHMBK.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHMBK.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHMBK.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHMBK.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHMBK.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPBK.ID_HAKMILIKURUSAN IS NULL"
					+ " AND PHPBK.ID_BORANGK = '" + idPHPBorangK + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idPPTBorangK",
						rs.getString("ID_PPTBORANGK") == null ? "" : rs
								.getString("ID_PPTBORANGK").toUpperCase());
				h.put("idHakmilikUrusan",
						rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs
								.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("idPHPBorangK",
						rs.getString("ID_PHPBORANGK") == null ? "" : rs
								.getString("ID_PHPBORANGK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				beanMaklumatBorangK.addElement(h);

			} else {

				h = new Hashtable();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");

				h.put("peganganHakmilik", "");
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				beanMaklumatBorangK.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//--tamat--------
	
	
	public String getIdHakmilikAgensiByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}
			
		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	public String getIdHakmilikPermohonanByIdFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKPERMOHONAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatHakmilik(String idHakmilikPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK"

					+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPermohonan", rs
						.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
						.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK").toUpperCase());
				beanMaklumatHakmilik.addElement(h);

			} else {

				h = new Hashtable();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");

				h.put("peganganHakmilik", "");
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				h.put("flagBorangK", "");
				beanMaklumatHakmilik.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String daftarBaru(String idUrusan, String idSuburusan, String idSubsuburusan, String idHakmilikAgensi,
			String idHakmilikSementara, String noRujukanSurat, String tarikhSurat, String idJenisTanah, String idPHPBorangK, 
			String idPPTBorangK, String idHakmilikUrusan, String idKementerianTanah, String idNegeriTanah, String tarikhTerima, 
			String idJenisPermohonan, HttpSession session, String idPermohonanLama) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String idHakmilikHtp = "";
		String idKementerian = "";
		String idNegeriHakmilik = "";
		String idLuas = "";
		String luas = "";
		String namaUser = "";
		String IdKategoriUser = "";
		String emelUser = "";
		String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN, TBLHTPHAKMILIK.ID_NEGERI, " 
				+ " TBLHTPHAKMILIKAGENSI.ID_LUAS_BERSAMAAN, TBLHTPHAKMILIKAGENSI.LUAS_BERSAMAAN, " 
				+ " TBLHTPHAKMILIKAGENSI.ID_HAKMILIK FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI " 
				+ " WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rsTanah = stmt.executeQuery(sql);
			if (rsTanah.next()){
				idKementerian = rsTanah.getString("ID_KEMENTERIAN");
				idNegeriHakmilik = rsTanah.getString("ID_NEGERI");
				idLuas = rsTanah.getString("ID_LUAS_BERSAMAAN");
				luas = rsTanah.getString("LUAS_BERSAMAAN");
				idHakmilikHtp = rsTanah.getString("ID_HAKMILIK");
			}			
		
			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_SUBSUBURUSAN", idSubsuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));	
			r.add("ID_LOKASIFAIL", "2"); //UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); //ONLINE ETAPP
			r.add("ID_NEGERI", idNegeriHakmilik);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);		
			sql = "SELECT A.USER_ID, A.USER_NAME, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_NEGERI,B.NO_FAX, B.NO_HP,"
				+ " B.NO_KP_BARU, B.NO_TEL, B.EMEL, B.KATEGORI"
				+ " FROM USERS A, USERS_ONLINE B"
				+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			
			ResultSet rsUser = stmt.executeQuery(sql);
			log.info("adadad" +sql);

			//TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			
			if (rsUser.next()){
				IdKategoriUser = rsUser.getString("KATEGORI");
				if (!"".equals(IdKategoriUser) && "Individu".equals(IdKategoriUser)) {
					r.add("ID_KATEGORIPEMOHON", "1");
				} else {
					r.add("ID_KATEGORIPEMOHON", "2");
				}
				if (rsUser.getString("USER_NAME") != null){
					namaUser = rsUser.getString("USER_NAME");
				}
				r.add("NAMA", namaUser);
				r.add("NO_PENGENALAN", rsUser.getString("NO_KP_BARU") == null ? "" : rsUser.getString("NO_KP_BARU"));
				r.add("NO_TEL", rsUser.getString("NO_TEL") == null ? "" : rsUser.getString("NO_TEL"));
				r.add("NO_FAX", rsUser.getString("NO_FAX") == null ? "" : rsUser.getString("NO_FAX"));
				if (rsUser.getString("EMEL") != null){
					emelUser = rsUser.getString("EMEL");
				}
				r.add("EMEL", emelUser);
				r.add("ALAMAT1_TETAP", rsUser.getString("ALAMAT1") == null ? "" : rsUser.getString("ALAMAT1"));
				r.add("ALAMAT2_TETAP", rsUser.getString("ALAMAT2") == null ? "" : rsUser.getString("ALAMAT2"));
				r.add("ALAMAT3_TETAP", rsUser.getString("ALAMAT3") == null ? "" : rsUser.getString("ALAMAT3"));
				r.add("POSKOD_TETAP", rsUser.getString("POSKOD") == null ? "" : rsUser.getString("POSKOD"));
				r.add("ID_NEGERITETAP", rsUser.getString("ID_NEGERI") == null ? "99999" : rsUser.getString("ID_NEGERI"));
				r.add("ID_BANDARTETAP","99999");
			}			
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);
			String idPejabatJKPTG = "";
			sql = "SELECT ID_PEJABATJKPTG, ID_NEGERI FROM TBLRUJPEJABATJKPTG "
					+ " WHERE ID_SEKSYEN = '4' AND ID_NEGERI = '" + idNegeriHakmilik + "'";
				
			ResultSet rsJKPTG = stmt.executeQuery(sql);
			log.info("Pejabat JKPTG" +sql);
			if (rsJKPTG.next()){
				idPejabatJKPTG = rsJKPTG.getString("ID_PEJABATJKPTG");
			}
		
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", idPejabatJKPTG);
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "");
			r.add("NO_RUJ_SURAT",noRujukanSurat);
			r.add("TARIKH_SURAT",tarikhSurat);
			r.add("TARIKH_TERIMA", r.unquote(TT));
			
			Calendar currentDate = new GregorianCalendar();			
			String noPermohonan = "JKPTG/BPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/" + currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);
			r.add("NO_PERMOHONAN", noPermohonan);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PERJANJIAN", "U");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);
			//TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			if ("3".equals(idJenisTanah)) {
				r.add("FLAG_BORANGK", "Y");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);
			//TBLPHPPERMOHONANSEWA
			String namaTujuan = getNamaTujuan(idSubsuburusan);
			log.info("idSubsuburusan "+idSubsuburusan);
			r = new SQLRenderer();
			long idPHPPermohonanSewa = DB.getNextID("TBLPHPPERMOHONANSEWA_SEQ");
			r.add("ID_PHPPERMOHONANSEWA", idPHPPermohonanSewa);
			r.add("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_LUASASAL", idLuas);
			r.add("LUAS_ASAL", luas);
			if ("14".equals(idNegeriHakmilik)){
				r.add("FLAG_PERMOHONANDARI", "0");
			} else {
				r.add("FLAG_PERMOHONANDARI", "1");
			}
			r.add("TUJUAN", namaTujuan);
			r.add("FLAG_PROSESFAIL", "J");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_PERMOHONANLAMA", idPermohonanLama);	

			sql = r.getSQLInsert("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);
			//TBLPHPPERMOHONANTUJUAN
			r = new SQLRenderer();
			long idTujuanPermohonan = DB.getNextID("TBLPHPPERMOHONANTUJUAN_SEQ");
			r.add("ID_PHPPERMOHONANTUJUAN", idTujuanPermohonan);
			r.add("ID_PHPPERMOHONANSEWA", idPHPPermohonanSewa);	
			r.add("ID_JENISTUJUAN", idSubsuburusan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			
			sql = r.getSQLInsert("TBLPHPPERMOHONANTUJUAN");
			stmt.executeUpdate(sql);
			log.info("INSERT : "+sql);
			// TBLPHPHAKMILIK
			String peganganHakmilik = "";	
			if ("3".equals(idJenisTanah)) {
				setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
				if (getBeanMaklumatBorangK().size() != 0) {
					Hashtable hashTanah = (Hashtable) getBeanMaklumatBorangK()
							.get(0);

					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					if (hashTanah.get("peganganHakmilik").toString().trim()
							.length() > 0) {
						r.add("PEGANGAN_HAKMILIK",
								hashTanah.get("peganganHakmilik"));
					} else {
						peganganHakmilik = getKodNegeri((String) hashTanah
								.get("idNegeri"))
								+ getKodDaerah((String) hashTanah
										.get("idDaerah"))
								+ getKodMukim((String) hashTanah.get("idMukim"))
								+ getKodJenisHakmilik((String) hashTanah
										.get("idJenisHakmilik"))
								+ Utils.digitLastFormatted(
										(String) hashTanah.get("noHakmilik"), 8);
						r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
					}
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
					r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
					r.add("ID_LOT", hashTanah.get("idLot"));
					r.add("NO_LOT", hashTanah.get("noLot"));
					r.add("ID_LUAS", hashTanah.get("idLuas"));
					r.add("LUAS", hashTanah.get("luasBersamaan"));
					r.add("SYARAT", hashTanah.get("syarat"));
					r.add("SEKATAN", hashTanah.get("sekatan"));
					r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
					r.add("ID_KATEGORI", hashTanah.get("idKategori"));
					r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
					r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
					r.add("ID_AGENSI", hashTanah.get("idAgensi"));

					r.add("TARIKH_BORANGK",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhBorangK")
									+ "','dd/MM/yyyy')"));
					r.add("CATATAN", hashTanah.get("catatan"));
					r.add("NO_PERSERAHAN", hashTanah.get("noPerserahan"));
					r.add("TARIKH_CATATAN",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhCatatan")
									+ "','dd/MM/yyyy')"));
					r.add("TARIKH_TERIMA",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhTerima")
									+ "','dd/MM/yyyy')"));

					sql = r.getSQLInsert("TBLPHPHAKMILIK");
					stmt.executeUpdate(sql);
					log.info("INSERT : "+sql);
				}
			} else {
				setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				if (getBeanMaklumatTanah().size() != 0) {
					Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah()
							.get(0);

					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					r.add("PEGANGAN_HAKMILIK",
							hashTanah.get("peganganHakmilik"));
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("NO_WARTA", hashTanah.get("noWarta"));
					r.add("TARIKH_WARTA",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhWarta")
									+ "','dd/MM/yyyy')"));
					r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
					r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
					r.add("ID_LOT", hashTanah.get("idLot"));
					r.add("NO_LOT", hashTanah.get("noLot"));
					r.add("ID_LUAS", hashTanah.get("idLuas"));
					r.add("LUAS", hashTanah.get("luasBersamaan"));
					r.add("SYARAT", hashTanah.get("syarat"));
					r.add("SEKATAN", hashTanah.get("sekatan"));
					r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
					r.add("ID_KATEGORI", hashTanah.get("idKategori"));
					r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
					r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
					r.add("ID_AGENSI", hashTanah.get("idAgensi"));

					sql = r.getSQLInsert("TBLPHPHAKMILIK");
					stmt.executeUpdate(sql);
					log.info("INSERT : "+sql);
				}
			}
									
			conn.commit();			
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}
	
	public String getKodDaerah(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '"
					+ idDaerah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_DAERAH");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodMukim(String idMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM = '"
					+ idMukim + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MUKIM");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '"
					+ idJenisHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_JENIS_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNamaSuburusan(String idSuburusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT LOWER(NAMA_SUBURUSAN) AS NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = '" + idSuburusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("NAMA_SUBURUSAN");
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
	
	public String getKodUrusanByIdUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '" + idUrusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_URUSAN");
			} else {
				return "";
			}
			
		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdSuburusanstatus(String idSuburusan, String idStatus) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '" + idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}
			
		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	//GENERATE NO FAIL
	public String generateNoFail(String idUrusan, String kodUrusan, String kodKementerian, 
	String idKementerian, String kodNegeri, String idNegeri, String idSuburusan) throws Exception {
		
		String noFail = "";
		String kodTanah = "";

		if ("35".equals(idSuburusan)) {
			kodTanah = "B";
		} else {
			kodTanah = "T";
		}

		noFail = "JKPTG/BPHP/" + kodUrusan + "/" + kodKementerian + "/"
				+ kodNegeri + "/" + kodTanah + "-"
				+ File.getSeqNo(4, Integer.parseInt(idUrusan), Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));

		return noFail;
	}
	
	public String getKodKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MAMPU");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_URUSAN, A.ID_SUBURUSAN, A.ID_SUBSUBURUSAN, A.NO_FAIL, A.ID_FAIL, B.ID_PERMOHONAN, B.NO_PERMOHONAN, " 
				+ " B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN AS TUJUAN_PERMOHONAN, D.ID_JENISTUJUAN,"
				+ " B.ID_PEMOHON, A.ID_URUSAN, A.ID_SUBURUSAN, C.FLAG_PROSESFAIL, C.CATATAN, C.TUJUAN, B.ID_PERMOHONAN "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLPHPPERMOHONANTUJUAN D "  
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"
				+ " AND C.ID_PHPPERMOHONANSEWA = D.ID_PHPPERMOHONANSEWA"
				+ " AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("sql permohonan: "+sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				//h.put("idSubsuburusan", rs.getString("ID_SUBSUBURUSAN") == null ? "" : rs.getString("ID_SUBSUBURUSAN"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("noRujukanSurat", rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "0" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "": sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tujuan", rs.getString("TUJUAN_PERMOHONAN") == null ? "" : rs.getString("TUJUAN_PERMOHONAN").toUpperCase());
				h.put("idTujuan", rs.getString("ID_JENISTUJUAN") == null ? "" : rs.getString("ID_JENISTUJUAN"));
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
	
	public String getIdHakmilikAgensi(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKAGENSI");
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
	
	public void setMaklumatSewa(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSewa = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANSEWA, E.ID_PHPPERMOHONANTUJUAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.TUJUAN, C.FLAG_TEMPOHSEWA, "
				+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_LUASMHNBERSAMAAN, A.ID_URUSAN, " 
				+ " C.LUAS_MHNBERSAMAAN, C.ID_LUASBAKI, C.LUAS_BAKI, C.ID_LUASASAL, C.LUAS_ASAL, E.ID_JENISTUJUAN, A.ID_SUBURUSAN "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLRUJLUAS D, TBLPHPPERMOHONANTUJUAN E"
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_PHPPERMOHONANSEWA = E.ID_PHPPERMOHONANSEWA" 
				+ " AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			log.info("sewa "+sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa", rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs.getString("ID_PHPPERMOHONANSEWA"));
				h.put("idTujuanPermohonan", rs.getString("ID_PHPPERMOHONANTUJUAN") == null ? "" : rs.getString("ID_PHPPERMOHONANTUJUAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukanSurat", rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT"));							
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));								
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("flagGuna", rs.getString("FLAG_GUNA") == null ? "" : rs.getString("FLAG_GUNA"));
				h.put("flagTempohSewa", rs.getString("FLAG_TEMPOHSEWA") == null ? "" : rs.getString("FLAG_TEMPOHSEWA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("keteranganLuasAsal", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? "" : rs.getString("ID_LUASMHN"));
				h.put("luas1", rs.getString("LUAS_MHN1") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2", rs.getString("LUAS_MHN2") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3", rs.getString("LUAS_MHN3") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan", rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("idTujuan", rs.getString("ID_JENISTUJUAN") == null ? "" : rs.getString("ID_JENISTUJUAN"));
				beanMaklumatSewa.addElement(h);
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
	
	public void setMaklumatHeader(String idFail, HttpSession session) throws Exception {
		Db db = null;
		String sql = "";
			
		try {
			beanMaklumatHeader = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;
			SQLRenderer r = new SQLRenderer();
			
//			if (!"".equals(idFail) && session.getAttribute("ID_FAIL") != null) {
//				idFail = (String) session.getAttribute("ID_FAIL");
//			}

			sql = " SELECT A.ID_FAIL,F.ID_HAKMILIKPERMOHONAN, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN, B.TARIKH_TERIMA, "
				+ " CASE WHEN ID_PERMOHONANLAMA IS NULL THEN 'PERMOHONAN BARU' ELSE 'PERMOHONAN PERLANJUTAN' END AS JENISPERMOHONAN, "
				+ " B.TARIKH_SURAT, C.ID_PEMOHON, C.NAMA, C.ID_NEGERITETAP, C.ID_KATEGORIPEMOHON, C.ID_PEJABAT, " 
				+ " I.ID_NEGERI AS ID_NEGERITANAH, H.ID_KEMENTERIAN AS ID_KEMENTERIANTANAH, "
				+ " H.ID_AGENSI AS ID_AGENSITANAH, J.KEPUTUSAN, J.FLAG_PROSESFAIL,"
				+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, " 
				+ " G.KETERANGAN AS NAMA_BANDAR, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, I.NO_HAKMILIK, I.NO_WARTA, " 
				+ " H.ID_HAKMILIKAGENSI, H.ID_HAKMILIK, J.FLAG_PERMOHONANDARI, K.NAMA_URUSAN, L.ID_SUBURUSAN, " 
				+ " L.NAMA_SUBURUSAN, K.ID_URUSAN, B.FLAG_AKTIF, B.NO_SAMBUNGAN, B.NO_PERMOHONAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, " 
				+ " TBLPHPHAKMILIKPERMOHONAN F, TBLRUJBANDAR G, TBLHTPHAKMILIKAGENSI H, TBLHTPHAKMILIK I, " 
				+ " TBLPHPPERMOHONANSEWA J, TBLRUJURUSAN K, TBLRUJSUBURUSAN L"
				+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' " 
				+ " AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI(+) " 
				+ " AND B.ID_STATUS = E.ID_STATUS(+) AND B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = K.ID_URUSAN " 
				+ " AND A.ID_SUBURUSAN = L.ID_SUBURUSAN AND F.ID_HAKMILIKAGENSI = H.ID_HAKMILIKAGENSI " 
				+ " AND H.ID_HAKMILIK = I.ID_HAKMILIK AND C.ID_BANDARTETAP = G.ID_BANDAR(+) " 
				+ " AND B.ID_PERMOHONAN = J.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("aaaaaaaaaaaaa " +sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs.getString("NAMA_URUSAN").toUpperCase());
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
				h.put("jenisPermohonan", rs.getString("JENISPERMOHONAN") == null ? "" : rs.getString("JENISPERMOHONAN").toUpperCase());//yati tambah
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
					
				} else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
					h.put("status", " PENDAFTARAN");
					
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("idUrusanH", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN").toUpperCase());
				h.put("idSuburusanH", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				beanMaklumatHeader.addElement(h);
				bil++;
				
				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
			}
			
			//if (bil == 1){
			else{
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("subUrusan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("idPemohon", "");
				h.put("idStatus", "");
				h.put("status", "");
				h.put("idHakmilik", "");
				h.put("flagAktif", "");
				h.put("idHakmilikAgensi","");
				h.put("perkara", "");
				beanMaklumatHeader.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateTanah(String idPermohonan, String idHakmilik,HttpSession session) throws Exception {


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

			//TBLPHPHAKMILIKPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilik);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	   	try {
	   		conn.rollback();
	   	} catch (SQLException e) {
	   		throw new Exception("Rollback error : " + e.getMessage());
	   	}
	   	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	   	
	   } finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void updatePermohonanSewa(String idPermohonanSewa, String idTujuanPermohonan, String idSubsuburusan,  
			String socTempohSewa, String idLuasKegunaan, String idLuas, String txtLuasMohon1, String txtLuasMohon2,
			String txtLuasMohon3, String txtLuasBersamaan, String txtBakiLuas, HttpSession session) throws Exception {
		
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
					
			//TBLPHPPERMOHONANSEWA
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("FLAG_GUNA",idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);			
			r.add("ID_LUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_LUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);
			r.add("FLAG_TEMPOHSEWA", socTempohSewa);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);
			
			//TBLPHPPERMOHONANTUJUAN
			r = new SQLRenderer();
			r.update("ID_PHPPERMOHONANTUJUAN", idTujuanPermohonan);
			r.add("ID_JENISTUJUAN", idSubsuburusan);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPHPPERMOHONANTUJUAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void updatePermohonanEmel(String idFail,String idPermohonan,HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "";
		String idhakmilikPermohonan = "";
		String noPermohonan = "";
		String idSuburusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN, D.NAMA, D.EMEL " 
				+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C,TBLPHPPEMOHON D "
				+ " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "
				+ " AND A.ID_PEMOHON = D.ID_PEMOHON AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()){
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
				idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
				namaUser = rsPermohonan.getString("NAMA");
				emelUser = rsPermohonan.getString("EMEL");
			}	
			
			//TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610197");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus(idSuburusan, "1610197")); //PERMOHONAN BARU
			r.add("AKTIF", "1");	
			r.add("ID_FAIL", idFail);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			
			stmt.executeUpdate(sql);
			
			//TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "P"); //TANAH DIPOHON
			r.add("FLAG_LAPORAN", "1"); //LAWATAN TAPAK
			r.add("ID_HAKMILIK", idhakmilikPermohonan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);
			
			//TBLPHPKERTASKERJAPENYEWAAN
			r = new SQLRenderer();
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAPENYEWAAN_SEQ");
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KERTAS", "1");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			if (!"".equals(namaUser) && !"".equals(emelUser)){
				EmailConfig ef = new EmailConfig();
				
				String subject = "PERMOHONAN PENYEWAAN HARTA TANAH PERSEKUTUAN #" + noPermohonan;
				String kandungan = namaUser.toUpperCase() + "."
						+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
						+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan.";
				
				ef.sendByOnlineUser(emelUser, subject, kandungan);
			}
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void hapusPermohonan(String idFail) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLPHPPERMOHONANSEWA
			sql = "DELETE FROM TBLPHPPERMOHONANSEWA WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPHAKMILIKPERMOHONAN 
			sql = "DELETE FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPPEMOHON 
			sql = "DELETE FROM TBLPHPPEMOHON WHERE ID_PEMOHON IN "
				+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			sql = "DELETE FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPFDFAIL
			sql = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL IN (" + idFail + ")";
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	public boolean checkMaklumatPywLengkap(String idPermohonan) throws Exception{
		Db db = null;
		String sql = "";
		boolean bool = true;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT FLAG_GUNA FROM TBLPHPPERMOHONANSEWA WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				
				//SEBAHAGIAN
				if("2".equals(rs.getString("FLAG_GUNA"))){
					
					sql = "SELECT ID_PERMOHONAN FROM TBLPHPPERMOHONANSEWA WHERE "
						+ "(LUAS_MHN IS NULL OR ID_LUASMHN IS NULL OR LUAS_MHN1 IS NULL OR FLAG_TEMPOHSEWA IS NULL)"
						+ " AND ID_PERMOHONAN= '" +idPermohonan+ "'";
					ResultSet rs2 = stmt.executeQuery(sql);
					if (rs2.next()){
						bool = false;
					} else {
						bool = true;
					}
				} else if("1".equals(rs.getString("FLAG_GUNA"))){
					sql = "SELECT ID_PERMOHONAN FROM TBLPHPPERMOHONANSEWA WHERE "
						+ " (LUAS_MHN IS NULL OR FLAG_TEMPOHSEWA IS NULL)"
						+ " AND ID_PERMOHONAN= '" +idPermohonan+ "'";
					ResultSet rs1 = stmt.executeQuery(sql);
					if (rs1.next()){
						bool = false;
					} else {
						bool = true;
					}
				}
				else{
					bool = true;
				}
				
			} else {
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
	
	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran,
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
			r.add("NAMA_DOKUMEN", txtNamaLampiran);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

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
	
	public void hapusLampiran(String idDokumen, HttpSession session) throws Exception {
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
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idDokumen + "] DIHAPUSKAN");

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
	
	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, JENIS_MIME FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaLampiran", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("jenisMime",
						rs.getString("JENIS_MIME") == null ? "" : StringUtils.substringBefore(rs.getString("JENIS_MIME"), "/"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiLampiran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND FLAG_DOKUMEN = 'L'"
					+ " AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL AND ID_PENAWARANKJP IS NULL" 
					+ " ORDER BY ID_DOKUMEN DESC";

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
				listLampiran.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateSenaraiSemak(String idPermohonanSewa, String txtTujuan, String socTempohSewa, String idLuasKegunaan, String idLuas, String txtLuasMohon1, String txtLuasMohon2,
			String txtLuasMohon3, String txtLuasBersamaan, String txtBakiLuas, HttpSession session) throws Exception {
		
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
					
			//TBLPHPPERMOHONANSEWA
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("TUJUAN", txtTujuan);
			r.add("FLAG_GUNA",idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);			
			r.add("ID_LUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_LUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);
			r.add("FLAG_TEMPOHSEWA", socTempohSewa);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}		
	}
	public String getIdHTPHakmilikByIdHakmilikPermohonan(
			String idHakmilikPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HTPAGENSI.ID_HAKMILIK FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLHTPHAKMILIKAGENSI HTPAGENSI"
					+ " WHERE PHPHMP.ID_HAKMILIKAGENSI = HTPAGENSI.ID_HAKMILIKAGENSI AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//SENARAI SEMAK
	public Vector getSenaraiSemak(String idPermohonan, String kategori) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_RUJSENARAISEMAK, A.KETERANGAN,"
					+ " CASE WHEN A.ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG, "
					+ " CASE WHEN B.KETERANGAN = 'INDIVIDU' THEN '1' ELSE '2' END AS ID_KATEGORIPEMOHON "
					+ " FROM TBLPHPRUJSENARAISEMAK A, TBLRUJKATEGORIPEMOHON B "
					+ " WHERE A.ID_KATEGORIPEMOHON = B.ID_KATEGORIPEMOHON AND A.FLAG_AKTIF = 'Y' AND B.KETERANGAN = '" + kategori + "' ";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return senaraiSemak;
	}
	
	//UPDATE SENARAI SEMAK
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";		
		
		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();
			
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);
			
			for (int i = 0; i < semaks.length; i++) {
			 	r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNamaTujuan(String idJenisTujuan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_SUBSUBURUSAN AS NAMA_TUJUAN FROM TBLRUJSUBSUBURUSAN WHERE ID_SUBSUBURUSAN = '"
					+ idJenisTujuan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NAMA_TUJUAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPejabatJKPTG(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_PEJABAT, B.NO_TEL, B.NO_FAX, B.EMEL, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, C.NAMA_NEGERI, D.NAMA_DAERAH, E.KETERANGAN AS NAMA_BANDAR, "
				+ "B.ID_PEJABATJKPTG FROM TBLPERMOHONAN A, TBLRUJPEJABATJKPTG B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJBANDAR E WHERE A.ID_JKPTG = B.ID_PEJABATJKPTG "
				+ "AND B.ID_NEGERI = C.ID_NEGERI(+) AND B.ID_DAERAH = D.ID_DAERAH(+) AND B.ID_BANDAR = E.ID_BANDAR(+) AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
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
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs
						.getString("EMEL").toUpperCase());
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

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}
	
	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}
	
	public Vector getBeanMaklumatSewa() {
		return beanMaklumatSewa;
	}
	
	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}
	
	public Vector getBeanMaklumatHeader() {
		return beanMaklumatHeader;
	}
	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}
	public Vector getBeanMaklumatBorangK() {
		return beanMaklumatBorangK;
	}
	public void setBeanMaklumatBorangK(Vector beanMaklumatBorangK) {
		this.beanMaklumatBorangK = beanMaklumatBorangK;
	}
	public Vector getListLampiran() {
		return listLampiran;
	}
	public void setListLampiran(Vector listLampiran) {
		this.listLampiran = listLampiran;
	}
	
}
