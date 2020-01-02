package ekptg.view.pfd;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmFolder extends VTemplate {
	
	 public Template doTemplate() throws Exception

	    {

	            String vm = "app/pfd/frmFolder.jsp";

	            Template template = this.engine.getTemplate(vm);

	            return template;

	    }

}
