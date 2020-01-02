// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 8/2/2012 12:31:40 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   VTemplate.java

package lebah.portal.velocity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.util.LogActivity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.helpers.Paging;
import ekptg.helpers.Paging2;

public abstract class VTemplate extends HttpServlet
{
    protected VTemplate()
    {
        id = "";
        templateName = "";
        parsedTemplateName = "";
        isActionTemplate = false;
        showVM = false;
        isDiv = false;
        submit = "";
        user = "";
    }
    protected VTemplate(VelocityEngine engine, VelocityContext context, HttpServletRequest request, HttpServletResponse response)
    {
        id = "";
        templateName = "";
        parsedTemplateName = "";
        isActionTemplate = false;
        showVM = false;
        isDiv = false;
        submit = "";
        user = "";
        this.engine = engine;
        this.context = context;
        this.request = request;
        this.response = response;
    }
    public void setEnvironment(VelocityEngine engine, VelocityContext context, HttpServletRequest request, HttpServletResponse response)
    {
        this.engine = engine;
        this.context = context;
        this.request = request;
        this.response = response;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return id;
    }
    public void setServletContext(ServletContext ctx)
    {
        servletContext = ctx;
    }
    public void setServletConfig(ServletConfig cfg)
    {
        servletConfig = cfg;
    }
    public ServletConfig getServletConfig()
    {
        return servletConfig;
    }
    public Template doTemplate()
        throws Exception
    {
        return null;
    }
    public StringBuffer getBuffer()
        throws Exception
    {
        StringBuffer sb = new StringBuffer("");
        try
        {
            Template template = doTemplate();
            templateName = template.getName();
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            writer.close();
            sb = writer.getBuffer();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        return sb;
    }
    public StringBuffer getBuffer(HttpSession session)
        throws Exception
    {
        StringBuffer sb = new StringBuffer("");
        try
        {
            Template template = doTemplate();
            if(session.getAttribute("_portal_login") != null)
                LogActivity.log(this, submit, (String)session.getAttribute("_portal_login"));
            templateName = template.getName();
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            writer.close();
            sb = writer.getBuffer();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        return sb;
    }
    public void setShowVM(boolean b)
    {
        showVM = b;
    }
    public void print()
        throws Exception
    {
        PrintWriter out = response.getWriter();
        out.print(getBuffer());
        printVM(out);
    }
    public void print(HttpSession session)
        throws Exception
    {
        PrintWriter out = response.getWriter();
        out.print(getBuffer(session));
        printVM(out);
    }
    private void printVM(PrintWriter out)
    {
        if(showVM)
        {
            out.print("<br/><table width=\"100%\"><tr><td align=\"right\"><font size=\"1\">");
            if(isActionTemplate)
                out.print(parsedTemplateName);
            else
                out.print(templateName);
            out.print("</font></tr></td></table>");
        }
    }
    protected String getParam(String param)
    {
        return request.getParameter(param) == null ? "" : request.getParameter(param);
    }
    protected int getParamAsInteger(String param)
    {
        return getParam(param) == "" ? 0 : Integer.parseInt(getParam(param));
    }
    protected boolean post(HttpSession session)
    {
        return session.getAttribute("doPost") == null ? true : "true".equals((String)session.getAttribute("doPost"));
    }
    protected String getParam(HttpServletRequest request, String param)
    {
        return request.getParameter(param) == null ? "" : request.getParameter(param);
    }
    public String getParsedTemplateName()
    {
        return parsedTemplateName;
    }
    public void setParsedTemplateName(String parsedTemplateName)
    {
        this.parsedTemplateName = parsedTemplateName;
    }
    public boolean isActionTemplate()
    {
        return isActionTemplate;
    }
    public void setActionTemplate(boolean isActionTemplate)
    {
        this.isActionTemplate = isActionTemplate;
    }
    public boolean isDiv()
    {
        return isDiv;
    }
    public void setDiv(boolean isDiv)
    {
        this.isDiv = isDiv;
    }
    protected void setupPage(HttpSession session, String action, Vector lists)
    {
        if(lists == null) {
            context.put("totalRecords", Integer.valueOf(0));
            context.remove("SenaraiFail");
            context.put("page", Integer.valueOf(0));
            context.put("itemsPerPage", Integer.valueOf(0));
            context.put("totalPages", Integer.valueOf(0));
            context.put("startNumber", Integer.valueOf(0));
            context.put("isFirstPage", Boolean.valueOf(true));
            context.put("isLastPage", Boolean.valueOf(true));
        } else {
            context.put("totalRecords", Integer.valueOf(lists.size()));
            int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
            int itemsPerPage;
            if(context.get("itemsPerPage") == null || "".equals(context.get("itemsPerPage")) || "0".equals(context.get("itemsPerPage")))
                itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage") : 10;
            else
                itemsPerPage = ((Integer)context.get("itemsPerPage")).intValue();
            if("getNext".equals(action))
                page++;
            else
            if("getPrevious".equals(action))
                page--;
            else
            if("getPage".equals(action))
                page = getParamAsInteger("value");
            else
            if("doChangeItemPerPage".equals(action))
                itemsPerPage = getParamAsInteger("itemsPerPage");
            if(itemsPerPage == 0)
                itemsPerPage = 10;
            Paging paging = new Paging(session, lists, itemsPerPage);
            if(page > paging.getTotalPages())
                page = 1;
            context.put("SenaraiFail", paging.getPage(page));
            context.put("page", new Integer(page));
            context.put("itemsPerPage", new Integer(itemsPerPage));
            context.put("totalPages", new Integer(paging.getTotalPages()));
            context.put("startNumber", new Integer(paging.getTopNumber()));
            context.put("isFirstPage", new Boolean(paging.isFirstPage()));
            context.put("isLastPage", new Boolean(paging.isLastPage()));
        }
    }

    protected void setupPage(HttpSession session, String action, List lists)
    {
        if(lists == null) {
            context.put("totalRecords", Integer.valueOf(0));
            context.put("SenaraiFail", "");
            context.put("page", Integer.valueOf(0));
            context.put("itemsPerPage", Integer.valueOf(0));
            context.put("totalPages", Integer.valueOf(0));
            context.put("startNumber", Integer.valueOf(0));
            context.put("isFirstPage", Boolean.valueOf(true));
            context.put("isLastPage", Boolean.valueOf(true));
        } else {
            context.put("totalRecords", Integer.valueOf(lists.size()));
            int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
            int itemsPerPage;
            if(context.get("itemsPerPage") == null || "".equals(context.get("itemsPerPage")) || "0".equals(context.get("itemsPerPage")))
                itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage") : 10;
            else
                itemsPerPage = ((Integer)context.get("itemsPerPage")).intValue();
            if("getNext".equals(action))
                page++;
            else
            if("getPrevious".equals(action))
                page--;
            else
            if("getPage".equals(action))
                page = getParamAsInteger("value");
            else
            if("doChangeItemPerPage".equals(action))
                itemsPerPage = getParamAsInteger("itemsPerPage");
            if(itemsPerPage == 0)
                itemsPerPage = 10;
            Paging2 paging = new Paging2(session, lists, itemsPerPage);
            if(page > paging.getTotalPages())
                page = 1;
            context.put("SenaraiFail", paging.getPage(page));
            context.put("page", new Integer(page));
            context.put("itemsPerPage", new Integer(itemsPerPage));
            context.put("totalPages", new Integer(paging.getTotalPages()));
            context.put("startNumber", new Integer(paging.getTopNumber()));
            context.put("isFirstPage", new Boolean(paging.isFirstPage()));
            context.put("isLastPage", new Boolean(paging.isLastPage()));
        }
    }

    protected VelocityEngine engine;
    protected VelocityContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext servletContext;
    protected ServletConfig servletConfig;
    protected String id;
    protected String templateName;
    protected String parsedTemplateName;
    protected boolean isActionTemplate;
    protected boolean showVM;
    protected boolean isDiv;
    protected String submit;
    protected String user;
}