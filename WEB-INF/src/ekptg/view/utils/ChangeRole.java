package ekptg.view.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.view.online.PermohonanID;

public class ChangeRole implements IServlet2 {
	
	static Logger myLogger = Logger.getLogger(ekptg.view.utils.ChangeRole.class);
	Hashtable viewPengguna = null;
	PermohonanID mohonId = new PermohonanID();

	public void doService(HttpServletRequest request
	,HttpServletResponse response
	,ServletContext context) throws IOException, ServletException{
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 //String contextPath = request.getContextPath();
		 String role = request.getParameter("SelectedRole");
		 String submit = request.getParameter("command");
//		 myLogger.info("submit : "+submit);
		 String USER_ID_SYSTEM = ((String)session.getAttribute("_ekptg_user_id")==null?"":(String)session.getAttribute("_ekptg_user_id"));		 
		 //String USER_LOGIN = (String)session.getAttribute("_portal_login");
		 String USER_LOGIN = ((String)session.getAttribute("_portal_login")==null?"":(String)session.getAttribute("_portal_login"));	 
		 //String _portal_role = (String)session.getAttribute("_portal_role");
		 String _portal_role = ((String)session.getAttribute("_portal_role")==null?"":(String)session.getAttribute("_portal_role"));		 
		 //String user_type = (String)session.getAttribute("_ekptg_user_type");
		 String user_type = ((String)session.getAttribute("_ekptg_user_type")==null?"":(String)session.getAttribute("_ekptg_user_type"));		 
		 //String afterLogin = (String)session.getAttribute("afterLogin");
		 String afterLogin = ((String)session.getAttribute("afterLogin")==null?"":(String)session.getAttribute("afterLogin"));
		 //system.out.println("::::::::::::::: afterLogin : "+afterLogin+" SelectedRole: "+role+" submit : "+submit+" user_type : "+user_type);
		 		 
		if(submit.equals("doSaveNewPassword")) {
			boolean allowed = false;
			String changeLogin = request.getParameter("changeLogin");
			String changePassword = request.getParameter("changePassword");
			String NOKP1 = request.getParameter("NOKP1");
			String NOKP2 = request.getParameter("NOKP2");
			String NOKP3 = request.getParameter("NOKP3");
			String password_asal = request.getParameter("PASSWORD_ASAL");
			String password_baru = request.getParameter("PASSWORD_NEW");
			String user_id = "";
			String user_id_gen = "";
			String println = "";
			if(changeLogin.equals("Y")){
				String FULLMYID = NOKP1+NOKP2+NOKP3;
				try {
					updateUsername(session,USER_ID_SYSTEM,FULLMYID);
					allowed = true;
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(changePassword.equals("Y")){
				try {
					user_id = checkPasswordLogin(USER_ID_SYSTEM, password_asal);
					user_id_gen = checkPasswordGen(USER_ID_SYSTEM, password_baru);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(user_id.equals("")){
					println = "<font class='blink' color='red'><strong>Sila Pastikan Katalaluan Asal Adalah Tepat!</strong></font><script>document.getElementById('PASSWORD_ASAL').focus();</script>";
				}else if(!user_id_gen.equals("")){
					//maksudnya password baru ada dalam 3 gen
//					myLogger.info(" user_id_gen wujud !!!");
					println = "<font class='blink' color='red'><strong>Katalaluan anda pernah didaftarkan, pastikan katalaluan baharu adalah bukan daripada 3 generasi katalaluan yang pernah dimasukan sebelum ini!</strong></font><script>document.getElementById('PASSWORD_NEW').focus();</script>";
				}else{
					try {
						updatePassword(session,user_id,password_baru);
						//tambah emel reset password baru 
						hantarEmel(session,user_id,password_baru);
						allowed = true;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				//out.println("<font class='blink' color='red'><strong>SALAH</strong></font>");
			}
			
			if(allowed==true){
				println += "" +"<script>refreshPage('"+role+"');</script>";
			}
			out.println(println);
			
		}else if(submit.equals("doCheckMyID")){
			String newLogin = request.getParameter("fullnewmyid");
			try {
				boolean checkDuplicate  = checkDuplicateMyID(newLogin, USER_ID_SYSTEM);
				if(checkDuplicate == true){
				out.println("<font class='blink' color='red'><strong>MyID Telah Wujud!, Sila Hubungi Pihak BPICT!</strong></font>" +
						"<input type='hidden' id='duplicateMYID' name='duplicateMYID' value='Y' >");
				}else{
					out.println("" +
							"<input type='hidden' id='duplicateMYID' name='duplicateMYID' value='N' >");
				}
			
			}catch (Exception e) {
				out.println("");
				e.printStackTrace();
			}
		}
		/*
		else if(submit.equals("logout_internal") || submit.equals("logout"))
		{
			try {
				////system.out.println("context : "+context);
				
				if(submit.equals("logout_internal"))
				{
					cleanupVelocity(session, (VelocityContext) session.getAttribute("VELOCITY_CONTEXT"), request);
					out.println("<script>window.top.location.replace(\"index.jsp\");</script>");
				}
				else if(submit.equals("logout"))
				{
					//window.top.location.replace("index.jsp");
					out.println("<script>window.top.location.replace(\"../logout_audittrail.jsp\");</script>");
				}
				
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				out.println("");
				e.printStackTrace();			
			}
		}*/
		else if(submit.equals("doCheckExpired")){
			try {
				//String LAST_CHANGEPASSWORD = checkPasswordExpiry(USER_ID_SYSTEM,90);	
				Hashtable hariTukarPass = checkPassExpByDay(USER_ID_SYSTEM,90);
				String LAST_CHANGEPASSWORD = (String) hariTukarPass.get("LAST_CHANGEPASSWORD");
				String EXP_DATE = (String) hariTukarPass.get("EXP_DATE");
				int HARI = (int) hariTukarPass.get("HARI");
				int BAKI = (int) hariTukarPass.get("BAKI");
				
				String mesej = "";
				String println = "";
				String style = "<style> .container  table{ display:none;} .navigation_menu  td{ display:none;} .apps-body  div{ display:none;} .page_footer_body  div{display:none;} .apps-body .welcome:first-of-type{display : block;} .apps-body .welcome .table_tukar_password:first-of-type{display : block;}  .apps-body .welcome .table_tukar_password .pstrength-bar:first-of-type{display : block;} </style> ";
				
				String setfield="";
				//myLogger.info("KENA TUKAR LOGIN : "+isInteger(USER_LOGIN));
				//x
				if((USER_LOGIN.length()!=12 || isInteger(USER_LOGIN)==false) && user_type.equals("internal")){					
					setfield += "document.getElementById('changeLogin').value='Y';";
					mesej += "Sila kemaskini username anda daripada ["+USER_LOGIN+"] kepada MyID/No. KP Baru. <br>";
					//mesej += "Katalaluan anda telah tamat tempoh (melebihi 90), tarikh terakhir anda menukar katalaluan adalah pada "+LAST_CHANGEPASSWORD+".<br> ";
				}else{
					setfield += "document.getElementById('changeLogin').value='N';";	
				}
				
				//if(!LAST_CHANGEPASSWORD.equals(""))
				if(HARI>90){
					setfield += "document.getElementById('changePassword').value='Y';";
					mesej += "Katalaluan anda telah tamat tempoh (melebihi 90), tarikh terakhir anda menukar katalaluan adalah pada "+LAST_CHANGEPASSWORD+".<br> ";
					//mesej += "Katalaluan anda telah tamat tempoh (melebihi 90).<br> ";
					
				}else{
					setfield += "document.getElementById('changePassword').value='N'; ";
					
					//expiry reminder & reset afterLogin session.. sbb nak check semasa login ja
					//alert bila tingal lagi sebulan or less
					if(afterLogin.equals("Y") && BAKI <= 30)
					{
						setfield += " alert('Tarikh luput kata laluan anda pada "+EXP_DATE+" (Baki "+BAKI+" hari). Sila tukar kata laluan anda sebelum tarikh tersebut untuk mengelakkan ID anda disekat!'); ";
					}
					
					session.setAttribute("afterLogin", "N");
					
				}
				
				if(!setfield.equals("")){
					println += "<script>"+setfield+"</script>";
				}
				
				if(!mesej.equals("")){
					mesej += "Sila kemaskini maklumat anda untuk menggunakan Sistem MyeTaPP seperti biasa.";
					println += style + "<script>displayPasswordExpiry('"+mesej+"');</script>";
				}
				out.println(println);
			
			} catch (Exception e) {
				out.println("");
				e.printStackTrace();
			}
			
		}else if(submit.equals("doCheckAktif")){
			String kumpulan_role = "";
			String mesej = "";
			Hashtable checkKeaftifanByRole = null;
			try {
				kumpulan_role = getKumpulanROLE(role);
				checkKeaftifanByRole = checkKeaftifanByRole(USER_ID_SYSTEM);
								
				Integer check_user_internal = (Integer)checkKeaftifanByRole.get("CHECK_USERS_INTERNAL");
                Integer check_user_online = (Integer)checkKeaftifanByRole.get("CHECK_USERS_ONLINE");
                Integer check_user_kjp = (Integer)checkKeaftifanByRole.get("CHECK_USERS_KJP");
                Integer check_user_int = (Integer)checkKeaftifanByRole.get("CHECK_USERS_INT");				
				
				if(kumpulan_role.equals("online")){
					if(check_user_online==0){
						mesej = " Status sebagai pengguna modul JKPTG Online adalah tidak aktif! Sila hubungi pihak BPICT. ";
					}
				
				}else if(kumpulan_role.equals("KJP")){
					if(check_user_kjp==0){
						mesej = " Status sebagai pengguna modul KJP Online adalah tidak aktif! Sila hubungi pihak BPICT. ";
					}
				
				}else if(kumpulan_role.equals("Integrasi")){
					if(check_user_int==0){
						mesej = " Status sebagai pengguna modul Integrasi adalah tidak aktif! Sila hubungi pihak BPICT. ";
					}
				
				}else{
					if(check_user_internal==0){
						mesej = " Status sebagai pengguna dalaman MyeTaPP adalah tidak aktif! Sila hubungi pihak BPICT. ";
					}
				}
				
				if(!mesej.equals("")){
					String style = "<style> .container  table{ display:none;} .navigation_menu  td{ display:none;} .apps-body  div{ display:none;} .page_footer_body  div{display:none;} .apps-body .welcome:first-of-type{display : block;} </style> ";
					out.println(style + " <script>checkNotAktif('"+mesej+"');</script>");
				
				}else{
					out.println(" ");
				}

			} catch (Exception e) {
				e.printStackTrace();
				out.println("");
			}
						
		}else if(submit.equals("doChangeRole")){
			if(role.equals("online") 
				|| role.equals("ppt-online-user") 
				|| role.equals("php-online-user") 
				|| role.equals("ppk-online-user")){
				try {
					 //updateUserType(USER_ID_SYSTEM,"online");
					 //String roleOne = getOneInternalRole(USER_LOGIN);
					updateUserMainTypeRole(USER_ID_SYSTEM,"online",role);
		    		deleteUserRole(USER_LOGIN, role);
		    		insertToReplaceUserRole(USER_LOGIN,_portal_role);
					 out.println("" +
						 		"<script> " +
						 		//"alert('SelectedRole : "+SelectedRole+"'); " +
						 				"changeRole('"+role+"');" +
						 				//"checkNotAktif('XXXXXXXX')" +
	
		 						"</script>");
				
				} catch (Exception e) {
					out.println("");
					e.printStackTrace();
				}
	 		
			}else{
				try {
					 //updateUserType(USER_ID_SYSTEM,"internal");
					 updateUserMainTypeRole(USER_ID_SYSTEM,"internal",role);
	    			 deleteUserRole(USER_LOGIN, role);
	    			 insertToReplaceUserRole(USER_LOGIN,_portal_role);
					 out.println("" +
						 		"  <script>   "+
							 //"alert('SelectedRole : "+SelectedRole+"'); " +
						 				"changeRole('"+role+"');" +
						 				//"checkNotAktif('XXXXXXXX')" +
						 						" </script>");
				} catch (Exception e) {
					out.println("");
					e.printStackTrace();
				}
			
			}
		
		}
		 //out.println("<script>changeRole("+SelectedRole+")</script>");		
		
	}
	
	public void updateUserType(String user_id,String type)  throws Exception {
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
				sql = r.getSQLUpdate("USERS");		
				myLogger.info("updateUserType : "+sql);
				stmt.executeUpdate(sql);

				conn.commit();
			} catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
	}
	 
	public void updateUserMainTypeRole(String user_id,String type,String Role) throws Exception {
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
//				myLogger.info("updateUserMainTypeRole : "+sql);
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
//				myLogger.info("deleteUserRole : "+sql);
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
//				myLogger.info("insertToReplaceUserRole : "+sql);
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
					myLogger.info("getOneInternalRole : "+sql);
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

	public String checkPasswordExpiry(String user_id, Integer maxday) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
//		boolean check = false;
		try {
			db = new Db();
			stmt = db.getStatement();
			String LAST_CHANGEPASSWORD = "";
				
			sql = "  SELECT U.USER_ID,TO_CHAR(U.LAST_CHANGEPASSWORD,'DD/MM/YYYY') AS LAST_CHANGEPASSWORD, TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) FROM USERS U "+
				" WHERE TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > "+maxday+" AND  U.USER_ID = '"+user_id+"' ";
				myLogger.info("checkPasswordExpiry : "+sql);
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					LAST_CHANGEPASSWORD = rs.getString("LAST_CHANGEPASSWORD") == null ? "" : rs.getString("LAST_CHANGEPASSWORD");						
				}				
				return LAST_CHANGEPASSWORD;
		
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
	
		}
		
	}
	    
	public Hashtable checkPassExpByDay(String user_id, Integer maxday) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
				stmt = db.getStatement();
				
				sql = "  SELECT U.USER_ID,TO_CHAR(U.LAST_CHANGEPASSWORD,'DD/MM/YYYY') AS LAST_CHANGEPASSWORD, " +
						" ("+maxday+" - NVL(TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)),0)) as BAKI, " +
						" NVL(TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)),0) as HARI, " +
						" TO_CHAR(TRUNC(SYSDATE + ("+maxday+" - NVL(TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)),0))),'DD/MM/YYYY') as EXP_DATE "+
						" FROM USERS U "+
						" WHERE U.USER_ID = '"+user_id+"' ";
//				myLogger.info(" checkPassExpByDay :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				Hashtable h;
				h = new Hashtable();
				
				while (rs.next()) {
					h.put("LAST_CHANGEPASSWORD",rs.getString("LAST_CHANGEPASSWORD") == null ? "" : rs.getString("LAST_CHANGEPASSWORD"));
					h.put("BAKI",rs.getString("BAKI") == null ? 0 : rs.getInt("BAKI"));
					h.put("HARI",rs.getString("HARI") == null ? 0 : rs.getInt("HARI"));
					h.put("EXP_DATE",rs.getString("EXP_DATE") == null ? "" : rs.getString("EXP_DATE"));					
				}
				return h;
		
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
			
		}
		
	}
	    
	public String checkPasswordLogin(String user_id, String password) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
//		boolean check = false;
		try {
			db = new Db();
			stmt = db.getStatement();
			String USER_ID = "";
				
			sql = "  SELECT U.USER_ID FROM USERS U "+
				" WHERE U.USER_ID = '"+user_id+"' AND U.USER_PASSWORD = '"+PasswordService.encrypt(password)+"' ";
//					myLogger.info("checkPasswordLogin : "+sql);
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					USER_ID = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");						
				}				
				return USER_ID;
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
	
		}
		
	}
	    
	public String checkPasswordGen(String user_id, String password) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
//			boolean check = false;
		try {
				db = new Db();
				stmt = db.getStatement();
				String USER_ID = "";
				String encrypass = PasswordService.encrypt(password);
				
				sql = "  SELECT USER_ID FROM USERS "+
					"  WHERE USER_ID = '"+user_id+"'  "+
					"  AND (USER_PASSWORD = '"+encrypass+"' OR  USER_PASSWORD_GEN_2  = '"+encrypass+"' OR USER_PASSWORD_GEN_3 = '"+encrypass+"') ";
//				myLogger.info("checkPasswordGen : "+sql);
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					USER_ID = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");						
				}				
				return USER_ID;
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
			
		}
		
	}
	    
	public String getKumpulanROLE(String ROLE_id) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
		
		try {
				db = new Db();
				stmt = db.getStatement();
				String KUMPULAN = "";
				
				sql = "  SELECT   NAME AS ID, REGEXP_REPLACE (REGEXP_REPLACE (THEME, 'eTapp_', ''),'.css','' ) AS NAMA_KUMPULAN, "+
						" UPPER (DESCRIPTION) AS KETERANGAN FROM ROLE R WHERE NAME = '"+ROLE_id+"' AND NAME IS NOT NULL ";
					//system.out.println("getKumpulanROLE : "+sql);
					rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						KUMPULAN = rs.getString("NAMA_KUMPULAN") == null ? "" : rs.getString("NAMA_KUMPULAN");
					}				
				return KUMPULAN;
		
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		
		}
		
	}
	    
	public boolean checkDuplicateMyID(String username, String USERID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean duplicate = false;
		try {
			db = new Db();
			stmt = db.getStatement();
			int count = 0;
				
			sql = "  SELECT COUNT(*) AS FOUND FROM USERS WHERE USER_LOGIN = '"+username.trim()+"' AND USER_ID != '"+USERID+"' ";
//					myLogger.info("checkDuplicateMyID : "+sql);
			rs = stmt.executeQuery(sql);				
			while (rs.next()) {
				count = rs.getString("FOUND") == null ? 0 : rs.getInt("FOUND");
			}	
					
			if(count>0){
				duplicate = true;
			}			
			return duplicate;
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
			
		}
		
	}
	    
	public Hashtable checkKeaftifanByRole(String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String KUMPULAN = "";
				
			sql = "  SELECT  U.USER_ID, U.USER_ROLE, U.USER_NAME "+
						"  ,(SELECT COUNT(*) FROM USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID AND (UI.FLAG_AKTIF IS NULL OR UI.FLAG_AKTIF = '' OR UI.FLAG_AKTIF = '1')) AS CHECK_USERS_INTERNAL "+
						"  ,(SELECT COUNT(*) FROM USERS_ONLINE UO WHERE U.USER_ID = UO.USER_ID AND (UO.FLAG_AKTIF IS NULL OR UO.FLAG_AKTIF = '' OR UO.FLAG_AKTIF = '1')) AS CHECK_USERS_ONLINE  "+
						"  ,(SELECT COUNT(*) FROM USERS_KEMENTERIAN UK WHERE U.USER_ID = UK.USER_ID AND (UK.FLAG_AKTIF IS NULL OR UK.FLAG_AKTIF = '' OR UK.FLAG_AKTIF = '1')) AS CHECK_USERS_KJP "+
						"  ,(SELECT COUNT(*) FROM USERS_INTEGRASI UINT WHERE U.USER_ID = UINT.USER_ID AND (UINT.FLAG_AKTIF IS NULL OR UINT.FLAG_AKTIF = '' OR UINT.FLAG_AKTIF = '1')) AS CHECK_USERS_INT  "+
						"  FROM USERS U WHERE USER_ID = '"+USER_ID+"' ";
					//system.out.println("checkKeaftifanByRole : "+sql);
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("USER_ROLE",rs.getString("USER_ROLE") == null ? "" : rs.getString("USER_ROLE"));
				h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("CHECK_USERS_INTERNAL",rs.getString("CHECK_USERS_INTERNAL") == null ? "" : rs.getInt("CHECK_USERS_INTERNAL"));
				h.put("CHECK_USERS_ONLINE",rs.getString("CHECK_USERS_ONLINE") == null ? "" : rs.getInt("CHECK_USERS_ONLINE"));
				h.put("CHECK_USERS_KJP",rs.getString("CHECK_USERS_KJP") == null ? "" : rs.getInt("CHECK_USERS_KJP"));
				h.put("CHECK_USERS_INT",rs.getString("CHECK_USERS_INT") == null ? "" : rs.getInt("CHECK_USERS_INT"));
					
			}				
			return h;
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		
		}
		
	}
	    
	public void updatePassword(HttpSession session,String USER_ID,String password_baru) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";	
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
						
			String sql_gen = " UPDATE USERS U SET U.USER_PASSWORD_GEN_3 = U.USER_PASSWORD_GEN_2,  " +
						"U.USER_PASSWORD_GEN_2 = U.USER_PASSWORD  " +
						"WHERE USER_ID = '"+USER_ID+"' ";
//			myLogger.info(" UPDATE PASSWORD GEN : "+sql_gen);
			stmt.executeUpdate(sql_gen);
					
			r.clear();
			r.update("USER_ID",USER_ID);
			r.add("USER_PASSWORD",PasswordService.encrypt(password_baru));
			r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
			sql = r.getSQLUpdate("USERS");
			myLogger.info(" UPDATE PASSWORD : "+sql);
			stmt.executeUpdate(sql);
			conn.commit();
			
		}catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
		
		}catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	    
	public void updateUsername(HttpSession session,String USER_ID,String Username_Baru) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
				
			String user_login_lama = getUSER_LOGINBYID(session, USER_ID,db);
				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
					
			r.clear();
			r.update("USER_ID",USER_ID);
			r.add("USER_LOGIN",Username_Baru);
			//r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
			sql = r.getSQLUpdate("USERS");
//			myLogger.info(" UPDATE USERNAME : "+sql);
			stmt.executeUpdate(sql);
				
			if(checkTableExist(session,"TBLUSERACTIVITYEVENT",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("TBLUSERACTIVITYEVENT");
//				myLogger.info(" UPDATE TBLUSERACTIVITYEVENT : "+sql);
				stmt.executeUpdate(sql);
				//SELECT 
				//T.ID_USERACTIVITYEVENT
				//FROM TBLUSERACTIVITYEVENT T;
				
			}
				
			if(checkTableExist(session,"USER_ROLE",db)>0){
				r.clear();
				r.update("USER_ID",user_login_lama);
				r.add("USER_ID",Username_Baru);
				sql = r.getSQLUpdate("USER_ROLE");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE USER_ROLE : "+sql);
				//SELECT 
				//U.USER_ID, U.ROLE_ID
				//FROM USER_ROLE U;
				
			}
				
			if(checkTableExist(session,"USER_TRACKER",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("USER_TRACKER");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE USER_TRACKER : "+sql);
				/*
				SELECT 
				U.USER_LOGIN, U.LOG_YEAR, U.LOG_MONTH, 
				   U.LOG_DAY, U.MODULE_ID, U.MODULE_CLASS, 
				   U.MODULE_NAME, U.TIME12, U.TIME24, 
				   U.STR_DATE, U.REMOTE_ADD, U.LOG_DATE
				FROM USER_TRACKER U;
				*/
				
			}
				
			if(checkTableExist(session,"TAB_USER",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("TAB_USER");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE TAB_USER : "+sql);
				/*
				SELECT 
				T.TAB_ID, T.TAB_TITLE, T.USER_LOGIN, 
				   T.SEQUENCE, T.DISPLAY_TYPE, T.LOCKED, 
				   T.HIDE
				FROM TAB_USER T;
				*/
				
			}
				
			if(checkTableExist(session,"USER_CSS",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("USER_CSS");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE USER_CSS : "+sql);
				/*
				SELECT 
				U.USER_LOGIN, U.CSS_NAME
				FROM USER_CSS U;
				*/
	
			}
				
			if(checkTableExist(session,"USER_MODULE",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("USER_MODULE");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE USER_MODULE : "+sql);
				
			}
				/*
				 SELECT 
					U.TAB_ID, U.USER_LOGIN, U.MODULE_ID, 
					   U.SEQUENCE, U.MODULE_CUSTOM_TITLE, U.COLUMN_NUMBER
					FROM USER_MODULE U;				 
				 */
				
			if(checkTableExist(session,"USER_MODULE_TEMPLATE",db)>0){
				r.clear();
				r.update("USER_LOGIN",user_login_lama);
				r.add("USER_LOGIN",Username_Baru);
				sql = r.getSQLUpdate("USER_MODULE_TEMPLATE");
				stmt.executeUpdate(sql);
//				myLogger.info(" UPDATE USER_MODULE_TEMPLATE : "+sql);				
				/*
				SELECT 
				U.TAB_ID, U.USER_LOGIN, U.MODULE_ID, 
				   U.SEQUENCE, U.MODULE_CUSTOM_TITLE, U.COLUMN_NUMBER
				FROM USER_MODULE_TEMPLATE U;
				*/
	
			}
			conn.commit();
			session.setAttribute("_portal_login", Username_Baru);
		
		} catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
		
		}catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	    
	public int checkTableExist(HttpSession session, String table_name,Db db) throws Exception {
		//Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//db = new Db();
			stmt = db.getStatement();
			int ada=0;
			sql = "  select count(table_name) as ada from all_tables where table_name='"+table_name+"' ";	
//			myLogger.info(" OT : USER_LOGIN :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);				
			while (rs.next()) {							
				ada = (rs.getString("ada") == null ? 0 : rs.getInt("ada"));
			}			
			return ada;
			
		} finally {
				//if (rs != null)
				//	rs.close();
				//if (stmt != null)
				//	stmt.close();
				//if (db != null)
				//	db.close();
		
		}
		
	}		
	    		
	public String getUSER_LOGINBYID(HttpSession session, String USER_ID, Db db) throws Exception {
		//Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//db = new Db();
			stmt = db.getStatement();
			String USER_LOGIN="";
			sql = " SELECT USER_LOGIN FROM USERS WHERE USER_ID = '"+USER_ID+"' ";	
//			myLogger.info(" OT : USER_LOGIN :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);				
			while (rs.next()) {				
				USER_LOGIN = (rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
			}			
			return USER_LOGIN;
			
		} finally {
				//if (rs != null)
				//	rs.close();
				//if (stmt != null)
				//	stmt.close();
				//if (db != null)
				//	db.close();
		}
	
	}
	    
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try{
			Long.parseLong(s);
	           // s is a valid integer
			isValidInteger = true;
	        
		}catch (NumberFormatException ex){
			// s is not an integer    
		}
		return isValidInteger;
	     
	}
	    
	public void hantarEmel(HttpSession session,String user_id, String password_baru) throws Exception {
		myLogger.info("MASUK FUNCTION EMEL");	
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
			
		viewPengguna = mohonId.viewDataPenggunaMOHON(session, user_id);
		String subject = "";
		String content = "";
		
		subject = " Sistem MyeTaPP - Perubahan maklumat bagi Akaun Pengguna ( ID : "+user_id+" ) ";
		content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
		content+= " Untuk makluman Ybhg. Dato/ Tuan/ Puan/ Cik, Kata Laluan bagi Akaun Pengguna MyeTaPP yang berikut telah ditukar (reset) oleh Pentadbir Sistem . <br>";
		content+= " Akaun tersebut boleh diakses menggunakan maklumat berikut : <br><br>";
		content+= " ID Pengguna : "+user_id+" <br>";
		content+= " Kata Laluan : "+password_baru+" <br><br><br>";
		content+= " <strong>Perhatian : </strong><br>";
		content+= "1. Kata Laluan ini boleh ditukar setelah Log Masuk dilakukan melalui Menu : My Profile <br>";
		content+= "2. Rahsiakan kata laluan anda daripada orang lain. <br><br><br>";
		content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";
		content+= " Sekian, terima kasih.<br><br><br>";
		content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			
		email.SUBJECT = subject;
		email.MESSAGE = content;		
		//GET EMEL USER
		email.RECIEPIENT = (String) viewPengguna.get("EMEL");
		email.sendEmail();		 
		 
	}
	    
	public void cleanupVelocity(HttpSession session, VelocityContext context,HttpServletRequest request) {
	    //HttpSession session = this.request.getSession();    		
	    //system.out.println("** CR cleanupVelocity **");
	    context = (VelocityContext) session.getAttribute("VELOCITY_CONTEXT");
		if (context != null) {
			//system.out.println("** CR cleanupVelocity **");
			Object objArray[] = context.getKeys();
			for (int i = 0; i < objArray.length; i++) {
				//system.out.println(" name context : "+objArray[i]);
				context.remove(objArray[i]);
				//myLogger.debug((new StringBuilder("removed:")).append(objArray[i]).toString());
				////system.out.println((new StringBuilder("removed:")).append(objArray[i]).toString());
			}
		
		}
		removeSession(request,session);	
	
	}
	    
	public void removeSession(HttpServletRequest request,HttpSession session) {
	    	//HttpSession session = request.getSession(false);
	    	////system.out.println("Session : "+session);	  
	    	/*
	    	HttpSession session_check = request.getSession(false);
	    	if (session_check != null) {
	    		//system.out.println("CR Session : "+session_check);
	    		session_check.invalidate();
	    	}
	    	else 
	    	{
	    		//system.out.println("CR Session : Tiada");
	    	}
	    	*/
	    	//session.removeAttribute("attribute name");
	    Enumeration e = (Enumeration) (session.getAttributeNames());
	    while ( e.hasMoreElements()){
	    	String name = (String) e.nextElement();
	        	////system.out.println("CR Session Name : "+name);
	        	/*
	            Object tring;
	            if((tring = e.nextElement())!=null)
	            {
	                out.println(session.getValue((String) String));
	                out.println("<br/>");
	            }
	            */
	        session.removeAttribute(name);
    
	    }
	    	    
	}


}
