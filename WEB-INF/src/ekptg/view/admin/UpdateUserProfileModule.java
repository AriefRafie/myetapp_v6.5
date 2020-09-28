package ekptg.view.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.portal.AjaxBasedModule;
import lebah.portal.db.RegisterUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class UpdateUserProfileModule extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = -981199404771733782L;
	static Logger myLog = Logger.getLogger(UpdateUserProfileModule.class);
	private String user_name = "";
	private String user_password ="";
  
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
	    doJob(session);
	    String template = "vtl/admin/updateuserprofileKJP.vm";
	    return template;
	  
	}

	private void doJob(HttpSession session) throws Exception{
	    String userId = (String)session.getAttribute("_ekptg_user_id");
	    this.context.put("operation", "");
	    String submit = getParam("command");
	
	    String user_type = (String)session.getAttribute("_ekptg_user_type");
	    String user_login = (String)session.getAttribute("_portal_login");
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
//    	myLog.info("user_type:"+user_type);
//    	myLog.info("user_login:"+user_login);
//    	myLog.info(user_id);
    
    if ("update".equals(submit)){
      this.context.put("operation", "update");
      user_login = (String)session.getAttribute("_portal_login");
      String nama_jawatan = (String)this.context.get("nama_jawatan");
      this.context.put("nama_jawatan", nama_jawatan);

      user_name = getParam("user_name");
      user_password = getParam("user_password");
      String emel = getParam("emel");
//      String user_login_alt = getParam("user_login_alt");
      String id_jawatan = getParam("id_jawatan");
      String no_kp_baru = getParam("no_kp_baru");
      if ("".equals(user_password)){
    	  if(RegisterUser.updateStaff(userId, user_password, user_name, id_jawatan,no_kp_baru, emel)){
    		  this.context.put("updateUserSuccess", new Boolean(true));
    	  }else {
          this.context.put("updateUserSuccess", new Boolean(false));
    	  }

      }else if(RegisterUser.updateStaff(userId, user_password, user_name, id_jawatan,no_kp_baru, emel)){      
        this.context.put("updateUserSuccess", new Boolean(true));
      }else
        this.context.put("updateUserSuccess", new Boolean(false));

      session.setAttribute("_portal_username", user_name);
      session.setAttribute("_portal_login", user_login);
    
    } else if ("modeUpdate".equals(submit)) {
      this.context.put("mode", "doUpdate");
    }

    User user = null;
//    if ("online".equals(user_type)) {
//      user = PrepareUser.getUserOnlineById(user_login);
//    }else {
      user = getUserById(user_login);
//    }
//
    if (user == null){
      throw new DbException("User Is NULL!");
    }

    String user_name = user.getName();
    String user_password = user.getPassword();
    String user_role = user.getRole();
    String nama_negeri = user.getNama_negeri();
//
//    if ("internal".equals(user_type)) {
    String nama_seksyen = user.getNama_seksyen();
//      String nama_daerah = user.getNama_daerah();
    String nama_pejabat = user.getNama_pejabat();
//      String nama_jawatan = user.getNama_jawatan();
//      String id_pejabatjkptg = user.getId_pejabatjkptg();
//      String daerah_jagaan = PrepareUser.getDaerahJagaan(id_pejabatjkptg);
    this.context.put("nama_seksyen", nama_seksyen);
//      this.context.put("nama_daerah", nama_daerah);
    this.context.put("nama_pejabat", nama_pejabat);
//      this.context.put("nama_jawatan", nama_jawatan);
//      this.context.put("daerahJagaan", daerah_jagaan);
//    }
//
    String emel = user.getEmel();
    this.context.put("emel", emel);
//    String alamat1 = user.getAlamat1();
//    String alamat2 = user.getAlamat2();
//    String alamat3 = user.getAlamat3();
//
    this.context.put("user_login", user_login);
    this.context.put("user_name", user_name);
    this.context.put("user_password", user_password);
    this.context.put("user_role", user_role);
//    this.context.put("no_kp_baru", user.getNo_kp_baru());
//    this.context.put("nama_negeri", nama_negeri);
//    this.context.put("alamat1", alamat1);
//    this.context.put("alamat2", alamat2);
//    this.context.put("alamat3", alamat3);
    
  }
  
  public static User getUserById(String usrlogin) throws Exception {
      User user = null;
      Db db = null;
      String sql = "";
      try {
        db = new Db();
        Statement stmt = db.getStatement();
        String name = ""; 
        String password = ""; 
        String role = ""; 
        String nama_pejabat = "";
        String nama_seksyen = "";
        String nama_negeri="",nama_daerah="",nama_jawatan="",alamat="";
        String id_pejabatjkptg ="";
        String emel = "";
//        String no_kp_baru = "";
        sql = "SELECT user_login,user_name, user_password, user_role"+
			" ,id_kementerian,(select nama_kementerian from tblrujkementerian where id_kementerian=UK.id_kementerian) as nama_kementerian "+
			" ,id_agensi,(select nama_agensi from tblrujagensi where id_agensi=UK.id_agensi) as nama_agensi "+
//			" id_daerah,(select nama_daerah from tblrujDaerah where id_daerah=B.id_daerah) as nama_daerah, "+
//			" id_jawatan,(select keterangan from tblRujJawatan where id_jawatan=B.id_jawatan) as nama_jawatan,"+
//			" id_pejabatjkptg,B.emel,B.no_kp " +
			" ,B.emel"+
			" FROM users A,users_internal B,users_kementerian UK "+
			" where A.user_id = B.user_id  AND A.user_id = UK.user_id "+
			" AND A.user_login='" + usrlogin + "' ";
        
        myLog.info("sql:"+sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
          name = rs.getString("user_name");
          password = rs.getString("user_password");
          role = rs.getString("user_role");
          
          emel = Utils.isNull(rs.getString("emel"));
          nama_pejabat = rs.getString("nama_kementerian");
          nama_seksyen = rs.getString("nama_agensi");
//          nama_negeri = rs.getString("nama_negeri");
//          nama_daerah = rs.getString("nama_daerah");
//          nama_jawatan = rs.getString("nama_jawatan");
//          id_pejabatjkptg = rs.getString("id_pejabatjkptg");
//          no_kp_baru = Utils.isNull(rs.getString("no_kp"));
        }
//        alamat = f.getAlamatPejabatJKPTG(id_pejabatjkptg);
        String theme = getTheme(usrlogin);
        user = new User(usrlogin, name, password, role, theme,nama_seksyen,
      		  nama_negeri,nama_daerah,nama_jawatan,nama_pejabat,id_pejabatjkptg);
        user.setEmel(emel);
        //user.setAlternateLogin(login_alt);
        //user.setAlamat(alamat);
        //user.setNo_kp_baru(no_kp_baru);
      } catch (SQLException ex) {
        throw new Exception(ex.getMessage() + ": " + sql);
      } finally {
        if (db != null) db.close();
      }
      return user;
      
  }
  
  public static String getTheme(String usrlogin) throws Exception {
	    String theme = "";
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "SELECT u.css_name FROM user_css u, page_css p WHERE u.css_name = p.css_name AND user_login = '" + 
	    		  usrlogin + "'";
	      ResultSet rs = stmt.executeQuery(sql);
	      if(rs.next()){
	          theme = rs.getString("css_name");
	      }else{
	          theme = "";
	      }
	    } finally {	if (db != null) db.close();	}
	    return theme;
	    
  }
  
  
}