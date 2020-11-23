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

	public OnlineUser getUserInternal(String loginName){
		OnlineUser user = null;
		Db db = null;
		try{
			db = new Db();

			String sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
					  " PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,J.KETERANGAN AS NAMA_JAWATAN, k.NAMA_KEMENTERIAN, a.NAMA_AGENSI "+
					  " FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,  USERS_KEMENTERIAN UK, TBLRUJKEMENTERIAN K, TBLRUJAGENSI A "+
					  " WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
					  " AND UI.USER_ID = UK.USER_ID(+) AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) AND UK.ID_AGENSI = A.ID_AGENSI(+) "+
					  " AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
	                  " AND UI.ID_NEGERI = N.ID_NEGERI(+) "+
	                  " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)"+
	                  " AND UI.ID_DAERAH = D.ID_DAERAH(+)"+
					  " AND UI.USER_ID = '"+loginName+"'";
			System.out.println("getUserOnline :: sql >>> "+sql);

			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				System.out.println("rs.getString(NAMA_JAWATAN) >>> "+rs.getString("NAMA_JAWATAN"));
				user = new OnlineUser();
				user.setId(rs.getString("USER_ID"));
				user.setEmail(rs.getString("EMEL"));
				user.setName(rs.getString("USER_NAME"));
				user.setJawatan(rs.getString("NAMA_JAWATAN"));
				user.setSeksyen(rs.getString("NAMA_SEKSYEN"));
				user.setPejabat(rs.getString("NAMA_PEJABAT"));
				user.setNegeri(rs.getString("NAMA_NEGERI"));
				user.setDaerah(rs.getString("NAMA_DAERAH"));
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
