package ekptg.model.pfd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.intergration.XEkptgEmailSender;

public class FrmModelMesyuaratSenarai {

	private static Vector paparSeksyen = null;
	private static Vector senaraiPegawai = new Vector();
	
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	/*public Boolean havePengerusi(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT)){
	//			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_AHLIMESYUARAT = " + ID_AHLI + " AND ID_MESYUARAT = " + ID_MESYUARAT;
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}*/
	
	

	public Boolean haveSetiausaha(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PERANAN = 3 AND ID_AHLIMESYUARAT <> " + ID_AHLI + "  AND ID_MESYUARAT = " + ID_MESYUARAT;
				System.out.println("SQL SET:"+sql.toUpperCase());
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && "".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PERANAN = 3  AND ID_MESYUARAT = " + ID_MESYUARAT;
				System.out.println("SQL SET:"+sql.toUpperCase());
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	
	
	
	public Boolean havePengerusi(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)){
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PERANAN = 1 AND ID_AHLIMESYUARAT <> " + ID_AHLI + " AND ID_MESYUARAT = " + ID_MESYUARAT;
	//			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && "".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PERANAN = 1  AND ID_MESYUARAT = " + ID_MESYUARAT;
		//			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_MESYUARAT = " + ID_MESYUARAT;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						returnValue = true;
					}
				}
			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	
	public void updateStatusProses(String ID_MESYUARAT, String STATUS_PROSES) throws Exception {
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(STATUS_PROSES)) {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARAT", ID_MESYUARAT);
				r.add("STATUS_PROSES", STATUS_PROSES);
				String sql = r.getSQLUpdate("TBLPFDMESYUARAT") + " AND STATUS_PROSES != 5";
				stmt.execute(sql);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusProses(String ID_MESYUARAT) throws Exception {
		Db db = new Db();
		String returnValue = "1";
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				ResultSet rs = null;
				
				r.add("ID_MESYUARAT", ID_MESYUARAT);
				r.add("STATUS_PROSES");
				String sql = r.getSQLSelect("TBLPFDMESYUARAT");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	private static void populateRunningNumber(String TYPE, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		
		String TABLE_NAME, FIELD_DETAILS, FIELD_ID;
		if ("1".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATAGENDA";
			FIELD_DETAILS = "ID_MESYUARAT";
			FIELD_ID = "ID_AGENDAMESYUARAT";
		} else if ("2".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATMINIT";
			FIELD_DETAILS = "ID_AGENDAMESYUARAT";
			FIELD_ID = "ID_MINITMESYUARAT";
		} else {
			TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
			FIELD_DETAILS = "ID_MINITMESYUARAT";
			FIELD_ID = "ID_SUBMINITMESYUARAT";
		}
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			int iCount = 1, ID = 0;
			sql = "SELECT " + FIELD_ID + " FROM " + TABLE_NAME + " WHERE " + FIELD_DETAILS + "=" + ID_DETAILS + " ORDER BY " + FIELD_ID;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID = rs.getInt(1);
				if (ID > 0) {
					updateRunningNumber(TYPE, ID, iCount);
				}
				iCount++;
			}
			rs.close();
			stmt.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	private static void updateRunningNumber(String TYPE, int ID_DETAILS, int RunningNumber) throws DbException, Exception {
		String sql = "";
		Db db = new Db();

		String TABLE_NAME, FIELD_NUMBER, FIELD_ID;
		if ("1".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATAGENDA";
			FIELD_NUMBER = "NO_AGENDA";
			FIELD_ID = "ID_AGENDAMESYUARAT";
		} else if ("2".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATMINIT";
			FIELD_NUMBER = "NO_MINIT";
			FIELD_ID = "ID_MINITMESYUARAT";
		} else {
			TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
			FIELD_NUMBER = "NO_SUBMINIT";
			FIELD_ID = "ID_SUBMINITMESYUARAT";
		}
		
		try {
			Statement stmt = db.getStatement();
			sql = "UPDATE " + TABLE_NAME + " SET " + FIELD_NUMBER + "=" + RunningNumber + " WHERE " + FIELD_ID + "=" + ID_DETAILS;
			stmt.execute(sql);
			stmt.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
	
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("-?\\d+(.\\d+)?");
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMesyuaratData(String ID_MESYUARAT) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector vData = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = new Hashtable();

		        String Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "";
		        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Seksyen = "", Mesyuarat_Unit = "", Mesyuarat_NoFailDiperlukan = "", Mesyuarat_DisediakanOleh = "";
		        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "", Mesyuarat_NoFail = "";
		        String Mesyuarat_ValueLokasi = "", Mesyuarat_ValueSeksyen = "", Mesyuarat_ValueUnit = "", Mesyuarat_ValueStatus = "", Mesyuarat_ValueDari = "", Mesyuarat_ValueHingga = "";
		        String Mesyuarat_TempahMakananPemohon = "", Mesyuarat_TempahMakananBilAhli = "", Mesyuarat_TempahMakananMakanan = "", Mesyuarat_TempahMakananMinuman = "";
		        
				sql = "SELECT M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, M.ID_LOKASI, M.ID_STATUS, " +
					"M.ID_SEKSYEN, M.NO_FAIL_DIPERLUKAN, M.DISEDIAKAN_OLEH, M.DISEMAK_OLEH, M.DISAHKAN_OLEH, M.CATATAN, " +
					"TO_CHAR(CASE WHEN L.ID_LOKASI != '16102' THEN L.LOKASI WHEN L.ID_LOKASI = '16102' THEN M.CATATAN ELSE '' END) AS LOKASI, " +
					"SK.NAMA_SEKSYEN, ST.STATUS_MESYUARAT, FAIL.NO_FAIL, " +
					"M.TEMPAHMAKANAN_PEMOHON, M.TEMPAHMAKANAN_BILAHLI, M.TEMPAHMAKANAN_MAKANAN, M.TEMPAHMAKANAN_MINUMAN, M.ID_UNIT, UN.NAMA_UNIT " +
					"FROM TBLPFDMESYUARAT M, TBLPFDRUJLOKASIMESYUARAT L, TBLPFDRUJSTATUSMESYUARAT ST, TBLRUJSEKSYEN SK, TBLPFDFAIL FAIL, TBLRUJUNIT UN " + 
					"WHERE M.ID_LOKASI = L.ID_LOKASI(+) AND M.ID_STATUS = ST.ID_STATUS(+) AND M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_UNIT = UN.ID_UNIT(+) AND M.ID_FAIL = FAIL.ID_FAIL " + 
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Mesyuarat_Bil = rs.getString(1) == null ? "" : rs.getString(1);
					Mesyuarat_Tajuk = rs.getString(2) == null ? "" : rs.getString(2);
					Mesyuarat_Tarikh = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
					Mesyuarat_Dari = rs.getString(4) == null ? "" : rs.getString(4);
					Mesyuarat_Hingga = rs.getString(5) == null ? "" : rs.getString(5);
					Mesyuarat_Lokasi = rs.getString(6) == null ? "" : rs.getString(6);
					Mesyuarat_Status = rs.getString(7) == null ? "" : rs.getString(7);
					Mesyuarat_Seksyen = rs.getString(8) == null ? "" : rs.getString(8);
					Mesyuarat_NoFailDiperlukan = rs.getString(9) == null ? "" : rs.getString(9);
					Mesyuarat_DisediakanOleh = rs.getString(10) == null ? "" : rs.getString(10);
					Mesyuarat_DisemakOleh = rs.getString(11) == null ? "" : rs.getString(11);
					Mesyuarat_DisahkanOleh = rs.getString(12) == null ? "" : rs.getString(12);
					Mesyuarat_Catatan = rs.getString(13) == null ? "" : rs.getString(13);
					Mesyuarat_ValueLokasi = rs.getString(14) == null ? "" : rs.getString(14);
					Mesyuarat_ValueSeksyen = rs.getString(15) == null ? "" : rs.getString(15);
					Mesyuarat_ValueStatus = rs.getString(16) == null ? "" : rs.getString(16);
					Mesyuarat_NoFail = rs.getString(17) == null ? "" : rs.getString(17);
					Mesyuarat_TempahMakananPemohon = rs.getString(18) == null ? "" : rs.getString(18);
					Mesyuarat_TempahMakananBilAhli = rs.getString(19) == null ? "" : rs.getString(19);
					Mesyuarat_TempahMakananMakanan = rs.getString(20) == null ? "" : rs.getString(20);
					Mesyuarat_TempahMakananMinuman = rs.getString(21) == null ? "" : rs.getString(21);
					Mesyuarat_Unit = rs.getString(22) == null ? "" : rs.getString(22);
					Mesyuarat_ValueUnit = rs.getString(23) == null ? "" : rs.getString(23);

					String WAKTU_MESYUARAT_DARI = "";
					String WAKTU_MESYUARAT_HINGGA = "";
					
					if (Mesyuarat_Dari.length() < 4) {
						Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
					}
					if (Mesyuarat_Hingga.length() < 4) {
						Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
					}
					if (!isNumeric(Mesyuarat_Dari)) {
						Mesyuarat_Dari = "1000";
					}
					if (!isNumeric(Mesyuarat_Hingga)) {
						Mesyuarat_Hingga = "1100";
					}
					WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
					WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
					if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
						WAKTU_MESYUARAT_DARI = " AM";
					} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
						WAKTU_MESYUARAT_DARI = " PM";
					} else {
						WAKTU_MESYUARAT_DARI = " PM";
					}
					if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
						WAKTU_MESYUARAT_HINGGA = " AM";
					} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
						WAKTU_MESYUARAT_HINGGA = " PM";
					} else {
						WAKTU_MESYUARAT_HINGGA = " PM";
					}
					Mesyuarat_ValueDari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
					Mesyuarat_ValueHingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;

					h.put("Mesyuarat_Bil", Mesyuarat_Bil);
					h.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
					h.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
					h.put("Mesyuarat_Dari", Mesyuarat_Dari);
					h.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
					h.put("Mesyuarat_Lokasi", Mesyuarat_Lokasi);
					h.put("Mesyuarat_Status", Mesyuarat_Status);
					h.put("Mesyuarat_Seksyen", Mesyuarat_Seksyen);
					h.put("Mesyuarat_NoFailDiperlukan", Mesyuarat_NoFailDiperlukan);
					h.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
					h.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
					h.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
					h.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
					h.put("Mesyuarat_ValueLokasi", Mesyuarat_ValueLokasi);
					h.put("Mesyuarat_ValueSeksyen", Mesyuarat_ValueSeksyen);
					h.put("Mesyuarat_ValueStatus", Mesyuarat_ValueStatus);
					h.put("Mesyuarat_ValueDari", Mesyuarat_ValueDari);
					h.put("Mesyuarat_ValueHingga", Mesyuarat_ValueHingga);
					h.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
					h.put("Mesyuarat_TempahMakananPemohon", Mesyuarat_TempahMakananPemohon);
					h.put("Mesyuarat_TempahMakananBilAhli", Mesyuarat_TempahMakananBilAhli);
					h.put("Mesyuarat_TempahMakananMakanan", Mesyuarat_TempahMakananMakanan);
					h.put("Mesyuarat_TempahMakananMinuman", Mesyuarat_TempahMakananMinuman);
					h.put("Mesyuarat_Unit", Mesyuarat_Unit);
					h.put("Mesyuarat_ValueUnit", Mesyuarat_ValueUnit);
					
					vData.add(h);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return vData;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMesyuaratTemplateData(String ID_MESYUARAT) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector vData = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = new Hashtable();

		        String Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "";
		        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Seksyen = "", Mesyuarat_Unit = "", Mesyuarat_NoFail = "", Mesyuarat_DisediakanOleh = "";
		        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "", Mesyuarat_NoFailDiperlukan = "";
		        
				sql = "SELECT M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, M.ID_LOKASI, M.ID_STATUS, M.ID_SEKSYEN, M.NO_FAIL_DIPERLUKAN, M.DISEDIAKAN_OLEH, M.DISEMAK_OLEH, M.DISAHKAN_OLEH, M.CATATAN, FAIL.NO_FAIL, M.ID_UNIT " +
					"FROM TBLPFDMESYUARATTEMPLATE M, TBLPFDRUJLOKASIMESYUARAT L, TBLPFDRUJSTATUSMESYUARAT ST, TBLPFDFAIL FAIL " + 
					"WHERE M.ID_LOKASI = L.ID_LOKASI(+) AND M.ID_STATUS = ST.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " + 
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Mesyuarat_Bil = rs.getString(1) == null ? "" : rs.getString(1);
					Mesyuarat_Tajuk = rs.getString(2) == null ? "" : rs.getString(2);
					Mesyuarat_Tarikh = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
					Mesyuarat_Dari = rs.getString(4) == null ? "" : rs.getString(4);
					Mesyuarat_Hingga = rs.getString(5) == null ? "" : rs.getString(5);
					Mesyuarat_Lokasi = rs.getString(6) == null ? "" : rs.getString(6);
					Mesyuarat_Status = rs.getString(7) == null ? "" : rs.getString(7);
					Mesyuarat_Seksyen = rs.getString(8) == null ? "" : rs.getString(8);
					Mesyuarat_NoFailDiperlukan = rs.getString(9) == null ? "" : rs.getString(9);
					Mesyuarat_DisediakanOleh = rs.getString(10) == null ? "" : rs.getString(10);
					Mesyuarat_DisemakOleh = rs.getString(11) == null ? "" : rs.getString(11);
					Mesyuarat_DisahkanOleh = rs.getString(12) == null ? "" : rs.getString(12);
					Mesyuarat_Catatan = rs.getString(13) == null ? "" : rs.getString(13);
					Mesyuarat_NoFail = rs.getString(14) == null ? "" : rs.getString(14);
					Mesyuarat_Unit = rs.getString(15) == null ? "" : rs.getString(15);
					h.put("Mesyuarat_Bil", Mesyuarat_Bil);
					h.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
					h.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
					h.put("Mesyuarat_Dari", Mesyuarat_Dari);
					h.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
					h.put("Mesyuarat_Lokasi", Mesyuarat_Lokasi);
					h.put("Mesyuarat_Status", Mesyuarat_Status);
					h.put("Mesyuarat_Seksyen", Mesyuarat_Seksyen);
					h.put("Mesyuarat_NoFailDiperlukan", Mesyuarat_NoFailDiperlukan);
					h.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
					h.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
					h.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
					h.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
					h.put("Mesyuarat_Kategori", "2");
					h.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
					h.put("Mesyuarat_Unit", Mesyuarat_Unit);
					vData.add(h);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return vData;
	}
	
	public String getMesyuaratNextBilNo(String TAJUK_MESYUARAT) throws Exception {
		String returnValue = "0";
		String StrROWNUM = "";
		Db db = new Db();
		String sql = "";
		
		try {			
			if (!"".equalsIgnoreCase(TAJUK_MESYUARAT)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SQLRenderer r = new SQLRenderer();
				int ROWNUM = 0;
				
				r.add("BIL_MESYUARAT");
				r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT);
				r.add("ROWNUM", 1);
				sql = r.getSQLSelect("TBLPFDMESYUARAT", "ID_MESYUARAT DESC");
				r.clear();
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
				if (isNumeric(returnValue)) {
					ROWNUM = Integer.parseInt(returnValue);
					ROWNUM = ROWNUM + 1;
					StrROWNUM = Integer.toString(ROWNUM);
				}else{
					ROWNUM = Integer.parseInt(returnValue);
					StrROWNUM = Integer.toString(ROWNUM);
				}

				returnValue = StrROWNUM;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean deleteMesyuaratData(String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARAT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			Boolean haveData = false;
			
			r.add("ID_MESYUARAT");
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			sql = r.getSQLSelect(TABLE_NAME);
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				haveData = true;
			}
			rs.close();
			
			if (haveData) {
				sql = "DELETE FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATAGENDA WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATMINIT WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				stmt.execute(sql);
				sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_MESYUARAT = " + ID_MESYUARAT;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean checkLocationAvailable(String ID_MESYUARAT, String ID_LOKASI, String TARIKH, int MASA_DARI, int MASA_HINGGA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_LOKASI) && MASA_DARI >= 0 && MASA_HINGGA >= 0) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SQLRenderer r = new SQLRenderer();
			
				r.add("ID_MESYUARAT");
				r.add("ID_LOKASI", ID_LOKASI);
				if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
					r.add("ID_MESYUARAT", ID_MESYUARAT, "!=");
				}
				r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH + "', 'dd/MM/yyyy')"));
				r.add("TO_NUMBER(SUBSTR(MASA_MESYUARAT_DARI, 1, 2))", MASA_DARI, "<=");
				r.add("TO_NUMBER(SUBSTR(MASA_MESYUARAT_HINGGA, 1, 2))", MASA_HINGGA, ">=");
				sql = r.getSQLSelect("TBLPFDMESYUARAT");
				
				rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					returnValue = true;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String saveMesyuaratData(String ID_MESYUARAT, String BIL_MESYUARAT, String NO_FAIL, String TAJUK_MESYUARAT, String JENIS_MESYUARAT, String TARIKH_MESYUARAT, String MESYUARAT_DARI, String MESYUARAT_HINGGA, 
			String ID_SEKSYEN, String ID_UNIT, String ID_LOKASI, String ID_STATUS, String NO_FAIL_DIPERLUKAN, String DISEDIAKAN_OLEH, String DISEMAK_OLEH, String DISAHKAN_OLEH, String CATATAN, 
			String STATUS_PROSES,HttpSession session, String user_negeri) throws Exception {
		String returnValue = "";
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARAT";
	//	Db db = new Db();
		Db db = null;
		Connection conn = null;
		
		try {
			
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			
			
			
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			
			
			Boolean haveData = false;
			long ID_DB = 0;
			
			SQLRenderer r = new SQLRenderer();
			r.add("ID_MESYUARAT");
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			sql = r.getSQLSelect(TABLE_NAME);
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				haveData = true;
			}
			rs.close();

			//------------------------
			// k-mie, 20100727
			// get ID_FAIL from TBLPFDFAIL
			String ID_FAIL = "";
			r.add("ID_FAIL");
			r.add("NO_FAIL", NO_FAIL);
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
			}
			//------------------------
			
			String WAKTU_MESYUARAT_DARI = "";
			String WAKTU_MESYUARAT_HINGGA = "";
			
			if (MESYUARAT_DARI.length() < 4) {
				MESYUARAT_DARI = "0" + MESYUARAT_DARI;
			}
			if (MESYUARAT_HINGGA.length() < 4) {
				MESYUARAT_HINGGA = "0" + MESYUARAT_HINGGA;
			}
			if (!isNumeric(MESYUARAT_DARI)) {
				MESYUARAT_DARI = "1000";
			}
			if (!isNumeric(MESYUARAT_HINGGA)) {
				MESYUARAT_HINGGA = "1100";
			}
			WAKTU_MESYUARAT_DARI = MESYUARAT_DARI.substring(0, 2);
			WAKTU_MESYUARAT_HINGGA = MESYUARAT_HINGGA.substring(0, 2);
			if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
				WAKTU_MESYUARAT_DARI = "1";
			} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
				WAKTU_MESYUARAT_DARI = "2";
			} else {
				WAKTU_MESYUARAT_DARI = "3";
			}
			if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
				WAKTU_MESYUARAT_HINGGA = "1";
			} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
				WAKTU_MESYUARAT_HINGGA = "2";
			} else {
				WAKTU_MESYUARAT_HINGGA = "3";
			}
			
			if (haveData) {
				r.update("ID_MESYUARAT", ID_MESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("BIL_MESYUARAT", BIL_MESYUARAT);
			r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT);
			r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH_MESYUARAT + "', 'dd/MM/yyyy')"));
			r.add("MASA_MESYUARAT_DARI", MESYUARAT_DARI);
			r.add("WAKTU_MESYUARAT_DARI", WAKTU_MESYUARAT_DARI);
			r.add("MASA_MESYUARAT_HINGGA", MESYUARAT_HINGGA);
			r.add("WAKTU_MESYUARAT_HINGGA", WAKTU_MESYUARAT_HINGGA);
			r.add("ID_LOKASI", ID_LOKASI);
			r.add("ID_STATUS", ID_STATUS);
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			r.add("ID_UNIT", ID_UNIT);
			r.add("ID_FAIL", ID_FAIL);
			r.add("NO_FAIL_DIPERLUKAN", NO_FAIL_DIPERLUKAN);
			r.add("DISEDIAKAN_OLEH", DISEDIAKAN_OLEH);
			r.add("DISEMAK_OLEH", DISEMAK_OLEH);
			r.add("DISAHKAN_OLEH", DISAHKAN_OLEH);
			r.add("CATATAN", CATATAN);
			r.add("STATUS_PROSES", STATUS_PROSES);
			r.add("ID_NEGERI", user_negeri);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
				ID_DB = Long.parseLong(ID_MESYUARAT);
			} else {
				ID_DB = DB.getNextID("TBLPFDMESYUARAT_SEQ");
				r.add("ID_MESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			
			
			System.out.println("SIMPAN MESYUARAT CHECK :"+haveData);
			
			
			
			stmt.execute(sql);
			
			
			if (haveData) {
				//copy_mesyuarat(ID_FAIL,ID_DB,db,session);
			} else {
				copy_mesyuarat(ID_FAIL,ID_DB,db,session);
			}
			
			returnValue = Long.toString(ID_DB);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveMesyuaratTempahanMakanan(String ID_MESYUARAT, String PEMOHON, String BIL_AHLI, String MAKANAN, String MINUMAN) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				String sql = "";
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARAT", ID_MESYUARAT);
				r.add("TEMPAHMAKANAN_PEMOHON", PEMOHON);
				r.add("TEMPAHMAKANAN_BILAHLI", BIL_AHLI);
				r.add("TEMPAHMAKANAN_MAKANAN", MAKANAN);
				r.add("TEMPAHMAKANAN_MINUMAN", MINUMAN);
				sql = r.getSQLUpdate("TBLPFDMESYUARAT");
				stmt.execute(sql);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveAhliMesyuaratData(String ID_MESYUARAT, String ID_AHLIMESYUARAT, String KATEGORI_AHLI, String ID_PEGAWAI, String NAMA_AHLI, String NAMA_AGENSI, String ID_AGENSI, 
			String ID_NEGERI, String ID_SEKSYEN, String ID_UNIT, String JAWATAN, String EMAIL, String NO_TEL, String NO_FAKS, String ID_PERANAN, String AlamatAhli) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		
		String TABLE_NAME = "TBLPFDAHLIMESYUARAT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;
			
			
			
			
			if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
				sql = "SELECT UPPER(U.USER_NAME) AS NAMA_PEGAWAI,J.KETERANGAN AS JAWATAN, J.KOD_JAWATAN, U.USER_NAME " +
						"FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
						"WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " 						
					+" AND U.USER_ID ="+ID_PEGAWAI+""
	+" AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'"
			+	" ORDER BY J.KOD_JAWATAN,U.USER_NAME";
				//sql = "SELECT USENAMA_PEGAWAI FROM USERS WHERE ID_PEGAWAI = " + ID_PEGAWAI;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NAMA_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
					JAWATAN = rs.getString(2) == null ? "" : rs.getString(2);
				}
				rs.close();
			}

			
			
			
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_AHLIMESYUARAT)) {
				SQL_SEARCH += " AND ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT;
			}
			if (!"".equalsIgnoreCase(NAMA_AHLI)) {
				if (!"".equalsIgnoreCase(SQL_SEARCH)) {
					SQL_SEARCH += " OR NAMA_AHLIMESYUARAT LIKE '%" + NAMA_AHLI + "%'";
				} else {
					SQL_SEARCH += " AND NAMA_AHLIMESYUARAT LIKE '%" + NAMA_AHLI + "%'";
				}
			}
			sql = "SELECT ID_AHLIMESYUARAT FROM " + TABLE_NAME + " WHERE ID_MESYUARAT = " + ID_MESYUARAT + SQL_SEARCH;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID_AHLIMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
				haveData = true;
			}
			
			rs.close();
			
			SQLRenderer r = new SQLRenderer();
			
			
			if (!ID_AHLIMESYUARAT.equals("") && ID_AHLIMESYUARAT!=null) {
				r.update("ID_AHLIMESYUARAT", ID_AHLIMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ALAMAT_AHLI", AlamatAhli);
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			r.add("ID_PEGAWAI", ID_PEGAWAI);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			r.add("ID_UNIT", ID_UNIT);
			r.add("KATEGORI_AHLI", KATEGORI_AHLI);
			r.add("NAMA_AHLIMESYUARAT", NAMA_AHLI);
			r.add("ID_AGENSI", ID_AGENSI);
			r.add("NAMA_AGENSI", NAMA_AGENSI);
			r.add("JAWATAN", JAWATAN);
			r.add("EMAIL", EMAIL);
			r.add("ID_PERANAN", ID_PERANAN);
			r.add("NO_TEL", NO_TEL);
			r.add("NO_FAKS", NO_FAKS);
			//r.add("ALAMAT_AHLI", AlamatAhli);
			if (!ID_AHLIMESYUARAT.equals("") && ID_AHLIMESYUARAT!=null) {
				
				sql = r.getSQLUpdate(TABLE_NAME);
				System.out.println("TEST UPDATE AHLI ::::"+sql.toUpperCase());
			} else {
				long ID_DB = DB.getNextID("TBLPFDAHLIMESYUARAT_SEQ");
				r.add("ID_AHLIMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				System.out.println("TEST INSERT AHLI ::::"+sql.toUpperCase());
				sql = r.getSQLInsert(TABLE_NAME);
			}
			
			
			
			stmt.execute(sql);
			returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean deleteAhliMesyuaratData(String ID_MESYUARAT, String ID_AHLIMESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDAHLIMESYUARAT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			Boolean haveData = false;
			
			r.add("ID_AHLIMESYUARAT");
			r.add("ID_AHLIMESYUARAT", ID_AHLIMESYUARAT);
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			sql = r.getSQLSelect(TABLE_NAME);
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				haveData = true;
			}
			rs.close();
			
			if (haveData) {
				sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratDataUrussetia(String ID_MESYUARAT, String ID_AHLIMESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = new Hashtable();
			
			
			
			String USER_ID = "",NEGERI_USER = "",ID_AHLI = "",ALAMAT_AHLI = "", KATEGORI_AHLI = "", NAMA_AGENSI = "", ID_SEKSYEN = "", ID_NEGERI = "", ID_UNIT = "", ID_PEGAWAI = "", NAMA_AHLI = "", JAWATAN = "", EMAIL = "", ID_PERANAN = "", NO_TEL = "", NO_FAKS = "";
			sql = "SELECT M.ID_AHLIMESYUARAT, " +
					"M.KATEGORI_AHLI, " +
					"M.NAMA_AGENSI, M.ID_SEKSYEN, M.ID_NEGERI, M.ID_UNIT, M.ID_PEGAWAI, " +
					"M.NAMA_AHLIMESYUARAT, M.JAWATAN, M.EMAIL, M.ID_PERANAN, " +
					"M.NO_TEL, M.NO_FAKS,M.ALAMAT_AHLI "+
				"FROM TBLPFDAHLIMESYUARAT M,"+
				"TBLPFDRUJPERANANAHLIMESYUARAT PR " +
				"WHERE "+
				" M.ID_PERANAN = PR.ID_PERANAN(+) " +
				"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " " +
				"AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT+"";
				
			System.out.println("TEST URUSETIA"+sql.toUpperCase());
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				
				
				h.put("ID_AHLI", rs.getString("ID_AHLIMESYUARAT") == null ? "" : rs.getString("ID_AHLIMESYUARAT"));
				h.put("ALAMAT_AHLI", rs.getString("ALAMAT_AHLI") == null ? "" : rs.getString("ALAMAT_AHLI"));
				h.put("KATEGORI_AHLI", rs.getString("KATEGORI_AHLI") == null ? "" : rs.getString("KATEGORI_AHLI"));
				h.put("NAMA_AGENSI", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI"));
				h.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_UNIT", rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
				h.put("ID_PEGAWAI", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("NAMA_AHLI", rs.getString("NAMA_AHLIMESYUARAT") == null ? "" : rs.getString("NAMA_AHLIMESYUARAT"));
				h.put("JAWATAN", rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
				h.put("EMAIL", rs.getString("EMAIL") == null ? "" : rs.getString("EMAIL"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAKS", rs.getString("NO_FAKS") == null ? "" : rs.getString("NO_FAKS"));
				h.put("ID_PERANAN", rs.getString("ID_PERANAN") == null ? "" : rs.getString("ID_PERANAN"));
				h.put("NEGERI_USER","");
				h.put("USER_ID", "");
				
				
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratData(String ID_MESYUARAT, String ID_AHLIMESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = new Hashtable();
			
		/*	String ID_AHLI = "",ALAMAT_AHLI = "", KATEGORI_AHLI = "", NAMA_AGENSI = "", ID_SEKSYEN = "", ID_NEGERI = "", ID_UNIT = "", ID_PEGAWAI = "", NAMA_AHLI = "", JAWATAN = "", EMAIL = "", ID_PERANAN = "", NO_TEL = "", NO_FAKS = "";
			sql = "SELECT M.ID_AHLIMESYUARAT, M.KATEGORI_AHLI, M.NAMA_AGENSI, M.ID_SEKSYEN, M.ID_NEGERI, M.ID_UNIT, M.ID_PEGAWAI, M.NAMA_AHLIMESYUARAT, M.JAWATAN, M.EMAIL, M.ID_PERANAN, M.NO_TEL, M.NO_FAKS,M.ALAMAT_AHLI " +
				"FROM TBLPFDAHLIMESYUARAT M, TBLRUJSEKSYEN SK, TBLRUJNEGERI NG, TBLRUJPEGAWAI PG, TBLPFDRUJPERANANAHLIMESYUARAT PR " +
				"WHERE M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_NEGERI = NG.ID_NEGERI(+) AND M.ID_PEGAWAI = PG.ID_PEGAWAI(+) AND M.ID_PERANAN = PR.ID_PERANAN(+) " +
				"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT;
				*/
			
			
			String USER_ID = "",NEGERI_USER = "",ID_AHLI = "",ALAMAT_AHLI = "", KATEGORI_AHLI = "", NAMA_AGENSI = "", ID_SEKSYEN = "", ID_NEGERI = "", ID_UNIT = "", ID_PEGAWAI = "", NAMA_AHLI = "", JAWATAN = "", EMAIL = "", ID_PERANAN = "", NO_TEL = "", NO_FAKS = "";
			sql = "SELECT M.ID_AHLIMESYUARAT, " +
					"M.KATEGORI_AHLI, " +
					"M.NAMA_AGENSI, M.ID_SEKSYEN, M.ID_NEGERI, M.ID_UNIT, M.ID_PEGAWAI, " +
					"M.NAMA_AHLIMESYUARAT, M.JAWATAN, M.EMAIL, M.ID_PERANAN, " +
					"M.NO_TEL, M.NO_FAKS,M.ALAMAT_AHLI, J.KOD_JAWATAN, PG.USER_NAME," +
					"TO_CHAR(UI.ID_NEGERI) AS NEGERI_USER,TO_CHAR(PG.USER_ID) AS USER_ID " +
				"FROM TBLPFDAHLIMESYUARAT M, TBLRUJSEKSYEN SK, TBLRUJNEGERI NG, " +
			//	"TBLRUJPEGAWAI PG, " +
				"USERS PG, USERS_INTERNAL UI, TBLRUJJAWATAN J, " +
				"TBLPFDRUJPERANANAHLIMESYUARAT PR " +
				"WHERE M.ID_SEKSYEN = SK.ID_SEKSYEN(+) " +
				"AND M.ID_NEGERI = NG.ID_NEGERI(+) " +
				"AND M.ID_PEGAWAI = PG.USER_ID(+) " +
				"AND PG.USER_ID = UI.USER_ID(+) " +
				"AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " +
				"AND M.ID_PERANAN = PR.ID_PERANAN(+) " +
				"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " " +
				"AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT+""
				+" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'"
				+" ORDER BY J.KOD_JAWATAN,PG.USER_NAME";
			
			System.out.println("test 123 :"+sql.toUpperCase());
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
				KATEGORI_AHLI = rs.getString(2) == null ? "" : rs.getString(2);
				NAMA_AGENSI = rs.getString(3) == null ? "" : rs.getString(3);
				ID_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
				ID_NEGERI = rs.getString(5) == null ? "" : rs.getString(5);
				ID_UNIT = rs.getString(6) == null ? "" : rs.getString(6);
				ID_PEGAWAI = rs.getString(7) == null ? "" : rs.getString(7);
				NAMA_AHLI = rs.getString(8) == null ? "" : rs.getString(8);
				JAWATAN = rs.getString(9) == null ? "" : rs.getString(9);
				EMAIL = rs.getString(10) == null ? "" : rs.getString(10);
				ID_PERANAN = rs.getString(11) == null ? "" : rs.getString(11);
				NO_TEL = rs.getString(12) == null ? "" : rs.getString(12);
				NO_FAKS = rs.getString(13) == null ? "" : rs.getString(13);
				ALAMAT_AHLI = rs.getString(14) == null ? "" : rs.getString(14);
				
				h.put("ID_AHLI", ID_AHLI);
				h.put("ALAMAT_AHLI", ALAMAT_AHLI);
				h.put("KATEGORI_AHLI", KATEGORI_AHLI);
				h.put("NAMA_AGENSI", NAMA_AGENSI);
				h.put("ID_SEKSYEN", ID_SEKSYEN);
				h.put("ID_NEGERI", ID_NEGERI);
				h.put("ID_UNIT", ID_UNIT);
				h.put("ID_PEGAWAI", ID_PEGAWAI);
				h.put("NAMA_AHLI", NAMA_AHLI);
				h.put("JAWATAN", JAWATAN);
				h.put("EMAIL", EMAIL);
				h.put("NO_TEL", NO_TEL);
				h.put("NO_FAKS", NO_FAKS);
				h.put("ID_PERANAN", ID_PERANAN);
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				h.put("USER_ID", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				
				
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean saveAhliMesyuaratTemplateToMesyuarat(String ID_TEMPLATE, String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_TEMPLATE) && !"".equalsIgnoreCase(ID_MESYUARAT)) {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				ResultSet rs = null;
				
				String ID_PEGAWAI = "", ID_NEGERI = "", ID_SEKSYEN = "", ID_UNIT = "", KATEGORI_AHLI = "", NAMA_AHLI = "", ID_AGENSI = "";
				String NAMA_AGENSI = "", JAWATAN = "", EMAIL = "", NO_TEL = "", NO_FAKS = "", ID_PERANAN = "";
				
				sql = "SELECT ID_PEGAWAI, ID_NEGERI, ID_SEKSYEN, ID_UNIT, KATEGORI_AHLI, NAMA_AHLIMESYUARAT, ID_AGENSI, NAMA_AGENSI, JAWATAN, EMAIL, NO_TEL, NO_FAKS, ID_PERANAN " +
					"FROM TBLPFDAHLIMESYUARATTEMPLATE " +
					"WHERE ID_MESYUARAT = " + ID_TEMPLATE +
					" ORDER BY ID_PERANAN, KATEGORI_AHLI, NAMA_AHLIMESYUARAT, ID_AHLIMESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_PEGAWAI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
					ID_SEKSYEN = rs.getString(3) == null ? "" : rs.getString(3);
					ID_UNIT = rs.getString(4) == null ? "" : rs.getString(4);
					KATEGORI_AHLI = rs.getString(5) == null ? "" : rs.getString(5);
					NAMA_AHLI = rs.getString(6) == null ? "" : rs.getString(6);
					ID_AGENSI = rs.getString(7) == null ? "" : rs.getString(7);
					NAMA_AGENSI = rs.getString(8) == null ? "" : rs.getString(8);
					JAWATAN = rs.getString(9) == null ? "" : rs.getString(9);
					EMAIL = rs.getString(10) == null ? "" : rs.getString(10);
					NO_TEL = rs.getString(11) == null ? "" : rs.getString(11);
					NO_FAKS = rs.getString(12) == null ? "" : rs.getString(12);
					ID_PERANAN = rs.getString(13) == null ? "" : rs.getString(13);
					
					long ID_DB = DB.getNextID("TBLPFDAHLIMESYUARAT_SEQ");
					r.add("ID_AHLIMESYUARAT", ID_DB);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("ID_MESYUARAT", ID_MESYUARAT);
					r.add("ID_PEGAWAI", ID_PEGAWAI);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_SEKSYEN", ID_SEKSYEN);
					r.add("ID_UNIT", ID_UNIT);
					r.add("KATEGORI_AHLI", KATEGORI_AHLI);
					r.add("NAMA_AHLIMESYUARAT", NAMA_AHLI);
					r.add("ID_AGENSI", ID_AGENSI);
					r.add("NAMA_AGENSI", NAMA_AGENSI);
					r.add("JAWATAN", JAWATAN);
					r.add("EMAIL", EMAIL);
					r.add("NO_TEL", NO_TEL);
					r.add("NO_FAKS", NO_FAKS);
					r.add("ID_PERANAN", ID_PERANAN);
					sql = r.getSQLInsert("TBLPFDAHLIMESYUARAT");
					r.clear();
					executeSQL(sql);
				}
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	private void executeSQL(String SQL) throws Exception {
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(SQL)) {
				Statement stmt = db.getStatement();
				stmt.execute(SQL);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratList(String ID_MESYUARAT, Boolean URUSETIA) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				int iCount = 1;
				
				String VIEW_KATEGORI_AHLI = "";
				if (URUSETIA) {
					VIEW_KATEGORI_AHLI = " AND M.KATEGORI_AHLI = 3";
				} else {
					VIEW_KATEGORI_AHLI = " AND M.KATEGORI_AHLI <> 3";
				}
				
				String ID_AHLI = "", KATEGORI_AHLI = "", NAMA_AHLI = "", NAMA_SEKSYEN = "", NAMA_NEGERI = "", NAMA_AGENSI = "", AGENSI = "", PERANAN = ""; 
				sql = "SELECT M.ID_AHLIMESYUARAT, M.KATEGORI_AHLI, M.NAMA_AHLIMESYUARAT, SK.NAMA_SEKSYEN, NG.NAMA_NEGERI, AG.NAMA_AGENSI, M.NAMA_AGENSI, PR.NAMA_PERANAN " +
					"FROM TBLPFDAHLIMESYUARAT M, TBLRUJNEGERI NG, TBLRUJSEKSYEN SK, TBLRUJAGENSI AG, TBLPFDRUJPERANANAHLIMESYUARAT PR " +
					"WHERE M.ID_NEGERI = NG.ID_NEGERI(+) AND M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_AGENSI = AG.ID_AGENSI(+) AND M.ID_PERANAN = PR.ID_PERANAN(+) " +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT + VIEW_KATEGORI_AHLI +
					" ORDER BY M.ID_PERANAN, M.KATEGORI_AHLI, M.NAMA_AHLIMESYUARAT, M.ID_AHLIMESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
					KATEGORI_AHLI = rs.getString(2) == null ? "" : rs.getString(2);
					NAMA_AHLI = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_SEKSYEN = rs.getString(4) == null ? "" : rs.getString(4);
					NAMA_NEGERI = rs.getString(5) == null ? "" : rs.getString(5);
					NAMA_AGENSI = rs.getString(6) == null ? "" : rs.getString(6);
					AGENSI = rs.getString(7) == null ? "" : rs.getString(7);
					PERANAN = rs.getString(8) == null ? "" : rs.getString(8);
					if ("1".equalsIgnoreCase(KATEGORI_AHLI) || "3".equalsIgnoreCase(KATEGORI_AHLI)) {
						KATEGORI_AHLI = "PEGAWAI DALAMAN";
						if (!"".equalsIgnoreCase(NAMA_NEGERI)) {
							NAMA_SEKSYEN = NAMA_SEKSYEN + " " + NAMA_NEGERI;
						}
					} else {
						KATEGORI_AHLI = "PEGAWAI LUARAN";
						if ("".equalsIgnoreCase(NAMA_AGENSI)) {
							NAMA_SEKSYEN = AGENSI;
						} else {
							NAMA_SEKSYEN = NAMA_AGENSI;
						}
					}
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDAhli", ID_AHLI);
					h.put("Kategori", KATEGORI_AHLI);
					h.put("NamaAhli", NAMA_AHLI);
					h.put("SeksyenUnitAgensi", NAMA_SEKSYEN);
					h.put("Peranan", PERANAN);
					v.add(h);
					iCount++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratListID(String ID_MESYUARAT, String id_user) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				
				String ID_AHLI = ""; 
				sql = "SELECT M.ID_AHLIMESYUARAT " +
					"FROM TBLPFDAHLIMESYUARAT M, TBLPFDMESYUARAT MS " +
					"WHERE M.ID_MESYUARAT = MS.ID_MESYUARAT " +
					"AND M.ID_PEGAWAI = '"+id_user+"'" +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
					h = new Hashtable();
					h.put("IDAhli", ID_AHLI);
					v.add(h);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratKehadiran(String ID_MESYUARAT, String id_user) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			int iCount = 1;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			
			String ID_AHLI = "", NAMA_AHLI = "", HADIR = "", NAMA_WAKIL = "";
			sql = "SELECT ID_AHLIMESYUARAT, NAMA_AHLIMESYUARAT, HADIR, NAMA_WAKIL FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT = " + ID_MESYUARAT + " AND ID_PEGAWAI = '"+id_user+"' ORDER BY FLAG_PENGERUSI, KATEGORI_AHLI, NAMA_AHLIMESYUARAT, ID_AHLIMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
				NAMA_AHLI = rs.getString(2) == null ? "" : rs.getString(2);
				HADIR = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_WAKIL = rs.getString(4) == null ? "" : rs.getString(4);
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDAhli", ID_AHLI);
				h.put("NamaAhli", NAMA_AHLI);
				if ("1".equalsIgnoreCase(HADIR)) {
					h.put("Wakil", NAMA_WAKIL);
					//h.put("EnableWakil", "readonly=\"readonly\"");
					h.put("CheckHadir", "checked=\"checked\"");
					h.put("CheckTidakHadir", "");
				} else {
					h.put("Wakil", NAMA_WAKIL);
					//h.put("EnableWakil", "");
					h.put("CheckHadir", "");
					h.put("CheckTidakHadir", "checked=\"checked\"");
				}
				v.add(h);
				iCount++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuaratKehadiran(String ID_MESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			int iCount = 1;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			
			String ID_AHLI = "", NAMA_AHLI = "", FLAG_PENGERUSI = "", HADIR = "", NAMA_WAKIL = "";
			sql = "SELECT ID_AHLIMESYUARAT, NAMA_AHLIMESYUARAT, FLAG_PENGERUSI, HADIR, NAMA_WAKIL FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY FLAG_PENGERUSI, KATEGORI_AHLI, NAMA_AHLIMESYUARAT, ID_AHLIMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
				NAMA_AHLI = rs.getString(2) == null ? "" : rs.getString(2);
				FLAG_PENGERUSI = rs.getString(3) == null ? "" : rs.getString(3);
				HADIR = rs.getString(4) == null ? "" : rs.getString(4);
				NAMA_WAKIL = rs.getString(5) == null ? "" : rs.getString(5);
				
				if ("1".equalsIgnoreCase(FLAG_PENGERUSI)) {
					NAMA_AHLI += " (Pengerusi)";
				}
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDAhli", ID_AHLI);
				h.put("NamaAhli", NAMA_AHLI);
				if ("1".equalsIgnoreCase(HADIR)) {
					h.put("Hadir", "Ya");
					h.put("Wakil", "");
				} else {
					h.put("Hadir", "Tidak");
					h.put("Wakil", NAMA_WAKIL);
				}
				v.add(h);
				iCount++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean simpanKehadiran(Vector vData) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Hashtable h = null;
			if (!vData.isEmpty()) {
				int iDataCount = 0;
				String ID_AHLI = "", HADIR = "", NAMA_WAKIL = "";
				for (iDataCount = 0; iDataCount < vData.size(); iDataCount++) {
					h = (Hashtable) vData.elementAt(iDataCount);
					ID_AHLI = (String) h.get("ID");
					HADIR = (String) h.get("HADIR");
					NAMA_WAKIL = (String) h.get("WAKIL");
					if (!"".equalsIgnoreCase(NAMA_WAKIL)) {
						if (NAMA_WAKIL == null) {
							NAMA_WAKIL = "";
						}
					}
					if (!"".equalsIgnoreCase(HADIR)) {
						if ("0".equalsIgnoreCase(HADIR)) {
							HADIR = "0";
						} else {
							HADIR = "1";
						}
					}
					r.update("ID_AHLIMESYUARAT", ID_AHLI);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("HADIR", HADIR);
					r.add("NAMA_WAKIL", NAMA_WAKIL);
					sql = r.getSQLUpdate("TBLPFDAHLIMESYUARAT");
					r.clear();
					stmt.execute(sql);
					stmt.clearBatch();
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean havePengerusi(String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE KATEGORI_URUSETIA = 1 AND ID_MESYUARAT = " + ID_MESYUARAT;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean haveTimbalanPengerusi(String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE KATEGORI_URUSETIA = 2 AND ID_MESYUARAT = " + ID_MESYUARAT;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean haveSetiausaha(String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE KATEGORI_URUSETIA = 3 AND ID_MESYUARAT = " + ID_MESYUARAT;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector getAgendaMesyuaratList(String ID_MESYUARAT, String id_user) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				int iCount = 1;
				
				String ID_AGENDAMESYUARAT = "", TAJUK_AGENDA = ""; 
				sql = "SELECT M.ID_AGENDAMESYUARAT, M.TAJUK_AGENDA " +
					"FROM TBLPFDMESYUARATAGENDA M, TBLPFDMESYUARAT MS " +
					"WHERE M.ID_MESYUARAT = MS.ID_MESYUARAT " +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT +
					"AND M.IDPEGAWAI_TINDAKAN = " + id_user +
					" ORDER BY M.NO_AGENDA, M.TAJUK_AGENDA, M.ID_AGENDAMESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_AGENDAMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					TAJUK_AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
					
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDMinit", ID_AGENDAMESYUARAT);
					h.put("Agenda", TAJUK_AGENDA);
					v.add(h);
					iCount++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getAgendaMesyuaratData(String ID_AGENDA) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listAgenda = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_AGENDA)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				sql = "SELECT * FROM (SELECT A.AGENDA_MESYUARAT, ROWNUM AS BIL, A.TAJUK_AGENDA, A.ID_AGENDAMESYUARAT FROM TBLPFDMESYUARATAGENDA A ORDER BY A.ID_AGENDAMESYUARAT) WHERE ID_AGENDAMESYUARAT = " + ID_AGENDA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("Agenda_Agenda", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("Agenda_No", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Agenda_Tajuk", rs.getString(3) == null ? "" : rs.getString(3));
					listAgenda.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listAgenda;
	}	

	public Boolean deleteAgendaMesyuaratData(String ID_MESYUARAT, String ID_AGENDAMESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATAGENDA";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			Boolean haveData = false;
			
			r.add("ID_AGENDAMESYUARAT");
			r.add("ID_AGENDAMESYUARAT", ID_AGENDAMESYUARAT);
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			sql = r.getSQLSelect(TABLE_NAME);
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				haveData = true;
			}
			rs.close();
			
			if (haveData) {
				// kena delete subminit & minit dulu utk this agenda
				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE ID_AGENDAMESYUARAT = " + ID_AGENDAMESYUARAT;
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATMINIT WHERE ID_AGENDAMESYUARAT = " + ID_AGENDAMESYUARAT;
				stmt.execute(sql);

				sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_AGENDAMESYUARAT = " + ID_AGENDAMESYUARAT;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveAgendaMesyuaratData(String ID_MESYUARAT, String ID_AGENDAMESYUARAT, String NO_AGENDA, String TAJUK_AGENDA, String AGENDA) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATAGENDA";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;

			if (!"".equalsIgnoreCase(ID_AGENDAMESYUARAT)) {
				sql = "SELECT ID_AGENDAMESYUARAT FROM " + TABLE_NAME + " WHERE ID_AGENDAMESYUARAT = " + ID_AGENDAMESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_AGENDAMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				rs.close();
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_AGENDAMESYUARAT", ID_AGENDAMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			r.add("AGENDA_MESYUARAT", AGENDA);
			r.add("NO_AGENDA", NO_AGENDA);
			r.add("TAJUK_AGENDA", TAJUK_AGENDA);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long ID_DB = DB.getNextID("TBLPFDMESYUARAT_SEQ");
				r.add("ID_AGENDAMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			stmt.execute(sql);
			returnValue = true;
			
			populateRunningNumber("1", ID_MESYUARAT);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	

	@SuppressWarnings("unchecked")
	public Vector getMinitMesyuaratList(String ID_MESYUARAT, String id_user) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				int iCount = 1;
				
				String ID_MINITMESYUARAT = "", TAJUK_AGENDA = "", TAJUK_MINIT = ""; 
				sql = "SELECT M.ID_MINITMESYUARAT, AG.TAJUK_AGENDA, M.TAJUK_MINIT " +
					"FROM TBLPFDMESYUARATMINIT M, TBLPFDMESYUARATAGENDA AG, TBLPFDMESYUARAT MS " +
					"WHERE M.ID_MESYUARAT = MS.ID_MESYUARAT AND M.ID_AGENDAMESYUARAT = AG.ID_AGENDAMESYUARAT " +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT +
					"AND M.IDPEGAWAI_TINDAKAN = " + id_user +
					" ORDER BY AG.NO_AGENDA, M.NO_MINIT, M.TAJUK_MINIT, M.ID_MINITMESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_MINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					TAJUK_AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
					TAJUK_MINIT = rs.getString(3) == null ? "" : rs.getString(3);
					
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDMinit", ID_MINITMESYUARAT);
					h.put("Agenda", TAJUK_AGENDA);
					h.put("Minit", TAJUK_MINIT);
					v.add(h);
					iCount++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMinitMesyuaratData(String ID_MINIT) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listAgenda = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MINIT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				sql = "SELECT * FROM (SELECT A.MINIT_MESYUARAT, ROWNUM AS BIL, A.TAJUK_MINIT, A.ID_AGENDAMESYUARAT, " +
						"A.TINDAKAN, A.MAKLUMAN, A.ID_MINITMESYUARAT,A.MINIT_MAKLUMBALAS,A.IDPEGAWAI_TINDAKAN FROM TBLPFDMESYUARATMINIT A " +
						"ORDER BY A.ID_MINITMESYUARAT) WHERE ID_MINITMESYUARAT = " + ID_MINIT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("Minit_Minit", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("Minit_No", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Minit_Tajuk", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("Minit_Agenda", rs.getString(4) == null ? "" : rs.getString(4));
					h.put("Minit_Tindakan", rs.getString(5) == null ? "" : rs.getString(5));
					h.put("Minit_Makluman", rs.getString(6) == null ? "" : rs.getString(6));
					h.put("Minit_Maklumbalas", rs.getString(8)== null ? "" : rs.getString(8));
					h.put("idPegawaiTindakan", rs.getString(9)== null ? "" : rs.getString(9));
					listAgenda.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listAgenda;
	}	

	public Boolean deleteMinitMesyuaratData(String ID_MESYUARAT, String ID_MINITMESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			Boolean haveData = false;
			
			r.add("ID_MINITMESYUARAT");
			r.add("ID_MINITMESYUARAT", ID_MINITMESYUARAT);
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			sql = r.getSQLSelect(TABLE_NAME);
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				haveData = true;
			}
			rs.close();
			
			if (haveData) {
				// kena delete subminit utk this minit dulu
				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE ID_MINITMESYUARAT = " + ID_MINITMESYUARAT;
				stmt.execute(sql);

				sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_MINITMESYUARAT = " + ID_MINITMESYUARAT;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveMinitMesyuaratData(String ID_MESYUARAT, String ID_AGENDAMESYUARAT, 
			String ID_MINITMESYUARAT, String NO_MINIT, String TAJUK_MINIT, String MINIT, 
			String TINDAKAN, String MAKLUMAN,String MAKLUMBALAS, String idPegawaiTindakan) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;

			if (!"".equalsIgnoreCase(ID_MINITMESYUARAT)) {
				sql = "SELECT ID_MINITMESYUARAT FROM " + TABLE_NAME + " WHERE ID_MINITMESYUARAT = " + ID_MINITMESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_MINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				rs.close();
			}
			
			if (!"".equalsIgnoreCase(MAKLUMAN)) {
				TINDAKAN = "";
				MAKLUMAN = "1";
			} else if (!"".equalsIgnoreCase(TINDAKAN)) {
				MAKLUMAN = "0";
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_MINITMESYUARAT", ID_MINITMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			r.add("ID_AGENDAMESYUARAT", ID_AGENDAMESYUARAT);
			r.add("MINIT_MESYUARAT", MINIT);
			r.add("MINIT_MAKLUMBALAS", MAKLUMBALAS);
			r.add("NO_MINIT", NO_MINIT);
			r.add("TAJUK_MINIT", TAJUK_MINIT);
			r.add("TINDAKAN", TINDAKAN);
			r.add("MAKLUMAN", MAKLUMAN);
			r.add("IDPEGAWAI_TINDAKAN", idPegawaiTindakan);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long ID_DB = DB.getNextID("TBLPFDMESYUARATMINIT_SEQ");
				r.add("ID_MINITMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			stmt.execute(sql);
			returnValue = true;
			
			populateRunningNumber("2", ID_AGENDAMESYUARAT);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	

	public Boolean saveMinitMesyuaratDataEdit(String ID_MESYUARAT,
			String ID_MINITMESYUARAT, String MAKLUMBALAS) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;

			if (!"".equalsIgnoreCase(ID_MINITMESYUARAT)) {
				sql = "SELECT ID_MINITMESYUARAT FROM " + TABLE_NAME + " WHERE ID_MINITMESYUARAT = " + ID_MINITMESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_MINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				rs.close();
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_MINITMESYUARAT", ID_MINITMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_MESYUARAT", ID_MESYUARAT);
		//	r.add("ID_AGENDAMESYUARAT", ID_AGENDAMESYUARAT);
//			r.add("MINIT_MESYUARAT", MINIT);
			r.add("MINIT_MAKLUMBALAS", MAKLUMBALAS);
//			r.add("NO_MINIT", NO_MINIT);
//			r.add("TAJUK_MINIT", TAJUK_MINIT);
//			r.add("TINDAKAN", TINDAKAN);
//			r.add("MAKLUMAN", MAKLUMAN);
//			r.add("IDPEGAWAI_TINDAKAN", idPegawaiTindakan);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long ID_DB = DB.getNextID("TBLPFDMESYUARATMINIT_SEQ");
				r.add("ID_MINITMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			stmt.execute(sql);
			returnValue = true;
			
			//populateRunningNumber("2", ID_AGENDAMESYUARAT);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	@SuppressWarnings("unchecked")
	public Vector getSubMinitMesyuaratList(String ID_MESYUARAT, String id_user) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				int iCount = 1;
				
				String ID_SUBMINITMESYUARAT = "", TAJUK_AGENDA = "", TAJUK_MINIT = "", TAJUK_SUBMINIT = ""; 
				sql = "SELECT M.ID_SUBMINITMESYUARAT, AG.TAJUK_AGENDA, MM.TAJUK_MINIT, M.TAJUK_SUBMINIT " +
					"FROM TBLPFDMESYUARATSUBMINIT M, TBLPFDMESYUARATAGENDA AG, TBLPFDMESYUARATMINIT MM, TBLPFDMESYUARAT MS " +
					"WHERE M.ID_MESYUARAT = MS.ID_MESYUARAT AND M.ID_AGENDAMESYUARAT = AG.ID_AGENDAMESYUARAT AND M.ID_MINITMESYUARAT = MM.ID_MINITMESYUARAT " +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT +
					"AND M.IDPEGAWAI_TINDAKAN = " + id_user +
					" ORDER BY AG.NO_AGENDA, MM.NO_MINIT, M.NO_SUBMINIT, M.TAJUK_SUBMINIT, M.ID_SUBMINITMESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_SUBMINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					TAJUK_AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
					TAJUK_MINIT = rs.getString(3) == null ? "" : rs.getString(3);
					TAJUK_SUBMINIT = rs.getString(4) == null ? "" : rs.getString(4);
					
					h = new Hashtable();
					h.put("No", iCount);
					h.put("IDMinit", ID_SUBMINITMESYUARAT);
					h.put("Agenda", TAJUK_AGENDA);
					h.put("Minit", TAJUK_MINIT);
					h.put("SubMinit", TAJUK_SUBMINIT);
					v.add(h);
					iCount++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getSubMinitMesyuaratData(String ID_SUBMINIT) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listAgenda = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_SUBMINIT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;

				sql = "SELECT * FROM (SELECT A.SUBMINIT_MESYUARAT, ROWNUM AS BIL, A.TAJUK_SUBMINIT, A.ID_AGENDAMESYUARAT, A.ID_MINITMESYUARAT, A.TINDAKAN, A.MAKLUMAN, A.ID_SUBMINITMESYUARAT, A.SUBMINIT_MAKLUMBALAS FROM TBLPFDMESYUARATSUBMINIT A ORDER BY A.ID_SUBMINITMESYUARAT) WHERE ID_SUBMINITMESYUARAT = " + ID_SUBMINIT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("SubMinit_SubMinit", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("SubMinit_No", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("SubMinit_Tajuk", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("SubMinit_Agenda", rs.getString(4) == null ? "" : rs.getString(4));
					h.put("SubMinit_Minit", rs.getString(5) == null ? "" : rs.getString(5));
					h.put("SubMinit_Tindakan", rs.getString(6) == null ? "" : rs.getString(6));
					h.put("SubMinit_Makluman", rs.getString(7) == null ? "" : rs.getString(7));
					h.put("SubMinit_Maklumbalas", rs.getString(9) == null ? "" : rs.getString(9));
					listAgenda.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listAgenda;
	}	

	public Boolean deleteSubMinitMesyuaratData(String ID_MINITMESYUARAT, String ID_SUBMINITMESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			Boolean haveData = false;
			

				sql = "DELETE FROM " + TABLE_NAME + " WHERE ID_SUBMINITMESYUARAT = " + ID_SUBMINITMESYUARAT;
				stmt.execute(sql);
				returnValue = true;
			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveSubMinitMesyuaratData(String ID_MESYUARAT, String ID_AGENDAMESYUARAT, String ID_MINITMESYUARAT, String ID_SUBMINITMESYUARAT, String NO_SUBMINIT, String TAJUK_SUBMINIT, String SUBMINIT, String TINDAKAN, String MAKLUMAN, String MAKLUMBALAS, String idPegawaiTindakan) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;

			if (!"".equalsIgnoreCase(ID_SUBMINITMESYUARAT)) {
				sql = "SELECT ID_SUBMINITMESYUARAT FROM " + TABLE_NAME + " WHERE ID_SUBMINITMESYUARAT = " + ID_SUBMINITMESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_SUBMINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				rs.close();
			}
			
			if (TINDAKAN != null) {
				if (!"".equalsIgnoreCase(TINDAKAN)) {
					MAKLUMAN = "0";
				} else {
					if (MAKLUMAN != null) {
						if ("1".equalsIgnoreCase(MAKLUMAN)) {
							MAKLUMAN = "1";
							TINDAKAN = "";
						} else {
							MAKLUMAN = "0";
						}
					} else {
						MAKLUMAN = "0";
					}
				}
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_SUBMINITMESYUARAT", ID_SUBMINITMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			r.add("ID_AGENDAMESYUARAT", ID_AGENDAMESYUARAT);
			r.add("ID_MINITMESYUARAT", ID_MINITMESYUARAT);
			r.add("NO_SUBMINIT", NO_SUBMINIT);
			r.add("TAJUK_SUBMINIT", TAJUK_SUBMINIT);
			r.add("SUBMINIT_MESYUARAT", SUBMINIT);
			r.add("SUBMINIT_MAKLUMBALAS", MAKLUMBALAS);
			r.add("TINDAKAN", TINDAKAN);
			r.add("MAKLUMAN", MAKLUMAN);
			r.add("IDPEGAWAI_TINDAKAN", idPegawaiTindakan);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long ID_DB = DB.getNextID("TBLPFDMESYUARATSUBMINIT_SEQ");
				r.add("ID_SUBMINITMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			stmt.execute(sql);
			returnValue = true;
			
			populateRunningNumber("3", ID_MINITMESYUARAT);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	public Boolean saveSubMinitMesyuaratDataEdit(String ID_MESYUARAT, String ID_SUBMINITMESYUARAT, String MAKLUMBALAS) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;

			if (!"".equalsIgnoreCase(ID_SUBMINITMESYUARAT)) {
				sql = "SELECT ID_SUBMINITMESYUARAT FROM " + TABLE_NAME + " WHERE ID_SUBMINITMESYUARAT = " + ID_SUBMINITMESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_SUBMINITMESYUARAT = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				rs.close();
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				r.update("ID_SUBMINITMESYUARAT", ID_SUBMINITMESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_MESYUARAT", ID_MESYUARAT);
			r.add("SUBMINIT_MAKLUMBALAS", MAKLUMBALAS);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long ID_DB = DB.getNextID("TBLPFDMESYUARATSUBMINIT_SEQ");
				r.add("ID_SUBMINITMESYUARAT", ID_DB);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			stmt.execute(sql);
			returnValue = true;
			
			//populateRunningNumber("3", ID_MINITMESYUARAT);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuaratMinit(String ID_MESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			
			String NO_AGENDA = "", AGENDA = "", NO_MINIT = "", MINIT = "", NO_SUBMINIT = "", SUBMINIT = "";
			sql = "SELECT MA.NO_AGENDA, MA.AGENDA_MESYUARAT, MM.NO_MINIT, MM.MINIT_MESYUARAT, MS.NO_SUBMINIT, MS.SUBMINIT_MESYUARAT " + 
				"FROM TBLPFDMESYUARATAGENDA MA, TBLPFDMESYUARATMINIT MM, TBLPFDMESYUARATSUBMINIT MS " +
				"WHERE MA.ID_AGENDAMESYUARAT = MM.ID_AGENDAMESYUARAT AND MM.ID_MINITMESYUARAT = MS.ID_MINITMESYUARAT(+) " +
				"AND MA.ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY MA.NO_AGENDA, MM.NO_MINIT, MS.NO_SUBMINIT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				NO_AGENDA = rs.getString(1) == null ? "" : rs.getString(1);
				AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
				NO_MINIT = rs.getString(3) == null ? "" : rs.getString(3);
				MINIT = rs.getString(4) == null ? "" : rs.getString(4);
				NO_SUBMINIT = rs.getString(5) == null ? "" : rs.getString(5);
				SUBMINIT = rs.getString(6) == null ? "" : rs.getString(6);
				
				SUBMINIT = SUBMINIT.replace("\r\n", "<br />");
				
				h = new Hashtable();
				h.put("NoAgenda", NO_AGENDA);
				h.put("Agenda", AGENDA);
				h.put("NoMinit", NO_MINIT);
				h.put("Minit", MINIT);
				h.put("NoSubMinit", NO_SUBMINIT);
				h.put("SubMinit", SUBMINIT);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public String getSOCPerananAhliMesyuarat(String SOC_NAME) throws Exception {
		return getSOCPerananAhliMesyuarat(SOC_NAME, "", "", "");
	}
	public String getSOCPerananAhliMesyuarat(String SOC_NAME, String SELECTED_PERANAN) throws Exception {
		return getSOCPerananAhliMesyuarat(SOC_NAME, SELECTED_PERANAN, "", "");
	}
	public String getSOCPerananAhliMesyuarat(String SOC_NAME, String SELECTED_PERANAN, String KATEGORI_AHLI) throws Exception {
		return getSOCPerananAhliMesyuarat(SOC_NAME, SELECTED_PERANAN, KATEGORI_AHLI, "", "");
	}
	public String getSOCPerananAhliMesyuarat(String SOC_NAME, String SELECTED_PERANAN, String KATEGORI_AHLI, String DISABLED) throws Exception {
		return getSOCPerananAhliMesyuarat(SOC_NAME, SELECTED_PERANAN, KATEGORI_AHLI, DISABLED, "");
	}
	public String getSOCPerananAhliMesyuarat(String SOC_NAME, String SELECTED_PERANAN, String KATEGORI_AHLI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(KATEGORI_AHLI)) {
				SQL_SEARCH = "WHERE KATEGORI_AHLI = " + KATEGORI_AHLI + " ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_PERANAN = "", PERANAN = "";
			sql = "SELECT ID_PERANAN, SUBSTR(NAMA_PERANAN, 1, 50) FROM TBLPFDRUJPERANANAHLIMESYUARAT " +
				SQL_SEARCH + "ORDER BY ID_PERANAN, NAMA_PERANAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERANAN = rs.getString(1) == null ? "" : rs.getString(1);
				PERANAN = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_PERANAN)) {
					if (SELECTED_PERANAN.equalsIgnoreCase(ID_PERANAN)) {
						returnValue += "  <option value=\"" + ID_PERANAN + "\" selected=\"selected\">" + PERANAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_PERANAN + "\">" + PERANAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCLokasiMesyuarat(String SOC_NAME) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, "", "", "", "", "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, SELECTED_LOKASI, "", "", "", "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI, String DISABLED) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, SELECTED_LOKASI, DISABLED, "", "", "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI, String DISABLED,String ID_SEKSYEN , String ID_NEGERI ) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, SELECTED_LOKASI, DISABLED, "", ID_SEKSYEN, ID_NEGERI);
	}
	
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI, String DISABLED, String ONCHANGE, String ID_SEKSYEN , String ID_NEGERI ) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
//			if(!"".equalsIgnoreCase(ID_SEKSYEN)){
//				SQL_SEARCH = "";				
//			}
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			if(!"".equalsIgnoreCase(ID_SEKSYEN)){
				 SQL_Search_Seksyen = " AND  ID_SEKSYEN = '" + ID_SEKSYEN + "' ";
			}
			
			if(!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)){
				SQL_Search_Negeri = " AND  ID_SEKSYEN = '" + ID_NEGERI + "' ";
			}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				" WHERE ID_LOKASI IS NOT NULL " +
				SQL_Search_Seksyen + SQL_Search_Negeri +
				" ORDER BY LOKASI, ID_LOKASI";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_LOKASI = rs.getString(1) == null ? "" : rs.getString(1);
				LOKASI = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_LOKASI)) {
					if (SELECTED_LOKASI.equalsIgnoreCase(ID_LOKASI)) {
						returnValue += "  <option value=\"" + ID_LOKASI + "\" selected=\"selected\">" + LOKASI + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_LOKASI + "\">" + LOKASI + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI, String DISABLED,String ID_UNIT , String ID_NEGERI ) throws Exception {
		return getSOCLokasiMesyuaratNegeri(SOC_NAME, SELECTED_LOKASI, DISABLED, "", ID_UNIT, ID_NEGERI);
	}
	
	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI, String DISABLED, String ONCHANGE, String ID_UNIT , String ID_NEGERI ) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
//			if(!"".equalsIgnoreCase(ID_SEKSYEN)){
//				SQL_SEARCH = "";				
//			}
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			if(!"".equalsIgnoreCase(ID_UNIT)){
				 SQL_Search_Seksyen = " AND  ID_UNIT = '" + ID_UNIT + "' ";
			}
			
			if(!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)){
				SQL_Search_Negeri = " AND  ID_NEGERI = '" + ID_NEGERI + "' ";
			}

			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				" WHERE ID_LOKASI IS NOT NULL " +
				SQL_Search_Seksyen + SQL_Search_Negeri +
				" ORDER BY LOKASI, ID_LOKASI";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_LOKASI = rs.getString(1) == null ? "" : rs.getString(1);
				LOKASI = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_LOKASI)) {
					if (SELECTED_LOKASI.equalsIgnoreCase(ID_LOKASI)) {
						returnValue += "  <option value=\"" + ID_LOKASI + "\" selected=\"selected\">" + LOKASI + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_LOKASI + "\">" + LOKASI + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public String getSOCStatusMesyuarat(String SOC_NAME) throws Exception {
		return getSOCStatusMesyuarat(SOC_NAME, "", "", "");
	}
	public String getSOCStatusMesyuarat(String SOC_NAME, String SELECTED_STATUS) throws Exception {
		return getSOCStatusMesyuarat(SOC_NAME, SELECTED_STATUS, "", "");
	}
	public String getSOCStatusMesyuarat(String SOC_NAME, String SELECTED_STATUS, String DISABLED) throws Exception {
		return getSOCStatusMesyuarat(SOC_NAME, SELECTED_STATUS, DISABLED, "");
	}
	public String getSOCStatusMesyuarat(String SOC_NAME, String SELECTED_STATUS, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
System.out.println("SOC STATUS ::"+SELECTED_STATUS);
		
		if(SELECTED_STATUS==null || SELECTED_STATUS.equals("") )
		{
			SELECTED_STATUS = "";
		}
		
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			//returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_STATUS = "", STATUS = "";
			sql = "SELECT ID_STATUS, SUBSTR(STATUS_MESYUARAT, 1, 50) FROM TBLPFDRUJSTATUSMESYUARAT " +
				SQL_SEARCH + "ORDER BY STATUS_MESYUARAT, ID_STATUS";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_STATUS = rs.getString(1) == null ? "" : rs.getString(1);
				STATUS = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(STATUS)) {
					if (SELECTED_STATUS.equalsIgnoreCase(ID_STATUS)) {
						returnValue += "  <option value=\"" + ID_STATUS + "\" selected=\"selected\">" + STATUS + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_STATUS + "\">" + STATUS + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, "", "", "", "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, "", "", "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, "", "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, SELECTED_KATEGORI, "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI, String DISABLED) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, SELECTED_KATEGORI, DISABLED, "", "");
	}
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI, String DISABLED,String actionx,String MAIN_actionx) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, SELECTED_KATEGORI, DISABLED, "",actionx,MAIN_actionx);
	}
	
	
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI, String DISABLED, String ONCHANGE,String actionx,String main_actionx) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		System.out.println("main actionx ::"+main_actionx);
		System.out.println("submit ::"+actionx);
		System.out.println("ID_PEGAWAI XXXXXX ::"+SELECTED_PEGAWAI);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			ResultSet rs1 = null;
			String SQL_SEARCH = "";
			String SQL_SEARCH_ID_MESYUARAT = "";
			
			
			if (!"".equalsIgnoreCase(SELECTED_SEKSYEN)) {
				SQL_SEARCH = "AND UI.ID_SEKSYEN = " + SELECTED_SEKSYEN;
			}
			
			if ("".equalsIgnoreCase(ID_MESYUARAT)) {
				ID_MESYUARAT = "00000";
			}
			
			
			
			SQL_SEARCH_ID_MESYUARAT =" ID_MESYUARAT =  " + ID_MESYUARAT + " AND ";
			
			
			String VIEW_KAT_AHLI = "";
//			if ("3".equalsIgnoreCase(SELECTED_KATEGORI)) {
//				VIEW_KAT_AHLI = "KATEGORI_AHLI != 3 AND ";
//			} else {
//				VIEW_KAT_AHLI = "KATEGORI_AHLI = 3 AND ";				
//			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			//returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			
			if(SELECTED_PEGAWAI.equals("") && !actionx.equals("daftarAhli"))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			
			else
			{
				
				
				if((main_actionx.equals("daftarAhli") && actionx.equals("")) || ((actionx.equals("changeSeksyen") || actionx.equals("changeNegeri"))))
					
				{					
					returnValue += "  <option value=\"\">SILA PILIH\r\n";	
				}
				else
				{
				
		sql1 = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI " +
				" ,J.KOD_JAWATAN, PG.USER_NAME " +
		"FROM " +
	    "USERS PG, " +
		"USERS_INTERNAL UI,TBLRUJJAWATAN J " +
		"WHERE UI.USER_ID = PG.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND UI.ID_NEGERI = '16' ";
		sql1 += " AND PG.USER_ID = '"+SELECTED_PEGAWAI+"'";
	
		//System.out.println("sql1:"+sql1.toUpperCase());
			
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {	
				
			//	System.out.println("check sql1:"+sql1.toUpperCase());
						returnValue += "  <option value=\"" + (rs1.getString("ID_PEGAWAI") == null ? "" : rs1.getString("ID_PEGAWAI")) + "\" selected=\"selected\">" + (rs1.getString("NP") == null ? "" : rs1.getString("NP")) + "</option>\r\n";
			
			}
				}
					
			
			
			}
			
			
			String ID_PEGAWAI = "", NAMA_PEGAWAI = "";
			
			/*RASUL PUNYA
			 * sql = "SELECT DISTINCT SUBSTR(TRIM(PG.NAMA_PEGAWAI), 1, 50) AS NP, PG.ID_PEGAWAI " +
				"FROM TBLRUJPEGAWAI PG, TBLPFDAHLIMESYUARAT AH " +
				"WHERE PG.ID_PEGAWAI = AH.ID_PEGAWAI(+) " + SQL_SEARCH + 
				" AND PG.ID_PEGAWAI NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + SQL_SEARCH_ID_MESYUARAT + " ID_PEGAWAI IS NOT NULL ) " +
				"ORDER BY NP, PG.ID_PEGAWAI";*/
			
			
			sql = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI " +
					" ,J.KOD_JAWATAN, PG.USER_NAME " +
			"FROM " +
		//	"TBLRUJPEGAWAI PG, " +
    		"USERS PG, " +
			"TBLPFDAHLIMESYUARAT AH,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
			"WHERE PG.USER_ID = AH.ID_PEGAWAI(+) AND UI.ID_NEGERI = '16' AND  UI.USER_ID = PG.USER_ID " + SQL_SEARCH ;
		
			if(!actionx.equals("viewAhli"))
			{
			sql += " AND PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + SQL_SEARCH_ID_MESYUARAT + " ID_PEGAWAI IS NOT NULL ) ";
			}
			
			sql += " AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " 
			+" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'";
			
				//	+" ORDER BY J.KOD_JAWATAN,PG.USER_NAME";
			
			
			
			if(!SELECTED_PEGAWAI.equals(""))
			{
			sql += " AND PG.USER_ID != '"+SELECTED_PEGAWAI+"'";
			}

			sql += " ORDER BY J.KOD_JAWATAN, PG.USER_NAME";

			
			
			//System.out.println("SELECT PEGAWAI :"+sql.toUpperCase());
		
			
			
			
//			sql = "SELECT DISTINCT SUBSTR(TRIM(PG.NAMA_PEGAWAI), 1, 50) AS NP, PG.ID_PEGAWAI " +
//			"FROM TBLRUJPEGAWAI PG, TBLPFDAHLIMESYUARAT AH " +
//			"WHERE PG.ID_PEGAWAI = AH.ID_PEGAWAI(+) " + SQL_SEARCH + 
//			" AND PG.ID_PEGAWAI NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + VIEW_KAT_AHLI + "AND ID_PEGAWAI <> null) " +
//			"ORDER BY NP, PG.ID_PEGAWAI";
			
//			sql = "SELECT DISTINCT SUBSTR(TRIM(USR.USER_NAME), 1, 50) AS NP, PG.USER_ID AS ID_PEGAWAI " + 
//			      " FROM USERS_INTERNAL PG JOIN USERS USR ON PG.USER_ID = USR.USER_ID " + 
//			      " WHERE  PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " +  VIEW_KAT_AHLI  + " AND ID_PEGAWAI <> 0) " +SQL_SEARCH+ 
//			      " AND PG.ID_NEGERI = 16 ORDER BY NP, PG.USER_ID ";

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				NAMA_PEGAWAI = rs.getString(1) == null ? "" : rs.getString(1);
				ID_PEGAWAI = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
					if (SELECTED_PEGAWAI.equalsIgnoreCase(ID_PEGAWAI)) {
						returnValue += "  <option value=\"" + ID_PEGAWAI + "\" selected=\"selected\">" + NAMA_PEGAWAI + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_PEGAWAI + "\">" + NAMA_PEGAWAI + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		//	System.out.println("returnValue:"+returnValue.toUpperCase());
			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
		
		
	}

	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, "", "", "", "", "", "");
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, "", "", "", "", "");
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_NEGERI, "", "", "", "");
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_NEGERI, SELECTED_KATEGORI, "", "", "");
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED,String actionx,String MAIN_actionx) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_NEGERI, DISABLED, SELECTED_KATEGORI, "",actionx,MAIN_actionx);
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED, String ONCHANGE,String actionx,String main_actionx) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		System.out.print("ID_PEGAWAI ::"+SELECTED_PEGAWAI);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			ResultSet rs1 = null;
			String SQL_SEARCH = "";
			String SQL_SEARCH_ID_MESYUARAT = "";
			if (!"".equalsIgnoreCase(SELECTED_NEGERI)) {
				SQL_SEARCH = "AND UI.ID_NEGERI = " + SELECTED_NEGERI;
			}
			
			if ("".equalsIgnoreCase(ID_MESYUARAT)) {
				ID_MESYUARAT = "00000";
			}
			
			SQL_SEARCH_ID_MESYUARAT =" ID_MESYUARAT =  " + ID_MESYUARAT + " AND ";
			
			String VIEW_KAT_AHLI = "";
//			if ("3".equalsIgnoreCase(SELECTED_KATEGORI)) {
//				VIEW_KAT_AHLI = "KATEGORI_AHLI != 3 ";
//			} else {
//				VIEW_KAT_AHLI = "KATEGORI_AHLI = 3 ";
//			}
			 
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			//returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			

			if(SELECTED_PEGAWAI.equals(""))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			else
			{
				
				if(!actionx.equals("daftarAhli") && !actionx.equals("changeSeksyen") && !actionx.equals("changeNegeri"))
				{
				
		sql1 = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI " +
				" ,J.KOD_JAWATAN, PG.USER_NAME " +
		"FROM " +
	    "USERS PG, " +
		"USERS_INTERNAL UI,TBLRUJJAWATAN J " +
		"WHERE UI.USER_ID = PG.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) ";
		sql1 += " AND PG.USER_ID = '"+SELECTED_PEGAWAI+"'";
	
		//System.out.println("sql1:"+sql1.toUpperCase());
			
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {	
				
				System.out.println("check sql1:"+sql1.toUpperCase());
						returnValue += "  <option value=\"" + (rs1.getString("ID_PEGAWAI") == null ? "" : rs1.getString("ID_PEGAWAI")) + "\" selected=\"selected\">" + (rs1.getString("NP") == null ? "" : rs1.getString("NP")) + "</option>\r\n";
			
			}
				}
				else
				{
					returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";	
					
				}
			
			}
	
			
			String ID_PEGAWAI = "", NAMA_PEGAWAI = "";
		/*	sql = "SELECT DISTINCT SUBSTR(TRIM(PG.NAMA_PEGAWAI), 1, 50) AS NP, PG.ID_PEGAWAI " +
				"FROM TBLRUJPEGAWAI PG, TBLPFDAHLIMESYUARAT AH " +
				"WHERE PG.ID_PEGAWAI = AH.ID_PEGAWAI(+) " + SQL_SEARCH + 
				" AND PG.ID_PEGAWAI NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + SQL_SEARCH_ID_MESYUARAT + " ID_PEGAWAI IS NOT NULL) " +
				"ORDER BY NP, PG.ID_PEGAWAI";*/
			
			
			sql = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI" +
					", J.KOD_JAWATAN, PG.USER_NAME " +
			"FROM USERS PG,USERS_INTERNAL UI, TBLPFDAHLIMESYUARAT AH, TBLRUJJAWATAN J " +
			"WHERE PG.USER_ID = AH.ID_PEGAWAI(+) AND PG.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " + SQL_SEARCH + 
			" AND PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + SQL_SEARCH_ID_MESYUARAT + " ID_PEGAWAI IS NOT NULL) " +
			" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'";
			
		//	+" ORDER BY J.KOD_JAWATAN,PG.USER_NAME";
			
			if(!SELECTED_PEGAWAI.equals(""))
			{
			sql += " AND PG.USER_ID != '"+SELECTED_PEGAWAI+"'";
			}
			
			sql += " ORDER BY J.KOD_JAWATAN, PG.USER_NAME";
			
			
			System.out.println("SELECT PEGAWAI QUERY ::::::"+sql.toUpperCase());
			
			
//			sql = "SELECT DISTINCT SUBSTR(TRIM(PG.NAMA_PEGAWAI), 1, 50) AS NP, PG.ID_PEGAWAI " +
//			"FROM TBLRUJPEGAWAI PG, TBLPFDAHLIMESYUARAT AH " +
//			"WHERE PG.ID_PEGAWAI = AH.ID_PEGAWAI(+) " + SQL_SEARCH + 
//			" AND PG.ID_PEGAWAI NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + VIEW_KAT_AHLI + "AND ID_PEGAWAI <> 0) " +
//			"ORDER BY NP, PG.ID_PEGAWAI";
//			sql = "SELECT DISTINCT SUBSTR(TRIM(USR.USER_NAME), 1, 50) AS NP, PG.USER_ID AS ID_PEGAWAI " + 
//		      " FROM USERS_INTERNAL PG JOIN USERS USR ON PG.USER_ID = USR.USER_ID " + 
//		      " WHERE  PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " +  VIEW_KAT_AHLI  + " AND ID_PEGAWAI <> 0) " +SQL_SEARCH+ 
//		      " ORDER BY NP, PG.USER_ID ";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				NAMA_PEGAWAI = rs.getString(1) == null ? "" : rs.getString(1);
				ID_PEGAWAI = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
					if (SELECTED_PEGAWAI.equalsIgnoreCase(ID_PEGAWAI)) {
						returnValue += "  <option value=\"" + ID_PEGAWAI + "\" selected=\"selected\">" + NAMA_PEGAWAI + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_PEGAWAI + "\">" + NAMA_PEGAWAI + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public String getSOCAgendaMesyuarat(String SOC_NAME) throws Exception {
		return getSOCAgendaMesyuarat(SOC_NAME, "", "", "", "");
	}
	public String getSOCAgendaMesyuarat(String SOC_NAME, String SELECTED_AGENDA) throws Exception {
		return getSOCAgendaMesyuarat(SOC_NAME, SELECTED_AGENDA, "", "", "");
	}
	public String getSOCAgendaMesyuarat(String SOC_NAME, String SELECTED_AGENDA, String SELECTED_MESYUARAT) throws Exception {
		return getSOCAgendaMesyuarat(SOC_NAME, SELECTED_AGENDA, "", "", "");
	}
	public String getSOCAgendaMesyuarat(String SOC_NAME, String SELECTED_AGENDA, String SELECTED_MESYUARAT, String DISABLED) throws Exception {
		return getSOCAgendaMesyuarat(SOC_NAME, SELECTED_AGENDA, DISABLED, "", "");
	}
	
	
	public String getSOCAgendaMesyuarat(String SOC_NAME, String SELECTED_AGENDA, String ID_MESYUARAT, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				SQL_SEARCH = "WHERE ID_MESYUARAT = " + ID_MESYUARAT + " ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_AGENDA = "", AGENDA = "";
			sql = "SELECT ID_AGENDAMESYUARAT, SUBSTR(TAJUK_AGENDA, 1, 50) FROM TBLPFDMESYUARATAGENDA " +
				SQL_SEARCH + "ORDER BY NO_AGENDA, TAJUK_AGENDA, ID_AGENDAMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AGENDA = rs.getString(1) == null ? "" : rs.getString(1);
				AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AGENDA)) {
					if (SELECTED_AGENDA.equalsIgnoreCase(ID_AGENDA)) {
						returnValue += "  <option value=\"" + ID_AGENDA + "\" selected=\"selected\">" + AGENDA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AGENDA + "\">" + AGENDA + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	
	public String getSOCMaklumatPegawai(String SOC_NAME, String SELECTED_AGENDA, String ID_MESYUARAT, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		
		System.out.println("MAKLUMAT ID PEGAWAI :"+SOC_NAME);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			/*if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				SQL_SEARCH = "WHERE ID_MESYUARAT = " + ID_MESYUARAT + " ";
			}*/
			
			
			
			
			
			/*
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_AGENDA = "", AGENDA = "";
			sql = "SELECT ID_AGENDAMESYUARAT, SUBSTR(TAJUK_AGENDA, 1, 50) FROM TBLPFDMESYUARATAGENDA " +
				SQL_SEARCH + "ORDER BY NO_AGENDA, TAJUK_AGENDA, ID_AGENDAMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AGENDA = rs.getString(1) == null ? "" : rs.getString(1);
				AGENDA = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_AGENDA)) {
					if (SELECTED_AGENDA.equalsIgnoreCase(ID_AGENDA)) {
						returnValue += "  <option value=\"" + ID_AGENDA + "\" selected=\"selected\">" + AGENDA + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_AGENDA + "\">" + AGENDA + "</option>\r\n";
					}
				}
			}*/
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	

	public String getSOCMinitMesyuarat(String SOC_NAME) throws Exception {
		return getSOCMinitMesyuarat(SOC_NAME, "", "", "", "");
	}
	public String getSOCMinitMesyuarat(String SOC_NAME, String SELECTED_MINIT) throws Exception {
		return getSOCMinitMesyuarat(SOC_NAME, SELECTED_MINIT, "", "", "");
	}
	public String getSOCMinitMesyuarat(String SOC_NAME, String SELECTED_MINIT, String ID_AGENDA) throws Exception {
		return getSOCMinitMesyuarat(SOC_NAME, SELECTED_MINIT, ID_AGENDA, "", "");
	}
	public String getSOCMinitMesyuarat(String SOC_NAME, String SELECTED_MINIT, String ID_AGENDA, String DISABLED) throws Exception {
		return getSOCMinitMesyuarat(SOC_NAME, SELECTED_MINIT, ID_AGENDA, DISABLED, "");
	}
	public String getSOCMinitMesyuarat(String SOC_NAME, String SELECTED_MINIT, String ID_AGENDA, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(ID_AGENDA)) {
				SQL_SEARCH = "WHERE ID_AGENDAMESYUARAT = " + ID_AGENDA + " ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_MINIT = "", MINIT = "";
			sql = "SELECT ID_MINITMESYUARAT, SUBSTR(TAJUK_MINIT, 1, 50) FROM TBLPFDMESYUARATMINIT " +
				SQL_SEARCH + "ORDER BY NO_MINIT, TAJUK_MINIT, ID_MINITMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_MINIT = rs.getString(1) == null ? "" : rs.getString(1);
				MINIT = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_MINIT)) {
					if (SELECTED_MINIT.equalsIgnoreCase(ID_MINIT)) {
						returnValue += "  <option value=\"" + ID_MINIT + "\" selected=\"selected\">" + MINIT + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_MINIT + "\">" + MINIT + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuarat(String Mesyuarat_NoFail, String Mesyuarat_Tajuk, String Mesyuarat_Jenis, String Mesyuarat_Urusetia, String Mesyuarat_Lokasi, String Mesyuarat_Tarikh,String id_seksyen, String id_negeri, String id_user) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			if(!"".equalsIgnoreCase(id_seksyen)){
				 SQL_Search_Seksyen = " AND M.ID_SEKSYEN = '" + id_seksyen + "' ";
			}
			
			if(!"".equalsIgnoreCase(id_negeri) && !"16".equalsIgnoreCase(id_negeri)){
				SQL_Search_Negeri = " AND M.ID_SEKSYEN = '" + id_negeri + "' ";
			}
			
			String SQL_Search = "";
			if ("".equalsIgnoreCase(Mesyuarat_Tajuk) && "".equalsIgnoreCase(Mesyuarat_Jenis) && "".equalsIgnoreCase(Mesyuarat_Urusetia) && "".equalsIgnoreCase(Mesyuarat_Lokasi) && "".equalsIgnoreCase(Mesyuarat_Tarikh)) {
				// search all
			} else {
				// build search string
				if (!"".equalsIgnoreCase(Mesyuarat_NoFail)) {
					SQL_Search += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + Mesyuarat_NoFail.toUpperCase() + "%' ";
				}
				if ("2".equalsIgnoreCase(Mesyuarat_Jenis)) {
					SQL_Search += "AND UPPER(M.KATEGORI_MESYUARAT) = '2' ";
				} else if ("1".equalsIgnoreCase(Mesyuarat_Jenis)) {
					SQL_Search += "AND UPPER(M.KATEGORI_MESYUARAT) != '2' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tajuk)) {
					SQL_Search += "AND UPPER(M.TAJUK_MESYUARAT) LIKE '%" + Mesyuarat_Tajuk.toUpperCase() + "%' ";
				}
				if (!"0".equalsIgnoreCase(Mesyuarat_Urusetia)) {
					SQL_Search += "AND UPPER(RS.NAMA_SEKSYEN) LIKE '%" + Mesyuarat_Urusetia.toUpperCase() + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Lokasi)) {
					SQL_Search += "AND UPPER(RL.LOKASI) LIKE '%" + Mesyuarat_Lokasi.toUpperCase() + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tarikh)) {
					SQL_Search += "AND UPPER(M.TARIKH_MESYUARAT) LIKE '%" + Mesyuarat_Tarikh.toUpperCase() + "%' ";
				}
			}
			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_SEKSYEN, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
				"FROM TBLPFDMESYUARAT M, TBLRUJSEKSYEN RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_SEKSYEN = RS.ID_SEKSYEN(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
				SQL_Search + SQL_Search_Seksyen + SQL_Search_Negeri +
				"ORDER BY M.TARIKH_MESYUARAT";
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			String Mesyuarat_Dari = "", Mesyuarat_Hingga = "", WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
			while (rs.next()) {
				WaktuMesyuarat = rs.getString(10) == null ? "" : rs.getString(10);
				if (!"".equalsIgnoreCase(WaktuMesyuarat)) {
					if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " AM";
					} else if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " PM";
					} else {
						WaktuMesyuarat = " PM";
					}
				}
				
				Mesyuarat_Dari = rs.getString(6) == null ? "" : rs.getString(6);
				Mesyuarat_Hingga = rs.getString(7) == null ? "" : rs.getString(7);
				if (Mesyuarat_Dari.length() < 4) {
					Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
					WAKTU_MESYUARAT_DARI = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
					WAKTU_MESYUARAT_DARI = " PM";
				} else {
					WAKTU_MESYUARAT_DARI = " PM";
				}
				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
					WAKTU_MESYUARAT_HINGGA = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
					WAKTU_MESYUARAT_HINGGA = " PM";
				} else {
					WAKTU_MESYUARAT_HINGGA = " PM";
				}
				Mesyuarat_Dari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
				Mesyuarat_Hingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
				
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListIDMesyuarat", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListUrusetiaSeksyen", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("ListBil", rs.getString(3) == null ? "" : rs.getString(3));
				h.put("ListTajuk", rs.getString(4) == null ? "" : rs.getString(4));
				h.put("ListTarikh", rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5)));
				h.put("ListMasa", Mesyuarat_Dari + " - " + Mesyuarat_Hingga);
				h.put("ListLokasi", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("ListStatus", rs.getString(9) == null ? "" : rs.getString(9));
				h.put("ListNoFail", rs.getString(10) == null ? "" : rs.getString(10));
				listMesyuarat.add(h);
				ListNo++;
			}
			rs.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listMesyuarat;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuaratNegeri(String Mesyuarat_NoFail, String Mesyuarat_Tajuk, String Mesyuarat_Jenis, String Mesyuarat_Urusetia, String Mesyuarat_Lokasi, String Mesyuarat_Tarikh,String id_unit, String id_negeri, String id_user) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			if(!"".equalsIgnoreCase(id_unit)){
				 SQL_Search_Seksyen = " AND M.ID_UNIT = '" + id_unit + "' ";
			}
			
			if(!"".equalsIgnoreCase(id_negeri) && !"16".equalsIgnoreCase(id_negeri)){
				SQL_Search_Negeri = " AND M.ID_NEGERI = '" + id_negeri + "' ";
			}
			
			String SQL_Search = "";
			if ("".equalsIgnoreCase(Mesyuarat_Tajuk) && "".equalsIgnoreCase(Mesyuarat_Jenis) && "".equalsIgnoreCase(Mesyuarat_Urusetia) && "".equalsIgnoreCase(Mesyuarat_Lokasi) && "".equalsIgnoreCase(Mesyuarat_Tarikh)) {
				// search all
			} else {
				// build search string
				if (!"".equalsIgnoreCase(Mesyuarat_NoFail)) {
					SQL_Search += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + Mesyuarat_NoFail.toUpperCase() + "%' ";
				}
				if ("2".equalsIgnoreCase(Mesyuarat_Jenis)) {
					SQL_Search += "AND UPPER(M.KATEGORI_MESYUARAT) = '2' ";
				} else if ("1".equalsIgnoreCase(Mesyuarat_Jenis)) {
					SQL_Search += "AND UPPER(M.KATEGORI_MESYUARAT) != '2' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tajuk)) {
					SQL_Search += "AND UPPER(M.TAJUK_MESYUARAT) LIKE '%" + Mesyuarat_Tajuk.toUpperCase() + "%' ";
				}
				if (!"0".equalsIgnoreCase(Mesyuarat_Urusetia)) {
					SQL_Search += "AND UPPER(RS.NAMA_UNIT) LIKE '%" + Mesyuarat_Urusetia.toUpperCase() + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Lokasi)) {
					SQL_Search += "AND UPPER(RL.LOKASI) LIKE '%" + Mesyuarat_Lokasi.toUpperCase() + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tarikh)) {
					SQL_Search += "AND UPPER(M.TARIKH_MESYUARAT) LIKE '%" + Mesyuarat_Tarikh.toUpperCase() + "%' ";
				}
			}
			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_UNIT, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
				"FROM TBLPFDMESYUARAT M, TBLRUJUNIT RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_UNIT = RS.ID_UNIT(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
				SQL_Search + SQL_Search_Seksyen + SQL_Search_Negeri +
				"ORDER BY M.TARIKH_MESYUARAT";
			
//			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_UNIT, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
//			"FROM TBLPFDMESYUARAT M, TBLRUJUNIT RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
//			"WHERE M.ID_UNIT = RS.ID_UNIT(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
//			SQL_Search + SQL_Search_Seksyen + SQL_Search_Negeri +
//			"ORDER BY M.TARIKH_MESYUARAT";
			
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			String Mesyuarat_Dari = "", Mesyuarat_Hingga = "", WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
			while (rs.next()) {
				WaktuMesyuarat = rs.getString(10) == null ? "" : rs.getString(10);
				if (!"".equalsIgnoreCase(WaktuMesyuarat)) {
					if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " AM";
					} else if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " PM";
					} else {
						WaktuMesyuarat = " PM";
					}
				}
				
				Mesyuarat_Dari = rs.getString(6) == null ? "" : rs.getString(6);
				Mesyuarat_Hingga = rs.getString(7) == null ? "" : rs.getString(7);
				if (Mesyuarat_Dari.length() < 4) {
					Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
					WAKTU_MESYUARAT_DARI = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
					WAKTU_MESYUARAT_DARI = " PM";
				} else {
					WAKTU_MESYUARAT_DARI = " PM";
				}
				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
					WAKTU_MESYUARAT_HINGGA = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
					WAKTU_MESYUARAT_HINGGA = " PM";
				} else {
					WAKTU_MESYUARAT_HINGGA = " PM";
				}
				Mesyuarat_Dari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
				Mesyuarat_Hingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
				
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListIDMesyuarat", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListUrusetiaUnit", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("ListBil", rs.getString(3) == null ? "" : rs.getString(3));
				h.put("ListTajuk", rs.getString(4) == null ? "" : rs.getString(4));
				h.put("ListTarikh", rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5)));
				h.put("ListMasa", Mesyuarat_Dari + " - " + Mesyuarat_Hingga);
				h.put("ListLokasi", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("ListStatus", rs.getString(9) == null ? "" : rs.getString(9));
				h.put("ListNoFail", rs.getString(10) == null ? "" : rs.getString(10));
				listMesyuarat.add(h);
				ListNo++;
			}
			rs.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listMesyuarat;
	}
	

	@SuppressWarnings("unchecked")
	public Vector getMesyuarat(String ID_MESYUARAT) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listAgenda = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				sql = "SELECT BIL_MESYUARAT, TAJUK_MESYUARAT, TARIKH_MESYUARAT FROM TBLPFDMESYUARAT WHERE ID_STATUS != 4 AND ID_MESYUARAT = " + ID_MESYUARAT;
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("Mesyuarat_Bil", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("Mesyuarat_Tajuk", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Mesyuarat_Tarikh", rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3)));
					listAgenda.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listAgenda;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMesyuaratMinit(String ID_MINIT) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMinit = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MINIT)) {
				sql = "SELECT M.MINIT_MESYUARAT, C.ID_AGENDAMESYUARAT, M.NO_MINIT, M.TAJUK_MINIT "+
					"FROM TBLPFDMESYUARATMINIT M, TBLPFDMESYUARATAGENDA C WHERE M.ID_AGENDAMESYUARAT = C.ID_AGENDAMESYUARAT " +
					"AND M.STATUS_MINIT = 1 AND M.ID_MINITMESYUARAT = " + ID_MINIT;
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("Mesyuarat_Minit", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("Mesyuarat_Agenda", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Mesyuarat_NoMinit", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("Mesyuarat_TajukMinit", rs.getString(4) == null ? "" : rs.getString(4));
					listMinit.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listMinit;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMesyuaratSubMinit(String ID_SUBMINIT) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listSubMinit = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_SUBMINIT)) {
				sql = "SELECT M.SUBMINIT_MESYUARAT, M.ID_AGENDAMESYUARAT, M.ID_MINITMESYUARAT, M.TINDAKAN_MESYUARAT, M.MAKLUMAN_MESYUARAT, M.NO_SUBMINIT, M.TAJUK_SUBMINIT " +
					"FROM TBLPFDMESYUARATSUBMINIT M, TBLPFDMESYUARATAGENDA C1, TBLPFDMESYUARATMINIT C2 WHERE M.ID_AGENDAMESYUARAT = C1.ID_AGENDAMESYUARAT " +
					"AND M.ID_MINITMESYUARAT = C2.ID_MINITMESYUARAT AND M.STATUS_SUBMINIT = 1 AND M.ID_SUBMINITMESYUARAT = " + ID_SUBMINIT;
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					h = new Hashtable();
					h.put("Mesyuarat_SubMinit", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("Mesyuarat_Agenda", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Mesyuarat_Minit", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("Mesyuarat_Tindakan", rs.getString(4) == null ? "" : rs.getString(4));
					h.put("Mesyuarat_Makluman", rs.getString(5) == null ? "" : rs.getString(5));
					h.put("Mesyuarat_NoSubMinit", rs.getString(6) == null ? "" : rs.getString(6));
					h.put("Mesyuarat_TajukSubMinit", rs.getString(7) == null ? "" : rs.getString(7));
					listSubMinit.add(h);
				}
				rs.close();
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listSubMinit;
	}
	
	public static String getSOCMesyuaratAgenda(String SOC_NAME, String ID_MESYUARAT) throws Exception {
		return getSOCMesyuaratAgenda(SOC_NAME, ID_MESYUARAT, null, null, null);
	}
	
	public static String getSOCMesyuaratAgenda(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA) throws Exception {
		return getSOCMesyuaratAgenda(SOC_NAME, ID_MESYUARAT, SELECTED_AGENDA, null, null);
	}
	
	public static String getSOCMesyuaratAgenda(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA, String DISABLED) throws Exception {
		return getSOCMesyuaratAgenda(SOC_NAME, ID_MESYUARAT, SELECTED_AGENDA, DISABLED, null);
	}
	
	public static String getSOCMesyuaratAgenda(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA, String DISABLED, String ONCHANGE) throws DbException, Exception {
		String sql = "";
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				String ID = "", TEXT = "";
				sql = "SELECT ID_AGENDAMESYUARAT, TAJUK_AGENDA FROM TBLPFDMESYUARATAGENDA WHERE ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY ID_AGENDAMESYUARAT";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SOC += "<select name=\"" + SOC_NAME + "\"" + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
				SOC += "<option value=\"-1\">SILA PILIH</option\r\n";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					TEXT = rs.getString(2) == null ? "" : rs.getString(2);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (ID.equalsIgnoreCase(SELECTED_AGENDA)) {
							SOC += "<option value=\"" + ID + "\" selected=\"selected\">" + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + ID + "\">" + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return SOC;
	}
	
	public static String getSOCMesyuaratMinit(String SOC_NAME, String ID_MESYUARAT) throws Exception {
		return getSOCMesyuaratMinit(SOC_NAME, ID_MESYUARAT, null, null, null);
	}
	
	public static String getSOCMesyuaratMinit(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA) throws Exception {
		return getSOCMesyuaratMinit(SOC_NAME, ID_MESYUARAT, SELECTED_AGENDA, null, null);
	}
	
	public static String getSOCMesyuaratMinit(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA, String SELECTED_MINIT) throws Exception {
		return getSOCMesyuaratMinit(SOC_NAME, ID_MESYUARAT, SELECTED_AGENDA, SELECTED_MINIT, null);
	}

	public static String getSOCMesyuaratMinit(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA, String SELECTED_MINIT, String DISABLED) throws Exception {
		return getSOCMesyuaratMinit(SOC_NAME, ID_MESYUARAT, SELECTED_AGENDA, SELECTED_MINIT, null, null);
	}

	public static String getSOCMesyuaratMinit(String SOC_NAME, String ID_MESYUARAT, String SELECTED_AGENDA, String SELECTED_MINIT, String DISABLED, String ONCHANGE) throws DbException, Exception {
		String sql = "";
		String SOC = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				String ID = "", TEXT = "";
				if (!"".equalsIgnoreCase(SELECTED_AGENDA)) {
					sql = "SELECT ID_MINITMESYUARAT, TAJUK_MINIT FROM TBLPFDMESYUARATMINIT WHERE STATUS_MINIT = 1 AND ID_MESYUARAT = " + ID_MESYUARAT + " AND ID_AGENDAMESYUARAT = " + SELECTED_AGENDA + " ORDER BY ID_MINITMESYUARAT";
				} else {
					sql = "SELECT ID_MINITMESYUARAT, TAJUK_MINIT FROM TBLPFDMESYUARATMINIT WHERE STATUS_MINIT = 1 AND ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY ID_MINITMESYUARAT";
				}
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SOC += "<select name=\"" + SOC_NAME + "\"" + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
				SOC += "<option value=\"-1\">SILA PILIH</option\r\n";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID = rs.getString(1) == null ? "" : rs.getString(1);
					TEXT = rs.getString(2) == null ? "" : rs.getString(2);
					if (!"".equalsIgnoreCase(ID) && !"".equalsIgnoreCase(TEXT)) {
						if (ID.equalsIgnoreCase(SELECTED_MINIT)) {
							SOC += "<option value=\"" + ID + "\" selected=\"selected\">" + TEXT + "</option>\r\n";
						} else {
							SOC += "<option value=\"" + ID + "\">" + TEXT + "</option>\r\n";
						}
					}
				}
				rs.close();
				SOC += "</select>\r\n";
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return SOC;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuaratDetails(String TYPE_MINIT, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector list = new Vector();
		Hashtable h = null;
		String FIELD_ID, FIELD_TEXT, TABLE_NAME, ADDITIONAL_WHERE, ORDER_BY;
		if ("1".equalsIgnoreCase(TYPE_MINIT)) {
			FIELD_ID = "ID_MESYUARAT";
			FIELD_TEXT = "ID_AGENDAMESYUARAT, AGENDA_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATAGENDA";
			ADDITIONAL_WHERE = "";
			ORDER_BY = " ORDER BY ID_AGENDAMESYUARAT";
		} else if ("2".equalsIgnoreCase(TYPE_MINIT)) {
			FIELD_ID = "MM.ID_MESYUARAT";
			FIELD_TEXT = "MM.ID_MINITMESYUARAT, MA.AGENDA_MESYUARAT, MM.MINIT_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATMINIT MM, TBLPFDMESYUARATAGENDA MA";
			ADDITIONAL_WHERE = " AND MM.ID_AGENDAMESYUARAT = MA.ID_AGENDAMESYUARAT";
			ORDER_BY = " ORDER BY MM.ID_MINITMESYUARAT, MM.ID_AGENDAMESYUARAT";
		} else {
			FIELD_ID = "MS.ID_MESYUARAT";
			FIELD_TEXT = "MS.ID_SUBMINITMESYUARAT, MA.AGENDA_MESYUARAT, MM.MINIT_MESYUARAT, MS.SUBMINIT_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATSUBMINIT MS, TBLPFDMESYUARATAGENDA MA, TBLPFDMESYUARATMINIT MM";
			ADDITIONAL_WHERE = " AND MS.ID_AGENDAMESYUARAT = MA.ID_AGENDAMESYUARAT AND MM.ID_MINITMESYUARAT = MS.ID_MINITMESYUARAT";
			ORDER_BY = " ORDER BY MS.ID_SUBMINITMESYUARAT, MS.ID_AGENDAMESYUARAT, MS.ID_MINITMESYUARAT";
		}
		
		try {
			int ListNo = 1;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "SELECT " + FIELD_TEXT + " FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + ID_DETAILS + ADDITIONAL_WHERE + ORDER_BY;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListID", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListAgenda", rs.getString(2) == null ? "" : rs.getString(2));
				if ("2".equalsIgnoreCase(TYPE_MINIT)) {
					h.put("ListMinit", rs.getString(3) == null ? "" : rs.getString(3));
				} else if ("3".equalsIgnoreCase(TYPE_MINIT)) {
					h.put("ListMinit", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("ListSubMinit", rs.getString(4) == null ? "" : rs.getString(4));
				}
				list.add(h);
				ListNo++;
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return list;
	}
	
	public String saveMesyuaratDetails(String TYPE_MINIT, String ID_MESYUARAT, String ID_AGENDA, String ID_MINIT, String ID_DETAILS, String TEXT_DETAILS, String TAJUK, String TINDAKAN, String MAKLUMAN) throws DbException, Exception {
		String sql = "";
		String FIELD_ID, FIELD_TEXT, TABLE_NAME, SEQUENCE_NAME, FIELD_TAJUK;
		if ("1".equalsIgnoreCase(TYPE_MINIT)) {
			FIELD_ID = "ID_AGENDAMESYUARAT";
			FIELD_TEXT = "AGENDA_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATAGENDA";
			SEQUENCE_NAME = "TBLPFDMESYUARATAGENDA_SEQ";
			FIELD_TAJUK = "TAJUK_AGENDA";
		} else if ("2".equalsIgnoreCase(TYPE_MINIT)) {
			FIELD_ID = "ID_MINITMESYUARAT";
			FIELD_TEXT = "MINIT_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATMINIT";
			SEQUENCE_NAME = "TBLPFDMESYUARATMINIT_SEQ";
			FIELD_TAJUK = "TAJUK_MINIT";
		} else {
			FIELD_ID = "ID_SUBMINITMESYUARAT";
			FIELD_TEXT = "SUBMINIT_MESYUARAT";
			TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
			SEQUENCE_NAME = "TBLPFDMESYUARATSUBMINIT_SEQ";
			FIELD_TAJUK = "TAJUK_SUBMINIT";
		}
		Db db = new Db();
		
		try {
			Boolean haveData = false;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if (!"".equalsIgnoreCase(ID_DETAILS)) {
				sql = "SELECT " + FIELD_ID + " FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + ID_DETAILS;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
			}
			
			SQLRenderer r = new SQLRenderer();
			if (haveData) {
				// do update
				r.update(FIELD_ID, ID_DETAILS);
				if ("2".equalsIgnoreCase(TYPE_MINIT)) {
					r.add("ID_AGENDAMESYUARAT", ID_AGENDA);
				} else if ("3".equalsIgnoreCase(TYPE_MINIT)) {
					r.add("ID_AGENDAMESYUARAT", ID_AGENDA);
					r.add("ID_MINITMESYUARAT", ID_MINIT);
					r.add("TINDAKAN_MESYUARAT", TINDAKAN);
					r.add("MAKLUMAN_MESYUARAT", MAKLUMAN);
				}
				r.add(FIELD_TAJUK, TAJUK);
				r.add(FIELD_TEXT, TEXT_DETAILS);
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				// do insert
				long ID_INSERT = DB.getNextID(SEQUENCE_NAME);
				r.add(FIELD_ID, ID_INSERT);
				r.add("ID_MESYUARAT", ID_MESYUARAT);
				if ("2".equalsIgnoreCase(TYPE_MINIT)) {
					r.add("ID_AGENDAMESYUARAT", ID_AGENDA);
				} else if ("3".equalsIgnoreCase(TYPE_MINIT)) {
					r.add("ID_AGENDAMESYUARAT", ID_AGENDA);
					r.add("ID_MINITMESYUARAT", ID_MINIT);
					r.add("TINDAKAN_MESYUARAT", TINDAKAN);
					r.add("MAKLUMAN_MESYUARAT", MAKLUMAN);
				}
				r.add(FIELD_TAJUK, TAJUK);
				r.add(FIELD_TEXT, TEXT_DETAILS);
				sql = r.getSQLInsert(TABLE_NAME);
				ID_DETAILS = String.valueOf(ID_INSERT);
			}
			stmt.execute(sql);
			if ("1".equalsIgnoreCase(TYPE_MINIT)) {
				populateRunningNumber(TYPE_MINIT, ID_MESYUARAT);
 			} else if ("2".equalsIgnoreCase(TYPE_MINIT)) {
 				populateRunningNumber(TYPE_MINIT, ID_AGENDA);
 			} else {
 				populateRunningNumber(TYPE_MINIT, ID_MINIT);
 			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return ID_DETAILS;
	}
	
	public Boolean deleteMesyuaratAgenda(String ID_MESYUARAT, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		String FIELD_ID, TABLE_NAME;
		FIELD_ID = "ID_AGENDAMESYUARAT";
		TABLE_NAME = "TBLPFDMESYUARATAGENDA";
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			
			if (!"".equalsIgnoreCase(ID_DETAILS)) {
				ResultSet rs = null;
				String ID_POPULATE = "";
				sql = "SELECT ID_MESYUARAT FROM " + TABLE_NAME + " WHERE " + FIELD_ID + "=" + ID_DETAILS;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_POPULATE = rs.getString(1) == null ? "" : rs.getString(1);
				}
				rs.close();

				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATMINIT WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);
				sql = "DELETE FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);

				if ("".equalsIgnoreCase(ID_POPULATE)) {
					populateRunningNumber("1", ID_POPULATE);
				}
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return returnValue;
	}
	
	public Boolean deleteMesyuaratMinit(String ID_MESYUARAT, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		String FIELD_ID, TABLE_NAME;
		FIELD_ID = "ID_MINITMESYUARAT";
		TABLE_NAME = "TBLPFDMESYUARATMINIT";
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			
			if (!"".equalsIgnoreCase(ID_DETAILS)) {
				ResultSet rs = null;
				String ID_POPULATE = "";
				sql = "SELECT ID_AGENDAMESYUARAT FROM " + TABLE_NAME + " WHERE " + FIELD_ID + "=" + ID_DETAILS;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_POPULATE = rs.getString(1) == null ? "" : rs.getString(1);
				}
				rs.close();

				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);
				sql = "DELETE FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);

				if ("".equalsIgnoreCase(ID_POPULATE)) {
					populateRunningNumber("2", ID_POPULATE);
				}
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return returnValue;
	}
	
	public Boolean deleteMesyuaratSubMinit(String ID_MESYUARAT, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		String FIELD_ID, TABLE_NAME;
		FIELD_ID = "ID_SUBMINITMESYUARAT";
		TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			
			if (!"".equalsIgnoreCase(ID_DETAILS)) {
				ResultSet rs = null;
				String ID_POPULATE = "";
				sql = "SELECT ID_MINITMESYUARAT FROM " + TABLE_NAME + " WHERE " + FIELD_ID + "=" + ID_DETAILS;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_POPULATE = rs.getString(1) == null ? "" : rs.getString(1);
				}
				rs.close();

				sql = "DELETE FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + ID_DETAILS;
				returnValue = stmt.execute(sql);

				if (!"".equalsIgnoreCase(ID_POPULATE)) {
					populateRunningNumber("3", ID_POPULATE);
				}
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return returnValue;
	}
	
	public String getLastMesyuaratRunningNumber(String TYPE, String ID_DETAILS) throws DbException, Exception {
		String sql = "";
		String returnValue = "";
		Db db = new Db();
		
		String TABLE_NAME = "", FIELD_ID = "", FIELD_SUBQUERY = "";
		if ("1".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATAGENDA";
			FIELD_ID = "ID_AGENDAMESYUARAT";
			FIELD_SUBQUERY = "ID_MESYUARAT";
		} else if ("2".equalsIgnoreCase(TYPE)) {
			TABLE_NAME = "TBLPFDMESYUARATMINIT";
			FIELD_ID = "ID_MINITMESYUARAT";
			FIELD_SUBQUERY = "ID_AGENDAMESYUARAT";
		} else {
			TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
			FIELD_ID = "ID_SUBMINITMESYUARAT";
			FIELD_SUBQUERY = "ID_MINITMESYUARAT";
		}
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			int runningNumber = 0;
			
			if (!"".equalsIgnoreCase(ID_DETAILS)) {
				sql = "SELECT * FROM (SELECT ROWNUM AS BIL, " +
					"FROM " + TABLE_NAME + " A WHERE A." + FIELD_SUBQUERY + " = " + ID_DETAILS + " ORDER BY A." + FIELD_ID + ") " + 
					"ORDER BY BIL DESC";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					runningNumber = rs.getInt(1);
				}
				rs.close();
			}
			stmt.close();
			runningNumber = runningNumber + 1;
			returnValue = Integer.toString(runningNumber);
		} finally {
			if (db != null) {
				db.close();
			}
		}
		
		return returnValue;
	}
	
	public String getIDAgenda(String CHECK_TYPE, String CHECK_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "", TABLE_NAME = "", FIELD_ID = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if ("1".equalsIgnoreCase(CHECK_TYPE)) {
				TABLE_NAME = "TBLPFDMESYUARATMINIT";
				FIELD_ID = "ID_MINITMESYUARAT";
			} else {
				TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
				FIELD_ID = "ID_SUBMINITMESYUARAT";
			}
			
			if (!"".equalsIgnoreCase(CHECK_ID)) {
				sql = "SELECT ID_AGENDAMESYUARAT FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + CHECK_ID;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getIDMinit(String CHECK_TYPE, String CHECK_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "", TABLE_NAME = "", FIELD_ID = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if ("1".equalsIgnoreCase(CHECK_TYPE)) {
				TABLE_NAME = "TBLPFDMESYUARATMINIT";
				FIELD_ID = "ID_MINITMESYUARAT";
			} else {
				TABLE_NAME = "TBLPFDMESYUARATSUBMINIT";
				FIELD_ID = "ID_SUBMINITMESYUARAT";
			}
			
			if (!"".equalsIgnoreCase(CHECK_ID)) {
				sql = "SELECT ID_MINITMESYUARAT FROM " + TABLE_NAME + " WHERE " + FIELD_ID + " = " + CHECK_ID;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	public Boolean checkfailwujud(String mesyuarat_NoFail) throws SQLException, DbException {
		// TODO Auto-generated method stub
		Boolean  returnValue =false;
		String no_fail = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
		
			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE NO_FAIL = '" + mesyuarat_NoFail + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
					no_fail =  rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
			}
			
			if ("".equalsIgnoreCase(no_fail)){
				returnValue = false;
			}else{				
				returnValue = true;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	
	public static void setSeksyen(String user_id) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			  paparSeksyen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		      h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		      h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		      paparSeksyen.addElement(h); 
		      }
		      
		}
		finally {
		      if (db != null) db.close();
		    }  


		}
	public static Vector getDataSeksyen() {
		// TODO Auto-generated method stub
		return paparSeksyen;
	}
	public void email(String id_mesyuarat, Hashtable h) throws DbException, SQLException {
		// TODO Auto-generated method stub
		Db db = null;
		String sql = "";
		String email = "";
		Vector k = new Vector(); 
		
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT EMAIL FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT = '"+id_mesyuarat+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
//		      Hashtable k = new Hashtable();
		      while(rs.next()) {
		    	  email = rs.getString("EMAIL");
		    	  if(email != null && email !=""){
			    	  k.addElement(email);		    		  
		    	  }

		      }
		      
		      System.out.println("LIST EMEL :"+k);
		      
		      ProcesEmail(k,h);
		      
		}
		finally {
		      if (db != null) db.close();
		    }
	}
	private void ProcesEmail(Vector k, Hashtable h) {
		// TODO Auto-generated method stub
		//declaration
        String Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "";
        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Seksyen = "", Mesyuarat_NoFailDiperlukan = "", Mesyuarat_DisediakanOleh = "";
        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "", Mesyuarat_NoFail = "";
        String Mesyuarat_ValueLokasi = "", Mesyuarat_ValueSeksyen = "", Mesyuarat_ValueStatus = "", Mesyuarat_ValueDari = "", Mesyuarat_ValueHingga = "";
        String Mesyuarat_TempahMakananPemohon = "", Mesyuarat_TempahMakananBilAhli = "", Mesyuarat_TempahMakananMakanan = "", Mesyuarat_TempahMakananMinuman = "";

        //extract data h
        Mesyuarat_Tajuk = String.valueOf(h.get("Mesyuarat_Tajuk"));
        Mesyuarat_Tarikh = String.valueOf(h.get("Mesyuarat_Tarikh"));
        Mesyuarat_Dari = String.valueOf(h.get("Mesyuarat_Dari"));
        Mesyuarat_Hingga =String.valueOf(h.get("Mesyuarat_Hingga"));
        Mesyuarat_Lokasi = String.valueOf(h.get("Mesyuarat_Lokasi"));
        Mesyuarat_Seksyen = String.valueOf(h.get("Mesyuarat_Seksyen"));
        Mesyuarat_ValueLokasi = String.valueOf(h.get("Mesyuarat_ValueLokasi"));
        Mesyuarat_ValueDari = String.valueOf(h.get("Mesyuarat_ValueDari"));
        Mesyuarat_ValueHingga = String.valueOf(h.get("Mesyuarat_ValueHingga"));
        Mesyuarat_Catatan = String.valueOf(h.get("Mesyuarat_ValueHingga"));

        //extract data k
        
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		email.MULTIPLE_RECIEPIENT = new String[k.size()];
        for(int i = 0 ; i < k.size(); i++){
        	System.out.println("EMEL MASUK :"+(String)k.elementAt(i));
        	
        	
        	if((String)k.elementAt(i)!=null)
        	{
        	email.MULTIPLE_RECIEPIENT[i] = (String)k.elementAt(i); 
        	}
        }      
		email.FROM ="etapp_webmaster@ekptg.gov.my";
		email.MESSAGE =
			
			"Dato'/Tuan/Puan</BR>"+
			"<BR></BR>"+
			"<BR>"+
			"Dimaklumkan bahawa " + Mesyuarat_Tajuk + " akan diadakan pada ketetapan berikut :"+ 
				"<BR><BR><BR>MESYUARAT : <B>"+Mesyuarat_Tajuk+"</B>"+
				"<BR>TARIKH : <B>"+ Mesyuarat_Tarikh +" </B>" +
				"<BR>MASA : <B>"+ Mesyuarat_ValueDari +" - "+Mesyuarat_ValueHingga+"</B>"+
				"<BR>TEMPAT : <B>"+Mesyuarat_ValueLokasi+"</B>" +
				"<BR><BR>Untuk keterangan lanjut sila layari www.etapp.gov.my" +
				"<BR><BR>Sekian, terima kasih." ;
				//"<BR><BR><BR><BR> <em>Catatan : "+Mesyuarat_Catatan+"</em>";
		
		
		
		
	

		
		
		
		
		
		
			
		email.SUBJECT="PANGGILAN MESYUARAT : " + Mesyuarat_Tajuk;
		email.sendEmail();
	}
	
	
	
	public void emailminit(String id_mesyuarat, Hashtable h) throws DbException, SQLException {
		// TODO Auto-generated method stub
		Db db = null;
		String sql = "";
		String email = "";
		Vector k = new Vector(); 
		
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT EMAIL FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT = '"+id_mesyuarat+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
//		      Hashtable k = new Hashtable();
		      while(rs.next()) {
		    	  
		    	  
		    	  email = rs.getString("EMAIL");
		    	  if(email != null && email !="")
		    	  {
			    	  k.addElement(email);		    		  
		    	  }

		      }
		      
		      System.out.println("LIIIISTTT EMEL :"+k);
		      
		      ProcesEmailMinit(k,h);
		      
		}
		finally {
		      if (db != null) db.close();
		    }
	}
	private void ProcesEmailMinit(Vector k, Hashtable h) {
		// TODO Auto-generated method stub
		//declaration
        String Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "";
        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Seksyen = "", Mesyuarat_NoFailDiperlukan = "", Mesyuarat_DisediakanOleh = "";
        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "", Mesyuarat_NoFail = "";
        String Mesyuarat_ValueLokasi = "", Mesyuarat_ValueSeksyen = "", Mesyuarat_ValueStatus = "", Mesyuarat_ValueDari = "", Mesyuarat_ValueHingga = "";
        String Mesyuarat_TempahMakananPemohon = "", Mesyuarat_TempahMakananBilAhli = "", Mesyuarat_TempahMakananMakanan = "", Mesyuarat_TempahMakananMinuman = "";

        //extract data h
        Mesyuarat_Tajuk = String.valueOf(h.get("Mesyuarat_Tajuk"));
        Mesyuarat_Tarikh = String.valueOf(h.get("Mesyuarat_Tarikh"));
        Mesyuarat_Dari = String.valueOf(h.get("Mesyuarat_Dari"));
        Mesyuarat_Hingga =String.valueOf(h.get("Mesyuarat_Hingga"));
        Mesyuarat_Lokasi = String.valueOf(h.get("Mesyuarat_Lokasi"));
        Mesyuarat_Seksyen = String.valueOf(h.get("Mesyuarat_Seksyen"));
        Mesyuarat_ValueLokasi = String.valueOf(h.get("Mesyuarat_ValueLokasi"));
        Mesyuarat_ValueDari = String.valueOf(h.get("Mesyuarat_ValueDari"));
        Mesyuarat_ValueHingga = String.valueOf(h.get("Mesyuarat_ValueHingga"));
        Mesyuarat_Catatan = String.valueOf(h.get("Mesyuarat_ValueHingga"));

        //extract data k
        
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		email.MULTIPLE_RECIEPIENT = new String[k.size()];
		
		//email.MULTIPLE_RECIEPIENT = new String[1];
		
        for(int i = 0 ; i < k.size(); i++){
        	System.out.println("EMEL TEST :"+(String)k.elementAt(i));
        //	email.MULTIPLE_RECIEPIENT[i] = (String)k.elementAt(i); 
        	
        	if((String)k.elementAt(i)!=null)
        	{
        	email.MULTIPLE_RECIEPIENT[i] = (String)k.elementAt(i); 
        	}
        	
        } 
		
		
	//	email.MULTIPLE_RECIEPIENT[0] = "razman@hla-group.com"; 
		
		email.FROM ="etapp_webmaster@ekptg.gov.my";
	/*	email.MESSAGE ="Minit mesyuarat disediakan bagi " + Mesyuarat_Tajuk + " yang diadakan pada :"+ 
					"<BR><B>TARIKH : "+ Mesyuarat_Tarikh +" </B>" +
				"<BR><B>MASA : "+ Mesyuarat_ValueDari +" - "+Mesyuarat_ValueHingga+"</B>"+
				"<BR><B>TEMPAT : "+Mesyuarat_ValueLokasi+"</B>" +				
				"<BR>Bagi yang berkaitan, sila beri maklumbalas";*/
		
		
		email.MESSAGE = "Dato'/Tuan/Puan,</br>"
		+"<br></br>"
		+"<br>Pohon kerjasama untuk menyemak minit dan menyediakan maklumbalas</br>"
		+"<br>yang berkaitan diruang yang disediakan.</br>"
		+"<br>Kerjasama dan perhatian Dato'/Tuan/Puan amat dihargai.</br>"
		+"<br></br>"
		+"<br>Sekian, terima kasih.</br>";
		
		
		
			
		email.SUBJECT="PEMBERITAHUAN MINIT MENSYUARAT BAGI " + Mesyuarat_Tajuk+" TELAH DISEDIAKAN";
		email.sendEmail();
	}
	
	
	public  void deleteMesyuaratTemplateData(String ID_MESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARAT";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			/*
				
				sql = "DELETE FROM TBLPFDMESYUARATAGENDA WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATMINIT WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);*/
				
			    sql = "DELETE FROM TBLPFDAHLIMESYUARAT WHERE ID_MESYUARAT IN (SELECT ID_MESYUARAT FROM TBLPFDMESYUARAT WHERE ID_FAIL IN (SELECT ID_FAIL FROM TBLPFDMESYUARATTEMPLATE WHERE ID_MESYUARAT  IN (" + ID_MESYUARAT+ ")))";
		     	stmt.execute(sql);
			
				sql = "DELETE FROM TBLPFDMESYUARAT WHERE ID_FAIL IN (SELECT ID_FAIL FROM TBLPFDMESYUARATTEMPLATE WHERE ID_MESYUARAT  IN (" + ID_MESYUARAT+ "))";
				stmt.execute(sql);
				
				sql = "DELETE FROM TBLPFDMESYUARATTEMPLATE WHERE ID_MESYUARAT  IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);
				
			
		} finally {
			if (db != null)
				db.close();
		}
	
	}
	public void deleteMesyuaratDiCalendar(String id_mesyuarat) throws Exception {
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			/*
				
				sql = "DELETE FROM TBLPFDMESYUARATAGENDA WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATMINIT WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);
				sql = "DELETE FROM TBLPFDMESYUARATSUBMINIT WHERE ID_MESYUARAT IN (" + ID_MESYUARAT+ ")";
				stmt.execute(sql);*/
				
				
				sql = "DELETE FROM CALENDAR_EVENT WHERE ID_MESYUARAT = " + id_mesyuarat+ "";
				stmt.execute(sql);
				
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	public Boolean checkMesyuaratDiCalendar(String id_mesyuarat) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("id_mesyuarat");
			r.add("id_mesyuarat",id_mesyuarat);
			sql = r.getSQLSelect("CALENDAR_EVENT");
			r.clear();
			rs = stmt.executeQuery(sql);
			returnValue = rs.next();
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
		
	}

	
	
	public void copy_mesyuarat(String id_fail,long id_mesyuarat_baru,Db db,HttpSession session) throws Exception {
		
	
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String id_mesyuarat_lama = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				
			
			Vector getidmesyuarat_lama = getidmesyuarat_lama(id_fail);		
			
			if(getidmesyuarat_lama.size()>0)
			{
			Hashtable d = (Hashtable) getidmesyuarat_lama.get(0);
			id_mesyuarat_lama = d.get("ID_MESYUARAT").toString();
			
			Vector mesyuarat_lama = null;
			mesyuarat_lama = getSenaraiAgenda(id_mesyuarat_lama);
			if (mesyuarat_lama.size() != 0){
				for (int i = 0; i < mesyuarat_lama.size(); i++){
					
					Hashtable agenda = (Hashtable) mesyuarat_lama.get(i);
					String ID_AGENDAMESYUARAT = "";
					if (agenda.get("ID_AGENDAMESYUARAT") != null)
						ID_AGENDAMESYUARAT = (String) agenda.get("ID_AGENDAMESYUARAT");
						copyAGENDA(ID_AGENDAMESYUARAT,id_fail, id_mesyuarat_lama,id_mesyuarat_baru,db,userId);
					}
			}
			}
			
		}
			 catch (SQLException ex) { 
			    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
			    }	 finally {
			if (db != null)
				db.close();
		}
	    
	}
	
	
	private Vector getSenaraiAgenda(String id_mesyuarat) throws Exception {
		Db db = null;
		String sql = "";
		Vector listAgenda = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

		//	sql = "SELECT ID_TANAHGANTI, ID_HAKMILIKAGENSI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			
			sql = " SELECT T.ID_AGENDAMESYUARAT, T.ID_MESYUARAT, T.TARIKH_MASUK, "+
			      " T.TARIKH_KEMASKINI, T.NO_AGENDA, T.TAJUK_AGENDA,T.AGENDA_MESYUARAT, T.ID_MASUK, T.ID_KEMASKINI "+
			      " FROM TBLPFDMESYUARATAGENDA T WHERE T.ID_MESYUARAT = '"+id_mesyuarat+"'";
			
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_AGENDAMESYUARAT", rs.getString("ID_AGENDAMESYUARAT") == null ? "" : rs.getString("ID_AGENDAMESYUARAT"));
				listAgenda.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listAgenda;
	}
	
	
	
	public Vector getidmesyuarat_lama(String id_fail) throws Exception {
	    Db db = null;
	    String sql = "";
		Vector getidmesyuarat_lama = new Vector();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	     

	      sql = "" +
	      " SELECT DISTINCT MS.ID_MESYUARAT " +
	      " FROM TBLPFDMESYUARATTEMPLATE TEM,TBLPFDMESYUARAT MS " +
	      " WHERE TEM.ID_FAIL = MS.ID_FAIL  "+	    
	      " AND TEM.ID_FAIL = '"+id_fail+"' "+
	      " AND MS.BIL_MESYUARAT = "+
	      " (SELECT MAX(X.BIL_MESYUARAT) FROM TBLPFDMESYUARAT X,TBLPFDMESYUARATTEMPLATE Y "+
	      " WHERE X.ID_FAIL = Y.ID_FAIL  AND Y.ID_FAIL = '"+id_fail+"')--GET ID_TEMPLETE ";
	    
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("ID_MESYUARAT", rs.getString("ID_MESYUARAT")==null?"":rs.getString("ID_MESYUARAT"));
		    	getidmesyuarat_lama.addElement(h);
	      }
	      return getidmesyuarat_lama;

	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	
	private void copyAGENDA(String ID_AGENDAMESYUARAT_LAMA,String id_fail, String id_mesyuarat_lama, long id_mesyuarat_baru,  Db db, String userId) throws Exception {

		String sql = "";		
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPTANAHGANTIPLPSN
			r = new SQLRenderer();
			long TBLPFDMESYUARATAGENDA = DB.getNextID("TBLPFDMESYUARATAGENDA_SEQ");
			
			
			sql = " INSERT INTO TBLPFDMESYUARATAGENDA ("+
				"ID_AGENDAMESYUARAT, ID_MESYUARAT, TARIKH_MASUK,TARIKH_KEMASKINI, NO_AGENDA,TAJUK_AGENDA,AGENDA_MESYUARAT, ID_MASUK, ID_KEMASKINI) "
			    + " SELECT DISTINCT " +
						" "+TBLPFDMESYUARATAGENDA+", "+id_mesyuarat_baru+",SYSDATE,SYSDATE, T.NO_AGENDA, T.TAJUK_AGENDA,T.AGENDA_MESYUARAT,"+userId+", "+userId+" "
				+ "  FROM TBLPFDMESYUARATAGENDA T "
				+ " WHERE  T.ID_AGENDAMESYUARAT  = '"+ID_AGENDAMESYUARAT_LAMA+"'";
			

			stmt.executeUpdate(sql);
					
			
			Vector listSenaraiMinit = null;
			listSenaraiMinit = getSenaraiMinit(ID_AGENDAMESYUARAT_LAMA);
			if (listSenaraiMinit.size() != 0){
				for (int i = 0; i < listSenaraiMinit.size(); i++){
					
					Hashtable hashDokumen = (Hashtable) listSenaraiMinit.get(i);
					String idMinitLama = "";
					if (hashDokumen.get("ID_MINITMESYUARAT") != null)
						idMinitLama = (String) hashDokumen.get("ID_MINITMESYUARAT");
					
					copyMINIT(idMinitLama,TBLPFDMESYUARATAGENDA,id_fail, id_mesyuarat_lama, id_mesyuarat_baru,db, userId);
					
				}
			}
			
				
		} catch (SQLException ex) { 
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    }		
	}
	
	private void copyMINIT(String idMinitLama,long id_agenda_baru,String id_fail,String id_mesyuarat_lama,long id_mesyuarat_baru,Db db,String userId)  throws Exception {
	
	String sql = "";		
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r = new SQLRenderer();
			long ID_MINITMESYUARAT = DB.getNextID("TBLPFDMESYUARATMINIT_SEQ");
			
			sql = "INSERT INTO TBLPFDMESYUARATMINIT ( "+
					 "  ID_MINITMESYUARAT, ID_MESYUARAT, ID_AGENDAMESYUARAT, "+
					 "  TARIKH_MASUK, TARIKH_KEMASKINI, NO_MINIT, "+
					 "  TAJUK_MINIT, MINIT_MESYUARAT, TINDAKAN, "+
					 "  MAKLUMAN, MAKLUMBALAS, ID_MASUK, "+
					 "  ID_KEMASKINI, MINIT_MAKLUMBALAS) ";
			
			 sql += "SELECT DISTINCT "+ID_MINITMESYUARAT+", "+id_mesyuarat_baru+","+id_agenda_baru+",SYSDATE, " +
			 		" SYSDATE, T.NO_MINIT,T.TAJUK_MINIT, T.MINIT_MESYUARAT, T.TINDAKAN, T.MAKLUMAN, " +
			 		" T.MAKLUMBALAS, "+userId+","+userId+", T.MINIT_MAKLUMBALAS "+
			 " FROM TBLPFDMESYUARATMINIT T WHERE  T.ID_MINITMESYUARAT = '"+idMinitLama+"'";
		    
			

			stmt.executeUpdate(sql);
						
			
			Vector listSenaraiSubMinit = null;
			listSenaraiSubMinit = getSenaraiSubMinit(idMinitLama);
			if (listSenaraiSubMinit.size() != 0){
				for (int i = 0; i < listSenaraiSubMinit.size(); i++){
					
					Hashtable hashDokumen = (Hashtable) listSenaraiSubMinit.get(i);
					String idSubMinitLama = "";
					if (hashDokumen.get("ID_SUBMINITMESYUARAT") != null)
						idSubMinitLama = (String) hashDokumen.get("ID_SUBMINITMESYUARAT");
					
					copySUBMINIT(idSubMinitLama,ID_MINITMESYUARAT,id_fail, id_mesyuarat_lama, id_mesyuarat_baru,db, userId,id_agenda_baru);
					
				}
			}
			
			
		} catch (SQLException ex) { 
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    }		
	}
	
	
	private void copySUBMINIT(String idSubMinitLama,long id_minit_baru,String id_fail,String id_mesyuarat_lama,long id_mesyuarat_baru,Db db,String userId,long id_agenda_baru)  throws Exception {
		
		String sql = "";		
			try {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r = new SQLRenderer();
				long ID_SUBMINITMESYUARAT = DB.getNextID("TBLPFDMESYUARATSUBMINIT_SEQ");			
				
				sql += "INSERT INTO TBLPFDMESYUARATSUBMINIT ( "+
					  "  ID_SUBMINITMESYUARAT, ID_MESYUARAT, ID_AGENDAMESYUARAT, "+
					  "  ID_MINITMESYUARAT, TARIKH_MASUK, TARIKH_KEMASKINI, "+
					  "  NO_SUBMINIT, TAJUK_SUBMINIT, SUBMINIT_MESYUARAT, "+
					  "  TINDAKAN, MAKLUMAN, MAKLUMBALAS,  "+
					  "   ID_MASUK, ID_KEMASKINI, SUBMINIT_MAKLUMBALAS) ";			    
				
				sql += " SELECT "+ID_SUBMINITMESYUARAT+", "+id_mesyuarat_baru+", "+id_agenda_baru+", "+
				      " T.ID_MINITMESYUARAT,SYSDATE,SYSDATE, "+
				      " T.NO_SUBMINIT, T.TAJUK_SUBMINIT, T.SUBMINIT_MESYUARAT, "+
				      " T.TINDAKAN, T.MAKLUMAN, T.MAKLUMBALAS, "+
				      " "+userId+", "+userId+", T.SUBMINIT_MAKLUMBALAS "+
				      " FROM TBLPFDMESYUARATSUBMINIT T WHERE T.ID_SUBMINITMESYUARAT = "+idSubMinitLama+"";
				
				System.out.println("SQL COPY SUB MINIT :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
							
				
			
				
			} catch (SQLException ex) { 
		    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
		    }		
		}
		
	
	
	
	private Vector getSenaraiMinit(String id_agenda_lama) throws Exception {
		Db db = null;
		String sql = "";
		Vector ID_MINITMESYUARAT = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_MINITMESYUARAT FROM TBLPFDMESYUARATMINIT WHERE ID_AGENDAMESYUARAT = '" + id_agenda_lama + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_MINITMESYUARAT", rs.getString("ID_MINITMESYUARAT") == null ? "" : rs.getString("ID_MINITMESYUARAT"));
				ID_MINITMESYUARAT.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return ID_MINITMESYUARAT;
	}
	
	private Vector getSenaraiSubMinit(String id_minit_lama) throws Exception {
		Db db = null;
		String sql = "";
		Vector ID_SUBMINITMESYUARAT = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBMINITMESYUARAT FROM TBLPFDMESYUARATSUBMINIT WHERE ID_MINITMESYUARAT = '" + id_minit_lama + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBMINITMESYUARAT", rs.getString("ID_SUBMINITMESYUARAT") == null ? "" : rs.getString("ID_SUBMINITMESYUARAT"));
				ID_SUBMINITMESYUARAT.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return ID_SUBMINITMESYUARAT;
	}
	
	
	
	public Vector getMaklumatPegawai(String id_users) throws Exception {
		Db db = null;
		String sql = "";
		Vector getMaklumatPegawai = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UI.EMEL,J.KETERANGAN FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J "+
					" WHERE U.USER_ID = UI.USER_ID "+
					" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND U.USER_ID = '"+id_users+"'";

				
				
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("EMEL", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				getMaklumatPegawai.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return getMaklumatPegawai;
	}

	
	public Vector getMaklumatKategori(String id_ahli) throws Exception {
		Db db = null;
		String sql = "";
		Vector getMaklumatKategori = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KATEGORI_AHLI FROM TBLPFDAHLIMESYUARAT WHERE ID_AHLIMESYUARAT = '"+id_ahli+"'";

				
				
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("KATEGORI_AHLI", rs.getString("KATEGORI_AHLI") == null ? "" : rs.getString("KATEGORI_AHLI"));
				//h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				getMaklumatKategori.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return getMaklumatKategori;
	}


	public static void setListPegawai(String user_id,String user_negeri) throws Exception {
	    Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	  /*    
	      sql = "select user_id,user_name from users where user_id in " +
	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
	      		"and id_jawatan in (0,1,2,3,4,5,6))";

	      sql = "select user_id,user_name from users where user_id in " +
    		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
    		"and id_jawatan in (0,1,2,3,4,5,6))";
    		
    		*/
	      
	      sql =  " SELECT U.user_id,initcap(U.user_name) as user_name FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') ";	   
	      
	      
	      sql += "ORDER BY U.USER_NAME ASC ";

	      
	      System.out.println("SQL USER PENGARAH ::"+sql.toUpperCase());
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

			  senaraiPegawai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListPegawai() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}




	public Vector getDataPegawaiMinit(String id_minit) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 Vector paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMESYUARATMINIT A, USERS B WHERE B.USER_ID = A.IDPEGAWAI_TINDAKAN AND A.ID_MINITMESYUARAT = '"+id_minit+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public Vector getDataPegawaiSubMinit(String id_subminit) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 Vector paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMESYUARATSUBMINIT A, USERS B WHERE B.USER_ID = A.IDPEGAWAI_TINDAKAN AND A.ID_SUBMINITMESYUARAT = '"+id_subminit+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListMesyuaratMyInfo(String user_id) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector list = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_SEKSYEN, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
				"FROM TBLPFDMESYUARAT M, TBLRUJSEKSYEN RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_SEKSYEN = RS.ID_SEKSYEN(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
				"AND M.ID_MESYUARAT IN (SELECT ID_MESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PEGAWAI = "+user_id+")"+
				//"AND M.TARIKH_MESYUARAT >= sysdate "+
				"ORDER BY M.TARIKH_MESYUARAT";
			
			
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			String Mesyuarat_Dari = "", Mesyuarat_Hingga = "", WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
			while (rs.next()) {
				WaktuMesyuarat = rs.getString(10) == null ? "" : rs.getString(10);
				if (!"".equalsIgnoreCase(WaktuMesyuarat)) {
					if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " AM";
					} else if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " PM";
					} else {
						WaktuMesyuarat = " PM";
					}
				}
				
				Mesyuarat_Dari = rs.getString(6) == null ? "" : rs.getString(6);
				Mesyuarat_Hingga = rs.getString(7) == null ? "" : rs.getString(7);
				if (Mesyuarat_Dari.length() < 4) {
					Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
					WAKTU_MESYUARAT_DARI = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
					WAKTU_MESYUARAT_DARI = " PM";
				} else {
					WAKTU_MESYUARAT_DARI = " PM";
				}
				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
					WAKTU_MESYUARAT_HINGGA = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
					WAKTU_MESYUARAT_HINGGA = " PM";
				} else {
					WAKTU_MESYUARAT_HINGGA = " PM";
				}
				Mesyuarat_Dari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
				Mesyuarat_Hingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListIDMesyuarat", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListUrusetiaSeksyen", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("ListBil", rs.getString(3) == null ? "" : rs.getString(3));
				h.put("ListTajuk", rs.getString(4) == null ? "" : rs.getString(4));
				h.put("ListTarikh", rs.getDate(5) == null ? "" : sdf1.format(rs.getDate(5)));
				h.put("ListMasa", Mesyuarat_Dari + " - " + Mesyuarat_Hingga);
				h.put("ListLokasi", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("ListStatus", rs.getString(9) == null ? "" : rs.getString(9));
				h.put("ListNoFail", rs.getString(10) == null ? "" : rs.getString(10));
				list.add(h);
				ListNo++;
			}
			rs.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return list;
	}

}
