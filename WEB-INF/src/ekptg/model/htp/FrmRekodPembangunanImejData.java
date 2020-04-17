package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmRekodPembangunanImejData {
	
	private static Vector listImejByIdHakmilik = null;
	private static Vector listImejById = null;
	
	private static Logger myLog = Logger.getLogger(FrmRekodPembangunanImejData.class);
	// SENARAI MAKLUMAT PERIHAL BY ID HAKMILIK
	public static Vector getMaklumatImejByIdHakmilik(String id) throws Exception {
		Db db = null;
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listImejByIdHakmilik = new Vector();
			Statement stmt = db.getStatement();						
			sql =	"SELECT A.ID_HAKMILIK,B.ID_GAMBAR, B.RINGKASAN, B.PERIHAL_IMEJ,B.NAMA_FAIL "+
					"FROM TBLHTPHAKMILIK A, TBLHTPGAMBAR B "+
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK AND B.JENIS_GAMBAR = 'ASAS' "+
					"AND A.ID_HAKMILIK = '"+id +"' ORDER BY B.ID_GAMBAR DESC";			
			//myLog.info("papar detail gambar "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idGambar", rs.getString("ID_GAMBAR"));
				h.put("namaFail", rs.getString("NAMA_FAIL"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("ringkasan", rs.getString("RINGKASAN")==null ? "" :rs.getString("RINGKASAN"));
				h.put("perihalImej", rs.getString("PERIHAL_IMEJ")==null ? "" :rs.getString("PERIHAL_IMEJ"));
				listImejByIdHakmilik.addElement(h);
		    	bil++;
		    	count++;
			}
//		    if (count == 0){
//		        h = new Hashtable();
//		    	h.put("bil","");
//		    	h.put("idGambar",0);
//		        h.put("ringkasan", "Tiada rekod.");
//		    	h.put("perihalImej", "");
//		    	
//		    	listImejByIdHakmilik.addElement(h);
//	      }			
		} finally {
			if (db != null)
				db.close();
		}
		return listImejByIdHakmilik;
	}
//	// ADD IMEJ BY ID_HAKMILIK
//	public static String addDetailImej(Hashtable data) throws Exception {
//		Db db = null;
//		Connection conn = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date(); 
//		String currentDate = sdf.format(date);
//
//		String sql = "";
//
//		try {
//			db = new Db();
//			conn = db.getConnection();
//	    	conn.setAutoCommit(false);
//			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
//			
//		    long idImej = DB.getNextID("TBLHTPGAMBAR_SEQ"); 
//		    r.add("ID_GAMBAR",idImej);
//		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
//		    r.add("PERIHAL_IMEJ",data.get("txtPerihalImej"));
//		    r.add("RINGKASAN",data.get("txtRingkas"));
//		    
//		    sql = r.getSQLInsert("TBLHTPGAMBAR");
//		    log.info("sql insert baru "+sql);
//			stmt.executeUpdate(sql);
//	
//			conn.commit();
//		    return ""+idImej;
//		    
//		}catch (SQLException ex) { 
//	    	try {
//	    		conn.rollback();
//	    	} catch (SQLException e) {
//	    		throw new Exception("Rollback error : " + e.getMessage());
//	    	}
//	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
//	    	
//	    } finally {
//			if (db != null)
//				db.close();
//		}
//	}
	// PAPAR MAKLUMAT PERGERAKAN BY ID
	public static Vector getMaklumatImejById(String id) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listImejById = new Vector();
			Statement stmt = db.getStatement();
			
			sql =	"SELECT A.ID_HAKMILIK,B.ID_GAMBAR, B.RINGKASAN, B.PERIHAL_IMEJ," +
					"TO_CHAR(B.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_KEMASKINI "+
					"FROM TBLHTPHAKMILIK A, TBLHTPGAMBAR B "+
					"WHERE A.ID_HAKMILIK = B.ID_HAKMILIK "+
					"AND B.ID_GAMBAR = ' "+id +"'";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idGambar", rs.getString("ID_GAMBAR"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("ringkasan", rs.getString("RINGKASAN")==null ? "" :rs.getString("RINGKASAN"));
				h.put("perihalImej", rs.getString("PERIHAL_IMEJ")==null ? "" :rs.getString("PERIHAL_IMEJ"));
				h.put("tarikh", rs.getString("TARIKH_KEMASKINI")==null ? "" :rs.getString("TARIKH_KEMASKINI"));
				listImejById.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return listImejById;
	}
	// UPDATE DETAL IMAGE
	public static void updateImejById(Hashtable data) throws Exception {
	    Db dbImej = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(); 
		String currentDate = sdf.format(date);
	    String sql = "";
	    try
	    {
	    	dbImej = new Db();
			  Statement stmtGambar = dbImej.getStatement();
			  SQLRenderer rImej = new SQLRenderer();
			  rImej.update("ID_GAMBAR", data.get("idGambar"));
			  rImej.add("PERIHAL_IMEJ", data.get("txtPerihalImej"));
			  rImej.add("RINGKASAN", data.get("txtRingkas"));
	    	  //convert date before add
			  String tarikhKemaskini = currentDate;
			  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			  rImej.add("TARIKH_KEMASKINI", rImej.unquote(txdTarikhKemaskini));
			  sql = rImej.getSQLUpdate("TBLHTPGAMBAR");
			  stmtGambar.executeUpdate(sql);
		 }
		 finally {
		    if (dbImej != null) dbImej.close();
		 }
	}
	public static void hapusImej(String idGambar) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPGAMBAR
			r.add("ID_GAMBAR", idGambar);

			sql = r.getSQLDelete("TBLHTPGAMBAR");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	// UPDATE DETAL IMAGE by idhakmilikperihal
		public static void updateImejByIdhakmilikperihal(Hashtable data) throws Exception {
		    Db dbImej = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(); 
			String currentDate = sdf.format(date);
		    String sql = "";
		    try
		    {
		    	dbImej = new Db();
				  Statement stmtGambar = dbImej.getStatement();
				  SQLRenderer rImej = new SQLRenderer();
				  rImej.update("ID_HAKMILIKPERIHAL", data.get("idHakmilikPerihal"));
				  rImej.add("PERIHAL_IMEJ", data.get("txtPerihalImej"));
				 
		    	  //convert date before add
				  String tarikhKemaskini = currentDate;
				  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
				  rImej.add("TARIKH_KEMASKINI", rImej.unquote(txdTarikhKemaskini));
				  sql = rImej.getSQLUpdate("TBLHTPGAMBAR");
				  stmtGambar.executeUpdate(sql);
			 }
			 finally {
			    if (dbImej != null) dbImej.close();
			 }
		}
		
		public static void hapusImejByIdHakmilikPerihal(String idHakmilikPerihal) throws Exception {

			Db db = null;
			Connection conn = null;
			String sql = "";

			try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
				
				//TBLHTPHAKMILIKPERIHAL
				r.add("ID_HAKMILIKPERIHAL", idHakmilikPerihal);
				System.out.println("id perihal==="+idHakmilikPerihal);
				sql = "DELETE FROM TBLHTPGAMBAR WHERE ID_HAKMILIKPERIHAL= " +idHakmilikPerihal;
				stmt.executeUpdate(sql);
				
				conn.commit();
				
			} catch (SQLException ex) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException e) {
		    		throw new Exception("Rollback error : " + e.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
		    	
		    } finally {
				if (db != null)
					db.close();
			}	
		}
}
