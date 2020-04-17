package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPLPHeaderData;
import ekptg.model.php2.FrmPLPMesyuaratData;

public class FrmPLPMesyuaratView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPLPHeaderData logicHeader = new FrmPLPHeaderData();
	FrmPLPMesyuaratData logic = new FrmPLPMesyuaratData();
		
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String submit = getParam("command");  
        String hitButton = getParam("hitButton");

        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}

		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idMesyuarat = getParam("idMesyuarat");
        String checkPampasan = getParam("checkPampasan");
        if (checkPampasan == null || "".equals(checkPampasan) ) {
        	checkPampasan = "0";
		}
        String checkSyor = getParam("checkSyor");
        if (checkSyor == null || "".equals(checkSyor) ) {
        	checkSyor = "0";
		}
        
        //VECTOR
		Vector beanHeader = null;
        Vector senaraiMesyuarat = null;
        Vector beanMaklumatMesyuarat = null;
        Vector beanMaklumatPampasan = null;
        Vector beanMinitMesyuarat = null; //12092017

        //GET DROPDOWN PARAM
        String idLokasi = getParam("socLokasi");
		if (idLokasi == null || idLokasi.trim().length() == 0){
			idLokasi = "99999";
		}
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0){
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0){
			idMinitDari = "99999";
		}
		String idJamHingga = getParam("socJamHingga");
		if (idJamHingga == null || idJamHingga.trim().length() == 0){
			idJamHingga = "99999";
		}
		String idMinitHingga = getParam("socMinitHingga");
		if (idMinitHingga == null || idMinitHingga.trim().length() == 0){
			idMinitHingga = "99999";
		}
		
		String step = getParam("step");
		
        vm = "app/php2/frmPLPMesyuarat.jsp";
        
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanMesyuarat".equals(hitButton)){
    			idMesyuarat = logic.simpanMesyuarat(idPermohonan, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatan"), session);
    		}
        	if ("simpanKemaskiniMesyuarat".equals(hitButton)){
    			logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatan"), 
    					getParam("socKeputusanPemohon"), getParam("txtUlasanPemohon"), session);
    		}
        	if ("hapusMesyuarat".equals(hitButton)){
    			logic.hapusMesyuarat(idMesyuarat, session);
    		}        		
        	if ("doSeterusnya".equals(hitButton)){
    			logic.updateStatus(idFail, idPermohonan, session);          		
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        	if ("doSimpan".equals(hitButton)){
			    logic.simpanMaklumatPampasan(idPermohonan, getParam("socGanti"), getParam("txtCatatan"), getParam("txtPampasan"), session);
			}
        	if ("simpanDokumenMM".equals(hitButton)) {
        		logic.hapusDokumen(idMesyuarat, session);
				uploadFiles(idMesyuarat, idPermohonan, session);
			}
        }

        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
		}
		
		if(mode.equals("view")){
			
			this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");
        	
        	//MAKLUMAT PAMPASAN
        	beanMaklumatPampasan = new Vector();
			logic.setMaklumatPampasan(idPermohonan);
			beanMaklumatPampasan = logic.getBeanMaklumatPampasan();
	    	this.context.put("BeanMaklumatPampasan", beanMaklumatPampasan);
	    	
		//OPEN POPUP DAFTAR BARU MESYUARAT
		if ("openMesyuarat".equals(flagPopup)){
			
			//MODE NEW
        	if ("new".equals(modePopup)){
    				
    			this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
        		
        		beanMaklumatMesyuarat = new Vector();    		   	
        		Hashtable hashMesyuarat = new Hashtable();
        		hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
        		hashMesyuarat.put("catatan", getParam("txtCatatan"));
        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
        		hashMesyuarat.put("ulasanPemohon", getParam("txtUlasanPemohon"));
        		hashMesyuarat.put("flagKeputusanPemohon", getParam("socKeputusanPemohon"));
        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
    			
    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), ""));
    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
    				
        	} else if ("view".equals(modePopup)){ //MODE VIEW
    				
    			this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
    			
    			beanMaklumatMesyuarat = new Vector();
	   			logic.setMaklumatMesyuarat(idMesyuarat);
	   			beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
	   			this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
	   			
	   			if (beanMaklumatMesyuarat.size() != 0){
					Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
					idLokasi = (String)(hashMesyuarat.get("idLokasi"));
					idJamDari = (String)(hashMesyuarat.get("idJamDari"));
					idMinitDari = (String)(hashMesyuarat.get("idMinitDari"));
					idJamHingga = (String)(hashMesyuarat.get("idJamHingga"));
					idMinitHingga = (String)(hashMesyuarat.get("idMinitHingga"));
	   			}
    			
    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "class=\"disabled\""));
    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));
    			
    			// MAKLUMAT MINIT MESYUARAT
    			beanMinitMesyuarat = new Vector();
    			logic.setMaklumatDokumen(idMesyuarat);
    			beanMinitMesyuarat = logic.getBeanMaklumatDokumen();
    			this.context.put("BeanMinitMesyuarat",beanMinitMesyuarat);
    			
        	} else if ("update".equals(modePopup)){
    				
    			this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
        		
        		beanMaklumatMesyuarat = new Vector();    		   	
        		Hashtable hashMesyuarat = new Hashtable();
        		hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
        		hashMesyuarat.put("catatan", getParam("txtCatatan"));
        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
        		hashMesyuarat.put("ulasanPemohon", getParam("txtUlasanPemohon"));
        		hashMesyuarat.put("flagKeputusanPemohon", getParam("socKeputusanPemohon"));
        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
    			
    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), ""));
    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
    				
        		}
			}
		}
		else if(mode.equals("update")){
		
		this.context.put("readonly", "");
		this.context.put("inputTextClass", "");
		this.context.put("disabled", "");

    	beanMaklumatPampasan = new Vector();
    	logic.setMaklumatPampasan(idPermohonan);
	    Hashtable hashMaklumatPampasanDB = (Hashtable) logic.getBeanMaklumatPampasan().get(0);
		Hashtable hashMaklumatPampasan = new Hashtable();
		hashMaklumatPampasan.put("jenis", getParam("socGanti"));	
		hashMaklumatPampasan.put("catatan", getParam("txtCatatan"));
		hashMaklumatPampasan.put("pampasan", getParam("txtPampasan"));
		beanMaklumatPampasan.addElement(hashMaklumatPampasan);
		this.context.put("BeanMaklumatPampasan", beanMaklumatPampasan);
		}
					
		//SENARAI MESYUARAT
        logic.setSenaraiMesyuarat(idPermohonan);
		senaraiMesyuarat = logic.getListMesyuarat();
		this.context.put("SenaraiMesyuarat",senaraiMesyuarat);
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
			
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idStatus", idStatus);
	    this.context.put("idMesyuarat", idMesyuarat); 
	    this.context.put("checkPampasan", checkPampasan);
	    this.context.put("checkSyor", checkSyor);
	        
	    this.context.put("step",step);
	    
		return vm;
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idMesyuarat, String idPermohonan, HttpSession session)
			throws Exception {
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
					saveData(item, session, idMesyuarat, idPermohonan);
				}
			}
		}
	}
	
	private void saveData(FileItem item, HttpSession session,
		String idMesyuarat, String idPermohonan) throws Exception {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		SQLRenderer r = null;
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumenUpload = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_MESYUARAT,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "values(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumenUpload);
			ps.setString(2, getParam("txtNamaImej"));
			ps.setString(3, getParam("txtCatatan"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idMesyuarat);
			ps.setString(9, "L");
			ps.setString(10, idPermohonan);
			
			ps.executeUpdate();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL PELEPASAN [" + idMesyuarat
							+ "] DIDAFTARKAN");

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}