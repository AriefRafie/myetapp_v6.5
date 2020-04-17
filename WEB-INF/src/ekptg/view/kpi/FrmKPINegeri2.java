package ekptg.view.kpi;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.kpi.FrmKPIHTML;


public class FrmKPINegeri2 extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7507678888633793301L;
	static Logger mylog = Logger.getLogger(FrmKPINegeri2.class);
	private final String PATH="app/kpi/";

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String template_name = "";
		
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		//String action = getParam("action"); // Second Level
		Long tempIdNegeri = 0L;
		Vector<?> senaraiKeberkesanan = null;
		Vector<?> senaraiDesc = null;
		@SuppressWarnings("unused")
		int userLevel = 0;
		@SuppressWarnings("unused")
		User user = null;
		String idSeksyen = "";
			
		String userRole = (String)session.getAttribute("_portal_role");			
		String userId = (String)session.getAttribute("_portal_login");
		
		mylog.info("First Time,submit:"+submit);
		if(FrmUtilData.isUserRole(userId,"penggunaunit")) {
			userLevel = 1;
			template_name = PATH+"frmKPINegeriUnit.jsp";
		}else if(FrmUtilData.isUserRole(userId,"pengarahunit")) 
			userLevel = 2;
		else if(FrmUtilData.isUserRole(userId,"penggunanegeri")) 
			userLevel = 3;
		else if(FrmUtilData.isUserRole(userId,"pengarahnegeri")) 
			userLevel = 4;
		else if(FrmUtilData.isUserRole(userId,"penggunahq")) 
			userLevel = 5;
		else if(FrmUtilData.isUserRole(userId,"pengarahhq")) 
			userLevel = 6;
		else{
			throw new Exception("[PERINGKAT PENGGUNA] Tiada Kebenaran, Sila hubungi Pentadbir Sistem");
		}
		user = PrepareUser.getUserById(userId);
		//mylog.info("doTemplate2:userRole::"+userRole);
	
		//default pusaka
		//String tempIdUrusan = getParam("socUrusan")==null?"382":getParam("socUrusan");
		//String tempIdSubUrusan = getParam("socSuburusan")==""?"59":getParam("socSuburusan");
		String tempIdUrusan = getParam("socUrusan")==null?"0":getParam("socUrusan");
		String tempIdSubUrusan = getParam("socSuburusan")==""?"0":getParam("socSuburusan");

		String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(tempIdUrusan=="") ? 0L : Long.parseLong(tempIdUrusan),null," onChange=\"javascript:doChangeUrusan()\" ","2");
		String socSuburusan = HTML.SelectSuburusanByIdUrusan((tempIdUrusan=="") ? "0" : tempIdUrusan,"socSuburusan",(tempIdSubUrusan=="") ? 0L : Long.parseLong(tempIdSubUrusan), ""," onChange=\"javascript:doChangeSubUrusan()\" ");

	
		//String socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(getParam("socUrusan")=="") ? 0L : Long.parseLong(getParam("socUrusan")),null," onChange=\"javascript:doChangeUrusan()\" ","2");
		//this.context.put("socurusan",socUrusan);
	
		//String socSuburusan = HTML.SelectSuburusanByIdUrusan((getParam("socUrusan")=="") ? "0" : getParam("socUrusan"),"socSuburusan",(getParam("socSuburusan")=="") ? 0L : Long.parseLong(getParam("socSuburusan")), ""," onChange=\"javascript:doChangeSubUrusan()\" ");
		//this.context.put("socsuburusan",socSuburusan);

	
		if("terperinci".equals(submit)){
		   template_name = "app/kpi/frmKPIUtamaTerperinci.jsp";

		}else if ("pilihsuburusan".equals(submit)) {
			String tempTarikhMula = getParam("txdMula")==""?"0":getParam("txdMula");
			String tempTarikhAkhir = getParam("txdAkhir")==""?"0":getParam("txdAkhir");
			this.context.put("tarikhMula", tempTarikhMula);  
			this.context.put("tarikhAkhir", tempTarikhAkhir);  

			//tempIdSubUrusan
			Tblrujsuburusan rsu = (Tblrujsuburusan)DB.getSubUrusan(tempIdSubUrusan);			
			socSuburusan = UtilHTML.SelectSuburusanByLogin(userId, "socSuburusan",rsu.getIdSuburusan(), "", " onChange=\"javascript:doChangeSubUrusan()\"");
			
			senaraiDesc = FrmKPIData.getKeteranganUntukUtama(""+rsu.getIdUrusan(),tempIdSubUrusan);
			this.context.put("senaraidesc", senaraiDesc);  
		   
   			//Tblrujpejabatjkptg pej = null;
			//pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
			/*
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(""+rsu.getIdUrusan(),tempIdSubUrusan,""+pej.getIdNegeri(),"0","0");
			this.context.put("terperinci", terperinci);  

			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(""+rsu.getIdUrusan(), tempIdSubUrusan,"0","0","0","0");
			this.context.put("kesans", senaraiKeberkesanan);  
			*/
			String strNegeri = FrmKPIHTML.getKPIMengikutNegeri("", null,tempIdUrusan,""+rsu.getIdSuburusan(),""+user.getId_pejabatjkptg(),"0","0");
			this.context.put("mengikutnegeri", strNegeri);  
						
		}else if("pilihannegeri".equals(submit)){
			template_name = "app/kpi/frmKPIUtama.jsp";
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
			//mylog.info("pilihannegeri:idnegeri::"+getParam("idnegeri"));
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			this.context.put("selectNegeri", selectNegeri);  
				
			senaraiDesc = FrmKPIData.getKeteranganTerperinciByNegeri(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,"0","0","0");
			this.context.put("senaraidesc", senaraiDesc);  
			  
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,"0","0");
			this.context.put("terperinci", terperinci);  

			//senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,""+tempIdNegeri,"0","0","0");
			this.context.put("kesans", senaraiKeberkesanan);  

		}else if ("byurusandefault".equals(submit)) {
				
				template_name = "app/kpi/frmKPIUtama.jsp";
			//if(role.substring((role.length()-4), (role.length())).equalsIgnoreCase("NEGERI")){ //propose untuk PPK
	
		    //if(role.substring((role.length()-4), (role.length())).equalsIgnoreCase("UNIT")){ //propose untuk PPK
	
			//Vector<?> senaraiDesc = null;
			//senaraiDesc = FrmKPIData.getSenaraiKeterangan((getParam("socUrusan")=="") ? "" : getParam("socUrusan"));
			//senaraiDesc = FrmKPIData.getSenaraiKeterangan("382");
			senaraiDesc = FrmKPIData.getKeteranganUntukUtama(tempIdUrusan,tempIdSubUrusan);
			this.context.put("senaraidesc", senaraiDesc);  
		   
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,"0","0","0");
			this.context.put("terperinci", terperinci);  
	
			//Vector<?> senaraiKeberkesanan = null;
			//senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,"0","0","0","0");
			
			this.context.put("kesans", senaraiKeberkesanan);  
			//Long tempLong = 0L;
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			this.context.put("selectNegeri", selectNegeri);  
	
			
		}else if("pilihannegeri".equals(submit)){
			template_name = "app/kpi/frmKPIUtama.jsp";
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
			//mylog.info("pilihannegeri:idnegeri::"+getParam("idnegeri"));
			String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
			this.context.put("selectNegeri", selectNegeri);  
				
			//Vector<?> senaraiDesc = null;
			senaraiDesc = FrmKPIData.getKeteranganTerperinciByNegeri(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,"0","0","0");
			this.context.put("senaraidesc", senaraiDesc);  
			  
			String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,"0","0");
			this.context.put("terperinci", terperinci);  
	
			//Vector<?> senaraiKeberkesanan = null;
			//senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
			senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,""+tempIdNegeri,"0","0","0");
			this.context.put("kesans", senaraiKeberkesanan);  

		}else if("janalaporan".equals(submit)){
		   template_name = "app/kpi/frmKPIUtama.jsp";
		   String tempTarikhMula = getParam("txdMula")==""?"0":getParam("txdMula");
		   String tempTarikhAkhir = getParam("txdAkhir")==""?"0":getParam("txdAkhir");
		   tempIdNegeri = Long.parseLong(getParam("idnegeri"));
		   mylog.info("pilihannegeri:txdMula::"+getParam("txdMula"));
		   mylog.info("pilihannegeri:txdAkhir::"+getParam("txdAkhir"));
		   mylog.info("pilihannegeri:idnegeri::"+getParam("idnegeri"));
		   this.context.put("tarikhMula", tempTarikhMula);  
		   this.context.put("tarikhAkhir", tempTarikhAkhir);  
		   
		   String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		   this.context.put("selectNegeri", selectNegeri);  
			
		   
		   senaraiDesc = FrmKPIData.getKeteranganTerperinciByNegeri(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,"0",tempTarikhMula,tempTarikhAkhir);
		   this.context.put("senaraidesc", senaraiDesc);  
		   
		   String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,""+tempIdNegeri,tempTarikhMula,tempTarikhAkhir);
		   this.context.put("terperinci", terperinci);  

		   //Vector<?> senaraiKeberkesanan = null;
		   //senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
		   senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,""+tempIdNegeri,"0",tempTarikhMula,tempTarikhAkhir);
		   this.context.put("kesans", senaraiKeberkesanan);  

		}else{ 		
			//template_name = PATH+"frmKPINegeriUnit.jsp";
			mylog.info("First Time");

			if(userRole.substring((userRole.length()-3),(userRole.length())).equalsIgnoreCase("HTP")|| 
				userRole.substring((userRole.length()-3),(userRole.length())).equalsIgnoreCase("PPK")){ 
				idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));
				socUrusan = FrmKPIHTML.SelectUrusan("socUrusan",(tempIdUrusan=="") ? 0L : Long.parseLong(tempIdUrusan),null," onChange=\"javascript:doChangeUrusan()\" ",idSeksyen);
				mylog.info("First Time if");
				if(userLevel==1){
					//template_name = "app/kpi/frmKPINegeriUnit.jsp";
					//suburusanhtp
					socSuburusan = UtilHTML.SelectSuburusanByLogin(userId, "socSuburusan",0L, "", " onChange=\"javascript:doChangeSubUrusan()\"");

				}//else
			}else{
				mylog.info("First Time else");
				template_name = "app/kpi/frmKPINegeriUnit.jsp";

				senaraiDesc = FrmKPIData.getKeteranganUntukUtama(tempIdUrusan,tempIdSubUrusan);
				this.context.put("senaraidesc", senaraiDesc);  
	   
				String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTMLUtama(tempIdUrusan,tempIdSubUrusan,"0","0","0");
				this.context.put("terperinci", terperinci);  

				//Vector<?> senaraiKeberkesanan = null;
				//senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
				senaraiKeberkesanan = FrmKPIData.getKPIKeberkesananUtama(tempIdUrusan, tempIdSubUrusan,"0","0","0","0");		
				this.context.put("kesans", senaraiKeberkesanan);  
				String selectNegeri = UtilHTML.SelectNegeriXKod("idnegeri", tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
				this.context.put("selectNegeri", selectNegeri);  
				
			}
			this.context.put("tarikhMula", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));  
			this.context.put("tarikhAkhir", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));  
				//String strNegeri = FrmKPIHTML.getKPIMengikutNegeri("", null,tempIdUrusan,tempIdSubUrusan,"0","0","0");
			this.context.put("mengikutnegeri", "");  

		}
		this.context.put("socurusan",socUrusan);
    	this.context.put("socsuburusan",socSuburusan);
		this.context.put("EkptgUtil", new ekptg.helpers.Utils());

	
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