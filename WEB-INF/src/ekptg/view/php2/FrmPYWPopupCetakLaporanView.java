package ekptg.view.php2;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWPopupCetakLaporanData;

public class FrmPYWPopupCetakLaporanView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPYWPopupCetakLaporanData logic = new FrmPYWPopupCetakLaporanData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmPYWPopupCetakLaporan.jsp";
	    
	    String idFail = getParam("idFail");
	    String idPermohonan = getParam("idPermohonan");
	    String idHakmilikPermohonan = getParam("idHakmilikPermohonan");   
	    String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idTanah = getParam("idTanah");
		String report = getParam("report");
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		String idKementerian = getParam("socKementerian"); //BY AIN 9/5/2017
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		
		String noFail = logic.getNoFailByIdFail(idFail);
		
		this.context.put("selectPegawai",HTML.selectPegawaiUnitPenyewaanByNegeri(idNegeriUser,"socPegawai", Utils.parseLong(idPegawai), "", ""));
		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian), "", ""));
		
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idHakmilikPermohonan", idHakmilikPermohonan);	
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idTanah", idTanah);
		this.context.put("report", report);
		this.context.put("noFail", noFail);
		
		return vm;
	}

}
