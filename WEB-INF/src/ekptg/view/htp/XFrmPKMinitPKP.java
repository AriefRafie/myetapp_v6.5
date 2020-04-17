package ekptg.view.htp;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;

public class XFrmPKMinitPKP extends VTemplate {

    @Override
	public Template doTemplate() throws Exception {
    	
    	HttpSession session = this.request.getSession();

    	String vm = "app/htp/frmPKMinitPKP.jsp";
    	String id = getParam("id_kemaskini");
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

	    System.out.println("FrmPKSuratPerjanjianTLengkap:doTemplate::currentdate:::"+new java.util.Date());
	    System.out.println("FrmPKSuratPerjanjianTLengkap:doTemplate::id:::"+id);

    	
//    	FrmSenaraiFailGadaianData.setList();
    	//Vector list = FrmSenaraiFailGadaianData.getList();
	    //this.context.put("Senaraifail", list);

        Template template = this.engine.getTemplate(vm);
        return template;
    }
}
