/**
 * 
 */
package dataCleaning.php;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;
import lebah.db.SQLRenderer;

/**
 * @author Mohd Faizal
 *
 */
public class UpdateTarikhHantarFailSewaKeHQ {
	
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
			
			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPengarahHQ' ORDER BY ID_FAIL ASC";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String idFail = rs.getString("ID_FAIL");
				Date tarikhTugasan = rs.getDate("TARIKH_DITUGASKAN");				
				updateTarikhHantarHQ(idFail, tarikhTugasan);
				System.out.println(i++ + " : " + idFail);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}		
	}

	private static void updateTarikhHantarHQ(String idFail, Date tarikhTugasan) {
		Db db1 = null;
		String sql = "";
		try {
			db1 = new Db();
			Statement stmt = db1.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.add("TARIKH_HANTAR_HQ", r.unquote("to_date('" + sdf.format(tarikhTugasan) + "','dd/MM/yyyy')"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
