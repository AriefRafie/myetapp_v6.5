package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmModelMaklumatKebangkrapan {
	
	private int STATUS_BARU = 1;
	private int STATUS_DALAM_PROSES = 2;
	private int STATUS_SELESAI = 3;
	
	static Logger myLogger = Logger.getLogger(FrmModelMaklumatKebangkrapan.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String generateText(Boolean ALL_DATA) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			String ID = "", NAMA_PB = "", NO_KP = "", STATUS_BANGKRAP = "", TARIKH_HUKUM = "", TARIKH_GULUNG = "", CAWANGAN = "", NO_ESTET = "", CATATAN = "";
			String WRITE_TEXT = "";
			returnValue = "'ID PB,NAMA PB,NO KP,STATUS KEBANGKRAPAN,TARIKH HUKUM,TARIKH GULUNG,CAWANGAN,NO ESTET,CATATAN\r\n";
			if (ALL_DATA) {
				sql = "SELECT M.ID_JIM, PB.NAMA_PB, CASE WHEN PB.NO_KP_LAMA <> '' THEN PB.NO_KP_LAMA ELSE PB.NO_PB END AS NO_KP, M.STATUS_KEBANGKRAPAN, " +
					"TO_CHAR(M.TARIKH_HUKUM, 'DD/MM/YYYY') AS TARIKH_HUKUM, TO_CHAR(M.TARIKH_GULUNG, 'DD/MM/YYYY') AS TARIKH_GULUNG, M.CAWANGAN, M.NO_ESTET, M.CATATAN " +
					"FROM TBLINTJIM M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
					"WHERE M.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN " +
					"AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN";
			} else {
				sql = "SELECT M.ID_JIM, PB.NAMA_PB, CASE WHEN PB.NO_KP_LAMA <> '' THEN PB.NO_KP_LAMA ELSE PB.NO_PB END AS NO_KP, M.STATUS_KEBANGKRAPAN, " +
					"TO_CHAR(M.TARIKH_HUKUM, 'DD/MM/YYYY') AS TARIKH_HUKUM, TO_CHAR(M.TARIKH_GULUNG, 'DD/MM/YYYY') AS TARIKH_GULUNG, M.CAWANGAN, M.NO_ESTET, M.CATATAN " +
					"FROM TBLINTJIM M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
					"WHERE M.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN " +
					"AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN " +
					"AND M.TEXT_GENERATED = 0";
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID = rs.getString(1) == null ? "" : rs.getString(1);
				NAMA_PB = rs.getString(2) == null ? "" : rs.getString(2);
				NO_KP = rs.getString(3) == null ? "" : rs.getString(3);
				STATUS_BANGKRAP = rs.getString(4) == null ? "" : rs.getString(4);
				TARIKH_HUKUM = rs.getString(5) == null ? "" : rs.getString(5);
				TARIKH_GULUNG = rs.getString(6) == null ? "" : rs.getString(6);
				CAWANGAN = rs.getString(7) == null ? "" : rs.getString(7);
				NO_ESTET = rs.getString(8) == null ? "" : rs.getString(8);
				CATATAN = rs.getString(9) == null ? "" : rs.getString(9);
				if (!"".equalsIgnoreCase(NO_KP)) {
					WRITE_TEXT = ID + "," + NAMA_PB + "," + NO_KP + "," + STATUS_BANGKRAP + "," + TARIKH_HUKUM + "," + TARIKH_GULUNG + "," + CAWANGAN + "," + NO_ESTET + "," + CATATAN;
					returnValue += WRITE_TEXT + "\r\n";
				}
				
				sql = "UPDATE TBLINTJIM SET TEXT_GENERATED = 1 WHERE ID_JIM = " + ID;
				FrmModelUtilitiesIntegration.execSQL(sql);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchMaklumatKebangkrapan(String NO_FAIL, String NO_PERMOHONAN, String NAMA_PEMOHON, String NOKP_PEMOHON) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FA.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(PM.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_PEMOHON)) {
				SQL_SEARCH = " AND UPPER(PB.NAMA_PB) LIKE '%" + NAMA_PEMOHON.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_PEMOHON)) {
				SQL_SEARCH = " AND UPPER(PB.NO_PB) LIKE '%" + NOKP_PEMOHON.toUpperCase() + "%'";
			}
			sql = "SELECT PM.ID_PERMOHONAN, PB.ID_PIHAKBERKEPENTINGAN, FA.NO_FAIL, PM.NO_PERMOHONAN, PB.NAMA_PB, PB.NO_PB, PB.ALAMAT1, PB.ALAMAT2, PB.ALAMAT3 " +
				"FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
				"WHERE PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN" +
				SQL_SEARCH + " ORDER BY FA.NO_FAIL, PB.NAMA_PB";
			int i = 1;
			String ID_PERMOHONAN = "", ID_PB = "", ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", ALAMAT = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_PB = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NO_PERMOHONAN = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
				NOKP_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT1 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT2 = rs.getString(8) == null ? "" : rs.getString(8);
				ALAMAT3 = rs.getString(9) == null ? "" : rs.getString(9);
				
				if (!"".equalsIgnoreCase(ALAMAT1)) {
					ALAMAT = ALAMAT1;
				}
				if (!"".equalsIgnoreCase(ALAMAT2)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT2;
					} else {
						ALAMAT = ALAMAT2;
					}
				}
				if (!"".equalsIgnoreCase(ALAMAT3)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT3;
					} else {
						ALAMAT = ALAMAT3;
					}
				}
				
				h = new Hashtable();
				h.put("No", i);
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("ID_PB", ID_PB);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("NoKPPemohon", NOKP_PEMOHON);
				h.put("Alamat", ALAMAT);
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
	public Vector viewMaklumatKebangkrapanMaklumat(String ID_PIHAKBERKEPENTINGAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				String NAMA_PEMOHON = "", NO_KP_PEMOHON = "", ALAMAT_PEMOHON = "";
				String STATUS_BANGKRAP = "", CATATAN = "", TARIKH_GULUNG = "", TARIKH_HUKUM = "", JENIS_NO_PB = "", TARIKH_SEBUTAN = "", CAWANGAN = "", NO_ESTET = "";
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				
				// if no data in TBLINT, get data from TBLPPT
				sql = "SELECT UPPER(PB.NAMA_PB), PB.NO_PB, UPPER(PB.ALAMAT1) || ', ' || UPPER(PB.ALAMAT2) || ', ' || UPPER(PB.ALAMAT3), M.STATUS_KEBANGKRAPAN, " +
					"JNP.KOD_JENIS_NOPB, M.TARIKH_HUKUM, M.TARIKH_GULUNG, M.CATATAN, M.TARIKH_SEBUTAN, M.CAWANGAN, M.NO_ESTET " +
					"FROM TBLINTJIM M, TBLPPTPIHAKBERKEPENTINGAN PB, TBLRUJJENISNOPB JNP " +
					"WHERE M.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND PB.ID_JENISNOPB = JNP.ID_JENISNOPB(+) " +
					"AND M.ID_PIHAKBERKEPENTINGAN = " + ID_PIHAKBERKEPENTINGAN;
				myLogger.info("::::: viewMaklumatKebangkrapanMaklumat :"+sql);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					NAMA_PEMOHON = rs.getString(1) == null ? "" : rs.getString(1);
					NO_KP_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT_PEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
					STATUS_BANGKRAP = rs.getString(4) == null ? "" : rs.getString(4);
					JENIS_NO_PB = rs.getString(5) == null ? "" : rs.getString(5);
					TARIKH_HUKUM = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
					TARIKH_GULUNG = rs.getDate(7) == null ? "" : sdf.format(rs.getDate(7));
					CATATAN = rs.getString(8) == null ? "" : rs.getString(8);
					TARIKH_SEBUTAN = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
					CAWANGAN = rs.getString(10) == null ? "" : rs.getString(10);
					NO_ESTET = rs.getString(11) == null ? "" : rs.getString(11);
				}
				
				if (!haveINTData) {
					sql = "SELECT UPPER(PB.NAMA_PB), PB.NO_PB, UPPER(PB.ALAMAT1) || ', ' || UPPER(PB.ALAMAT2) || ', ' || UPPER(PB.ALAMAT3), '', JNP.KOD_JENIS_NOPB, '', '', '', '', '', '' " +
						"FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLRUJJENISNOPB JNP " +
						"WHERE PB.ID_JENISNOPB = JNP.ID_JENISNOPB(+) " +
						"AND PB.ID_PIHAKBERKEPENTINGAN = " + ID_PIHAKBERKEPENTINGAN;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NAMA_PEMOHON = rs.getString(1) == null ? "" : rs.getString(1);
						NO_KP_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
						ALAMAT_PEMOHON = rs.getString(3) == null ? "" : rs.getString(3);
						STATUS_BANGKRAP = rs.getString(4) == null ? "" : rs.getString(4);
						JENIS_NO_PB = rs.getString(5) == null ? "" : rs.getString(5);
						TARIKH_HUKUM = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
						TARIKH_GULUNG = rs.getDate(7) == null ? "" : sdf.format(rs.getDate(7));
						CATATAN = rs.getString(8) == null ? "" : rs.getString(8);
						TARIKH_SEBUTAN = rs.getDate(9) == null ? "" : sdf.format(rs.getDate(9));
						CAWANGAN = rs.getString(10) == null ? "" : rs.getString(10);
						NO_ESTET = rs.getString(11) == null ? "" : rs.getString(11);
					}
				}
				h = new Hashtable();
				h.put("haveINTData", haveINTData);
				h.put("MP_NAMAPEMOHON", NAMA_PEMOHON);
				h.put("MP_NOKPPEMOHON", NO_KP_PEMOHON);
				h.put("MP_ALAMATPEMOHON", ALAMAT_PEMOHON);
				h.put("MP_STATUSKEBANGKRAPAN", STATUS_BANGKRAP);
				h.put("MP_JENISNOPB", JENIS_NO_PB);
				h.put("MP_TARIKHHUKUM", TARIKH_HUKUM);
				h.put("MP_TARIKHGULUNG", TARIKH_GULUNG);
				h.put("MP_TARIKHSEBUTAN", TARIKH_SEBUTAN);
				h.put("MP_CATATAN", CATATAN);
				h.put("MP_CAWANGAN", CAWANGAN);
				h.put("MP_NOESTET", NO_ESTET);
				v.add(h);					
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector viewMaklumatHTA(String ID_PIHAKBERKEPENTINGAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				int i = 0;
				String NO_LOT = "", NO_HAKMILIK = "", JENIS_HAKMILIK = "", NEGERI = "", DAERAH = "", MUKIM = "", LUAS_LOT = "", UNIT_LUASLOT = "", LUAS_AMBIL = "", UNIT_LUASAMBIL = "", BA = "", BB = "";
				
				sql = "SELECT HM.NO_LOT, HM.NO_HAKMILIK, JHM.KETERANGAN, NG.NAMA_NEGERI, DE.NAMA_DAERAH, MK.NAMA_MUKIM, HM.LUAS_LOT, LUAS_LOT.KETERANGAN, HM.LUAS_AMBIL, LUAS_AMBIL.KETERANGAN, HPB.SYER_ATAS, HPB.SYER_BAWAH " +
					"FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HPB, TBLPPTHAKMILIK HM, TBLRUJJENISHAKMILIK JHM, TBLRUJNEGERI NG, TBLRUJDAERAH DE, TBLRUJMUKIM MK, TBLRUJLUAS LUAS_LOT, TBLRUJLUAS LUAS_AMBIL " +
					"WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN AND HPB.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK AND HM.ID_NEGERI = NG.ID_NEGERI AND HM.ID_DAERAH = DE.ID_DAERAH AND HM.ID_MUKIM = MK.ID_MUKIM AND HM.ID_UNITLUASLOT = LUAS_LOT.ID_LUAS(+) AND HM.ID_UNITLUASAMBIL = LUAS_AMBIL.ID_LUAS(+) " +
					"AND PB.ID_PIHAKBERKEPENTINGAN = " + ID_PIHAKBERKEPENTINGAN;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NO_LOT = rs.getString(1) == null ? "" : rs.getString(1);
					NO_HAKMILIK = rs.getString(2) == null ? "" : rs.getString(2);
					JENIS_HAKMILIK = rs.getString(3) == null ? "" : rs.getString(3);
					NEGERI = rs.getString(4) == null ? "" : rs.getString(4);
					DAERAH = rs.getString(5) == null ? "" : rs.getString(5);
					MUKIM = rs.getString(6) == null ? "" : rs.getString(6);
					LUAS_LOT = rs.getString(7) == null ? "" : rs.getString(7);
					UNIT_LUASLOT = rs.getString(8) == null ? "" : rs.getString(8);
					LUAS_AMBIL = rs.getString(9) == null ? "" : rs.getString(9);
					UNIT_LUASAMBIL = rs.getString(10) == null ? "" : rs.getString(10);
					BA = rs.getString(11) == null ? "" : rs.getString(11);
					BB = rs.getString(12) == null ? "" : rs.getString(12);
					if  (!"".equalsIgnoreCase(UNIT_LUASLOT)) {
						LUAS_LOT += " " + UNIT_LUASLOT;
					}
					if  (!"".equalsIgnoreCase(UNIT_LUASAMBIL)) {
						LUAS_AMBIL += " " + UNIT_LUASAMBIL;
					}
					if  ("".equalsIgnoreCase(BA)) {
						BA = "0";
					}
					if  ("".equalsIgnoreCase(BB)) {
						BB = "0";
					}
					
					i++;
					h = new Hashtable();
					h.put("No", i);
					h.put("NoLot", NO_LOT);
					h.put("NoHakmilik", NO_HAKMILIK);
					h.put("JenisHakmilik", JENIS_HAKMILIK);
					h.put("Negeri", NEGERI);
					h.put("Daerah", DAERAH);
					h.put("Mukim", MUKIM);
					h.put("LuasAsal", LUAS_LOT);
					h.put("LuasDiambil", LUAS_AMBIL);
					h.put("BA", BA);
					h.put("BB", BB);
					v.add(h);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	@SuppressWarnings("rawtypes")
	public Boolean saveMaklumatKebangkrapan(String ID_PIHAKBERKEPENTINGAN, String ID_PERMOHONAN, String USER_ID, Boolean isJIMUser, Hashtable h) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				long ID_JIM = 0;
				int STATUS_PROSES = 1;
				String STATUS_KEBANGKRAPAN = "", CATATAN = "", TARIKH_HUKUM = "", TARIKH_GULUNG = "", TARIKH_SEBUTAN = "", CAWANGAN = "", NO_ESTET = "";
				
				STATUS_KEBANGKRAPAN = (String) h.get("MP_STATUSKEBANGKRAPAN");
				TARIKH_HUKUM = (String) h.get("MP_TARIKHHUKUM");
				TARIKH_GULUNG = (String) h.get("MP_TARIKHGULUNG");
				CATATAN = (String) h.get("MP_CATATAN");
				TARIKH_SEBUTAN = (String) h.get("MP_TARIKHSEBUTAN");
				CAWANGAN = (String) h.get("MP_CAWANGAN");
				NO_ESTET = (String) h.get("MP_NOESTET");
				
				// check in TBLINTJIM
				r.add("ID_PIHAKBERKEPENTINGAN");
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
				sql = r.getSQLSelect("TBLINTJIM");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				
				if (isJIMUser) {
					STATUS_PROSES = STATUS_DALAM_PROSES;
				} else {
					STATUS_PROSES = STATUS_BARU;
				}
				
				if (haveData) {
					r.update("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
				} else {
					r.add("ID_SIMPAN", USER_ID);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				}
				r.add("ID_KEMASKINI", USER_ID);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("STATUS_PROSES", STATUS_PROSES);
				if (!isJIMUser) {
					r.add("ID_PERMOHONAN", ID_PERMOHONAN);
					r.add("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
				} else {
					r.add("STATUS_KEBANGKRAPAN", STATUS_KEBANGKRAPAN);
					r.add("TARIKH_HUKUM", r.unquote("TO_DATE('" + TARIKH_HUKUM + "', 'dd/MM/yyyy')"));
					r.add("TARIKH_GULUNG", r.unquote("TO_DATE('" + TARIKH_GULUNG + "', 'dd/MM/yyyy')"));
					r.add("TARIKH_SEBUTAN", r.unquote("TO_DATE('" + TARIKH_SEBUTAN + "', 'dd/MM/yyyy')"));
					r.add("CAWANGAN", CAWANGAN);
					r.add("NO_ESTET", NO_ESTET);
					r.add("CATATAN", CATATAN);
				}
				if ("".equalsIgnoreCase(TARIKH_GULUNG)) {
					r.add("JENIS_NO_PB", 1);
				} else {
					r.add("JENIS_NO_PB", 0);
				}
				if (haveData) {
					sql = r.getSQLUpdate("TBLINTJIM");
				} else {
					ID_JIM = DB.getNextID("TBLINTJIM_SEQ");
					r.add("ID_JIM", ID_JIM);
					sql = r.getSQLInsert("TBLINTJIM");
				}
				returnValue = true;
				myLogger.info("::::::::::::::::: saveMaklumatKebangkrapan :"+sql);
				stmt.execute(sql);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean sendMaklumatKebangkrapan(String ID_PIHAKBERKEPENTINGAN, String ID_PERMOHONAN, Boolean isJIMUser) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				// check in TBLINTJIM
				r.add("ID_PIHAKBERKEPENTINGAN");
				r.add("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJIM");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					if (isJIMUser) {
						r.add("STATUS_PROSES", STATUS_SELESAI);
					} else {
						r.add("STATUS_PROSES", STATUS_BARU);
					}
					r.update("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
					sql = r.getSQLUpdate("TBLINTJIM");
					returnValue = true;
					stmt.execute(sql);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteMaklumatKebangkrapan(String ID_PIHAKBERKEPENTINGAN, String ID_PERMOHONAN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				r.add("ID_JIM");
				r.add("ID_PIHAKBERKEPENTINGAN", ID_PIHAKBERKEPENTINGAN);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect("TBLINTJIM");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String ID_JIM = "";
					ID_JIM = rs.getString(1) == null ? "" : rs.getString(1);
					r.add("ID_JIM", ID_JIM);
					sql = r.getSQLDelete("TBLINTJIM");
					stmt.execute(sql);
					returnValue = true;
				}
			}
		} finally {
			if (db != null) 
				db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<Object> viewSenaraiPBFail(String NO_FAIL) throws Exception {
		Vector<Object> v = new Vector<Object>();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				int i = 1;
				
				String ID_PERMOHONAN = "", NO_HAKMILIK = "", NO_LOT = "", NO_PT = "", MUKIM = "", JUMLAH_PB = "", NO_LOTPT = "", ID_HAKMILIK = "";
				sql = "SELECT M.ID_PERMOHONAN, FA.NO_FAIL, HM.NO_HAKMILIK, HM.NO_LOT, HM.NO_PT, MK.NAMA_MUKIM, COUNT(PB.ID_PIHAKBERKEPENTINGAN) AS JUMLAH_PB, HM.ID_HAKMILIK " +
					"FROM TBLPPTPERMOHONAN M, TBLPPTHAKMILIK HM, TBLPPTHAKMILIKPB HMPB, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPFDFAIL FA, TBLRUJMUKIM MK " +
					"WHERE M.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HMPB.ID_HAKMILIK AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN " +
					"AND M.ID_FAIL = FA.ID_FAIL AND HM.ID_MUKIM = MK.ID_MUKIM AND FA.NO_FAIL LIKE '%" + NO_FAIL + "%' " +
					"GROUP BY M.ID_PERMOHONAN, FA.NO_FAIL, HM.NO_HAKMILIK, HM.NO_LOT, HM.NO_PT, MK.NAMA_MUKIM, HM.ID_HAKMILIK";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					h = new Hashtable<String, String>();
					ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					NO_HAKMILIK = rs.getString(3) == null ? "" : rs.getString(3);
					NO_LOT = rs.getString(4) == null ? "" : rs.getString(4);
					NO_PT = rs.getString(5) == null ? "" : rs.getString(5);
					MUKIM = rs.getString(6) == null ? "" : rs.getString(6);
					JUMLAH_PB = rs.getString(7) == null ? "" : rs.getString(7);
					ID_HAKMILIK = rs.getString(8) == null ? "" : rs.getString(8);
					if (!"".equalsIgnoreCase(NO_PT.trim())) {
						if (!"".equalsIgnoreCase(NO_LOT.trim())) {
							NO_LOTPT = NO_LOT + "/PT " + NO_PT;
						} else {
							NO_LOTPT = "PT " + NO_PT;
						}
					}
					h.put("NO", i);
					h.put("ID_PERMOHONAN", ID_PERMOHONAN);
					h.put("NO_FAIL", NO_FAIL);
					h.put("NO_HAKMILIK", NO_HAKMILIK);
					h.put("NO_LOTPT", NO_LOTPT);
					h.put("MUKIM", MUKIM);
					h.put("JUMLAH_PB", JUMLAH_PB);
					h.put("ID_HAKMILIK", ID_HAKMILIK);
					v.add(h);
					i++;
				}
			}
		} finally {
			if (db != null) 
				db.close();			
		}
		
		return v;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<Object> viewSenaraiPB(String ID_PERMOHONAN, String ID_HAKMILIK) throws Exception {
		Vector<Object> v = new Vector<Object>();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				int i = 1;
				
				String NAMA_PB = "", JENIS_PB = "", BAHAGIAN = "", ID_PB = "", STATUS_BANGKRAP = "", ID_JIM = "";
				sql = "SELECT PB.NAMA_PB, JPB.KETERANGAN, PB.SYER_ATAS || '/' || PB.SYER_BAWAH AS BAHAGIAN, PB.ID_PIHAKBERKEPENTINGAN, JIM.STATUS_KEBANGKRAPAN, JIM.ID_JIM " +
					"FROM TBLPPTPERMOHONAN M, TBLPPTHAKMILIK HM, TBLPPTHAKMILIKPB HMPB, TBLPPTPIHAKBERKEPENTINGAN PB, TBLRUJJENISPB JPB, TBLINTJIM JIM " +
					"WHERE M.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HMPB.ID_HAKMILIK AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN " +
					"AND PB.ID_JENISPB = JPB.ID_JENISPB(+) AND PB.ID_PIHAKBERKEPENTINGAN = JIM.ID_PIHAKBERKEPENTINGAN(+) " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND HM.ID_HAKMILIK = " + ID_HAKMILIK;
				
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					NAMA_PB = rs.getString(1) == null ? "" : rs.getString(1);
					JENIS_PB = rs.getString(2) == null ? "" : rs.getString(2);
					BAHAGIAN = rs.getString(3) == null ? "" : rs.getString(3);
					ID_PB = rs.getString(4) == null ? "" : rs.getString(4);
					STATUS_BANGKRAP = rs.getString(5) == null ? "" : rs.getString(5);
					ID_JIM = rs.getString(6) == null ? "" : rs.getString(6);
					if ("/".equalsIgnoreCase(BAHAGIAN.trim())) {
						BAHAGIAN = "";
					}
					if ("1".equalsIgnoreCase(STATUS_BANGKRAP.trim())) {
						STATUS_BANGKRAP = "Ya";
					} else if ("0".equalsIgnoreCase(STATUS_BANGKRAP.trim())) {
						STATUS_BANGKRAP = "Tidak";
					} else {
						STATUS_BANGKRAP = "Tiada Rekod";
					}
					if ("".equalsIgnoreCase(ID_JIM.trim())) {
						h = new Hashtable<String, String>();
						h.put("Bil", i);
						h.put("NamaPB", NAMA_PB);
						h.put("JenisPB", JENIS_PB);
						h.put("Bahagian", BAHAGIAN);
						h.put("ID_PB", ID_PB);
						h.put("StatusBangkrap", STATUS_BANGKRAP);
						v.add(h);
						i++;
					}
				}
			}
		} finally {
			if (db != null) 
				db.close();			
		}
		
		return v;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<Object> viewSenaraiPBMaklumatFail(String ID_PERMOHONAN) throws Exception {
		Vector<Object> v = new Vector<Object>();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				String NO_FAIL = "", NO_RUJUKAN_PTG = "", NO_RUJUKAN_PTD = "", NAMA_KEMENTERIAN = "", NAMA_AGENSI = "", NEGERI = "", DAERAH = "", TUJUAN = "";
				sql = "SELECT FA.NO_FAIL, M.NO_RUJUKAN_PTG, M.NO_RUJUKAN_PTD, K.NAMA_KEMENTERIAN, AG.NAMA_AGENSI, NG.NAMA_NEGERI, DA.NAMA_DAERAH, M.TUJUAN " +
					"FROM TBLPPTPERMOHONAN M, TBLPFDFAIL FA, TBLRUJKEMENTERIAN K, TBLRUJAGENSI AG, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
					"WHERE M.ID_FAIL = FA.ID_FAIL AND FA.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) AND M.ID_AGENSI = AG.ID_AGENSI(+) AND M.ID_NEGERI = NG.ID_NEGERI(+) " +
					"AND M.ID_DAERAH = DA.ID_DAERAH(+) " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					h = new Hashtable<String, String>();
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_RUJUKAN_PTG = rs.getString(2) == null ? "" : rs.getString(2);
					NO_RUJUKAN_PTD = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_KEMENTERIAN = rs.getString(4) == null ? "" : rs.getString(4);
					NAMA_AGENSI = rs.getString(5) == null ? "" : rs.getString(5);
					NEGERI = rs.getString(6) == null ? "" : rs.getString(6);
					DAERAH = rs.getString(7) == null ? "" : rs.getString(7);
					TUJUAN = rs.getString(8) == null ? "" : rs.getString(8);
					h.put("NO_FAIL", NO_FAIL);
					h.put("NO_RUJUKAN_PTG", NO_RUJUKAN_PTG);
					h.put("NO_RUJUKAN_PTD", NO_RUJUKAN_PTD);
					h.put("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					h.put("NAMA_AGENSI", NAMA_AGENSI);
					h.put("NEGERI", NEGERI);
					h.put("DAERAH", DAERAH);
					h.put("TUJUAN", TUJUAN);
					v.add(h);
				}
			}
		} finally {
			if (db != null) 
				db.close();			
		}
		
		return v;
	}
	
	public void addPBDataList(String ID_PERMOHONAN, String[] ListPB, String ID_USER) throws Exception {
		Db db = new Db();
		Db db2 = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && ListPB.length > 0) {
				Statement stmt = db.getStatement();
				Statement stmt2 = db2.getStatement();
				ResultSet rs = null;
				String sql = "";
				long ID_JIM = 0;
				
				for (int i = 0; i < ListPB.length; i++) {
					sql = "SELECT ID_JIM FROM TBLINTJIM WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_PIHAKBERKEPENTINGAN = " + ListPB[i];
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						sql = "UPDATE TBLINTJIM SET ID_KEMASKINI = " + ID_USER + ", TARIKH_KEMASKINI = SYSDATE, STATUS_PROSES = 1, " +
							"STATUS_KEBANGKRAPAN = NULL WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_PIHAKBERKEPENTINGAN = " + ListPB[i];
					} else {
						ID_JIM = DB.getNextID("TBLINTJIM_SEQ");
						sql = "INSERT INTO TBLINTJIM (ID_JIM, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, STATUS_PROSES, ID_PERMOHONAN, " +
							"ID_PIHAKBERKEPENTINGAN) VALUES (" + ID_JIM + ", " + ID_USER + ", SYSDATE, " + ID_USER + ", SYSDATE, 1, " + ID_PERMOHONAN + 
							", " + ListPB[i] + ")";
					}
					
					stmt2.execute(sql);
					rs.close();
				}
			}
		} finally {
			if (db != null) 
				db.close();			
		}
	}
}