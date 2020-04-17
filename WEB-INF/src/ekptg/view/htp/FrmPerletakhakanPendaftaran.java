package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

public class FrmPerletakhakanPendaftaran extends AjaxBasedModule {
	/**
	 * 
	 */
	private final String PATH = "app/htp/perletakhakan/fail/";
	private static final long serialVersionUID = -8487877676979529353L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPerletakhakanPendaftaran.class);
	FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view
	private FrmRekodUtilData frmRekodUtilData = null;
	private final String IDURUSAN = "5";
	private HtpPermohonan htpPermohonan = null;
	private IHTPFail iHTPFail = null;  
	String isCarian = "tidak";
    String idPermohonan = "";

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();	
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		String vm="";		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action"); //untuk setup paging je		
		String idFail = getParam("idFail");		
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idSuburusanStatusFail = getParam("idSuburusanStatusFail");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		
		// CARIAN
		String idNegeriC = getParam("socNegeriC");
		if (idNegeriC == null || idNegeriC.trim().length() == 0){
			idNegeriC = "99999";
		}
		String idDaerahC = getParam("socDaerahC");
		if (idDaerahC == null || idDaerahC.trim().length() == 0){
			idDaerahC = "99999";
		}
		String idMukimC = getParam("socMukimC");
		if (idMukimC == null || idMukimC.trim().length() == 0){
			idMukimC = "99999";
		}
		String idkementerianC = getParam("socKementerianC");
		if (idkementerianC == null || idkementerianC.trim().length() == 0){
			idkementerianC = "99999";
		}
		String idAgensiC = getParam("socAgensiC");
		if (idAgensiC == null || idAgensiC.trim().length() == 0){
			idAgensiC = "99999";
		}
				
		//PENDAFTAAN
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idkementerian = getParam("socKementerian");
		if (idkementerian == null || idkementerian.trim().length() == 0){
			idkementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		
		Vector<?> list = null;
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		context.remove("senaraihakmilikrizab");
		//SENARAI PENDAFTARAN PERLETAHAKAN
		if("".equals(actionPerletakhakan)){
			vm = "app/htp/frmSenaraiFailPerletakhakan.jsp";
		   	   
			logic.getSenaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,(String)session.getAttribute("_ekptg_user_id"));
			isCarian = getParam("txtcarian");
			myLog.info("isCarian::"+isCarian);
			if(isCarian.equals("ya")){
				//list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,null);		
				logic.getSenaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,null);
				isCarian = "ya";		
			}
			list = logic.getListFailPerletakhakan();
			this.context.put("SenaraiFail", list);
			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeri();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC), " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  style=\"width:400\""));
			    
			setupPage(session,action,list);
		
		}else if ("tambah".equals(actionPerletakhakan)) {	//TAMBAH PENDAFTARAN PERLETAHAKAN			
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
			
			this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeNegeri();\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah), "", ""));
		    this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian), " onChange=\"doChangeKementerian();\" "));
		    this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idkementerian, Utils.parseLong(idAgensi), "  "));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong(idSuburusan),""));
			
			this.context.put("txtTajuk",getParam("txtTajuk"));
			this.context.put("noFail","");			
			this.context.put("txtNoFailKJP", getParam("txtNoFailKJP"));
			if ("".equals(getParam("txdTarikhSurKJP"))){
				this.context.put("txdTarikhSurKJP", lebah.util.Util.getDateTime(new Date(),"dd/MM/yyyy"));
			} else {
				this.context.put("txdTarikhSurKJP",getParam("txdTarikhSurKJP"));
			}			
			this.context.put("txtNoFailLain", getParam("txtNoFailLain"));
			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
		    this.context.put("semakclass", new FrmSemakan());		
	   
		//SIMPAN BARU PENDAFTARAN PERLETAHAKAN BY ID_FAIL
		}else if ("simpanBaru".equals(actionPerletakhakan)){
		   //vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
		   idFail = logic.save(getParam("txtTajuk"),getParam("txtNoFailKJP"),getParam("txdTarikhSurKJP"),getParam("txtNoFailLain"),idkementerian, idAgensi,idNegeri,idDaerah, idSuburusan, session);		   
		   htpPermohonan = getIHTPFail().findPermohonan(idFail, "","");
		   AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+htpPermohonan.getPermohonan().getPfdFail().getNoFail()+"] DITAMBAH ");
		   idPermohonan = logic.getIdPermonanByIdFail(idFail);
		   
		   String[] cbsemaks = this.request.getParameterValues("sbcSemakan");
		   FrmSemakan frmSemak = new FrmSemakan();
		   frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		   if (cbsemaks != null) {
			   for (int i = 0; i < cbsemaks.length; i++) {
				   frmSemak = new FrmSemakan();
				   frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
			   }
		   }
		   
		   //VIEW BALIK LEPAS SIMPAN
		   this.context.put("readonly", "readonly");
		   this.context.put("mode", "view");
		   this.context.put("inputTextClass", "disabled");
		   viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);  
	   
	   // PAPAR PENDAFTARAN PERLETAHAKAN BY ID_FAIL
		} else if ("papar".equals(actionPerletakhakan)) {		
			//vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
			this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	
        	//check hakmilik has been transfer to rekod
        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
        	this.context.put("isTransfered", isTransfered);
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);	
        	
			Vector senaraiHakmilikRizab = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
			context.put("senaraihakmilikrizab",senaraiHakmilikRizab);
			
	   //KEMASKINI PENDAFTARAN PERLETAHAKAN BY ID_FAIL
		} else if("kemaskini".equals(actionPerletakhakan)){		
			//vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
			this.context.put("mode", "update");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	Vector maklumatPermohonan = null;
       		maklumatPermohonan = new Vector();
       		list = new Vector();
    		logic.setMaklumatPermohonan(idFail);
    		maklumatPermohonan = logic.getMaklumatPermohonan();
    		Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);

    		this.context.put("txtTajuk", getParam("txtTajuk") == null ?  hashPermohonanDB.get("txtTajuk") : getParam("txtTajuk"));
    		this.context.put("noFail", hashPermohonanDB.get("noFail"));
    		this.context.put("txtNoFailKJP", getParam("txtNoFailKJP") == null ?  hashPermohonanDB.get("txtNoFailKJP") : getParam("txtNoFailKJP"));
    		this.context.put("txdTarikhSurKJP", getParam("txdTarikhSurKJP") == null ?  hashPermohonanDB.get("txdTarikhSurKJP") : getParam("txdTarikhSurKJP"));
    		this.context.put("txtNoFailLain", getParam("txtNoFailLain") == null ?  hashPermohonanDB.get("txtNoFailLain") : getParam("txtNoFailLain"));
    		this.context.put("idFail", hashPermohonanDB.get("idFail"));
    		this.context.put("idPermohonan", hashPermohonanDB.get("idPermohonan"));
    		this.context.put("idHtpPermohonan",hashPermohonanDB.get("idHtpPermohonan"));
    		this.context.put("idSuburusanStatusFail",hashPermohonanDB.get("idSuburusanStatusFail"));
    		
    		this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
    	    this.context.put("semakclass", new FrmSemakan());
        	
    	    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashPermohonanDB.get("id_negeri")), "disabled", " class=\"disabled\""));
    	    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("id_negeri"),"socDaerah", Long.parseLong((String) hashPermohonanDB.get("id_daerah")), "disabled", " class=\"disabled\" "));
    		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong((String) hashPermohonanDB.get("id_kementerian")), "disabled", " class=\"disabled\" style=\"width:400\""));
    		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\" style=\"width:400\""));
    		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
	
	   //UPDATE PENDAFTARAN PERLETAKHAKAN BY ID_FAIL
		} else if("edit".equals(actionPerletakhakan)){
			//vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
			if(postDB){			
				logic.update(getParam("txtTajuk"),
					getParam("txtNoFailKJP"), getParam("txdTarikhSurKJP"),
					getParam("txtNoFailLain"), idPermohonan, idHtpPermohonan, session);
			}
			
			String[] cbsemaks = this.request.getParameterValues("sbcSemakan");
			FrmSemakan frmSemak = new FrmSemakan();
			frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
			if (cbsemaks != null) {
				for (int i = 0; i < cbsemaks.length; i++) {
					frmSemak = new FrmSemakan();
					frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
				}
			}
			   
			//VIEW BALIK LEPAS UPDATE
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
		
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail); 
		
		}else if("sahkan".equals(actionPerletakhakan)){
			//vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
			new FrmPerletakhakanMaklumatData().transferToRecord(idFail);
			this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
        	this.context.put("isTransfered", isTransfered);
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);
        	
        	
		}
		
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHtpPermohonan", idHtpPermohonan);
		this.context.put("idSuburusanStatusFail", idSuburusanStatusFail);
		this.context.put("kodKementerian", getParam("kodKementerian"));
		this.context.put("kodNegeri", getParam("kodNegeri"));
		this.context.put("iscarian", isCarian);
		return vm;
	  
   }
	//********************************* SENARAI METHOD *******************************************************************
	public void setupPage(HttpSession session,String action,Vector list) {
		
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
				this.context.put("SenaraiFail",paging.getPage(page));
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
	}
	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL
	private void viewPendaftaranPerletakhakanByIdFail(String actionPerletakhakan,String idFail) throws Exception {
		
		Vector maklumatPermohonan = null;
   		maklumatPermohonan = new Vector();
   		Vector list = null;
   		list = new Vector();
		logic.setMaklumatPermohonan(idFail);
		maklumatPermohonan = logic.getMaklumatPermohonan();
		Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);
		idPermohonan = String.valueOf(hashPermohonanDB.get("idPermohonan"));
		this.context.put("txtTajuk", hashPermohonanDB.get("txtTajuk"));
		this.context.put("noFail", hashPermohonanDB.get("noFail"));
		this.context.put("txtNoFailKJP", hashPermohonanDB.get("txtNoFailKJP"));
		this.context.put("txdTarikhSurKJP", hashPermohonanDB.get("txdTarikhSurKJP"));
		this.context.put("txtNoFailLain", hashPermohonanDB.get("txtNoFailLain"));
		this.context.put("idFail", hashPermohonanDB.get("idFail"));
		this.context.put("idPermohonan", hashPermohonanDB.get("idPermohonan"));
		this.context.put("idHtpPermohonan",hashPermohonanDB.get("idHtpPermohonan"));
		this.context.put("idSuburusanStatusFail",hashPermohonanDB.get("idSuburusanStatusFail"));
		
		this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
	    this.context.put("semakclass", new FrmSemakan());
		
	    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashPermohonanDB.get("id_negeri")), "disabled", " class=\"disabled\""));
	    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("id_negeri"), "socDaerah", Long.parseLong((String) hashPermohonanDB.get("id_daerah")), "disabled", " class=\"disabled\""));
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong((String) hashPermohonanDB.get("id_kementerian")), "disabled", " class=\"disabled\" style=\"width:400\""));
		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\" style=\"width:400\""));
		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
		this.context.put("txdTarikhDaftar", hashPermohonanDB.get("tarikhDaftar"));

	}	
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
		
	}

		
}