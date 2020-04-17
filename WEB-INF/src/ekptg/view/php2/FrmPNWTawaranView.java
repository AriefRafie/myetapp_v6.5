/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPHPDokumenData;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWTawaranData;

public class FrmPNWTawaranView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWTawaranData logic = new FrmPNWTawaranData();
	FrmPHPDokumenData logicDokumen = new FrmPHPDokumenData();

	@Override 
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

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

		// VECTOR
		Vector beanHeader = null;
		Vector senaraiAgensi = null;
        Vector beanMaklumatAgensi = null;
        Vector beanMaklumatImejan = null;               
        
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
		
		vm = "app/php2/frmPNWTawaran.jsp"; 
		
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

		//OPEN POPUP AGENSI
    	if ("openPopupAgensi".equals(flagPopup)){
    		
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
					
//					idAgensi = idAgensiDef;
					
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
		
		//SENARAI AGENSI
		senaraiAgensi = new Vector();
		logic.setSenaraiAgensi(idPermohonan);
		senaraiAgensi = logic.getListAgensi();
		this.context.put("SenaraiAgensi", senaraiAgensi);
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
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
