package ekptg.view.htp.pajakan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanPerjanjianPajakanData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.pajakan.IPajakanUtamaBayaran;
import ekptg.model.htp.pajakan.PajakanUtamaBayaranBean;

public class FrmPajakanPerjanjianView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanPerjanjianView.class);
	private final String PATH="app/htp/pajakan/perjanjian/";
	private HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;
	private String idFail = "";
	private IPajakanUtamaBayaran iPajakanBayaran = null;
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
		String idPermohonan = getParam("idPermohonan");
        //myLog.info(action+","+actionPajakan+","+submit+","+mode);
        //myLog.info(getParam("idFail")+", FailSession="+session.getAttribute("idFail")+","+idPermohonan);
        if (session.getAttribute("idFail") != null){
        	idFail = String.valueOf(session.getAttribute("idFail"));
        	if(!getParam("idFail").equals("")){
        		idFail = getParam("idFail");
        	}
    		htpPermohonan = getIHTP().findPermohonan(idFail,idPermohonan,"");
    		if(htpPermohonan == null)
				throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA KEMASKINI FAIL TERLEBIH DAHULU"));
			
        	idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());

        }
        idFail = getParam("idFail").equals("")?idFail:getParam("idFail");
 		session.setAttribute("idFail", idFail);
		String idStatus = getParam("idStatus");
		String subUrusan = getParam("subUrusan");
		String idDraf = getParam("idDraf");
		String idPajakan = getParam("idPajakan");

		// VECTOR
		Vector list = null;
		Vector beanHeader = null;

		System.out.println("action : "+action+" actionPajakan : "+actionPajakan+" submit : "+submit+" mode : "+mode+" hitButton : "+hitButton+" selectedTab : "+selectedTab);
		System.out.println("idPermohonan : "+idPermohonan+" idfail : "+session.getAttribute("idFail")+" idPajakan : "+idPajakan);
		
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
		logicHeader.setMaklumatPermohonan(idFail);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			//idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
		
			/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String strval = "display";
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				strval = "hide";
				throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
			}
			this.context.put("valFlagMohonFail", strval);
			
			
//			String showpopup = "false";
//			if(hashHeader.get("tarikhSuratPemohon")!=null){
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//				Calendar c = Calendar.getInstance();
//				c.setTime(sdf.parse(hashHeader.get("tarikhSuratPemohon").toString())); // Now use today date.
//				c.add(Calendar.DATE, 21); 
//				Date sysdate = sdf.parse(sdf.format(new Date()));
//				Date tarikhMohon = sdf.parse(sdf.format(c.getTime()));
//				
//				//.... check kalau ulasan dah masuk xyah popup (for temporary)...
//				if(tarikhMohon.after(sysdate) || tarikhMohon.equals(sysdate)){
//	        		//System.out.println("tarikhMohon is after/equal sysdate");
//	        		showpopup = "true";
//	        	}else{
//	        		showpopup = "false";
//	        	}
//			}
//			this.context.put("showpopup",showpopup);
			
		}

		vm = PATH+"index.jsp";
		if (actionPajakan.equalsIgnoreCase("papar")) {
			//vm = PATH+"frmPajakanPerjanjianPajakan.jsp";
			vm = PATH+"index.jsp";

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
        		
        		PajakanView(mode,idPajakan,idPermohonan);
        		
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
		myLog.info("idFail="+idFail);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("subUrusan", subUrusan);
		this.context.put("idDraf", idDraf);
		this.context.put("idPajakan", idPajakan);
		this.context.put("page", "4");
		getPermohonanInfo();
		if(getParam("idPermohonan").equals("")){
			getPermohonanInfo(idPermohonan);
		}
        //myLog.info(vm+":idPermohonan="+idPermohonan);
        return vm;
        
	}
	
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}

	private void getPermohonanInfo(String idPermohonan )throws Exception{
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
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
	
	public void PajakanView(String mode, String idPajakan,String idPermohonan) throws Exception{
    	
		String idCaraBayar = getParam("socCaraBayar");
		if (idCaraBayar == null || idCaraBayar.trim().length() == 0){
			idCaraBayar = "99999";
		}
		
		try{
    		
    		Vector beanPajakan = null;
    		Pajakan pajakan = getIPajakanBayaran().getMaklumatCukai(idPermohonan);
    		myLog.info(pajakan.getKadarCukaiString());
			this.context.put("pajakanTemp",pajakan);
			System.out.println("PajakanView. mode : "+mode);
			
    		if (mode.equalsIgnoreCase("newPajakan")){
    			System.out.println("newPajakan");
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
				
				//PENAMBAHBAIKAN FASA3. 27112014. SYAZ. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT
    			showNewEditDropdown("new",null);
    			
				this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    			
    		} else if (mode.equalsIgnoreCase("viewPajakan")){
    			System.out.println("viewPajakan");
    			beanPajakan = new Vector();
    			logic.setMaklumatPajakan(idPajakan);
    			beanPajakan = logic.getBeanMaklumatPajakan();
    			this.context.put("BeanPajakan", beanPajakan);
    			
    			Hashtable hashMaklumatPajakanDB = (Hashtable) beanPajakan.get(0);
    			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatPajakanDB.get("idCaraBayar")), "disabled='disabled' class='disabled'"));
    			
    			showNewEditDropdown("view",(Integer) hashMaklumatPajakanDB.get("notifikasiPeringatan"));
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}
    		//mode = update
    		else if(mode.equalsIgnoreCase("updatePajakan")){
    			System.out.println("updatePajakan");
    			beanPajakan = new Vector();
    			logic.setMaklumatPajakan(idPajakan);
    			beanPajakan = logic.getBeanMaklumatPajakan();
    			this.context.put("BeanPajakan", beanPajakan);
    			Hashtable hashMaklumatPajakanDB = (Hashtable) beanPajakan.get(0);
    			
    			
    			showNewEditDropdown("edit",(Integer) hashMaklumatPajakanDB.get("notifikasiPeringatan"));
    			
    			//this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
    			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatPajakanDB.get("idCaraBayar")), null));
    			
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
		hash.put("flagNotifikasi", getParam("flagNotifikasi"));
		
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
		hash.put("socNotifikasiPeringatan", getParam("socNotifikasiPeringatan"));
		
		String kcukai = getParam("txtKadarCukai") == "" ? "0.00" : getParam("txtKadarCukai");
		
		hash.put("kadar", Utils.parseDouble(Utils.RemoveComma(kcukai)));
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
		hash.put("socNotifikasiPeringatan", getParam("socNotifikasiPeringatan"));
		
		String kcukai = getParam("txtKadarCukai") == "" ? "0.00" : getParam("txtKadarCukai");
		
		hash.put("kadar", Utils.parseDouble(Utils.RemoveComma(kcukai)));
		hash.put("kadarPajakan", getParam("txtKadarPajakan"));
		hash.put("idCaraBayar", getParam("socCaraBayar"));
		hash.put("katCukai", getParam("socKategoriCukai"));
		
		logic.saveUpdatePajakan(idPajakan, hash, session);
	}
    
    private void showNewEditDropdown(String strMode,Integer value) throws Exception {
    	
    	if(strMode.equalsIgnoreCase("new") || strMode.equalsIgnoreCase("edit")){
    		strMode = "";
    	}else{
    		strMode = "disabled class='disabled'";
    	}
    	
    	this.context.put("selectNumberRange", HTML.selectNumberRange("socNotifikasiPeringatan", value, strMode, "onchange='javascript:doCheckTarikhTamat()' style='width:15% !important'", 1, 99));
    	
	}
    	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private IPajakanUtamaBayaran getIPajakanBayaran(){
		if(iPajakanBayaran== null)
			iPajakanBayaran = new PajakanUtamaBayaranBean();
		return iPajakanBayaran;
	}
	
	
}
