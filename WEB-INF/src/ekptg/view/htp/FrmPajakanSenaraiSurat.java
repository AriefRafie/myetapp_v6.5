package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailPembelianData;
import ekptg.model.htp.FrmUtilData;

public class FrmPajakanSenaraiSurat extends AjaxBasedModule {
	
	public String doTemplate2() throws Exception	{
		
		HttpSession session = this.request.getSession();
	    String template_name = "";
		Vector list = new Vector();
    	list.clear();
       	String disability = "";
    	String readability = "";
		String submit = getParam("command");
		System.out.println(this.className+":doTemplate2::submit:::"+submit);
		Hashtable permohonan = null;
		lebah.util.Util lutil = new lebah.util.Util();
	    Date now = new Date();
	    
		String strTarikhsemasa = lutil.getDateTime(now, "dd/MM/yyyy");
	    this.context.put("tarikhsemasa", strTarikhsemasa);

	    if("memo".equals(submit)){
	    	template_name = "app/htp/frmPajakanMemoPenawaran.jsp";
	    	
			String idPermohonan = getParam("idpermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
		    
	    }else if("cukailebih".equals(submit)){
	    	template_name = "app/htp/frmCukaiSuratMaklumanStatusLebihan.jsp";
	    	
			String idPermohonan = getParam("idpermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
	    }else if("pegesahansenarai".equals(submit)){
	    	template_name = "app/htp/frmCukaiSuratPengesahanSenaraiCukai.jsp";
    	
	    	String idPermohonan = getParam("idpermohonan");
	    	session.setAttribute("permohonan", idPermohonan);
	    	permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(idPermohonan);

	    	Tblrujpejabatjkptg pejabatHantar = FrmUtilData.getPejabatJKPTG(null, null,null, "Ketua Pengarah");	
	    	this.context.put("infoHantar", pejabatHantar);
	    	
	    	//Tblrujpejabatjkptg pejabatPengirim = (Tblrujpejabatjkptg)FrmUtilData.getPejabatJKPTG(null, (String)permohonan.get("idnegeri"),null, "Pengarah".toUpperCase());
	    	//this.context.put("infoPengirim", pejabatPengirim);
	    	
	    	this.context.put("permohonanInfo", permohonan);
	    }else if("surat".equals(submit)){//senarai surat
	    	template_name = "app/htp/frmPajakanSenaraiSurat.jsp";

			String idPermohonan = getParam("idpermohonan");
			System.out.println(this.className+":doTemplate2::surat||idPermohonan:::"+idPermohonan);
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable)FrmUtilData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);

	    }else{		
    		template_name = "app/htp/frmPajakanSenaraiFailSurat.jsp";
    		//template_name = "app/htp/frmCukaiSenaraiFailUpload.jsp";
    		
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			//ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	//list = FrmSenaraiFailPembelianData.getList();
    		list = FrmUtilData. getSenaraiFailByUrusan("3", "", "",Long.parseLong("20"));
 
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			System.out.println(this.className+":doTemplate2::else");
		}
    	//Template template = this.engine.getTemplate(template_name);
        return template_name;
    }
	
	//*** Senarai Fail Pembelian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception
	  {
		FrmSenaraiFailPembelianData.setList(key_cari,keyNo_cari,idNegeri);
	  }


}
