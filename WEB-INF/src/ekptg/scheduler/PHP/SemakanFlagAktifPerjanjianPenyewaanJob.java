package ekptg.scheduler.PHP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SemakanFlagAktifPerjanjianPenyewaanJob implements Job {
	
	static Logger myLogger = Logger.getLogger("SemakanFlagAktifPerjanjianPenyewaanJob");
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		myLogger.info("Executing SemakanFlagAktifPerjanjianPenyewaanJob on : " + new Date());
		System.out.println("Executing SemakanFlagAktifPerjanjianPenyewaanJob on : " + new Date());
		
		Db db = null;
		Connection conn = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			//GET ALL FAIL HASIL
			sql = "SELECT ID_HASIL FROM TBLPHPHASIL ORDER BY ID_HASIL ASC";
			ResultSet rsHasil = stmt.executeQuery(sql);
			
			while (rsHasil.next()) {
				String idHasil = rsHasil.getString("ID_HASIL");
				//SEMAKAN PERJANJIAN TAMBAHAN
				semakanPerjanjianTambahan(idHasil, db);
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) { db.close(); }
		}
		
		myLogger.info("Finish SemakanFlagAktifPerjanjianPenyewaanJob on : " + new Date());
		System.out.println("Finish SemakanFlagAktifPerjanjianPenyewaanJob on : " + new Date());
	}

	private void semakanPerjanjianTambahan(String idHasil, Db db) {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_BAYARANPERLUDIBAYAR FROM TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE FLAG_PERJANJIAN != 'U' AND FLAG_AKTIF = 'Y'";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
