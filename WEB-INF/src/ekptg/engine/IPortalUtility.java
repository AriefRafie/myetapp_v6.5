package ekptg.engine;

import lebah.portal.element.User;

public interface IPortalUtility {
	public String resetPassword(String userId,String email)throws Exception;
	@Deprecated
	public User getUserOnlineById(String usrlogin);
	public OnlineUser getUserOnline(String loginName);
	public OnlineUser getUserKJP(String loginName);
}
