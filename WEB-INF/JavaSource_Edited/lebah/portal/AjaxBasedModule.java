package lebah.portal;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lebah.portal.action.ButtonDivElement;
import lebah.portal.action.element.ButtonElement;
import lebah.portal.action.element.HTMLElement;
import lebah.portal.action.element.HrefElement;
import lebah.portal.velocity.VTemplate;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.view.ppk.BicaraInteraktif;

public abstract class AjaxBasedModule extends VTemplate
{

    protected String formName;
    protected String moduleId;
    protected String className;
    protected boolean uploadFile;
    //static Logger myLogger = Logger.getLogger("lebah/portal/AjaxBasedModule");
    static Logger myLogger = Logger.getLogger(AjaxBasedModule.class);
    private boolean addMethod;
    private Vector methods;
    private Hashtable hiddenField;
    private Vector hiddenFieldList;
    private Hashtable confirms;

    public AjaxBasedModule()
    {
        formName = "";
        moduleId = "";
        className = "";
        uploadFile = false;
        addMethod = false;
        methods = new Vector();
        hiddenField = new Hashtable();
        hiddenFieldList = new Vector();
        confirms = new Hashtable();
    }

    public void setUploadFile(boolean flag)
    {
        uploadFile = flag;
    }

    public boolean isOnlineUser(String url)
    {
        if(url == null)
        {
            return false;
        }
        return url.indexOf("online") != -1;
    }

    public abstract String doTemplate2()
        throws Exception;

    public Template doTemplate()
        throws Exception
    {
        HttpSession session = request.getSession();
        String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
        myLogger.info("USER_ID_SYSTEM ::::::::: "+USER_ID_SYSTEM);
        Template template = null;
        /*
        if(USER_ID_SYSTEM == null)
		{
			template = engine.getTemplate("vtl/ajax/div_main.vm");
			context.put("vmName", "app/blankSessionOut.vm");       
		}
        else{
        */
        String requestMethod = request.getMethod();
        String enctype = "";
        moduleId = getId();
        if(moduleId == null || "".equals(moduleId))
        {
            moduleId = getParam("module_id");
        }
        context.put("moduleId", moduleId);
        context.put("uniqueId", moduleId.replace('.', '_'));
        showVM = false;
        String vmMain = "";
        if(!isDiv)
        {
            vmMain = "vtl/ajax/ajax_main.vm";
        } else
        {
            vmMain = "vtl/ajax/div_main.vm";
        }
        if(uploadFile)
        {
            enctype = "enctype=\"multipart/form-data\"";
        }
        context.put("enctype", enctype);
        className = getClass().getName();
        context.put("className", className);
        String pid = moduleId;
        context.put("targetName", (new StringBuilder("D")).append(pid.replace('.', '_')).toString());
        formName = (new StringBuilder("F")).append(pid.replace('.', '_')).toString();
        context.put("formName", formName);
        context.put("formname", formName);
        context.put("form", (new StringBuilder("F")).append(formName).toString());
        String securityToken = (String)session.getAttribute("securityToken");
        context.put("securityToken", securityToken);
        context.put("button", new ButtonElement(getId(), this));
        context.put("button_validate", new ButtonElement(getId(), this, true));
        context.put("button_div", new ButtonDivElement(getId(), this));
        context.put("href", new HrefElement(getId(), this));
        HTMLElement html = new HTMLElement(getId(), this, request.getPathInfo().lastIndexOf("/") != 0 ? "../../" : "../");
        context.put("html", html);
        String contentName = (new StringBuilder("contentDIV")).append(pid.replace('.', '_')).toString();
        context.put("contentName", contentName);
        
        if(USER_ID_SYSTEM == null)
		{
			template = engine.getTemplate("app/blankSessionOut.vm");
			context.put("vmName", "");       
			//context.put("getId", getId());       
		}
        else
        {
        	context.put("vmName", doTemplate2());   
	        if(engine != null)
	        {
	            template = engine.getTemplate(vmMain);
	        }
        }
       //}
        return template;
    }

    public void receiveMethod(String methodName)
    {
        if(!methods.contains(methodName))
        {
            methods.addElement(methodName);
            confirms.put(methodName, "");
            addMethod = true;
        } else
        {
            addMethod = false;
        }
    }

    public void receiveParams(String params, String methodName)
    {
        if(addMethod)
        {
            StringTokenizer st = new StringTokenizer(params, ",");
            Vector v = new Vector();
            while(st.hasMoreTokens()) 
            {
                String token = st.nextToken();
                String param = token.substring(0, token.indexOf("=")).trim();
                v.addElement(param);
                if(!hiddenFieldList.contains(param))
                {
                    hiddenFieldList.addElement(param);
                }
            }
            hiddenField.put(methodName, v);
        }
    }

    public void receiveConfirm(String confirm, String methodName)
    {
        if(addMethod)
        {
            confirms.put(methodName, confirm);
        }
    }

}
