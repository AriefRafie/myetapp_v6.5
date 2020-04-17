package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmModelMyInfoNilaianHartaTakAlih {
	
	static Logger myLog = Logger.getLogger(ekptg.model.integrasi.FrmModelMyInfoNilaianHartaTakAlih.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Vector searchNilaianHartaTerdahulu(Boolean isJPPHUser, String USER_ID, String NO_FAIL, String NO_PERMOHONAN, String TARIKH_HANTAR_DARI, String TARIKH_HANTAR_KE, String TARIKH_SELESAI_DARI, String TARIKH_SELESAI_KE, String STATUS_FAIL) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String SEARCH_REKOD = "";
			String sql = "";
			Hashtable<String, Comparable> h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			int i = 1;
			
			String ID_NEGERI = "", ID_DAERAH = "", TEMP = "", TEMP_HA = "";
			if (isJPPHUser) {
				sql = "SELECT I.ID_NEGERI, I.ID_DAERAH " +
				"FROM USERS U, USERS_INTEGRASI I " +
				"WHERE U.USER_ID = I.USER_ID " +
				"AND U.USER_ID = " + USER_ID;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
					TEMP = "AND (M.ID_NEGERI = " + ID_NEGERI + " AND M.ID_DAERAH = " + ID_DAERAH + ") ";
					TEMP_HA = "AND (M.ID_NEGERILOKASI = " + ID_NEGERI + " AND M.ID_BANDARLOKASI = " + ID_DAERAH + ") ";
				}				
			} else {
				sql = "SELECT I.ID_NEGERI " +
				"FROM USERS U, USERS_INTERNAL I " +
				"WHERE U.USER_ID = I.USER_ID " +
				"AND U.USER_ID = " + USER_ID;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
					TEMP = "AND M.ID_NEGERI = " + ID_NEGERI + " ";
					TEMP_HA = "AND M.ID_NEGERILOKASI = " + ID_NEGERI + " ";
				}				
			}
			
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SEARCH_REKOD += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SEARCH_REKOD += "AND UPPER(PRMHN.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TARIKH_HANTAR_DARI) && !"".equalsIgnoreCase(TARIKH_HANTAR_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_SIMPAN) >= TO_DATE('" + TARIKH_HANTAR_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_SIMPAN) <= TO_DATE('" + TARIKH_HANTAR_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(TARIKH_SELESAI_DARI) && !"".equalsIgnoreCase(TARIKH_SELESAI_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_KEMASKINI) >= TO_DATE('" + TARIKH_SELESAI_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_KEMASKINI) <= TO_DATE('" + TARIKH_SELESAI_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(STATUS_FAIL)) {
				if ("1".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'BARU' ";
				} else if ("2".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DALAM PROSES JPPH' ";
				} else if ("3".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
				} else if ("4".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DIKEMBALIKAN' ";
				}
			}
			
			String CARI_NOFAIL = "", CARI_STATUS = "", CARI_NAMAPEMOHON = "", CARI_TARIKHHANTAR = "", CARI_TARIKHSELESAI = "", CARI_JENISHARTA = "";
			String CARI_STATUSFAIL = "", CARI_IDHARTA = "";
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES, M.ID_HTA " +
				"FROM TBLINTJPPHAH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + TEMP + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HTA (Ada Hakmilik)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				CARI_IDHARTA = rs.getString(7) == null ? "" : rs.getString(7);
				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("NoFail", CARI_NOFAIL);
				h.put("Status", CARI_STATUS);
				h.put("NamaPemohon", CARI_NAMAPEMOHON);
				h.put("JenisPermohonan", CARI_JENISHARTA);
				h.put("TarikhHantar", CARI_TARIKHHANTAR);
				h.put("TarikhSelesai", CARI_TARIKHSELESAI);
				h.put("StatusFail", CARI_STATUSFAIL);
				h.put("IDHarta", CARI_IDHARTA);
				v.add(h);
				i++;
			}
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES, M.ID_HTA " +
				"FROM TBLINTJPPHTH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + TEMP + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HTA (Tiada Hakmilik)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				CARI_IDHARTA = rs.getString(7) == null ? "" : rs.getString(7);
				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("NoFail", CARI_NOFAIL);
				h.put("Status", CARI_STATUS);
				h.put("NamaPemohon", CARI_NAMAPEMOHON);
				h.put("JenisPermohonan", CARI_JENISHARTA);
				h.put("TarikhHantar", CARI_TARIKHHANTAR);
				h.put("TarikhSelesai", CARI_TARIKHSELESAI);
				h.put("StatusFail", CARI_STATUSFAIL);
				h.put("IDHarta", CARI_IDHARTA);
				v.add(h);
				i++;
			}

			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES, M.ID_HA " +
			"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FAIL, TBLPPKPEMOHON PMHN " +
			"WHERE M.JENIS_HA = 1 AND M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FAIL.ID_FAIL AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON " + TEMP_HA + 
			SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HA (Kenderaan)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				CARI_IDHARTA = rs.getString(7) == null ? "" : rs.getString(7);
				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("NoFail", CARI_NOFAIL);
				h.put("Status", CARI_STATUS);
				h.put("NamaPemohon", CARI_NAMAPEMOHON);
				h.put("JenisPermohonan", CARI_JENISHARTA);
				h.put("TarikhHantar", CARI_TARIKHHANTAR);
				h.put("TarikhSelesai", CARI_TARIKHSELESAI);
				h.put("StatusFail", CARI_STATUSFAIL);
				h.put("IDHarta", CARI_IDHARTA);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	public static Boolean populateNilaianHartaTerdahulu(String USER_ID, String NO_FAIL, String NO_PERMOHONAN, String TARIKH_HANTAR_DARI, String TARIKH_HANTAR_KE, String TARIKH_SELESAI_DARI, String TARIKH_SELESAI_KE, String STATUS_FAIL) throws Exception {
		Db db = new Db();
		Db dbInsert = new Db();
		
		try {
			String SEARCH_REKOD = "";
			String sql = "";
			Statement stmt = db.getStatement();
			Statement stmtInsert = dbInsert.getStatement();
			ResultSet rs = null;
			
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SEARCH_REKOD += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SEARCH_REKOD += "AND UPPER(PRMHN.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TARIKH_HANTAR_DARI) && !"".equalsIgnoreCase(TARIKH_HANTAR_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_SIMPAN) >= TO_DATE('" + TARIKH_HANTAR_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_SIMPAN) <= TO_DATE('" + TARIKH_HANTAR_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(TARIKH_SELESAI_DARI) && !"".equalsIgnoreCase(TARIKH_SELESAI_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_KEMASKINI) >= TO_DATE('" + TARIKH_SELESAI_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_KEMASKINI) <= TO_DATE('" + TARIKH_SELESAI_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(STATUS_FAIL)) {
				if ("1".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'BARU' ";
				} else if ("2".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DALAM PROSES JPPH' ";
				} else if ("3".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
				} else if ("4".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DIKEMBALIKAN' ";
				}
			}
			
			String CARI_NOFAIL = "", CARI_STATUS = "", CARI_NAMAPEMOHON = "", CARI_TARIKHHANTAR = "", CARI_TARIKHSELESAI = "";
			
			sql = "DELETE FROM TBLINTRPTTEMP WHERE USER_ID = " + USER_ID + " AND ID_JENISRPT IN (1, 2, 3)";
			stmt.executeQuery(sql);
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPPHAH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				
				sql = "INSERT INTO TBLINTRPTTEMP (USER_ID, ID_JENISRPT, COLUMN1, COLUMN2, COLUMN3, COLUMN4, COLUMN5) VALUES ('" +
				USER_ID + "', '1', '" + CARI_NOFAIL + "', '" + CARI_NAMAPEMOHON + "', '" + CARI_STATUS + "', '" + CARI_TARIKHHANTAR + "', '" +  
					CARI_TARIKHSELESAI + "')";
				stmtInsert.executeQuery(sql);
			}
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI " +
				"FROM TBLINTJPPHTH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));

				sql = "INSERT INTO TBLINTRPTTEMP (USER_ID, ID_JENISRPT, COLUMN1, COLUMN2, COLUMN3, COLUMN4, COLUMN5) VALUES ('" +
					USER_ID + "', '2', '" + CARI_NOFAIL + "', '" + CARI_NAMAPEMOHON + "', '" + CARI_STATUS + "', '" + CARI_TARIKHHANTAR + "', '" +  
					CARI_TARIKHSELESAI + "')";
				stmtInsert.executeQuery(sql);
			}

			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI " +
			"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FAIL, TBLPPKPEMOHON PMHN " +
			"WHERE M.JENIS_HA = 1 AND M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FAIL.ID_FAIL AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON " + 
			SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				
				sql = "INSERT INTO TBLINTRPTTEMP (USER_ID, ID_JENISRPT, COLUMN1, COLUMN2, COLUMN3, COLUMN4, COLUMN5) VALUES ('" +
				USER_ID + "', '3', '" + CARI_NOFAIL + "', '" + CARI_NAMAPEMOHON + "', '" + CARI_STATUS + "', '" + CARI_TARIKHHANTAR + "', '" +  
					CARI_TARIKHSELESAI + "')";
				stmtInsert.executeQuery(sql);
			}
		} catch (Exception ex) {
			
		} finally {
			if (db != null) { db.close(); }
			if (dbInsert != null) { dbInsert.close(); }
		}
		return true;
	}
	
	public static String printTextNilaianHartaTerdahulu(String NO_FAIL, String NO_PERMOHONAN, String TARIKH_HANTAR_DARI, String TARIKH_HANTAR_KE, String TARIKH_SELESAI_DARI, String TARIKH_SELESAI_KE, String STATUS_FAIL) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String SEARCH_REKOD = "";
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			int i = 1;
			
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SEARCH_REKOD += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SEARCH_REKOD += "AND UPPER(PRMHN.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(TARIKH_HANTAR_DARI) && !"".equalsIgnoreCase(TARIKH_HANTAR_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_SIMPAN) >= TO_DATE('" + TARIKH_HANTAR_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_SIMPAN) <= TO_DATE('" + TARIKH_HANTAR_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(TARIKH_SELESAI_DARI) && !"".equalsIgnoreCase(TARIKH_SELESAI_KE)) {
				SEARCH_REKOD += "AND (UPPER(M.TARIKH_KEMASKINI) >= TO_DATE('" + TARIKH_SELESAI_DARI.toUpperCase() + "', 'dd/MM/yyyy') AND UPPER(M.TARIKH_KEMASKINI) <= TO_DATE('" + TARIKH_SELESAI_KE.toUpperCase() + "', 'dd/MM/yyyy')) ";
			}
			if (!"".equalsIgnoreCase(STATUS_FAIL)) {
				if ("1".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'BARU' ";
				} else if ("2".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DALAM PROSES JPPH' ";
				} else if ("3".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'SELESAI' ";
				} else if ("4".equalsIgnoreCase(STATUS_FAIL.trim())) {
					SEARCH_REKOD += "AND UPPER(M.STATUS_PROSES) = 'DIKEMBALIKAN' ";
				}
			}
			
			String CARI_NOFAIL = "", CARI_STATUS = "", CARI_NAMAPEMOHON = "", CARI_TARIKHHANTAR = "", CARI_TARIKHSELESAI = "", CARI_JENISHARTA = "", CARI_STATUSFAIL = "";
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES " +
				"FROM TBLINTJPPHAH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HTA (Ada Hakmilik)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				returnValue += i + "\t" + CARI_NOFAIL + "\t" + CARI_STATUS + "\t" + CARI_NAMAPEMOHON + "\t" + CARI_JENISHARTA + "\t" + CARI_TARIKHHANTAR 
					+ "\t" + CARI_TARIKHSELESAI + "\t" + CARI_STATUSFAIL + "\r\n";
				i++;
			}
			
			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES " +
				"FROM TBLINTJPPHTH M, TBLPPKPERMOHONAN PRMHN, TBLPPKPEMOHON PMHN, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON AND PRMHN.ID_FAIL = FAIL.ID_FAIL " + SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HTA (Tiada Hakmilik)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				returnValue += i + "\t" + CARI_NOFAIL + "\t" + CARI_STATUS + "\t" + CARI_NAMAPEMOHON + "\t" + CARI_JENISHARTA + "\t" + CARI_TARIKHHANTAR 
					+ "\t" + CARI_TARIKHSELESAI + "\t" + CARI_STATUSFAIL + "\r\n";
				i++;
			}

			sql = "SELECT FAIL.NO_FAIL, UPPER(M.STATUS_PROSES), PMHN.NAMA_PEMOHON, M.TARIKH_SIMPAN, M.TARIKH_KEMASKINI, M.STATUS_PROSES " +
			"FROM TBLINTJPPHHA M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FAIL, TBLPPKPEMOHON PMHN " +
			"WHERE M.JENIS_HA = 1 AND M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FAIL.ID_FAIL AND PRMHN.ID_PEMOHON = PMHN.ID_PEMOHON " + 
			SEARCH_REKOD;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CARI_JENISHARTA = "HA (Kenderaan)";
				CARI_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
				CARI_STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				CARI_NAMAPEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
				CARI_TARIKHHANTAR = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				CARI_TARIKHSELESAI = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
				CARI_STATUSFAIL = rs.getString(6) == null ? "" : rs.getString(6);
				returnValue += i + "\t" + CARI_NOFAIL + "\t" + CARI_STATUS + "\t" + CARI_NAMAPEMOHON + "\t" + CARI_JENISHARTA + "\t" + CARI_TARIKHHANTAR 
					+ "\t" + CARI_TARIKHSELESAI + "\t" + CARI_STATUSFAIL + "\r\n";
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchNilaianHTA(int JENIS_HTA, Boolean isJPPHUser, String USER_ID) throws Exception {
		Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>();
		Db db = new Db();
		
		try {
			String SQL_WHERE = "", TBL_HTA = "";
			if (JENIS_HTA == 0) {
				TBL_HTA = "TBLINTJPPHAH ";
			} else {
				TBL_HTA = "TBLINTJPPHTH ";
			}
			if (isJPPHUser) {
				SQL_WHERE += "AND (UPPER(M.STATUS_PROSES) != 'SELESAI' AND UPPER(M.STATUS_PROSES) != 'DIKEMBALIKAN') ";
			}
			String sql = "";
			String date30 = "", date14 = "", date05 = "";
			Hashtable<String, Comparable> h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			String ID_NEGERI = "", ID_MUKIM = "", ID_DAERAH = "", TEMP = "";
			if (!"".equalsIgnoreCase(USER_ID)) {
				if (isJPPHUser) {
					sql = "SELECT I.ID_NEGERI, I.ID_DAERAH, I.ID_MUKIM " +
						"FROM USERS U, USERS_INTEGRASI I " +
						"WHERE U.USER_ID = I.USER_ID " +
						"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "OR (M.ID_NEGERI = " + ID_NEGERI + " AND M.ID_DAERAH = " + ID_DAERAH;
						} else {
							TEMP = "M.ID_NEGERI = " + ID_NEGERI + " AND M.ID_DAERAH = " + ID_DAERAH;
						}
					}
				} else {
					// boleh view fail utk negeri dia saje
					sql = "SELECT I.ID_NEGERI " +
					"FROM USERS U, USERS_INTERNAL I " +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "AND M.ID_NEGERI = " + ID_NEGERI + " ";
						} else {
							TEMP = "M.ID_NEGERI = " + ID_NEGERI + " ";
						}
					}
				}
				// k-mie, 20120126
				// HQ boleh access semua file
				if ("16".equalsIgnoreCase(ID_NEGERI.trim())) {
					TEMP = "";
				}
				if (!"".equalsIgnoreCase(TEMP)) {
					SQL_WHERE += "AND (" + TEMP.trim() + ") ";
				}
			}
			
			sql = "SELECT M.ID_PERMOHONAN, FA.NO_FAIL, PMHN.NO_PERMOHONAN, SM.NAMA_SIMATI, CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, MHN.NAMA_PEMOHON, M.STATUS_PROSES, M.TARIKH_KEMASKINI, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(M.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05, " +
				"M.ID_HTA, DA.NAMA_DAERAH, MK.NAMA_MUKIM, M.NO_PTLOT " + 
				"FROM " + TBL_HTA + " M, TBLPPKPERMOHONAN PMHN, TBLPFDFAIL FA, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLRUJDAERAH DA, TBLRUJMUKIM MK " +
				"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FA.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON " + SQL_WHERE +
				"AND M.ID_DAERAH = DA.ID_DAERAH(+) AND M.ID_MUKIM = MK.ID_MUKIM(+) " +
				"ORDER BY M.TARIKH_KEMASKINI, M.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_SIMATI = "", NO_KP = "", NAMA_PEMOHON = "", STATUS = "", TARIKH_HANTAR = "", ID_HTA = "";
			String DAERAH = "", MUKIM = "", NO_PTLOT = "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP = rs.getString(5) == null ? "" : rs.getString(5);
				NAMA_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				TARIKH_HANTAR = rs.getDate(8) == null ? "" : sdf.format(rs.getDate(8));
				date30 = rs.getString(9) == null ? "" : rs.getString(9);
				date14 = rs.getString(10) == null ? "" : rs.getString(10);
				date05 = rs.getString(11) == null ? "" : rs.getString(11);
				ID_HTA = rs.getString(12) == null ? "" : rs.getString(12);
				DAERAH = rs.getString(13) == null ? "" : rs.getString(13);
				MUKIM = rs.getString(14) == null ? "" : rs.getString(14);
				NO_PTLOT = rs.getString(15) == null ? "" : rs.getString(15);
				
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
				
				if ("".equalsIgnoreCase(NO_FAIL.trim())) {
					NO_FAIL = "[TIADA NO FAIL]";
				}
				
				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
				h.put("date30", date30);
				h.put("date14", date14);
				h.put("date05", date05);
				h.put("ID_HTA", ID_HTA);
				h.put("Daerah", DAERAH);
				h.put("Mukim", MUKIM);
				h.put("NoPTLot", NO_PTLOT);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchNilaianHAK(Boolean isJPPHUser, String USER_ID) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_WHERE = "";
			String date30 = "", date14 = "", date05 = "";	
			Hashtable<String, Comparable> h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			String ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", TEMP = "";
			if (!"".equalsIgnoreCase(USER_ID)) {
				if (isJPPHUser) {
					sql = "SELECT I.ID_NEGERI, I.ID_DAERAH " +
					"FROM USERS U, USERS_INTEGRASI I " +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "OR C.ID_NEGERILOKASI = " + ID_NEGERI + " AND C.ID_BANDARLOKASI = " + ID_DAERAH;
						} else {
							TEMP = "C.ID_NEGERILOKASI = " + ID_NEGERI + " AND C.ID_BANDARLOKASI = " + ID_DAERAH;
						}
					}
				} else {
					// boleh view fail utk negeri dia saje
					sql = "SELECT I.ID_NEGERI, I.ID_DAERAH " +
					"FROM USERS U, USERS_INTERNAL I " +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "AND C.ID_NEGERILOKASI = " + ID_NEGERI + " ";
						} else {
							TEMP = "C.ID_NEGERILOKASI = " + ID_NEGERI + " ";
						}
					}
				}
				// k-mie, 20120126
				// HQ boleh access semua file
				if ("16".equalsIgnoreCase(ID_NEGERI.trim())) {
					TEMP = "";
				}
				if (!"".equalsIgnoreCase(TEMP)) {
					SQL_WHERE += "AND (" + TEMP.trim() + ")";
				}
			}
			if (isJPPHUser) {
				SQL_WHERE += "AND (UPPER(C.STATUS_PROSES) != 'SELESAI' AND UPPER(C.STATUS_PROSES) != 'DIKEMBALIKAN') ";
			} else {
				SQL_WHERE += " ";
			}
			
			sql = "SELECT PMHN.ID_PERMOHONAN, FA.NO_FAIL, PMHN.NO_PERMOHONAN, SM.NAMA_SIMATI, CASE WHEN SM.NO_KP_LAIN <> '' THEN SM.NO_KP_LAIN ELSE CASE WHEN SM.NO_KP_LAMA <> '' THEN SM.NO_KP_LAMA ELSE SM.NO_KP_BARU END END AS NO_KP_SIMATI, MHN.NAMA_PEMOHON, C.STATUS_PROSES, C.TARIKH_KEMASKINI, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05, " +
				"ID_HA, NG.NAMA_NEGERI, DA.NAMA_DAERAH, C.NO_PENDAFTARAN " +
				"FROM TBLINTJPPHHA C, TBLPPKPERMOHONAN PMHN, TBLPFDFAIL FA, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPPKSIMATI SM, TBLPPKPEMOHON MHN, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
				"WHERE C.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FA.ID_FAIL AND PMHN.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND PMHNSM.ID_SIMATI = SM.ID_SIMATI AND PMHN.ID_PEMOHON = MHN.ID_PEMOHON " + SQL_WHERE +
				"AND C.ID_NEGERILOKASI = NG.ID_NEGERI(+) AND C.ID_BANDARLOKASI = DA.ID_DAERAH(+) " +
				"ORDER BY C.TARIKH_KEMASKINI, C.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", NO_PERMOHONAN = "", NAMA_SIMATI = "", NO_KP = "", NAMA_PEMOHON = "", STATUS = "", TARIKH_HANTAR = "", ID_HA = "";
			String NEGERI = "", DAERAH = "", NO_PENDAFTARAN = "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP = rs.getString(5) == null ? "" : rs.getString(5);
				NAMA_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				TARIKH_HANTAR = rs.getDate(8) == null ? "" : sdf.format(rs.getDate(8));
				date30 = rs.getString(9) == null ? "" : rs.getString(9);
				date14 = rs.getString(10) == null ? "" : rs.getString(10);
				date05 = rs.getString(11) == null ? "" : rs.getString(11);
				ID_HA = rs.getString(12) == null ? "" : rs.getString(12);
				NEGERI = rs.getString(13) == null ? "" : rs.getString(13);
				DAERAH = rs.getString(14) == null ? "" : rs.getString(14);
				NO_PENDAFTARAN = rs.getString(15) == null ? "" : rs.getString(15);
				
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

				if ("".equalsIgnoreCase(NO_FAIL.trim())) {
					NO_FAIL = "[TIADA NO FAIL]";
				}

				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("TarikhHantar", TARIKH_HANTAR);
				h.put("Status", STATUS);
				h.put("date30", date30);
				h.put("date14", date14);
				h.put("date05", date05);
				h.put("ID_HA", ID_HA);
				h.put("Negeri", NEGERI);
				h.put("Daerah", DAERAH);
				h.put("NoPendaftaran", NO_PENDAFTARAN);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchNilaianSewaan(Boolean isJPPHUser, String USER_ID) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_WHERE = "";
			String date30 = "", date14 = "", date05 = "";	
			Hashtable<String, Comparable> h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			String ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", TEMP = "";
			if (!"".equalsIgnoreCase(USER_ID)) {
				if (isJPPHUser) {
					sql = "SELECT I.ID_NEGERI, I.ID_DAERAH, I.ID_MUKIM " +
						"FROM USERS U, USERS_INTEGRASI I " +
						"WHERE U.USER_ID = I.USER_ID " +
						"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "OR C.ID_NEGERI = " + ID_NEGERI + " AND C.ID_DAERAH = " + ID_DAERAH;
						} else {
							TEMP = "C.ID_NEGERI = " + ID_NEGERI + " AND C.ID_DAERAH = " + ID_DAERAH;
						}
					}
				} else {
					// boleh view fail utk negeri dia saje
					sql = "SELECT I.ID_NEGERI " +
						"FROM USERS U, USERS_INTERNAL I " +
						"WHERE U.USER_ID = I.USER_ID " +
						"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "AND C.ID_NEGERI = " + ID_NEGERI + " ";
						} else {
							TEMP = "C.ID_NEGERI = " + ID_NEGERI + " ";
						}
					}
				}
				// k-mie, 20120126
				// HQ boleh access semua file
				if ("16".equalsIgnoreCase(ID_NEGERI.trim())) {
					TEMP = "";
				}
				if (!"".equalsIgnoreCase(TEMP)) {
					SQL_WHERE += "AND (" + TEMP.trim() + ")";
				}
			}
			if (isJPPHUser) {
				SQL_WHERE += "AND (UPPER(C.STATUS_PROSES) != 'SELESAI' AND UPPER(C.STATUS_PROSES) != 'DIKEMBALIKAN') ";
			} else {
				SQL_WHERE += " ";
			}
			
			sql = "SELECT C.ID_PERMOHONAN, C.ID_HM, FA.NO_FAIL, C.NO_HAKMILIK, C.NO_PTLOT, NG.NAMA_NEGERI, DA.NAMA_DAERAH, C.STATUS_PROSES, C.TARIKH_KEMASKINI, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05 " +
				"FROM TBLINTJPPHSEWAAN C, TBLPHPHAKMILIKPERMOHONAN PHPPMHN, TBLPERMOHONAN M, TBLHTPHAKMILIKAGENSI HTPHMAG, TBLHTPHAKMILIK HTPHM, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
				"WHERE C.ID_PERMOHONAN = PHPPMHN.ID_PERMOHONAN AND PHPPMHN.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_FAIL = FA.ID_FAIL AND PHPPMHN.ID_HAKMILIKAGENSI = HTPHMAG.ID_HAKMILIKAGENSI AND HTPHMAG.ID_HAKMILIK = HTPHM.ID_HAKMILIK AND M.ID_FAIL = FA.ID_FAIL AND HTPHM.ID_NEGERI = NG.ID_NEGERI(+) AND HTPHM.ID_DAERAH = DA.ID_DAERAH(+) " + 
				SQL_WHERE +
				"ORDER BY C.TARIKH_KEMASKINI, C.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", ID_HM = "", NO_FAIL = "", NO_HAKMILIK = "", NO_PTLOT = "", NEGERI = "", DAERAH = "", STATUS = "", TARIKH_KEMASKINI = "";
			//myLog.info("sql="+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_HM = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NO_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
				NO_PTLOT = rs.getString(5) == null ? "" : rs.getString(5);
				NEGERI = rs.getString(6) == null ? "" : rs.getString(6);
				DAERAH = rs.getString(7) == null ? "" : rs.getString(7);
				STATUS = rs.getString(8) == null ? "" : rs.getString(8);
				TARIKH_KEMASKINI = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
				date30 = rs.getString(10) == null ? "" : rs.getString(10);
				date14 = rs.getString(11) == null ? "" : rs.getString(11);
				date05 = rs.getString(12) == null ? "" : rs.getString(12);
				
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

				if ("".equalsIgnoreCase(NO_FAIL.trim())) {
					NO_FAIL = "[TIADA NO FAIL]";
				}

				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("IDHM", ID_HM);
				h.put("NoFail", NO_FAIL);
				h.put("NoHakmilik", NO_HAKMILIK);
				h.put("NoPTLot", NO_PTLOT);
				h.put("Negeri", NEGERI);
				h.put("Daerah", DAERAH);
				h.put("TarikhKemaskini", TARIKH_KEMASKINI);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchNilaianPajakan(Boolean isJPPHUser, String USER_ID) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_WHERE = "";
			String date30 = "", date14 = "", date05 = "";	
			Hashtable<String, Comparable> h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			String ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", TEMP = "";
			if (!"".equalsIgnoreCase(USER_ID)) {
				if (isJPPHUser) {
					sql = "SELECT I.ID_NEGERI, I.ID_DAERAH, I.ID_MUKIM " +
					"FROM USERS U, USERS_INTEGRASI I " +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_DAERAH = rs.getString(2) == null ? "" : rs.getString(2);
						ID_MUKIM = rs.getString(3) == null ? "" : rs.getString(3);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "OR C.ID_NEGERI = " + ID_NEGERI + " AND C.ID_DAERAH = " + ID_DAERAH;
						} else {
							TEMP = "C.ID_NEGERI = " + ID_NEGERI + " AND C.ID_DAERAH = " + ID_DAERAH;
						}
					}
				} else {
					// boleh view fail utk negeri dia saje
					sql = "SELECT I.ID_NEGERI " +
					"FROM USERS U, USERS_INTERNAL I " +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND U.USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
						if (!"".equalsIgnoreCase(TEMP)) {
							TEMP += "AND C.ID_NEGERI = " + ID_NEGERI + " ";
						} else {
							TEMP = "C.ID_NEGERI = " + ID_NEGERI + " ";
						}
					}
				}
				// k-mie, 20120126
				// HQ boleh access semua file
				if ("16".equalsIgnoreCase(ID_NEGERI.trim())) {
					TEMP = "";
				}
				if (!"".equalsIgnoreCase(TEMP)) {
					SQL_WHERE += "AND (" + TEMP.trim() + ")";
				}
			}
			if (isJPPHUser) {
				SQL_WHERE += "AND (UPPER(C.STATUS_PROSES) != 'SELESAI' AND UPPER(C.STATUS_PROSES) != 'DIKEMBALIKAN') ";
			} else {
				SQL_WHERE += " ";
			}
			
			sql = "SELECT C.ID_PERMOHONAN, C.ID_HM, FA.NO_FAIL, C.NO_HAKMILIK, C.NO_PTLOT, NG.NAMA_NEGERI, DA.NAMA_DAERAH, C.STATUS_PROSES, C.TARIKH_KEMASKINI, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 30) THEN '1' ELSE '2' END AS MORE_30, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 14) THEN '1' ELSE '2' END AS MORE_14, " +
				"CASE WHEN (TO_DATE(SYSDATE, 'dd/MM/yyyy') - TO_DATE(C.TARIKH_KEMASKINI, 'dd/MM/yyyy') > 05) THEN '1' ELSE '2' END AS MORE_05 " +
				"FROM TBLINTJPPHPAJAKAN C, TBLPERMOHONAN PMHN, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
				"WHERE C.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FA.ID_FAIL AND FA.ID_NEGERI = NG.ID_NEGERI(+) AND FA.ID_DAERAH = DA.ID_DAERAH(+) " + 
				SQL_WHERE +
				"ORDER BY C.TARIKH_KEMASKINI, C.STATUS_PROSES";
			int i = 1;
			String ID_PERMOHONAN = "", ID_HM = "", NO_FAIL = "", NO_HAKMILIK = "", NO_PTLOT = "", NEGERI = "", DAERAH = "", STATUS = "", TARIKH_KEMASKINI = "";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_HM = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NO_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
				NO_PTLOT = rs.getString(5) == null ? "" : rs.getString(5);
				NEGERI = rs.getString(6) == null ? "" : rs.getString(6);
				DAERAH = rs.getString(7) == null ? "" : rs.getString(7);
				STATUS = rs.getString(8) == null ? "" : rs.getString(8);
				TARIKH_KEMASKINI = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
				date30 = rs.getString(10) == null ? "" : rs.getString(10);
				date14 = rs.getString(11) == null ? "" : rs.getString(11);
				date05 = rs.getString(12) == null ? "" : rs.getString(12);
				
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

				if ("".equalsIgnoreCase(NO_FAIL.trim())) {
					NO_FAIL = "[TIADA NO FAIL]";
				}

				h = new Hashtable<String, Comparable>();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("IDHM", ID_HM);
				h.put("NoFail", NO_FAIL);
				h.put("NoHakmilik", NO_HAKMILIK);
				h.put("NoPTLot", NO_PTLOT);
				h.put("Negeri", NEGERI);
				h.put("Daerah", DAERAH);
				h.put("TarikhKemaskini", TARIKH_KEMASKINI);
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