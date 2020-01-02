package ekptg.view.kpi;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.kpi.FrmKPIHTML;

public class FrmKPISetup extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	private String result = new String();
	static Logger mylog = Logger.getLogger(FrmKPISetup.class);

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

   		//System.out.println(this.className+":selectedTab::"+selectedTab);
   		
	    Vector senaraiFail = null;
	    String submit = getParam("command");
	    this.context.put(this.className+":command", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put(this.className+":pagemode", pageMode);
		Vector list = new Vector();
    	mylog.info(this.className+":command::"+submit);
    	mylog.info(this.className+":pagemode::"+pageMode);
    	//System.out.println(this.className+":fail::"+getParam("fail"));
		template_name = "app/kpi/frmKPISetup.jsp";	        
	    if ("tambahketerangan".equals(submit)) {
		      simpanKeterangan(session);

		}else if ("kemaskiniketerangan".equals(submit)) {
			updateKeterangan(session);
			//template_name = "app/kpi/frmKPISetup.jsp";	        
		}else if ("delete".equals(submit)) {
			deleteKeterangan(session);
			template_name = "app/kpi/frmKPISetup.jsp";	        
		    //}
			//if("cukaiperingkatbayar".equals(submit)){
			 		
		}else if ("byurusandefault".equals(submit)) {
			String idUrusan = getParam("socUrusan")=="" ? "0" : getParam("socUrusan");
			String idSubUrusan = getParam("socSuburusan")=="" ? null : getParam("socSuburusan");

			
	    	//String socUrusan = HTML.SelectUrusan("socUrusan",(getParam("socUrusan")=="") ? 0L : Long.parseLong(getParam("socUrusan")),null,"onChange=\"javascript:doChangeUrusan()\" ");
	    	//String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(getParam("socUrusan")=="") ? 0L : Long.parseLong(getParam("socUrusan")),null,"onChange=\"javascript:doChangeUrusan()\" ","2");
	    	//modified on 20/11/2009
			String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(getParam("socUrusan")=="") ? 0L : Long.parseLong(getParam("socUrusan")),null,"onChange=\"javascript:doChangeUrusan()\" ",null);
	    	this.context.put("socurusan",socUrusan);
	    	
	    	String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusan")=="") ? "0" : getParam("socUrusan"),"socSuburusan",(getParam("socSuburusan")=="") ? 0L : Long.parseLong(getParam("socSuburusan")), "","onChange=\"javascript:doChangeSubUrusan()\" ");
		    this.context.put("socsuburusan",socSuburusan);

	     	
	    	//String socDesc = FrmKPIHTML.SelectKeterangan("socDesc","onChange=\"doChangeDesc()\" ");
	    	String socDesc = FrmKPIHTML.SelectKeterangan("socDesc",null);
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
	    	
	    	senaraiDesc = FrmKPIData.getSenaraiKeterangan(idUrusan,idSubUrusan);
	    	this.context.put("senaraidesc", senaraiDesc);  
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
			String idUrusan = getParam("socUrusanStatus")=="" ? "0" : getParam("socUrusanStatus");
			String idSubUrusan = getParam("socSubUrusanStatus")=="" ? null : getParam("socSubUrusanStatus");
			Long idStatus = getParam("socStatus")=="" ? 0L : Long.parseLong(("socStatus"));
			//mylog.info(this.className+":bystatus::idUrusan:::"+idUrusan);
			//mylog.info(this.className+":bystatus::idSubUrusan:::"+idSubUrusan);
			//mylog.info(this.className+":bystatus::idStatus:::"+idStatus);
			
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
		}
	    senaraiFail = FrmKPIData.getSenaraiKeterangan();
	    this.context.put("senarais", senaraiFail);  
    	mylog.info(this.className+":selectedTab::"+selectedTab);
		
   		this.context.put("selectedTab",selectedTab);
	    return template_name;
	  }
	
	  private void simpanKeterangan(HttpSession session) throws Exception {
		    String keterangan = getParam("keterangan");
		    int id_masuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    Date tarikh_masukd = new Date();
		    FrmKPIData.add(keterangan, id_masuk);
	  }
	  
	  private void updateKeterangan(HttpSession session)throws Exception{
		    String idKpiketerangan = getParam("idkpiketerangan");
		    String keterangan = getParam("keterangan");
		    int idKemaskini = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		    FrmKPIData.update(idKpiketerangan, keterangan, idKemaskini);
	  }

	  private void deleteKeterangan(HttpSession session) throws Exception {
		  String idKpiketerangan = getParam("idkpiketerangan");
		  FrmKPIData.delete(idKpiketerangan);
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
}
