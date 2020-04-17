package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.htp.FrmSenaraiFailGadaianData;

public class FrmSenaraiFailGadaian extends VTemplate {

    @Override
	public Template doTemplate() throws Exception

    {
    	HttpSession session = this.request.getSession();

    	String vm = "app/htp/frmSenaraiFailGadaian.jsp";

//    	FrmSenaraiFailGadaianData.setList();
    	Vector list = FrmSenaraiFailGadaianData.getList();
	    this.context.put("Senaraifail", list);

        Template template = this.engine.getTemplate(vm);
        return template;
    }
}
