package ekptg.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.intergration.XEkptgEmailSender;

public class FrmModelNilaianHartaTakAlih {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchHTA(String NO_FAIL, String NO_PERMOHONAN, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
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
	
	public String getIDPermohonan(String NO_FAIL) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				String sql = "";
				sql = "SELECT M.ID_PERMOHONAN " +
					"FROM TBLPPKPERMOHONAN M, TBLPFDFAIL FA " +
					"WHERE M.ID_FAIL = FA.ID_FAIL " +
					"AND FA.NO_FAIL = '" + NO_FAIL + "'";
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
	
	public String getIDHTAFromIDPermohonanNoFail(String ID_PERMOHONAN, String NO_FAIL, String selectedTab) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(NO_FAIL)) {
				if ("1".equalsIgnoreCase(selectedTab)) {
					selectedTab = " AND HTA.JENIS_HTA != 'Y'";
				} else {
					selectedTab = " AND HTA.JENIS_HTA = 'Y'";
				}
					
				String sql = "";
				sql = "SELECT HTA.ID_HTA " +
					"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPFDFAIL FA, TBLPPKHTA HTA " +
					"WHERE M.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND M.ID_FAIL = FA.ID_FAIL AND PMHNSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " +
					"AND FA.NO_FAIL = '" + NO_FAIL + "'" + selectedTab;
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
	
	public Boolean isPermohonanSelesai(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA) && !"".equalsIgnoreCase(JENIS_HTA)) {
				String sql = "", TEMP = "", CHILD_TABLE = "TBLINTJPPHAH";
				if ("1".equalsIgnoreCase(JENIS_HTA)) {
					CHILD_TABLE = "TBLINTJPPHTH";
				}
				sql = "SELECT UPPER(C.STATUS_PROSES) " +
					"FROM TBLINTJPPH M, " + CHILD_TABLE + " C " +
					"WHERE M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND C.ID_HTA = " + ID_HTA;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					TEMP = rs.getString(1) == null ? "" : rs.getString(1);
				}
				if ("SELESAI".equalsIgnoreCase(TEMP)) {
					returnValue = true;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector viewHTAMaklumatPermohonan(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, M.NO_PERMOHONAN, M.ID_NEGERIMHN, M.ID_DAERAHMHN, '', NEGERI.NAMA_NEGERI, " +
					"DAERAH.NAMA_DAERAH, '', SM.ID_SIMATI, SM.NAMA_SIMATI, SM.TARIKH_MATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, " +
					"M.ID_PEMOHON, PEMOHON.NAMA_PEMOHON, PEMOHON.NO_KP_BARU, PEMOHON.NO_KP_LAMA, PEMOHON.ALAMAT_1, PEMOHON.ALAMAT_2, " +
					"PEMOHON.ALAMAT_3, PEMOHON.NO_TEL, M.TARIKH_MOHON " +
					"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL FAIL, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, " +
					"TBLPPKSIMATI SM, TBLPPKPEMOHON PEMOHON " +
					"WHERE M.ID_FAIL = FAIL.ID_FAIL AND M.ID_NEGERIMHN = NEGERI.ID_NEGERI(+) AND M.ID_DAERAHMHN = DAERAH.ID_DAERAH(+) " +
					"AND M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI " +
					"AND M.ID_PEMOHON = PEMOHON.ID_PEMOHON AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String ID_FAIL = "", NO_FAIL = "", NO_PERMOHONAN = "", ID_NEGERI = "", ID_DAERAH = "", ID_UNIT = "", NAMA_NEGERI = "";
					String NAMA_DAERAH = "", NAMA_UNIT = "", ID_SIMATI = "", NAMA_SIMATI = "", TARIKH_MATI = "", NO_KP_BARU_SIMATI = "";
					String NO_KP_LAMA_SIMATI = "", NO_KP_SIMATI = "", ID_PEMOHON = "", NAMA_PEMOHON = "", NO_KP_BARU_PEMOHON = "";
					String NO_KP_LAMA_PEMOHON = "", NO_KP_PEMOHON = "", ALAMAT1_PEMOHON = "", ALAMAT2_PEMOHON = "", ALAMAT3_PEMOHON = "";
					String ALAMAT_PEMOHON = "", NO_TEL_PEMOHON = "", TARIKH_MOHON = "";
					ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
					ID_NEGERI = rs.getString(4) == null ? "" : rs.getString(4);
					ID_DAERAH = rs.getString(5) == null ? "" : rs.getString(5);
					ID_UNIT = rs.getString(6) == null ? "" : rs.getString(6);
					NAMA_NEGERI = rs.getString(7) == null ? "" : rs.getString(7);
					NAMA_DAERAH = rs.getString(8) == null ? "" : rs.getString(8);
					NAMA_UNIT = rs.getString(9) == null ? "" : rs.getString(9);
					ID_SIMATI = rs.getString(10) == null ? "" : rs.getString(10);
					NAMA_SIMATI = rs.getString(11) == null ? "" : rs.getString(11);
					TARIKH_MATI = rs.getDate(12) == null ? "" : sdf.format(rs.getDate(12));
					NO_KP_BARU_SIMATI = rs.getString(13) == null ? "" : rs.getString(13);
					NO_KP_LAMA_SIMATI = rs.getString(14) == null ? "" : rs.getString(14);
					ID_PEMOHON = rs.getString(15) == null ? "" : rs.getString(15);
					NAMA_PEMOHON = rs.getString(16) == null ? "" : rs.getString(16);
					NO_KP_BARU_PEMOHON = rs.getString(17) == null ? "" : rs.getString(17);
					NO_KP_LAMA_PEMOHON = rs.getString(18) == null ? "" : rs.getString(18);
					ALAMAT1_PEMOHON = rs.getString(19) == null ? "" : rs.getString(19);
					ALAMAT2_PEMOHON = rs.getString(20) == null ? "" : rs.getString(20);
					ALAMAT3_PEMOHON = rs.getString(21) == null ? "" : rs.getString(21);
					NO_TEL_PEMOHON = rs.getString(22) == null ? "" : rs.getString(22);
					TARIKH_MOHON = rs.getDate(23) == null ? "" : sdf.format(rs.getDate(23));
					if (!"".equalsIgnoreCase(NO_KP_BARU_SIMATI)) {
						NO_KP_SIMATI = NO_KP_BARU_SIMATI;
					} else {
						NO_KP_SIMATI = NO_KP_LAMA_SIMATI;
					}
					if (!"".equalsIgnoreCase(NO_KP_BARU_PEMOHON)) {
						NO_KP_PEMOHON = NO_KP_BARU_PEMOHON;
					} else {
						NO_KP_PEMOHON = NO_KP_LAMA_PEMOHON;
					}
					if (!"".equalsIgnoreCase(ALAMAT1_PEMOHON)) {
						ALAMAT_PEMOHON = ALAMAT1_PEMOHON;
					}
					if (!"".equalsIgnoreCase(ALAMAT2_PEMOHON)) {
						if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
							ALAMAT_PEMOHON += ", " + ALAMAT2_PEMOHON;
						} else {
							ALAMAT_PEMOHON = ALAMAT2_PEMOHON;
						}
					}
					if (!"".equalsIgnoreCase(ALAMAT3_PEMOHON)) {
						if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
							ALAMAT_PEMOHON += ", " + ALAMAT3_PEMOHON;
						} else {
							ALAMAT_PEMOHON = ALAMAT3_PEMOHON;
						}
					}
					h = new Hashtable();
					h.put("MP_IDFAIL", ID_FAIL);
					h.put("MP_NOFAIL", NO_FAIL);
					h.put("MP_NOPERMOHONAN", NO_PERMOHONAN);
					h.put("MP_IDNEGERI", ID_NEGERI);
					h.put("MP_IDDAERAH", ID_DAERAH);
					h.put("MP_IDUNIT", ID_UNIT);
					h.put("MP_NEGERI", NAMA_NEGERI);
					h.put("MP_DAERAH", NAMA_DAERAH);
					h.put("MP_UNIT", NAMA_UNIT);
					h.put("MP_IDSIMATI", ID_SIMATI);
					h.put("MP_NAMASIMATI", NAMA_SIMATI);
					h.put("MP_NOKPSIMATI", NO_KP_SIMATI);
					h.put("MP_TARIKHMATI", TARIKH_MATI);
					h.put("MP_IDPEMOHON", ID_PEMOHON);
					h.put("MP_NAMAPEMOHON", NAMA_PEMOHON);
					h.put("MP_NOKPPEMOHON", NO_KP_PEMOHON);
					h.put("MP_ALAMAT1PEMOHON", ALAMAT1_PEMOHON);
					h.put("MP_ALAMAT2PEMOHON", ALAMAT2_PEMOHON);
					h.put("MP_ALAMAT3PEMOHON", ALAMAT3_PEMOHON);
					h.put("MP_ALAMATPEMOHON", ALAMAT_PEMOHON);
					h.put("MP_NOTELPEMOHON", NO_TEL_PEMOHON);
					h.put("MP_TARIKHMOHON", TARIKH_MOHON);
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
	public Vector viewNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String selectedTab, Boolean isJPPHUser) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				// check kat table integrasi dulu, kalau takde baru check kat table PPK
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				String NILAIAN_PADA_TARIKH_MATI = "", ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", SEKSYEN = "", JENIS_HAKMILIK = "";
				String NO_HAKMILIK = "", NO_PTLOT = "", JENIS_PTLOT = "", NO_BANGUNAN = "", NO_TINGKAT = "", NO_PETAK = "", BA_SIMATI = "";
				String BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "", TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "";
				String SEKATAN_KEPENTINGAN = "", ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "", LUAS_PETAK = "", LUAS_TANAH = "", UNIT_LUAS_TANAH = "";
				String JENIS_HARTA_DINILAI = "", NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "";
				String CATATAN = "", NAMA_PEGAWAI_JKPTG = "", EMAIL_ADDR_JKPTG = "", EMAIL_SEND_JKPTG = "", EMAIL_ADDR_JPPH = "", EMAIL_SEND_JPPH = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NILAI_TARIKH_MATI = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "", JPPH_TARIKH_NILAIAN = "";
				
				if ("0".equalsIgnoreCase(selectedTab)) {
					sql = "SELECT NILAIAN_PADA_TARIKH_MATI, ID_NEGERI, ID_DAERAH, ID_MUKIM, SEKSYEN, JENIS_HAKMILIK, NO_HAKMILIK, NO_PTLOT, JENIS_PTLOT, " +
					"NO_BANGUNAN, NO_TINGKAT, NO_PETAK, BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, " +
					"SYARAT_NYATA, SEKATAN_KEPENTINGAN, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, LUAS_PETAK, LUAS_TANAH, UNIT_LUAS_TANAH, JENIS_HARTA_DINILAI, " +
					"CATATAN, NAMA_PEGAWAI_JKPTG, EMAIL_ADDR_JKPTG, EMAIL_SEND_JKPTG, EMAIL_ADDR_JPPH, EMAIL_SEND_JPPH, " +
					"JPPH_NILAI_TARIKH_MOHON, JPPH_NILAI_TARIKH_MATI, JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN, JPPH_TARIKH_NILAIAN " +
					"FROM TBLINTJPPHAH WHERE ID_HTA = " + ID_HTA;
				} else {
					sql = "SELECT NILAIAN_PADA_TARIKH_MATI, ID_NEGERI, ID_DAERAH, ID_MUKIM, SEKSYEN, '', '', NO_PTLOT, JENIS_PTLOT, " +
					"NO_BANGUNAN, NO_TINGKAT, NO_PETAK, BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, " +
					"SYARAT_NYATA, SEKATAN_KEPENTINGAN, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, LUAS_PETAK, NAMA_PEMILIK, TARIKH_PERJANJIAN, NAMA_PEMAJU, " +
					"CATATAN, NAMA_PEGAWAI_JKPTG, EMAIL_ADDR_JKPTG, EMAIL_SEND_JKPTG, EMAIL_ADDR_JPPH, EMAIL_SEND_JPPH, " +
					"JPPH_NILAI_TARIKH_MOHON, JPPH_NILAI_TARIKH_MATI, JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN, JPPH_TARIKH_NILAIAN " +
					"FROM TBLINTJPPHTH WHERE ID_HTA = " + ID_HTA;
				}
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					NILAIAN_PADA_TARIKH_MATI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
					ID_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
					ID_MUKIM = rs.getString(4) == null ? "" : rs.getString(4);
					SEKSYEN = rs.getString(5) == null ? "" : rs.getString(5);
					JENIS_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
					NO_HAKMILIK = rs.getString(7) == null ? "" : rs.getString(7);
					NO_PTLOT = rs.getString(8) == null ? "" : rs.getString(8);
					JENIS_PTLOT = rs.getString(9) == null ? "" : rs.getString(9);
					NO_BANGUNAN = rs.getString(10) == null ? "" : rs.getString(10);
					NO_TINGKAT = rs.getString(11) == null ? "" : rs.getString(11);
					NO_PETAK = rs.getString(12) == null ? "" : rs.getString(12);
					BA_SIMATI = rs.getString(13) == null ? "" : rs.getString(13);
					BB_SIMATI = rs.getString(14) == null ? "" : rs.getString(14);
					JENIS_PEGANGAN = rs.getString(15) == null ? "" : rs.getString(15);
					TARIKH_LUPUT_PAJAKAN = rs.getDate(16) == null ? "" : sdf.format(rs.getDate(16));
					TEMPOH_PAJAKAN = rs.getString(17) == null ? "" : rs.getString(17);
					KATEGORI_TANAH = rs.getString(18) == null ? "" : rs.getString(18);
					SYARAT_NYATA = rs.getString(19) == null ? "" : rs.getString(19);
					SEKATAN_KEPENTINGAN = rs.getString(20) == null ? "" : rs.getString(20);
					ALAMAT1_HARTA = rs.getString(21) == null ? "" : rs.getString(21);
					ALAMAT2_HARTA = rs.getString(22) == null ? "" : rs.getString(22);
					ALAMAT3_HARTA = rs.getString(23) == null ? "" : rs.getString(23);
					LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
					
					if ("0".equalsIgnoreCase(selectedTab)) {
						LUAS_TANAH = rs.getString(25) == null ? "" : rs.getString(25);
						UNIT_LUAS_TANAH = rs.getString(26) == null ? "" : rs.getString(26);
						JENIS_HARTA_DINILAI = rs.getString(27) == null ? "" : rs.getString(27);						
					} else {
						NAMA_PEMILIK = rs.getString(25) == null ? "" : rs.getString(25);
						TARIKH_PERJANJIAN = rs.getDate(26) == null ? "" : sdf.format(rs.getDate(26));
						NAMA_PEMAJU = rs.getString(27) == null ? "" : rs.getString(27);
					}
					CATATAN = rs.getString(28) == null ? "" : rs.getString(28);
					NAMA_PEGAWAI_JKPTG = rs.getString(29) == null ? "" : rs.getString(29);
					EMAIL_ADDR_JKPTG = rs.getString(30) == null ? "" : rs.getString(30);
					EMAIL_SEND_JKPTG = rs.getString(31) == null ? "" : rs.getString(31);
					EMAIL_ADDR_JPPH = rs.getString(32) == null ? "" : rs.getString(32);
					EMAIL_SEND_JPPH = rs.getString(33) == null ? "" : rs.getString(33);
					JPPH_NILAI_TARIKH_MOHON = rs.getString(34) == null ? "" : rs.getString(34);
					JPPH_NILAI_TARIKH_MATI = rs.getString(35) == null ? "" : rs.getString(35);
					JPPH_NAMA_PEGAWAI = rs.getString(36) == null ? "" : rs.getString(36);
					JPPH_CAWANGAN_JPPH = rs.getString(37) == null ? "" : rs.getString(37);
					JPPH_CATATAN = rs.getString(38) == null ? "" : rs.getString(38);
					JPPH_TARIKH_NILAIAN = rs.getDate(39) == null ? "" : sdf.format(rs.getDate(39));
				}
				
				if (!haveINTData) {
					if ("0".equalsIgnoreCase(selectedTab)) {
						sql = "SELECT '', HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, '', HTA.ID_JENISHM, HTA.NO_HAKMILIK, HTA.NO_PT, '', HTA.NO_BANGUNAN, " +
						"HTA.NO_TINGKAT, HTA.NO_STRATA, HTA.BA_SIMATI, HTA.BB_SIMATI, '', '', '', HTA.ID_KATEGORI, '', '', HTA.ALAMAT_HTA1, HTA.ALAMAT_HTA2, HTA.ALAMAT_HTA3, '', " +
						"HTA.LUAS, HTA.ID_LUAS, '', '', '', '', '', '', '', '', '', '', '', '', ''" +
						"FROM TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM, TBLPPKSIMATI SM, TBLRUJJENISHAKMILIK JHM, TBLRUJKATEGORI KATEGORI " +
						"WHERE HTA.ID_NEGERI = NEGERI.ID_NEGERI AND HTA.ID_DAERAH = DAERAH.ID_DAERAH AND HTA.ID_MUKIM = MUKIM.ID_MUKIM AND HTA.ID_SIMATI = SM.ID_SIMATI AND HTA.ID_KATEGORI = KATEGORI.ID_KATEGORI(+) " +
						"AND HTA.ID_JENISHM = JHM.ID_JENISHAKMILIK AND HTA.JENIS_HTA = 'Y' AND HTA.ID_HTA = " + ID_HTA;
					} else {
						sql = "SELECT '', HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, '', HTA.ID_JENISHM, HTA.NO_HAKMILIK, HTA.NO_PT, '', HTA.NO_BANGUNAN, " +
						"HTA.NO_TINGKAT, HTA.NO_STRATA, HTA.BA_SIMATI, HTA.BB_SIMATI, '', '', '', HTA.ID_KATEGORI, '', '', HTA.ALAMAT_HTA1, HTA.ALAMAT_HTA2, HTA.ALAMAT_HTA3, '', " +
						"'', HTA.TARIKH_PERJANJIAN, HTA.NAMA_PEMAJU, '', '', '', '', '', '', '', '', '', '', '', ''" +
						"FROM TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM, TBLPPKSIMATI SM, TBLRUJJENISHAKMILIK JHM, TBLRUJKATEGORI KATEGORI " +
						"WHERE HTA.ID_NEGERI = NEGERI.ID_NEGERI AND HTA.ID_DAERAH = DAERAH.ID_DAERAH AND HTA.ID_MUKIM = MUKIM.ID_MUKIM AND HTA.ID_SIMATI = SM.ID_SIMATI AND HTA.ID_KATEGORI = KATEGORI.ID_KATEGORI(+) " +
						"AND HTA.ID_JENISHM = JHM.ID_JENISHAKMILIK AND HTA.JENIS_HTA != 'Y' AND HTA.ID_HTA = " + ID_HTA;
					}
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NILAIAN_PADA_TARIKH_MATI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
						ID_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
						ID_MUKIM = rs.getString(4) == null ? "" : rs.getString(4);
						SEKSYEN = rs.getString(5) == null ? "" : rs.getString(5);
						JENIS_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
						NO_HAKMILIK = rs.getString(7) == null ? "" : rs.getString(7);
						NO_PTLOT = rs.getString(8) == null ? "" : rs.getString(8);
						JENIS_PTLOT = rs.getString(9) == null ? "" : rs.getString(9);
						NO_BANGUNAN = rs.getString(10) == null ? "" : rs.getString(10);
						NO_TINGKAT = rs.getString(11) == null ? "" : rs.getString(11);
						NO_PETAK = rs.getString(12) == null ? "" : rs.getString(12);
						BA_SIMATI = rs.getString(13) == null ? "" : rs.getString(13);
						BB_SIMATI = rs.getString(14) == null ? "" : rs.getString(14);
						JENIS_PEGANGAN = rs.getString(15) == null ? "" : rs.getString(15);
						TARIKH_LUPUT_PAJAKAN = rs.getDate(16) == null ? "" : sdf.format(rs.getDate(16));
						TEMPOH_PAJAKAN = rs.getString(17) == null ? "" : rs.getString(17);
						KATEGORI_TANAH = rs.getString(18) == null ? "" : rs.getString(18);
						SYARAT_NYATA = rs.getString(19) == null ? "" : rs.getString(19);
						SEKATAN_KEPENTINGAN = rs.getString(20) == null ? "" : rs.getString(20);
						ALAMAT1_HARTA = rs.getString(21) == null ? "" : rs.getString(21);
						ALAMAT2_HARTA = rs.getString(22) == null ? "" : rs.getString(22);
						ALAMAT3_HARTA = rs.getString(23) == null ? "" : rs.getString(23);
						LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
						
						if ("0".equalsIgnoreCase(selectedTab)) {
							LUAS_TANAH = rs.getString(25) == null ? "" : rs.getString(25);
							UNIT_LUAS_TANAH = rs.getString(26) == null ? "" : rs.getString(26);
							JENIS_HARTA_DINILAI = rs.getString(27) == null ? "" : rs.getString(27);						
						} else {
							NAMA_PEMILIK = rs.getString(25) == null ? "" : rs.getString(25);
							TARIKH_PERJANJIAN = rs.getDate(26) == null ? "" : sdf.format(rs.getDate(26));
							NAMA_PEMAJU = rs.getString(27) == null ? "" : rs.getString(27);
						}
						CATATAN = rs.getString(28) == null ? "" : rs.getString(28);
						NAMA_PEGAWAI_JKPTG = rs.getString(29) == null ? "" : rs.getString(29);
						EMAIL_ADDR_JKPTG = rs.getString(30) == null ? "" : rs.getString(30);
						EMAIL_SEND_JKPTG = rs.getString(31) == null ? "" : rs.getString(31);
						EMAIL_ADDR_JPPH = rs.getString(32) == null ? "" : rs.getString(32);
						EMAIL_SEND_JPPH = rs.getString(33) == null ? "" : rs.getString(33);
						JPPH_NILAI_TARIKH_MOHON = rs.getString(34) == null ? "" : rs.getString(34);
						JPPH_NILAI_TARIKH_MATI = rs.getString(35) == null ? "" : rs.getString(35);
						JPPH_NAMA_PEGAWAI = rs.getString(36) == null ? "" : rs.getString(36);
						JPPH_CAWANGAN_JPPH = rs.getString(37) == null ? "" : rs.getString(37);
						JPPH_CATATAN = rs.getString(38) == null ? "" : rs.getString(38);
						JPPH_TARIKH_NILAIAN = rs.getDate(39) == null ? "" : sdf.format(rs.getDate(39));
					}
				}
				h = new Hashtable();
				h.put("PERLU_NILAIAN_TARIKH_MATI", NILAIAN_PADA_TARIKH_MATI);
				h.put("ID_NEGERI", ID_NEGERI);
				h.put("ID_DAERAH", ID_DAERAH);
				h.put("ID_MUKIM", ID_MUKIM);
				h.put("SEKSYEN", SEKSYEN);
				h.put("JENIS_HAKMILIK", JENIS_HAKMILIK);
				h.put("NO_HAKMILIK", NO_HAKMILIK);
				h.put("NO_PTLOT", NO_PTLOT);
				h.put("JENIS_PTLOT", JENIS_PTLOT);
				h.put("NO_BANGUNAN", NO_BANGUNAN);
				h.put("NO_TINGKAT", NO_TINGKAT);
				h.put("NO_PETAK", NO_PETAK);
				h.put("BA_SIMATI", BA_SIMATI);
				h.put("BB_SIMATI", BB_SIMATI);
				h.put("JENIS_PEGANGAN", JENIS_PEGANGAN);
				h.put("TARIKH_LUPUT_PAJAKAN", TARIKH_LUPUT_PAJAKAN);
				h.put("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
				h.put("KATEGORI_TANAH", KATEGORI_TANAH);
				h.put("SYARAT_NYATA", SYARAT_NYATA);
				h.put("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
				h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
				h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
				h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
				h.put("LUAS_PETAK", LUAS_PETAK);

				if ("0".equalsIgnoreCase(selectedTab)) {
					h.put("LUAS_TANAH", LUAS_TANAH);
					h.put("UNIT_LUAS_TANAH", UNIT_LUAS_TANAH);
					h.put("JENIS_HARTA_DINILAI", JENIS_HARTA_DINILAI);
				} else {
					h.put("NAMA_PEMILIK", NAMA_PEMILIK);
					h.put("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
					h.put("NAMA_PEMAJU", NAMA_PEMAJU);
				}
				h.put("CATATAN", CATATAN);
				h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("EMAIL_ADDR_JKPTG", EMAIL_ADDR_JKPTG);
				h.put("EMAIL_SEND_JKPTG", EMAIL_SEND_JKPTG);
				h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
				h.put("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
				h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("JPPH_TARIKH_NILAIAN", JPPH_TARIKH_NILAIAN);
				h.put("EMAIL_ADDR_JPPH", EMAIL_ADDR_JPPH);
				h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				h.put("haveINTData", haveINTData.toString());
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean sendNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA, Boolean isJPPHUser) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				Statement stmt = db.getStatement();
				String sql = "";
				
				String STATUS = "";
				if (isJPPHUser) {
					STATUS = "SELESAI";
				} else {
					STATUS = "BARU";
				}
				
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					sql = "UPDATE TBLINTJPPHAH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;
				} else {
					sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;
				}
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean saveNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String USER_ID, String JENIS_HTA, Boolean isJPPHUser, Hashtable h) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				String CHILD_TABLE = "TBLINTJPPHAH";
				String CHILD_TABLE_ID = "ID_JPPHAH";
				if ("1".equalsIgnoreCase(JENIS_HTA)) {
					CHILD_TABLE = "TBLINTJPPHTH";
					CHILD_TABLE_ID = "ID_JPPHTH";
				}
				
				// save in TBLINTJPPH first
				long ID_JPPH = 0;
				String TARIKH_SIMPAN = "", TARIKH_KEMASKINI = "";
				String NO_PERMOHONAN = "", ID_FAIL = "", NO_FAIL = "", ID_NEGERI = "", ID_DAERAH = "", ID_UNIT = "";
				String ID_PEMOHON = "", NAMA_PEMOHON = "", NO_KP_PEMOHON = "", ALAMAT1_PEMOHON = "", ALAMAT2_PEMOHON = "", ALAMAT3_PEMOHON = "";
				String NO_TEL_PEMOHON = "", TARIKH_MOHON = "";
				String ID_SIMATI = "", NAMA_SIMATI = "", NO_KP_SIMATI = "", TARIKH_MATI = "";
				
				// get latest data from TBLPPKPERMOHONAN, TBLPPKPEMOHON and TBLPPKSIMATI to be saved in TBLINTJPPH
				sql = "SELECT M.NO_PERMOHONAN, M.ID_FAIL, FAIL.NO_FAIL, M.ID_NEGERIMHN, M.ID_DAERAHMHN, '' AS UNIT, PMHN.ID_PEMOHON, PMHN.NAMA_PEMOHON, " +
					"CASE WHEN PMHN.NO_KP_BARU = '' THEN CASE WHEN PMHN.NO_KP_LAMA = '' THEN PMHN.NO_KP_LAIN ELSE PMHN.NO_KP_LAMA END ELSE PMHN.NO_KP_BARU END AS NO_KP, " +
					"PMHN.ALAMAT_1, PMHN.ALAMAT_2, PMHN.ALAMAT_3, PMHN.NO_TEL, M.TARIKH_MOHON, SM.ID_SIMATI, SM.NAMA_SIMATI, " +
					"CASE WHEN SM.NO_KP_BARU = '' THEN CASE WHEN SM.NO_KP_LAMA = '' THEN SM.NO_KP_LAIN ELSE SM.NO_KP_LAMA END ELSE SM.NO_KP_BARU END AS NO_KP_SIMATI, " +
					"SM.TARIKH_MATI " +
					"FROM TBLPPKPERMOHONAN M, TBLPFDFAIL FAIL, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLPPKPEMOHON PMHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM " +
					"WHERE M.ID_FAIL = FAIL.ID_FAIL AND M.ID_NEGERIMHN = NEGERI.ID_NEGERI AND M.ID_DAERAHMHN = DAERAH.ID_DAERAH AND M.ID_PEMOHON = PMHN.ID_PEMOHON AND M.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					ID_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
					ID_NEGERI = rs.getString(4) == null ? "" : rs.getString(4);
					ID_DAERAH = rs.getString(5) == null ? "" : rs.getString(5);
					ID_UNIT = rs.getString(6) == null ? "" : rs.getString(6);
					ID_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					NAMA_PEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
					NO_KP_PEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
					ALAMAT1_PEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
					ALAMAT2_PEMOHON = rs.getString(11) == null ? "" : rs.getString(11);
					ALAMAT3_PEMOHON = rs.getString(12) == null ? "" : rs.getString(12);
					NO_TEL_PEMOHON = rs.getString(13) == null ? "" : sdf.format(rs.getDate(13));
					TARIKH_MOHON = rs.getString(14) == null ? "" : sdf.format(rs.getDate(14));
					ID_SIMATI = rs.getString(15) == null ? "" : rs.getString(15);
					NAMA_SIMATI = rs.getString(16) == null ? "" : rs.getString(16);
					NO_KP_SIMATI = rs.getString(17) == null ? "" : rs.getString(17);
					TARIKH_MATI = rs.getString(18) == null ? "" : sdf.format(rs.getDate(18));
				}
				
				// does the data already exist? if does, then overwrite. else create new.
				haveData = false;
				r.clear();
				r.add("ID_JPPH");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJPPH");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_JPPH = rs.getInt(1);
					haveData = true;
				}
				rs.close();
				
				// save the latest retrieved data into TBLINTJPPH
				r.clear();
				if (haveData) {
					r.update("ID_JPPH", ID_JPPH);
				} else {
					ID_JPPH = DB.getNextID("TBLINTJPPH_SEQ");
					r.add("ID_JPPH", ID_JPPH);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				}
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("NO_PERMOHONAN", NO_PERMOHONAN);
				r.add("ID_FAIL", ID_FAIL);
				r.add("NO_FAIL", NO_FAIL);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_UNIT", ID_UNIT);
				r.add("ID_PEMOHON", ID_PEMOHON);
				r.add("NAMA_PEMOHON", NAMA_PEMOHON);
				r.add("NO_KP_PEMOHON", NO_KP_PEMOHON);
				r.add("ALAMAT1_PEMOHON", ALAMAT1_PEMOHON);
				r.add("ALAMAT2_PEMOHON", ALAMAT2_PEMOHON);
				r.add("ALAMAT3_PEMOHON", ALAMAT3_PEMOHON);
				r.add("NO_TEL_PEMOHON", NO_TEL_PEMOHON);
				r.add("TARIKH_MOHON", r.unquote("TO_DATE('" + TARIKH_MOHON + "', 'dd/MM/yyyy')"));
				r.add("ID_SIMATI", ID_SIMATI);
				r.add("NAMA_SIMATI", NAMA_SIMATI);
				r.add("NO_KP_SIMATI", NO_KP_SIMATI);
				r.add("TARIKH_MATI", r.unquote("TO_DATE('" + TARIKH_MATI + "', 'dd/MM/yyyy')"));
				if (haveData) {
					// data exists, overwrite
					sql = r.getSQLUpdate("TBLINTJPPH");
				} else {
					// data does not exist, create new
					sql = r.getSQLInsert("TBLINTJPPH");
				}
				//stmt.close();
				//stmt = db.getStatement();
				stmt.executeQuery(sql);
				
				// save in child table
				long ID_JPPHTH = 0;
				String STATUS_PROSES = "DALAM PROSES", ID_MUKIM = "";
				String SEKSYEN = "", JENIS_HM = "", NO_HAKMILIK = "", NO_PTLOT = "", JENIS_PTLOT = "", NO_BANGUNAN = "", NO_TINGKAT = "", NO_PETAK = "", BA_SIMATI = "", BB_SIMATI = "";
				String JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "", TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "";
				String LUAS_TANAH = "", JENIS_LUAS_TANAH = "", LUAS_PETAK = "", ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "";
				String JENIS_HARTA_DINILAI = "", CATATAN = "", NAMA_PEGAWAI_JKPTG = "", PERLU_NILAIAN_TARIKH_MATI = "";
				String NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NILAI_TARIKH_MATI = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "";
				String JPPH_TARIKH_NILAIAN = "", EMAIL_ADDR = "", EMAIL_SEND = "";

				ID_HTA = (String) h.get("ID_HTA").toString().toUpperCase();
				PERLU_NILAIAN_TARIKH_MATI = (String) h.get("PERLU_NILAIAN_TARIKH_MATI").toString().toUpperCase();
				ID_NEGERI = (String) h.get("ID_NEGERI").toString().toUpperCase();
				ID_DAERAH = (String) h.get("ID_DAERAH").toString().toUpperCase();
				ID_MUKIM = (String) h.get("ID_MUKIM").toString().toUpperCase();
				SEKSYEN = (String) h.get("SEKSYEN").toString().toUpperCase();
				JENIS_HM = (String) h.get("ID_JENISHM").toString().toUpperCase();
				NO_HAKMILIK = (String) h.get("NO_HAKMILIK").toString().toUpperCase();
				NO_PTLOT = (String) h.get("NO_PTLOT").toString().toUpperCase();
				JENIS_PTLOT = (String) h.get("ID_JENISPTLOT").toString().toUpperCase();
				NO_BANGUNAN = (String) h.get("NO_BANGUNAN").toString().toUpperCase();
				NO_TINGKAT = (String) h.get("NO_TINGKAT").toString().toUpperCase();
				NO_PETAK = (String) h.get("NO_PETAK").toString().toUpperCase();
				BA_SIMATI = (String) h.get("BA_SIMATI").toString().toUpperCase();
				BB_SIMATI = (String) h.get("BB_SIMATI").toString().toUpperCase();
				JENIS_PEGANGAN = (String) h.get("ID_JENISPEGANGAN").toString().toUpperCase();
				TARIKH_LUPUT_PAJAKAN = (String) h.get("TARIKH_LUPUT_PJKN").toString().toUpperCase();
				TEMPOH_PAJAKAN = (String) h.get("TEMPOH_PJKN").toString().toUpperCase();
				KATEGORI_TANAH = (String) h.get("ID_KATEGORITANAH").toString().toUpperCase();
				SYARAT_NYATA = (String) h.get("SYARAT_NYATA").toString().toUpperCase();
				SEKATAN_KEPENTINGAN = (String) h.get("SEKATAN_KEPENTINGAN").toString().toUpperCase();
				LUAS_TANAH = (String) h.get("LUAS_TANAH").toString().toUpperCase();
				JENIS_LUAS_TANAH = (String) h.get("ID_JENISLUAS").toString().toUpperCase();
				LUAS_PETAK = (String) h.get("LUAS_PETAK").toString().toUpperCase();
				ALAMAT1_HARTA = (String) h.get("ALAMAT1_HARTA").toString().toUpperCase();
				ALAMAT2_HARTA = (String) h.get("ALAMAT2_HARTA").toString().toUpperCase();
				ALAMAT3_HARTA = (String) h.get("ALAMAT3_HARTA").toString().toUpperCase();
				JENIS_HARTA_DINILAI = (String) h.get("ID_JENISHARTA").toString().toUpperCase();
				CATATAN = (String) h.get("CATATAN").toString().toUpperCase();
				NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG").toString().toUpperCase();
				JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_MOHON").toString().toUpperCase();
				JPPH_NILAI_TARIKH_MATI = (String) h.get("JPPH_NILAI_MATI").toString().toUpperCase();
				JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI").toString().toUpperCase();
				JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_NAMA_CAWANGAN").toString().toUpperCase();
				JPPH_CATATAN = (String) h.get("JPPH_CATATAN").toString().toUpperCase();
				JPPH_TARIKH_NILAIAN = (String) h.get("JPPH_TARIKH_NILAIAN").toString().toUpperCase();
				if (isJPPHUser) {
					EMAIL_ADDR = (String) h.get("EMAIL_ADDR_JKPTG").toString();
					EMAIL_SEND = (String) h.get("EMAIL_SEND_JKPTG").toString().toUpperCase();					
				} else {
					EMAIL_ADDR = (String) h.get("EMAIL_ADDR_JPPH").toString();
					EMAIL_SEND = (String) h.get("EMAIL_SEND_JPPH").toString().toUpperCase();					
				}
				
				// check in TBLINTJPPHTH
				r.clear();
				r.add("C." + CHILD_TABLE_ID);
				r.add("M.ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect(CHILD_TABLE + " C, TBLINTJPPH M");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_JPPHTH = rs.getInt(1);
					haveData = true;
				} else {
					haveData = false;
				}
				
				if (isJPPHUser) {
					STATUS_PROSES = "DALAM PROSES JPPH";
				} else {
					STATUS_PROSES = "BARU";
				}
				
				r.clear();
				if (haveData) {
					r.update(CHILD_TABLE_ID, ID_JPPHTH);
				} else {
					ID_JPPHTH = DB.getNextID(CHILD_TABLE + "_SEQ");
					r.add(CHILD_TABLE_ID, ID_JPPHTH);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				}
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("STATUS_PROSES", STATUS_PROSES);
				if (!isJPPHUser) {
					//r.add("ID_PERMOHONAN", ID_PERMOHONAN);
					//r.add("ID_FAIL", ID_FAIL);
					r.add("ID_JPPH", ID_JPPH);
					r.add("ID_HTA", ID_HTA);
					//r.add("ID_PEMOHON", ID_PEMOHON);
					//r.add("JENIS_HTA", JENIS_HTA);
					r.add("NILAIAN_PADA_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_DAERAH", ID_DAERAH);
					r.add("ID_MUKIM", ID_MUKIM);
					r.add("SEKSYEN", SEKSYEN);
					if ("0".equalsIgnoreCase(JENIS_HTA)) {
						r.add("JENIS_HAKMILIK", JENIS_HM);
						r.add("NO_HAKMILIK", NO_HAKMILIK);
					}
					r.add("NO_PTLOT", NO_PTLOT);
					r.add("JENIS_PTLOT", JENIS_PTLOT);
					r.add("NO_BANGUNAN", NO_BANGUNAN);
					r.add("NO_TINGKAT", NO_TINGKAT);
					r.add("NO_PETAK", NO_PETAK);
					r.add("BA_SIMATI", BA_SIMATI);
					r.add("BB_SIMATI", BB_SIMATI);
					r.add("JENIS_PEGANGAN", JENIS_PEGANGAN);
					r.add("TARIKH_LUPUT_PAJAKAN", r.unquote("TO_DATE('" + TARIKH_LUPUT_PAJAKAN + "', 'dd/MM/yyyy')"));
					r.add("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
					r.add("KATEGORI_TANAH", KATEGORI_TANAH);
					r.add("SYARAT_NYATA", SYARAT_NYATA);
					r.add("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
					if ("0".equalsIgnoreCase(JENIS_HTA)) {
						r.add("LUAS_TANAH", LUAS_TANAH);
						r.add("UNIT_LUAS_TANAH", JENIS_LUAS_TANAH);
						r.add("JENIS_HARTA_DINILAI", JENIS_HARTA_DINILAI);					
					} else {
						r.add("NAMA_PEMILIK", NAMA_PEMILIK);
						r.add("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
						r.add("NAMA_PEMAJU", NAMA_PEMAJU);					
					}
					r.add("LUAS_PETAK", LUAS_PETAK);
					r.add("ALAMAT1_HARTA", ALAMAT1_HARTA);
					r.add("ALAMAT2_HARTA", ALAMAT2_HARTA);
					r.add("ALAMAT3_HARTA", ALAMAT3_HARTA);
					r.add("CATATAN", CATATAN);
					r.add("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
					r.add("EMAIL_ADDR_JKPTG", EMAIL_ADDR);
					r.add("EMAIL_SEND_JKPTG", EMAIL_SEND);
				} else {
					r.add("JPPH_NILAI_MOHON", JPPH_NILAI_TARIKH_MOHON);
					r.add("JPPH_NILAI_MATI", JPPH_NILAI_TARIKH_MATI);
					r.add("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
					r.add("JPPH_NAMA_CAWANGAN", JPPH_CAWANGAN_JPPH);
					r.add("JPPH_CATATAN", JPPH_CATATAN);
					r.add("JPPH_TARIKH_NILAIAN", r.unquote("TO_DATE('" + JPPH_TARIKH_NILAIAN + "', 'dd/MM/yyyy')"));
					r.add("EMAIL_ADDR_JPPH", EMAIL_ADDR);
					r.add("EMAIL_SEND_JPPH", EMAIL_SEND);
				}
				if (haveData) {
					sql = r.getSQLUpdate(CHILD_TABLE);
				} else {
					sql = r.getSQLInsert(CHILD_TABLE);
				}
				stmt.execute(sql);
				r.clear();

				String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
				if (isJPPHUser) {
					if ("1".equalsIgnoreCase(EMAIL_SEND) && !"".equalsIgnoreCase(EMAIL_ADDR)) {
						sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
							EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
							EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
							EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
							EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
							EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
						}

						if (!"".equalsIgnoreCase(EMAIL_FROM) && !"".equalsIgnoreCase(EMAIL_SUBJECT) && !"".equalsIgnoreCase(EMAIL_BODY)) {
							EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
							if (isJPPHUser) {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
							}
							Date date = new Date();
							if ("0".equalsIgnoreCase(JENIS_HTA)) {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Ada Hakmilik)");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Tiada Hakmilik)");
							}
							EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
							EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS_PROSES);
							EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
							XEkptgEmailSender email = XEkptgEmailSender.getInstance();
							email.FROM = EMAIL_FROM;
							email.MULTIPLE_RECIEPIENT = new String[1];
							email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR;
							if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
								if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
									email.TO_CC = new String[2];
									email.TO_CC[0] = EMAIL_CC_1;
									email.TO_CC[1] = EMAIL_CC_2;
								} else {
									email.TO_CC = new String[1];
									if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
										email.TO_CC[0] = EMAIL_CC_1;
									} else {
										email.TO_CC[0] = EMAIL_CC_2;
									}
								}
							}
							email.RECIEPIENT = EMAIL_ADDR;
							email.SUBJECT = EMAIL_SUBJECT;
							email.MESSAGE = EMAIL_BODY;
							email.sendEmail();				
						}
					} else {
						EMAIL_SEND = "0";
					}
				} else {
					if ("1".equalsIgnoreCase(EMAIL_SEND) && !"".equalsIgnoreCase(EMAIL_ADDR)) {
						sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
							EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
							EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
							EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
							EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
							EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
						}

						if (!"".equalsIgnoreCase(EMAIL_FROM) && !"".equalsIgnoreCase(EMAIL_SUBJECT) && !"".equalsIgnoreCase(EMAIL_BODY)) {
							EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
							if (isJPPHUser) {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
							}
							Date date = new Date();
							if ("0".equalsIgnoreCase(JENIS_HTA)) {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Ada Hakmilik)");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Tiada Hakmilik)");
							}
							EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
							EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS_PROSES);
							EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
							XEkptgEmailSender email = XEkptgEmailSender.getInstance();
							email.FROM = EMAIL_FROM;
							email.MULTIPLE_RECIEPIENT = new String[1];
							email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR;
							if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
								if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
									email.TO_CC = new String[2];
									email.TO_CC[0] = EMAIL_CC_1;
									email.TO_CC[1] = EMAIL_CC_2;
								} else {
									email.TO_CC = new String[1];
									if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
										email.TO_CC[0] = EMAIL_CC_1;
									} else {
										email.TO_CC[0] = EMAIL_CC_2;
									}
								}
							}
							email.RECIEPIENT = EMAIL_ADDR;
							email.SUBJECT = EMAIL_SUBJECT;
							email.MESSAGE = EMAIL_BODY;
							email.sendEmail();				
						}
					} else {
						EMAIL_SEND = "0";
					}
				}

				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(JENIS_HTA) && !"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					sql = "DELETE FROM TBLINTJPPHAH WHERE ID_HTA = " + ID_HTA;
				} else {
					sql = "DELETE FROM TBLINTJPPHTH WHERE ID_HTA = " + ID_HTA;
				}
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public String SelectJenisPTLot(String SOC_NAME) throws Exception {
		return SelectJenisPTLot(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, DISABLED, "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PTLOT == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - PT</option>\r\n";
				returnValue += "  <option value=\"2\">02 - LOT</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - PT</option>\r\n";
				if (SELECTED_PTLOT == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - LOT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - LOT</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectJenisNoBangunan(String SOC_NAME) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, DISABLED, "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_NOBANGUNAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - BANGUNAN</option>\r\n";
				returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
				returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - BANGUNAN</option>\r\n";
				if (SELECTED_NOBANGUNAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - TINGKAT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
					if (SELECTED_NOBANGUNAN == 3) {
						returnValue += "  <option value=\"3\" selected=\"selected\">03 - STRATA</option>\r\n";
					} else {
						returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectJenisPegangan(String SOC_NAME) throws Exception {
		return SelectJenisPegangan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, DISABLED, "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PEGANGAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - KEKAL</option>\r\n";
				returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - KEKAL</option>\r\n";
				if (SELECTED_PEGANGAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - PAJAKAN</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectKategoriTanah(String SOC_NAME) throws Exception {
		return SelectKategoriTanah(SOC_NAME, 0, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, DISABLED, "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + ">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_KATEGORI, KOD_KATEGORI, KETERANGAN FROM TBLRUJKATEGORI " +
				SQL_SEARCH + "ORDER BY KOD_KATEGORI, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_KATEGORI == ID_DATA) {
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
	
	public String SelectJenisLuasTanah(String SOC_NAME) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, 0, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, DISABLED, "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_LUAS, KOD_LUAS, KETERANGAN FROM TBLRUJLUAS " +
				SQL_SEARCH + "ORDER BY KOD_LUAS, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_LUASTANAH == ID_DATA) {
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

	public String SelectJenisHartaDinilai(String SOC_NAME) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, 0, "", "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, SELECTED_HARTADINILAI, "", "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI, String DISABLED) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, SELECTED_HARTADINILAI, DISABLED, "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_HARTADINILAI == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - TANAH SAHAJA</option>\r\n";
				returnValue += "  <option value=\"2\">02 - BANGUNAN SAHAJA</option>\r\n";
				returnValue += "  <option value=\"3\">03 - TANAH DAN BANGUNAN</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - TANAH SAHAJA</option>\r\n";
				if (SELECTED_HARTADINILAI == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - BANGUNAN SAHAJA</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - BANGUNAN SAHAJA</option>\r\n";
					if (SELECTED_HARTADINILAI == 3) {
						returnValue += "  <option value=\"3\" selected=\"selected\">03 - TANAH DAN BANGUNAN</option>\r\n";
					} else {
						returnValue += "  <option value=\"3\">03 - TANAH DAN BANGUNAN</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	
	
	public Boolean returnNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "UPDATE TBLINTJPPHHTA SET STATUS_PROSES = 'DIKEMBALIKAN', TARIKH_KEMASKINI = SYSDATE WHERE ID_HTA = " + ID_HTA;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}

	public Boolean changeStatusPermohonanForJPPH(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "UPDATE TBLINTJPPHHTA SET STATUS_PROSES = 'DALAM PROSES JPPH', TARIKH_KEMASKINI = SYSDATE WHERE STATUS_PROSES = 'BARU' AND ID_HTA = " + ID_HTA;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public static String getNamaPejabatJPPH(String USER_NAME) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_NAME)) {
				String sql = "";
				sql = "SELECT PJ.NAMA_PEJABAT " +
					"FROM TBLRUJPEJABATJKPTG PJ, USERS_INTERNAL UI, USERS U " +
					"WHERE PJ.ID_PEJABATJKPTG = UI.ID_PEJABATJKPTG AND UI.USER_ID = U.USER_ID " +
					"AND UPPER(U.USER_LOGIN) = '" + USER_NAME.toUpperCase() + "'";
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