package ekptg.view.online;

import lebah.portal.AjaxBasedModule;

public class FrmOnlineMenuUtamaHTP extends AjaxBasedModule {
	
	private static final String PATH="app/online/manuUtama/";
	private String vm = PATH+"frmMenuUtamaKJPpptphp.jsp";

	@Override
	public String doTemplate2() throws Exception {
		// TODO Auto-generated method stub
		return vm;
	}

}