package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanPerjanjianPajakanData;

public class FrmPajakanPerjanjianPajakanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmPajakanPerjanjianPajakanView.class);
	private final String PATH="app/htp/pajakan/";

	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanPerjanjianPajakanData logic = new FrmPajakanPerjanjianPajakanData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPajakan = getParam("actionPajakan"); // our main submit
		if (actionPajakan.isEmpty()){
	        actionPajakan = "papar";
	    }
		String submit = getParam("command"); // for doAjax only
		String mode = getParam("mode");
		if (mode.isEmpty()){
	       	mode = "view";
	    }
		String hitButton = getParam("hitButton");
		String selectedTab = getParam("selectedTab");
		if (selectedTab.equals("") || selectedTab.equals(null)) {
			selectedTab = "0";
		}

		// GET ID PARAM
		String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
		String subUrusan = getParam("subUrusan");
		String idDraf = getParam("idDraf");
		String idPajakan = getParam("idPajakan");

		// VECTOR
		Vector list = null;
		Vector beanHeader = null;

		//HITBUTTON
		if (postDB) {
			if ("seterusnya".equals(hitButton)){    			
    			logic.seterusnya(idFail, idPermohonan, subUrusan, session);
    		}
			if ("saveMOA".equals(hitButton)){    			
    			saveMOA(idPermohonan, session);
    		}
			if ("save15A".equals(hitButton)){    			
    			save15A(idPermohonan, session);
    		}
			if ("saveJM".equals(hitButton)){    			
				saveJM(idPermohonan, session);
    		}
			if ("saveDraf".equals(hitButton)){    			
    			saveDraf(idPermohonan, session);
    		}
    		if ("saveUpdateDraf".equals(hitButton)){    			
    			saveUpdateDraf(idDraf, session);
    		}
    		if ("hapusDraf".equals(hitButton)){ 
    			idDraf = getParam("idDraf");
    			logic.hapusDraf(idDraf);
    		}
    		if ("savePajakan".equals(hitButton)){    			
    			savePajakan(idPermohonan, session);
    		}
    		if ("saveUpdatePajakan".equals(hitButton)){    			
    			saveUpdatePajakan(idPajakan, session);
    		}
    		if ("hapusPajakan".equals(hitButton)){    			
    			logic.hapusPajakan(idPajakan);
    		}
		}

		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFailSession);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
		}

		if (actionPajakan.equalsIgnoreCase("papar")) {

			//vm = "app/htp/frmPajakanPerjanjianPajakan.jsp";
			vm = PATH+"frmPajakanPerjanjianPajakan.jsp";

			if (selectedTab.equals("0")){
        		        	
				Vector senaraiDraf = new Vector();
        		logic.setListDraf(idPermohonan);
        		senaraiDraf = logic.getSenaraiDraf();
        		this.context.put("SenaraiDraf", senaraiDraf);
        		
        		DrafView(mode, idDraf);
        		
        	} else if (selectedTab.equals("1")){  	
        		
        		Vector senaraiPajakan = new Vector();
        		logic.setListPajakan(idPermohonan);
        		senaraiPajakan = logic.getSenaraiPajakan();
        		this.context.put("SenaraiPajakan", senaraiPajakan);
        		
        		PajakanView(mode, idPajakan);
        		
        	} else if (selectedTab.equals("2")){
        		
        		logic.setMaklumatMOA(idPermohonan);
            	MOAView(mode);
            	
        	} else if (selectedTab.equals("3")){
        		
        		logic.setMaklumat15A(idPermohonan);
            	Borang15AView(mode);
        		
        	} else if (selectedTab.equals("4")){
        		
        		logic.setMaklumatTabKelulusanJemaahMenteri(idPermohonan);
				JemaahMenteriView(mode);
  
        	}

		}

		// SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		this.context.put("selectedTab", selectedTab);
		this.context.put("mode", mode);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("subUrusan", subUrusan);
		this.context.put("idDraf", idDraf);
		this.context.put("idPajakan", idPajakan);

		log.info(vm);
		return vm;
	}	

	public void MOAView(String mode) throws Exception{
    	try{
    		
    		Vector beanMaklumatMOA = null;
    		
    		if (mode.equalsIgnoreCase("view")){
    			
    			beanMaklumatMOA = new Vector();
    			beanMaklumatMOA = logic.getBeanMaklumatMOA();
        		this.context.put("BeanMaklumatMOA", beanMaklumatMOA);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	public void Borang15AView(String mode) throws Exception{
    	try{
    		
    		Vector beanMaklumat15A = null;
    		
    		if (mode.equalsIgnoreCase("view")){
    			
    			beanMaklumat15A = new Vector();
    			beanMaklumat15A = logic.getBeanMaklumat15A();
        		this.context.put("BeanMaklumat15A", beanMaklumat15A);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	public void JemaahMenteriView(String mode) throws Exception{
    	try{
    		
    		Vector beanMaklumatJM = null;
    		
    		if (mode.equalsIgnoreCase("view")){
    			
    			beanMaklumatJM = new Vector();
    			beanMaklumatJM = logic.getBeanMaklumatJemaahMenteri();
        		this.context.put("BeanMaklumatJM", beanMaklumatJM);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("update")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	public void DrafView(String mode, String idDraf) throws Exception{
    	try{
    		
    		Vector beanDraf = null;
    		
    		if (mode.equalsIgnoreCase("newDraf")){
    			
    			beanDraf = new Vector();
    			Hashtable hashDraf = new Hashtable();
    			hashDraf.put("tarikhHantar", getParam("txdTarikhHantarDraf") == null ? "" : getParam("txdTarikhHantarDraf"));
    			hashDraf.put("tarikhTerima", getParam("txdTarikhTerimaDraf") == null ? "" : getParam("txdTarikhTerimaDraf"));
    			hashDraf.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP") == null ? "" : getParam("txdTarikhHantarPKP"));
    			hashDraf.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP") == null ? "" : getParam("txdTarikhTerimaPKP"));
    			hashDraf.put("ulasan", getParam("txtKeteranganDraf") == null ? "" : getParam("txtKeteranganDraf"));
    			
    			beanDraf.addElement(hashDraf);
				this.context.put("BeanDraf", beanDraf);
				
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewDraf")){
    			
    			beanDraf = new Vector();
    			logic.setMaklumatDraf(idDraf);
    			beanDraf = logic.getBeanMaklumatDraf();
    			this.context.put("BeanDraf", beanDraf);
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updateDraf")){
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	public void PajakanView(String mode, String idPajakan) throws Exception{
    	
		String idCaraBayar = getParam("socCaraBayar");
		if (idCaraBayar == null || idCaraBayar.trim().length() == 0){
			idCaraBayar = "99999";
		}
		
		try{
    		
    		Vector beanPajakan = null;
    		
    		if (mode.equalsIgnoreCase("newPajakan")){
    			
    			beanPajakan = new Vector();
    			Hashtable hashPajakan = new Hashtable();
    			hashPajakan.put("tarikhTandatangan", getParam("txdTarikhTandatangan") == null ? "" : getParam("txdTarikhTandatangan"));
    			hashPajakan.put("tarikhMula", getParam("txdTarikhMulaPajakan") == null ? "" : getParam("txdTarikhMulaPajakan"));
    			hashPajakan.put("tarikhTamat", getParam("txdTarikhTamatPajakan") == null ? "" : getParam("txdTarikhTamatPajakan"));
    			hashPajakan.put("tempoh", getParam("txtTempoh") == null ? "" : getParam("txtTempoh"));
    			hashPajakan.put("kadar", getParam("txtKadarCukai") == null ? "" : getParam("txtKadarCukai"));
    			hashPajakan.put("kadarPajakan", getParam("txtKadarPajakan") == null ? "" : getParam("txtKadarPajakan"));
    			
    			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
    			
    			beanPajakan.addElement(hashPajakan);
				this.context.put("BeanPajakan", beanPajakan);
				
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewPajakan")){
    			
    			beanPajakan = new Vector();
    			logic.setMaklumatPajakan(idPajakan);
    			beanPajakan = logic.getBeanMaklumatPajakan();
    			this.context.put("BeanPajakan", beanPajakan);
    			
    			Hashtable hashMaklumatPajakanDB = (Hashtable) beanPajakan.get(0);
    			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatPajakanDB.get("idCaraBayar")), "disabled='disabled' class='disabled'"));
    			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updatePajakan")){
    			
    			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	private void saveMOA(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("tarikhTerima", getParam("txdTarikhTerimaMOA"));
		hash.put("tarikhTandatangan", getParam("txdTarikhTandatanganPTP"));
		hash.put("tarikhKembali", getParam("txdTarikhKembaliMOA"));
		hash.put("tarikhDaftar", getParam("txdTarikhDaftarMOA"));
		hash.put("tarikhBayar", getParam("txdTarikhBayaranMOA"));
		hash.put("noPerjanjian", getParam("txtNoPerjanjianMOA"));
		hash.put("tarikhHantarPengarah", getParam("txdTarikhHantarPengarah"));
		
		logic.updateMOA(idPermohonan, hash, session);
	}
	
	private void save15A(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("tarikhTerima", getParam("txdTarikhTerimaPemohon"));
		hash.put("tarikhTandatangan", getParam("txdTarikhTandatanganPTP15A"));
		hash.put("tarikhHantar", getParam("txdTarikhHantarPemohon"));
		hash.put("tarikhDaftar", getParam("txdTarikhDaftarPajakan"));
		hash.put("tarikhTerimaHakmilik", getParam("txdtarikhTerimaHakmilik"));
		hash.put("tarikhKemaskini", getParam("txdTarikhKemaskiniHakmilik"));
		
		logic.update15A(idPermohonan, hash, session);
	}
	
	private void saveJM(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
		hash.put("tarikhPerbadanan", getParam("txdTarikhPerbadanan"));
		hash.put("tarikhMesyuarat", getParam("txdtarikhMesyuarat"));
		hash.put("noMemo", getParam("txtNoMemorandum"));
		
		logic.updateJM(idPermohonan, hash, session);
	}
	
	private void saveDraf(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	String t = getParam("txdTarikhTerimaDraf");
    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
		hash.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP"));
		hash.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP"));
		hash.put("ulasan", getParam("txtKeteranganDraf"));
		
		logic.saveDraf(idPermohonan, hash, session);
	}
    
    private void saveUpdateDraf(String idDraf, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("tarikhHantar", getParam("txdTarikhHantarDraf"));
		hash.put("tarikhTerima", getParam("txdTarikhTerimaDraf"));
		hash.put("tarikhHantarPKP", getParam("txdTarikhHantarPKP"));
		hash.put("tarikhTerimaPKP", getParam("txdTarikhTerimaPKP"));
		hash.put("ulasan", getParam("txtKeteranganDraf"));
		
		logic.saveUpdateDraf(idDraf, hash, session);
	}
    
    private void savePajakan(String idPermohonan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("tarikhTandatangan", getParam("txdTarikhTandatangan"));
		hash.put("tarikhMula", getParam("txdTarikhMulaPajakan"));
		hash.put("tarikhTamat", getParam("txdTarikhTamatPajakan"));
		hash.put("tempoh", getParam("txtTempoh"));
		hash.put("kadar", getParam("txtKadarCukai"));
		hash.put("kadarPajakan", getParam("txtKadarPajakan"));
		hash.put("idCaraBayar", getParam("socCaraBayar"));
		hash.put("katCukai", getParam("socKategoriCukai"));
		
		logic.savePajakan(idPermohonan, hash, session);
	}
    
    private void saveUpdatePajakan(String idPajakan, HttpSession session) throws Exception {
    	Hashtable hash = new Hashtable();
    	hash.put("tarikhTandatangan", getParam("txdTarikhTandatangan"));
		hash.put("tarikhMula", getParam("txdTarikhMulaPajakan"));
		hash.put("tarikhTamat", getParam("txdTarikhTamatPajakan"));
		hash.put("tempoh", getParam("txtTempoh"));
		hash.put("kadar", getParam("txtKadarCukai"));
		hash.put("kadarPajakan", getParam("txtKadarPajakan"));
		hash.put("idCaraBayar", getParam("socCaraBayar"));
		hash.put("katCukai", getParam("socKategoriCukai"));
		
		logic.saveUpdatePajakan(idPajakan, hash, session);
	}
}
