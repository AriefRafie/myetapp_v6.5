package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmModelMyInfoSPTB {
	
	static Logger myLogger = Logger.getLogger(FrmModelMyInfoSPTB.class);	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
	@SuppressWarnings("unchecked")
	public Vector searchSPTB(Boolean REKOD_SELESAI, String USER_ID) throws Exception {
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
			if (!"".equalsIgnoreCase(USER_ID)) {
				sql = "SELECT INT.ID_NEGERI FROM USERS U, USERS_INTERNAL INT WHERE U.USER_ID = INT.USER_ID AND UPPER(U.USER_ID) = '" + USER_ID + "'";
				myLogger.info("SQL INT :"+sql);
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
			if (!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)) {
				SEARCH_REKOD += "AND FA.ID_NEGERI = " + ID_NEGERI + " ";
			}

			sql = " SELECT M.ID_FAIL,M.ID_HAKMILIK,M.ID_SEKSYEN,M.NO_FAIL, M.NO_PERMOHONAN, M.NO_HAKMILIK,M.KOD_JENIS_HAKMILIK, M.NO_LOT,M.NAMA_DAERAH,M.NAMA_MUKIM, M.TARIKH_KEMASKINI, M.STATUS_PROSES, "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 30) THEN '1' ELSE '2' END AS MORE_30,  "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 14) THEN '1' ELSE '2' END AS MORE_14,  "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 05) THEN '1' ELSE '2' END AS MORE_05  "+
			" FROM TBLINTSPTB M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM,TBLRUJSUBURUSAN SU  "+
			" WHERE M.ID_FAIL = FA.ID_FAIL "+
			" AND M.ID_HAKMILIK = HM.ID_HAKMILIK "+
			" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN  "+
			" AND PM.ID_FAIL = FA.ID_FAIL  "+
			" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
			SEARCH_REKOD +
			" ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI ";
			
			myLogger.info("SQL MY INFO SPBT::"+sql.toUpperCase());
			
			int i = 1;
			//String ID_PEMOHON = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_PEMOHON = "", NOKP_PEMOHON = "", TARIKH_HANTAR = "", STATUS = "";
			
			String ID_FAIL = "";
			String ID_HAKMILIK = "";
			String ID_SEKSYEN = "";
			String NO_FAIL = "";
			String NO_PERMOHONAN = "";
			String KOD_JENIS_HAKMILIK = "";
			String NO_HAKMILIK = "";
			String NO_LOT = "";
			String NAMA_DAERAH = "";
			String NAMA_MUKIM = "";
			String STATUS = "";
			
			Hashtable h = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
				ID_HAKMILIK = rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK");
				NO_PERMOHONAN = rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN");
				ID_SEKSYEN = rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN");
				NO_FAIL = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
				
				KOD_JENIS_HAKMILIK = rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
				NO_HAKMILIK = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				NO_LOT = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
				
				NAMA_DAERAH = rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH");
				NAMA_MUKIM = rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM");
				
				STATUS = rs.getString("STATUS_PROSES") == null ? "" : rs.getString("STATUS_PROSES");
				date30 = rs.getString("MORE_30") == null ? "" : rs.getString("MORE_30");
				date14 = rs.getString("MORE_14") == null ? "" : rs.getString("MORE_14");
				date05 = rs.getString("MORE_05") == null ? "" : rs.getString("MORE_05");
				
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
				h.put("ID_FAIL", ID_FAIL);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("ID_SEKSYEN", ID_SEKSYEN);
				h.put("NO_FAIL", NO_FAIL);
				h.put("NO_PERMOHONAN", NO_PERMOHONAN);
				h.put("KOD_JENIS_HAKMILIK", KOD_JENIS_HAKMILIK);
				h.put("NO_HAKMILIK", NO_HAKMILIK);
				h.put("NO_LOT", NO_LOT);
				h.put("NAMA_DAERAH", NAMA_DAERAH);
				h.put("NAMA_MUKIM", NAMA_MUKIM);
				
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
	
	
	public Vector searchSPTBPPK(Boolean REKOD_SELESAI, String USER_ID) throws Exception {
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
			if (!"".equalsIgnoreCase(USER_ID)) {
				sql = "SELECT INT.ID_NEGERI FROM USERS U, USERS_INTERNAL INT WHERE U.USER_ID = INT.USER_ID AND UPPER(U.USER_ID) = '" + USER_ID + "'";
				myLogger.info("SQL INT :"+sql);
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
			if (!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)) {
				SEARCH_REKOD += "AND FA.ID_NEGERI = " + ID_NEGERI + " ";
			}

		/*	sql = " SELECT M.ID_FAIL,M.ID_HAKMILIK,M.ID_SEKSYEN,M.NO_FAIL, M.NO_PERMOHONAN, M.NO_HAKMILIK,M.KOD_JENIS_HAKMILIK, M.NO_LOT,M.NAMA_DAERAH,M.NAMA_MUKIM, M.TARIKH_KEMASKINI, M.STATUS_PROSES, "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 30) THEN '1' ELSE '2' END AS MORE_30,  "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 14) THEN '1' ELSE '2' END AS MORE_14,  "+
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 05) THEN '1' ELSE '2' END AS MORE_05  "+
			" FROM TBLINTSPTB M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM,TBLRUJSUBURUSAN SU  "+
			" WHERE M.ID_FAIL = FA.ID_FAIL "+
			" AND M.ID_HAKMILIK = HM.ID_HAKMILIK "+
			" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN  "+
			" AND PM.ID_FAIL = FA.ID_FAIL  "+
			" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
			SEARCH_REKOD +
			" ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI "; */
			
			sql = " SELECT M.ID_FAIL,M.ID_HAKMILIK,M.ID_SEKSYEN,M.NO_FAIL, M.NO_PERMOHONAN, M.NO_HAKMILIK, "+
			" M.KOD_JENIS_HAKMILIK, M.NO_LOT,M.NAMA_DAERAH,M.NAMA_MUKIM, M.TARIKH_KEMASKINI, "+
			" M.STATUS_PROSES,  CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 30) THEN '1' ELSE '2' END AS MORE_30,"+   
			" CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 14) "+
			" THEN '1' ELSE '2' END AS MORE_14,   CASE WHEN (TO_DATE(SYSDATE, 'DD/MM/YYYY') - TO_DATE(M.TARIKH_KEMASKINI, 'DD/MM/YYYY') > 05) THEN '1' ELSE '2'"+ 
			" END AS MORE_05  "+
			" FROM TBLINTSPTB M, TBLPPKPERMOHONAN PM, TBLPFDFAIL FA, TBLPPKHTA HM,TBLRUJSUBURUSAN SU,TBLPPKSIMATI SM,TBLPPKPERMOHONANSIMATI PSM"+   
			" WHERE M.ID_FAIL = FA.ID_FAIL  "+
			" AND M.ID_HAKMILIK = HM.ID_HTA"+
			" AND HM.ID_SIMATI = PSM.ID_SIMATI"+
			" AND PSM.ID_PERMOHONAN = PM.ID_PERMOHONAN"+  
			" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN   "+
			" AND PM.ID_FAIL = FA.ID_FAIL "+
			" AND SM.ID_SIMATI = PSM.ID_SIMATI "+  
			SEARCH_REKOD +
			" ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI"; 
			
			myLogger.info("SQL MY INFO SPBT::"+sql.toUpperCase());
			
			int i = 1;
			//String ID_PEMOHON = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_PEMOHON = "", NOKP_PEMOHON = "", TARIKH_HANTAR = "", STATUS = "";
			
			String ID_FAIL = "";
			String ID_HAKMILIK = "";
			String ID_SEKSYEN = "";
			String NO_FAIL = "";
			String NO_PERMOHONAN = "";
			String KOD_JENIS_HAKMILIK = "";
			String NO_HAKMILIK = "";
			String NO_LOT = "";
			String NAMA_DAERAH = "";
			String NAMA_MUKIM = "";
			String STATUS = "";
			
			Hashtable h = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
				ID_HAKMILIK = rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK");
				NO_PERMOHONAN = rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN");
				ID_SEKSYEN = rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN");
				NO_FAIL = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
				
				KOD_JENIS_HAKMILIK = rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
				NO_HAKMILIK = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				NO_LOT = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
				
				NAMA_DAERAH = rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH");
				NAMA_MUKIM = rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM");
				
				STATUS = rs.getString("STATUS_PROSES") == null ? "" : rs.getString("STATUS_PROSES");
				date30 = rs.getString("MORE_30") == null ? "" : rs.getString("MORE_30");
				date14 = rs.getString("MORE_14") == null ? "" : rs.getString("MORE_14");
				date05 = rs.getString("MORE_05") == null ? "" : rs.getString("MORE_05");
				
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
				h.put("ID_FAIL", ID_FAIL);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("ID_SEKSYEN", ID_SEKSYEN);
				h.put("NO_FAIL", NO_FAIL);
				h.put("NO_PERMOHONAN", NO_PERMOHONAN);
				h.put("KOD_JENIS_HAKMILIK", KOD_JENIS_HAKMILIK);
				h.put("NO_HAKMILIK", NO_HAKMILIK);
				h.put("NO_LOT", NO_LOT);
				h.put("NAMA_DAERAH", NAMA_DAERAH);
				h.put("NAMA_MUKIM", NAMA_MUKIM);
				
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