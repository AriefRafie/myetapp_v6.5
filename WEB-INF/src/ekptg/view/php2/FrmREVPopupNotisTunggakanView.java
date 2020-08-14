/**
 * 
 */
package ekptg.view.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.DB;

/**
 * @author Mohd Faizal
 *
 */
public class FrmREVPopupNotisTunggakanView extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		this.context.put("successSave", "T");
		
		//GET DEFAULT PARAM
	    String vm = "app/php2/frmREVPopupNotisTunggakan.jsp";
	    
	    String submit = getParam("command");   
	    
	    String idHasil = getParam("idHasil");
	    String report = getParam("report");
	    String bilPeringatan = getParam("bilPeringatan");
	    String tarikhNotis = getParam("tarikhNotis");
	    
	    if ("janaNotisTuntutanTunggakan".equals(submit)) {
	    	janaNotisTuntutanTunggakan(idHasil, bilPeringatan, tarikhNotis, session);
	    }
	    if ("janaNotisRampasanDeposit".equals(submit)) {
	    	janaNotisRampasanDeposit(idHasil, tarikhNotis, session);
	    }
	    
		this.context.put("idHasil", idHasil);
		this.context.put("report", report);
		
		return vm;
	}

	private void janaNotisTuntutanTunggakan(String idHasil,
			String bilPeringatan, String tarikhNotis, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		try {			
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			Double kadarSewaSebulan = getKadarSewaSebulan(idHasil, tarikhNotis, db);
			Double jumlahTunggakan = getJumlahTunggakan(idHasil, tarikhNotis, db);
			Double bulanTunggakan = Math.ceil(jumlahTunggakan / kadarSewaSebulan);

			// TBLPHPNOTISHASIL
			long idNotis = DB.getNextID("TBLPHPNOTISHASIL_SEQ");
			r.add("ID_NOTIS", idNotis);
			r.add("ID_HASIL", idHasil);
			if (!"".equals(tarikhNotis)) {
				r.add("TARIKH_NOTIS",
						r.unquote("to_date('" + tarikhNotis + "','dd/MM/yyyy')"));
				
				Calendar calMula = new GregorianCalendar();
				Date dateMula = sdf.parse(tarikhNotis);
				calMula.setTime(dateMula);
				calMula.add(Calendar.MONTH, 2);
				r.add("TARIKH_AKHIR_NOTIS",
						r.unquote("to_date('" + sdf.format(calMula.getTime()) + "','dd/MM/yyyy')"));
				
			}				
			r.add("KADAR_SEWA", kadarSewaSebulan);
			r.add("BULAN_TUNGGAKAN", bulanTunggakan);
			r.add("JUMLAH_TUNGGAKAN", jumlahTunggakan);
			r.add("BIL_PERINGATAN", bilPeringatan);
			r.add("ID_JENIS_NOTIS", "1"); //NOTIS TUNTUTAN TUNGGAKAN
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPNOTISHASIL");
			stmt.executeUpdate(sql);
			conn.commit();
			this.context.put("successSave", "Y");
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	private void janaNotisRampasanDeposit(String idHasil,
			String tarikhNotis, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		
		try {			
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			Double kadarSewaSebulan = getKadarSewaSebulan(idHasil, tarikhNotis, db);
			Double jumlahTunggakan = getJumlahTunggakan(idHasil, tarikhNotis, db);
			Double bulanTunggakan = Math.ceil(jumlahTunggakan / kadarSewaSebulan);

			// TBLPHPNOTISHASIL
			long idNotis = DB.getNextID("TBLPHPNOTISHASIL_SEQ");
			r.add("ID_NOTIS", idNotis);
			r.add("ID_HASIL", idHasil);
			if (!"".equals(tarikhNotis)) {
				r.add("TARIKH_NOTIS",
						r.unquote("to_date('" + tarikhNotis + "','dd/MM/yyyy')"));
				
				Calendar calMula = new GregorianCalendar();
				Date dateMula = sdf.parse(tarikhNotis);
				calMula.setTime(dateMula);
				calMula.add(Calendar.MONTH, 2);
				r.add("TARIKH_AKHIR_NOTIS",
						r.unquote("to_date('" + sdf.format(calMula.getTime()) + "','dd/MM/yyyy')"));
				
			}				
			r.add("KADAR_SEWA", kadarSewaSebulan);
			r.add("BULAN_TUNGGAKAN", bulanTunggakan);
			r.add("JUMLAH_TUNGGAKAN", jumlahTunggakan);
			r.add("ID_JENIS_NOTIS", "2"); // NOTIS RAMPASAN DEPOSIT
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPNOTISHASIL");
			stmt.executeUpdate(sql);
			conn.commit();
			this.context.put("successSave", "Y");
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	public double getKadarSewaSebulan(String idHasil, String tarikhNotis, Db db) {
		double kadarSewaSebulan = 0D;
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TO_DATE('" + tarikhNotis + "', 'DD/MM/YYYY') BETWEEN TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL = '" + idHasil + "'"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC" ;
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("BAYARAN") != null) {
					if (rs.getDouble("BAYARAN") >= 0D) {
						kadarSewaSebulan = rs.getDouble("BAYARAN");
					}
				}
			} else {
				sql = "SELECT TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA < TO_DATE('" + tarikhNotis + "', 'DD/MM/YYYY')"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL = '" + idHasil + "'"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC" ;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					if (rs.getString("BAYARAN") != null) {
						if (rs.getDouble("BAYARAN") >= 0D) {
							kadarSewaSebulan = rs.getDouble("BAYARAN");
						}
					}
				}				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return kadarSewaSebulan;
	}
	
	public double getJumlahTunggakan(String idHasil, String tarikhNotis, Db db) {
		double tunggakanSewa = 0D;
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			
			sql = "SELECT SUM(NVL(KREDIT,0)) - SUM(NVL(DEBIT,0)) AS JUMLAH_TUNGGAKAN"					
					+ " FROM TBLPHPAKAUN"					
					+ " WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y'"
					+ " AND TARIKH <= TO_DATE('" + tarikhNotis + "', 'DD/MM/YYYY')"
					+ " AND TBLPHPAKAUN.ID_HASIL = '" + idHasil + "'"
					+ " ORDER BY TARIKH, ID_AKAUN ASC";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tunggakanSewa = rs.getDouble("JUMLAH_TUNGGAKAN");
			}
			
			if (tunggakanSewa < 0D) {
				tunggakanSewa = tunggakanSewa * -1;
			} else {
				tunggakanSewa = 0D;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tunggakanSewa;
	}

}
