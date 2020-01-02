package ekptg.model.htp.utiliti;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class RujukanNegeriBean implements IKodRujukan{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.RujukanNegeriBean.class);
    String sql = "";
	
	/**
	 *  
	 */
    @Override	 
	public String getIdByKod(String kod) throws Exception {
		String returnValue = "0";
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT ID_NEGERI FROM TBLRUJNEGERI WHERE " +
	      		" KOD_NEGERI = '"+kod+"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString(1);	}
	      	return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}
    
	/**
	 *  
	 */
    @Override	 
	public String getNamaByKod(String kod) throws Exception {
		String returnValue = "0";
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE " +
	      		" KOD_NEGERI = '"+kod+"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString(1);	}
	      	return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}
	
}
