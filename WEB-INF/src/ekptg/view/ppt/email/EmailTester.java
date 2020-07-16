package ekptg.view.ppt.email;

import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
//import ekptg.intergration.EkptgEmailSender;

public class EmailTester {
	static Logger myLogger = Logger.getLogger(EmailTester.class);
	
	public static void main(String args []){

		
	}
	
	public static void setEmail(String jenisSuburusan, String emailTO, String emailType, 
								String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian){
		
		
		String suburusan = "";
		
		if(jenisSuburusan.equals("seksyen8")){
			suburusan = "Seksyen 8";
		}else if(jenisSuburusan.equals("seksyen4")){
			suburusan = "Seksyen 4";
		}else if(jenisSuburusan.equals("sementara")){
			suburusan = "Pendudukan / Penggunaan Sementara";
		}else if(jenisSuburusan.equals("PPK")){
			suburusan = "PPK TAC";
			String subject_emel = "myetaPP : Pengesahan Permohonan TAC "+suburusan;
			String email_from = "norhayati.bain@gmail.com";
			
			EmailSender email = EmailSender.getInstance();
			
			//email.FROM = email_from;
			email.SUBJECT = subject_emel;
			email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,suburusan,nofail,tarikh_permohonan,nama_kementerian);	
			email.MULTIPLE_RECIEPIENT = new String[1];
			//email.MULTIPLE_RECIEPIENT[0] = "m.syazreen@hla-group.com";
			
			email.TO_CC = new String[1];
			//email.TO_CC[0]  = "razman@hla-group.com";

			email.sendEmail();
		}
		else{
		//subject (untuk testing)
		String subject_emel = "eTaPP : Pengesahan Permohonan Pengambilan Tanah "+suburusan;		
		//default sender email
		String email_from = "etapp_webmaster@ekptg.gov.my";
		
		EmailSender email = EmailSender.getInstance();
		
		//email.FROM = email_from;
		email.SUBJECT = subject_emel;
		email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,suburusan,nofail,tarikh_permohonan,nama_kementerian);	
		email.MULTIPLE_RECIEPIENT = new String[1];
		//email.MULTIPLE_RECIEPIENT[0] = "m.syazreen@hla-group.com";
		
		email.TO_CC = new String[1];
		//email.TO_CC[0]  = "razman@hla-group.com";

		email.sendEmail();
		}
	

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
		
		
		if(emailType.equals("hantarUntukPengesahan")){
			
			bff.append("Sila sahkan permohonan Pengambilan Tanah daripada "+nama_kementerian+" ");
			bff.append("<br/>");
			bff.append("yang diterima pada "+tarikh_permohonan+".");
			
		}else if(emailType.equals("hantarUntukPengesahanDanUntukAgihan")){
			
			bff.append("Sila sahkan serta membuat agihan tugas bagi permohonan Pengambilan Tanah ");
			bff.append("<br/>");
			bff.append("daripada "+nama_kementerian+" yang diterima pada "+tarikh_permohonan+".");
			
		}else if(emailType.equals("hantarUntukAgihan")){
			
			bff.append("Sila semak pengagihan tugas bagi permohonan Pengambilan Tanah daripada "+nama_kementerian+" - "+nofail+".");
			
		}else if(emailType.equals("hantarUntukSemakan")){
			
			bff.append("Sila semak dan sahkan Kertas Kerja MMK bagi permohonan Pengambilan Tanah daripada "+nama_kementerian+" - "+nofail+".");
		
		}else if(emailType.equals("hantarUntukTAC")){
			
			bff.append("Sila semak dan sahkan No.TAC");
		
		}
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan serta pengesahan dari pihak tuan/puan.");
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
