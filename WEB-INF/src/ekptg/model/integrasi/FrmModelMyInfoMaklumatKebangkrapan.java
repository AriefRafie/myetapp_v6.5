package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class FrmModelMyInfoMaklumatKebangkrapan {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int STATUS_BARU = 1;
	private int STATUS_DALAM_PROSES = 2;
	private int STATUS_SELESAI = 3;
	
	static Logger myLogger = Logger.getLogger(FrmModelMaklumatKebangkrapan.class);
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchMaklumatKebangkrapan(Boolean REKOD_SELESAI, String USER_NAME) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String date30 = "", date14 = "", date05 = "";
			if (REKOD_SELESAI) {
				SEARCH_REKOD = "AND M.STATUS_PROSES = '" + STATUS_SELESAI + "' ";
			} else {
				SEARCH_REKOD = "AND M.STATUS_PROSES != '" + STATUS_SELESAI + "' ";
			}
			// check ID NEGERI user
			String ID_NEGERI = "";
			if (!"".equalsIgnoreCase(USER_NAME)) {
				sql = "SELECT INT.ID_NEGERI FROM USERS U, USERS_INTERNAL INT WHERE U.USER_ID = INT.USER_ID AND UPPER(U.USER_NAME) = '" + USER_NAME.toUpperCase() + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
			if (!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)) {
				SEARCH_REKOD += "AND FA.ID_NEGERI = " + ID_NEGERI + " ";
			}

			sql = "SELECT PM.ID_PERMOHONAN, PB.ID_PIHAKBERKEPENTINGAN, FA.NO_FAIL, PM.NO_PERMOHONAN, PB.NAMA_PB, PB.NO_PB, M.TARIKH_KEMASKINI, M.STATUS_PROSES, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05 " +
				"FROM TBLINTJIM M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
				"WHERE M.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN " +
				"AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN " +
				SEARCH_REKOD + "ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI";
			int i = 1;
			String ID_PERMOHONAN = "", ID_PB = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_PEMOHON = "", NOKP_PEMOHON = "", TARIKH_HANTAR = "", STATUS = "";
			Hashtable h = null;
			myLogger.info("::::::::: searchMaklumatKebangkrapan :"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_PB = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NO_PERMOHONAN = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
				NOKP_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
				TARIKH_HANTAR = rs.getDate(7) == null ? "" : sdf.format(rs.getDate(7));
				STATUS = rs.getString(8) == null ? "" : rs.getString(8);
				date30 = rs.getString(9) == null ? "" : rs.getString(9);
				date14 = rs.getString(10) == null ? "" : rs.getString(10);
				date05 = rs.getString(11) == null ? "" : rs.getString(11);
				
				if ("1".equalsIgnoreCase(date30)) {
					date14 = "";
					date05 = "";
				} else if ("1".equalsIgnoreCase(date14)) {
					date30 = "";
					date05 = "";
				} else if ("1".equalsIgnoreCase(date05)) {
					date30 = "";
					date14 = "";
				} else {
					date30 = "";
					date14 = "";
					date05 = "";
				}
				
				h = new Hashtable();
				h.put("No", i);
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("ID_PB", ID_PB);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("NoKPPemohon", NOKP_PEMOHON);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
				h.put("date30", date30);
				h.put("date14", date14);
				h.put("date05", date05);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public String uploadFile(FileItem objItem) throws Exception {
		String returnValue = "";
		
		try {
			if (objItem != null) {
				String line = "";
				String split[], arr[] = objItem.getString().split("\r\n");
				
				String ID = "", NAMA_PB = "", NO_KP = "", STATUS_BANGKRAP = "", TARIKH_HUKUM = "", TARIKH_GULUNG = "", CAWANGAN = "", NO_ESTET = "", CATATAN = "";
				String sql = "";
				SQLRenderer r = new SQLRenderer();
				for (int i = 0; i <= arr.length; i++) {
					line = arr[i];
					split = line.split("\\|");
					if (split.length >= 3) {
						ID = split[0] == null ? "" : split[0];
						NAMA_PB = split[1] == null ? "" : split[1];
						NO_KP = split[2] == null ? "" : split[2];
						if (split.length >= 4) {
							STATUS_BANGKRAP = split[3] == null ? "" : split[3];
						}
						if (split.length >= 5) {
							TARIKH_HUKUM = split[4] == null ? "" : split[4];
						}
						if (split.length >= 6) {
							TARIKH_GULUNG = split[5] == null ? "" : split[5];
						}
						if (split.length >= 7) {
							CAWANGAN = split[6] == null ? "" : split[6];
						}
						if (split.length >= 8) {
							NO_ESTET = split[7] == null ? "" : split[7];
						}
						if (split.length >= 9) {
							CATATAN = split[8] == null ? "" : split[8];
						}
						
						// cleanup
						if (!"1".equalsIgnoreCase(STATUS_BANGKRAP.trim()) && !"0".equalsIgnoreCase(STATUS_BANGKRAP.trim())) {
							STATUS_BANGKRAP = "0";
						}
						
						r.clear();
						r.add("STATUS_PROSES", STATUS_SELESAI);
						r.add("STATUS_KEBANGKRAPAN", STATUS_BANGKRAP);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						if (!"".equalsIgnoreCase(TARIKH_HUKUM.trim())) {
							r.add("TARIKH_HUKUM", r.unquote("TO_DATE('" + TARIKH_HUKUM + "', 'dd/MM/yyyy')"));
						}
						if (!"".equalsIgnoreCase(TARIKH_GULUNG.trim())) {
							r.add("TARIKH_GULUNG", r.unquote("TO_DATE('" + TARIKH_GULUNG + "', 'dd/MM/yyyy')"));
						}
						r.add("CAWANGAN", CAWANGAN);
						r.add("NO_ESTET", NO_ESTET);
						r.add("CATATAN", CATATAN);
						r.update("ID_JIM", ID);
						sql = r.getSQLUpdate("TBLINTJIM");
						FrmModelUtilitiesIntegration.execSQL(sql);
					}
				}
			}
		} catch (Exception ex) {
			
		}
		return returnValue;
	}
}