package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.model.integrasi.FrmModelMyInfoMaklumatKebangkrapan;

@SuppressWarnings("serial")
public class FrmViewMyInfoMaklumatKebangkrapan extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String userName = "", userRole = "";
    Boolean isJIMUser = false;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoMaklumatKebangkrapan.jsp";

        HttpSession session = this.request.getSession();
        
        FrmModelMyInfoMaklumatKebangkrapan model = new FrmModelMyInfoMaklumatKebangkrapan();
        
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        isJIMUser = isJIMUser(userRole);
        
    	Vector vList = new Vector();
    	
    	String action2 = getParam("action2");
    	if ("uploadFile".equalsIgnoreCase(action2.trim())) {
        	DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem objItem = (FileItem)itr.next();
		    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
		    		model.uploadFile(objItem);
		    	}
		    }
    	}

        vList = model.searchMaklumatKebangkrapan(false, userName);
        context.put("ListMaklumatKebangkrapan", vList);
        vList = model.searchMaklumatKebangkrapan(true, userName);
        context.put("ListMaklumatKebangkrapanSelesai", vList);
        context.put("isJIMUser", isJIMUser);
        
    	return vm;
	}
	
	private Boolean isJIMUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "jim".equalsIgnoreCase(userRole);
		return returnValue;
	}
}