package ekptg.view.php2.emel;

import org.apache.log4j.Logger;

import ekptg.intergration.XEkptgEmailSender;

public class EmailOnline {
	static Logger myLogger = Logger.getLogger(EmailOnline.class);
	
	public static void main(String args []){
		
	}
	
	public static void setEmail(String emailTO, String emailType, String noPermohonan,String urusan){
	
		String subject_emel = "";
		if(emailType.equals("hantarSemakanPenawaran")){
			subject_emel = "eTaPP : Semakan Permohonan Penawaran Online ";
		}else if(emailType.equals("hantarPengesahanPenawaran")){
			subject_emel = "eTaPP : Pengesahan Permohonan Penawaran Online ";
		}else if(emailType.equals("hantarSemakanTukarguna")){
			subject_emel = "eTaPP : Semakan Permohonan Tukarguna Online ";
		}else if(emailType.equals("hantarPengesahanTukarguna")){
			subject_emel = "eTaPP : Pengesahan Permohonan Tukarguna Online ";
		}
		
		
		//default sender email
		String email_from = "mohdfaizal_hassan@hla-group.com";
			
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		email.FROM = email_from;
		email.SUBJECT = subject_emel;
		email.MESSAGE =setMessageTable(emailType, urusan, noPermohonan)+""+setParaGraph(emailType, noPermohonan);	
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = "raiyuzen@gmail.com";
		
		email.TO_CC = new String[1];
		email.TO_CC[0]  = "raiyuzen@gmail.com";
		
		email.sendEmail();

	}
	
	public static String setMessageTable(String emailType, String urusan, String noPermohonan){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td><b>PERMOHONAN " + urusan + " : " +  noPermohonan + "</b></td>");
		bff.append("</tr>");
		bff.append("</table>");
		
		return bff.toString();
	}
	public static String setParaGraph(String emailType, String noPermohonan){
		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		
		
		if(emailType.equals("hantarSemakanPenawaran")){		
			bff.append("Sila buat semakan maklumat Permohonan Penawaran bagi nombor permohonan : " + noPermohonan + ".");	
		}else if(emailType.equals("hantarPengesahanPenawaran")){
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG. ");
		}else if(emailType.equals("hantarSemakanTukarguna")){		
			bff.append("Sila buat semakan maklumat Permohonan Tukarguna bagi nombor permohonan : " + noPermohonan + ".");	
		}else if(emailType.equals("hantarPengesahanTukarguna")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG. ");
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
