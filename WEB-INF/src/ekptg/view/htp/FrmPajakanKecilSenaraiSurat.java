package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmSenaraiFailPembelianData;
import ekptg.model.htp.FrmUtilData;

public class FrmPajakanKecilSenaraiSurat extends AjaxBasedModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5299932488418079540L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmPajakanKecilSenaraiSurat.class);

	public String doTemplate2() throws Exception	{
		
		HttpSession session = this.request.getSession();
	    String template_name = "";
       	String disability = "";
    	String readability = "";
		String submit = getParam("command");
		Hashtable permohonan = null;
		final lebah.util.Util lutil = new lebah.util.Util();
	    Date now = new Date();
	    Vector<?> list =null;
		String strTarikhsemasa = lutil.getDateTime(now, "dd/MM/yyyy");
	    this.context.put("tarikhsemasa", strTarikhsemasa);
		mylog.info(":submit:::"+submit);
		if(session.getAttribute("permohonan")!=null){
			mylog.info(":mysession:::"+session.getAttribute("permohonan"));
	    	template_name = "app/htp/frmPajakanKecilSenaraiSurat.jsp";

			String idPermohonan = (String)session.getAttribute("permohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable)FrmUtilData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
			
		}

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
	    	template_name = "app/htp/frmPajakanKecilSenaraiSurat.jsp";

			String idPermohonan = getParam("idpermohonan");
			mylog.info("doTemplate2::surat||idPermohonan:::"+idPermohonan);
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable)FrmUtilData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
		    
	    }else if("carian".equals(submit)){ //carian
	    	String nofail = getParam("NoFail");
		    this.context.put("carianNoFail", nofail);
	    	template_name = "app/htp/frmPajakanKecilSenaraiFailSurat.jsp";
    		list = FrmSenaraiFailPajakanKecilData.getList(nofail,(String)session.getAttribute("_ekptg_user_id"));
	    	this.context.put("Senaraifail", list);  /**/
	    }else{		
    		template_name = "app/htp/frmPajakanKecilSenaraiFailSurat.jsp";
    		
			String keyNo_cari = getParam("NoFail")==""?"":getParam("NoFail");
    		list = FrmSenaraiFailPajakanKecilData.getList("",(String)session.getAttribute("_ekptg_user_id"));
    		
			String key_cari = getParam("NamaFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			//ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	//list = FrmSenaraiFailPembelianData.getList();
    		//list = FrmUtilData. getSenaraiFailByUrusan("3", "", "",Long.parseLong("20"));
 
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
		}
        return template_name;
    }
	
	//*** Senarai Fail Pembelian Controller
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) 
		throws Exception{
		FrmSenaraiFailPembelianData.setList(key_cari,keyNo_cari,idNegeri);
	  }


}
