package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBCetakSuratMohonByrSblmLesenKeluarData;
import ekptg.model.php2.FrmAPBHeaderData;


public class FrmAPBCetakSuratMohonByrSblmLesenKeluar extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBCetakSuratMohonByrSblmLesenKeluarData logic = new FrmAPBCetakSuratMohonByrSblmLesenKeluarData();

	
	//@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String vm = ""; 
        String hitButton = getParam("hitButton");
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
		this.context.put("completed",false);
		
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0) {
			idLuas = "99999";
		}
		
        //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatBayaranSblm = null;
		
		String step = getParam("step");
		
		vm = "app/php2/frmAPBCetakSuratMohonBayaranSblmLesenKeluar.jsp"; 
		
		if (postDB) {
			if ("doSimpanKemaskiniBayaranSblm".equals(hitButton)){
				logic.updateMaklumatBayaranSblm(idPermohonan,getParam("txtUlasanEIA"),getParam("txtUlasanHidro") ,getParam("txtLuasKawasan") ,idLuas,
						Utils.RemoveSymbol(getParam("txtFeeLesen")),Utils.RemoveSymbol(getParam("txtJumlahFeeLesen")),getParam("txtKmPersegi"),Utils.RemoveSymbol(getParam("txtWangCagaran")),Utils.RemoveSymbol(getParam("txtWangAmanah")), 
						getParam("txtMaklumatTambahan"),getParam("txtIsipadu"),Utils.RemoveSymbol(getParam("txtKadarRoyaltiPasir")),
						Utils.RemoveSymbol(getParam("txtJumlahRoyaltiSeluruh")),Utils.RemoveSymbol(getParam("txtJumDahuluRoyalti")),getParam("txtSyaratKelulusan"),session);
			}
			if ("doSeterusnya".equals(hitButton)){
				logic.updateStatus(idFail, idPermohonan, Utils.RemoveSymbol(getParam("txtJumlahFeeLesen")), session);            		
	    	}
			if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
			
    	}//END POSTDB 
		
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
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 10); // 10 = CETAKAN SURAT MOHON BAYARAN SEBELUM PENGELUARAN LESEN
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		// MODE VIEW
		if("view".equals(mode)){				
      	
        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
        	
			//MAKLUMAT BAYARAN SEBELUM PENGELUARAN LESEN
			beanMaklumatBayaranSblm = new Vector();
	    	logic.setMaklumatBayaranSblm(idPermohonan);
	    	beanMaklumatBayaranSblm = logic.getBeanMaklumatBayaranSblm();
	       	this.context.put("BeanMaklumatBayaranSblm", beanMaklumatBayaranSblm);       
        	if (beanMaklumatBayaranSblm.size() != 0){
				Hashtable hashMaklumatBayaranSblm = (Hashtable) logic.getBeanMaklumatBayaranSblm().get(0);
				this.context.put("selectLuas", HTML.SelectLuasAPB("socLuas", Long.parseLong((String) hashMaklumatBayaranSblm.get("idLuas")), "disabled", " style=\"width:250px\" class=\"disabled\""));
			}
		}

		// MODE UPDATE
		else if("update".equals(mode)){
			
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");			
			
        	//MAKLUMAT BAYARAN SEBELUM PENGELUARAN LESEN
			beanMaklumatBayaranSblm = new Vector();
	    	logic.setMaklumatBayaranSblm(idPermohonan);
	    	beanMaklumatBayaranSblm = logic.getBeanMaklumatBayaranSblm();
	       	this.context.put("BeanMaklumatBayaranSblm", beanMaklumatBayaranSblm);      
        	if (beanMaklumatBayaranSblm.size() != 0){
				Hashtable hashMaklumatBayaranSblm = (Hashtable) logic.getBeanMaklumatBayaranSblm().get(0);
				this.context.put("selectLuas", HTML.SelectLuasAPB("socLuas", Long.parseLong((String) hashMaklumatBayaranSblm.get("idLuas")), "", " style=\"width:250px\" onchange=kiraJumlahFeeLesen()"));
			}
		}		
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
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
		
		return vm;
	}	
}

