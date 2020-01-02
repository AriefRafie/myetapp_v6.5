package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmModelTestWebService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchHakmilik(String NO_FAIL, String NO_PERMOHONAN, String NO_HAKMILIK, String NAMA_SIMATI, String NO_KP_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String SEARCH_REKOD = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SEARCH_REKOD += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SEARCH_REKOD += "AND UPPER(PM.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_HAKMILIK)) {
				SEARCH_REKOD += "AND UPPER(HTA.NO_HAKMILIK) LIKE '%" + NO_HAKMILIK.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SEARCH_REKOD += "AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_KP_SIMATI)) {
				SEARCH_REKOD += "AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NO_KP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NO_KP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_BARU) LIKE '%" + NO_KP_SIMATI.toUpperCase() + "%') ";
			}
			String sql = "";
			sql = "SELECT HTA.ID_HTA, FAIL.NO_FAIL, PM.NO_PERMOHONAN, HTA.NO_HAKMILIK, SM.NAMA_SIMATI,  " +
				"CASE WHEN SM.NO_KP_BARU = '' THEN (CASE WHEN SM.NO_KP_LAMA = '' THEN SM.NO_KP_LAIN ELSE SM.NO_KP_LAMA END) ELSE SM.NO_KP_BARU END AS NO_KP_SIMATI " +
				"FROM TBLPPKPERMOHONAN PM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPFDFAIL FAIL, TBLPPKHTA HTA " +
				"WHERE PM.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND PM.ID_FAIL = FAIL.ID_FAIL AND PSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " + SEARCH_REKOD +
				"ORDER BY FAIL.NO_FAIL, PM.NO_PERMOHONAN, HTA.NO_HAKMILIK, SM.NAMA_SIMATI";
			int i = 1;
			String ID_HTA = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_HTA = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NO_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_SIMATI = rs.getString(6) == null ? "" : rs.getString(6);
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDHakmilik", ID_HTA);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NoHakmilik", NO_HAKMILIK);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP_SIMATI);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public String getNoHakMilik(String ID_HAKMILIK) throws Exception {
		String NO_HAKMILIK = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				sql = "SELECT HTA.NO_HAKMILIK  " +
					"FROM TBLPPKHTA HTA " +
					"WHERE HTA.ID_HTA = " + ID_HAKMILIK.toUpperCase();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_HAKMILIK = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null) db.close();	
		}
		return NO_HAKMILIK;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getWSDetails() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String WS_USER = "", WS_PASS = "", WS_URL = "";
			String sql = "";
			ResultSet rs = null;
			Statement stmt = db.getStatement();
			Hashtable h = new Hashtable();
			sql = "SELECT WS_USERNAME, WS_PASSWORD, WS_URL " +
				"FROM TBLINTETANAHCFG " +
				"WHERE ROWNUM <= 1";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				WS_USER = rs.getString(1) == null ? "" : rs.getString(1);
				WS_PASS = rs.getString(2) == null ? "" : rs.getString(2);
				WS_URL = rs.getString(3) == null ? "" : rs.getString(3);
				h.put("WS_USER", WS_USER);
				h.put("WS_PASS", WS_PASS);
				h.put("WS_URL", WS_URL);
				v.add(h);
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getHakMilik(String ID_HAKMILIK) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK)) {
				String NO_FAIL = "", NO_PERMOHONAN = "", NO_HAKMILIK = "", NAMA_SIMATI = "", NO_KP_SIMATI = "";
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				Hashtable h = new Hashtable();
				sql = "SELECT FAIL.NO_FAIL, PM.NO_PERMOHONAN, HTA.NO_HAKMILIK, SM.NAMA_SIMATI,  " +
					"CASE WHEN SM.NO_KP_BARU = '' THEN (CASE WHEN SM.NO_KP_LAMA = '' THEN SM.NO_KP_LAIN ELSE SM.NO_KP_LAMA END) ELSE SM.NO_KP_BARU END AS NO_KP_SIMATI " +
					"FROM TBLPPKPERMOHONAN PM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPFDFAIL FAIL, TBLPPKHTA HTA " +
					"WHERE PM.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND PM.ID_FAIL = FAIL.ID_FAIL AND PSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " + 
					"AND UPPER(HTA.ID_HTA) = '" + ID_HAKMILIK.toUpperCase() + "' " +
					"ORDER BY FAIL.NO_FAIL, PM.NO_PERMOHONAN, HTA.NO_HAKMILIK, SM.NAMA_SIMATI";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_PERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
					NO_HAKMILIK = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
					NO_KP_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
					h.put("NO_FAIL", NO_FAIL);
					h.put("NO_PERMOHONAN", NO_PERMOHONAN);
					h.put("NO_HAKMILIK", NO_HAKMILIK);
					h.put("NAMA_SIMATI", NAMA_SIMATI);
					h.put("NO_KP_SIMATI", NO_KP_SIMATI);
					v.add(h);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public String getETIDHakMilik(String ID_HAKMILIK) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK)) {
				String KOD_NEGERI = "", KOD_DAERAH = "", KOD_MUKIM = "", KOD_JENIS_HAKMILIK = "", NO_HAKMILIK = "";
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				
				sql = "SELECT NG.KOD_NEGERI, DH.KOD_DAERAH, MK.KOD_MUKIM, JH.KOD_JENIS_HAKMILIK, M.NO_HAKMILIK " +
					"FROM TBLPPKHTA M, TBLRUJNEGERI NG, TBLRUJDAERAH DH, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JH " +
					"WHERE M.ID_NEGERI = NG.ID_NEGERI AND M.ID_DAERAH = DH.ID_DAERAH AND M.ID_MUKIM = MK.ID_MUKIM AND M.ID_JENISHM = JH.ID_JENISHAKMILIK " +
					"AND UPPER(M.ID_HTA) = '" + ID_HAKMILIK.toUpperCase() + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					KOD_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
					KOD_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
					KOD_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
					KOD_JENIS_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
					NO_HAKMILIK = rs.getString(5) == null ? "" : rs.getString(5);
				}
				if (NO_HAKMILIK.length() < 8) {
					// append 0's in front of no hakmilik
					String Z = "";
					for (int i = 0; i < (8 - NO_HAKMILIK.length()); i++) {
						Z = Z + "0";
					}
					NO_HAKMILIK = Z + NO_HAKMILIK;
				}
				returnValue = KOD_NEGERI + KOD_DAERAH + KOD_MUKIM + KOD_JENIS_HAKMILIK + NO_HAKMILIK;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}

	public String getNoFail(String ID_HAKMILIK) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK)) {
				String NO_FAIL = "";
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				
				sql = "SELECT FAIL.NO_FAIL " +
					"FROM TBLPPKHTA M, TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN PH, TBLPFDFAIL FAIL " +
					"WHERE M.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = PH.ID_PERMOHONAN AND PH.ID_FAIL = FAIL.ID_FAIL " +
					"AND M.ID_HTA = " + ID_HAKMILIK;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
				}
				returnValue = NO_FAIL;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	public String getNamaNegeriByKod(String KOD_NEGERI) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(KOD_NEGERI)) {
				String NAMA_NEGERI = "";
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				
				sql = "SELECT NAMA_NEGERI " +
					"FROM TBLRUJNEGERI " +
					"WHERE UPPER(KOD_NEGERI) = '" + KOD_NEGERI.toUpperCase() + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NAMA_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
				}
				returnValue = NAMA_NEGERI;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}	
	
	public String getNamaDaerahByKod(String KOD_NEGERI, String KOD_DAERAH) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(KOD_NEGERI) && !"".equalsIgnoreCase(KOD_DAERAH)) {
				String NAMA_DAERAH = "";
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				
				sql = "SELECT DE.NAMA_DAERAH " +
					"FROM TBLRUJDAERAH DE, TBLRUJNEGERI NG " +
					"WHERE NG.ID_NEGERI = DE.ID_NEGERI " +
					"AND NG.KOD_NEGERI = " + KOD_NEGERI + " AND UPPER(DE.KOD_DAERAH) = '" + KOD_DAERAH.toUpperCase() + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NAMA_DAERAH = rs.getString(1) == null ? "" : rs.getString(1);
				}
				returnValue = NAMA_DAERAH;
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}	
}