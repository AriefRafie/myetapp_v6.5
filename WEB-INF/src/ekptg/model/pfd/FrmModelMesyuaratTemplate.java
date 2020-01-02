package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmModelMesyuaratTemplate {
	
	private static Vector paparNegeri = null;
	private static Vector paparUnit = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("-?\\d+(.\\d+)?");
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

		        String Mesyuarat_NoRujukanMesyuarat = "", Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "", Mesyuarat_Kategori ="";
		        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Seksyen = "", Mesyuarat_NoFail = "", Mesyuarat_DisediakanOleh = "";
		        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "";
		        String Mesyuarat_ValueLokasi = "", Mesyuarat_ValueSeksyen = "", Mesyuarat_ValueStatus = "", Mesyuarat_ValueDari = "", Mesyuarat_ValueHingga = "";
		        
				sql = "SELECT M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, M.ID_LOKASI, M.ID_STATUS, M.ID_SEKSYEN, M.NO_FAIL_DIPERLUKAN, M.DISEDIAKAN_OLEH, M.DISEMAK_OLEH, M.DISAHKAN_OLEH, M.CATATAN, L.LOKASI, SK.NAMA_SEKSYEN, ST.STATUS_MESYUARAT, FAIL.NO_FAIL, M.KATEGORI_MESYUARAT " +
					"FROM TBLPFDMESYUARATTEMPLATE M, TBLPFDRUJLOKASIMESYUARAT L, TBLPFDRUJSTATUSMESYUARAT ST, TBLRUJSEKSYEN SK, TBLPFDFAIL FAIL " + 
					"WHERE M.ID_LOKASI = L.ID_LOKASI(+) AND M.ID_STATUS = ST.ID_STATUS(+) AND M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_FAIL = FAIL.ID_FAIL " + 
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
					Mesyuarat_NoFail = rs.getString(9) == null ? "" : rs.getString(9);
					Mesyuarat_DisediakanOleh = rs.getString(10) == null ? "" : rs.getString(10);
					Mesyuarat_DisemakOleh = rs.getString(11) == null ? "" : rs.getString(11);
					Mesyuarat_DisahkanOleh = rs.getString(12) == null ? "" : rs.getString(12);
					Mesyuarat_Catatan = rs.getString(13) == null ? "" : rs.getString(13);
					Mesyuarat_ValueLokasi = rs.getString(14) == null ? "" : rs.getString(14);
					Mesyuarat_ValueSeksyen = rs.getString(15) == null ? "" : rs.getString(15);
					Mesyuarat_ValueStatus = rs.getString(16) == null ? "" : rs.getString(16);
					Mesyuarat_NoRujukanMesyuarat = rs.getString(17) == null ? "" : rs.getString(17);
					Mesyuarat_Kategori = rs.getString(18) == null ? "" : rs.getString(18);

					String WAKTU_MESYUARAT_DARI = "";
					String WAKTU_MESYUARAT_HINGGA = "";
					
					if (!"".equals(Mesyuarat_Dari) &&!"".equals(Mesyuarat_Hingga)){
					if (Mesyuarat_Dari.length() < 4) {
						Mesyuarat_Dari = "0" + Mesyuarat_Dari;
					}
					if (Mesyuarat_Hingga.length() < 4) {
						Mesyuarat_Hingga = "0" + Mesyuarat_Hingga;
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
					}
					
					h.put("Mesyuarat_NoRujukanMesyuarat", Mesyuarat_NoRujukanMesyuarat);
					h.put("Mesyuarat_Bil", Mesyuarat_Bil);
					h.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
					h.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
					h.put("Mesyuarat_Dari", Mesyuarat_Dari);
					h.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
					h.put("Mesyuarat_Lokasi", Mesyuarat_Lokasi);
					h.put("Mesyuarat_Status", Mesyuarat_Status);
					h.put("Mesyuarat_Seksyen", Mesyuarat_Seksyen);
					h.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
					h.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
					h.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
					h.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
					h.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
					h.put("Mesyuarat_ValueLokasi", Mesyuarat_ValueLokasi);
					h.put("Mesyuarat_ValueSeksyen", Mesyuarat_ValueSeksyen);
					h.put("Mesyuarat_ValueStatus", Mesyuarat_ValueStatus);
					h.put("Mesyuarat_ValueDari", Mesyuarat_ValueDari);
					h.put("Mesyuarat_ValueHingga", Mesyuarat_ValueHingga);
					h.put("Mesyuarat_Kategori", Mesyuarat_Kategori);
					
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
	public Vector getMesyuaratTemplateDataNegeri(String ID_MESYUARAT) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector vData = new Vector();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = new Hashtable();

		        String Mesyuarat_NoRujukanMesyuarat = "", Mesyuarat_Bil = "", Mesyuarat_Tajuk = "", Mesyuarat_Tarikh = "", Mesyuarat_Dari = "", Mesyuarat_Hingga = "", Mesyuarat_Kategori ="";
		        String Mesyuarat_Lokasi = "", Mesyuarat_Status = "", Mesyuarat_Unit = "", Mesyuarat_NoFail = "", Mesyuarat_DisediakanOleh = "";
		        String Mesyuarat_DisemakOleh = "", Mesyuarat_DisahkanOleh = "", Mesyuarat_Catatan = "";
		        String Mesyuarat_ValueLokasi = "", Mesyuarat_ValueUnit = "", Mesyuarat_ValueStatus = "", Mesyuarat_ValueDari = "", Mesyuarat_ValueHingga = "";
		        
				sql = "SELECT M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, M.ID_LOKASI, M.ID_STATUS, M.ID_UNIT, M.NO_FAIL_DIPERLUKAN, M.DISEDIAKAN_OLEH, M.DISEMAK_OLEH, M.DISAHKAN_OLEH, M.CATATAN, L.LOKASI, SK.NAMA_UNIT, ST.STATUS_MESYUARAT, FAIL.NO_FAIL, M.KATEGORI_MESYUARAT " +
					"FROM TBLPFDMESYUARATTEMPLATE M, TBLPFDRUJLOKASIMESYUARAT L, TBLPFDRUJSTATUSMESYUARAT ST, TBLRUJUNIT SK, TBLPFDFAIL FAIL " + 
					"WHERE M.ID_LOKASI = L.ID_LOKASI(+) AND M.ID_STATUS = ST.ID_STATUS(+) AND M.ID_UNIT = SK.ID_UNIT(+) AND M.ID_FAIL = FAIL.ID_FAIL " + 
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
					Mesyuarat_Unit = rs.getString(8) == null ? "" : rs.getString(8);
					Mesyuarat_NoFail = rs.getString(9) == null ? "" : rs.getString(9);
					Mesyuarat_DisediakanOleh = rs.getString(10) == null ? "" : rs.getString(10);
					Mesyuarat_DisemakOleh = rs.getString(11) == null ? "" : rs.getString(11);
					Mesyuarat_DisahkanOleh = rs.getString(12) == null ? "" : rs.getString(12);
					Mesyuarat_Catatan = rs.getString(13) == null ? "" : rs.getString(13);
					Mesyuarat_ValueLokasi = rs.getString(14) == null ? "" : rs.getString(14);
					Mesyuarat_ValueUnit = rs.getString(15) == null ? "" : rs.getString(15);
					Mesyuarat_ValueStatus = rs.getString(16) == null ? "" : rs.getString(16);
					Mesyuarat_NoRujukanMesyuarat = rs.getString(17) == null ? "" : rs.getString(17);
					Mesyuarat_Kategori = rs.getString(18) == null ? "" : rs.getString(18);
					//Mesyuarat_Unit = rs.getString(19) == null ? "" : rs.getString(19);

					String WAKTU_MESYUARAT_DARI = "";
					String WAKTU_MESYUARAT_HINGGA = "";
					
					if (!"".equals(Mesyuarat_Dari) &&!"".equals(Mesyuarat_Hingga)){
					if (Mesyuarat_Dari.length() < 4) {
						Mesyuarat_Dari = "0" + Mesyuarat_Dari;
					}
					if (Mesyuarat_Hingga.length() < 4) {
						Mesyuarat_Hingga = "0" + Mesyuarat_Hingga;
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
					}
					
					h.put("Mesyuarat_NoRujukanMesyuarat", Mesyuarat_NoRujukanMesyuarat);
					h.put("Mesyuarat_Bil", Mesyuarat_Bil);
					h.put("Mesyuarat_Tajuk", Mesyuarat_Tajuk);
					h.put("Mesyuarat_Tarikh", Mesyuarat_Tarikh);
					h.put("Mesyuarat_Dari", Mesyuarat_Dari);
					h.put("Mesyuarat_Hingga", Mesyuarat_Hingga);
					h.put("Mesyuarat_Lokasi", Mesyuarat_Lokasi);
					h.put("Mesyuarat_Status", Mesyuarat_Status);
					h.put("Mesyuarat_Unit", Mesyuarat_Unit);
					h.put("Mesyuarat_NoFail", Mesyuarat_NoFail);
					h.put("Mesyuarat_DisediakanOleh", Mesyuarat_DisediakanOleh);
					h.put("Mesyuarat_DisemakOleh", Mesyuarat_DisemakOleh);
					h.put("Mesyuarat_DisahkanOleh", Mesyuarat_DisahkanOleh);
					h.put("Mesyuarat_Catatan", Mesyuarat_Catatan);
					h.put("Mesyuarat_ValueLokasi", Mesyuarat_ValueLokasi);
					h.put("Mesyuarat_ValueUnit", Mesyuarat_ValueUnit);
					h.put("Mesyuarat_ValueStatus", Mesyuarat_ValueStatus);
					h.put("Mesyuarat_ValueDari", Mesyuarat_ValueDari);
					h.put("Mesyuarat_ValueHingga", Mesyuarat_ValueHingga);
					h.put("Mesyuarat_Kategori", Mesyuarat_Kategori);
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
	
	public String saveMesyuaratTemplateData(String ID_MESYUARAT, String NO_RUJUKANMESYUARAT, String BIL_MESYUARAT, String TAJUK_MESYUARAT, String KATEGORI_MESYUARAT, String TARIKH_MESYUARAT, String MESYUARAT_DARI, String MESYUARAT_HINGGA, 
			String ID_LOKASI, String ID_STATUS, String ID_SEKSYEN, String NO_FAIL, String DISEDIAKAN_OLEH, String DISEMAK_OLEH, String DISAHKAN_OLEH, String CATATAN, 
			String STATUS_PROSES, String ID_URUSETIA) throws Exception {
		String returnValue = "";
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATTEMPLATE";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;
			long DB_ID = 0;
			
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
			r.add("NO_FAIL", NO_RUJUKANMESYUARAT);
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
			}
			//------------------------
			
//			String WAKTU_MESYUARAT_DARI = "";
//			String WAKTU_MESYUARAT_HINGGA = "";
			
//			if (!"".equals(MESYUARAT_DARI) &&!"".equals(MESYUARAT_HINGGA)){
//				
//			
//				if (MESYUARAT_DARI.length() < 4) {
//					MESYUARAT_DARI = "0" + MESYUARAT_DARI;
//				}
//				if (MESYUARAT_HINGGA.length() < 4) {
//					MESYUARAT_HINGGA = "0" + MESYUARAT_HINGGA;
//				}
//				if (!isNumeric(MESYUARAT_DARI)) {
//					MESYUARAT_DARI = "1000";
//				}
//				if (!isNumeric(MESYUARAT_HINGGA)) {
//					MESYUARAT_HINGGA = "1100";
//				}
//				WAKTU_MESYUARAT_DARI = MESYUARAT_DARI.substring(0, 2);
//				WAKTU_MESYUARAT_HINGGA = MESYUARAT_HINGGA.substring(0, 2);
//				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
//					WAKTU_MESYUARAT_DARI = "1";
//				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
//					WAKTU_MESYUARAT_DARI = "2";
//				} else {
//					WAKTU_MESYUARAT_DARI = "3";
//				}
//				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
//					WAKTU_MESYUARAT_HINGGA = "1";
//				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
//					WAKTU_MESYUARAT_HINGGA = "2";
//				} else {
//					WAKTU_MESYUARAT_HINGGA = "3";
//				}
//			}
			if (haveData) {
				r.update("ID_MESYUARAT", ID_MESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("BIL_MESYUARAT", BIL_MESYUARAT);
			r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT);
			r.add("KATEGORI_MESYUARAT", KATEGORI_MESYUARAT);
			r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH_MESYUARAT + "', 'dd/MM/yyyy')"));
			r.add("MASA_MESYUARAT_DARI", MESYUARAT_DARI);
			//r.add("WAKTU_MESYUARAT_DARI", WAKTU_MESYUARAT_DARI);
			r.add("MASA_MESYUARAT_HINGGA", MESYUARAT_HINGGA);
			//r.add("WAKTU_MESYUARAT_HINGGA", WAKTU_MESYUARAT_HINGGA);
			//r.add("ID_LOKASI", ID_LOKASI);
			r.add("ID_STATUS", ID_STATUS);
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			r.add("ID_FAIL", ID_FAIL);
			r.add("NO_FAIL_DIPERLUKAN", NO_RUJUKANMESYUARAT);
			r.add("DISEDIAKAN_OLEH", DISEDIAKAN_OLEH);
			r.add("DISEMAK_OLEH", DISEMAK_OLEH);
			r.add("DISAHKAN_OLEH", DISAHKAN_OLEH);
			r.add("CATATAN", CATATAN);
			r.add("STATUS_PROSES", STATUS_PROSES);
			r.add("ID_URUSETIA", ID_URUSETIA);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
				DB_ID = Long.parseLong(ID_MESYUARAT);
			} else {
				DB_ID = DB.getNextID("TBLPFDMESYUARATTEMPLATE_SEQ");
				r.add("ID_MESYUARAT", DB_ID);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			System.out.println("insert : " + sql);
			stmt.execute(sql);
			returnValue = Long.toString(DB_ID);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String saveMesyuaratTemplateDataNegeri(String ID_MESYUARAT, String NO_RUJUKANMESYUARAT, String BIL_MESYUARAT, String TAJUK_MESYUARAT, String KATEGORI_MESYUARAT, String TARIKH_MESYUARAT, String MESYUARAT_DARI, String MESYUARAT_HINGGA, 
			String ID_LOKASI, String ID_STATUS, String ID_NEGERI, String ID_UNIT, String NO_FAIL, String DISEDIAKAN_OLEH, String DISEMAK_OLEH, String DISAHKAN_OLEH, String CATATAN, 
			String STATUS_PROSES, String ID_URUSETIA) throws Exception {
		String returnValue = "";
		String sql = "";
		String TABLE_NAME = "TBLPFDMESYUARATTEMPLATE";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;
			long DB_ID = 0;
			
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
			r.add("NO_FAIL", NO_RUJUKANMESYUARAT);
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
			}
			//------------------------
			
//			String WAKTU_MESYUARAT_DARI = "";
//			String WAKTU_MESYUARAT_HINGGA = "";
			
//			if (!"".equals(MESYUARAT_DARI) &&!"".equals(MESYUARAT_HINGGA)){
//				
//			
//				if (MESYUARAT_DARI.length() < 4) {
//					MESYUARAT_DARI = "0" + MESYUARAT_DARI;
//				}
//				if (MESYUARAT_HINGGA.length() < 4) {
//					MESYUARAT_HINGGA = "0" + MESYUARAT_HINGGA;
//				}
//				if (!isNumeric(MESYUARAT_DARI)) {
//					MESYUARAT_DARI = "1000";
//				}
//				if (!isNumeric(MESYUARAT_HINGGA)) {
//					MESYUARAT_HINGGA = "1100";
//				}
//				WAKTU_MESYUARAT_DARI = MESYUARAT_DARI.substring(0, 2);
//				WAKTU_MESYUARAT_HINGGA = MESYUARAT_HINGGA.substring(0, 2);
//				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
//					WAKTU_MESYUARAT_DARI = "1";
//				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
//					WAKTU_MESYUARAT_DARI = "2";
//				} else {
//					WAKTU_MESYUARAT_DARI = "3";
//				}
//				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
//					WAKTU_MESYUARAT_HINGGA = "1";
//				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
//					WAKTU_MESYUARAT_HINGGA = "2";
//				} else {
//					WAKTU_MESYUARAT_HINGGA = "3";
//				}
//			}
			if (haveData) {
				r.update("ID_MESYUARAT", ID_MESYUARAT);
			}
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("BIL_MESYUARAT", BIL_MESYUARAT);
			r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT);
			r.add("KATEGORI_MESYUARAT", KATEGORI_MESYUARAT);
			r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH_MESYUARAT + "', 'dd/MM/yyyy')"));
			r.add("MASA_MESYUARAT_DARI", MESYUARAT_DARI);
			//r.add("WAKTU_MESYUARAT_DARI", WAKTU_MESYUARAT_DARI);
			r.add("MASA_MESYUARAT_HINGGA", MESYUARAT_HINGGA);
			//r.add("WAKTU_MESYUARAT_HINGGA", WAKTU_MESYUARAT_HINGGA);
			//r.add("ID_LOKASI", ID_LOKASI);
			r.add("ID_STATUS", ID_STATUS);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_UNIT", ID_UNIT);
			r.add("ID_FAIL", ID_FAIL);
			r.add("NO_FAIL_DIPERLUKAN", NO_RUJUKANMESYUARAT);
			r.add("DISEDIAKAN_OLEH", DISEDIAKAN_OLEH);
			r.add("DISEMAK_OLEH", DISEMAK_OLEH);
			r.add("DISAHKAN_OLEH", DISAHKAN_OLEH);
			r.add("CATATAN", CATATAN);
			r.add("STATUS_PROSES", STATUS_PROSES);
			r.add("ID_URUSETIA", ID_URUSETIA);
			if (haveData) {
				sql = r.getSQLUpdate(TABLE_NAME);
				DB_ID = Long.parseLong(ID_MESYUARAT);
			} else {
				DB_ID = DB.getNextID("TBLPFDMESYUARATTEMPLATE_SEQ");
				r.add("ID_MESYUARAT", DB_ID);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert(TABLE_NAME);
			}
			System.out.println("insert : " + sql);
			stmt.execute(sql);
			returnValue = Long.toString(DB_ID);
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean saveAhliMesyuaratTemplateData(String ID_MESYUARAT, String ID_AHLIMESYUARAT, String KATEGORI_AHLI, String ID_PEGAWAI, String NAMA_AHLI, String NAMA_AGENSI, String ID_AGENSI, 
			String ID_NEGERI, String ID_SEKSYEN, String ID_UNIT, String JAWATAN, String EMAIL, String NO_TEL, String NO_FAKS, String ID_PERANAN) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDAHLIMESYUARATTEMPLATE";
		Db db = new Db();
		
		
		System.out.println("ID_AHLIMESYUARAT TEMPLATE :"+ID_AHLIMESYUARAT);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Boolean haveData = false;
			
		/*	if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
				sql = "SELECT NAMA_PEGAWAI , JAWATAN FROM TBLRUJPEGAWAI WHERE ID_PEGAWAI = " + ID_PEGAWAI;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NAMA_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
					JAWATAN = rs.getString(2) == null ? "" : rs.getString(2);
				}
				rs.close();
			}*/
			
			
			if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
				sql = "SELECT UPPER(U.USER_NAME) AS NAMA_PEGAWAI,J.KETERANGAN AS JAWATAN, U.USER_NAME, J.KOD_JAWATAN " +
						"FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
						"WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "
					+" AND U.USER_ID ="+ID_PEGAWAI+"" +
				" AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'"
					

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
			if (!ID_AHLIMESYUARAT.equals("") && ID_AHLIMESYUARAT!=null) {
				sql = r.getSQLUpdate(TABLE_NAME);
			} else {
				long DB_ID = DB.getNextID("TBLPFDAHLIMESYUARATTMPLTE_SEQ");
				r.add("ID_AHLIMESYUARAT", DB_ID);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
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

	
	
	public Boolean deleteAhliMesyuaratTemplateData(String ID_MESYUARAT, String ID_AHLIMESYUARAT) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		String TABLE_NAME = "TBLPFDAHLIMESYUARATTEMPLATE";
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
	public Vector getAhliMesyuaratTemplateData(String ID_MESYUARAT, String ID_AHLIMESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = new Hashtable();
			
			String ID_AHLI = "", KATEGORI_AHLI = "", NAMA_AGENSI = "", ID_SEKSYEN = "", ID_NEGERI = "", ID_UNIT = "", ID_PEGAWAI = "", NAMA_AHLI = "", JAWATAN = "", EMAIL = "", ID_PERANAN = "", NO_TEL = "", NO_FAKS = "";
			/*sql = "SELECT M.ID_AHLIMESYUARAT, M.KATEGORI_AHLI, M.NAMA_AGENSI, M.ID_SEKSYEN, M.ID_NEGERI, M.ID_UNIT, M.ID_PEGAWAI, M.NAMA_AHLIMESYUARAT, M.JAWATAN, M.EMAIL, PR.ID_PERANAN, M.NO_TEL, M.NO_FAKS " +
				"FROM TBLPFDAHLIMESYUARATTEMPLATE M, TBLRUJSEKSYEN SK, TBLRUJNEGERI NG, " +
				"TBLRUJPEGAWAI PG, " +
				"TBLPFDRUJPERANANAHLIMESYUARAT PR " +
				"WHERE M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_NEGERI = NG.ID_NEGERI(+) AND M.ID_PEGAWAI = PG.ID_PEGAWAI(+) AND M.ID_PERANAN = PR.ID_PERANAN " +
				"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT;
		*/
			
			sql = "SELECT M.ID_AHLIMESYUARAT, M.KATEGORI_AHLI, M.NAMA_AGENSI, M.ID_SEKSYEN, " +
					"M.ID_NEGERI, M.ID_UNIT, M.ID_PEGAWAI, M.NAMA_AHLIMESYUARAT, " +
					"M.JAWATAN, M.EMAIL, PR.ID_PERANAN, M.NO_TEL, M.NO_FAKS,J.KOD_JAWATAN,PG.USER_NAME," +
					"TO_CHAR(UI.ID_NEGERI) AS NEGERI_USER,TO_CHAR(PG.USER_ID) AS USER_ID " +
			"FROM TBLPFDAHLIMESYUARATTEMPLATE M, TBLRUJSEKSYEN SK, TBLRUJNEGERI NG, " +
			"USERS PG, USERS_INTERNAL UI, TBLRUJJAWATAN J, " +
			"TBLPFDRUJPERANANAHLIMESYUARAT PR " +
			"WHERE M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_NEGERI = NG.ID_NEGERI(+) " +
			"AND M.ID_PEGAWAI = PG.USER_ID(+) AND M.ID_PERANAN = PR.ID_PERANAN " +
			"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " " +
					"AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT+"" +
							" AND PG.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+)" +
							" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'"
							

							+" ORDER BY J.KOD_JAWATAN,PG.USER_NAME ";

			System.out.println("test :"+sql);
			
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
				
				h.put("ID_AHLI", ID_AHLI);
				h.put("KATEGORI_AHLI", KATEGORI_AHLI);
				h.put("NAMA_AGENSI", NAMA_AGENSI);
				h.put("ID_SEKSYEN", ID_SEKSYEN);
				h.put("ID_NEGERI", ID_NEGERI);
				h.put("ID_UNIT", ID_UNIT);
				h.put("ID_PEGAWAI", ID_PEGAWAI);
				h.put("NAMA_AHLI", NAMA_AHLI);
				h.put("JAWATAN", JAWATAN);
				h.put("EMAIL", EMAIL);
				h.put("ID_PERANAN", ID_PERANAN);
				h.put("NO_TEL", NO_TEL);
				h.put("NO_FAKS", NO_FAKS);
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
	
	@SuppressWarnings("unchecked")
	public Vector getAhliMesyuaratTemplateList(String ID_MESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_MESYUARAT)) {
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				int iCount = 1;
				
				String ID_AHLI = "", KATEGORI_AHLI = "", NAMA_AHLI = "", NAMA_SEKSYEN = "", NAMA_NEGERI = "", NAMA_AGENSI = "", AGENSI = "", PERANAN = ""; 
				sql = "SELECT M.ID_AHLIMESYUARAT, M.KATEGORI_AHLI, M.NAMA_AHLIMESYUARAT, SK.NAMA_SEKSYEN, NG.NAMA_NEGERI, AG.NAMA_AGENSI, M.NAMA_AGENSI, PR.NAMA_PERANAN " +
					"FROM TBLPFDAHLIMESYUARATTEMPLATE M, TBLRUJNEGERI NG, TBLRUJSEKSYEN SK, TBLRUJAGENSI AG, TBLPFDRUJPERANANAHLIMESYUARAT PR " +
					"WHERE M.ID_NEGERI = NG.ID_NEGERI(+) AND M.ID_SEKSYEN = SK.ID_SEKSYEN(+) AND M.ID_AGENSI = AG.ID_AGENSI(+) AND M.ID_PERANAN = PR.ID_PERANAN " +
					"AND M.ID_MESYUARAT = " + ID_MESYUARAT +
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
					if ("1".equalsIgnoreCase(KATEGORI_AHLI)) {
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
	public Vector getAhliMesyuaratListID(String ID_MESYUARAT) throws Exception {
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
					"FROM TBLPFDAHLIMESYUARATTEMPLATE M, TBLPFDMESYUARATTEMPLATE MS " +
					"WHERE M.ID_MESYUARAT = MS.ID_MESYUARAT " +
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
	public Vector getAhliMesyuaratKehadiran(String ID_MESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			int iCount = 1;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			
			String ID_AHLI = "", NAMA_AHLI = "", HADIR = "", NAMA_WAKIL = "";
			sql = "SELECT ID_AHLIMESYUARAT, NAMA_AHLIMESYUARAT, HADIR, NAMA_WAKIL FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY FLAG_PENGERUSI, KATEGORI_AHLI, NAMA_AHLIMESYUARAT, ID_AHLIMESYUARAT";
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
					h.put("Wakil", "");
					h.put("EnableWakil", "readonly=\"readonly\"");
					h.put("CheckHadir", "checked=\"checked\"");
					h.put("CheckTidakHadir", "");
				} else {
					h.put("Wakil", NAMA_WAKIL);
					h.put("EnableWakil", "");
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
	public Vector getListMesyuaratTemplateKehadiran(String ID_MESYUARAT) throws Exception {
		Vector v = new Vector();
		String sql = "";
		Db db = new Db();
		
		try {
			int iCount = 1;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			
			String ID_AHLI = "", NAMA_AHLI = "", PERANAN = "";
			sql = "SELECT M.ID_AHLIMESYUARAT, M.NAMA_AHLIMESYUARAT, PR.NAMA_PERANAN " + 
				"FROM TBLPFDAHLIMESYUARATTEMPLATE M, TBLPFDRUJPERANANAHLIMESYUARAT PR WHERE M.ID_PERANAN = PR.ID_PERANAN AND M.ID_MESYUARAT = " + ID_MESYUARAT + " ORDER BY M.ID_PERANAN, M.KATEGORI_AHLI, M.NAMA_AHLIMESYUARAT, M.ID_AHLIMESYUARAT";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_AHLI = rs.getString(1) == null ? "" : rs.getString(1);
				NAMA_AHLI = rs.getString(2) == null ? "" : rs.getString(2);
				PERANAN = rs.getString(3) == null ? "" : rs.getString(3);
				
				h = new Hashtable();
				h.put("No", iCount);
				h.put("IDAhli", ID_AHLI);
				h.put("NamaAhli", NAMA_AHLI);
				h.put("Peranan", PERANAN);
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
					sql = r.getSQLUpdate("TBLPFDAHLIMESYUARATTEMPLATE");
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
	
	public Boolean havePengerusi(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)){
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_AHLIMESYUARAT <> " + ID_AHLI + " AND ID_MESYUARAT = " + ID_MESYUARAT;
	//			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1 AND ID_MESYUARAT = " + ID_MESYUARAT;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && "".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 1  AND ID_MESYUARAT = " + ID_MESYUARAT;
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

	public Boolean haveTimbalanPengerusi(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 2 AND ID_AHLIMESYUARAT = " + ID_AHLI + " AND AND ID_MESYUARAT = " + ID_MESYUARAT;
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

	public Boolean haveSetiausaha(String ID_MESYUARAT, String ID_AHLI) throws Exception {
		Boolean returnValue = false;
		String sql = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && !"".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 3 AND ID_AHLIMESYUARAT <> " + ID_AHLI + "  AND ID_MESYUARAT = " + ID_MESYUARAT;
				System.out.println("SQL SET:"+sql.toUpperCase());
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
			
			
			if(!"".equalsIgnoreCase(ID_MESYUARAT) && "".equalsIgnoreCase(ID_AHLI)){
				sql = "SELECT ID_AHLIMESYUARAT FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_PERANAN = 3  AND ID_MESYUARAT = " + ID_MESYUARAT;
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
		return getSOCLokasiMesyuarat(SOC_NAME, "", "", "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, SELECTED_LOKASI, "", "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI, String DISABLED) throws Exception {
		return getSOCLokasiMesyuarat(SOC_NAME, SELECTED_LOKASI, DISABLED, "");
	}
	public String getSOCLokasiMesyuarat(String SOC_NAME, String SELECTED_LOKASI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				SQL_SEARCH + "WHERE ID_NEGERI = '16' ORDER BY LOKASI, ID_LOKASI";
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

	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI, String user_negeri, String DISABLED) throws Exception {
		return getSOCLokasiMesyuaratNegeri(SOC_NAME, SELECTED_LOKASI, DISABLED, "");
	}
	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI,String user_negeri, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				SQL_SEARCH + "WHERE ID_NEGERI = '"+user_negeri+"' ORDER BY LOKASI, ID_LOKASI";
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
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			String ID_STATUS = "", STATUS = "";
			sql = "SELECT ID_STATUS, SUBSTR(STATUS_MESYUARAT, 1, 50) FROM TBLPFDRUJSTATUSMESYUARAT " +
				SQL_SEARCH + "ORDER BY STATUS_MESYUARAT, ID_STATUS";
			
			System.out.println("sql STATUS :"+sql.toUpperCase());
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

	//--Seksyen-
	
	
	/*
	public String getSOCPegawaiBySeksyen(String SOC_NAME) throws Exception {
		return getSOCPegawaiBySeksyen(SOC_NAME, "", "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String SOC_NAME, String SELECTED_PEGAWAI) throws Exception {
		return getSOCPegawaiBySeksyen(SOC_NAME, SELECTED_PEGAWAI, "", "", "");
	}
	public String getSOCPegawaiBySeksyen(String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN) throws Exception {
		return getSOCPegawaiBySeksyen(SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, "", "");
	}
	public String getSOCPegawaiBySeksyen(String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String DISABLED) throws Exception {
		return getSOCPegawaiBySeksyen(SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, DISABLED, "");
	}
	public String getSOCPegawaiByNegeri(String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED) throws Exception {
		return getSOCPegawaiByNegeri(SOC_NAME, SELECTED_PEGAWAI, SELECTED_NEGERI, DISABLED, SELECTED_KATEGORI, "");
	}
	

	public String getSOCPegawaiBySeksyen(String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		
		System.out.print("ID_PEGAWAI ::"+SELECTED_PEGAWAI);
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
		if (!"".equalsIgnoreCase(SELECTED_SEKSYEN)) {
				SQL_SEARCH = "AND UI.ID_SEKSYEN = " + SELECTED_SEKSYEN;
			}
 
			
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			
			if(SELECTED_PEGAWAI.equals(""))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			String ID_PEGAWAI = "", NAMA_PEGAWAI = "";
		
			
			sql = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NAMA_PEGAWAI, " +
					"PG.USER_ID AS ID_PEGAWAI, PG.USER_NAME, J.KOD_JAWATAN,J.KOD_JAWATAN, PG.USER_NAME " +
			"FROM USERS PG,USERS_INTERNAL UI, TBLPFDAHLIMESYUARAT AH, TBLRUJJAWATAN J " +
			"WHERE PG.USER_ID = AH.ID_PEGAWAI(+) AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND PG.USER_ID = UI.USER_ID " + SQL_SEARCH + 
			" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'";
			
			if(!SELECTED_PEGAWAI.equals(""))
			{
			sql += " AND PG.USER_ID = '"+SELECTED_PEGAWAI+"'";
			}

			sql += " ORDER BY J.KOD_JAWATAN, PG.USER_NAME";
			
			
			
			
			System.out.println("SSSSSSSS :"+sql.toUpperCase());
			
			
				rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PEGAWAI = rs.getString(1) == null ? "" : rs.getString(1);
				NAMA_PEGAWAI = rs.getString(2) == null ? "" : rs.getString(2);
				if (!"".equalsIgnoreCase(ID_PEGAWAI)) {
					if (SELECTED_PEGAWAI.equalsIgnoreCase(ID_PEGAWAI)) {
						returnValue += "  <option value=\"" + NAMA_PEGAWAI + "\" selected=\"selected\">" +  ID_PEGAWAI+ "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + NAMA_PEGAWAI + "\">" + ID_PEGAWAI + "</option>\r\n";
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
	
	public String getSOCPegawaiByNegeri(String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(SELECTED_NEGERI)) {
				SQL_SEARCH = "AND UI.ID_NEGERI = " + SELECTED_NEGERI;
			}
			String VIEW_KAT_AHLI = "";
			if ("3".equalsIgnoreCase(SELECTED_KATEGORI)) {
				VIEW_KAT_AHLI = "KATEGORI_AHLI != 3 ";
			} else {
				VIEW_KAT_AHLI = "KATEGORI_AHLI = 3 ";
			}
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			
			if(SELECTED_PEGAWAI.equals(""))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			
			String ID_PEGAWAI = "", NAMA_PEGAWAI = "";
			
			
			sql = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI," +
					"J.KOD_JAWATAN, PG.USER_NAME " +
			"FROM USERS PG,USERS_INTERNAL UI, TBLPFDAHLIMESYUARAT AH, TBLRUJJAWATAN J " +
			"WHERE PG.USER_ID = AH.ID_PEGAWAI(+) AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND PG.USER_ID = UI.USER_ID " + SQL_SEARCH + 
			" AND PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + VIEW_KAT_AHLI + "AND ID_PEGAWAI <> 0) " +
			" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'";
			
			if(!SELECTED_PEGAWAI.equals(""))
			{
			sql += " AND PG.USER_ID = '"+SELECTED_PEGAWAI+"'";
			}
			
			sql += " ORDER BY J.KOD_JAWATAN, PG.USER_NAME";
			
			System.out.println("LIST USERS :"+sql.toUpperCase());
			
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
	*/
	
	
	
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
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI, String DISABLED,String ACTION,String MAIN_ACTION) throws Exception {
		return getSOCPegawaiBySeksyen(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_SEKSYEN, SELECTED_KATEGORI, DISABLED, "",ACTION,MAIN_ACTION);
	}
	
	
	public String getSOCPegawaiBySeksyen(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_SEKSYEN, String SELECTED_KATEGORI, String DISABLED, String ONCHANGE,String action,String main_action) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		System.out.println("main action ::"+main_action);
		System.out.println("submit ::"+action);
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
			
			if(SELECTED_PEGAWAI.equals("") && !action.equals("daftarAhli"))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			
			else
			{
				
				
				if((main_action.equals("daftarAhli") && action.equals("")) || ((action.equals("changeSeksyen") || action.equals("changeNegeri"))))
					
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
		
			if(!action.equals("viewAhli"))
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
			System.out.println("returnValue:"+returnValue.toUpperCase());
			
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
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED,String ACTION,String MAIN_ACTION) throws Exception {
		return getSOCPegawaiByNegeri(ID_MESYUARAT,SOC_NAME, SELECTED_PEGAWAI, SELECTED_NEGERI, DISABLED, SELECTED_KATEGORI, "",ACTION,MAIN_ACTION);
	}
	public String getSOCPegawaiByNegeri(String ID_MESYUARAT,String SOC_NAME, String SELECTED_PEGAWAI, String SELECTED_NEGERI, String SELECTED_KATEGORI, String DISABLED, String ONCHANGE,String action,String main_action) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		System.out.println("ID_PEGAWAI ::"+SELECTED_PEGAWAI);
		System.out.println("ACTION ::"+main_action);
		System.out.println("SUBMIT ::"+action);
		System.out.println("SELECTED_NEGERI ::"+SELECTED_NEGERI);
		
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
		 
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			if(SELECTED_PEGAWAI.equals(""))
			{
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			}
			else
			{
				
				if(!action.equals("daftarAhli") && !action.equals("changeSeksyen") && !action.equals("changeNegeri"))
				{				
		sql1 = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI " +
				" ,J.KOD_JAWATAN, PG.USER_NAME " +
		"FROM " +
	    "USERS PG, " +
		"USERS_INTERNAL UI,TBLRUJJAWATAN J " +
		"WHERE UI.USER_ID = PG.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) ";
		sql1 += " AND PG.USER_ID = '"+SELECTED_PEGAWAI+"'";
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
			
			sql = "SELECT DISTINCT UPPER(SUBSTR(TRIM(PG.USER_NAME), 1, 50)) AS NP, PG.USER_ID AS ID_PEGAWAI" +
					", J.KOD_JAWATAN, PG.USER_NAME " +
			"FROM USERS PG,USERS_INTERNAL UI, TBLPFDAHLIMESYUARAT AH, TBLRUJJAWATAN J " +
			"WHERE PG.USER_ID = AH.ID_PEGAWAI(+) AND PG.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " + SQL_SEARCH + 
			" AND PG.USER_ID NOT IN (SELECT ID_PEGAWAI FROM TBLPFDAHLIMESYUARAT WHERE " + SQL_SEARCH_ID_MESYUARAT + " ID_PEGAWAI IS NOT NULL) " +
			" AND PG.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint','sptb') and J.kod_jawatan != '00'";
			
			if(!SELECTED_PEGAWAI.equals(""))
			{
			sql += " AND PG.USER_ID != '"+SELECTED_PEGAWAI+"'";
			}			
			sql += " ORDER BY J.KOD_JAWATAN, PG.USER_NAME";
			System.out.println("SELECT PEGAWAI QUERY ::::::"+sql.toUpperCase());
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


	
	@SuppressWarnings("unchecked")
	public Vector getListMesyuaratTemplate(String Mesyuarat_NoRujukanDokumen, String Mesyuarat_Tajuk, String Mesyuarat_Urusetia, String Mesyuarat_Lokasi, String Mesyuarat_Tarikh,String id_seksyen, String id_negeri, String ID_URUSETIA) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			String SQL_Search_Unit = "";
			if(!"".equalsIgnoreCase(id_seksyen)){
				 SQL_Search_Seksyen = " AND M.ID_SEKSYEN = '" + id_seksyen + "' ";
			}
			
//			if(!"".equalsIgnoreCase(id_unit)){
//				 SQL_Search_Unit = " AND M.ID_UNIT = '" + id_unit + "' ";
//			}
			
			if(!"".equalsIgnoreCase(id_negeri) && !"16".equalsIgnoreCase(id_negeri)){
				SQL_Search_Negeri = " AND M.ID_NEGERI = '" + id_negeri + "' ";
			}
			
			
			String SQL_Search = "";
			if ("".equalsIgnoreCase(Mesyuarat_Tajuk) && "".equalsIgnoreCase(Mesyuarat_NoRujukanDokumen) && "".equalsIgnoreCase(Mesyuarat_Urusetia) && "".equalsIgnoreCase(Mesyuarat_Lokasi) && "".equalsIgnoreCase(Mesyuarat_Tarikh)) {
				// search all
			} else {
				// build search string
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.toUpperCase();
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.toUpperCase();
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.toUpperCase();
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.toUpperCase();
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.toUpperCase();
				
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.replace("'", "''");
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.replace("\\", "\\\\");
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.replace("'", "''");
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.replace("\\", "\\\\");
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.replace("'", "''");
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.replace("\\", "\\\\");
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.replace("'", "''");
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.replace("\\", "\\\\");
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.replace("'", "''");
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.replace("\\", "\\\\");
				
				if (!"".equalsIgnoreCase(Mesyuarat_NoRujukanDokumen)) {
					SQL_Search += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + Mesyuarat_NoRujukanDokumen + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tajuk)) {
					SQL_Search += "AND UPPER(M.TAJUK_MESYUARAT) LIKE '%" + Mesyuarat_Tajuk + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Urusetia)) {
					SQL_Search += "AND UPPER(RS.NAMA_SEKSYEN) LIKE '%" + Mesyuarat_Urusetia + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Lokasi)) {
					SQL_Search += "AND UPPER(RL.LOKASI) LIKE '%" + Mesyuarat_Lokasi + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tarikh)) {
					SQL_Search += "AND UPPER(M.TARIKH_MESYUARAT) LIKE '%" + Mesyuarat_Tarikh + "%' ";
				}
			}

			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_SEKSYEN, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT,  M.WAKTU_MESYUARAT_DARI, FAIL.NO_FAIL, M.KATEGORI_MESYUARAT " +
				"FROM TBLPFDMESYUARATTEMPLATE M, TBLRUJSEKSYEN RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_SEKSYEN = RS.ID_SEKSYEN(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_URUSETIA = '"+ID_URUSETIA+"'" +
				SQL_Search + SQL_Search_Seksyen + SQL_Search_Negeri + "  ORDER BY M.ID_MESYUARAT DESC ";
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			System.out.println(" select : " +  sql);
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
					Mesyuarat_Dari = "0" + Mesyuarat_Dari;
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = "0" + Mesyuarat_Hingga;
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				if(!"".equalsIgnoreCase(Mesyuarat_Dari)  && !"0".equalsIgnoreCase(Mesyuarat_Dari) && !"00".equalsIgnoreCase(Mesyuarat_Dari) && !"".equalsIgnoreCase(Mesyuarat_Hingga) && !"00".equalsIgnoreCase(Mesyuarat_Hingga) && !"0".equalsIgnoreCase(Mesyuarat_Hingga) ){
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
				}
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
				h.put("ListNoFail", rs.getString(11) == null ? "" : rs.getString(11));
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
	
	
	public Vector getListMesyuaratTemplateNegeri(String Mesyuarat_NoRujukanDokumen, String Mesyuarat_Tajuk, String Mesyuarat_Urusetia, String Mesyuarat_Lokasi, String Mesyuarat_Tarikh, String id_negeri, String ID_URUSETIA) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Unit = "";
			String SQL_Search_Negeri = "";
//			if(!"".equalsIgnoreCase(id_unit)){
//				 SQL_Search_Unit = " AND M.ID_UNIT = '" + id_unit + "' ";
//			}
			
			if(!"".equalsIgnoreCase(id_negeri) && !"16".equalsIgnoreCase(id_negeri)){
				SQL_Search_Negeri = " AND M.ID_NEGERI = '" + id_negeri + "' ";
			}
			
			
			String SQL_Search = "";
			if ("".equalsIgnoreCase(Mesyuarat_Tajuk) && "".equalsIgnoreCase(Mesyuarat_NoRujukanDokumen) && "".equalsIgnoreCase(Mesyuarat_Urusetia) && "".equalsIgnoreCase(Mesyuarat_Lokasi) && "".equalsIgnoreCase(Mesyuarat_Tarikh)) {
				// search all
			} else {
				// build search string
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.toUpperCase();
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.toUpperCase();
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.toUpperCase();
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.toUpperCase();
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.toUpperCase();
				
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.replace("'", "''");
				Mesyuarat_NoRujukanDokumen = Mesyuarat_NoRujukanDokumen.replace("\\", "\\\\");
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.replace("'", "''");
				Mesyuarat_Tajuk = Mesyuarat_Tajuk.replace("\\", "\\\\");
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.replace("'", "''");
				Mesyuarat_Urusetia = Mesyuarat_Urusetia.replace("\\", "\\\\");
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.replace("'", "''");
				Mesyuarat_Lokasi = Mesyuarat_Lokasi.replace("\\", "\\\\");
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.replace("'", "''");
				Mesyuarat_Tarikh = Mesyuarat_Tarikh.replace("\\", "\\\\");
				
				if (!"".equalsIgnoreCase(Mesyuarat_NoRujukanDokumen)) {
					SQL_Search += "AND UPPER(FAIL.NO_FAIL) LIKE '%" + Mesyuarat_NoRujukanDokumen + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tajuk)) {
					SQL_Search += "AND UPPER(M.TAJUK_MESYUARAT) LIKE '%" + Mesyuarat_Tajuk + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Urusetia)) {
					SQL_Search += "AND UPPER(RS.NAMA_UNIT) LIKE '%" + Mesyuarat_Urusetia + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Lokasi)) {
					SQL_Search += "AND UPPER(RL.LOKASI) LIKE '%" + Mesyuarat_Lokasi + "%' ";
				}
				if (!"".equalsIgnoreCase(Mesyuarat_Tarikh)) {
					SQL_Search += "AND UPPER(M.TARIKH_MESYUARAT) LIKE '%" + Mesyuarat_Tarikh + "%' ";
				}
			}

			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_UNIT, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT,  M.WAKTU_MESYUARAT_DARI, FAIL.NO_FAIL, M.KATEGORI_MESYUARAT " +
				"FROM TBLPFDMESYUARATTEMPLATE M, TBLRUJUNIT RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_UNIT = RS.ID_UNIT(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL AND M.ID_URUSETIA = '"+ID_URUSETIA+"'" +
				SQL_Search + SQL_Search_Unit + SQL_Search_Negeri + "  ORDER BY M.ID_MESYUARAT DESC ";
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			System.out.println(" select : " +  sql);
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
					Mesyuarat_Dari = "0" + Mesyuarat_Dari;
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = "0" + Mesyuarat_Hingga;
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				if(!"".equalsIgnoreCase(Mesyuarat_Dari)  && !"0".equalsIgnoreCase(Mesyuarat_Dari) && !"00".equalsIgnoreCase(Mesyuarat_Dari) && !"".equalsIgnoreCase(Mesyuarat_Hingga) && !"00".equalsIgnoreCase(Mesyuarat_Hingga) && !"0".equalsIgnoreCase(Mesyuarat_Hingga) ){
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
				}
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
				h.put("ListNoFail", rs.getString(11) == null ? "" : rs.getString(11));
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

			sql = "SELECT KATEGORI_AHLI FROM TBLPFDAHLIMESYUARATTEMPLATE WHERE ID_AHLIMESYUARAT = '"+id_ahli+"'";

				
				
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
					"M.NO_TEL, M.NO_FAKS " +
					//",M.ALAMAT_AHLI "+
				"FROM TBLPFDAHLIMESYUARATTEMPLATE M,"+
				"TBLPFDRUJPERANANAHLIMESYUARAT PR " +
				"WHERE "+
				" M.ID_PERANAN = PR.ID_PERANAN(+) " +
				"AND M.ID_MESYUARAT = " + ID_MESYUARAT + " " +
				"AND M.ID_AHLIMESYUARAT = " + ID_AHLIMESYUARAT+"";
				
			System.out.println("TEST URUSETIA"+sql.toUpperCase());
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				
				
				h.put("ID_AHLI", rs.getString("ID_AHLIMESYUARAT") == null ? "" : rs.getString("ID_AHLIMESYUARAT"));
			//	h.put("ALAMAT_AHLI", rs.getString("ALAMAT_AHLI") == null ? "" : rs.getString("ALAMAT_AHLI"));
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

	public Vector getListNegeri() throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			  paparNegeri = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT ID_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				  h.put("nama_negeri",rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				  paparNegeri.addElement(h); 
		      }
		      
		      return paparNegeri;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}





	public Vector getDataNegeri(String user_negeri) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			  paparNegeri = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT ID_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"+user_negeri+"' ORDER BY ID_NEGERI ASC";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("id_negeri",rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				  h.put("nama_negeri",rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				  paparNegeri.addElement(h); 
		      }
		      
		      return paparNegeri;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}


	public Vector getListUnit(String user_negeri) {
		// TODO Auto-generated method stub
		return null;
	}



	public Vector getDataUnit(String user_negeri) {
		// TODO Auto-generated method stub
		return null;
	}





	public Vector getUnitByNegeri(String id_negeri) throws Exception {
		Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  

 	      sql = "SELECT id_unit,nama_unit from TBLRUJUNIT WHERE ID_NEGERI = '"+id_negeri+"'";
 	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable s;
 			while (rs.next()) {
 				s = new Hashtable();
 				s.put("id_Unit", rs.getString("id_Unit") == null ? ""
						: rs.getString("id_Unit"));
// 				s.put("kod_Suburusan", rs.getString("kod_Suburusan") == null ? ""
//						: rs.getString("kod_Suburusan"));
 				s.put("nama_Unit", rs.getString("nama_Unit") == null ? ""
						: rs.getString("nama_Unit"));
 				
 				v.addElement(s);
 			}
 		
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
	}



	
}
