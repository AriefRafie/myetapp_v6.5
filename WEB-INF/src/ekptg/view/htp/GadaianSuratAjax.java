package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailGadaianData;

public class GadaianSuratAjax extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(GadaianSuratAjax.class);

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
    	log.info("GadaianSurat::");
    	String vm = "";
    	String disability = "";
    	String readability = "";
    	Vector list = new Vector();
    	//GadaianProcess GP = new GadaianProcess();
    	FrmGadaianA FGA = new FrmGadaianA();
    	list.clear();
		String submit = getParam("command");
		String mode = getParam("mode");
		String setting = getParam("setting");
//		String negeri = getParam("");
//		String permohonan = getParam("idPermohonan");
		if("SenaraiPermohonan".equals(submit)){
			/*
			 * template name = app/htp/frmGadaianSenaraiPermohonanSuratAjax.jsp
			 */
			vm = "app/htp/frmGadaianSenaraiPermohonanSuratAjax.jsp";
			
			int idFail = Integer.parseInt(getParam("idFail"));
			String carian = getParam("NamaPemohon");
//			GP.ListPermohonan(session, idFail, carian);
			FGA.ListPermohonan(session, String.valueOf(idFail),"", carian);
			list = FrmGadaianSenaraiPermohonanData.getListPermohonan();
		    this.context.put("SenaraiPermohonan", list);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("carian", getParam("NamaPemohon"));
			log.info("GadaianSurat::SenaraiPermohonan");
			
		}else if("PaparMenu".equals(submit)){
			/*
			 * template name = app/htp/frmGadaianMenuSuratAjax.jsp
			 */
			vm = "app/htp/frmGadaianMenuSuratAjax.jsp";
			
			int idNegeri = Integer.parseInt(getParam("idNegeri"));
			int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			
//			int idNegeri = Integer.parseInt(negeri);
//			int idPermohonan = Integer.parseInt(permohonan);
			
			log.info("idNegeri :" + idNegeri);
			log.info("idPermohonan : " + idPermohonan);
			
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
			
			log.info("GadaianSurat::PaparMenu");
		}else{
			/*
			 * template name = app/htp/frmSenaraiFailGadaianSuratAjax.jsp
			 */
			vm = "app/htp/frmSenaraiFailGadaianSuratAjax.jsp";
			
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
//			GP.ListFail(session, key_cari, keyNo_cari, idNegeri);
			
			FGA.ListFail(session, key_cari, keyNo_cari, idNegeri,null,null,null,null,null);
	    	list = FrmSenaraiFailGadaianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			log.info("GadaianSurat::SenaraiFail");
		}
		
        return vm;
	}



}
