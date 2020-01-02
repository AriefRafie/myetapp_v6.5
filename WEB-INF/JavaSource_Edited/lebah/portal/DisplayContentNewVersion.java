package lebah.portal;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.DbException;
import lebah.portal.db.CustomClass;
import lebah.portal.db.UserPage;
import lebah.portal.db.UserTrackerLog;
import lebah.portal.element.Module2;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

// Referenced classes of package lebah.portal:
//            Moduler, PortletInfo, ModuleBarDragabble, ModuleDragabbleDiv, 
//            ModuleTitleDragabble, ModuleIcons, ModuleTitle, TopModuler, 
//            MerakPortlet, MerakContext, MerakConfig, MerakResponse, 
//            MerakRequest, ClassLoadManager, HtmlContainer, XMLContainer, 
//            Attributable, ErrorMsg

public class DisplayContentNewVersion
{

    public DisplayContentNewVersion()
    {
    }

    public static void showWindowsType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td align=\"center\">");
        out.println("<table class=\"body\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"1" +
"00%\">"
);
        out.println("<tr><td valign=\"top\">");
        context.put("modules", cModuler.getModuleList());
        ModuleBarDragabble moduleBar = new ModuleBarDragabble(engine, context, req, res);
        try
        {
            moduleBar.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td></tr>");
        out.println("<tr><td align=\"center\">");
        Vector vmodules = cModuler.getModuleList();
        for(int i = 0; i < vmodules.size(); i++)
        {
            Module2 currentModule = (Module2)vmodules.elementAt(i);
            if(currentModule != null)
            {
                module = currentModule.getId();
                moduleTitle = currentModule.getCustomTitle();
                moduleRealTitle = currentModule.getTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
            } else
            {
                res.sendRedirect("");
            }
            Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            String portalId = currentModule.getId();
            context.put("modules", vmodules);
            context.put("portletId", portalId);
            context.put("moduleTitle", moduleTitle);
            ModuleDragabbleDiv div = new ModuleDragabbleDiv(engine, context, req, res);
            try
            {
                div.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
            out.println((new StringBuilder("<script>new Draggable('")).append(portalId).append("');</script>").toString());
            out.println("<tr><td>");
            ModuleTitleDragabble cModuleTitle = new ModuleTitleDragabble(engine, context, req, res);
            try
            {
                cModuleTitle.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
            out.println("<tr><td>");
            try
            {
                printContent(content, svtCfg, req, res, out, portletInfo);
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
            out.println("</table>");
            out.println("</div>");
        }

        out.println("</td></tr>");
        out.println("<tr><td>");
        ModuleIcons icons = new ModuleIcons(engine, context, req, res);
        try
        {
            icons.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td></tr>");
        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showTwoColumnsWithSingleBottom(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        PortletInfo portletInfo = new PortletInfo();
        Module2 module1 = cModuler.getFirstModule();
        cModuler.removeModule(module1);
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\" width=\"50%\">");
        for(int colnum = 0; colnum < 2; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td valign=\"top\" width=\"60%\">");
            }
            if(colnum == 2)
            {
                out.println("</td><td valign=\"top\" width=\"20%\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                doPrintModule(currentModule, portletInfo, engine, context, svtCfg, req, res, module, out, session);
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\">");
        doPrintModule(module1, portletInfo, engine, context, svtCfg, req, res, module, out, session);
        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showThreeColumnsWithSingleBottom(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        PortletInfo portletInfo = new PortletInfo();
        Module2 module1 = cModuler.getFirstModule();
        cModuler.removeModule(module1);
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\" width=\"20%\">");
        for(int colnum = 0; colnum < 3; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td valign=\"top\" width=\"60%\">");
            }
            if(colnum == 2)
            {
                out.println("</td><td valign=\"top\" width=\"20%\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                doPrintModule(currentModule, portletInfo, engine, context, svtCfg, req, res, module, out, session);
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\">");
        doPrintModule(module1, portletInfo, engine, context, svtCfg, req, res, module, out, session);
        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showThreeColumnsWithSingleTop(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\">");
        Module2 module1 = cModuler.getFirstModule();
        doPrintModule(module1, portletInfo, engine, context, svtCfg, req, res, module, out, session);
        out.println("</td></tr></table>");
        out.println("</td></tr>");
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\" width=\"20%\">");
        cModuler.removeModule(module1);
        for(int colnum = 0; colnum < 3; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td valign=\"top\" width=\"60%\">");
            }
            if(colnum == 2)
            {
                out.println("</td><td valign=\"top\" width=\"20%\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                doPrintModule(currentModule, portletInfo, engine, context, svtCfg, req, res, module, out, session);
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showNavigationType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        PortletInfo portletInfo;
        Object content;
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        portletInfo = new PortletInfo();
        content = null;
        Module2 currentModule = cModuler.getModuleById(module);
        if(currentModule != null)
        {
        	
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
            session.setAttribute("_dc_portletInfo", portletInfo);
            content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            session.setAttribute("_dc_content", content);
        } else
        {
            Module2 firstModule = cModuler.getFirstModule();
            System.out.println("firstModule : "+firstModule);
            if(firstModule != null)
            {
                module = firstModule.getId();
                moduleTitle = firstModule.getCustomTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
                System.out.println("portletInfo.id : "+portletInfo.id);
                session.setAttribute("_dc_portletInfo", portletInfo);
                content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                session.setAttribute("_dc_content", content);
            }
            if(firstModule == null)
            {
                moduleTitle = "Modules has not been setup!";
            }
            session.setAttribute("_portal_module", module);
        }
        out.println("<tr><td>");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        out.println("<tr><td class=\"navigation_menu\" align=\"center\" valign=\"top\" width=\"180\" " +
"nowrap>"
);
        try
        {
            cModuler.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td><td valign=\"top\">");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        if(!"".equals(moduleRealTitle))
        {
            out.println("<tr><td>");
            context.put("moduleTitle", moduleTitle);
            ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
            try
            {
                cModuleTitle.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
        }
        out.println("<tr class=\"module_content\"><td>");
        try
        {
            //printContent(content, svtCfg, req, res, out, portletInfo);
            
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        content = null;
       
        /*
        razman keluarkan dlu
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        */
        return;
    }

    public static void showETaPPContent(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        PortletInfo portletInfo;
        Object content;
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        portletInfo = new PortletInfo();
        content = null;
        Module2 currentModule = cModuler.getModuleById(module);
        if(currentModule != null)
        {
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
            content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
        } else
        {
            Module2 firstModule = cModuler.getFirstModule();
            if(firstModule != null)
            {
                module = firstModule.getId();
                moduleTitle = firstModule.getCustomTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
                content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            }
            if(firstModule == null)
            {
                moduleTitle = "Modules has not been setup!";
            }
            session.setAttribute("_portal_module", module);
        }
        out.println("<tr><td>");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        out.println("<tr><td class=\"navigation_menu\" align=\"center\" valign=\"top\" width=\"180\" " +
"nowrap>"
);
        try
        {
            cModuler.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td><td valign=\"top\">");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        if(!"".equals(moduleRealTitle))
        {
            out.println("<tr><td>");
            context.put("moduleTitle", moduleTitle);
            ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
            try
            {
                cModuleTitle.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
        }
        out.println("<tr class=\"module_content\"><td>");
        try
        {
            printContent(content, svtCfg, req, res, out, portletInfo);
            
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        content = null;
        
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        return;
    }

    public static void showPullDownMenuType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        PortletInfo portletInfo;
        Object content;
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        portletInfo = new PortletInfo();
        content = null;
        Module2 currentModule = cModuler.getModuleById(module);
        if(currentModule != null)
        {
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
            session.setAttribute("_dc_portletInfo", portletInfo);
            content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            session.setAttribute("_dc_content", content);
        } else
        {
            Module2 firstModule = cModuler.getFirstModule();
            if(firstModule != null)
            {
                module = firstModule.getId();
                moduleTitle = firstModule.getCustomTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
                System.out.println("portletInfo.id : "+portletInfo.id);
                session.setAttribute("_dc_portletInfo", portletInfo);
                content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                session.setAttribute("_dc_content", content);
            }
            if(firstModule == null)
            {
                moduleTitle = "Modules has not been setup!";
            }
            session.setAttribute("_portal_module", module);
        }
        out.println("<tr><td>");
        out.println("<table align=\"center\" width=\"95%\" cellpadding=\"0\" cellspacing=\"0\" border" +
"=\"0\">"
);
        out.println("<tr><td valign=\"top\">");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        if(!"".equals(moduleRealTitle))
        {
            out.println("<tr><td>");
            context.put("moduleTitle", moduleTitle);
            ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
            try
            {
                cModuleTitle.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
        }
        out.println("<tr><td>");
        try
        {
            //printContent(content, svtCfg, req, res, out, portletInfo);
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        content = null;
        
        /*
        razman keluarkan dlu
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        */
        return;
    }

    public static void showTopNavigationType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        PortletInfo portletInfo;
        Object content;
        TopModuler cModuler = new TopModuler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        portletInfo = new PortletInfo();
        content = null;
        Module2 currentModule = cModuler.getModuleById(module);
        if(currentModule != null)
        {
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
            content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
        } else
        {
            Module2 firstModule = cModuler.getFirstModule();
            if(firstModule != null)
            {
                module = firstModule.getId();
                moduleTitle = firstModule.getCustomTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
                content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            }
            if(firstModule == null)
            {
                moduleTitle = "Modules has not been setup!";
            }
            session.setAttribute("_portal_module", module);
        }
        out.println("<td>");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><br>");
        out.println("<tr><td class=\"navigation_menu\" align=\"center\" valign=\"top\" nowrap>");
        try
        {
            cModuler.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td></tr><tr><td valign=\"top\">");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        out.println("<tr><td>");
        try
        {
            printContent(content, svtCfg, req, res, out, portletInfo);
            
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        content = null;
       
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td>");
        return;
    }

    public static void showTopNavigationTitleType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        PortletInfo portletInfo;
        Object content;
        String tab_id = (String)session.getAttribute("_tab_id");
        String usrlogin = (String)session.getAttribute("_portal_login");
        String tabTitle = UserPage.getTabTitle(usrlogin, tab_id);
        TopModuler cModuler = new TopModuler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        portletInfo = new PortletInfo();
        content = null;
        Module2 currentModule = cModuler.getModuleById(module);
        if(currentModule != null)
        {
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
            content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
        } else
        {
            Module2 firstModule = cModuler.getFirstModule();
            if(firstModule != null)
            {
                module = firstModule.getId();
                moduleTitle = firstModule.getCustomTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
                content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            }
            if(firstModule == null)
            {
                moduleTitle = "Modules has not been setup!";
            }
            session.setAttribute("_portal_module", module);
        }
        out.println("<td>");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        out.println("<tr><td class=\"tab_title\">&nbsp;");
        out.println(tabTitle);
        out.println("</td></tr>");
        out.println("<tr><td class=\"navigation_menu\" align=\"center\" valign=\"top\" nowrap>");
        try
        {
            cModuler.print();
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td></tr><tr><td valign=\"top\">");
        out.println("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        out.println("<tr><td>");
        try
        {
            printContent(content, svtCfg, req, res, out, portletInfo);
            
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        content = null;
        
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td>");
        return;
    }

    public static void showModularType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        System.out.println("single_column");
        for(; cModuler.hasMoreModules(); out.println("<tr><td height=\"1\"> </td></tr>"))
        {
            Module2 currentModule = cModuler.getNext();
            if(currentModule != null)
            {
                module = currentModule.getId();
                moduleTitle = currentModule.getCustomTitle();
                moduleRealTitle = currentModule.getTitle();
                portletInfo.id = module;
                portletInfo.title = moduleTitle;
            } else
            {
                res.sendRedirect("");
            }
            Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
            System.out.println((new StringBuilder("rendered module = ")).append(module).toString());
            out.println("<tr><td>");
            out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
            if(!"".equals(moduleRealTitle))
            {
                out.println("<tr><td>");
                context.put("moduleTitle", moduleTitle);
                ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
                try
                {
                    cModuleTitle.print();
                }
                catch(Exception ex)
                {
                    out.println(ex.getMessage());
                }
                out.println("</td></tr>");
            }
            out.println("<tr><td>");
            try
            {
                System.out.println((new StringBuilder("print content ")).append(content).toString());
                printContent(content, svtCfg, req, res, out, portletInfo);
                System.out.println("print content done");
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
            out.println("</table>");
            out.println("</td></tr>");
        }

    }

    public static void showNarrowWideType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td>");
        out.println("<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\">");
        out.println("<tr><td class=\"module_content_left_bg\" valign=\"top\" width=\"280px\">");
        for(int colnum = 0; colnum < 2; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td valign=\"top\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                if(currentModule != null)
                {
                    module = currentModule.getId();
                    moduleTitle = currentModule.getCustomTitle();
                    moduleRealTitle = currentModule.getTitle();
                    portletInfo.id = module;
                    portletInfo.title = moduleTitle;
                } else
                {
                    res.sendRedirect("");
                }
                Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                out.println("<tr><td>");
                out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
                if(!"".equals(moduleRealTitle))
                {
                    out.println("<tr><td>");
                    context.put("moduleTitle", moduleTitle);
                    ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
                    try
                    {
                        cModuleTitle.print();
                    }
                    catch(Exception ex)
                    {
                        out.println(ex.getMessage());
                    }
                    out.println("</td></tr>");
                }
                out.println("<tr><td class=\"module_content_bg\">");
                try
                {
                    printContent(content, svtCfg, req, res, out, portletInfo);
                }
                catch(Exception ex)
                {
                    out.println(ex.getMessage());
                }
                out.println("</td></tr>");
                out.println("</table>");
                if(colnum == 0)
                {
                    out.println("</td>");
                } else
                {
                    out.println("</td></tr>");
                }
                out.println("<tr><td height=\"1\"> </td></tr>");
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showWideNarrowType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td>");
        out.println("<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\">");
        out.println("<tr><td valign=\"top\" >");
        for(int colnum = 0; colnum < 2; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td width=\"300px\" valign=\"top\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                if(currentModule != null)
                {
                    module = currentModule.getId();
                    moduleTitle = currentModule.getCustomTitle();
                    moduleRealTitle = currentModule.getTitle();
                    portletInfo.id = module;
                    portletInfo.title = moduleTitle;
                } else
                {
                    res.sendRedirect("");
                }
                Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                out.println("<tr><td>");
                out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
                if(!"".equals(moduleRealTitle))
                {
                    out.println("<tr><td>");
                    context.put("moduleTitle", moduleTitle);
                    ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
                    try
                    {
                        cModuleTitle.print();
                    }
                    catch(Exception ex)
                    {
                        out.println(ex.getMessage());
                    }
                    out.println("</td></tr>");
                }
                out.println("<tr><td>");
                try
                {
                    printContent(content, svtCfg, req, res, out, portletInfo);
                }
                catch(Exception ex)
                {
                    out.println(ex.getMessage());
                }
                out.println("</td></tr>");
                out.println("</table>");
                if(colnum == 0)
                {
                    out.println("</td>");
                } else
                {
                    out.println("</td></tr>");
                }
                out.println("<tr><td height=\"1\"> </td></tr>");
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showTwoColumnsType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\" width=\"45%\">");
        for(int colnum = 0; colnum < 2; colnum++)
        {
            if(colnum > 0)
            {
                out.println("</td><td valign=\"top\" width=\"55%\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                if(currentModule != null)
                {
                    module = currentModule.getId();
                    moduleTitle = currentModule.getCustomTitle();
                    moduleRealTitle = currentModule.getTitle();
                    portletInfo.id = module;
                    portletInfo.title = moduleTitle;
                } else
                {
                    res.sendRedirect("");
                }
                Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                out.println("<tr><td>");
                out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
                if(!"".equals(moduleRealTitle))
                {
                    out.println("<tr><td>");
                    context.put("moduleTitle", moduleTitle);
                    ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
                    try
                    {
                        cModuleTitle.print();
                    }
                    catch(Exception ex)
                    {
                        out.println(ex.getMessage());
                    }
                    out.println("</td></tr>");
                }
                out.println("<tr><td>");
                try
                {
                    printContent(content, svtCfg, req, res, out, portletInfo);
                }
                catch(Exception ex)
                {
                    out.println(ex.getMessage());
                }
                out.println("</td></tr>");
                out.println("</table>");
                out.println("</td></tr>");
                out.println("<tr><td height=\"1\"> </td></tr>");
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    public static void showThreeColumnsType(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PrintWriter out, HttpSession session)
        throws Exception
    {
        Moduler cModuler = new Moduler(engine, context, req, res);
        String moduleTitle = "";
        String moduleRealTitle = "";
        PortletInfo portletInfo = new PortletInfo();
        out.println("<tr><td>");
        out.println("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">");
        out.println("<tr><td valign=\"top\" width=\"20%\">");
        for(int colnum = 0; colnum < 3; colnum++)
        {
            if(colnum == 1)
            {
                out.println("</td><td valign=\"top\" width=\"60%\">");
            }
            if(colnum == 2)
            {
                out.println("</td><td valign=\"top\" width=\"20%\">");
            }
            out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
            Vector vmodules = cModuler.getModulesInColumn(colnum);
            for(int cn = 0; cn < vmodules.size(); cn++)
            {
                Module2 currentModule = (Module2)vmodules.elementAt(cn);
                if(currentModule != null)
                {
                    module = currentModule.getId();
                    moduleTitle = currentModule.getCustomTitle();
                    moduleRealTitle = currentModule.getTitle();
                    portletInfo.id = module;
                    portletInfo.title = moduleTitle;
                } else
                {
                    res.sendRedirect("");
                }
                Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
                out.println("<tr><td>");
                out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
                if(!"".equals(moduleRealTitle))
                {
                    out.println("<tr><td>");
                    context.put("moduleTitle", moduleTitle);
                    ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
                    try
                    {
                        cModuleTitle.print();
                    }
                    catch(Exception ex)
                    {
                        out.println(ex.getMessage());
                    }
                    out.println("</td></tr>");
                }
                out.println("<tr><td>");
                try
                {
                    printContent(content, svtCfg, req, res, out, portletInfo);
                }
                catch(Exception ex)
                {
                    out.println(ex.getMessage());
                }
                out.println("</td></tr>");
                out.println("</table>");
                out.println("</td></tr>");
            }

            out.println("</table>");
        }

        out.println("</td></tr></table>");
        out.println("</td></tr>");
    }

    private static void doPrintModule(Module2 currentModule, PortletInfo portletInfo, VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, 
            PrintWriter out, HttpSession session)
        throws Exception
    {
        String moduleTitle = "";
        String moduleRealTitle = "";
        if(currentModule != null)
        {
            module = currentModule.getId();
            moduleTitle = currentModule.getCustomTitle();
            moduleRealTitle = currentModule.getTitle();
            portletInfo.id = module;
            portletInfo.title = moduleTitle;
        } else
        {
            res.sendRedirect("");
        }
        Object content = renderContent(engine, context, svtCfg, req, res, module, portletInfo);
        out.println("<tr><td>");
        out.println("<table class=\"module_frame\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\"" +
" border=\"0\">"
);
        if(!"".equals(moduleRealTitle))
        {
            out.println("<tr><td>");
            context.put("moduleTitle", moduleTitle);
            ModuleTitle cModuleTitle = new ModuleTitle(engine, context, req, res);
            try
            {
                cModuleTitle.print();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
            out.println("</td></tr>");
        }
        out.println("<tr><td>");
        try
        {
            printContent(content, svtCfg, req, res, out, portletInfo);
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</td></tr>");
    }

    public static void printContent(Object content, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, PrintWriter out, PortletInfo portletInfo)
        throws Exception
    {
        if(content != null)
        {
            if(content instanceof VTemplate)
            {
            	System.out.println("VTemplate");
                HttpSession session = req.getSession();
                synchronized(session){
                ((VTemplate)content).print(session);
                }
            } 
            else
            if(content instanceof MerakPortlet)
            {
            	System.out.println("MerakPortlet");
                ((MerakPortlet)content).doView(req, res);
            } 
            else
            if(content instanceof GenericPortlet)
            {
            	System.out.println("GenericPortlet");
                Hashtable portletState = getPortletState(svtCfg, req, res, out, portletInfo);
                RenderRequest renderRequest = (RenderRequest)portletState.get("renderRequest");
                RenderResponse renderResponse = (RenderResponse)portletState.get("renderResponse");
                PortletConfig config = (PortletConfig)portletState.get("config");
                GenericPortlet portlet = (GenericPortlet)content;
                portlet.init(config);
                String reqMethod = req.getMethod();
                System.out.println((new StringBuilder("Portlet request method is = ")).append(reqMethod).toString());
                portlet.render(renderRequest, renderResponse);
            }
        }
    }
    
    //razman add
    public void printContentNew(Object content, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, PrintWriter out, PortletInfo portletInfo)
            throws Exception
        {
            if(content != null)
            {
                if(content instanceof VTemplate)
                {
                	synchronized(this){
	                    HttpSession session = req.getSession();
	                    ((VTemplate)content).print(session);
                	}
                } 
                else if(content instanceof MerakPortlet)
                {
                    ((MerakPortlet)content).doView(req, res);
                } 
                else if(content instanceof GenericPortlet)
                {
                        //synchronized(this){
                    	Hashtable portletState = getPortletState(svtCfg, req, res, out, portletInfo);
	                    RenderRequest renderRequest = (RenderRequest)portletState.get("renderRequest");
	                    RenderResponse renderResponse = (RenderResponse)portletState.get("renderResponse");
	                    PortletConfig config = (PortletConfig)portletState.get("config");
	                    GenericPortlet portlet = (GenericPortlet)content;
	                    portlet.init(config);
	                    String reqMethod = req.getMethod();
	                    System.out.println((new StringBuilder("Portlet request method is = ")).append(reqMethod).toString());                    
	                    portlet.render(renderRequest, renderResponse);
	                    // }
                }
            }
        }

    private static Hashtable getPortletState(ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, PrintWriter out, PortletInfo portletInfo)
        throws Exception
    {
        HttpSession session = req.getSession();
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
        renderResponse.printWriter = out;
        renderRequest.httpServletRequest = req;
        renderResponse.httpServletResponse = res;
        h.put("renderRequest", renderRequest);
        h.put("renderResponse", renderResponse);
        h.put("config", config);
        return h;
    }

    public static Object renderContent(VelocityEngine engine, VelocityContext context, ServletConfig svtCfg, HttpServletRequest req, HttpServletResponse res, String module, PortletInfo portletInfo)
    {
        HttpSession session = req.getSession();
        Object content = null;
        try
        {
            if(!"".equals(module))
            {
                if(session.getAttribute("_log_module") != null && !module.equals((String)session.getAttribute("_log_module")))
                {
                    UserTrackerLog.save(req, (String)session.getAttribute("_portal_login"), module);
                    session.setAttribute("_log_module", module);
                } else
                {
                    session.setAttribute("_log_module", module);
                }
            }
            System.out.println("** CustomClass.getName(module) : "+CustomClass.getName(module)+" module : "+module+" req.getRequestedSessionId() : "+req.getRequestedSessionId());
            content = ClassLoadManager.load(CustomClass.getName(module), module, req.getRequestedSessionId());
            System.out.println("** content : "+content);
            if(content instanceof VTemplate)
            {
                ((VTemplate)content).setEnvironment(engine, context, req, res);
                ((VTemplate)content).setServletContext(svtCfg.getServletContext());
                ((VTemplate)content).setServletConfig(svtCfg);
                ((VTemplate)content).setId(module);
            }
            if(content instanceof HtmlContainer)
            {
                String url = UserPage.getUrlForHtmlContainer(module);
                if(url != null)
                {
                    ((HtmlContainer)content).setUrl(url);
                }
            }
            if(content instanceof XMLContainer)
            {
                Hashtable h = UserPage.getUrlAndXslForXMLContainer(module);
                if(h != null)
                {
                    ((XMLContainer)content).setXml((String)h.get("xml"));
                    ((XMLContainer)content).setXsl((String)h.get("xsl"));
                }
            }
            if(content instanceof Attributable)
            {
                Hashtable h = UserPage.getValuesForAttributable(module);
                if(h != null)
                {
                    ((Attributable)content).setValues(h);
                }
            }
        }
        catch(DbException dbx)
        {
            content = new ErrorMsg(engine, context, req, res);
            ((ErrorMsg)content).setError((new StringBuilder("Database Error : ")).append(dbx.getMessage()).toString());
        }
        catch(ClassNotFoundException cnfex)
        {
            content = new ErrorMsg(engine, context, req, res);
            ((ErrorMsg)content).setError((new StringBuilder("ClassNotFoundException : ")).append(cnfex.getMessage()).toString());
        }
        catch(InstantiationException iex)
        {
            content = new ErrorMsg(engine, context, req, res);
            ((ErrorMsg)content).setError((new StringBuilder("InstantiationException : ")).append(iex.getMessage()).toString());
        }
        catch(IllegalAccessException illex)
        {
            content = new ErrorMsg(engine, context, req, res);
            ((ErrorMsg)content).setError((new StringBuilder("IllegalAccessException : ")).append(illex.getMessage()).toString());
        }
        catch(Exception ex)
        {
            content = new ErrorMsg(engine, context, req, res);
            ((ErrorMsg)content).setError((new StringBuilder("Other Exception during class initiation : ")).append(ex.getMessage()).toString());
        }
        return content;
    }
}
