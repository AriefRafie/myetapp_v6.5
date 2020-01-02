package ekptg.view.php2;

import lebah.portal.AjaxBasedModule;

public class FrmAPBPopupPilihPegawaiView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    
		String report = getParam("report");
		String idFail = getParam("idFail");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idAduan = getParam("idAduan");
		
		vm = "app/php2/frmAPBPopupPilihPegawai.jsp";
		
		this.context.put("report", report);
		this.context.put("idFail", idFail);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idAduan", idAduan);
		
		return vm;
	}
}