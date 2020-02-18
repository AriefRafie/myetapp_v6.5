/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWJabatanTeknikalData;
import ekptg.model.php2.online.FrmPYWOnlineKJPSenaraiFailData;

/**
 *  modified by hilda
 *
 */
public class FrmPYWJabatanTeknikalView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWJabatanTeknikalData logic = new FrmPYWJabatanTeknikalData();
	FrmPYWOnlineKJPSenaraiFailData logicKJP = new FrmPYWOnlineKJPSenaraiFailData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String submit = getParam("command");  
	    String vm = ""; 
	    String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idHakmilikPermohonan = getParam("idHakmilikPermohonan");
        String idStatus = getParam("idStatus");
        String idSuburusan = getParam("idSuburusan");
        String flagPermohonanDari = getParam("flagPermohonanDari");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idNegeriTanah = getParam("idNegeriTanah");
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
        String flagStatus = getParam("flagStatus");
        String aktif = getParam("aktif");
        String idHakmilik = "";
        String idKementerianJKT = getParam("idKementerianJKT");
        String idAgensiJKT = getParam("idAgensiJKT");
        String idPejabatJKT = getParam("idPejabatJKT");
        
        //GET DROPDOWN PARAM
        String idDokumen = getParam("socDokumen");
		if (idDokumen == null || idDokumen.trim().length() == 0){
			idDokumen = "99999";
		}
        String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idKementerianKJP = getParam("socKementerianKJP");
		if (idKementerianKJP == null || idKementerianKJP.trim().length() == 0){
			idKementerianKJP = "99999";
		}
		String idAgensiKJP = getParam("socAgensiKJP");
		if (idAgensiKJP == null || idAgensiKJP.trim().length() == 0){
			idAgensiKJP = "99999";
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
        Vector senaraiKJP = null;
        Vector beanMaklumatKJP = null;
        Vector senaraiJPPH = null;
        Vector beanMaklumatJPPH = null;
        Vector senaraiJKT = null;
        Vector beanMaklumatJKT = null;
        Vector senaraiJH = null;
        Vector beanMaklumatJH = null;
        Vector beanMaklumatPejabat = null;
        Vector beanMaklumatAgensi = null;
        Vector beanMaklumatLampiran = null;
        this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
        
        String step = getParam("step");
        
        vm = "app/php2/frmPYWJabatanTeknikal.jsp";
        
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanMaklumatKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJT(idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJT(idUlasanTeknikal, idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatKJT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanKJT(idPermohonan, idDokumen, getParam("txtUlasan"),  session); 
    		}
        	if ("simpanMaklumatKJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJP(idPermohonan, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganKJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJP(idUlasanTeknikal, idPermohonan, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatKJP".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJP(idUlasanTeknikal, idKementerianKJP, idAgensiKJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), getParam("socKeputusan"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanKJP(idPermohonan, getParam("txtUlasan"),  session);
    		}
        	if ("simpanMaklumatJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJPPH(idPermohonan, getParam("idPejabatJPPH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJPPH(idUlasanTeknikal, idPermohonan, getParam("idPejabatJPPH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJPPH".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatJPPH(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),
        				getParam("txtKadarSewaBulan"), getParam("txtKadarSewaTahun"), getParam("socKadarSewa"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJPPH(idPermohonan, getParam("txtUlasan"),  session);
    		}
        	if ("simpanMaklumatJKT".equals(hitButton)){
        		logic.simpanMaklumatJKT(idPermohonan, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJKT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJKT(idUlasanTeknikal, idPermohonan, idKementerianJKT, idAgensiJKT, idPejabatJKT,
        				getParam("txtTarikhHantar"), getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJKT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatJKT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJKT(idPermohonan, idKementerianJKT, idAgensiJKT, idPejabatJKT, getParam("txtUlasan"),  session);
    		}
        	if ("simpanMaklumatJH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJH(idPermohonan, getParam("idPejabatJH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJH(idUlasanTeknikal, idPermohonan, getParam("idPejabatJH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJH".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatJH(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJH(idPermohonan, getParam("txtUlasan"),  session);
    		}
    		if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusDokumen(idUlasanTeknikal, session);
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
    		}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, idSuburusan, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, idSuburusan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        }
        
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
			idSuburusan = (String) hashPermohonan.get("idSuburusan");
			flagPermohonanDari = (String) hashPermohonan.get("flagPermohonanDari");
		}
		
		String flagMT = logic.getFlagMT(idFail, userId);
		this.context.put("flagMT", flagMT);
		
		//MAKLUMAT HAKMILIK
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idHakmilik = logicHeader.getIdHakmilikByIdHakmilikPermohonan((String) hashHakmilik.get("idHakmilikPermohonan"));
			//System.out.println(idHakmilik);
			idNegeriTanah = (String) hashHakmilik.get("idNegeri");		
			idKementerianTanah = (String) hashHakmilik.get("idKementerian");			
			idAgensiTanah = (String) hashHakmilik.get("idAgensi");
		}
	
		// MODE VIEW
		if("view".equals(mode)){
			
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			
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
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
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
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri));
					
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
						aktif = (String) hashMaklumatKJT.get("aktif");
					}
					this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
					
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
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
					} else {
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri));
					}					
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT KJP
			if ("openKJP".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			hashMaklumatKJP.put("tarikhHantar", "");
		    			hashMaklumatKJP.put("jangkamasa", "");
		    			hashMaklumatKJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
						idKementerianKJP = idKementerianTanah;
						idAgensiKJP = idAgensiTanah;
						
	    			} else {
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			}
	    			
	    			this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "", ""));
					
				}  else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			hashMaklumatKJP.put("tarikhHantar", "");
		    			hashMaklumatKJP.put("jangkamasa", "");
		    			hashMaklumatKJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			} else {
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar") == null ? "" : getParam("txtTarikhHantar"));
		    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa") == null ? "" : getParam("txtJangkaMasa"));
		    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima") == null ? "" : getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			}	
	    			
	    			this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "disabled", " class=\"disabled\""));
					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatKJP = new Vector();
					logic.setMaklumatKJP(idUlasanTeknikal, logicKJP.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
					beanMaklumatKJP = logic.getBeanMaklumatKJP();
					this.context.put("BeanMaklumatKJP",beanMaklumatKJP);
					
					if (beanMaklumatKJP.size() != 0){
						Hashtable hashMaklumatKJP = (Hashtable) logic.getBeanMaklumatKJP().get(0);
						flagStatus = (String) hashMaklumatKJP.get("flagStatus");
						aktif = (String) hashMaklumatKJP.get("aktif");
						idKementerianKJP = (String) hashMaklumatKJP.get("idKementerianKJP");
						idAgensiKJP = (String) hashMaklumatKJP.get("idAgensiKJP");
					}
					
					this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "disabled", " class=\"disabled\""));
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatKJP = new Vector();    			
	    			Hashtable hashMaklumatKJP = new Hashtable();
	    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatKJP.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatKJP.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatKJP.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatKJP.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatKJP.put("flagKeputusan", getParam("socKeputusan"));
	    			beanMaklumatKJP.addElement(hashMaklumatKJP);
					this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
					
					if ("1".equals(flagStatus)){
						this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "", " onChange=\"doChangeKementerian();\""));
						this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "", ""));
						
					} else {
						this.context.put("selectKementerianKJP", HTML.SelectKementerian("socKementerianKJP", Utils.parseLong(idKementerianKJP), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensiKJP", HTML.SelectAgensiByKementerian("socAgensiKJP", idKementerianKJP, Utils.parseLong(idAgensiKJP), "disabled", " class=\"disabled\""));
					}	
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT JPPH
			if ("openJPPH".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", "");
		    			hashMaklumatJPPH.put("jangkamasa", "");
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", "");

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
						idPejabat = logic.getIdPejabatJPPH(idHakmilik);
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
						
	    			} else {
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", "");
		    			hashMaklumatJPPH.put("jangkamasa", "");
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", "");

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);						
						
	    			} else {
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
	    			}
	    			
	    			idPejabat = getParam("idPejabatJPPH");
	    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
					
	    			beanMaklumatJPPH = new Vector();
					logic.setMaklumatJPPH(idUlasanTeknikal);
					beanMaklumatJPPH = logic.getBeanMaklumatJPPH();
					this.context.put("BeanMaklumatJPPH",beanMaklumatJPPH);
					
					if (beanMaklumatJPPH.size() != 0){
						Hashtable hashMaklumatJPPH = (Hashtable) logic.getBeanMaklumatJPPH().get(0);
						idPejabat = (String) hashMaklumatJPPH.get("idPejabat");
						flagStatus = (String) hashMaklumatJPPH.get("flagStatus");
						aktif = (String) hashMaklumatJPPH.get("aktif");
					}
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			beanMaklumatJPPH = new Vector();    			
	    			Hashtable hashMaklumatJPPH = new Hashtable();
	    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatJPPH.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJPPH.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJPPH.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJPPH.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJPPH.put("kadarSewaBulan", getParam("txtKadarSewaBulan"));
	    			hashMaklumatJPPH.put("kadarSewaTahun", getParam("txtKadarSewaTahun"));
	    			hashMaklumatJPPH.put("jenisKadarSewa", getParam("socKadarSewa"));
	    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
					this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
					
					idPejabat = getParam("idPejabatJPPH");
	    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT JKT
			if ("openJKT".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJKT = new Vector();    			
	    			Hashtable hashMaklumatJKT = new Hashtable();
	    			hashMaklumatJKT.put("tarikhHantar", "");
	    			hashMaklumatJKT.put("jangkamasa", "");
	    			hashMaklumatJKT.put("tarikhJangkaTerima", "");

	    			beanMaklumatJKT.addElement(hashMaklumatJKT);
					this.context.put("BeanMaklumatJKT", beanMaklumatJKT);
				
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
					if ("".equals(submit)){
	    				
						beanMaklumatJKT = new Vector();    			
		    			Hashtable hashMaklumatJKT = new Hashtable();
		    			hashMaklumatJKT.put("tarikhHantar", "");
		    			hashMaklumatJKT.put("jangkamasa", "");
		    			hashMaklumatJKT.put("tarikhJangkaTerima", "");

		    			beanMaklumatJKT.addElement(hashMaklumatJKT);
						this.context.put("BeanMaklumatJKT", beanMaklumatJKT);
						
	    			} else {
	    				
	    				beanMaklumatJKT = new Vector();    			
		    			Hashtable hashMaklumatJKT = new Hashtable();
		    			hashMaklumatJKT.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJKT.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJKT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJKT.addElement(hashMaklumatJKT);
						this.context.put("BeanMaklumatJKT", beanMaklumatJKT);
						
	    			}
			
					logic.setMaklumatJKT(idUlasanTeknikal);					
					if (logic.getBeanMaklumatJKT().size() != 0){
						Hashtable hashMaklumatJKTDB = (Hashtable) logic.getBeanMaklumatJKT().get(0);
						idKementerianJKT = (String) hashMaklumatJKTDB.get("idKementerian");
						idAgensiJKT = (String) hashMaklumatJKTDB.get("idAgensi");
						idPejabatJKT = (String) hashMaklumatJKTDB.get("idPejabat");
					}
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJKT);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensiJKT);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJKT = new Vector();
					logic.setMaklumatJKT(idUlasanTeknikal);
					beanMaklumatJKT = logic.getBeanMaklumatJKT();
					this.context.put("BeanMaklumatJKT",beanMaklumatJKT);
					
					if (beanMaklumatJKT.size() != 0){
						Hashtable hashMaklumatJKT = (Hashtable) logic.getBeanMaklumatJKT().get(0);
						idKementerianJKT = (String) hashMaklumatJKT.get("idKementerian");
						idAgensiJKT = (String) hashMaklumatJKT.get("idAgensi");
						idPejabatJKT = (String) hashMaklumatJKT.get("idPejabat");
						flagStatus = (String) hashMaklumatJKT.get("flagStatus");
						aktif = (String) hashMaklumatJKT.get("aktif");
					}
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJKT);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensiJKT);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);				
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJKT = new Vector();    			
	    			Hashtable hashMaklumatJKT = new Hashtable();
	    			hashMaklumatJKT.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJKT.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJKT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatJKT.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJKT.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJKT.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJKT.put("ulasan", getParam("txtUlasan"));
	    			beanMaklumatJKT.addElement(hashMaklumatJKT);
					this.context.put("BeanMaklumatJKT", beanMaklumatJKT);
	    			
					logic.setMaklumatJKT(idUlasanTeknikal);					
					if (logic.getBeanMaklumatJKT().size() != 0){
						Hashtable hashMaklumatJKTDB = (Hashtable) logic.getBeanMaklumatJKT().get(0);
						idKementerianJKT = (String) hashMaklumatJKTDB.get("idKementerian");
						idAgensiJKT = (String) hashMaklumatJKTDB.get("idAgensi");
						idPejabatJKT = (String) hashMaklumatJKTDB.get("idPejabat");
					}
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJKT);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensiJKT);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT JH
			if ("openJH".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			idPejabat = logic.getIdPejabatJH(idHakmilik);
	    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
	    			
					if ("".equals(submit)){
						
						beanMaklumatJH = new Vector();    			
		    			Hashtable hashMaklumatJH = new Hashtable();
		    			hashMaklumatJH.put("tarikhHantar", "");
		    			hashMaklumatJH.put("jangkamasa", "");
		    			hashMaklumatJH.put("tarikhJangkaTerima", "");

		    			beanMaklumatJH.addElement(hashMaklumatJH);
						this.context.put("BeanMaklumatJH", beanMaklumatJH);
						
					} else {
						
						beanMaklumatJH = new Vector();    			
		    			Hashtable hashMaklumatJH = new Hashtable();
		    			hashMaklumatJH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJH.addElement(hashMaklumatJH);
						this.context.put("BeanMaklumatJH", beanMaklumatJH);
						
					}
				
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			idPejabat = logic.getIdPejabatJH(idHakmilik);
	    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
	    			
					if ("".equals(submit)){
						
						beanMaklumatJH = new Vector();    			
		    			Hashtable hashMaklumatJH = new Hashtable();
		    			hashMaklumatJH.put("tarikhHantar", "");
		    			hashMaklumatJH.put("jangkamasa", "");
		    			hashMaklumatJH.put("tarikhJangkaTerima", "");

		    			beanMaklumatJH.addElement(hashMaklumatJH);
						this.context.put("BeanMaklumatJH", beanMaklumatJH);
						
					} else {
						
						beanMaklumatJH = new Vector();    			
		    			Hashtable hashMaklumatJH = new Hashtable();
		    			hashMaklumatJH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJH.addElement(hashMaklumatJH);
						this.context.put("BeanMaklumatJH", beanMaklumatJH);
						
					}
				
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJH = new Vector();
					logic.setMaklumatJH(idUlasanTeknikal);
					beanMaklumatJH = logic.getBeanMaklumatJH();
					this.context.put("BeanMaklumatJH",beanMaklumatJH);
					
					if (beanMaklumatJH.size() != 0){
						Hashtable hashMaklumatJH = (Hashtable) logic.getBeanMaklumatJH().get(0);
						idPejabat = (String) hashMaklumatJH.get("idPejabat");
						flagStatus = (String) hashMaklumatJH.get("flagStatus");
						aktif = (String) hashMaklumatJH.get("aktif");
					}
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);					
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			idPejabat = getParam("idPejabatJH");
					
	    			beanMaklumatJH = new Vector();    			
	    			Hashtable hashMaklumatJH = new Hashtable();
	    			hashMaklumatJH.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJH.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatJH.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJH.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJH.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJH.put("ulasan", getParam("txtUlasan"));
	    			beanMaklumatJH.addElement(hashMaklumatJH);
					this.context.put("BeanMaklumatJH", beanMaklumatJH);
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);	
				}
			}
			
			//DOKUMEN JPPH
			senaraiJPPH = new Vector();
			logic.setSenaraiJPPH(idPermohonan);
			senaraiJPPH = logic.getListJPPH();
			this.context.put("SenaraiJPPH", senaraiJPPH);
			
			//DOKUMEN KJT
			senaraiKJT = new Vector();
			logic.setSenaraiKJT(idPermohonan);
			senaraiKJT = logic.getListKJT();
			this.context.put("SenaraiKJT", senaraiKJT);
			
			//DOKUMEN KJP
			senaraiKJP = new Vector();
			logic.setSenaraiKJP(idPermohonan);
			senaraiKJP = logic.getListKJP();
			this.context.put("SenaraiKJP", senaraiKJP);	
			
			//DOKUMEN JKT
			senaraiJKT = new Vector();
			logic.setSenaraiJKT(idPermohonan);
			senaraiJKT = logic.getListJKT();
			this.context.put("SenaraiJKT", senaraiJKT);
			
			//DOKUMEN JH
			senaraiJH = new Vector();
			logic.setSenaraiJH(idPermohonan);
			senaraiJH = logic.getListJH();
			this.context.put("SenaraiJH", senaraiJH);
			
			//LAMPIRAN ULASAN TEKNIKAL
			beanMaklumatLampiran = new Vector();
			beanMaklumatLampiran = logic.getBeanMaklumatLampiran(idUlasanTeknikal);
			this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
		}


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
        this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idStatus", idStatus);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idNegeriTanah", idNegeriTanah);
        this.context.put("idKementerianTanah", idKementerianTanah);
        this.context.put("idAgensiTanah", idAgensiTanah);
        this.context.put("idKementerianKJP", idKementerianKJP);
        this.context.put("idAgensiKJP", idAgensiKJP);
        this.context.put("idDokumen", idDokumen);
        this.context.put("flagStatus", flagStatus);
        this.context.put("aktif", aktif);
        this.context.put("flagPermohonanDari", flagPermohonanDari);
        this.context.put("idAgensi", idAgensi);
        this.context.put("idPejabat", idPejabat);
        this.context.put("idKementerianJKT", idKementerianJKT);
        this.context.put("idAgensiJKT", idAgensiJKT);
        this.context.put("idPejabatJKT", idPejabatJKT);
        this.context.put("step",step);       
        if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
        
		return vm;
	}
}
