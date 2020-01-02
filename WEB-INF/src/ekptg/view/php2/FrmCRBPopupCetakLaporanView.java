package ekptg.view.php2;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPLPPopupCetakLaporanData;

public class FrmCRBPopupCetakLaporanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPPopupCetakLaporanData logic = new FrmPLPPopupCetakLaporanData();

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmCRBPopupCetakLaporan.jsp";
	    
	    String idFail = getParam("idFail");
	    String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idTanah = getParam("idTanah");
		String report = getParam("report");
		String idPermohonan = getParam("idPermohonan");
		String idMesyuarat = getParam("idMesyuarat");
		String txtAgensi = getParam("txtAgensi"); //by Ain
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		String idPejabat = getParam("socNamaPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idPejabatPTG = getParam("socPejabatPTG");
		if (idPejabatPTG == null || idPejabatPTG.trim().length() == 0){
			idPejabatPTG = "99999";
		}
		String idPejabatPTD = getParam("socPejabatPTD");
		if (idPejabatPTD == null || idPejabatPTD.trim().length() == 0){
			idPejabatPTD = "99999";
		}
		String idPejabatPBT = getParam("socPejabatPBT");
		if (idPejabatPBT == null || idPejabatPBT.trim().length() == 0){
			idPejabatPBT = "99999";
		}
		String idPejabatJKPTG = getParam("socPejabatJKPTG");
		if (idPejabatJKPTG == null || idPejabatJKPTG.trim().length() == 0){
			idPejabatJKPTG = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String noFail = logic.getNoFailByIdFail(idFail);	
		String idNegeri = logic.getIdNegeriByIdFail(idFail);	
		
		this.context.put("selectPegawai",HTML.selectPegawaiUnitPenguatkuasaan("socPegawai", Utils.parseLong(idPegawai), "", ""));
		this.context.put("selectNamaPejabat",HTML.selectNamaPejabat("socNamaPejabat", Utils.parseLong(idPejabat), "", "", noFail));
		
		this.context.put("selectPejabatPTG",HTML.SelectPejabatPTGbyNegeri(idNegeri, "socPejabatPTG", Utils.parseLong(idPejabatPTG), "", ""));
		this.context.put("selectPejabatPTD",HTML.SelectPejabatPTDbyNegeri(idNegeri, "socPejabatPTD", Utils.parseLong(idPejabatPTD), "", ""));
		this.context.put("selectPejabatPBT",HTML.SelectPejabatPBTByNegeri(idNegeri, "socPejabatPBT", Utils.parseLong(idPejabatPBT), "", ""));
		//this.context.put("selectPejabatJKPTG",HTML.SelectPejabatJKPTGByIdNegeri("socPejabatJKPTG", Utils.parseLong(idPejabatJKPTG), "", "", idNegeri));
		this.context.put("selectPejabatJKPTG", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabatJKPTG", Utils.parseLong(idPejabatJKPTG), "","",idNegeri, "4"));
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", ""));
		this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi", Utils.parseLong(idAgensi), "", ""));
		this.context.put("selectAgensi1",HTML.SelectAgensi("socAgensi1", Utils.parseLong(idAgensi), "", ""));
		this.context.put("selectAgensi2",HTML.SelectAgensi("socAgensi2", Utils.parseLong(idAgensi), "", ""));
		this.context.put("txtAgensi",txtAgensi);
		this.context.put("idFail", idFail);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idTanah", idTanah);
		this.context.put("report", report);
		this.context.put("noFail", noFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idMesyuarat", idMesyuarat);
		
		return vm;
	}
}
