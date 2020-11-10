/**
 *
 */
package ekptg.view.php2;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.portal.AjaxBasedModule;
import lebah.util.Util;
import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.engine.GetAttachment;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmREVHeaderData;
import ekptg.model.php2.FrmREVMemantauBayaranSewaData;
import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.model.php2.utiliti.PHPUtilHTML;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.ws.gfmas.GfmasMemantauBayaranSewaManager;

/**
 *
 *
 */
public class FrmREVMemantauBayaranSewaView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmREVMemantauBayaranSewaData logic = new FrmREVMemantauBayaranSewaData();
	FrmREVHeaderData logicHeader = new FrmREVHeaderData();
	GfmasMemantauBayaranSewaManager gfmas = new GfmasMemantauBayaranSewaManager();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmREVMemantauBayaranSewaView.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }

	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
        String actionHasil = getParam("actionHasil");
	    String hitButton = getParam("hitButton");
        String submit = getParam("command");
        String mode = getParam("mode");
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
        String selectedTabLower = (String) getParam("selectedTabLower");
		if (selectedTabLower == null || "".equals(selectedTabLower) ) {
			selectedTabLower = "0";
		}

        //GET ID PARAM
		String idFail = getParam("idFail");
        String idHasil = getParam("idHasil");
        String idPemohon = getParam("idPemohon");
        String idAkaun = getParam("idAkaun");
        String idNotis = getParam("idNotis");
        String noLotTanah = getParam("noLotTanah");

        String idLuasKegunaan = getParam("idLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}

		String idJenisPelarasan = getParam("socJenisPelarasan");
		if (idJenisPelarasan == null || idJenisPelarasan.trim().length() == 0){
			idJenisPelarasan = "99999";
		}

		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}

		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0){
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idNegeriTanah = getParam("socNegeriTanah");
		if (idNegeriTanah == null || idNegeriTanah.trim().length() == 0) {
			idNegeriTanah = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idModBayaran = getParam("socModBayaran");
		if (idModBayaran == null || idModBayaran.trim().length() == 0){
			idModBayaran = "99999";
		}
		String idKategoriBayaran = getParam("socKategoriBayaran");
		if (idKategoriBayaran == null || idKategoriBayaran.trim().length() == 0){
			idKategoriBayaran = "99999";
		}
        String idCaraBayaran = getParam("socCaraBayaran");
		if (idCaraBayaran == null || idCaraBayaran.trim().length() == 0){
			idCaraBayaran = "99999";
		}
		String idBank = getParam("socBank");
		if (idBank == null || idBank.trim().length() == 0){
			idBank = "99999";
		}

        //VECTOR
        Vector list = null;
        Vector beanMaklumatPemohon = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatBayaranD = null;
        Vector beanMaklumatBayaran = null;
        Vector beanMaklumatBayaranLL = null;
        Vector beanMaklumatPelarasanD = null;
        Vector beanMaklumatPelarasan = null;

        String idStatusDeposit = getParam("socStatusDeposit");
		if (idStatusDeposit == null || idStatusDeposit.trim().length() == 0){
			idStatusDeposit = "Y";
		}

        if (postDB){
        	if ("doSimpanKemaskiniPemohon".equals(hitButton)){
        		logic.updatePemohon(idPemohon,
						getParam("txtNama"), getParam("txtNoPendaftaran"),
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idBandar, idNegeri, getParam("txtEmel"),
						getParam("txtNoTel"), getParam("txtNoFaks"),
						getParam("txtNoRujukan"), getParam("txtCatatan"), session);
        	}
        	if("doSimpanKemaskiniTindakanMahkamah".equals(hitButton)){
        		logic.updateTindakanMahkamah(idFail, idHasil,
						getParam("tarikh_penamaan_sst"), getParam("tarikh_notis_rampasan"), getParam("tarikh_notis_tuntutan"), session);
        	}
        	if ("doSimpanKemaskiniPermohonan".equals(hitButton)){
        		logic.updatePermohonan(idFail, idHasil, getParam("noFail"), idUrusan, idSuburusan, getParam("txtPerkara"), getParam("txtTujuan"), getParam("txtCatatanPermohonan"), session);
        	}

        	if ("doSimpanKemaskiniTanah".equals(hitButton)){
        		logic.updateTanah(idFail, idHasil, idNegeriTanah, idKementerian, idAgensi, getParam("txtMaklumatLot"), idLuas, getParam("txtLuas"), getParam("txtCatatanTanah"), session);
        	}
    		if ("simpanBayaranD".equals(hitButton)){
         		idAkaun = logic.simpanBayaranD(idHasil, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"),idStatusDeposit, getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranD".equals(hitButton)){
    			logic.simpanKemaskiniBayaranD(idHasil, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), idStatusDeposit, getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("hapusBayaranD".equals(hitButton)){
         		logic.hapusBayaranD(idHasil, idAkaun, session);
        	}
    		if ("simpanBayaran".equals(hitButton)){
         		idAkaun = logic.simpanBayaran(idHasil, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaran".equals(hitButton)){
    			logic.simpanKemaskiniBayaran(idHasil, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("hapusBayaran".equals(hitButton)){
         		logic.hapusBayaran(idHasil, idAkaun, session);
        	}
    		if ("simpanBayaranLL".equals(hitButton)){
         		idAkaun = logic.simpanBayaranLL(idHasil, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, idKategoriBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranLL".equals(hitButton)){
    			logic.simpanKemaskiniBayaranLL(idHasil, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"),
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, idKategoriBayaran, session);
    		}
    		if ("hapusBayaranLL".equals(hitButton)){
         		logic.hapusBayaranLL(idHasil, idAkaun, session);
        	}
    		if ("simpanPelarasanD".equals(hitButton)){
         		idAkaun = logic.simpanPelarasanD(idHasil, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"), getParam("txtTarikhCek"), idBank, getParam("txtNoResit"), getParam("txtTarikhResit"), session);
        	}
    		if ("simpanKemaskiniPelarasanD".equals(hitButton)){
         		logic.simpanKemaskiniPelarasanD(idHasil, idAkaun, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"),getParam("txtTarikhCek"), idBank, getParam("txtNoResit"), getParam("txtTarikhResit"), session);
        	}
    		if ("hapusPelarasanD".equals(hitButton)){
         		logic.hapusPelarasanD(idHasil, idAkaun, session);
        	}
    		if ("simpanPelarasan".equals(hitButton)){
         		idAkaun = logic.simpanPelarasan(idHasil, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"), getParam("txtTarikhCek"), idBank, getParam("txtNoResit"), getParam("txtTarikhResit"), session);
        	}
    		if ("simpanKemaskiniPelarasan".equals(hitButton)){
         		logic.simpanKemaskiniPelarasan(idHasil, idAkaun, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"), getParam("txtTarikhCek"), idBank, getParam("txtNoResit"), getParam("txtTarikhResit"), session);
        	}
    		if ("hapusPelarasan".equals(hitButton)){
         		logic.hapusPelarasan(idHasil, idAkaun, session);
        	}
    		if ("hapusFail".equals(hitButton)){
         		logic.hapusFail(idFail, session);
        	}
    		if ("doSimpanKemaskiniABT".equals(hitButton)){
        		logic.kemaskiniABT(idHasil, getParam("txtCatatanABT"), session);
        	}
    		if ("hapusNotis".equals(hitButton)){
         		logic.hapusNotis(idNotis, session);
        	}
    		if ("sendNotisByEmail".equals(hitButton)){
    			//ada attachment dlm emel
    			hantarInvoisByEmel(session, idAkaun, idFail, servletContext, request, response);
    		}
    	}

        if ("papar".equals(actionHasil)){

        	vm = "app/php2/frmREVMaklumatBayaranSewa.jsp";

        	//HEADER
        	header(idHasil);

        	this.context.put("txtNamaPemohon", getParam("txtNamaPemohon"));
        	this.context.put("txtNoFail", getParam("txtNoFail"));
        	this.context.put("selectedTabLower", selectedTabLower);

        	//MAKLUMAT DEPOSIT
        	if ("0".equals(selectedTabUpper)){

        		if ("newBayaranD".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranD = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranD.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranD", beanMaklumatBayaranD);

                	this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", " onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectStatusDeposit",HTML.SelectStatusAktifTidak("socStatusDeposit", idStatusDeposit, "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", " onChange=\"doChangeModBayaran();\""));

        		} else if ("capaianGfmas".equals(mode)) {

        			this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

        			String noResit = getParam("txtNoResit");

    				Hashtable<String, String> data = gfmas.getMaklumat(noResit);

        			String tarikhTransaksi = data.get("tarikhTransaksi");
    				this.context.put("tarikhTransaksi", data.get("tarikhTransaksi"));

    				String caraBayar = data.get("caraBayar");
    				this.context.put("caraBayar", data.get("caraBayar"));

    				String tarikhCek = data.get("tarikhCek");
    				this.context.put("tarikhCek", data.get("tarikhCek"));

    				String noCekRujukan = data.get("noCekRujukan");
    				this.context.put("noCekRujukan", data.get("noCekRujukan"));

    				String amaunRM = data.get("amaunRM");
    				this.context.put("amaunRM", data.get("amaunRM"));

    				String tarikhResit = data.get("tarikhResit");
    				this.context.put("tarikhResit", data.get("tarikhResit"));

    				beanMaklumatBayaranD = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", data.get("tarikhTransaksi"));
//        			hashBayaran.put("tarikh", data.get("tarikhTransaksi") == null || data.get("tarikhTransaksi") == "" ? sdf.format(new Date()) : data.get("tarikhTransaksi"));
        			this.context.put("selectCaraBayaran", HTML.selectCaraBayar("socCaraBayaran", Long.parseLong(data.get("caraBayar")), ""));
        			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
        			hashBayaran.put("tarikhCek", data.get("tarikhCek"));
        			hashBayaran.put("noRujukan", data.get("noCekRujukan"));
        			hashBayaran.put("amaun", data.get("amaunRM"));
        			hashBayaran.put("noResit", noResit);
        			hashBayaran.put("tarikhResit", data.get("tarikhResit"));
        			beanMaklumatBayaranD.addElement(hashBayaran);

        			this.context.put("BeanMaklumatBayaranD", beanMaklumatBayaranD);

        			mode = "newBayaranD";

    			}  else if ("viewBayaranD".equals(mode)){

                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranD = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaranD = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaranD", beanMaklumatBayaranD);

            		if (beanMaklumatBayaranD.size() != 0){
            			Hashtable hashBayaranDB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranDB.get("idCaraBayar");
            			idBank = (String) hashBayaranDB.get("idBank");
            			idStatusDeposit = (String) hashBayaranDB.get("flagDeposit");
            			idModBayaran = (String) hashBayaranDB.get("modBayaran");
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectStatusDeposit",HTML.SelectStatusAktifTidak("socStatusDeposit", idStatusDeposit, "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
            		}

        		} else if ("updateBayaranD".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranD = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranD.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranD", beanMaklumatBayaranD);

            		this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectStatusDeposit",HTML.SelectStatusAktifTidak("socStatusDeposit", idStatusDeposit, "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "onChange=\"doChangeModBayaran();\""));
        		}

        		if ("newPelarasanD".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasanD = new Vector();
        			Hashtable hashPelarasan = new Hashtable();
        			hashPelarasan.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashPelarasan.put("noRujukan", getParam("txtNoRujukan"));
        			hashPelarasan.put("amaun", getParam("txtAmaun"));
        			hashPelarasan.put("noResit", getParam("txtNoResit"));
        			hashPelarasan.put("tarikhResit", getParam("txtTarikhResit"));
        			hashPelarasan.put("butiran", getParam("txtButiran"));
        			hashPelarasan.put("tarikhCek", getParam("txtTarikhCek"));
        			beanMaklumatPelarasanD.addElement(hashPelarasan);

        			this.context.put("BeanMaklumatPelarasanD", beanMaklumatPelarasanD);
        			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));

        			if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}

        		} else if ("viewPelarasanD".equals(mode)){

                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");

                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasanD = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasanD = logic.getBeanMaklumatPelarasan();

            		this.context.put("BeanMaklumatPelarasanD", beanMaklumatPelarasanD);

            		if (beanMaklumatPelarasanD.size() != 0){
            			Hashtable hashPelarasanDB = (Hashtable) logic.getBeanMaklumatPelarasan().get(0);
            			idJenisPelarasan = (String) hashPelarasanDB.get("idJenisPelarasan");

            			if("C".equals(idJenisPelarasan)){
            				idBank = (String) hashPelarasanDB.get("idBank");
            				this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			}
            		}

            		if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}

        		} else if ("updatePelarasanD".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	beanMaklumatPelarasanD = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasanD = logic.getBeanMaklumatPelarasan();

            		this.context.put("BeanMaklumatPelarasanD", beanMaklumatPelarasanD);
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));

            		if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}
        		}

        		senaraiDeposit(idHasil, action, session);
        	}

        	//MAKLUMAT AKAUN
        	if ("1".equals(selectedTabUpper)){

        		if ("newBayaran".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaran.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);

                	this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "onChange=\"doChangeModBayaran();\""));

        		} else if ("capaianGfmasSewa".equals(mode)) {

        			this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

        			String noResit = getParam("txtNoResit");

    				Hashtable<String, String> data = gfmas.getMaklumat(noResit);

    				beanMaklumatBayaran = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", data.get("tarikhTransaksi"));
        			this.context.put("selectCaraBayaran", HTML.selectCaraBayar("socCaraBayaran", Long.parseLong(data.get("caraBayar")), ""));
        			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
        			hashBayaran.put("tarikhCek", data.get("tarikhCek"));
        			hashBayaran.put("noRujukan", data.get("noCekRujukan"));
        			hashBayaran.put("amaun", data.get("amaunRM"));
        			hashBayaran.put("noResit", noResit);
        			hashBayaran.put("tarikhResit", data.get("tarikhResit"));
        			beanMaklumatBayaran.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);

        			mode = "newBayaran";

    			} else if ("viewBayaran".equals(mode)){

                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaran = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);

            		if (beanMaklumatBayaran.size() != 0){
            			Hashtable hashBayaranDB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranDB.get("idCaraBayar");
            			idBank = (String) hashBayaranDB.get("idBank");
            			idModBayaran = (String) hashBayaranDB.get("modBayaran");
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
            		}

        		} else if ("updateBayaran".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaran.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);

            		this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "onChange=\"doChangeModBayaran();\""));

        		}

        		if ("newPelarasan".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasan = new Vector();
        			Hashtable hashPelarasan = new Hashtable();
        			hashPelarasan.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashPelarasan.put("tarikhCek", getParam("txtTarikhCek"));
        			hashPelarasan.put("noRujukan", getParam("txtNoRujukan"));
        			hashPelarasan.put("amaun", getParam("txtAmaun"));
        			hashPelarasan.put("noResit", getParam("txtNoResit"));
        			hashPelarasan.put("tarikhResit", getParam("txtTarikhResit"));
        			hashPelarasan.put("butiran", getParam("txtButiran"));
        			beanMaklumatPelarasan.addElement(hashPelarasan);
        			this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);
        			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
        			if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}

        		} else if ("viewPelarasan".equals(mode)){

                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");

                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasan = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasan = logic.getBeanMaklumatPelarasan();
            		this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);

            		if (beanMaklumatPelarasan.size() != 0){
            			Hashtable hashPelarasanB = (Hashtable) logic.getBeanMaklumatPelarasan().get(0);
            			idJenisPelarasan = (String) hashPelarasanB.get("idJenisPelarasan");

            			if("C".equals(idJenisPelarasan)){
                			idBank = (String) hashPelarasanB.get("idBank");
                			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
                			}
            		}

            		if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}

        		} else if ("updatePelarasan".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	beanMaklumatPelarasan = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasan = logic.getBeanMaklumatPelarasan();
            		this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));

            		if ("D".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("K".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			} else if ("C".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "selected");
        				this.context.put("selectedR", "");
        				this.context.put("cek", idJenisPelarasan);

        			} else if ("R".equals(idJenisPelarasan)){

        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "selected");
        				this.context.put("cek", idJenisPelarasan);

        			} else {

        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				this.context.put("selectedC", "");
        				this.context.put("selectedR", "");
        				this.context.put("cek", "");

        			}
        		}

        		senaraiAkaun(idHasil, action, session);
        	}

        	//MAKLUMAT AKAUN
        	if ("2".equals(selectedTabUpper)){

        		if ("newBayaranLL".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranLL = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranLL.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranLL", beanMaklumatBayaranLL);

                	this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "onChange=\"doChangeModBayaran();\""));
                	this.context.put("selectKategoriBayaran",HTML.SelectSuburusanByIdUrusan("12", "socKategoriBayaran", Long.parseLong(idKategoriBayaran), "", "onChange=\"doChangeModBayaran();\""));

        		} else if ("capaianGfmasBayaran".equals(mode)) {

        			this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

        			String noResit = getParam("txtNoResit");

    				Hashtable<String, String> data = gfmas.getMaklumat(noResit);

    				beanMaklumatBayaranLL = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", data.get("tarikhTransaksi"));
        			this.context.put("selectCaraBayaran", HTML.selectCaraBayar("socCaraBayaran", Long.parseLong(data.get("caraBayar")), ""));
        			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
        			hashBayaran.put("tarikhCek", data.get("tarikhCek"));
        			hashBayaran.put("noRujukan", data.get("noCekRujukan"));
        			hashBayaran.put("amaun", data.get("amaunRM"));
        			hashBayaran.put("noResit", noResit);
        			hashBayaran.put("tarikhResit", data.get("tarikhResit"));
        			beanMaklumatBayaranLL.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranLL", beanMaklumatBayaranLL);

        			mode = "newBayaranLL";

    			} else if ("viewBayaranLL".equals(mode)){

                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
                    logic.setMaklumatBayaranLL(idAkaun);
                    beanMaklumatBayaranLL = logic.getBeanMaklumatBayaranLL();
            		this.context.put("BeanMaklumatBayaranLL", beanMaklumatBayaranLL);

            		if (beanMaklumatBayaranLL.size() != 0){
            			Hashtable hashBayaranDB = (Hashtable) logic.getBeanMaklumatBayaranLL().get(0);
            			idCaraBayaran = (String) hashBayaranDB.get("idCaraBayar");
            			idBank = (String) hashBayaranDB.get("idBank");
            			idModBayaran = (String) hashBayaranDB.get("modBayaran");
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
                    	this.context.put("selectKategoriBayaran",HTML.SelectSuburusanByIdUrusan("12", "socKategoriBayaran", Long.parseLong(idKategoriBayaran), "disabled", "class=\"disabled\""));
            		}

        		} else if ("updateBayaranLL".equals(mode)){

                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranLL = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranLL.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranLL", beanMaklumatBayaranLL);

            		this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", "onChange=\"doChangeModBayaran();\""));
                	this.context.put("selectKategoriBayaran",HTML.SelectSuburusanByIdUrusan("12", "socKategoriBayaran", Long.parseLong(idKategoriBayaran), "", "onChange=\"doChangeModBayaran();\""));

        		}

        		senaraiAkaunLL(idHasil);
        	}

        	//MAKLUMAT PERJANJIAN
        	if ("3".equals(selectedTabUpper)){

        		//PERJANJIAN UTAMA
        		Vector senaraiPerjanjian = new Vector();
                logic.setListPerjanjian(idHasil);
                senaraiPerjanjian = logic.getSenaraiPerjanjian();
        		this.context.put("SenaraiPerjanjian", senaraiPerjanjian);

        		//PERJANJIAN TAMBAHAN
        		Vector senaraiPerjanjianTambahan = new Vector();
                logic.setListPerjanjianTambahan(idHasil);
                senaraiPerjanjianTambahan = logic.getSenaraiPerjanjianTambahan();
        		this.context.put("SenaraiPerjanjianTambahan", senaraiPerjanjianTambahan);
        	}
        	//MAKLUMAT PEMOHON
        	if ("4".equals(selectedTabUpper)){

        		if ("view".equals(mode)){

        			this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");

        			// MAKLUMAT PEMOHON
        			logic.setMaklumatPemohon(idHasil);
        			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
        			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
        			if (logic.getBeanMaklumatPemohon().size() != 0){
        				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
        				idPemohon = (String) hashPemohon.get("idPemohon");
        				idNegeri = (String) hashPemohon.get("idNegeri");
        				idBandar = (String) hashPemohon.get("idBandar");
        			}

        			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
        			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));

        		} else if ("update".equals(mode)){

        			this.context.put("readonly", "");
            		this.context.put("inputTextClass", "");
            		this.context.put("disabled", "");

            		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
        			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));

        			//MAKLUMAT PEMOHON
            		beanMaklumatPemohon = new Vector();
        			Hashtable hashPemohon = new Hashtable();
        			hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
        			hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
        			hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
        			hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
        			hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
        			hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
        			hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
        			hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
        			hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
        			hashPemohon.put("noRujukan", getParam("txtNoRujukan") == null ? "": getParam("txtNoRujukan"));
        			hashPemohon.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
        			beanMaklumatPemohon.addElement(hashPemohon);
        			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);

        		}
        	}

        	//MAKLUMAT PERMOHONAN
        	if ("5".equals(selectedTabUpper)){

        		if ("view".equals(mode)){

        			this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");

        			// MAKLUMAT PERMOHONAN
        			beanMaklumatPermohonan = new Vector();
        			logic.setMaklumatPermohonan(idHasil);
        			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
        			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        			if (logic.getBeanMaklumatPermohonan().size() != 0){
        				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
        				idHasil = (String) hashPermohonan.get("idHasil");
        				idUrusan = (String) hashPermohonan.get("idUrusan");
        				idSuburusan = (String) hashPermohonan.get("idSuburusan");
        				if (hashPermohonan.get("flagGuna") != null && hashPermohonan.get("flagGuna").toString().trim().length() != 0){
                			idLuasKegunaan = (String) hashPermohonan.get("flagGuna");
                		} else {
                			idLuasKegunaan = "99999";
                		}
        			}
        			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
        			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
        			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));

        		} else if ("update".equals(mode)){

        			this.context.put("readonly", "");
            		this.context.put("inputTextClass", "");
            		this.context.put("disabled", "");

            		this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "", " onChange=\"doChangeUrusan();\""));
        			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "", " "));
        			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan),"", ""));

        			// MAKLUMAT PERMOHONAN
        			beanMaklumatPermohonan = new Vector();
        			Hashtable hashPermohonan = new Hashtable();
        			hashPermohonan.put("noFail",getParam("noFail") == null ? "": getParam("noFail"));
        			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
        			hashPermohonan.put("tujuan", getParam("txtTujuan") == null ? "": getParam("txtTujuan"));
        			hashPermohonan.put("catatan", getParam("txtCatatanPermohonan") == null ? "": getParam("txtCatatanPermohonan"));
        			beanMaklumatPermohonan.addElement(hashPermohonan);
        			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

        		}
        	}

        	//MAKLUMAT TANAH
        	if ("6".equals(selectedTabUpper)){

        		if ("view".equals(mode)){

        			this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");

        			// MAKLUMAT TANAH
        			beanMaklumatTanah = new Vector();
        			logic.setMaklumatTanah(idFail);
        			beanMaklumatTanah = logic.getBeanMaklumatTanah();
        			if (logic.getBeanMaklumatTanah().size() != 0){
        				Hashtable hashMaklumatTanah = (Hashtable) logic.getBeanMaklumatTanah().get(0);
        				idNegeriTanah = (String) hashMaklumatTanah.get("idNegeri");
        				idKementerian = (String) hashMaklumatTanah.get("idKementerian");
        				idAgensi = (String) hashMaklumatTanah.get("idAgensi");
        				idLuas = (String) hashMaklumatTanah.get("idLuas");
        			}
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

        			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah",Long.parseLong(idNegeriTanah), "disabled", " class=\"disabled\""));
        			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
        			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

        		} else if ("update".equals(mode)){

        			this.context.put("readonly", "");
            		this.context.put("inputTextClass", "");
            		this.context.put("disabled", "");

            		// MAKLUMAT TANAH
        			beanMaklumatTanah = new Vector();
        			/*logic.setMaklumatTanah(idFail);
        			beanMaklumatTanah = logic.getBeanMaklumatTanah();
        			if (logic.getBeanMaklumatTanah().size() != 0){
        				Hashtable hashMaklumatTanah = (Hashtable) logic.getBeanMaklumatTanah().get(0);
        				idNegeriTanah = (String) hashMaklumatTanah.get("idNegeri");
        				idKementerian = (String) hashMaklumatTanah.get("idKementerian");
        				idAgensi = (String) hashMaklumatTanah.get("idAgensi");
        				idLuas = (String) hashMaklumatTanah.get("idLuas");
        				hashMaklumatTanah.put("maklumatLot", getParam("txtMaklumatLot") == null ? "": getParam("txtMaklumatLot"));
            			hashMaklumatTanah.put("catatanTanah",getParam("txtCatatanTanah") == null ? "": getParam("txtCatatanTanah"));
            			beanMaklumatTanah.addElement(hashMaklumatTanah);
        			}
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);*/
        			Hashtable hashMaklumatTanah = new Hashtable();
        			hashMaklumatTanah.put("lot", getParam("noLotTanah") == null ? "": getParam("noLotTanah"));
        			hashMaklumatTanah.put("hakmilik", getParam("noMilikTanah") == null ? "": getParam("noMilikTanah"));
        			hashMaklumatTanah.put("noWarta", getParam("noWartaTanah") == null ? "": getParam("noWartaTanah"));
        			hashMaklumatTanah.put("mukim", getParam("namaMukimTanah") == null ? "": getParam("namaMukimTanah"));
        			hashMaklumatTanah.put("daerah", getParam("namaDerahTanah") == null ? "": getParam("namaDerahTanah"));
        			hashMaklumatTanah.put("negeri", getParam("namaNegeriTanah") == null ? "": getParam("namaNegeriTanah"));
        			hashMaklumatTanah.put("tujuan", getParam("namatujuan") == null ? "": getParam("namatujuan"));
        			hashMaklumatTanah.put("statusRizab", getParam("status") == null ? "": getParam("status"));
        			hashMaklumatTanah.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
        			hashMaklumatTanah.put("maklumatLot", getParam("txtMaklumatLot") == null ? "": getParam("txtMaklumatLot"));
        			hashMaklumatTanah.put("luas",getParam("txtLuas") == null ? "": getParam("txtLuas"));
        			hashMaklumatTanah.put("catatanTanah",getParam("txtCatatanTanah") == null ? "": getParam("txtCatatanTanah"));
        			hashMaklumatTanah.put("idNegeriTanah", getParam("socNegeriTanah") == null ? "": getParam("socNegeriTanah"));
        			hashMaklumatTanah.put("idKementerian", getParam("socKementerian") == null ? "": getParam("socKementerian"));
        			hashMaklumatTanah.put("idAgensi", getParam("socAgensi") == null ? "": getParam("socAgensi"));
        			beanMaklumatTanah.addElement(hashMaklumatTanah);
        			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

        			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah",Long.parseLong(idNegeriTanah),"disabled", " class=\"disabled\""));
        			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
        			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

        		}
        	}

        	//MAKLUMAT ABT
        	if ("7".equals(selectedTabUpper)){
        		Hashtable abt = logic.getMaklumatABT(idHasil);
        		context.put("abt", abt);
        	}

        	//MAKLUMAT NOTIS
        	if ("8".equals(selectedTabUpper)){
        		//MAKLUMAT ABT
        		Hashtable abt = logic.getMaklumatABT(idHasil);
        		context.put("abt", abt);

        		//SENARAI NOTIS
        		Vector listNotis = logic.getSenaraiNotis(idHasil);
				this.context.put("listNotis", listNotis);
        	}
        	//MAKLUMAT MEMO
        	if ("9".equals(selectedTabUpper)){
        		//MAKLUMAT ABT
        		Hashtable abt = logic.getMaklumatABT(idHasil);
        		context.put("abt", abt);

        		//SENARAI MEMO
        		Vector listMemo = logic.getSenaraiMemo(idHasil);
				this.context.put("listMemo", listMemo);
        	}
        	//MAKLUMAT TINDAKAN MAHKAMAH
        	if ("10".equals(selectedTabUpper)){

        		this.context.put("readonly", "readonly");
    			this.context.put("inputTextClass", "disabled");
    			this.context.put("disabled", "disabled");

        		if("0".equals(selectedTabLower)){

            		if ("view".equals(mode)){

            			this.context.put("readonly", "readonly");
            			this.context.put("inputTextClass", "disabled");
            			this.context.put("disabled", "disabled");

            			// MAKLUMAT TINDAKAN MAHKAMAH
            			Hashtable tm = logic.getMaklumatTindakanMahkamah(idHasil);
                		context.put("tm", tm);

            		} else if ("update".equals(mode)){

            			this.context.put("readonly", "");
                		this.context.put("inputTextClass", "");
                		this.context.put("disabled", "");

                		// MAKLUMAT TANAH
            			beanMaklumatTanah = new Vector();
            			Hashtable hashMaklumatTanah = new Hashtable();
            			hashMaklumatTanah.put("maklumatLot", getParam("txtMaklumatLot") == null ? "": getParam("txtMaklumatLot"));
            			hashMaklumatTanah.put("luas",getParam("txtLuas") == null ? "": getParam("txtLuas"));
            			hashMaklumatTanah.put("catatanTanah",getParam("txtCatatanTanah") == null ? "": getParam("txtCatatanTanah"));
            			beanMaklumatTanah.addElement(hashMaklumatTanah);
            			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

            			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah",Long.parseLong(idNegeriTanah), "",""));
            			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
            			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", ""));

            		}
        		}else if("1".equals(selectedTabLower)){

        		}else{
        			Hashtable tm = logic.getMaklumatTindakanMahkamah(idHasil);
            		context.put("tm", tm);
        		}
        	}

        }else if ("muatNaikDokumen".equals(submit)) {//ros guna nie
					logic.hapusDokumen(idHasil);
					uploadFiles(idHasil, session);
					vm = "app/php2/refreshDokumenMuatNaik.jsp";
				}
        else {
        	vm = "app/php2/frmREVSenaraiFail.jsp";

        	// DROP DOWN CARIAN
    		String jenisHakmilik = getParam("socJenisHakmilik");
    		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
    			jenisHakmilik = "99999";
    		}
    		String jenisLot = getParam("socJenisLot");
    		if (jenisLot == null || jenisLot.trim().length() == 0){
    			jenisLot = "99999";
    		}
    		String idNegeriC = getParam("socNegeriC");
    		if (idNegeriC == null || idNegeriC.trim().length() == 0){
    			idNegeriC = "99999";
    		}
    		String idDaerahC = getParam("socDaerahC");
    		if (idDaerahC == null || idDaerahC.trim().length() == 0){
    			idDaerahC = "99999";
    		}
    		String idMukimC = getParam("socMukimC");
    		if (idMukimC == null || idMukimC.trim().length() == 0){
    			idMukimC = "99999";
    		}
    		String idKementerianC = getParam("socKementerianC");
			if (idKementerianC == null || idKementerianC.trim().length() == 0) {
				idKementerianC = "99999";
			}
			String idAgensiC = getParam("socAgensiC");
			if (idAgensiC == null || idAgensiC.trim().length() == 0) {
				idAgensiC = "99999";
			}
    		String idBankC = getParam("socBankC");
    		if (idBankC == null || idBankC.trim().length() == 0){
    			idBankC = "99999";
    		}
    		String idJenisFailC = getParam("socJenisFailC");
    		if (idJenisFailC == null || idJenisFailC.trim().length() == 0){
    			idJenisFailC = "99999";
    		}
    		String idStatusPerjanjianC = getParam("socStatusPerjanjianC");
    		if (idStatusPerjanjianC == null || idStatusPerjanjianC.trim().length() == 0){
    			idStatusPerjanjianC = "99999";
    		}
    		String flagDetail = getParam("flagDetail");

    		if ("S".equals(idJenisFailC)){

				this.context.put("selected", "");
				this.context.put("selectedD", "");
				this.context.put("selectedS", "selected");

			} else if ("D".equals(idJenisFailC)){

				this.context.put("selected", "");
				this.context.put("selectedD", "selected");
				this.context.put("selectedS", "");

			} else {
				this.context.put("selected", "selected");
				this.context.put("selectedD", "");
				this.context.put("selectedS", "");
			}

    		list = new Vector();

        	logic.carianFail(getParam("txtNoFail"), getParam("txtNamaPemohon"), getParam("txtNoRujukan"), idBankC,
        			getParam("txtNoCek"), getParam("txtNoResit"), idJenisFailC, idStatusPerjanjianC,
        			getParam("txtTujuan"), idNegeriC, idDaerahC, idMukimC, jenisHakmilik, getParam("txtNoHakmilik"),
        			getParam("txtNoWarta"), jenisLot, getParam("txtNoLot"), getParam("txtNoPegangan"), idKementerianC, idAgensiC);

    		list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNamaPemohon", getParam("txtNamaPemohon"));
			this.context.put("txtNoRujukan", getParam("txtNoRujukan"));
			this.context.put("txtNoCek", getParam("txtNoCek"));
			this.context.put("txtNoResit", getParam("txtNoResit"));
			this.context.put("selectBankC", HTML.selectBank("socBankC", Long.parseLong(idBankC), "", ""));
			this.context.put("socJenisFail", getParam("socJenisFail"));
			this.context.put("txtTujuan", getParam("txtTujuan"));
			this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
        	this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
        	this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("selectNegeriC",HTML.SelectNegeri("socNegeriC", Long.parseLong(idNegeriC), "", " onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerianC,  Long.parseLong(idAgensiC), "", ""));
			this.context.put("selectStatusPerjanjian", HTML.SelectStatusPerjanjianSewa("socStatusPerjanjianC", Long.parseLong(idStatusPerjanjianC), "", ""));
			this.context.put("flagDetail", flagDetail);

			setupPage(session,action,list);
        }

        //SET DEFAULT PARAM
		this.context.put("actionHasil", actionHasil);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("mode", mode);

		//SET DEFAULT ID PARAM
		this.context.put("idHasil", idHasil);
		this.context.put("idAkaun", idAkaun);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idNotis", idNotis);
		this.context.put("idCaraBayaran", idCaraBayaran);
		this.context.put("idModBayaran", idModBayaran);
		this.context.put("idKategoriBayaran", idKategoriBayaran);
		this.context.put("idNegeri", idNegeri);
		this.context.put("idBandar", idBandar);
		this.context.put("idLuas", idLuas);

		return vm;
	}

	private void senaraiDeposit(String idHasil, String action, HttpSession session) throws Exception {
		Vector senaraiDeposit = new Vector();
        logic.setListDeposit(idHasil);
        senaraiDeposit = logic.getSenaraiDeposit();
		this.context.put("SenaraiDeposit", senaraiDeposit);

		setupPageDeposit(session,action,senaraiDeposit);

		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalDeposit(idHasil);

		if (total > 0D){
			this.context.put("totalDeposit", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("totalDeposit", "(" + Util.formatDecimal(total * -1) + ")");
		} else {
			this.context.put("totalDeposit", "0.00");
		}
	}

	private void senaraiAkaun(String idHasil, String action, HttpSession session) throws Exception {
		Vector senaraiAkaun = new Vector();
        logic.setListAkaun(idHasil);
        senaraiAkaun = logic.getSenaraiAkaun();
		this.context.put("SenaraiAkaun", senaraiAkaun);

		setupPageSewa(session,action,senaraiAkaun);

		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotal(idHasil);

		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
			this.context.put("flag_tunggakan", "Y");
		} else if (total < 0D){
			this.context.put("total", "(" + Util.formatDecimal(total * -1) + ")");
			this.context.put("flag_tunggakan", "T");
		} else {
			this.context.put("total", "0.00");
			this.context.put("flag_tunggakan", "T");
		}
	}

	private void senaraiAkaunLL(String idHasil) throws Exception {
		Vector senaraiAkaunLL = new Vector();
        logic.setListAkaunLL(idHasil);
        senaraiAkaunLL = logic.getSenaraiAkaunLL();
		this.context.put("SenaraiAkaunLL", senaraiAkaunLL);

		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalLL(idHasil);

		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
			this.context.put("flag_tunggakan", "Y");
		} else if (total < 0D){
			this.context.put("total", Util.formatDecimal(total * -1));
			this.context.put("flag_tunggakan", "T");
		} else {
			this.context.put("total", "0.00");
			this.context.put("flag_tunggakan", "T");
		}
	}

	private void header(String idHasil) throws Exception {

		Vector beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idHasil);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			this.context.put("idFail", (String) hashPermohonan.get("idFail"));
			//this.context.put("txtNamaPemohon", (String) hashPermohonan.get("namaPemohon"));
			//this.context.put("txtNoFail", (String) hashPermohonan.get("noFail"));
		}
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

	public void setupPageDeposit(HttpSession session,String action,Vector list) {

		try {
			this.context.put("pagingTitle","");

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

		    //CUSTOMIZE BY PEJE - TO RESET ITEMSPERPAGE VALUE WHEN CHANGE TAB
		    if ("doChangeTabUpper".equals(getParam("command"))) {
				itemsPerPage = 10;
			}

		    Paging paging = new Paging(session,list,itemsPerPage);

			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiDeposit",paging.getPage(page));
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

	public void setupPageSewa(HttpSession session,String action,Vector list) {

		try {
			this.context.put("pagingTitle","");

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

		    //CUSTOMIZE BY PEJE - TO RESET ITEMSPERPAGE VALUE WHEN CHANGE TAB
		    if ("doChangeTabUpper".equals(getParam("command"))) {
				itemsPerPage = 10;
			}

		    Paging paging = new Paging(session,list,itemsPerPage);

			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiAkaun",paging.getPage(page));
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

	public static void hantarInvoisByEmel(HttpSession session, String idAkaun,
			String idFail, ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		myLogger.info("MASUK FUNCTION EMEL--------------------");
		Db db = null;
		Connection conn = null;
		String sql = "";
		String noFail = "";
		String namaUser = "";
		String emelUser = "nurulain.siprotech@gmail.com";
		String subject = "";
		String content = "";

		EmailSender email = EmailSender.getInstance();
		EmailProperty pro = EmailProperty.getInstance();

		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			sql = "SELECT A.NO_FAIL, C.NAMA, C.EMEL "
					+ " FROM TBLPFDFAIL A, TBLPHPHASIL B, TBLPHPPEMOHON C "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("MASUK "+sql);
			if (rs.next()) {
				noFail = rs.getString("NO_FAIL");
				namaUser = rs.getString("NAMA");
				//emelUser = rs.getString("EMEL");
			}

			if (!"".equals(namaUser) && !"".equals(emelUser)){

				subject = "INVOIS " + noFail;
				content = namaUser.toUpperCase() + "."
						+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
						+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan.";

				//To send attachments
				GetAttachment report = new GetAttachment();
				//ServletContext context = getServletContext();
				final Map<String, Object> myMap = new HashMap<String,Object>();
		    	String path ="";
		    	String folderName = "php2\\REV";
		    	String fileName = "REVInvoisSewa";

		    	if (folderName != null) {
					// path = "/reports/" + folderName + "/" + fileName ;
					path = File.separator + "reports" + File.separator + folderName + File.separator + fileName;
				} else {
					// path = "/reports/" + fileName ;
					path = File.separator + "reports" + File.separator + fileName;
				}

				FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();

		    	myMap.put("ID_AKAUN", idAkaun);
		    	myMap.put("KADAR_SEWA", logic.getKadarSewaSebulan(idAkaun));
		    	myMap.put("TUNGGAKAN_SEWA", logic.getTunggakanSewa(idAkaun));
		    	myMap.put("SEWA_SEMASA", logic.getKadarSewaSemasa(idAkaun));
		    	myMap.put("flagVersion", "no");
		    	myMap.put("ReportDir", path);
		    	myLogger.info("path "+path);
		    	myMap.put("os", "1");

		    	//parameter utk panggil report, boleh multiple
		    	byte[] bytes1 = report.getReportBytes("php2\\REV", "REVInvoisSewa", request, response, servletContext, myMap);

		    	//open razman add new feature : attachment in bytes
				email.ATTACHMENT_BYTES = new String[1];
				email.ATTACHMENT_BYTES[0] = new String(bytes1, "ISO-8859-1");;
				email.ATTACHMENT_BYTES_NAME = new String[1]; //kena sama dengan jumlah attachment
				email.ATTACHMENT_BYTES_NAME[0] = "Invois Sewa";//letak nama file bersesuaian
				//close razman add new feature : attachment in bytes

				email.SUBJECT = subject;
				email.MESSAGE = content;
				email.RECIEPIENT = emelUser;
				email.sendEmail();
			}
		} catch (DbException e) {
			myLogger.error(e);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void uploadFiles(String idHasil, HttpSession session) throws Exception {
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
					saveData(item, idHasil, logic.getIdPermohonanByIdHasil(idHasil), session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idHasil, String idPermohonan,
			HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();
			// TBLPHPDOKUMEN
			long idDokumenUpload = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, ID_MASUK, TARIKH_MASUK, CONTENT, JENIS_MIME, NAMA_FAIL, ID_HASIL, FLAG_DOKUMEN, ID_PERMOHONAN) "
							+ "values(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumenUpload);
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idHasil);
			ps.setString(9, "L");
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}

		this.context.put("flagStatus", "Y");
		this.context.put("idUlasanTeknikalReload", idHasil);
	}

}
