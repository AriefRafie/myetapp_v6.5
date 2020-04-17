package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;


public class FrmSewaanSemak extends VTemplate {

    public Template doTemplate() throws Exception

    {
    		Date now = new Date();
    		SimpleDateFormat Format =
                new SimpleDateFormat("dd/MM/yyyy");

    		String vm = "vtl/htp/frmSewaanSemak.jsp";
            //this.context.put("sekarang", Format.format(now));
            //Vector listNegeri = List.getNegeri();
    	    //this.context.put("SenaraiNegeri", listNegeri);
            Template template = this.engine.getTemplate(vm);

            return template;

    }
}
