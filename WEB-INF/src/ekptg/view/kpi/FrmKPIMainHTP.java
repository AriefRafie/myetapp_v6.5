package ekptg.view.kpi;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.kpi.FrmKPIHTML;


public class FrmKPIMainHTP extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2242232308995085910L;
	static Logger mylog = Logger.getLogger(ekptg.view.kpi.FrmKPIMainHTP.class);
	private final String IDSUBURUSANPERMOHONAN = "42";
	private String terperinci = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String template_name = "app/kpi/frmKPIUtama.jsp";
		//this.context.put("Util", new lebah.util.Util());
		this.context.put("EkptgUtil", new ekptg.helpers.Utils());
	
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		String action = getParam("action"); // Second Level
		String idSeksyen = "";
		
		Vector<?> list = new Vector();
		//mylog.info(this.className+":submit::"+submit);
		//mylog.info(this.className+":pageMode::"+pageMode);
		//mylog.info(this.className+":fail::"+getParam("fail"));
		Long tempIdNegeri = 0L;
		Vector<?> senaraiKeberkesanan = null;
		Vector<?> senaraiDesc = null;
		String userRole = (String)session.getAttribute("_portal_role");
		String tempIdUrusan = getParam("socUrusan")==null?"0":getParam("socUrusan");
		String tempIdSubUrusan = getParam("socSubUrusan")==""?"0":getParam("socSubUrusan");
	
		String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(tempIdUrusan=="") ? 0L : Long.parseLong(tempIdUrusan),null," onChange=\"javascript:doChangeUrusan()\" ","3");
		String socSuburusan = HTML.SelectSuburusanByIdUrusan((tempIdUrusan=="") ? "0" : tempIdUrusan,"socSubUrusan",(tempIdSubUrusan=="") ? 0L : Long.parseLong(tempIdSubUrusan), ""," onChange=\"javascript:doChangeSubUrusan()\" ");
 		if (tempIdUrusan.equals("1")){
 	 		socSuburusan = HTML.SelectSuburusanByIdUrusan(tempIdUrusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
 		}
		
		if("terperinci".equals(submit)){
			   template_name = "app/kpi/frmKPIUtamaTerperinci.jsp";
	
		}else if ("byurusandefault".equals(submit)) {
			template_name = "app/kpi/frmKPIUtama.jsp";
			
			senaraiDesc = FrmKPIData.getKeteranganUntukUtamaHTP(tempIdUrusan,tempIdSubUrusan);	   
			terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtamaHTP(tempIdUrusan,tempIdSubUrusan,"0","0","0");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtamaHTP(tempIdUrusan,tempIdSubUrusan,"0","0","0","0");
			
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			this.context.put("senaraidesc", senaraiDesc);  
			this.context.put("terperinci", terperinci);  
			this.context.put("kesans", senaraiKeberkesanan);  
			this.context.put("selectNegeri", selectNegeri);  
		
		}else if("pilihannegeri".equals(submit)){
			template_name = "app/kpi/frmKPIUtama.jsp";
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			this.context.put("selectNegeri", selectNegeri);  
				
			senaraiDesc = FrmKPIData.getKeteranganTerperinciByNegeri(tempIdUrusan,tempIdSubUrusan,String.valueOf(tempIdNegeri),"0","0","0");
			this.context.put("senaraidesc", senaraiDesc);  
			  
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,String.valueOf(tempIdNegeri),"0","0");
			this.context.put("terperinci", terperinci);  
	
			//Vector<?> senaraiKeberkesanan = null;
			//senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,String.valueOf(tempIdNegeri),"0","0","0");
			this.context.put("kesans", senaraiKeberkesanan);  
	
		}else if("janalaporan".equals(submit)){
			template_name = "app/kpi/frmKPIUtama.jsp";
			String tempTarikhMula = getParam("txdMula")==""?"0":getParam("txdMula");
			String tempTarikhAkhir = getParam("txdAkhir")==""?"0":getParam("txdAkhir");
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
			mylog.info("pilihannegeri:txdMula::"+getParam("txdMula"));
			mylog.info("pilihannegeri:txdAkhir::"+getParam("txdAkhir"));
			mylog.info("pilihannegeri:idnegeri::"+getParam("idnegeri"));	   
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);		   
			String terperinci = "";
			//tempIdSubUrusan = ;
			if((tempTarikhMula == "0" && tempTarikhAkhir=="0") || (tempTarikhMula == "" && tempTarikhAkhir=="")){
				senaraiDesc = FrmKPIData.getKeteranganUntukUtamaHTP(tempIdUrusan,tempIdSubUrusan);					   
				terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtamaHTP(tempIdUrusan,tempIdSubUrusan,"0","0","0");		
				senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtamaHTP(tempIdUrusan,tempIdSubUrusan,"0","0","0","0");
   
		   } else{
			   senaraiDesc = FrmKPIData.getKeteranganTerperinciByNegeri(tempIdUrusan,tempIdSubUrusan,String.valueOf(tempIdNegeri),"0",tempTarikhMula,tempTarikhAkhir);		   
			   terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,String.valueOf(tempIdNegeri),tempTarikhMula,tempTarikhAkhir);
			   senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,String.valueOf(tempIdNegeri),"0",tempTarikhMula,tempTarikhAkhir);
	
		   }
		   this.context.put("tarikhMula", tempTarikhMula);  
		   this.context.put("tarikhAkhir", tempTarikhAkhir);  			   
		   this.context.put("selectNegeri", selectNegeri);  			
		   this.context.put("senaraidesc", senaraiDesc);  
		   this.context.put("terperinci", terperinci);  	
		   this.context.put("kesans", senaraiKeberkesanan);  
	
		}else{ 		
			template_name = "app/kpi/frmKPIUtama.jsp";
			//mylog.info("doTemplate2:userRole 1::"+userRole.substring((userRole.length()-3),userRole.length()));
			//mylog.info("doTemplate2:userRole 2::"+userRole);
	
			if(userRole.substring((userRole.length()-3),(userRole.length())).equalsIgnoreCase("HTP")|| 
					userRole.substring((userRole.length()-3),(userRole.length())).equalsIgnoreCase("PPK")){ 
				idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));
				socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(tempIdUrusan=="") ? 0L : Long.parseLong(tempIdUrusan),null," onChange=\"javascript:doChangeUrusan()\" ",idSeksyen);
			}else
		    //if(role.substring((role.length()-4), (role.length())).equalsIgnoreCase("UNIT")){ //propose untuk PPK
	
			senaraiDesc = FrmKPIData.getKeteranganUntukUtama(tempIdUrusan,tempIdSubUrusan);	   
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,"0","0","0");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,"0","0","0","0");		
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			
			this.context.put("senaraidesc", senaraiDesc);  
			this.context.put("terperinci", terperinci);  
			this.context.put("kesans", senaraiKeberkesanan);  
			this.context.put("selectNegeri", selectNegeri);  
		   
		}
		this.context.put("socurusan",socUrusan);
	    this.context.put("socsuburusan",socSuburusan);	
		return template_name;
		
	}

public void setupPage(HttpSession session,String action,Vector lists) {
	this.context.put("totalRecords",lists.size());
	int page = getParam("page") == "" ? 1:getParamAsInteger("page");
	
	int itemsPerPage;
	if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
		itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
	} else {
		itemsPerPage = (Integer)this.context.get("itemsPerPage");
	}
    
    if ("getNext".equals(action)) {
    	page++;
    } else if ("getPrevious".equals(action)) {
    	page--;
    } else if ("getPage".equals(action)) {
    	page = getParamAsInteger("value");
    } else if ("doChangeItemPerPage".equals(action)) {
       itemsPerPage = getParamAsInteger("itemsPerPage");
    }
	Paging paging = new Paging(session,lists,itemsPerPage);
	
	if (page > paging.getTotalPages()) page = 1; //reset page number
	this.context.put("lists",paging.getPage(page));
    this.context.put("page", new Integer(page));
    this.context.put("itemsPerPage", new Integer(itemsPerPage));
    this.context.put("totalPages", new Integer(paging.getTotalPages()));
    this.context.put("startNumber", new Integer(paging.getTopNumber()));
    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
}

public void setParameterValues(Hashtable<String, String> parameters) {
	String name="";
	String value="";
	Enumeration allparam = request.getParameterNames();
	for (; allparam.hasMoreElements(); ) {
        name = (String)allparam.nextElement();
        if (name.indexOf("Form_") != -1) { // get only parameters with name like Form_
	        value = request.getParameter(name);
	        parameters.put(name,value);
        }
	}
}

}