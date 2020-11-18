package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmAPBSenaraiFailData;
import ekptg.model.php2.utiliti.LampiranBean;
//import ekptg.model.php2.FrmAPBSenaraiFailOnlineData;
import ekptg.model.utils.lampiran.ILampiran;

@SuppressWarnings("serial")
public class FrmAPBSenaraiFailOnlineView extends AjaxBasedModule {

	FrmAPBSenaraiFailData logic = new FrmAPBSenaraiFailData();
	private ILampiran iLampiran = null;
	FrmSemakan semak = null;
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	   	    	    
	    // DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idUrusan = getParam("idUrusan");
        String hitButton = getParam("hitButton");
        String noFailLama = getParam("noFailLama");
        String idPermohonanLama = getParam("idPermohonanLama");
        
        //VECTOR
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatPemohon = null;
        Vector senaraiSemak = null;

        String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
        
       //ACTION BUTTON
		if (postDB){
			this.context.put("noFailOnline", "");
        	if ("daftarBaru".equals(hitButton)){
        		logic.updateDaftarOnline(idFail,getParam("idPermohonan"),getParam("txtPerkara"),session);
        	}
        	if ("generateNoFailAPBOnline".equals(hitButton)){
        		String nofailAPBOnline=logic.generateNoFail(session);
        		this.context.put("noFailOnline", nofailAPBOnline);
        	}
			if("daftarSambung".equals(hitButton)){// set flag aktif = 0
				logic.simpanSambungan(idFail,getParam("idPermohonan"),getParam("tarikhTerima"),getParam("tarikhTerima"),noFailLama,idPermohonanLama, session);	
	    	}
    	}
		
		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phpapb"));
		        
        if ("papar".equals(actionOnline)){
        	
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	
        	vm = "app/php2/frmAPBDaftarOnline.jsp";
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan); 
				
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
        	
        }  else if ("daftar".equals(actionOnline)){
        	
           	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "disabled");
        	
        	vm = "app/php2/frmAPBDaftarOnline.jsp";
        	
        	// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			semak = new FrmSemakan();
			senaraiSemak = semak.getSenaraiSemakanAttach2("phpapb",idPermohonan);
			this.context.put("SenaraiSemak", senaraiSemak);
			
        } else {
	     	// GO TO LIST FAIL APB
			vm = "app/php2/frmAPBSenaraiFailOnline.jsp";
	
			//logic.getCarianFail(getParam("txtNoPermohonan"),getParam("txdTarikhPermohonan"));
			list = logic.getCarianFailOnline(getParam("txtNoPermohonan"),getParam("txtTarikhTerima"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
			this.context.put("txtTarikhTerima", getParam("txtTarikhTerima"));
			setupPage(session, action, list);
        }
		
		//SET DEFAULT ID PARAM
		this.context.put("actionOnline", actionOnline);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idUrusan", idUrusan);
	    
		return vm;
	}
	
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
				
	}
}
	
