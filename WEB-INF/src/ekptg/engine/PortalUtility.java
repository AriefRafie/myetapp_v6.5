package ekptg.engine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.portal.element.User;

import org.apache.commons.lang.RandomStringUtils;

import ekptg.helpers.Utils;


public class PortalUtility implements IPortalUtility{
	public String resetPassword(String userId,String email)throws Exception{
		String p = RandomStringUtils.randomAlphanumeric(6);
		System.out.println("generated password "+p);
		String en = lebah.util.PasswordService.encrypt(p);
		System.out.println("encrypted password "+en);
		int result = updatePassword(userId, en);
		if(result > 0){
			return p;
		}
		else{
			return null;
		}
		
	}
	private int updatePassword(String userId,String password)throws Exception{
		Db db = null;
		Connection conn = null;
		String sql = "";
		try{
			db= new Db();
			//conn = db.getConnection();
			Statement stat = db.getStatement();
			sql ="UPDATE USERS SET USER_PASSWORD='"+password+"' WHERE USER_LOGIN='"+userId+"'";
			int s = stat.executeUpdate(sql);
			System.out.println(s);
			return s;
		}
		catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null)
			db.close();
		}
		return 0;
	}
	public OnlineUser getUserOnline(String loginName){
		OnlineUser user = null;
		Db db = null;
		try{
			db = new Db();
			String sql = "Select a.user_id,user_name,no_kp_baru,b.emel,kategori from users a ,users_online b "+
			"where a.user_id=b.user_id "+
    		"and a.user_id='" + loginName + "' ";
			
			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				user = new OnlineUser();
				user.setId(rs.getString("user_id"));
				user.setEmail(rs.getString("emel"));
				user.setNoKp(rs.getString("no_kp_baru"));
				user.setName(rs.getString("user_name"));
				user.setKategori(rs.getString("kategori"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public OnlineUser getUserKJP(String loginName){
		OnlineUser user = null;
		Db db = null;
		try{
			db = new Db();
			String sql = "Select a.user_id,a.user_name from users a "+
    		"where a.user_id='" + loginName + "' ";
			
			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				user = new OnlineUser();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("user_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	@Deprecated
    public User getUserOnlineById(String usrlogin) {
    	User user = null;
    	Db db = null;
    	String sql="";
        String name = ""; String password = ""; String role = ""; String login_alt = "";
        String nama_seksyen = "",nama_negeri="",nama_daerah="",nama_pejabat="",nama_jawatan="",alamat="";
        String id_pejabatjkptg ="",no_kp_baru="",kategori="";

    	try {
    		db = new Db();
    		sql = " Select a.user_id,user_name,no_kp_baru,b.kategori from users a ,users_online b "+
    		"where a.user_id=b.user_id "+
    		"and a.user_id='" + usrlogin + "' ";
    		Statement stmt = db.getStatement();
    		ResultSet rs = stmt.executeQuery(sql);
    		Hashtable h = new Hashtable();
    		if (rs.next()) {
    			// name = rs.getString("user_name");
    			 //no_kp_baru = rs.getString("no_kp_baru");
    			 h.put("name",rs.getString("user_name"));
    			 h.put("no_kp_baru",Utils.isNull(rs.getString("no_kp_baru")));
    			 h.put("kategori",Utils.isNull(rs.getString("kategori")));
    		}
    		//user = new User(usrlogin, name, password, role);
    		user = new User(h);
    	}catch (Exception e) {
    		
    	}finally {
    		if (db != null) db.close();
    	}
    	return user;
    }

}
