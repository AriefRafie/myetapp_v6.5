package ekptg.model.htp.utiliti;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;
//import ekptg.model.htp.HtpBean;


public class HTPRoleBean implements IHTPRole{
	
// 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.HTPRoleBean.class);
//	private final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    String sql = "";
//	PfdFail fail = null;
//	Permohonan permohonan = null;
//	HtpPermohonan htpPermohonan = null;

	@Override
	/** 
	 * Mengenalpasti role yang diperolehi oleh pengguna 
	 */
	public boolean isUserRoleUsers(String userid,String roleid) throws Exception {
		boolean returnValue = false;
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT USER_ID FROM USERS WHERE " +
	      		" USER_ID = " +userid+
	      		" AND USER_ROLE LIKE '"+roleid+"'"+
	      		" ";
	      myLog.info("isUserRole:sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = true;	}
	      return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}

	  
//	private IHtp getIHTP(){
//		if(iHTP== null)
//			iHTP = new HtpBean();
//		return iHTP;
//	}	
	
	
}
