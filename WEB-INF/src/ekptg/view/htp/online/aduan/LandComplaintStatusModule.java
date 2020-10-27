package ekptg.view.htp.online.aduan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.Paging;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.EkptgPublicComplaintHandler;
import ekptg.model.online.aduan.IEkptgPublicComplaintHandler;

public class LandComplaintStatusModule extends AjaxModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563234412401545875L;
	private final String PATH ="app/htp/online/aduantanah/status/";
	private String vm = PATH+"index.jsp";
	String userId = null;
	private IEkptgPublicComplaintHandler handler;
	@Override
	public String doAction() throws Exception {
		String command = getParam("command");
		HttpSession session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		String action = getParam("action");
		
		if(command.equals("viewComplaint")){
//			String noAduan = getParam("noAduan");
//			Complaint complaint = getHandler().getComplaint(noAduan);
			String noAduan = getParam("noAduan");
			Complaint complaint = getHandler().getComplaint(noAduan);
			if(complaint == null){
				complaint = new Complaint();
				complaint.setStatus("REKOD ADUAN TIDAK DITEMUI");
			}
			context.put("complaint", complaint);
			vm =PATH+"view.jsp";		
				
		}else{
//			context.remove("complaint");
			vm = PATH+"index.jsp";
			Vector<Complaint> v = getHandler().getComplaintByUserId(userId);
			context.put("lists", v);
			setupPage(session, action, v);
		}
		return vm;
	}
	
	private IEkptgPublicComplaintHandler getHandler(){
		if(handler == null)
			handler = new EkptgPublicComplaintHandler();
		return handler;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
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

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiAduan", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
}
