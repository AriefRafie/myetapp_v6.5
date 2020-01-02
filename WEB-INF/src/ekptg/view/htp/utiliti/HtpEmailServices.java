package ekptg.view.htp.utiliti;

import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
//import ekptg.view.ppt.email.EkptgEmailSender;

public class HtpEmailServices {
	
	static Logger myLog = Logger.getLogger(ekptg.view.htp.utiliti.HtpEmailServices.class);

	public static void main(String args []){
		
	}
	
	
	public void setEmail(String emUrusan
		,String emEmailto
		,String emMaincontent
		,String emNofail
		,String emTitle
		,String emTarikhMohon
		,String emAgensi){			
//		System.out.println("emUrusan:"+emUrusan);
//		System.out.println("emailto:"+emEmailto);
//		System.out.println("emMaincontent:"+emMaincontent);
		
		String emailSubject = "";	
		if(emMaincontent.equals("hantarPengesahanPermohonanPajakan")){
			emailSubject = "MyeTaPP : Pengesahan Permohonan Pajakan ";
		}	
		
		//default sender email
		EmailProperty pro = EmailProperty.getInstance();
		String emEmailFrom = pro.getFrom();
					
		EmailSender email = EmailSender.getInstance();
		email.FROM = emEmailFrom;
		email.SUBJECT = emailSubject;
		email.MESSAGE =setMessageTable(emMaincontent,emTitle)+""+setParaGraph(emMaincontent,"",emNofail,emTarikhMohon,emAgensi,emTitle);	
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = emEmailto;
		
		email.sendEmail();
		
	}
	
	private String setMessageTable(String emMaincontent,String emTitle){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td><b>Penerimaan Permohonan Pajakan : "+emTitle.toUpperCase()+"</b></td>");
		bff.append("</tr>");
		bff.append("</table>");	
		return bff.toString();
	}
	
	private String setParaGraph(String emMaincontent,String emUrusan,String emNofail,String emTarikhMohon,String emAgensi,String emTitle){		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
				
		// Perdaftaran Permohonan
		if(emMaincontent.equals("hantarPengesahanPermohonanPajakan")){		
			bff.append("Sila sahkan maklumat Permohonan Pajakan Tanah yang didaftar pada "+emTarikhMohon+".");	
		}
		
		/*else if(emMaincontent.equals("hantarLulus")){
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG. ");
		}else if(emMaincontent.equals("lulus")){
			bff.append("Permohonan telah diluluskan, permohonan ini perlu dihantar kepada JKPTG. ");		
		// Perdaftaran Penarikan
		}else if(emMaincontent.equals("hantarSemakanPenarikan")){		
			bff.append("Sila sahkan maklumat permohonan Penarikan Balik sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emMaincontent.equals("hantarLulusPenarikan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		// Perdaftaran Pembatalan
		}else if(emMaincontent.equals("hantarSemakanPembatalan")){		
			bff.append("Sila sahkan maklumat permohonan Pembatalan sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emMaincontent.equals("hantarLulusPembatalan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		// Perdaftaran Bantahan
		}else if(emMaincontent.equals("hantarSemakanBantahan")){		
			bff.append("Sila sahkan maklumat permohonan Bantahan sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emMaincontent.equals("hantarLulusBantahan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		}else if(emMaincontent.equals("mintaBayaranPampasan")){		
			bff.append("MEMOHON BAYARAN PAMPASAN PENGAMBILAN TANAH UNTUK PROJEK "+emTitle+".");	
		}*/
		
		//if(!emMaincontent.equals("mintaBayaranPampasan")){	
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan dan pengesahan dari pihak tuan/puan.");
		//}
		
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
