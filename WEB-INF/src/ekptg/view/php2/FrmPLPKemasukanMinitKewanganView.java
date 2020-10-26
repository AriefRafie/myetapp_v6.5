/**
 * @author modified by hilda
 *
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPKemasukanMinitKewanganData;

public class FrmPLPKemasukanMinitKewanganView extends AjaxBasedModule{
	
private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPKemasukanMinitKewanganData logic = new FrmPLPKemasukanMinitKewanganData();
	@Override
	
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM 
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
        String idKertaskerja = getParam("idKertaskerja");
        
        //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatKewangan = null;
		Vector beanMaklumatLampiran = null;
		
		String step = getParam("step");
		
		vm = "app/php2/frmPLPKemasukanMinitKewangan.jsp";
		
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanKemaskiniMinitKewangan".equals(hitButton)){
				logic.simpanKemaskiniMinitKewangan(idPermohonan, getParam("txtTarikhTerima"), getParam("socKeputusan"),
						getParam("txtUlasan"), getParam("socKeputusanPemohon"), getParam("txtUlasanPemohon"), session);
			}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
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
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");			
		}
		
		if("view".equals(mode)){

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			//MAKLUMAT KEMASUKAN MINIT KEWANGAN
			beanMaklumatKewangan = new Vector();
			logic.setMaklumatMinitKewangan(idPermohonan);
			beanMaklumatKewangan = logic.getBeanMaklumatKewangan();
	       	this.context.put("BeanMaklumatKewangan", beanMaklumatKewangan);
	       	
	       	String a = getParam("idKertaskerja");
	       	
	       	if (beanMaklumatKewangan.size() != 0){
				Hashtable hashMaklumatKewangan = (Hashtable) logic.getBeanMaklumatKewangan().get(0);
				hashMaklumatKewangan.put("ulasanPemohon", getParam("txtUlasanPemohon"));
				hashMaklumatKewangan.put("flagKeputusanPemohon", getParam("socKeputusanPemohon"));
				idKertaskerja = (String) hashMaklumatKewangan.get("idKertaskerja");
	       	}
	       	
	       	//LAMPIRAN ULASAN
	       	beanMaklumatLampiran = new Vector();
			beanMaklumatLampiran = logic.getBeanMaklumatLampiran(idKertaskerja);
			this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
	       	
		} else if("update".equals(mode)){
				
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");
			
    		//MAKLUMAT KEMASUKAN MINIT KEWANGAN
			beanMaklumatKewangan = new Vector();
			logic.setMaklumatMinitKewangan(idPermohonan);
			//beanMaklumatKewangan = logic.getBeanMaklumatKewangan();
			Hashtable HashMaklumatKewangan = (Hashtable) logic.getBeanMaklumatKewangan().get(0);
			Hashtable hashMaklumatKewangan = new Hashtable();
			
			hashMaklumatKewangan.put("tarikhTerima", getParam("txtTarikhTerima"));
			hashMaklumatKewangan.put("keputusan", getParam("socKeputusan"));
			if ("doChangeKeputusanKewangan".equals(submit)){
				hashMaklumatKewangan.put("flagKeputusanPemohon", getParam("socKeputusanPemohon"));
				
			}
			hashMaklumatKewangan.put("ulasan", getParam("txtUlasan"));
			hashMaklumatKewangan.put("ulasanPemohon", getParam("txtUlasanPemohon"));
			
			beanMaklumatKewangan.addElement(hashMaklumatKewangan);
	       	this.context.put("BeanMaklumatKewangan", beanMaklumatKewangan);	
	  
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
	    this.context.put("idKertaskerja", idKertaskerja);
		
	    this.context.put("step",step);
	    
	    if (!"".equals(getParam("flagFrom"))){
	      	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
	    }
	    
		return vm;
	}	
}