package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.FrmModelMyInfoBorangJ;

@SuppressWarnings("serial")
public class FrmViewMyInfoBorangJ extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String userName = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoBorangJ.jsp";

        HttpSession session = this.request.getSession();
        
        FrmModelMyInfoBorangJ modelBorangJ = new FrmModelMyInfoBorangJ();
        
        userName = (String) session.getAttribute("_portal_username");
        
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();

        vList = modelBorangJ.searchBorangJ(false, userName);
        context.put("ListBorangJ", vList);
        vList = modelBorangJ.searchBorangJ(true, userName);
        context.put("ListBorangJSelesai", vList);
        
    	return vm;
	}
}