package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.DB;


public class FrmUploadFail extends VTemplate{
	
	static Logger myLogger = Logger.getLogger(FrmUploadFail.class);
	
	public Template doTemplate() throws Exception{
		
			HttpSession session = request.getSession();
			String submit = getParam("command2");  
			String vm = "";	
			vm = "app/ppk/frmUploadFail.jsp";			
			
			String id = getParam("idPermohonan");
    		//int id = Integer.parseInt(getParam("idPermohonan"));
    		context.put("idPermohonan", id);
    		
    		String idFail = getParam("idFail");
    		context.put("idFail", getParam("idFail"));
    		
    		String ekptg_user_id = getParam("ekptg_user_id");
    		context.put("ekptg_user_id", getParam("ekptg_user_id"));
    		
    		myLogger.info("idPermohonan(TAMBAHDOKUMEN) = " +getParam("idPermohonan"));
    		myLogger.info("idFail(TAMBAHDOKUMEN) = " +getParam("idFail"));
    		myLogger.info("SUBMIT = "+submit);
    		
    		context.put("completed",false);     
            int files = 1;
            try {
              files = Integer.parseInt(getParam("files")); } 
            catch (Exception localException) {
            }
            context.put("num_files", Integer.valueOf(files));      
			
            myLogger.info("COMMAND2 :: "+submit);
            if("uploadFile".equals(submit)){   
            	
        			uploadFiles();
        			
            		context.put("completed",true);
            		//vm = "app/test/upload.jsp";
             		vm = "app/ppk/frmUploadFail.jsp";
        	}
            this.context.put("SenaraiFail", getList());
            Template template = engine.getTemplate(vm);
            return template;
    }

	private void uploadFiles() throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(this.request);
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
        try {
        	db = new Db();
        	long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRAN_SEQ");
        	long id_Dokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);

        	PreparedStatement pd = con.prepareStatement("insert into TBLPFDDOKUMEN " +
        			"(id_Dokumen,id_fail,catatan,tajuk_dokumen) " +
        			"values(?,?,?,?)");
        	
        	pd.setLong(1, id_Dokumen);
        	pd.setString(2, getParam("idFail"));
        	pd.setString(3, getParam("keterangan"));
        	pd.setString(4, getParam("nama_dokumen"));
        	pd.executeUpdate();
        	
        	PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRAN " +
        			"(id_Lampiran,id_Dokumen,content,nama_Fail,jenis_Mime) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1, id_Lampiran);
        	ps.setLong(2, id_Dokumen);
        	ps.setBinaryStream(3,item.getInputStream(),(int)item.getSize());
        	ps.setString(4,item.getName());
        	ps.setString(5,item.getContentType());
        	
        	ps.executeUpdate();	
        	
            con.commit();
	    } finally {
		      if (db != null) db.close();
	    }
	}
	
	 public Vector getList() throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT id_lampiran,nama_fail from tblpfdrujlampiran";
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id", rs.getInt("id_lampiran"));
		        h.put("nama_fail", rs.getString("nama_fail"));
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
}
