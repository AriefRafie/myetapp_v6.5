package ekptg.view.str;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.str.FrmSTRSkimBgnnKhasData;

public class FrmMaklumatStrata extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2108900986561071788L;
	static Logger myLogger = Logger.getLogger(FrmMaklumatStrata.class);
	FrmSTRSkimBgnnKhasData skimBgnnKhasModel = new FrmSTRSkimBgnnKhasData();
	
	@Override
	public String doTemplate2() throws Exception {
		System.out.println ("status:-------------------`1111111111111:");
		HttpSession session = request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String current_role = (String) session.getAttribute("myrole");
		this.context.put("current_role", current_role);
		
		Vector vList = null;
		Vector vCf = new Vector();
		Vector vPemilik = new Vector ();
		Vector vCfTemp = null;

		String moduleName = (String) session.getAttribute("_portal_module");
		String[] mName = moduleName.split("_");
		
		//String idStatus = getParam("idStatus");
		//this.context.put("idStatus", "2");
		System.out.println ("status:-------------------:");

		// context.put("moduleName", mName[1].toLowerCase());

		Boolean postDB = true;
		// String doPost = (String) session.getAttribute("doPost");
		// if (doPost.equals("true")) {
		// postDB = true;
		// }

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String command = getParam("command");
		
		String sub_command = getParam("sub_command");
		System.out.println(" command : "+command);
		String vm = "";
		String RO_General = "";
		String DIS_General = "";
		// String step = getParam("step");
		String hitButton = getParam("hitButton");
		if(!hitButton.equals(""))
		{
			command = "";
		}
		
		System.out.println("hitbutton---------"+hitButton);
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String mode = getParam("mode");
		String selectedTab = (String) getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		// GET ID PARAM
		String idStrata = "";
		
		String paramNoStrata = getParam("paramNoStrata");
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
		
		String listIdStrataDel = getParam("listIdStrataDel");
		String radioCF = getParam("radioCF");
		String radioPemilik = getParam("radioPemilik");
		String idNegeri = getParam("idNegeri");
		//String idDaerah = getParam("idDaerah");
//		String idTblrujstatusstrata = getParam("idTblrujstatusstrata");

		// HITBUTTON
		boolean flagLoadData = false;
		if (postDB) {
			String idcfs = getParam("idcfs");
			String idpemilik = getParam("idpemilik");
			String flagCf = getParam("flagCf");
			String alamatPemilik = getParam ("alamatPemilik");
			 vCfTemp = (Vector)context.get("ListCF");
			if ("tambah".equals(hitButton)) {
				context.put("idcfs", null);
				context.put("pemilik", null);
				vCf.clear();
				context.put("ListCF", vCf);
				setupPage(session, action, vCf);
				
				vPemilik.clear();
				context.put("ListPemilik", vPemilik);
			
				setupPage(session, action, vPemilik);
				
				String idDaerah = getParam("idDaerah");
				

				// Maklumat Pembangunan Strata
				this.context.put("selectNegeriStr", HTML.SelectNegeri("idNegeriStr", Utils.parseLong(""), "",""));
				this.context.put("selectIdLot",HTML.SelectLot("idLot", Utils.parseLong(""), "style=width:auto"));

				// Maklumat Hakmilik Pembangunan Strata
				this.context.put("selectNegeriHM", HTML.SelectNegeri("idNegeri", Utils.parseLong(""), "","onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerah", HTML.SelectDaerah("idDaerah", Utils.parseLong(""), ""));
				this.context.put("selectMukim", HTML.SelectMukim("idBandarpekanmukim", Utils.parseLong(""), ""));
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("idKodjenishakmilik", Utils.parseLong(""), ""));

				// Maklumat Pemaju
				this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", Utils.parseLong(""), "onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarPemaju", HTML.SelectBandar("idKodbandarpemaju", Utils.parseLong(""), ""));
				
				// Maklumat Pemilik
				this.context.put("selectNegeriPemilik", HTML.SelectNegeri("idKodnegeripemilik", Utils.parseLong(""), "onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarPemilik", HTML.SelectBandar("idKodbandarpemilik", Utils.parseLong(""), ""));

				// Maklumat Skim
				this.context.put("selectNegeriSkim", HTML.SelectNegeri("idKodnegeriskim", Utils.parseLong(""), "onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarSkim", HTML.SelectBandar("idKodbandarskim", Utils.parseLong(""), ""));
				
				//ListDokumen(idNegeri,idDaerah);
				
				//reset all value
				this.context.put("idStrata",  "");
				this.context.put("idTblstrhakmilik",  "");
				this.context.put("bilPetak",  "");
				this.context.put("noFailMajlis",  ""); 
				this.context.put("tarikhLulusmajlis",  "");
				this.context.put("noKadPengenalan",  "");
				this.context.put("namaPemilik",  "");
				this.context.put("alamat1Pemilik",  "");
				this.context.put("alamat2Pemilik",  "");
				this.context.put("alamat3Pemilik",  "");
				this.context.put("poskodPemilik",  "");
				this.context.put("namaPemaju",  "");
				this.context.put("alamat1Pemaju",  "");
				this.context.put("alamat2Pemaju",  "");
				this.context.put("alamat3Pemaju",  "");
				this.context.put("poskodPemaju",  "");
				this.context.put("namaSkim",  "");
				this.context.put("alamat1Skim",  "");
				this.context.put("alamat2Skim",  "");
				this.context.put("alamat3Skim",  "");
				this.context.put("poskodSkim", "");
				this.context.put("idTblrujstatusstrata",  "");
				this.context.put("tarikhMohonstrata", "");
				this.context.put("tarikhDaftarstrata",  "");
				this.context.put("noRujptg",  "");
				this.context.put("flagCf",  "");
				this.context.put("ulasanStrata",  "");
				this.context.put("flagStrata",  "T");
				
				this.context.put("idHm",  "");
				this.context.put("idHakmilik",  "");
				this.context.put("noHakmilik",  "");
				this.context.put("noLot",  "");
				this.context.put("status",  "");
				this.context.put("tarikhSifus",  "");
				this.context.put("tarikhCPSP",  "");
				
				
				mode = "new";
				vm = "app/str/frmTambahMaklumatStrata.jsp";
			} else if ("papar".equals(hitButton)) {
				
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
				vm = "app/str/frmPaparMaklumatStrata.jsp";
			} 
		
			else if ("kemaskini".equals(hitButton) || "simpan".equals(hitButton)){
				
				if ("simpan".equals(hitButton)) {
					System.out.println("masuk sini");
					vPemilik = skimBgnnKhasModel.getDataPemilik(idStrata);
					Vector vPemilikTemp = (Vector)context.get("ListPemilik");
					idStrata = skimBgnnKhasModel.simpan(getParam("idNegeriStr"), getParam("idLot"), getParam("noLot"), getParam("bilPetak"),
							getParam("noFailMajlis"), getParam("tarikhLulusmajlis"), getParam("idNegeri"), getParam("idDaerah"), getParam("idBandarpekanmukim"),
							getParam("idKodjenishakmilik"), getParam("noHakmilik"), getParam("namaPemaju"), getParam("alamat1Pemaju"),
							getParam("alamat2Pemaju"), getParam("alamat3Pemaju"), getParam("poskodPemaju"),
							getParam("idKodnegeripemaju"), getParam("idKodbandarpemaju"), getParam("namaSkim"),
							getParam("alamat1Skim"), getParam("alamat2Skim"), getParam("alamat3Skim"), getParam("poskodSkim"),
							getParam("idKodnegeriskim"), getParam("idKodbandarskim"), getParam("idTblrujstatusstrata"),
							getParam("tarikhDaftarstrata"), getParam("tarikhMohonstrata"), getParam("noRujptg"),getParam("ulasanStrata"),getParam("flagCf"),getParam("flagStrata"),getParam("tarikhSifus"),getParam("tarikhCPSP"),getParam("txtSenaraiPBT"),session);

					AuditTrail.logActivity(this, session, "INS", "PEMAJU ["+getParam("noLot")+" "+getParam("namaPemaju")+" "+getParam("noHakmilik")+"] Added");
					vm = "app/str/frmPaparMaklumatStrata.jsp";
					//System.out.println("vm 1"+vm);
					//flagLoadData = true;
				} 
				else
				{
					idStrata = getParam("idStrata");
				}
				System.out.println("*** idStrata :"+idStrata);
				//kena set terus semua id table supaya tidak insert new row
				Vector data = new Vector();
				vCf.clear();
				vPemilik.clear();

				//get data from db to display on jsp
				data = skimBgnnKhasModel.getData(idStrata);
				
				
				vPemilik = skimBgnnKhasModel.getDataPemilik(idStrata);
				
				context.put("ListPemilik", vPemilik);
				//context.put("ListPemilik", "Tidur3");
				setupPage(session, action, vPemilik);
				
				if(data.size()!=0){
					Hashtable h = (Hashtable)data.get(0);
					
					this.context.put("selectNegeriStr", HTML.SelectNegeri("idNegeriStr", Utils.parseLong(h.get("ID_NEGERI_STR")+""), "","onChange=\"doChangeNegeri();\""));
					this.context.put("selectIdLot",HTML.SelectLot("idLot", Utils.parseLong(h.get("ID_LOT")+""), "style=width:auto " + mode + " "));
					// Maklumat Hakmilik Pembangunan Strata
					String idDaerah = h.get("ID_DAERAH")+"";
					if (idDaerah == null || idDaerah.trim().length() == 0){
						idDaerah = "99999";
					}
					String idBandarpekanmukim = h.get("ID_BANDARPEKANMUKIM")+"";
					if (idBandarpekanmukim == null || idBandarpekanmukim.trim().length() == 0){
						idBandarpekanmukim = "99999";
					}
					String idKodjenishakmilik = h.get("ID_KODJENISHAKMILIK")+"";
					if (idKodjenishakmilik == null || idKodjenishakmilik.trim().length() == 0){
						idKodjenishakmilik = "99999";
					}
					this.context.put("selectNegeriHM", HTML.SelectNegeri("idNegeri", Utils.parseLong(h.get("ID_NEGERI")+""), "","onChange=\"doChangeNegeri();\""));
					this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(h.get("ID_NEGERI")+"", "idDaerah", Long.parseLong(idDaerah), "", "onChange=\"doChangeDaerah();\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "idBandarpekanmukim", idBandarpekanmukim == null ? null : Utils.parseLong(idBandarpekanmukim), "", ""));
					this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("idKodjenishakmilik", Utils.parseLong(idKodjenishakmilik), ""));

					
					// Maklumat Pemilik
					this.context.put("selectNegeriPemilik", HTML.SelectNegeri("idKodnegeripemilik", Utils.parseLong(""), "onChange=\"doChangeNegeri();\""));
					this.context.put("selectBandarPemilik", HTML.SelectBandar("idKodbandarpemilik", Utils.parseLong(""), ""));
					
					// Maklumat Pemaju
//					this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", Utils.parseLong(h.get("ID_KODNEGERIPEMAJU")+""), "","onChange=\"doChangeNegeri();\""));
//					this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri(h.get("ID_KODNEGERIPEMAJU")+"", "idKodbandarpemaju", Long.parseLong(h.get("ID_KODBANDARPEMAJU")+""), ""));
//					
					// Maklumat Pemaju
					this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", h.get("ID_KODNEGERIPEMAJU") == null ? 0 : Utils.parseLong(h.get("ID_KODNEGERIPEMAJU")+""), "","onChange=\"doChangeNegeri();\""));
					this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri( h.get("ID_KODNEGERIPEMAJU") == null ? "0" : h.get("ID_KODNEGERIPEMAJU")+"", "idKodbandarpemaju",  h.get("ID_KODBANDARPEMAJU") == null ? 0 : Utils.parseLong(h.get("ID_KODBANDARPEMAJU")+""), ""));

					
					// Maklumat Skim
					this.context.put("selectNegeriSkim", HTML.SelectNegeri("idKodnegeriskim", Utils.parseLong(h.get("ID_KODNEGERISKIM")+""), "","onChange=\"doChangeNegeri();\""));
					this.context.put("selectBandarSkim",HTML.SelectBandarByNegeri(h.get("ID_KODNEGERISKIM")+"", "idKodbandarskim", h.get("ID_KODBANDARSKIM") == null ? null : Utils.parseLong(h.get("ID_KODBANDARSKIM")+""), ""));
					this.context.put("idStrata",  h.get("ID_STRATA"));
					this.context.put("idTblstrhakmilik",  h.get("ID_TBLSTRHAKMILIK"));
					this.context.put("bilPetak",  h.get("BIL_PETAK"));
					this.context.put("noFailMajlis",  h.get("NO_FAILMAJLIS")); 
					this.context.put("tarikhLulusmajlis",  h.get("TARIKH_LULUSMAJLIS"));
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
					System.out.println("---------6-------------");
					
					this.context.put("idHm",  h.get("ID"));
					this.context.put("idHakmilik",  h.get("ID_HAKMILIK"));
					this.context.put("noHakmilik",  h.get("NO_HAKMILIK"));
					this.context.put("ulasan",  h.get("ULASAN"));
					this.context.put("noLot",  h.get("NO_LOT"));
					this.context.put("status",  h.get("STATUS"));
					this.context.put("flagCf",  h.get("FLAG_CF"));
					this.context.put("radioCF",  h.get("FLAG_CF"));
					this.context.put("flagStrata",  h.get("FLAG_STRATA"));
					this.context.put("txtSenaraiPBT",  h.get("ID_PEJABAT"));
					ListDokumen((String)h.get("ID_NEGERI"),(String)h.get("ID_DAERAH"));
				}
				
				mode = "kemaskini";
				vm = "app/str/frmTambahMaklumatStrata.jsp";
				//flagLoadData = true;
			} else if ("kemaskiniSimpan".equals(hitButton)){
				myLogger.info("kemaskiniSimpan idTblrujstatusstrata ::: "+getParam("idTblrujstatusstrata"));
				mode = "new";
				System.out.println("id cf::::::"+getParam("idcfs"));
				System.out.println("id cf::::::"+getParam("idcfs").length());
				if (getParam("idcfs") == null)
				{ System.out.println("bella");
				
				}
				 vCfTemp = (Vector)context.get("ListCF");
				 Vector vPemilikTemp = (Vector)context.get("ListPemilik");
				 System.out.println("ulasan tiada strata :::::"+getParam("ulasanStrata"));
				skimBgnnKhasModel.saveUpdate(getParam("idNegeriStr"), getParam("idLot"), getParam("noLot"), getParam("bilPetak"),
						getParam("noFailMajlis"), getParam("tarikhLulusmajlis"), getParam("idNegeri"), getParam("idDaerah"), getParam("idBandarpekanmukim"),
						getParam("idKodjenishakmilik"), getParam("noHakmilik"), getParam("namaPemaju"), getParam("alamat1Pemaju"),
						getParam("alamat2Pemaju"), getParam("alamat3Pemaju"), getParam("poskodPemaju"),
						getParam("idKodnegeripemaju"), getParam("idKodbandarpemaju"), getParam("namaSkim"),
						getParam("alamat1Skim"), getParam("alamat2Skim"), getParam("alamat3Skim"), getParam("poskodSkim"),
						getParam("idKodnegeriskim"), getParam("idKodbandarskim"), getParam("idTblrujstatusstrata"),
						getParam("tarikhDaftarstrata"), getParam("tarikhMohonstrata"), getParam("noRujptg"),getParam("idStrata"),
						getParam("idHm"),getParam("flagCf"),getParam("idcfs"),getParam("ulasanStrata"),getParam("idpemilik"),getParam("radioCF"),
						getParam("noCf"),getParam("tarikhCf"),getParam("noKelulusankhas"),getParam("ulasan"),getParam("flagStrata"),getParam("tarikhSifus"),getParam("tarikhCPSP"),getParam("txtSenaraiPBT"),vPemilikTemp,session);
			
				
				AuditTrail.logActivity(this, session, "UPD", "PEMAJU ["+getParam("noLot")+" "+getParam("namaPemaju")+" "+getParam("noHakmilik")+"] Updated");
				//load blk data with latest
				Vector data = new Vector();
				idStrata = getParam("idStrata");
				data = skimBgnnKhasModel.getData(idStrata);
				
				vCf = skimBgnnKhasModel.getDataCF(idStrata);
				//vCf = skimBgnnKhasModel.getDataCF(idStrata);
				
				vPemilik = skimBgnnKhasModel.getDataPemilik(idStrata);
//				//vCf = skimBgnnKhasModel.getDataCF(idStrata);
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
					
					this.context.put("idStrata",  h.get("ID_STRATA"));
					this.context.put("idTblstrhakmilik",  h.get("ID_TBLSTRHAKMILIK"));
					this.context.put("bilPetak",  h.get("BIL_PETAK"));
					this.context.put("noFailMajlis",  h.get("NO_FAILMAJLIS")); 
					this.context.put("tarikhLulusmajlis",  h.get("TARIKH_LULUSMAJLIS")); 
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
					this.context.put("noCf",  h.get("NO_CF"));
					this.context.put("tarikhCf",  h.get("TARIKH_CF"));
					this.context.put("noKelulusankhas",  h.get("NO_KELULUSANKHAS"));
					this.context.put("ulasan",  h.get("ULASAN"));
					//this.context.put("tarikhNoKelulusanKhas",  h.get("TARIKHNOKELULUSANKHAS"));
					//System.out.println("id cfs----"+this.context.put("tarikhNoKelulusanKhas",  h.get("TARIKHNOKELULUSANKHAS")));
					this.context.put("idcfs",  h.get("ID_CF"));
					this.context.put("radioCF",  h.get("FLAG_CF"));
					
					this.context.put("FlagAktif",  h.get("FLAG_AKTIF"));
					
					this.context.put("idHm",  h.get("ID"));
					this.context.put("idHakmilik",  h.get("ID_HAKMILIK"));
					this.context.put("noHakmilik",  h.get("NO_HAKMILIK"));
					this.context.put("noLot",  h.get("NO_LOT"));
					this.context.put("status",  h.get("STATUS"));
					this.context.put("flagStrata",  h.get("FLAG_STRATA"));
					this.context.put("txtSenaraiPBT",  h.get("NAMA_PEJABAT"));
					
					
					System.out.println("sop id hakmilik--"+this.context.put("idHm",  h.get("ID")));
				}
				
				mode = "view";
				vm = "app/str/frmPaparMaklumatStrata.jsp";
			} else if ("hapus".equals(hitButton)) {
				Vector data = new Vector();

				skimBgnnKhasModel.deleteBgnnKhas(listIdStrataDel, this, session);
				// vm = "app/str/frmCarianMaklumatStrata.jsp";
				flagLoadData = true;
			} else if ("cari".equals(hitButton)) {
				vm = "app/str/frmCarianMaklumatStrata.jsp";
				flagLoadData = true;
			} 
			
			else if ("kosongkan".equals(hitButton)) {
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
			}else if("batal".equals(hitButton)){
				vm = "app/str/frmCarianMaklumatStrata.jsp";
				flagLoadData = true;
			}
			else if("copyAlamatPemilik".equals(hitButton) || "doAddPemilik".equals(hitButton) || "doDeletePemilik".equals(hitButton)){
			//if idStrata ada value = kes update. if null new insert.
			idStrata = getParam("idStrata"); 
			String idPemilik = getParam("idPemilik");
			String flagPemilik = getParam("flagPemilik");
			System.out.println(getParam("flagCf"));
			
			//Vector vPemilikTemp = (Vector)context.get("ListPemilik");
			if("doAddPemilik".equals(hitButton)){
				System.out.println("masuk tambah pemilik---");
				//ada value => kemasikini
				if(!idStrata.isEmpty()){
					System.out.println("masukkk ada ---");
						Hashtable h = new Hashtable();
						
						h.put("idStrata", idStrata);
						h.put("radioPemilik", getParam("radioPemilik"));
						h.put("noKadPengenalan", getParam("noKadPengenalan"));
						h.put("namaPemilikTanah", getParam("namaPemilikTanah"));
						h.put("alamat1Pemilik", getParam("alamat1Pemilik"));
						h.put("alamat2Pemilik", getParam("alamat2Pemilik"));
						h.put("alamat3Pemilik", getParam("alamat3Pemilik"));
						h.put("poskodPemilik", getParam("poskodPemilik"));
						h.put("idKodnegeriPemilik", getParam("idKodnegeripemilik"));
						h.put("idKodbandarPemilik", getParam("idKodbandarpemilik"));
						System.out.println("kod negeri" +getParam("idKodnegeripemilik"));
						System.out.println("kod bandar" +getParam("idKodbandarpemilik"));
						vPemilik.addElement(h);
						//skimBgnnKhasModel.SimpanPemilik(idStrata,vPemilikTemp,session);
						skimBgnnKhasModel.SimpanPemilik(h);
						AuditTrail.logActivity(this, session, "INS", "PEMILIK TANAH ["+getParam("namaPemilikTanah")+" "+getParam("noKadPengenalan")+"] Updated");
					}
				else{//kemasukan baru
					
					System.out.println("masukkk ada  xxxxx---");
						Hashtable h = new Hashtable();
						h.put("radioPemilik", getParam("radioPemilik"));
						h.put("noKadPengenalan", getParam("noKadPengenalan"));
						h.put("namaPemilikTanah", getParam("namaPemilikTanah"));
						h.put("alamat1Pemilik", getParam("alamat1Pemilik"));
						h.put("alamat2Pemilik", getParam("alamat2Pemilik"));
						h.put("alamat3Pemilik", getParam("alamat3Pemilik"));
						h.put("poskodPemilik", getParam("poskodPemilik"));
						h.put("idKodnegeriPemilik", getParam("idKodnegeriPemilik"));
						h.put("idKodbandarPemilik", getParam("idKodbandarPemilik"));
						vPemilik.addElement(h);
					}
			
				context.put("radioPemilik", "");
				context.put("idPemilik", "");
				context.put("noKadPengenalan", "");
				context.put("namaPemilikTanah", "");
				context.put("alamat1Pemilik", "");
				context.put("alamat2Pemilik", "");
				context.put("alamat3Pemilik", "");
				context.put("poskodPemilik", "");
				context.put("idKodnegeriPemilik", "");
				context.put("idKodbandarPemilik", "");
				
			}
			else if("doDeletePemilik".equals(hitButton)){
				//ada value => kemasikini
				String cntStr = getParam("cnt");
				int cnt = 0;	
			System.out.println("idPemilik+++++:"+idPemilik);
			skimBgnnKhasModel.deletePemilik(idPemilik,this,session);
			}
				
			//list selecteed id to removed
			context.put("idcfs", idcfs);
			context.put("ListCF", vCfTemp);
			setupPage(session, action, vCfTemp);
			
//		//list selecteed id to removed
		context.put("idpemilik", idpemilik);
		vPemilik = skimBgnnKhasModel.getDataPemilik(idStrata);
		context.put("ListPemilik", vPemilik);
		
		setupPage(session, action, vPemilik);

		// Maklumat Pembangunan Strata
		this.context.put("selectNegeriStr", HTML.SelectNegeri("idNegeriStr", Utils.parseLong(getParam("idNegeriStr")), "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectIdLot",HTML.SelectLot("idLot", Utils.parseLong(getParam("idLot")), "style=width:auto " + mode + " "));
		// this.context.put("noLot", "");

		// Maklumat Hakmilik Pembangunan Strata
		String idDaerah = getParam("idDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}

		String idBandarpekanmukim = getParam("idBandarpekanmukim");
		if (idBandarpekanmukim == null || idBandarpekanmukim.trim().length() == 0){
			idBandarpekanmukim = "99999";
		}

		String idKodjenishakmilik = getParam("idKodjenishakmilik");
		if (idKodjenishakmilik == null || idKodjenishakmilik.trim().length() == 0){
			idKodjenishakmilik = "99999";
		}

		this.context.put("selectNegeriHM", HTML.SelectNegeri("idNegeri", Utils.parseLong(getParam("idNegeri")), "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(getParam("idNegeri"), "idDaerah", Long.parseLong(idDaerah), "", "onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "idBandarpekanmukim", idBandarpekanmukim == null ? null : Utils.parseLong(idBandarpekanmukim), "", ""));
		this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("idKodjenishakmilik", Utils.parseLong(idKodjenishakmilik), ""));

		// Maklumat Pemaju
		String idKodbandarpemaju = getParam("idKodbandarpemaju");
		if (idKodbandarpemaju == null || idKodbandarpemaju.trim().length() == 0){
			idKodbandarpemaju = "99999";
		}
		this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", null, "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri(getParam("idKodnegeripemaju"), "idKodbandarpemaju", Long.parseLong(idKodbandarpemaju), ""));

		// Maklumat Pemaju
		idKodbandarpemaju = getParam("idKodbandarpemaju");
		if (idKodbandarpemaju == null || idKodbandarpemaju.trim().length() == 0){
			idKodbandarpemaju = "99999";
		}
		this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", Utils.parseLong(getParam("idKodnegeripemaju")), "","onChange=\"doChangeNegeri();\""));
		this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri(getParam("idKodnegeripemaju"), "idKodbandarpemaju", Long.parseLong(idKodbandarpemaju), ""));
	
		if("doAddPemilik".equals(hitButton)){
			
			System.out.println("xxxxx");
			this.context.put("selectNegeriPemilik", HTML.SelectNegeri("idKodnegeripemilik", null, "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPemilik",HTML.SelectBandarByNegeri(getParam("idKodnegeripemilik"), "idKodbandarpemilik", null, ""));

		}	
		
		// Maklumat Skim
		String idKodbandarskim = getParam("idKodbandarskim");
		if (idKodbandarskim == null || idKodbandarskim.trim().length() == 0){
			idKodbandarskim = "99999";
		}
		this.context.put("selectNegeriSkim", HTML.SelectNegeri("idKodnegeriskim", Utils.parseLong(getParam("idKodnegeriskim")), "onChange=\"doChangeNegeri();\""));
		this.context.put("selectBandarSkim",HTML.SelectBandarByNegeri(getParam("idKodnegeriskim"), "idKodbandarskim", Long.parseLong(idKodbandarskim), ""));

		this.context.put("noLot", getParam("noLot"));
		this.context.put("bilPetak", getParam("bilPetak"));
		this.context.put("noFailMajlis", getParam("noFailMajlis"));
		this.context.put("tarikhLulusmajlis", getParam("tarikhLulusmajlis"));
		this.context.put("noCf", getParam("noCf"));
		this.context.put("tarikhCf", getParam("tarikhCf"));
		this.context.put("noKelulusankhas", getParam("noKelulusankhas"));
		this.context.put("noHakmilik", getParam("noHakmilik"));
		this.context.put("namaPemaju", getParam("namaPemaju"));
		this.context.put("alamat1Pemaju", getParam("alamat1Pemaju"));
		this.context.put("alamat2Pemaju", getParam("alamat2Pemaju"));
		this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
		this.context.put("poskodPemaju", getParam("poskodPemaju"));
		this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
		this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
		this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
		this.context.put("namaSkim", getParam("namaSkim"));
		this.context.put("alamat1Skim", getParam("alamat1Skim"));
		this.context.put("alamat2Skim", getParam("alamat2Skim"));
		this.context.put("alamat3Skim", getParam("alamat3Skim"));
		this.context.put("poskodSkim", getParam("poskodSkim"));
		this.context.put("idTblrujstatusstrata", getParam("idTblrujstatusstrata"));
		this.context.put("tarikhDaftarstrata", getParam("tarikhDaftarstrata"));
		this.context.put("noRujptg", getParam("noRujptg"));
		this.context.put("flagCf", getParam("flagCf"));

		this.context.put("idStrata", getParam("idStrata"));
		this.context.put("flagStrata", getParam("flagStrata"));
		this.context.put("txtSenaraiPBT", getParam("txtSenaraiPBT"));
		
		
		if("copyAlamatPemilik".equals(hitButton))
		{
			Hashtable copyAlamatPemilik = skimBgnnKhasModel.getAlamatPemilikSebelum(idStrata);
			
			Hashtable h = new Hashtable();
			
			h.put("idStrata", idStrata);
			h.put("radioPemilik", getParam("radioPemilik"));
			h.put("noKadPengenalan", getParam("noKadPengenalan"));
			h.put("namaPemilikTanah", getParam("namaPemilikTanah"));
			
			if(copyAlamatPemilik!=null)
			{
				System.out.println("masuk sini 1");
				System.out.println("jenis pengenalan: "+getParam("radioPemilik"));
				System.out.println("nokadpengenalanpemilik: "+getParam("noKadPengenalan"));
				System.out.println("namapemilik: "+getParam("namaPemilikTanah"));
				context.put("radioPemilik", getParam("radioPemilik"));
				context.put("noKadPengenalan", getParam("noKadPengenalan"));
				context.put("namaPemilikTanah", getParam("namaPemilikTanah"));
				context.put("alamat1Pemilik", (String)copyAlamatPemilik.get("ALAMAT1_PEMILIK"));
				context.put("alamat2Pemilik", (String)copyAlamatPemilik.get("ALAMAT2_PEMILIK"));
				context.put("alamat3Pemilik", (String)copyAlamatPemilik.get("ALAMAT3_PEMILIK"));
				context.put("poskodPemilik", (String)copyAlamatPemilik.get("POSKOD_PEMILIK"));
				this.context.put("selectNegeriPemilik", HTML.SelectNegeri("idKodnegeripemilik", Utils.parseLong((String)copyAlamatPemilik.get("ID_KODNEGERIPEMILIK")), "","onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarPemilik",HTML.SelectBandarByNegeri((String)copyAlamatPemilik.get("ID_KODNEGERIPEMILIK"), "idKodbandarpemilik", Utils.parseLong((String)copyAlamatPemilik.get("ID_KODBANDARPEMILIK")), ""));
				
				
				// Maklumat Pemaju
				idKodbandarpemaju = getParam("idKodbandarpemaju");
				if (idKodbandarpemaju == null || idKodbandarpemaju.trim().length() == 0){
					idKodbandarpemaju = "99999";
				}
				this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", Utils.parseLong(getParam("idKodnegeripemaju")), "","onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri(getParam("idKodnegeripemaju"), "idKodbandarpemaju", Long.parseLong(idKodbandarpemaju), ""));
			}

		}
		
		

		flagLoadData = false;
		mode = getParam("mode");
		System.out.println("mode"+mode);
		vm = "app/str/frmTambahMaklumatStrata.jsp";
			}
			else {
				vm = "app/str/frmCarianMaklumatStrata.jsp";
				flagLoadData = true;
			}
		} else {
			flagLoadData = true;
		}
		//filter dropdown list
		if("doFilter".equals(command)){
			
			//System.out.println("doFilter");
			vCfTemp = (Vector)context.get("ListCF");
			Vector vPemilikTemp = (Vector)context.get("ListPemilik");
			
			//list selecteed id to removed
			context.put("idcfs", getParam("idcfs"));
			context.put("ListCF", vCfTemp);
			setupPage(session, action, vCfTemp);
			
			context.put("idPemilik", getParam("idPemilik"));
			context.put("ListPemilik", vPemilikTemp);

			setupPage(session, action, vPemilikTemp);
			
			// Maklumat Pembangunan Strata
			//this.context.put("selectNegeriStr", HTML.SelectNegeri("idNegeriStr", Utils.parseLong(getParam("idNegeriStr")), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectIdLot",HTML.SelectLot("idLot", Utils.parseLong(getParam("idLot")), "style=width:auto " + mode + " "));
			// this.context.put("noLot", "");

			// Maklumat Hakmilik Pembangunan Strata
			String idDaerah = getParam("idDaerah");
			if (idDaerah == null || idDaerah.trim().length() == 0){
				idDaerah = "99999";
			}
			
			String idBandarpekanmukim = getParam("idBandarpekanmukim");
			if (idBandarpekanmukim == null || idBandarpekanmukim.trim().length() == 0){
				idBandarpekanmukim = "99999";
			}
			
			String idKodjenishakmilik = getParam("idKodjenishakmilik");
			if (idKodjenishakmilik == null || idKodjenishakmilik.trim().length() == 0){
				idKodjenishakmilik = "99999";
			}
			
			this.context.put("selectNegeriHM", HTML.SelectNegeri("idNegeri", Utils.parseLong(getParam("idNegeri")), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(getParam("idNegeri"), "idDaerah", Long.parseLong(idDaerah), "", "onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "idBandarpekanmukim", idBandarpekanmukim == null ? null : Utils.parseLong(idBandarpekanmukim), "", ""));
			this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("idKodjenishakmilik", Utils.parseLong(idKodjenishakmilik), ""));

			// Maklumat Pemaju
			String idKodbandarpemaju = getParam("idKodbandarpemaju");
			if (idKodbandarpemaju == null || idKodbandarpemaju.trim().length() == 0){
				idKodbandarpemaju = "99999";
			}
			this.context.put("selectNegeriPemaju", HTML.SelectNegeri("idKodnegeripemaju", Utils.parseLong(getParam("idKodnegeripemaju")), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPemaju",HTML.SelectBandarByNegeri(getParam("idKodnegeripemaju"), "idKodbandarpemaju", Long.parseLong(idKodbandarpemaju), ""));
			
			// Maklumat Pemilik
			String idKodbandarpemilik = getParam("idKodbandarpemilik");
			if (idKodbandarpemilik == null || idKodbandarpemilik.trim().length() == 0){
			idKodbandarpemilik = "99999";
			}
			this.context.put("selectNegeriPemilik", HTML.SelectNegeri("idKodnegeripemilik", Utils.parseLong(getParam("idKodnegeripemilik")), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPemilik",HTML.SelectBandarByNegeri(getParam("idKodnegeripemilik"), "idKodbandarpemilik", Long.parseLong(idKodbandarpemilik), ""));

			
			// Maklumat Skim
			String idKodbandarskim = getParam("idKodbandarskim");
			if (idKodbandarskim == null || idKodbandarskim.trim().length() == 0){
				idKodbandarskim = "99999";
			}
			this.context.put("selectNegeriSkim", HTML.SelectNegeri("idKodnegeriskim", Utils.parseLong(getParam("idKodnegeriskim")), "onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarSkim",HTML.SelectBandarByNegeri(getParam("idKodnegeriskim"), "idKodbandarskim", Long.parseLong(idKodbandarskim), ""));
			
			
			ListDokumen(idNegeri,idDaerah);
			this.context.put("noLot", getParam("noLot"));
			this.context.put("bilPetak", getParam("bilPetak"));
			this.context.put("noFailMajlis", getParam("noFailMajlis"));
			this.context.put("tarikhLulusmajlis", getParam("tarikhLulusmajlis"));
			this.context.put("noCf", getParam("noCf"));
			this.context.put("tarikhCf", getParam("tarikhCf"));
			this.context.put("noKelulusankhas", getParam("noKelulusankhas"));
			this.context.put("ulasan", getParam("ulasan"));
			this.context.put("noHakmilik", getParam("noHakmilik"));
			this.context.put("radioPemilik", getParam("radioPemilik"));
			this.context.put("noKadPengenalan", getParam("noKadPengenalan"));
			this.context.put("namaPemilikTanah", getParam("namaPemilikTanah"));
			this.context.put("alamat1Pemilik", getParam("alamat1Pemilik"));
			this.context.put("alamat2Pemilik", getParam("alamat2Pemilik"));
			this.context.put("alamat3Pemilik", getParam("alamat3Pemilik"));
			this.context.put("poskodPemilik", getParam("poskodPemilik"));
			this.context.put("namaPemaju", getParam("namaPemaju"));
			this.context.put("alamat1Pemaju", getParam("alamat1Pemaju"));
			this.context.put("alamat2Pemaju", getParam("alamat2Pemaju"));
			this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
			this.context.put("poskodPemaju", getParam("poskodPemaju"));
			this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
			this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
			this.context.put("alamat3Pemaju", getParam("alamat3Pemaju"));
			this.context.put("namaSkim", getParam("namaSkim"));
			this.context.put("alamat1Skim", getParam("alamat1Skim"));
			this.context.put("alamat2Skim", getParam("alamat2Skim"));
			this.context.put("alamat3Skim", getParam("alamat3Skim"));
			this.context.put("poskodSkim", getParam("poskodSkim"));
			this.context.put("idTblrujstatusstrata", getParam("idTblrujstatusstrata"));
			this.context.put("tarikhDaftarstrata", getParam("tarikhDaftarstrata"));
			this.context.put("radioCF", getParam("radioCF"));
			this.context.put("noRujptg", getParam("noRujptg"));
			this.context.put("flagCf", getParam("flagCf"));
			this.context.put("flagPemilik", getParam("flagPemilik"));
			this.context.put("mode", getParam("mode"));
			mode = getParam("mode");
			this.context.put("idStrata", getParam("idStrata"));
			this.context.put("tarikhDaftarstrata", getParam("tarikhDaftarstrata"));
			this.context.put("tarikhMohonstrata", getParam("tarikhMohonstrata"));
			this.context.put("ulasanStrata", getParam("ulasanStrata"));
			this.context.put("noRujptg", getParam("noRujptg"));
			this.context.put("flagStrata", getParam("flagStrata"));
			this.context.put("tarikhSifus", getParam("tarikhSifus"));
			this.context.put("tarikhCPSP", getParam("tarikhCPSP"));
			this.context.put("txtSenaraiPBT",  getParam("txtSenaraiPBT"));
			
			
			
			flagLoadData = false;
			//mode = "new";
			vm = "app/str/frmTambahMaklumatStrata.jsp";
		}
		///bella
		
		 if("showHistory".equals(command))
		 {
			 System.out.println("3333333");	
			 String id_hakmilik = getParam("ID_HAKMILIK");
				System.out.println("showHistory id_hakmilik : "+id_hakmilik);			
				this.context.put("listHistoryStata", skimBgnnKhasModel.listHistoryStata(session, id_hakmilik));
				
				System.out.println("4444444");
				vm = "app/str/frmHistoryStrata.jsp";
				flagLoadData = false;
			}
		
		System.out.println("showHistory id_hakmilik  command: "+command+"  flagLoadData :"+flagLoadData);
		
		if ((flagLoadData || ("doCarian".equals(command)) || ("doChanges".equals(command)))) {
			vList = skimBgnnKhasModel.carian(paramNegeri, paramKodLot, paramNoLot, paramNoCF, paramNamaPemaju, paramNamaSkim,paramNoStrata);
			context.put("SenaraiFail", vList);
			setupPage(session, action, vList);
			context.put("pagingTitle", "title");

			this.context.put("selectNegeriD",HTML.SelectNegeri("paramNegeri", Utils.parseLong(paramNegeri), ""));
			this.context.put("selectKodLot",HTML.SelectLot("paramKodLot", Utils.parseLong(paramKodLot), "style=width:auto " + mode + " "));
			this.context.put("paramNoLot", paramNoLot);
			
			vm = "app/str/frmCarianMaklumatStrata.jsp";
			 }

		this.context.put("mode", mode);
		this.context.put("RO_General", RO_General);
		this.context.put("DIS_General", DIS_General);
		this.context.put("radioCF", radioCF);
//		this.context.put("idTblrujstatusstrata", idTblrujstatusstrata);
		System.out.println(" vm : "+vm);
		return vm;
	}	
	
	public void setupPage(HttpSession session, String action, Vector list) {
		System.out.println(action);
		//System.out.println(list.size());
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
	
	private void ListDokumen(String idNegeri, String idDaerah) throws Exception {

		FrmSTRSkimBgnnKhasData skimBgnnKhasModel = new FrmSTRSkimBgnnKhasData();
		Vector listDokumen = new Vector();
		listDokumen.clear();
		
		Vector senaraiPBT = new Vector();
		System.out.println("++++++id negeri++++"+idNegeri);
		System.out.println("++++++id daerah++++"+idDaerah);
		senaraiPBT = skimBgnnKhasModel.dropdown_senaraipbt(idNegeri,idDaerah);
		this.context.put("senaraiPBT", senaraiPBT);

	}// close ListDokumen
}
	