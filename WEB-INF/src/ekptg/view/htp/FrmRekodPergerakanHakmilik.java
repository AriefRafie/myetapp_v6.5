package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPergerakanHakmilikData;


public class FrmRekodPergerakanHakmilik extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104047546676113876L;
	private static Logger log = Logger.getLogger(FrmRekodPergerakanHakmilik.class);
	
	public String doTemplate2() throws Exception {

		String vm = "";
		
		String submit = getParam("command");//1st level
		log.info("submit :" +submit);
		
		String action = getParam("action");//2nd		
		this.context.put("action",action);
		log.info("action :" +action);
		
		Vector listPergerakan =null;
		HttpSession session = this.request.getSession();
		
		String idHakmilik = getParam("idHakmilik");
		this.context.put("idHakmilik",idHakmilik);
		
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String idLot = getParam("socJenisLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}				
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String noFail = getParam("txtNoFail");
		String noHakmilik = getParam("txtNoHakmilik");
		String noWarta = getParam("txtNoWarta");
		String noLot = getParam("txtNoLot");
		String idPergerakan = getParam("idPergerakan");
		
		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);
		log.info("firstAction :" +firstAction);
		
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		log.info("nextAction :" +nextAction);
		
		//VIEW SENARAI HAKMILIK DAN RIZAB
		if ("".equals(firstAction))
		{
			vm = "app/htp/frmRekodSenaraiPergerakanHakmilik.jsp";
			listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
			this.context.put("SenaraiHakmilik", listPergerakan);
					
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
			this.context.put("txtNoFail", "");
			this.context.put("txtNoHakmilik", "");
			this.context.put("txtNoWarta", "");
			this.context.put("txtNoLot", "");
			setupPage(session,action,listPergerakan);
		}
		else
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		if("carianHakmilikRizab".equals(firstAction)){
				
			vm = "app/htp/frmRekodSenaraiPergerakanHakmilik.jsp";
				
			// if carian = null
			if(idNegeri.equals("99999") && idDaerah.equals("99999") && idMukim.equals("99999") && noHakmilik.isEmpty()){
					
				listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				this.context.put("txtNoFail", "");
				this.context.put("txtNoHakmilik", "");
				this.context.put("txtNoLot", "");
				this.context.put("SenaraiHakmilik", listPergerakan);
				
			}
			else
			{
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
				this.context.put("txtNoFail",noFail);
				this.context.put("txtNoHakmilik", noHakmilik);
		    	this.context.put("txtNoWarta", noWarta);
		    	this.context.put("txtNoLot", noLot);
				this.context.put("SenaraiHakmilik", listPergerakan);
			}

			setupPage(session,action,listPergerakan);
			if ("doChangeState".equals(nextAction)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				//this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
				this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
				this.context.put("txtNoFail", "");
				this.context.put("txtNoHakmilik", "");
				this.context.put("txtNoWarta", "");
				this.context.put("txtNoLot", "");
			}
			else if ("doChangeDaerah".equals(nextAction)) {
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
					this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
					this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
					this.context.put("txtNoFail", "");
					this.context.put("txtNoHakmilik", "");
					this.context.put("txtNoWarta", "");
					this.context.put("txtNoLot", "");
			}

		}
		else
		//VIEW SENARAI PERGERAKAN HAKMILIK BY ID
		if ("paparDetailPergerakanHakmilik".equals(firstAction))
		{
			vm = "app/htp/frmRekodPergerakanHakmilik.jsp";
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
		
			// VIEW MAKLUMAT FAIL (MASTER) BY ID
			view_modeMaklumatFail(session);
			
			// VIEW MAKLUMAT PERGERAKAN HAKMILIK BY ID
			listPergerakan = view_modeSenaraiPergerakanFail(session);
			this.context.put("SenaraiPergerakan", listPergerakan);
			setupPagePergerakanHakmilikDetail(session,action,listPergerakan);		
		}
		else
		// view page tambah pergerakan
	    if("tambahPergerakanHakmilik".equals(firstAction)){
			this.context.put("readonly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "new");
					
			this.context.put("txtKeterangan","");
			this.context.put("txtKepada","");
			this.context.put("txdTarikhSerah","");
			this.context.put("txtSalinan","");
			this.context.put("socStatusS","");
			this.context.put("txdTarikhKembali","");
			this.context.put("txtCatatan","");
			this.context.put("sorDokumen","");
	    	//vm = "app/htp/frmRekodTambahPergerakan.jsp";
	    	vm = "app/htp/frmRekodPergerakanTambah.jsp";
	    }
		else
		// add new rekod to tblhtppergerakan
		if("tambahPergerakanHakmilikDetail".equals(firstAction)){
	
				Hashtable hPergerakanAdd = new Hashtable();
				hPergerakanAdd.put("idHakmilik", getParam("idHakmilik"));
				hPergerakanAdd.put("keterangan", getParam("txtKeterangan"));
				hPergerakanAdd.put("kepada", getParam("txtKepada"));
				hPergerakanAdd.put("tarikhSerah", getParam("txdTarikhSerah"));
				hPergerakanAdd.put("socStatusS", getParam("socStatusS"));
				hPergerakanAdd.put("bilSalinan", getParam("txtSalinan"));
				hPergerakanAdd.put("txdTarikhKembali", getParam("txdTarikhKembali"));
				hPergerakanAdd.put("txtCatatan", getParam("txtCatatan"));		
				hPergerakanAdd.put("sorDokumen", getParam("sorDokumen"));
				hPergerakanAdd.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
				String idPergerakanBaru = FrmRekodPergerakanHakmilikData.addPergerakan(hPergerakanAdd);
				// view balik lepas insert
				this.context.put("readonly", "readonly=\"readonly\"");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				view_modePergerakanByIdPergerakan(session,idPergerakanBaru);
		    	//vm = "app/htp/frmRekodTambahPergerakan.jsp";
		    	vm = "app/htp/frmRekodPergerakanTambah.jsp";
		}
		else
		// kemaskini pergerakan hakmilik
		if("kemaskiniPergerakanHakmilikDetail".equals(firstAction)){
			
			this.context.put("readonly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");
	    	//vm = "app/htp/frmRekodTambahPergerakan.jsp";
	    	vm = "app/htp/frmRekodPergerakanTambah.jsp";
			
			view_modePergerakanByIdPergerakan(session,idPergerakan);
		}
		else
		// update pergerakan hakmilik
		if("updatePergerakanHakmilikDetail".equals(firstAction)){	
			
	    	//vm = "app/htp/frmRekodTambahPergerakan.jsp";
	    	vm = "app/htp/frmRekodPergerakanTambah.jsp";
			
			Hashtable hPergerakanUpdate = new Hashtable();
			hPergerakanUpdate.put("idPergerakan", getParam("idPergerakan"));
			hPergerakanUpdate.put("idHakmilik", getParam("idHakmilik"));
			hPergerakanUpdate.put("keterangan", getParam("txtKeterangan"));
			hPergerakanUpdate.put("kepada", getParam("txtKepada"));
			hPergerakanUpdate.put("tarikhSerah", getParam("txdTarikhSerah"));
			hPergerakanUpdate.put("socStatusS", getParam("socStatusS"));
			hPergerakanUpdate.put("bilSalinan", getParam("txtSalinan"));
			hPergerakanUpdate.put("txdTarikhKembali", getParam("txdTarikhKembali"));
			hPergerakanUpdate.put("txtCatatan", getParam("txtCatatan"));
			hPergerakanUpdate.put("sorDokumen", getParam("sorDokumen"));
			hPergerakanUpdate.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
			FrmRekodPergerakanHakmilikData.updatePergerakan(hPergerakanUpdate);
			
			// view balik lepas insert
			this.context.put("readonly", "readonly=\"readonly\"");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
			view_modePergerakanByIdPergerakan(session,idPergerakan);
			
		}
		// view pergerakan hakmilik by idpergerakan
		else 
		if("paparPergerakanHakmilikDetail".equals(firstAction)){		
			//vm = "app/htp/frmRekodTambahPergerakan.jsp";
			vm = "app/htp/frmRekodPergerakanTambah.jsp";
			log.info("paparPergerakanHakmilikDetail:idpergerakan = "+idPergerakan);
			view_modePergerakanByIdPergerakan(session,idPergerakan);
		}
	    else
		if(("doChanges".equals(submit)) || ("record_listing".equals(submit))){	
		    vm = "app/htp/frmRekodSenaraiPergerakanHakmilik.jsp";
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("txtNoHakmilik", "");
		    listPergerakan = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
			this.context.put("SenaraiHakmilik", listPergerakan);
		  	setupPage(session,action,listPergerakan);
		}

		log.info(vm);
		return vm;
		
	}
	// VIEW SENARAI FAIL PERGERAKAN BY ID_HAKMILIK
	private Vector view_modeSenaraiPergerakanFail(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idHakmilik"));
		return FrmRekodPergerakanHakmilikData.getPaparSenaraiPergerakanHakmilikById(String.valueOf(getParam("idHakmilik")));
	}
	private void view_modePergerakanByIdPergerakan(HttpSession session,String idPergerakan) throws Exception {
		Vector list =null;
		list = FrmRekodPergerakanHakmilikData.getMaklumatPergerakanById(idPergerakan);
		Hashtable hPergerakanById = (Hashtable) list.get(0);
		
		this.context.put("idPergerakan",getParam("idPergerakan") == "" ? (String)hPergerakanById.get("idPergerakan"):getParam("idPergerakan"));
		this.context.put("txtKeterangan",getParam("txtKeterangan") == "" ? (String)hPergerakanById.get("keterangan"):getParam("txtKeterangan"));
		this.context.put("txtKepada", getParam("txtKepada") == "" ? (String)hPergerakanById.get("kepada"):getParam("txtKepada"));
		this.context.put("txdTarikhSerah", getParam("txdTarikhSerah")=="" ? (String)hPergerakanById.get("tarikhSerahan"): getParam("txdTarikhSerah"));
		this.context.put("txtSalinan",getParam("txtSalinan") =="" ? (String)hPergerakanById.get("bilSalinan"):getParam("txtSalinan"));
		this.context.put("socStatusS",getParam("socStatusS")== "" ? (String)hPergerakanById.get("socStatusS"):getParam("socStatusS"));
		this.context.put("txdTarikhKembali",getParam("txdTarikhKembali") =="" ? (String)hPergerakanById.get("tarikhKembali"):getParam("txdTarikhKembali"));
		this.context.put("txtCatatan",getParam("txtCatatan")== "" ? (String)hPergerakanById.get("catatan"):getParam("txtCatatan"));
		this.context.put("sorDokumen",getParam("sorDokumen")== "" ? (String)hPergerakanById.get("sorDokumen"):getParam("sorDokumen"));
	}
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	

	private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi) throws Exception {
		return FrmRekodPergerakanHakmilikData.getCarianSenaraiHakmilikRizabById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);		
	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFail(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idHakmilik"));
		Vector list =null;
		list = FrmRekodPergerakanHakmilikData.getPaparMaklumatFailById(String.valueOf(getParam("idHakmilik")));
		Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
		this.context.put("selectLot", (String)hMaklumatFail.get("jenisLot"));	
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));	
	}
	// SETUP PAGING FOR LIST SENARAI PERGERAKAN HAKMILIK
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
		this.context.put("SenaraiHakmilik",paging.getPage(page));
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
	// SETUP PAGING FOR LIST SENARAI PERGERAKAN HAKMILIK	
	public void setupPagePergerakanHakmilikDetail(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiHakmilik",paging.getPage(page));
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
}

