/**
 * 
 */
package dataCleaning.php;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;

/**
 * @author Mohd Faizal
 *
 */
public class SemakLuasHakmilikPermohonanSewa {
	
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
		Db db1 = null;
		Db db2 = null;
		Connection conn = null;
		String sql = "";
		
		try {
			db = new Db();
			db1 = new Db();
			db2 = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_PERMOHONAN, A.ID_HAKMILIK AS ID_PHPHAKMILIK, B.ID_HAKMILIKAGENSI, B.ID_HAKMILIKSEMENTARA, B.FLAG_HAKMILIK"
					+ " FROM TBLPHPHAKMILIK A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_HAKMILIKPERMOHONAN = B.ID_HAKMILIKPERMOHONAN"
					+ " AND A.LUAS IS NULL";
			sql = sql + " ORDER BY B.ID_PERMOHONAN ASC NULLS LAST";	
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String idPermohonan = rs.getString("ID_PERMOHONAN") != null ? rs.getString("ID_PERMOHONAN") : "";
				String idPHPHakmilik = rs.getString("ID_PHPHAKMILIK") != null ? rs.getString("ID_PHPHAKMILIK") : "";
				String idHakmilikAgensi = rs.getString("ID_HAKMILIKAGENSI") != null ? rs.getString("ID_HAKMILIKAGENSI") : "";
				String idHakmilikSementara = rs.getString("ID_HAKMILIKSEMENTARA") != null ? rs.getString("ID_HAKMILIKSEMENTARA") : "";
				String flagHakmilik = rs.getString("FLAG_HAKMILIK") != null ? rs.getString("FLAG_HAKMILIK") : "";
				
				if (idHakmilikAgensi != null && !"".equals(idHakmilikAgensi)) {
					updateLuasHakmilikAgensi(idPermohonan, idPHPHakmilik, idHakmilikAgensi, flagHakmilik, db1, db2);
				} else if (idHakmilikSementara != null && !"".equals(idHakmilikSementara)) {
					updateLuasHakmilikSementara(idPermohonan, idPHPHakmilik, idHakmilikSementara, flagHakmilik, db1, db2);
				}
			}
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
			if (db1 != null) db1.close();
			if (db2 != null) db2.close();
		}		
	}

	private static void updateLuasHakmilikAgensi(String idPermohonan, String idPHPHakmilik, String idHakmilikAgensi, String flagHakmilik, Db db1, Db db2) {
		String sql = "";
		try {
			Statement stmt = db1.getStatement();
			sql = "SELECT LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE LUAS_BERSAMAAN IS NOT NULL AND ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			if (rsHA.next()) {
				String luas = rsHA.getString("LUAS_BERSAMAAN");
				updateDataHA(idPermohonan, idPHPHakmilik, idPHPHakmilik, luas, db2);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void updateLuasHakmilikSementara(String idPermohonan, String idPHPHakmilik, String idHakmilikSementara, String flagHakmilik, Db db1, Db db2) {
		String sql = "";
		try {
			Statement stmt = db1.getStatement();
			sql = "SELECT LUAS FROM TBLPHPHAKMILIKSEMENTARA WHERE LUAS IS NOT NULL AND ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara + "'";
			ResultSet rsHS = stmt.executeQuery(sql);
			if (rsHS.next()) {
				String luas = rsHS.getString("LUAS");
				updateDataHA(idPermohonan, idPHPHakmilik, idPHPHakmilik, luas, db2);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void updateDataHA(String idPermohonan, String idPHPHakmilik, String flagHakmilik, String luas, Db db2) {
		String sql = "";
		try {
			Statement stmt = db2.getStatement();
			sql = "UPDATE TBLPHPHAKMILIK SET ID_LUAS = '2', ID_UNITLUASAMBIL = '2', LUAS = '" + luas + "', LUAS_AMBIL = '" + luas + "' WHERE ID_HAKMILIK = '" + idPHPHakmilik + "'";
			stmt.execute(sql);
			
			if ("U".equals(flagHakmilik)) {
				sql = "UPDATE TBLPHPPERMOHONANSEWA SET ID_LUAS_ASAL = '2', LUAS_ASAL = '" + luas + "' WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				stmt.execute(sql);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
}
