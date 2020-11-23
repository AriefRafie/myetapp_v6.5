package ekptg.view.htp.online.jrp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblhtphakmilikbangunan;
import ekptg.model.entities.Tblhtppihakberkepentingan;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmPajakanKecilHakmilikData;
import ekptg.model.htp.FrmPajakanKecilMaklumatData;
import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmJRPSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmSewaanDerafData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pajakankecil.HTPPajakanKecilFailBean;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PemilikBean;
import ekptg.model.htp.utiliti.HTPEmelSemakanBean;
import ekptg.model.htp.utiliti.HTPSusulanBean;
import ekptg.model.htp.utiliti.IHTPSusulan;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.model.utils.lampiran.ILampiran;
import ekptg.model.utils.lampiran.LampiranBean;

public class FrmKJPJawatankuasaRuangPejabat extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -609204869152059236L;
	private final String PATHTP="app/htp/";
	private final String PATHVER = PATHTP+ResourceBundle.getBundle("file").getString("ver_htp")+"/";
	private final String PATH = PATHVER+"jrp/online/";

	private static Logger myLog = Logger.getLogger(ekptg.view.htp.online.jrp.FrmKJPJawatankuasaRuangPejabat.class);
	private ekptg.model.htp.IHtp iHtp = null;
 	private IHTPFail iHTPFail = null;  
 	private IHTPFail iHTPPKFail = null;
    private ILampiran iLampiran = null;

	private IHTPStatus iStatus = null;
	private IHTPSusulan iSusulan = null;
	private IPemilik iPemilik = null;
	private IOnline iOnline = null;
	private IHTPPermohonan iHTPPermohonan = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	
	private Long idHakmilikUrusan = 0L;
	private PihakBerkepentingan pemilik = null;
	private HtpPermohonan htpPermohonan = null;
	private String idUrusan = "14";
	private String idSubUrusan = "9920122";
	private String idFailN = null;
	private String jenisAkses="Readonly";
	private String userId = null;
	private String idPihakBerkepentingan = null;
	private String skrin = null;
	private String idKemaskiniPaging = null;
	// Carian
	private String socKementerian = "";
	private String socAgensi = "";
	private String socNegeri = "";
	private String socDaerah = "";
	private String socMukim = "";
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;

 	String idNegeri = "";
 	private String idDaerah = "";
 	private String idMukim = "";
	String txtTajukCarian = "";
 	String idKementerian = "";
 	String idAgensi = "";
 	String noFail = "";
 	String jawatan = "";
	String idJawatan = "";
 	private IHtp iHTP = null;  
 	private UserKementerian uk = null;
 	private IPenggunaKementerian iPengguna = null;
 	private IUserPegawai iUser = null;

	public String doTemplate2()throws Exception {
		myLog.info("20200928 0953");
		 
		try{
			HttpSession session = this.request.getSession();
		    userId = session.getAttribute("_ekptg_user_id").toString();
			String portal_role = (String)session.getAttribute("myrole");
			context.put("portal_role",portal_role);
		    String template_name = "";
	      	String disability = " disabled class=disabled ";
		    String socAgensi = "";
		    String socKementerian = "";
		    String socNegeri = "";
		    String socUrusan = "";
		    String idNegeriPemohon = "";
		    String namaPemohon = getParam("namaPemohon");
			Vector<Hashtable<String,String>> semakanSenarai = new <Hashtable<String,String>>Vector();
			Vector listDetailKJP = null;

		    this.context.put("util", new lebah.util.Util());
		    Vector senaraiFail = null;
		    socNegeri = HTML.SelectNegeriA("socNegeri");
		    //socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");	    
	        String id_kementerian = getParam("sockementerian");
			String action = getParam("action"); //untuk setup paging je
		    String submit = getParam("command");
		    String idFail = getParam("fail");
		    String tajuk = getParam("txtTajukFail");
		    idFailN = getParam("idFailN");
		    idPihakBerkepentingan = getParam("id_pemilik");
		    idKemaskiniPaging = getParam("id_kemaskini");
		    String pageMode = getParam("pagemode");
		    String langkah = getParam("langkah");
		    String isCarian = "tidak";
		    myLog.info("1. command="+submit+",pageMode="+pageMode);
		 	idNegeri = getParam("socNegeri");
			txtTajukCarian = getParam("txtTajukFail");
		 	idKementerian = getParam("socKementerian");
		 	idAgensi = getParam("socAgensi");
		    //myLog.info("1. idNegeri::"+idNegeri+"1. txtTajukCarian::"+txtTajukCarian);
		    //myLog.info("1. idKementerian::"+idKementerian+"1. idAgensi::"+idAgensi);
			String intPB = "0";
	
			if (id_kementerian == null || id_kementerian.trim().length() == 0){
				uk = getIPengguna().getKementerian(userId);
				if(uk == null){
					throw new Exception(getHTP().getErrorHTML("[ONLINE-HTP JRP] KEMENTERIAN TIDAK DIJUMPAI"));
				}
			
				myLog.info("id_kementerian="+uk.getAgensi().getKementerian().getIdKementerian());
				id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
				//System.out.println("+++++"+id_kementerian);
			}
			Hashtable hUser = getIUser().getPengguna(userId);
			jawatan = String.valueOf(hUser.get("jawatan"));
			idJawatan = String.valueOf(hUser.get("idjawatan"));
			context.put("idjawatan", idJawatan);

			myLog.info("JAWATAN="+jawatan);
			myLog.info("JAWATAN="+hUser.get("idjawatan"));
			myLog.info("idSubUrusan====="+idSubUrusan);
			if (!("".equals(submit))) {
				
		    	this.context.put("idsuburusan",idSubUrusan);
		    	this.context.put("idurusan",idUrusan);		    	
		    	// PAGE 1
		    	if(submit.equals("pksenaraifailcari")){ //carian
		    		template_name = PATH+"frmJRPSenaraiFail.jsp";
		    	    myLog.info("equals("+submit+")::pksenaraifailcari");
		    		//String nofail = getParam("nofail");
		    		noFail = getParam("txtNoFail");
				 	idDaerah = getParam("socDaerah");
				 	idMukim = getParam("socMukim");
				 	
				 	maklumatCarian();
		    		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),"disabled class=disabled","onChange=\"doChangeKementerianCarian()\" style=\"width:400\"");
				    this.context.put("socKementerian",socKementerian);
		    		//senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail.trim(),txtTajukCarian,idNegeri,idKementerian,idAgensi,"");      			    		
//				    senaraiFail =  getIHTPPKFail().getSenaraiFail(
//				    		null, noFail.trim(), txtTajukCarian
//				    		, idKementerian, idAgensi
//				    		, idNegeri, idDaerah, idMukim
//				    		, idUrusan, "", ""
//				    		, "", "", false );
				    
				    myLog.info("else");	 
			    	
					    senaraiFail = getIHTPFail().getSenaraiFail(null
			    			, null, null
			    			, id_kementerian, null
			    			, null, null, null
			    			, idUrusan
			    			,null);
				    noFail = getParam("txtNoFail");
		    		this.context.put("txtTajukCarian", txtTajukCarian);	    		
		    		isCarian = "ya";		
					setupPage(session,action,senaraiFail);
	
		    	}else if(submit.equals("pksenaraipermohonan")){
			    	template_name = PATH+"frmJRPSenaraiPermohonan.jsp";	
		    		myLog.info("equals(submit)::pksenaraipermohonan");
		    		idFail = getParam("fail");
			    	Vector senaraiPermohonan = null;
			    	Hashtable hashLanjutan = getIHTP().getInfoTamatSelesaiPermohonan(getParam("idpermohonan"));		
			    	String lanjutan = "tidak";
			    	if(hashLanjutan!=null)
			    		lanjutan= "ya";
				    this.context.put("lanjutan", lanjutan);	    

			    	senaraiPermohonan = FrmJRPSenaraiPermohonanData.getList(idFail);
			    	this.context.put("senaraiList", senaraiPermohonan);	    	
			    	this.context.put("idFail", idFail);	    
		        	Hashtable hFail = getIHTPFail().getMaklumatPermohonan(idFail);
			    	this.context.put("nomborFail", hFail.get("noFail"));	    

		    	}else if(submit.equals("tajukpermohonan")){
			    	template_name = PATH+"frmJRPTajuk.jsp";
			    	idFail = getParam("fail");
			    	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFail);
		    		myLog.info("equals(submit)::tajukpermohonan");
			    	this.context.put("permohonan", permohonan);	 
			    	this.context.put("dis", 1);
			    	this.context.put("idFail", idFail);	    	
		    	
			    }else if(submit.equals("kemaskinitajukpermohonan")){
			    	template_name = PATH+"frmJRPTajuk.jsp";
			    	idFail = getParam("fail");
			    	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFail);
		    		myLog.info("equals(submit)::tajukpermohonan");
		    		this.context.put("dis", 0);
			    	this.context.put("permohonan", permohonan);	    	
			    	this.context.put("idFail", idFail);	    	
		    	
			    }else if(submit.equals("simpantajukpermohonan")){
			    	template_name = PATH+"frmJRPSenaraiPermohonan.jsp";	
			    	idFail = getParam("fail");
			    	simpanTajuk(idFail);
			    	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFail);
		    		myLog.info("equals(submit)::tajukpermohonan");
		    		this.context.put("dis", 1);
			    	this.context.put("permohonan", permohonan);	    	
			    	idFail = getParam("fail");
			    	Vector senaraiPermohonan = null;
			    	senaraiPermohonan = FrmJRPSenaraiPermohonanData.getList(idFail);
			    	this.context.put("senaraiList", senaraiPermohonan);	    	
			    	this.context.put("idFail", idFail);	        	
		    	
			    }else if(submit.equals("pkfailbaru")){
//			    	myLog.info("submit="+submit+",pageMode="+pageMode);
			    	template_name = PATH+"frmJRPPendaftaran.jsp";
			    	String strOperation = "";
				    this.context.put("socSeksyen","3");
			    	if(getParam("mode").equals("reset")){
			    		id_kementerian = "99999";	    		
			    	}

				    if(pageMode.equals("0")){
				    	//myLog.info("pageMode::0"+pageMode);
					    this.context.put("socSeksyen","3");
					    socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian),"onChange=\"doChangeKementerianCarian()\" style=\"width:400\"");
					    socUrusan = HTML.SelectSuburusanByIdUrusan(idUrusan,"socUrusan",Long.parseLong(idSubUrusan),"");
//					    socUrusan = HTML.SelectSuburusan("socUrusan",Long.parseLong(idSubUrusan),disability);
//					    socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong(idUrusan),disability);
				    	this.context.put("socUrusan",socUrusan);
				    	//perjanjian 44
				    	this.context.put("idsuburusan",idSubUrusan);
				    	this.context.put("idurusan",idUrusan);
				    	String strdate = "";
				    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
				    	this.context.put("sekarang",strdate);
					    this.context.put("pageMode", "0");  
					    this.context.put("nofail", "");  
					    socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), disability, "onChange=\"doChangeKementerian()\" style=\"width:400\"");
					    socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
					    this.context.put("socNegeri",socNegeri);
					    this.context.put("socKementerian",socKementerian);
					    this.context.put("socAgensi",socAgensi);
					    this.context.put("pageMode", 0);  
		    		
			    	}else if(pageMode.equals("1")){
				    	strOperation = "success";
					    String idnegeri = getParam("socNegeri");
					    String idagensi = getParam("socAgensi");
			
						idFail = String.valueOf(ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ"));
						simpanFail(idFail);
						
						String idPermohonan = simpanPermohonan(session,0,idFail);					
					    this.context.put("idPermohonan",idPermohonan);
					    idFailN = idFail;		
					    
					    simpanStatus(session,Long.parseLong(String.valueOf(idPermohonan)),Long.parseLong(idFail),"1");
			        	Hashtable hFail = getIHTPFail().getMaklumatPermohonan(idFailN);
						AuditTrail.logActivity("1", "3", this, session, "INS", "FAIL PERMOHONAN ["+hFail.get("noFail")+"] DITAMBAH ");

					    String[] cbsemaks = this.request.getParameterValues("cbsemaks");
			        	FrmSemakan frmSemak = new FrmSemakan();
			        	if(cbsemaks!=null){
			        		for (int i = 0; i < cbsemaks.length; i++) { 
			        			frmSemak = new FrmSemakan();
			        			//myLog.info("pkfailbaru|pageMode["+pageMode+"::cbsemaks:::"+cbsemaks[i]);
			        			frmSemak.semakanTambah(cbsemaks[i], ""+idPermohonan);           
			        		}
			        	}
			        	//after saved
			        	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFailN);		        	
						myLog.info("fir hash after saved : " + permohonan);				
						this.context.put("permohonanInfo", permohonan);
					    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());				    
				    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
				    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled='disabled' class='disabled'");
				    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),"disabled='disabled' class='disabled'");
				    	this.context.put("socUrusan",socUrusan);		    
					    // Senarai semakan
					    // 24/10/2010
					    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
					    this.context.put("senaraiSemakan",semakanSenarai );
				    	pageMode = "2";
			    	
				    }else if(pageMode.equals("3")){
				    	myLog.info("pageMode=3, id=" + id);
				    	String id = getParam("id_kemaskini");
	
				    	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFailN);		    	
					    this.context.put("permohonanInfo", permohonan);
					    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());			    
					    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
				    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
				    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled='disabled' class='disabled'");
				    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),"disabled='disabled' class='disabled'");
				    	this.context.put("socUrusan",socUrusan);	    
					    // Senarai semakan
					    // 24/10/2010
				    	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
					    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
					    this.context.put("senaraiSemakan",semakanSenarai );		    	
				    	pageMode = "4";
				    	
				    }/*else if(pageMode.equals("3")){
			    	
			    	pageMode = "4";
			    }*/	else{

				    	String id = getParam("id_kemaskini");
				    	//System.out.println("FrmPajakanKecilA: equals(submit)::pkfailbaru:::else|id"+id);
				    	Hashtable permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFailN);		    	
					    this.context.put("permohonanInfo", permohonan);
					    Hashtable h = new Hashtable();
	//					h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
					    h.put("idPermohonan", Long.parseLong((String)permohonan.get("idpermohonan")));
					    h.put("idFailN", idFailN);
					    h.put("id_kemaskini", 1);
					    h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
					    h.put("NoFailLain", getParam("txtNoFailLain"));
					    h.put("Tajuk", getParam("txttajuk"));
					    
					    myLog.info("fir hash kemaskini : " + h);
					    FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);				    
					    permohonan = FrmJRPSenaraiPermohonanData.getFailInfo(idFailN);		    	
					    this.context.put("permohonanInfo", permohonan);
					    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());				    
					    
					    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"disabled class=disabled");
				    	socKementerian = HTML.SelectKementerian("sockementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled class=disabled");
				    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled class=disabled");
				    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),"disabled='disabled' class='disabled'");
				    	this.context.put("socUrusan",socUrusan);				    
					    // Senarai semakan
					    //24/10/2010
				    	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
					    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
					    this.context.put("senaraiSemakan",semakanSenarai );
				    	pageMode = "2";
    	
			    	}
				    this.context.put("operation",strOperation);
				    this.context.put("socNegeri",socNegeri);
				    this.context.put("socKementerian",socKementerian);
				    this.context.put("socAgensi",socAgensi);
				    this.context.put("pageMode",pageMode);
	    	
		    	}else if (submit.equals("doChangeKementerian")){
			    	template_name = PATH+"frmJRPPendaftaran.jsp";
			    	myLog.info("FrmPajakanKecilA: equals(submit)::doChangeKementerian");
				    String idnegeri = getParam("socNegeri");
				    if(idnegeri == ""){
					    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
				    }else{	
					    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri",Long.parseLong(idnegeri),"enabled");
				    }
			    	socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" style=\"width:400\"");
			    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
					
				    socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),disability);
			    	this.context.put("socUrusan",socUrusan);
			    	String strdate = "";
			    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
			    	this.context.put("sekarang",strdate);
					//pageMode = Integer.parseInt(getParam("pagemode"));
				    this.context.put("nofail", "");  
				    // 24/10/2010
				    //semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
				    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+idnegeri);
				    this.context.put("senaraiSemakan",semakanSenarai );
				    this.context.put("socSeksyen","3");	
					this.context.put("pageMode","0");
				    this.context.put("socNegeri",socNegeri);
				    this.context.put("socKementerian",socKementerian);
				    this.context.put("socAgensi",socAgensi);
		
		    	}else if(submit.equals("pkpendaftaranseterus")) {
			    	template_name = "app/htp/pajakankecil/online/frmJRPSenaraiPemilik.jsp";
		    		myLog.info("FrmPajakanKecilA: equals(submit)::pkpendaftaranseterus");
				    //readability = "readonly";
				    disability = "disabled";
			    	Hashtable hakmilikbangunan = null;
			    	String id = getParam("id_kemaskini");
					Hashtable permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				    //this.context.put("socNegeri",socNegeri);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),"disabled");
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"disabled");
			    	
					hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
					this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
					Vector senaraiHakmilik = null;
					senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
					this.context.put("senaraihakmilik", senaraiHakmilik);
	
					Vector senaraiPemilik = null;
					senaraiPemilik = FrmPajakanKecilHakmilikData.getSenaraiPemilik(Long.parseLong(id));
					this.context.put("senaraipemilik", senaraiPemilik);
	    	
		    	}else if (submit.equals("pkpermohonantambah")) { //belum
			    	template_name = PATH+"frmJRPPendaftaranPermohonan.jsp";	
		    		myLog.info(" pkpermohonantambah:"+pageMode);
				    //disability = "disabled class=disabled";
				   	String id_ = getParam("id_kemaskini");
					Hashtable<?, ?> h = null;
					h = FrmSenaraiFailPajakanKecilData.getFailInfo(id_);
					
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()),disability+" style=\"width:400\"");
					socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong(idUrusan),disability);
			    	this.context.put("socUrusan",socUrusan);
			    	this.context.put("socNegeri",socNegeri);
					this.context.put("socKementerian",socKementerian);
					  
			        String strdate = "";
			    	strdate = (String)h.get("tarikhDaftar");
			    	this.context.put("sekarang",strdate);
			
			    	String noFail = (String)h.get("noFail");
				    this.context.put("nofail", noFail);  
				    idFail =(String) h.get("idFail");
				    Vector<?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanHTP(id_);
		        	Hashtable<?, ?> hpermohonan = (Hashtable<?, ?>) permohonan.get(0);
			    	//socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(hpermohonan.get("idagensi").toString()),disability);
			    	socAgensi = HTML.SelectAgensiByKementerian("socAgensi"
							, String.valueOf(h.get("idKementerian")), Utils.parseLong(String.valueOf(h.get("idagensi")))
							, disability, " style=\"width:400px\"");
	
			    	this.context.put("idsuburusan",idSubUrusan);
			    	this.context.put("idurusan",idUrusan);
			    	this.context.put("idagensi",hpermohonan.get("idagensi"));
			    	context.put("fail_id",idFail);
			    	context.put("permohonanInfo",hpermohonan);
			    	context.put("tarikhdaftar",h.get("tarikhDaftar"));
					pageMode = "4";
					
		    	}else if (submit.equals("pkpermohonankemaskini")) {	//frmPajakanKecilSemakPermohonan
			    	template_name = PATH+"frmJRPSemakPermohonan.jsp";	
		    		skrin = "1";
		    		//1 //2 //3
			    	//myLog.info("pkpermohonankemaskini:"+pageMode);
					String id_ = getParam("id_kemaskini");
			    	myLog.info("pkpermohonankemaskini:pageMode="+pageMode+",id="+id_);
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id_);
		        	FrmSemakan frmSemak = new FrmSemakan();
	
				    if(pageMode.equals("4")){
	
				    	if(id_==""){
				    		myLog.info("pkpermohonankemaskini::simpan");
				    		String idPermohonan=simpanPermohonan(session,1,"0");
				    		//System.out.println("FrmPajakanKecilA:pkpermohonankemaskini::idPermohonan:::"+idPermohonan);
				    		id_ = String.valueOf(idPermohonan);
				    		
				    	}else{
				    		Hashtable<String, Number> h = new Hashtable<String, Number>();
							h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
							h.put("id_kemaskini", 1);
	
						    FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);
							String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				        	//FrmSemakan frmSemak = new FrmSemakan();
				           	if(cbsemaks!=null){
				        		for (int i = 0; i < cbsemaks.length; i++) { 
				        			frmSemak = new FrmSemakan();
				        			//System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
				        			frmSemak.semakanTambah(cbsemaks[i], id);           
				        		}
				        	} 
						    
						    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());					    
				    		permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id_);
					    	pageMode = "2";
	
				    	}
				    	
					}else if(pageMode.equals("3")){					
						socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),"");
						this.context.put("socAgensi", socAgensi);
				    	myLog.info("pkpermohonankemaskini:id::30");
		
					}else if(pageMode.equals("simpan")){
				    	myLog.info("pkpermohonankemaskini:simpan");
						String[] cbsemaks = this.request.getParameterValues("cbsemaks");
					   	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
			        	Hashtable hash = null;
			        	Hashtable h = new Hashtable();
	//						h.put("id_Fail", Long.parseLong((String)permohonan.get("idpermohonan")));
						h.put("idPermohonan", Long.parseLong((String)permohonan.get("idpermohonan")));
						h.put("idFailN", idFailN);
						h.put("id_kemaskini", 1);
						h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
						h.put("NoFailLain", getParam("txtNoFailLain"));
						h.put("Tajuk", getParam("txttajuk"));
						myLog.info("fir hash kemaskini : " + h);
						FrmSenaraiFailPajakanKecilData.kemaskiniFail(h);
			    		int i=0;
			    		
			    		for (int ii = 0; ii < semakanSenarai.size(); ii++) {
			  	    	  	hash = (Hashtable)semakanSenarai.get(ii);
		        			if(frmSemak.isSemakan(id, (String)hash.get("id"))){
		        				i = i+1;	
		        			}
		        		}
			    	    if (i!=0) {
			    	    	frmSemak.semakanHapusByPermohonan(id);
			    	    }
			    					
						if(cbsemaks!=null){
							for (int iii = 0; iii < cbsemaks.length; iii++) { 
								frmSemak = new FrmSemakan();
								frmSemak.semakanTambah(cbsemaks[iii], id);           
							}
						} 	
						//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1");		    		
		    	    	pageMode = "2";
		    	    	
			    	}else if(pageMode.equals("kemaskini")){
			    	    myLog.info("pkpermohonankemaskini:kemaskini::id="+id_);
				    	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1");
						socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability+" style=\"width:400px\"");
		    	    	pageMode = "3";
			    	
			    	}else{				
				    	myLog.info("pkpermohonankemaskini:id::31");
						this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());			    
						socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability+" style=\"width:400px\"");
				    	pageMode = "2";
				    	myLog.info("pkpermohonankemaskini:id::32");
				    	
					}
					label(permohonan);
			    	//myLog.info("pkpermohonankemaskini:id::33");
	
					this.context.put("permohonanInfo", permohonan);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability+" style=\"width:400px\"");
			    	//myLog.info("pkpermohonankemaskini:id::34");
				    socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong(idUrusan),disability);
			    	this.context.put("socUrusan",socUrusan);
			    	context.put("socNegeri",socNegeri);
			    	context.put("socKementerian",socKementerian);
			    	context.put("socAgensi",socAgensi);

				    // Senarai semakan
			    	//Kemaskini pada 24/10/1010, senarai kemaskini mengikut negeri
			    	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak");
			    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
			    	//myLog.info("pkpermohonankemaskini:id::35");
				    this.context.put("senaraiSemakan",semakanSenarai );
	    	
		    	}else if(submit.equals("pksemakanseterus")) {
					template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";
		    		//skrin = "1";
		    		skrin = "2";
				    //disability = "disabled class=disabled";
	
			    	Hashtable<?, ?> hakmilikbangunan = null;
			    	String id_ = getParam("id_kemaskini");
			    	String idPermohonan = getParam("id_kemaskini");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
					
					label(permohonan);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
					Vector<?> senaraiHakmilik = null;
					senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(idPermohonan));
					
					viewHakmilikPermilik(senaraiHakmilik);
		    	
		    	}else if(submit.equals("deletePemilik")){
		    		template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";
		    		String idPemilikPB = getParam("idPemilikPB");
		    		FrmPajakanKecilHakmilikData.deletePemilik(idPemilikPB);
		    		//skrin = "1";
		    		skrin = "2";
		    		//disability = "disabled class=disabled";
	
			    	Hashtable<?, ?> hakmilikbangunan = null;
			    	String id_ = getParam("id_permohonan");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id_);
					
					label(permohonan);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				   	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
			    	
					Vector<?> senaraiHakmilik = null;
					senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
					viewHakmilikPermilik(senaraiHakmilik);
		    			    		
		    	}else if(submit.equals("deleteHakmilik")){
					template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";
		    		String idHakmilik = getParam("idHakmilik");
		    		FrmPajakanKecilHakmilikData.deleteHakmilik(idHakmilik);
		    		//skrin = "1";
		    		skrin = "2";
		    		//disability = "disabled class=disabled";
	
			    	Hashtable<?, ?> hakmilikbangunan = null;
			    	String id_ = getParam("id_permohonan");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id_);
					
					label(permohonan);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				   	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
			    	
					Vector<?> senaraiHakmilik = null;
					senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id_));
					viewHakmilikPermilik(senaraiHakmilik);
		    			    		
		    	}else if(submit.equals("pkpemilikkemaskini")){
		    		template_name = PATH+"frmJRPPemilikKemaskiniN1.jsp";
		    		myLog.info("pkpemilikkemaskini ::");
					//skrin = "1";	    		
		    		skrin = "2";
		    		disability = "disabled class=disabled";
		    		String socDaerah = "";
					String vid = getParam("fail");
					
					String idPermohonan = getParam("id_kemaskini");
					myLog.info("pkpemilikkemaskini 2"+getParam("id_kemaskini"));
					    
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
					Hashtable<?, ?> infohakmilik = FrmUtilData.getHakmilikUrusan(idPermohonan,vid); 
					ekptg.model.entities.Tblhtphakmilikurusan hakmilik = null;
					hakmilik = FrmPajakanKecilHakmilikData.getHakmilikInfo(Long.parseLong(vid));
				    this.context.put("hakmilik", hakmilik);		    
					this.context.put("infohakmilik", infohakmilik);
					this.context.put("permohonanInfo", permohonan);
					this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",(String)permohonan.get("idnegeri")));
			
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
			    	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),"", "onChange=\"doChangeNegeri1()\" ");
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",hakmilik.getIdDaerah(),"","");
	                String socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerah123("+id+")\" ");
	                String socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
	                this.context.put("socDaerah1",socDaerah1);
	                this.context.put("socMukim", socMukim);                
	
		            String noLot = HTML.SelectLot("noLot");
		            this.context.put("noLot", noLot);
		            this.context.put("nhk", vid);
			    	this.context.put("nhp", id);
					this.context.put("socDaerah",socDaerah);
					this.context.put("idnegeri",permohonan.get("idnegeri"));
						
					Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(idPermohonan);
					if(p.size()!=0){
						pemilik = getIPemilik().findPemilik(p.elementAt(0).getIdpihakberkepentingan());
					}
					this.context.put("pemilik",pemilik);	
					if ("doChangeNegeri1".equalsIgnoreCase(pageMode)) {
						String socNegeri2 = getParam("socNegeri");
						System.out.println("doChangeNegeri1"+socNegeri2);
						addPemilik2();
						socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri2) ,"","onChange=\"doChangeNegeri1()\" ");
						socDaerah = HTML.SelectDaerahByNegeri(socNegeri2,"socDaerah");
						this.context.put("socDaerah",socDaerah);
					}
					this.context.put("socNegeri",socNegeri);
	
			    }else if(submit.equals("pilihMukim")){
			    	template_name = PATH+"frmJRPPemilikKemaskiniN.jsp";
				    this.context.put("pageMode", 0);  
		    		//disability = "disabled class=disabled";
		    		String socDaerah = "";
		    		String socDaerah1 = "";
	                String socMukim = "";                   
	                String id_ = getParam("id_kemaskini");
	                //String socDaerah = getParam("socDaerah");
	                String idDaerah = getParam("socDaerah1");
	                Vector<?> senaraiHakmilik = null;
	
	                senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id_));
	                this.context.put("senaraihakmilik", senaraiHakmilik);
	                Hashtable<?, ?> permohonan1 = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id_);
	                this.context.put("permohonanInfo", permohonan1);
	                //this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
					this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",(String)permohonan1.get("idnegeri")));
	                String noLot = HTML.SelectLot("noLot");
	                this.context.put("noLot", noLot);
		                
	                socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan1.get("idnegeri").toString()),disability);
	                socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan1.get("idkementerian").toString()),disability);
	                socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan1.get("idagensi").toString()),disability);
	                socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan1.get("idnegeri"),"socDaerah1",Long.parseLong(idDaerah) ," onChange=\"doChangeDaerah123("+id_+")\"");
	
	                socDaerah = HTML.SelectDaerahByNegeri((String)permohonan1.get("idnegeri"),"socDaerah","" );
	                socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim");
	                this.context.put("socDaerah",socDaerah);
	                this.context.put("socDaerah1",socDaerah1);
	                this.context.put("idnegeri",permohonan1.get("idnegeri"));
	                socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim") ;
	                this.context.put("socMukim",socMukim);
		
			    }else if(submit.equals("tambahhakmilikpemilik")) {
					template_name = PATH+"frmJRPPemilikKemaskiniN.jsp";
					myLog.info("tambahhakmilikpemilik"+pageMode+" :"+getParam("id_kemaskini")+" :"+getParam("idpermohonan")+" :"+template_name);
					//skrin = "1";		    	
		    		skrin = "2";
					String idPermohonan = getParam("id_kemaskini");
				    disability = "disabled class=disabled";
					
				    String socDaerah = "";
				    String socMukim = "";
				    String socLuas = "";
					
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
					label(permohonan);
					
					//Maklumat Hakmilik
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
	                this.context.put("socDaerah1",HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerahHakmilik("+idPermohonan+")\" "));
					this.context.put("socMukim", HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim"));
					//modified by Rosli on 25/08/2010 senarai jenis hakmilik mengikut negeri
					//this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
					this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",permohonan.get("idnegeri").toString()));
		            this.context.put("noLot", HTML.SelectLot("noLot"));
			    	this.context.put("idnegeri", permohonan.get("idnegeri"));
	
					//Maklumat Pemilik
			    	this.context.put("socNegeri1",HTML.SelectNegeri("socNegeri1",Long.parseLong(permohonan.get("idnegeri").toString()),"", " onChange=\"doChangeDaerahHakmilik("+idPermohonan+")\" "));
					this.context.put("socDaerah",HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",0L,""));
					
					Vector<PihakBerkepentingan> p = getIPemilik().findPemilikByPermohonan(idPermohonan);
					if(p.size()!=0){
						pemilik = getIPemilik().findPemilik(p.elementAt(0).getIdpihakberkepentingan());
					}
					this.context.put("pemilik",pemilik);	
					
					//Maklumat Permohonan
					this.context.put("permohonanInfo", permohonan);
					
					if (pageMode.equalsIgnoreCase("dochangedaerah")) {
						myLog.info("dochangedaerah");
						String socNegeri1 = getParam("socNegeri1");
						String socDaerah1 = getParam("socDaerah1");
						String tempIdJenisHakmilik = getParam("socHakmilik");
						String tempIdMukim = getParam("socMukim");
						
						maklumatHakmilikPemilik();
						if(socDaerah1=="")
							socDaerah1="0";
						if(socNegeri1=="")
							socNegeri1="00";
						if(tempIdJenisHakmilik=="")
							tempIdJenisHakmilik="00";
						if(tempIdMukim=="")
							tempIdMukim="00";
						this.context.put("socDaerah1",HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1",Long.parseLong(socDaerah1), " onChange=\"doChangeDaerahHakmilik("+idPermohonan+")\" "));
						this.context.put("socMukim", HTML.SelectMukimByDaerah(socDaerah1, "socMukim",Long.parseLong(tempIdMukim),""));
						//this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",String.valueOf(permohonan.get("idnegeri")),Long.parseLong(tempIdJenisHakmilik),""));
						this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
	
						//pemilik
						this.context.put("socNegeri1",HTML.SelectNegeri("socNegeri1",Long.parseLong(socNegeri1),"", " onChange=\"doChangeDaerahHakmilik("+idPermohonan+")\" "));
						this.context.put("socDaerah",HTML.SelectDaerahByNegeri(socNegeri1, "socDaerah"));
					
					}else if(pageMode.equalsIgnoreCase("simpan")){
						template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";
						myLog.info("simpan :: "+template_name);
						simpanHakmilik(session,0);						
						intPB = simpanPB(session,0,idHakmilikUrusan);
							
						ekptg.model.entities.Tblhtppihakberkepentingan pihak = null;
						pihak = FrmPajakanKecilHakmilikData.getPemilikInfo(Long.parseLong(""+intPB));
				    	this.context.put("pihak", pihak);
				    	
				    	ekptg.model.entities.Tblhtphakmilikurusan hakmilik = null;
						hakmilik = FrmPajakanKecilHakmilikData.getHakmilikInfo(pihak.getIdHakmilikurusan());
				    	this.context.put("hakmilik", hakmilik);
				    	Vector<?> senaraiHakmilik = null;
						senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
						
						//fir implement 
						viewHakmilikPermilik(senaraiHakmilik);
						
	//			    	template_name = "app/htp/pajakankecil/online/frmJRPSenaraiHakmilikPemilik.jsp";
						socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",pihak.getIdDaerah(),disability);				    	    
			    	    pageMode = "2";
			    	    
					}				
			    	this.context.put("pageMode", pageMode);
				    //Negeri bagi Hakmilik
			    	this.context.put("socNegeri",socNegeri);

			    }else if (submit.equalsIgnoreCase("viewhakmilik")){	
					template_name = PATH+"frmJRPUpdateHakmilik.jsp";
					//skrin = "1";
		    		skrin = "2";
			    	String id = getParam("id_permohonan");
					String idHakmilikUrusan = getParam("idHakmilik");
				    disability = "disabled class=disabled";
					myLog.info("viewhakmilik :: id="+id);
					
				    String socDaerah = "";
				    String socMukim = "";
				    String socLuas = "";
					
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					label(permohonan);
					
					//hakmilik details
					Hashtable hakmilikinfo = FrmPajakanKecilHakmilikData.getHakmilikDetails(id);
					
					//Maklumat Hakmilik
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(hakmilikinfo.get("idNegeri").toString()),disability);
	                this.context.put("socDaerah1",HTML.SelectDaerahByNegeri((String)hakmilikinfo.get("idNegeri"),"socDaerah1",Long.parseLong(hakmilikinfo.get("idDaerah").toString()), disability));
					this.context.put("socMukim", HTML.SelectMukimByNegeri(hakmilikinfo.get("idNegeri").toString(), "socMukim", Long.parseLong(hakmilikinfo.get("idMukim").toString()), disability));
					this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik",Long.parseLong(hakmilikinfo.get("idJenisHakmilik").toString()),disability));
		            this.context.put("noLot", HTML.SelectLot("noLot",Long.parseLong(hakmilikinfo.get("idLot").toString()),disability));
		            this.context.put("noBangunan", hakmilikinfo.get("noBangunan"));
		            this.context.put("noTingkat", hakmilikinfo.get("noTingkat"));
		            this.context.put("noPetak",hakmilikinfo.get("noPetak"));
		            this.context.put("noLotvalue", hakmilikinfo.get("noLot"));
		            this.context.put("noHakmilik", hakmilikinfo.get("noHakmilik"));
		            this.context.put("idPermohonan", hakmilikinfo.get("idPermohonan"));
		            this.context.put("idHakmilikurusan", hakmilikinfo.get("idHakmilikUrusan"));
	
		            this.context.put("idnegeri", hakmilikinfo.get("idNegeri"));
					//Maklumat Permohonan
					this.context.put("permohonanInfo", permohonan);
			
			    	this.context.put("pagestatus", "view");
			    	this.context.put("classDis", disability);
					this.context.put("socNegeri", socNegeri);
		    	
			    }else if(submit.equalsIgnoreCase("kemaskinihakmilik")){
					template_name = PATH+"frmJRPUpdateHakmilik.jsp";
					myLog.info("kemaskinihakmilik"+template_name);
					//skrin = "1";		    	
		    		skrin = "2";
					String id = getParam("id_permohonan");
					String idHakmilikUrusan = getParam("idHakmilik");
				    disability = "";				          
			
			    	this.context.put("pagestatus", "kemaskini");
			    	this.context.put("classDis", disability);
			    	//pageMode ="0";
			    	if(pageMode.equals("0")){
			    		pageMode ="new";
			    		this.context.put("pagemode", "new");
			    	
			    	}else if(pageMode.equals("new")){
			    		pageMode ="0";
			    		updateHakmilikInfo();
			    		this.context.put("classDis", "disabled");
			    		disability = "disabled class=disabled";
			    	}
					
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					label(permohonan);
					
					//hakmilik details
					Hashtable hakmilikinfo = FrmPajakanKecilHakmilikData.getHakmilikDetails(id);
					//Maklumat Hakmilik
			    	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(hakmilikinfo.get("idNegeri").toString()),"disabled class=disabled");
	                this.context.put("socDaerah1",HTML.SelectDaerahByNegeri((String)hakmilikinfo.get("idNegeri"),"socDaerah1",Long.parseLong(hakmilikinfo.get("idDaerah").toString()), disability));
					this.context.put("socMukim", HTML.SelectMukimByNegeri(hakmilikinfo.get("idNegeri").toString(), "socMukim", Long.parseLong(hakmilikinfo.get("idMukim").toString()), disability));
					this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik",Long.parseLong(hakmilikinfo.get("idJenisHakmilik").toString()),disability));
		            this.context.put("noLot", HTML.SelectLot("noLot",Long.parseLong(hakmilikinfo.get("idLot").toString()),disability));
		            this.context.put("noLotvalue", hakmilikinfo.get("noLot"));
		            this.context.put("noHakmilik", hakmilikinfo.get("noHakmilik"));
		            this.context.put("noLotvalue", hakmilikinfo.get("noLot"));
		            this.context.put("noBangunan", hakmilikinfo.get("noBangunan"));
		            this.context.put("noTingkat", hakmilikinfo.get("noTingkat"));
		            this.context.put("noPetak", hakmilikinfo.get("noPetak"));
		            this.context.put("idPermohonan", hakmilikinfo.get("idPermohonan"));
		            this.context.put("pagemode", pageMode);
		            this.context.put("idnegeri", hakmilikinfo.get("idNegeri"));
					//Maklumat Permohonan
					this.context.put("permohonanInfo", permohonan);
					this.context.put("socNegeri", socNegeri);
			    
			    }else if(submit.equalsIgnoreCase("viewpemilik")){
			    	template_name = PATH+"frmJRPUpdatePihakKepentingan.jsp";
			    	myLog.info("viewpemilik");
			    	//skrin = "1";
		    		skrin = "2";
			    	String id = getParam("id_permohonan");
			    	String idPihakBerkepentingan = getParam("idPihakBerkepentingan");
			    	this.context.put("classDis", disability);
			    	PihakBerkepentingan pihak = FrmSenaraiFailPajakanKecilData.getPemilik(idPihakBerkepentingan);
			    	this.context.put("pihak",pihak);
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					label(permohonan);
					//hakmilik details
					Hashtable hakmilikinfo = FrmPajakanKecilHakmilikData.getHakmilikDetails(id);
					//Maklumat Hakmilik
			    	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(pihak.getIdNegeri()),disability);
					//modified by rosli on 23/08/2010 add change
			    	//socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(pihak.getIdNegeri()),disability,"onChange=\"doChangeNegeri2()\" ");
	                this.context.put("socDaerah",HTML.SelectDaerahByNegeri(pihak.getIdNegeri(),"socDaerah",Long.parseLong(pihak.getIdDaerah()), disability));
					this.context.put("noLotvalue", hakmilikinfo.get("noLot"));
		            this.context.put("noHakmilik", hakmilikinfo.get("noHakmilik"));
		            this.context.put("idPermohonan", hakmilikinfo.get("idPermohonan"));
		            this.context.put("pagemode", 0);
		            this.context.put("idnegeri", hakmilikinfo.get("idNegeri"));
					//Maklumat Permohonan
					this.context.put("permohonanInfo", permohonan);
				    this.context.put("socNegeri", socNegeri);
   
			    }else if(submit.equalsIgnoreCase("pkkemaskinipemilik")){
			    	template_name = PATH+"frmJRPUpdatePihakKepentingan.jsp";
			    	myLog.info("pkkemaskinipemilik ::");
			    	//skrin = "1";
		    		skrin = "2";
			    	String id = getParam("id_permohonan");
			    	String idPihakBerkepentingan = getParam("idPihakBerkepentingan");
			    	this.context.put("classDis", disability);
			    	if(pageMode.equals("0")){
			    		pageMode ="new";
			    		this.context.put("pagemode", "new");
			    		this.context.put("classDis", "");
			    		disability = "";
			    	
			    	}else if(pageMode.equals("new")){
			    		pageMode ="0";
			    		updatePemilikInfo();
			    		this.context.put("classDis", "disabled");
			    		disability = "disabled class=disabled";
			    	
			    	}
			    	PihakBerkepentingan pihak = FrmSenaraiFailPajakanKecilData.getPemilik(idPihakBerkepentingan);
			    	this.context.put("pihak",pihak);
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					label(permohonan);
					//hakmilik details
					Hashtable hakmilikinfo = FrmPajakanKecilHakmilikData.getHakmilikDetails(id);
					//Maklumat Hakmilik
			    	socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(pihak.getIdNegeri().toString()),"","onChange=\"doChangeNegeri2()\" ");
	                this.context.put("socDaerah",HTML.SelectDaerahByNegeri(pihak.getIdNegeri(),"socDaerah",Long.parseLong(pihak.getIdDaerah()), disability));
					this.context.put("noLotvalue", hakmilikinfo.get("noLot"));
		            this.context.put("noHakmilik", hakmilikinfo.get("noHakmilik"));
		            this.context.put("idPermohonan", hakmilikinfo.get("idPermohonan"));
		            this.context.put("pagemode", pageMode);
		            this.context.put("idnegeri", hakmilikinfo.get("idNegeri"));
					//Maklumat Permohonan
					this.context.put("permohonanInfo", permohonan);
					if ("doChangeNegeri2".equalsIgnoreCase(pageMode)) {
			    		this.context.put("pagemode", "new");
			    		this.context.put("classDis", "");
					
						String socNegeri2 = getParam("socNegeri");
						System.out.println("doChangeNegeri2"+socNegeri2);
						addPemilik2();
						socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri2) ,"","onChange=\"doChangeNegeri2()\" ");
						this.context.put("socDaerah",HTML.SelectDaerahByNegeri(socNegeri2,"socDaerah"));
		
					}
				    this.context.put("socNegeri", socNegeri);

			    }else if(submit.equals("pkpemiliktambah")) {
					template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";		    	
					myLog.info("pkpemiliktambah ::"+template_name);
					//skrin = "1";		    	
		    		skrin = "2";
				    disability = "disabled class=disabled";
	
				    String socDaerah = "";
				    String socMukim = "";
				    //String socNegeri = "";
				    String socLuas = "";
				    String idHakmilik = "";
					String id = getParam("id_kemaskini");
					idHakmilik = getParam("fail");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
					socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
					this.context.put("socMukim", socMukim);
					label(permohonan);
					this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",(String)permohonan.get("idnegeri")));
		
					if(pageMode.equals("0")){
			    	    System.out.println("FrmPajakanKecil:pemiliktambah::idHakmilik:::"+idHakmilik);
	
					}else if(pageMode.equals("1")){
			    	    myLog.info("FrmPajakanKecil:pkpemiliktambah(1)::idHakmilik:::"+idHakmilik);
			    		//String id = getParam("id_kemaskini");
						//Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
			    	    if("".equals(idHakmilik)){
							
							Long idHakmilikU = getParam("fail")==""?0L:Long.parseLong(getParam("fail"));
							if(FrmPajakanKecilHakmilikData.isHakmilikUrusan(idHakmilikU)){														
								intPB = simpanPB(session,0,0L);
								
							}else{							
								simpanHakmilik(session,0);	
								//fir - if tambah hak milik, id pb already have, just need to add hakmilikurusan and hakmilikurusanpb
								if(!idPihakBerkepentingan.equals("") && !idPihakBerkepentingan.equals(null)){									
									simpanHakMilikUrusanPB(idPihakBerkepentingan,String.valueOf(idHakmilikUrusan), session);
																	
								}else{									
									intPB = simpanPB(session,0,idHakmilikUrusan);
									simpanHakMilikUrusanPB(String.valueOf(intPB),String.valueOf(idHakmilikUrusan), session);
								
								}
								
							}
	
			    	    }else{
							//Long idHakmilikU = getParam("fail")==""?0L:Long.parseLong(getParam("fail"));
							intPB = simpanPB(session,0,Long.parseLong(idHakmilik));
			
						}
						
			    	    /*fir implement here to cater pemilik list */
				    	Vector<?> senaraiHakmilik = null;
						senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
						viewHakmilikPermilik(senaraiHakmilik);
			    	    pageMode = "2";
			    	    
					}else {						
					    //String idHakmilik = "";
					}
			    	this.context.put("pageMode", pageMode);			    	
			    	disability = "disabled class=disabled";
				    String vid = getParam("fail");
				    myLog.info("pkpemilikkemaskini 2"+getParam("id_kemaskini"));
				    this.context.put("permohonanInfo", permohonan);
					this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",(String)permohonan.get("idnegeri")));
	
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
					//by rosli ikut jsp
			    	socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah","");
					//socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerahalamat","");
					//Syiful
	                String socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1", " onChange=\"doChangeDaerah123("+id+")\" ");
	                //String socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
	                this.context.put("socDaerah1",socDaerah1);
	                this.context.put("socMukim", socMukim);                
	
	                String noLot = HTML.SelectLot("noLot");
	                this.context.put("noLot", noLot);
	
		    		//this.context.put("noL", LHM);
		    		this.context.put("nhk", vid);
		    		this.context.put("nhp", id);
					this.context.put("socDaerah",socDaerah);
					this.context.put("idnegeri",permohonan.get("idnegeri"));
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	this.context.put("idnegeri", permohonan.get("idnegeri"));			    
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
					Vector senaraiHakmilik = null;
					senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(id));
					viewHakmilikPermilik(senaraiHakmilik);
					this.context.put("socDaerah",socDaerah);
	
			    }else if(submit.equals("pkpemilikseterus")) {
			    	//skrin = "2";
			    	skrin = "3";
			    	Hashtable<?, ?> hakmilikbangunan = null;
				    disability = "disabled='disabled' class='disabled'";
				    String socDaerah = "";
				    String socLuas = "";				    
					String id = getParam("id_kemaskini");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);		    	
					hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
					this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);		
					if(hakmilikbangunan==null){
						template_name = PATH+"frmJRPMaklumatBaru.jsp";
						myLog.info("hakmilikbangunan ="+hakmilikbangunan);
						socLuas = HTML.SelectLuas("socLuas");
						socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
						String socPegawai = HTML.SelectPegawai("socPegawai");
						this.context.put("socPegawai",socPegawai);
				    	pageMode = "3";
				    	
					}else if(hakmilikbangunan.equals(id)){
						
					}else{
						template_name = PATH+"frmJRPMaklumat.jsp";
						myLog.info("Else ::"+template_name);
						disability = "disabled class=disabled";
						socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
						socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
						socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
						String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
						this.context.put("socPegawai",socPegawai);
						pageMode = "4";
						
					}	
				    this.context.put("socLuas",socLuas);
				    this.context.put("socDaerah",socDaerah);
				    this.context.put("socNegeri",socNegeri);
			    	myLog.info("pkpemilikseterus ::"+template_name);
			    
			    }else if(submit.equals("viewSewaan")){
					template_name = PATH+"frmJRPMaklumat.jsp";
					myLog.info("viewSewaan ::"+template_name);
					skrin = "2";	    	
			    	String permohonanId = getParam("id_kemaskini");
			    	String hakMilikBangunanId = getParam("idHakmilikBangunan");
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(permohonanId);
			    	Hashtable hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(permohonanId);
			    	disability = "";
					String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
					String socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
					this.context.put("socPegawai",socPegawai);
					pageMode = "3";
					this.context.put("socLuas",socLuas);
					this.context.put("socDaerah",socDaerah);
					this.context.put("pageMode",pageMode);
			    	
			    }else if(submit.equals("KemaskiniSewaan")){
					template_name = PATH+"frmJRPMaklumat.jsp";
					myLog.info("KemaskiniSewaan ::"+template_name);
			    	disability = "disabled class=disabled";
			    	skrin = "3";
			    	String permohonanId = getParam("id_kemaskini");
			    	String hakMilikBangunanId = getParam("idHakmilikBangunan");
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(permohonanId);
			    	Hashtable hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(permohonanId);
			    	disability = "";
					String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
					String socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
					this.context.put("socPegawai",socPegawai);
					pageMode = "5";
					this.context.put("socLuas",socLuas);
					this.context.put("socDaerah",socDaerah);
					this.context.put("pageMode",pageMode);
			    	
			    }else if(submit.equals("updateSewaanSewaan")){
					template_name = "app/htp/pajakankecil/online/frmJRPMaklumat.jsp";
			    	myLog.info("updateSewaanSewaan ::"+template_name);
			    	skrin = "3";	
			    	String permohonanId = getParam("id_kemaskini");
			    	String hakMilikBangunanId = getParam("idHakmilikBangunan");
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(permohonanId);
			    	int month = 0;
					int year = 0;
					String  strmonth  = getParam("txtbulan");
					String stryear = getParam("txttahun");
					if(!strmonth.equals(""))
						month = Integer.parseInt(strmonth);
					if(!stryear.equals(""))
						year = Integer.parseInt(stryear);
			    			    	
			    	Tblhtphakmilikbangunan s = new Tblhtphakmilikbangunan();
			    	s.setIdHakmilikbangunan(Long.parseLong(hakMilikBangunanId));
					s.setIdPermohonan(Long.parseLong(getParam("id_kemaskini")));
					s.setAlamat1(getParam("txtalamat1"));
					s.setAlamat2(getParam("txtalamat2"));
					s.setAlamat3(getParam("txtalamat3"));
					s.setPoskod(getParam("txtposkod"));
				    s.setIdLuas(Long.parseLong(getParam("socLuas")));
				    s.setLuas(getParam("txtluas"));
				    s.setIdDaerah(Long.parseLong(getParam("socDaerah")));
				    s.setIdNegeri(Long.parseLong(permohonan.get("idnegeri").toString()));
				    s.setSewaBulanan(Utils.RemoveSymbol(getParam("txtsewa")));
				    s.setBulan(month);
				    s.setTahun(year);
				    s.setIdMasuk(Long.parseLong((String)session.getAttribute("_ekptg_user_id")));
				    s.setTarikhMasuk(new Date());
				    FrmPajakanKecilMaklumatData.kemaskini(s,getParam("txddari"),getParam("txdhingga"));
			    	
			    	disability = "disabled class=disabled";
			    	
			    	Hashtable hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(permohonanId);
			    	disability = "disabled class=disabled";
					String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
					String socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
					this.context.put("socPegawai",socPegawai);
					pageMode = "4";
					this.context.put("socLuas",socLuas);
					this.context.put("socDaerah",socDaerah);
					this.context.put("pageMode",pageMode);
					this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
			    
			    }else if(submit.equals("pkmaklumat")){			    	
					template_name = PATH+"frmJRPMaklumatBaru.jsp";
					skrin = "2";		    	
			    	myLog.info("pkmaklumat:submit="+submit);
					String id = getParam("id_kemaskini");
				    //readability = "readonly";
				    disability = "disabled class=disabled";
				    String socDaerah = "";				
				    Hashtable<?, ?> hakmilikbangunan = null;
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
				    socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah");
				    this.context.put("socDaerah",socDaerah);
				    //String socNegeri = "";
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					if(pageMode.equals("0")){
						//template_name = "app/htp/pajakankecil/online/frmPajakanKecilMaklumat.jsp";
						int month = 0;
						int year = 0;
						String  strmonth  = getParam("txtbulan");
						String stryear = getParam("txttahun");
						if(!strmonth.equals(""))
							month = Integer.parseInt(strmonth);
						if(!stryear.equals(""))
							year = Integer.parseInt(stryear);
						
			    	    myLog.info("pkmaklumat:0::"+pageMode);
			    	    
			    	    Tblhtphakmilikbangunan s = new Tblhtphakmilikbangunan();			    
						s.setIdPermohonan(Long.parseLong(getParam("id_kemaskini")));
						s.setAlamat1(getParam("txtalamat1"));
						s.setAlamat2(getParam("txtalamat2"));
						s.setAlamat3(getParam("txtalamat3"));
						s.setPoskod(getParam("txtposkod"));
						s.setIdLuas(Long.parseLong(getParam("socLuas")));
						s.setLuas(getParam("txtluas"));
						s.setIdDaerah(Long.parseLong(getParam("socDaerah")));
						s.setIdNegeri(Long.parseLong(permohonan.get("idnegeri").toString()));
						s.setSewaBulanan(getParam("txtsewa"));
						s.setBulan(month);
						s.setTahun(year);
						s.setIdMasuk(Long.parseLong((String)session.getAttribute("_ekptg_user_id")));
						s.setTarikhMasuk(new Date());
					    FrmPajakanKecilMaklumatData.add(s,getParam("txddari"),getParam("txdhingga"));	
					    
					    Hashtable data = null;
					    data = new Hashtable();			
					    data.put("id_Permohonan", (String)getParam("id_kemaskini"));
					    data.put("id_Dokumen", "1");
					    data.put("minit_Arahan", "SILA AMBIL TINDAKAN (PENAMATAN TEMPOH PENYEWAAN BANGUNAN)");
					    //data.put("id_Pegawai_Ygmengarah", (String)session.getAttribute("_ekptg_user_id"));
					    //data.put("id_Pegawai_Ygmenerima", (String)session.getAttribute("_ekptg_user_id"));
					    data.put("userid", (String)session.getAttribute("_ekptg_user_id"));
					    data.put("id_Pegawai_Ygmengarah", "1");
					    data.put("id_Pegawai_Ygmenerima", "1");
					    data.put("status_Tindakan", "0");
					    data.put("tarikh_Minit_Arahan", (String)getParam("txdhingga"));
					    data.put("tempoh", "150");
						FrmUtilData.tambahPeringatan(data);					
				    	hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
				    	//String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),disability);
				    	String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,disability);
				    	//socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(hakmilikbangunan.get("idnegeri").toString()),disability);
				    	socDaerah = HTML.SelectDaerahByNegeri((String)hakmilikbangunan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),disability);
				    	
				    	//this.context.put("socLuas",socLuas);
				    	//this.context.put("socNegeri",socNegeri);
				    	this.context.put("socDaerah",socDaerah);
						this.context.put("socPegawai",socPegawai);
						pageMode = "4";
				    	
					}else if(pageMode.equals("1")){
			    	    myLog.info("pkmaklumat:1::"+pageMode);
			    	    
					}else if(pageMode.equals("3")){
			    	    myLog.info("pkmaklumat:3::"+pageMode);
						hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
						this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
						
						socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
				    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
				    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
	
						String socLuas = HTML.SelectLuas("socLuas",Long.parseLong(hakmilikbangunan.get("idluas").toString()),null);
						socDaerah = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah",Long.parseLong(hakmilikbangunan.get("iddaerah").toString()),"");
						String socPegawai = HTML.SelectPegawai("socPegawai",Long.parseLong(hakmilikbangunan.get("idpegawai").toString()) ,"");
						this.context.put("socPegawai",socPegawai);
						this.context.put("pageMode","4");
					    this.context.put("socLuas",socLuas);
	
					}else if(pageMode.equals("4")){
						myLog.info("pkmaklumat:4::"+pageMode);
			    	    Hashtable<String, Comparable> s = new Hashtable<String, Comparable>();
			    	    
						hakmilikbangunan = FrmPajakanKecilMaklumatData.getHakmilikBangunanInfo(id);
						s.put("idhakmilikbangunan",Long.parseLong((String)hakmilikbangunan.get("idhakmilikbangunan")));
					    s.put("idpermohonan",Long.parseLong(getParam("id_kemaskini")));
						s.put("alamat1",getParam("txtalamat1"));
						s.put("alamat2",getParam("txtalamat2"));
						s.put("alamat3",getParam("txtalamat3"));
						s.put("poskod",getParam("txtposkod"));
					    s.put("idluas",Long.parseLong(getParam("socLuas")));
					    s.put("luas",getParam("txtluas"));
					    s.put("iddaerah",Long.parseLong(getParam("socDaerah")));
					    s.put("idnegeri",Long.parseLong(permohonan.get("idnegeri").toString()));
					    s.put("sewa",Double.parseDouble(getParam("txtsewa")));
					    //s.setTarikhMula(new Date(getParam("txddari")));
					    //s.setTarikhTamat(new Date(getParam("txdhingga")));
					    s.put("idpegawai",Long.parseLong(getParam("socPegawai")));
					    s.put("idkemaskini",Long.parseLong("1"));
					    s.put("tarikhkemaskini",new Date());
					   	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
				    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
	
					}
					this.context.put("hakmilikbangunaninfo", hakmilikbangunan);
		
			    }else if(submit.equals("pkmaklumatseterus")) {
			    	template_name = PATH+"frmJRPSemakanPKP.jsp";	
			    	myLog.info("pkmaklumatseterus ::"+template_name);
			    	disability = "disabled class=disabled";
	
					String id = getParam("id_kemaskini");
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
			    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1"+permohonan.get("idnegeri"));
			    	this.context.put("senaraiSemakan",semakanSenarai );
			
			    }else if(submit.equals("semakanPKP"))    {
			    	String id = getParam("id_kemaskini");
			    	Hashtable<String, String> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
			    	template_name = PATH+"frmJRPSemakanPKP.jsp";
			    	//skrin = "3";
			    	skrin = "4";
//		    	    myLog.info("semakanPKP:id::"+id +",idpermohonan:"+getParam("idpermohonan")+",pageMode:"+pageMode);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
			    	
		        	FrmSemakan frmSemak = new FrmSemakan();
		        	semakanSenarai = frmSemak.getSenaraiSemakanAttach("htpjrp",id);
					//this.context.put("senaraiSemak", semakanSenarai);
	
			    	if(pageMode.equals("simpan")){
						String[] cbsemaks = this.request.getParameterValues("cbsemaks");
					   	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1"+permohonan.get("idnegeri"));
			        	Hashtable hash = null;
			    		int i=0;
			    		
			    		for (int ii = 0; ii < semakanSenarai.size(); ii++) {
			  	    	  	hash = (Hashtable)semakanSenarai.get(ii);
		        			if(frmSemak.isSemakan(id, (String)hash.get("id"))){
		        				i = i+1;	
		        			}
		        		}
			    	    if (i!=0) {
			    	    	frmSemak.semakanHapusByPermohonan(id);
			    	    }
			    					
						if(cbsemaks!=null){
							for (int iii = 0; iii < cbsemaks.length; iii++) { 
								frmSemak = new FrmSemakan();
								frmSemak.semakanTambah(cbsemaks[iii], id);           
							}
						} 	
							semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1"+permohonan.get("idnegeri"));		    		
		    	    	pageMode = "2";
		    	    	
			    	}else if(pageMode.equals("kemaskini")){
			    	    myLog.info("kemaskini:id::"+id);
				    	semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak1"+permohonan.get("idnegeri"));
		    	    	pageMode = "3";
		    	    	
			    	}else{
			    		int i=0;
			        	Hashtable<String,String> hash = null;
			    		for (int ii = 0; ii < semakanSenarai.size(); ii++) {
			  	    	  	hash = (Hashtable<String,String>)semakanSenarai.get(ii);
		        			if(frmSemak.isSemakan(id, (String)hash.get("id"))){
		        				i = i+1;	
		        				}
		        		}
			    	    if (i==0) {
			    	    	pageMode = "0";
			    	    }else{
			    	    	pageMode = "2";
			    	    }
//			    		myLog.info("iiiiiiiiiiiiiiiii:"+pageMode);
			    	
			    	}
			    	label(permohonan);
				    this.context.put("permohonanInfo", permohonan);
			    	this.context.put("senaraiSemak",semakanSenarai );
				    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
	
				    //shiqa - pengesahan 25062020
			    }else if(submit.equalsIgnoreCase("pengesahan")){
			    	String id = getParam("id_kemaskini");
			    	
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					myLog.info("pengesahan ::idpermohonan="+FrmJRPSenaraiPermohonanData.getPermohonanInfo(id));	
					//getSemakanPerakuanPembelian();
					FrmJRPSenaraiPermohonanData logic = new FrmJRPSenaraiPermohonanData();
					
					//24092020
					logic.getIdNegeriKJPByUserId(userId);
					listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
	    			Hashtable hashList = (Hashtable) listDetailKJP.get(0);
	    			idNegeriPemohon = hashList.get("idNegeri").toString();
	    			namaPemohon = (String) hashList.get("namaPemohon");
	    			userId = (String) hashList.get("userId");

	    			this.context.put("ListdetailKJP", listDetailKJP);
	    			this.context.put("namaPemohon", namaPemohon);
	    			this.context.put("userId", userId);
//	    			myLog.info("namaPemohon:: " + namaPemohon);
					
					skrin = "4";
					String semakMode="";
					String statusSemasa="1";
//					myLog.info("idSubUrusanxxxxx====="+idSubUrusan);
					
					if (getIOnline().isHantar(Long.parseLong(String.valueOf(permohonan.get("idsuburusan"))),
							Long.parseLong(String.valueOf(permohonan.get("idpermohonan"))),
							Long.parseLong(String.valueOf(permohonan.get("idfail"))), "4"))
					{
						semakMode = "xupdate";			
					}else{
						
						myLog.info("idpermohonan::"+FrmJRPSenaraiPermohonanData.getPermohonanInfo(id));
						semakMode = "update";
						if(getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idsuburusan"))),
								Long.parseLong(String.valueOf(permohonan.get("idpermohonan"))),
								Long.parseLong(String.valueOf(permohonan.get("idfail"))), "1")){
							statusSemasa = "1";	
		 

						
						}else if(getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idsuburusan"))),
								Long.parseLong(String.valueOf(permohonan.get("idpermohonan"))),
								Long.parseLong(String.valueOf(permohonan.get("idfail"))), "2")){
							statusSemasa = "2";


							
						}else if(getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idsuburusan"))),
								Long.parseLong(String.valueOf(permohonan.get("idpermohonan"))),
								Long.parseLong(String.valueOf(permohonan.get("idfail"))), "3")){
							statusSemasa = "3";	

							
						}
					}
					
					FrmSemakan frmSemak = new FrmSemakan();
		        	semakanSenarai = frmSemak.getSenaraiSemakanAttach("htpjrp",id);
					this.context.put("senaraiSemak", semakanSenarai);
	
					//myLog.info("semakMode="+semakMode);
					context.put("semakMode", semakMode);
					myLog.info("statusSemasa:"+statusSemasa);
					context.put("statussemasa", statusSemasa);
					context.put("fail",(String.valueOf(permohonan.get("idfail"))));
					context.put("buttonSend", "disabled");
					context.put("idpermohonan", Long.parseLong(String.valueOf(permohonan.get("idpermohonan"))));

					template_name = PATH+"frmJRPSemakanPKP.jsp";
					
					//shiqa - simpanpengesahan 25062020
				}  else if(submit.equalsIgnoreCase("simpanpengesahan2")){
					String id = getParam("idpermohonan");
			    	Hashtable<?, ?> permohonan2 = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					myLog.info("simpanpengesahan1 ::id_permohonan="+permohonan2);	
			    	myLog.info("simpanpengesahan2 ::idpermohonan="+FrmJRPSenaraiPermohonanData.getPermohonanInfo(id));	
			    	myLog.info("id_kementerian="+uk.getAgensi().getKementerian().getIdKementerian());
					String semakMode="";
					/*
					 * 1 untuk status - Pra-daftar
					 * 2 untuk status - Tindakan Penyemak 
					 * 3 untuk status - Tindakan Pelulus
					 * 4 untuk status - Permohonan Online (Pengesahan) 
					 * 5  untuk status - Penerimaan Permohonan
					*/
					String langkah2 = "2";
					EmailConfig ec = new EmailConfig();

					//myLog.info("from="+email.FROM);
					String emelSubjek = ec.tajukSemakan+"Jawatankuasa Ruang Pejabat";
					String kandungan = "";
					if(idJawatan.equals("20")||idJawatan.equals("24")){
						myLog.info("BACA SINIIIII============"+uk.getAgensi().getKementerian().getIdKementerian());
						
						langkah2 = "2";
						
						kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));
		    			
						if(!getEmelSemak().checkEmail(userId).equals(""))
							getIHTP().getErrorHTML("[ONLINE-HTP JRP] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

						ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
								, "9"
								, String.valueOf(String.valueOf(permohonan2.get("idkementerian")))
								, emelSubjek, kandungan);

					}else if (idJawatan.equals("9")){
						langkah2 = "3";				
						
						kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));
		    			
						if(!getEmelSemak().checkEmail(userId).equals(""))
							getIHTP().getErrorHTML("[ONLINE-HTP JRP] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

						ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
								, "4"
								, String.valueOf(String.valueOf(permohonan2.get("idkementerian")))
								, emelSubjek, kandungan);
									
					}else if (idJawatan.equals("4")){
						langkah2 = "4";
						emelSubjek = ec.tajukHantarPermohonan + " Jawatankuasa Ruang Pejabat";
								
						kandungan = getEmelSemak().setKandungan(String.valueOf(String.valueOf(permohonan2.get("tajukfail")))
									,String.valueOf(String.valueOf(permohonan2.get("kementerian")))
									,String.valueOf(String.valueOf(permohonan2.get("noP"))));
		    			
						if(!getEmelSemak().checkEmail(userId).equals(""))
							getIHTP().getErrorHTML("[ONLINE-HTP JRP] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

						ec.hantarPermohonan(getEmelSemak().checkEmail(userId), "(HTP)HQPenggunaPajakanKecil", emelSubjek, kandungan);

//						ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
//								, "4"
//								, String.valueOf(String.valueOf(permohonan2.get("idkementerian")))
//								, emelSubjek, kandungan);
										
					}
					Hashtable<?, ?> permohonan3 = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					String idPermohonan = String.valueOf(permohonan3.get("idpermohonan"));


					/*
					subUrusanStatusFail = new Tblrujsuburusanstatusfail();
					subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
					
					subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
					subUrusanStatusFail.setAktif("0");*/
				
					Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
					myLog.info("Langkah=="+langkah2);
					long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,idSubUrusan,"=");
					rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
//					myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan);
					rsusf.setIdFail(Long.parseLong(idFail));
					rsusf.setIdSuburusanstatusfail(Long.parseLong(idSubUrusan));
					rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
					rsusf.setUrl("-");
					simpanPengesahan(rsusf,langkah2);
//					myLog.info("userId=="+userId);
					rsusf.setIdMasuk(Long.parseLong(userId));

					
					if(getIOnline().isHantar(Long.parseLong(String.valueOf(permohonan3.get("idsuburusan"))),
							Long.parseLong(String.valueOf(permohonan3.get("idpermohonan"))),
							Long.parseLong(String.valueOf(permohonan3.get("idfail"))),langkah)){
						semakMode = "xupdate";			
					}else{
						semakMode = "update";
					}
//					myLog.info("selectedTab=======");
					context.put("semakMode", semakMode);
					skrin = "4";
					//context.put("selectedTab", 4);
					template_name = PATH+"frmJRPSemakanPKP.jsp";	
					//return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txtarikhkeputusan")));
									
				
				}else if(submit.equals("pksemakanpkpseterus")) {
			    	template_name = PATH+"frmJRPDeraf.jsp";	
			    	myLog.info("pksemakanpkpseterus ::"+template_name);
			    	//skrin = "4";		    	
			    	skrin = "5";		    	
				    disability = "disabled class=disabled";
			    	Vector<?> senarais = null;
			    	String id = getParam("id_kemaskini");
			    	senarais = FrmSewaanDerafData.getSenarai(id);
					Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
					socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
					socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);
		   		    this.context.put("senaraideraf",senarais );
		   	
//			    }else if("pkderafseterus".equals(submit)){	
//			    	template_name = "app/htp/pajakankecil/online/frmPajakanKecilSenaraiSurat.jsp";
//			    	myLog.info("pkderafseterus ::"+template_name);
//			    	
		    	}else if(submit.equals("simpanpermohonan")){	
			    	template_name = PATH+"frmJRPPendaftaran.jsp";
			    	myLog.info("simpanpermohonan ::"+template_name);
			    	String strOperation = "success";
				    String idnegeri = getParam("socNegeri");
				    String idagensi = getParam("socAgensi");
				    idFail = getParam("id_kemaskini");
			    	//myLog.info("simpanpermohonan:id::"+idFail);			    
					//long longIdFail = ekptg.helpers.DB.getNextID("TBLPFDFAIL_SEQ");
					//simpanFail(session,longIdFail);
					//int idPermohonan=46;
					String idPermohonan = simpanPermohonan(session,0,idFail);
					simpanStatus(session,Long.parseLong(idPermohonan),Long.parseLong(idFail),"1");
				    this.context.put("idPermohonan",idPermohonan);
					String[] cbsemaks = this.request.getParameterValues("cbsemaks");
		        	FrmSemakan frmSemak = new FrmSemakan();
		        	//frmSemak.semakanHapusByPermohonan(id);
		        	if(cbsemaks!=null){
		        		for (int i = 0; i < cbsemaks.length; i++) { 
		        			frmSemak = new FrmSemakan();
		        			//System.out.println("FrmPajakanKecilA:pkfailbaru|pageMode["+pageMode+"::cbsemaks:::"+cbsemaks[i]);
		        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan)); 
		        			
		        		}
		        	}
		        	//after saved
					Hashtable permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(String.valueOf(idPermohonan));
				    this.context.put("permohonanInfo", permohonan);
				    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
				    
				    socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
			    	socKementerian = HTML.SelectKementerian("socKementerian",Long.parseLong(permohonan.get("idkementerian").toString()),disability);
			    	socAgensi = HTML.SelectAgensi("socAgensi",Long.parseLong(permohonan.get("idagensi").toString()),disability);	
			    	socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),disability);
			    	this.context.put("socUrusan",socUrusan);
			    
				    // Senarai semakan
				    // 24/10/2010, senarai semak ditukar kepada "frmSewaanSemak"
			    	//semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh","aktif");
				    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+permohonan.get("idnegeri"));
				    this.context.put("senaraiSemakan",semakanSenarai );
			    	pageMode = "2";
		    	
		    	}else if(submit.equals("pkpemiliktambah2")){
		    		template_name = PATH+"frmJRPSenaraiHakmilikPemilik2.jsp";
		    		myLog.info("pkpemiliktambah2 ::"+template_name);
		    		//skrin = "1";	    		
		    		skrin = "2";
		    		addPemilik();
				
		    	}else if(submit.equals("addHakmilikToPemilik")){
		    		template_name = PATH+"frmJRPAddHakmilik.jsp";
		    		myLog.info("addHakmilikToPemilik ::"+template_name);
		    		//skrin = "1";	    		
		    		skrin = "2";	    		
		    		String idPermohonan = getParam("id_permohonan");
		    		String idPemilik = getParam("id_pemilik");
		    		disability = "disabled class=disabled";
		    		Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(idPermohonan);
		    		if(permohonan.isEmpty())
		    			throw new Exception("[PASSING DATA ERROR] Data PERMOHONAN not Found");
		    		
		    		socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(permohonan.get("idnegeri").toString()),disability);
		    		String socMukim = HTML.SelectMukimByNegeri((String)permohonan.get("idnegeri"), "socMukim");
		    		String socDaerah1 = HTML.SelectDaerahByNegeri((String)permohonan.get("idnegeri"),"socDaerah1");
					this.context.put("socMukim", socMukim);
					this.context.put("socDaerah1", socDaerah1);
					this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik"));
					//this.context.put("socHakmilik",UtilHTML.SelectJenisHakmilikMengikutNegeri("socHakmilik",(String)permohonan.get("idnegeri")));
					this.context.put("idnegeri", permohonan.get("idnegeri"));
		    		Tblhtppihakberkepentingan pihakKepentingan = FrmPajakanKecilHakmilikData.getPemilikInfo(idPemilik);
		    		String noLot = HTML.SelectLot("noLot");
		            this.context.put("noLot", noLot);
		    		if(pihakKepentingan == null)
		    			throw new Exception("[PASSING DATA ERROR] Data Pihak Berkepentingan not Found");
		    		String socNegeri1 = HTML.SelectNegeri("socNegeri1",pihakKepentingan.getIdNegeri(),disability);
		    		String socDaerah = HTML.SelectDaerah("socDaerah", pihakKepentingan.getIdDaerah(), disability);//HTML.SelectDaerahByNegeri(permohonan.get("idnegeri").toString(),"socDaerah",pihakKepentingan.getIdDaerah(),disability);
		    		context.put("pihakKepentigan", pihakKepentingan);
		    		this.context.put("socNegeri1",socNegeri1);
		    		this.context.put("socDaerah",socDaerah);
		    	
		    	}else if(submit.equals("doChanges")){
		    		template_name = PATH+"frmJRPSenaraiFail.jsp";
		    		myLog.info("doChanges ::"+template_name);
		    		String nofail = getParam("nofail");
				 	idDaerah = getParam("socDaerah");
				 	idMukim = getParam("socMukim");
				 	maklumatCarian();
		    		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian),"onChange=\"doChangeKementerianCarian()\" style=\"width:400\"");
				    this.context.put("socKementerian",socKementerian);		    		
		    		//senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail.trim());    		
		    		//this.context.put("SenaraiFail", senaraiFail);  
		    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail.trim(),txtTajukCarian,idNegeri,idKementerian,idAgensi,"");      			    		
		    		this.context.put("txtNoFail", nofail);  
		    		this.context.put("txtTajukCarian", txtTajukCarian);	    		
		    		isCarian = "ya";		
		    		setupPage(session,action,senaraiFail);
		    	
			    }else if(submit.equals("tindakan")) {
			    	String pathTemp = PATH;
			    	String PATH = "app/htp/pajakankecil/online/";
			    	template_name = pathTemp+"frmJRPTindakanSenarai.jsp";	
			    	myLog.info("tindakan:"+getParam("id_kemaskini"));
					Hashtable hPergerakan = null; 		
			    	Vector<?> senaraiTindakan = null;
			    	skrin = "6";		    	
				    jenisAkses = "write";
				   	String id = getParam("id_kemaskini");
			    	String idPermohonan = getParam("id_kemaskini");
			
			    	Hashtable<?, ?> permohonan = FrmJRPSenaraiPermohonanData.getPermohonanInfo(id);
			    	idFail = String.valueOf(permohonan.get("idfail"));
			    	myLog.info("fail="+permohonan.get("idfail"));
//			    	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
//			    	this.context.put("senaraiTindakan",senaraiTindakan);
			    	boolean disableFungsi = false;
			    	Hashtable hFungsi = null;
	    			myLog.info(getParam("roleparam"));
	    			/**
	    			 * Maklumat Status dan Langkah
	    			 * 		#Nama Status					Langkah
	    			 *  TINDAKAN PENGGUNA/PENYEDIA (NEGERI)		81		  
	    			 *	TINDAKAN PEGAWAI (NEGERI)				82		  
	    			 *	TINDAKAN PENGARAH (NEGERI)				83		  
	    			 *	TINDAKAN PENGGUNA/PENYEDIA				84	  
	    			 *	TINDAKAN PEGAWAI						85		  
	    			 *	TINDAKAN PENGARAH						86	  
	    			 *	DILULUSKAN HQ							87
	    			*/
		    		if(portal_role.contains("PenggunaNegeri")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"81")!=null){
		    				disableFungsi = true;
		    			}
		    		}else if(portal_role.contains("PegawaiNegeri")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"82")!=null){
		    				disableFungsi = true;
		    			}
		    		}else if(portal_role.contains("PengarahNegeri")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"83")!=null){
		    				disableFungsi = true;
		    			}
		    		}else if(portal_role.contains("HQPengguna")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"84")!=null){
		    				disableFungsi = true;
		    			}
		    		}else if(portal_role.contains("HQPegawai")||portal_role.contains("KetuaPenolong")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"85")!=null){
		    				disableFungsi = true;
		    			}
		    		}else if(portal_role.contains("HQPengarah")){
		    			if(getIHTP().getInfoSelesaiPermohonan(idPermohonan,"86")!=null){
		    				disableFungsi = true;
		    			}
		    		}

	    			myLog.info(portal_role);
	    			myLog.info(disableFungsi);
			    	this.context.put("disableFungsi",disableFungsi);
			
			    	if(pageMode.equals("tambah")){
				    	template_name = PATH+"frmJRPTindakan.jsp";	
						this.context.put("readonly", "");
						this.context.put("disabled", "");
						this.context.put("mode", "new");
						
			    	}else if(pageMode.equals("simpan")){
			    		Hashtable hInsert = null;
			    		if(portal_role.contains("PenggunaNegeri")){
			    			hInsert = new Hashtable(); 			
			    			String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"81");
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", idSusulan);
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);
			    					
			    		}else if(portal_role.contains("PegawaiNegeri")){
			    			hInsert = new Hashtable(); 			
			    			String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"83");
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", idSusulan);
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);
			    			
			    		}else if(portal_role.contains("PengarahNegeri")){
			    			String idSusulan = getISusulan().simpan(setSusulanValues(idPermohonan));
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"84");
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", idSusulan);
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);
			    		
			    		}
				    	//kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"99");
						this.context.put("readonly", "readonly=\"readonly\"");
						this.context.put("disabled", "disabled");
						this.context.put("mode", "view");
						//hPergerakan = getIHTP().getInfoTamatSelesaiPermohonan(idPermohonan);				
						//public Hashtable getInfoSelesaiPermohonan(String idPermohonan,String langkah)throws Exception {		
						this.context.put("hPergerakan", hPergerakan);
						senaraiTindakan = new Vector();
				    	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
				    	this.context.put("senaraiTindakan",senaraiTindakan);
						
				    	//shiqa - simpanpengarah 25062020
			    	}else if(pageMode.equals("simpanpengesahan")){
			    		Hashtable hInsert = null;
			    		if(portal_role.contains("PenggunaNegeri")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"82");		    					
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", getParam("idsusulan"));
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);
							
			    		}else if(portal_role.contains("PegawaiNegeri")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"83");
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", getParam("idsusulan"));
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);

			    		}else if(portal_role.contains("PengarahNegeri")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"84");		    		
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", getParam("idsusulan"));
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);
			    		
			    		}else if(portal_role.contains("HQPengguna")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"85");		    					
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", getParam("idsusulan"));
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);

			    		}else if(portal_role.contains("HQPegawai")||portal_role.contains("KetuaPenolong")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"86");
			    			hInsert = new Hashtable(); 			
			    			hInsert.put("idStatusFail", idStatusFail);
			    			hInsert.put("idSusulan", getParam("idsusulan"));
			    			hInsert.put("idMasuk", userId);
							getISusulan().simpanSusulanStatusFail(hInsert);

			    		}else if(portal_role.contains("HQPengarah")){
			    			String idStatusFail = kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"87");		    		
//			    			hInsert = new Hashtable(); 			
//			    			hInsert.put("idStatusFail", idStatusFail);
//			    			hInsert.put("idSusulan", getParam("idsusulan"));
//			    			hInsert.put("idMasuk", userID);
//							getISusulan().simpanSusulanStatusFail(hInsert);
			    		
			    		}
			    		//kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"99");
						this.context.put("readonly", "readonly=\"readonly\"");
						this.context.put("disabled", "disabled");
						this.context.put("mode", "view");
				    	this.context.put("hPergerakan", hPergerakan);
						senaraiTindakan = new Vector();
				    	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
				    	this.context.put("senaraiTindakan",senaraiTindakan);
											
			    	}else if(pageMode.equals("view")){
				    	template_name = PATH+"frmJRPTindakan.jsp";	
				    	String idSusulan = getParam("idsusulan");
				    	//String idSusulanStatus = getParam("idsusulanstatus");
				    	this.context.put("readonly", "readonly=\"readonly\"");
						this.context.put("disabled", "disabled");
						this.context.put("mode", "view");
						setSusulan(idPermohonan, idSusulan);

			    	}else if(pageMode.equals("kemaskini")){
				    	template_name = PATH+"frmJRPTindakan.jsp";	
				    	String idSusulan = getParam("idsusulan");
				    	//String idSusulanStatus = getParam("idsusulanstatus");
						this.context.put("readonly", "");
						this.context.put("disabled", "");
						this.context.put("mode", "update");			
						setSusulan(idPermohonan, idSusulan);
			    	
			    	}else if(pageMode.equals("dokemaskini")){
				    	template_name = PATH+"frmJRPTindakan.jsp";	
				    	String idSusulan = getParam("idsusulan");
		    			String idSusulanStatus = getISusulan().kemaskini(setSusulanStatusValues(idPermohonan));
						setSusulan(idPermohonan, idSusulan);

						this.context.put("readonly", "readonly=\"readonly\"");
						this.context.put("disabled", "disabled");
						this.context.put("mode", "view");
					
			    	}else if (pageMode.equals("hapus")){
				    	String idSusulan = getParam("idsusulan");
				    	getISusulan().hapus(idSusulan);
						senaraiTindakan = new Vector();
				    	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
				    	this.context.put("senaraiTindakan",senaraiTindakan);
			    	
			    	}else{
						senaraiTindakan = new Vector();
				    	senaraiTindakan = getISusulan().getMaklumat(idPermohonan);
				    	this.context.put("senaraiTindakan",senaraiTindakan);
	    	
			    	}

		    		
		    	}
	    	
		    }else{ // !=submit
		    	myLog.info("1st else: Langkah::"+langkah);
			 	idDaerah = getParam("socDaerah");
			 	idMukim = getParam("socMukim");
			 	myLog.info("id_kementerian = "+id_kementerian);
			 	maklumatCarian();
			    if(langkah.equals("0->0")){ //carian
			    	template_name = PATH+"frmJRPSenaraiFail.jsp";
			    	myLog.info("FrmPajakanKecilA: !=submit::0->0");
			    	noFail = getParam("nofail");
			    	if(getParam("page_").equals("tajuk")){
			    		noFail = getParam("txtNoFailSek");
			    	}
			    	senaraiFail = FrmSenaraiFailPajakanKecilData.getList(noFail);
					setupPage(session,action,senaraiFail);

			    }else if(langkah.equals("0->1")){
				    template_name = PATH+"frmJRPPendaftaran.jsp";
			    	myLog.info("FrmPajakanKecilA: !=submit::0->1");	    		
			    	String strdate = "";
			    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
					this.context.put("socSeksyen","3");
					socUrusan = HTML.SelectUrusan("socUrusan",Long.parseLong("14"),"disabled class=disabled");
				    this.context.put("socUrusan",socUrusan);	
				    //perjanjian 44
				    this.context.put("idsuburusan","9920122");
				    this.context.put("idurusan","14");			     	
			    	this.context.put("sekarang",strdate);
				    this.context.put("pageMode", "0");  
				    this.context.put("nofail", "");  
				    semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemak"+idNegeri);
				    this.context.put("senaraiSemakan",semakanSenarai );	
				    socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");
				    socAgensi = HTML.SelectAgensiByKementerian("socAgensi","",Long.parseLong("1"),"");
				
				    this.context.put("socNegeri",socNegeri);
					this.context.put("socKementerian",socKementerian);
					this.context.put("socAgensi",socAgensi);
					this.context.put("pageMode", 0);  
		
			    }else{
			    	myLog.info("else");	 
			    	template_name = PATH+"frmJRPSenaraiFail.jsp";
					    senaraiFail = getIHTPFail().getSenaraiFail(null
			    			, null, null
			    			, id_kementerian, null
			    			, null, null, null
			    			, idUrusan
			    			,null);
//					    senaraiFail =  getIHTPPKFail().getSenaraiFail(
//					    		null, noFail.trim(), txtTajukCarian
//					    		, idKementerian, idAgensi
//					    		, idNegeri, idDaerah, idMukim
//					    		, idUrusan, "", ""
//					    		, "", "", false );
			    	//senaraiFail = FrmSenaraiFailPajakanKecilData.getList("",userId,"");
					this.context.put("SenaraiFail", senaraiFail);
					socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),"disabled class=disabled","onChange=\"doChangeKementerianCarian()\" style=\"width:400\"");
					this.context.put("socKementerian",socKementerian);
					setupPage(session,action,senaraiFail);
		
			    }

		    } //end of ""==submit 
	
			this.context.put("javaScriptLampiran", getDoc().javascriptUpload("", "paparLampiran", "idDokumen",session,"jrp"));

			// Carian
		    this.context.put("txtNoFail",noFail);
		    this.context.put("txtTajuk",txtTajukCarian);	   		    	
			this.context.put("pageMode", pageMode);  
			this.context.put("idFailN", idFailN);
			this.context.put("idPihakBerkepentingan", idPihakBerkepentingan);
			this.context.put("skrin", skrin);
			this.context.put("idKemaskiniPaging", idKemaskiniPaging);
			this.context.put("idUser", session.getAttribute("_ekptg_user_id"));
			this.context.put("iscarian", isCarian);		
			myLog.info("template_name="+template_name);
			return template_name;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("[KJP JAWATANKUASA RUANG PEJABAT] SILA LOGIN SEMULA");
			
		}			
				
	}
	
	
	 
	private void maklumatHakmilikPemilik() throws NumberFormatException, Exception {
		String noLot = getParam("noLot");
		String socHakmilik = getParam("socHakmilik");
		if(noLot=="")
			noLot="0";
		if(socHakmilik=="")
			socHakmilik="0";
		
		this.context.put("noLot", HTML.SelectLot("noLot", Long.parseLong(noLot), ""));
		this.context.put("socHakmilik",HTML.SelectJenisHakmilik("socHakmilik", Long.parseLong(socHakmilik), ""));
		this.context.put("norujukan", getParam("txtnorujukan"));
        this.context.put("nama", getParam("txtnama"));
        this.context.put("alamat1", getParam("txtalamat1"));
        this.context.put("alamat2", getParam("txtalamat2"));
        this.context.put("alamat3", getParam("txtalamat3"));
        this.context.put("poskod", getParam("txtposkod"));
        this.context.put("nolot", getParam("txtnolot"));
        this.context.put("nohakmilik", getParam("txtnohakmilik"));
		
	}


	private void updatePemilikInfo() {
		String idpihakberkepentingan = getParam("idPihakBerkepentingan");
		String nama = getParam("txtnama");
		String alamat1 = getParam("txtalamat1");
		String alamat2 = getParam("txtalamat2");
		String alamat3 = getParam("txtalamat3");
		String poskod = getParam("txtposkod");
		String socDaerah= getParam("socDaerah");
		String socNegeri = getParam("socNegeri");
		String norujukan = getParam("txtnorujukan");
		
		PihakBerkepentingan pihak = new PihakBerkepentingan();
		pihak.setIdpihakberkepentingan(idpihakberkepentingan);
		pihak.setNama(nama);
		pihak.setAlamat1(alamat1);
		pihak.setAlamat2(alamat2);
		pihak.setAlamat3(alamat3);
		pihak.setPoskod(poskod);
		pihak.setIdDaerah(socDaerah);
		pihak.setIdNegeri(socNegeri);
		pihak.setNoRujukan(norujukan);
		FrmSenaraiFailPajakanKecilData.updatePemilik(pihak);
	}


	private void updateHakmilikInfo() {
		  String idHakmilikUrusan = getParam("idHakmilik");
		  String idNegeri = getParam("socNegeri");
		  String idDaerah = getParam("socDaerah1");
		  String idHakmilik = getParam("socHakmilik");
		  String noLot = getParam("txtnolot");
		  String noHakmilik = getParam("txtnohakmilik");
		  String idMukim  = getParam("socMukim");
		  String idLot = getParam("noLot");
		  String noBanguan = getParam("noBangunan");
		  String noTingkat = getParam("noTingkat");
		  String noPetak = getParam("noPetak");
		
		  
		  HakmilikUrusan urusan = new HakmilikUrusan();
		  urusan.setIdhakmilikurusan(idHakmilikUrusan);
		  urusan.setIdNegeri(idNegeri);
		  urusan.setIdDaerah(idDaerah);
		  urusan.setIdMukim(idMukim);
		  urusan.setNolot(noLot);
		  urusan.setNohakmilik(noHakmilik);
		  urusan.setIdHakmilik(idHakmilik);
		  urusan.setIdLot(idLot);
		  urusan.setNoBangunan(noBanguan);
		  urusan.setNoTingkat(noTingkat);
		  urusan.setNoPetak(noPetak);
		  urusan.setIdKemaskini(userId);
		  FrmSenaraiFailPajakanKecilData.updateHakmilik(urusan);
		
	}

	private void simpanFail(String idfail) throws Exception {		  
		Hashtable<String, String> h = null;
		h = new Hashtable<String, String>();
		int idFaharasat = 1;
		int idKementerian = 0;
		int idLokasi= 1;  
		int idNegeri = 0;
		int idSeksyen = 3;
		int idStatus = 7;/**AKTIF*/
		int idTarafkeselamatan = 1; /** TERBUKA*/
		String catatan = "TIADA";
		String flagFail = "1";
		String kodKementerianMampu = "";
		String kodNegeriMampu = "";
		String noFailroot = "TIADA";
		String txdBukafail = "";
		 
		try {			
		
			idNegeri = Integer.parseInt(getParam("socNegeri"));
			idKementerian = Integer.parseInt(getParam("sockementerian"));
			  
			txdBukafail = getParam("txdTarikhBukaFail"); 
			kodNegeriMampu = FrmSenaraiFailPajakanKecilData.getNegeriByMampu(idNegeri);
			kodKementerianMampu = FrmSenaraiFailPajakanKecilData.getKementerianByMampu(idNegeri);
			 
			int fileSeq = 0;
			  
			String noFail = "JKPTG/101/847/";
			if (!("".equals(getParam("txtNoFailSek")))) {
				noFail = getParam("txtNoFailSek");
			}else{
				fileSeq = File.getSeqNo(Integer.parseInt(getParam("socSeksyen"))
									,Integer.parseInt(idUrusan)
									,idKementerian
									,idNegeri);
				noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;			  
			
			}
			  
			h.put("catatan",catatan);
			h.put("flag_Fail",flagFail);
			h.put("id_Faharasat",String.valueOf(idFaharasat));
			h.put("id_Fail", String.valueOf(idfail));
			h.put("id_Kementerian",String.valueOf(idKementerian));
			h.put("id_Lokasi", String.valueOf(idLokasi)); 
			h.put("id_Masuk", userId);
			h.put("id_Negeri", String.valueOf(idNegeri));
			h.put("id_Seksyen", String.valueOf(idSeksyen));
			h.put("id_Status",String.valueOf(idStatus));
			h.put("id_Suburusan", idSubUrusan);
			h.put("id_Tarafkeselamatan", String.valueOf(idTarafkeselamatan));
			h.put("id_Urusan", idUrusan);
			h.put("no_Fail", noFail);
			h.put("no_Failroot", noFailroot);
			h.put("tajuk_Fail", getParam("txttajuk"));
			h.put("tarikh_Bukafail",txdBukafail);		  
			  
			FrmUtilData.simpanFailPK(h);
		
		} catch (Exception e) {
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.getMessage().toString());
		}	
		
	}

	private void updatePermohonan(HttpSession session)throws Exception{
		String id = getParam("id_kemaskini");
		//String kod_cara_bayar = getParam("kod_cara_bayar");
		    //String keterangan = getParam("keterangan");
		    //String id_kemaskini = getParam("id_kemaskini");
		    //Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		    //Date tarikh_kemaskini = new Date();
		    //CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		    	//	keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	}

	private void deleteCaraBayar(HttpSession session) throws Exception {
	    String carabayar_id = getParam("id_carabayar");
	    //CaraBayarData.delete(carabayar_id);
	}
	  
	private String simpanPermohonan(HttpSession session,int i,String idfail)throws Exception {
		Hashtable data = null;
		String noPermohonan = "TIADA";
	    String noPerserahan = "TIADA";
		data = new Hashtable();
	    if(i==0){
	    	data.put("id_Fail", idfail);
	    }else{
	    	data.put("id_Fail", (String)getParam("fail"));
	    }
	    data.put("no_Permohonan",noPermohonan);
		data.put("no_Perserahan",noPerserahan); 
	    data.put("tarikh_SuratKJP", getParam("txdTarikhSuratKJP"));
	    data.put("tarikh_Terima", getParam("txdTarikhSuratKJP"));
	    data.put("tajuk", getParam("txttajuk"));
	    if(i==0){
	    	data.put("id_Agensi", Integer.parseInt(getParam("socAgensi")));
	    }else{
	    	data.put("id_Agensi", Integer.parseInt(getParam("idagensi")));
	    }
	    data.put("id_Jenistanah", 1);
	    data.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
	    data.put("no_Failkjp", "TIADA");
	    data.put("no_Faillain", getParam("txtNoFailLain"));
	    data.put("tarikh_Agihan", getParam("txdTarikhBukaFail"));
	    data.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
	    data.put("id_Masuk", userId);
	    /**KJP Tiada Pejabat
	    //idPejabat = 0
	    //data.put("idPejabat", String.valueOf(session.getAttribute("_ekptg_user_unit"))); */
	    data.put("idPejabat", "0");

	    return FrmUtilData.simpanPermohonanHTP(data);
	    	
	} 
	  	
	private void simpanHakmilik(HttpSession session,int i) throws Exception {
		  Hashtable h = new Hashtable();
			String strdate = "";
	    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
	    	//this.context.put("sekarang",strdate);
	   
		  if(i == 0){
			  //fail baru
				h.put("idPermohonan", getParam("id_kemaskini"));
				h.put("idNegeri", getParam("idnegeri"));
				h.put("socNegeri1", getParam("socNegeri1"));
				h.put("socDaerah", getParam("socDaerah1"));
				h.put("socDaerah1", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socHakmilik", getParam("socHakmilik"));
				//h.put("socMukim", 1);
				//h.put("socJenisHakmilik", 1);
				h.put("NoHakmilik", getParam("txtnohakmilik"));
				h.put("NoLot", getParam("txtnolot"));
				h.put("socLot", getParam("noLot"));
				h.put("noBangunan", getParam("noBangunan"));
				h.put("noTingkat", getParam("noTingkat"));
				h.put("noPetak", getParam("noPetak"));
				//h.put("socLot", 1);
				h.put("TarikhTerima", strdate);
				h.put("CukaiPertama", "0.00");
				h.put("Lokasi", "TIADA");
				h.put("socLuas", "1");
				h.put("NoPelan", "TIADA");
				h.put("socRizab", 1);
				h.put("socKategori", 1);
				h.put("Syarat", "TIADA");
				h.put("TarikhLuput", strdate);
				h.put("CukaiSemasa", "0.00");
				h.put("Luas", "0.00");
				h.put("TarikhWarta", strdate);
				h.put("NoWarta","TIADA");
				h.put("NoPU", "TIADA");
				h.put("NoSyit", "TIADA");
				h.put("Sekatan", "TIADA");
				idHakmilikUrusan = FrmPajakanKecilHakmilikData.simpan(h);
			}else{
				//kemaskini fail
//				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
				h.put("idHakmilikurusan", Integer.parseInt(getParam("idHakmilikurusan")));
//				h.put("socNegeri", getParam("socNegeri"));
				h.put("socDaerah", getParam("socDaerah"));
				h.put("socMukim", getParam("socMukim"));
				h.put("socJenisHakmilik", getParam("socJenisHakmilik"));
				h.put("NoHakmilik", getParam("txtNoHakmilik"));
				h.put("NoLot", getParam("txtNoLot"));
				h.put("socLot", getParam("noLot"));
				h.put("TarikhTerima", getParam("txdTarikhTerima"));
				h.put("CukaiPertama", getParam("txtCukaiPertama"));
				h.put("Lokasi", getParam("txtLokasi"));
				h.put("socLuas", getParam("socLuas"));
				h.put("NoPelan", getParam("txtNoPelan"));
				h.put("socRizab", getParam("socRizab"));
				h.put("socKategori", getParam("socKategori"));
				h.put("Syarat", getParam("txtSyarat"));
				h.put("TarikhLuput", getParam("txdTarikhLuput"));
				h.put("CukaiSemasa", getParam("txtCukaiSemasa"));
				h.put("Luas", getParam("txtLuas"));
				h.put("TarikhWarta", getParam("txdTarikhWarta"));
				h.put("NoWarta", getParam("txtNoWarta"));
				h.put("NoPU", getParam("txtNoPU"));
				h.put("NoSyit", getParam("txtNoSyit"));
				h.put("Sekatan", getParam("txtSekatan"));
				FrmGadaianPenHamilikData.update(h);
			}
	  }
	  
	  private void simpanPemilik(HttpSession session,int i,Long l) throws Exception {
		  Hashtable h= new Hashtable();
		  String userID = session.getAttribute("_ekptg_user_id").toString();
		  
			if(i==0){
				//fail baru
				//Hashtable h = new Hashtable();
				if(l!=0L)
					h.put("idHakmilikurusan", Integer.parseInt(""+l));
				else	
					h.put("idHakmilikurusan", Integer.parseInt(getParam("txtnohakmilik")));
				
				h.put("noRujukan", getParam("txtnorujukan"));
				h.put("nama", getParam("txtnama"));
				h.put("alamat1", getParam("txtalamat1"));
				h.put("alamat2", getParam("txtalamat2"));
				h.put("alamat3", getParam("txtalamat3"));
				h.put("poskod", getParam("txtposkod"));
				h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
				h.put("idNegeri", Integer.parseInt(getParam("idnegeri")));
				h.put("noTelefon", "TIADA");
				h.put("noFax", "TIADA");
				h.put("noPerserahan","TIADA");
				h.put("userID",userID);
				
				FrmPajakanKecilHakmilikData.simpanPemilik(h);
			}else{
				//kemaskini fail
				//Hashtable h = new Hashtable();
				h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
				h.put("nama", getParam("txtNama"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
				h.put("noTelefon", getParam("txtNoTelefon"));
				h.put("noFax", getParam("txtNoFax"));
				h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
				h.put("noPerserahan", getParam("txtNoPerserahan"));
				h.put("userID",userID);
				FrmGadaianHakmilikData.update(h);
			}
		  //FrmGadaianHakmilikData.simpan(data);

	  }
	  
      private String simpanPB(HttpSession session,int i,Long l) throws Exception {
    	  Hashtable h= new Hashtable();
    	  if(i==0){
    		  //fail baru
    		  //Hashtable h = new Hashtable();
    		  //if(l!=0L)
    		  h.put("idHakmilikurusan", l);
    		  //else      
    		  //          h.put("idHakmilikurusan", Integer.parseInt(getParam("fail")));
              h.put("noRujukan", getParam("txtnorujukan"));
              h.put("nama", getParam("txtnama"));
              h.put("alamat1", getParam("txtalamat1"));
              h.put("alamat2", getParam("txtalamat2"));
              h.put("alamat3", getParam("txtalamat3"));
              h.put("poskod", getParam("txtposkod"));
              //modified by rosli 2009/12/28 socdaerah --> socDaerahalamat,idnegeri -->idNegerialamat
              //h.put("idDaerah", Integer.parseInt(getParam("socDaerahalamat")));
              h.put("idDaerah", Integer.parseInt(getParam("socDaerah")));
              h.put("idNegeri", Integer.parseInt(getParam("socNegeri1")));
              h.put("noTelefon", "TIADA");
              h.put("noFax", "TIADA");
              h.put("noPerserahan","TIADA");
              return FrmUtilData.simpanPB(h);
                    
    	  }else{
    		  //kemaskini fail
    		  //Hashtable h = new Hashtable();
    		  h.put("idPihakberkepentingan", Integer.parseInt(getParam("idPihakberkepentingan")));
    		  h.put("nama", getParam("txtNama"));
    		  h.put("alamat1", getParam("txtAlamat1"));
    		  h.put("alamat2", getParam("txtAlamat2"));
    		  h.put("alamat3", getParam("txtAlamat3"));
    		  h.put("poskod", getParam("txtPoskod"));
    		  h.put("idDaerah", Integer.parseInt(getParam("socADaerah")));
    		  h.put("noTelefon", getParam("txtNoTelefon"));
    		  h.put("noFax", getParam("txtNoFax"));
    		  h.put("idBebanan", Integer.parseInt(getParam("idBebanan")));
              h.put("noPerserahan", getParam("txtNoPerserahan"));
              h.put("uid", Integer.parseInt((String)session.getAttribute("_ekptg_user_id")));
              return FrmUtilData.updatePB(h);
    	  
    	  }
          //FrmGadaianHakmilikData.simpan(data);
      }

	    public String getJarakBulan(Date dari,Date hingga) {
	        //RequestContext rc = RequestContext.getCurrentInstance();
	        //Date hingga = (Date)rc.getPageFlowScope().get("hingga");
	        //Date dari = (Date)this.getTxdDateDari().getValue();
	        Long diff, DayDur, MonthDur;
	            if(hingga != null && dari != null) {
	                diff = hingga.getTime() - dari.getTime();
	                DayDur = diff / (1000*60*60*24);
	                //MonthDur = (DayDur % 365) / 29; // baki daripada tahun dalam bentuk bulan
	                 MonthDur = DayDur / 29;
	                System.out.println("Duration in Month : "+MonthDur);
	            }else {
	                System.out.println("date hingga or dari is null on JarakBulan");
	                MonthDur = null;
	            }
	        return ""+MonthDur;
	    }	


	    private void label(Hashtable permohonan){
			String labelNegeri = (String)permohonan.get("negeri");
			String labelKementerian = (String)permohonan.get("kementerian");
			String labelAgensi = (String)permohonan.get("agensi");
			String labelTajuk = (String)permohonan.get("tujuan");
			String labelNoFail = (String)permohonan.get("fail");
			String labelNoFailLain = (String)permohonan.get("rujukanlain");
			String labelTarikhSuratKJP = (String)permohonan.get("rtterima");
			String labelTarikhBukaFail = (String)permohonan.get("tdaftar");

			this.context.put("labelNegeri", labelNegeri);
			this.context.put("labelKementerian", labelKementerian);
			this.context.put("labelAgensi", labelAgensi);
			this.context.put("labelTajuk", labelTajuk);
			this.context.put("labelNoFail", labelNoFail);
			this.context.put("labelNoFailLain", labelNoFailLain);
			this.context.put("labelTarikhSuratKJP", labelTarikhSuratKJP);
			this.context.put("labelTarikhBukaFail", labelTarikhBukaFail);

	    }
	    
		  public void simpanStatus(HttpSession session,Long idPermohonan,Long idFail,String langkah)
		  	throws Exception{
			  Long setIdSuburusanstatus = 0L;
			  
			  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			  Long idMasuk = Long.parseLong((String)session.getAttribute("_ekptg_user_id"));
			  Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
			  s.setIdPermohonan(idPermohonan);
			  s.setIdFail(idFail);
			  s.setIdSuburusanstatus(setIdSuburusanstatus);
			  s.setAktif("1");
			  s.setIdMasuk(idMasuk);
			  s.setUrl("TIADA");
			  s.setTarikhMasuk(new Date());
			  FrmUtilData.simpanStatusPermohonan(s);
			  //public void simpanStatusPermohonan(Tblrujsuburusanstatusfail s,Connection conn ) throws Exception{
		  
		  }
	
		  public void simpanTajuk(String idFail)throws Exception{
			  String tajuk = getParam("txttajuk");
			  FrmPajakanKecilHakmilikData.simpanTajuk(tajuk,idFail);
		  }
		  private void addPemilik2() throws Exception{
				String idpermohonan = getParam("id_kemaskini");
			  	String idHakmilik = getParam("fail");
			  	String noRujukan = getParam("txtnorujukan");
			  	String name = getParam("txtnama");
			  	String alamat1 = getParam("txtalamat1");
			  	String alamat2 = getParam("txtalamat2");
			 	String alamat3 = getParam("txtalamat3");
			  	String postcode = getParam("txtposkod");

			  	
			  	this.context.put("", idpermohonan);
			  	this.context.put("", idHakmilik);
			  	this.context.put("noRujukan", noRujukan);
			  	this.context.put("name", name);
			  	this.context.put("alamat1", alamat1);
			  	this.context.put("alamat2", alamat2);
			  	this.context.put("alamat3", alamat3);
			  	this.context.put("postcode", postcode);

		  }
		  private void addPemilik() throws Exception{
			String idpermohonan = getParam("id_kemaskini");
		  	String idHakmilik = getParam("fail");
		  	String noRujukan = getParam("txtnorujukan");
		  	String name = getParam("txtnama");
		  	String alamat1 = getParam("txtalamat1");
		  	String alamat2 = getParam("txtalamat2");
		 	String alamat3 = getParam("txtalamat3");
		  	String postcode = getParam("txtposkod");
		  	String district =getParam("socDaerah");
		  	String state = getParam("socNegeri");
		  
		  	Hashtable h= new Hashtable();
		  	h.put("idHakmilikurusan", idHakmilik);
			h.put("noRujukan", noRujukan);
			h.put("nama", name);
			h.put("alamat1", alamat1);
			h.put("alamat2", alamat2);
			h.put("alamat3", alamat3);
			h.put("poskod", postcode);
			h.put("idDaerah", Integer.parseInt(district));
			h.put("idNegeri", Integer.parseInt(state));
			h.put("noTelefon", "TIADA");
			h.put("noFax", "TIADA");
			h.put("noPerserahan","TIADA");
			h.put("userID",userId);
			
			FrmPajakanKecilHakmilikData.simpanPemilik(h);
			
			Vector<?> senaraiHakmilik = null;
			senaraiHakmilik = FrmPajakanKecilHakmilikData.getSenaraiHakmilik(Long.parseLong(idpermohonan));
		

			//fir
			viewHakmilikPermilik(senaraiHakmilik);
		  }
		  
		  public void simpanHakMilikUrusanPB(String idPihakBerkepentingan, String idHakmilikUrusan, HttpSession session) throws Exception{
			  try{
				  FrmPajakanKecilHakmilikData.simpanHakMilikUrusanPB(idPihakBerkepentingan, idHakmilikUrusan, session);
				  
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  
		  }
		  
		  private void viewHakmilikPermilik(Vector senaraiHakmilik)throws Exception{
			  Hashtable hash = null;
			  Hashtable hashInfoPemilikandHakmilik = new Hashtable() ;
			  Vector vInfoPemilikandHakmilik = new Vector();
			  Iterator i =  senaraiHakmilik.iterator();
			  Vector vsenaraiPemilik = new Vector();
				
			  while(i.hasNext()){
				  hash = (Hashtable)i.next();
				  String idHakmilikurusan = hash.get("idhakmilikurusan").toString();
				  String nolot = hash.get("nolot").toString();
				  String nohakmilik = hash.get("nohakmilik").toString();
				  String namanegeri = hash.get("namanegeri").toString();
				  String namadaerah = hash.get("namadaerah").toString();
				  String namamukim = hash.get("namamukim").toString();
				  String keterangan = hash.get("keterangan").toString();
				  String kodjenishakmilik = hash.get("kodjenishakmilik").toString();
					
				  HakmilikUrusan hakMilikUrusan = new HakmilikUrusan();
				  hakMilikUrusan.setIdhakmilikurusan(idHakmilikurusan);
				  hakMilikUrusan.setNolot(nolot);
				  hakMilikUrusan.setNohakmilik(nohakmilik);
				  hakMilikUrusan.setNamadaerah(namadaerah);
				  hakMilikUrusan.setNamamukim(namamukim);
				  hakMilikUrusan.setNamanegeri(namanegeri);
				  hakMilikUrusan.setKeterangan(keterangan);
				  hakMilikUrusan.setKodjenishakmilik(kodjenishakmilik);
				  myLog.info("idHakmilikurusan hash : " + idHakmilikurusan);
				  hakMilikUrusan = FrmPajakanKecilHakmilikData.getSenaraiPemilikbyIdHakmilikUrusan(hakMilikUrusan,idHakmilikurusan);

//					hashInfoPemilikandHakmilik.put("idhakmilikurusan", idHakmilikurusan);
//					hashInfoPemilikandHakmilik.put("nolot", nolot);
//					hashInfoPemilikandHakmilik.put("nohakmilik", nohakmilik);
//					hashInfoPemilikandHakmilik.put("namanegeri", namanegeri);
//					hashInfoPemilikandHakmilik.put("namadaerah", namadaerah);
//					hashInfoPemilikandHakmilik.put("namamukim", namamukim);
//					hashInfoPemilikandHakmilik.put("keterangan", keterangan);
//					hashInfoPemilikandHakmilik.put("kodjenishakmilik", kodjenishakmilik);						
//					hashInfoPemilikandHakmilik.put("senaraiPemilik", vsenaraiPemilik);
				  vInfoPemilikandHakmilik.addElement(hakMilikUrusan);
					
			  }
			  myLog.info("fir vector hakmilik n pb : " + vInfoPemilikandHakmilik);
			  this.context.put("senaraihakmilik", vInfoPemilikandHakmilik);
			  
		  }
		  
		  private void maklumatCarian() throws Exception{
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),null,"onChange=doChangeKementerianCarian();");
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(idDaerah),null, "onChange=\"doChangeKementerianCarian()\"");
			socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim) , "");
		    //socKementerian = HTML.SelectKementerian("socKementerian", "onChange=\"doChangeKementerianCarian() \" style=\"width:400\"");
		    socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian),"disabled class=disabled","onChange=\"doChangeKementerianCarian()\" style=\"width:400\"");
		 	socAgensi = HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "","style=\"width:400\"");
		  
		    this.context.put("socNegeri",socNegeri);
		    this.context.put("socDaerah", socDaerah);
			this.context.put("socMukim", socMukim);
		    this.context.put("socKementerian",socKementerian);
		    this.context.put("socAgensi",socAgensi);
		    
		  }
		  // Skrin Tindakan
		  private String kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan
				  ,String idSubUrusan,String langkah)throws Exception {
				 try {
					subUrusanStatusFail = new Tblrujsuburusanstatusfail();
					subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
					myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan);
					subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
					subUrusanStatusFail.setAktif("0");
				
					Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
					long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
					subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
					subUrusanStatusFailN.setAktif("1");
					myLog.info("setIdSuburusanstatus="+setIdSuburusanstatus+",getParam(\"txtarikhkeputusan\")="+getParam("txtarikhkeputusan"));
					//subUrusanStatusFailN.setUrl(getParam("txtcatatan") == null ? "" : getParam("txtcatatan"));
					subUrusanStatusFailN.setUrl("-");
					subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
					myLog.info("getParam(\"txdTarikhTerima\")="+getParam("txtarikhkeputusan"));
					subUrusanStatusFailN.setTarikhMasuk(new Date(getParam("txtarikhkeputusan")));
					return String.valueOf(getStatus().kemaskiniSimpanStatusAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txtarikhkeputusan")));
					
				} catch (Exception e) {
					throw new Exception("Ralat FrmPajakanKecilNegeri, kemaskiniSimpanStatusSelesai:"+getIHTP().getErrorHTML(e.getMessage()));
					
				}
			}	
			  
		  private Hashtable setSusulanValues(String idPermohonan) throws Exception{
			  	String tarikh = getParam("txtarikhkeputusan");
			  	String catatan = getParam("txtcatatan");
			  	Hashtable h = new Hashtable();
				h.put("txdTarikh", tarikh);
				h.put("idPermohonan", idPermohonan);
				h.put("catatan", catatan);
				h.put("idMasuk",userId);
				h.put("idSusulanStatus", getParam("idsusulanstatus"));
				return h;
		  }
		  
		  private Hashtable setSusulanStatusValues(String idPermohonan) throws Exception{
			  	String tarikh = getParam("txtarikhkeputusan");
			  	String catatan = getParam("txtcatatan");
				Hashtable h = getISusulan().getMaklumat(idPermohonan, getParam("idsusulan"));
				
			  	Hashtable hBaru = new Hashtable();
			  	hBaru.put("txdTarikh", tarikh);
			  	hBaru.put("idPermohonan", idPermohonan);
			  	hBaru.put("catatan", catatan);
			  	hBaru.put("idMasuk",userId);
			  	hBaru.put("idSubUrusanStatusFail", h.get("idStatusFail"));
			  	hBaru.put("idSusulan", h.get("idSusulan"));
			  	hBaru.put("idSusulanStatus", h.get("idSusulanStatus"));
				return hBaru;
				
		  }

		  private void setSusulan(String idPermohonan, String idSusulan) throws Exception{
			  Hashtable h = getISusulan().getMaklumat(idPermohonan, idSusulan);
			  this.context.put("idSusulanStatus", h.get("idSusulanStatus"));
			  this.context.put("idSusulan", idSusulan);
			  this.context.put("txtCatatan", h.get("keterangan"));
			  this.context.put("tarikhHantar", h.get("tarikh"));
			
		  }
		  //shiqa - simpanpengesahan 25062020
	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf, String langkah2) throws Exception {
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");

			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,
					String.valueOf(rsusf.getIdSuburusanstatusfail()), "=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userId));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN);

		} catch (Exception e) {
			// throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			getIHTP().getErrorHTML(e.toString());

		}
	}
	
		  
		  private IPemilik getIPemilik(){
			  if(iPemilik==null){
				  iPemilik = new PemilikBean();
			  }
			  return iPemilik;
				
		  }
		  private IPenggunaKementerian getIPengguna(){
				if(iPengguna==null){
					iPengguna = new PenggunaKementerianBean();
				}
				return iPengguna;
				
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
		
			private IHTPStatus getStatus(){
				if(iStatus==null){
					iStatus = new HTPStatusBean();
				}
				return iStatus;
						
			}
			private IHtp getHTP(){
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
			
	private IHTPFail getIHTPPKFail(){
		if (iHTPPKFail == null){
			iHTPPKFail = new HTPPajakanKecilFailBean();
			}
		return iHTPPKFail;
	}	
	
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserBean();
		}
		return iUser;
			
	}
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
			
	}
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelJRPBean();
		return emelSemak;
	}
	private IHTPPermohonan getIHTPPermohonan(){
		if(iHTPPermohonan== null)
			iHTPPermohonan = new HTPPermohonanBean();
		return iHTPPermohonan;
	}
	private ILampiran getDoc(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
				
	}
	

}
