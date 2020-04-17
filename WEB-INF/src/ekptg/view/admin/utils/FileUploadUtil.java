package ekptg.view.admin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletRequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author Shamsul Bahrin Abd Mutalb
 * @version 1.0
 */
public class FileUploadUtil {
	
	public static List<String> uploadRequest(String uploadDir, ActionRequest request) throws Exception {
		List<String> uploadedFiles = new ArrayList<String>();
		try {
			java.io.File dir = new java.io.File(uploadDir);
			if ( !dir.exists() ) dir.mkdirs();
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			List items = upload.parseRequest(new PortletRequestContext(request));
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if(!item.isFormField()) {
					if ( item.getName() != null && !"".equals(item.getName())) {
		    			File fullFile  = new File(item.getName());
		    			String filename = (!uploadDir.endsWith("/") ? uploadDir += "/": uploadDir) + fullFile.getName();
		    			filename = filename.replaceAll(" ", "_");
		    			File savedFile = new File(filename);
		    			item.write(savedFile);
		    			uploadedFiles.add(filename);
		    			System.out.println("Finished uploading... " + item.getName());
					}
				}
			}
		} catch ( Exception e ) {
			System.out.println("Exception in FileUploadUtil...");
			e.printStackTrace();
		}
			
		return uploadedFiles;
	}	
	

}
