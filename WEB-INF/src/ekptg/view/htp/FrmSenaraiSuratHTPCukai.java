package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmUtilData;

public class FrmSenaraiSuratHTPCukai extends AjaxBasedModule {	
	/** sama fungsi dengan class FrmCukaiSenaraiSurat
	 * 
	 */
	private static final long serialVersionUID = -6366949383289282002L;
	private final String PATHTP="app/htp/";
	private final String PATH="app/htp/cukai/";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmSenaraiSuratHTPCukai.class);
	Vector<?> vecSenaraiSurat = null;
    String idUrusan = "11";
    String idSubUrusan = "43";
	public String doTemplate2() throws Exception	{
		
		HttpSession session = this.request.getSession();
	    String template_name = "";
		Vector<?> list = null;
       	String disability = "";
    	//String readability = "";
     	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
		String submit = getParam("command");
		Hashtable<?, ?> permohonan = null;
		lebah.util.Util lutil = new lebah.util.Util();
	    
		Date now = new Date();
		String strTarikhsemasa = lutil.getDateTime(now, "dd/MM/yyyy");
	    this.context.put("tarikhsemasa", strTarikhsemasa);
		String userId = (String)session.getAttribute("_portal_login");

	    if("memo".equals(submit)){
	    	template_name = "app/htp/frmCukaiMemoBaucer.jsp";
	    	
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

	    	Tblrujpejabatjkptg pejabatHantar = FrmUtilData.getPejabatJKPTG(null, "16",null, "Ketua Pengarah");	    	
	    	this.context.put("infoHantar", pejabatHantar);
	    	
	    	Vector<?> vecNegeri=null;
	    	vecNegeri = ekptg.helpers.DB.getNegeri("16");
			Tblrujnegeri h = (Tblrujnegeri) vecNegeri.get(0);
	    	this.context.put("infoNegeri", h);

	    	
	    	Tblrujpejabatjkptg pejabatPengirim = (Tblrujpejabatjkptg)FrmUtilData.getPejabatJKPTG(null, (String)permohonan.get("idnegeri"),null, "Pengarah".toUpperCase());
	    	this.context.put("infoPengirim", pejabatPengirim);
	    	
	    	this.context.put("permohonanInfo", permohonan);
	    }else if("surat".equals(submit)){//senarai surat
	    	template_name = "app/htp/frmSenaraiSuratHTP.jsp";

			String idPermohonan = getParam("idpermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
		    senaraiSurat(userId);
		   	this.context.put("senaraiSurat",vecSenaraiSurat);
 
	    }else if("memopenawaran".equals(submit)){//senarai surat
	    	template_name = "app/htp/frmPajakanMemoPenawaran.jsp";

			String idPermohonan = getParam("idpermohonan");
			System.out.println(this.className+":doTemplate2::surat||idPermohonan:::"+idPermohonan);
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
		    this.context.put("permohonanInfo", permohonan);
		    
	    } else{		
    		template_name = PATHTP+"frmCukaiSenaraiFailSurat.jsp";
    		
			getParam("NamaFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			//ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	//list = FrmSenaraiFailPembelianData.getList();
    		list = FrmUtilData. getSenaraiFailByUrusan("11", "", "",idNegeri);
 
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
		   	
			//this.context.put("senaraifail",list);
			setupPage(session,action,list);
		}
        return template_name;
    }
	
	/*public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("senaraiFail",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	} */

	private void senaraiSurat(String login) throws Exception{
		vecSenaraiSurat = FrmUtilData.getLaporanMengikutUrusan(idSubUrusan,null,"T");	
	}

}
