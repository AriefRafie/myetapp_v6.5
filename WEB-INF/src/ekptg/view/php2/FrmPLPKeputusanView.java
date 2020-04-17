package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPKelulusanMenteriData;
import ekptg.model.php2.FrmPLPKeputusanData;

public class FrmPLPKeputusanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPKeputusanData logic = new FrmPLPKeputusanData();
	FrmPLPKelulusanMenteriData logicKeputusanmenteri = new FrmPLPKelulusanMenteriData();
	
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
        String idKeputusanPTD = getParam("idKeputusanPTD");
        String flagGuna = getParam("flagGuna");
        String statusRizab = getParam("statusRizab");
        
        String flagPampasan = getParam("flagPampasan");
        String pampasan = getParam("pampasan");
        
        this.context.put("redirectToSkrinBorang", "");
               
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatKeputusan = null;
		
		//GET DROPDOWN PARAM
        String idPampasan = getParam("socGanti");
		if (idPampasan == null || idPampasan.trim().length() == 0){
			idPampasan = "99999";
		}
		
		String step = getParam("step");
		
		//SEND TO DB
		if (postDB) {
	    	if ("doHantarProses".equals(hitButton)){
	    		logic.updateStatusBorang(idFail, idPermohonan, idKeputusanPTD, session);
	    		if(idKeputusanPTD.equals("S"))
	    		{
	    			this.context.put("redirectToSkrinBorang", "Y");
	    		}
	    	} 
	    	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doSimpan".equals(hitButton)){
			    logic.simpanMaklumatKeputusan(idPermohonan, getParam("txtPampasan"), idPampasan, 
			    		getParam("txtTarikhTerima"), getParam("socKeputusan"), getParam("txtUlasan"), session);
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
			idKeputusanPTD = (String) hashPermohonan.get("idKeputusanPTD");
			flagGuna = (String) hashPermohonan.get("flagGuna");
			
			flagPampasan = (String) hashPermohonan.get("flagPampasan");
			pampasan = (String) hashPermohonan.get("pampasan");
		}
		
		Vector beanKelulusanMenteri = new Vector();
		logicKeputusanmenteri.setMaklumatKelulusanMenteri(idPermohonan);
		beanKelulusanMenteri = logicKeputusanmenteri.getBeanKelulusanMenteri();
		this.context.put("BeanKelulusanMenteri", beanKelulusanMenteri);
		
		//MAKLUMAT HAKMILIK
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			if ("".equals((String) hashHakmilik.get("noHakmilik"))){
				statusRizab = "RIZAB";
			} else {
				statusRizab = "MILIK";
			}			
		}
		
		vm = "app/php2/frmPLPKeputusan.jsp";
                        
        if("view".equals(mode)){
			 
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			 
			//MAKLUMAT KEPUTUSAN
			beanMaklumatKeputusan = new Vector();
			logic.setMaklumatKeputusan(idPermohonan);
			beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
	    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
	      		
		 } else if("update".equals(mode)){ 
			 	
			 System.out.println("cek" + getParam("txtCatatan"));
        	 this.context.put("readonly", "");
	    	 this.context.put("inputTextClass", "");
	    	 this.context.put("disabled", "");
	    
	    	//MAKLUMAT KEPUTUSAN
	    	beanMaklumatKeputusan = new Vector();
	    	logic.setMaklumatKeputusan(idPermohonan);
		    Hashtable hashMaklumatKeputusanDB = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
			Hashtable hashMaklumatKeputusan = new Hashtable();
			hashMaklumatKeputusan.put("tarikhKeputusan", getParam("txtTarikhKeputusan"));	
			hashMaklumatKeputusan.put("flagPampasan", getParam("socPampasan"));
			hashMaklumatKeputusan.put("pampasan", getParam("txtPampasan"));
			hashMaklumatKeputusan.put("jenis", getParam("socGanti"));	
			hashMaklumatKeputusan.put("catatan", getParam("txtCatatan"));
			hashMaklumatKeputusan.put("ulasan", getParam("txtUlasan"));
			hashMaklumatKeputusan.put("tarikhTerima", getParam("txttarikhTerima"));
			hashMaklumatKeputusan.put("keputusan", getParam("socKeputusan"));
			beanMaklumatKeputusan.addElement(hashMaklumatKeputusan);
			this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
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
        this.context.put("idKeputusanPTD", idKeputusanPTD);
        this.context.put("flagGuna", flagGuna);
        this.context.put("statusRizab", statusRizab);
        this.context.put("idPampasan", idPampasan);
        this.context.put("socGanti", idPampasan);
        
        this.context.put("flagPampasan", flagPampasan);
        this.context.put("pampasan", pampasan);
        
        this.context.put("step",step);
        System.out.println(" keputusan vm : "+vm);
        return vm;
	}
}
