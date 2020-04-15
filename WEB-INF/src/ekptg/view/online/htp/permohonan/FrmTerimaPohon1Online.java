package ekptg.view.online.htp.permohonan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.intergration.BorangKIntergrationBean;
import ekptg.intergration.IBorangKIntergration;
import ekptg.intergration.IIntegrasi;
import ekptg.intergration.IntegrasiGISBean;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPEmelPermohonanBean;
import ekptg.model.htp.HTPEmelSemakBean;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HTPPermohonanTanahBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPBayaran;
import ekptg.model.htp.IHTPEmel;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.IUtilHTML;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.UtilHTMLBean;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.KeputusanUlasan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.permohonan.HTPBayaranPermohonanBean;
import ekptg.model.htp.permohonan.IPermohonanPerizapan;
import ekptg.model.htp.permohonan.PermohonanPerizapanBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.HTPSusulanPembangunanBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserBean;

public class FrmTerimaPohon1Online extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3744798063048260707L;
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.permohonan.FrmTerimaPohon1Online.class);
	private ekptg.model.htp.IHtp iHtp = null;
	private final String PATH="app/htp/permohonan/online/";
	private final String PATHVER = PATH+"v02/";
	//private final String PATHONLINE="app/htp/permohonan/online/";	
	//private final String PATH="app/htp/permohonan/online/";	
	private final String JENISTANAH = "1,3,6";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private HtpPermohonan htpPermohonan = null;
	private IPenggunaKementerian iPengguna = null;
	private Permohonan permohonan = null;
	private PfdFail fail = null;
	private UserKementerian uk = null;

	FrmSenaraiFailTerimaPohonData fData = null;
	//INIT
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
	String idUser = "";
	String tabmode = "";
	String readOnly = "";
	String disabled = "";
	String style = "";
	String idNotis="";
	//ppt
	Vector listPPT = null;
	Vector listHakmilik = null;
	Vector listBorangK =  null;
	Vector senaraiTerimaPohon = null;
	//18/08/2010
	String flagAdvSearch = "";
	private IBorangKIntergration kIntergration = null;
	
	//----- Start addby zul 27/7/2017 -----
	private Hashtable<String, String> hashTerimaPohonInfo = null; 
	HTPPermohonanTanahBean tanahBean =null;
	private String DISABILITY = " disabled class=\"disabled\" ";
  	private String inputStyle = DISABILITY;
	private String DISABILITYNUM = " disabled class=\"inputnumberdisabled\" ";
  	private String inputStyleNum = DISABILITYNUM;
  	private String pageMode = "";
  	
  	//this for jkp online
  	private IUserPegawai iUser = null;
  	private String userID = null;
	String jawatan = "";
	String idJawatan = "";
	
	private FrmRekodUtilData frmRekodUtilData = null;
	String userId = "";
	private IHtp iHTP = null; 
	
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	private IHTPPermohonan iHTPPermohonan = null;
	private IOnline iOnline = null;
	
	private String idStatus = "";
	private String listStatus = "";
	//private String IDSUBURUSANS = "1,10";
	private String IDSUBURUSANS = "23,25";
	
	private String tarikhBukaFail = "";
	private String txtNoLot = "";
	private String noSyit = "";
	private String noPelan = "";
	private String socLot = "";
	private String socLuas = "";
	private String Luas = "";
	private String LuasLama = "";
	private String Lokasi = "";
	
	Hashtable<String, String> hashData = null;
	private ekptg.model.htp.permohonan.IPermohonanPerizapan ipr= null;
	Vector<Hashtable<String, String>> maklumatAsasTanahInfo = null;
	private IHTPStatus iStatus = null;
	String button_ = "";
	private ekptg.model.htp.IHTPPermohonan iPermohonan = null;
	private IHTPSusulan iSusulan = null;
	private IHTPSusulan iSusulanPembangunan = null;
	private IHTPBayaran iBayaran = null;
	
	private ekptg.model.htp.IHTPEmel iHTPEmel = null;
	//----- End addby zul 27/7/2017 -----
	
  	private IntegrasiGISBean integrasiGIS = null;
  	
	//END INIT	
	@Override
	public String doTemplate2() throws Exception {	
		fData = FrmSenaraiFailTerimaPohonData.getInstance();
		HttpSession session = this.request.getSession();
		
		//idUser = (String)session.getAttribute("_ekptg_user_id");
		idUser = String.valueOf(session.getAttribute("_ekptg_user_id"));
		if(idUser == null){
			getIHTP().getErrorHTML("[HTP PERMOHONAN] SILA LOGIN SEMULA");
		}
		//Parameters
		String submit = getParam("command");
		String action = getParam("action");
		String mode = getParam("mode");
		String selectedTab = getParam("tabId");
		String doChange = getParam("doChange");
		String pagemode = getParam("pagemode");
		String button = getParam("button");
		String idNotis = getParam("idNotis");
		tabmode = getParam("tabmode");
		idpermohonan = getParam("idpermohonan");
		idfail = getParam("idfail");
		idhakmilikurusan = getParam("idhakmilikurusan");
		//GET PARAM
		nofail = getParam("txtNoFail");
	 	//txtTajuk = getParam("txtTajuk");
	 	//18/08/2010
		txtTajuk = getParam("txtTajukFail");
	 	id_kementerian = getParam("socKementerian");
	 	id_agensi = getParam("socAgensi");
	 	idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	idurusan = getParam("socUrusan");
	 	
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");
	 	
		if (id_kementerian == null || id_kementerian.trim().length() == 0){
			uk = getIPengguna().getKementerian(idUser);
			if(uk == null){
				//throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
				throw new Exception(getHTP().getErrorHTML("[ONLINE-HTP PERMOHONAN] KEMENTERIAN TIDAK DIJUMPAI"));
			}
		
			myLog.info("id_kementerian="+uk.getAgensi().getKementerian().getIdKementerian());
			id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
			//System.out.println("+++++"+id_kementerian);
		}
	 	
		if (selectedTab == null || "".equals(selectedTab) ) 
		{
    		selectedTab="0";
    		tabmode = "0";
    	}
		String selectedTab2 = "";
		String selectedTab3 = "";
		
		this.context.put("readOnly", "");
		this.context.put("disabled", "");			
		//DEFAULT template
		String template_name = PATH+"indexOnline.jsp";
		myLog.debug("SUBMIT :: " +submit+",MODE :: " + mode+",BUTTON :: " + button +",selectedTab :: " +selectedTab+",tabmode :: " + tabmode+",PAGEMODE :: " +pagemode);
		this.context.put("showSahkanButton",false);
		this.context.put("sahkanresult","");
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		
		//---------- Start addby zul 27/7/2017----------
		context.put("path", PATH); 
		String command = getParam("command");
		String noRujukanOnline = getParam("txtNoRujukanOnline");
		String portal_role = String.valueOf(session.getAttribute("myrole"));
		//---------- end addby zul 27/7/2017---------- 
		
		try{
			
			//---------- Start addby zul ----------
			tanahBean = new HTPPermohonanTanahBean();
			tanahBean.setLabelDaerah(context, idnegeri);
			
			//this for JKP online
			HttpSession ses = request.getSession();
			userID = (String)ses.getAttribute("_ekptg_user_id");
			Hashtable hUser = getIUser().getPengguna(userID);
			jawatan = String.valueOf(hUser.get("jawatan"));
			idJawatan = String.valueOf(hUser.get("idjawatan"));
			context.put("idjawatan", idJawatan);
			/*myLog.info("JAWATAN ===== " + jawatan);
			myLog.info("JAWATAN ===== " + hUser.get("idjawatan"));*/
			context.remove("showButton");
			
			if ("viewMaklumatPermohonan".equals(submit)) {
				myLog.info("viewMaklumatPermohonan ===== " + template_name);
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,true,true,false,false);
				template_name = PATH+"frmMaklumatPermohonanView.jsp";
				Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	   			//context.remove("hideButton");
	   			context.put("showButton", "show");
	   			
			}else if(submit.equals("maklumatpermohonan")) {
				template_name = PATH + "frmMaklumatPermohonanForm.jsp";
	    		String hittButton = getParam("hittButton");
				myLog.info("submit = maklumatpermohonan, hittButton="+hittButton);
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
	   				idnegeri = String.valueOf(hashTerimaPohonInfo.get("lblIdNegeri"));
	   				inputStyle = " class=\"\" ";
    				pageMode = "kemaskini";
       	    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(String.valueOf(hashTerimaPohonInfo.get("lblIdDaerah"))),disability,"");
    				socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)hashTerimaPohonInfo.get("lblidAgensi")),disability+" style=\"width:400\"");
    				socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)hashTerimaPohonInfo.get("lblTanahKeselamatan")),disability);
	    		
	   			} else if (hittButton.equals("kemaskinisimpan")){
					template_name = PATH +"frmMaklumatPermohonanView.jsp";
	    			getValues();
	    			
					idpermohonan = (String)hashTerimaPohonInfo.get("lblIdPermohonan");
					//htpPermohonan.setIdDaerah(String.valueOf(TerimaPohonInfo.get("lblIdDaerah")));	//lblIdDaerah
					//htpPermohonan.setIdAgensi(String.valueOf(TerimaPohonInfo.get("lblidAgensi")));	//lblidAgensi
					htpPermohonan.setIdJenisTanah(String.valueOf(hashTerimaPohonInfo.get("lblidKodJTanah")));	//lblidKodJTanah
					htpPermohonan.setIdHtpPermohonan(String.valueOf(hashTerimaPohonInfo.get("idHtpPermohonan")));	// idHtpPermohonan
					//fail.setIdTarafKeselamatan(String.valueOf(TerimaPohonInfo.get("lblTanahKeselamatan")));	//lblTanahKeselamatan
					fail.setTarikhDaftarFail(String.valueOf(hashTerimaPohonInfo.get("tarikhDaftarFail")));//tarikhDaftarFail
					permohonan.setPfdFail(fail);
					permohonan.setIdMasuk(Long.parseLong(idUser));
					//TBLPERMOHONAN (TARIKH_SURAT,TARIKH_TERIMA,TUJUAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLHTPPERMOHONAN (NO_RUJUKAN_LAIN,NO_RUJUKAN_KJP,NO_RUJUKAN_UPT,NO_RUJUKAN_PTG,NO_RUJUKAN_PTD
					//	,ID_AGENSI,ID_DAERAH,ID_JENISTANAH,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					//TBLPFDFAIL (TAJUK_FAIL,TARIKH_DAFTAR_FAIL,ID_TARAFKESELAMATAN,ID_KEMASKINI,TARIKH_KEMASKINI-Auto)
					htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan, idpermohonan, "");
					hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idfail);   
					System.out.println("hashTerimaPohonInfo ======== " + hashTerimaPohonInfo.size());
		   			//setMaklumatPermohonan(hashTerimaPohonInfo);
		   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);
					readOnly = "readonly";
					context.put("disabled", "disabled");	
	
	   			}else if(hittButton.equals("view")) {
					template_name = PATH + "frmMaklumatPermohonanView.jsp";
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
	    	//---------- End Addby zul ---------- 
	    		
			}else if ("terimapohoncarian".equals(submit)) {
				
				idStatus = getParam("listStatus") == "" ? "0" : getParam("listStatus");
				myLog.info("idStatus ===== " + idStatus);
				
				/*String langkah = "-4,-3,-2,-1";
				listStatus = getIUtil().SelectStatusByLangkah("socStatus",Long.parseLong(idStatus),"",langkah);*/
				listStatus = getIUtil().SelectStatusPermohonan("socStatus",Long.parseLong(idStatus),"",IDSUBURUSANS);
				//myLog.info("listStatus ==== " + listStatus);
				context.put("listStatus", listStatus);
				
				//CARIAN
				myLog.debug("terimapohoncarian:"+submit+"::"+template_name);
		    	flagAdvSearch = "Y";
				senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
						id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan,idStatus,noRujukanOnline); //--- addbyzul for carian by idStatus dan noRujukanOnline--
				doListing(session,action,mode,senaraiTerimaPohon);

				
				
				
		    } else if ("kemaskinipermohonan".equals(submit)) {
		    	myLog.debug("kemaskinipermohonan="+submit);
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,false,true,false,false);
		    	template_name = PATH+"frmTerimaPohonMaklumatTABBOnline.jsp";		    	
		    	//HEADER
		    	Hashtable TerimaPohonInfo = null;
	    		String hittButton = getParam("hittButton");
	    		String pageMode = "";
	    		
	    		//SIMPAN
	    		if ("Simpan".equals(hittButton)) {
	    			myLog.info("Simpan==");
	    			String idFailBaru = doSimpanMaklumatPermohonan();
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idFailBaru);
	    			idfail = idFailBaru;
	    			
	    		} else if ("kemaskinisimpan".equals(hittButton)){
	    			template_name = PATH+"frmMaklumatPermohonanOnline.jsp";
	    			getValues();
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	    			htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan, idpermohonan, "");
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	  				
				} else if ("kemaskini".equals(hittButton)){
	    			template_name = PATH+"frmMaklumatPermohonanOnline.jsp";
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					readOnly = "";
					disabled = "";
					style = "";   
					pageMode = "kemaskini";
					this.context.put("readOnly", readOnly);
					this.context.put("disabled", disabled);	
					
	    		}else{
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	    		}
				this.context.put("pageMode", pageMode);	
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	    		
		    	//TAB NAVIGATION
	    		idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
	    		iddaerah = (String)TerimaPohonInfo.get("lblIdDaerah");
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
				context.put("idNegeriNotis",String.valueOf(TerimaPohonInfo.get("lblNegeri"))); //add bymatjuju
	    		
	    		if(iddaerah == null || "".equals(iddaerah)) iddaerah="-1";
	    		
	    		//setPaging(false,false,true,false,false);
	    		
		    	if ("MakAsasTanah".equals(mode)) {
		    		//MAKLUMAT ASAS TANAH
		    		System.out.println("MakAsasTanah");
	    			String txtNoLot = getParam("txtNoLot");
	    			String noSyit = getParam("noSyit");
	    			String noPelan = getParam("noPelan");
		    		String socLot = getParam("socLot");
		    		String socLuas = getParam("socLuas");
		    		String LuasLama = getParam("txtLuas1");
		    		String Luas = getParam("Luas");
		    		String Lokasi = getParam("Lokasi");
		    		idmukim = getParam("socMukim2");
		    		noLot = getParam("socLot");
	    			//IF DO CHANGES DETECTED
	    			if (doChange.indexOf("doChange") != -1) {
	    				//OVERRIDE VALUES
	    				myLog.debug("changes...");
	    				myLog.debug("TAB MODE:"+tabmode);
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    			}
	    			
		    		if("SimpanMaklumatAsasTanah".equals(button)){
		    			myLog.debug(" ********** SimpanMaklumatAsasTanah ****8");
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				doSimpanMaklumatAsasTanah();
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    				
	    			} else if ("KemaskiniMaklumatInfo".equals(button)) {
	    				myLog.info("KemaskiniMaklumatInfo");
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";

	    				Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahKemaskini(idhakmilikurusan);
	    				Hashtable t = (Hashtable)MaklumatAsasTanahInfo.get(0);
			    		idhakmilikurusan =(String)t.get("idhakmilikurusan");
			    		idnegeri =(String)t.get("idnegeri");
			    		iddaerah =(String)t.get("iddaerah");
			    		idmukim =(String)t.get("idmukim");
			    		txtNoLot =(String)t.get("nolot");
			    		noSyit =(String)t.get("nosyit");
			    		noPelan =(String)t.get("nopelan");
			    		socLot =(String)t.get("idlot");
			    		socLuas =(String)t.get("idluas");
			    		Luas =(String)t.get("luas");
			    		LuasLama =(String)t.get("luasLama");
			    		Lokasi =(String)t.get("lokasi");
	    				
	    			} else if ("doViewForKemaskini".equals(button)){
	     	    		//
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else if ("doKemaskiniMaklumatAsasTanah".equals(button)){
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				doKemaskiniMaklumatAsasTanah();
	    			
	    			} else if ("DetailLot".equals(button)){
	    				//SET SELECTED
	    				myLog.debug(" ** "+button+" **");
	    				selectedTab2 = getParam("tabId2");
	    				this.context.put("selectedTab2", selectedTab2);
	    				if ("1".equals(selectedTab2)) {
	    					initMaklumatCharting("");
	    				} else {
	    					initMaklumatLokasi("");
	    				}
	    			
	    			} else if ("SimpanDetailLot".equals(button)) {
	    				myLog.debug(" ** "+button+" **");
	    				SimpanLokasiTanah();
	    				initMaklumatLokasi("readonly");
	       			
	    			}else if ("doKemaskiniDetailLot".equals(button)){
	    				myLog.debug(" ** "+button+" **");
	    				doKemaskiniLokasiTanah();
	    				initMaklumatLokasi("readonly");
	    			
	    			} else if ("doViewForKemaskiniDetailLot".equals(button)){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else if ("SimpanCharting".equals(button)){
	    				myLog.debug(" ** "+button+" **");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				SimpanCharting();
	    				initMaklumatCharting("readonly");
	    			
	    			} else if ("doKemaskiniCharting".equals(button)){
	    				myLog.debug(" ** "+button+" **");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				KemaskiniCharting();
	    				initMaklumatCharting("readonly");
	    			
	    			}else if ("doViewForKemaskiniCharting".equals(button)){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			}else if("hantarPengesahan".equals(hittButton)){
//		    			System.out.println("ZUL TEST MASUK");
	    				myLog.debug("HANTAR PENGESAHAN");
		    			
		    			getPermohonanInfo();
		    			myLog.info("simpanpengesahan = id_permohonan :::::::::: "+ htpPermohonan.getPermohonan().getIdPermohonan());
		    			String semakMode="";
		    			String langkah = "";
		    			/*
		    			 * -4 untuk status - Pendaftaran
		    			 * -3 untuk status - Tindakan Pegawai 
		    			 * -2 untuk status - Tindakan Pengarah
		    			 * -1 untuk status - Permohonan Online (Pengesahan)
		    			 * 1  untuk status - Penerimaan Permohonan
		    			*/
		    			if(idJawatan.equals("20")||idJawatan.equals("24")){
		    				langkah = "-3";
		    				context.put("statusInfo", "MAKLUMAT TELAH DIHANTAR UNTUK SEMAKAN");
		    			}else if (idJawatan.equals("9")){
		    				langkah = "-2";				
		    				context.put("statusInfo", "MAKLUMAT TELAH DIHANTAR UNTUK KELULUSAN");
		    			}else if (idJawatan.equals("4")){
		    				langkah = "-1";
		    				context.put("statusInfo", "MAKLUMAT PERMOHONAN TELAH DIHANTAR");
		    			}
		    			
		    			Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
		    			rsusf.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
		    			rsusf.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
		    			rsusf.setIdSuburusanstatusfail(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
		    			rsusf.setUrl("-");
		    			simpanPengesahan(rsusf,langkah);
		    			
		    			HtpPermohonan htpPermohonanNew = new HtpPermohonan();
		    			Permohonan permohonanNew = new Permohonan();
		    			permohonanNew.setIdMasuk(Long.parseLong(userID));
		    			htpPermohonanNew.setPermohonan(permohonanNew);			
		    			getIHTPPermohonan().kemaskiniPermohonanTarikh(htpPermohonanNew, String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()), String.valueOf(htpPermohonan.getIdHtpPermohonan()));
		    			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
		    					,htpPermohonan.getPermohonan().getIdPermohonan()
		    					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),langkah)){
		    				semakMode = "xupdate";			
		    			}else{
		    				semakMode = "update";
		    			}
		    			context.put("semakMode", semakMode);
		    			context.put("selectedTab", 3);
		    			
		    			
		    			String nama_projek = null;
						String tarikh_permohonan = null;
						//String id_kementerian = null;
						//String idJawatan = null;
						//String id_user = null;
						String purpose = null;
//						sendEmail(nama_projek, tarikh_permohonan, id_kementerian, idJawatan, idUser, purpose);
						
						if(idJawatan.equals("20")||idJawatan.equals("24")){
							sendEmailTindakanPermohonanOnline(id_kementerian, idJawatan, idUser);
							//System.out.println("JAWATAN 20 dan 24");
						}else if (idJawatan.equals("9")){
							sendEmailTindakanPermohonanOnline(id_kementerian, idJawatan, idUser);
							//System.out.println("JAWATAN 9");
						}else if (idJawatan.equals("4")){
							sendEmailTindakanPermohonanOnline(id_kementerian, idJawatan, idUser);
							//System.out.println("JAWATAN 4");
						}
		    			
			    	} else {
	    				//SENARAI 
	    				myLog.debug(" ** SENARAI **");
		    			//komen pada 25/06/2010 oleh mrosli
		    			Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
		    			
		    			//untuk ppt
		    			//String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
		    			context.put("id_jenistanah", id_jenistanah);
			    		
		    			this.context.put("bilBorangK", MaklumatAsasTanahInfo.size());
			    		this.context.put("MAT", MaklumatAsasTanahInfo);
	 	    			this.context.put("TerimaPohonInfo", TerimaPohonInfo);
	 	    			
	 	    			/*----- addby zulfazdli untuk dapatkan status semasa dalam table tblrujsuburusanstatus -----*/
	 	    			getPermohonanInfo();
	 	    			myLog.info("getPermohonanInfo ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
	 	    			String semakMode="";
		 	   			String statusSemasa="-4";
		 	   			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
		 	   					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-1")){
		 	   				semakMode = "xupdate";			
		 	   			}else{
		 	   				semakMode = "update";
		 	   				if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
		 	   						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-4")){
		 	   					statusSemasa = "-4";
		 	   				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
		 	   						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-3")){
		 	   					statusSemasa = "-3";
		 	   				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
		 	   							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-2")){
		 	   					statusSemasa = "-2";	 	   					
		 	   				}
		 	   			}
		 	   			//myLog.info("semakMode="+semakMode);
		 	   			context.put("semakMode", semakMode);
		 	   			myLog.info("statusSemasa === " + statusSemasa);
		 	   			context.put("statusSemasa", statusSemasa);
		 	   			//context.put("info", "MAKLUMAT TELAH DIHANTAR UNTUK SEMAKAN");
		 	   			/*----- end addby zul -----*/
	 	    			
	    			}
		    		
		    		//ASSIGN VALUES
	    			//DISABLE OPTION UTK PILIH NEGERI
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

		    	} else if ("BorangK".equals(mode)) {
		    		context.remove("listBorangK");
		    		//list infoborangk
		    		listBorangK = getKIntergration().getHTPBorangKList(idpermohonan);
		     		context.put("listBorangK", listBorangK);
		     		context.put("saiz_borangk", listBorangK.size());   	
		     		
		    	} else if ("Pembayaran".equals(mode)) {
		    		System.out.println("Pembayaran");
		    		selectedTab = "4"; //TAB KEPUTUSAN/PEMBAYARAN
		    		myLog.debug(" ** Pembayaran **");
		    		myLog.debug(" ** "+button+" **");
		    		this.context.put("visible","1");
					selectedTab3 = getParam("tabId3");
					myLog.debug("selectedTab3:"+selectedTab3);
					
					if ("1".equals(selectedTab3)) {
					//if ("1".equals(tabmode)) { //TAB KEPUTUSAN 
						myLog.debug("TAB KEPUTUSAN");
						if("TambahKeputusan".equals(button)){
							KeputusanPermohonan("insert");
							initKeputusanPermohonan("");
						}else if ("KemaskiniKeputusan".equals(button)){
							KeputusanPermohonan("update");
							initKeputusanPermohonan("readonly");
							this.context.put("visible","0");
						}else if ("doViewForKemaskiniKeputusan".equals(button)){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
		    			}
						else {
							initKeputusanPermohonan("");
						}
						
					} else { //TAB BUKTI PEMBAYARAN 
						if("TambahPembayaran".equals(button)){
							myLog.debug("Simpan Pembayaran");
		    				Pembayaran("insert");
		    				initMaklumatPembayaran("readonly");
		    			}else if ("KemaskiniPembayaran".equals(button)){
		    				Pembayaran("update");
		    				initMaklumatPembayaran("readonly");
		    				this.context.put("visible","0");
		    			}else if ("doViewForKemaskiniBuktiBayaran".equals(button)){
		    				readOnly = "";
		    				disabled = "";
		    				style = "";
		    			}
		    			else {
		    				//DISPLAY
		    				initMaklumatPembayaran("");
		    			}
					}
					
					this.context.put("selectedTab3", selectedTab3);
						
				/* START
				 * FOR TAB MAKLUMAT 
				 * 1.NOTIS 5A - Negeri Semenanjung
				 * 2.SURAT TAWARAN KELULUSAN - Negeri Sabah kod Negeri 12
				 * 3.MAKLUMAT BAYARAN L & S 80 - Negeri SERAWAK kod negeri 13	
				 * ADDBY zulfazdliabuas@gmail.com
				 */
		    	} else if ("Notis5A".equals(mode)) { //NOTIS 5 A
		    		
		    		idNotis = getParam("idNotis");
		    		this.context.put("idNotis", idNotis);
		    		TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
		    		myLog.debug("idNotis:"+idNotis);
		    		myLog.debug("idpermohonan:"+idpermohonan);
		    		this.context.put("idpermohonan", idpermohonan);
		    		
		    		if("TambahNotis".equals(button)){
		    			//View Form Untuk Tambah Notis 5A
		    		} else if("SimpanNotis".equals(button)){ 
		    			Notis5A("insert",null);
		    			senaraiNotis5A();
		    		} else if ("ViewNotis".equals(button)) {
		    			viewNotis5A(idNotis);
		    		} else if ("KemaskiniNotis".equals(button)) {
		    			tabmode="2";
		    			Notis5A("update",idNotis);
		    			readOnly = "readonly";
		    			disabled = "disabled";
		    			style = readOnly + " class="+disabled+" ";
		    			//senaraiNotis5A();
		    		} else if ("BuktiBayaranNotis".equals(button)) {
		    			SimpanBuktiBayaranNotis();
		    			viewBuktiBayaranNotis();
		    		}
		    		else {
		    			//SENARAI NOTIS
		    			senaraiNotis5A();
		    		}
		    	}
//		    	/* ***END FOR TAB MAKLUMAT*** */
		    	
//		    /* *** addby zulfazdliabuas@gmail.com *** */
		    	
		    	
		    	
		   ///
		    //Simpan Bukti Pembayaran-indexOnline.jsp
			} else if ("simpanBuktiPembayaran".equals(submit)) {
				Hashtable TerimaPohonInfo = null;
				System.out.println("-------Read simpanBuktiPembayaran-------");
				TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	    		myLog.debug("idpermohonan:"+idpermohonan);
	    		this.context.put("idpermohonan", idpermohonan);
				
				String noBaucer = getParam("txtNoBaucer");
				String noResit = getParam("txtNoResit");
				String tarikhBaucer = getParam("txdTarikhBaucer");
				String tarikhResit = getParam("txdTarikhResit");
				String jumlahBaucer = getParam("txtJumlahBaucer");
		
				saveData(noBaucer,noResit,tarikhBaucer,tarikhResit,jumlahBaucer);
				template_name = PATH + "indexOnline.jsp";
				
				
			} else if ("uploadDoc".equals(submit)) {
				myLog.debug("uploadDoc=" + submit);

				String id_permohonan = getParam("id_permohonan");
				String keterangan = getParam("txtKeterangan");
				String namaDokumen = getParam("txtNamaDokumen");
				String xxxxx = getParam("txtNamaDokumen2");

				// myLog.debug("xxxxx="+xxxxx);
				myLog.debug("session=" + session);

				uploadFiles(id_permohonan, keterangan, namaDokumen, session);
				template_name = PATH + "indexOnline.jsp";

			} else if ("pohonfailbaru".equals(submit)) {
				
				myLog.info("SIMPAN PERMOHONAN BARU DARIPADA USER ONLINE");
				template_name = PATH +"frmMaklumatPermohonanForm.jsp";
		    	String hittButton = getParam("hittButton");
				myLog.info("submit ===== " + submit + " ::::: " + " hittButton ===== " + hittButton);
				String pageMode = "";
				context.remove("noPermohonanOnline");
				
				if (hittButton.equals("Simpan")) {
	   				
					template_name = PATH + "frmMaklumatPermohonanView.jsp";
					setPaging(false,true,true,false,false);
		    		
					String idFailBaru = doSimpanMaklumatPermohonanOnline();
		    		AuditTrail.logActivity("1", getParam("socSeksyen"), this, session, "INS", "FAIL PERMOHONAN ["+fData.strNoFail+"] DITAMBAH ");
		    		//x perlu ada integrasi GIS untuk online 
	    			//getIntegrasi().simpan(fData.strNoFail, (idurusan.equals("1")?2:3));

		    		hashTerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFailBaru);
		    		idfail = idFailBaru;
					
		    		idpermohonan = String.valueOf(hashTerimaPohonInfo.get("lblIdPermohonan"));
		   			setMaklumatPermohonan(hashTerimaPohonInfo);		
					/*tanahBean = new HTPPermohonanTanahBean();
		   			tanahBean.setMaklumatPermohonan(context, hashTerimaPohonInfo, DISABILITY);*/
					
		   			context.put("disabled", "disabled");	
					readOnly = "readonly";
		  	    	context.put("socDaerah", socDaerah);
	    	    	context.put("socAgensi", socAgensi);
	    	    	context.put("socjenisFail", socjenisFail);
					//context.put("pageMode", pageMode);	
					context.put("readOnly", readOnly);
					//context.put("disability", disability);
					context.put("showButton", "show");
					
				
   				}else{				
					this.context.put("readOnly", "");
					this.context.put("disabled", "");	
			    	this.context.put("idpermohonan","");
			    	this.context.put("idfail","");
			    	doListing(session,action,mode,senaraiTerimaPohon);
					viewPohonFailBaru(mode);
				
   				}
	    		context.put("idNegeriNotis",idnegeri);	
	    		context.put("pageMode", pageMode);
	    		
	    		//btnDaftarBaru();
	    	}
			
			else if("kosong".equals(submit)){
				
				
				flagAdvSearch = "";
				if(flagAdvSearch.equals("Y")){
					senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
							id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan,idStatus, noRujukanOnline); //--- addbyzul for carian by idStatus dan noRujukanOnline --
					doListing(session,action,mode,senaraiTerimaPohon);
		    	}else{
		    		myLog.debug("default page ::"+template_name);
		    		//senaraiTerimaPohon = fData.TerimaPohongetList(idUser,null,null,null,null,null,null,null,null);
		    		senaraiTerimaPohon = fData.TerimaPohongetListOnlineSHTP(null,null,null,id_kementerian,null,null,null,null,null);
		    		doListing(session,action,mode,senaraiTerimaPohon);
		    		if(mode.equals("changeNegeri")){
				    	flagAdvSearch = "Y";
		    			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
								id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan,idStatus, noRujukanOnline); //--- addbyzul for carian by idStatus dan noRujukanOnline --
						doListing(session,action,mode,senaraiTerimaPohon);
		    		}
		    	
		    	}
				myLog.debug("kosong ::");
				
				socNegeri = HTML.SelectNegeri("socNegeri",null,"onChange=doChangeNegeriX();");
				socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",null,style, "onChange=\"doChanges2('"+tabmode+"')\"");
	    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2",style);
				socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,null,  " style=\"width:400\"");
				socUrusan = UtilHTML.SelectUrusan("socUrusan",null,"onChange=\"doChanges()\" ");//disabled class=disabled
				
				this.context.put("socNegeri", socNegeri);
				this.context.put("socDaerah", socDaerah);
				this.context.put("socMukim", socMukim);
				this.context.put("socAgensi", socAgensi);
				this.context.put("txtNoFail", "");
				this.context.put("txtTajuk", "");
				this.context.put("socUrusan", socUrusan);
				
				//return template_name = "app/htp/permohonan/online/indexOnline.jsp";
			}
			
			else {
	    		
	    		/* ****START ADDBY ZULFAZDLI***** */
	    		/*--- addby zulfazdli untuk dapatkan senarai status mengikut IDSUBURUSAN ---*/
	    		idStatus = getParam("listStatus") == "" ? "0" : getParam("listStatus");
				//myLog.info("idStatus ==== " + idStatus);
				
				/*String langkah = "-4,-3,-2,-1";
				//listStatus = getIUtil().SelectStatusByLangkah(null,null,null,langkah);
				listStatus = getIUtil().SelectStatusByLangkah("socStatus",Long.parseLong(idStatus),"",langkah);*/
				listStatus = getIUtil().SelectStatusPermohonan("listStatus",Long.parseLong(idStatus),"",IDSUBURUSANS);
				//myLog.info("listStatus ==== " + listStatus);
				context.put("listStatus", listStatus);
				/*--- end addby zulfazdli ---*/
				
				/*----- start addby zulfazdli untuk dapatkan status semasa fail dalam table tblrujsuburusanstatus -----*/
    			getPermohonanInfo();
    			myLog.info("getPermohonanInfo ::id_permohonan="+htpPermohonan.getPermohonan().getIdPermohonan());	
    			String semakMode="";
 	   			String statusSemasa="-4";
 	   			if(getIOnline().isHantar(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
 	   					,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-1")){
 	   				semakMode = "xupdate";			
 	   			}else{
 	   				semakMode = "update";
 	   				if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
 	   						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-4")){
 	   					statusSemasa = "-4";
 	   				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
 	   						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-3")){
 	   					statusSemasa = "-3";
 	   				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),htpPermohonan.getPermohonan().getIdPermohonan()
 	   							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"-2")){
 	   					statusSemasa = "-2";	 	   					
 	   				}
 	   			}
 	   			//myLog.info("semakMode="+semakMode);
 	   			context.put("semakMode", semakMode);
 	   			myLog.info("statusSemasa ===== " + statusSemasa);
 	   			context.put("statusSemasa", statusSemasa);
 	   			//context.put("info", "MAKLUMAT TELAH DIHANTAR UNTUK SEMAKAN");
 	   			/*----- end addby zulfazdli -----*/
 	   			/* ****ADDBY ZULFAZDLI***** */
 	   			
		    	//FIRST PAGE - SENARAI FAIL PERMOHONAN		    	
		    	if(flagAdvSearch.equals("Y")){
					senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
							id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan,idStatus,noRujukanOnline); //--- addbyzul for carian by idStatus dan noRujukanOnline --
					doListing(session,action,mode,senaraiTerimaPohon);
		    	}else{
		    		myLog.debug("default page ::"+template_name);
		    		//senaraiTerimaPohon = fData.TerimaPohongetList(idUser,null,null,null,null,null,null,null,null);
		    		//senaraiTerimaPohon = fData.TerimaPohongetListOnlineSHTP(idUser,null,null,id_kementerian,null,null,null,null,null);
		    		senaraiTerimaPohon = fData.TerimaPohongetListOnlineSHTP(null,null,null,id_kementerian,null,null,null,null,null);
		    		doListing(session,action,mode,senaraiTerimaPohon);
		    		if(mode.equals("changeNegeri")){
				    	flagAdvSearch = "Y";
		    			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
								id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan,idStatus,noRujukanOnline); //--- addbyzul for carian by idStatus dan noRujukanOnline --
						doListing(session,action,mode,senaraiTerimaPohon);
		    		}
		    	
		    	}
		    }
			
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
			myLog.info("selectedtab:selectedTab="+selectedTab+",selectedTab2="+selectedTab2+",selectedTab3="+selectedTab3);
			
			this.context.put("tabmode", tabmode);
			this.context.put("button", button);
			this.context.put("mode", mode);
			this.context.put("submit", submit);
	    	this.context.put("style",style);
	    	this.context.put("idpermohonan",idpermohonan);
	    	this.context.put("idhakmilikurusan",idhakmilikurusan);
	    	this.context.put("idfail",idfail);
		    // 18/08/2010
	    	this.context.put("flagAdvSearch",flagAdvSearch);	
	    	
	    	myLog.info("selectedTab == " + selectedTab + "<br/>" + "selectedTab2 == " + selectedTab2 + "<br/>" + "selectedTab3 == " + selectedTab3 + "<br/>" + "tabmode == " + tabmode + "<br />" + "button === " + button + "<br/>" + "mode == " + mode + "<br />" + "tabmode === " + tabmode + "<br/>" + "submit === " + submit);
			
		}catch(Exception e){
			//e.printStackTrace();
			throw new Exception(getHTP().getErrorHTML(e.toString()));
		
		}
		return template_name;
		
	}
	
	public void initContext() {
		this.context.put("socMukim","");
	}
	
	public void emptyContext() {
		myLog.debug(" ******** emptyContext ********888");
		this.context.put("txtNoFail","");
		this.context.put("txtTajukFail","");
	}
	
	public void doListing(HttpSession session,String action,String mode,Vector v) throws Exception {
		if (mode.indexOf("change") != -1) {
	    	myLog.debug("changes detected...");
	    	initContext();
	    	
	    }else if ("cancel".equals(mode)) {
    		myLog.debug("cancel mode");
    		//emptyContext();
    		nofail = "";
    		txtTajuk = "";
    	    iddaerah = "-1";
    	    idmukim = "-1";
    	    id_agensi = "-1";
    
		} else {
    		myLog.debug(" do listing :"+nofail);

    	}
        this.context.put("txtNoFail",nofail);
	    this.context.put("txtTajuk",txtTajuk);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), "disabled class=disabled","onChange=\"doChangeKementerianX()\" style=\"width:400\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), ""," style=\"width:400\"");
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"onChange=doChangeNegeriX();");
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
	
	public void setMaklumatPermohonan(Hashtable TerimaPohonInfo) throws Exception {
		try {
		String lblNegeri = "";
		String lblKementerian = "";
		String lblidAgensi = "";
		String lblidUrusan = "";
		String lblidSubUrusan = "";
		String lblidKodJTanah = "";
		String lblTanahKeselamatan = "";
		
		idurusan = (String)TerimaPohonInfo.get("lblidUrusan");
		idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
		
		this.context.put("noFail", TerimaPohonInfo.get("lblNoFail"));
		this.context.put("diDaftarOleh", TerimaPohonInfo.get("diDaftarOleh"));
		this.context.put("diDaftarPada", TerimaPohonInfo.get("diDaftarPada"));
		
//		socNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idnegeri),"disabled class=disabled");
//		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"lblDaerah",Utils.parseLong((String)TerimaPohonInfo.get("lblIdDaerah")),"disabled class=disabled");
//		socKementerian =HTML.SelectKementerian("lblKementerian", Utils.parseLong((String)TerimaPohonInfo.get("lblidKementerian")), "disabled class=disabled");
//		socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)TerimaPohonInfo.get("lblidAgensi")),"disabled class=disabled");
//		socUrusan = UtilHTML.SelectUrusan("lblidUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidUrusan")),"disabled class=disabled");		
//		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"lblidSubUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidSubUrusan")),"disabled class=disabled");
//		socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils.parseLong((String)TerimaPohonInfo.get("lblidKodJTanah")), "disabled class=disabled");
//		socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)TerimaPohonInfo.get("lblTanahKeselamatan")),"disabled class=disabled");
		
		socNegeri = (String)TerimaPohonInfo.get("lblNamaNegeri");	
		socDaerah = (String)TerimaPohonInfo.get("lblNamaDaerah");	
		socKementerian = (String)TerimaPohonInfo.get("lblKementerian");	
		socAgensi = (String)TerimaPohonInfo.get("lblAgensi");	
		socUrusan = (String)TerimaPohonInfo.get("lblUrusan");	
		socSubUrusan = (String)TerimaPohonInfo.get("lblNamaSubUrusan");	
		socStatustanah = (String)TerimaPohonInfo.get("lblKodJTanah")+" - "+(String)TerimaPohonInfo.get("lblKeterangan");	
		socjenisFail = (String)TerimaPohonInfo.get("lblTarafKeselamatan");	

		
		context.put("socKementerian", socKementerian);
		context.put("socStatustanah", socStatustanah);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socjenisFail", socjenisFail);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri",socNegeri);
		
		this.context.put("txtTajuk",TerimaPohonInfo.get("lblTujuan"));
		this.context.put("txdTarikhSuratKJP",TerimaPohonInfo.get("lblTrkhSurat"));
		this.context.put("txtnoFailKJP",TerimaPohonInfo.get("lblNoRujKJP"));
		this.context.put("txtnoFailUPT", TerimaPohonInfo.get("lblNoRujLain"));
		this.context.put("idPermohonan",TerimaPohonInfo.get("lblIdPermohonan"));
		context.put("noPermohonanOnline", TerimaPohonInfo.get("noP"));
		
		} catch (Exception e) {
			throw new Exception("Ralat:"+e.getCause());
		}
	}
	
	public void setMaklumatPermohonanOnline(Hashtable TerimaPohonInfo) throws Exception {
		try {
			String lblNegeri = "";
			String lblKementerian = "";
			String lblidAgensi = "";
			String lblidUrusan = "";
			String lblidSubUrusan = "";
			String lblidKodJTanah = "";
			String lblTanahKeselamatan = "";
		
			idurusan = (String)TerimaPohonInfo.get("lblidUrusan");
			idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
		
			this.context.put("noFail", TerimaPohonInfo.get("lblNoFail"));
			this.context.put("noP", TerimaPohonInfo.get("noP"));
			this.context.put("diDaftarOleh", TerimaPohonInfo.get("diDaftarOleh"));
			this.context.put("diDaftarPada", TerimaPohonInfo.get("diDaftarPada"));
		
			socNegeri = (String)TerimaPohonInfo.get("lblNamaNegeri");	
			socDaerah = (String)TerimaPohonInfo.get("lblNamaDaerah");	
			socKementerian = (String)TerimaPohonInfo.get("lblKementerian");	
			socAgensi = (String)TerimaPohonInfo.get("lblAgensi");	
			socUrusan = (String)TerimaPohonInfo.get("lblUrusan");	
			socSubUrusan = (String)TerimaPohonInfo.get("lblNamaSubUrusan");	
			socStatustanah = (String)TerimaPohonInfo.get("lblKodJTanah")+" - "+(String)TerimaPohonInfo.get("lblKeterangan");	
			socjenisFail = (String)TerimaPohonInfo.get("lblTarafKeselamatan");	
		
			context.put("socKementerian", socKementerian);
			context.put("socStatustanah", socStatustanah);
			context.put("socUrusan", socUrusan);
			context.put("socAgensi", socAgensi);
			context.put("socNegeri", socNegeri);
			context.put("socDaerah", socDaerah);
			context.put("socjenisFail", socjenisFail);
			context.put("socSubUrusan", socSubUrusan);
			context.put("socNegeri",socNegeri);
			
			this.context.put("txtTajuk",TerimaPohonInfo.get("lblTujuan"));
			this.context.put("txdTarikhSuratKJP",TerimaPohonInfo.get("lblTrkhSurat"));
			this.context.put("txtnoFailKJP",TerimaPohonInfo.get("lblNoRujKJP"));
			this.context.put("txtnoFailUPT", TerimaPohonInfo.get("lblNoRujLain"));
			this.context.put("idPermohonan",TerimaPohonInfo.get("lblIdPermohonan"));
			
		} catch (Exception e) {
			throw new Exception("Ralat:"+e.getCause());
		}
	}
	

	public void viewPohonFailBaru(String mode) throws Exception {
		System.out.println("Function viewPohonFailBaru ===== " + mode);
		//String fail1 = getParam("cmdSimpan");
		//utk simpan
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChanges()\" ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), "disabled=\"disabled\"","onChange=\"doChanges()\"  style=\"width:400\"");
 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), " style=\"width:400\"");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChanges()\" ");//disabled class=disabled
 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(idsuburusan), "");
 		
		context.put("socKementerian", socKementerian);
		context.put("socUrusan", socUrusan);
		context.put("socAgensi", socAgensi);
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socSubUrusan", socSubUrusan);
		context.put("socNegeri",socNegeri);
		
		if (mode.indexOf("doChange") != -1) {
			myLog.debug(" ** CHANGES DETECTED !!");
			//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah", Long.parseLong(getParam("socStatusTanah")==""?"0":getParam("socStatusTanah")), "",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk",getParam("txtTajuk"));
			context.put("noFail","");
			context.put("noP","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT","");
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
			context.put("txtTarikhPermohonan",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
	    } else {
	 		//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah", null, "",JENISTANAH);

			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk","");
			context.put("noFail","");
			context.put("noP","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT","");
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
			context.put("txtTarikhPermohonan",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
	    }

	}
	
	public String doSimpanMaklumatPermohonan() throws Exception {		
		Hashtable<String, String> hashData = null;
		hashData = new Hashtable<String, String>();
		hashData.put("id_Fail", idfail);
		hashData.put("id_Tarafkeselamatan", "1");
		hashData.put("id_Seksyen", getParam("socSeksyen"));
		hashData.put("id_Urusan", idurusan);
		hashData.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		hashData.put("tajuk_Fail", getParam("txtTajuk"));
		hashData.put("no_Fail", nofail);
		hashData.put("id_Negeri", idnegeri);
		hashData.put("id_Daerah", iddaerah);
		hashData.put("id_Kementerian",id_kementerian);
		hashData.put("id_Agensi",id_agensi);
		hashData.put("flag_Fail","1");
		hashData.put("id_Status", "8");
		hashData.put("id_Masuk", idUser);
		hashData.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		hashData.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		hashData.put("noFailUPT", getParam("txtnoFailUPT"));  
		hashData.put("noFailKJP", getParam("txtnoFailKJP"));  
		hashData.put("StatusTanah", getParam("socStatustanah"));  
//		return fData.simpanPermohonan(h,idUser);
		return fData.simpanPermohonanOnline(hashData,idUser);
			
	}
	
	public void doSimpanMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		data.put("Luas", getParam("Luas"));
		data.put("LuasH", getParam("LuasH") == null ? "" : getParam("LuasH"));
		data.put("Lokasi", getParam("Lokasi"));
		//data.put("jenistanah", session.getAttribute("StatusTanah"));
		FrmTerimaPohonData.simpanMaklumatAsasTanah(data);	
	}
	
	/*start addbyzul*/
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
	
	//CHARTING
	private void ChartingSimpanKemaskini(String tindakan) throws Exception {
		tanahBean.chartingSimpanKemaskini(userId, getParam("idpermohonan"), getParam("idhakmilikurusan"), getParam("RadioGroup1")
			, getParam("JumBayaranPelan"), getParam("ulasan"), getParam("keteranganImej")
			, String.valueOf(this.context.get("noFail")), tindakan);
		
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
		//Penambahbaikan phase3. Syaz. 24112014
		ku.setFlagNotifikasi(getParam("flagNotifikasi"));
		
		if (mode.equals("update")) {
			getIPermohonan().keputusanPermohonanTanah(ku);
		}else{
			getIPermohonan().keputusanPermohonanTanah(ku);
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
				String strDate = getParam("txtarikhkeputusan")
						.equals("")?"01/01/1900":getParam("txtarikhkeputusan");
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
				subUrusanStatusFailN.setTarikhMasuk(date);

				return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txtarikhkeputusan")));
					
			} catch (Exception e) {
				throw new Exception("Ralat "+this.className+", kemaskiniSimpanStatusSelesai:"+getIHTP().getErrorHTML(e.getMessage()));			
			}
		
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
	private void setSusulan(String idPermohonan, String idSusulan) throws Exception{
		hashData = new Hashtable<String, String>();
		hashData = getISusulan().getMaklumat(idPermohonan, idSusulan);
		this.context.put("idSusulanStatus", hashData.get("idSusulanStatus"));
		this.context.put("idSusulan", idSusulan);
		this.context.put("txtCatatan", hashData.get("keterangan"));
		this.context.put("tarikhHantar",hashData.get("tarikh"));
		
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
	/*----------------------- end addbyzul --------------------------*/
	
	
	
	
	
	
	public void doKemaskiniMaklumatAsasTanah() throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("socNegeri", getParam("socnegeri2"));
		data.put("socDaerah", getParam("socdaerah2"));
		data.put("socMukim", getParam("socMukim2"));
		data.put("socLot", getParam("socLot"));
		data.put("noLot", getParam("noLot"));
		data.put("txtNoLot", getParam("txtNoLot"));
		data.put("noSyit", getParam("noSyit"));
		data.put("noPelan", getParam("noPelan"));
		data.put("socLuas", getParam("socLuas"));
		data.put("Luas", getParam("Luas"));
		data.put("LuasH", getParam("LuasH") == null ? "" : getParam("LuasH"));
		data.put("Lokasi", getParam("Lokasi"));
		//data.put("jenistanah", session.getAttribute("StatusTanah"));
		FrmTerimaPohonData.updateMAT(data);	
	}
	
	///LOKASI TANAH
	
	private void initMaklumatLokasi(String readmode) throws Exception {
			Vector LokasiInfo = fData.getLokasiTanahInfo(idhakmilikurusan);
			Hashtable detail = null;
			if (LokasiInfo.size() > 0) {
				detail = (Hashtable)LokasiInfo.get(0);
				tabmode = "4";//Update
			} else {
				tabmode = "3";//Insert
			}
			this.context.put("detail",detail);
			
			if ("readonly".equals(readmode)) {
				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class="+disabled+" ";
			} else {
				readOnly = "";
				disabled = "";
				style = "";
			}
	}
	
	private void initMaklumatCharting(String readmode) throws Exception {
		Vector LokasiInfo = fData.getPelanLakaranInfo(idhakmilikurusan);
		Hashtable detail = null;
		if (LokasiInfo.size() > 0) {
			detail = (Hashtable)LokasiInfo.get(0);
			tabmode = "4";//Update
		} else {
			tabmode = "3";//Insert
		}
		this.context.put("detail",detail);
		
		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class="+disabled+" ";
		} else {
			readOnly = "";
			disabled = "";
			style = "";
		}
}
	
	
	private void SimpanLokasiTanah()throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		
		data.put("txtbandar", getParam("txtJDbandar"));
		data.put("txtbandarperihal", getParam("txtJDbandarPerihal"));
		data.put("txtLebuhRaya", getParam("txtJDLebuhRaya"));
		data.put("txtLebuhRayaperihal", getParam("txtJDLebuhRayaPerihal"));
		data.put("txtJkeretapi", getParam("txtJDJalanKeretapi"));
		data.put("txtJkeretapiperihal", getParam("txtJDJalanKeretapiPerihal"));
		data.put("txtSgPntai", getParam("txtJDSungaiPantai"));
		
		data.put("txtSgPntaiperihal", getParam("txtJDSungaiPantaiPerihal"));
		data.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		data.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		data.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		data.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		data.put("txtSempadanKeteranganLain", getParam("txtKeteranganLain"));
		
		data.put("txtPerihalLokasi", getParam("PerihalLokasi"));
		data.put("txtZone", getParam("zone"));
		
		FrmTerimaPohonData.simpanLokasiTanah(data);
	}
	
	private void doKemaskiniLokasiTanah()throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		
		data.put("txtbandar", getParam("txtJDbandar"));
		data.put("txtbandarperihal", getParam("txtJDbandarPerihal"));
		data.put("txtLebuhRaya", getParam("txtJDLebuhRaya"));
		data.put("txtLebuhRayaperihal", getParam("txtJDLebuhRayaPerihal"));
		data.put("txtJkeretapi", getParam("txtJDJalanKeretapi"));
		data.put("txtJkeretapiperihal", getParam("txtJDJalanKeretapiPerihal"));
		data.put("txtSgPntai", getParam("txtJDSungaiPantai"));
		
		data.put("txtSgPntaiperihal", getParam("txtJDSungaiPantaiPerihal"));
		data.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		data.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		data.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		data.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		data.put("txtSempadanKeteranganLain", getParam("txtKeteranganLain"));
		
		data.put("txtPerihalLokasi", getParam("PerihalLokasi"));
		data.put("txtZone", getParam("zone"));
		
		FrmTerimaPohonData.kemaskiniLokasiTanah(data);
	}
	
	///
	//CHARTING
	private void SimpanCharting()throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("Flagcharting", getParam("RadioGroup1"));
		data.put("JumBayaranPelan", getParam("JumBayaranPelan"));
		data.put("ulasan", getParam("ulasan"));
		data.put("keteranganImej", getParam("keteranganImej"));
		data.put("nofail",this.context.get("noFail"));
		FrmTerimaPohonData.LakaranPelan(data,"insert");
	}
	
	private void KemaskiniCharting()throws Exception {
		Hashtable data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		myLog.debug("*** RadioGroup1:"+getParam("RadioGroup1"));
		data.put("Flagcharting", getParam("RadioGroup1"));
		data.put("JumBayaranPelan", getParam("JumBayaranPelan"));
		data.put("ulasan", getParam("ulasan"));
		data.put("keteranganImej", getParam("keteranganImej"));
		data.put("nofail",this.context.get("noFail"));
		FrmTerimaPohonData.LakaranPelan(data,"update");
	}
	
	//PEMBAYARAN
	
	private void initMaklumatPembayaran(String readmode) throws Exception {		
		Vector PembayaranInfo = fData.getPembayaranInfo(idpermohonan);
		Hashtable pembayaran = null;
		String idcarabayar = "-1";
		String caraBayaran = "";
		if (PembayaranInfo.size() > 0) {
			pembayaran = (Hashtable) PembayaranInfo.get(0);
			tabmode = "4";//Update
			idcarabayar = (String)pembayaran.get("idcarabayar");
		} else {
			tabmode = "3";//Insert
		}
		myLog.debug("*** "+pembayaran);
		this.context.put("pembayaran", pembayaran);
		//INIT
		
		if ("readonly".equals(readmode)) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class="+disabled+" ";
		} else {
			readOnly = "";
			disabled = "";
			style = "";
		}
		
		//caraBayaran = HTML.selectCaraBayar("socBayaran",Utils.parseLong(idcarabayar),style + " width=40px ", null);
		caraBayaran = HTML.selectCaraBayar("socBayaran",Utils.parseLong(idcarabayar),style + " ", null);
		this.context.put("socBayaran", caraBayaran);

	}
	
	private void Pembayaran(String mode)throws Exception {
		
		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		//data.put("idhakmilikurusan", getParam("idhakmilikurusan"));
		data.put("TarikhSuratKePTD", getParam("txtTarikhSuratKePTD"));
		data.put("NoResit", getParam("txtNoResit"));
		data.put("caraBayaran", getParam("socBayaran"));
		data.put("tempatBayaran", getParam("txttempatBayaran"));
		data.put("JumBayaran", getParam("txtBayaranProses"));
		data.put("TarikhResit", getParam("txtTarikhResit"));
		data.put("NoBaucerCekDraft", getParam("txtNoBaucerCekDraft"));
		data.put("TarikhBaucerCek", getParam("txtTarikhBaucerCek"));
		FrmTerimaPohonData.Pembayaran(data,mode);	
		
	}
	
	private void initKeputusanPermohonan(String readmode) throws Exception {
			
			Vector KeputusanInfo = fData.getKeputusanInfo(idpermohonan);
			Hashtable keputusan = null;
			String idcarabayar = "-1";
			String caraBayaran = "";
			if (KeputusanInfo.size() > 0) {
				keputusan = (Hashtable) KeputusanInfo.get(0);
				tabmode = "4";//Update
			} else {
				tabmode = "3";//Insert
			}
			myLog.debug("*** "+keputusan);
			this.context.put("keputusan", keputusan);
			//INIT
			
			if ("readonly".equals(readmode)) {
				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class="+disabled+" ";
			} else {
				readOnly = "";
				disabled = "";
				style = "";
			}
			
			caraBayaran = HTML.selectCaraBayar("socBayaran",Utils.parseLong(idcarabayar),style + " width=40px ", null);
			this.context.put("socBayaran", caraBayaran);
	
	}
	
	public void KeputusanPermohonan(String mode) throws Exception {
		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser",idUser);
		data.put("idpermohonan", getParam("idpermohonan"));
		data.put("idkeputusanmohon", getParam("idkeputusanmohon"));		
		data.put("NoRujukPTD", getParam("txtNoRujukPTD"));
		data.put("TarikhKeputusan", getParam("txtTarikhKeputusan"));
		data.put("PilihKeputusan", getParam("PilihKeputusan"));
		data.put("Catatan", getParam("txtCatatan"));
		//data.put("Catatan", getParam("txtCatatan"));
		FrmTerimaPohonData.KeputusanBayaran(data,mode);
	}

	///NOTIS 5A
	
	public void senaraiNotis5A() throws Exception {
		Vector Notis5ASenarai = fData.getSenaraiNotis5A(idpermohonan);
		this.context.put("SenaraiNotis5A", Notis5ASenarai);
	}
	
	public void viewNotis5A(String idNotis) throws Exception {
		Hashtable Notis5A=fData.getDataNotis5A(idNotis);
		this.context.put("dat", Notis5A);
		this.context.put("viewNotis", 0);
		viewBuktiBayaranNotis();
	}
	
	public void Notis5A(String mode,String idNotis) throws Exception {
		
		Hashtable hNotis = new Hashtable();
		hNotis.put("idUser",idUser);
		hNotis.put("idpermohonan",Utils.isNull(getParam("idpermohonan")));
		hNotis.put("NoKPPemilikAsal",Utils.isNull(getParam("txtNoKPPemilikAsal")));
		hNotis.put("TarikhNotis5A",Utils.isNull(getParam("txtTarikhNotis5A")));
		hNotis.put("TarikhLuputPertama",Utils.isNull(getParam("txtTarikhLuputPertama")));
		hNotis.put("CukaiTahunPertama",Utils.isNull(getParam("txtCukaiTahunPertama")));
		hNotis.put("BayaranNotis",Utils.isNull(getParam("txtBayaranNotis")));
		hNotis.put("BayaranNotisF",Utils.isNull(getParam("txtBayaranNotisF")));
		hNotis.put("Premium",Utils.isNull(getParam("txtPremium")));
		hNotis.put("RayuanPremium",Utils.isNull(getParam("txtRayuanPremium")));
		hNotis.put("RayuanLain",Utils.isNull(getParam("txtRayuanLain")));
		hNotis.put("PerihalRayuan",Utils.isNull(getParam("txtPerihalRayuan")));
		hNotis.put("TarikhTerimaNotis5A",Utils.isNull(getParam("txtTarikhTerimaNotis5A")));
		hNotis.put("TarikhLuputNotisKedua",Utils.isNull(getParam("txtTarikhLuputNotisKedua")));
		hNotis.put("TarikhLuputNotisKetiga",Utils.isNull(getParam("txtTarikhLuputNotisKetiga")));
		hNotis.put("PendaftaranHakmilik",Utils.isNull(getParam("txtPendaftaranHakmilik")));
		hNotis.put("BayarUkur", Utils.isNull(getParam("txtBayarUkur")));
		hNotis.put("BayaranPerenggan",Utils.isNull(getParam("txtBayaranPerenggan")));
		hNotis.put("TandaSempadan",Utils.isNull(getParam("txtTandaSempadan")));
		hNotis.put("BayaranLain",Utils.isNull(getParam("txtBayaranLain")));
		hNotis.put("JumBayaran",Utils.isNull(getParam("txtJumBayaran")));
		//--Azam Add
		hNotis.put("BayaranLain2",Utils.isNull(getParam("txtBayaranLain2")));
		hNotis.put("BayaranLain3",Utils.isNull(getParam("txtBayaranLain3")));
		hNotis.put("BayaranFail",Utils.isNull(getParam("txtBayaranFail")));
		
		FrmTerimaPohonData.Notis5A(hNotis,mode,idNotis);
}
	
	private void SimpanBuktiBayaranNotis()throws Exception {
		
		Hashtable data = null;
		data = new Hashtable();
		data.put("idUser", idUser);
		data.put("idPermohonan", getParam("dipermohonanNotis"));
		data.put("NoBaucer", getParam("txtNoBaucer") == null ? "" : getParam("txtNoBaucer"));
		data.put("TarikhBaucer", getParam("txtTarikhBaucer") == null ? "" : getParam("txtTarikhBaucer"));
		data.put("TarikhResit", getParam("txtTarikhResit") == null ? "" : getParam("txtTarikhResit"));
		data.put("JumBaucer", getParam("txtJumBaucer") == null ? "" : getParam("txtJumBaucer"));
		data.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
		FrmTerimaPohonData.simpanBuktiNotis5A(data);		
	}
	
	private void viewBuktiBayaranNotis() throws Exception {
		Vector BuktiBayaranInfo = fData.getBuktiBayaranNotis5A(idpermohonan);
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
	
	private IBorangKIntergration getKIntergration(){
		if(kIntergration == null)
			kIntergration = new BorangKIntergrationBean();
		return kIntergration;
	}
	
	private IPenggunaKementerian getIPengguna(){
		if(iPengguna==null){
			iPengguna = new PenggunaKementerianBean();
		}
		return iPengguna;
		
	}
	
	
	private IHtp getHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}

	private void getValues(){
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtnoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSuratKJP");
		String idPermohonan = getParam("idpermohonan");
		//String noFailLain = getParam("txtnoFailUPT");
		String noRujukanUPT = getParam("txtnoFailUPT");
		String noRujukanPTG = "";
		String noRujukanPTD= "";
		String noFailLain = "";
		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
		permohonan.setTujuan(tajuk);
		htpPermohonan.setNoRujukanKJP(failKJP);
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanUPT(noRujukanUPT);
		htpPermohonan.setNoRujukanPTG(noRujukanPTG);
		htpPermohonan.setNoRujukanPTD(noRujukanPTD);		
		htpPermohonan.setNoRujukanLain(noFailLain);
		permohonan.setIdPermohonan(idPermohonan);		
		permohonan.setPfdFail(fail);
		permohonan.setTarikhTerima("");
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
		
	}
	
//	@Command("btnDaftarBaru")
//	public void btnDaftarBaru() throws Exception{
//		HttpSession session = this.request.getSession();
//		String action = getParam("action");
//		String mode = getParam("mode");
//		System.out.println("MASUK KE btnDaftarBaru ::: " + " ACTION = " + action + " ----- " + "MODE = " + mode );
//		doListing(session,action,mode,senaraiTerimaPohon);
//		
//		if(mode.equals("changeNegeri")){
//	    	flagAdvSearch = "Y";
//			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan);
//			doListing(session,action,mode,senaraiTerimaPohon);
//		}
//	}
	
	private IIntegrasi getIntegrasi(){
		if(integrasiGIS==null){
			integrasiGIS = new IntegrasiGISBean();
		}
		return integrasiGIS;
				
	}
	
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserBean();
		}
		return iUser;
			
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	
	}
	
	//----- Start addby zul 27/7/2017 -----
	public String doSimpanMaklumatPermohonanOnline() throws Exception {		
		Hashtable<String, String> hashData = null;
		hashData = new Hashtable<String, String>();
		hashData.put("id_Fail", idfail);
		hashData.put("id_Tarafkeselamatan", "1");
		hashData.put("id_Seksyen", getParam("socSeksyen"));
		hashData.put("id_Urusan", idurusan);
		hashData.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		hashData.put("tajuk_Fail", getParam("txtTajuk"));
		hashData.put("no_Fail", nofail);
		hashData.put("id_Negeri", idnegeri);
		hashData.put("id_Daerah", iddaerah);
		hashData.put("id_Kementerian",id_kementerian);
		hashData.put("id_Agensi",id_agensi);
		hashData.put("flag_Fail","1");
		hashData.put("id_Status", "8"); //138 = permohonan Online , 8 = PENDAFTARAN
		hashData.put("id_Masuk", idUser);
		hashData.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
//		hashData.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		hashData.put("TarikhPermohonan", getParam("txtTarikhPermohonan")); 
//		hashData.put("noFailUPT", getParam("txtnoFailUPT"));  
		hashData.put("noFailUPT", "");
//		hashData.put("noFailKJP", getParam("txtnoFailKJP"));
		hashData.put("noFailKJP", "");
//		hashData.put("StatusTanah", getParam("socStatustanah")); zulfazdli disable ganti code bawah Hardcode terus status tanah kepada sulit
		hashData.put("StatusTanah", "3"); //Status Tanah 3 = SULIT
//		return fData.simpanPermohonan(h,idUser);
		return fData.simpanPermohonanOnline(hashData,idUser);
	}
	
	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf,String langkah)throws Exception {
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
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userID));
			subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userID));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail,subUrusanStatusFailN);
			
		} catch (Exception e) {
			//throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			getIHTP().getErrorHTML(e.toString());
		}
	}
	
//	private void getPermohonanInfo()throws Exception{
//		
//		Hashtable<String, String> listPermohonan = null;
//		
//		String idPermohonan = getParam("txtidPermohonan");
//		myLog.debug("PRINTTTTTTT idPermohonan ===== " + idPermohonan);
//		String idHtpPermohonan = getParam("txtidHtpPermohonan");
//		myLog.debug("PRINTTTTTTT idHtpPermohonan ===== " + idHtpPermohonan);
////		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
//		listPermohonan = fData.getPermohonanInfo(idPermohonan);
//		context.put("permohonan", listPermohonan);
//	}
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idpermohonan");
		String idFail = getParam("idfail");
		//System.out.println("idPermohonan ===== " + idPermohonan);
		//System.out.println("idFail ===== " + idFail);
		//htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		htpPermohonan = getIHTPPermohonan().getPermohonanInfo(idFail, idPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	
	private IHTPPermohonan getIHTPPermohonan(){
		if(iHTPPermohonan== null)
			iHTPPermohonan = new HTPPermohonanBean();
		return iHTPPermohonan;
	}
	
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
			
	}
	
	private IUtilHTML getIUtil(){
		IUtilHTML iUtil = null;
		if(iUtil == null){
			iUtil = new UtilHTMLBean();
		}
		return iUtil;
			
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
	private IHTPBayaran getIBayaran(){
		if(iBayaran==null){
			iBayaran = new HTPBayaranPermohonanBean();
		}
		return iBayaran;
			
	}
	
	private IHTPEmel getHTPEmel(){
		if(iHTPEmel == null)
			iHTPEmel = new HTPEmelPermohonanBean();
		return iHTPEmel;
	}

	private IHTPEmel getHTPEmelSemak(){
		if(iHTPEmel == null)
			iHTPEmel = new HTPEmelSemakBean();
		return iHTPEmel;
	}
	private void uploadFiles(String id_permohonan, String keterangan,
			String namaDokumen, HttpSession session) throws FileUploadException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		
		if (isMultipart != false) {
			
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				
				
				FileItem item = (FileItem) itr.next();
				
				if ((!(item.isFormField())) && (item.getName() != null)	&& (!("".equals(item.getName())))) {
					
					saveData(item, id_permohonan, keterangan, namaDokumen, session);
				}
			}
		}		
	}
	private void saveData(String noBaucer, String noResit, String tarikhBaucer, String tarikhResit, String jumlahBaucer) throws Exception {
		Db db = null;
		String sqlInsertTblhtpbayaran = "";
		System.out.println("Dalam SaveData Bukti Pembayaran");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	long idBayaran =  DB.getNextID(db,"TBLHTPBAYARAN_SEQ");
		    	String txtNoBaucer = noBaucer;
		    	String txtNoResit = noResit;
		    	String txdTarikhBaucer = "to_date('" + tarikhBaucer + "','dd/MM/yyyy')";
		    	String txdTarikhResit = "to_date('" + tarikhResit + "','dd/MM/yyyy')";
		    	String txtJumlahBaucer = jumlahBaucer;
		    	
		    	Date now = new Date();
		    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    	String tarikhMasuk = "to_date('"+formatter.format(now)+"', 'dd/MM/yyyy')";
		    	
		    	SQLRenderer r = new SQLRenderer();
		    	
		    	r.add("ID_BAYARAN", idBayaran);
		    	r.add("ID_PERMOHONAN", idpermohonan);
		    	r.add("NO_BAUCER", txtNoBaucer);
		    	r.add("NO_RESIT", txtNoResit);
		    	r.add("TARIKH_BAUCER", r.unquote(txdTarikhBaucer));
		    	r.add("TARIKH_RESIT", r.unquote(txdTarikhResit));
		    	r.add("JUMLAH_BAUCER", txtJumlahBaucer);
		    	r.add("TARIKH_MASUK", r.unquote(tarikhMasuk));
		    	
		    	myLog.info("TarikhBauce: "+ tarikhBaucer);
		    	myLog.info("id User: "+ idUser);
		  
		    	sqlInsertTblhtpbayaran = r.getSQLInsert("TBLHTPBAYARAN");
		    	System.out.println("sqlInsertTblhtpbayaran = " + sqlInsertTblhtpbayaran);
				stmt.executeUpdate(sqlInsertTblhtpbayaran);
				
		    	con.commit();
		    	return;
		    }
		    finally {
			      if (db != null) db.close();
		    }
			}

	private void saveData(FileItem item, String id_permohonan,
			String keterangan, String namaDokumen, HttpSession session) {
		System.out.println("saveDAta ");
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		
		try {
			db = new Db();
			// TBLPPKDOKUMENSENARAIHUTANG
			long idDokumen = DB.getNextID("TBLHTPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLHTPDOKUMEN"
							+ " (ID_DOKUMEN, ID_PERMOHONAN, NAMA_DOKUMEN, CONTENT, JENIS_MIME, ID_MASUK, KETERANGAN, TARIKH_MASUK)"
							+ " VALUES(?,?,?,?,?,?,?,SYSDATE)");
			ps.setLong(1, idDokumen);
			ps.setLong(2, Long.valueOf(id_permohonan));
			ps.setString(3, namaDokumen);
			//ps.setString(4, item.getName());
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize()); //content
			ps.setString(5, item.getContentType()); //jenis mime
			ps.setString(6, userId);
			ps.setString(7, keterangan);
			System.out.println("ps : " + ps.toString());
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		context.put("id_permohonan", id_permohonan);
	}
	
/*	private Permohonan getlistPermohonan(){
		String idPermohonan = getParam("txtidPermohonan");
		myLog.debug("PRINTTTTTTT idPermohonan ===== " + idPermohonan);
		
		return permohonan;
	}*/
	
	@SuppressWarnings({ "static-access" })
	private void sendEmailTindakanPermohonanOnline(String id_kementerian, String idJawatan, String idUser) throws Exception {
	
		System.out.println("sendEmailTest idUser ===== " + idUser);

		EmailPermohonanOnline email = new EmailPermohonanOnline();
		//et.setEmail(userIdKementerian, id_jawatan_user, id_user);
		email.setEmailStatusPermohonanOnline(id_kementerian, idJawatan, idUser);
		
	}
	
	//----- End addby zul 27/7/2017 -----
}
	