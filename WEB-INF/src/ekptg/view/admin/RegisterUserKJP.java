package ekptg.view.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class RegisterUserKJP extends AjaxBasedModule {
	 static Logger myLogger = Logger.getLogger(RegisterUserKJP.class);
	 
	 public String doTemplate2() throws Exception {
		 String vm = "vtl/admin/registeruser_KJP.vm";
		 
		 HttpSession session = this.request.getSession();
		 String id_masuk = (String)session.getAttribute("_ekptg_user_id");
		 
		 String submit = getParam("command");
		 String socKementerian = getParam("socKementerian");
		 String socAgensi = getParam("socAgensi");
		 String user_login = getParam("user_login");
		 String user_name = getParam("user_name");
		 String user_password = getParam("user_password");
		 String user_role = getParam("user_role");
		 String page_style = getParam("page_style");
		 String user_type = getParam("user_type");
		 String userRoleNew = getParam("socjenispengguna");
		 String id_jawatan = getParam("socJawatan");
		 myLogger.info("userRoleNew:"+userRoleNew);

 		if(!userRoleNew.equals("")){
			if(userRoleNew.equals("2")){
				 user_role = getParam("user_roleagensi");	
		
			}
 		}
		 
		 String selectKementerian = "",selectAgensi="";	
		 String selectJawatan = "";
		 this.context.put("registerUserStatus", "");
		 
		 if ("add".equals(submit)) {
			  if (isExist(user_login)) {
				  this.context.put("registerUserStatus", "userexist");
			  }
			  else if (this.add(user_name, user_login, user_password, 
					  user_role, page_style, user_type,
					  socKementerian,socAgensi, id_masuk, id_jawatan)) {
	            AuditTrail.logActivity(this,session,"INS","USER KJP ["+user_name+"],AGENSI ID="+socAgensi+" Added");
	          	this.context.put("registerUserStatus", "success");
	          	this.context.put("user_name", user_name);
			  } else {
				this.context.put("registerUserStatus", "failed");
			  }
			  
		 }else if("clear".equals(submit)){
		 
			 selectKementerian = HTML.SelectKementerian("socKementerian",0L, "", "onChange='doChanges();'");
			 selectAgensi = HTML.SelectAgensiByKementerian("socAgensi","",0L,"", "");
			 selectJawatan = HTML.SelectjawatanKJP("socJawatan");
			 
			 this.context.put("selectJawatan",selectJawatan);
			 this.context.put("user_login", "");
			 this.context.put("user_name", "");
			 this.context.put("user_password", "");
			 this.context.put("selectKementerian", selectKementerian);
			 this.context.put("selectAgensi", selectAgensi);
			 this.context.put("idjenispengguna", "");
		 
		 }else {
			 if ("doChanges".equals(submit)) {
				 selectKementerian = HTML.SelectKementerian("socKementerian",Utils.parseLong(socKementerian), "", "onChange='doChanges();'");
				 selectAgensi = HTML.SelectAgensiByKementerian("socAgensi",socKementerian,Utils.parseLong(socAgensi), "");
			 } else {
				 selectKementerian = HTML.SelectKementerian("socKementerian",0L, "", "onChange='doChanges();'");
				 selectAgensi = HTML.SelectAgensiByKementerian("socAgensi","",0L,"", "");
			 }
			 
			 this.context.put("selectJawatan",HTML.SelectjawatanKJP("socJawatan",Utils.parseLong(id_jawatan)));
			 this.context.put("user_login", user_login);
			 this.context.put("user_name", user_name);
			 this.context.put("user_password", user_password);
			 this.context.put("selectKementerian", selectKementerian);
			 this.context.put("selectAgensi", selectAgensi);
			 this.context.put("idjenispengguna", userRoleNew);
		 }
		 return vm;
	 }
	 
	  public boolean add(String username,String userlogin, String password, String role, String style,
						 String usertype,String idKementerian,String idAgensi,String id_masuk,String id_jawatan) throws Exception { 
			Db db = null;
			Connection conn = null;
			String sql = "";
			try
			{
				db = new Db();
				Statement stmt = db.getStatement();
				conn = db.getConnection();
				conn.setAutoCommit(false);
			
				SQLRenderer r = new SQLRenderer();
				r.add("user_login");
				r.add("user_login", userlogin);
				sql = r.getSQLSelect("users");
			//myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.next()) {
					long id = DB.getNextID("USERS_SEQ");
					r.clear();
					r.add("user_id", id);
					r.add("user_login", userlogin);
					r.add("user_password", PasswordService.encrypt(password));
					r.add("user_name", username);
					r.add("user_role", role);
					r.add("user_type", usertype);
					r.add("date_registered",r.unquote("sysdate")); // Oracle date time
					r.add("id_masuk", id_masuk);
					r.add("tarikh_masuk", r.unquote("sysdate"));
					r.add("id_kemaskini", id_masuk);
					r.add("tarikh_kemaskini", r.unquote("sysdate"));
					myLogger.info("SQL Insert users:"+sql);
					sql = r.getSQLInsert("users");
					stmt.executeUpdate(sql);
			
					//masukkan dlm table user internal
					r.clear();
					r.add("user_id",id);
					r.add("id_masuk", id_masuk);
					r.add("id_jawatan",id_jawatan);
					r.add("tarikh_masuk", r.unquote("sysdate"));
					r.add("id_kemaskini", id_masuk);
					r.add("tarikh_kemaskini", r.unquote("sysdate"));
					//myLogger.info("SQL Insert user:"+sql);
					sql = r.getSQLInsert("users_internal");
					stmt.executeUpdate(sql);
			  			  
					//masukkan dlm table user kementerian
					r.clear();
					long idUserKementerian = DB.getNextID("USERS_KEMENTERIAN_SEQ");
					r.add("id_userskementerian",id);
					r.add("user_id",id);
					r.add("id_kementerian",idKementerian);
					r.add("id_agensi",idAgensi);
					r.add("id_masuk", id_masuk);
					r.add("tarikh_masuk", r.unquote("sysdate"));
					r.add("id_kemaskini", id_masuk);
					r.add("tarikh_kemaskini", r.unquote("sysdate"));
					//myLogger.info("SQL Insert user:"+sql);
					sql = r.getSQLInsert("users_kementerian");
					stmt.executeUpdate(sql);
			  
					//MASUKKAN DLM TABLE USER_ROLE 
					// Komen Oleh Mohamad Rosli pada 16/11/2011, hanya role "online_kjp" sahaja diguna
//			  		r.clear();
//			  		r.add("user_id",userlogin);
//			  		r.add("role_id","OnlineHTP");
//			  		sql = r.getSQLInsert("user_role");
//			  		stmt.executeUpdate(sql);
			  
					conn.commit();
				} else { 
				//- user already exist
				return false ; }
			
			}
			catch(SQLException ex)
			{
			try
			{
			conn.rollback();
			}
			catch(SQLException sqlexception) { }
			throw new DbException((new StringBuilder(String.valueOf(ex.getMessage()))).append(": ").append(sql).toString());
			}
			
			return true;
	  } 	 
	  
	  public boolean isExist(String user_login) throws Exception {
		  	boolean output=false;
			String sql = "";
			Db db = null;
			try {
				db = new Db(); 
				sql = "Select count(*) as total from users where user_login='"+user_login+"' ";
				//myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if (rs.next()){	
					if (rs.getInt("total") > 0) {
						output = true;
					}
					
				}
			} catch (Exception e) {
				throw new Exception ("error getting maklumat pengguna :"+e.getMessage());
			}finally {
				if (db != null) db.close();
			}
			return output;		
	  }
	 

}
