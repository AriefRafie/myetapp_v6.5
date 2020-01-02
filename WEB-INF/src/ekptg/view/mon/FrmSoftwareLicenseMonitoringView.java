/**
 * 
 */
package ekptg.view.mon;

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

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.mon.FrmSoftwareLicenseMonitoringData;


public class FrmSoftwareLicenseMonitoringView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmSoftwareLicenseMonitoringData logic = new FrmSoftwareLicenseMonitoringData();
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionMonitoring = getParam("actionMonitoring");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTab = (String) getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		// GET ID PARAM
		String idPerisian = getParam("idPerisian");
		String idDokumen = getParam("idDokumen");
		
		// VECTOR
		Vector list = null;
		Vector beanMaklumatPerisian = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatImejan = null;
		
		// GET DROPDOWN PARAM
		String idKategori = getParam("socKategori");
		if (idKategori == null || idKategori.trim().length() == 0) {
			idKategori = "99999";
		}
		String idStatus = getParam("socStatus");
		if (idStatus == null || idStatus.trim().length() == 0) {
			idStatus = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		
		this.context.put("onload", "");
		this.context.put("completed", false);
						
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idPerisian = logic.daftarBaru(getParam("txtNamaPerisian"), getParam("txtNamaPengeluar"), getParam("txtKeterangan"), getParam("txtNoSiri"),  
						getParam("txtHarga"), getParam("txdTarikhBeli"), getParam("txdTarikhAktif"), getParam("txtTempoh"), getParam("txdTarikhLuput"),
						getParam("txtBilPengguna"),getParam("txtCdKey"), idKategori, session);
				
			}
			if ("simpanKemaskini".equals(hitButton)) {
				logic.simpanKemaskini(idPerisian, getParam("txtNamaPerisian"), getParam("txtNamaPengeluar"), getParam("txtKeterangan"), getParam("txtNoSiri"),  
						getParam("txtHarga"), getParam("txdTarikhBeli"), getParam("txdTarikhAktif"), getParam("txtTempoh"), getParam("txdTarikhLuput"),
						getParam("txtBilPengguna"), getParam("txtCdKey"), idKategori, session);
			}
			if ("hapus".equals(hitButton)){
				logic.hapus(idPerisian);				
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idPerisian, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen);
			}
		}
		
		// SET DEFAULT PARAM
		this.context.put("actionMonitoring", actionMonitoring);

		if ("daftarBaru".equals(actionMonitoring)) {
			
			// GO TO DAFTAR BARU PERISIAN
			vm = "app/mon/frmMONDaftarPerisian.jsp";

			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			// MAKLUMAT PERISIAN
			this.context.put("selectKategori",HTML.SelectKategoriMonitoring("socKategori", Long.parseLong(idKategori), "", ""));
			
			beanMaklumatPerisian = new Vector();
			Hashtable hashPerisian = new Hashtable();
			hashPerisian.put("txtNamaPerisian", getParam("txtNamaPerisian") == null ? "": getParam("txtNamaPerisian"));
			hashPerisian.put("txtNamaPengeluar", getParam("txtNamaPengeluar") == null ? "": getParam("txtNamaPengeluar"));
			hashPerisian.put("txtKeterangan", getParam("txtKeterangan") == null ? "": getParam("txtKeterangan"));
			hashPerisian.put("txtNoSiri", getParam("txtNoSiri") == null ? "": getParam("txtNoSiri"));
			hashPerisian.put("txtCdKey", getParam("txtCdKey") == null ? "": getParam("txtCdKey"));
			hashPerisian.put("txtHarga", getParam("txtHarga") == null ? "0.00": getParam("txtHarga"));			
			hashPerisian.put("txdTarikhBeli",getParam("txdTarikhBeli") == null ? "": getParam("txdTarikhBeli"));
			hashPerisian.put("txdTarikhAktif",getParam("txdTarikhAktif") == null ? "": getParam("txdTarikhAktif"));
			hashPerisian.put("txtTempoh", getParam("txtTempoh") == null ? "": getParam("txtTempoh"));
			hashPerisian.put("txdTarikhLuput",getParam("txdTarikhLuput") == null ? "": getParam("txdTarikhLuput"));
			hashPerisian.put("txtBilPengguna", getParam("txtBilPengguna") == null ? "": getParam("txtBilPengguna"));
			beanMaklumatPerisian.addElement(hashPerisian);
			this.context.put("BeanMaklumatPerisian", beanMaklumatPerisian);
			
		} else if ("papar".equals(actionMonitoring)) {
			
			// GO TO VIEW PELEPASAN
			vm = "app/mon/frmMONMaklumatPerisian.jsp";
			
			// MAKLUMAT PERISIAN
			beanMaklumatPerisian = new Vector();
			logic.setMaklumatPerisian(idPerisian);
			beanMaklumatPerisian = logic.getBeanMaklumatPerisian();
			this.context.put("BeanMaklumatPerisian", beanMaklumatPerisian);
			if (logic.getBeanMaklumatPerisian().size() != 0){
				Hashtable hashPerisian = (Hashtable) logic.getBeanMaklumatPerisian().get(0);
				idKategori = (String) hashPerisian.get("idKategori");
			}
			
			//VIEW MODE
	        if ("view".equals(mode)){
	        	
	        	this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");				
					
				this.context.put("selectKategori",HTML.SelectKategoriMonitoring("socKategori", Long.parseLong(idKategori), "disabled", " class=\"disabled\""));
				
	        } else if ("update".equals(mode)){
	        	
	        	this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
				this.context.put("selectKategori",HTML.SelectKategoriMonitoring("socKategori", Long.parseLong(idKategori), "", ""));
	        }
	        
	        if ("1".equals(selectedTab)){
				//OPEN POPUP DOKUMEN
		        if ("openPopupDokumen".equals(flagPopup)){
		        	
		        	if ("new".equals(modePopup)){
		        		
		        		this.context.put("readonlyPopup", "");
			    		this.context.put("inputTextClassPopup", "");
			    		
			    		beanMaklumatImejan = new Vector();    			
			    		Hashtable hashMaklumatImejan = new Hashtable();
			    		hashMaklumatImejan.put("namaImej", "");
			    		hashMaklumatImejan.put("catatanImej", "");
			    		beanMaklumatImejan.addElement(hashMaklumatImejan);
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
			    			
		        	} else if ("update".equals(modePopup)){
		        		
		        		this.context.put("readonlyPopup", "");
			    		this.context.put("inputTextClassPopup", "");
			    		
			    		//MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						beanMaklumatImejan = logic.getMaklumatImej(idDokumen);
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
			    		
		        	} 
		        } 
	        		
	        	//SENARAI IMEJAN
				senaraiImejan = new Vector();
				senaraiImejan = logic.getSenaraiImejan(idPerisian);
				this.context.put("SenaraiImejan", senaraiImejan);
			}
			
		} else {
			
			// GO TO LIST PERISIAN
			vm = "app/mon/frmMONSenaraiPerisian.jsp";
					
			if ("cari".equals(hitButton)){
				this.context.put("txtNamaPerisian", getParam("txtNamaPerisian"));
				this.context.put("txtNamaPengeluar", getParam("txtNamaPengeluar"));
				this.context.put("txtNoSiri", getParam("txtNoSiri"));			
				this.context.put("txdTarikhAktif", getParam("txdTarikhAktif"));
				this.context.put("txdTarikhLuput", getParam("txdTarikhLuput"));
				
				logic.carian(getParam("txtNamaPerisian"), getParam("txtNamaPengeluar"), getParam("txtNoSiri"),
						getParam("txdTarikhAktif"), getParam("txdTarikhLuput"), idKategori, idStatus, idNegeri, session);
			} else {
				this.context.put("txtNamaPerisian", "");
				this.context.put("txtNamaPengeluar", "");
				this.context.put("txtNoSiri", "");
				this.context.put("txdTarikhAktif", "");
				this.context.put("txdTarikhLuput", "");
				idKategori = "99999";
				idStatus = "99999";
				idNegeri = "99999";
				
				
				logic.carian(null, null, null, null, null, null, null, null, null);
			}	
			
			this.context.put("selectKategori",HTML.SelectKategoriMonitoring("socKategori", Long.parseLong(idKategori), "", ""));
			this.context.put("selectStatus",HTML.SelectStatusMonitoring("socStatus", idStatus, "", ""));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
			
			list = new Vector();
			list = logic.getSenaraiPerisian();
			this.context.put("SenaraiPerisian", list);
			
			setupPage(session, action, list);
		}
		
		// SET DEFAULT PARAM
		this.context.put("actionMonitoring", actionMonitoring);
		this.context.put("mode", mode);
		this.context.put("selectedTab", selectedTab);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
		// SET DEFAULT ID PARAM
		this.context.put("idPerisian", idPerisian);
		this.context.put("idDokumen", idDokumen);
				
		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiPerisian", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idLaporan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idLaporan, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idPerisian, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();

			// TBLMONDOKUMEN
			long idDokumen = DB.getNextID("TBLMONDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLMONDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_PERISIAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idPerisian);
			ps.executeUpdate();

			con.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}
