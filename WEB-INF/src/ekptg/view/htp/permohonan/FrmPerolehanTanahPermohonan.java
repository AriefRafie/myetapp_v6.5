package ekptg.view.htp.permohonan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.intergration.BorangKIntergrationBean;
import ekptg.intergration.IBorangKIntergration;
import ekptg.intergration.IIntegrasi;
import ekptg.intergration.IntegrasiGISBean;
import ekptg.intergration.entity.BorangK;
import ekptg.intergration.entity.HTPBorangK;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPajakanBayaranPajakanCukaiTanahData;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanTanahBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPBayaran;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.KeputusanUlasan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.permohonan.HTPBayaranPermohonanBean;
import ekptg.model.htp.permohonan.IPermohonanPerizapan;
import ekptg.model.htp.permohonan.PermohonanPerizapanBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.HTPSusulanPembangunanBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;

/**
 * @author Mohamad Rosli
 * Sub Modul Permohonan bagi kegunaan Pengguna Unit Perolehan BHTP 
 *
 */
public class FrmPerolehanTanahPermohonan extends AjaxBasedModule{	
	private BorangK borangK = null;
	private final String PATH="app/htp/permohonan/";
	private final String PATHVER = PATH+"v02/";
	private final String JENISTANAH = "1,2,3,6";
	private final String JENISTANAHPENGAMBILAN = "3";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private Hashtable<String, String> hashTerimaPohonInfo = null;	
	private IHtp iHTP = null;  
	private IHTPBayaran iBayaran = null;
	private IHTPSusulan iSusulan = null;
	private IHTPSusulan iSusulanPembangunan = null;
	private ekptg.model.htp.IHtp iHtp = null;
	private ekptg.model.htp.IHTPPermohonan iPermohonan = null;
	private ekptg.model.htp.permohonan.IPermohonanPerizapan ipr= null;
	private FrmRekodUtilData frmRekodUtilData = null;
	private HtpPermohonan htpPermohonan = null;
	private IBorangKIntergration kIntergration = null;
	private IHTPStatus iStatus = null;
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private String pageMode = "";
	private static final long serialVersionUID = 3234056422558052679L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.permohonan.FrmPerolehanTanahPermohonan.class);
	private String DISABILITY = " disabled class=\"disabled\" ";
	private String DISABILITYNUM = " disabled class=\"inputnumberdisabled\" ";
  	private String inputStyle = DISABILITY;
  	private String inputStyleNum = DISABILITYNUM;

	private String tarikhBukaFail = "";
	private String txtNoLot = "";
	private String noSyit = "";
	private String noPelan = "";
	private String socLot = "";
	private String socLuas = "";
	private String Luas = "";
	private String LuasLama = "";
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	
	FrmSenaraiFailTerimaPohonData fData = null;
	String socKementerian = "";
	String socAgensi = "";
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String socUrusan = "";
	String socSubUrusan = "";
	String socStatustanah = "";
	String socjenisFail = "";	
	String readability = "";
	String disability = "";
	String noLot = "";
	
 	String txtTajuk = "";
 	String txtTajukCarian = "";
 	String id_kementerian = "";
 	String id_agensi = "";
 	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
 	String idurusan = "";
 	String idsuburusan = "";
	String nofail = "";
	String idpermohonan ="";
	String idhakmilikurusan ="";
	String idfail = "";
	String userId = "";
	String tabmode = "";
	String readOnly = "";
	String disabled = "";
	String style = "";
	String idNotis = "";
	String Lokasi = "";
	//ppt
	Vector<HTPBorangK> listBorangK =  null;
	Vector<Hashtable<String, String>> maklumatAsasTanahInfo = null;
	Vector<Hashtable<String, String>> senaraiTerimaPohon = null;
	//18/08/2010
	String flagAdvSearch = "";
	String button_ = ""; 
	FrmPajakanBayaranPajakanCukaiTanahData pajakanData = new FrmPajakanBayaranPajakanCukaiTanahData();
	//Hashtable<String, String> terimaPohonInfo = null;
	Hashtable<String, String> hashData = null;
	HTPPermohonanTanahBean tanahBean =null;
	String labelDaerah = "Daerah";
	// INTEGRASI 2017/06
	private IntegrasiGISBean integrasiGIS = null;

	//END INIT	
	@Override
	public String doTemplate2() throws Exception {				
		//Syaz 05112014
		context.put("utils",new Utils());	
		fData = FrmSenaraiFailTerimaPohonData.getInstance();
		HttpSession session = this.request.getSession();
		String portal_role = String.valueOf(session.getAttribute("myrole"));
		context.put("portal_role_",portal_role);
		userId = String.valueOf(session.getAttribute("_ekptg_user_id"));
		if(userId == null){
			getIHTP().getErrorHTML("[HTP PERMOHONAN] SILA LOGIN SEMULA");
		}
		//PARAMETERS
		//Skrin Carian
		
		String action = getParam("action");
		String button = getParam("button");
		button_ = button;
		String doChange = getParam("doChange");
		String idNotis = getParam("idNotis");
		String mode = getParam("mode");
		String pagemode = getParam("pagemode");
		String selectedTab = getParam("tabId");		
		String submit = getParam("command");
		tabmode = getParam("tabmode");
		idpermohonan = getParam("idpermohonan");
		idfail = getParam("idfail");
		idhakmilikurusan = getParam("idhakmilikurusan");
		nofail = getParam("txtNoFail").trim();
	 	txtTajuk = getParam("txtTajuk");
	 	//18/08/2010
		txtTajukCarian = getParam("txtTajukFail");
	 	id_kementerian = getParam("socKementerian");
	 	id_agensi = getParam("socAgensi");
	 	idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	idurusan = getParam("socUrusan");
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");
	 	
		if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    		tabmode = "0";
    	}
		String selectedTab2 = "";
		String selectedTab3 = "";
		
		this.context.put("readOnly", "");
		this.context.put("disabled", "");			
		//DEFAULT template
		String template_name = PATHVER+"fail/frmTerimaPohonSenaraiFail2.jsp";
		myLog.info("SUBMIT=" +submit+",MODE=" + mode+",BUTTON =" + button+",action="+action);
		myLog.info("selectedTab= " +selectedTab+",tabmode= " + tabmode+",pagemode=" +pagemode);
		this.context.put("showSahkanButton",false);
		this.context.put("sahkanresult","");
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		tarikhBukaFail = getParam("txdtarikhdaftarfail");
		String tarikhSurat = "";
		String tarikhHantar = "";
		String tarikhKeputusan = "";
		boolean notifikasiSimpan = false;
		
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		context.remove("senaraihakmilikrizab");
		
		try{
//			if(!idnegeri.equals("") && idnegeri.equals("3"))
//				labelDaerah = "Jajahan";
//			else if(!idnegeri.equals("") && idnegeri.equals("13"))
//				labelDaerah = "Bahagian";			
//			//myLog.debug("labelDaerah=" +labelDaerah);
//			this.context.put("labelDaerah",labelDaerah)	    		
			tanahBean = new HTPPermohonanTanahBean();
			tanahBean.setLabelDaerah(context, idnegeri);
			String gisValue = "XGIS";
			if(portal_role.equals("(HTP)PelukisPelan"))
				gisValue = "GIS";
			this.context.put("gisPage",gisValue);    		

			if (submit.equals("SahkanPermohonan")) {
				template_name = PATH+"FrmTerimaPohon.jsp";
				if("doSahkan".equals(button)){
					transferRecord(idpermohonan);
					this.context.put("sahkanresult","Permohonan telah disahkan");
				} else {
					this.context.put("sahkanresult","");
				}
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
	   			//setMaklumatPermohonan(terimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
				return template_name;

			}else if(submit.equals("maklumatpermohonan")) {
				template_name = PATHVER+"fail/frmTerimaPohonEdit.jsp";
	    		String hittButton = getParam("hittButton");
				myLog.info("submit=maklumatpermohonan, hittButton="+hittButton);
				setPaging(false,true,true,false,false);
				hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
				tanahBean.setLabelDaerah(context, String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri")));
	   			//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	   			//dapatkan flag utk button sahkan
	   			if (fData.showSahkanButton(idpermohonan)) {
	   				this.context.put("showSahkanButton",true);
	   			}
	   			
	   			if (hittButton.equals("kemaskini")){
	   				myLog.info("kemas kini");
	   				idnegeri = String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri"));
	   				inputStyle = " class=\"\" ";
    				pageMode = "kemaskini";
    				
    				System.out.println("d-------------"+hashTerimaPohonInfo.get("lblIdDaerah"));
       	    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"))),disability,"");
    				socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)hashTerimaPohonInfo.get("lblidAgensi")),disability+" style=\"width:400\"");
    				socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)hashTerimaPohonInfo.get("lblTanahKeselamatan")),disability);
	    		
	   			} else if (hittButton.equals("kemaskinisimpan")){
	   				System.out.println("masuk siniiiiii kemaskinisimpan");
					template_name = PATHVER+"fail/frmMaklumatFail.jsp";
	    			getValues();
	    			idnegeri = String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri"));
	    			
					idpermohonan = (String)hashTerimaPohonInfo.get("lblIdPermohonan");
					htpPermohonan.setIdDaerah(String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah")));	//lblIdDaerah
					htpPermohonan.setIdAgensi(String.valueOf(hashTerimaPohonInfo.get("lblidAgensi")));	//lblidAgensi
					htpPermohonan.setIdJenisTanah(String.valueOf(hashTerimaPohonInfo.get("lblidKodJTanah")));	//lblidKodJTanah
					htpPermohonan.setIdHtpPermohonan(String.valueOf(hashTerimaPohonInfo.get("idHtpPermohonan")));	// idHtpPermohonan
					//fail.setIdTarafKeselamatan(String.valueOf(TerimaPohonInfo.get("lblTanahKeselamatan")));	//lblTanahKeselamatan
					fail.setTarikhDaftarFail(String.valueOf(hashTerimaPohonInfo.get("tarikhDaftarFail")));//tarikhDaftarFail
					permohonan.setPfdFail(fail);
					permohonan.setIdMasuk(Long.parseLong(userId));
					//TBLPERMOHONAN (TARIKH_SURAT,TARIKH_TERIMA,TUJUAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLHTPPERMOHONAN (NO_RUJUKAN_LAIN,NO_RUJUKAN_KJP,NO_RUJUKAN_UPT,NO_RUJUKAN_PTG,NO_RUJUKAN_PTD
					//	,ID_AGENSI,ID_DAERAH,ID_JENISTANAH,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLPFDFAIL (TAJUK_FAIL,TARIKH_DAFTAR_FAIL,ID_TARAFKESELAMATAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan, idpermohonan, "");
					hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);   
		   			//setMaklumatPermohonan(hashTerimaPohonInfo);
		   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
					readOnly = "readonly";
					context.put("disabled", "disabled");	
					
					System.out.println("daerag dlm simpkemas"+hashTerimaPohonInfo.get("lblIdDaerah"));
					socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"))),DISABILITY,"");
    				socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)hashTerimaPohonInfo.get("lblidAgensi")),DISABILITY+" style=\"width:400\"");
    				socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)hashTerimaPohonInfo.get("lblTanahKeselamatan")),DISABILITY);
	
	   			}else if(hittButton.equals("view")) {
					template_name = PATHVER+"fail/frmMaklumatFail.jsp";
					myLog.info("hittButton=view");
					context.put("disabled", "disabled");	
					readOnly = "readonly";
	   			
	   			}
    	    	context.put("socDaerah", socDaerah);
    	    	context.put("socAgensi", socAgensi);
    	    	context.put("socjenisFail", socjenisFail);
				context.put("pageMode", pageMode);	
				context.put("readOnly", readOnly);
				//context.put("disability", disability);
	    		context.put("idNegeriNotis",String.valueOf(hashTerimaPohonInfo.get("lblNegeri")));	
 
			}else if(submit.equals("viewMaklumatPermohonan")) {
				myLog.info("viewMaklumatPermohonan");
				template_name = PATHVER+"fail/frmMaklumatFail.jsp";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,true,true,false,false);
				hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
				myLog.info("hashTerimaPohonInfo="+hashTerimaPohonInfo);
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
				tanahBean.setLabelDaerah(context, String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri")));
	   			//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	   			//dapatkan flag utk button sahkan
	   			if (fData.showSahkanButton(idpermohonan)) {
	   				this.context.put("showSahkanButton",true);
	   			}
				Vector<Hashtable<String, String>> senaraiHakmilikRizab = 
						frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idpermohonan);
				this.context.put("senaraihakmilikrizab",senaraiHakmilikRizab);
	    		context.put("idNegeriNotis",String.valueOf(hashTerimaPohonInfo.get("lblNegeri")));	
	    		
	    		this.context.put("txdtarikhsuratfail",tarikhSurat);
				this.context.put("txdtarikhantarfail",tarikhHantar);
				this.context.put("txdtarikhkeputusanfail",tarikhKeputusan);
				this.context.put("txtTajukCarian",txtTajukCarian);
				selectedTab2 = (String)this.context.get("selectedTab2");
				if (selectedTab2 == null || "".equals(selectedTab2) ) {
		    		selectedTab2="0";
		    	}
				selectedTab3 = (String)this.context.get("selectedTab3");
				if (selectedTab3 == null || "".equals(selectedTab3) ) {
		    		selectedTab3="0";
		    	}
				this.context.put("selectedTab", selectedTab);
				this.context.put("selectedTab2", selectedTab2);
				this.context.put("selectedTab3", selectedTab3);
		    	//myLog.info("selectedtab:selectedTab="+selectedTab+",selectedTab2="+selectedTab2+",selectedTab3="+selectedTab3);
				
				this.context.put("tabmode", tabmode);
				this.context.put("button", button);
				this.context.put("mode", mode);
				this.context.put("submit", submit);
		    	this.context.put("style",style);
		    	this.context.put("idpermohonan",idpermohonan);
		    	this.context.put("idPermohonan",idpermohonan);//Utk frmTerimaPohon.jsp
		    	this.context.put("idhakmilikurusan",idhakmilikurusan);
		    	this.context.put("idfail",idfail);
		    	//myLog.info("selectedtab X:button="+button+",tabmode="+tabmode+",mode="+mode);
			    // 18/08/2010
		    	//myLog.info("flagAdvSearch="+flagAdvSearch);
		    	this.context.put("flagAdvSearch",flagAdvSearch);
		    	this.context.put("idnegeri", idnegeri); 
		    	
			//CARIAN
			}else if (submit.equals("terimapohoncarian")) {
				myLog.info("terimapohoncarian:"+submit);				
				String hittButton = getParam("hittbutton");
	    		if (hittButton.equals("simpanGIS")) {
	    			int gisStatus = getParamAsInteger("statusgis");
	    			getIntegrasi().simpan(getParam("nofailgis"),gisStatus);
	    			notifikasiSimpan = true;
	    			
	    		}
	    		tarikhSurat = getParam("txdtarikhsuratfail");
	    		tarikhHantar = getParam("txdtarikhantarfail");
	    		tarikhKeputusan = getParam("txdtarikhkeputusanfail");
//				myLog.info("tarikhSurat="+tarikhSurat);
//				myLog.info("tarikhHantar="+tarikhHantar);
//				myLog.info("tarikhKeputusan"+tarikhKeputusan);
		    	//flagAdvSearch = "Y";
	    		senaraiTerimaPohon = getPR().TerimaPohongetList(null,nofail,txtTajukCarian,
									id_kementerian,id_agensi
									,idnegeri,iddaerah,idmukim
									,idurusan
									,tarikhSurat,tarikhBukaFail,tarikhHantar,tarikhKeputusan);

   				this.context.put("tarikhBukaFail",tarikhBukaFail);		    	
				doListing(session,action,mode,senaraiTerimaPohon);
			
			}else if (submit.equals("pohonfailbaru")) {
				template_name = PATHVER+"fail/frmTerimaPohonEdit.jsp";
		    	String hittButton = getParam("hittButton");
				myLog.info("submit=pohonfailbaru, hittButton="+hittButton);
				if (hittButton.equals("Simpan")) {
	   				template_name = PATHVER+"fail/frmMaklumatFail.jsp";
					setPaging(false,true,true,false,false);
		    		
					String idFailBaru = doSimpanMaklumatPermohonan();
		    		AuditTrail.logActivity("1", getParam("socSeksyen"), this, session, "INS", "FAIL PERMOHONAN ["+fData.strNoFail+"] DITAMBAH ");
		    		//integrasi GIS
	    			getIntegrasi().simpan(fData.strNoFail, (idurusan.equals("1")?2:3));

		    		hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFailBaru);
		    		idfail = idFailBaru;
					idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
		   			//setMaklumatPermohonan(hashTerimaPohonInfo);		
		   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
					context.put("disabled", "disabled");	
					readOnly = "readonly";
		  	    	context.put("socDaerah", socDaerah);
	    	    	context.put("socAgensi", socAgensi);
	    	    	context.put("socjenisFail", socjenisFail);
					context.put("pageMode", pageMode);	
					context.put("readOnly", readOnly);
					//context.put("disability", disability);
				
   				}else{				
					this.context.put("readOnly", "");
					this.context.put("disabled", "");	
			    	this.context.put("idpermohonan","");
			    	this.context.put("idfail","");
					viewPohonFailBaru(mode);
				
   				}
	    		context.put("idNegeriNotis",idnegeri);	
	
			}else if(submit.equals("maklumatanah")){
		    	template_name = PATHVER+"maklumatanah/frmTerimaPohonMaklumaTABB.jsp";	    	
				myLog.info("maklumatanah:mode="+mode+",button="+button);
				//MAKLUMAT ASAS TANAH//HEADER
				setPaging(false,false,true,false,false);	
		    	
	    		String pageMode = "";
	    		hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
	    		this.context.put("pageMode", pageMode);	
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
				tanahBean.setLabelDaerah(context, String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri")));
				//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	   			String idJenisTanah = hashTerimaPohonInfo.get("lblidKodJTanah").toString();
    			context.put("id_jenistanah", idJenisTanah);
	    		context.put("idNegeriNotis",String.valueOf(hashTerimaPohonInfo.get("lblNegeri")));	
	    		//myLog.info(hashTerimaPohonInfo.get("lblNegeri"));
	    		if (mode.equals("MakAsasTanah")) {
	 	    		//IF DO CHANGES DETECTED
	    			if (doChange.indexOf("doChange") != -1) {
	    				myLog.info("doChange:TAB MODE="+tabmode);
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");

	    			}
		    		socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri),DISABILITY);
		    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(iddaerah),style, "onChange=\"doChanges2('"+tabmode+"')\"");
		    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2", Utils.parseLong(idmukim) ,style);
		    		socLot = HTML.SelectLot("socLot", Utils.parseLong(socLot),style);
		    		
		    		if (button.equals("KemaskiniMaklumatInfo")) {
		    			myLog.info("KemaskiniMaklumatInfo");
	    				Hashtable<?, ?> t = getPR().getMaklumatAsasTanahKemaskini(idhakmilikurusan);
			    		idhakmilikurusan =String.valueOf(t.get("idhakmilikurusan"));
			    		idnegeri = String.valueOf(t.get("idnegeri"));
			    		iddaerah =String.valueOf(t.get("iddaerah"));
			    		idmukim =String.valueOf(t.get("idmukim"));
			    		txtNoLot =String.valueOf(t.get("nolot"));
			    		noSyit =String.valueOf(t.get("nosyit"));
			    		noPelan =String.valueOf(t.get("nopelan"));
			    		socLot =String.valueOf(t.get("idlot"));
			    		socLuas =String.valueOf(t.get("idluas"));
			    		Luas =String.valueOf(t.get("luasH"));
			    		LuasLama =String.valueOf(t.get("luas"));
		    			//myLog.info("LuasLama:"+LuasLama+",Luas:"+Luas+"");			    		
//						String luas = "0";
//						String luas1 = "0";
//						String luas2 = "0";
//						if(socLuas.equals("1") || socLuas.equals("2") || socLuas.equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
//							if(socLuas.equals("1")){
//								if(LuasLama.contains("KM"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else
//									luas = LuasLama;
//								
//							}else if(socLuas.equals("2")){
//								if(LuasLama.contains("H"))
//									luas = LuasLama.substring(0, (LuasLama.length()-1));
//								else
//									luas = LuasLama;
//								
//							}else if(socLuas.equals("3")){
//								if(LuasLama.contains("MP"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else{
//									if(LuasLama.contains("M"))
//										luas = LuasLama.substring(0, (LuasLama.length()-1));
//									else
//										luas = LuasLama;
//								}
//								
//							}else if(socLuas.equals("5")){
//								if(LuasLama.contains("KP"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else{
//									if(LuasLama.contains("K"))
//										luas = LuasLama.substring(0, (LuasLama.length()-1));
//									else
//										luas = LuasLama;
//								}
//								
//							}else if(socLuas.equals("6")){
//								if(LuasLama.contains("P"))
//									luas = LuasLama.substring(0, (LuasLama.length()-1));
//								else
//									luas = LuasLama;
//								
//							}
//				
//						}else if(socLuas.equals("4")){
//							if(LuasLama.contains("E,") && LuasLama.contains("R,")){
//								luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//								luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("R,"));
//								luas2 = LuasLama.substring(LuasLama.indexOf("R,")+2,(LuasLama.length()-1));
//							
//							}
//						}else if(socLuas.equals("7")){
//								//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//								if(LuasLama.contains("E,") && LuasLama.contains("D")){
//									luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//									luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("D"));
//								}
//								
//						}else if(socLuas.equals("8")){
//							//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//							if(LuasLama.contains("R,") && LuasLama.contains("J,")){
//								luas = LuasLama.substring(0,LuasLama.indexOf("R,"));
//								luas1 = LuasLama.substring(LuasLama.indexOf("R,")+2,LuasLama.indexOf("J"));
//								luas2 = LuasLama.substring(LuasLama.indexOf("J,")+2,(LuasLama.length()-1));
//							}
//							
//						}else{ //7||9 (TIADA SAMPLE DATA)
//							luas = LuasLama;
//							
//						}
//						this.context.put("txtLuasLama1", luas1.trim());	
//						this.context.put("txtLuasLama2", luas2.trim());	
//						this.context.put("txtLuasLama", luas);
			    		tanahBean.maklumatLuas(context, socLuas, getParam("socLuas"), LuasLama);
			    		Lokasi = String.valueOf(t.get("lokasi"));
			    		
			    		context.put("noSyit", noSyit);
			    		context.put("noPelan", noPelan);
			    		context.put("txtNoLot", txtNoLot);
			    		context.put("Lokasi", Lokasi);
			    		this.context.put("readonly", "readonly=\"readonly\"");
						pageMode = "view";
						
	    			// Tambah Maklumat Tanah
			    	}else {
		   		      	disability = " class=\"\" ";
		   		      	/* 2017/02/11
		    			maklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);		    			
		    			//untuk ppt
		    			if(maklumatAsasTanahInfo.size()>0 ){
		    				//tabmode = "3_1";
		    			}		    			
			    		this.context.put("bilBorangK", maklumatAsasTanahInfo.size());
			    		this.context.put("MAT", maklumatAsasTanahInfo);
			    		*/
			    		//tanahBean = new HTPPermohonanTanahBean();
			    		tanahBean.maklumatAsas(context, maklumatAsasTanahInfo, idpermohonan);

	    			}		    		
		    		context.put("socNegeri2", socNegeri);
		    		context.put("socDaerah2", socDaerah);
		    		context.put("socMukim2",socMukim );
		    		context.put("socLot",socLot );
		    		context.put("socLuas", socLuas);
		    		//myLog.info("pageMode x : "+pageMode);
					context.put("pageMode", pageMode);	
					context.put("readOnly", readOnly);
					context.put("disability", disability);
	    		
	    		} else if (mode.equals("maklumatanahterperinci")){
    				myLog.info("DetailLot");
    				selectedTab2 = getParam("tabId2");
    				this.context.put("selectedTab2", selectedTab2);
    				if (selectedTab2.equals("1")) {
    					initMaklumatCharting("");				
    				} else {
    					initMaklumatLokasi("");   				
    				}
    				
	    		}else{
    				myLog.info("mode = else");
    				/* 2017/02/11
		 			maklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);		    			
	    			//untuk ppt
	    			if(maklumatAsasTanahInfo.size()>0 ){
	    				//tabmode = "3_1";
	    			}	    			
		    		this.context.put("bilBorangK", maklumatAsasTanahInfo.size());
		    		this.context.put("MAT", maklumatAsasTanahInfo);
		    		*/
		    		//tanahBean = new HTPPermohonanTanahBean();
		    		tanahBean.maklumatAsas(context, maklumatAsasTanahInfo, idpermohonan);

	    		}
				
		    }else if(submit.equals("kemaskinipermohonan")){
		    	// Kemaskini pada 2012/12/05
		    	template_name = PATHVER+"maklumatanah/frmMaklumaTanahTABB.jsp";
		    	this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,false,true,false,false);	
		    	//HEADER
	    		String hittButton = getParam("hittButton");
	    		String pageMode = "";
	    		//SIMPAN
		    	myLog.info("kemaskinipermohonan:hittButton="+hittButton);
	    		if (hittButton.equals("Simpan")) {
	    			template_name = PATHVER+"fail/frmTerimaPohonEdit.jsp";
	    			String idFailBaru = doSimpanMaklumatPermohonan();
	    			AuditTrail.logActivity("1", getParam("socSeksyen"), this, session, "INS", "FAIL PERMOHONAN ["+fData.strNoFail+"] DITAMBAH ");
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFailBaru);
	    			idfail = idFailBaru;
					setPaging(false,true,true,false,false);
   			
	    		} else if (hittButton.equals("kemaskinisimpan")){
	    			template_name = PATHVER+"fail/frmTerimaPohonEdit.jsp";
	    			getValues();
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
					idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
					htpPermohonan.setIdDaerah(String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah")));	//lblIdDaerah
					htpPermohonan.setIdAgensi(String.valueOf(hashTerimaPohonInfo.get("lblidAgensi")));	//lblidAgensi
					htpPermohonan.setIdJenisTanah(String.valueOf(hashTerimaPohonInfo.get("lblidKodJTanah")));	//lblidKodJTanah
					htpPermohonan.setIdHtpPermohonan(String.valueOf(hashTerimaPohonInfo.get("idHtpPermohonan")));	// idHtpPermohonan
					fail.setIdTarafKeselamatan(String.valueOf(hashTerimaPohonInfo.get("lblTanahKeselamatan")));	//lblTanahKeselamatan
					fail.setTarikhDaftarFail(String.valueOf(hashTerimaPohonInfo.get("tarikhDaftarFail")));//tarikhDaftarFail
					permohonan.setPfdFail(fail);
					permohonan.setIdMasuk(Long.parseLong(userId));
					//TBLPERMOHONAN (TARIKH_SURAT,TARIKH_TERIMA,TUJUAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLHTPPERMOHONAN (NO_RUJUKAN_LAIN,NO_RUJUKAN_KJP,NO_RUJUKAN_UPT,NO_RUJUKAN_PTG,NO_RUJUKAN_PTD
					//	,ID_AGENSI,ID_DAERAH,ID_JENISTANAH,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLPFDFAIL (TAJUK_FAIL,TARIKH_DAFTAR_FAIL,ID_TARAFKESELAMATAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan, idpermohonan, "");
					hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
					setPaging(false,true,true,false,false);
      				
    			} else if (hittButton.equals("kemaskini")){
	    			template_name = PATHVER+"fail/frmTerimaPohonEdit.jsp";
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
    				readOnly = "";
    				disabled = "";
    				style = "";   
    				pageMode = "kemaskini";
    				this.context.put("readOnly", readOnly);
    				this.context.put("disabled", disabled);	
   				
	    		} else {
	    			myLog.info("else hittButton:idfail="+idfail);
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
	    			
	    		}
				this.context.put("pageMode", pageMode);	
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
	   			iddaerah = String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"));
				tanahBean.setLabelDaerah(context, String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri")));
				//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);	   			 			
	    		//ASSIGN VALUES
				//DISABLE OPTION UTK PILIH NEGERI
	    		socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri),"disabled class=disabled");
	    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(iddaerah),style, "onChange=\"doChanges2('"+tabmode+"')\"");
	    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2", Utils.parseLong(idmukim) ,style);
	    		//Maklumat Permohonan
	    		context.put("socDaerah", socDaerah);
	    		
	    		context.put("socNegeri2", socNegeri);
	    		context.put("socDaerah2", socDaerah);
	    		context.put("socMukim2",socMukim );
				String id_jenistanah = hashTerimaPohonInfo.get("lblidKodJTanah").toString();
				//myLog.info(message)
				
    			//myLog.info("607 : hashTerimaPohonInfo="+hashTerimaPohonInfo+",mode = "+mode);
		    	myLog.info("kemaskinipermohonan:mode="+mode);
    			idnegeri= String.valueOf(hashTerimaPohonInfo.get("lblNegeri"));
		    	//MAKLUMAT ASAS TANAH
	    		if (mode.equals("MakAsasTanah")) {
	    			//myLog.info("MakAsasTanah : doChange = "+doChange);
	    			txtNoLot = getParam("txtNoLot");
	    			noSyit = getParam("noSyit");
	    			noPelan = getParam("noPelan");
		    		socLot = getParam("socLot");
		    		socLuas = getParam("socLuas");
		    		LuasLama = getParam("txtLuasGabung");
		    		Luas = getParam("Luas");
		    		Lokasi = getParam("Lokasi");
		    		idmukim = getParam("socMukim2");
		    		noLot = getParam("socLot");
	    			//IF DO CHANGES DETECTED
	    			if (doChange.indexOf("doChange") != -1) {	
	    				myLog.info("tabmode:2: "+tabmode);
	    				if(tabmode.equalsIgnoreCase("2")){
	    					//untuk kemaskini shj
	    					myLog.info("tabmode:2");
	    					pageMode = "kemaskiniprasimpan";
	    				}
	    				//OVERRIDE VALUES
	    				myLog.info("doChange:TAB MODE="+tabmode);
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    				//LuasLama = "";
	    			}
	    			myLog.info("636 : button = "+button);
	    			if(button.equals("SimpanMaklumatAsasTanah")){
		    			myLog.debug("SimpanMaklumatAsasTanah");
	    				readOnly = "disabled";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				//doSimpanMaklumatAsasTanah();
	    				idhakmilikurusan = simpanMaklumatAsasTanah();
	    				getMaklumatTanah();
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	 	    			context.remove("TerimaPohonInfo");
	 	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
			   			//setMaklumatPermohonan(hashTerimaPohonInfo);
			   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
			   			pageMode = "view";
			   			
		    		} else if (button.equals("KemaskiniMaklumatInfo")) {
		    			pageMode = "view";
		    			readOnly = "disabled";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				
		    			myLog.info("KemaskiniMaklumatInfo");
	    				Hashtable<?,?> t = getPR().getMaklumatAsasTanahKemaskini(idhakmilikurusan);
			    		idhakmilikurusan =String.valueOf(t.get("idhakmilikurusan"));
			    		idnegeri =String.valueOf(t.get("idnegeri"));
			    		iddaerah =String.valueOf(t.get("iddaerah"));
			    		idmukim =String.valueOf(t.get("idmukim"));
			    		txtNoLot =String.valueOf(t.get("nolot"));
			    		noSyit =String.valueOf(t.get("nosyit"));
			    		noPelan =String.valueOf(t.get("nopelan"));
			    		socLot =String.valueOf(t.get("idlot"));
			    		socLuas =String.valueOf(t.get("idluas"));
			    		Luas =String.valueOf(t.get("luasH"));
			    		LuasLama =String.valueOf(t.get("luas"));
		    			//myLog.info("LuasLama:"+LuasLama+",Luas:"+Luas+"");
			    		
//						String luas = "0";
//						String luas1 = "0";
//						String luas2 = "0";
//						if(socLuas.equals("1") || socLuas.equals("2") || socLuas.equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
//							if(socLuas.equals("1")){
//								if(LuasLama.contains("KM"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else
//									luas = LuasLama;
//								
//							}else if(socLuas.equals("2")){
//								if(LuasLama.contains("H"))
//									luas = LuasLama.substring(0, (LuasLama.length()-1));
//								else
//									luas = LuasLama;
//								
//							}else if(socLuas.equals("3")){
//								if(LuasLama.contains("MP"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else{
//									if(LuasLama.contains("M"))
//										luas = LuasLama.substring(0, (LuasLama.length()-1));
//									else
//										luas = LuasLama;
//								}
//								
//							}else if(socLuas.equals("5")){
//								if(LuasLama.contains("KP"))
//									luas = LuasLama.substring(0, (LuasLama.length()-2));
//								else{
//									if(LuasLama.contains("K"))
//										luas = LuasLama.substring(0, (LuasLama.length()-1));
//									else
//										luas = LuasLama;
//								}
//								
//							}else if(socLuas.equals("6")){
//								if(LuasLama.contains("P"))
//									luas = LuasLama.substring(0, (LuasLama.length()-1));
//								else
//									luas = LuasLama;
//								
//							}
//				
//						}else if(socLuas.equals("4")){
//							if(LuasLama.contains("E,") && LuasLama.contains("R,")){
//								luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//								luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("R,"));
//								luas2 = LuasLama.substring(LuasLama.indexOf("R,")+2,(LuasLama.length()-1));
//							
//							}
//						}else if(socLuas.equals("7")){
//								//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//								if(LuasLama.contains("E,") && LuasLama.contains("D")){
//									luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//									luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("D"));
//								}
//								
//						}else if(socLuas.equals("8")){
//							//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//							if(LuasLama.contains("R,") && LuasLama.contains("J,")){
//								luas = LuasLama.substring(0,LuasLama.indexOf("R,"));
//								luas1 = LuasLama.substring(LuasLama.indexOf("R,")+2,LuasLama.indexOf("J"));
//								luas2 = LuasLama.substring(LuasLama.indexOf("J,")+2,(LuasLama.length()-1));
//							}
//							
//						}else{ //7||9 (TIADA SAMPLE DATA)
//							luas = LuasLama;							
//						}
//						this.context.put("txtLuasLama1", luas1.trim());	
//						this.context.put("txtLuasLama2", luas2.trim());	
//						this.context.put("txtLuasLama", luas);					
			    		tanahBean.maklumatLuas(context, socLuas, getParam("socLuas"), LuasLama);
			    		Lokasi =String.valueOf(t.get("lokasi"));
	    				
	    			} else if (button.equals("doViewForKemaskini")){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    				getMaklumatTanah();	    				
	    				pageMode = "kemaskiniprasimpan";
	    			
	    			} else if (button.equals("doKemaskiniMaklumatAsasTanah")){
	    				myLog.info("doKemaskiniMaklumatAsasTanah");
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				idhakmilikurusan = kemaskiniMaklumatAsasTanah();
	    				getMaklumatTanah();
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	 	    			context.remove("TerimaPohonInfo");
	 	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
			   			//setMaklumatPermohonan(hashTerimaPohonInfo);
			   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	    			
	    			} else if (button.equals("deletemaklumatanah")){	
	    				this.context.remove("MAT");
	    				//myLog.info("deletemaklumatanah:idhakmilikurusan:"+idhakmilikurusan+"");
	    				getHTP().hapusHakmilik(idhakmilikurusan);
		    			maklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
			    		this.context.put("MAT", maklumatAsasTanahInfo);

			    		//SET SELECTED
	    			} else if (button.equals("DetailLot")){
	    				//myLog.debug("DetailLot");
	    				selectedTab2 = getParam("tabId2");
	    				this.context.put("selectedTab2", selectedTab2);
	    				if ("1".equals(selectedTab2)) {
	    					initMaklumatCharting("");	
	    				} else {
	    					initMaklumatLokasi("");	
	    				}
	    			
	    			} else if (button.equals("SimpanDetailLot")) {
	    				myLog.debug("SimpanDetailLot");
	    				SimpanLokasiTanah();
	    				initMaklumatLokasi("readonly");
	       			
	    			}else if (button.equals("doKemaskiniDetailLot")){
	    				myLog.debug("doKemaskiniDetailLot");
	    				doKemaskiniLokasiTanah();
	    				initMaklumatLokasi("readonly");
	    			
	    			} else if (button.equals("doViewForKemaskiniDetailLot")){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else if (button.equals("SimpanCharting")){
	    				myLog.debug("SimpanCharting");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				ChartingSimpanKemaskini("insert");
	    				initMaklumatCharting("readonly");
	    			
	    			} else if (button.equals("doKemaskiniCharting")){
	    				myLog.debug("doKemaskiniCharting");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				ChartingSimpanKemaskini("update");
	    				initMaklumatCharting("readonly");
	    			
	    			}else if (button.equals("doViewForKemaskiniCharting")){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else {
	    				//SENARAI 
		    			myLog.info(" ** SENARAI:else button::tabmode= **"+tabmode);
		    			/* 2017/02/11
		    			maklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);		    			
		    			//untuk ppt
		    			context.put("id_jenistanah", id_jenistanah);
		    			//if(maklumatAsasTanahInfo.size()>0 ){
		    				//tabmode = "3_1";
		    			//}
		    			
			    		this.context.put("bilBorangK", maklumatAsasTanahInfo.size());
			    		this.context.put("MAT", maklumatAsasTanahInfo); */
			    		//tanahBean = new HTPPermohonanTanahBean();
			    		tanahBean.maklumatAsas(context, maklumatAsasTanahInfo, idpermohonan);

	 	    			this.context.put("TerimaPohonInfo", hashTerimaPohonInfo);
	 	    			LuasLama = getParam("txtLuasGabung").equals("")?"":getParam("txtLuas1");
	   			
	    			}
		    		//ASSIGN VALUES
	    			//DISABLE OPTION UTK PILIH NEGERI
	    			System.out.println("iddaerah -------------"+iddaerah);
		    		socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri),"disabled class=disabled");
		    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(iddaerah),style, "onChange=\"doChanges2('"+tabmode+"')\"");
		    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2", Utils.parseLong(idmukim) ,style);
		    		socLot = HTML.SelectLot("socLot", Utils.parseLong(socLot),style);
		    		
		    		context.put("socNegeri2", socNegeri);
		    		context.put("socDaerah2", socDaerah);
		    		context.put("socMukim2",socMukim );
		    		context.put("socLot",socLot );
		    		context.put("socLuas", socLuas);
		    		context.put("noSyit", noSyit);
		    		context.put("noPelan", noPelan);
		    		context.put("txtNoLot", txtNoLot);
		    		context.put("Lokasi", Lokasi);
		    		context.put("txtLuas1", LuasLama);
		    		context.put("Luas", Luas);
					context.put("pageMode", pageMode);
					context.put("style", style);
		    		//myLog.info("pageMode : "+pageMode+" style : "+style);
					
		    	} else if (mode.equals("BorangK")) {
		    		//list infoborangk
		    		//fData.setListBorangK();
		     		//listBorangK = fData.getListBorangK();
		    		listBorangK = getKIntergration().getHTPBorangKList(idpermohonan);
		     		context.put("listBorangK", listBorangK);
		     		context.put("saiz_borangk", listBorangK.size());   	
		     		
		    	} else if (mode.equals("Pembayaran")) {	
		    		selectedTab = "1"; //TAB KEPUTUSAN/PEMBAYARAN
		    		if(id_jenistanah.equals("3")){
			    		selectedTab = "2"; //TAB KEPUTUSAN/PEMBAYARAN (TANAH PENGAMBILAN)		    		
		    		}
	    			context.put("id_jenistanah", id_jenistanah);
		    		//myLog.debug("Pembayaran");
		    		this.context.put("visible","1");
					selectedTab3 = getParam("tabId3");
					myLog.debug("Keputusan/Pembayaran:selectedTab3="+selectedTab3);				
					if (selectedTab3.equals("1")) { //TAB KEPUTUSAN 
						myLog.debug("TAB HANTAR PTG/PTD:selectedTab3="+selectedTab3);
						String suratExt = "";
						String kodUrusan = String.valueOf(hashTerimaPohonInfo.get("kodUrusan"));
						String kodTanah = String.valueOf(hashTerimaPohonInfo.get("lblKodJTanah"));
						//myLog.debug("TAB HANTAR PTG/PTD:ID_SUBURUSAN="+TerimaPohonInfo.get("lblidSubUrusan"));
						//myLog.debug("TAB HANTAR PTG/PTD:KOD URUSAN="+kodUrusan);
						if(idnegeri.equals("12") || idnegeri.equals("13")){
							//suratExt = kodUrusan+idnegeri+kodTanah;
						}else {
							if(kodUrusan.equals("878")){
								suratExt = kodUrusan+kodTanah;
							}else{
								if(String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan")).equals("5"))
									suratExt = kodUrusan+"05";
								else if	(String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan")).equals("6"))
									suratExt = kodUrusan+"06";

							}
							
						}
						
						//myLog.info("suratExt="+suratExt);	
	    				this.context.put("suratExt",suratExt);
						if(button.equals("hantarptgptdsimpan")){
							//myLog.debug("hantarptgptdsimpan");
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));
							rsusf.setIdSuburusanstatusfail(Long.parseLong(idsuburusan));
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							//myLog.info("txttarikhsuratPTGPTD 1 ="+getParam("txttarikhsuratPTGPTD"));
							//rsusf.setTarikhMasuk(new Date(getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD")));
							rsusf.setTarikhMasuk(getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD"));
					    	kemaskiniSimpanStatusSelesai(rsusf,"10");
					    	statusView(idpermohonan);
					    	this.context.put("selectedTab", 3);
							
						}else if (button.equals("kemaskiniptgptd")){
							//String idSuburusanStatusFail = getParam("txtid");
							long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("10",String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan")),"=");						
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							String strDate = getParam("txttarikhsuratPTGPTD")
											.equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD");
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
							rsusf.setTarikhKemaskini(date);

							//myLog.info(setIdSuburusanstatus);
							rsusf.setIdKemaskini(Long.parseLong(userId));
							getStatus().kemaskiniStatus(rsusf, getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD"));
							statusView(idpermohonan);
							
						}else if (button.equals("paparanptgptd")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
							statusView(idpermohonan);

						}else if (button.equals("hapusptgptd")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
							statusView(idpermohonan);	
							
						}else {
							statusView(idpermohonan);
						}
						
					}else if (selectedTab3.equals("2")) {//TAB KEPUTUSAN 
						myLog.debug("TAB KEPUTUSAN");
						idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));
						if(button.equals("TambahKeputusan")){
							keputusanPermohonan("insert");
							initKeputusanPermohonan("");
							
						}else if (button.equals("KemaskiniKeputusan")){
							keputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							this.context.put("visible","0");
							context.put("pagemode", "view");
							
						}else if (button.equals("doViewForKemaskiniKeputusan")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
		    				context.put("pagemode", "kemaskini");
						
						}else {
							//System.out.println("default keputusan permohonan");
							initKeputusanPermohonan("");
							context.put("pagemode", "view");
						
						}
						
					}else if (selectedTab3.equals("3")) {
						myLog.debug("TAB TINDAKAN HQ: button="+button);
						myLog.debug("TAB TINDAKAN HQ: pageMode="+pageMode);
				    	boolean disableFungsi = false;
				    	//Hashtable hFungsi = null;
						//Hashtable hPergerakan = null; 		
				    	Vector<?> senaraiTindakan = null;
				    	String langkah = "84";
						String sumber = "PERMOHONAN_TINDAKAN";
						
				    	myLog.debug("TAB TINDAKAN HQ: idpermohonan="+idpermohonan);
				    	idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));
						pagemode = getParam("pagemode");
				    	String idFail = idfail;
				    	String idPermohonan = idpermohonan ;
				    	String idSubUrusan = idsuburusan;
				  		if(portal_role.contains("HQPengguna")){
				  			langkah = "84";
						}else if(portal_role.contains("HQPegawai")){
				  			langkah = "85";
						}else if(portal_role.contains("HQPengarah")){
				  			langkah = "86";
						}else if(portal_role.contains("Admin")){
				  			langkah = "87";
						} 
						myLog.info("969:"+disableFungsi+","+langkah);

						if(button.equals("tambah")){
							this.context.put("readonly", "");
							this.context.put("disabled", "");
							this.context.put("txtCatatan", "");
							this.context.put("tarikhHantar", "");
							mode = "new";
						
						}else if (button.equals("simpan")){
				    		Hashtable<String, String> hInsert = null;
			    			hInsert = new Hashtable<String, String>(); 			
			    			String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,langkah);
			    			
			    			hInsert.put("idStatusFail", idStatusFail);
					    	hInsert.put("idSusulan", idSusulan);
					    	hInsert.put("idMasuk", userId);
			    			hInsert.put("sumber", sumber);
							getISusulanPembangunan().simpanSusulanStatusFail(hInsert);
					    					
					    	context.put("readonly", "readonly=\"readonly\"");
							context.put("disabled", "disabled");
							mode = "view";
							//context.put("hPergerakan", hPergerakan);
							senaraiTindakan = new Vector<Object>();
						    senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
						    context.put("senaraiTindakan",senaraiTindakan);								
				    	
						}else if(button.equals("view")){
					    	String idSusulan = getParam("idsusulan");
					    	this.context.put("readonly", "readonly=\"readonly\"");
							this.context.put("disabled", "disabled");
							mode = "view";
							setSusulan(idPermohonan, idSusulan);
						
						}else if(button.equals("kemaskini")){
					    	String idSusulan = getParam("idsusulan");
					    	this.context.put("readonly", "");
							this.context.put("disabled", "");
							mode = "update";
							setSusulan(idPermohonan, idSusulan);
					   	
						}else if(button.equals("dokemaskini")){
					    	String idSusulan = getParam("idsusulan");
			    			//String idSusulanStatus = getISusulan().kemaskini(setSusulanStatusValues(idPermohonan));
							setSusulan(idPermohonan, idSusulan);
							this.context.put("readonly", "readonly=\"readonly\"");
							this.context.put("disabled", "disabled");
							mode = "view";										

						}else if (button.equals("hapus")){
					    	String idSusulan = getParam("idsusulan");
					    	getISusulan().hapus(idSusulan);		    					
					    	context.put("readonly", "readonly=\"readonly\"");
							context.put("disabled", "disabled");
							senaraiTindakan = new Vector<Object>();
						    senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
						    context.put("senaraiTindakan",senaraiTindakan);		
						    
						}else if (button.equals("KemaskiniKeputusan")){
							keputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							context.put("visible","0");
								
						}else if (button.equals("doViewForKemaskiniKeputusan")){
			    			readOnly = "";
			    			disabled = "";
			    			style = "";
						
						}else if (button.equals("tindakanpermohonan")){
							senaraiTindakan = new Vector<Object>();
						   	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
						   	context.put("senaraiTindakan",senaraiTindakan);
			    			
			    		}else {
							initKeputusanPermohonan("");
						}
					
					} else { //TAB BUKTI PEMBAYARAN 
						myLog.info("Tab BUKTI PEMBAYARAN PERMOHONAN");
//						String suratExt = "";
//						String kodUrusan = String.valueOf(TerimaPohonInfo.get("kodUrusan"));
//						String kodTanah = String.valueOf(TerimaPohonInfo.get("lblKodJTanah"));
//						if(idnegeri.equals("5")){
//							suratExt = kodUrusan+idnegeri+kodTanah;
//						}else if(idnegeri.equals("10")){
//							suratExt = kodUrusan+idnegeri+kodTanah;
//						}else if(idnegeri.equals("12")){
//							suratExt = kodUrusan+idnegeri+kodTanah;
//						}else if(idnegeri.equals("13")){
//							suratExt = kodUrusan+idnegeri+kodTanah;
//						}
//						myLog.info("suratExt="+suratExt);	
//	    				this.context.put("suratExt",suratExt);
						if(button.equals("TambahPembayaran")){
							myLog.info("button=TambahPembayaran");
		    				Pembayaran("insert");
		    				getValuesPembayaran("readonly");

						} else if (button.equals("KemaskiniPembayaran")){
							myLog.info("button=KemaskiniPembayaran");
		    				Pembayaran("update");
		    				getValuesPembayaran("readonly");
		    				this.context.put("visible","0");
		    			
		    			}else if (button.equals("doViewForKemaskiniBuktiBayaran")){
							myLog.info("button=doViewForKemaskiniBuktiBayaran");
							readOnly = "";
		    				disabled = "";
		    				style = "";
		    				getValuesPembayaran("");
		    			
		    			}else {	//DISPLAY		    				
							myLog.info(" button=else");
		    				getValuesPembayaran("");
		    			}
						
					}
					this.context.put("selectedTab3", selectedTab3);
				/**
				 * Maklumat Notis 5A
				 */
		    	} else if ("Notis5A".equals(mode)) {	    		
		    		idNotis = getParam("idNotis");
		    		this.context.put("idNotis", idNotis);
		    		myLog.info("mode:Notis5A::idNotis="+idNotis);
		    		
		    		//Form Untuk Tambah Notis 5A
		    		if(button.equals("TambahNotis")){
		    			this.context.remove("dat");
						inputStyleNum = " class=\"inputnumber\" ";
		    			context.put("inputStyleNum",inputStyleNum);
	    		
		    		} else if(button.equals("SimpanNotis")){
		    			//System.out.println("here");
		    			Notis5A("insert",null);
		    			senaraiNotis5A();
		    			
		    		} else if (button.equals("ViewNotis")) {
			    		myLog.info("mode:Notis5A::idNotis="+idNotis+",button="+button);
		    			viewNotis5A(idNotis);
						inputStyleNum = " class=\"inputnumber\" ";
		    			context.put("inputStyleNum",inputStyleNum);
		    			
		    		} else if (button.equals("KemaskiniNotis")) {
		    			tabmode = "2";
		    			Notis5A("update",idNotis);
		    			readOnly = "readonly";
		    			disabled = "disabled";
		    			style = readOnly + " class="+disabled+" ";
						inputStyleNum = " class=\"inputnumber\" ";
		    			context.put("inputStyleNum",inputStyleNum);
		    			senaraiNotis5A();
		    			viewNotis5A(idNotis);
		    			
		    		} else if(button.equals("HapusNotis")){
		    			getHTP().hapusNotis(idNotis);
		    			senaraiNotis5A();	
	    			
		    		} else if (button.equals("BuktiBayaranNotis")) {
		    			System.out.println("simpan bukti");
		    			SimpanBuktiBayaranNotis();
		    			viewBuktiBayaranNotis();
		    		
		    		} else if (button.equals("BayaranNotisHapus")) {
		    			String idBayaran = getParam("idBayaran");
		    			getHTP().hapusBayaran(idBayaran);
		    			viewBuktiBayaranNotis();
		    		
		    		//SENARAI NOTIS
		    		}else {
		    			senaraiNotis5A();
		    		}
	
		    		selectedTab = "2"; //TAB NOTIS
	    			myLog.info("Notis 5A :id_jenistanah="+id_jenistanah);
		    		if(id_jenistanah.equals("3")){
		    			 //TAB NOTIS TANAH PENGAMBILAN
		    			myLog.info("Notis 5A :pengambilan");
			    		selectedTab = "3";		    		
		    		}
	    			context.put("id_jenistanah", id_jenistanah);

		    	}
				//this.context.put("selectedTab", selectedTab);
				//log.info("462,selectedTab:"+selectedTab);
	    	// Lama - lama	   			
		    }else if(submit.equals("viewBorangK")){
		    	String idPermohonan = getParam("idpermohonan");
		    	String idFail = getParam("idfail");
		    	template_name = PATH +"borangKList.jsp";
				listBorangK = getKIntergration().getHTPBorangKList(idPermohonan);
		    	context.put("borangKList", listBorangK);
		    	context.put("idpermohonan", idPermohonan);
		    	context.put("idfail", idFail);
		    	context.remove("borangKMessage");
		    	
		    }else if(submit.equals("searchBorangK")){
		    	String idPermohonan = getParam("idpermohonan");
		    	String noFailPPT = getParam("noFailPPT");
		    	Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT);
		    	context.put("borangKList", k);
		    	template_name = PATH +"borangKList.jsp";
		    	context.put("idpermohonan", idPermohonan);
		    	
		    }else if(submit.equals("simpanBorangK")){
		    	template_name = PATH +"borangKList.jsp";
		    	String noFailPPT = getParam("noFailPPT");
		    	String idHakmilikPPT = getParam("idHakmilikPPT");
		    	String idPermohonanHTP = getParam("idpermohonan");
		    	getKIntergration().simpanHTPBorangK(idHakmilikPPT, idPermohonanHTP, userId);	    	
		    	Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT);
		    	context.put("borangKList", k);	    	
		    	context.put("borangKMessage", "REKOD BERJAYA DISIMPAN");
		    	
		    }else if(submit.equals("daftarBorangK")){
		    	template_name = PATH +"frmTerimaPermohonanBorangK.jsp";
				viewPohonFailBaruBorangK(mode);
  	
		    }else if(submit.equals("kemaskinipermohonanborangK")){
		    	myLog.debug("kemaskinipermohonanborangK:"+submit);
		    	this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
		    	template_name = PATH+"frmTerimaPohonMaklumatTABB.jsp";
		    	String hittButton = getParam("hittButton");
	    		//SIMPAN
	    		if (hittButton.equals("Simpan")) {
	    			template_name = PATH+"FrmTerimaPohon.jsp";
			    	String idHakmilikPPT = getParam("idHakmilikPPT");	    			
	    			String idFailBaru = doSimpanMaklumatPermohonan();
	    			AuditTrail.logActivity("1", getParam("socSeksyen"), this, session, "INS", "FAIL PERMOHONAN ["+fData.strNoFail+"] DITAMBAH ");
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFailBaru);
					idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
	    			//log.info("idHakmilikPPT="+idHakmilikPPT+",idPermohonan="+TerimaPohonInfo.get("lblIdPermohonan"));
			    	getKIntergration().simpanHTPBorangK(idHakmilikPPT, idpermohonan, userId);
			    	idfail = idFailBaru;

	    			
	    		} else {
	    			//System.out.println("****"+idfail);
	    			hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);	    			
	    		}
				idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
	   			//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	   			//ASSIGN VALUES
				//DISABLE OPTION UTK PILIH NEGERI
	    		socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri),"disabled class=disabled");
	    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(iddaerah),style, "onChange=\"doChanges2('"+tabmode+"')\"");
	    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2", Utils.parseLong(idmukim) ,style);
	    		context.put("socNegeri2", socNegeri);
	    		context.put("socDaerah2", socDaerah);
	    		context.put("socMukim2",socMukim );
				//String id_jenistanah = String.valueOf(terimaPohonInfo.get("lblidKodJTanah"));

		    }else if(submit.equals("maklumatbayarankeputusan")){
		    	template_name = PATHVER+"maklumatanah/frmMaklumaTanahTABB.jsp";
		    	myLog.info("maklumatbayarankeputusan="+submit);
	    		hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
				idpermohonan = (String)hashTerimaPohonInfo.get("lblIdPermohonan");
	   			iddaerah = String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"));
				//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);

	   			String id_jenistanah = hashTerimaPohonInfo.get("lblidKodJTanah").toString();
	    		this.context.put("idNegeriNotis",String.valueOf(hashTerimaPohonInfo.get("lblNegeri")));	

		    	this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,false,true,false,false);	
		    	//HEADER
	    		//SIMPAN
	    		//if (mode.equals("Pembayaran")) {
	    			
		    		selectedTab = "1"; //TAB KEPUTUSAN/PEMBAYARAN
		    		if(id_jenistanah.equals("3")){
			    		selectedTab = "2"; //TAB KEPUTUSAN/PEMBAYARAN (TANAH PENGAMBILAN)	    		
		    		}
	    			context.put("id_jenistanah", id_jenistanah);
		    		//myLog.debug("Pembayaran");
		    		this.context.put("visible","1");
					selectedTab3 = getParam("tabId3");
					myLog.debug("Keputusan/Pembayaran:selectedTab3="+selectedTab3);
					
					if (selectedTab3.equals("1")) {
					//if ("1".equals(tabmode)) { //TAB KEPUTUSAN 
						myLog.debug("TAB HANTAR PTG/PTD:selectedTab3="+selectedTab3);
						if(button.equals("hantarptgptdsimpan")){
							//myLog.debug("hantarptgptdsimpan");
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));
							rsusf.setIdSuburusanstatusfail(Long.parseLong(idsuburusan));
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							//myLog.info("txttarikhsuratPTGPTD 1 ="+getParam("txttarikhsuratPTGPTD"));
							rsusf.setTarikhMasuk(getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD"));
					    	kemaskiniSimpanStatusSelesai(rsusf,"10");
					    	statusView(idpermohonan);
					    	this.context.put("selectedTab", 3);
							
						}else if (button.equals("kemaskiniptgptd")){
							//String idSuburusanStatusFail = getParam("txtid");
							long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("10",String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan")),"=");						
							Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
							rsusf.setIdPermohonan(Long.parseLong(idpermohonan));
							rsusf.setIdFail(Long.parseLong(idfail));
							rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
							rsusf.setUrl(getParam("txtcatatanPTGPTD"));
							String strDate = getParam("txttarikhsuratPTGPTD")
											.equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD");
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
							rsusf.setTarikhKemaskini(date);

							//myLog.info(setIdSuburusanstatus);
							rsusf.setIdKemaskini(Long.parseLong(userId));
							getStatus().kemaskiniStatus(rsusf, getParam("txttarikhsuratPTGPTD").equals("")?"01/01/1900":getParam("txttarikhsuratPTGPTD"));
							statusView(idpermohonan);
							
						}else if (button.equals("paparanptgptd")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
							statusView(idpermohonan);

						}else if (button.equals("hapusptgptd")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
							statusView(idpermohonan);	
							
						}else {
							statusView(idpermohonan);						
						}
						
					}else if (selectedTab3.equals("2")) {
					//if ("1".equals(tabmode)) { //TAB KEPUTUSAN 
						myLog.debug("TAB KEPUTUSAN");
						idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));

						if(button.equals("TambahKeputusan")){
							keputusanPermohonan("insert");
							initKeputusanPermohonan("");
							
						}else if (button.equals("KemaskiniKeputusan")){
							keputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							this.context.put("visible","0");
							
						}else if (button.equals("doViewForKemaskiniKeputusan")){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
		    			
						}else {
							initKeputusanPermohonan("");					
						}
						
					}else if (selectedTab3.equals("3")) {
						myLog.debug("TAB TINDAKAN NEGERI");
						idsuburusan = String.valueOf(hashTerimaPohonInfo.get("lblidSubUrusan"));

							if(button.equals("TambahKeputusan")){
								keputusanPermohonan("insert");
								initKeputusanPermohonan("");
								
							}else if (button.equals("KemaskiniKeputusan")){
								keputusanPermohonan("update");
								initKeputusanPermohonan("readonly");
								this.context.put("visible","0");
								context.put("pagemode", "view");
							}else if (button.equals("doViewForKemaskiniKeputusan")){
			    				readOnly = "";
			    				disabled = "";
			    				style = "";
			    				context.put("pagemode", "kemaskini");
							}else {
								initKeputusanPermohonan("");
							
							}
					
					} else { //TAB BUKTI PEMBAYARAN 
						myLog.info("Tab BUKTI PEMBAYARAN PERMOHONAN");
						if(button.equals("TambahPembayaran")){
							myLog.debug("TambahPembayaran");
		    				Pembayaran("insert");
		    				//initMaklumatPembayaran("readonly");
		    				getValuesPembayaran("readonly");

						} else if (button.equals("KemaskiniPembayaran")){
		    				Pembayaran("update");
		    				//initMaklumatPembayaran("readonly");
		    				getValuesPembayaran("readonly");

		    				this.context.put("visible","0");
		    			
		    			}else if (button.equals("doViewForKemaskiniBuktiBayaran")){
							myLog.info("doViewForKemaskiniBuktiBayaran");
							readOnly = "";
		    				disabled = "";
		    				style = "";
		    				getValuesPembayaran("");
	    			
		    			}else {	//DISPLAY		    				
		    				//initMaklumatPembayaran("");
		    				getValuesPembayaran("");
		    			}
					    //this.context.put("pagemode", "view");
	
					}
					this.context.put("selectedTab3", selectedTab3);
					
		    }else if(submit.equals("Notis5A")){//NOTIS 5A
			 	template_name = PATHVER+"maklumatanah/frmMaklumaTanahTABB.jsp";
    			myLog.debug("Notis5A");
	    		hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);
	    		//this.context.put("pageMode", pageMode);	
				idpermohonan = (String)hashTerimaPohonInfo.get("lblIdPermohonan");
	   			iddaerah = String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"));
				//setMaklumatPermohonan(hashTerimaPohonInfo);
	   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
	   			String id_jenistanah = hashTerimaPohonInfo.get("lblidKodJTanah").toString();
	    		this.context.put("idNegeriNotis",String.valueOf(hashTerimaPohonInfo.get("lblNegeri")));	
			 	
	    		idNotis = getParam("idNotis");
	    		this.context.put("idNotis", idNotis);
	    		if(button.equals("TambahNotis")){
	    			//View Form Untuk Tambah Notis 5A
	    			this.context.remove("dat");
					inputStyleNum = " class=\"inputnumber\" ";
	    			context.put("inputStyleNum",inputStyleNum);
		    		
	    		} else if(button.equals("SimpanNotis")){
	    			Notis5A("insert",null);
	    			senaraiNotis5A();
	    			
	    		} else if (button.equals("ViewNotis")) {
	    			myLog.info("Notis 5A:ViewNotis = "+button);
	    			String idcarabayar = "-1";
	    			String caraBayaran = "";
	    			caraBayaran = getIBayaran().selectCaraBayaran("socBayaran", Utils.parseLong(idcarabayar)
	    					,style + " style=\"width:200\"", "","");
	    			this.context.put("socBayaran", caraBayaran);
	    			viewNotis5A(idNotis);
					inputStyleNum = " class=\"inputnumber\" ";
	    			context.put("inputStyleNum",inputStyleNum);
	    			
	    		} else if (button.equals("KemaskiniNotis")) {
	    			tabmode="2";
	    			Notis5A("update",idNotis);
	    			readOnly = "readonly";
	    			disabled = "disabled";
	    			style = readOnly + " class="+disabled+" ";
	    			senaraiNotis5A();
	    			viewNotis5A(idNotis);
	    		
	    		} else if(button.equals("HapusNotis")){
	    			getHTP().hapusNotis(idNotis);
	    			senaraiNotis5A();	
	    		
	    		} else if (button.equals("BuktiBayaranNotis")) {
	    			SimpanBuktiBayaranNotis();
	    			viewBuktiBayaranNotis();
	    		
	    		} else if (button.equals("BayaranNotisHapus")) {
	    			String idBayaran = getParam("idBayaran");
	    			getHTP().hapusBayaran(idBayaran);
	    			viewBuktiBayaranNotis();
	    		
	    		}else {
	    			//SENARAI NOTIS
	    			senaraiNotis5A();
	    			
	    		}

	    		selectedTab = "2"; //TAB NOTIS
    			myLog.info("Notis 5A :id_jenistanah="+id_jenistanah);
	    		if(id_jenistanah.equals("3")){
	    			myLog.info("Notis 5A :pengambilan");
		    		selectedTab = "3"; //TAB NOTIS TANAH PENGAMBILAN		    		
	    		
	    		}
    			context.put("id_jenistanah", id_jenistanah);				
    			myLog.info("1482:id_jenistanah="+id_jenistanah);
						
		    	
		    } else {
		    	//FIRST PAGE - SENARAI FAIL PERMOHONAN		    	
		    	if(flagAdvSearch.equals("open")){
		    		//myLog.info("Y");
		    		tarikhSurat = getParam("txdtarikhsuratfail");
		    		tarikhHantar = getParam("txdtarikhantarfail");
		    		tarikhKeputusan = getParam("txdtarikhkeputusanfail");
		    		senaraiTerimaPohon = getPR().TerimaPohongetList(null,nofail,txtTajukCarian==""?txtTajuk:txtTajukCarian
		    							,id_kementerian,id_agensi
		    							,idnegeri,iddaerah,idmukim
		    							,idurusan
		    							,tarikhSurat,tarikhBukaFail,tarikhHantar,tarikhKeputusan);
					doListing(session,action,mode,senaraiTerimaPohon);
					
		    	}else{
		    		//myLog.info("default page");
		    		senaraiTerimaPohon = getPR().TerimaPohongetList(userId,"",""
		    							,"",""
		    							,"","",""
		    							,"",tarikhBukaFail);
		    		//if(action.contains("Page")){
			    	if(action.contains("get")){
			    		senaraiTerimaPohon = getPR().TerimaPohongetList(null,nofail,txtTajukCarian==""?txtTajuk:txtTajukCarian
			    							,id_kementerian,id_agensi
			    							,idnegeri,iddaerah,idmukim
			    							,idurusan,tarikhBukaFail);
		    			
		    		}
			    	doListing(session,action,mode,senaraiTerimaPohon);
					this.context.put("SenaraiFails", senaraiTerimaPohon);			 

		    		this.context.put("flagAdvSearch",flagAdvSearch);
			    	this.context.put("idnegeri", idnegeri);	    		
		    	}
	    		if(mode.equals("changeNegeri")){
		    		/* 2017/02/11*/
		    		 senaraiTerimaPohon = getPR().TerimaPohongetList(null,nofail,txtTajukCarian
		    				 			,id_kementerian,id_agensi
		    				 			,idnegeri,iddaerah,idmukim
		    				 			,idurusan,tarikhBukaFail);
			    	 doListing(session,action,mode,senaraiTerimaPohon);
						this.context.put("SenaraiFails", senaraiTerimaPohon);			 
			    	 
	    		}else if(mode.equals("cancel")){
	    			idnegeri = "";
	    			nofail = "";
	        		txtTajuk = "";
	        	    iddaerah = "-1";
	        	    idmukim = "-1";
	        	    id_kementerian = "";
	        	    id_agensi = "-1";
	        	    idurusan = "-1";
	        	    tarikhSurat = "";
	        	    tarikhBukaFail = "";
	        	    tarikhHantar = "";
	        	    tarikhKeputusan = "";
	        	    senaraiTerimaPohon = getPR().TerimaPohongetList(userId,nofail,txtTajukCarian
	        	    					,id_kementerian,id_agensi
	        	    					,idnegeri,iddaerah,idmukim
	        	    					,idurusan,tarikhBukaFail);
			    	doListing(session,action,mode,senaraiTerimaPohon);
					this.context.put("SenaraiFails", senaraiTerimaPohon);			 
	
	    		}
	    		
		    }
			this.context.put("txdtarikhsuratfail",tarikhSurat);
			this.context.put("txdtarikhantarfail",tarikhHantar);
			this.context.put("txdtarikhkeputusanfail",tarikhKeputusan);
			this.context.put("txtTajukCarian",txtTajukCarian);
			selectedTab2 = (String)this.context.get("selectedTab2");
			if (selectedTab2 == null || "".equals(selectedTab2) ) {
	    		selectedTab2="0";
	    	}
			selectedTab3 = (String)this.context.get("selectedTab3");
			if (selectedTab3 == null || "".equals(selectedTab3) ) {
	    		selectedTab3="0";
	    	}
			this.context.put("selectedTab", selectedTab);
			this.context.put("selectedTab2", selectedTab2);
			this.context.put("selectedTab3", selectedTab3);
	    	//myLog.info("selectedtab:selectedTab="+selectedTab+",selectedTab2="+selectedTab2+",selectedTab3="+selectedTab3);
			
			this.context.put("tabmode", tabmode);
			this.context.put("button", button);
			this.context.put("mode", mode);
			this.context.put("submit", submit);
	    	this.context.put("style",style);
	    	this.context.put("idpermohonan",idpermohonan);
	    	this.context.put("idPermohonan",idpermohonan);//Utk frmTerimaPohon.jsp
	    	this.context.put("idhakmilikurusan",idhakmilikurusan);
	    	this.context.put("idfail",idfail);
	    	//myLog.info("selectedtab X:button="+button+",tabmode="+tabmode+",mode="+mode);
		    // 18/08/2010
	    	//myLog.info("flagAdvSearch="+flagAdvSearch);
	    	this.context.put("flagAdvSearch",flagAdvSearch);
	    	this.context.put("idnegeri", idnegeri); 
			this.context.put("inputStyle", inputStyle); 
			this.context.put("inputStyleNum", inputStyleNum); 
			this.context.put("notifikasiSimpan", notifikasiSimpan); 
			if(session.getAttribute("rbFile") == null){
				ResourceBundle rb = ResourceBundle.getBundle("file");
				session.setAttribute("rbFile", rb);
			
			}

				myLog.info("selectedTab == " + selectedTab + "\n" + "selectedTab2 == " + selectedTab2 + "\n" + "selectedTab3 == " + selectedTab3 + "\n" + "tabmode == " + tabmode + "\n" + "button === " + button + "\n" + "mode == " + mode + "\n" + "submit === " + submit);
			
		}catch(Exception e){
			e.printStackTrace();
			myLog.info("812");

		}
		myLog.info("page=================="+template_name);
		return template_name;
	
	}
	
	public void initContext() {
		this.context.put("socMukim","");
	}
	
	public void emptyContext() {
		this.context.put("txtNoFail","");
		this.context.put("txtTajukFail","");
		
	}
	
	public void doListing(HttpSession session,String action,String mode,Vector<Hashtable<String, String>> v) 
		throws Exception {
		if (mode.indexOf("change") != -1) {
			myLog.debug("changes detected...");
	    	initContext();

		}
        this.context.put("txtNoFail",nofail);
	    this.context.put("txtTajuk",txtTajuk);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChangeKementerianX()\" style=\"width:400\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), ""," style=\"width:400\"");
		//
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"onChange=doChangeNegeriX();");
		myLog.info("doListing:idnegeri="+idnegeri.length());
		
//		if(idnegeri.length()==0)
//			socNegeri = UtilHTML.SelectNegeri("socNegeri",null,null,"onChange=doChangeNegeriX();");
//		else
//			socNegeri = UtilHTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri.equals("")?"0":idnegeri),null,"onChange=doChangeNegeriX();");
		
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
		this.context.put("socUrusan", socUrusan);
	   	this.context.put("socKementerian", socKementerian);
	   	this.context.put("socAgensi", socAgensi);
	    this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);		
		this.context.put("senaraiList1", v); 
		setupPage(session,action,v);
		
	}
	
	public void setMaklumatPermohonanXX(Hashtable<String, String> TerimaPohonInfo) throws Exception {
		try {
			idurusan = (String)TerimaPohonInfo.get("lblidUrusan");
			idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
			this.context.put("noFail", TerimaPohonInfo.get("lblNoFail"));
			this.context.put("diDaftarOleh", TerimaPohonInfo.get("diDaftarOleh"));
			this.context.put("diDaftarPada", TerimaPohonInfo.get("diDaftarPada"));
		
			socNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idnegeri),"disabled class=disabled");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"lblDaerah",Utils.parseLong((String)TerimaPohonInfo.get("lblIdDaerah")),"disabled class=disabled");
			socKementerian =HTML.SelectKementerian("lblKementerian", Utils.parseLong((String)TerimaPohonInfo.get("lblidKementerian")), DISABILITY +"style=\"width:400\"");
			socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)TerimaPohonInfo.get("lblidAgensi")), DISABILITY +"style=\"width:400\"");
			socUrusan = UtilHTML.SelectUrusan("lblidUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidUrusan")),"disabled class=disabled");		
			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"lblidSubUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidSubUrusan")),"disabled class=disabled");
			socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils.parseLong((String)TerimaPohonInfo.get("lblidKodJTanah")), "disabled class=disabled");
			socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)TerimaPohonInfo.get("lblTanahKeselamatan")),"disabled class=disabled");
		
			context.put("lblNamaNegeri", TerimaPohonInfo.get("lblNamaNegeri"));
			context.put("lblNamaDaerah", TerimaPohonInfo.get("lblNamaDaerah"));
			context.put("lblKementerian", TerimaPohonInfo.get("lblKementerian"));
			context.put("lblAgensi", TerimaPohonInfo.get("lblAgensi"));
			context.put("lblUrusan", TerimaPohonInfo.get("lblUrusan"));
			context.put("lblNamaSubUrusan", TerimaPohonInfo.get("lblNamaSubUrusan"));
			context.put("lblKeterangan", TerimaPohonInfo.get("lblKeterangan"));
			context.put("lblTarafKeselamatan", TerimaPohonInfo.get("lblTarafKeselamatan"));
			context.put("socKementerian", socKementerian);
			context.put("socStatustanah", socStatustanah);
			context.put("socUrusan", socUrusan);
			context.put("socAgensi", socAgensi);
			context.put("socNegeri", socNegeri);
			context.put("socDaerah", socDaerah);
			context.put("socjenisFail", socjenisFail);
			context.put("socSubUrusan", socSubUrusan);
			context.put("socNegeri",socNegeri);
		
			context.put("txtTajuk",TerimaPohonInfo.get("lblTujuan"));
			context.put("txdTarikhSuratKJP",TerimaPohonInfo.get("lblTrkhSurat"));
			context.put("txtnoFailKJP",TerimaPohonInfo.get("lblNoRujKJP"));
			context.put("txtnoFailUPT", TerimaPohonInfo.get("lblNoRujUPT"));
			context.put("idpermohonan",TerimaPohonInfo.get("lblIdPermohonan"));
			context.put("txtnoFailNegeri",TerimaPohonInfo.get("lblNoRujLain"));
			context.put("txtnoFailPTG",TerimaPohonInfo.get("lblNoRujPTG"));
			context.put("txtnoFailPTD",TerimaPohonInfo.get("lblNoRujPTD"));

		} catch (Exception e) {
			throw new Exception("Ralat:"+e.getCause());	
		}
		
	}
	/**
	 * Fung untuk simpan
	 * @param mode
	 * @throws Exception
	 */
	public void viewPohonFailBaru(String mode) throws Exception {
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChanges()\" ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
		//socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChanges()\" ");
		socKementerian = UtilHTML.SelectKementerianAktif("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChanges()\" style=\"width:400\"");
 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "style=\"width:400\"");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChanges()\" ");//disabled class=disabled
 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(idsuburusan), "");
 		if (idurusan.equals("1")){
 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
 		}
 		if (mode.indexOf("reset") != -1) {
 			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong("99999"),"","onChange=\"doChanges()\" ");
 			socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong("99999"),"","");
 			socKementerian = UtilHTML.SelectKementerianAktif("socKementerian", Utils.parseLong("99999"), null,"onChange=\"doChanges()\" ");

 		}

		context.put("socKementerian", socKementerian);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri",socNegeri);
		String statusTanah = "";
		String jenisFail = "";
		if (mode.indexOf("doChange") != -1) {
			myLog.debug(" ** CHANGES DETECTED !!");
			//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
	 		// modified by rosli 2010/08/02
	 		//socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
	 		// modified by rosli 2010/09/03
			statusTanah = String.valueOf(getParamAsInteger("socStatustanah"));
			jenisFail = String.valueOf(getParamAsInteger("socjenisFail"));
			socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",Long.parseLong(statusTanah),"",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail",Long.parseLong(jenisFail),"");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk",getParam("txtTajuk"));
			context.put("noFail","");
			//context.put("txtnoFailKJP","");
	 		// modified by rosli 2010/09/03
			context.put("txtnoFailKJP",getParam("txtnoFailKJP"));
			//context.put("txtnoFailUPT","");
	 		// modified by rosli 2010/09/03
			context.put("txtnoFailUPT",getParam("txtnoFailUPT"));
			//context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
	 		// modified by rosli 2010/09/03
			context.put("txdTarikhSuratKJP",getParam("txdTarikhSuratKJP"));	
			context.put("statusTanah",idurusan);	
	    } else {
	    	//1-Tanah Kerajaan ,3-Tanah Kerajaan(Pengambilan),6-Tanah Kerajaan(Penswastaan)
	 		//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
	 		// modified by rosli 2010/08/02
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk","");
			context.put("noFail","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT","");
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
	 
	    }

	}
	
	public void viewPohonFailBaruBorangK(String mode) throws Exception {
		String idHakmilikPPT = getParam("idHakmilikPPT");
    	//String idPermohonanHTP = getParam("idpermohonan");
    	myLog.info("viewPohonFailBaruBorangK:idHakmilikPPT="+idHakmilikPPT);
		//utk simpan
    	borangK  = getHTP().carianFailPPT("", idHakmilikPPT);
    	idnegeri = borangK.getIdNegeri();
    	iddaerah = borangK.getIdDaerah();
    	id_kementerian = borangK.getIdKementerian();
    	id_agensi = borangK.getIdAgensi();
    	idurusan = "1";
    	String txtnoFailUPT = borangK.getNoFail();
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChangesBorangK()\" ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChangesBorangK()\" ");
 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChangesBorangK()\" ");//disabled class=disabled
 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(idsuburusan), "");
 		if (idurusan.equals("1")){
 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
 		}
 		
		context.put("socKementerian", socKementerian);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri",socNegeri);
		context.put("idHakmilikPPT",idHakmilikPPT);
				
		if (mode.indexOf("doChange") != -1) {
			myLog.debug(" ** CHANGES DETECTED !!");
	 		// modified by rosli 2010/08/02
	 		//socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",Long.parseLong(getParam("socStatustanah")),"",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk",getParam("txtTajuk"));
			context.put("noFail","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT",getParam("txtnoFailUPT"));
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
			
	    } else {
	    	//1-Tanah Kerajaan ,3-Tanah Kerajaan(Pengambilan),6-Tanah Kerajaan(Penswastaan)
	 		// modified by rosli 2010/08/02
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",Long.parseLong(JENISTANAHPENGAMBILAN),"",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk","");
			context.put("noFail","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT",txtnoFailUPT);
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
			
	    }

	}
	
	public String doSimpanMaklumatPermohonan() throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("id_Fail", idfail);
		hashData.put("id_Tarafkeselamatan", "1");
		hashData.put("id_Seksyen", getParam("socSeksyen"));
		hashData.put("id_Urusan", idurusan);
		hashData.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		hashData.put("tajuk_Fail", txtTajuk);
		hashData.put("no_Fail", nofail);
		hashData.put("id_Negeri", idnegeri);
		hashData.put("id_Daerah", iddaerah);
		hashData.put("id_Kementerian",id_kementerian);
		hashData.put("id_Agensi",id_agensi);
		hashData.put("flag_Fail",String.valueOf(1));
		hashData.put("id_Status", "1");
		hashData.put("id_Masuk", userId);
		hashData.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		hashData.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		hashData.put("noFailUPT", getParam("txtnoFailUPT"));  
		hashData.put("noFailKJP", getParam("txtnoFailKJP"));  
		hashData.put("StatusTanah", getParam("socStatustanah"));  
		return fData.simpanPermohonan(hashData,userId);
		//fData.janaFail(h);
	
	}
	
	public void doSimpanMaklumatAsasTanahX() throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idpermohonan", getParam("idpermohonan"));
		hashData.put("socNegeri", getParam("socnegeri2"));
		hashData.put("socDaerah", getParam("socdaerah2"));
		hashData.put("socMukim", getParam("socMukim2"));
		hashData.put("socLot", getParam("socLot"));
		hashData.put("noLot", getParam("noLot"));
		hashData.put("txtNoLot", getParam("txtNoLot"));
		hashData.put("noSyit", getParam("noSyit"));
		hashData.put("noPelan", getParam("noPelan"));
		hashData.put("socLuas", getParam("socLuas"));
		hashData.put("Luas", getParam("Luas"));
		hashData.put("LuasH", getParam("txtLuasGabung"));
		hashData.put("Lokasi", getParam("Lokasi"));
		//hashData.put("jenistanah", session.getAttribute("StatusTanah"));
		//2010/11/04
		//FrmTerimaPohonData.simpanMaklumatAsasTanah(hashData);
		getPR().simpanMaklumatAsasTanah(hashData);
		
	}
	
	public String simpanMaklumatAsasTanah() throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idpermohonan", getParam("idpermohonan"));
		hashData.put("socNegeri", getParam("socnegeri2"));
		hashData.put("socDaerah", getParam("socdaerah2"));
		hashData.put("socMukim", getParam("socMukim2"));
		hashData.put("socLot", getParam("socLot"));
		hashData.put("noLot", getParam("noLot"));
		hashData.put("txtNoLot", getParam("txtNoLot"));
		hashData.put("noSyit", getParam("noSyit"));
		hashData.put("noPelan", getParam("noPelan"));
		hashData.put("socLuas", getParam("socLuas"));
		//hashData.put("LuasH", getParam("LuasH") == null ? "" : getParam("LuasH"));		
		hashData.put("LuasH", getParam("Luas"));
		hashData.put("Luas", getParam("txtLuasGabung"));
		hashData.put("Lokasi", getParam("Lokasi"));
		//hashData.put("jenistanah", session.getAttribute("StatusTanah"));
		//2010/11/04
		//FrmTerimaPohonData.simpanMaklumatAsasTanah(hashData);
		// getPR().simpanMaklumatAsasTanah(hashData);
		return getPR().simpanMaklumatTanah(hashData);
		
	}
	
	public void doKemaskiniMaklumatAsasTanah() throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		hashData.put("idpermohonan", getParam("idpermohonan"));
		hashData.put("socNegeri", getParam("socnegeri2"));
		hashData.put("socDaerah", getParam("socdaerah2"));
		hashData.put("socMukim", getParam("socMukim2"));
		hashData.put("socLot", getParam("socLot"));
		hashData.put("noLot", getParam("noLot"));
		hashData.put("txtNoLot", getParam("txtNoLot"));
		hashData.put("noSyit", getParam("noSyit"));
		hashData.put("noPelan", getParam("noPelan"));
		hashData.put("socLuas", getParam("socLuas"));
		//hashData.put("Luas", getParam("Luas"));
		hashData.put("Luas", getParam("txtLuasGabung"));
		//hashData.put("LuasH", getParam("LuasH") == null ? "" : getParam("LuasH"));
		hashData.put("LuasH", getParam("Luas") == null ? "" : getParam("Luas"));
		hashData.put("Lokasi", getParam("Lokasi"));
		//hashData.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		//FrmTerimaPohonData.updateMAT(hashData);	
		getPR().kemaskiniMaklumatAsasTanah(hashData);
	}
	
	public String kemaskiniMaklumatAsasTanah() throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		hashData.put("idpermohonan", getParam("idpermohonan"));
		hashData.put("socNegeri", getParam("socnegeri2"));
		hashData.put("socDaerah", getParam("socdaerah2"));
		hashData.put("socMukim", getParam("socMukim2"));
		hashData.put("socLot", getParam("socLot"));
		hashData.put("noLot", getParam("noLot"));
		hashData.put("txtNoLot", getParam("txtNoLot"));
		hashData.put("noSyit", getParam("noSyit"));
		hashData.put("noPelan", getParam("noPelan"));
		hashData.put("socLuas", getParam("socLuas"));
		//hashData.put("Luas", getParam("Luas"));
		hashData.put("Luas", getParam("txtLuasGabung"));
		//hashData.put("LuasH", getParam("LuasH") == null ? "" : getParam("LuasH"));
		hashData.put("LuasH", getParam("Luas") == null ? "" : getParam("Luas"));
		hashData.put("Lokasi", getParam("Lokasi"));
		//hashData.put("jenistanah", session.getAttribute("StatusTanah"));
		// 2010/11/04
		//FrmTerimaPohonData.updateMAT(hashData);	
		return getPR().kemaskiniMaklumatTanah(hashData);
	
	}
	//LOKASI TANAH
	private void initMaklumatLokasi(String readmode) throws Exception {
//		Vector<Hashtable<String, String>> LokasiInfo = fData.getLokasiTanahInfo(idhakmilikurusan);
//		Hashtable<?, ?> detail = null;
//		if (LokasiInfo.size() > 0) {
//			detail = LokasiInfo.get(0);
//			tabmode = "4";//Update
//		
//		} else {
//			tabmode = "3";//Insert
//		}
//		this.context.put("detail",detail);
//			
//		if ("readonly".equals(readmode)) {
//			readOnly = "readonly";
//			disabled = "disabled";
//			style = readOnly + " class="+disabled+" ";
//			
//		} else {
//			readOnly = "";
//			disabled = "";
//			style = "";
//			
//		}
		PermohonanPerizapanBean ppb = new PermohonanPerizapanBean();
		ppb.initLokasiKeputusanPermohonan(context, fData, idpermohonan, tabmode, readmode, readOnly, disabled, style,"lokasi");				

	}
	
	private void initMaklumatCharting(String readmode) throws Exception {
//		Vector<Hashtable<String, String>> LokasiInfo = fData.getPelanLakaranInfo(idhakmilikurusan);
//		Hashtable<?, ?> detail = null;
//		if (LokasiInfo.size() > 0) {
//			detail = LokasiInfo.get(0);
//			tabmode = "4";//Update
//		} else {
//			tabmode = "3";//Insert
//		}
//		this.context.put("detail",detail);
//		
//		if ("readonly".equals(readmode)) {
//			readOnly = "readonly";
//			disabled = "disabled";
//			style = readOnly + " class="+disabled+" ";
//		
//		} else {
//			readOnly = "";
//			disabled = "";
//			style = "";
//		
//		}
		PermohonanPerizapanBean ppb = new PermohonanPerizapanBean();
		ppb.initLokasiKeputusanPermohonan(context, fData, idpermohonan, tabmode, readmode, readOnly, disabled, style,"detail");				

	}
	
	private void SimpanLokasiTanah() throws Exception {
		tanahBean.lokasiTanahSimpanKemaskini(userId,getParam("idhakmilikurusan"), getParam("txtJDbandar"), getParam("txtJDbandarPerihal")
		, getParam("txtJDLebuhRaya"), getParam("txtJDLebuhRayaPerihal"), getParam("txtJDJalanKeretapi"),  getParam("txtJDJalanKeretapiPerihal")
		, getParam("txtJDSungaiPantai"), getParam("txtJDSungaiPantaiPerihal")
		, getParam("txtSempadanUtara"), getParam("txtSempadanSelatan"),  getParam("txtSempadanTimur"), getParam("txtSempadanBarat")
		, getParam("txtKeteranganLain"), getParam("PerihalLokasi"), getParam("zone"), "update"); 
		
	}
	
	private void doKemaskiniLokasiTanah()throws Exception {
		tanahBean.lokasiTanahSimpanKemaskini(userId,getParam("idhakmilikurusan"), getParam("txtJDbandar"), getParam("txtJDbandarPerihal")
				, getParam("txtJDLebuhRaya"), getParam("txtJDLebuhRayaPerihal"), getParam("txtJDJalanKeretapi"),  getParam("txtJDJalanKeretapiPerihal")
				, getParam("txtJDSungaiPantai"), getParam("txtJDSungaiPantaiPerihal")
				, getParam("txtSempadanUtara"), getParam("txtSempadanSelatan"),  getParam("txtSempadanTimur"), getParam("txtSempadanBarat")
				, getParam("txtKeteranganLain"), getParam("PerihalLokasi"), getParam("zone"), "insert"); 
		
	}
	//CHARTING
	private void ChartingSimpanKemaskini(String tindakan) throws Exception {
		tanahBean.chartingSimpanKemaskini(userId, getParam("idpermohonan"), getParam("idhakmilikurusan"), getParam("RadioGroup1")
			, getParam("JumBayaranPelan"), getParam("ulasan"), getParam("keteranganImej")
			, String.valueOf(this.context.get("noFail")), tindakan);
		
	}
	//PEMBAYARAN
	private void getValuesPembayaran(String readmode) throws Exception {	
		Bayaran bayaran = null;
		bayaran = getIBayaran().getPembayaranByPermohonan(idpermohonan);
		myLog.info("bayaran:"+bayaran);
		String idCaraBayar = "0";
		String caraBayaran = "";
		String jumlahBayaranFormat = "0.00";
		readOnly = "";
		disabled = "";
		style = "";

		if (bayaran != null) {
			tabmode = "4";//Update
			idCaraBayar = getParam("socBayaran").equals("")?String.valueOf(bayaran.getIdCaraBayaran()):getParam("socBayaran");
			if(this.button_.equalsIgnoreCase("doViewForKemaskiniBuktiBayaran")||this.button_.equalsIgnoreCase("paparanbayaranper")){
				myLog.info("idCaraBayar="+idCaraBayar);
				caraBayaran = getIBayaran().selectCaraBayaran("socBayaran", Utils.parseLong(idCaraBayar)
						,style + " style=\"width:200\"", "","");

				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
				
			}else{			
				caraBayaran = bayaran.getCaraBayar();
				tabmode = "1";//View
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class="+disabled+" ";
				
			}
			jumlahBayaranFormat = Utils.format2Decimal(bayaran.getJumlah());
			
		} else {
			tabmode = "3";//Insert
			this.context.put("pagemode", "add");
			idCaraBayar = getParam("socBayaran");
			caraBayaran = getIBayaran().selectCaraBayaran("socBayaran", Utils.parseLong(idCaraBayar)
					,style + " style=\"width:200\"", "","");

		}
		this.context.put("jumlahBayaranFormat", jumlahBayaranFormat);
		this.context.put("socBayaran", caraBayaran);
		this.context.put("pembayaran", bayaran);
	
	}

	private void Pembayaran(String mode)throws Exception {		
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idpermohonan", getParam("idpermohonan"));
		//hashData.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		hashData.put("TarikhSuratKePTD", getParam("txtTarikhSuratKePTD"));
		hashData.put("NoResit", getParam("txtNoResit"));
		hashData.put("caraBayaran", getParam("socBayaran"));
		hashData.put("tempatBayaran", getParam("txttempatBayaran"));
		hashData.put("JumBayaran", getParam("txtBayaranProses"));
		hashData.put("TarikhResit", getParam("txtTarikhResit"));
		hashData.put("NoBaucerCekDraft", getParam("txtNoBaucerCekDraft"));
		hashData.put("TarikhBaucerCek", getParam("txtTarikhBaucerCek"));
		FrmTerimaPohonData.Pembayaran(hashData,mode);	
		
	}
	
	private void initKeputusanPermohonan(String readmode) throws Exception {			
//		Vector<Hashtable<String, String>> KeputusanInfo = fData.getKeputusanInfo(idpermohonan);
//		Hashtable<String, String> keputusan = null;
//		if (KeputusanInfo.size() > 0) {
//			keputusan = KeputusanInfo.get(0);
//			tabmode = "4";//Update
//			this.context.put("pagemode", "view");
//				
//		} else {
//			tabmode = "3";//Insert
//			this.context.put("pagemode", "add");
//				
//		}
//		this.context.put("keputusan", keputusan);
//		if ("readonly".equals(readmode)) {
//			readOnly = "readonly";
//			disabled = "disabled";
//			style = readOnly + " class="+disabled+" ";
//
//		} else {
//			readOnly = "";
//			disabled = "";
//			style = "";
//			
//		}
		PermohonanPerizapanBean ppb = new PermohonanPerizapanBean();
		ppb.initLokasiKeputusanPermohonan(context, fData, idpermohonan, tabmode, readmode, readOnly, disabled, style,"keputusan");				
	
	}
	
	public void KeputusanPermohonan(String mode) throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idpermohonan", getParam("idpermohonan"));
		hashData.put("idkeputusanmohon", getParam("idkeputusanmohon"));		
		hashData.put("NoRujukPTD", getParam("txtNoRujukPTD"));
		hashData.put("TarikhKeputusan", getParam("txtTarikhKeputusan"));
		hashData.put("PilihKeputusan", getParam("PilihKeputusan"));
		hashData.put("Catatan", getParam("txtCatatan"));
		hashData.put("idSubUrusan", idsuburusan);		
		//hashData.put("Catatan", getParam("txtCatatan"));
		FrmTerimaPohonData.KeputusanBayaran(hashData,mode);
		
	}
	
	public void keputusanPermohonan(String mode) throws Exception {
		KeputusanUlasan ku = new KeputusanUlasan();
		Tblrujsuburusanstatusfail sf = new Tblrujsuburusanstatusfail();
		sf.setIdMasuk(Long.parseLong(userId));
		sf.setIdPermohonan(Long.parseLong(getParam("idpermohonan")));
		sf.setIdSuburusanstatus(Long.parseLong(idsuburusan));
		sf.setIdFail(Long.parseLong(idfail));
		ku.setSubUrusanStatusFail(sf);
		ku.setIdUlasan(Long.parseLong(getParam("idkeputusanmohon").equals("")?"0":getParam("idkeputusanmohon")));
		ku.setNo(getParam("txtNoRujukPTD"));
		ku.setTarikhTerima(getParam("txtTarikhKeputusan"));
		ku.setKeputusan(getParam("PilihKeputusan"));
		ku.setUlasan(getParam("txtCatatan"));	
		//Penambahbaikan Fasa 3. Syaz. 24112014
		ku.setFlagNotifikasi(getParam("flagNotifikasi"));
		
		if (mode.equals("update")) {
			getIPermohonan().keputusanPermohonanTanah(ku);
		}else{
			getIPermohonan().keputusanPermohonanTanah(ku);
		}
		
	}
	/**
	 * NOTIS 5A
	 */
	public void senaraiNotis5A() throws Exception {
		Vector<Hashtable<String, String>> Notis5ASenarai = fData.getSenaraiNotis5A(idpermohonan);
		this.context.put("SenaraiNotis5A", Notis5ASenarai);
	
	}
	
	public void viewNotis5A(String idNotis) throws Exception {
		Hashtable<String, String> Notis5A = fData.getDataNotis5A(idNotis);
		this.context.put("dat", Notis5A);
		this.context.put("viewNotis", 0);
		viewBuktiBayaranNotis();
		
	}
	
	public void Notis5A(String mode,String idNotis) throws Exception {	
		hashData = new Hashtable<String, String>();
		hashData.put("idUser",userId);
		hashData.put("idpermohonan",Utils.isNull(getParam("idpermohonan")));
		hashData.put("NoKPPemilikAsal",Utils.isNull(getParam("txtNoKPPemilikAsal")));
		//myLog.info("TarikhNotis5A="+getParam("txtTarikhNotis5A"));
		hashData.put("TarikhNotis5A",Utils.isNull(getParam("txtTarikhNotis5A"))); //LS 80,
		hashData.put("TarikhLuputPertama",Utils.isNull(getParam("txtTarikhLuputPertama")));
		hashData.put("CukaiTahunPertama",Utils.isNull(Utils.RemoveComma(getParam("txtCukaiTahunPertama"))));
		hashData.put("BayaranNotis",Utils.isNull(getParam("txtBayaranNotis")));
		hashData.put("BayaranNotisF",Utils.isNull(getParam("txtBayaranNotisF")));
		hashData.put("Premium",Utils.isNull(Utils.RemoveComma(getParam("txtPremium"))));
		hashData.put("RayuanPremium",Utils.isNull(getParam("txtRayuanPremium")));
		hashData.put("RayuanLain",Utils.isNull(getParam("txtRayuanLain")));
		hashData.put("PerihalRayuan",Utils.isNull(getParam("txtPerihalRayuan")));
		hashData.put("TarikhTerimaNotis5A",Utils.isNull(getParam("txtTarikhTerimaNotis5A")));
		hashData.put("TarikhLuputNotisKedua",Utils.isNull(getParam("txtTarikhLuputNotisKedua")));
		hashData.put("TarikhLuputNotisKetiga",Utils.isNull(getParam("txtTarikhLuputNotisKetiga")));
		hashData.put("PendaftaranHakmilik",Utils.isNull(getParam("txtPendaftaranHakmilik")));
		hashData.put("BayarUkur", Utils.isNull(getParam("txtBayarUkur")));
		hashData.put("BayaranPerenggan",Utils.isNull(getParam("txtBayaranPerenggan")));
		hashData.put("TandaSempadan",Utils.isNull(getParam("txtTandaSempadan")));
		hashData.put("BayaranLain",Utils.isNull(getParam("txtBayaranLain")));
		hashData.put("JumBayaran",Utils.isNull(getParam("txtJumBayaran")));
		//--Azam Add
		hashData.put("BayaranLain2",Utils.isNull(getParam("txtBayaranLain2")));
		hashData.put("BayaranLain3",Utils.isNull(getParam("txtBayaranLain3")));
		hashData.put("BayaranFail",Utils.isNull(getParam("txtBayaranFail")));			
		//--Syaz Add
		hashData.put("TarikhRayuan",Utils.isNull(getParam("txtTarikhRayuan")));
		hashData.put("TempohRayuan",Utils.isNull(getParam("txTempohRayuan")));	
		FrmTerimaPohonData.Notis5A(hashData,mode,idNotis);	
		
	}
	
	private void SimpanBuktiBayaranNotis()throws Exception {
		hashData = new Hashtable<String, String>();
		hashData.put("idUser", userId);
		hashData.put("idPermohonan", getParam("dipermohonanNotis"));
		hashData.put("NoBaucer", getParam("txtNoBaucer") == null ? "" : getParam("txtNoBaucer"));
		hashData.put("TarikhBaucer", getParam("txtTarikhBaucer") == null ? "" : getParam("txtTarikhBaucer"));
		hashData.put("TarikhResit", getParam("txtTarikhResit") == null ? "" : getParam("txtTarikhResit"));
		hashData.put("JumBaucer", getParam("txtJumBaucer") == null ? "" : getParam("txtJumBaucer"));
		hashData.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
		
		//add yang ada hashData shj
		if(getParam("txtNoBaucer")!="" || getParam("txtNoResit")!=""){
			FrmTerimaPohonData.simpanBuktiNotis5A(hashData);		
		}
	
	}
	
	private void viewBuktiBayaranNotis() throws Exception {
		Vector<Hashtable<String, String>> BuktiBayaranInfo = fData.getBuktiBayaranNotis5A(idpermohonan);
		this.context.put("BuktiBayaranInfo", BuktiBayaranInfo);
	
	}
	
	public void setPaging(boolean page1,boolean page2,boolean page3,boolean page4,boolean page5) {
		this.context.put("page1",page1);
		this.context.put("page2",page2);
		this.context.put("page3",page3);
		this.context.put("page4",page4);
		this.context.put("page5",page5);
	
	}
	
	private void transferRecord(String idPermohonan) {
		HakmilikInterface bean = new HakmilikBean();
		bean.transferRecord(idPermohonan);
		
	}
	
	private void getValues(){
		System.out.println("getvalue daaerah------------------"+getParam("socdaerah2"));
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtnoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSuratKJP");
		String idPermohonan = getParam("idpermohonan");
		String noFailUPT = getParam("txtnoFailUPT");
		String noFailLain = getParam("txtnofailnegeri");
		String noFailPTG = getParam("txtnofailptg");
		String noFailPTD = getParam("txtnofailptd");
		String idDaerah = getParam("socdaerah2");			
		String idAgensi = getParam("lblidAgensi");			
		String idKeselamatan = getParam("lblTanahKeselamatan");

		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();

		fail.setIdTarafKeselamatan(idKeselamatan);
		
		permohonan.setTujuan(tajuk);		
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanKJP(failKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		htpPermohonan.setNoRujukanUPT(noFailUPT);
		htpPermohonan.setNoRujukanPTG(noFailPTG);
		htpPermohonan.setNoRujukanPTD(noFailPTD);
		permohonan.setTarikhTerima(tarikhSuratKJP);
		permohonan.setIdPermohonan(idPermohonan);
		permohonan.setIdMasuk(Long.parseLong(userId));
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);
		htpPermohonan.setIdAgensi(idAgensi);
		htpPermohonan.setIdDaerah(idDaerah);

		context.put("permohonan", htpPermohonan);
		
//		tanahBean.getValues(context, tajuk, failKJP, tarikhSuratKJP, idPermohonan
//			, noFailUPT, noFailLain, noFailPTG, noFailPTD, idDaerah
//			, idAgensi, idKeselamatan, userId);
				
	}

	private void getMaklumatTanah() throws Exception{
		Hashtable<String, String> t = getPR().getMaklumatAsasTanahKemaskini(idhakmilikurusan);
		idhakmilikurusan =String.valueOf(t.get("idhakmilikurusan"));
		idnegeri =String.valueOf(t.get("idnegeri"));
		iddaerah =String.valueOf(t.get("iddaerah"));
		idmukim =String.valueOf(t.get("idmukim"));
		txtNoLot =String.valueOf(t.get("nolot"));
		noSyit =String.valueOf(t.get("nosyit"));
		noPelan =String.valueOf(t.get("nopelan"));
		socLot =String.valueOf(t.get("idlot"));
		socLuas =String.valueOf(t.get("idluas"));
		Luas =String.valueOf(t.get("luasH"));
		LuasLama =String.valueOf(t.get("luas"));
		//myLog.info("LuasLama:"+LuasLama+",Luas:"+Luas+"");
	
//		String luas = "0";
//		String luas1 = "0";
//		String luas2 = "0";
//		if(socLuas.equals("1") || socLuas.equals("2") || socLuas.equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
//			if(socLuas.equals("1")){
//				if(LuasLama.contains("KM"))
//					luas = LuasLama.substring(0, (LuasLama.length()-2));
//				else
//					luas = LuasLama;
//				
//			}else if(socLuas.equals("2")){
//				if(LuasLama.contains("H"))
//					luas = LuasLama.substring(0, (LuasLama.length()-1));
//				else
//					luas = LuasLama;
//				
//			}else if(socLuas.equals("3")){
//				if(LuasLama.contains("MP"))
//					luas = LuasLama.substring(0, (LuasLama.length()-2));
//				else{
//					if(LuasLama.contains("M"))
//						luas = LuasLama.substring(0, (LuasLama.length()-1));
//					else
//						luas = LuasLama;
//				}
//				
//			}else if(socLuas.equals("5")){
//				if(LuasLama.contains("KP"))
//					luas = LuasLama.substring(0, (LuasLama.length()-2));
//				else{
//					if(LuasLama.contains("K"))
//						luas = LuasLama.substring(0, (LuasLama.length()-1));
//					else
//						luas = LuasLama;
//				}
//				
//			}else if(socLuas.equals("6")){
//				if(LuasLama.contains("P"))
//					luas = LuasLama.substring(0, (LuasLama.length()-1));
//				else
//					luas = LuasLama;
//				
//			}
//	
//		}else if(socLuas.equals("4")){
//			if(LuasLama.contains("E,") && LuasLama.contains("R,")){
//				luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//				luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("R,"));
//				luas2 = LuasLama.substring(LuasLama.indexOf("R,")+2,(LuasLama.length()-1));
//			
//			}
//		}else if(socLuas.equals("7")){
//				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//				if(LuasLama.contains("E,") && LuasLama.contains("D")){
//					luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
//					luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("D"));
//				}
//				
//		}else if(socLuas.equals("8")){
//			//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
//			if(LuasLama.contains("R,") && LuasLama.contains("J,")){
//				luas = LuasLama.substring(0,LuasLama.indexOf("R,"));
//				luas1 = LuasLama.substring(LuasLama.indexOf("R,")+2,LuasLama.indexOf("J"));
//				luas2 = LuasLama.substring(LuasLama.indexOf("J,")+2,(LuasLama.length()-1));
//			}
//			
//		}else{ //7||9 (TIADA SAMPLE DATA)
//			luas = LuasLama;
//			
//		}
//		this.context.put("txtLuasLama1", luas1.trim());	
//		this.context.put("txtLuasLama2", luas2.trim());	
//		this.context.put("txtLuasLama", luas);	
		tanahBean.maklumatLuas(context, socLuas, getParam("socLuas"), LuasLama);	
		Lokasi = String.valueOf(t.get("lokasi"));

	}
	
	public void statusView(String idPermohonan) throws Exception{			
		Hashtable<String, String> hInfo = null; 		
		try{
			hInfo = new Hashtable<String, String>();
			hInfo = getStatus().getInfoStatusPermohonan(idPermohonan,"10");	
//			myLog.info("statusView("+idPermohonan+"):hInfo="+hInfo);
//			NumberToWords nw = new NumberToWords();
//			myLog.info(nw.convertTwoPart("12356777.22"));
			
			if(hInfo == null){								
				this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				readOnly = "";
				disabled = "";
				style = "";
				tabmode = "3";//Insert
				
			}else{
				tabmode = "4";//Update
				if(this.button_.equalsIgnoreCase("paparanptgptd")){
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "");
					this.context.put("classDis", "");
					this.context.put("pagemode", "kemaskini");
					
				}else{					
					tabmode = "1";//View
					this.context.put("selesaiBean", hInfo);
					this.context.put("mode", "disabled='disabled'");
					this.context.put("classDis", "class='disabled'");
					this.context.put("pagemode", "view");
					
				}

			}			
			
		}catch(Exception e){
			e.printStackTrace();			
		}
	
	}	
	// Skrin Tindakan
	private void kemaskiniSimpanStatusSelesai(Tblrujsuburusanstatusfail rsusf,String langkah) throws Exception {
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,String.valueOf(rsusf.getIdSuburusanstatusfail()),"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			subUrusanStatusFailN.setTarikhMasuk(rsusf.getTarikhMasukStr());
			getHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN,rsusf.getTarikhMasukStr());
			
		} catch (Exception e) {
			throw new Exception("Ralat FrmPermohonanTanah[1674]:"+e.getCause());			
		}
		
	}

	private String kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan
		,String idSubUrusan,String langkah) throws Exception {
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			//myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan);
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl("-");
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
//			String strDate = getParam("txtarikhkeputusan").equals("")?"01/01/1900":getParam("txtarikhkeputusan");
//			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
//			subUrusanStatusFailN.setTarikhMasuk(date);

			return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txtarikhkeputusan")));
				
		} catch (Exception e) {
			throw new Exception("Ralat "+this.className+", kemaskiniSimpanStatusSelesai:"+getIHTP().getErrorHTML(e.getMessage()));			
		}
	
	}		
	  
	private void setSusulan(String idPermohonan, String idSusulan) throws Exception{
		hashData = new Hashtable<String, String>();
		hashData = getISusulan().getMaklumat(idPermohonan, idSusulan);
		this.context.put("idSusulanStatus", hashData.get("idSusulanStatus"));
		this.context.put("idSusulan", idSusulan);
		this.context.put("txtCatatan", hashData.get("keterangan"));
		this.context.put("tarikhHantar",hashData.get("tarikh"));
		
	}

	private Hashtable<String, String> setSusulanValues(String idPermohonan) throws Exception{
		String tarikh = getParam("txtarikhkeputusan");
		String catatan = getParam("txtcatatan");
		hashData = new Hashtable<String, String>();
		hashData.put("txdTarikh", tarikh);
		hashData.put("idPermohonan", idPermohonan);
		hashData.put("catatan", catatan);
		hashData.put("idMasuk",userId);
		//h.put("idSusulanStatus", getParam("idsusulanstatus"));
		return hashData;
	
	}

	private IBorangKIntergration getKIntergration(){
		if(kIntergration == null)
			kIntergration = new BorangKIntergrationBean();
		return kIntergration;
		
	}
	
	private IHtp getHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
		
	}

	private IPermohonanPerizapan getPR(){
		if(ipr == null)
			ipr = new PermohonanPerizapanBean();
		return ipr;
	
	}
	
	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
			
	}
	
	private IHTPPermohonan getIPermohonan(){
		if(iPermohonan==null){
			//iPermohonan = new HTPPermohonanBean();
			iPermohonan = new HTPPermohonanTanahBean();
		}
		return iPermohonan;
			
	}

	private IHTPBayaran getIBayaran(){
		if(iBayaran==null){
			iBayaran = new HTPBayaranPermohonanBean();
		}
		return iBayaran;
			
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	
	}
	
	private IHTPSusulan getISusulan(){
		if(iSusulan==null){
			iSusulan = new HTPSusulanBean();
		}
		return iSusulan;
				
	}
	
	private IHTPSusulan getISusulanPembangunan(){
		if(iSusulanPembangunan==null){
			iSusulanPembangunan = new HTPSusulanPembangunanBean();
		}
		return iSusulanPembangunan;
				
	}
	private IIntegrasi getIntegrasi(){
		if(integrasiGIS==null){
			integrasiGIS = new IntegrasiGISBean();
		}
		return integrasiGIS;
				
	}
	  
		  
}
	