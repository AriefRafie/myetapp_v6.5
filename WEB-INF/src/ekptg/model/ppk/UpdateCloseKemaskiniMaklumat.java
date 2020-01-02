package ekptg.model.ppk;

import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class UpdateCloseKemaskiniMaklumat {
	
	public String updateFlagKemaskiniPerintah(String idFail) throws Exception {
		Db db = null;
		String status = "success";
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERMOHONAN
			r.update("ID_FAIL", idFail);
			r.add("FLAG_KEBENARAN_EDIT", "4");
			//r.add("TARIKH_EDIT_PERINTAH", r.unquote("sysdate"));
			

			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return status;
	}
	
	

}
