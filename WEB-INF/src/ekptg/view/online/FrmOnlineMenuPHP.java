package ekptg.view.online;

import lebah.portal.AjaxBasedModule;

public class FrmOnlineMenuPHP extends AjaxBasedModule{
	private static final String PATH="app/online/manuUtama/";
	private String vm = PATH+"indexPHP.jsp";
	@Override
	public String doTemplate2() throws Exception {
	
		return vm;
	}

}
