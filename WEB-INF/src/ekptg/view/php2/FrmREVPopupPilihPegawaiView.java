package ekptg.view.php2;

import lebah.portal.AjaxBasedModule;

public class FrmREVPopupPilihPegawaiView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    
		String report = getParam("report");
		String idHasil = getParam("idHasil");
		String idBayaran = getParam("idBayaran");
		
		vm = "app/php2/frmREVPopupPilihPegawai.jsp";
		
		this.context.put("report", report);
		this.context.put("idHasil", idHasil);
		this.context.put("idBayaran", idBayaran);

		
		return vm;
	}

}
