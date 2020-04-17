package ekptg.scheduler.PHP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class GenerateSewaBulananPenyewaanJob implements Job {
	
	static Logger myLogger = Logger.getLogger("GenerateSewaBulananPenyewaanJob");
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		myLogger.info("Executing GenerateSewaBulananPenyewaanJob on : " + new Date());
		System.out.println("Executing GenerateSewaBulananPenyewaanJob on : " + new Date());
		
		Db db = null;
		Connection conn = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			//GET PERJANJIAN UTAMA YANG AKTIF
			sql = "SELECT TBLPHPHASIL.ID_HASIL, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT,"
					+ " TBLPHPBAYARANPERLUDIBAYAR.BAYARAN, TBLPHPBAYARANPERLUDIBAYAR.MOD_CAJ_SEWAAN"
					+ " FROM TBLPHPHASIL, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT > SYSDATE"
					+ " AND TO_CHAR (SYSDATE, 'DD') = TO_CHAR (TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, 'DD')"
					+ " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA ASC";
			ResultSet rsPerjanjianUtama = stmt.executeQuery(sql);
			
			while (rsPerjanjianUtama.next()) {
				String idHasil = rsPerjanjianUtama.getString("ID_HASIL");
				String tarikhMula = sdf.format(rsPerjanjianUtama.getDate("TARIKH_MULA"));
				String kadarSewa = rsPerjanjianUtama.getString("BAYARAN");
				String modCajSewaan = rsPerjanjianUtama.getString("MOD_CAJ_SEWAAN");				
				
				//SEMAK SAMA ADA TERDAPAT PERJANJIAN TAMBAHAN YANG AKTIF
				sql = "SELECT ID_HASIL, TARIKH_MULA, TARIKH_TAMAT, BAYARAN, MOD_CAJ_SEWAAN"
						+ " FROM TBLPHPBAYARANPERLUDIBAYAR"
						+ " WHERE TARIKH_MULA IS NOT NULL AND TARIKH_TAMAT IS NOT NULL AND BAYARAN IS NOT NULL AND FLAG_PERJANJIAN != 'U'"
						+ " AND FLAG_AKTIF = 'Y' AND TARIKH_TAMAT > SYSDATE AND ID_HASIL = '" + idHasil + "' ORDER BY TARIKH_MULA ASC";
				ResultSet rsPerjanjianTambahan = stmt.executeQuery(sql);
				
				if (rsPerjanjianTambahan.next()) {
					kadarSewa = rsPerjanjianTambahan.getString("BAYARAN");
					generateAkaunCaj(idHasil, tarikhMula, kadarSewa, modCajSewaan, db);
					updateFlagTunggakan(idHasil, db);
				} else {
					generateAkaunCaj(idHasil, tarikhMula, kadarSewa, modCajSewaan, db);
					updateFlagTunggakan(idHasil, db);
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) { db.close(); }
		}
		
		myLogger.info("Finish GenerateSewaBulananPenyewaanJob on : " + new Date());
		System.out.println("Finish GenerateSewaBulananPenyewaanJob on : " + new Date());
	}

	private static void generateAkaunCaj(String idHasil, String tarikhMula, String kadarSewa, String modCajSewaan, Db db) {
		String sql = "";
		boolean boolCaj = false;
		try {	
			Statement stmt = db.getStatement();
			
			Calendar calCurrent = new GregorianCalendar();
			Date dateCurrent = new Date();
			calCurrent.setTime(dateCurrent);
			
			Calendar calMula = new GregorianCalendar();
			Date dateMula = sdf.parse(tarikhMula);
			calMula.setTime(dateMula);
			
			int diffYear = calCurrent.get(Calendar.YEAR) - calMula.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + calCurrent.get(Calendar.MONTH) - calMula.get(Calendar.MONTH);
			
			int modSewa = 1;
			int remainder = 1;					
			
			//MOD CAJ SEWAAN
			//1 = BULAN KE BULAN; 3 = 3 BULAN SEKALI; 6= 6 BULAN SEKALI; 12 = SETAHUN SEKALI; 0 = CAJ PENUH(LUMPSUMP)
			if ("1".equals(modCajSewaan)) {
				boolCaj = true;
			} else if ("3".equals(modCajSewaan)) {
				modSewa = 3;
				remainder = diffMonth%modSewa;
				if (remainder == 0) {
					boolCaj = true;
				}
			} else if ("6".equals(modCajSewaan)) {
				modSewa = 6;
				remainder = diffMonth%modSewa;
				if (remainder == 0) {
					boolCaj = true;
				}
			} else if ("12".equals(modCajSewaan)) {
				modSewa = 12;
				remainder = diffMonth%modSewa;
				if (remainder == 0) {
					boolCaj = true;
				}
			}
			
			if (boolCaj) {
				// TBLPHPAKAUN
				SQLRenderer r = new SQLRenderer();
				r.add("ID_HASIL", idHasil);
				r.add("TARIKH", r.unquote("to_date('" + sdf.format(calCurrent.getTime()) + "','dd/MM/yyyy')"));
				r.add("DEBIT", kadarSewa);
				r.add("ID_JENISBAYARAN", "10"); // BAYARAN SEWA

				if (calCurrent.get(Calendar.MONTH) == 0) {
					r.add("CATATAN", "SEWA BULAN JANUARI " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 1) {
					r.add("CATATAN", "SEWA BULAN FEBRUARI " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 2) {
					r.add("CATATAN", "SEWA BULAN MAC " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 3) {
					r.add("CATATAN", "SEWA BULAN APRIL " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 4) {
					r.add("CATATAN", "SEWA BULAN MEI " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 5) {
					r.add("CATATAN", "SEWA BULAN JUN " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 6) {
					r.add("CATATAN", "SEWA BULAN JULAI " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 7) {
					r.add("CATATAN", "SEWA BULAN OGOS " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 8) {
					r.add("CATATAN", "SEWA BULAN SEPTEMBER " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 9) {
					r.add("CATATAN", "SEWA BULAN OKTOBER " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 10) {
					r.add("CATATAN", "SEWA BULAN NOVEMBER " + calCurrent.get(Calendar.YEAR));
				} else if (calCurrent.get(Calendar.MONTH) == 11) {
					r.add("CATATAN", "SEWA BULAN DISEMBER " + calCurrent.get(Calendar.YEAR));
				} else {
					r.add("CATATAN", "");
				}
				r.add("ID_JENISTRANSAKSI", "1"); // CAJ
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPAKAUN");
				stmt.executeUpdate(sql);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void updateFlagTunggakan(String idHasil, Db db) {
		String sql = "";
		Double total = 0D;
		String flagTunggakan = "T";
		
		try {
			Statement stmt = db.getStatement();
			
			sql = "SELECT SUM(DEBIT) - SUM(KREDIT) AS TOTAL FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				total = rs.getDouble("TOTAL");
			}
			
			if (total > 0D) {
				flagTunggakan = "Y";
			}
			sql = "UPDATE TBLPHPHASIL SET FLAG_TUNGGAKAN = '" + flagTunggakan + "' WHERE ID_HASIL = '" + idHasil + "'";
			stmt.executeQuery(sql);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
}
