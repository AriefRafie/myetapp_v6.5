/**
 * 
 */
package ekptg.view.integrasi.sid;

import integrasi.rest.sid.SIDManager;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;

public class FrmCarianFailPPT extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");

		Vector list = new Vector();
		
		String noFail = getParam("txtNoFail");
		String noHakmilik = getParam("txtNoHakmilik");
		String noLot = getParam("txtNoLot");

		try {			
			
			if ("carian".equals(submit) || "doChanges".equals(submit)) {
				if ("".equals(noFail))
					noFail = "-";
				if ("".equals(noHakmilik))
					noHakmilik = "-";
				if ("".equals(noLot))
					noLot = "-";
				
				list = SIDManager.getArkibDokumenPPT(noFail, noHakmilik, noLot);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.context.put("SenaraiFail", list);
		this.context.put("noFail", getParam("txtNoFail"));
		this.context.put("noHakmilik", getParam("txtNoHakmilik"));
		this.context.put("noLot", getParam("txtNoLot"));
		System.out.println(SIDManager.getFlagMsg());
		System.out.println(SIDManager.getOutputMsg());

		setupPage(session, action, list);

		return "app/integrasi/sid/frmCarianFailPPT.jsp";
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
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
			this.context.put("SenaraiFail", paging.getPage(page));
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
