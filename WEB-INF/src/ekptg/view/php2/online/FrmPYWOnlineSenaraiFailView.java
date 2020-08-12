package ekptg.view.php2.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.online.FrmPYWOnlineSenaraiFailData;
import ekptg.model.php2.utiliti.PHPUtilHTML;
import ekptg.view.ppt.FrmBantahanSenaraiOnline;

public class FrmPYWOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPYWOnlineSenaraiFailView.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		FrmPYWOnlineSenaraiFailData logic = new FrmPYWOnlineSenaraiFailData();
		FrmPYWHeaderData header = new FrmPYWHeaderData();
		FrmSemakan semak = null;

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String vm = "";
		
		String command = getParam("command");
		String flag_penyewaan = getParam("flag_penyewaan");
		this.context.put("flag_penyewaan", flag_penyewaan);
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);
		
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String submit = getParam("command");
		String actionPenyewaan = getParam("actionPenyewaan");
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		
		//GET ID PARAM
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String idPHPBorangK = getParam("idPHPBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPPTBorangK = getParam("idPPTBorangK");
        String idDokumen = getParam("idDokumen"); // ADD MAKLUMAT LAMPIRAN
		String idPermohonanSewa = getParam("idPermohonanSewa");
		String idPermohonanLama = getParam("idPermohonan");
		String idTujuanPermohonan = getParam("idTujuanPermohonan");
        String kategori = getParam("kategori");
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
                				
		Vector list = null;
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatTanah = null;
		Vector beanHeader = null;
		Vector beanMaklumatSewa = null;
		Vector beanMaklumatBorangK = null;
        Vector beanMaklumatLampiran = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiLampiran = null;
        Vector senaraiSemak = null;
		
        // GET DROPDOWN PARAM
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0) {
			idSuburusan = "99999";
		}
		String idSubsuburusan = getParam("socSubsuburusan");
		if (idSubsuburusan == null || idSubsuburusan.trim().length() == 0) {
			idSubsuburusan = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String idJenisPermohonan = getParam("socJenisPermohonan");
		if (idJenisPermohonan == null || idJenisPermohonan.trim().length() == 0) {
			idJenisPermohonan = "99999";
		}
		String idFailLama = getParam("socNoFailLama");
		if (idFailLama == null || idFailLama.trim().length() == 0) {
			idFailLama = "99999";
		}

		this.context.put("errorPeganganHakmilik", "");
		this.context.put("onload", "");
		
		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
				
		//SAVE TO DB
		if (postDB) {
			if ("doDaftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idUrusan, idSuburusan, idSubsuburusan, idHakmilikAgensi, idHakmilikSementara,
				getParam("txtNoRujukanSurat"), getParam("txttarikhSurat"), idJenisTanah, idPHPBorangK, idPPTBorangK,
				getParam("idKementerianTanah"), getParam("idNegeriTanah"), idHakmilikUrusan, getParam("tarikhTerima"),
				idJenisPermohonan,session, idPermohonanLama);
			}
			if ("doSimpanKemaskiniMaklumatTnh".equals(hitButton)){
        		logic.updateTanah(idPermohonan,idHakmilikAgensi,session);	
            }
			if("doSimpanSenaraiSemak".equals(hitButton)){
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"), 
						session);
			}
			if ("doSimpanKemaskiniMaklumatPenyewaan".equals(hitButton)){
        		logic.updatePermohonanSewa(idPermohonanSewa, idTujuanPermohonan, idSubsuburusan, getParam("socTempohSewa"), 
        				idLuasKegunaan, idLuas, getParam("txtLuasMohon1"), getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
						session);
        	}
			if ("doHantarEmel".equals(hitButton)){
				
				if (logic.checkMaklumatPywLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat penyewaan yang belum lengkap.')\"");	
				} else {
					logic.updatePermohonanEmel(idFail,idPermohonan,session);
				}				
			}
			if ("doHapus".equals(hitButton)){
				logic.hapusPermohonan(idFail);
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
			//SENARAI SEMAK
			if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
        		String semaks [] = this.request.getParameterValues("idsSenaraiSemak");
        		FrmSemakan frmSemak = new FrmSemakan();
        		frmSemak.semakanHapusByPermohonan(idPermohonan);
        		if (semaks != null) {
    				for (int i = 0; i < semaks.length; i++) {
    					FrmSemakan.semakanTambah(semaks[i], String.valueOf(idPermohonan));
    				}
    			}
        	}
		}
		
		//ajax command
		if ("showStatusPermohonanSewa".equals(submit)){
			String id_fail = getParam("id_fail");
			this.context.put("id_fail", id_fail);
			
			String namaPemohon = getParam("namaPemohon");
			this.context.put("namaPemohon", namaPemohon);
			
			vm = "app/php2/online/frmStatusPermohonanSewa.jsp";
		}
		else if ("paparMaklumatPenyewaan".equals(actionPenyewaan)){
			
			// GO TO MAKLUMAT PERMOHONAN  
			vm = "app/php2/online/frmPYWMaklumatPermohonan.jsp";
			
			this.context.put("afterSave", "");
			this.context.put("completed", false);
			
        	//HEADER
            beanHeader = new Vector();
            logic.setMaklumatHeader(idFail, session);
            beanHeader = logic.getBeanMaklumatHeader();
    		this.context.put("BeanHeader", beanHeader);
    		
    		if (beanHeader.size() != 0){
    			Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			idFail = (String) hashHeader.get("idFail");
    			idPermohonan = (String) hashHeader.get("idPermohonan");
    			idStatus = (String) hashHeader.get("idStatus");
    			idHakmilikAgensi = (String) hashHeader.get("idHakmilikAgensi");	
    		}
    		
    		// MODE VIEW
    		if("view".equals(mode)){
    			
    			this.context.put("readonly", "readonly");
    			this.context.put("inputTextClass", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			//MAKLUMAT TANAH
    			beanMaklumatTanah = new Vector();
    			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			
    			//MAKLUMAT PEMOHON
            	header = new FrmPYWHeaderData();
    			Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
    			this.context.put("pemohon", vec.get(0));
    						
    			//MAKLUMAT SEWA
    			beanMaklumatSewa = new Vector();
    			logic.setMaklumatSewa(idPermohonan);
    			beanMaklumatSewa = logic.getBeanMaklumatSewa();
    			this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
    			if (beanMaklumatSewa.size() != 0){
        			Hashtable hashMaklumatSewa = (Hashtable) logic.getBeanMaklumatSewa().get(0);
        			idPermohonanSewa = (String)(hashMaklumatSewa.get("idPermohonanSewa"));
        			idTujuanPermohonan = (String)(hashMaklumatSewa.get("idTujuanPermohonan"));
        			idUrusan = (String)(hashMaklumatSewa.get("idUrusan"));
        			idSuburusan = (String)(hashMaklumatSewa.get("idSuburusan"));
        			idSubsuburusan = (String)(hashMaklumatSewa.get("idTujuan"));
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
        		}
    			this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
    			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
    			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "disabled", " class=\"disabled\""));    			
    			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
    			
    			senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
    			this.context.put("SenaraiSemak", senaraiSemak);
    			
    			if ("2".equals(selectedTabUpper)) {
	            	this.context.put("completed", false);
	        		this.context.put("flagPopup", "closePopupLampiran");
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("apb",idPermohonan);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			
	    			if (mode.equals("update")){
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
	    			}
	    			else if (mode.equals("view")){
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
	        		}
	    			
	            }
    			
    			beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPermohonan);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
    			
    		} else if("update".equals(mode)){
    			
    			this.context.put("readonly", "");
        		this.context.put("inputTextClass", "");
        		this.context.put("disabled", "");        		
        		
        		if ("doChangeJenisTanah".equals(submit)){
    				idHakmilikAgensi = "";
    				idPHPBorangK = "";
    				idPPTBorangK = "";
    				idHakmilikUrusan = "";
    			}
        		
        		//MAKLUMAT TANAH
        		beanMaklumatTanah = new Vector();
    			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
    			beanMaklumatTanah = logic.getBeanMaklumatTanah();
    			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			
        		if ("doChangePeganganHakmilik1".equals(submit)){
    				beanMaklumatTanah = new Vector();
    				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik1"));
    				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
    				beanMaklumatTanah = logic.getBeanMaklumatTanah();
    				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    				this.context.put("idHakmilikAgensi", idHakmilikAgensi);
    				this.context.put("idKementerian", getParam("idKementerian"));
    				this.context.put("kodKementerian", getParam("kodKementerian"));
    				this.context.put("idNegeriTanah", getParam("idNegeriTanah"));
    				this.context.put("kodNegeriTanah", getParam("kodNegeriTanah"));
    				if (idHakmilikAgensi.isEmpty()){
    					this.context.put("errorPeganganHakmilik", "Hakmilik tidak wujud.");
    				}
    			} else if ("doChangeMaklumatTanah".equals(submit)){
    				beanMaklumatTanah = new Vector();
    				idHakmilikAgensi = getParam("idHakmilikAgensiPopup");
        			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
        			beanMaklumatTanah = logic.getBeanMaklumatTanah();
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
    			}
        		
        		//MAKLUMAT PENYEWAAN
            	beanMaklumatSewa = new Vector();
        		logic.setMaklumatSewa(idPermohonan);
        		Hashtable hashMaklumatSewaDB = (Hashtable) logic.getBeanMaklumatSewa().get(0);
    			Hashtable hashMaklumatSewa = new Hashtable();
    			hashMaklumatSewa.put("tarikhTerima", getParam("tarikhTerima"));
    			hashMaklumatSewa.put("tarikhSurat", getParam("tarikhSurat"));
    			hashMaklumatSewa.put("perkara", getParam("txtPerkara"));
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
               	
               	this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
               	this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan,"socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
    			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "", " onChange=\"doChangeSubsuburusan();\""));
    			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));        	       	
        		
        		senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
    			this.context.put("SenaraiSemak", senaraiSemak);
    			
    		}
			
		} else if ("papar".equals(actionPenyewaan)){
			
			vm = "app/php2/online/frmPYWDaftarOnline.jsp";
			
			mode = "view";
			this.context.put("mode", "view");
        	this.context.put("readonly", "disabled");
        	this.context.put("inputTextClass", "disabled");
        	
        	if("2".equals(idJenisPermohonan)){
			this.context.put("selectNoFailLama", PHPUtilHTML.SelectNoFailByIdPemohon(id_user, "socNoFailLama", Long.parseLong(idFailLama), "disabled", " class=\"disabled\""));  
        	}
			myLogger.info("id Fail Lama : "+idFailLama);
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFailLama);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
	
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idUrusan = (String) hashPermohonan.get("idUrusan");
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
				idSubsuburusan = (String) hashPermohonan.get("idTujuan");
				idPermohonan= (String) hashPermohonan.get("idPermohonan");
			}
			this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "disabled", " class=\"disabled\""));

			//MAKLUMAT TANAH
			/*beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(logic.getIdHakmilikAgensi(idFail));
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);*/
			
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
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
			}		
			
		//DAFTAR PERMOHONAN BARU
		} else if ("daftarBaru".equals(actionPenyewaan)) {
			
			vm = "app/php2/online/frmPYWDaftarOnline.jsp";
			
			mode = "new";
			this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	// JENIS PERMOHONAN
        	if ("1".equals(idJenisPermohonan)) {
				this.context.put("selected_0", "");
				this.context.put("selected_1", "selected");
				this.context.put("selected_2", "");
				this.context.put("selected_3", "");
				this.context.put("idJenisPermohonan", idJenisPermohonan);
        	} else if ("2".equals(idJenisPermohonan)) {
				this.context.put("selected_0", "");
				this.context.put("selected_1", "");
				this.context.put("selected_2", "selected");
				this.context.put("selected_3", "");
				this.context.put("idJenisPermohonan", idJenisPermohonan);
        	} else if ("3".equals(idJenisPermohonan)) {
				this.context.put("selected_0", "");
				this.context.put("selected_1", "");
				this.context.put("selected_2", "");
				this.context.put("selected_3", "selected");
				this.context.put("idJenisPermohonan", idJenisPermohonan); //declaration baru
        	} else {
        		this.context.put("selected_0", "selected");
				this.context.put("selected_1", "");
				this.context.put("selected_2", "");
				this.context.put("selected_3", "");
				this.context.put("idJenisPermohonan", "0"); 
        	}
        	
        	if("2".equals(idJenisPermohonan)){
    			this.context.put("selectNoFailLama", PHPUtilHTML.SelectNoFailByIdPemohon(id_user, "socNoFailLama", Long.parseLong(idFailLama), "", " onChange=\"doChangeNoFailLama();\""));  
        	}
        	
        	//MAKLUMAT PEMOHON
			Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
			this.context.put("pemohon", vec.get(0));
        	
			//MAKLUMAT PERMOHONAN
			if ("1".equals(idJenisPermohonan)){
        		
            	beanMaklumatPermohonan = new Vector();
    			Hashtable hashPermohonan = new Hashtable();
    			//hashPermohonan.put("noFail", "");
    			hashPermohonan.put("noPermohonan", "");
    			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
    			hashPermohonan.put("perkara", getParam("txtperkara") == null ? "": getParam("txtperkara"));
    			hashPermohonan.put("tarikhSurat", getParam("txttarikhSurat") == null ? "" : getParam("txttarikhSurat"));
    			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
    			//hashPermohonan.put("idSubsuburusan",getParam("socSubsuburusan") == null ? "": getParam("socSubsuburusan"));
    			beanMaklumatPermohonan.addElement(hashPermohonan);
    			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);		
    			
    			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), ""," onChange=\"doChangeUrusan();\""));
    			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan,"socSuburusan", Long.parseLong(idSuburusan), ""," onChange=\"doChangeSuburusan();\""));
    			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "", " onChange=\"doChangeSubsuburusan();\""));
    				
        	} else if ("2".equals(idJenisPermohonan)){
        		Vector<Hashtable<String,String>> vec1 = header.setMaklumatPemohon(id_user);
    			this.context.put("pemohon", vec1.get(0));
        	}

			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik").trim());
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			//MAKLUMAT BORANG K
			if ("doChangePeganganHakmilikBorangK".equals(submit)) {
				idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idPHPBorangK.isEmpty()) {
					idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idHakmilikUrusan.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Maklumat Borang K tidak wujud.");
					}					
				}
			}			
			
			beanMaklumatBorangK = new Vector();
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
			if ("doChangeJenisTanah".equals(submit)){
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}
			
			if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("3".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else if ("4".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
        	}else{
    			this.context.put("selected", "selected");
    			this.context.put("selected1", "");
    			this.context.put("selected2", "");
    			this.context.put("selected3", "");
    			this.context.put("selected4", "");
    			this.context.put("idJenisTanah", 0);
            }

		} else if ("daftarLanjut".equals(actionPenyewaan)) {
				
				vm = "app/php2/online/frmPYWDaftarOnline.jsp";
				
				mode = "new";
				this.context.put("mode", "new");
	        	this.context.put("readonly", "");
	        	this.context.put("inputTextClass", "");
	        	
	        	// JENIS PERMOHONAN
	        	if ("1".equals(idJenisPermohonan)) {
					this.context.put("selected_0", "");
					this.context.put("selected_1", "selected");
					this.context.put("selected_2", "");
					this.context.put("selected_3", "");
					this.context.put("idJenisPermohonan", idJenisPermohonan);
	        	} else if ("2".equals(idJenisPermohonan)) {
					this.context.put("selected_0", "");
					this.context.put("selected_1", "");
					this.context.put("selected_2", "selected");
					this.context.put("selected_3", "");
					this.context.put("idJenisPermohonan", idJenisPermohonan);
	        	} else if ("3".equals(idJenisPermohonan)) {
					this.context.put("selected_0", "");
					this.context.put("selected_1", "");
					this.context.put("selected_2", "");
					this.context.put("selected_3", "selected");
					this.context.put("idJenisPermohonan", idJenisPermohonan); //declaration baru
	        	} else {
	        		this.context.put("selected_0", "selected");
					this.context.put("selected_1", "");
					this.context.put("selected_2", "");
					this.context.put("selected_3", "");
					this.context.put("idJenisPermohonan","0"); 
	        	}
	    			//MAKLUMAT PEMOHON
	            	header = new FrmPYWHeaderData();
	    			Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
	    			this.context.put("pemohon", vec.get(0));
	    						
	    			//MAKLUMAT SEWA
	    			beanMaklumatSewa = new Vector();
	    			logic.setMaklumatSewa(idPermohonan);
	    			beanMaklumatSewa = logic.getBeanMaklumatSewa();
	    			this.context.put("BeanMaklumatSewa", beanMaklumatSewa);
	    			if (beanMaklumatSewa.size() != 0){
	        			Hashtable hashMaklumatSewa = (Hashtable) logic.getBeanMaklumatSewa().get(0);
	        			idPermohonanSewa = (String)(hashMaklumatSewa.get("idPermohonanSewa"));
	        			idTujuanPermohonan = (String)(hashMaklumatSewa.get("idTujuanPermohonan"));
	        			idUrusan = (String)(hashMaklumatSewa.get("idUrusan"));
	        			idSuburusan = (String)(hashMaklumatSewa.get("idSuburusan"));
	        			idSubsuburusan = (String)(hashMaklumatSewa.get("idTujuan"));
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
	        		}
	    			
	    			this.context.put(idUrusan, idUrusan);
	    			this.context.put(idSubsuburusan, idSubsuburusan);
	    			myLogger.info("idSubsuburusan : "+idSubsuburusan);
	    			
	    			this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
	    			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
	    			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "disabled", " class=\"disabled\""));    			
	    			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
	    			
	    			senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			
	        	beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	        	
	        	
				//MAKLUMAT PERMOHONAN
				if ("2".equals(idJenisPermohonan)){

					this.context.put("selectNoFailLama", PHPUtilHTML.SelectNoFailByIdPemohon(id_user, "socNoFailLama", Long.parseLong(idFailLama), "", " onChange=\"doChangeNoFailLama();\""));  
			        
					//MAKLUMAT PERMOHONAN
		        	beanMaklumatPermohonan = new Vector();
					logic.setMaklumatPermohonan(idFail);
					beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
					this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			

					if (logic.getBeanMaklumatPermohonan().size() != 0){
						Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
						idUrusan = (String) hashPermohonan.get("idUrusan");
						idSuburusan = (String) hashPermohonan.get("idSuburusan");
						idSubsuburusan = (String) hashPermohonan.get("idTujuan");
						idPermohonan= (String) hashPermohonan.get("idPermohonan");
						String tarikhSurat = (String) hashPermohonan.get("tarikhSurat");
						String perkara= (String) hashPermohonan.get("perkara");
						String noRujukanSurat = (String) hashPermohonan.get("noRujukanSurat");
						String tarikhTerima= (String) hashPermohonan.get("tarikhTerima");
						
						this.context.put("tarikhSurat", tarikhSurat);
						this.context.put("perkara", perkara);
						this.context.put("noRujukanSurat", noRujukanSurat);
						this.context.put("tarikhTerima", tarikhTerima);
						this.context.put("idSubsuburusan", idSubsuburusan);
						this.context.put("idPermohonan", idPermohonan);
					}

				
	    			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), ""," onChange=\"doChangeUrusan();\""));
	    			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan,"socSuburusan", Long.parseLong(idSuburusan), ""," onChange=\"doChangeSuburusan();\""));
	    			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "", " onChange=\"doChangeSubsuburusan();\""));
	    				
	    			Vector<Hashtable<String,String>> vec1 = header.setMaklumatPemohon(id_user);
	    			this.context.put("pemohon", vec1.get(0));
	        	} 

				//MAKLUMAT HAKMILIK
				if ("doChangePeganganHakmilik".equals(submit)) {
					idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik").trim());
					if (idHakmilikAgensi.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
				
				beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				
				//MAKLUMAT BORANG K
				if ("doChangePeganganHakmilikBorangK".equals(submit)) {
					idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idPHPBorangK.isEmpty()) {
						idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"));
						if (idHakmilikUrusan.isEmpty()) {
							this.context.put("errorPeganganHakmilik","Maklumat Borang K tidak wujud.");
						}					
					}
				}			
				
				beanMaklumatBorangK = new Vector();
				logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
				beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
				this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
				if ("doChangeJenisTanah".equals(submit)){
					idHakmilikAgensi = "";
					idPPTBorangK = "";
					idHakmilikUrusan = "";
					idPHPBorangK = "";
				}
				
				if ("1".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "selected");
					this.context.put("selected2", "");
					this.context.put("selected3", "");
					this.context.put("selected4", "");
					this.context.put("idJenisTanah", idJenisTanah);
	        	}else if ("2".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "selected");
					this.context.put("selected3", "");
					this.context.put("selected4", "");
					this.context.put("idJenisTanah", idJenisTanah);
	        	}else if ("3".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
					this.context.put("selected3", "selected");
					this.context.put("selected4", "");
					this.context.put("idJenisTanah", idJenisTanah);
	        	}else if ("4".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
					this.context.put("selected3", "");
					this.context.put("selected4", "selected");
					this.context.put("idJenisTanah", idJenisTanah);
	        	}else{
	    			this.context.put("selected", "selected");
	    			this.context.put("selected1", "");
	    			this.context.put("selected2", "");
	    			this.context.put("selected3", "");
	    			this.context.put("selected4", "");
	    			this.context.put("idJenisTanah", 0);
	            }

			} else{
			
			Vector status_PermohonanSewa = logic.statusPermohonanSewa(getParam("findNoPermohonan"), 
					getParam("findNoHakmilik"), getParam("findNoWarta"), getParam("findNoLot"), id_user);
			this.context.put("status_PermohonanSewa", status_PermohonanSewa);
			setupPage(session, action, status_PermohonanSewa);
			
			context.put("findNoPermohonan", getParam("findNoPermohonan"));	
			context.put("findNoHakmilik", getParam("findNoHakmilik"));
			context.put("findNoWarta", getParam("findNoWarta"));
			context.put("findNoLot", getParam("findNoLot"));

			// screen
			vm = "app/php2/online/frmPYWSenaraiFailOnline.jsp";

		}
		
		// SET DEFAULT PARAM
		this.context.put("actionPenyewaan", actionPenyewaan);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
	    this.context.put("idLuas", idLuas);
	    this.context.put("idPermohonanSewa", idPermohonanSewa);
	    this.context.put("idTujuanPermohonan", idTujuanPermohonan);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);
		this.context.put("idDokumen", idDokumen);
		this.context.put("idFailLama", idFailLama); 
		this.context.put("idSubsuburusan", idSubsuburusan); 
		this.context.put("namatujuan", logic.getNamaTujuan(idSubsuburusan));

		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("status_PermohonanSewa", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	//UPLOAD LAMPIRAN
	private void uploadLampiran(String idPermohonan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
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
			PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
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
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS", "FAIL [" + idPermohonan + "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}

}
