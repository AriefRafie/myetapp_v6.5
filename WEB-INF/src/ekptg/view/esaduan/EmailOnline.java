package ekptg.view.esaduan;

import org.apache.log4j.Logger;

public class EmailOnline {
	static Logger myLogger = Logger.getLogger(EmailOnline.class);
	
	public static void main(String args []){

		
	}
	
	
	public static void setEmail(String jenisSuburusan, String emailTO, String emailType, 
								String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian){
	
		String subject_emel = "";
		if(emailType.equals("1")){
			subject_emel = "eTaPP : Cubaan Notifikasi ";
		}
		
		//default sender email
		String email_from = "razman@hla-group.com";
			
		EkptgEmailSenderFLMS email = EkptgEmailSenderFLMS.getInstance();
		email.FROM = email_from;
		email.SUBJECT = subject_emel;
		email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian);	
		email.MULTIPLE_RECIEPIENT = new String[1];
		//email.MULTIPLE_RECIEPIENT[0] = "m.syazreen@gmail.com";
		email.MULTIPLE_RECIEPIENT[0] = "razman@hla-group.com";
		
		email.TO_CC = new String[1];
		email.TO_CC[0]  = "razman.zainal@gmail.com";
		
		email.sendEmail();

	}
	
	public static String setMessageTable(String emailType,String nama_projek){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td><b>NAMA PROJEK : "+nama_projek.toUpperCase()+"</b></td>");
		bff.append("</tr>");
		bff.append("</table>");
		
		return bff.toString();
	}
	public static String setParaGraph(String emailType,String suburusan,String nofail,String tarikh_permohonan,String nama_kementerian){
		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		
		
		if(emailType.equals("1")){		
			bff.append("Sila sahkan maklumat permohonan Pengambilan Tanah yang didaftar pada "+tarikh_permohonan+".");	
		}
		
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Emel ini dijana oleh Sistem eTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
	}
}
