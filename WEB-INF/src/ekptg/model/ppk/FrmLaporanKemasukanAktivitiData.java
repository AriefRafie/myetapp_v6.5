package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.DB;

public class FrmLaporanKemasukanAktivitiData {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(ekptg.model.ppk.FrmLaporanKemasukanAktivitiData.class);

	private Vector senaraiAktiviti = null;
	private Vector beanMaklumatAktiviti = null;

	public void carianAktiviti(String namaAktiviti, String tarikhMulaDari, String tarikhMulaHingga, String idBulan, String idTahun, HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = null;		
		String sql = "";

		try {
			senaraiAktiviti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_AKTIVITI, NAMA_AKTIVITI, LOKASI_AKTIVITI, TARIKH_MULA, TARIKH_TAMAT "
					+ " FROM TBLAKTIVITIPEGAWAI "
					+ " WHERE USER_ID = '" + userId + "'";

			// namaAktiviti
			if (namaAktiviti != null) {
				if (!namaAktiviti.trim().equals("")) {
					sql = sql + " AND UPPER(NAMA_AKTIVITI) LIKE '%' ||'" + namaAktiviti.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			if (tarikhMulaDari != null) {
				if (!tarikhMulaDari.trim().equals("")) {
					sql = sql + " AND TARIKH_MULA >= TO_DATE('" + tarikhMulaDari + "','DD/MM/YYYY')";
				}				
			}
			
			if (tarikhMulaHingga != null ) {
				if (!tarikhMulaHingga.trim().equals("")) {
					sql = sql + " AND TARIKH_MULA - 1 < TO_DATE('" + tarikhMulaHingga + "','DD/MM/YYYY')";
				}				
			}
			
			if (idBulan != null ) {
				if (!idBulan.trim().equals("") && !idBulan.trim().equals("99999")) {
					sql = sql + " AND TO_NUMBER(TO_CHAR(TARIKH_MULA, 'mm')) = '" + idBulan + "'";
				}				
			}
			
			if (idTahun != null ) {
				if (!idTahun.trim().equals("") && !idTahun.trim().equals("99999")) {
					sql = sql + " AND TO_NUMBER(TO_CHAR(TARIKH_MULA, 'yyyy')) = '" + idTahun + "'";
				}				
			}

			sql = sql + " ORDER BY TARIKH_MULA ASC NULLS LAST ";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idAktiviti", rs.getString("ID_AKTIVITI") == null ? "" : rs.getString("ID_AKTIVITI"));	
				h.put("namaAktiviti", rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());	
				h.put("lokasiAktiviti", rs.getString("LOKASI_AKTIVITI") == null ? "" : rs.getString("LOKASI_AKTIVITI").toUpperCase());	
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT")));
				
				senaraiAktiviti.addElement(h);
				bil++;
			}

		}catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String daftarBaru(String txtNamaAktiviti, String txtLokasiAktiviti, String txtKeteranganAktiviti, String txtTarikhMula, String txtTarikhTamat, String txtCatatan, String txtMasaMula, String txtMasaTamat, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String userLogin = (String)session.getAttribute("_portal_login");
		String sql = "";
		String idAktivitiString = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			
			String TM = "to_date('" + txtTarikhMula + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamat + "','dd/MM/yyyy')";

			//TBLAKTIVITIPEGAWAI
			long idAktiviti = DB.getNextID("TBLAKTIVITIPEGAWAI_SEQ");
			idAktivitiString = String.valueOf(idAktiviti);
			r.add("ID_AKTIVITI", idAktiviti);
			r.add("NAMA_AKTIVITI", txtNamaAktiviti);
			r.add("LOKASI_AKTIVITI", txtLokasiAktiviti);
			r.add("KETERANGAN_AKTIVITI", txtKeteranganAktiviti);
			r.add("CATATAN", txtCatatan);
			r.add("TARIKH_MULA", r.unquote(TM));
			r.add("TARIKH_TAMAT", r.unquote(TT));
			r.add("MASA_MULA", txtMasaMula);
			r.add("MASA_TAMAT", txtMasaTamat);
			r.add("USER_ID", userId);

			sql = r.getSQLInsert("TBLAKTIVITIPEGAWAI");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			saveActivityEvent(userLogin, idAktiviti, txtNamaAktiviti, txtLokasiAktiviti, txtTarikhMula, txtMasaMula, txtTarikhTamat, txtMasaTamat, txtKeteranganAktiviti);
			
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
		return idAktivitiString;
	}
	
	public void simpanKemaskini(String idAktiviti, String txtNamaAktiviti, String txtLokasiAktiviti, String txtKeteranganAktiviti, String txtTarikhMula, String txtTarikhTamat, String txtCatatan, String txtMasaMula, String txtMasaTamat, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String userLogin = (String)session.getAttribute("_portal_login");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			
			String TM = "to_date('" + txtTarikhMula + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamat + "','dd/MM/yyyy')";

			//TBLAKTIVITIPEGAWAI
			r.update("ID_AKTIVITI", idAktiviti);
			r.add("NAMA_AKTIVITI", txtNamaAktiviti);
			r.add("LOKASI_AKTIVITI", txtLokasiAktiviti);
			r.add("KETERANGAN_AKTIVITI", txtKeteranganAktiviti);
			r.add("CATATAN", txtCatatan);
			r.add("TARIKH_MULA", r.unquote(TM));
			r.add("TARIKH_TAMAT", r.unquote(TT));
			r.add("MASA_MULA", txtMasaMula);
			r.add("MASA_TAMAT", txtMasaTamat);

			sql = r.getSQLUpdate("TBLAKTIVITIPEGAWAI");
			stmt.executeUpdate(sql);
			
			deleteUserActivityEvent(Long.valueOf(idAktiviti), db);
			
			conn.commit();
			
			saveActivityEvent(userLogin, Long.parseLong(idAktiviti), txtNamaAktiviti, txtLokasiAktiviti, txtTarikhMula, txtMasaMula, txtTarikhTamat, txtMasaTamat, txtKeteranganAktiviti);
			
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
	}
	
	public void setMaklumatAktiviti(String idAKtiviti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAktiviti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT *"
				+ " FROM TBLAKTIVITIPEGAWAI WHERE ID_AKTIVITI = '" + idAKtiviti + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAktiviti", rs.getString("ID_AKTIVITI") == null ? "" : rs.getString("ID_AKTIVITI"));	
				h.put("namaAktiviti", rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());	
				h.put("lokasiAktiviti", rs.getString("LOKASI_AKTIVITI") == null ? "" : rs.getString("LOKASI_AKTIVITI").toUpperCase());	
				h.put("keteranganAktiviti", rs.getString("KETERANGAN_AKTIVITI") == null ? "" : rs.getString("KETERANGAN_AKTIVITI").toUpperCase());
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("masaMula", rs.getString("MASA_MULA") == null ? "" : rs.getString("MASA_MULA"));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("masaTamat", rs.getString("MASA_TAMAT") == null ? "" : rs.getString("MASA_TAMAT"));
				h.put("flagFullDay", rs.getString("FLAG_FULL_DAY") == null ? "" : rs.getString("FLAG_FULL_DAY"));
				beanMaklumatAktiviti.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	/** ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE 
	 * @param description 
	 * @param locationRemark 
	 * @param masaTamat 
	 * @param tarikhTamat 
	 * @param masaMula 
	 * @param keteranganAktiviti 
	 * @param eventDateStart_ 
	 * @param masa_bicara 
	 * @param jenisWaktu 
	 * @param idUnitPSK 
	 * @param db 
	 * @throws Exception **/

	private static void saveActivityEvent(String userLoginPegawai, Long idAktiviti, String description, String locationRemark, String tarikhMula, String masaMula, String tarikhTamat, String masaTamat, String keteranganAktiviti) throws Exception {
		
		String jamMula  = "";
		String minitMula = "";
		String jamTamat  = "";
		String minitTamat = "";
		String startTime = "";
		String endTime = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DbPersistence db = new DbPersistence();	
		
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");
		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userLoginPegawai);
			db.persist(userActivityEvent);
			db.commit();
		}		
		
		Calendar calMula = new GregorianCalendar();
		Date dateMula = sdf.parse(sdf.format(sdf.parse(tarikhMula)));
		calMula.setTime(dateMula);
		
		Calendar calTamat = new GregorianCalendar();
		Date dateTamat = sdf.parse(sdf.format(sdf.parse(tarikhTamat)));
		calTamat.setTime(dateTamat);
		
		ActivityEvent activityEvent = null;
		db.begin();
		String displayColor = "#FFCCCC";
		
		while (calMula.getTime().before(calTamat.getTime())) {
			
			tarikhMula = sdf.format(calMula.getTime());
	
			if (!"".equals(masaMula)){
				
				jamMula = masaMula.substring(0, 2);
				minitMula = masaMula.substring(2, 4);
				
				if (Integer.valueOf(minitMula) < 15){
					minitMula = "00";
				} else if (Integer.valueOf(minitMula) >= 15 && Integer.valueOf(minitMula) < 30){
					minitMula = "15";
				} else if (Integer.valueOf(minitMula) >= 30 && Integer.valueOf(minitMula) < 45){
					minitMula = "30";
				} else if (Integer.valueOf(minitMula) >= 45 && Integer.valueOf(minitMula) < 60){
					minitMula = "45";
				}	
				
				if (Integer.valueOf(jamMula) < 12) {
					startTime = jamMula + ":" + minitMula + " AM";
				} else if (Integer.valueOf(jamMula) == 12) {
					startTime = jamMula + ":" + minitMula + " PM";
				} else {
					startTime = (Integer.valueOf(jamMula) - 12) + ":" + minitMula + " PM";
				}
			}
			
			Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
			Date endDateTime = parseDateTime(tarikhMula + " " + "12:00 AM");					
			
			activityEvent = new ActivityEvent();
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark(keteranganAktiviti);
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdAktivitiPegawai(idAktiviti);
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			
			calMula.add(Calendar.DATE, 1);
			masaMula = "0600";
		}

		if (calTamat.getTime().compareTo(calMula.getTime()) == 0){
			
			tarikhMula = sdf.format(calMula.getTime());
			
			if (!"".equals(masaMula)){
				
				jamMula = masaMula.substring(0, 2);
				minitMula = masaMula.substring(2, 4);
				
				if (Integer.valueOf(minitMula) < 15){
					minitMula = "00";
				} else if (Integer.valueOf(minitMula) >= 15 && Integer.valueOf(minitMula) < 30){
					minitMula = "15";
				} else if (Integer.valueOf(minitMula) >= 30 && Integer.valueOf(minitMula) < 45){
					minitMula = "30";
				} else if (Integer.valueOf(minitMula) >= 45 && Integer.valueOf(minitMula) < 60){
					minitMula = "45";
				}	
				
				if (Integer.valueOf(jamMula) < 12) {
					startTime = jamMula + ":" + minitMula + " AM";
				} else if (Integer.valueOf(jamMula) == 12) {
					startTime = jamMula + ":" + minitMula + " PM";
				} else {
					startTime = (Integer.valueOf(jamMula) - 12) + ":" + minitMula + " PM";
				}
			}
			
			if (!"".equals(masaTamat)){
				
				jamTamat = masaTamat.substring(0, 2);
				minitTamat = masaTamat.substring(2, 4);
				
				if (Integer.valueOf(minitTamat) < 15){
					minitTamat = "00";
				} else if (Integer.valueOf(minitTamat) >= 15 && Integer.valueOf(minitTamat) < 30){
					minitTamat = "15";
				} else if (Integer.valueOf(minitTamat) >= 30 && Integer.valueOf(minitTamat) < 45){
					minitTamat = "30";
				} else if (Integer.valueOf(minitTamat) >= 45 && Integer.valueOf(minitTamat) < 60){
					minitTamat = "45";
				}	
				
				if (Integer.valueOf(jamTamat) < 12) {
					endTime = jamTamat + ":" + minitTamat + " AM";
				} else if (Integer.valueOf(jamTamat) == 12) {
					endTime = jamTamat + ":" + minitTamat + " PM";
				} else {
					endTime = (Integer.valueOf(jamTamat) - 12) + ":" + minitTamat + " PM";
				}
			}
			
			Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
			Date endDateTime = parseDateTime(tarikhTamat + " " + endTime);	
			
			activityEvent = new ActivityEvent();
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark(keteranganAktiviti);
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdAktivitiPegawai(idAktiviti);
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			
		} 		
		
		try {
			db.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static Date parseDateTime(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy hh:mm a")
						.parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	public static Date parseDate(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	public void hapusAktiviti(String idAktiviti) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			deleteUserActivityEvent(Long.valueOf(idAktiviti), db);
			
			sql = "DELETE FROM TBLAKTIVITIPEGAWAI WHERE ID_AKTIVITI = '" + idAktiviti + "'";
			stmt.execute(sql);
			
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
	}
	
	private static void deleteUserActivityEvent(Long idAktiviti, Db db) throws Exception {
		
		String sql = "";

		try {
			
			Statement stmt = db.getStatement();
			
			sql = "DELETE FROM TBLACTIVITYEVENT WHERE ID_AKTIVITI_PEGAWAI = '" + idAktiviti + "'";
			stmt.execute(sql);
			
		} catch (SQLException ex) { 
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
		}	
	}

	public Vector getSenaraiAktiviti() {
		return senaraiAktiviti;
	}

	public void setSenaraiAktiviti(Vector senaraiAktiviti) {
		this.senaraiAktiviti = senaraiAktiviti;
	}

	public Vector getBeanMaklumatAktiviti() {
		return beanMaklumatAktiviti;
	}

	public void setBeanMaklumatAktiviti(Vector beanMaklumatAktiviti) {
		this.beanMaklumatAktiviti = beanMaklumatAktiviti;
	}

	
}