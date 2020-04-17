package etapp.template;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class UOASecondaryRoles {
	public static void addSecondaryRoles(String username, String[] roles) throws Exception {
		Db db = null;
		try {
//			User user = getUser(userId);
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			String sql = "";
			{
				sql = "DELETE FROM user_role WHERE user_id = '" + username +"'";
				db.getStatement().executeUpdate(sql);
			}
			{
				for ( String role : roles ) {
					sql = r
					.reset()
					.add("role_id", role)
					.add("user_id", username)
					.getSQLInsert("user_role")
					;
					db.getStatement().executeUpdate(sql);
				}
			}
		} finally { 
			if ( db != null ) db.close();
		}
	}
}
