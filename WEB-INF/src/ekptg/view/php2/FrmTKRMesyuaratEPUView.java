/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRMesyuaratEPUData;

/**
 * 
 *
 */
public class FrmTKRMesyuaratEPUView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	
	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRMesyuaratEPUData logic = new FrmTKRMesyuaratEPUData();

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
        String idMesyuarat = getParam("idMesyuarat");
        String idKeputusan = getParam("idKeputusan");
        
        //VECTOR
		Vector beanHeader = null;
        Vector beanMaklumatMesyuarat = null;
        
        //GET DROPDOWN PARAM
        String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0){
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0){
			idMinitDari = "99999";
		}
		String idJamHingga = getParam("socJamHingga");
		if (idJamHingga == null || idJamHingga.trim().length() == 0){
			idJamHingga = "99999";
		}
		String idMinitHingga = getParam("socMinitHingga");
		if (idMinitHingga == null || idMinitHingga.trim().length() == 0){
			idMinitHingga = "99999";
		}
				
		vm = "app/php2/frmTKRMesyuaratEPU.jsp";
		
		//SEND TO MODEL
        if (postDB) {
        	if ("simpanKemaskiniMesyuarat".equals(hitButton)){
    			logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, getParam("socSyor"), getParam("txtCatatan"), idStatus, session);
    		}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, idKeputusan, session);
        	}
        }
        
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
		}
		
		idMesyuarat = logic.getIdMesyuaratEPU(idPermohonan);
		
		if ("view".equals(mode)){
			
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			beanMaklumatMesyuarat = new Vector();
   			logic.setMaklumatMesyuarat(idMesyuarat);
   			beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
   			this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
   			
   			if (beanMaklumatMesyuarat.size() != 0){
				Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
				idJamDari = (String)(hashMesyuarat.get("idJamDari"));
				idMinitDari = (String)(hashMesyuarat.get("idMinitDari"));
				idJamHingga = (String)(hashMesyuarat.get("idJamHingga"));
				idMinitHingga = (String)(hashMesyuarat.get("idMinitHingga"));
				idKeputusan = (String)(hashMesyuarat.get("flagSyor"));
   			}
			
			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));
			
		} else {
			
			 this.context.put("readonly", "");
	    	 this.context.put("inputTextClass", "");
	    	 this.context.put("disabled", "");
	    	 
	    	beanMaklumatMesyuarat = new Vector();    		   	
     		Hashtable hashMesyuarat = new Hashtable();
     		hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
     		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
     		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
     		hashMesyuarat.put("catatan", getParam("txtCatatan"));
     		hashMesyuarat.put("flagSyor", getParam("socSyor"));
     		beanMaklumatMesyuarat.addElement(hashMesyuarat);
 			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
 			
 			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
 			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
 			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
 			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
		}
		
		//SET DEFAULT PARAM
	    this.context.put("mode", mode);
	    
	    //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idMesyuarat", idMesyuarat);
        this.context.put("idKeputusan", idKeputusan);
        
		return vm;
	}
}
