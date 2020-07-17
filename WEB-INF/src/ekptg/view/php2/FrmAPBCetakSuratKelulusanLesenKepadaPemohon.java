package ekptg.view.php2;

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

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmCetakSuratKelulusanLesenKepadaPemohonData;


public class FrmAPBCetakSuratKelulusanLesenKepadaPemohon extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmCetakSuratKelulusanLesenKepadaPemohonData logic = new FrmCetakSuratKelulusanLesenKepadaPemohonData();

	
	//@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String vm = ""; 
        String hitButton = getParam("hitButton");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagReKeyin = "";
        
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idDokumen = getParam("idDokumen");
        String idLaporanPasir = getParam("idLaporanPasir");
		this.context.put("completed",false);
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0) {
			idLuas = "99999";
		}
		//GET DROPDOWN PARAM -AIN-
		String idTempoh = getParam("socTempoh");
		if (idTempoh == null || idTempoh.trim().length() == 0){
			idTempoh = "99999";	
		}
		
        //VECTOR
		Vector beanHeader= null;
		Vector beanMaklumatSuratKelulusanLesenKepadaPemohon = null;
		Vector senaraiPelan = null;
        Vector beanMaklumatPelan = null;

		String step = getParam("step");
		String actionPopup = getParam("actionPopup");
		
		this.context.put("onload", "");
		
		vm = "app/php2/frmAPBCetakSuratKelulusanLesenKepadaPemohon.jsp"; 
		
		if (postDB) {
			if ("doSeterusnya".equals(hitButton)){
				
		    	if(logic.checkMaklumatPenyediaanLesen(idPermohonan)){
    				this.context.put("onload", " \"alert('Sila masukkan nombor lesen.')\"");
    			} else {
    				logic.updateStatus(idFail, idPermohonan, session);
    			}
    		}        
	    	if ("doSimpanKemaskiniSuratKelulusanLesenKepadaPemohon".equals(hitButton)){
				logic.updateMaklumatSuratKelulusanLesenKepadaPemohon(idPermohonan,getParam("txtNoLesen"),getParam("txtTarikhMula") ,getParam("txtTarikhTamat") ,session);
			}
	    	if ("simpanPelan".equals(hitButton)) {
				uploadPelan(idPermohonan, session);
			}
			if ("simpanKemaskiniPelan".equals(hitButton)) {
				logic.simpanKemaskiniPelan(idDokumen, getParam("txtNamaPelan"), getParam("txtCatatanPelan"), session);
			}
			if ("hapusPelan".equals(hitButton)) {
				logic.hapusPelan(idDokumen, session);
			}
	    	if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
    	}
		
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String)hashHeader.get("idFail");
			idPermohonan = (String)hashHeader.get("idPermohonan");
			idStatus = hashHeader.get("idStatus").toString();
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}

		// GET FLAG OPEN DETAIL
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 12); // 12 = CETAKAN SURAT KELULUSAN LESEN KEPADA PEMOHON
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		if ("0".equals(selectedTabUpper)){
			
			// MODE VIEW
			if("view".equals(mode)){
	      	
	        	this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				// VIEW TEMPOH DILULUSKAN -AIN-
				this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"disabled", " style=\"width:100px\" class=\"disabled\""));
	        	
				//MAKLUMAT PENYEDIAAN LESEN DAN MEMO
				beanMaklumatSuratKelulusanLesenKepadaPemohon = new Vector();
		    	logic.setMaklumatSuratKelulusanLesenKepadaPemohon(idPermohonan);
		    	beanMaklumatSuratKelulusanLesenKepadaPemohon = logic.getBeanMaklumatSuratKelulusanLesenKepadaPemohon();
		       	this.context.put("BeanMaklumatSuratKelulusanLesenKepadaPemohon", beanMaklumatSuratKelulusanLesenKepadaPemohon);       
			}

			// MODE UPDATE
			else if("update".equals(mode)){
				
				this.context.put("readonly", "");
	    		this.context.put("inputTextClass", "");
	    		this.context.put("disabled", "");	
	    		
	    		this.context.put("selectTempoh",HTML.SelectBulanTahun("socTempoh", Long.parseLong(idTempoh),"", " style=\"width:100px\""));
	    		
	    		//MAKLUMAT PENYEDIAAN LESEN DAN MEMO
				beanMaklumatSuratKelulusanLesenKepadaPemohon = new Vector();
		    	logic.setMaklumatSuratKelulusanLesenKepadaPemohon(idPermohonan);
		    	beanMaklumatSuratKelulusanLesenKepadaPemohon = logic.getBeanMaklumatSuratKelulusanLesenKepadaPemohon();
		       	this.context.put("BeanMaklumatSuratKelulusanLesenKepadaPemohon", beanMaklumatSuratKelulusanLesenKepadaPemohon);       
			}
			
		} else if ("1".equals(selectedTabUpper)){
			
			//OPEN POPUP PELAN
        	if ("openPopupPelan".equals(flagPopup)){
        		
        		if ("new".equals(modePopup)){
        			
        			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			beanMaklumatPelan = new Vector();    			
	    			Hashtable hashMaklumatPelan = new Hashtable();
	    			hashMaklumatPelan.put("namaPelan", "");
	    			hashMaklumatPelan.put("catatanPelan", "");
	    			beanMaklumatPelan.addElement(hashMaklumatPelan);
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
	    			
        		} else if ("update".equals(modePopup)){
        			
        			this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			//MAKLUMAT PELAN
					beanMaklumatPelan = new Vector();
					logic.setMaklumatPelan(idDokumen);
					beanMaklumatPelan = logic.getBeanMaklumatPelan();
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
	    			
        		} else if ("view".equals(modePopup)){
        			
        			this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			
	    			//MAKLUMAT DOKUMEN
					beanMaklumatPelan = new Vector();
					logic.setMaklumatPelan(idDokumen);
					beanMaklumatPelan = logic.getBeanMaklumatPelan();
					this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
        		}
        	} 
    		
    		//SENARAI PELAN
			senaraiPelan = new Vector();
			logic.setSenaraiPelan(idPermohonan);
			senaraiPelan = logic.getListPelan();
			this.context.put("SenaraiPelan", senaraiPelan);
			
		}
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		if ("paparSejarah".equals(actionPopup)){
        	vm = "app/php2/frmPopupSejarahMaklumatLesen.jsp";
        }

		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		
	    //SET ID PARAM
		this.context.put("idFail", idFail);
	    this.context.put("idPermohonan", idPermohonan); 
	    this.context.put("idStatus", idStatus);
	    this.context.put("idLaporanPasir", idLaporanPasir);
		this.context.put("idDokumen", idDokumen);
		this.context.put("step",step);
		
		if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
		
		return vm;
	}
	
	private void uploadPelan(String idPermohonan, HttpSession session) throws Exception {
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
					savePelan(item, idPermohonan, session);
				}
			}
		}
	}

	private void savePelan(FileItem item, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaPelan"));
			ps.setString(3, getParam("catatanPelan"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, "P");
			ps.setString(9, idPermohonan);
			ps.executeUpdate();

			con.commit();
			
			AuditTrail.logActivity("1610239", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}

