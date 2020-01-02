package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailGadaianData;
import ekptg.model.htp.FrmUtilData;

public class FrmGadaianSenaraiFailSuratAjax extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ekptg.view.htp.FrmGadaianSenaraiFailSuratAjax.class);
	Vector<?> vecHash = null;
    String idUrusan = "108";

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
    	String vm = "";
    	String disability = "";
    	//String readability = "";
    	Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
    	//GadaianProcess GP = new GadaianProcess();
    	FrmGadaianA FGA = new FrmGadaianA();
    	list.clear();
		String submit = getParam("command");
		//String mode = getParam("mode");
		//String setting = getParam("setting");
//		String negeri = getParam("");
//		String permohonan = getParam("idPermohonan");
		Hashtable<?, ?> permohonan = null;
		
		String userId = (String)session.getAttribute("_portal_login");

		if("SenaraiPermohonan".equals(submit)){
			/*
			 * template name = app/htp/frmGadaianSenaraiPermohonanSuratAjax.jsp
			 */
			vm = "app/htp/frmGadaianSenaraiPermohonanSuratAjaxBaru.jsp";
			
			int idFail = Integer.parseInt(getParam("idFail"));
			String carian = getParam("NamaPemohon");
//			GP.ListPermohonan(session, idFail, carian);
			FGA.ListPermohonan(session, String.valueOf(idFail),"",carian);
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
			//vm = "app/htp/frmGadaianMenuSuratAjax.jsp";
			//by rosli on 12/02/2010
			vm = "app/htp/frmSenaraiSuratHTP.jsp";
			
			int idNegeri = Integer.parseInt(getParam("idNegeri"));
			//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			String idPermohonan = "";
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
			
			idPermohonan = getParam("idPermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			paparanRekod(userId);
		}else{
			/*
			 * template name = app/htp/frmSenaraiFailGadaianSuratAjax.jsp
			 */
			vm = "app/htp/frmGadaianSenaraiFailSuratAjax.jsp";
			
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			
			FGA.ListFail(session, key_cari, keyNo_cari, idNegeri,null,null,null,null,null);
	    	list = FrmSenaraiFailGadaianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			log.info("::SenaraiFail");
		}
	    context.put("permohonanInfo", permohonan);
		context.put("senaraiSurat",vecHash);
		
        return vm;
	}

	private void paparanRekod(String login) throws Exception{
		Vector<?> v = FrmUtilData.getSubUrusanByRole(login);
		Tblrujsuburusan f = null;
		String s = "TIADA";
		for (int i = 0; i < v.size(); i++) {
			f = (Tblrujsuburusan) v.get(i);				
			
			if (i==0) {
				s = ""+f.getIdSuburusan();
			} else {
				s += ","+f.getIdSuburusan();
			}
		}
		//vecHash = FrmUtilData.getLaporanMengikutUrusan(s,null,"T");
		vecHash = FrmUtilData.getLaporanSuratMengikutUrusan(idUrusan,null,"T");
		
}


}
