package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWMaklumatPermohonanData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

public class FrmPYWMaklumatPermohonanView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWMaklumatPermohonanData logic = new FrmPYWMaklumatPermohonanData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;
	
	@Override
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
	    
	    //GET DEFAULT PARAM
	    String submit = getParam("command");  
	    String vm = ""; 
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
		String flagReKeyin = "";
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idSuburusan = getParam("idSuburusan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idHakmilik = getParam("idHakmilik");
        String idPermohonanSewa = getParam("idPermohonanSewa");
        String idNegeriPemohon = getParam("idNegeriPemohon");
        String flagBorangK = getParam("flagBorangK");
        String idBorangK = getParam("flagBorangK");
        String idPPTBorangK = getParam("idPPTBorangK");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
        String idPHPBorangK = getParam("idPHPBorangK");
        String idJenisTanah = getParam("idJenisTanah");
        String flagSebabTamat = getParam("flagSebabTamat");
        String noFail = "";
        String idDokumen = getParam("idDokumen"); // ADD BY AIN MAKLUMAT LAMPIRAN
        String tujuan = getParam("tujuan");
   
        //GET DROPDOWN PARAM
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
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
		String idJenisTujuan = getParam("socJenisTujuan");
		if (idJenisTujuan == null || idJenisTujuan.trim().length() == 0) {
			idJenisTujuan = "99999";
		}
		String idJenisTujuan2 = getParam("socJenisTujuan2");
		if (idJenisTujuan2 == null || idJenisTujuan2.trim().length() == 0) {
			idJenisTujuan2 = "99999";
		}
		
		//VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBorangK = null;
        Vector beanMaklumatSewa = null;
        Vector beanMaklumatPemohon = null;
        Vector senaraiPengarah = null;
        Vector senaraiTanahBerkaitan = null;
        Vector beanSyorTolakRingkas = null;
        Vector beanMaklumatPenamatanPenyewaan = null;
        Vector senaraiSemak = null;
        Vector senaraiLampiran = null;
        Vector beanMaklumatLampiran = null;  
        
        String step = getParam("step");
        
        vm = "app/php2/frmPYWMaklumatPermohonan.jsp";  

		this.context.put("afterSave", "");
		this.context.put("completed", false);
		
        //SUBMIT TO DB
        if (postDB) {
        	if ("doSimpanKemaskiniMaklumatSewa".equals(hitButton)) {
				logic.updatePermohonanSewa(idFail, idPermohonan,
						getParam("tarikhTerima"), getParam("tarikhSurat"), getParam("txtNoRujukanSurat"),
						getParam("txtNoFailNegeri"), getParam("txtPerkara"), idPermohonanSewa, 
						getParam("txtTujuan"), idJenisTujuan, getParam("socTempohSewa"), 
						idLuasKegunaan, idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
						session);
			}
        	if ("doSimpanKemaskiniMaklumatPemohon".equals(hitButton)) {
				logic.updatePemohon(idPemohon, idKategoriPemohon,
						getParam("txtNama"), getParam("txtNamaPegawai"), getParam("txtNoPendaftaran"),
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idBandar, idNegeri, getParam("txtEmel"),
						getParam("txtNoTel"), getParam("txtNoFaks"),
						getParam("txtModalBenar"), getParam("txtModalJelas"), session);
			}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, idSuburusan, flagSebabTamat, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        	if ("doHapus".equals(hitButton)){
        		logic.doHapus(idPermohonan, getParam("idHakmilikPermohonan"), session);
    		}
        	
        	//syor tolak ringkas
        	if ("doSimpanSyorTolakRingkas".equals(hitButton)){
        		logic.doSimpanSyorTolakRingkas(idPermohonanSewa, idPermohonan ,getParam("sebabTolakRingkas"), getParam("tarikhSuratTolakRingkas"), getParam("catatanMaklumatTambahan"), session);
        		
        		this.context.put("readonlySyor", "disabled");
        		this.context.put("$inputTextSyorClass", "disabled");
    		}
        	if ("doPengesahanPengarahNegeri".equals(hitButton)){
        		logic.doPengesahanPengarahNegeri(idFail, idNegeriUser, session);
        		this.context.put("afterSave", "doPengesahanPengarahNegeri");
    		}
        	if ("doTangguhMaklumatTambahan".equals(hitButton)){
        		logic.doTangguhMaklumatTambahan(idFail, idNegeriUser, session);
        		this.context.put("afterSave", "doTangguhMaklumatTambahan");
    		}
        	if ("doSetujuTolakRingkas".equals(hitButton)){
        		logic.doSetujuTolakRingkas(idPermohonan,idFail, idNegeriUser, session);
    		}
        	if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
        		String semaks [] = this.request.getParameterValues("idsSenaraiSemak");
    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
        	}
        	if ("simpanLampiran".equals(hitButton)) {
				uploadLampiran(idPermohonan, session);
			}
			if ("simpanKemaskiniLampiran".equals(hitButton)) {
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"), session);
			}
			if ("hapusLampiran".equals(hitButton)) {
				logic.hapusLampiran(idDokumen, session);
			}
        }    

        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idPemohon = (String) hashPermohonan.get("idPemohon");
			idStatus = (String) hashPermohonan.get("idStatus");
			idSuburusan = (String) hashPermohonan.get("idSuburusan");
			
			noFail = (String) hashPermohonan.get("noFail");
			
			if(hashPermohonan.get("flagSebabTamat")!=null && hashPermohonan.get("flagSebabTamat")!=""){
				flagSebabTamat = (String) hashPermohonan.get("flagSebabTamat");
			}
			if(hashPermohonan.get("idPermohonanSewa")!=null && hashPermohonan.get("idPermohonanSewa")!=""){
				idPermohonanSewa = (String) hashPermohonan.get("idPermohonanSewa");
			}
		}
		
		idJenisTujuan = logic.getIdTujuanByIdPermohonanSewa(idPermohonanSewa);

		String flagMT = logic.getFlagMT(idFail, userId);
		this.context.put("flagMT", flagMT);
		
		//MAKLUMAT HAKMILIK
		String statusRizab = "";
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idHakmilik = logicHeader.getIdHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			//ADD BY AIN - 18092017 - GET IDHTPHAKMILIK BAGI CETAKAN MAKLUMAT TANAH
			String idHTPHakmilik = logicHeader.getIdHTPHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			this.context.put("idHTPHakmilik", idHTPHakmilik);

			if ("".equals((String) hashHakmilik.get("noHakmilik"))){
				statusRizab = "RIZAB";
			} else {
				statusRizab = "MILIK";
			}
			flagBorangK = (String) hashHakmilik.get("flagBorangK");
			
		}
		this.context.put("flagBorangK", flagBorangK);
		
		if ("Y".equals(flagBorangK)){
			beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {
			beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		}	
		
		this.context.put("flagPopup", "");
		this.context.put("modePopup", "");

		this.context.put("readonlyTugasan", "disabled");
		this.context.put("inputTextTugasanClass", "disabled");
		
		//VIEW MODE
        if ("view".equals(mode)){

        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled"); 
        	this.context.put("readonlySyor", "readonly");
			this.context.put("inputTextSyorClass", "disabled");
        			
			//MAKLUMAT SEWA
			beanMaklumatSewa = new Vector();
			logic.setMaklumatSewa(idPermohonan);
			beanMaklumatSewa = logic.getBeanMaklumatSewa();
			this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
			if (beanMaklumatSewa.size() != 0){
    			Hashtable hashMaklumatSewa = (Hashtable) logic.getBeanMaklumatSewa().get(0);
    			idPermohonanSewa = (String)(hashMaklumatSewa.get("idPermohonanSewa"));
        		if (hashMaklumatSewa.get("flagGuna") != null && hashMaklumatSewa.get("flagGuna").toString().trim().length() != 0){
        			idLuasKegunaan = (String) hashMaklumatSewa.get("flagGuna");
        		} else {
        			idLuasKegunaan = "99999";
        		}
        		if (hashMaklumatSewa.get("idLuasMohon") != null && hashMaklumatSewa.get("idLuasMohon").toString().trim().length() != 0){
        			idLuas = (String) hashMaklumatSewa.get("idLuasMohon");
        		} else {
        			idLuas = "99999";
        		}
        		if(hashMaklumatSewa.get("tujuan") != null && hashMaklumatSewa.get("tujuan").toString().trim().length() != 0){
        			tujuan = (String) hashMaklumatSewa.get("tujuan");
        		} else {
        			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan", Long.parseLong(idJenisTujuan), "disabled", "class=\"disabled\""));
    				this.context.put("selectJenisTujuan2", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan2", Long.parseLong(idJenisTujuan2), "disabled", "class=\"disabled\""));
        		}
    		}

			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
			
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idPemohon);
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
  		
			//SENARAI PENGARAH
			senaraiPengarah = new Vector();
			logic.setSenaraiPengarah(idPemohon);
			senaraiPengarah = logic.getListPengarah();
			this.context.put("SenaraiPengarah", senaraiPengarah);	
			
			//SENARAI TANAH BERKAITAN
			senaraiTanahBerkaitan = new Vector();
			logic.setSenaraiTanahBerkaitan(idPermohonan);
			senaraiTanahBerkaitan = logic.getListTanahBerkaitan();
			this.context.put("SenaraiTanahBerkaitan", senaraiTanahBerkaitan);
			
			//SYOR TOLAK RINGKAS
			logic.setSyorTolakRingkas(idPermohonan, userId);
			beanSyorTolakRingkas = logic.getBeanSyorTolakRingkas();
			this.context.put("BeanSyorTolakRingkas", beanSyorTolakRingkas);
			
			//SENARAI SEMAK
			senaraiSemak = logic.getSenaraiSemak(idPermohonan, idKategoriPemohon);
			this.context.put("SenaraiSemak", senaraiSemak);
			
			//POPUP LAMPIRAN
			if ("5".equals(selectedTabUpper)) {
				
				if ("openPopupLampiran".equals(flagPopup)){
	        		
	        		if ("new".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			beanMaklumatLampiran = new Vector();    			
		    			Hashtable hashMaklumatLampiran = new Hashtable();
		    			hashMaklumatLampiran.put("namaLampiran", "");
		    			hashMaklumatLampiran.put("catatanLampiran", "");
		    			beanMaklumatLampiran.addElement(hashMaklumatLampiran);
						this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);
		    			
	        		} else if ("update".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			//MAKLUMAT LAMPIRAN
		    			beanMaklumatLampiran = new Vector();
						logic.setMaklumatLampiran(idDokumen);
						beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
						this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);
		    			
	        		} else if ("view".equals(modePopup)){
	        			
	        			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			//MAKLUMAT LAMPIRAN
		    			beanMaklumatLampiran = new Vector();
						logic.setMaklumatLampiran(idDokumen);
						beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
						this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);
	        		}
	        	} 
			}
			
			//SENARAI LAMPIRAN
			senaraiLampiran = new Vector();
			logic.setSenaraiLampiran(idPermohonan);
			senaraiLampiran = logic.getListLampiran();
			this.context.put("SenaraiLampiran", senaraiLampiran);
		

        } else if ("update".equals(mode)){ //UPDATE MODE
        	
        	this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");
    		
    		//MAKLUMAT PENYEWAAN
        	beanMaklumatSewa = new Vector();
    		logic.setMaklumatSewa(idPermohonan);
    		Hashtable hashMaklumatSewaDB = (Hashtable) logic.getBeanMaklumatSewa().get(0);
			Hashtable hashMaklumatSewa = new Hashtable();
			hashMaklumatSewa.put("tarikhTerima", getParam("tarikhTerima"));
			hashMaklumatSewa.put("tarikhSurat", getParam("tarikhSurat"));
			hashMaklumatSewa.put("noRujukanSurat", getParam("txtNoRujukanSurat"));
			hashMaklumatSewa.put("noFailNegeri", getParam("txtNoFailNegeri"));
			hashMaklumatSewa.put("perkara", getParam("txtPerkara")); 
			hashMaklumatSewa.put("tujuan", getParam("txtTujuan"));
			if(hashMaklumatSewa.get("tujuan") != null && hashMaklumatSewa.get("tujuan").toString().trim().length() != 0){
    			tujuan = (String) hashMaklumatSewa.get("tujuan");
    		} else {
    			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan", Long.parseLong(idJenisTujuan), "", " onChange=\"doChangeTujuan();\""));
    		}
			hashMaklumatSewa.put("catatan", getParam("txtCatatan"));
			hashMaklumatSewa.put("luasAsal", hashMaklumatSewaDB.get("luasAsal"));
			hashMaklumatSewa.put("keteranganLuasAsal", hashMaklumatSewaDB.get("keteranganLuasAsal"));			
			if ("doChangeLuas".equals(submit)){
				hashMaklumatSewa.put("luas1", "");
				hashMaklumatSewa.put("luas2", "");
				hashMaklumatSewa.put("luas3", "");
				hashMaklumatSewa.put("luasBersamaan", "");
				hashMaklumatSewa.put("luasBaki", "");
			} else {
				hashMaklumatSewa.put("luas1", getParam("txtLuasMohon1"));
				hashMaklumatSewa.put("luas2", getParam("txtLuasMohon2"));
				hashMaklumatSewa.put("luas3", getParam("txtLuasMohon3"));
				if ("1".equals(idLuasKegunaan)){
					hashMaklumatSewa.put("luasBersamaan",  hashMaklumatSewaDB.get("luasAsal"));		
					hashMaklumatSewa.put("luasBaki", Utils.formatLuas(0D));
				} else {
					hashMaklumatSewa.put("luasBersamaan", getParam("txtLuasBersamaan"));			
					hashMaklumatSewa.put("luasBaki", getParam("txtBakiLuas"));		
				}
			}
			hashMaklumatSewa.put("flagTempohSewa", getParam("socTempohSewa"));
			beanMaklumatSewa.addElement(hashMaklumatSewa);
           	this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
        
    		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));        	

        	//MAKLUMAT PEMOHON
    		beanMaklumatPemohon = new Vector();
			Hashtable hashPemohon = new Hashtable();
			hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
			hashPemohon.put("namaPegawai", getParam("txtNamaPegawai") == null ? "": getParam("txtNamaPegawai"));
			hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
			hashPemohon.put("pekerjaan",getParam("txtPekerjaan") == null ? "": getParam("txtPekerjaan"));
			hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
			hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
			hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
			hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
			hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
			hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
			hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
			hashPemohon.put("modalJelas", getParam("txtModalJelas") == null ? "": getParam("txtModalJelas"));
			hashPemohon.put("modalBenar", getParam("txtModalBenar") == null ? "": getParam("txtModalBenar"));
			beanMaklumatPemohon.addElement(hashPemohon);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
			
			senaraiSemak = logic.getSenaraiSemak(idPermohonan, idKategoriPemohon);
			this.context.put("SenaraiSemak", senaraiSemak);
	
        }
        
        if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        
        //SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idPermohonanSewa", idPermohonanSewa);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("idHakmilik", idHakmilik);
        this.context.put("idHakmilikAgensi", idHakmilikAgensi);
        this.context.put("idLuasKegunaan", idLuasKegunaan);
        this.context.put("idLuas", idLuas);
        this.context.put("idKategoriPemohon", idKategoriPemohon);
        this.context.put("idNegeriPemohon", idNegeriPemohon);   
        this.context.put("statusRizab",statusRizab);  
        this.context.put("flagBorangK",flagBorangK);
        this.context.put("idBorangK",idBorangK);
        this.context.put("idPPTBorangK", idPPTBorangK);
        this.context.put("idPHPBorangK", idPHPBorangK);
        this.context.put("idHakmilikUrusan", idHakmilikUrusan);
        this.context.put("idJenisTanah", idJenisTanah);
        this.context.put("userRole", userRole);
        this.context.put("flagSebabTamat", flagSebabTamat);
        this.context.put("idJenisTujuan", idJenisTujuan);
        this.context.put("idDokumen", idDokumen);
        this.context.put("step",step);
        
        if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
        if (!"".equals(noFail)){
        	session.setAttribute("NO_FAIL", noFail);
        }
        if (!"".equals(idNegeri)){
        	session.setAttribute("ID_NEGERI", idNegeri);
        }
        if (!"".equals(idBandar)){
        	session.setAttribute("ID_BANDAR", idBandar);
        }

		return vm;
	}
	
	// UPLOAD FILE
		private void uploadLampiran(String idPermohonan, HttpSession session) throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart != false) {
				List items = upload.parseRequest(request);
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if ((!(item.isFormField())) && (item.getName() != null)
							&& (!("".equals(item.getName())))) {
						saveLampiran(item, idPermohonan, session);
					}
				}
			}
		}
		
		private void saveLampiran(FileItem item, String idPermohonan, HttpSession session) throws Exception {

			Db db = null;
			String userId = (String) session.getAttribute("_ekptg_user_id");
			
			try {
				db = new Db();

				// TBLPHPDOKUMEN
				long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
								+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN) "
								+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
				ps.setLong(1, idDokumen);
				ps.setString(2, getParam("namaLampiran"));
				ps.setString(3, getParam("catatanLampiran"));
				ps.setString(4, userId);
				ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
				ps.setString(6, item.getContentType());
				ps.setString(7, item.getName());
				ps.setString(8, "L");
				ps.setString(9, idPermohonan);
				ps.executeUpdate();

				con.commit();
				
				AuditTrail.logActivity("1610198", "4", null, session, "INS",
						"FAIL [" + idPermohonan + "] DIDAFTARKAN");
				
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("completed", true);
		}
}
