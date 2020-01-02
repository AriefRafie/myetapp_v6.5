package ekptg.view.ppk.integrasi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.lhdn.FrmSenaraiLHDNData;

public class FrmPopupHistory extends AjaxBasedModule{
	private static final long serialVersionUID = -3576494755269810197L;

	@Override
	public String doTemplate2() throws Exception {
HttpSession session = request.getSession();
		
		FrmSenaraiLHDNData senaraiLHDNData = new FrmSenaraiLHDNData();        
		String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        String idFail = getParam("idFail");
        String vm = "";
        String command = getParam("command");
        String hitButton = getParam("hitButton"); 
        boolean flagLoadData = false;
        Vector vList = null;
        Vector list = null;
		if(!hitButton.equals(""))
		{
			command = "";
		}
		
		  vList = senaraiLHDNData.list(idFail);
		  context.put("SenaraiFail", vList);
			
			if(vList.size()!=0){
					Hashtable h = (Hashtable)vList.get(0);
					this.context.put("idFail",  h.get("ID_FAIL"));
	        
		}
			vm = "app/ppk/perintah/senaraiHistory.jsp"; 
		return vm;
		
	}
}

