package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.view.ppt.FrmUlasanJPBDOnline;
import lebah.db.Db;

public class FrmModelMyInfoUlasanJPBD {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger myLogger = Logger.getLogger(FrmModelMyInfoUlasanJPBD.class);
	
	@SuppressWarnings("unchecked")
	public Vector searchBorangLampiranA1(Boolean REKOD_SELESAI, String userId) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			if (REKOD_SELESAI) {
				SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
			} else {
				SEARCH_REKOD = "AND (UPPER(M.STATUS_PROSES) != 'SELESAI' AND UPPER(M.STATUS_PROSES) != 'MOHON PENGESAHAN') ";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, USERS U, USERS_KEMENTERIAN UK " +
				"WHERE U.USER_ID = UK.USER_ID "
				+ "AND U.USER_ID = '"+userId+"'"
				+ "AND FAIL.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "
				+ "AND M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN "
				+ "AND PMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD +
				"GROUP BY M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI DESC";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", STATUS = "", TARIKH_HANTAR = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			myLogger.info("searchBorangLampiranA1--------"+sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				STATUS = rs.getString(4) == null ? "" : rs.getString(4);
				TARIKH_HANTAR = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
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
	public Vector searchBorangLampiran(Boolean REKOD_SELESAI, String userId, String txtNoFail) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			if (REKOD_SELESAI) {
				SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
			} else {
				SEARCH_REKOD = "AND (UPPER(M.STATUS_PROSES) != 'SELESAI' AND UPPER(M.STATUS_PROSES) != 'MOHON PENGESAHAN') ";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, USERS U, USERS_KEMENTERIAN UK " +
				"WHERE U.USER_ID = UK.USER_ID "
				+ "AND U.USER_ID = '"+userId+"'"
				+ "AND FAIL.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "
				+ "AND M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN "
				+ "AND PMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD +
				"GROUP BY M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI DESC";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", STATUS = "", TARIKH_HANTAR = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String nofail = "";
			
			
			// NO FAIL
			if (txtNoFail != "" && txtNoFail != null) {
				if (!nofail.equals("")) {
					// sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
					sql += " AND (UPPER(FAIL.NO_FAIL) LIKE '%" + nofail.toUpperCase() + "%' ";
							/*+ " OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' "
							+ " OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' "
							+ " OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";*/
				}
			} // close carian by nofail

			// SOCKEMENTERIAN
			/*if (socKementerian != null) {
				if (!socKementerian.trim().equals("") && !socKementerian.trim().equals("0")) {
					sql = sql + " AND F.ID_KEMENTERIAN = " + socKementerian + "  ";
				}
			}*/
			
			
			rs = stmt.executeQuery(sql);
			myLogger.info("searchBorangLampiranA1--------"+sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				STATUS = rs.getString(4) == null ? "" : rs.getString(4);
				TARIKH_HANTAR = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
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
	public Vector searchBorangLampiranA1TungguPengesahan() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			SEARCH_REKOD = "AND (UPPER(M.STATUS_PROSES) = 'MOHON PENGESAHAN') ";
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD +
				"GROUP BY M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI DESC";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", STATUS = "", TARIKH_HANTAR = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				STATUS = rs.getString(4) == null ? "" : rs.getString(4);
				TARIKH_HANTAR = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
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
	public Vector viewFullPermohonan(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, MUKIM.NAMA_MUKIM, HM.NO_LOT, HM.NO_SYIT, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPPTHAKMILIK HM, TBLPFDFAIL FAIL, TBLRUJMUKIM MUKIM " +
				"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_PERMOHONAN = HM.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL AND HM.ID_MUKIM = MUKIM.ID_MUKIM(+) " + SEARCH_REKOD +
				"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI DESC";
			int i = 1;
			String NO_FAIL = "", NO_PERMOHONAN = "", NAMA_MUKIM = "", NO_LOT = "", NO_SYIT = "", STATUS = "", TARIKH_HANTAR = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_MUKIM = rs.getString(4) == null ? "" : rs.getString(4);
				NO_LOT = rs.getString(5) == null ? "" : rs.getString(5);
				NO_SYIT = rs.getString(6) == null ? "" : rs.getString(6);
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				TARIKH_HANTAR = rs.getDate(8) == null ? "" : sdf.format(rs.getDate(8));
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaMukim", NAMA_MUKIM);
				h.put("NoLot", NO_LOT);
				h.put("NoSyit", NO_SYIT);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
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