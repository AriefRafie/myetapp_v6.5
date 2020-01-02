package ekptg.view.php2.online;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.online.FrmAPBOnlineSenaraiFailData;

public class FrmAPBOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    String userId =  (String) session.getAttribute("_ekptg_user_id");
	    	    
	    // DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String idPermohonan = getParam("idPermohonan");
		String idPengarah = getParam("idPengarah");
        String idPembeliPasir = getParam("idPembeliPasir");
        String idProjek = getParam("idProjek");
        String idPakar = getParam("idPakar");
        String idKoordinat = getParam("idKoordinat");
        String idPemohon = getParam("idPemohon");
		        
        //VECTOR
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanHeader = null;
        Vector beanMaklumatProjek = null; 
        Vector senaraiPengarah = null;
        Vector senaraiPembeliPasir = null;
        Vector senaraiProjek = null;
        Vector senaraiKoordinat = null;
        Vector senaraiPakar = null;
        
        
        //GET DROPDOWN PARAM
        String idKaitanTujuan = getParam("socKaitanTujuan");
		if (idKaitanTujuan == null || idKaitanTujuan.trim().length() == 0){
			idKaitanTujuan = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idFlagLuar = "1";
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
      
		this.context.put("onload", "");
		//SAVE TO DB				
		if (postDB){
				if ("daftarBaru".equals(hitButton)){
					idFail = logic.daftarBaru(getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), 
							getParam("txtLuas"), getParam("socLuas"),session);
	        	}
				if ("doSimpanKemaskiniPermohonan".equals(hitButton)){
					logic.updatePermohonan(idFail,idPermohonan,idPemohon,getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"), getParam("socTempoh"), 
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
							getParam("socLuas"),getParam("txtModalBenar"),getParam("txtModalJelas"),session);
	        	}
				if ("doSimpanPengarah".equals(hitButton)){
	        		idPengarah = logic.savePengarah(idPemohon, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	        				getParam("socBangsa"), getParam("txtNoPengenalan"), getParam("txtSaham"), session);
		
	        	}
	        	if ("doSimpanKemaskiniPengarah".equals(hitButton)){
	        		logic.updatePengarah(idPengarah, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	        				getParam("socBangsa"), getParam("txtNoPengenalan"), getParam("txtSaham"), session);
	        	}
	        	if ("doHapusPengarah".equals(hitButton)){
	        		logic.removePengarah(idPengarah);
	        	}
	        	if ("doSimpanPembeliPasir".equals(hitButton)){
	        		idPembeliPasir = logic.savePembeliPasir(idPermohonan, getParam("txtNamaPembeliPasir"), getParam("txtAlamat1PembeliPasir"), getParam("txtAlamat2PembeliPasir"),
	        				getParam("txtAlamat3PembeliPasir"), getParam("txtPoskodPembeliPasir"), getParam("socNegeriPembeliPasir"), getParam("socBandarPembeliPasir"), getParam("txtNoTelPembeliPasir"),
	        				getParam("txtNoFaxPembeliPasir"), getParam("socJenisPerjanjian"), session);
	        	}
	        	if ("doSimpanKemaskiniPembeliPasir".equals(hitButton)){
	        		logic.updatePembeliPasir(idPembeliPasir, getParam("txtNamaPembeliPasir"), getParam("txtAlamat1PembeliPasir"), getParam("txtAlamat2PembeliPasir"),
	        				getParam("txtAlamat3PembeliPasir"), getParam("txtPoskodPembeliPasir"), getParam("socNegeriPembeliPasir"), getParam("socBandarPembeliPasir"), getParam("txtNoTelPembeliPasir"),
	        				getParam("txtNoFaxPembeliPasir"), getParam("socJenisPerjanjian"), session);
	        	}
	        	if ("doHapusPembeliPasir".equals(hitButton)){
	        		logic.removePembeliPasir(idPembeliPasir);
	        	}        	
	        	if ("doSimpanProjek".equals(hitButton)){
	        		logic.saveProjek(idPermohonan, getParam("txtNamaProjek"), session);	
	        	}
	        	if ("doSimpanKemaskiniProjek".equals(hitButton)){
	        		logic.updateProjek(idProjek, getParam("txtNamaProjek"), session);
	        	}
	        	if ("doHapusProjek".equals(hitButton)){
	        		logic.removeProjek(idProjek);
	        	}
	        	if ("doSimpanPakar".equals(hitButton)){
	        		idPakar = logic.savePakar(idPermohonan, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
	        	}
	        	if ("doSimpanKemaskiniPakar".equals(hitButton)){
	        		logic.updatePakar(idPakar, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
	        	}
	        	if ("doHapusPakar".equals(hitButton)){
	        		logic.removePakar(idPakar);
	        	}        	
	        	if ("doSimpanKoordinat".equals(hitButton)){
	        		idKoordinat = logic.saveKoordinat(idPermohonan, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
	        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
	        	}
	        	if ("doSimpanKemaskiniKoordinat".equals(hitButton)){
	        		logic.updateKoordinat(idKoordinat, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
	        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
	        	}
	        	if ("doHapusKoordinat".equals(hitButton)){
	        		logic.removeKoordinat(idKoordinat);
	        	}
	        	if ("doHantarEmel".equals(hitButton)){			
					logic.updatePermohonanEmel(idFail,idPermohonan,session);
				}
				if ("doHapus".equals(hitButton)){
					logic.hapusPermohonan(idFail);
				}
        }
			
		if ("papar".equals(actionOnline)){
			
			//GO TO VIEW APB        	
        	vm = "app/php2/online/frmAPBDaftarManual.jsp";
   
        	mode = "view";
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");
        	
        	//MAKLUMAT PERMOHONAN
			logic.setMaklumatPermohonanDaftar(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashMaklumatPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				
				idNegeri = (String) hashMaklumatPermohonan.get("idNegeri");
				idKaitanTujuan = (String) hashMaklumatPermohonan.get("idKaitanTujuan");
				idFlagLuar = (String) hashMaklumatPermohonan.get("idFlagLuar");
				idLuas = (String) hashMaklumatPermohonan.get("idLuas");
			}
			
			this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"disabled", " class=\"disabled\""));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"disabled", " style=\"width:250px\" class=\"disabled\""));
        
        				
		} else if ("daftarBaru".equals(actionOnline)){
        	
        	//GO TO DAFTAR BARU APB        	
        	vm = "app/php2/online/frmAPBDaftarManual.jsp";
        	
        	mode = "new";
           	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
        	
        	//MAKLUMAT PERMOHONAN
        	this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			
			beanMaklumatPermohonan = new Vector();
			Hashtable hashMaklumatPermohonan = new Hashtable();
			hashMaklumatPermohonan.put("noPermohonan","");
			hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
			hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
			hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
			hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
			hashMaklumatPermohonan.put("tempoh", getParam("txtTempoh"));
			hashMaklumatPermohonan.put("pengalaman", getParam("txtPengalaman"));
			hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
			hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
			beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);	
        	

        } else if ("seterusnya".equals(actionOnline)){
        	
        	// GO TO MAKLUMAT PERMOHONAN  
       		vm = "app/php2/online/frmAPBMaklumatPermohonan.jsp";
       		
        	//HEADER
            beanHeader = new Vector();
            logic.setMaklumatHeader(idFail);
            beanHeader = logic.getBeanMaklumatHeader();
    		this.context.put("BeanHeader", beanHeader);
    		
    		if (beanHeader.size() != 0){
    			Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			idFail = (String) hashHeader.get("idFail");
    			idPermohonan = (String) hashHeader.get("idPermohonan");
    			idStatus = (String) hashHeader.get("idStatus");
    			idPemohon = (String)hashHeader.get("idPemohon");
    		}

	    		if ("0".equals(selectedTabUpper)){
	    			
	    			maklumatPermohonan(mode, idPermohonan);			
	    			maklumatProjek(mode, idPermohonan, idProjek);
	    			maklumatPakar(mode, idPermohonan, idPakar);
	    			maklumatKoordinat(mode, idPermohonan, idKoordinat);
	    			maklumatPengarah(mode, idPemohon, idPengarah);
	    			
	    			//SENARAI PROJEK
	    			logic.setSenaraiProjek(idPermohonan);
	    			senaraiProjek = logic.getListProjek();
	    			this.context.put("SenaraiProjek", senaraiProjek);
	        		
	        		//SENARAI TITIK KOORDINAT
	    			logic.setSenaraiKoordinat(idPermohonan);
	    			senaraiKoordinat = logic.getListKoordinat();
	    			this.context.put("SenaraiKoordinat", senaraiKoordinat);
	        		
	        		//SENARAI TITIK KOORDINAT
	    			logic.setSenaraiPakar(idPermohonan);
	    			senaraiPakar = logic.getListPakar();
	    			this.context.put("SenaraiPakar", senaraiPakar);
	    			
	    			//SENARAI PENGARAH
	        		logic.setSenaraiPengarah(idPemohon);
	        		senaraiPengarah = logic.getListPengarah();
	        		this.context.put("SenaraiPengarah", senaraiPengarah);
	            	
	            } else if ("1".equals(selectedTabUpper)){
	            	
	            	maklumatPembeliPasir(mode, idPembeliPasir, idPermohonan);
	            	
	            	//SENARAI PEMBELI PASIR
	        		logic.setSenaraiPembeliPasir(idPermohonan);
	        		senaraiPembeliPasir = logic.getListPembeliPasir();
	        		this.context.put("SenaraiPembeliPasir", senaraiPembeliPasir);
	            }
        }
        else {
    		    		
     		//GO TO LIST FAIL APB       	
        	vm = "app/php2/online/frmAPBSenaraiFail.jsp";
        	
        	logic.carianFail(userId,getParam("txtNoPermohonan"),getParam("txdTarikhPermohonan"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
			this.context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));

			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionOnline", actionOnline);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idPengarah", idPengarah);
		this.context.put("idPembeliPasir", idPembeliPasir);
	    this.context.put("idProjek", idProjek);
	    this.context.put("idPakar", idPakar);
	    this.context.put("idKoordinat", idKoordinat);
	    this.context.put("idPemohon", idPemohon);
	    
		return vm;
	}
	
	public void setupPage(HttpSession session,String action,Vector list) {
		
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
				this.context.put("SenaraiFail",paging.getPage(page));
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
	
	private void maklumatPermohonan(String mode, String idPermohonan) throws Exception, Exception {
		
		Vector beanMaklumatPermohonan = null;
		
		//GET DROPDOWN PARAM
		String idKaitanTujuan = getParam("socKaitanTujuan");
		if (idKaitanTujuan == null || idKaitanTujuan.trim().length() == 0){
			idKaitanTujuan = "99999";
		}
		String idTempoh = getParam("socTempoh");
		if (idTempoh == null || idTempoh.trim().length() == 0){
			idTempoh = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idFlagLuar = "1";
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		if ("update".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
        	
        	//MAKLUMAT PERMOHONAN
        	this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			
			beanMaklumatPermohonan = new Vector();
			Hashtable hashMaklumatPermohonan = new Hashtable();
			hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
			hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
			hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
			hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
			hashMaklumatPermohonan.put("tempoh", getParam("socTempoh"));
			hashMaklumatPermohonan.put("pengalaman", getParam("txtRingkasanPengalaman"));
			hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
			hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
			hashMaklumatPermohonan.put("modalSemasa", getParam("txtModalSemasa"));
			hashMaklumatPermohonan.put("modalSedia", getParam("txtModalSedia"));
			hashMaklumatPermohonan.put("modalBenar", getParam("txtModalBenar"));
			hashMaklumatPermohonan.put("modalJelas", getParam("txtModalJelas"));
			beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);		
		
		} else if ("view".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");
			
			//MAKLUMAT PERMOHONAN
			logic.setMaklumatPermohonan(idPermohonan);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashMaklumatPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				
				idNegeri = (String) hashMaklumatPermohonan.get("idNegeri");
				idKaitanTujuan = (String) hashMaklumatPermohonan.get("idKaitanTujuan");
				idFlagLuar = (String) hashMaklumatPermohonan.get("idFlagLuar");
				idLuas = (String) hashMaklumatPermohonan.get("idLuas");
			}
			
			this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"disabled", " class=\"disabled\""));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"disabled", " style=\"width:250px\" class=\"disabled\""));
		}		
	}

	private void maklumatPembeliPasir(String mode, String idPembeliPasir, String idPermohonan) throws Exception {
		
		Vector beanMaklumatPembeliPasir = null;
        
        //GET DROPDOWN PARAM
		String idNegeri = getParam("socNegeriPembeliPasir");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandarPembeliPasir");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}
		String idJenisPerjanjian = getParam("socJenisPerjanjian");
		if (idJenisPerjanjian == null || idJenisPerjanjian.trim().length() == 0){
			idJenisPerjanjian = "99999";
		}
		
		if ("updatePembeliPasir".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMBELI PASIR
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), ""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "", " "));
			
			beanMaklumatPembeliPasir = new Vector();
			Hashtable hashPembeliPasir = new Hashtable();
			hashPembeliPasir.put("nama", getParam("txtNamaPembeliPasir"));		
			hashPembeliPasir.put("alamat1", getParam("txtAlamat1PembeliPasir"));
			hashPembeliPasir.put("alamat2", getParam("txtAlamat2PembeliPasir"));
			hashPembeliPasir.put("alamat3", getParam("txtAlamat3PembeliPasir"));
			hashPembeliPasir.put("poskod", getParam("txtPoskodPembeliPasir"));
			hashPembeliPasir.put("noTel", getParam("txtNoTelPembeliPasir"));
			hashPembeliPasir.put("noFax", getParam("txtNoFaxPembeliPasir"));			
			beanMaklumatPembeliPasir.addElement(hashPembeliPasir);
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);		
		
		} else if ("viewPembeliPasir".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PEMBELI PASIR
			logic.setMaklumatPembeliPasir(idPembeliPasir);
			beanMaklumatPembeliPasir = logic.getBeanMaklumatPembeliPasir();
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);
			
			if (logic.getBeanMaklumatPembeliPasir().size() != 0){
				Hashtable hashPembeliPasir = (Hashtable) logic.getBeanMaklumatPembeliPasir().get(0);
				
				idNegeri = (String) hashPembeliPasir.get("idNegeri");
				idBandar = (String) hashPembeliPasir.get("idBandar");
				idJenisPerjanjian = (String) hashPembeliPasir.get("idJenisPerjanjian");
			}
			
			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "disabled", " class=\"disabled\""));
			
		} else if ("newPembeliPasir".equals(mode)){
			
			String submit = getParam("command");   
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMBELI PASIR
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), ""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "", " "));
			
			beanMaklumatPembeliPasir = new Vector();
			Hashtable hashPembeliPasir = new Hashtable();
        	
        	if (!"".equals(submit)){
            	
        		hashPembeliPasir.put("nama", getParam("txtNamaPembeliPasir"));		
				hashPembeliPasir.put("alamat1", getParam("txtAlamat1PembeliPasir"));
				hashPembeliPasir.put("alamat2", getParam("txtAlamat2PembeliPasir"));
				hashPembeliPasir.put("alamat3", getParam("txtAlamat3PembeliPasir"));
				hashPembeliPasir.put("poskod", getParam("txtPoskodPembeliPasir"));
				hashPembeliPasir.put("noTel", getParam("txtNoTelPembeliPasir"));
				hashPembeliPasir.put("noFax", getParam("txtNoFaxPembeliPasir"));	
        	}
        	else{
        		
        		hashPembeliPasir.put("nama", "");		
    			hashPembeliPasir.put("alamat1", "");
    			hashPembeliPasir.put("alamat2", "");
    			hashPembeliPasir.put("alamat3", "");
    			hashPembeliPasir.put("poskod", "");
    			hashPembeliPasir.put("noTel", "");
    			hashPembeliPasir.put("noFax", "");
    			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong("99999"),"","onChange=\"doChangeNegeri();\""));
    			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong("99999"), ""));
    			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong("99999"), "", " "));
        	}
        	
        	beanMaklumatPembeliPasir.addElement(hashPembeliPasir);
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);
		}
	}

	private void maklumatPengarah(String mode, String idPemohon, String idPengarah) throws Exception {

		Vector beanMaklumatPengarah = null;
        
        //GET DROPDOWN PARAM
		String idWarganegara = getParam("socWarganegara");
		if (idWarganegara == null || idWarganegara.trim().length() == 0){
			idWarganegara = "99999";
		}
		String idJenisPengenalan = getParam("socJenisPengenalan");
		if (idJenisPengenalan == null || idJenisPengenalan.trim().length() == 0){
			idJenisPengenalan = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0){
			idBangsa = "99999";
		}
		
		if ("updatePengarah".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PENGARAH
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", ""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "", ""));
		
		} else if ("viewPengarah".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PENGARAH
			logic.setMaklumatPengarah(idPengarah);
			beanMaklumatPengarah = logic.getBeanMaklumatPengarah();
			this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
			
			if (logic.getBeanMaklumatPengarah().size() != 0){
				Hashtable hashPengarah = (Hashtable) logic.getBeanMaklumatPengarah().get(0);
				
				idJenisPengenalan = (String) hashPengarah.get("idJenisPengenalan");
				idWarganegara = (String) hashPengarah.get("idWarganegara");
				idBangsa = (String) hashPengarah.get("idBangsa");
			}
			
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "disabled", " class=\"disabled\""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "disabled", " class=\"disabled\""));
			
		} else if ("newPengarah".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PENGARAH
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong("99999"), "", ""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong("99999"), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong("99999"), "", ""));
			
			beanMaklumatPengarah = new Vector();
			Hashtable hashPengarah = new Hashtable();
			hashPengarah.put("nama", "");
			hashPengarah.put("noPengenalan","");
			hashPengarah.put("saham","");					
			beanMaklumatPengarah.addElement(hashPengarah);
			this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);	
		}
	}
	
	private void maklumatProjek(String mode, String idPermohonan, String idProjek) throws Exception {
		
		Vector beanMaklumatProjek = null;
		
		if ("updateProjek".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewProjek".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PROJEK
			logic.setMaklumatProjek(idProjek);
			beanMaklumatProjek = logic.getBeanMaklumatProjek();
			this.context.put("BeanMaklumatProjek", beanMaklumatProjek);
			
		} else if ("newProjek".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PROJEK			
			beanMaklumatProjek = new Vector();
			Hashtable hashProjek = new Hashtable();
			hashProjek.put("namaProjek","");					
			beanMaklumatProjek.addElement(hashProjek);
			this.context.put("BeanMaklumatProjek", beanMaklumatProjek);			
		}
	}
	
	private void maklumatPakar(String mode, String idPermohonan, String idPakar) throws Exception {

		Vector beanMaklumatPakar = null;
		
		if ("updatePakar".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewPakar".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT Pakar
			logic.setMaklumatPakar(idPakar);
			beanMaklumatPakar = logic.getBeanMaklumatPakar();
			this.context.put("BeanMaklumatPakar", beanMaklumatPakar);
			
		} else if ("newPakar".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT Pakar			
			beanMaklumatPakar = new Vector();
			Hashtable hashPakar = new Hashtable();
			
			hashPakar.put("nama", "");	
			hashPakar.put("kelayakan", "");		
			beanMaklumatPakar.addElement(hashPakar);
			this.context.put("BeanMaklumatPakar", beanMaklumatPakar);
		}
	}
	
	private void maklumatKoordinat(String mode, String idPermohonan, String idKoordinat) throws Exception {

		Vector beanMaklumatKoordinat = null;
		
		if ("updateKoordinat".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewKoordinat".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT KOORDINAT
			logic.setMaklumatKoordinat(idKoordinat);
			beanMaklumatKoordinat = logic.getBeanMaklumatKoordinat();
			this.context.put("BeanMaklumatKoordinat", beanMaklumatKoordinat);
			
		} else if ("newKoordinat".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT Koordinat			
			beanMaklumatKoordinat = new Vector();
			Hashtable hashKoordinat = new Hashtable();
			hashKoordinat.put("labelTitik", "");	
			hashKoordinat.put("darjahU", "");	
			hashKoordinat.put("minitU", "");	
			hashKoordinat.put("saatU", "");	
			hashKoordinat.put("darjahT", "");	
			hashKoordinat.put("minitT", "");	
			hashKoordinat.put("saatT", "");	
			beanMaklumatKoordinat.addElement(hashKoordinat);
			this.context.put("BeanMaklumatKoordinat", beanMaklumatKoordinat);			
		}
	}

}
