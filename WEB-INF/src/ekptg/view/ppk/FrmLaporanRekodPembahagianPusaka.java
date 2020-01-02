package ekptg.view.ppk;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

public class FrmLaporanRekodPembahagianPusaka extends AjaxBasedModule {
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmLaporanRekodPembahagianPusaka.jsp";
		String submit = getParam("hitButt");
		String mode = getParam("mode");
		
		
		
		
		return vm;
	}
}
