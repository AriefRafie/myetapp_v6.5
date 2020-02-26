/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWKeputusanData;

/**
 * 
 *
 */
public class FrmPYWKeputusanView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWKeputusanData logic = new FrmPYWKeputusanData();
	
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
        String statusRizab = getParam("statusRizab");
        String idSuburusan = getParam("idSuburusan");
        String idPerjanjian = getParam("idPerjanjian");
        String flagPermohonanDari = getParam("flagPermohonanDari");
        
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatKeputusan = null;
		Vector beanMaklumatPerjanjian = null;
		
		String step = getParam("step");
		
		this.context.put("errMsg", "");
		
		//SEND TO DB
		if (postDB) {
	    	if ("doHantarProses".equals(hitButton)){
	    		logic.updateStatus(idFail, idPermohonan, idKeputusan, idSuburusan, session); 
	    	} 
	    	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doSimpan".equals(hitButton)){
			    logic.simpanMaklumatKeputusan(idPermohonan, idKeputusan, getParam("txtTarikhKeputusan"), idPerjanjian,
			    		getParam("txtTarikhMulaDasar"), getParam("txtTempohDasar"), getParam("txtTarikhTamatDasar"), getParam("txtTarikhMula"), 
			    		getParam("txtTempoh"), getParam("txtTarikhTamat"), getParam("txtKadarSewa"),getParam("txtRoyalti"), getParam("txtCagaran"), session);
			}
		}
		
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			idKeputusan = (String) hashHeader.get("idKeputusan");
			flagGuna = (String) hashHeader.get("flagGuna");
			statusRizab = (String) hashHeader.get("statusRizab");
			idSuburusan = (String) hashHeader.get("idSuburusan");
			flagPermohonanDari = (String) hashHeader.get("flagPermohonanDari");
		}
		
		vm = "app/php2/frmPYWKeputusan.jsp";
		
		idPerjanjian = logic.getIdPerjanjianByIdPermohonan(idPermohonan);

        if("view".equals(mode)){
			 
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			 
			//MAKLUMAT KEPUTUSAN
			beanMaklumatKeputusan = new Vector();
			logic.setMaklumatKeputusan(idPermohonan);
			beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
	    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
	    	
	    	//MAKLUMAT PERJANJIAN
			beanMaklumatPerjanjian = new Vector();
			logic.setMaklumatPerjanjian(idPerjanjian);
			beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
	    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
	      		
		 } else if("update".equals(mode)){ 
			 	
        	 this.context.put("readonly", "");
	    	 this.context.put("inputTextClass", "");
	    	 this.context.put("disabled", "");
	    
	    	//MAKLUMAT KEPUTUSAN
	    	beanMaklumatKeputusan = new Vector();
	    	logic.setMaklumatKeputusan(idPermohonan);
		    Hashtable hashMaklumatKeputusanDB = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
			Hashtable hashMaklumatKeputusan = new Hashtable();
			hashMaklumatKeputusan.put("keputusan", getParam("socKeputusan"));	
			hashMaklumatKeputusan.put("tarikhKeputusan", getParam("txtTarikhKeputusan"));	
			beanMaklumatKeputusan.addElement(hashMaklumatKeputusan);
			this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
			
			if ("1610206".equals(idStatus)){
				idKeputusan = getParam("socKeputusan");
			}			
			
			//MAKLUMAT PERJANJIAN
	    	beanMaklumatPerjanjian = new Vector();
			Hashtable hashMaklumatPerjanjian = new Hashtable();
			hashMaklumatPerjanjian.put("tarikhMulaDasar", getParam("txtTarikhMulaDasar"));
			hashMaklumatPerjanjian.put("tarikhTamatDasar", getParam("txtTarikhTamatDasar"));
			hashMaklumatPerjanjian.put("tempohDasar", getParam("txtTempohDasar"));
			hashMaklumatPerjanjian.put("tarikhMula", getParam("txtTarikhMula"));	
			hashMaklumatPerjanjian.put("tempoh", getParam("txtTempoh"));
			hashMaklumatPerjanjian.put("tarikhTamat", getParam("txtTarikhTamat"));	
			hashMaklumatPerjanjian.put("kadarSewa", getParam("txtKadarSewa"));
			hashMaklumatPerjanjian.put("royalti", getParam("txtRoyalti"));
			hashMaklumatPerjanjian.put("cagaran", getParam("txtCagaran"));	
			
			beanMaklumatPerjanjian.addElement(hashMaklumatPerjanjian);
			this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
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
        this.context.put("statusRizab", statusRizab);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idPerjanjian", idPerjanjian);
        this.context.put("flagPermohonanDari", flagPermohonanDari);
        
        this.context.put("step",step);
        
        if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
        
		return vm;
	}
}
