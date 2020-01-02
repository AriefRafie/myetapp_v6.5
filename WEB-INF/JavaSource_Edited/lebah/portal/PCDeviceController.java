package lebah.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.portal.db.CustomClass;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

// Referenced classes of package lebah.portal:
//            ClassLoadManager, ErrorMsg, PortletInfo, MerakContext, 
//            MerakConfig, MerakResponse, MerakRequest, SecurityTokenDenied

public class PCDeviceController
{

    static Logger myLogger = Logger.getLogger(PCDeviceController.class);

    public PCDeviceController()
    {
    }

    public static void doService(ServletContext servletContext, ServletConfig servletConfig, VelocityEngine engine, VelocityContext context, HttpSession session, HttpServletRequest req, HttpServletResponse res)
        throws IOException
    {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        boolean localAccess = false;
        if("127.0.0.1".equals(req.getRemoteAddr()))
        {
            localAccess = true;
        }
        String app_path = servletContext.getRealPath("/");
        app_path = app_path.replace('\\', '/');
        session.setAttribute("_portal_app_path", app_path);
        String _portal_login = (String)session.getAttribute("_portal_login");
        String myrole = (String)session.getAttribute("myrole");
        if(_portal_login == null || "".equals(_portal_login))
        {
            session.setAttribute("_portal_login", "none");
        }
        if(myrole == null || "".equals(myrole))
        {
            session.setAttribute("myrole", "none");
        }
        
        String prev_token = session.getAttribute("form_token") != null ? (String)session.getAttribute("form_token") : "";
        String form_token = req.getParameter("form_token") != null ? req.getParameter("form_token") : "empty";
        if(prev_token.equals(form_token))
        {
            session.setAttribute("doPost", "true");
            session.setAttribute("isPost", new Boolean(true));
        } else
        if("empty".equals(form_token))
        {
            session.setAttribute("doPost", "false");
            session.setAttribute("isPost", new Boolean(false));
        } else
        {
            session.setAttribute("doPost", "false");
            session.setAttribute("isPost", new Boolean(false));
        }
        form_token = Long.toString(System.currentTimeMillis());
        session.setAttribute("form_token", form_token);
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        session.setAttribute("_portal_server", serverPort != 80 ? ((Object) ((new StringBuilder(String.valueOf(serverName))).append(":").append(serverPort).toString())) : ((Object) (serverName)));
        context.put("server", serverPort != 80 ? ((Object) ((new StringBuilder(String.valueOf(serverName))).append(":").append(serverPort).toString())) : ((Object) (serverName)));
        String uri = req.getRequestURI();
        String s1 = uri.substring(1);
        context.put("appname", s1.substring(0, s1.indexOf("/")));
        session.setAttribute("_portal_appname", s1.substring(0, s1.indexOf("/")));
        String pathInfo = req.getPathInfo();
        String queryString = req.getQueryString();
        context.put("queryString", queryString);
        pathInfo = pathInfo.substring(1);
        int slash = pathInfo.indexOf("/");
        boolean allowed = true;
        
        String jsAllelement = "";
        
        if(slash == -1 && localAccess)
        {
            allowed = true;
        } else
        if(slash == -1 && !localAccess)
        {
            securityTokenDenied(engine, context, req, res);
            allowed = false;
        } else
        if(localAccess)
        {
            allowed = true;
        }
        boolean hasToken = true;
        if(slash > -1)
        {
            String securityTokenURI = pathInfo.substring(0, slash);
            String securityToken = (String)session.getAttribute("securityToken");
            if(!localAccess && !securityTokenURI.equals(securityToken))
            {
                securityTokenDenied(engine, context, req, res);
                allowed = false;
                myLogger.info("SECURITY TOKEN DENIED... access was done thru x controller");
            }
            context.put("securityToken", securityToken);
        } else
        {
            hasToken = false;
        }
        String className = "";
        
        if(allowed)
        {
            pathInfo = pathInfo.substring(pathInfo.indexOf("/") + 1);
            String module = pathInfo != null ? pathInfo : "";
            session.setAttribute("_portal_action", module);
            session.setAttribute("_portal_module", module);
            context.put("session", session);
            String ddir = "../";
            if(hasToken)
            {
                ddir = "../../";
            }
           
            context.put("relativeDir", ddir);
            out.println("<html>");
            out.println("<title>eTapp Controller</title>");
            out.println((new StringBuilder("<link href=\"")).append(ddir).append("styles.css\" rel=\"stylesheet\" type=\"text/css\">").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/fck/fckeditor.js\"></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/fck/createFCKEditor.js\"></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/prototype.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/scriptaculous.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/fixed.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/dragdrop.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/unittest.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/scriptaculous/ajax.js\" ></script>").toString());
            out.println((new StringBuilder("<script type=\"text/javascript\" src=\"")).append(ddir).append("library/js/CalendarPopup.js\" ></script>").toString());
            String contentName = (new StringBuilder("contentDIV")).append(module.replace('.', '_')).toString();
            out.println("<body leftmargin=\"0\" rightmargin=\"0\" topmargin=\"0\"><div id=\""+contentName+"\">");
            jsAllelement += " <script> "+
   				 "$jquery('#'+'"+contentName+"'+' input').each(function(key, value) { "+
   				 //"  console.log(\"input x : \"+this.name); " +
   				 "  tukarNamaButtonCetak(this); "+
   				 "}); "+
   				 "</script> ";
            if(!"".equals(module))
            {
                try
                {
                    Object content = null;
                    try
                    {
                        String role = (String)session.getAttribute("myrole");
                        if(!localAccess)
                        {
                            myLogger.info((new StringBuilder(String.valueOf(module))).append(", ").append(role).toString());
                            className = CustomClass.getName(module, role);
                            content = ClassLoadManager.load(className, module, req.getRequestedSessionId());
                        } else
                        {
                            className = module;
                            content = ClassLoadManager.load(className, module, "");
                        }
                        if(content == null)
                        {
                            content = new ErrorMsg(engine, context, req, res);
                            ((ErrorMsg)content).setError((new StringBuilder("No privillege for ")).append(module).append(" on role ").append(role).toString());
                            ((VTemplate)content).print(session);
                        }
                        if(content instanceof GenericPortlet)
                        {
                            PortletInfo portletInfo = new PortletInfo();
                            portletInfo.id = "test_id";
                            portletInfo.title = "Test Title";
                            Hashtable portletState = getPortletState(servletConfig, req, res, out, portletInfo);
                            RenderRequest renderRequest = (RenderRequest)portletState.get("renderRequest");
                            RenderResponse renderResponse = (RenderResponse)portletState.get("renderResponse");
                            PortletConfig config = (PortletConfig)portletState.get("config");
                            GenericPortlet portlet = (GenericPortlet)content;
                            portlet.init(config);
                            String reqMethod = req.getMethod();
                            myLogger.info((new StringBuilder("Portlet request method is = ")).append(reqMethod).toString());
                            portlet.render(renderRequest, renderResponse);
                        }
                        ((VTemplate)content).setEnvironment(engine, context, req, res);
                        ((VTemplate)content).setServletContext(servletConfig.getServletContext());
                        ((VTemplate)content).setServletConfig(servletConfig);
                        ((VTemplate)content).setId(module);
                        try
                        {
                            if(content == null)
                            {
                                ((VTemplate)content).setShowVM(true);
                            }
                            ((VTemplate)content).print(session);
                        }
                        catch(Exception ex)
                        {
                            out.println(ex.getMessage());
                        }
                    }
                    catch(ClassNotFoundException cnfex)
                    {
                        content = new ErrorMsg(engine, context, req, res);
                        ((ErrorMsg)content).setError((new StringBuilder("ClassNotFoundException : ")).append(cnfex.getMessage()).toString());
                        ((VTemplate)content).print(session);
                    }
                    catch(InstantiationException iex)
                    {
                        content = new ErrorMsg(engine, context, req, res);
                        ((ErrorMsg)content).setError((new StringBuilder("InstantiationException : ")).append(iex.getMessage()).toString());
                        ((VTemplate)content).print(session);
                    }
                    catch(IllegalAccessException illex)
                    {
                        content = new ErrorMsg(engine, context, req, res);
                        ((ErrorMsg)content).setError((new StringBuilder("IllegalAccessException : ")).append(illex.getMessage()).toString());
                        ((VTemplate)content).print(session);
                    }
                    catch(Exception ex)
                    {
                        content = new ErrorMsg(engine, context, req, res);
                        ((ErrorMsg)content).setError((new StringBuilder("Other Exception during class initiation : ")).append(ex.getMessage()).toString());
                        ((VTemplate)content).print(session);
                        ex.printStackTrace();
                    }
                }
                catch(Exception ex)
                {
                    myLogger.info(ex.getMessage());
                }
            }
        } else
        {
            myLogger.info((new StringBuilder("UNAUTHORIZED ACCESS... for module ")).append(className).toString());
        }
       
        out.println("</div>"+jsAllelement+"</body>");
        out.println("</html>");
    }

    private static Hashtable getPortletState(ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, PrintWriter out, PortletInfo portletInfo)
        throws Exception
    {
        Hashtable h = new Hashtable();
        MerakContext context = new MerakContext();
        context.httpServletRequest = req;
        MerakConfig config = new MerakConfig();
        config.portletInfo = portletInfo;
        config.portletContext = context;
        MerakResponse renderResponse = new MerakResponse();
        MerakRequest renderRequest = new MerakRequest();
        renderRequest.windowState = WindowState.NORMAL;
        renderRequest.portletMode = PortletMode.VIEW;
        renderRequest.contextPath = svtCfg.getServletContext().getRealPath("/");
        renderResponse.printWriter = out;
        renderRequest.httpServletRequest = req;
        renderResponse.httpServletResponse = res;
        h.put("renderRequest", renderRequest);
        h.put("renderResponse", renderResponse);
        h.put("config", config);
        return h;
    }

    private static void securityTokenDenied(VelocityEngine engine, VelocityContext context, HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            VTemplate content = new SecurityTokenDenied(engine, context, req, res);
            content.print();
        }
        catch(Exception e)
        {
            myLogger.info(e.getMessage());
        }
    }

}
