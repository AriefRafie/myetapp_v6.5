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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPYWMaklumatPermohonanData;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRMaklumatPermohonanData;
import ekptg.model.php2.FrmTKRPopupSenaraiTanahData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmTKRMaklumatPermohonanView extends AjaxBasedModule{

private static final long serialVersionUID = 1L;

	private static final Log myLog = LogFactory.getLog(FrmTKRMaklumatPermohonanView.class);
	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRMaklumatPermohonanData logic = new FrmTKRMaklumatPermohonanData();
	FrmTKRPopupSenaraiTanahData logicTanah = new FrmTKRPopupSenaraiTanahData();
	FrmPYWMaklumatPermohonanData logicPYWData = new FrmPYWMaklumatPermohonanData();
	FrmSemakan semak = null;
	private ILampiran iLampiran = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		String dopost = getParam("dopost");
	    if (doPost.equals("true") || dopost.equals("tru")) {
	        postDB = true;
	    }

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
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
        String idHakmilik = getParam("idHakmilik");
        String idDokumen = getParam("idDokumen");
        String flagBorangK = getParam("flagBorangK");
		String flagFrom = getParam("flagFrom");
		String step = getParam("step");
        String idTanahGanti = getParam("idTanahGanti");
        String idHakmilikAgensiTG = getParam("idHakmilikAgensiTG");
        String idPemohonList = getParam("idPemohon");
        String idNegeriPemohon = getParam("idNegeriPemohon");
	    String idKementerianPemohon = getParam("idKementerianPemohon");

        //GET DROPDOWN PARAM
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}

		String idluasAsal = getParam("socLuasAsal");
		if (idluasAsal == null || idluasAsal.trim().length() == 0){
			idluasAsal = "99999";
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

		String idLuasKegunaanTG = getParam("socLuasKegunaanTG");
		if (idLuasKegunaanTG == null || idLuasKegunaanTG.trim().length() == 0){
			idLuasKegunaanTG = "99999";
		}
		String idLuasTG = getParam("socLuasTG");
		if (idLuasTG == null || idLuasTG.trim().length() == 0){
			idLuasTG = "99999";
		}
		String idNegeriJKPTG = getParam("socNegeriJKPTG");
		if (idNegeriJKPTG == null || idNegeriJKPTG.trim().length() == 0){
			idNegeriJKPTG = "99999";
		}
		String idJenisHakmilikTG = getParam("socJenisHakmilikTG");
		if (idJenisHakmilikTG == null || idJenisHakmilikTG.trim().length() == 0){
			idJenisHakmilikTG = "99999";
		}
		String idLotTG = getParam("socLotTG");
		if (idLotTG == null || idLotTG.trim().length() == 0){
			idLotTG = "99999";
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

        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idHakmilikSementara = getParam("idHakmilikSementara");

        //VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBorangK = null;
        Vector beanMaklumatTukarguna = null;
        Vector beanMaklumatPejabat = null;
        Vector beanMaklumatAgensi = null;
        Vector senaraiTanahGanti = null;
        Vector beanMaklumatTanahGanti = null;
        Vector beanMaklumatLuasTG = null;
    	Vector beanListMaklumatPemohon = null;
        Vector senaraiPelan = null;
        Vector beanMaklumatPelan = null;
        Vector senaraiSemak = null;

        Vector senaraiTanahBerkaitan = new Vector();

        vm = "app/php2/frmTKRMaklumatPermohonan.jsp";

        //SEND TO MODEL
    	if (postDB) {
    		if ("doSimpanKemaskiniMaklumatTukarguna".equals(hitButton)) {
				logic.updatePermohonanTukarguna(idFail, idPermohonan,
						getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtPerkara"), idPermohonanPelepasan,
						getParam("txtCadanganKegunaan"), idLuasKegunaan,
						idLuas, getParam("txtLuasMohon1"),
						getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"),
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
			}
			if ("doHapusTanahGanti".equals(hitButton)){
				logic.hapusTanahGanti(idTanahGanti, session);
			}
        	if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, session);
    		}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        	if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
        		String semaks [] = this.request.getParameterValues("idsSenaraiSemak");
    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
        	}
        	//NEW METHOD FOR HAPUS
        	if ("doHapusFail".equals(hitButton)){
    			logic.doHapusFail(idFail, idPermohonan, getParam("tarikhHapus"), getParam("txtSebab"), session);
    			step = "";
    			vm = "app/php2/frmPLPSenaraiFail.jsp";
    		}

        	if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idFail,idPermohonan, session);
			}
        	if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaPelan"), getParam("txtCatatanPelan"), session);
			}
        	if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
        	//maklumat pemohon
        	if("doSimpanMaklumatPemohon".equals(hitButton)){
				logic.savePemohon(idKategoriPemohon, idFail, idPermohonan,
						idKementerian, idAgensi,idPejabat, session);
        	}
        	if("doSimpanKemaskiniMaklumatPemohon".equals(hitButton)){
				logic.updatePemohon(idPemohonList, idKategoriPemohon,
						idKementerian, idAgensi,idPejabat, session);
        	}
			if ("doHapusMaklumatPemohon".equals(hitButton)){
				logic.hapusPemohon(idPemohonList, session);
			}
			//maklumat tanah
        	if("doSimpanMaklumatTanah".equals(hitButton)){
				beanMaklumatTanah = new Vector();
				logicTanah.setMaklumatTanah(idHakmilikAgensiTG, idHakmilikSementara);
				beanMaklumatTanah = logicTanah.getBeanMaklumatTanah();

				idHakmilik = logicHeader.getIdHakmilikByIdFail(idFail);

				logic.saveMaklumatTanah(idHakmilikAgensiTG, idPermohonan, idHakmilik, beanMaklumatTanah, session);
        	}

			//maklumat tanah berkaitan
        	if("doSimpanMaklumatTanahBerkaitan".equals(hitButton)){
				beanMaklumatTanah = new Vector();
				logicTanah.setMaklumatTanah(idHakmilikAgensiTG, idHakmilikSementara);
				beanMaklumatTanah = logicTanah.getBeanMaklumatTanah();

				idHakmilik = logicHeader.getIdHakmilikByIdFail(idFail);

				logic.saveMaklumatTanahBerkaitan(idHakmilikAgensiTG, idPermohonan, idHakmilik, beanMaklumatTanah, session);
        	}

        	if ("doHapus".equals(hitButton)){
        		logicPYWData.doHapus(idPermohonan, getParam("idHakmilikPermohonan"), session);
    		}
    	}

		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phptkr"));

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
			if ("".equals((String) hashHakmilik.get("noHakmilik"))){
				statusRizab = "RIZAB";
			} else {
				statusRizab = "MILIK";
			}
			flagBorangK = (String) hashHakmilik.get("flagBorangK");
			/*idAgensi = (String) hashHakmilik.get("idAgensi");
			idKementerian = (String) hashHakmilik.get("idKementerian");*/
		}
		this.context.put("flagBorangK", flagBorangK);

		if ("Y".equals(flagBorangK)){
			beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {

			if ("doChangeMaklumatTanah".equals(submit)){
				beanMaklumatTanah = new Vector();
				logicTanah.setMaklumatTanah(idHakmilikAgensiTG, idHakmilikSementara);
				beanMaklumatTanah = logicTanah.getBeanMaklumatTanah();
			} else {
				idHakmilikAgensiTG = logicHeader.getIdHakmilikByIdFail(idFail);
				myLog.info("idHakmilikAgensiTG="+idHakmilikAgensiTG);
				beanMaklumatTanah = new Vector();
				logicTanah.setMaklumatTanah(idHakmilikAgensiTG, idHakmilikSementara);
				beanMaklumatTanah = logicTanah.getBeanMaklumatTanah();
			}

			if(beanMaklumatTanah.size()==0){
				beanMaklumatTanah = new Vector();
				beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
			}

			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		}

		this.context.put("flagPopup", "");
		this.context.put("modePopup", "");

		//SENARAI TANAH BERKAITAN
		if(selectedTabUpper.equals("1")){
			senaraiTanahBerkaitan = new Vector();
			logicPYWData.setSenaraiTanahBerkaitan(idPermohonan);
			senaraiTanahBerkaitan = logicPYWData.getListTanahBerkaitan();
			this.context.put("SenaraiTanahBerkaitan", senaraiTanahBerkaitan);
		}

		// MAKLUMAT PEMOHON
		if(selectedTabUpper.equals("3")){

			if("view".equals(mode)){
//				tabMaklumatPemohon("list", idPemohon, idKategoriPemohon, idPejabat, idKementerian, idAgensi, idNegeri, idFail,
//						 beanMaklumatAgensi, beanMaklumatPejabat, beanListMaklumatPemohon);
				tabMaklumatPemohon("viewPemohon", idPemohonList, idKategoriPemohon, idPejabat, idKementerian, idAgensi, idNegeri, idFail,
						 beanMaklumatAgensi, beanMaklumatPejabat, beanListMaklumatPemohon);
				idPemohon = idPemohonList;
			}

			if("addNewPemohon".equals(hitButton) || "addNewPemohon".equals(mode)){
				tabMaklumatPemohon("addNewPemohon", idPemohon, idKategoriPemohon, idPejabat, idKementerian, idAgensi, idNegeri, idFail,
						 beanMaklumatAgensi, beanMaklumatPejabat, beanListMaklumatPemohon);
			}

			if("viewPemohon".equals(hitButton) || "viewPemohon".equals(mode)){
				tabMaklumatPemohon("viewPemohon", idPemohonList, idKategoriPemohon, idPejabat, idKementerian, idAgensi, idNegeri, idFail,
						 beanMaklumatAgensi, beanMaklumatPejabat, beanListMaklumatPemohon);
				idPemohon = idPemohonList;
			}

			if("updatePemohon".equals(hitButton) || "updatePemohon".equals(mode)){
				tabMaklumatPemohon("updatePemohon", idPemohonList, idKategoriPemohon, idPejabat, idKementerian, idAgensi, idNegeri, idFail,
						 beanMaklumatAgensi, beanMaklumatPejabat, beanListMaklumatPemohon);
				idPemohon = idPemohonList;
			}

		}

		//SENARAI SEMAK
		if("5".equals(selectedTabUpper)){
			/*senaraiSemak = logic.getSenaraiSemak(idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);*/

			semak = new FrmSemakan();
			senaraiSemak = semak.getSenaraiSemakanAttach2("phptukar",idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);


			myLog.info("senaraiSemak ="+senaraiSemak);
		}

		if("6".equals(selectedTabUpper)){

			//OPEN POPUP DOKUMEN
        	if ("openPopupDokumen".equals(flagPopup)){

        		if ("new".equals(modePopup)){

        			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");

	    			beanMaklumatPelan = new Vector();
	    			Hashtable hashMaklumatPelan = new Hashtable();
	    			hashMaklumatPelan.put("namaPelan", "");
	    			hashMaklumatPelan.put("catatanPelan", "");
	    			beanMaklumatPelan.addElement(hashMaklumatPelan);
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);

        		} else if ("update".equals(modePopup)){

        			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");

	    			//MAKLUMAT DOKUMEN
					beanMaklumatPelan = new Vector();
					logic.setMaklumatPelan(idDokumen);
					beanMaklumatPelan = logic.getbeanMaklumatPelan();
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);

        		} else if ("view".equals(modePopup)){

        			this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");

	    			//MAKLUMAT DOKUMEN
					beanMaklumatPelan = new Vector();
					logic.setMaklumatPelan(idDokumen);
					beanMaklumatPelan = logic.getbeanMaklumatPelan();
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
        		}
        	}

        	//SENARAI PELAN
			senaraiPelan = new Vector();
			logic.setSenaraiPelan(idPermohonan);
			senaraiPelan = logic.getlistPelan();
			this.context.put("SenaraiPelan", senaraiPelan);
		}

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
        			System.out.println("idtanah="+idTanahGanti);
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
			
        	//MAKLUMAT TUKAGUNA
			beanMaklumatTukarguna = new Vector();
			logic.setMaklumatTukarguna(idPermohonan);
			beanMaklumatTukarguna = logic.getBeanMaklumatTukarguna();
			this.context.put("BeanMaklumatTukarguna", beanMaklumatTukarguna);
			if (beanMaklumatTukarguna.size() != 0){
    			Hashtable hashMaklumatTukarguna = (Hashtable) logic.getBeanMaklumatTukarguna().get(0);
    			idPermohonanPelepasan = (String)(hashMaklumatTukarguna.get("idPermohonanPelepasan"));
        		if (hashMaklumatTukarguna.get("flagGuna") != null && hashMaklumatTukarguna.get("flagGuna").toString().trim().length() != 0){
        			idLuasKegunaan = (String) hashMaklumatTukarguna.get("flagGuna");
        		} else {
        			idLuasKegunaan = "99999";
        		}
        		if (hashMaklumatTukarguna.get("idLuasMohon") != null && hashMaklumatTukarguna.get("idLuasMohon").toString().trim().length() != 0){
        			idLuas = (String) hashMaklumatTukarguna.get("idLuasMohon");
        		} else {
        			idLuas = "99999";

        		}
    		}

			if ("1".equals(idLuas)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "selected");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "");
				this.context.put("idLuas", idLuas);
	    	} else if ("2".equals(idLuas)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "selected");
				this.context.put("selectedL3", "");
				this.context.put("idLuas", idLuas);
	    	} else if ("3".equals(idLuas)) {
				this.context.put("selected", "");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "selected");
				this.context.put("idLuas", idLuas);
	    	} else {
	    		this.context.put("selected", "selected");
				this.context.put("selectedL1", "");
				this.context.put("selectedL2", "");
				this.context.put("selectedL3", "");
				this.context.put("idLuas", "0");
	    	}

			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));

			//SENARAI TANAH GANTI
			senaraiTanahGanti = new Vector();
			logic.setSenaraiTanahGanti(idPermohonan);
			senaraiTanahGanti = logic.getListTanahGanti();
			this.context.put("SenaraiTanahGanti", senaraiTanahGanti);

        } else if ("update".equals(mode)){  //UPDATE MODE

        	this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");

        	//MAKLUMAT TUKARGUNA
        	beanMaklumatTukarguna = new Vector();
    		logic.setMaklumatTukarguna(idPermohonan);
    		Hashtable hashMaklumatTukargunaDB = (Hashtable) logic.getBeanMaklumatTukarguna().get(0);
			Hashtable hashMaklumatTukarguna = new Hashtable();
			hashMaklumatTukarguna.put("tarikhTerima", getParam("tarikhTerima"));
			hashMaklumatTukarguna.put("tarikhSurat", getParam("tarikhSurat"));
			hashMaklumatTukarguna.put("perkara", getParam("txtPerkara"));
			hashMaklumatTukarguna.put("namaProjek", getParam("txtNamaProjek"));
			hashMaklumatTukarguna.put("cadanganKegunaan", getParam("txtCadanganKegunaan"));
			hashMaklumatTukarguna.put("luasAsal", hashMaklumatTukargunaDB.get("luasAsal"));
			hashMaklumatTukarguna.put("idLuas", hashMaklumatTukargunaDB.get("idLuas"));
			hashMaklumatTukarguna.put("keteranganLuasAsal", hashMaklumatTukargunaDB.get("keteranganLuasAsal"));
			if ("doChangeLuas".equals(submit)){
				hashMaklumatTukarguna.put("luas1", "");
				hashMaklumatTukarguna.put("luas2", "");
				hashMaklumatTukarguna.put("luas3", "");
				hashMaklumatTukarguna.put("luasBersamaan", "");
				hashMaklumatTukarguna.put("luasBaki", "");
			} else {
				hashMaklumatTukarguna.put("luas1", getParam("txtLuasMohon1"));
				hashMaklumatTukarguna.put("luas2", getParam("txtLuasMohon2"));
				hashMaklumatTukarguna.put("luas3", getParam("txtLuasMohon3"));
				if ("1".equals(idLuasKegunaan)){
					hashMaklumatTukarguna.put("luasBersamaan",  hashMaklumatTukargunaDB.get("luasAsal"));
					hashMaklumatTukarguna.put("luasBaki", Utils.formatLuas(0D));
				} else {
					hashMaklumatTukarguna.put("luasBersamaan", getParam("txtLuasBersamaan"));
					hashMaklumatTukarguna.put("luasBaki", getParam("txtBakiLuas"));
				}
			}
			hashMaklumatTukarguna.put("kemajuanTanah", getParam("txtKemajuanTanah"));
			beanMaklumatTukarguna.addElement(hashMaklumatTukarguna);
           	this.context.put("BeanMaklumatTukarguna", beanMaklumatTukarguna);

    		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));

    		senaraiSemak = logic.getSenaraiSemak(idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);
        }

        if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        if ("hapusFail".equals(step)){
        	vm = "app/php2/frmHapusFail.jsp";
        }

     	if ("1".equals(idLuas)) {
			this.context.put("selected", "");
			this.context.put("selectedL1", "selected");
			this.context.put("selectedL2", "");
			this.context.put("selectedL3", "");
			this.context.put("idLuas", idLuas);
    	} else if ("2".equals(idLuas)) {
			this.context.put("selected", "");
			this.context.put("selectedL1", "");
			this.context.put("selectedL2", "selected");
			this.context.put("selectedL3", "");
			this.context.put("idLuas", idLuas);
    	} else if ("3".equals(idLuas)) {
			this.context.put("selected", "");
			this.context.put("selectedL1", "");
			this.context.put("selectedL2", "");
			this.context.put("selectedL3", "selected");
			this.context.put("idLuas", idLuas);
    	} else {
    		this.context.put("selected", "selected");
			this.context.put("selectedL1", "");
			this.context.put("selectedL2", "");
			this.context.put("selectedL3", "");
			this.context.put("idLuas", "0");
    	}

        //SET DEFAULT PARAM
        this.context.put("idDokumen", idDokumen);
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagFrom", flagFrom);

		//SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        //this.context.put("idKategoriPemohon", idKategoriPemohon);
        this.context.put("idAgensi", idAgensi);
        this.context.put("idStatus", idStatus);
        this.context.put("idPermohonanPelepasan", idPermohonanPelepasan);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
		this.context.put("idLuas", idLuas);
		this.context.put("idHakmilik", idHakmilik);

		this.context.put("flagBorangK",flagBorangK);
		this.context.put("statusRizab",statusRizab);

		this.context.put("idTanahGanti",idTanahGanti);
		this.context.put("idHakmilikAgensiTG",idHakmilikAgensiTG);

		this.context.put("idKategoriPemohon",idKategoriPemohon);
		this.context.put("idAgensi",idAgensi);
		this.context.put("idNegeriPemohon",idNegeriPemohon);
	    this.context.put("idKementerianPemohon", idKementerianPemohon);
		this.context.put("idKementerian",idKementerian);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		this.context.put("hitButton", hitButton);

		this.context.put("step",step);

		return vm;
	}

	private void tabMaklumatPemohon(String mode, String idPemohon, String idKategoriPemohon, String idPejabat, String idKementerian, String idAgensi, String idNegeri, String idFail,
			Vector beanMaklumatAgensi, Vector beanMaklumatPejabat, Vector beanListMaklumatPemohon) throws Exception{

		beanListMaklumatPemohon = new Vector();
		logic.setListMaklumatPemohon(idFail) ;
		beanListMaklumatPemohon = logic.getBeanListMaklumatPemohon();
		this.context.put("BeanListMaklumatPemohon",beanListMaklumatPemohon);

		if("list".equals(mode)){
			this.context.put("flagPopup", "closePopupPemohon");
		}

		if("addNewPemohon".equals(mode)){
			beanMaklumatAgensi = new Vector();
			this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

    		if ("doChangeKategori".equals(submit)){
    			idKementerian = "99999";
				idAgensi = "99999";
			}
    		if ("doChangeKementerian".equals(submit)){
				idAgensi = "99999";
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			if ("3".equals(idKategoriPemohon)) {

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));

			} else if (("8".equals(idKategoriPemohon))) {

				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
				this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeri));

				if ("doChangeNegeri".equals(submit)){
					this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeri));
					idPejabat = "99999";
				}

				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);


				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));

			}
		}

		if("viewPemohon".equals(mode)){
			logic.setMaklumatPemohon(idPemohon);
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));

			if ("3".equals(idKategoriPemohon)) {

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

			} else if (("8".equals(idKategoriPemohon))) {

				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

				Hashtable hashNegeri = (Hashtable) logic.getBeanMaklumatPejabat().get(0);
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashNegeri.get("idNegeri")), "disabled", " class=\"disabled\""));
				this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", (String) hashNegeri.get("idNegeri")));

			}
		}

		if("updatePemohon".equals(mode)){
    		if ("doChangeKategori".equals(submit)){
    			idKementerian = "99999";
				idAgensi = "99999";
			}
    		if ("doChangeKementerian".equals(submit)){
				idAgensi = "99999";
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));

			if ("3".equals(idKategoriPemohon)) {

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));

			} else if (("8".equals(idKategoriPemohon))) {

				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
				this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeri));

				if ("doChangeNegeri".equals(submit)){
					this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeri));
					idPejabat = "99999";
				}

				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));

			}
		}

        this.context.put("idKategoriPemohon", idKategoriPemohon);

	}

	// UPLOAD FILE
	private void uploadFiles(String idFail, String idPermohonan , HttpSession session) throws Exception {
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
					if(item.getSize()<500000000L)
						saveData(item ,idFail, idPermohonan , session);
					else
						this.context.put("limitExceed", true);
				}
			}
		}
	}


	private void saveData(FileItem item,String idFail, String idPermohonan, HttpSession session) throws Exception {

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
					"FAIL [" + idDokumen
							+ "] DIDAFTARKAN");

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}

	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
}