package ekptg.view.esaduan;

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
		host = rb.getString("SMTP_HOST2");
		return host;
	}
	
	public String getPort() {
		port = rb.getString("SMTP_PORT2");
		return port;
	}
	
	public String getFrom() {
		from = rb.getString("SMTP_FROM2");
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
	public static void main(String args[]){
		System.out.println("TEST :"+EmailProperty.getInstance().getAduanFrom());
	}
	
}
