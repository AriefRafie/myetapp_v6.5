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
import ekptg.model.php2.FrmPLPKelulusanMenteriData;

public class FrmPLPKelulusanMenteriView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPKelulusanMenteriData logic = new FrmPLPKelulusanMenteriData();
	
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
        String statusRizab = getParam("statusRizab");
             
        //VECTOR
		Vector beanHeader= null;
		Vector beanKelulusanMenteri = null;
		
		String step = getParam("step");
		
		vm = "app/php2/frmPLPKelulusanMenteri.jsp";

		
		//SEND TO MODEL
        if (postDB) {
        	if ("simpanKemaskiniKelulusanMenteri".equals(hitButton)){
				logic.simpanKemaskiniKelulusanMenteri(idPermohonan, getParam("txtTarikhTerima"), getParam("socKeputusan"),
						getParam("txtUlasan"), idStatus, getParam("socKeputusanPemohon"), getParam("txtUlasanPemohon"), session);
			}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, session);
        	}
        	if ("doPindaKeputusan".equals(hitButton)){
        		logic.pindaKeputusan(idFail, idPermohonan, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
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
		}
		
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
		
		if("view".equals(mode)){

			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			//MAKLUMAT KEMASUKAN MINIT KEWANGAN
			beanKelulusanMenteri = new Vector();
			logic.setMaklumatKelulusanMenteri(idPermohonan);
			beanKelulusanMenteri = logic.getBeanKelulusanMenteri();
			this.context.put("BeanKelulusanMenteri", beanKelulusanMenteri);
			if (beanKelulusanMenteri.size() != 0){
				Hashtable hashMaklumatKelulusanMenteri = (Hashtable) logic.getBeanKelulusanMenteri().get(0);
				/*keputusan = (String) hashMaklumatKelulusanMenteri.get("keputusan");*/
	       	}
	
		} else if("update".equals(mode)){
				
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");
			
    		//MAKLUMAT KEMASUKAN MINIT KEWANGAN
			beanKelulusanMenteri = new Vector();
			logic.setMaklumatKelulusanMenteri(idPermohonan);
			//beanKelulusanMenteri = logic.getBeanKelulusanMenteri();
			Hashtable HashKelulusanMenteri = (Hashtable) logic.getBeanKelulusanMenteri().get(0);
			Hashtable hashKelulusanMenteri = new Hashtable();
			
			hashKelulusanMenteri.put("tarikhTerima", getParam("txtTarikhTerima"));
			hashKelulusanMenteri.put("keputusan", getParam("socKeputusan"));
			if ("doChangeKeputusanMenteri".equals(submit)){
				hashKelulusanMenteri.put("flagKeputusanPemohon", getParam("socKeputusanPemohon"));
			}
			hashKelulusanMenteri.put("ulasan", getParam("txtUlasan"));
			hashKelulusanMenteri.put("ulasanPemohon", getParam("txtUlasanPemohon"));
			
			beanKelulusanMenteri.addElement(hashKelulusanMenteri);
	       	this.context.put("BeanKelulusanMenteri", beanKelulusanMenteri);
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
	    this.context.put("statusRizab", statusRizab);
	    
	    if (!"".equals(getParam("flagFrom"))){
	      	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
	    }
		
		return vm;
	}
}
