package ekptg.view.htp;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmPKSuratKembaliBL extends VTemplate {

    @Override
	public Template doTemplate() throws Exception {
    	
    	HttpSession session = this.request.getSession();

    	String vm = "app/htp/frmPKSuratKembaliBL.jsp";
    	String id = getParam("id_kemaskini");
    	/*Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
	    this.context.put("permohonanInfo", permohonan);
	    this.context.put("util", new lebah.util.Util());
	    this.context.put("currentdate", new java.util.Date());
	    //this.context.put("currentdate1", new java.sql.Date();
	    Hashtable listKementerian = FrmKementerianData.getList((String)permohonan.get("idkementerian"));
	    this.context.put("infokementerian", listKementerian);
*/
	    System.out.println("FrmPKSuratKembaliBL:doTemplate::currentdate:::"+new java.util.Date());
	    System.out.println("FrmPKSuratKembaliBL:doTemplate::id:::"+id);

    	
//    	FrmSenaraiFailGadaianData.setList();
    	//Vector list = FrmSenaraiFailGadaianData.getList();
	    //this.context.put("Senaraifail", list);

        Template template = this.engine.getTemplate(vm);
        return template;
    }
}
