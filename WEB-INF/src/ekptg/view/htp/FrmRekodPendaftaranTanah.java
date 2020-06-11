package ekptg.view.htp;

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
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPembangunanImejData;
import ekptg.model.htp.FrmRekodPembangunanPentadbiranData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HtpPerjanjian;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Penyewaan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.rekod.FrmHakmilikRizabBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanPajakanBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanPenyewaanBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.ITanah;
import ekptg.model.htp.rekod.ITanahCarian;
import ekptg.model.htp.rekod.ITanahUrusan;
import ekptg.model.htp.rekod.TanahBean;

public class FrmRekodPendaftaranTanah extends AjaxBasedModule {
	private boolean isSambungan = false;
	private final String PATH="app/htp/rekod/";
	private HakmilikInterface iHakmilik_ = null;
	private ITanah iHakmilik = null;
	private ITanahUrusan iHakmilikStatus = null;
	private ITanahUrusan iHakmilikStatusP = null;
	private Hashtable hastableHakmilik = null;
	private ITanahCarian iHakmilikRizab = null;
 	private IHtp iHTP = null;  
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmRekodPendaftaranTanah.class);
	private String socStatusTemp = "";
	private String idHakmilik = "";
	private Vector vector = null;
	String userId = "";
	FrmRekodUtilData frmRekodUtilData = null;
	private HakMilik hakmilik = null;
	String RO_General = "";
	String actionI = "";
	
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
		actionI = action;
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
		//String 
		idHakmilik = getParam("idHakmilik");
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
		
		//String flagAdvSearch = getParam("flagAdvSearch")==null?"-":getParam("flagAdvSearch");
		String flagAdvSearch = getParam("flagAdvSearch");
		String skrin = getParam("skrin");
		//context.remove("totalRecords");
		//log.debug("idJenisTanah:"+idJenisTanah);
		myLog.debug("idStatus:"+idStatus);
	
		//****************FIRST PAGE PROCESS **************************
		//VIEW SENARAI HAKMILIK DAN RIZAB
		if(firstAction.equals("")){
			myLog.info("firstAction : Kosong");
			vm = PATH+"frmRekodSenaraiHakmilikRizab.jsp";
			//flagAdvSearch = "-";
			//asal
			//list = view_modeCarianFailHakmilikDanRizab(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
			if(!"".equals(skrin)){
			   //idNegeri = getParam("socNegeriHR");		   
			}		   
			// 21/02/2012
			//list = carianSenaraiHakmilikRizabAktif(session,idJenisTanah,idNegeri,idDaerah,idMukim,noFail,idJenisHakmilik,noHakmilik,noWarta,idLot,noLot,idAgensi,idKementerian, idStatus);
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
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
			this.context.put("txtNoFailC", "");
			this.context.put("txtNoHakmilikC", noHakmilik);
			this.context.put("txtNoWartaC",noWarta);
			this.context.put("txtNoLotC", "");
			setupPage(session,action,list);
			this.context.put("flagAdvSearch", flagAdvSearch);
		   
		//VIEW CARIAN HAKMILIK DAN RIZAB BY idNegeri,idDaerah,idMukim,noHakmilik
		}else if(firstAction.equals("carianHakmilikRizab")){
			
			myLog.info("firstAction : carianHakmilikRizab");
			vm = PATH+"frmRekodSenaraiHakmilikRizab.jsp";
			flagAdvSearch = "open";			
			this.context.put("flagAdvSearch", flagAdvSearch);
			// IF CARIAN = NULL
			context.remove("SenaraiFail");
			context.remove("totalRecords");
			context.remove("itemsPerPage");
			this.context.put("totalPages",0);
			this.context.put("isFirstPage",true);
			this.context.put("isLastPage",true);
			if(idJenisTanah.equals("99999")
				&&idNegeri.equals("99999") 
				&& idDaerah.equals("99999") 
				&& idMukim.equals("99999") 
				&& noHakmilik.isEmpty()){						
				myLog.info("firstAction : carianHakmilikRizab 1");
				//view_modeSenaraiFail(session);
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
		   	   	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		   	   	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " style=\"width:400\""));
		   	   	this.context.put("txtNoFailC", "");
		   	   	this.context.put("txtNoHakmilikC", "");
		   	   	this.context.put("txtNoWartaC", "");
		   	   	this.context.put("txtNoLotC", "");
		   	   	this.context.put("SenaraiTanah", list);
		    
		}else {
		    	//IF ADA CARIAN
				myLog.info("firstAction : carianHakmilikRizab 2");
		    	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
		    	this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
		    	this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri,"socDaerah", Utils.parseLong(idDaerah),""," onChange=\"doChangeDaerah();\""));
		    	this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim),null));
		    	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
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
		   	
			if (nextAction.equals("doChangeState")) {
				myLog.info("firstAction : carianHakmilikRizab 3");
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri), " onChange=\"doChangeState();\""));
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
		    
			}else if (nextAction.equals("doChangeDaerah")) {
				myLog.info("firstAction : carianHakmilikRizab 4");
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
		    
		    }else if (nextAction.equals("doChangeKementerian")) {
				myLog.info("firstAction : carianHakmilikRizab 5");
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
			if(!list.isEmpty()){
				setupPage(session,action,list);
			}
			myLog.info("firstAction : carianHakmilikRizab 6");
			//return vm;
			
		//****************SECOND PAGE PROCESS **************************
		}else if(firstAction.equals("PendaftaranHakmilik")){
			this.context.put("jenis_button","2");
			this.context.put("rizab_hakmilik_label", "HAKMILIK");
			String selectedTab = getParam("tabId");		
			if (selectedTab == null || "".equals(selectedTab) ) {
	    		selectedTab="0";
	    	}
	
			// VIEW DETAIL HAKMILIK
			if (nextAction.equals("paparDetailHakmilik")){
				//14/10/2010, guna pembolehubah PATH
				// 29/05/2012
				vm = PATH+"frmRekodPendaftaranHakmilik.jsp";	
				//
				//vm = PATH+"frmRekodPendaftaranHakmilikTab.jsp";	
				isSambungan = false;
				this.context.put("readonly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				
			    // VIEW HEADER (MASTER) BY ID
				// Kemaskini pada 2011/02/28 return id_hakmilik (asal)
				//noHakmilikAsal = view_modeMaklumatFail(session);
				noHakmilikAsal = viewMaklumatFailMengikutHakmilik(session);
				// VIEW MAKLUMAT HAKMILIK BY ID
				//14/10/2010
				//view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
				viewModeHakmilik(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
				
				//LIST HAKMILIK SAMBUNGAN
//				listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
//				this.context.put("listSambungan",listSambungan);
				myLog.info("isSambungan:"+isSambungan);
				if(isSambungan){	 
					vm = PATH+"frmRekodPendaftaranHakmilikSambView.jsp";	
					noHakmilikAsal = viewModeHakmilikSambungan(session,submit);
			
					//LIST HAKMILIK SAMBUNGAN
					//rosli on 11/05/2010				
					//listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
					
					//28/01/2011
					listSambungan = getIHakmilik().getSenaraiHakmilikSambungan(noHakmilikAsal
							, String.valueOf(hakmilik.getNegeri().getIdNegeri())
							, String.valueOf(hakmilik.getDaerah().getIdDaerah())
							, String.valueOf(hakmilik.getMukim().getIdMukim()));
					this.context.put("listSambungan",listSambungan);
					
			   }
			   
	        }else if (nextAction.equals("paparDetailRizab")){	// VIEW DETAIL RIZAB
	        	// Kemaskini Oleh Rosli pada 20110228, PATH 
	        	//vm = "app/htp/frmRekodPendaftaranRizab.jsp";
	        	vm = PATH+"frmRekodPendaftaranRizab.jsp";
	        	this.context.put("rizab_hakmilik_label", "RIZAB");
	        	this.context.put("readonly", "readonly");
	        	this.context.put("disabled", "disabled");
	        	this.context.put("mode", "view");
						
			   // VIEW HEADER(MASTER) BY ID
               view_modeMaklumatFail(session);
 					
               // VIEW MAKLUMAT RIZAB BY ID
               viewModeRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
		    
			}else if (nextAction.equals("kemaskiniDetailHakmilik")){	//KEMASKINI DETAIL MAKLUMAT HAKMILIK
			    // 14/10/2010
				//vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
			    vm = PATH+"frmRekodPendaftaranHakmilik.jsp";
				
				this.context.put("readonly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");

				// VIEW HEADER(MASTER) BY ID
				view_modeMaklumatFail(session);
				// VIEW MAKLUMAT HAKMILIK BY ID
				viewModeHakmilik(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
				
			// Paparan Maklumat Hakmilik, apabila memilih Status Sah, BATAL(SAMBUNGAN)
			//KEMASKINI DETAIL MAKLUMAT HAKMILIK
			}else if (nextAction.equals("kemaskinidetailhakmiliksamb")){	
				 // 18/10/2010
				 vm = PATH+"frmRekodPendaftaranHakmilik.jsp";					
				 this.context.put("readonly", "");
				 this.context.put("disabled", "");	
				 this.context.put("mode", "update");
				 // VIEW HEADER(MASTER) BY ID
				 viewModeMaklumatFail(session,idHakmilik);
				 // VIEW MAKLUMAT HAKMILIK BY ID
				 viewModeHakmilikSambungan(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
					
			}else if (nextAction.equals("kemaskiniDetailRizab")){	//KEMASKINI DETAIL MAKLUMAT RIZAB
	        	// Kemaskini Oleh Rosli pada 20110228, PATH 
			    //vm = "app/htp/frmRekodPendaftaranRizab.jsp";
				vm = PATH+"frmRekodPendaftaranRizab.jsp";

				this.context.put("readonly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");
				
				// VIEW HEADER(MASTER) BY ID
				view_modeMaklumatFail(session);

				// VIEW MAKLUMAT RIZAB BY ID
				viewModeRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			 
			}else if (nextAction.equals("updateDetailHakmilik")){	//KEMASKINI MAKLUMAT HAKMILIK
				 vm = PATH+"frmRekodPendaftaranHakmilik.jsp";
				 socStatusTemp = getParam("socStatusDaftar");
				 
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
				 hHakmilikUpdate.put("txtCukaiTahun", Utils.RemoveComma(getParam("txtCukaiTahun")));
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
				 //LUAS BARU
				 hHakmilikUpdate.put("txtLuas", getParam("txtLuas"));
				 hHakmilikUpdate.put("socTaraf", getParam("socTaraf"));
				 hHakmilikUpdate.put("socKategori", getParam("socKategori"));			
				 hHakmilikUpdate.put("txtNoPelan", getParam("txtNoPelan"));
				 hHakmilikUpdate.put("txtTempoh", getParam("txtTempoh"));			
				 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
				 hHakmilikUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
				 hHakmilikUpdate.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
				 hHakmilikUpdate.put("txtCukaiTerkini", Utils.RemoveComma(getParam("txtCukaiTerkini")));
				 hHakmilikUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				 hHakmilikUpdate.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
				 hHakmilikUpdate.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
				 hHakmilikUpdate.put("txtNoPu", getParam("txtNoPu"));
				 hHakmilikUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				 hHakmilikUpdate.put("txtNoSyit", getParam("txtNoSyit"));
				 //hHakmilikUpdate.put("txtNoWarta", getParam("txtNoWarta"));
				 hHakmilikUpdate.put("txtNoWarta", getParam("txtNoRizab"));
				 //myLog.info(getParam("txtNoWarta"));
				 myLog.info(getParam("txtNoRizab"));
				 hHakmilikUpdate.put("txtSekatan", getParam("txtSekatan"));
				 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
				 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				 hHakmilikUpdate.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
				 hHakmilikUpdate.put("socStatus",socStatusTemp);
				 hHakmilikUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
				 hHakmilikUpdate.put("socJenisRizab", getParam("socJenisRizab"));	
				 hHakmilikUpdate.put("socRizab", getParam("socRizab"));	
				 hHakmilikUpdate.put("catatan", getParam("txtKemAgenTerkini"));	
				 hHakmilikUpdate.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
				 String socJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
				 hHakmilikUpdate.put("socJenisHakmilikBaru", socJenisHakmilikBaru);
				 String idHakmilikCukai = getParam("txtIdHakmilikCukai");
				 hHakmilikUpdate.put("idHakmilikCukai", idHakmilikCukai);
				 hHakmilikUpdate.put("txtCukaiSemasa", Utils.RemoveComma(getParam("txtCukaiSemasa")));
				 hHakmilikUpdate.put("idKemaskini", userId);
				 hHakmilikUpdate.put("txtKodSocJenisHakmilikBaru",frmRekodUtilData.getKodJenisHakmilik(socJenisHakmilikBaru));
				 
				 // 15/10/2010 
				 //FrmRekodPendaftaranHakmilikRizabData.updateHakmilikById(hHakmilikUpdate);
				 this.context.put("readonly", "readonly");
			 	 this.context.put("disabled", "disabled");	
		 		 this.context.put("mode", "view");
				 
				 //VIEW SEMULA HAKMILIK YANG DIUPDATE
			 	 if(socStatusTemp.equals("S")){
			 		 getIHakmilik().kemaskiniHakmilikTambahSambungan(hHakmilikUpdate);
			 		 this.context.put("mode", "update");				 
			 		 noHakmilikAsal = viewModeHakmilikSambungan(session,submit);				 
			 		 //LIST HAKMILIK SAMBUNGAN
			 		 //rosli on 11/05/2010
			 		 //listSambungan = viewModeSenaraiHakmilikSambungan(session,noHakmilikAsal);
			 		 
			 		 //28/01/2011
			 		 listSambungan = getIHakmilik().getSenaraiHakmilikSambungan(noHakmilikAsal
								, String.valueOf(hakmilik.getNegeri().getIdNegeri())
								, String.valueOf(hakmilik.getDaerah().getIdDaerah())
								, String.valueOf(hakmilik.getMukim().getIdMukim()));
			 		 this.context.put("listSambungan",listSambungan);

			 	 }else{
			 		 FrmRekodPendaftaranHakmilikRizabData.updateHakmilikById(hHakmilikUpdate);
					 // VIEW HEADER (MASTER) BY ID
					 noHakmilikAsal = view_modeMaklumatFail(session);
					 // VIEW MAKLUMAT HAKMILIK BY ID
					 view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);	 		 
			 	 
			 	 }
			 	myLog.info("socStatusTemp="+socStatusTemp);

			}else if (nextAction.equals("updateDetailRizab"))	{	//UPDATE DETAIL RIZAB		
				// Kemaskini Oleh Rosli pada 20110228, PATH 
				vm = PATH+"frmRekodPendaftaranRizab.jsp";
				Hashtable hRizabUpdate = new Hashtable();
				hRizabUpdate.put("idHakmilik", getParam("idHakmilik"));
				hRizabUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				hRizabUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				hRizabUpdate.put("socLuas", getParam("socLuas"));
				 //LUAS LAMA
				 if(getParam("socLuas").equals("1")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"KM"));
				 }
				 else
				 if(getParam("socLuas").equals("2")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"H"));
				 }
				 else
				 if(getParam("socLuas").equals("3")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"M"));
				 }
				 else
				 if(getParam("socLuas").equals("4")){
					 hRizabUpdate.put("txtLuasLama",(getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
				 }
				 else
				 if(getParam("socLuas").equals("5")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"K"));
				 }
				 else
				 if(getParam("socLuas").equals("7")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas5")+"E"+getParam("txtLuas6")+"D"));
				 }
				 else
				 if(getParam("socLuas").equals("8")){
					 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas2")+"R"+getParam("txtLuas3")+"J"+getParam("txtLuas4")+"K"));
				 }
				 System.out.println((getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
				 //LUAS BARU
				 hRizabUpdate.put("txtLuas", getParam("txtLuas"));
				 hRizabUpdate.put("txtNoPelan", getParam("txtNoPelan"));
				 hRizabUpdate.put("txtNoPu", getParam("txtNoPu"));
				 hRizabUpdate.put("txtLokasi", getParam("txtLokasi"));	
				 hRizabUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				 hRizabUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));	
				//hRizabUpdate.put("socStatus", getParam("socStatus"));
				 hRizabUpdate.put("socStatus",getParam("socStatusDaftar"));
				 hRizabUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));
				 hRizabUpdate.put("txtNoLot", getParam("txtNoLot"));
				 hRizabUpdate.put("idMasuk", userId);
				 hRizabUpdate.put("catatan", getParam("txtKemAgenTerkini"));	
				 FrmRekodPendaftaranHakmilikRizabData.XupdateRizabById(hRizabUpdate);
				
				 //PAPAR SEMULA RIZAB YANG DIUPDATE
				 this.context.put("readonly", "readonly");
				 this.context.put("disabled", "disabled");	
				 this.context.put("mode", "view");

				 //VIEW MAKLUMAT RIZAB BY ID
				 viewModeRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			   
				 //20/10/2010
			}else if (nextAction.equals("papardetailhakmiliksamb")){
				vm = PATH+"frmRekodPendaftaranHakmilikSambungan.jsp";	
				isSambungan = false;
				this.context.put("readonly", "");
				this.context.put("disabled", "");	
				this.context.put("mode", "update");
				
				//Kemaskini Oleh Rosli pada 01/03/2011, kemaskini paparan kod hakmilik, no hakmilik  
				//noHakmilikAsal = view_modeMaklumatFail(session);
				noHakmilikAsal = viewMaklumatFailMengikutHakmilik(session);
				kemaskiniMaklumatHakmilik(noHakmilikAsal);
							   
			}else if (nextAction.equals("maklumathakmiliksambarureadonly")){	
				vm = PATH+"frmRekodPendaftaranHakmilik.jsp";	
				isSambungan = false;
				this.context.put("readonly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				
			    // VIEW HEADER (MASTER) BY ID
				noHakmilikAsal = viewMaklumatFailMengikutHakmilik(session);
				// VIEW MAKLUMAT HAKMILIK BY ID
				viewModeHakmilik(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
											   
			} //End of nextAction = maklumathakmilikbarureadonly
			
			if(selectedTab.equals("1")){
				context.put("UTIL", new ekptg.helpers.Utils());
				Vector vecSewa = getHakmilikPenyewaan().getMaklumat(getParam("idHakmilik"));
				Penyewaan sewa = new Penyewaan();
				if(!vecSewa.isEmpty()){
					Hashtable hashHakmilik = (Hashtable) vecSewa.get(0);
					Permohonan per = new Permohonan();
					PfdFail fail = new PfdFail();
					Pemohon pemohon = new Pemohon();
					HtpPerjanjian perjanjian = new HtpPerjanjian();
					fail.setNoFail(String.valueOf(hashHakmilik.get("noFail")));
					per.setPfdFail(fail);
					sewa.setPermohonan(per);
					pemohon.setNama(String.valueOf(hashHakmilik.get("pemohon")));
					sewa.setPemohon(pemohon);
					perjanjian.setNoRujukanPerjanjian(String.valueOf(hashHakmilik.get("noRujukan")));
					//perjanjian.setTarikhPerjanjian(tarikhPerjanjian)
					sewa.setHtpPerjanjian(perjanjian);
					sewa.setTarikhMula(new Date(String.valueOf(hashHakmilik.get("tarikhMula"))));
					sewa.setTarikhTamat(new Date(String.valueOf(hashHakmilik.get("tarikhTamat"))));
					sewa.setTempoh(String.valueOf(hashHakmilik.get("tempoh")));
					sewa.setKadar(Double.parseDouble(String.valueOf(hashHakmilik.get("kadar"))));
					
				}
				
				myLog.info(getParam("idHakmilik"));
				Vector vecPajakan = getHakmilikPajakan().getMaklumat(getParam("idHakmilik"));
				myLog.info(vecPajakan.size());
				Pajakan pajakan = new Pajakan();
				myLog.info(vecPajakan.isEmpty());
				if(!vecPajakan.isEmpty()){
					Hashtable hashHakmilik = (Hashtable) vecPajakan.get(0);
					Permohonan per = new Permohonan();
					PfdFail fail = new PfdFail();
					Pemohon pemohon = new Pemohon();
					HtpPerjanjian perjanjian = new HtpPerjanjian();
					myLog.info(hashHakmilik.get("noFail"));
					fail.setNoFail(String.valueOf(hashHakmilik.get("noFail")));
					per.setPfdFail(fail);
					pajakan.setPermohonan(per);
					pemohon.setNama(String.valueOf(hashHakmilik.get("pemohon")));
					pajakan.setPemohon(pemohon);
					//perjanjian.setNoRujukanPerjanjian(String.valueOf(hashHakmilik.get("noRujukan")));
					//perjanjian.setTarikhPerjanjian(tarikhPerjanjian)
					//sewa.setHtpPerjanjian(perjanjian);
					pajakan.setTarikhMulaPajakan(new Date(String.valueOf(hashHakmilik.get("tarikhMula"))));
					//pajakan.setTarikhTamatPajakan(new Date(String.valueOf(hashHakmilik.get("tarikhTamat"))));
					pajakan.setTempohPajakan(String.valueOf(hashHakmilik.get("tempoh")));
					pajakan.setKadarPajakan(Double.parseDouble(String.valueOf(hashHakmilik.get("kadar"))));
					
				}

				this.context.put("sewa", sewa);
				this.context.put("pajakan", pajakan);
				
			}
			this.context.put("selectedTab", selectedTab);
	
		// End of firstAction=PendaftaranHakmilik
		}else if(firstAction.equals("deleteHakmilikBaru")){	  
			vm = PATH+"frmRekodPendaftaranHakmilikjsp";
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
		}else if(firstAction.equals("PendaftaranPembangunan")){
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
		   
		   if(nextAction.equals("doChangeLuas")){
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

		   //TAMBAH PEMBANGUNAN HAKMILIK BY ID HAKMILIK
		   if(nextAction.equals("tambahDetailKeluasan")){
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
			
		   // VIEW PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   }else if(nextAction.equals("viewDetailKeluasan")){			
		       this.context.put("readOnly", "readOnly");
		       this.context.put("disabled", "disabled");
		       this.context.put("mode", "view");
		       view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal,nextAction);
		       
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
		   
		   }else if(nextAction.equals("paparsahajakeluasanterperinci")){
			   vm = PATH+"pembangunan/frmRekodPembangunanPentadbiranReadOnly.jsp";

		       this.context.put("readOnly", "readOnly");
		       this.context.put("disabled", "disabled");
		       this.context.put("mode", "view");
		       //view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal,nextAction);	       
				//VIEW LUAS TERKUMPUL
		       //view_modeLuasTerkumpul(session);
			   
		   //KEMASKINI PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   } else if(nextAction.equals("kemaskiniDetailKeluasan")){
		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
		       this.context.put("mode", "kemaskini");
		       view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal,nextAction);
		       
				//VIEW LUAS TERKUMPUL
			    view_modeLuasTerkumpul(session);
		       
		   }  
		   //UPDATE PEMBANGUNAN HAKMILIK BY ID HAKMILIKPERIHAL
		   if(nextAction.equals("updateDetailKeluasan")){

		       this.context.put("readOnly", "");
		       this.context.put("disabled", "");
		       this.context.put("mode", "update");
		       this.context.put("popupSkrin","popupSkrin");
			   Hashtable hPembangunanUpdate = new Hashtable();
			   
			   myLog.debug("%%% hakmilik periah="+idHakmilikPerihal);
			   
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
		   
		   
		   //DELETE REKOD IMAGE
		   }else if(nextAction.equals("deleteDetailPembangunan")){	  
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
		   
		//****************FOURTH PAGE PROCESS **************************
	    } else if(firstAction.equals("PendaftaranImej")){
			vm = PATH+"imej/frmRekodPembangunanImej.jsp";
			this.context.put("jenis_button","4");
			   
			// VIEW HEADER(MASTER) BY ID
			view_modeMaklumatFail(session);
			  
			//SENARAI IMEJ BY ID HAKMILIK
			listImej = view_modeSenaraiImej(session);
			this.context.put("SenaraiImej", listImej);
			//Vector senaraiIDist = (Vector)getIHakmilik().getMaklumatImejByIdHakmilikDist(getParam("idHakmilik"));
			this.context.put("SenaraiImejDist", listImej);
			myLog.info("SenaraiImejDist="+listImej.isEmpty());
			setupPagePentadbiran(session,action,listImej);
			   
		    this.context.put("readOnly", "");
		    this.context.put("disabled", "");		       
			this.context.put("txtPerihalImej","");
			this.context.put("txtRingkas","");
			this.context.put("txdTarikhKemaskiniImej","");
			this.context.put("mode", "new");			   
			this.context.put("num_files",1); // 2011/10/12 Ditambah oleh Mohamad Rosli untuk bil Upload Fail
			//ADD NEW DETAIL IMEJ
			if(nextAction.equals("tambahDetailImej")){				   
				uploadFiles(); 
				listImej = view_modeSenaraiImej(session);
				this.context.put("SenaraiImej", listImej);			   
			
				//VIEW DETAIL IMEJ
			}else if(nextAction.equals("viewDetailImej")){
				this.context.put("readOnly", "readOnly");
			    this.context.put("disabled", "disabled");
			    this.context.put("mode","view");	
			    
			    //VIEW IMAGE DETAIL BY ID GAMBAR
			    view_modeImejByIdGambar(session,idGambar);
			      
			    //KEMASKINI DETAIL IMEJ
			}else if(nextAction.equals("viewDetailInfo")){
				this.context.put("readOnly", "readOnly");
			    this.context.put("disabled", "disabled");
			    this.context.put("mode","viewx");	
			    
			    //VIEW IMAGE DETAIL BY ID GAMBAR
			    view_modeImejByIdGambar(session,idGambar);
			      
			    //KEMASKINI DETAIL IMEJ
			}else if(nextAction.equals("kemaskiniDetailImej")){
				this.context.put("readOnly", "");
				this.context.put("disabled", "");
				this.context.put("mode","kemaskini");					      
				//VIEW IMAGE DETAIL BY ID GAMBAR
				view_modeImejByIdGambar(session,idGambar);
			
				//UPDATE DETAIL IMEJ
			}else if(nextAction.equals("updateDetailImej")){
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
		      
		      //DELETE REKOD IMAGE
			}else if(nextAction.equals("deleteDetailImej")){	  
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
			//Tambah pada 2011/10/12 untuk bilangan fail upload
			RO_General = "readonly=\"readonly\"";
			if (actionI.equals("changeLampiran")) {
				RO_General = "";
				String x = getParam("X");
				actionI = x;
				int j = getParamAsInteger("jumlahlampiran");
//				context.put("SOC_Jadual", akta.getSOCJadual("SOC_Jadual", SOC_Jadual, Akta_IDAkta, RO_General, ""));
//				context.put("txtCatatan",getParam("txtCatatan"));
//				context.put("txtTajuk",getParam("txtTajuk"));
//				showJadualLampiranFail();
				this.context.put("num_files", j);
			}
			this.context.put("idHakmilik", getParam("idHakmilik"));
		
		}	

		this.context.put("flagAdvSearch", flagAdvSearch);
		this.context.put("cariIdNegeri", idNegeri);
		this.context.put("cariIdDaerah", idDaerah);
		this.context.put("cariIdMukim", idMukim);
		this.context.put("cariIdJenisTanah", idJenisTanah);	
		this.context.put("cariIdStatus", idStatus);	
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

		// Kemaskini 2012 04 06
		//list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
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
	
	// VIEW HEADER BY ID
	private String viewMaklumatFailMengikutHakmilik(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");	
		Vector list =null;
		// Kemaskini 2012 04 06
		//list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
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
		return idHakmilik;
		
	}
	
	// VIEW HEADER BY ID
	private String viewModeMaklumatFail(HttpSession session,String idHakmilik) throws Exception {
		vector = new Vector();
		// Kemaskini 2012 04 06
		//vector = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		vector = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
		Hashtable hMaklumatFail = (Hashtable) vector.get(0);	
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
		this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));

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
	//Add by rosli 30/06/2010
	
	   // VIEW MAKLUMAT HAKMILIK BY ID
	private void viewModeRizab(HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
		myLog.info("viewModeRizab:nextAction="+nextAction);
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikRizabData.getPaparRizabById(idHakmilik);
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
		this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
		this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));

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
			String luas = "0";
			String luas1 = "0";
			String luas2 = "0";
			//log.info("getParam(\"socLuas\")="+getParam("socLuas"));
			if(getParam("socLuas").equals("1") || getParam("socLuas").equals("2") || getParam("socLuas").equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
				//log.info("Belum viewMaklumatRizab:submit="+nextAction+":luas="+luas);
				if(getParam("socLuas").equals("1")){
					if(getParam("txtLuasGabung").contains("KM"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else
						luas = getParam("txtLuasGabung");
					
				}else if(getParam("socLuas").equals("2")){
					if(getParam("txtLuasGabung").contains("H"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
					
				}else if(getParam("socLuas").equals("3")){
					if(getParam("txtLuasGabung").contains("MP"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else{
						if(getParam("txtLuasGabung").contains("M"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
					}
					
				}else if(getParam("socLuas").equals("5")){
					if(getParam("txtLuasGabung").contains("KP"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else{
						if(getParam("txtLuasGabung").contains("K"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
					}
					
				}else if(getParam("socLuas").equals("6")){
					if(getParam("txtLuasGabung").contains("P"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
					
				}
				//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);
	
			}else if(getParam("socLuas").equals("4")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(getParam("txtLuasGabung").contains("E,") && getParam("txtLuasGabung").contains("R,")){
					luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E,"));
					luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("E,")+2,getParam("txtLuasGabung").indexOf("R,"));
					luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,(getParam("txtLuasGabung").length()-1));
				
				}
				
			}else if(getParam("socLuas").equals("8")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(getParam("txtLuasGabung").contains("R,") && getParam("txtLuasGabung").contains("J,")){
					luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("R,"));
					luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,getParam("txtLuasGabung").indexOf("J"));
					luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("J,")+2,(getParam("txtLuasGabung").length()-1));
				}
				
			}else{ //7||9 (TIADA SAMPLE DATA)
				luas = getParam("txtLuasGabung");
				
			}
			this.context.put("txtLuasLama1", luas1.trim());	
			this.context.put("txtLuasLama2", luas2.trim());	
			this.context.put("txtLuasLama", luas);
	

			
		
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
		this.context.put("txtLuas",getParam("txtLuas") == "" ? hHakmilik.get("luasConvert"):getParam("txtLuas"));
		this.context.put("txtNoLot",getParam("txtNoLot") == "" ? (String)hHakmilik.get("noLot"):getParam("txtNoLot"));
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("noHakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? "" : getParam("txtHakmilikBerikut"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		//log.info("05/10/2010 : getParam(\"socStatus\")="+getParam("socStatus"));
		this.context.put("socStatus",getParam("socStatusDaftar").equals("") ? (String)hHakmilik.get("socStatus"):getParam("socStatusDaftar"));
		//this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));

		
		if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){
				
		}
		
	}	
	
	//add by Rosli 14/10/2010
	// VIEW MAKLUMAT HAKMILIK BY ID
	private void viewModeHakmilik(HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
		myLog.info("viewModeHakmilik:nextAction="+nextAction);
		String idHakmilik = getParam("idHakmilik");
		isSambungan = false;
		vector = new Vector();
		vector = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
		Hashtable hHakmilik = null;
		if (vector.size() > 0) {
			hHakmilik = (Hashtable) vector.get(0);
			
		} else {
			throw new Exception("Maklumat Rekod Tidak Lengkap");
			
		}
		socStatusTemp = String.valueOf(hHakmilik.get("socStatus"));
		
		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("statusBatal", (String)hHakmilik.get("socStatus"));
		this.context.put("txtKodSocJenisHakmilik", (String)hHakmilik.get("kodJenisHakmilik"));
		myLog.info("socStatusTemp:"+hHakmilik.get("socStatus"));
		if(socStatusTemp.equals("S") || socStatusTemp.equals("B")){
			isSambungan = true;	
		}
		myLog.info("isSambungan:"+isSambungan);

		this.context.put("idHakmilikCukai", (String)hHakmilik.get("idHakmilikCukai"));		
		this.context.put("socStatusTanah", (String)hHakmilik.get("socStatusTanah"));
    	this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
    	this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));	
		this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));

		this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));

		if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){
			
			//this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), ""," style=\"width:200px\"")); 
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")), " style=\"width:200px\" onChange=\"doChangeStateHR();\""));
	    	this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),""," style=\"width:200px\" onChange=\"doChangeDaerahHR();\""));
	    	this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR"))," style=\"width:200px\""));		
	    	//this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")), " style=\"width:200px\""));
	    	this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong(getParam("socJenisHakmilikHR") == "" ?(String)hHakmilik.get("idJenisHakmilikHR"):getParam("socJenisHakmilikHR")), " style=\"width:200px\""));
	    	
	    	//this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")), " style=\"width:200px\""));
			//myLog.info("getParam(\"socLotHR\"):"+getParam("socLotHR"));
			this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong(String.valueOf(getParam("socLotHR")==""?hHakmilik.get("idLot"):getParam("socLotHR"))), " style=\"width:200px\""));	    	
	    	//this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style=\"width:200px\""));
		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
			this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
			
			if ("doChangeStateHR".equals(lastAction)) {				
				//myLog.info("doChangeStateHR:");
				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\" style=\"width:200px\""));
				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\" style=\"width:200px\""));	
				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
			}
			
			if ("doChangeDaerahHR".equals(lastAction)) {				
				//myLog.info("doChangeDaerahHR:");
				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\"  style=\"width:200px\""));
				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\"  style=\"width:200px\""));
				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
			}

			
		}else if(nextAction.equals("updateDetailHakmilik")|| nextAction.equals("updateDetailRizab")
				||nextAction.contains("paparDetailHakmilik")||nextAction.equals("paparDetailRizab")){
			
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
		    this.context.put("selectJenisHakmilikHR", getParam("socJenisHakmilikHR") == "" ? hHakmilik.get("kodJenisHakmilikHR"):getParam("socJenisHakmilikHR"));		    
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
		//this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? hHakmilik.get("luasConvert"):getParam("txtLuas"));
		this.context.put("txtNoLot",getParam("txtNoLot") == "" ? (String)hHakmilik.get("noLot"):getParam("txtNoLot"));
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("noHakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? "" : getParam("txtHakmilikBerikut"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		//log.info("viewModeHakmilik:socStatus="+getParam("socStatus"));
		//this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("socStatus",socStatusTemp);
		//14/10/2010
		if(nextAction.equals("kemaskiniDetailHakmilik")){
			String luas = "0";
			String luas1 = "0";
			String luas2 = "0";
			//log.info("getParam(\"socLuas\")="+getParam("socLuas"));
			if(getParam("socLuas").equals("1") || getParam("socLuas").equals("2") || getParam("socLuas").equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
				//log.info("Belum viewMaklumatRizab:submit="+nextAction+":luas="+luas);
				if(getParam("socLuas").equals("1")){
					if(getParam("txtLuasGabung").contains("KM"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else
						luas = getParam("txtLuasGabung");
					
				}else if(getParam("socLuas").equals("2")){
					if(getParam("txtLuasGabung").contains("H"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
					
				}else if(getParam("socLuas").equals("3")){
					if(getParam("txtLuasGabung").contains("MP"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else{
						if(getParam("txtLuasGabung").contains("M"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
					}
					
				}else if(getParam("socLuas").equals("5")){
					if(getParam("txtLuasGabung").contains("KP"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
					else{
						if(getParam("txtLuasGabung").contains("K"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
					}
					
				}else if(getParam("socLuas").equals("6")){
					if(getParam("txtLuasGabung").contains("P"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
					
				}
				//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);
	
			}else if(getParam("socLuas").equals("4")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(getParam("txtLuasGabung").contains("E,") && getParam("txtLuasGabung").contains("R,")){
					luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E,"));
					luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("E,")+2,getParam("txtLuasGabung").indexOf("R,"));
					luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,(getParam("txtLuasGabung").length()-1));
				
				}
				
			}else if(getParam("socLuas").equals("8")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(getParam("txtLuasGabung").contains("R,") && getParam("txtLuasGabung").contains("J,")){
					luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("R,"));
					luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,getParam("txtLuasGabung").indexOf("J"));
					luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("J,")+2,(getParam("txtLuasGabung").length()-1));
				}
				
			}else{ //7||9 (TIADA SAMPLE DATA)
				luas = getParam("txtLuasGabung");
				
			}
			this.context.put("txtLuasLama1", luas1.trim());	
			this.context.put("txtLuasLama2", luas2.trim());	
			this.context.put("txtLuasLama", luas);		
			this.context.put("txtLuas",getParam("txtLuas"));
				
			//log.info("viewModeHakmilik:socLuas="+getParam("socLuas"));
			//log.info("viewModeHakmilik:socStatus="+getParam("socStatus"));
			myLog.info("viewModeHakmilik:socStatusDaftar="+getParam("socStatusDaftar"));
			this.context.put("socStatus",getParam("socStatusDaftar"));
			//this.context.put("socStatus",getParam("socStatus"));

		}
		
	}
	
	//add by Rosli 18/10/2010
	// VIEW MAKLUMAT HAKMILIK & SAMBUNGAN
	private void viewModeHakmilikSambungan(HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar, String idJenisHakmilikBaru) throws Exception {
		myLog.info("viewModeHakmilikSambungan:nextAction="+nextAction);
		//String idHakmilik = getParam("idHakmilik");
		vector = new Vector();
		vector = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
		hastableHakmilik = new Hashtable();
		if (vector.size() > 0) {
			hastableHakmilik = (Hashtable) vector.get(0);
			
		} else {
			//throw new Exception("Maklumat Rekod Tidak Lengkap");
			throw new Exception(getIHTP().getErrorHTML("MAKLUMAT REKOD TIDAK LENGKAP"));
		
		}
		
		socStatusTemp = getParam("socStatusDaftar");		
		if(socStatusTemp.equals("S") || socStatusTemp.equals("B")){
			isSambungan = true;	
		}		
		//log.info("socStatusTemp:"+hHakmilik.get("socStatus"));
		//log.info("isSambungan:"+isSambungan);		
		this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(String.valueOf(hastableHakmilik.get("idNegeriHR")))," ", " style=\"width:200px\" onChange=\"doChangeStateHR();\" "));
		this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(String.valueOf(hastableHakmilik.get("idNegeriHR")),"socDaerahHR", Utils.parseLong(String.valueOf(hastableHakmilik.get("idDaerahHR")))," "," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" "));
	    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(String.valueOf(hastableHakmilik.get("idDaerahHR")),"socMukimHR", Utils.parseLong(String.valueOf(hastableHakmilik.get("idMukimHR")))," "," style=\"width:200px\" "));
		this.context.put("idHakmilik", String.valueOf(hastableHakmilik.get("idHakmilik")));
		this.context.put("statusBatal", socStatusTemp);	
		this.context.put("txtKodSocJenisHakmilik", String.valueOf(hastableHakmilik.get("kodJenisHakmilik")));		
		this.context.put("idHakmilikCukai", String.valueOf(hastableHakmilik.get("idHakmilikCukai")));		
		//this.context.put("socStatusTanah", String.valueOf(hastableHakmilik.get("socStatusTanah")));
    	
		this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  String.valueOf(hastableHakmilik.get("tarikhDaftar")):getParam("txdTarikhDaftar"));
	   	String strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? strdate:getParam("txdTarikhTerima"));	
		this.context.put("socTaraf", getParam("socTaraf") == "" ? String.valueOf(hastableHakmilik.get("socTaraf")):getParam("socTaraf"));
		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? String.valueOf(hastableHakmilik.get("tempoh")):getParam("txtTempoh"));
		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ?String.valueOf(hastableHakmilik.get("tarikhLuput")):getParam("txdTarikhLuput"));
		this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? String.valueOf(hastableHakmilik.get("cukai")):getParam("txtCukaiTahun"));
		this.context.put("txtCukaiTerkini","");
    	this.context.put("txtLokasi",getParam("txtLokasi") == "" ? String.valueOf(hastableHakmilik.get("lokasi")):getParam("txtLokasi"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? String.valueOf(hastableHakmilik.get("hakmilikAsal")):getParam("txtHakmilikAsal"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? String.valueOf(hastableHakmilik.get("kegunaanTanah")):getParam("txtKegunaanTanah"));
		this.context.put("socLuas", getParam("socLuas") == "" ? String.valueOf(hastableHakmilik.get("idLuasLama")):getParam("socLuas"));

		String luas = "0";
		String luas1 = "0";
		String luas2 = "0";
		//log.info("getParam(\"socLuas\")="+getParam("socLuas"));
		if(getParam("socLuas").equals("1") || getParam("socLuas").equals("2") || getParam("socLuas").equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
			//log.info("Belum viewMaklumatRizab:submit="+nextAction+":luas="+luas);
			if(getParam("socLuas").equals("1")){
				if(getParam("txtLuasGabung").contains("KM"))
					luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
				else
					luas = getParam("txtLuasGabung");
				
			}else if(getParam("socLuas").equals("2")){
				if(getParam("txtLuasGabung").contains("H"))
					luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
				else
					luas = getParam("txtLuasGabung");
				
			}else if(getParam("socLuas").equals("3")){
				if(getParam("txtLuasGabung").contains("MP"))
					luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
				else{
					if(getParam("txtLuasGabung").contains("M"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
				}
				
			}else if(getParam("socLuas").equals("5")){
				if(getParam("txtLuasGabung").contains("KP"))
					luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
				else{
					if(getParam("txtLuasGabung").contains("K"))
						luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
					else
						luas = getParam("txtLuasGabung");
				}
				
			}else if(getParam("socLuas").equals("6")){
				if(getParam("txtLuasGabung").contains("P"))
					luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
				else
					luas = getParam("txtLuasGabung");
				
			}
			//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);

		}else if(getParam("socLuas").equals("4")){
			//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
			if(getParam("txtLuasGabung").contains("E,") && getParam("txtLuasGabung").contains("R,")){
				luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E,"));
				luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("E,")+2,getParam("txtLuasGabung").indexOf("R,"));
				luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,(getParam("txtLuasGabung").length()-1));
			
			}
			
		}else if(getParam("socLuas").equals("8")){
			//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
			if(getParam("txtLuasGabung").contains("R,") && getParam("txtLuasGabung").contains("J,")){
				luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("R,"));
				luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,getParam("txtLuasGabung").indexOf("J"));
				luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("J,")+2,(getParam("txtLuasGabung").length()-1));
			}
			
		}else{ //7||9 (TIADA SAMPLE DATA)
			luas = getParam("txtLuasGabung");
			
		}
		this.context.put("txtLuasLama1", luas1.trim());	
		this.context.put("txtLuasLama2", luas2.trim());	
		this.context.put("txtLuasLama", luas);		
		this.context.put("txtLuas",getParam("txtLuas"));
		this.context.put("txtPegawaiAkhir", String.valueOf(hastableHakmilik.get("userName")));
		this.context.put("txdTarikhKemaskini", String.valueOf(hastableHakmilik.get("tarikhKemaskini")));
		
		// Maklumat sebelah kanan
		this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong(String.valueOf(hastableHakmilik.get("idJenisHakmilikHR"))), " style=\"width:200px\""));
		//this.context.put("txtNoHakmilik","") ; //telah disetkan di maklumat fail	
	    this.context.put("txtNoBangunan",getParam("txtNoBangunan") == "" ? String.valueOf(hastableHakmilik.get("noBangunan")):getParam("txtNoBangunan"));
		this.context.put("txtNoTingkat",getParam("txtNoTingkat") == "" ? String.valueOf(hastableHakmilik.get("noTingkat")):getParam("txtNoTingkat"));
		this.context.put("txtNoPetak",getParam("txtNoPetak") == "" ? String.valueOf(hastableHakmilik.get("noPetak")):getParam("txtNoPetak"));
	    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong(String.valueOf(getParam("socLotHR"))), " style=\"width:200px\""));
		//this.context.put("txtNoLot","") ; //telah disetkan di maklumat fail		    		
		this.context.put("txtNoLot","") ; //reset semula	    		
		this.context.put("socRizab", getParam("socRizab") == "" ? String.valueOf(hastableHakmilik.get("socRizab")):getParam("socRizab"));
		this.context.put("txtNoRizab",getParam("txtNoRizab") == "" ?String.valueOf(hastableHakmilik.get("noRizab")):getParam("txtNoRizab"));
		this.context.put("txdTarikhRizab",getParam("txdTarikhRizab") == "" ?String.valueOf(hastableHakmilik.get("tarikhRizab")):getParam("txdTarikhRizab"));    	
	    this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong(String.valueOf(getParam("socJenisRizab"))), " style='width:200px;'"));
		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? String.valueOf(hastableHakmilik.get("kawasanRizab")):getParam("txtKawasanRizab"));
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong(String.valueOf(hastableHakmilik.get("idKategori"))), " style='width:200px;'"));
		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? String.valueOf(hastableHakmilik.get("syarat")):getParam("txtSyarat"));
		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? String.valueOf(hastableHakmilik.get("sekatan")):getParam("txtSekatan"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? String.valueOf(hastableHakmilik.get("catatan")):getParam("txtKemAgenTerkini"));
		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? String.valueOf(hastableHakmilik.get("noPelan")):getParam("txtNoPelan"));
		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? String.valueOf(hastableHakmilik.get("noSyit")):getParam("txtNoSyit"));
		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? String.valueOf(hastableHakmilik.get("noPu")):getParam("txtNoPu"));
				
    	this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong(String.valueOf(hastableHakmilik.get("idLuasLama"))), "disabled"," style=\"width:200px\" class=\"disabled\""));
		
		this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru",Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? String.valueOf(hastableHakmilik.get("noFailJopa")):getParam("txtNoFailJopa"));
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? String.valueOf(hastableHakmilik.get("noHakmilikAsal")):getParam("txtNoHakmilikAsal"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? "" : getParam("txtHakmilikBerikut"));
		this.context.put("socStatus",socStatusTemp);	
		myLog.info("1626 - socStatusTemp:"+socStatusTemp);
		//this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? String.valueOf(hastableHakmilik.get("tarafHakmilik")):getParam("txtTarafHakmilik"));
		//this.context.put("statusRizab", String.valueOf(hastableHakmilik.get("statusRizab")));
		
		if (lastAction.indexOf("doChange") != -1) {
				
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\" style=\"width:200px\""));
			this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\" style=\"width:200px\""));	
			this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));

			this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini"));
			this.context.put("txtNoLot",getParam("txtNoLot"));
			
			//this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
			//this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
		    //this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectNegeriHR",String.valueOf(hastableHakmilik.get("namaNegeriHR")));
			//this.context.put("selectDaerahHR",String.valueOf(hastableHakmilik.get("namaDaerahHR")));
		    //this.context.put("selectMukimHR", String.valueOf(hastableHakmilik.get("namaMukimHR")));
			//this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectJenisLotHR", String.valueOf(hastableHakmilik.get("namaLot")));
		    //this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\" class=\"disabled\""));		    
		    //this.context.put("selectJenisHakmilikHR", String.valueOf(hastableHakmilik.get("kodJenisHakmilikHR"));		    
		    //this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		    //this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong(String.valueOf(hastableHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
			//this.context.put("selectKategori", String.valueOf(hastableHakmilik.get("namaKategori"));
			//this.context.put("selectRizab", String.valueOf(hastableHakmilik.get("namaJenisRizab"));

		}	
		
		
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
		this.context.remove("SenaraiImejDist");
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
	        myLog.info(name +"="+value);
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
		 myLog.info("tarikh : "+txdTarikhKemaskini+"currentDate : "+currentDate);
		 try {
			 db = new Db();
			 long idGambar = DB.getNextID("TBLHTPGAMBAR_SEQ");
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("insert into TBLHTPGAMBAR " +
	        			"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,nama_fail,jenis_mime,tarikh_kemaskini) " +
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
	 
	 public void XsetupPage(HttpSession session,String action,Vector list) {
		 try {
			 if (list.isEmpty() || list == null) {
				 this.context.put("totalRecords",0);
				 this.context.put("SenaraiFail","");
				 this.context.put("page",0);
				 this.context.put("itemsPerPage",0);
				 this.context.put("totalPages",0);
				 this.context.put("startNumber",0);
				 this.context.put("isFirstPage",true);
				 this.context.put("isLastPage",true);
				    
			 }else {
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
				 this.context.put("SenaraiFail",paging.getPage(page));
				 this.context.put("page", new Integer(page));
				 this.context.put("itemsPerPage", new Integer(itemsPerPage));
				 this.context.put("totalPages", new Integer(paging.getTotalPages()));
				 this.context.put("startNumber", new Integer(paging.getTopNumber()));
				 this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
				 this.context.put("isLastPage", new Boolean(paging.isLastPage()));
			 }
				        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
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
		
		// VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
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
			//this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
			this.context.put("socStatus",getParam("socStatusDaftar") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatusDaftar"));
			this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
			this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
			this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
			this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));	
			this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtNoHakmilikAsal"));
			this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
			
			hakmilik = getHakmilik().getHakmilik(idHakmilik);

			return (String)hHakmilik.get("kodJenisHakmilik")+hHakmilik.get("noHakmilik");
		}	
		
		  // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
		private void view_modeHakmilikRizab(HttpSession session,String submit) throws Exception {
			String idHakmilik = getParam("idHakmilik");
			myLog.info("idHakmilik :"+id);
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
		
		//Tambah oleh Rosli pada 01/03/2011, terus papar mode kemaskini maklumat hakmilik
		//paparan maklumat hakmilik dan bersedia untuk dikemaskini
		private void kemaskiniMaklumatHakmilik(String idHakmilik) throws Exception {
			myLog.info("kemaskiniMaklumatHakmilik:="+idHakmilik);
			isSambungan = false;
			vector = new Vector();
			vector = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
			Hashtable hHakmilik = null;
			if (vector.size() > 0) {
				hHakmilik = (Hashtable) vector.get(0);
				
			} else {
				//throw new Exception("Maklumat Rekod Tidak Lengkap");
				throw new Exception(getIHTP().getErrorHTML("MAKLUMAT REKOD TIDAK LENGKAP"));
				
			}
			socStatusTemp = String.valueOf(hHakmilik.get("socStatus"));			
			this.context.put("idHakmilik", String.valueOf(hHakmilik.get("idHakmilik")));
			this.context.put("statusBatal", socStatusTemp);
			this.context.put("txtKodSocJenisHakmilik", String.valueOf(hHakmilik.get("kodJenisHakmilik")));
			this.context.put("idHakmilikCukai", String.valueOf(hHakmilik.get("idHakmilikCukai")));		
			this.context.put("socStatusTanah", String.valueOf(hHakmilik.get("socStatusTanah")));
	    	this.context.put("txdTarikhDaftar",String.valueOf(hHakmilik.get("tarikhDaftar")));
	    	this.context.put("txtCukaiTahun",String.valueOf(hHakmilik.get("cukai")));
			this.context.put("txtLokasi",String.valueOf(hHakmilik.get("lokasi")));
			this.context.put("txdTarikhTerima",String.valueOf(hHakmilik.get("tarikhTerima")));	
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong(String.valueOf(hHakmilik.get("idJenisRizab"))), " style='width:200px;'"));

			String strIdLuas = hHakmilik.get("idLuasLama")=="0"?"0":String.valueOf(hHakmilik.get("idLuasLama"));
			String strLuas = hHakmilik.get("luasLama")==""?"":String.valueOf(hHakmilik.get("luasLama"));

			this.context.put("txtLuasLama",strLuas);
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(String.valueOf(hHakmilik.get("idNegeriHR"))), " style=\"width:200px\" onChange=\"doChangeStateHR();\""));
			this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),""," style=\"width:200px\" onChange=\"doChangeDaerahHR();\""));
		    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR"))," style=\"width:200px\""));		
		    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")), " style=\"width:200px\""));
			this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong(String.valueOf(hHakmilik.get("idLot"))), " style=\"width:200px\""));	    	
			this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
			
			this.context.put("txtNoBangunan",String.valueOf(hHakmilik.get("noBangunan")));
			this.context.put("txtNoTingkat",String.valueOf(hHakmilik.get("noTingkat")));
			this.context.put("txtNoPetak",String.valueOf(hHakmilik.get("noPetak")));
			this.context.put("txtNoPelan",String.valueOf(hHakmilik.get("noPelan")));
			this.context.put("txtTempoh",String.valueOf(hHakmilik.get("tempoh")));
			this.context.put("txtSyarat",String.valueOf(hHakmilik.get("syarat")));
			this.context.put("txtHakmilikAsal",String.valueOf(hHakmilik.get("hakmilikAsal")));
			this.context.put("txtNoFailJopa",String.valueOf(hHakmilik.get("noFailJopa")));
			this.context.put("txtTarafHakmilik",String.valueOf(hHakmilik.get("tarafHakmilik")));
			this.context.put("txdTarikhLuput",String.valueOf(hHakmilik.get("tarikhLuput")));
			this.context.put("txtCukaiTerkini",String.valueOf(hHakmilik.get("cukaiTerkini")));
			this.context.put("txtLuas",String.valueOf(hHakmilik.get("luas")));
			this.context.put("txtNoPu",String.valueOf(hHakmilik.get("noPu")));
			this.context.put("txdTarikhWarta",String.valueOf(hHakmilik.get("tarikhWarta")));
			this.context.put("txtNoWarta",String.valueOf(hHakmilik.get("noWarta")));
			this.context.put("txtNoRizab",String.valueOf(hHakmilik.get("noRizab")));
			this.context.put("txdTarikhRizab",String.valueOf(hHakmilik.get("tarikhRizab")));
			this.context.put("txtKawasanRizab",String.valueOf(hHakmilik.get("kawasanRizab")));
			this.context.put("txtNoSyit",String.valueOf(hHakmilik.get("noSyit")));
			this.context.put("txtSekatan",String.valueOf(hHakmilik.get("sekatan")));
			this.context.put("txtHakmilikBerikut",String.valueOf(hHakmilik.get("hakmilikBerikut")));
			this.context.put("socTaraf", String.valueOf(hHakmilik.get("socTaraf")));
			this.context.put("socRizab", String.valueOf(hHakmilik.get("socRizab")));
			this.context.put("statusRizab", String.valueOf(hHakmilik.get("statusRizab")));
			this.context.put("txdTarikhKemaskini", String.valueOf(hHakmilik.get("tarikhKemaskini")));
			this.context.put("txtPegawaiAkhir", String.valueOf(hHakmilik.get("userName")));
			
			this.context.put("socLuas", strIdLuas);
			this.context.put("txtLuas",String.valueOf(hHakmilik.get("luasConvert")));
			this.context.put("txtNoLot",String.valueOf(hHakmilik.get("noLot")));
			this.context.put("txtNoHakmilikAsal",String.valueOf(hHakmilik.get("noHakmilikAsal")));
			this.context.put("txtKemAgenTerkini",String.valueOf(hHakmilik.get("catatan")));
			this.context.put("txtHakmilikBerikut","");
			this.context.put("txtKegunaanTanah",String.valueOf(hHakmilik.get("kegunaanTanah")));
			//log.info("viewModeHakmilik:socStatus="+getParam("socStatus"));
			//this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
			this.context.put("socStatus",socStatusTemp);
			//14/10/2010
			String luas = "0";
			String luas1 = "0";
			String luas2 = "0";

			if(strIdLuas.equals("1") || strIdLuas.equals("2") || strIdLuas.equals("3") || strIdLuas.equals("5") || strIdLuas.equals("6")){
				if(strIdLuas.equals("1")){
					if(strLuas.contains("KM"))
						luas = strLuas.substring(0, (strLuas.length()-2));
					else
						luas = strLuas;
					
				}else if(strIdLuas.equals("2")){
					if(strLuas.contains("H"))
						luas = strLuas.substring(0, (strLuas.length()-1));
					else
						luas = strLuas;
					
				}else if(strIdLuas.equals("3")){
					if(strLuas.contains("MP"))
						luas = strLuas.substring(0, (strLuas.length()-2));
					else{
						if(strLuas.contains("M"))
							luas = strLuas.substring(0, (strLuas.length()-1));
						else
							luas = strLuas;
					}
					
				}else if(strIdLuas.equals("5")){
					if(strLuas.contains("KP"))
						luas = strLuas.substring(0, (strLuas.length()-2));
					else{
						if(strLuas.contains("K"))
							luas = strLuas.substring(0, (strLuas.length()-1));
						else
							luas = strLuas;
					}
					
				}else if(strIdLuas.equals("6")){
					if(strLuas.contains("P"))
						luas = strLuas.substring(0, (strLuas.length()-1));
					else
						luas = strLuas;
					
				}
				//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);
	
			}else if(strIdLuas.equals("4")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(strLuas.contains("E,") && strLuas.contains("R,")){
					luas = strLuas.substring(0,strLuas.indexOf("E,"));
					luas1 = strLuas.substring(strLuas.indexOf("E,")+2,strLuas.indexOf("R,"));
					luas2 = strLuas.substring(strLuas.indexOf("R,")+2,(strLuas.length()-1));
				
				}
				
			}else if(strIdLuas.equals("8")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+strLuas);
				if(strLuas.contains("R,") && strLuas.contains("J,")){
					luas = strLuas.substring(0,strLuas.indexOf("R,"));
					luas1 = strLuas.substring(strLuas.indexOf("R,")+2,strLuas.indexOf("J"));
					luas2 = strLuas.substring(strLuas.indexOf("J,")+2,(strLuas.length()-1));
				}
				
			}else{ //7||9 (TIADA SAMPLE DATA)
				luas = strLuas;
				
			}
			
			this.context.put("txtLuasLama1", luas1.trim());	
			this.context.put("txtLuasLama2", luas2.trim());	
			this.context.put("txtLuasLama", luas);		
			
		}		
		
		private ITanahCarian getIHakmilikRizab(){
			if (iHakmilikRizab == null){
				iHakmilikRizab = new FrmHakmilikRizabBean();
			}
			return iHakmilikRizab;
		}
		
		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}	

		private ITanah getIHakmilik(){
			if (iHakmilik==null){
				iHakmilik=new TanahBean();
			}
			return iHakmilik;
		}
		
		private HakmilikInterface getHakmilik(){
			if (iHakmilik_==null){
				iHakmilik_=new HakmilikBean();
			}
			return iHakmilik_;
		}
		
		private ITanahUrusan getHakmilikPenyewaan(){
			if(iHakmilikStatus== null)
				iHakmilikStatus = new FrmHakmilikUrusanPenyewaanBean();
			return iHakmilikStatus;
		}
		private ITanahUrusan getHakmilikPajakan(){
			if(iHakmilikStatusP== null)
				iHakmilikStatusP = new FrmHakmilikUrusanPajakanBean();
			return iHakmilikStatusP;
		}

				
}	
