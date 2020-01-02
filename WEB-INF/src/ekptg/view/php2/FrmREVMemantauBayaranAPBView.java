/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.Util;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmREVMemantauBayaranAPBData;

/**
 * 
 *
 */
public class FrmREVMemantauBayaranAPBView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmREVMemantauBayaranAPBData logic = new FrmREVMemantauBayaranAPBData();
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
        
        //GET ID PARAM
		String idFail = getParam("idFail");
        String idJadualPertama = getParam("idJadualPertama");
        String idAkaun = getParam("idAkaun");               
        
		String idModBayaran = getParam("socModBayaran");
		if (idModBayaran == null || idModBayaran.trim().length() == 0){
			idModBayaran = "99999";
		}		
        String idCaraBayaran = getParam("socCaraBayaran");
		if (idCaraBayaran == null || idCaraBayaran.trim().length() == 0){
			idCaraBayaran = "99999";
		}
		String idBank = getParam("socBank");
		if (idBank == null || idBank.trim().length() == 0){
			idBank = "99999";
		}
		String idJenisBayaran = getParam("socJenisBayaran");
		if (idJenisBayaran == null || idJenisBayaran.trim().length() == 0){
			idJenisBayaran = "99999";
		} 
		String idStatusDeposit = getParam("socStatusDeposit");
		if (idStatusDeposit == null || idStatusDeposit.trim().length() == 0){
			idStatusDeposit = "Y";
		}
		
        //VECTOR
        Vector list = null;
        Vector beanMaklumatBayaranD = null;
        Vector beanMaklumatBayaranFiPermohonan = null;
        Vector beanMaklumatBayaranFiLesen = null;
        Vector beanMaklumatBayaranRoyalti = null;        
        
        if (postDB){        	
    		if ("simpanBayaranD".equals(hitButton)){
         		idAkaun = logic.simpanBayaranD(idJadualPertama, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"),idStatusDeposit, getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranD".equals(hitButton)){
    			logic.simpanKemaskiniBayaranD(idJadualPertama, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), idStatusDeposit, getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("simpanBayaranFiPermohonan".equals(hitButton)){
         		idAkaun = logic.simpanBayaranFiPermohonan(idJadualPertama, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranFiPermohonan".equals(hitButton)){
    			logic.simpanKemaskiniBayaranFiPermohonan(idJadualPertama, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("simpanBayaranFiLesen".equals(hitButton)){
         		idAkaun = logic.simpanBayaranFiLesen(idJadualPertama, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranFiLesen".equals(hitButton)){
    			logic.simpanKemaskiniBayaranFiLesen(idJadualPertama, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("simpanBayaranRoyalti".equals(hitButton)){
         		idAkaun = logic.simpanBayaranRoyalti(idJadualPertama, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
        	}
    		if ("simpanKemaskiniBayaranRoyalti".equals(hitButton)){
    			logic.simpanKemaskiniBayaranRoyalti(idJadualPertama, idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), getParam("txtTarikhCek"), 
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), getParam("txtNoMel"), idModBayaran, session);
    		}
    		if ("hapusBayaran".equals(hitButton)){
         		logic.hapusBayaran(idAkaun, session);
        	}     		
    	}
        
        if ("papar".equals(actionHasil)){
        	
        	vm = "app/php2/frmREVMaklumatBayaranAPB.jsp";
        	
        	//HEADER
        	header(idFail, session);
        	
        	//MAKLUMAT DEPOSIT PASIR
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
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
                	
        		} else if ("viewBayaranD".equals(mode)){
        			
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
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
        		}
        		senaraiDeposit(idJadualPertama, action, session);
        	}
        	
        	//MAKLUMAT FI PERMOHONAN
        	if ("1".equals(selectedTabUpper)){
        		
        		if ("newBayaranFiPermohonan".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiPermohonan = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranFiPermohonan.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranFiPermohonan", beanMaklumatBayaranFiPermohonan);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", " onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
                	
        		} else if ("viewBayaranFiPermohonan".equals(mode)){
        			
                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");
                	
                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiPermohonan = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaranFiPermohonan = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaranFiPermohonan", beanMaklumatBayaranFiPermohonan);
                	
            		if (beanMaklumatBayaranFiPermohonan.size() != 0){
            			Hashtable hashBayaranFiPermohonanB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranFiPermohonanB.get("idCaraBayar");
            			idBank = (String) hashBayaranFiPermohonanB.get("idBank");
            			idStatusDeposit = (String) hashBayaranFiPermohonanB.get("flagDeposit");
            			idModBayaran = (String) hashBayaranFiPermohonanB.get("modBayaran");
            			
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
            		}
        			
        		} else if ("updateBayaranFiPermohonan".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiPermohonan = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranFiPermohonan.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranFiPermohonan", beanMaklumatBayaranFiPermohonan);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
        		}
        		senaraiFiPermohonan(idJadualPertama, action, session);
        	}
        	
        	//MAKLUMAT FI LESEN
        	if ("2".equals(selectedTabUpper)){
        		
        		if ("newBayaranFiLesen".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiLesen = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranFiLesen.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranFiLesen", beanMaklumatBayaranFiLesen);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", " onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
                	
        		} else if ("viewBayaranFiLesen".equals(mode)){
        			
                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");
                	
                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiLesen = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaranFiLesen = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaranFiLesen", beanMaklumatBayaranFiLesen);
                	
            		if (beanMaklumatBayaranFiLesen.size() != 0){
            			Hashtable hashBayaranFiLesenB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranFiLesenB.get("idCaraBayar");
            			idBank = (String) hashBayaranFiLesenB.get("idBank");
            			idStatusDeposit = (String) hashBayaranFiLesenB.get("flagDeposit");
            			idModBayaran = (String) hashBayaranFiLesenB.get("modBayaran");
            			
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
            		}
        			
        		} else if ("updateBayaranFiLesen".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranFiLesen = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranFiLesen.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranFiLesen", beanMaklumatBayaranFiLesen);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
        		}
        		senaraiFiLesen(idJadualPertama, action, session);
        	}
        	
        	//MAKLUMAT ROYALTI
        	if ("3".equals(selectedTabUpper)){
        		
        		if ("newBayaranRoyalti".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranRoyalti = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranRoyalti.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranRoyalti", beanMaklumatBayaranRoyalti);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", " onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
                	this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
                	
        		} else if ("viewBayaranRoyalti".equals(mode)){
        			
                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");
                	
                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranRoyalti = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaranRoyalti = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaranRoyalti", beanMaklumatBayaranRoyalti);
                	
            		if (beanMaklumatBayaranRoyalti.size() != 0){
            			Hashtable hashBayaranRoyaltiB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranRoyaltiB.get("idCaraBayar");
            			idBank = (String) hashBayaranRoyaltiB.get("idBank");
            			idStatusDeposit = (String) hashBayaranRoyaltiB.get("flagDeposit");
            			idModBayaran = (String) hashBayaranRoyaltiB.get("modBayaran");
            			
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            			this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "disabled", " class=\"disabled\""));
            		}
        			
        		} else if ("updateBayaranRoyalti".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaranRoyalti = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("tarikhCek", getParam("txtTarikhCek"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			hashBayaran.put("noMel", getParam("txtNoMel"));
        			beanMaklumatBayaranRoyalti.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaranRoyalti", beanMaklumatBayaranRoyalti);
                	
        			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));
            		this.context.put("selectModBayaran",HTML.SelectModBayaran("socModBayaran", idModBayaran, "", ""));
        		}
        		senaraiRoyalti(idJadualPertama, action, session);
        	}
        	
        } else {
        	
        	vm = "app/php2/frmREVSenaraiFailAPB.jsp";
        	
        	// DROP DOWN CARIAN
    		String idBankC = getParam("socBankC");
    		if (idBankC == null || idBankC.trim().length() == 0){
    			idBankC = "99999";
    		}
    		
    		list = logic.getSenaraiFail(getParam("txtNoFail"), getParam("txtNamaPemohon"), getParam("txtNoPengenalan"), getParam("txtNoLesen"), idBankC, getParam("txtNoCek"), getParam("txtNoResit"));
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNamaPemohon", getParam("txtNamaPemohon"));
			this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
			this.context.put("txtNoLesen", getParam("txtNoLesen"));	
			this.context.put("selectBankC", HTML.selectBank("socBankC", Long.parseLong(idBankC), "", ""));		
			this.context.put("txtNoCek", getParam("txtNoCek"));
			this.context.put("txtNoResit", getParam("txtNoResit"));
						
			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionHasil", actionHasil);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("mode", mode);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idJadualPertama", idJadualPertama);
		this.context.put("idAkaun", idAkaun);
		this.context.put("idCaraBayaran", idCaraBayaran);
        
		return vm;
	}
	
	private void senaraiDeposit(String idJadualPertama, String action, HttpSession session) throws Exception {
		Vector senaraiDeposit = new Vector();
		senaraiDeposit = logic.getSenaraiDeposit(idJadualPertama);
		this.context.put("SenaraiDeposit", senaraiDeposit);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalDeposit(idJadualPertama);
		
		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("total", "(" + Util.formatDecimal(total * -1) + ")");
		} else {
			this.context.put("total", "0.00");
		}		
	}
	
	private void senaraiFiPermohonan(String idJadualPertama, String action, HttpSession session) throws Exception {
		Vector senaraiFiPermohonan = new Vector();
		senaraiFiPermohonan = logic.getSenaraiFiPermohonan(idJadualPertama);
		this.context.put("SenaraiFiPermohonan", senaraiFiPermohonan);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalFiPermohonan(idJadualPertama);
		
		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("total", "(" + Util.formatDecimal(total * -1) + ")");
		} else {
			this.context.put("total", "0.00");
		}		
	}
	
	private void senaraiFiLesen(String idJadualPertama, String action, HttpSession session) throws Exception {
		Vector senaraiFiLesen = new Vector();
		senaraiFiLesen = logic.getSenaraiFiLesen(idJadualPertama);
		this.context.put("SenaraiFiLesen", senaraiFiLesen);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalFiLesen(idJadualPertama);
		
		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("total", Util.formatDecimal(total * -1));
		} else {
			this.context.put("total", "0.00");
		}		
	}
	
	private void senaraiRoyalti(String idJadualPertama, String action, HttpSession session) throws Exception {
		Vector senaraiRoyalti = new Vector();
		senaraiRoyalti = logic.getSenaraiRoyalti(idJadualPertama);
		this.context.put("SenaraiRoyalti", senaraiRoyalti);
		
		setupPageRoyalti(session, action, senaraiRoyalti);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotalRoyalti(idJadualPertama);
		
		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("total", Util.formatDecimal(total * -1));
		} else {
			this.context.put("total", "0.00");
		}		
	}

	private void header(String idFail, HttpSession session) throws Exception {
		
		Vector beanHeader = new Vector();
		beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
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
	
	public void setupPageRoyalti(HttpSession session,String action,Vector list) {
		
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
				this.context.put("SenaraiRoyalti",paging.getPage(page));
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
}
