package ekptg.view.online.htp.permohonan;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.intergration.BorangKIntergrationBean;
import ekptg.intergration.IBorangKIntergration;
import ekptg.model.entities.UserKementerian;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IPenggunaKementerian;
import ekptg.model.htp.PenggunaKementerianBean;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmPermohonanOnlineAgensi extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3744798063048260707L;
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.permohonan.FrmPermohonanOnlineAgensi.class);
	private ekptg.model.htp.IHtp iHtp = null;
	private final String PATH="app/htp/permohonan/online/";
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
 	String idAgensi = "";
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

	//END INIT	
	@Override
	public String doTemplate2() throws Exception {	
		fData = FrmSenaraiFailTerimaPohonData.getInstance();
		HttpSession session = this.request.getSession();
		idUser = (String)session.getAttribute("_ekptg_user_id");		
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
	 	//id_agensi = getParam("socAgensi");
	 	idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	idurusan = getParam("socUrusan");
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");
	 	
	 	uk = getIPengguna().getKementerian(idUser);
		if (id_kementerian == null || id_kementerian.trim().length() == 0){
			if(uk == null){
				//throw new Exception("[ONLINE-HTP REKOD] KEMENTERIAN TIDAK DIJUMPAI");
				throw new Exception(getHTP().getErrorHTML("[ONLINE-HTP PERMOHONAN] KEMENTERIAN TIDAK DIJUMPAI"));
			}
		
			myLog.info("id_kementerian="+uk.getAgensi().getKementerian().getIdKementerian());
			id_kementerian = String.valueOf(uk.getAgensi().getKementerian().getIdKementerian());
			//System.out.println("+++++"+id_kementerian);
		}
		idAgensi = String.valueOf(uk.getAgensi().getIdAgensi());

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
		myLog.debug("SUBMIT :: " +submit+",MODE :: " + mode+",BUTTON :: " + button
				+",selectedTab :: " +selectedTab+",tabmode :: " + tabmode+",PAGEMODE :: " +pagemode);

		this.context.put("showSahkanButton",false);
		this.context.put("sahkanresult","");
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		try{
			if ("maklumatpermohonanonline".equals(submit)) {
				myLog.info("viewMaklumatPermohonan ::"+template_name);
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,true,true,false,false);
				template_name = PATH+"frmTerimaPohonOnline.jsp";
				Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);

			}else if ("terimapohoncarian".equals(submit)) {
				//CARIAN
				myLog.debug("terimapohoncarian:"+submit+"::"+template_name);
		    	flagAdvSearch = "Y";
				senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
						id_kementerian,idAgensi,idnegeri,iddaerah,idmukim,idurusan,null,null); //--- addby zul for carian by idStatus dan noRujukanOnline --
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
	    			template_name = PATH+"frmTerimaPohonOnline.jsp";
	    			getValues();
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	    			htpPermohonan = getHTP().kemaskiniPermohonan(htpPermohonan, idpermohonan, "");
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	  				
				} else if ("kemaskini".equals(hittButton)){
	    			template_name = PATH+"frmTerimaPohonOnline.jsp";
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
					readOnly = "";
					disabled = "";
					style = "";   
					pageMode = "kemaskini";
					this.context.put("readOnly", readOnly);
					this.context.put("disabled", disabled);	
					
	    		} else {
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	    		}
				this.context.put("pageMode", pageMode);	
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	    		
		    	//TAB NAVIGATION
	    		idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
	    		iddaerah = (String)TerimaPohonInfo.get("lblIdDaerah");
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
	    		
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
	    			/////////////////////
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
		    			} else if ("KemaskiniPembayaran".equals(button)){
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
				//// NOTIS 5A		
		    	} else if ("Notis5A".equals(mode)) { //NOTIS 5 A
		    		
		    		idNotis = getParam("idNotis");
		    		this.context.put("idNotis", idNotis);
		    		myLog.debug("idNotis:"+idNotis);
		    		
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
			}else {
		    	//FIRST PAGE - SENARAI FAIL PERMOHONAN		    	
		    	if(flagAdvSearch.equals("Y")){
					senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
							id_kementerian,idAgensi,idnegeri,iddaerah,idmukim,idurusan,null,null); //--- addby zul for carian by idStatus dan noRujukanOnline --
					doListing(session,action,mode,senaraiTerimaPohon);
		    	}else{
		    		myLog.debug("default page ::"+template_name);
		    		//senaraiTerimaPohon = fData.TerimaPohongetList(idUser,null,null,null,null,null,null,null,null);
		    		senaraiTerimaPohon = fData.TerimaPohongetListOnlineSHTP(null,null,null,id_kementerian,null,null,null,null,null);
		    		doListing(session,action,mode,senaraiTerimaPohon);
		    		if(mode.equals("changeNegeri")){
				    	flagAdvSearch = "Y";
		    			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajuk,
								id_kementerian,idAgensi,idnegeri,iddaerah,idmukim,idurusan,null,null); //--- addby zul for carian by idStatus dan noRujukanOnline --
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
    	    idAgensi = "-1";
    
		} else {
    		myLog.debug(" do listing :"+nofail);

    	}
        this.context.put("txtNoFail",nofail);
	    this.context.put("txtTajuk",txtTajuk);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan", Utils.parseLong(idurusan), null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian",  Utils.parseLong(id_kementerian), " disabled class=disabled", " onChange=\"doChangeKementerianX()\" style=\"width:400\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(idAgensi), " disabled class=disabled"," style=\"width:400\"");
		socNegeri = HTML.SelectNegeri("socNegeri", Utils.parseLong(idnegeri), null," onChange=doChangeNegeriX();");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah), null, " onChange=\"doChangeDaerahX()\"");
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
	
//	public void viewPohonFailBaru(String mode) throws Exception {
//		//utk simpan
//		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChanges()\" ");
//		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
//		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChanges()\" ");
// 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "");
//		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChanges()\" ");//disabled class=disabled
// 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(idsuburusan), "");
// 		if (idurusan.equals("1")){
// 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
// 		}
// 		
//		context.put("socKementerian", socKementerian);
//		context.put("socUrusan", socUrusan);
//		context.put("socAgensi", socAgensi);
//		context.put("socNegeri", socNegeri);
//		context.put("socDaerah", socDaerah);
//		context.put("socSubUrusan", socSubUrusan);
//		context.put("socNegeri",socNegeri);
//		
//		if (mode.indexOf("doChange") != -1) {
//			log.debug(" ** CHANGES DETECTED !!");
//			//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
//	 		// modified by rosli 2010/08/02
//	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
//			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
//			context.put("socStatustanah", socStatustanah);
//			context.put("socjenisFail", socjenisFail);
//			context.put("txtTajuk",getParam("txtTajuk"));
//			context.put("noFail","");
//			context.put("txtnoFailKJP","");
//			context.put("txtnoFailUPT","");
//			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
//	    } else {
//	    	//1-Tanah Kerajaan ,3-Tanah Kerajaan(Pengambilan),6-Tanah Kerajaan(Penswastaan)
//	 		//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
//	 		// modified by rosli 2010/08/02
//	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
//			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
//			context.put("socStatustanah", socStatustanah);
//			context.put("socjenisFail", socjenisFail);
//			context.put("txtTajuk","");
//			context.put("noFail","");
//			context.put("txtnoFailKJP","");
//			context.put("txtnoFailUPT","");
//			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
//	    }
//
//	}
	public void viewPohonFailBaru(String mode) throws Exception {
		
		//String fail1 = getParam("cmdSimpan");
		//utk simpan
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChanges()\" ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), "disabled=\"disabled\"","onChange=\"doChanges()\"  style=\"width:400\"");
 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(idAgensi), " style=\"width:400\"");
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
	    }

	}
	
	public String doSimpanMaklumatPermohonan() throws Exception {		
		Hashtable h = new Hashtable();
		h.put("id_Fail", idfail);
		h.put("id_Tarafkeselamatan", "1");
		h.put("id_Seksyen", getParam("socSeksyen"));
		h.put("id_Urusan", idurusan);
		h.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		h.put("tajuk_Fail", getParam("txtTajuk"));
	    h.put("no_Fail", nofail);
		h.put("id_Negeri", idnegeri);
		h.put("id_Daerah", iddaerah);
		h.put("id_Kementerian",id_kementerian);
		h.put("id_Agensi",idAgensi);
		h.put("flag_Fail",1);
		h.put("id_Status", "138");
		h.put("id_Masuk", idUser);
		h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		h.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		h.put("noFailUPT", getParam("txtnoFailUPT"));  
		h.put("noFailKJP", getParam("txtnoFailKJP"));  
		h.put("StatusTanah", getParam("socStatustanah"));  
//		return fData.simpanPermohonan(h,idUser);
		return fData.simpanPermohonanOnline(h,idUser);
			
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
	
	/////////////
	
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
	
	////////////////////////////
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
	
	
}
	