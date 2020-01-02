package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBMaklumatKelulusanHidrografiData;


public class FrmAPBMaklumatKelulusanHidrografi extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBMaklumatKelulusanHidrografiData logic = new FrmAPBMaklumatKelulusanHidrografiData();

	
	//@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String vm = ""; 
        String hitButton = getParam("hitButton");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }		
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idDokumen = getParam("idDokumen");
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		this.context.put("completed",false);
		
		String idNegeriJAS = getParam("socJAS");
		if (idNegeriJAS == null || idNegeriJAS.trim().length() == 0) {
			idNegeriJAS = "99999";
		}
		String idTempohJAS = getParam("socTempoh");
		if (idTempohJAS == null || idTempohJAS.trim().length() == 0) {
			idTempohJAS = "99999";
		}		
        //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatJAS=null;
		Vector beanMaklumatHIDRO = null;
		Vector beanMaklumatDokumen = null;
		Vector senaraiDokumenHidro = null;
		Vector senaraiDokumenJAS = null;
		
		vm = "app/php2/frmAPBMaklumatKelulusanEIADanHidrografi.jsp"; 
		
		if (postDB) {
			if ("doSimpanKemaskiniHidro".equals(hitButton)){
				logic.updateMaklumatHidro(idPermohonan,getParam("txtNoRujHidro"),getParam("txtTarikhSuratLulusHIDRO") ,getParam("txtTarikhTerimaHIDRO"),
						getParam("socKeputusanHIDRO"),getParam("txtTarikhLulusHIDRO"),session);
			}
			if ("doSimpanKemaskiniJAS".equals(hitButton)){
				logic.updateMaklumatJAS(idPermohonan,getParam("txtNoRujJAS"),getParam("txtTarikhSuratLulusJAS") ,getParam("txtTarikhTerimaJAS"),
						getParam("socKeputusanJAS"),getParam("txtTempohLulusJAS"),getParam("txtTarikhLulusJAS"),idNegeriJAS,idTempohJAS,session);
			}
			if ("doSeterusnya".equals(hitButton)){
				logic.updateStatus(idFail, idPermohonan, session);            		
	    	}			
    	}//END POSTDB 
    	
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idPemohon = hashHeader.get("idPemohon").toString();
			idStatus = hashHeader.get("idStatus").toString();	
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 9); // 9 = MAKLUMAT KELULUSAN EIA DAN HIDROGRAFI
		this.context.put("flagOpenDetail", flagOpenDetail);
	
		// MODE VIEW
		if("view".equals(mode)){			
      	
        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
        	
	    	//MAKLUMAT KELULUSAN HIDROGRAFI
			beanMaklumatHIDRO = new Vector();
	    	logic.setMaklumatHIDRO(idPermohonan);
	    	beanMaklumatHIDRO = logic.getBeanMaklumatHIDRO();
	       	this.context.put("BeanMaklumatHIDRO", beanMaklumatHIDRO);       
	       	
	    	//MAKLUMAT KELULUSAN EIA/JAS
			beanMaklumatJAS = new Vector();
	    	logic.setMaklumatJAS(idPermohonan);
	    	beanMaklumatJAS = logic.getBeanMaklumatJAS();
	       	this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
        	if (beanMaklumatJAS.size() != 0){
				Hashtable hashMaklumatJAS = (Hashtable) logic.getBeanMaklumatJAS().get(0);
				this.context.put("selectNegeri", HTML.SelectNegeri("socJAS", Long.parseLong((String) hashMaklumatJAS.get("socJAS")), "disabled", " style=\"width:300px\" class=\"disabled\""));
				this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh",Long.parseLong((String) hashMaklumatJAS.get("socTempoh")), "disabled", " style=\"width:100px\" class=\"disabled\""));
        	}
		// MODE UPDATE
		} else if("update".equals(mode)){
			
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");			
			
        	//MAKLUMAT KELULUSAN HIDROGRAFI
    		beanMaklumatHIDRO = new Vector();
        	logic.setMaklumatHIDRO(idPermohonan);
        	beanMaklumatHIDRO = logic.getBeanMaklumatHIDRO();
           	this.context.put("BeanMaklumatHIDRO", beanMaklumatHIDRO);   
        	
       		
        	//MAKLUMAT KELULUSAN EIA/JAS
    		beanMaklumatJAS = new Vector();
        	logic.setMaklumatJAS(idPermohonan);
        	beanMaklumatJAS = logic.getBeanMaklumatJAS();
           	this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
        	if (beanMaklumatJAS.size() != 0){
				Hashtable hashMaklumatJAS = (Hashtable) logic.getBeanMaklumatJAS().get(0);
				this.context.put("selectNegeri", HTML.SelectNegeri("socJAS", Long.parseLong((String) hashMaklumatJAS.get("socJAS")), "", " style=\"width:300px\""));
				this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong((String) hashMaklumatJAS.get("socTempoh")), "", " style=\"width:100px\""));
			}
		}		

		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("idSkrin", "4");	
		
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idStatus", idStatus);
	    this.context.put("idDokumen", idDokumen);
	        
		return vm;
	}		
}

