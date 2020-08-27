/**
 * 
 */
package ekptg.view.htp.pajakan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmFailLainKemaskiniData;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanPendaftaranData;
import ekptg.model.htp.FrmPenswastaan2SenaraiFailData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.HTPeringatanBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IHTPeringatan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.view.htp.utiliti.HtpEmailServices;

public class FrmPajakanPendaftaranView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanPendaftaranView.class);
	private final String PATH="app/htp/pajakan/fail/";
	private IHTPeringatan iHTPP = null;  	
	private HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;
	private String idFail = "";
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanPendaftaranData logic = new FrmPajakanPendaftaranData();
	FrmPenswastaan2SenaraiFailData swastaData = new FrmPenswastaan2SenaraiFailData();
	
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
        String actionPajakan = getParam("actionPajakan");
	    String vm = ""; 
        if (actionPajakan.isEmpty()){
        	actionPajakan = "papar";
        }
        String submit = getParam("command");   
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
        String selectedTab = getParam("selectedTab");
        if(selectedTab.equals("") || selectedTab.equals(null)){
        	selectedTab = "0";
        }     
        
        System.out.println("action : "+action+" | actionPajakan : "+actionPajakan+" | submit : "+submit+" | hitButton : "+hitButton+" | mode : "+mode+" | selectedTab : "+selectedTab);
        
        String idPermohonan = getParam("idPermohonan");
        myLog.info("idPermohonan:"+idPermohonan);
        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = String.valueOf(session.getAttribute("idFail"));
        	if(!getParam("idFail").equals("")){
        		idFailSession = getParam("idFail");
        	}
    		htpPermohonan = getIHTP().findPermohonan(idFailSession,idPermohonan,"");
    		if(htpPermohonan == null)
				throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA KEMASKINI FAIL TERLEBIH DAHULU"));
			
        	idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());

        }
        idFail = getParam("idFail").equals("")?idFailSession:getParam("idFail");
 		session.setAttribute("idFail", idFail);
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
//        myLog.info("idFailSession:"+idFailSession);
//        myLog.info("idFail:"+getParam("idFail"));
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiHakmilik = null;
        Vector beanMaklumatPermohonan = null;
		Vector list = null;		
		
		//for email
		boolean sendEmail = true;
		HtpEmailServices htpEmailService = new HtpEmailServices();
		String emUrusan = "Pajakan";
		String emEmailto = "m.syazreen@hla-group.com";
		String emMaincontent = "hantarPengesahanPermohonanPajakan";
		String emNofail = "";
		String emTitle = "";
		String emTarikhMohon = "";
		String emAgensi = "";
		
        vm = PATH+"frmDaftarHakmilik.jsp";
        //HITBUTTON
		if (postDB){
    		if ("seterusnya".equals(hitButton)){
         		logic.seterusnya(idFail, idPermohonan, subUrusan, session);
        	}
    		if ("deleteHakmilik".equals(hitButton)){
         		logic.deleteHakmilik(idHakmilikUrusan);
        	}
    		if ("saveUpdateFail".equals(hitButton)){
    			//saveUpdateFail(idFail,session);
    			saveUpdateFail(idFail,subUrusan,session);
        	}
    		if ("hantarPengesahan".equals(hitButton)){
    			swastaData.saveHantarPengesahan(idFail, session,"H"); //hantar
    			
    			//email
    			if(sendEmail){
    				System.out.println("send mail");
    				htpEmailService.setEmail(emUrusan,emEmailto,emMaincontent,emNofail,emTitle,emTarikhMohon,emAgensi);
    			}
    			
        	}
    		if ("sahkanPermohonan".equals(hitButton)){
    			swastaData.saveHantarPengesahan(idFail, session,"S"); //sahkan
        	}
		}
		
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
	        myLog.info("idPermohonan 2:"+idPermohonan);
	        
	        /*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			String strval = "display";
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				strval = "hide";
			}
			this.context.put("valFlagMohonFail", strval);
			
		}
		//ACTION PAJAKAN
		if ("papar".equals(actionPajakan)){
			//GO TO PAPAR PAJAKAN  
			if(selectedTab.equals("0")){
				logicHeader.setMaklumatPermohonan(idFail);
		
				beanMaklumatPermohonan = new Vector();
    			beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
        		this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        		MaklumatPermohonanView(mode);
        		this.context.remove("senaraiFailLain");
	        	list = FrmFailLainKemaskiniData.getDataFailLain(idFail);
		       	this.context.put("senaraiFailLain",list);
				
			}else if(selectedTab.equals("1")){
        		senaraiHakmilik = new Vector();
    			logic.setListHakmilik(idPermohonan);
    			senaraiHakmilik = logic.getSenaraiHakmilik();
    			this.context.put("SenaraiHakmilik", senaraiHakmilik);
        
			}
			SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	        myLog.info("idPermohonan 4:"+idPermohonan);
	        Vector peringatanBayaran = getIHTPP().getPeringatanJenisBayaranPer(idPermohonan, "3",sdfTahun.format(new Date()),"21");
			this.context.put("bayarPajakan", peringatanBayaran);
        	
		}		
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("mode", mode);
		this.context.put("subUrusan", subUrusan);
		this.context.put("selectedTab", selectedTab);
		this.context.put("page", "2");
		getPermohonanInfo();
		if(getParam("idPermohonan").equals("")){
			getPermohonanInfo(idPermohonan);
		}
        //log.info(vm+":idPermohonan="+idPermohonan);
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
	
	  public void MaklumatPermohonanView(String mode) throws Exception{

	    	try {
	    		
	    		if (mode.equalsIgnoreCase("view")){    			

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	    		
	    		}else if(mode.equalsIgnoreCase("update")){	//mode = update    			

	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    		
	    		}

	    	} catch (Exception e){
	    		e.printStackTrace();
	    		
	    	}
	    }
	  
	  private void saveUpdateFail(String idFail,String idSubUrusan,HttpSession session) throws Exception {
	    	Hashtable hash = new Hashtable();
	    	hash.put("noFailKJP", getParam("txtNoFailKJP"));
			hash.put("tarikhSuratKJP", getParam("tarikhSuratKJP"));
			hash.put("noFailLain", getParam("txtNoFailLain"));
			hash.put("tarikhAgihan", getParam("tarikhAgihan"));
			hash.put("tajuk", getParam("txtTajuk"));
			hash.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon"));			
			//logic.saveUpdateFail(idFail, hash, session);
			logic.saveUpdateFail(idFail, idSubUrusan, hash, session);
			
		}
	  
		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}

		private IHTPeringatan getIHTPP(){
			if(iHTPP== null)
				iHTPP = new HTPeringatanBean();
			return iHTPP;
		}
}
