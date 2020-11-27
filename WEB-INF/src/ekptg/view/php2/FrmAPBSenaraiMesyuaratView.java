
package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmAPBSenaraiMesyuaratData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

/**
 * @author nurulain
 * 
 */
public class FrmAPBSenaraiMesyuaratView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(ekptg.view.php2.FrmAPBSenaraiMesyuaratView.class);

	FrmAPBSenaraiMesyuaratData logic = new FrmAPBSenaraiMesyuaratData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		this.context.put("userId", userId);
		this.context.put("userRole", userRole);
		this.context.put("idNegeriUser", idNegeriUser);
		this.context.put("close_window", "NO");

		// GET DEFAULT PARAM
		String vm = "";
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String step = getParam("step");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		String actionMesyuarat = getParam("actionMesyuarat");
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		String refreshPaparan = getParam("refreshPaparan").toString();
		
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		
        if (mode.isEmpty()){
        	mode = "view";
        }

		// GET ID PARAM
        String idSuburusan = getParam("idSuburusan");
		String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idMesyuarat = getParam("idMesyuarat");
        String idKehadiran = getParam("idKehadiran");
		String flagPermohonanDari = getParam("flagPermohonanDari");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagResult = getParam("flagResult");
		String catatan = getParam("catatan");
		String idMesyuaratPermohonan = getParam("idMesyuaratPermohonan");
		String idDokumen = getParam("idDokumen");
		
		// VECTOR
		Vector list = null;
		Vector senaraiMesyuarat = null;
		Vector beanMaklumatMesyuarat = null;
		Vector beanMaklumatKehadiran = null;
		Vector senaraiKehadiran = null;
		Vector senaraiFailMohonBaru = null;
		Vector senaraiFailMohonLanjut = null;
		Vector beanMaklumatPengerusi = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatImejan = null;
		Vector beanMaklumatLampiran = null;
		this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
		
		// GET DROPDOWN PARAM
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		
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
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0) {
			idPegawai = "99999";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		//HITBUTTON
		if (postDB) {
			if ("simpanMesyuarat".equals(hitButton)) {
				idMesyuarat = logic.simpanMesyuarat(getParam("txtTarikhMesyuarat"), 
						getParam("txtBilMesyuarat"), getParam("txtTujuanMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga, getParam("txtCatatanMesyuarat"), idLokasi, session);
				this.context.put("idMesyuarat", idMesyuarat);
			}
			if ("simpanKemaskiniMesyuarat".equals(hitButton)) {
				logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTarikhMesyuarat"),
						getParam("txtBilMesyuarat"), getParam("txtTajukMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga, getParam("txtCatatanMesyuarat"), idLokasi, session);
			}
			
			if ("simpanKehadiran".equals(hitButton)) {

				String listNama[] = this.request.getParameterValues("txtNama");
				String listAgensi[] = this.request
						.getParameterValues("txtAgensi");
				String listJawatan[] = this.request
						.getParameterValues("txtJawatan");
				String listNoTel[] = this.request
						.getParameterValues("txtNoTel");
				String listEmail[] = this.request
						.getParameterValues("txtEmail");
				if (listNama != null) {
					for (int i = 0; i < listNama.length; i++) {
						if (!listNama[i].equals("")) {
							logic.saveKehadiran(i, idMesyuarat, listNama[i],
									listAgensi[i], listJawatan[i],
									listNoTel[i], listEmail[i],getParam("flagPengerusi"),
									session);
							logic.sendEmailMesyuarat(idMesyuarat, listEmail[i], session);
						}
					}
				}
			}
			
			if ("simpanKemaskiniKehadiran".equals(hitButton)) {
				logic.updateKehadiran(idMesyuarat, idKehadiran,
						getParam("txtNama"), getParam("txtAgensi"),
						getParam("txtNoTel"), getParam("txtJawatan"),
						getParam("socPengerusi"), session);
			}
			if ("hapusKehadiran".equals(hitButton)) {
				logic.hapusKehadiranMesyuarat(idKehadiran, session);
			}
			if ("simpanLampiran".equals(hitButton)) {
				uploadFiles(idMesyuarat, session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idMesyuarat, idPermohonan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen,
						getParam("txtNamaImej"), getParam("txtCatatanImej"),
						session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
			if ("doBatalPermohonan".equals(hitButton)) {
//				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
//						getParam("tarikhBatal"), getParam("txtSebab"), session);
//				step = "";
			}
			
			if ("simpanKeputusanBaru".equals(hitButton)) {
				logic.simpanKeputusanPermohonanBaru(idMesyuaratPermohonan,flagResult,session);
			}
			if ("simpanCatatanBaru".equals(hitButton)) {
				logic.simpanCatatanPermohonanBaru(idMesyuaratPermohonan,catatan,session);
			}
			if ("hapusPermohonanMesyuarat".equals(hitButton)) {
				logic.hapusPermohonanMesyuarat(idMesyuaratPermohonan,session);
			}
			if ("doSelesaiMesyuarat".equals(hitButton)) {
				logic.updateStatus(idMesyuarat, session);
			}
			if ("hapusMesyuarat".equals(hitButton)){
        		logic.hapusDokumen(idMesyuarat, session);
    			logic.hapusMesyuarat(idMesyuarat, session);
			}
			
		}
		myLogger.info("actionPenyewaan : " +actionMesyuarat);
		myLogger.info("mode : " +mode);
		
		if ("daftarBaru".equals(actionMesyuarat)) {
			
			vm = "app/php2/frmPYWDaftarMesyuarat.jsp";
						
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			this.context.put("idUrusan", "9");
			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));
			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitDari), "", ""));
			this.context.put("selectLokasi", HTML.SelectLokasiMesyuaratNegeri("socLokasi", Long.parseLong(idLokasi), "", "", "16"));
			
		} else if ("papar".equals(actionMesyuarat)) {
			
			vm = "app/php2/frmAPBMaklumatMesyuaratSenaraiPermohonan.jsp";
			
			if ("1".equals(selectedTabUpper)) {

				if ("update".equals(modePopup)) {

					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);

				} else {

					this.context.put("readonlyPopup", "readonly");
					this.context.put("inputTextClassPopup", "disabled");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
				}

				// SENARAI KEHADIRAN MESYUARAT
				logic.setSenaraiKehadiran(idMesyuarat);
				senaraiKehadiran = logic.getListKehadiran();
				this.context.put("SenaraiKehadiran", senaraiKehadiran);

			} else if ("2".equals(selectedTabUpper)) {
				
				// SENARAI MESYUARAT PERMOHONAN BAHARU
				logic.setSenaraiPermohonanBaharu(idMesyuarat);
				senaraiFailMohonBaru = logic.getListPermohonanBaharu();
				this.context.put("SenaraiFailMohonBaru", senaraiFailMohonBaru);
				this.context.put("totalRecords", senaraiFailMohonBaru.size());
				
				// SENARAI MESYUARAT PERMOHONAN PERLANJUTAN
				logic.setSenaraiPermohonanLanjut(idMesyuarat);
				senaraiFailMohonLanjut = logic.getListPermohonanLanjut();
				this.context.put("SenaraiFailMohonLanjut", senaraiFailMohonLanjut);
				this.context.put("totalLanjutRecords", senaraiFailMohonLanjut.size());
				
				if(refreshPaparan.equals("true")){
					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
				}		
			} else if ("3".equals(selectedTabUpper)) {
				
				if ("openPopupDokumen".equals(flagPopup)) {

					if ("new".equals(modePopup)) {

						beanMaklumatImejan = new Vector();
						Hashtable hashMaklumatImejan = new Hashtable();
						hashMaklumatImejan.put("namaImej", "");
						hashMaklumatImejan.put("catatanImej", "");
						beanMaklumatImejan.addElement(hashMaklumatImejan);
						this.context.put("BeanMaklumatImejan",beanMaklumatImejan);

					} else {

						// MAKLUMAT DOKUMEN
						beanMaklumatImejan = new Vector();
						logic.setMaklumatImej(idDokumen);
						beanMaklumatImejan = logic.getBeanMaklumatImejan();
						this.context.put("BeanMaklumatImejan", beanMaklumatImejan);

					}
				}

				// SENARAI IMEJAN
				senaraiImejan = new Vector();
				logic.setSenaraiImejan(idMesyuarat);
				senaraiImejan = logic.getListImejan();
				this.context.put("SenaraiImejan", senaraiImejan);
				
			} else {

				// MODE VIEW
				if ("view".equals(mode)) {

					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
					if (beanMaklumatMesyuarat.size() != 0) {
						Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
						idLokasi = (String) (hashMesyuarat.get("idLokasi"));
						idJamDari = (String) (hashMesyuarat.get("idJamDari"));
						idMinitDari = (String) (hashMesyuarat.get("idMinitDari"));
						idJamHingga = (String) (hashMesyuarat.get("idJamHingga"));
						idMinitHingga = (String) (hashMesyuarat.get("idMinitHingga"));
					}

					this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(idLokasi), "disabled", " class=\"disabled\""));
					this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
					this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
					this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
					this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));
					
					// LAMPIRAN MAKLUMAT MESYUARAT
					beanMaklumatLampiran = new Vector();
					beanMaklumatLampiran = logic.getBeanMaklumatLampiran(idMesyuarat);
					this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);

				} else {

					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);

					this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "", ""));
					this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));
					this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamDari), "", ""));
					this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitDari), "", ""));
					this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "", ""));
				}
			}
			
		} else {
			
			// GO TO LIST SENARAI MESYUARAT
			vm = "app/php2/frmAPBSenaraiFailMesyuarat.jsp";

			logic.carianSenaraiMesyuarat(getParam("txtCarianTajukMesyuarat"), getParam("txtCarianBilMesyuarat"), 
					getParam("txtCarianTarikhMesyuarat"), userId, userRole);
			//logic.listSenaraiMesyuarat();
			
			list = new Vector();
			
			list = logic.getSenaraiMesyuarat();
			
			this.context.put("SenaraiMesyurat", list);
			
			this.context.put("txtTajukMesyuarat", getParam("txtTajukMesyuarat"));
			this.context.put("txtBilMesyuarat", getParam("txtBilMesyuarat"));
			this.context.put("txtTarikhMesyuarat", getParam("txtTarikhMesyuarat"));
			setupPage(session, action, list);
			
		}

		// SET DEFAULT PARAM
		this.context.put("actionMesyuarat", actionMesyuarat);
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idMesyuarat", idMesyuarat); 
	    this.context.put("idKehadiran", idKehadiran);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
	    this.context.put("flagPermohonanDari", flagPermohonanDari); 
	        
	    this.context.put("step",step);
	    
	    
	    if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
		
		if (session.getAttribute("MSG") != null){
			this.context.put("errMsg", session.getAttribute("MSG"));
			session.removeAttribute("MSG");
		} else {
			this.context.put("errMsg", "");
		}
				
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
			this.context.put("SenaraiMesyuarat", paging.getPage(page));
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
	public void saveKehadiran(int i, String idMesyuarat, String nama,
			String agensi, String jawatan, String noTel, String flagPengerusi,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if (!"".equals(flagPengerusi)) {
				if (Integer.parseInt(flagPengerusi) == (i + 1)) {
					resetFlagPengerusi(idMesyuarat, db, userId);
				}
			}

			// TBLPHPKEHADIRANMESY
			long idKehadiran = DB.getNextID("TBLPHPKEHADIRANMESY_SEQ");
			r.add("ID_KEHADIRAN", idKehadiran);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("NAMA_PEGAWAI", nama);
			r.add("NAMA_AGENSI", agensi);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("NO_TELEFON", noTel);
			if (!"".equals(flagPengerusi)) {
				if (Integer.parseInt(flagPengerusi) == (i + 1)) {
					r.add("FLAG_PENGERUSI", "Y");
				} else {
					r.add("FLAG_PENGERUSI", "T");
				}
			} else {
				r.add("FLAG_PENGERUSI", "T");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL [" + idKehadiran + "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void resetFlagPengerusi(String idMesyuarat, Db db, String userId)
			throws Exception {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKEHADIRANMESY
			r.update("ID_MESYUARAT", idMesyuarat);
			r.add("FLAG_PENGERUSI", "T");

			sql = r.getSQLUpdate("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}
	
	// UPLOAD FILE
	private void uploadFiles(String idMesyuarat,
			HttpSession session) throws Exception {
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
					saveData(item, idMesyuarat, session);
				}
			}
		}
	}
	
	private void uploadFiles(String idMesyuarat, String idPermohonan,
			HttpSession session) throws Exception {
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
					saveData(item, idMesyuarat, idPermohonan, session);
				}
			}
		}
	}
	
	private void saveData(FileItem item, String idMesyuarat,
			String idPermohonan, HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_MESYUARAT,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idMesyuarat);
			ps.setString(9, "MM");// MINIT MESYUARAT
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
			this.context.put("idDokumen", idDokumen);

		} finally {
			if (db != null)
				db.close();
		}

		this.context.put("completed", true);
	}
	
	private void saveData(FileItem item, String idMesyuarat,
			HttpSession session) throws Exception {

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
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_MESYUARAT,FLAG_DOKUMEN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idMesyuarat);
			ps.setString(9, "L");// LAMPIRAN MESYUARAT
			ps.executeUpdate();

			con.commit();
			this.context.put("idDokumen", idDokumen);

		} finally {
			if (db != null)
				db.close();
		}

		this.context.put("completed", true);
	}
}
