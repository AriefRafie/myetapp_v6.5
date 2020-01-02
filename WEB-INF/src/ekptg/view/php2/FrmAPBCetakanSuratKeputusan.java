package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBCetakSuratKeputusanData;
import ekptg.model.php2.FrmAPBHeaderData;

public class FrmAPBCetakanSuratKeputusan extends AjaxBasedModule {

    private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBCetakSuratKeputusanData logic = new FrmAPBCetakSuratKeputusanData();
	
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
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }		
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        
        String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0) {
			idLuas = "99999";
		}
        
        //VECTOR
		Vector beanHeader = null;
		Vector beanKeputusanMenteri = null;
		Vector beanKelulusanDasar = null;
		
		String step = getParam("step");
        
    	vm = "app/php2/frmAPBCtkSrtTwrnKelulusanDasar.jsp"; 
    	
    	//SUBMIT TO NEXT PROCESS
    	if (postDB) {    	
    		if ("doSimpanKemaskini".equals(hitButton)){
				logic.updateMaklumatBayaranSblm(idPermohonan,getParam("txtLuasKawasan") ,idLuas,Utils.RemoveSymbol(getParam("txtFeeLesen")),
						Utils.RemoveSymbol(getParam("txtJumlahFeeLesen")),getParam("txtKmPersegi"),Utils.RemoveSymbol(getParam("txtWangCagaran")),Utils.RemoveSymbol(getParam("txtWangAmanah")), 
						Utils.RemoveSymbol(getParam("txtKadarRoyaltiPasir")),
						getParam("txtLuasNautika"), getParam("txtTarikhSurat"), getParam("txtTempohKelulusanDasar"), getParam("txtTarikhTamatKelulusanDasar"), 
						session);
			}
    		if ("doSimpanPerlanjutan".equals(hitButton)){
				logic.daftarPerlanjutanTempoh(idPermohonan, getParam("txtLuasKawasan") ,idLuas,Utils.RemoveSymbol(getParam("txtFeeLesen")),
						Utils.RemoveSymbol(getParam("txtJumlahFeeLesen")),getParam("txtKmPersegi"),Utils.RemoveSymbol(getParam("txtWangCagaran")),Utils.RemoveSymbol(getParam("txtWangAmanah")), 
						Utils.RemoveSymbol(getParam("txtKadarRoyaltiPasir")),
						getParam("txtLuasNautika"), getParam("txtTarikhSurat"), getParam("txtTempohKelulusanDasar"), getParam("txtTarikhTamatKelulusanDasar"), 
						session);
			}
			if ("doSimpanKutipanDataPerlanjutanTempoh".equals(hitButton)){
				logic.daftarKutipanDataPerlanjutanTempoh(idPermohonan, getParam("txtLuasKawasan") ,idLuas,Utils.RemoveSymbol(getParam("txtFeeLesen")),
						Utils.RemoveSymbol(getParam("txtJumlahFeeLesen")),getParam("txtKmPersegi"),Utils.RemoveSymbol(getParam("txtWangCagaran")),Utils.RemoveSymbol(getParam("txtWangAmanah")), 
						Utils.RemoveSymbol(getParam("txtKadarRoyaltiPasir")),
						getParam("txtLuasNautika"), getParam("txtTarikhSurat"), getParam("txtTempohKelulusanDasar"), getParam("txtTarikhTamatKelulusanDasar"), 
						session);
			}
			
    		if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
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
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idPemohon = hashHeader.get("idPemohon").toString();
			idStatus = hashHeader.get("idStatus").toString();	
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 8); // 8 = KEPUTUSAN PERTIMBANGAN DASAR
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		//MAKLUMAT KEPUTUSAN MENTERI
	    beanKeputusanMenteri = new Vector();
		logic.setMaklumatKeputusanMenteri(idPermohonan);
		beanKeputusanMenteri = logic.getBeanKeputusanMenteri();
		this.context.put("BeanKeputusanMenteri", beanKeputusanMenteri);
		
		//SEJARAH PERLANJUTAN TEMPOH KELULUSAN DASAR
		Vector senaraiPerlanjutanTempoh = logic.getSenaraiPerlanjutanTempoh(idPermohonan);
		this.context.put("SenaraiPerlanjutanTempoh", senaraiPerlanjutanTempoh);
		
		// MODE VIEW
		if("view".equals(mode)){
			
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			//MAKLUMAT BAYARAN SEBELUM PENGELUARAN LESEN
			beanKelulusanDasar = new Vector();
	    	logic.setMaklumatKelulusanDasar(idPermohonan);
	    	beanKelulusanDasar = logic.getBeanKelulusanDasar();
	       	this.context.put("BeanKelulusanDasar", beanKelulusanDasar);       
        	if (beanKelulusanDasar.size() != 0){
				Hashtable hashMaklumatDB = (Hashtable) logic.getBeanKelulusanDasar().get(0);
				this.context.put("selectLuas", HTML.SelectLuasAPB("socLuas", Long.parseLong((String) hashMaklumatDB.get("idLuas")), "disabled", " style=\"width:200px\" class=\"disabled\""));
			}
		} else if("update".equals(mode) || "daftarPerlanjutanTempoh".equals(mode) || "kutipanDataPerlanjutanTempoh".equals(mode)){
			
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");			
			
    		//MAKLUMAT BAYARAN SEBELUM PENGELUARAN LESEN
			beanKelulusanDasar = new Vector();
	    	logic.setMaklumatKelulusanDasar(idPermohonan);
	    	beanKelulusanDasar = logic.getBeanKelulusanDasar();
	       	this.context.put("BeanKelulusanDasar", beanKelulusanDasar);       
        	if (beanKelulusanDasar.size() != 0){
				Hashtable hashMaklumatDB = (Hashtable) logic.getBeanKelulusanDasar().get(0);
				this.context.put("selectLuas", HTML.SelectLuasAPB("socLuas", Long.parseLong((String) hashMaklumatDB.get("idLuas")), "", " style=\"width:200px\" onchange=kiraBatuNautika();kiraJumlahFeeLesen()"));
			}
		}	
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
        
		//SET DEFAULT PARAM
		this.context.put("mode", mode);	
		
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idStatus", idStatus);
	    this.context.put("command", getParam("command"));
	    this.context.put("action", getParam("action"));
 
		return vm;
	}
}
