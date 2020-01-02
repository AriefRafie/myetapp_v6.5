package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBPenyediaanLesenDanMemoKepadaMenteriData;


public class FrmAPBPenyediaanLesenDanMemoKepadaMenteri extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBPenyediaanLesenDanMemoKepadaMenteriData logic = new FrmAPBPenyediaanLesenDanMemoKepadaMenteriData();

	
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
        String idStatus = getParam("idStatus");
		this.context.put("completed",false);
		
		//GET DROPDOWN PARAM 
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0) {
			idLuas = "99999";
		}
		String idTempoh = getParam("socTempoh");
		if (idTempoh == null || idTempoh.trim().length() == 0){
			idTempoh = "99999";	
		} //AIN 10/1/2017
		
		//VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatPenyediaanLesenDanMemo = null;
		
		String step = getParam("step");
		
		this.context.put("onload", "");
		
		vm = "app/php2/frmAPBPenyediaanLesenDanMemoKepadaMenteri.jsp"; 
		
	    //SUBMIT TO NEXT PROCESS
		if (postDB) {    	
			if ("doSimpanKemaskiniPenyediaanLesenDanMemo".equals(hitButton)){
				logic.updateMaklumatPenyediaanLesenDanMemo(idPermohonan,getParam("txtTempohDiluluskan"),getParam("socTempoh"),session);
			}
			if ("doSeterusnya".equals(hitButton)){
				
		    	if(logic.checkMaklumatPenyediaanLesenDanMemoKepadaMenteri(idPermohonan)){
    				this.context.put("onload", " \"alert('Sila masukkan tempoh yang diluluskan.')\"");
    			} else {
    				logic.updateStatus(idFail, idPermohonan, session);
    			}
    		}
			if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
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
			idStatus = hashHeader.get("idStatus").toString();
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}

		// GET FLAG OPEN DETAIL
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 11); // 11 = PENYEDIAAN LESEN DAN MEMO KEPADA MENTERI
		this.context.put("flagOpenDetail", flagOpenDetail);
	    
		//MODE VIEW -AIN 11/1/2017-
	    if("view".equals(mode)){
	       	
	    	this.context.put("readonly", "readonly");
	 		this.context.put("inputTextClass", "disabled");
	 		this.context.put("disabled", "disabled"); 	
	 		
	 		logic.setMaklumatPenyediaanLesenDanMemo(idPermohonan);
	     	beanMaklumatPenyediaanLesenDanMemo = logic.getBeanMaklumatPenyediaanLesenDanMemo();
	     	this.context.put("BeanMaklumatPenyediaanLesenDanMemo", beanMaklumatPenyediaanLesenDanMemo);
	     	
	     	if (logic.getBeanMaklumatPenyediaanLesenDanMemo().size() != 0) {
	     		Hashtable hashMaklumatPenyediaanLesenMemo = (Hashtable) logic.getBeanMaklumatPenyediaanLesenDanMemo().get(0);
				idTempoh = (String) hashMaklumatPenyediaanLesenMemo.get("idTempoh");
	     	}
	 		
		    this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"disabled", " style=\"width:100px\" class=\"disabled\""));
	 	}
	    
	    //MODE UPDATE -AIN 11/1/2017-
	    else if("update".equals(mode)){	
	 		
	    	this.context.put("readonly", "");
	     	this.context.put("inputTextClass", "");
	     	this.context.put("disabled", "");
	     	
	     	this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"", " style=\"width:100px\""));
	     	
	     	//MAKLUMAT PENYEDIAAN LESEN DAN MEMO
			beanMaklumatPenyediaanLesenDanMemo = new Vector();
			logic.setMaklumatPenyediaanLesenDanMemo(idPermohonan);
		    beanMaklumatPenyediaanLesenDanMemo = logic.getBeanMaklumatPenyediaanLesenDanMemo();
		    this.context.put("BeanMaklumatPenyediaanLesenDanMemo", beanMaklumatPenyediaanLesenDanMemo);      
	 	}
	    
	    if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan);       
	    this.context.put("idStatus", idStatus);      
		
		return vm;
	}	
}

