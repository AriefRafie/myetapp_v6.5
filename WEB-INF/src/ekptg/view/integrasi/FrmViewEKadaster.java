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

import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelEKadaster;

public class FrmViewEKadaster extends AjaxBasedModule {

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
        
        FrmModelEKadaster modelEKadaster = new FrmModelEKadaster();
        
        // action
        String action = getParam("action");
        String action2 = getParam("action2");
        
        Boolean doUpload = false;

        userID = (String) session.getAttribute("_ekptg_user_id");
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        
        String IDFail = getParam("IDFail");
        Boolean NoFileSpecified = false, FileProcessSuccess = false, FileProcessFail = false, FileDeleteSuccess = false, FileDeleteFail = false;

    	Vector vList = new Vector();
    	
        if ("uploadFile".equalsIgnoreCase(action2)) {
        	DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem objItem = (FileItem)itr.next();
		    	if ((!(objItem.isFormField())) && (objItem.getName() != null) && (!("".equals(objItem.getName())))) {
		    		doUpload = modelEKadaster.doUpload(objItem, userID);
		    	}
		    }
		} else if ("deleteFile".equalsIgnoreCase(action2)) {
			if ("".equalsIgnoreCase(IDFail)) {
				NoFileSpecified = true;
			} else {
				if (modelEKadaster.deleteFile(IDFail)) {
					FileDeleteSuccess = true;
				} else {
					FileDeleteFail = true;
				}
			}
		}
        
        // get list of files
        vList = modelEKadaster.getListUploadedFile();
        context.put("ListUploaded", vList);
        setupPage(session, action, vList);
        
        context.put("IDFail", IDFail);
        context.put("UploadStatus", doUpload);
        context.put("NoFileSpecified", NoFileSpecified);
        context.put("FileProcessSuccess", FileProcessSuccess);
        context.put("FileProcessFail", FileProcessFail);
        context.put("FileDeleteSuccess", FileDeleteSuccess);
        context.put("FileDeleteFail", FileDeleteFail);
        
    	vm = "app/integrasi/frmEKadaster.jsp";

        context.put("action2", action2);
        context.put("action", action);
		return vm;
	}
	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
			this.context.put("ListUploaded",paging.getPage(page));
		    this.context.put("page", new Integer(page));
		    this.context.put("itemsPerPage", new Integer(itemsPerPage));
		    this.context.put("totalPages", new Integer(paging.getTotalPages()));
		    this.context.put("startNumber", new Integer(paging.getTopNumber()));
		    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
		    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
}