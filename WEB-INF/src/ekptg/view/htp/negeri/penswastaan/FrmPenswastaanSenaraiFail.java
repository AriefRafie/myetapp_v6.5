package ekptg.view.htp.negeri.penswastaan;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmFailLainKemaskiniData;
import ekptg.model.htp.FrmPenswastaan2SenaraiFailData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;
/**
 * 
 *
 */
public class FrmPenswastaanSenaraiFail extends AjaxBasedModule {
	
	private final String jenisAkses="Readonly";
	private final String PATH = "app/htp/negeri/penswastaan/fail/";
	private InternalUser iu = null;
	private static final long serialVersionUID = 1L;
	private HtpPermohonan htpPermohonan = null;
 	private IHtp iHTP = null;  
	private Permohonan permohonan = null;
	private PfdFail fail = null;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.penswastaan.FrmPenswastaanSenaraiFail.class);	
	FrmPenswastaan2SenaraiFailData logic = new FrmPenswastaan2SenaraiFailData();
	//14/03/2011
	String flagAdvSearch = "";
    String idNegeri = "";
 	private String DISABILITY = " disabled class=disabled ";
	private IHTPFail iHTPFail = null;  
 	private String noFail = "";
 	private String tajuk = "";
 	private String idKementerian = "";
 	private String idAgensi = "";			
 	private String idDaerah = "";
 	private String idMukim = "";
	private String tarikhPermohonan = "";

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		Boolean postDB = false;
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String actionPenswastaan = getParam("actionPenswastaan"); // our main submit
		String doPost = session.getAttribute("doPost").toString();
		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		String selectedTab = getParam("selectedTab");
    	String strdate = "";
		String submit = getParam("command"); // for doAjax only
		String vm = "";
		
		try {

		// VECTOR
		Vector beanMaklumatPermohonan = null;
		Vector list = null;		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		if (doPost.equals("true")) {
			postDB = true;
		}		
		if (selectedTab.equals("") || selectedTab.equals(null)) {
			selectedTab = "0";
		}				
		//GET DROPDOWN PARAM
//        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			//idNegeri = "99999";
			iu = InternalUserUtil.getSeksyenId(userId);
			idNegeri = iu.getIdNegeri();

		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		flagAdvSearch = getParam("flagAdvSearch");
		myLog.info("actionPenswastaan="+actionPenswastaan+",flagAdvSearch="+flagAdvSearch);
		myLog.info("idFail="+idFail);
		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), session);
         		mode = "view";
    			htpPermohonan = getIHTPFail().findPermohonan(idFail, "","");
    			AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+htpPermohonan.getPermohonan().getPfdFail().getNoFail()+"] DITAMBAH ");
         		session.setAttribute("idFail", idFail);
        	}
    		if ("saveUpdate".equals(hitButton)){
         		logic.saveUpdate(idFail, getParam("txtNoFailKJP"), getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), session);
        	}
    	}
		
		if (actionPenswastaan.equals("papar")){		//GO TO PAPAR PENSWASTAAN		
			myLog.info("papar");
			vm = PATH+"frmDaftar.jsp";			
			Hashtable hashPermohonan = getIHTPFail().getMaklumatPermohonan(idFail);
			idPermohonan = String.valueOf(hashPermohonan.get("idPermohonan"));
			//mylog.info("idPermohonan:"+ hashPermohonan.get("idPermohonan"));		
			session.setAttribute("idFail", idFail);
			//htpPermohonan = getIHTP().findPermohonan(idFail, "","");
			htpPermohonan = getIHTPFail().findPermohonan(idFail, "","");
			context.put("permohonan", htpPermohonan);
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashPermohonan.get("idNegeri")), " disabled", " class=\"disabled\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"
					, Long.parseLong((String) hashPermohonan.get("idKementerian")), "disabled"
					, " class=\"disabled\" style=\"width:400\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi"
					, (String) hashPermohonan.get("idKementerian"), Long.parseLong((String) hashPermohonan.get("idAgensi"))
					, "disabled", " class=\"disabled\" style=\"width:400\""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("4", "socSuburusan" ,Long.parseLong((String) hashPermohonan.get("idSuburusan")), "disabled", " class=\"disabled\""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong((String) hashPermohonan.get("idStatusTanah")), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong((String) hashPermohonan.get("idJenisFail")), "disabled", " class=\"disabled\""));
			
			if ("view".equals(mode)){				
				this.context.put("readonly", "readonly");
	        	this.context.put("inputTextClass", "disabled");
	        	list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
		       	this.context.put("AgendaMesyuarat",list);
				this.context.put("mode","New");
		       	this.context.put("idFailLama",idFail);

			} else if ("update".equals(mode)){				
				this.context.put("readonly", "");
	        	this.context.put("inputTextClass", "");
	        	
			}
			
		} else if ("daftarBaru".equals(actionPenswastaan)){
			vm = PATH+"frmDaftar.jsp"; 	
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			beanMaklumatPermohonan.addElement(hashPermohonan);

			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);			
			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("4", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
			
		} else {	// SENARAI FAIL PENSWASTAAN
			vm = PATH+"index.jsp";
			list = new Vector();
			getValuesCarian();
			if(flagAdvSearch.equals("Y")){
				//list = logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),null);
				list = getIHTPFail().getSenaraiFail(null, noFail, tajuk
						, idKementerian.equals("99999")?"":idKementerian, idAgensi.equals("99999")?"":idAgensi, idNegeri, idDaerah, idMukim
						, "4", tarikhPermohonan, false);
			}else{
				list = logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),userId);
			
			}		
			if(submit.equals("reset")){
				noFail = "";
				tajuk = "";
			}
			context.put("txtNoFail", noFail);
			context.put("txtTajukFail", tajuk);		
			context.put("txtTajukCarian", tajuk);					
			setupPage(session, action, list);

		}
		
		}catch(Exception e){	
			e.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[HTP PENSWASTAAN] SENARAI FILES"));
			
		}
		
		// SET DEFAULT PARAM
		this.context.put("actionPenswastaan", actionPenswastaan);
		this.context.put("selectedTab", selectedTab);
		this.context.put("mode", mode);
    	
		strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    	this.context.put("sekarang",strdate);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
    	this.context.put("flagAdvSearch",flagAdvSearch);	
	    this.context.put("jenisAkses", jenisAkses);
	
		return vm;
		
	}

	private void getValuesCarian()throws Exception {
		myLog.info("getValuesCarian:mula");
		//String idNegeri = getParam("socNegeri");		
		idKementerian = getParam("socKementerian");
		idAgensi = getParam("socAgensi");			
		noFail = getParam("txtNoFail");
		tajuk = getParam("txtTajukFail");		
		String namaPemohon = getParam("txtNamaPemohon");
		tarikhPermohonan = getParam("txdTarikhTerima");
		String socDaerah = "";
		String socMukim = "";
		idDaerah = getParam("socDaerah");
		idMukim = getParam("socMukim");

		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
				
		fail.setIdNegeri(idNegeri);
		fail.setIdKementerian(idKementerian);
		fail.setNoFail(noFail);
		
		htpPermohonan.setIdAgensi(idAgensi);		
		permohonan.setTujuan(tajuk);		
		permohonan.setPfdFail(fail);
		htpPermohonan.setNoRujukanLain(namaPemohon);	
		permohonan.setTarikhTerima(tarikhPermohonan);			
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri", htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(), "",DISABILITY));
		context.put("socKementerian",HTML.SelectKementerian("socKementerian",htpPermohonan.getPermohonan().getPfdFail().getIdKementerian(), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(), "", " style=\"width:400\""));
		socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(idDaerah),null, "onChange=\"cmdChangeDaerahCarian()\"");
		socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim) , "");
	    this.context.put("socDaerah", socDaerah);
	    this.context.put("socMukim", socMukim);
		myLog.info("getValuesCarian:akhir");
		
	}
	
	private void getValuesPermohonan()throws Exception {
		idKementerian = getParam("socKementerian");
		idAgensi = getParam("socAgensi");			
		noFail = getParam("txtNoFail");
		tajuk = getParam("txtTajukFail");		
		String namaPemohon = getParam("txtNamaPemohon");
		tarikhPermohonan = getParam("txdTarikhTerima");
		String socDaerah = "";
		String socMukim = "";
		idDaerah = getParam("socDaerah");
		idMukim = getParam("socMukim");

		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
				
		fail.setIdNegeri(idNegeri);
		fail.setIdKementerian(idKementerian);
		fail.setNoFail(noFail);
		
		htpPermohonan.setIdAgensi(idAgensi);		
		permohonan.setTujuan(tajuk);		
		permohonan.setPfdFail(fail);
		htpPermohonan.setNoRujukanLain(namaPemohon);	
		permohonan.setTarikhTerima(tarikhPermohonan);			
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri", htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(), "", " disabled=\"disabled\""));
		context.put("socKementerian",HTML.SelectKementerian("socKementerian",htpPermohonan.getPermohonan().getPfdFail().getIdKementerian(), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(), "", " style=\"width:400\""));
		socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(idDaerah),null, "onChange=\"cmdChangeDaerahCarian()\"");
		socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim) , "");
	    this.context.put("socDaerah", socDaerah);
	    this.context.put("socMukim", socMukim);
		myLog.info("getValuesCarian:akhir");
		
	}
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}

	
}