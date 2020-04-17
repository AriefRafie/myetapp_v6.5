package ekptg.view.php2;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBPopupCetakLaporanData;

public class FrmAPBPopupCetakLaporanView extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FrmAPBPopupCetakLaporanData logic = new FrmAPBPopupCetakLaporanData();

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmAPBPopupCetakLaporan.jsp";
	    
	    String idFail = getParam("idFail");
	    String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idTanah = getParam("idTanah");
		String report = getParam("report");
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		
		String noFail = logic.getNoFailByIdFail(idFail);		
		
		this.context.put("selectPegawai",HTML.selectPegawaiUnitAPB("socPegawai", Utils.parseLong(idPegawai), "", ""));
		
		this.context.put("idFail", idFail);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idTanah", idTanah);
		this.context.put("report", report);
		this.context.put("noFail", noFail);
		
		return vm;
	}

}
