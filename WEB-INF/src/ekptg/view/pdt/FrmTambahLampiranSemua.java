package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.pdt.FrmTambahLampiranData;
import ekptg.model.pdt.IPDT;
import ekptg.model.pdt.PDTPerundanganBean;

public class FrmTambahLampiranSemua extends AjaxBasedModule{
	
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.FrmTambahLampiranSemua.class);
 	private IPDT iPDT = null;  
	private final String PATH="app/pdt/utiliti/";
	public String doTemplate2() throws Exception{
		
		String vm = PATH+"frmTambahLampiran.jsp";
    	String sendCommand = getParam("sendCommand");
    	String popupCommand = getParam("popupCommand");
    	String mode = getParam("mode");
		HttpSession session = this.request.getSession();
		Vector list = new Vector();
		this.context.put("sendCommand",sendCommand);
		this.context.put("isComplete", false);
		this.context.put("id_Dokumen",getParam("idDokumen"));
		this.context.put("idDokumen",getParam("idDokumen"));
		if (sendCommand.equals("tambah")){			
	       	 //vm = "app/pdt/frmTambahLampiran.jsp";
			int files = 5;
		    this.context.put("num_files", Integer.valueOf(files));
	       	String idPerundangan = getParam("idDokumen");
	       	Hashtable h = view(idPerundangan);
			this.context.put("idDokumen",h.get("idPerundangan"));
			this.context.put("id_Dokumen",h.get("idPerundangan"));
			this.context.put("tjkDok",h.get("maklumat"));
	       
		//}else if ("simpan".equals(popupCommand)){
		}else if (sendCommand.equals("simpan")){
			myLog.info(getParam("idDokumen"));
			uploadFiles();
	       	this.context.put("isComplete", true);
		
		}
		return vm;
		
	}
	
	private void view_Dokumen(HttpSession session) throws Exception {
    	//int id = Integer.parseInt(getParam("idDokumen"));
    	String id = getParam("idDokumen");
    	FrmTambahLampiranData tambahLampiran = new FrmTambahLampiranData();
    	tambahLampiran.setDataDokumen(id);
	   
	}

	private Hashtable view(String id_) throws Exception {
		return getPDTPerundangan().getMaklumat(id_);
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
        	long idLampiran = DB.getNextID("TBLPDTRUJLAMPIRANSEMUA_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPDTRUJLAMPIRANSEMUA " +
        			"(id_Lampiran,id_Dokumen,nama_Fail,jenis_Mime,content,sumber) " +
        			"values(?,?,?,?,?,'PERUNDANGAN')");
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
	
	private IPDT getPDTPerundangan(){
		if(iPDT== null)
			iPDT = new PDTPerundanganBean();
		return iPDT;
	}
	

}
