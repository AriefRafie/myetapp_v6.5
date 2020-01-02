package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBKertasKerjaJawatankuasaData;


public class FrmAPBKertasKerjaJawatankuasaView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBKertasKerjaJawatankuasaData logic = new FrmAPBKertasKerjaJawatankuasaData();

	
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
	    String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
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
        String idKertasKerjaApb = getParam("idKertasKerjaApb");
		
        //VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatKawasanPermohonan = null;
		Vector beanMaklumatKertasRingkas = null;
		Vector senaraiKoordinat = null;
		Vector senaraiPertindihan = null;
		
		vm = "app/php2/frmAPBKertasKerjaJawatankuasa.jsp"; 
    	
    	if (postDB) {
			if ("doSimpanKemaskiniMaklumatKertasRingkas".equals(hitButton)){
				logic.updateMaklumatKertasRingkas(idPermohonan, getParam("txtUlasanJUPEM"), getParam("txtUlasanJPS"), getParam("txtUlasanJabGeoSains"),
						getParam("txtUlasanPusatHidrografi"), getParam("txtUlasanJabPerikanan"), getParam("txtJabLaut"), getParam("txtUlasanJabatanAlamSekitar"), 
						getParam("txtUlasanPTG"), getParam("txtNotaTambahan"), session);
			}
			
			//SUBMIT TO NEXT PROCESS
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
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 4); // 4 = CETAKAN KERTAS KERJA JAWATANKUASA
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		//MAKLUMAT PERMOHONAN
		logic.setMaklumatKawasanPermohonan(idPermohonan);
		beanMaklumatKawasanPermohonan = logic.getBeanMaklumatKawasanPermohonan();
		this.context.put("BeanMaklumatKawasanPermohonan", beanMaklumatKawasanPermohonan);
			
		//SENARAI TITIK KOORDINAT
		logic.setSenaraiKoordinat(idPermohonan);
		senaraiKoordinat = logic.getListKoordinat();
		this.context.put("SenaraiKoordinat", senaraiKoordinat);
		
		//SENARAI PERTINDIHAN
		logic.setSenaraiPertindihan(idPermohonan);
		senaraiPertindihan = logic.getListPertindihan();
		this.context.put("SenaraiPertindihan", senaraiPertindihan);
	
		// MODE VIEW
		if("view".equals(mode)){		
      	
        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
        	
        	//MAKLUMAT KERTAS RINGKAS
        	beanMaklumatKertasRingkas = new Vector();
        	logic.setMaklumatKertasRingkas(idPermohonan);
        	beanMaklumatKertasRingkas = logic.getBeanMaklumatKertasRingkas();
           	this.context.put("BeanMaklumatKertasRingkas", beanMaklumatKertasRingkas);
 	
		} else if("update".equals(mode)){ // MODE UPDATE
			
			this.context.put("readonly", "");
    		this.context.put("inputTextClass", "");
    		this.context.put("disabled", "");    	
    		
    		//MAKLUMAT KERTAS RINGKAS
        	beanMaklumatKertasRingkas = new Vector();
        	logic.setMaklumatKertasRingkas(idPermohonan);
        	beanMaklumatKertasRingkas = logic.getBeanMaklumatKertasRingkas();
           	this.context.put("BeanMaklumatKertasRingkas", beanMaklumatKertasRingkas);
		}		

		//SET DEFAULT PARAM
		this.context.put("mode", mode);	
		this.context.put("selectedTabUpper", selectedTabUpper);
		
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idStatus", idStatus);
	    this.context.put("idKertasKerjaApb", idKertasKerjaApb);
	   
		return vm;
	}
}

