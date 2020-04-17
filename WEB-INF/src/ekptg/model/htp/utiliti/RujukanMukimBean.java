package ekptg.model.htp.utiliti;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class RujukanMukimBean implements IKodRujukan{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.RujukanMukimBean.class);
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
	      sql = " SELECT RM.ID_MUKIM " +
	      		" FROM TBLRUJDAERAH RD,TBLRUJNEGERI RN,TBLRUJMUKIM RM " +
	      		" WHERE RD.ID_NEGERI = RN.ID_NEGERI AND RD.ID_DAERAH = RD.ID_DAERAH " +
	      		" AND RN.KOD_NEGERI = '"+kod.substring(0,2)+"'" +
	      		" AND RD.KOD_DAERAH = '"+kod.substring(2,4)+"'" +
	      		" AND RM.KOD_MUKIM = '"+kod.substring(4,6)+"'";
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
	      sql = " SELECT RM.NAMA_MUKIM " +
    		" FROM TBLRUJDAERAH RD,TBLRUJNEGERI RN,TBLRUJMUKIM RM " +
      		" WHERE RD.ID_NEGERI = RN.ID_NEGERI AND RD.ID_DAERAH = RD.ID_DAERAH " +
      		" AND RN.KOD_NEGERI = '"+kod.substring(0,2)+"'" +
      		" AND RD.KOD_DAERAH = '"+kod.substring(2,4)+"'" +
      		" AND RM.KOD_MUKIM = '"+kod.substring(4,6)+"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString(1);	}
	      	return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}
	
}
