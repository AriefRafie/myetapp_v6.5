package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.app.RoleProcessor;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.utils.FrmSuburusanRoleData;


public class FrmSetupSuburusanRole extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2905313678230026597L;
	static Logger mylog = Logger.getLogger(ekptg.view.utils.FrmSetupSuburusanRole.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String template = "app/utils/frmSuburusanRole.jsp";
	
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		String action = getParam("action"); // Second Level
	
		String id_Urusan = null;
		String id_Suburusan = null;

		Vector<?> lists = null;
		this.context.put("result", "");
		//Form submitted values
		String id = getParam("id");
	
		//FrmSuburusanStatusData f = new FrmSuburusanStatusData();
		FrmSuburusanRoleData roleData = new FrmSuburusanRoleData();
        RoleProcessor processor = new RoleProcessor();
		mylog.info(submit+"-->"+action);
	    this.context.put("roleList",  processor.getRoles());
	    this.context.put("userRole",  getParam("Form_name"));
	
		if ("doChanges".equals(submit)) {
			id_Urusan = getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan");
			this.context.put("id_Urusan", id_Urusan);
			this.context.put("Form_id_Suburusan", id_Suburusan);    
			this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,"onChange=\"doChanges()\" "));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"Form_id_Suburusan",id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null,"onChange=\"doChanges()\" "));
		    String mymode = (String)this.context.get("mode");
		    if(mymode=="edit" || mymode=="add" || mymode=="update")
		 		template = "app/utils/frmSuburusanRoleEdit.jsp";

		} else if ("pilihrole".equals(submit)) { 
			id_Urusan = getParam("id_Urusan")==""?null:getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan")==""?null:getParam("Form_id_Suburusan");
		    this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",null,null,"onChange=\"doChanges()\" "));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("Form_id_Suburusan"));
		    //this.context.put("roleList",  processor.getRoles());
		}else if ("delete".equals(submit)) { 
			roleData.delete(id);
			id_Urusan = (String)context.get("id_Urusan");
			id_Suburusan = (String)context.get("Form_id_Suburusan");
		} else if ("edit".equals(submit)) { 
	 		this.context.put("mode", "edit");
	 		template = "app/utils/frmSuburusanRoleEdit.jsp";
	 		Hashtable<?, ?> details = roleData.getDetails(id);
	 		this.context.put("details", (Hashtable<?, ?>)details);
	 		id_Suburusan = (String)details.get("idsuburusan");
	 		id_Urusan = (String)details.get("idurusan");
		    this.context.put("selectUrusan2",HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,null));
	        this.context.put("selectSuburusan2",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?null:id_Urusan,"Form_id_Suburusan",id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null));
		 	//this.context.put("id", id);
    
	        return template;       
		}else if ("tambah".equals(submit)) { //live
			this.context.put("mode", "add");
			template = "app/utils/frmSuburusanRoleEdit.jsp";
			this.context.put("details",null);
			id_Urusan = getParam("id_Urusan");
			id_Suburusan = getParam("Form_id_Suburusan");

		    this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",Utils.parseLong(id_Urusan),null,"onChange=\"doChanges()\" "));
		    //this.context.put("selectUrusan",HTML.SelectUrusan("id_Urusan",null,null,"onChange=\"doChanges()\" "));
	        this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(id_Urusan==""?"0":id_Urusan,"Form_id_Suburusan",id_Suburusan==""?null:Utils.parseLong(id_Suburusan),null));

	        return template;
		} else if ("simpan".equals(submit)) { //live
	 		this.context.put("mode", "doAdd");
	 		template = "app/utils/frmSuburusanRoleEdit.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (FrmSuburusanRoleData.insert(parameters, (String)session.getAttribute("_ekptg_user_id")))
	 			this.context.put("result", "success");
	 		else 
	 			this.context.put("result", "failed");
		} else if ("doUpdate".equals(submit)) {
	 		this.context.put("mode", "update");
	 		template = "app/utils/frmSuburusanRoleEdit.jsp";
	 		Hashtable<String, String> parameters = new Hashtable<String, String>();
			setParameterValues(parameters);
	 		if (roleData.update(parameters, id)) this.context.put("result", "success");
	 		else this.context.put("result", "failed");
	 		Hashtable<?, ?> details = roleData.getDetails(id);
	 		this.context.put("details", (Hashtable<?, ?>)details);
	 		return template;
		}  else if ("goBack".equals(submit)) { 
	 		this.context.put("selectSuburusan",this.context.get("selectSuburusan"));
		    id_Suburusan = (String)this.context.get("id_Suburusan");
		    id_Urusan = (String)this.context.get("id_Urusan");
 		
		} else {
			  id_Urusan = getParam("id_Urusan")==""?null:getParam("id_Urusan");
			  id_Suburusan = getParam("Form_id_Suburusan")==""?null:getParam("Form_id_Suburusan");
		      this.context.put("selectUrusan",HTML.SelectUrusan("Form_id_Urusan",null,null,"onChange=\"doChanges()\" "));
		      this.context.put("selectSuburusan",HTML.SelectSuburusan("Form_id_Suburusan"));
		      this.context.put("roleList",  processor.getRoles());
	
		      return template;
		}
		
		lists = FrmUtilData.getSubUrusanByRole(null,getParam("Form_name"));
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
	Enumeration<?> allparam = request.getParameterNames();
	for (; allparam.hasMoreElements(); ) {
        name = (String)allparam.nextElement();
        if (name.indexOf("Form_") != -1) { // get only parameters with name like Form_
	        value = request.getParameter(name);
	        parameters.put(name,value);
        }
	}
}

}