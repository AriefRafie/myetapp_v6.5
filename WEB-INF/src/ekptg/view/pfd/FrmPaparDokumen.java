package ekptg.view.pfd;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.pfd.FrmPaparDokumenData;

public class FrmPaparDokumen extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception

    {
			 String action = getParam("action");
			 HttpSession session = this.request.getSession();
			 Vector list = new Vector();
			 String disability1 = "";
			 String vm = "";
			 vm = "app/pfd/frmPaparDokumen.jsp";
		
			disability1 = "disabled";
        	view_Dokumen(session);
        	list = FrmPaparDokumenData.getDataDokumen();
        	
        	this.context.put("Dokumen",list);
       
        	
    		view_MinitArahan(session);
    		list = FrmPaparDokumenData.getListMinitArahan();
    		this.context.put("MinitArahan",list);
        	
        	



            return vm;

    }
	private void view_Dokumen(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idDokumen"));
    	FrmPaparDokumenData.setDataDokumen(id);
	   
	  }
	private void view_MinitArahan(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idDokumen"));
    	FrmPaparDokumenData.setListMinitArahan(id);
	   
	  }

}
