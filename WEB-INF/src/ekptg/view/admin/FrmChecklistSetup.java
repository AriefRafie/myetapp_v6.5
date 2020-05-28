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
    	mylog.info(this.className+":command="+submit+",pagemode="+pageMode);
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
//		    String socDesc = FrmKPIHTML.SelectKeterangan("socDesc",null);
	    	this.context.put("socdesc",socDesc);
	    	Vector senaraiDesc = null;
	    	
	    	if ("view".equals(pageMode)) {

	    	}else if ("kemaskini".equals(pageMode)) {
	    		updateKeteranganVS(session);

	    	}else if ("bysuburusan".equals(pageMode)) {
	    	
	    	}else if("tambahketeranganvs".equals(pageMode)) {
		      simpanKeteranganVS(session);
	    	
	    	}else if ("delete".equals(pageMode)) {
				deleteKeteranganVS(session);
			}
	    	
	    	senaraiDesc = SemakanData.getSenaraiSemakan(idUrusan,idSubUrusan,skrin);
//	    	senaraiDesc = FrmKPIData.getSenaraiKeterangan(idUrusan,idSubUrusan);
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
		
		    Vector senaraiDesc = null;
		    senaraiDesc = FrmKPIData.getSenaraiKeterangan((getParam("socUrusan")=="") ? "" : getParam("socUrusan"),"59");
		    this.context.put("senaraidesc", senaraiDesc);  

		      

		}else if ("bystatus".equals(submit)) {
			Long idStatus = getParam("socStatus")=="" ? 0L : Long.parseLong(("socStatus"));
			//mylog.info(this.className+":bystatus::idUrusan:::"+idUrusan);
			
	    	String socUrusan = FrmKPIHTML.SelectUrusan("socUrusanStatus",(getParam("socUrusanStatus")=="") ? 0L : Long.parseLong(getParam("socUrusanStatus")),null,"onChange=\"javascript:doChangeUrusanStatus()\" ",null);
	    	this.context.put("socurusanstatus",socUrusan);

	    	String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"socSubUrusanStatus",(getParam("socSubUrusanStatus")=="") ? 0L : Long.parseLong(getParam("socSubUrusanStatus")), "onChange=\"javascript:doChangeSubUrusanStatus()\" ");
	    	this.context.put("socsuburusanstatus",socSuburusan);

	    	//String socDesc = FrmKPIHTML.SelectKeterangan("socDesc","onChange=\"doChangeDesc()\" ");
	    	//String socDesc = FrmKPIHTML.SelectKeterangan("socDesc",null);
	    	//this.context.put("socdesc",socDesc);
	    	
	    	//SelectStatusFailByUrusan(String selectName,Long selectedValue, 
	    		//	String disability, String jsFunction,String idUrusan,String idSuburusan) throws Exception {
			String senaraiStatus = FrmKPIHTML.SelectStatusFailByUrusan("idstatus",idStatus,"","",idUrusan,idSubUrusan);
	    	
	   	    //Vector senaraiDesc = null;
    	    //senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML(idUrusan,idSubUrusan);

	    	Vector senaraiDescs = null;
			if ("viewbyurusanstatus".equals(pageMode)) {
	    	    //senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"59");
	    	    
				//senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML((getParam("socUrusanStatus")=="") ? "0" : getParam("socUrusanStatus"),"");
	    	    //this.context.put("senaraidescstatus", senaraiDesc);  

	    	}else if ("viewbysuburusanstatus".equals(pageMode)) {
	    	    //senaraiDesc = FrmKPIData.getSenaraiKeteranganByStatusHTML(idUrusan,idSubUrusan);

	    	}else if ("tambahstatus".equals(pageMode)) {
	    		simpanKeteranganStatus(session);

	    	}else if ("kemaskinistatus".equals(pageMode)) {
				updateKeteranganStatus(session);

	    	}else if ("delete".equals(pageMode)) {
				deleteKeteranganStatus(session);	 		
			}
			
    	    senaraiDescs = FrmKPIData.getSenaraiKeteranganByStatusHTML(idUrusan,idSubUrusan);
    	    this.context.put("senaraidescstatus", senaraiDescs);  
			
    	    this.context.put("senaraistatus", senaraiStatus);  

		}else if("tambahtatus".equals(submit)){ 
	      this.simpanKeteranganStatus(session);
			//template_name = "app/kpi/frmKPISetup.jsp";	        
		}else{ 
			mylog.info(this.className+"!=submit else:user_id=::"+session.getAttribute("_ekptg_user_id"));
			//template_name = "app/kpi/frmKPISetup.jsp";	
			if(selectedTab.equals("0"))
				senaraiFail = FrmSemakan.getSemakan(idSemakan, semakan);
			else if(selectedTab.equals("1"))
				senaraiFail = SemakanData.getSenaraiSemakan(idUrusan,idSubUrusan,skrin);

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
	    
	  private void simpanKeteranganVS(HttpSession session) throws Exception {
		    String idKeterangan = getParam("socDesc");
		    String idUrusan = getParam("socSuburusan");
		    String sasaran = getParam("socsasaran");
		    String jenis = getParam("socjsasaran");
		    String giliran = getParam("socgiliran");
		    String seq = getParam("socaturan");
		    int id_masuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    Hashtable h = new Hashtable(); 
		    h.put("idketerangan", idKeterangan);
		    h.put("idurusan", idUrusan);
		    h.put("sasaran", sasaran);
		    h.put("jenis", jenis);
		    h.put("giliran", giliran);
		    h.put("idmasuk", id_masuk);
		    FrmKPIData.simpanKeteranganVS(h);
	  }
	  
	  private void updateKeteranganVS(HttpSession session) throws Exception {
		    String idKpiurusan = getParam("idkpiketerangan");
		    String idKeterangan = getParam("socDesc");
		    String idUrusan = getParam("socSuburusan");
		    String sasaran = getParam("socsasaran");
		    String jenis = getParam("socjsasaran");
		    String giliran = getParam("socgiliran");
		    String seq = getParam("socaturan");
		    int id_kemaskini = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    int pilih = getParamAsInteger("socpilihan");
		    
		    Hashtable h = new Hashtable(); 
		    h.put("idkpiurusan", idKpiurusan);
		    h.put("idketerangan", idKeterangan);
		    h.put("idurusan", idUrusan);
		    h.put("sasaran", sasaran);
		    h.put("jenis", jenis);
		    h.put("giliran", giliran);
		    h.put("seq", seq);
		    h.put("idkemaskini", id_kemaskini);
		    h.put("pilih", pilih);
		    FrmKPIData.updateKeteranganvs(h);
	  }
	  
	  private void deleteKeteranganVS(HttpSession session) throws Exception {
		  String idKpiketerangan = getParam("idkpiketerangan");
		  FrmKPIData.deleteVS(idKpiketerangan);
	  }
	  
	  private void simpanKeteranganStatus(HttpSession session) throws Exception {
		    String idStatus = getParam("idstatus");
		    String id_Kpi = getParam("idkpiketerangan");
		    int id_masuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    Hashtable h = new Hashtable(); 
		    h.put("idsuburusanstatus", idStatus);
		    h.put("idkpiurusan", id_Kpi);
		    h.put("idmasuk", id_masuk);
		    //mylog.info(this.className+":"+h);
		    FrmKPIData.simpanKeteranganStatus(h);
	  }
	  
	  private void updateKeteranganStatus(HttpSession session)throws Exception{
		    String idKpistatus = getParam("idkpiketerangan");
		    String idStatus = getParam("idstatus");
		    int idKemaskini = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    FrmKPIData.updateStatus(idKpistatus, idStatus, idKemaskini);
	  }
	  private void deleteKeteranganStatus(HttpSession session) throws Exception {
		  String idKpiketerangan = getParam("idkpiketerangan");
		  FrmKPIData.deleteStatus(idKpiketerangan);
	  }

	  private void DataPenyata(HttpSession session, int idNegeri, String disability, String readability, String style1, String style2) throws Exception
		{
		  	String negeri = "";
			Vector list = new Vector();
			list.clear();
			try{
				list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				mylog.info("CukaiProcess::DataPenyata::list.size():::"+list.size());
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			    mylog.info("penyataview::DataPenyata::idpermohonan:::"+idPermohonan);
				negeri = getParam("Negeri");
				mylog.info("CukaiProcess::DataPenyata::negeri:::"+negeri);
				this.context.put("negeri", "");
								
				if(list.size() != 0){			    
					mylog.info("CukaiProcess::DataPenyata::list::"+list);
					this.context.put("Penyata", list);
					this.context.put("idPermohonan", idPermohonan);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				}else{
					this.context.put("Penyata", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				}
				this.context.put("negeri", negeri);
			}catch(Exception ex){
				mylog.info("CukaiProcess::DataPenyata::Exception:: = "+ex);
				ex.printStackTrace();
			}
		}	  	  

//Baucer
	  
	  private void DataBaucer(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
		{
		  	Vector list = new Vector();
			list.clear();
//			int idCukaiUtama = Integer.parseInt(getParam("idCukaiUtama"));
//		    mylog.info("penyataview::DataBaucer::idCukaiUtama:::"+idCukaiUtama);
		    
			try{
				//list = FrmCukaiBaucerData.getListBaucer();
				mylog.info("CukaiProcess::DataBaucer::list.size():::"+list.size());
								
				if(list.size() != 0){			    
					mylog.info("CukaiProcess::DataBaucer::list::"+list);
					this.context.put("Baucer", list);
					this.context.put("idCukaiUtama", list);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				}else{
					this.context.put("Baucer", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				}
			}catch(Exception ex){
				mylog.info("CukaiProcess::DataBaucer::Exception:: = "+ex);
				ex.printStackTrace();
			}
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
	
	
}
