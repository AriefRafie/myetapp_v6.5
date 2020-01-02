package ekptg.helpers;

import java.sql.ResultSet;
import java.sql.Statement;

import lebah.db.Db;
import ekptg.model.entities.InternalUser;

public class InternalUserUtil {
	public static InternalUser getSeksyenId(String userId)throws Exception{
		Db db = null;
		InternalUser user = null;
		try{
			db = new Db();
		    Statement stmt = db.getStatement();
		    String sql = "select id_seksyen, id_negeri,id_daerah,id_pejabatjkptg from users_internal where user_id = '"+userId+"'";
		    ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	user = new InternalUser();
		    	user.setIdSeksyen(rs.getString("id_seksyen"));
		    	user.setIdNegeri(rs.getString("id_negeri"));
		    	user.setIdDaerah(rs.getString("id_daerah"));
		    	user.setIdPejabat(rs.getString("id_pejabatjkptg"));
		    	//user.setIdJawatan(rs.getString("id_jawatan "));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		return user;
	}
	 
}
