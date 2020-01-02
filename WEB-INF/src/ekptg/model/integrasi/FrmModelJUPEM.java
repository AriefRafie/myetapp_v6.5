package ekptg.model.integrasi;

//import java.sql.Blob;
import java.io.File;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import ekptg.helpers.DB;
import ekptg.intergration.FileUploadProperty;
//----------------------------------
//XML
//----------------------------------

public class FrmModelJUPEM {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector getMaklumatHakmilik(Vector vHM) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vHM.isEmpty()) {
				Hashtable h = new Hashtable();
				h = (Hashtable) vHM.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "";
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						sql = "SELECT DE.NAMA_DAERAH, MK.NAMA_MUKIM, JHM.KETERANGAN, HM.NO_HAKMILIK, CASE WHEN HM.NO_LOT <> '' THEN HM.NO_LOT ELSE HM.NO_PT END, HM.TARIKH_BORANGK, HM.LUAS_AMBIL, HM.TARIKH_SURAT_PTG, HM.NO_SYIT " +
							"FROM TBLPPTPERMOHONAN PMHN, TBLPPTHAKMILIK HM, TBLRUJDAERAH DE, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JHM " +
							"WHERE PMHN.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_DAERAH = DE.ID_DAERAH(+) AND HM.ID_MUKIM = MK.ID_MUKIM(+) AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) " +
							"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND HM.ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							h.clear();
							String DAERAH = "", MUKIM = "", JENIS_HAKMILIK = "", NO_HAKMILIK = "", NO_LOTPT = "", TARIKH_DAFTAR_BORANGK = "", LUAS_DIAMBIL = "", TARIKH_SURAT_PTG = "", NO_SYIT = "";
							DAERAH = rs.getString(1) == null ? "" : rs.getString(1);
							MUKIM = rs.getString(2) == null ? "" : rs.getString(2);
							JENIS_HAKMILIK = rs.getString(3) == null ? "" : rs.getString(3);
							NO_HAKMILIK = rs.getString(4) == null ? "" : rs.getString(4);
							NO_LOTPT = rs.getString(5) == null ? "" : rs.getString(5);
							TARIKH_DAFTAR_BORANGK = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
							LUAS_DIAMBIL = rs.getString(7) == null ? "" : rs.getString(7);
							TARIKH_SURAT_PTG = rs.getDate(8) == null ? "" : sdf.format(rs.getDate(8));
							NO_SYIT = rs.getString(9) == null ? "" : rs.getString(9);
	
							h.put("DAERAH", DAERAH);
							h.put("MUKIM", MUKIM);
							h.put("JENIS_HAKMILIK", JENIS_HAKMILIK);
							h.put("NO_HAKMILIK", NO_HAKMILIK);
							h.put("NO_LOTPT", NO_LOTPT);
							h.put("TARIKH_DAFTAR_BORANGK", TARIKH_DAFTAR_BORANGK);
							h.put("LUAS_DIAMBIL", LUAS_DIAMBIL);
							h.put("TARIKH_SURAT_PTG", TARIKH_SURAT_PTG);
							h.put("NO_SYIT", NO_SYIT);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListPUB(Vector vPUB) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUB.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUB.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "";
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						int iCount = 0;
						String ID_JUPEMPUB = "", PUNo = "", PTNo = "", Lot = "", QTNo = "", UPI = "", PUQTKey = "";
						sql = "SELECT PUB.ID_JUPEMPUB, PUB.DATA_05, PUB.DATA_06, PUB.DATA_07, PUB.DATA_08, PUB.DATA_09, PUB.DATA_10 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUB PUB " +
							"WHERE M.ID_JUPEM = PUB.ID_JUPEM " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							ID_JUPEMPUB = rs.getString(1) == null ? "" : rs.getString(1);
							PUNo = rs.getString(2) == null ? "" : rs.getString(2);
							PTNo = rs.getString(3) == null ? "" : rs.getString(3);
							Lot = rs.getString(4) == null ? "" : rs.getString(4);
							QTNo = rs.getString(5) == null ? "" : rs.getString(5);
							UPI = rs.getString(6) == null ? "" : rs.getString(6);
							PUQTKey = rs.getString(7) == null ? "" : rs.getString(7);
	
							h = new Hashtable();
							iCount++;
							h.put("ID_JUPEMPUB", ID_JUPEMPUB);
							h.put("No", iCount);
							h.put("PUNo", PUNo);
							h.put("PTNo", PTNo);
							h.put("Lot", Lot);
							h.put("QTNo", QTNo);
							h.put("UPI", UPI);
							h.put("PUQTKey", PUQTKey);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getPUBData(Vector vPUB) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUB.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUB.get(0);
				if (!h.isEmpty()) {
					String ID_JUPEMPUB = "";
					ID_JUPEMPUB = (String) h.get("ID_JUPEMPUB");
					if (!"".equalsIgnoreCase(ID_JUPEMPUB)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "", DATA_14 = "", DATA_15 = "", DATA_16 = "", DATA_17 = "", DATA_18 = "", DATA_19 = "", DATA_20 = "";
						String DATA_21 = "", DATA_22 = "", DATA_23 = "", DATA_24 = "", DATA_25 = "", DATA_26 = "";
						sql = "SELECT PUB.DATA_01, PUB.DATA_02, PUB.DATA_03, PUB.DATA_04, PUB.DATA_05, PUB.DATA_06, PUB.DATA_07, PUB.DATA_08, PUB.DATA_09, PUB.DATA_10, " +
							"PUB.DATA_11, PUB.DATA_12, PUB.DATA_13, PUB.DATA_14, PUB.DATA_15, PUB.DATA_16, PUB.DATA_17, PUB.DATA_18, PUB.DATA_19, PUB.DATA_20, " +
							"PUB.DATA_21, PUB.DATA_22, PUB.DATA_23, PUB.DATA_24, PUB.DATA_25, PUB.DATA_26 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUB PUB " +
							"WHERE M.ID_JUPEM = PUB.ID_JUPEM " +
							"AND PUB.ID_JUPEMPUB = " + ID_JUPEMPUB;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
							DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
							DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
							DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
							DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
							DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
							DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
							DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
							DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
							DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
							DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
							DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
							DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
							DATA_14 = rs.getString(14) == null ? "" : rs.getString(14);
							DATA_15 = rs.getString(15) == null ? "" : rs.getString(15);
							DATA_16 = rs.getString(16) == null ? "" : rs.getString(16);
							DATA_17 = rs.getString(17) == null ? "" : rs.getString(17);
							DATA_18 = rs.getString(18) == null ? "" : rs.getString(18);
							DATA_19 = rs.getString(19) == null ? "" : rs.getString(19);
							DATA_20 = rs.getString(20) == null ? "" : rs.getString(20);
							DATA_21 = rs.getString(21) == null ? "" : rs.getString(21);
							DATA_22 = rs.getString(22) == null ? "" : rs.getString(22);
							DATA_23 = rs.getString(23) == null ? "" : rs.getString(23);
							DATA_24 = rs.getString(24) == null ? "" : rs.getString(24);
							DATA_25 = rs.getString(25) == null ? "" : rs.getString(25);
							DATA_26 = rs.getString(26) == null ? "" : rs.getString(26);
	
							h = new Hashtable();
							h.put("DATA_PUB_01", DATA_01);
							h.put("DATA_PUB_02", DATA_02);
							h.put("DATA_PUB_03", DATA_03);
							h.put("DATA_PUB_04", DATA_04);
							h.put("DATA_PUB_05", DATA_05);
							h.put("DATA_PUB_06", DATA_06);
							h.put("DATA_PUB_07", DATA_07);
							h.put("DATA_PUB_08", DATA_08);
							h.put("DATA_PUB_09", DATA_09);
							h.put("DATA_PUB_10", DATA_10);
							h.put("DATA_PUB_11", DATA_11);
							h.put("DATA_PUB_12", DATA_12);
							h.put("DATA_PUB_13", DATA_13);
							h.put("DATA_PUB_14", DATA_14);
							h.put("DATA_PUB_15", DATA_15);
							h.put("DATA_PUB_16", DATA_16);
							h.put("DATA_PUB_17", DATA_17);
							h.put("DATA_PUB_18", DATA_18);
							h.put("DATA_PUB_19", DATA_19);
							h.put("DATA_PUB_20", DATA_20);
							h.put("DATA_PUB_21", DATA_21);
							h.put("DATA_PUB_22", DATA_22);
							h.put("DATA_PUB_23", DATA_23);
							h.put("DATA_PUB_24", DATA_24);
							h.put("DATA_PUB_25", DATA_25);
							h.put("DATA_PUB_26", DATA_26);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getPUDData(Vector vPUD) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUD.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUD.get(0);
				if (!h.isEmpty()) {
					String ID_JUPEMPUD = "";
					ID_JUPEMPUD = (String) h.get("ID_JUPEMPUD");
					if (!"".equalsIgnoreCase(ID_JUPEMPUD)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "", DATA_14 = "";
						sql = "SELECT PUD.DATA_01, PUD.DATA_02, PUD.DATA_03, PUD.DATA_04, PUD.DATA_05, PUD.DATA_06, PUD.DATA_07, PUD.DATA_08, PUD.DATA_09, PUD.DATA_10, " +
							"PUD.DATA_11, PUD.DATA_12, PUD.DATA_13, PUD.DATA_14 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUD PUD " +
							"WHERE M.ID_JUPEM = PUD.ID_JUPEM " +
							"AND PUD.ID_JUPEMPUD = " + ID_JUPEMPUD;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
							DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
							DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
							DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
							DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
							DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
							DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
							DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
							DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
							DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
							DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
							DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
							DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
							DATA_14 = rs.getString(14) == null ? "" : rs.getString(14);
	
							h = new Hashtable();
							h.put("DATA_PUD_01", DATA_01);
							h.put("DATA_PUD_02", DATA_02);
							h.put("DATA_PUD_03", DATA_03);
							h.put("DATA_PUD_04", DATA_04);
							h.put("DATA_PUD_05", DATA_05);
							h.put("DATA_PUD_06", DATA_06);
							h.put("DATA_PUD_07", DATA_07);
							h.put("DATA_PUD_08", DATA_08);
							h.put("DATA_PUD_09", DATA_09);
							h.put("DATA_PUD_10", DATA_10);
							h.put("DATA_PUD_11", DATA_11);
							h.put("DATA_PUD_12", DATA_12);
							h.put("DATA_PUD_13", DATA_13);
							h.put("DATA_PUD_14", DATA_14);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getPULData(Vector vPUL) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUL.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUL.get(0);
				if (!h.isEmpty()) {
					String ID_JUPEMPUL = "";
					ID_JUPEMPUL = (String) h.get("ID_JUPEMPUL");
					if (!"".equalsIgnoreCase(ID_JUPEMPUL)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "";
						sql = "SELECT PUL.DATA_01, PUL.DATA_02, PUL.DATA_03, PUL.DATA_04, PUL.DATA_05, PUL.DATA_06, PUL.DATA_07, PUL.DATA_08, PUL.DATA_09, PUL.DATA_10, " +
							"PUL.DATA_11, PUL.DATA_12, PUL.DATA_13 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUL PUL " +
							"WHERE M.ID_JUPEM = PUL.ID_JUPEM " +
							"AND PUL.ID_JUPEMPUL = " + ID_JUPEMPUL;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
							DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
							DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
							DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
							DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
							DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
							DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
							DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
							DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
							DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
							DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
							DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
							DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
	
							h = new Hashtable();
							h.put("DATA_PUL_01", DATA_01);
							h.put("DATA_PUL_02", DATA_02);
							h.put("DATA_PUL_03", DATA_03);
							h.put("DATA_PUL_04", DATA_04);
							h.put("DATA_PUL_05", DATA_05);
							h.put("DATA_PUL_06", DATA_06);
							h.put("DATA_PUL_07", DATA_07);
							h.put("DATA_PUL_08", DATA_08);
							h.put("DATA_PUL_09", DATA_09);
							h.put("DATA_PUL_10", DATA_10);
							h.put("DATA_PUL_11", DATA_11);
							h.put("DATA_PUL_12", DATA_12);
							h.put("DATA_PUL_13", DATA_13);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
		
	@SuppressWarnings("unchecked")
	public Boolean saveMainData(Vector v) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", ID_USER = "";
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					ID_USER = (String) h.get("ID_USER");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						Boolean haveINTData = false;
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						long ID_JUPEM = 0;
						sql = "SELECT ID_JUPEM " +
							"FROM TBLINTJUPEM " +
							"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEM = rs.getInt(1);
						}
						if (ID_JUPEM > 0) {
							haveINTData = true;
						}
						
						r.clear();
						r.add("ID_KEMASKINI", ID_USER);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						r.add("ID_PERMOHONAN", ID_PERMOHONAN);
						r.add("ID_HAKMILIK", ID_HAKMILIK);
						if (haveINTData) {
							r.update("ID_JUPEM", ID_JUPEM);
							sql = r.getSQLUpdate("TBLINTJUPEM");
						} else {
							ID_JUPEM = DB.getNextID("TBLINTJUPEM_SEQ");
							r.add("ID_JUPEM", ID_JUPEM);
							r.add("ID_MASUK", ID_USER);
							r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
							sql = r.getSQLInsert("TBLINTJUPEM");
						}
						stmt.execute(sql);
						returnValue = true;
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}	
	
	@SuppressWarnings("unchecked")
	public Boolean savePUBData(Vector v) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", ID_USER = "", TEMP = "";
					long ID_JUPEMPUB = 0, ID_JUPEM = 0;
					
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					ID_USER = (String) h.get("ID_USER");
					TEMP = (String) h.get("ID_JUPEMPUB");
					if (FrmModelUtilitiesIntegration.isNumeric(TEMP)) {
						ID_JUPEMPUB = Long.parseLong(TEMP);
					}
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						Boolean haveINTData = false;
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						sql = "SELECT ID_JUPEM " +
						"FROM TBLINTJUPEM " +
						"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEM = rs.getInt(1);
						}
						
						sql = "SELECT PUB.ID_JUPEMPUB " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUB PUB " +
							"WHERE M.ID_JUPEM = PUB.ID_JUPEM(+) " +
							"AND PUB.ID_JUPEMPUB = " + ID_JUPEMPUB;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEMPUB = rs.getInt(1);
						}
						if (ID_JUPEMPUB > 0) {
							haveINTData = true;
						}

						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "", DATA_14 = "", DATA_15 = "", DATA_16 = "", DATA_17 = "", DATA_18 = "", DATA_19 = "", DATA_20 = "";
						String DATA_21 = "", DATA_22 = "", DATA_23 = "", DATA_24 = "", DATA_25 = "", DATA_26 = "";
						DATA_01 = (String) h.get("DATA_PUB_01");
						DATA_02 = (String) h.get("DATA_PUB_02");
						DATA_03 = (String) h.get("DATA_PUB_03");
						DATA_04 = (String) h.get("DATA_PUB_04");
						DATA_05 = (String) h.get("DATA_PUB_05");
						DATA_06 = (String) h.get("DATA_PUB_06");
						DATA_07 = (String) h.get("DATA_PUB_07");
						DATA_08 = (String) h.get("DATA_PUB_08");
						DATA_09 = (String) h.get("DATA_PUB_09");
						DATA_10 = (String) h.get("DATA_PUB_10");
						DATA_11 = (String) h.get("DATA_PUB_11");
						DATA_12 = (String) h.get("DATA_PUB_12");
						DATA_13 = (String) h.get("DATA_PUB_13");
						DATA_14 = (String) h.get("DATA_PUB_14");
						DATA_15 = (String) h.get("DATA_PUB_15");
						DATA_16 = (String) h.get("DATA_PUB_16");
						DATA_17 = (String) h.get("DATA_PUB_17");
						DATA_18 = (String) h.get("DATA_PUB_18");
						DATA_19 = (String) h.get("DATA_PUB_19");
						DATA_20 = (String) h.get("DATA_PUB_20");
						DATA_21 = (String) h.get("DATA_PUB_21");
						DATA_22 = (String) h.get("DATA_PUB_22");
						DATA_23 = (String) h.get("DATA_PUB_23");
						DATA_24 = (String) h.get("DATA_PUB_24");
						DATA_25 = (String) h.get("DATA_PUB_25");
						DATA_26 = (String) h.get("DATA_PUB_26");
						
						r.clear();
						r.add("ID_KEMASKINI", ID_USER);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						r.add("ID_JUPEM", ID_JUPEM);
						r.add("DATA_01", DATA_01);
						r.add("DATA_02", DATA_02);
						r.add("DATA_03", DATA_03);
						r.add("DATA_04", DATA_04);
						r.add("DATA_05", DATA_05);
						r.add("DATA_06", DATA_06);
						r.add("DATA_07", DATA_07);
						r.add("DATA_08", DATA_08);
						r.add("DATA_09", DATA_09);
						r.add("DATA_10", DATA_10);
						r.add("DATA_11", DATA_11);
						r.add("DATA_12", DATA_12);
						r.add("DATA_13", DATA_13);
						r.add("DATA_14", DATA_14);
						r.add("DATA_15", DATA_15);
						r.add("DATA_16", DATA_16);
						r.add("DATA_17", DATA_17);
						r.add("DATA_18", DATA_18);
						r.add("DATA_19", DATA_19);
						r.add("DATA_20", DATA_20);
						r.add("DATA_21", DATA_21);
						r.add("DATA_22", DATA_22);
						r.add("DATA_23", DATA_23);
						r.add("DATA_24", DATA_24);
						r.add("DATA_25", DATA_25);
						r.add("DATA_26", DATA_26);
						if (haveINTData) {
							r.update("ID_JUPEMPUB", ID_JUPEMPUB);
							sql = r.getSQLUpdate("TBLINTJUPEMPUB");
						} else {
							ID_JUPEMPUB = DB.getNextID("TBLINTJUPEMPUB_SEQ");
							r.add("ID_JUPEMPUB", ID_JUPEMPUB);
							r.add("ID_MASUK", ID_USER);
							r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
							sql = r.getSQLInsert("TBLINTJUPEMPUB");
						}
						stmt.execute(sql);
						returnValue = true;
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector getListPUD(Vector vPUD) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUD.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUD.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "";
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						int iCount = 0;
						String ID_JUPEMPUD = "", PUNo = "", LoNo = "", FileNo = "", LandUseCode = "", LandTitleCode = "";
						sql = "SELECT PUD.ID_JUPEMPUD, PUD.DATA_05, PUD.DATA_06, PUD.DATA_07, PUD.DATA_11, PUD.DATA_12 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUD PUD " +
							"WHERE M.ID_JUPEM = PUD.ID_JUPEM " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							ID_JUPEMPUD = rs.getString(1) == null ? "" : rs.getString(1);
							PUNo = rs.getString(2) == null ? "" : rs.getString(2);
							LoNo = rs.getString(3) == null ? "" : rs.getString(3);
							FileNo = rs.getString(4) == null ? "" : rs.getString(4);
							LandUseCode = rs.getString(5) == null ? "" : rs.getString(5);
							LandTitleCode = rs.getString(6) == null ? "" : rs.getString(6);
	
							h = new Hashtable();
							iCount++;
							h.put("ID_JUPEMPUD", ID_JUPEMPUD);
							h.put("No", iCount);
							h.put("PUNo", PUNo);
							h.put("LoNo", LoNo);
							h.put("FileNo", FileNo);
							h.put("LandUseCode", LandUseCode);
							h.put("LandTitleCode", LandTitleCode);
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean savePUDData(Vector v) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", ID_USER = "", TEMP = "";
					long ID_JUPEMPUD = 0, ID_JUPEM = 0;
					
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					ID_USER = (String) h.get("ID_USER");
					TEMP = (String) h.get("ID_JUPEMPUD");
					if (FrmModelUtilitiesIntegration.isNumeric(TEMP)) {
						ID_JUPEMPUD = Long.parseLong(TEMP);
					}
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						Boolean haveINTData = false;
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						sql = "SELECT ID_JUPEM " +
						"FROM TBLINTJUPEM " +
						"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEM = rs.getInt(1);
						}
						
						sql = "SELECT PUD.ID_JUPEMPUD " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUD PUD " +
							"WHERE M.ID_JUPEM = PUD.ID_JUPEM(+) " +
							"AND PUD.ID_JUPEMPUD = " + ID_JUPEMPUD;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEMPUD = rs.getInt(1);
						}
						if (ID_JUPEMPUD > 0) {
							haveINTData = true;
						}

						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "", DATA_14 = "";
						DATA_01 = (String) h.get("DATA_PUD_01");
						DATA_02 = (String) h.get("DATA_PUD_02");
						DATA_03 = (String) h.get("DATA_PUD_03");
						DATA_04 = (String) h.get("DATA_PUD_04");
						DATA_05 = (String) h.get("DATA_PUD_05");
						DATA_06 = (String) h.get("DATA_PUD_06");
						DATA_07 = (String) h.get("DATA_PUD_07");
						DATA_08 = (String) h.get("DATA_PUD_08");
						DATA_09 = (String) h.get("DATA_PUD_09");
						DATA_10 = (String) h.get("DATA_PUD_10");
						DATA_11 = (String) h.get("DATA_PUD_11");
						DATA_12 = (String) h.get("DATA_PUD_12");
						DATA_13 = (String) h.get("DATA_PUD_13");
						DATA_14 = (String) h.get("DATA_PUD_14");
						
						r.clear();
						r.add("ID_KEMASKINI", ID_USER);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						r.add("ID_JUPEM", ID_JUPEM);
						r.add("DATA_01", DATA_01);
						r.add("DATA_02", DATA_02);
						r.add("DATA_03", DATA_03);
						r.add("DATA_04", DATA_04);
						r.add("DATA_05", DATA_05);
						r.add("DATA_06", DATA_06);
						r.add("DATA_07", DATA_07);
						r.add("DATA_08", DATA_08);
						r.add("DATA_09", DATA_09);
						r.add("DATA_10", DATA_10);
						r.add("DATA_11", DATA_11);
						r.add("DATA_12", DATA_12);
						r.add("DATA_13", DATA_13);
						r.add("DATA_14", DATA_14);
						if (haveINTData) {
							r.update("ID_JUPEMPUD", ID_JUPEMPUD);
							sql = r.getSQLUpdate("TBLINTJUPEMPUD");
						} else {
							ID_JUPEMPUD = DB.getNextID("TBLINTJUPEMPUD_SEQ");
							r.add("ID_JUPEMPUD", ID_JUPEMPUD);
							r.add("ID_MASUK", ID_USER);
							r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
							sql = r.getSQLInsert("TBLINTJUPEMPUD");
						}
						stmt.execute(sql);
						returnValue = true;
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector getListPUL(Vector vPUL) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!vPUL.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) vPUL.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "";
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						int iCount = 0;
						String ID_JUPEMPUL = "", PUNo = "", PTNo = "", Lot = "", QTNo = "", UPI = "";
						sql = "SELECT PUL.ID_JUPEMPUL, PUL.DATA_05, PUL.DATA_06, PUL.DATA_07, PUL.DATA_08, PUL.DATA_09 " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUL PUL " +
							"WHERE M.ID_JUPEM = PUL.ID_JUPEM " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							ID_JUPEMPUL = rs.getString(1) == null ? "" : rs.getString(1);
							PUNo = rs.getString(2) == null ? "" : rs.getString(2);
							PTNo = rs.getString(3) == null ? "" : rs.getString(3);
							Lot = rs.getString(4) == null ? "" : rs.getString(4);
							QTNo = rs.getString(5) == null ? "" : rs.getString(5);
							UPI  = rs.getString(6) == null ? "" : rs.getString(6);
	
							h = new Hashtable();
							iCount++;
							h.put("ID_JUPEMPUL", ID_JUPEMPUL);
							h.put("No", iCount);
							h.put("PUNo", PUNo);
							h.put("PTNo", PTNo);
							h.put("Lot", Lot);
							h.put("QTNo", QTNo);
							h.put("UPI", UPI );
							v.add(h);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean savePULData(Vector v) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", ID_USER = "", TEMP = "";
					long ID_JUPEMPUL = 0, ID_JUPEM = 0;
					
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					ID_USER = (String) h.get("ID_USER");
					TEMP = (String) h.get("ID_JUPEMPUL");
					if (FrmModelUtilitiesIntegration.isNumeric(TEMP)) {
						ID_JUPEMPUL = Long.parseLong(TEMP);
					}
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
						Boolean haveINTData = false;
						String sql = "";
						ResultSet rs = null;
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						sql = "SELECT ID_JUPEM " +
						"FROM TBLINTJUPEM " +
						"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HAKMILIK = " + ID_HAKMILIK;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEM = rs.getInt(1);
						}
						
						sql = "SELECT PUL.ID_JUPEMPUL " +
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUL PUL " +
							"WHERE M.ID_JUPEM = PUL.ID_JUPEM(+) " +
							"AND PUL.ID_JUPEMPUL = " + ID_JUPEMPUL;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							ID_JUPEMPUL = rs.getInt(1);
						}
						if (ID_JUPEMPUL > 0) {
							haveINTData = true;
						}

						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "";
						DATA_01 = (String) h.get("DATA_PUL_01");
						DATA_02 = (String) h.get("DATA_PUL_02");
						DATA_03 = (String) h.get("DATA_PUL_03");
						DATA_04 = (String) h.get("DATA_PUL_04");
						DATA_05 = (String) h.get("DATA_PUL_05");
						DATA_06 = (String) h.get("DATA_PUL_06");
						DATA_07 = (String) h.get("DATA_PUL_07");
						DATA_08 = (String) h.get("DATA_PUL_08");
						DATA_09 = (String) h.get("DATA_PUL_09");
						DATA_10 = (String) h.get("DATA_PUL_10");
						DATA_11 = (String) h.get("DATA_PUL_11");
						DATA_12 = (String) h.get("DATA_PUL_12");
						DATA_13 = (String) h.get("DATA_PUL_13");
						
						r.clear();
						r.add("ID_KEMASKINI", ID_USER);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						r.add("ID_JUPEM", ID_JUPEM);
						r.add("DATA_01", DATA_01);
						r.add("DATA_02", DATA_02);
						r.add("DATA_03", DATA_03);
						r.add("DATA_04", DATA_04);
						r.add("DATA_05", DATA_05);
						r.add("DATA_06", DATA_06);
						r.add("DATA_07", DATA_07);
						r.add("DATA_08", DATA_08);
						r.add("DATA_09", DATA_09);
						r.add("DATA_10", DATA_10);
						r.add("DATA_11", DATA_11);
						r.add("DATA_12", DATA_12);
						r.add("DATA_13", DATA_13);
						if (haveINTData) {
							r.update("ID_JUPEMPUL", ID_JUPEMPUL);
							sql = r.getSQLUpdate("TBLINTJUPEMPUL");
						} else {
							ID_JUPEMPUL = DB.getNextID("TBLINTJUPEMPUL_SEQ");
							r.add("ID_JUPEMPUL", ID_JUPEMPUL);
							r.add("ID_MASUK", ID_USER);
							r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
							sql = r.getSQLInsert("TBLINTJUPEMPUL");
						}
						stmt.execute(sql);
						returnValue = true;
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getNamaUserByID(String ID_USER) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_USER)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				sql = "SELECT USER_NAME FROM USERS WHERE USER_ID = " + ID_USER;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getMIMEType(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("CONTENT_MIME");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getUploadID(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_ETERIMAAN");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListUploadedFile() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			SQLRenderer r = new SQLRenderer();
			int i = 1;
			String IDFail = "", NamaFail = "", NamaPegawai = "", TarikhMuatNaik = "", SHA1 = "", MIME = "";
			
			r.add("ID_EKADASTER");
			r.add("CONTENT_NAME");
			r.add("USER_ID");
			r.add("TARIKH_KEMASKINI");
			r.add("SHA1");
			r.add("CONTENT_MIME");
			sql = r.getSQLSelect("TBLINTEKADASTER", "TARIKH_KEMASKINI DESC");
			r.clear();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IDFail = rs.getString(1) == null ? "" : rs.getString(1);
				NamaFail = rs.getString(2) == null ? "" : rs.getString(2);
				NamaPegawai = rs.getString(3) == null ? "" : rs.getString(3);
				TarikhMuatNaik = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				SHA1 = rs.getString(5) == null ? "" : rs.getString(5);
				MIME = rs.getString(6) == null ? "" : rs.getString(6);
				
				NamaPegawai = getNamaUserByID(NamaPegawai);
					
				h = new Hashtable();
				h.put("No", i);
				h.put("IDFail", IDFail);
				h.put("NamaFail", NamaFail);
				h.put("NamaPegawai", NamaPegawai);
				h.put("TarikhMuatNaik", TarikhMuatNaik);
				h.put("SHA1", SHA1);
				h.put("MIME", MIME);
				
				v.add(h);
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	public Boolean doUpload(FileItem objItem, String userID) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String SHA1_Value = "";
			MessageDigest md = MessageDigest.getInstance("SHA1");

			md.update(objItem.get());
		    
		    byte[] mdbytes = md.digest();
		 
		    //convert the byte to hex format
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		    SHA1_Value = sb.toString();
		    
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			long ID_DB = DB.getNextID("TBLINTEKADASTER_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLINTEKADASTER " +
        			"(ID_EKADASTER, TARIKH_SIMPAN, TARIKH_KEMASKINI, USER_ID, CONTENT, CONTENT_NAME, CONTENT_MIME, SHA1) " +
        			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        	ps.setLong(1, ID_DB);
			ps.setDate(2, date);
			ps.setDate(3, date);
        	ps.setString(4, userID);
        	ps.setBinaryStream(5, objItem.getInputStream(), (int)objItem.getSize());
        	ps.setString(6, objItem.getName());
        	ps.setString(7, objItem.getContentType());
        	ps.setString(8, SHA1_Value);
        	ps.executeUpdate();
            con.commit();
            
            returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean processFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
		        Connection con = db.getConnection();
		        PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM TBLINTEKADASTER WHERE ID_EKADASTER = ?");
		        ps.setString(1, IDFail);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		        	//Blob b = rs.getBlob("CONTENT");
			        //String s = new String(b.getBytes(1l, (int) b.length()));
			        //System.out.println(s);
			        ps.execute("UPDATE TBLINTEKADASTER SET STATUS_PROSES = 'SELESAI' WHERE ID_EKADASTER = " + IDFail);
			        returnValue = true;
		        }
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
	
	public Boolean deleteFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
				Statement stmt = db.getStatement();
				String sql = "";
				sql = "DELETE FROM TBLINTEKADASTER WHERE ID_EKADASTER = " + IDFail;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public String generateFileName(Vector v) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", GEN_TYPE = "";
					
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					GEN_TYPE = (String) h.get("GEN_TYPE");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(GEN_TYPE)) {
						Statement stmt = db.getStatement();
						ResultSet rs = null;
						String sql = "";
						if ("PUB".equalsIgnoreCase(GEN_TYPE)) {
							sql = "SELECT PUB.DATA_01 || PUB.DATA_02 || PUB.DATA_03 || PUB.DATA_04 || PUB.DATA_05 AS FILENAME " + 
								"FROM TBLINTJUPEM M, TBLINTJUPEMPUB PUB " +
								"WHERE M.ID_JUPEM = PUB.ID_JUPEM " +
								"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						} else if ("PUD".equalsIgnoreCase(GEN_TYPE)) {
							sql = "SELECT PUD.DATA_01 || PUD.DATA_02 || PUD.DATA_03 || PUD.DATA_04 || PUD.DATA_05 AS FILENAME " + 
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUD PUD " +
							"WHERE M.ID_JUPEM = PUD.ID_JUPEM " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						} else {
							sql = "SELECT PUL.DATA_01 || PUL.DATA_02 || PUL.DATA_03 || PUL.DATA_04 || PUL.DATA_05 AS FILENAME " + 
							"FROM TBLINTJUPEM M, TBLINTJUPEMPUL PUL " +
							"WHERE M.ID_JUPEM = PUL.ID_JUPEM " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						}
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							returnValue = rs.getString(1) == null ? "" : rs.getString(1);
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		returnValue = returnValue.replace("/", "-");
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public String generateText(Vector v) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!v.isEmpty()) {
				Hashtable h = null;
				h = (Hashtable) v.get(0);
				if (!h.isEmpty()) {
					String ID_PERMOHONAN = "", ID_HAKMILIK = "", GEN_TYPE = "";
					
					ID_PERMOHONAN = (String) h.get("ID_PERMOHONAN");
					ID_HAKMILIK = (String) h.get("ID_HAKMILIK");
					GEN_TYPE = (String) h.get("GEN_TYPE");
					if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(GEN_TYPE)) {
						String DATA_01 = "", DATA_02 = "", DATA_03 = "", DATA_04 = "", DATA_05 = "", DATA_06 = "", DATA_07 = "", DATA_08 = "", DATA_09 = "", DATA_10 = "";
						String DATA_11 = "", DATA_12 = "", DATA_13 = "", DATA_14 = "", DATA_15 = "", DATA_16 = "", DATA_17 = "", DATA_18 = "", DATA_19 = "", DATA_20 = "";
						String DATA_21 = "", DATA_22 = "", DATA_23 = "", DATA_24 = "", DATA_25 = "", DATA_26 = "";
						Statement stmt = db.getStatement();
						ResultSet rs = null;
						String sql = "";
						if ("PUB".equalsIgnoreCase(GEN_TYPE)) {
							sql = "SELECT PUB.DATA_01, PUB.DATA_02, PUB.DATA_03, PUB.DATA_04, PUB.DATA_05, PUB.DATA_06, PUB.DATA_07, PUB.DATA_08, PUB.DATA_09, PUB.DATA_10, " +
								"PUB.DATA_11, PUB.DATA_12, PUB.DATA_13, PUB.DATA_14, PUB.DATA_15, PUB.DATA_16, PUB.DATA_17, PUB.DATA_18, PUB.DATA_19, PUB.DATA_20, " +
								"PUB.DATA_21, PUB.DATA_22, PUB.DATA_23, PUB.DATA_24, PUB.DATA_25, PUB.DATA_26 " +
								"FROM TBLINTJUPEM M, TBLINTJUPEMPUB PUB " +
								"WHERE M.ID_JUPEM = PUB.ID_JUPEM " +
								"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						} else if ("PUD".equalsIgnoreCase(GEN_TYPE)) {
							sql = "SELECT PUD.DATA_01, PUD.DATA_02, PUD.DATA_03, PUD.DATA_04, PUD.DATA_05, PUD.DATA_06, PUD.DATA_07, PUD.DATA_08, PUD.DATA_09, PUD.DATA_10, " +
								"PUD.DATA_11, PUD.DATA_12, PUD.DATA_13, PUD.DATA_14 " +
								"FROM TBLINTJUPEM M, TBLINTJUPEMPUD PUD " +
								"WHERE M.ID_JUPEM = PUD.ID_JUPEM " +
								"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						} else {
							sql = "SELECT PUL.DATA_01, PUL.DATA_02, PUL.DATA_03, PUL.DATA_04, PUL.DATA_05, PUL.DATA_06, PUL.DATA_07, PUL.DATA_08, PUL.DATA_09, PUL.DATA_10, " +
								"PUL.DATA_11, PUL.DATA_12, PUL.DATA_13 " +
								"FROM TBLINTJUPEM M, TBLINTJUPEMPUL PUL " +
								"WHERE M.ID_JUPEM = PUL.ID_JUPEM " +
								"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HAKMILIK = " + ID_HAKMILIK;
						}
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if ("PUB".equalsIgnoreCase(GEN_TYPE)) {
								DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
								DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
								DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
								DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
								DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
								DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
								DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
								DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
								DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
								DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
								DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
								DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
								DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
								DATA_14 = rs.getString(14) == null ? "" : rs.getString(14);
								DATA_15 = rs.getString(15) == null ? "" : rs.getString(15);
								DATA_16 = rs.getString(16) == null ? "" : rs.getString(16);
								DATA_17 = rs.getString(17) == null ? "" : rs.getString(17);
								DATA_18 = rs.getString(18) == null ? "" : rs.getString(18);
								DATA_19 = rs.getString(19) == null ? "" : rs.getString(19);
								DATA_20 = rs.getString(20) == null ? "" : rs.getString(20);
								DATA_21 = rs.getString(21) == null ? "" : rs.getString(21);
								DATA_22 = rs.getString(22) == null ? "" : rs.getString(22);
								DATA_23 = rs.getString(23) == null ? "" : rs.getString(23);
								DATA_24 = rs.getString(24) == null ? "" : rs.getString(24);
								DATA_25 = rs.getString(25) == null ? "" : rs.getString(25);
								DATA_26 = rs.getString(26) == null ? "" : rs.getString(26);
								
								returnValue += DATA_01 + "\t" + DATA_02 + "\t" + DATA_03 + "\t" + DATA_04 + "\t" + DATA_05 + "\t" + DATA_06 + "\t" + DATA_07 + "\t" + DATA_08 + "\t" + DATA_09 + "\t" + DATA_10 + "\t";
								returnValue += DATA_11 + "\t" + DATA_12 + "\t" + DATA_13 + "\t" + DATA_14 + "\t" + DATA_15 + "\t" + DATA_16 + "\t" + DATA_17 + "\t" + DATA_18 + "\t" + DATA_19 + "\t" + DATA_20 + "\t";
								returnValue += DATA_21 + "\t" + DATA_22 + "\t" + DATA_23 + "\t" + DATA_24 + "\t" + DATA_25 + "\t" + DATA_26 + "\r\n";
							} else if ("PUD".equalsIgnoreCase(GEN_TYPE)) {
								DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
								DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
								DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
								DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
								DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
								DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
								DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
								DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
								DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
								DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
								DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
								DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
								DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
								DATA_14 = rs.getString(14) == null ? "" : rs.getString(14);
								
								returnValue += DATA_01 + "\t" + DATA_02 + "\t" + DATA_03 + "\t" + DATA_04 + "\t" + DATA_05 + "\t" + DATA_06 + "\t" + DATA_07 + "\t" + DATA_08 + "\t" + DATA_09 + "\t" + DATA_10 + "\t";
								returnValue += DATA_11 + "\t" + DATA_12 + "\t" + DATA_13 + "\t" + DATA_14 + "\r\n";
							} else {
								DATA_01 = rs.getString(1) == null ? "" : rs.getString(1);
								DATA_02 = rs.getString(2) == null ? "" : rs.getString(2);
								DATA_03 = rs.getString(3) == null ? "" : rs.getString(3);
								DATA_04 = rs.getString(4) == null ? "" : rs.getString(4);
								DATA_05 = rs.getString(5) == null ? "" : rs.getString(5);
								DATA_06 = rs.getString(6) == null ? "" : rs.getString(6);
								DATA_07 = rs.getString(7) == null ? "" : rs.getString(7);
								DATA_08 = rs.getString(8) == null ? "" : rs.getString(8);
								DATA_09 = rs.getString(9) == null ? "" : rs.getString(9);
								DATA_10 = rs.getString(10) == null ? "" : rs.getString(10);
								DATA_11 = rs.getString(11) == null ? "" : rs.getString(11);
								DATA_12 = rs.getString(12) == null ? "" : rs.getString(12);
								DATA_13 = rs.getString(13) == null ? "" : rs.getString(13);
								
								returnValue += DATA_01 + "\t" + DATA_02 + "\t" + DATA_03 + "\t" + DATA_04 + "\t" + DATA_05 + "\t" + DATA_06 + "\t" + DATA_07 + "\t" + DATA_08 + "\t" + DATA_09 + "\t" + DATA_10 + "\t";
								returnValue += DATA_11 + "\t" + DATA_12 + "\t" + DATA_13 + "\r\n";
							}
						}
					}
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public void WriteFile(String FileName, String UserID, String Content) throws Exception {
		String FolderName = FileUploadProperty.getInstance().getIntegrasiJUPEMFolder();
		if (!FolderName.endsWith("/")) {
			FolderName = FolderName + "/";
		}
		if (!UserID.endsWith("/")) {
			UserID = UserID + "/";
		}
		File fd = new File(FolderName + UserID);
		File f = new File(FolderName + UserID + FileName);
		FileUtils.forceMkdir(fd);
		FileUtils.writeStringToFile(f, Content, "UTF-8");
	}
	
	public void DeleteFiles(String UserID) throws Exception {
		String FolderName = FileUploadProperty.getInstance().getIntegrasiJUPEMFolder();
		if (!FolderName.endsWith("/")) {
			FolderName = FolderName + "/";
		}
		if (!UserID.endsWith("/")) {
			UserID = UserID + "/";
		}
		File f = new File(FolderName + UserID);
		FileUtils.cleanDirectory(f);
	}

	public String getSOCNegeri(String SOC_NAME) throws Exception {
		return getSOCNegeri(SOC_NAME, "", false, "");
	}
	public String getSOCNegeri(String SOC_NAME, String SELECTED_NEGERI) throws Exception {
		return getSOCNegeri(SOC_NAME, SELECTED_NEGERI, false, "");
	}
	public String getSOCNegeri(String SOC_NAME, String SELECTED_NEGERI, Boolean DISABLED) throws Exception {
		return getSOCNegeri(SOC_NAME, SELECTED_NEGERI, DISABLED, "");
	}
	public String getSOCNegeri(String SOC_NAME, String SELECTED_NEGERI, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_NEGERI, KOD_NEGERI, NAMA_NEGERI " +
					"FROM TBLRUJNEGERI WHERE KOD_NEGERI <= 16 AND KOD_NEGERI != 00 " +
					"ORDER BY KOD_NEGERI, NAMA_NEGERI, ID_NEGERI";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_NEGERI)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}

	public String getSOCDaerah(String SOC_NAME) throws Exception {
		return getSOCDaerah(SOC_NAME, "", "", false, "");
	}
	public String getSOCDaerah(String SOC_NAME, String SELECTED_DAERAH) throws Exception {
		return getSOCDaerah(SOC_NAME, SELECTED_DAERAH, "", false, "");
	}
	public String getSOCDaerah(String SOC_NAME, String SELECTED_DAERAH, String SELECTED_IDNEGERI) throws Exception {
		return getSOCDaerah(SOC_NAME, SELECTED_DAERAH, SELECTED_IDNEGERI, false, "");
	}
	public String getSOCDaerah(String SOC_NAME, String SELECTED_DAERAH, String SELECTED_IDNEGERI, Boolean DISABLED) throws Exception {
		return getSOCDaerah(SOC_NAME, SELECTED_DAERAH, SELECTED_IDNEGERI, DISABLED, "");
	}
	public String getSOCDaerah(String SOC_NAME, String SELECTED_DAERAH, String SELECTED_IDNEGERI, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "", sql_where = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				if (!"".equalsIgnoreCase(SELECTED_IDNEGERI)) {
					sql_where = "WHERE ID_NEGERI = " + SELECTED_IDNEGERI + " ";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH " +
					"FROM TBLRUJDAERAH " + sql_where +
					"ORDER BY KOD_DAERAH, NAMA_DAERAH, ID_DAERAH";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_DAERAH)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCMukim(String SOC_NAME) throws Exception {
		return getSOCMukim(SOC_NAME, "", "", "", false, "");
	}
	public String getSOCMukim(String SOC_NAME, String SELECTED_MUKIM) throws Exception {
		return getSOCMukim(SOC_NAME, SELECTED_MUKIM, "", "", false, "");
	}
	public String getSOCMukim(String SOC_NAME, String SELECTED_MUKIM, String SELECTED_DAERAH) throws Exception {
		return getSOCMukim(SOC_NAME, SELECTED_MUKIM, SELECTED_DAERAH, "", false, "");
	}
	public String getSOCMukim(String SOC_NAME, String SELECTED_MUKIM, String SELECTED_DAERAH, String SELECTED_NEGERI) throws Exception {
		return getSOCMukim(SOC_NAME, SELECTED_MUKIM, SELECTED_DAERAH, SELECTED_NEGERI, false, "");
	}
	public String getSOCMukim(String SOC_NAME, String SELECTED_MUKIM, String SELECTED_DAERAH, String SELECTED_NEGERI, Boolean DISABLED) throws Exception {
		return getSOCMukim(SOC_NAME, SELECTED_MUKIM, SELECTED_DAERAH, SELECTED_NEGERI, DISABLED, "");
	}
	public String getSOCMukim(String SOC_NAME, String SELECTED_MUKIM, String SELECTED_DAERAH, String SELECTED_NEGERI, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "", sql_where = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				if (!"".equalsIgnoreCase(SELECTED_DAERAH) && !"".equalsIgnoreCase(SELECTED_NEGERI)) {
					sql_where = "AND DE.KOD_DAERAH = '" + SELECTED_DAERAH + "' AND NG.KOD_NEGERI = '" + SELECTED_NEGERI + "' ";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT MK.ID_MUKIM, MK.KOD_MUKIM, MK.NAMA_MUKIM " +
					"FROM TBLRUJMUKIM MK, TBLRUJDAERAH DE, TBLRUJNEGERI NG " + 
					"WHERE NG.ID_NEGERI = DE.ID_NEGERI AND DE.ID_DAERAH = MK.ID_DAERAH " + sql_where +
					"ORDER BY MK.KOD_MUKIM, MK.NAMA_MUKIM, MK.ID_MUKIM";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_MUKIM)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCMarkDesc(String SOC_NAME) throws Exception {
		return getSOCMarkDesc(SOC_NAME, "", false, "");
	}
	public String getSOCMarkDesc(String SOC_NAME, String SELECTED_MARKDESC) throws Exception {
		return getSOCMarkDesc(SOC_NAME, SELECTED_MARKDESC, false, "");
	}
	public String getSOCMarkDesc(String SOC_NAME, String SELECTED_MARKDESC, Boolean DISABLED) throws Exception {
		return getSOCMarkDesc(SOC_NAME, SELECTED_MARKDESC, DISABLED, "");
	}
	public String getSOCMarkDesc(String SOC_NAME, String SELECTED_MARKDESC, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_MARKDESC, KOD, KETERANGAN " +
					"FROM TBLINTRUJMARKDESC " +
					"ORDER BY KOD, KETERANGAN, ID_MARKDESC";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (ID.equalsIgnoreCase(SELECTED_MARKDESC)) {
							SOC += "<option value=\"" + ID + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + ID + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCLandTitle(String SOC_NAME) throws Exception {
		return getSOCLandTitle(SOC_NAME, "", false, "");
	}
	public String getSOCLandTitle(String SOC_NAME, String SELECTED_LANDTITLE) throws Exception {
		return getSOCLandTitle(SOC_NAME, SELECTED_LANDTITLE, false, "");
	}
	public String getSOCLandTitle(String SOC_NAME, String SELECTED_LANDTITLE, Boolean DISABLED) throws Exception {
		return getSOCLandTitle(SOC_NAME, SELECTED_LANDTITLE, DISABLED, "");
	}
	public String getSOCLandTitle(String SOC_NAME, String SELECTED_LANDTITLE, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_LANDTITLE, KOD, KETERANGAN " +
					"FROM TBLINTRUJLANDTITLE " +
					"ORDER BY KOD, KETERANGAN, ID_LANDTITLE";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_LANDTITLE)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCLandUse(String SOC_NAME) throws Exception {
		return getSOCLandUse(SOC_NAME, "", false, "");
	}
	public String getSOCLandUse(String SOC_NAME, String SELECTED_LANDUSE) throws Exception {
		return getSOCLandUse(SOC_NAME, SELECTED_LANDUSE, false, "");
	}
	public String getSOCLandUse(String SOC_NAME, String SELECTED_LANDUSE, Boolean DISABLED) throws Exception {
		return getSOCLandUse(SOC_NAME, SELECTED_LANDUSE, DISABLED, "");
	}
	public String getSOCLandUse(String SOC_NAME, String SELECTED_LANDUSE, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_LANDUSE, KOD, KETERANGAN " +
					"FROM TBLINTRUJLANDUSE " +
					"ORDER BY KOD, KETERANGAN, ID_LANDUSE";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_LANDUSE)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCClass(String SOC_NAME) throws Exception {
		return getSOCClass(SOC_NAME, "", false, "");
	}
	public String getSOCClass(String SOC_NAME, String SELECTED_CLASS) throws Exception {
		return getSOCClass(SOC_NAME, SELECTED_CLASS, false, "");
	}
	public String getSOCClass(String SOC_NAME, String SELECTED_CLASS, Boolean DISABLED) throws Exception {
		return getSOCClass(SOC_NAME, SELECTED_CLASS, DISABLED, "");
	}
	public String getSOCClass(String SOC_NAME, String SELECTED_CLASS, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_CLASS, KOD, KETERANGAN " +
					"FROM TBLINTRUJCLASS " +
					"ORDER BY KOD, KETERANGAN, ID_CLASS";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_CLASS)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCLineCode(String SOC_NAME) throws Exception {
		return getSOCLineCode(SOC_NAME, "", false, "");
	}
	public String getSOCLineCode(String SOC_NAME, String SELECTED_LINECODE) throws Exception {
		return getSOCLineCode(SOC_NAME, SELECTED_LINECODE, false, "");
	}
	public String getSOCLineCode(String SOC_NAME, String SELECTED_LINECODE, Boolean DISABLED) throws Exception {
		return getSOCLineCode(SOC_NAME, SELECTED_LINECODE, DISABLED, "");
	}
	public String getSOCLineCode(String SOC_NAME, String SELECTED_LINECODE, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_LINECODE, KOD, KETERANGAN " +
					"FROM TBLINTRUJLINECODE " +
					"ORDER BY KOD, KETERANGAN, ID_LINECODE";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_LINECODE)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
	
	public String getSOCLineType(String SOC_NAME) throws Exception {
		return getSOCLineType(SOC_NAME, "", false, "");
	}
	public String getSOCLineType(String SOC_NAME, String SELECTED_LINETYPE) throws Exception {
		return getSOCLineType(SOC_NAME, SELECTED_LINETYPE, false, "");
	}
	public String getSOCLineType(String SOC_NAME, String SELECTED_LINETYPE, Boolean DISABLED) throws Exception {
		return getSOCLineType(SOC_NAME, SELECTED_LINETYPE, DISABLED, "");
	}
	public String getSOCLineType(String SOC_NAME, String SELECTED_LINETYPE, Boolean DISABLED, String ONCHANGE) throws Exception {
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SOC_NAME)) {
				String sql = "";
				ResultSet rs = null;
				Statement stmt = db.getStatement();
				String ID = "", KOD = "", TEXT = "";
				
				if (DISABLED) {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" disabled=\"disabled\" " + ONCHANGE + ">\r\n";
				} else {
					SOC += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\"" + ONCHANGE + ">\r\n";
				}
				SOC += "<option value=\"\">SILA PILIH</option\r\n";
				sql = "SELECT ID_LINETYPE, KOD, KETERANGAN " +
					"FROM TBLINTRUJLINETYPE " +
					"ORDER BY KOD, KETERANGAN, ID_LINETYPE";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					KOD = rs.getString(2) == null ? "" : rs.getString(2);
					TEXT = rs.getString(3) == null ? "" : rs.getString(3);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (KOD.equalsIgnoreCase(SELECTED_LINETYPE)) {
							SOC += "<option value=\"" + KOD + "\" selected=\"selected\">" + KOD + " - " + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + KOD + "\">" + KOD + " - " + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null)
				db.close();
		}
		
		return SOC;
	}
}