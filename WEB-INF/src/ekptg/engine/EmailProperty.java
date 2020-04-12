package ekptg.engine;

import java.util.ResourceBundle;

public class EmailProperty {
	private String host;
	private String port;
	private String from;
	private final String BASENAME = "ekptgmail";
	private ResourceBundle rb = null;
	private volatile static EmailProperty singleton =null;
	
	private String aduanFrom;
	private String aduanTo;
	private String aduanCc;
	
	private EmailProperty(){
		rb = ResourceBundle.getBundle(BASENAME);
	}
	
	public String getHost() {
		//host = rb.getString("SMTP_HOST2_GM");//gmail
		host = rb.getString("SMTP_HOST");//ekptgemail
		return host;
	}
	
	public String getPort() {
		//port = rb.getString("SMTP_PORT2_GM");//gmail
		port = rb.getString("SMTP_PORT");//ekptgemail
		return port;
	}
	
	public String getFrom() {
		//from = rb.getString("SMTP_FROM2_GM");//gmail
		from = rb.getString("SMTP_FROM");//ekptgemail
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
	
	public String getKata() {
		return rb.getString("password");	//emel gmail
	}
	
	public static EmailProperty getInstance(){
		if(singleton == null){
			synchronized(EmailProperty.class){
				if(singleton == null){
					singleton = new EmailProperty(); 
				}
			}
		}
		return singleton;
	}
	
	
}
