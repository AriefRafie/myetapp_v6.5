package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmModelBorangJ {
	static Logger myLogger = Logger.getLogger(FrmModelBorangJ.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String SelectMahkamahByNegeri(String SOC_NAME, long SELECTED_NEGERI) throws Exception {
		return SelectMahkamahByNegeri(SOC_NAME, SELECTED_NEGERI, 0, "", "");
	}
	public String SelectMahkamahByNegeri(String SOC_NAME, long SELECTED_NEGERI, long SELECTED_MAHKAMAH) throws Exception {
		return SelectMahkamahByNegeri(SOC_NAME, SELECTED_NEGERI, SELECTED_MAHKAMAH, "", "");
	}
	public String SelectMahkamahByNegeri(String SOC_NAME, long SELECTED_NEGERI, long SELECTED_MAHKAMAH, String DISABLED) throws Exception {
		return SelectMahkamahByNegeri(SOC_NAME, SELECTED_NEGERI, SELECTED_MAHKAMAH, DISABLED, "");
	}
	public String SelectMahkamahByNegeri(String SOC_NAME, long SELECTED_NEGERI, long SELECTED_MAHKAMAH, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "WHERE ID_JENISPEJABAT = 41 ";
			
			if (SELECTED_NEGERI > 0) {
				SQL_SEARCH += "AND ID_NEGERI = " + SELECTED_NEGERI + " ";
			}
			if (SELECTED_MAHKAMAH > 0) {
				SQL_SEARCH += "AND ID_PEJABAT = " + SELECTED_MAHKAMAH + " ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + ">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_PEJABAT, KOD_PEJABAT, NAMA_PEJABAT FROM TBLRUJPEJABAT " +
				SQL_SEARCH + "ORDER BY KOD_PEJABAT, NAMA_PEJABAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_MAHKAMAH == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	
	@SuppressWarnings("unchecked")
	public List searchBorangJ(String NO_FAIL, String NO_PERMOHONAN, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List searchBorangJ = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(M.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SQL_SEARCH = " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_SIMATI)) {
				SQL_SEARCH = " AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%')";
			}
			sql = "SELECT DISTINCT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.ALAMAT_1, SM.ALAMAT_2, SM.ALAMAT_3 " +
				"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPFDFAIL FAIL, TBLRUJSUBURUSANSTATUSFAIL SUF " +
				"WHERE M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL " +
				" AND FAIL.ID_FAIL = SUF.ID_FAIL AND SUF.ID_SUBURUSANSTATUS IN (813,853) " +
				SQL_SEARCH + " ORDER BY FAIL.NO_FAIL, SM.NAMA_SIMATI";
			String ID_PERMOHONAN = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "", ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", ALAMAT = "";
			
			myLogger.info(" V3: SQL searchBorangJ :"+ sql);
			rs = stmt.executeQuery(sql);
			searchBorangJ = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 1;			
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_BARU = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_LAMA = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT1 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT2 = rs.getString(8) == null ? "" : rs.getString(8);
				ALAMAT3 = rs.getString(9) == null ? "" : rs.getString(9);
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}
				if (!"".equalsIgnoreCase(ALAMAT1)) {
					ALAMAT = ALAMAT1;
				}
				if (!"".equalsIgnoreCase(ALAMAT2)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT2;
					} else {
						ALAMAT = ALAMAT2;
					}
				}
				if (!"".equalsIgnoreCase(ALAMAT3)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT3;
					} else {
						ALAMAT = ALAMAT3;
					}
				}
				h.put("No", bil);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("Alamat", ALAMAT);								
				searchBorangJ.add(h);
				bil++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return searchBorangJ;

	}
	
	@SuppressWarnings("unchecked")
	public Vector searchBorangJ_LAMA(String NO_FAIL, String NO_PERMOHONAN, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(M.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SQL_SEARCH = " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_SIMATI)) {
				SQL_SEARCH = " AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%')";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.ALAMAT_1, SM.ALAMAT_2, SM.ALAMAT_3 " +
				"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL" +
				SQL_SEARCH + " ORDER BY FAIL.NO_FAIL, SM.NAMA_SIMATI";
			int i = 1;
			String ID_PERMOHONAN = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "", ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", ALAMAT = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_BARU = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_LAMA = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT1 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT2 = rs.getString(8) == null ? "" : rs.getString(8);
				ALAMAT3 = rs.getString(9) == null ? "" : rs.getString(9);
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}
				if (!"".equalsIgnoreCase(ALAMAT1)) {
					ALAMAT = ALAMAT1;
				}
				if (!"".equalsIgnoreCase(ALAMAT2)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT2;
					} else {
						ALAMAT = ALAMAT2;
					}
				}
				if (!"".equalsIgnoreCase(ALAMAT3)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT3;
					} else {
						ALAMAT = ALAMAT3;
					}
				}
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("Alamat", ALAMAT);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getMaklumatMahkamah(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT PJ.ALAMAT1, PJ.ALAMAT2, PJ.ALAMAT3, PJ.POSKOD, PJ.NO_TEL, PJ.NO_FAX " +
					"FROM TBLPPKPERMOHONAN PMHN, TBLPPKKEPUTUSANPERMOHONAN KPMHN, TBLPPKPERBICARAAN PBCRN, TBLPPKBORANGJ BRGJ, TBLRUJPEJABAT PJ " +
					"WHERE PMHN.ID_PERMOHONAN = KPMHN.ID_PERMOHONAN AND KPMHN.ID_KEPUTUSANPERMOHONAN = PBCRN.ID_KEPUTUSANPERMOHONAN AND PBCRN.ID_PERBICARAAN = BRGJ.ID_PERBICARAAN AND BRGJ.ID_MAHKAMAH = PJ.ID_PEJABAT " +
					"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
				myLogger.info("getMaklumatMahkamah :    "+sql);
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = new Hashtable();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", POSKOD = "", NO_TEL = "", NO_FAX = "";
					ALAMAT1 = rs.getString(1) == null ? "" : rs.getString(1);
					ALAMAT2 = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT3 = rs.getString(3) == null ? "" : rs.getString(3);
					POSKOD = rs.getString(4) == null ? "" : rs.getString(4);
					NO_TEL = rs.getString(5) == null ? "" : rs.getString(5);
					NO_FAX = rs.getString(6) == null ? "" : rs.getString(6);
					h.put("PERMOHONAN_ALAMATMAHKAMAH1", ALAMAT1);
					h.put("PERMOHONAN_ALAMATMAHKAMAH2", ALAMAT2);
					h.put("PERMOHONAN_ALAMATMAHKAMAH3", ALAMAT3);
					h.put("PERMOHONAN_POSKODMAHKAMAH", POSKOD);
					h.put("PERMOHONAN_NOTELEFONMAHKAMAH", NO_TEL);
					h.put("PERMOHONAN_NOFAKSMAHKAMAH", NO_FAX);
					v.add(h);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector viewBorangJ(String ID_PERMOHONAN, String USER_NAME) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				
				String MP_NOFAIL = "", MP_NOPERMOHONAN = "", MP_NEGERI = "", MP_DAERAH = "", MP_UNIT = "", MP_TARIKHMOHON = "", MP_NAMAPEMOHON = "";
				String MP_NOKPPEMOHON = "", MP_ALAMATPEMOHON = "", MP_NOTELPEMOHON = "", MP_NAMASIMATI = "", MP_NOKPSIMATI = "", MP_TARIKHMATI = "", MP_FAKTAGUAMAN = "", MP_PENDAPAT = "";
				String PERMOHONAN_TARIKHRUJUKAN = "", PERMOHONAN_NEGERIMAHKAMAH = "", PERMOHONAN_NAMAMAHKAMAH = "", PERMOHONAN_PEGAWAIPENGENDALI = "";
				String KEPUTUSAN_TARIKHTERIMABORANGJ = "", KEPUTUSAN_TARIKHHANTARKEPUTUSAN = "", KEPUTUSAN_KEPUTUSAN = "", KEPUTUSAN_CATATAN = "";
				String SIJILFARAID_NAMAMAHKAMAH = "", SIJILFARAID_DAERAHMAHKAMAH = "", SIJILFARAID_NEGERIMAHKAMAH = "", SIJILFARAID_NOKES = "";
				String SIJILFARAID_NAMAHAKIM = "", SIJILFARAID_HARTAPUSAKASIMATI = "", SIJILFARAID_TARIKHMATI = "", SIJILFARAID_NAMASEKSYEN = "";
				String SIJILFARAID_NAMAORDINAN = "", SIJILFARAID_KEPUTUSANFARAID = "", SIJILFARAID_TARIKHSIJILFARAID = "";
				String SEBAB_TANGGUH = "";
				String KEPUTUSAN_MAHKAMAH = "";
				String ID_PERMOHONANSIMATI = "";
				String ID_PERBICARAAN = "";
				String ID_PERINTAH = "";
				String ID_BORANGJ = "";
				
				
				
				// check TBLINTJKSM
				sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DA.NAMA_DAERAH, '', PMHN.TARIKH_MOHON, MHN.NAMA_PEMOHON, " +
					"CASE WHEN MHN.NO_KP_LAIN <> '' THEN MHN.NO_KP_LAIN ELSE CASE WHEN MHN.NO_KP_LAMA <> '' THEN MHN.NO_KP_LAMA ELSE MHN.NO_KP_BARU END END AS NO_KP_PEMOHON, " +
					"MHN.ALAMAT_1 || ', ' || MHN.ALAMAT_2 || ', ' || MHN.ALAMAT_3, MHN.NO_TEL, " +
					"SM.NAMA_SIMATI, CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, SM.TARIKH_MATI, " +
					"BRGJ.CATATAN1, BRGJ.KEPUTUSAN_MAHKAMAH, M.PERM_TARIKH_RUJUKAN, NGM.NAMA_NEGERI, PJM.NAMA_PEJABAT, PRU.NAMA_PEGAWAI, " +
					"M.KEP_TARIKH_TERIMA, M.KEP_TARIKH_KEPUTUSAN, M.KEP_KEPUTUSAN, M.KEP_CATATAN, M.SF_NAMA_MAHKAMAH, M.SF_DAERAH_MAHKAMAH, " +
					"M.SF_NEGERI_MAHKAMAH, M.SF_NO_KES, M.SF_NAMA_HAKIM, M.SF_HARTA_PUSAKA_SIMATI, M.SF_TARIKH_MATI, M.SF_NAMA_SEKSYEN, " +
					"M.SF_NAMA_ORDINAN, M.SF_KEPUTUSAN_FARAID, M.SF_TARIKH_SIJIL, PRNTH.SEBAB_TANGGUH, PRNTH.KEPUTUSAN_MAHKAMAH, PMHNSM.ID_PERMOHONANSIMATI,PBCRN.ID_PERBICARAAN,PRNTH.ID_PERINTAH, BRGJ.ID_BORANGJ " +
					"FROM TBLINTJKSM M, TBLPFDFAIL FAIL, TBLPPKPERMOHONAN PMHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, " +
					"TBLRUJNEGERI NG, TBLRUJDAERAH DA, TBLPPKKEPUTUSANPERMOHONAN KPMHN, TBLPPKPERBICARAAN PBCRN, TBLPPKBORANGJ BRGJ, TBLRUJNEGERI NGM, " +
					"TBLPPKPERINTAH PRNTH, TBLRUJPEJABAT PJM, TBLPPKRUJUNIT PRU " +
					"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN " +
					"AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON AND PMHN.ID_NEGERIMHN = NG.ID_NEGERI " +
					"AND PMHN.ID_PERMOHONAN = KPMHN.ID_PERMOHONAN AND KPMHN.ID_KEPUTUSANPERMOHONAN = PBCRN.ID_KEPUTUSANPERMOHONAN " +
					"AND PBCRN.ID_PERBICARAAN = BRGJ.ID_PERBICARAAN AND PBCRN.ID_PERBICARAAN = PRNTH.ID_PERBICARAAN " +
					"AND PRNTH.ID_UNITPSK = PRU.ID_UNITPSK(+) AND BRGJ.ID_NEGERIMAHKAMAH = NGM.ID_NEGERI AND BRGJ.ID_MAHKAMAH = PJM.ID_PEJABAT " +
					"AND PMHN.ID_DAERAHMHN = DA.ID_DAERAH AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				myLogger.info("viewBorangJ 1 : "+sql);
				
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					MP_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
					MP_NOPERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
					MP_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
					MP_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
					MP_UNIT = rs.getString(5) == null ? "" : rs.getString(5);
					MP_TARIKHMOHON = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
					MP_NAMAPEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					MP_NOKPPEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
					MP_ALAMATPEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
					MP_NOTELPEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
					MP_NAMASIMATI = rs.getString(11) == null ? "" : rs.getString(11);
					MP_NOKPSIMATI = rs.getString(12) == null ? "" : rs.getString(12);
					MP_TARIKHMATI = rs.getDate(13) == null ? "" : sdf.format(rs.getDate(13));
					MP_FAKTAGUAMAN = rs.getString(14) == null ? "" : rs.getString(14);
					MP_PENDAPAT = rs.getString(15) == null ? "" : rs.getString(15);
					PERMOHONAN_TARIKHRUJUKAN = rs.getDate(16) == null ? "" : sdf.format(rs.getDate(16));
					PERMOHONAN_NEGERIMAHKAMAH = rs.getString(17) == null ? "" : rs.getString(17);
					PERMOHONAN_NAMAMAHKAMAH = rs.getString(18) == null ? "" : rs.getString(18);
					PERMOHONAN_PEGAWAIPENGENDALI = rs.getString(19) == null ? "" : rs.getString(19);
					KEPUTUSAN_TARIKHTERIMABORANGJ = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
					KEPUTUSAN_TARIKHHANTARKEPUTUSAN = rs.getDate(21) == null ? "" : sdf.format(rs.getDate(21));
					KEPUTUSAN_KEPUTUSAN = rs.getString(22) == null ? "" : rs.getString(22);
					KEPUTUSAN_CATATAN = rs.getString(23) == null ? "" : rs.getString(23);
					SIJILFARAID_NAMAMAHKAMAH = rs.getString(24) == null ? "" : rs.getString(24);
					SIJILFARAID_DAERAHMAHKAMAH = rs.getString(25) == null ? "" : rs.getString(25);
					SIJILFARAID_NEGERIMAHKAMAH = rs.getString(26) == null ? "" : rs.getString(26);
					SIJILFARAID_NOKES = rs.getString(27) == null ? "" : rs.getString(27);
					SIJILFARAID_NAMAHAKIM = rs.getString(28) == null ? "" : rs.getString(28);
					SIJILFARAID_HARTAPUSAKASIMATI = rs.getString(29) == null ? "" : rs.getString(29); 
					SIJILFARAID_TARIKHMATI = rs.getDate(30) == null ? "" : sdf.format(rs.getDate(30)); 
					SIJILFARAID_NAMASEKSYEN = rs.getString(31) == null ? "" : rs.getString(31); 
					SIJILFARAID_NAMAORDINAN = rs.getString(32) == null ? "" : rs.getString(32); 
					SIJILFARAID_KEPUTUSANFARAID = rs.getString(33) == null ? "" : rs.getString(33); 
					SIJILFARAID_TARIKHSIJILFARAID = rs.getDate(34) == null ? "" : sdf.format(rs.getDate(34));
					SEBAB_TANGGUH = rs.getString(35) == null ? "" : rs.getString(35); 
					KEPUTUSAN_MAHKAMAH = rs.getString(36) == null ? "" : rs.getString(36); 
					ID_PERMOHONANSIMATI = rs.getString(37) == null ? "" : rs.getString(37); 
					ID_PERBICARAAN = rs.getString(38) == null ? "" : rs.getString(38); 
					ID_PERINTAH = rs.getString(39) == null ? "" : rs.getString(39); 
					ID_BORANGJ = rs.getString(40) == null ? "" : rs.getString(40); 
					
					
					
				}
				
				if (!haveINTData) {
					sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DA.NAMA_DAERAH, '', PMHN.TARIKH_MOHON, MHN.NAMA_PEMOHON, " +
						"CASE WHEN MHN.NO_KP_LAIN <> '' THEN MHN.NO_KP_LAIN ELSE CASE WHEN MHN.NO_KP_LAMA <> '' THEN MHN.NO_KP_LAMA ELSE MHN.NO_KP_BARU END END AS NO_KP_PEMOHON, " +
						"MHN.ALAMAT_1 || ', ' || MHN.ALAMAT_2 || ', ' || MHN.ALAMAT_3, MHN.NO_TEL, " +
						"SM.NAMA_SIMATI, CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, SM.TARIKH_MATI, " +
						"BRGJ.CATATAN1, BRGJ.KEPUTUSAN_MAHKAMAH, BRGJ.TARIKH_MOHON, NGM.NAMA_NEGERI, PJM.NAMA_PEJABAT, PRU.NAMA_PEGAWAI, " +
						//" '', '', '', '', " +
						" BRGJ.TARIKH_TERIMA_BORANGJ,BRGJ.TARIKH_HANTAR_BORANGJ, BRGJ.CATATAN4, BRGJ.CATATAN5, "+
						"'', '', '', '', '', '', '', '', '', '', '', PRNTH.SEBAB_TANGGUH, PRNTH.KEPUTUSAN_MAHKAMAH, PMHNSM.ID_PERMOHONANSIMATI,PBCRN.ID_PERBICARAAN,PRNTH.ID_PERINTAH, BRGJ.ID_BORANGJ  " +
						"FROM TBLPFDFAIL FAIL, TBLPPKPERMOHONAN PMHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLRUJNEGERI NG, " +
						"TBLRUJDAERAH DA, TBLPPKKEPUTUSANPERMOHONAN KPMHN, TBLPPKPERBICARAAN PBCRN, TBLPPKBORANGJ BRGJ, TBLPPKPERINTAH PRNTH, TBLPPKRUJUNIT PRU, TBLRUJNEGERI NGM, TBLRUJPEJABAT PJM " +
						"WHERE PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI " +
						"AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON AND PMHN.ID_NEGERIMHN = NG.ID_NEGERI AND PMHN.ID_DAERAHMHN = DA.ID_DAERAH " +
						"AND PMHN.ID_PERMOHONAN = KPMHN.ID_PERMOHONAN AND KPMHN.ID_KEPUTUSANPERMOHONAN = PBCRN.ID_KEPUTUSANPERMOHONAN " +
						"AND PBCRN.ID_PERBICARAAN = BRGJ.ID_PERBICARAAN AND PBCRN.ID_PERBICARAAN = PRNTH.ID_PERBICARAAN " +
						"AND PRNTH.ID_UNITPSK = PRU.ID_UNITPSK(+) AND BRGJ.ID_NEGERIMAHKAMAH = NGM.ID_NEGERI(+) AND BRGJ.ID_MAHKAMAH = PJM.ID_PEJABAT(+) " +
						"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
					myLogger.info("viewBorangJ 2 : "+sql);
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						haveINTData = false;
						MP_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
						MP_NOPERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
						MP_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
						MP_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
						MP_UNIT = rs.getString(5) == null ? "" : rs.getString(5);
						MP_TARIKHMOHON = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
						MP_NAMAPEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
						MP_NOKPPEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
						MP_ALAMATPEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
						MP_NOTELPEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
						MP_NAMASIMATI = rs.getString(11) == null ? "" : rs.getString(11);
						MP_NOKPSIMATI = rs.getString(12) == null ? "" : rs.getString(12);
						MP_TARIKHMATI = rs.getDate(13) == null ? "" : sdf.format(rs.getDate(13));
						MP_FAKTAGUAMAN = rs.getString(14) == null ? "" : rs.getString(14);
						MP_PENDAPAT = rs.getString(15) == null ? "" : rs.getString(15);
						PERMOHONAN_TARIKHRUJUKAN = rs.getDate(16) == null ? "" : sdf.format(rs.getDate(16));
						PERMOHONAN_NEGERIMAHKAMAH = rs.getString(17) == null ? "" : rs.getString(17);
						PERMOHONAN_NAMAMAHKAMAH = rs.getString(18) == null ? "" : rs.getString(18);
						PERMOHONAN_PEGAWAIPENGENDALI = rs.getString(19) == null ? "" : rs.getString(19);
						
						KEPUTUSAN_TARIKHTERIMABORANGJ = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
						KEPUTUSAN_TARIKHHANTARKEPUTUSAN = rs.getDate(21) == null ? "" : sdf.format(rs.getDate(21));
						KEPUTUSAN_KEPUTUSAN = rs.getString(22) == null ? "" : rs.getString(22);
						KEPUTUSAN_CATATAN = rs.getString(23) == null ? "" : rs.getString(23);
						
						SIJILFARAID_NAMAMAHKAMAH = rs.getString(24) == null ? "" : rs.getString(24);
						SIJILFARAID_DAERAHMAHKAMAH = rs.getString(25) == null ? "" : rs.getString(25);
						SIJILFARAID_NEGERIMAHKAMAH = rs.getString(26) == null ? "" : rs.getString(26);
						SIJILFARAID_NOKES = rs.getString(27) == null ? "" : rs.getString(27);
						SIJILFARAID_NAMAHAKIM = rs.getString(28) == null ? "" : rs.getString(28);
						SIJILFARAID_HARTAPUSAKASIMATI = rs.getString(29) == null ? "" : rs.getString(29); 
						SIJILFARAID_TARIKHMATI = rs.getDate(30) == null ? "" : sdf.format(rs.getDate(30)); 
						SIJILFARAID_NAMASEKSYEN = rs.getString(31) == null ? "" : rs.getString(31); 
						SIJILFARAID_NAMAORDINAN = rs.getString(32) == null ? "" : rs.getString(32); 
						SIJILFARAID_KEPUTUSANFARAID = rs.getString(33) == null ? "" : rs.getString(33); 
						SIJILFARAID_TARIKHSIJILFARAID = rs.getDate(34) == null ? "" : sdf.format(rs.getDate(34));
						SEBAB_TANGGUH = rs.getString(35) == null ? "" : rs.getString(35); 
						KEPUTUSAN_MAHKAMAH = rs.getString(36) == null ? "" : rs.getString(36); 
						ID_PERMOHONANSIMATI = rs.getString(37) == null ? "" : rs.getString(37); 
						ID_PERBICARAAN = rs.getString(38) == null ? "" : rs.getString(38); 
						ID_PERINTAH = rs.getString(39) == null ? "" : rs.getString(39); 
						ID_BORANGJ = rs.getString(40) == null ? "" : rs.getString(40); 
					}
				}
				
				h = new Hashtable();
				h.put("haveINTData", haveINTData);
				h.put("MP_NOFAIL", MP_NOFAIL);
				h.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
				h.put("MP_NEGERI", MP_NEGERI);
				h.put("MP_DAERAH", MP_DAERAH);
				h.put("MP_UNIT", MP_UNIT);
				h.put("MP_TARIKHMOHON", MP_TARIKHMOHON);
				h.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
				h.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
				h.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
				h.put("MP_NOTELPEMOHON", MP_NOTELPEMOHON);
				h.put("MP_NAMASIMATI", MP_NAMASIMATI);
				h.put("MP_NOKPSIMATI", MP_NOKPSIMATI);
				h.put("MP_TARIKHMATI", MP_TARIKHMATI);
				h.put("MP_FAKTAGUAMAN", MP_FAKTAGUAMAN);
				h.put("MP_PENDAPAT", MP_PENDAPAT);
				h.put("PERMOHONAN_TARIKHRUJUKAN", PERMOHONAN_TARIKHRUJUKAN);
				h.put("PERMOHONAN_NEGERIMAHKAMAH", PERMOHONAN_NEGERIMAHKAMAH);
				h.put("PERMOHONAN_NAMAMAHKAMAH", PERMOHONAN_NAMAMAHKAMAH);
				h.put("PERMOHONAN_PEGAWAIPENGENDALI", PERMOHONAN_PEGAWAIPENGENDALI);
				h.put("KEPUTUSAN_TARIKHTERIMABORANGJ", KEPUTUSAN_TARIKHTERIMABORANGJ);
				h.put("KEPUTUSAN_TARIKHHANTARKEPUTUSAN", KEPUTUSAN_TARIKHHANTARKEPUTUSAN);
				h.put("KEPUTUSAN_KEPUTUSAN", KEPUTUSAN_KEPUTUSAN);
				h.put("KEPUTUSAN_CATATAN", KEPUTUSAN_CATATAN);
				h.put("SIJILFARAID_NAMAMAHKAMAH", SIJILFARAID_NAMAMAHKAMAH);
				h.put("SIJILFARAID_DAERAHMAHKAMAH", SIJILFARAID_DAERAHMAHKAMAH);
				h.put("SIJILFARAID_NEGERIMAHKAMAH", SIJILFARAID_NEGERIMAHKAMAH);
				h.put("SIJILFARAID_NOKES", SIJILFARAID_NOKES);
				h.put("SIJILFARAID_NAMAHAKIM", SIJILFARAID_NAMAHAKIM);
				h.put("SIJILFARAID_HARTAPUSAKASIMATI", SIJILFARAID_HARTAPUSAKASIMATI);
				h.put("SIJILFARAID_TARIKHMATI", SIJILFARAID_TARIKHMATI);
				h.put("SIJILFARAID_NAMASEKSYEN", SIJILFARAID_NAMASEKSYEN);
				h.put("SIJILFARAID_NAMAORDINAN", SIJILFARAID_NAMAORDINAN);
				h.put("SIJILFARAID_KEPUTUSANFARAID", SIJILFARAID_KEPUTUSANFARAID);
				h.put("SIJILFARAID_TARIKHSIJILFARAID", SIJILFARAID_TARIKHSIJILFARAID);
				h.put("SEBAB_TANGGUH", SEBAB_TANGGUH);
				h.put("KEPUTUSAN_MAHKAMAH", KEPUTUSAN_MAHKAMAH);				
				h.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
				h.put("ID_PERBICARAAN", ID_PERBICARAAN);
				h.put("ID_PERINTAH", ID_PERINTAH);
				h.put("ID_BORANGJ", ID_BORANGJ);
				
				
				
				
				v.add(h);
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector viewBorangJWaris(String ID_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_SIMATI)) {
				String sql = "";
				sql = "SELECT M.NAMA_OB, M.ALAMAT_1, M.ALAMAT_2, M.ALAMAT_3, SAUDARA.KETERANGAN " +
					"FROM TBLPPKOB M, TBLPPKRUJSAUDARA SAUDARA " +
					"WHERE M.ID_SAUDARA = SAUDARA.ID_SAUDARA(+) " +
					"AND M.ID_SIMATI = " + ID_SIMATI + " ORDER BY M.NAMA_OB";
				int i = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String NAMA_OB = "", ALAMAT_1 = "", ALAMAT_2 = "", ALAMAT_3 = "", KETERANGAN = "", ALAMAT = "";
					NAMA_OB = rs.getString(1) == null ? "" : rs.getString(1);
					ALAMAT_1 = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT_2 = rs.getString(3) == null ? "" : rs.getString(3);
					ALAMAT_3 = rs.getString(4) == null ? "" : rs.getString(4);
					KETERANGAN = rs.getString(5) == null ? "" : rs.getString(5);
					if (!"".equalsIgnoreCase(ALAMAT_1)) {
						ALAMAT = ALAMAT_1;
					}
					if (!"".equalsIgnoreCase(ALAMAT_2)) {
						if (!"".equalsIgnoreCase(ALAMAT)) {
							ALAMAT += ", " + ALAMAT_2;
						} else {
							ALAMAT = ALAMAT_2;
						}
					}
					if (!"".equalsIgnoreCase(ALAMAT_3)) {
						if (!"".equalsIgnoreCase(ALAMAT)) {
							ALAMAT += ", " + ALAMAT_3;
						} else {
							ALAMAT = ALAMAT_3;
						}
					}
					h = new Hashtable();
					h.put("No", i);
					h.put("Nama", NAMA_OB);
					h.put("Alamat", ALAMAT);
					h.put("HubunganOB", KETERANGAN);
					v.add(h);
					i++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean saveBorangJ(String ID_PERMOHONAN, String USER_ID, Hashtable h) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(USER_ID) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				long ID_JKSM = 0;
				String ID_FAIL = "", ID_NEGERI = "", ID_DAERAH = "", ID_UNIT = "", ID_SIMATI = "", ID_PEMOHON = "", PERM_TARIKH_RUJUKAN = "";
				String PERM_IDNEGERIMAHKAMAH = "", PERM_IDMAHKAMAH = "", PERM_IDPEGAWAI = "";
				
				sql = "SELECT PMHN.ID_FAIL, PMHN.ID_NEGERIMHN, PMHN.ID_DAERAHMHN, '', PMHNSM.ID_SIMATI, PMHN.ID_PEMOHON, '', BRGJ.ID_NEGERIMAHKAMAH, BRGJ.ID_MAHKAMAH, PRNTH.ID_UNITPSK " +
					"FROM TBLPPKPERMOHONAN PMHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKKEPUTUSANPERMOHONAN KPMHN, TBLPPKPERBICARAAN PBCRN, TBLPPKBORANGJ BRGJ, TBLPPKPERINTAH PRNTH " +
					"WHERE PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHN.ID_PERMOHONAN = KPMHN.ID_PERMOHONAN AND KPMHN.ID_KEPUTUSANPERMOHONAN = PBCRN.ID_KEPUTUSANPERMOHONAN " +
					"AND PBCRN.ID_PERBICARAAN = BRGJ.ID_PERBICARAAN AND BRGJ.ID_PERBICARAAN = PRNTH.ID_PERBICARAAN AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
				myLogger.info(" maklumatBorangJ :  "+sql);
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
					ID_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
					ID_UNIT = rs.getString(4) == null ? "" : rs.getString(4);
					ID_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
					ID_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
					PERM_TARIKH_RUJUKAN = rs.getDate(7) == null ? "" : sdf.format(rs.getDate(7));
					PERM_IDNEGERIMAHKAMAH = rs.getString(8) == null ? "" : rs.getString(8);
					PERM_IDMAHKAMAH = rs.getString(9) == null ? "" : rs.getString(9);
					PERM_IDPEGAWAI = rs.getString(10) == null ? "" : rs.getString(10);
				}
				
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJKSM");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				
				if (haveData) {
					r.update("ID_PERMOHONAN", ID_PERMOHONAN);
				} else {
					r.add("ID_MASUK", USER_ID);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				}
				r.add("ID_KEMASKINI", USER_ID);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("STATUS_PROSES", "DIHANTAR KE JKSM");
				r.add("ID_FAIL", ID_FAIL);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_UNIT", ID_UNIT);
				r.add("ID_SIMATI", ID_SIMATI);
				r.add("ID_PEMOHON", ID_PEMOHON);
				r.add("PERM_TARIKH_RUJUKAN", r.unquote("TO_DATE('" + PERM_TARIKH_RUJUKAN + "', 'dd/MM/yyyy')"));
				r.add("PERM_IDNEGERIMAHKAMAH", PERM_IDNEGERIMAHKAMAH);
				r.add("PERM_IDMAHKAMAH", PERM_IDMAHKAMAH);
				r.add("PERM_IDPEGAWAI", PERM_IDPEGAWAI);
				if (haveData) {
					sql = r.getSQLUpdate("TBLINTJKSM");
				} else {
					ID_JKSM = DB.getNextID("TBLINTJKSM_SEQ");
					r.add("ID_JKSM", ID_JKSM);
					sql = r.getSQLInsert("TBLINTJKSM");
				}
				returnValue = stmt.execute(sql);
				
				// SAVE INTO TBLINTFILEUPLOAD
				Vector vINT = new Vector();
				Hashtable hINT = null;
				long ID_UPLOAD = 0;
				String NAMA_PROSES = "JKSM", STATUS_PROSES = "BARU", JUMLAH_INPUT = "12";
				String NO_PERMOHONAN = "", NAMA_SIMATI = "", NO_KP_SIMATI = "", NAMA_PEMOHON = "", NO_KP_PEMOHON = "";
				String NAMA_WARIS = "", NO_KP_WARIS = "", ALAMAT1_WARIS = "", ALAMAT2_WARIS = "", ALAMAT3_WARIS = "";
				String KOD_PERSAUDARAAN = "", KETERANGAN_PERSAUDARAAN = "";
				sql = "SELECT PMHN.NO_PERMOHONAN, SM.NAMA_SIMATI, CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, MHN.NAMA_PEMOHON, " +
					"CASE WHEN MHN.NO_KP_LAIN <> '' THEN MHN.NO_KP_LAIN ELSE CASE WHEN MHN.NO_KP_LAMA <> '' THEN MHN.NO_KP_LAMA ELSE MHN.NO_KP_BARU END END AS NO_KP_PEMOHON, OB.NAMA_OB, CASE WHEN OB.NO_KP_LAIN <> '' THEN OB.NO_KP_LAIN ELSE CASE WHEN OB.NO_KP_LAMA <> '' THEN OB.NO_KP_LAMA ELSE OB.NO_KP_BARU END END AS NO_KP_WARIS, " +
					"OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, RS.KOD, RS.KETERANGAN " +
					"FROM TBLINTJKSM M, TBLPPKPERMOHONAN PMHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLPPKOB OB, TBLPPKRUJSAUDARA RS " +
					"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON AND PMHNSM.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI AND OB.ID_SAUDARA = RS.ID_SAUDARA " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NO_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					NAMA_SIMATI = rs.getString(2) == null ? "" : rs.getString(2);
					NO_KP_SIMATI = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
					NO_KP_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
					NAMA_WARIS = rs.getString(6) == null ? "" : rs.getString(6);
					NO_KP_WARIS = rs.getString(7) == null ? "" : rs.getString(7);
					ALAMAT1_WARIS = rs.getString(8) == null ? "" : rs.getString(8);
					ALAMAT2_WARIS = rs.getString(9) == null ? "" : rs.getString(9);
					ALAMAT3_WARIS = rs.getString(10) == null ? "" : rs.getString(10);
					KOD_PERSAUDARAAN = rs.getString(11) == null ? "" : rs.getString(11);
					KETERANGAN_PERSAUDARAAN = rs.getString(12) == null ? "" : rs.getString(12);
					hINT = new Hashtable();
					hINT.put("NO_PERMOHONAN", NO_PERMOHONAN);
					hINT.put("NAMA_SIMATI", NAMA_SIMATI);
					hINT.put("NO_KP_SIMATI", NO_KP_SIMATI);
					hINT.put("NAMA_PEMOHON", NAMA_PEMOHON);
					hINT.put("NO_KP_PEMOHON", NO_KP_PEMOHON);
					hINT.put("NAMA_WARIS", NAMA_WARIS);
					hINT.put("NO_KP_WARIS", NO_KP_WARIS);
					hINT.put("ALAMAT1_WARIS", ALAMAT1_WARIS);
					hINT.put("ALAMAT2_WARIS", ALAMAT2_WARIS);
					hINT.put("ALAMAT3_WARIS", ALAMAT3_WARIS);
					hINT.put("KOD_PERSAUDARAAN", KOD_PERSAUDARAAN);
					hINT.put("KETERANGAN_PERSAUDARAAN", KETERANGAN_PERSAUDARAAN);
					vINT.add(hINT);
				}
				
				int i = 0;
				for (i = 0; i <= vINT.size() - 1; i++) {
					hINT = (Hashtable) vINT.get(i);
					NO_PERMOHONAN = (String) hINT.get("NO_PERMOHONAN");
					NAMA_SIMATI = (String) hINT.get("NAMA_SIMATI");
					NO_KP_SIMATI = (String) hINT.get("NO_KP_SIMATI");
					NAMA_PEMOHON = (String) hINT.get("NAMA_PEMOHON");
					NO_KP_PEMOHON = (String) hINT.get("NO_KP_PEMOHON");
					NAMA_WARIS = (String) hINT.get("NAMA_WARIS");
					NO_KP_WARIS = (String) hINT.get("NO_KP_WARIS");
					ALAMAT1_WARIS = (String) hINT.get("ALAMAT1_WARIS");
					ALAMAT2_WARIS = (String) hINT.get("ALAMAT2_WARIS");
					ALAMAT3_WARIS = (String) hINT.get("ALAMAT3_WARIS");
					KOD_PERSAUDARAAN = (String) hINT.get("KOD_PERSAUDARAAN");
					KETERANGAN_PERSAUDARAAN = (String) hINT.get("KETERANGAN_PERSAUDARAAN");

					// kalau ada dlm table TBLINTFILEUPLOAD, update. kalau tak, insert baru
					haveData = false;
					r.clear();
					r.add("INPUT_01");
					r.add("INPUT_01", NO_PERMOHONAN);
					r.add("STATUS_PROSES", STATUS_PROSES);
					r.add("INPUT_07", NO_KP_WARIS);
					sql = r.getSQLSelect("TBLINTFILEUPLOAD");
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						haveData = true;
					}
					
					r.clear();
					if (haveData) {
						r.update("INPUT_01", NO_PERMOHONAN);
						r.update("INPUT_07", NO_KP_WARIS);
					}
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("NAMA_PROSES", NAMA_PROSES);
					r.add("STATUS_PROSES", STATUS_PROSES);
					r.add("JUMLAH_INPUT", JUMLAH_INPUT);
					r.add("INPUT_01", NO_PERMOHONAN);
					r.add("INPUT_02", NAMA_SIMATI);
					r.add("INPUT_03", NO_KP_SIMATI);
					r.add("INPUT_04", NAMA_PEMOHON);
					r.add("INPUT_05", NO_KP_PEMOHON);
					r.add("INPUT_06", NAMA_WARIS);
					r.add("INPUT_07", NO_KP_WARIS);
					r.add("INPUT_08", ALAMAT1_WARIS);
					r.add("INPUT_09", ALAMAT2_WARIS);
					r.add("INPUT_10", ALAMAT3_WARIS);
					r.add("INPUT_11", KOD_PERSAUDARAAN);
					r.add("INPUT_12", KETERANGAN_PERSAUDARAAN);
					if (haveData) {
						sql = r.getSQLUpdate("TBLINTFILEUPLOAD");
					} else {
						ID_UPLOAD = DB.getNextID("TBLINTFILEUPLOAD_SEQ");
						r.add("ID_UPLOAD", ID_UPLOAD);
						r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
						sql = r.getSQLInsert("TBLINTFILEUPLOAD");
					}
					r.clear();
					stmt.execute(sql);
					returnValue = true;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteBorangJ(String ID_PERMOHONAN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";

				r.clear();
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJKSM");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					r.clear();
					r.add("ID_PERMOHONAN", ID_PERMOHONAN);
					sql = r.getSQLDelete("TBLINTJKSM");
					stmt.execute(sql);
					returnValue = true;
				}

				String NO_PERMOHONAN = "";
				r.clear();
				r.add("PMHN.NO_PERMOHONAN");
				r.add("PMHN.ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("FU.INPUT_01", r.unquote("PMHN.NO_PERMOHONAN"));
				sql = r.getSQLSelect("TBLINTFILEUPLOAD FU, TBLPPKPERMOHONAN PMHN");				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
						r.clear();
						r.add("INPUT_01", NO_PERMOHONAN);
						sql = r.getSQLDelete("TBLINTFILEUPLOAD");
						stmt.execute(sql);
						returnValue = true;
					}
				}
			}
		} finally {
			if (db != null) 
				db.close();
		}
		return returnValue;
	}
	
	public String getUnitUser(String USER_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_ID)) {
				String sql = "";
				sql = "SELECT UPPER(RJ.NAMA_PEJABAT) " +
					"FROM USERS U, USERS_INTERNAL I, TBLRUJPEJABATJKPTG RJ " +
					"WHERE U.USER_ID = I.USER_ID AND I.ID_PEJABATJKPTG = RJ.ID_PEJABATJKPTG " +
					"AND U.USER_ID = " + USER_ID;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
}