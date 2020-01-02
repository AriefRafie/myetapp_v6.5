package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPKeputusanData;

public class FrmPLPBorang12ABView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPKeputusanData logic = new FrmPLPKeputusanData();
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String submit = getParam("command");  
	    String vm = ""; 
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
       
	    //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idKeputusan = getParam("idKeputusan");
        String flagGuna = getParam("flagGuna");
        String idPampasan = "";
        String pampasan = "";
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatKeputusan = null;
		Vector beanMaklumatTarikh = null;
		
		String step = getParam("step");
		
		//MAKLUMAT KEPUTUSAN
		beanMaklumatKeputusan = new Vector();
		logic.setMaklumatKeputusan(idPermohonan);
		beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
		
		if (logic.getBeanMaklumatKeputusan().size() != 0){
			Hashtable hashMaklumatKeputusan = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
			idPampasan = (String) hashMaklumatKeputusan.get("jenis");
			pampasan = (String) hashMaklumatKeputusan.get("pampasan");
			
		}
    	
		//SEND TO DB
		if (postDB) {
	    	if ("doHantarProses".equals(hitButton)){
	    		logic.updateStatus(idFail, idPermohonan, idKeputusan, idPampasan, pampasan, session);
	    	} 
	    	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doSimpan".equals(hitButton)){
			    logic.simpanMaklumatTarikh(idPermohonan, idKeputusan, getParam("txtTarikhHantar"), getParam("txtTarikhLulus"), session);
			}
		}
		
		beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
			idKeputusan = (String) hashPermohonan.get("idKeputusan");
			flagGuna = (String) hashPermohonan.get("flagGuna");

		}
		/*
		//MAKLUMAT TARIKH PTD
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			if ("".equals((String) hashHakmilik.get("noHakmilik"))){
				//statusRizab = "RIZAB";
			} else {
				//statusRizab = "MILIK";
			}			
		}*/
		
		vm = "app/php2/frmPLPBorang12AB.jsp";
                        
        if("view".equals(mode)){
			 
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			 
			//MAKLUMAT KEPUTUSAN
			beanMaklumatTarikh = new Vector();
			logic.setMaklumatTarikh(idPermohonan);
			beanMaklumatTarikh = logic.getBeanMaklumatTarikh();
	    	this.context.put("BeanMaklumatTarikh", beanMaklumatTarikh);
	    	
	    	
	      		
		 } else if("update".equals(mode)){ 
			 	
			 System.out.println("cek" + getParam("txtCatatan"));
        	 this.context.put("readonly", "");
	    	 this.context.put("inputTextClass", "");
	    	 this.context.put("disabled", "");
	    
	    	//MAKLUMAT KEPUTUSAN
	    	beanMaklumatTarikh = new Vector();
	    	logic.setMaklumatKeputusan(idPermohonan);
		    Hashtable beanMaklumatTarikhDB = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
			Hashtable hashMaklumatTarikh = new Hashtable();
			hashMaklumatTarikh.put("tarikhHantar", getParam("txtTarikhHantar"));	
			hashMaklumatTarikh.put("tarikhLulus", getParam("txtTarikhLulus"));
			beanMaklumatTarikh.addElement(hashMaklumatTarikh);
			this.context.put("BeanMaklumatTarikh", beanMaklumatTarikh);
		 } 	
        
        if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        
        //SET DEFAULT PARAM
	    this.context.put("mode", mode);
	    
	    //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idKeputusan", idKeputusan);
        this.context.put("flagGuna", flagGuna);
        
        this.context.put("step",step);
        
        return vm;
	}
}
