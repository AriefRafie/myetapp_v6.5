package ekptg.view.pdt;

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
import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmEnakmenPindaanData;


public class PendaftaranEnakmenPindaan extends AjaxBasedModule {
	private static Logger myLogger = Logger.getLogger(ekptg.view.pdt.PendaftaranEnakmenPindaan.class);
	
	FrmEnakmenPindaanData enakmenpinda = new FrmEnakmenPindaanData();
	
	private Vector listSenarai = null;
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		
		String vm = "";
		String action = getParam("action");
		String idEnakmen = getParam("idEnakmen");
		String txtNoEnakmenAsal = getParam("txtNoEnakmenAsal");
		String idEnakmenPinda = getParam("idEnakmenPinda");
		String noEnakmen = getParam("txtNoEnakmenP");
		String namaEnakmen = getParam("txtNamaEnakmenP");
		String tarikhKuatkuasa = "";
		String current_role = (String) session.getAttribute("myrole");
		this.context.put("current_role", current_role);
		//String MAIN_LEGEND = "MAKLUMAT ENAKMEN";
		initForm();
		myLogger.debug("action:"+action);
		if ("tambah".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmenPindaan.jsp";
			this.context.put("mode", "new");
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			
			
		} else if (action.equals("simpan")) { 
			vm = "app/pdt/frmKemaskiniEnakmenPindaan.jsp";
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			Hashtable hAddEnakmen = new Hashtable();
			hAddEnakmen.put("noEnakmenAsal", getParam("txtNoEnakmenAsal"));
			hAddEnakmen.put("noEnakmen", getParam("txtNoEnakmen1"));
			hAddEnakmen.put("namaEnakmen", getParam("txtNamaEnakmen1"));
			hAddEnakmen.put("tarikhKuatkuasa",getParam("txtTarikhKuatkuasa1"));
			hAddEnakmen.put("tarikhMansuh", getParam("txtTarikhMansuh"));
			hAddEnakmen.put("noWarta", Utils.isNull(getParam("txtNoWarta")));
			hAddEnakmen.put("tarikhWarta", getParam("txtTarikhWarta"));
			hAddEnakmen.put("catatan", getParam("txtCatatan"));
			hAddEnakmen.put("noFail", getParam("txtNoFail"));
			hAddEnakmen.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));
			hAddEnakmen.put("tarikhDaftar", getParam("txtTarikhDaftar"));
			hAddEnakmen.put("idMasuk",user_id);
			hAddEnakmen.put("tagDokumen", getParam("tag_dokumen"));
			hAddEnakmen.put("idTagDokumen", getParam("id_tagdokumen"));
			//System.out.println("id tag dokumen" +getParam("id_tagdokumen"));
			idEnakmenPinda = enakmenpinda.addEnakmen(hAddEnakmen);
			UploadFile(idEnakmenPinda);
			paparDetail(idEnakmenPinda,"disabled");
			
		} else if ("kemaskini".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmenPindaan.jsp";
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");
			// kemaskini enakmen
			paparDetail(idEnakmenPinda,"");
			
		}   else if ("doKemaskini".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmenPindaan.jsp";
			myLogger.info("92:"+getParam("txtCatatan"));
			myLogger.info("93:"+getParam("txtCatatan_"));
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			Hashtable hEnakmenUpdate = new Hashtable();
			hEnakmenUpdate.put("idEnakmenPinda", idEnakmenPinda);
			hEnakmenUpdate.put("idKemaskini",user_id);
			hEnakmenUpdate.put("tarikhKemaskini",getParam("txtTarikhDaftar"));
			hEnakmenUpdate.put("noEnakmenAsal", getParam("txtNoEnakmenAsal"));
			hEnakmenUpdate.put("noEnakmenPinda", getParam("txtNoEnakmen1"));
			hEnakmenUpdate.put("namaEnakmenPinda",getParam("txtNamaEnakmen1"));
			hEnakmenUpdate.put("tarikhKuatkusa",getParam("txtTarikhKuatkuasa1"));
			hEnakmenUpdate.put("noWarta",getParam("txtNoWarta"));
			hEnakmenUpdate.put("tarikhMansuh",getParam("txtTarikhMansuh"));
			hEnakmenUpdate.put("tarikhWarta",getParam("txtTarikhWarta"));
			hEnakmenUpdate.put("catatan", getParam("txtCatatan"));
			hEnakmenUpdate.put("tarikhDaftar",getParam("txtTarikhDaftar"));
			hEnakmenUpdate.put("noFail", getParam("txtNoFail"));
			hEnakmenUpdate.put("tagDokumen", getParam("tag_dokumen"));
			hEnakmenUpdate.put("idTagDokumen", getParam("id_tagdokumen"));
			enakmenpinda.updateEnakmenPinda(hEnakmenUpdate);
			UploadFile(idEnakmenPinda);
			paparDetail(idEnakmenPinda,"disabled");
			
			
		} else if ("papar".equals(action)) {
			myLogger.debug("** action ***"+action);
			vm = "app/pdt/frmKemaskiniEnakmenPindaan.jsp";
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			paparDetail(idEnakmenPinda,"disabled");
			// kemaskini data
		} else if ("delete".equals(action)) {
			
			enakmenpinda.Delete(idEnakmenPinda);
			vm = "app/pdt/frmDaftarEnakmenPindaan.jsp";
			
			listSenarai = enakmenpinda.getListing(noEnakmen,namaEnakmen, tarikhKuatkuasa);
			setupPage(session,action,listSenarai);
		
		}else {
			vm = "app/pdt/frmDaftarEnakmenPindaan.jsp";
			String tag = getParam("tag_dokumen");
			//listSenarai = FrmEnakmenPindaanData.getListing(noEnakmen,namaEnakmen, tarikhKuatkuasa);
			listSenarai = enakmenpinda.getListing(txtNoEnakmenAsal,noEnakmen,namaEnakmen,tarikhKuatkuasa,tag);
			context.put("tag_Dokumen",tag);

			setupPage(session,action,listSenarai);
			
		}
		this.context.put("idEnakmenPinda", idEnakmenPinda);
		myLogger.debug("vm:"+vm);
		return vm;
	}	 
	
	public void initForm() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.context.put("txtNoEnakmenAsal", "");
		this.context.put("txtNoEnakmen", "");
		this.context.put("txtNoFail", "");
		this.context.put("txtNamaEnakmen", "");
		this.context.put("txtTarikhKuatkuasa", "");
		this.context.put("txtTarikhMansuh","");
		this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen",null, ""));
		this.context.put("txtCatatan", "");
		this.context.put("txtNoWarta", "");
		this.context.put("txtTarikhWarta","");
		this.context.put("txtTarikhDaftar", sdf.format(new Date()));
		this.context.put("tag_Dokumen", "");
		this.context.put("id_tagdokumen", "0");
			
	}
	
	
	public void UploadFile(String idEnakmen) throws Exception {
		//txfMuatNaikDokumen
		myLogger.info("MASUK UPLOAD");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveBlob(idEnakmen,item);
			    }
		    }
		}
	}
	
	public void saveBlob(String idEnakmenPinda,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtenakmenpinda " +
			 		"SET Content=?,jenis_mime=? WHERE id_enakmenpinda=?");
			 myLogger.info(" ps :"+ps);
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idEnakmenPinda);
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}
	
	public void paparDetail(String idEnakmenPinda,String mode) throws Exception {
		//String idEnakmen = getParam("idEnakmen");
		Hashtable hPapar = enakmenpinda.getPaparEnakmenById(idEnakmenPinda);
		this.context.put("idEnakmenPinda", hPapar.get("idEnakmenPinda"));
		this.context.put("txtNoEnakmenAsal", hPapar.get("noEnakmenAsal"));
		this.context.put("txtNoEnakmen", hPapar.get("noEnakmenPinda"));
		this.context.put("txtNamaEnakmen", hPapar.get("namaEnakmenPinda"));
		this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa"));
		this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh"));
		this.context.put("txtNoWarta", hPapar.get("noWarta"));
		this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta"));

//		this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", 
//				Utils.parseLong(hPapar.get("idseksyen").toString()),mode));
		this.context.put("txtNoFail",hPapar.get("noFail"));
		this.context.put("txtCatatan", hPapar.get("catatan"));
		this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar"));
		this.context.put("tag_Dokumen", hPapar.get("tagging"));
		this.context.put("id_tagdokumen", hPapar.get("idTagging"));
		this.context.put("DOC", hPapar.get("DOC"));
	}
	
}
