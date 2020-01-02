package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.DB;
import ekptg.model.pdt.FrmTambahLampiranData;

public class FrmTambahLampiran extends AjaxBasedModule{
	
	FrmTambahLampiranData tambahLampiran = new FrmTambahLampiranData();
	
	public String doTemplate2() throws Exception{

    	String vm = "app/pdt/frmTambahLampiran.jsp";
    	String sendCommand = getParam("sendCommand");
    	String popupCommand = getParam("popupCommand");
    	String mode = getParam("mode");
		HttpSession session = this.request.getSession();
		Vector list = new Vector();
		this.context.put("sendCommand",sendCommand);
		this.context.put("isComplete", false);
		if (sendCommand.equals("tambah")){			
			 int files = 5;
//			 try {
//				  files = Integer.parseInt(getParam("files")); } 
//			 catch (Exception e) {
//					  e.printStackTrace();
//			 }
		     this.context.put("num_files", Integer.valueOf(files));
		     this.context.put("idDokumen", getParam("idDokumen"));
	       	 vm = "app/pdt/frmTambahLampiran.jsp";
	       	
	       	 view_Dokumen(session);
	       	 list = tambahLampiran.getDataDokumen();
	       	 this.context.put("Dokumen",list);     	 
	       
		}else if ("simpan".equals(popupCommand)){
	       	 uploadFiles();
	       	 this.context.put("isComplete", true);
		
		}
		return vm;
		
	}
	
	private void view_Dokumen(HttpSession session) throws Exception {
    	String id = getParam("idDokumen");
    	
    	tambahLampiran.setDataDokumen(id);
	   
	}
	
	private void uploadFiles() throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		    if (isMultipart) {
			    List items = upload.parseRequest(this.request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			      FileItem item = (FileItem)itr.next();
			      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	 saveData(item);
			      }
			    }
		    }
		  }
	
	private void saveData(FileItem item) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long idLampiran = DB.getNextID("TBLPDTRUJLAMPIRAN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into tblpdtrujlampiran " +
        			"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1,idLampiran);
        	ps.setString(2, getParam("idDokumen"));
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.executeUpdate();
        	
        	
            con.commit();
	    } finally {
		      if (db != null) db.close();
	    }
  }
	
	

}
