package ekptg.view.htp;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;

public class FrmPembelianSuratMinit extends VTemplate {
	
	public Template doTemplate() throws Exception	{
		
		HttpSession session = this.request.getSession();
	    String template_name = "";
    	String id = (String)session.getAttribute("permohonan");

    	Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
	    this.context.put("permohonanInfo", permohonan);
	    this.context.put("util", new lebah.util.Util());
	    this.context.put("currentdate", new java.util.Date());
	    Hashtable listkementerian= ekptg.model.utils.FrmKementerianData.getList((String)permohonan.get("idkementerian"));
	    this.context.put("infokementerian", listkementerian);
	    
	    Hashtable listagensi= ekptg.model.utils.FrmAgensiData.getList((String)permohonan.get("idagensi"));
	    this.context.put("infoagensi", listagensi);

	    Hashtable listnegeri= ekptg.model.utils.FrmNegeriData.getList((String)listagensi.get("id_negeri"));
	    this.context.put("infonegeri", listnegeri);
    	template_name = "app/htp/frmPembelianSuratMinit.jsp";
    	Template template = this.engine.getTemplate(template_name);
        return template;
    }

}
