package ekptg.model.integrasi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.intergration.XEkptgEmailSender;

public class FrmModelNilaianHartaTakAlih {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//IL start
static Logger myLog = Logger.getLogger(FrmModelNilaianHartaTakAlih.class);
	private Vector listHTA = new Vector();
	private Vector listHTAX = new Vector();
	
	public void setDataHTAX(String idPermohonansimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, DOKUMEN.ID_DOKUMEN , PELAN.ID_PELAN ,"
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, "
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,"
					+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA  "
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS ,  TBLPPKDOKUMEN DOKUMEN,  TBLPPKUPLOADPELAN PELAN"
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND H.ID_HTA = HP.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA "
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN "
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idPermohonansimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'T'  " + " ORDER BY H.ID_HTA DESC ";
//			myLogger.info("HTAAMX STRUCTURE BARU :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs
						.getString("id_dokumen"));
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs
						.getString("id_pelan"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs
						.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs
						.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs
						.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs
						.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));

				listHTAX.addElement(h);
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}

	}
	
	public void setDataHTA(String idsimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,"
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP , PELAN.ID_PELAN ,"
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN, "

					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					+ " H.SEKATAN, H.SYARAT_NYATA  "

					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLRUJJENISHAKMILIK RUJ , TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN  "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND HP.ID_HTA = H.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI " 
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA "
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'Y'  ORDER BY H.ID_HTA DESC";

			// System.out.println("HTAAM :"+sql.toUpperCase());
//			myLogger.info("HTAAM STRUCTURE BARU" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs
						.getString("id_dokumen"));
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs
						.getString("id_pelan"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));

				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());

				// System.out.println(h);
				listHTA.addElement(h);
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}

	}
	

//IL end
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector searchHTA(String NO_FAIL, String NO_PERMOHONAN, String NAMA_SIMATI, String NOKP_SIMATI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FAIL.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(M.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_SIMATI)) {
				SQL_SEARCH = " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + NAMA_SIMATI.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_SIMATI)) {
				SQL_SEARCH = " AND (UPPER(SM.NO_KP_BARU) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%' OR UPPER(SM.NO_KP_LAMA) LIKE '%" + NOKP_SIMATI.toUpperCase() + "%')";
			}
			sql = "SELECT M.ID_PERMOHONAN, FAIL.NO_FAIL, M.NO_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.ALAMAT_1, SM.ALAMAT_2, SM.ALAMAT_3 " +
				"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPFDFAIL FAIL " +
				"WHERE M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND M.ID_FAIL = FAIL.ID_FAIL" +
				SQL_SEARCH + " ORDER BY FAIL.NO_FAIL, SM.NAMA_SIMATI";
			int i = 1;
			String ID_PERMOHONAN = "", NO_KP_BARU = "", NO_KP_LAMA = "", NO_KP = "", ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", ALAMAT = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_SIMATI = rs.getString(4) == null ? "" : rs.getString(4);
				NO_KP_BARU = rs.getString(5) == null ? "" : rs.getString(5);
				NO_KP_LAMA = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT1 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT2 = rs.getString(8) == null ? "" : rs.getString(8);
				ALAMAT3 = rs.getString(9) == null ? "" : rs.getString(9);
				
				if (!"".equalsIgnoreCase(NO_KP_BARU)) {
					NO_KP = NO_KP_BARU;
				} else {
					NO_KP = NO_KP_LAMA;
				}
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
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaSiMati", NAMA_SIMATI);
				h.put("NoKPSiMati", NO_KP);
				h.put("Alamat", ALAMAT);
				v.add(h);
				i++;
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public String getIDPermohonan(String NO_FAIL) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				String sql = "";
				sql = "SELECT M.ID_PERMOHONAN " +
					"FROM TBLPPKPERMOHONAN M, TBLPFDFAIL FA " +
					"WHERE M.ID_FAIL = FA.ID_FAIL " +
					"AND FA.NO_FAIL = '" + NO_FAIL + "'";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getIDHTAFromIDPermohonanNoFail(String ID_PERMOHONAN, String NO_FAIL, String selectedTab) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(NO_FAIL)) {
				if ("1".equalsIgnoreCase(selectedTab)) {
					selectedTab = " AND HTA.JENIS_HTA != 'Y'";
				} else {
					selectedTab = " AND HTA.JENIS_HTA = 'Y'";
				}
					
				String sql = "";
				sql = "SELECT HTA.ID_HTA " +
					"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PMHNSM, TBLPFDFAIL FA, TBLPPKHTA HTA " +
					"WHERE M.ID_PERMOHONAN = PMHNSM.ID_PERMOHONAN AND M.ID_FAIL = FA.ID_FAIL AND PMHNSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " +
					"AND FA.NO_FAIL = '" + NO_FAIL + "'" + selectedTab;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public static String getCawanganUserJPPH(String USER_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_ID)) {
				String sql = "";
				sql = "SELECT UPPER(RJ.NAMA_PEJABAT) " +
					"FROM USERS U, USERS_INTEGRASI I, TBLINTRUJPEJABAT RJ " +
					"WHERE U.USER_ID = I.USER_ID AND I.ID_PEJABAT = RJ.ID_PEJABAT " +
					"AND U.USER_ID = " + USER_ID;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}			
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	// function is also used in nilaian HAK
	public static String getUnitUser(String USER_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_ID)) {
				String sql = "";
				sql = "SELECT UPPER(RJ.NAMA_PEJABAT) " +
					"FROM USERS U, USERS_INTERNAL I, TBLRUJPEJABATJKPTG RJ " +
					"WHERE U.USER_ID = I.USER_ID AND I.ID_PEJABATJKPTG = RJ.ID_PEJABATJKPTG " +
					"AND U.USER_ID = " + USER_ID;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	// function is also used in nilaian HAK
	public static String getNoTelefonUser(String USER_ID) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_ID)) {
				String sql = "";
				sql = "SELECT UPPER(RJ.NO_TEL) " +
					"FROM USERS U, USERS_INTERNAL I, TBLRUJPEJABATJKPTG RJ " +
					"WHERE U.USER_ID = I.USER_ID AND I.ID_PEJABATJKPTG = RJ.ID_PEJABATJKPTG " +
					"AND U.USER_ID = " + USER_ID;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean isPermohonanSelesai(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA) && !"".equalsIgnoreCase(JENIS_HTA)) {
				String sql = "", TEMP = "", CHILD_TABLE = "TBLINTJPPHAH";
				if ("1".equalsIgnoreCase(JENIS_HTA)) {
					CHILD_TABLE = "TBLINTJPPHTH";
				}
				sql = "SELECT UPPER(STATUS_PROSES) " +
					"FROM " + CHILD_TABLE + " " +
					"WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					TEMP = rs.getString(1) == null ? "" : rs.getString(1);
				}
				if ("SELESAI".equalsIgnoreCase(TEMP)) {
					returnValue = true;
				}
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector viewHTAMaklumatPermohonan(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				String SQL_JENIS_HTA = "";
				if (JENIS_HTA.trim().equalsIgnoreCase("1")) {
					SQL_JENIS_HTA = "AND UPPER(HTA.JENIS_HTA) != 'Y' ";
				}

				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
								
				String MP_NOFAIL = "", MP_NOPERMOHONAN = "", MP_NEGERI = "", MP_DAERAH = "", MP_UNIT = "", MP_TARIKHMOHON = "", MP_NAMAPEMOHON = "";
				String MP_NOKPPEMOHON = "", MP_ALAMATPEMOHON = "", MP_NOTELPEMOHON = "", MP_NAMASIMATI = "", MP_NOKPSIMATI = "", MP_TARIKHMATI = "";
				String MP_NOKPBARUPEMOHON = "", MP_NOKPLAMAPEMOHON = "", MP_NOKPLAINPEMOHON = "";
				String MP_NOKPBARUSIMATI = "", MP_NOKPLAMASIMATI = "", MP_NOKPLAINSIMATI = "";
				String MP_ALAMAT1PEMOHON = "", MP_ALAMAT2PEMOHON = "", MP_ALAMAT3PEMOHON = "";

				// get UNIT value from TBLINTJPPHAH/TH (in case user is JPPH)
				if ("1".equalsIgnoreCase(JENIS_HTA.trim())) {
					sql = "SELECT UNIT FROM TBLINTJPPHTH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				} else {
					sql = "SELECT UNIT FROM TBLINTJPPHAH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				}
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					MP_UNIT = rs.getString(1) == null ? "" : rs.getString(1);
				}
				
				sql = "SELECT FA.NO_FAIL, M.NO_PERMOHONAN, NG.NAMA_NEGERI, DA.NAMA_DAERAH, '', M.TARIKH_MOHON, PMHN.NAMA_PEMOHON, PMHN.NO_KP_BARU, " +
					"PMHN.NO_KP_LAMA, PMHN.NO_KP_LAIN, PMHN.ALAMAT_1, PMHN.ALAMAT_2, PMHN.ALAMAT_3, PMHN.NO_TEL, SM.NAMA_SIMATI, " +
					"SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.TARIKH_MATI " +
					"FROM TBLPPKPERMOHONAN M, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPPKPEMOHON PMHN, TBLPPKHTA HTA " +
					"WHERE M.ID_FAIL = FA.ID_FAIL AND M.ID_NEGERIMHN = NG.ID_NEGERI(+) AND M.ID_DAERAHMHN = DA.ID_DAERAH(+) AND M.ID_PERMOHONAN = PSM.ID_PERMOHONAN " +
					"AND PSM.ID_SIMATI = SM.ID_SIMATI AND M.ID_PEMOHON = PMHN.ID_PEMOHON AND SM.ID_SIMATI = HTA.ID_SIMATI " +
					SQL_JENIS_HTA + "AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND HTA.ID_HTA = " + ID_HTA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					MP_NOFAIL = rs.getString(1) == null ? "" : rs.getString(1);
					MP_NOPERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
					MP_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
					MP_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
					// commented because we take UNIT from TBLINTJPPHAH/TH (if exist)
					// MP_UNIT = rs.getString(5) == null ? "" : rs.getString(5);
					MP_TARIKHMOHON = rs.getDate(6) == null ? "" : sdf.format(rs.getDate(6));
					MP_NAMAPEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					MP_NOKPBARUPEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
					MP_NOKPLAMAPEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
					MP_NOKPLAINPEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
					MP_ALAMAT1PEMOHON = rs.getString(11) == null ? "" : rs.getString(11);
					MP_ALAMAT2PEMOHON = rs.getString(12) == null ? "" : rs.getString(12);
					MP_ALAMAT3PEMOHON = rs.getString(13) == null ? "" : rs.getString(13);
					MP_NOTELPEMOHON = rs.getString(14) == null ? "" : rs.getString(14);
					MP_NAMASIMATI = rs.getString(15) == null ? "" : rs.getString(15);
					MP_NOKPBARUSIMATI = rs.getString(16) == null ? "" : rs.getString(16);
					MP_NOKPLAMASIMATI = rs.getString(17) == null ? "" : rs.getString(17);
					MP_NOKPLAINSIMATI = rs.getString(18) == null ? "" : rs.getString(18);
					MP_TARIKHMATI = rs.getDate(19) == null ? "" : sdf.format(rs.getDate(19));
					if (!"".equalsIgnoreCase(MP_NOKPBARUPEMOHON)) {
						MP_NOKPPEMOHON = MP_NOKPBARUPEMOHON;
					} else if (!"".equalsIgnoreCase(MP_NOKPLAMAPEMOHON)){
						MP_NOKPPEMOHON = MP_NOKPLAMAPEMOHON;
					} else {
						MP_NOKPPEMOHON = MP_NOKPLAINPEMOHON;
					}
					if (!"".equalsIgnoreCase(MP_NOKPBARUSIMATI)) {
						MP_NOKPSIMATI = MP_NOKPBARUSIMATI;
					} else if (!"".equalsIgnoreCase(MP_NOKPLAMASIMATI)){
						MP_NOKPSIMATI = MP_NOKPLAMASIMATI;
					} else {
						MP_NOKPSIMATI = MP_NOKPLAINSIMATI;
					}
					if (!"".equalsIgnoreCase(MP_ALAMAT1PEMOHON)) {
						MP_ALAMATPEMOHON = MP_ALAMAT1PEMOHON;
					}
					if (!"".equalsIgnoreCase(MP_ALAMAT2PEMOHON)) {
						MP_ALAMATPEMOHON += ", " + MP_ALAMAT2PEMOHON;
					}
					if (!"".equalsIgnoreCase(MP_ALAMAT3PEMOHON)) {
						MP_ALAMATPEMOHON += ", " + MP_ALAMAT3PEMOHON;
					}
					
					Calendar cal = Calendar.getInstance();
					cal.set(1991, 11, 1);
					Date tarikhMati = (Date) (rs.getDate(19) == null ? "" : rs.getDate(19));					

					h = new Hashtable();
					h.put("MP_NOFAIL", MP_NOFAIL);
					h.put("MP_NOPERMOHONAN", MP_NOPERMOHONAN);
					h.put("MP_NEGERI", MP_NEGERI);
					h.put("MP_DAERAH", MP_DAERAH);
					h.put("MP_UNIT", MP_UNIT);
					h.put("MP_TARIKHMOHON", MP_TARIKHMOHON);
					h.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
					h.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
					h.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
					h.put("MP_NOTELPEMOHON", MP_NOTELPEMOHON);
					h.put("MP_NAMASIMATI", MP_NAMASIMATI);
					h.put("MP_NOKPSIMATI", MP_NOKPSIMATI);
					h.put("MP_TARIKHMATI", MP_TARIKHMATI);
					h.put("COND_DISPNILAIANTARIKHMATI", cal.before(tarikhMati));
					v.add(h);
				}
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector viewNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				
				String PERLU_NILAIAN_TARIKH_MATI = "", ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "", SEKSYEN = "", JENIS_HAKMILIK = "";
				String NO_HAKMILIK = "", JENIS_PEMBANGUNAN = "", NO_PTLOT = "", JENIS_PTLOT = "", NO_BANGUNAN = "", NO_TINGKAT = "", NO_PETAK = "", NO_RSS = "";
				String BA_SIMATI = "", BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "", TEMPOH_PAJAKAN = "";
				String KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "", LUAS_TANAH = "", JENIS_LUAS_TANAH = "", LUAS_PETAK = "";
				String ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "", JENIS_HARTA_DINILAI = "", CATATAN = "", NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "";
				String NAMA_PEGAWAI_JKPTG = "", NO_TEL_PEGAWAI_JKPTG = "", CAWANGAN_PEGAWAI_JKPTG = "", EMAIL_ADDR_PEGAWAI_JPPH = "", EMAIL_SEND_JPPH = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NILAI_TARIKH_MATI = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "", JPPH_TARIKH_LAWAT_PERIKSA = "";
				
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					sql = "SELECT PERLU_NILAIAN_TARIKH_MATI, ID_NEGERI, ID_DAERAH, ID_MUKIM, SEKSYEN, JENIS_HAKMILIK, NO_HAKMILIK, NO_PTLOT, JENIS_PTLOT, " +
						"NO_BANGUNAN, NO_TINGKAT, NO_PETAK, NO_RSS, BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, " +
						"SYARAT_NYATA, SEKATAN_KEPENTINGAN, LUAS_TANAH, JENIS_LUAS_TANAH, LUAS_PETAK, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, JENIS_HARTA_DINILAI, " +
						"CATATAN, '', '', '', NAMA_PEGAWAI_JKPTG, NO_TEL_PEGAWAI_JKPTG, CAWANGAN_PEGAWAI_JKPTG, EMAIL_ADDR_PEGAWAI_JPPH, EMAIL_SEND_JPPH, " +
						"JPPH_NILAI_TARIKH_MOHON, JPPH_NILAI_TARIKH_MATI, JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN, JPPH_TARIKH_LAWAT_PERIKSA, " +
						"JENIS_PEMBANGUNAN " +
						"FROM TBLINTJPPHAH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				} else {
					sql = "SELECT PERLU_NILAIAN_TARIKH_MATI, ID_NEGERI, ID_DAERAH, ID_MUKIM, SEKSYEN, '', '', NO_PTLOT, JENIS_PTLOT, " +
						"NO_BANGUNAN, NO_TINGKAT, NO_PETAK, '', BA_SIMATI, BB_SIMATI, JENIS_PEGANGAN, TARIKH_LUPUT_PAJAKAN, TEMPOH_PAJAKAN, KATEGORI_TANAH, " +
						"SYARAT_NYATA, SEKATAN_KEPENTINGAN, '', '', LUAS_PETAK, ALAMAT1_HARTA, ALAMAT2_HARTA, ALAMAT3_HARTA, JENIS_HARTA_DINILAI, " +
						"CATATAN, TARIKH_PERJANJIAN, NAMA_PEMAJU, LUAS_PETAK, NAMA_PEGAWAI_JKPTG, NO_TEL_PEGAWAI_JKPTG, CAWANGAN_PEGAWAI_JKPTG, EMAIL_ADDR_PEGAWAI_JPPH, EMAIL_SEND_JPPH, " +
						"JPPH_NILAI_TARIKH_MOHON, JPPH_NILAI_TARIKH_MATI, JPPH_NAMA_PEGAWAI, JPPH_CAWANGAN_JPPH, JPPH_CATATAN, JPPH_TARIKH_LAWAT_PERIKSA, " +
						"'' " +
						"FROM TBLINTJPPHTH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				}
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					PERLU_NILAIAN_TARIKH_MATI = rs.getString(1) == null ? "" : rs.getString(1);
					ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
					ID_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
					ID_MUKIM = rs.getString(4) == null ? "" : rs.getString(4);
					SEKSYEN = rs.getString(5) == null ? "" : rs.getString(5);
					JENIS_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
					NO_HAKMILIK = rs.getString(7) == null ? "" : rs.getString(7);
					NO_PTLOT = rs.getString(8) == null ? "" : rs.getString(8);
					JENIS_PTLOT = rs.getString(9) == null ? "" : rs.getString(9);
					NO_BANGUNAN = rs.getString(10) == null ? "" : rs.getString(10);
					NO_TINGKAT = rs.getString(11) == null ? "" : rs.getString(11);
					NO_PETAK = rs.getString(12) == null ? "" : rs.getString(12);
					NO_RSS = rs.getString(13) == null ? "" : rs.getString(13);
					BA_SIMATI = rs.getString(14) == null ? "" : rs.getString(14);
					BB_SIMATI = rs.getString(15) == null ? "" : rs.getString(15);
					JENIS_PEGANGAN = rs.getString(16) == null ? "" : rs.getString(16);
					TARIKH_LUPUT_PAJAKAN = rs.getDate(17) == null ? "" : sdf.format(rs.getDate(17));
					TEMPOH_PAJAKAN = rs.getString(18) == null ? "" : rs.getString(18);
					KATEGORI_TANAH = rs.getString(19) == null ? "" : rs.getString(19);
					SYARAT_NYATA = rs.getString(20) == null ? "" : rs.getString(20);
					SEKATAN_KEPENTINGAN = rs.getString(21) == null ? "" : rs.getString(21);
					LUAS_TANAH = rs.getString(22) == null ? "" : rs.getString(22);
					JENIS_LUAS_TANAH = rs.getString(23) == null ? "" : rs.getString(23);
					LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
					ALAMAT1_HARTA = rs.getString(25) == null ? "" : rs.getString(25);
					ALAMAT2_HARTA = rs.getString(26) == null ? "" : rs.getString(26);
					ALAMAT3_HARTA = rs.getString(27) == null ? "" : rs.getString(27);
					JENIS_HARTA_DINILAI = rs.getString(28) == null ? "" : rs.getString(28);						
					CATATAN = rs.getString(29) == null ? "" : rs.getString(29);
					NAMA_PEMILIK = rs.getString(30) == null ? "" : rs.getString(30);
					TARIKH_PERJANJIAN = rs.getDate(31) == null ? "" : sdf.format(rs.getDate(31));
					NAMA_PEMAJU = rs.getString(32) == null ? "" : rs.getString(32);					
					NAMA_PEGAWAI_JKPTG = rs.getString(33) == null ? "" : rs.getString(33);
					NO_TEL_PEGAWAI_JKPTG = rs.getString(34) == null ? "" : rs.getString(34);
					CAWANGAN_PEGAWAI_JKPTG = rs.getString(35) == null ? "" : rs.getString(35);
					EMAIL_ADDR_PEGAWAI_JPPH = rs.getString(36) == null ? "" : rs.getString(36);
					EMAIL_SEND_JPPH = rs.getString(37) == null ? "" : rs.getString(37);
					JPPH_NILAI_TARIKH_MOHON = rs.getString(38) == null ? "" : rs.getString(38);
					JPPH_NILAI_TARIKH_MATI = rs.getString(39) == null ? "" : rs.getString(39);
					JPPH_NAMA_PEGAWAI = rs.getString(40) == null ? "" : rs.getString(40);
					JPPH_CAWANGAN_JPPH = rs.getString(41) == null ? "" : rs.getString(41);
					JPPH_CATATAN = rs.getString(42) == null ? "" : rs.getString(42);
					JPPH_TARIKH_LAWAT_PERIKSA = rs.getDate(43) == null ? "" : sdf.format(rs.getDate(43));
					JENIS_PEMBANGUNAN = rs.getString(44) == null ? "" : rs.getString(44);
				}
				
				if (!haveINTData) {
					String SQL_JENIS_HTA = "";
					if ("0".equalsIgnoreCase(JENIS_HTA.trim())) {
						SQL_JENIS_HTA = "AND UPPER(HTA.JENIS_HTA) = 'Y' ";
					} else {
						SQL_JENIS_HTA = "AND UPPER(HTA.JENIS_HTA) = 'T' ";
					}
					sql = "SELECT '', HTA.ID_NEGERI, HTA.ID_DAERAH, HTA.ID_MUKIM, '', HTA.ID_JENISHM, HTA.NO_HAKMILIK, HTA.NO_PT, '', HTA.NO_BANGUNAN, HTA.NO_TINGKAT, " +
						"HTA.NO_STRATA, '', HTA.BA_SIMATI, HTA.BB_SIMATI, '', '', '', HTA.ID_KATEGORI, '', '', HTA.LUAS, HTA.ID_LUAS, HTA.LUAS_HMP, HTA.ALAMAT_HTA1, HTA.ALAMAT_HTA2, " +
						"HTA.ALAMAT_HTA3, '', '', '', HTA.TARIKH_PERJANJIAN, HTA.NAMA_PEMAJU, '', '', '', '', '', '', '', '', '', '', '', '' " +
						"FROM TBLPPKPERMOHONAN M, TBLPPKPERMOHONANSIMATI PSM, TBLPPKHTA HTA " +
						"WHERE M.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = HTA.ID_SIMATI " +
						SQL_JENIS_HTA + "AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND HTA.ID_HTA = " + ID_HTA;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						PERLU_NILAIAN_TARIKH_MATI = rs.getString(1) == null ? "" : rs.getString(1);
						ID_NEGERI = rs.getString(2) == null ? "" : rs.getString(2);
						ID_DAERAH = rs.getString(3) == null ? "" : rs.getString(3);
						ID_MUKIM = rs.getString(4) == null ? "" : rs.getString(4);
						SEKSYEN = rs.getString(5) == null ? "" : rs.getString(5);
						JENIS_HAKMILIK = rs.getString(6) == null ? "" : rs.getString(6);
						NO_HAKMILIK = rs.getString(7) == null ? "" : rs.getString(7);
						NO_PTLOT = rs.getString(8) == null ? "" : rs.getString(8);
						JENIS_PTLOT = rs.getString(9) == null ? "" : rs.getString(9);
						NO_BANGUNAN = rs.getString(10) == null ? "" : rs.getString(10);
						NO_TINGKAT = rs.getString(11) == null ? "" : rs.getString(11);
						NO_PETAK = rs.getString(12) == null ? "" : rs.getString(12);
						NO_RSS = rs.getString(13) == null ? "" : rs.getString(13);
						BA_SIMATI = rs.getString(14) == null ? "" : rs.getString(14);
						BB_SIMATI = rs.getString(15) == null ? "" : rs.getString(15);
						JENIS_PEGANGAN = rs.getString(16) == null ? "" : rs.getString(16);
						TARIKH_LUPUT_PAJAKAN = rs.getDate(17) == null ? "" : sdf.format(rs.getDate(17));
						TEMPOH_PAJAKAN = rs.getString(18) == null ? "" : rs.getString(18);
						KATEGORI_TANAH = rs.getString(19) == null ? "" : rs.getString(19);
						SYARAT_NYATA = rs.getString(20) == null ? "" : rs.getString(20);
						SEKATAN_KEPENTINGAN = rs.getString(21) == null ? "" : rs.getString(21);
						LUAS_TANAH = rs.getString(22) == null ? "" : rs.getString(22);
						JENIS_LUAS_TANAH = rs.getString(23) == null ? "" : rs.getString(23);
						LUAS_PETAK = rs.getString(24) == null ? "" : rs.getString(24);
						ALAMAT1_HARTA = rs.getString(25) == null ? "" : rs.getString(25);
						ALAMAT2_HARTA = rs.getString(26) == null ? "" : rs.getString(26);
						ALAMAT3_HARTA = rs.getString(27) == null ? "" : rs.getString(27);
						JENIS_HARTA_DINILAI = rs.getString(28) == null ? "" : rs.getString(28);						
						CATATAN = rs.getString(29) == null ? "" : rs.getString(29);
						NAMA_PEMILIK = rs.getString(30) == null ? "" : rs.getString(30);
						TARIKH_PERJANJIAN = rs.getDate(31) == null ? "" : sdf.format(rs.getDate(31));
						NAMA_PEMAJU = rs.getString(32) == null ? "" : rs.getString(32);					
						NAMA_PEGAWAI_JKPTG = rs.getString(33) == null ? "" : rs.getString(33);
						NO_TEL_PEGAWAI_JKPTG = rs.getString(34) == null ? "" : rs.getString(34);
						CAWANGAN_PEGAWAI_JKPTG = rs.getString(35) == null ? "" : rs.getString(35);
						EMAIL_ADDR_PEGAWAI_JPPH = rs.getString(36) == null ? "" : rs.getString(36);
						EMAIL_SEND_JPPH = rs.getString(37) == null ? "" : rs.getString(37);
						JPPH_NILAI_TARIKH_MOHON = rs.getString(38) == null ? "" : rs.getString(38);
						JPPH_NILAI_TARIKH_MATI = rs.getString(39) == null ? "" : rs.getString(39);
						JPPH_NAMA_PEGAWAI = rs.getString(40) == null ? "" : rs.getString(40);
						JPPH_CAWANGAN_JPPH = rs.getString(41) == null ? "" : rs.getString(41);
						JPPH_CATATAN = rs.getString(42) == null ? "" : rs.getString(42);
						JPPH_TARIKH_LAWAT_PERIKSA = rs.getDate(43) == null ? "" : sdf.format(rs.getDate(43));
						JENIS_PEMBANGUNAN = rs.getString(44) == null ? "" : rs.getString(44);
					}
				}
				if (!"".equalsIgnoreCase(LUAS_TANAH.trim())) {
					if (".".equalsIgnoreCase(LUAS_TANAH.substring(0, 1))) {
						LUAS_TANAH = "0" + LUAS_TANAH;
					}
				}
				if (!"".equalsIgnoreCase(LUAS_PETAK.trim())) {
					if (".".equalsIgnoreCase(LUAS_PETAK.substring(0, 1))) {
						LUAS_PETAK = "0" + LUAS_PETAK;
					}
				}
				h = new Hashtable();
				h.put("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
				h.put("ID_NEGERI", ID_NEGERI);
				h.put("ID_DAERAH", ID_DAERAH);
				h.put("ID_MUKIM", ID_MUKIM);
				h.put("SEKSYEN", SEKSYEN);
				h.put("JENIS_HAKMILIK", JENIS_HAKMILIK);
				h.put("NO_HAKMILIK", NO_HAKMILIK);
				h.put("JENIS_PEMBANGUNAN", JENIS_PEMBANGUNAN);
				h.put("NO_PTLOT", NO_PTLOT);
				h.put("JENIS_PTLOT", JENIS_PTLOT);
				h.put("NO_BANGUNAN", NO_BANGUNAN);
				h.put("NO_TINGKAT", NO_TINGKAT);
				h.put("NO_PETAK", NO_PETAK);
				h.put("NO_RSS", NO_RSS);
				h.put("BA_SIMATI", BA_SIMATI);
				h.put("BB_SIMATI", BB_SIMATI);
				h.put("JENIS_PEGANGAN", JENIS_PEGANGAN);
				h.put("TARIKH_LUPUT_PAJAKAN", TARIKH_LUPUT_PAJAKAN);
				h.put("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
				h.put("KATEGORI_TANAH", KATEGORI_TANAH);
				h.put("SYARAT_NYATA", SYARAT_NYATA);
				h.put("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
				h.put("LUAS_TANAH", LUAS_TANAH);
				h.put("JENIS_LUAS_TANAH", JENIS_LUAS_TANAH);
				h.put("LUAS_PETAK", LUAS_PETAK);
				h.put("ALAMAT1_HARTA", ALAMAT1_HARTA);
				h.put("ALAMAT2_HARTA", ALAMAT2_HARTA);
				h.put("ALAMAT3_HARTA", ALAMAT3_HARTA);
				h.put("JENIS_HARTA_DINILAI", JENIS_HARTA_DINILAI);
				h.put("CATATAN", CATATAN);
				h.put("NAMA_PEMILIK", NAMA_PEMILIK);
				h.put("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
				h.put("NAMA_PEMAJU", NAMA_PEMAJU);
				h.put("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
				h.put("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
				h.put("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
				h.put("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
				h.put("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				h.put("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
				h.put("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
				h.put("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
				h.put("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("JPPH_TARIKH_LAWAT_PERIKSA", JPPH_TARIKH_LAWAT_PERIKSA);
				h.put("haveINTData", haveINTData.toString());
				v.add(h);
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	//public Boolean sendNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA, String USER_ID, Boolean isJPPHUser) throws Exception {
	public Boolean sendNilaianHTA(String ID_PERMOHONAN, Vector vecID_HTA, String JENIS_HTA, String USER_ID, Boolean isJPPHUser, Hashtable h) throws Exception {//IL
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			for(int i=0; i<vecID_HTA.size(); i++){//IL
				String ID_HTA = (String) vecID_HTA.get(i);//IL
				
			if (!"".equalsIgnoreCase(ID_HTA)) {
				Statement stmt = db.getStatement();
				String sql = "";
				
				String STATUS = "";
				if (isJPPHUser) {
					STATUS = "SELESAI";
				} else {
					STATUS = "BARU";
				}
				
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					//IL start
					ResultSet rs = null;
					sql = "SELECT COUNT(ID_HTA) AS BIL FROM TBLINTJPPHAH WHERE ID_HTA = "+ID_HTA;//IL
					rs = stmt.executeQuery(sql);//IL
						
					if(rs.next()){//IL
						String bil = rs.getString("BIL") == null ? "" : rs.getString("BIL");//IL
								
						if(Long.parseLong(bil) > 0){//IL		
							sql = "UPDATE TBLINTJPPHAH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;
						}else{
							saveNilaianHTABasic(ID_PERMOHONAN, ID_HTA, JENIS_HTA, USER_ID, isJPPHUser, h);
						}
					}

				} else {
					//IL comment
					//sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;

						ResultSet rs = null;
						sql = "SELECT COUNT(ID_HTA) AS BIL FROM TBLINTJPPHTH WHERE ID_HTA = "+ID_HTA;
						rs = stmt.executeQuery(sql);
						
						if(rs.next()){
							String bil = rs.getString("BIL") == null ? "" : rs.getString("BIL");
							
							if(Long.parseLong(bil) > 0){
								sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = '" + STATUS + "', ID_KEMASKINI = "+USER_ID+", TARIKH_KEMASKINI = SYSDATE WHERE ID_HTA = " + ID_HTA;
							} else {
								saveNilaianHTABasic(ID_PERMOHONAN, ID_HTA, JENIS_HTA, USER_ID, isJPPHUser, h);
							}
						}
				}
				stmt.execute(sql);
				
				// should send email to JKPTG user if nilaian is completed by JPPH
				if (isJPPHUser) {
					// ****************************
					// sending e-mail codes
					ResultSet rs = null;
	
					// get NO_FAIL
					String NO_FAIL = "";
					if ("0".equalsIgnoreCase(JENIS_HTA)) {
						sql = "SELECT FA.NO_FAIL " +
							"FROM TBLINTJPPHAH M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA " +
							"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HTA = " + ID_HTA;
					} else {
						sql = "SELECT FA.NO_FAIL " +
							"FROM TBLINTJPPHTH M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA " +
							"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " +
							"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HTA = " + ID_HTA;
					}
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					}
					
					// get e-mail address of JKPTG user that saves this file
					String EMAIL_ADDR = "";
					sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						EMAIL_ADDR = rs.getString(1) == null ? "" : rs.getString(1);
					}
					
					// temporary hardcode
					EMAIL_ADDR = "zulkhaimi@hla-group.com";
	
					String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
					sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
						EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
						EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
						EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
						EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
						EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
					}

					if (!"".equalsIgnoreCase(EMAIL_ADDR.trim()) && !"".equalsIgnoreCase(EMAIL_FROM.trim()) 
							&& !"".equalsIgnoreCase(EMAIL_SUBJECT.trim()) && !"".equalsIgnoreCase(EMAIL_BODY.trim())) {
						EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
						if (isJPPHUser) {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
						} else {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
						}
						Date date = new Date();
						if ("0".equalsIgnoreCase(JENIS_HTA)) {
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Ada Hakmilik)");
						} else {
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Tiada Hakmilik)");
						}
						EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
						EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS);
						EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
						XEkptgEmailSender email = XEkptgEmailSender.getInstance();
						email.FROM = EMAIL_FROM;
						email.MULTIPLE_RECIEPIENT = new String[1];
						email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR;
						if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
							if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
								email.TO_CC = new String[2];
								email.TO_CC[0] = EMAIL_CC_1;
								email.TO_CC[1] = EMAIL_CC_2;
							} else {
								email.TO_CC = new String[1];
								if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
									email.TO_CC[0] = EMAIL_CC_1;
								} else {
									email.TO_CC[0] = EMAIL_CC_2;
								}
							}
						}
						email.RECIEPIENT = EMAIL_ADDR;
						email.SUBJECT = EMAIL_SUBJECT;
						email.MESSAGE = EMAIL_BODY;
						email.sendEmail();				
					}
					// ****************************				
				}
				
				returnValue = true;
				}
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		
		return returnValue;
	}
	
	//IL start
	private void saveNilaianHTABasic(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA, String USER_ID, Boolean isJPPHUser, Hashtable h) throws Exception{
		Db db = new Db();
		SQLRenderer r = new SQLRenderer();
		Statement stmt = db.getStatement();
		ResultSet rs = null;
//		Boolean haveData = false;
		String sql = "";
		String CHILD_TABLE = "TBLINTJPPHAH";
		String CHILD_TABLE_ID = "ID_JPPHAH";
		if ("1".equalsIgnoreCase(JENIS_HTA)) {
			CHILD_TABLE = "TBLINTJPPHTH";
			CHILD_TABLE_ID = "ID_JPPHTH";
		}
		
		// save
		long ID_KEY = 0;
		String STATUS_PROSES = "DALAM PROSES", UNIT = "", PERLU_NILAIAN_TARIKH_MATI = "", ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "";
		String SEKSYEN = "", JENIS_HAKMILIK = "", NO_HAKMILIK = "", NO_PTLOT = "", JENIS_PTLOT = "", NO_BANGUNAN = "", NO_TINGKAT = "", NO_PETAK = "", NO_RSS = "";
		String BA_SIMATI = "", BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "", TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "";
		String LUAS_TANAH = "", JENIS_LUAS_TANAH = "", LUAS_PETAK = "", ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "";
		String JENIS_HARTA_DINILAI = "", CATATAN = "", NAMA_PEGAWAI_JKPTG = "", NO_TEL_PEGAWAI_JKPTG = "", CAWANGAN_PEGAWAI_JKPTG = "";
		String EMAIL_ADDR_PEGAWAI_JPPH = "", EMAIL_SEND_JPPH = "", NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "";
		String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NILAI_TARIKH_MATI = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "";
		String JPPH_TARIKH_LAWAT_PERIKSA = "";

		UNIT = (String) h.get("UNIT").toString().toUpperCase();
		PERLU_NILAIAN_TARIKH_MATI = (String) h.get("PERLU_NILAIAN_TARIKH_MATI").toString().toUpperCase();
		ID_NEGERI = (String) h.get("ID_NEGERI").toString().toUpperCase();
		ID_DAERAH = (String) h.get("ID_DAERAH").toString().toUpperCase();
		ID_MUKIM = (String) h.get("ID_MUKIM").toString().toUpperCase();
		SEKSYEN = (String) h.get("SEKSYEN").toString().toUpperCase();
		JENIS_HAKMILIK = (String) h.get("JENIS_HAKMILIK").toString().toUpperCase();
		NO_HAKMILIK = (String) h.get("NO_HAKMILIK").toString().toUpperCase();
		NO_PTLOT = (String) h.get("NO_PTLOT").toString().toUpperCase();
		JENIS_PTLOT = (String) h.get("JENIS_PTLOT").toString().toUpperCase();
		NO_BANGUNAN = (String) h.get("NO_BANGUNAN").toString().toUpperCase();
		NO_TINGKAT = (String) h.get("NO_TINGKAT").toString().toUpperCase();
		NO_PETAK = (String) h.get("NO_PETAK").toString().toUpperCase();
		NO_RSS = (String) h.get("NO_RSS").toString().toUpperCase();
		BA_SIMATI = (String) h.get("BA_SIMATI").toString().toUpperCase();
		BB_SIMATI = (String) h.get("BB_SIMATI").toString().toUpperCase();
		JENIS_PEGANGAN = (String) h.get("JENIS_PEGANGAN").toString().toUpperCase();
		TARIKH_LUPUT_PAJAKAN = (String) h.get("TARIKH_LUPUT_PAJAKAN").toString().toUpperCase();
		TEMPOH_PAJAKAN = (String) h.get("TEMPOH_PAJAKAN").toString().toUpperCase();
		KATEGORI_TANAH = (String) h.get("KATEGORI_TANAH").toString().toUpperCase();
		SYARAT_NYATA = (String) h.get("SYARAT_NYATA").toString().toUpperCase();
		SEKATAN_KEPENTINGAN = (String) h.get("SEKATAN_KEPENTINGAN").toString().toUpperCase();
		LUAS_TANAH = (String) h.get("LUAS_TANAH").toString().toUpperCase();
		JENIS_LUAS_TANAH = (String) h.get("JENIS_LUAS_TANAH").toString().toUpperCase();
		LUAS_PETAK = (String) h.get("LUAS_PETAK").toString().toUpperCase();
		ALAMAT1_HARTA = (String) h.get("ALAMAT1_HARTA").toString().toUpperCase();
		ALAMAT2_HARTA = (String) h.get("ALAMAT2_HARTA").toString().toUpperCase();
		ALAMAT3_HARTA = (String) h.get("ALAMAT3_HARTA").toString().toUpperCase();
		JENIS_HARTA_DINILAI = (String) h.get("JENIS_HARTA_DINILAI").toString().toUpperCase();
		CATATAN = (String) h.get("CATATAN").toString().toUpperCase();
		NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG").toString().toUpperCase();
		NO_TEL_PEGAWAI_JKPTG = (String) h.get("NO_TEL_PEGAWAI_JKPTG").toString().toUpperCase();
		CAWANGAN_PEGAWAI_JKPTG = (String) h.get("CAWANGAN_PEGAWAI_JKPTG").toString().toUpperCase();
		JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_TARIKH_MOHON").toString().toUpperCase();
		JPPH_NILAI_TARIKH_MATI = (String) h.get("JPPH_NILAI_TARIKH_MATI").toString().toUpperCase();
		JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI").toString().toUpperCase();
		JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_CAWANGAN_JPPH").toString().toUpperCase();
		JPPH_CATATAN = (String) h.get("JPPH_CATATAN").toString().toUpperCase();
		JPPH_TARIKH_LAWAT_PERIKSA = (String) h.get("JPPH_TARIKH_LAWAT_PERIKSA").toString().toUpperCase();
		
		// check in TBLINTJPPHTH
		r.clear();
		r.add(CHILD_TABLE_ID);
		r.add("ID_PERMOHONAN", ID_PERMOHONAN);
		sql = r.getSQLSelect(CHILD_TABLE);
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			ID_KEY = rs.getInt(1);
//			haveData = true;
		} else {
//			haveData = false;
		}
		
		if (isJPPHUser) {
			STATUS_PROSES = "DALAM PROSES JPPH";
		} else {
			STATUS_PROSES = "BARU";
		}
		
		r.clear();
//		if (haveData) {
//			r.update(CHILD_TABLE_ID, ID_KEY);
//		} else {
			ID_KEY = DB.getNextID(CHILD_TABLE + "_SEQ");
			r.add(CHILD_TABLE_ID, ID_KEY);
			r.add("ID_SIMPAN", USER_ID);
			r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
//		}
		r.add("STATUS_PROSES", STATUS_PROSES);
		if (!isJPPHUser) {
			r.add("ID_PERMOHONAN", ID_PERMOHONAN);
			r.add("ID_HTA", ID_HTA);
			r.add("ID_KEMASKINI", USER_ID);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("UNIT", UNIT);
			r.add("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_DAERAH", ID_DAERAH);
			r.add("ID_MUKIM", ID_MUKIM);
			r.add("SEKSYEN", SEKSYEN);
			r.add("JENIS_HAKMILIK", JENIS_HAKMILIK);
			r.add("NO_HAKMILIK", NO_HAKMILIK);
			r.add("NO_PTLOT", NO_PTLOT);
			r.add("JENIS_PTLOT", JENIS_PTLOT);
			r.add("NO_BANGUNAN", NO_BANGUNAN);
			r.add("NO_TINGKAT", NO_TINGKAT);
			r.add("NO_PETAK", NO_PETAK);
			r.add("NO_RSS", NO_RSS);
			r.add("BA_SIMATI", BA_SIMATI);
			r.add("BB_SIMATI", BB_SIMATI);
			r.add("JENIS_PEGANGAN", JENIS_PEGANGAN);
			r.add("TARIKH_LUPUT_PAJAKAN", r.unquote("TO_DATE('" + TARIKH_LUPUT_PAJAKAN + "', 'dd/MM/yyyy')"));
			r.add("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
			r.add("KATEGORI_TANAH", KATEGORI_TANAH);
			r.add("SYARAT_NYATA", SYARAT_NYATA);
			r.add("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
			r.add("LUAS_TANAH", LUAS_TANAH);
			r.add("JENIS_LUAS_TANAH", JENIS_LUAS_TANAH);
			r.add("LUAS_PETAK", LUAS_PETAK);
			r.add("ALAMAT1_HARTA", ALAMAT1_HARTA);
			r.add("ALAMAT2_HARTA", ALAMAT2_HARTA);
			r.add("ALAMAT3_HARTA", ALAMAT3_HARTA);
			r.add("JENIS_HARTA_DINILAI", JENIS_HARTA_DINILAI);					
			r.add("CATATAN", CATATAN);
			r.add("NAMA_PEMILIK", NAMA_PEMILIK);
			r.add("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
			r.add("NAMA_PEMAJU", NAMA_PEMAJU);					
			r.add("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
			r.add("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
			r.add("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
			r.add("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
			r.add("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
		} else {
			r.add("ID_KEMASKINI_JPPH", USER_ID);
			r.add("TARIKH_KEMASKINI_JPPH", r.unquote("SYSDATE"));
			r.add("NO_RSS", NO_RSS);
			r.add("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
			r.add("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
			r.add("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
			r.add("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
			r.add("JPPH_CATATAN", JPPH_CATATAN);
			r.add("JPPH_TARIKH_LAWAT_PERIKSA", r.unquote("TO_DATE('" + JPPH_TARIKH_LAWAT_PERIKSA + "', 'dd/MM/yyyy')"));
		}
//		if (haveData) {
//			sql = r.getSQLUpdate(CHILD_TABLE);
//		} else {
			sql = r.getSQLInsert(CHILD_TABLE);
//		}
		stmt.execute(sql);
	}

//IL end
	@SuppressWarnings("rawtypes")
	public Boolean saveNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA, String USER_ID, Boolean isJPPHUser, Hashtable h) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				String CHILD_TABLE = "TBLINTJPPHAH";
				String CHILD_TABLE_ID = "ID_JPPHAH";
				if ("1".equalsIgnoreCase(JENIS_HTA)) {
					CHILD_TABLE = "TBLINTJPPHTH";
					CHILD_TABLE_ID = "ID_JPPHTH";
				}
				
				// save
				long ID_KEY = 0;
				String STATUS_PROSES = "DALAM PROSES", UNIT = "", PERLU_NILAIAN_TARIKH_MATI = "", ID_NEGERI = "", ID_DAERAH = "", ID_MUKIM = "";
				String SEKSYEN = "", JENIS_HAKMILIK = "", NO_HAKMILIK = "", NO_PTLOT = "", JENIS_PTLOT = "", NO_BANGUNAN = "", NO_TINGKAT = "", NO_PETAK = "", NO_RSS = "";
				String BA_SIMATI = "", BB_SIMATI = "", JENIS_PEGANGAN = "", TARIKH_LUPUT_PAJAKAN = "", TEMPOH_PAJAKAN = "", KATEGORI_TANAH = "", SYARAT_NYATA = "", SEKATAN_KEPENTINGAN = "";
				String LUAS_TANAH = "", JENIS_LUAS_TANAH = "", LUAS_PETAK = "", ALAMAT1_HARTA = "", ALAMAT2_HARTA = "", ALAMAT3_HARTA = "";
				String JENIS_HARTA_DINILAI = "", CATATAN = "", NAMA_PEGAWAI_JKPTG = "", NO_TEL_PEGAWAI_JKPTG = "", CAWANGAN_PEGAWAI_JKPTG = "";
				String EMAIL_ADDR_PEGAWAI_JPPH = "", EMAIL_SEND_JPPH = "", NAMA_PEMILIK = "", TARIKH_PERJANJIAN = "", NAMA_PEMAJU = "";
				String JPPH_NILAI_TARIKH_MOHON = "", JPPH_NILAI_TARIKH_MATI = "", JPPH_NAMA_PEGAWAI = "", JPPH_CAWANGAN_JPPH = "", JPPH_CATATAN = "";
				String JPPH_TARIKH_LAWAT_PERIKSA = "";

				UNIT = (String) h.get("UNIT").toString().toUpperCase();
				PERLU_NILAIAN_TARIKH_MATI = (String) h.get("PERLU_NILAIAN_TARIKH_MATI").toString().toUpperCase();
				ID_NEGERI = (String) h.get("ID_NEGERI").toString().toUpperCase();
				ID_DAERAH = (String) h.get("ID_DAERAH").toString().toUpperCase();
				ID_MUKIM = (String) h.get("ID_MUKIM").toString().toUpperCase();
				SEKSYEN = (String) h.get("SEKSYEN").toString().toUpperCase();
				JENIS_HAKMILIK = (String) h.get("JENIS_HAKMILIK").toString().toUpperCase();
				NO_HAKMILIK = (String) h.get("NO_HAKMILIK").toString().toUpperCase();
				NO_PTLOT = (String) h.get("NO_PTLOT").toString().toUpperCase();
				JENIS_PTLOT = (String) h.get("JENIS_PTLOT").toString().toUpperCase();
				NO_BANGUNAN = (String) h.get("NO_BANGUNAN").toString().toUpperCase();
				NO_TINGKAT = (String) h.get("NO_TINGKAT").toString().toUpperCase();
				NO_PETAK = (String) h.get("NO_PETAK").toString().toUpperCase();
				NO_RSS = (String) h.get("NO_RSS").toString().toUpperCase();
				BA_SIMATI = (String) h.get("BA_SIMATI").toString().toUpperCase();
				BB_SIMATI = (String) h.get("BB_SIMATI").toString().toUpperCase();
				JENIS_PEGANGAN = (String) h.get("JENIS_PEGANGAN").toString().toUpperCase();
				TARIKH_LUPUT_PAJAKAN = (String) h.get("TARIKH_LUPUT_PAJAKAN").toString().toUpperCase();
				TEMPOH_PAJAKAN = (String) h.get("TEMPOH_PAJAKAN").toString().toUpperCase();
				KATEGORI_TANAH = (String) h.get("KATEGORI_TANAH").toString().toUpperCase();
				SYARAT_NYATA = (String) h.get("SYARAT_NYATA").toString().toUpperCase();
				SEKATAN_KEPENTINGAN = (String) h.get("SEKATAN_KEPENTINGAN").toString().toUpperCase();
				LUAS_TANAH = (String) h.get("LUAS_TANAH").toString().toUpperCase();
				JENIS_LUAS_TANAH = (String) h.get("JENIS_LUAS_TANAH").toString().toUpperCase();
				LUAS_PETAK = (String) h.get("LUAS_PETAK").toString().toUpperCase();
				ALAMAT1_HARTA = (String) h.get("ALAMAT1_HARTA").toString().toUpperCase();
				ALAMAT2_HARTA = (String) h.get("ALAMAT2_HARTA").toString().toUpperCase();
				ALAMAT3_HARTA = (String) h.get("ALAMAT3_HARTA").toString().toUpperCase();
				JENIS_HARTA_DINILAI = (String) h.get("JENIS_HARTA_DINILAI").toString().toUpperCase();
				CATATAN = (String) h.get("CATATAN").toString().toUpperCase();
				NAMA_PEGAWAI_JKPTG = (String) h.get("NAMA_PEGAWAI_JKPTG").toString().toUpperCase();
				NO_TEL_PEGAWAI_JKPTG = (String) h.get("NO_TEL_PEGAWAI_JKPTG").toString().toUpperCase();
				CAWANGAN_PEGAWAI_JKPTG = (String) h.get("CAWANGAN_PEGAWAI_JKPTG").toString().toUpperCase();
				JPPH_NILAI_TARIKH_MOHON = (String) h.get("JPPH_NILAI_TARIKH_MOHON").toString().toUpperCase();
				JPPH_NILAI_TARIKH_MATI = (String) h.get("JPPH_NILAI_TARIKH_MATI").toString().toUpperCase();
				JPPH_NAMA_PEGAWAI = (String) h.get("JPPH_NAMA_PEGAWAI").toString().toUpperCase();
				JPPH_CAWANGAN_JPPH = (String) h.get("JPPH_CAWANGAN_JPPH").toString().toUpperCase();
				JPPH_CATATAN = (String) h.get("JPPH_CATATAN").toString().toUpperCase();
				JPPH_TARIKH_LAWAT_PERIKSA = (String) h.get("JPPH_TARIKH_LAWAT_PERIKSA").toString().toUpperCase();
				
				// check in TBLINTJPPHTH
				r.clear();
				r.add(CHILD_TABLE_ID);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				sql = r.getSQLSelect(CHILD_TABLE);
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_KEY = rs.getInt(1);
					haveData = true;
				} else {
					haveData = false;
				}
				
				if (isJPPHUser) {
					STATUS_PROSES = "DALAM PROSES JPPH";
				} else {
					STATUS_PROSES = "BARU";
				}
				
				r.clear();
				if (haveData) {
					r.update(CHILD_TABLE_ID, ID_KEY);
				} else {
					ID_KEY = DB.getNextID(CHILD_TABLE + "_SEQ");
					r.add(CHILD_TABLE_ID, ID_KEY);
					r.add("ID_SIMPAN", USER_ID);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
				}
				r.add("STATUS_PROSES", STATUS_PROSES);
				if (!isJPPHUser) {
					r.add("ID_PERMOHONAN", ID_PERMOHONAN);
					r.add("ID_HTA", ID_HTA);
					r.add("ID_KEMASKINI", USER_ID);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("UNIT", UNIT);
					r.add("PERLU_NILAIAN_TARIKH_MATI", PERLU_NILAIAN_TARIKH_MATI);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_DAERAH", ID_DAERAH);
					r.add("ID_MUKIM", ID_MUKIM);
					r.add("SEKSYEN", SEKSYEN);
					r.add("JENIS_HAKMILIK", JENIS_HAKMILIK);
					r.add("NO_HAKMILIK", NO_HAKMILIK);
					r.add("NO_PTLOT", NO_PTLOT);
					r.add("JENIS_PTLOT", JENIS_PTLOT);
					r.add("NO_BANGUNAN", NO_BANGUNAN);
					r.add("NO_TINGKAT", NO_TINGKAT);
					r.add("NO_PETAK", NO_PETAK);
					r.add("NO_RSS", NO_RSS);
					r.add("BA_SIMATI", BA_SIMATI);
					r.add("BB_SIMATI", BB_SIMATI);
					r.add("JENIS_PEGANGAN", JENIS_PEGANGAN);
					r.add("TARIKH_LUPUT_PAJAKAN", r.unquote("TO_DATE('" + TARIKH_LUPUT_PAJAKAN + "', 'dd/MM/yyyy')"));
					r.add("TEMPOH_PAJAKAN", TEMPOH_PAJAKAN);
					r.add("KATEGORI_TANAH", KATEGORI_TANAH);
					r.add("SYARAT_NYATA", SYARAT_NYATA);
					r.add("SEKATAN_KEPENTINGAN", SEKATAN_KEPENTINGAN);
					r.add("LUAS_TANAH", LUAS_TANAH);
					r.add("JENIS_LUAS_TANAH", JENIS_LUAS_TANAH);
					r.add("LUAS_PETAK", LUAS_PETAK);
					r.add("ALAMAT1_HARTA", ALAMAT1_HARTA);
					r.add("ALAMAT2_HARTA", ALAMAT2_HARTA);
					r.add("ALAMAT3_HARTA", ALAMAT3_HARTA);
					r.add("JENIS_HARTA_DINILAI", JENIS_HARTA_DINILAI);					
					r.add("CATATAN", CATATAN);
					r.add("NAMA_PEMILIK", NAMA_PEMILIK);
					r.add("TARIKH_PERJANJIAN", TARIKH_PERJANJIAN);
					r.add("NAMA_PEMAJU", NAMA_PEMAJU);					
					r.add("NAMA_PEGAWAI_JKPTG", NAMA_PEGAWAI_JKPTG);
					r.add("NO_TEL_PEGAWAI_JKPTG", NO_TEL_PEGAWAI_JKPTG);
					r.add("CAWANGAN_PEGAWAI_JKPTG", CAWANGAN_PEGAWAI_JKPTG);
					r.add("EMAIL_ADDR_PEGAWAI_JPPH", EMAIL_ADDR_PEGAWAI_JPPH);
					r.add("EMAIL_SEND_JPPH", EMAIL_SEND_JPPH);
				} else {
					r.add("ID_KEMASKINI_JPPH", USER_ID);
					r.add("TARIKH_KEMASKINI_JPPH", r.unquote("SYSDATE"));
					r.add("NO_RSS", NO_RSS);
					r.add("JPPH_NILAI_TARIKH_MOHON", JPPH_NILAI_TARIKH_MOHON);
					r.add("JPPH_NILAI_TARIKH_MATI", JPPH_NILAI_TARIKH_MATI);
					r.add("JPPH_NAMA_PEGAWAI", JPPH_NAMA_PEGAWAI);
					r.add("JPPH_CAWANGAN_JPPH", JPPH_CAWANGAN_JPPH);
					r.add("JPPH_CATATAN", JPPH_CATATAN);
					r.add("JPPH_TARIKH_LAWAT_PERIKSA", r.unquote("TO_DATE('" + JPPH_TARIKH_LAWAT_PERIKSA + "', 'dd/MM/yyyy')"));
				}
				if (haveData) {
					sql = r.getSQLUpdate(CHILD_TABLE);
				} else {
					sql = r.getSQLInsert(CHILD_TABLE);
				}
				stmt.execute(sql);
				r.clear();

				// ****************************
				// sending e-mail codes
				String NO_FAIL = "";
				sql = "SELECT FA.NO_FAIL " +
					"FROM " + CHILD_TABLE + " M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA " +
					"WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN + " AND M.ID_HTA = " + ID_HTA;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					haveData = true;
				}
				String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
				if (!isJPPHUser) {
					if ("1".equalsIgnoreCase(EMAIL_ADDR_PEGAWAI_JPPH) && !"".equalsIgnoreCase(EMAIL_SEND_JPPH)) {
						sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
							EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
							EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
							EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
							EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
							EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
						}

						if (!"".equalsIgnoreCase(EMAIL_FROM) && !"".equalsIgnoreCase(EMAIL_SUBJECT) && !"".equalsIgnoreCase(EMAIL_BODY)) {
							EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
							if (isJPPHUser) {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
							}
							Date date = new Date();
							if ("0".equalsIgnoreCase(JENIS_HTA)) {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Ada Hakmilik)");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Tiada Hakmilik)");
							}
							EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
							EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS_PROSES);
							EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
							XEkptgEmailSender email = XEkptgEmailSender.getInstance();
							email.FROM = EMAIL_FROM;
							email.MULTIPLE_RECIEPIENT = new String[1];
							email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR_PEGAWAI_JPPH;
							if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
								if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
									email.TO_CC = new String[2];
									email.TO_CC[0] = EMAIL_CC_1;
									email.TO_CC[1] = EMAIL_CC_2;
								} else {
									email.TO_CC = new String[1];
									if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
										email.TO_CC[0] = EMAIL_CC_1;
									} else {
										email.TO_CC[0] = EMAIL_CC_2;
									}
								}
							}
							email.RECIEPIENT = EMAIL_ADDR_PEGAWAI_JPPH;
							email.SUBJECT = EMAIL_SUBJECT;
							email.MESSAGE = EMAIL_BODY;
							email.sendEmail();				
						}
					} else {
						EMAIL_SEND_JPPH = "0";
					}
				}
				// ****************************

				returnValue = true;
			}			
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(JENIS_HTA) && !"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					sql = "DELETE FROM TBLINTJPPHAH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				} else {
					sql = "DELETE FROM TBLINTJPPHTH WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				}
				stmt.execute(sql);
				returnValue = true;
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}

	public String SelectJenisPembangunan(String SOC_NAME) throws Exception {
		return SelectJenisPembangunan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPembangunan(String SOC_NAME, long SELECTED_JENISPEMBANGUNAN) throws Exception {
		return SelectJenisPembangunan(SOC_NAME, SELECTED_JENISPEMBANGUNAN, "", "");
	}
	public String SelectJenisPembangunan(String SOC_NAME, long SELECTED_JENISPEMBANGUNAN, String DISABLED) throws Exception {
		return SelectJenisPembangunan(SOC_NAME, SELECTED_JENISPEMBANGUNAN, DISABLED, "");
	}
	public String SelectJenisPembangunan(String SOC_NAME, long SELECTED_JENISPEMBANGUNAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_JENISPEMBANGUNAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - BERTANAH</option>\r\n";
				returnValue += "  <option value=\"2\">02 - STRATA</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - BERTANAH</option>\r\n";
				returnValue += "  <option value=\"2\" selected=\"selected\">02 - STRATA</option>\r\n";
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {

		}
		return returnValue;
	}	
	
	public String SelectJenisPTLot(String SOC_NAME) throws Exception {
		return SelectJenisPTLot(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, DISABLED, "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PTLOT == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - PT</option>\r\n";
				returnValue += "  <option value=\"2\">02 - LOT</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - PT</option>\r\n";
				if (SELECTED_PTLOT == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - LOT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - LOT</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {

		}
		return returnValue;
	}	

	public String SelectJenisNoBangunan(String SOC_NAME) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, DISABLED, "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_NOBANGUNAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - BANGUNAN</option>\r\n";
				returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
				returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - BANGUNAN</option>\r\n";
				if (SELECTED_NOBANGUNAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - TINGKAT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
					if (SELECTED_NOBANGUNAN == 3) {
						returnValue += "  <option value=\"3\" selected=\"selected\">03 - STRATA</option>\r\n";
					} else {
						returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {

		}
		return returnValue;
	}	

	public String SelectJenisPegangan(String SOC_NAME) throws Exception {
		return SelectJenisPegangan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, DISABLED, "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PEGANGAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - KEKAL</option>\r\n";
				returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - KEKAL</option>\r\n";
				if (SELECTED_PEGANGAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - PAJAKAN</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {

		}
		return returnValue;
	}	

	public String SelectKategoriTanah(String SOC_NAME) throws Exception {
		return SelectKategoriTanah(SOC_NAME, 0, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, DISABLED, "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + ">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_KATEGORI, KOD_KATEGORI, KETERANGAN FROM TBLRUJKATEGORI " +
				SQL_SEARCH + "ORDER BY KOD_KATEGORI, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_KATEGORI == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	public String SelectJenisLuasTanah(String SOC_NAME) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, 0, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, DISABLED, "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_LUAS, KOD_LUAS, KETERANGAN FROM TBLRUJLUAS " +
				SQL_SEARCH + "ORDER BY KOD_LUAS, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_LUASTANAH == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	

	public String SelectJenisHartaDinilai(String SOC_NAME) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, 0, "", "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, SELECTED_HARTADINILAI, "", "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI, String DISABLED) throws Exception {
		return SelectJenisHartaDinilai(SOC_NAME, SELECTED_HARTADINILAI, DISABLED, "");
	}
	public String SelectJenisHartaDinilai(String SOC_NAME, long SELECTED_HARTADINILAI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_HARTADINILAI == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - TANAH SAHAJA</option>\r\n";
				returnValue += "  <option value=\"2\">02 - BANGUNAN SAHAJA</option>\r\n";
				returnValue += "  <option value=\"3\">03 - TANAH DAN BANGUNAN (BERSAMA)</option>\r\n";
				returnValue += "  <option value=\"4\">04 - TANAH DAN BANGUNAN (BERASINGAN)</option>\r\n";
			} else if (SELECTED_HARTADINILAI == 2) {
				returnValue += "  <option value=\"1\">01 - TANAH SAHAJA</option>\r\n";
				returnValue += "  <option value=\"2\" selected=\"selected\">02 - BANGUNAN SAHAJA</option>\r\n";
				returnValue += "  <option value=\"3\">03 - TANAH DAN BANGUNAN (BERSAMA)</option>\r\n";
				returnValue += "  <option value=\"4\">04 - TANAH DAN BANGUNAN (BERASINGAN)</option>\r\n";
			} else if (SELECTED_HARTADINILAI == 3) {
				returnValue += "  <option value=\"1\">01 - TANAH SAHAJA</option>\r\n";
				returnValue += "  <option value=\"2\">02 - BANGUNAN SAHAJA</option>\r\n";
				returnValue += "  <option value=\"3\" selected=\"selected\">03 - TANAH DAN BANGUNAN (BERSAMA)</option>\r\n";
				returnValue += "  <option value=\"4\">04 - TANAH DAN BANGUNAN (BERASINGAN)</option>\r\n";
			} else if (SELECTED_HARTADINILAI == 4) {
				returnValue += "  <option value=\"1\">01 - TANAH SAHAJA</option>\r\n";
				returnValue += "  <option value=\"2\">02 - BANGUNAN SAHAJA</option>\r\n";
				returnValue += "  <option value=\"3\">03 - TANAH DAN BANGUNAN (BERSAMA)</option>\r\n";
				returnValue += "  <option value=\"4\" selected=\"selected\">04 - TANAH DAN BANGUNAN (BERASINGAN)</option>\r\n";
			}
			returnValue += "</select>\r\n";
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {

		}
		return returnValue;
	}	
	
	public Boolean returnNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				if (JENIS_HTA.trim().equalsIgnoreCase("1")) {
					sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = 'DIKEMBALIKAN', TARIKH_KEMASKINI = SYSDATE WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				} else {
					sql = "UPDATE TBLINTJPPHAH SET STATUS_PROSES = 'DIKEMBALIKAN', TARIKH_KEMASKINI = SYSDATE WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HTA = " + ID_HTA;
				}
				stmt.execute(sql);
				returnValue = true;
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}

	public Boolean changeStatusPermohonanForJPPH(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				if (JENIS_HTA.trim().equalsIgnoreCase("1")) {
					sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = 'DALAM PROSES JPPH', TARIKH_KEMASKINI = SYSDATE WHERE STATUS_PROSES = 'BARU' AND ID_HTA = " + ID_HTA;
				} else {
					sql = "UPDATE TBLINTJPPHAH SET STATUS_PROSES = 'DALAM PROSES JPPH', TARIKH_KEMASKINI = SYSDATE WHERE STATUS_PROSES = 'BARU' AND ID_HTA = " + ID_HTA;
				}
				
				stmt.execute(sql);
				returnValue = true;
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public String SelectPenggunaJPPHEmail(String SOC_NAME, long SELECTED_PENGGUNA, String ID_JENISPEJABAT, String ID_NEGERI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String sql = "";
			
			returnValue += "<select size='auto' id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			
			String USER_ID = "", USER_NAME = "";
			if ("".equalsIgnoreCase(ID_NEGERI.trim())) {
				sql = "SELECT USER_ID, USER_NAME " +
					"FROM USERS U, USERS_INTEGRASI I" +
					"WHERE U.USER_ID = I.USER_ID " +
					"AND I.ID_JENISPEJABAT = " + ID_JENISPEJABAT;
			} else {
				sql = "SELECT USER_ID, USER_NAME " +
				"FROM USERS U, USERS_INTEGRASI I" +
				"WHERE U.USER_ID = I.USER_ID " +
				"AND I.ID_JENISPEJABAT = " + ID_JENISPEJABAT + " AND I.ID_NEGERI = " + ID_NEGERI;
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				USER_ID = rs.getString(1) == null ? "" : rs.getString(1);
				USER_NAME = rs.getString(2) == null ? "" : rs.getString(2);
				
				if (!"".equalsIgnoreCase(USER_ID.trim())) {
					returnValue += "  <option value=\"" + USER_ID + "\">" + USER_NAME + "</option>\r\n";
				}
			}

			returnValue += "</select>\r\n";
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	public static String getNamaPejabatJPPH(String USER_NAME) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(USER_NAME)) {
				String sql = "";
				sql = "SELECT PJ.NAMA_PEJABAT " +
					"FROM TBLRUJPEJABATJKPTG PJ, USERS_INTERNAL UI, USERS U " +
					"WHERE PJ.ID_PEJABATJKPTG = UI.ID_PEJABATJKPTG AND UI.USER_ID = U.USER_ID " +
					"AND UPPER(U.USER_LOGIN) = '" + USER_NAME.toUpperCase() + "'";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 }finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
		
	}

	//Il start
	public void updateSelectedHartaTakAlih(String selectedHartaTakAlih, String idSimati) throws Exception {
		Db db = new Db();
		Connection conn = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "UPDATE TBLPPKHTA SET FLAG_NILAIAN = NULL WHERE ID_SIMATI = "+idSimati+" AND ID_HTA NOT IN ("+selectedHartaTakAlih+")";
			Statement stmt = db.getStatement();
			stmt.execute(sql);
			
			for(int i=0; i<selectedHartaTakAlih.length() ; i++){
				String carryHartaTakAlih = "";
				if(selectedHartaTakAlih.indexOf(",")>0){ 
					carryHartaTakAlih = selectedHartaTakAlih.substring(0, selectedHartaTakAlih.indexOf(",")); 		
					selectedHartaTakAlih = selectedHartaTakAlih.substring(selectedHartaTakAlih.indexOf(",")+1, selectedHartaTakAlih.length());
				} else {
					carryHartaTakAlih = selectedHartaTakAlih;
					selectedHartaTakAlih = "";
				}
				
				sql = " UPDATE TBLPPKHTA SET FLAG_NILAIAN = 'Y' WHERE ID_HTA ="+carryHartaTakAlih;
				stmt = db.getStatement();
				stmt.execute(sql);
			}
			
			conn.commit();
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();			
		}
	}

	public Vector getListHTA() {
		return listHTA;
	}

	public void setListHTA(Vector listHTA) {
		this.listHTA = listHTA;
	}
	
	public Vector getListHTAX() {
		return listHTAX;
	}

	public void setListHTAX(Vector listHTAX) {
		this.listHTAX = listHTAX;
	}

	public Vector getDataHTA() {
		return listHTA;
	}
	
	public Vector getDataHTAX() {
		return listHTAX;
	}

	public Vector setListDataHTA(String idSimati) throws Exception {
		Vector returnValue = new Vector();
		Db db = new Db();
		Connection conn = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			
			String sql = " SELECT SM.ID_PERMOHONAN, H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, " +
					"H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.SEKATAN, H.SYARAT_NYATA, SM.ID_PERMOHONANSIMATI " +
					"FROM TBLPPKHTA HP, TBLPPKHTAPERMOHONAN H, TBLPPKPERMOHONANSIMATI SM " +
					"WHERE HP.ID_HTA = H.ID_HTA AND HP.FLAG_NILAIAN = 'Y' AND H.JENIS_HTA = 'Y'  and SM.ID_SIMATI = HP.ID_SIMATI " +
					"AND HP.ID_SIMATI = "+idSimati;
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
//				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs
//						.getString("id_dokumen"));
//				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs
//						.getString("id_pelan"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));

//				h.put("kod_hakmilik",
//						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
//								.getString("KOD_JENIS_HAKMILIK"));
//				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
//						: rs.getString("KETERANGAN"));

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? ""
						: rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idPermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? ""
						: rs.getString("ID_PERMOHONANSIMATI").toUpperCase());

				// System.out.println(h);
				returnValue.addElement(h);
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}

	public Vector setListDataHTAX(String idSimati) throws Exception {
		Vector returnValue = new Vector();
		Db db = new Db();
		Connection conn = null;
		
		try {
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			
			String sql = " SELECT SM.ID_PERMOHONAN, H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, "
									+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, "
									+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
									+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,"
									+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA, SM.ID_PERMOHONANSIMATI " +
					"FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKPERMOHONANSIMATI SM  " +
					"WHERE HP.ID_HTA = H.ID_HTA AND HP.FLAG_NILAIAN = 'Y' AND H.JENIS_HTA = 'T'  and SM.ID_SIMATI = HP.ID_SIMATI " +
					"AND HP.ID_SIMATI = "+idSimati;
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
//				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs
//						.getString("id_dokumen"));
//				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs
//						.getString("id_pelan"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs
						.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs
						.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs
						.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs
						.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? ""
						: rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idPermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? ""
						: rs.getString("ID_PERMOHONANSIMATI").toUpperCase());

				// System.out.println(h);
				returnValue.addElement(h);
			}
		}catch (Exception re) {
			myLog.error("Error: ", re);
			 throw re;
			 } finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	

//IL end
}
