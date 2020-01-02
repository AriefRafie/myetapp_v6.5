package ekptg.view.htp;

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
import ekptg.intergration.entity.BorangK;
import ekptg.intergration.entity.HTPBorangK;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmTerimaPohonData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmTerimaPermohonanBorangK extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3744798063048260707L;
	private static Logger log = Logger.getLogger(ekptg.view.htp.FrmTerimaPermohonanBorangK.class);
	private final String PATH="app/htp/permohonan/";
	private final String JENISTANAH = "1,3,6";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private IHtp iHtp = null;

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
		String template_name = PATH+"frmTerimaPohonSenaraiFail2.jsp";
		//////////////////////////
		log.debug("SUBMIT :: " +submit);
		//log.debug("ACTION :: " +action);
		log.debug("MODE :: " + mode);
		log.debug("BUTTON :: " + button);
		//log.debug("DOCHAGE :: " +doChange);
		log.debug("selectedTab :: " +selectedTab);
		log.debug("tabmode :: " + tabmode);
		log.debug("PAGEMODE :: " +pagemode);
		//log.debug("idpermohonan :: " + idpermohonan);
		////////////////
		this.context.put("showSahkanButton",false);
		this.context.put("sahkanresult","");
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");

		try{

			if ("SahkanPermohonan".equals(submit)) {
				template_name = PATH+"FrmTerimaPohon.jsp";
				if("doSahkan".equals(button)){
					transferRecord(idpermohonan);
					this.context.put("sahkanresult","Permohonan telah disahkan");
				} else {
					this.context.put("sahkanresult","");
				}
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				return template_name;
				
			}
			else if ("viewMaklumatPermohonan".equals(submit)) {
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				setPaging(false,true,true,false,false);
				template_name = PATH+"FrmTerimaPohon.jsp";
				Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	   			//dapatkan flag utk button sahkan
	   			if (fData.showSahkanButton(idpermohonan)) {
	   				this.context.put("showSahkanButton",true);
	   			}
	   			
			}else if ("terimapohoncarian".equals(submit)) {
				//CARIAN
				log.debug("terimapohoncarian:"+submit);
		    	flagAdvSearch = "Y";
				senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajukCarian,
									id_kementerian,id_agensi
									,idnegeri,iddaerah,idmukim
									,idurusan, null, true);
				doListing(session,action,mode,senaraiTerimaPohon);
			
			}else if ("pohonfailbaru".equals(submit)) {
				log.debug(submit);
				template_name = PATH+"FrmTerimaPohon.jsp";
				this.context.put("readOnly", "");
				this.context.put("disabled", "");	
		    	this.context.put("idpermohonan","");
		    	this.context.put("idfail","");
				viewPohonFailBaru(mode);
				
		    }else if("kemaskinipermohonan".equals(submit)){
				log.debug("kemaskinipermohonan:"+submit);
		    	this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
		    	template_name = PATH+"frmTerimaPohonMaklumatTABB.jsp";
	
	//			setPaging(false,true,true,false,false);
	//			template_name = PATH+"FrmTerimaPohon.jsp";
	//			Hashtable TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	//			idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	//   			setMaklumatPermohonan(TerimaPohonInfo);
	//   			//dapatkan flag utk button sahkan
	//   			if (fData.showSahkanButton(idpermohonan)) {
	//   				this.context.put("showSahkanButton",true);
	//   			}
		    	//HEADER
		    	Hashtable TerimaPohonInfo = null;
	    		String hittButton = getParam("hittButton");
	    		//SIMPAN
	    		if ("Simpan".equals(hittButton)) {
	    			template_name = PATH+"FrmTerimaPohon.jsp";
	    			String idFailBaru = doSimpanMaklumatPermohonan();
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idFailBaru);
	    			idfail = idFailBaru;
	    			
	    		} else {
	    			System.out.println("****"+idfail);
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	    			
	    		}
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	   			 			
	    		//ASSIGN VALUES
				//DISABLE OPTION UTK PILIH NEGERI
	    		socNegeri = HTML.SelectNegeri("socnegeri2",Utils.parseLong(idnegeri),"disabled class=disabled");
	    		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socdaerah2",Utils.parseLong(iddaerah),style, "onChange=\"doChanges2('"+tabmode+"')\"");
	    		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim2", Utils.parseLong(idmukim) ,style);
	//    		socLot = HTML.SelectLot("socLot", Utils.parseLong(socLot),style);
	    		
	    		context.put("socNegeri2", socNegeri);
	    		context.put("socDaerah2", socDaerah);
	    		context.put("socMukim2",socMukim );
	//    		context.put("socLot",socLot );
	//    		context.put("socLuas", socLuas);
	//    		context.put("noSyit", noSyit);
	//    		context.put("noPelan", noPelan);
	//    		context.put("txtNoLot", txtNoLot);
	//    		context.put("Lokasi", Lokasi);
	//    		context.put("txtLuas1", LuasLama);
	//    		context.put("Luas", Luas);
				String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
	
	    		if ("MakAsasTanah".equals(mode)) {
		    		//MAKLUMAT ASAS TANAH
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
	    				log.debug("changes...");
	    				log.debug("TAB MODE:"+tabmode);
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    			}
	    			/////////////////////
		    		if("SimpanMaklumatAsasTanah".equals(button)){
	    				log.debug(" ********** SimpanMaklumatAsasTanah ****8");
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				doSimpanMaklumatAsasTanah();
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    			
		    		} else if ("KemaskiniMaklumatInfo".equals(button)) {
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
	      				log.debug("DetailLot ** "+button+" **");
	    				selectedTab2 = getParam("tabId2");
	    				this.context.put("selectedTab2", selectedTab2);
	    				if ("1".equals(selectedTab2)) {
	    					initMaklumatCharting("");
	    				} else {
	    					initMaklumatLokasi("");
	    				}
	    			
	    			} else if ("SimpanDetailLot".equals(button)) {
	    				log.debug("SimpanDetailLot ** "+button+" **");
	    				SimpanLokasiTanah();
	    				initMaklumatLokasi("readonly");
	       			
	    			}else if ("doKemaskiniDetailLot".equals(button)){
	    				log.debug("doKemaskiniDetailLot ** "+button+" **");
	    				doKemaskiniLokasiTanah();
	    				initMaklumatLokasi("readonly");
	    			
	    			} else if ("doViewForKemaskiniDetailLot".equals(button)){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else if ("SimpanCharting".equals(button)){
	    				log.debug("SimpanCharting ** "+button+" **");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				SimpanCharting();
	    				initMaklumatCharting("readonly");
	    			
	    			} else if ("doKemaskiniCharting".equals(button)){
	    				log.debug("doKemaskiniCharting ** "+button+" **");
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
		    			log.debug(" ** SENARAI **");
		    			log.debug(" ** SENARAI:tabmode= **"+tabmode);
		    			//komen pada 25/06/2010 oleh mrosli
		    			Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
		    			
		    			//untuk ppt
		    			//String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
		    			context.put("id_jenistanah", id_jenistanah);
		    			if(MaklumatAsasTanahInfo.size()>0 ){
		    				//tabmode = "3_1";
		    			}
		    			
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
		    		//list infoborangk
		    		//fData.setListBorangK();
		     		//listBorangK = fData.getListBorangK();
		    		listBorangK = getKIntergration().getHTPBorangKList(idpermohonan);
		     		context.put("listBorangK", listBorangK);
		     		context.put("saiz_borangk", listBorangK.size());   	
		     		
		    	} else if ("Pembayaran".equals(mode)) {
	
		    		selectedTab = "1"; //TAB KEPUTUSAN/PEMBAYARAN
		    		if(id_jenistanah.equals("3")){
			    		selectedTab = "2"; //TAB KEPUTUSAN/PEMBAYARAN (TANAH PENGAMBILAN)
		    		
		    		}
		    		log.debug(" ** Pembayaran **");
		    		log.debug(" ** "+button+" **");
		    		this.context.put("visible","1");
					selectedTab3 = getParam("tabId3");
					log.debug("selectedTab3:"+selectedTab3);
					
					if ("1".equals(selectedTab3)) {
					//if ("1".equals(tabmode)) { //TAB KEPUTUSAN 
						log.debug("TAB KEPUTUSAN");
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
		    			
						}else {
							initKeputusanPermohonan("");
						}
						
					} else { //TAB BUKTI PEMBAYARAN 
						if("TambahPembayaran".equals(button)){
		    				log.debug("Simpan Pembayaran");
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
	
		    	} else if ("Notis5A".equals(mode)) { //NOTIS 5 A
		    		
		    		idNotis = getParam("idNotis");
		    		this.context.put("idNotis", idNotis);
		    		log.debug("idNotis:"+idNotis);
		    		
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
		    			senaraiNotis5A();
		    			viewNotis5A(idNotis);
	    			
		    		} else if ("BuktiBayaranNotis".equals(button)) {
		    			SimpanBuktiBayaranNotis();
		    			viewBuktiBayaranNotis();
		    		
		    		} else if ("BayaranNotisHapus".equals(button)) {
		    			String idBayaran = getParam("idBayaran");
		    			getHTP().hapusBayaran(idBayaran);
		    			viewBuktiBayaranNotis();
		    		
		    		}else {
		    			//SENARAI NOTIS
		    			senaraiNotis5A();
		    		}
	
		    		selectedTab = "2"; //TAB NOTIS
		    		if(id_jenistanah.equals("3")){
			    		selectedTab = "3"; //TAB NOTIS TANAH PENGAMBILAN		    		
		    		}
		    	}
				//this.context.put("selectedTab", selectedTab);
				log.info("462,selectedTab:"+selectedTab);
	   			
		    }else if ("kemaskinipermohonanlama".equals(submit)) {
		    	log.debug("***** "+submit);
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");	
				//template_name = PATH+"FrmTerimaPohon.jsp";
		    	template_name = PATH+"frmTerimaPohonMaklumatTABB.jsp";
		    	
		    	//HEADER
		    	Hashtable TerimaPohonInfo = null;
	    		String hittButton = getParam("hittButton");
	   		
	    		//SIMPAN
	    		if ("Simpan".equals(hittButton)) {
	    			String idFailBaru = doSimpanMaklumatPermohonan();
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idFailBaru);
	    			idfail = idFailBaru;
	    			
	    		} else {
	    			TerimaPohonInfo = fData.getTerimaPohonInfo(idfail);
	    			
	    		}
				idpermohonan = (String)TerimaPohonInfo.get("lblIdPermohonan");
	   			setMaklumatPermohonan(TerimaPohonInfo);
	    		
		    	//TAB NAVIGATION
	    		idnegeri = (String)TerimaPohonInfo.get("lblNegeri");
	    		iddaerah = (String)TerimaPohonInfo.get("lblIdDaerah");
	    		
	    		if(iddaerah == null || "".equals(iddaerah)) iddaerah="-1";
	    		
	    		setPaging(false,false,true,false,false);
	    		
		    	if ("MakAsasTanah".equals(mode)) {
		    		//MAKLUMAT ASAS TANAH
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
	    				log.debug("changes...");
	    				log.debug("TAB MODE:"+tabmode);
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    			}
	    			/////////////////////
		    		if("SimpanMaklumatAsasTanah".equals(button)){
	    				log.debug(" ********** SimpanMaklumatAsasTanah ****8");
	    				readOnly = "readonly";
	    				disabled = "disabled";
	    				style = readOnly + " class="+disabled+" ";
	    				doSimpanMaklumatAsasTanah();
	    				idnegeri = getParam("socnegeri2");
	    				iddaerah = getParam("socdaerah2");
	    				idmukim = getParam("socMukim2");
	    			} else if ("KemaskiniMaklumatInfo".equals(button)) {
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
	      				log.debug(" ** "+button+" **");
	    				selectedTab2 = getParam("tabId2");
	    				this.context.put("selectedTab2", selectedTab2);
	    				if ("1".equals(selectedTab2)) {
	    					initMaklumatCharting("");
	    				} else {
	    					initMaklumatLokasi("");
	    				}
	    			
	    			} else if ("SimpanDetailLot".equals(button)) {
	    				log.debug(" ** "+button+" **");
	    				SimpanLokasiTanah();
	    				initMaklumatLokasi("readonly");
	       			
	    			}else if ("doKemaskiniDetailLot".equals(button)){
	    				log.debug(" ** "+button+" **");
	    				doKemaskiniLokasiTanah();
	    				initMaklumatLokasi("readonly");
	    			
	    			} else if ("doViewForKemaskiniDetailLot".equals(button)){
	    				readOnly = "";
	    				disabled = "";
	    				style = "";
	    			
	    			} else if ("SimpanCharting".equals(button)){
	    				log.debug(" ** "+button+" **");
	    				selectedTab2 = "1";
	    				this.context.put("selectedTab2", selectedTab2);
	    				SimpanCharting();
	    				initMaklumatCharting("readonly");
	    			
	    			} else if ("doKemaskiniCharting".equals(button)){
	    				log.debug(" ** "+button+" **");
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
		    			log.debug(" ** SENARAI **");
		    			//komen pada 25/06/2010 oleh mrosli
		    			Vector MaklumatAsasTanahInfo = fData.getMaklumatAsasTanahInfo(idpermohonan);
		    			
		    			//untuk ppt
		    			String id_jenistanah = TerimaPohonInfo.get("lblidKodJTanah").toString();
		    			context.put("id_jenistanah", id_jenistanah);
		    			
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
		    		//list infoborangk
		    		//fData.setListBorangK();
		     		//listBorangK = fData.getListBorangK();
		     		listBorangK = getKIntergration().getHTPBorangKList(idpermohonan);
		     		context.put("listBorangK", listBorangK);
		     		context.put("saiz_borangk", listBorangK.size());   	
		     		
		    	} else if ("Pembayaran".equals(mode)) {
		    		
		    		selectedTab = "4"; //TAB KEPUTUSAN/PEMBAYARAN
		    		log.debug(" ** Pembayaran **");
		    		log.debug(" ** "+button+" **");
		    		this.context.put("visible","1");
					selectedTab3 = getParam("tabId3");
					log.debug("selectedTab3:"+selectedTab3);
					
					if ("1".equals(selectedTab3)) {
					//if ("1".equals(tabmode)) { //TAB KEPUTUSAN 
						log.debug("TAB KEPUTUSAN");
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
		    				log.debug("Simpan Pembayaran");
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
		    		log.debug("idNotis:"+idNotis);
		    		
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
		    			viewNotis5A(idNotis);
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
	
		    }
		    else if(submit.equals("viewBorangK")){
		    	String idPermohonan = getParam("idpermohonan");
		    	String idFail = getParam("idfail");
		    	template_name = PATH +"borangKList.jsp";
		    	Vector<HTPBorangK> k = getKIntergration().getHTPBorangKList(idPermohonan);
		    	context.put("borangKList", k);
		    	context.put("idpermohonan", idPermohonan);
		    	context.put("idfail", idFail);
		    	context.remove("borangKMessage");
		    	
		    }
		    else if(submit.equals("searchBorangK")){
		    	String idPermohonan = getParam("idpermohonan");
		    	String noFailPPT = getParam("noFailPPT");
		    	Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT);
		    	context.put("borangKList", k);
		    	template_name = PATH +"borangKList.jsp";
		    	context.put("idpermohonan", idPermohonan);
		    }
		    else if(submit.equals("simpanBorangK")){
		    	String noFailPPT = getParam("noFailPPT");
		    	String idHakmilikPPT = getParam("idHakmilikPPT");
		    	String idPermohonanHTP = getParam("idpermohonan");
		    	getKIntergration().simpanHTPBorangK(idHakmilikPPT, idPermohonanHTP, idUser);
		    	
		    	Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT);
		    	context.put("borangKList", k);
		    	template_name = PATH +"borangKList.jsp";
		    	
		    	context.put("borangKMessage", "REKOD BERJAYA DISIMPAN");
		    	
		    }
		    else {
		    	//FIRST PAGE - SENARAI FAIL PERMOHONAN
		    	
				context.remove("SenaraiFail");
		    	if(flagAdvSearch.equals("Y")){
					senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajukCarian
							,id_kementerian,id_agensi
							,idnegeri,iddaerah,idmukim
							,idurusan, null, true);
					doListing(session,action,mode,senaraiTerimaPohon);
		    	}else{
		    		log.debug("default page");
		    		senaraiTerimaPohon = fData.TerimaPohongetList(idUser,null,null
		    				,null,null
		    				,null,null,null
		    				,null, null, false);
		    		doListing(session,action,mode,senaraiTerimaPohon);
		    		if(mode.equals("changeNegeri")){
				    	flagAdvSearch = "Y";
		    			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajukCarian,
								id_kementerian,id_agensi
								,idnegeri,iddaerah,idmukim
								,idurusan, null, true);
						doListing(session,action,mode,senaraiTerimaPohon);
		    		}
		    	}
		    	
		    }
			selectedTab2 = (String)this.context.get("selectedTab2");
			if (selectedTab2 == null || "".equals(selectedTab2) ) 
			{
	    		selectedTab2="0";
	    	}
			selectedTab3 = (String)this.context.get("selectedTab3");
			if (selectedTab3 == null || "".equals(selectedTab3) ) 
			{
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
			e.printStackTrace();
		}

		log.debug("** "+template_name);
		System.out.println(template_name);
		return template_name;
	}
	
	public void initContext() {
		this.context.put("socMukim","");
	}
	
	public void emptyContext() {
		log.debug(" ******** emptyContext ********888");
		this.context.put("txtNoFail","");
		this.context.put("txtTajukFail","");
	}
	
	public void doListing(HttpSession session,String action,String mode,Vector v) throws Exception {

		if (mode.indexOf("change") != -1) {
	    	log.debug("changes detected...");
	    	initContext();
	    	
	    }
		else if ("cancel".equals(mode)) {
    		log.debug("cancel mode");
    		//emptyContext();
    		nofail = "";
    		txtTajuk = "";
    	    iddaerah = "-1";
    	    idmukim = "-1";
    	    id_agensi = "-1";
    
		} else {
    		log.debug(" do listing :"+nofail);

    	}
        this.context.put("txtNoFail",nofail);
	    this.context.put("txtTajuk",txtTajuk);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChangeKementerianX()\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "","");
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
		
		socNegeri = HTML.SelectNegeri("lblNegeri",Utils.parseLong(idnegeri),"disabled class=disabled");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"lblDaerah",Utils.parseLong((String)TerimaPohonInfo.get("lblIdDaerah")),"disabled class=disabled");

		socKementerian =HTML.SelectKementerian("lblKementerian", Utils.parseLong((String)TerimaPohonInfo.get("lblidKementerian")), "disabled class=disabled");
		socAgensi = HTML.SelectAgensi("lblidAgensi",Utils.parseLong((String)TerimaPohonInfo.get("lblidAgensi")),"disabled class=disabled");
		socUrusan = UtilHTML.SelectUrusan("lblidUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidUrusan")),"disabled class=disabled");
		
		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"lblidSubUrusan",Utils.parseLong((String)TerimaPohonInfo.get("lblidSubUrusan")),"disabled class=disabled");
		socStatustanah = HTML.SelectJenisTanah("lblidKodJTanah", Utils.parseLong((String)TerimaPohonInfo.get("lblidKodJTanah")), "disabled class=disabled");
		socjenisFail = HTML.SelectTarafKeselamatan("lblTanahKeselamatan",Utils.parseLong((String)TerimaPohonInfo.get("lblTanahKeselamatan")),"disabled class=disabled");
		
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
	///////
	
	public void viewPohonFailBaru(String mode) throws Exception {
		
		//String fail1 = getParam("cmdSimpan");
		//utk simpan
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"","onChange=\"doChanges()\" ");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChanges()\" ");
 		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "");
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChanges()\" ");//disabled class=disabled
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
		
		if (mode.indexOf("doChange") != -1) {
			log.debug(" ** CHANGES DETECTED !!");
			//socStatustanah = HTML.SelectJenisTanah("socStatustanah");
	 		// modified by rosli 2010/08/02
	 		socStatustanah = UtilHTML.SelectJenisTanah("socStatustanah",JENISTANAH);
			socjenisFail = HTML.SelectTarafKeselamatan("socjenisFail");
			context.put("socStatustanah", socStatustanah);
			context.put("socjenisFail", socjenisFail);
			context.put("txtTajuk",getParam("txtTajuk"));
			context.put("noFail","");
			context.put("txtnoFailKJP","");
			context.put("txtnoFailUPT","");
			context.put("txdTarikhSuratKJP",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
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
	
	public String doSimpanMaklumatPermohonan() throws Exception {
		
		Hashtable h = new Hashtable();
		h.put("id_Fail", idfail);
		h.put("id_Tarafkeselamatan", "1");
		h.put("id_Seksyen", getParam("socSeksyen"));
		h.put("id_Urusan", idurusan);
		h.put("id_Suburusan", idsuburusan);// getParam("socSuburusan"));
		h.put("tajuk_Fail", txtTajuk);
		h.put("no_Fail", nofail);
		h.put("id_Negeri", idnegeri);
		h.put("id_Daerah", iddaerah);
		h.put("id_Kementerian",id_kementerian);
		h.put("id_Agensi",id_agensi);
		h.put("flag_Fail",1);
		h.put("id_Status", "1");
		h.put("id_Masuk", idUser);
		h.put("TarikhSurKJP", getParam("txdTarikhSuratKJP"));
		h.put("TarikhPermohonan", getParam("txdTarikhSuratKJP"));  
		h.put("noFailUPT", getParam("txtnoFailUPT"));  
		h.put("noFailKJP", getParam("txtnoFailKJP"));  
		h.put("StatusTanah", getParam("socStatustanah"));  
		return fData.simpanPermohonan(h,idUser);
		//fData.janaFail(h);
	
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
		log.debug("*** RadioGroup1:"+getParam("RadioGroup1"));
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
		log.debug("*** "+pembayaran);
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
		
		caraBayaran = HTML.selectCaraBayar("socBayaran",Utils.parseLong(idcarabayar),style + " width=40px ", null);
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
			log.debug("*** "+keputusan);
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
		hNotis.put("CukaiTahunPertama",Utils.isNull(Utils.RemoveComma(getParam("txtCukaiTahunPertama"))));
		hNotis.put("BayaranNotis",Utils.isNull(getParam("txtBayaranNotis")));
		hNotis.put("BayaranNotisF",Utils.isNull(getParam("txtBayaranNotisF")));
		hNotis.put("Premium",Utils.isNull(Utils.RemoveComma(getParam("txtPremium"))));
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
	
	private IHtp getHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}

}
	