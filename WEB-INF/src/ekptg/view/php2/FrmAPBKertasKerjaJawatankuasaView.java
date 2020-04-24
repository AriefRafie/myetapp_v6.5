package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBKertasKerjaJawatankuasaData;
import ekptg.model.php2.FrmAPBJabatanTeknikalData;

public class FrmAPBKertasKerjaJawatankuasaView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBKertasKerjaJawatankuasaData logic = new FrmAPBKertasKerjaJawatankuasaData();
	FrmAPBJabatanTeknikalData doc = new FrmAPBJabatanTeknikalData();
	
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
	
		//
		
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
           	
        	// RETRIEVE MAKLUMAT DOKUMEN JUPEM
        	Vector senaraiJUPEM = new Vector();
    		doc.setSenaraiJUPEM(idPermohonan);
    		senaraiJUPEM = doc.getListJUPEM();
    		this.context.put("SenaraiJUPEM", senaraiJUPEM);
    		if (senaraiJUPEM.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJUPEM().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenJUPEM = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenJUPEM",beanMaklumatDokumenJUPEM);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN JPS
        	Vector senaraiJPS = new Vector();
    		doc.setSenaraiJPS(idPermohonan);
    		senaraiJPS = doc.getListJPS();
    		this.context.put("SenaraiJPS", senaraiJPS);
    		if (senaraiJPS.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJPS().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenJPS = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenJPS",beanMaklumatDokumenJPS);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN JMG
        	Vector senaraiJMG = new Vector();
    		doc.setSenaraiJMG(idPermohonan);
    		senaraiJMG = doc.getListJMG();
    		this.context.put("SenaraiJMG", senaraiJMG);
    		if (senaraiJMG.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJMG().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenJMG = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenJMG",beanMaklumatDokumenJMG);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN PHN
        	Vector senaraiPHN = new Vector();
    		doc.setSenaraiPHM(idPermohonan);
    		senaraiPHN = doc.getListPHM();
    		this.context.put("SenaraiPHN", senaraiPHN);
    		if (senaraiPHN.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListPHM().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenPHN = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenPHN",beanMaklumatDokumenPHN);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN DOF
        	Vector senaraiDOF = new Vector();
    		doc.setSenaraiJP(idPermohonan);
    		senaraiDOF = doc.getListJP();
    		this.context.put("SenaraiDOF", senaraiDOF);
    		if (senaraiDOF.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJP().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenDOF = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenDOF",beanMaklumatDokumenDOF);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN JLM
        	Vector senaraiJLM = new Vector();
    		doc.setSenaraiJLM(idPermohonan);
    		senaraiJLM = doc.getListJLM();
    		this.context.put("SenaraiJLM", senaraiJLM);
    		if (senaraiJLM.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJLM().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenJLM = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenJLM",beanMaklumatDokumenJLM);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN JAS
        	Vector senaraiJAS = new Vector();
    		doc.setSenaraiJAS(idPermohonan);
    		senaraiJAS = doc.getListJAS();
    		this.context.put("SenaraiJAS", senaraiJAS);
    		if (senaraiJAS.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListJAS().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenJAS = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenJAS",beanMaklumatDokumenJAS);
    		}
    		// RETRIEVE MAKLUMAT DOKUMEN PTG
        	Vector senaraiPTG = new Vector();
    		doc.setSenaraiPTG(idPermohonan);
    		senaraiPTG = doc.getListPTG();
    		this.context.put("SenaraiPTG", senaraiPTG);
    		if (senaraiPTG.size() != 0){
    			String idUlasanTeknikal="";
    			Hashtable hashHeader = (Hashtable) doc.getListPTG().get(0);
    			idUlasanTeknikal = hashHeader.get("idUlasanTeknikal").toString();
    			doc.setMaklumatDokumen(idUlasanTeknikal);
    			Vector beanMaklumatDokumenPTG = doc.getBeanMaklumatDokumen();
    			this.context.put("BeanMaklumatDokumenPTG",beanMaklumatDokumenPTG);
    		}
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

