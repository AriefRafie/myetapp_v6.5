package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBPembaruanLesenData;

public class FrmAPBPembaruanLesenView extends  AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmAPBPembaruanLesenView.class);
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBPembaruanLesenData logic = new FrmAPBPembaruanLesenData();

	
	@Override
	public String doTemplate2() throws Exception {
	HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String vm = "";  
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");    
        
        myLogger.info("mode :: "+mode);
        myLogger.info("hitButton :: "+hitButton);
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idStatus = getParam("idStatus");
        String idHakmilik = getParam("idHakmilik");
        String flagAktif = getParam("flagAktif");
               
        //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatPendaftaranPembaharuan = null;
		Vector listSambungan = null;
		Vector beanMaklumatPembaharuan = null;
	        
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		//DAFTAR SAMBUNGAN
		if(postDB){			
			if("doSimpan".equals(hitButton)){// set flag aktif = 0
				logic.simpanSambungan(idFail,idPemohon,idPermohonan,getParam("tarikhMula"), getParam("tarikhSurat"), session);	
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
			//flag Sambung untuk cek sama ada dah pernah daftar untuk sambungan atau belum.
			//if flag Sambung = null then belum buat.ifflag Sambung  = 0 dah buat sambungan
			flagAktif = (String)hashHeader.get("flagAktif");
		}
		
		//MODE
		if (mode.isEmpty()){
			if("0".equals(flagAktif)){
				mode = "view";
			}
			else
				mode = "new";
		}
		
        vm = "app/php2/frmAPBPembaruanLesen.jsp";
    	
        // dah buat permohonan sambungan. flag aktif = 0
        if("view".equals(mode)){
       
        	this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
						
			//MAKLUMAT PERMOHONAN PERJANJIAN SAMBUNGAN FROM DB
			beanMaklumatPembaharuan = new Vector();
			logic.setMaklumatSambungan(idFail);
			beanMaklumatPembaharuan = logic.getBeanMaklumatSambungan();
			this.context.put("BeanMaklumatPembaharuan", beanMaklumatPembaharuan);

        }
        // baru nk buat sambungan. flag aktif = null
        else if("new".equals(mode)){
        	
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN PERJANJIAN SAMBUNGAN NEW REKOD
        	beanMaklumatPendaftaranPembaharuan = new Vector();
			Hashtable hashSambung = new Hashtable();
			hashSambung.put("tarikhMula", getParam("tarikhMula") == null ? "" : sdf.format(currentDate));
			hashSambung.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
			hashSambung.put("perkara", getParam("perkara") == null ? "" : getParam("perkara"));
			beanMaklumatPendaftaranPembaharuan.addElement(hashSambung);
			this.context.put("BeanMaklumatPendaftaranPembaharuan", beanMaklumatPendaftaranPembaharuan);
        }
        
        //SET DEFAULT PARAM
	    this.context.put("mode", mode);
		
		//SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemohon", idPemohon);
        this.context.put("idStatus", idStatus);
        this.context.put("idHakmilik", idHakmilik);
        this.context.put("flagAktif", flagAktif);
        
        return vm;
	}

}
