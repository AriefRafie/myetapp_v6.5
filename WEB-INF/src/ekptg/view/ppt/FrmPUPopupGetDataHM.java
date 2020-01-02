package ekptg.view.ppt;


import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPUPopupGetDataHM extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPUPopupGetDataHM.class);
	
	@SuppressWarnings("unchecked")
	Vector listHM = new Vector();

	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	//default
    	String vm = "";
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	context.put("showPaging", "no");
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("searchHM".equals(submit)){
    		
    		context.put("showPaging", "yes");
    		
    		//carian
    		ListCarianHM(session,userIdNeg);			
    		listHM = FrmUPTSek8HakmilikData.getListCarianHM();
    		context.put("listHM",listHM);
    		context.put("sizelistHM",listHM.size());
    		
    		setupPage(session,action,listHM);
    		
    	}else{
    		
    		clearPopupValue();
    		context.put("listHM","");
    		context.put("sizelistHM",0);
    	}
    	
    	//screen
    	vm = "app/ppt/PUstandalone/frmPopupGetDataHM.jsp";
    	myLogger.info("vm : "+vm);
    	return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	private void ListCarianHM(HttpSession session,String userIdNeg) throws Exception{
    	
		context.put("txtNoFail", getParam("txtNoFail").trim());
		context.put("txtNolot", getParam("txtNolot").trim());
		context.put("txtNoHakmilik", getParam("txtNoHakmilik").trim());
		context.put("txtNamaPB", getParam("txtNamaPB").trim());
		
		FrmUPTSek8HakmilikData.setListCarianPopupHM(getParam("txtNoFail").trim(),getParam("txtNolot").trim(),
											   getParam("txtNoHakmilik").trim(),getParam("txtNamaPB").trim(),userIdNeg,"");
      
	}//close listcarian

	private void clearPopupValue() throws Exception{  
		
		context.put("txtNoFail", "");
		context.put("txtNolot", "");
		context.put("txtNoHakmilik", "");
		context.put("txtNamaPB", "");
		
	}//close clearPopupValue
	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session,String action,Vector list) {
		
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
			this.context.put("listHM",paging.getPage(page));
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
	}	
	
}//close class
