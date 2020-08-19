package ekptg.view.php2.online;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.online.FrmPYWOnlineSenaraiFailData;

public class FrmStatusPermohonanSewa extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		FrmPYWOnlineSenaraiFailData logic = new FrmPYWOnlineSenaraiFailData();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String vm = "";
		
		String command = getParam("command");
		System.out.print(" command :"+command);
		
		String flag_penyewaan = getParam("flag_penyewaan");
		this.context.put("flag_penyewaan", flag_penyewaan);
		System.out.print(" ****** flag_penyewaan :"+flag_penyewaan);

		Vector status_Penyewaan = new Vector();
		Vector status_pembayaranSewa = new Vector();
		Vector status_PermohonanSewa = new Vector();
		
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idFail = getParam("idFail");
		String actionPenyewaan = getParam("actionPenyewaan");
		String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String idPermohonanSewa = getParam("idPermohonanSewa");
		String findNoFail = getParam("findNoFail");
		String findNoHakmilik = getParam("findNoHakmilik");
		String findNoLot = getParam("findNoLot");
        		
		Vector list = null;
		
		// GO TO LIST FAIL AISHAH PHP
			
		if(flag_penyewaan.equals("PermohonanSewa"))
		{
			status_PermohonanSewa = logic.statusPermohonanSewa(findNoFail, findNoHakmilik,"", findNoLot, id_user);
			this.context.put("status_PermohonanSewa", status_PermohonanSewa);
			System.out.println(" status_PermohonanSewa :"+status_PermohonanSewa);
		}
					
		this.context.put("no_fail", "");	
		this.context.put("no_fail_negeri", "");
		this.context.put("nama", "");
		this.context.put("tarikh_terima", "");
		this.context.put("keterangan", "");
					
		this.context.put("no_fail", "");	
		this.context.put("nama_urusan", "");
		this.context.put("nama_suburusan", "");
		this.context.put("tajuk_fail", "");
		this.context.put("tujuan", "");
		this.context.put("no_rujukan", "");
		this.context.put("tarikh_mula", "");
		this.context.put("tarikh_tamat", "");
		this.context.put("debit", "");
		this.context.put("kredit", "");
		
		this.context.put("idFail", "");	
		this.context.put("idPermohonan", "");
		this.context.put("noFail", "");
		this.context.put("noFailNegeri", "");
		this.context.put("tarikhTerima", "");
		this.context.put("namaPemohon", "");
		this.context.put("idStatus", "");
		this.context.put("status", "");
		this.context.put("noSambungan", "");
		this.context.put("flagBuka", "");
		this.context.put("flagMT", "");
		this.context.put("flagPindaan", "");
		
		this.context.put("noFail", "");	
		this.context.put("urusan", "");
		this.context.put("subUrusan", "");
		this.context.put("tarikhSurat", "");
		this.context.put("tarikhTerima", "");
		this.context.put("noRujSurat", "");
		this.context.put("tajukFail", "");
		this.context.put("status", "");
		this.context.put("namaPemohon", "");
		this.context.put("noPengenalan", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		
		this.context.put("alamat3", "");	
		this.context.put("poskod", "");
		this.context.put("bandar", "");
		this.context.put("negeri", "");
		this.context.put("noTel", "");
		this.context.put("noFax", "");
		this.context.put("tarikhBatal", "");
		this.context.put("catatanBatal", "");

		// DROP DOWN CARIAN
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0) {
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0) {
			jenisLot = "99999";
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

		String flagDetail = getParam("flagDetail");

		// GO TO LIST FAIL PENYEWAAN

		list = new Vector();
		logic.carianFail(getParam("txtNoPermohonanC"),
				getParam("txdTarikhMohonC"), idNegeriC, idDaerahC,
				idMukimC, jenisHakmilik, getParam("txtNoHakmilik"),
				getParam("txtNoWarta"), jenisLot, getParam("txtNoLot"),
				getParam("txtNoPegangan"), session);
		
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);

		this.context.put("txtNoPermohonanC", getParam("txtNoPermohonanC"));
		this.context.put("txdTarikhMohonC", getParam("txdTarikhMohonC"));
		this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		this.context.put("txtNoWarta", getParam("txtNoWarta"));
		this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
		this.context.put("txtNoLot", getParam("txtNoLot"));
		this.context.put("selectNegeriC", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerahC", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukimC", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));

		this.context.put("flagDetail", flagDetail);
		setupPage(session, action, list);
		
		vm = "app/php2/online/frmPYWSenaraiFailOnline.jsp";

		// SET DEFAULT PARAM
		this.context.put("actionPenyewaan", actionPenyewaan);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		//this.context.put("idLuasKegunaan", idLuasKegunaan);
	    //this.context.put("idLuas", idLuas);
	    this.context.put("idPermohonanSewa", idPermohonanSewa);
		return vm;
	}

}
