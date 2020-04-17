package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
import ekptg.model.pdt.FrmDaftarEnakmenData;
import ekptg.model.pdt.FrmKemaskiniEnakmenData;

public class PendaftaranEnakmen extends AjaxBasedModule {
	private static Logger myLogger = Logger.getLogger(PendaftaranEnakmen.class);
	
	FrmDaftarEnakmenData enakmen = new FrmDaftarEnakmenData();
	FrmKemaskiniEnakmenData kemaskiniEnakmen = new FrmKemaskiniEnakmenData();
	
	public String doTemplate2() throws Exception {
		//debugParams();
		String vm = "";
		String action = getParam("action");
		String idSeksyen = getParam("socSeksyen");
		String idFail = getParam("socNoFail");

		String noEnakmen = "";
		String namaEnakmen = "";
		String tarikhKuatkuasa = "";
		String noWarta = "";
		String tarikhWarta = "";
		String catatan = "";
		String tarikhDaftar = "";
		String idKeselamatan = "";
		String noPenggal = "";
		String tajukPenggal = "";
		String tarikhMansuh = "";
		
		Vector list = new Vector();
		Vector listSenaraiEnakmen = new Vector();
		list.clear();

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		
		HttpSession session = this.request.getSession();
		
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String idEnakmen = getParam("idEnakmen");
		if ("kembali".equals(action) || "batal".equals(action)) {

			vm = "app/pdt/frmDaftarEnakmen.jsp";
			if (!"".equals(getParam("txtNoEnakmenC")))
				noEnakmen = getParam("txtNoEnakmenC");
			if (!"".equals(getParam("txtNamaEnakmenC")))
				namaEnakmen = getParam("txtNamaEnakmenC");
			if (!"".equals(getParam("txtTarikhKuatkuasaC")))
				tarikhKuatkuasa = getParam("txtTarikhKuatkuasaC");
			
			list = enakmen.getCarianPaparEnakmen(noEnakmen, namaEnakmen,
					tarikhKuatkuasa);
			//prepareItemForDisplay(session, list, 10, "first");
			setupPage(session,action,list);
			this.context.put("txtNoEnakmenC", noEnakmen);
			this.context.put("txtNamaEnakmenC", namaEnakmen);
			this.context.put("txtTarikhKuatkuasaC", tarikhKuatkuasa);

		} else
		// tambah enakmen baru
		if ("tambah".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmen.jsp";	
			this.context.put("mode", "new");
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("idEnakmen","");
			this.context.put("txtNoEnakmen", "");
			this.context.put("txtNoFail", "");
			this.context.put("txtNamaEnakmen", "");
			this.context.put("txtTarikhKuatkuasa", "");
			this.context.put("txtTarikhMansuh","");
			this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen",Utils.parseLong(idSeksyen), ""));
			this.context.put("txtCatatan", "");
			this.context.put("txtNoWarta", "");
			this.context.put("txtTarikhWarta", tarikhWarta);
			this.context.put("txtTarikhDaftar", sdf.format(now));

		} // papar enakmen
		else if ("papar".equals(action)) {
			myLogger.debug("** action ***"+action);
			vm = "app/pdt/frmKemaskiniEnakmen.jsp";
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			Hashtable hPapar = enakmen.getPaparEnakmenById(idEnakmen);
			this.context.put("idEnakmen", hPapar.get("idEnakmen"));
			this.context.put("txtNoEnakmen", hPapar.get("noEnakmen"));
			this.context.put("txtNamaEnakmen", hPapar.get("namaEnakmen"));
			this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa"));
			this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh"));
			this.context.put("txtNoWarta", hPapar.get("noWarta"));
			this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta"));

			this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", 
					Utils.parseLong(hPapar.get("idseksyen").toString()),"disabled"));
			this.context.put("txtNoFail",hPapar.get("noFail"));
			this.context.put("txtCatatan", hPapar.get("catatan"));
			this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar"));	
			
			
			// kemaskini data
		} else if ("kemaskini".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmen.jsp";
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");
			// kemaskini enakmen
			paparEnakmenDetail(idEnakmen,"");
		} else if ("simpan".equals(action)) { 
			vm = "app/pdt/frmKemaskiniEnakmen.jsp";
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			Hashtable hAddEnakmen = new Hashtable();
			hAddEnakmen.put("noEnakmen", getParam("txtNoEnakmen1"));
			hAddEnakmen.put("namaEnakmen", getParam("txtNamaEnakmen1"));
			hAddEnakmen.put("tarikhKuatkuasa",getParam("txtTarikhKuatkuasa1"));
			hAddEnakmen.put("tarikhMansuh", getParam("txtTarikhMansuh"));
			hAddEnakmen.put("noWarta", Utils.isNull(getParam("txtNoWarta")));
			hAddEnakmen.put("tarikhWarta", getParam("txtTarikhWarta"));
			hAddEnakmen.put("catatan", getParam("txtCatatan"));
			hAddEnakmen.put("noFail", getParam("txtNoFail"));
			hAddEnakmen.put("idSeksyen", getParam("socSeksyen") == null
					|| getParam("socSeksyen") == "" ? "" : Integer
					.parseInt(getParam("socSeksyen")));
			hAddEnakmen.put("tarikhDaftar", getParam("txtTarikhDaftar"));
			hAddEnakmen.put("idMasuk",user_id);
			idEnakmen = enakmen.addEnakmen(hAddEnakmen);
			UploadFile(idEnakmen);
			paparEnakmenDetail(idEnakmen,"disabled");
		}  else if ("doKemaskini".equals(action)) {
			vm = "app/pdt/frmKemaskiniEnakmen.jsp";
			this.context.put("mode", "view");
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", "disabled");
			Hashtable hEnakmenUpdate = new Hashtable();
			hEnakmenUpdate.put("idEnakmen", idEnakmen);
			hEnakmenUpdate.put("idKemaskini",user_id);
			hEnakmenUpdate.put("tarikhKemaskini",getParam("txtTarikhDaftar"));
			hEnakmenUpdate.put("noEnakmen", getParam("txtNoEnakmen1"));
			hEnakmenUpdate.put("namaEnakmen",getParam("txtNamaEnakmen1"));
			hEnakmenUpdate.put("tarikhKuatkusa",getParam("txtTarikhKuatkuasa1"));
			hEnakmenUpdate.put("noWarta",getParam("txtNoWarta"));
			hEnakmenUpdate.put("tarikhMansuh",getParam("txtTarikhMansuh"));
			hEnakmenUpdate.put("tarikhWarta",getParam("txtTarikhWarta"));
			hEnakmenUpdate.put("catatan", getParam("txtCatatan"));
			hEnakmenUpdate.put("tarikhDaftar",getParam("txtTarikhDaftar"));
			hEnakmenUpdate.put("noFail", getParam("txtNoFail"));
			hEnakmenUpdate.put("idSeksyen",getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "": Integer.parseInt(getParam("socSeksyen")));
			kemaskiniEnakmen.updateEnakmen(hEnakmenUpdate);
			UploadFile(idEnakmen);
			paparEnakmenDetail(idEnakmen,"disabled");
		}
		else {
				myLogger.debug("else:"+action);
				vm = "app/pdt/frmDaftarEnakmen.jsp";
				if (!"".equals(getParam("txtNoEnakmenC")))
					noEnakmen = getParam("txtNoEnakmenC");
				if (!"".equals(getParam("txtNamaEnakmenC")))
					namaEnakmen = getParam("txtNamaEnakmenC");
				if (!"".equals(getParam("txtTarikhKuatkuasaC")))
					tarikhKuatkuasa = getParam("txtTarikhKuatkuasaC");

				listSenaraiEnakmen = enakmen.getCarianPaparEnakmen(noEnakmen,namaEnakmen, tarikhKuatkuasa);
				//prepareItemForDisplay(session, listSenaraiEnakmen, 10, "first");
				setupPage(session,action,listSenaraiEnakmen);
				this.context.put("txtNoEnakmenC", noEnakmen);
				this.context.put("txtNamaEnakmenC", namaEnakmen);
				this.context.put("txtTarikhKuatkuasaC", tarikhKuatkuasa);

		}
		this.context.put("action", action);
		myLogger.debug(vm);
		myLogger.debug("action:"+action);

		return vm;
	}



		////////
		public void UploadFile(String idEnakmen) throws Exception {
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
		
		public void saveBlob(String idEnakmen,FileItem item) throws Exception {
			 Db db = null;
			 try {
				 db = new Db();
				 Connection con = db.getConnection();
				 con.setAutoCommit(false);
				 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtenakmen " +
				 		"SET Content=?,jenis_mime=? WHERE id_enakmen=?");
				 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
				 ps.setString(2,item.getName());
				 ps.setString(3,idEnakmen);
				 ps.executeUpdate();
				 con.commit();
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 finally {
				      if (db != null) db.close();
			 }
		}
		
		public void paparEnakmenDetail(String idEnakmen,String mode) throws Exception {
			//String idEnakmen = getParam("idEnakmen");
			Hashtable hPapar = enakmen.getPaparEnakmenById(idEnakmen);
			this.context.put("idEnakmen", hPapar.get("idEnakmen"));
			this.context.put("txtNoEnakmen", hPapar.get("noEnakmen"));
			this.context.put("txtNamaEnakmen", hPapar.get("namaEnakmen"));
			this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa"));
			this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh"));
			this.context.put("txtNoWarta", hPapar.get("noWarta"));
			this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta"));

			this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", 
					Utils.parseLong(hPapar.get("idseksyen").toString()),mode));
			this.context.put("txtNoFail",hPapar.get("noFail"));
			this.context.put("txtCatatan", hPapar.get("catatan"));
			this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar"));
		}
		
		
		public void debugParams() {
			Enumeration allparam = request.getParameterNames();
			String name="";String value="";
			for (; allparam.hasMoreElements(); ) {
		        // Get the name of the request parameter
		        name = (String)allparam.nextElement();
		        // Get the value of the request parameter
		        value = request.getParameter(name);
		        //System.out.println(name +"="+value);
			    myLogger.debug(name +"="+value);
			}
		}
		
	 
}
