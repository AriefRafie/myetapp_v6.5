// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 8/2/2012 12:32:33 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   OnlineController.java

package lebah.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Labels;
import lebah.db.UniqueID;
import lebah.portal.db.PrepareUser;
import lebah.portal.db.Role;
import lebah.portal.db.User;
import lebah.portal.db.UserData;
import lebah.portal.db.UserPage;
import lebah.portal.db.UserTrackerLog;
import lebah.portal.element.Tab;
import lebah.portal.handler.DeviceHandler;
import lebah.portal.velocity.VServlet;
//import lebah.portal.velocity.VTemplate;
import lebah.util.Util;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import ekptg.helpers.Utils;

// Referenced classes of package lebah.portal:
//            PortalControllerModule, Title, Props, Header, 
//            Tabber, DisplayContent, ErrorMsg

public class OnlineController extends VServlet implements Serializable{

	static Logger myLogger = Logger.getLogger(lebah.portal.OnlineController.class);
	static int counter;
  	private DeviceHandler loginIntercept;

    public OnlineController(){
        loginIntercept = null;
    }

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{
        doPost(req, res);
    }

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{
        try{
            doBody(req, res);
        }catch(Exception e){
            PrintWriter out = res.getWriter();
            String contextPath = req.getContextPath();
            out.println((new StringBuilder("<link rel=\"stylesheet\" type=\"text/css\" href=\"")).append(contextPath).append("/default.css\" />").toString());
            out.println("<input type=button value=kembali onClick=\"history.go(-1);\" ");
            out.println("<div class=\"error\">");
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
            out.println("</div>");
            out.close();
        }
        
    }

    public synchronized void doBody(HttpServletRequest req, HttpServletResponse res)
        throws Exception{
//      myLogger.debug("Online...");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        //HttpSession session = req.getSession();
        HttpSession session = req.getSession();
        if(session.getAttribute("_onlinecontext") != null){
            context = (VelocityContext)session.getAttribute("_onlinecontext");
        } else{
            initVelocity(getServletConfig());
            session.setAttribute("_onlinecontext", context);
        }
        String browser = "";
        String userAgent = req.getHeader("User-Agent");
        context.put("userAgent", userAgent);
        if(userAgent.indexOf("MSIE") > 0)
            browser = "ie";
        else if(userAgent.indexOf("Firefox") > 0)
            browser = "firefox";
        else if(userAgent.indexOf("Netscape") > 0)
            browser = "netscape";
        else if(userAgent.indexOf("Safari") > 0)
            browser = "safari";
        else if(userAgent.indexOf("MIDP") > 0)
            browser = "midp";
        else
//            myLogger.info(userAgent);
        context.put("browser", browser);
        session.setAttribute("_ekptg_user_type", "online");       
        
        context.put("relativeDir", "../");
        res.setHeader("Expires", "Tue, 25 Dec 1993 23:59:59 GMT");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("Cache-control", "no-cache");
        res.setHeader("Last-Modified", "FRI, JUN 26 2020 23:59:59 GMT");
        context.put("util", new Util());
        Utils EkptgUtil = new Utils();
        context.put("EkptgUtil", EkptgUtil);
        context.put("label", Labels.getInstance().getTitles());
        String securityToken = (String)session.getAttribute("securityToken");
        if(securityToken == null || "".equals(securityToken)){
            securityToken = UniqueID.getUID();
            session.setAttribute("securityToken", securityToken);
        }
        context.put("securityToken", securityToken);
        if(session.getAttribute("_VELOCITY_INITIALIZED") == null){
            session.setAttribute("_VELOCITY_ENGINE", engine);
            session.setAttribute("_VELOCITY_CONTEXT", context);
            session.setAttribute("_VELOCITY_INITIALIZED", "true");
        }
        String prev_token = session.getAttribute("form_token") == null ? "" : (String)session.getAttribute("form_token");
        String form_token = req.getParameter("form_token") == null ? "empty" : req.getParameter("form_token");
        if(prev_token.equals(form_token)){
            session.setAttribute("doPost", "true");
            session.setAttribute("isPost", new Boolean(true));
        } else if("empty".equals(form_token)){
            session.setAttribute("doPost", "false");
            session.setAttribute("isPost", new Boolean(false));
        } else{
            session.setAttribute("doPost", "false");
            session.setAttribute("isPost", new Boolean(false));
        }
        form_token = Long.toString(System.currentTimeMillis());
        session.setAttribute("form_token", form_token);
        String app_path = getServletContext().getRealPath("/");
        app_path = app_path == null ? "" : app_path.replace('\\', '/');
        //System.out.println("WARNING: app_path is null.");
        if("".equals(app_path))
        	session.setAttribute("_portal_app_path", app_path);
        
        context.put("ip_address", req.getRemoteAddr());
		session.setAttribute("ip_address", req.getRemoteAddr());
        
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        session.setAttribute("_portal_server", serverPort == 80 ? ((Object) (serverName)) : ((Object) ((new StringBuilder(String.valueOf(serverName))).append(":").append(serverPort).toString())));
        context.put("server", serverPort == 80 ? ((Object) (serverName)) : ((Object) ((new StringBuilder(String.valueOf(serverName))).append(":").append(serverPort).toString())));
        String reqUrl = req.getRequestURL().toString();
        String queryString = req.getQueryString();
        String portalReqUrl = (new StringBuilder(String.valueOf(reqUrl))).append("?").append(queryString).toString();
        session.setAttribute("_portal_reqUrl", portalReqUrl);
        String uri = req.getRequestURI();
        String s1 = uri.substring(1);
        context.put("appname", s1.substring(0, s1.indexOf("/")));
        session.setAttribute("_portal_appname", s1.substring(0, s1.indexOf("/")));
        context.put("_portal_appname", session.getAttribute("_portal_appname"));
        String pathInfo = req.getPathInfo();
        pathInfo = pathInfo.substring(1);
        session.setAttribute("_portal_pathInfo", pathInfo);
        String action = pathInfo == null ? "" : pathInfo;
        session.setAttribute("_ekptg_loginType", "online");
        if(session.getAttribute("_portal_role") == null || "".equals(session.getAttribute("_portal_role"))){
            session.setAttribute("_portal_role", "anon");
            session.setAttribute("_portal_username", "Anonymous");
            session.setAttribute("_portal_login", "anon");
            session.setAttribute("_portal_islogin", "false");
            session.setAttribute("_portal_css", null);
            action = "";
            
        }
        String module = req.getParameter("_portal_module") == null ? "" : req.getParameter("_portal_module");
        if(session.getAttribute("_portal_module") != null && (module == null || "".equals(module)))
            module = (String)session.getAttribute("_portal_module");
        
        if("anon".equals(session.getAttribute("_portal_role")) && "false".equals(session.getAttribute("_portal_islogin"))){
            String visitor = req.getParameter("visitor") == null ? session.getAttribute("_portal_visitor") == null ? "anon" : (String)session.getAttribute("_portal_visitor") : req.getParameter("visitor");
            if(!"anon".equals(visitor) && "anon".equals(PrepareUser.getRole(visitor))){
                session.setAttribute("_portal_login", visitor);
            } else{
                visitor = "anon";
                session.setAttribute("_portal_login", "anon");
            }
            session.setAttribute("_portal_visitor", visitor);
            if(req.getParameter("visitor") != null && !"login".equals(action))
                action = "";
        }
        session.setAttribute("_portal_action", action);
        session.setAttribute("_portal_module", module);
        context.put("session", session);
        if("logout".equalsIgnoreCase(action)){
        	res.sendRedirect("../logout_audittrail.jsp"); //addby zulfazdliabuas@gmail.com for logout paparkan rekod audittrail
//            res.sendRedirect("../logout_online.jsp");
        }else if("logout_internal".equalsIgnoreCase(action)){
            res.sendRedirect("../logout_internal.jsp");
        }else synchronized(this){
            createPortalPage(req, res, out, session, action, module, EkptgUtil);
        }
        
    }

    private void createPortalPage(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String action, String module, Utils EkptgUtil)
       throws Exception{
//        String islogin = (String)session.getAttribute("_portal_islogin");
        String login = (String)session.getAttribute("_portal_login");
 
        //open language
  		String language_type = req.getParameter("language_type") == null ? "": req.getParameter("language_type");
  		String currentLanguage = ((String) session.getAttribute("selectedLanguage") == null ? "" : (String) session.getAttribute("selectedLanguage")); 
  		ResourceBundle rb_lang = null;
  		Enumeration bundleKeys = null;		
  		//System.out.println("language_type : "+language_type+" currentLanguage : "+currentLanguage);		
  		if(language_type.equals("ENGLISH") 
  			|| (language_type.equals("") && currentLanguage.equals("ENGLISH"))){
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
  			    //System.out.println("key : "+key+" value : "+value);		
  			    context.put(key,value);			   
  			}
  		}
  		//close language
              		
        if(login == null || "anon".equals(login)){
            String page = req.getParameter("page");
            String path = req.getRealPath((new StringBuilder("/portal/")).append(page).append(".jsp").toString());
            if(!EkptgUtil.isFileExist(path))
                page = null;
            if(page == null)
                page = "";
            context.put("page", page);
            PortalControllerModule Portal = new PortalControllerModule(engine, context, req, res);
            try{
                Portal.print();
            }catch(Exception ex){
                out.println(ex.getMessage());
            }
        } else{
            User user = UserData.getUser(login);
            context.put("secondaryRoles", user.getSecondaryRoles());
            context.put("numRoles", Integer.valueOf(user.getSecondaryRoles().length));
            Role primaryRole = user.getRole();
            context.put("primaryRole", primaryRole);
//            session.setAttribute("_portal_userId", user.getLogin());
            session.setAttribute("_portal_role", user.getRole().getName());
            String myrole = req.getParameter("myrole");
            if(myrole == null){
                myrole = (String)session.getAttribute("myrole");
                if (myrole == null)
    				session.setAttribute("myrole",session.getAttribute("_portal_role"));
            
            } else{
                session.setAttribute("myrole", myrole);
                if(!"".equals(myrole))
                    session.setAttribute("_portal_role", myrole);
                action = "";
            }
                       
            context.put("myrole", myrole != null ? ((Object) ((String)session.getAttribute("myrole"))) : "");
            String css = "online.css";
            session.setAttribute("_portal_css", css);
//            VTemplate content = null;
            out.println("<html>");
            out.println("<title>");
            Title cTitle = new Title(engine, context, req, res);
            cTitle.print();
            out.println("</title>");
            out.println("<script type=\"text/javascript\" src=\"../library/fck/fckeditor.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"../library/fck/createFCKEditor.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"../library/js/dropdown.js\" ></script>");
            out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/prototype.js\" ></script>");
            out.println("<script type=\"text/javascript\" src=\"../library/scriptaculous/scriptaculous.js\" ></script>");
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
            out.println("<script type=\"text/javascript\" src=\"../library/js/jquery-1.3.2.min.js\" ></script>");
            out.println("<script>var $jquery = jQuery.noConflict();</script>");
            out.println((new StringBuilder("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/")).append(css).append("\" />").toString());
            //out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/SpryTabbedPanels.css\" />");
            out.println("<link rel=\"shortcut icon\" href=\"../favicon.ico\" />");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../library/css/sweetalert.css\" />"); //addby zulfazdli untuk confirmation logout date 19-04-2017
            out.println("<script type=\"text/javascript\" src=\"../library/js/sweetalert.min.js\" ></script>");  //addby zulfazdli untuk confirmation logout date 19-04-2017
//            out.println("<body>");
    		out.println("<body onmousemove=\"reset_interval_online()\" onclick=\"reset_interval_online()\" onkeypress=\"reset_interval_online()\" onscroll=\"reset_interval_online()\">"); //addby zulfazdli untuk confirmation logout date 19-04-2017
            out.println("<div class=\"apps-container\">");
            out.println("<div class=\"apps-body\">");
//            out.println("<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
            loginIntercept = (DeviceHandler)Props.item.get("LOGIN_INTERCEPT");
            if(loginIntercept != null)
                loginIntercept.process(getServletContext(), req, res);
            Header cHeader = new Header(engine, context, req, res);
            cHeader.print();
            
            Tabber cTabber = new Tabber(engine, context, req, res);
            if("".equals(action) || "c".equals(action)){
                Tab firstTab = cTabber.getFirstTab();
                if(firstTab != null)
                    action = firstTab.getId();
                
                session.setAttribute("_portal_action", action);
                session.setAttribute("_tab_id", action);
            
            } else{
                session.setAttribute("_tab_id", action);
            }
//            out.println("<tr><td>");
            try{
                if(cTabber != null)
                    cTabber.print();
//                else
//                    System.out.println("THERE ARE NO TABS DEFINED!!");
            }catch(Exception ex){
                out.println(ex.getMessage());
            }
//            out.println("</td></tr>");
            try{
            	out.println("<div class=\"container\">");
                String usrlogin = (String)session.getAttribute("_portal_login");
                String display_type = UserPage.getDisplayType(usrlogin, action, myrole);
//                session.setAttribute("portal_display_type", display_type);
//                DisplayContent.showNavigationType(engine, context, getServletConfig(), req, res, module, out, session);
                      
                UserTrackerLog.save(req, (String)session.getAttribute("_portal_login"), module);
                
                if ( "left_navigation".equals(display_type) )
					DisplayContent.showNavigationType(engine, context, getServletConfig(), req, res, module, out, session);
                else if ( "top_navigation".equals(display_type) )
                    DisplayContent.showTopNavigationType(engine, context, getServletConfig(), req, res, module, out, session);
                else if ( "top_navigation_with_title".equals(display_type) )
                    DisplayContent.showTopNavigationTitleType(engine, context, getServletConfig(), req, res, module, out, session);
                else if ( "two_columns_with_bottom".equals(display_type) )
                    DisplayContent.showTwoColumnsWithSingleBottom(engine, context, getServletConfig(), req, res, module, out, session);
                else if ( "three_columns_with_top".equals(display_type) )
                    DisplayContent.showThreeColumnsWithSingleTop(engine, context, getServletConfig(), req, res, module, out, session);
                else if ( "three_columns_with_bottom".equals(display_type) )
                    DisplayContent.showThreeColumnsWithSingleBottom(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "narrow_wide".equals(display_type) )
					DisplayContent.showNarrowWideType(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "wide_narrow".equals(display_type) )
					DisplayContent.showWideNarrowType(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "two_columns".equals(display_type) )
					DisplayContent.showTwoColumnsType(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "three_columns".equals(display_type) )
					DisplayContent.showThreeColumnsType(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "pulldown_menu".equals(display_type) )
					DisplayContent.showPullDownMenuType(engine, context, getServletConfig(), req, res, module, out, session);
				else if ( "windows".equals(display_type) )
					DisplayContent.showWindowsType(engine, context, getServletConfig(), req, res, module, out, session);				
				else
					DisplayContent.showNavigationType(engine, context, getServletConfig(), req, res, module, out, session);
                out.println("</div>");
            
            }catch(Exception ex){
                out.println(ex.getMessage());
            }finally{
				//long totalMem = Runtime.getRuntime().totalMemory();
				//long freeMem = Runtime.getRuntime().freeMemory();
				//System.gc();
				//icnt++;
				//System.out.println(icnt + ") free memory = " + freeMem + "/" + totalMem);
            }
                       
        Footer cFooter = new Footer(engine, context, req, res);
//        out.println("</table>");
        out.println("</div>");
        try{
            cFooter.print();
        }catch(Exception ex){
            out.println(ex.getMessage());
        }
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        }
        
    }

    private void showError_(String err, HttpServletRequest req, HttpServletResponse res){
        ErrorMsg emsg = new ErrorMsg(engine, context, req, res);
        emsg.setError(err);
        try{
            emsg.print();
        }catch(Exception ex){
            System.out.println("ERROR WHILE SHOW ERROR");
        }
        
    }
    

}