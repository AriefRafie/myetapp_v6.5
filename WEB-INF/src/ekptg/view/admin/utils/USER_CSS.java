package ekptg.view.admin.utils;

public class USER_CSS {

	private String user_login;
	private String css_name;

	public USER_CSS() {}
	
	public USER_CSS(String user_login, String css_name){
		this.user_login = user_login;
		this.css_name = css_name;
	}
	
	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String userLogin) {
		user_login = userLogin;
	}

	public String getCss_name() {
		return css_name;
	}

	public void setCss_name(String cssName) {
		css_name = cssName;
	}

}
