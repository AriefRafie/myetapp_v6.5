package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmCRBCetakSuratTindakanData;
import ekptg.model.php2.FrmCRBHeaderData;

public class FrmCRBCetakSuratTindakanView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBCetakSuratTindakanData logic = new FrmCRBCetakSuratTindakanData();

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
		
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idHakmilik = getParam("idHakmilik");
        String idHakmilikPermohonan = getParam("idHakmilikPermohonan");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        
        String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        String idPejabat = getParam("idPejabat");
        
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiSuratTindakan = null;
        Vector beanMaklumatSuratTindakan = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiSuratPanggilanOperasi = null;
        Vector beanMaklumatSuratPanggilanOperasi = null;
        
        boolean flagOpenDetail = false;
		String status = "";
		
		String step = getParam("step");
        
		vm = "app/php2/frmCRBCetakSuratTindakan.jsp";
		
		//HTBUTTON
		if (postDB) {
			if ("simpanMaklumatSuratTindakan".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatSuratTindakan(idPermohonan, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("simpanKemaskiniMaklumatSuratTindakan".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatSuratTindakan(idUlasanTeknikal, getParam("txtTarikhHantar"), session);
    		}
        	if ("hapusMaklumatSuratTindakan".equals(hitButton)){
    			logic.hapusMaklumatSuratTindakan(idUlasanTeknikal, session);
    		}
        	if ("simpanMaklumatSuratPanggilanOperasi".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatSuratPanggilanOperasi(idPermohonan, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("simpanKemaskiniMaklumatSuratPanggilanOperasi".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatSuratPanggilanOperasi(idUlasanTeknikal, getParam("txtTarikhHantar"), session);
    		}
        	if ("hapusMaklumatSuratPanggilanOperasi".equals(hitButton)){
    			logic.hapusMaklumatSuratPanggilanOperasi(idUlasanTeknikal, session);
    		}
			if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, idHakmilikPermohonan, session);          		
			}
			
			if ("doSelesaiPermohonan".equals(hitButton)){
				logicHeader.doSelesaiPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhSelesai"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
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
			idHakmilikPermohonan = (String)hashHeader.get("idHakmilikPermohonan");
		}
		
		//GET FLAG OPEN DETAIL
		flagOpenDetail =  logicHeader.getFlagOpenDetail(idStatus, 6); //6 = CETAKAN SURAT TINDAKAN
		
		//OPEN POPUP DETAIL MAKLUMAT SURAT TINDAKAN
		if ("openSuratTindakan".equals(flagPopup)){
			
			if ("new".equals(modePopup)){
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)) {

    				beanMaklumatSuratTindakan = new Vector();    			
        			Hashtable hashMaklumatSuratTindakan = new Hashtable();
        			hashMaklumatSuratTindakan.put("tarikhHantar", "");
        			beanMaklumatSuratTindakan.addElement(hashMaklumatSuratTindakan);
    				this.context.put("BeanMaklumatSuratTindakan", beanMaklumatSuratTindakan);
			
				} else {
	    			beanMaklumatSuratTindakan = new Vector();    			
	    			Hashtable hashMaklumatSuratTindakan = new Hashtable();
	    			hashMaklumatSuratTindakan.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			beanMaklumatSuratTindakan.addElement(hashMaklumatSuratTindakan);
					this.context.put("BeanMaklumatSuratTindakan", beanMaklumatSuratTindakan);
				}
    			idPejabat = logic.getIdPejabatPTD(idHakmilik);
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
    			
			} else if ("view".equals(modePopup)){
							
				this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			beanMaklumatSuratTindakan = new Vector();
				logic.setMaklumatSuratTindakan(idUlasanTeknikal);
				beanMaklumatSuratTindakan = logic.getBeanMaklumatSuratTindakan();
				this.context.put("BeanMaklumatSuratTindakan",beanMaklumatSuratTindakan);
				
				if (beanMaklumatSuratTindakan.size() != 0){
					Hashtable hashMaklumatSuratTindakan = (Hashtable) logic.getBeanMaklumatSuratTindakan().get(0);
					idPejabat = (String) hashMaklumatSuratTindakan.get("idPejabat");
					flagStatus = (String) hashMaklumatSuratTindakan.get("flagStatus");
					flagAktif = (String) hashMaklumatSuratTindakan.get("flagAktif");
				}
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
			
			} else if ("update".equals(modePopup)){	
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			beanMaklumatSuratTindakan = new Vector();    			
    			Hashtable hashMaklumatSuratTindakan = new Hashtable();
    			hashMaklumatSuratTindakan.put("tarikhHantar", getParam("txtTarikhHantar"));
    			beanMaklumatSuratTindakan.addElement(hashMaklumatSuratTindakan);
				this.context.put("BeanMaklumatSuratTindakan", beanMaklumatSuratTindakan);	
				
				idPejabat = logic.getIdPejabatPTD(idHakmilik);
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
			}			
		}
		
		//OPEN POPUP DETAIL MAKLUMAT SURAT PANGGILAN OPERASI
		if ("openSuratPanggilanOperasi".equals(flagPopup)){
			
			if ("new".equals(modePopup)){
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			if ("".equals(submit)) {

    				beanMaklumatSuratPanggilanOperasi = new Vector();    			
        			Hashtable hashMaklumatSuratPanggilanOperasi = new Hashtable();
        			hashMaklumatSuratPanggilanOperasi.put("tarikhHantar", "");
        			beanMaklumatSuratPanggilanOperasi.addElement(hashMaklumatSuratPanggilanOperasi);
    				this.context.put("BeanMaklumatSuratPanggilanOperasi", beanMaklumatSuratPanggilanOperasi);
			
				} else {
	    			beanMaklumatSuratPanggilanOperasi = new Vector();    			
	    			Hashtable hashMaklumatSuratPanggilanOperasi = new Hashtable();
	    			hashMaklumatSuratPanggilanOperasi.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			beanMaklumatSuratPanggilanOperasi.addElement(hashMaklumatSuratPanggilanOperasi);
					this.context.put("BeanMaklumatSuratPanggilanOperasi", beanMaklumatSuratPanggilanOperasi);
				}
    			idPejabat = logic.getIdPejabatPTD(idHakmilik);
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
    			
			} else if ("view".equals(modePopup)){
							
				this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			this.context.put("disabled", "disabled");
    			
    			beanMaklumatSuratPanggilanOperasi = new Vector();
				logic.setMaklumatSuratPanggilanOperasi(idUlasanTeknikal);
				beanMaklumatSuratPanggilanOperasi = logic.getBeanMaklumatSuratPanggilanOperasi();
				this.context.put("BeanMaklumatSuratPanggilanOperasi",beanMaklumatSuratPanggilanOperasi);
				
				if (beanMaklumatSuratPanggilanOperasi.size() != 0){
					Hashtable hashMaklumatSuratPanggilanOperasi = (Hashtable) logic.getBeanMaklumatSuratPanggilanOperasi().get(0);
					idPejabat = (String) hashMaklumatSuratPanggilanOperasi.get("idPejabat");
					flagStatus = (String) hashMaklumatSuratPanggilanOperasi.get("flagStatus");
					flagAktif = (String) hashMaklumatSuratPanggilanOperasi.get("flagAktif");
				}
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
			
			} else if ("update".equals(modePopup)){	
				
				this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
    			this.context.put("disabled", "");
    			
    			beanMaklumatSuratPanggilanOperasi = new Vector();    			
    			Hashtable hashMaklumatSuratPanggilanOperasi = new Hashtable();
    			hashMaklumatSuratPanggilanOperasi.put("tarikhHantar", getParam("txtTarikhHantar"));
    			beanMaklumatSuratPanggilanOperasi.addElement(hashMaklumatSuratPanggilanOperasi);
				this.context.put("BeanMaklumatSuratPanggilanOperasi", beanMaklumatSuratPanggilanOperasi);	
				
				idPejabat = logic.getIdPejabatPTD(idHakmilik);
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabat(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
			}			
		}		
		
		//SENARAI SURAT TINDAKAN
		senaraiSuratTindakan = new Vector();
		logic.setSenaraiSuratTindakan(idPermohonan);
		senaraiSuratTindakan = logic.getListSuratTindakan();
		this.context.put("SenaraiSuratTindakan", senaraiSuratTindakan);
		
		//SENARAI SURAT PANGGILAN OPERASI
		senaraiSuratPanggilanOperasi = new Vector();
		logic.setSenaraiSuratPanggilanOperasi(idPermohonan);
		senaraiSuratPanggilanOperasi = logic.getListSuratPanggilanOperasi();
		this.context.put("SenaraiSuratPanggilanOperasi", senaraiSuratPanggilanOperasi);
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idHakmilik", idHakmilik);
        this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);
        this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("idPejabat", idPejabat);      
        this.context.put("selectedTabUpper", selectedTabUpper);        
        this.context.put("flagOpenDetail", flagOpenDetail);
	    this.context.put("status", status.toUpperCase());
	    
	    if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }

	    
		return vm;
	}
}
