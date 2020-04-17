package ekptg.view.integrasi.macgdi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.macgdi.FrmLaporanHTPData;

public class FrmLaporanHTP extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8156815674706248570L;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		
        String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        String id = getParam("id");
        String command = getParam("command");
        String hitButton = getParam("hitButton");
        FrmLaporanHTPData laporanHTPData = new FrmLaporanHTPData();
        String vm = "";
        Vector vList = null;
        
        vList = laporanHTPData.list();
		context.put("SenaraiFail", vList);
		if(vList.size()!=0){
			Hashtable h = (Hashtable)vList.get(0);
			this.context.put("id",  h.get("ID"));
		}
        vm = "app/integrasi/macgdi/frmLaporanHTP.jsp"; 

	    return vm;
	}
}

