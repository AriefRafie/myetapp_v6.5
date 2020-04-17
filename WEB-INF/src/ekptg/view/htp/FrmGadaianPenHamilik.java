package ekptg.view.htp;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmGadaianPenHamilik extends VTemplate {

    public Template doTemplate() throws Exception

    {

            String vm = "app/htp/frmGadaianPenHamilik.jsp";

            Template template = this.engine.getTemplate(vm);

            return template;

    }
}
