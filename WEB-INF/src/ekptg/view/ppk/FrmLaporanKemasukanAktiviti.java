package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmLaporanKemasukanAktivitiData;

public class FrmLaporanKemasukanAktiviti extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmLaporanKemasukanAktivitiData logic = new FrmLaporanKemasukanAktivitiData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("userId", userId);

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionAktiviti = getParam("actionAktiviti");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");

		// GET ID PARAM
		String idAktiviti = getParam("idAktiviti");
		
		// VECTOR
		Vector list = null;
		Vector beanMaklumatAktiviti = null;

		// HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idAktiviti = logic.daftarBaru(getParam("txtNamaAktiviti"), getParam("txtLokasiAktiviti"),
						getParam("txtKeteranganAktiviti"), getParam("txtTarikhMula"), getParam("txtTarikhTamat"),
						getParam("txtCatatan"), getParam("txtMasaMula"), getParam("txtMasaTamat"), session);
			}
			if ("simpanKemaskini".equals(hitButton)) {
				logic.simpanKemaskini(idAktiviti, getParam("txtNamaAktiviti"), getParam("txtLokasiAktiviti"),
						getParam("txtKeteranganAktiviti"), getParam("txtTarikhMula"), getParam("txtTarikhTamat"),
						getParam("txtCatatan"), getParam("txtMasaMula"), getParam("txtMasaTamat"), session);
			}
			if ("hapusAktiviti".equals(hitButton)) {
				logic.hapusAktiviti(idAktiviti);
			}
		}

		if ("papar".equals(actionAktiviti)) {
			
			vm = "app/ppk/frmDaftarAktiviti.jsp";
			
			this.context.put("mode", "view");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatAktiviti = new Vector();
			logic.setMaklumatAktiviti(idAktiviti);
			beanMaklumatAktiviti = logic.getBeanMaklumatAktiviti();
			this.context.put("BeanMaklumatAktiviti", beanMaklumatAktiviti);

		} else if ("daftarBaru".equals(actionAktiviti)) { 
			
			// GO TO DAFTAR BARU AKTIVITI
			vm = "app/ppk/frmDaftarAktiviti.jsp";
			
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");	
			
			// MAKLUMAT AKTIVITI
			beanMaklumatAktiviti = new Vector();
			Hashtable hashMaklumatAktiviti = new Hashtable();
			hashMaklumatAktiviti.put("namaAktiviti", getParam("txtNamaAktiviti") == null ? "": getParam("txtNamaAktiviti"));			
			hashMaklumatAktiviti.put("lokasiAktiviti", getParam("txtLokasiAktiviti") == null ? "": getParam("txtLokasiAktiviti"));			
			hashMaklumatAktiviti.put("keteranganAktiviti", getParam("txtKeteranganAktiviti") == null ? "": getParam("txtKeteranganAktiviti"));
			hashMaklumatAktiviti.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
			hashMaklumatAktiviti.put("tarikhMula", getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
			hashMaklumatAktiviti.put("masaMula", getParam("txtMasaMula") == null ? "": getParam("txtMasaMula"));			
			hashMaklumatAktiviti.put("tarikhTamat", getParam("txtTarikhTamat") == null ? "": getParam("txtTarikhTamat"));
			hashMaklumatAktiviti.put("masaTamat", getParam("txtMasaTamat") == null ? "": getParam("txtMasaTamat"));
			hashMaklumatAktiviti.put("flagFullDay", getParam("txtFlagFullDay") == null ? "": getParam("txtFlagFullDay"));
			beanMaklumatAktiviti.addElement(hashMaklumatAktiviti);
			this.context.put("BeanMaklumatAktiviti", beanMaklumatAktiviti);
			
		} else {
			
			String idBulan = getParam("socBulan");
			if (idBulan == null || idBulan.trim().length() == 0){
				idBulan = "99999";
			}
			String idTahun = getParam("socTahun");
			if (idTahun == null || idTahun.trim().length() == 0){
				idTahun = "99999";
			}

			// GO TO LIST AKTIVITI
			vm = "app/ppk/frmSenaraiAktiviti.jsp";
			
			logic.carianAktiviti(getParam("txtNamaAktivitiC"), getParam("txdTarikhMulaDariC"), getParam("txdTarikhMulaHinggaC"), idBulan, idTahun, session);
			
			list = new Vector();
			list = logic.getSenaraiAktiviti();
			this.context.put("SenaraiAktiviti", list);
			setupPage(session, action, list);
			
			this.context.put("txtNamaAktivitiC", getParam("txtNamaAktivitiC"));
			this.context.put("txdTarikhMulaDariC", getParam("txdTarikhMulaDariC"));
			this.context.put("txdTarikhMulaHinggaC", getParam("txdTarikhMulaHinggaC"));
			this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), "", " style=\"width:auto\""));
			this.context.put("selectTahun",HTML.SelectTahun("socTahun", idTahun, "", " style=\"width:auto\""));
		}

		// SET DEFAULT PARAM
		this.context.put("actionAktiviti", actionAktiviti);

		// SET DEFAULT ID PARAM
		this.context.put("idAktiviti", idAktiviti);
				
		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
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
			this.context.put("SenaraiAktiviti", paging.getPage(page));
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
}
