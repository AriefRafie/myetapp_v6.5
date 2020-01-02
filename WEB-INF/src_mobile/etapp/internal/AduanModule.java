package etapp.internal;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class AduanModule extends VTemplate {
	public Template doTemplate() throws Exception {
		HttpSession session = request.getSession();
		
        String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        
        context.put("moduleName", mName[1].toLowerCase());

	    Template template = engine.getTemplate("vtl/mobile/Aduan/menu_complaint.vm"); 
	    return template; 
	}
}