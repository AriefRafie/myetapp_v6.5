package ekptg.view.php2;

import lebah.portal.AjaxBasedModule;

public class FrmCRBPopupPilihPegawai extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    
		String report = getParam("report");
		String idFail = getParam("idFail");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		
		vm = "app/php2/frmCRBPopupPilihPegawai.jsp";
		
		this.context.put("report", report);
		this.context.put("idFail", idFail);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);

		
		return vm;
	}
}
