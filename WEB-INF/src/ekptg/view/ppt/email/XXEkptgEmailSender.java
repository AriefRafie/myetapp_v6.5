/**
 * @author saiful.bakhtiar@hla-group.com
 * @version 1.0
 * 
 */
package ekptg.view.ppt.email;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;

public class XXEkptgEmailSender {
	protected static Logger log = Logger.getLogger(XXEkptgEmailSender.class);
	private static XXEkptgEmailSender singleton;
	private static EmailProperty pro = EmailProperty.getInstance();
	private static final String EMAIL_HOST= pro.getHost();
	private static final int SMTP_PORT = Integer.parseInt(pro.getPort());
	private Properties props = null;
	private Session mailSession = null;
	private MimeMessage msg = null;
	private InternetAddress multipleTo [];
	private InternetAddress multipleCC [];
	private File fileAttachments[];
	public String FROM = null;
	public String MESSAGE = null;
	public String RECIEPIENT = null;
	public String SUBJECT = null;
	public String MULTIPLE_RECIEPIENT [];
	public String TO_CC [];
	public String ATTACHMENT[];
	private XXEkptgEmailSender(){
		getGMailProperties();
		getMailSession();
	}
	
	public static synchronized XXEkptgEmailSender getInstance(){
		//if(singleton  == null){
			singleton = new XXEkptgEmailSender();
		//}
		
		return singleton;
	}
	private Properties getMailProperties(){
		props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
	    props.setProperty("mail.host", EMAIL_HOST);
	    props.setProperty("mail.smtp.port", String.valueOf(SMTP_PORT));
	    props.put("mail.debug", "true");
	    

	   	return props;
	}
	
	private Properties getGMailProperties(){
		props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", "smtp.gmail.com");
		props.put("mail.smtps.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtps.port", "465");
		props.put("mail.smtp.starttls.enable","true");
		
		return props;

	}
	private MimeMessage setMessage()throws Exception{
		msg = new MimeMessage(mailSession);
		if(MESSAGE == null)
			throw new Exception("EMAIL DONT HAVE ANY MESSAGE");
		if(SUBJECT == null)
			throw new Exception("EMAIL DONT HAVE ANY SUBJECT");
		if(RECIEPIENT == null && MULTIPLE_RECIEPIENT.length <= 0)
			throw new Exception("EMAIL DONT HAVE ANY RECIEPIENT");
		
		if(MULTIPLE_RECIEPIENT != null && MULTIPLE_RECIEPIENT.length > 0){
			multipleTo = new InternetAddress[MULTIPLE_RECIEPIENT.length];
			for(int i = 0; i<MULTIPLE_RECIEPIENT.length; i++){
				String to = MULTIPLE_RECIEPIENT[i];
				if(to != null && !to.equals(""))
					multipleTo[i]= new InternetAddress(MULTIPLE_RECIEPIENT[i]);
			}
			log.info("MULTIPLE EMAIL RECEIPIENT : "+multipleTo);
		}
		
		if(TO_CC != null && TO_CC.length > 0){
			multipleCC = new InternetAddress[TO_CC.length];
			for(int i=0;i<TO_CC.length;i++){
				String cc = TO_CC[i];
				if(cc != null && !cc.equals("")){
					multipleCC[i] = new InternetAddress(TO_CC[i]);
				}
			}
			log.info("MULTIPLE EMAIL CC : "+multipleCC);
		}
		if(ATTACHMENT != null && ATTACHMENT.length > 0){
			int totalAttachment = ATTACHMENT.length;
			
			fileAttachments = new File[totalAttachment];
			for(int i =0;i<totalAttachment;i++){
				fileAttachments[i] = new File(ATTACHMENT[i]);
				System.out.println(fileAttachments[i].getName());
			}
		}
		msg.setSubject(SUBJECT);
		Multipart mp = new MimeMultipart();

		MimeBodyPart textPart = new MimeBodyPart();
	    textPart.setContent(MESSAGE, "text/html");
	    if(fileAttachments != null && fileAttachments.length > 0){
			for(int i = 0;i<fileAttachments.length;i++){
				MimeBodyPart attachPart = new MimeBodyPart();
				FileDataSource source = new FileDataSource(fileAttachments[i]);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(fileAttachments[i].getName());
				mp.addBodyPart(attachPart);
			}
		}
	    mp.addBodyPart(textPart);
	    
	    msg.setContent(mp);
	    msg.setFrom(new InternetAddress(FROM));
	    if(multipleTo != null && multipleTo.length > 0){
	    	 msg.addRecipients(Message.RecipientType.TO,multipleTo);
	    }
	    else{
	    	msg.addRecipient(Message.RecipientType.TO,new InternetAddress(RECIEPIENT));
	    }
	    
	    if(multipleCC != null && multipleCC.length > 0){
	    	msg.addRecipients(Message.RecipientType.CC, multipleCC);
	    }
	    log.info("EMAIL TO: "+RECIEPIENT);
	    log.info("EMAIL CC: "+TO_CC);
	    return msg;


	}
	public void sendEmail(){
		try{
			setMessage();
			Transport transport = mailSession.getTransport();
			//transport.connect();
			transport.connect("smtp.gmail.com","etappkjp@gmail.com","etapp123");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private Session getMailSession(){
		mailSession = Session.getDefaultInstance(props);
		return mailSession;
	}
}
