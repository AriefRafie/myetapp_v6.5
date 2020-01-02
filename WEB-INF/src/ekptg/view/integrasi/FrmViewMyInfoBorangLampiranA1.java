package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.FrmModelBorangLampiranA1;
import ekptg.model.integrasi.FrmModelMyInfoBorangLampiranA1;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewMyInfoBorangLampiranA1 extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
    String userName = "", userRole = "", userID = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoBorangLampiranA1.jsp";

        HttpSession session = this.request.getSession();
        
       // System.out.println(FrmModelETanah.saveETanahPusaka("1", "4522", "8507"));
        
        FrmModelMyInfoBorangLampiranA1 modelBorang = new FrmModelMyInfoBorangLampiranA1();
        FrmModelBorangLampiranA1 logic = new FrmModelBorangLampiranA1();
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        
        Boolean isJPBDUser = isJPBDUser(userRole);
        Boolean isPegawaiJPBD = logic.isPegawaiJPBD(userID);
        
        String command = getParam("command");
        
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();

    	if ("viewFullPermohonan".equalsIgnoreCase(command)) {
    		String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
    			vList = modelBorang.viewFullPermohonan(ID_PERMOHONAN);
    			context.put("selectedFullPermohonan", ID_PERMOHONAN);
        		context.put("viewFullPermohonan", "true");
    			context.put("ListFullPermohonan", vList);
    		}
    	}
        vList = modelBorang.searchBorangLampiranA1(false);
        context.put("ListBorangLampiranA1", vList);
        vList = modelBorang.searchBorangLampiranA1(true);
        context.put("ListBorangLampiranA1Selesai", vList);
        vList = modelBorang.searchBorangLampiranA1TungguPengesahan();
        context.put("ListBorangLampiranA1Pengesahan", vList);
	
        context.put("isJPBDUser", isJPBDUser);
        context.put("isPegawaiJPBD", isPegawaiJPBD);
        
    	return vm;
	}
	
	private Boolean isJPBDUser(String USER_ROLE) throws Exception {
		Boolean returnValue = false;
		if ("jpbd".equalsIgnoreCase(USER_ROLE)) {
			returnValue = true;
		}
		return returnValue;
	}
}