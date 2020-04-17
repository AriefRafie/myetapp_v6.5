package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmCRBCetakSuratPeringatanData;
import ekptg.model.php2.FrmCRBHeaderData;

public class FrmCRBCetakSuratPeringatanView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBCetakSuratPeringatanData logic = new FrmCRBCetakSuratPeringatanData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
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
        String idStatus = getParam("idStatus");
        String idHakmilik = getParam("idHakmilik");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        String idPejabat = getParam("idPejabat");
        String idPenceroboh = getParam("idPenceroboh");
        
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiNotis = null;
        Vector beanMaklumatNotis = null;
        Vector senaraiPenceroboh = null;
        Vector beanMaklumatPenceroboh = null;
        Vector beanMaklumatPejabat = null;
        
        //GET DROPDOWN PARAM
        String idDokumen = getParam("socDokumen");
		if (idDokumen == null || idDokumen.trim().length() == 0){
			idDokumen = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0) {
			idBangsa = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
		
		boolean flagOpenDetail = false;
		String status = "";
		
		vm = "app/php2/frmCRBCetakSuratPeringatan.jsp";
		
		//HTBUTTON
		if (postDB) {
			if ("simpanMaklumatNotis".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatNotis(idPermohonan, idDokumen, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("simpanKemaskiniMaklumatNotis".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatNotis(idUlasanTeknikal, idDokumen, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("hapusMaklumatNotis".equals(hitButton)){
    			logic.hapusMaklumatNotis(idUlasanTeknikal);
    		}
        	if ("simpanPenceroboh".equals(hitButton)) {
				idPenceroboh = logic.savePenceroboh(idPermohonan, getParam("txtNama"),
						getParam("txtNoTelefon"), getParam("txtNoTelefonBimbit"), idBangsa,
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idNegeri, idBandar, getParam("txtEmel"), session);
			}
			if ("simpanKemaskiniPenceroboh".equals(hitButton)) {
				logic.updatePenceroboh(idPenceroboh, getParam("txtNama"),
						getParam("txtNoTelefon"), getParam("txtNoTelefonBimbit"), idBangsa,
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idNegeri, idBandar, getParam("txtEmel"), session);
			}
			if ("hapusMaklumatPenceroboh".equals(hitButton)) {
				logic.hapusMaklumatPenceroboh(idPenceroboh);
			}
			if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, session);          		
			}
		}
		
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String)hashHeader.get("idFail");
			idPermohonan = (String)hashHeader.get("idPermohonan");
			idStatus = (String)hashHeader.get("idStatus");
			status = (String) hashHeader.get("status");		
			idHakmilik = (String)hashHeader.get("idHakmilik");
		}
		
		//GET FLAG OPEN DETAIL
		flagOpenDetail =  logicHeader.getFlagOpenDetail(idStatus, 6); //6 = CETAKAN SURAT TINDAKAN
		
		//OPEN POPUP DETAIL MAKLUMAT NOTIS
		if ("openNotis".equals(flagPopup)){
			
			if ("new".equals(modePopup)){
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)){
    				
    				beanMaklumatNotis = new Vector();    			
	    			Hashtable hashMaklumatNotis = new Hashtable();
	    			hashMaklumatNotis.put("tarikhHantar", "");
	    			beanMaklumatNotis.addElement(hashMaklumatNotis);
					this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
					
					idDokumen = "99999";
					
    			} else {
    				
    				beanMaklumatNotis = new Vector();    			
	    			Hashtable hashMaklumatNotis = new Hashtable();
	    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			beanMaklumatNotis.addElement(hashMaklumatNotis);
					this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
    			}
    			
    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
    			
    			idPejabat = logic.getIdPejabatPTD(idHakmilik);
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
    			
			} else if ("view".equals(modePopup)){
							
				this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			beanMaklumatNotis = new Vector();
				logic.setMaklumatNotis(idUlasanTeknikal);
				beanMaklumatNotis = logic.getBeanMaklumatNotis();
				this.context.put("BeanMaklumatNotis",beanMaklumatNotis);
				
				if (beanMaklumatNotis.size() != 0){
					Hashtable hashMaklumatNotis = (Hashtable) logic.getBeanMaklumatNotis().get(0);
					idPejabat = (String) hashMaklumatNotis.get("idPejabat");
					idDokumen = (String) hashMaklumatNotis.get("idDokumen");
					flagStatus = (String) hashMaklumatNotis.get("flagStatus");
					flagAktif = (String) hashMaklumatNotis.get("flagAktif");
				}
				
				this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
			
			} else if ("update".equals(modePopup)){	
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			beanMaklumatNotis = new Vector();    			
    			Hashtable hashMaklumatNotis = new Hashtable();
    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
    			beanMaklumatNotis.addElement(hashMaklumatNotis);
				this.context.put("BeanMaklumatNotis", beanMaklumatNotis);	
				
				this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
    			
    			if ("9".equals(idDokumen)){
    				
    				idPejabat = logic.getIdPejabatPTD(idHakmilik);
    				
    				beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
    			}
			}			
		}
		
		//OPEN POPUP DETAIL MAKLUMAT PENCEROBOH
		if ("openPenceroboh".equals(flagPopup)){
			
			if ("new".equals(modePopup)){
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)) {

    				beanMaklumatPenceroboh = new Vector();
					Hashtable hashMaklumatPenceroboh = new Hashtable();
					hashMaklumatPenceroboh.put("nama", "");
					hashMaklumatPenceroboh.put("noTelefon", "");
					hashMaklumatPenceroboh.put("noTelefonBimbit","");
					hashMaklumatPenceroboh.put("alamat1", "");
					hashMaklumatPenceroboh.put("alamat2", "");
					hashMaklumatPenceroboh.put("alamat3", "");
					hashMaklumatPenceroboh.put("poskod", "");
					hashMaklumatPenceroboh.put("emel", "");
					beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
					this.context.put("BeanMaklumatPenceroboh", beanMaklumatPenceroboh);
					
					idBangsa = "99999";
					idNegeri = "99999";
					idBandar = "99999";
    							
				} else {

					beanMaklumatPenceroboh = new Vector();
					Hashtable hashMaklumatPenceroboh = new Hashtable();
					hashMaklumatPenceroboh.put("nama", getParam("txtNama"));
					hashMaklumatPenceroboh.put("noTelefon", getParam("txtNoTelefon"));
					hashMaklumatPenceroboh.put("noTelefonBimbit", getParam("txtNoTelefonBimbit"));
					hashMaklumatPenceroboh.put("alamat1", getParam("txtAlamat1"));
					hashMaklumatPenceroboh.put("alamat2", getParam("txtAlamat2"));
					hashMaklumatPenceroboh.put("alamat3", getParam("txtAlamat3"));
					hashMaklumatPenceroboh.put("poskod", getParam("txtPoskod"));
					hashMaklumatPenceroboh.put("emel", getParam("txtEmel"));
					beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
					this.context.put("BeanMaklumatPenceroboh", beanMaklumatPenceroboh);	
				}

				this.context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), ""));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri,"socBandar",Long.parseLong(idBandar),""));
    			
			} else if ("view".equals(modePopup)){
				
				this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			beanMaklumatPenceroboh = new Vector();
				logic.setMaklumatPenceroboh(idPenceroboh);
				beanMaklumatPenceroboh = logic.getBeanMaklumatPenceroboh();
				this.context.put("BeanMaklumatPenceroboh",beanMaklumatPenceroboh);

				if (beanMaklumatPenceroboh.size() != 0) {
					Hashtable hashMaklumatPenceroboh = (Hashtable) logic.getBeanMaklumatPenceroboh().get(0);
					idBangsa = (String) hashMaklumatPenceroboh.get("idBangsa");
					idNegeri = (String) hashMaklumatPenceroboh.get("idNegeri");
					idBandar = (String) hashMaklumatPenceroboh.get("idBandar");
				}
				
				this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa",Long.parseLong(idBangsa), "disabled", " class=\"disabled\""));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled"," class=\"disabled\""));
				this.context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri, "socBandar",	Long.parseLong(idBandar), "disabled"," class=\"disabled\""));
    			
			} else if ("update".equals(modePopup)){
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			beanMaklumatPenceroboh = new Vector();
				Hashtable hashMaklumatPenceroboh = new Hashtable();
				hashMaklumatPenceroboh.put("nama", getParam("txtNama"));
				hashMaklumatPenceroboh.put("noTelefon", getParam("txtNoTelefon"));
				hashMaklumatPenceroboh.put("noTelefonBimbit", getParam("txtNoTelefonBimbit"));
				hashMaklumatPenceroboh.put("alamat1", getParam("txtAlamat1"));
				hashMaklumatPenceroboh.put("alamat2", getParam("txtAlamat2"));
				hashMaklumatPenceroboh.put("alamat3", getParam("txtAlamat3"));
				hashMaklumatPenceroboh.put("poskod", getParam("txtPoskod"));
				hashMaklumatPenceroboh.put("emel", getParam("txtEmel"));
				beanMaklumatPenceroboh.addElement(hashMaklumatPenceroboh);
				this.context.put("BeanMaklumatPenceroboh", beanMaklumatPenceroboh);	

				this.context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), ""));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri,"socBandar",Long.parseLong(idBandar),""));
			}
		}
		
		//SENARAI NOTIS
		senaraiNotis = new Vector();
		logic.setSenaraiNotis(idPermohonan);
		senaraiNotis = logic.getListNotis();
		this.context.put("SenaraiNotis", senaraiNotis);
		
		//SENARAI PENCEROBOH
		senaraiPenceroboh = new Vector();
		logic.setSenaraiPenceroboh(idPermohonan);
		senaraiPenceroboh = logic.getListPenceroboh();
		this.context.put("SenaraiPenceroboh", senaraiPenceroboh);
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idHakmilik", idHakmilik);
        this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("idDokumen", idDokumen);
        this.context.put("idPejabat", idPejabat);
        this.context.put("idPenceroboh", idPenceroboh);
        
        this.context.put("flagOpenDetail", flagOpenDetail);
	    this.context.put("status", status.toUpperCase());
	    
		return vm;
	}
}
