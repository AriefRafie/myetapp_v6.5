/**
 * 
 */
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
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPMaklumatPermohonanData;
import ekptg.model.php2.FrmPNWPopupSenaraiTanahData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmPLPMaklumatPermohonanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private ILampiran iLampiran = null;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPMaklumatPermohonanData logic = new FrmPLPMaklumatPermohonanData();
	FrmPNWPopupSenaraiTanahData logicTanah = new FrmPNWPopupSenaraiTanahData();
	static Logger myLog = Logger.getLogger(FrmPLPMaklumatPermohonanView.class);

	String userId = null;
	String userRole = null;
	String idNegeriUser = null;
	
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;

	    userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
	    
	    //GET DEFAULT PARAM
	    String submit = getParam("command");  
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String step = getParam("step");
	    String vm = ""; 
		String flagReKeyin = "";
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true") || getParam("doPost").equals("true")) {
	        postDB = true;
	    }

        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
        String idTanahGanti = getParam("idTanahGanti");
        String idHakmilik = getParam("idHakmilik");
        String idDokumen = getParam("idDokumen");
        String flagBorangK = getParam("flagBorangK");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idHakmilikSementara = getParam("idHakmilikSementara");
        String idNegeriPemohon = getParam("idNegeriPemohon");
	    String idKementerianPemohon = getParam("idKementerianPemohon");
        
        //GET DROPDOWN PARAM
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idJenisProjek = getParam("socJenisProjek");
		if (idJenisProjek == null || idJenisProjek.trim().length() == 0){
			idJenisProjek = "99999";
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
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}
		String idJenisHakmilikTG = getParam("socJenisHakmilikTG");
		if (idJenisHakmilikTG == null || idJenisHakmilikTG.trim().length() == 0){
			idJenisHakmilikTG = "99999";
		}
		String idLotTG = getParam("socLotTG");
		if (idLotTG == null || idLotTG.trim().length() == 0){
			idLotTG = "99999";
		}
		String idLuasTG = getParam("socLuasTG");
		if (idLuasTG == null || idLuasTG.trim().length() == 0){
			idLuasTG = "99999";
		}
		String idNegeriTG = getParam("socNegeriTG");
		if (idNegeriTG == null || idNegeriTG.trim().length() == 0){
			idNegeriTG = "99999";
		}
		String idDaerahTG = getParam("socDaerahTG");
		if (idDaerahTG == null || idDaerahTG.trim().length() == 0){
			idDaerahTG = "99999";
		}
		String idMukimTG = getParam("socMukimTG");
		if (idMukimTG == null || idMukimTG.trim().length() == 0){
			idMukimTG = "99999";
		}
		String idKategoriTG = getParam("socKategoriTG");
		if (idKategoriTG == null || idKategoriTG.trim().length() == 0){
			idKategoriTG = "99999";
		}
		String idSubKategoriTG = getParam("socSubKategoriTG");
		if (idSubKategoriTG == null || idSubKategoriTG.trim().length() == 0){
			idSubKategoriTG = "99999";
		}		
		
        //VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBorangK = null;
        Vector beanMaklumatPelepasan = null;
        Vector beanMaklumatPemohon = null;
        Vector beanMaklumatAgensi = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiTanahGanti = null;
        Vector beanMaklumatTanahGanti = null;
        Vector senaraiTanahBerkaitan = null;
        Vector senaraiPelan = null;
        Vector beanMaklumatPelan = null; 
        Vector beanMaklumatLampiran = null;
        Vector senaraiLampiran = null;
        Vector senaraiSemak = null;
        
        FrmSemakan semak = null;
        
        vm = "app/php2/frmPLPMaklumatPermohonan.jsp";  
        
        if(hitButton.equals("refresh"))
        	hitButton = null;
        
        //SEND TO MODEL
    	if (postDB) {
    		if ("simpanDaftarHakmilikBaru".equals(hitButton)) {
//    			logicTanah.simpan(idPermohonan, idHakmilikAgensi, idHakmilikSementara, session);
    			logic.simpanDaftarHakmilikBaru(idPermohonan, idHakmilikAgensi, idHakmilikSementara, session);
    			hitButton = "refresh";
    		}
    		if ("doSimpanKemaskiniMaklumatPelepasan".equals(hitButton)) {
				logic.updatePermohonanPelepasan(idFail, idPermohonan,
						getParam("tarikhTerima"), getParam("tarikhSurat"), getParam("txtNoRujukanSurat"),
						getParam("txtNoFailNegeri"), getParam("txtPerkara"), idPermohonanPelepasan,
						idJenisProjek, getParam("txtNamaProjek"),
						idLuasKegunaan,
						idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
						session);
			}
			if ("doSimpanKemaskiniMaklumatKemajuanTanah".equals(hitButton)) {
				logic.updateKemajuanTanah(idPermohonanPelepasan,getParam("txtKemajuanTanah"), session);
			}
			if ("doSimpanKemaskiniMaklumatPemohon".equals(hitButton)) {
				logic.updatePemohon(idPemohon, idKategoriPemohon,
						getParam("txtNama"), getParam("txtNoPendaftaran"),
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idBandar, idNegeri, getParam("txtEmel"),
						getParam("txtNoTel"), getParam("txtNoFaks"), idPejabat, idKementerian, idAgensi, 
						session);
			}
			if ("doSimpanTanahGanti".equals(hitButton)) {
				logic.saveTanahGanti(idPermohonan,
						getParam("txtPeganganHakmilikTG"),
						getParam("txtNoHakmilikTG"), getParam("txtNoLotTG"),
						getParam("txtLuas1TG"), getParam("txtLuas2TG"),
						getParam("txtLuas3TG"),
						getParam("txtLuasBersamaanTG"),
						getParam("txtNoSyitTG"), getParam("txtNoPelanTG"),
						getParam("txtSyaratTG"), getParam("txtSekatanTG"),
						getParam("txtKegunaanTanahTG"), idJenisHakmilikTG,
						idLotTG, idLuasTG, idNegeriTG, idDaerahTG, idMukimTG,
						idKategoriTG, idSubKategoriTG, session);
				flagReKeyin = "Y";
    			hitButton = "refresh";
			}
			if ("doSimpanKemaskiniTanahGanti".equals(hitButton)) {
				logic.updateTanahGanti(idTanahGanti,
						getParam("txtPeganganHakmilikTG"),
						getParam("txtNoHakmilikTG"), getParam("txtNoLotTG"),
						getParam("txtLuas1TG"), getParam("txtLuas2TG"),
						getParam("txtLuas3TG"),
						getParam("txtLuasBersamaanTG"),
						getParam("txtNoSyitTG"), getParam("txtNoPelanTG"),
						getParam("txtSyaratTG"), getParam("txtSekatanTG"),
						getParam("txtKegunaanTanahTG"), idJenisHakmilikTG,
						idLotTG, idLuasTG, idNegeriTG, idDaerahTG, idMukimTG,
						idKategoriTG, idSubKategoriTG, session);
    			hitButton = "refresh";
			}  
			if ("doHapusTanahGanti".equals(hitButton)){
				logic.hapusTanahGanti(idTanahGanti, session);
			}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		} 
        	if ("doHapus".equals(hitButton)){
        		logic.doHapus(idPermohonan, getParam("idHakmilikPermohonan"), session);
    		}
        	if ("doHapusFail".equals(hitButton)){
    			logic.doHapusFail(idFail, idPermohonan, getParam("tarikhHapus"), getParam("txtSebab"), session);
    			step = "";
    			vm = "app/php2/frmPLPSenaraiFail.jsp";
    		}
        	if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
//        		String semaks [] = this.request.getParameterValues("idsSenaraiSemak");
//    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
        		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
        		
        		FrmSemakan frmSemak = new FrmSemakan();
    			frmSemak.semakanHapusByPermohonan(idPermohonan);
    			if (cbsemaks != null) {
    				for (int i = 0; i < cbsemaks.length; i++) {
    					FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
    				}
    			}
        	}
        	if ("simpanLampiran".equals(hitButton)) {
				uploadLampiran(idPermohonan, session);
			}
			if ("simpanKemaskiniLampiran".equals(hitButton)) {
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaPelan"), getParam("txtCatatanPelan"), session);
			}
			if ("hapusLampiran".equals(hitButton)) {
				logic.hapusLampiran(idDokumen, session);
			}
        	if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, session);
    		} 
        	
    	}
    	
    	this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phppelepasan"));
    	
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idPemohon = (String) hashPermohonan.get("idPemohon");
			idStatus = (String) hashPermohonan.get("idStatus");
			idNegeriPemohon = (String) hashPermohonan.get("idNegeriPemohon");
			idKementerianPemohon = (String) hashPermohonan.get("idKementerianPemohon");
		}
		
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
		
        //VIEW MODE
        if ("view".equals(mode)){
        	
        	//OPEN POPUP DAFTAR BARU TANAH GANTI
        	if ("openPopupTanahGanti".equals(flagPopup)){
        		
        		//MODEPOPUP = NEW
        		if ("new".equals(modePopup)){
        			this.context.put("readonlyPopup", "");
        			this.context.put("inputTextClassPopup", "");
        			this.context.put("disabledPopup", "");
        			
        			if ("doChangeNegeri".equals(submit)){
        				idDaerahTG = "99999";
        				idMukimTG = "99999";
        			}
        			            		
        			beanMaklumatTanahGanti = new Vector();
        			Hashtable hashTanahGanti = new Hashtable();
        			
        			if ("Y".equals(flagReKeyin)){
        				hashTanahGanti.put("peganganHakmilik", "");
            			hashTanahGanti.put("noHakmilik", "");
            			hashTanahGanti.put("noLot", "");
            			hashTanahGanti.put("luas1", "");
            			hashTanahGanti.put("luas2", "");
            			hashTanahGanti.put("luas3", "");
            			hashTanahGanti.put("luasBersamaan", "");
            			hashTanahGanti.put("noSyit", "");
            			hashTanahGanti.put("noPelan", "");
            			hashTanahGanti.put("syarat", "");
            			hashTanahGanti.put("sekatan", "");
            			hashTanahGanti.put("kegunaanTanah", "");
            			
            			idJenisHakmilikTG = "99999";
            			idLotTG = "99999";
            			idLuasTG = "99999";
            			idNegeriTG = "99999";
            			idDaerahTG = "99999";
            			idMukimTG = "99999";
            			idKategoriTG = "99999";
            			idSubKategoriTG = "99999";	
        			} else {
        				hashTanahGanti.put("peganganHakmilik", getParam("txtPeganganHakmilikTG"));
            			hashTanahGanti.put("noHakmilik", getParam("txtNoHakmilikTG"));
            			hashTanahGanti.put("noLot", getParam("txtNoLotTG"));
            			if ("doChangeLuas".equals(submit)){
            				hashTanahGanti.put("luas1", "");
                			hashTanahGanti.put("luas2", "");
                			hashTanahGanti.put("luas3", "");
                			hashTanahGanti.put("luasBersamaan", "");
            			} else {
            				hashTanahGanti.put("luas1", getParam("txtLuas1TG"));
                			hashTanahGanti.put("luas2", getParam("txtLuas2TG"));
                			hashTanahGanti.put("luas3", getParam("txtLuas3TG"));
                			hashTanahGanti.put("luasBersamaan", getParam("txtLuasBersamaanTG"));
            			}
            			hashTanahGanti.put("noSyit", getParam("txtNoSyitTG"));
            			hashTanahGanti.put("noPelan", getParam("txtNoPelanTG"));
            			hashTanahGanti.put("syarat", getParam("txtSyaratTG"));
            			hashTanahGanti.put("sekatan", getParam("txtSekatanTG"));
            			hashTanahGanti.put("kegunaanTanah", getParam("txtKegunaanTanahTG"));
        			}
        			
        			
        			beanMaklumatTanahGanti.addElement(hashTanahGanti);
    				this.context.put("BeanMaklumatTanahGanti", beanMaklumatTanahGanti);
        			
       				this.context.put("selectJenisHakmilikTG", HTML.SelectJenisHakmilik("socJenisHakmilikTG", Long.parseLong(idJenisHakmilikTG), ""));
    				this.context.put("selectLotTG", HTML.SelectLot("socLotTG",Long.parseLong(idLotTG), ""));
    				this.context.put("selectNegeriTG", HTML.SelectNegeri("socNegeriTG", Long.parseLong(idNegeriTG), "", " onChange=\"doChangeNegeri();\""));
    				this.context.put("selectDaerahTG", HTML.SelectDaerahByNegeri(idNegeriTG, "socDaerahTG", Long.parseLong(idDaerahTG), "", " onChange=\"doChangeDaerah();\""));
    				this.context.put("selectMukimTG", HTML.SelectMukimByDaerah(idDaerahTG, "socMukimTG", Long.parseLong(idMukimTG), ""));
    				this.context.put("selectKategoriTG", HTML.SelectKategoriTanah("socKategoriTG", Long.parseLong(idKategoriTG), "", " onChange=\"doChangeKategori();\""));
    				this.context.put("selectSubKategoriTG", HTML.SelectSubKategoriTanah(idKategoriTG, "socSubKategoriTG", Long.parseLong(idSubKategoriTG), "", ""));
        		}
        		
        		//MODEPOPUP = VIEW
        		if ("view".equals(modePopup)){
        			this.context.put("readonlyPopup", "readonly");
        			this.context.put("inputTextClassPopup", "disabled");
        			this.context.put("disabledPopup", "disabled");
        			
        			beanMaklumatTanahGanti = new Vector();
        			logic.setMaklumatTanahGanti(idTanahGanti);
        			beanMaklumatTanahGanti = logic.getBeanMaklumatTanahGanti();
    				this.context.put("BeanMaklumatTanahGanti", beanMaklumatTanahGanti);
    				if (beanMaklumatTanahGanti.size() != 0){
    					Hashtable hashTanahGanti = (Hashtable) logic.getBeanMaklumatTanahGanti().get(0);
    					
    					idJenisHakmilikTG = (String) hashTanahGanti.get("idJenisHakmilik");
    					idLotTG = (String) hashTanahGanti.get("idLot");
    					idNegeriTG = (String) hashTanahGanti.get("idNegeri");
    					idDaerahTG = (String) hashTanahGanti.get("idDaerah");
    					idMukimTG = (String) hashTanahGanti.get("idMukim");
    					idKategoriTG = (String) hashTanahGanti.get("idKategori");
    					idSubKategoriTG = (String) hashTanahGanti.get("idSubKategori");
    					idLuasTG = (String) hashTanahGanti.get("idLuas");
    				}
    				
    				this.context.put("selectJenisHakmilikTG", HTML.SelectJenisHakmilik("socJenisHakmilikTG", Long.parseLong(idJenisHakmilikTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectLotTG", HTML.SelectLot("socLotTG",Long.parseLong(idLotTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectNegeriTG", HTML.SelectNegeri("socNegeriTG", Long.parseLong(idNegeriTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectDaerahTG", HTML.SelectDaerahByNegeri(idNegeriTG, "socDaerahTG", Long.parseLong(idDaerahTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectMukimTG", HTML.SelectMukimByDaerah(idDaerahTG, "socMukimTG", Long.parseLong(idMukimTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectKategoriTG", HTML.SelectKategoriTanah("socKategoriTG", Long.parseLong(idKategoriTG), "disabled", " class=\"disabled\""));
    				this.context.put("selectSubKategoriTG", HTML.SelectSubKategoriTanah(idKategoriTG, "socSubKategoriTG", Long.parseLong(idSubKategoriTG), "disabled", " class=\"disabled\""));
        		}
        		
        		//MODEPOPUP = UPDATE
        		if ("update".equals(modePopup)){
        			this.context.put("readonlyPopup", "");
        			this.context.put("inputTextClassPopup", "");
        			this.context.put("disabledPopup", "");
        			
        			if ("doChangeNegeri".equals(submit)){
        				idDaerahTG = "99999";
        				idMukimTG = "99999";
        			}
        			            		
        			beanMaklumatTanahGanti = new Vector();
        			Hashtable hashTanahGanti = new Hashtable();
        			
        			hashTanahGanti.put("peganganHakmilik", getParam("txtPeganganHakmilikTG"));
        			hashTanahGanti.put("noHakmilik", getParam("txtNoHakmilikTG"));
        			hashTanahGanti.put("noLot", getParam("txtNoLotTG"));
        			hashTanahGanti.put("luas1", getParam("txtLuas1TG"));
        			hashTanahGanti.put("luas2", getParam("txtLuas2TG"));
        			hashTanahGanti.put("luas3", getParam("txtLuas3TG"));
        			hashTanahGanti.put("luasBersamaan", getParam("txtLuasBersamaanTG"));
        			hashTanahGanti.put("noSyit", getParam("txtNoSyitTG"));
        			hashTanahGanti.put("noPelan", getParam("txtNoPelanTG"));
        			hashTanahGanti.put("syarat", getParam("txtSyaratTG"));
        			hashTanahGanti.put("sekatan", getParam("txtSekatanTG"));
        			hashTanahGanti.put("kegunaanTanah", getParam("txtKegunaanTanahTG"));
        			
        			
        			beanMaklumatTanahGanti.addElement(hashTanahGanti);
    				this.context.put("BeanMaklumatTanahGanti", beanMaklumatTanahGanti);
        			
       				this.context.put("selectJenisHakmilikTG", HTML.SelectJenisHakmilik("socJenisHakmilikTG", Long.parseLong(idJenisHakmilikTG), ""));
    				this.context.put("selectLotTG", HTML.SelectLot("socLotTG",Long.parseLong(idLotTG), ""));
    				this.context.put("selectNegeriTG", HTML.SelectNegeri("socNegeriTG", Long.parseLong(idNegeriTG), "", " onChange=\"doChangeNegeri();\""));
    				this.context.put("selectDaerahTG", HTML.SelectDaerahByNegeri(idNegeriTG, "socDaerahTG", Long.parseLong(idDaerahTG), "", " onChange=\"doChangeDaerah();\""));
    				this.context.put("selectMukimTG", HTML.SelectMukimByDaerah(idDaerahTG, "socMukimTG", Long.parseLong(idMukimTG), ""));
    				this.context.put("selectKategoriTG", HTML.SelectKategoriTanah("socKategoriTG", Long.parseLong(idKategoriTG), "", " onChange=\"doChangeKategori();\""));
    				this.context.put("selectSubKategoriTG", HTML.SelectSubKategoriTanah(idKategoriTG, "socSubKategoriTG", Long.parseLong(idSubKategoriTG), "", ""));	
        		}
        	}
        	
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
        	
        	//MAKLUMAT PELEPASAN
			beanMaklumatPelepasan = new Vector();
			logic.setMaklumatPelepasan(idPermohonan);
			beanMaklumatPelepasan = logic.getBeanMaklumatPelepasan();
			this.context.put("BeanMaklumatPelepasan", beanMaklumatPelepasan);
			if (beanMaklumatPelepasan.size() != 0){
    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPelepasan().get(0);
    			idPermohonanPelepasan = (String)(hashMaklumatPelepasan.get("idPermohonanPelepasan"));
        		if (hashMaklumatPelepasan.get("flagGuna") != null && hashMaklumatPelepasan.get("flagGuna").toString().trim().length() != 0){
        			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
        		} else {
        			idLuasKegunaan = "99999";
        		}
        		if (hashMaklumatPelepasan.get("flagJenisProjek") != null && hashMaklumatPelepasan.get("flagJenisProjek").toString().trim().length() != 0){
        			idJenisProjek = (String) hashMaklumatPelepasan.get("flagJenisProjek");
        		} else {
        			idJenisProjek = "99999";
        		}
        		if (hashMaklumatPelepasan.get("idLuasMohon") != null && hashMaklumatPelepasan.get("idLuasMohon").toString().trim().length() != 0){
        			idLuas = (String) hashMaklumatPelepasan.get("idLuasMohon");
        		} else {
        			idLuas = "99999";
        		}
    		}
			
			this.context.put("selectJenisProjek",HTML.SelectJenisProjek("socJenisProjek", Long.parseLong(idJenisProjek), "disabled", " class=\"disabled\" style=\"width:auto\""));
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
    		
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idPemohon);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				
			} else 
			if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon)) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				if ("8".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("4".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					
				} else if ("5".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
			}			

			//SENARAI TANAH GANTI
			senaraiTanahGanti = new Vector();
			logic.setSenaraiTanahGanti(idPermohonan);
			senaraiTanahGanti = logic.getListTanahGanti();
			this.context.put("SenaraiTanahGanti", senaraiTanahGanti);	
			
			//SENARAI TANAH BERKAITAN
			senaraiTanahBerkaitan = new Vector();
			logic.setSenaraiTanahBerkaitan(idPermohonan);
			senaraiTanahBerkaitan = logic.getListTanahBerkaitan();
			this.context.put("SenaraiTanahBerkaitan", senaraiTanahBerkaitan);
			
			//SENARAI SEMAK
			semak = new FrmSemakan();
			semak.mode = mode;
			senaraiSemak = semak.getSenaraiSemakanAttach("phppelepasan",idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);
			this.context.put("mode", mode);
			
			//MAKLUMAT LAMPIRAN
			if ("6".equals(selectedTabUpper)){
				
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
			

        } else if ("update".equals(mode)){  //UPDATE MODE 
        	
        	this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");
            	
        	//MAKLUMAT PELEPASAN
        	beanMaklumatPelepasan = new Vector();
    		logic.setMaklumatPelepasan(idPermohonan);
    		Hashtable hashMaklumatPelepasanDB = (Hashtable) logic.getBeanMaklumatPelepasan().get(0);
			Hashtable hashMaklumatPelepasan = new Hashtable();
			hashMaklumatPelepasan.put("tarikhTerima", getParam("tarikhTerima"));
			hashMaklumatPelepasan.put("tarikhSurat", getParam("tarikhSurat"));
			hashMaklumatPelepasan.put("noRujukanSurat", getParam("txtNoRujukanSurat"));
			hashMaklumatPelepasan.put("noFailNegeri", getParam("txtNoFailNegeri"));
			hashMaklumatPelepasan.put("perkara", getParam("txtPerkara"));
			hashMaklumatPelepasan.put("namaProjek", getParam("txtNamaProjek"));
			hashMaklumatPelepasan.put("cadanganKegunaan", getParam("txtCadanganKegunaan"));
			hashMaklumatPelepasan.put("luasAsal", Utils.RemoveSymbol((String)hashMaklumatPelepasanDB.get("luasAsal")));
			hashMaklumatPelepasan.put("keteranganLuasAsal", hashMaklumatPelepasanDB.get("keteranganLuasAsal"));			
			if ("doChangeLuas".equals(submit)){
				hashMaklumatPelepasan.put("luas1", "");
				hashMaklumatPelepasan.put("luas2", "");
				hashMaklumatPelepasan.put("luas3", "");
				hashMaklumatPelepasan.put("luasBersamaan", "");
				hashMaklumatPelepasan.put("luasBaki", "");
			} else {
				hashMaklumatPelepasan.put("luas1", getParam("txtLuasMohon1"));
				hashMaklumatPelepasan.put("luas2", getParam("txtLuasMohon2"));
				hashMaklumatPelepasan.put("luas3", getParam("txtLuasMohon3"));
				if ("1".equals(idLuasKegunaan)){
					hashMaklumatPelepasan.put("luasBersamaan",  hashMaklumatPelepasanDB.get("luasAsal"));		
					hashMaklumatPelepasan.put("luasBaki", Utils.formatLuas(0D));
				} else {
					hashMaklumatPelepasan.put("luasBersamaan", getParam("txtLuasBersamaan"));			
					hashMaklumatPelepasan.put("luasBaki", getParam("txtBakiLuas"));		
				}
			}
			hashMaklumatPelepasan.put("kemajuanTanah", getParam("txtKemajuanTanah"));
			beanMaklumatPelepasan.addElement(hashMaklumatPelepasan);
           	this.context.put("BeanMaklumatPelepasan", beanMaklumatPelepasan);
        	
    		this.context.put("selectJenisProjek",HTML.SelectJenisProjek("socJenisProjek", Long.parseLong(idJenisProjek), "", " style=\"width:auto\""));
    		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
    		
    		// MAKLUMAT PEMOHON
    		if ("doChangeKategori".equals(submit)){
				idNegeri = "99999";
				idPejabat = "99999";
			}
			if ("doChangeNegeri".equals(submit)){
				idPejabat = "99999";
			}
    		if ("doChangeKementerian".equals(submit)){
				idAgensi = "99999";
			}
    		
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			} else if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon)) {
				
				beanMaklumatPemohon = new Vector();
				Hashtable hashPemohon = new Hashtable();
				hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
				hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				hashPemohon.put("jenisPerniagaan",getParam("txtJenisPerniagaan") == null ? "": getParam("txtJenisPerniagaan"));
				hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
				hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
				hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
				hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
				hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
				hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
				hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
				beanMaklumatPemohon.addElement(hashPemohon);
	
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				if ("8".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("4".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "1"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					
				} else if ("5".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
			}		
			
			semak = new FrmSemakan();
			semak.mode = mode;
			senaraiSemak = semak.getSenaraiSemakanAttach("phppelepasan",idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);
			this.context.put("mode", mode);
        }
        
        if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        if ("hapusFail".equals(step)){
        	vm = "app/php2/frmHapusFail.jsp";
        }
        
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("idPermohonanPelepasan", idPermohonanPelepasan);
        this.context.put("idKategoriPemohon", idKategoriPemohon);
        this.context.put("idNegeriPemohon", idNegeriPemohon);
	    this.context.put("idKementerianPemohon", idKementerianPemohon);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
		this.context.put("idLuas", idLuas);
		this.context.put("idTanahGanti", idTanahGanti);
		this.context.put("idLuasTG", idLuasTG);
		this.context.put("idHakmilik", idHakmilik);		
		this.context.put("flagBorangK",flagBorangK);
		this.context.put("statusRizab",statusRizab);
		this.context.put("idDokumen", idDokumen);
		this.context.put("step",step);
		this.context.put("hitButton",hitButton);
		
		if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
       
		return vm;
	}	
	
	private void uploadPelan(String idPermohonan, HttpSession session) throws Exception {
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
					savePelan(item, idPermohonan, session);
				}
			}
		}
	}
			
	private void savePelan(FileItem item, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		
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
			ps.setString(2, getParam("namaPelan"));
			ps.setString(3, getParam("catatanPelan"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, "P");
			ps.setString(9, idPermohonan);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL PELEPASAN [" + idPermohonan + "] DITAMBAH");
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
	
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
					"FAIL PELEPASAN [" + idPermohonan + "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
	
	private ILampiran getDocPHP() {
		if(iLampiran == null) {
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
}
