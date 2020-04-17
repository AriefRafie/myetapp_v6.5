/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWPenamatanPerjanjianData;

/**
 * 
 *
 */
public class FrmPYWPenamatanPerjanjianView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWPenamatanPerjanjianData logic = new FrmPYWPenamatanPerjanjianData();
	
	@Override
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
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
        String actionPenyewaan = getParam("actionPenyewaan");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idUrusan = getParam("idUrusan");
        String idSuburusan = getParam("idSuburusan");
        String idStatus = getParam("idStatus");
        String flagAktif = getParam("flagAktif");
        String idMesyuarat = getParam("idMesyuarat");
        
        //GET DROPDOWN PARAM
        String idJawatan = getParam("socJawatan");
		if (idJawatan == null || idJawatan.trim().length() == 0) {
			idJawatan = "99999";
		}
		String idLokasi = getParam("socLokasi");
		if (idLokasi == null || idLokasi.trim().length() == 0){
			idLokasi = "99999";
		}
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
        
        //VECTOR
        Vector beanHeader = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatKertasMakluman = null;
        Vector senaraiMesyuarat = null;
        Vector beanMaklumatMesyuarat = null;
        
        //DATE
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date currentDate = new Date();
    	
    	//SUBMIT TO DB
        if (postDB) {
        	if ("daftarPenamatan".equals(hitButton)){
				logic.daftarPenamatan(idFail, idPermohonan, idSuburusan, getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("socFlagSebabTamat"), getParam("txtCatatan"), session);
			}
        	if ("simpanKemaskiniMaklumatPenamatan".equals(hitButton)){
				logic.simpanKemaskiniMaklumatPenamatan(idPermohonan, getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("socFlagSebabTamat"), getParam("txtCatatan"), idStatus, session);
			}
        	if ("simpanKemaskiniKertasMakluman".equals(hitButton)){
				logic.simpanKemaskiniKertasMakluman(idPermohonan, getParam("txtUlasan"),
						getParam("txtSediaOleh"), idJawatan, getParam("tarikhSedia"), session);
			}
        	if ("simpanMesyuarat".equals(hitButton)){
    			idMesyuarat = logic.simpanMesyuarat(idPermohonan, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatanMesyuarat"), session);
    		}
        	if ("simpanKemaskiniMesyuarat".equals(hitButton)){
    			logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatanMesyuarat"), session);
    		}
        	if ("hapusMesyuarat".equals(hitButton)){
    			logic.hapusMesyuarat(idMesyuarat);
    		}
        	if ("selesai".equals(hitButton)){
        		logic.selesai(idFail, idPermohonan, idSuburusan, session);
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
			idUrusan = (String) hashHeader.get("idUrusan");
			idSuburusan = (String) hashHeader.get("idSuburusan");
			flagAktif = (String) hashHeader.get("flagAktif");
		}
		
		vm = "app/php2/frmPYWPenamatanPerjanjian.jsp";
		
		if ("1610195".equals(idStatus)) {
			
			mode = "new";
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("flagSebabTamat", getParam("socFlagSebabTamat") == null ? "": getParam("socFlagSebabTamat"));
			hashPermohonan.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
		} else {
			
			//MAKLUMAT PERMOHONAN PENAMATAN PERJANJIAN
			if ("0".equals(selectedTabUpper)){
				
				beanMaklumatPermohonan = new Vector();
		        logic.setMaklumatPermohonan(idPermohonan);
		        beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				
				if ("view".equals(mode)){
					
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
					
				} else {
					
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
					
				}
			}
			
			//KERTAS MAKLUMAN
			if ("1".equals(selectedTabUpper)){
				
				beanMaklumatKertasMakluman = new Vector();
		        logic.setMaklumatKertasMakluman(idPermohonan);
		        beanMaklumatKertasMakluman = logic.getBeanMaklumatKertasMakluman();
				this.context.put("BeanMaklumatKertasMakluman", beanMaklumatKertasMakluman);
				
				if ("view".equals(mode)){
					
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
					
					if (beanMaklumatKertasMakluman.size() != 0){
						Hashtable hashKertas = (Hashtable) logic.getBeanMaklumatKertasMakluman().get(0);
						idJawatan = (String) hashKertas.get("idJawatan");
					}
					
					this.context.put("selectJawatan", HTML.SelectJawatan("socJawatan", Long.parseLong(idJawatan), "disabled", " class=\"disabled\""));
					
				} else {
					
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
					
					this.context.put("selectJawatan", HTML.SelectJawatan("socJawatan", Long.parseLong(idJawatan), "", ""));
					
				}
			}
			
			//MESYUARAT
			if ("2".equals(selectedTabUpper)){
				
				//OPEN POPUP DAFTAR BARU MESYUARAT
				if ("openMesyuarat".equals(flagPopup)){
					
					//MODE NEW
		        	if ("new".equals(modePopup)){
		    				
		    			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		        		
		        		beanMaklumatMesyuarat = new Vector();    		   	
		        		Hashtable hashMesyuarat = new Hashtable();
		        		if (getParam("txtTajukMesyuarat") == null || "".equals(getParam("txtTajukMesyuarat"))){
		        			hashMesyuarat.put("tajukMesyuarat", "MESYUARAT JAWATANKUASA PENYEWAAN DAN PENGUATKUASAN HARTA TANAH PERSEKUTUAN");
		        		} else {
		        			hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
		        		}        		
		        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
		        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
		        		hashMesyuarat.put("catatan", getParam("txtCatatanMesyuarat"));
		        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
		        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
		    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "",""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
		    				
		        	} else if ("view".equals(modePopup)){ //MODE VIEW
		    				
		    			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			beanMaklumatMesyuarat = new Vector();
			   			logic.setMaklumatMesyuarat(idMesyuarat);
			   			beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
			   			this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
			   			
			   			if (beanMaklumatMesyuarat.size() != 0){
							Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
							idLokasi = (String)(hashMesyuarat.get("idLokasi"));
							idJamDari = (String)(hashMesyuarat.get("idJamDari"));
							idMinitDari = (String)(hashMesyuarat.get("idMinitDari"));
							idJamHingga = (String)(hashMesyuarat.get("idJamHingga"));
							idMinitHingga = (String)(hashMesyuarat.get("idMinitHingga"));
			   			}
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "disabled", " class=\"disabled\""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));
		    				
		        	} else if ("update".equals(modePopup)){
		    				
		    			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		        		
		        		beanMaklumatMesyuarat = new Vector();    		   	
		        		Hashtable hashMesyuarat = new Hashtable();
		        		hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
		        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
		        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
		        		hashMesyuarat.put("catatan", getParam("txtCatatanMesyuarat"));
		        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
		        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
		    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "",""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
		    				
		        	}
				}
							
				//SENARAI MESYUARAT
				senaraiMesyuarat = new Vector();
		        logic.setSenaraiMesyuarat(idPermohonan);
				senaraiMesyuarat = logic.getListMesyuarat();
				this.context.put("SenaraiMesyuarat",senaraiMesyuarat);
			}
		}
	    
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("actionPenyewaan", actionPenyewaan);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idUrusan", idUrusan);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idStatus", idStatus);      
        this.context.put("flagAktif", flagAktif);
        this.context.put("idMesyuarat", idMesyuarat);
		
		return vm;
	}

}
