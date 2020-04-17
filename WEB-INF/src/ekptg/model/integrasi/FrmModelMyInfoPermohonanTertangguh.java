package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmModelMyInfoPermohonanTertangguh {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchBorangJ() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) != 'SELESAI' ";
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PERMOHONAN.NO_PERMOHONAN, PEMOHON.NAMA_PEMOHON, SM.NAMA_SIMATI, M.TARIKH_KEMASKINI, M.STATUS_PROSES " +
				"FROM TBLINTJKSM M, TBLPPKPERMOHONAN PERMOHONAN, TBLPPKSIMATI SM, TBLPFDFAIL FAIL, TBLPPKPEMOHON PEMOHON " +
				"WHERE M.ID_PERMOHONAN = PERMOHONAN.ID_PERMOHONAN AND M.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_PEMOHON = PEMOHON.ID_PEMOHON " + SEARCH_REKOD +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_PEMOHON = "", NAMA_SIMATI = "", TARIKH_HANTAR = "", STATUS = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_SIMATI = rs.getString(5) == null ? "" : rs.getString(5);
				TARIKH_HANTAR = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("NamaSiMati", NAMA_SIMATI);
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
	public Vector searchNilaianHTA(int JENIS_HTA, String USER_NAME) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String CHILD_TABLE = "", CHILD_WHERE = "";
			CHILD_WHERE = "AND C.ID_JPPH = M.ID_JPPH AND UPPER(C.STATUS_PROSES) != 'SELESAI' ";
			if (JENIS_HTA == 0) {
				CHILD_TABLE = "TBLINTJPPHAH C, ";
			} else {
				CHILD_TABLE = "TBLINTJPPHTH C, ";
			}
			String sql = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
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
				CHILD_WHERE = "AND C.ID_NEGERI = " + ID_NEGERI + " " + CHILD_WHERE;
			}
			
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, PEMOHON.NAMA_PEMOHON, C.STATUS_PROSES, C.TARIKH_KEMASKINI " +
				"FROM TBLINTJPPH M, TBLPPKSIMATI SM, " + CHILD_TABLE + "TBLPFDFAIL FAIL, TBLPPKPEMOHON PEMOHON " +
				"WHERE M.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_PEMOHON = PEMOHON.ID_PEMOHON " + CHILD_WHERE +
				"ORDER BY M.TARIKH_KEMASKINI, M.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_SIMATI = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "", NAMA_PEMOHON = "", STATUS = "", TARIKH_HANTAR = "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_BARU = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_LAMA = rs.getString(6) == null ? "" : rs.getString(6);
				NAMA_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
				STATUS = rs.getString(8) == null ? "" : rs.getString(8);
				TARIKH_HANTAR = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("NamaPemohon", NAMA_PEMOHON);
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
	public Vector searchNilaianHAK(String USER_NAME) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_WHERE = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
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
				SQL_WHERE = "AND C.ID_NEGERI = " + ID_NEGERI + " ";
			}
			
			SQL_WHERE += "AND UPPER(HAK.STATUS_PROSES) != 'SELESAI' ";
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, PEMOHON.NAMA_PEMOHON, HAK.STATUS_PROSES, HAK.TARIKH_KEMASKINI " +
				"FROM TBLINTJPPH M, TBLPPKSIMATI SM, TBLINTJPPHHAK HAK, TBLPFDFAIL FAIL, TBLPPKPEMOHON PEMOHON " +
				"WHERE M.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_PEMOHON = PEMOHON.ID_PEMOHON AND M.ID_JPPH = HAK.ID_JPPH " + SQL_WHERE +
				"ORDER BY M.TARIKH_KEMASKINI, M.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_SIMATI = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "", NAMA_PEMOHON = "", STATUS = "", TARIKH_HANTAR = "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_BARU = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_LAMA = rs.getString(6) == null ? "" : rs.getString(6);
				NAMA_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
				STATUS = rs.getString(8) == null ? "" : rs.getString(8);
				TARIKH_HANTAR = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("NamaPemohon", NAMA_PEMOHON);
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
	public Vector searchBorangLampiranA1() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SEARCH_REKOD = "";
			SEARCH_REKOD = "AND UPPER(M.STATUS_PROSES) != 'SELESAI' ";
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, PERMOHONAN.NO_PERMOHONAN, HM.NO_LOT, HM.NO_SYIT, MUKIM.NAMA_MUKIM, M.STATUS_PROSES, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PERMOHONAN, TBLPPTHAKMILIK HM, TBLPFDFAIL FAIL, TBLRUJMUKIM MUKIM " +
				"WHERE M.ID_PERMOHONAN = PERMOHONAN.ID_PERMOHONAN AND M.ID_PERMOHONAN = HM.ID_PERMOHONAN AND PERMOHONAN.ID_FAIL = FAIL.ID_FAIL AND PERMOHONAN.ID_MUKIM = MUKIM.ID_MUKIM " + SEARCH_REKOD +
				"ORDER BY M.STATUS_PROSES, M.TARIKH_KEMASKINI";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NO_LOT = "", NO_SYIT = "", NAMA_MUKIM = "", STATUS = "", TARIKH_HANTAR = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NO_LOT = rs.getString(4) == null ? "" : rs.getString(4);
				NO_SYIT = rs.getString(5) == null ? "" : rs.getString(5);
				NAMA_MUKIM = rs.getString(6) == null ? "" : rs.getString(6);
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				TARIKH_HANTAR = rs.getDate(8) == null ? "" : sdf.format(rs.getDate(8));
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NoLot", NO_LOT);
				h.put("NoSyitUkur", NO_SYIT);
				h.put("Mukim", NAMA_MUKIM);
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