package ekptg.view.online.htp.perletakhakan;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;

public class FrmPermohonanPerletakhakanPengesahan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8487877676979529353L;
	private static Logger myLogger = Logger.getLogger(ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakanPengesahan.class);
	private final String PATH="app/htp/perletakhakan/";
	FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view
	private final String IDURUSAN = "5";
    String isCarian = "tidak";
	String userId = "";
	private IPenggunaKementerian iPengguna = null;
	private UserKementerian uk = null;
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();		
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		userId = (String)session.getAttribute("_ekptg_user_id");

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
			/*uk = getIPengguna().getKementerian(userId);
			if(uk == null)
				throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
			
			idkementerianC = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
		*/
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
			//idkementerian = "99999";
			idkementerian =idkementerianC;
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
		//SENARAI PENDAFTARAN PERLETAHAKAN
		if("".equals(actionPerletakhakan)){
			vm = PATH+"frmPerletakhakanSenaraiFailPengesahan.jsp";
	   	   
			//logic.getSenaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,(String)session.getAttribute("_ekptg_user_id"));
			logic.getSenaraiFailOnline(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,(String)session.getAttribute("_ekptg_user_id"));
			isCarian = getParam("txtcarian");
			myLogger.info("isCarian::"+isCarian);
			if(isCarian.equals("ya")){
				//list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,null);		
				//logic.getSenaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,null);
				logic.getSenaraiFailOnline(getParam("txtNoFail"), getParam("txtTajukFail"), idkementerianC, idAgensiC, idNegeriC, idDaerahC, idMukimC,(String)session.getAttribute("_ekptg_user_id"));
				isCarian = "ya";		
			}
			list = logic.getListFailPerletakhakan();
			this.context.put("SenaraiFail", list);
			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			//this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC), " onChange=\"doChangeKementerian();\" "));
			//midified by rosli 20100804
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," disabled=\"disabled\"", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			    
			setupPage(session,action,list);
		}
		else
        //TAMBAH PENDAFTARAN PERLETAHAKAN
		if ("tambah".equals(actionPerletakhakan)) {
			
			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
	
			this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeNegeri();\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah), "", ""));
		    //this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian), " onChange=\"doChangeKementerian();\" "));
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian)," disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" "));
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
	   
		} else if ("simpanBaru".equals(actionPerletakhakan)){	//SIMPAN BARU PENDAFTARAN PERLETAHAKAN BY ID_FAIL

			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
		   idFail = logic.saveOnline(getParam("txtTajuk"),getParam("txtNoFailKJP"),getParam("txdTarikhSurKJP"),getParam("txtNoFailLain"),idkementerian, idAgensi,idNegeri,idDaerah, idSuburusan, session);
		   
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
	   }
	   else
	   // PAPAR PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if ("papar".equals(actionPerletakhakan)) {
			
			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	
        	//check hakmilik has been transfer to rekod
        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
        	this.context.put("isTransfered", isTransfered);
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);			
		} 
	   else 
	   //KEMASKINI PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if("kemaskini".equals(actionPerletakhakan)){
			
			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
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
    	    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("id_negeri"),"socDaerah", Long.parseLong((String) hashPermohonanDB.get("id_daerah")), "disabled", " class=\"disabled\""));
    		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong((String) hashPermohonanDB.get("id_kementerian")), "disabled", " class=\"disabled\""));
    		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\""));
    		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
	
		}
	   else
	   //UPDATE PENDAFTARAN PERLETAKHAKAN BY ID_FAIL
		if("edit".equals(actionPerletakhakan)){
			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";

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
		}
		else if("sahkan".equals(actionPerletakhakan)){
			new FrmPerletakhakanMaklumatData().transferToRecord(idFail);
			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
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
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong((String) hashPermohonanDB.get("id_kementerian")), "disabled", " class=\"disabled\""));
		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Long.parseLong((String) hashPermohonanDB.get("id_agensi")), "disabled", " class=\"disabled\""));
		this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong((String)hashPermohonanDB.get("idSuburusan")),"disabled", " class=\"disabled\""));
	}	
	
	private IPenggunaKementerian getIPengguna(){
		if(iPengguna==null){
			iPengguna = new PenggunaKementerianBean();
		}
		return iPengguna;
		
	}
}