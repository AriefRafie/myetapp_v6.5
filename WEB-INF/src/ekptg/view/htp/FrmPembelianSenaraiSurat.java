package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailPembelianData;

public class FrmPembelianSenaraiSurat extends VTemplate {
	
	public Template doTemplate() throws Exception	{
		
		HttpSession session = this.request.getSession();
	    String template_name = "";
		Vector list = new Vector();
    	list.clear();
       	String disability = "";
    	String readability = "";
		String submit = getParam("command");

	    if("surat".equals(submit)){
	    	template_name = "app/htp/frmPembelianSenaraiSurat.jsp";
	    	
			String idPermohonan = getParam("idPermohonan");
			session.setAttribute("permohonan", idPermohonan);
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);

	    }else{		
    		template_name = "app/htp/frmPembelianSenaraiFailSurat.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	list = FrmSenaraiFailPembelianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			System.out.println("PembelianProcess::SenaraiFail");
		}
    	Template template = this.engine.getTemplate(template_name);
        return template;
    }
	
	//*** Senarai Fail Pembelian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	  {
		FrmSenaraiFailPembelianData.setList(key_cari,keyNo_cari,idNegeri);
	  }


}
