package ekptg.model.admin;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailTesting {

    public static void main(String[] args) {

        //final String username = "rosli@si-protech.com.my";
        //final String password = "m.roslimail";
        //final String username = "etapp.pla@gmail.com";
        //final String password = "etapp123";
    	//final String username = "myetapp2016@gmail.com";
    	//final String password = "ProjekeTaPP2016";
    	final String username = "roslizakariasip@gmail.com";
    	final String password = "m.rosligmail";
       
        Properties prop = new Properties();
		//
        prop.put("mail.smtp.host", "smtp.gmail.com");
		//prop.put("mail.smtp.host", "mail2.netmyne.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        //
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("roslizakaria@gmail.com, matle04@yahoo.com")
            );
            message.setSubject("Testing Gmail TLS");
            //message.setText("Dear Mail Crawler,"
            //        + "\n\n Please do not spam my email!");
    		StringBuffer sb = new StringBuffer();
    		sb.append("");
    		sb.append("");
    		sb.append("Nota: Emel ini dijana dari sistem MyeTaPP.");
    		//sb.append("<br/>");
    		//sb.append("<br/>");
    		String msg =sb.toString();
            message.setText("Selesai"+msg);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}