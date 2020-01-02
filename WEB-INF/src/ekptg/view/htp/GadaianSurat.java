package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailGadaianData;

public class GadaianSurat extends VTemplate {
	public Template doTemplate() throws Exception
    {
		HttpSession session = this.request.getSession();
    	System.out.println("GadaianSurat::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	Vector list = new Vector();
    	GadaianProcess GP = new GadaianProcess();
    	list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		String setting = getParam("setting");
		if("SenaraiPermohonan".equals(submit)){
			vm = "app/htp/frmGadaianSenaraiPermohonanSurat.jsp";
			int idFail = Integer.parseInt(getParam("idFail"));
			String carian = getParam("NamaPemohon");
			GP.ListPermohonan(session, idFail, carian);
			list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
		    this.context.put("SenaraiPermohonan", list);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("carian", getParam("NamaPemohon"));
			System.out.println("GadaianSurat::SenaraiPermohonan");
		}else if("PaparMenu".equals(submit)){
			int idNegeri = Integer.parseInt(getParam("idNegeri"));
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			if(idNegeri == 12){
				this.context.put("idNegerixxx", 1);
				this.context.put("idPermohonan", idPermohonan);
			}else if(idNegeri == 13 || idNegeri == 15){
				this.context.put("idNegerixxx", 2);
				this.context.put("idPermohonan", idPermohonan);
			}else{
				this.context.put("idNegerixxx", 3);
				this.context.put("idPermohonan", idPermohonan);
			}
			vm = "app/htp/frmGadaianMenuSurat.jsp";
			System.out.println("GadaianSurat::PaparMenu");
		}else{
			vm = "app/htp/frmSenaraiFailGadaianSurat.jsp";
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			GP.ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	list = FrmSenaraiFailGadaianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			System.out.println("GadaianSurat::SenaraiFail");
		}
		Template template = this.engine.getTemplate(vm);
        return template;
    }
}
