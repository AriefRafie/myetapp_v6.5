package ekptg.view.str;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.str.FrmSTRSkimBgnnKhasData;

public class FrmSemakMaklumatStrata extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8917493131595224020L;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		FrmSTRSkimBgnnKhasData skimBgnnKhasModel = new FrmSTRSkimBgnnKhasData();
		Vector vList = null;
		Vector vCf = new Vector();
		Vector vPemilik = new Vector ();

		String moduleName = (String) session.getAttribute("_portal_module");
		String[] mName = moduleName.split("_");

		// context.put("moduleName", mName[1].toLowerCase());

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String submit = getParam("command");
		String vm = "";
		String RO_General = "";
		String DIS_General = "";
		// String step = getParam("step");
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String mode = getParam("mode");
		String selectedTab = (String) getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		// GET ID PARAM
		String idStrata = getParam("idStrata");
		String paramNegeri = getParam("paramNegeri");
		String paramNegeri1 = getParam("paramNegeri1");
		String paramDaerah = getParam("paramDaerah");
		String paramMukim = getParam("paramMukim");
		String paramJenisHakmilik = getParam("paramJenisHakmilik");
		String paramBandar = getParam("paramBandar");
		String paramKodLot = getParam("paramKodLot");
		String paramNoLot = getParam("paramNoLot");
		String paramNoCF = getParam("paramNoCF");
		String paramNamaPemaju = getParam("paramNamaPemaju");
		String paramNamaPemilik = getParam("paramNamaPemilik");
		String paramNamaSkim = getParam("paramNamaSkim");
		String paramNoStrata = getParam("paramNoStrata");

		// HITBUTTON
		boolean flagLoadData = false;
		if (postDB) {
			if ("papar".equals(hitButton)) {
				Vector data = new Vector();
				vCf.clear();
				vPemilik.clear();
				context.put("idcfs", null);
				context.put("idPemilik", null);
				RO_General = "readonly=\"readonly\"";
				DIS_General = "disabled=\"disabled\"";
				
				idStrata = getParam("idStrata");
				//get data from db to display on jsp
				data = skimBgnnKhasModel.getData(idStrata);

				
				vPemilik = skimBgnnKhasModel.getDataPemilik(idStrata);
				context.put("ListPemilik", vPemilik);
				
				setupPage(session, action, vPemilik);
				
				if(data.size()!=0){
					Hashtable h = (Hashtable)data.get(0);
					
					// Maklumat Pembangunan Strata
				    this.context.put("selectNegeriStr",skimBgnnKhasModel.getNegeri(h.get("ID_NEGERI_STR")+"").getNamaNegeri());
				    this.context.put("selectIdLot",skimBgnnKhasModel.getLot(h.get("ID_LOT")+"").getKeterangan());
					
				 // Maklumat Hakmilik Pembangunan Strata
					this.context.put("selectNegeriHM", skimBgnnKhasModel.getNegeri(h.get("ID_NEGERI")+"").getNamaNegeri());
					this.context.put("selectDaerah", skimBgnnKhasModel.getDaerah(h.get("ID_DAERAH")+"").getNamaDaerah());
					this.context.put("selectMukim", skimBgnnKhasModel.getMukim(h.get("ID_BANDARPEKANMUKIM")+"").getNamaMukim());
					this.context.put("selectJenisHakmilik",skimBgnnKhasModel.getJenishakmilik(h.get("ID_KODJENISHAKMILIK")+"").getKeterangan());
				    
					
					// Maklumat Pemaju
				    this.context.put("selectNegeriPemaju",skimBgnnKhasModel.getNegeri(h.get("ID_KODNEGERIPEMAJU")+"").getNamaNegeri());
				    this.context.put("selectBandarPemaju",skimBgnnKhasModel.getBandarDesc(h.get("ID_KODBANDARPEMAJU")+"").getKeterangan());
				    
				 // Maklumat Pemilik
				    this.context.put("selectNegeriPemilik",skimBgnnKhasModel.getNegeri(h.get("ID_KODNEGERIPEMILIK")+"").getNamaNegeri());
				    this.context.put("selectBandarPemilik",skimBgnnKhasModel.getBandarDesc(h.get("ID_KODBANDARPEMILIK")+"").getKeterangan());
					
					// Maklumat Skim
					this.context.put("selectNegeriSkim",skimBgnnKhasModel.getNegeri(h.get("ID_KODNEGERISKIM")+"").getNamaNegeri());
					this.context.put("selectBandarSkim",skimBgnnKhasModel.getBandarDesc(h.get("ID_KODBANDARSKIM")+"").getKeterangan());
					
					this.context.put("idNegeri",  h.get("ID_NEGERI_STR"));
					this.context.put("idStrata",  h.get("ID_STRATA"));
					this.context.put("idTblstrhakmilik",  h.get("ID_TBLSTRHAKMILIK"));
					this.context.put("bilPetak",  h.get("BIL_PETAK"));
					this.context.put("noFailMajlis",  h.get("NO_FAILMAJLIS")); 
					this.context.put("tarikhLulusmajlis",  h.get("TARIKH_LULUSMAJLIS"));
					this.context.put("radioPemilik",  h.get("JENIS_PEMILIK"));
					this.context.put("noPengenalan",  h.get("NOPENGENALAN_PEMILIK"));
					this.context.put("namaPemilik",  h.get("NAMA_PEMILIK"));
					this.context.put("alamat1Pemilik",  h.get("ALAMAT1_PEMILIK"));
					this.context.put("alamat2Pemilik",  h.get("ALAMAT2_PEMILIK"));
					this.context.put("alamat3Pemilik",  h.get("ALAMAT3_PEMILIK"));
					this.context.put("poskodPemilik",  h.get("POSKOD_PEMILIK"));
					this.context.put("namaPemaju",  h.get("NAMA_PEMAJU"));
					this.context.put("alamat1Pemaju",  h.get("ALAMAT1_PEMAJU"));
					this.context.put("alamat2Pemaju",  h.get("ALAMAT2_PEMAJU"));
					this.context.put("alamat3Pemaju",  h.get("ALAMAT3_PEMAJU"));
					this.context.put("poskodPemaju",  h.get("POSKOD_PEMAJU"));
					this.context.put("namaSkim",  h.get("NAMA_SKIM"));
					this.context.put("alamat1Skim",  h.get("ALAMAT1_SKIM"));
					this.context.put("alamat2Skim",  h.get("ALAMAT2_SKIM"));
					this.context.put("alamat3Skim",  h.get("ALAMAT3_SKIM"));
					this.context.put("poskodSkim", h.get("POSKOD_SKIM"));
					this.context.put("idTblrujstatusstrata",  h.get("ID_TBLRUJSTATUSSTRATA"));
					this.context.put("tarikhMohonstrata", h.get("TARIKH_MOHONSTRATA"));
					this.context.put("tarikhDaftarstrata",  h.get("TARIKH_DAFTARSTRATA"));
					this.context.put("tarikhSifus", h.get("TARIKH_SIFUS"));
					this.context.put("tarikhCPSP", h.get("TARIKH_CPSP"));
					this.context.put("noRujptg",  h.get("NO_RUJUKANPTG"));
					this.context.put("ulasanStrata",  h.get("ULASAN_STRATA"));
					this.context.put("flagCf",  h.get("FLAG_CF"));
					this.context.put("idLot",  h.get("ID_LOT"));
					this.context.put("idHm",  h.get("ID"));
					this.context.put("idHakmilik",  h.get("ID_HAKMILIK"));
					this.context.put("noHakmilik",  h.get("NO_HAKMILIK"));
					this.context.put("noLot",  h.get("NO_LOT"));
					this.context.put("status",  h.get("STATUS"));
					System.out.println("no cf:::::"+this.context.put("noCf",  h.get("NO_CF")));
					this.context.put("noCf",  h.get("NO_CF"));
					this.context.put("tarikhCf",  h.get("TARIKH_CF"));
					this.context.put("noKelulusankhas",  h.get("NO_KELULUSANKHAS"));
					this.context.put("ulasan",  h.get("ULASAN"));
					//this.context.put("tarikhNoKelulusanKhas",  h.get("TARIKHNOKELULUSANKHAS"));
					this.context.put("idcfs",  h.get("ID_CF"));
					this.context.put("FlagAktif",  h.get("FLAG_AKTIF"));
					this.context.put("flagStrata",  h.get("FLAG_STRATA"));
					this.context.put("txtSenaraiPBT",  h.get("NAMA_PEJABAT"));
					
				}
				
				mode = "view";
				vm = "app/str/frmPaparSemakMaklumatStrata.jsp";
			} else if ("cari".equals(hitButton)) {
				vm = "app/str/frmCarianSemakMaklumatStrata.jsp";
				flagLoadData = true;
			} else if ("kosongkan".equals(hitButton)) {
				System.out.println("masuk siniiii");
				idStrata = "";
				paramNegeri = "";
				paramNoStrata = "";
				paramKodLot = "";
				paramNoLot = "";
				paramNoCF = "";
				paramNamaPemaju = "";
				paramNamaSkim = "";
				this.context.put("paramNegeri", "");
				this.context.put("paramNoStrata", "");
				this.context.put("paramKodLot", "");
				this.context.put("paramNoLot", "");
				this.context.put("paramNoCF", "");
				this.context.put("paramNamaPemaju", "");
				this.context.put("paramNamaSkim", "");

				vm = "app/str/frmCarianMaklumatStrata.jsp";
				flagLoadData = true;
			} else if ("batal".equals(hitButton)) {
				vm = "app/str/frmCarianSemakMaklumatStrata.jsp";
				flagLoadData = true;
			} else {
				vm = "app/str/frmCarianSemakMaklumatStrata.jsp";
				flagLoadData = true;
			}
		} else {
			flagLoadData = true;
		}
System.out.println("flagLoadData"+flagLoadData);
		if (flagLoadData) {
			if("showHistory".equals(submit))
			 {
					String id_hakmilik = getParam("ID_HAKMILIK");
					System.out.println("showHistory id_hakmilik : "+id_hakmilik);			
					this.context.put("listHistoryStata", skimBgnnKhasModel.listHistoryStata(session, id_hakmilik));
					vm = "app/str/frmHistoryStrata.jsp";
				}
			 else
			 {
			vList = skimBgnnKhasModel.carian(paramNegeri, paramKodLot, paramNoLot, paramNoCF, paramNamaPemaju, paramNamaSkim, paramNoStrata);
			context.put("SenaraiFail", vList);
			setupPage(session, action, vList);
			context.put("pagingTitle", "title");

			this.context.put("selectNegeriD",HTML.SelectNegeri("paramNegeri", Utils.parseLong(paramNegeri), ""));
			this.context.put("selectKodLot",HTML.SelectUnitPT("paramKodLot", Utils.parseLong(paramKodLot), "style=width:auto " + mode + " "));
			this.context.put("paramNoLot", paramNoLot);
			this.context.put("paramNoStrata", paramNoStrata);
			
			vm = "app/str/frmCarianSemakMaklumatStrata.jsp";
			 }
		}
		return vm;
	}
	public void setupPage(HttpSession session, String action, Vector list) {
		System.out.println(action);
		System.out.println(list.size());
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
}