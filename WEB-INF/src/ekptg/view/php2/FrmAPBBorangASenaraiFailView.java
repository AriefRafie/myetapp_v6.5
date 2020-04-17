package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBBorangASenaraiFailData;

public class FrmAPBBorangASenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBBorangASenaraiFailData logic = new FrmAPBBorangASenaraiFailData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
		// PAGING SHJ
		String vm = "";
		String actionLesen = getParam("actionLesen");
		
		String hitButton = getParam("hitButton");
		
		String submit = getParam("command");
		String mode = getParam("mode");

		if (mode.isEmpty()) {
			mode = "view";
		}

		// GET ID PARAM
		String idJadualKedua = getParam("idJadualKedua");
		String idBorangA = getParam("idBorangA");
		String idBarge = getParam("idBarge");

		// VECTOR
		Vector list = null;
		Vector listBorangA = null;

		Vector beanHeader = null;
		Vector beanMaklumatPelesen = null;
		Vector senaraiBorangA = null;
		Vector beanPelesen = null;
		Vector beanMaklumatAmbilPasir = null;
		Vector beanMaklumatBarge = null;
		Vector senaraiBarge = null;
		
		//GET DROPDOWN PARAM
        String idBulan = getParam("socBulan");
		if (idBulan == null || idBulan.trim().length() == 0){
			idBulan = "99999";
		}
		String idBulanList = getParam("socBulanList");
		if (idBulanList == null || idBulanList.trim().length() == 0){
			idBulanList = "99999";
		}
		
		
		// HITBUTTON FOR POPUP MAKLUMAT KJPKJT
		if (postDB) {
			if ("simpanMaklumatAmbilPasir".equals(hitButton)) {

				idBorangA = logic.simpanMaklumatAmbilPasir(idJadualKedua,
						idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"),
						getParam("txtDestinasiDihantar"),
						getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"),
						getParam("txtPembeli"),getParam("txtTarikhMula"),getParam("txtTarikhTamat"),getParam("txtLaluan"),
						getParam("txtKaedah"),getParam("txtKawasan"),
						session);
			}

			if ("simpanKemaskiniMaklumatPasir".equals(hitButton)) {

				logic.simpanKemaskiniMaklumatPasir(idBorangA,
						idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"),
						getParam("txtDestinasiDihantar"),
						getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"),
						getParam("txtPembeli"),getParam("txtTarikhMula"),getParam("txtTarikhTamat"),getParam("txtLaluan"),
						getParam("txtKaedah"),getParam("txtKawasan"),
						session);

			}
			if ("simpanMaklumatBarge".equals(hitButton)) {

				idBarge = logic.simpanMaklumatBarge(idBorangA,
						getParam("txtNamaDaftar"),
						getParam("txtNoPendaftaran"), getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), 
						session);
			}
			if ("simpanKemaskiniMaklumatBarge".equals(hitButton)) {

				logic.simpanKemaskiniMaklumatBarge(idBarge,
						getParam("txtNamaDaftar"),
						getParam("txtNoPendaftaran"), getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), 
						session);

			}
		}
		
		if ("doMaklumatPasir".equals(actionLesen)) {
			
			// HEADER
			beanHeader = new Vector();
			logic.setMaklumatPermohonan(idJadualKedua);
			beanHeader = logic.getBeanMaklumatPermohonan();
			
			this.context.put("BeanHeader", beanHeader);
			
			
			vm = "app/php2/frmAPBBorangAMaklumatAmbilPasir.jsp";
			
			if("new".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanMaklumatAmbilPasir = new Vector();
				 Hashtable hashMaklumatAmbilPasir = new Hashtable();
				 hashMaklumatAmbilPasir.put("tujuanAmbil",getParam("txtTujuanAmbil") == null ? "": getParam("txtTujuanAmbil"));
				 hashMaklumatAmbilPasir.put("destinasiHantar",getParam("txtDestinasiDihantar") == null ? "": getParam("txtDestinasiDihantar"));
				 hashMaklumatAmbilPasir.put("jumlahPasir",getParam("txtAnggaranPasir") == null ? "": getParam("txtAnggaranPasir"));
				 hashMaklumatAmbilPasir.put("jumlahRoyalti",getParam("txtJumlahRoyalti")== null ? "": getParam("txtJumlahRoyalti"));
				 hashMaklumatAmbilPasir.put("bulan",getParam("txtBulan") == null ?"": getParam("txtBulan"));
				 hashMaklumatAmbilPasir.put("tahun",getParam("txtTahun") == null ? "": getParam("txtTahun"));
				 
				 hashMaklumatAmbilPasir.put("kontraktor",getParam("txtKontraktor") == null ? "": getParam("txtKontraktor"));
				 hashMaklumatAmbilPasir.put("pembeli",getParam("txtPembeli") == null ? "": getParam("txtPembeli"));
				 hashMaklumatAmbilPasir.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
				 hashMaklumatAmbilPasir.put("tarikhTamat",getParam("txtTarikhTamat")== null ? "": getParam("txtTarikhTamat"));
				 hashMaklumatAmbilPasir.put("laluan",getParam("txtLaluan") == null ?"": getParam("txtLaluan"));
				 hashMaklumatAmbilPasir.put("kaedah",getParam("txtKaedah") == null ? "": getParam("txtKaedah"));
				 hashMaklumatAmbilPasir.put("kawasan",getParam("txtKawasan") == null ? "": getParam("txtKawasan"));

				 beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				 
			}
			
			if ("view".equals(mode)) {
									
				 this.context.put("readonly", "readonly");
				 this.context.put("inputTextClass", "disabled");
				
				 beanMaklumatAmbilPasir = new Vector();
				 logic.setMaklumatAmbilPasir(idBorangA);
				 beanMaklumatAmbilPasir = logic.getBeanMaklumatAmbilPasir();
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				
				 
				 if (logic.getBeanMaklumatAmbilPasir().size() != 0){
					 Hashtable hashPasir = (Hashtable) logic.getBeanMaklumatAmbilPasir().get(0);
					 idBulan = (String) hashPasir.get("bulan");
					 }
				 
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), "disabled", " class=\"disabled\""));
				 }
			
			if ("update".equals(mode)) {
				
				 this.context.put("readonly", "");
				 this.context.put("inputTextClass", "");
				
				 beanMaklumatAmbilPasir = new Vector();
				 logic.setMaklumatAmbilPasir(idBorangA);
				 
				 Hashtable hashMaklumatAmbilPasir = new Hashtable();
				 hashMaklumatAmbilPasir.put("tujuanAmbil",getParam("txtTujuanAmbil")== null ? "": getParam("txtTujuanAmbil"));
				 hashMaklumatAmbilPasir.put("destinasiHantar",getParam("txtDestinasiDihantar") == null ? "": getParam("txtDestinasiDihantar"));
				 hashMaklumatAmbilPasir.put("jumlahPasir",getParam("txtAnggaranPasir")== null ? "": getParam("txtAnggaranPasir"));
				 hashMaklumatAmbilPasir.put("jumlahRoyalti",getParam("txtJumlahRoyalti")== null ? "": getParam("txtJumlahRoyalti"));
				 hashMaklumatAmbilPasir.put("tahun",getParam("txtTahun") == null ? "": getParam("txtTahun"));
				 hashMaklumatAmbilPasir.put("kontraktor",getParam("txtKontraktor") == null ? "": getParam("txtKontraktor"));
				 hashMaklumatAmbilPasir.put("pembeli",getParam("txtPembeli") == null ? "": getParam("txtPembeli"));
				 hashMaklumatAmbilPasir.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
				 hashMaklumatAmbilPasir.put("tarikhTamat",getParam("txtTarikhTamat")== null ? "": getParam("txtTarikhTamat"));
				 hashMaklumatAmbilPasir.put("laluan",getParam("txtLaluan") == null ?"": getParam("txtLaluan"));
				 hashMaklumatAmbilPasir.put("kaedah",getParam("txtKaedah") == null ? "": getParam("txtKaedah"));
				 hashMaklumatAmbilPasir.put("kawasan",getParam("txtKawasan") == null ? "": getParam("txtKawasan"));
				
				 beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				 
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				 }
			
			if("newBarge".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanMaklumatBarge = new Vector();
				Hashtable hashMaklumatBarge = new Hashtable();
				hashMaklumatBarge.put("namaDidaftarkan",getParam("txtNamaDaftar") == null ? "": getParam("txtNamaDaftar"));
				hashMaklumatBarge.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				hashMaklumatBarge.put("kapasiti",getParam("txtKapasiti") == null ? "": getParam("txtKapasiti"));
				
				hashMaklumatBarge.put("jenis",getParam("txtJenis") == null ? "": getParam("txtJenis"));
				hashMaklumatBarge.put("noTel",getParam("txtNoTel") == null ? "": getParam("txtNoTel"));

				beanMaklumatBarge.addElement(hashMaklumatBarge);
				
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
			}
			
			if ("viewBarge".equals(mode)) {
									
				 this.context.put("readonly", "readonly");
				 this.context.put("inputTextClass", "disabled");
									
				 beanMaklumatBarge = new Vector();
				 logic.setMaklumatBarge(idBarge);
				 beanMaklumatBarge = logic.getBeanMaklumatBarge();
				 
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
				
				 }
				
				 if ("updateBarge".equals(mode)) {
				
				 this.context.put("readonly", "");
				 this.context.put("inputTextClass", "");
				
				 logic.setMaklumatBarge(idBarge);
				 beanMaklumatBarge = new Vector();
				 Hashtable hashMaklumatBarge = new Hashtable();
				 hashMaklumatBarge.put("namaDidaftarkan",getParam("txtNamaDaftar")== null ? "": getParam("txtNamaDaftar"));
				 hashMaklumatBarge.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				 hashMaklumatBarge.put("kapasiti",getParam("txtKapasiti") == null ? "": getParam("txtKapasiti"));
				 
				 hashMaklumatBarge.put("jenis",getParam("txtJenis") == null ? "": getParam("txtJenis"));
				 hashMaklumatBarge.put("noTel",getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
									
				 beanMaklumatBarge.addElement(hashMaklumatBarge);
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
				
				 }
								
			
			
			//LIST BARGE
			senaraiBarge = new Vector();
			senaraiBarge = logic.getSenaraiBarge();

			logic.carianBarge(idBorangA);
			senaraiBarge = logic.getSenaraiBarge();
			
			this.context.put("SenaraiBarge", senaraiBarge);
			setupPage2(session, action, senaraiBarge);
		
			
		} else if ("papar".equals(actionLesen)) {

			vm = "app/php2/frmAPBBorangASenaraiBorang.jsp";
			
			logic.maklumatPelesen(idJadualKedua);
			beanPelesen = logic.getBeanPelesen();
			if (beanPelesen.size() != 0) {
				Hashtable hashPelesen = (Hashtable) logic.getBeanPelesen().get(0);

				this.context.put("namaPelesen", (String) hashPelesen.get("namaPelesen").toString());
				this.context.put("noLesen", (String) hashPelesen.get("noLesen").toString());

			}

			listBorangA = new Vector();
			logic.carian(idJadualKedua, idBulanList, getParam("txtTahun2"));
			listBorangA = logic.getSenaraiBorangA();
			this.context.put("SenaraiBorangA", listBorangA);

			this.context.put("selectBulanList",HTML.SelectBulan("socBulanList", Long.parseLong(idBulanList)," style=\"width:auto\""));
			this.context.put("tahun", getParam("txtTahun2"));

			setupPage1(session, action, listBorangA);
	
		} else {

			// GO TO LIST FAIL LESEN
			vm = "app/php2/frmAPBBorangASenaraiFail.jsp";

			logic.carianFail(getParam("txtNamaPelesen"),getParam("txtNoLesen"));
			list = new Vector();
			list = logic.getSenaraiFail();

			this.context.put("SenaraiFail", list);

			this.context.put("txtNamaPelesen", getParam("txtNamaPelesen"));
			this.context.put("txtNoLesen", getParam("txtNoLesen"));

			setupPage(session, action, list);

		}

		// SET DEFAULT PARAM
		this.context.put("actionLesen", actionLesen);

		// SET DEFAULT ID PARAM
		this.context.put("idJadualKedua", idJadualKedua);
		this.context.put("idBorangA", idBorangA);
		this.context.put("idBarge", idBarge);
		this.context.put("mode", mode);
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
			this.context.put("SenaraiFail", paging.getPage(page));
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

	public void setupPage1(HttpSession session, String action, Vector list) {

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
			this.context.put("SenaraiBorangA", paging.getPage(page));
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
	
	public void setupPage2(HttpSession session, String action, Vector list) {

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
			this.context.put("SenaraiBarge", paging.getPage(page));
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
