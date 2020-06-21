package ekptg.view.admin;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.admin.SemakanData;
import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.kpi.FrmKPIHTML;
import ekptg.model.utils.rujukan.UtilHTMLPilihanJenisDokumen;
import ekptg.model.utils.rujukan.UtilHTMLPilihanSemakan;

public class FrmChecklistSetup extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5276213479124156950L;
	private String result = new String();
	static Logger mylog = Logger.getLogger(ekptg.view.admin.FrmChecklistSetup.class);
	private String userId = "";
	private IUtilHTMLPilihan iPilihan = null;
	private IUtilHTMLPilihan iPilihanj = null;
	private Hashtable<String,String> h = null; 
    private Vector<Hashtable<String,String>> senaraiDesc = null;

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
    	String readability = "";
    	String style1 = "";
		String style2 = "";

		this.context.put("Util", new lebah.util.Util());

		String selectedTab = new String();
    	selectedTab = getParam("tabId").toString();
    	if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    	}
   		
	    Vector senaraiFail = null;
		String action = getParam("action");
	    String submit = getParam("command");
//	    this.context.put(this.className+":command", submit);
	    String pageMode = getParam("pagemode");
//	    this.context.put(this.className+":pagemode", pageMode);
	    String skrin = getParam("txtskrin");
	    this.context.put("txtSkrin", skrin);
		String idUrusan = getParam("socUrusan")=="" ? "0" : getParam("socUrusan");
		String idSubUrusan = getParam("socSuburusan")=="" ? "0" : getParam("socSuburusan");


	    userId = String.valueOf(session.getAttribute("_ekptg_user_id"));
	    String idSemakan = null;
	    String semakan = null;

	    Vector list = new Vector();
    	mylog.info("command="+submit+",pagemode="+pageMode);
    	template_name = "app/admin/checklist/index.jsp";	        
	    
    	if ("tambahketerangan".equals(submit)) {
    		simpanKeterangan();

		}else if ("kemaskiniketerangan".equals(submit)) {
			kemaskiniKeterangan();

		}else if ("delete".equals(submit)) {
			hapusKeterangan();
			 		
		}else if ("byurusandefault".equals(submit)) {
			String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(idUrusan=="") ? 0L : Long.parseLong(idUrusan),null,"onChange=\"javascript:doChangeUrusan()\" ",null);
	    	this.context.put("socurusan",socUrusan);
	    	
	    	String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusan")=="") ? "0" : getParam("socUrusan"),"socSuburusan",(getParam("socSuburusan")=="") ? 0L : Long.parseLong(getParam("socSuburusan")), "","onChange=\"javascript:doChangeSubUrusan()\" ");
		    this.context.put("socsuburusan",socSuburusan);
		    
		    String socDesc = PilihSemakan().Pilihan("socDesc");
	    	this.context.put("socdesc",socDesc);
	    	
	    	if (pageMode.equals("view")) {

	    	}else if (pageMode.equals("kemaskini")) {
	    		updateKeteranganVS();

	    	}else if (pageMode.equals("bysuburusan")) {
	    	
	    	}else if(pageMode.equals("tambahketeranganvs")) {
	    		simpanKeteranganVS();
	    	
	    	}else if ("delete".equals(pageMode)) {
				deleteKeteranganVS();
			}
	    	
			semakan = getParam("txtdesc").equals("")?null:getParam("txtdesc");
	    	senaraiDesc = SemakanData.getSenaraiSemakan(idUrusan,idSubUrusan,skrin,semakan);
	    	
	    	this.context.put("senaraidesc", senaraiDesc);  
	    	senaraiFail = senaraiDesc;	    	

    	//deleteKeterangan(session);
			//template_name = "app/kpi/frmKPISetup.jsp";	        
		    //}
			//if("cukaiperingkatbayar".equals(submit)){
		}else if ("tambahketeranganvs".equals(submit)) {		    	
		      //sama dgn line 64-73
			String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(getParam("socUrusan")=="") ? 0L : Long.parseLong(getParam("socUrusan")),null,"onChange=\"javascript:doChangeUrusan()\" ","2");
			this.context.put("socurusan",socUrusan);
		    	
			String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusan")=="") ? "0" : getParam("socUrusan"),"socSuburusan",(getParam("socSuburusan")=="") ? 0L : Long.parseLong(getParam("socSuburusan")), "");
			this.context.put("socsuburusan",socSuburusan);

			String socDesc = FrmKPIHTML.SelectKeterangan("socDesc",null);
			this.context.put("socdesc",socDesc);
		
//		    senaraiDesc = FrmKPIData.getSenaraiKeterangan((getParam("socUrusan")=="") ? "" : getParam("socUrusan"),"59");
//		    this.context.put("senaraidesc", senaraiDesc);  

		}else if ("bystatus".equals(submit)) {
			Long idStatus = getParam("socStatus")=="" ? 0L : Long.parseLong(("socStatus"));
			//mylog.info(this.className+":bystatus::idUrusan:::"+idUrusan);
			
	    	String socUrusan = FrmKPIHTML.SelectUrusan("socUrusanStatus",(getParam("socUrusanStatus")=="") ? 0L : Long.parseLong(getParam("socUrusanStatus")),null,"onChange=\"javascript:doChangeUrusanStatus()\" ",null);
	    	this.context.put("socurusanstatus",socUrusan);

	    	String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"socSubUrusanStatus",(getParam("socSubUrusanStatus")=="") ? 0L : Long.parseLong(getParam("socSubUrusanStatus")), "onChange=\"javascript:doChangeSubUrusanStatus()\" ");
	    	this.context.put("socsuburusanstatus",socSuburusan);

	    	String socSeksyen = getParam("socseksyen")=="" ? "0" : getParam("socseksyen");
	    	
	    	String senaraiStatus = PilihJenis().Pilihan("idstatus", "", socSeksyen, "");
    	    this.context.put("senaraistatus", senaraiStatus); 
    	    
    	    this.context.put("socSeksyen", HTML.SelectSeksyen("socseksyen", Long.parseLong(socSeksyen),"","onChange=\"javascript:doChangeUrusanStatus()\" ")); 

	   	 	if ("viewbyurusanstatus".equals(pageMode)) {
	    	    //senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"59");
	    	    
				//senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"");
	    	    //this.context.put("senaraidescstatus", senaraiDesc);  

	    	}else if ("viewbysuburusanstatus".equals(pageMode)) {
	    	    //senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML(idUrusan,idSubUrusan);

	    	}else if ("tambahstatus".equals(pageMode)) {
	    		simpanKeteranganStatus();

	    	}else if (pageMode.equals("kemaskinistatus")) {
				updateKeteranganStatus();

	    	}else if ("delete".equals(pageMode)) {
				deleteKeteranganStatus(session);	 		
			}
			
    	    senaraiDesc = SemakanData.getSenaraiJenisDokumen(idUrusan,idSubUrusan,"");
    	    this.context.put("senaraidescstatus", senaraiDesc);  
	    	senaraiFail = senaraiDesc;	    	
			

		}else if("tambahtatus".equals(submit)){ 
	      //this.simpanKeteranganStatus(session);
			//template_name = "app/kpi/frmKPISetup.jsp";	        
		}else{ 
			mylog.info(this.className+"!=submit else:user_id=::"+session.getAttribute("_ekptg_user_id"));
			//template_name = "app/kpi/frmKPISetup.jsp";	
			semakan = getParam("txtdesc").equals("")?null:getParam("txtdesc");
			if(selectedTab.equals("0"))
				senaraiFail = FrmSemakan.getSemakan(idSemakan, semakan);
			else if(selectedTab.equals("1"))
				senaraiFail = SemakanData.getSenaraiSemakan(idUrusan,idSubUrusan,skrin,semakan);

		}
		setupPage(session,action,senaraiFail);

	    this.context.put("senarais", senaraiFail);  
    	mylog.info(this.className+":selectedTab::"+selectedTab);
		
   		this.context.put("selectedTab",selectedTab);
	    return template_name;
	    
	  }
	
	  private void simpanKeterangan() throws Exception {
		    String keterangan = getParam("keterangan");
		    SemakanData.tambah(keterangan, userId);
		    
	  }
	  
	  private void kemaskiniKeterangan()throws Exception{
		    String idKpiketerangan = getParam("idkpiketerangan");
		    String keterangan = getParam("keterangan");
		    SemakanData.kemaskini(idKpiketerangan, keterangan, userId);
		    
	  }

	  private void hapusKeterangan() throws Exception {
		  String idKpiketerangan = getParam("idkpiketerangan");
		  SemakanData.hapus(idKpiketerangan);
		  
	  }
	    
	  private void simpanKeteranganVS() throws Exception {
		    String idKeterangan = getParam("socDesc");
		    String idUrusan = getParam("socUrusan");
		    String idSubUrusan = getParam("socSuburusan");
		    String seq = getParam("socaturan");
		    String txtSkrin = getParam("txtskrin");
		    h = new Hashtable<String,String>(); 
		    h.put("idKeterangan", idKeterangan);
		    h.put("idUrusan", idUrusan);
		    h.put("idSubUrusan", idSubUrusan);
		    h.put("seq", seq);
		    h.put("txtSkrin", txtSkrin);
		    h.put("idMasuk", userId);
		    SemakanData.simpanKeteranganVS(h);
	  }
	  
	  private void updateKeteranganVS() throws Exception {
		  String idSenarai = getParam("idkpiketerangan");
		  String idKeterangan = getParam("socDesc");
		  String idUrusan = getParam("socUrusan");
		  String idSubUrusan = getParam("socSuburusan");		    
		  String seq = getParam("socaturan");
		  String txtSkrin = getParam("txtskrin");
//		    int pilih = getParamAsInteger("socpilihan");
		    
		  h = new Hashtable<String,String>(); 
		  h.put("idKemaskini", userId);
		  h.put("idSenarai", idSenarai);
		  h.put("idKeterangan", idKeterangan);
		  h.put("idUrusan", idUrusan);
		  h.put("idSubUrusan", idSubUrusan);
		  h.put("seq", seq);
		  h.put("txtSkrin", txtSkrin);
		  SemakanData.updateKeteranganvs(h);
		    
	  }
	  
	  private void deleteKeteranganVS() throws Exception {
		  String idKpiketerangan = getParam("idkpiketerangan");
		  SemakanData.deleteVS(idKpiketerangan);
	  }
	  
	  private void simpanKeteranganStatus() throws Exception {
		    String idStatus = getParam("idstatus");
		    String idSemakan = getParam("idkpiketerangan");
			  h = new Hashtable<String,String>(); 
		    h.put("idJenis", idStatus);
		    h.put("idSemakan", idSemakan);
		    h.put("idMasuk", userId);
		    //mylog.info(this.className+":"+h);
		    SemakanData.simpanJenisDokumen(h);
	  }
	  
	private void updateKeteranganStatus() throws Exception{
		String idKpistatus = getParam("idkpiketerangan");
	    String idStatus = getParam("idstatus");
	    String idSemakan = getParam("idsemakan");
	    SemakanData.updateJenisDokumen(idKpistatus, idStatus,idSemakan,userId);
	    
	}
	
	private void deleteKeteranganStatus(HttpSession session) throws Exception {
		String idKpiketerangan = getParam("idkpiketerangan");
		SemakanData.deleteStatus(idKpiketerangan);
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
	
	private IUtilHTMLPilihan PilihSemakan(){
		if(iPilihan==null){
			iPilihan = new UtilHTMLPilihanSemakan();
		}
		return iPilihan;
				
	}
	
	private IUtilHTMLPilihan PilihJenis(){
		if(iPilihanj==null){
			iPilihanj = new UtilHTMLPilihanJenisDokumen();
		}
		return iPilihanj;
				
	}
	
	
}
