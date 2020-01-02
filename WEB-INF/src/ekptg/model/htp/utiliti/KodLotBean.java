package ekptg.model.htp.utiliti;


import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class KodLotBean implements IKod{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.KodLotBean.class);
    String sql = "";
	@Override
	/** 
	 * Mengenalpasti role yang diperolehi oleh pengguna 
	 */
	public String getKod(String id) throws Exception {
		String returnValue = "TIDAK DI KENALPASTI";
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "SELECT KETERANGAN FROM TBLRUJLOT WHERE " +
	      		" ID_LOT = " +id+
	      		" ";
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString(1);	}
	      return returnValue;
	    } finally {
	      if (db != null) db.close();
	    }		
	
	}

	
}
