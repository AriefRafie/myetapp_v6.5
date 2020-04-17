package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmModelNilaianSewaan {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchHTA(String NO_FAIL, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SQL_SEARCH = " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_SIMATI)) {
				SQL_SEARCH = " AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%')";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.ALAMAT_1, SM.ALAMAT_2, SM.ALAMAT_3 " +
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
				NAMA_SIMATI = rs.getString(3) == null ? "" : rs.getString(3);
				NO_KP_BARU = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_LAMA = rs.getString(5) == null ? "" : rs.getString(5);
				ALAMAT1 = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT2 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT3 = rs.getString(8) == null ? "" : rs.getString(8);
				
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
	
	public String getIDSiMati(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
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
	
	public String getIDPemohon(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
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
	
	public String getFirstIDHTAAH(String ID_SIMATI) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_SIMATI)) {
				String sql = "";
				sql = "SELECT ID_HTA FROM TBLPPKHTA WHERE UPPER(JENIS_HTA) = 'Y' AND ID_SIMATI = " + ID_SIMATI + " ORDER BY NO_PT";
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
	
	public String getFirstIDHTATH(String ID_SIMATI) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_SIMATI)) {
				String sql = "";
				sql = "SELECT ID_HTA FROM TBLPPKHTA WHERE UPPER(JENIS_HTA) != 'Y' AND ID_SIMATI = " + ID_SIMATI + " ORDER BY ALAMAT_HTA1";
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
	
	@SuppressWarnings("unchecked")
	public Vector viewHTAMaklumatPermohonan(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, M.NO_PERMOHONAN, M.ID_NEGERIMHN, M.ID_DAERAHMHN, M.SEKSYEN, NEGERI.NAMA_NEGERI, " +
					"DAERAH.NAMA_DAERAH, SEKSYEN.NAMA_SEKSYEN, SM.ID_SIMATI, SM.NAMA_SIMATI, SM.TARIKH_MATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, " +
					"M.ID_PEMOHON, PEMOHON.NAMA_PEMOHON, PEMOHON.NO_KP_BARU, PEMOHON.NO_KP_LAMA, PEMOHON.ALAMAT_1, PEMOHON.ALAMAT_2, " +
					"PEMOHON.ALAMAT_3, PEMOHON.NO_TEL " +
					"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL FAIL, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, " +
					"TBLRUJSEKSYEN SEKSYEN, TBLPPKSIMATI SM, TBLPPKPEMOHON PEMOHON " +
					"WHERE M.ID_FAIL = FAIL.ID_FAIL AND M.ID_NEGERIMHN = NEGERI.ID_NEGERI(+) AND M.ID_DAERAHMHN = DAERAH.ID_DAERAH(+) " +
					"AND M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND M.SEKSYEN = SEKSYEN.ID_SEKSYEN " +
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
					String ALAMAT_PEMOHON = "", NO_TEL_PEMOHON = "";
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
	public Vector viewListNilaianHTAAH(String ID_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_SIMATI)) {
				String sql = "";
				sql = "SELECT M.ID_HTA, M.NO_PT " +
					"FROM TBLPPKHTA M " +
					"WHERE UPPER(M.JENIS_HTA) = 'Y' AND M.ID_SIMATI = " + ID_SIMATI + " ORDER BY M.NO_PT";
				int i = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ID_HTAAH = "", NO_PT = "";
					ID_HTAAH = rs.getString(1) == null ? "" : rs.getString(1);
					NO_PT = rs.getString(2) == null ? "" : rs.getString(2);
					h = new Hashtable();
					h.put("No", i);
					h.put("IDHTAAH", ID_HTAAH);
					h.put("NoPTLot", NO_PT);
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
	public Vector viewNilaianHTAAH(String ID_HTA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				String ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", ID_SEKSYEN = "", ID_JENISHM = "", NO_HAKMILIK = "", NO_PT = "", JENIS_PT = "";
				String NO_BANGUNAN = "", NO_TINGKAT = "", NO_STRATA = "", BA_SIMATI = "", BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "";
				String TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "", LUAS_TANAH = "", JENIS_LUAS_TANAH = "";
				String LUAS_PETAK = "", ALAMAT_HTA1 = "", ALAMAT_HTA2 = "", ALAMAT_HTA3 = "", JENIS_HARTA_DINILAI = "", CATATAN = "";
				String NAMA_PEGAWAI_JKPTG = "", JENIS_BANGUNAN = "";
				String JPPH_NILAI_MOHON_TANAH = "", JPPH_NILAI_MOHON_BGN = "", JPPH_NILAI_MATI_TANAH = "", JPPH_NILAI_MATI_BGN = "";
				String JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "";
				sql = "SELECT ID_NEGERI, ID_DAERAH, ID_MUKIM, ID_SEKSYEN, JENIS_HAKMILIK, NO_HAKMILIK, NO_PTLOT, " +
					"JENIS_PTLOT, NO_BANGUNAN, NO_TINGKAT, NO_STRATA, BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, " +
					"TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, SYARAT_NYATA, SEKATAN_KEPENTINGAN, LUAS_TANAH, " +
					"UNIT_LUAS_TANAH, LUAS_PETAK, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, JENIS_HARTA_DINILAI, CATATAN, NAMA_PEGAWAI_JKPTG, " +
					"JPPH_NILAI_TARIKH_MOHON_TANAH, JPPH_NILAI_TARIKH_MOHON_BGN, JPPH_NILAI_TARIKH_MATI_TANAH, JPPH_NILAI_TARIKH_MATI_BGN, " +
					"JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN " +
					"FROM TBLINTJPPHAH " +
					"WHERE ID_HTA = " + ID_HTA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
					ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
					ID_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
					ID_JENISHM = rs.getString(5) == null ? "" : rs.getString(5);
					NO_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
					NO_PT = rs.getString(7) == null ? "" : rs.getString(7);
					JENIS_PT = rs.getString(8) == null ? "" : rs.getString(8);
					NO_BANGUNAN = rs.getString(9) == null ? "" : rs.getString(9);
					NO_TINGKAT = rs.getString(10) == null ? "" : rs.getString(10);
					NO_STRATA = rs.getString(11) == null ? "" : rs.getString(11);
					BA_SIMATI = rs.getString(12) == null ? "" : rs.getString(12);
					BB_SIMATI = rs.getString(13) == null ? "" : rs.getString(13);
					JENIS_PEGANGAN = rs.getString(14) == null ? "" : rs.getString(14);
					TARIKH_LUPUT_PAJAKAN = rs.getDate(15) == null ? "" : sdf.format(rs.getDate(15));
					TEMPOH_PAJAKAN = rs.getString(16) == null ? "" : rs.getString(16);
					KATEGORI_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
					SYARAT_NYATA = rs.getString(18) == null ? "" : rs.getString(18);
					SEKATAN_KEPENTINGAN = rs.getString(19) == null ? "" : rs.getString(19);
					LUAS_TANAH = rs.getString(20) == null ? "" : rs.getString(20);
					JENIS_LUAS_TANAH = rs.getString(21) == null ? "" : rs.getString(21);
					LUAS_PETAK = rs.getString(22) == null ? "" : rs.getString(22);
					ALAMAT_HTA1 = rs.getString(23) == null ? "" : rs.getString(23);
					ALAMAT_HTA2 = rs.getString(24) == null ? "" : rs.getString(24);
					ALAMAT_HTA3 = rs.getString(25) == null ? "" : rs.getString(25);
					JENIS_HARTA_DINILAI = rs.getString(26) == null ? "" : rs.getString(26);
					CATATAN = rs.getString(27) == null ? "" : rs.getString(27);
					NAMA_PEGAWAI_JKPTG = rs.getString(28) == null ? "" : rs.getString(28);
					JPPH_NILAI_MOHON_TANAH = rs.getString(29) == null ? "" : rs.getString(29);
					JPPH_NILAI_MOHON_BGN = rs.getString(30) == null ? "" : rs.getString(30);
					JPPH_NILAI_MATI_TANAH = rs.getString(31) == null ? "" : rs.getString(31);
					JPPH_NILAI_MATI_BGN = rs.getString(32) == null ? "" : rs.getString(32);
					JPPH_NAMA_PEGAWAI = rs.getString(33) == null ? "" : rs.getString(33);
					JPPH_CAWANGAN_JPPH = rs.getString(34) == null ? "" : rs.getString(34);
					JPPH_CATATAN = rs.getString(35) == null ? "" : rs.getString(35);

					if ("".equalsIgnoreCase(NO_BANGUNAN)) {
						if (!"".equalsIgnoreCase(NO_TINGKAT)) {
							NO_BANGUNAN = NO_TINGKAT;
							JENIS_BANGUNAN = "2";
						} else if (!"".equalsIgnoreCase(NO_STRATA)) {
							NO_BANGUNAN = NO_STRATA;
							JENIS_BANGUNAN = "3";
						}
					} else {
						JENIS_BANGUNAN = "1";
					}
				}
				
				if (!haveINTData) {
					sql = "SELECT HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, '', HTA.ID_JENISHM, HTA.NO_HAKMILIK, HTA.NO_PT, '', HTA.NO_BANGUNAN, " +
						"HTA.NO_TINGKAT, HTA.NO_STRATA, HTA.BA_SIMATI, HTA.BB_SIMATI, '', '', '', '', '', '', '', '', '', HTA.ALAMAT_HTA1, " +
						"HTA.ALAMAT_HTA2, HTA.ALAMAT_HTA3, '', '', '', '', '', '', '', '', '', '' " +
						"FROM TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM, TBLPPKSIMATI SM, TBLRUJJENISHAKMILIK JHM " +
						"WHERE HTA.ID_NEGERI = NEGERI.ID_NEGERI AND HTA.ID_DAERAH = DAERAH.ID_DAERAH AND HTA.ID_MUKIM = MUKIM.ID_MUKIM AND HTA.ID_SIMATI = SM.ID_SIMATI " +
						"AND HTA.ID_JENISHM = JHM.ID_JENISHAKMILIK AND HTA.ID_HTA = " + ID_HTA;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
						ID_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
						ID_JENISHM = rs.getString(5) == null ? "" : rs.getString(5);
						NO_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
						NO_PT = rs.getString(7) == null ? "" : rs.getString(7);
						JENIS_PT = rs.getString(8) == null ? "" : rs.getString(8);
						NO_BANGUNAN = rs.getString(9) == null ? "" : rs.getString(9);
						NO_TINGKAT = rs.getString(10) == null ? "" : rs.getString(10);
						NO_STRATA = rs.getString(11) == null ? "" : rs.getString(11);
						BA_SIMATI = rs.getString(12) == null ? "" : rs.getString(12);
						BB_SIMATI = rs.getString(13) == null ? "" : rs.getString(13);
						JENIS_PEGANGAN = rs.getString(14) == null ? "" : rs.getString(14);
						TARIKH_LUPUT_PAJAKAN = rs.getDate(15) == null ? "" : sdf.format(rs.getDate(15));
						TEMPOH_PAJAKAN = rs.getString(16) == null ? "" : rs.getString(16);
						KATEGORI_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
						SYARAT_NYATA = rs.getString(18) == null ? "" : rs.getString(18);
						SEKATAN_KEPENTINGAN = rs.getString(19) == null ? "" : rs.getString(19);
						LUAS_TANAH = rs.getString(20) == null ? "" : rs.getString(20);
						JENIS_LUAS_TANAH = rs.getString(21) == null ? "" : rs.getString(21);
						LUAS_PETAK = rs.getString(22) == null ? "" : rs.getString(22);
						ALAMAT_HTA1 = rs.getString(23) == null ? "" : rs.getString(23);
						ALAMAT_HTA2 = rs.getString(24) == null ? "" : rs.getString(24);
						ALAMAT_HTA3 = rs.getString(25) == null ? "" : rs.getString(25);
						JENIS_HARTA_DINILAI = rs.getString(26) == null ? "" : rs.getString(26);
						CATATAN = rs.getString(27) == null ? "" : rs.getString(27);
						NAMA_PEGAWAI_JKPTG = rs.getString(28) == null ? "" : rs.getString(28);
						JPPH_NILAI_MOHON_TANAH = rs.getString(29) == null ? "" : rs.getString(29);
						JPPH_NILAI_MOHON_BGN = rs.getString(30) == null ? "" : rs.getString(30);
						JPPH_NILAI_MATI_TANAH = rs.getString(31) == null ? "" : rs.getString(31);
						JPPH_NILAI_MATI_BGN = rs.getString(32) == null ? "" : rs.getString(32);
						JPPH_NAMA_PEGAWAI = rs.getString(33) == null ? "" : rs.getString(33);
						JPPH_CAWANGAN_JPPH = rs.getString(34) == null ? "" : rs.getString(34);
						JPPH_CATATAN = rs.getString(35) == null ? "" : rs.getString(35);
						
						if ("".equalsIgnoreCase(NO_BANGUNAN)) {
							if (!"".equalsIgnoreCase(NO_TINGKAT)) {
								NO_BANGUNAN = NO_TINGKAT;
								JENIS_BANGUNAN = "2";
							} else if (!"".equalsIgnoreCase(NO_STRATA)) {
								NO_BANGUNAN = NO_STRATA;
								JENIS_BANGUNAN = "3";
							}
						} else {
							JENIS_BANGUNAN = "1";
						}
					}
				}
				h = new Hashtable();
				h.put("HTAAH_NEGERI", ID_NEGERI);
				h.put("HTAAH_DAERAH", ID_DAERAH);
				h.put("HTAAH_MUKIM", ID_MUKIM);
				h.put("HTAAH_SEKSYEN", ID_SEKSYEN);
				h.put("HTAAH_JENISHAKMILIK", ID_JENISHM);
				h.put("HTAAH_NOHAKMILIK", NO_HAKMILIK);
				h.put("HTAAH_NOPTLOT", NO_PT);
				h.put("HTAAH_JENISPTLOT", JENIS_PT);
				h.put("HTAAH_NOBANGUNAN", NO_BANGUNAN);
				h.put("HTAAH_JENISNOBANGUNAN", JENIS_BANGUNAN);
				h.put("HTAAH_BASIMATI", BA_SIMATI);
				h.put("HTAAH_BBSIMATI", BB_SIMATI);
				h.put("HTAAH_JENISPEGANGAN", JENIS_PEGANGAN);
				h.put("HTAAH_TARIKHLUPUTPAJAKAN", TARIKH_LUPUT_PAJAKAN);
				h.put("HTAAH_TEMPOHPAJAKAN", TEMPOH_PAJAKAN);
				h.put("HTAAH_KATEGORITANAH", KATEGORI_TANAH);
				h.put("HTAAH_SYARATNYATA", SYARAT_NYATA);
				h.put("HTAAH_SEKATANKEPENTINGAN", SEKATAN_KEPENTINGAN);
				h.put("HTAAH_LUASTANAH", LUAS_TANAH);
				h.put("HTAAH_JENISLUASTANAH", JENIS_LUAS_TANAH);
				h.put("HTAAH_LUASPETAK", LUAS_PETAK);
				h.put("HTAAH_ALAMAT1HARTA", ALAMAT_HTA1);
				h.put("HTAAH_ALAMAT2HARTA", ALAMAT_HTA2);
				h.put("HTAAH_ALAMAT3HARTA", ALAMAT_HTA3);
				h.put("HTAAH_JENISHARTADINILAI", JENIS_HARTA_DINILAI);
				h.put("HTAAH_CATATAN", CATATAN);
				h.put("HTAAH_NAMAPEGAWAIJKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("HTAAH_NILAITARIKHMOHON", JPPH_NILAI_MOHON_TANAH);
				h.put("HTAAH_NILAITARIKHMOHON", JPPH_NILAI_MOHON_BGN);
				h.put("HTAAH_NILAITARIKHMATITANAH", JPPH_NILAI_MATI_TANAH);
				h.put("HTAAH_NILAITARIKHMATIBANGUNAN", JPPH_NILAI_MATI_BGN);
				h.put("HTAAH_NAMAPEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("HTAAH_CAWANGANJPPH", JPPH_CAWANGAN_JPPH);
				h.put("HTAAH_JPPHCATATAN", JPPH_CATATAN);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector viewListNilaianHTATH(String ID_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_SIMATI)) {
				String sql = "";
				sql = "SELECT M.ID_HTA, M.ALAMAT_HTA1, M.ALAMAT_HTA2, M.ALAMAT_HTA3, M.BA_SIMATI, M.BB_SIMATI, M.NAMA_PEMAJU, M.LUAS_HMP " +
					"FROM TBLPPKHTA M " +
					"WHERE UPPER(M.JENIS_HTA) != 'Y' AND M.ID_SIMATI = " + ID_SIMATI + " ORDER BY M.ALAMAT_HTA1";
				int i = 1;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ID_HTATH = "", ALAMAT = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "", LUAS = "", BAHAGIAN_SIMATI = "";
					String ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", BA_SIMATI = "", BB_SIMATI = "";
					ID_HTATH = rs.getString(1) == null ? "" : rs.getString(1);
					ALAMAT1 = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT2 = rs.getString(3) == null ? "" : rs.getString(3);
					ALAMAT3 = rs.getString(4) == null ? "" : rs.getString(4);
					BA_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
					BB_SIMATI = rs.getString(6) == null ? "" : rs.getString(6);
					NAMA_PEMAJU = rs.getString(7) == null ? "" : rs.getString(7);
					LUAS = rs.getString(8) == null ? "" : rs.getString(8);
					BAHAGIAN_SIMATI = BA_SIMATI + "/" + BB_SIMATI;

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
					h.put("IDHTATH", ID_HTATH);
					h.put("AlamatHarta", ALAMAT);
					h.put("TarikhPerjanjian", TARIKH_PERJANJIAN);
					h.put("NamaPemaju", NAMA_PEMAJU);
					h.put("Luas", LUAS);
					h.put("BahagianSiMati", BAHAGIAN_SIMATI);
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
	public Vector viewNilaianHTATH(String ID_HTA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				String ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", ID_SEKSYEN = "", NO_PT = "", JENIS_PT = "", NO_BANGUNAN = "", JENIS_BANGUNAN = "";
				String NO_TINGKAT = "", NO_STRATA = "", BA_SIMATI = "", BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "";
				String TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "", ALAMAT_HTA1 = "", ALAMAT_HTA2 = "";
				String ALAMAT_HTA3 = "", NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "", LUAS_PETAK = "", CATATAN = "", NAMA_PEGAWAI_JKPTG = "";
				String JPPH_NILAI_MOHON_TANAH = "", JPPH_NILAI_MOHON_BANGUNAN = "", JPPH_NILAI_MATI_TANAH = "", JPPH_NILAI_MATI_BANGUNAN = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "";
				sql = "SELECT ID_NEGERI, ID_DAERAH, ID_MUKIM, ID_SEKSYEN, NO_PTLOT, JENIS_PTLOT, NO_BANGUNAN, NO_TINGKAT, " +
					"NO_STRATA, BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, " +
					"SYARAT_NYATA, SEKATAN_KEPENTINGAN, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, NAMA_PEMILIK, " +
					"TARIKH_PERJANJIAN, NAMA_PEMAJU, LUAS_PETAK, CATATAN, NAMA_PEGAWAI_JKPTG, " +
					"JPPH_NILAI_TARIKH_MOHON_TANAH, JPPH_NILAI_TARIKH_MOHON_BGN, JPPH_NILAI_TARIKH_MATI_TANAH, JPPH_NILAI_TARIKH_MATI_BGN, " +
					"JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN " +
					"FROM TBLINTJPPHTH " +
					"WHERE ID_HTA = " + ID_HTA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
					ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
					ID_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
					NO_PT = rs.getString(5) == null ? "" : rs.getString(5);
					JENIS_PT = rs.getString(6) == null ? "" : rs.getString(6);
					NO_BANGUNAN = rs.getString(7) == null ? "" : rs.getString(7);
					NO_TINGKAT = rs.getString(8) == null ? "" : rs.getString(8); 
					NO_STRATA = rs.getString(9) == null ? "" : rs.getString(9); 
					BA_SIMATI = rs.getString(10) == null ? "" : rs.getString(10);
					BB_SIMATI = rs.getString(11) == null ? "" : rs.getString(11);
					JENIS_PEGANGAN = rs.getString(12) == null ? "" : rs.getString(12);
					TARIKH_LUPUT_PAJAKAN = rs.getDate(13) == null ? "" : sdf.format(rs.getDate(13));
					TEMPOH_PAJAKAN = rs.getString(14) == null ? "" : rs.getString(14);
					KATEGORI_TANAH = rs.getString(15) == null ? "" : rs.getString(15);
					SYARAT_NYATA = rs.getString(16) == null ? "" : rs.getString(16);
					SEKATAN_KEPENTINGAN = rs.getString(17) == null ? "" : rs.getString(17);
					ALAMAT_HTA1 = rs.getString(18) == null ? "" : rs.getString(18);
					ALAMAT_HTA2 = rs.getString(19) == null ? "" : rs.getString(19);
					ALAMAT_HTA3 = rs.getString(20) == null ? "" : rs.getString(20);
					NAMA_PEMILIK = rs.getString(21) == null ? "" : rs.getString(21);
					TARIKH_PERJANJIAN = rs.getDate(22) == null ? "" : sdf.format(rs.getDate(22));
					NAMA_PEMAJU = rs.getString(23) == null ? "" : rs.getString(23);
					LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
					CATATAN = rs.getString(25) == null ? "" : rs.getString(25);
					NAMA_PEGAWAI_JKPTG = rs.getString(26) == null ? "" : rs.getString(26);
					JPPH_NILAI_MOHON_TANAH = rs.getString(27) == null ? "" : rs.getString(27);
					JPPH_NILAI_MOHON_BANGUNAN = rs.getString(28) == null ? "" : rs.getString(28);
					JPPH_NILAI_MATI_TANAH = rs.getString(29) == null ? "" : rs.getString(29);
					JPPH_NILAI_MATI_BANGUNAN = rs.getString(30) == null ? "" : rs.getString(30);
					JPPH_NAMA_PEGAWAI = rs.getString(31) == null ? "" : rs.getString(31);
					JPPH_CAWANGAN_JPPH = rs.getString(32) == null ? "" : rs.getString(32);
					JPPH_CATATAN = rs.getString(33) == null ? "" : rs.getString(33);

					if ("".equalsIgnoreCase(NO_BANGUNAN)) {
						if (!"".equalsIgnoreCase(NO_TINGKAT)) {
							NO_BANGUNAN = NO_TINGKAT;
							JENIS_BANGUNAN = "2";
						} else if (!"".equalsIgnoreCase(NO_STRATA)) {
							NO_BANGUNAN = NO_STRATA;
							JENIS_BANGUNAN = "3";
						}
					} else {
						JENIS_BANGUNAN = "1";
					}
				}
				
				if (!haveINTData) {
					sql = "SELECT HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, '', HTA.NO_PT, '', HTA.NO_BANGUNAN, HTA.NO_TINGKAT, HTA.NO_STRATA, " +
						"HTA.BA_SIMATI, HTA.BB_SIMATI, '', '', '', '', '', '', HTA.ALAMAT_HTA1, HTA.ALAMAT_HTA2, HTA.ALAMAT_HTA3, " +
						"'', '', HTA.NAMA_PEMAJU, HTA.LUAS_HMP, '', '', '', '', '', '', '', '', '' " +
						"FROM TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM, TBLPPKSIMATI SM, TBLRUJJENISHAKMILIK JHM " +
						"WHERE HTA.ID_NEGERI = NEGERI.ID_NEGERI AND HTA.ID_DAERAH = DAERAH.ID_DAERAH AND HTA.ID_MUKIM = MUKIM.ID_MUKIM AND HTA.ID_SIMATI = SM.ID_SIMATI " +
						"AND HTA.ID_JENISHM = JHM.ID_JENISHAKMILIK AND HTA.ID_HTA = " + ID_HTA;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
						ID_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
						NO_PT = rs.getString(5) == null ? "" : rs.getString(5);
						JENIS_PT = rs.getString(6) == null ? "" : rs.getString(6);
						NO_BANGUNAN = rs.getString(7) == null ? "" : rs.getString(7);
						NO_TINGKAT = rs.getString(8) == null ? "" : rs.getString(8); 
						NO_STRATA = rs.getString(9) == null ? "" : rs.getString(9); 
						BA_SIMATI = rs.getString(10) == null ? "" : rs.getString(10);
						BB_SIMATI = rs.getString(11) == null ? "" : rs.getString(11);
						JENIS_PEGANGAN = rs.getString(12) == null ? "" : rs.getString(12);
						TARIKH_LUPUT_PAJAKAN = rs.getDate(13) == null ? "" : sdf.format(rs.getDate(13));
						TEMPOH_PAJAKAN = rs.getString(14) == null ? "" : rs.getString(14);
						KATEGORI_TANAH = rs.getString(15) == null ? "" : rs.getString(15);
						SYARAT_NYATA = rs.getString(16) == null ? "" : rs.getString(16);
						SEKATAN_KEPENTINGAN = rs.getString(17) == null ? "" : rs.getString(17);
						ALAMAT_HTA1 = rs.getString(18) == null ? "" : rs.getString(18);
						ALAMAT_HTA2 = rs.getString(19) == null ? "" : rs.getString(19);
						ALAMAT_HTA3 = rs.getString(20) == null ? "" : rs.getString(20);
						NAMA_PEMILIK = rs.getString(21) == null ? "" : rs.getString(21);
						TARIKH_PERJANJIAN = rs.getDate(22) == null ? "" : sdf.format(rs.getDate(22));
						NAMA_PEMAJU = rs.getString(23) == null ? "" : rs.getString(23);
						LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
						CATATAN = rs.getString(25) == null ? "" : rs.getString(25);
						NAMA_PEGAWAI_JKPTG = rs.getString(26) == null ? "" : rs.getString(26);
						JPPH_NILAI_MOHON_TANAH = rs.getString(27) == null ? "" : rs.getString(27);
						JPPH_NILAI_MOHON_BANGUNAN = rs.getString(28) == null ? "" : rs.getString(28);
						JPPH_NILAI_MATI_TANAH = rs.getString(29) == null ? "" : rs.getString(29);
						JPPH_NILAI_MATI_BANGUNAN = rs.getString(30) == null ? "" : rs.getString(30);
						JPPH_NAMA_PEGAWAI = rs.getString(31) == null ? "" : rs.getString(31);
						JPPH_CAWANGAN_JPPH = rs.getString(32) == null ? "" : rs.getString(32);
						JPPH_CATATAN = rs.getString(33) == null ? "" : rs.getString(33);
						
						if ("".equalsIgnoreCase(NO_BANGUNAN)) {
							if (!"".equalsIgnoreCase(NO_TINGKAT)) {
								NO_BANGUNAN = NO_TINGKAT;
								JENIS_BANGUNAN = "2";
							} else if (!"".equalsIgnoreCase(NO_STRATA)) {
								NO_BANGUNAN = NO_STRATA;
								JENIS_BANGUNAN = "3";
							}
						} else {
							JENIS_BANGUNAN = "1";
						}
					}
				}
				h = new Hashtable();
				h.put("HTATH_NEGERI", ID_NEGERI);
				h.put("HTATH_DAERAH", ID_DAERAH);
				h.put("HTATH_MUKIM", ID_MUKIM);
				h.put("HTATH_SEKSYEN", ID_SEKSYEN);
				h.put("HTATH_NOPTLOT", NO_PT);
				h.put("HTATH_JENISPTLOT", JENIS_PT);
				h.put("HTATH_NOBANGUNAN", NO_BANGUNAN);
				h.put("HTATH_JENISNOBANGUNAN", JENIS_BANGUNAN);
				h.put("HTATH_BASIMATI", BA_SIMATI);
				h.put("HTATH_BBSIMATI", BB_SIMATI);
				h.put("HTATH_JENISPEGANGAN", JENIS_PEGANGAN);
				h.put("HTATH_TARIKHLUPUTPAJAKAN", TARIKH_LUPUT_PAJAKAN);
				h.put("HTATH_TEMPOHPAJAKAN", TEMPOH_PAJAKAN);
				h.put("HTATH_KATEGORITANAH", KATEGORI_TANAH);
				h.put("HTATH_SYARATNYATA", SYARAT_NYATA);
				h.put("HTATH_SEKATANKEPENTINGAN", SEKATAN_KEPENTINGAN);
				h.put("HTATH_ALAMAT1HARTA", ALAMAT_HTA1);
				h.put("HTATH_ALAMAT2HARTA", ALAMAT_HTA2);
				h.put("HTATH_ALAMAT3HARTA", ALAMAT_HTA3);
				h.put("HTATH_NAMAPEMILIK", NAMA_PEMILIK);
				h.put("HTATH_TARIKHPERJANJIANJUALBELI", TARIKH_PERJANJIAN);
				h.put("HTATH_NAMAPEMAJU", NAMA_PEMAJU);
				h.put("HTATH_LUASPETAK", LUAS_PETAK);
				h.put("HTATH_CATATAN", CATATAN);
				h.put("HTAAH_NAMAPEGAWAIJKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("HTATH_NILAITARIKHMOHONTANAH", JPPH_NILAI_MOHON_TANAH);
				h.put("HTATH_NILAITARIKHMOHONBANGUNAN", JPPH_NILAI_MOHON_BANGUNAN);
				h.put("HTATH_NILAITARIKHMATITANAH", JPPH_NILAI_MATI_TANAH);
				h.put("HTATH_NILAITARIKHMATIBANGUNAN", JPPH_NILAI_MATI_BANGUNAN);
				h.put("HTATH_NAMAPEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("HTATH_CAWANGANJPPH", JPPH_CAWANGAN_JPPH);
				h.put("HTATH_JPPHCATATAN", JPPH_CATATAN);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean saveNilaianHTA(String ID_PERMOHONAN, String JENIS_HTA, Hashtable h) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				String CHILD_TABLE = "", CHILD_FIELD = "", CHILD_VALUE = "", SEQUENCE_TABLE = "", ID_FIELD = "";
				long ID_JPPH = 0, ID_JPPHAH = 0, ID_JPPHTH = 0;
				String ID_FAIL = "", NO_FAIL = "", NO_PERMOHONAN = "", ID_NEGERI = "", ID_DAERAH = "", ID_UNIT = "", ID_SIMATI = "", NAMA_SIMATI = "", TARIKH_MATI = "", NO_KP_SIMATI = "";
				String ID_PEMOHON = "", NAMA_PEMOHON = "", NO_KP_PEMOHON = "", ALAMAT1_PEMOHON = "", ALAMAT2_PEMOHON = "", ALAMAT3_PEMOHON = "";

				NO_PERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
				ID_FAIL = (String) h.get("MP_IDFAIL");
				NO_FAIL = (String) h.get("MP_NOFAIL");
				ID_NEGERI = (String) h.get("MP_IDNEGERI");
				ID_DAERAH = (String) h.get("MP_IDDAERAH");
				ID_UNIT = (String) h.get("MP_IDUNIT");
				ID_SIMATI = (String) h.get("MP_IDSIMATI");
				NAMA_SIMATI = (String) h.get("MP_NAMASIMATI");
				NO_KP_SIMATI = (String) h.get("MP_NOKPSIMATI");
				TARIKH_MATI = (String) h.get("MP_TARIKHMATI");
				ID_PEMOHON = (String) h.get("MP_IDPEMOHON");
				NAMA_PEMOHON = (String) h.get("MP_NAMAPEMOHON");
				NO_KP_PEMOHON = (String) h.get("MP_NOKPPEMOHON");
				ALAMAT1_PEMOHON = (String) h.get("MP_ALAMAT1PEMOHON");
				ALAMAT2_PEMOHON = (String) h.get("MP_ALAMAT2PEMOHON");
				ALAMAT3_PEMOHON = (String) h.get("MP_ALAMAT3PEMOHON");
				
				// check in TBLINTJPPH
				r.add("ID_PERMOHONAN");
				r.add("ID_JPPH");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJPPH");
				r.clear();
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_JPPH = rs.getInt(2);
					haveData = true;
				}
				
				if (haveData) {
					r.update("ID_PERMOHONAN", ID_PERMOHONAN);
				}
				r.add("STATUS_PROSES", "BARU");
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("ID_FAIL", ID_FAIL);
				r.add("NO_FAIL", NO_FAIL);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("NO_PERMOHONAN", NO_PERMOHONAN);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_UNIT", ID_UNIT);
				r.add("ID_SIMATI", ID_SIMATI);
				r.add("NAMA_SIMATI", NAMA_SIMATI);
				r.add("NO_KP_SIMATI", NO_KP_SIMATI);
				r.add("TARIKH_MATI", r.unquote("TO_DATE('" + TARIKH_MATI + "', 'dd/MM/yyyy')"));
				r.add("ID_PEMOHON", ID_PEMOHON);
				r.add("NAMA_PEMOHON", NAMA_PEMOHON);
				r.add("NO_KP_PEMOHON", NO_KP_PEMOHON);
				r.add("ALAMAT1_PEMOHON", ALAMAT1_PEMOHON);
				r.add("ALAMAT2_PEMOHON", ALAMAT2_PEMOHON);
				r.add("ALAMAT3_PEMOHON", ALAMAT3_PEMOHON);
				if (haveData) {
					sql = r.getSQLUpdate("TBLINTJPPH");
				} else {
					ID_JPPH = DB.getNextID("TBLINTJPPH_SEQ");
					r.add("ID_JPPH", ID_JPPH);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
					sql = r.getSQLInsert("TBLINTJPPH");
				}
				stmt.execute(sql);
				r.clear();
				
				String HTAAH_NEGERI = "", HTAAH_DAERAH = "", HTAAH_MUKIM = "", HTAAH_SEKSYEN = "", HTAAH_JENISHAKMILIK = "", HTAAH_NOHAKMILIK = "";
				String HTAAH_NOPTLOT = "", HTAAH_JENISPTLOT = "", HTAAH_NOBANGUNAN = "", HTAAH_JENISNOBANGUNAN = "", HTAAH_BASIMATI = "", HTAAH_BBSIMATI = "", HTAAH_JENISPEGANGAN = "";
				String HTAAH_TARIKHLUPUTPAJAKAN = "", HTAAH_TEMPOHPAJAKAN = "", HTAAH_KATEGORITANAH = "", HTAAH_SYARATNYATA = "", HTAAH_SEKATANKEPENTINGAN = "";
				String HTAAH_LUASTANAH = "", HTAAH_JENISLUASTANAH = "", HTAAH_LUASPETAK = "", HTAAH_ALAMAT1HARTA = "", HTAAH_ALAMAT2HARTA = "";
				String HTAAH_ALAMAT3HARTA = "", HTAAH_JENISHARTADINILAI = "", HTAAH_CATATAN = "", HTAAH_NILAITARIKHMOHONTANAH = "", HTAAH_NILAITARIKHMOHONBANGUNAN = "";
				String HTAAH_NILAITARIKHMATITANAH = "", HTAAH_NILAITARIKHMATIBANGUNAN = "", HTAAH_NAMAPEGAWAI = "", HTAAH_CAWANGANJPPH = "", HTAAH_JPPHCATATAN = "";
				String HTATH_NEGERI = "", HTATH_DAERAH = "", HTATH_MUKIM = "", HTATH_SEKSYEN = "", HTATH_NOPTLOT = "", HTATH_JENISPTLOT = "", HTATH_NOBANGUNAN = "", HTATH_JENISNOBANGUNAN = "";
				String HTATH_BASIMATI = "", HTATH_BBSIMATI = "", HTATH_JENISPEGANGAN = "", HTATH_TARIKHLUPUTPAJAKAN = "", HTATH_TEMPOHPAJAKAN = "", HTATH_KATEGORITANAH = "";
				String HTATH_SYARATNYATA = "", HTATH_SEKATANKEPENTINGAN = "", HTATH_ALAMAT1HARTA = "", HTATH_ALAMAT2HARTA = "", HTATH_ALAMAT3HARTA = "";
				String HTATH_NAMAPEMILIK = "", HTATH_TARIKHPERJANJIANJUALBELI = "", HTATH_NAMAPEMAJU = "", HTATH_LUASPETAK = "", HTATH_CATATAN = "";
				String HTATH_NILAITARIKHMOHONTANAH = "", HTATH_NILAITARIKHMOHONBANGUNAN = "", HTATH_NILAITARIKHMATITANAH = "", HTATH_NILAITARIKHMATIBANGUNAN = "";
				String HTATH_NAMAPEGAWAI = "", HTATH_CAWANGANJPPH = "", HTATH_JPPHCATATAN = "";
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					ID_JPPHAH = Utils.parseLong((String) h.get("ID_HTAAH"));
					HTAAH_NEGERI = (String) h.get("HTAAH_NEGERI");
					HTAAH_DAERAH = (String) h.get("HTAAH_DAERAH");
					HTAAH_MUKIM = (String) h.get("HTAAH_MUKIM");
					HTAAH_SEKSYEN = (String) h.get("HTAAH_SEKSYEN");
					HTAAH_JENISHAKMILIK = (String) h.get("HTAAH_JENISHAKMILIK");
					HTAAH_NOHAKMILIK = (String) h.get("HTAAH_NOHAKMILIK");
					HTAAH_NOPTLOT = (String) h.get("HTAAH_NOPTLOT");
					HTAAH_JENISPTLOT = (String) h.get("HTAAH_JENISPTLOT");
					HTAAH_NOBANGUNAN = (String) h.get("HTAAH_NOBANGUNAN");
					HTAAH_JENISNOBANGUNAN = (String) h.get("HTAAH_JENISNOBANGUNAN");
					HTAAH_BASIMATI = (String) h.get("HTAAH_BASIMATI");
					HTAAH_BBSIMATI = (String) h.get("HTAAH_BBSIMATI");
					HTAAH_JENISPEGANGAN = (String) h.get("HTAAH_JENISPEGANGAN");
					HTAAH_TARIKHLUPUTPAJAKAN = (String) h.get("HTAAH_TARIKHLUPUTPAJAKAN");
					HTAAH_TEMPOHPAJAKAN = (String) h.get("HTAAH_TEMPOHPAJAKAN");
					HTAAH_KATEGORITANAH = (String) h.get("HTAAH_KATEGORITANAH");
					HTAAH_SYARATNYATA = (String) h.get("HTAAH_SYARATNYATA");
					HTAAH_SEKATANKEPENTINGAN = (String) h.get("HTAAH_SEKATANKEPENTINGAN");
					HTAAH_LUASTANAH = (String) h.get("HTAAH_LUASTANAH");
					HTAAH_JENISLUASTANAH = (String) h.get("HTAAH_JENISLUASTANAH");
					HTAAH_LUASPETAK = (String) h.get("HTAAH_LUASPETAK");
					HTAAH_ALAMAT1HARTA = (String) h.get("HTAAH_ALAMAT1HARTA");
					HTAAH_ALAMAT2HARTA = (String) h.get("HTAAH_ALAMAT2HARTA");
					HTAAH_ALAMAT3HARTA = (String) h.get("HTAAH_ALAMAT3HARTA");
					HTAAH_JENISHARTADINILAI = (String) h.get("HTAAH_JENISHARTADINILAI");
					HTAAH_CATATAN = (String) h.get("HTAAH_CATATAN");
					HTAAH_NILAITARIKHMOHONTANAH = (String) h.get("HTAAH_NILAITARIKHMOHONTANAH");
					HTAAH_NILAITARIKHMOHONBANGUNAN = (String) h.get("HTAAH_NILAITARIKHMOHONBANGUNAN");
					HTAAH_NILAITARIKHMATITANAH = (String) h.get("HTAAH_NILAITARIKHMATITANAH");
					HTAAH_NILAITARIKHMATIBANGUNAN = (String) h.get("HTAAH_NILAITARIKHMATIBANGUNAN");
					HTAAH_NAMAPEGAWAI = (String) h.get("HTAAH_NAMAPEGAWAI");
					HTAAH_CAWANGANJPPH = (String) h.get("HTAAH_CAWANGANJPPH");
					HTAAH_JPPHCATATAN = (String) h.get("HTAAH_JPPHCATATAN");			
					CHILD_TABLE = "TBLINTJPPHAH";
					CHILD_FIELD = "ID_HTA";
					CHILD_VALUE = Long.toString(ID_JPPHAH);
					SEQUENCE_TABLE = "TBLINTJPPHTH_SEQ";
					ID_FIELD = "ID_JPPHAH";
				} else {
					ID_JPPHTH = Utils.parseLong((String) h.get("ID_HTATH"));
					HTATH_NEGERI = (String) h.get("HTATH_NEGERI");
					HTATH_DAERAH = (String) h.get("HTATH_DAERAH");
					HTATH_MUKIM = (String) h.get("HTATH_MUKIM");
					HTATH_SEKSYEN = (String) h.get("HTATH_SEKSYEN");
					HTATH_NOPTLOT = (String) h.get("HTATH_NOPTLOT");
					HTATH_JENISPTLOT = (String) h.get("HTATH_JENISPTLOT");
					HTATH_NOBANGUNAN = (String) h.get("HTATH_NOBANGUNAN");
					HTATH_JENISNOBANGUNAN = (String) h.get("HTATH_JENISNOBANGUNAN");
					HTATH_BASIMATI = (String) h.get("HTATH_BASIMATI");
					HTATH_BBSIMATI = (String) h.get("HTATH_BBSIMATI");
					HTATH_JENISPEGANGAN = (String) h.get("HTATH_JENISPEGANGAN");
					HTATH_TARIKHLUPUTPAJAKAN = (String) h.get("HTATH_TARIKHLUPUTPAJAKAN");
					HTATH_TEMPOHPAJAKAN = (String) h.get("HTATH_TEMPOHPAJAKAN");
					HTATH_KATEGORITANAH = (String) h.get("HTATH_KATEGORITANAH");
					HTATH_SYARATNYATA = (String) h.get("HTATH_SYARATNYATA");
					HTATH_SEKATANKEPENTINGAN = (String) h.get("HTATH_SEKATANKEPENTINGAN");
					HTATH_ALAMAT1HARTA = (String) h.get("HTATH_ALAMAT1HARTA");
					HTATH_ALAMAT2HARTA = (String) h.get("HTATH_ALAMAT2HARTA");
					HTATH_ALAMAT3HARTA = (String) h.get("HTATH_ALAMAT3HARTA");
					HTATH_NAMAPEMILIK = (String) h.get("HTATH_NAMAPEMILIK");
					HTATH_TARIKHPERJANJIANJUALBELI = (String) h.get("HTATH_TARIKHPERJANJIANJUALBELI");
					HTATH_NAMAPEMAJU = (String) h.get("HTATH_NAMAPEMAJU");
					HTATH_LUASPETAK = (String) h.get("HTATH_LUASPETAK");
					HTATH_CATATAN = (String) h.get("HTATH_CATATAN");
					HTATH_NILAITARIKHMOHONTANAH = (String) h.get("HTATH_NILAITARIKHMOHONTANAH");
					HTATH_NILAITARIKHMOHONBANGUNAN = (String) h.get("HTATH_NILAITARIKHMOHONBANGUNAN");
					HTATH_NILAITARIKHMATITANAH = (String) h.get("HTATH_NILAITARIKHMATITANAH");
					HTATH_NILAITARIKHMATIBANGUNAN = (String) h.get("HTATH_NILAITARIKHMATIBANGUNAN");
					HTATH_NAMAPEGAWAI = (String) h.get("HTATH_NAMAPEGAWAI");
					HTATH_CAWANGANJPPH = (String) h.get("HTATH_CAWANGANJPPH");
					HTATH_JPPHCATATAN = (String) h.get("HTATH_JPPHCATATAN");					
					CHILD_TABLE = "TBLINTJPPHTH";
					CHILD_FIELD = "ID_HTA";
					CHILD_VALUE = Long.toString(ID_JPPHTH);
					SEQUENCE_TABLE = "TBLINTJPPHTH_SEQ";
					ID_FIELD = "ID_JPPHTH";
				}
				
				// check in CHILD TABLE
				haveData = false;
				r.add("ID_JPPH");
				r.add("ID_JPPH", ID_JPPH);
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					r.add("ID_HTA", ID_JPPHAH);
				} else {
					r.add("ID_HTA", ID_JPPHTH);
				}
				sql = r.getSQLSelect(CHILD_TABLE);
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				
				if (haveData) {
					r.update("ID_JPPH", ID_JPPH);
					if ("0".equalsIgnoreCase(JENIS_HTA)) {
						r.update("ID_HTA", ID_JPPHAH);
					} else {
						r.update("ID_HTA", ID_JPPHTH);
					}
				}
				r.add("STATUS_PROSES", "BARU");
				r.add("ID_JPPH", ID_JPPH);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					r.add("ID_HTA", ID_JPPHAH);
					r.add("ID_NEGERI", HTAAH_NEGERI);
					r.add("ID_DAERAH", HTAAH_DAERAH);
					r.add("ID_MUKIM", HTAAH_MUKIM);
					r.add("ID_SEKSYEN", HTAAH_SEKSYEN);
					r.add("JENIS_HAKMILIK", HTAAH_JENISHAKMILIK);
					r.add("NO_HAKMILIK", HTAAH_NOHAKMILIK);
					r.add("NO_PTLOT", HTAAH_NOPTLOT);
					r.add("JENIS_PTLOT", HTAAH_JENISPTLOT);
					if ("1".equalsIgnoreCase(HTAAH_JENISNOBANGUNAN)) {
						r.add("NO_BANGUNAN", HTAAH_NOBANGUNAN);
					} else if ("2".equalsIgnoreCase(HTAAH_JENISNOBANGUNAN)) {
						r.add("NO_TINGKAT", HTAAH_NOBANGUNAN);
					} else {
						r.add("NO_STRATA", HTAAH_NOBANGUNAN);						
					}
					r.add("BA_SIMATI", HTAAH_BASIMATI);
					r.add("BB_SIMATI", HTAAH_BBSIMATI);
					r.add("JENIS_PEGANGAN", HTAAH_JENISPEGANGAN);
					r.add("TARIKH_LUPUT_PAJAKAN", r.unquote("TO_DATE('" + HTAAH_TARIKHLUPUTPAJAKAN + "', 'dd/MM/yyyy')"));
					r.add("TEMPOH_PAJAKAN", HTAAH_TEMPOHPAJAKAN);
					r.add("KATEGORI_TANAH", HTAAH_KATEGORITANAH);
					r.add("SYARAT_NYATA", HTAAH_SYARATNYATA);
					r.add("SEKATAN_KEPENTINGAN", HTAAH_SEKATANKEPENTINGAN);
					r.add("LUAS_TANAH", HTAAH_LUASTANAH);
					r.add("UNIT_LUAS_TANAH", HTAAH_JENISLUASTANAH);
					r.add("LUAS_PETAK", HTAAH_LUASPETAK);
					r.add("ALAMAT1_HARTA", HTAAH_ALAMAT1HARTA);
					r.add("ALAMAT2_HARTA", HTAAH_ALAMAT2HARTA);
					r.add("ALAMAT3_HARTA", HTAAH_ALAMAT3HARTA);
					r.add("JENIS_HARTA_DINILAI", HTAAH_JENISHARTADINILAI);
					r.add("CATATAN", HTAAH_CATATAN);
					r.add("JPPH_NILAI_TARIKH_MOHON_TANAH", HTAAH_NILAITARIKHMOHONTANAH);
					r.add("JPPH_NILAI_TARIKH_MOHON_BGN", HTAAH_NILAITARIKHMOHONBANGUNAN);
					r.add("JPPH_NILAI_TARIKH_MATI_TANAH", HTAAH_NILAITARIKHMATITANAH);
					r.add("JPPH_NILAI_TARIKH_MATI_BGN", HTAAH_NILAITARIKHMATIBANGUNAN);
					r.add("JPPH_NAMA_PEGAWAI", HTAAH_NAMAPEGAWAI);
					r.add("JPPH_CAWANGAN_JPPH", HTAAH_CAWANGANJPPH);
					r.add("JPPH_CATATAN", HTAAH_JPPHCATATAN);
				} else {
					r.add("ID_HTA", ID_JPPHTH);
					r.add("ID_NEGERI", HTATH_NEGERI);
					r.add("ID_DAERAH", HTATH_DAERAH);
					r.add("ID_MUKIM", HTATH_MUKIM);
					r.add("ID_SEKSYEN", HTATH_SEKSYEN);
					r.add("NO_PTLOT", HTATH_NOPTLOT);
					r.add("JENIS_PTLOT", HTATH_JENISPTLOT);
					if ("1".equalsIgnoreCase(HTATH_JENISNOBANGUNAN)) {
						r.add("NO_BANGUNAN", HTATH_NOBANGUNAN);
					} else if ("2".equalsIgnoreCase(HTATH_JENISNOBANGUNAN)) {
						r.add("NO_TINGKAT", HTATH_NOBANGUNAN);
					} else {
						r.add("NO_STRATA", HTATH_NOBANGUNAN);						
					}
					r.add("BA_SIMATI", HTATH_BASIMATI);
					r.add("BB_SIMATI", HTATH_BBSIMATI);
					r.add("JENIS_PEGANGAN", HTATH_JENISPEGANGAN);
					r.add("TARIKH_LUPUT_PAJAKAN", r.unquote("TO_DATE('" + HTATH_TARIKHLUPUTPAJAKAN + "', 'dd/MM/yyyy')"));
					r.add("TEMPOH_PAJAKAN", HTATH_TEMPOHPAJAKAN);
					r.add("KATEGORI_TANAH", HTATH_KATEGORITANAH);
					r.add("SYARAT_NYATA", HTATH_SYARATNYATA);
					r.add("SEKATAN_KEPENTINGAN", HTATH_SEKATANKEPENTINGAN);
					r.add("ALAMAT1_HARTA", HTATH_ALAMAT1HARTA);
					r.add("ALAMAT2_HARTA", HTATH_ALAMAT2HARTA);
					r.add("ALAMAT3_HARTA", HTATH_ALAMAT3HARTA);
					r.add("NAMA_PEMILIK", HTATH_NAMAPEMILIK);
					r.add("TARIKH_PERJANJIAN", r.unquote("TO_DATE('" + HTATH_TARIKHPERJANJIANJUALBELI + "', 'dd/MM/yyyy')"));
					r.add("NAMA_PEMAJU", HTATH_NAMAPEMAJU);
					r.add("LUAS_PETAK", HTATH_LUASPETAK);
					r.add("CATATAN", HTATH_CATATAN);
					r.add("JPPH_NILAI_TARIKH_MOHON_TANAH", HTATH_NILAITARIKHMOHONTANAH);
					r.add("JPPH_NILAI_TARIKH_MOHON_BGN", HTATH_NILAITARIKHMOHONBANGUNAN);
					r.add("JPPH_NILAI_TARIKH_MATI_TANAH", HTATH_NILAITARIKHMATITANAH);
					r.add("JPPH_NILAI_TARIKH_MATI_BGN", HTATH_NILAITARIKHMATIBANGUNAN);
					r.add("JPPH_NAMA_PEGAWAI", HTATH_NAMAPEGAWAI);
					r.add("JPPH_CAWANGAN_JPPH", HTATH_CAWANGANJPPH);
					r.add("JPPH_CATATAN", HTATH_JPPHCATATAN);
				}
				if (haveData) {
					sql = r.getSQLUpdate(CHILD_TABLE);
				} else {
					ID_JPPHAH = DB.getNextID(SEQUENCE_TABLE);
					r.add(ID_FIELD, ID_JPPHAH);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
					sql = r.getSQLInsert(CHILD_TABLE);
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
	
	public Boolean deleteNilaianHTA(String ID_PERMOHONAN, String JENIS_HTA, String ID_HTA) throws Exception {
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
}