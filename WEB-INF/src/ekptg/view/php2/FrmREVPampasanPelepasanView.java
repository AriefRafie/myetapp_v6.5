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
import lebah.util.Util;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmREVPampasanPelepasanData;

public class FrmREVPampasanPelepasanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmREVPampasanPelepasanData logic = new FrmREVPampasanPelepasanData();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
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
        String actionHasil = getParam("actionHasil");
	    String hitButton = getParam("hitButton");
        String submit = getParam("command");   
        String mode = getParam("mode");   
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
        
        //GET ID PARAM
        String idHasil = getParam("idHasil");
        String idFail = getParam("idFail");
        String idPemohon = getParam("idPemohon");
        String idAkaun = getParam("idAkaun");
                
        //GET DROPDOWN PARAM        
        String idCaraBayaran = getParam("socCaraBayaran");
		if (idCaraBayaran == null || idCaraBayaran.trim().length() == 0){
			idCaraBayaran = "99999";
		}
		String idBank = getParam("socBank");
		if (idBank == null || idBank.trim().length() == 0){
			idBank = "99999";
		}
		String idJenisPelarasan = getParam("socJenisPelarasan");
		if (idJenisPelarasan == null || idJenisPelarasan.trim().length() == 0){
			idJenisPelarasan = "99999";
		}
		
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}
        
        //VECTOR
        Vector list = null;
        Vector beanHeader = null;
        Vector beanMaklumatPemohon = null;
        Vector beanMaklumatBayaran = null;
        Vector beanMaklumatPelarasan = null;
        
        if (postDB){
        	if ("doSimpanKemaskiniPemohon".equals(hitButton)){
        		logic.updatePemohon(idPemohon,
						getParam("txtNama"), getParam("txtNoPendaftaran"),
						getParam("txtAlamat1"), getParam("txtAlamat2"),
						getParam("txtAlamat3"), getParam("txtPoskod"),
						idBandar, idNegeri, getParam("txtEmel"),
						getParam("txtNoTel"), getParam("txtNoFaks"),
						getParam("txtNoRujukan"), session);
        	}
    		if ("simpanBayaran".equals(hitButton)){
         		idAkaun = logic.simpanBayaran(idHasil, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), 
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), session);
        	}
    		if ("simpanKemaskiniBayaran".equals(hitButton)){
    			logic.simpanKemaskiniBayaran(idAkaun, getParam("txtTarikh"), idCaraBayaran, idBank, getParam("txtNoRujukan"), 
     				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtNoResit"), getParam("txtTarikhResit"), getParam("txtButiran"), session);
    		}
    		if ("hapusBayaran".equals(hitButton)){
         		logic.hapusBayaran(idAkaun, session);
        	}		
    		if ("simpanPelarasan".equals(hitButton)){
         		idAkaun = logic.simpanPelarasan(idHasil, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"), session);
        	}
    		if ("simpanKemaskiniPelarasan".equals(hitButton)){
         		logic.simpanKemaskiniPelarasan(idAkaun, getParam("txtTarikh"), idJenisPelarasan, getParam("txtNoRujukan"),
         				Utils.RemoveSymbol(getParam("txtAmaun")), getParam("txtButiran"), session);
        	}
    		if ("hapusPelarasan".equals(hitButton)){
         		logic.hapusPelarasan(idAkaun, session);
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
			idPemohon = (String) hashPermohonan.get("idPemohon");			
		}
        
        if ("papar".equals(actionHasil)){
        	
        	vm = "app/php2/frmREVMaklumatPampasanPelepasan.jsp";
        	
        	//MAKLUMAT AKAUN
        	if ("0".equals(selectedTabUpper)){
        		
        		if ("newBayaran".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			beanMaklumatBayaran.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);
                	
                	this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
                	this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));

                	
        		} else if ("viewBayaran".equals(mode)){
        			
                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");
                	
                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
                    logic.setMaklumatBayaran(idAkaun);
                    beanMaklumatBayaran = logic.getBeanMaklumatBayaran();
            		this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);
                	
            		if (beanMaklumatBayaran.size() != 0){
            			Hashtable hashBayaranDB = (Hashtable) logic.getBeanMaklumatBayaran().get(0);
            			idCaraBayaran = (String) hashBayaranDB.get("idCaraBayar");
            			idBank = (String) hashBayaranDB.get("idBank");
            			this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "disabled", " class=\"disabled\""));
            			this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "disabled", " class=\"disabled\""));
            		}
        			
        		} else if ("updateBayaran".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT BAYARAN
                	beanMaklumatBayaran = new Vector();
        			Hashtable hashBayaran = new Hashtable();
        			hashBayaran.put("tarikh", getParam("txtTarikh"));
        			hashBayaran.put("noRujukan", getParam("txtNoRujukan"));
        			hashBayaran.put("amaun", getParam("txtAmaun"));
        			hashBayaran.put("noResit", getParam("txtNoResit"));
        			hashBayaran.put("tarikhResit", getParam("txtTarikhResit"));
        			hashBayaran.put("butiran", getParam("txtButiran"));
        			beanMaklumatBayaran.addElement(hashBayaran);
        			this.context.put("BeanMaklumatBayaran", beanMaklumatBayaran);
                	
            		this.context.put("selectCaraBayaran",HTML.selectCaraBayaran("socCaraBayaran", Long.parseLong(idCaraBayaran), "", "onChange=\"doChangeCaraBayar();\""));
            		this.context.put("selectBank",HTML.selectBank("socBank", Long.parseLong(idBank), "", ""));

        		}
        		
        		if ("newPelarasan".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasan = new Vector();
        			Hashtable hashPelarasan = new Hashtable();
        			hashPelarasan.put("tarikh", getParam("txtTarikh") == null || getParam("txtTarikh") == "" ? sdf.format(new Date()) : getParam("txtTarikh"));
        			hashPelarasan.put("noRujukan", getParam("txtNoRujukan"));
        			hashPelarasan.put("amaun", getParam("txtAmaun"));
        			hashPelarasan.put("butiran", getParam("txtButiran"));
        			beanMaklumatPelarasan.addElement(hashPelarasan);
        			this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);
                	
        			if ("D".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				
        			} else if ("K".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				
        			} else {
        				
        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				
        			}
                	
        		} else if ("viewPelarasan".equals(mode)){
        			
                	this.context.put("readonly", "readonly");
                	this.context.put("inputTextClass", "disabled");
                	
                	//MAKLUMAT PELARASAN
                	beanMaklumatPelarasan = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasan = logic.getBeanMaklumatPelarasan();
            		this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);
                	
            		if (beanMaklumatPelarasan.size() != 0){
            			Hashtable hashPelarasanB = (Hashtable) logic.getBeanMaklumatPelarasan().get(0);
            			idJenisPelarasan = (String) hashPelarasanB.get("idJenisPelarasan");
            		}        	
            		
            		if ("D".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				
        			} else if ("K".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				
        			} else {
        				
        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				
        			}
        			
        		} else if ("updatePelarasan".equals(mode)){
        			
                	this.context.put("readonly", "");
                	this.context.put("inputTextClass", "");

                	beanMaklumatPelarasan = new Vector();
                    logic.setMaklumatPelarasan(idAkaun);
                    beanMaklumatPelarasan = logic.getBeanMaklumatPelarasan();
            		this.context.put("BeanMaklumatPelarasan", beanMaklumatPelarasan);
                	
            		if ("D".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "selected");
        				this.context.put("selectedK", "");
        				
        			} else if ("K".equals(idJenisPelarasan)){
        				
        				this.context.put("selected", "");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "selected");
        				
        			} else {
        				
        				this.context.put("selected", "selected");
        				this.context.put("selectedD", "");
        				this.context.put("selectedK", "");
        				
        			} 
        		}

        		senaraiAkaun(idHasil);
        	}
        	
        	//MAKLUMAT PEMOHON
        	if ("1".equals(selectedTabUpper)){
        		
        		if ("view".equals(mode)){
        			
        			this.context.put("readonly", "readonly");
        			this.context.put("inputTextClass", "disabled");
        			this.context.put("disabled", "disabled");
        			
        			// MAKLUMAT PEMOHON
        			logic.setMaklumatPemohon(idHasil);
        			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
        			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
        			if (logic.getBeanMaklumatPemohon().size() != 0){
        				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
        				idPemohon = (String) hashPemohon.get("idPemohon");
        				idNegeri = (String) hashPemohon.get("idNegeri");
        				idBandar = (String) hashPemohon.get("idBandar");
        			}

        			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
        			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
        			
        		} else if ("update".equals(mode)){
        			
        			this.context.put("readonly", "");
            		this.context.put("inputTextClass", "");
            		this.context.put("disabled", "");
            		
            		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
        			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
        			
        			//MAKLUMAT PEMOHON
            		beanMaklumatPemohon = new Vector();
        			Hashtable hashPemohon = new Hashtable();
        			hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
        			hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
        			hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
        			hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
        			hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
        			hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
        			hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
        			hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
        			hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
        			hashPemohon.put("noRujukan", getParam("txtNoRujukan") == null ? "": getParam("txtNoRujukan"));
        			beanMaklumatPemohon.addElement(hashPemohon);
        			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
            		
        		}
        	}        	
        	
        } else {
        	
        	vm = "app/php2/frmREVSenaraiPampasanPelepasan.jsp";
        	
        	// DROP DOWN CARIAN
    		String jenisHakmilik = getParam("socJenisHakmilik");
    		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
    			jenisHakmilik = "99999";
    		}
    		String jenisLot = getParam("socJenisLot");
    		if (jenisLot == null || jenisLot.trim().length() == 0){
    			jenisLot = "99999";
    		}
    		String idNegeriC = getParam("socNegeriC");
    		if (idNegeriC == null || idNegeriC.trim().length() == 0){
    			idNegeriC = "99999";
    		}
    		String idDaerahC = getParam("socDaerahC");
    		if (idDaerahC == null || idDaerahC.trim().length() == 0){
    			idDaerahC = "99999";
    		}
    		String idMukimC = getParam("socMukimC");
    		if (idMukimC == null || idMukimC.trim().length() == 0){
    			idMukimC = "99999";
    		}    		
    		String idStatusC = getParam("socStatusC");
    		if (idStatusC == null || idStatusC.trim().length() == 0){
    			idStatusC = "99999";
    		} 
    		String flagDetail = getParam("flagDetail");
        	
    		list = new Vector();
        	logic.carianFail(getParam("txtNoFail"),getParam("txtNamaPemohon"),getParam("txtNoRujukan"),idNegeriC,idDaerahC,idMukimC,
        			jenisHakmilik,getParam("txtNoHakmilik"),getParam("txtNoWarta"),jenisLot,getParam("txtNoLot"),getParam("txtNoPegangan"), getParam("txtNoRujukanPenyewa"));
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNamaPemohon", getParam("txtNamaPemohon"));
			this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
        	this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
        	this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("selectNegeriC",HTML.SelectNegeri("socNegeriC", Long.parseLong(idNegeriC), "", " onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
			this.context.put("flagDetail", flagDetail);
			
			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionHasil", actionHasil);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("mode", mode);
		
		//SET DEFAULT ID PARAM
		this.context.put("idHasil", idHasil);
		this.context.put("idFail", idFail);
		this.context.put("idAkaun", idAkaun);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idCaraBayaran", idCaraBayaran);
		this.context.put("idNegeri", idNegeri);
		this.context.put("idBandar", idBandar);
        
		return vm;
	}
	
	private void senaraiAkaun(String idHasil) throws Exception {
		Vector senaraiAkaun = new Vector();
        logic.setListAkaun(idHasil);
        senaraiAkaun = logic.getSenaraiAkaun();
		this.context.put("SenaraiAkaun", senaraiAkaun);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic.calculateTotal(idHasil);
		
		if (total > 0D){
			this.context.put("total", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("total", "(" + Util.formatDecimal(total * -1) + ")");
		} else {
			this.context.put("total", "0.00");
		}		
	}
	
	public void setupPage(HttpSession session,String action,Vector list) {
		
		try {
		
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiFail",paging.getPage(page));
			    this.context.put("page", new Integer(page));
			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
}
