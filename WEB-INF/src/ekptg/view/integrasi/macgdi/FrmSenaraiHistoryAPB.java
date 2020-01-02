package ekptg.view.integrasi.macgdi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.macgdi.FrmLaporanHTPData;

public class FrmSenaraiHistoryAPB extends AjaxBasedModule{
	private static final long serialVersionUID = -3576494755269810197L;

	@Override
	public String doTemplate2() throws Exception {
HttpSession session = request.getSession();
		
		FrmLaporanHTPData laporanHTPData = new FrmLaporanHTPData();        
		String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        String id = getParam("id");
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
		
		  vList = laporanHTPData.paparAPB(id);
		  context.put("SenaraiFail", vList);
			
			if(vList.size()!=0){
				Hashtable h = (Hashtable)vList.get(0);
				this.context.put("id",  h.get("ID"));
				this.context.put("NamaPengguna",  h.get("USER_NAME"));
				this.context.put("TarikhCetak",  h.get("TARIKH_CETAK"));
	         
		}
			vm = "app/integrasi/macgdi/apb/frmHistoryAPB.jsp";
		
		return vm;
		
	}
}

