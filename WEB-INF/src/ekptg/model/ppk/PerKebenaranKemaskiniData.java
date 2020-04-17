package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;





public class PerKebenaranKemaskiniData {
	
	static Logger myLogger = Logger.getLogger(PerKebenaranKemaskiniData.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);
	
	//DISPLAY LIST USER AGIHAN
	/*
	    SELECT U.USER_NAME,U.USER_ID,U.USER_LOGIN FROM USERS U,USERS_INTERNAL UI
		WHERE 
		U.USER_ID = UI.USER_ID 
		AND (U.USER_ROLE IN ('admin_es','developer_es') 
		OR 
		U.USER_LOGIN IN 
		(SELECT DISTINCT USER_ROLE.USER_ID FROM USER_ROLE WHERE USER_ROLE.ROLE_ID IN ('admin_es','developer_es')))
	*/
	
	@SuppressWarnings("unchecked")
	Vector checkEmail = null;
	
	@SuppressWarnings("unchecked")
	public Vector checkEmail(String userId) throws Exception {
		
		checkEmail = new Vector();
		checkEmail.clear();
		
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
	
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
				checkEmail.addElement(h);
			}
			return checkEmail;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public String getNamaUnit( String id_unit) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String namaUnit = "";
			
			sql = 	" SELECT NAMA_PEJABAT " +
					" FROM " +
					" TBLRUJPEJABATJKPTG A " +
					" WHERE ID_PEJABATJKPTG = "+id_unit ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					namaUnit = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
				}
			
			return namaUnit;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public int isExistStaffUnit(int ID_PKE) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			int isExist = 0;
			
			sql = 	" SELECT  COUNT(ID_PKS) AS ID_PKS " +
					" FROM " +
					" TBLPPKKEBENARANSTAFF A " +
					" WHERE ID_PKE = '"+ID_PKE+"'" ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					isExist = rs.getInt(1);
				}
			
			return isExist;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}



	
	
	
	
}
