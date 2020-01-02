package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
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
public class FrmPenswastaan2SenaraiFailView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	private final String PATH = "app/htp/penswastaan/fail/";
	private HtpBean iHtp = null;
	private HtpPermohonan htpPermohonan = null;
 	private IHTPFail iHTPFail = null;  
	private Permohonan permohonan = null;
	private PfdFail fail = null;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPenswastaan2SenaraiFailView.class);	
	FrmPenswastaan2SenaraiFailData logic = new FrmPenswastaan2SenaraiFailData();
	//14/03/2011
	String flagAdvSearch = "";
	
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
		myLog.info("action="+action+",submit="+submit+",mode="+mode+",actionPenswastaan="+actionPenswastaan+",hitButton="+hitButton);
		// VECTOR
		Vector beanMaklumatPermohonan = null;
		Vector list = null;		
		
		if (doPost.equals("true")) {
			postDB = true;
		}		
		if (selectedTab.equals("") || selectedTab.equals(null)) {
			selectedTab = "0";
		}				
		//GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
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
		String userId = (String)session.getAttribute("_ekptg_user_id");
		flagAdvSearch = getParam("flagAdvSearch");

		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), session);
         		mode = "view";
         		session.setAttribute("idFail", idFail);
        	}
    		if ("saveUpdate".equals(hitButton)){
         		logic.saveUpdate(idFail, getParam("txtNoFailKJP"), getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"), session);
        	}
    		if ("hantarPengesahan".equals(hitButton)){
    			logic.saveHantarPengesahan(idFail, session,"H"); //hantar
        	}
    		if ("sahkanPermohonan".equals(hitButton)){
    			logic.saveHantarPengesahan(idFail, session,"S"); //sahkan
        	}
    	}
		
		if ("papar".equals(actionPenswastaan)){		//GO TO PAPAR PENSWASTAAN			   
			vm = PATH+"frmDaftar.jsp";			
//			beanMaklumatPermohonan = new Vector();
//			logic.setMaklumatPermohonan(idFail);
//			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
//			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
//			Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
//			idPermohonan = String.valueOf(hashPermohonan.get("idPermohonan"));
			
			session.setAttribute("idFail", idFail);
			//htpPermohonan = getIHTP().findPermohonan(idFail, "","");
			// 18/04/2012
			htpPermohonan = getIHTPFail().findPermohonan(idFail, "","");
			idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());
			//mylog.info("idPermohonan:"+ hashPermohonan.get("idPermohonan"));
			context.put("permohonan", htpPermohonan);

			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"
					, htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(), "disabled", " class=\"disabled\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"
					, htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()
					, "disabled", " class=\"disabled\"  style=\"width:400\""));
			//this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi"
			//, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
			//, htpPermohonan.getIdAgensi(), "disabled", " class=\"disabled\"  style=\"width:400\""));
			this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi"
					, htpPermohonan.getIdAgensi(), "disabled", " class=\"disabled\"  style=\"width:400\""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("4", "socSuburusan" 
					, htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(), "disabled", " class=\"disabled\""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" 
					, htpPermohonan.getIdJenisTanah(), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" 
					, htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(), "disabled", " class=\"disabled\""));
			
			if ("view".equals(mode)){				
				this.context.put("readonly", "readonly");
	        	this.context.put("inputTextClass", "disabled");
        		this.context.remove("senaraiFailLain");
	        	list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
		       	this.context.put("senaraiFailLain",list);
				this.context.put("mode","New");
		       	this.context.put("idFailLama",idFail);

			} else if ("update".equals(mode)){				
				this.context.put("readonly", "");
	        	this.context.put("inputTextClass", "");
	        	
			}
			
		} else if ("daftarBaru".equals(actionPenswastaan)){
			
			//GO TO DAFTAR BARU PENSWASTAAN   
			//vm = "app/htp/frmPenswastaan2Daftar.jsp";
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
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"
					, Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi"
					, idKementerian ,Long.parseLong(idAgensi), "", " style=\"width:400\""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("4", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
			
		} else {	// SENARAI FAIL PENSWASTAAN
			vm = PATH+"index.jsp";
			list = new Vector();
			getValuesCarian();
			if(flagAdvSearch.equals("Y")){
				list = logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),null);
				//myLog.info(fail.getIdNegeri());
				if(!String.valueOf(fail.getIdNegeri()).equals("0")
						||!String.valueOf(fail.getIdKementerian()).equals("0")
						||!String.valueOf(htpPermohonan.getIdAgensi()).equals("0")
						||!fail.getNoFail().equals("")
						||!permohonan.getTujuan().equals("")
						||!htpPermohonan.getNoRujukanLain().equals("")
						||!permohonan.getTarikhTerima().equals("")
						){
					list = getIHTP().carianFail(fail.getNoFail(), permohonan.getTujuan()
						, permohonan.getTarikhTerima(), htpPermohonan.getNoRujukanLain(), String.valueOf(fail.getIdNegeri())
						, String.valueOf(fail.getIdKementerian()),String.valueOf(htpPermohonan.getIdAgensi())
						,String.valueOf("4"),"");					
				}
				
			}else{
				list = logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),userId);
			
			}		

//			this.context.put("SenaraiFail", list);
//			this.context.put("txtNoFail", getParam("txtNoFail"));
//			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			setupPage(session, action, list);

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
		myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan+",flagAdvSearch="+flagAdvSearch);
		return vm;
		
	}

	private void getValuesCarian()throws Exception {
//		myLog.info("getValuesCarian:mula");
		String idNegeri = getParam("socNegeri");		
		String idKementerian = getParam("socKementerian");
		String idAgensi = getParam("socAgensi");			
		String noFail = getParam("txtNoFail");
		String tajuk = getParam("txtTajukFail");		
		String namaPemohon = getParam("txtNamaPemohon");
		String tarikhPermohonan = getParam("txdTarikhTerima");

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
		context.put("socNegeri",HTML.SelectNegeri("socNegeri", fail.getIdNegeri(), "", ""));
		context.put("socKementerian",HTML.SelectKementerian("socKementerian",fail.getIdKementerian(), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
		context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", String.valueOf(fail.getIdKementerian()),htpPermohonan.getIdAgensi(), "", " style=\"width:400\""));
//		myLog.info("getValuesCarian:akhir");
		
	}
	
	private IHtp getIHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}
	
}