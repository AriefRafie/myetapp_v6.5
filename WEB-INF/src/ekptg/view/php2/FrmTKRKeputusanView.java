package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRKeputusanData;

public class FrmTKRKeputusanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRKeputusanData logic = new FrmTKRKeputusanData();
	
	//DATE
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date currentDate = new Date();
	
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
	    String actionTukarguna = getParam("actionTukarguna");
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
       
	    //GET ID PARAM
        String idFail = getParam("idFail");
        String noFail = "";
        String tajukFail = "";
        String k1  = "";
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idKeputusan = getParam("idKeputusan");
        String flagGuna = getParam("flagGuna");
        String statusRizab = getParam("statusRizab");
        String flagAktif = getParam("flagAktif");
        String noRayuan = getParam("noRayuan");
        
        String idKategoriPemohon = "";
        String idPejabat = "";
        String idKementerian = "";
        String idAgensi = "";
               
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatKeputusan = null;
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		
		//GET DROPDOWN PARAM
        String idPampasan = getParam("socPampasan");
		if (idPampasan == null || idPampasan.trim().length() == 0){
			idPampasan = "99999";
		}
		
		String step = getParam("step");
		
		//SEND TO DB
		if (postDB) {
			
			if("doChangeKeputusan".equals(submit)){
				//if(!getParam("kelulusan").equals(getParam("socKeputusan"))){
        			//idStatus = "1610206";			
				logic.setMaklumatKeputusan(idPermohonan);
				if (logic.getBeanMaklumatKeputusan().size() != 0){
					Hashtable keputusan = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
					//idStatus = (String) keputusan.get("idStatus");
					k1 = (String) keputusan.get("keputusan");
					
					//System.out.println("keputusan = "+k1 + " status = " +idStatus);
				}
	    		//}
			}
	    	if ("doHantarProses".equals(hitButton)){
	    		logic.updateStatus(idFail, idPermohonan, idKeputusan, session);
	    	} 
	    	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
	    	if ("doSimpan".equals(hitButton)){
	    		logic.simpanMaklumatKeputusan(idPermohonan, getParam("txtTarikhKeputusan"), getParam("socKeputusan"),
						getParam("txtPampasan"), getParam("socPampasan"), getParam("txtUlasan"), idStatus, session);
	    		
	    		logic.setMaklumatKeputusan(idPermohonan);
				if (logic.getBeanMaklumatKeputusan().size() != 0){
					Hashtable keputusan = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
					//idStatus = (String) keputusan.get("idStatus");
					k1 = (String) keputusan.get("keputusan");
					//System.out.println("keputusan belum berubah ="+k1);
				}
				
				if(k1.equals(null)|| k1.equalsIgnoreCase("")){
					logic.simpanMaklumatKeputusan(idPermohonan, getParam("txtTarikhKeputusan"), getParam("socKeputusan"),
				    getParam("txtPampasan"), getParam("socPampasan"), getParam("txtUlasan"), idStatus, session);
				}
				else{
					if(!k1.equals(getParam("socKeputusan"))){
						logic.simpanMaklumatKeputusan(idPermohonan, getParam("txtTarikhKeputusan"), getParam("socKeputusan"),
						getParam("txtPampasan"), getParam("socPampasan"), getParam("txtUlasan"), idStatus, session);
					}else{
	    			//System.out.println("sama soc= "+ getParam("socKeputusan"));
					}		    
				}
				/*logic.simpanMaklumatKeputusan(idPermohonan, getParam("txtTarikhKeputusan"), getParam("socKeputusan"),
	    		getParam("txtPampasan"), getParam("socPampasan"), idStatus, session);*/
	
	    	}
	    	if ("daftarRayuan".equals(hitButton)) {
				idFail = logic.daftarRayuan(idFail, idPermohonan, getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						getParam("idHakmilikAgensi"), session);
			}
	    	if ("daftarEPU".equals(hitButton)) {
				logic.daftarEPU(idFail, idPermohonan, session);
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
			noFail = (String) hashHeader.get("noFail");
			tajukFail = (String) hashHeader.get("tajukFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			idKeputusan = (String) hashHeader.get("idKeputusan");
			flagGuna = (String) hashHeader.get("flagGuna");
			statusRizab = (String) hashHeader.get("statusRizab");
			flagAktif = (String) hashHeader.get("flagAktif");
			noRayuan = (String) hashHeader.get("noRayuan");
		}
		
		if ("daftarRayuan".equals(actionTukarguna)){
			
			vm = "app/php2/frmTKRDaftarRayuanManual.jsp";
			
			if ("new".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				Hashtable hashPermohonan = new Hashtable();
				hashPermohonan.put("noFail", noFail);
				hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
				hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
				hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
				hashPermohonan.put("perkara", getParam("txtPerkara") == null || "".equals(getParam("txtPerkara")) ? tajukFail : getParam("txtPerkara"));
				beanMaklumatPermohonan.addElement(hashPermohonan);
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);		
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idPejabat = (String) hashPemohon.get("idPejabat");
					idKementerian = (String) hashPemohon.get("idKementerian");
					idAgensi = (String) hashPemohon.get("idAgensi");
				}

				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
				
				if ("3".equals(idKategoriPemohon)) {
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensi);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
					
					this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					
				} else if (("8".equals(idKategoriPemohon))) {
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
				
				beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(logic.getidHakmilikAgensi(idFail));
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				
			} else {
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				
				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				logic.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);		
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idPejabat = (String) hashPemohon.get("idPejabat");
					idKementerian = (String) hashPemohon.get("idKementerian");
					idAgensi = (String) hashPemohon.get("idAgensi");
				}

				this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
				
				if ("3".equals(idKategoriPemohon)) {
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensi);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
					
					this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					
				} else if (("8".equals(idKategoriPemohon))) {
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
				
				beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(logic.getidHakmilikAgensi(idFail));
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			}
			
		} else {
			
			vm = "app/php2/frmTKRKeputusan.jsp";
	                        
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
				 	
	        	 this.context.put("readonly", "");
		    	 this.context.put("inputTextClass", "");
		    	 this.context.put("disabled", "");
		    
		    	//MAKLUMAT KEPUTUSAN
		    	beanMaklumatKeputusan = new Vector();
		    	logic.setMaklumatKeputusan(idPermohonan);
			    Hashtable hashMaklumatKeputusanDB = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
				Hashtable hashMaklumatKeputusan = new Hashtable();
				hashMaklumatKeputusan.put("tarikhKeputusan", getParam("txtTarikhKeputusan"));	
				hashMaklumatKeputusan.put("keputusan", getParam("socKeputusan"));
				hashMaklumatKeputusan.put("flagPampasan", getParam("socPampasan"));
				hashMaklumatKeputusan.put("pampasan", getParam("txtPampasan"));
				hashMaklumatKeputusan.put("jenis", getParam("socGanti"));	
				hashMaklumatKeputusan.put("catatan", getParam("txtCatatan"));
				hashMaklumatKeputusan.put("ulasan", getParam("txtUlasan"));
				beanMaklumatKeputusan.addElement(hashMaklumatKeputusan);
				this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
	        }	        
		}			
        
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
        //SET DEFAULT PARAM
        this.context.put("actionTukarguna", actionTukarguna);
	    this.context.put("mode", mode);
	    
	    //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idKeputusan", idKeputusan);
        this.context.put("flagGuna", flagGuna);
        this.context.put("statusRizab", statusRizab);
        this.context.put("idPampasan", idPampasan);
        this.context.put("flagAktif", flagAktif);
        this.context.put("noRayuan", noRayuan);
        
        this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idAgensi", idAgensi);
        
		this.context.put("step",step);
		
        return vm;
	}
}
