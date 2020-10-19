/**
 * 
 */
package ekptg.view.php2.online;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPHPDokumenData;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWTawaranKJPData;
import ekptg.model.php2.online.FrmPLPOnlineKJPSenaraiFailData;

public class FrmPNWTawaranKJPView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWTawaranKJPData logic = new FrmPNWTawaranKJPData();
	FrmPHPDokumenData logicDokumen = new FrmPHPDokumenData();
	
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
		String noRujukan = getParam("txtNoRujukan");
		String txtTarikhTerima = getParam("txtTarikhTerima");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		String idJenisTanah = "1";
		
		String idKategoriPemohon = "";

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiAgensi = null;
        Vector beanMaklumatAgensi = null;
        Vector beanMaklumatImejan = null;               
        Vector list = null;
        
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
		
		
		
		//SUBMIT TO NEXT PROCESS
		if (postDB) {
			if ("simpanAgensi".equals(hitButton)) {
				idPenawaranKJP = logic.simpanAgensi(idPermohonan, getParam("txtNoRujukan"), getParam("txtTarikhTerima"), idKementerian, idAgensi, getParam("txtTujuanKegunaan"), session);
				flagReKeyin = "Y";
				idKementerian = "99999";
				idAgensi = "99999";
    			uploadFiles(idPenawaranKJP, idPermohonan, session);
			}
			if ("simpanKemaskiniAgensi".equals(hitButton)) {
				logic.simpanKemaskiniAgensi(idPenawaranKJP, getParam("txtNoRujukan"), getParam("txtTarikhTerima"), idKementerian, idAgensi, getParam("txtTujuanKegunaan"), session);
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
		
		//vm = "app/php2/online/ulasanKJP/pnw/frmPNWTawaranKJP.jsp";
		//vm = "app/php2/online/ulasanKJP/pnw/senaraiFailTawaran.jsp";

		logic.carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txtPemohon"),
				getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
				idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
				getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
				getParam("txtNoPegangan"), idStatusC, idKementerianC, idAgensiC, getParam("checkTanah"));
		
		list = new Vector();
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);

		this.context.put("txtNoFail", getParam("txtNoFail"));
		this.context.put("txtTajukFail", getParam("txtTajukFail"));
		this.context.put("txtPemohon", getParam("txtPemohon"));
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
		this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
		this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerianC,  Long.parseLong(idAgensiC), "", ""));
		this.context.put("checkTanah", getParam("checkTanah"));
		this.context.put("flagTanah", getParam("checkTanah"));
		this.context.put("flagDetail", flagDetail);
		setupPage(session, action, list);
	
		vm = "app/php2/online/ulasanKJP/pnw/senaraiFailTawaran.jsp";
 	
		//SENARAI AGENSI
		senaraiAgensi = new Vector();
		logic.setSenaraiAgensi(idPermohonan);
		senaraiAgensi = logic.getListAgensi();
		this.context.put("SenaraiAgensi", senaraiAgensi);
		
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
		this.context.put("idStatus", idStatus);
		this.context.put("idPenawaranKJP", idPenawaranKJP);
		
		this.context.put("step",step);
		

		if ("paparFail".equals(actionPenawaran)) {
			myLogger.info("BACA paparFail=========="+idFail);
			vm = "app/php2/online/ulasanKJP/pnw/frmPNWTawaranKJP.jsp";
			
			//HEADER
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail, session);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
			
			if (beanHeader.size() != 0){
				myLogger.info("BACA kat sini1");
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
	    			
					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), ""," onChange=\"doChangeKementerian();\""));
	    			
	    			if ("".equals(flagReKeyin)){
	    				
	    				beanMaklumatAgensi = new Vector();    			
		    			Hashtable hashMaklumatAgensi = new Hashtable();
		    			hashMaklumatAgensi.put("tarikhTerima", getParam("txtTarikhTerima"));
		    			hashMaklumatAgensi.put("noRujukan", noFail);
		    			hashMaklumatAgensi.put("tujuanKegunaan", cadanganKegunaan);
		    			beanMaklumatAgensi.addElement(hashMaklumatAgensi);
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
						this.context.put("noRujukan", noFail);
						
//						idAgensi = idAgensiDef;
						
						this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi", Long.parseLong(idAgensi), "",""));
						
	    			} 
	    			
	    			if(submit.equals("doChangeKementerian")) {
	    				
	    				beanMaklumatAgensi = new Vector();    			
		    			Hashtable hashMaklumatAgensi = new Hashtable();
		    			hashMaklumatAgensi.put("tarikhTerima", txtTarikhTerima);
		    			hashMaklumatAgensi.put("noRujukan", noRujukan);
		    			hashMaklumatAgensi.put("tujuanKegunaan", "");
		    			beanMaklumatAgensi.addElement(hashMaklumatAgensi);
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);	
						this.context.put("txtTarikhTerima", txtTarikhTerima);					
						
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "",""));
						
	    			}		    			
	    			
	    			setMaklumatPelan(beanMaklumatImejan, "");
	    			this.context.put("modeDokumenItem", "addNewDokumen");
					


	    			
	    		} else if ("update".equals(modePopup)){
	    			
	    			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("readonlyPopupMinit", "readonly");
	    			this.context.put("inputTextClassPopupMinit", "disabled");
	    			
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), ""," onChange=\"doChangeKementerian();\""));
	    			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "",""));
	    			
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
			logicDokumen.setMaklumatImej("ID_PENAWARANKJP", idPenawaranKJP);
			beanMaklumatImejan = logicDokumen.getBeanMaklumatImejan();			
			
			if(beanMaklumatImejan.size()>0)
    			this.context.put("modeDokumen", "addDokumen");    	
			else 
    			this.context.put("modeDokumen", "noDokumen");    
		}
		this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idPenawaranKJP, String idPermohonan, HttpSession session) throws Exception {
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
					logicDokumen.saveData(item, idPermohonan, getParam("txtNamaImej"), getParam("txtCatatanImej"), "P", "ID_PENAWARANKJP", idPenawaranKJP, session);
					this.context.put("completed", true);
				}
			}
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
					logicDokumen.updateDokumen(idDokumen, item, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
					this.context.put("completed", true);
				}
			}
		}
	}
	
}
