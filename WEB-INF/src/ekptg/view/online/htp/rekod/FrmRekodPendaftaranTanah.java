/* EditBy zulfazdliabuas@gmail.com
 * Date : 23/05/2017
 * ADD MAKLUMAT PEMBAGUNAN
 */
package ekptg.view.online.htp.rekod;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.rekod.FrmHakmilikRizabBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.HTPSusulanPembangunanBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.IHakmilikRizab;
import ekptg.model.htp.utiliti.IHTPSusulan;


public class FrmRekodPendaftaranTanah extends AjaxBasedModule {
	private boolean isSambungan = false;
	private FrmRekodUtilData frmRekodUtilData = null;
	private final String PATH="app/htp/";
	private HakmilikInterface hakmilikInterface = null;
	private HakMilik hakmilik = null;
	private HakMilik hakmilikExt = null;
	private IHakmilikRizab iHakmilikRizab = null;
 	private IHtp iHTP = null;  
	private IPenggunaKementerian iPengguna = null;
	private UserKementerian uk = null;
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.rekod.FrmRekodPendaftaranTanah.class);
	private String userId = "";
	private IHTPSusulan iSusulanPembangunan = null;
	
	@Override
	public String doTemplate2() throws Exception {
		String vm = "";		
		HttpSession session = this.request.getSession();
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
		userId = (String)session.getAttribute("_ekptg_user_id");
		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);		
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		String lastAction = getParam("lastAction");	
		this.context.put("lastAction",lastAction);
		String submit = getParam("command");//1st level
		this.context.put("command",submit);
		String action = getParam("action");//2nd		
		//this.context.put("action",action);
		myLog.info("firstAction :" +firstAction+"|nextAction :" +nextAction+"|lastAction :" +lastAction+"|submit :" +submit+"|action :" +action);
		
		Vector list =null;
		Vector listSambungan = null;
		
	    //****************FIRST PAGE INITIALIZATION VALUE **************************
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}
		String idLot = getParam("socJenisLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0 || idNegeri.equals("-1")){
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
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			uk = getIPengguna().getKementerian(userId);
			if(uk == null){
				//throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
				throw new Exception(getIHTP().getErrorHTML("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI"));
		
			}
			idKementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0 || idAgensi.equals("-1")){
			idAgensi = "99999";
		}
		String idStatus = getParam("socStatus");
		if (idStatus == null || idStatus.trim().length() == 0){
			idStatus = "0";
		}
		String noFail = getParam("txtNoFailC");
		String noHakmilik = getParam("txtNoHakmilikC");
		String noWarta = getParam("txtNoWartaC");
		String noLot = getParam("txtNoLotC");		
		//****************SECOND PAGE INITIALIZATION VALUE **************************
		String idNegeriHR = getParam("socNegeriHR");
		if (idNegeriHR == null || idNegeriHR.trim().length() == 0){
			idNegeriHR = "99999";
		}
		String idDaerahHR = getParam("socDaerahHR");
		if (idDaerahHR == null || idDaerahHR.trim().length() == 0){
			idDaerahHR = "99999";
		}
		String idMukimHR = getParam("socMukimHR");
		if (idMukimHR == null || idMukimHR.trim().length() == 0){
			idMukimHR = "99999";
		}
		String idJenisHakmilikHR = getParam("socJenisHakmilikHR");
		if (idJenisHakmilikHR == null || idJenisHakmilikHR.trim().length() == 0){
			idJenisHakmilikHR = "99999";
		}
		String idLotHR = getParam("socLotHR");
		if (idLotHR == null || idLotHR.trim().length() == 0){
			idLotHR = "99999";
		}
		//log.info("jenis lot :" +idLotHR);		
		String idCaraBayar = getParam("socCaraBayar");
		if (idCaraBayar == null || idCaraBayar.trim().length() == 0){
			idCaraBayar = "99999";
		}
		String noHakmilikAsal = getParam("txtNoHakmilik");
		//log.info("noHakmilikAsal :" +noHakmilikAsal);				
		//****************THIRD PAGE INITIALIZATION VALUE **************************
		Vector listPembangunan	=null;
		//Vector listPerihal =null;
		//String luasAsal ="";
		String idHakmilik = getParam("idHakmilik");
		String idHakmilikPerihal = getParam("idHakmilikPerihal");
		this.context.put("idHakmilikPerihal", idHakmilikPerihal);
		String idLuasBanguanan = getParam("socLuasBangunan");
		if (idLuasBanguanan == null || idLuasBanguanan.trim().length() == 0){
			idLuasBanguanan = "99999";
		}
		//String popupSkrin ="";
		//****************FOURTH PAGE INITIALIZATION VALUE **************************
		//Vector listImej	=null;
		String idGambar = getParam("idGambar");
		this.context.put("idGambar", idGambar);			
		/*
		 * @author : Firzan
		 * comment : copy from FrmRekodHakmilikSementara to cater Hakmilik Sambungan
		 * 
		 * */
		//****************FIFTH PAGE INITIALIZATION VALUE **************************
		String idJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
		if (idJenisHakmilikBaru == null || idJenisHakmilikBaru.trim().length() == 0){
			idJenisHakmilikBaru = "99999";
		}
		
		String msg ="Hakmilik "+getParam("txtHakmilikAsal")+" telah dibatalkan. No Hakmilik Sambungan "+getParam("txtHakmilikBerikut")+" telah berjaya didaftarkan";
		this.context.put("msg", msg);
		
		this.context.put("modePopup", "");
		this.context.put("idJenisTanah", idJenisTanah);	
		this.context.put("idStatus", idStatus);	
		String flagAdvSearch = getParam("flagAdvSearch");
		//****************FIRST PAGE PROCESS **************************
		//VIEW SENARAI HAKMILIK DAN RIZAB
		
	    if("paparterperincirizab".equals(submit)){
	    	myLog.info("918:"+submit);
		   vm = PATH+"rekod/frmRekodPendaftaranRizabReadOnly.jsp";
		   this.context.put("rizab_hakmilik_label", "RIZAB");
		   this.context.put("readonly", "readonly");
		   this.context.put("disabled", "disabled");
		   this.context.put("mode", "view");
		   // VIEW HEADER(MASTER) BY ID
           viewMaklumatFail(idHakmilik);					
           // VIEW MAKLUMAT RIZAB BY ID
           viewMaklumatRizab(hakmilik,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
	    
	    }else if ("paparterperincihakmilik".equals(submit)){
	    	String langkah = "0";    	
	    	myLog.info("931:"+submit);
	    	this.context.put("rizab_hakmilik_label", "HAKMILIK");
	    	vm = PATH+"rekod/frmRekodPendaftaranHakmilikReadOnly.jsp";	
	    	this.context.put("readonly", "readonly");
	    	this.context.put("disabled", "disabled");
	    	this.context.put("mode", "view");			
		    // VIEW HEADER (MASTER) BY ID
	    	System.out.println("PRINTTT ======= " + idHakmilik);
	    	viewMaklumatFail(idHakmilik);					
	    	// VIEW MAKLUMAT HAKMILIK BY ID
	    	//view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
	    	viewMaklumatHakmilik(hakmilik,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
		    
			//LIST HAKMILIK SAMBUNGAN
		   if(isSambungan){	 
			   noHakmilikAsal = viewModeHakmilikSambungan(session,submit);
			
			   listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			   this.context.put("listSambungan",listSambungan);
		   }
		   	   	
		   /*** Start addby zul - for view MAKLUMAT PEMBANGUNAN ***/
			myLog.debug("idStatus:"+idStatus);
			String portal_role = (String)session.getAttribute("myrole");
			context.put("portal_role_",portal_role);
	  		if(portal_role.contains("HQPengguna")){
	  			langkah = "24";
			}else if(portal_role.contains("HQPegawai")){
	  			langkah = "25";
			}else if(portal_role.contains("HQPengarah")){
	  			langkah = "26";
			}
			listPembangunan = viewSenaraiPembangunan(idHakmilik,langkah);
			context.put("SenaraiPembangunan", listPembangunan);
			/*** End addby zul ***/			
			
	    }else{
	    	
			if("carianHakmilikRizab".equals(firstAction)){
				vm = PATH+"rekod/frmRekodSenaraiHakmilikRizabReadOnly.jsp";
				flagAdvSearch = "open";			
				myLog.debug("carianHakmilikRizab:flagAdvSearch="+flagAdvSearch);
				this.context.put("flagAdvSearch", flagAdvSearch);

				// IF CARIAN = NULL
				if(idJenisTanah.equals("99999")
					&& idNegeri.equals("99999") 
					&& idDaerah.equals("99999") 
					&& idMukim.equals("99999") 
					&& noHakmilik.isEmpty()){				
				   //list = FrmRekodPendaftaranHakmilikRizabData.getPaparSenaraiHakmilikRizab();
					list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
						, idNegeri, idDaerah, idMukim
						, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
						, idLotHR, noLot, idAgensi, idKementerian, idStatus);

			   	   this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			   	   this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			   	   this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			   	   this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			   	   this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			   	   //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
				   this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			   	   this.context.put("txtNoFailC", "");
			       this.context.put("txtNoHakmilikC", "");
			       this.context.put("txtNoWartaC", "");
			       this.context.put("txtNoLotC", "");
			       this.context.put("SenaraiTanah", list);
			    
			   }else{	//IF ADA CARIAN
			    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			    	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			    	this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			    	this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
			    	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			    	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			    	//list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
					list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
							, idNegeri, idDaerah, idMukim
							, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
							, idLotHR, noLot, idAgensi, idKementerian, idStatus);
			    	this.context.put("txtNoFailC", noFail);
			    	this.context.put("txtNoHakmilikC", noHakmilik);
			    	this.context.put("txtNoWartaC", noWarta);
			    	this.context.put("txtNoLotC", noLot);
			    	this.context.put("SenaraiTanah", list);
			    
			   }
			   	//WHY THIS SETUP PAGE IS HERE?????
			   	//setupPage(session,action,list);
			   	
				if ("doChangeState".equals(nextAction)) {
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					//this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
					   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\"  style=\"width:400\""));
					   this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
					//list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
					list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
							, idNegeri, idDaerah, idMukim
							, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
							, idLotHR, noLot, idAgensi, idKementerian, idStatus);
			    	this.context.put("txtNoFailC", noFail);
			    	this.context.put("txtNoHakmilikC", noHakmilik);
			    	this.context.put("txtNoWartaC", noWarta);
			    	this.context.put("txtNoLotC", noLot);
			    	this.context.put("SenaraiTanah", list);			
			    	
			    }else if ("doChangeDaerah".equals(nextAction)) {
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
					//list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
					list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
							, idNegeri, idDaerah, idMukim
							, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
							, idLotHR, noLot, idAgensi, idKementerian, idStatus);
			    	this.context.put("txtNoFailC", noFail);
			    	this.context.put("txtNoHakmilikC", noHakmilik);
			    	this.context.put("txtNoWartaC", noWarta);
			    	this.context.put("txtNoLotC", noLot);
			    	this.context.put("SenaraiTanah", list);			
			    	
			    } else if ("doChangeKementerian".equals(nextAction)) {
			    	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
					this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
					//list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
					list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
							, idNegeri, idDaerah, idMukim
							, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
							, idLotHR, noLot, idAgensi, idKementerian, idStatus);
			    	this.context.put("txtNoFailC", noFail);
			    	this.context.put("txtNoHakmilikC", noHakmilik);
			    	this.context.put("txtNoWartaC", noWarta);
			    	this.context.put("txtNoLotC", noLot);
				   	this.context.put("SenaraiTanah", list);	
				   	
				 }
				setupPage(session,action,list);
				//return vm;
				
			}else{
				myLog.debug("default page");
				myLog.debug("flagAdvSearch="+flagAdvSearch);
				myLog.debug("idStatus="+idStatus);
				vm = PATH+"rekod/frmRekodSenaraiHakmilikRizabReadOnly.jsp";
			   //flagAdvSearch = "-";
//			   if(idStatus.equals("1"))
//				   list = carianSenaraiHakmilikRizabAktif(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
//			   else
//				   list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
				list = getIHakmilikRizab().getCarianSenaraiHakmilikRizab(idJenisTanah
						, idNegeri, idDaerah, idMukim
						, noFail, idJenisHakmilikBaru, noHakmilik, noWarta
						, idLotHR, noLot, idAgensi, idKementerian, idStatus);

			   this.context.put("SenaraiTanah", list);
			   this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			   this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
			   this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
			   this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));  
			   this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
			   //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			   this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			   this.context.put("txtNoFailC", "");
			   this.context.put("txtNoHakmilikC", "");
			   this.context.put("txtNoWartaC", "");
			   this.context.put("txtNoLotC", "");
			   setupPage(session,action,list);
			   this.context.put("flagAdvSearch", flagAdvSearch);
			}
	    	
	    	
	    }
	    
		this.context.put("flagAdvSearch", flagAdvSearch);
		return vm;
		
	}
	
	private Vector<Hashtable<String,String>> viewSenaraiPembangunan(String idHakmilik,String langkah) throws Exception {
		return getISusulanPembangunan().getMaklumatMengikutLangkah(idHakmilik,langkah);
		
	}
	private IHTPSusulan getISusulanPembangunan(){
		if(iSusulanPembangunan==null){
			iSusulanPembangunan = new HTPSusulanPembangunanBean();
		}
		return iSusulanPembangunan;
				
	}
		
	//****************FIRST PAGE METHOD/FUCTION **************************
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
// 2017/07/27
//	private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus) throws Exception {
//		return FrmRekodPendaftaranHakmilikRizabData.getCarianSenaraiHakmilikRizabById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);		
//	}
	
	//****************FIRST PAGE METHOD/FUCTION **************************
	//by rosli 2010/05/11 SENARAI HAKMILIK YANG AKTIF SAHAJA
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
// 2017/07/27
//	private Vector carianSenaraiHakmilikRizabAktif(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus) throws Exception {
//		return FrmRekodPendaftaranHakmilikRizabData.getCarianSenaraiHakmilikRizabAktif(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);		
//	}
	
	//****************SECOND PAGE METHOD/FUCTION **************************
	// VIEW HEADER BY ID
// 2017/07/27
//	private String view_modeMaklumatFail(HttpSession session) throws Exception {
//		String idHakmilik = getParam("idHakmilik");	
//		Vector list =null;
//
//		//list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
//		list = getHakmilikBean().getPaparMaklumatFailById(idHakmilik);
//		Hashtable hMaklumatFail = (Hashtable) list.get(0);
//		
//		this.context.put("txtFailPTD",(String)hMaklumatFail.get("noFailPtd"));
//		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
//		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
//		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
//		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
//		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
//		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
//		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
//		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
//		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
//		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
//		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
//		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
//		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
//		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
//		this.context.put("pegawaiAkhir", (String)hMaklumatFail.get("pegawaiAkhir"));
//		return (String)hMaklumatFail.get("hakmilikAsal");
//	}
	
    // VIEW MAKLUMAT HAKMILIK BY ID
// 2017/07/27
//	private void view_modeHakmilikRizab(HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
//		String idHakmilik = getParam("idHakmilik");
//		Vector list =null;
//		list = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
//		Hashtable hHakmilik = null;
//		if (list.size() > 0) {
//			hHakmilik = (Hashtable) list.get(0);
//		} else {
//			throw new Exception("Maklumat Rekod Tidak Lengkap");
//		}
//		
//		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
//		this.context.put("statusBatal", (String)hHakmilik.get("socStatus"));
//		this.context.put("txtKodSocJenisHakmilik", (String)hHakmilik.get("kodJenisHakmilik"));
//		
//		if(hHakmilik.get("socStatus").equals("S")||hHakmilik.get("socStatus").equals("B")){
//			isSambungan = true;	
//		}
//		this.context.put("idHakmilikCukai", (String)hHakmilik.get("idHakmilikCukai"));		
//		this.context.put("socStatusTanah", (String)hHakmilik.get("socStatusTanah"));
//    	this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
//    	this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
//		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
//		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));	
//		
//		if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){
//			
//			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), ""," style=\"width:200px\"")); 
//			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")), " style=\"width:200px\" onChange=\"doChangeStateHR();\""));
//	    	this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),""," style=\"width:200px\" onChange=\"doChangeDaerahHR();\""));
//	    	this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR"))," style=\"width:200px\""));		
//	    	this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")), " style=\"width:200px\""));
//	    	this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")), " style=\"width:200px\""));
//	    	//this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style=\"width:200px\""));
//		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
//			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
//			this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
//			
//			if ("doChangeStateHR".equals(lastAction)) {
//				
//				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\" style=\"width:200px\""));
//				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\" style=\"width:200px\""));	
//				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
//			}
//			
//			if ("doChangeDaerahHR".equals(lastAction)) {
//				
//				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\"  style=\"width:200px\""));
//				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\"  style=\"width:200px\""));
//				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
//			}
//		}else if(nextAction.equals("updateDetailHakmilik") 
//				|| nextAction.equals("updateDetailRizab")
//				||nextAction.equals("paparDetailHakmilik")
//				||nextAction.equals("paparDetailRizab")){
//			
//			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), "disabled"," style=\"width:200px\" class=\"disabled\""));
//			//this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
//			//this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
//		    //this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
//			this.context.put("selectNegeriHR",hHakmilik.get("namaNegeriHR"));
//			this.context.put("selectDaerahHR",hHakmilik.get("namaDaerahHR"));
//		    this.context.put("selectMukimHR", hHakmilik.get("namaMukimHR"));
//			//this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
//			this.context.put("selectJenisLotHR", hHakmilik.get("namaLot"));
//		    //this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\" class=\"disabled\""));		    
//		    this.context.put("selectJenisHakmilikHR", hHakmilik.get("kodJenisHakmilikHR"));		    
//		    //this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//			//this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//			//this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
//			this.context.put("selectKategori", hHakmilik.get("namaKategori"));
//			this.context.put("selectRizab", hHakmilik.get("namaJenisRizab"));
//
//		}	
//		
//		this.context.put("txtNoBangunan",getParam("txtNoBangunan") == "" ? (String)hHakmilik.get("noBangunan"):getParam("txtNoBangunan"));
//		this.context.put("txtNoTingkat",getParam("txtNoTingkat") == "" ? (String)hHakmilik.get("noTingkat"):getParam("txtNoTingkat"));
//		this.context.put("txtNoPetak",getParam("txtNoPetak") == "" ? (String)hHakmilik.get("noPetak"):getParam("txtNoPetak"));
//		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
//		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
//		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
//		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
//		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
//		this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
//		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
//		this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
//		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
//		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
//		this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
//		this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
//		this.context.put("txtNoRizab",getParam("txtNoRizab") == "" ? (String)hHakmilik.get("noRizab"):getParam("txtNoRizab"));
//		this.context.put("txdTarikhRizab",getParam("txdTarikhRizab") == "" ? (String)hHakmilik.get("tarikhRizab"):getParam("txdTarikhRizab"));
//		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
//		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
//		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
//		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
//		this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
//		this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
//		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
//		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
//		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
//		this.context.put("socLuas", getParam("socLuas") == "" ? (String)hHakmilik.get("idLuasLama"):getParam("socLuas"));
//		this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));
//		this.context.put("txtLuas",getParam("txtLuas") == "" ? hHakmilik.get("luasConvert"):getParam("txtLuas"));
//		this.context.put("txtNoLot",getParam("txtNoLot") == "" ? (String)hHakmilik.get("noLot"):getParam("txtNoLot"));
//		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("noHakmilikAsal"):getParam("txtNoHakmilikAsal"));
//		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
//		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? "" : getParam("txtHakmilikBerikut"));
//		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
//		this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
//
//	}	
	
	// view_modeSenaraiHakmilikSambungan
// 2017/07/27
//	private Vector view_modeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
//		return FrmRekodPendaftaranHakmilikRizabData.getSenaraiHakmilikSambungan(noHakmilikAsal);			
//	}
	
	//****************THIRD PAGE METHOD/FUCTION **************************
	// VIEW SENARAI PEMBANGUNAN
// 2017/07/27
//	private Vector view_modeSenaraiPembangunan(HttpSession session) throws Exception {
//		String idHakmilik = getParam("idHakmilik");
//		return FrmRekodPembangunanPentadbiranData.getMaklumatPembangunanByIdHakmilik(idHakmilik);
//	}
	
	// VIEW SENARAI PEMBANGUNAN
// 2017/07/27
//	private void view_modeLuasTerkumpul(HttpSession session) throws Exception {
//		// LUAS LUM SUM
//		String idHakmilik = getParam("idHakmilik");
//	   Vector listTerkumpul = FrmRekodPembangunanPentadbiranData.getLuasSemua(idHakmilik);
//	   Hashtable luasTerkumpul = (Hashtable) listTerkumpul.get(0);	
//	   this.context.put("txtBangunan",luasTerkumpul.get("txtBangunan"));
//	   this.context.put("txtJalan",luasTerkumpul.get("txtJalan"));
//	   this.context.put("txtPadang",luasTerkumpul.get("txtPadang"));
//	   this.context.put("txtParking",luasTerkumpul.get("txtParking"));
//	   this.context.put("txtLain",luasTerkumpul.get("txtLain"));
//	   this.context.put("txtJumlahGuna",luasTerkumpul.get("txtJumlahGuna"));
//	   this.context.put("txtBakiBelum",luasTerkumpul.get("txtBakiBelum"));
//	   this.context.put("txtPeratusBelum", (String)luasTerkumpul.get("txtPeratusBelum"));
//	}
	
	//VIEW HAKMILIKPERIHAL BY ID
// 2017/07/27
//	private void view_modePerihalByIdHakmilikPerihal(HttpSession session,String idHakmilikPerihal,String nextAction) throws Exception {
//		Vector list =null;
//		list = FrmRekodPembangunanPentadbiranData.getMaklumatPerihalById(idHakmilikPerihal);
//		Hashtable hPergerakanById = (Hashtable) list.get(0);
//		
//		this.context.put("idHakmilikPerihal",(String)hPergerakanById.get("idHakmilikPerihal"));
//		this.context.put("socJenisBinaan",(String)hPergerakanById.get("jenisBangunan"));
//		this.context.put("txtNoJKR",(String)hPergerakanById.get("noRujukanJKR"));
//		this.context.put("txdTarikhBina",(String)hPergerakanById.get("tarikhBina"));
//		this.context.put("txtHarga", (String)hPergerakanById.get("hargaBina"));
//		this.context.put("socLuas", getParam("socLuas") == "" ? (String)hPergerakanById.get("socLuasBangunan"):getParam("socLuas"));
//		this.context.put("txtLuasLama", (String)hPergerakanById.get("luasLama"));
//		String cekBinaan = (String)hPergerakanById.get("jenisBangunan");
//		if(cekBinaan.equals("B"))
//			this.context.put("txtLuas",(String)hPergerakanById.get("luasB"));
//		else if(cekBinaan.equals("P"))
//			this.context.put("txtLuas",(String)hPergerakanById.get("luasP"));
//		else if(cekBinaan.equals("PR"))
//			this.context.put("txtLuas",(String)hPergerakanById.get("luasPR"));
//		else if(cekBinaan.equals("J"))
//			this.context.put("txtLuas",(String)hPergerakanById.get("luasJ"));
//		else if(cekBinaan.equals("L"))
//			this.context.put("txtLuas",(String)hPergerakanById.get("luasL"));
//		this.context.put("txtCatatan", (String)hPergerakanById.get("catatan"));
//	}
	
	// GET LUAS ASAL DAN BAKI TERKINI
//	private void xview_modeGetLuasAsalDanBaki(HttpSession session,String idHakmilik) throws Exception {		
//	  //Vector list =null;
//	  //list = FrmRekodPembangunanPentadbiranData.getLuasAsal(idHakmilik);
//	  //Hashtable hLuasAsal = (Hashtable) list.get(0);
//	  //this.context.put("txtLuasAsal",(String)hLuasAsal.get("luasAsal"));
//	  //this.context.put("bakiTerkini",(String)hLuasAsal.get("bakiTerkini"));	
//	
//	}
	
	//****************FOURTH PAGE METHOD/FUCTION **************************	
	// VIEW SENARAI IMEJ
// 2017/07/27
//	private Vector view_modeSenaraiImej(HttpSession session) throws Exception {
//		String idHakmilik = getParam("idHakmilik");
//		return FrmRekodPembangunanImejData.getMaklumatImejByIdHakmilik(idHakmilik);
//	}
//	
	//VIEW IMEJ BY ID
// 2017/07/27
//	private void view_modeImejByIdGambar(HttpSession session,String idGambar) throws Exception {	
//		Vector list =null;
//		list = FrmRekodPembangunanImejData.getMaklumatImejById(idGambar);
//		Hashtable hImejById = (Hashtable) list.get(0);
//		
//		this.context.put("idGambar",(String)hImejById.get("idGambar"));
//		this.context.put("idHakmilik",(String)hImejById.get("idHakmilik"));
//		this.context.put("txtRingkas",(String)hImejById.get("ringkasan"));
//		this.context.put("txtPerihalImej", (String)hImejById.get("perihalImej"));
//		this.context.put("txdTarikhKemaskiniImej", (String)hImejById.get("tarikh"));
//	}
	
	// UPLOAD FILE
// 2017/07/27
//	private void uploadFiles() throws Exception {
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//	    Enumeration allparam = request.getParameterNames();
//		String name="";String value="";
//		for (; allparam.hasMoreElements(); ) {
//	        // Get the name of the request parameter
//	        name = (String)allparam.nextElement();
//	        // Get the value of the request parameter
//	        value = request.getParameter(name);
//		    myLog.info(name +"="+value);
//		}
//	    List items = upload.parseRequest(request);
//	    Iterator itr = items.iterator();
//	    while (itr.hasNext()) {
//	    	FileItem item = (FileItem)itr.next();
//		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
//		    	saveData(item);
//		    }
//	    }
//	 }

// 2017/07/27
//	 private void saveData(FileItem item) throws Exception {
//		 Db db = null;
//		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		 Date date = new Date(); 
//		 String currentDate = sdf.format(date);
//		 //convert date before add
//		 String txdTarikhKemaskiniImej = currentDate;
//		 String txdTarikhKemaskini = "to_date('" + txdTarikhKemaskiniImej + "','dd/MM/yyyy')";	
//		 System.out.println("tarikh : "+txdTarikhKemaskini+"currentDate : "+currentDate);
//		 try {
//			 db = new Db();
//			 long idGambar = DB.getNextID("TBLHTPGAMBAR_SEQ");
//			 Connection con = db.getConnection();
//			 con.setAutoCommit(false);
//			 PreparedStatement ps = con.prepareStatement("insert into TBLHTPGAMBAR " +
//	        			"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail,tarikh_kemaskini) " +
//	        			"values(?,?,?,?,?,?,?,sysdate)");
//			 //log.info("insert into TBLHTPGAMBAR " +
//			 //"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail,tarikh_kemaskini) " +
//			 //"values(?,?,?,?,?,?,?,sysdate)");
//
//			 ps.setLong(1, idGambar);
//			 ps.setString(2, getParam("idHakmilik"));
//			 ps.setString(3, getParam("txtPerihalImej").toUpperCase());
//			 ps.setString(4, getParam("txtRingkas").toUpperCase());
//			 ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
//			 ps.setString(6,item.getName());
//			 ps.setString(7,item.getContentType());
//			 ps.executeUpdate();
//			 con.commit();
//		 } finally {
//			      if (db != null) db.close();
//		 }
//	 }
	 
	 public void setupPagePentadbiran(HttpSession session,String action,Vector list) {
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
			this.context.put("SenaraiPerihal",paging.getPage(page));
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
	 
	 	public void setupPageImej(HttpSession session,String action,Vector list) {
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
			this.context.put("SenaraiImej",paging.getPage(page));
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
	 	
	 	/*
	 	 * @author : Firzan
	 	 * comment : this method is copied from frmRekodHakmilikSementara to cater Hakmilik sambungan being
	 	 * 			 joined to Rekod Hakmilik/rizab
	 	 * */
	 	
	 // VIEW MAKLUMAT FAIL BY ID (MASTER)
// 2017/07/27
//		private void view_modeMaklumatFailFrmRekodHakmilikSementara(HttpSession session) throws Exception {
//			String idHakmilik = getParam("idHakmilik");	
//			Vector list =null;
//			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idHakmilik);
//			Hashtable hMaklumatFail = (Hashtable) list.get(0);
//			
//			this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
//			this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
//			this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
//			this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
//			this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
//			this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
//			this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
//			this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
//			this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
//			this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
//			this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
//			this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
//			this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
//			this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
//		}
//		
		// VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
// 2017/07/27
//		private void view_modeHakmilikSementara(HttpSession session) throws Exception {
//			String idHakmilik = getParam("idHakmilik");
//			Vector list =null;
//			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
//			Hashtable hHakmilik = (Hashtable) list.get(0);
//
//			this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
//			this.context.put("txdTarikhTerima", (String)hHakmilik.get("tarikhTerima"));
//			this.context.put("txdTarikhDaftar", (String)hHakmilik.get("tarikhDaftar"));
//			this.context.put("txtCukaiTahun", (String)hHakmilik.get("cukai"));
//			this.context.put("txtLokasi", (String)hHakmilik.get("lokasi"));
//	        this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
//		    this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
//		    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
//		    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\"  class=\"disabled\""));
//		    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
//			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//		    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//		    this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
//			this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
//			this.context.put("kodJenis", (String)hHakmilik.get("kodJenis"));
//			this.context.put("noHakmilik", (String)hHakmilik.get("noHakmilik"));
//			this.context.put("txtCukaiTerkini",(String)hHakmilik.get("cukaiTerkini"));
//			this.context.put("txtNoBangunan",(String)hHakmilik.get("noBangunan"));
//			this.context.put("txtNoTingkat",(String)hHakmilik.get("noTingkat"));
//			this.context.put("txtNoPetak",(String)hHakmilik.get("noPetak"));
//			this.context.put("txtNoPelan", (String)hHakmilik.get("noPelan"));
//			this.context.put("txtTempoh", (String)hHakmilik.get("tempoh"));
//			this.context.put("txtSyarat", (String)hHakmilik.get("syarat"));
//			this.context.put("txtHakmilikAsal", (String)hHakmilik.get("hakmilikAsal"));
//			this.context.put("txtNoFailJopa", (String)hHakmilik.get("noFailJopa"));
//			this.context.put("txtTarafHakmilik", (String)hHakmilik.get("tarafHakmilik"));
//			this.context.put("txdTarikhLuput", (String)hHakmilik.get("tarikhLuput"));
//			this.context.put("txtCukaiTerkini", (String)hHakmilik.get("cukaiTerkini"));
//			this.context.put("txtKegunaanTanah", (String)hHakmilik.get("kegunaanTanah"));
//			this.context.put("txtLuas", (String)hHakmilik.get("luas"));
//			this.context.put("txtNoPu", (String)hHakmilik.get("noPu"));
//			this.context.put("txdTarikhWarta",(String)hHakmilik.get("tarikhWarta"));
//			this.context.put("txtNoWarta",(String)hHakmilik.get("noWarta"));
//			this.context.put("txtNoRizab",(String)hHakmilik.get("noRizab"));
//			this.context.put("txdTarikhRizab",(String)hHakmilik.get("tarikhRizab"));
//			this.context.put("txtKawasanRizab",(String)hHakmilik.get("kawasanRizab"));
//			this.context.put("txtNoSyit",(String)hHakmilik.get("noSyit"));
//			this.context.put("txtSekatan",(String)hHakmilik.get("sekatan"));
//			this.context.put("txtHakmilikBerikut",(String)hHakmilik.get("hakmilikBerikut"));
//			this.context.put("socStatus", getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
//			this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
//			this.context.put("socTaraf",(String)hHakmilik.get("socTaraf"));
//			this.context.put("socRizab", (String)hHakmilik.get("socRizab"));
//			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
//			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
//			this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
//			this.context.put("socLuas",(String)hHakmilik.get("idLuasLama"));
//			this.context.put("txtLuasLama",(String)hHakmilik.get("luasLama"));
//			this.context.put("txtLuas",(String)hHakmilik.get("luasConvert"));		
//			this.context.put("txtNoHakmilikAsal",(String)hHakmilik.get("hakmilikAsal"));		
//		}
//		
		 // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
		private String viewModeHakmilikSambungan(HttpSession session,String submit) throws Exception {
			String idHakmilik = getParam("idHakmilik");
			myLog.info("idHakmilik :"+idHakmilik);
			Vector list =null;
			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
			Hashtable hHakmilik = (Hashtable) list.get(0);

			this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
			this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));
			this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
			this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
			this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
			if(submit.equals("kemaskiniDetailHakmilik")||submit.equals("kemaskiniDetailRizab")){
				//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style='width:200px;'"));
			    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), " style='width:200px;'" ));
				this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
				this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
			
			}
			else
				if(submit.equals("updateDetailHakmilik")|| submit.equals("updateDetailRizab")||submit.equals("paparDetailHakmilik")||submit.equals("paparDetailRizab")){
					//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
				}	
			this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
			this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
			this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
			this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
			this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
			this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
			this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
			this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
			this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
			this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
			this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
			this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
			this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
			this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
			this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
			this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
			this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
			this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
			this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
			this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
			this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));	
			this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtNoHakmilikAsal"));
			this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));			
			return (String)hHakmilik.get("kodJenisHakmilik")+hHakmilik.get("noHakmilik");
			
		}	
		
		  // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
		private void view_modeHakmilikRizab(HttpSession session,String submit) throws Exception {
			String idHakmilik = getParam("idHakmilik");
			myLog.info("idHakmilik :"+id);
			Vector<Hashtable<String,String>> list =null;
			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
			Hashtable<String,String> hHakmilik = (Hashtable<String,String>) list.get(0);

			this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
			this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));
			this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
			this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
			this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
			if(submit.equals("kemaskiniDetailHakmilik")||submit.equals("kemaskiniDetailRizab")){
				//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style='width:200px;'"));
			    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), " style='width:200px;'" ));
				this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
				this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
			}
			else
				if(submit.equals("updateDetailHakmilik")|| submit.equals("updateDetailRizab")||submit.equals("paparDetailHakmilik")||submit.equals("paparDetailRizab")){
					//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
					this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
				}	
			this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
			this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
			this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
			this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
			this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
			this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
			this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
			this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
			this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
			this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
			this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
			this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
			this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
			this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
			this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
			this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
			this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
			this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
			this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
			this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
			this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));	
			this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtNoHakmilikAsal"));
			this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
			
		}	

		// view_modeSenaraiHakmilikSambungan
		private Vector<Hashtable<String,String>> viewModeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
			return FrmRekodPendaftaranHakmilikSementaraData.getSenaraiHakmilikSambungan(noHakmilikAsal);	
			
		}

		
		private IPenggunaKementerian getIPengguna(){
			if(iPengguna==null){
				iPengguna = new PenggunaKementerianBean();
			}
			return iPengguna;
			
		}
		
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
				 this.context.put("SenaraiTanah",paging.getPage(page));
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
		 		  
			private IHtp getIHTP(){
				if(iHTP== null)
					iHTP = new HtpBean();
				return iHTP;
			}

		private String viewMaklumatFail(String idHakmilik) throws Exception {
			hakmilik = (HakMilik)getHakmilikBean().getMaklumatFailById(idHakmilik);
			this.context.put("txtFailPTD",hakmilik.getNoFailPTD());
			this.context.put("txtFailPTG",hakmilik.getNoFailPTG());
			this.context.put("txtTajuk",hakmilik.getPermohonan().getPfdFail().getTajukFail());
			this.context.put("txtNamaKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
			this.context.put("txtNoFailSeksyen",hakmilik.getNoFail());
			this.context.put("txtNamaNegeri", hakmilik.getNegeri().getNamaNegeri());
			this.context.put("txtNamaDaerah", hakmilik.getDaerah().getNamaDaerah());
			this.context.put("txtNamaMukim", hakmilik.getMukim().getNamaMukim());
			this.context.put("txtNamaAgensi",hakmilik.getAgensi().getNamaAgensi());
			this.context.put("txtFailKJP", hakmilik.getNoFailKJP());
			this.context.put("caraPerolehan", hakmilik.getPerolehan());
			return idHakmilik;
				
		}
		
		// VIEW MAKLUMAT(RIZAB) BY ID
		private void viewMaklumatRizab(HakMilik hakmilik,String nextAction,String lastAction
				,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR
				, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
			hakmilikExt = getHakmilikBean().getWarta(String.valueOf(hakmilik.getIdHakmilik()));
			
			this.context.put("idHakmilik", String.valueOf(hakmilik.getIdHakmilik()));
			this.context.put("statusBatal", hakmilikExt.getStatusSah());			
			if(hakmilikExt.getStatusSah().equals("S")||hakmilikExt.getStatusSah().equals("B")){
				isSambungan = true;	
			}
			this.context.put("txtLokasi",getParam("txtLokasi") == "" ? hakmilikExt.getLokasi():getParam("txtLokasi"));
			if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){				
				this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), ""," style=\"width:200px\"")); 
				this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
				
				if ("doChangeStateHR".equals(lastAction)) {					
					this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\" style=\"width:200px\""));
					this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\" style=\"width:200px\""));	
					this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));

				}
				if ("doChangeDaerahHR".equals(lastAction)) {	
					this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\"  style=\"width:200px\""));
					this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\"  style=\"width:200px\""));
					this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
				
				}
			}else if(nextAction.equals("updateDetailHakmilik")|| nextAction.equals("updateDetailRizab")
					||nextAction.equals("paparDetailHakmilik")||nextAction.equals("paparDetailRizab")){				
				this.context.put("selectNegeriHR",hakmilik.getNegeri().getNamaNegeri());
				this.context.put("selectDaerahHR",hakmilik.getDaerah().getNamaDaerah());
			    this.context.put("selectMukimHR", hakmilik.getMukim().getNamaMukim());

			}	
			this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? hakmilikExt.getNoPelan():getParam("txtNoPelan"));
			this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? hakmilikExt.getNoFailJOPA():getParam("txtNoFailJopa"));
			this.context.put("txtLuas",getParam("txtLuas") == "" ? hakmilikExt.getLuasString():getParam("txtLuas"));
			this.context.put("txtNoPu",getParam("txtNoPu") == "" ? hakmilikExt.getNoPU():getParam("txtNoPu"));
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? sdf.format(hakmilikExt.getTarikhWarta()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhWarta()):getParam("txdTarikhWarta"));
			this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? sdf.format(hakmilikExt.getTarikhTerima()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhTerima()):getParam("txdTarikhTerima"));				
			this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? hakmilikExt.getNoWarta():getParam("txtNoWarta"));
			this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? hakmilikExt.getNoSyit():getParam("txtNoSyit"));		
			this.context.put("txdTarikhKemaskini", sdf.format(hakmilikExt.getTarikhKemaskini()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhKemaskini()));
			this.context.put("txtPegawaiAkhir", hakmilikExt.getNamaKemaskini());
			this.context.put("socLuas", getParam("socLuas") == "" ? hakmilikExt.getIdLuas():getParam("socLuas"));
			this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? hakmilikExt.getLuasString():getParam("txtLuasLama"));
			this.context.put("txtLuas",getParam("txtLuas") == "" ? Utils.formatLuas(hakmilikExt.getLuasBersamaan()):getParam("txtLuas"));
			this.context.put("txtNoLot",getParam("txtNoLot") == "" ? hakmilikExt.getRujLot().getKeterangan()+' '+hakmilikExt.getNoLot():getParam("txtNoLot"));
			this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? hakmilikExt.getCatatan():getParam("txtKemAgenTerkini"));
			this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? hakmilikExt.getKegunaan():getParam("txtKegunaanTanah"));
			this.context.put("socStatus",getParam("socStatus") == "" ? hakmilikExt.getStatusSah():getParam("socStatus"));

		}
		
		// VIEW MAKLUMAT(HAKMILIK) BY ID
		private void viewMaklumatHakmilik(HakMilik hakmilik,String nextAction,String lastAction
				,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR
				, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
			hakmilikExt = getHakmilikBean().getHakmilik(String.valueOf(hakmilik.getIdHakmilik()));
			
			this.context.put("idHakmilik", String.valueOf(hakmilik.getIdHakmilik()));
			this.context.put("statusBatal", hakmilikExt.getStatusSah());			
			//	hakmilik sahaja
			this.context.put("txtKodSocJenisHakmilik", hakmilikExt.getRujJenisHakmilik().getKodJenisHakmilik());
			if(hakmilikExt.getStatusSah().equals("S")||hakmilikExt.getStatusSah().equals("B")){
				isSambungan = true;	
			}
			this.context.put("txtLokasi",getParam("txtLokasi") == "" ? hakmilikExt.getLokasi():getParam("txtLokasi"));
			this.context.put("selectNegeriHR",hakmilik.getNegeri().getNamaNegeri());
			this.context.put("selectDaerahHR",hakmilik.getDaerah().getNamaDaerah());
			this.context.put("selectMukimHR", hakmilik.getMukim().getNamaMukim());
		    this.context.put("selectJenisHakmilikHR", hakmilikExt.getRujJenisHakmilik().getKodJenisHakmilik()+" - "+hakmilikExt.getRujJenisHakmilik().getKeterangan());		    
			this.context.put("txtNoHakmilik", hakmilik.getNoHakmilik());
			this.context.put("txtNoBangunan",hakmilikExt.getStrata().getBangunan());
			this.context.put("txtNoTingkat",hakmilikExt.getStrata().getTingkat());
			this.context.put("txtNoPetak",hakmilikExt.getStrata().getPetak());		    			
			this.context.put("txtNoPelan",hakmilikExt.getNoPelan());
			this.context.put("txtTempoh",hakmilikExt.getTempoh());
			this.context.put("txtSyarat",hakmilikExt.getSyarat());		
			this.context.put("txtHakmilikAsal",hakmilikExt.getNoHakmilikAsal());
			this.context.put("txtNoFailJopa",hakmilikExt.getNoFailJOPA());
			//this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
			this.context.put("socTaraf",hakmilikExt.getTaraf());
			this.context.put("txdTarikhLuput",hakmilikExt.getTarikhLuputFormat());
			this.context.put("txtCukaiTerkini",Utils.format2Decimal(hakmilikExt.getHakmilikCukai().getCukaiTerkini()));
			this.context.put("txtLuas",hakmilikExt.getLuasString());
			this.context.put("txtNoPu",hakmilikExt.getNoPU());
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.context.put("txdTarikhDaftar",sdf.format(hakmilikExt.getTarikhDaftar()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhDaftar()));				
			this.context.put("txdTarikhTerima",sdf.format(hakmilikExt.getTarikhTerima()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhTerima()));				
	    	this.context.put("txtCukaiTahun",Utils.format2Decimal(hakmilikExt.getHakmilikCukai().getCukai()));
			this.context.put("txtLokasi",hakmilikExt.getLokasi());
			this.context.put("txdTarikhRizab",hakmilikExt.getTarikhRizabFormat());
			this.context.put("txtNoRizab",hakmilikExt.getNoRizab());
			this.context.put("txtKawasanRizab",hakmilikExt.getKawasanRizab());
			this.context.put("txtNoSyit",hakmilikExt.getNoSyit());	
			
			this.context.put("txtSekatan",hakmilikExt.getSekatan());
			this.context.put("socRizab", hakmilikExt.getRizab());
			//this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txtNoHakmilikAsal",hakmilikExt.getNoHakmilikAsal());
			this.context.put("txtHakmilikBerikut",hakmilikExt.getNoHakmilikBerikut());
			
			this.context.put("txdTarikhKemaskini",sdf.format(hakmilikExt.getTarikhKemaskini()).equals("01/01/1900")?"":sdf.format(hakmilikExt.getTarikhKemaskini()));
			this.context.put("txtPegawaiAkhir",hakmilikExt.getNamaKemaskini());
			this.context.put("socLuas",hakmilikExt.getIdLuas());
			this.context.put("txtLuasLama",hakmilikExt.getLuasString());
			//this.context.put("txtLuas",getParam("txtLuas") == "" ? Utils.formatLuas(hakmilikExt.getLuasBersamaan()):getParam("txtLuas"));
			this.context.put("txtNoLot",hakmilikExt.getRujLot().getKeterangan()+' '+hakmilikExt.getNoLot());
			this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? hakmilikExt.getCatatan():getParam("txtKemAgenTerkini"));
			this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? hakmilikExt.getKegunaan():getParam("txtKegunaanTanah"));
			this.context.put("socStatus",getParam("socStatus") == "" ? hakmilikExt.getStatusSah():getParam("socStatus"));

		}		
			
		private HakmilikInterface getHakmilikBean(){
			if(hakmilikInterface == null){
				hakmilikInterface = new HakmilikBean();
			}
			return hakmilikInterface;
		
		}
		
		
		private IHakmilikRizab getIHakmilikRizab(){
			if (iHakmilikRizab == null){
				iHakmilikRizab = new FrmHakmilikRizabBean();
			}
			return iHakmilikRizab;
		}

}	
