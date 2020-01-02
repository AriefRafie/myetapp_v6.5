package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmModelMesyuaratTempahan {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("-?\\d+(.\\d+)?");
	}
	
	public Boolean locIsAvailable(String ID_LOKASI, String TARIKH_MESYUARAT, int DARI, int HINGGA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_LOKASI) && !"".equalsIgnoreCase(TARIKH_MESYUARAT) && DARI >= 0 && HINGGA >= 0) {
				SQLRenderer r = new SQLRenderer();
				r.add("ID_MESYUARAT");
				r.add("ID_LOKASI", ID_LOKASI);
				r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH_MESYUARAT + "', 'dd/MM/yyyy')"));
				r.add("TO_NUMBER(SUBSTR(MASA_MESYUARAT_DARI, 1, 2))", DARI, "<=");
				r.add("TO_NUMBER(SUBSTR(MASA_MESYUARAT_HINGGA, 1, 2))", HINGGA, ">=");
				String sql = r.getSQLSelect("TBLPFDMESYUARAT");
				
				Statement stmt = db.getStatement();
				ResultSet rs = null;
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
	
	@SuppressWarnings("unchecked")
	public Vector getTempahanList(String BULAN, String TAHUN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(BULAN) && !"".equalsIgnoreCase(TAHUN)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				int iCount = 1;
				String MASA_DARI = "", MASA_HINGGA = "";
				String WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
				String sql = "SELECT FAIL.NO_FAIL, M.TAJUK_MESYUARAT, LOK.LOKASI, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, M.ID_MESYUARAT " +
					"FROM TBLPFDMESYUARAT M, TBLPFDFAIL FAIL, TBLPFDRUJLOKASIMESYUARAT LOK " +
					"WHERE M.ID_FAIL = FAIL.ID_FAIL(+) AND M.ID_LOKASI = LOK.ID_LOKASI " +
					"AND TO_CHAR(M.TARIKH_MESYUARAT, 'MM') = " + BULAN + " AND TO_CHAR(M.TARIKH_MESYUARAT, 'YYYY') = " + TAHUN +
					" ORDER BY M.TARIKH_MESYUARAT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					MASA_DARI = rs.getString(5) == null ? "" : rs.getString(5);
					MASA_HINGGA = rs.getString(6) == null ? "" : rs.getString(6);
					if (MASA_DARI.length() < 4) {
						MASA_DARI = String.format("0000", MASA_DARI);
					}
					if (MASA_HINGGA.length() < 4) {
						MASA_HINGGA = String.format("0000", MASA_HINGGA);
					}
					if (!isNumeric(MASA_DARI)) {
						MASA_DARI = "1000";
					}
					if (!isNumeric(MASA_HINGGA)) {
						MASA_HINGGA = "1100";
					}
					WAKTU_MESYUARAT_DARI = MASA_DARI.substring(0, 2);
					WAKTU_MESYUARAT_HINGGA = MASA_HINGGA.substring(0, 2);
					if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
						WAKTU_MESYUARAT_DARI = " pagi";
					} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
						WAKTU_MESYUARAT_DARI = " petang";
					} else {
						WAKTU_MESYUARAT_DARI = " malam";
					}
					if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
						WAKTU_MESYUARAT_HINGGA = " pagi";
					} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
						WAKTU_MESYUARAT_HINGGA = " petang";
					} else {
						WAKTU_MESYUARAT_HINGGA = " malam";
					}
					MASA_DARI = MASA_DARI.substring(0, 2) + ":" + MASA_DARI.substring(2, 4) + WAKTU_MESYUARAT_DARI;
					MASA_HINGGA = MASA_HINGGA.substring(0, 2) + ":" + MASA_HINGGA.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
					
					h = new Hashtable();
					h.put("No", iCount);
					h.put("NoFail", rs.getString(1) == null ? "" : rs.getString(1));
					h.put("TajukMesyuarat", rs.getString(2) == null ? "" : rs.getString(2));
					h.put("Lokasi", rs.getString(3) == null ? "" : rs.getString(3));
					h.put("Tarikh", rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4)));
					h.put("Masa", MASA_DARI + " - " + MASA_HINGGA);
					h.put("ID", rs.getString(7) == null ? "" : rs.getString(7));
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
	
	public Boolean saveTempahan(String NO_FAIL, String TAJUK_MESYUARAT, String ID_SEKSYEN, String ID_LOKASI, String TARIKH_MESYUARAT, String MASA_DARI, String MASA_HINGGA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(NO_FAIL) && !"".equalsIgnoreCase(TAJUK_MESYUARAT) && !"".equalsIgnoreCase(ID_SEKSYEN) && !"".equalsIgnoreCase(ID_LOKASI) && !"".equalsIgnoreCase(TARIKH_MESYUARAT) && !"".equalsIgnoreCase(MASA_DARI) && !"".equalsIgnoreCase(MASA_HINGGA)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				SQLRenderer r = new SQLRenderer();
				String sql = "";
				String ID_FAIL = "";
				
				r.add("ID_FAIL");
				r.add("NO_FAIL", NO_FAIL);
				sql = r.getSQLSelect("TBLPFDFAIL");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
				}

				String WAKTU_MESYUARAT_DARI = "";
				String WAKTU_MESYUARAT_HINGGA = "";
				
				if (MASA_DARI.length() < 4) {
					MASA_DARI = String.format("0000", MASA_DARI);
				}
				if (MASA_HINGGA.length() < 4) {
					MASA_HINGGA = String.format("0000", MASA_HINGGA);
				}
				if (!isNumeric(MASA_DARI)) {
					MASA_DARI = "1000";
				}
				if (!isNumeric(MASA_HINGGA)) {
					MASA_HINGGA = "1100";
				}
				WAKTU_MESYUARAT_DARI = MASA_DARI.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = MASA_HINGGA.substring(0, 2);
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
				
				r.add("BIL_MESYUARAT", "1");
				r.add("ID_FAIL", ID_FAIL);
				r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT);
				r.add("TARIKH_MESYUARAT", r.unquote("TO_DATE('" + TARIKH_MESYUARAT + "', 'dd/MM/yyyy')"));
				r.add("ID_LOKASI", ID_LOKASI);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				r.add("MASA_MESYUARAT_DARI", MASA_DARI);
				r.add("WAKTU_MESYUARAT_DARI", WAKTU_MESYUARAT_DARI);
				r.add("MASA_MESYUARAT_HINGGA", MASA_HINGGA);
				r.add("WAKTU_MESYUARAT_HINGGA", WAKTU_MESYUARAT_HINGGA);
				r.add("ID_STATUS", "1");
				sql = r.getSQLInsert("TBLPFDMESYUARAT");
				stmt.execute(sql);
				returnValue = true;
			}
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
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				  "WHERE ID_NEGERI = '16'" +
				  "ORDER BY LOKASI, ID_LOKASI";
			
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

	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI, String user_negeri ) throws Exception {
		return getSOCLokasiMesyuaratNegeri(SOC_NAME, SELECTED_LOKASI, user_negeri, "", "");
	}
	
	public String getSOCLokasiMesyuaratNegeri(String SOC_NAME, String SELECTED_LOKASI, String user_negeri, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + "style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">-SILA PILIH-</option>\r\n";
			String ID_LOKASI = "", LOKASI = "";
			sql = "SELECT ID_LOKASI, SUBSTR(LOKASI, 1, 50) FROM TBLPFDRUJLOKASIMESYUARAT " +
				  "WHERE ID_NEGERI = '"+user_negeri+"'" +
				  "ORDER BY LOKASI, ID_LOKASI";
			
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

}
