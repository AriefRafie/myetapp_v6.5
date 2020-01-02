package ekptg.view.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class RegisterUserARB extends AjaxModule{
	
	static Logger myLogger = Logger.getLogger(RegisterUserARB.class);	
	private final String PATH="vtl/admin/";
	private String vm = PATH +"registeruser_ARB.jsp";
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	HttpSession session = null;
	String action = null;	
		
	public String doAction() throws Exception{
			
		session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		context.put("pengguna_aktif",userId);		
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		context.put("id_negeri_user",user_negeri_login);
		//role = (String)session.getAttribute("myrole");
		String command = getParam("command");
		String mode = getParam("mode");
		action = getParam("action");
		String flag_daftar = "";
		String carian_main = "";
		String cari_mesej = "";
		String flag_arkib = "";		
		String bolehsimpan = "";
		String readmode = "";		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		myLogger.info("COMMAND :"+command);
		//myLogger.info("ROLE :"+role);
		context.put("alert_wujud","");
		
		Vector list_negeri = null;
		Vector list_pejabat = null;
		Vector list_jawatan = null;
		Vector list_role = null;
		Vector list_user = null;
		
		if(command.equals("select_pejabat")){	
			context.put("user_id",getParam("user_id"));
			context.put("user_login",getParam("user_login"));
			context.put("user_password",getParam("user_password"));
			context.put("user_password2",getParam("user_password2"));
			context.put("user_name",getParam("user_name"));
			context.put("user_email",getParam("user_email"));
			context.put("id_jawatan",getParam("id_jawatan"));
			context.put("id_negeri",getParam("id_negeri"));	
			context.put("id_pejabat",getParam("id_pejabat"));
			context.put("jenis_pejabat",getParam("jenis_pejabat"));
			context.put("role",getParam("role"));
			list_negeri = list_negeri("");
			context.put("list_negeri",list_negeri);			
			list_pejabat = list_pejabat("",getParam("id_negeri"),getParam("jenis_pejabat"));
			context.put("list_pejabat",list_pejabat);
			list_jawatan = list_jawatan("");
			context.put("list_jawatan",list_jawatan);
			list_role = list_role("userSemakanPerintah");
			context.put("list_role",list_role);
			
			Hashtable h = (Hashtable) list_pejabat.get(0);
			if (!h.get("ID_PEJABAT").toString().equals("")){
				context.put("NamaPejabat",h.get("NAMA_PEJABAT").toString());	
				context.put("Alamat1",h.get("ALAMAT1").toString());	
				context.put("Alamat2",h.get("ALAMAT2").toString());
				context.put("Alamat3",h.get("ALAMAT3").toString());
				context.put("Poskod",h.get("POSKOD").toString());
				context.put("Bandar",h.get("BANDAR").toString());
				context.put("Negeri",h.get("NEGERI").toString());
				context.put("No_tel",h.get("NO_TEL").toString());
			}
			
		}
		else if(command.equals("select_negeri")){
			context.put("user_id",getParam("user_id"));
			context.put("user_login",getParam("user_login"));
			context.put("user_password",getParam("user_password"));
			context.put("user_password2",getParam("user_password2"));
			context.put("user_name",getParam("user_name"));
			context.put("user_email",getParam("user_email"));
			context.put("id_jawatan",getParam("id_jawatan"));
			context.put("id_negeri",getParam("id_negeri"));	
			context.put("jenis_pejabat","");
			context.put("id_pejabat","");
			context.put("role",getParam("role"));
			list_negeri = list_negeri("");
			context.put("list_negeri",list_negeri);			
			list_pejabat = list_pejabat("",getParam("id_negeri"),getParam("jenis_pejabat"));
			context.put("list_pejabat",list_pejabat);
			list_jawatan = list_jawatan("");
			context.put("list_jawatan",list_jawatan);
			list_role = list_role("userSemakanPerintah");
			context.put("list_role",list_role);
		}
		else if(command.equals("check_pejabat")){
			context.put("user_id",getParam("user_id"));
			myLogger.info("getParam(jenis_pejabat):"+getParam("jenis_pejabat"));
			context.put("user_login",getParam("user_login"));
			context.put("user_password",getParam("user_password"));
			context.put("user_password2",getParam("user_password2"));
			context.put("user_name",getParam("user_name"));
			context.put("user_email",getParam("user_email"));
			context.put("id_jawatan",getParam("id_jawatan"));
			context.put("id_negeri",getParam("id_negeri"));	
			context.put("jenis_pejabat",getParam("jenis_pejabat"));
			context.put("id_pejabat","");
			context.put("role",getParam("role"));
			list_negeri = list_negeri("");
			context.put("list_negeri",list_negeri);			
			list_pejabat = list_pejabat("",getParam("id_negeri"),getParam("jenis_pejabat"));
			context.put("list_pejabat",list_pejabat);
			list_jawatan = list_jawatan("");
			context.put("list_jawatan",list_jawatan);
			list_role = list_role("userSemakanPerintah");
			context.put("list_role",list_role);
		}
		
		else if(command.equals("getUser")){	
			list_user = list_user(getParam("user_id"));
			Hashtable h = (Hashtable) list_user.get(0);
			if (!h.get("USER_ID").toString().equals("")){
			
			context.put("user_id",h.get("USER_ID").toString());
			context.put("user_login",h.get("USER_LOGIN").toString());
			context.put("user_password","");
			context.put("user_password2","");
			context.put("user_name",h.get("USER_NAME").toString());
			context.put("user_email",h.get("EMEL").toString());
			context.put("id_jawatan",h.get("ID_JAWATAN").toString());
			context.put("id_negeri",h.get("ID_NEGERI").toString());	
			context.put("jenis_pejabat",h.get("FLAG_JENIS_PEJABAT").toString());
			context.put("id_pejabat",h.get("ID_PEJABAT").toString());
			context.put("role",h.get("USER_ROLE").toString());
			list_negeri = list_negeri("");
			context.put("list_negeri",list_negeri);			
			list_pejabat = list_pejabat("",h.get("ID_NEGERI").toString(),h.get("FLAG_JENIS_PEJABAT").toString());
			context.put("list_pejabat",list_pejabat);
			list_jawatan = list_jawatan("");
			context.put("list_jawatan",list_jawatan);
			list_role = list_role("userSemakanPerintah");
			context.put("list_role",list_role);	
			
			Hashtable h1 = (Hashtable) list_pejabat.get(0);
			if (!h1.get("ID_PEJABAT").toString().equals("")){
				context.put("NamaPejabat",h1.get("NAMA_PEJABAT").toString());	
				context.put("Alamat1",h1.get("ALAMAT1").toString());	
				context.put("Alamat2",h1.get("ALAMAT2").toString());
				context.put("Alamat3",h1.get("ALAMAT3").toString());
				context.put("Poskod",h1.get("POSKOD").toString());
				context.put("Bandar",h1.get("BANDAR").toString());
				context.put("Negeri",h1.get("NEGERI").toString());
				context.put("No_tel",h1.get("NO_TEL").toString());
			}
			
			}
		}		
		else if(command.equals("hapus")){
			String get_user_id = getParam("user_id");
			hapus(get_user_id);
			default_value();
		}		
		else if(command.equals("add")){
			
			Db db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String alert_wujud = "";
			
			if(!getParam("user_id").equals(""))
			{
				String sql1 = "SELECT USER_ID FROM USERS WHERE USER_ID != '"+getParam("user_id")+"' " +
						" AND USER_LOGIN = '"+getParam("user_login")+"' ";
				myLogger.info("CHECK WUJUD USER UPDATE :"+sql1);
				ResultSet rs1 = stmt.executeQuery(sql1);					
				if(rs1.next()) {
				alert_wujud = "yes";
				}				
			
			}else{
				
				String sql1 = "SELECT USER_ID FROM USERS WHERE  USER_LOGIN = '"+getParam("user_login")+"' ";
				myLogger.info("CHECK WUJUD USER ADD :"+sql1);
				ResultSet rs = stmt.executeQuery(sql1);
				
				if(rs.next()) {				
				alert_wujud = "yes";
				}
			}
			
			
			if(alert_wujud.equals("yes"))
			{
				context.put("alert_wujud","yes");
				context.put("user_id",getParam("user_id"));
				context.put("user_login",getParam("user_login"));
				context.put("user_password",getParam("user_password"));
				context.put("user_password2",getParam("user_password2"));
				context.put("user_name",getParam("user_name"));
				context.put("user_email",getParam("user_email"));
				context.put("id_jawatan",getParam("id_jawatan"));
				context.put("id_negeri",getParam("id_negeri"));	
				context.put("id_pejabat",getParam("id_pejabat"));
				context.put("jenis_pejabat",getParam("jenis_pejabat"));
				context.put("role",getParam("role"));
				list_negeri = list_negeri("");
				context.put("list_negeri",list_negeri);			
				list_pejabat = list_pejabat("",getParam("id_negeri"),getParam("jenis_pejabat"));
				context.put("list_pejabat",list_pejabat);
				list_jawatan = list_jawatan("");
				context.put("list_jawatan",list_jawatan);
				list_role = list_role("userSemakanPerintah");
				context.put("list_role",list_role);
				
				Hashtable h = (Hashtable) list_pejabat.get(0);
				if (!h.get("ID_PEJABAT").toString().equals("")){
					context.put("NamaPejabat",h.get("NAMA_PEJABAT").toString());	
					context.put("Alamat1",h.get("ALAMAT1").toString());	
					context.put("Alamat2",h.get("ALAMAT2").toString());
					context.put("Alamat3",h.get("ALAMAT3").toString());
					context.put("Poskod",h.get("POSKOD").toString());
					context.put("Bandar",h.get("BANDAR").toString());
					context.put("Negeri",h.get("NEGERI").toString());
					context.put("No_tel",h.get("NO_TEL").toString());
				}
			}
			else
			{
			add(getParam("user_id"),getParam("user_login"), getParam("user_password"), getParam("user_name"), getParam("user_email"), 
					getParam("id_jawatan"), getParam("id_negeri"), getParam("jenis_pejabat"), getParam("id_pejabat"),getParam("role"),
					(String)session.getAttribute("_ekptg_user_id"));
			
			if(!getParam("user_id").equals(""))
			{
				list_user = list_user(getParam("user_id"));
				Hashtable h = (Hashtable) list_user.get(0);
				if (!h.get("USER_ID").toString().equals("")){
				
				context.put("user_id",h.get("USER_ID").toString());
				context.put("user_login",h.get("USER_LOGIN").toString());
				context.put("user_password","");
				context.put("user_password2","");
				context.put("user_name",h.get("USER_NAME").toString());
				context.put("user_email",h.get("EMEL").toString());
				context.put("id_jawatan",h.get("ID_JAWATAN").toString());
				context.put("id_negeri",h.get("ID_NEGERI").toString());	
				context.put("jenis_pejabat",h.get("JENIS_PENGGUNA").toString());
				context.put("id_pejabat",h.get("ID_PEJABAT").toString());
				context.put("role",h.get("USER_ROLE").toString());
				list_negeri = list_negeri("");
				context.put("list_negeri",list_negeri);			
				list_pejabat = list_pejabat("",h.get("ID_NEGERI").toString(),h.get("FLAG_JENIS_PEJABAT").toString());
				context.put("list_pejabat",list_pejabat);
				list_jawatan = list_jawatan("");
				context.put("list_jawatan",list_jawatan);
				list_role = list_role("userSemakanPerintah");
				context.put("list_role",list_role);	
				
				Hashtable h1 = (Hashtable) list_pejabat.get(0);
				if (!h1.get("ID_PEJABAT").toString().equals("")){
					context.put("NamaPejabat",h1.get("NAMA_PEJABAT").toString());	
					context.put("Alamat1",h1.get("ALAMAT1").toString());	
					context.put("Alamat2",h1.get("ALAMAT2").toString());
					context.put("Alamat3",h1.get("ALAMAT3").toString());
					context.put("Poskod",h1.get("POSKOD").toString());
					context.put("Bandar",h1.get("BANDAR").toString());
					context.put("Negeri",h1.get("NEGERI").toString());
					context.put("No_tel",h1.get("NO_TEL").toString());
				}
				
				}
				
			}
			else
			{
			default_value();
			}
		}
		}
		else
		{			
			//default list 	
			//default value 
			default_value();
		}	
		list_user = list_user("");
		context.put("list_user",list_user);
		setupPage(session,action,list_user);
		return vm;	
	}
	
	private void default_value()throws Exception
	{
		context.put("user_id","");
		context.put("user_login","");
		context.put("user_password","");
		context.put("user_password2","");
		context.put("user_name","");
		context.put("user_email","");
		context.put("id_jawatan","");
		context.put("id_negeri","");
		context.put("id_negeri","");
		context.put("id_pejabat","");
		context.put("jenis_pejabat","");
		context.put("role","");
		context.put("list_pejabat","");			
		list_negeri = list_negeri("");
		context.put("list_negeri",list_negeri);
		list_jawatan = list_jawatan("");
		context.put("list_jawatan",list_jawatan);
		list_role = list_role("userSemakanPerintah");
		context.put("list_role",list_role);
		
	}
	
	Vector list_role = null;
	public Vector list_role(String role)
			throws Exception {
		list_role = new Vector();
		Db db = null;
		list_role.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT NAME,DESCRIPTION,THEME FROM ROLE WHERE NAME = 'userSemakanPerintah' ORDER BY NAME ";
			
			//sql += " WHERE NAME = 'userSemakanPerintah' ";
					
			myLogger.info("SQL ROLE :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	

			while (rs.next()) {
				h = new Hashtable();
				h.put("NAME",
						rs.getString("NAME") == null ? "" : rs
								.getString("NAME"));
				h.put("DESCRIPTION", rs.getString("DESCRIPTION") == null ? ""
						: rs.getString("DESCRIPTION"));			
				h.put("THEME", rs.getString("THEME") == null ? ""
						: rs.getString("THEME"));
				
				list_role.addElement(h);
			}
			
			myLogger.info("list_role :"+list_role);
			return list_role;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector list_negeri = null;
	public Vector list_negeri(String id_negeri)
			throws Exception {
		list_negeri = new Vector();
		Db db = null;
		list_negeri.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_NEGERI,KOD_NEGERI,NAMA_NEGERI FROM TBLRUJNEGERI ";
			if(!id_negeri.equals(""))
			{
			sql += " WHERE ID_NEGERI = '"+id_negeri+"' ";
			}		
			myLogger.info("SQL RUJNEGERI :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI"));			
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				
				list_negeri.addElement(h);
			}
			return list_negeri;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector list_jawatan = null;
	public Vector list_jawatan(String id_jawatan)
			throws Exception {
		list_jawatan = new Vector();
		Db db = null;
		list_jawatan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_JAWATAN,KOD_JAWATAN,UPPER(KETERANGAN) AS NAMA_JAWATAN FROM TBLRUJJAWATAN ";
			if(!id_jawatan.equals(""))
			{
			sql += " WHERE ID_JAWATAN = '"+id_jawatan+"' ORDER BY ID_JAWATAN ASC ";
			}		
			myLogger.info("SQL RUJJAWATAN :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_JAWATAN",
						rs.getString("ID_JAWATAN") == null ? "" : rs
								.getString("ID_JAWATAN"));
				h.put("KOD_JAWATAN", rs.getString("KOD_JAWATAN") == null ? ""
						: rs.getString("KOD_JAWATAN"));			
				h.put("NAMA_JAWATAN", rs.getString("NAMA_JAWATAN") == null ? ""
						: rs.getString("NAMA_JAWATAN"));
				
				list_jawatan.addElement(h);
			}
			return list_jawatan;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector list_pejabat = null;
	public Vector list_pejabat(String id_pejabat,String id_negeri,String jenispejabat)
			throws Exception {
		list_pejabat = new Vector();
		Db db = null;
		list_pejabat.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PEJABAT,UPPER(P.NAMA_PEJABAT) || ' - ' ||  UPPER(P.ALAMAT2) || ', ' ||  UPPER(B.KETERANGAN) AS NAMA_PEJABAT," +
					" UPPER(P.ALAMAT1) AS ALAMAT1,UPPER(P.ALAMAT2) AS ALAMAT2,"+
				" UPPER(P.ALAMAT3) AS ALAMAT3, "+
				" UPPER(P.POSKOD) AS POSKOD,UPPER(B.KETERANGAN) AS BANDAR,UPPER(N.NAMA_NEGERI) AS NEGERI,P.NO_TEL  "+
				" FROM TBLRUJPEJABAT P,TBLRUJNEGERI N,TBLRUJBANDAR B "+
				" WHERE P.ID_JENISPEJABAT = '"+jenispejabat+"' AND P.ID_SEKSYEN = '2' "+
				" AND P.ID_NEGERI = N.ID_NEGERI(+) AND P.ID_BANDAR = B.ID_BANDAR(+)";
			
			
			if(!id_negeri.equals(""))
			{
			sql += " AND P.ID_NEGERI = '"+id_negeri+"' ";
			}
			
			if(!id_pejabat.equals(""))
			{
			sql += " AND P.ID_PEJABAT = '"+id_pejabat+"' ";
			}
			
			
			myLogger.info("SQL RUJPEJABAT :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEJABAT",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT"));			
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? ""
						: rs.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? ""
						: rs.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? ""
						: rs.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? ""
						: rs.getString("POSKOD"));
				h.put("NEGERI", rs.getString("NEGERI") == null ? ""
						: rs.getString("NEGERI"));
				h.put("BANDAR", rs.getString("BANDAR") == null ? ""
						: rs.getString("BANDAR"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? ""
						: rs.getString("NO_TEL"));
				
				list_pejabat.addElement(h);
			}
			return list_pejabat;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public boolean add(String id_penguna,String user_login, String password, String user_name, String user_email, String id_jawatan, String id_negeri, 
			String jenis_pejabat, String id_pejabat, String role, String user_id) throws Exception { 
			Db db = null;
			Connection conn = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				myLogger.info("id_penguna :"+id_penguna);
				if(id_penguna.equals(""))
				{
				
				SQLRenderer r = new SQLRenderer();
				
				
					long id = DB.getNextID("USERS_SEQ");
					r.clear();
					r.add("USER_ID", id);
					r.add("USER_LOGIN", user_login);
					r.add("USER_PASSWORD", PasswordService.encrypt(password));
					r.add("USER_NAME", user_name);
					r.add("USER_ROLE", role);
					r.add("DATE_REGISTERED",r.unquote("SYSDATE"));
					r.add("USER_TYPE", "internal");
					r.add("ID_MASUK", user_id);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					r.add("ID_KEMASKINI", user_id);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql = r.getSQLInsert("USERS");
					stmt.executeUpdate(sql);
					
					//masukkan dlm table user internal
					r.clear();
					r.add("USER_ID", id);
					r.add("ID_JAWATAN", id_jawatan);
					r.add("ID_MASUK", user_id);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					r.add("ID_KEMASKINI", user_id);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql = r.getSQLInsert("USERS_INTERNAL");
					stmt.executeUpdate(sql);			  
				  
					//masukkan dlm table user ARB
					r.clear();
					r.add("ID_MASUK", user_id);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
					r.add("ID_KEMASKINI", user_id);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					r.add("USER_ID", id);
					r.add("FLAG_JENIS_PEJABAT", jenis_pejabat);
					r.add("ID_PEJABAT", id_pejabat);
					r.add("ID_NEGERI", id_negeri);					
					r.add("EMEL", user_email);
					//myLogger.info("SQL Insert user:"+sql);
					sql = r.getSQLInsert("USERS_ARB");
					stmt.executeUpdate(sql);
				  
					//MASUKKAN DLM TABLE USER_ROLE 
					r.clear();
					r.add("USER_ID", user_login);
					r.add("ROLE_ID", role);
					sql = r.getSQLInsert("USER_ROLE");
					stmt.executeUpdate(sql);
					
					conn.commit();
					
				
				
				
					
					
				} 
				else
				{
					SQLRenderer r = new SQLRenderer();					
					
					
						
						r.clear();
						r.update("USER_ID", id_penguna);
						r.add("USER_LOGIN", user_login);						
						r.add("USER_PASSWORD", PasswordService.encrypt(password));
						r.add("USER_NAME", user_name);
						r.add("USER_ROLE", role);
						r.add("DATE_REGISTERED",r.unquote("SYSDATE"));
						r.add("USER_TYPE", "internal");						
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						sql = r.getSQLUpdate("USERS");
						myLogger.info("UPDATE USERS :"+sql);
						stmt.executeUpdate(sql);
						
						r.clear();
						r.update("USER_ID", id_penguna);
						r.add("ID_JAWATAN", id_jawatan);					
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
						sql = r.getSQLUpdate("USERS_INTERNAL");
						myLogger.info("UPDATE USERS INTERNAL :"+sql);
						stmt.executeUpdate(sql);
						
						r.clear();
						r.update("USER_ID", id_penguna);						
						r.add("ID_KEMASKINI", user_id);
						r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));						
						r.add("FLAG_JENIS_PEJABAT", jenis_pejabat);
						r.add("ID_PEJABAT", id_pejabat);
						r.add("ID_NEGERI", id_negeri);					
						r.add("EMEL", user_email);
						sql = r.getSQLUpdate("USERS_ARB");
						myLogger.info("UPDATE USERS ARB :"+sql);
						stmt.executeUpdate(sql);
						
						
						String sql2 = "SELECT COUNT(*) AS TOT FROM USER_ROLE WHERE USER_ID != '"+id_penguna+"' AND ROLE_ID= '"+role+"' ";
						myLogger.info("sql current role :"+sql2);
						ResultSet rs2 = stmt.executeQuery(sql2);	
						Integer tot = 0;
						if(rs2.next()) {
						tot = rs2.getInt("TOT");	
						}	
						if(tot==0)
						{				
							r.clear();
							r.add("USER_ID", user_login);
							r.add("ROLE_ID", role);
							sql = r.getSQLInsert("USER_ROLE");
							stmt.executeUpdate(sql);	
						}
						
						conn.commit();
					}
					
					
				
				
				
				
			} catch(SQLException ex) {
				try {
					conn.rollback();
				} catch(SQLException sqlexception) { 
					
				}
				throw new DbException((new StringBuilder(String.valueOf(ex.getMessage()))).append(": ").append(sql).toString());
			}
			return true;
		} 
	
	Vector list_user = null;
	public Vector list_user(String user_id)
			throws Exception {
		list_user = new Vector();
		Db db = null;
		list_user.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT US.USER_ID,US.USER_LOGIN,US.USER_PASSWORD,US.USER_NAME,US.USER_ROLE,UA.ID_NEGERI,UI.ID_JAWATAN, "+
			" UA.ID_PEJABAT,UA.FLAG_JENIS_PEJABAT,UA.EMEL,PEJ.NAMA_PEJABAT,PEJ.ALAMAT1,B.KETERANGAN AS NAMA_BANDAR, "+
			" (CASE "+
			" WHEN UA.FLAG_JENIS_PEJABAT = '9' THEN 'AMANAH RAYA BERHAD' "+
			" WHEN UA.FLAG_JENIS_PEJABAT = '8' THEN 'MAHKAMAH TINGGI' "+
			" ELSE '' END) AS JENIS_PENGGUNA "+
			" FROM USERS US,USERS_INTERNAL UI,USERS_ARB UA,TBLRUJPEJABAT PEJ,TBLRUJBANDAR B "+
			" WHERE US.USER_ID = UI.USER_ID AND UI.USER_ID = UA.USER_ID AND UA.ID_PEJABAT = PEJ.ID_PEJABAT(+) AND PEJ.ID_BANDAR = B.ID_BANDAR(+)";
			
			boolean setLimit = true;
			
			if(!user_id.equals(""))
			{
			setLimit = false;
			sql += " AND US.USER_ID = '"+user_id+"' ";
			}
			
			if (setLimit) {	
			sql = sql + " AND ROWNUM <= 50 ";
			}
			
			myLogger.info("SQL USER :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			Integer bil = 0;
			while (rs.next()) {
				bil++;
				h = new Hashtable();
				h.put("BIL",bil);
				h.put("USER_ID",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID").toUpperCase());
				h.put("USER_LOGIN",
						rs.getString("USER_LOGIN") == null ? "" : rs
								.getString("USER_LOGIN"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME").toUpperCase());
				h.put("USER_ROLE",
						rs.getString("USER_ROLE") == null ? "" : rs
								.getString("USER_ROLE"));
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI").toUpperCase());
				h.put("ID_PEJABAT",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT").toUpperCase());
				h.put("ID_JAWATAN",
						rs.getString("ID_JAWATAN") == null ? "" : rs
								.getString("ID_JAWATAN").toUpperCase());
				h.put("FLAG_JENIS_PEJABAT",
						rs.getString("FLAG_JENIS_PEJABAT") == null ? "" : rs
								.getString("FLAG_JENIS_PEJABAT").toUpperCase());
				h.put("EMEL",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));				
				h.put("NAMA_PEJABAT",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT").toUpperCase());				
				h.put("ALAMAT1",
						rs.getString("ALAMAT1") == null ? "" : rs
								.getString("ALAMAT1").toUpperCase());				
				h.put("NAMA_BANDAR",
						rs.getString("NAMA_BANDAR") == null ? "" : rs
								.getString("NAMA_BANDAR").toUpperCase());				
				h.put("JENIS_PENGGUNA",
						rs.getString("JENIS_PENGGUNA") == null ? "" : rs
								.getString("JENIS_PENGGUNA").toUpperCase());
				
				
				list_user.addElement(h);
			}
			return list_user;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void hapus(String user_id) throws Exception {
	
		Connection conn = null;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
		    conn = db.getConnection();
		    conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			r.add("USER_ID", user_id);			
			sql = r.getSQLDelete("USERS");
			stmt.executeUpdate(sql);
			r.clear();
			r.add("USER_ID", user_id);			
			sql = r.getSQLDelete("USERS_INTERNAL");
			stmt.executeUpdate(sql);			
			r.clear();
			r.add("USER_ID", user_id);			
			sql = r.getSQLDelete("USERS_ARB");
			stmt.executeUpdate(sql);			
				
			conn.commit();
			
		} catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }	
	}
		
	
}
