package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.UtilHTML;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.utils.FrmSuburusanStatusData;


public class FrmSuburusanStatus extends AjaxBasedModule {
	/**
	 * 
	 */
	private static Logger myLog = Logger.getLogger(ekptg.view.utils.FrmSuburusanStatus.class);
	private static final long serialVersionUID = -2022684756290557175L;

	private String selectSuburusan = "";
	private String selectUrusan = "";
	Hashtable<?, ?> details = null;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String template = "app/utils/frmSuburusanStatus.jsp";		
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		String action = getParam("action"); // Second Level		
		String id_Urusan = null;
		String id_Suburusan = null;
		String id_status = null;
	    Vector lists = new Vector();
	    this.context.put("result", "");
	    //Form submitted values
	    String id = getParam("id");	
	    FrmSuburusanStatusData f = new FrmSuburusanStatusData();
		this.context.remove("selectUrusan");
		this.context.remove("selectSuburusan");
		  
		myLog.info("submit="+submit);
		if ("doChanges".equals(submit)) {
			id_Urusan = getParam("id_Urusan");
			id_Suburusan = getParam("id_Suburusan");
		    this.context.put("id_Urusan", id_Urusan);
		    this.context.put("id_Suburusan", id_Suburusan);		    
		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,"onChange=\"doChanges()\" ");
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null,"onChange=\"doChanges()\" ");

		} else if ("delete".equals(submit)) { 
	 		f.delete(id);
	 		id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
			id_Suburusan = getParam("id_Suburusan")==""?"0":getParam("id_Suburusan");	
		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null
		    		," onChange=\"doChangesSubmit('addNewPejabat')\"");
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null);
 	
		} else if ("edit".equals(submit)) { 
	 		this.context.put("mode", "edit");
	 		template = "app/utils/frmEditSuburusanStatus.jsp";
	 		details = f.getDetails(id);
	 		this.context.put("details", (Hashtable<?, ?>)details);
	 		//Re-do
	 		if ("filterDaerah".equals(action)) {
	 			id_Suburusan = getParam("Form_id_Suburusan");
	 		} else {
	 			id_Suburusan = (String)details.get("id_Suburusan");
	 		}
	 		id_Urusan = (String)details.get("id_Urusan");
	 		id_status = (String)details.get("idstatus");
	 		//myLog.info("edit:id_Urusan="+id_Urusan);
	 		//myLog.info("edit:id_Suburusan="+id_Suburusan);
	 		selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,null);
	 		selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"Form_id_Suburusan"
	 				,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null);
	        this.context.put("selectStatus2",UtilHTML.SelectStatus("Form_id_status", Utils.parseLong(id_status), ""));
	        this.context.put("id",id);
			Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			v = FrmKPIData.getStatusFailBySuburusan(id_Urusan, id_Suburusan);
	        this.context.put("langkah",v);	            
	        String sg = ekptg.model.htp.UtilHTML.SelectGroup("selectgroup","",""," onChange=\"doChanges1()\" ");
	        this.context.put("selectgroup",sg);	        
	        String moduleList = ekptg.model.htp.UtilHTML.SelectModules("Form_module_id",(String)details.get("moduleid")
	        		,"",null);
	        this.context.put("selectmodul",moduleList);
	        
	 	}else if ("addNewPejabat".equals(submit)) { 
	 		template = "app/utils/frmEditSuburusanStatus.jsp";
	 		this.context.put("mode", "add");
			this.context.put("details",null);
	 		if ("filterDaerah".equals(action)) {
	 			//myLog.info("filterDaerah");
	  			id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
				id_Suburusan = getParam("Form_id_Suburusan")==""?"0":getParam("Form_id_Suburusan");	 			
	 			
	 		} else {
	 			//myLog.info("X filterDaerah");
	  			id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
	  			id_Suburusan = getParam("id_Suburusan")==""?"0":getParam("id_Suburusan");	 			
				id_status = getParam("Form_id_Status")==""?"0":getParam("Form_id_Status");
	
	 		}
//	 		myLog.info("edit:id_Urusan="+id_Urusan);
//	 		myLog.info("edit:id_Suburusan="+id_Suburusan);
//	 		myLog.info("edit:id_status="+id_status);

		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null
		    		," onChange=\"doChangesSubmit('addNewPejabat')\"");
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"Form_id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null);
	        this.context.put("selectStatus2",UtilHTML.SelectStatus("Form_id_Status"
	        		,id_status==""?null:Utils.parseLong(id_status),""));
	 		
	    	Vector<Hashtable<String, String>> v = new Vector<Hashtable<String, String>>();
			v = FrmKPIData.getStatusFailBySuburusan(id_Urusan, id_Suburusan);
	        this.context.put("langkah",v);        
	        String sg = ekptg.model.htp.UtilHTML.SelectGroup("group_Id","",""," onChange=\"doChanges1()\" ");
	        this.context.put("selectgroup",sg);
	        String moduleList = ekptg.model.htp.UtilHTML.SelectModules("Form_module_Id","","",null);
	        this.context.put("selectmodul",moduleList);
	
	 	} else if ("doAdd".equals(submit)) {
	 		this.context.put("mode", "doAdd");
	 		template = "app/utils/frmEditSuburusanStatus.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (FrmSuburusanStatusData.insert(parameters, (String)session.getAttribute("_ekptg_user_id")))
	 			this.context.put("result", "Simpan");
	 		else 
	 			this.context.put("result", "failed");

	 		details = f.getDetails(FrmSuburusanStatusData.idSubUrusanStatus);
	 		this.context.put("details", (Hashtable)details);

	 		id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan")==""?"0":getParam("Form_id_Suburusan");	
		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null
		    		," onChange=\"doChangesSubmit('addNewPejabat')\"");
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"Form_id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null);
	 		
	 	} else if ("doUpdate".equals(submit)) {
	 		this.context.put("mode", "update");
	 		template = "app/utils/frmEditSuburusanStatus.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (f.update(parameters, id)) this.context.put("result", "Kemaskini");
	 		else this.context.put("result", "failed");
	 		details = f.getDetails(id);
	 		this.context.put("details", (Hashtable)details);
	 		id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan")==""?"0":getParam("Form_id_Suburusan");
 	
		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,null);
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"Form_id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null);
	 		
	 	} else if ("view-daerahjagaan".equals(submit)) {
	 		template = "app/utils/frmDaerahJagaan.jsp";
	 		lists = f.getListingDaerahJagaan(id);
	 		setupPage(session,action,lists);
	 		Hashtable details = f.getDetails(id);
	 		this.context.put("details", (Hashtable)details);
	 	
	 	} else if ("goBack".equals(submit)) { 
			id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan")==""?"0":getParam("Form_id_Suburusan"); 	
		    selectUrusan = HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null," onChange=\"doChanges()\" ");
		    selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"id_Suburusan"
		    		,id_Suburusan==""?null:Utils.parseLong(id_Suburusan)," onChange=\"doChanges()\" ");
	 		
	 	} else {
			  id_Urusan = getParam("id_Urusan")==""?"0":getParam("id_Urusan");
			  id_Suburusan = getParam("id_Suburusan")==""?"0":getParam("id_Suburusan");
			  selectUrusan = HTML.SelectUrusan("id_Urusan",Long.parseLong(id_Urusan),null,"onChange=\"doChanges()\" ");
			  selectSuburusan = HTML.SelectSuburusan("id_Suburusan",Long.parseLong(id_Suburusan)
					  ," onChange=\"doChanges()\"");
			  if(!id_Urusan.equals("0")){
				  selectSuburusan = HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"id_Suburusan"
						  ,id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null,"onChange=\"doChanges()\" ");				  
			  }
		}
	    this.context.put("selectUrusan",selectUrusan);
        this.context.put("selectSuburusan",selectSuburusan);
        lists = f.getListing(id_Urusan,id_Suburusan);
        if(lists.isEmpty()==false)
        	setupPage(session,action,lists);		
	
        return template;
		
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