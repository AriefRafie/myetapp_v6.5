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
import ekptg.model.php2.FrmCRBHeaderData;
import ekptg.model.php2.FrmCRBLawatanTapakData;

public class FrmCRBLawatanTapakView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBLawatanTapakData logic = new FrmCRBLawatanTapakData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	// @Override
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
			
        //SET VALUE USER
	    this.context.put("userId", userId);
	   	this.context.put("userRole", userRole);
	  	this.context.put("idNegeriUser", idNegeriUser);

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String actionCRB = getParam("actionCRB");
		
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower)) {
			selectedTabLower = "0";
		}		
		
		boolean flagOpenDetail = false;
		String status = "";

		// GET ID PARAM
		String idFail = getParam("idFail");
		String step = getParam("step");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");		
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idLaporanTanah = getParam("idLaporanTanah");
        String idNegeriTanah = getParam("idNegeriTanah");
        String idPenceroboh = getParam("idPenceroboh");
        String idPegawaiLaporanTanah = getParam("idPegawaiLaporanTanah");
        String idDokumen = getParam("idDokumen");	
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagStatus = getParam("flagStatus");
		String flagAktif = "";
		String flagReKeyin = "";
		String flagReKeyinPenceroboh = "";

		// VECTOR
		Vector beanHeader = null;	
		Vector senaraiKJT = null;
        Vector beanMaklumatKJT = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiLawatanTapak = null;
        Vector beanMaklumatLaporanTanah = null;
        Vector senaraiPenceroboh = null;
        Vector beanMaklumatPenceroboh = null;
        Vector senaraiKehadiran = null;
        Vector beanMaklumatKehadiran = null;
        Vector senaraiImejan = null;
        Vector beanMaklumatImejan = null;
        Vector senaraiLampiran = null;
        Vector beanMaklumatLampiran = null;

		// GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}	
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0) {
			idBangsa = "99999";
		}
		String idNegeriPenceroboh = getParam("socNegeriPenceroboh");
		if (idNegeriPenceroboh == null || idNegeriPenceroboh.trim().length() == 0) {
			idNegeriPenceroboh = "99999";
		}
		String idBandarPenceroboh = getParam("socBandarPenceroboh");
		if (idBandarPenceroboh == null || idBandarPenceroboh.trim().length() == 0) {
			idBandarPenceroboh = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idNegeriPelapor = getParam("socidNegeriPelapor");
		if (idNegeriPelapor == null || idNegeriPelapor.trim().length() == 0) {
			idNegeriPelapor = "99999";
		}
		String idNegeriPenyemak = getParam("socidNegeriPenyemak");
		if (idNegeriPenyemak == null || idNegeriPenyemak.trim().length() == 0) {
			idNegeriPenyemak = "99999";
		}
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0) {
			idPegawai = "99999";
		}
		
		this.context.put("completed", false);
		this.context.put("onload", "");

		vm = "app/php2/frmCRBSenaraiLawatanTapak.jsp";

		// HITBUTTON
		if (postDB) {
			if ("simpanMaklumatKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJT(idPermohonan, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		//logic.sendEmail(idPermohonan, idPejabat, session);
        		session.setAttribute("MSG", "Emel telah dihantar kepada JKPTG berkaitan");
    		}
        	if ("simpanMaklumatUlanganKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJT(idUlasanTeknikal, idPermohonan, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatKJT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
    		}
        	if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
    		}
        	if ("doSimpanSiasatanLT".equals(hitButton)) {
				idLaporanTanah = logic.simpanSiasatanLawatanTapak(idPermohonan,
						getParam("txtTarikhTerimaFail"), getParam("txtTarikhLawatan"), getParam("txtTarikhLaporan"),
						getParam("txtTujuanLaporan"), 
						getParam("txtLokasi"), getParam("txtLaporanSemasaTapak"),
						getParam("txtIsuUlasan"), logicHeader.getIdHakmilikPermohonanByIdFail(idFail), session);
				this.context.put("idLaporanTanah", idLaporanTanah);
			}
        	if ("simpanKemaskiniMaklumatSiasatanLT".equals(hitButton)) {
				logic.updateSiasatanLawatanTapak(idPermohonan, idLaporanTanah,
						getParam("txtTarikhTerimaFail"), getParam("txtTarikhLawatan"), getParam("txtTarikhLaporan"),
						getParam("txtTujuanLaporan"), 
						getParam("txtLokasi"), getParam("txtLaporanSemasaTapak"),
						getParam("txtIsuUlasan"), session);
			}
        	if ("doHapusMaklumatLT".equals(hitButton)) {
				logic.hapusMaklumatLT(idLaporanTanah, session);
			}
        	if ("doSimpanKemaskiniMaklumatLain".equals(hitButton)) {
				logic.simpanKemaskiniMaklumatLain(idLaporanTanah,
						getParam("txtJalanHubungan"),
						getParam("txtKawasanBerhampiran"),
						getParam("txtJarakDariBandar"),
						getParam("kemudahanAsasA"), getParam("kemudahanAsasL"),
						getParam("kemudahanAsasT"),
						getParam("txtKemudahanAsas"),
						getParam("txtKeadaanTanah"),
						getParam("txtKeadaanRupabumi"), getParam("txtUtara"),
						getParam("txtSelatan"), getParam("txtTimur"),
						getParam("txtBarat"), session);
			}
        	if ("doSimpanPenceroboh".equals(hitButton)) {
				idPenceroboh = logic.savePenceroboh(idPermohonan,
						idLaporanTanah, getParam("txtNamaPenceroboh"),
						getParam("txtNoTelefon"),
						getParam("txtNoTelefonBimbit"), idBangsa,
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idNegeriPenceroboh, idBandarPenceroboh,
						getParam("txtEmel"), getParam("txtJenisPencerobohan"),session);
				flagReKeyinPenceroboh = "Y";
				idBangsa = "99999";
				idNegeriPenceroboh = "99999";
				idBandarPenceroboh = "99999";
			}
			if ("doSimpanKemaskiniPenceroboh".equals(hitButton)) {
				logic.updatePenceroboh(idPenceroboh,
						getParam("txtNamaPenceroboh"),
						getParam("txtNoTelefon"),
						getParam("txtNoTelefonBimbit"), idBangsa,
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idNegeriPenceroboh, idBandarPenceroboh,
						getParam("txtEmel"), getParam("txtJenisPencerobohan"),session);
			}
			if ("doHapusMaklumatPenceroboh".equals(hitButton)) {
				logic.hapusMaklumatPenceroboh(idPenceroboh, session);
			}
			if ("doSimpanKehadiran".equals(hitButton)) {
				idPegawaiLaporanTanah = logic.saveKehadiran(idLaporanTanah,
						getParam("txtNama"), getParam("txtJawatan"),
						idKementerian, idAgensi, session);
				flagReKeyin = "Y";
				idKementerian = "99999";
				idAgensi = "99999";
			}
			if ("doSimpanKemaskiniKehadiran".equals(hitButton)) {
				logic.updateKehadiran(idPegawaiLaporanTanah,
						getParam("txtNama"), getParam("txtJawatan"),
						idKementerian, idAgensi, session);
			}
			if ("doHapusMaklumatKehadiran".equals(hitButton)) {
				logic.hapusMaklumatKehadiran(idPegawaiLaporanTanah, session);
			}
			if ("doSimpanKemaskiniMaklumatPelapor".equals(hitButton)) {
				logic.updatePelapor(idLaporanTanah, getParam("txtNamaPelapor"),
						getParam("txtJawatanPelapor"), idNegeriPelapor, getParam("txtNamaPenyemak"),
						getParam("txtJawatanPenyemak"), idNegeriPenyemak,  session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idLaporanTanah, idPermohonan, "I", session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen,
						getParam("txtNamaImej"), getParam("txtCatatanImej"),
						session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
			if ("simpanLampiran".equals(hitButton)) {
				uploadFiles(idLaporanTanah, idPermohonan, "L", session);
			}
			if ("simpanKemaskiniLampiran".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen,
						getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"),
						session);
			}
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}
        	if ("gotoIbuPejabat".equals(hitButton)){
        		logic.gotoHantarHQ(idFail, idNegeriUser, idPermohonan, session);
        		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DIHANTAR KE IBUPEJABAT");
	    		this.context.put("onload", "gotoSenaraiFail();");
        	}
        	if ("gotoHantarTugasanPP".equals(hitButton)){
	    		logic.gotoHantarTugasanPP(idFail, idNegeriUser, session);
	    		session.removeAttribute("ID_FAIL");
				session.setAttribute("MSG", "FAIL TELAH DITUGASKAN KEPADA PENOLONG PENGARAH");
	    		this.context.put("onload", "gotoSenaraiFail();");
			}
			if ("doSelesaiPermohonan".equals(hitButton)){
				logicHeader.doSelesaiPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhSelesai"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    			
			} else if ("gotoHantarTugasanPPT".equals(step)){
    			this.context.put("selectPegawai", HTML.SelectPYWPenolongPegawaiTanahHQ("socPegawai", Long.parseLong(idPegawai), "", ""));
    			vm = "app/php2/frmPYWAgihanTugas.jsp";
            
    		} 
		}// END POSTDB

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			status = (String) hashHeader.get("status");			
		}
		
		//MAKLUMAT HAKMILIK
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idNegeriTanah = (String) hashHakmilik.get("idNegeri");					
		}
		
		//GET FLAG OPEN DETAIL
		flagOpenDetail =  logicHeader.getFlagOpenDetail(idStatus, 3); //3 = LAWATAN TAPAK
		
		if ("0".equals(selectedTabUpper)) {
			
			//OPEN POPUP DETAIL MAKLUMAT KJT
			if ("openKJT".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", "");
		    			hashMaklumatKJT.put("jangkamasa", "");
		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
						
						idNegeri = "99999";
						idPejabat = "99999";
						
	    			} else {
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
	    			}
	    			
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", "");
		    			hashMaklumatKJT.put("jangkamasa", "");
		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
						
	    			} else {
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
	    			}

					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatKJT = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatKJT = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatKJT",beanMaklumatKJT);
					
					if (beanMaklumatKJT.size() != 0){
						Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						idNegeri = (String) hashMaklumatKJT.get("idNegeri");
						idPejabat = (String) hashMaklumatKJT.get("idPejabat");
						flagStatus = (String) hashMaklumatKJT.get("flagStatus");
						flagAktif = (String) hashMaklumatKJT.get("flagAktif");
					}
				
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatKJT.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatKJT.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatKJT.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatKJT.put("ulasan", getParam("txtUlasan"));
	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
					
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
	    			
					if ("1".equals(flagStatus)){						
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
					} else {
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					}					
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			//DOKUMEN KJT
			senaraiKJT = new Vector();
			logic.setSenaraiKJT(idPermohonan);
			senaraiKJT = logic.getListKJT();
			this.context.put("SenaraiKJT", senaraiKJT);
		
		} else if ("1".equals(selectedTabUpper)) {
			
			if ("newLawatanTapak".equals(actionCRB)) {
				
				if ("new".equals(modePopup)){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
	    			
	    			// MAKLUMAT LAWATAN TAPAK
					beanMaklumatLaporanTanah = new Vector();
					Hashtable hashLT = new Hashtable();
					hashLT.put("tarikhTerimaFail", getParam("txtTarikhTerimaFail") == null ? "" : getParam("txtTarikhTerimaFail"));
					hashLT.put("tarikhLawatan", getParam("txtTarikhLawatan") == null ? "" : getParam("txtTarikhLawatan"));
					hashLT.put("tarikhLaporan", getParam("txtTarikhLaporan") == null ? "" : getParam("txtTarikhLaporan"));
					hashLT.put("tujuanLaporan", getParam("txtTujuanLaporan") == null ? "" : getParam("txtTujuanLaporan"));
					hashLT.put("lokasi", getParam("txtLokasi") == null ? "" : getParam("txtLokasi"));
					hashLT.put("laporanSemasaTapak", getParam("txtLaporanSemasaTapak") == null ? "" : getParam("txtLaporanSemasaTapak"));
					hashLT.put("ulasan", getParam("txtIsuUlasan") == null ? "" : getParam("txtIsuUlasan"));
					beanMaklumatLaporanTanah.addElement(hashLT);
					this.context.put("BeanMaklumatLaporanTanah", beanMaklumatLaporanTanah);
				}
				
			} else if ("viewLawatanTapak".equals(actionCRB)) {
				
				if ("0".equals(selectedTabLower) || "1".equals(selectedTabLower) || "4".equals(selectedTabLower)) {
					
					beanMaklumatLaporanTanah = new Vector();
					logic.setMaklumatLaporanTanah(idLaporanTanah);
					beanMaklumatLaporanTanah = logic.getBeanMaklumatLaporanTanah();
					this.context.put("BeanMaklumatLaporanTanah",beanMaklumatLaporanTanah);
					
					if (beanMaklumatLaporanTanah.size() != 0) {
						Hashtable hashMaklumatPelapor = (Hashtable) logic.getBeanMaklumatLaporanTanah().get(0);
						idNegeriPelapor = (String) hashMaklumatPelapor.get("idNegeri");
						idNegeriPenyemak = (String) hashMaklumatPelapor.get("idNegeriPenyemak");
					}
					
					if ("view".equals(modePopup)) {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
						
						this.context.put("selectNegeriPelapor", HTML.SelectNegeri("socidNegeriPelapor", Long.parseLong(idNegeriPelapor), "disabled"," class=\"disabled\""));
						this.context.put("selectNegeriPenyemak", HTML.SelectNegeri("socidNegeriPenyemak", Long.parseLong(idNegeriPenyemak), "disabled"," class=\"disabled\""));
					} else {						
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
						
						this.context.put("selectNegeriPelapor", HTML.SelectNegeri("socidNegeriPelapor", Long.parseLong(idNegeriPelapor), "", ""));
						this.context.put("selectNegeriPenyemak", HTML.SelectNegeri("socidNegeriPenyemak", Long.parseLong(idNegeriPenyemak), "", ""));
					}					
				}
				
				if ("2".equals(selectedTabLower)) {					
					
					if ("openPopupPenceroboh".equals(flagPopup)) {

						// MODEPOPUP = NEW
						if ("new".equals(modePopup)) {
							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							if ("".equals(submit)) {

								beanMaklumatPenceroboh = new Vector();
								Hashtable hashMaklumatPenceroboh = new Hashtable();
								hashMaklumatPenceroboh.put("namaPenceroboh", "");
								hashMaklumatPenceroboh.put("noTelefon", "");
								hashMaklumatPenceroboh.put("noTelefonBimbit","");
								hashMaklumatPenceroboh.put("alamat1", "");
								hashMaklumatPenceroboh.put("alamat2", "");
								hashMaklumatPenceroboh.put("alamat3", "");
								hashMaklumatPenceroboh.put("poskod", "");
								hashMaklumatPenceroboh.put("emel", "");
								hashMaklumatPenceroboh.put("jenisPencerobohan", getParam("txtJenisPencerobohan"));
								beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
								this.context.put("BeanMaklumatPenceroboh", beanMaklumatPenceroboh);
								idBangsa = "99999";
								idNegeriPenceroboh = "99999";
								idBandarPenceroboh = "99999";
						
							} else {
								beanMaklumatPenceroboh = new Vector();
								Hashtable hashMaklumatPenceroboh = new Hashtable();
								hashMaklumatPenceroboh.put("namaPenceroboh", getParam("txtNamaPenceroboh"));
								hashMaklumatPenceroboh.put("noTelefon", getParam("txtNoTelefon"));
								hashMaklumatPenceroboh.put("noTelefonBimbit", getParam("txtNoTelefonBimbit"));
								hashMaklumatPenceroboh.put("alamat1", getParam("txtAlamat1"));
								hashMaklumatPenceroboh.put("alamat2", getParam("txtAlamat2"));
								hashMaklumatPenceroboh.put("alamat3", getParam("txtAlamat3"));
								hashMaklumatPenceroboh.put("poskod", getParam("txtPoskod"));
								hashMaklumatPenceroboh.put("emel", getParam("txtEmel"));
								hashMaklumatPenceroboh.put("jenisPencerobohan", getParam("txtJenisPencerobohan"));
								beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
								this.context.put("BeanMaklumatPenceroboh", beanMaklumatPenceroboh);
							}

							this.context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), ""));
							this.context.put("selectNegeriPenceroboh",HTML.SelectNegeri("socNegeriPenceroboh",Long.parseLong(idNegeriPenceroboh),""," onChange=\"doChangeNegeriPenceroboh();\""));
							this.context.put("selectBandarPenceroboh", HTML.SelectBandarByNegeri(idNegeriPenceroboh,"socBandarPenceroboh",Long.parseLong(idBandarPenceroboh),""));
						}

						// MODEPOPUP = VIEW
						if ("view".equals(modePopup)) {
							this.context.put("readonlyPopup", "readonly");
							this.context.put("inputTextClassPopup", "disabled");

							beanMaklumatPenceroboh = new Vector();
							logic.setMaklumatPenceroboh(idPenceroboh);
							beanMaklumatPenceroboh = logic.getBeanMaklumatPenceroboh();
							this.context.put("BeanMaklumatPenceroboh",beanMaklumatPenceroboh);

							if (beanMaklumatPenceroboh.size() != 0) {
								Hashtable hashMaklumatPenceroboh = (Hashtable) logic.getBeanMaklumatPenceroboh().get(0);
								this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa",Long.parseLong((String) hashMaklumatPenceroboh.get("idBangsa")),"disabled"," class=\"disabled\""));
								this.context.put("selectNegeriPenceroboh",HTML.SelectNegeri("socNegeriPenceroboh",Long.parseLong((String) hashMaklumatPenceroboh.get("idNegeriPenceroboh")),"disabled"," class=\"disabled\""));
								this.context.put("selectBandarPenceroboh",HTML.SelectBandarByNegeri((String) hashMaklumatPenceroboh.get("idNegeriPenceroboh"),	"socBandarPenceroboh",	Long.parseLong((String) hashMaklumatPenceroboh	.get("idBandarPenceroboh")),"disabled"," class=\"disabled\""));
							}
						}

						// MODEPOPUP = UPDATE
						if ("update".equals(modePopup)) {
							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							beanMaklumatPenceroboh = new Vector();
							logic.setMaklumatPenceroboh(idPenceroboh);
							Hashtable hashMaklumatPencerobohDB = (Hashtable) logic.getBeanMaklumatPenceroboh().get(0);
							Hashtable hashMaklumatPenceroboh = new Hashtable();
							hashMaklumatPenceroboh.put("namaPenceroboh",getParam("txtNamaPenceroboh") == null ? hashMaklumatPencerobohDB.get("namaPenceroboh"): getParam("txtNamaPenceroboh"));
							hashMaklumatPenceroboh.put("noTelefon",getParam("txtNoTelefon") == null ? hashMaklumatPencerobohDB.get("noTelefon"): getParam("txtNoTelefon"));
							hashMaklumatPenceroboh.put("noTelefonBimbit",getParam("txtNoTelefonBimbit") == null ? hashMaklumatPencerobohDB.get("noTelefonBimbit"): getParam("txtNoTelefonBimbit"));
							hashMaklumatPenceroboh.put("alamat1",getParam("txtAlamat1") == null ? hashMaklumatPencerobohDB.get("alamat1"): getParam("txtAlamat1"));
							hashMaklumatPenceroboh.put("alamat2",getParam("txtAlamat2") == null ? hashMaklumatPencerobohDB.get("alamat2"): getParam("txtAlamat2"));
							hashMaklumatPenceroboh.put("alamat3",getParam("txtAlamat3") == null ? hashMaklumatPencerobohDB.get("alamat3"): getParam("txtAlamat3"));
							hashMaklumatPenceroboh.put("poskod",getParam("txtPoskod") == null ? hashMaklumatPencerobohDB.get("poskod"): getParam("txtPoskod"));
							hashMaklumatPenceroboh.put("emel",getParam("txtEmel") == null ? hashMaklumatPencerobohDB.get("emel"): getParam("txtEmel"));
							hashMaklumatPenceroboh.put("jenisPencerobohan",getParam("txtJenisPencerobohan") == null ? hashMaklumatPencerobohDB.get("jenisPencerobohan"): getParam("txtJenisPencerobohan"));
							beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
							this.context.put("BeanMaklumatPenceroboh",beanMaklumatPenceroboh);

							if (beanMaklumatPenceroboh.size() != 0) {
								Hashtable hashPermohonanDB = (Hashtable) logic.getBeanMaklumatPenceroboh().get(0);

								if (idBangsa == "99999") {
									idBangsa = (String) hashPermohonanDB.get("idBangsa");
								}
								if (idNegeriPenceroboh == "99999") {
									idNegeriPenceroboh = (String) hashPermohonanDB.get("idNegeriPenceroboh");
								}
								if (idBandarPenceroboh == "99999") {
									idBandarPenceroboh = (String) hashPermohonanDB.get("idBandarPenceroboh");
								}
							}

							this.context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "",""));
							this.context.put("selectNegeriPenceroboh",HTML.SelectNegeri("socNegeriPenceroboh", Long.parseLong(idNegeriPenceroboh),""," onChange=\"doChangeNegeriPenceroboh();\""));
							this.context.put("selectBandarPenceroboh", HTML.SelectBandarByNegeri(idNegeriPenceroboh,"socBandarPenceroboh",Long.parseLong(idBandarPenceroboh),"", ""));
						}
					}
					
					logic.setSenaraiPenceroboh(idPermohonan, idLaporanTanah);
					senaraiPenceroboh = logic.getListPenceroboh();
					this.context.put("SenaraiPenceroboh", senaraiPenceroboh);
				}
				
				if ("3".equals(selectedTabLower)) {		

					if ("openPopupKehadiran".equals(flagPopup)) {

						// MODEPOPUP = NEW
						if ("new".equals(modePopup)) {
							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							if (!"".equals(submit)) {

								beanMaklumatKehadiran = new Vector();
								Hashtable hashMaklumatKehadiran = new Hashtable();
								hashMaklumatKehadiran.put("namaPegawai",getParam("txtNama"));
								hashMaklumatKehadiran.put("jawatan",getParam("txtJawatan"));
								beanMaklumatKehadiran.addElement(hashMaklumatKehadiran);
								this.context.put("BeanMaklumatKehadiran",beanMaklumatKehadiran);

								this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian),""," onChange=\"doChangeKementerian();\""));
								this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi",idKementerian, Long.parseLong(idAgensi),"", ""));

							} else {

								beanMaklumatKehadiran = new Vector();
								Hashtable hashMaklumatKehadiran = new Hashtable();
								hashMaklumatKehadiran.put("namaPegawai", "");
								hashMaklumatKehadiran.put("jawatan", "");
								beanMaklumatKehadiran.addElement(hashMaklumatKehadiran);
								this.context.put("BeanMaklumatKehadiran",beanMaklumatKehadiran);

								idKementerian = "99999";

								this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian),""," onChange=\"doChangeKementerian();\""));
								this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi",idKementerian, Long.parseLong(idAgensi),"", ""));
							}
						}

						// MODEPOPUP = VIEW
						if ("view".equals(modePopup)) {
							this.context.put("readonlyPopup", "readonly");
							this.context.put("inputTextClassPopup", "disabled");

							beanMaklumatKehadiran = new Vector();
							logic.setMaklumatKehadiran(idPegawaiLaporanTanah);
							beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
							this.context.put("BeanMaklumatKehadiran",beanMaklumatKehadiran);

							if (beanMaklumatKehadiran.size() != 0) {
								Hashtable hashMaklumatKehadiranDB = (Hashtable) logic.getBeanMaklumatKehadiran().get(0);
								this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong((String) hashMaklumatKehadiranDB	.get("idKementerian")),"disabled"," class=\"disabled\""));
								this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",(String) hashMaklumatKehadiranDB.get("idKementerian"),Long.parseLong((String) hashMaklumatKehadiranDB.get("idAgensi")),"disabled"," class=\"disabled\""));
							}
						}

						// MODEPOPUP = UPDATE
						if ("update".equals(modePopup)) {
							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							beanMaklumatKehadiran = new Vector();
							logic.setMaklumatKehadiran(idPegawaiLaporanTanah);
							Hashtable hashMaklumatKehadiranDB = (Hashtable) logic.getBeanMaklumatKehadiran().get(0);
							Hashtable hashMaklumatKehadiran = new Hashtable();
							hashMaklumatKehadiran.put("idPegawaiLaporanTanah",	hashMaklumatKehadiranDB.get("idPegawaiLaporanTanah"));
							hashMaklumatKehadiran.put("namaPegawai",getParam("txtNama") == null ? hashMaklumatKehadiranDB.get("namaPegawai"): getParam("txtNama"));
							hashMaklumatKehadiran.put("jawatan",getParam("txtJawatan") == null ? hashMaklumatKehadiranDB.get("jawatan"): getParam("txtJawatan"));
							beanMaklumatKehadiran.addElement(hashMaklumatKehadiran);
							this.context.put("BeanMaklumatKehadiran",beanMaklumatKehadiran);

							if (beanMaklumatKehadiran.size() != 0) {
								Hashtable hashPermohonanDB = (Hashtable) logic.getBeanMaklumatKehadiran().get(0);

								if (idKementerian == "99999") {
									idKementerian = (String) hashPermohonanDB.get("idKementerian");
								}
								if (idAgensi == "99999") {
									idAgensi = (String) hashPermohonanDB.get("idAgensi");
								}
							}

							this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian),""," onChange=\"doChangeKementerian();\""));
							this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi",idKementerian, Long.parseLong(idAgensi), ""," "));

							if ("doChangeKementerian".equals(submit)) {
								this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi",idKementerian, Long.parseLong(idAgensi),"", ""));
							}
						}
					}

					// SENARAI KEHADIRAN
					senaraiKehadiran = new Vector();
					logic.setSenaraiKehadiran(idLaporanTanah);
					senaraiKehadiran = logic.getListKehadiran();
					this.context.put("SenaraiKehadiran", senaraiKehadiran);
				}
				
				if ("5".equals(selectedTabLower)) {	

					// OPEN POPUP DOKUMEN
					if ("openPopupDokumen".equals(flagPopup)) {

						if ("new".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							beanMaklumatImejan = new Vector();
							Hashtable hashMaklumatImejan = new Hashtable();
							hashMaklumatImejan.put("namaImej", "");
							hashMaklumatImejan.put("catatanImej", "");
							beanMaklumatImejan.addElement(hashMaklumatImejan);
							this.context.put("BeanMaklumatImejan",beanMaklumatImejan);

						} else if ("update".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							// MAKLUMAT DOKUMEN
							beanMaklumatImejan = new Vector();
							logic.setMaklumatImej(idDokumen);
							beanMaklumatImejan = logic.getBeanMaklumatImejan();
							this.context.put("BeanMaklumatImejan",beanMaklumatImejan);

						} else if ("view".equals(modePopup)) {

							this.context.put("readonlyPopup", "readonly");
							this.context.put("inputTextClassPopup", "disabled");

							// MAKLUMAT DOKUMEN
							beanMaklumatImejan = new Vector();
							logic.setMaklumatImej(idDokumen);
							beanMaklumatImejan = logic.getBeanMaklumatImejan();
							this.context.put("BeanMaklumatImejan",beanMaklumatImejan);
						}
					}

					// SENARAI IMEJAN
					senaraiImejan = new Vector();
					logic.setSenaraiImejan(idLaporanTanah);
					senaraiImejan = logic.getListImejan();
					this.context.put("SenaraiImejan", senaraiImejan);
				}
				
				if ("6".equals(selectedTabLower)) {	

					// OPEN POPUP DOKUMEN
					if ("openPopupDokumen".equals(flagPopup)) {

						if ("new".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							beanMaklumatLampiran = new Vector();
							Hashtable hashMaklumatLampiran = new Hashtable();
							hashMaklumatLampiran.put("namaLampiran", "");
							hashMaklumatLampiran.put("catatanLampiran", "");
							beanMaklumatLampiran.addElement(hashMaklumatLampiran);
							this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);

						} else if ("update".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							// MAKLUMAT DOKUMEN
							beanMaklumatLampiran = new Vector();
							logic.setMaklumatLampiran(idDokumen);
							beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
							this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);

						} else if ("view".equals(modePopup)) {

							this.context.put("readonlyPopup", "readonly");
							this.context.put("inputTextClassPopup", "disabled");

							// MAKLUMAT DOKUMEN
							beanMaklumatLampiran = new Vector();
							logic.setMaklumatLampiran(idDokumen);
							beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
							this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
						}
					}

					// SENARAI LAMPIRAN
					senaraiLampiran = new Vector();
					logic.setSenaraiLampiran(idLaporanTanah);
					senaraiLampiran = logic.getListLampiran();
					this.context.put("SenaraiLampiran", senaraiLampiran);
				}
			}
			
			// SENARAI LAWATAN TAPAK
			logic.setSenaraiLawatanTapak(idPermohonan);
			senaraiLawatanTapak = logic.getListLawatanTapak();
			this.context.put("SenaraiLawatanTapak", senaraiLawatanTapak);

		}
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }

		// SET DEFAULT PARAM
		this.context.put("actionCRB", actionCRB);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("selectedTabLower", selectedTabLower);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);	
		this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
	
		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idLaporanTanah", idLaporanTanah);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idNegeriTanah", idNegeriTanah);
		this.context.put("idPenceroboh", idPenceroboh);
		this.context.put("idPegawaiLaporanTanah", idPegawaiLaporanTanah);
		this.context.put("idDokumen", idDokumen);
		this.context.put("flagOpenDetail", flagOpenDetail);
	    this.context.put("status", status.toUpperCase());
	    
	    if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }

		return vm;
	}

	// UPLOAD FILE
	private void uploadFiles(String idLaporanTanah, String idPermohonan, String flagDokumen, HttpSession session) throws Exception {
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
					saveData(item, idLaporanTanah, idPermohonan, flagDokumen, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idLaporanTanah, String idPermohonan, String flagDokumen, HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_LAPORANTANAH,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			if ("L".equals(flagDokumen)){
				ps.setString(2, getParam("namaLampiran"));
				ps.setString(3, getParam("catatanLampiran"));
			} else {
				ps.setString(2, getParam("namaImej"));
				ps.setString(3, getParam("catatanImej"));
			}			
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idLaporanTanah);
			ps.setString(9, flagDokumen);
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idDokumen
							+ "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		
		this.context.put("completed", true);
	}
}
