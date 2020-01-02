package ekptg.view.htp.rekod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmRekodPembangunanImejData;
import ekptg.model.htp.FrmRekodPembangunanPentadbiranData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;

public class XFrmRekodPendaftaranTanahNegeri extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
	private final String PATH="app/htp/";
	private IPenggunaKementerian iPengguna = null;
	private InternalUser iu = null;
	
	private static Logger log = Logger.getLogger(ekptg.view.htp.rekod.XFrmRekodPendaftaranTanahNegeri.class);
	String userId = "";
	boolean isSambungan = false;
	FrmRekodUtilData frmRekodUtilData = null;
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
		//log.info("firstAction :" +firstAction);
		
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		//log.info("nextAction :" +nextAction);
		
		String lastAction = getParam("lastAction");	
		this.context.put("lastAction",lastAction);
		//log.info("lastAction :" +lastAction);
		
		String submit = getParam("command");//1st level
		this.context.put("command",submit);
		//log.info("submit :" +submit);
		
		String action = getParam("action");//2nd		
		//this.context.put("action",action);
		log.info("firstAction :" +firstAction+"|nextAction :" +nextAction+"|lastAction :" +lastAction+"|submit :" +submit+"|action :" +action);
		
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
		if (idNegeri == null || idNegeri.trim().length() == 0){
			//idNegeri = "99999";
			iu = InternalUserUtil.getSeksyenId(userId);
			idNegeri = iu.getIdNegeri();
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
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
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
		Vector listPerihal =null;
		String luasAsal ="";
		String idHakmilik = getParam("idHakmilik");
		String idHakmilikPerihal = getParam("idHakmilikPerihal");
		this.context.put("idHakmilikPerihal", idHakmilikPerihal);
		String idLuasBanguanan = getParam("socLuasBangunan");
		if (idLuasBanguanan == null || idLuasBanguanan.trim().length() == 0){
			idLuasBanguanan = "99999";
		}
		String popupSkrin ="";
		//****************FOURTH PAGE INITIALIZATION VALUE **************************
		Vector listImej	=null;
		String idGambar = getParam("idGambar");
		this.context.put("idGambar", idGambar);	
		
		/*
		 * @author : Firzan
		 * comment : copy from FrmRekodHakmilikSementara to cater Hakmilik Sambungan
		 * 
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
		if("".equals(firstAction))	{
			//log.debug("1");
		   vm = PATH+"rekod/frmRekodSenaraiHakmilikRizabReadOnly.jsp";
		   //flagAdvSearch = "-";
		   //asal
		   //list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
		   list = carianSenaraiHakmilikRizabAktif(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
		   this.context.put("SenaraiTanah", list);
		   this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		   //this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
		   this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), "  disabled=\"disabled\" onChange=\"doChangeState();\""));
		   this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		   this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));  
		   this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		   //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\""));
		   this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", ""));
		   this.context.put("txtNoFailC", "");
		   this.context.put("txtNoHakmilikC", "");
		   this.context.put("txtNoWartaC", "");
		   this.context.put("txtNoLotC", "");
		   setupPage(session,action,list);
		   this.context.put("flagAdvSearch", flagAdvSearch);
		   return vm;
		
		}
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		if("carianHakmilikRizab".equals(firstAction)){

			   vm = PATH+"rekod/frmRekodSenaraiHakmilikRizabReadOnly.jsp";
			flagAdvSearch = "open";			
			this.context.put("flagAdvSearch", flagAdvSearch);

		   // IF CARIAN = NULL
		   if(idJenisTanah.equals("99999")&&idNegeri.equals("99999") && idDaerah.equals("99999") && idMukim.equals("99999") && noHakmilik.isEmpty()){
							
		   //view_modeSenaraiFail(session);
		   list = FrmRekodPendaftaranHakmilikRizabData.getPaparSenaraiHakmilikRizab();
			   
		   	   this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		   	   this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		   	   this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " disabled=\"disabled\" onChange=\"doChangeState();\""));
		   	   this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		   	   this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
		   	   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			   //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			   this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
		   	   this.context.put("txtNoFailC", "");
		       this.context.put("txtNoHakmilikC", "");
		       this.context.put("txtNoWartaC", "");
		       this.context.put("txtNoLotC", "");
		       this.context.put("SenaraiTanah", list);
		    
		   }else{	//IF ADA CARIAN
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		    	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " disabled=\"disabled\" onChange=\"doChangeState();\""));
		    	this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		    	this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
		    	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		    	//this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		    	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
		    	this.context.put("txtNoFailC", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoWartaC", noWarta);
		    	this.context.put("txtNoLotC", noLot);
		    	this.context.put("SenaraiTanah", list);
		    
		   }
		   	//WHY THIS SETUP PAGE IS HERE?????
		   	//setupPage(session,action,list);
		   	
			if ("doChangeState".equals(nextAction)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " disabled=\"disabled\" onChange=\"doChangeState();\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
				//   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\"  style=\"width:400\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
		    	this.context.put("txtNoFailC", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoWartaC", noWarta);
		    	this.context.put("txtNoLotC", noLot);
		    	this.context.put("SenaraiTanah", list);			
		    	
		    }else if ("doChangeDaerah".equals(nextAction)) {
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " disabled=\"disabled\" onChange=\"doChangeState();\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim),"",""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
				//this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled=\"disabled\"", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
		    	this.context.put("selectJenisLot",HTML.SelectLot("socJenisLot", Utils.parseLong(idLot), "", ""));
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
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
				list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
		    	this.context.put("txtNoFailC", noFail);
		    	this.context.put("txtNoHakmilikC", noHakmilik);
		    	this.context.put("txtNoWartaC", noWarta);
		    	this.context.put("txtNoLotC", noLot);
			   	this.context.put("SenaraiTanah", list);			
			 }
			setupPage(session,action,list);
			return vm;
		}	
		//****************SECOND PAGE PROCESS **************************
		if("PendaftaranHakmilik".equals(firstAction)){
			this.context.put("jenis_button","2");
			
			// VIEW DETAIL HAKMILIK
			if ("paparDetailHakmilik".equals(nextAction)){
				this.context.put("rizab_hakmilik_label", "HAKMILIK");
			   vm = PATH+"rekod/frmRekodPendaftaranHakmilikReadOnly.jsp";	
			   this.context.put("readonly", "readonly");
			   this.context.put("disabled", "disabled");
			   this.context.put("mode", "view");
				
			    // VIEW HEADER (MASTER) BY ID
			   noHakmilikAsal = view_modeMaklumatFail(session);

			   // VIEW MAKLUMAT HAKMILIK BY ID
			   view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			    
				//LIST HAKMILIK SAMBUNGAN
//				listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
//				this.context.put("listSambungan",listSambungan);
			   if(isSambungan){	 
				   noHakmilikAsal = viewModeHakmilikSambungan(session,submit);
			
				   //LIST HAKMILIK SAMBUNGAN
				   //rosli on 11/05/2010
				
				   listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
				   this.context.put("listSambungan",listSambungan);
			   }
	        
			}else if ("paparDetailRizab".equals(nextAction)){	// VIEW DETAIL RIZAB
 
			   vm = PATH+"rekod/frmRekodPendaftaranRizabReadOnly.jsp";
			   this.context.put("rizab_hakmilik_label", "RIZAB");
			   this.context.put("readonly", "readonly");
			   this.context.put("disabled", "disabled");
			   this.context.put("mode", "view");
						
			   // VIEW HEADER(MASTER) BY ID
               view_modeMaklumatFail(session);
 					
               // VIEW MAKLUMAT RIZAB BY ID
               view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
		    
			}else if ("kemaskiniDetailHakmilik".equals(nextAction)){	//KEMASKINI DETAIL MAKLUMAT HAKMILIK
			    vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
				
				this.context.put("readonly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");

				// VIEW HEADER(MASTER) BY ID
				view_modeMaklumatFail(session);

				// VIEW MAKLUMAT HAKMILIK BY ID
				view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
				
			 }
			 else
			 //KEMASKINI DETAIL MAKLUMAT RIZAB
			 if ("kemaskiniDetailRizab".equals(nextAction)){
				
			    vm = "app/htp/frmRekodPendaftaranRizab.jsp";
				
				this.context.put("readonly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");
				
				// VIEW HEADER(MASTER) BY ID
				view_modeMaklumatFail(session);

				// VIEW MAKLUMAT RIZAB BY ID
				view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			 }
			 else
			 //UPDATE DETAIL MAKLUMAT HAKMILIK
			 if ("updateDetailHakmilik".equals(nextAction)){
				 vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";

				 Hashtable hHakmilikUpdate = new Hashtable();
				 hHakmilikUpdate.put("idHakmilik", getParam("idHakmilik"));
				 hHakmilikUpdate.put("socNegeriHR", getParam("socNegeriHR"));
				 hHakmilikUpdate.put("socDaerahHR", getParam("socDaerahHR"));
				 hHakmilikUpdate.put("socMukimHR", getParam("socMukimHR"));
				 hHakmilikUpdate.put("socJenisHakmilikHR", getParam("socJenisHakmilikHR"));
				 hHakmilikUpdate.put("txtNoHakmilik", getParam("txtNoHakmilik"));	
				 hHakmilikUpdate.put("socLotHR", getParam("socLotHR"));
				 hHakmilikUpdate.put("txtNoLot", getParam("txtNoLot"));
				 hHakmilikUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				 hHakmilikUpdate.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
				 hHakmilikUpdate.put("txtNoBangunan", getParam("txtNoBangunan"));
				 hHakmilikUpdate.put("txtNoTingkat", getParam("txtNoTingkat"));
				 hHakmilikUpdate.put("txtNoPetak", getParam("txtNoPetak"));
				 hHakmilikUpdate.put("txtCukaiTahun", getParam("txtCukaiTahun"));
				 hHakmilikUpdate.put("txtLokasi", getParam("txtLokasi"));	
				 hHakmilikUpdate.put("socLuas", getParam("socLuas"));
				 //LUAS LAMA
				 if(getParam("socLuas").equals("1")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas1")+"KM"));
				 }
				 else
				 if(getParam("socLuas").equals("2")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas1")+"H"));
				 }
				 else
				 if(getParam("socLuas").equals("3")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas1")+"M"));
				 }
				 else
				 if(getParam("socLuas").equals("4")){
					 hHakmilikUpdate.put("txtLuasLama",(getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
				 }
				 else
				 if(getParam("socLuas").equals("5")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas1")+"K"));
				 }
				 else
				 if(getParam("socLuas").equals("7")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas5")+"E"+getParam("txtLuas6")+"D"));
				 }
				 else
				 if(getParam("socLuas").equals("8")){
					 hHakmilikUpdate.put("txtLuasLama", (getParam("txtLuas2")+"R"+getParam("txtLuas3")+"J"+getParam("txtLuas4")+"K"));
				 }
				 //System.out.println((getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
				 //LUAS BARU
				 hHakmilikUpdate.put("txtLuas", getParam("txtLuas"));
				 hHakmilikUpdate.put("socTaraf", getParam("socTaraf"));
				 hHakmilikUpdate.put("socKategori", getParam("socKategori"));			
				 hHakmilikUpdate.put("txtNoPelan", getParam("txtNoPelan"));
				 hHakmilikUpdate.put("txtTempoh", getParam("txtTempoh"));			
				 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
				 hHakmilikUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
				 hHakmilikUpdate.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
				 hHakmilikUpdate.put("txtCukaiTerkini", getParam("txtCukaiTerkini"));
				 hHakmilikUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				 hHakmilikUpdate.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
				 hHakmilikUpdate.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
				 hHakmilikUpdate.put("txtNoPu", getParam("txtNoPu"));
				 hHakmilikUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				 hHakmilikUpdate.put("txtNoSyit", getParam("txtNoSyit"));
				 hHakmilikUpdate.put("txtNoWarta", getParam("txtNoWarta"));
				 hHakmilikUpdate.put("txtSekatan", getParam("txtSekatan"));
				 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
				 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				 hHakmilikUpdate.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
				 hHakmilikUpdate.put("socStatus", getParam("socStatus"));
				 hHakmilikUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
				 hHakmilikUpdate.put("socJenisRizab", getParam("socJenisRizab"));	
				 hHakmilikUpdate.put("socRizab", getParam("socRizab"));	
				 hHakmilikUpdate.put("catatan", getParam("txtKemAgenTerkini"));	
				 hHakmilikUpdate.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
				 String socJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
				 hHakmilikUpdate.put("socJenisHakmilikBaru", socJenisHakmilikBaru);
				 String idHakmilikCukai = getParam("txtIdHakmilikCukai");
				 hHakmilikUpdate.put("idHakmilikCukai", idHakmilikCukai);
				 hHakmilikUpdate.put("txtCukaiSemasa", getParam("txtCukaiSemasa"));
				 hHakmilikUpdate.put("idKemaskini", userId);
				 hHakmilikUpdate.put("txtKodSocJenisHakmilikBaru",frmRekodUtilData.getKodJenisHakmilik(socJenisHakmilikBaru));
				 
				 FrmRekodPendaftaranHakmilikRizabData.updateHakmilikById(hHakmilikUpdate);
				 this.context.put("readonly", "readonly");
			 	 this.context.put("disabled", "disabled");	
		 		 this.context.put("mode", "view");
				 
				 //VIEW SEMULA HAKMILIK YANG DIUPDATE
			 	 if(getParam("socStatus").equals("S")){
			 		 //FrmRekodPendaftaranHakmilikSementaraData.updateHakmilikById(hHakmilikUpdate);
			 		 FrmRekodPendaftaranHakmilikSementaraData.addHakmilikBaruById(hHakmilikUpdate);
			 		
			 		 this.context.put("mode", "update");
					 
			 		 noHakmilikAsal = viewModeHakmilikSambungan(session,submit);				 
					//LIST HAKMILIK SAMBUNGAN
					 //rosli on 11/05/2010
					listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
					this.context.put("listSambungan",listSambungan);
			 	 }else{
					    // VIEW HEADER (MASTER) BY ID
					   noHakmilikAsal = view_modeMaklumatFail(session);
					    // VIEW MAKLUMAT HAKMILIK BY ID
					    view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);	 		 
			 	 }
				 

//			  }
//			  else
//			  //UPDATE DETAIL RIZAB
//			  if ("updateDetailRizab".equals(nextAction))	{
//				
//				 vm = "app/htp/frmRekodPendaftaranRizab.jsp";
//
//				 Hashtable hRizabUpdate = new Hashtable();
//				 hRizabUpdate.put("idHakmilik", getParam("idHakmilik"));
//				 hRizabUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
//				 hRizabUpdate.put("socLuas", getParam("socLuas"));
//				 //LUAS LAMA
//				 if(getParam("socLuas").equals("1")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"KM"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("2")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"H"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("3")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"M"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("4")){
//					 hRizabUpdate.put("txtLuasLama",(getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("5")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"K"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("7")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas5")+"E"+getParam("txtLuas6")+"D"));
//				 }
//				 else
//				 if(getParam("socLuas").equals("8")){
//					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas2")+"R"+getParam("txtLuas3")+"J"+getParam("txtLuas4")+"K"));
//				 }
//				 System.out.println((getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
//				 //LUAS BARU
//				 hRizabUpdate.put("txtLuas", getParam("txtLuas"));
//				 hRizabUpdate.put("txtNoPelan", getParam("txtNoPelan"));
//				 hRizabUpdate.put("txtNoPu", getParam("txtNoPu"));
//				 hRizabUpdate.put("txtLokasi", getParam("txtLokasi"));	
//				 hRizabUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
//				 hRizabUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));	
//				 hRizabUpdate.put("socStatus", getParam("socStatus"));
//				 hRizabUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));
//				 hRizabUpdate.put("txtNoLot", getParam("txtNoLot"));
//
//				 FrmRekodPendaftaranHakmilikRizabData.updateRizabById(hRizabUpdate);
//				
//				 //PAPAR SEMULA RIZAB YANG DIUPDATE
//				 this.context.put("readonly", "readonly");
//				 this.context.put("disabled", "disabled");	
//				 this.context.put("mode", "view");
//
//				 //VIEW MAKLUMAT RIZAB BY ID
//				 view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			   }	
		}else if("deleteHakmilikBaru".equals(firstAction)){	  
			vm = "app/htp/frmRekodPendaftaranHakmilikjsp";
			FrmRekodPendaftaranHakmilikSementaraData.hapusHakmilikBaruById(getParam("idHakmilikBaru"));
	
//		//LIST HAKMILIK SAMBUNGAN
			this.context.put("socStatus", "S");

			 this.context.put("readonly", "readonly");
		 	 this.context.put("disabled", "disabled");	
		 	 if(getParam("socStatus").equals("S"))
		 		 this.context.put("mode", "update");
		 	 else
		 		 this.context.put("mode", "view");
			 
			 noHakmilikAsal = viewModeHakmilikSambungan(session,submit);
			 
			//LIST HAKMILIK SAMBUNGAN
			 //rosli on 11/05/2010
			listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			this.context.put("listSambungan",listSambungan);

		//****************THIRD PAGE PROCESS **************************
		}else
		if("PendaftaranPembangunan".equals(firstAction)){

		   vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
		   this.context.put("jenis_button","3");

		    // VIEW HEADER (MASTER) BY ID
		    view_modeMaklumatFail(session);
		    
		    //SENARAI PEMBANGUNAN BY ID HAKMILIK
		   listPembangunan = view_modeSenaraiPembangunan(session);
		   this.context.put("SenaraiPembangunan", listPembangunan); 
		   
		   if (listPerihal != null) {
			   setupPagePentadbiran(session,action,listPerihal);
		   }
		   
		   // GET LUAS ASAL DAN BAKI TERKINI
		   luasAsal = FrmRekodPembangunanPentadbiranData.getLuasAsal(idHakmilik);
		   this.context.put("txtLuasAsal",luasAsal); 
		   
		   //VIEW LUAS TERKUMPUL
		   view_modeLuasTerkumpul(session);

		   this.context.put("readonly", "");
		   this.context.put("disabled", "");
		   this.context.put("socJenisBinaan","");
		   this.context.put("txtNoJKR","");
		   this.context.put("txdTarikhBina","");
		   this.context.put("txtHarga","");
		   this.context.put("socLuas", "");
		   this.context.put("txtLuas","");
		   this.context.put("txtLuasH","");
		   this.context.put("txtCatatan","");
		   this.context.put("mode", "new");
		   this.context.put("idHakmilik",idHakmilik);
		   
		   if("doChangeLuas".equals(nextAction)){
			   this.context.put("readonly", "");
			   this.context.put("disabled", "");
			   this.context.put("socJenisBinaan",getParam("socJenisBinaan"));
			   this.context.put("txtNoJKR",getParam("txtNoJKR"));
			   this.context.put("txdTarikhBina",getParam("txdTarikhBina"));
			   this.context.put("txtHarga",getParam("txtHarga"));
			   this.context.put("socLuas", getParam("socLuas"));
			   this.context.put("txtLuas",getParam("txtLuas"));
			   this.context.put("txtLuasH",getParam("txtLuasH"));
			   this.context.put("txtCatatan",getParam("txtCatatan"));
			   //this.context.put("mode", "new");
			   this.context.put("mode",getParam("mode"));
		   }

		   //TAMABAH PEMBANGUNAN HAKMILIK BY ID HAKMILIK
		   if("tambahDetailKeluasan".equals(nextAction)){
			   if (postDB){
				   	Hashtable hAddDetailKeluasan = new Hashtable();
				   	hAddDetailKeluasan.put("idHakmilik", getParam("idHakmilik"));
				   	hAddDetailKeluasan.put("socJenisBinaan", getParam("socJenisBinaan"));
				   	hAddDetailKeluasan.put("txtNoJKR", getParam("txtNoJKR"));
				   	hAddDetailKeluasan.put("txdTarikhBina", getParam("txdTarikhBina"));
				   	hAddDetailKeluasan.put("txtHarga", getParam("txtHarga"));
				   	hAddDetailKeluasan.put("socLuas", getParam("socLuas"));
					 //LUAS LAMA
					 if(getParam("socLuas").equals("1")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas1")+"KM"));
					 }
					 else
					 if(getParam("socLuas").equals("2")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas1")+"H"));
					 }
					 else
					 if(getParam("socLuas").equals("3")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas1")+"M"));
					 }
					 else
					 if(getParam("socLuas").equals("4")){
						 hAddDetailKeluasan.put("txtLuasLama",(getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
					 }
					 else
					 if(getParam("socLuas").equals("5")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas1")+"K"));
					 }
					 else
					 if(getParam("socLuas").equals("7")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas5")+"E"+getParam("txtLuas6")+"D"));
					 }
					 else
					 if(getParam("socLuas").equals("8")){
						 hAddDetailKeluasan.put("txtLuasLama", (getParam("txtLuas2")+"R"+getParam("txtLuas3")+"J"+getParam("txtLuas4")+"K"));
					 }
					 System.out.println((getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
					 //LUAS BARU
					hAddDetailKeluasan.put("txtLuas", getParam("txtLuas"));
				   	hAddDetailKeluasan.put("txtCatatan", getParam("txtCatatan"));
				   	String idHakmilikPerihalBaru = FrmRekodPembangunanPentadbiranData.addDetailKeluasan(hAddDetailKeluasan);
			   }
			
		    //SENARAI PEMBANGUNAN BY ID HAKMILIK
			listPembangunan = view_modeSenaraiPembangunan(session);
			this.context.put("SenaraiPembangunan", listPembangunan);
			
			//VIEW LUAS TERKUMPUL
		    view_modeLuasTerkumpul(session);
			
		   }
		   else
		   // VIEW PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   if("viewDetailKeluasan".equals(nextAction)){
			
		       this.context.put("readOnly", "readOnly");
		       this.context.put("disabled", "disabled");
		       this.context.put("mode", "view");
		       view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal,nextAction);
		       
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
		   } 
		   else
		   //KEMASKINI PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   if("kemaskiniDetailKeluasan".equals(nextAction)){

		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
		       this.context.put("mode", "kemaskini");
		       view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal,nextAction);
		       
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
		       
		   }  
		   //UPDATE PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   if("updateDetailKeluasan".equals(nextAction)){

		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
		       this.context.put("mode", "update");
		       this.context.put("popupSkrin","popupSkrin");
			   Hashtable hPembangunanUpdate = new Hashtable();
			   
			   log.debug("%%% hakmilik periah="+idHakmilikPerihal);
			   
			   hPembangunanUpdate.put("idHakmilikPerihal", getParam("idHakmilikPerihal"));
			   hPembangunanUpdate.put("socJenisBinaan", getParam("socJenisBinaan"));
			   hPembangunanUpdate.put("txtNoJKR", getParam("txtNoJKR"));
			   hPembangunanUpdate.put("txdTarikhBina", getParam("txdTarikhBina"));
			   hPembangunanUpdate.put("txtHarga", getParam("txtHarga"));
			   hPembangunanUpdate.put("socLuasBangunan", getParam("socLuasBangunan"));
			   hPembangunanUpdate.put("txtLuas", getParam("txtLuas"));
			   hPembangunanUpdate.put("txtLuasH", getParam("txtLuasH"));
			   hPembangunanUpdate.put("txtCatatan", getParam("txtCatatan"));
			   FrmRekodPembangunanPentadbiranData.updatePembangunanById(hPembangunanUpdate,session);
		      	   
			    //SENARAI PEMBANGUNAN BY ID HAKMILIK
			   listPembangunan = view_modeSenaraiPembangunan(session);
			   this.context.put("SenaraiPembangunan", listPembangunan);
			   
		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
			   this.context.put("socJenisBinaan","");
			   this.context.put("txtNoJKR","");
			   this.context.put("txdTarikhBina","");
			   this.context.put("txtHarga","");
			   this.context.put("selectLuasBangunan", HTML.SelectLuas("socLuasBangunan",Utils.parseLong(idLuasBanguanan), " style=\"width:200px\""));
			   this.context.put("txtLuas","");
			   this.context.put("txtLuasH","");
			   this.context.put("txtCatatan","");
			   this.context.put("mode", "new");			   
			   
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
		   } 
		   else
		   //DELETE REKOD IMAGE
		   if("deleteDetailPembangunan".equals(nextAction)){	  
			   FrmRekodPembangunanPentadbiranData.hapusPembangunanById(getParam("idHakmilikPerihal"));
			   
			    //SENARAI PEMBANGUNAN BY ID HAKMILIK
			   listPembangunan = view_modeSenaraiPembangunan(session);
			   this.context.put("SenaraiPembangunan", listPembangunan);
			   
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
			   //PREPARE FOR INSERT BARU
		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
			   this.context.put("socJenisBinaan","");
			   this.context.put("txtNoJKR","");
			   this.context.put("txdTarikhBina","");
			   this.context.put("txtHarga","");
			   this.context.put("selectLuasBangunan", HTML.SelectLuas("socLuasBangunan",Utils.parseLong(idLuasBanguanan), " style=\"width:200px\""));
			   this.context.put("txtLuas","");
			   this.context.put("txtLuasH","");
			   this.context.put("txtCatatan","");
			   this.context.put("mode", "new");
		   }
	    }
		//****************FOURTH PAGE PROCESS **************************
		else
			if("PendaftaranImej".equals(firstAction)){
			   vm = "app/htp/frmRekodPembangunanImej.jsp";
			   this.context.put("jenis_button","4");
			   
				// VIEW HEADER(MASTER) BY ID
				view_modeMaklumatFail(session);
			   
			    //SENARAI IMEJ BY ID HAKMILIK
			   listImej = view_modeSenaraiImej(session);
			   this.context.put("SenaraiImej", listImej);
			   setupPagePentadbiran(session,action,listImej);
			   
		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
		       
			   this.context.put("txtPerihalImej","");
			   this.context.put("txtRingkas","");
			   this.context.put("txdTarikhKemaskiniImej","");
			   this.context.put("mode", "new");
			   
			   //ADD NEW DETAIL IMEJ
			   if("tambahDetailImej".equals(nextAction)){
				   
				  uploadFiles(); 
				  listImej = view_modeSenaraiImej(session);
				  this.context.put("SenaraiImej", listImej);			   
			   }
			   //VIEW DETAIL IMEJ
			   else
			   if("viewDetailImej".equals(nextAction)){
			      this.context.put("readOnly", "readOnly");
			      this.context.put("disabled", "disabled");
			      this.context.put("mode","view");
			      
			      //VIEW IMAGE DETAIL BY ID GAMBAR
			      view_modeImejByIdGambar(session,idGambar);
			   }
			   else
			   //KEMASKINI DETAIL IMEJ
			   if("kemaskiniDetailImej".equals(nextAction)){
				   this.context.put("readOnly", "");
				   this.context.put("disabled", "");
				   this.context.put("mode","kemaskini");
					      
				   //VIEW IMAGE DETAIL BY ID GAMBAR
				    view_modeImejByIdGambar(session,idGambar);
			  }	
			  else
			  //UPDATE DETAIL IMEJ
		      if("updateDetailImej".equals(nextAction)){
			      this.context.put("readOnly", "readOnly");
			      this.context.put("disabled", "disabled");
						 	
				  Hashtable hImejUpdate = new Hashtable();
				  hImejUpdate.put("idGambar", getParam("idGambar"));
				  hImejUpdate.put("txtPerihalImej", getParam("txtPerihalImej").toUpperCase());
				  hImejUpdate.put("txtRingkas", getParam("txtRingkas").toUpperCase());	
				  FrmRekodPembangunanImejData.updateImejById(hImejUpdate);
				  
				    //SENARAI IMEJ BY ID HAKMILIK
				   listImej = view_modeSenaraiImej(session);
				   this.context.put("SenaraiImej", listImej);
				   setupPagePentadbiran(session,action,listImej);
				   
				   //PREPARE FOR INSERT BARU
			       this.context.put("readOnly", "");
			       this.context.put("disabled", "");
			       
				   this.context.put("txtPerihalImej","");
				   this.context.put("txtRingkas","");
				   this.context.put("txdTarikhKemaskiniImej","");
				   this.context.put("mode", "new");
		      }
		      else 
		      //DELETE REKOD IMAGE
		      if("deleteDetailImej".equals(nextAction)){	  
		    	  FrmRekodPembangunanImejData.hapusImej(getParam("idGambar"));
		    	  
				    //SENARAI IMEJ BY ID HAKMILIK
				   listImej = view_modeSenaraiImej(session);
				   this.context.put("SenaraiImej", listImej);
				   setupPagePentadbiran(session,action,listImej);
				   
				   //PREPARE FOR INSERT BARU
			       this.context.put("readOnly", "");
			       this.context.put("disabled", "");
			       
				   this.context.put("txtPerihalImej","");
				   this.context.put("txtRingkas","");
				   this.context.put("mode", "new");
		      }
			}	

		this.context.put("flagAdvSearch", flagAdvSearch);
		return vm;
	}
		
	//****************FIRST PAGE METHOD/FUCTION **************************
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
	private Vector view_modeCarianFailHakmilikDanRizab(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus) throws Exception {
		return FrmRekodPendaftaranHakmilikRizabData.getCarianSenaraiHakmilikRizabById(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);		
	}
	
	//****************FIRST PAGE METHOD/FUCTION **************************
	//by rosli 2010/05/11 SENARAI HAKMILIK YANG AKTIF SAHAJA
	// VIEW CARIAN FAIL HAKMILIK DAN RIZAB	
	private Vector carianSenaraiHakmilikRizabAktif(HttpSession session,String idJenisTanah,String idNegeri,String idDaerah,String idMukim,String noFail,String idJenisHakmilik,String noHakmilik,String noWarta,String idLot,String noLot, String idAgensi, String idKementerian, String idStatus) throws Exception {
		return FrmRekodPendaftaranHakmilikRizabData.getCarianSenaraiHakmilikRizabAktif(idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);		
	}
	
	//****************SECOND PAGE METHOD/FUCTION **************************
	// VIEW HEADER BY ID
	private String view_modeMaklumatFail(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");	
		Vector list =null;

		list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTD",(String)hMaklumatFail.get("noFailPtd"));
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
		this.context.put("pegawaiAkhir", (String)hMaklumatFail.get("pegawaiAkhir"));
		return (String)hMaklumatFail.get("hakmilikAsal");
	}
	
    // VIEW MAKLUMAT HAKMILIK BY ID
	private void view_modeHakmilikRizab(HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
		Hashtable hHakmilik = null;
		if (list.size() > 0) {
			hHakmilik = (Hashtable) list.get(0);
		} else {
			throw new Exception("Maklumat Rekod Tidak Lengkap");
		}
		
		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("statusBatal", (String)hHakmilik.get("socStatus"));
		this.context.put("txtKodSocJenisHakmilik", (String)hHakmilik.get("kodJenisHakmilik"));
		
		if(hHakmilik.get("socStatus").equals("S")||hHakmilik.get("socStatus").equals("B")){
			isSambungan = true;	
		}
		this.context.put("idHakmilikCukai", (String)hHakmilik.get("idHakmilikCukai"));		
		this.context.put("socStatusTanah", (String)hHakmilik.get("socStatusTanah"));
    	this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
    	this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));	
		
		if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){
			
			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), ""," style=\"width:200px\"")); 
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")), " style=\"width:200px\" onChange=\"doChangeStateHR();\""));
	    	this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),""," style=\"width:200px\" onChange=\"doChangeDaerahHR();\""));
	    	this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR"))," style=\"width:200px\""));		
	    	this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")), " style=\"width:200px\""));
	    	this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")), " style=\"width:200px\""));
	    	//this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style=\"width:200px\""));
		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
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
		}else if(nextAction.equals("updateDetailHakmilik")|| nextAction.equals("updateDetailRizab")||nextAction.equals("paparDetailHakmilik")||nextAction.equals("paparDetailRizab")){
			
			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
			//this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
		    //this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectNegeriHR",hHakmilik.get("namaNegeriHR"));
			this.context.put("selectDaerahHR",hHakmilik.get("namaDaerahHR"));
		    this.context.put("selectMukimHR", hHakmilik.get("namaMukimHR"));
			//this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectJenisLotHR", hHakmilik.get("namaLot"));
		    //this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\" class=\"disabled\""));		    
		    this.context.put("selectJenisHakmilikHR", hHakmilik.get("kodJenisHakmilikHR"));		    
		    //this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", hHakmilik.get("namaKategori"));
			this.context.put("selectRizab", hHakmilik.get("namaJenisRizab"));

		}	
		
		this.context.put("txtNoBangunan",getParam("txtNoBangunan") == "" ? (String)hHakmilik.get("noBangunan"):getParam("txtNoBangunan"));
		this.context.put("txtNoTingkat",getParam("txtNoTingkat") == "" ? (String)hHakmilik.get("noTingkat"):getParam("txtNoTingkat"));
		this.context.put("txtNoPetak",getParam("txtNoPetak") == "" ? (String)hHakmilik.get("noPetak"):getParam("txtNoPetak"));
		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
		this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
		this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
		this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
		this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
		this.context.put("txtNoRizab",getParam("txtNoRizab") == "" ? (String)hHakmilik.get("noRizab"):getParam("txtNoRizab"));
		this.context.put("txdTarikhRizab",getParam("txdTarikhRizab") == "" ? (String)hHakmilik.get("tarikhRizab"):getParam("txdTarikhRizab"));
		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
		this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
		this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
		this.context.put("socLuas", getParam("socLuas") == "" ? (String)hHakmilik.get("idLuasLama"):getParam("socLuas"));
		this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? hHakmilik.get("luasConvert"):getParam("txtLuas"));
		this.context.put("txtNoLot",getParam("txtNoLot") == "" ? (String)hHakmilik.get("noLot"):getParam("txtNoLot"));
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("noHakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? "" : getParam("txtHakmilikBerikut"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));

	}	
	
	// view_modeSenaraiHakmilikSambungan
	private Vector view_modeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
		return FrmRekodPendaftaranHakmilikRizabData.getSenaraiHakmilikSambungan(noHakmilikAsal);	
		
	}
	
	//****************THIRD PAGE METHOD/FUCTION **************************
	// VIEW SENARAI PEMBANGUNAN
	private Vector view_modeSenaraiPembangunan(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		return FrmRekodPembangunanPentadbiranData.getMaklumatPembangunanByIdHakmilik(idHakmilik);
	}
	
	// VIEW SENARAI PEMBANGUNAN
	private void view_modeLuasTerkumpul(HttpSession session) throws Exception {
		// LUAS LUM SUM
		String idHakmilik = getParam("idHakmilik");
	   Vector listTerkumpul = FrmRekodPembangunanPentadbiranData.getLuasSemua(idHakmilik);
	   Hashtable luasTerkumpul = (Hashtable) listTerkumpul.get(0);	
	   this.context.put("txtBangunan",luasTerkumpul.get("txtBangunan"));
	   this.context.put("txtJalan",luasTerkumpul.get("txtJalan"));
	   this.context.put("txtPadang",luasTerkumpul.get("txtPadang"));
	   this.context.put("txtParking",luasTerkumpul.get("txtParking"));
	   this.context.put("txtLain",luasTerkumpul.get("txtLain"));
	   this.context.put("txtJumlahGuna",luasTerkumpul.get("txtJumlahGuna"));
	   this.context.put("txtBakiBelum",luasTerkumpul.get("txtBakiBelum"));
	   this.context.put("txtPeratusBelum", (String)luasTerkumpul.get("txtPeratusBelum"));
	}
	
	//VIEW HAKMILIKPERIHAL BY ID
	private void view_modePerihalByIdHakmilikPerihal(HttpSession session,String idHakmilikPerihal,String nextAction) throws Exception {
		Vector list =null;
		list = FrmRekodPembangunanPentadbiranData.getMaklumatPerihalById(idHakmilikPerihal);
		Hashtable hPergerakanById = (Hashtable) list.get(0);
		
		this.context.put("idHakmilikPerihal",(String)hPergerakanById.get("idHakmilikPerihal"));
		this.context.put("socJenisBinaan",(String)hPergerakanById.get("jenisBangunan"));
		this.context.put("txtNoJKR",(String)hPergerakanById.get("noRujukanJKR"));
		this.context.put("txdTarikhBina",(String)hPergerakanById.get("tarikhBina"));
		this.context.put("txtHarga", (String)hPergerakanById.get("hargaBina"));
		this.context.put("socLuas", getParam("socLuas") == "" ? (String)hPergerakanById.get("socLuasBangunan"):getParam("socLuas"));
		this.context.put("txtLuasLama", (String)hPergerakanById.get("luasLama"));
		String cekBinaan = (String)hPergerakanById.get("jenisBangunan");
		if(cekBinaan.equals("B"))
			this.context.put("txtLuas",(String)hPergerakanById.get("luasB"));
		else if(cekBinaan.equals("P"))
			this.context.put("txtLuas",(String)hPergerakanById.get("luasP"));
		else if(cekBinaan.equals("PR"))
			this.context.put("txtLuas",(String)hPergerakanById.get("luasPR"));
		else if(cekBinaan.equals("J"))
			this.context.put("txtLuas",(String)hPergerakanById.get("luasJ"));
		else if(cekBinaan.equals("L"))
			this.context.put("txtLuas",(String)hPergerakanById.get("luasL"));
		this.context.put("txtCatatan", (String)hPergerakanById.get("catatan"));
	}
	
	// GET LUAS ASAL DAN BAKI TERKINI
	private void xview_modeGetLuasAsalDanBaki(HttpSession session,String idHakmilik) throws Exception {
		
	  //Vector list =null;
	  //list = FrmRekodPembangunanPentadbiranData.getLuasAsal(idHakmilik);
	  //Hashtable hLuasAsal = (Hashtable) list.get(0);
	  //this.context.put("txtLuasAsal",(String)hLuasAsal.get("luasAsal"));
	  //this.context.put("bakiTerkini",(String)hLuasAsal.get("bakiTerkini"));	
	
	}
	
	//****************FOURTH PAGE METHOD/FUCTION **************************	
	// VIEW SENARAI IMEJ
	private Vector view_modeSenaraiImej(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		return FrmRekodPembangunanImejData.getMaklumatImejByIdHakmilik(idHakmilik);
	}
	
	//VIEW IMEJ BY ID
	private void view_modeImejByIdGambar(HttpSession session,String idGambar) throws Exception {
		
		Vector list =null;
		list = FrmRekodPembangunanImejData.getMaklumatImejById(idGambar);
		Hashtable hImejById = (Hashtable) list.get(0);
		
		this.context.put("idGambar",(String)hImejById.get("idGambar"));
		this.context.put("idHakmilik",(String)hImejById.get("idHakmilik"));
		this.context.put("txtRingkas",(String)hImejById.get("ringkasan"));
		this.context.put("txtPerihalImej", (String)hImejById.get("perihalImej"));
		this.context.put("txdTarikhKemaskiniImej", (String)hImejById.get("tarikh"));
	}
	
	// UPLOAD FILE
	private void uploadFiles() throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    Enumeration allparam = request.getParameterNames();
		String name="";String value="";
		for (; allparam.hasMoreElements(); ) {
	        // Get the name of the request parameter
	        name = (String)allparam.nextElement();
	        // Get the value of the request parameter
	        value = request.getParameter(name);
	        //System.out.println(name +"="+value);
		    log.info(name +"="+value);
		}
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	saveData(item);
		    }
	    }
	 }
	
	 private void saveData(FileItem item) throws Exception {
		 Db db = null;
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = new Date(); 
		 String currentDate = sdf.format(date);
		 //convert date before add
		 String txdTarikhKemaskiniImej = currentDate;
		 String txdTarikhKemaskini = "to_date('" + txdTarikhKemaskiniImej + "','dd/MM/yyyy')";	
		 System.out.println("tarikh : "+txdTarikhKemaskini+"currentDate : "+currentDate);
		 try {
			 db = new Db();
			 long idGambar = DB.getNextID("TBLHTPGAMBAR_SEQ");
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("insert into TBLHTPGAMBAR " +
	        			"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail,tarikh_kemaskini) " +
	        			"values(?,?,?,?,?,?,?,sysdate)");
			 //log.info("insert into TBLHTPGAMBAR " +
			 //"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail,tarikh_kemaskini) " +
			 //"values(?,?,?,?,?,?,?,sysdate)");

			 ps.setLong(1, idGambar);
			 ps.setString(2, getParam("idHakmilik"));
			 ps.setString(3, getParam("txtPerihalImej").toUpperCase());
			 ps.setString(4, getParam("txtRingkas").toUpperCase());
			 ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
			 ps.setString(6,item.getName());
			 ps.setString(7,item.getContentType());
			 ps.executeUpdate();
			 con.commit();
		 } finally {
			      if (db != null) db.close();
		 }
	 }
	 
	 	
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
		private void view_modeMaklumatFailFrmRekodHakmilikSementara(HttpSession session) throws Exception {
			String idHakmilik = getParam("idHakmilik");	
			Vector list =null;
			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idHakmilik);
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
		private void view_modeHakmilikSementara(HttpSession session) throws Exception {
			String idHakmilik = getParam("idHakmilik");
			Vector list =null;
			list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
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
			this.context.put("socStatus", getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
			this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
			this.context.put("socTaraf",(String)hHakmilik.get("socTaraf"));
			this.context.put("socRizab", (String)hHakmilik.get("socRizab"));
			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
			this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
			this.context.put("socLuas",(String)hHakmilik.get("idLuasLama"));
			this.context.put("txtLuasLama",(String)hHakmilik.get("luasLama"));
			this.context.put("txtLuas",(String)hHakmilik.get("luasConvert"));		
			this.context.put("txtNoHakmilikAsal",(String)hHakmilik.get("hakmilikAsal"));		
		}
		
		 // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
		private String viewModeHakmilikSambungan(HttpSession session,String submit) throws Exception {
			String idHakmilik = getParam("idHakmilik");
			log.info("idHakmilik :"+idHakmilik);
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
			log.info("idHakmilik :"+id);
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
			
		}	

		// view_modeSenaraiHakmilikSambungan
		private Vector viewModeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
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

}	
