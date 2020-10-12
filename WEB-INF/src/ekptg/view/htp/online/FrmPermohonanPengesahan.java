package ekptg.view.htp.online;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
import ekptg.model.entities.Tblrujsuburusanstatus;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmJRPSenaraiPermohonanData;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPEmelBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPEmel;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.utiliti.HTPEmelKelulusanBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.model.utils.lampiran.ILampiran;
import ekptg.model.utils.lampiran.LampiranBean;

public class FrmPermohonanPengesahan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8487877676979529353L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.online.FrmPermohonanPengesahan.class);
	private ekptg.model.htp.IHtp iHtp = null;
	private ekptg.model.htp.IHTPEmel iHTPEmel = null;
	private ekptg.model.htp.IHTPStatus iStatus = null;
	private final String PATHTP="app/htp/";
	private final String PATHVER = PATHTP+ResourceBundle.getBundle("file").getString("ver_htp")+"/";
	private final String PATH = PATHVER+"online/";
//	private final String PATHPERMOHONAN="app/htp/permohonan/";	
	
	private IEmel emelulus = null;
	FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view
	private String IDURUSAN = "";
	private String noFail = "";
	private String subModul ="";
	private String tajukFail = "";
	String isCarian = "tidak";
	String userId = "";
	private IPenggunaKementerian iPengguna = null;
	private IOnline iOnline = null;
//	private UserKementerian uk = null;
	String idFail = "";
	FrmSenaraiFailTerimaPohonData fData = null;
	String idNegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
 	String idUrusan = "";
 	String idsuburusan = "";
	String socKementerian = "";
	String socAgensi = "";
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String socUrusan = "";
	String socSubUrusan = "";
	String socStatusTanah = "";
	String socJenisFail = "";	
	String readability = "";
	String disability = "";
	String noLot = "";
	private HtpPermohonan htpPermohonan = null;
	private IPembelian iPembelian = null;
	Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	String idSubUrusan = "";
	String tabmode = "";
	String style = "";
	String readOnly = "";
	String disabled = "";
	String idhakmilikurusan ="";
	String idUser = ""; 
	String id_kementerian = "";
 	String id_agensi = "";
	String nofail = "";
	//pembelian
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private String readonly = " disabled class = \"disabled\"";
	private String tabId = "";
	//Pajakan
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
    Vector beanMaklumatPermohonan = null;
    ILampiran iLampiran = null;
    
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		fData = FrmSenaraiFailTerimaPohonData.getInstance();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		String portal_role = String.valueOf(session.getAttribute("myrole"));
		String portalRoleNew = portal_role.substring(5,portal_role.length());
		myLog.info("portal_role="+portal_role+",portalRoleNew="+portalRoleNew);
		userId = (String)session.getAttribute("_ekptg_user_id");
		idUser = userId;
		String vm="";
		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action"); //untuk setup paging je
		String mode = getParam("mode");
		String selectedTab = getParam("tabId");
		
		String doChange = getParam("doChange");
		String button = getParam("button");
		idFail = getParam("idfail");		
		//String idfail = getParam("idfail");		
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idSuburusanStatusFail = getParam("idSuburusanStatusFail");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		
		if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    		tabmode = "0";
    	}
		String selectedTab2 = "";
		String selectedTab3 = "";
		// CARIAN
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
			/*uk = getIPengguna().getKementerian(userId);
			if(uk == null)
				throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
			
			idkementerianC = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
		*/
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
			//idkementerian = "99999";
			idkementerian =idkementerianC;
		}

		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		
		String tabSemasa = request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1,request.getRequestURI().length());
//		myLog.info("tabId 1="+request.getRequestURI());
		int iCount = 0;
		String defaultLangkah ="-1";
		//Permohonan
		if(tabSemasa.equals("1586518438767") //HQ
			|| tabSemasa.equals("1352353744653") //HQ | Penyedia 			
			|| tabSemasa.equals("1365032518280") //Pegawai1349328130099
			|| tabSemasa.equals("1346119820484") //KPP
			|| tabSemasa.equals("1349389007777")	//Pengarah
			|| tabSemasa.equals("1330411830838") //Admin | Pegawai2 - Server 
			){
//			myLog.info("tabId Permohonan="+tabId);
			IDURUSAN = "1,10";	
		// Pembelian
		}else if(tabSemasa.equals("1586518438768") 
		    || tabSemasa.equals("1356483564813") //HQ Pembelian
		    || tabSemasa.equals("1357181788404") //HQ Pembelian & Penswastaan
	    	|| tabSemasa.equals("1349323813314") //Pegawai
	    	//|| tabSemasa.equals("1586518438768") 
			|| tabSemasa.equals("1524781377540") //KPP
		|| tabSemasa.equals("1515369861708")	//Pengarah
    		){
			IDURUSAN = "2"; 
		//Gadaian 
        }else if(tabSemasa.equals("1592343471300") //
        	|| tabSemasa.equals("1356483564813") //HQ Gadaian
    		|| tabSemasa.equals("1349328130095") //Pegawai
			|| tabSemasa.equals("1524781377543") //KPP
			|| tabSemasa.equals("1515369861707") //Pengarah
        	){
    			IDURUSAN = "108";   
        //JRP
    	}else if(tabSemasa.equals("1592343471290")
    		|| tabSemasa.equals("1592293150193") //HQ Pembelian & Penswastaan
    		|| tabSemasa.equals("1601238745477") //(HTP)HQPenggunaPajakanKecil - local
    		){ //HQ Local|
    		IDURUSAN = "14"; 
    		defaultLangkah = "4";
    	//PAJAKAN
    	}else if(tabSemasa.equals("1592343471299") //HQ Local| 
        	|| tabSemasa.equals("1349328130101") //Pegawai1 
			|| tabSemasa.equals("1382002308484") //Pegawai2 - Server 
			|| tabSemasa.equals("1586518438768") //Pengarah
			|| tabSemasa.equals("1357114378377") //Admin
			){
    		IDURUSAN = "3";
    	}else{
    		IDURUSAN = "1,2,3,10,14,108";    		
    	}
//		myLog.info("IDURUSAN="+IDURUSAN);
		myLog.info("command="+submit+",mode="+mode);

		FrmSemakan semak = new FrmSemakan();
		Vector senaraiSemak = null;
		
		Vector<?> list = null;
		vm = PATH+"index.jsp";
		if("".equals(submit)){
			vm = PATH+"index.jsp";	   	   
			// 2020/09/05|
			list = getIOnline().findFailOnlineUrusan(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC,IDURUSAN,defaultLangkah);
			isCarian = getParam("txtcarian");
			myLog.info("isCarian::"+isCarian);
			if(isCarian.equals("ya")){
				list = getIOnline().findFailOnlineUrusan(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC,IDURUSAN);
				//list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
				isCarian = "ya";		
	
			}
			this.context.put("SenaraiFail", list);			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			//this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC), " onChange=\"doChangeKementerian();\" "));
			//midified by rosli 20100804
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\"  style=\"width:400\""));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "   style=\"width:400\""));
			    
			setupPage(session,action,list);
		
		// JRP	
		}else if (submit.equals("jrpviewmaklumat")) {
				idFail = getParam("idfail");
			mode= "view";
			
				this.context.put("readOnly", readonly);
				this.context.put("disabled", "disabled");	
				context.put("inputstyleread", readonly);
				
				Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFail);
				idPermohonan = (String)permohonan.get("idpermohonan");
				myLog.info("idPermohonan="+idPermohonan);
				//permohonanSetMaklumat(TerimaPohonInfo);
				
				this.context.put("permohonanInfo", permohonan);
				this.context.put("beanMaklumatPermohonan", permohonan);

			    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
		    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
		    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled='disabled' class='disabled'");
		    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("309"),"disabled='disabled' class='disabled'");
		    	
		    	this.context.put("socNegeri",socNegeri);	    
		    	this.context.put("socKementerian",socKementerian);	    
		    	this.context.put("socAgensi",socAgensi);	    
		    	//this.context.put("socUrusan",socUrusan);	    
		    	this.context.put("socUrusan",socUrusan);	    
			
				
				FrmSemakan frmSemak = new FrmSemakan();
//	        	Vector semakanSenarai = frmSemak.getSenaraiSemakanAttach("htpjrp",idPermohonan);
//				this.context.put("senaraiSemak", semakanSenarai);
//			    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());			    
				getSemakanPengesahan("htpjrp",frmSemak,idPermohonan,mode);

		    	this.context.put("mode",mode);	 
		    	
//		    	PERMOHONAN ONLINE	4
//		    	TINDAKAN PENYEDIA	5
//		    	TINDAKAN PEGAWAI	6
//		    	TINDAKAN PENGARAH	7
//		    	PENERIMAAN PERMOHONAN	8
//		    	if(portal_role.equals("HQPengguna")){
//					langkah = langkah;
//					kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idsuburusan,langkahSeterus);
//					ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
//						
//				}else if(portal_role.equals("HQPegawai")){
//					langkah = langkah;
//					kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idsuburusan,langkahSeterus);
//					ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
//				
//				}else if(portal_role.equals("HQPengarah")){
//				}
//		    	viewPengesahan(String langkah,String langkahSeterus,String portal_role,EmailConfig ec,String idPermohonan
//		    			,String userMail,String tajuk,String kandungan);
				
//				String statuSemasa = "0";
//				Hashtable<String,String> hashStatus = getStatusRekod().getInfoStatusPermohonanFail(idTanah, idPermohonan,langkah);
//				if(hashStatus != null)
//					statuSemasa = hashStatus.get("langkah");
//
//					this.context.put("statuSemasa", statuSemasa);
//		    	
				vm = PATH+"frmJRPPengesahan.jsp";
				myLog.info("vm="+vm);

		// PERMOHONAN	
		}else if ("permohonanviewmaklumat".equals(submit)
				|| (submit.equals("viewMaklumatPermohonan"))) {
			idFail = getParam("idfail");

			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");	
			context.put("inputstyleread", readonly);
			//setPaging(false,true,true,false,false);
			vm = PATH+"frmPermohonanPengesahan.jsp";
			Hashtable<String, String> TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFail);
			idPermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
			permohonanSetMaklumat(TerimaPohonInfo);
			
			FrmSemakan frmSemak = new FrmSemakan();
			mode= !mode.equals("")?mode:"view";
			getSemakanPengesahan("htpermohonan",frmSemak,idPermohonan,mode);

   		
		}else if ("permohonanditolak".equals(submit)) {
			idFail = getParam("idfail");
			vm = PATH+"frmSenaraiFailPengesahan.jsp";
			Hashtable<String, String> TerimaPohonInfo = FrmSenaraiFailTerimaPohonData.getTerimaPohonInfo(idFail);
			idPermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
			idSubUrusan = (String)TerimaPohonInfo.get("lblidSubUrusan");
			
//			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
//			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
//			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
//			subUrusanStatusFail.setAktif("0");
			//getIOnline().kemaskiniStatusPermohonanAktif(subUrusanStatusFail);
			
			//s = new Tblrujsuburusanstatusfail();
			//102 TOLAK (PERMOHONAN ONLINE)
//			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("102",idSubUrusan,"=");
//			//s.setIdPermohonan(Long.parseLong(idPermohonan));
//			//s.setIdFail(Long.parseLong(idfail));		
//			subUrusanStatusFail.setIdSuburusanstatus(setIdSuburusanstatus);
//			subUrusanStatusFail.setAktif("1");
//			subUrusanStatusFail.setIdMasuk(Long.parseLong(userId));
//			getIOnline().simpanStatusPermohonan(subUrusanStatusFail);
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"102");

			//permohonanSetMaklumat(TerimaPohonInfo);
			//
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));		 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
			setupPage(session,action,list);
   		
		}else if ("permohonanditerima".equals(submit)) {
			vm = PATH+"frmPermohonanPengesahan.jsp";
			idFail = getParam("idfail");
			this.context.put("readOnly", "");
			this.context.put("disabled", "disabled");			
			Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idFail);
			idPermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
			idSubUrusan = (String)TerimaPohonInfo.get("lblidSubUrusan");
			htpPermohonan = getIOnline().findPermohonan(idPermohonan,"");
			getIOnline().kemaskiniFail(htpPermohonan, idPermohonan, "");
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"1");	
			TerimaPohonInfo = fData.getTerimaPohonInfo(idFail);
			permohonanSetMaklumat(TerimaPohonInfo);
			
			EmailSender email = EmailSender.getInstance();
			//email.FROM ="etapp_webmaster@ekptg.gov.my";
			email.SUBJECT="EMAIL DARI PERMOHONAN TANAH";
			email.MESSAGE = getHTPEmel().setEmailSign(String.valueOf(TerimaPohonInfo.get("lblNoFail"))
					,String.valueOf(TerimaPohonInfo.get("lblTujuan")),"");
			email.MULTIPLE_RECIEPIENT = new String[1];
			email.MULTIPLE_RECIEPIENT[0] = "roslizakaria@gmail.com";
			//email.MULTIPLE_RECIEPIENT[1]="wfaresh@gmail.com";
			//email.MULTIPLE_RECIEPIENT[2]="cipon.it@gmail.com";				
			email.TO_CC = new String[1];
			email.TO_CC[0]  = "rosli@hla-group.com";
			email.sendEmail();  		
			
		}else if ("pajakanviewmaklumat".equals(submit)) {	// PAJAKAN
			vm = PATH+"frmPajakanPengesahan.jsp";
			idFail = getParam("idfail");
			logicHeader.setMaklumatPermohonan(idFail);
			
			beanMaklumatPermohonan = new Vector();
    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idAgensi = String.valueOf(hashHeader.get("idAgensi"));
			idNegeri = String.valueOf(hashHeader.get("idNegeri"));
			idSuburusan = String.valueOf(hashHeader.get("subUrusan"));
			String idKementerian = String.valueOf(hashHeader.get("idKementerian"));
			String idJenisFail = String.valueOf(hashHeader.get("idJenisFail"));
			String idStatusTanah = String.valueOf(hashHeader.get("idStatusTanah"));
			
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), " disabled class=disabled", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled class=disabled", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), " disabled class=disabled", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), " disabled class=disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), " disabled class=disabled", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), " disabled class=disabled", ""));
   		
        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        	MaklumatPermohonanView("view");
        	this.context.put("mode", "view");
        	
			semak.mode = mode;
			senaraiSemak = semak.getSenaraiSemakanAttach("htppajakanmycoid",idPermohonan);
   			this.context.put("senaraiSemak", senaraiSemak);
		
			   		
		}else if ("pajakanditolak".equals(submit)) {
			idFail = getParam("idfail");
			vm = PATH+"frmSenaraiFailPengesahan.jsp";
			logic.setMaklumatPermohonan(idFail);
			Vector maklumatPermohonan = logic.getMaklumatPermohonan();
			Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);
			//Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idFail);
			idPermohonan = String.valueOf(hashPermohonanDB.get("idPermohonan"));
			idSubUrusan = String.valueOf(hashPermohonanDB.get("idSuburusan"));		
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"102");

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));		 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
			setupPage(session,action,list);
   		
		}else if ("pajakanditerima".equals(submit)) {
			vm = PATH+"frmPajakanPengesahan.jsp";
			idFail = getParam("idfail");			
			logic.setMaklumatPermohonan(idFail);
			Vector maklumatPermohonan = logic.getMaklumatPermohonan();
			Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);
			idPermohonan = String.valueOf(hashPermohonanDB.get("idPermohonan"));
			idSubUrusan = String.valueOf(hashPermohonanDB.get("idSuburusan"));		
			htpPermohonan = getIOnline().findPermohonan(idPermohonan,"");
			getIOnline().kemaskiniFail(htpPermohonan, idPermohonan, "");
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"1");		
        	this.context.put("mode", "view_");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");      	
        	logicHeader.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = new Vector();
    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        	MaklumatPermohonanView("view");
		
			EmailSender email = EmailSender.getInstance();
			email.FROM ="etapp_webmaster@ekptg.gov.my";
			email.SUBJECT="EMAIL DARI PERMOHONAN PAJAKAN";
			Hashtable TerimaPohonInfo = (Hashtable)beanMaklumatPermohonan.elementAt(0);
			
			email.MESSAGE = getHTPEmel().setEmailSign(String.valueOf(TerimaPohonInfo.get("noFail"))
					,String.valueOf(TerimaPohonInfo.get("tajuk")),"");
			email.MULTIPLE_RECIEPIENT = new String[1];
			email.MULTIPLE_RECIEPIENT[0] = "roslizakaria@gmail.com";
			//email.MULTIPLE_RECIEPIENT[1]="wfaresh@gmail.com";
			//email.MULTIPLE_RECIEPIENT[2]="cipon.it@gmail.com";				
			email.TO_CC = new String[1];
			email.TO_CC[0]  = "rosli@hla-group.com";
			email.sendEmail(); 	
   		
		}else if ("pembelianviewmaklumat".equals(submit)) {	// PEMBELIAN
			idFail = getParam("idfail");
			vm = PATH+"frmPembelianPengesahan.jsp";
			getValues();
			
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			String pageMode = "update";
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", pageMode);
			context.put("inputstyleread", readonly);
			context.put("page","1");
//			Vector dokumens = getIOnline().getLampiranByPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
//			context.put("senaraidokumen", dokumens);
			
			getSemakanPengesahan("htpakuanpembelian",semak,String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()),pageMode);

			   		
		}else if ("pembelianditolak".equals(submit)) {
			//idFail = getParam("idfail");
			vm = PATH+"frmSenaraiFailPengesahan.jsp";
			getValues();
			idPermohonan = String.valueOf(permohonan.getIdPermohonan());
			//htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			htpPermohonan = getIOnline().findPermohonan(idPermohonan,idHtpPermohonan);			
			idSubUrusan = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());		
			idFail = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"102");

			//
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));		 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
			setupPage(session,action,list);
   		
		}else if ("pembelianditerima".equals(submit)) {
			vm = PATH+"frmPembelianPengesahan.jsp";	
			getValues();
			viewDetail2();
			idPermohonan = String.valueOf(permohonan.getIdPermohonan());
			htpPermohonan = getIOnline().findPermohonan(idPermohonan,idHtpPermohonan);			
			idSubUrusan = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());		
			idFail = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			//getIOnline().kemaskiniFail(htpPermohonan, idPermohonan, "");
			//kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"1");
			
			htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update_");
			context.put("inputstyleread", readonly);
			EmailProperty pro = EmailProperty.getInstance();
			String EMAIL_HOST= pro.getHost();
			//myLog.info("EMAIL_HOST="+EMAIL_HOST);

			EmailSender email = EmailSender.getInstance();
			//email.FROM ="rosli@hla-group.com";		
			if(EMAIL_HOST.equals("mail.hla-group.com")){
				email.FROM = getHTPEmel().checkEmail(userId);
			}else{
				email.FROM = "etapp_webmaster@ekptg.gov.my";
			}
			myLog.info("from="+email.FROM);
			email.SUBJECT="EMAIL DARI PERMOHONAN PERAKUAN PEMBELIAN";
			email.MESSAGE = getHTPEmel().setEmailSign(htpPermohonan.getPermohonan().getPfdFail().getNoFail()
					,htpPermohonan.getPermohonan().getPfdFail().getTajukFail(),"");
			email.MULTIPLE_RECIEPIENT = new String[1];
			//email.MULTIPLE_RECIEPIENT[0] = "roslizakaria@gmail.com";
			email.MULTIPLE_RECIEPIENT[0] = getHTPEmel().checkEmail(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdMasuk()));
			myLog.info("to="+email.MULTIPLE_RECIEPIENT[0]);
			email.TO_CC = new String[1];
			//email.TO_CC[0]  = "rosli@hla-group.com";
			email.TO_CC[0] = getHTPEmel().checkEmail(userId);
			email.sendEmail(); 
   		
		}else if ("perletakhakanviewmaklumat".equals(submit)) {	// PERLETAKHAKAN
			idFail = getParam("idfail");
			vm = PATH+"frmPerletakhakanPengesahan.jsp";
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);
			   		
		}else if ("perletakhakanditolak".equals(submit)) {
			idFail = getParam("idfail");
			vm = PATH+"frmSenaraiFailPengesahan.jsp";
			logic.setMaklumatPermohonan(idFail);
			Vector maklumatPermohonan = logic.getMaklumatPermohonan();
			Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);
			//Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idFail);
			idPermohonan = String.valueOf(hashPermohonanDB.get("idPermohonan"));
			idSubUrusan = String.valueOf(hashPermohonanDB.get("idSuburusan"));		
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"102");

			//
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));		 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
			setupPage(session,action,list);
   		
		}else if ("perletakhakanditerima".equals(submit)) {
			vm = PATH+"frmPerletakhakanPengesahan.jsp";
			idFail = getParam("idfail");
			this.context.put("readOnly", "");
			this.context.put("disabled", "disabled");	
			logic.setMaklumatPermohonan(idFail);
			Vector maklumatPermohonan = logic.getMaklumatPermohonan();
			Hashtable hashPermohonanDB = (Hashtable) maklumatPermohonan.get(0);
			idPermohonan = String.valueOf(hashPermohonanDB.get("idPermohonan"));
			idSubUrusan = String.valueOf(hashPermohonanDB.get("idSuburusan"));		
			htpPermohonan = getIOnline().findPermohonan(idPermohonan,"");
			getIOnline().kemaskiniFail(htpPermohonan, idPermohonan, "");
			kemaskiniSimpanStatusPenerimaan(idFail,idPermohonan,idSubUrusan,"1");
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	//VIEW PENDAFTARAN PERLETAHAKAN BY ID FAIL	
        	viewPendaftaranPerletakhakanByIdFail(actionPerletakhakan,idFail);
			EmailSender email =EmailSender.getInstance();
			email.FROM ="etapp_webmaster@ekptg.gov.my";
			email.SUBJECT="EMAIL DARI PERMOHONAN PERLETAKHAKAN";
			
			email.MESSAGE = getHTPEmel().setEmailSign(noFail,tajukFail,"");
			email.MULTIPLE_RECIEPIENT = new String[1];
			email.MULTIPLE_RECIEPIENT[0] = "roslizakaria@gmail.com";
			//email.MULTIPLE_RECIEPIENT[1]="wfaresh@gmail.com";
			//email.MULTIPLE_RECIEPIENT[2]="cipon.it@gmail.com";				
			email.TO_CC = new String[1];
			email.TO_CC[0]  = "rosli@hla-group.com";
			email.sendEmail();	
   		
		}		
		
		if (submit.indexOf("doChange") != -1) {
			vm = PATH+"frmSenaraiFailPengesahan.jsp";
		   	   
			//21/09/2010
			list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
			isCarian = getParam("txtcarian");
			myLog.info("isCarian::"+isCarian);
			if(isCarian.equals("ya")){
				list = getIOnline().findFailOnline(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeriC, idkementerianC);
				isCarian = "ya";		
	
			}
			this.context.put("SenaraiFail", list);			 
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			 
			this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Utils.parseLong(idNegeriC), " onChange=\"doChangeNegeriCarian();\" "));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Utils.parseLong(idDaerahC), "", " onChange=\"doChangeDaerahCarian();\" "));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Utils.parseLong(idMukimC), "", ""));
			//this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC), " onChange=\"doChangeKementerian();\" "));
			//midified by rosli 20100804
			this.context.put("selectKementerianC", HTML.SelectKementerian("socKementerianC", Utils.parseLong(idkementerianC)," ", " onChange=\"doChangeKementerianCarian();\" "));
			this.context.put("selectAgensiC", HTML.SelectAgensiByKementerian("socAgensiC", idkementerianC, Utils.parseLong(idAgensiC), "  "));
			    
			setupPage(session,action,list);
		}
		
		this.context.put("javaScriptLampiran", getDoc().javascriptUpload("", "paparLampiran", "idDokumen",session,"perakuan"));

		this.context.put("idFail", idFail);
		//2010/09/21
		//this.context.put("idfail", idfail);
		myLog.info("idPermohonan="+idPermohonan);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHtpPermohonan", idHtpPermohonan);
		this.context.put("idSuburusanStatusFail", idSuburusanStatusFail);
		this.context.put("kodKementerian", getParam("kodKementerian"));
		this.context.put("kodNegeri", getParam("kodNegeri"));
		this.context.put("iscarian", isCarian);
		return vm;
	  
   }
	//********************************* SENARAI METHOD *******************************************************************

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
		noFail = String.valueOf(hashPermohonanDB.get("noFail"));
		tajukFail = String.valueOf(hashPermohonanDB.get("txtTajuk"));
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
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
		
	}	
	//Permohonan
	
	public void permohonanSetMaklumat(Hashtable TerimaPohonInfo) throws Exception {
		try {
			String lblNegeri = "";
			String lblKementerian = "";
			String lblidAgensi = "";
			String lblidUrusan = "";
			String lblidSubUrusan = "";
			String lblidKodJTanah = "";
			String lblTanahKeselamatan = "";
		
			idUrusan = (String)TerimaPohonInfo.get("lblidUrusan");
			idNegeri = (String)TerimaPohonInfo.get("lblNegeri");
		
			this.context.put("noFail", TerimaPohonInfo.get("lblNoFail"));
			this.context.put("diDaftarOleh", TerimaPohonInfo.get("diDaftarOleh"));
			this.context.put("diDaftarPada", TerimaPohonInfo.get("diDaftarPada"));
		
			socNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idNegeri),"disabled class=disabled");
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri,"lblDaerah",Utils.parseLong((String)TerimaPohonInfo.get("lblIdDaerah")),"disabled class=disabled");

			socKementerian =HTML.SelectKementerian("lblKementerian", Utils.parseLong((String)TerimaPohonInfo.get("lblidKementerian")), "disabled class=disabled");
			socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)TerimaPohonInfo.get("lblidAgensi")),"disabled class=disabled");
			socUrusan = UtilHTML.SelectUrusan("lblidUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidUrusan")),"disabled class=disabled");
		
			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idUrusan,"lblidSubUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidSubUrusan")),"disabled class=disabled");
			socStatusTanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils.parseLong((String)TerimaPohonInfo.get("lblidKodJTanah")), "disabled class=disabled");
			socJenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)TerimaPohonInfo.get("lblTanahKeselamatan")),"disabled class=disabled");
		
			context.put("socKementerian", socKementerian);
			context.put("socStatustanah", socStatusTanah);
			context.put("socUrusan", socUrusan);
			context.put("socAgensi", socAgensi);
			context.put("socNegeri", socNegeri);
			context.put("socDaerah", socDaerah);
			context.put("socjenisFail", socJenisFail);
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
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	private void kemaskiniSimpanStatusPenerimaan_(String idFail,String idPermohonan,String idSubUrusan,String langkah)throws Exception {
		 Db db = null;
		 Connection conn = null;
		 try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");

			getIOnline().kemaskiniStatusPermohonanAktif(subUrusanStatusFail);
		
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			subUrusanStatusFail.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFail.setAktif("1");
			subUrusanStatusFail.setIdMasuk(Long.parseLong(userId));
			getIOnline().simpanStatusPermohonan(subUrusanStatusFail);
	          conn.commit();		      		
		
		} catch (Exception e) {
			throw new Exception("Ralat[554]:"+e.getCause());
			
		}
	}
	
	private void kemaskiniSimpanStatusPenerimaan(String idFail,String idPermohonan,String idSubUrusan,String langkah)throws Exception {
		 try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");

			//getIOnline().kemaskiniStatusPermohonanAktif(subUrusanStatusFail);
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN);
			
		} catch (Exception e) {
			throw new Exception("Ralat[554]:"+e.getCause());
			
		}
	}	
	//PERMOHONAN
	public void setPaging(boolean page1,boolean page2,boolean page3,boolean page4,boolean page5) {
		this.context.put("page1",page1);
		this.context.put("page2",page2);
		this.context.put("page3",page3);
		this.context.put("page4",page4);
		this.context.put("page5",page5);
	}

public String doSimpanMaklumatPermohonan() throws Exception {
		
		Hashtable h = new Hashtable();
		h.put("id_Fail", idFail);
		h.put("id_Tarafkeselamatan", "1");
		h.put("id_Seksyen", getParam("socSeksyen"));
		h.put("id_Urusan", idUrusan);
		h.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		h.put("tajuk_Fail", getParam("txtTajuk"));
		h.put("no_Fail", nofail);
		h.put("id_Negeri", idNegeri);
		h.put("id_Daerah", iddaerah);
		h.put("id_Kementerian",id_kementerian);
		h.put("id_Agensi",id_agensi);
		h.put("flag_Fail",1);
		h.put("id_Status", "138");
		h.put("id_Masuk", idUser);
		h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		h.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		h.put("noFailUPT", getParam("txtnoFailUPT"));  
		h.put("noFailKJP", getParam("txtnoFailKJP"));  
		h.put("StatusTanah", getParam("socStatustanah"));  
		return fData.simpanPermohonanOnline(h,idUser);
		//fData.janaFail(h);
	
	}

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

	//PEMBELIAN
	private void getValues(){
		String idNegeri = getParam("socNegeri");
		String idKementerian = getParam("socKementerian");
		String idSubUrusan = getParam("socSuburusan");
		String idAgensi = getParam("socAgensi");			
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtNoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSurKJP");
		String noFailLain = getParam("txtNoFailLain");
		String tarikhPermohonan = getParam("txdTarikhPermohonan");
		String idJenisTanah = getParam("socStatusTanah");
		String idKeselamatan = getParam("socTarafKeselamatan");
		String noFail = getParam("txtNoFailSek");
		String idPermohonan = getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan");
		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
		
		
		fail.setIdNegeri(idNegeri);
		fail.setIdKementerian(idKementerian);
		fail.setIdSubUrusan(idSubUrusan);
		fail.setIdTarafKeselamatan(idKeselamatan);
		fail.setNoFail(noFail);
		//senaraiFail = new FrmSenaraiFailTerimaPohonData();
		//senaraiFail.getKementerianByMampu(Integer.parseInt(idKementerian));
		
		htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
		htpPermohonan.setIdAgensi(idAgensi);
		permohonan.setTujuan(tajuk);
		htpPermohonan.setNoRujukanKJP(failKJP);
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		permohonan.setTarikhTerima(tarikhPermohonan);
		htpPermohonan.setIdJenisTanah(idJenisTanah);
		permohonan.setIdPermohonan(idPermohonan);
		
		
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);
	
		context.put("permohonan", htpPermohonan);
	}

	private void viewDetail2() throws  Exception{	
		String socNegeri = HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),"disabled");
		String socKementerian = HTML.SelectKementerian("socKementerian", htpPermohonan.getPermohonan().getPfdFail().getIdKementerian(),"disabled");
		String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(),"disabled");
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
		context.put("socNegeri",socNegeri);
		context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("2", "socSuburusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),"disabled"));
		context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), "disabled"));
		context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(), "disabled") );
	}	
	
	//Pajakan
	  public void MaklumatPermohonanView(String mode) throws Exception{
		  try {	    		
			  if (mode.equalsIgnoreCase("view")){    			

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		this.context.put("inputTextClass", "disabled");
	        		
	        	}
	    		//mode = update
	    		else if(mode.equalsIgnoreCase("update")){	    			

	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    		}

	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	    }
	  
	private boolean isTab(String role, String tab) throws Exception {
		boolean returnValue = false;
		Utils utils = new Utils();
		tabId = utils.getTabID(tab,role);
		if(!tabId.equals("")) {
			returnValue = true;
			//myLog.info(utils.getTabID(tab,role));
		}
		return returnValue;
		
	}
	
	private String tabs(String role, String tab) throws Exception {
		String returnValue = "";
		Utils utils = new Utils();
		//tabId = utils.getTabID(tab,role);
		//if(!tabId.equals("")) {
			//returnValue = true;
			//myLog.info(utils.getTabID(tab,role));
			returnValue = utils.getTabID(tab,role);
		//}
		return returnValue;
		
	}
	
	private void getSemakanPengesahan(String jenisSemak,FrmSemakan semak,String idPermohonan,String mode) throws Exception{
		semak.mode = mode;
		Vector senaraiSemak = semak.getSenaraiSemakanAttach(jenisSemak,idPermohonan);
		
    	this.context.put("mode",mode);
    	this.context.put("senaraiSemak", senaraiSemak);

//		context.put("semakclass", new FrmSemakan());
//		Vector semakList = FrmSemakan.getSenaraiSemakan("frmPajakanSemakan");
//		context.put("perakuanPembelian", semakList);
	}
	
	private void viewPengesahan(String langkah,String langkahSeterus,String portal_role,EmailConfig ec,String idPermohonan
		,String userMail,String tajuk,String kandungan) throws Exception{
		//String langkah = "0";
		if(portal_role.equals("HQPengguna")){
			//langkah = langkah;
			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idsuburusan,langkahSeterus);
			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
				
		}else if(portal_role.equals("HQPegawai")){
			//langkah = langkah;
			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idsuburusan,langkahSeterus);
			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
		
		}else if(portal_role.equals("HQPengarah")){
			//langkah = langkah;
			kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idsuburusan,langkahSeterus);
			tajuk = ec.tajukKelulusan+subModul;
			ec.sendByRole(userMail, portal_role, null, tajuk, kandungan);
		
			kandungan = getEmeLulus().setEmailSign(htpPermohonan.getPermohonan().getTarikhTerima()
				, htpPermohonan.getPermohonan().getPfdFail().getTajukFail()
				, htpPermohonan.getPermohonan().getPfdFail().getNamaKementerian());
			//"4" Pengarah (KJP)
			ec.sendByRoleKJP(getEmeLulus().checkEmail(userId)
					, "4"
					, String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian())
					, tajuk, kandungan);

		}
		myLog.info("langkah="+langkah);
	
		String statuSemasa = "0";
		Tblrujsuburusanstatus hashStatus = getStatus().getStatusPermohonanAktif(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdFail())
											, String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
	
		if(hashStatus != null)
			statuSemasa = String.valueOf(hashStatus.getLangkah());
		
	//	myLog.info("langkah1 ="+htpPermohonan.getPermohonan().getPfdFail().getIdFail());
	//	myLog.info("langkah2="+String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
		//
		myLog.info("statuSemasa="+statuSemasa+"|portal_role="+portal_role);
		//this.context.put("idFail", idFail);
		this.context.put("statuSemasa", statuSemasa);
	
	}
	//Skrin Tindakan
	private String kemaskiniSimpanStatusSelesai(String idFail
		,String idPermohonan
		,String idSubUrusan
		,String langkah) throws Exception {
		try {				
			
			Tblrujsuburusanstatusfail subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl("-");
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN));
				
		} catch (Exception e) {
			throw new Exception(getHTP().getErrorHTML("Ralat Pengesahan, kemaskiniSimpanStatusSelesai:"+e.getMessage()));
		}
	
	}	
	
	private IHtp getHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
		
	}

	private IHTPEmel getHTPEmel(){
		if(iHTPEmel == null)
			iHTPEmel = new HTPEmelBean();
		
		return iHTPEmel;
		
	}	
	
	private IEmel getEmeLulus(){
		if(emelulus == null)
			emelulus = new HTPEmelKelulusanBean();
		return emelulus;
	}
	
	private IHTPStatus getStatus(){
		if(iStatus == null)
			iStatus = new HTPStatusBean();
		
		return iStatus;
		
	}
	
	private ILampiran getDoc(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
				
	}
	
	
}