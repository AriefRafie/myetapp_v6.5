package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.integrasi.FrmModelMyInfoSPTB;

@SuppressWarnings("serial")
public class FrmViewMyInfoSPTB extends AjaxBasedModule {

	static Logger myLogger = Logger.getLogger(FrmViewMyInfoSPTB.class);	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String userName = "",_portal_role = "", userRole = "", userID = "";
    Boolean isSPTBUser = false;
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoSPTB.jsp";

        HttpSession session = this.request.getSession();
        
        FrmModelMyInfoSPTB modelSPTB = new FrmModelMyInfoSPTB();
        
        userName = (String) session.getAttribute("_portal_username");
        userID = (String) session.getAttribute("_ekptg_user_id");
        userRole = (String) session.getAttribute("_portal_role");
        _portal_role = (String) session.getAttribute("_portal_role");
        
        isSPTBUser = isSPTBUser(userRole);
        
    	Vector vList = new Vector();
    	Vector vListPPK = new Vector();

        vList = modelSPTB.searchSPTB(false, userID);
        vListPPK = modelSPTB.searchSPTBPPK(false, userID);
        context.put("ListSPTB", vList);
        context.put("ListSPTBPPK", vListPPK);
        vList = modelSPTB.searchSPTB(true, userID);
        vListPPK = modelSPTB.searchSPTBPPK(true, userID);
        context.put("ListSPTBSelesai", vList);
        context.put("ListSPTBSelesaiPPK", vListPPK);
        context.put("isSPTBUser", isSPTBUser);
        context.put("_portal_role",_portal_role);
        
    	return vm;
	}
	
	private Boolean isSPTBUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "sptb".equalsIgnoreCase(userRole);
		return returnValue;
	}
}