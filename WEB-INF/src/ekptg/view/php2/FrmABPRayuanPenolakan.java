package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBRayuanPenolakanData;

public class FrmABPRayuanPenolakan extends  AjaxBasedModule {

	 private static final long serialVersionUID = 1L;
		
	 FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	 FrmAPBRayuanPenolakanData logic = new FrmAPBRayuanPenolakanData();
		
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
        String submit = getParam("command");  
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        String hitButtonDaftar = getParam("hitButtonDaftar");
		String masterMode = getParam("masterMode");
        
        
      //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String flagAktif = getParam("flagAktif");
        String noRayuan = getParam("noRayuan");
        
      //RAYUAN TUKARGUNA
        String idFailRayuan = getParam("idFailRayuan");
        String idPermohonanRayuan = getParam("idPermohonanRayuan");
        
      //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatPermohonanRayuan = null;
		Vector listRayuan = null;
		Vector beanRayuanLama = null;
		
		if(postDB){
			if("doSimpan".equals(hitButton)){
	    		
				//create fail rayuan
				listRayuan = logic.simpanRayuan(idFail,idPemohon,idPermohonan,getParam("tarikhTerima"), 
    					getParam("tarikhSurat"), getParam("perkara"),getParam("socTukarKoordinat"),noRayuan, session);
    		
				if(!listRayuan.isEmpty() && listRayuan.size()> 0) {
	        		Hashtable hashRayuanDB = (Hashtable) listRayuan.get(0);
	        		idFail = hashRayuanDB.get("idFailRayuan").toString();
	        		idPermohonan= hashRayuanDB.get("idPermohonanRayuan").toString();  
	        		
	        	}
			}
			
			if ("doHantarProses".equals(hitButtonDaftar)){
				//update status berdasarkan tukar koordinat atau tidak
				logic.updateStatus(idFail, idPermohonan,getParam("socTukarKoordinat"),session);
			}
		}
	        
      //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			
			if (idPemohon.isEmpty()){
				idPemohon = hashHeader.get("idPemohon").toString();
			}
			idStatus = hashHeader.get("idStatus").toString();
			flagAktif = (String)hashHeader.get("flagAktif");
			noRayuan = (String)hashHeader.get("noRayuan");
		}
		
		//MODE
		if (mode.isEmpty()){

			if("0".equals(flagAktif)){
				mode = "view";
			}
			else
				mode = "new";
		 }
		 
		//SET MASTER MODE
		if ("1610208".equals(idStatus)){
			masterMode = "update";
		} else {
		    masterMode = "view";
		}
			
        
        vm = "app/php2/frmAPBRayuan.jsp";
    	
        if("view".equals(mode)){
       
        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			//MAKLUMAT RAYUAN
			logic.setMaklumatRayuan(idPermohonan);
			beanMaklumatPermohonanRayuan = logic.getBeanMaklumatRayuan();
			this.context.put("BeanMaklumatPermohonanRayuan", beanMaklumatPermohonanRayuan);
			
			
			//MAKLUMAT RAYUAN BG FAIL LAMA
			if("0".equals(flagAktif)){
				beanRayuanLama = new Vector();
				logic.setMaklumatRayuanLama(idPermohonan);
				beanRayuanLama = logic.getBeanMaklumatRayuanLama();
				this.context.put("BeanRayuanLama", beanRayuanLama);
			}
        	
        }
        //if("new".equals(mode))
        else {
        	
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN RAYUAN
        	beanMaklumatPermohonanRayuan = new Vector();
			Hashtable hashRayuan = new Hashtable();
			hashRayuan.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
			hashRayuan.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
			hashRayuan.put("perkara", getParam("perkara") == null ? "" : getParam("perkara"));
			hashRayuan.put("socTukarKoordinat", getParam("socTukarKoordinat") == null ? "" : getParam("socTukarKoordinat"));
			beanMaklumatPermohonanRayuan.addElement(hashRayuan);
			this.context.put("BeanMaklumatPermohonanRayuan", beanMaklumatPermohonanRayuan);
        }
        
        //SET DEFAULT PARAM
	    this.context.put("mode", mode);
	    this.context.put("masterMode", masterMode);
		
		
		//SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("noRayuan", noRayuan);
        
        //SET ID PARAM RAYUAN APB
        this.context.put("idFailRayuan", idFailRayuan);
        this.context.put("idPermohonanRayuan", idPermohonanRayuan);
        
        return vm;
	}

}
