/**
 * 
 */
package ekptg.view.php2.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.php2.FrmPHPDokumenData;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWTawaranKJPData;
import ekptg.model.php2.online.FrmPLPOnlineKJPSenaraiFailData;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserKJPBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.view.htp.online.jrp.HTPEmelJRPBean;

public class FrmPNWTawaranKJPView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(FrmPNWTawaranKJPView.class);
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWTawaranKJPData logic = new FrmPNWTawaranKJPData();
	FrmPHPDokumenData logicDokumen = new FrmPHPDokumenData();
	private String readonly = "disabled class = \"disabled\"";
	private IHtp iHTP = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	private IUserPegawai iUser = null;
	
	
	static Logger myLogger = Logger.getLogger(FrmPNWTawaranKJPView.class);


	@Override 
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String actionPenawaran = getParam("actionPenawaran");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagReKeyin = "";
		
		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");
		String idPenawaranKJP = getParam("idPenawaranKJP");
		String idDokumen = getParam("idDokumen");
		String noRujukanKJP = getParam("txtNoRujukanKJP");
		String txtTarikhTerima = getParam("txtTarikhTerima");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		String idJenisTanah = "1";
		
		String idKategoriPemohon = "";
		String idNegeriPemohon = "";
		String userJawatan = "";
		String idJawatan = "";
		String namaPemohon = "";

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiAgensi = null;
        Vector beanMaklumatAgensi = null;
        Vector beanMaklumatImejan = null;               
        Vector list = null;
        Vector listDetailKJP = null;
        
        // GET DROPDOWN PARAM
        String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		
		String step = getParam("step");
		
		String cadanganKegunaan = "";
		String noRujukanSurat = "";
		String idAgensiDef = "";
		String noFail = "";
		
		
		listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
		if (!listDetailKJP.isEmpty() && listDetailKJP.size() > 0) {
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			idNegeriPemohon = hashRayuanDB.get("idNegeri").toString();
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			idAgensi = hashRayuanDB.get("idAgensi").toString();
			//myLog.info("JAWATAN="+userJawatan);
			myLogger.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}

		//userJawatan = String.valueOf(hUser.get("userJawatan"));
		//context.put("idjawatan", idJawatan);

		this.context.put("idNegeriPemohon", idNegeriPemohon);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		
		
		
		//SUBMIT TO NEXT PROCESS
		if (postDB) {
			if ("simpanAgensi".equals(hitButton)) {
				idPenawaranKJP = logic.simpanAgensi(idPermohonan, getParam("txtNoRujukanKJP"), getParam("txtTarikhTerima"), idKementerian, idAgensi, getParam("txtTujuanKegunaan"), session);
				flagReKeyin = "Y";
				idKementerian = "99999";
				idAgensi = "99999";
    			uploadFiles(idPenawaranKJP, idPermohonan, session);
    			
    			Hashtable lampiran = logic.getMaklumatLampiran(idPermohonan);
				this.context.put("lampiran", lampiran);
				this.context.put("idPermohonan", idPermohonan);
				
			}
			if ("simpanKemaskiniAgensi".equals(hitButton)) {
				logic.simpanKemaskiniAgensi(idPenawaranKJP, getParam("txtNoRujukanKJP"), getParam("txtTarikhTerima"), idKementerian, idAgensi, getParam("txtTujuanKegunaan"), session);
				updateUploadFile(idDokumen, session);
				
				Hashtable lampiran = logic.getMaklumatLampiran(idPermohonan);
				this.context.put("lampiran", lampiran);
				
			}
			if ("hapusAgensi".equals(hitButton)) {
				logic.hapusAgensi(idPenawaranKJP,idDokumen, session);
			}
			if ("doSeterusnya".equals(hitButton)) {
				logic.updateStatus(idFail, idPermohonan, session);
			}	
			if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				updateUploadFile(idDokumen, session);
			}
        	if ("hapusDokumen".equals(hitButton)){
    			logicDokumen.hapusDokumen(idDokumen, session);
    			this.context.put("modeDokumen", "noDokumen");    			
    		}     
			if ("simpanDokumen".equals(hitButton)) {
    			uploadFiles(idPenawaranKJP, idPermohonan, session);
    			
			}
			if ("doHantarProses".equals(hitButton)) {
				Hashtable hUser = getIUser().getPengguna(userId);
				idKementerian =  String.valueOf(hUser.get("idKementerian"));
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
				namaPemohon = hashRayuanDB.get("namaPemohon").toString();
				
				idFail = (String) hashHeader.get("idFail");
				noFail = (String) hashHeader.get("noFail");
				idPermohonan = (String) hashHeader.get("idPermohonan");
				idStatus = (String) hashHeader.get("idStatus");
				this.context.put("idKementerian", idKementerian);
				
				EmailConfig ec = new EmailConfig();

				
				String emelSubjek = ec.tajukSemakanInt + "Penerima Tawaran";
				String kandungan = "";
				
				//kandungan = getEmelSemak().setEmailSign(String.valueOf(hashHeader.get("noFail")));
					
				kandungan = getEmelSemak().setEmailSign(String.valueOf(hashHeader.get("noFail")),
						String.valueOf(hashHeader.get("tajukFail")), String.valueOf(hashRayuanDB.get("namaPemohon")));

				if (!getEmelSemak().checkEmail(userId).equals(""))
					getIHTP().getErrorHTML("[ONLINE-PHP PELEPASAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
				
				ec.sendByRole(getEmelSemak().checkEmail(userId), "(PHP)UserPelepasan",
						String.valueOf(String.valueOf(hashHeader.get("idNegeriPemohon"))), emelSubjek, kandungan);
				

			}
		}
			
 
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String)hashHeader.get("idFail");
			idPermohonan = (String)hashHeader.get("idPermohonan");
			idStatus = (String)hashHeader.get("idStatus");
			
			idAgensiDef = (String)hashHeader.get("idAgensi");
			cadanganKegunaan =  (String)hashHeader.get("cadanganKegunaan");
			noRujukanSurat = (String)hashHeader.get("noRujukanSurat");
			noFail = (String)hashHeader.get("noFail");
		}
		// Baca senarai fail
		// DROP DOWN CARIAN
		String idJenisHakmilikC = getParam("socJenisHakmilikC");
		if (idJenisHakmilikC == null || idJenisHakmilikC.trim().length() == 0) {
			idJenisHakmilikC = "99999";
		}
		String idLotC = getParam("socLotC");
		if (idLotC == null || idLotC.trim().length() == 0) {
			idLotC = "99999";
		}
		String idNegeriC = getParam("socNegeriC");
		if (idNegeriC == null || idNegeriC.trim().length() == 0) {
			idNegeriC = "99999";
		}
		String idDaerahC = getParam("socDaerahC");
		if (idDaerahC == null || idDaerahC.trim().length() == 0) {
			idDaerahC = "99999";
		}
		String idMukimC = getParam("socMukimC");
		if (idMukimC == null || idMukimC.trim().length() == 0) {
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

		String idStatusC = getParam("socStatusC");
		if (idStatusC == null || idStatusC.trim().length() == 0) {
			idStatusC = "99999";
		}
		String flagDetail = getParam("flagDetail");

		// GO TO LIST FAIL PENAWARAN
		
		logic.senaraiFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txtPemohon"),
				getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
				idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
				getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
				getParam("txtNoPegangan"), idStatusC, idKementerian, idAgensiC, getParam("checkTanah"));
		
		list = new Vector();
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);

		this.context.put("txtNoFail", getParam("txtNoFail"));
		this.context.put("txtTajukFail", getParam("txtTajukFail"));
		this.context.put("txtPemohon", getParam("txtPemohon"));
		this.context.put("txtNoRujukanKJP", getParam("txtNoRujukanKJP"));
		this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		
		this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilikC", Long.parseLong(idJenisHakmilikC), ""));
		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		this.context.put("txtNoWarta", getParam("txtNoWarta"));
		this.context.put("selectLot", HTML.SelectLot("socLotC",Long.parseLong(idLotC), ""));
		this.context.put("txtNoLot", getParam("txtNoLot"));
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
		this.context.put("selectStatus", HTML.SelectStatusPenawaran("socStatusC", Long.parseLong(idStatusC), "", ""));

		this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeNegeri();\""));
		this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerian,  Long.parseLong(idAgensiC), "", " onChange=\"doChangeNegeri();\""));
		this.context.put("checkTanah", getParam("checkTanah"));
		this.context.put("flagTanah", getParam("checkTanah"));
		this.context.put("flagDetail", flagDetail);
		setupPage(session, action, list);
	
		vm = "app/php2/online/ulasanKJP/pnw/senaraiFailTawaran.jsp";
 	
		//SENARAI AGENSI
		senaraiAgensi = new Vector();
		myLogger.info("idKementerian===="+idKementerian);
		myLogger.info("idAgensi===="+idAgensi);
		logic.setSenaraiAgensi(idPermohonan, idKementerian, idAgensi);
		senaraiAgensi = logic.getListAgensi();
		this.context.put("SenaraiAgensi", senaraiAgensi);
		this.context.put("bilSenaraiAgensi", senaraiAgensi.size());
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/online/ulasanKJP/pnw/frmBatalPermohonan.jsp";
        }
		
		// SET DEFAULT PARAM
		this.context.put("actionPenawaran", actionPenawaran);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("mode", mode);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		this.context.put("idStatus", idStatus);
		this.context.put("idPenawaranKJP", idPenawaranKJP);
		
		this.context.put("step",step);
		
		if ("carian".equals(flagPopup)) {
			
			
			// GO TO LIST FAIL PENAWARAN
			
			logic.carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txtPemohon"),
					getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
					idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
					getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
					getParam("txtNoPegangan"), idStatusC, idKementerian, idAgensi, getParam("checkTanah"));
			
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtNoRujukanKJP", getParam("txtNoRujukanKJP"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilikC", Long.parseLong(idJenisHakmilikC), ""));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("selectLot", HTML.SelectLot("socLotC",Long.parseLong(idLotC), ""));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
			this.context.put("selectStatus", HTML.SelectStatusPenawaran("socStatusC", Long.parseLong(idStatusC), "", ""));

			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerian,  Long.parseLong(idAgensiC), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("checkTanah", getParam("checkTanah"));
			this.context.put("flagTanah", getParam("checkTanah"));
			this.context.put("flagPopup", flagPopup);
			setupPage(session, action, list);
		
			vm = "app/php2/online/ulasanKJP/pnw/senaraiFailTawaran.jsp";
		}

		else if ("paparFail".equals(flagPopup)) {
			vm = "app/php2/online/ulasanKJP/pnw/frmPNWTawaranKJP.jsp";
			
			//HEADER
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail, session);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
			
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				idFail = (String)hashHeader.get("idFail");
				idPermohonan = (String)hashHeader.get("idPermohonan");
				idStatus = (String)hashHeader.get("idStatus");
				
				idAgensiDef = (String)hashHeader.get("idAgensi");
				cadanganKegunaan =  (String)hashHeader.get("cadanganKegunaan");
				noRujukanSurat = (String)hashHeader.get("noRujukanSurat");
				noFail = (String)hashHeader.get("noFail");  
			}
			
		} 		//OPEN POPUP AGENSI
		else if ("openPopupAgensi".equals(flagPopup)){
	    		vm = "app/php2/online/ulasanKJP/pnw/frmPNWTawaranKJP.jsp"; 
	    		if ("new".equals(modePopup)){
	    			
	    			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("readonlyPopupMinit", "");
	    			this.context.put("inputTextClassPopupMinit", "");
	    			
	    			//disable kementerian
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerian), "", readonly+" style=\"width:400\" "));	    			
	    			
	    			if ("".equals(flagReKeyin)){
	    				
	    				beanMaklumatAgensi = new Vector();    			
		    			Hashtable hashMaklumatAgensi = new Hashtable();
		    			hashMaklumatAgensi.put("tarikhTerima", getParam("txtTarikhTerima"));
		    			hashMaklumatAgensi.put("noRujukanSuratKJP", noRujukanKJP);
		    			hashMaklumatAgensi.put("tujuanKegunaan", cadanganKegunaan);
		    			beanMaklumatAgensi.addElement(hashMaklumatAgensi);
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
						this.context.put("txtNoRujukanKJP", noRujukanKJP);	
						
//						idAgensi = idAgensiDef;
						
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", readonly+" style=\"width:400\" "));
						
	    			} 
	    			
	    			if(submit.equals("doChangeKementerian")) {
	    				
	    				beanMaklumatAgensi = new Vector();    			
		    			Hashtable hashMaklumatAgensi = new Hashtable();
		    			hashMaklumatAgensi.put("tarikhTerima", txtTarikhTerima);
		    			hashMaklumatAgensi.put("noRujukanSuratKJP", noRujukanKJP);
		    			hashMaklumatAgensi.put("tujuanKegunaan", "");
		    			beanMaklumatAgensi.addElement(hashMaklumatAgensi);
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);	
						this.context.put("txtTarikhTerima", txtTarikhTerima);		
						this.context.put("txtNoRujukanKJP", noRujukanKJP);		
						
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "",""));
						
	    			}		    			
	    			
	    			setMaklumatPelan(beanMaklumatImejan, "");
	    			this.context.put("modeDokumenItem", "addNewDokumen");
					


	    			
	    		} else if ("update".equals(modePopup)){
	    			
	    			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("readonlyPopupMinit", "readonly");
	    			this.context.put("inputTextClassPopupMinit", "disabled");
	    			
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "",readonly+" style=\"width:400\" "));
	    			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "",readonly+" style=\"width:400\" "));
	    			
	    			setMaklumatPelan(beanMaklumatImejan, idPenawaranKJP);
	    			this.context.put("modeDokumenItem", "viewable");
	    			
	    		} else if ("view".equals(modePopup)){
	    			
	    			this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("readonlyPopupMinit", "readonly");
	    			this.context.put("inputTextClassPopupMinit", "disabled");
	    			
	    			//MAKLUMAT LAPORAN TANAH
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idPenawaranKJP);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
					if (beanMaklumatAgensi.size() != 0){
						Hashtable hashMaklumatAgensi = (Hashtable) logic.getBeanMaklumatAgensi().get(0);
						
						idKementerian = (String) hashMaklumatAgensi.get("idKementerian");
						idAgensi = (String) hashMaklumatAgensi.get("idAgensi");
					}
	    			
					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					
	    			setMaklumatPelan(beanMaklumatImejan, idPenawaranKJP);
	    			this.context.put("modeDokumenItem", "viewable");
	    		
	    		}  else if ("updateMinit".equals(modePopup)){
				
	    			this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("readonlyPopupMinit", "");
	    			this.context.put("inputTextClassPopupMinit", "");
	    			
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idPenawaranKJP);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
		   			
					if (beanMaklumatAgensi.size() != 0){
						Hashtable hashMaklumatAgensi = (Hashtable) logic.getBeanMaklumatAgensi().get(0);
						
						idKementerian = (String) hashMaklumatAgensi.get("idKementerian");
						idAgensi = (String) hashMaklumatAgensi.get("idAgensi");
					}
	    			
					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
	    			
	    			this.context.put("modeDokumenItem", "updatable");
	    			
	    		} else if ("newMinit".equals(modePopup)){
					
	    			this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("readonlyPopupMinit", "");
	    			this.context.put("inputTextClassPopupMinit", "");
	    			
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idPenawaranKJP);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
		   			
					if (beanMaklumatAgensi.size() != 0){
						Hashtable hashMaklumatAgensi = (Hashtable) logic.getBeanMaklumatAgensi().get(0);
						
						idKementerian = (String) hashMaklumatAgensi.get("idKementerian");
						idAgensi = (String) hashMaklumatAgensi.get("idAgensi");
					}
	    			
					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
	    			
	    			setMaklumatPelan(beanMaklumatImejan, "");
	    			this.context.put("modeDokumenItem", "addNewDokumen");
	    			
	    		} 
	    		
	    	}
		
				// SET DEFAULT PARAM
				this.context.put("actionPenawaran", actionPenawaran);

				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);
				this.context.put("idHakmilikAgensi", idHakmilikAgensi);
				this.context.put("idHakmilikUrusan", idHakmilikUrusan);
				this.context.put("idHakmilikSementara", idHakmilikSementara);
				this.context.put("namaJenisTanah", namaJenisTanah);
				this.context.put("idJenisTanah", idJenisTanah);
				this.context.put("idPermohonan", idPermohonan);
				this.context.put("idPenawaranKJP", idPenawaranKJP);
		
		return vm;
    	
	}

	
	
	private void setMaklumatPelan(Vector beanMaklumatImejan, String idPenawaranKJP) throws Exception{
		beanMaklumatImejan = new Vector();
		if(idPenawaranKJP.isEmpty()){
			Hashtable hashMaklumatImejan = new Hashtable();
			hashMaklumatImejan.put("namaImej", "");
			hashMaklumatImejan.put("catatanImej", "");
			beanMaklumatImejan.addElement(hashMaklumatImejan);
			
			this.context.put("modeDokumen", "addDokumen");   
		} else {
			logic.setMaklumatImej("ID_PENAWARANKJP", idPenawaranKJP);
			beanMaklumatImejan = logic.getBeanMaklumatImejan();			
			
			if(beanMaklumatImejan.size()>0)
    			this.context.put("modeDokumen", "addDokumen");    	
			else 
    			this.context.put("modeDokumen", "noDokumen");    
		}
		this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idPenawaranKJP, String idPermohonan, HttpSession session) throws Exception {
		myLogger.info("Baca uploadFiles===1");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		myLogger.info("Baca uploadFiles===2");
		if (isMultipart != false) {
			myLogger.info("Baca uploadFiles===3");
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				myLogger.info("Baca uploadFiles===4");
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					myLogger.info("Baca uploadFiles===5");
					saveData(item, idPermohonan, getParam("txtNamaImej"), getParam("txtCatatanImej"), 
							"P", "ID_PENAWARANKJP", idPenawaranKJP, session);
					this.context.put("completed", true);
					myLogger.info("Baca uploadFiles===6");
				}
			}
		}
	}
	
	
	public void saveData(FileItem item, String idPermohonan, String namaImej, String catatanImej, 
			String flagDokumen, String column, String data, HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN,"+column+") "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, namaImej);
			ps.setString(3, catatanImej);
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, flagDokumen);
			ps.setString(9, idPermohonan);
			ps.setString(10, data);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610210", "4", null, session, "INS",
					"FAIL PENAWARAN [" + idDokumen
							+ "] DIDAFTARKAN");
			myLogger.info("Baca saveData===");
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	
	private void updateUploadFile(String idDokumen, HttpSession session) throws Exception  {
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
					updateDokumen(idDokumen, item, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
					this.context.put("completed", true);
				}
			}
		}
	}
	public void updateDokumen(String idDokumen, FileItem item, String namaImej, String catatanImej, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {			
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("UPDATE TBLPHPDOKUMEN SET NAMA_DOKUMEN=?, CATATAN=?, CONTENT=?, JENIS_MIME=?, NAMA_FAIL=?, ID_KEMASKINI=?, TARIKH_KEMASKINI=SYSDATE WHERE ID_DOKUMEN=?");

			ps.setString(1, namaImej);
			ps.setString(2, catatanImej);
			ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
			ps.setString(4, item.getContentType());
			ps.setString(5, item.getName());
			ps.setString(6, userId);
			ps.setString(7, idDokumen);

			 ps.executeUpdate();
			 conn.commit();
			 
			 AuditTrail.logActivity("1610210", "4", null, session, "UPD",
						"FAIL PENAWARAN [" + idDokumen
								+ "] DIKEMASKINI");
			 
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}	
	
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelJRPBean();
		return emelSemak;
	}
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserKJPBean();
		}
		return iUser;
			
	}
	
}
