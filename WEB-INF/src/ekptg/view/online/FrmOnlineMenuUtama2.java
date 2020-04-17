package ekptg.view.online;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.engine.IPortalUtility;
import ekptg.engine.OnlineUser;
import ekptg.engine.PortalUtility;

public class FrmOnlineMenuUtama2 extends AjaxBasedModule {
	
	private static final String PATH="app/online/manuUtama/";
	private String vm = PATH+"index2.jsp";
	private IPortalUtility portalUtility;
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		String user_login = (String)session.getAttribute("_ekptg_user_id");
		getUserDetail(user_login);
		return vm;
	}
	private void getUserDetail(String user_login){
		OnlineUser user = getUtility().getUserOnline(user_login);
		context.put("user", user);
		
	}
	private IPortalUtility getUtility(){
		if(portalUtility == null)
			portalUtility = new PortalUtility();
		return portalUtility;
	}
}
