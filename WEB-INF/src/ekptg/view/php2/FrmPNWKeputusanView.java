package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWKeputusanData;

public class FrmPNWKeputusanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWKeputusanData logic = new FrmPNWKeputusanData();
	
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
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
       
	    //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idKeputusan = getParam("idKeputusan");
        String keputusan = "";
        
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatTanah = null;
		Vector senaraiAgensi = null;
		Vector beanMaklumatKeputusan = null;
		
		String step = getParam("step");
		
		vm = "app/php2/frmPNWKeputusan.jsp";
		
		//SUBMIT TO NEXT PROCESS
		if (postDB) {
			 if ("simpanKemaskiniAgensi".equals(hitButton)){
				 logic.clearLuas(idPermohonan);
				 String ids[] = this.request.getParameterValues("ids");
				 String txtLuas[] = this.request.getParameterValues("txtLuas");
				 if (ids != null) {
					for (int i = 0; i < ids.length; i++) {
						if (!"".equals(txtLuas[i]) && Double.valueOf(txtLuas[i]) != 0D){
							logic.updateLuasAgensi(ids[i], txtLuas[i], session);
						}					
					}
				}
			 }
			 if ("simpanKeputusan".equals(hitButton)){
				 
				 //System.out.println("AFTER bean = " + getParam("kelulusan") + " ubah" + getParam("socKeputusan"));
	        		if(!getParam("kelulusan").equals(getParam("socKeputusan"))){
	        			idStatus = "1610206";
	        		}
				    logic.simpanKeputusan(idPermohonan, getParam("txtTarikhKeputusan"), getParam("socKeputusan"), idStatus, session);
			 }
			 if ("doSeterusnya".equals(hitButton)){
	    		logic.updateStatus(idFail, idPermohonan, idKeputusan, session);
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
			idKeputusan = (String) hashHeader.get("keputusan");
		}
		
		beanMaklumatTanah = new Vector();
        logic.setMaklumatTanah(idPermohonan);
        beanMaklumatTanah = logic.getBeanMaklumatTanah();
		this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		
		//CHECK KEPUTUSAN
		keputusan = logic.setKeputusan(idFail);
		this.context.put("keputusan", keputusan);
		
		if ("1".equals(selectedTabUpper)){
			
			if ("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				
			} else if ("update".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
			}
			
			this.context.put("txtJumlahLuas", logic.getTotalLuas(idPermohonan));
			
			//SENARAI AGENSI
			senaraiAgensi = new Vector();
			logic.setSenaraiAgensi(idPermohonan);
			senaraiAgensi = logic.getListAgensi();
			this.context.put("SenaraiAgensi", senaraiAgensi);
			
		} else if ("0".equals(selectedTabUpper)){
			
			if ("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				
				//MAKLUMAT KEPUTUSAN
				beanMaklumatKeputusan = new Vector();
				logic.setMaklumatKeputusan(idPermohonan);
				beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
		    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
				
			} else if ("update".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
				//MAKLUMAT KEPUTUSAN
				beanMaklumatKeputusan = new Vector();
				logic.setMaklumatKeputusan(idPermohonan);
				beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
		    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
				
			}
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
        this.context.put("idKeputusan", idKeputusan);
        

        this.context.put("step",step);
        
        return vm;
	}
}
