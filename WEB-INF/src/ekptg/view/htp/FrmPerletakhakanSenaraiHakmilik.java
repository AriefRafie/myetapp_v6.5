package ekptg.view.htp;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmPerletakhakanSenaraiHakmilik extends VTemplate {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Template doTemplate() throws Exception

    {

            String vm = "app/htp/frmPerletakhakanSenaraiHakmilik.jsp";

            Template template = this.engine.getTemplate(vm);

            return template;

    }
}
