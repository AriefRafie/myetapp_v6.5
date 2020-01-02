package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodBatalHakmilikData;

public class FrmRekodBatalHakmilik extends AjaxBasedModule{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmRekodBatalHakmilik.class);
	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");//1st level
		log.info("submit :" +submit);
		
		String action = getParam("action");//2nd		
		this.context.put("action",action);
		log.info("action :" +action);
		
		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);
		log.info("firstAction :" +firstAction);
		
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		log.info("nextAction :" +nextAction);
		
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
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
		String idJenisHakmilikAsal = getParam("socJenisHakmilikAsal");
		if (idJenisHakmilikAsal == null || idJenisHakmilikAsal.trim().length() == 0){
			idJenisHakmilikAsal = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}

		String idLot = getParam("socJenisLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String noFail = getParam("txtNoFail");
		String noHakmilik = getParam("txtNoHakmilikC");
		String noWarta = getParam("txtNoWarta");
		String noLot = getParam("txtNoLotC");
		
		Vector list =null;
		Vector listSambungan = null;
		String noHakmilikAsal = getParam("txtNoHakmilik");
		System.out.println("no Hakmilik Asal "+noHakmilikAsal);
		HttpSession session = this.request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		
		//VIEW SENARAI HAKMILIK DAN RIZAB
		if("".equals(firstAction))		
		{
		   vm = "app/htp/frmRekodSenaraiHakmilikBatal.jsp";

		   list = view_modeCarianBatalFailHakmilik(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
		   this.context.put("SenaraiBatal", list);
		   this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		   this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
		   this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		   this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));  
		   this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		   this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		   this.context.put("txtNoFail", "");
		   this.context.put("txtNoHakmilikC", "");
		   this.context.put("txtNoLotC", "");
		   setupPage(session,action,list);
		}
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		if("carianHakmilikRizab".equals(firstAction)){

		   vm = "app/htp/frmRekodSenaraiHakmilikBatal.jsp";
						
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		    	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
		    	this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		    	this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
		    	this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	list = view_modeCarianBatalFailHakmilik(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
		    	this.context.put("txtNoFail", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);;
		    	this.context.put("txtNoLotC", noLot);
		    	this.context.put("SenaraiBatal", list);
		    	setupPage(session,action,list);
		    	
			if ("doChangeState".equals(nextAction)) {
	
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		    	this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				list = view_modeCarianBatalFailHakmilik(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
		    	this.context.put("txtNoFail", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoLotC", noLot);
		    	this.context.put("SenaraiBatal", list);		
		    	setupPage(session,action,list);
		    }
			else
			if ("doChangeDaerah".equals(nextAction)) {
		    	
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		    	this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), ""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				list = view_modeCarianBatalFailHakmilik(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);
		    	this.context.put("txtNoFail", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoLotC", noLot);
		    	this.context.put("SenaraiBatal", list);			
		    	setupPage(session,action,list);
		    }		   	
		   	
		}	
		else
			//VIEW HAKMILIK BY ID
			if ("paparDetailHakmilik".equals(firstAction))
			{
				vm = "app/htp/frmRekodHakmilikBatal.jsp";
				//vm = "app/htp/newDesign3.jsp";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				
				// VIEW MAKLUMAT FAIL (MASTER) BY ID
				view_modeMaklumatFail(session);

				// VIEW MAKLUMAT HAKMILIK BY ID
				view_modeHakmilikBatal(session,submit);
				
				//LIST HAKMILIK SAMBUNGAN
				listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
				this.context.put("listSambungan",listSambungan);
						
			 }	
		return vm;
	}
	// view_modeSenaraiHakmilikSambungan
	private Vector view_modeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
		return FrmRekodBatalHakmilikData.getSenaraiHakmilikSambungan(noHakmilikAsal);	
		
	}
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
	private Vector view_modeCarianBatalFailHakmilik(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi) throws Exception {
		return FrmRekodBatalHakmilikData.getCarianSenaraiHakmilikBatalById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi);		
	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFail(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idHakmilik"));	
		Vector list =null;
		list = FrmRekodBatalHakmilikData.getPaparMaklumatFailById(id);
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
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
	}
    // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
	private void view_modeHakmilikBatal(HttpSession session,String submit) throws Exception {
		int id = Integer.parseInt(getParam("idHakmilik"));
		Vector list =null;
		list = FrmRekodBatalHakmilikData.getPaparBatalHakmilikById(id);
		Hashtable hHakmilik = (Hashtable) list.get(0);

		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("txdTarikhTerima", (String)hHakmilik.get("tarikhTerima"));
		this.context.put("txdTarikhDaftar", (String)hHakmilik.get("tarikhDaftar"));
		this.context.put("txtCukaiTahun", (String)hHakmilik.get("cukai"));
		this.context.put("txtLokasi", (String)hHakmilik.get("lokasi"));
        this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
	    this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
	    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\"  class=\"disabled\""));
	    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
		//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));		
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
		this.context.put("kodJenis", (String)hHakmilik.get("kodJenis"));
		this.context.put("noHakmilik", (String)hHakmilik.get("noHakmilik"));
		this.context.put("txtCukaiTerkini",(String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtNoBangunan",(String)hHakmilik.get("noBangunan"));
		this.context.put("txtNoTingkat",(String)hHakmilik.get("noTingkat"));
		this.context.put("txtNoPetak",(String)hHakmilik.get("noPetak"));
		this.context.put("txtNoPelan", (String)hHakmilik.get("noPelan"));
		this.context.put("txtTempoh", (String)hHakmilik.get("tempoh"));
		this.context.put("txtSyarat", (String)hHakmilik.get("syarat"));
		this.context.put("txtHakmilikAsal", (String)hHakmilik.get("hakmilikAsal"));
		this.context.put("txtNoFailJopa", (String)hHakmilik.get("noFailJopa"));
		this.context.put("txtTarafHakmilik", (String)hHakmilik.get("tarafHakmilik"));
		this.context.put("txdTarikhLuput", (String)hHakmilik.get("tarikhLuput"));
		this.context.put("txtCukaiTerkini", (String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtKegunaanTanah", (String)hHakmilik.get("kegunaanTanah"));
		this.context.put("txtLuas", (String)hHakmilik.get("luas"));
		this.context.put("txtNoPu", (String)hHakmilik.get("noPu"));
		this.context.put("txdTarikhWarta",(String)hHakmilik.get("tarikhWarta"));
		this.context.put("txtNoWarta",(String)hHakmilik.get("noWarta"));
		this.context.put("txtNoRizab",(String)hHakmilik.get("noRizab"));
		this.context.put("txdTarikhRizab",(String)hHakmilik.get("tarikhRizab"));
		this.context.put("txtKawasanRizab",(String)hHakmilik.get("kawasanRizab"));
		this.context.put("txtNoSyit",(String)hHakmilik.get("noSyit"));
		this.context.put("txtSekatan",(String)hHakmilik.get("sekatan"));
		this.context.put("txtHakmilikBerikut",(String)hHakmilik.get("hakmilikBerikut"));
		this.context.put("socStatus",(String)hHakmilik.get("socStatus"));
		this.context.put("socTaraf", (String)hHakmilik.get("socTaraf"));
		this.context.put("socRizab", (String)hHakmilik.get("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
		this.context.put("socLuas",(String)hHakmilik.get("idLuasLama"));
		this.context.put("txtLuasLama",(String)hHakmilik.get("luasLama"));
		this.context.put("txtLuas",(String)hHakmilik.get("luasConvert"));
		this.context.put("txtKemAgenTerkini",(String)hHakmilik.get("txtKemAgenTerkini"));
		this.context.put("txtNoHakmilikAsal",(String)hHakmilik.get("txtNoHakmilikAsal"));	
	}
	// SETUP PAGING
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
			this.context.put("SenaraiBatal",paging.getPage(page));
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

