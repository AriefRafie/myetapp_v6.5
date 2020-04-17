package ekptg.intergration;

import java.util.ResourceBundle;

public class XEmailProperty {
	private String host;
	private String port;
	private String from;
	private final String BASENAME = "ekptgmail";
	private ResourceBundle rb = null;
	private volatile static XEmailProperty singleton =null;
	
	private String aduanFrom;
	private String aduanTo;
	private String aduanCc;
	
	private XEmailProperty(){
		rb = ResourceBundle.getBundle(BASENAME);
	}
	public String getHost() {
		host = rb.getString("SMTP_HOST");
		return host;
	}
	
	public String getPort() {
		port = rb.getString("SMTP_PORT");
		return port;
	}
	
	public String getFrom() {
		from = rb.getString("SMTP_FROM");
		return from;
	}
		
	public String getAduanFrom() {
		aduanFrom = rb.getString("ADUAN_ADMIN_FROM");
		return aduanFrom;
	}
	
	public String getAduanTo() {
		aduanTo = rb.getString("ADUAN_ADMIN_TO");
		return aduanTo;
	}
	
	public String getAduanCc() {
		aduanCc = rb.getString("ADUAN_ADMIN_CC");
		return aduanCc;
	}
	
	public static XEmailProperty getInstance(){
		if(singleton == null){
			synchronized(XEmailProperty.class){
				if(singleton == null){
					singleton = new XEmailProperty(); 
				}
			}
		}
		return singleton;
	}
	public static void main(String args[]){
		System.out.println(XEmailProperty.getInstance().getAduanFrom());
	}
	
}
