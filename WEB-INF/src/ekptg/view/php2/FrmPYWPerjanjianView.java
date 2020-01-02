package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWPerjanjianData;


public class FrmPYWPerjanjianView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWPerjanjianData logic = new FrmPYWPerjanjianData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		this.context.put("userId", userId);
		this.context.put("userRole", userRole);
		this.context.put("idNegeriUser", idNegeriUser);
		
	    //GET DEFAULT PARAM
	    String submit = getParam("command");  
	    String vm = ""; 
        String mode = getParam("mode");
        String modeT = getParam("modeT");
        String actionPenyewaan = getParam("actionPenyewaan");
        if (mode.isEmpty()){
        	mode = "view";
        }
        if (modeT.isEmpty()){
        	modeT = "viewT";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idUrusan = getParam("idUrusan");
        String idSuburusan = getParam("idSuburusan");
        String idStatus = getParam("idStatus");
        String idPerjanjian = getParam("idPerjanjian");
        String idMaklumbalas = getParam("idMaklumbalas");
        String idKeputusan = getParam("idKeputusan");
        String flagAktif = getParam("flagAktif");
        String noSambungan = getParam("noSambungan");
        String idPermohonanSewa = getParam("idPermohonanSewa");
        String noFail = "";
        String tajukFail = "";        
        String urusan = "";
        String subUrusan = "";
        String flagSebabTamat = "";
        
        // GET DROPDOWN PARAM
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
		
		//VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBorangK = null;
        Vector beanMaklumatPerjanjian = null;
        Vector beanMaklumatTamatSewa = null;
        Vector beanMaklumatMaklumbalas = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatPemohon = null;
        Vector senaraiDokumen = null;
        
        String step = getParam("step");
        
        //DATE
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date currentDate = new Date();
    	
    	this.context.put("onload", "");
    	this.context.put("errMsg", "");
		
		//SUBMIT TO DB
        if (postDB) {
        	if ("simpanKemaskiniPerjanjian".equals(hitButton)) {
        		logic.simpanKemaskiniPerjanjian(idPerjanjian, getParam("txtNoSiri"), getParam("txtTarikhMula"),
			    		getParam("txtTempoh"), getParam("txtTarikhTamat"), getParam("txtKadarSewa"), getParam("txtRoyalti"),getParam("txtCagaran"), session);
			
        		logic.simpanKemaskiniMaklumbalas(idPerjanjian, getParam("txtTarikhTerimaCagaran"), getParam("txtNoRujukanCagaran"),
			    		getParam("socCagaran"), getParam("txtTarikhTerimaTandatangan"), getParam("txtNoRujukanTandatangan"), getParam("socTandatangan"),
			    		getParam("txtTarikhTerimaMatiSetem"), getParam("txtNoRujukanMatiSetem"), getParam("socMatiSetem"), session);
			
        	
        	}
        	if ("simpanKemaskiniMaklumbalas".equals(hitButton)) {
        		logic.simpanKemaskiniMaklumbalas(idPerjanjian, getParam("txtTarikhTerimaCagaran"), getParam("txtNoRujukanCagaran"),
			    		getParam("socCagaran"), getParam("txtTarikhTerimaTandatangan"), getParam("txtNoRujukanTandatangan"), getParam("socTandatangan"),
			    		getParam("txtTarikhTerimaMatiSetem"), getParam("txtNoRujukanMatiSetem"), getParam("socMatiSetem"), session);
			}
        	if ("daftarSambungan".equals(hitButton)) {
        		idFail = logic.daftarSambungan(idFail, idPermohonan, idUrusan, idSuburusan, getParam("tarikhTerima"),
    					getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
    					idKategoriPemohon, getParam("txtNama"),getParam("txtNamaPegawai"), getParam("txtNoPendaftaran"),
    					getParam("txtPekerjaan"),getParam("txtAlamat1"),
    					getParam("txtAlamat2"), getParam("txtAlamat3"),
    					getParam("txtPoskod"), idBandar, idNegeri,
    					getParam("txtEmel"), getParam("txtNoTel"), getParam("txtNoFaks"),							
    					getParam("noSambungan"),
    					userRole, idNegeriUser, session);
        	}        	
        	if ("doHantarProses".equals(hitButton)){
        		if (logic.checkEmptyNoSiri(idPermohonan)){
        			logic.updateStatus(idFail, idPermohonan, idKeputusan, idSuburusan, session);
        			logic.updateNoSiriPerjanjian(idPermohonan, session);
        			this.context.put("errMsg", "PERJANJIAN TELAH DIAKTIFKAN");
        			session.setAttribute("MSG", "PERJANJIAN TELAH DIAKTIFKAN.FAIL TELAH DIHANTAR KE BILIK FAIL");
    	    		this.context.put("onload", "gotoSenaraiFail();");
        		} else {
        			this.context.put("onload", " \"alert('Sila Masukkan No Siri Perjanjian.')\"");
        		}
	    	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        	
        	if ("doSimpanSuratPeringatan".equals(hitButton)) {
        		logic.simpanSuratPeringatan(idPerjanjian, getParam("txtTarikhHantar"), session);
        		this.context.put("savePeringatan", "true");
			}
        	if ("doSimpanTamatSewa".equals(hitButton)) {
        		logic.simpanTamatSewa(idPermohonanSewa, getParam("socSebabTamat"), getParam("txtTarikhSuratTamat"), getParam("txtCatatanTamat"), session);
        		logic.simpanTamatLogTugasan(idFail , userRole, idNegeriUser, session);
        		
				this.context.put("afterTamatSewa", "afterTamatSewa");
			}
        }  
        
        

        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			urusan = (String) hashHeader.get("urusan");
			idUrusan = (String) hashHeader.get("idUrusan");
			subUrusan = (String) hashHeader.get("subUrusan");
			idSuburusan = (String) hashHeader.get("idSuburusan");
			idKeputusan = (String) hashHeader.get("idKeputusan");
			noFail = (String) hashHeader.get("noFail");
			tajukFail = (String) hashHeader.get("tajukFail");
			flagAktif = (String) hashHeader.get("flagAktif");
			noSambungan = (String) hashHeader.get("noSambungan");
			flagSebabTamat = (String) hashHeader.get("flagSebabTamat");
			
			if(hashHeader.get("idPermohonanSewa") != null){
				idPermohonanSewa = (String) hashHeader.get("idPermohonanSewa");
			}
		}
				
		if ("daftarSambungan".equals(actionPenyewaan)){
			
			vm = "app/php2/frmPYWDaftarSambunganManual.jsp"; 
			
			if ("new".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				Hashtable hashPermohonan = new Hashtable();
				hashPermohonan.put("noFail", noFail);
				hashPermohonan.put("urusan", urusan);
				hashPermohonan.put("subUrusan", subUrusan);
				hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
				hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
				hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
				if (tajukFail.contains("PERMOHONAN PENYAMBUNGAN")){
					hashPermohonan.put("perkara", getParam("txtPerkara") == null || "".equals(getParam("txtPerkara")) ? tajukFail : getParam("txtPerkara"));
				} else {
					hashPermohonan.put("perkara", getParam("txtPerkara") == null || "".equals(getParam("txtPerkara")) ? tajukFail.replace("PERMOHONAN", "PERMOHONAN PENYAMBUNGAN") : getParam("txtPerkara"));
				}
				
				beanMaklumatPermohonan.addElement(hashPermohonan);
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				
				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohonDB = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					if ("99999".equals(idKategoriPemohon))
						idKategoriPemohon = (String) hashPemohonDB.get("idKategoriPemohon");
					if ("99999".equals(idNegeri))
						idNegeri = (String) hashPemohonDB.get("idNegeri");
					if ("99999".equals(idBandar))
						idBandar = (String) hashPemohonDB.get("idBandar");
					
					// MAKLUMAT PEMOHON			
					beanMaklumatPemohon = new Vector();
					Hashtable hashPemohon = new Hashtable();
					hashPemohon.put("nama", getParam("txtNama") == null || "".equals(getParam("txtNama")) ? hashPemohonDB.get("nama"): getParam("txtNama"));
					hashPemohon.put("namaPegawai", getParam("txtNamaPegawai") == null  || "".equals(getParam("txtNamaPegawai")) ? hashPemohonDB.get("namaPegawai"): getParam("txtNamaPegawai"));
					hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null  || "".equals(getParam("txtNoPendaftaran")) ? hashPemohonDB.get("noPendaftaran"): getParam("txtNoPendaftaran"));
					hashPemohon.put("pekerjaan",getParam("txtPekerjaan") == null  || "".equals(getParam("txtPekerjaan")) ? hashPemohonDB.get("pekerjaan"): getParam("txtPekerjaan"));
					hashPemohon.put("alamat1", getParam("txtAlamat1") == null  || "".equals(getParam("txtAlamat1")) ? hashPemohonDB.get("alamat1"): getParam("txtAlamat1"));
					hashPemohon.put("alamat2", getParam("txtAlamat2") == null  || "".equals(getParam("txtAlamat2")) ? hashPemohonDB.get("alamat2"): getParam("txtAlamat2"));
					hashPemohon.put("alamat3", getParam("txtAlamat3") == null  || "".equals(getParam("txtAlamat3")) ? hashPemohonDB.get("alamat3"): getParam("txtAlamat3"));
					hashPemohon.put("poskod", getParam("txtPoskod") == null  || "".equals(getParam("txtPoskod")) ? hashPemohonDB.get("poskod"): getParam("txtPoskod"));
					hashPemohon.put("emel", getParam("txtEmel") == null  || "".equals(getParam("txtEmel")) ? hashPemohonDB.get("emel"): getParam("txtEmel"));
					hashPemohon.put("noTel", getParam("txtNoTel") == null  || "".equals(getParam("txtNoTel")) ? hashPemohonDB.get("noTel"): getParam("txtNoTel"));
					hashPemohon.put("noFaks", getParam("txtNoFaks") == null  || "".equals(getParam("txtNoFaks")) ? hashPemohonDB.get("noFaks"): getParam("txtNoFaks"));
					beanMaklumatPemohon.addElement(hashPemohon);

					this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);	
				}
				
				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
				String flagBorangK = "";
				logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
				if (logic.getBeanMaklumatHakmilik().size() != 0){
					Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
					flagBorangK = (String) hashHakmilik.get("flagBorangK");
				}
				
				if ("Y".equals(flagBorangK)){
					beanMaklumatBorangK = new Vector();
					beanMaklumatBorangK = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
					this.context.put("idJenisTanah", "3");
				} else {
					beanMaklumatTanah = new Vector();
					beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
					this.context.put("idJenisTanah", "1");
				}
				
			} else if ("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				
				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				logic.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				if (logic.getBeanMaklumatPermohonan().size() != 0){
					Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
					idUrusan = (String) hashPermohonan.get("idUrusan");
					idSuburusan = (String) hashPermohonan.get("idSuburusan");
				}

				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);
				beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idNegeri = (String) hashPemohon.get("idNegeri");
					idBandar = (String) hashPemohon.get("idBandar");
				}
				
				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
							
				String flagBorangK = "";
				logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
				if (logic.getBeanMaklumatHakmilik().size() != 0){
					Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
					flagBorangK = (String) hashHakmilik.get("flagBorangK");
				}
				
				if ("Y".equals(flagBorangK)){
					beanMaklumatBorangK = new Vector();
					beanMaklumatBorangK = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
					this.context.put("idJenisTanah", "3");
				} else {
					beanMaklumatTanah = new Vector();
					beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
					this.context.put("idJenisTanah", "1");
				}				
			}
			
		} else {
			
			vm = "app/php2/frmPYWPerjanjian.jsp"; 
			
			idPerjanjian = logic.getIdPerjanjianByIdPermohonan(idPermohonan);

	        if("view".equals(mode)){
				 
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
		    	
		    	//MAKLUMAT PERJANJIAN
				beanMaklumatPerjanjian = new Vector();
				logic.setMaklumatPerjanjian(idPerjanjian);
				beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
		    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
		    	
		    	//MAKLUMBALAS
				beanMaklumatMaklumbalas = new Vector();
				logic.setMaklumatMaklumbalas(idPerjanjian);
				beanMaklumatMaklumbalas = logic.getBeanMaklumatMaklumbalas();
		    	this.context.put("BeanMaklumatMaklumbalas", beanMaklumatMaklumbalas);
		    	
		    	//SENARAI SURAT PERINGATAN
				senaraiDokumen = new Vector();
				logic.setSenaraiDokumen(idPerjanjian);
				senaraiDokumen = logic.getListSuratPeringatan();
				this.context.put("SenaraiDokumen", senaraiDokumen);
		      		
			 }  else if("update".equals(mode)){ 	
				 
	        	 this.context.put("readonly", "");
		    	 this.context.put("inputTextClass", "");
		    	 this.context.put("disabled", "");
		    	 
		    	 //MAKLUMAT PERJANJIAN
				beanMaklumatPerjanjian = new Vector();
				logic.setMaklumatPerjanjian(idPerjanjian);
				beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
			    this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
			    	

		    	//SENARAI SURAT PERINGATAN
				senaraiDokumen = new Vector();
				logic.setSenaraiDokumen(idPerjanjian);
				senaraiDokumen = logic.getListSuratPeringatan();
				this.context.put("SenaraiDokumen", senaraiDokumen);
		      	
			    
			    //MAKLUMBALAS
				beanMaklumatMaklumbalas = new Vector();
				logic.setMaklumatMaklumbalas(idPerjanjian);
				beanMaklumatMaklumbalas = logic.getBeanMaklumatMaklumbalas();
			    this.context.put("BeanMaklumatMaklumbalas", beanMaklumatMaklumbalas);
			 }
			 
	        
	        if("viewT".equals(modeT)){
				 
				this.context.put("readonlyT", "readonly");
				this.context.put("inputTextClassT", "disabled");
				this.context.put("disabledT", "disabled");
		    	
		    	
		    	//MAKLUMAT TAMAT SEWA
		    	beanMaklumatTamatSewa = new Vector();
				logic.setMaklumatTamatSewa(idPermohonanSewa);
				beanMaklumatTamatSewa = logic.getBeanMaklumatTamatSewa();
		    	this.context.put("BeanMaklumatTamatSewa", beanMaklumatTamatSewa);
		    	
		      		
			 } else if("updateT".equals(modeT)){ 	
				 
				 this.context.put("readonlyT", "");
		    	 this.context.put("inputTextClassT", "");
		    	 this.context.put("disabledT", "");
		    	 
			  //MAKLUMAT TAMAT SEWA
		    	beanMaklumatTamatSewa = new Vector();
				logic.setMaklumatTamatSewa(idPermohonanSewa);
				beanMaklumatTamatSewa = logic.getBeanMaklumatTamatSewa();
		    	this.context.put("BeanMaklumatTamatSewa", beanMaklumatTamatSewa);
				 
			 }
		}
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        
        //SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("modeT", modeT);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("actionPenyewaan", actionPenyewaan);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idUrusan", idUrusan);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idStatus", idStatus);      
        this.context.put("idPerjanjian", idPerjanjian);
        this.context.put("idMaklumbalas", idMaklumbalas);
        this.context.put("idKeputusan", idKeputusan);
        this.context.put("flagAktif", flagAktif);
        this.context.put("noSambungan", noSambungan);
        
        this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idNegeri", idNegeri);
       
		this.context.put("step",step);
		
		if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
        this.context.put("idPermohonanSewa", idPermohonanSewa);
        this.context.put("flagSebabTamat", flagSebabTamat);
		
		return vm;
	}
}