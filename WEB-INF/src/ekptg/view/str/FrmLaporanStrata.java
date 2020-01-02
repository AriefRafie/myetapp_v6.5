package ekptg.view.str;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

public class FrmLaporanStrata extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8156815674706248570L;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		
        String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        
        String vm = "";
        
        
        vm = "app/str/frmLaporanStrata.jsp"; 
	    return vm;
	}

}

