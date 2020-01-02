package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.model.integrasi.FrmModelETerimaan;
//import lebah.portal.AjaxBasedModule;
//import lebah.portal.velocity.VTemplate;
//import ekptg.helpers.HTML;
//import ekptg.helpers.Paging;
//import ekptg.helpers.Utils;

public class FrmViewETerimaan extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	String NAMA_FAIL = "";
    
    String userID = "", userName = "", userRole = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelETerimaan modelETerimaan = new FrmModelETerimaan();
        
        // action
        String action = getParam("action2");
        Boolean doUpload = false, isMIMEOkay = false;

        userID = (String) session.getAttribute("_ekptg_user_id");
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        
        String IDFail = getParam("IDFail");
        Boolean NoFileSpecified = false, FileProcessSuccess = false, FileProcessFail = false, FileDeleteSuccess = false, FileDeleteFail = false;

//    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("uploadFile".equalsIgnoreCase(action)) {
        	DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    String objName = "", MIME_Type = "";

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem objItem = (FileItem)itr.next();
		    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
		    		doUpload = modelETerimaan.doUpload(objItem, userID);
		    		objName = objItem.getName();
		    		MIME_Type = modelETerimaan.getMIMEType(objName);
		    		if ("text/plain".equalsIgnoreCase(MIME_Type)) {
		    			isMIMEOkay = true;
		    		} else {	
		    			isMIMEOkay = false;
		    		}
		    		IDFail = modelETerimaan.getUploadID(objName);
		    	}
		    }
		} else if ("processFile".equalsIgnoreCase(action)) {
			if ("".equalsIgnoreCase(IDFail)) {
				NoFileSpecified = true;
			} else {
				// read file and process
				if (modelETerimaan.processFile(IDFail)) {
					FileProcessSuccess = true;
				} else {
					FileProcessFail = true;
				}
			}
		} else if ("deleteFile".equalsIgnoreCase(action)) {
			if ("".equalsIgnoreCase(IDFail)) {
				NoFileSpecified = true;
			} else {
				// read file and process
				if (modelETerimaan.deleteFile(IDFail)) {
					FileDeleteSuccess = true;
				} else {
					FileDeleteFail = true;
				}
			}
		}
        
        // get list of files
        vList = modelETerimaan.getListUploadedFile();
        context.put("ListUploaded", vList);
        
        context.put("IDFail", IDFail);
        context.put("UploadStatus", doUpload);
        context.put("isMIMEOkay", isMIMEOkay);
        context.put("NoFileSpecified", NoFileSpecified);
        context.put("FileProcessSuccess", FileProcessSuccess);
        context.put("FileProcessFail", FileProcessFail);
        context.put("FileDeleteSuccess", FileDeleteSuccess);
        context.put("FileDeleteFail", FileDeleteFail);
        
    	vm = "app/integrasi/frmETerimaan.jsp";

        context.put("action2", action);
		return vm;
	}
}