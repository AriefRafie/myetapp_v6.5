package ekptg.view.admin.utils;

public class UrlUtil {
	
	private String string;
	
	public UrlUtil(String str) {
		string = str;
	}
	
	public String append(String s) {
		if ( string.indexOf("?") > 0 ) return string.concat("&").concat(s);
		else return string.concat("?").concat(s);
	}
	
	public String toString() {
		if ( string.indexOf("?") > 0 ) return string.concat("&");
		else return string.concat("?");
	}
	
	public static void main(String[] args) throws Exception {
		String url = "/__xyz_123_abc?p=1";
		System.out.println(new UrlUtil(url).toString().concat("command=do_thing"));
	}

}
