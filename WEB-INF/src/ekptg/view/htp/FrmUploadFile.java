package ekptg.view.htp;

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
import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmUploadFile extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmUploadFile.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		this.setUploadFile(true);
		String vm = "";
		Vector listImej	=null;
		
		HttpSession session = this.request.getSession();
		String nextAction = getParam("nextAction");	
		this.context.put("nextAction",nextAction);
		log.info("nextAction :" +nextAction);
		
		vm = "app/htp/testUpload.jsp";
		  this.context.put("txtPerihalImej","");
		if("tambahDetailImej".equals(nextAction)){
			  vm = "app/htp/testUpload.jsp";
			  uploadFiles(); 
			  this.context.put("SenaraiImej", listImej);	
			  log.info("masuk cni");

	        System.out.println("pass : "+getParam("txtPerihalImej"));
	   }
		return vm;
	}
	// UPLOAD FILE
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
	        try {
	        	db = new Db();
	        	long idGambar = DB.getNextID("TBLHTPGAMBAR_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLHTPGAMBAR " +
	        			"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail) " +
	        			"values(?,?,?,?,?,?,?)");
	        	ps.setLong(1, idGambar);
	        	ps.setString(2, getParam("idHakmilik"));
	        	ps.setString(3, getParam("txtPerihalImej"));
	        	ps.setString(4, getParam("txtRingkas"));
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.setString(6,item.getName());
	        	ps.setString(7,item.getContentType());
	        	ps.executeUpdate();
	            con.commit();
		    } finally {
			      if (db != null) db.close();
		    }
	  }
}
