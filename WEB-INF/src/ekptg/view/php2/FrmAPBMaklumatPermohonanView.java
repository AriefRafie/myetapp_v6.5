/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBMaklumatPermohonanData;

/**
 * 
 *
 */
public class FrmAPBMaklumatPermohonanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBMaklumatPermohonanData logic = new FrmAPBMaklumatPermohonanData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String)session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String vm = ""; 
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");  
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idPengarah = getParam("idPengarah");
        String idPembeliPasir = getParam("idPembeliPasir");
        String idProjek = getParam("idProjek");
        String idPakar = getParam("idPakar");
        String idKoordinat = getParam("idKoordinat");
        String idKategoriPemohon = getParam("idKategoriPemohon");
        String noPermohonan = getParam("noPermohonan");
        String idKoordinat_original = getParam("idKoordinat_original");
        
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiPengarah = null;
        Vector senaraiPembeliPasir = null;
        Vector senaraiProjek = null;
        Vector senaraiKoordinat = null;
        Vector senaraiKoordinatHistory = null;
        Vector senaraiPakar = null;
        
		String step = getParam("step");
        
        vm = "app/php2/frmAPBMaklumatPermohonan.jsp";  
        
        //ACTION BUTTON
		if (postDB){
			if ("doSimpanKemaskiniPermohonan".equals(hitButton)){
				logic.updatePermohonan(idFail,idPermohonan,getParam("tarikhTerima"),getParam("tarikhSurat"),getParam("txtNoRujukanSurat"),getParam("txtPerkara"),getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"), getParam("txtTempoh"), getParam("socTempoh"),
						getParam("txtRingkasanPengalaman"), getParam("socFlagLuar"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), getParam("socLuas"),session);
        	}
        	if ("doSimpanKemaskiniPemohon".equals(hitButton)){
        		updatePemohon(idPemohon, session);
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
        		logic.removePengarah(idPengarah, session);
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
        		logic.removePembeliPasir(idPembeliPasir, session);
        	}        	
        	if ("doSimpanProjek".equals(hitButton)){
        		logic.saveProjek(idPermohonan, getParam("txtNamaProjek"), session);	
        	}
        	if ("doSimpanKemaskiniProjek".equals(hitButton)){
        		logic.updateProjek(idProjek, getParam("txtNamaProjek"), session);
        	}
        	if ("doHapusProjek".equals(hitButton)){
        		logic.removeProjek(idProjek, session);
        	}
        	if ("doSimpanPakar".equals(hitButton)){
        		idPakar = logic.savePakar(idPermohonan, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
        	}
        	if ("doSimpanKemaskiniPakar".equals(hitButton)){
        		logic.updatePakar(idPakar, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
        	}
        	if ("doHapusPakar".equals(hitButton)){
        		logic.removePakar(idPakar, session);
        	}        	
        	if ("doSimpanKoordinat".equals(hitButton)){
        		idKoordinat = logic.saveKoordinat(idKoordinat_original, idPermohonan, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
        	}
        	if ("doSimpanKemaskiniKoordinat".equals(hitButton)){
        		logic.updateKoordinat(idKoordinat, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
        	}
        	if ("doHapusKoordinat".equals(hitButton)){
        		logic.removeKoordinat(idKoordinat, session);
        	}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
    	}

        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan =(String)hashHeader.get("idPermohonan");
			noPermohonan =(String)hashHeader.get("noPermohonan");
			idPemohon = (String)hashHeader.get("idPemohon");
			idStatus =(String) hashHeader.get("idStatus");	
			idKategoriPemohon =(String)hashHeader.get("idKategoriPemohon");	
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
			
		}
		
		// GET FLAG OPEN DETAIL
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 2); // 2 = MAKLUMAT PERMOHONAN
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		if ("0".equals(selectedTabUpper)){
			
			maklumatPermohonan(mode, idPermohonan);			
			maklumatPakar(mode, idPermohonan, idPakar);
			maklumatKoordinat(mode, idPermohonan, idKoordinat);
			
    		//SENARAI TITIK KOORDINAT
			logic.setSenaraiKoordinat(idPermohonan);
			senaraiKoordinat = logic.getListKoordinat();
			this.context.put("SenaraiKoordinat", senaraiKoordinat);
			
			//SENARAI TITIK KOORDINAT (LAMA)
			logic.setSenaraiKoordinatHistory(idPermohonan);
			senaraiKoordinatHistory = logic.getListKoordinatHistory();
			this.context.put("SenaraiKoordinatHistory", senaraiKoordinatHistory);
    		
    		//SENARAI PAKAR
			logic.setSenaraiPakar(idPermohonan);
			senaraiPakar = logic.getListPakar();
			this.context.put("SenaraiPakar", senaraiPakar);
        	
        } else if ("1".equals(selectedTabUpper)){
        	
        	maklumatPemohon(mode, idFail);
        	maklumatProjek(mode, idPermohonan, idProjek);
        	maklumatPengarah(mode, idPemohon, idPengarah);
        	

        	//SENARAI PROJEK
			logic.setSenaraiProjek(idPermohonan);
			senaraiProjek = logic.getListProjek();
			this.context.put("SenaraiProjek", senaraiProjek);
			
        	//SENARAI PENGARAH
    		logic.setSenaraiPengarah(idPemohon);
    		senaraiPengarah = logic.getListPengarah();
    		this.context.put("SenaraiPengarah", senaraiPengarah);
        	
        } else if ("2".equals(selectedTabUpper)){
        	
        	maklumatPembeliPasir(mode, idPembeliPasir, idPermohonan);
        	
    		
        	//SENARAI PEMBELI PASIR
    		logic.setSenaraiPembeliPasir(idPermohonan);
    		senaraiPembeliPasir = logic.getListPembeliPasir();
    		this.context.put("SenaraiPembeliPasir", senaraiPembeliPasir);
        }
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("idPengarah", idPengarah);
        this.context.put("idPembeliPasir", idPembeliPasir);
        this.context.put("idProjek", idProjek);
        this.context.put("idPakar", idPakar);
        this.context.put("idKoordinat", idKoordinat);
        this.context.put("idKategoriPemohon", idKategoriPemohon);

		return vm;
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
		String idFlagLuar = getParam("socFlagLuar");
		if (idFlagLuar == null || idFlagLuar.trim().length() == 0){
			idFlagLuar = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		if ("update".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"", " "));
			this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"", " style=\"width:100px\""));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"", " style=\"width:100px\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			
			beanMaklumatPermohonan = new Vector();
			Hashtable hashMaklumatPermohonan = new Hashtable();
			hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
			hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
			hashMaklumatPermohonan.put("noRujSurat", getParam("txtNoRujukanSurat"));	
			hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
			hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
			hashMaklumatPermohonan.put("tempoh", getParam("txtTempoh"));
			hashMaklumatPermohonan.put("pengalaman", getParam("txtPengalaman"));
			hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
			hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
			hashMaklumatPermohonan.put("modalSemasa", getParam("txtModalSemasa"));
			hashMaklumatPermohonan.put("modalSedia", getParam("txtModalSedia"));	
			beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);		
		
		} else if ("view".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PERMOHONAN
			logic.setMaklumatPermohonan(idPermohonan);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashMaklumatPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				
				idNegeri = (String) hashMaklumatPermohonan.get("idNegeri");
				idTempoh = (String) hashMaklumatPermohonan.get("idTempoh");
				idKaitanTujuan = (String) hashMaklumatPermohonan.get("idKaitanTujuan");
				idFlagLuar = (String) hashMaklumatPermohonan.get("idFlagLuar");
				idLuas = (String) hashMaklumatPermohonan.get("idLuas");
			}
			
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"disabled", " style=\"width:100px\" class=\"disabled\""));
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
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeri("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
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
			
			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeri("socNegeriPembeliPasir",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "disabled", " class=\"disabled\""));
			
		} else if ("newPembeliPasir".equals(mode)){
			
			String submit = getParam("command");   
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMBELI PASIR
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeri("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
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
    			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeri("socNegeriPembeliPasir",Long.parseLong("99999"),"","onChange=\"doChangeNegeri();\""));
    			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong("99999"), ""));
    			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong("99999"), "", " "));
        	}
        	
        	beanMaklumatPembeliPasir.addElement(hashPembeliPasir);
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);
		}
	}

	private void maklumatPemohon(String mode, String idFail) throws Exception {
		
		Vector beanMaklumatPemohon = null;
        
        //GET DROPDOWN PARAM
        String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0){
			idKategoriPemohon = "99999";
		}
		String idJenisPengenalanIndividu = getParam("socJenisPengenalanIndividu");
		if (idJenisPengenalanIndividu == null || idJenisPengenalanIndividu.trim().length() == 0){
			idJenisPengenalanIndividu = "99999";
		}
		String idJantina = getParam("socJantina");
		if (idJantina == null || idJantina.trim().length() == 0){
			idJantina = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0){
			idBangsa = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}
		String idNegeriSykt = getParam("socNegeriSykt");
		if (idNegeriSykt == null || idNegeriSykt.trim().length() == 0){
			idNegeriSykt = "99999";
		}
		String idBandarSykt = getParam("socBandarSykt");
		if (idBandarSykt == null || idBandarSykt.trim().length() == 0){
			idBandarSykt = "99999";
		}
		
		if ("view".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idJenisPengenalanIndividu = (String) hashPemohon.get("idJenisPengenalan");
				idJantina = (String) hashPemohon.get("idJantina");
				idBangsa = (String) hashPemohon.get("idBangsa");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
				idNegeriSykt = (String) hashPemohon.get("idNegeriSykt");
				idBandarSykt = (String) hashPemohon.get("idBandarSykt");
			}
			
			this.context.put("selectKategoriPemohon",HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			//INDIVIDU
			this.context.put("selectJenisPengenalanIndividu",HTML.SelectJenisNoPbIndividu("socJenisPengenalanIndividu", Long.parseLong(idJenisPengenalanIndividu), "disabled", " class=\"disabled\""));
			this.context.put("selectJantina",HTML.SelectJantina("socJantina", Long.parseLong(idJantina), "disabled", " class=\"disabled\""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
			//SYARIKAT
			this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"disabled", " class=\"disabled\""));
			this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), "disabled", " class=\"disabled\""));
			
		} else if ("update".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon",HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "", " onChange=\"doChangeKategoriPemohon();\""));
			
			beanMaklumatPemohon = new Vector();
			Hashtable hashPemohon = new Hashtable();
			//INDIVIDU
			hashPemohon.put("nama", getParam("txtNama"));
			hashPemohon.put("noPengenalan", getParam("txtNoPengenalan"));
			hashPemohon.put("pekerjaan", getParam("txtPekerjaan"));			
			hashPemohon.put("alamat1", getParam("txtAlamat1"));
			hashPemohon.put("alamat2", getParam("txtAlamat2"));
			hashPemohon.put("alamat3", getParam("txtAlamat3"));
			hashPemohon.put("poskod", getParam("txtPoskod"));
			hashPemohon.put("noTel", getParam("txtNoTel"));
			hashPemohon.put("noTelBim", getParam("txtNoTelBimbit"));
			hashPemohon.put("noFax", getParam("txtNoFax"));
			hashPemohon.put("emel", getParam("txtEmel"));
			
			//SYARIKAT
			hashPemohon.put("namaSykt", getParam("txtNamaSykt"));
			hashPemohon.put("noPengenalanSykt", getParam("txtNoPengenalanSykt"));
			hashPemohon.put("pekerjaanSykt", getParam("txtPekerjaanSykt"));			
			hashPemohon.put("alamat1Sykt", getParam("txtAlamat1Sykt"));
			hashPemohon.put("alamat2Sykt", getParam("txtAlamat2Sykt"));
			hashPemohon.put("alamat3Sykt", getParam("txtAlamat3Sykt"));
			hashPemohon.put("poskodSykt", getParam("txtPoskodSykt"));
			hashPemohon.put("noTelSykt", getParam("txtNoTelSykt"));
			hashPemohon.put("noFaxSykt", getParam("txtNoFaxSykt"));
			hashPemohon.put("emelSykt", getParam("txtEmelSykt"));
			hashPemohon.put("modalBenar", getParam("txtModalBenar"));
			hashPemohon.put("modalJelas", getParam("txtModalJelas"));
			
			beanMaklumatPemohon.addElement(hashPemohon);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			//INDIVIDU
			this.context.put("selectJenisPengenalanIndividu",HTML.SelectJenisNoPbIndividu("socJenisPengenalanIndividu", Long.parseLong(idJenisPengenalanIndividu), ""));
			this.context.put("selectJantina",HTML.SelectJantina("socJantina", Long.parseLong(idJantina), ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), ""));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
			
			//SYARIKAT
			this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), ""));
			
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
	
	private void updatePemohon(String idPemohon, HttpSession session) throws Exception {		
		
		Hashtable hash = new Hashtable();
		
		hash.put("idKategoriPemohon", getParam("socKategoriPemohon"));
		
		hash.put("nama", getParam("txtNama"));
		hash.put("idJenisPengenalanIndividu", getParam("socJenisPengenalanIndividu"));
		hash.put("noPengenalan", getParam("txtNoPengenalan"));
		hash.put("pekerjaan", getParam("txtPekerjaan"));
		hash.put("idJantina", getParam("socJantina"));
		hash.put("idBangsa", getParam("socBangsa"));
		hash.put("alamat1", getParam("txtAlamat1"));
		hash.put("alamat2", getParam("txtAlamat2"));
		hash.put("alamat3", getParam("txtAlamat3"));		
		hash.put("poskod", getParam("txtPoskod"));
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("idBandar", getParam("socBandar"));		
		hash.put("noTel", getParam("txtNoTel"));
		hash.put("noTelBim", getParam("txtNoTelBimbit"));
		hash.put("noFax", getParam("txtNoFax"));
		hash.put("emel", getParam("txtEmel"));
		
		hash.put("namaSykt", getParam("txtNamaSykt"));
		hash.put("noPengenalanSykt", getParam("txtNoPengenalanSykt"));
		hash.put("pekerjaanSykt", getParam("txtPekerjaanSykt"));
		hash.put("alamat1Sykt", getParam("txtAlamat1Sykt"));
		hash.put("alamat2Sykt", getParam("txtAlamat2Sykt"));
		hash.put("alamat3Sykt", getParam("txtAlamat3Sykt"));		
		hash.put("poskodSykt", getParam("txtPoskodSykt"));
		hash.put("idNegeriSykt", getParam("socNegeriSykt"));
		hash.put("idBandarSykt", getParam("socBandarSykt"));		
		hash.put("noTelSykt", getParam("txtNoTelSykt"));
		hash.put("noFaxSykt", getParam("txtNoFaxSykt"));
		hash.put("emelSykt", getParam("txtEmelSykt"));
		hash.put("modalBenar", getParam("txtModalBenar"));
		hash.put("modalJelas", getParam("txtModalJelas"));
		
		logic.updatePemohon(idPemohon, hash, session);
	}
}
