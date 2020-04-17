/**
 * 
 */
package dataCleaning.php;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;
import ekptg.view.php2.util.UtilHasil;

/**
 * @author Mohd Faizal
 *
 */
public class RegeneratePerjanjianFailHasil {
	
	//TODO COMMENT CLASS SQLRenderer LINE 372-377 BEFORE START.
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("START JOB ON " + new Date());
		doJob();
		System.out.println("FINISH JOB ON " + new Date());
	}

	private static void doJob() {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
//			sql = "SELECT TBLPFDFAIL.NO_FAIL, TBLPHPPEMOHON.NAMA, TBLPHPBAYARANPERLUDIBAYAR.NO_RUJUKAN,"
//					+ " TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT,"
//					+ " TBLPHPHASIL.ID_HASIL, TBLPFDFAIL.ID_FAIL, TBLPFDFAIL.ID_SUBURUSAN, TBLPHPHASIL.FLAG_TUNGGAKAN, TBLPHPHASIL.FLAG_TUNGGAKAND"
//
//					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLPFDFAIL TBLPFDFAILPERMOHONAN, TBLPERMOHONAN, TBLPHPHAKMILIKPERMOHONAN,"
//					+ " TBLPHPHAKMILIK, TBLPHPBAYARANPERLUDIBAYAR"
//
//					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
//					+ " AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON(+)"
//					+ " AND TBLPHPHASIL.ID_FAILPERMOHONAN = TBLPFDFAILPERMOHONAN.ID_FAIL(+)"
//					+ " AND TBLPFDFAILPERMOHONAN.ID_FAIL = TBLPERMOHONAN.ID_PERMOHONAN(+)"
//					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN(+)"
//					+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN(+)"
//					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
//					+ " AND TBLPFDFAIL.ID_URUSAN = 115"
//					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y'"
//					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
//					+ " AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK(+) = 'U'";
//			sql = sql + " ORDER BY TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA DESC NULLS LAST";
			
			sql = "SELECT ID_HASIL FROM TBLPHPHASIL ORDER BY ID_HASIL ASC";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				i++;
				String idHasil = rs.getString("ID_HASIL");
				System.out.println(i + " - " + idHasil);
				UtilHasil.kemaskiniRekodPerjanjianDanAkaun(idHasil);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}		
	}
}
