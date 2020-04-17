package lebah.app;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

// Referenced classes of package lebah.app:
//            RoleProcessor

public class RoleEditorModule extends VTemplate
{

    private String targetPage;

    public RoleEditorModule()
    {
    }

    public Template doTemplate()
        throws Exception
    {
        HttpSession session = request.getSession();
        RoleProcessor processor = new RoleProcessor();
        String action = request.getParameter("form_action");
        System.out.println("action : "+action);
        
        if(action == null || action.equals(""))
        {
            action = "none";
        }
        if(action.equals("none"))
        {
            java.util.Vector list = processor.getRoles();
            context.put("roleList", list);
            context.put("isRoleSelected", Boolean.valueOf(false));
            targetPage = "vtl/admin/role_editor.vm";
        } else
        if(action.equals("get_modules"))
        {
        	Db db = null;
	        try {		        	
	        	db = new Db();		    
	            String role = request.getParameter("role");
	            java.util.Vector moduleList = processor.getModules(role,db);
	            context.put("moduleList", moduleList);
	            java.util.Vector roleList = processor.getRoles();
	            context.put("roleList", roleList);
	            context.put("isRoleSelected", Boolean.valueOf(true));
	            context.put("userRole", role);
	        } finally {
			      if (db != null) db.close();
		    }
            targetPage = "vtl/admin/role_editor.vm";
        } else
        if(action.equals("update_role"))
        {
            String role = request.getParameter("role");
            String modules[] = request.getParameterValues("module");
            processor.updateRoleModule(role, modules);
            java.util.Vector moduleList = processor.getModules(role);
            context.put("moduleList", moduleList);
            java.util.Vector roleList = processor.getRoles();
            context.put("roleList", roleList);
            context.put("isRoleSelected", Boolean.valueOf(true));
            context.put("userRole", role);
            targetPage = "vtl/admin/role_editor.vm";
        }
        Template template = engine.getTemplate(targetPage);
        return template;
    }
}
