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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.model.ppt.FrmSek8PampasanData;

/**
 * 
 *
 */
public class FrmPYWOnlineSenaraiFailData {
	
	static Logger myLogger = Logger.getLogger(FrmSek8PampasanData.class);
	private Vector senaraiFail = null;	
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatHeader = null;
	private Vector beanMaklumatSewa = null;
	private static final Log log = LogFactory.getLog(FrmPYWOnlineSenaraiFailData.class);
	
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
			
			System.out.println("*** senaraiPerjanjian = "+sql);
			
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
			
			System.out.println("*** setListAkaun : "+sql);

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
			
			myLogger.info("SQL setListDeposit : "+sql.toUpperCase());
			
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
            
                        System.out.println(" SQL xx PEMOHON LIST FROM MODEL PEMBAYARAN :"+sql);
                        ResultSet rs = stmt.executeQuery(sql);

                        Hashtable h;
                        int BIL = 1;
                        while (rs.next()) {

                            h = new Hashtable();
                            h.put("BIL", BIL);
                            System.out.println(" ada no fail :	 :"+rs.getString("NO_FAIL"));
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
                        
                        System.out.println(" SENARAI USER LIST FROM MODEL PEMBAYARAN SEWA :"+status_pembayaranSewa);
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
	
	public Vector statusPermohonanSewa(String findNoFail, String findNoHakmilik, String findNoLot, String id_user) throws Exception {
		
		Vector status_PermohonanSewa = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*sql = " SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, A.NO_FAIL_NEGERI, C.NAMA, TO_CHAR (B.TARIKH_TERIMA, 'DD/MM/YYYY') AS TARIKH_TERIMA, A.ID_URUSAN, A.ID_SUBURUSAN, RUJURUSAN.NAMA_URUSAN, "+
					" RUJSUBURUSAN.NAMA_SUBURUSAN, TO_CHAR (B.TARIKH_SURAT, 'DD/MM/YYYY') AS TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, "+
					" (CASE WHEN B.ID_STATUS = 1610212 THEN D.KETERANGAN ELSE 'DALAM PROSES' END ) AS STATUS, "+
					" MOHONSEWA.TUJUAN, MOHONSEWA.FLAG_TEMPOHSEWA, MOHONSEWA.LUAS_MHNBERSAMAAN, "+
					" C.NO_PENGENALAN, C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, RUJBANDAR.KETERANGAN AS NAMA_BANDAR, "+
					" RUJNEGERI.NAMA_NEGERI, C.NO_TEL, C.NO_FAX, F.PEGANGAN_HAKMILIK, F.NO_LOT, F.LUAS, F.NO_HAKMILIK, F.NO_WARTA, TO_CHAR (F.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "+
					" RUJMUKIM.NAMA_MUKIM, RUJDAERAH.NAMA_DAERAH, RUJKATEGORI.KETERANGAN AS KATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, "+
					" F.SYARAT, F.SEKATAN, F.KEGUNAAN_TANAH, RUJKEMENTERIAN.NAMA_KEMENTERIAN, RUJAGENSI.NAMA_AGENSI, B.CATATAN_BATAL, "+
					" TO_CHAR (B.TARIKH_BATAL, 'DD/MM/YYYY') AS TARIKH_BATAL "+
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, "+
					" USERS H, USERS_ONLINE I, TBLRUJNEGERI RUJNEGERI, TBLRUJBANDAR RUJBANDAR, TBLPHPPERMOHONANSEWA MOHONSEWA, "+
					" TBLRUJURUSAN RUJURUSAN, TBLRUJSUBURUSAN RUJSUBURUSAN, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, "+
					" TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJKEMENTERIAN RUJKEMENTERIAN, TBLRUJAGENSI RUJAGENSI"+
					" WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (7, 12, 13) AND B.FLAG_PERJANJIAN = 'U' AND A.ID_FAIL = B.ID_FAIL(+) "+
					" AND B.ID_STATUS = D.ID_STATUS AND B.ID_STATUS = D.ID_STATUS(+) AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+) "+
					" AND B.ID_PEMOHON = C.ID_PEMOHON(+) AND C.ID_BANDARTETAP = RUJBANDAR.ID_BANDAR(+) AND C.ID_NEGERITETAP = RUJNEGERI.ID_NEGERI(+)"+
					" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_PERMOHONAN = MOHONSEWA.ID_PERMOHONAN AND B.FLAG_AKTIF = 'Y' "+
					" AND A.NO_FAIL IS NOT NULL AND E.FLAG_HAKMILIK = 'U' AND A.ID_URUSAN = RUJURUSAN.ID_URUSAN(+) "+
					" AND A.ID_SUBURUSAN = RUJSUBURUSAN.ID_SUBURUSAN(+) AND I.NO_KP_BARU = C.NO_PENGENALAN AND F.ID_MUKIM = RUJMUKIM.ID_MUKIM "+
					" AND F.ID_DAERAH = RUJDAERAH.ID_DAERAH AND F.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI AND F.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI "+
					" AND F.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN AND F.ID_AGENSI = RUJAGENSI.ID_AGENSI AND I.USER_ID = '"+id_user+"' ";*/
			
			sql = " SELECT TBLPFDFAIL.ID_FAIL, TBLPERMOHONAN.ID_PERMOHONAN, TBLPHPPEMOHON.ID_PEMOHON, TBLPERMOHONAN.ID_STATUS, "+
					" TBLPFDFAIL.NO_FAIL, TBLPFDFAIL.NO_FAIL_NEGERI, TO_CHAR(TBLPERMOHONAN.TARIKH_TERIMA, 'DD/MM/YYYY') AS TARIKH_TERIMA, "+
					" (CASE WHEN TBLPERMOHONAN.ID_STATUS IN ('1610212','1610195','1610222') THEN TBLRUJSTATUS.KETERANGAN ELSE 'DALAM PROSES' END ) AS STATUS, "+
					" TBLRUJURUSAN.NAMA_URUSAN, TBLRUJSUBURUSAN.NAMA_SUBURUSAN, TO_CHAR(TBLPERMOHONAN.TARIKH_SURAT, 'DD/MM/YYYY') AS TARIKH_SURAT, "+
					" TBLPERMOHONAN.NO_RUJ_SURAT, TBLPFDFAIL.TAJUK_FAIL, "+
					" UPPER(TBLRUJLOT.KETERANGAN) || ' ' || TBLPHPHAKMILIK.NO_LOT AS NO_LOT, TBLPHPHAKMILIK.LUAS || ' ' || UPPER(TBLRUJLUAS.KETERANGAN) AS LUAS_LOT, "+
					" TBLPHPHAKMILIK.NO_WARTA, TO_CHAR(TBLPHPHAKMILIK.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, TBLPHPHAKMILIK.NO_HAKMILIK, "+
					" TBLRUJMUKIM.NAMA_MUKIM, TBLRUJDAERAH.NAMA_DAERAH, TBLRUJNEGERI.NAMA_NEGERI, "+
					" TBLRUJKATEGORI.KETERANGAN AS KATEGORI_TANAH, TBLRUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI_TANAH, TBLPHPHAKMILIK.KEGUNAAN_TANAH, "+
					" TBLRUJAGENSI.NAMA_AGENSI, TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN, TBLPHPPERMOHONANSEWA.FLAG_TEMPOHSEWA, "+
					" TBLPHPHAKMILIK.SYARAT, TBLPHPHAKMILIK.SEKATAN, TBLPERMOHONAN.TARIKH_BATAL, TBLPERMOHONAN.CATATAN_BATAL, TBLPHPPERMOHONANSEWA.LUAS_MHNBERSAMAAN "+
					" FROM TBLPFDFAIL, TBLPERMOHONAN, TBLPHPPEMOHON, USERS_ONLINE, TBLRUJSTATUS, TBLRUJURUSAN, TBLRUJSUBURUSAN, TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK, "+
					" TBLRUJLOT, TBLRUJLUAS, TBLRUJNEGERI, TBLRUJDAERAH, TBLRUJMUKIM, TBLRUJKATEGORI, TBLRUJSUBKATEGORI, TBLRUJKEMENTERIAN, TBLRUJAGENSI, TBLPHPPERMOHONANSEWA "+
					" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON "+
					" AND TBLPHPPEMOHON.NO_PENGENALAN = USERS_ONLINE.NO_KP_BARU AND TBLPERMOHONAN.ID_STATUS = TBLRUJSTATUS.ID_STATUS(+) "+
					" AND TBLPFDFAIL.ID_SEKSYEN = 4 AND TBLPFDFAIL.ID_URUSAN IN (7, 12, 13) "+
					" AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN(+) AND TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN(+) "+
					" AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK = 'U' "+
					" AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN "+
					" AND TBLPHPHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+) AND TBLPHPHAKMILIK.ID_LUAS = TBLRUJLUAS.ID_LUAS(+) "+
					" AND TBLPHPHAKMILIK.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPHPHAKMILIK.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH AND TBLPHPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM "+
					" AND TBLPHPHAKMILIK.ID_KATEGORI = TBLRUJKATEGORI.ID_KATEGORI(+) AND TBLPHPHAKMILIK.ID_SUBKATEGORI = TBLRUJSUBKATEGORI.ID_SUBKATEGORI(+) "+
					" AND TBLPHPHAKMILIK.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN(+) AND TBLPHPHAKMILIK.ID_AGENSI = TBLRUJAGENSI.ID_AGENSI(+) "+
					" AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANSEWA.ID_PERMOHONAN "+
					" AND USERS_ONLINE.USER_ID = '"+id_user+"' ";

			if (findNoFail != null) {
				if (!findNoFail.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPFDFAIL.NO_FAIL) LIKE '%' ||'"
							+ findNoFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoHakmilik != null) {
				if (!findNoHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.NO_HAKMILIK) LIKE '%' ||'"
							+ findNoHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoLot != null) {
				if (!findNoLot.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.NO_LOT) LIKE '%' ||'"
							+ findNoLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			        
			System.out.println(" SQL PEMOHON LIST FROM MODEL PERMOHONAN SEWA 1 :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("bil", bil);
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_PEMOHON", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				//h.put("ID_STATUS,", rs.getString("ID_STATUS,") == null ? "" : rs.getString("ID_STATUS,"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_FAIL_NEGERI", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("TARIKH_TERIMA", rs.getString("TARIKH_TERIMA") == null ? "" : rs.getString("TARIKH_TERIMA"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("NAMA_URUSAN", rs.getString("NAMA_URUSAN") == null ? "" : rs.getString("NAMA_URUSAN"));
				h.put("NAMA_SUBURUSAN", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN"));
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? "" : rs.getString("TARIKH_SURAT"));
				h.put("NO_RUJ_SURAT", rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT"));
				h.put("TAJUK_FAIL", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? "" : rs.getString("LUAS_LOT"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("KATEGORI_TANAH", rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH"));
				h.put("SUBKATEGORI_TANAH", rs.getString("SUBKATEGORI_TANAH") == null ? "" : rs.getString("SUBKATEGORI_TANAH"));
				h.put("KEGUNAAN_TANAH", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH"));
				h.put("NAMA_AGENSI", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI"));
				h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				h.put("FLAG_TEMPOHSEWA", rs.getString("FLAG_TEMPOHSEWA") == null ? "" : rs.getString("FLAG_TEMPOHSEWA"));
				h.put("SYARAT", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
				h.put("SEKATAN", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
				h.put("TARIKH_BATAL", rs.getString("TARIKH_BATAL") == null ? "" : rs.getString("TARIKH_BATAL"));
				h.put("CATATAN_BATAL", rs.getString("CATATAN_BATAL") == null ? "" : rs.getString("CATATAN_BATAL"));
				h.put("LUAS_MHNBERSAMAAN", rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : rs.getString("LUAS_MHNBERSAMAAN"));
				
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
		
				
				System.out.println("DATA PERMOHONAN :"+sql.toUpperCase());
				
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
				System.out.println(" SENARAI USER LIST FROM MODEL PPT LIST PERMOHONAN :"+listPohon);
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

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.NO_PERMOHONAN, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS, H.USER_LOGIN, B.NO_SAMBUNGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLHTPHAKMILIKAGENSI F, TBLHTPHAKMILIK G, USERS H"
				+ " WHERE A.ID_URUSAN IN (7,12,13) AND A.FLAG_JENIS_FAIL = '4' AND B.FLAG_PERJANJIAN = 'U' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
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
			
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
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
	
	public void setMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
				+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
				+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN"
				+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
				+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
				+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
				+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK"));
				h.put("noLot", rs.getString("JENIS_LOT") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("JENIS_LOT") + " " + rs.getString("NO_LOT"));
				h.put("luasLot", rs.getString("LUAS_BERSAMAAN") == null || rs.getString("JENIS_LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN")) + " " + rs.getString("JENIS_LUAS_BERSAMAAN"));
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
	
	public String daftarBaru(String idUrusan, String idSuburusan, String idHakmilikAgensi, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String idHakmilik = "";
		String idKementerian = "";
		String idNegeriHakmilik = "";
		String idLuas = "";
		String luas = "";
		String namaUser = "";
		String emelUser = "";		

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN, TBLHTPHAKMILIK.ID_NEGERI, TBLHTPHAKMILIKAGENSI.ID_LUAS_BERSAMAAN, TBLHTPHAKMILIKAGENSI.LUAS_BERSAMAAN, TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rsTanah = stmt.executeQuery(sql);
			if (rsTanah.next()){
				idKementerian = rsTanah.getString("ID_KEMENTERIAN");
				idNegeriHakmilik = rsTanah.getString("ID_NEGERI");
				idLuas = rsTanah.getString("ID_LUAS_BERSAMAAN");
				luas = rsTanah.getString("LUAS_BERSAMAAN");
				idHakmilik = rsTanah.getString("ID_HAKMILIK");
			}			
			
			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
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
						
			sql = "SELECT A.USER_NAME, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_NEGERI,B.NO_FAX, B.NO_HP,"
				+ " B.NO_KP_BARU, B.NO_TEL, B.EMEL "
				+ " FROM USERS A, USERS_ONLINE B"
				+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			
			ResultSet rsUser = stmt.executeQuery(sql);

			//TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", "2");
			if (rsUser.next()){
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
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "");
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			
			Calendar currentDate = new GregorianCalendar();			
			String noPermohonan = "JKPTG/SPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/" + currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);
			r.add("NO_PERMOHONAN", noPermohonan);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PERJANJIAN", "U");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLPHPPERMOHONANSEWA
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
			if ("12".equals(idUrusan)){
				r.add("TUJUAN", "Mengeluarkan hasil " + getNamaSuburusan(idSuburusan));
			}
			r.add("FLAG_PROSESFAIL", "J");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERMOHONANSEWA");
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
	    session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
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
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_URUSAN, A.ID_SUBURUSAN, B.ID_PERMOHONAN, B.NO_PERMOHONAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
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

			sql = "SELECT C.ID_PHPPERMOHONANSEWA, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.TUJUAN, C.FLAG_TEMPOHSEWA, C.ID_LUASASAL, C.LUAS_ASAL,"
				+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_LUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_LUASBAKI, C.LUAS_BAKI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLRUJLUAS D"
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa", rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs.getString("ID_PHPPERMOHONANSEWA"));
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
	
	public void setMaklumatHeader(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHeader = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT A.ID_FAIL,F.ID_HAKMILIKPERMOHONAN, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, C.ID_PEMOHON, C.NAMA, C.ID_NEGERITETAP, C.ID_KATEGORIPEMOHON, C.ID_PEJABAT, I.ID_NEGERI AS ID_NEGERITANAH, H.ID_KEMENTERIAN AS ID_KEMENTERIANTANAH, H.ID_AGENSI AS ID_AGENSITANAH, J.KEPUTUSAN, J.FLAG_PROSESFAIL,"
				+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, G.KETERANGAN AS NAMA_BANDAR, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, I.NO_HAKMILIK, I.NO_WARTA, H.ID_HAKMILIKAGENSI, H.ID_HAKMILIK, J.FLAG_PERMOHONANDARI, K.NAMA_URUSAN, L.ID_SUBURUSAN, L.NAMA_SUBURUSAN, K.ID_URUSAN, B.FLAG_AKTIF, B.NO_SAMBUNGAN, B.NO_PERMOHONAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPHAKMILIKPERMOHONAN F, TBLRUJBANDAR G, TBLHTPHAKMILIKAGENSI H, TBLHTPHAKMILIK I, TBLPHPPERMOHONANSEWA J, TBLRUJURUSAN K, TBLRUJSUBURUSAN L"
				+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI(+) AND B.ID_STATUS = E.ID_STATUS(+) AND B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = K.ID_URUSAN AND A.ID_SUBURUSAN = L.ID_SUBURUSAN"
				+ " AND F.ID_HAKMILIKAGENSI = H.ID_HAKMILIKAGENSI AND H.ID_HAKMILIK = I.ID_HAKMILIK AND C.ID_BANDARTETAP = G.ID_BANDAR(+) AND B.ID_PERMOHONAN = J.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
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
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
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
			}
			
			if (bil == 1){
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
	
	public void updatePermohonanSewa(String idPermohonanSewa, String txtTujuan, String socTempohSewa, String idLuasKegunaan, String idLuas, String txtLuasMohon1, String txtLuasMohon2,
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
						
			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN " 
				+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C WHERE "
				+ " C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()){
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
				idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
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
				XEkptgEmailSender email = XEkptgEmailSender.getInstance();
				email.FROM = "etapp_webmaster@kptg.gov.my";
				email.RECIEPIENT = emelUser;				
				email.SUBJECT = "PERMOHONAN PENYEWAAN HARTA TANAH PERSEKUTUAN #" + noPermohonan;
				email.MESSAGE = namaUser.toUpperCase() + "."
								+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
								+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan."
								+ "<br><br>Terima Kasih.";
				email.sendEmail();
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
}
