package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.utils.FrmSetupSuburusanDokumenData;

public class FrmSetupSuburusanDokumen extends AjaxBasedModule {
	/**
	 *  
	 */
	private static final long serialVersionUID = 6392001167937222459L;
	private static final String PATH="app/utils/";
	private static Logger myLog = Logger.getLogger(ekptg.view.utils.FrmSetupSuburusanDokumen.class);

	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String template = PATH+"frmSetupSuburusanDokumen.jsp";
		
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		String action = getParam("action"); // Second Level
		String doChange = getParam("doChange");
		String pagemode = getParam("pagemode");
		String button = getParam("button");
		
		String id_Urusan = null;
		String id_Suburusan = null;
		String id_status = null;
	
	    Vector lists = new Vector();
	    this.context.put("result", "");
	    //Form submitted values
	    String id = getParam("id");
		
	    FrmSetupSuburusanDokumenData f = new FrmSetupSuburusanDokumenData();
			
		if ("doChangesUrusan".equals(submit)) {
			id_Urusan = getParam("id_Urusan");
			id_Suburusan = getParam("id_Suburusan");
		    this.context.put("id_Urusan", id_Urusan);
		    this.context.put("id_Suburusan", id_Suburusan);
		    
		    this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,"onChange=\"doChangesUrusan()\" "));
	        this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"id_Suburusan",id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null,"onChange=\"doChangesUrusan()\" "));
	 	
		} else if ("delete".equals(submit)) { 
	 		f.delete(id);
			id_Suburusan = (String)context.get("id_Suburusan");
			id_Urusan = (String)context.get("id_Urusan");
			
	 	} else if ("edit".equals(submit)) { 
	 		this.context.put("mode", "edit");
	 		template = PATH+"frmSetupSuburusanDokumenEdit.jsp";
	 		id_Urusan = getParam("id_Urusan");	 		
 			id_Suburusan = getParam("Form_id_Urusan");
	 		Hashtable<?, ?> details = f.getDetailsV1(id);
	 		this.context.put("details", (Hashtable<?, ?>)details);
 			//id_Suburusan = getParam("Form_id_Suburusan");

	 		id_status = (String)details.get("idstatus");//
		    this.context.put("selectUrusan2",HTML.SelectUrusan("Form_id_Urusan",Utils.parseLong(id_Urusan),null,null));
	        this.context.put("selectSuburusan2",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"Form_id_Suburusan",id_Suburusan==""?0L:Utils.parseLong(id_Suburusan),null));
	        	//this.context.put("selectStatus2",FrmKPIHTML.SelectStatusFailByUrusan("Form_id_status",Utils.parseLong(id_status),null,null,id_Urusan==""?null:id_Urusan,id_Suburusan==""?"0":id_Suburusan));
	        //this.context.put("selectStatus2",UtilHTML.SelectStatus("Form_id_status", Utils.parseLong(id_status), ""));
	        this.context.put("id",id);
			//Vector<?> v = new Vector();
			//v = FrmKPIData.getStatusFailBySuburusan(id_Urusan, id_Suburusan);
	        //this.context.put("langkah",v);
	            
	        String sg = ekptg.model.htp.UtilHTML.SelectGroup("selectgroup","",""," onChange=\"doChanges1()\" ");
	        //System.out.println(this.className+":sg::"+sg);
	        this.context.put("selectgroup",sg);
	        
	        String moduleList = ekptg.model.htp.UtilHTML.SelectModules("Form_module_id",(String)details.get("moduleid"),"",null);
	        this.context.put("selectmodul",moduleList);
	
	               
	        return template;
	        
	 	}else if ("addNewPejabat".equals(submit)) { 
			this.context.put("mode", "add");
			this.context.put("details",null);
	 		if ("filterDaerah".equals(action)) {
	 			id_Suburusan = getParam("Form_id_Suburusan");
	 		} else {
	  			id_Urusan = getParam("id_Urusan");
				id_Suburusan = getParam("id_Suburusan");
				//id_Suburusan = (String)this.context.get("id_Suburusan");
	 			id_status = getParam("Form_id_Status");
	
	 		}
			//id_Urusan = getParam("Form_id_Urusan");
			//id_status = getParam("Form_id_Status");
		    this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,null));
	        this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"Form_id_Suburusan",id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null));
	        //this.context.put("selectStatus2",FrmKPIHTML.SelectStatusFailByUrusan("Form_id_Status",null,null,null,id_Urusan==""?null:id_Urusan,id_Suburusan==""?"0":id_Suburusan));
	        this.context.put("selectStatus2",HTML.SelectStatus("Form_id_Status",id_status==""?null:Utils.parseLong(id_status),""));
	 		
	    	//Vector<?> v = new Vector();
			//v = FrmKPIData.getStatusFailBySuburusan(id_Urusan, id_Suburusan);
	        //this.context.put("langkah",v);
	            
	        String sg = ekptg.model.htp.UtilHTML.SelectGroup("group_Id","",""," onChange=\"doChanges1()\" ");
	        //System.out.println(this.className+":sg::"+sg);
	        this.context.put("selectgroup",sg);
	
	        String moduleList = ekptg.model.htp.UtilHTML.SelectModules("Form_module_Id","","",null);
	        //System.out.println(this.className+":moduleList::"+moduleList);
	        //session.setAttribute("senaraimodul", moduleList);
	        this.context.put("selectmodul",moduleList);
	
	    	Hashtable details = null;
	    	details.put("peringkat", "");
	    	details.put("template", "");
	    	details.put("dokumen", "");
	    	details.put("jenisdokumen", "");
	    	details.put("moduleid", "");
	 		this.context.put("details", (Hashtable)details);
	 		template = PATH+"frmSetupSuburusanDokumenEdit.jsp";
	 		return template;
	 		
	 	} else if ("doAdd".equals(submit)) {
	 		this.context.put("mode", "doAdd");
	 		template = PATH+"frmSetupSuburusanDokumenEdit.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (FrmSetupSuburusanDokumenData.insert(parameters, (String)session.getAttribute("_ekptg_user_id")))
	 			this.context.put("result", "success");
	 		else 
	 			this.context.put("result", "failed");
	 	} else if ("doUpdate".equals(submit)) {
	 		this.context.put("mode", "update");
	 		template = PATH+"frmSetupSuburusanDokumenEdit.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (f.update(parameters, id)) this.context.put("result", "success");
	 		else this.context.put("result", "failed");
	 		Hashtable details = f.getDetailsV1(id);
	 		this.context.put("details", (Hashtable)details);	 		 		
	 		return template;
	 		
	 	} else if ("view-daerahjagaan".equals(submit)) {
	 		template = PATH+"frmSetupSuburusanDokumenEdit.jsp";
	 		lists = f.getListingDaerahJagaan(id);
	 		setupPage(session,action,lists);
	 		Hashtable details = f.getDetails(id);
	 		this.context.put("details", (Hashtable)details);
	 		return template;
	 		
	 	} else if ("goBack".equals(submit)) { 
	 		this.context.put("selectSuburusan",this.context.get("selectSuburusan"));
		    id_Suburusan = (String)this.context.get("id_Suburusan");
		    id_Urusan = (String)this.context.get("id_Urusan");
	 		
	 	} else {
			  id_Urusan = getParam("id_Urusan")==""?null:getParam("id_Urusan");
			  id_Suburusan = getParam("id_Suburusan")==""?null:getParam("id_Suburusan");
			  myLog.info("id_Urusan="+id_Urusan +",id_Suburusan="+ id_Suburusan);
			  //id_daerah = getParam("id_daerah");
		      this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",null,null,"onChange=\"doChangesUrusan()\" "));
		      //this.context.put("selectSuburusan",HTML.SelectSuburusan("id_Suburusan"));
		      this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(id_Urusan==null?"0":id_Urusan,"id_Suburusan",id_Suburusan==null?0L:Utils.parseLong(id_Suburusan),null,"onChange=\"doChangesUrusan()\" "));

		      //return template;
		}
		lists = f.getListing(id_Urusan, id_Suburusan);
		//this.context.put("lists", lists); 
	    this.context.put("id_Urusan", id_Urusan);
	    this.context.put("id_Suburusan", id_Suburusan);
	    setupPage(session,action,lists);
	
		
		return template;
	}

	/*public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("listNotis",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	} */
	
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