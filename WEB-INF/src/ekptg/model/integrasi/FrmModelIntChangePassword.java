package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import lebah.db.Db;
import lebah.util.PasswordService;

public class FrmModelIntChangePassword {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Boolean changePassword(String userID, String User_NewPass1, String User_NewPass2, String User_CurPass) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String sql = "";
			if (!"".equalsIgnoreCase(userID)) {
				if (User_NewPass1.trim().equalsIgnoreCase(User_NewPass2.trim())) {
					Boolean canChange = false;
					sql = "SELECT USER_ID FROM USERS WHERE USER_ID = " + userID + " AND USER_PASSWORD = '" + PasswordService.encrypt(User_CurPass) + "'";
					Statement stmt = db.getStatement();
					ResultSet rs = null;
		            //
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						canChange = true;
					}
					if (canChange) {
						sql = "UPDATE USERS SET USER_PASSWORD = '" + PasswordService.encrypt(User_NewPass1) + "' WHERE USER_ID = " + userID;
			            //System.out.println("sql="+sql);
						stmt.execute(sql);
						returnValue = true;

					}
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
		
	}
	
	
}