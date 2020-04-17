package ekptg.view.pfd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
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
import ekptg.model.pfd.FrmPaparLampiranData;


public class FrmPaparLampiran extends AjaxBasedModule{
	
	 protected String boundary = null;

	
	public String doTemplate2() throws Exception

    {	
		
		 String submit = getParam("command");
		 String action = getParam("action");
		 String mode = getParam("mode");
		
		 Vector paparDokumen = null;
		 Vector listLampiran = null;
         String vm = "";
    	 HttpSession session = this.request.getSession();
		 

         if ("tambah".equals(action)){
        	 vm = "app/pfd/frmPaparLampiran.jsp";
        	 
        	 view_Dokumen(session);
        	 paparDokumen = FrmPaparLampiranData.getDataDokumen();
        	 Hashtable h = (Hashtable)paparDokumen.get(0);
        	 context.put("idDokumen",h.get("id_Dokumen"));
        	 context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
        	 context.put("no_Rujukan_Dokumen",h.get("no_Rujukan_Dokumen"));
        	 
        	 
        	 list_Lampiran(session);
        	 listLampiran = FrmPaparLampiranData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",false);
             

         }      	 
         else if ("uploadFile".equals(action)){
        	 uploadFiles();
			 vm = "app/pfd/frmPaparLampiran.jsp";
			 
			 context.put("completed",true);
				 
        		 	 
        		
        	 } 
         
         
         return vm;

    }
	private void view_Dokumen(HttpSession session) throws Exception {
    	String id = getParam("idDokumen");
    	FrmPaparLampiranData.setDataDokumen(id);
	   
	  }
	private void list_Lampiran(HttpSession session) throws Exception {
    	String id = getParam("idDokumen");
    	FrmPaparLampiranData.setListLampiran(id);
	   
	  }
	 private void uploadFiles() throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item);
		      }
		    }
		  }
	 
	 private void saveData(FileItem item) throws Exception {
	  		Db db = null;
      	    HttpSession session = this.request.getSession();
      	    Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = "to_date('" +  sdf.format(now) + "','dd/MM/yyyy')";
	  		String id = getParam("idDokumen");
			String user_id = (String)session.getAttribute("_ekptg_user_id");
	        try {
	        	db = new Db();
	        	long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
	        			"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content) " +
	        			"values(?,?,?,?,?)");
	        	ps.setLong(1, id_Lampiran);
	        	ps.setString(2, id);
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
