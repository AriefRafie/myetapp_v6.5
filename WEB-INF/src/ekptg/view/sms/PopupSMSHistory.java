package ekptg.view.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.sms.PopupSMSHistoryData;
import ekptg.model.sms.mySMSData;

public class PopupSMSHistory extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 102242219690179716L;

	public String doTemplate2() throws Exception {

		String vm = "app/mySMS/popupHistorySMS.jsp";
		String submit = getParam("command");
        String action = getParam("action");
        String jenisSMSHistory = getParam("socJenisSMSHistory");
        context.put("jenisSMSHistory", jenisSMSHistory);
        context.put("action",action);
        Vector historyData = null;
        Vector listPerbicaraan = null;
        Vector listPerintah = null;
        Vector kodPejabat = null;
        PopupSMSHistoryData popupSMSHistory = new PopupSMSHistoryData();
        HttpSession session = this.request.getSession();
        mySMSData mySMS = new mySMSData();
        Hashtable hKodPejabat;
        String user_id = (String)session.getAttribute("_ekptg_user_id");
        Date now = new Date();
        SimpleDateFormat Format =  new SimpleDateFormat("ddMMyyyy-hhmm");
        
        if("cari".equals(action)){
        	popupSMSHistory.historyData(jenisSMSHistory);
 		    historyData = popupSMSHistory.getHistoryData();
 	        context.put("SenaraiHistory",historyData);
 	        context.put("historyData",historyData.size());
 	        setupPageHistoryData(session,action,historyData);
 	        vm = "app/mySMS/popupHistorySMS.jsp";
        }
        else{
 		    popupSMSHistory.historyData(jenisSMSHistory);
 		    historyData = popupSMSHistory.getHistoryData();
 	        context.put("SenaraiHistory",historyData);
 	        context.put("historyData",historyData.size());
 	        setupPageHistoryData(session,action,historyData);
 	        context.put("jenisSMSHistory", "");
 	        vm = "app/mySMS/popupHistorySMS.jsp";
        }
		
		return vm;
	}

	public void setupPageHistoryData(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiHistory",paging.getPage(page));
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



}
