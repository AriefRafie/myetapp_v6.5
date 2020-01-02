package ekptg.view.ppk.integrasi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.lhdn.FrmSenaraiLHDNData;

public class FrmSenaraiPerintahLHDN extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8156815674706248570L;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ

        FrmSenaraiLHDNData senaraiLHDNData = new FrmSenaraiLHDNData();
    
        String vm = "";
        Vector vList = null;

        String jenisKp = getParam("socJenisKp");
		if (jenisKp == null || jenisKp.trim().length() == 0){
			jenisKp = "0";
		}
		
		vm = "app/ppk/perintah/senaraiPerintah.jsp";
		
//		if(!getParam("txtNoFail").equals("") || !getParam("socJenisKp").equals("") || !getParam("txtIcSimati").equals("")){
			
			vList = senaraiLHDNData.carian(getParam("txtNoFail"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
			
//		}
		context.put("SenaraiFail", vList);
		setupPage(session, action, vList);
		
		this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));
        this.context.put("txtNoFail", getParam("txtNoFail"));
        this.context.put("txtIcSimati", getParam("txtIcSimati"));
		
        
        if(vList.size()!=0){
			Hashtable h = (Hashtable)vList.get(0);
			this.context.put("idFail",  h.get("ID_FAIL"));
		}
        
        return vm; 
	}
	
	public void setupPage(HttpSession session, String action, Vector list) {
		System.out.println(action);
//		System.out.println(list.size());
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

