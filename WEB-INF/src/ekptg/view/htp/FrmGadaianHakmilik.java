package ekptg.view.htp;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmGadaianHakmilik extends VTemplate {

    public Template doTemplate() throws Exception

    {

            String vm = "app/htp/frmGadaianHakmilik.jsp";

            Template template = this.engine.getTemplate(vm);

            return template;

    }
}
