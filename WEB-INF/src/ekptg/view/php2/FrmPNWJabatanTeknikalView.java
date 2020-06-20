package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPNWHeaderData;
import ekptg.model.php2.FrmPNWJabatanTeknikalData;

public class FrmPNWJabatanTeknikalView extends AjaxBasedModule{

private static final long serialVersionUID = 1L;
	
	FrmPNWHeaderData logicHeader = new FrmPNWHeaderData();
	FrmPNWJabatanTeknikalData logic = new FrmPNWJabatanTeknikalData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String submit = getParam("command");  
	    String vm = ""; 
	    String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
		
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");        
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idKertasKerja = getParam("idKertasKerja");
        String idNegeriTanah = getParam("idNegeriTanah");
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
        String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        String idDokumen = "4";
        
        //GET DROPDOWN PARAM
        String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}		
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}	
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiKJT = null;
        Vector beanMaklumatKJT = null;
        Vector beanMaklumatPejabat = null;        
        
        String step = getParam("step");
        
        vm = "app/php2/frmPNWJabatanTeknikal.jsp";
        
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanMaklumatKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJT(idPermohonan, "4", idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJT(idUlasanTeknikal, idPermohonan, "4", idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatKJT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, "4", idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
    		}
        	if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
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
			idNegeriTanah = (String) hashHakmilik.get("idNegeri");		
			idKementerianTanah = (String) hashHakmilik.get("idKementerian");			
			idAgensiTanah = (String) hashHakmilik.get("idAgensi");			
		}

		//OPEN POPUP DETAIL MAKLUMAT KJT
		if ("openKJT".equals(flagPopup)){
			
			if ("new".equals(modePopup)){
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)){
    				
    				beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", "");
	    			hashMaklumatKJT.put("jangkamasa", "");
	    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
					
					idDokumen = "99999";
					idKementerian = "99999";
					idAgensi = "99999";
					idNegeri = "99999";
					idPejabat = "99999";
					
    			} else {
    				
    				beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
    			}
    			
    			//this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
				if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
					idNegeri = idNegeriTanah;
				}
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
			
			} else if ("newUlangan".equals(modePopup)){
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)){
    				
    				beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", "");
	    			hashMaklumatKJT.put("jangkamasa", "");
	    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
					
    			} else {
    				
    				beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
    			}
    			
    			//this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
					idNegeri = idNegeriTanah;
				}
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
			
			} else if ("view".equals(modePopup)){
				this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			this.context.put("disabled", "disabled");
				
    			beanMaklumatKJT = new Vector();
				logic.setMaklumatKJT(idUlasanTeknikal);
				beanMaklumatKJT = logic.getBeanMaklumatKJT();
				this.context.put("BeanMaklumatKJT",beanMaklumatKJT);
				
				if (beanMaklumatKJT.size() != 0){
					Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
					idDokumen = (String) hashMaklumatKJT.get("idDokumen");
					idKementerian = (String) hashMaklumatKJT.get("idKementerian");
					idAgensi = (String) hashMaklumatKJT.get("idAgensi");
					idNegeri = (String) hashMaklumatKJT.get("idNegeri");
					idPejabat = (String) hashMaklumatKJT.get("idPejabat");
					flagStatus = (String) hashMaklumatKJT.get("flagStatus");
					flagAktif = (String) hashMaklumatKJT.get("flagAktif");
				}
				//this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
				
    			beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				
			} else if ("update".equals(modePopup)){					
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			beanMaklumatKJT = new Vector();    			
    			Hashtable hashMaklumatKJT = new Hashtable();
    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
    			hashMaklumatKJT.put("tarikhTerima", getParam("txtTarikhTerima"));
    			hashMaklumatKJT.put("tarikhSurat", getParam("txtTarikhSurat"));
    			hashMaklumatKJT.put("noRujukan", getParam("txtNoRujukanSurat"));
    			hashMaklumatKJT.put("ulasan", getParam("txtUlasan"));
    			beanMaklumatKJT.addElement(hashMaklumatKJT);
				this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
    			
				if ("1".equals(flagStatus)){
					//this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));

				} else {
					//this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
				}					
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
			}
		}
		
		//DOKUMEN KJT
		senaraiKJT = new Vector();
		logic.setSenaraiKJT(idPermohonan);
		senaraiKJT = logic.getListKJT();
		this.context.put("SenaraiKJT", senaraiKJT);
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idNegeriTanah", idNegeriTanah);
        this.context.put("idKementerianTanah", idKementerianTanah);
        this.context.put("idAgensiTanah", idAgensiTanah);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idKertasKerja", idKertasKerja);
        this.context.put("idDokumen", idDokumen);
        
        this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
	    

        this.context.put("step",step);
        
		return vm;
	}
}
