package ekptg.view.online.htp.perletakhakan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmHakmilikPerletakhakanData;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;

public class FrmPerletakhakanPendaftaranOnline extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1984680735857853555L;
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.perletakhakan.FrmPerletakhakanPendaftaranOnline.class);
	private FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view
	private FrmHakmilikPerletakhakanData perletakhakanData1 = new FrmHakmilikPerletakhakanData();
	private IPenggunaKementerian iPengguna = null;
	private UserKementerian uk = null;
	private final String PATH="app/htp/perletakhakan/online/";
	private Vector<?> list1 = null;
	private Vector beanHeader1 = null;
	private Vector listHakmilik1 = null;
	private Vector beanHakmilik1 = null;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();		
		Boolean postDB = false;
		String vm="";
		
		try{		
				
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action"); //untuk setup paging je
		String mode  = getParam("mode");
		
		String idFail = getParam("idFail");		
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idSuburusanStatusFail = getParam("idSuburusanStatusFail");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		String id_kementerian = "";
		String idUser = (String)session.getAttribute("_ekptg_user_id");	
		String idHakmilikurusan = getParam("idHakmilikurusan");
		// CARIAN
		myLog.info(actionPerletakhakan+"---"+mode);
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
		
		if (id_kementerian == null || id_kementerian.trim().length() == 0){
			uk = getIPengguna().getKementerian(idUser);
			if(uk == null)
				throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
		
			id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
			myLog.info("+++++"+id_kementerian);
		}
		
		Vector list = null;
		//SENARAI PENDAFTARAN PERLETAHAKAN
		if("".equals(actionPerletakhakan)){
			
			vm = PATH+"frmSenaraiFailPerletakhakan2.jsp";
			myLog.info("SenaraiFail ::" +vm);
			logic.getSenaraiFail2(getParam("txtNoFail"), getParam("txtTajukFail"), id_kementerian, idAgensiC, idNegeriC, idDaerahC, idMukimC);
			list = logic.getListFailPerletakhakan();
			this.context.put("SenaraiFail", list);
			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeri();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(id_kementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", id_kementerian, Utils.parseLong(idAgensiC), "  "));
			    
			setupPage(session,action,list);
		}
		else
        //TAMBAH PENDAFTARAN PERLETAHAKAN
		if ("tambah".equals(actionPerletakhakan)) {
			myLog.info("tambah ::" +vm);
			
//			vm = PATH+"frmPerletakhakanMaklumatFail.jsp";
//			this.context.put("mode", "new");
//        	this.context.put("readonly", "");
//        	this.context.put("inputTextClass", "");
//			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeNegeri();\" "));
//			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah), "", ""));
//		    this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idkementerian), " onChange=\"doChangeKementerian();\" "));
//		    this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idkementerian, Utils.parseLong(idAgensi), "  "));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("5","socSuburusan", Long.parseLong(idSuburusan),""));
//			
//			this.context.put("txtTajuk",getParam("txtTajuk"));
//			this.context.put("noFail","");			
//			this.context.put("txtNoFailKJP", getParam("txtNoFailKJP"));
//			if ("".equals(getParam("txdTarikhSurKJP"))){
//				this.context.put("txdTarikhSurKJP", lebah.util.Util.getDateTime(new Date(),"dd/MM/yyyy"));
//			} else {
//				this.context.put("txdTarikhSurKJP",getParam("txdTarikhSurKJP"));
//			}			
//			this.context.put("txtNoFailLain", getParam("txtNoFailLain"));
//			
//			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPerletakhakanSema"));
//		    this.context.put("semakclass", new FrmSemakan());		
	   
		}else 		
			//************************************************************************************************************************************
		if ("maklumathakmilik".equals(actionPerletakhakan)){
			
			//vm = PATH+"frmPerletakhakanSenaraiHakmilikOnline.jsp";
			vm = PATH+"frmPerletakhakanTabHakmilikOnline2.jsp";
			//myLogger.info("maklumathakmilik :: "+vm);
			//HEADER
			beanHeader1 = new Vector();
			perletakhakanData1.setMaklumatHeader(idFail);
			beanHeader1 = perletakhakanData1.getBeanHeader();
			if (beanHeader1.size() != 0){
				Hashtable hashHeader = (Hashtable) perletakhakanData1.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader1);
			this.context.put("mode", "1");
			
			//SENARAI HAKMILIK
			perletakhakanData1.setMaklumatSenaraiHakmilik(idPermohonan);
	    	listHakmilik1 = perletakhakanData1.getSenaraiHakmilik();
	    	this.context.put("Listhakmilik", listHakmilik1);
	    	
	    	if ("viewHakmilik".equals(mode)){
	    		myLog.info("viewHakmilik");
	    		this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("mode", "viewHakmilik");
				
	    		beanHakmilik1 = new Vector();
	    		perletakhakanData1.setMaklumatHakmilik(idHakmilikurusan);
	    		beanHakmilik1 = perletakhakanData1.getBeanHakmilik();
	    		
	    		if(beanHakmilik1.size()!= 0){
	    			Hashtable hashPermohonanDB = (Hashtable) beanHakmilik1.get(0);

	    			this.context.put("txtLokasi", hashPermohonanDB.get("LOKASI"));
	    			this.context.put("txtCukaiSemasa", hashPermohonanDB.get("CUKAI_TERKINI"));
	    			this.context.put("txtSyaratNyata", hashPermohonanDB.get("SYARAT"));
	    			this.context.put("txtNoPelan", hashPermohonanDB.get("NO_PELAN"));
	    			this.context.put("txtNoStrata", "");
	    			this.context.put("txtLuasBersamaan", hashPermohonanDB.get("LUAS_BERSAMAAN"));
	    			this.context.put("txtSekatan", hashPermohonanDB.get("SEKATAN"));
	    			this.context.put("txtLot", hashPermohonanDB.get("NO_LOT"));
	    			this.context.put("txtLuas", hashPermohonanDB.get("LUAS"));
	    			this.context.put("txtNoSyit", hashPermohonanDB.get("NO_SYIT"));
	    			this.context.put("txtNoHakmilik", hashPermohonanDB.get("NO_HAKMILIK"));
	    			
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong((String) hashPermohonanDB.get("ID_NEGERI")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri((String) hashPermohonanDB.get("ID_NEGERI"),"socDaerah",Long.parseLong((String) hashPermohonanDB.get("ID_DAERAH")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah((String) hashPermohonanDB.get("ID_DAERAH"),"socMukim",Long.parseLong((String) hashPermohonanDB.get("ID_MUKIM")),"disabled", " class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong((String) hashPermohonanDB.get("ID_JENISHAKMILIK")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Long.parseLong((String) hashPermohonanDB.get("ID_KATEGORI")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Long.parseLong((String) hashPermohonanDB.get("ID_LUAS")),"disabled"," class=\"disabled\" style=\"width:200px\""));
					this.context.put("selectLot", HTML.SelectLot("socLot",Long.parseLong((String) hashPermohonanDB.get("ID_LOT")),"disabled"," class=\"disabled\" style=\"width:200px\""));
	    		}
	    	} 
        	//this.context.put("page1", true);
        	this.context.put("page4", true);

			
		}
	   else
		//SIMPAN BARU PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if ("simpanBaru".equals(actionPerletakhakan)){
		   
		   myLog.info("simpanBaru ::" +vm);
//		   vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
//		   idFail = logic.save(getParam("txtTajuk"),getParam("txtNoFailKJP"),getParam("txdTarikhSurKJP"),getParam("txtNoFailLain"),idkementerian, idAgensi,idNegeri,idDaerah, idSuburusan, session);
//		   
//		   idPermohonan = logic.getIdPermonanByIdFail(idFail);
//		   
//		   String[] cbsemaks = this.request.getParameterValues("sbcSemakan");
//		   FrmSemakan frmSemak = new FrmSemakan();
//		   frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
//		   if (cbsemaks != null) {
//			   for (int i = 0; i < cbsemaks.length; i++) {
//				   frmSemak = new FrmSemakan();
//				   frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
//			   }
//		   }
//		   
//		   //VIEW BALIK LEPAS SIMPAN
//		   this.context.put("readonly", "readonly");
//		   this.context.put("mode", "view");
//		   this.context.put("inputTextClass", "disabled");
//		   viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);  
	   }
	   else
	   // PAPAR PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if ("papar".equals(actionPerletakhakan)) {
		   
			vm = PATH+"frmPerletakhakanMaklumatFail2.jsp";
			myLog.info("papar ::" +vm);
        	this.context.put("mode", "view");
//        	this.context.put("readonly", "readonly");
//        	this.context.put("inputTextClass", "disabled");
//        	this.context.put("mode1", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("page3", true);
        	
        	//check hakmilik has been transfer to rekod
        	boolean isTransfered = new FrmPerletakhakanMaklumatData().isFailTransfered(idFail);
        	this.context.put("isTransfered", isTransfered);
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);
        	
			perletakhakanData1.setMaklumatHeader(idFail);
			beanHeader1 = perletakhakanData1.getBeanHeader();
			if (beanHeader1.size() != 0){
				Hashtable hashHeader = (Hashtable) perletakhakanData1.getBeanHeader().get(0);				
				if (idPermohonan.isEmpty()){
					idPermohonan = hashHeader.get("idPermohonan").toString();
				}
			}
			this.context.put("List", beanHeader1);
		} 
	   else 
	   //KEMASKINI PENDAFTARAN PERLETAHAKAN BY ID_FAIL
	   if("kemaskini".equals(actionPerletakhakan)){
		  
			vm = "app/htp/frmPerletakhakanMaklumatFail.jsp";
			 myLog.info("kemaskini ::" +vm);
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
			myLog.info("edit ::" +vm);
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
			myLog.info("sahkan ::" +vm);
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
		
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("[HTP PERLETAKHAKAN ONLINE SENARAI FAIL] SILA LOGIN SEMULA");
		}
		
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