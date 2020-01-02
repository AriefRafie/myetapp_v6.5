package ekptg.view.online;

import lebah.portal.AjaxBasedModule;

public class FrmOnlineManuUtama extends AjaxBasedModule {
	
	private static final String PATH="app/online/manuUtama/";
	private String vm = PATH+"frmManuUtama.jsp";

	@Override
	public String doTemplate2() throws Exception {
		// TODO Auto-generated method stub
		return vm;
	}

}
