package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;


public class MinitArahanTKPK extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception

    {

		 String vm = "";
		 String submit = getParam("command");
         String action = getParam("action");
         String mode = getParam("mode");
         HttpSession session = this.request.getSession();
         Date now = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
        // myLogger.debug("action:"+action);
         
         if("tabLogDokumenMasuk".equals(action)){
        	 
         }
         else
         {
        	 vm = "app/pfd/frmKemaskiniMinitArahanTKPK.jsp";
        	 
         }
    
    
    
	return vm;
	}

}
