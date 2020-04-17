package ekptg.model.htp;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class EmailProperty {
	private String host;
	private String port;
	private String from;
	private final String BASENAME = "ekptgmailhtp";
	private ResourceBundle rb = null;
	private volatile static EmailProperty singleton =null;
	
	private String aduanFrom;
	private String aduanTo;
	private String kepada;
	private String[] kepadas;
	private String aduanCc;
	
	private EmailProperty(){
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
	
	public String getKepada() {
		kepada = rb.getString("TO");
		return kepada;
	}

	public String[] getKepadas() {
		//kepadas = rb.getStringArray("TO");
		int x = 1;
		int xx = 1;
		for (Enumeration e = rb.getKeys(); e.hasMoreElements();x++){
			  String name = (String)e.nextElement();
//			  String value = (String)rb.getString(name);
//			  System.out.println("name:"+name+", " + 
//				       "value = " + value);
			  if(name.equals("TO")){
				  kepadas[xx] = (String)rb.getString(name);
				  xx++;
			  }
			  System.out.println("XX:"+xx); 
		}
		return kepadas;
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
		System.out.println(EmailProperty.getInstance());	
		//System.out.println(EmailProperty.getInstance().getKepada());
		EmailProperty ep = EmailProperty.getInstance();
		String ke[] =  ep.getKepadas();
		//System.out.println(ke[0]);
	}
	
}
