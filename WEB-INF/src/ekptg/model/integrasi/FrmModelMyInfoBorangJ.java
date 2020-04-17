package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmModelMyInfoBorangJ {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchBorangJ(Boolean REKOD_SELESAI, String USER_NAME) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String date30 = "", date14 = "", date05 = "";
			if (REKOD_SELESAI) {
				SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
			} else {
				SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) != 'SELESAI' ";
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
				SEARCH_REKOD += "AND PMHN.ID_NEGERIMHN = " + ID_NEGERI + " ";
			}

			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, MHN.NAMA_PEMOHON, SM.NAMA_SIMATI, M.TARIKH_KEMASKINI, M.STATUS_PROSES, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05 " +
				"FROM TBLINTJKSM M, TBLPPKPERMOHONAN PMHN, TBLPFDFAIL FAIL, TBLPPKPEMOHON MHN, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM " +
				"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON " +
				"AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI " + SEARCH_REKOD +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_PEMOHON = "", NAMA_SIMATI = "", TARIKH_HANTAR = "", STATUS = "";
			Hashtable h = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
				TARIKH_HANTAR = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				date30 = rs.getString(8) == null ? "" : rs.getString(8);
				date14 = rs.getString(9) == null ? "" : rs.getString(9);
				date05 = rs.getString(10) == null ? "" : rs.getString(10);
				
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
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("NamaSiMati", NAMA_SIMATI);
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
}