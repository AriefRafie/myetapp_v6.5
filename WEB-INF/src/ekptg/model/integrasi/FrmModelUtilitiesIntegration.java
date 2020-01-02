package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;

public class FrmModelUtilitiesIntegration {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String getCurDate(String DATE_FORMAT) {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT);
		return s.format(date);
	}
	
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHmsS");
		return s.format(date);
	}
	
	public static Boolean execSQL(String SQL) throws Exception {
		Boolean b = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SQL)) {
				Statement stmt = db.getStatement();
				stmt.execute(SQL);
				b = true;
			}
		} finally {
			if (db != null) db.close();
		}
		return b;
	}
	
	public static Boolean isNumeric(String TO_CHECK) throws Exception {
		return TO_CHECK.matches("-?\\d+(.\\d+)?");
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getEmailContent(String KOD_AGENSI) throws Exception, DbException {
		Vector returnValue = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(KOD_AGENSI)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				
				sql = "SELECT ALAMAT_DARI, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2, TAJUK, KANDUNGAN, STATUS FROM TBLINTEMAILMSG WHERE STATUS = 1 AND UPPER(KOD_AGENSI) = '" + KOD_AGENSI + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String ALAMAT_DARI = "", ADA_CC = "", ALAMAT_CC_1 = "", ALAMAT_CC_2 = "", TAJUK = "", KANDUNGAN = "";
					ALAMAT_DARI = rs.getString("ALAMAT_DARI") == null ? "" : rs.getString("ALAMAT_DARI");
					ADA_CC = rs.getString("ADA_CC") == null ? "" : rs.getString("ADA_CC");
					ALAMAT_CC_1 = rs.getString("ALAMAT_CC_1") == null ? "" : rs.getString("ALAMAT_CC_1");
					ALAMAT_CC_2 = rs.getString("ALAMAT_CC_2") == null ? "" : rs.getString("ALAMAT_CC_2");
					TAJUK = rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK");
					KANDUNGAN = rs.getString("KANDUNGAN") == null ? "" : rs.getString("KANDUNGAN");
					
					Hashtable h = new Hashtable();
					h.put("ALAMAT_DARI", ALAMAT_DARI);
					h.put("ADA_CC", ADA_CC);
					h.put("ALAMAT_CC_1", ALAMAT_CC_1);
					h.put("ALAMAT_CC_2", ALAMAT_CC_2);
					h.put("TAJUK", TAJUK);
					h.put("KANDUNGAN", KANDUNGAN);
					returnValue.add(h);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public static Vector getWSDetails() throws Exception {
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
				"WHERE STATUS = 1";
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
	private static String getIDHM(Vector v) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable hDetail = (Hashtable) v.get(0);
				if (!hDetail.isEmpty()) {
					String ID_HAKMILIK = (String) hDetail.get("ID_HAKMILIK");
					String isPPT = (String) hDetail.get("isPPT");
					if (!"".equalsIgnoreCase(ID_HAKMILIK)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						String KOD_NEGERI = "", KOD_DAERAH = "", KOD_MUKIM = "", KOD_JENIS_HAKMILIK = "", NO_HAKMILIK = "";
						if ("1".equalsIgnoreCase(isPPT)) {
							sql = "SELECT NG.KOD_NEGERI, DH.KOD_DAERAH, MK.KOD_MUKIM, JH.KOD_JENIS_HAKMILIK, M.NO_HAKMILIK " +
							"FROM TBLPPTHAKMILIK M, TBLRUJNEGERI NG, TBLRUJDAERAH DH, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JH " +
							"WHERE M.ID_NEGERI = NG.ID_NEGERI AND M.ID_DAERAH = DH.ID_DAERAH AND M.ID_MUKIM = MK.ID_MUKIM AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK " +
							"AND UPPER(M.ID_HAKMILIK) = '" + ID_HAKMILIK.toUpperCase() + "'";
						} else {
							sql = "SELECT NG.KOD_NEGERI, DH.KOD_DAERAH, MK.KOD_MUKIM, JH.KOD_JENIS_HAKMILIK, M.NO_HAKMILIK " +
							"FROM TBLPPKHTA M, TBLRUJNEGERI NG, TBLRUJDAERAH DH, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JH " +
							"WHERE M.ID_NEGERI = NG.ID_NEGERI AND M.ID_DAERAH = DH.ID_DAERAH AND M.ID_MUKIM = MK.ID_MUKIM AND M.ID_JENISHM = JH.ID_JENISHAKMILIK " +
							"AND UPPER(M.ID_HTA) = '" + ID_HAKMILIK.toUpperCase() + "'";
						}						
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							KOD_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
							KOD_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
							KOD_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
							KOD_JENIS_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
							NO_HAKMILIK = rs.getString(5) == null ? "" : rs.getString(5);
						}
						if (!"".equalsIgnoreCase(KOD_NEGERI) && !"".equalsIgnoreCase(KOD_DAERAH) && !"".equalsIgnoreCase(KOD_MUKIM) && !"".equalsIgnoreCase(KOD_JENIS_HAKMILIK)) {
							if (NO_HAKMILIK.length() < 8) {
								// append 0's in front of no hakmilik
								String Z = "";
								for (int i = 0; i < (8 - NO_HAKMILIK.length()); i++) {
									Z = Z + "0";
								}
								NO_HAKMILIK = Z + NO_HAKMILIK;
							}
						}
						returnValue = KOD_NEGERI + KOD_DAERAH + KOD_MUKIM + KOD_JENIS_HAKMILIK + NO_HAKMILIK;
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}		
		return returnValue;
	}
}