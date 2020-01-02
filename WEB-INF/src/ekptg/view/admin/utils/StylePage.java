package ekptg.view.admin.utils;

public class StylePage {

	private String css_title;
	private String css_name;
	
	public StylePage() {}
	
	public StylePage(String css_title, String css_name){
		this.css_title = css_title;
		this.css_name = css_name;
	}

	public String getCss_title() {
		return css_title;
	}

	public void setCss_title(String cssTitle) {
		css_title = cssTitle;
	}

	public String getCss_name() {
		return css_name;
	}

	public void setCss_name(String cssName) {
		css_name = cssName;
	}

}
