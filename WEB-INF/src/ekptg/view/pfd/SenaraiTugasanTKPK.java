package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.pfd.FrmSenaraiTugasanTKPK;

public class SenaraiTugasanTKPK extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception

    {

		 String vm = "";
		 String submit = getParam("command");
         String action1 = getParam("action1");
         String action = getParam("action");
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
         String mode = getParam("mode");
         Vector listLogDokumenByUserId = new Vector();
         Vector listAgihanTugasanByUserId = new Vector();
         Vector listPenerimaTugasanByUserId = new Vector();
         Vector listLogDokumenByUserIdSelesai = new Vector();
         Vector listAgihanTugasanByUserIdSelesai = new Vector();
         Vector listPenerimaTugasanByUserIdSelesai = new Vector();
         HttpSession session = this.request.getSession();
         Date now = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
        // myLogger.debug("action:"+action);
         
         if("tabLogDokumenMasuk".equals(action1)){

         }
         
         else if("logDokumenSelesai".equals(action1)){
        	vm = "app/pfd/frmSenaraiTugasanTKPK.jsp";	
        	context.put("pagemode",1);
        	
        	listLogDokumenByUserIdSelesai(session);
 	    	listLogDokumenByUserIdSelesai = FrmSenaraiTugasanTKPK.getListLogDokumenSelesai();
 	    	this.context.put("SenaraiDokumenByUserIdSelesai", listLogDokumenByUserIdSelesai);
         }
         
         else if("agihanTugasanSelesai".equals(action1)){
        	vm = "app/pfd/frmSenaraiTugasanTKPK.jsp";	
        	context.put("pagemode",2);
        	
        	listAgihanTugasanByUserIdSelesai(session);
        	listAgihanTugasanByUserIdSelesai = FrmSenaraiTugasanTKPK.getListAgihanTugasanSelesai();
	    	this.context.put("SenaraiAgihanTugasanByUserIdSelesai",listAgihanTugasanByUserIdSelesai);
         }
         
         else
         {
        	vm = "app/pfd/frmSenaraiTugasanTKPK.jsp";  	
        	context.put("pagemode",3);
        	
        	listLogDokumenByUserId(session);
 	    	listLogDokumenByUserId = FrmSenaraiTugasanTKPK.getListLogDokumen();
 	    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
        	
        	listAgihanTugasanByUserId(session);
        	listAgihanTugasanByUserId = FrmSenaraiTugasanTKPK.getListAgihanTugasan();
	    	this.context.put("SenaraiAgihanTugasanByUserId",listAgihanTugasanByUserId);
        	 
         }
    
    
    
	return vm;
	}

	private void listAgihanTugasanByUserId(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmSenaraiTugasanTKPK.setListAgihanTugasan(user_id);
		
	}
	
	private void listAgihanTugasanByUserIdSelesai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmSenaraiTugasanTKPK.setListAgihanTugasanSelesai(user_id);
		
	}

	private void listLogDokumenByUserId(HttpSession session) throws Exception {
		 String user_id = (String)session.getAttribute("_ekptg_user_id");
		 FrmSenaraiTugasanTKPK.setListLogDokumenByUserId(user_id);
		
	}
	
	private void listLogDokumenByUserIdSelesai(HttpSession session) throws Exception {
		 String user_id = (String)session.getAttribute("_ekptg_user_id");
		 FrmSenaraiTugasanTKPK.setListLogDokumenByUserIdSelesai(user_id);
		
	}

}
