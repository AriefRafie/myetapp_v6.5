package ekptg.view.integrasi;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelMyInfoNilaianHartaTakAlih;

@SuppressWarnings("serial")
public class FrmViewMyInfoNilaianHartaTakAlih extends AjaxBasedModule {

	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoNilaianJPPH.jsp";
		String action = getParam("action");

        HttpSession session = this.request.getSession();
        
        FrmModelMyInfoNilaianHartaTakAlih modelMyInfoHTA = new FrmModelMyInfoNilaianHartaTakAlih();
        
        String userRole = "", userID = "";
        userRole = (String) session.getAttribute("_portal_role") == null ? "" : (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id") == null ? "" : (String) session.getAttribute("_ekptg_user_id");
        
        Boolean isJPPHUser = isJPPHUser(userRole);
        context.put("isJPPHUser", isJPPHUser);
        
    	Vector<Object> vList = new Vector<Object>();
    	
    	context.remove("haveList");
    	context.remove("ListNilaian");

        vList = modelMyInfoHTA.searchNilaianHTA(0, isJPPHUser, userID);
        context.put("ListNilaianHTAAH", vList);
        vList = modelMyInfoHTA.searchNilaianHTA(1, isJPPHUser, userID);
        context.put("ListNilaianHTATH", vList);
        vList = modelMyInfoHTA.searchNilaianHAK(isJPPHUser, userID);
        context.put("ListNilaianHAK", vList);
        vList = modelMyInfoHTA.searchNilaianSewaan(isJPPHUser, userID);
        context.put("ListNilaianSewaan", vList);
        vList = modelMyInfoHTA.searchNilaianPajakan(isJPPHUser, userID);
        context.put("ListNilaianPajakan", vList);
        
        // k-mie, 23112011
        // request by pn aminah to put all in 1 page
        String action2 = getParam("action2");
        if ("searchNilaianHTATerdahulu".equalsIgnoreCase(action2)) {
    		String NO_FAIL = "", NO_PERMOHONAN = "", TARIKH_HANTAR_DARI = "", TARIKH_HANTAR_KE = "", TARIKH_SELESAI_DARI = "", TARIKH_SELESAI_KE = "", STATUS_FAIL = "";
    		NO_FAIL = getParam("Carian_NoFail");
    		NO_PERMOHONAN = getParam("Carian_NoPermohonan");
    		TARIKH_HANTAR_DARI = getParam("Carian_TarikhHantarDari");
    		TARIKH_HANTAR_KE = getParam("Carian_TarikhHantarKe");
    		TARIKH_SELESAI_DARI = getParam("Carian_TarikhSelesaiDari");
    		TARIKH_SELESAI_KE = getParam("Carian_TarikhSelesaiKe");
    		STATUS_FAIL = getParam("Carian_Status");
    		vList = modelMyInfoHTA.searchNilaianHartaTerdahulu(isJPPHUser, userID, NO_FAIL, NO_PERMOHONAN, TARIKH_HANTAR_DARI, TARIKH_HANTAR_KE, TARIKH_SELESAI_DARI, TARIKH_SELESAI_KE, STATUS_FAIL);
    		context.put("Carian_NoFail", NO_FAIL);
    		context.put("Carian_NoPermohonan", NO_PERMOHONAN);
    		context.put("Carian_TarikhHantarDari", TARIKH_HANTAR_DARI);
    		context.put("Carian_TarikhHantarKe", TARIKH_HANTAR_KE);
    		context.put("Carian_TarikhSelesaiDari", TARIKH_SELESAI_DARI);
    		context.put("Carian_TarikhSelesaiKe", TARIKH_SELESAI_KE);
    		context.remove("selectedStatus1");
    		context.remove("selectedStatus2");
    		context.remove("selectedStatus3");
    		context.remove("selectedStatus4");
    		if ("1".equalsIgnoreCase(STATUS_FAIL)) {
    			context.put("selectedStatus1", "selected=\"selected\"");
    		} else if ("2".equalsIgnoreCase(STATUS_FAIL)) {
    			context.put("selectedStatus2", "selected=\"selected\"");
    		} else if ("3".equalsIgnoreCase(STATUS_FAIL)) {
    			context.put("selectedStatus3", "selected=\"selected\"");
    		} else if ("4".equalsIgnoreCase(STATUS_FAIL)) {
    			context.put("selectedStatus4", "selected=\"selected\"");
    		}

    		//context.put("Carian_Status", STATUS_FAIL);
    		context.put("ListNilaian", vList);
    		context.put("haveList", "true");
    		setupPage(session, action, vList);
    	}
        context.put("uid", userID);
        context.put("action2", action2);

    	return vm;
	}
	
	private Boolean isJPPHUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "jpph".equalsIgnoreCase(userRole);
		return returnValue;
	}

	@SuppressWarnings("rawtypes")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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
		    	
		    Paging paging = new Paging(session, list, itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
			this.context.put("ListNilaian", paging.getPage(page));
		    this.context.put("page", new Integer(page));
		    this.context.put("itemsPerPage", new Integer(itemsPerPage));
		    this.context.put("totalPages", new Integer(paging.getTotalPages()));
		    this.context.put("startNumber", new Integer(paging.getTopNumber()));
		    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
		    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
		        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}	
	}
}