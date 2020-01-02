package lebah.portal.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

// Referenced classes of package lebah.portal.db:
//            UserLogger
public class AuthenticateUser
{

    private String role;
    private String userLogin;
    private String userName;
    private boolean allowed;
    HttpServletRequest req;
    private String userNegeri;
    private String userPejabat;
    private String userID;
    private String userType;
    static Logger myLog = Logger.getLogger(AuthenticateUser.class);

    public AuthenticateUser(){ }

    public AuthenticateUser(HttpServletRequest req)
    {
        this.req = req;
    }

    public boolean lookup(String username, String password)
        throws Exception
    {
        return lookup(username, password, null);
    }
    
    public boolean lookup(String username, String password, String usertype)
    	throws Exception{
            Db db;
            Statement stmt;
            ResultSet rs;
            String sql;
            if(username == null)
            {
                return false;
            }
            if(username.toUpperCase().indexOf(" OR ") >= 0 && password.toUpperCase().indexOf(" OR ") >= 0)
            {
                myLog.info("[ALERT]: POSSIBLE SQL INJECTION ");
                myLog.info((new StringBuilder("username = ")).append(username).toString());
                myLog.info((new StringBuilder("password = ")).append(password).toString());
                if(req != null)
                {
                    UserLogger.save(req, (new StringBuilder("ALERT!!")).append(username).append(", ").append(password).toString());
                }
                return false;
            }
            db = null;
            stmt = null;
            rs = null;
            sql = "";
            try{
                db = new Db();
                stmt = db.getStatement();
                //SQLRenderer r = new SQLRenderer();
                                
                sql = " SELECT  U.USER_ID, U.USER_ROLE, U.USER_NAME "+
                		" ,(SELECT COUNT(*) FROM USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID) AS CHECK_USERS_INTERNAL  "+
                		" ,(SELECT COUNT(*) FROM USERS_ONLINE UO WHERE U.USER_ID = UO.USER_ID) AS CHECK_USERS_ONLINE  "+
                		//" ,(SELECT COUNT(*) FROM USERS_KEMENTERIAN UK WHERE U.USER_ID = UK.USER_ID) AS CHECK_USERS_KJP  "+
                		//" ,(SELECT COUNT(*) FROM USERS_INTEGRASI UINT WHERE U.USER_ID = UINT.USER_ID) AS CHECK_USERS_INT "+
                		" FROM USERS U "+
                		" WHERE U.USER_LOGIN = '"+username+"'  "+
                		" AND U.USER_PASSWORD = '"+PasswordService.encrypt(password)+"' ";
                
                myLog.info(" TEST CHECK AUTH : "+sql);
                rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    userID = rs.getString("user_id");
                    role = rs.getString("user_role");
                    userName = rs.getString("user_name");
                    userLogin = username;
                    //allowed = true;
                    
                    Integer check_user_internal = rs.getInt("CHECK_USERS_INTERNAL");
                    Integer check_user_online = rs.getInt("CHECK_USERS_ONLINE");
                    //Integer check_user_kjp = rs.getInt("CHECK_USERS_KJP");
                    //Integer check_user_int = rs.getInt("CHECK_USERS_INT");
                    
                    if((check_user_online>0 && usertype.equals("online")) || (check_user_internal>0 && usertype.equals("internal")))
                    {
                    	allowed = true;
                    	if(req != null)
                        {
                    		UserLogger.save(req, username);
                        }
                    	
                    	if(check_user_internal>0 && usertype.equals("internal"))
                    	{
                    		//'online','ppt-online-user','php-online-user','ppk-online-user'
                    		if(role.equals("online") || role.equals("ppt-online-user") || role.equals("php-online-user") || role.equals("ppk-online-user"))
                    		{
                    			String roleOne = getOneInternalRole(username);
                    			if(!roleOne.equals(""))
                    			{
                    			updateUserMainTypeRole(userID,usertype,roleOne);
                    			deleteUserRole(username, roleOne);                    			
                    			insertToReplaceUserRole(username,role);
                    			role = roleOne;
                    			}
                    		}
                    		userNegeri = getNegeriUser(db, userID);
                    		userPejabat = getPejabat(db, userID);
                            
                    	}
                    	else if(check_user_online>0 && usertype.equals("online"))
                    	{
                    		if(!role.equals("online") && !role.equals("ppt-online-user") && !role.equals("php-online-user") && !role.equals("ppk-online-user"))
                    		{
                    			updateUserMainTypeRole(userID,usertype,"online");
                    			deleteUserRole(username, "online");
                    			insertToReplaceUserRole(username,role);
                    			role = "online";
                    		}
                    		userNegeri = getNegeriUser(db, userID);
                            
                    	}
                    }
                }
               
            }
            catch (Exception re) {
        		throw re;
        	}finally {
        		if (db != null)
        			db.close();
        	}           
            return allowed;
        
    }

    //razman comment after decompile
    /*
    public boolean lookup(String username, String password, String usertype)
        throws Exception
    {
        Db db;
        Statement stmt;
        ResultSet rs;
        String sql;
        if(username == null)
        {
            return false;
        }
        if(username.toUpperCase().indexOf(" OR ") >= 0 && password.toUpperCase().indexOf(" OR ") >= 0)
        {
            System.out.println("[ALERT]: POSSIBLE SQL INJECTION ");
            System.out.println((new StringBuilder("username = ")).append(username).toString());
            System.out.println((new StringBuilder("password = ")).append(password).toString());
            if(req != null)
            {
                UserLogger.save(req, (new StringBuilder("ALERT!!")).append(username).append(", ").append(password).toString());
            }
            return false;
        }
        db = null;
        stmt = null;
        rs = null;
        sql = "";
        try
        {
            db = new Db();
            stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.clear();
            r.add("user_id");
            r.add("user_role");
            r.add("user_name");
            r.add("user_login", username);
            r.add("user_password", PasswordService.encrypt(password));
            r.add("user_type", usertype);
            sql = r.getSQLSelect("users");
            myLogger.debug(sql);
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                userID = rs.getString("user_id");
                role = rs.getString("user_role");
                userName = rs.getString("user_name");
                userLogin = username;
                allowed = true;
                if("internal".equals(usertype))
                {
                    userNegeri = getNegeriUser(db, userID);
                    UserLogger.save(req, username);
                }
            }
            break MISSING_BLOCK_LABEL_476;
        }
        catch(SQLException ex)
        {
            System.out.println((new StringBuilder(String.valueOf(ex.getMessage()))).append(": ").append(sql).toString());
            allowed = false;
        }
        if(rs != null)
        {
            rs.close();
            rs = null;
        }
        if(stmt != null)
        {
            stmt.close();
            stmt = null;
        }
        if(db != null)
        {
            db.close();
            db = null;
        }
        break MISSING_BLOCK_LABEL_519;
        Exception exception;
        exception;
        if(rs != null)
        {
            rs.close();
            rs = null;
        }
        if(stmt != null)
        {
            stmt.close();
            stmt = null;
        }
        if(db != null)
        {
            db.close();
            db = null;
        }
        throw exception;
        if(rs != null)
        {
            rs.close();
            rs = null;
        }
        if(stmt != null)
        {
            stmt.close();
            stmt = null;
        }
        if(db != null)
        {
            db.close();
            db = null;
        }
        return allowed;
    }
    */

    public String getRole()
    {
        return role;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getUserLogin()
    {
        return userLogin;
    }

    public boolean getAllowed()
    {
        return allowed;
    }

    public String getUserNegeri()
    {
        return userNegeri;
    }
    public String getUserPejabat()
    {
        return userPejabat;
    }

    public String getUserID()
    {
        return userID;
    }

    public String getuserType()
    {
        return userType;
    }

    public String getNegeriUser(Db db, String userid)
        throws Exception
    {
        String output = "";
        String sql = "";
        try
        {
            sql = (new StringBuilder("select id_negeri from users_internal where user_id='")).append(userid).append("'").toString();
            ResultSet rs = db.getStatement().executeQuery(sql);
            if(rs.next())
            {
                output = rs.getString("id_negeri");
            }
        }
        catch(Exception e)
        {
            throw new Exception((new StringBuilder("error getting id_negeri :")).append(e.getMessage()).toString());
        }
        return output;
    }
    
    public String getPejabat(Db db, String userid)
            throws Exception
        {
            String output = "";
            String sql = "";
            try
            {
                sql = (new StringBuilder("select nvl(ui.id_pejabatjkptg,'') as id_pejabatjkptg from users_internal ui, tblrujpejabatjkptg p where ui.id_pejabatjkptg = p.id_pejabatjkptg and  ui.user_id='")).append(userid).append("'").toString();
                ResultSet rs = db.getStatement().executeQuery(sql);
                if(rs.next())
                {
                    output = rs.getString("id_pejabatjkptg");
                }
            }
            catch(Exception e)
            {
                throw new Exception((new StringBuilder("error getting id_negeri :")).append(e.getMessage()).toString());
            }
            return output;
        }
    
    public void updateUserMainTypeRole(String user_id,String type,String Role) throws Exception {
		//myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("USER_ID", user_id);		
			r.add("USER_TYPE", type);
			r.add("USER_ROLE", Role);			
			sql = r.getSQLUpdate("USERS");		
			myLog.info("updateUserMainTypeRole : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
    
    public void deleteUserRole(String user_name, String role) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			r.add("USER_ID", user_name);
			r.add("ROLE_ID", role);
			sql = r.getSQLDelete("USER_ROLE");
			myLog.info("deleteUserRole : "+sql);
			stmt.executeUpdate(sql);
			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
    
    public void insertToReplaceUserRole(String username,String Role) throws Exception {
		//myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("USER_ID", username);
			r.add("ROLE_ID", Role);			
			sql = r.getSQLInsert("USER_ROLE");	
			myLog.info("insertToReplaceUserRole : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
    
    public String getOneInternalRole(String username) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String role = "";
			
			sql = "  SELECT ROWNUM,USER_ID,ROLE_ID FROM ( "+
					"  select USER_ID,ROLE_ID from user_role  "+
					"  WHERE USER_ID = '"+username+"' "+
					"  AND ROLE_ID NOT IN ('online','ppt-online-user','php-online-user','ppk-online-user') "+
					"  ORDER BY ROLE_ID "+
					"  ) WHERE ROWNUM = 1  ";
				myLog.info("getOneInternalRole : "+sql);
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					role = rs.getString("ROLE_ID") == null ? "" : rs.getString("ROLE_ID");
				}
			
			return role;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
    

}
