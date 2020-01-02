package ekptg.model.integrasi;

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

public class FrmModelNilaianHartaAlihKenderaan {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String SelectBahanBakar(String SOC_NAME) throws Exception {
		return SelectBahanBakar(SOC_NAME, 0, "", "");
	}
	public String SelectBahanBakar(String SOC_NAME, long SELECTED_BAHANBAKAR) throws Exception {
		return SelectBahanBakar(SOC_NAME, SELECTED_BAHANBAKAR, "", "");
	}
	public String SelectBahanBakar(String SOC_NAME, long SELECTED_BAHANBAKAR, String DISABLED) throws Exception {
		return SelectBahanBakar(SOC_NAME, SELECTED_BAHANBAKAR, DISABLED, "");
	}
	public String SelectBahanBakar(String SOC_NAME, long SELECTED_BAHANBAKAR, String DISABLED, String ONCHANGE) throws Exception {
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
			sql = "SELECT ID_BAHANBAKAR, KOD_BAHANBAKAR, KETERANGAN FROM TBLINTRUJBAHANBAKAR " +
				SQL_SEARCH + "ORDER BY KOD_BAHANBAKAR, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_BAHANBAKAR == ID_DATA) {
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
	
	public String SelectBuatan(String SOC_NAME) throws Exception {
		return SelectBuatan(SOC_NAME, 0, "", "");
	}
	public String SelectBuatan(String SOC_NAME, long SELECTED_BUATAN) throws Exception {
		return SelectBuatan(SOC_NAME, SELECTED_BUATAN, "", "");
	}
	public String SelectBuatan(String SOC_NAME, long SELECTED_BUATAN, String DISABLED) throws Exception {
		return SelectBuatan(SOC_NAME, SELECTED_BUATAN, DISABLED, "");
	}
	public String SelectBuatan(String SOC_NAME, long SELECTED_BUATAN, String DISABLED, String ONCHANGE) throws Exception {
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
			sql = "SELECT ID_BUATAN, KOD_BUATAN, KETERANGAN FROM TBLINTRUJBUATAN " +
				SQL_SEARCH + "ORDER BY KOD_BUATAN, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_BUATAN == ID_DATA) {
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

	public String SelectJenisBadan(String SOC_NAME) throws Exception {
		return SelectJenisBadan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisBadan(String SOC_NAME, long SELECTED_JENISBADAN) throws Exception {
		return SelectJenisBadan(SOC_NAME, SELECTED_JENISBADAN, "", "");
	}
	public String SelectJenisBadan(String SOC_NAME, long SELECTED_JENISBADAN, String DISABLED) throws Exception {
		return SelectJenisBadan(SOC_NAME, SELECTED_JENISBADAN, DISABLED, "");
	}
	public String SelectJenisBadan(String SOC_NAME, long SELECTED_JENISBADAN, String DISABLED, String ONCHANGE) throws Exception {
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
			sql = "SELECT ID_JENISBADAN, KOD_JENISBADAN, KETERANGAN FROM TBLINTRUJJENISBADAN " +
				SQL_SEARCH + "ORDER BY KOD_JENISBADAN, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_JENISBADAN == ID_DATA) {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchHAK(String NO_FAIL, String NO_PENDAFTARAN, String ID_NEGERILOKASI, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FA.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PENDAFTARAN)) {
				SQL_SEARCH = " AND UPPER(M.NO_PENDAFTARAN) LIKE '%" + NO_PENDAFTARAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SQL_SEARCH = " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_SIMATI)) {
				SQL_SEARCH = " AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%')";
			}
			sql = "SELECT M.ID_PERMOHONAN, M.ID_HA, FA.NO_FAIL, M.NO_PENDAFTARAN, NG.NAMA_NEGERI, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA " +
				"FROM TBLINTJPPHHA M, TBLPFDFAIL FA, TBLRUJNEGERI NG, tblppksimati sm, tblppkpermohonansimati ps, tblppkpermohonan p " +
				"WHERE M.ID_FAIL = FA.ID_FAIL AND M.ID_NEGERILOKASI = NG.ID_NEGERI(+) " +
				" AND p.ID_FAIL = FA.ID_FAIL and p.ID_PERMOHONAN = ps.ID_PERMOHONAN and ps.ID_SIMATI = sm.ID_SIMATI" +
				SQL_SEARCH + " ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI, FA.NO_FAIL, SM.NAMA_SIMATI";
			int i = 1;
			String ID_PERMOHONAN = "", ID_HA = "", NAMA_NEGERI = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			rs = stmt.executeQuery(sql);
			
			
			
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_HA = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NO_PENDAFTARAN = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_NEGERI = rs.getString(5) == null ? "" : rs.getString(5);
				NAMA_SIMATI = rs.getString(6) == null ? "" : rs.getString(6);
				NO_KP_BARU = rs.getString(7) == null ? "" : rs.getString(7);
				NO_KP_LAMA = rs.getString(8) == null ? "" : rs.getString(8);
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}

				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("IDHA", ID_HA);
				h.put("NoFail", NO_FAIL);
				h.put("NoKenderaan", NO_PENDAFTARAN);
				h.put("NegeriKenderaanBerada", NAMA_NEGERI);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
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

	public Boolean isPermohonanSelesai(String ID_PERMOHONAN, String ID_HA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HA)) {
				String sql = "", TEMP = "";
				sql = "SELECT UPPER(STATUS_PROSES) " +
					"FROM TBLINTJPPHHA " +
					"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HA = " + ID_HA;
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
	public Vector viewHAKMaklumatPermohonan(String ID_PERMOHONAN, String ID_HA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				String MP_NOFAIL = "", MP_NEGERI = "", MP_DAERAH = "", MP_UNIT = "";
				String MP_TARIKHMOHON = "", MP_NAMAPEMOHON = "", MP_NOKPPEMOHON = "", MP_ALAMATPEMOHON = "", MP_NOTELPEMOHON = "";
				String MP_NAMASIMATI = "", MP_NOKPSIMATI = "", MP_TARIKHMATI = "";

				sql = "SELECT UNIT FROM TBLINTJPPHHA WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HA = " + ID_HA;				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					MP_UNIT = rs.getString(1) == null ? "" : rs.getString(1);
				}				
				sql = "SELECT FA.NO_FAIL, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TARIKH_MOHON, MHN.NAMA_PEMOHON, " +
					"CASE WHEN MHN.NO_KP_LAIN <> '' THEN MHN.NO_KP_LAIN ELSE CASE WHEN MHN.NO_KP_LAMA <> '' THEN MHN.NO_KP_LAMA ELSE MHN.NO_KP_BARU END END AS NO_KP_PEMOHON, " +
					"MHN.ALAMAT_1 || ', ' || MHN.ALAMAT_2 || ', ' || MHN.ALAMAT_3, MHN.NO_TEL, SM.NAMA_SIMATI, " +
					"CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, SM.TARIKH_MATI " +
					"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PMHN, TBLPFDFAIL FA, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
					"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FA.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON AND PMHN.ID_NEGERIMHN = NG.ID_NEGERI AND PMHN.ID_DAERAHMHN = DE.ID_DAERAH " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HA = " + ID_HA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					MP_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
					MP_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
					MP_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
					MP_TARIKHMOHON = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					MP_NAMAPEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
					MP_NOKPPEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
					MP_ALAMATPEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					MP_NOTELPEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
					MP_NAMASIMATI = rs.getString(9) == null ? "" : rs.getString(9);
					MP_NOKPSIMATI = rs.getString(10) == null ? "" : rs.getString(10);
					MP_TARIKHMATI = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
				}
				
				if (!haveINTData) {
					sql = "SELECT FA.NO_FAIL, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TARIKH_MOHON, MHN.NAMA_PEMOHON, " +
						"CASE WHEN MHN.NO_KP_LAIN <> '' THEN MHN.NO_KP_LAIN ELSE CASE WHEN MHN.NO_KP_LAMA <> '' THEN MHN.NO_KP_LAMA ELSE MHN.NO_KP_BARU END END AS NO_KP_PEMOHON, " +
						"MHN.ALAMAT_1 || ', ' || MHN.ALAMAT_2 || ', ' || MHN.ALAMAT_3, MHN.NO_TEL, SM.NAMA_SIMATI, " +
						"CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, SM.TARIKH_MATI " +
						"FROM TBLPPKPERMOHONAN PMHN, TBLPFDFAIL FA, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
						"WHERE PMHN.ID_FAIL = FA.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON AND PMHN.ID_NEGERIMHN = NG.ID_NEGERI AND PMHN.ID_DAERAHMHN = DE.ID_DAERAH " +
						"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						MP_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
						MP_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
						MP_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
						MP_TARIKHMOHON = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
						MP_NAMAPEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
						MP_NOKPPEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
						MP_ALAMATPEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
						MP_NOTELPEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
						MP_NAMASIMATI = rs.getString(9) == null ? "" : rs.getString(9);
						MP_NOKPSIMATI = rs.getString(10) == null ? "" : rs.getString(10);
						MP_TARIKHMATI = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
					}
				}
				h = new Hashtable();
    			h.put("MP_NOFAIL", MP_NOFAIL);
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
				h.put("haveINTData", haveINTData);
				v.add(h);				
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector viewNilaianHAK(String ID_HA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				String ALAMAT1_LOKASI = "", ALAMAT2_LOKASI = "", ALAMAT3_LOKASI = "", ID_NEGERILOKASI = "", ID_BANDARLOKASI = "";
				String NO_PENDAFTARAN = "", JENIS_BADAN = "", JENIS_BUATAN = "", NAMA_MODEL = "", KEUPAYAAN_ENJIN = "";
				String TAHUN_DIBUAT = "", NO_ENJIN = "", NO_CASIS = "", JENIS_BAHANBAKAR = "", CATATAN = "";
				String NAMA_PEGAWAI_JKPTG = "", NO_TEL_PEGAWAI_JKPTG = "", CAWANGAN_PEGAWAI_JKPTG = "", EMAIL_ADDR_PEGAWAI_JPPH = "", EMAIL_SEND_JPPH = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "", JPPH_TARIKH_LAWAT_PERIKSA = "";

				sql = "SELECT ALAMAT1_LOKASI, ALAMAT2_LOKASI, ALAMAT3_LOKASI, ID_NEGERILOKASI, ID_BANDARLOKASI, " +
					"NO_PENDAFTARAN, JENIS_BADAN, JENIS_BUATAN, NAMA_MODEL, KEUPAYAAN_ENJIN, TAHUN_DIBUAT, NO_ENJIN, NO_CASIS, JENIS_BAHANBAKAR, CATATAN, " +
					"NAMA_PEGAWAI_JKPTG, NO_TEL_PEGAWAI_JKPTG, CAWANGAN_PEGAWAI_JKPTG, EMAIL_ADDR_PEGAWAI_JPPH, EMAIL_SEND_JPPH, " +
					"JPPH_NILAI_TARIKH_MOHON, JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN, JPPH_TARIKH_LAWAT_PERIKSA " +
					"FROM TBLINTJPPHHA " +
					"WHERE ID_HA = " + ID_HA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ALAMAT1_LOKASI = rs.getString(1) == null ? "" : rs.getString(1);
					ALAMAT2_LOKASI = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT3_LOKASI = rs.getString(3) == null ? "" : rs.getString(3);
					ID_NEGERILOKASI = rs.getString(4) == null ? "" : rs.getString(4);
					ID_BANDARLOKASI = rs.getString(5) == null ? "" : rs.getString(5);
					NO_PENDAFTARAN = rs.getString(6) == null ? "" : rs.getString(6);
					JENIS_BADAN = rs.getString(7) == null ? "" : rs.getString(7);
					JENIS_BUATAN = rs.getString(8) == null ? "" : rs.getString(8);
					NAMA_MODEL = rs.getString(9) == null ? "" : rs.getString(9);
					KEUPAYAAN_ENJIN = rs.getString(10) == null ? "" : rs.getString(10);
					TAHUN_DIBUAT = rs.getString(11) == null ? "" : rs.getString(11);
					NO_ENJIN = rs.getString(12) == null ? "" : rs.getString(12);
					NO_CASIS = rs.getString(13) == null ? "" : rs.getString(13);
					JENIS_BAHANBAKAR = rs.getString(14) == null ? "" : rs.getString(14);
					CATATAN = rs.getString(15) == null ? "" : rs.getString(15);
					NAMA_PEGAWAI_JKPTG = rs.getString(16) == null ? "" : rs.getString(16);
					NO_TEL_PEGAWAI_JKPTG = rs.getString(17) == null ? "" : rs.getString(17);
					CAWANGAN_PEGAWAI_JKPTG = rs.getString(18) == null ? "" : rs.getString(18);
					EMAIL_ADDR_PEGAWAI_JPPH = rs.getString(19) == null ? "" : rs.getString(19);
					EMAIL_SEND_JPPH = rs.getString(20) == null ? "" : rs.getString(20);
					JPPH_NILAI_TARIKH_MOHON = rs.getString(21) == null ? "" : rs.getString(21);
					JPPH_NAMA_PEGAWAI = rs.getString(22) == null ? "" : rs.getString(22);
					JPPH_CAWANGAN_JPPH = rs.getString(23) == null ? "" : rs.getString(23);
					JPPH_CATATAN = rs.getString(24) == null ? "" : rs.getString(24);
					JPPH_TARIKH_LAWAT_PERIKSA = rs.getDate(25) == null ? "" : sdf.format(rs.getDate(25));
				}
				
				if (!haveINTData) {
					sql = "SELECT '', '', '', '', '', HAK.NO_DAFTAR, '', '', HAK.JENAMA, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' " +
						"FROM TBLPPKHA HAK " +
						"WHERE HAK.ID_HA = " + ID_HA;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ALAMAT1_LOKASI = rs.getString(1) == null ? "" : rs.getString(1);
						ALAMAT2_LOKASI = rs.getString(2) == null ? "" : rs.getString(2);
						ALAMAT3_LOKASI = rs.getString(3) == null ? "" : rs.getString(3);
						ID_NEGERILOKASI = rs.getString(4) == null ? "" : rs.getString(4);
						ID_BANDARLOKASI = rs.getString(5) == null ? "" : rs.getString(5);
						NO_PENDAFTARAN = rs.getString(6) == null ? "" : rs.getString(6);
						JENIS_BADAN = rs.getString(7) == null ? "" : rs.getString(7);
						JENIS_BUATAN = rs.getString(8) == null ? "" : rs.getString(8);
						NAMA_MODEL = rs.getString(9) == null ? "" : rs.getString(9);
						KEUPAYAAN_ENJIN = rs.getString(10) == null ? "" : rs.getString(10);
						TAHUN_DIBUAT = rs.getString(11) == null ? "" : rs.getString(11);
						NO_ENJIN = rs.getString(12) == null ? "" : rs.getString(12);
						NO_CASIS = rs.getString(13) == null ? "" : rs.getString(13);
						JENIS_BAHANBAKAR = rs.getString(14) == null ? "" : rs.getString(14);
						CATATAN = rs.getString(15) == null ? "" : rs.getString(15);
						NAMA_PEGAWAI_JKPTG = rs.getString(16) == null ? "" : rs.getString(16);
						NO_TEL_PEGAWAI_JKPTG = rs.getString(17) == null ? "" : rs.getString(17);
						CAWANGAN_PEGAWAI_JKPTG = rs.getString(18) == null ? "" : rs.getString(18);
						EMAIL_ADDR_PEGAWAI_JPPH = rs.getString(19) == null ? "" : rs.getString(19);
						EMAIL_SEND_JPPH = rs.getString(20) == null ? "" : rs.getString(20);
						JPPH_NILAI_TARIKH_MOHON = rs.getString(21) == null ? "" : rs.getString(21);
						JPPH_NAMA_PEGAWAI = rs.getString(22) == null ? "" : rs.getString(22);
						JPPH_CAWANGAN_JPPH = rs.getString(23) == null ? "" : rs.getString(23);
						JPPH_CATATAN = rs.getString(24) == null ? "" : rs.getString(24);
						JPPH_TARIKH_LAWAT_PERIKSA = rs.getDate(25) == null ? "" : sdf.format(rs.getDate(25));
					}
				}

				h = new Hashtable();
				h.put("ALAMAT1_LOKASI", ALAMAT1_LOKASI);
				h.put("ALAMAT2_LOKASI", ALAMAT2_LOKASI);
				h.put("ALAMAT3_LOKASI", ALAMAT3_LOKASI);
				h.put("ID_NEGERILOKASI", ID_NEGERILOKASI);
				h.put("ID_BANDARLOKASI", ID_BANDARLOKASI);
				h.put("NO_PENDAFTARAN", NO_PENDAFTARAN);
				h.put("JENIS_BADAN", JENIS_BADAN);
				h.put("JENIS_BUATAN", JENIS_BUATAN);
				h.put("NAMA_MODEL", NAMA_MODEL);
				h.put("KEUPAYAAN_ENJIN", KEUPAYAAN_ENJIN);
				h.put("TAHUN_DIBUAT", TAHUN_DIBUAT);
				h.put("NO_ENJIN", NO_ENJIN);
				h.put("NO_CASIS", NO_CASIS);
				h.put("JENIS_BAHANBAKAR", JENIS_BAHANBAKAR);
				h.put("CATATAN", CATATAN);
				h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
				h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
				h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
				h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
				h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				h.put("haveINTData", haveINTData.toString());
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean sendNilaianHAK(String ID_PERMOHONAN, String ID_HA, String USER_ID, Boolean isJPPHUser) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HA)) {
				Statement stmt = db.getStatement();
				String sql = "";
				
				String STATUS = "";
				if (isJPPHUser) {
					STATUS = "SELESAI";
				} else {
					STATUS = "BARU";
				}
				
				sql = "UPDATE TBLINTJPPHHA SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HA = " + ID_HA;
				stmt.execute(sql);
				
				// should send email to JKPTG user if nilaian is completed by JPPH
				if (isJPPHUser) {
					// ****************************
					// sending e-mail codes
					ResultSet rs = null;
	
					// get NO_FAIL
					String NO_FAIL = "";
					sql = "SELECT FA.NO_FAIL " +
						"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA " +
						"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " +
						"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HA = " + ID_HA;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					}
					
					// get e-mail address of JKPTG user that saves this file
					String EMAIL_ADDR = "";
					sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						EMAIL_ADDR = rs.getString(1) == null ? "" : rs.getString(1);
					}
					
					// temporary hardcode
					EMAIL_ADDR = "zulkhaimi@hla-group.com";
	
					String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
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

					if (!"".equalsIgnoreCase(EMAIL_ADDR.trim()) && !"".equalsIgnoreCase(EMAIL_FROM.trim()) 
							&& !"".equalsIgnoreCase(EMAIL_SUBJECT.trim()) && !"".equalsIgnoreCase(EMAIL_BODY.trim())) {
						EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
						if (isJPPHUser) {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
						} else {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
						}
						Date date = new Date();
						EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Alih (Kenderaan)");
						EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
						EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS);
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
					// ****************************				
				}
				
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public Boolean saveNilaianHAK(String ID_PERMOHONAN, String ID_HA, String USER_ID, Boolean isJPPHUser, Hashtable h) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN.trim()) && !"".equalsIgnoreCase(ID_HA.trim()) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				long ID_JPPHHA = 0;
				String STATUS_PROSES = "", UNIT = "", JENIS_HA = "", ALAMAT1_LOKASI = "", ALAMAT2_LOKASI = "", ALAMAT3_LOKASI = "", ID_NEGERILOKASI = "", ID_BANDARLOKASI = "";
				String NO_PENDAFTARAN = "", JENIS_BADAN = "", JENIS_BUATAN = "", NAMA_MODEL = "", KEUPAYAAN_ENJIN = "", TAHUN_DIBUAT = "", NO_ENJIN = "";
				String NO_CASIS = "", JENIS_BAHANBAKAR = "", ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "", CATATAN = "", NAMA_PEGAWAI_JKPTG = "", NO_TEL_PEGAWAI_JKPTG = "", CAWANGAN_PEGAWAI_JKPTG = "";
				String EMAIL_ADDR_PEGAWAI_JPPH = "", EMAIL_SEND_JPPH = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "", JPPH_TARIKH_LAWAT_PERIKSA = "";
				
				UNIT = (String) h.get("UNIT").toString().toUpperCase();
				JENIS_HA = (String) h.get("JENIS_HA");
				ALAMAT1_LOKASI = (String) h.get("ALAMAT1_LOKASI");
				ALAMAT2_LOKASI = (String) h.get("ALAMAT2_LOKASI");
				ALAMAT3_LOKASI = (String) h.get("ALAMAT3_LOKASI");
				ID_NEGERILOKASI = (String) h.get("ID_NEGERILOKASI");
				ID_BANDARLOKASI = (String) h.get("ID_BANDARLOKASI");
				NO_PENDAFTARAN = (String) h.get("NO_PENDAFTARAN");
				JENIS_BADAN = (String) h.get("JENIS_BADAN");
				JENIS_BUATAN = (String) h.get("JENIS_BUATAN");
				NAMA_MODEL = (String) h.get("NAMA_MODEL");
				KEUPAYAAN_ENJIN = (String) h.get("KEUPAYAAN_ENJIN");
				TAHUN_DIBUAT = (String) h.get("TAHUN_DIBUAT");
				NO_ENJIN = (String) h.get("NO_ENJIN");
				NO_CASIS = (String) h.get("NO_CASIS");
				JENIS_BAHANBAKAR = (String) h.get("JENIS_BAHANBAKAR");
				ALAMAT1_HARTA = (String) h.get("ALAMAT1_HARTA");
				ALAMAT2_HARTA = (String) h.get("ALAMAT2_HARTA");
				ALAMAT3_HARTA = (String) h.get("ALAMAT3_HARTA");
				CATATAN = (String) h.get("CATATAN");
				NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG");
				NO_TEL_PEGAWAI_JKPTG = (String) h.get("NO_TEL_PEGAWAI_JKPTG");
				CAWANGAN_PEGAWAI_JKPTG = (String) h.get("CAWANGAN_PEGAWAI_JKPTG");
				EMAIL_ADDR_PEGAWAI_JPPH = (String) h.get("EMAIL_ADDR_PEGAWAI_JPPH");
				EMAIL_SEND_JPPH = (String) h.get("EMAIL_SEND_JPPH");
				JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_TARIKH_MOHON");
				JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI");
				JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_CAWANGAN_JPPH");
				JPPH_CATATAN = (String) h.get("JPPH_CATATAN");
				JPPH_TARIKH_LAWAT_PERIKSA = (String) h.get("JPPH_TARIKH_LAWAT_PERIKSA");
				
				if (isJPPHUser) {
					STATUS_PROSES = "DALAM PROSES JPPH";
				} else {
					STATUS_PROSES = "BARU";
				}
				
				String TABLE_NAME = "TBLINTJPPHHA", SEQ_NAME = "TBLINTJPPHHA_SEQ";
				
				// check in TBLINTJPPHHA, if exists just update
				r.clear();
				r.add("ID_JPPHHA");
				r.add("ID_HA", ID_HA);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect(TABLE_NAME);				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_JPPHHA = rs.getInt(1);
					haveData = true;
				}
				
				r.clear();
				if (haveData) {
					r.update("ID_JPPHHA", ID_JPPHHA);
				} else {
					r.add("ID_SIMPAN", USER_ID);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				}
				r.add("STATUS_PROSES", STATUS_PROSES);
				if (!isJPPHUser) {
					r.add("ID_PERMOHONAN", ID_PERMOHONAN);
					r.add("ID_HA", ID_HA);
					r.add("ID_KEMASKINI", USER_ID);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("UNIT", UNIT);
					r.add("JENIS_HA", JENIS_HA);
					r.add("ALAMAT1_LOKASI", ALAMAT1_LOKASI);
					r.add("ALAMAT2_LOKASI", ALAMAT2_LOKASI);
					r.add("ALAMAT3_LOKASI", ALAMAT3_LOKASI);
					r.add("ID_NEGERILOKASI", ID_NEGERILOKASI);
					r.add("ID_BANDARLOKASI", ID_BANDARLOKASI);
					r.add("NO_PENDAFTARAN", NO_PENDAFTARAN);
					r.add("JENIS_BADAN", JENIS_BADAN);
					r.add("JENIS_BUATAN", JENIS_BUATAN);
					r.add("NAMA_MODEL", NAMA_MODEL);
					r.add("KEUPAYAAN_ENJIN", KEUPAYAAN_ENJIN);
					r.add("TAHUN_DIBUAT", TAHUN_DIBUAT);
					r.add("NO_ENJIN", NO_ENJIN);
					r.add("NO_CASIS", NO_CASIS);
					r.add("JENIS_BAHANBAKAR", JENIS_BAHANBAKAR);
					r.add("ALAMAT1_HARTA", ALAMAT1_HARTA);
					r.add("ALAMAT2_HARTA", ALAMAT2_HARTA);
					r.add("ALAMAT3_HARTA", ALAMAT3_HARTA);
					r.add("CATATAN", CATATAN);
					r.add("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
					r.add("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
					r.add("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
					r.add("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
					r.add("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				} else {
					r.add("ID_KEMASKINI_JPPH", USER_ID);
					r.add("TARIKH_KEMASKINI_JPPH", r.unquote("SYSDATE"));
					r.add("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
					r.add("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
					r.add("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
					r.add("JPPH_CATATAN", JPPH_CATATAN);
					r.add("JPPH_TARIKH_LAWAT_PERIKSA", r.unquote("TO_DATE('" + JPPH_TARIKH_LAWAT_PERIKSA + "', 'dd/MM/yyyy')"));
				}
				if (haveData) {
					sql = r.getSQLUpdate(TABLE_NAME);
				} else {
					ID_JPPHHA = DB.getNextID(SEQ_NAME);
					r.add("ID_JPPHHA", ID_JPPHHA);
					sql = r.getSQLInsert(TABLE_NAME);
				}
				stmt.execute(sql);
				r.clear();			

				// ****************************
				// sending e-mail codes
				String NO_FAIL = "";
				sql = "SELECT FA.NO_FAIL " +
					"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA " +
					"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HA = " + ID_HA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}

				// get NO_FAIL
				String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
				if (!isJPPHUser) {
					if ("1".equalsIgnoreCase(EMAIL_SEND_JPPH) && !"".equalsIgnoreCase(EMAIL_ADDR_PEGAWAI_JPPH)) {
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
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Alih (Kenderaan)");
							EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
							EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS_PROSES);
							EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
							XEkptgEmailSender email = XEkptgEmailSender.getInstance();
							email.FROM = EMAIL_FROM;
							email.MULTIPLE_RECIEPIENT = new String[1];
							email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR_PEGAWAI_JPPH;
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
							email.RECIEPIENT = EMAIL_ADDR_PEGAWAI_JPPH;
							email.SUBJECT = EMAIL_SUBJECT;
							email.MESSAGE = EMAIL_BODY;
							email.sendEmail();				
						}
					} else {
						EMAIL_SEND_JPPH = "0";
					}
				}
				// ****************************
				
				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean returnNilaianHAK(String ID_HA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "UPDATE TBLINTJPPHHA SET STATUS_PROSES = 'DIKEMBALIKAN', TARIKH_KEMASKINI = SYSDATE WHERE ID_HA = " + ID_HA;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public Boolean deleteNilaianHAK(String ID_HA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "DELETE FROM TBLINTJPPHHA WHERE ID_HA = " + ID_HA;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public Boolean changeStatusPermohonanForJPPH(String ID_HA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "UPDATE TBLINTJPPHHA SET STATUS_PROSES = 'DALAM PROSES JPPH', TARIKH_KEMASKINI = SYSDATE WHERE STATUS_PROSES = 'BARU' AND ID_HA = " + ID_HA;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
}