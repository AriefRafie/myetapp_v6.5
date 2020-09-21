package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmAPBKemasukanDokumenData {
	
	static Logger myLogger = Logger.getLogger(FrmAPBKemasukanDokumenData.class);	
	protected Db db;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

	public Vector list() throws Exception {

		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_NAMALAPORAN = "", RS_ID = "";

			sql = " SELECT NAMA_LAPORAN, ID FROM TBLINTMACGDILAPORAN WHERE URUSAN = 'HTP'";

			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_NAMALAPORAN = rs.getString("NAMA_LAPORAN") == null ? "" : rs
						.getString("NAMA_LAPORAN");
				RS_ID = rs.getString("ID") == null ? "" : rs.getString("ID");

				h = new Hashtable();
				h.put("No", iCount);
				h.put("NamaLaporan", RS_NAMALAPORAN);
				h.put("id", RS_ID);
				v.addElement(h);
				iCount++;
				System.out.println("count------" + iCount);
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	public Vector papar(String id) throws Exception {

		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_NAMALAPORAN = "", RS_ID = "", RS_USERLOGIN = "", RS_USERNAME = "";
			String RS_TARIKHCETAK;

			if ((!"".equalsIgnoreCase(id) && (!"-1".equalsIgnoreCase(id)))) {
				SQL_SEARCH += " AND B.ID =" + id;
			}

			sql = " SELECT DISTINCT A.ID_SENARAILAPORAN, A.USER_LOGIN," +
					" C.USER_NAME, B.NAMA_LAPORAN AS NAMA_LAPORAN, B.ID," +
					" MAX(A.TARIKH_CETAK) AS TARIKH_CETAK" +
					" FROM TBLINTMACGDIHISTORY A, TBLINTMACGDILAPORAN B, USERS C" +
					" WHERE A.USER_LOGIN = C.USER_LOGIN" +
					" AND A.ID_SENARAILAPORAN = B.ID"
					+ SQL_SEARCH
					+ " GROUP BY B.NAMA_LAPORAN,A.ID_SENARAILAPORAN, A.USER_LOGIN, C.USER_NAME,  B.ID ";

			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("masuk sini");
//			while (rs.next()) {
//
//				RS_NAMALAPORAN = rs.getString("NAMA_LAPORAN") == null ? "" : rs
//						.getString("NAMA_LAPORAN");
//				RS_ID = rs.getString("ID") == null ? "" : rs.getString("ID");
//				RS_USERLOGIN = rs.getString("USER_LOGIN") == null ? "" : rs
//						.getString("USER_LOGIN");
//				RS_USERNAME = rs.getString("USER_NAME") == null ? "" : rs
//						.getString("USER_NAME");
//				RS_TARIKHCETAK = rs.getDate("TARIKH_CETAK") == null ? "" : sdf
//						.format(rs.getDate("TARIKH_CETAK"));
//
//				h = new Hashtable();
//				h.put("No", iCount);
//				h.put("NamaLaporan", RS_NAMALAPORAN);
//				h.put("NamaPengguna", RS_USERNAME);
//				h.put("id", RS_ID);
//				h.put("TarikhCetak", RS_TARIKHCETAK);
//				v.addElement(h);
//				iCount++;
//				System.out.println("count------" + iCount);
//			}
			while (rs.next()) {
			h = new Hashtable();
			h.put("id", rs.getString("ID") == null ? "" : rs.getString("ID"));
			h.put("NamaPengguna", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
			h.put("tarikhCetak", rs.getString("TARIKH_CETAK") == null ? "" : rs.getString("TARIKH_CETAK"));
			h.put("No", iCount);
			
			v.addElement(h);
			iCount++;
			}
			
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Vector getSenaraiLaporanAPB() {
		String sql = "";
		Vector listLaporan = null;
		Hashtable h;
		
		try {
			listLaporan = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			//sql = "SELECT ID, NAMA_LAPORAN"
			//		+ " FROM TBLINTMACGDILAPORAN"
			//		+ " WHERE URUSAN = 'APB'";
			sql = "SELECT DISTINCT ID, NAMA_DOKUMEN_RUJUKAN"
					+ " FROM TBLINTPHPDOKUMENRUJUKAN"
					+ " WHERE URUSAN = 'APB'";			
			
			sql = sql + " ORDER BY ID ASC";	
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.error("tolong keluar: " +sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idLaporan", rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("namaLaporan", rs.getString("NAMA_DOKUMEN_RUJUKAN") == null ? "" : rs.getString("NAMA_DOKUMEN_RUJUKAN"));	
				
				listLaporan.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listLaporan;
	}
	
	public Hashtable getMaklumatLaporan(String idLaporan) {
		Db db = null;
		String sql = "";
		Hashtable laporan = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLINTPHPDOKUMENRUJUKAN WHERE ID = '" + idLaporan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				laporan = new Hashtable();
				laporan.put("idLaporan",rs.getString("ID") == null ? "" : rs.getString("ID"));
				laporan.put("namaLaporan", rs.getString("NAMA_DOKUMEN_RUJUKAN") == null ? "" : rs.getString("NAMA_DOKUMEN_RUJUKAN").toUpperCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return laporan;
	}
	
	public Vector getSenaraiDokumen(String idLaporan) {
		String sql = "";
		Vector listDokumen = null;
		Hashtable h;
		
		try {
			listDokumen = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			//sql = "SELECT ID, NAMA_DOKUMEN, CATATAN, TARIKH_CETAK, USER_LOGIN FROM TBLINTMACGDILAPORANDOKUMEN WHERE ID_MACGDILAPORAN = '" + idLaporan + "'";
			//sql = sql + " ORDER BY NAMA_DOKUMEN ASC";
			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN_RUJUKAN = '" + idLaporan + "'";
			sql = sql + " ORDER BY NAMA_DOKUMEN ASC";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				//h.put("tarikhCetak", rs.getString("TARIKH_CETAK") == null ? "" : rs.getString("TARIKH_CETAK"));
				
				listDokumen.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listDokumen;
	}
	
	public void hapusDokumen(String idDokumen) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPPKDOKUMENSENARAIHUTANG
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			//TBLINTPHPDOKUMENRUJUKAN
			//sql = r.getSQLDelete("TBLINTMACGDILAPORANDOKUMEN");
			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	/*public Vector paparAPB(String id) throws Exception {

		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_ID = "", RS_USERNAME = "";
			String RS_TARIKHCETAK;

			if ((!"".equalsIgnoreCase(id) && (!"-1".equalsIgnoreCase(id)))) {
				SQL_SEARCH += " AND M.ID =" + id;
			}

			sql = " SELECT M.ID, M.TARIKH_CETAK, U.USER_NAME" +
					" FROM TBLINTMACGDILAPORANDOKUMEN M, USERS U "
					+ " WHERE M.USER_LOGIN = U.USER_LOGIN "
					+ SQL_SEARCH
					+ " ORDER BY M.TARIKH_CETAK DESC ";

			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("masuk sini APB");
				while (rs.next()) {
					h = new Hashtable();
					h.put("id", rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("NamaPengguna", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
					h.put("tarikhCetak", rs.getString("TARIKH_CETAK") == null ? "" : rs.getString("TARIKH_CETAK"));
					h.put("No", iCount);
					
					v.addElement(h);
					iCount++;
				}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}*/
}
