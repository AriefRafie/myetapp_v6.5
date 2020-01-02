/* ************************************************************************
LEBAH PORTAL FRAMEWORK
Copyright (C) 2007  Shamsul Bahrin

* ************************************************************************ */


package lebah.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.portal.db.AuthenticateUser;
import lebah.portal.db.PrepareUser;
import lebah.portal.mobile.MFooter;
import lebah.portal.mobile.MHeader;
import lebah.portal.mobile.MTitle;
import lebah.portal.mobile.MobileData;
import lebah.portal.mobile.UserInfo;
import lebah.portal.velocity.VTemplate;
import lebah.util.Util;

/**
 * 
 * @author Shamsul Bahrin Abd Mutalib
 * @version 1.01
 */
public class OutSourceController  extends lebah.portal.velocity.VMobileServlet {
	private static long icnt = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException    {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        HttpSession session = req.getSession();
        
//		synchronized(this) { 
//			context = (org.apache.velocity.VelocityContext) session.getAttribute("_VELOCITY_CONTEXT");
//			engine = (org.apache.velocity.app.VelocityEngine) session.getAttribute("_VELOCITY_ENGINE");
//			if (context == null) {
//				super.initVelocity(getServletConfig());
//				session.setAttribute("_VELOCITY_CONTEXT", context);
//				session.setAttribute("_VELOCITY_ENGINE", engine);
//				session.setAttribute("_VELOCITY_INITIALIZED", "true");
//			}
//		}
		
        //STORE VELOCITY ENGINE IN THE SESSION OBJECT
        
        
        
//        if ( session.getAttribute("_VELOCITY_INITIALIZED") == null ) {
//            session.setAttribute("_VELOCITY_ENGINE", engine);
//            session.setAttribute("_VELOCITY_CONTEXT", context);
//            session.setAttribute("_VELOCITY_INITIALIZED", "true");
//        }    
//        context.put("util", new Util());
        
        
        
        
        doBody(req, res, session);
	}
	
	public void doBody(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
        PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		context.put("util", new Util());
        String app_path = getServletContext().getRealPath("/");
        app_path = app_path != null ? app_path.replace('\\', '/') : "";
		session.setAttribute("_portal_app_path", app_path);
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		session.setAttribute("_portal_server", serverPort != 80 ? serverName + ":" + serverPort : serverName );
        context.put("server", serverPort != 80 ? serverName + ":" + serverPort : serverName);
		
		String reqUrl = req.getRequestURL().toString();
		String queryString = req.getQueryString();
		String portalReqUrl = reqUrl + "?" + queryString;
		session.setAttribute("_portal_reqUrl", portalReqUrl);
		String uri = req.getRequestURI();
		String s1 = uri.substring(1);
		context.put("appname", s1.substring(0, s1.indexOf("/")));
		session.setAttribute("_portal_appname", s1.substring(0, s1.indexOf("/")));
		//get pathinfo
        String pathInfo = req.getPathInfo();
        pathInfo = pathInfo.substring(1); //get rid of the first '/'
        session.setAttribute("_portal_pathInfo", pathInfo);
        //pathInfo only contains action
		String action = pathInfo != null ? pathInfo : "";

		//Set role to anonymous by default
		if ( session.getAttribute("_portal_role") == null || "".equals((String)session.getAttribute("_portal_role"))) {
			session.setAttribute("_portal_role", "anon");
			session.setAttribute("_portal_username", "Anonymous");
			session.setAttribute("_portal_login", "anon");
			session.setAttribute("_portal_islogin", "false");
			session.setAttribute("_portal_css", null);
			//action = "";
		}

		//module
		String module = req.getParameter("_portal_module") != null ?
					    req.getParameter("_portal_module") : "";
					    
		System.out.println("mobile module = " + module);

	    if ( "".equals(module) && !"login".equals(action)) {
	    	try {
	    		module = MobileData.getCurrentModule(action);
	    	} catch ( Exception e ) {
	    		
	    	}
	    }
		
		if ( "anon".equals((String) session.getAttribute("_portal_role"))
		&& "false".equals((String) session.getAttribute("_portal_islogin"))	)
		{
			String visitor = req.getParameter("visitor") != null ?
							 req.getParameter("visitor") :
							 session.getAttribute("_portal_visitor") != null ?
							 (String) session.getAttribute("_portal_visitor") : "anon";
			//this visitor user login must be of role anon
			if ( !"anon".equals(visitor) && "anon".equals(PrepareUser.getRole(visitor)) ) {
				session.setAttribute("_portal_login", visitor);
			}
			else {
				visitor = "anon";
				session.setAttribute("_portal_login", "anon");
			}
			session.setAttribute("_portal_visitor", visitor);
		}
		//---
		session.setAttribute("_portal_action", action);
		session.setAttribute("_portal_module", module);
		context.put("session", session);

		//To store content template
		VTemplate content = null;

        //**HTML
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\"");
		out.println("\"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\" >");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/mobileStyle.css\" />");
//		out.println("<script type=\"text/javascript\" src=\"../jquery.min.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../extra.script.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/scriptaculous.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/fixed.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/unittest.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/ajax.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/prototype.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../scriptaculous/misc.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../validate_form.js\" ></script>");
//		out.println("<script type=\"text/javascript\" src=\"../fck/fckeditor.js\"></script>");
//		out.println("<script type=\"text/javascript\" src=\"../fck/createFCKEditor.js\"></script>");
//		out.println("<script type=\"text/javascript\" src=\"../CalendarPopup.js\" ></script>");
		
		out.println("<title>");
		//**HTML
        
		//TITLE
		MTitle cTitle = new MTitle(engine, context, req, res);
		try {
			cTitle.print();
		} catch ( Exception ex ) {
			out.println(ex.getMessage());
		}

        //**HTML
		out.println("</title>");
		out.println("<body>");
		//Handle login request
		context.put("isLogin", false);
		if ( action.equalsIgnoreCase("login") ) {
			String usrlogin = req.getParameter("username");
			String password = req.getParameter("password");
			String usertype = req.getParameter("usertype");			
			try {
				AuthenticateUser auth = new AuthenticateUser(req);
				if ( auth.lookup(usrlogin, password, usertype) ) {
					session.setAttribute("_portal_role", auth.getRole());
					session.setAttribute("_portal_username", auth.getUserName());
					session.setAttribute("_portal_login", auth.getUserLogin());
					session.setAttribute("_portal_islogin", "true");
					String userSessionId = MobileData.createUserSession(auth.getUserLogin());
					context.put("userSessionId", userSessionId);
					context.put("isLogin", true);
					context.put("userName", auth.getUserName());
					context.put("user", auth.getUserLogin());					
				} else {
					session.setAttribute("_portal_role", "anon");
					session.setAttribute("_portal_username", "Anonymous");
					session.setAttribute("_portal_login", "anon");
					session.setAttribute("_portal_islogin", "false");
					context.put("isLogin", false);
					context.put("user", "anon");
					context.put("userName", "Guest");					
					res.sendRedirect("../accessdeniedmobile.jsp");
				}
			} catch ( Exception ex ) {
				content = new ErrorMsg(engine, context, req, res);
				((ErrorMsg) content).setError(ex.getMessage());
				try {
					content.print();
				} catch ( Exception e) { System.out.println(e); }
			}
		}
		else {
			context.put("userSessionId", action);
			//get user info from this session
			if ( !"anon".equals(action) && !"".equals(action)) {
				try {
					UserInfo userInfo = MobileData.getUserAction(action, module);
					session.setAttribute("_portal_role", userInfo.getRole());
					session.setAttribute("_portal_username", userInfo.getName());
					session.setAttribute("_portal_login", userInfo.getUserId());
					session.setAttribute("_portal_islogin", "true");	
					context.put("isLogin", true);
					context.put("user", userInfo.getUserId());
					context.put("userName", userInfo.getName());
				} catch ( Exception e ) {
					session.setAttribute("_portal_role", "anon");
					session.setAttribute("_portal_username", "Anonymous");
					session.setAttribute("_portal_login", "anon");
					session.setAttribute("_portal_islogin", "false");
					context.put("isLogin", false);
					context.put("user", "anon");
					context.put("userName", "Guest");					
				}
			}
			else {
				session.setAttribute("_portal_role", "anon");
				session.setAttribute("_portal_username", "Anonymous");
				session.setAttribute("_portal_login", "anon");
				session.setAttribute("_portal_islogin", "false");
				context.put("isLogin", false);
				context.put("user", "anon");
				context.put("userName", "Guest");					
			}
		}
		
		MHeader cHeader = new MHeader(engine, context, req, res);
		try {
			cHeader.print();
		} catch ( Exception ex ) {
			out.println(ex.getMessage());
		}
		//out.println("<p>");
		boolean isMainMenu = false;
		
		System.out.println("module = " + module);
		
		try {
			if ( !"".equals(module) && !"main_menu".equals(module)) {
				MDisplayContent.createPageById(engine, context, getServletConfig(), req, res, module, out, session);
			}
			else {
				System.out.println("### MOBILE MAIN MENU ###");
				MDisplayContent.createPageByClassName("lebah.portal.mobile.MainMenuModule", getServletContext(), getServletConfig(), engine, context, session, req, res);
				isMainMenu = true;
			}
		} catch ( Exception ex ) {
			out.println(ex.getMessage());
		}
//		out.println("</p>");
		if ( !isMainMenu ) {
			MFooter footer = new MFooter(engine, context, req, res);
			try {
				footer.print();
			} catch ( Exception ex ) {
				out.println(ex.getMessage());
			}
		}
		
		out.println("</body>");
		out.println("</html>");
        //**HTML
	}

	private void showError(String err, HttpServletRequest req, HttpServletResponse res) {
		ErrorMsg emsg = new ErrorMsg(engine, context, req, res);
		emsg.setError(err);
		try {
			emsg.print();
		} catch ( Exception ex ) {
			//error while displaying error!!
			System.out.println("ERROR WHILE SHOW ERROR");
		}
	}
}