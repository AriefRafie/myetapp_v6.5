// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 8/2/2012 12:32:32 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DesktopController.java

package lebah.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.app.HtmlRepositoryModule;
import lebah.app.NewHtmlModule;
import lebah.app.UpdatePageStyleModule;
import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.Labels;
import lebah.db.SQLRenderer;
import lebah.db.UniqueID;
import lebah.module.theme.PageStyleManagerModule;
import lebah.portal.db.AuthenticateUser;
import lebah.portal.db.PrepareUser;
import lebah.portal.db.Role;
import lebah.portal.db.User;
import lebah.portal.db.UserData;
import lebah.portal.db.UserPage;
import lebah.portal.db.UserTrackerLog;
import lebah.portal.element.Tab;
import lebah.portal.handler.DeviceHandler;
import lebah.portal.velocity.VServlet;
import lebah.portal.velocity.VTemplate;
import lebah.util.CSSFileManagerModule;
import lebah.util.Util;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.engine.Properties;
import ekptg.helpers.Utils;

// Referenced classes of package lebah.portal:
//            ErrorMsg, Props, Header, Customize, 
//            Tabber, DisplayContent, Footer

public class DesktopController extends VServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2298203991972249996L;
	static Logger myLog = Logger.getLogger("lebah.portal.DesktopController");
	static int counter;
	private DeviceHandler loginIntercept;

	public DesktopController() {
		loginIntercept = null;
		loginIntercept = null;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			doBody(req, res);
		} catch (Exception e) {
			PrintWriter out = res.getWriter();
			String contextPath = req.getContextPath();
			out.println((new StringBuilder(
					"<link rel=\"stylesheet\" type=\"text/css\" href=\""))
					.append(contextPath).append("/eTapp_Admin.css\" />")
					.toString());
			out.println("<input type=button value=kembali onClick=\"history.go(-1);\" ");
			out.println("<div class=\"error\">");
			out.println(e.getMessage());
			e.printStackTrace();
			out.println("<pre>");
			out.println("</pre>");
			out.println("</div>");
			out.close();
		}
	}	
	
	public 
	synchronized 
	void doBody(HttpServletRequest req,
		HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();		
		//System.out.println("req masuk ::::::::: "+req);
		String pathInfo = req.getPathInfo();
		//System.out.println("pathInfo ::::::::: "+pathInfo);
		pathInfo = pathInfo.substring(1);
		if (pathInfo == null) {
			cleanupVelocity();
			//cleanupVelocity(session, context,req);
			res.sendRedirect("../logout_internal.jsp");
			return;
		}
		
		String action = pathInfo == null ? "" : pathInfo;
		//System.out.println("action ::::::::: "+action);
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		//HttpSession session = req.getSession();
			
		//synchronized(this){
		context = (VelocityContext) session.getAttribute("VELOCITY_CONTEXT");
		engine = (VelocityEngine) session.getAttribute("VELOCITY_ENGINE");
		if (context == null || engine == null) {
			if (context == null)
				//2016/11/15 //myLogger.debug("[DesktopController] Velocity context is Null.");
			if (engine == null)
				//2016/11/15 //myLogger.debug("[DesktopController] Velocity engine is Null.");
				
			initVelocity(getServletConfig());
			session.setAttribute("VELOCITY_CONTEXT", context);
			session.setAttribute("VELOCITY_ENGINE", engine);			
		}
		session.setAttribute("_portal_username", (String) session.getAttribute("_portal_username"));	
		//}
		
		
		/*
		if (context == null) {
			super.initVelocity(getServletConfig());
			session.setAttribute("VELOCITY_CONTEXT", context);
			session.setAttribute("VELOCITY_ENGINE", engine);
		}
		*/
			
		/*
		context = (VelocityContext) session.getAttribute("VELOCITY_CONTEXT");
		engine = (VelocityEngine) session.getAttribute("VELOCITY_ENGINE");		
		if (context == null) {
			//System.out.println("... INITIALIZE VELOCITY");
			super.initVelocity(getServletConfig());
			session.setAttribute("VELOCITY_CONTEXT", context);
			session.setAttribute("VELOCITY_ENGINE", engine);
		}
		*/
		
		if (action.equalsIgnoreCase("logout_internal")) {
			UserTrackerLog.save(req,(String) session.getAttribute("_portal_login"), "lebah.portal.db.UserTrackerLog");
			cleanupVelocity();
			//cleanupVelocity(session, context,req);
			res.sendRedirect("../logout_internal.jsp");
			return;
			
		}
		session.setAttribute("_portal_pathInfo", pathInfo);
		String browser = "";
		String userAgent = req.getHeader("User-Agent");
		context.put("userAgent", userAgent);
		if (userAgent.indexOf("MSIE") > 0)
			browser = "ie";
		else if (userAgent.indexOf("Firefox") > 0)
			browser = "firefox";
		else if (userAgent.indexOf("Netscape") > 0)
			browser = "netscape";
		else if (userAgent.indexOf("Safari") > 0)
			browser = "safari";
		else if (userAgent.indexOf("MIDP") > 0)
			browser = "midp";
		else
			//myLogger.info(userAgent);		
		context.put("browser", browser);
		
		
		String remoteAddr =  req.getRemoteAddr();
		if (req != null) {
            remoteAddr = req.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = req.getRemoteAddr();
            }
        }
        	
		context.put("ip_address", remoteAddr);
		session.setAttribute("ip_address", remoteAddr);
		session.setAttribute("_ekptg_user_type", "internal");
		context.put("relativeDir", "../");
		res.setHeader("Expires", "Tue, 25 Dec 1993 23:59:59 GMT");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-control", "no-cache");
		res.setHeader("Last-Modified", "FRI, JUN 26 2020 23:59:59 GMT");
		//Tambah pada 2017/01/18 oleh Mohamad Rosli
		res.setHeader("X-Frame-Options", "sameorigin");
		res.setHeader("Set-Cookie", "HttpOnly;Secure");
		
		session.setAttribute("flagLogMasuk", "");
		context.put("util", new Util());
		context.put("EkptgUtil", new Utils());
		context.put("label", Labels.getInstance().getTitles());
		context.put("DBProperties", Properties.getInstance().getTitles());
		String securityToken = (String) session.getAttribute("securityToken");
		if (securityToken == null || "".equals(securityToken)) {
			securityToken = UniqueID.getUID();
			session.setAttribute("securityToken", securityToken);
		
		}
		context.put("securityToken", securityToken);
		String prev_token = session.getAttribute("form_token") == null ? ""
			: (String) session.getAttribute("form_token");
		String form_token = req.getParameter("form_token") == null ? "empty"
			: req.getParameter("form_token");
		if (prev_token.equals(form_token)) {
			session.setAttribute("doPost", "true");
			session.setAttribute("isPost", new Boolean(true));
		
		} else if ("empty".equals(form_token)) {
			session.setAttribute("doPost", "false");
			session.setAttribute("isPost", new Boolean(false));
		
		} else {
			session.setAttribute("doPost", "false");
			session.setAttribute("isPost", new Boolean(false));
		
		}
		form_token = Long.toString(System.currentTimeMillis());
		session.setAttribute("form_token", form_token);
		String app_path = getServletContext().getRealPath("/");
		app_path = app_path == null ? "" : app_path.replace('\\', '/');
		if ("".equals(app_path))
			//myLogger.warn("WARNING: app_path is null.");
		session.setAttribute("_portal_app_path", app_path);
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		session.setAttribute("_portal_server",
			serverPort == 80 ? ((Object) (serverName)): ((Object) ((
			new StringBuilder(String.valueOf(serverName))).append(":")
			.append(serverPort).toString())));
		context.put("server", serverPort == 80 ? ((Object) (serverName))
			: ((Object) ((new StringBuilder(String.valueOf(serverName)))
			.append(":").append(serverPort).toString())));
		String reqUrl = req.getRequestURL().toString();
		String queryString = req.getQueryString();
		String portalReqUrl = (new StringBuilder(String.valueOf(reqUrl)))
			.append("?").append(queryString).toString();
		session.setAttribute("_portal_reqUrl", portalReqUrl);
		String uri = req.getRequestURI();
		String s1 = uri.substring(1);
		context.put("appname", s1.substring(0, s1.indexOf("/")));
		session.setAttribute("_portal_appname",s1.substring(0, s1.indexOf("/")));
		context.put("_portal_appname", session.getAttribute("_portal_appname"));
		boolean isLogout = false;
		if (action.equalsIgnoreCase("logout_internal")) {
			cleanupVelocity();
			//cleanupVelocity(session, context,req);
			res.sendRedirect("../logout_internal.jsp");
		
		}
		// module
		String module = req.getParameter("_portal_module") != null ? req.getParameter("_portal_module") : "";
		if (session.getAttribute("_portal_module") != null)
			if ((module == null) || ("".equals(module)))
				module = (String) session.getAttribute("_portal_module");

		// String module = req.getParameter("_portal_module") == null ? "" :
		// req.getParameter("_portal_module");
		// if(session.getAttribute("_portal_module") != null && (module == null
		// || "".equals(module)))
		// module = (String)session.getAttribute("_portal_module");
		// if(session.getAttribute("_portal_module") != null && (module == null
		// || "".equals(module)))
		// module = (String)session.getAttribute("_portal_module");

		if ("anon".equals(session.getAttribute("_portal_role"))
			&& "false".equals(session.getAttribute("_portal_islogin"))) {
			String visitor = req.getParameter("visitor") == null ? session.getAttribute("_portal_visitor") == null ? "anon"
				: (String) session.getAttribute("_portal_visitor") : req.getParameter("visitor");
			if (!"anon".equals(visitor)
				&& "anon".equals(PrepareUser.getRole(visitor))) {
				session.setAttribute("_portal_login", visitor);
			} else {
				visitor = "anon";
				session.setAttribute("_portal_login", "anon");
			
			}
			session.setAttribute("_portal_visitor", visitor);
			if (req.getParameter("visitor") != null && !"login".equals(action))
				action = "";
		}
		session.setAttribute("_portal_action", action);
		session.setAttribute("_portal_module", module);

		context.put("session", session);
		VTemplate content = null;
		String css;
		if (session.getAttribute("_portal_css") == null
				|| session.getAttribute("_portal_css") == "")
			css = "eTapp_Admin.css";
		else
			css = (String) session.getAttribute("_portal_css");
		
		boolean isLoginSuccess = false;
		boolean isAccessDenied = false;
		
		boolean isNotActive = false;
		boolean isPasswordExpired = false;

		// Tambahan Oleh Razman
		//session.setAttribute("afteLogin", "N");
		String getUserName = req.getParameter("username");
		String myroletest = req.getParameter("myrole");
//		myLog.info("-----------------------------------username :" + getUserName);
//		myLog.info("-----------------------------------myrole 0 :" + myroletest);
		if (myroletest == null) {
			myroletest = (String) session.getAttribute("myrole");
		}
		// System.out.println("-----------------------------------myrole 1 :"+ myroletest);
		if (myroletest != null && getCSS(myroletest).size() > 0) {
			Hashtable<String,String> getCSS = getCSS(myroletest);
			// System.out.println("-----------------------------------GET css :"
			// + getCSS.get("theme").toString());
			css = getCSS.get("theme").toString();
			// isAccessDenied = true;
			// css = (String) session.getAttribute("_portal_css");
		}
		// System.out.println("-----------------------------------normal css :"+ (String) session.getAttribute("_portal_css"));
		// System.out.println("-----------------------------------myrole 2 :"+ myroletest);
		// tutup razman
		context.put("cnt_portal_username", "");
		context.put("cnt_afterLogin", "");
		if (action.equalsIgnoreCase("login")
			&& session.getAttribute("_portal_islogin") != "true") {
			//system.out.println("MASUK lOGIN");
			String usrlogin = req.getParameter("username");
			String password = req.getParameter("password");
			String loginType = req.getParameter("loginType");
			//myLogger.debug((new StringBuilder("usrlogin:")).append(usrlogin).toString());
			//synchronized(session){
			try {
				AuthenticateUser auth = new AuthenticateUser(req);
				if (auth.lookup(usrlogin, password, "internal")) {
					//razman check flag after login
					context.put("cnt_afterLogin", "Y");
					session.setAttribute("afterLogin", "Y");
					session.setAttribute("nickname", usrlogin);
					session.setAttribute("_portal_role", auth.getRole());
					context.put("cnt_portal_username", auth.getUserName());
					session.setAttribute("_portal_username", auth.getUserName());
					session.setAttribute("_portal_login", auth.getUserLogin());
					session.setAttribute("_portal_islogin", "true");
					session.setAttribute("_ekptg_user_negeri",
							auth.getUserNegeri() == null ? "" : auth.getUserNegeri());
					session.setAttribute("_ekptg_user_unit",auth.getUserPejabat());
					
					session.setAttribute("_ekptg_user_id", auth.getUserID());
					session.setAttribute("_ekptg_loginType", loginType);
					session.setAttribute("rFormat", "PDF");
					
					try {
						css = UserPage.getCSSByRole((String) session.getAttribute("_portal_role"));
						session.setAttribute("_portal_css", css);
						
					} catch (DbException cssex) {
						myLog.info("Can't get CSS");
					}
					
					isLoginSuccess = true;
					session.setAttribute("flagLogMasuk", "Y");
	
				} else {
					session.setAttribute("_portal_role", "anon");
					session.setAttribute("_portal_username", "Anonymous");
					session.setAttribute("_portal_login", "anon");
					session.setAttribute("_portal_islogin", "false");
					session.setAttribute("_ekptg_loginType", "");
					isAccessDenied = true;
					session.setAttribute("flagLogMasuk", "");
	
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				content = new ErrorMsg(engine, context, req, res);
				((ErrorMsg) content).setError(ex.getMessage());
			
			}
			//}
		} else if (session.getAttribute("_portal_role") == null
			|| "".equals(session.getAttribute("_portal_role"))) {
			session.setAttribute("_portal_role", "anon");
			session.setAttribute("_portal_username", "Anonymous");
			session.setAttribute("_portal_login", "anon");
			session.setAttribute("_portal_islogin", "false");
			session.setAttribute("_portal_css", null);
			action = "";
			cleanupVelocity();
			//cleanupVelocity(session, context,req);
			res.sendRedirect("../logout_internal.jsp");
		
		}

		// Edited by Hazwan for Admin Module
		if (css.equals("eTapp.css") || css == null) {
			css = "eTapp_Admin.css";
		}

		// myLogger.info(session.getAttribute("_portal_css"));
		// session.getAttribute("_portal_css"));
		out.println("<html>");
		out.println("<title>");
		out.println("MyeTaPP");
		out.println("</title>");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />");
		
		out.println("<script type=\"text/javascript\" src=\"../library/fck/fckeditor.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/fck/createFCKEditor.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/dropdown.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/prototype.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/scriptaculous.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/effects.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/controls.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/fixed.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/unittest.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/ajax.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/SpryTabbedPanels.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/CalendarPopup.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/ekptgTools.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/SpryValidationConfirm.js\" ></script>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/js/SpryValidationConfirm.css\" />");
		out.println("<script type=\"text/javascript\" src=\"../library/js/SpryValidationPassword.js\" ></script>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/js/SpryValidationPassword.css\" />");
		out.println("<script type=\"text/javascript\" src=\"../library/js/SpryValidationTextField.js\" ></script>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/js/SpryValidationTextField.css\" />");
		out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/modalbox.js\" ></script>");
		//out.println("<script type=\"text/javascript\" src=\"../library/js/jquery-1.3.2.min.js\" ></script>");
		//new jquery
		//out.println("<script type=\"text/javascript\" src=\"../bootstrap-wysihtml5-master/lib/js/jquery-1.7.2.min.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/jquery-1.7.2.min.js\" ></script>");
		//out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>");
		//out.println("<script type=\"text/javascript\" src=\"../library/js/htmlminifier.js\" ></script>");
		//out.println("<script type=\"text/javascript\" src=\"../library/js/htmlminifier.min.js\" ></script>");
		
		out.println("<script type=\"text/javascript\" src=\"../library/js/jquery.pstrength-min.1.2.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../fusioncharts/Charts/FusionCharts.js\"></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/html2canvas.min.js\" ></script>");
		
		//new editor		
		out.println("<script type=\"text/javascript\" src=\"../bootstrap-wysihtml5-master/lib/js/wysihtml5-0.3.0.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../bootstrap-wysihtml5-master/lib/js/prettify.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../bootstrap-wysihtml5-master/lib/js/bootstrap.min.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.js\" ></script>");
		
		
		
		out.println("<script>var $jquery = jQuery.noConflict();</script>");
		out.println((new StringBuilder(
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/"))
				.append(css).append("\" />").toString());
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/SpryTabbedPanels.css\" />");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/scriptaculous/modalbox.css\" />");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/js/jquery-ui-1.7.2.custom.css\" />");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/js/jquery.timepickr.css\" />");
		out.println("<script type=\"text/javascript\" src=\"../library/js/ui.timepickr.min.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/jquery.timepickr.min.js\" ></script>");
		out.println("<script type=\"text/javascript\" src=\"../library/js/fileuploader.js\" ></script>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/fileuploader.css\" />");
		out.println("<link rel=\"shortcut icon\" href=\"../favicon.ico\" />");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/css/sweetalert.css\" />"); //addby zulfazdli untuk confirmation logout date 19-05-2017
		out.println("<script type=\"text/javascript\" src=\"../library/js/sweetalert.min.js\" ></script>");  //addby zulfazdli untuk confirmation logout date 19-05-2017
		out.println("<body onmousemove=\"reset_interval()\" onclick=\"reset_interval()\" onkeypress=\"reset_interval()\" onscroll=\"reset_interval()\">");
		out.println("<div class=\"apps-container\">");
		out.println("<div class=\"apps-body\">");
		out.println("<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
		if (isLogout) {
			cleanupVelocity();
			//cleanupVelocity(session, context,req);
			res.sendRedirect("../logout_internal.jsp");
			return;
			
		}
		if (isAccessDenied) {
			res.sendRedirect("../accessdenied_internal.jsp");
		} else {
			if (action.equalsIgnoreCase("logout_internal")) {
				cleanupVelocity();
				//cleanupVelocity(session, context,req);
				res.sendRedirect("../logout_internal.jsp");
				return;
			
			}
			if (session.getAttribute("_portal_login") == null) {
				cleanupVelocity();
				//cleanupVelocity(session, context,req);
				res.sendRedirect("../logout_internal.jsp");
				return;
			
			}
			if ("login".equals(action))
				action = "";
			createPortalPage(req, res, out, session, action, module);
		
		}
		
	}

	private Date checkNinsertLastChangePassword(String usrlogin) throws Exception{
		Db db = null;
		String sql1 = "";
		Date date = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql1 = "SELECT USER_ID,LAST_CHANGEPASSWORD FROM USERS WHERE USER_LOGIN = '"+usrlogin+"'";
			//myLogger.info("LAST_CHANGEPASSWORD :" + sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);
			String userId = null;
			while (rs1.next()) {
				date = rs1.getDate("LAST_CHANGEPASSWORD") == null ? null
						: rs1.getDate("LAST_CHANGEPASSWORD");
				userId = rs1.getString("USER_ID") == null ? null
						: rs1.getString("USER_ID");
				
			}
			
			if(date == null){
				Statement stmt1 = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				String sql = "";
				r.add("LAST_CHANGEPASSWORD",r.unquote("sysdate"));
				r.update("USER_ID", userId);
				sql = r.getSQLUpdate("USERS");			      				     
				stmt1.executeUpdate(sql);
				date = new Date();
			      
			}

		}catch(Exception e){
			myLog.info("checkNinsertLastChangePassword : "+e.getMessage());
		}finally {
			if (db != null)
				db.close();
		}
		return date;
		
	}

	private void createPortalPage(HttpServletRequest req,
		HttpServletResponse res, PrintWriter out, HttpSession session,
		String action, String module) throws Exception {
		String islogin = (String) session.getAttribute("_portal_islogin");
		String login = (String) session.getAttribute("_portal_login");
		User user = UserData.getUser(login);
		
		//open language
		String language_type = req.getParameter("language_type") == null ? "": req.getParameter("language_type");
		String currentLanguage = ((String) session.getAttribute("selectedLanguage") == null ? "" : (String) session.getAttribute("selectedLanguage")); 
		ResourceBundle rb_lang = null;
		Enumeration bundleKeys = null;		
		//system.out.println("language_type : "+language_type+" currentLanguage : "+currentLanguage);		
		if(language_type.equals("ENGLISH") || (language_type.equals("") && currentLanguage.equals("ENGLISH"))){
			rb_lang = ResourceBundle.getBundle("eng_lang");
			session.setAttribute("selectedLanguage", "ENGLISH");			
	
		}else{
			rb_lang = ResourceBundle.getBundle("malay_lang");
			session.setAttribute("selectedLanguage", "MALAY");
		
		}
		
		if(rb_lang!=null){
			bundleKeys = rb_lang.getKeys();
			while (bundleKeys.hasMoreElements()) {
			    String key = (String)bundleKeys.nextElement();
			    String value = rb_lang.getString(key);
			    context.put(key,value);			   
			
			}
		}
		//close language
				
		//context.put("secondaryRoles", user.getSecondaryRoles());
		//RAZMAN ADD
		context.put("secondaryRoles",listRole(login));		
		context.put("numRoles",Integer.valueOf(user.getSecondaryRoles().length));
		Role primaryRole = user.getRole();
		context.put("primaryRole", primaryRole);
		
		session.setAttribute("_portal_role", user.getRole().getName());
		String myrole = req.getParameter("myrole");
		if (myrole == null) {
			myrole = (String) session.getAttribute("myrole");
			if (myrole == null)
				session.setAttribute("myrole",session.getAttribute("_portal_role"));
		
		} else {
			session.setAttribute("myrole", myrole);
			if (!"".equals(myrole))
				session.setAttribute("_portal_role", myrole);
			
			action = "";
		
		}
		/**
		 * Fungsi paparan Versi mengikut Modul
		 * Dibuat Oleh : Mohamad Rosli
		 * Dibuat Pada : 19/01/2017
		 * Dikemaskini Oleh : Razman 
		 * Dikemaskini Pada : 24/01/2017
		 */
		ResourceBundle rb = ResourceBundle.getBundle("file");
		String moduleVer ="";
		if(myrole==null){
			moduleVer = rb.getString("ver_"+UserData.getModuleByID(login));
		}else{
			//moduleVer = rb.getString("ver_"+UserData.getModuleByRole(myrole.equals(null)?user.getRole().getName():myrole));
			moduleVer = rb.getString("ver_"+UserData.getModuleByRole(myrole));
		}
		//myLog.info("489:module_="+moduleVer+",role="+userData.getModuleByRole(myrole));
		//session.removeAttribute("_moduleVer");
		session.setAttribute("rbFile", rb);
		session.setAttribute("_moduleVer", moduleVer);
		// Tamat
		context.put("_ekptg_user_id",session.getAttribute("_ekptg_user_negeri"));
		context.put("portal_role", session.getAttribute("myrole"));
		loginIntercept = (DeviceHandler) Props.item.get("LOGIN_INTERCEPT");
		if (loginIntercept != null)
			loginIntercept.process(getServletContext(), req, res);
		
		context.put("myrole",myrole != null ? ((Object) ((String) session.getAttribute("myrole"))) : "");
		out.println("<tr><td>");
		Header cHeader = new Header(engine, context, req, res);
		cHeader.print();
		out.println("</td></tr>");
		//2016/11/15
		if ("customize".equalsIgnoreCase(action) && "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td bgcolor=\"silver\" style=\"font-size:12pt; font-weight:bold\">");
			out.println("Personalization");
			out.println("</td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			Customize cCustomize = new Customize(engine, context, req, res);
			try {
				cCustomize.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");
			//2016/11/15////system.out.println(":::::::::::::::::::2");
		
		} else if ("pagetheme".equalsIgnoreCase(action)
			&& "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table bgcolor=\"silver\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td style=\"font-size:12pt; font-weight:bold\">");
			out.println("Personalization");
			out.println("</td><td align=\"right\"><input type=\"button\" value=\"Back\" onclick=\"document.location.href='customize'\"></td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			UpdatePageStyleModule pageStyle = new UpdatePageStyleModule(engine,context, req, res);

			try {
				pageStyle.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");
		
		} else if ("addHtmlContentModule".equalsIgnoreCase(action)
			&& "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table bgcolor=\"silver\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td style=\"font-size:12pt; font-weight:bold\">");
			out.println("Add HTML Module");
			out.println("</td><td align=\"right\"><input type=\"button\" value=\"Back\" onclick=\"document.location.href='../c/'\"></td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			NewHtmlModule newHtml = new NewHtmlModule(engine, context, req, res);
			try {
				newHtml.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");
		
		} else if ("contentManagerModule".equalsIgnoreCase(action)
			&& "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table bgcolor=\"silver\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td style=\"font-size:12pt; font-weight:bold\">");
			out.println("Content Manager");
			out.println("</td><td align=\"right\"><input type=\"button\"  value=\"Back\" onclick=\"document.location.href='../c/'\"></td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			HtmlRepositoryModule repository = new HtmlRepositoryModule(engine,context, req, res);
			try {
				repository.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");

		} else if ("CSSManagerModule".equalsIgnoreCase(action)
			&& "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table bgcolor=\"silver\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td style=\"font-size:12pt; font-weight:bold\">");
			out.println("CSS File Manager");
			out.println("</td><td align=\"right\"><input type=\"button\" value=\"Back\" onclick=\"document.location.href='pagetheme'\"></td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			CSSFileManagerModule repository = new CSSFileManagerModule(engine,context, req, res);
			try {
				repository.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");
		
		} else if ("CSSRegistry".equalsIgnoreCase(action)
			&& "true".equals(islogin)) {
			out.println("<tr><td>");
			out.println("<table bgcolor=\"silver\" width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\">");
			out.println("<tr><td style=\"font-size:12pt; font-weight:bold\">");
			out.println("CSS File Manager");
			out.println("</td><td align=\"right\"><input type=\"button\"  value=\"Back\" onclick=\"document.location.href='pagetheme'\"></td></tr></table>");
			out.println("</td></tr>");
			out.println("<tr><td>");
			PageStyleManagerModule repository = new PageStyleManagerModule(engine, context, req, res);
			try {
				repository.print();
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");

		} else {
			Tabber cTabber = new Tabber(engine, context, req, res);
			if ("".equals(action)) {
				Tab firstTab = cTabber.getFirstTab();
				if (firstTab != null)
					action = firstTab.getId();
				session.setAttribute("_portal_action", action);
				session.setAttribute("_tab_id", action);
			} else {
				session.setAttribute("_tab_id", action);
			}
			out.println("<tr><td>");
			try {
				if (cTabber != null)
					cTabber.print();
				//else
					//system.out.println("THERE ARE NO TABS DEFINED!!");
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.println("</td></tr>");
			try {
				String usrlogin = (String) session.getAttribute("_portal_login");
				String display_type = UserPage.getDisplayType(usrlogin, action,myrole);
				session.setAttribute("portal_display_type", display_type);

				UserTrackerLog.save(req,(String) session.getAttribute("_portal_login"), module);

				if ("left_navigation".equals(display_type))
					DisplayContent.showNavigationType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("top_navigation".equals(display_type))
					DisplayContent.showTopNavigationType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("top_navigation_with_title".equals(display_type))
					DisplayContent.showTopNavigationTitleType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("two_columns_with_bottom".equals(display_type))
					DisplayContent.showTwoColumnsWithSingleBottom(engine,context, 
						getServletConfig(), req, res, module, out,session);
				else if ("three_columns_with_top".equals(display_type))
					DisplayContent.showThreeColumnsWithSingleTop(engine,context, 
						getServletConfig(), req, res, module, out,session);
				else if ("three_columns_with_bottom".equals(display_type))
					DisplayContent.showThreeColumnsWithSingleBottom(engine,context, 
						getServletConfig(), req, res, module, out,session);
				else if ("narrow_wide".equals(display_type))
					DisplayContent.showNarrowWideType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("wide_narrow".equals(display_type))
					DisplayContent.showWideNarrowType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("two_columns".equals(display_type))
					DisplayContent.showTwoColumnsType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("three_columns".equals(display_type))
					DisplayContent.showThreeColumnsType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("pulldown_menu".equals(display_type))
					DisplayContent.showPullDownMenuType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else if ("windows".equals(display_type))
					DisplayContent.showWindowsType(engine, context,
						getServletConfig(), req, res, module, out, session);
				else
					DisplayContent.showNavigationType(engine, context,
						getServletConfig(), req, res, module, out, session);
				out.println("</div>");
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}

			context.put("myaction",action);
			
		}
		Footer cFooter = new Footer(engine, context, req, res);
		out.println("</table>");
		/*
		String contentName = (new StringBuilder("contentDIV")).append(module.replace('.', '_')).toString();
		String jsAllelement = "";
		jsAllelement += " <script> "+
				 "$jquery('#'+'"+contentName+"'+' input').each(function(key, value) { "+
				 "    console.log(\"x : \"+this.name); "+
				 "}); "+
				 "</script> ";
		out.println("XXXXXXXXX XXXXXXXXXX XXXXXXXXXX "+module+" </div>"+jsAllelement);
		*/		
		
		try {
			cFooter.print();
		} catch (Exception ex) {
			out.println(ex.getMessage());
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	public void cleanupVelocity() {
		if (context != null) {
//			System.out.println("** cleanupVelocity **");
			Object objArray[] = context.getKeys();
			for (int i = 0; i < objArray.length; i++) {
				context.remove(objArray[i]);
				//myLogger.debug((new StringBuilder("removed:")).append(objArray[i]).toString());
//				System.out.println((new StringBuilder("removed:")).append(objArray[i]).toString());
			}
		}
		
	}
	
	/*
	public void cleanupVelocity(HttpSession session, VelocityContext context,HttpServletRequest request) {
    	//HttpSession session = this.request.getSession();
    		
    	//System.out.println("** CR cleanupVelocity **");
    	context = (VelocityContext) session.getAttribute("VELOCITY_CONTEXT");
		if (context != null) {
			//system.out.println("** CR cleanupVelocity **");
			Object objArray[] = context.getKeys();
			for (int i = 0; i < objArray.length; i++) {
				////system.out.println(" name context : "+objArray[i]);
				context.remove(objArray[i]);
				//myLogger.debug((new StringBuilder("removed:")).append(objArray[i]).toString());
				////system.out.println((new StringBuilder("removed:")).append(objArray[i]).toString());
			}
		}
		
		//removeSession(request,session);	
		
		
	}
	*/
    
    public void removeSession(HttpServletRequest request,HttpSession session) {
    	Enumeration e = (Enumeration) (session.getAttributeNames());
        while ( e.hasMoreElements())
        {
        	String name = (String) e.nextElement();
        	session.removeAttribute(name);

        }
    	
    }


	private void showError(String err, HttpServletRequest req,
		HttpServletResponse res) {
		ErrorMsg emsg = new ErrorMsg(engine, context, req, res);
		emsg.setError(err);
		try {
			emsg.print();
		} catch (Exception ex) {
			//system.out.println("ERROR WHILE SHOW ERROR");
		}
		
	}
	// Tambahan oleh Razman
	public Hashtable<String, String> getCSS(String name) throws Exception {
		Db db = null;
		String sql1 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();

			sql1 = "SELECT NAME,DESCRIPTION,THEME FROM ROLE WHERE NAME = '"+ name + "' ";
			//myLog.info("CHECK CSS :" + sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable<String, String> h1 = new Hashtable<String, String>();
			//int tot = 0;
			while (rs1.next()) {
				//tot++;
				String theme = rs1.getString("THEME") == null ? "eTapp_Admin.css": rs1.getString("THEME");
				h1.put("theme", theme);

			}
			return h1;

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	// Tutup oleh Razman
		
	//Tambahan oleh Razman
	public List listRole(String username)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRolei = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql += " SELECT   NAME AS ID, "+
					" REGEXP_REPLACE (REGEXP_REPLACE (THEME, 'eTapp_', ''), "+
					" '.css', "+
					" '' "+
					" ) AS KOD, "+
					"  (DESCRIPTION) AS KETERANGAN, 2 AS LAYER "+
					" FROM ROLE R, USERS U WHERE R.NAME = U.USER_ROLE AND U.USER_LOGIN = '"+username+"' "+
					" AND NAME IS NOT NULL     "+
					" UNION ";
			sql += " SELECT   NAME AS ID, "+
					" REGEXP_REPLACE (REGEXP_REPLACE (THEME, 'eTapp_', ''), "+
					" '.css', "+
					" '' "+
					" ) AS KOD, "+
					"  (DESCRIPTION) AS KETERANGAN, 2 AS LAYER "+
					" FROM ROLE R, USER_ROLE UR WHERE R.NAME = UR.ROLE_ID AND UR.USER_ID = '"+username+"' "+
					" AND NAME IS NOT NULL     "+
					" UNION "+
					" SELECT DISTINCT '' AS ID, "+
					" REGEXP_REPLACE (REGEXP_REPLACE (THEME, 'eTapp_', ''), "+
					" '.css', "+
					" '' "+
					" ) AS KOD, "+
					" '' AS KETERANGAN, 1 AS LAYER "+
					" FROM ROLE R, USER_ROLE UR WHERE R.NAME = UR.ROLE_ID AND UR.USER_ID = '"+username+"' "+
					" AND NAME IS NOT NULL          ";
			
			sql += " UNION "+
					" SELECT DISTINCT '' AS ID, "+
					" REGEXP_REPLACE (REGEXP_REPLACE (THEME, 'eTapp_', ''), "+
					"  '.css', "+
					" '' "+
					" ) AS KOD, "+
					" '' AS KETERANGAN, 1 AS LAYER "+
					" FROM ROLE R, USERS U WHERE R.NAME = U.USER_ROLE AND U.USER_LOGIN = '"+username+"' "+
					" AND NAME IS NOT NULL  ";
			
			sql+= 	" ORDER BY KOD, LAYER "+
					" ";
			//myLog.info(" V3: SQL dropdown role utama  :"+ sql);			
				
			rs = stmt.executeQuery(sql);
			listRolei = Collections.synchronizedList(new ArrayList());
			Map<String, String> h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap<String, String>());
				h.put("name",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("description",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
				listRolei.add(h);
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listRolei;

	}
	
	
	
//	private static boolean getx(int y, int m) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(y, m - 1, 1);
//		long expireMillis = calendar.getTime().getTime();
//		long currentMillis = System.currentTimeMillis();
//		return expireMillis < currentMillis;
//		
//	}


}