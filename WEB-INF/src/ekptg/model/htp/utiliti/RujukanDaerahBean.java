package ekptg.model.htp.utiliti;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class RujukanDaerahBean implements IKodRujukan{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.RujukanDaerahBean.class);
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
	      sql = " SELECT RD.ID_DAERAH " +
	      		" FROM TBLRUJDAERAH RD,TBLRUJNEGERI RN " +
	      		" WHERE RD.ID_NEGERI = RN.ID_NEGERI " +
	      		" AND RN.KOD_NEGERI = '"+kod.substring(0,2)+"'" +
	      		" AND RD.KOD_DAERAH = '"+kod.substring(2,4)+"'";
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
	      sql = " SELECT RD.NAMA_DAERAH " +
    		" FROM TBLRUJDAERAH RD,TBLRUJNEGERI RN " +
    		" WHERE RD.ID_NEGERI = RN.ID_NEGERI " +
    		" AND RN.KOD_NEGERI = '"+kod.substring(0,2)+"'" +
    		" AND RD.KOD_DAERAH = '"+kod.substring(2,4)+"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString(1);	}
	      	return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}
	
}
