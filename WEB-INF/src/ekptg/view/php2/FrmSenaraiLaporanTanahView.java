package ekptg.view.php2;

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
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmSenaraiLaporanTanahData;

public class FrmSenaraiLaporanTanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmSenaraiLaporanTanahData logic = new FrmSenaraiLaporanTanahData();
	
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String submit = getParam("command");
		String vm = "";
		String step = getParam("step");
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTab = (String) getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		// GET ID PARAM
		String idLaporan = getParam("idLaporan");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idDokumen = getParam("idDokumen");
		
		// VECTOR
		Vector list = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatLaporan = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatImejan = null;
		
		// GET DROPDOWN PARAM
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		
		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		this.context.put("onload", "");
		this.context.put("completed", false);
		this.context.put("errorPeganganHakmilik", "");
		
		//HITBUTTON
		if (postDB) {
			if ("simpan".equals(hitButton)) {
				idLaporan = logic.daftarLaporan(idHakmilikAgensi, getParam("txdTarikhLawatan"), getParam("txtTujuanLaporan"), getParam("txtCatatan"), 
						getParam("txtPelapor"), getParam("txtJawatan"), idNegeri, session);
			}
			if ("kemaskini".equals(hitButton)) {
				logic.kemaskiniLaporan(idLaporan, getParam("txdTarikhLawatan"), getParam("txtTujuanLaporan"), getParam("txtCatatan"), 
						getParam("txtPelapor"), getParam("txtJawatan"), idNegeri, getParam("txtJalanHubungan"),
						getParam("txtKawasanBerhampiran"), getParam("txtJarakDariBandar"), getParam("kemudahanAsasA"), 
						getParam("kemudahanAsasL"), getParam("kemudahanAsasT"), getParam("txtKemudahanAsasLain"), 
						getParam("txtKeadaanTanah"), getParam("txtKeadaanRupabumi"), getParam("txtUtara"), 
						getParam("txtSelatan"), getParam("txtTimur"), getParam("txtBarat"), session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idLaporan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen);
			}
		}
		
		if ("daftar".equals(step)) {
			
			// GO TO DAFTAR LAPORAN TANAH
			vm = "app/php2/frmDaftarLaporanTanah.jsp";
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}			
			
			beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logic.getMaklumatTanah(idHakmilikAgensi);
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			this.context.put("txdTarikhLawatan", getParam("txdTarikhLawatan"));
			this.context.put("txtTujuanLaporan", getParam("txtTujuanLaporan"));
			this.context.put("txtCatatan", getParam("txtCatatan"));
			this.context.put("txtPelapor", getParam("txtPelapor"));
			this.context.put("txtJawatan", getParam("txtJawatan"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "",""));
			
		} else if ("kemaskini".equals(step)) {
			
			// GO TO MAKLUMAT LAPORAN TANAH
			vm = "app/php2/frmMaklumatLaporanTanah.jsp";
			
			beanMaklumatLaporan = new Vector();
			beanMaklumatLaporan = logic.getMaklumatLaporan(idLaporan);
			this.context.put("BeanMaklumatLaporan", beanMaklumatLaporan);
			if (beanMaklumatLaporan.size() != 0){
				Hashtable hashLaporan = (Hashtable) beanMaklumatLaporan.get(0);
				idHakmilikAgensi = (String) hashLaporan.get("idHakmilikAgensi");
				idNegeri = (String) hashLaporan.get("idNegeri");
			}
			
			beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logic.getMaklumatTanah(idHakmilikAgensi);
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "",""));
			
			if ("2".equals(selectedTab)){
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
				senaraiImejan = logic.getSenaraiImejan(idLaporan);
				this.context.put("SenaraiImejan", senaraiImejan);
			}
			
		} else {
			
			// GO TO SENARAI LAPORAN TANAH
			vm = "app/php2/frmSenaraiLaporanTanah.jsp";
			
			//GET DROP DOWN PARAM
			String idKementerianC = getParam("socKementerianC");
			if (idKementerianC == null || idKementerianC.trim().length() == 0) {
				idKementerianC = "99999";
			}
			String idAgensiC = getParam("socAgensiC");
			if (idAgensiC == null || idAgensiC.trim().length() == 0) {
				idAgensiC = "99999";
			}
			String idNegeriC = getParam("socNegeriC");
			if (idNegeriC == null || idNegeriC.trim().length() == 0) {
				idNegeriC = "99999";
			}
			String idDaerahC = getParam("socDaerahC");
			if (idDaerahC == null || idDaerahC.trim().length() == 0) {
				idDaerahC = "99999";
			}
			String idMukimC = getParam("socMukimC");
			if (idMukimC == null || idMukimC.trim().length() == 0) {
				idMukimC = "99999";
			}
			String idJenisHakmilikC = getParam("socJenisHakmilikC");
			if (idJenisHakmilikC == null || idJenisHakmilikC.trim().length() == 0) {
				idJenisHakmilikC = "99999";
			}
			String idLotC = getParam("socLotC");
			if (idLotC == null || idLotC.trim().length() == 0) {
				idLotC = "99999";
			}
			
			list = new Vector();
			list = logic.getSenaraiLaporan(getParam("txdTarikhLawatanC"), getParam("txtPelaporC"),idNegeriC, idDaerahC,
					idMukimC, getParam("txtNoPeganganC"), idJenisHakmilikC, getParam("txtNoHakmilikC"),
					getParam("txtNoWartaC"), idLotC, getParam("txtNoLotC"));
			this.context.put("SenaraiLaporan", list);
			
			this.context.put("txdTarikhLawatanC", getParam("txdTarikhLawatanC"));
			this.context.put("txtPelaporC", getParam("txtPelaporC"));			
			
			this.context.put("txtNoPeganganC", getParam("txtNoPeganganC"));
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilikC", Long.parseLong(idJenisHakmilikC), ""));
			this.context.put("txtNoHakmilikC", getParam("txtNoHakmilikC"));
			this.context.put("txtNoWartaC", getParam("txtNoWartaC"));
			this.context.put("selectLot", HTML.SelectLot("socLotC",Long.parseLong(idLotC), ""));
			this.context.put("txtNoLotC", getParam("txtNoLotC"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
			
			setupPage(session, action, list);
		}
		
		// SET DEFAULT PARAM
		this.context.put("step", step);
		this.context.put("selectedTab", selectedTab);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
		// SET DEFAULT ID PARAM
		this.context.put("idLaporan", idLaporan);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
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
			this.context.put("SenaraiLaporan", paging.getPage(page));
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

	private void saveData(FileItem item, String idLaporan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();

			// TBLPHPDOKUMENLAPORANTANAH
			long idDokumen = DB.getNextID("TBLPHPDOKUMENLAPORANTANAH_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMENLAPORANTANAH "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_LAPORANTANAH) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idLaporan);
			ps.executeUpdate();

			con.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}
